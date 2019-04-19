/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jmobius.gameserver.model.entity;

import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.events.EventType;
import com.l2jmobius.gameserver.model.events.annotations.RegisterEvent;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerLogin;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerLogout;
import com.l2jmobius.gameserver.model.events.listeners.ConsumerEventListener;
import com.l2jmobius.gameserver.model.interfaces.IUniqueId;
import com.l2jmobius.gameserver.model.skills.AbnormalVisualEffect;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ExNevitAdventEffect;
import com.l2jmobius.gameserver.network.serverpackets.ExNevitAdventPointInfoPacket;
import com.l2jmobius.gameserver.network.serverpackets.ExNevitAdventTimeChange;

/**
 * Nevit's Blessing handler.
 * @author Janiko
 */
public class NevitSystem implements IUniqueId
{
	public final L2PcInstance _player;
	
	private volatile ScheduledFuture<?> _adventTask;
	private volatile ScheduledFuture<?> _nevitEffectTask;
	
	public NevitSystem(L2PcInstance player)
	{
		_player = player;
		
		if (Config.NEVIT_ENABLED)
		{
			player.addListener(new ConsumerEventListener(player, EventType.ON_PLAYER_LOGIN, (OnPlayerLogin event) -> onPlayerLogin(event), this));
			player.addListener(new ConsumerEventListener(player, EventType.ON_PLAYER_LOGOUT, (OnPlayerLogout event) -> OnPlayerLogout(event), this));
		}
	}
	
	@RegisterEvent(EventType.ON_PLAYER_LOGIN)
	private void onPlayerLogin(OnPlayerLogin event)
	{
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 6);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		// Reset Nevit's Blessing
		if ((_player.getLastAccess() < (cal.getTimeInMillis() / 1000)) && (System.currentTimeMillis() > cal.getTimeInMillis()))
		{
			_player.getVariables().set("hunting_time", 0);
		}
		
		// Send Packets
		_player.sendPacket(new ExNevitAdventPointInfoPacket(getAdventPoints()));
		_player.sendPacket(new ExNevitAdventTimeChange(getAdventTime(), true));
		
		startNevitEffect(_player.getVariables().getInt("nevit_b", 0));
		
		// Set percent
		final int percent = calcPercent(_player.getVariables().getInt("hunting_points", 0));
		
		if ((percent >= 45) && (percent < 50))
		{
			_player.sendPacket(SystemMessageId.YOU_ARE_STARTING_TO_FEEL_THE_EFFECTS_OF_NEVIT_S_ADVENT_BLESSING);
		}
		else if ((percent >= 50) && (percent < 75))
		{
			_player.sendPacket(SystemMessageId.YOU_ARE_FURTHER_INFUSED_WITH_THE_BLESSINGS_OF_NEVIT_CONTINUE_TO_BATTLE_EVIL_WHEREVER_IT_MAY_LURK);
		}
		else if (percent >= 75)
		{
			_player.sendPacket(SystemMessageId.YOUR_CLOAK_HAS_BEEN_UNEQUIPPED_BECAUSE_YOUR_ARMOR_SET_IS_NO_LONGER_COMPLETE);
		}
	}
	
	@RegisterEvent(EventType.ON_PLAYER_LOGOUT)
	private void OnPlayerLogout(OnPlayerLogout event)
	{
		stopNevitEffectTask(true);
		stopAdventTask(false);
	}
	
	public void addPoints(int val)
	{
		if (getEffectTime() > 0)
		{
			setAdventPoints(0);
		}
		else
		{
			setAdventPoints(getAdventPoints() + val);
		}
		
		if (getAdventPoints() > Config.NEVIT_MAX_POINTS)
		{
			setAdventPoints(0);
			startNevitEffect(Config.NEVIT_BONUS_EFFECT_TIME);
		}
		
		final int percent = calcPercent(getAdventPoints());
		switch (percent)
		{
			case 45:
			{
				_player.sendPacket(SystemMessageId.YOU_ARE_STARTING_TO_FEEL_THE_EFFECTS_OF_NEVIT_S_ADVENT_BLESSING);
				break;
			}
			case 50:
			{
				_player.sendPacket(SystemMessageId.YOU_ARE_FURTHER_INFUSED_WITH_THE_BLESSINGS_OF_NEVIT_CONTINUE_TO_BATTLE_EVIL_WHEREVER_IT_MAY_LURK);
				break;
			}
			case 75:
			{
				_player.sendPacket(SystemMessageId.NEVIT_S_ADVENT_BLESSING_SHINES_STRONGLY_FROM_ABOVE_YOU_CAN_ALMOST_SEE_HIS_DIVINE_AURA);
				break;
			}
		}
		
		_player.sendPacket(new ExNevitAdventPointInfoPacket(getAdventPoints()));
	}
	
	public void startAdventTask()
	{
		if (_adventTask == null)
		{
			synchronized (this)
			{
				if ((_adventTask == null) && (getAdventTime() < Config.NEVIT_ADVENT_TIME))
				{
					_adventTask = ThreadPool.schedule(new AdventTask(), 30000);
					_player.sendPacket(new ExNevitAdventTimeChange(getAdventTime(), false));
				}
			}
		}
	}
	
	public class AdventTask implements Runnable
	{
		@Override
		public void run()
		{
			setAdventTime(getAdventTime() + 30);
			if (getAdventTime() >= Config.NEVIT_ADVENT_TIME)
			{
				setAdventTime(Config.NEVIT_ADVENT_TIME);
				stopAdventTask(true);
			}
			else
			{
				addPoints(72);
				if ((getAdventTime() % 60) == 0)
				{
					_player.sendPacket(new ExNevitAdventTimeChange(getAdventTime(), false));
				}
			}
			stopAdventTask(false);
		}
	}
	
	public synchronized void stopAdventTask(boolean sendPacket)
	{
		if (_adventTask != null)
		{
			_adventTask.cancel(true);
			_adventTask = null;
		}
		if (sendPacket)
		{
			_player.sendPacket(new ExNevitAdventTimeChange(getAdventTime(), true));
		}
	}
	
	public synchronized void startNevitEffect(int time)
	{
		if (getEffectTime() > 0)
		{
			stopNevitEffectTask(false);
			time += getEffectTime();
		}
		if ((Config.NEVIT_IGNORE_ADVENT_TIME || (getAdventTime() < Config.NEVIT_ADVENT_TIME)) && (time > 0))
		{
			_player.getVariables().set("nevit_b", time);
			_player.sendPacket(new ExNevitAdventEffect(time));
			_player.sendPacket(SystemMessageId.THE_ANGEL_NEVIT_HAS_BLESSED_YOU_FROM_ABOVE_YOU_ARE_IMBUED_WITH_FULL_VITALITY_AS_WELL_AS_A_VITALITY_REPLENISHING_EFFECT_AND_SHOULD_YOU_DIE_YOU_WILL_NOT_LOSE_EXP);
			_player.startAbnormalVisualEffect(true, AbnormalVisualEffect.NAVIT_ADVENT);
			_nevitEffectTask = ThreadPool.schedule(new NevitEffectEnd(), time * 1000);
		}
	}
	
	public class NevitEffectEnd implements Runnable
	{
		@Override
		public void run()
		{
			if (Config.NEVIT_IGNORE_ADVENT_TIME)
			{
				setAdventTime(0);
			}
			_player.getVariables().remove("nevit_b");
			_player.sendPacket(new ExNevitAdventEffect(0));
			_player.sendPacket(new ExNevitAdventPointInfoPacket(getAdventPoints()));
			_player.sendPacket(SystemMessageId.NEVIT_S_ADVENT_BLESSING_HAS_ENDED_CONTINUE_YOUR_JOURNEY_AND_YOU_WILL_SURELY_MEET_HIS_FAVOR_AGAIN_SOMETIME_SOON);
			_player.stopAbnormalVisualEffect(true, AbnormalVisualEffect.NAVIT_ADVENT);
			stopNevitEffectTask(false);
		}
	}
	
	public synchronized void stopNevitEffectTask(boolean saveTime)
	{
		if (_nevitEffectTask != null)
		{
			if (saveTime)
			{
				final int time = getEffectTime();
				if (time > 0)
				{
					_player.getVariables().set("nevit_b", time);
				}
				else
				{
					_player.getVariables().remove("nevit_b");
				}
			}
			_nevitEffectTask.cancel(true);
			_nevitEffectTask = null;
		}
	}
	
	public L2PcInstance getPlayer()
	{
		return _player;
	}
	
	@Override
	public int getObjectId()
	{
		return _player.getObjectId();
	}
	
	private int getEffectTime()
	{
		if (_nevitEffectTask == null)
		{
			return 0;
		}
		return (int) Math.max(0, _nevitEffectTask.getDelay(TimeUnit.SECONDS));
	}
	
	public boolean isAdventBlessingActive()
	{
		return ((_nevitEffectTask != null) && (_nevitEffectTask.getDelay(TimeUnit.MILLISECONDS) > 0));
	}
	
	public static int calcPercent(int points)
	{
		return (int) ((100.0D / Config.NEVIT_MAX_POINTS) * points);
	}
	
	public void setAdventPoints(int points)
	{
		_player.getVariables().set("hunting_points", points);
	}
	
	public void setAdventTime(int time)
	{
		_player.getVariables().set("hunting_time", time);
	}
	
	public int getAdventPoints()
	{
		return Config.NEVIT_ENABLED ? _player.getVariables().getInt("hunting_points", 0) : 0;
	}
	
	public int getAdventTime()
	{
		return Config.NEVIT_ENABLED ? _player.getVariables().getInt("hunting_time", 0) : 0;
	}
}
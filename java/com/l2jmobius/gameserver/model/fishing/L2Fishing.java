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
package com.l2jmobius.gameserver.model.fishing;

import java.util.concurrent.Future;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.data.xml.impl.FishingMonstersData;
import com.l2jmobius.gameserver.instancemanager.FishingChampionshipManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.events.AbstractScript;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ExFishingHpRegen;
import com.l2jmobius.gameserver.network.serverpackets.ExFishingStartCombat;
import com.l2jmobius.gameserver.network.serverpackets.PlaySound;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class L2Fishing implements Runnable
{
	private L2PcInstance _fisher;
	private int _time;
	private int _stop = 0;
	private int _goodUse = 0;
	private int _anim = 0;
	private int _mode = 0;
	private int _deceptiveMode = 0;
	private Future<?> _fishAiTask;
	private boolean _thinking;
	// Fish datas
	private final int _fishId;
	private final int _fishMaxHp;
	private int _fishCurHp;
	private final double _regenHp;
	private final boolean _isUpperGrade;
	private final int _lureId;
	
	@Override
	public void run()
	{
		if (_fisher == null)
		{
			return;
		}
		
		if (_fishCurHp >= (_fishMaxHp * 2))
		{
			// The fish got away
			_fisher.sendPacket(SystemMessageId.YOUR_BAIT_WAS_STOLEN_BY_THAT_FISH);
			doDie(false);
		}
		else if (_time <= 0)
		{
			// Time is up, so that fish got away
			_fisher.sendPacket(SystemMessageId.THAT_FISH_IS_MORE_DETERMINED_THAN_YOU_ARE_IT_SPIT_THE_HOOK);
			doDie(false);
		}
		else
		{
			aiTask();
		}
	}
	
	public L2Fishing(L2PcInstance Fisher, L2Fish fish, boolean isNoob, boolean isUpperGrade, int lureId)
	{
		_fisher = Fisher;
		_fishMaxHp = fish.getFishHp();
		_fishCurHp = _fishMaxHp;
		_regenHp = fish.getHpRegen();
		_fishId = fish.getItemId();
		_time = fish.getCombatDuration();
		_isUpperGrade = isUpperGrade;
		_lureId = lureId;
		final int lureType;
		if (isUpperGrade)
		{
			_deceptiveMode = (Rnd.get(100) >= 90) ? 1 : 0;
			lureType = 2;
		}
		else
		{
			_deceptiveMode = 0;
			lureType = isNoob ? 0 : 1;
		}
		_mode = (Rnd.get(100) >= 80) ? 1 : 0;
		
		_fisher.broadcastPacket(new ExFishingStartCombat(_fisher, _time, _fishMaxHp, _mode, lureType, _deceptiveMode));
		_fisher.sendPacket(new PlaySound(1, "SF_S_01", 0, 0, 0, 0, 0));
		// Succeeded in getting a bite
		_fisher.sendPacket(SystemMessageId.YOU_VE_GOT_A_BITE);
		
		if (_fishAiTask == null)
		{
			_fishAiTask = ThreadPool.scheduleAtFixedRate(this, 1000, 1000);
		}
	}
	
	public void changeHp(int hp, int pen)
	{
		_fishCurHp -= hp;
		if (_fishCurHp < 0)
		{
			_fishCurHp = 0;
		}
		
		_fisher.broadcastPacket(new ExFishingHpRegen(_fisher, _time, _fishCurHp, _mode, _goodUse, _anim, pen, _deceptiveMode));
		_anim = 0;
		if (_fishCurHp > (_fishMaxHp * 2))
		{
			_fishCurHp = _fishMaxHp * 2;
			doDie(false);
			return;
		}
		else if (_fishCurHp == 0)
		{
			doDie(true);
			return;
		}
	}
	
	public synchronized void doDie(boolean win)
	{
		if (_fishAiTask != null)
		{
			_fishAiTask.cancel(false);
			_fishAiTask = null;
		}
		
		if (_fisher == null)
		{
			return;
		}
		
		if (win)
		{
			final L2FishingMonster fishingMonster = FishingMonstersData.getInstance().getFishingMonster(_fisher.getLevel());
			if (fishingMonster != null)
			{
				if (Rnd.get(100) <= fishingMonster.getProbability())
				{
					_fisher.sendPacket(SystemMessageId.YOU_CAUGHT_SOMETHING_SMELLY_AND_SCARY_MAYBE_YOU_SHOULD_THROW_IT_BACK);
					final L2Npc monster = AbstractScript.addSpawn(fishingMonster.getFishingMonsterId(), _fisher);
					monster.setTarget(_fisher);
				}
				else
				{
					_fisher.sendPacket(SystemMessageId.YOU_CAUGHT_SOMETHING);
					_fisher.addItem("Fishing", _fishId, 1, null, true);
					FishingChampionshipManager.getInstance().newFish(_fisher, _lureId);
				}
			}
		}
		_fisher.endFishing(win);
		_fisher = null;
	}
	
	protected void aiTask()
	{
		if (_thinking)
		{
			return;
		}
		_thinking = true;
		_time--;
		
		try
		{
			if (_mode == 1)
			{
				if (_deceptiveMode == 0)
				{
					_fishCurHp += (int) _regenHp;
				}
			}
			else if (_deceptiveMode == 1)
			{
				_fishCurHp += (int) _regenHp;
			}
			if (_stop == 0)
			{
				_stop = 1;
				int check = Rnd.get(100);
				if (check >= 70)
				{
					_mode = _mode == 0 ? 1 : 0;
				}
				if (_isUpperGrade)
				{
					check = Rnd.get(100);
					if (check >= 90)
					{
						_deceptiveMode = _deceptiveMode == 0 ? 1 : 0;
					}
				}
			}
			else
			{
				_stop--;
			}
		}
		finally
		{
			_thinking = false;
			final ExFishingHpRegen efhr = new ExFishingHpRegen(_fisher, _time, _fishCurHp, _mode, 0, _anim, 0, _deceptiveMode);
			if (_anim != 0)
			{
				_fisher.broadcastPacket(efhr);
			}
			else
			{
				_fisher.sendPacket(efhr);
			}
		}
	}
	
	public void useReeling(int dmg, int pen)
	{
		_anim = 2;
		if (Rnd.get(100) > 90)
		{
			_fisher.sendPacket(SystemMessageId.THE_FISH_HAS_RESISTED_YOUR_ATTEMPT_TO_BRING_IT_IN);
			_goodUse = 0;
			changeHp(0, pen);
			return;
		}
		if (_fisher == null)
		{
			return;
		}
		if (_mode == 1)
		{
			if (_deceptiveMode == 0)
			{
				// Reeling is successful, Damage: $s1
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_REEL_THAT_FISH_IN_CLOSER_AND_CAUSE_S1_DAMAGE);
				sm.addInt(dmg);
				_fisher.sendPacket(sm);
				if (pen > 0)
				{
					sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_REELING_WAS_SUCCESSFUL_MASTERY_PENALTY_S1);
					sm.addInt(pen);
					_fisher.sendPacket(sm);
				}
				_goodUse = 1;
				changeHp(dmg, pen);
			}
			else
			{
				// Reeling failed, Damage: $s1
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_FAILED_TO_REEL_THAT_FISH_IN_FURTHER_AND_IT_REGAINS_S1_HP);
				sm.addInt(dmg);
				_fisher.sendPacket(sm);
				_goodUse = 2;
				changeHp(-dmg, pen);
			}
		}
		else if (_deceptiveMode == 0)
		{
			// Reeling failed, Damage: $s1
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_FAILED_TO_REEL_THAT_FISH_IN_FURTHER_AND_IT_REGAINS_S1_HP);
			sm.addInt(dmg);
			_fisher.sendPacket(sm);
			_goodUse = 2;
			changeHp(-dmg, pen);
		}
		else
		{
			// Reeling is successful, Damage: $s1
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_REEL_THAT_FISH_IN_CLOSER_AND_CAUSE_S1_DAMAGE);
			sm.addInt(dmg);
			_fisher.sendPacket(sm);
			if (pen > 0)
			{
				sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_REELING_WAS_SUCCESSFUL_MASTERY_PENALTY_S1);
				sm.addInt(pen);
				_fisher.sendPacket(sm);
			}
			_goodUse = 1;
			changeHp(dmg, pen);
		}
	}
	
	public void usePumping(int dmg, int pen)
	{
		_anim = 1;
		if (Rnd.get(100) > 90)
		{
			_fisher.sendPacket(SystemMessageId.THE_FISH_HAS_RESISTED_YOUR_ATTEMPT_TO_BRING_IT_IN);
			_goodUse = 0;
			changeHp(0, pen);
			return;
		}
		if (_fisher == null)
		{
			return;
		}
		if (_mode == 0)
		{
			if (_deceptiveMode == 0)
			{
				// Pumping is successful. Damage: $s1
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_PUMPING_IS_SUCCESSFUL_CAUSING_S1_DAMAGE);
				sm.addInt(dmg);
				_fisher.sendPacket(sm);
				if (pen > 0)
				{
					sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_PUMPING_WAS_SUCCESSFUL_MASTERY_PENALTY_S1);
					sm.addInt(pen);
					_fisher.sendPacket(sm);
				}
				_goodUse = 1;
				changeHp(dmg, pen);
			}
			else
			{
				// Pumping failed, Regained: $s1
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_FAILED_TO_DO_ANYTHING_WITH_THE_FISH_AND_IT_REGAINS_S1_HP);
				sm.addInt(dmg);
				_fisher.sendPacket(sm);
				_goodUse = 2;
				changeHp(-dmg, pen);
			}
		}
		else if (_deceptiveMode == 0)
		{
			// Pumping failed, Regained: $s1
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_FAILED_TO_DO_ANYTHING_WITH_THE_FISH_AND_IT_REGAINS_S1_HP);
			sm.addInt(dmg);
			_fisher.sendPacket(sm);
			_goodUse = 2;
			changeHp(-dmg, pen);
		}
		else
		{
			// Pumping is successful. Damage: $s1
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_PUMPING_IS_SUCCESSFUL_CAUSING_S1_DAMAGE);
			sm.addInt(dmg);
			_fisher.sendPacket(sm);
			if (pen > 0)
			{
				sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_PUMPING_WAS_SUCCESSFUL_MASTERY_PENALTY_S1);
				sm.addInt(pen);
				_fisher.sendPacket(sm);
			}
			_goodUse = 1;
			changeHp(dmg, pen);
		}
	}
}

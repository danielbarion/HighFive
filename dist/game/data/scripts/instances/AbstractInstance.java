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
package instances;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.InstanceReenterType;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.InstanceReenterTimeHolder;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

import ai.AbstractNpcAI;

/**
 * Abstract class for Instances.
 * @author FallenAngel
 */
public abstract class AbstractInstance extends AbstractNpcAI
{
	public final Logger LOGGER = Logger.getLogger(getClass().getSimpleName());
	
	public AbstractInstance()
	{
	}
	
	protected void enterInstance(L2PcInstance player, int templateId)
	{
		final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		if (world != null)
		{
			if (world.getTemplateId() == templateId)
			{
				onEnterInstance(player, world, false);
				
				final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
				if (inst.isRemoveBuffEnabled())
				{
					handleRemoveBuffs(player, world);
				}
				if (!inst.getEnterLocs().isEmpty())
				{
					teleportPlayer(player, inst.getEnterLocs().get(getRandom(inst.getEnterLocs().size())), world.getInstanceId(), false);
				}
				return;
			}
			player.sendPacket(SystemMessageId.YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON);
			return;
		}
		
		if (checkConditions(player))
		{
			final InstanceWorld instance = new InstanceWorld();
			instance.setInstance(InstanceManager.getInstance().createDynamicInstance(templateId));
			InstanceManager.getInstance().addWorld(instance);
			onEnterInstance(player, instance, true);
			
			final Instance inst = InstanceManager.getInstance().getInstance(instance.getInstanceId());
			if (inst.getReenterType() == InstanceReenterType.ON_ENTER)
			{
				handleReenterTime(instance);
			}
			if (inst.isRemoveBuffEnabled())
			{
				handleRemoveBuffs(instance);
			}
			if (!inst.getEnterLocs().isEmpty())
			{
				teleportPlayer(player, inst.getEnterLocs().get(getRandom(inst.getEnterLocs().size())), instance.getInstanceId(), false);
			}
		}
	}
	
	protected void finishInstance(InstanceWorld world)
	{
		finishInstance(world, Config.INSTANCE_FINISH_TIME);
	}
	
	protected void finishInstance(InstanceWorld world, int duration)
	{
		final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
		
		if (inst.getReenterType() == InstanceReenterType.ON_FINISH)
		{
			handleReenterTime(world);
		}
		
		if (duration == 0)
		{
			InstanceManager.getInstance().destroyInstance(inst.getId());
		}
		else if (duration > 0)
		{
			inst.setDuration(duration);
			inst.setEmptyDestroyTime(0);
		}
	}
	
	protected void handleReenterTime(InstanceWorld world)
	{
		final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
		final List<InstanceReenterTimeHolder> reenterData = inst.getReenterData();
		
		long time = -1;
		
		for (InstanceReenterTimeHolder data : reenterData)
		{
			if (data.getTime() > 0)
			{
				time = System.currentTimeMillis() + data.getTime();
				break;
			}
			
			final Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.AM_PM, data.getHour() >= 12 ? 1 : 0);
			calendar.set(Calendar.HOUR, data.getHour());
			calendar.set(Calendar.MINUTE, data.getMinute());
			calendar.set(Calendar.SECOND, 0);
			
			if (calendar.getTimeInMillis() <= System.currentTimeMillis())
			{
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			if (data.getDay() != null)
			{
				while (calendar.get(Calendar.DAY_OF_WEEK) != (data.getDay().getValue() + 1))
				{
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
			
			if (time == -1)
			{
				time = calendar.getTimeInMillis();
			}
			else if (calendar.getTimeInMillis() < time)
			{
				time = calendar.getTimeInMillis();
			}
		}
		
		if (time > 0)
		{
			setReenterTime(world, time);
		}
	}
	
	protected void handleRemoveBuffs(InstanceWorld world)
	{
		for (L2PcInstance player : world.getAllowed())
		{
			if (player != null)
			{
				handleRemoveBuffs(player, world);
			}
		}
	}
	
	protected abstract void onEnterInstance(L2PcInstance player, InstanceWorld world, boolean firstEntrance);
	
	protected boolean checkConditions(L2PcInstance player)
	{
		return true;
	}
	
	/**
	 * Sets reenter time for every player in the instance.
	 * @param world the instance
	 * @param time the time in milliseconds
	 */
	protected void setReenterTime(InstanceWorld world, long time)
	{
		for (L2PcInstance player : world.getAllowed())
		{
			if (player != null)
			{
				InstanceManager.getInstance().setInstanceTime(player.getObjectId(), world.getTemplateId(), time);
				if (player.isOnline())
				{
					player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.INSTANT_ZONE_S1_S_ENTRY_HAS_BEEN_RESTRICTED_YOU_CAN_CHECK_THE_NEXT_POSSIBLE_ENTRY_TIME_BY_USING_THE_COMMAND_INSTANCEZONE).addString(InstanceManager.getInstance().getInstanceIdName(world.getTemplateId())));
				}
			}
		}
	}
	
	private void handleRemoveBuffs(L2PcInstance player, InstanceWorld world)
	{
		final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
		switch (inst.getRemoveBuffType())
		{
			case ALL:
			{
				player.stopAllEffectsExceptThoseThatLastThroughDeath();
				
				final L2Summon summon = player.getSummon();
				if (summon != null)
				{
					summon.stopAllEffectsExceptThoseThatLastThroughDeath();
				}
				break;
			}
			case WHITELIST:
			{
				for (BuffInfo info : player.getEffectList().getBuffs())
				{
					if (!inst.getBuffExceptionList().contains(info.getSkill().getId()))
					{
						info.getEffected().getEffectList().stopSkillEffects(true, info.getSkill());
					}
				}
				
				final L2Summon summon = player.getSummon();
				if (summon != null)
				{
					for (BuffInfo info : summon.getEffectList().getBuffs())
					{
						if (!inst.getBuffExceptionList().contains(info.getSkill().getId()))
						{
							info.getEffected().getEffectList().stopSkillEffects(true, info.getSkill());
						}
					}
				}
				break;
			}
			case BLACKLIST:
			{
				for (BuffInfo info : player.getEffectList().getBuffs())
				{
					if (inst.getBuffExceptionList().contains(info.getSkill().getId()))
					{
						info.getEffected().getEffectList().stopSkillEffects(true, info.getSkill());
					}
				}
				
				final L2Summon summon = player.getSummon();
				if (summon != null)
				{
					for (BuffInfo info : summon.getEffectList().getBuffs())
					{
						if (inst.getBuffExceptionList().contains(info.getSkill().getId()))
						{
							info.getEffected().getEffectList().stopSkillEffects(true, info.getSkill());
						}
					}
				}
				break;
			}
		}
	}
}
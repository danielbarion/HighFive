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
package com.l2jmobius.gameserver.model.actor.instance;

import java.util.concurrent.ScheduledFuture;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jmobius.gameserver.model.effects.EffectFlag;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.util.MinionList;

/**
 * This class manages all Monsters. L2MonsterInstance:
 * <ul>
 * <li>L2MinionInstance</li>
 * <li>L2RaidBossInstance</li>
 * <li>L2GrandBossInstance</li>
 * </ul>
 */
public class L2MonsterInstance extends L2Attackable
{
	private static final int MONSTER_MAINTENANCE_INTERVAL = 1000;
	
	protected boolean _enableMinions = true;
	
	private L2MonsterInstance _master = null;
	private volatile MinionList _minionList = null;
	
	protected ScheduledFuture<?> _maintenanceTask = null;
	
	/**
	 * Creates a monster.
	 * @param template the monster NPC template
	 */
	public L2MonsterInstance(L2NpcTemplate template)
	{
		super(template);
		setInstanceType(InstanceType.L2MonsterInstance);
		setAutoAttackable(true);
	}
	
	/**
	 * Return True if the attacker is not another L2MonsterInstance.
	 */
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		if (isFakePlayer())
		{
			return isInCombat() || attacker.isMonster() || (getScriptValue() > 0);
		}
		
		// Check if the L2MonsterInstance target is aggressive
		if (Config.GUARD_ATTACK_AGGRO_MOB && isAggressive() && (attacker instanceof L2GuardInstance))
		{
			return true;
		}
		
		if (attacker.isMonster())
		{
			return attacker.isFakePlayer();
		}
		
		// Anything considers monsters friendly except Players, Attackables (Guards, Friendly NPC), Traps and EffectPoints.
		if (!attacker.isPlayable() && !attacker.isAttackable() && !(attacker instanceof L2TrapInstance) && !(attacker instanceof L2EffectPointInstance))
		{
			return false;
		}
		
		return super.isAutoAttackable(attacker) && !isEventMob();
	}
	
	/**
	 * Return True if the L2MonsterInstance is Aggressive (aggroRange > 0).
	 */
	@Override
	public boolean isAggressive()
	{
		return getTemplate().isAggressive() && !isEventMob() && !isAffected(EffectFlag.PASSIVE);
	}
	
	@Override
	public void onSpawn()
	{
		if (!isTeleporting())
		{
			if (_master != null)
			{
				setRandomWalking(false);
				setIsRaidMinion(_master.isRaid());
				_master.getMinionList().onMinionSpawn(this);
			}
			
			startMaintenanceTask();
		}
		
		// dynamic script-based minions spawned here, after all preparations.
		super.onSpawn();
	}
	
	@Override
	public void onTeleported()
	{
		super.onTeleported();
		
		if (hasMinions())
		{
			getMinionList().onMasterTeleported();
		}
	}
	
	protected int getMaintenanceInterval()
	{
		return MONSTER_MAINTENANCE_INTERVAL;
	}
	
	protected void startMaintenanceTask()
	{
	}
	
	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer))
		{
			return false;
		}
		
		if (_maintenanceTask != null)
		{
			_maintenanceTask.cancel(false); // doesn't do it?
			_maintenanceTask = null;
		}
		
		return true;
	}
	
	@Override
	public boolean deleteMe()
	{
		if (_maintenanceTask != null)
		{
			_maintenanceTask.cancel(false);
			_maintenanceTask = null;
		}
		
		if (hasMinions())
		{
			getMinionList().onMasterDie(true);
		}
		
		if (_master != null)
		{
			_master.getMinionList().onMinionDie(this, 0);
		}
		
		return super.deleteMe();
	}
	
	@Override
	public L2MonsterInstance getLeader()
	{
		return _master;
	}
	
	public void setLeader(L2MonsterInstance leader)
	{
		_master = leader;
	}
	
	public void enableMinions(boolean b)
	{
		_enableMinions = b;
	}
	
	public boolean hasMinions()
	{
		return _minionList != null;
	}
	
	public MinionList getMinionList()
	{
		if (_minionList == null)
		{
			synchronized (this)
			{
				if (_minionList == null)
				{
					_minionList = new MinionList(this);
				}
			}
		}
		return _minionList;
	}
	
	@Override
	public boolean isMonster()
	{
		return true;
	}
	
	/**
	 * @return true if this L2MonsterInstance (or its master) is registered in WalkingManager
	 */
	@Override
	public boolean isWalker()
	{
		return ((_master == null) ? super.isWalker() : _master.isWalker());
	}
	
	/**
	 * @return {@code true} if this L2MonsterInstance is not raid minion, master state otherwise.
	 */
	@Override
	public boolean giveRaidCurse()
	{
		return (isRaidMinion() && (_master != null)) ? _master.giveRaidCurse() : super.giveRaidCurse();
	}
	
	@Override
	public void doCast(Skill skill, L2Character target, L2Object[] targets)
	{
		// Might need some exceptions here, but it will prevent the monster buffing player bug.
		if (!skill.isBad() && (getTarget() != null) && getTarget().isPlayer())
		{
			setIsCastingNow(false);
			setIsCastingSimultaneouslyNow(false);
			return;
		}
		super.doCast(skill, target, targets);
	}
}

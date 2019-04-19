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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.PetDataTable;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.CategoryType;
import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.model.L2PetData.L2PetSkillLearn;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jmobius.gameserver.model.effects.L2EffectType;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public final class L2BabyPetInstance extends L2PetInstance
{
	private static final int BUFF_CONTROL = 5771;
	private static final int AWAKENING = 5753;
	
	protected List<SkillHolder> _buffs = null;
	protected SkillHolder _majorHeal = null;
	protected SkillHolder _minorHeal = null;
	protected SkillHolder _recharge = null;
	
	private Future<?> _castTask;
	
	protected boolean _bufferMode = true;
	
	/**
	 * Creates a baby pet.
	 * @param template the baby pet NPC template
	 * @param owner the owner
	 * @param control the summoning item
	 */
	public L2BabyPetInstance(L2NpcTemplate template, L2PcInstance owner, L2ItemInstance control)
	{
		super(template, owner, control);
		setInstanceType(InstanceType.L2BabyPetInstance);
	}
	
	/**
	 * Creates a baby pet.
	 * @param template the baby pet NPC template
	 * @param owner the owner
	 * @param control the summoning item
	 * @param level the level
	 */
	public L2BabyPetInstance(L2NpcTemplate template, L2PcInstance owner, L2ItemInstance control, byte level)
	{
		super(template, owner, control, level);
		setInstanceType(InstanceType.L2BabyPetInstance);
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		
		double healPower = 0;
		for (L2PetSkillLearn psl : PetDataTable.getInstance().getPetData(getId()).getAvailableSkills())
		{
			final int id = psl.getSkillId();
			final int lvl = PetDataTable.getInstance().getPetData(getId()).getAvailableLevel(id, getLevel());
			if (lvl == 0)
			{
				continue;
			}
			
			final Skill skill = SkillData.getInstance().getSkill(id, lvl);
			if (skill == null)
			{
				continue;
			}
			
			if ((skill.getId() == BUFF_CONTROL) || (skill.getId() == AWAKENING))
			{
				continue;
			}
			
			if (skill.hasEffectType(L2EffectType.MANAHEAL_BY_LEVEL))
			{
				_recharge = new SkillHolder(skill);
				continue;
			}
			
			if (skill.hasEffectType(L2EffectType.HEAL))
			{
				if (healPower == 0)
				{
					// set both heal types to the same skill
					_majorHeal = new SkillHolder(skill);
					_minorHeal = _majorHeal;
					healPower = skill.getPower();
				}
				else
				{
					// another heal skill found - search for most powerful
					if (skill.getPower() > healPower)
					{
						_majorHeal = new SkillHolder(skill);
					}
					else
					{
						_minorHeal = new SkillHolder(skill);
					}
				}
				continue;
			}
			
			if (skill.isContinuous() && !skill.isDebuff())
			{
				if (_buffs == null)
				{
					_buffs = new ArrayList<>();
				}
				_buffs.add(new SkillHolder(skill));
			}
		}
		startCastTask();
	}
	
	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer))
		{
			return false;
		}
		stopCastTask();
		abortCast();
		return true;
	}
	
	@Override
	public synchronized void unSummon(L2PcInstance owner)
	{
		stopCastTask();
		abortCast();
		super.unSummon(owner);
	}
	
	@Override
	public void doRevive()
	{
		super.doRevive();
		startCastTask();
	}
	
	@Override
	public void onDecay()
	{
		super.onDecay();
		
		if (_buffs != null)
		{
			_buffs.clear();
		}
	}
	
	private final void startCastTask()
	{
		if ((_majorHeal != null) || (_buffs != null) || ((_recharge != null) && (_castTask == null) && !isDead()))
		{
			_castTask = ThreadPool.scheduleAtFixedRate(new CastTask(this), 3000, 2000);
		}
	}
	
	@Override
	public void switchMode()
	{
		_bufferMode = !_bufferMode;
	}
	
	/**
	 * Verify if this pet is in support mode.
	 * @return {@code true} if this baby pet is in support mode, {@code false} otherwise
	 */
	public boolean isInSupportMode()
	{
		return _bufferMode;
	}
	
	private final void stopCastTask()
	{
		if (_castTask != null)
		{
			_castTask.cancel(false);
			_castTask = null;
		}
	}
	
	protected void castSkill(Skill skill)
	{
		// casting automatically stops any other action (such as autofollow or a move-to).
		// We need to gather the necessary info to restore the previous state.
		final boolean previousFollowStatus = getFollowStatus();
		
		// pet not following and owner outside cast range
		if (!previousFollowStatus && !isInsideRadius3D(getOwner(), skill.getCastRange()))
		{
			return;
		}
		
		setTarget(getOwner());
		useMagic(skill, false, false);
		
		final SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.YOUR_PET_USES_S1);
		msg.addSkillName(skill);
		sendPacket(msg);
		
		// calling useMagic changes the follow status, if the babypet actually casts
		// (as opposed to failing due some factors, such as too low MP, etc).
		// if the status has actually been changed, revert it. Else, allow the pet to
		// continue whatever it was trying to do.
		// NOTE: This is important since the pet may have been told to attack a target.
		// reverting the follow status will abort this attack! While aborting the attack
		// in order to heal is natural, it is not acceptable to abort the attack on its own,
		// merely because the timer stroke and without taking any other action...
		if (previousFollowStatus != getFollowStatus())
		{
			setFollowStatus(previousFollowStatus);
		}
	}
	
	private class CastTask implements Runnable
	{
		private final L2BabyPetInstance _baby;
		private final List<Skill> _currentBuffs = new ArrayList<>();
		
		public CastTask(L2BabyPetInstance baby)
		{
			_baby = baby;
		}
		
		@Override
		public void run()
		{
			final L2PcInstance owner = _baby.getOwner();
			// If the owner doesn't meet the conditions avoid casting.
			if ((owner == null) || owner.isDead() || owner.isInvul())
			{
				return;
			}
			
			// If the pet doesn't meet the conditions avoid casting.
			if (_baby.isCastingNow() || _baby.isBetrayed() || _baby.isMuted() || _baby.isOutOfControl() || !_bufferMode || (_baby.getAI().getIntention() == CtrlIntention.AI_INTENTION_CAST))
			{
				return;
			}
			
			Skill skill = null;
			if (_majorHeal != null)
			{
				// If the owner's HP is more than 80% for Baby Pets and 70% for Improved Baby pets, do nothing.
				// If the owner's HP is very low, under 15% for Baby pets and under 30% for Improved Baby Pets, have 75% chances of using a strong heal.
				// Otherwise, have 25% chances for weak heal.
				final double hpPercent = owner.getCurrentHp() / owner.getMaxHp();
				final boolean isImprovedBaby = isInCategory(CategoryType.BABY_PET_GROUP);
				if ((isImprovedBaby && (hpPercent < 0.3)) || (!isImprovedBaby && (hpPercent < 0.15)))
				{
					skill = _majorHeal.getSkill();
					if (!_baby.isSkillDisabled(skill) && (Rnd.get(100) <= 75))
					{
						if (_baby.getCurrentMp() >= skill.getMpConsume())
						{
							castSkill(skill);
							return;
						}
					}
				}
				else if ((_majorHeal.getSkill() != _minorHeal.getSkill()) && ((isImprovedBaby && (hpPercent < 0.7)) || (!isImprovedBaby && (hpPercent < 0.8))))
				{
					// Cast _minorHeal only if it's different than _majorHeal, then pet has two heals available.
					skill = _minorHeal.getSkill();
					if (!_baby.isSkillDisabled(skill) && (Rnd.get(100) <= 25))
					{
						if (_baby.getCurrentMp() >= skill.getMpConsume())
						{
							castSkill(skill);
							return;
						}
					}
				}
			}
			
			// Buff Control is not active
			if (!_baby.isAffectedBySkill(BUFF_CONTROL))
			{
				// searching for usable buffs
				if ((_buffs != null) && !_buffs.isEmpty())
				{
					for (SkillHolder buff : _buffs)
					{
						skill = buff.getSkill();
						if (_baby.isSkillDisabled(skill))
						{
							continue;
						}
						
						if (_baby.getCurrentMp() < skill.getMpConsume())
						{
							continue;
						}
						
						// If owner already have the buff, continue.
						final BuffInfo buffInfo = owner.getEffectList().getBuffInfoByAbnormalType(skill.getAbnormalType());
						if ((buffInfo != null) && (skill.getAbnormalLvl() <= buffInfo.getSkill().getAbnormalLvl()))
						{
							continue;
						}
						
						// If owner have the buff blocked, continue.
						if ((owner.getEffectList().getAllBlockedBuffSlots() != null) && owner.getEffectList().getAllBlockedBuffSlots().contains(skill.getAbnormalType()))
						{
							continue;
						}
						_currentBuffs.add(skill);
					}
				}
				
				if (!_currentBuffs.isEmpty())
				{
					skill = _currentBuffs.get(Rnd.get(_currentBuffs.size()));
					castSkill(skill);
					_currentBuffs.clear();
					return;
				}
			}
			
			// buffs/heal not casted, trying recharge, if exist recharge casted only if owner in combat stance.
			if ((_recharge != null) && owner.isInCombat() && ((owner.getCurrentMp() / owner.getMaxMp()) < 0.6) && (Rnd.get(100) <= 60))
			{
				skill = _recharge.getSkill();
				if (!_baby.isSkillDisabled(skill) && (_baby.getCurrentMp() >= skill.getMpConsume()))
				{
					castSkill(skill);
				}
			}
		}
	}
}

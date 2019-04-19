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
package com.l2jmobius.gameserver.model.actor.tasks.cubics;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2CubicInstance;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;

/**
 * Cubic heal task.
 * @author Zoey76
 */
public class CubicHeal implements Runnable
{
	private static final Logger LOGGER = Logger.getLogger(CubicHeal.class.getName());
	private final L2CubicInstance _cubic;
	
	public CubicHeal(L2CubicInstance cubic)
	{
		_cubic = cubic;
	}
	
	@Override
	public void run()
	{
		if (_cubic == null)
		{
			return;
		}
		
		if (_cubic.getOwner().isDead() || !_cubic.getOwner().isOnline())
		{
			_cubic.stopAction();
			_cubic.getOwner().getCubics().remove(_cubic.getId());
			_cubic.getOwner().broadcastUserInfo();
			_cubic.cancelDisappear();
			return;
		}
		
		try
		{
			final Skill skill = _cubic.getSkills().stream().filter(s -> s.getId() == L2CubicInstance.SKILL_CUBIC_HEAL).findFirst().orElse(null);
			if (skill == null)
			{
				return;
			}
			
			_cubic.cubicTargetForHeal();
			final L2Character target = _cubic.getTarget();
			if ((target != null) && !target.isDead())
			{
				if ((target.getMaxHp() - target.getCurrentHp()) > skill.getPower())
				{
					skill.activateSkill(_cubic, target);
					
					_cubic.getOwner().broadcastPacket(new MagicSkillUse(_cubic.getOwner(), target, skill.getId(), skill.getLevel(), 0, 0));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "", e);
		}
	}
}
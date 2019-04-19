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
package handlers.targethandlers;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.gameserver.handler.ITargetTypeHandler;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PetInstance;
import com.l2jmobius.gameserver.model.effects.L2EffectType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.skills.targets.L2TargetType;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.SystemMessageId;

/**
 * @author UnAfraid
 */
public class PcBody implements ITargetTypeHandler
{
	@Override
	public L2Object[] getTargetList(Skill skill, L2Character activeChar, boolean onlyFirst, L2Character target)
	{
		final List<L2Character> targetList = new ArrayList<>();
		if ((target != null) && target.isDead())
		{
			final L2PcInstance player;
			if (activeChar.isPlayer())
			{
				player = activeChar.getActingPlayer();
			}
			else
			{
				player = null;
			}
			
			final L2PcInstance targetPlayer;
			if (target.isPlayer())
			{
				targetPlayer = target.getActingPlayer();
			}
			else
			{
				targetPlayer = null;
			}
			
			final L2PetInstance targetPet;
			if (target.isPet())
			{
				targetPet = (L2PetInstance) target;
			}
			else
			{
				targetPet = null;
			}
			
			if ((player != null) && ((targetPlayer != null) || (targetPet != null)))
			{
				boolean condGood = true;
				
				if (skill.hasEffectType(L2EffectType.RESURRECTION))
				{
					if (targetPlayer != null)
					{
						// check target is not in a active siege zone
						if (targetPlayer.isInsideZone(ZoneId.SIEGE) && !targetPlayer.isInSiege())
						{
							condGood = false;
							activeChar.sendPacket(SystemMessageId.IT_IS_NOT_POSSIBLE_TO_RESURRECT_IN_BATTLEFIELDS_WHERE_A_SIEGE_WAR_IS_TAKING_PLACE);
						}
						
						if (targetPlayer.isFestivalParticipant()) // Check to see if the current player target is in a festival.
						{
							condGood = false;
							activeChar.sendMessage("You may not resurrect participants in a festival.");
						}
					}
				}
				
				if (condGood)
				{
					if (!onlyFirst)
					{
						targetList.add(target);
						return targetList.toArray(new L2Object[targetList.size()]);
					}
					return new L2Character[]
					{
						target
					};
				}
			}
		}
		activeChar.sendPacket(SystemMessageId.THAT_IS_AN_INCORRECT_TARGET);
		return EMPTY_TARGET_LIST;
	}
	
	@Override
	public Enum<L2TargetType> getTargetType()
	{
		return L2TargetType.PC_BODY;
	}
}

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
import java.util.Comparator;
import java.util.List;

import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.handler.ITargetTypeHandler;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2SiegeFlagInstance;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.skills.targets.L2TargetType;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.SystemMessageId;

/**
 * @author Adry_85
 */
public class AreaFriendly implements ITargetTypeHandler
{
	@Override
	public L2Object[] getTargetList(Skill skill, L2Character activeChar, boolean onlyFirst, L2Character target)
	{
		final List<L2Character> targetList = new ArrayList<>();
		final L2PcInstance player = activeChar.getActingPlayer();
		
		if (!checkTarget(player, target) && (skill.getCastRange() >= 0))
		{
			player.sendPacket(SystemMessageId.THAT_IS_AN_INCORRECT_TARGET);
			return EMPTY_TARGET_LIST;
		}
		
		if (onlyFirst)
		{
			return new L2Character[]
			{
				target
			};
		}
		
		if (player.getActingPlayer().isInOlympiadMode())
		{
			return new L2Character[]
			{
				player
			};
		}
		targetList.add(target); // Add target to target list
		
		if (target != null)
		{
			final int maxTargets = skill.getAffectLimit();
			L2World.getInstance().forEachVisibleObjectInRange(target, L2Character.class, skill.getAffectRange(), obj ->
			{
				if (!checkTarget(player, obj) || (obj == activeChar))
				{
					return;
				}
				
				if ((maxTargets > 0) && (targetList.size() >= maxTargets))
				{
					return;
				}
				
				targetList.add(obj);
			});
		}
		
		if (targetList.isEmpty())
		{
			return EMPTY_TARGET_LIST;
		}
		return targetList.toArray(new L2Character[targetList.size()]);
	}
	
	private boolean checkTarget(L2PcInstance activeChar, L2Character target)
	{
		if ((target == null) || target.isAlikeDead() || target.isDoor() || (target instanceof L2SiegeFlagInstance) || target.isMonster())
		{
			return false;
		}
		
		if (!GeoEngine.getInstance().canSeeTarget(activeChar, target))
		{
			return false;
		}
		
		if (target.isPlayable())
		{
			final L2PcInstance targetPlayer = target.getActingPlayer();
			
			if (activeChar == targetPlayer)
			{
				return true;
			}
			
			if (targetPlayer.inObserverMode() || targetPlayer.isInOlympiadMode())
			{
				return false;
			}
			
			if (activeChar.isInDuelWith(target))
			{
				return false;
			}
			
			if (activeChar.isInPartyWith(target))
			{
				return true;
			}
			
			if (target.isInsideZone(ZoneId.PVP))
			{
				return false;
			}
			
			if (activeChar.isInClanWith(target) || activeChar.isInAllyWith(target) || activeChar.isInCommandChannelWith(target))
			{
				return true;
			}
			
			if ((targetPlayer.getPvpFlag() > 0) || (targetPlayer.getKarma() > 0))
			{
				return false;
			}
		}
		return true;
	}
	
	public class CharComparator implements Comparator<L2Character>
	{
		@Override
		public int compare(L2Character char1, L2Character char2)
		{
			return Double.compare((char1.getCurrentHp() / char1.getMaxHp()), (char2.getCurrentHp() / char2.getMaxHp()));
		}
	}
	
	@Override
	public Enum<L2TargetType> getTargetType()
	{
		return L2TargetType.AREA_FRIENDLY;
	}
}
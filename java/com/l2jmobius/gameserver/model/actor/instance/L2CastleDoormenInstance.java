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

import java.util.StringTokenizer;

import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.model.ClanPrivilege;
import com.l2jmobius.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jmobius.gameserver.model.entity.clanhall.SiegableHall;

public class L2CastleDoormenInstance extends L2DoormenInstance
{
	public L2CastleDoormenInstance(L2NpcTemplate template)
	{
		super(template);
		setInstanceType(InstanceType.L2CastleDoormenInstance);
	}
	
	@Override
	protected final void openDoors(L2PcInstance player, String command)
	{
		final StringTokenizer st = new StringTokenizer(command.substring(10), ", ");
		st.nextToken();
		
		while (st.hasMoreTokens())
		{
			if (getConquerableHall() != null)
			{
				getConquerableHall().openCloseDoor(Integer.parseInt(st.nextToken()), true);
			}
			else
			{
				getCastle().openDoor(player, Integer.parseInt(st.nextToken()));
			}
		}
	}
	
	@Override
	protected final void closeDoors(L2PcInstance player, String command)
	{
		final StringTokenizer st = new StringTokenizer(command.substring(11), ", ");
		st.nextToken();
		
		while (st.hasMoreTokens())
		{
			if (getConquerableHall() != null)
			{
				getConquerableHall().openCloseDoor(Integer.parseInt(st.nextToken()), false);
			}
			else
			{
				getCastle().closeDoor(player, Integer.parseInt(st.nextToken()));
			}
		}
	}
	
	@Override
	protected final boolean isOwnerClan(L2PcInstance player)
	{
		if ((player.getClan() != null) && player.hasClanPrivilege(ClanPrivilege.CS_OPEN_DOOR))
		{
			final SiegableHall hall = getConquerableHall();
			// save in variable because it's a costly call
			if (hall != null)
			{
				if (player.getClanId() == hall.getOwnerId())
				{
					return true;
				}
			}
			else if (getCastle() != null)
			{
				if (player.getClanId() == getCastle().getOwnerId())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	protected final boolean isUnderSiege()
	{
		final SiegableHall hall = getConquerableHall();
		if (hall != null)
		{
			return hall.isInSiege();
		}
		return getCastle().getZone().isActive();
	}
}

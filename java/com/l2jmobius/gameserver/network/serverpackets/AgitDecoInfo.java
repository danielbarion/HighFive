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
package com.l2jmobius.gameserver.network.serverpackets;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.model.entity.ClanHall;
import com.l2jmobius.gameserver.model.entity.ClanHall.ClanHallFunction;
import com.l2jmobius.gameserver.model.entity.clanhall.AuctionableHall;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Steuf
 */
public class AgitDecoInfo implements IClientOutgoingPacket
{
	private final AuctionableHall _clanHall;
	
	public AgitDecoInfo(AuctionableHall ClanHall)
	{
		_clanHall = ClanHall;
	}
	
	//@formatter:off
	/*
	 * Packet send, must be confirmed
	 	packet.writeC(0xf7);
		packet.writeD(0); // clanhall id
		packet.writeC(0); // FUNC_RESTORE_HP (Fireplace)
		packet.writeC(0); // FUNC_RESTORE_MP (Carpet)
		packet.writeC(0); // FUNC_RESTORE_MP (Statue)
		packet.writeC(0); // FUNC_RESTORE_EXP (Chandelier)
		packet.writeC(0); // FUNC_TELEPORT (Mirror)
		packet.writeC(0); // Crytal
		packet.writeC(0); // Curtain
		packet.writeC(0); // FUNC_ITEM_CREATE (Magic Curtain)
		packet.writeC(0); // FUNC_SUPPORT
		packet.writeC(0); // FUNC_SUPPORT (Flag)
		packet.writeC(0); // Front Platform
		packet.writeC(0); // FUNC_ITEM_CREATE
		packet.writeD(0);
		packet.writeD(0);
	 */
	//@formatter:on
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.AGIT_DECO_INFO.writeId(packet);
		packet.writeD(_clanHall.getId()); // clanhall id
		// FUNC_RESTORE_HP
		ClanHallFunction function = _clanHall.getFunction(ClanHall.FUNC_RESTORE_HP);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
		}
		else if (((_clanHall.getGrade() == 0) && (function.getLvl() < 220)) || ((_clanHall.getGrade() == 1) && (function.getLvl() < 160)) || ((_clanHall.getGrade() == 2) && (function.getLvl() < 260)) || ((_clanHall.getGrade() == 3) && (function.getLvl() < 300)))
		{
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
		}
		// FUNC_RESTORE_MP
		function = _clanHall.getFunction(ClanHall.FUNC_RESTORE_MP);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
			packet.writeC(0);
		}
		else if ((((_clanHall.getGrade() == 0) || (_clanHall.getGrade() == 1)) && (function.getLvl() < 25)) || ((_clanHall.getGrade() == 2) && (function.getLvl() < 30)) || ((_clanHall.getGrade() == 3) && (function.getLvl() < 40)))
		{
			packet.writeC(1);
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
			packet.writeC(2);
		}
		// FUNC_RESTORE_EXP
		function = _clanHall.getFunction(ClanHall.FUNC_RESTORE_EXP);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
		}
		else if (((_clanHall.getGrade() == 0) && (function.getLvl() < 25)) || ((_clanHall.getGrade() == 1) && (function.getLvl() < 30)) || ((_clanHall.getGrade() == 2) && (function.getLvl() < 40)) || ((_clanHall.getGrade() == 3) && (function.getLvl() < 50)))
		{
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
		}
		// FUNC_TELEPORT
		function = _clanHall.getFunction(ClanHall.FUNC_TELEPORT);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
		}
		else if (function.getLvl() < 2)
		{
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
		}
		packet.writeC(0);
		// CURTAINS
		function = _clanHall.getFunction(ClanHall.FUNC_DECO_CURTAINS);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
		}
		else if (function.getLvl() <= 1)
		{
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
		}
		// FUNC_ITEM_CREATE
		function = _clanHall.getFunction(ClanHall.FUNC_ITEM_CREATE);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
		}
		else if (((_clanHall.getGrade() == 0) && (function.getLvl() < 2)) || (function.getLvl() < 3))
		{
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
		}
		// FUNC_SUPPORT
		function = _clanHall.getFunction(ClanHall.FUNC_SUPPORT);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
			packet.writeC(0);
		}
		else if (((_clanHall.getGrade() == 0) && (function.getLvl() < 2)) || ((_clanHall.getGrade() == 1) && (function.getLvl() < 4)) || ((_clanHall.getGrade() == 2) && (function.getLvl() < 5)) || ((_clanHall.getGrade() == 3) && (function.getLvl() < 8)))
		{
			packet.writeC(1);
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
			packet.writeC(2);
		}
		// Front Plateform
		function = _clanHall.getFunction(ClanHall.FUNC_DECO_FRONTPLATEFORM);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
		}
		else if (function.getLvl() <= 1)
		{
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
		}
		// FUNC_ITEM_CREATE
		function = _clanHall.getFunction(ClanHall.FUNC_ITEM_CREATE);
		if ((function == null) || (function.getLvl() == 0))
		{
			packet.writeC(0);
		}
		else if (((_clanHall.getGrade() == 0) && (function.getLvl() < 2)) || (function.getLvl() < 3))
		{
			packet.writeC(1);
		}
		else
		{
			packet.writeC(2);
		}
		packet.writeD(0);
		packet.writeD(0);
		return true;
	}
}

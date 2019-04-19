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

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.L2Henna;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * This server packet sends the player's henna information using the Game Master's UI.
 * @author KenM, Zoey76
 */
public final class GMHennaInfo implements IClientOutgoingPacket
{
	private final L2PcInstance _activeChar;
	private final List<L2Henna> _hennas = new ArrayList<>();
	
	public GMHennaInfo(L2PcInstance player)
	{
		_activeChar = player;
		for (L2Henna henna : _activeChar.getHennaList())
		{
			if (henna != null)
			{
				_hennas.add(henna);
			}
		}
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.GM_HENNA_INFO.writeId(packet);
		packet.writeC(_activeChar.getHennaStatINT()); // equip INT
		packet.writeC(_activeChar.getHennaStatSTR()); // equip STR
		packet.writeC(_activeChar.getHennaStatCON()); // equip CON
		packet.writeC(_activeChar.getHennaStatMEN()); // equip MEN
		packet.writeC(_activeChar.getHennaStatDEX()); // equip DEX
		packet.writeC(_activeChar.getHennaStatWIT()); // equip WIT
		packet.writeD(3); // Slots
		packet.writeD(_hennas.size()); // Size
		for (L2Henna henna : _hennas)
		{
			packet.writeD(henna.getDyeId());
			packet.writeD(0x01);
		}
		return true;
	}
}

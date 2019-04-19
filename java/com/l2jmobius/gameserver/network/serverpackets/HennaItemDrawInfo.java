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
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.L2Henna;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Zoey76
 */
public class HennaItemDrawInfo implements IClientOutgoingPacket
{
	private final L2PcInstance _activeChar;
	private final L2Henna _henna;
	
	public HennaItemDrawInfo(L2Henna henna, L2PcInstance player)
	{
		_henna = henna;
		_activeChar = player;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.HENNA_ITEM_INFO.writeId(packet);
		packet.writeD(_henna.getDyeId()); // symbol Id
		packet.writeD(_henna.getDyeItemId()); // item id of dye
		packet.writeQ(_henna.getWearCount()); // total amount of dye require
		packet.writeQ(_henna.getWearFee()); // total amount of Adena require to draw symbol
		packet.writeD(_henna.isAllowedClass(_activeChar.getClassId()) ? 0x01 : 0x00); // able to draw or not 0 is false and 1 is true
		packet.writeQ(_activeChar.getAdena());
		packet.writeD(_activeChar.getINT()); // current INT
		packet.writeC(_activeChar.getINT() + _henna.getStatINT()); // equip INT
		packet.writeD(_activeChar.getSTR()); // current STR
		packet.writeC(_activeChar.getSTR() + _henna.getStatSTR()); // equip STR
		packet.writeD(_activeChar.getCON()); // current CON
		packet.writeC(_activeChar.getCON() + _henna.getStatCON()); // equip CON
		packet.writeD(_activeChar.getMEN()); // current MEN
		packet.writeC(_activeChar.getMEN() + _henna.getStatMEN()); // equip MEN
		packet.writeD(_activeChar.getDEX()); // current DEX
		packet.writeC(_activeChar.getDEX() + _henna.getStatDEX()); // equip DEX
		packet.writeD(_activeChar.getWIT()); // current WIT
		packet.writeC(_activeChar.getWIT() + _henna.getStatWIT()); // equip WIT
		return true;
	}
}

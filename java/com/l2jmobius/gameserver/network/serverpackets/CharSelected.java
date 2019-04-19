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
import com.l2jmobius.gameserver.GameTimeController;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class CharSelected implements IClientOutgoingPacket
{
	private final L2PcInstance _activeChar;
	private final int _sessionId;
	
	/**
	 * @param cha
	 * @param sessionId
	 */
	public CharSelected(L2PcInstance cha, int sessionId)
	{
		_activeChar = cha;
		_sessionId = sessionId;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.CHARACTER_SELECTED.writeId(packet);
		
		packet.writeS(_activeChar.getName());
		packet.writeD(_activeChar.getObjectId());
		packet.writeS(_activeChar.getTitle());
		packet.writeD(_sessionId);
		packet.writeD(_activeChar.getClanId());
		packet.writeD(0x00); // ??
		packet.writeD(_activeChar.getAppearance().getSex() ? 1 : 0);
		packet.writeD(_activeChar.getRace().ordinal());
		packet.writeD(_activeChar.getClassId().getId());
		packet.writeD(0x01); // active ??
		packet.writeD(_activeChar.getX());
		packet.writeD(_activeChar.getY());
		packet.writeD(_activeChar.getZ());
		
		packet.writeF(_activeChar.getCurrentHp());
		packet.writeF(_activeChar.getCurrentMp());
		packet.writeD((int) _activeChar.getSp());
		packet.writeQ(_activeChar.getExp());
		packet.writeD(_activeChar.getLevel());
		packet.writeD(_activeChar.getKarma()); // thx evill33t
		packet.writeD(_activeChar.getPkKills());
		packet.writeD(_activeChar.getINT());
		packet.writeD(_activeChar.getSTR());
		packet.writeD(_activeChar.getCON());
		packet.writeD(_activeChar.getMEN());
		packet.writeD(_activeChar.getDEX());
		packet.writeD(_activeChar.getWIT());
		
		packet.writeD(GameTimeController.getInstance().getGameTime() % (24 * 60)); // "reset" on 24th hour
		packet.writeD(0x00);
		
		packet.writeD(_activeChar.getClassId().getId());
		
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		
		packet.writeB(new byte[64]);
		packet.writeD(0x00);
		return true;
	}
}

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
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class CharDeleteFail implements IClientOutgoingPacket
{
	public static final int REASON_DELETION_FAILED = 0x01;
	public static final int REASON_YOU_MAY_NOT_DELETE_CLAN_MEMBER = 0x02;
	public static final int REASON_CLAN_LEADERS_MAY_NOT_BE_DELETED = 0x03;
	
	private final int _error;
	
	public CharDeleteFail(int errorCode)
	{
		_error = errorCode;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.CHARACTER_DELETE_FAIL.writeId(packet);
		packet.writeD(_error);
		return true;
	}
}

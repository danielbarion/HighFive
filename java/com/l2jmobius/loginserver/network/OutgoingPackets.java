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
package com.l2jmobius.loginserver.network;

import com.l2jmobius.commons.network.PacketWriter;

/**
 * @author Mobius
 */
public enum OutgoingPackets
{
	INIT(0x00),
	LOGIN_FAIL(0x01),
	ACCOUNT_KICKED(0x02),
	LOGIN_OK(0x03),
	SERVER_LIST(0x04),
	PLAY_FAIL(0x06),
	PLAY_OK(0x07),
	
	PI_AGREEMENT_CHECK(0x11),
	PI_AGREEMENT_ACK(0x12),
	GG_AUTH(0x0b),
	LOGIN_OPT_FAIL(0x0D);
	
	private final int _id1;
	private final int _id2;
	
	OutgoingPackets(int id1)
	{
		this(id1, -1);
	}
	
	OutgoingPackets(int id1, int id2)
	{
		_id1 = id1;
		_id2 = id2;
	}
	
	public int getId1()
	{
		return _id1;
	}
	
	public int getId2()
	{
		return _id2;
	}
	
	public void writeId(PacketWriter packet)
	{
		packet.writeC(_id1);
		if (_id2 > 0)
		{
			packet.writeH(_id2);
		}
	}
	
	public static OutgoingPackets getPacket(int id1, int id2)
	{
		for (OutgoingPackets packet : values())
		{
			if ((packet.getId1() == id1) && (packet.getId2() == id2))
			{
				return packet;
			}
		}
		return null;
	}
}

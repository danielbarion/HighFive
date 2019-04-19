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
package com.l2jmobius.loginserver.network.serverpackets;

import com.l2jmobius.commons.network.IOutgoingPacket;
import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.loginserver.SessionKey;
import com.l2jmobius.loginserver.network.OutgoingPackets;

/**
 * <pre>
 * Format: dddddddd
 * f: the session key
 * d: ?
 * d: ?
 * d: ?
 * d: ?
 * d: ?
 * d: ?
 * b: 16 bytes - unknown
 * </pre>
 */
public final class LoginOk implements IOutgoingPacket
{
	private final int _loginOk1;
	private final int _loginOk2;
	
	public LoginOk(SessionKey sessionKey)
	{
		_loginOk1 = sessionKey.loginOkID1;
		_loginOk2 = sessionKey.loginOkID2;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.LOGIN_OK.writeId(packet);
		packet.writeD(_loginOk1);
		packet.writeD(_loginOk2);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x000003ea);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeB(new byte[16]);
		return true;
	}
}

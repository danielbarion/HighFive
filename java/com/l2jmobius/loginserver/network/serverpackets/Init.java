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
import com.l2jmobius.loginserver.network.OutgoingPackets;

/**
 * <pre>
 * Format: dd b dddd s
 * d: session id
 * d: protocol revision
 * b: 0x90 bytes : 0x80 bytes for the scrambled RSA public key
 *                 0x10 bytes at 0x00
 * d: unknow
 * d: unknow
 * d: unknow
 * d: unknow
 * s: blowfish key
 * </pre>
 */
public final class Init implements IOutgoingPacket
{
	private final int _sessionId;
	
	private final byte[] _publicKey;
	private final byte[] _blowfishKey;
	
	public Init(byte[] publickey, byte[] blowfishkey, int sessionId)
	{
		_sessionId = sessionId;
		_publicKey = publickey;
		_blowfishKey = blowfishkey;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.INIT.writeId(packet);
		
		packet.writeD(_sessionId); // session id
		packet.writeD(0x0000c621); // protocol revision
		
		packet.writeB(_publicKey); // RSA Public Key
		
		// unk GG related?
		packet.writeD(0x29DD954E);
		packet.writeD(0x77C39CFC);
		packet.writeD(0x97ADB620);
		packet.writeD(0x07BDE0F7);
		
		packet.writeB(_blowfishKey); // BlowFish key
		packet.writeC(0x00); // null termination ;)
		
		return true;
	}
}

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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;

/**
 * This class is made to create packets with any format
 * @author Maktakien
 */
public class AdminForgePacket implements IClientOutgoingPacket
{
	private final List<Part> _parts = new ArrayList<>();
	
	private static class Part
	{
		public byte b;
		public String str;
		
		public Part(byte bb, String string)
		{
			b = bb;
			str = string;
		}
	}
	
	public AdminForgePacket()
	{
		
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		for (Part p : _parts)
		{
			generate(packet, p.b, p.str);
		}
		return true;
	}
	
	public boolean generate(PacketWriter packet, byte b, String string)
	{
		if ((b == 'C') || (b == 'c'))
		{
			packet.writeC(Integer.decode(string));
			return true;
		}
		else if ((b == 'D') || (b == 'd'))
		{
			packet.writeD(Integer.decode(string));
			return true;
		}
		else if ((b == 'H') || (b == 'h'))
		{
			packet.writeH(Integer.decode(string));
			return true;
		}
		else if ((b == 'F') || (b == 'f'))
		{
			packet.writeF(Double.parseDouble(string));
			return true;
		}
		else if ((b == 'S') || (b == 's'))
		{
			packet.writeS(string);
			return true;
		}
		else if ((b == 'B') || (b == 'b') || (b == 'X') || (b == 'x'))
		{
			packet.writeB(new BigInteger(string).toByteArray());
			return true;
		}
		else if ((b == 'Q') || (b == 'q'))
		{
			packet.writeQ(Long.decode(string));
			return true;
		}
		return false;
	}
	
	public void addPart(byte b, String string)
	{
		_parts.add(new Part(b, string));
	}
}
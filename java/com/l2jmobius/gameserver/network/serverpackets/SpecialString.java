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

public final class SpecialString implements IClientOutgoingPacket
{
	private final int _strId;
	private final int _fontSize;
	private final int _x;
	private final int _y;
	private final int _color;
	private final boolean _isDraw;
	private final String _text;
	
	public SpecialString(int strId, boolean isDraw, int fontSize, int x, int y, int color, String text)
	{
		_strId = strId;
		_isDraw = isDraw;
		_fontSize = fontSize;
		_x = x;
		_y = y;
		_color = color;
		_text = text;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.SERVER_CLOSE_SOCKET.writeId(packet);
		packet.writeC(_strId); // string ID
		packet.writeC(_isDraw ? 1 : 0);// 1 - draw / 0 - hide
		packet.writeC(_fontSize); // -1 to 3 (font size)
		packet.writeD(_x); // ClientRight - x
		packet.writeD(_y); // ClientTop + y
		packet.writeD(_color); // AARRGGBB
		packet.writeS(_text); // wide string max len = 63
		return true;
	}
}
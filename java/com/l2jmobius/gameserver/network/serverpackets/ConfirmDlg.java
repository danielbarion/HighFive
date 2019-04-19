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
import com.l2jmobius.gameserver.network.SystemMessageId;

/**
 * ConfirmDlg server packet implementation.
 * @author kombat, UnAfraid
 */
public class ConfirmDlg extends AbstractMessagePacket<ConfirmDlg>
{
	private int _time;
	private int _requesterId;
	
	public ConfirmDlg(SystemMessageId smId)
	{
		super(smId);
	}
	
	public ConfirmDlg(int id)
	{
		this(SystemMessageId.getSystemMessageId(id));
	}
	
	public ConfirmDlg(String text)
	{
		this(SystemMessageId.S1_3);
		addString(text);
	}
	
	public ConfirmDlg addTime(int time)
	{
		_time = time;
		return this;
	}
	
	public ConfirmDlg addRequesterId(int id)
	{
		_requesterId = id;
		return this;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.CONFIRM_DLG.writeId(packet);
		writeMe(packet);
		packet.writeD(_time);
		packet.writeD(_requesterId);
		return true;
	}
}

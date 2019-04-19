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
import com.l2jmobius.gameserver.model.PartyMatchRoom;
import com.l2jmobius.gameserver.model.PartyMatchRoomList;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Gnacik
 */
public class ListPartyWating implements IClientOutgoingPacket
{
	private final L2PcInstance _cha;
	private final int _loc;
	private final int _lim;
	private final List<PartyMatchRoom> _rooms;
	
	public ListPartyWating(L2PcInstance player, int auto, int location, int limit)
	{
		_cha = player;
		_loc = location;
		_lim = limit;
		_rooms = new ArrayList<>();
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		for (PartyMatchRoom room : PartyMatchRoomList.getInstance().getRooms())
		{
			if ((room.getMembers() < 1) || (room.getOwner() == null) || !room.getOwner().isOnline() || (room.getOwner().getPartyRoom() != room.getId()))
			{
				PartyMatchRoomList.getInstance().deleteRoom(room.getId());
				continue;
			}
			if ((_loc > 0) && (_loc != room.getLocation()))
			{
				continue;
			}
			if ((_lim == 0) && ((_cha.getLevel() < room.getMinLvl()) || (_cha.getLevel() > room.getMaxLvl())))
			{
				continue;
			}
			_rooms.add(room);
		}
		final int size = _rooms.size();
		
		OutgoingPackets.LIST_PARTY_WAITING.writeId(packet);
		if (size > 0)
		{
			packet.writeD(0x01);
		}
		else
		{
			packet.writeD(0x00);
		}
		
		packet.writeD(_rooms.size());
		for (PartyMatchRoom room : _rooms)
		{
			packet.writeD(room.getId());
			packet.writeS(room.getTitle());
			packet.writeD(room.getLocation());
			packet.writeD(room.getMinLvl());
			packet.writeD(room.getMaxLvl());
			packet.writeD(room.getMaxMembers());
			packet.writeS(room.getOwner().getName());
			packet.writeD(room.getMembers());
			for (L2PcInstance member : room.getPartyMembers())
			{
				if (member != null)
				{
					packet.writeD(member.getClassId().getId());
					packet.writeS(member.getName());
				}
				else
				{
					packet.writeD(0x00);
					packet.writeS("Not Found");
				}
			}
		}
		return true;
	}
}

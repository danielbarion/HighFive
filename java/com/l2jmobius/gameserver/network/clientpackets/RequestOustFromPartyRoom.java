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
package com.l2jmobius.gameserver.network.clientpackets;

import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.PartyMatchRoom;
import com.l2jmobius.gameserver.model.PartyMatchRoomList;
import com.l2jmobius.gameserver.model.PartyMatchWaitingList;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.L2GameClient;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ExClosePartyRoom;
import com.l2jmobius.gameserver.network.serverpackets.ListPartyWating;

/**
 * format (ch) d
 * @author -Wooden-
 */
public final class RequestOustFromPartyRoom implements IClientIncomingPacket
{
	private int _charid;
	
	@Override
	public boolean read(L2GameClient client, PacketReader packet)
	{
		_charid = packet.readD();
		return true;
	}
	
	@Override
	public void run(L2GameClient client)
	{
		final L2PcInstance player = client.getActiveChar();
		if (player == null)
		{
			return;
		}
		
		final L2PcInstance member = L2World.getInstance().getPlayer(_charid);
		if (member == null)
		{
			return;
		}
		
		final PartyMatchRoom room = PartyMatchRoomList.getInstance().getPlayerRoom(member);
		if ((room == null) || (room.getOwner() != player))
		{
			return;
		}
		
		if (player.isInParty() && member.isInParty() && (player.getParty().getLeaderObjectId() == member.getParty().getLeaderObjectId()))
		{
			player.sendPacket(SystemMessageId.YOU_CANNOT_DISMISS_A_PARTY_MEMBER_BY_FORCE);
		}
		else
		{
			// Remove member from party room
			room.deleteMember(member);
			member.setPartyRoom(0);
			
			// Close the PartyRoom window
			member.sendPacket(new ExClosePartyRoom());
			
			// Add player back on waiting list
			PartyMatchWaitingList.getInstance().addPlayer(member);
			
			// Send Room list
			final int loc = 0; // TODO: Closes town
			member.sendPacket(new ListPartyWating(member, 0, loc, member.getLevel()));
			
			// Clean player's LFP title
			member.broadcastUserInfo();
			member.sendPacket(SystemMessageId.YOU_HAVE_BEEN_OUSTED_FROM_THE_PARTY_ROOM);
		}
	}
}

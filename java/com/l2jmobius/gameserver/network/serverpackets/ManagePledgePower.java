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
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class ManagePledgePower implements IClientOutgoingPacket
{
	
	private final int _action;
	private final L2Clan _clan;
	private final int _rank;
	
	public ManagePledgePower(L2Clan clan, int action, int rank)
	{
		_clan = clan;
		_action = action;
		_rank = rank;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		if (_action == 1)
		{
			OutgoingPackets.MANAGE_PLEDGE_POWER.writeId(packet);
			packet.writeD(0);
			packet.writeD(0);
			packet.writeD(_clan.getRankPrivs(_rank).getBitmask());
		}
		return true;
	}
}

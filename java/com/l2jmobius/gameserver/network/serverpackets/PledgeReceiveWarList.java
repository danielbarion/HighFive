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
import com.l2jmobius.gameserver.data.sql.impl.ClanTable;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author -Wooden-
 */
public class PledgeReceiveWarList implements IClientOutgoingPacket
{
	private final L2Clan _clan;
	private final int _tab;
	
	public PledgeReceiveWarList(L2Clan clan, int tab)
	{
		_clan = clan;
		_tab = tab;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.PLEDGE_RECEIVE_WAR_LIST.writeId(packet);
		
		packet.writeD(_tab); // type : 0 = Declared, 1 = Under Attack
		packet.writeD(0x00); // page
		packet.writeD(_tab == 0 ? _clan.getWarList().size() : _clan.getAttackerList().size());
		for (Integer i : _tab == 0 ? _clan.getWarList() : _clan.getAttackerList())
		{
			final L2Clan clan = ClanTable.getInstance().getClan(i);
			if (clan == null)
			{
				continue;
			}
			
			packet.writeS(clan.getName());
			packet.writeD(_tab); // ??
			packet.writeD(_tab); // ??
		}
		return true;
	}
}

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

import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager;
import com.l2jmobius.gameserver.model.TerritoryWard;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author -Gigiikun-
 */
public class ExShowOwnthingPos implements IClientOutgoingPacket
{
	public static final ExShowOwnthingPos STATIC_PACKET = new ExShowOwnthingPos();
	
	private ExShowOwnthingPos()
	{
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_SHOW_OWNTHING_POS.writeId(packet);
		
		if (TerritoryWarManager.getInstance().isTWInProgress())
		{
			final List<TerritoryWard> territoryWardList = TerritoryWarManager.getInstance().getAllTerritoryWards();
			packet.writeD(territoryWardList.size());
			for (TerritoryWard ward : territoryWardList)
			{
				packet.writeD(ward.getTerritoryId());
				
				if (ward.getNpc() != null)
				{
					packet.writeD(ward.getNpc().getX());
					packet.writeD(ward.getNpc().getY());
					packet.writeD(ward.getNpc().getZ());
				}
				else if (ward.getPlayer() != null)
				{
					packet.writeD(ward.getPlayer().getX());
					packet.writeD(ward.getPlayer().getY());
					packet.writeD(ward.getPlayer().getZ());
				}
				else
				{
					packet.writeD(0x00);
					packet.writeD(0x00);
					packet.writeD(0x00);
				}
			}
		}
		else
		{
			packet.writeD(0x00);
		}
		return true;
	}
}

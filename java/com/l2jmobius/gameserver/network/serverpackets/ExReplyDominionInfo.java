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
import com.l2jmobius.gameserver.instancemanager.CastleManager;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager.Territory;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author JIV
 */
public class ExReplyDominionInfo implements IClientOutgoingPacket
{
	public static final ExReplyDominionInfo STATIC_PACKET = new ExReplyDominionInfo();
	
	private ExReplyDominionInfo()
	{
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_REPLY_DOMINION_INFO.writeId(packet);
		final List<Territory> territoryList = TerritoryWarManager.getInstance().getAllTerritories();
		packet.writeD(territoryList.size()); // Territory Count
		for (Territory t : territoryList)
		{
			packet.writeD(t.getTerritoryId()); // Territory Id
			packet.writeS(CastleManager.getInstance().getCastleById(t.getCastleId()).getName().toLowerCase() + "_dominion"); // territory name
			packet.writeS(t.getOwnerClan().getName());
			packet.writeD(t.getOwnedWardIds().size()); // Emblem Count
			for (int i : t.getOwnedWardIds())
			{
				packet.writeD(i); // Emblem ID - should be in for loop for emblem count
			}
			packet.writeD((int) (TerritoryWarManager.getInstance().getTWStartTimeInMillis() / 1000));
		}
		return true;
	}
}

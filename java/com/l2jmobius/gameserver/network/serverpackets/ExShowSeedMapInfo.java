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
import com.l2jmobius.gameserver.instancemanager.SoDManager;
import com.l2jmobius.gameserver.instancemanager.SoIManager;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class ExShowSeedMapInfo implements IClientOutgoingPacket
{
	public static final ExShowSeedMapInfo STATIC_PACKET = new ExShowSeedMapInfo();
	
	private static final Location[] ENTRANCES =
	{
		new Location(-246857, 251960, 4331, 1),
		new Location(-213770, 210760, 4400, 2),
	};
	
	private ExShowSeedMapInfo()
	{
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_SHOW_SEED_MAP_INFO.writeId(packet);
		
		packet.writeD(ENTRANCES.length);
		for (Location loc : ENTRANCES)
		{
			packet.writeD(loc.getX());
			packet.writeD(loc.getY());
			packet.writeD(loc.getZ());
			switch (loc.getHeading())
			{
				case 1: // Seed of Destruction
				{
					packet.writeD(2770 + SoDManager.getInstance().getSoDState());
					break;
				}
				case 2: // Seed of Immortality
				{
					packet.writeD(SoIManager.getCurrentStage() + 2765);
					break;
				}
			}
		}
		return true;
	}
}

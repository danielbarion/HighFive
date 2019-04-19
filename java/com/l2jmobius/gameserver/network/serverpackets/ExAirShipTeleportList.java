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
import com.l2jmobius.gameserver.model.VehiclePathPoint;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class ExAirShipTeleportList implements IClientOutgoingPacket
{
	private final int _dockId;
	private final VehiclePathPoint[][] _teleports;
	private final int[] _fuelConsumption;
	
	public ExAirShipTeleportList(int dockId, VehiclePathPoint[][] teleports, int[] fuelConsumption)
	{
		_dockId = dockId;
		_teleports = teleports;
		_fuelConsumption = fuelConsumption;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_AIRSHIP_TELEPORT_LIST.writeId(packet);
		packet.writeD(_dockId);
		if (_teleports != null)
		{
			packet.writeD(_teleports.length);
			
			VehiclePathPoint[] path;
			VehiclePathPoint dst;
			for (int i = 0; i < _teleports.length; i++)
			{
				packet.writeD(i - 1);
				packet.writeD(_fuelConsumption[i]);
				path = _teleports[i];
				dst = path[path.length - 1];
				packet.writeD(dst.getX());
				packet.writeD(dst.getY());
				packet.writeD(dst.getZ());
			}
		}
		else
		{
			packet.writeD(0);
		}
		return true;
	}
}
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

import java.util.Comparator;
import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.instancemanager.CastleManager;
import com.l2jmobius.gameserver.model.entity.Castle;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author l3x
 */
public final class ExSendManorList implements IClientOutgoingPacket
{
	@Override
	public boolean write(PacketWriter packet)
	{
		final List<Castle> castles = CastleManager.getInstance().getCastles();
		castles.sort(Comparator.comparing(Castle::getResidenceId));
		
		OutgoingPackets.EX_SEND_MANOR_LIST.writeId(packet);
		packet.writeD(castles.size());
		for (Castle castle : castles)
		{
			packet.writeD(castle.getResidenceId());
			packet.writeS(castle.getName().toLowerCase());
		}
		return true;
	}
}
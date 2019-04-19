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
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public final class PartySmallWindowAll implements IClientOutgoingPacket
{
	private final L2Party _party;
	private final L2PcInstance _exclude;
	
	public PartySmallWindowAll(L2PcInstance exclude, L2Party party)
	{
		_exclude = exclude;
		_party = party;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.PARTY_SMALL_WINDOW_ALL.writeId(packet);
		packet.writeD(_party.getLeaderObjectId());
		packet.writeD(_party.getDistributionType().getId());
		packet.writeD(_party.getMemberCount() - 1);
		
		for (L2PcInstance member : _party.getMembers())
		{
			if ((member != null) && (member != _exclude))
			{
				packet.writeD(member.getObjectId());
				packet.writeS(member.getName());
				
				packet.writeD((int) member.getCurrentCp()); // c4
				packet.writeD(member.getMaxCp()); // c4
				
				packet.writeD((int) member.getCurrentHp());
				packet.writeD(member.getMaxHp());
				packet.writeD((int) member.getCurrentMp());
				packet.writeD(member.getMaxMp());
				packet.writeD(member.getLevel());
				packet.writeD(member.getClassId().getId());
				packet.writeD(0x00);// packet.writeD(0x01); ??
				packet.writeD(member.getRace().ordinal());
				packet.writeD(0x00); // T2.3
				packet.writeD(0x00); // T2.3
				if (member.hasSummon())
				{
					packet.writeD(member.getSummon().getObjectId());
					packet.writeD(member.getSummon().getId() + 1000000);
					packet.writeD(member.getSummon().getSummonType());
					packet.writeS(member.getSummon().getName());
					packet.writeD((int) member.getSummon().getCurrentHp());
					packet.writeD(member.getSummon().getMaxHp());
					packet.writeD((int) member.getSummon().getCurrentMp());
					packet.writeD(member.getSummon().getMaxMp());
					packet.writeD(member.getSummon().getLevel());
				}
				else
				{
					packet.writeD(0x00);
				}
			}
		}
		return true;
	}
}

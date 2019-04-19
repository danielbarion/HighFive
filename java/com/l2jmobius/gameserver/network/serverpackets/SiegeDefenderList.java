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
import com.l2jmobius.gameserver.model.L2SiegeClan;
import com.l2jmobius.gameserver.model.entity.Castle;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * Populates the Siege Defender List in the SiegeInfo Window<BR>
 * <BR>
 * c = 0xcb<BR>
 * d = CastleID<BR>
 * d = unknow (0x00)<BR>
 * d = unknow (0x01)<BR>
 * d = unknow (0x00)<BR>
 * d = Number of Defending Clans?<BR>
 * d = Number of Defending Clans<BR>
 * { //repeats<BR>
 * d = ClanID<BR>
 * S = ClanName<BR>
 * S = ClanLeaderName<BR>
 * d = ClanCrestID<BR>
 * d = signed time (seconds)<BR>
 * d = Type -> Owner = 0x01 || Waiting = 0x02 || Accepted = 0x03<BR>
 * d = AllyID<BR>
 * S = AllyName<BR>
 * S = AllyLeaderName<BR>
 * d = AllyCrestID<BR>
 * @author KenM
 */
public final class SiegeDefenderList implements IClientOutgoingPacket
{
	private final Castle _castle;
	
	public SiegeDefenderList(Castle castle)
	{
		_castle = castle;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.CASTLE_SIEGE_DEFENDER_LIST.writeId(packet);
		packet.writeD(_castle.getResidenceId());
		packet.writeD(0x00); // 0
		packet.writeD(0x01); // 1
		packet.writeD(0x00); // 0
		final int size = _castle.getSiege().getDefenderClans().size() + _castle.getSiege().getDefenderWaitingClans().size();
		if (size > 0)
		{
			L2Clan clan;
			
			packet.writeD(size);
			packet.writeD(size);
			// Listing the Lord and the approved clans
			for (L2SiegeClan siegeclan : _castle.getSiege().getDefenderClans())
			{
				clan = ClanTable.getInstance().getClan(siegeclan.getClanId());
				if (clan == null)
				{
					continue;
				}
				
				packet.writeD(clan.getId());
				packet.writeS(clan.getName());
				packet.writeS(clan.getLeaderName());
				packet.writeD(clan.getCrestId());
				packet.writeD(0x00); // signed time (seconds) (not storated by L2J)
				switch (siegeclan.getType())
				{
					case OWNER:
					{
						packet.writeD(0x01); // owner
						break;
					}
					case DEFENDER_PENDING:
					{
						packet.writeD(0x02); // approved
						break;
					}
					case DEFENDER:
					{
						packet.writeD(0x03); // waiting approved
						break;
					}
					default:
					{
						packet.writeD(0x00);
						break;
					}
				}
				packet.writeD(clan.getAllyId());
				packet.writeS(clan.getAllyName());
				packet.writeS(""); // AllyLeaderName
				packet.writeD(clan.getAllyCrestId());
			}
			for (L2SiegeClan siegeclan : _castle.getSiege().getDefenderWaitingClans())
			{
				clan = ClanTable.getInstance().getClan(siegeclan.getClanId());
				packet.writeD(clan.getId());
				packet.writeS(clan.getName());
				packet.writeS(clan.getLeaderName());
				packet.writeD(clan.getCrestId());
				packet.writeD(0x00); // signed time (seconds) (not storated by L2J)
				packet.writeD(0x02); // waiting approval
				packet.writeD(clan.getAllyId());
				packet.writeS(clan.getAllyName());
				packet.writeS(""); // AllyLeaderName
				packet.writeD(clan.getAllyCrestId());
			}
		}
		else
		{
			packet.writeD(0x00);
			packet.writeD(0x00);
		}
		return true;
	}
}

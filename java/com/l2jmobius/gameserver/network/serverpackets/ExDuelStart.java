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
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * Duel Start packet implementation.
 * @author KenM, Zoey76
 */
public class ExDuelStart implements IClientOutgoingPacket
{
	public static final ExDuelStart PLAYER_DUEL = new ExDuelStart(false);
	public static final ExDuelStart PARTY_DUEL = new ExDuelStart(true);
	
	private final boolean _partyDuel;
	
	public ExDuelStart(boolean partyDuel)
	{
		_partyDuel = partyDuel;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_DUEL_START.writeId(packet);
		packet.writeD(_partyDuel ? 1 : 0);
		return true;
	}
}

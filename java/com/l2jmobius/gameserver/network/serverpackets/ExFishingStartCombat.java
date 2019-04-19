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
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author -Wooden-
 */
public class ExFishingStartCombat implements IClientOutgoingPacket
{
	private final L2Character _activeChar;
	private final int _time;
	private final int _hp;
	private final int _lureType;
	private final int _deceptiveMode;
	private final int _mode;
	
	public ExFishingStartCombat(L2Character character, int time, int hp, int mode, int lureType, int deceptiveMode)
	{
		_activeChar = character;
		_time = time;
		_hp = hp;
		_mode = mode;
		_lureType = lureType;
		_deceptiveMode = deceptiveMode;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_FISHING_START_COMBAT.writeId(packet);
		packet.writeD(_activeChar.getObjectId());
		packet.writeD(_time);
		packet.writeD(_hp);
		packet.writeC(_mode); // mode: 0 = resting, 1 = fighting
		packet.writeC(_lureType); // 0 = newbie lure, 1 = normal lure, 2 = night lure
		packet.writeC(_deceptiveMode); // Fish Deceptive Mode: 0 = no, 1 = yes
		return true;
	}
}
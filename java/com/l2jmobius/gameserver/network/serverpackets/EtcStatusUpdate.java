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
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Luca Baldi
 */
public class EtcStatusUpdate implements IClientOutgoingPacket
{
	private final L2PcInstance _activeChar;
	
	public EtcStatusUpdate(L2PcInstance activeChar)
	{
		_activeChar = activeChar;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.ETC_STATUS_UPDATE.writeId(packet);
		packet.writeD(_activeChar.getCharges()); // 1-7 increase force, lvl
		packet.writeD(_activeChar.getWeightPenalty()); // 1-4 weight penalty, lvl (1=50%, 2=66.6%, 3=80%, 4=100%)
		packet.writeD((_activeChar.getMessageRefusal() || _activeChar.isChatBanned() || _activeChar.isSilenceMode()) ? 1 : 0); // 1 = block all chat
		packet.writeD(_activeChar.isInsideZone(ZoneId.DANGER_AREA) ? 1 : 0); // 1 = danger area
		packet.writeD(_activeChar.getExpertiseWeaponPenalty()); // Weapon Grade Penalty [1-4]
		packet.writeD(_activeChar.getExpertiseArmorPenalty()); // Armor Grade Penalty [1-4]
		packet.writeD(_activeChar.hasCharmOfCourage() ? 1 : 0); // 1 = charm of courage (allows resurrection on the same spot upon death on the siege battlefield)
		packet.writeD(_activeChar.getDeathPenaltyBuffLevel()); // 1-15 death penalty, lvl (combat ability decreased due to death)
		packet.writeD(_activeChar.getChargedSouls());
		return true;
	}
}

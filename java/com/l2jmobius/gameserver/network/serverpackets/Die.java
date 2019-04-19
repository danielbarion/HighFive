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
import com.l2jmobius.gameserver.data.xml.impl.AdminData;
import com.l2jmobius.gameserver.instancemanager.CHSiegeManager;
import com.l2jmobius.gameserver.instancemanager.CastleManager;
import com.l2jmobius.gameserver.instancemanager.FortManager;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager;
import com.l2jmobius.gameserver.model.L2AccessLevel;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.L2SiegeClan;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.entity.Castle;
import com.l2jmobius.gameserver.model.entity.Fort;
import com.l2jmobius.gameserver.model.entity.clanhall.SiegableHall;
import com.l2jmobius.gameserver.model.olympiad.OlympiadManager;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class Die implements IClientOutgoingPacket
{
	private final int _charObjId;
	private final boolean _canTeleport;
	private final boolean _sweepable;
	private L2AccessLevel _access = AdminData.getInstance().getAccessLevel(0);
	private L2Clan _clan;
	private final L2Character _activeChar;
	private boolean _isJailed;
	private boolean _staticRes = false;
	
	public Die(L2Character cha)
	{
		_charObjId = cha.getObjectId();
		_activeChar = cha;
		if (cha.isPlayer())
		{
			final L2PcInstance player = cha.getActingPlayer();
			_access = player.getAccessLevel();
			_clan = player.getClan();
			_isJailed = player.isJailed();
		}
		_canTeleport = cha.canRevive() && !cha.isPendingRevive();
		_sweepable = cha.isSweepActive();
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.DIE.writeId(packet);
		packet.writeD(_charObjId);
		packet.writeD(_canTeleport ? 0x01 : 0x00);
		
		if (_activeChar.isPlayer())
		{
			if (!OlympiadManager.getInstance().isRegistered(_activeChar.getActingPlayer()) && !_activeChar.isOnEvent())
			{
				_staticRes = _activeChar.getInventory().haveItemForSelfResurrection();
			}
			
			// Verify if player can use fixed resurrection without Feather
			if (_access.allowFixedRes())
			{
				_staticRes = true;
			}
		}
		
		if (_canTeleport && (_clan != null) && !_isJailed)
		{
			boolean isInCastleDefense = false;
			boolean isInFortDefense = false;
			
			L2SiegeClan siegeClan = null;
			final Castle castle = CastleManager.getInstance().getCastle(_activeChar);
			final Fort fort = FortManager.getInstance().getFort(_activeChar);
			final SiegableHall hall = CHSiegeManager.getInstance().getNearbyClanHall(_activeChar);
			if ((castle != null) && castle.getSiege().isInProgress())
			{
				// siege in progress
				siegeClan = castle.getSiege().getAttackerClan(_clan);
				if ((siegeClan == null) && castle.getSiege().checkIsDefender(_clan))
				{
					isInCastleDefense = true;
				}
			}
			else if ((fort != null) && fort.getSiege().isInProgress())
			{
				// siege in progress
				siegeClan = fort.getSiege().getAttackerClan(_clan);
				if ((siegeClan == null) && fort.getSiege().checkIsDefender(_clan))
				{
					isInFortDefense = true;
				}
			}
			
			packet.writeD(_clan.getHideoutId() > 0 ? 0x01 : 0x00); // 6d 01 00 00 00 - to hide away
			packet.writeD((_clan.getCastleId() > 0) || isInCastleDefense ? 0x01 : 0x00); // 6d 02 00 00 00 - to castle
			packet.writeD((TerritoryWarManager.getInstance().getHQForClan(_clan) != null) || ((siegeClan != null) && !isInCastleDefense && !isInFortDefense && !siegeClan.getFlag().isEmpty()) || ((hall != null) && hall.getSiege().checkIsAttacker(_clan)) ? 0x01 : 0x00); // 6d 03 00 00 00 - to siege HQ
			packet.writeD(_sweepable ? 0x01 : 0x00); // sweepable (blue glow)
			packet.writeD(_staticRes ? 0x01 : 0x00); // 6d 04 00 00 00 - to FIXED
			packet.writeD((_clan.getFortId() > 0) || isInFortDefense ? 0x01 : 0x00); // 6d 05 00 00 00 - to fortress
		}
		else
		{
			packet.writeD(0x00); // 6d 01 00 00 00 - to hide away
			packet.writeD(0x00); // 6d 02 00 00 00 - to castle
			packet.writeD(0x00); // 6d 03 00 00 00 - to siege HQ
			packet.writeD(_sweepable ? 0x01 : 0x00); // sweepable (blue glow)
			packet.writeD(_staticRes ? 0x01 : 0x00); // 6d 04 00 00 00 - to FIXED
			packet.writeD(0x00); // 6d 05 00 00 00 - to fortress
		}
		// TODO: protocol 152
		// packet.writeC(0); // show die animation
		// packet.writeD(0); // agathion ress button
		// packet.writeD(0); // additional free space
		return true;
	}
}

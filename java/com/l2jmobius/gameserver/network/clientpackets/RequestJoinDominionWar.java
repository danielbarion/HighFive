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

package com.l2jmobius.gameserver.network.clientpackets;

import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager;
import com.l2jmobius.gameserver.model.ClanPrivilege;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.L2GameClient;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ExShowDominionRegistry;

/**
 * @author Gigiikun
 */
public final class RequestJoinDominionWar implements IClientIncomingPacket
{
	private int _territoryId;
	private int _isClan;
	private int _isJoining;
	
	@Override
	public boolean read(L2GameClient client, PacketReader packet)
	{
		_territoryId = packet.readD();
		_isClan = packet.readD();
		_isJoining = packet.readD();
		return true;
	}
	
	@Override
	public void run(L2GameClient client)
	{
		final L2PcInstance activeChar = client.getActiveChar();
		if (activeChar == null)
		{
			return;
		}
		final L2Clan clan = activeChar.getClan();
		final int castleId = _territoryId - 80;
		
		if (TerritoryWarManager.getInstance().getIsRegistrationOver())
		{
			activeChar.sendPacket(SystemMessageId.IT_IS_NOT_A_TERRITORY_WAR_REGISTRATION_PERIOD_SO_A_REQUEST_CANNOT_BE_MADE_AT_THIS_TIME);
			return;
		}
		else if ((clan != null) && (TerritoryWarManager.getInstance().getTerritory(castleId).getOwnerClan() == clan))
		{
			activeChar.sendPacket(SystemMessageId.THE_CLAN_WHO_OWNS_THE_TERRITORY_CANNOT_PARTICIPATE_IN_THE_TERRITORY_WAR_AS_MERCENARIES);
			return;
		}
		
		if (_isClan == 0x01)
		{
			if (!activeChar.hasClanPrivilege(ClanPrivilege.CS_MANAGE_SIEGE))
			{
				activeChar.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			
			if (clan == null)
			{
				return;
			}
			
			if (_isJoining == 1)
			{
				if (System.currentTimeMillis() < clan.getDissolvingExpiryTime())
				{
					activeChar.sendPacket(SystemMessageId.YOUR_CLAN_MAY_NOT_REGISTER_TO_PARTICIPATE_IN_A_SIEGE_WHILE_UNDER_A_GRACE_PERIOD_OF_THE_CLAN_S_DISSOLUTION);
					return;
				}
				else if (TerritoryWarManager.getInstance().checkIsRegistered(-1, clan))
				{
					activeChar.sendPacket(SystemMessageId.YOU_VE_ALREADY_REQUESTED_A_TERRITORY_WAR_IN_ANOTHER_TERRITORY_ELSEWHERE);
					return;
				}
				TerritoryWarManager.getInstance().registerClan(castleId, clan);
			}
			else
			{
				TerritoryWarManager.getInstance().removeClan(castleId, clan);
			}
		}
		else
		{
			if ((activeChar.getLevel() < 40) || (activeChar.getClassId().level() < 2))
			{
				// TODO: punish player
				return;
			}
			if (_isJoining == 1)
			{
				if (TerritoryWarManager.getInstance().checkIsRegistered(-1, activeChar.getObjectId()))
				{
					activeChar.sendPacket(SystemMessageId.YOU_VE_ALREADY_REQUESTED_A_TERRITORY_WAR_IN_ANOTHER_TERRITORY_ELSEWHERE);
					return;
				}
				else if ((clan != null) && TerritoryWarManager.getInstance().checkIsRegistered(-1, clan))
				{
					activeChar.sendPacket(SystemMessageId.YOU_VE_ALREADY_REQUESTED_A_TERRITORY_WAR_IN_ANOTHER_TERRITORY_ELSEWHERE);
					return;
				}
				TerritoryWarManager.getInstance().registerMerc(castleId, activeChar);
			}
			else
			{
				TerritoryWarManager.getInstance().removeMerc(castleId, activeChar);
			}
		}
		activeChar.sendPacket(new ExShowDominionRegistry(castleId, activeChar));
	}
}

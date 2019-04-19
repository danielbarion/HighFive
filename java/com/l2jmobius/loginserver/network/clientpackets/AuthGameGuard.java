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
package com.l2jmobius.loginserver.network.clientpackets;

import com.l2jmobius.commons.network.IIncomingPacket;
import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.loginserver.network.ConnectionState;
import com.l2jmobius.loginserver.network.L2LoginClient;
import com.l2jmobius.loginserver.network.serverpackets.GGAuth;
import com.l2jmobius.loginserver.network.serverpackets.LoginFail.LoginFailReason;

/**
 * Format: ddddd
 * @author -Wooden-
 */
public class AuthGameGuard implements IIncomingPacket<L2LoginClient>
{
	private int _sessionId;
	
	@SuppressWarnings("unused")
	private int _data1;
	@SuppressWarnings("unused")
	private int _data2;
	@SuppressWarnings("unused")
	private int _data3;
	@SuppressWarnings("unused")
	private int _data4;
	
	@Override
	public boolean read(L2LoginClient client, PacketReader packet)
	{
		if (packet.getReadableBytes() >= 20)
		{
			_sessionId = packet.readD();
			_data1 = packet.readD();
			_data2 = packet.readD();
			_data3 = packet.readD();
			_data4 = packet.readD();
			return true;
		}
		return false;
	}
	
	@Override
	public void run(L2LoginClient client)
	{
		if (_sessionId == client.getSessionId())
		{
			client.setConnectionState(ConnectionState.AUTHED_GG);
			client.sendPacket(new GGAuth(client.getSessionId()));
		}
		else
		{
			client.close(LoginFailReason.REASON_ACCESS_FAILED);
		}
	}
}

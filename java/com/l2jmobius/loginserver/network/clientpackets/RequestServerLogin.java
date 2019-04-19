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

import com.l2jmobius.Config;
import com.l2jmobius.commons.network.IIncomingPacket;
import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.loginserver.LoginController;
import com.l2jmobius.loginserver.LoginServer;
import com.l2jmobius.loginserver.SessionKey;
import com.l2jmobius.loginserver.network.L2LoginClient;
import com.l2jmobius.loginserver.network.gameserverpackets.ServerStatus;
import com.l2jmobius.loginserver.network.serverpackets.LoginFail.LoginFailReason;
import com.l2jmobius.loginserver.network.serverpackets.PlayFail.PlayFailReason;
import com.l2jmobius.loginserver.network.serverpackets.PlayOk;

/**
 * <pre>
 * Format is ddc
 * d: first part of session id
 * d: second part of session id
 * c: server ID
 * </pre>
 */
public class RequestServerLogin implements IIncomingPacket<L2LoginClient>
{
	private int _skey1;
	private int _skey2;
	private int _serverId;
	
	@Override
	public boolean read(L2LoginClient client, PacketReader packet)
	{
		if (packet.getReadableBytes() >= 9)
		{
			_skey1 = packet.readD();
			_skey2 = packet.readD();
			_serverId = packet.readC();
			return true;
		}
		return false;
	}
	
	@Override
	public void run(L2LoginClient client)
	{
		final SessionKey sk = client.getSessionKey();
		
		// if we didnt showed the license we cant check these values
		if (!Config.SHOW_LICENCE || sk.checkLoginPair(_skey1, _skey2))
		{
			if ((LoginServer.getInstance().getStatus() == ServerStatus.STATUS_DOWN) || ((LoginServer.getInstance().getStatus() == ServerStatus.STATUS_GM_ONLY) && (client.getAccessLevel() < 1)))
			{
				client.close(LoginFailReason.REASON_ACCESS_FAILED);
			}
			else if (LoginController.getInstance().isLoginPossible(client, _serverId))
			{
				client.setJoinedGS(true);
				client.sendPacket(new PlayOk(sk));
			}
			else
			{
				client.close(PlayFailReason.REASON_SERVER_OVERLOADED);
			}
		}
		else
		{
			client.close(LoginFailReason.REASON_ACCESS_FAILED);
		}
	}
}

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
package com.l2jmobius.loginserver.network;

import java.util.logging.Logger;

import com.l2jmobius.commons.network.BaseRecievePacket;
import com.l2jmobius.loginserver.GameServerThread;
import com.l2jmobius.loginserver.network.gameserverpackets.BlowFishKey;
import com.l2jmobius.loginserver.network.gameserverpackets.ChangeAccessLevel;
import com.l2jmobius.loginserver.network.gameserverpackets.ChangePassword;
import com.l2jmobius.loginserver.network.gameserverpackets.GameServerAuth;
import com.l2jmobius.loginserver.network.gameserverpackets.PlayerAuthRequest;
import com.l2jmobius.loginserver.network.gameserverpackets.PlayerInGame;
import com.l2jmobius.loginserver.network.gameserverpackets.PlayerLogout;
import com.l2jmobius.loginserver.network.gameserverpackets.PlayerTracert;
import com.l2jmobius.loginserver.network.gameserverpackets.ReplyCharacters;
import com.l2jmobius.loginserver.network.gameserverpackets.RequestTempBan;
import com.l2jmobius.loginserver.network.gameserverpackets.ServerStatus;
import com.l2jmobius.loginserver.network.loginserverpackets.LoginServerFail;

/**
 * @author mrTJO
 */
public class L2JGameServerPacketHandler
{
	protected static Logger LOGGER = Logger.getLogger(L2JGameServerPacketHandler.class.getName());
	
	public enum GameServerState
	{
		CONNECTED,
		BF_CONNECTED,
		AUTHED
	}
	
	public static BaseRecievePacket handlePacket(byte[] data, GameServerThread server)
	{
		BaseRecievePacket msg = null;
		final int opcode = data[0] & 0xff;
		final GameServerState state = server.getLoginConnectionState();
		switch (state)
		{
			case CONNECTED:
			{
				switch (opcode)
				{
					case 0x00:
					{
						msg = new BlowFishKey(data, server);
						break;
					}
					default:
					{
						LOGGER.warning("Unknown Opcode (" + Integer.toHexString(opcode).toUpperCase() + ") in state " + state.name() + " from GameServer, closing connection.");
						server.forceClose(LoginServerFail.NOT_AUTHED);
						break;
					}
				}
				break;
			}
			case BF_CONNECTED:
			{
				switch (opcode)
				{
					case 0x01:
					{
						msg = new GameServerAuth(data, server);
						break;
					}
					default:
					{
						LOGGER.warning("Unknown Opcode (" + Integer.toHexString(opcode).toUpperCase() + ") in state " + state.name() + " from GameServer, closing connection.");
						server.forceClose(LoginServerFail.NOT_AUTHED);
						break;
					}
				}
				break;
			}
			case AUTHED:
			{
				switch (opcode)
				{
					case 0x02:
					{
						msg = new PlayerInGame(data, server);
						break;
					}
					case 0x03:
					{
						msg = new PlayerLogout(data, server);
						break;
					}
					case 0x04:
					{
						msg = new ChangeAccessLevel(data, server);
						break;
					}
					case 0x05:
					{
						msg = new PlayerAuthRequest(data, server);
						break;
					}
					case 0x06:
					{
						msg = new ServerStatus(data, server);
						break;
					}
					case 0x07:
					{
						msg = new PlayerTracert(data);
						break;
					}
					case 0x08:
					{
						msg = new ReplyCharacters(data, server);
						break;
					}
					case 0x09:
					{
						// msg = new RequestSendMail(data);
						break;
					}
					case 0x0A:
					{
						msg = new RequestTempBan(data);
						break;
					}
					case 0x0B:
					{
						new ChangePassword(data);
						break;
					}
					default:
					{
						LOGGER.warning("Unknown Opcode (" + Integer.toHexString(opcode).toUpperCase() + ") in state " + state.name() + " from GameServer, closing connection.");
						server.forceClose(LoginServerFail.NOT_AUTHED);
						break;
					}
				}
				break;
			}
		}
		return msg;
	}
}

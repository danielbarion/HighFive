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
package com.l2jmobius.loginserver;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.l2jmobius.commons.network.BaseSendablePacket;
import com.l2jmobius.commons.util.crypt.NewCrypt;
import com.l2jmobius.commons.util.crypt.ScrambledKeyPair;
import com.l2jmobius.loginserver.GameServerTable.GameServerInfo;
import com.l2jmobius.loginserver.network.L2JGameServerPacketHandler;
import com.l2jmobius.loginserver.network.L2JGameServerPacketHandler.GameServerState;
import com.l2jmobius.loginserver.network.loginserverpackets.ChangePasswordResponse;
import com.l2jmobius.loginserver.network.loginserverpackets.InitLS;
import com.l2jmobius.loginserver.network.loginserverpackets.KickPlayer;
import com.l2jmobius.loginserver.network.loginserverpackets.LoginServerFail;
import com.l2jmobius.loginserver.network.loginserverpackets.RequestCharacters;

/**
 * @author -Wooden-
 * @author KenM
 */
public class GameServerThread extends Thread
{
	protected static final Logger LOGGER = Logger.getLogger(GameServerThread.class.getName());
	private final Socket _connection;
	private InputStream _in;
	private OutputStream _out;
	private final RSAPublicKey _publicKey;
	private final RSAPrivateKey _privateKey;
	private NewCrypt _blowfish;
	private GameServerState _loginConnectionState = GameServerState.CONNECTED;
	
	private final String _connectionIp;
	
	private GameServerInfo _gsi;
	
	/** Authed Clients on a GameServer */
	private final Set<String> _accountsOnGameServer = ConcurrentHashMap.newKeySet();
	
	private String _connectionIPAddress;
	
	@Override
	public void run()
	{
		_connectionIPAddress = _connection.getInetAddress().getHostAddress();
		if (isBannedGameserverIP(_connectionIPAddress))
		{
			LOGGER.info("GameServerRegistration: IP Address " + _connectionIPAddress + " is on Banned IP list.");
			forceClose(LoginServerFail.REASON_IP_BANNED);
			// ensure no further processing for this connection
			return;
		}
		
		final InitLS startPacket = new InitLS(_publicKey.getModulus().toByteArray());
		try
		{
			sendPacket(startPacket);
			
			int lengthHi = 0;
			int lengthLo = 0;
			int length = 0;
			boolean checksumOk = false;
			for (;;)
			{
				lengthLo = _in.read();
				lengthHi = _in.read();
				length = (lengthHi * 256) + lengthLo;
				
				if ((lengthHi < 0) || _connection.isClosed())
				{
					LOGGER.finer("LoginServerThread: Login terminated the connection.");
					break;
				}
				
				final byte[] data = new byte[length - 2];
				
				int receivedBytes = 0;
				int newBytes = 0;
				int left = length - 2;
				while ((newBytes != -1) && (receivedBytes < (length - 2)))
				{
					newBytes = _in.read(data, receivedBytes, left);
					receivedBytes += newBytes;
					left -= newBytes;
				}
				
				if (receivedBytes != (length - 2))
				{
					LOGGER.warning("Incomplete Packet is sent to the server, closing connection.(LS)");
					break;
				}
				
				// decrypt if we have a key
				_blowfish.decrypt(data, 0, data.length);
				checksumOk = NewCrypt.verifyChecksum(data);
				if (!checksumOk)
				{
					LOGGER.warning("Incorrect packet checksum, closing connection (LS)");
					return;
				}
				
				L2JGameServerPacketHandler.handlePacket(data, this);
			}
		}
		catch (IOException e)
		{
			final String serverName = getServerId() != -1 ? "[" + getServerId() + "] " + GameServerTable.getInstance().getServerNameById(getServerId()) : "(" + _connectionIPAddress + ")";
			final String msg = "GameServer " + serverName + ": Connection lost: " + e.getMessage();
			LOGGER.info(msg);
		}
		finally
		{
			if (isAuthed())
			{
				_gsi.setDown();
				LOGGER.info("Server [" + getServerId() + "] " + GameServerTable.getInstance().getServerNameById(getServerId()) + " is now set as disconnected");
			}
			LoginServer.getInstance().getGameServerListener().removeGameServer(this);
			LoginServer.getInstance().getGameServerListener().removeFloodProtection(_connectionIp);
		}
	}
	
	public boolean hasAccountOnGameServer(String account)
	{
		return _accountsOnGameServer.contains(account);
	}
	
	public int getPlayerCount()
	{
		return _accountsOnGameServer.size();
	}
	
	/**
	 * Attachs a GameServerInfo to this Thread<br>
	 * <ul>
	 * <li>Updates the GameServerInfo values based on GameServerAuth packet</li>
	 * <li><b>Sets the GameServerInfo as Authed</b></li>
	 * </ul>
	 * @param gsi The GameServerInfo to be attached.
	 * @param port
	 * @param hosts
	 * @param maxPlayers
	 */
	public void attachGameServerInfo(GameServerInfo gsi, int port, String[] hosts, int maxPlayers)
	{
		setGameServerInfo(gsi);
		gsi.setGameServerThread(this);
		gsi.setPort(port);
		setGameHosts(hosts);
		gsi.setMaxPlayers(maxPlayers);
		gsi.setAuthed(true);
	}
	
	public void forceClose(int reason)
	{
		sendPacket(new LoginServerFail(reason));
		
		try
		{
			_connection.close();
		}
		catch (IOException e)
		{
			LOGGER.finer("GameServerThread: Failed disconnecting banned server, server already disconnected.");
		}
	}
	
	/**
	 * @param ipAddress
	 * @return
	 */
	public static boolean isBannedGameserverIP(String ipAddress)
	{
		return false;
	}
	
	public GameServerThread(Socket con)
	{
		_connection = con;
		_connectionIp = con.getInetAddress().getHostAddress();
		try
		{
			_in = _connection.getInputStream();
			_out = new BufferedOutputStream(_connection.getOutputStream());
		}
		catch (IOException e)
		{
			LOGGER.warning(getClass().getSimpleName() + ": " + e.getMessage());
		}
		final ScrambledKeyPair pair = LoginController.getInstance().getScrambledRSAKeyPair();
		_privateKey = (RSAPrivateKey) pair.getPrivateKey();
		_publicKey = (RSAPublicKey) pair.getPublicKey();
		_blowfish = new NewCrypt("_;v.]05-31!|+-%xT!^[$\00");
		setName(getClass().getSimpleName() + "-" + getId() + "@" + _connectionIp);
		start();
	}
	
	/**
	 * @param sl
	 */
	public void sendPacket(BaseSendablePacket sl)
	{
		try
		{
			final byte[] data = sl.getContent();
			NewCrypt.appendChecksum(data);
			_blowfish.crypt(data, 0, data.length);
			
			final int len = data.length + 2;
			synchronized (_out)
			{
				_out.write(len & 0xff);
				_out.write((len >> 8) & 0xff);
				_out.write(data);
				_out.flush();
			}
		}
		catch (IOException e)
		{
			LOGGER.severe("IOException while sending packet " + sl.getClass().getSimpleName());
		}
	}
	
	public void kickPlayer(String account)
	{
		sendPacket(new KickPlayer(account));
	}
	
	public void requestCharacters(String account)
	{
		sendPacket(new RequestCharacters(account));
	}
	
	public void ChangePasswordResponse(byte successful, String characterName, String msgToSend)
	{
		sendPacket(new ChangePasswordResponse(successful, characterName, msgToSend));
	}
	
	/**
	 * @param hosts The gameHost to set.
	 */
	public void setGameHosts(String[] hosts)
	{
		LOGGER.info("Updated Gameserver [" + getServerId() + "] " + GameServerTable.getInstance().getServerNameById(getServerId()) + " IP's:");
		
		_gsi.clearServerAddresses();
		for (int i = 0; i < hosts.length; i += 2)
		{
			try
			{
				_gsi.addServerAddress(hosts[i], hosts[i + 1]);
			}
			catch (Exception e)
			{
				LOGGER.warning("Couldn't resolve hostname \"" + e + "\"");
			}
		}
		
		for (String s : _gsi.getServerAddresses())
		{
			LOGGER.info(s);
		}
	}
	
	/**
	 * @return Returns the isAuthed.
	 */
	public boolean isAuthed()
	{
		if (_gsi == null)
		{
			return false;
		}
		return _gsi.isAuthed();
	}
	
	public void setGameServerInfo(GameServerInfo gsi)
	{
		_gsi = gsi;
	}
	
	public GameServerInfo getGameServerInfo()
	{
		return _gsi;
	}
	
	/**
	 * @return Returns the connectionIpAddress.
	 */
	public String getConnectionIpAddress()
	{
		return _connectionIPAddress;
	}
	
	public int getServerId()
	{
		if (_gsi != null)
		{
			return _gsi.getId();
		}
		return -1;
	}
	
	public RSAPrivateKey getPrivateKey()
	{
		return _privateKey;
	}
	
	public void SetBlowFish(NewCrypt blowfish)
	{
		_blowfish = blowfish;
	}
	
	public void addAccountOnGameServer(String account)
	{
		_accountsOnGameServer.add(account);
	}
	
	public void removeAccountOnGameServer(String account)
	{
		_accountsOnGameServer.remove(account);
	}
	
	public GameServerState getLoginConnectionState()
	{
		return _loginConnectionState;
	}
	
	public void setLoginConnectionState(GameServerState state)
	{
		_loginConnectionState = state;
	}
}

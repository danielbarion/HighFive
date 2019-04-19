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

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.crypto.SecretKey;

import com.l2jmobius.commons.network.ChannelInboundHandler;
import com.l2jmobius.commons.network.IIncomingPacket;
import com.l2jmobius.commons.network.IOutgoingPacket;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.commons.util.crypt.ScrambledKeyPair;
import com.l2jmobius.loginserver.LoginController;
import com.l2jmobius.loginserver.SessionKey;
import com.l2jmobius.loginserver.network.serverpackets.Init;
import com.l2jmobius.loginserver.network.serverpackets.LoginFail;
import com.l2jmobius.loginserver.network.serverpackets.LoginFail.LoginFailReason;
import com.l2jmobius.loginserver.network.serverpackets.PlayFail;
import com.l2jmobius.loginserver.network.serverpackets.PlayFail.PlayFailReason;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * Represents a client connected into the LoginServer
 * @author KenM
 */
public final class L2LoginClient extends ChannelInboundHandler<L2LoginClient>
{
	private static final Logger LOGGER = Logger.getLogger(L2LoginClient.class.getName());
	
	// Crypt
	private final ScrambledKeyPair _scrambledPair;
	private final SecretKey _blowfishKey;
	private InetAddress _addr;
	private Channel _channel;
	
	private String _account;
	private int _accessLevel;
	private int _lastServer;
	private SessionKey _sessionKey;
	private int _sessionId;
	private boolean _joinedGS;
	private Map<Integer, Integer> _charsOnServers;
	private Map<Integer, long[]> _charsToDelete;
	
	private long _connectionStartTime;
	
	public L2LoginClient(SecretKey blowfishKey)
	{
		super();
		_blowfishKey = blowfishKey;
		_scrambledPair = LoginController.getInstance().getScrambledRSAKeyPair();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx)
	{
		super.channelActive(ctx);
		
		setConnectionState(ConnectionState.CONNECTED);
		final InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
		_addr = address.getAddress();
		_channel = ctx.channel();
		_sessionId = Rnd.nextInt();
		_connectionStartTime = System.currentTimeMillis();
		
		sendPacket(new Init(_scrambledPair.getScrambledModulus(), _blowfishKey.getEncoded(), _sessionId));
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx)
	{
		if (!_joinedGS || ((_connectionStartTime + LoginController.LOGIN_TIMEOUT) < System.currentTimeMillis()))
		{
			LoginController.getInstance().removeAuthedLoginClient(getAccount());
		}
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, IIncomingPacket<L2LoginClient> packet)
	{
		try
		{
			packet.run(this);
		}
		catch (Exception e)
		{
			LOGGER.warning(getClass().getSimpleName() + ": " + e.getMessage());
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
	}
	
	public InetAddress getConnectionAddress()
	{
		return _addr;
	}
	
	public String getAccount()
	{
		return _account;
	}
	
	public void setAccount(String account)
	{
		_account = account;
	}
	
	public void setAccessLevel(int accessLevel)
	{
		_accessLevel = accessLevel;
	}
	
	public int getAccessLevel()
	{
		return _accessLevel;
	}
	
	public void setLastServer(int lastServer)
	{
		_lastServer = lastServer;
	}
	
	public int getLastServer()
	{
		return _lastServer;
	}
	
	public int getSessionId()
	{
		return _sessionId;
	}
	
	public ScrambledKeyPair getScrambledKeyPair()
	{
		return _scrambledPair;
	}
	
	public boolean hasJoinedGS()
	{
		return _joinedGS;
	}
	
	public void setJoinedGS(boolean val)
	{
		_joinedGS = val;
	}
	
	public void setSessionKey(SessionKey sessionKey)
	{
		_sessionKey = sessionKey;
	}
	
	public SessionKey getSessionKey()
	{
		return _sessionKey;
	}
	
	public long getConnectionStartTime()
	{
		return _connectionStartTime;
	}
	
	public void sendPacket(IOutgoingPacket packet)
	{
		if ((packet == null))
		{
			return;
		}
		
		// Write into the channel.
		_channel.writeAndFlush(packet);
	}
	
	public void close(LoginFailReason reason)
	{
		close(new LoginFail(reason));
	}
	
	public void close(PlayFailReason reason)
	{
		close(new PlayFail(reason));
	}
	
	public void close(IOutgoingPacket packet)
	{
		sendPacket(packet);
		closeNow();
	}
	
	public void closeNow()
	{
		if (_channel != null)
		{
			_channel.close();
		}
	}
	
	public void setCharsOnServ(int servId, int chars)
	{
		if (_charsOnServers == null)
		{
			_charsOnServers = new HashMap<>();
		}
		_charsOnServers.put(servId, chars);
	}
	
	public Map<Integer, Integer> getCharsOnServ()
	{
		return _charsOnServers;
	}
	
	public void serCharsWaitingDelOnServ(int servId, long[] charsToDel)
	{
		if (_charsToDelete == null)
		{
			_charsToDelete = new HashMap<>();
		}
		_charsToDelete.put(servId, charsToDel);
	}
	
	public Map<Integer, long[]> getCharsWaitingDelOnServ()
	{
		return _charsToDelete;
	}
}

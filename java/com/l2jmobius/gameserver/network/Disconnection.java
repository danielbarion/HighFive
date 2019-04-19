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
package com.l2jmobius.gameserver.network;

import java.util.logging.Logger;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.instancemanager.AntiFeedManager;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.events.EventDispatcher;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerLogout;
import com.l2jmobius.gameserver.network.serverpackets.IClientOutgoingPacket;
import com.l2jmobius.gameserver.taskmanager.AttackStanceTaskManager;

/**
 * @author NB4L1
 */
public final class Disconnection
{
	private static final Logger LOGGER = Logger.getLogger(Disconnection.class.getName());
	
	public static L2GameClient getClient(L2GameClient client, L2PcInstance activeChar)
	{
		if (client != null)
		{
			return client;
		}
		
		if (activeChar != null)
		{
			return activeChar.getClient();
		}
		
		return null;
	}
	
	public static L2PcInstance getActiveChar(L2GameClient client, L2PcInstance activeChar)
	{
		if (activeChar != null)
		{
			return activeChar;
		}
		
		if (client != null)
		{
			return client.getActiveChar();
		}
		
		return null;
	}
	
	private final L2GameClient _client;
	private final L2PcInstance _activeChar;
	
	private Disconnection(L2GameClient client)
	{
		this(client, null);
	}
	
	public static Disconnection of(L2GameClient client)
	{
		return new Disconnection(client);
	}
	
	private Disconnection(L2PcInstance activeChar)
	{
		this(null, activeChar);
	}
	
	public static Disconnection of(L2PcInstance activeChar)
	{
		return new Disconnection(activeChar);
	}
	
	private Disconnection(L2GameClient client, L2PcInstance activeChar)
	{
		_client = getClient(client, activeChar);
		_activeChar = getActiveChar(client, activeChar);
		
		// Anti Feed
		AntiFeedManager.getInstance().onDisconnect(_client);
		
		if (_client != null)
		{
			_client.setActiveChar(null);
		}
		
		if (_activeChar != null)
		{
			_activeChar.setClient(null);
		}
	}
	
	public static Disconnection of(L2GameClient client, L2PcInstance activeChar)
	{
		return new Disconnection(client, activeChar);
	}
	
	public Disconnection storeMe()
	{
		try
		{
			if ((_activeChar != null) && _activeChar.isOnline())
			{
				_activeChar.storeMe();
			}
		}
		catch (RuntimeException e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		return this;
	}
	
	public Disconnection deleteMe()
	{
		try
		{
			if ((_activeChar != null) && _activeChar.isOnline())
			{
				EventDispatcher.getInstance().notifyEventAsync(new OnPlayerLogout(_activeChar), _activeChar);
				_activeChar.deleteMe();
			}
		}
		catch (RuntimeException e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		return this;
	}
	
	public Disconnection close(boolean toLoginScreen)
	{
		if (_client != null)
		{
			_client.close(toLoginScreen);
		}
		
		return this;
	}
	
	public Disconnection close(IClientOutgoingPacket packet)
	{
		if (_client != null)
		{
			_client.close(packet);
		}
		
		return this;
	}
	
	public void defaultSequence(boolean toLoginScreen)
	{
		defaultSequence();
		close(toLoginScreen);
	}
	
	public void defaultSequence(IClientOutgoingPacket packet)
	{
		defaultSequence();
		close(packet);
	}
	
	private void defaultSequence()
	{
		storeMe();
		deleteMe();
	}
	
	public void onDisconnection()
	{
		if (_activeChar != null)
		{
			ThreadPool.schedule(() -> defaultSequence(), _activeChar.canLogout() ? 0 : AttackStanceTaskManager.COMBAT_TIME);
		}
	}
}
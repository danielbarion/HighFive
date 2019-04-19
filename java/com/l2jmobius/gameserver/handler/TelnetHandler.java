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
package com.l2jmobius.gameserver.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
public class TelnetHandler implements IHandler<ITelnetHandler, String>
{
	private final Map<String, ITelnetHandler> _telnetHandlers;
	
	protected TelnetHandler()
	{
		_telnetHandlers = new HashMap<>();
	}
	
	@Override
	public void registerHandler(ITelnetHandler handler)
	{
		for (String element : handler.getCommandList())
		{
			_telnetHandlers.put(element.toLowerCase(), handler);
		}
	}
	
	@Override
	public synchronized void removeHandler(ITelnetHandler handler)
	{
		for (String element : handler.getCommandList())
		{
			_telnetHandlers.remove(element.toLowerCase());
		}
	}
	
	@Override
	public ITelnetHandler getHandler(String command)
	{
		if (command.contains(" "))
		{
			command = command.substring(0, command.indexOf(" "));
		}
		
		return _telnetHandlers.get(command.toLowerCase());
	}
	
	@Override
	public int size()
	{
		return _telnetHandlers.size();
	}
	
	public static TelnetHandler getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final TelnetHandler _instance = new TelnetHandler();
	}
}

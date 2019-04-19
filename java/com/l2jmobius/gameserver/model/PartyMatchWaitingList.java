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
package com.l2jmobius.gameserver.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author Gnacik
 */
public class PartyMatchWaitingList
{
	private final List<L2PcInstance> _members;
	
	protected PartyMatchWaitingList()
	{
		_members = new CopyOnWriteArrayList<>();
	}
	
	public void addPlayer(L2PcInstance player)
	{
		// player.setPartyWait(1);
		if (!_members.contains(player))
		{
			_members.add(player);
		}
	}
	
	public void removePlayer(L2PcInstance player)
	{
		// player.setPartyWait(0);
		if (_members.contains(player))
		{
			_members.remove(player);
		}
	}
	
	public List<L2PcInstance> getPlayers()
	{
		return _members;
	}
	
	public static PartyMatchWaitingList getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final PartyMatchWaitingList _instance = new PartyMatchWaitingList();
	}
}
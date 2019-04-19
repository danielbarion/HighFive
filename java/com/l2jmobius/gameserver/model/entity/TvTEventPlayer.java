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
package com.l2jmobius.gameserver.model.entity;

import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author UnAfraid
 */
public class TvTEventPlayer
{
	private final L2PcInstance _player;
	
	protected TvTEventPlayer(L2PcInstance player)
	{
		_player = player;
	}
	
	public boolean isOnEvent()
	{
		return TvTEvent.isStarted() && TvTEvent.isPlayerParticipant(_player.getObjectId());
	}
	
	public boolean isBlockingExit()
	{
		return true;
	}
	
	public boolean isBlockingDeathPenalty()
	{
		return true;
	}
	
	public boolean canRevive()
	{
		return false;
	}
	
	public L2PcInstance getPlayer()
	{
		return _player;
	}
}

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
package com.l2jmobius.gameserver.model.interfaces;

import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author UnAfraid
 */
public interface IEventListener
{
	/**
	 * @return {@code true} if player is on event, {@code false} otherwise.
	 */
	boolean isOnEvent();
	
	/**
	 * @return {@code true} if player is blocked from leaving the game, {@code false} otherwise.
	 */
	boolean isBlockingExit();
	
	/**
	 * @return {@code true} if player is blocked from receiving death penalty upon death, {@code false} otherwise.
	 */
	boolean isBlockingDeathPenalty();
	
	/**
	 * @return {@code true} if player can revive after death, {@code false} otherwise.
	 */
	boolean canRevive();
	
	L2PcInstance getPlayer();
}

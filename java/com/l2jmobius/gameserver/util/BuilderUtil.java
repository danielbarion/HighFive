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
package com.l2jmobius.gameserver.util;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.serverpackets.CreatureSay;

/**
 * @author lord_rex
 */
public final class BuilderUtil
{
	private BuilderUtil()
	{
		// utility class
	}
	
	/**
	 * Sends builder system message to the player.
	 * @param player
	 * @param message
	 */
	public static void sendSysMessage(L2PcInstance player, String message)
	{
		if (Config.GM_STARTUP_BUILDER_HIDE)
		{
			player.sendPacket(new CreatureSay(0, ChatType.GENERAL, "SYS", message));
		}
		else
		{
			player.sendMessage(message);
		}
	}
	
	/**
	 * Sends builder html message to the player.
	 * @param player
	 * @param message
	 */
	public static void sendHtmlMessage(L2PcInstance player, String message)
	{
		player.sendPacket(new CreatureSay(0, ChatType.GENERAL, "HTML", message));
	}
	
	/**
	 * Changes player's hiding state.
	 * @param player
	 * @param hide
	 * @return {@code true} if hide state was changed, otherwise {@code false}
	 */
	public static boolean setHiding(L2PcInstance player, boolean hide)
	{
		if (player.isInvisible() && hide)
		{
			// already hiding
			return false;
		}
		
		if (!player.isInvisible() && !hide)
		{
			// already visible
			return false;
		}
		
		player.setSilenceMode(hide);
		player.setIsInvul(hide);
		player.setInvisible(hide);
		
		player.broadcastUserInfo();
		return true;
	}
}

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
package ai.areas.FantasyIsle.KrateisCube;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Kratei's Cube AI
 * @author Mobius
 */
public final class KrateisCube extends AbstractNpcAI
{
	// NPC
	private static final int MANAGER = 32503; // Kratei's Cube Entrance Manager
	// Location
	private static final Location FANTASY_TELEPORT = new Location(-59193, -56893, -2034);
	
	public KrateisCube()
	{
		addStartNpc(MANAGER);
		addFirstTalkId(MANAGER);
		addTalkId(MANAGER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		switch (event)
		{
			case "32503-1.html":
			case "32503-2.html":
			case "32503-3.html":
			{
				htmltext = event;
				break;
			}
			case "teleportToFantasyIsland":
			{
				player.teleToLocation(FANTASY_TELEPORT);
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		return npc.getId() + ".html";
	}
	
	public static void main(String[] args)
	{
		new KrateisCube();
	}
}

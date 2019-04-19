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
package ai.areas.Gracia.AI.NPC.Klemis;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Klemis AI.
 * @author St3eT
 */
public class Klemis extends AbstractNpcAI
{
	// NPC
	private static final int KLEMIS = 32734; // Klemis
	// Location
	private static final Location LOCATION = new Location(-180218, 185923, -10576);
	// Misc
	private static final int MIN_LV = 80;
	
	public Klemis()
	{
		addStartNpc(KLEMIS);
		addTalkId(KLEMIS);
		addFirstTalkId(KLEMIS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equals("portInside"))
		{
			if (player.getLevel() >= MIN_LV)
			{
				player.teleToLocation(LOCATION);
			}
			else
			{
				return "32734-01.html";
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
}
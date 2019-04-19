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
package ai.others.Asher;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;

import ai.AbstractNpcAI;

/**
 * Asher AI.
 * @author Adry_85
 * @since 2.6.0.0
 */
public class Asher extends AbstractNpcAI
{
	// NPC
	private static final int ASHER = 32714;
	// Location
	private static final Location LOCATION = new Location(43835, -47749, -792);
	// Misc
	private static final int ADENA = 50000;
	
	private Asher()
	{
		addFirstTalkId(ASHER);
		addStartNpc(ASHER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equals("teleport"))
		{
			if (player.getAdena() >= ADENA)
			{
				player.teleToLocation(LOCATION);
				takeItems(player, Inventory.ADENA_ID, ADENA);
			}
			else
			{
				return "32714-02.html";
			}
		}
		else if (event.equals("32714-01.html"))
		{
			return event;
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	public static void main(String[] args)
	{
		new Asher();
	}
}
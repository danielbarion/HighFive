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
package ai.others.TeleportToFantasy;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.NpcStringId;

import ai.AbstractNpcAI;

/**
 * Fantasy Island teleport AI.
 * @author Plim
 */
public final class TeleportToFantasy extends AbstractNpcAI
{
	// NPC
	private static final int PADDIES = 32378;
	// Locations
	private static final Location RUNE_TOWNSHIP = new Location(43835, -47749, -792);
	private static final Location[] RETURN_LOCATIONS =
	{
		new Location(-80826, 149775, -3043),
		new Location(-12672, 122776, -3116),
		new Location(15670, 142983, -2705),
		new Location(83400, 147943, -3404),
		new Location(111409, 219364, -3545),
		new Location(82956, 53162, -1495),
		new Location(146331, 25762, -2018),
		new Location(116819, 76994, -2714),
		new Location(43835, -47749, -792),
		new Location(147930, -55281, -2728),
		new Location(87386, -143246, -1293),
		new Location(12882, 181053, -3560)
	};
	private static final Location[] ISLE_LOCATIONS =
	{
		new Location(-58752, -56898, -2032),
		new Location(-59716, -57868, -2032),
		new Location(-60691, -56893, -2032),
		new Location(-59720, -55921, -2032)
	};
	// Misc
	private static final Map<Integer, Integer> TELEPORTERS = new HashMap<>();
	static
	{
		TELEPORTERS.put(30059, 2); // Trisha
		TELEPORTERS.put(30080, 3); // Clarissa
		TELEPORTERS.put(30177, 5); // Valentina
		TELEPORTERS.put(30233, 7); // Esmeralda
		TELEPORTERS.put(30256, 1); // Bella
		TELEPORTERS.put(30320, 0); // Richlin
		TELEPORTERS.put(30848, 6); // Elisa
		TELEPORTERS.put(30899, 4); // Flauen
		TELEPORTERS.put(31320, 8); // Ilyana
		TELEPORTERS.put(31275, 9); // Tatiana
		TELEPORTERS.put(31964, 10); // Bilia
	}
	// Player Variables
	private static final String FANTASY_RETURN = "FANTASY_RETURN";
	
	private TeleportToFantasy()
	{
		addStartNpc(PADDIES);
		addStartNpc(TELEPORTERS.keySet());
		addTalkId(PADDIES);
		addTalkId(TELEPORTERS.keySet());
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		if (npc.getId() == PADDIES)
		{
			final int returnId = player.getVariables().getInt(FANTASY_RETURN, -1);
			
			if (returnId != -1)
			{
				player.teleToLocation(RETURN_LOCATIONS[returnId]);
				player.getVariables().remove(FANTASY_RETURN);
			}
			else
			{
				npc.broadcastSay(ChatType.GENERAL, NpcStringId.IF_YOUR_MEANS_OF_ARRIVAL_WAS_A_BIT_UNCONVENTIONAL_THEN_I_LL_BE_SENDING_YOU_BACK_TO_RUNE_TOWNSHIP_WHICH_IS_THE_NEAREST_TOWN);
				player.teleToLocation(RUNE_TOWNSHIP);
			}
		}
		else
		{
			player.teleToLocation(ISLE_LOCATIONS[getRandom(ISLE_LOCATIONS.length)]);
			player.getVariables().set(FANTASY_RETURN, String.valueOf(TELEPORTERS.get(npc.getId())));
		}
		return super.onTalk(npc, player);
	}
	
	public static void main(String[] args)
	{
		new TeleportToFantasy();
	}
}

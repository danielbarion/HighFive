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
package ai.areas.DragonValley.SeparatedSoul;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Separated Soul teleport AI.
 * @author UnAfraid, improved by Adry_85
 */
public final class SeparatedSoul extends AbstractNpcAI
{
	// NPCs
	private static final int[] SEPARATED_SOULS =
	{
		32864,
		32865,
		32866,
		32867,
		32868,
		32869,
		32870,
		32891
	};
	
	// Items
	private static final int WILL_OF_ANTHARAS = 17266;
	private static final int SEALED_BLOOD_CRYSTAL = 17267;
	private static final int ANTHARAS_BLOOD_CRYSTAL = 17268;
	// Misc
	private static final int MIN_LEVEL = 80;
	// Locations
	private static final Map<String, Location> LOCATIONS = new HashMap<>();
	static
	{
		LOCATIONS.put("HuntersVillage", new Location(117031, 76769, -2696));
		LOCATIONS.put("AntharasLair", new Location(131116, 114333, -3704));
		LOCATIONS.put("AntharasLairDeep", new Location(148447, 110582, -3944));
		LOCATIONS.put("AntharasLairMagicForceFieldBridge", new Location(146129, 111232, -3568));
		LOCATIONS.put("DragonValley", new Location(73122, 118351, -3714));
		LOCATIONS.put("DragonValleyCenter", new Location(99218, 110283, -3696));
		LOCATIONS.put("DragonValleyNorth", new Location(116992, 113716, -3056));
		LOCATIONS.put("DragonValleySouth", new Location(113203, 121063, -3712));
	}
	
	private SeparatedSoul()
	{
		addStartNpc(SEPARATED_SOULS);
		addTalkId(SEPARATED_SOULS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (LOCATIONS.containsKey(event))
		{
			if (player.getLevel() >= MIN_LEVEL)
			{
				player.teleToLocation(LOCATIONS.get(event), true);
			}
			else
			{
				return "no-level.htm";
			}
		}
		else if ("Synthesis".equals(event)) // Request Item Synthesis
		{
			if (hasQuestItems(player, WILL_OF_ANTHARAS, SEALED_BLOOD_CRYSTAL))
			{
				takeItems(player, WILL_OF_ANTHARAS, 1);
				takeItems(player, SEALED_BLOOD_CRYSTAL, 1);
				giveItems(player, ANTHARAS_BLOOD_CRYSTAL, 1);
			}
			else
			{
				return "no-items.htm";
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	public static void main(String[] args)
	{
		new SeparatedSoul();
	}
}
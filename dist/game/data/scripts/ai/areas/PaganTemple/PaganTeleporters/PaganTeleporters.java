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
package ai.areas.PaganTemple.PaganTeleporters;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.data.xml.impl.DoorData;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Pagan Temple teleport AI.<br>
 * Original Jython script by BiTi.
 * @author Plim
 */
public final class PaganTeleporters extends AbstractNpcAI
{
	// NPCs
	private static final int TRIOLS_MIRROR_1 = 32039;
	private static final int TRIOLS_MIRROR_2 = 32040;
	// Locations
	private static final Map<Integer, Location> TRIOLS_LOCS = new HashMap<>();
	static
	{
		TRIOLS_LOCS.put(TRIOLS_MIRROR_1, new Location(-12766, -35840, -10856));
		TRIOLS_LOCS.put(TRIOLS_MIRROR_2, new Location(36640, -51218, 718));
	}
	// @formatter:off
	private static final int[] NPCS =
	{
		32034, 32035, 32036, 32037, 32039, 32040
	};
	// @formatter:on
	// Items
	private static final int VISITORS_MARK = 8064;
	private static final int FADED_VISITORS_MARK = 8065;
	private static final int PAGANS_MARK = 8067;
	
	private PaganTeleporters()
	{
		addStartNpc(NPCS);
		addTalkId(NPCS);
		addFirstTalkId(TRIOLS_MIRROR_1, TRIOLS_MIRROR_2);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "Close_Door1":
			{
				DoorData.getInstance().getDoor(19160001).closeMe();
				break;
			}
			case "Close_Door2":
			{
				DoorData.getInstance().getDoor(19160010).closeMe();
				DoorData.getInstance().getDoor(19160011).closeMe();
				break;
			}
		}
		return "";
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (TRIOLS_LOCS.containsKey(npc.getId()))
		{
			player.teleToLocation(TRIOLS_LOCS.get(npc.getId()));
		}
		return "";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		switch (npc.getId())
		{
			case 32034:
			{
				if (!hasAtLeastOneQuestItem(player, VISITORS_MARK, FADED_VISITORS_MARK, PAGANS_MARK))
				{
					return "noItem.htm";
				}
				DoorData.getInstance().getDoor(19160001).openMe();
				startQuestTimer("Close_Door1", 10000, null, null);
				return "FadedMark.htm";
			}
			case 32035:
			{
				DoorData.getInstance().getDoor(19160001).openMe();
				startQuestTimer("Close_Door1", 10000, null, null);
				return "FadedMark.htm";
			}
			case 32036:
			{
				if (!hasQuestItems(player, PAGANS_MARK))
				{
					return "noMark.htm";
				}
				DoorData.getInstance().getDoor(19160010).openMe();
				DoorData.getInstance().getDoor(19160011).openMe();
				startQuestTimer("Close_Door2", 10000, null, null);
				return "world.openDoor.htm";
			}
			case 32037:
			{
				DoorData.getInstance().getDoor(19160010).openMe();
				DoorData.getInstance().getDoor(19160011).openMe();
				startQuestTimer("Close_Door2", 10000, null, null);
				return "FadedMark.htm";
			}
		}
		return super.onTalk(npc, player);
	}
	
	public static void main(String[] args)
	{
		new PaganTeleporters();
	}
}

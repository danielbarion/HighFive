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
package ai.areas.DragonValley.DragonVortex;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Dragon Vortex AI.
 * @author Adry_85
 * @since 2.6.0.0
 */
public final class DragonVortex extends AbstractNpcAI
{
	// NPC
	private static final int DRAGON_VORTEX = 32871;
	// Raids
	private static final int EMERALD_HORN = 25718;
	private static final int DUST_RIDER = 25719;
	private static final int BLEEDING_FLY = 25720;
	private static final int BLACKDAGGER_WING = 25721;
	private static final int SHADOW_SUMMONER = 25722;
	private static final int SPIKE_SLASHER = 25723;
	private static final int MUSCLE_BOMBER = 25724;
	// Item
	private static final int LARGE_DRAGON_BONE = 17248;
	// Variables
	private static final String I_QUEST0 = "I_QUEST0";
	// Locations
	private static final Location SPOT_1 = new Location(92744, 114045, -3072);
	private static final Location SPOT_2 = new Location(110112, 124976, -3624);
	private static final Location SPOT_3 = new Location(121637, 113657, -3792);
	private static final Location SPOT_4 = new Location(109346, 111849, -3040);
	
	private DragonVortex()
	{
		addStartNpc(DRAGON_VORTEX);
		addFirstTalkId(DRAGON_VORTEX);
		addTalkId(DRAGON_VORTEX);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "RAIDBOSS":
			{
				if (hasQuestItems(player, LARGE_DRAGON_BONE))
				{
					if (!npc.getVariables().getBoolean(I_QUEST0, false))
					{
						takeItems(player, LARGE_DRAGON_BONE, 1);
						final int random = getRandom(100);
						int raid = 0;
						if (random < 3)
						{
							raid = MUSCLE_BOMBER;
						}
						else if (random < 8)
						{
							raid = SHADOW_SUMMONER;
						}
						else if (random < 15)
						{
							raid = SPIKE_SLASHER;
						}
						else if (random < 25)
						{
							raid = BLACKDAGGER_WING;
						}
						else if (random < 45)
						{
							raid = BLEEDING_FLY;
						}
						else if (random < 67)
						{
							raid = DUST_RIDER;
						}
						else
						{
							raid = EMERALD_HORN;
						}
						
						Location LOC = null;
						switch (npc.getX())
						{
							case 92225:
							{
								LOC = SPOT_1;
								break;
							}
							case 110116:
							{
								LOC = SPOT_2;
								break;
							}
							case 121172:
							{
								LOC = SPOT_3;
								break;
							}
							case 108924:
							{
								LOC = SPOT_4;
								break;
							}
						}
						
						npc.getVariables().set(I_QUEST0, true);
						addSpawn(raid, LOC, false, 0, true);
						startQuestTimer("CANSPAWN", 60000, npc, null);
					}
					else
					{
						return "32871-02.html";
					}
				}
				else
				{
					return "32871-01.html";
				}
				break;
			}
			case "CANSPAWN":
			{
				npc.getVariables().set(I_QUEST0, false);
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	public static void main(String[] args)
	{
		new DragonVortex();
	}
}
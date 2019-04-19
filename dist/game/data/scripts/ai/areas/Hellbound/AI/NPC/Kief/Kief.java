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
package ai.areas.Hellbound.AI.NPC.Kief;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;
import ai.areas.Hellbound.HellboundEngine;

/**
 * Kief AI.
 * @author DS
 */
public final class Kief extends AbstractNpcAI
{
	// NPCs
	private static final int KIEF = 32354;
	// Items
	private static final int BOTTLE = 9672; // Magic Bottle
	private static final int DARION_BADGE = 9674; // Darion's Badge
	private static final int DIM_LIFE_FORCE = 9680; // Dim Life Force
	private static final int LIFE_FORCE = 9681; // Life Force
	private static final int CONTAINED_LIFE_FORCE = 9682; // Contained Life Force
	private static final int STINGER = 10012; // Scorpion Poison Stinger
	
	public Kief()
	{
		addFirstTalkId(KIEF);
		addStartNpc(KIEF);
		addTalkId(KIEF);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		switch (event)
		{
			case "32354-11g.htm":
			{
				htmltext = event;
				break;
			}
			case "Badges":
			{
				switch (HellboundEngine.getInstance().getLevel())
				{
					case 2:
					case 3:
					{
						if (hasQuestItems(player, DARION_BADGE))
						{
							HellboundEngine.getInstance().updateTrust((int) getQuestItemsCount(player, DARION_BADGE) * 10, true);
							takeItems(player, DARION_BADGE, -1);
							return "32354-10.htm";
						}
						break;
					}
					default:
					{
						htmltext = "32354-10a.htm";
						break;
					}
				}
				break;
			}
			case "Bottle":
			{
				if (HellboundEngine.getInstance().getLevel() >= 7)
				{
					if (getQuestItemsCount(player, STINGER) >= 20)
					{
						takeItems(player, STINGER, 20);
						giveItems(player, BOTTLE, 1);
						htmltext = "32354-11h.htm";
					}
					else
					{
						htmltext = "32354-11i.htm";
					}
				}
				break;
			}
			case "dlf":
			{
				if (HellboundEngine.getInstance().getLevel() == 7)
				{
					if (hasQuestItems(player, DIM_LIFE_FORCE))
					{
						HellboundEngine.getInstance().updateTrust((int) getQuestItemsCount(player, DIM_LIFE_FORCE) * 20, true);
						takeItems(player, DIM_LIFE_FORCE, -1);
						htmltext = "32354-11a.htm";
					}
					else
					{
						htmltext = "32354-11b.htm";
					}
				}
				break;
			}
			case "lf":
			{
				if (HellboundEngine.getInstance().getLevel() == 7)
				{
					if (hasQuestItems(player, LIFE_FORCE))
					{
						HellboundEngine.getInstance().updateTrust((int) getQuestItemsCount(player, LIFE_FORCE) * 80, true);
						takeItems(player, LIFE_FORCE, -1);
						htmltext = "32354-11c.htm";
					}
					else
					{
						htmltext = "32354-11d.htm";
					}
				}
				break;
			}
			case "clf":
			{
				if (HellboundEngine.getInstance().getLevel() == 7)
				{
					if (hasQuestItems(player, CONTAINED_LIFE_FORCE))
					{
						HellboundEngine.getInstance().updateTrust((int) getQuestItemsCount(player, CONTAINED_LIFE_FORCE) * 200, true);
						takeItems(player, CONTAINED_LIFE_FORCE, -1);
						htmltext = "32354-11e.htm";
					}
					else
					{
						htmltext = "32354-11f.htm";
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		switch (HellboundEngine.getInstance().getLevel())
		{
			case 1:
			{
				return "32354-01.htm";
			}
			case 2:
			case 3:
			{
				return "32354-01a.htm";
			}
			case 4:
			{
				return "32354-01e.htm";
			}
			case 5:
			{
				return "32354-01d.htm";
			}
			case 6:
			{
				return "32354-01b.htm";
			}
			case 7:
			{
				return "32354-01c.htm";
			}
			default:
			{
				return "32354-01f.htm";
			}
		}
	}
}
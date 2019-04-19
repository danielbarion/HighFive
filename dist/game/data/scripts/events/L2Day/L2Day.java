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
package events.L2Day;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.QuestItemHolder;
import com.l2jmobius.gameserver.model.quest.LongTimeEvent;

/**
 * L2 Day event AI.
 * @author Pandragon
 */
public final class L2Day extends LongTimeEvent
{
	// NPCs
	private static final Map<Integer, Integer> MANAGERS = new HashMap<>();
	{
		MANAGERS.put(31854, 7117); // Talking Island Village
		MANAGERS.put(31855, 7118); // Elven Village
		MANAGERS.put(31856, 7119); // Dark Elven Village
		MANAGERS.put(31857, 7121); // Dwarven Village
		MANAGERS.put(31858, 7120); // Orc Village
	}
	// Items
	private static final int A = 3875;
	private static final int C = 3876;
	private static final int E = 3877;
	private static final int F = 3878;
	private static final int G = 3879;
	private static final int H = 3880;
	private static final int I = 3881;
	private static final int L = 3882;
	private static final int N = 3883;
	private static final int O = 3884;
	private static final int R = 3885;
	private static final int S = 3886;
	private static final int T = 3887;
	private static final int II = 3888;
	// Rewards
	private static final QuestItemHolder[] L2_REWARDS =
	{
		new QuestItemHolder(10260, 90, 3), // Alacrity Juice
		new QuestItemHolder(10261, 85, 3), // Accuracy Juice
		new QuestItemHolder(10262, 80, 3), // Critical Hit Juice
		new QuestItemHolder(10263, 75, 3), // Critical Rate Juice
		new QuestItemHolder(10264, 70, 3), // Casting Spd. Juice
		new QuestItemHolder(10265, 65, 3), // Evasion Juice
		new QuestItemHolder(10266, 60, 3), // M. Atk. Juice
		new QuestItemHolder(10267, 55, 3), // Power Juice
		new QuestItemHolder(10268, 50, 3), // Speed Juice
		new QuestItemHolder(10269, 45, 3), // Defense Juice
		new QuestItemHolder(10270, 40, 3), // MP Consumption Juice
		new QuestItemHolder(9546, 37, 2), // Fire Stone
		new QuestItemHolder(9547, 34, 2), // Water Stone
		new QuestItemHolder(9548, 31, 2), // Earth Stone
		new QuestItemHolder(9549, 28, 2), // Wind Stone
		new QuestItemHolder(9550, 25, 2), // Dark Stone
		new QuestItemHolder(9551, 22, 2), // Holy Stone
		new QuestItemHolder(8947, 19, 1), // L2day - Rabbit Ears
		new QuestItemHolder(8948, 16, 1), // L2day - Little Angel Wings
		new QuestItemHolder(8949, 13, 1), // L2day - Fairy Antennae
		new QuestItemHolder(3959, 10, 2), // Blessed Scroll of Resurrection (Event)
		new QuestItemHolder(3958, 7, 2), // Blessed Scroll of Escape (Event)
		new QuestItemHolder(8752, 4, 2), // High-Grade Life Stone - Level 76
		new QuestItemHolder(8762, 1, 1), // Top-Grade Life Stone - Level 76
		new QuestItemHolder(6660, 0, 1), // Ring of Queen Ant
	};
	private static final QuestItemHolder[] NC_REWARDS =
	{
		new QuestItemHolder(10260, 90, 2), // Alacrity Juice
		new QuestItemHolder(10261, 85, 2), // Accuracy Juice
		new QuestItemHolder(10262, 80, 2), // Critical Hit Juice
		new QuestItemHolder(10263, 75, 2), // Critical Rate Juice
		new QuestItemHolder(10264, 70, 2), // Casting Spd. Juice
		new QuestItemHolder(10265, 65, 2), // Evasion Juice
		new QuestItemHolder(10266, 60, 2), // M. Atk. Juice
		new QuestItemHolder(10267, 55, 2), // Power Juice
		new QuestItemHolder(10268, 50, 2), // Speed Juice
		new QuestItemHolder(10269, 45, 2), // Defense Juice
		new QuestItemHolder(10270, 40, 2), // MP Consumption Juice
		new QuestItemHolder(9546, 37, 1), // Fire Stone
		new QuestItemHolder(9547, 34, 1), // Water Stone
		new QuestItemHolder(9548, 31, 1), // Earth Stone
		new QuestItemHolder(9549, 28, 1), // Wind Stone
		new QuestItemHolder(9550, 25, 1), // Dark Stone
		new QuestItemHolder(9551, 22, 1), // Holy Stone
		new QuestItemHolder(8948, 19, 1), // L2day - Little Angel Wings
		new QuestItemHolder(8949, 16, 1), // L2day - Fairy Antennae
		new QuestItemHolder(8950, 13, 1), // L2day - Feathered Hat
		new QuestItemHolder(3959, 10, 1), // Blessed Scroll of Resurrection (Event)
		new QuestItemHolder(3958, 7, 1), // Blessed Scroll of Escape (Event)
		new QuestItemHolder(8742, 4, 2), // Mid-Grade Life Stone - Level 76
		new QuestItemHolder(8752, 1, 1), // High-Grade Life Stone - Level 76
		new QuestItemHolder(6661, 0, 1), // Earring of Orfen
	};
	private static final QuestItemHolder[] CH_REWARDS =
	{
		new QuestItemHolder(10260, 90, 1), // Alacrity Juice
		new QuestItemHolder(10261, 85, 1), // Accuracy Juice
		new QuestItemHolder(10262, 80, 1), // Critical Hit Juice
		new QuestItemHolder(10263, 75, 1), // Critical Rate Juice
		new QuestItemHolder(10264, 70, 1), // Casting Spd. Juice
		new QuestItemHolder(10265, 65, 1), // Evasion Juice
		new QuestItemHolder(10266, 60, 1), // M. Atk. Juice
		new QuestItemHolder(10267, 55, 1), // Power Juice
		new QuestItemHolder(10268, 50, 1), // Speed Juice
		new QuestItemHolder(10269, 45, 1), // Defense Juice
		new QuestItemHolder(10270, 40, 1), // MP Consumption Juice
		new QuestItemHolder(9546, 37, 1), // Fire Stone
		new QuestItemHolder(9547, 34, 1), // Water Stone
		new QuestItemHolder(9548, 31, 1), // Earth Stone
		new QuestItemHolder(9549, 28, 1), // Wind Stone
		new QuestItemHolder(9550, 25, 1), // Dark Stone
		new QuestItemHolder(9551, 22, 1), // Holy Stone
		new QuestItemHolder(8949, 19, 1), // L2day - Fairy Antennae
		new QuestItemHolder(8950, 16, 1), // L2day - Feathered Hat
		new QuestItemHolder(8951, 13, 1), // L2day - Artisan's Goggles
		new QuestItemHolder(3959, 10, 1), // Blessed Scroll of Resurrection (Event)
		new QuestItemHolder(3958, 7, 1), // Blessed Scroll of Escape (Event)
		new QuestItemHolder(8742, 4, 1), // Mid-Grade Life Stone - Level 76
		new QuestItemHolder(8752, 1, 1), // High-Grade Life Stone - Level 76
		new QuestItemHolder(6662, 0, 1), // Ring of Core
	};
	
	private L2Day()
	{
		addStartNpc(MANAGERS.keySet());
		addFirstTalkId(MANAGERS.keySet());
		addTalkId(MANAGERS.keySet());
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		switch (event)
		{
			case "collect_l2":
			{
				if (hasQuestItems(player, L, I, N, E, A, G, II) && (getQuestItemsCount(player, E) > 1))
				{
					takeItems(player, 1, L, I, N, E, A, G, E, II);
					final int random = getRandom(100);
					if (random >= 95)
					{
						rewardItems(player, MANAGERS.get(npc.getId()), 2);
					}
					else
					{
						for (QuestItemHolder holder : L2_REWARDS)
						{
							if (random >= holder.getChance())
							{
								rewardItems(player, holder);
								break;
							}
						}
					}
					htmltext = "manager-1.htm";
				}
				else
				{
					htmltext = "manager-no.htm";
				}
				break;
			}
			case "collect_nc":
			{
				if (hasQuestItems(player, N, C, S, O, F, T))
				{
					takeItems(player, 1, N, C, S, O, F, T);
					final int random = getRandom(100);
					if (random >= 95)
					{
						rewardItems(player, MANAGERS.get(npc.getId()), 1);
					}
					else
					{
						for (QuestItemHolder holder : NC_REWARDS)
						{
							if (random >= holder.getChance())
							{
								rewardItems(player, holder);
								break;
							}
						}
					}
					htmltext = "manager-1.htm";
				}
				else
				{
					htmltext = "manager-no.htm";
				}
				break;
			}
			case "collect_ch":
			{
				if (hasQuestItems(player, C, H, R, O, N, I, L, E) && (getQuestItemsCount(player, C) > 1))
				{
					takeItems(player, 1, C, H, R, O, N, I, C, L, E);
					final int random = getRandom(100);
					if (random >= 95)
					{
						rewardItems(player, MANAGERS.get(npc.getId()), 1);
					}
					else
					{
						for (QuestItemHolder holder : CH_REWARDS)
						{
							if (random >= holder.getChance())
							{
								rewardItems(player, holder);
								break;
							}
						}
					}
					htmltext = "manager-1.htm";
				}
				else
				{
					htmltext = "manager-no.htm";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		return "manager-1.htm";
	}
	
	public static void main(String[] args)
	{
		new L2Day();
	}
}

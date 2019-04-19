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
package quests.Q00629_CleanUpTheSwampOfScreams;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Clean Up The Swamp Of Screams (629)
 * @author netvirus
 */
public final class Q00629_CleanUpTheSwampOfScreams extends Quest
{
	// NPC
	private static final int PIERCE = 31553;
	// Items
	private static final int TALON_OF_STAKATO = 7250;
	private static final int GOLDEN_RAM_COIN = 7251;
	// Misc
	private static final int REQUIRED_TALON_COUNT = 100;
	private static final int MIN_LVL = 66;
	// Mobs
	private static final Map<Integer, Double> MOBS_DROP_CHANCES = new HashMap<>();
	
	static
	{
		MOBS_DROP_CHANCES.put(21508, 0.599); // splinter_stakato
		MOBS_DROP_CHANCES.put(21509, 0.524); // splinter_stakato_worker
		MOBS_DROP_CHANCES.put(21510, 0.640); // splinter_stakato_soldier
		MOBS_DROP_CHANCES.put(21511, 0.830); // splinter_stakato_drone
		MOBS_DROP_CHANCES.put(21512, 0.970); // splinter_stakato_drone_a
		MOBS_DROP_CHANCES.put(21513, 0.682); // needle_stakato
		MOBS_DROP_CHANCES.put(21514, 0.595); // needle_stakato_worker
		MOBS_DROP_CHANCES.put(21515, 0.727); // needle_stakato_soldier
		MOBS_DROP_CHANCES.put(21516, 0.879); // needle_stakato_drone
		MOBS_DROP_CHANCES.put(21517, 0.999); // needle_stakato_drone_a
	}
	
	public Q00629_CleanUpTheSwampOfScreams()
	{
		super(629);
		addStartNpc(PIERCE);
		addTalkId(PIERCE);
		addKillId(MOBS_DROP_CHANCES.keySet());
		registerQuestItems(TALON_OF_STAKATO, GOLDEN_RAM_COIN);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "31553-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "31553-04.html":
			case "31553-06.html":
			{
				if (qs.isStarted())
				{
					htmltext = event;
				}
				break;
			}
			case "31553-07.html":
			{
				if (qs.isStarted() && (getQuestItemsCount(player, TALON_OF_STAKATO) >= REQUIRED_TALON_COUNT))
				{
					rewardItems(player, GOLDEN_RAM_COIN, 20);
					takeItems(player, TALON_OF_STAKATO, 100);
					htmltext = event;
				}
				else
				{
					htmltext = "31553-08.html";
				}
				break;
			}
			case "31553-09.html":
			{
				if (qs.isStarted())
				{
					qs.exitQuest(true, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, -1, 2, npc);
		if (qs != null)
		{
			giveItemRandomly(qs.getPlayer(), npc, TALON_OF_STAKATO, 1, 0, MOBS_DROP_CHANCES.get(npc.getId()), true);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isCreated())
		{
			htmltext = ((player.getLevel() >= MIN_LVL) ? "31553-01.htm" : "31553-02.htm");
		}
		else if (qs.isStarted())
		{
			htmltext = ((getQuestItemsCount(player, TALON_OF_STAKATO) >= REQUIRED_TALON_COUNT) ? "31553-04.html" : "31553-05.html");
		}
		return htmltext;
	}
}

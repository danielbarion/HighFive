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
package quests.Q00628_HuntGoldenRam;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemChanceHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Hunt of the Golden Ram Mercenary Force (628)
 * @author netvirus, Zoey76
 */
public final class Q00628_HuntGoldenRam extends Quest
{
	// NPCs
	private static final int KAHMAN = 31554;
	// Items
	private static final int GOLDEN_RAM_BADGE_RECRUIT = 7246;
	private static final int GOLDEN_RAM_BADGE_SOLDIER = 7247;
	private static final int SPLINTER_STAKATO_CHITIN = 7248;
	private static final int NEEDLE_STAKATO_CHITIN = 7249;
	// Misc
	private static final int REQUIRED_ITEM_COUNT = 100;
	private static final int MIN_LVL = 66;
	// Mobs
	private static final Map<Integer, ItemChanceHolder> MOBS_DROP_CHANCES = new HashMap<>();
	
	static
	{
		MOBS_DROP_CHANCES.put(21508, new ItemChanceHolder(SPLINTER_STAKATO_CHITIN, 0.500, 1)); // splinter_stakato
		MOBS_DROP_CHANCES.put(21509, new ItemChanceHolder(SPLINTER_STAKATO_CHITIN, 0.430, 1)); // splinter_stakato_worker
		MOBS_DROP_CHANCES.put(21510, new ItemChanceHolder(SPLINTER_STAKATO_CHITIN, 0.521, 1)); // splinter_stakato_soldier
		MOBS_DROP_CHANCES.put(21511, new ItemChanceHolder(SPLINTER_STAKATO_CHITIN, 0.575, 1)); // splinter_stakato_drone
		MOBS_DROP_CHANCES.put(21512, new ItemChanceHolder(SPLINTER_STAKATO_CHITIN, 0.746, 1)); // splinter_stakato_drone_a
		MOBS_DROP_CHANCES.put(21513, new ItemChanceHolder(NEEDLE_STAKATO_CHITIN, 0.500, 2)); // needle_stakato
		MOBS_DROP_CHANCES.put(21514, new ItemChanceHolder(NEEDLE_STAKATO_CHITIN, 0.430, 2)); // needle_stakato_worker
		MOBS_DROP_CHANCES.put(21515, new ItemChanceHolder(NEEDLE_STAKATO_CHITIN, 0.520, 2)); // needle_stakato_soldier
		MOBS_DROP_CHANCES.put(21516, new ItemChanceHolder(NEEDLE_STAKATO_CHITIN, 0.531, 2)); // needle_stakato_drone
		MOBS_DROP_CHANCES.put(21517, new ItemChanceHolder(NEEDLE_STAKATO_CHITIN, 0.744, 2)); // needle_stakato_drone_a
	}
	
	public Q00628_HuntGoldenRam()
	{
		super(628);
		addStartNpc(KAHMAN);
		addTalkId(KAHMAN);
		addKillId(MOBS_DROP_CHANCES.keySet());
		registerQuestItems(SPLINTER_STAKATO_CHITIN, NEEDLE_STAKATO_CHITIN);
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
			case "accept":
			{
				if (qs.isCreated() && (player.getLevel() >= MIN_LVL))
				{
					qs.startQuest();
					if (hasQuestItems(player, GOLDEN_RAM_BADGE_SOLDIER))
					{
						qs.setCond(3);
						htmltext = "31554-05.htm";
					}
					else if (hasQuestItems(player, GOLDEN_RAM_BADGE_RECRUIT))
					{
						qs.setCond(2);
						htmltext = "31554-04.htm";
					}
					else
					{
						htmltext = "31554-03.htm";
					}
				}
				break;
			}
			case "31554-08.html":
			{
				if (getQuestItemsCount(player, SPLINTER_STAKATO_CHITIN) >= REQUIRED_ITEM_COUNT)
				{
					giveItems(player, GOLDEN_RAM_BADGE_RECRUIT, 1);
					takeItems(player, SPLINTER_STAKATO_CHITIN, -1);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "31554-12.html":
			case "31554-13.html":
			{
				if (qs.isStarted())
				{
					htmltext = event;
				}
				break;
			}
			case "31554-14.html":
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
		final QuestState qs = getRandomPartyMemberState(killer, -1, 1, npc);
		if (qs != null)
		{
			final ItemChanceHolder item = MOBS_DROP_CHANCES.get(npc.getId());
			if ((item.getCount() <= qs.getCond()) && !qs.isCond(3))
			{
				giveItemRandomly(qs.getPlayer(), npc, item.getId(), 1, REQUIRED_ITEM_COUNT, item.getChance(), true);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = ((player.getLevel() >= MIN_LVL) ? "31554-01.htm" : "31554-02.htm");
				break;
			}
			case State.STARTED:
			{
				final long itemCountSplinter = getQuestItemsCount(player, SPLINTER_STAKATO_CHITIN);
				final long itemCountNeedle = getQuestItemsCount(player, NEEDLE_STAKATO_CHITIN);
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = ((itemCountSplinter >= REQUIRED_ITEM_COUNT) ? "31554-07.html" : "31554-06.html");
						break;
					}
					case 2:
					{
						if (hasQuestItems(player, GOLDEN_RAM_BADGE_RECRUIT))
						{
							if ((itemCountSplinter >= REQUIRED_ITEM_COUNT) && (itemCountNeedle >= REQUIRED_ITEM_COUNT))
							{
								takeItems(player, GOLDEN_RAM_BADGE_RECRUIT, -1);
								takeItems(player, SPLINTER_STAKATO_CHITIN, -1);
								takeItems(player, NEEDLE_STAKATO_CHITIN, -1);
								giveItems(player, GOLDEN_RAM_BADGE_SOLDIER, 1);
								qs.setCond(3, true);
								htmltext = "31554-10.html";
							}
							else
							{
								htmltext = "31554-09.html";
							}
						}
						else
						{
							qs.setCond(1);
							htmltext = ((itemCountSplinter >= REQUIRED_ITEM_COUNT) ? "31554-07.html" : "31554-06.html");
						}
						break;
					}
					case 3:
					{
						if (hasQuestItems(player, GOLDEN_RAM_BADGE_SOLDIER))
						{
							htmltext = "31554-11.html";
						}
						else
						{
							qs.setCond(1);
							htmltext = ((itemCountSplinter >= REQUIRED_ITEM_COUNT) ? "31554-07.html" : "31554-06.html");
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

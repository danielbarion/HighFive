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
package quests.Q00299_GatherIngredientsForPie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.QuestItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Gather Ingredients for Pie (299)
 * @author xban1x
 */
public final class Q00299_GatherIngredientsForPie extends Quest
{
	// NPCs
	private static final int LARS = 30063;
	private static final int BRIGHT = 30466;
	private static final int EMILLY = 30620;
	// Monsters
	private static final Map<Integer, Integer> MONSTERS_CHANCES = new HashMap<>(2);
	// Items
	private static final int FRUIT_BASKET = 7136;
	private static final int AVELLAN_SPICE = 7137;
	private static final int HONEY_POUCH = 7138;
	// Rewards
	private static final List<QuestItemHolder> REWARDS = new ArrayList<>(5);
	// Misc
	private static final int MIN_LVL = 34;
	static
	{
		MONSTERS_CHANCES.put(20934, 700); // Wasp Worker
		MONSTERS_CHANCES.put(20935, 770); // Wasp Leader
		REWARDS.add(new QuestItemHolder(57, 400, 2500)); // Adena
		REWARDS.add(new QuestItemHolder(1865, 550, 50)); // Varnish
		REWARDS.add(new QuestItemHolder(1870, 700, 50)); // Coal
		REWARDS.add(new QuestItemHolder(1869, 850, 50)); // Iron Ore
		REWARDS.add(new QuestItemHolder(1871, 1000, 50)); // Charcoal
	}
	
	public Q00299_GatherIngredientsForPie()
	{
		super(299);
		addStartNpc(EMILLY);
		addTalkId(LARS, BRIGHT, EMILLY);
		addKillId(MONSTERS_CHANCES.keySet());
		registerQuestItems(FRUIT_BASKET, HONEY_POUCH, AVELLAN_SPICE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String html = null;
		if (qs == null)
		{
			return html;
		}
		switch (event)
		{
			case "30063-02.html":
			{
				if (qs.isCond(3))
				{
					giveItems(player, AVELLAN_SPICE, 1);
					qs.setCond(4, true);
					html = event;
				}
				break;
			}
			case "30466-02.html":
			{
				if (qs.isCond(5))
				{
					giveItems(player, FRUIT_BASKET, 1);
					qs.setCond(6, true);
					html = event;
				}
				break;
			}
			case "30620-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					html = event;
				}
				break;
			}
			case "30620-06.html":
			{
				if (qs.isCond(2) && (getQuestItemsCount(player, HONEY_POUCH) >= 100))
				{
					takeItems(player, HONEY_POUCH, -1);
					qs.setCond(3, true);
					html = event;
				}
				else
				{
					html = "30620-07.html";
				}
				break;
			}
			case "30620-10.html":
			{
				if (qs.isCond(4) && hasQuestItems(player, AVELLAN_SPICE))
				{
					takeItems(player, AVELLAN_SPICE, -1);
					qs.setCond(5, true);
					html = event;
				}
				else
				{
					html = "30620-11.html";
				}
				break;
			}
			case "30620-14.html":
			{
				if (qs.isCond(6) && hasQuestItems(player, FRUIT_BASKET))
				{
					takeItems(player, FRUIT_BASKET, -1);
					final int chance = getRandom(1000);
					for (QuestItemHolder holder : REWARDS)
					{
						if (holder.getChance() > chance)
						{
							rewardItems(player, holder);
							break;
						}
					}
					qs.exitQuest(true, true);
					html = event;
				}
				else
				{
					html = "30620-15.html";
				}
				break;
			}
		}
		return html;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, 1, 3, npc);
		if ((qs != null) && (getRandom(1000) < MONSTERS_CHANCES.get(npc.getId())) && (getQuestItemsCount(killer, HONEY_POUCH) < 100))
		{
			if (giveItemRandomly(killer, npc, HONEY_POUCH, 1, 2, 100, 1, true))
			{
				qs.setCond(2);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String html = getNoQuestMsg(talker);
		switch (npc.getId())
		{
			case LARS:
			{
				switch (qs.getCond())
				{
					case 3:
					{
						html = "30063-01.html";
						break;
					}
					case 4:
					{
						html = "30063-03.html";
						break;
					}
				}
				break;
			}
			case BRIGHT:
			{
				switch (qs.getCond())
				{
					case 5:
					{
						html = "30466-01.html";
						break;
					}
					case 6:
					{
						html = "30466-03.html";
						break;
					}
				}
				break;
			}
			case EMILLY:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						html = (talker.getLevel() >= MIN_LVL) ? "30620-01.htm" : "30620-02.htm";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								html = "30620-05.html";
								break;
							}
							case 2:
							{
								if (getQuestItemsCount(talker, HONEY_POUCH) >= 100)
								{
									html = "30620-04.html";
								}
								break;
							}
							case 3:
							{
								html = "30620-08.html";
								break;
							}
							case 4:
							{
								if (hasQuestItems(talker, AVELLAN_SPICE))
								{
									html = "30620-09.html";
								}
								break;
							}
							case 5:
							{
								html = "30620-12.html";
								break;
							}
							case 6:
							{
								if (hasQuestItems(talker, FRUIT_BASKET))
								{
									html = "30620-13.html";
								}
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
		return html;
	}
}

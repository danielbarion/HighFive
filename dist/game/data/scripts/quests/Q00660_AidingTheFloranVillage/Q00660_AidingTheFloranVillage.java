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
package quests.Q00660_AidingTheFloranVillage;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemChanceHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Aiding the Floran Village (660)
 * @author Zoey76
 */
public final class Q00660_AidingTheFloranVillage extends Quest
{
	// NPC
	private static final int ALEX = 30291;
	private static final int MARIA = 30608;
	// Items
	private static final int SCROLL_ENCHANT_WEAPON_D_GRADE = 955;
	private static final int SCROLL_ENCHANT_ARMOR_D_GRADE = 956;
	private static final int WATCHING_EYES = 8074;
	private static final int ROUGHLY_HEWN_ROCK_GOLEM_SHARD = 8075;
	private static final int DELU_LIZARDMANS_SCALE = 8076;
	// Misc
	private static final int MIN_LEVEL = 30;
	private static final int ADENA_REWARD_1 = 13000;
	private static final int ADENA_REWARD_2 = 1000;
	private static final int ADENA_REWARD_3 = 20000;
	private static final int ADENA_REWARD_4 = 2000;
	private static final int ADENA_REWARD_5 = 45000;
	private static final int ADENA_REWARD_6 = 5000;
	private static final int DELU_LIZARDMAN_COMMANDER_DOUBLE_ITEM_CHANCE = 33;
	// Monsters
	private static final int DELU_LIZARDMAN_COMMANDER = 21107; // Delu Lizardman Commander
	
	private static final Map<Integer, ItemChanceHolder> MONSTERS = new HashMap<>();
	static
	{
		MONSTERS.put(21102, new ItemChanceHolder(WATCHING_EYES, 0.500)); // Watchman of the Plains
		MONSTERS.put(21106, new ItemChanceHolder(WATCHING_EYES, 0.630)); // Cursed Seer
		MONSTERS.put(21103, new ItemChanceHolder(ROUGHLY_HEWN_ROCK_GOLEM_SHARD, 0.520)); // Roughly Hewn Rock Golem
		MONSTERS.put(20781, new ItemChanceHolder(DELU_LIZARDMANS_SCALE, 0.650)); // Delu Lizardman Shaman
		MONSTERS.put(21104, new ItemChanceHolder(DELU_LIZARDMANS_SCALE, 0.650)); // Delu Lizardman Supplier
		MONSTERS.put(21105, new ItemChanceHolder(DELU_LIZARDMANS_SCALE, 0.750)); // Delu Lizardman Special Agent
	}
	
	public Q00660_AidingTheFloranVillage()
	{
		super(660);
		addStartNpc(MARIA, ALEX);
		addTalkId(MARIA, ALEX);
		addKillId(MONSTERS.keySet());
		addKillId(DELU_LIZARDMAN_COMMANDER);
		registerQuestItems(WATCHING_EYES, ROUGHLY_HEWN_ROCK_GOLEM_SHARD, DELU_LIZARDMANS_SCALE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "30608-06.htm":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					qs.startQuest();
					htmltext = event;
				}
				else
				{
					htmltext = "30608-06a.htm";
				}
				break;
			}
			case "30608-02.htm":
			case "30608-03.html":
			case "30291-07.html":
			case "30291-09.html":
			case "30291-10.html":
			case "30291-14.html":
			case "30291-18.html":
			{
				htmltext = event;
				break;
			}
			case "30291-03.htm":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					if (qs.isCreated())
					{
						qs.setState(State.STARTED);
						qs.setCond(2);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ACCEPT);
					}
					htmltext = event;
				}
				else
				{
					htmltext = "30291-02.htm";
				}
				break;
			}
			case "30291-06.html":
			{
				final long itemCount = getQuestItemsCount(player, WATCHING_EYES) + getQuestItemsCount(player, ROUGHLY_HEWN_ROCK_GOLEM_SHARD) + getQuestItemsCount(player, DELU_LIZARDMANS_SCALE);
				if (itemCount > 0)
				{
					giveAdena(player, itemCount * 100, true);
					takeItems(player, -1, WATCHING_EYES, ROUGHLY_HEWN_ROCK_GOLEM_SHARD, DELU_LIZARDMANS_SCALE);
					htmltext = event;
				}
				else
				{
					htmltext = "30291-08.html";
				}
				break;
			}
			case "30291-08a.html":
			{
				qs.exitQuest(true, true);
				takeItems(player, -1, WATCHING_EYES, ROUGHLY_HEWN_ROCK_GOLEM_SHARD, DELU_LIZARDMANS_SCALE);
				htmltext = event;
				break;
			}
			case "30291-12.html":
			{
				final long itemCount1 = getQuestItemsCount(player, WATCHING_EYES);
				final long itemCount2 = getQuestItemsCount(player, ROUGHLY_HEWN_ROCK_GOLEM_SHARD);
				final long itemCount3 = getQuestItemsCount(player, DELU_LIZARDMANS_SCALE);
				final long itemCount = itemCount1 + itemCount2 + itemCount3;
				if (itemCount < 100)
				{
					htmltext = "30291-11.html";
				}
				else
				{
					tradeItems(player, 100, itemCount1, itemCount2, itemCount3);
					
					if (getRandom(99) > 50)
					{
						giveItems(player, SCROLL_ENCHANT_ARMOR_D_GRADE, 1);
						giveAdena(player, ADENA_REWARD_1, true);
						htmltext = event;
					}
					else
					{
						giveAdena(player, ADENA_REWARD_2, true);
						htmltext = "30291-13.html";
					}
				}
				break;
			}
			case "30291-16.html":
			{
				final long itemCount1 = getQuestItemsCount(player, WATCHING_EYES);
				final long itemCount2 = getQuestItemsCount(player, ROUGHLY_HEWN_ROCK_GOLEM_SHARD);
				final long itemCount3 = getQuestItemsCount(player, DELU_LIZARDMANS_SCALE);
				final long itemCount = itemCount1 + itemCount2 + itemCount3;
				if (itemCount < 200)
				{
					htmltext = "30291-15.html";
				}
				else
				{
					tradeItems(player, 200, itemCount1, itemCount2, itemCount3);
					
					if (getRandom(100) >= 50)
					{
						if (getRandom(2) == 0)
						{
							giveItems(player, SCROLL_ENCHANT_ARMOR_D_GRADE, 1);
							giveAdena(player, ADENA_REWARD_3, true);
						}
						else
						{
							giveItems(player, SCROLL_ENCHANT_WEAPON_D_GRADE, 1);
						}
						htmltext = event;
					}
					else
					{
						giveAdena(player, ADENA_REWARD_4, true);
						htmltext = "30291-17.html";
					}
				}
				break;
			}
			case "30291-20.html":
			{
				final long itemCount1 = getQuestItemsCount(player, WATCHING_EYES);
				final long itemCount2 = getQuestItemsCount(player, ROUGHLY_HEWN_ROCK_GOLEM_SHARD);
				final long itemCount3 = getQuestItemsCount(player, DELU_LIZARDMANS_SCALE);
				final long itemCount = itemCount1 + itemCount2 + itemCount3;
				if (itemCount < 500)
				{
					htmltext = "30291-19.html";
				}
				else
				{
					tradeItems(player, 500, itemCount1, itemCount2, itemCount3);
					
					if (getRandom(100) >= 50)
					{
						giveItems(player, SCROLL_ENCHANT_ARMOR_D_GRADE, 1);
						giveAdena(player, ADENA_REWARD_5, true);
						htmltext = event;
					}
					else
					{
						giveAdena(player, ADENA_REWARD_6, true);
						htmltext = "30291-21.html";
					}
				}
				break;
			}
			case "30291-22.html":
			{
				final long itemCount = getQuestItemsCount(player, WATCHING_EYES) + getQuestItemsCount(player, ROUGHLY_HEWN_ROCK_GOLEM_SHARD) + getQuestItemsCount(player, DELU_LIZARDMANS_SCALE);
				if (itemCount <= 0)
				{
					htmltext = "30291-23.html";
				}
				else
				{
					giveAdena(player, itemCount * 100, true);
					htmltext = event;
				}
				
				takeItems(player, -1, WATCHING_EYES, ROUGHLY_HEWN_ROCK_GOLEM_SHARD, DELU_LIZARDMANS_SCALE);
				qs.exitQuest(true, true);
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(player, 2, 2, npc);
		if (qs != null)
		{
			final ItemChanceHolder item = MONSTERS.get(npc.getId());
			if (item != null)
			{
				giveItemRandomly(player, npc, item.getId(), item.getCount(), 0, item.getChance(), true);
			}
			else
			{
				if (getRandom(100) < DELU_LIZARDMAN_COMMANDER_DOUBLE_ITEM_CHANCE)
				{
					giveItems(player, DELU_LIZARDMANS_SCALE, 2);
				}
				else
				{
					giveItems(player, DELU_LIZARDMANS_SCALE, 1);
				}
				playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isCreated())
		{
			switch (npc.getId())
			{
				case MARIA:
				{
					htmltext = player.getLevel() >= MIN_LEVEL ? "30608-01.htm" : "30608-04.html";
					break;
				}
				case ALEX:
				{
					htmltext = player.getLevel() >= MIN_LEVEL ? "30291-01.htm" : "30291-02.htm";
					break;
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case MARIA:
				{
					htmltext = "30608-05.html";
					break;
				}
				case ALEX:
				{
					switch (qs.getCond())
					{
						case 1:
						{
							// Quest started with Maria.
							qs.setCond(2, true);
							htmltext = "30291-04.html";
							break;
						}
						case 2:
						{
							htmltext = "30291-05.html";
							break;
						}
					}
					break;
				}
			}
		}
		return htmltext;
	}
	
	private static void tradeItems(L2PcInstance player, long required, long itemCount1, long itemCount2, long itemCount3)
	{
		if (itemCount1 < required)
		{
			takeItems(player, WATCHING_EYES, itemCount1);
			required -= itemCount1;
		}
		else
		{
			takeItems(player, WATCHING_EYES, required);
			required = 0;
		}
		
		if (itemCount2 < required)
		{
			takeItems(player, ROUGHLY_HEWN_ROCK_GOLEM_SHARD, itemCount2);
			required -= itemCount2;
		}
		else
		{
			takeItems(player, ROUGHLY_HEWN_ROCK_GOLEM_SHARD, required);
			required = 0;
		}
		
		if (itemCount3 < required)
		{
			takeItems(player, DELU_LIZARDMANS_SCALE, itemCount3);
		}
		else
		{
			takeItems(player, DELU_LIZARDMANS_SCALE, required);
		}
	}
}

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
package quests.Q00383_TreasureHunt;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Treasure Hunt (383)
 * @author Adry_85
 */
public final class Q00383_TreasureHunt extends Quest
{
	// NPCs
	private static final int ESPEN = 30890;
	private static final int PIRATES_CHEST = 31148;
	// Items
	private static final int THIEF_KEY = 1661;
	private static final int PIRATES_TREASURE_MAP = 5915;
	// Misc
	private static final int MIN_LEVEL = 42;
	// Rewards
	private static final ItemHolder SCROLL_ENCHANT_ARMOR_C = new ItemHolder(952, 1);
	private static final ItemHolder SCROLL_ENCHANT_ARMOR_D = new ItemHolder(956, 1);
	private static final ItemHolder EMERALD = new ItemHolder(1337, 1);
	private static final ItemHolder BLUE_ONYX = new ItemHolder(1338, 2);
	private static final ItemHolder ONYX = new ItemHolder(1339, 2);
	private static final ItemHolder MITHRIL_GLOVES = new ItemHolder(2450, 1);
	private static final ItemHolder SAGES_WORN_GLOVES = new ItemHolder(2451, 1);
	private static final ItemHolder MOONSTONE = new ItemHolder(3447, 2);
	private static final ItemHolder ALEXANDRITE = new ItemHolder(3450, 1);
	private static final ItemHolder FIRE_EMERALD = new ItemHolder(3453, 1);
	private static final ItemHolder IMPERIAL_DIAMOND = new ItemHolder(3456, 1);
	private static final ItemHolder MUSICAL_SCORE_THEME_OF_LOVE = new ItemHolder(4408, 1);
	private static final ItemHolder MUSICAL_SCORE_THEME_OF_BATTLE = new ItemHolder(4409, 1);
	private static final ItemHolder MUSICAL_SCORE_THEME_OF_CELEBRATION = new ItemHolder(4418, 1);
	private static final ItemHolder MUSICAL_SCORE_THEME_OF_COMEDY = new ItemHolder(4419, 1);
	private static final ItemHolder DYE_S1C3_C = new ItemHolder(4481, 1); // Greater Dye of STR <Str+1 Con-3>
	private static final ItemHolder DYE_S1D3_C = new ItemHolder(4482, 1); // Greater Dye of STR <Str+1 Dex-3>
	private static final ItemHolder DYE_C1S3_C = new ItemHolder(4483, 1); // Greater Dye of CON<Con+1 Str-3>
	private static final ItemHolder DYE_C1C3_C = new ItemHolder(4484, 1); // Greater Dye of CON<Con+1 Dex-3>
	private static final ItemHolder DYE_D1S3_C = new ItemHolder(4485, 1); // Greater Dye of DEX <Dex+1 Str-3>
	private static final ItemHolder DYE_D1C3_C = new ItemHolder(4486, 1); // Greater Dye of DEX <Dex+1 Con-3>
	private static final ItemHolder DYE_I1M3_C = new ItemHolder(4487, 1); // Greater Dye of INT <Int+1 Men-3>
	private static final ItemHolder DYE_I1W3_C = new ItemHolder(4488, 1); // Greater Dye of INT <Int+1 Wit-3>
	private static final ItemHolder DYE_M1I3_C = new ItemHolder(4489, 1); // Greater Dye of MEN <Men+1 Int-3>
	private static final ItemHolder DYE_M1W3_C = new ItemHolder(4490, 1); // Greater Dye of MEN <Men+1 Wit-3>
	private static final ItemHolder DYE_W1I3_C = new ItemHolder(4491, 1); // Greater Dye of WIT <Wit+1 Int-3>
	private static final ItemHolder DYE_W1M3_C = new ItemHolder(4492, 1); // Greater Dye of WIT <Wit+1 Men-3>
	
	public Q00383_TreasureHunt()
	{
		super(383);
		addStartNpc(ESPEN);
		addTalkId(ESPEN, PIRATES_CHEST);
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
			case "30890-04.htm":
			{
				htmltext = event;
				break;
			}
			case "30890-05.htm":
			{
				if (hasQuestItems(player, PIRATES_TREASURE_MAP))
				{
					giveAdena(player, 1000, false);
					takeItems(player, PIRATES_TREASURE_MAP, -1);
					htmltext = event;
				}
				break;
			}
			case "30890-06.htm":
			{
				htmltext = (hasQuestItems(player, PIRATES_TREASURE_MAP)) ? event : "30890-12.html";
				break;
			}
			case "30890-07.htm":
			{
				if (hasQuestItems(player, PIRATES_TREASURE_MAP))
				{
					qs.startQuest();
					takeItems(player, PIRATES_TREASURE_MAP, -1);
					htmltext = event;
				}
				break;
			}
			case "30890-08.html":
			case "30890-09.html":
			case "30890-10.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
			case "30890-11.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "31148-02.html":
			{
				if (qs.isCond(2))
				{
					if (hasQuestItems(player, THIEF_KEY))
					{
						takeItems(player, THIEF_KEY, -1);
						qs.exitQuest(true, true);
						htmltext = event;
						
						int bonus = 0;
						int random = getRandom(100);
						
						if (random < 5)
						{
							rewardItems(player, MITHRIL_GLOVES);
						}
						else if (random < 6)
						{
							rewardItems(player, SAGES_WORN_GLOVES);
						}
						else if (random < 18)
						{
							rewardItems(player, SCROLL_ENCHANT_ARMOR_D);
						}
						else if (random < 28)
						{
							rewardItems(player, SCROLL_ENCHANT_ARMOR_C);
						}
						else
						{
							bonus += 500;
						}
						
						random = getRandom(1000);
						
						if (random < 25)
						{
							rewardItems(player, DYE_S1C3_C);
						}
						else if (random < 50)
						{
							rewardItems(player, DYE_S1D3_C);
						}
						else if (random < 75)
						{
							rewardItems(player, DYE_C1S3_C);
						}
						else if (random < 100)
						{
							rewardItems(player, DYE_C1C3_C);
						}
						else if (random < 125)
						{
							rewardItems(player, DYE_D1S3_C);
						}
						else if (random < 150)
						{
							rewardItems(player, DYE_D1C3_C);
						}
						else if (random < 175)
						{
							rewardItems(player, DYE_I1M3_C);
						}
						else if (random < 200)
						{
							rewardItems(player, DYE_I1W3_C);
						}
						else if (random < 225)
						{
							rewardItems(player, DYE_M1I3_C);
						}
						else if (random < 250)
						{
							rewardItems(player, DYE_M1W3_C);
						}
						else if (random < 275)
						{
							rewardItems(player, DYE_W1I3_C);
						}
						else if (random < 300)
						{
							rewardItems(player, DYE_W1M3_C);
						}
						else
						{
							bonus += 300;
						}
						
						random = getRandom(100);
						
						if (random < 4)
						{
							rewardItems(player, EMERALD);
						}
						else if (random < 8)
						{
							rewardItems(player, BLUE_ONYX);
						}
						else if (random < 12)
						{
							rewardItems(player, ONYX);
						}
						else if (random < 16)
						{
							rewardItems(player, MOONSTONE);
						}
						else if (random < 20)
						{
							rewardItems(player, ALEXANDRITE);
						}
						else if (random < 25)
						{
							rewardItems(player, FIRE_EMERALD);
						}
						else if (random < 27)
						{
							rewardItems(player, IMPERIAL_DIAMOND);
						}
						else
						{
							bonus += 500;
						}
						
						random = getRandom(100);
						
						if (random < 20)
						{
							rewardItems(player, MUSICAL_SCORE_THEME_OF_LOVE);
						}
						else if (random < 40)
						{
							rewardItems(player, MUSICAL_SCORE_THEME_OF_BATTLE);
						}
						else if (random < 60)
						{
							rewardItems(player, MUSICAL_SCORE_THEME_OF_CELEBRATION);
						}
						else if (random < 80)
						{
							rewardItems(player, MUSICAL_SCORE_THEME_OF_COMEDY);
						}
						else
						{
							bonus += 500;
						}
						
						giveAdena(player, bonus, true);
					}
					else
					{
						htmltext = "31148-03.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (player.getLevel() < MIN_LEVEL)
			{
				htmltext = "30890-01.html";
			}
			else if (!hasQuestItems(player, PIRATES_TREASURE_MAP))
			{
				htmltext = "30890-02.html";
			}
			else
			{
				htmltext = "30890-03.htm";
			}
		}
		else if (qs.isStarted())
		{
			if (npc.getId() == ESPEN)
			{
				if (qs.isCond(1))
				{
					htmltext = "30890-13.html";
				}
				else if (qs.isCond(2))
				{
					htmltext = "30890-14.html";
				}
			}
			else
			{
				if (qs.isCond(2))
				{
					htmltext = "31148-01.html";
				}
			}
		}
		return htmltext;
	}
}

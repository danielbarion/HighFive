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
package quests.Q00663_SeductiveWhispers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.util.Util;

/**
 * Seductive Whispers (663)
 * @author Zoey76
 * @since 2.6.0.0
 */
public class Q00663_SeductiveWhispers extends Quest
{
	// NPCs
	private static final int WILBERT = 30846;
	// Misc
	private static final int MIN_LEVEL = 50;
	// Quest items
	private static final int SPIRIT_BEAD = 8766;
	// Rewards
	private static final ItemHolder SCROLL_ENCHANT_WEAPON_A_GRADE = new ItemHolder(729, 1);
	private static final ItemHolder SCROLL_ENCHANT_ARMOR_A_GRADE = new ItemHolder(730, 2);
	private static final ItemHolder SCROLL_ENCHANT_WEAPON_B_GRADE = new ItemHolder(947, 2);
	private static final ItemHolder SCROLL_ENCHANT_ARMOR_B_GRADE = new ItemHolder(948, 2);
	private static final ItemHolder SCROLL_ENCHANT_WEAPON_C_GRADE = new ItemHolder(951, 1);
	private static final ItemHolder SCROLL_ENCHANT_WEAPON_D_GRADE = new ItemHolder(955, 1);
	private static final ItemHolder RECIPE_GREAT_SWORD_60 = new ItemHolder(4963, 1);
	private static final ItemHolder RECIPE_HEAVY_WAR_AXE_60 = new ItemHolder(4964, 1);
	private static final ItemHolder RECIPE_SPRITES_STAFF_60 = new ItemHolder(4965, 1);
	private static final ItemHolder RECIPE_KESHANBERK_60 = new ItemHolder(4966, 1);
	private static final ItemHolder RECIPE_SWORD_OF_VALHALLA_60 = new ItemHolder(4967, 1);
	private static final ItemHolder RECIPE_KRIS_60 = new ItemHolder(4968, 1);
	private static final ItemHolder RECIPE_HELL_KNIFE_60 = new ItemHolder(4969, 1);
	private static final ItemHolder RECIPE_ARTHRO_NAIL_60 = new ItemHolder(4970, 1);
	private static final ItemHolder RECIPE_DARK_ELVEN_LONG_BOW_60 = new ItemHolder(4971, 1);
	private static final ItemHolder RECIPE_GREAT_AXE_60 = new ItemHolder(4972, 1);
	private static final ItemHolder RECIPE_SWORD_OF_DAMASCUS_60 = new ItemHolder(5000, 1);
	private static final ItemHolder RECIPE_LANCE_60 = new ItemHolder(5001, 1);
	private static final ItemHolder RECIPE_DEADMANS_GLORY_60 = new ItemHolder(5002, 1);
	private static final ItemHolder RECIPE_ART_OF_BATTLE_AXE_60 = new ItemHolder(5003, 1);
	private static final ItemHolder RECIPE_TAFF_OF_EVIL_SPIRITS_60 = new ItemHolder(5004, 1);
	private static final ItemHolder RECIPE_DEMONS_DAGGER_60 = new ItemHolder(5005, 1);
	private static final ItemHolder RECIPE_BELLION_CESTUS_60 = new ItemHolder(5006, 1);
	private static final ItemHolder RECIPE_BOW_OF_PERIL_60 = new ItemHolder(5007, 1);
	private static final ItemHolder GREAT_SWORD_BLADE = new ItemHolder(4104, 12);
	private static final ItemHolder GREAT_AXE_HEAD = new ItemHolder(4113, 12);
	private static final ItemHolder DARK_ELVEN_LONGBOW_SHAFT = new ItemHolder(4112, 12);
	private static final ItemHolder SWORD_OF_VALHALLA_BLADE = new ItemHolder(4108, 12);
	private static final ItemHolder ARTHRO_NAIL_BLADE = new ItemHolder(4111, 12);
	private static final ItemHolder SPRITES_STAFF_HEAD = new ItemHolder(4104, 12);
	private static final ItemHolder KRIS_EDGE = new ItemHolder(4109, 12);
	private static final ItemHolder KESHANBERK_BLADE = new ItemHolder(4107, 12);
	private static final ItemHolder HEAVY_WAR_AXE_HEAD = new ItemHolder(4105, 12);
	private static final ItemHolder HELL_KNIFE_EDGE = new ItemHolder(4110, 12);
	private static final ItemHolder SWORD_OF_DAMASCUS_BLADE = new ItemHolder(4114, 13);
	private static final ItemHolder LANCE_BLADE = new ItemHolder(4115, 13);
	private static final ItemHolder BELLION_CESTUS_EDGE = new ItemHolder(4120, 13);
	private static final ItemHolder EVIL_SPIRIT_HEAD = new ItemHolder(4118, 13);
	private static final ItemHolder DEADMANS_GLORY_STONE = new ItemHolder(4116, 13);
	private static final ItemHolder ART_OF_BATTLE_AXE_BLADE = new ItemHolder(4117, 13);
	private static final ItemHolder DEMONS_DAGGER_EDGE = new ItemHolder(4119, 13);
	private static final ItemHolder BOW_OF_PERIL_SHAFT = new ItemHolder(4121, 13);
	// Monsters
	private static final int SPITEFUL_SOUL_LEADER = 20974;
	private static final int SPITEFUL_SOUL_LEADER_CHANCE = 100;
	private static final Map<Integer, Integer> MONSTERS = new HashMap<>();
	
	static
	{
		MONSTERS.put(20674, 807);
		MONSTERS.put(20678, 372);
		MONSTERS.put(20954, 460);
		MONSTERS.put(20674, 537);
		MONSTERS.put(20956, 540);
		MONSTERS.put(20957, 565);
		MONSTERS.put(20958, 425);
		MONSTERS.put(20959, 682);
		MONSTERS.put(20960, 372);
		MONSTERS.put(20961, 547);
		MONSTERS.put(20962, 522);
		MONSTERS.put(20963, 498);
		MONSTERS.put(20975, 975);
		MONSTERS.put(20976, 825);
		MONSTERS.put(20996, 385);
		MONSTERS.put(20997, 342);
		MONSTERS.put(20998, 377);
		MONSTERS.put(20999, 450);
		MONSTERS.put(21000, 395);
		MONSTERS.put(21001, 535);
		MONSTERS.put(21002, 472);
		MONSTERS.put(21006, 502);
		MONSTERS.put(21007, 540);
		MONSTERS.put(21008, 692);
		MONSTERS.put(21009, 740);
		MONSTERS.put(21010, 595);
	}
	
	public Q00663_SeductiveWhispers()
	{
		super(663);
		addStartNpc(WILBERT);
		addTalkId(WILBERT);
		addKillId(MONSTERS.keySet());
		addKillId(SPITEFUL_SOUL_LEADER);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = player.getLevel() < MIN_LEVEL ? "30846-02.html" : "30846-01.htm";
				break;
			}
			case State.STARTED:
			{
				if ((qs.getMemoState() < 4) && (qs.getMemoState() >= 1))
				{
					if (hasQuestItems(player, SPIRIT_BEAD))
					{
						htmltext = "30846-05.html";
					}
					else
					{
						htmltext = "30846-04.html";
					}
				}
				
				if ((qs.getMemoState() / 1000) == 0)
				{
					switch (qs.getMemoState() % 10)
					{
						case 4:
						{
							htmltext = "30846-05a.html";
							break;
						}
						case 5:
						{
							htmltext = "30846-11.html";
							break;
						}
						case 6:
						{
							htmltext = "30846-15.html";
							break;
						}
						case 7:
						{
							if (((qs.getMemoState() % 100) / 10) >= 7)
							{
								qs.setMemoState(1);
								giveAdena(player, 2384000, true);
								giveItems(player, SCROLL_ENCHANT_WEAPON_A_GRADE);
								giveItems(player, SCROLL_ENCHANT_ARMOR_A_GRADE);
								htmltext = "30846-17.html";
							}
							else
							{
								final int winCount = (qs.getMemoState() / 10) + 1;
								htmltext = getHtml(player, "30846-16.html", 0, 0, winCount, 0);
							}
							break;
						}
					}
				}
				else if (qs.isMemoState(1005))
				{
					htmltext = "30846-23.html";
				}
				else if (qs.isMemoState(1006))
				{
					htmltext = "30846-26.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = getNoQuestMsg(player);
		
		switch (event)
		{
			case "30846-01a.htm":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					htmltext = event;
				}
				break;
			}
			case "30846-03.htm":
			{
				if (qs.isCreated() && (player.getLevel() >= MIN_LEVEL))
				{
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = event;
				}
				break;
			}
			case "30846-06.html":
			case "30846-07.html":
			case "30846-08.html":
			{
				htmltext = event;
				break;
			}
			case "30846-09.html":
			{
				if (qs.isStarted() && ((qs.getMemoState() % 10) <= 4))
				{
					final int memoState = qs.getMemoState() / 10;
					if (memoState < 1)
					{
						if (getQuestItemsCount(player, SPIRIT_BEAD) >= 50)
						{
							takeItems(player, SPIRIT_BEAD, 50);
							qs.setMemoState(5);
							qs.setMemoStateEx(1, 0);
							htmltext = event;
						}
						else
						{
							htmltext = "30846-10.html";
						}
					}
					else
					{
						qs.setMemoState((memoState * 10) + 5);
						qs.setMemoStateEx(1, 0);
						htmltext = "30846-09a.html";
					}
				}
				break;
			}
			case "30846-14.html":
			{
				if (qs.isStarted() && ((qs.getMemoState() % 10) == 5) && ((qs.getMemoState() / 1000) == 0))
				{
					final int card1pic = Math.max(qs.getMemoStateEx(1), 0);
					final int i1 = card1pic % 10;
					final int i2 = (card1pic - i1) / 10;
					final int rdn1 = getRandom(2) + 1;
					final int rdn2 = getRandom(5) + 1;
					final int winCount = (qs.getMemoState() / 10) + 1;
					final int card2pic = (rdn1 * 10) + rdn2;
					if (rdn1 == i2)
					{
						final int i3 = rdn2 + i1;
						if (((i3 % 5) == 0) && (i3 != 10))
						{
							if (((qs.getMemoState() % 100) / 10) >= 7)
							{
								giveAdena(player, 2384000, true);
								giveItems(player, SCROLL_ENCHANT_WEAPON_A_GRADE);
								giveItems(player, SCROLL_ENCHANT_ARMOR_A_GRADE);
								qs.setMemoState(4);
								htmltext = getHtml(player, "30846-14.html", card1pic, card2pic, winCount, -1);
							}
							else
							{
								qs.setMemoState(((qs.getMemoState() / 10) * 10) + 7);
								htmltext = getHtml(player, "30846-13.html", card1pic, card2pic, winCount, -1);
							}
						}
						else
						{
							qs.setMemoState(((qs.getMemoState() / 10) * 10) + 6);
							qs.setMemoStateEx(1, card2pic);
							htmltext = getHtml(player, "30846-12.html", card1pic, card2pic, winCount, -1);
						}
					}
					else if (rdn1 != i2)
					{
						if ((rdn2 == 5) || (i1 == 5))
						{
							if (((qs.getMemoState() % 100) / 10) >= 7)
							{
								giveAdena(player, 2384000, true);
								giveItems(player, SCROLL_ENCHANT_WEAPON_A_GRADE);
								giveItems(player, SCROLL_ENCHANT_ARMOR_A_GRADE);
								qs.setMemoState(4);
								htmltext = getHtml(player, "30846-14.html", card1pic, card2pic, winCount, -1);
							}
							else
							{
								qs.setMemoState(((qs.getMemoState() / 10) * 10) + 7);
								htmltext = getHtml(player, "30846-13.html", card1pic, card2pic, winCount, -1);
							}
						}
						else
						{
							qs.setMemoState(((qs.getMemoState() / 10) * 10) + 6);
							qs.setMemoStateEx(1, card2pic);
							htmltext = getHtml(player, "30846-12.html", card1pic, card2pic, winCount, -1);
						}
					}
				}
				break;
			}
			case "30846-19.html":
			{
				if (qs.isStarted() && ((qs.getMemoState() % 10) == 6) && ((qs.getMemoState() / 1000) == 0))
				{
					final int card1pic = Math.max(qs.getMemoStateEx(1), 0);
					int i1 = card1pic % 10;
					int i2 = (card1pic - i1) / 10;
					int rnd1 = getRandom(2) + 1;
					int rnd2 = getRandom(5) + 1;
					int card2pic = (rnd1 * 10) + rnd2;
					if (rnd1 == i2)
					{
						int i3 = rnd2 + i1;
						if (((i3 % 5) == 0) && (i3 != 10))
						{
							qs.setMemoState(1);
							qs.setMemoStateEx(1, 0);
							htmltext = getHtml(player, "30846-19.html", card1pic, card2pic, -1, -1);
						}
						else
						{
							qs.setMemoState(((qs.getMemoState() / 10) * 10) + 5);
							qs.setMemoStateEx(1, card2pic);
							htmltext = getHtml(player, "30846-18.html", card1pic, card2pic, -1, -1);
						}
					}
					else if (rnd1 != i2)
					{
						if ((rnd2 == 5) || (i1 == 5))
						{
							qs.setMemoState(1);
							htmltext = getHtml(player, "30846-19.html", card1pic, card2pic, -1, -1);
						}
						else
						{
							qs.setMemoState(((qs.getMemoState() / 10) * 10) + 5);
							qs.setMemoStateEx(1, card2pic);
							htmltext = getHtml(player, "30846-18.html", card1pic, card2pic, -1, -1);
						}
					}
				}
				break;
			}
			case "30846-20.html":
			{
				if (qs.isStarted() && ((qs.getMemoState() % 10) == 7) && ((qs.getMemoState() / 1000) == 0))
				{
					qs.setMemoState((((qs.getMemoState() / 10) + 1) * 10) + 4);
					qs.setMemoStateEx(1, 0);
					htmltext = event;
				}
				break;
			}
			case "30846-21.html":
			{
				if (qs.isStarted() && ((qs.getMemoState() % 10) == 7) && ((qs.getMemoState() / 1000) == 0))
				{
					int i0 = qs.getMemoState() / 10;
					if (i0 == 0)
					{
						giveAdena(player, 40000, true);
					}
					else if (i0 == 1)
					{
						giveAdena(player, 80000, true);
					}
					else if (i0 == 2)
					{
						giveAdena(player, 110000, true);
						giveItems(player, SCROLL_ENCHANT_WEAPON_D_GRADE);
					}
					else if (i0 == 3)
					{
						giveAdena(player, 199000, true);
						giveItems(player, SCROLL_ENCHANT_WEAPON_C_GRADE);
					}
					else if (i0 == 4)
					{
						giveAdena(player, 388000, true);
						final int rdn = getRandom(18) + 1;
						if (rdn == 1)
						{
							giveItems(player, RECIPE_GREAT_SWORD_60);
						}
						else if (rdn == 2)
						{
							giveItems(player, RECIPE_HEAVY_WAR_AXE_60);
						}
						else if (rdn == 3)
						{
							giveItems(player, RECIPE_SPRITES_STAFF_60);
						}
						else if (rdn == 4)
						{
							giveItems(player, RECIPE_KESHANBERK_60);
						}
						else if (rdn == 5)
						{
							giveItems(player, RECIPE_SWORD_OF_VALHALLA_60);
						}
						else if (rdn == 6)
						{
							giveItems(player, RECIPE_KRIS_60);
						}
						else if (rdn == 7)
						{
							giveItems(player, RECIPE_HELL_KNIFE_60);
						}
						else if (rdn == 8)
						{
							giveItems(player, RECIPE_ARTHRO_NAIL_60);
						}
						else if (rdn == 9)
						{
							giveItems(player, RECIPE_DARK_ELVEN_LONG_BOW_60);
						}
						else if (rdn == 10)
						{
							giveItems(player, RECIPE_GREAT_AXE_60);
						}
						else if (rdn == 11)
						{
							giveItems(player, RECIPE_SWORD_OF_DAMASCUS_60);
						}
						else if (rdn == 12)
						{
							giveItems(player, RECIPE_LANCE_60);
						}
						else if (rdn == 13)
						{
							giveItems(player, RECIPE_DEADMANS_GLORY_60);
						}
						else if (rdn == 14)
						{
							giveItems(player, RECIPE_ART_OF_BATTLE_AXE_60);
						}
						else if (rdn == 15)
						{
							giveItems(player, RECIPE_TAFF_OF_EVIL_SPIRITS_60);
						}
						else if (rdn == 16)
						{
							giveItems(player, RECIPE_DEMONS_DAGGER_60);
						}
						else if (rdn == 17)
						{
							giveItems(player, RECIPE_BELLION_CESTUS_60);
						}
						else if (rdn == 18)
						{
							giveItems(player, RECIPE_BOW_OF_PERIL_60);
						}
					}
					else if (i0 == 5)
					{
						giveAdena(player, 675000, true);
						final int rnd = getRandom(18) + 1;
						if (rnd == 1)
						{
							giveItems(player, GREAT_SWORD_BLADE);
						}
						else if (rnd == 2)
						{
							giveItems(player, GREAT_AXE_HEAD);
						}
						else if (rnd == 3)
						{
							giveItems(player, DARK_ELVEN_LONGBOW_SHAFT);
						}
						else if (rnd == 4)
						{
							giveItems(player, SWORD_OF_VALHALLA_BLADE);
						}
						else if (rnd == 5)
						{
							giveItems(player, ARTHRO_NAIL_BLADE);
						}
						else if (rnd == 6)
						{
							giveItems(player, SPRITES_STAFF_HEAD);
						}
						else if (rnd == 7)
						{
							giveItems(player, KRIS_EDGE);
						}
						else if (rnd == 8)
						{
							giveItems(player, KESHANBERK_BLADE);
						}
						else if (rnd == 9)
						{
							giveItems(player, HEAVY_WAR_AXE_HEAD);
						}
						else if (rnd == 10)
						{
							giveItems(player, HELL_KNIFE_EDGE);
						}
						else if (rnd == 11)
						{
							giveItems(player, SWORD_OF_DAMASCUS_BLADE);
						}
						else if (rnd == 12)
						{
							giveItems(player, LANCE_BLADE);
						}
						else if (rnd == 13)
						{
							giveItems(player, BELLION_CESTUS_EDGE);
						}
						else if (rnd == 14)
						{
							giveItems(player, EVIL_SPIRIT_HEAD);
						}
						else if (rnd == 15)
						{
							giveItems(player, DEADMANS_GLORY_STONE);
						}
						else if (rnd == 16)
						{
							giveItems(player, ART_OF_BATTLE_AXE_BLADE);
						}
						else if (rnd == 17)
						{
							giveItems(player, DEMONS_DAGGER_EDGE);
						}
						else if (rnd == 18)
						{
							giveItems(player, BOW_OF_PERIL_SHAFT);
						}
					}
					else if (i0 == 6)
					{
						giveAdena(player, 1284000, true);
						giveItems(player, SCROLL_ENCHANT_WEAPON_B_GRADE);
						giveItems(player, SCROLL_ENCHANT_ARMOR_B_GRADE);
					}
					qs.setMemoState(1);
					qs.setMemoStateEx(1, 0);
					htmltext = event;
				}
				break;
			}
			case "30846-21a.html":
			{
				if (qs.isStarted() && qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "30846-22.html":
			{
				if (qs.isStarted() && ((qs.getMemoState() % 10) == 1))
				{
					if (getQuestItemsCount(player, SPIRIT_BEAD) >= 1)
					{
						takeItems(player, SPIRIT_BEAD, 1);
						qs.setMemoState(1005);
						htmltext = event;
					}
					else
					{
						htmltext = "30846-22a.html";
					}
				}
				break;
			}
			case "30846-25.html":
			{
				if (qs.isStarted() && qs.isMemoState(1005))
				{
					int card1pic = qs.getMemoStateEx(1);
					if (card1pic < 0)
					{
						card1pic = 0;
					}
					int card1 = card1pic % 10;
					int i2 = (card1pic - card1) / 10;
					int rnd1 = getRandom(2) + 1;
					int rnd2 = getRandom(5) + 1;
					int card2pic = (rnd1 * 10) + rnd2;
					if (rnd1 == i2)
					{
						int i3 = rnd2 + card1;
						if (((i3 % 5) == 0) && (i3 != 10))
						{
							qs.setMemoState(1);
							qs.setMemoStateEx(1, 0);
							giveAdena(player, 800, true);
							htmltext = getHtml(player, "30846-25.html", card1pic, card2pic, -1, card1);
						}
						else
						{
							qs.setMemoState(1006);
							qs.setMemoStateEx(1, card2pic);
							htmltext = getHtml(player, "30846-24.html", card1pic, card2pic, -1, -1);
						}
					}
					else if (rnd1 != i2)
					{
						if ((rnd2 == 5) || (card1 == 5))
						{
							qs.setMemoState(1);
							qs.setMemoStateEx(1, 0);
							giveAdena(player, 800, true);
							htmltext = getHtml(player, "30846-25.html", card1pic, card2pic, -1, -1);
						}
						else
						{
							qs.setMemoState(1006);
							qs.setMemoStateEx(1, card2pic);
							htmltext = getHtml(player, "30846-24.html", card1pic, card2pic, -1, -1);
						}
					}
				}
				break;
			}
			case "30846-29.html":
			{
				if (qs.isStarted() && qs.isMemoState(1006))
				{
					final int card1pic = Math.max(qs.getMemoStateEx(1), 0);
					int i1 = card1pic % 10;
					int i2 = (card1pic - i1) / 10;
					int rnd1 = getRandom(2) + 1;
					int rnd2 = getRandom(5) + 1;
					int card2pic = (rnd1 * 10) + rnd2;
					if (rnd1 == i2)
					{
						final int i3 = rnd2 + i1;
						if (((i3 % 5) == 0) && (i3 != 10))
						{
							qs.setMemoState(1);
							qs.setMemoStateEx(1, 0);
							htmltext = getHtml(player, "30846-29.html", card1pic, card2pic, 0, -1);
						}
						else
						{
							qs.setMemoState(1005);
							qs.setMemoStateEx(1, card2pic);
							htmltext = getHtml(player, "30846-28.html", card1pic, card2pic, 0, -1);
						}
					}
					else if (rnd1 != i2)
					{
						if ((rnd2 == 5) || (i1 == 5))
						{
							qs.setMemoState(1);
							qs.setMemoStateEx(1, 0);
							htmltext = getHtml(player, "30846-29.html", card1pic, card2pic, 0, -1);
						}
						else
						{
							qs.setMemoState(1005);
							qs.setMemoStateEx(1, card2pic);
							htmltext = getHtml(player, "30846-28.html", card1pic, card2pic, 0, -1);
						}
					}
				}
				break;
			}
			case "30846-30.html":
			{
				if (qs.isStarted())
				{
					qs.exitQuest(true);
					htmltext = event;
				}
				break;
			}
			case "30846-31.html":
			case "30846-32.html":
			{
				if (qs.isStarted())
				{
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
		final List<L2PcInstance> players = new LinkedList<>();
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && (qs.getMemoState() >= 1) && (qs.getMemoState() <= 4))
		{
			players.add(killer);
			players.add(killer);
		}
		
		if (killer.isInParty())
		{
			for (L2PcInstance partyMember : killer.getParty().getMembers())
			{
				final QuestState partyMemberQuestState = getQuestState(partyMember, false);
				if ((partyMemberQuestState != null) && partyMemberQuestState.isStarted() && (partyMemberQuestState.getMemoState() >= 1) && (partyMemberQuestState.getMemoState() <= 4))
				{
					players.add(partyMember);
				}
			}
		}
		
		if (!players.isEmpty())
		{
			final L2PcInstance rewardedPlayer = players.get(getRandom(players.size()));
			if (Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, rewardedPlayer, false))
			{
				final int rnd = getRandom(1000);
				
				if (npc.getId() == SPITEFUL_SOUL_LEADER)
				{
					if (rnd <= SPITEFUL_SOUL_LEADER_CHANCE)
					{
						giveItems(rewardedPlayer, SPIRIT_BEAD, 2);
					}
					else
					{
						giveItems(rewardedPlayer, SPIRIT_BEAD, 1);
					}
				}
				else if (rnd < MONSTERS.get(npc.getId()))
				{
					giveItems(rewardedPlayer, SPIRIT_BEAD, 1);
					playSound(rewardedPlayer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	private String getHtml(L2PcInstance player, String htmlName, int card1pic, int card2pic, int winCount, int card1)
	{
		String html = getHtm(player, htmlName);
		html = html.replace("<?card1pic?>", Integer.toString(card1pic));
		html = html.replace("<?card2pic?>", Integer.toString(card2pic));
		html = html.replace("<?name?>", player.getName());
		if (winCount >= 0)
		{
			html = html.replace("<?wincount?>", Integer.toString(winCount));
		}
		if (card1 >= 0)
		{
			html = html.replace("<?card1?>", Integer.toString(card1));
		}
		return html;
	}
}

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
package quests.Q00330_AdeptOfTaste;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Adept Of Taste (330)
 * @author ivantotov
 */
public final class Q00330_AdeptOfTaste extends Quest
{
	// NPCs
	private static final int ACCESSORY_MERCHANT_SONIA = 30062;
	private static final int PRIESTESS_GLYVKA = 30067;
	private static final int MAGISTER_ROLLANT = 30069;
	private static final int GUARD_JACOB = 30073;
	private static final int GROCER_PANO = 30078;
	private static final int MAGISTER_MIRIEN = 30461;
	private static final int JONAS = 30469;
	// Items
	private static final int INGREDIENT_LIST = 1420;
	private static final int SONIAS_BOTANY_BOOK = 1421;
	private static final int RED_MANDRAGORA_ROOT = 1422;
	private static final int WHITE_MANDRAGORA_ROOT = 1423;
	private static final int RED_MANDRAGORA_SAP = 1424;
	private static final int WHITE_MANDRAGORA_SAP = 1425;
	private static final int JACOBS_INSECT_BOOK = 1426;
	private static final int NECTAR = 1427;
	private static final int ROYAL_JELLY = 1428;
	private static final int HONEY = 1429;
	private static final int GOLDEN_HONEY = 1430;
	private static final int PANOS_CONTRACT = 1431;
	private static final int HOBGOBLIN_AMULET = 1432;
	private static final int DIONIAN_POTATO = 1433;
	private static final int GLYVKAS_BOTANY_BOOK = 1434;
	private static final int GREEN_MARSH_MOSS = 1435;
	private static final int BROWN_MARSH_MOSS = 1436;
	private static final int GREEN_MOSS_BUNDLE = 1437;
	private static final int BROWN_MOSS_BUNDLE = 1438;
	private static final int ROLLANTS_CREATURE_BOOK = 1439;
	private static final int BODY_OF_MONSTER_EYE = 1440;
	private static final int MONSTER_EYE_MEAT = 1441;
	private static final int JONASS_1ST_STEAK_DISH = 1442;
	private static final int JONASS_2ND_STEAK_DISH = 1443;
	private static final int JONASS_3RD_STEAK_DISH = 1444;
	private static final int JONASS_4TH_STEAK_DISH = 1445;
	private static final int JONASS_5TH_STEAK_DISH = 1446;
	private static final int MIRIENS_REVIEW_1 = 1447;
	private static final int MIRIENS_REVIEW_2 = 1448;
	private static final int MIRIENS_REVIEW_3 = 1449;
	private static final int MIRIENS_REVIEW_4 = 1450;
	private static final int MIRIENS_REVIEW_5 = 1451;
	// Reward
	private static final int JONASS_SALAD_RECIPE = 1455;
	private static final int JONASS_SAUCE_RECIPE = 1456;
	private static final int JONASS_STEAK_RECIPE = 1457;
	// Monster
	private static final int HOBGOBLIN = 20147;
	private static final int MANDRAGORA_SPROUT1 = 20154;
	private static final int MANDRAGORA_SAPLING = 20155;
	private static final int MANDRAGORA_BLOSSOM = 20156;
	private static final int BLOODY_BEE = 20204;
	private static final int MANDRAGORA_SPROUT2 = 20223;
	private static final int GRAY_ANT = 20226;
	private static final int GIANT_CRIMSON_ANT = 20228;
	private static final int STINGER_WASP = 20229;
	private static final int MONSTER_EYE_SEARCHER = 20265;
	private static final int MONSTER_EYE_GAZER = 20266;
	// Misc
	private static final int MIN_LEVEL = 24;
	
	public Q00330_AdeptOfTaste()
	{
		super(330);
		addStartNpc(JONAS);
		addTalkId(JONAS, ACCESSORY_MERCHANT_SONIA, PRIESTESS_GLYVKA, MAGISTER_ROLLANT, GUARD_JACOB, GROCER_PANO, MAGISTER_MIRIEN);
		addKillId(HOBGOBLIN, MANDRAGORA_SPROUT1, MANDRAGORA_SAPLING, MANDRAGORA_BLOSSOM, BLOODY_BEE, MANDRAGORA_SPROUT2, GRAY_ANT, GIANT_CRIMSON_ANT, STINGER_WASP, MONSTER_EYE_SEARCHER, MONSTER_EYE_GAZER);
		registerQuestItems(INGREDIENT_LIST, SONIAS_BOTANY_BOOK, RED_MANDRAGORA_ROOT, WHITE_MANDRAGORA_ROOT, RED_MANDRAGORA_SAP, WHITE_MANDRAGORA_SAP, JACOBS_INSECT_BOOK, NECTAR, ROYAL_JELLY, HONEY, GOLDEN_HONEY, PANOS_CONTRACT, HOBGOBLIN_AMULET, DIONIAN_POTATO, GLYVKAS_BOTANY_BOOK, GREEN_MARSH_MOSS, BROWN_MARSH_MOSS, GREEN_MOSS_BUNDLE, BROWN_MOSS_BUNDLE, ROLLANTS_CREATURE_BOOK, BODY_OF_MONSTER_EYE, MONSTER_EYE_MEAT, JONASS_1ST_STEAK_DISH, JONASS_2ND_STEAK_DISH, JONASS_3RD_STEAK_DISH, JONASS_4TH_STEAK_DISH, JONASS_5TH_STEAK_DISH, MIRIENS_REVIEW_1, MIRIENS_REVIEW_2, MIRIENS_REVIEW_3, MIRIENS_REVIEW_4, MIRIENS_REVIEW_5);
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
			case "30469-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					giveItems(player, INGREDIENT_LIST, 1);
					htmltext = event;
				}
				break;
			}
			case "30062-05.html":
			{
				if (hasQuestItems(player, SONIAS_BOTANY_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_ROOT) + getQuestItemsCount(player, WHITE_MANDRAGORA_ROOT)) >= 40))
				{
					if (getQuestItemsCount(player, WHITE_MANDRAGORA_ROOT) < 40)
					{
						takeItems(player, SONIAS_BOTANY_BOOK, 1);
						takeItems(player, RED_MANDRAGORA_ROOT, -1);
						takeItems(player, WHITE_MANDRAGORA_ROOT, -1);
						giveItems(player, RED_MANDRAGORA_SAP, 1);
						htmltext = event;
					}
				}
				break;
			}
			case "30067-05.html":
			{
				if (hasQuestItems(player, GLYVKAS_BOTANY_BOOK) && ((getQuestItemsCount(player, GREEN_MARSH_MOSS) + getQuestItemsCount(player, BROWN_MARSH_MOSS)) >= 20))
				{
					if (getQuestItemsCount(player, BROWN_MARSH_MOSS) < 20)
					{
						takeItems(player, GLYVKAS_BOTANY_BOOK, 1);
						takeItems(player, GREEN_MARSH_MOSS, -1);
						takeItems(player, BROWN_MARSH_MOSS, -1);
						giveItems(player, GREEN_MOSS_BUNDLE, 1);
						htmltext = event;
					}
				}
				break;
			}
			case "30073-05.html":
			{
				if ((hasQuestItems(player, JACOBS_INSECT_BOOK) && (getQuestItemsCount(player, NECTAR) >= 20)) && (getQuestItemsCount(player, ROYAL_JELLY) < 10))
				{
					takeItems(player, JACOBS_INSECT_BOOK, 1);
					takeItems(player, NECTAR, -1);
					takeItems(player, ROYAL_JELLY, -1);
					giveItems(player, HONEY, 1);
					htmltext = event;
				}
				break;
			}
			case "30062-04.html":
			case "30067-04.html":
			case "30073-04.html":
			case "30469-04.html":
			case "30469-04t1.html":
			case "30469-04t2.html":
			case "30469-04t3.html":
			case "30469-04t4.html":
			case "30469-04t5.html":
			{
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case HOBGOBLIN:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, PANOS_CONTRACT) && (getQuestItemsCount(killer, HOBGOBLIN_AMULET) < 30))
						{
							giveItems(killer, HOBGOBLIN_AMULET, 1);
							if (getQuestItemsCount(killer, HOBGOBLIN_AMULET) == 30)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case MANDRAGORA_SPROUT1:
				case MANDRAGORA_SPROUT2:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, SONIAS_BOTANY_BOOK) && !hasAtLeastOneQuestItem(killer, RED_MANDRAGORA_SAP, WHITE_MANDRAGORA_SAP))
						{
							final int i0 = getRandom(100);
							if (i0 < 70)
							{
								if (getQuestItemsCount(killer, RED_MANDRAGORA_ROOT) < 40)
								{
									giveItems(killer, RED_MANDRAGORA_ROOT, 1);
									if (getQuestItemsCount(killer, RED_MANDRAGORA_ROOT) == 40)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
							else if (i0 < 77)
							{
								if (getQuestItemsCount(killer, WHITE_MANDRAGORA_ROOT) < 40)
								{
									giveItems(killer, WHITE_MANDRAGORA_ROOT, 1);
									if (getQuestItemsCount(killer, WHITE_MANDRAGORA_ROOT) == 40)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
						}
					}
					break;
				}
				case MANDRAGORA_SAPLING:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, SONIAS_BOTANY_BOOK) && !hasAtLeastOneQuestItem(killer, RED_MANDRAGORA_SAP, WHITE_MANDRAGORA_SAP))
						{
							final int i0 = getRandom(100);
							if (i0 < 77)
							{
								if (getQuestItemsCount(killer, RED_MANDRAGORA_ROOT) < 40)
								{
									giveItems(killer, RED_MANDRAGORA_ROOT, 1);
									if (getQuestItemsCount(killer, RED_MANDRAGORA_ROOT) == 40)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
							else if (i0 < 85)
							{
								if (getQuestItemsCount(killer, WHITE_MANDRAGORA_ROOT) < 40)
								{
									giveItems(killer, WHITE_MANDRAGORA_ROOT, 1);
									if (getQuestItemsCount(killer, WHITE_MANDRAGORA_ROOT) == 40)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
						}
					}
					break;
				}
				case MANDRAGORA_BLOSSOM:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, SONIAS_BOTANY_BOOK) && !hasAtLeastOneQuestItem(killer, RED_MANDRAGORA_SAP, WHITE_MANDRAGORA_SAP))
						{
							final int i0 = getRandom(100);
							if (i0 < 87)
							{
								if (getQuestItemsCount(killer, RED_MANDRAGORA_ROOT) < 40)
								{
									giveItems(killer, RED_MANDRAGORA_ROOT, 1);
									if (getQuestItemsCount(killer, RED_MANDRAGORA_ROOT) == 40)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
							else if (i0 < 96)
							{
								if (getQuestItemsCount(killer, WHITE_MANDRAGORA_ROOT) < 40)
								{
									giveItems(killer, WHITE_MANDRAGORA_ROOT, 1);
									if (getQuestItemsCount(killer, WHITE_MANDRAGORA_ROOT) == 40)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
						}
					}
					break;
				}
				case BLOODY_BEE:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, JACOBS_INSECT_BOOK))
						{
							final int i0 = getRandom(100);
							if (i0 < 80)
							{
								if (getQuestItemsCount(killer, NECTAR) < 20)
								{
									giveItems(killer, NECTAR, 1);
									if (getQuestItemsCount(killer, NECTAR) == 20)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
							else if (i0 < 95)
							{
								if (getQuestItemsCount(killer, ROYAL_JELLY) < 10)
								{
									giveItems(killer, ROYAL_JELLY, 1);
									if (getQuestItemsCount(killer, ROYAL_JELLY) == 10)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
						}
					}
					break;
				}
				case GRAY_ANT:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, GLYVKAS_BOTANY_BOOK))
						{
							final int i0 = getRandom(100);
							if (i0 < 87)
							{
								if (getQuestItemsCount(killer, GREEN_MARSH_MOSS) < 20)
								{
									giveItems(killer, GREEN_MARSH_MOSS, 1);
									if (getQuestItemsCount(killer, GREEN_MARSH_MOSS) == 20)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
							else if (i0 < 96)
							{
								if (getQuestItemsCount(killer, BROWN_MARSH_MOSS) < 20)
								{
									giveItems(killer, BROWN_MARSH_MOSS, 1);
									if (getQuestItemsCount(killer, BROWN_MARSH_MOSS) == 20)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
						}
					}
					break;
				}
				case GIANT_CRIMSON_ANT:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, GLYVKAS_BOTANY_BOOK))
						{
							final int i0 = getRandom(100);
							if (i0 < 90)
							{
								if (getQuestItemsCount(killer, GREEN_MARSH_MOSS) < 20)
								{
									giveItems(killer, GREEN_MARSH_MOSS, 1);
									if (getQuestItemsCount(killer, GREEN_MARSH_MOSS) == 20)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
							else
							{
								if (getQuestItemsCount(killer, BROWN_MARSH_MOSS) < 20)
								{
									giveItems(killer, BROWN_MARSH_MOSS, 1);
									if (getQuestItemsCount(killer, BROWN_MARSH_MOSS) == 20)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
						}
					}
					break;
				}
				case STINGER_WASP:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, JACOBS_INSECT_BOOK))
						{
							final int i0 = getRandom(100);
							if (i0 < 92)
							{
								if (getQuestItemsCount(killer, NECTAR) < 20)
								{
									giveItems(killer, NECTAR, 1);
									if (getQuestItemsCount(killer, NECTAR) == 20)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
							else
							{
								if (getQuestItemsCount(killer, ROYAL_JELLY) < 10)
								{
									giveItems(killer, ROYAL_JELLY, 1);
									if (getQuestItemsCount(killer, ROYAL_JELLY) == 10)
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
									}
									else
									{
										playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
									}
								}
							}
						}
					}
					break;
				}
				case MONSTER_EYE_SEARCHER:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, ROLLANTS_CREATURE_BOOK) && (getQuestItemsCount(killer, BODY_OF_MONSTER_EYE) < 30))
						{
							final int i0 = getRandom(100);
							if (i0 < 77)
							{
								if (getQuestItemsCount(killer, BODY_OF_MONSTER_EYE) == 29)
								{
									giveItems(killer, BODY_OF_MONSTER_EYE, 1);
									playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
								}
								else
								{
									giveItems(killer, BODY_OF_MONSTER_EYE, 2);
									playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
								}
							}
							else if (i0 < 97)
							{
								if (getQuestItemsCount(killer, BROWN_MARSH_MOSS) == 28)
								{
									giveItems(killer, BODY_OF_MONSTER_EYE, 2);
									playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
								}
								else if (getQuestItemsCount(killer, BROWN_MARSH_MOSS) == 29)
								{
									giveItems(killer, BODY_OF_MONSTER_EYE, 1);
									playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
								}
								else
								{
									giveItems(killer, BODY_OF_MONSTER_EYE, 3);
									playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
								}
								
							}
						}
					}
					break;
				}
				case MONSTER_EYE_GAZER:
				{
					if ((getQuestItemsCount(killer, RED_MANDRAGORA_SAP) + getQuestItemsCount(killer, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(killer, HONEY) + getQuestItemsCount(killer, GOLDEN_HONEY) + getQuestItemsCount(killer, DIONIAN_POTATO) + getQuestItemsCount(killer, GREEN_MOSS_BUNDLE) + getQuestItemsCount(killer, BROWN_MOSS_BUNDLE) + getQuestItemsCount(killer, MONSTER_EYE_MEAT)) < 5)
					{
						if (hasQuestItems(killer, INGREDIENT_LIST, ROLLANTS_CREATURE_BOOK) && (getQuestItemsCount(killer, BODY_OF_MONSTER_EYE) < 30))
						{
							final int i0 = getRandom(10);
							if (i0 < 7)
							{
								giveItems(killer, BODY_OF_MONSTER_EYE, 1);
								if (getQuestItemsCount(killer, BODY_OF_MONSTER_EYE) == 30)
								{
									playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
								}
								else
								{
									playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
								}
							}
							else
							{
								if (getQuestItemsCount(killer, BROWN_MARSH_MOSS) == 29)
								{
									giveItems(killer, BODY_OF_MONSTER_EYE, 1);
									playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
								}
								else
								{
									giveItems(killer, BODY_OF_MONSTER_EYE, 2);
									playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
								}
								
							}
						}
					}
					break;
				}
			}
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
			if (npc.getId() == JONAS)
			{
				if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "30469-01.htm";
				}
				else
				{
					htmltext = "30469-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case JONAS:
				{
					if (hasQuestItems(player, INGREDIENT_LIST))
					{
						if ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5)
						{
							htmltext = "30469-04.html";
						}
						else
						{
							if ((getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE)) == 0)
							{
								if (getRandom(10) < 5)
								{
									takeItems(player, INGREDIENT_LIST, -1);
									takeItems(player, RED_MANDRAGORA_SAP, -1);
									takeItems(player, WHITE_MANDRAGORA_SAP, -1);
									takeItems(player, HONEY, -1);
									takeItems(player, GOLDEN_HONEY, -1);
									takeItems(player, DIONIAN_POTATO, -1);
									takeItems(player, GREEN_MOSS_BUNDLE, -1);
									takeItems(player, BROWN_MOSS_BUNDLE, -1);
									takeItems(player, MONSTER_EYE_MEAT, -1);
									giveItems(player, JONASS_2ND_STEAK_DISH, 1);
									htmltext = "30469-05t2.html";
								}
								else
								{
									takeItems(player, INGREDIENT_LIST, -1);
									takeItems(player, RED_MANDRAGORA_SAP, -1);
									takeItems(player, WHITE_MANDRAGORA_SAP, -1);
									takeItems(player, HONEY, -1);
									takeItems(player, GOLDEN_HONEY, -1);
									takeItems(player, DIONIAN_POTATO, -1);
									takeItems(player, GREEN_MOSS_BUNDLE, -1);
									takeItems(player, BROWN_MOSS_BUNDLE, -1);
									takeItems(player, MONSTER_EYE_MEAT, -1);
									giveItems(player, JONASS_1ST_STEAK_DISH, 1);
									htmltext = "30469-05t1.html";
								}
							}
							
							if ((getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE)) == 1)
							{
								if (getRandom(10) < 5)
								{
									takeItems(player, INGREDIENT_LIST, -1);
									takeItems(player, RED_MANDRAGORA_SAP, -1);
									takeItems(player, WHITE_MANDRAGORA_SAP, -1);
									takeItems(player, HONEY, -1);
									takeItems(player, GOLDEN_HONEY, -1);
									takeItems(player, DIONIAN_POTATO, -1);
									takeItems(player, GREEN_MOSS_BUNDLE, -1);
									takeItems(player, BROWN_MOSS_BUNDLE, -1);
									takeItems(player, MONSTER_EYE_MEAT, -1);
									giveItems(player, JONASS_3RD_STEAK_DISH, 1);
									htmltext = "30469-05t3.html";
								}
								else
								{
									takeItems(player, INGREDIENT_LIST, -1);
									takeItems(player, RED_MANDRAGORA_SAP, -1);
									takeItems(player, WHITE_MANDRAGORA_SAP, -1);
									takeItems(player, HONEY, -1);
									takeItems(player, GOLDEN_HONEY, -1);
									takeItems(player, DIONIAN_POTATO, -1);
									takeItems(player, GREEN_MOSS_BUNDLE, -1);
									takeItems(player, BROWN_MOSS_BUNDLE, -1);
									takeItems(player, MONSTER_EYE_MEAT, -1);
									giveItems(player, JONASS_2ND_STEAK_DISH, 1);
									htmltext = "30469-05t2.html";
								}
							}
							
							if ((getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE)) == 2)
							{
								if (getRandom(10) < 5)
								{
									takeItems(player, INGREDIENT_LIST, -1);
									takeItems(player, RED_MANDRAGORA_SAP, -1);
									takeItems(player, WHITE_MANDRAGORA_SAP, -1);
									takeItems(player, HONEY, -1);
									takeItems(player, GOLDEN_HONEY, -1);
									takeItems(player, DIONIAN_POTATO, -1);
									takeItems(player, GREEN_MOSS_BUNDLE, -1);
									takeItems(player, BROWN_MOSS_BUNDLE, -1);
									takeItems(player, MONSTER_EYE_MEAT, -1);
									giveItems(player, JONASS_4TH_STEAK_DISH, 1);
									htmltext = "30469-05t4.html";
								}
								else
								{
									takeItems(player, INGREDIENT_LIST, -1);
									takeItems(player, RED_MANDRAGORA_SAP, -1);
									takeItems(player, WHITE_MANDRAGORA_SAP, -1);
									takeItems(player, HONEY, -1);
									takeItems(player, GOLDEN_HONEY, -1);
									takeItems(player, DIONIAN_POTATO, -1);
									takeItems(player, GREEN_MOSS_BUNDLE, -1);
									takeItems(player, BROWN_MOSS_BUNDLE, -1);
									takeItems(player, MONSTER_EYE_MEAT, -1);
									giveItems(player, JONASS_3RD_STEAK_DISH, 1);
									htmltext = "30469-05t3.html";
								}
							}
							
							if ((getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE)) == 3)
							{
								if (getRandom(10) < 5)
								{
									takeItems(player, INGREDIENT_LIST, -1);
									takeItems(player, RED_MANDRAGORA_SAP, -1);
									takeItems(player, WHITE_MANDRAGORA_SAP, -1);
									takeItems(player, HONEY, -1);
									takeItems(player, GOLDEN_HONEY, -1);
									takeItems(player, DIONIAN_POTATO, -1);
									takeItems(player, GREEN_MOSS_BUNDLE, -1);
									takeItems(player, BROWN_MOSS_BUNDLE, -1);
									takeItems(player, MONSTER_EYE_MEAT, -1);
									giveItems(player, JONASS_5TH_STEAK_DISH, 1);
									playSound(player, QuestSound.ITEMSOUND_QUEST_JACKPOT);
									htmltext = "30469-05t5.html";
								}
								else
								{
									takeItems(player, INGREDIENT_LIST, -1);
									takeItems(player, RED_MANDRAGORA_SAP, -1);
									takeItems(player, WHITE_MANDRAGORA_SAP, -1);
									takeItems(player, HONEY, -1);
									takeItems(player, GOLDEN_HONEY, -1);
									takeItems(player, DIONIAN_POTATO, -1);
									takeItems(player, GREEN_MOSS_BUNDLE, -1);
									takeItems(player, BROWN_MOSS_BUNDLE, -1);
									takeItems(player, MONSTER_EYE_MEAT, -1);
									giveItems(player, JONASS_4TH_STEAK_DISH, 1);
									htmltext = "30469-05t4.html";
								}
							}
						}
					}
					else
					{
						if ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) == 0)
						{
							if ((getQuestItemsCount(player, JONASS_1ST_STEAK_DISH) + getQuestItemsCount(player, JONASS_2ND_STEAK_DISH) + getQuestItemsCount(player, JONASS_3RD_STEAK_DISH) + getQuestItemsCount(player, JONASS_4TH_STEAK_DISH) + getQuestItemsCount(player, JONASS_5TH_STEAK_DISH)) == 1)
							{
								if ((getQuestItemsCount(player, MIRIENS_REVIEW_1) + getQuestItemsCount(player, MIRIENS_REVIEW_2) + getQuestItemsCount(player, MIRIENS_REVIEW_3) + getQuestItemsCount(player, MIRIENS_REVIEW_4) + getQuestItemsCount(player, MIRIENS_REVIEW_5)) == 0)
								{
									htmltext = "30469-06.html";
								}
							}
							else
							{
								if ((getQuestItemsCount(player, MIRIENS_REVIEW_1) + getQuestItemsCount(player, MIRIENS_REVIEW_2) + getQuestItemsCount(player, MIRIENS_REVIEW_3) + getQuestItemsCount(player, MIRIENS_REVIEW_4) + getQuestItemsCount(player, MIRIENS_REVIEW_5)) == 1)
								{
									if (hasQuestItems(player, MIRIENS_REVIEW_1))
									{
										takeItems(player, MIRIENS_REVIEW_1, 1);
										giveAdena(player, 10000, true);
										htmltext = "30469-06t1.html";
									}
									
									if (hasQuestItems(player, MIRIENS_REVIEW_2))
									{
										takeItems(player, MIRIENS_REVIEW_2, 1);
										giveAdena(player, 14870, true);
										htmltext = "30469-06t2.html";
									}
									
									if (hasQuestItems(player, MIRIENS_REVIEW_3))
									{
										takeItems(player, MIRIENS_REVIEW_3, 1);
										giveAdena(player, 6490, true);
										giveItems(player, JONASS_SALAD_RECIPE, 1);
										htmltext = "30469-06t3.html";
									}
									
									if (hasQuestItems(player, MIRIENS_REVIEW_4))
									{
										takeItems(player, MIRIENS_REVIEW_4, 1);
										giveAdena(player, 12220, true);
										giveItems(player, JONASS_SAUCE_RECIPE, 1);
										htmltext = "30469-06t4.html";
									}
									
									if (hasQuestItems(player, MIRIENS_REVIEW_5))
									{
										takeItems(player, MIRIENS_REVIEW_5, 1);
										giveAdena(player, 16540, true);
										giveItems(player, JONASS_STEAK_RECIPE, 1);
										htmltext = "30469-06t5.html";
									}
									
									qs.exitQuest(true, true);
								}
							}
						}
					}
					break;
				}
				case ACCESSORY_MERCHANT_SONIA:
				{
					if (hasQuestItems(player, INGREDIENT_LIST) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && !hasAtLeastOneQuestItem(player, SONIAS_BOTANY_BOOK, RED_MANDRAGORA_SAP, WHITE_MANDRAGORA_SAP))
					{
						giveItems(player, SONIAS_BOTANY_BOOK, 1);
						htmltext = "30062-01.html";
					}
					else if (hasQuestItems(player, INGREDIENT_LIST, SONIAS_BOTANY_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && !hasAtLeastOneQuestItem(player, RED_MANDRAGORA_SAP, WHITE_MANDRAGORA_SAP))
					{
						if ((getQuestItemsCount(player, RED_MANDRAGORA_ROOT) + getQuestItemsCount(player, WHITE_MANDRAGORA_ROOT)) < 40)
						{
							htmltext = "30062-02.html";
						}
						else
						{
							if (getQuestItemsCount(player, WHITE_MANDRAGORA_ROOT) < 40)
							{
								htmltext = "30062-03.html";
							}
							else
							{
								takeItems(player, SONIAS_BOTANY_BOOK, 1);
								takeItems(player, RED_MANDRAGORA_ROOT, -1);
								takeItems(player, WHITE_MANDRAGORA_ROOT, -1);
								giveItems(player, WHITE_MANDRAGORA_SAP, 1);
								htmltext = "30062-06.html";
							}
						}
					}
					else if (hasQuestItems(player, INGREDIENT_LIST) && !hasQuestItems(player, SONIAS_BOTANY_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && hasAtLeastOneQuestItem(player, RED_MANDRAGORA_SAP, WHITE_MANDRAGORA_SAP))
					{
						htmltext = "30062-07.html";
					}
					break;
				}
				case PRIESTESS_GLYVKA:
				{
					if (hasQuestItems(player, INGREDIENT_LIST) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && !hasAtLeastOneQuestItem(player, GLYVKAS_BOTANY_BOOK, GREEN_MOSS_BUNDLE, BROWN_MOSS_BUNDLE))
					{
						giveItems(player, GLYVKAS_BOTANY_BOOK, 1);
						htmltext = "30067-01.html";
					}
					else if (hasQuestItems(player, INGREDIENT_LIST, GLYVKAS_BOTANY_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5))
					{
						if ((getQuestItemsCount(player, GREEN_MARSH_MOSS) + getQuestItemsCount(player, BROWN_MARSH_MOSS)) < 20)
						{
							htmltext = "30067-02.html";
						}
						else
						{
							if (getQuestItemsCount(player, BROWN_MARSH_MOSS) < 20)
							{
								htmltext = "30067-03.html";
							}
							else
							{
								takeItems(player, GLYVKAS_BOTANY_BOOK, 1);
								takeItems(player, GREEN_MARSH_MOSS, -1);
								takeItems(player, BROWN_MARSH_MOSS, -1);
								giveItems(player, BROWN_MOSS_BUNDLE, 1);
								htmltext = "30067-06.html";
							}
						}
					}
					else if (hasQuestItems(player, INGREDIENT_LIST) && !hasQuestItems(player, GLYVKAS_BOTANY_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && hasAtLeastOneQuestItem(player, GREEN_MOSS_BUNDLE, BROWN_MOSS_BUNDLE))
					{
						htmltext = "30067-07.html";
					}
					break;
				}
				case MAGISTER_ROLLANT:
				{
					if (hasQuestItems(player, INGREDIENT_LIST) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && !hasAtLeastOneQuestItem(player, ROLLANTS_CREATURE_BOOK, MONSTER_EYE_MEAT))
					{
						giveItems(player, ROLLANTS_CREATURE_BOOK, 1);
						htmltext = "30069-01.html";
					}
					else if (hasQuestItems(player, INGREDIENT_LIST, ROLLANTS_CREATURE_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5))
					{
						if (getQuestItemsCount(player, BODY_OF_MONSTER_EYE) < 30)
						{
							htmltext = "30069-02.html";
						}
						else
						{
							takeItems(player, ROLLANTS_CREATURE_BOOK, 1);
							takeItems(player, BODY_OF_MONSTER_EYE, -1);
							giveItems(player, MONSTER_EYE_MEAT, 1);
							htmltext = "30069-03.html";
						}
					}
					else if (hasQuestItems(player, INGREDIENT_LIST, MONSTER_EYE_MEAT) && !hasQuestItems(player, ROLLANTS_CREATURE_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5))
					{
						htmltext = "30069-04.html";
					}
					break;
				}
				case GUARD_JACOB:
				{
					if (hasQuestItems(player, INGREDIENT_LIST) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && !hasAtLeastOneQuestItem(player, JACOBS_INSECT_BOOK, HONEY, GOLDEN_HONEY))
					{
						giveItems(player, JACOBS_INSECT_BOOK, 1);
						htmltext = "30073-01.html";
					}
					else if (hasQuestItems(player, INGREDIENT_LIST, JACOBS_INSECT_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5))
					{
						if (getQuestItemsCount(player, NECTAR) < 20)
						{
							htmltext = "30073-02.html";
						}
						else
						{
							if (getQuestItemsCount(player, ROYAL_JELLY) < 10)
							{
								htmltext = "30073-03.html";
							}
							else
							{
								takeItems(player, JACOBS_INSECT_BOOK, 1);
								takeItems(player, NECTAR, -1);
								takeItems(player, ROYAL_JELLY, -1);
								giveItems(player, GOLDEN_HONEY, 1);
								htmltext = "30073-06.html";
							}
						}
					}
					else if (hasQuestItems(player, INGREDIENT_LIST) && !hasQuestItems(player, JACOBS_INSECT_BOOK) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && hasAtLeastOneQuestItem(player, HONEY, GOLDEN_HONEY))
					{
						htmltext = "30073-07.html";
					}
					break;
				}
				case GROCER_PANO:
				{
					if (hasQuestItems(player, INGREDIENT_LIST) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5) && !hasAtLeastOneQuestItem(player, PANOS_CONTRACT, DIONIAN_POTATO))
					{
						giveItems(player, PANOS_CONTRACT, 1);
						htmltext = "30078-01.html";
					}
					else if (hasQuestItems(player, INGREDIENT_LIST, PANOS_CONTRACT) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5))
					{
						if (getQuestItemsCount(player, HOBGOBLIN_AMULET) < 30)
						{
							htmltext = "30078-02.html";
						}
						else
						{
							takeItems(player, PANOS_CONTRACT, 1);
							takeItems(player, HOBGOBLIN_AMULET, -1);
							giveItems(player, DIONIAN_POTATO, 1);
							htmltext = "30078-03.html";
						}
					}
					else if (hasQuestItems(player, INGREDIENT_LIST, DIONIAN_POTATO) && !hasQuestItems(player, PANOS_CONTRACT) && ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) < 5))
					{
						htmltext = "30078-04.html";
					}
					break;
				}
				case MAGISTER_MIRIEN:
				{
					if (hasQuestItems(player, INGREDIENT_LIST))
					{
						htmltext = "30461-01.html";
					}
					else
					{
						if ((getQuestItemsCount(player, RED_MANDRAGORA_SAP) + getQuestItemsCount(player, WHITE_MANDRAGORA_SAP) + getQuestItemsCount(player, HONEY) + getQuestItemsCount(player, GOLDEN_HONEY) + getQuestItemsCount(player, DIONIAN_POTATO) + getQuestItemsCount(player, GREEN_MOSS_BUNDLE) + getQuestItemsCount(player, BROWN_MOSS_BUNDLE) + getQuestItemsCount(player, MONSTER_EYE_MEAT)) == 0)
						{
							if ((getQuestItemsCount(player, JONASS_1ST_STEAK_DISH) + getQuestItemsCount(player, JONASS_2ND_STEAK_DISH) + getQuestItemsCount(player, JONASS_3RD_STEAK_DISH) + getQuestItemsCount(player, JONASS_4TH_STEAK_DISH) + getQuestItemsCount(player, JONASS_5TH_STEAK_DISH)) == 1)
							{
								if ((getQuestItemsCount(player, MIRIENS_REVIEW_1) + getQuestItemsCount(player, MIRIENS_REVIEW_2) + getQuestItemsCount(player, MIRIENS_REVIEW_3) + getQuestItemsCount(player, MIRIENS_REVIEW_4) + getQuestItemsCount(player, MIRIENS_REVIEW_5)) == 0)
								{
									if (hasQuestItems(player, JONASS_1ST_STEAK_DISH))
									{
										takeItems(player, JONASS_1ST_STEAK_DISH, 1);
										giveItems(player, MIRIENS_REVIEW_1, 1);
										htmltext = "30461-02t1.html";
									}
									
									if (hasQuestItems(player, JONASS_2ND_STEAK_DISH))
									{
										takeItems(player, JONASS_2ND_STEAK_DISH, 1);
										giveItems(player, MIRIENS_REVIEW_2, 1);
										htmltext = "30461-02t2.html";
									}
									
									if (hasQuestItems(player, JONASS_3RD_STEAK_DISH))
									{
										takeItems(player, JONASS_3RD_STEAK_DISH, 1);
										giveItems(player, MIRIENS_REVIEW_3, 1);
										htmltext = "30461-02t3.html";
									}
									
									if (hasQuestItems(player, JONASS_4TH_STEAK_DISH))
									{
										takeItems(player, JONASS_4TH_STEAK_DISH, 1);
										giveItems(player, MIRIENS_REVIEW_4, 1);
										htmltext = "30461-02t4.html";
									}
									
									if (hasQuestItems(player, JONASS_5TH_STEAK_DISH))
									{
										takeItems(player, JONASS_5TH_STEAK_DISH, 1);
										giveItems(player, MIRIENS_REVIEW_5, 1);
										htmltext = "30461-02t5.html";
									}
								}
							}
							else
							{
								if ((getQuestItemsCount(player, MIRIENS_REVIEW_1) + getQuestItemsCount(player, MIRIENS_REVIEW_2) + getQuestItemsCount(player, MIRIENS_REVIEW_3) + getQuestItemsCount(player, MIRIENS_REVIEW_4) + getQuestItemsCount(player, MIRIENS_REVIEW_5)) == 1)
								{
									htmltext = "30461-04.html";
								}
							}
						}
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

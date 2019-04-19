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
package quests.Q00372_LegacyOfInsolence;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.QuestItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Legacy Of Insolence (372)
 * @author ivantotov
 */
public final class Q00372_LegacyOfInsolence extends Quest
{
	// NPCs
	private static final int TRADER_HOLLY = 30839;
	private static final int WAREHOUSE_KEEPER_WALDERAL = 30844;
	private static final int MAGISTER_DESMOND = 30855;
	private static final int ANTIQUE_DEALER_PATRIN = 30929;
	private static final int CLAUDIA_ATHEBALDT = 31001;
	// Items
	private static final int ANCIENT_RED_PAPYRUS = 5966;
	private static final int ANCIENT_BLUE_PAPYRUS = 5967;
	private static final int ANCIENT_BLACK_PAPYRUS = 5968;
	private static final int ANCIENT_WHITE_PAPYRUS = 5969;
	private static final int REVELATION_OF_THE_SEALS_CHAPTER_OF_AVARICE = 5972;
	private static final int REVELATION_OF_THE_SEALS_CHAPTER_OF_GNOSIS = 5973;
	private static final int REVELATION_OF_THE_SEALS_CHAPTER_OF_STRIFE = 5974;
	private static final int REVELATION_OF_THE_SEALS_CHAPTER_OF_VENGEANCE = 5975;
	private static final int REVELATION_OF_THE_SEALS_CHAPTER_OF_AWEKENING = 5976;
	private static final int REVELATION_OF_THE_SEALS_CHAPTER_OF_CALAMITY = 5977;
	private static final int REVELATION_OF_THE_SEALS_CHAPTER_OF_DESCENT = 5978;
	private static final int ANCIENT_EPIC_CHAPTER_1 = 5979;
	private static final int ANCIENT_EPIC_CHAPTER_2 = 5980;
	private static final int ANCIENT_EPIC_CHAPTER_3 = 5981;
	private static final int ANCIENT_EPIC_CHAPTER_4 = 5982;
	private static final int ANCIENT_EPIC_CHAPTER_5 = 5983;
	private static final int IMPERIAL_GENEALOGY_1 = 5984;
	private static final int IMPERIAL_GENEALOGY_2 = 5985;
	private static final int IMPERIAL_GENEALOGY_3 = 5986;
	private static final int IMPERIAL_GENEALOGY_4 = 5987;
	private static final int IMPERIAL_GENEALOGY_5 = 5988;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR = 5989;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR = 5990;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR = 5991;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR = 5992;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR = 5993;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR = 5994;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR = 5995;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR = 5996;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR = 5997;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR = 5998;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR = 5999;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR = 6000;
	private static final int BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR = 6001;
	// Rewards
	private static final int RECIPE_SEALED_DARK_CRYSTAL_BOOTS_60 = 5368;
	private static final int RECIPE_SEALED_TALLUM_BOOTS_60 = 5370;
	private static final int RECIPE_SEALED_BOOTS_OF_NIGHTMARE_60 = 5380;
	private static final int RECIPE_SEALED_MAJESTIC_BOOTS_60 = 5382;
	private static final int RECIPE_SEALED_DARK_CRYSTAL_GLOVES_60 = 5392;
	private static final int RECIPE_SEALED_TALLUM_GLOVES_60 = 5394;
	private static final int RECIPE_SEALED_GAUNTLETS_OF_NIGHTMARE_60 = 5404;
	private static final int RECIPE_SEALED_MAJESTIC_GAUNTLETS_60 = 5406;
	private static final int RECIPE_SEALED_DARK_CRYSTAL_HELMET_60 = 5426;
	private static final int RECIPE_SEALED_TALLUM_HELMET_60 = 5428;
	private static final int RECIPE_SEALED_HELM_OF_NIGHTMARE_60 = 5430;
	private static final int RECIPE_SEALED_MAJESTIC_CIRCLET_60 = 5432;
	private static final int SEALED_DARK_CRYSTAL_BOOTS_LINING = 5496;
	private static final int SEALED_TALLUM_BOOTS_LINING = 5497;
	private static final int SEALED_BOOTS_OF_NIGHTMARE_LINING = 5502;
	private static final int SEALED_MAJESTIC_BOOTS_LINING = 5503;
	private static final int SEALED_DARK_CRYSTAL_GLOVES_DESIGN = 5508;
	private static final int SEALED_TALLUM_GLOVES_DESIGN = 5509;
	private static final int SEALED_GAUNTLETS_OF_NIGHTMARE_DESIGN = 5514;
	private static final int SEALED_MAJESTIC_GAUNTLETS_DESIGN = 5515;
	private static final int SEALED_DARK_CRYSTAL_HELMET_DESIGN = 5525;
	private static final int SEALED_TALLUM_HELM_DESIGN = 5526;
	private static final int SEALED_HELM_OF_NIGHTMARE_DESIGN = 5527;
	private static final int SEALED_MAJESTIC_CIRCLET_DESIGN = 5528;
	// Monsters
	private static final int HALLATES_INSPECTOR = 20825;
	private static final Map<Integer, QuestItemHolder> MONSTER_REWARDS = new HashMap<>();
	
	static
	{
		MONSTER_REWARDS.put(20817, new QuestItemHolder(ANCIENT_RED_PAPYRUS, 302, 1));
		MONSTER_REWARDS.put(20821, new QuestItemHolder(ANCIENT_RED_PAPYRUS, 410, 1));
		MONSTER_REWARDS.put(HALLATES_INSPECTOR, new QuestItemHolder(ANCIENT_RED_PAPYRUS, 1, 447));
		MONSTER_REWARDS.put(20829, new QuestItemHolder(ANCIENT_BLUE_PAPYRUS, 451, 1));
		MONSTER_REWARDS.put(21062, new QuestItemHolder(ANCIENT_WHITE_PAPYRUS, 290, 1));
		MONSTER_REWARDS.put(21069, new QuestItemHolder(ANCIENT_BLACK_PAPYRUS, 280, 1));
	}
	
	// Misc
	private static final int MIN_LEVEL = 59;
	
	public Q00372_LegacyOfInsolence()
	{
		super(372);
		addStartNpc(WAREHOUSE_KEEPER_WALDERAL);
		addTalkId(WAREHOUSE_KEEPER_WALDERAL, TRADER_HOLLY, MAGISTER_DESMOND, ANTIQUE_DEALER_PATRIN, CLAUDIA_ATHEBALDT);
		addKillId(MONSTER_REWARDS.keySet());
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		final int chance = getRandom(100);
		
		if (qs == null)
		{
			return super.onAdvEvent(event, npc, player);
		}
		
		String htmltext = null;
		switch (event)
		{
			case "30844-04.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30844-07.html":
			{
				if (hasQuestItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR))
				{
					htmltext = event;
				}
				else
				{
					htmltext = "30844-06.html";
				}
				break;
			}
			case "30844-09.html":
			{
				qs.exitQuest(true, true);
				htmltext = event;
				break;
			}
			case "30844-07a.html":
			{
				if (hasQuestItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR))
				{
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR, 1);
					
					if (chance < 10)
					{
						giveItems(player, SEALED_DARK_CRYSTAL_BOOTS_LINING, 1);
					}
					else if (chance < 20)
					{
						giveItems(player, SEALED_DARK_CRYSTAL_GLOVES_DESIGN, 1);
					}
					else if (chance < 30)
					{
						giveItems(player, SEALED_DARK_CRYSTAL_HELMET_DESIGN, 1);
					}
					else if (chance < 40)
					{
						giveItems(player, SEALED_DARK_CRYSTAL_BOOTS_LINING, 1);
						giveItems(player, SEALED_DARK_CRYSTAL_GLOVES_DESIGN, 1);
						giveItems(player, SEALED_DARK_CRYSTAL_HELMET_DESIGN, 1);
					}
					else if (chance < 51)
					{
						giveItems(player, RECIPE_SEALED_DARK_CRYSTAL_BOOTS_60, 1);
					}
					else if (chance < 62)
					{
						giveItems(player, RECIPE_SEALED_DARK_CRYSTAL_GLOVES_60, 1);
					}
					else if (chance < 79)
					{
						giveItems(player, RECIPE_SEALED_DARK_CRYSTAL_HELMET_60, 1);
					}
					else if (chance < 100)
					{
						giveItems(player, RECIPE_SEALED_DARK_CRYSTAL_BOOTS_60, 1);
						giveItems(player, RECIPE_SEALED_DARK_CRYSTAL_GLOVES_60, 1);
						giveItems(player, RECIPE_SEALED_DARK_CRYSTAL_HELMET_60, 1);
					}
					htmltext = event;
				}
				else
				{
					htmltext = "30844-07e.html";
				}
				break;
			}
			case "30844-07b.html":
			{
				if (hasQuestItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR))
				{
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR, 1);
					
					if (chance < 10)
					{
						giveItems(player, SEALED_TALLUM_BOOTS_LINING, 1);
					}
					else if (chance < 20)
					{
						giveItems(player, SEALED_TALLUM_GLOVES_DESIGN, 1);
					}
					else if (chance < 30)
					{
						giveItems(player, SEALED_TALLUM_HELM_DESIGN, 1);
					}
					else if (chance < 40)
					{
						giveItems(player, SEALED_TALLUM_BOOTS_LINING, 1);
						giveItems(player, SEALED_TALLUM_GLOVES_DESIGN, 1);
						giveItems(player, SEALED_TALLUM_HELM_DESIGN, 1);
					}
					else if (chance < 51)
					{
						giveItems(player, RECIPE_SEALED_TALLUM_BOOTS_60, 1);
					}
					else if (chance < 62)
					{
						giveItems(player, RECIPE_SEALED_TALLUM_GLOVES_60, 1);
					}
					else if (chance < 79)
					{
						giveItems(player, RECIPE_SEALED_TALLUM_HELMET_60, 1);
					}
					else if (chance < 100)
					{
						giveItems(player, RECIPE_SEALED_TALLUM_BOOTS_60, 1);
						giveItems(player, RECIPE_SEALED_TALLUM_GLOVES_60, 1);
						giveItems(player, RECIPE_SEALED_TALLUM_HELMET_60, 1);
					}
					htmltext = event;
				}
				else
				{
					htmltext = "30844-07e.html";
				}
				break;
			}
			case "30844-07c.html":
			{
				if (hasQuestItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR))
				{
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR, 1);
					
					if (chance < 17)
					{
						giveItems(player, SEALED_BOOTS_OF_NIGHTMARE_LINING, 1);
					}
					else if (chance < 34)
					{
						giveItems(player, SEALED_GAUNTLETS_OF_NIGHTMARE_DESIGN, 1);
					}
					else if (chance < 49)
					{
						giveItems(player, SEALED_HELM_OF_NIGHTMARE_DESIGN, 1);
					}
					else if (chance < 58)
					{
						giveItems(player, SEALED_BOOTS_OF_NIGHTMARE_LINING, 1);
						giveItems(player, SEALED_GAUNTLETS_OF_NIGHTMARE_DESIGN, 1);
						giveItems(player, SEALED_HELM_OF_NIGHTMARE_DESIGN, 1);
					}
					else if (chance < 70)
					{
						giveItems(player, RECIPE_SEALED_BOOTS_OF_NIGHTMARE_60, 1);
					}
					else if (chance < 82)
					{
						giveItems(player, RECIPE_SEALED_GAUNTLETS_OF_NIGHTMARE_60, 1);
					}
					else if (chance < 92)
					{
						giveItems(player, RECIPE_SEALED_HELM_OF_NIGHTMARE_60, 1);
					}
					else if (chance < 100)
					{
						giveItems(player, RECIPE_SEALED_BOOTS_OF_NIGHTMARE_60, 1);
						giveItems(player, RECIPE_SEALED_GAUNTLETS_OF_NIGHTMARE_60, 1);
						giveItems(player, RECIPE_SEALED_HELM_OF_NIGHTMARE_60, 1);
					}
					htmltext = event;
				}
				else
				{
					htmltext = "30844-07e.html";
				}
				break;
			}
			case "30844-07d.html":
			{
				if (hasQuestItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, //
					BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR))
				{
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_1ST_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_2ND_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_3RD_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_4TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_5TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_6TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_7TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_8TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_9TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_10TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_11TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_12TH_FLOOR, 1);
					takeItems(player, BLUEPRINT_TOWER_OF_INSOLENCE_13TH_FLOOR, 1);
					
					if (chance < 17)
					{
						giveItems(player, SEALED_MAJESTIC_BOOTS_LINING, 1);
					}
					else if (chance < 34)
					{
						giveItems(player, SEALED_MAJESTIC_GAUNTLETS_DESIGN, 1);
					}
					else if (chance < 49)
					{
						giveItems(player, SEALED_MAJESTIC_CIRCLET_DESIGN, 1);
					}
					else if (chance < 58)
					{
						giveItems(player, SEALED_MAJESTIC_BOOTS_LINING, 1);
						giveItems(player, SEALED_MAJESTIC_GAUNTLETS_DESIGN, 1);
						giveItems(player, SEALED_MAJESTIC_CIRCLET_DESIGN, 1);
					}
					else if (chance < 70)
					{
						giveItems(player, RECIPE_SEALED_MAJESTIC_BOOTS_60, 1);
					}
					else if (chance < 82)
					{
						giveItems(player, RECIPE_SEALED_MAJESTIC_GAUNTLETS_60, 1);
					}
					else if (chance < 92)
					{
						giveItems(player, RECIPE_SEALED_MAJESTIC_CIRCLET_60, 1);
					}
					else if (chance < 100)
					{
						giveItems(player, RECIPE_SEALED_MAJESTIC_BOOTS_60, 1);
						giveItems(player, RECIPE_SEALED_MAJESTIC_GAUNTLETS_60, 1);
						giveItems(player, RECIPE_SEALED_MAJESTIC_CIRCLET_60, 1);
					}
					htmltext = event;
				}
				else
				{
					htmltext = "30844-07e.html";
				}
				break;
			}
			case "30844-05b.html":
			{
				qs.setCond(2);
				htmltext = event;
				break;
			}
			case "30844-03.htm":
			case "30844-05.html":
			case "30844-05a.html":
			case "30844-08.html":
			case "30844-10.html":
			case "30844-11.html":
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
		final QuestItemHolder item = MONSTER_REWARDS.get(npc.getId());
		if (npc.getId() == HALLATES_INSPECTOR)
		{
			if (getRandom(1000) < item.getChance())
			{
				final QuestState qs = getRandomPartyMemberState(killer, -1, 3, npc);
				if (qs != null)
				{
					giveItems(qs.getPlayer(), item.getId(), item.getCount());
					playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
			return super.onKill(npc, killer, isSummon);
		}
		
		if (Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true) && (getRandom(1000) < item.getChance()))
		{
			L2PcInstance rewardedPlayer = null;
			if (!killer.isInParty())
			{
				final QuestState qs = getQuestState(killer, false);
				if ((qs != null) && qs.isStarted())
				{
					rewardedPlayer = killer;
				}
			}
			else
			{
				int chance = 0;
				for (L2PcInstance partyMember : killer.getParty().getMembers())
				{
					final QuestState partyMemberQuestState = getQuestState(partyMember, false);
					if ((partyMemberQuestState != null) && partyMemberQuestState.isStarted())
					{
						final int chance2 = getRandom(1000);
						if (chance < chance2)
						{
							chance = chance2;
							rewardedPlayer = partyMember;
						}
					}
				}
			}
			
			if ((rewardedPlayer != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, rewardedPlayer, true))
			{
				giveItems(rewardedPlayer, item.getId(), item.getCount());
				playSound(rewardedPlayer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		final int chance = getRandom(100);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == WAREHOUSE_KEEPER_WALDERAL)
			{
				if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "30844-01.htm";
				}
				else
				{
					htmltext = "30844-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case WAREHOUSE_KEEPER_WALDERAL:
				{
					htmltext = "30844-05.html";
					break;
				}
				case TRADER_HOLLY:
				{
					if (hasQuestItems(player, IMPERIAL_GENEALOGY_1, IMPERIAL_GENEALOGY_2, IMPERIAL_GENEALOGY_3, IMPERIAL_GENEALOGY_4, IMPERIAL_GENEALOGY_5))
					{
						takeItems(player, IMPERIAL_GENEALOGY_1, 1);
						takeItems(player, IMPERIAL_GENEALOGY_2, 1);
						takeItems(player, IMPERIAL_GENEALOGY_3, 1);
						takeItems(player, IMPERIAL_GENEALOGY_4, 1);
						takeItems(player, IMPERIAL_GENEALOGY_5, 1);
						
						if (chance < 30)
						{
							giveItems(player, SEALED_DARK_CRYSTAL_BOOTS_LINING, 1);
						}
						else if (chance < 60)
						{
							giveItems(player, SEALED_DARK_CRYSTAL_GLOVES_DESIGN, 1);
						}
						else if (chance < 80)
						{
							giveItems(player, SEALED_DARK_CRYSTAL_HELMET_DESIGN, 1);
						}
						else if (chance < 90)
						{
							giveItems(player, SEALED_DARK_CRYSTAL_BOOTS_LINING, 1);
							giveItems(player, SEALED_DARK_CRYSTAL_GLOVES_DESIGN, 1);
							giveItems(player, SEALED_DARK_CRYSTAL_HELMET_DESIGN, 1);
						}
						else if (chance < 100)
						{
							giveAdena(player, 4000, true);
						}
						htmltext = "30839-02.html";
					}
					else
					{
						htmltext = "30839-01.html";
					}
					break;
				}
				case MAGISTER_DESMOND:
				{
					if (hasQuestItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_AVARICE, REVELATION_OF_THE_SEALS_CHAPTER_OF_GNOSIS, REVELATION_OF_THE_SEALS_CHAPTER_OF_STRIFE, //
						REVELATION_OF_THE_SEALS_CHAPTER_OF_VENGEANCE, REVELATION_OF_THE_SEALS_CHAPTER_OF_AWEKENING, REVELATION_OF_THE_SEALS_CHAPTER_OF_CALAMITY, //
						REVELATION_OF_THE_SEALS_CHAPTER_OF_DESCENT))
					{
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_AVARICE, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_GNOSIS, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_STRIFE, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_VENGEANCE, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_AWEKENING, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_CALAMITY, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_DESCENT, 1);
						
						if (chance < 31)
						{
							giveItems(player, SEALED_MAJESTIC_BOOTS_LINING, 1);
						}
						else if (chance < 62)
						{
							giveItems(player, SEALED_MAJESTIC_GAUNTLETS_DESIGN, 1);
						}
						else if (chance < 75)
						{
							giveItems(player, SEALED_MAJESTIC_CIRCLET_DESIGN, 1);
						}
						else if (chance < 83)
						{
							giveItems(player, SEALED_MAJESTIC_BOOTS_LINING, 1);
							giveItems(player, SEALED_MAJESTIC_GAUNTLETS_DESIGN, 1);
							giveItems(player, SEALED_MAJESTIC_CIRCLET_DESIGN, 1);
						}
						else if (chance < 100)
						{
							giveAdena(player, 4000, true);
						}
						htmltext = "30855-02.html";
					}
					else
					{
						htmltext = "30855-01.html";
					}
					break;
				}
				case ANTIQUE_DEALER_PATRIN:
				{
					if (hasQuestItems(player, ANCIENT_EPIC_CHAPTER_1, ANCIENT_EPIC_CHAPTER_2, ANCIENT_EPIC_CHAPTER_3, ANCIENT_EPIC_CHAPTER_4, ANCIENT_EPIC_CHAPTER_5))
					{
						takeItems(player, ANCIENT_EPIC_CHAPTER_1, 1);
						takeItems(player, ANCIENT_EPIC_CHAPTER_2, 1);
						takeItems(player, ANCIENT_EPIC_CHAPTER_3, 1);
						takeItems(player, ANCIENT_EPIC_CHAPTER_4, 1);
						takeItems(player, ANCIENT_EPIC_CHAPTER_5, 1);
						
						if (chance < 30)
						{
							giveItems(player, SEALED_TALLUM_BOOTS_LINING, 1);
						}
						else if (chance < 60)
						{
							giveItems(player, SEALED_TALLUM_GLOVES_DESIGN, 1);
						}
						else if (chance < 80)
						{
							giveItems(player, SEALED_TALLUM_HELM_DESIGN, 1);
						}
						else if (chance < 90)
						{
							giveItems(player, SEALED_TALLUM_BOOTS_LINING, 1);
							giveItems(player, SEALED_TALLUM_GLOVES_DESIGN, 1);
							giveItems(player, SEALED_TALLUM_HELM_DESIGN, 1);
						}
						else if (chance < 100)
						{
							giveAdena(player, 4000, true);
						}
						htmltext = "30929-02.html";
					}
					else
					{
						htmltext = "30929-02.html";
					}
					break;
				}
				case CLAUDIA_ATHEBALDT:
				{
					if (hasQuestItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_AVARICE, REVELATION_OF_THE_SEALS_CHAPTER_OF_GNOSIS, REVELATION_OF_THE_SEALS_CHAPTER_OF_STRIFE, //
						REVELATION_OF_THE_SEALS_CHAPTER_OF_VENGEANCE, REVELATION_OF_THE_SEALS_CHAPTER_OF_AWEKENING, REVELATION_OF_THE_SEALS_CHAPTER_OF_CALAMITY, //
						REVELATION_OF_THE_SEALS_CHAPTER_OF_DESCENT))
					{
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_AVARICE, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_GNOSIS, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_STRIFE, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_VENGEANCE, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_AWEKENING, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_CALAMITY, 1);
						takeItems(player, REVELATION_OF_THE_SEALS_CHAPTER_OF_DESCENT, 1);
						
						if (chance < 31)
						{
							giveItems(player, SEALED_BOOTS_OF_NIGHTMARE_LINING, 1);
						}
						else if (chance < 62)
						{
							giveItems(player, SEALED_GAUNTLETS_OF_NIGHTMARE_DESIGN, 1);
						}
						else if (chance < 75)
						{
							giveItems(player, SEALED_HELM_OF_NIGHTMARE_DESIGN, 1);
						}
						else if (chance < 83)
						{
							giveItems(player, SEALED_BOOTS_OF_NIGHTMARE_LINING, 1);
							giveItems(player, SEALED_GAUNTLETS_OF_NIGHTMARE_DESIGN, 1);
							giveItems(player, SEALED_HELM_OF_NIGHTMARE_DESIGN, 1);
						}
						else if (chance < 100)
						{
							giveAdena(player, 4000, true);
						}
						htmltext = "31001-02.html";
					}
					else
					{
						htmltext = "31001-01.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

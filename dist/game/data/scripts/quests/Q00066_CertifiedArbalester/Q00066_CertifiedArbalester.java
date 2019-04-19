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
package quests.Q00066_CertifiedArbalester;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;
import com.l2jmobius.gameserver.util.Util;

/**
 * Certified Arbalester (66)
 * @author ivantotov
 */
public final class Q00066_CertifiedArbalester extends Quest
{
	// NPCs
	private static final int WAREHOUSE_KEEPER_HOLVAS = 30058;
	private static final int MAGISTER_GAIUS = 30171;
	private static final int BLACKSMITH_POITAN = 30458;
	private static final int MAGISTER_CLAYTON = 30464;
	private static final int MAGISTER_GAUEN = 30717;
	private static final int MAGISTER_KAIENA = 30720;
	private static final int MASTER_RINDY = 32201;
	private static final int GRAND_MASTER_MELDINA = 32214;
	private static final int MASTER_SELSIA = 32220;
	// Items
	private static final int ENMITY_CRYSTAL = 9773;
	private static final int ENMITY_CRYSTAL_CORE = 9774;
	private static final int MANUSCRIPT_PAGE = 9775;
	private static final int ENCODED_PAGE_ON_THE_ANCIENT_RACE = 9776;
	private static final int KAMAEL_INQUISITOR_TRAINEE_MARK = 9777;
	private static final int FRAGMENT_OF_ATTACK_ORDERS = 9778;
	private static final int GRANDIS_ATTACK_ORDERS = 9779;
	private static final int MANASHENS_TALISMAN = 9780;
	private static final int RESEARCH_ON_THE_GIANTS_AND_THE_ANCIENT_RACE = 9781;
	// Reward
	private static final int DIMENSIONAL_DIAMOND = 7562;
	private static final int KAMAEL_INQUISITOR_MARK = 9782;
	// Monster
	private static final int GRANITIC_GOLEM = 20083;
	private static final int HANGMAN_TREE = 20144;
	private static final int AMBER_BASILISK = 20199;
	private static final int STRAIN = 20200;
	private static final int GHOUL = 20201;
	private static final int DEAD_SEEKER = 20202;
	private static final int GRANDIS = 20554;
	private static final int MANASHEN_GARGOYLE = 20563;
	private static final int TIMAK_ORC = 20583;
	private static final int TIMAK_ORC_ARCHER = 20584;
	private static final int DELU_LIZARDMAN_SHAMAN = 20781;
	private static final int WATCHMAN_OF_THE_PLAINS = 21102;
	private static final int ROUGHLY_HEWN_ROCK_GOLEM = 21103;
	private static final int DELU_LIZARDMAN_SUPPLIER = 21104;
	private static final int DELU_LIZARDMAN_AGENT = 21105;
	private static final int CURSED_SEER = 21106;
	private static final int DELU_LIZARDMAN_COMMANDER = 21107;
	// Quest Monster
	private static final int CRIMSON_LADY = 27336;
	// Misc
	private static final int MIN_LEVEL = 39;
	
	public Q00066_CertifiedArbalester()
	{
		super(66);
		addStartNpc(MASTER_RINDY);
		addTalkId(MASTER_RINDY, WAREHOUSE_KEEPER_HOLVAS, MAGISTER_GAIUS, BLACKSMITH_POITAN, MAGISTER_CLAYTON, MAGISTER_GAUEN, MAGISTER_KAIENA, GRAND_MASTER_MELDINA, MASTER_SELSIA);
		addKillId(GRANITIC_GOLEM, HANGMAN_TREE, AMBER_BASILISK, STRAIN, GHOUL, DEAD_SEEKER, GRANDIS, MANASHEN_GARGOYLE, TIMAK_ORC, TIMAK_ORC_ARCHER, DELU_LIZARDMAN_SHAMAN, WATCHMAN_OF_THE_PLAINS, ROUGHLY_HEWN_ROCK_GOLEM, DELU_LIZARDMAN_SUPPLIER, DELU_LIZARDMAN_AGENT, CURSED_SEER, DELU_LIZARDMAN_COMMANDER, CRIMSON_LADY);
		registerQuestItems(ENMITY_CRYSTAL, ENMITY_CRYSTAL_CORE, MANUSCRIPT_PAGE, ENCODED_PAGE_ON_THE_ANCIENT_RACE, KAMAEL_INQUISITOR_TRAINEE_MARK, FRAGMENT_OF_ATTACK_ORDERS, GRANDIS_ATTACK_ORDERS, MANASHENS_TALISMAN, RESEARCH_ON_THE_GIANTS_AND_THE_ANCIENT_RACE);
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
			case "ACCEPT":
			{
				if ((player.getLevel() >= MIN_LEVEL) && (player.getClassId() == ClassId.WARDER) && !hasQuestItems(player, KAMAEL_INQUISITOR_MARK))
				{
					qs.startQuest();
					qs.setMemoState(1);
					if (player.getVariables().getInt("2ND_CLASS_DIAMOND_REWARD", 0) == 0)
					{
						giveItems(player, DIMENSIONAL_DIAMOND, 64);
						player.getVariables().set("2ND_CLASS_DIAMOND_REWARD", 1);
						htmltext = "32201-07a.htm";
					}
					else
					{
						htmltext = "32201-07.htm";
					}
				}
				break;
			}
			case "32201-08.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "30058-03.html":
			case "30058-04.html":
			{
				if (qs.isMemoState(7))
				{
					htmltext = event;
				}
				break;
			}
			case "30058-05.html":
			{
				if (qs.isMemoState(7))
				{
					qs.setMemoState(8);
					qs.setCond(7, true);
					htmltext = event;
				}
				break;
			}
			case "30058-08.html":
			{
				if (qs.isMemoState(9))
				{
					giveItems(player, ENCODED_PAGE_ON_THE_ANCIENT_RACE, 1);
					qs.setMemoState(10);
					qs.setCond(9, true);
					htmltext = event;
				}
				break;
			}
			case "30171-03.html":
			{
				if (qs.isMemoState(23))
				{
					htmltext = event;
				}
				break;
			}
			case "30171-05.html":
			{
				if (qs.isMemoState(23))
				{
					takeItems(player, GRANDIS_ATTACK_ORDERS, -1);
					qs.setMemoState(24);
					htmltext = event;
				}
				break;
			}
			case "30171-06.html":
			case "30171-07.html":
			{
				if (qs.isMemoState(24))
				{
					htmltext = event;
				}
				break;
			}
			case "30171-08.html":
			{
				if (qs.isMemoState(24))
				{
					qs.setMemoState(25);
				}
				qs.setCond(14, true);
				htmltext = event;
				break;
			}
			case "30458-03.html":
			{
				if (qs.isMemoState(5))
				{
					takeItems(player, ENMITY_CRYSTAL_CORE, 1);
					qs.setMemoState(6);
					htmltext = event;
				}
				break;
			}
			case "30458-05.html":
			case "30458-06.html":
			case "30458-07.html":
			case "30458-08.html":
			{
				if (qs.isMemoState(6))
				{
					htmltext = event;
				}
				break;
			}
			case "30458-09.html":
			{
				if (qs.isMemoState(6))
				{
					qs.setMemoState(7);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "30464-03.html":
			case "30464-04.html":
			case "30464-05.html":
			{
				if (qs.isMemoState(2))
				{
					htmltext = event;
				}
				break;
			}
			case "30464-06.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setMemoState(3);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "30464-09.html":
			{
				if (qs.isMemoState(4))
				{
					giveItems(player, ENMITY_CRYSTAL_CORE, 1);
					qs.setMemoState(5);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "30464-11.html":
			{
				htmltext = event;
				break;
			}
			case "30717-03.html":
			case "30717-05.html":
			case "30717-06.html":
			case "30717-07.html":
			case "30717-08.html":
			{
				if (qs.isMemoState(28))
				{
					htmltext = event;
				}
				break;
			}
			case "30717-09.html":
			{
				if (qs.isMemoState(28))
				{
					qs.setMemoState(29);
					qs.setCond(17, true);
					htmltext = event;
				}
				break;
			}
			case "30720-03.html":
			{
				if (qs.isMemoState(29))
				{
					htmltext = event;
				}
				break;
			}
			case "30720-04.html":
			{
				if (qs.isMemoState(29))
				{
					qs.setMemoState(30);
					qs.setCond(18, true);
					htmltext = event;
				}
				break;
			}
			case "32214-03.html":
			{
				if (qs.isMemoState(10))
				{
					htmltext = event;
				}
				break;
			}
			case "32214-04.html":
			{
				if (qs.isMemoState(10))
				{
					takeItems(player, ENCODED_PAGE_ON_THE_ANCIENT_RACE, 1);
					giveItems(player, KAMAEL_INQUISITOR_TRAINEE_MARK, 1);
					qs.setMemoState(11);
					qs.setCond(10, true);
					htmltext = event;
				}
				break;
			}
			case "32220-03.html":
			{
				if (qs.isMemoState(11))
				{
					takeItems(player, KAMAEL_INQUISITOR_TRAINEE_MARK, -1);
					qs.setMemoState(12);
					htmltext = event;
				}
				break;
			}
			case "32220-05.html":
			{
				if (qs.isMemoState(12))
				{
					qs.setMemoState(13);
					htmltext = event;
				}
				break;
			}
			case "32220-06.html":
			{
				if (qs.isMemoState(13))
				{
					qs.setMemoStateEx(1, 0);
					htmltext = event;
				}
				break;
			}
			case "32220-09.html":
			case "32220-10.html":
			{
				if (qs.isMemoState(13))
				{
					htmltext = event;
				}
				break;
			}
			case "32220-11.html":
			case "32220-12.html":
			case "32220-13.html":
			{
				if (qs.isMemoState(13))
				{
					qs.setMemoState(13);
					qs.setMemoStateEx(1, 1);
					htmltext = event;
				}
				break;
			}
			case "32220-13a.html":
			{
				if (qs.isMemoState(13))
				{
					qs.setMemoState(20);
					qs.setMemoStateEx(1, 0);
					htmltext = event;
				}
				break;
			}
			case "32220-13b.html":
			{
				if (qs.isMemoState(20))
				{
					qs.setMemoState(21);
					qs.setCond(11, true);
					htmltext = event;
				}
				break;
			}
			case "32220-19.html":
			case "32220-21.html":
			case "32220-22.html":
			case "32220-23.html":
			case "32220-24.html":
			case "32220-25.html":
			{
				if (qs.isMemoState(31))
				{
					htmltext = event;
				}
				break;
			}
			case "32220-26.html":
			{
				if (qs.isMemoState(31))
				{
					qs.setMemoStateEx(1, 0);
					qs.setMemoState(32);
					qs.setCond(19, true);
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
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case GRANITIC_GOLEM:
				case HANGMAN_TREE:
				{
					if (qs.isMemoState(8) && (getQuestItemsCount(killer, MANUSCRIPT_PAGE) < 30))
					{
						if (getQuestItemsCount(killer, MANUSCRIPT_PAGE) >= 29)
						{
							qs.setCond(8, true);
						}
						else
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						giveItems(killer, MANUSCRIPT_PAGE, 1);
						if ((getRandom(1000) < 100) && (getQuestItemsCount(killer, MANUSCRIPT_PAGE) < 29))
						{
							giveItems(killer, MANUSCRIPT_PAGE, 1);
						}
					}
					break;
				}
				case AMBER_BASILISK:
				{
					if (qs.isMemoState(8) && (getQuestItemsCount(killer, MANUSCRIPT_PAGE) < 30))
					{
						if (getRandom(1000) < 980)
						{
							if (getQuestItemsCount(killer, MANUSCRIPT_PAGE) >= 29)
							{
								qs.setCond(8, true);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							giveItems(killer, MANUSCRIPT_PAGE, 1);
						}
					}
					break;
				}
				case STRAIN:
				{
					if (qs.isMemoState(8) && (getQuestItemsCount(killer, MANUSCRIPT_PAGE) < 30))
					{
						if (getRandom(1000) < 860)
						{
							if (getQuestItemsCount(killer, MANUSCRIPT_PAGE) >= 29)
							{
								qs.setCond(8, true);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							giveItems(killer, MANUSCRIPT_PAGE, 1);
						}
					}
					break;
				}
				case GHOUL:
				case DEAD_SEEKER:
				{
					if (qs.isMemoState(8) && (getQuestItemsCount(killer, MANUSCRIPT_PAGE) < 30))
					{
						if (getQuestItemsCount(killer, MANUSCRIPT_PAGE) >= 29)
						{
							qs.setCond(8, true);
						}
						else
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						giveItems(killer, MANUSCRIPT_PAGE, 1);
						if ((getRandom(1000) < 20) && (getQuestItemsCount(killer, MANUSCRIPT_PAGE) < 29))
						{
							giveItems(killer, MANUSCRIPT_PAGE, 1);
						}
					}
					break;
				}
				case GRANDIS:
				{
					if (qs.isMemoState(21) || (qs.isMemoState(22) && (getQuestItemsCount(killer, FRAGMENT_OF_ATTACK_ORDERS) < 10)))
					{
						if (getRandom(1000) < 780)
						{
							if (qs.isMemoState(21) && !hasQuestItems(killer, FRAGMENT_OF_ATTACK_ORDERS))
							{
								qs.setMemoState(22);
								qs.setCond(12, true);
								giveItems(killer, FRAGMENT_OF_ATTACK_ORDERS, 1);
							}
							else if (qs.isMemoState(22) && (getQuestItemsCount(killer, FRAGMENT_OF_ATTACK_ORDERS) >= 9))
							{
								qs.setMemoState(23);
								qs.setCond(13, true);
								takeItems(killer, FRAGMENT_OF_ATTACK_ORDERS, -1);
								giveItems(killer, GRANDIS_ATTACK_ORDERS, 1);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
								giveItems(killer, FRAGMENT_OF_ATTACK_ORDERS, 1);
							}
						}
						break;
					}
				}
				case MANASHEN_GARGOYLE:
				{
					if (qs.isMemoState(25) || (qs.isMemoState(26) && (getQuestItemsCount(killer, MANASHENS_TALISMAN) < 10)))
					{
						if (getRandom(1000) < 840)
						{
							if (qs.isMemoState(25) && !hasQuestItems(killer, MANASHENS_TALISMAN))
							{
								qs.setMemoState(26);
								qs.setCond(15, true);
							}
							else if (qs.isMemoState(26) && (getQuestItemsCount(killer, MANASHENS_TALISMAN) >= 9))
							{
								qs.setMemoState(27);
								qs.setCond(16, true);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							giveItems(killer, MANASHENS_TALISMAN, 1);
						}
						break;
					}
				}
				case TIMAK_ORC:
				case TIMAK_ORC_ARCHER:
				{
					if (qs.isMemoState(32))
					{
						final int i4 = qs.getMemoStateEx(1);
						if (i4 < 5)
						{
							qs.setMemoStateEx(1, i4 + 1);
						}
						else if (i4 >= 4)
						{
							qs.setMemoStateEx(1, 0);
							addSpawn(CRIMSON_LADY, npc, true, 0, false);
						}
					}
					break;
				}
				case DELU_LIZARDMAN_SHAMAN:
				case DELU_LIZARDMAN_SUPPLIER:
				{
					if (qs.isMemoState(3) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 30))
					{
						if (getQuestItemsCount(killer, ENMITY_CRYSTAL) >= 29)
						{
							qs.setCond(4, true);
						}
						else
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						giveItems(killer, ENMITY_CRYSTAL, 1);
						if ((getRandom(1000) < 80) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 29))
						{
							giveItems(killer, ENMITY_CRYSTAL, 1);
						}
					}
					break;
				}
				case WATCHMAN_OF_THE_PLAINS:
				{
					if (qs.isMemoState(3) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 30))
					{
						if (getRandom(1000) < 840)
						{
							if (getQuestItemsCount(killer, ENMITY_CRYSTAL) >= 29)
							{
								qs.setCond(4, true);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							giveItems(killer, ENMITY_CRYSTAL, 1);
						}
					}
					break;
				}
				case ROUGHLY_HEWN_ROCK_GOLEM:
				{
					if (qs.isMemoState(3) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 30))
					{
						if (getRandom(1000) < 860)
						{
							if (getQuestItemsCount(killer, ENMITY_CRYSTAL) >= 29)
							{
								qs.setCond(4, true);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							giveItems(killer, ENMITY_CRYSTAL, 1);
						}
					}
					break;
				}
				case DELU_LIZARDMAN_AGENT:
				{
					if (qs.isMemoState(3) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 30))
					{
						if (getQuestItemsCount(killer, ENMITY_CRYSTAL) >= 29)
						{
							qs.setCond(4, true);
						}
						else
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						giveItems(killer, ENMITY_CRYSTAL, 1);
						if ((getRandom(1000) < 240) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 29))
						{
							giveItems(killer, ENMITY_CRYSTAL, 1);
						}
					}
					break;
				}
				case CURSED_SEER:
				{
					if (qs.isMemoState(3) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 30))
					{
						if (getQuestItemsCount(killer, ENMITY_CRYSTAL) >= 29)
						{
							qs.setCond(4, true);
						}
						else
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						giveItems(killer, ENMITY_CRYSTAL, 1);
						if ((getRandom(1000) < 40) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 29))
						{
							giveItems(killer, ENMITY_CRYSTAL, 1);
						}
					}
					break;
				}
				case DELU_LIZARDMAN_COMMANDER:
				{
					if (qs.isMemoState(3) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 30))
					{
						if (getQuestItemsCount(killer, ENMITY_CRYSTAL) >= 28)
						{
							qs.setCond(4, true);
						}
						
						if (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 29)
						{
							giveItems(killer, ENMITY_CRYSTAL, 2);
							if ((getRandom(1000) < 220) && (getQuestItemsCount(killer, ENMITY_CRYSTAL) < 28))
							{
								giveItems(killer, ENMITY_CRYSTAL, 1);
								if (getQuestItemsCount(killer, ENMITY_CRYSTAL) >= 27)
								{
									qs.setCond(4, true);
								}
								else
								{
									playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
								}
							}
						}
						else
						{
							giveItems(killer, ENMITY_CRYSTAL, 1);
						}
					}
					break;
				}
				case CRIMSON_LADY:
				{
					if (qs.isMemoState(32))
					{
						giveItems(killer, RESEARCH_ON_THE_GIANTS_AND_THE_ANCIENT_RACE, 1);
						qs.setMemoState(32);
						qs.setCond(20, true);
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
		final int memoState = qs.getMemoState();
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == MASTER_RINDY)
			{
				if ((player.getClassId() == ClassId.WARDER) && !hasQuestItems(player, KAMAEL_INQUISITOR_MARK))
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						htmltext = "32201-01.htm";
					}
					else
					{
						htmltext = "32201-02.html";
					}
				}
				else
				{
					htmltext = "32201-03.html";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case MASTER_RINDY:
				{
					if (memoState == 1)
					{
						qs.setMemoState(2);
						qs.setCond(2, true);
						htmltext = "32201-09.html";
					}
					else if (memoState == 2)
					{
						htmltext = "32201-10.html";
					}
					else if ((memoState > 2) && (memoState < 11))
					{
						htmltext = "32201-11.html";
					}
					else if (memoState >= 11)
					{
						htmltext = "32201-12.html";
					}
					break;
				}
				case WAREHOUSE_KEEPER_HOLVAS:
				{
					if (memoState < 7)
					{
						htmltext = "30058-01.html";
					}
					else if (memoState == 7)
					{
						htmltext = "30058-02.html";
					}
					else if (memoState == 8)
					{
						if (getQuestItemsCount(player, MANUSCRIPT_PAGE) < 30)
						{
							htmltext = "30058-06.html";
						}
						else
						{
							takeItems(player, MANUSCRIPT_PAGE, -1);
							qs.setMemoState(9);
							htmltext = "30058-07.html";
						}
					}
					else if (memoState == 9)
					{
						giveItems(player, ENCODED_PAGE_ON_THE_ANCIENT_RACE, 1);
						qs.setMemoState(10);
						qs.setCond(9, true);
						htmltext = "30058-09.html";
					}
					else if (memoState > 9)
					{
						htmltext = "30058-10.html";
					}
					break;
				}
				case MAGISTER_GAIUS:
				{
					if (memoState < 23)
					{
						htmltext = "30171-01.html";
					}
					else if (memoState == 23)
					{
						htmltext = "30171-02.html";
					}
					else if (memoState == 24)
					{
						htmltext = "30171-06.html";
					}
					else if (memoState == 25)
					{
						htmltext = "30171-09.html";
					}
					else if (memoState == 26)
					{
						htmltext = "30171-10.html";
					}
					else if (memoState == 27)
					{
						htmltext = "30171-11.html";
					}
					else if (memoState == 28)
					{
						htmltext = "30171-12.html";
					}
					else if (memoState == 29)
					{
						htmltext = "30171-13.html";
					}
					break;
				}
				case BLACKSMITH_POITAN:
				{
					if (memoState < 5)
					{
						htmltext = "30458-01.html";
					}
					else if (memoState == 5)
					{
						htmltext = "30458-02.html";
					}
					else if (memoState == 6)
					{
						htmltext = "30458-04.html";
					}
					else if (memoState == 7)
					{
						htmltext = "30458-10.html";
					}
					break;
				}
				case MAGISTER_CLAYTON:
				{
					if (memoState < 2)
					{
						htmltext = "30464-01.html";
					}
					else if (memoState == 2)
					{
						qs.setMemoState(2);
						htmltext = "30464-02.html";
					}
					else if (memoState == 3)
					{
						if (getQuestItemsCount(player, ENMITY_CRYSTAL) < 30)
						{
							htmltext = "30464-07.html";
						}
						else
						{
							takeItems(player, ENMITY_CRYSTAL, -1);
							qs.setMemoState(4);
							htmltext = "30464-08.html";
						}
					}
					else if (memoState == 4)
					{
						giveItems(player, ENMITY_CRYSTAL_CORE, 1);
						qs.setMemoState(5);
						qs.setCond(5, true);
						htmltext = "30464-10.html";
					}
					else if (memoState == 5)
					{
						htmltext = "30464-12.html";
					}
					else if (memoState > 5)
					{
						htmltext = "30464-13.html";
					}
					break;
				}
				case MAGISTER_GAUEN:
				{
					if (memoState < 27)
					{
						htmltext = "30717-01.html";
					}
					else if (memoState == 27)
					{
						takeItems(player, MANASHENS_TALISMAN, -1);
						qs.setMemoState(28);
						htmltext = "30717-02.html";
					}
					else if (memoState == 28)
					{
						htmltext = "30717-04.html";
					}
					else if (memoState >= 29)
					{
						htmltext = "30717-10.html";
					}
					break;
				}
				case MAGISTER_KAIENA:
				{
					if (memoState < 29)
					{
						htmltext = "30720-01.html";
					}
					else if (memoState == 29)
					{
						htmltext = "30720-02.html";
					}
					if (memoState >= 30)
					{
						htmltext = "30720-05.html";
					}
					break;
				}
				case GRAND_MASTER_MELDINA:
				{
					if (memoState < 10)
					{
						htmltext = "32214-01.html";
					}
					else if (memoState == 10)
					{
						htmltext = "32214-02.html";
					}
					if (memoState == 11)
					{
						htmltext = "32214-05.html";
					}
					if (memoState > 11)
					{
						htmltext = "32214-06.html";
					}
					break;
				}
				case MASTER_SELSIA:
				{
					if (memoState < 11)
					{
						htmltext = "32220-01.html";
					}
					else if (memoState == 11)
					{
						htmltext = "32220-02.html";
					}
					else if (memoState == 12)
					{
						htmltext = "32220-04.html";
					}
					else if (memoState == 13)
					{
						if (qs.getMemoStateEx(1) == 0)
						{
							qs.setMemoStateEx(1, 0);
							htmltext = "32220-07.html";
						}
						else if (qs.getMemoStateEx(1) == 1)
						{
							qs.setMemoStateEx(1, 0);
							htmltext = "32220-08.html";
						}
					}
					else if (memoState == 20)
					{
						qs.setMemoState(21);
						qs.setCond(11, true);
						htmltext = "32220-14.html";
					}
					else if (memoState == 21)
					{
						htmltext = "32220-15.html";
					}
					else if (memoState == 22)
					{
						htmltext = "32220-16.html";
					}
					else if ((memoState >= 23) && (memoState < 30))
					{
						htmltext = "32220-17.html";
					}
					else if (memoState == 30)
					{
						qs.setMemoState(31);
						htmltext = "32220-18.html";
					}
					else if (memoState == 31)
					{
						htmltext = "32220-20.html";
					}
					else if (memoState == 32)
					{
						if (!hasQuestItems(player, RESEARCH_ON_THE_GIANTS_AND_THE_ANCIENT_RACE))
						{
							htmltext = "32220-27.html";
						}
						else
						{
							giveAdena(player, 77666, true);
							giveItems(player, KAMAEL_INQUISITOR_MARK, 1);
							addExpAndSp(player, 429546, 29476);
							qs.exitQuest(false, true);
							player.sendPacket(new SocialAction(player.getObjectId(), 3));
							htmltext = "32220-28.html";
						}
					}
					break;
				}
			}
		}
		if (qs.isCompleted())
		{
			if (npc.getId() == MASTER_RINDY)
			{
				if (player.getClassId() == ClassId.ARBALESTER)
				{
					htmltext = "32201-05.html";
				}
				else
				{
					htmltext = "32201-06.html";
				}
			}
		}
		return htmltext;
	}
}
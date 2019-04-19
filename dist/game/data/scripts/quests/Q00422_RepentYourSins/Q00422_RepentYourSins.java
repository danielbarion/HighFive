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
package quests.Q00422_RepentYourSins;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Repent Your Sins (422)
 * @author ivantotov
 */
public final class Q00422_RepentYourSins extends Quest
{
	// NPCs
	private static final int BLACKSMITH_PUSHKIN = 30300;
	private static final int PIOTUR = 30597;
	private static final int ELDER_CASIAN = 30612;
	private static final int KATARI = 30668;
	private static final int MAGISTER_JOAN = 30718;
	private static final int BLACK_JUDGE = 30981;
	// Items
	private static final int RATMAN_SCAVENGERS_SKULL = 4326;
	private static final int TUREK_WAR_HOUNDS_TAIL = 4327;
	private static final int TYRANT_KINGPINS_HEART = 4328;
	private static final int TRISALIM_TARANTULAS_VENOM_SAC = 4329;
	private static final int PENITENTS_MANACLES1 = 4330;
	private static final int MANUAL_OF_MANACLES = 4331;
	private static final int PENITENTS_MANACLES = 4425;
	// Reward
	private static final int MANACLES_OF_PENITENT = 4426;
	// Materials
	private static final int SILVER_NUGGET = 1873;
	private static final int ADAMANTITE_NUGGET = 1877;
	private static final int COKES = 1879;
	private static final int STEEL = 1880;
	private static final int BLACKSMITHS_FRAME = 1892;
	// Monster
	private static final int SCAVENGER_WERERAT = 20039;
	private static final int TYRANT_KINGPIN = 20193;
	private static final int TUREK_WAR_HOUND = 20494;
	private static final int TRISALIM_TARANTULA = 20561;
	
	public Q00422_RepentYourSins()
	{
		super(422);
		addStartNpc(BLACK_JUDGE);
		addTalkId(BLACK_JUDGE, BLACKSMITH_PUSHKIN, PIOTUR, ELDER_CASIAN, KATARI, MAGISTER_JOAN);
		addKillId(SCAVENGER_WERERAT, TYRANT_KINGPIN, TUREK_WAR_HOUND, TRISALIM_TARANTULA);
		registerQuestItems(RATMAN_SCAVENGERS_SKULL, TUREK_WAR_HOUNDS_TAIL, TYRANT_KINGPINS_HEART, TRISALIM_TARANTULAS_VENOM_SAC, PENITENTS_MANACLES1, MANUAL_OF_MANACLES, PENITENTS_MANACLES);
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
				qs.startQuest();
				if ((player.getLevel() > 20) && (player.getLevel() < 31))
				{
					playSound(player, QuestSound.ITEMSOUND_QUEST_ACCEPT);
					qs.setMemoState(2);
					qs.setCond(3);
					htmltext = "30981-04.htm";
				}
				else if (player.getLevel() < 21)
				{
					qs.setMemoState(1);
					qs.setMemoStateEx(1, 0);
					qs.setCond(2);
					htmltext = "30981-03.htm";
				}
				else if ((player.getLevel() > 30) && (player.getLevel() < 41))
				{
					qs.setMemoState(3);
					qs.setCond(4);
					htmltext = "30981-05.htm";
				}
				else
				{
					qs.setMemoState(4);
					qs.setCond(5);
					htmltext = "30981-06.htm";
				}
				break;
			}
			case "30981-11.html":
			{
				if ((qs.getMemoState() >= 9) && (qs.getMemoState() <= 12))
				{
					if (hasAtLeastOneQuestItem(player, MANACLES_OF_PENITENT, PENITENTS_MANACLES1))
					{
						if (hasQuestItems(player, PENITENTS_MANACLES1))
						{
							takeItems(player, PENITENTS_MANACLES1, 1);
						}
						
						if (hasQuestItems(player, MANACLES_OF_PENITENT))
						{
							takeItems(player, MANACLES_OF_PENITENT, 1);
						}
						qs.setMemoStateEx(1, player.getLevel());
						giveItems(player, PENITENTS_MANACLES, 1);
						qs.setCond(16);
						htmltext = event;
					}
				}
				break;
			}
			case "30981-14.html":
			case "30981-17.html":
			{
				if ((qs.getMemoState() >= 9) && (qs.getMemoState() <= 12))
				{
					htmltext = event;
				}
				break;
			}
			case "30981-15t.html":
			{
				final L2ItemInstance petItem = player.getInventory().getItemByItemId(PENITENTS_MANACLES);
				final int petLevel = (petItem == null) ? 0 : petItem.getEnchantLevel();
				if ((qs.getMemoState() >= 9) && (qs.getMemoState() <= 12) && (petLevel > qs.getMemoStateEx(1)))
				{
					final L2Summon summon = player.getSummon();
					if (summon != null)
					{
						htmltext = event;
					}
					else
					{
						int i1 = 0;
						if (player.getLevel() > qs.getMemoStateEx(1))
						{
							i1 = petLevel - qs.getMemoStateEx(1) - (player.getLevel() - qs.getMemoStateEx(1));
						}
						else
						{
							i1 = petLevel - qs.getMemoStateEx(1);
						}
						
						if (i1 < 0)
						{
							i1 = 0;
						}
						
						final int i0 = getRandom(i1) + 1;
						if (player.getPkKills() <= i0)
						{
							giveItems(player, MANACLES_OF_PENITENT, 1);
							if (petItem != null)
							{
								takeItems(player, PENITENTS_MANACLES, -1);
							}
							htmltext = "30981-15.html";
							
							player.setPkKills(0);
							qs.exitQuest(true, true);
						}
						else
						{
							giveItems(player, MANACLES_OF_PENITENT, 1);
							if (petItem != null)
							{
								takeItems(player, PENITENTS_MANACLES, -1);
							}
							htmltext = "30981-16.html";
							
							player.setPkKills(player.getPkKills() - i0);
							qs.setMemoStateEx(1, 0);
						}
					}
				}
				break;
			}
			case "30981-18.html":
			{
				if ((qs.getMemoState() >= 9) && (qs.getMemoState() <= 12))
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
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case SCAVENGER_WERERAT:
				{
					if (qs.isMemoState(5) && (getQuestItemsCount(killer, RATMAN_SCAVENGERS_SKULL) < 10))
					{
						if (getQuestItemsCount(killer, RATMAN_SCAVENGERS_SKULL) == 9)
						{
							giveItems(killer, RATMAN_SCAVENGERS_SKULL, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						else
						{
							giveItems(killer, RATMAN_SCAVENGERS_SKULL, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case TYRANT_KINGPIN:
				{
					if (qs.isMemoState(7) && !hasQuestItems(killer, TYRANT_KINGPINS_HEART))
					{
						giveItems(killer, TYRANT_KINGPINS_HEART, 1);
						playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					}
					break;
				}
				case TUREK_WAR_HOUND:
				{
					if (qs.isMemoState(6) && (getQuestItemsCount(killer, TUREK_WAR_HOUNDS_TAIL) < 10))
					{
						if (getQuestItemsCount(killer, TUREK_WAR_HOUNDS_TAIL) == 9)
						{
							giveItems(killer, TUREK_WAR_HOUNDS_TAIL, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						else
						{
							giveItems(killer, TUREK_WAR_HOUNDS_TAIL, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case TRISALIM_TARANTULA:
				{
					if (qs.isMemoState(8) && (getQuestItemsCount(killer, TRISALIM_TARANTULAS_VENOM_SAC) < 3))
					{
						if (getQuestItemsCount(killer, TRISALIM_TARANTULAS_VENOM_SAC) == 2)
						{
							giveItems(killer, TRISALIM_TARANTULAS_VENOM_SAC, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						else
						{
							giveItems(killer, TRISALIM_TARANTULAS_VENOM_SAC, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
		final int memoState = qs.getMemoState();
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == BLACK_JUDGE)
			{
				if (player.getPkKills() == 0)
				{
					htmltext = "30981-01.htm";
				}
				else
				{
					htmltext = "30981-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case BLACK_JUDGE:
				{
					if (memoState == 1000)
					{
						takeItems(player, PENITENTS_MANACLES, 1);
					}
					else if (memoState < 9)
					{
						htmltext = "30981-07.html";
					}
					else if ((memoState >= 9) && (memoState <= 12))
					{
						if (!hasAtLeastOneQuestItem(player, MANUAL_OF_MANACLES, MANACLES_OF_PENITENT, PENITENTS_MANACLES1, PENITENTS_MANACLES))
						{
							giveItems(player, MANUAL_OF_MANACLES, 1);
							qs.setCond(14, true);
							htmltext = "30981-08.html";
						}
						else if (hasQuestItems(player, MANUAL_OF_MANACLES) && !hasAtLeastOneQuestItem(player, MANACLES_OF_PENITENT, PENITENTS_MANACLES1, PENITENTS_MANACLES))
						{
							htmltext = "30981-09.html";
						}
						else if (hasQuestItems(player, PENITENTS_MANACLES1) && !hasAtLeastOneQuestItem(player, MANUAL_OF_MANACLES, MANACLES_OF_PENITENT, PENITENTS_MANACLES))
						{
							htmltext = "30981-10.html";
						}
						else if (hasQuestItems(player, PENITENTS_MANACLES))
						{
							final L2ItemInstance petItem = player.getInventory().getItemByItemId(PENITENTS_MANACLES);
							final int petLevel = (petItem == null) ? 0 : petItem.getEnchantLevel();
							if (petLevel < (qs.getMemoStateEx(1) + 1))
							{
								htmltext = "30981-12.html";
							}
							else
							{
								htmltext = "30981-13.html";
							}
						}
						else if (hasQuestItems(player, MANACLES_OF_PENITENT) && !hasQuestItems(player, PENITENTS_MANACLES))
						{
							htmltext = "30981-16t.html";
						}
					}
					break;
				}
				case BLACKSMITH_PUSHKIN:
				{
					if ((memoState >= 9) && (memoState <= 12))
					{
						if (!hasAtLeastOneQuestItem(player, PENITENTS_MANACLES1, PENITENTS_MANACLES, MANACLES_OF_PENITENT) && hasQuestItems(player, MANUAL_OF_MANACLES))
						{
							if ((getQuestItemsCount(player, BLACKSMITHS_FRAME) > 0) && (getQuestItemsCount(player, STEEL) >= 5) && (getQuestItemsCount(player, ADAMANTITE_NUGGET) >= 2) && (getQuestItemsCount(player, SILVER_NUGGET) >= 10) && (getQuestItemsCount(player, COKES) >= 10))
							{
								takeItems(player, SILVER_NUGGET, 10);
								takeItems(player, ADAMANTITE_NUGGET, 2);
								takeItems(player, COKES, 10);
								takeItems(player, STEEL, 5);
								takeItems(player, BLACKSMITHS_FRAME, 1);
								giveItems(player, PENITENTS_MANACLES1, 1);
								takeItems(player, MANUAL_OF_MANACLES, 1);
								qs.setCond(15, true);
								htmltext = "30300-01.html";
							}
							else
							{
								htmltext = "30300-02.html";
							}
						}
						else if (hasAtLeastOneQuestItem(player, PENITENTS_MANACLES1, PENITENTS_MANACLES, MANACLES_OF_PENITENT))
						{
							htmltext = "30300-03.html";
						}
					}
					break;
					
				}
				case PIOTUR:
				{
					if (memoState == 2)
					{
						qs.setMemoState(6);
						qs.setCond(7, true);
						htmltext = "30597-01.html";
					}
					else if (memoState == 6)
					{
						if (getQuestItemsCount(player, TUREK_WAR_HOUNDS_TAIL) < 10)
						{
							htmltext = "30597-02.html";
						}
						else
						{
							takeItems(player, TUREK_WAR_HOUNDS_TAIL, -1);
							qs.setMemoState(10);
							qs.setCond(11, true);
							htmltext = "30597-03.html";
						}
					}
					else if (memoState == 10)
					{
						htmltext = "30597-04.html";
					}
					break;
				}
				case ELDER_CASIAN:
				{
					if (memoState == 3)
					{
						qs.setMemoState(7);
						qs.setCond(8, true);
						htmltext = "30612-01.html";
					}
					else if (memoState == 7)
					{
						if (!hasQuestItems(player, TYRANT_KINGPINS_HEART))
						{
							htmltext = "30612-02.html";
						}
						else
						{
							takeItems(player, TYRANT_KINGPINS_HEART, -1);
							qs.setMemoState(11);
							qs.setCond(12, true);
							htmltext = "30612-03.html";
						}
					}
					else if (memoState == 11)
					{
						htmltext = "30612-04.html";
					}
					break;
				}
				case KATARI:
				{
					if (memoState == 1)
					{
						qs.setMemoState(5);
						qs.setCond(6, true);
						htmltext = "30668-01.html";
					}
					else if (memoState == 5)
					{
						if (getQuestItemsCount(player, RATMAN_SCAVENGERS_SKULL) < 10)
						{
							htmltext = "30668-02.html";
						}
						else
						{
							takeItems(player, RATMAN_SCAVENGERS_SKULL, -1);
							qs.setMemoState(9);
							qs.setCond(10, true);
							htmltext = "30668-03.html";
						}
					}
					else if (memoState == 9)
					{
						htmltext = "30668-04.html";
					}
					break;
				}
				case MAGISTER_JOAN:
				{
					if (memoState == 4)
					{
						qs.setMemoState(8);
						qs.setCond(9, true);
						htmltext = "30718-01.html";
					}
					else if (memoState == 8)
					{
						if (getQuestItemsCount(player, TRISALIM_TARANTULAS_VENOM_SAC) < 3)
						{
							htmltext = "30718-02.html";
						}
						else
						{
							takeItems(player, TRISALIM_TARANTULAS_VENOM_SAC, -1);
							qs.setMemoState(12);
							qs.setCond(13, true);
							htmltext = "30718-03.html";
						}
					}
					else if (memoState == 12)
					{
						htmltext = "30718-04.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

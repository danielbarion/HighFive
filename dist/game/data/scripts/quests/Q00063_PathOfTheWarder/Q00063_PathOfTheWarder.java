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
package quests.Q00063_PathOfTheWarder;

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
 * Path Of The Warder (63)
 * @author ivantotov
 */
public final class Q00063_PathOfTheWarder extends Quest
{
	// NPCs
	private static final int MASTER_SIONE = 32195;
	private static final int MASTER_GOBIE = 32198;
	private static final int MASTER_TOBIAS = 30297;
	private static final int CAPTAIN_BATHIS = 30332;
	// Items
	private static final int ORDERS = 9762;
	private static final int ORGANIZATION_CHART = 9763;
	private static final int GOBIES_ORDERS = 9764;
	private static final int LETTER_TO_HUMANS = 9765;
	private static final int HUMANS_REOLY = 9766;
	private static final int LETTER_TO_THE_DARKELVES = 9767;
	private static final int DARK_ELVES_REPLY = 9768;
	private static final int REPORT_TO_SIONE = 9769;
	private static final int EMPTY_SOUL_CRYSTAL = 9770;
	private static final int TAKS_CAPTURED_SOUL = 9771;
	// Reward
	private static final int STEELRAZOR_EVALUTION = 9772;
	// Monster
	private static final int OL_MAHUM_PATROL = 20053;
	private static final int OL_MAHUM_NOVICE = 20782;
	private static final int MAILLE_LIZARDMAN = 20919;
	private static final int MAILLE_LIZARDMAN_SCOUT = 20920;
	private static final int MAILLE_LIZARDMAN_GUARD = 20921;
	// Quest Monster
	private static final int OL_MAHUM_OFFICER_TAK = 27337;
	// Misc
	private static final int MIN_LEVEL = 18;
	
	public Q00063_PathOfTheWarder()
	{
		super(63);
		addStartNpc(MASTER_SIONE);
		addTalkId(MASTER_SIONE, MASTER_GOBIE, MASTER_TOBIAS, CAPTAIN_BATHIS);
		addKillId(OL_MAHUM_PATROL, OL_MAHUM_NOVICE, MAILLE_LIZARDMAN, MAILLE_LIZARDMAN_SCOUT, MAILLE_LIZARDMAN_GUARD, OL_MAHUM_OFFICER_TAK);
		registerQuestItems(ORDERS, ORGANIZATION_CHART, GOBIES_ORDERS, LETTER_TO_HUMANS, HUMANS_REOLY, LETTER_TO_THE_DARKELVES, DARK_ELVES_REPLY, REPORT_TO_SIONE, EMPTY_SOUL_CRYSTAL, TAKS_CAPTURED_SOUL);
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
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = "32195-05.htm";
				}
				break;
			}
			case "32195-06.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32195-08.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "32198-03.html":
			{
				if (qs.isMemoState(3))
				{
					takeItems(player, GOBIES_ORDERS, 1);
					giveItems(player, LETTER_TO_HUMANS, 1);
					qs.setMemoState(4);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "32198-07.html":
			{
				if (qs.isMemoState(7))
				{
					htmltext = event;
				}
				break;
			}
			case "32198-08.html":
			{
				if (qs.isMemoState(7))
				{
					giveItems(player, LETTER_TO_THE_DARKELVES, 1);
					qs.setMemoState(8);
					qs.setCond(7, true);
					htmltext = event;
				}
				break;
			}
			case "32198-12.html":
			{
				if (qs.isMemoState(12))
				{
					giveItems(player, REPORT_TO_SIONE, 1);
					qs.setMemoState(13);
					qs.setCond(9, true);
					htmltext = event;
				}
				break;
			}
			case "32198-15.html":
			{
				if (qs.isMemoState(14))
				{
					qs.setMemoState(15);
					htmltext = event;
				}
				break;
			}
			case "32198-16.html":
			{
				if (qs.isMemoState(15))
				{
					giveItems(player, EMPTY_SOUL_CRYSTAL, 1);
					qs.setMemoState(16);
					qs.set("ex", 0);
					qs.setCond(11, true);
					htmltext = event;
				}
				break;
			}
			case "30332-03.html":
			{
				if (qs.isMemoState(4))
				{
					takeItems(player, LETTER_TO_HUMANS, 1);
					giveItems(player, HUMANS_REOLY, 1);
					qs.setMemoState(5);
					htmltext = event;
				}
				break;
			}
			case "30332-05.html":
			{
				if (qs.isMemoState(5))
				{
					htmltext = event;
				}
				break;
			}
			case "30332-06.html":
			{
				if (qs.isMemoState(5))
				{
					qs.setMemoState(6);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "30297-03.html":
			{
				if (qs.isMemoState(9))
				{
					htmltext = event;
				}
				break;
			}
			case "30297-04.html":
			{
				if (qs.isMemoState(9))
				{
					qs.setMemoState(10);
					htmltext = event;
				}
				break;
			}
			case "30297-06.html":
			{
				if (qs.isMemoState(10))
				{
					giveItems(player, DARK_ELVES_REPLY, 1);
					qs.setMemoState(11);
					qs.setCond(8, true);
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
				case OL_MAHUM_PATROL:
				{
					if (qs.isMemoState(2) && (getQuestItemsCount(killer, ORGANIZATION_CHART) < 5))
					{
						if ((getQuestItemsCount(killer, ORDERS) >= 10) && (getQuestItemsCount(killer, ORGANIZATION_CHART) >= 4))
						{
							qs.setCond(3, true);
						}
						else
						{
							playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						giveItems(killer, ORGANIZATION_CHART, 1);
					}
					break;
				}
				case OL_MAHUM_NOVICE:
				{
					if (qs.isMemoState(2) && (getQuestItemsCount(killer, ORDERS) < 10))
					{
						if ((getQuestItemsCount(killer, ORDERS) >= 9) && (getQuestItemsCount(killer, ORGANIZATION_CHART) >= 5))
						{
							qs.setCond(3, true);
						}
						else
						{
							playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						giveItems(killer, ORDERS, 1);
					}
					break;
				}
				case MAILLE_LIZARDMAN:
				case MAILLE_LIZARDMAN_SCOUT:
				case MAILLE_LIZARDMAN_GUARD:
				{
					if (qs.isMemoState(16) && !hasQuestItems(killer, TAKS_CAPTURED_SOUL))
					{
						final int i4 = qs.getInt("ex");
						if (i4 < 4)
						{
							qs.set("ex", i4 + 1);
						}
						else
						{
							qs.set("ex", 0);
							final L2Npc monster = addSpawn(OL_MAHUM_OFFICER_TAK, npc, true, 0, false);
							addAttackDesire(monster, killer);
						}
					}
					break;
				}
				case OL_MAHUM_OFFICER_TAK:
				{
					if (qs.isMemoState(16) && !hasQuestItems(killer, TAKS_CAPTURED_SOUL))
					{
						takeItems(killer, EMPTY_SOUL_CRYSTAL, 1);
						giveItems(killer, TAKS_CAPTURED_SOUL, 1);
						qs.setCond(12, true);
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
			if (npc.getId() == MASTER_SIONE)
			{
				if ((player.getClassId() == ClassId.FEMALE_SOLDIER) && !hasQuestItems(player, STEELRAZOR_EVALUTION))
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						htmltext = "32195-01.htm";
					}
					else
					{
						htmltext = "32195-02.html";
					}
				}
				else if (hasQuestItems(player, STEELRAZOR_EVALUTION))
				{
					htmltext = "32195-03.html";
				}
				else
				{
					htmltext = "32195-04.html";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case MASTER_SIONE:
				{
					if (memoState == 1)
					{
						htmltext = "32195-07.html";
					}
					else if (memoState == 2)
					{
						if ((getQuestItemsCount(player, ORDERS) < 10) || (getQuestItemsCount(player, ORGANIZATION_CHART) < 5))
						{
							htmltext = "32195-09.html";
						}
						else
						{
							takeItems(player, ORDERS, -1);
							takeItems(player, ORGANIZATION_CHART, -1);
							giveItems(player, GOBIES_ORDERS, 1);
							qs.setMemoState(3);
							qs.setCond(4, true);
							htmltext = "32195-10.html";
						}
					}
					else if (memoState == 3)
					{
						htmltext = "32195-11.html";
					}
					else if ((memoState > 3) && (memoState != 13))
					{
						htmltext = "32195-12.html";
					}
					else if (memoState == 13)
					{
						takeItems(player, REPORT_TO_SIONE, 1);
						qs.setMemoState(14);
						qs.setCond(10, true);
						htmltext = "32195-13.html";
					}
					break;
				}
				case MASTER_GOBIE:
				{
					if (memoState < 3)
					{
						htmltext = "32198-01.html";
					}
					else if (memoState == 3)
					{
						htmltext = "32198-02.html";
					}
					else if ((memoState == 4) || (memoState == 5))
					{
						htmltext = "32198-04.html";
					}
					else if (memoState == 6)
					{
						takeItems(player, HUMANS_REOLY, 1);
						qs.setMemoState(7);
						htmltext = "32198-05.html";
					}
					else if (memoState == 7)
					{
						htmltext = "32198-06.html";
					}
					else if (memoState == 8)
					{
						htmltext = "32198-09.html";
					}
					else if (memoState == 11)
					{
						takeItems(player, DARK_ELVES_REPLY, 1);
						qs.setMemoState(12);
						htmltext = "32198-10.html";
					}
					else if (memoState == 12)
					{
						giveItems(player, REPORT_TO_SIONE, 1);
						qs.setMemoState(13);
						htmltext = "32198-11.html";
					}
					else if (memoState == 13)
					{
						htmltext = "32198-13.html";
					}
					else if (memoState == 14)
					{
						htmltext = "32198-14.html";
					}
					else if (memoState == 15)
					{
						giveItems(player, EMPTY_SOUL_CRYSTAL, 1);
						qs.setMemoState(16);
						qs.set("ex", 0);
						qs.setCond(11, true);
						htmltext = "32198-17.html";
					}
					else if (memoState == 16)
					{
						if (!hasQuestItems(player, TAKS_CAPTURED_SOUL))
						{
							qs.set("ex", 0);
							htmltext = "32198-18.html";
						}
						else
						{
							giveAdena(player, 163800, true);
							takeItems(player, TAKS_CAPTURED_SOUL, 1);
							giveItems(player, STEELRAZOR_EVALUTION, 1);
							final int level = player.getLevel();
							if (level >= 20)
							{
								addExpAndSp(player, 320534, 22046);
							}
							else if (level == 19)
							{
								addExpAndSp(player, 456128, 28744);
							}
							else
							{
								addExpAndSp(player, 591724, 35442);
							}
							qs.exitQuest(false, true);
							player.sendPacket(new SocialAction(player.getObjectId(), 3));
							htmltext = "32198-19.html";
						}
					}
					break;
				}
				case CAPTAIN_BATHIS:
				{
					if (memoState == 4)
					{
						htmltext = "30332-01.html";
					}
					else if (memoState < 4)
					{
						htmltext = "30332-02.html";
					}
					else if (memoState == 5)
					{
						htmltext = "30332-04.html";
					}
					else if (memoState == 6)
					{
						htmltext = "30332-07.html";
					}
					else if (memoState > 6)
					{
						htmltext = "30332-08.html";
					}
					break;
				}
				case MASTER_TOBIAS:
				{
					if (memoState == 8)
					{
						takeItems(player, LETTER_TO_THE_DARKELVES, 1);
						qs.setMemoState(9);
						htmltext = "30297-01.html";
					}
					else if (memoState == 9)
					{
						htmltext = "30297-02.html";
					}
					else if (memoState == 10)
					{
						giveItems(player, DARK_ELVES_REPLY, 1);
						qs.setMemoState(11);
						qs.setCond(8, true);
						htmltext = "30297-05.html";
					}
					else if (memoState >= 11)
					{
						htmltext = "30297-07.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == MASTER_GOBIE)
			{
				if (hasQuestItems(player, STEELRAZOR_EVALUTION))
				{
					htmltext = "32198-20.html";
				}
			}
		}
		return htmltext;
	}
}
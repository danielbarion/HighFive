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
package quests.Q00414_PathOfTheOrcRaider;

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
 * Path Of The Orc Raider (414)
 * @author ivantotov
 */
public final class Q00414_PathOfTheOrcRaider extends Quest
{
	// NPCs
	private static final int PREFECT_KARUKIA = 30570;
	private static final int PREFRCT_KASMAN = 30501;
	private static final int PREFRCT_TAZEER = 31978;
	// Items
	private static final int GREEN_BLOOD = 1578;
	private static final int GOBLIN_DWELLING_MAP = 1579;
	private static final int KURUKA_RATMAN_TOOTH = 1580;
	private static final int BETRAYER_UMBAR_REPORT = 1589;
	private static final int BETRAYER_ZAKAN_REPORT = 1590;
	private static final int HEAD_OF_BETRAYER = 1591;
	private static final int TIMORA_ORC_HEAD = 8544;
	// Reward
	private static final int MARK_OF_RAIDER = 1592;
	// Quest Monster
	private static final int KURUKA_RATMAN_LEADER = 27045;
	private static final int UMBAR_ORC = 27054;
	private static final int TIMORA_ORC = 27320;
	// Monster
	private static final int GOBLIN_TOMB_RAIDER_LEADER = 20320;
	// Misc
	private static final int MIN_LEVEL = 18;
	
	public Q00414_PathOfTheOrcRaider()
	{
		super(414);
		addStartNpc(PREFECT_KARUKIA);
		addTalkId(PREFECT_KARUKIA, PREFRCT_KASMAN, PREFRCT_TAZEER);
		addKillId(KURUKA_RATMAN_LEADER, UMBAR_ORC, TIMORA_ORC, GOBLIN_TOMB_RAIDER_LEADER);
		registerQuestItems(GREEN_BLOOD, GOBLIN_DWELLING_MAP, KURUKA_RATMAN_TOOTH, BETRAYER_UMBAR_REPORT, BETRAYER_ZAKAN_REPORT, HEAD_OF_BETRAYER, TIMORA_ORC_HEAD);
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
				if (player.getClassId() == ClassId.ORC_FIGHTER)
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						if (hasQuestItems(player, MARK_OF_RAIDER))
						{
							htmltext = "30570-04.htm";
						}
						else
						{
							if (!hasQuestItems(player, GOBLIN_DWELLING_MAP))
							{
								giveItems(player, GOBLIN_DWELLING_MAP, 1);
							}
							qs.startQuest();
							htmltext = "30570-05.htm";
						}
					}
					else
					{
						htmltext = "30570-02.htm";
					}
				}
				else if (player.getClassId() == ClassId.ORC_RAIDER)
				{
					htmltext = "30570-02a.htm";
				}
				else
				{
					htmltext = "30570-03.htm";
				}
				break;
			}
			case "30570-07a.html":
			{
				if (hasQuestItems(player, GOBLIN_DWELLING_MAP) && (getQuestItemsCount(player, KURUKA_RATMAN_TOOTH) >= 10))
				{
					takeItems(player, GOBLIN_DWELLING_MAP, 1);
					takeItems(player, KURUKA_RATMAN_TOOTH, -1);
					giveItems(player, BETRAYER_UMBAR_REPORT, 1);
					giveItems(player, BETRAYER_ZAKAN_REPORT, 1);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "30570-07b.html":
			{
				if (hasQuestItems(player, GOBLIN_DWELLING_MAP) && (getQuestItemsCount(player, KURUKA_RATMAN_TOOTH) >= 10))
				{
					takeItems(player, GOBLIN_DWELLING_MAP, 1);
					takeItems(player, KURUKA_RATMAN_TOOTH, -1);
					qs.setCond(5, true);
					qs.setMemoState(2);
					htmltext = event;
				}
				break;
			}
			case "31978-04.html":
			{
				if (qs.isMemoState(2))
				{
					htmltext = event;
				}
				break;
			}
			case "31978-02.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setMemoState(3);
					qs.setCond(6, true);
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
				case GOBLIN_TOMB_RAIDER_LEADER:
				{
					if (hasQuestItems(killer, GOBLIN_DWELLING_MAP) && (getQuestItemsCount(killer, KURUKA_RATMAN_TOOTH) < 10) && (getQuestItemsCount(killer, GREEN_BLOOD) <= 20))
					{
						if (getRandom(100) < (getQuestItemsCount(killer, GREEN_BLOOD) * 5))
						{
							takeItems(killer, GREEN_BLOOD, -1);
							addAttackDesire(addSpawn(KURUKA_RATMAN_LEADER, npc, true, 0, true), killer);
						}
						else
						{
							giveItems(killer, GREEN_BLOOD, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case KURUKA_RATMAN_LEADER:
				{
					if (hasQuestItems(killer, GOBLIN_DWELLING_MAP) && (getQuestItemsCount(killer, KURUKA_RATMAN_TOOTH) < 10))
					{
						takeItems(killer, GREEN_BLOOD, -1);
						if (getQuestItemsCount(killer, KURUKA_RATMAN_TOOTH) >= 9)
						{
							giveItems(killer, KURUKA_RATMAN_TOOTH, 1);
							qs.setCond(2, true);
						}
						else
						{
							giveItems(killer, KURUKA_RATMAN_TOOTH, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case UMBAR_ORC:
				{
					if (hasAtLeastOneQuestItem(killer, BETRAYER_UMBAR_REPORT, BETRAYER_ZAKAN_REPORT) && (getQuestItemsCount(killer, HEAD_OF_BETRAYER) < 2) && (getRandom(10) < 2))
					{
						giveItems(killer, HEAD_OF_BETRAYER, 1);
						if (hasQuestItems(killer, BETRAYER_ZAKAN_REPORT))
						{
							takeItems(killer, BETRAYER_ZAKAN_REPORT, 1);
						}
						else if (hasQuestItems(killer, BETRAYER_UMBAR_REPORT))
						{
							takeItems(killer, BETRAYER_UMBAR_REPORT, 1);
						}
						if (getQuestItemsCount(killer, HEAD_OF_BETRAYER) == 2)
						{
							qs.setCond(4, true);
						}
						else
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case TIMORA_ORC:
				{
					if (qs.isMemoState(3) && !hasQuestItems(killer, TIMORA_ORC_HEAD))
					{
						if (getRandom(100) < 60)
						{
							giveItems(killer, TIMORA_ORC_HEAD, 1);
							qs.setCond(7, true);
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
		if (qs.isCreated() || qs.isCompleted())
		{
			if (npc.getId() == PREFECT_KARUKIA)
			{
				htmltext = "30570-01.htm";
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case PREFECT_KARUKIA:
				{
					if (hasQuestItems(player, GOBLIN_DWELLING_MAP) && (getQuestItemsCount(player, KURUKA_RATMAN_TOOTH) < 10))
					{
						htmltext = "30570-06.html";
					}
					else if (hasQuestItems(player, GOBLIN_DWELLING_MAP) && (getQuestItemsCount(player, KURUKA_RATMAN_TOOTH) >= 10))
					{
						if (!hasAtLeastOneQuestItem(player, BETRAYER_UMBAR_REPORT, BETRAYER_ZAKAN_REPORT))
						{
							htmltext = "30570-07.html";
						}
					}
					else if (hasQuestItems(player, HEAD_OF_BETRAYER) || hasAtLeastOneQuestItem(player, BETRAYER_UMBAR_REPORT, BETRAYER_ZAKAN_REPORT))
					{
						htmltext = "30570-08.html";
					}
					else if (qs.isMemoState(2))
					{
						htmltext = "30570-07b.html";
					}
					break;
				}
				case PREFRCT_KASMAN:
				{
					if (!hasQuestItems(player, HEAD_OF_BETRAYER) && (getQuestItemsCount(player, BETRAYER_UMBAR_REPORT, BETRAYER_ZAKAN_REPORT) >= 2))
					{
						htmltext = "30501-01.html";
					}
					else if (getQuestItemsCount(player, HEAD_OF_BETRAYER) == 1)
					{
						htmltext = "30501-02.html";
					}
					else if (getQuestItemsCount(player, HEAD_OF_BETRAYER) == 2)
					{
						giveAdena(player, 163800, true);
						giveItems(player, MARK_OF_RAIDER, 1);
						final int level = player.getLevel();
						if (level >= 20)
						{
							addExpAndSp(player, 320534, 21312);
						}
						else if (level == 19)
						{
							addExpAndSp(player, 456128, 28010);
						}
						else
						{
							addExpAndSp(player, 591724, 34708);
						}
						qs.exitQuest(false, true);
						player.sendPacket(new SocialAction(player.getObjectId(), 3));
						htmltext = "30501-03.html";
					}
					break;
				}
				case PREFRCT_TAZEER:
				{
					if (qs.isMemoState(2))
					{
						htmltext = "31978-01.html";
					}
					else if (qs.isMemoState(3))
					{
						if (!hasQuestItems(player, TIMORA_ORC_HEAD))
						{
							htmltext = "31978-03.html";
						}
						else
						{
							giveAdena(player, 81900, true);
							giveItems(player, MARK_OF_RAIDER, 1);
							final int level = player.getLevel();
							if (level >= 20)
							{
								addExpAndSp(player, 160267, 10656);
							}
							else if (level == 19)
							{
								addExpAndSp(player, 228064, 14005);
							}
							else
							{
								addExpAndSp(player, 295862, 17354);
							}
							qs.exitQuest(false, true);
							player.sendPacket(new SocialAction(player.getObjectId(), 3));
							htmltext = "31978-05.html";
						}
					}
					break;
				}
			}
		}
		return htmltext;
	}
}
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
package quests.Q00064_CertifiedBerserker;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;
import com.l2jmobius.gameserver.util.Util;

/**
 * Certified Berserker (64)
 * @author ivantotov
 */
public final class Q00064_CertifiedBerserker extends Quest
{
	// NPCs
	private static final int MASTER_ENTIENS = 32200;
	private static final int MASTER_ORKURUS = 32207;
	private static final int MASTER_TENAIN = 32215;
	private static final int CARAVANER_GORT = 32252;
	private static final int HARKILGAMED = 32253;
	// Items
	private static final int BREKA_ORC_HEAD = 9754;
	private static final int MESSAGE_PLATE = 9755;
	private static final int REPORT_EAST = 9756;
	private static final int REPORT_NORTH = 9757;
	private static final int HARKILGAMEDS_LETTER = 9758;
	private static final int TENAINS_RECOMMENDATION = 9759;
	// Reward
	private static final int DIMENSIONAL_DIAMOND = 7562;
	private static final int ORKURUS_RECOMMENDATION = 9760;
	// Monster
	private static final int DEAD_SEEKER = 20202;
	private static final int MARSH_STAKATO_DRONE = 20234;
	private static final int BREKA_ORC = 20267;
	private static final int BREKA_ORC_ARCHER = 20268;
	private static final int BREKA_ORC_SHAMAN = 20269;
	private static final int BREKA_ORC_OVERLORD = 20270;
	private static final int BREKA_ORC_WARRIOR = 20271;
	private static final int ROAD_SCAVENGER = 20551;
	// Quest Monster
	private static final int DIVINE_EMISSARY = 27323;
	// Misc
	private static final int MIN_LEVEL = 39;
	
	public Q00064_CertifiedBerserker()
	{
		super(64);
		addStartNpc(MASTER_ORKURUS);
		addTalkId(MASTER_ORKURUS, MASTER_ENTIENS, MASTER_TENAIN, CARAVANER_GORT, HARKILGAMED);
		addKillId(DEAD_SEEKER, MARSH_STAKATO_DRONE, BREKA_ORC, BREKA_ORC_ARCHER, BREKA_ORC_SHAMAN, BREKA_ORC_OVERLORD, BREKA_ORC_WARRIOR, ROAD_SCAVENGER, DIVINE_EMISSARY);
		registerQuestItems(BREKA_ORC_HEAD, MESSAGE_PLATE, REPORT_EAST, REPORT_NORTH, HARKILGAMEDS_LETTER, TENAINS_RECOMMENDATION);
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
					if (player.getVariables().getInt("2ND_CLASS_DIAMOND_REWARD", 0) == 0)
					{
						giveItems(player, DIMENSIONAL_DIAMOND, 48);
						player.getVariables().set("2ND_CLASS_DIAMOND_REWARD", 1);
						htmltext = "32207-06.htm";
					}
					else
					{
						htmltext = "32207-06a.htm";
					}
				}
				break;
			}
			case "32207-10.html":
			{
				if (qs.isMemoState(11))
				{
					htmltext = event;
				}
				break;
			}
			case "32207-11.html":
			{
				if (qs.isMemoState(11))
				{
					giveAdena(player, 63104, true);
					giveItems(player, ORKURUS_RECOMMENDATION, 1);
					addExpAndSp(player, 349006, 23948);
					qs.exitQuest(false, true);
					player.sendPacket(new SocialAction(player.getObjectId(), 3));
					htmltext = event;
				}
				break;
			}
			case "32215-02.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "32215-07.html":
			case "32215-08.html":
			case "32215-09.html":
			{
				if (qs.isMemoState(5))
				{
					htmltext = event;
				}
				break;
			}
			case "32215-10.html":
			{
				if (qs.isMemoState(5))
				{
					takeItems(player, MESSAGE_PLATE, 1);
					qs.setMemoState(6);
					qs.setCond(8, true);
					htmltext = event;
				}
				break;
			}
			case "32215-15.html":
			{
				if (qs.isMemoState(10))
				{
					takeItems(player, HARKILGAMEDS_LETTER, 1);
					giveItems(player, TENAINS_RECOMMENDATION, 1);
					qs.setMemoState(11);
					qs.setCond(14, true);
					htmltext = event;
				}
				break;
			}
			case "32252-02.html":
			{
				if (qs.isMemoState(3))
				{
					qs.setMemoState(4);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "32253-02.html":
			{
				if (qs.isMemoState(9))
				{
					giveItems(player, HARKILGAMEDS_LETTER, 1);
					qs.setMemoState(10);
					qs.setCond(13, true);
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
				case DEAD_SEEKER:
				{
					if (qs.isMemoState(7) && !hasQuestItems(killer, REPORT_EAST))
					{
						if (getRandom(100) < 20)
						{
							giveItems(killer, REPORT_EAST, 1);
							if (hasQuestItems(killer, REPORT_NORTH))
							{
								qs.setCond(10, true);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case MARSH_STAKATO_DRONE:
				{
					if (qs.isMemoState(7) && !hasQuestItems(killer, REPORT_NORTH))
					{
						if (getRandom(100) < 20)
						{
							giveItems(killer, REPORT_NORTH, 1);
							if (hasQuestItems(killer, REPORT_EAST))
							{
								qs.setCond(10, true);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case BREKA_ORC:
				case BREKA_ORC_ARCHER:
				case BREKA_ORC_SHAMAN:
				case BREKA_ORC_OVERLORD:
				case BREKA_ORC_WARRIOR:
				{
					if (qs.isMemoState(2) && (getQuestItemsCount(killer, BREKA_ORC_HEAD) < 20))
					{
						if (getQuestItemsCount(killer, BREKA_ORC_HEAD) >= 19)
						{
							giveItems(killer, BREKA_ORC_HEAD, 1);
							qs.setCond(3, true);
						}
						else
						{
							giveItems(killer, BREKA_ORC_HEAD, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case ROAD_SCAVENGER:
				{
					if (qs.isMemoState(4) && !hasQuestItems(killer, MESSAGE_PLATE))
					{
						if (getRandom(100) < 20)
						{
							giveItems(killer, MESSAGE_PLATE, 1);
							qs.setCond(6, true);
						}
					}
					break;
				}
				case DIVINE_EMISSARY:
				{
					if (qs.isMemoState(9))
					{
						if (getRandom(100) < 20)
						{
							final L2Npc kamael = addSpawn(HARKILGAMED, npc, true, 60000);
							kamael.broadcastPacket(new NpcSay(kamael, ChatType.NPC_GENERAL, NpcStringId.S1_DID_YOU_COME_TO_HELP_ME).addStringParameter(killer.getAppearance().getVisibleName()));
							playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
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
			if (npc.getId() == MASTER_ORKURUS)
			{
				if (player.getRace() == Race.KAMAEL)
				{
					if (player.getClassId() == ClassId.TROOPER)
					{
						if (player.getLevel() >= MIN_LEVEL)
						{
							htmltext = "32207-01.htm";
						}
						else
						{
							htmltext = "32207-02.html";
						}
					}
					else
					{
						htmltext = "32207-03.html";
					}
				}
				else
				{
					htmltext = "32207-04.html";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case MASTER_ORKURUS:
				{
					if (memoState == 1)
					{
						htmltext = "32207-07.html";
					}
					else if ((memoState >= 2) && (memoState < 11))
					{
						htmltext = "32207-08.html";
					}
					else if (memoState == 11)
					{
						htmltext = "32207-09.html";
					}
					break;
				}
				case MASTER_ENTIENS:
				{
					if (memoState == 6)
					{
						qs.setMemoState(7);
						qs.setCond(9, true);
						player.getRadar().addMarker(27956, 106003, -3831);
						player.getRadar().addMarker(50568, 152408, -2656);
						htmltext = "32200-01.html";
					}
					else if (memoState == 7)
					{
						if (!hasQuestItems(player, REPORT_EAST, REPORT_NORTH))
						{
							htmltext = "32200-02.html";
						}
						else
						{
							takeItems(player, REPORT_EAST, 1);
							takeItems(player, REPORT_NORTH, 1);
							qs.setMemoState(8);
							qs.setCond(11, true);
							htmltext = "32200-03.html";
						}
					}
					else if (memoState == 8)
					{
						htmltext = "32200-04.html";
					}
					break;
				}
				case MASTER_TENAIN:
				{
					if (memoState == 1)
					{
						htmltext = "32215-01.html";
					}
					else if (memoState == 2)
					{
						if (getQuestItemsCount(player, BREKA_ORC_HEAD) < 20)
						{
							htmltext = "32215-03.html";
						}
						else
						{
							takeItems(player, BREKA_ORC_HEAD, -1);
							qs.setMemoState(3);
							qs.setCond(4, true);
							htmltext = "32215-04.html";
						}
					}
					else if (memoState == 3)
					{
						htmltext = "32215-05.html";
					}
					else if (memoState == 5)
					{
						htmltext = "32215-06.html";
					}
					else if (memoState == 6)
					{
						htmltext = "32215-11.html";
					}
					else if (memoState == 8)
					{
						qs.setMemoState(9);
						qs.setCond(12, true);
						htmltext = "32215-12.html";
					}
					else if (memoState == 9)
					{
						htmltext = "32215-13.html";
					}
					else if (memoState == 10)
					{
						htmltext = "32215-14.html";
					}
					else if (memoState == 11)
					{
						htmltext = "32215-16.html";
					}
					break;
				}
				case CARAVANER_GORT:
				{
					if (memoState == 3)
					{
						htmltext = "32252-01.html";
					}
					else if (memoState == 4)
					{
						if (!hasQuestItems(player, MESSAGE_PLATE))
						{
							htmltext = "32252-03.html";
						}
						else
						{
							qs.setMemoState(5);
							qs.setCond(7, true);
							htmltext = "32252-04.html";
						}
					}
					else if (memoState == 5)
					{
						htmltext = "32252-05.html";
					}
					break;
				}
				case HARKILGAMED:
				{
					if (memoState == 9)
					{
						htmltext = "32253-01.html";
					}
					else if (memoState == 10)
					{
						htmltext = "32253-03.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == MASTER_ORKURUS)
			{
				htmltext = "32207-05.html";
			}
		}
		return htmltext;
	}
}
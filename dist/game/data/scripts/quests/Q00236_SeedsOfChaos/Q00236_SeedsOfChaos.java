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
package quests.Q00236_SeedsOfChaos;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Util;

import quests.Q00025_HidingBehindTheTruth.Q00025_HidingBehindTheTruth;

/**
 * Seeds Of Chaos (236)
 * @author ivantotov
 */
public final class Q00236_SeedsOfChaos extends Quest
{
	// NPCs
	private static final int KURSTIN = 31387;
	private static final int MYSTERIOU_WIZARD = 31522;
	private static final int HIERARCH_KEKROPUS = 32138;
	private static final int VICE_HIERARCH_MAO = 32190;
	private static final int KATENAR = 32235;
	private static final int HARKILGAMED = 32236;
	private static final int RODENPICULA = 32237;
	private static final int ROCK = 32238;
	private static final int MOTHER_NORNIL = 32239;
	private static final int KATENAR_A = 32332;
	private static final int KATENAR_B = 32333;
	private static final int HARKILGAMED_A = 32334;
	// Items
	private static final int STAR_OF_DESTINY = 5011;
	private static final int SHINING_MEDALLION = 9743;
	private static final int BLOOD_JEWEL = 9744;
	private static final int BLACK_ECHO_CRYSTAL = 9745;
	// Reward
	private static final int SCROLL_ENCHANT_WEAPON_A_GRADE = 729;
	// Monster
	private static final int NEEDLE_STAKATO_DRONE = 21516;
	private static final int SHOUT_OF_SPLENDOR = 21532;
	private static final int ALLIANCE_OF_SPLENDOR = 21533;
	private static final int ALLIANCE_OF_SPLENDOR_1 = 21534;
	private static final int SIGNET_OF_SPLENDOR = 21535;
	private static final int CROWN_OF_SPLENDOR = 21536;
	private static final int FANG_OF_SPLENDOR = 21537;
	private static final int FANG_OF_SPLENDOR_1 = 21538;
	private static final int WAILINGOF_SPLENDOR = 21539;
	private static final int WAILINGOF_SPLENDOR_1 = 21540;
	private static final int VAMPIRE_WIZARD = 21588;
	private static final int VAMPIRE_WIZARD_A = 21589;
	// Misc
	private static final int MIN_LEVEL = 75;
	
	public Q00236_SeedsOfChaos()
	{
		super(236);
		addStartNpc(HIERARCH_KEKROPUS);
		addTalkId(HIERARCH_KEKROPUS, KURSTIN, MYSTERIOU_WIZARD, VICE_HIERARCH_MAO, KATENAR, HARKILGAMED, RODENPICULA, ROCK, MOTHER_NORNIL, KATENAR_A, KATENAR_B, HARKILGAMED_A);
		addKillId(NEEDLE_STAKATO_DRONE, SHOUT_OF_SPLENDOR, ALLIANCE_OF_SPLENDOR, ALLIANCE_OF_SPLENDOR_1, SIGNET_OF_SPLENDOR, CROWN_OF_SPLENDOR, FANG_OF_SPLENDOR, FANG_OF_SPLENDOR_1, WAILINGOF_SPLENDOR, WAILINGOF_SPLENDOR_1, VAMPIRE_WIZARD, VAMPIRE_WIZARD_A);
		addSpawnId(KATENAR, HARKILGAMED, KATENAR_A, KATENAR_B, HARKILGAMED_A);
		registerQuestItems(SHINING_MEDALLION, BLOOD_JEWEL, BLACK_ECHO_CRYSTAL);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ("KATENAR_120".equals(event))
		{
			final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			if (npc0 != null)
			{
				if (npc0.getVariables().getBoolean("SPAWNED"))
				{
					npc0.getVariables().set("SPAWNED", false);
					if (c0 != null)
					{
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.HMM_WHERE_DID_MY_FRIEND_GO));
					}
				}
			}
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		else if ("HARKILGAMED_120".equals(event))
		{
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			if (npc0 != null)
			{
				if (npc0.getVariables().getBoolean("SPAWNED"))
				{
					npc0.getVariables().set("SPAWNED", false);
					npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.GRAAAH_WE_RE_BEING_ATTACKED));
				}
			}
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		else if ("KATENAR_A_120".equals(event))
		{
			final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			if (npc0 != null)
			{
				if (npc0.getVariables().getBoolean("SPAWNED"))
				{
					npc0.getVariables().set("SPAWNED", false);
					if (c0 != null)
					{
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.HMM_WHERE_DID_MY_FRIEND_GO));
					}
				}
			}
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		else if ("KATENAR_B_120".equals(event))
		{
			final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			if (npc0 != null)
			{
				if (npc0.getVariables().getBoolean("SPAWNED"))
				{
					npc0.getVariables().set("SPAWNED", false);
					if (c0 != null)
					{
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.HMM_WHERE_DID_MY_FRIEND_GO));
					}
				}
			}
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		else if ("HARKILGAMED_A_120".equals(event))
		{
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			if (npc0 != null)
			{
				if (npc0.getVariables().getBoolean("SPAWNED"))
				{
					npc0.getVariables().set("SPAWNED", false);
					npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.GRAAAH_WE_RE_BEING_ATTACKED));
				}
			}
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "32138-04.htm":
			{
				if (qs.isCreated())
				{
					qs.setMemoState(1);
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "32138-03.htm":
			{
				if ((player.getLevel() >= MIN_LEVEL) && (player.getRace() == Race.KAMAEL) && hasQuestItems(player, STAR_OF_DESTINY))
				{
					htmltext = event;
				}
				break;
			}
			case "32138-12.html":
			{
				if (qs.isMemoState(30))
				{
					qs.setMemoState(40);
					qs.setCond(15, true);
					htmltext = event;
				}
				break;
			}
			case "31387-03.html":
			{
				if (qs.isMemoState(11))
				{
					takeItems(player, BLOOD_JEWEL, -1);
					qs.setMemoState(12);
					htmltext = event;
				}
				break;
			}
			case "31387-05a.html":
			{
				if (qs.isMemoState(12))
				{
					final QuestState q25 = player.getQuestState(Q00025_HidingBehindTheTruth.class.getSimpleName());
					if ((q25 != null) && q25.isCompleted())
					{
						htmltext = event;
					}
					else
					{
						htmltext = "31387-05b.html";
					}
				}
				break;
			}
			case "31387-10.html":
			{
				if (qs.isMemoState(12))
				{
					qs.setMemoState(20);
					qs.setMemoStateEx(1, 1);
					qs.setCond(11, true);
					htmltext = event;
				}
				else if (qs.isMemoState(20) && (qs.getMemoStateEx(1) == 1))
				{
					htmltext = event;
				}
				break;
			}
			case "31522-04a.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "31522-05a.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "31522-05b.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(3);
					qs.setMemoStateEx(1, 0);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "31522-09a.html":
			{
				if (qs.isMemoState(2) && hasQuestItems(player, BLACK_ECHO_CRYSTAL))
				{
					takeItems(player, BLACK_ECHO_CRYSTAL, -1);
					qs.setMemoState(6);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "31522-12a.html":
			{
				if (qs.isMemoState(6))
				{
					if (!npc.getVariables().getBoolean("SPAWNED", false))
					{
						npc.getVariables().set("SPAWNED", true);
						npc.getVariables().set("PLAYER_ID", player.getObjectId());
						final L2Npc katenar = addSpawn(KATENAR, player.getX() + 10, player.getY() + 10, player.getZ(), +10, false, 0);
						katenar.getVariables().set("npc0", npc);
						katenar.getVariables().set("player0", player);
						htmltext = event;
					}
					else if (npc.getVariables().getInt("PLAYER_ID") == player.getObjectId())
					{
						htmltext = "31522-13a.html";
					}
					else
					{
						htmltext = "31522-14a.html";
					}
				}
				break;
			}
			case "31522-09b.html":
			{
				if (qs.isMemoState(3) && (qs.getMemoStateEx(1) == 2))
				{
					if (!npc.getVariables().getBoolean("SPAWNED", false))
					{
						npc.getVariables().set("SPAWNED", true);
						npc.getVariables().set("PLAYER_ID", player.getObjectId());
						final L2Npc katenar = addSpawn(KATENAR_A, player.getX() + 10, player.getY() + 10, player.getZ(), +10, false, 0);
						katenar.getVariables().set("npc0", npc);
						katenar.getVariables().set("player0", player);
						htmltext = event;
					}
					else if (npc.getVariables().getInt("PLAYER_ID") == player.getObjectId())
					{
						htmltext = "31522-10b.html";
					}
					else
					{
						htmltext = "31522-11b.html";
					}
				}
				break;
			}
			case "31522-14b.html":
			{
				if (qs.isMemoState(7) && hasQuestItems(player, BLOOD_JEWEL))
				{
					if (!npc.getVariables().getBoolean("SPAWNED", false))
					{
						npc.getVariables().set("SPAWNED", true);
						npc.getVariables().set("PLAYER_ID", player.getObjectId());
						final L2Npc katenar = addSpawn(KATENAR_B, player.getX() + 10, player.getY() + 10, player.getZ(), +10, false, 0);
						katenar.getVariables().set("npc0", npc);
						katenar.getVariables().set("player0", player);
						htmltext = event;
					}
					else if (npc.getVariables().getInt("PLAYER_ID") == player.getObjectId())
					{
						htmltext = "31522-15b.html";
					}
					else
					{
						htmltext = "31522-15bz.html";
					}
				}
				break;
			}
			case "32235-09a.html":
			{
				if (qs.isMemoState(6))
				{
					qs.setMemoState(20);
					qs.setMemoStateEx(1, 0);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "32236-07.html":
			{
				if (qs.isMemoState(20))
				{
					final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
					if (c0 != null)
					{
						qs.setMemoState(21);
						qs.setMemoStateEx(1, 0);
						qs.setCond(12, true);
					}
					htmltext = event;
				}
				break;
			}
			case "32237-10.html":
			{
				if (qs.isMemoState(40))
				{
					htmltext = event;
				}
				break;
			}
			case "32237-11.html":
			{
				if (qs.isMemoState(40))
				{
					qs.setMemoState(42);
					qs.setCond(17, true);
					htmltext = event;
				}
				break;
			}
			case "32237-13.html":
			{
				if (qs.isMemoState(43))
				{
					qs.setMemoState(44);
					qs.setCond(19, true);
					htmltext = event;
				}
				break;
			}
			case "32237-17.html":
			{
				if (qs.isMemoState(45))
				{
					giveItems(player, SCROLL_ENCHANT_WEAPON_A_GRADE, 1);
					takeItems(player, STAR_OF_DESTINY, 1);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "32238-02.html":
			{
				if (qs.isMemoState(20))
				{
					if (!npc.getVariables().getBoolean("SPAWNED", false))
					{
						npc.getVariables().set("SPAWNED", true);
						npc.getVariables().set("PLAYER_ID", player.getObjectId());
						final L2Npc kamael = addSpawn(HARKILGAMED, 71722, -78853, -4464, 0, false, 0);
						kamael.getVariables().set("npc0", npc);
						kamael.getVariables().set("player0", player);
						htmltext = event;
					}
					else if (npc.getVariables().getInt("PLAYER_ID") == player.getObjectId())
					{
						htmltext = "32238-03.html";
					}
					else
					{
						htmltext = "32238-04z.html";
					}
				}
				break;
			}
			case "32238-06.html":
			{
				if (qs.isMemoState(22))
				{
					if (!npc.getVariables().getBoolean("SPAWNED", false))
					{
						npc.getVariables().set("SPAWNED", true);
						npc.getVariables().set("PLAYER_ID", player.getObjectId());
						final L2Npc kamael = addSpawn(HARKILGAMED_A, 71722, -78853, -4464, 0, false, 0);
						kamael.getVariables().set("npc0", npc);
						kamael.getVariables().set("player0", player);
						htmltext = event;
					}
					else if (npc.getVariables().getInt("PLAYER_ID") == player.getObjectId())
					{
						htmltext = "32238-07.html";
					}
					else
					{
						htmltext = "32238-08.html";
					}
				}
				break;
			}
			case "32239-04.html":
			{
				if (qs.isMemoState(42))
				{
					qs.setMemoState(43);
					qs.setCond(18, true);
					htmltext = event;
				}
				break;
			}
			case "32239-08.html":
			{
				if (qs.isMemoState(44))
				{
					qs.setMemoState(45);
					qs.setCond(20, true);
					htmltext = event;
				}
				break;
			}
			case "32332-05b.html":
			{
				if (qs.isMemoState(3) && (qs.getMemoStateEx(1) == 2))
				{
					final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
					if (c0 != null)
					{
						qs.setMemoState(7);
						qs.setMemoStateEx(1, 0);
						qs.setCond(8, true);
					}
					htmltext = event;
				}
				break;
			}
			case "32334-17.html":
			{
				if (qs.isMemoState(22))
				{
					final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
					if (c0 != null)
					{
						takeItems(player, SHINING_MEDALLION, -1);
						qs.setMemoState(30);
						qs.setCond(14, true);
					}
					htmltext = event;
				}
				break;
			}
			case "KEITNAR_DESPAWN":
			{
				if (qs.isMemoState(20) && (qs.getMemoStateEx(1) == 0))
				{
					final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
					final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
					if (player == c0)
					{
						if (npc0 != null)
						{
							npc0.getVariables().set("SPAWNED", false);
						}
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS));
						npc.deleteMe();
					}
				}
				break;
			}
			case "HARKILGAMED_DESPAWN":
			{
				if (qs.isMemoState(21))
				{
					final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
					final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
					if (player == c0)
					{
						if (npc0 != null)
						{
							npc0.getVariables().set("SPAWNED", false);
						}
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.IN_THAT_CASE_I_WISH_YOU_GOOD_LUCK));
						npc.deleteMe();
					}
				}
				break;
			}
			case "KEITNAR_A_DESPAWN":
			{
				final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
				final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
				if (player == c0)
				{
					if (npc0 != null)
					{
						npc0.getVariables().set("SPAWNED", false);
					}
					npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS));
					npc.deleteMe();
				}
				break;
			}
			case "KEITNAR_B_DESPAWN":
			{
				if (qs.isMemoState(11) && hasQuestItems(player, BLOOD_JEWEL))
				{
					final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
					final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
					if (player == c0)
					{
						if (npc0 != null)
						{
							npc0.getVariables().set("SPAWNED", false);
						}
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS));
						npc.deleteMe();
					}
				}
				break;
			}
			case "HARKILGAMED_A_DESPAWN":
			{
				if (qs.isMemoState(30))
				{
					final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
					final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
					if (player == c0)
					{
						if (npc0 != null)
						{
							npc0.getVariables().set("SPAWNED", false);
						}
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.SAFE_TRAVELS));
						npc.deleteMe();
					}
				}
				break;
			}
			case "31387-02.html":
			case "31387-04.html":
			case "31387-06.html":
			case "31387-07.html":
			case "31387-08.html":
			case "31522-02.html":
			case "31522-03.html":
			case "31522-04b.html":
			case "31522-08a.html":
			case "31522-11a.html":
			case "32138-02.html":
			case "32138-07.html":
			case "32138-08.html":
			case "32138-09.html":
			case "32138-10.html":
			case "32138-11.html":
			case "32235-02a.html":
			case "32235-03a.html":
			case "32235-04a.html":
			case "32235-05a.html":
			case "32235-06a.html":
			case "32235-08a.html":
			case "32236-03.html":
			case "32236-04.html":
			case "32236-05.html":
			case "32236-06.html":
			case "32237-02.html":
			case "32237-03.html":
			case "32237-04.html":
			case "32237-05.html":
			case "32237-06.html":
			case "32237-07.html":
			case "32237-08.html":
			case "32237-16.html":
			case "32237-18.html":
			case "32239-03.html":
			case "32239-07.html":
			case "32332-02b.html":
			case "32332-03b.html":
			case "32332-04b.html":
			case "32334-10.html":
			case "32334-11.html":
			case "32334-12.html":
			case "32334-13.html":
			case "32334-14.html":
			case "32334-15.html":
			case "32334-16.html":
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
				case NEEDLE_STAKATO_DRONE:
				{
					if (qs.isMemoState(2) && !hasQuestItems(killer, BLACK_ECHO_CRYSTAL))
					{
						if (getRandom(100) < 20)
						{
							giveItems(killer, BLACK_ECHO_CRYSTAL, 1);
							qs.setCond(3, true);
						}
					}
					break;
				}
				case SHOUT_OF_SPLENDOR:
				case ALLIANCE_OF_SPLENDOR:
				case ALLIANCE_OF_SPLENDOR_1:
				case SIGNET_OF_SPLENDOR:
				case CROWN_OF_SPLENDOR:
				case FANG_OF_SPLENDOR:
				case FANG_OF_SPLENDOR_1:
				case WAILINGOF_SPLENDOR:
				case WAILINGOF_SPLENDOR_1:
				{
					if (qs.isMemoState(21) && (getQuestItemsCount(killer, SHINING_MEDALLION) < 62))
					{
						if (getRandom(100) < 70)
						{
							giveItems(killer, SHINING_MEDALLION, 1);
							if (getQuestItemsCount(killer, SHINING_MEDALLION) == 62)
							{
								qs.setMemoState(22);
								qs.setCond(13, true);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case VAMPIRE_WIZARD:
				case VAMPIRE_WIZARD_A:
				{
					if (qs.isMemoState(7) && !hasQuestItems(killer, BLOOD_JEWEL))
					{
						if (getRandom(100) < 8)
						{
							giveItems(killer, BLOOD_JEWEL, 1);
							qs.setCond(9, true);
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
			if (npc.getId() == HIERARCH_KEKROPUS)
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					if (player.getRace() == Race.KAMAEL)
					{
						if (hasQuestItems(player, STAR_OF_DESTINY))
						{
							htmltext = "32138-01.htm";
						}
						else
						{
							htmltext = "32138-01x.html";
						}
					}
					else
					{
						htmltext = "32138-01y.html";
					}
				}
				else
				{
					htmltext = "32138-01z.html";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case HIERARCH_KEKROPUS:
				{
					switch (qs.getMemoState())
					{
						case 1:
						{
							htmltext = "32138-05.html";
							break;
						}
						case 30:
						{
							htmltext = "32138-06.html";
							break;
						}
						case 40:
						{
							htmltext = "32138-13.html";
							break;
						}
					}
					break;
				}
				case KURSTIN:
				{
					switch (qs.getMemoState())
					{
						case 11:
						{
							htmltext = "31387-01.html";
							break;
						}
						case 12:
						{
							htmltext = "31387-04.html";
							break;
						}
						case 20:
						{
							if (qs.getMemoStateEx(1) == 1)
							{
								htmltext = "31387-11.html";
							}
							break;
						}
					}
					break;
				}
				case MYSTERIOU_WIZARD:
				{
					switch (qs.getMemoState())
					{
						case 1:
						{
							htmltext = "31522-01.html";
							break;
						}
						case 2:
						{
							if (!hasQuestItems(player, BLACK_ECHO_CRYSTAL))
							{
								htmltext = "31522-06a.html";
							}
							else
							{
								htmltext = "31522-07a.html";
							}
							break;
						}
						case 6:
						{
							htmltext = "31522-10a.html";
							break;
						}
						case 20:
						{
							if (qs.getMemoStateEx(1) == 0)
							{
								htmltext = "31522-15a.html";
							}
							break;
						}
						case 3:
						{
							if (qs.getMemoStateEx(1) == 0)
							{
								qs.setMemoStateEx(1, 1);
								htmltext = "31522-06b.html";
							}
							else if (qs.getMemoStateEx(1) == 1)
							{
								qs.setMemoStateEx(1, 2);
								qs.setCond(7, true);
								htmltext = "31522-07b.html";
							}
							else if (qs.getMemoStateEx(1) == 2)
							{
								htmltext = "31522-08b.html";
							}
							break;
						}
						case 7:
						{
							if (!hasQuestItems(player, BLOOD_JEWEL))
							{
								htmltext = "31522-12b.html";
							}
							else
							{
								htmltext = "31522-13b.html";
							}
							break;
						}
						case 11:
						{
							htmltext = "31522-16b.html";
							break;
						}
					}
					break;
				}
				case VICE_HIERARCH_MAO:
				{
					if ((qs.getMemoState() >= 40) && (qs.getMemoState() <= 45))
					{
						htmltext = "32190-01.html";
					}
					break;
				}
				case KATENAR:
				{
					switch (qs.getMemoState())
					{
						case 6:
						{
							final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
							final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
							npc0.getVariables().set("SPAWNED", false);
							if (player == c0)
							{
								htmltext = "32235-01a.html";
							}
							else
							{
								htmltext = "32235-01z.html";
							}
							break;
						}
						case 20:
						{
							if (qs.getMemoStateEx(1) == 0)
							{
								htmltext = "32235-09z.html";
							}
							break;
						}
					}
					break;
				}
				case HARKILGAMED:
				{
					switch (qs.getMemoState())
					{
						case 20:
						{
							final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
							final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
							npc0.getVariables().set("SPAWNED", false);
							if (player == c0)
							{
								htmltext = "32236-01.html";
							}
							else
							{
								htmltext = "32236-02.html";
							}
							break;
						}
						case 21:
						{
							htmltext = "32236-07z.html";
							break;
						}
						case 22:
						{
							htmltext = "32236-08z.html";
							break;
						}
					}
					break;
				}
				case RODENPICULA:
				{
					switch (qs.getMemoState())
					{
						case 40:
						{
							htmltext = "32237-01.html";
							break;
						}
						case 42:
						{
							htmltext = "32237-11a.html";
							break;
						}
						case 43:
						{
							htmltext = "32237-12.html";
							break;
						}
						case 44:
						{
							htmltext = "32237-14.html";
							break;
						}
						case 45:
						{
							htmltext = "32237-15.html";
							break;
						}
					}
					break;
				}
				case ROCK:
				{
					switch (qs.getMemoState())
					{
						case 20:
						{
							htmltext = "32238-01.html";
							break;
						}
						case 21:
						{
							htmltext = "32238-04.html";
							break;
						}
						case 22:
						{
							htmltext = "32238-05.html";
							break;
						}
						case 30:
						{
							htmltext = "32238-09.html";
							break;
						}
					}
					break;
				}
				case MOTHER_NORNIL:
				{
					switch (qs.getMemoState())
					{
						case 40:
						{
							htmltext = "32239-01.html";
							break;
						}
						case 42:
						{
							htmltext = "32239-02.html";
							break;
						}
						case 43:
						{
							htmltext = "32239-05.html";
							break;
						}
						case 44:
						{
							htmltext = "32239-06.html";
							break;
						}
						case 45:
						{
							htmltext = "32239-09.html";
							break;
						}
					}
					break;
				}
				case KATENAR_A:
				{
					switch (qs.getMemoState())
					{
						case 3:
						{
							if (qs.getMemoStateEx(1) == 2)
							{
								final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
								final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
								npc0.getVariables().set("SPAWNED", false);
								if (player == c0)
								{
									htmltext = "32332-01b.html";
								}
								else
								{
									htmltext = "32332-01z.html";
								}
							}
							break;
						}
						case 7:
						{
							if (!hasQuestItems(player, BLOOD_JEWEL))
							{
								htmltext = "32332-05z.html";
							}
							break;
						}
					}
					break;
				}
				case KATENAR_B:
				{
					switch (qs.getMemoState())
					{
						case 7:
						{
							if (hasQuestItems(player, BLOOD_JEWEL))
							{
								final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
								final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
								npc0.getVariables().set("SPAWNED", false);
								if (player == c0)
								{
									qs.setMemoState(11);
									qs.setCond(10, true);
									htmltext = "32333-06bz.html";
								}
								else
								{
									qs.setMemoState(11);
									qs.setCond(10, true);
									htmltext = "32333-06b.html";
								}
							}
							break;
						}
						case 11:
						{
							htmltext = "32333-06b.html";
							break;
						}
					}
					break;
				}
				case HARKILGAMED_A:
				{
					switch (qs.getMemoState())
					{
						case 22:
						{
							final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
							final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
							npc0.getVariables().set("SPAWNED", false);
							if (player == c0)
							{
								htmltext = "32334-08.html";
							}
							else
							{
								htmltext = "32334-09.html";
							}
							break;
						}
						case 30:
						{
							htmltext = "32334-18.html";
							break;
						}
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == HIERARCH_KEKROPUS)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
		}
		return htmltext;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		switch (npc.getId())
		{
			case KATENAR:
			{
				final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
				startQuestTimer("KATENAR_120", 120000, npc, null);
				if (c0 != null)
				{
					npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.S1_FINALLY_WE_MEET).addStringParameter(c0.getAppearance().getVisibleName()));
				}
				break;
			}
			case HARKILGAMED:
			{
				startQuestTimer("HARKILGAMED_120", 120000, npc, null);
				npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.HMM_IS_SOMEONE_APPROACHING));
				break;
			}
			case KATENAR_A:
			{
				final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
				startQuestTimer("KATENAR_A_120", 120000, npc, null);
				if (c0 != null)
				{
					npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.S1_DID_YOU_WAIT_FOR_LONG).addStringParameter(c0.getAppearance().getVisibleName()));
				}
				break;
			}
			case KATENAR_B:
			{
				final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
				startQuestTimer("KATENAR_B_120", 120000, npc, null);
				if (c0 != null)
				{
					npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.DID_YOU_BRING_WHAT_I_ASKED_S1).addStringParameter(c0.getAppearance().getVisibleName()));
				}
				break;
			}
			case HARKILGAMED_A:
			{
				final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
				startQuestTimer("HARKILGAMED_A_120", 120000, npc, null);
				if (c0 != null)
				{
					npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.S1_HAS_EVERYTHING_BEEN_FOUND).addStringParameter(c0.getAppearance().getVisibleName()));
				}
				break;
			}
		}
		return super.onSpawn(npc);
	}
}
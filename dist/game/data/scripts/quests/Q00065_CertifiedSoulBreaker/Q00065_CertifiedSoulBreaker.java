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
package quests.Q00065_CertifiedSoulBreaker;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.CategoryType;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;
import com.l2jmobius.gameserver.util.Util;

/**
 * Certified Soul Breaker (65)
 * @author ivantotov
 */
public final class Q00065_CertifiedSoulBreaker extends Quest
{
	// NPCs
	private static final int CAPTAIN_LUCAS = 30071;
	private static final int JACOB = 30073;
	private static final int GUARD_HARLAN = 30074;
	private static final int GUARD_XABER = 30075;
	private static final int GUARD_LIAM = 30076;
	private static final int GUARD_VESA = 30123;
	private static final int GUARD_ZEROME = 30124;
	private static final int WHARF_MANAGER_FELTON = 30879;
	private static final int KEKROPUS = 32138;
	private static final int VICE_HIERARCH_CASCA = 32139;
	private static final int GRAND_MASTER_HOLST = 32199;
	private static final int GRAND_MASTER_VITUS = 32213;
	private static final int GRAND_MASTER_MELDINA = 32214;
	private static final int KATENAR = 32242;
	private static final int CARGO_BOX = 32243;
	private static final int SUSPICIOUS_MAN = 32244;
	// Items
	private static final int SEALED_DOCUMENT = 9803;
	private static final int WYRM_HEART = 9804;
	private static final int KEKROPUS_RECOMMENDATION = 9805;
	// Reward
	private static final int DIMENSIONAL_DIAMOND = 7562;
	private static final int SOUL_BREAKER_CERTIFICATE = 9806;
	// Monster
	private static final int WYRM = 20176;
	// Quest Monster
	private static final int GUARDIAN_ANGEL = 27332;
	// Misc
	private static final int MIN_LEVEL = 39;
	// Locations
	private static final Location SUSPICIOUS_SPAWN = new Location(16489, 146249, -3112);
	private static final Location MOVE_TO = new Location(16490, 145839, -3080);
	
	public Q00065_CertifiedSoulBreaker()
	{
		super(65);
		addStartNpc(GRAND_MASTER_VITUS);
		addTalkId(GRAND_MASTER_VITUS, CAPTAIN_LUCAS, JACOB, GUARD_HARLAN, GUARD_XABER, GUARD_LIAM, GUARD_VESA, GUARD_ZEROME, WHARF_MANAGER_FELTON, KEKROPUS, VICE_HIERARCH_CASCA, GRAND_MASTER_HOLST, GRAND_MASTER_MELDINA, KATENAR, CARGO_BOX, SUSPICIOUS_MAN);
		addKillId(WYRM, GUARDIAN_ANGEL);
		addSpawnId(GUARDIAN_ANGEL, SUSPICIOUS_MAN);
		registerQuestItems(SEALED_DOCUMENT, WYRM_HEART, KEKROPUS_RECOMMENDATION);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ("DESPAWN_5".equals(event))
		{
			if (npc != null)
			{
				npc.deleteMe();
			}
			return super.onAdvEvent(event, npc, player);
		}
		else if ("DESPAWN_70".equals(event))
		{
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
			if (npc0 != null)
			{
				if (npc0.getVariables().getBoolean("SPAWNED"))
				{
					npc0.getVariables().set("SPAWNED", false);
					if (c0 != null)
					{
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.S1_I_WILL_BE_BACK_SOON_STAY_THERE_AND_DON_T_YOU_DARE_WANDER_OFF).addStringParameter(c0.getAppearance().getVisibleName()));
					}
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
			case "ACCEPT":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					if (player.getVariables().getInt("2ND_CLASS_DIAMOND_REWARD", 0) == 0)
					{
						giveItems(player, DIMENSIONAL_DIAMOND, 47);
						player.getVariables().set("2ND_CLASS_DIAMOND_REWARD", 1);
						htmltext = "32213-05.htm";
					}
					else
					{
						htmltext = "32213-06.htm";
					}
				}
				break;
			}
			case "32213-09.html":
			{
				htmltext = event;
				break;
			}
			case "32213-04.htm":
			{
				if ((player.getLevel() >= MIN_LEVEL) && player.isInCategory(CategoryType.KAMAEL_SECOND_CLASS_GROUP))
				{
					htmltext = event;
				}
				break;
			}
			case "30071-02.html":
			{
				if (qs.isMemoState(7))
				{
					qs.setMemoState(8);
					qs.setCond(8, true);
					htmltext = event;
				}
				break;
			}
			case "30879-02.html":
			{
				if (qs.isMemoState(11))
				{
					htmltext = event;
				}
				break;
			}
			case "30879-03.html":
			{
				if (qs.isMemoState(11))
				{
					qs.setMemoState(12);
					qs.setCond(12, true);
					htmltext = event;
				}
				break;
			}
			case "32138-02.html":
			case "32138-03.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32138-04.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "32138-07.html":
			{
				if (qs.isMemoState(21))
				{
					qs.setMemoState(22);
					qs.setCond(15, true);
					htmltext = event;
				}
				break;
			}
			case "32138-10.html":
			case "32138-11.html":
			{
				if (qs.isMemoState(23))
				{
					htmltext = event;
				}
				break;
			}
			case "32138-12.html":
			{
				if (qs.isMemoState(23))
				{
					takeItems(player, WYRM_HEART, -1);
					giveItems(player, KEKROPUS_RECOMMENDATION, 1);
					qs.setMemoState(24);
					qs.setCond(17, true);
					htmltext = event;
				}
				break;
			}
			case "32139-02.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setMemoState(3);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32139-04.html":
			{
				if (qs.isMemoState(3))
				{
					qs.setMemoState(4);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "32139-07.html":
			{
				if (qs.isMemoState(14))
				{
					htmltext = event;
				}
				break;
			}
			case "32139-08.html":
			{
				if (qs.isMemoState(14))
				{
					takeItems(player, SEALED_DOCUMENT, -1);
					qs.setMemoState(21);
					qs.setCond(14, true);
					htmltext = event;
				}
				break;
			}
			case "32199-02.html":
			{
				if (qs.isMemoState(4))
				{
					qs.setMemoState(5);
					qs.setCond(5, true);
					addSpawn(npc, SUSPICIOUS_MAN, SUSPICIOUS_SPAWN, false, 0);
					htmltext = event;
				}
				break;
			}
			case "32214-02.html":
			{
				if (qs.isMemoState(10))
				{
					qs.setMemoState(11);
					qs.setCond(11, true);
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
				case WYRM:
				{
					if (qs.isMemoState(22))
					{
						if (giveItemRandomly(killer, npc, WYRM_HEART, 1, 10, 0.20, true))
						{
							qs.setMemoState(23);
							qs.setCond(16, true);
						}
					}
					break;
				}
				case GUARDIAN_ANGEL:
				{
					final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
					final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
					if (killer == c0)
					{
						if (c0 != null)
						{
							if (qs.isMemoState(12))
							{
								final L2Npc katenar = addSpawn(KATENAR, killer.getX() + 20, killer.getY() + 20, killer.getZ(), 0, false, 0);
								katenar.getVariables().set("player0", killer);
								katenar.getVariables().set("npc0", npc);
								qs.setMemoState(13);
								npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.GRR_I_VE_BEEN_HIT));
							}
						}
					}
					else
					{
						if (npc0 != null)
						{
							if (npc0.getVariables().getBoolean("SPAWNED"))
							{
								npc0.getVariables().set("SPAWNED", false);
							}
						}
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.GRR_WHO_ARE_YOU_AND_WHY_HAVE_YOU_STOPPED_ME));
					}
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
			if (npc.getId() == GRAND_MASTER_VITUS)
			{
				if (player.getRace() == Race.KAMAEL)
				{
					if ((player.getLevel() >= MIN_LEVEL) && player.isInCategory(CategoryType.KAMAEL_SECOND_CLASS_GROUP))
					{
						htmltext = "32213-01.htm";
					}
					else
					{
						htmltext = "32213-03.html";
					}
				}
				else
				{
					htmltext = "32213-02.html";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case GRAND_MASTER_VITUS:
				{
					if (memoState == 1)
					{
						htmltext = "32213-07.html";
					}
					else if ((memoState > 1) && (memoState < 24))
					{
						htmltext = "32213-08.html";
					}
					else if (memoState == 24)
					{
						giveAdena(player, 71194, true);
						giveItems(player, SOUL_BREAKER_CERTIFICATE, 1);
						addExpAndSp(player, 393750, 27020);
						qs.exitQuest(false, true);
						player.sendPacket(new SocialAction(player.getObjectId(), 3));
						htmltext = "32213-10.html";
					}
					break;
				}
				case CAPTAIN_LUCAS:
				{
					if (memoState == 7)
					{
						htmltext = "30071-01.html";
					}
					else if (memoState == 8)
					{
						htmltext = "30071-03.html";
					}
					break;
				}
				case JACOB:
				{
					if (memoState == 6)
					{
						if (qs.getMemoStateEx(1) == 0)
						{
							qs.setMemoStateEx(1, 10);
							htmltext = "30073-01.html";
						}
						else if (qs.getMemoStateEx(1) == 10)
						{
							htmltext = "30073-01a.html";
						}
						else if (qs.getMemoStateEx(1) == 1)
						{
							qs.setMemoState(7);
							qs.setMemoStateEx(1, 0);
							qs.setCond(7, true);
							htmltext = "30073-02.html";
						}
					}
					else if (memoState == 7)
					{
						htmltext = "30073-03.html";
					}
					break;
				}
				case GUARD_HARLAN:
				{
					if (memoState == 6)
					{
						if (qs.getMemoStateEx(1) == 0)
						{
							qs.setMemoStateEx(1, 1);
							htmltext = "30074-01.html";
						}
						else if (qs.getMemoStateEx(1) == 1)
						{
							htmltext = "30074-01a.html";
						}
						else if (qs.getMemoStateEx(1) == 10)
						{
							qs.setMemoState(7);
							qs.setMemoStateEx(1, 0);
							qs.setCond(7, true);
							htmltext = "30074-02.html";
						}
					}
					else if (memoState == 7)
					{
						htmltext = "30074-03.html";
					}
					break;
				}
				case GUARD_XABER:
				{
					if (memoState == 8)
					{
						if (qs.getMemoStateEx(1) == 0)
						{
							qs.setMemoStateEx(1, 1);
							htmltext = "30075-01.html";
						}
						else if (qs.getMemoStateEx(1) == 1)
						{
							htmltext = "30075-01a.html";
						}
						else if (qs.getMemoStateEx(1) == 10)
						{
							qs.setMemoState(9);
							qs.setMemoStateEx(1, 0);
							qs.setCond(9, true);
							htmltext = "30075-02.html";
						}
					}
					else if (memoState == 9)
					{
						htmltext = "30075-03.html";
					}
					break;
				}
				case GUARD_LIAM:
				{
					if (memoState == 8)
					{
						if (qs.getMemoStateEx(1) == 0)
						{
							qs.setMemoStateEx(1, 10);
							htmltext = "30076-01.html";
						}
						else if (qs.getMemoStateEx(1) == 10)
						{
							htmltext = "30076-01a.html";
						}
						else if (qs.getMemoStateEx(1) == 1)
						{
							qs.setMemoState(9);
							qs.setMemoStateEx(1, 0);
							qs.setCond(9, true);
							htmltext = "30076-02.html";
						}
					}
					else if (memoState == 9)
					{
						htmltext = "30076-03.html";
					}
					break;
				}
				case GUARD_VESA:
				{
					if (memoState == 9)
					{
						if (qs.getMemoStateEx(1) == 0)
						{
							qs.setMemoStateEx(1, 10);
							htmltext = "30123-01.html";
						}
						else if (qs.getMemoStateEx(1) == 10)
						{
							htmltext = "30123-01.html";
						}
						else if (qs.getMemoStateEx(1) == 1)
						{
							qs.setMemoState(10);
							qs.setMemoStateEx(1, 0);
							qs.setCond(10, true);
							htmltext = "30123-02.html";
						}
					}
					else if (memoState == 10)
					{
						htmltext = "30123-03.html";
					}
					break;
				}
				case GUARD_ZEROME:
				{
					if (memoState == 9)
					{
						if (qs.getMemoStateEx(1) == 0)
						{
							qs.setMemoStateEx(1, 1);
							htmltext = "30124-01.html";
						}
						else if (qs.getMemoStateEx(1) == 1)
						{
							htmltext = "30124-01.html";
						}
						else if (qs.getMemoStateEx(1) == 10)
						{
							qs.setMemoState(10);
							qs.setMemoStateEx(1, 0);
							qs.setCond(10, true);
							htmltext = "30124-02.html";
						}
					}
					else if (memoState == 10)
					{
						htmltext = "30124-03.html";
					}
					break;
				}
				case WHARF_MANAGER_FELTON:
				{
					if (memoState == 11)
					{
						htmltext = "30879-01.html";
					}
					else if (memoState == 12)
					{
						htmltext = "30879-04.html";
					}
					break;
				}
				case KEKROPUS:
				{
					if (memoState == 1)
					{
						htmltext = getHtm(player, "32138-01.html");
						htmltext = htmltext.replaceAll("%name1%", player.getName());
					}
					else if (memoState == 2)
					{
						htmltext = "32138-05.html";
					}
					else if (memoState == 21)
					{
						htmltext = "32138-06.html";
					}
					else if (memoState == 22)
					{
						htmltext = "32138-08.html";
					}
					else if (memoState == 23)
					{
						htmltext = "32138-09.html";
					}
					else if (memoState == 24)
					{
						htmltext = "32138-13.html";
					}
					break;
				}
				case VICE_HIERARCH_CASCA:
				{
					if (memoState == 2)
					{
						htmltext = "32139-01.html";
					}
					else if (memoState == 3)
					{
						htmltext = "32139-03.html";
					}
					else if (memoState == 4)
					{
						htmltext = "32139-05.html";
					}
					else if (memoState == 14)
					{
						htmltext = "32139-06.html";
					}
					else if (memoState == 21)
					{
						htmltext = "32139-09.html";
					}
					break;
				}
				case GRAND_MASTER_HOLST:
				{
					if (memoState == 4)
					{
						htmltext = "32199-01.html";
					}
					else if (memoState == 5)
					{
						qs.setMemoState(6);
						qs.setMemoStateEx(1, 0); // Custom line
						qs.setCond(6, true);
						htmltext = "32199-03.html";
					}
					else if (memoState == 6)
					{
						htmltext = "32199-04.html";
					}
					break;
				}
				case GRAND_MASTER_MELDINA:
				{
					if (memoState == 10)
					{
						htmltext = "32214-01.html";
					}
					else if (memoState == 11)
					{
						htmltext = "32214-03.html";
					}
					break;
				}
				case CARGO_BOX:
				{
					if (memoState == 12)
					{
						if (!npc.getVariables().getBoolean("SPAWNED", false))
						{
							npc.getVariables().set("SPAWNED", true);
							npc.getVariables().set("PLAYER_ID", player.getObjectId());
							final L2Npc angel = addSpawn(GUARDIAN_ANGEL, 36110, 191921, -3712, 0, true, 0, false);
							angel.getVariables().set("npc0", npc);
							angel.getVariables().set("player0", player);
							addAttackDesire(angel, player);
							htmltext = "32243-01.html";
						}
						else if (npc.getVariables().getInt("PLAYER_ID") == player.getObjectId())
						{
							htmltext = "32243-03.html";
						}
						else
						{
							htmltext = "32243-02.html";
						}
					}
					else if (memoState == 13)
					{
						if (!npc.getVariables().getBoolean("SPAWNED", false))
						{
							npc.getVariables().set("SPAWNED", true);
							npc.getVariables().set("PLAYER_ID", player.getObjectId());
							final L2Npc katenar = addSpawn(KATENAR, 36110, 191921, -3712, 0, false, 0);
							katenar.getVariables().set("player0", player);
							katenar.getVariables().set("npc0", npc);
							htmltext = "32243-06.html";
						}
						else if (npc.getVariables().getInt("PLAYER_ID") == player.getObjectId())
						{
							htmltext = "32243-04.html";
						}
						else
						{
							htmltext = "32243-05.html";
						}
					}
					else if (memoState == 14)
					{
						htmltext = "32243-07.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == GRAND_MASTER_VITUS)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
		}
		return htmltext;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		if (npc.getId() == SUSPICIOUS_MAN)
		{
			startQuestTimer("DESPAWN_5", 5000, npc, null);
			npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.DRATS_HOW_COULD_I_BE_SO_WRONG));
			npc.setRunning();
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, MOVE_TO);
		}
		else if (npc.getId() == GUARDIAN_ANGEL)
		{
			final L2PcInstance c0 = npc.getVariables().getObject("player0", L2PcInstance.class);
			startQuestTimer("DESPAWN_70", 70000, npc, null);
			if (c0 != null)
			{
				npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.S1_STEP_BACK_FROM_THE_CONFOUNDED_BOX_I_WILL_TAKE_IT_MYSELF).addStringParameter(c0.getAppearance().getVisibleName()));
			}
		}
		return super.onSpawn(npc);
	}
}
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
package ai.areas.CrumaTower.Alarm;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;

import ai.AbstractNpcAI;
import quests.Q00184_ArtOfPersuasion.Q00184_ArtOfPersuasion;
import quests.Q00185_NikolasCooperation.Q00185_NikolasCooperation;

/**
 * Alarm AI for quests Art of Persuasion (184) and Nikola's Cooperation (185).
 * @author Zoey76
 */
public final class Alarm extends AbstractNpcAI
{
	// NPC
	private static final int ALARM = 32367;
	// Misc
	private static final int ART_OF_PERSUASION_ID = 184;
	private static final int NIKOLAS_COOPERATION_ID = 185;
	
	private Alarm()
	{
		addStartNpc(ALARM);
		addTalkId(ALARM);
		addFirstTalkId(ALARM);
		addSpawnId(ALARM);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		final L2PcInstance player0 = npc.getVariables().getObject("player0", L2PcInstance.class);
		final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
		switch (event)
		{
			case "SELF_DESTRUCT_IN_60":
			{
				startQuestTimer("SELF_DESTRUCT_IN_30", 30000, npc, null);
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_ENTER_PASSCODE_TO_OVERRIDE);
				break;
			}
			case "SELF_DESTRUCT_IN_30":
			{
				startQuestTimer("SELF_DESTRUCT_IN_10", 20000, npc, null);
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_ENTER_PASSCODE_TO_OVERRIDE);
				break;
			}
			case "SELF_DESTRUCT_IN_10":
			{
				startQuestTimer("RECORDER_CRUSHED", 10000, npc, null);
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_ENTER_PASSCODE_TO_OVERRIDE);
				break;
			}
			case "RECORDER_CRUSHED":
			{
				if ((npc0 != null) && npc0.getVariables().getBoolean("SPAWNED"))
				{
					npc0.getVariables().set("SPAWNED", false);
					if (player0 != null)
					{
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.RECORDER_CRUSHED);
						if (verifyMemoState(player0, ART_OF_PERSUASION_ID, -1))
						{
							setMemoState(player0, ART_OF_PERSUASION_ID, 5);
						}
						else if (verifyMemoState(player0, NIKOLAS_COOPERATION_ID, -1))
						{
							setMemoState(player0, NIKOLAS_COOPERATION_ID, 5);
						}
					}
				}
				npc.deleteMe();
				break;
			}
			case "32367-184_04.html":
			case "32367-184_06.html":
			case "32367-184_08.html":
			{
				htmltext = event;
				break;
			}
			case "2":
			{
				if (player0 == player)
				{
					if (verifyMemoState(player, ART_OF_PERSUASION_ID, 3))
					{
						htmltext = "32367-184_02.html";
					}
					else if (verifyMemoState(player, NIKOLAS_COOPERATION_ID, 3))
					{
						htmltext = "32367-185_02.html";
					}
				}
				break;
			}
			case "3":
			{
				if (verifyMemoState(player, ART_OF_PERSUASION_ID, 3))
				{
					setMemoStateEx(player, ART_OF_PERSUASION_ID, 1, 1);
					htmltext = "32367-184_04.html";
				}
				else if (verifyMemoState(player, NIKOLAS_COOPERATION_ID, 3))
				{
					setMemoStateEx(player, NIKOLAS_COOPERATION_ID, 1, 1);
					htmltext = "32367-185_04.html";
				}
				break;
			}
			case "4":
			{
				if (verifyMemoState(player, ART_OF_PERSUASION_ID, 3))
				{
					setMemoStateEx(player, ART_OF_PERSUASION_ID, 1, getMemoStateEx(player, ART_OF_PERSUASION_ID, 1) + 1);
					htmltext = "32367-184_06.html";
				}
				else if (verifyMemoState(player, NIKOLAS_COOPERATION_ID, 3))
				{
					setMemoStateEx(player, NIKOLAS_COOPERATION_ID, 1, getMemoStateEx(player, NIKOLAS_COOPERATION_ID, 1) + 1);
					htmltext = "32367-185_06.html";
				}
				break;
			}
			case "5":
			{
				if (verifyMemoState(player, ART_OF_PERSUASION_ID, 3))
				{
					setMemoStateEx(player, ART_OF_PERSUASION_ID, 1, getMemoStateEx(player, ART_OF_PERSUASION_ID, 1) + 1);
					htmltext = "32367-184_08.html";
				}
				else if (verifyMemoState(player, NIKOLAS_COOPERATION_ID, 3))
				{
					setMemoStateEx(player, NIKOLAS_COOPERATION_ID, 1, getMemoStateEx(player, NIKOLAS_COOPERATION_ID, 1) + 1);
					htmltext = "32367-185_08.html";
				}
				break;
			}
			case "6":
			{
				if (verifyMemoState(player, ART_OF_PERSUASION_ID, 3))
				{
					if (getMemoStateEx(player, ART_OF_PERSUASION_ID, 1) >= 3)
					{
						if ((npc0 != null) && npc0.getVariables().getBoolean("SPAWNED"))
						{
							npc0.getVariables().set("SPAWNED", false);
						}
						npc.deleteMe();
						setMemoState(player, ART_OF_PERSUASION_ID, 4);
						htmltext = "32367-184_09.html";
					}
					else
					{
						setMemoStateEx(player, ART_OF_PERSUASION_ID, 1, 0);
						htmltext = "32367-184_10.html";
					}
				}
				else if (verifyMemoState(player, NIKOLAS_COOPERATION_ID, 3))
				{
					if (getMemoStateEx(player, NIKOLAS_COOPERATION_ID, 1) >= 3)
					{
						if ((npc0 != null) && npc0.getVariables().getBoolean("SPAWNED"))
						{
							npc0.getVariables().set("SPAWNED", false);
						}
						npc.deleteMe();
						setMemoState(player, NIKOLAS_COOPERATION_ID, 4);
						htmltext = "32367-185_09.html";
					}
					else
					{
						setMemoStateEx(player, NIKOLAS_COOPERATION_ID, 1, 0);
						htmltext = "32367-185_10.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance talker)
	{
		String htmltext = getNoQuestMsg(talker);
		if (verifyMemoState(talker, ART_OF_PERSUASION_ID, 3) || verifyMemoState(talker, NIKOLAS_COOPERATION_ID, 3))
		{
			if (npc.getVariables().getObject("player0", L2PcInstance.class) == talker)
			{
				htmltext = "32367-01.html";
			}
			else
			{
				htmltext = "32367-02.html";
			}
		}
		return htmltext;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		startQuestTimer("SELF_DESTRUCT_IN_60", 60000, npc, null);
		npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.INTRUDER_ALERT_THE_ALARM_WILL_SELF_DESTRUCT_IN_2_MINUTES);
		final L2PcInstance player = npc.getVariables().getObject("player0", L2PcInstance.class);
		if (player != null)
		{
			playSound(player, QuestSound.ITEMSOUND_SIREN);
		}
		return super.onSpawn(npc);
	}
	
	/**
	 * Verifies if the given player has the require memo state.
	 * @param player the player
	 * @param questId the quest ID
	 * @param memoState the memo state, if memo state is less than zero, only quest state is checked
	 * @return {@code true} if the player has the memo state, {@code false} otherwise
	 */
	private static boolean verifyMemoState(L2PcInstance player, int questId, int memoState)
	{
		QuestState qs = null;
		switch (questId)
		{
			case ART_OF_PERSUASION_ID:
			{
				qs = player.getQuestState(Q00184_ArtOfPersuasion.class.getSimpleName());
				break;
			}
			case NIKOLAS_COOPERATION_ID:
			{
				qs = player.getQuestState(Q00185_NikolasCooperation.class.getSimpleName());
				break;
			}
		}
		return (qs != null) && ((memoState < 0) || qs.isMemoState(memoState));
	}
	
	/**
	 * Sets the memo state for the given player and quest.
	 * @param player the player
	 * @param questId the quest ID
	 * @param memoState the memo state
	 */
	private static void setMemoState(L2PcInstance player, int questId, int memoState)
	{
		QuestState qs = null;
		switch (questId)
		{
			case ART_OF_PERSUASION_ID:
			{
				qs = player.getQuestState(Q00184_ArtOfPersuasion.class.getSimpleName());
				break;
			}
			case NIKOLAS_COOPERATION_ID:
			{
				qs = player.getQuestState(Q00185_NikolasCooperation.class.getSimpleName());
				break;
			}
		}
		if (qs != null)
		{
			qs.setMemoState(memoState);
		}
	}
	
	/**
	 * Gets the memo state ex for the given player, quest and slot.
	 * @param player the player
	 * @param questId the quest ID
	 * @param slot the slot
	 * @return the memo state ex
	 */
	private static int getMemoStateEx(L2PcInstance player, int questId, int slot)
	{
		QuestState qs = null;
		switch (questId)
		{
			case ART_OF_PERSUASION_ID:
			{
				qs = player.getQuestState(Q00184_ArtOfPersuasion.class.getSimpleName());
				break;
			}
			case NIKOLAS_COOPERATION_ID:
			{
				qs = player.getQuestState(Q00185_NikolasCooperation.class.getSimpleName());
				break;
			}
		}
		return (qs != null) ? qs.getMemoStateEx(slot) : -1;
	}
	
	/**
	 * Sets the memo state ex for the given player and quest.
	 * @param player the player
	 * @param questId the quest ID
	 * @param slot the slot
	 * @param memoStateEx the memo state ex
	 */
	private static void setMemoStateEx(L2PcInstance player, int questId, int slot, int memoStateEx)
	{
		QuestState qs = null;
		switch (questId)
		{
			case ART_OF_PERSUASION_ID:
			{
				qs = player.getQuestState(Q00184_ArtOfPersuasion.class.getSimpleName());
				break;
			}
			case NIKOLAS_COOPERATION_ID:
			{
				qs = player.getQuestState(Q00185_NikolasCooperation.class.getSimpleName());
				break;
			}
		}
		if (qs != null)
		{
			qs.setMemoStateEx(slot, memoStateEx);
		}
	}
	
	public static void main(String[] args)
	{
		new Alarm();
	}
}

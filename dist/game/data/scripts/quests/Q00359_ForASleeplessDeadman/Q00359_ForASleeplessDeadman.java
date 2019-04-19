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
package quests.Q00359_ForASleeplessDeadman;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * For a Sleepless Deadman (359)
 * @author Adry_85
 */
public final class Q00359_ForASleeplessDeadman extends Quest
{
	// NPC
	private static final int ORVEN = 30857;
	// Item
	private static final int REMAINS_OF_ADEN_RESIDENTS = 5869;
	// Misc
	private static final int MIN_LEVEL = 60;
	private static final int REMAINS_COUNT = 60;
	// Rewards
	private static final int[] REWARDS = new int[]
	{
		5494, // Sealed Dark Crystal Shield Fragment
		5495, // Sealed Shield of Nightmare Fragment
		6341, // Sealed Phoenix Earring Gemstone
		6342, // Sealed Majestic Earring Gemstone
		6343, // Sealed Phoenix Necklace Beads
		6344, // Sealed Majestic Necklace Beads
		6345, // Sealed Phoenix Ring Gemstone
		6346, // Sealed Majestic Ring Gemstone
	};
	// Mobs
	private static final Map<Integer, Double> MOBS = new HashMap<>();
	static
	{
		MOBS.put(21006, 0.365); // doom_servant
		MOBS.put(21007, 0.392); // doom_guard
		MOBS.put(21008, 0.503); // doom_archer
	}
	
	public Q00359_ForASleeplessDeadman()
	{
		super(359);
		addStartNpc(ORVEN);
		addTalkId(ORVEN);
		addKillId(MOBS.keySet());
		registerQuestItems(REMAINS_OF_ADEN_RESIDENTS);
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
			case "30857-02.htm":
			case "30857-03.htm":
			case "30857-04.htm":
			{
				htmltext = event;
				break;
			}
			case "30857-05.htm":
			{
				qs.setMemoState(1);
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "30857-10.html":
			{
				rewardItems(player, REWARDS[getRandom(REWARDS.length)], 4);
				qs.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(player, 1, 3, npc);
		if ((qs != null) && giveItemRandomly(qs.getPlayer(), npc, REMAINS_OF_ADEN_RESIDENTS, 1, REMAINS_COUNT, MOBS.get(npc.getId()), true))
		{
			qs.setCond(2, true);
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "30857-01.htm" : "30857-06.html";
		}
		else if (qs.isStarted())
		{
			if (qs.isMemoState(1))
			{
				if (getQuestItemsCount(player, REMAINS_OF_ADEN_RESIDENTS) < REMAINS_COUNT)
				{
					htmltext = "30857-07.html";
				}
				else
				{
					takeItems(player, REMAINS_OF_ADEN_RESIDENTS, -1);
					qs.setMemoState(2);
					qs.setCond(3, true);
					htmltext = "30857-08.html";
				}
			}
			else if (qs.isMemoState(2))
			{
				htmltext = "30857-09.html";
			}
		}
		return htmltext;
	}
}

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
package quests.Q00186_ContractExecution;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

import quests.Q00184_ArtOfPersuasion.Q00184_ArtOfPersuasion;

/**
 * Contract Execution (186)
 * @author ivantotov
 */
public final class Q00186_ContractExecution extends Quest
{
	// NPCs
	private static final int MAESTRO_NIKOLA = 30621;
	private static final int RESEARCHER_LORAIN = 30673;
	private static final int BLUEPRINT_SELLER_LUKA = 31437;
	// Items
	private static final int LORAINES_CERTIFICATE = 10362;
	private static final int METALLOGRAPH_RESEARCH_REPORT = 10366;
	private static final int LETO_LIZARDMAN_ACCESSORY = 10367;
	// Misc
	private static final int MIN_LEVEL = 41;
	private static final int MAX_LEVEL_FOR_EXP_SP = 47;
	// Monsters
	private static final Map<Integer, Integer> MONSTERS = new HashMap<>();
	static
	{
		MONSTERS.put(20577, 40); // Leto Lizardman
		MONSTERS.put(20578, 44); // Leto Lizardman Archer
		MONSTERS.put(20579, 46); // Leto Lizardman Soldier
		MONSTERS.put(20580, 88); // Leto Lizardman Warrior
		MONSTERS.put(20581, 50); // Leto Lizardman Shaman
		MONSTERS.put(20582, 100); // Leto Lizardman Overlord
	}
	
	public Q00186_ContractExecution()
	{
		super(186);
		addStartNpc(RESEARCHER_LORAIN);
		addTalkId(RESEARCHER_LORAIN, BLUEPRINT_SELLER_LUKA, MAESTRO_NIKOLA);
		addKillId(MONSTERS.keySet());
		registerQuestItems(METALLOGRAPH_RESEARCH_REPORT, LETO_LIZARDMAN_ACCESSORY);
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
			case "30673-03.htm":
			{
				if ((player.getLevel() >= MIN_LEVEL) && hasQuestItems(player, LORAINES_CERTIFICATE))
				{
					qs.startQuest();
					qs.setMemoState(1);
					giveItems(player, METALLOGRAPH_RESEARCH_REPORT, 1);
					takeItems(player, LORAINES_CERTIFICATE, -1);
					htmltext = event;
				}
				break;
			}
			case "30621-02.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "30621-03.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "31437-03.html":
			{
				if (qs.isMemoState(2) && hasQuestItems(player, LETO_LIZARDMAN_ACCESSORY))
				{
					htmltext = event;
				}
				break;
			}
			case "31437-04.html":
			{
				if (qs.isMemoState(2) && hasQuestItems(player, LETO_LIZARDMAN_ACCESSORY))
				{
					qs.setMemoState(3);
					htmltext = event;
				}
				break;
			}
			case "31437-06.html":
			{
				if (qs.isMemoState(3))
				{
					giveAdena(player, 105083, true);
					if (player.getLevel() < MAX_LEVEL_FOR_EXP_SP)
					{
						addExpAndSp(player, 285935, 18711);
					}
					qs.exitQuest(false, true);
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
		if ((qs != null) && qs.isMemoState(2) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, false) && (getRandom(100) < MONSTERS.get(npc.getId())) && !hasQuestItems(killer, LETO_LIZARDMAN_ACCESSORY))
		{
			giveItems(killer, LETO_LIZARDMAN_ACCESSORY, 1);
			playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
			if (npc.getId() == RESEARCHER_LORAIN)
			{
				final QuestState q184 = player.getQuestState(Q00184_ArtOfPersuasion.class.getSimpleName());
				if ((q184 != null) && q184.isCompleted() && hasQuestItems(player, LORAINES_CERTIFICATE))
				{
					htmltext = player.getLevel() >= MIN_LEVEL ? "30673-01.htm" : "30673-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case RESEARCHER_LORAIN:
				{
					if (memoState >= 1)
					{
						htmltext = "30673-04.html";
					}
					break;
				}
				case MAESTRO_NIKOLA:
				{
					if (memoState == 1)
					{
						htmltext = "30621-01.html";
					}
					else if (memoState == 2)
					{
						htmltext = "30621-04.html";
					}
					break;
				}
				case BLUEPRINT_SELLER_LUKA:
				{
					if (memoState == 2)
					{
						htmltext = hasQuestItems(player, LETO_LIZARDMAN_ACCESSORY) ? "31437-02.html" : "31437-01.html";
					}
					else if (memoState == 3)
					{
						htmltext = "31437-05.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted() && (npc.getId() == RESEARCHER_LORAIN))
		{
			htmltext = getAlreadyCompletedMsg(player);
		}
		return htmltext;
	}
}
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
package quests.Q00018_MeetingWithTheGoldenRam;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Meeting With The Golden Ram (18)<br>
 * Original jython script by disKret.
 * @author nonom
 */
public class Q00018_MeetingWithTheGoldenRam extends Quest
{
	// NPCs
	private static final int DONAL = 31314;
	private static final int DAISY = 31315;
	private static final int ABERCROMBIE = 31555;
	// Item
	private static final int BOX = 7245;
	
	public Q00018_MeetingWithTheGoldenRam()
	{
		super(18);
		addStartNpc(DONAL);
		addTalkId(DONAL, DAISY, ABERCROMBIE);
		registerQuestItems(BOX);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "31314-03.html":
			{
				if (player.getLevel() >= 66)
				{
					st.startQuest();
				}
				else
				{
					htmltext = "31314-02.html";
				}
				break;
			}
			case "31315-02.html":
			{
				st.setCond(2, true);
				giveItems(player, BOX, 1);
				break;
			}
			case "31555-02.html":
			{
				if (hasQuestItems(player, BOX))
				{
					giveAdena(player, 40000, true);
					addExpAndSp(player, 126668, 11731);
					st.exitQuest(false, true);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		final int npcId = npc.getId();
		
		switch (st.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				if (npcId == DONAL)
				{
					htmltext = "31314-01.htm";
				}
				break;
			}
			case State.STARTED:
			{
				if (npcId == DONAL)
				{
					htmltext = "31314-04.html";
				}
				else if (npcId == DAISY)
				{
					htmltext = (st.getCond() < 2) ? "31315-01.html" : "31315-03.html";
				}
				else if ((npcId == ABERCROMBIE) && st.isCond(2) && hasQuestItems(player, BOX))
				{
					htmltext = "31555-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

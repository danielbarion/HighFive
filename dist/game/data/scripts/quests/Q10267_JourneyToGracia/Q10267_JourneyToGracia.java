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
package quests.Q10267_JourneyToGracia;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Journey To Gracia (10267)<br>
 * Original Jython script by Kerberos.
 * @author nonom
 */
public class Q10267_JourneyToGracia extends Quest
{
	// NPCs
	private static final int ORVEN = 30857;
	private static final int KEUCEREUS = 32548;
	private static final int PAPIKU = 32564;
	// Item
	private static final int LETTER = 13810;
	
	public Q10267_JourneyToGracia()
	{
		super(10267);
		addStartNpc(ORVEN);
		addTalkId(ORVEN, KEUCEREUS, PAPIKU);
		registerQuestItems(LETTER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "30857-06.html":
			{
				st.startQuest();
				giveItems(player, LETTER, 1);
				break;
			}
			case "32564-02.html":
			{
				st.setCond(2, true);
				break;
			}
			case "32548-02.html":
			{
				giveAdena(player, 92500, true);
				addExpAndSp(player, 75480, 7570);
				st.exitQuest(false, true);
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case ORVEN:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() < 75) ? "30857-00.html" : "30857-01.htm";
						break;
					}
					case State.STARTED:
					{
						htmltext = "30857-07.html";
						break;
					}
					case State.COMPLETED:
					{
						htmltext = "30857-0a.html";
						break;
					}
				}
				break;
			}
			case PAPIKU:
			{
				if (st.isStarted())
				{
					htmltext = st.isCond(1) ? "32564-01.html" : "32564-03.html";
				}
				break;
			}
			case KEUCEREUS:
			{
				if (st.isStarted() && st.isCond(2))
				{
					htmltext = "32548-01.html";
				}
				else if (st.isCompleted())
				{
					htmltext = "32548-03.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

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
package quests.Q00182_NewRecruits;

import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * New Recruits (182)
 * @author Gnacik
 * @version 2010-10-15 Based on official server Naia
 */
public class Q00182_NewRecruits extends Quest
{
	// NPCs
	private static final int KEKROPUS = 32138;
	private static final int MENACING_MACHINE = 32258;
	
	public Q00182_NewRecruits()
	{
		super(182);
		addStartNpc(KEKROPUS);
		addTalkId(KEKROPUS, MENACING_MACHINE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final String htmltext = event;
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return htmltext;
		}
		
		if (npc.getId() == KEKROPUS)
		{
			if (event.equalsIgnoreCase("32138-03.html"))
			{
				st.startQuest();
			}
		}
		else if (npc.getId() == MENACING_MACHINE)
		{
			if (event.equalsIgnoreCase("32258-04.html"))
			{
				giveItems(player, 847, 2);
				st.exitQuest(false, true);
			}
			else if (event.equalsIgnoreCase("32258-05.html"))
			{
				giveItems(player, 890, 2);
				st.exitQuest(false, true);
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
		if (npcId == KEKROPUS)
		{
			switch (st.getState())
			{
				case State.CREATED:
				{
					final int level = player.getLevel();
					if (player.getRace() == Race.KAMAEL)
					{
						htmltext = "32138-00.html";
					}
					else if ((level >= 17) && (level <= 21) && (player.getClassId().ordinal() == 0))
					{
						htmltext = "32138-01.htm";
					}
					else
					{
						htmltext = "32138-00b.html";
					}
					break;
				}
				case State.STARTED:
				{
					if (st.getInt("cond") == 1)
					{
						htmltext = "32138-04.html";
					}
					break;
				}
				case State.COMPLETED:
				{
					htmltext = getAlreadyCompletedMsg(player);
					break;
				}
			}
		}
		else if ((npcId == MENACING_MACHINE) && st.isStarted())
		{
			htmltext = "32258-01.html";
		}
		return htmltext;
	}
}

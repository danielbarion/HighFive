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
package quests.Q00019_GoToThePastureland;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Go to the Pastureland (19)<br>
 * Original Jython script by disKret.
 * @author malyelfik
 */
public class Q00019_GoToThePastureland extends Quest
{
	// NPCs
	private static final int VLADIMIR = 31302;
	private static final int TUNATUN = 31537;
	// Items
	private static final int VEAL = 15532;
	private static final int YOUNG_WILD_BEAST_MEAT = 7547;
	
	public Q00019_GoToThePastureland()
	{
		super(19);
		addStartNpc(VLADIMIR);
		addTalkId(VLADIMIR, TUNATUN);
		registerQuestItems(VEAL, YOUNG_WILD_BEAST_MEAT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState qs = getQuestState(player, false);
		
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		if (event.equalsIgnoreCase("31302-02.htm"))
		{
			qs.startQuest();
			giveItems(player, VEAL, 1);
		}
		else if (event.equalsIgnoreCase("31537-02.html"))
		{
			if (hasQuestItems(player, YOUNG_WILD_BEAST_MEAT))
			{
				giveAdena(player, 50000, true);
				addExpAndSp(player, 136766, 12688);
				qs.exitQuest(false, true);
				htmltext = "31537-02.html";
			}
			else if (hasQuestItems(player, VEAL))
			{
				giveAdena(player, 147200, true);
				addExpAndSp(player, 385040, 75250);
				qs.exitQuest(false, true);
				htmltext = "31537-02.html";
			}
			else
			{
				htmltext = "31537-03.html";
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (npc.getId() == VLADIMIR)
		{
			switch (qs.getState())
			{
				case State.CREATED:
				{
					if (player.getLevel() >= 82)
					{
						htmltext = "31302-01.htm";
					}
					else
					{
						htmltext = "31302-03.html";
					}
					break;
				}
				case State.STARTED:
				{
					htmltext = "31302-04.html";
					break;
				}
				case State.COMPLETED:
				{
					htmltext = getAlreadyCompletedMsg(player);
					break;
				}
			}
		}
		else if ((npc.getId() == TUNATUN) && qs.isCond(1))
		{
			htmltext = "31537-01.html";
		}
		return htmltext;
	}
}

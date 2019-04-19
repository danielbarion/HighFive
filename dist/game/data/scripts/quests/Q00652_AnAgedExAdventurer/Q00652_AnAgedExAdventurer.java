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
package quests.Q00652_AnAgedExAdventurer;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * An Aged Ex-Adventurer (652)
 * @author malyelfik
 */
public class Q00652_AnAgedExAdventurer extends Quest
{
	// NPCs
	private static final int TANTAN = 32012;
	private static final int SARA = 30180;
	// Items
	private static final int SOULSHOT_C = 1464;
	private static final int ENCHANT_ARMOR_D = 956;
	
	public Q00652_AnAgedExAdventurer()
	{
		super(652);
		addStartNpc(TANTAN);
		addTalkId(TANTAN, SARA);
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
		if (event.equals("32012-04.htm"))
		{
			if (getQuestItemsCount(player, SOULSHOT_C) < 100)
			{
				return "32012-05.htm";
			}
			
			qs.startQuest();
			takeItems(player, SOULSHOT_C, 100);
			npc.deleteMe();
			htmltext = event;
		}
		else if (event.equals("32012-03.html"))
		{
			htmltext = event;
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case TANTAN:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= 46) ? "32012-01.htm" : "32012-01a.htm";
						break;
					}
					case State.STARTED:
					{
						htmltext = "32012-02.html";
						break;
					}
				}
				break;
			}
			case SARA:
			{
				if (qs.isStarted())
				{
					if (getRandom(10) <= 4)
					{
						giveItems(player, ENCHANT_ARMOR_D, 1);
						giveAdena(player, 5026, true);
						htmltext = "30180-01.html";
					}
					else
					{
						giveAdena(player, 10000, true);
						htmltext = "30180-02.html";
					}
					qs.exitQuest(true, true);
				}
				break;
			}
		}
		return htmltext;
	}
}
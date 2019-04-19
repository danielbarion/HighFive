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
package quests.Q00452_FindingtheLostSoldiers;

import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Finding the Lost Soldiers (452)
 * @author Gigiikun
 * @version 2012-08-10
 */
public class Q00452_FindingtheLostSoldiers extends Quest
{
	private static final int JAKAN = 32773;
	private static final int TAG_ID = 15513;
	private static final int[] SOLDIER_CORPSES =
	{
		32769,
		32770,
		32771,
		32772
	};
	
	public Q00452_FindingtheLostSoldiers()
	{
		super(452);
		addStartNpc(JAKAN);
		addTalkId(JAKAN);
		addTalkId(SOLDIER_CORPSES);
		registerQuestItems(TAG_ID);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		String htmltext = event;
		
		if (npc.getId() == JAKAN)
		{
			if (event.equals("32773-3.htm"))
			{
				qs.startQuest();
			}
		}
		else if (qs.isCond(1))
		{
			if (getRandom(10) < 5)
			{
				giveItems(player, TAG_ID, 1);
			}
			else
			{
				htmltext = "corpse-3.html";
			}
			qs.setCond(2, true);
			npc.deleteMe();
		}
		else
		{
			htmltext = "corpse-3.html";
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (npc.getId() == JAKAN)
		{
			switch (qs.getState())
			{
				case State.CREATED:
				{
					htmltext = (player.getLevel() < 84) ? "32773-0.html" : "32773-1.htm";
					break;
				}
				case State.STARTED:
				{
					if (qs.isCond(1))
					{
						htmltext = "32773-4.html";
					}
					else if (qs.isCond(2))
					{
						htmltext = "32773-5.html";
						takeItems(player, TAG_ID, -1);
						giveAdena(player, 95200, true);
						addExpAndSp(player, 435024, 50366);
						qs.exitQuest(QuestType.DAILY, true);
					}
					break;
				}
				case State.COMPLETED:
				{
					if (qs.isNowAvailable())
					{
						qs.setState(State.CREATED);
						htmltext = (player.getLevel() < 84) ? "32773-0.html" : "32773-1.htm";
					}
					else
					{
						htmltext = "32773-6.html";
					}
					break;
				}
			}
		}
		else if (qs.isCond(1))
		{
			htmltext = "corpse-1.html";
		}
		return htmltext;
	}
}

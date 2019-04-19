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
package quests.Q00013_ParcelDelivery;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Parcel Delivery (13)<br>
 * Original Jython script by Emperorc.
 * @author nonom
 */
public class Q00013_ParcelDelivery extends Quest
{
	// NPCs
	private static final int FUNDIN = 31274;
	private static final int VULCAN = 31539;
	// Item
	private static final int PACKAGE = 7263;
	
	public Q00013_ParcelDelivery()
	{
		super(13);
		addStartNpc(FUNDIN);
		addTalkId(FUNDIN, VULCAN);
		registerQuestItems(PACKAGE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "31274-02.html":
			{
				qs.startQuest();
				giveItems(player, PACKAGE, 1);
				break;
			}
			case "31539-01.html":
			{
				if (qs.isCond(1) && hasQuestItems(player, PACKAGE))
				{
					giveAdena(player, 157834, true);
					addExpAndSp(player, 589092, 58794);
					qs.exitQuest(false, true);
				}
				else
				{
					htmltext = "31539-02.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		final int npcId = npc.getId();
		
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				if (npcId == FUNDIN)
				{
					htmltext = (player.getLevel() >= 74) ? "31274-00.htm" : "31274-01.html";
				}
				break;
			}
			case State.STARTED:
			{
				if (qs.isCond(1))
				{
					if (npcId == FUNDIN)
					{
						htmltext = "31274-02.html";
					}
					else if (npcId == VULCAN)
					{
						htmltext = "31539-00.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

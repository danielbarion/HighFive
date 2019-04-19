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
package quests.Q10288_SecretMission;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Secret Mission (10288)
 * @author Gnacik
 */
public class Q10288_SecretMission extends Quest
{
	// NPCs
	private static final int DOMINIC = 31350;
	private static final int AQUILANI = 32780;
	private static final int GREYMORE = 32757;
	// Item
	private static final int LETTER = 15529;
	// Location
	private static final Location TELEPORT = new Location(118833, -80589, -2688);
	
	public Q10288_SecretMission()
	{
		super(10288);
		addStartNpc(AQUILANI, DOMINIC);
		addFirstTalkId(AQUILANI);
		addTalkId(DOMINIC, GREYMORE, AQUILANI);
		registerQuestItems(LETTER);
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
		
		switch (event)
		{
			case "31350-03.html":
			{
				if (player.getLevel() < 82)
				{
					htmltext = "31350-02b.html";
				}
				break;
			}
			case "31350-05.htm":
			{
				qs.startQuest();
				giveItems(player, LETTER, 1);
				break;
			}
			case "32780-03.html":
			{
				if (qs.isCond(1) && hasQuestItems(player, LETTER))
				{
					qs.setCond(2, true);
				}
				break;
			}
			case "32757-03.html":
			{
				if (qs.isCond(2) && hasQuestItems(player, LETTER))
				{
					giveAdena(player, 106583, true);
					addExpAndSp(player, 417788, 46320);
					qs.exitQuest(false, true);
				}
				break;
			}
			case "teleport":
			{
				if ((npc.getId() == AQUILANI) && qs.isCompleted())
				{
					player.teleToLocation(TELEPORT);
					return null;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		// dialog only changes when you talk to Aquilani after quest completion
		if ((qs != null) && qs.isCompleted())
		{
			return "32780-05.html";
		}
		return "32780.html";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case DOMINIC:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = "31350-01.htm";
						break;
					}
					case State.STARTED:
					{
						if (qs.isCond(1))
						{
							htmltext = "31350-06.html";
						}
						break;
					}
					case State.COMPLETED:
					{
						htmltext = "31350-07.html";
						break;
					}
				}
				break;
			}
			case AQUILANI:
			{
				if (qs.isStarted())
				{
					if (qs.isCond(1) && hasQuestItems(player, LETTER))
					{
						htmltext = "32780-01.html";
					}
					else if (qs.isCond(2))
					{
						htmltext = "32780-04.html";
					}
				}
				break;
			}
			case GREYMORE:
			{
				if (qs.isStarted() && qs.isCond(2) && hasQuestItems(player, LETTER))
				{
					return "32757-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

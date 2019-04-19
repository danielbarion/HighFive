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
package quests.Q00109_InSearchOfTheNest;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * In Search of the Nest (109)
 * @author Adry_85
 */
public class Q00109_InSearchOfTheNest extends Quest
{
	// NPCs
	private static final int PIERCE = 31553;
	private static final int SCOUTS_CORPSE = 32015;
	private static final int KAHMAN = 31554;
	// Items
	private static final int SCOUTS_NOTE = 14858;
	
	public Q00109_InSearchOfTheNest()
	{
		super(109);
		addStartNpc(PIERCE);
		addTalkId(PIERCE, SCOUTS_CORPSE, KAHMAN);
		registerQuestItems(SCOUTS_NOTE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "31553-0.htm":
			{
				qs.startQuest();
				break;
			}
			case "32015-2.html":
			{
				giveItems(player, SCOUTS_NOTE, 1);
				qs.setCond(2, true);
				break;
			}
			case "31553-3.html":
			{
				takeItems(player, SCOUTS_NOTE, -1);
				qs.setCond(3, true);
				break;
			}
			case "31554-2.html":
			{
				giveAdena(player, 161500, true);
				addExpAndSp(player, 701500, 50000);
				qs.exitQuest(false, true);
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case PIERCE:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() < 81) ? "31553-0a.htm" : "31553-0b.htm";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "31553-1.html";
								break;
							}
							case 2:
							{
								htmltext = "31553-2.html";
								break;
							}
							case 3:
							{
								htmltext = "31553-3a.html";
								break;
							}
						}
						break;
					}
					case State.COMPLETED:
					{
						htmltext = getAlreadyCompletedMsg(player);
						break;
					}
				}
				break;
			}
			case SCOUTS_CORPSE:
			{
				if (qs.isStarted())
				{
					if (qs.isCond(1))
					{
						htmltext = "32015-1.html";
					}
					else if (qs.isCond(2))
					{
						htmltext = "32015-3.html";
					}
				}
				break;
			}
			case KAHMAN:
			{
				if (qs.isStarted() && qs.isCond(3))
				{
					htmltext = "31554-1.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

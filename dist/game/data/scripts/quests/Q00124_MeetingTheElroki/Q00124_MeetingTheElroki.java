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
package quests.Q00124_MeetingTheElroki;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Meeting the Elroki (124)
 * @author Adry_85
 */
public class Q00124_MeetingTheElroki extends Quest
{
	// NPCs
	private static final int MARQUEZ = 32113;
	private static final int MUSHIKA = 32114;
	private static final int ASAMAH = 32115;
	private static final int KARAKAWEI = 32117;
	private static final int MANTARASA = 32118;
	// Item
	private static final int MANTARASA_EGG = 8778;
	
	public Q00124_MeetingTheElroki()
	{
		super(124);
		addStartNpc(MARQUEZ);
		addTalkId(MARQUEZ, MUSHIKA, ASAMAH, KARAKAWEI, MANTARASA);
		registerQuestItems(MANTARASA_EGG);
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
			case "32113-03.html":
			{
				qs.startQuest();
				break;
			}
			case "32113-04.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
				}
				break;
			}
			case "32114-04.html":
			{
				if (qs.isCond(2))
				{
					qs.setCond(3, true);
				}
				break;
			}
			case "32115-06.html":
			{
				if (qs.isCond(3))
				{
					qs.setCond(4, true);
				}
				break;
			}
			case "32117-05.html":
			{
				if (qs.isCond(4))
				{
					qs.setCond(5, true);
				}
				break;
			}
			case "32118-04.html":
			{
				if (qs.isCond(5))
				{
					giveItems(player, MANTARASA_EGG, 1);
					qs.setCond(6, true);
				}
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
			case MARQUEZ:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() < 75) ? "32113-01a.htm" : "32113-01.htm";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "32113-05.html";
								break;
							}
							case 2:
							{
								htmltext = "32113-06.html";
								break;
							}
							case 3:
							case 4:
							case 5:
							{
								htmltext = "32113-07.html";
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
			case MUSHIKA:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "32114-01.html";
							break;
						}
						case 2:
						{
							htmltext = "32114-02.html";
							break;
						}
						default:
						{
							htmltext = "32114-03.html";
							break;
						}
					}
					break;
				}
				break;
			}
			case ASAMAH:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						{
							htmltext = "32115-01.html";
							break;
						}
						case 3:
						{
							htmltext = "32115-02.html";
							break;
						}
						case 4:
						{
							htmltext = "32115-07.html";
							break;
						}
						case 5:
						{
							htmltext = "32115-08.html";
							break;
						}
						case 6:
						{
							if (hasQuestItems(player, MANTARASA_EGG))
							{
								htmltext = "32115-09.html";
								giveAdena(player, 100013, true);
								addExpAndSp(player, 301922, 30294);
								qs.exitQuest(false, true);
							}
							break;
						}
					}
				}
				break;
			}
			case KARAKAWEI:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						{
							htmltext = "32117-01.html";
							break;
						}
						case 4:
						{
							htmltext = "32117-02.html";
							break;
						}
						case 5:
						{
							htmltext = "32117-07.html";
							break;
						}
						case 6:
						{
							htmltext = "32117-06.html";
							break;
						}
					}
				}
				break;
			}
			case MANTARASA:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						case 4:
						{
							htmltext = "32118-01.html";
							break;
						}
						case 5:
						{
							htmltext = "32118-03.html";
							break;
						}
						case 6:
						{
							htmltext = "32118-02.html";
							break;
						}
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

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
package quests.Q00131_BirdInACage;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Bird in a Cage (131)
 * @author Zoey76
 */
public class Q00131_BirdInACage extends Quest
{
	// NPCs
	private static final int KANIS = 32264;
	private static final int PARME = 32271;
	// Items
	private static final int ECHO_CRYSTAL_OF_FREE_THOUGHT = 9783;
	private static final int PARMES_LETTER = 9784;
	private static final int FIRE_STONE = 9546;
	// Locations
	private static final Location INSTANCE_EXIT = new Location(143281, 148843, -12004);
	// Misc
	private static final int MIN_LEVEL = 78;
	
	public Q00131_BirdInACage()
	{
		super(131);
		addStartNpc(KANIS);
		addTalkId(KANIS, PARME);
		registerQuestItems(ECHO_CRYSTAL_OF_FREE_THOUGHT, PARMES_LETTER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "32264-04.html":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					st.startQuest();
					htmltext = event;
				}
				break;
			}
			case "32264-06.html":
			{
				if (st.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32264-07.html":
			{
				if (st.isCond(1))
				{
					st.setCond(2);
					htmltext = event;
				}
				break;
			}
			case "32264-09.html":
			case "32264-10.html":
			case "32264-11.html":
			{
				if (st.isCond(2))
				{
					htmltext = event;
				}
				break;
			}
			case "32264-12.html":
			{
				if (st.isCond(2))
				{
					giveItems(player, ECHO_CRYSTAL_OF_FREE_THOUGHT, 1);
					st.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32264-14.html":
			case "32264-15.html":
			{
				if (st.isCond(3))
				{
					htmltext = event;
				}
				break;
			}
			case "32264-17.html":
			{
				if (st.isCond(4) && hasQuestItems(player, PARMES_LETTER))
				{
					takeItems(player, PARMES_LETTER, -1);
					st.setCond(5);
					htmltext = event;
				}
				break;
			}
			case "32264-19.html":
			{
				if (st.isCond(5) && hasQuestItems(player, ECHO_CRYSTAL_OF_FREE_THOUGHT))
				{
					addExpAndSp(player, 250677, 25019);
					giveItems(player, FIRE_STONE + getRandom(4), 4);
					st.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "32271-03.html":
			{
				if (st.isCond(3))
				{
					htmltext = event;
				}
				break;
			}
			case "32271-04.html":
			{
				if (st.isCond(3))
				{
					giveItems(player, PARMES_LETTER, 1);
					st.setCond(4, true);
					player.setInstanceId(0);
					player.teleToLocation(INSTANCE_EXIT, true);
					htmltext = event;
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
		
		switch (st.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == KANIS)
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "32264-01.htm" : "32264-02.html";
				}
				break;
			}
			case State.STARTED:
			{
				if (npc.getId() == KANIS)
				{
					switch (st.getCond())
					{
						case 1:
						{
							htmltext = "32264-05.html";
							break;
						}
						case 2:
						{
							htmltext = "32264-08.html";
							break;
						}
						case 3:
						{
							htmltext = "32264-13.html";
							break;
						}
						case 4:
						{
							htmltext = "32264-16.html";
							break;
						}
						case 5:
						{
							htmltext = "32264-18.html";
							break;
						}
					}
				}
				else if (npc.getId() == PARME)
				{
					if (st.getCond() < 3)
					{
						htmltext = "32271-01.html";
					}
					else if (st.isCond(3))
					{
						htmltext = "32271-02.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

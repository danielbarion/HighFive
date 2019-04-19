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
package quests.Q00033_MakeAPairOfDressShoes;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Make a Pair of Dress Shoes (33)
 * @author malyelfik
 */
public class Q00033_MakeAPairOfDressShoes extends Quest
{
	// NPCs
	private static final int IAN = 30164;
	private static final int WOODLEY = 30838;
	private static final int LEIKAR = 31520;
	// Items
	private static final int LEATHER = 1882;
	private static final int THREAD = 1868;
	private static final int DRESS_SHOES_BOX = 7113;
	// Misc
	private static final int MIN_LEVEL = 60;
	private static final int LEATHER_COUNT = 200;
	private static final int THREAD_COUNT = 600;
	private static final int ADENA_COUNT = 500000;
	private static final int ADENA_COUNT2 = 200000;
	private static final int ADENA_COUNT3 = 300000;
	
	public Q00033_MakeAPairOfDressShoes()
	{
		super(33);
		addStartNpc(WOODLEY);
		addTalkId(WOODLEY, IAN, LEIKAR);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = event;
		switch (event)
		{
			case "30838-03.htm":
			{
				qs.startQuest();
				break;
			}
			case "30838-06.html":
			{
				qs.setCond(3, true);
				break;
			}
			case "30838-09.html":
			{
				if ((getQuestItemsCount(player, LEATHER) >= LEATHER_COUNT) && (getQuestItemsCount(player, THREAD) >= THREAD_COUNT) && (player.getAdena() >= ADENA_COUNT2))
				{
					takeItems(player, LEATHER, LEATHER_COUNT);
					takeItems(player, THREAD, LEATHER_COUNT);
					takeItems(player, Inventory.ADENA_ID, ADENA_COUNT2);
					qs.setCond(4, true);
				}
				else
				{
					htmltext = "30838-10.html";
				}
				break;
			}
			case "30838-13.html":
			{
				giveItems(player, DRESS_SHOES_BOX, 1);
				qs.exitQuest(false, true);
				break;
			}
			case "31520-02.html":
			{
				qs.setCond(2, true);
				break;
			}
			case "30164-02.html":
			{
				if (player.getAdena() < ADENA_COUNT3)
				{
					return "30164-03.html";
				}
				takeItems(player, Inventory.ADENA_ID, ADENA_COUNT3);
				qs.setCond(5, true);
				break;
			}
			default:
			{
				htmltext = null;
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
		
		switch (npc.getId())
		{
			case WOODLEY:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? "30838-01.htm" : "30838-02.html";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "30838-04.html";
								break;
							}
							case 2:
							{
								htmltext = "30838-05.html";
								break;
							}
							case 3:
							{
								htmltext = ((getQuestItemsCount(player, LEATHER) >= LEATHER_COUNT) && (getQuestItemsCount(player, THREAD) >= THREAD_COUNT) && (player.getAdena() >= ADENA_COUNT)) ? "30838-07.html" : "30838-08.html";
								break;
							}
							case 4:
							{
								htmltext = "30838-11.html";
								break;
							}
							case 5:
							{
								htmltext = "30838-12.html";
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
			case LEIKAR:
			{
				if (qs.isStarted())
				{
					if (qs.isCond(1))
					{
						htmltext = "31520-01.html";
					}
					else if (qs.isCond(2))
					{
						htmltext = "31520-03.html";
					}
				}
				break;
			}
			case IAN:
			{
				if (qs.isStarted())
				{
					if (qs.isCond(4))
					{
						htmltext = "30164-01.html";
					}
					else if (qs.isCond(5))
					{
						htmltext = "30164-04.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
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
package quests.Q00037_MakeFormalWear;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Make Formal Wear (37)
 * @author malyelfik
 */
public class Q00037_MakeFormalWear extends Quest
{
	// NPCs
	private static final int ALEXIS = 30842;
	private static final int LEIKAR = 31520;
	private static final int JEREMY = 31521;
	private static final int MIST = 31627;
	// Items
	private static final int FORMAL_WEAR = 6408;
	private static final int MYSTERIOUS_CLOTH = 7076;
	private static final int JEWEL_BOX = 7077;
	private static final int SEWING_KIT = 7078;
	private static final int DRESS_SHOES_BOX = 7113;
	private static final int BOX_OF_COOKIES = 7159;
	private static final int ICE_WINE = 7160;
	private static final int SIGNET_RING = 7164;
	// Misc
	private static final int MIN_LEVEL = 60;
	
	public Q00037_MakeFormalWear()
	{
		super(37);
		addStartNpc(ALEXIS);
		addTalkId(ALEXIS, JEREMY, LEIKAR, MIST);
		registerQuestItems(SIGNET_RING, ICE_WINE, BOX_OF_COOKIES);
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
			case "30842-03.htm":
			{
				qs.startQuest();
				break;
			}
			case "31520-02.html":
			{
				giveItems(player, SIGNET_RING, 1);
				qs.setCond(2, true);
				break;
			}
			case "31521-02.html":
			{
				giveItems(player, ICE_WINE, 1);
				qs.setCond(3, true);
				break;
			}
			case "31627-02.html":
			{
				if (!hasQuestItems(player, ICE_WINE))
				{
					return getNoQuestMsg(player);
				}
				takeItems(player, ICE_WINE, 1);
				qs.setCond(4, true);
				break;
			}
			case "31521-05.html":
			{
				giveItems(player, BOX_OF_COOKIES, 1);
				qs.setCond(5, true);
				break;
			}
			case "31520-05.html":
			{
				if (!hasQuestItems(player, BOX_OF_COOKIES))
				{
					return getNoQuestMsg(player);
				}
				takeItems(player, BOX_OF_COOKIES, 1);
				qs.setCond(6, true);
				break;
			}
			case "31520-08.html":
			{
				if (!hasQuestItems(player, SEWING_KIT, JEWEL_BOX, MYSTERIOUS_CLOTH))
				{
					return "31520-09.html";
				}
				takeItems(player, SEWING_KIT, 1);
				takeItems(player, JEWEL_BOX, 1);
				takeItems(player, MYSTERIOUS_CLOTH, 1);
				qs.setCond(7, true);
				break;
			}
			case "31520-12.html":
			{
				if (!hasQuestItems(player, DRESS_SHOES_BOX))
				{
					return "31520-13.html";
				}
				takeItems(player, DRESS_SHOES_BOX, 1);
				giveItems(player, FORMAL_WEAR, 1);
				qs.exitQuest(false, true);
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
			case ALEXIS:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? "30842-01.htm" : "30842-02.html";
						break;
					}
					case State.STARTED:
					{
						if (qs.isCond(1))
						{
							htmltext = "30842-04.html";
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
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "31520-01.html";
							break;
						}
						case 2:
						{
							htmltext = "31520-03.html";
							break;
						}
						case 5:
						{
							htmltext = "31520-04.html";
							break;
						}
						case 6:
						{
							htmltext = hasQuestItems(player, SEWING_KIT, JEWEL_BOX, MYSTERIOUS_CLOTH) ? "31520-06.html" : "31520-07.html";
							break;
						}
						case 7:
						{
							htmltext = hasQuestItems(player, DRESS_SHOES_BOX) ? "31520-10.html" : "31520-11.html";
							break;
						}
					}
				}
				break;
			}
			case JEREMY:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 2:
						{
							htmltext = "31521-01.html";
							break;
						}
						case 3:
						{
							htmltext = "31521-03.html";
							break;
						}
						case 4:
						{
							htmltext = "31521-04.html";
							break;
						}
						case 5:
						{
							htmltext = "31521-06.html";
							break;
						}
					}
				}
				break;
			}
			case MIST:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 3:
						{
							htmltext = "31627-01.html";
							break;
						}
						case 4:
						{
							htmltext = "31627-03.html";
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
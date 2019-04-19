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
package quests.Q00174_SupplyCheck;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;

/**
 * Supply Check (174)
 * @author malyelfik
 */
public class Q00174_SupplyCheck extends Quest
{
	// NPCs
	private static final int NIKA = 32167;
	private static final int BENIS = 32170;
	private static final int MARCELA = 32173;
	
	// Items
	private static final int WAREHOUSE_MANIFEST = 9792;
	private static final int GROCERY_STORE_MANIFEST = 9793;
	private static final int[] REWARD =
	{
		23, // Wooden Breastplate
		43, // Wooden Helmet
		49, // Gloves
		2386, // Wooden Gaiters
		37, // Leather Shoes
	};
	
	// Misc
	private static final int MIN_LEVEL = 2;
	
	public Q00174_SupplyCheck()
	{
		super(174);
		addStartNpc(MARCELA);
		addTalkId(MARCELA, BENIS, NIKA);
		
		registerQuestItems(WAREHOUSE_MANIFEST, GROCERY_STORE_MANIFEST);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		if (event.equalsIgnoreCase("32173-03.htm"))
		{
			st.startQuest();
			return event;
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case MARCELA:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? "32173-01.htm" : "32173-02.htm";
						break;
					}
					case State.STARTED:
					{
						switch (st.getCond())
						{
							case 1:
							{
								htmltext = "32173-04.html";
								break;
							}
							case 2:
							{
								st.setCond(3, true);
								takeItems(player, WAREHOUSE_MANIFEST, -1);
								htmltext = "32173-05.html";
								break;
							}
							case 3:
							{
								htmltext = "32173-06.html";
								break;
							}
							case 4:
							{
								for (int itemId : REWARD)
								{
									giveItems(player, itemId, 1);
								}
								giveAdena(player, 2466, true);
								addExpAndSp(player, 5672, 446);
								st.exitQuest(false, true);
								// Newbie Guide
								showOnScreenMsg(player, NpcStringId.DELIVERY_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE, 2, 5000);
								htmltext = "32173-07.html";
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
			case BENIS:
			{
				if (st.isStarted())
				{
					switch (st.getCond())
					{
						case 1:
						{
							st.setCond(2, true);
							giveItems(player, WAREHOUSE_MANIFEST, 1);
							htmltext = "32170-01.html";
							break;
						}
						case 2:
						{
							htmltext = "32170-02.html";
							break;
						}
						default:
						{
							htmltext = "32170-03.html";
							break;
						}
					}
				}
				break;
			}
			case NIKA:
			{
				if (st.isStarted())
				{
					switch (st.getCond())
					{
						case 1:
						case 2:
						{
							htmltext = "32167-01.html";
							break;
						}
						case 3:
						{
							st.setCond(4, true);
							giveItems(player, GROCERY_STORE_MANIFEST, 1);
							htmltext = "32167-02.html";
							break;
						}
						case 4:
						{
							htmltext = "32167-03.html";
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
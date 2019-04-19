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
package quests.Q00364_JovialAccordion;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Jovial Accordion (364)
 * @author Adry_85
 */
public final class Q00364_JovialAccordion extends Quest
{
	// NPCs
	private static final int SABRIN = 30060;
	private static final int XABER = 30075;
	private static final int SWAN = 30957;
	private static final int BARBADO = 30959;
	private static final int BEER_CHEST = 30960;
	private static final int CLOTH_CHEST = 30961;
	// Items
	private static final int STOLEN_BLACK_BEER = 4321;
	private static final int STOLEN_EVENT_CLOTHES = 4322;
	private static final int CLOTHES_CHEST_KEY = 4323;
	private static final int BEER_CHEST_KEY = 4324;
	private static final int THEME_OF_THE_FEAST = 4421;
	// Misc
	private static final int MIN_LEVEL = 15;
	
	public Q00364_JovialAccordion()
	{
		super(364);
		addStartNpc(BARBADO);
		addTalkId(BARBADO, BEER_CHEST, CLOTH_CHEST, SABRIN, XABER, SWAN);
		registerQuestItems(STOLEN_BLACK_BEER, STOLEN_EVENT_CLOTHES, CLOTHES_CHEST_KEY, BEER_CHEST_KEY);
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
			case "START":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					st.startQuest();
					st.setMemoState(1);
					htmltext = "30959-02.htm";
				}
				else
				{
					htmltext = "30959-03.htm";
				}
				break;
			}
			case "OPEN_CHEST":
			{
				if (hasQuestItems(player, BEER_CHEST_KEY))
				{
					if (getRandomBoolean())
					{
						giveItems(player, STOLEN_BLACK_BEER, 1);
						htmltext = "30960-02.html";
					}
					else
					{
						htmltext = "30960-03.html";
					}
					takeItems(player, BEER_CHEST_KEY, -1);
				}
				else
				{
					htmltext = "30960-04.html";
				}
				break;
			}
			case "OPEN_CLOTH_CHEST":
			{
				if (hasQuestItems(player, CLOTHES_CHEST_KEY))
				{
					if (getRandomBoolean())
					{
						giveItems(player, STOLEN_EVENT_CLOTHES, 1);
						htmltext = "30961-02.html";
					}
					else
					{
						htmltext = "30961-03.html";
					}
					takeItems(player, CLOTHES_CHEST_KEY, -1);
				}
				else
				{
					htmltext = "30961-04.html";
				}
				break;
			}
			case "30957-02.html":
			{
				giveItems(player, CLOTHES_CHEST_KEY, 1);
				giveItems(player, BEER_CHEST_KEY, 1);
				st.setMemoState(2);
				st.setCond(2, true);
				htmltext = event;
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
			case State.CREATED:
			{
				if (npc.getId() == BARBADO)
				{
					htmltext = "30959-01.htm";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case BARBADO:
					{
						switch (st.getMemoState())
						{
							case 1:
							case 2:
							case 3:
							case 4:
							{
								htmltext = "30959-04.html";
								break;
							}
							case 5:
							{
								rewardItems(player, THEME_OF_THE_FEAST, 1);
								st.exitQuest(true, true);
								htmltext = "30959-05.html";
								break;
							}
						}
						break;
					}
					case BEER_CHEST:
					{
						htmltext = "30960-01.html";
						break;
					}
					case CLOTH_CHEST:
					{
						htmltext = "30961-01.html";
						break;
					}
					case SABRIN:
					{
						if (hasQuestItems(player, STOLEN_BLACK_BEER))
						{
							takeItems(player, STOLEN_BLACK_BEER, -1);
							htmltext = "30060-01.html";
							if (st.isMemoState(2))
							{
								st.setMemoState(3);
							}
							else if (st.isMemoState(3))
							{
								st.setMemoState(4);
							}
						}
						else
						{
							htmltext = "30060-02.html";
						}
						break;
					}
					case XABER:
					{
						if (hasQuestItems(player, STOLEN_EVENT_CLOTHES))
						{
							takeItems(player, STOLEN_EVENT_CLOTHES, -1);
							htmltext = "30075-01.html";
							if (st.isMemoState(2))
							{
								st.setMemoState(3);
							}
							else if (st.isMemoState(3))
							{
								st.setMemoState(4);
							}
						}
						else
						{
							htmltext = "30075-02.html";
						}
						break;
					}
					case SWAN:
					{
						switch (st.getMemoState())
						{
							case 1:
							{
								htmltext = "30957-01.html";
								break;
							}
							case 2:
							case 3:
							{
								if (hasAtLeastOneQuestItem(player, BEER_CHEST_KEY, CLOTHES_CHEST_KEY, STOLEN_BLACK_BEER, STOLEN_EVENT_CLOTHES))
								{
									htmltext = "30957-03.html";
								}
								else if (!hasQuestItems(player, BEER_CHEST_KEY, CLOTHES_CHEST_KEY, STOLEN_BLACK_BEER, STOLEN_EVENT_CLOTHES))
								{
									if (st.isMemoState(2))
									{
										playSound(player, QuestSound.ITEMSOUND_QUEST_GIVEUP);
										st.exitQuest(true, true);
										htmltext = "30957-06.html";
									}
									else
									{
										st.setMemoState(5);
										st.setCond(3, true);
										htmltext = "30957-04.html";
									}
								}
								break;
							}
							case 4:
							{
								if (!hasQuestItems(player, BEER_CHEST_KEY, CLOTHES_CHEST_KEY, STOLEN_BLACK_BEER, STOLEN_EVENT_CLOTHES))
								{
									st.setMemoState(5);
									st.setCond(3, true);
									giveAdena(player, 100, true);
									htmltext = "30957-05.html";
								}
								break;
							}
							case 5:
							{
								htmltext = "30957-07.html";
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

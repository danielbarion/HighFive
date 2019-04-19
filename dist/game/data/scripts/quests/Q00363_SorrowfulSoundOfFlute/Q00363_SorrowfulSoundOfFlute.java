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
package quests.Q00363_SorrowfulSoundOfFlute;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Sorrowful Sound of Flute (363)
 * @author Adry_85
 */
public final class Q00363_SorrowfulSoundOfFlute extends Quest
{
	// NPCs
	private static final int ALDO = 30057;
	private static final int HOLVAS = 30058;
	private static final int POITAN = 30458;
	private static final int RANSPO = 30594;
	private static final int OPIX = 30595;
	private static final int NANARIN = 30956;
	private static final int BARBADO = 30959;
	// Items
	private static final int EVENT_CLOTHES = 4318;
	private static final int NANARINS_FLUTE = 4319;
	private static final int SABRINS_BLACK_BEER = 4320;
	private static final int THEME_OF_SOLITUDE = 4420;
	// Misc
	private static final int MIN_LEVEL = 15;
	
	public Q00363_SorrowfulSoundOfFlute()
	{
		super(363);
		addStartNpc(NANARIN);
		addTalkId(NANARIN, POITAN, RANSPO, ALDO, HOLVAS, OPIX, BARBADO);
		registerQuestItems(EVENT_CLOTHES, NANARINS_FLUTE, SABRINS_BLACK_BEER);
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
					st.setMemoState(2);
					htmltext = "30956-02.htm";
				}
				else
				{
					htmltext = "30956-03.htm";
				}
				break;
			}
			case "30956-05.html":
			{
				giveItems(player, EVENT_CLOTHES, 1);
				st.setMemoState(4);
				st.setCond(3, true);
				htmltext = event;
				break;
			}
			case "30956-06.html":
			{
				giveItems(player, NANARINS_FLUTE, 1);
				st.setMemoState(4);
				st.setCond(3, true);
				htmltext = event;
				break;
			}
			case "30956-07.html":
			{
				giveItems(player, SABRINS_BLACK_BEER, 1);
				st.setMemoState(4);
				st.setCond(3, true);
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
				if (npc.getId() == NANARIN)
				{
					htmltext = "30956-01.htm";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case NANARIN:
					{
						switch (st.getMemoState())
						{
							case 2:
							{
								htmltext = "30956-04.html";
								break;
							}
							case 4:
							{
								htmltext = "30956-08.html";
								break;
							}
							case 5:
							{
								rewardItems(player, THEME_OF_SOLITUDE, 1);
								st.exitQuest(true, true);
								htmltext = "30956-09.html";
								break;
							}
							case 6:
							{
								st.exitQuest(true, false);
								playSound(player, QuestSound.ITEMSOUND_QUEST_GIVEUP);
								htmltext = "30956-10.html";
								break;
							}
						}
						break;
					}
					case POITAN:
					{
						if (st.isMemoState(2) && ((st.getInt("ex") % 100) < 10))
						{
							final int ex = st.getInt("ex");
							st.set("ex", ex + 11);
							switch (getRandom(3))
							{
								case 0:
								{
									htmltext = "30458-01.html";
									break;
								}
								case 1:
								{
									htmltext = "30458-02.html";
									break;
								}
								case 2:
								{
									htmltext = "30458-03.html";
									break;
								}
							}
							st.setCond(2, true);
						}
						else if ((st.getMemoState() >= 2) && ((st.getInt("ex") % 100) >= 10))
						{
							htmltext = "30458-04.html";
						}
						break;
					}
					case RANSPO:
					{
						if (st.isMemoState(2) && ((st.getInt("ex") % 10000) < 1000))
						{
							final int ex = st.getInt("ex");
							st.set("ex", ex + 1001);
							switch (getRandom(3))
							{
								case 0:
								{
									htmltext = "30594-01.html";
									break;
								}
								case 1:
								{
									htmltext = "30594-02.html";
									break;
								}
								case 2:
								{
									htmltext = "30594-03.html";
									break;
								}
							}
							st.setCond(2, true);
						}
						else if ((st.getMemoState() >= 2) && ((st.getInt("ex") % 10000) >= 1000))
						{
							htmltext = "30594-04.html";
						}
						break;
					}
					case ALDO:
					{
						if (st.isMemoState(2) && ((st.getInt("ex") % 100000) < 10000))
						{
							final int ex = st.getInt("ex");
							st.set("ex", ex + 10001);
							switch (getRandom(3))
							{
								case 0:
								{
									htmltext = "30057-01.html";
									break;
								}
								case 1:
								{
									htmltext = "30057-02.html";
									break;
								}
								case 2:
								{
									htmltext = "30057-03.html";
									break;
								}
							}
							st.setCond(2, true);
						}
						else if ((st.getMemoState() >= 2) && ((st.getInt("ex") % 100000) >= 10000))
						{
							htmltext = "30057-04.html";
						}
						break;
					}
					case HOLVAS:
					{
						if (st.isMemoState(2) && ((st.getInt("ex") % 1000) < 100))
						{
							final int ex = st.getInt("ex");
							st.set("ex", ex + 101);
							switch (getRandom(3))
							{
								case 0:
								{
									htmltext = "30058-01.html";
									break;
								}
								case 1:
								{
									htmltext = "30058-02.html";
									break;
								}
								case 2:
								{
									htmltext = "30058-03.html";
									break;
								}
							}
							st.setCond(2, true);
						}
						else if ((st.getMemoState() >= 2) && ((st.getInt("ex") % 1000) >= 100))
						{
							htmltext = "30058-04.html";
						}
						break;
					}
					case OPIX:
					{
						if (st.isMemoState(2) && (st.getInt("ex") < 100000))
						{
							final int ex = st.getInt("ex");
							st.set("ex", ex + 100001);
							switch (getRandom(3))
							{
								case 0:
								{
									htmltext = "30595-01.html";
									break;
								}
								case 1:
								{
									htmltext = "30595-02.html";
									break;
								}
								case 2:
								{
									htmltext = "30595-03.html";
									break;
								}
							}
							st.setCond(2, true);
						}
						else if ((st.getMemoState() >= 2) && (st.getInt("ex") >= 100000))
						{
							htmltext = "30595-04.html";
						}
						break;
					}
					case BARBADO:
					{
						if (st.isMemoState(4))
						{
							final int ex = (st.getInt("ex") % 10) * 20;
							if (getRandom(100) < ex)
							{
								if (hasQuestItems(player, EVENT_CLOTHES))
								{
									takeItems(player, EVENT_CLOTHES, -1);
								}
								else if (hasQuestItems(player, NANARINS_FLUTE))
								{
									takeItems(player, NANARINS_FLUTE, -1);
								}
								else if (hasQuestItems(player, SABRINS_BLACK_BEER))
								{
									takeItems(player, SABRINS_BLACK_BEER, -1);
								}
								st.setMemoState(5);
								st.setCond(4, true);
								htmltext = "30959-01.html";
							}
							else
							{
								st.setMemoState(6);
								st.setCond(4, true);
								htmltext = "30959-02.html";
							}
						}
						else if (st.getMemoState() >= 5)
						{
							htmltext = "30959-03.html";
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

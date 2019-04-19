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
package quests.Q00192_SevenSignsSeriesOfDoubt;

import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Seven Signs, Series of Doubt (192)
 * @author Adry_85
 */
public final class Q00192_SevenSignsSeriesOfDoubt extends Quest
{
	// NPCs
	private static final int HOLLINT = 30191;
	private static final int HECTOR = 30197;
	private static final int STAN = 30200;
	private static final int CROOP = 30676;
	private static final int UNIDENTIFIED_BODY = 32568;
	// Items
	private static final int CROOPS_INTRODUCTION = 13813;
	private static final int JACOBS_NECKLACE = 13814;
	private static final int CROOPS_LETTER = 13815;
	// Misc
	private static final int MIN_LEVEL = 79;
	
	public Q00192_SevenSignsSeriesOfDoubt()
	{
		super(192);
		addStartNpc(CROOP, UNIDENTIFIED_BODY);
		addTalkId(CROOP, STAN, UNIDENTIFIED_BODY, HECTOR, HOLLINT);
		registerQuestItems(CROOPS_INTRODUCTION, JACOBS_NECKLACE, CROOPS_LETTER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "30676-02.htm":
			{
				htmltext = event;
				break;
			}
			case "30676-03.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "video":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					playMovie(player, Movie.SSQ_SUSPICIOUS_DEATHS);
					startQuestTimer("back", 32000, npc, player);
					return "";
				}
				break;
			}
			case "back":
			{
				player.teleToLocation(81654, 54851, -1513);
				return "";
			}
			case "30676-10.html":
			case "30676-11.html":
			case "30676-12.html":
			case "30676-13.html":
			{
				if (qs.isCond(6) && hasQuestItems(player, JACOBS_NECKLACE))
				{
					htmltext = event;
				}
				break;
			}
			case "30676-14.html":
			{
				if (qs.isCond(6) && hasQuestItems(player, JACOBS_NECKLACE))
				{
					giveItems(player, CROOPS_LETTER, 1);
					takeItems(player, JACOBS_NECKLACE, -1);
					qs.setCond(7, true);
					htmltext = event;
				}
				break;
			}
			case "30200-02.html":
			case "30200-03.html":
			{
				if (qs.isCond(4))
				{
					htmltext = event;
				}
				break;
			}
			case "30200-04.html":
			{
				if (qs.isCond(4))
				{
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "32568-02.html":
			{
				if (qs.isCond(5))
				{
					giveItems(player, JACOBS_NECKLACE, 1);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "30197-02.html":
			{
				if (qs.isCond(3) && hasQuestItems(player, CROOPS_INTRODUCTION))
				{
					htmltext = event;
				}
				break;
			}
			case "30197-03.html":
			{
				if (qs.isCond(3) && hasQuestItems(player, CROOPS_INTRODUCTION))
				{
					takeItems(player, CROOPS_INTRODUCTION, -1);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "30191-02.html":
			{
				if (qs.isCond(7) && hasQuestItems(player, CROOPS_LETTER))
				{
					htmltext = event;
				}
				break;
			}
			case "reward":
			{
				if (qs.isCond(7) && hasQuestItems(player, CROOPS_LETTER))
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						addExpAndSp(player, 52518015, 5817677);
						qs.exitQuest(false, true);
						htmltext = "30191-03.html";
					}
					else
					{
						htmltext = "level_check.html";
					}
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
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				if (npc.getId() == CROOP)
				{
					htmltext = "30676-05.html";
				}
				else if (npc.getId() == UNIDENTIFIED_BODY)
				{
					htmltext = "32568-04.html";
				}
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == CROOP)
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "30676-01.htm" : "30676-04.html";
				}
				else if (npc.getId() == UNIDENTIFIED_BODY)
				{
					htmltext = "32568-04.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case CROOP:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "30676-06.html";
								break;
							}
							case 2:
							{
								giveItems(player, CROOPS_INTRODUCTION, 1);
								qs.setCond(3, true);
								htmltext = "30676-07.html";
								break;
							}
							case 3:
							case 4:
							case 5:
							{
								htmltext = "30676-08.html";
								break;
							}
							case 6:
							{
								if (hasQuestItems(player, JACOBS_NECKLACE))
								{
									htmltext = "30676-09.html";
								}
								break;
							}
						}
						break;
					}
					case HECTOR:
					{
						if (qs.isCond(3))
						{
							if (hasQuestItems(player, CROOPS_INTRODUCTION))
							{
								htmltext = "30197-01.html";
							}
						}
						else if (qs.getCond() > 3)
						{
							htmltext = "30197-04.html";
						}
						break;
					}
					case STAN:
					{
						if (qs.isCond(4))
						{
							htmltext = "30200-01.html";
						}
						else if (qs.getCond() > 4)
						{
							htmltext = "30200-05.html";
						}
						break;
					}
					case UNIDENTIFIED_BODY:
					{
						if (qs.isCond(5))
						{
							htmltext = "32568-01.html";
						}
						else if (qs.getCond() < 5)
						{
							htmltext = "32568-03.html";
						}
						break;
					}
					case HOLLINT:
					{
						if (qs.isCond(7) && hasQuestItems(player, CROOPS_LETTER))
						{
							htmltext = "30191-01.html";
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

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
package quests.Q10293_SevenSignsForbiddenBookOfTheElmoreAdenKingdom;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q10292_SevenSignsGirlOfDoubt.Q10292_SevenSignsGirlOfDoubt;

/**
 * Seven Signs, Forbidden Book of the Elmore-Aden Kingdom (10293)
 * @author Adry_85
 */
public final class Q10293_SevenSignsForbiddenBookOfTheElmoreAdenKingdom extends Quest
{
	// NPCs
	private static final int SOPHIA1 = 32596;
	private static final int ELCADIA = 32784;
	private static final int ELCADIA_INSTANCE = 32785;
	private static final int PILE_OF_BOOKS1 = 32809;
	private static final int PILE_OF_BOOKS2 = 32810;
	private static final int PILE_OF_BOOKS3 = 32811;
	private static final int PILE_OF_BOOKS4 = 32812;
	private static final int PILE_OF_BOOKS5 = 32813;
	private static final int SOPHIA2 = 32861;
	private static final int SOPHIA3 = 32863;
	// Item
	private static final int SOLINAS_BIOGRAPHY = 17213;
	// Misc
	private static final int MIN_LEVEL = 81;
	
	public Q10293_SevenSignsForbiddenBookOfTheElmoreAdenKingdom()
	{
		super(10293);
		addFirstTalkId(SOPHIA3);
		addStartNpc(ELCADIA);
		addTalkId(ELCADIA, ELCADIA_INSTANCE, SOPHIA1, SOPHIA2, SOPHIA3, PILE_OF_BOOKS1, PILE_OF_BOOKS2, PILE_OF_BOOKS3, PILE_OF_BOOKS4, PILE_OF_BOOKS5);
		registerQuestItems(SOLINAS_BIOGRAPHY);
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
			case "32784-03.htm":
			case "32784-05.html":
			case "32861-13.html":
			case "32863-02.html":
			case "32863-03.html":
			{
				htmltext = event;
				break;
			}
			case "32784-04.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32784-07.html":
			case "32784-08.html":
			{
				if (qs.isCond(8))
				{
					htmltext = event;
				}
				break;
			}
			case "REWARD":
			{
				if (player.isSubClassActive())
				{
					htmltext = "32784-10.html";
				}
				else
				{
					addExpAndSp(player, 15000000, 1500000);
					qs.exitQuest(false, true);
					htmltext = "32784-09.html";
				}
				break;
			}
			case "32785-02.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32785-07.html":
			{
				if (qs.isCond(4))
				{
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "32596-03.html":
			case "32596-04.html":
			{
				if ((qs.getCond() >= 1) && (qs.getCond() < 8))
				{
					htmltext = event;
				}
				break;
			}
			case "32861-02.html":
			case "32861-03.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32861-04.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "32861-07.html":
			{
				if (qs.isCond(3))
				{
					htmltext = event;
				}
				break;
			}
			case "32861-08.html":
			{
				if (qs.isCond(3))
				{
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "32861-11.html":
			{
				if (qs.isCond(5))
				{
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "32809-02.html":
			{
				if (qs.isCond(6))
				{
					qs.setCond(7, true);
					giveItems(player, SOLINAS_BIOGRAPHY, 1);
					htmltext = event;
				}
				break;
			}
			case "32810-02.html":
			case "32811-02.html":
			case "32812-02.html":
			case "32813-02.html":
			{
				if (qs.isCond(6))
				{
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if ((qs.getCond() >= 1) && (qs.getCond() < 8))
		{
			htmltext = "32863-01.html";
		}
		else
		{
			htmltext = "32863-04.html";
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		switch (npc.getId())
		{
			case ELCADIA:
			{
				if (qs.isCompleted())
				{
					htmltext = "32784-02.html";
				}
				else if (qs.isCreated())
				{
					qs = player.getQuestState(Q10292_SevenSignsGirlOfDoubt.class.getSimpleName());
					htmltext = ((player.getLevel() >= MIN_LEVEL) && (qs != null) && qs.isCompleted()) ? "32784-01.htm" : "32784-11.htm";
				}
				else if (qs.isStarted())
				{
					if (qs.isCond(1))
					{
						htmltext = "32784-06.html";
					}
					else if (qs.isCond(8))
					{
						htmltext = "32784-07.html";
					}
				}
				break;
			}
			case ELCADIA_INSTANCE:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "32785-01.html";
						break;
					}
					case 2:
					{
						htmltext = "32785-04.html";
						qs.setCond(3, true);
						break;
					}
					case 3:
					{
						htmltext = "32785-05.html";
						break;
					}
					case 4:
					{
						htmltext = "32785-06.html";
						break;
					}
					case 5:
					{
						htmltext = "32785-08.html";
						break;
					}
					case 6:
					{
						htmltext = "32785-09.html";
						break;
					}
					case 7:
					{
						qs.setCond(8, true);
						htmltext = "32785-11.html";
						break;
					}
					case 8:
					{
						htmltext = "32785-12.html";
						break;
					}
				}
				break;
			}
			case SOPHIA1:
			{
				if (qs.isStarted())
				{
					if ((qs.getCond() >= 1) && (qs.getCond() < 8))
					{
						htmltext = "32596-01.html";
					}
					else
					{
						htmltext = "32596-05.html";
					}
				}
				break;
			}
			case SOPHIA2:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "32861-01.html";
						break;
					}
					case 2:
					{
						htmltext = "32861-05.html";
						break;
					}
					case 3:
					{
						htmltext = "32861-06.html";
						break;
					}
					case 4:
					{
						htmltext = "32861-09.html";
						break;
					}
					case 5:
					{
						htmltext = "32861-10.html";
						break;
					}
					case 6:
					case 7:
					{
						htmltext = "32861-12.html";
						break;
					}
					case 8:
					{
						htmltext = "32861-14.html";
						break;
					}
				}
				break;
			}
			case PILE_OF_BOOKS1:
			{
				if (qs.isCond(6))
				{
					htmltext = "32809-01.html";
				}
				break;
			}
			case PILE_OF_BOOKS2:
			{
				if (qs.isCond(6))
				{
					htmltext = "32810-01.html";
				}
				break;
			}
			case PILE_OF_BOOKS3:
			{
				if (qs.isCond(6))
				{
					htmltext = "32811-01.html";
				}
				break;
			}
			case PILE_OF_BOOKS4:
			{
				if (qs.isCond(6))
				{
					htmltext = "32812-01.html";
				}
				break;
			}
			case PILE_OF_BOOKS5:
			{
				if (qs.isCond(6))
				{
					htmltext = "32813-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

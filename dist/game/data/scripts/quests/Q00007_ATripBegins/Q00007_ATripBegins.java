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
package quests.Q00007_ATripBegins;

import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * A Trip Begins (7)
 * @author malyelfik
 */
public class Q00007_ATripBegins extends Quest
{
	// NPCs
	private static final int MIRABEL = 30146;
	private static final int ARIEL = 30148;
	private static final int ASTERIOS = 30154;
	// Items
	private static final int ARIELS_RECOMMENDATION = 7572;
	private static final int SCROLL_OF_ESCAPE_GIRAN = 7559;
	private static final int MARK_OF_TRAVELER = 7570;
	// Misc
	private static final int MIN_LEVEL = 3;
	
	public Q00007_ATripBegins()
	{
		super(7);
		addStartNpc(MIRABEL);
		addTalkId(MIRABEL, ARIEL, ASTERIOS);
		registerQuestItems(ARIELS_RECOMMENDATION);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = event;
		switch (event)
		{
			case "30146-03.htm":
			{
				st.startQuest();
				break;
			}
			case "30146-06.html":
			{
				giveItems(player, SCROLL_OF_ESCAPE_GIRAN, 1);
				giveItems(player, MARK_OF_TRAVELER, 1);
				st.exitQuest(false, true);
				break;
			}
			case "30148-02.html":
			{
				st.setCond(2, true);
				giveItems(player, ARIELS_RECOMMENDATION, 1);
				break;
			}
			case "30154-02.html":
			{
				if (!hasQuestItems(player, ARIELS_RECOMMENDATION))
				{
					return "30154-03.html";
				}
				takeItems(player, ARIELS_RECOMMENDATION, -1);
				st.setCond(3, true);
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
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case MIRABEL:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = ((player.getRace() == Race.ELF) && (player.getLevel() >= MIN_LEVEL)) ? "30146-01.htm" : "30146-02.html";
						break;
					}
					case State.STARTED:
					{
						if (st.isCond(1))
						{
							htmltext = "30146-04.html";
						}
						else if (st.isCond(3))
						{
							htmltext = "30146-05.html";
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
			case ARIEL:
			{
				if (st.isStarted())
				{
					if (st.isCond(1))
					{
						htmltext = "30148-01.html";
					}
					else if (st.isCond(2))
					{
						htmltext = "30148-03.html";
					}
				}
				break;
			}
			case ASTERIOS:
			{
				if (st.isStarted())
				{
					if (st.isCond(2))
					{
						htmltext = "30154-01.html";
					}
					else if (st.isCond(3))
					{
						htmltext = "30154-04.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
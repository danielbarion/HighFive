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
package quests.Q00250_WatchWhatYouEat;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Watch What You Eat (250)
 * @author Gnacik
 * @version 2010-08-05 Based on Freya PTS
 */
public class Q00250_WatchWhatYouEat extends Quest
{
	// NPCs
	private static final int SALLY = 32743;
	// Mobs - Items
	private static final int[][] MOBS =
	{
		{
			18864,
			15493
		},
		{
			18865,
			15494
		},
		{
			18868,
			15495
		}
	};
	
	public Q00250_WatchWhatYouEat()
	{
		super(250);
		addStartNpc(SALLY);
		addFirstTalkId(SALLY);
		addTalkId(SALLY);
		for (int[] mob : MOBS)
		{
			addKillId(mob[0]);
		}
		registerQuestItems(15493, 15494, 15495);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState st = getQuestState(player, false);
		
		if (st == null)
		{
			return htmltext;
		}
		
		if (npc.getId() == SALLY)
		{
			if (event.equalsIgnoreCase("32743-03.htm"))
			{
				st.startQuest();
			}
			else if (event.equalsIgnoreCase("32743-end.htm"))
			{
				giveAdena(player, 135661, true);
				addExpAndSp(player, 698334, 76369);
				st.exitQuest(false, true);
			}
			else if (event.equalsIgnoreCase("32743-22.html") && st.isCompleted())
			{
				htmltext = "32743-23.html";
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (npc.getId() == SALLY)
		{
			return "32743-20.html";
		}
		
		return null;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		if (st.isStarted() && st.isCond(1))
		{
			for (int[] mob : MOBS)
			{
				if (npc.getId() == mob[0])
				{
					if (!hasQuestItems(player, mob[1]))
					{
						giveItems(player, mob[1], 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
				}
			}
			if (hasQuestItems(player, MOBS[0][1]) && hasQuestItems(player, MOBS[1][1]) && hasQuestItems(player, MOBS[2][1]))
			{
				st.setCond(2, true);
			}
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (npc.getId() == SALLY)
		{
			switch (st.getState())
			{
				case State.CREATED:
				{
					htmltext = (player.getLevel() >= 82) ? "32743-01.htm" : "32743-00.htm";
					break;
				}
				case State.STARTED:
				{
					if (st.isCond(1))
					{
						htmltext = "32743-04.htm";
					}
					else if (st.isCond(2))
					{
						if (hasQuestItems(player, MOBS[0][1]) && hasQuestItems(player, MOBS[1][1]) && hasQuestItems(player, MOBS[2][1]))
						{
							htmltext = "32743-05.htm";
							for (int items[] : MOBS)
							{
								takeItems(player, items[1], -1);
							}
						}
						else
						{
							htmltext = "32743-06.htm";
						}
					}
					break;
				}
				case State.COMPLETED:
				{
					htmltext = "32743-done.htm";
					break;
				}
			}
		}
		return htmltext;
	}
}

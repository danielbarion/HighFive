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
package quests.Q00027_ChestCaughtWithABaitOfWind;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00050_LanoscosSpecialBait.Q00050_LanoscosSpecialBait;

/**
 * Chest Caught With A Bait Of Wind (27)<br>
 * Original Jython script by DooMIta.
 * @author nonom
 */
public class Q00027_ChestCaughtWithABaitOfWind extends Quest
{
	// NPCs
	private static final int LANOSCO = 31570;
	private static final int SHALING = 31434;
	// Items
	private static final int BLUE_TREASURE_BOX = 6500;
	private static final int STRANGE_BLUESPRINT = 7625;
	private static final int BLACK_PEARL_RING = 880;
	
	public Q00027_ChestCaughtWithABaitOfWind()
	{
		super(27);
		addStartNpc(LANOSCO);
		addTalkId(LANOSCO, SHALING);
		registerQuestItems(STRANGE_BLUESPRINT);
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
		
		switch (event)
		{
			case "31570-03.htm":
			{
				st.startQuest();
				break;
			}
			case "31570-05.htm":
			{
				if (st.isCond(1) && hasQuestItems(player, BLUE_TREASURE_BOX))
				{
					htmltext = "31570-06.htm";
					st.setCond(2, true);
					giveItems(player, STRANGE_BLUESPRINT, 1);
					takeItems(player, BLUE_TREASURE_BOX, -1);
				}
				break;
			}
			case "31434-02.htm":
			{
				if (st.isCond(2) && hasQuestItems(player, STRANGE_BLUESPRINT))
				{
					giveItems(player, BLACK_PEARL_RING, 1);
					st.exitQuest(false, true);
					htmltext = "31434-01.htm";
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
				final QuestState qs = player.getQuestState(Q00050_LanoscosSpecialBait.class.getSimpleName());
				if (npc.getId() == LANOSCO)
				{
					htmltext = "31570-02.htm";
					if (qs != null)
					{
						htmltext = ((player.getLevel() >= 27) && qs.isCompleted()) ? "31570-01.htm" : htmltext;
					}
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case LANOSCO:
					{
						if (st.isCond(1))
						{
							if (hasQuestItems(player, BLUE_TREASURE_BOX))
							{
								htmltext = "31570-04.htm";
							}
							else
							{
								htmltext = "31570-05.htm";
							}
						}
						else
						{
							htmltext = "31570-07.htm";
						}
						break;
					}
					case SHALING:
					{
						if (st.isCond(2))
						{
							htmltext = "31434-00.htm";
						}
						break;
					}
				}
			}
		}
		return htmltext;
	}
}

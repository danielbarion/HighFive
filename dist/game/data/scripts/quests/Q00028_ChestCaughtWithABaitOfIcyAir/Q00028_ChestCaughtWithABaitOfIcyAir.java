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
package quests.Q00028_ChestCaughtWithABaitOfIcyAir;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00051_OFullesSpecialBait.Q00051_OFullesSpecialBait;

/**
 * Chest Caught With A Bait Of Icy Air (28)<br>
 * Original Jython script by Skeleton.
 * @author nonom
 */
public class Q00028_ChestCaughtWithABaitOfIcyAir extends Quest
{
	// NPCs
	private static final int OFULLE = 31572;
	private static final int KIKI = 31442;
	// Items
	private static final int YELLOW_TREASURE_BOX = 6503;
	private static final int KIKIS_LETTER = 7626;
	private static final int ELVEN_RING = 881;
	
	public Q00028_ChestCaughtWithABaitOfIcyAir()
	{
		super(28);
		addStartNpc(OFULLE);
		addTalkId(OFULLE, KIKI);
		registerQuestItems(KIKIS_LETTER);
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
			case "31572-04.htm":
			{
				st.startQuest();
				break;
			}
			case "31572-08.htm":
			{
				if (st.isCond(1) && hasQuestItems(player, YELLOW_TREASURE_BOX))
				{
					giveItems(player, KIKIS_LETTER, 1);
					takeItems(player, YELLOW_TREASURE_BOX, -1);
					st.setCond(2, true);
					htmltext = "31572-07.htm";
				}
				break;
			}
			case "31442-03.htm":
			{
				if (st.isCond(2) && hasQuestItems(player, KIKIS_LETTER))
				{
					giveItems(player, ELVEN_RING, 1);
					st.exitQuest(false, true);
					htmltext = "31442-02.htm";
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
		final int npcId = npc.getId();
		
		switch (st.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				final QuestState qs = player.getQuestState(Q00051_OFullesSpecialBait.class.getSimpleName());
				if (npcId == OFULLE)
				{
					htmltext = "31572-02.htm";
					if (qs != null)
					{
						htmltext = ((player.getLevel() >= 36) && qs.isCompleted()) ? "31572-01.htm" : htmltext;
					}
				}
				break;
			}
			case State.STARTED:
			{
				switch (npcId)
				{
					case OFULLE:
					{
						switch (st.getCond())
						{
							case 1:
							{
								htmltext = "31572-06.htm";
								if (hasQuestItems(player, YELLOW_TREASURE_BOX))
								{
									htmltext = "31572-05.htm";
								}
								break;
							}
							case 2:
							{
								htmltext = "31572-09.htm";
								break;
							}
						}
						break;
					}
					case KIKI:
					{
						if (st.isCond(2))
						{
							htmltext = "31442-01.htm";
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

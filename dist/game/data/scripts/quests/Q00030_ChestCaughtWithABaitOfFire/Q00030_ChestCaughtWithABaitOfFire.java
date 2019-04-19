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
package quests.Q00030_ChestCaughtWithABaitOfFire;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00053_LinnaeusSpecialBait.Q00053_LinnaeusSpecialBait;

/**
 * Chest Caught With A Bait Of Fire (30)<br>
 * Original Jython script by Ethernaly.
 * @author nonom
 */
public class Q00030_ChestCaughtWithABaitOfFire extends Quest
{
	// NPCs
	private static final int LINNAEUS = 31577;
	private static final int RUKAL = 30629;
	// Items
	private static final int RED_TREASURE_BOX = 6511;
	private static final int RUKAL_MUSICAL = 7628;
	private static final int PROTECTION_NECKLACE = 916;
	
	public Q00030_ChestCaughtWithABaitOfFire()
	{
		super(30);
		addStartNpc(LINNAEUS);
		addTalkId(LINNAEUS, RUKAL);
		registerQuestItems(RUKAL_MUSICAL);
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
			case "31577-02.htm":
			{
				st.startQuest();
				break;
			}
			case "31577-04a.htm":
			{
				if (st.isCond(1) && hasQuestItems(player, RED_TREASURE_BOX))
				{
					giveItems(player, RUKAL_MUSICAL, 1);
					takeItems(player, RED_TREASURE_BOX, -1);
					st.setCond(2, true);
					htmltext = "31577-04.htm";
				}
				break;
			}
			case "30629-02.htm":
			{
				if (st.isCond(2) && hasQuestItems(player, RUKAL_MUSICAL))
				{
					giveItems(player, PROTECTION_NECKLACE, 1);
					st.exitQuest(false, true);
					htmltext = "30629-03.htm";
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
				final QuestState qs = player.getQuestState(Q00053_LinnaeusSpecialBait.class.getSimpleName());
				if (npcId == LINNAEUS)
				{
					htmltext = "31577-00.htm";
					if (qs != null)
					{
						htmltext = ((player.getLevel() >= 61) && qs.isCompleted()) ? "31577-01.htm" : htmltext;
					}
				}
				break;
			}
			case State.STARTED:
			{
				switch (npcId)
				{
					case LINNAEUS:
					{
						switch (st.getCond())
						{
							case 1:
							{
								htmltext = "31577-03a.htm";
								if (hasQuestItems(player, RED_TREASURE_BOX))
								{
									htmltext = "31577-03.htm";
								}
								break;
							}
							case 2:
							{
								htmltext = "31577-05.htm";
								break;
							}
						}
						break;
					}
					case RUKAL:
					{
						if (st.isCond(2))
						{
							htmltext = "30629-01.htm";
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

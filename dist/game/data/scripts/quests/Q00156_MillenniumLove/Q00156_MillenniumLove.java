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
package quests.Q00156_MillenniumLove;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Millennium Love (156)
 * @author xban1x
 */
public class Q00156_MillenniumLove extends Quest
{
	// NPCs
	private static final int LILITH = 30368;
	private static final int BAENEDES = 30369;
	// Items
	private static final int LILITHS_LETTER = 1022;
	private static final int THEONS_DIARY = 1023;
	private static final int GREATER_COMP_SOULSHOUT_PACKAGE_NO_GRADE = 5250;
	// Misc
	private static final int MIN_LVL = 15;
	
	public Q00156_MillenniumLove()
	{
		super(156);
		addStartNpc(LILITH);
		addTalkId(LILITH, BAENEDES);
		registerQuestItems(LILITHS_LETTER, THEONS_DIARY);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		String htmltext = null;
		if (st != null)
		{
			switch (event)
			{
				case "30368-02.html":
				case "30368-03.html":
				{
					htmltext = event;
					break;
				}
				case "30368-05.htm":
				{
					if (player.getLevel() >= MIN_LVL)
					{
						st.startQuest();
						giveItems(player, LILITHS_LETTER, 1);
						htmltext = event;
					}
					else
					{
						htmltext = "30368-04.htm";
					}
					break;
				}
				case "30369-02.html":
				{
					if (st.isCond(1) && hasQuestItems(player, LILITHS_LETTER))
					{
						takeItems(player, LILITHS_LETTER, 1);
						giveItems(player, THEONS_DIARY, 1);
						st.setCond(2, true);
						htmltext = event;
					}
					break;
				}
				case "30369-03.html":
				{
					if (st.isCond(1) && hasQuestItems(player, LILITHS_LETTER))
					{
						addExpAndSp(player, 3000, 0);
						st.exitQuest(false, true);
						htmltext = event;
					}
					break;
				}
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
			case LILITH:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = "30368-01.htm";
						break;
					}
					case State.STARTED:
					{
						if (st.isCond(1) && hasQuestItems(player, LILITHS_LETTER))
						{
							htmltext = "30368-06.html";
						}
						else if (st.isCond(2) && hasQuestItems(player, THEONS_DIARY))
						{
							giveItems(player, GREATER_COMP_SOULSHOUT_PACKAGE_NO_GRADE, 1);
							addExpAndSp(player, 3000, 0);
							st.exitQuest(false, true);
							htmltext = "30368-07.html";
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
			case BAENEDES:
			{
				switch (st.getCond())
				{
					case 1:
					{
						if (hasQuestItems(player, LILITHS_LETTER))
						{
							htmltext = "30369-01.html";
						}
						break;
					}
					case 2:
					{
						if (hasQuestItems(player, THEONS_DIARY))
						{
							htmltext = "30369-04.html";
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

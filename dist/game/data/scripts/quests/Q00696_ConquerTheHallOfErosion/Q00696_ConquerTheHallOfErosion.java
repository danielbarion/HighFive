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
package quests.Q00696_ConquerTheHallOfErosion;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

public final class Q00696_ConquerTheHallOfErosion extends Quest
{
	private static final int TEPIOS = 32603;
	private static final int MARK_OF_KEUCEREUS_STAGE_1 = 13691;
	private static final int MARK_OF_KEUCEREUS_STAGE_2 = 13692;
	
	public Q00696_ConquerTheHallOfErosion()
	{
		super(696);
		addStartNpc(TEPIOS);
		addTalkId(TEPIOS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final String htmltext = event;
		final QuestState qs = player.getQuestState(getName());
		if (qs == null)
		{
			return htmltext;
		}
		
		if (event.equalsIgnoreCase("32603-02.html"))
		{
			qs.startQuest();
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
			case State.CREATED:
			{
				if (player.getLevel() >= 75)
				{
					if ((getQuestItemsCount(player, MARK_OF_KEUCEREUS_STAGE_1) > 0) || (getQuestItemsCount(player, MARK_OF_KEUCEREUS_STAGE_2) > 0))
					{
						htmltext = "32603-01.htm";
					}
					else
					{
						htmltext = "32603-05.html";
						qs.exitQuest(true);
					}
				}
				else
				{
					htmltext = "32603-00.html";
					qs.exitQuest(true);
				}
				break;
			}
			case State.STARTED:
			{
				if (qs.getInt("cohemenes") != 0)
				{
					if (getQuestItemsCount(player, MARK_OF_KEUCEREUS_STAGE_2) < 1)
					{
						takeItems(player, MARK_OF_KEUCEREUS_STAGE_1, 1);
						giveItems(player, MARK_OF_KEUCEREUS_STAGE_2, 1);
					}
					htmltext = "32603-04.html";
					qs.exitQuest(true);
				}
				else
				{
					htmltext = "32603-01a.html";
				}
				break;
			}
		}
		return htmltext;
	}
}
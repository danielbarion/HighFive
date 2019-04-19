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
package quests.Q00112_WalkOfFate;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Walk of Fate (112)
 * @author Zoey76
 */
public class Q00112_WalkOfFate extends Quest
{
	// NPCs
	private static final int LIVINA = 30572;
	private static final int KARUDA = 32017;
	// Item
	private static final int SCROLL_ENCHANT_ARMOR_D_GRADE = 956;
	// Misc
	private static final int MIN_LEVEL = 20;
	
	public Q00112_WalkOfFate()
	{
		super(112);
		addStartNpc(LIVINA);
		addTalkId(LIVINA, KARUDA);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if ((st == null) || (player.getLevel() < MIN_LEVEL))
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "30572-04.htm":
			{
				st.startQuest();
				htmltext = event;
				break;
			}
			case "32017-02.html":
			{
				giveAdena(player, 22308, true);
				addExpAndSp(player, 112876, 5774);
				giveItems(player, SCROLL_ENCHANT_ARMOR_D_GRADE, 1);
				st.exitQuest(false, true);
				htmltext = event;
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
				htmltext = (player.getLevel() < MIN_LEVEL) ? "30572-03.html" : "30572-01.htm";
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case LIVINA:
					{
						htmltext = "30572-05.html";
						break;
					}
					case KARUDA:
					{
						htmltext = "32017-01.html";
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
		}
		return htmltext;
	}
}

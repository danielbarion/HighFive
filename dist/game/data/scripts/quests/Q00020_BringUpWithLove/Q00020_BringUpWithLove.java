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
package quests.Q00020_BringUpWithLove;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Bring Up With Love (20)
 * @author Adry_85
 */
public class Q00020_BringUpWithLove extends Quest
{
	// NPC
	private static final int TUNATUN = 31537;
	// Items
	private static final int WATER_CRYSTAL = 9553;
	private static final int INNOCENCE_JEWEL = 15533;
	// Misc
	private static final int MIN_LEVEL = 82;
	
	public Q00020_BringUpWithLove()
	{
		super(20);
		addStartNpc(TUNATUN);
		addTalkId(TUNATUN);
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
			case "31537-02.htm":
			case "31537-03.htm":
			case "31537-04.htm":
			case "31537-05.htm":
			case "31537-06.htm":
			case "31537-07.htm":
			case "31537-08.htm":
			case "31537-09.htm":
			case "31537-10.htm":
			case "31537-12.htm":
			{
				htmltext = event;
				break;
			}
			case "31537-11.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "31537-16.html":
			{
				if (qs.isCond(2) && hasQuestItems(player, INNOCENCE_JEWEL))
				{
					giveItems(player, WATER_CRYSTAL, 1);
					takeItems(player, INNOCENCE_JEWEL, -1);
					qs.exitQuest(false, true);
					htmltext = event;
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
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				htmltext = player.getLevel() >= MIN_LEVEL ? "31537-01.htm" : "31537-13.html";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "31537-14.html";
						break;
					}
					case 2:
					{
						htmltext = !hasQuestItems(player, INNOCENCE_JEWEL) ? "31537-14.html" : "31537-15.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	public static void checkJewelOfInnocence(L2PcInstance player)
	{
		final QuestState qs = player.getQuestState(Q00020_BringUpWithLove.class.getSimpleName());
		if ((qs != null) && qs.isCond(1) && !hasQuestItems(player, INNOCENCE_JEWEL) && (getRandom(100) < 5))
		{
			giveItems(player, INNOCENCE_JEWEL, 1);
			qs.setCond(2, true);
		}
	}
}

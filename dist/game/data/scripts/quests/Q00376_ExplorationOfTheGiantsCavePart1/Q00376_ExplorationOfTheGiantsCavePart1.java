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
package quests.Q00376_ExplorationOfTheGiantsCavePart1;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Exploration of the Giants' Cave Part 1 (376)<br>
 * Original Jython script by Gnacik.
 * @author nonom
 */
public class Q00376_ExplorationOfTheGiantsCavePart1 extends Quest
{
	// NPC
	private static final int SOBLING = 31147;
	// Items
	private static final int ANCIENT_PARCHMENT = 14841;
	private static final int BOOK1 = 14836;
	private static final int BOOK2 = 14837;
	private static final int BOOK3 = 14838;
	private static final int BOOK4 = 14839;
	private static final int BOOK5 = 14840;
	// Mobs
	private static final Map<Integer, Double> MOBS = new HashMap<>();
	static
	{
		MOBS.put(22670, 0.314); // const_lord
		MOBS.put(22671, 0.302); // const_gaurdian
		MOBS.put(22672, 0.300); // const_seer
		MOBS.put(22673, 0.258); // hirokai
		MOBS.put(22674, 0.248); // imagro
		MOBS.put(22675, 0.264); // palite
		MOBS.put(22676, 0.258); // hamrit
		MOBS.put(22677, 0.266); // kranout
	}
	
	public Q00376_ExplorationOfTheGiantsCavePart1()
	{
		super(376);
		addStartNpc(SOBLING);
		addTalkId(SOBLING);
		addKillId(MOBS.keySet());
		registerQuestItems(ANCIENT_PARCHMENT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "31147-02.htm":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "31147-04.html":
			case "31147-cont.html":
			{
				htmltext = event;
				break;
			}
			case "31147-quit.html":
			{
				qs.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(player, -1, 3, npc);
		if (qs != null)
		{
			giveItemRandomly(qs.getPlayer(), npc, ANCIENT_PARCHMENT, 1, 0, MOBS.get(npc.getId()), true);
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isCreated())
		{
			htmltext = (player.getLevel() >= 79) ? "31147-01.htm" : "31147-00.html";
		}
		else if (qs.isStarted())
		{
			htmltext = hasQuestItems(player, BOOK1, BOOK2, BOOK3, BOOK4, BOOK5) ? "31147-03.html" : "31147-02a.html";
		}
		return htmltext;
	}
}

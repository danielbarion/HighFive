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
package quests.Q00368_TrespassingIntoTheHolyGround;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Trespassing into the Holy Ground (368)
 * @author Adry_85
 */
public final class Q00368_TrespassingIntoTheHolyGround extends Quest
{
	// NPC
	private static final int RESTINA = 30926;
	// Item
	private static final int BLADE_STAKATO_FANG = 5881;
	// Misc
	private static final int MIN_LEVEL = 36;
	// Mobs
	private static final Map<Integer, Double> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20794, 0.60); // blade_stakato
		MOBS.put(20795, 0.57); // blade_stakato_worker
		MOBS.put(20796, 0.61); // blade_stakato_soldier
		MOBS.put(20797, 0.93); // blade_stakato_drone
	}
	
	public Q00368_TrespassingIntoTheHolyGround()
	{
		super(368);
		addStartNpc(RESTINA);
		addTalkId(RESTINA);
		addKillId(MOBS.keySet());
		registerQuestItems(BLADE_STAKATO_FANG);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "30926-02.htm":
			{
				st.startQuest();
				htmltext = event;
				break;
			}
			case "30926-05.html":
			{
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
			case "30926-06.html":
			{
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final int i;
		switch (npc.getId())
		{
			case 20795:
			case 20797:
			{
				i = 1;
				break;
			}
			default:
			{
				i = 3;
				break;
			}
		}
		
		final QuestState st = getRandomPartyMemberState(player, -1, i, npc);
		if (st != null)
		{
			giveItemRandomly(player, npc, BLADE_STAKATO_FANG, 1, 0, MOBS.get(npc.getId()), true);
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (st.isCreated())
		{
			htmltext = ((player.getLevel() >= MIN_LEVEL) ? "30926-01.htm" : "30926-03.html");
		}
		else if (st.isStarted())
		{
			if (hasQuestItems(player, BLADE_STAKATO_FANG))
			{
				final long count = getQuestItemsCount(player, BLADE_STAKATO_FANG);
				final long bonus = (count >= 10 ? 9450 : 2000);
				giveAdena(player, (count * 250) + bonus, true);
				takeItems(player, BLADE_STAKATO_FANG, -1);
				htmltext = "30926-04.html";
			}
			else
			{
				htmltext = "30926-07.html";
			}
		}
		return htmltext;
	}
}

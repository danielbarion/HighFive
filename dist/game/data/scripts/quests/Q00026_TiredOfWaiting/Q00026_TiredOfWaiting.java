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
package quests.Q00026_TiredOfWaiting;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Tired Of Waiting (26)
 * @author corbin12
 */
public final class Q00026_TiredOfWaiting extends Quest
{
	// NPCs
	private static final int ISAEL_SILVERSHADOW = 30655;
	private static final int KITZKA = 31045;
	// Items
	private static final int DELIVERY_BOX = 17281;
	private static final Map<String, Integer> REWARDS = new HashMap<>();
	static
	{
		REWARDS.put("31045-10.html", 17248); // Large Dragon Bone
		REWARDS.put("31045-11.html", 17266); // Will of Antharas
		REWARDS.put("31045-12.html", 17267); // Sealed Blood Crystal
	}
	
	public Q00026_TiredOfWaiting()
	{
		super(26);
		addStartNpc(ISAEL_SILVERSHADOW);
		addTalkId(ISAEL_SILVERSHADOW, KITZKA);
		registerQuestItems(DELIVERY_BOX);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "30655-02.htm":
			case "30655-03.htm":
			case "30655-05.html":
			case "30655-06.html":
			case "31045-02.html":
			case "31045-03.html":
			case "31045-05.html":
			case "31045-06.html":
			case "31045-07.html":
			case "31045-08.html":
			case "31045-09.html":
			{
				htmltext = event;
				break;
			}
			case "30655-04.html":
			{
				if (qs.isCreated())
				{
					giveItems(player, DELIVERY_BOX, 1);
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "31045-04.html":
			{
				if (qs.isStarted())
				{
					takeItems(player, DELIVERY_BOX, -1);
					htmltext = event;
				}
				break;
			}
			case "31045-10.html":
			case "31045-11.html":
			case "31045-12.html":
			{
				if (qs.isStarted())
				{
					giveItems(player, REWARDS.get(event), 1);
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
		
		switch (npc.getId())
		{
			case ISAEL_SILVERSHADOW:
			{
				if (qs.isCreated())
				{
					htmltext = (player.getLevel() >= 80) ? "30655-01.htm" : "30655-00.html";
				}
				else if (qs.isStarted())
				{
					htmltext = "30655-07.html";
				}
				else
				{
					htmltext = "30655-08.html";
				}
				break;
			}
			case KITZKA:
			{
				if (qs.isStarted())
				{
					htmltext = hasQuestItems(player, DELIVERY_BOX) ? "31045-01.html" : "31045-09.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

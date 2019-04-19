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
package quests.Q00659_IdRatherBeCollectingFairyBreath;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * I'd Rather Be Collecting Fairy Breath (659)
 * @author Adry_85
 */
public final class Q00659_IdRatherBeCollectingFairyBreath extends Quest
{
	// NPC
	private static final int GALATEA = 30634;
	// Item
	private static final int FAIRY_BREATH = 8286;
	// Misc
	private static final int MIN_LEVEL = 26;
	// Mobs
	private static final Map<Integer, Double> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20078, 0.98); // whispering_wind
		MOBS.put(21023, 0.82); // sobing_wind
		MOBS.put(21024, 0.86); // babbleing_wind
		MOBS.put(21025, 0.90); // giggleing_wind
		MOBS.put(21026, 0.96); // singing_wind
	}
	
	public Q00659_IdRatherBeCollectingFairyBreath()
	{
		super(659);
		addStartNpc(GALATEA);
		addTalkId(GALATEA);
		addKillId(MOBS.keySet());
		registerQuestItems(FAIRY_BREATH);
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
			case "30634-02.htm":
			{
				st.startQuest();
				htmltext = event;
				break;
			}
			case "REWARD":
			{
				if (hasQuestItems(player, FAIRY_BREATH))
				{
					final long count = getQuestItemsCount(player, FAIRY_BREATH);
					final long bonus = ((count >= 10) ? 5365 : 0);
					takeItems(player, FAIRY_BREATH, -1);
					giveAdena(player, (count * 50) + bonus, true);
					htmltext = "30634-05.html";
				}
				else
				{
					htmltext = "30634-06.html";
				}
				break;
			}
			case "30634-07.html":
			{
				htmltext = event;
				break;
			}
			case "30634-08.html":
			{
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getRandomPartyMemberState(player, -1, 3, npc);
		if (st != null)
		{
			giveItemRandomly(player, npc, FAIRY_BREATH, 1, 0, MOBS.get(npc.getId()), true);
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
			htmltext = ((player.getLevel() >= MIN_LEVEL) ? "30634-01.htm" : "30634-03.html");
		}
		else if (st.isStarted())
		{
			htmltext = (hasQuestItems(player, FAIRY_BREATH) ? "30634-04.html" : "30634-09.html");
		}
		return htmltext;
	}
}

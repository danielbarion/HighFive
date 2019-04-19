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
package quests.Q00165_ShilensHunt;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Shilen's Hunt (165)
 * @author xban1x
 */
public class Q00165_ShilensHunt extends Quest
{
	// NPC
	private static final int NELSYA = 30348;
	// Monsters
	private static final Map<Integer, Integer> MONSTERS = new HashMap<>();
	static
	{
		MONSTERS.put(20456, 3); // Ashen Wolf
		MONSTERS.put(20529, 1); // Young Brown Keltir
		MONSTERS.put(20532, 1); // Brown Keltir
		MONSTERS.put(20536, 2); // Elder Brown Keltir
	}
	// Items
	private static final int LESSER_HEALING_POTION = 1060;
	private static final int DARK_BEZOAR = 1160;
	// Misc
	private static final int MIN_LVL = 3;
	private static final int REQUIRED_COUNT = 13;
	
	public Q00165_ShilensHunt()
	{
		super(165);
		addStartNpc(NELSYA);
		addTalkId(NELSYA);
		addKillId(MONSTERS.keySet());
		registerQuestItems(DARK_BEZOAR);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && event.equalsIgnoreCase("30348-03.htm"))
		{
			st.startQuest();
			return event;
		}
		return null;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState st = getQuestState(killer, false);
		if ((st != null) && st.isCond(1) && (getRandom(3) < MONSTERS.get(npc.getId())))
		{
			giveItems(killer, DARK_BEZOAR, 1);
			if (getQuestItemsCount(killer, DARK_BEZOAR) < REQUIRED_COUNT)
			{
				playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
			else
			{
				st.setCond(2, true);
			}
		}
		return super.onKill(npc, killer, isSummon);
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
				htmltext = (player.getRace() == Race.DARK_ELF) ? (player.getLevel() >= MIN_LVL) ? "30348-02.htm" : "30348-01.htm" : "30348-00.htm";
				break;
			}
			case State.STARTED:
			{
				if (st.isCond(2) && (getQuestItemsCount(player, DARK_BEZOAR) >= REQUIRED_COUNT))
				{
					giveItems(player, LESSER_HEALING_POTION, 5);
					addExpAndSp(player, 1000, 0);
					st.exitQuest(false, true);
					htmltext = "30348-05.html";
				}
				else
				{
					htmltext = "30348-04.html";
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
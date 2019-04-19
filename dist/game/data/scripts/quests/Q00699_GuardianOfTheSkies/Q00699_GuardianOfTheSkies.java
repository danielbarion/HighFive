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
package quests.Q00699_GuardianOfTheSkies;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q10273_GoodDayToFly.Q10273_GoodDayToFly;

/**
 * Guardian of the Skies
 * @author xban1x
 */
public class Q00699_GuardianOfTheSkies extends Quest
{
	// NPC
	private static final int LEKON = 32557;
	// Monsters
	private static final int VALDSTONE = 25623;
	private static final Map<Integer, Integer> MONSTERS = new HashMap<>();
	static
	{
		MONSTERS.put(22614, 840); // Vulture Rider lvl 1
		MONSTERS.put(22615, 857); // Vulture Rider lvl 2
		MONSTERS.put(25633, 719); // Vulture Rider lvl 3
	}
	// Item
	private static final int VULTURES_GOLDEN_FEATHER = 13871;
	// Misc
	private static final int MIN_LVL = 75;
	private static final int VULTURES_GOLDEN_FEATHER_ADENA = 1500;
	private static final int BONUS = 8335;
	private static final int BONUS_COUNT = 10;
	
	public Q00699_GuardianOfTheSkies()
	{
		super(699);
		addStartNpc(LEKON);
		addTalkId(LEKON);
		addKillId(VALDSTONE);
		addKillId(MONSTERS.keySet());
		registerQuestItems(VULTURES_GOLDEN_FEATHER);
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
				case "32557-03.htm":
				case "32557-08.html":
				{
					htmltext = event;
					break;
				}
				case "32557-04.htm":
				{
					st.startQuest();
					htmltext = event;
					break;
				}
				case "32557-09.html":
				{
					st.exitQuest(true, true);
					htmltext = event;
					break;
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState st = getQuestState(killer, false);
		if (st != null)
		{
			if (npc.getId() == VALDSTONE)
			{
				int amount = 0;
				final int chance = getRandom(1000);
				if (chance < 215)
				{
					amount = getRandom(10) + 90;
				}
				else if (chance < 446)
				{
					amount = getRandom(10) + 80;
				}
				else if (chance < 715)
				{
					amount = getRandom(10) + 70;
				}
				else
				{
					amount = getRandom(10) + 60;
				}
				giveItems(killer, VULTURES_GOLDEN_FEATHER, amount);
				playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
			else
			{
				if (getRandom(1000) < MONSTERS.get(npc.getId()))
				{
					giveItems(killer, VULTURES_GOLDEN_FEATHER, 1);
					playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				st = player.getQuestState(Q10273_GoodDayToFly.class.getSimpleName());
				htmltext = ((st == null) || (!st.isCompleted()) || (player.getLevel() < MIN_LVL)) ? "32557-02.htm" : "32557-01.htm";
				break;
			}
			case State.STARTED:
			{
				final long feathers = getQuestItemsCount(player, VULTURES_GOLDEN_FEATHER);
				if (feathers > 0)
				{
					giveAdena(player, ((feathers * VULTURES_GOLDEN_FEATHER_ADENA) + (feathers > BONUS_COUNT ? BONUS : 0)), true);
					takeItems(player, VULTURES_GOLDEN_FEATHER, -1);
					htmltext = (feathers > BONUS_COUNT) ? "32557-07.html" : "32557-06.html";
				}
				else
				{
					htmltext = "32557-05.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

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
package quests.Q00700_CursedLife;

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
 * Cursed Life (700)
 * @author xban1x
 */
public class Q00700_CursedLife extends Quest
{
	// NPC
	private static final int ORBYU = 32560;
	// Monsters
	private static final int ROK = 25624;
	private static final Map<Integer, Integer[]> MONSTERS = new HashMap<>();
	//@formatter:off
	static
	{
		MONSTERS.put(22602, new Integer[] { 15, 139, 965}); // Mutant Bird lvl 1
		MONSTERS.put(22603, new Integer[] { 15, 143, 999}); // Mutant Bird lvl 2
		MONSTERS.put(25627, new Integer[] { 14, 125, 993}); // Mutant Bird lvl 3
		MONSTERS.put(22604, new Integer[] { 5, 94, 994}); // Dra Hawk lvl 1
		MONSTERS.put(22605, new Integer[] { 5, 99, 993}); // Dra Hawk lvl 2
		MONSTERS.put(25628, new Integer[] { 3, 73, 991}); // Dra Hawk lvl 3
	}
	//@formatter:on
	// Items
	private static final int SWALLOWED_BONES = 13874;
	private static final int SWALLOWED_STERNUM = 13873;
	private static final int SWALLOWED_SKULL = 13872;
	// Misc
	private static final int MIN_LVL = 75;
	private static final int SWALLOWED_BONES_ADENA = 500;
	private static final int SWALLOWED_STERNUM_ADENA = 5000;
	private static final int SWALLOWED_SKULL_ADENA = 50000;
	private static final int BONUS = 16670;
	
	public Q00700_CursedLife()
	{
		super(700);
		addStartNpc(ORBYU);
		addTalkId(ORBYU);
		addKillId(ROK);
		addKillId(MONSTERS.keySet());
		registerQuestItems(SWALLOWED_BONES, SWALLOWED_STERNUM, SWALLOWED_SKULL);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = getQuestState(player, false);
		String htmltext = null;
		if (st != null)
		{
			switch (event)
			{
				case "32560-02.htm":
				{
					st = player.getQuestState(Q10273_GoodDayToFly.class.getSimpleName());
					htmltext = ((player.getLevel() < MIN_LVL) || (st == null) || (!st.isCompleted())) ? "32560-03.htm" : event;
					break;
				}
				case "32560-04.htm":
				case "32560-09.html":
				{
					htmltext = event;
					break;
				}
				case "32560-05.htm":
				{
					st.startQuest();
					htmltext = event;
					break;
				}
				case "32560-10.html":
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
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				htmltext = "32560-01.htm";
				break;
			}
			case State.STARTED:
			{
				final long bones = getQuestItemsCount(player, SWALLOWED_BONES);
				final long ribs = getQuestItemsCount(player, SWALLOWED_STERNUM);
				final long skulls = getQuestItemsCount(player, SWALLOWED_SKULL);
				final long sum = bones + ribs + skulls;
				if (sum > 0)
				{
					giveAdena(player, ((bones * SWALLOWED_BONES_ADENA) + (ribs * SWALLOWED_STERNUM_ADENA) + (skulls * SWALLOWED_SKULL_ADENA) + (sum >= 10 ? BONUS : 0)), true);
					takeItems(player, -1, SWALLOWED_BONES, SWALLOWED_STERNUM, SWALLOWED_SKULL);
					htmltext = sum < 10 ? "32560-07.html" : "32560-08.html";
				}
				else
				{
					htmltext = "32560-06.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if (st != null)
		{
			if (npc.getId() == ROK)
			{
				int amount = 0;
				int chance = getRandom(1000);
				if (chance < 700)
				{
					amount = 1;
				}
				else if (chance < 885)
				{
					amount = 2;
				}
				else if (chance < 949)
				{
					amount = 3;
				}
				else if (chance < 966)
				{
					amount = getRandom(5) + 4;
				}
				else if (chance < 985)
				{
					amount = getRandom(9) + 4;
				}
				else if (chance < 993)
				{
					amount = getRandom(7) + 13;
				}
				else if (chance < 997)
				{
					amount = getRandom(15) + 9;
				}
				else if (chance < 999)
				{
					amount = getRandom(23) + 53;
				}
				else
				{
					amount = getRandom(49) + 76;
				}
				giveItems(player, SWALLOWED_BONES, amount);
				chance = getRandom(1000);
				if (chance < 520)
				{
					amount = 1;
				}
				else if (chance < 771)
				{
					amount = 2;
				}
				else if (chance < 836)
				{
					amount = 3;
				}
				else if (chance < 985)
				{
					amount = getRandom(2) + 4;
				}
				else if (chance < 995)
				{
					amount = getRandom(4) + 5;
				}
				else
				{
					amount = getRandom(8) + 6;
				}
				giveItems(player, SWALLOWED_STERNUM, amount);
				chance = getRandom(1000);
				if (chance < 185)
				{
					amount = getRandom(2) + 1;
				}
				else if (chance < 370)
				{
					amount = getRandom(6) + 2;
				}
				else if (chance < 570)
				{
					amount = getRandom(6) + 7;
				}
				else if (chance < 850)
				{
					amount = getRandom(6) + 12;
				}
				else
				{
					amount = getRandom(6) + 17;
				}
				giveItems(player, SWALLOWED_SKULL, amount);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
			else
			{
				final Integer[] chances = MONSTERS.get(npc.getId());
				final int chance = getRandom(1000);
				if (chance < chances[0])
				{
					giveItems(player, SWALLOWED_BONES, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				else if (chance < chances[1])
				{
					giveItems(player, SWALLOWED_STERNUM, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				else if (chance < chances[2])
				{
					giveItems(player, SWALLOWED_SKULL, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
}

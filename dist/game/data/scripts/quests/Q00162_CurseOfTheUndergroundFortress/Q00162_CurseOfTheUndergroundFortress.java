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
package quests.Q00162_CurseOfTheUndergroundFortress;

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
 * Curse of the Underground Fortress (162)
 * @author xban1x
 */
public class Q00162_CurseOfTheUndergroundFortress extends Quest
{
	// NPC
	private static final int UNOREN = 30147;
	// Monsters
	private static final Map<Integer, Integer> MONSTERS_SKULLS = new HashMap<>();
	private static final Map<Integer, Integer> MONSTERS_BONES = new HashMap<>();
	static
	{
		MONSTERS_SKULLS.put(20033, 25); // Shade Horror
		MONSTERS_SKULLS.put(20345, 26); // Dark Terror
		MONSTERS_SKULLS.put(20371, 23); // Mist Terror
		MONSTERS_BONES.put(20463, 25); // Dungeon Skeleton Archer
		MONSTERS_BONES.put(20464, 23); // Dungeon Skeleton
		MONSTERS_BONES.put(20504, 26); // Dread Soldier
	}
	// Items
	private static final int BONE_SHIELD = 625;
	private static final int BONE_FRAGMENT = 1158;
	private static final int ELF_SKULL = 1159;
	// Misc
	private static final int MIN_LVL = 12;
	private static final int REQUIRED_COUNT = 13;
	
	public Q00162_CurseOfTheUndergroundFortress()
	{
		super(162);
		addStartNpc(UNOREN);
		addTalkId(UNOREN);
		addKillId(MONSTERS_SKULLS.keySet());
		addKillId(MONSTERS_BONES.keySet());
		registerQuestItems(BONE_FRAGMENT, ELF_SKULL);
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
				case "30147-03.htm":
				{
					htmltext = event;
					break;
				}
				case "30147-04.htm":
				{
					st.startQuest();
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
		if ((st != null) && st.isCond(1))
		{
			if (MONSTERS_SKULLS.containsKey(npc.getId()))
			{
				if (getRandom(100) < MONSTERS_SKULLS.get(npc.getId()))
				{
					long skulls = getQuestItemsCount(killer, ELF_SKULL);
					if (skulls < 3)
					{
						giveItems(killer, ELF_SKULL, 1);
						if (((++skulls) >= 3) && (getQuestItemsCount(killer, BONE_FRAGMENT) >= 10))
						{
							st.setCond(2, true);
						}
						else
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
				}
			}
			else if (MONSTERS_BONES.containsKey(npc.getId()))
			{
				if (getRandom(100) < MONSTERS_BONES.get(npc.getId()))
				{
					long bones = getQuestItemsCount(killer, BONE_FRAGMENT);
					if (bones < 10)
					{
						giveItems(killer, BONE_FRAGMENT, 1);
						if (((++bones) >= 10) && (getQuestItemsCount(killer, ELF_SKULL) >= 3))
						{
							st.setCond(2, true);
						}
						else
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
				}
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
				htmltext = (player.getRace() != Race.DARK_ELF) ? (player.getLevel() >= MIN_LVL) ? "30147-02.htm" : "30147-01.htm" : "30147-00.htm";
				break;
			}
			case State.STARTED:
			{
				if ((getQuestItemsCount(player, BONE_FRAGMENT) + getQuestItemsCount(player, ELF_SKULL)) >= REQUIRED_COUNT)
				{
					giveItems(player, BONE_SHIELD, 1);
					addExpAndSp(player, 22652, 1004);
					giveAdena(player, 24000, true);
					st.exitQuest(false, true);
					htmltext = "30147-06.html";
				}
				else
				{
					htmltext = "30147-05.html";
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
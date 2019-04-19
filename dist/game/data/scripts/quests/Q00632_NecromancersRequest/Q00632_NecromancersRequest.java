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
package quests.Q00632_NecromancersRequest;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Necromancer's Request (632)
 * @author Zoey76
 */
public final class Q00632_NecromancersRequest extends Quest
{
	// NPC
	private static final int MYSTERIOUS_WIZARD = 31522;
	// Items
	private static final int VAMPIRES_HEART = 7542;
	private static final int ZOMBIES_BRAIN = 7543;
	// Misc
	private static final int MIN_LEVEL = 63;
	private static final int REQUIRED_ITEM_COUNT = 200;
	private static final int ADENA_REWARD = 120000;
	// Monsters
	private static final Map<Integer, Double> BRAIN_MONSTERS = new HashMap<>();
	private static final Map<Integer, Double> HEART_MONSTERS = new HashMap<>();
	static
	{
		BRAIN_MONSTERS.put(21547, 0.565); // Corrupted Knight
		BRAIN_MONSTERS.put(21548, 0.484); // Resurrected Knight
		BRAIN_MONSTERS.put(21549, 0.585); // Corrupted Guard
		BRAIN_MONSTERS.put(21550, 0.597); // Corrupted Guard
		BRAIN_MONSTERS.put(21551, 0.673); // Resurrected Guard
		BRAIN_MONSTERS.put(21552, 0.637); // Resurrected Guard
		BRAIN_MONSTERS.put(21555, 0.575); // Slaughter Executioner
		BRAIN_MONSTERS.put(21556, 0.560); // Slaughter Executioner
		BRAIN_MONSTERS.put(21562, 0.631); // Guillotine's Ghost
		BRAIN_MONSTERS.put(21571, 0.758); // Ghost of Rebellion Soldier
		BRAIN_MONSTERS.put(21576, 0.647); // Ghost of Guillotine
		BRAIN_MONSTERS.put(21577, 0.625); // Ghost of Guillotine
		BRAIN_MONSTERS.put(21579, 0.766); // Ghost of Rebellion Leader
		
		HEART_MONSTERS.put(21568, 0.452); // Devil Bat
		HEART_MONSTERS.put(21569, 0.484); // Devil Bat
		HEART_MONSTERS.put(21573, 0.499); // Atrox
		HEART_MONSTERS.put(21582, 0.522); // Vampire Soldier
		HEART_MONSTERS.put(21585, 0.413); // Vampire Magician
		HEART_MONSTERS.put(21586, 0.496); // Vampire Adept
		HEART_MONSTERS.put(21587, 0.519); // Vampire Warrior
		HEART_MONSTERS.put(21588, 0.428); // Vampire Wizard
		HEART_MONSTERS.put(21589, 0.439); // Vampire Wizard
		HEART_MONSTERS.put(21590, 0.428); // Vampire Magister
		HEART_MONSTERS.put(21591, 0.502); // Vampire Magister
		HEART_MONSTERS.put(21592, 0.370); // Vampire Magister
		HEART_MONSTERS.put(21593, 0.592); // Vampire Warlord
		HEART_MONSTERS.put(21594, 0.554); // Vampire Warlord
		HEART_MONSTERS.put(21595, 0.392); // Vampire Warlord
	}
	
	public Q00632_NecromancersRequest()
	{
		super(632);
		addStartNpc(MYSTERIOUS_WIZARD);
		addTalkId(MYSTERIOUS_WIZARD);
		addKillId(BRAIN_MONSTERS.keySet());
		addKillId(HEART_MONSTERS.keySet());
		registerQuestItems(VAMPIRES_HEART, ZOMBIES_BRAIN);
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
			case "31522-104.htm":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					qs.startQuest();
					qs.setMemoState(11);
					htmltext = event;
				}
				break;
			}
			case "31522-201.html":
			{
				htmltext = event;
				break;
			}
			case "31522-202.html":
			{
				if (getQuestItemsCount(player, VAMPIRES_HEART) >= REQUIRED_ITEM_COUNT)
				{
					takeItems(player, VAMPIRES_HEART, -1);
					giveAdena(player, ADENA_REWARD, true);
					qs.setMemoState(11);
					htmltext = event;
				}
				else
				{
					htmltext = "31522-203.html";
				}
				break;
			}
			case "31522-204.html":
			{
				takeItems(player, VAMPIRES_HEART, -1);
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
			if (BRAIN_MONSTERS.containsKey(npc.getId()))
			{
				giveItemRandomly(player, npc, ZOMBIES_BRAIN, 1, 0, BRAIN_MONSTERS.get(npc.getId()), true);
			}
			else
			{
				giveItemRandomly(player, npc, VAMPIRES_HEART, 1, 0, HEART_MONSTERS.get(npc.getId()), true);
				
				if (getQuestItemsCount(player, VAMPIRES_HEART) >= REQUIRED_ITEM_COUNT)
				{
					qs.setCond(2);
					qs.setMemoState(12);
				}
			}
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
			htmltext = player.getLevel() >= MIN_LEVEL ? "31522-101.htm" : "31522-103.htm";
		}
		else if (qs.isStarted())
		{
			if (qs.isMemoState(11))
			{
				htmltext = "31522-106.html";
			}
			else if (qs.isMemoState(12) && (getQuestItemsCount(player, VAMPIRES_HEART) >= REQUIRED_ITEM_COUNT))
			{
				htmltext = "31522-105.html";
			}
		}
		return htmltext;
	}
}

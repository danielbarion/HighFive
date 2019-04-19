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
package quests.Q00360_PlunderTheirSupplies;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.util.Util;

/**
 * Plunder Supplies (360)
 * @author netvirus
 */
public final class Q00360_PlunderTheirSupplies extends Quest
{
	// Npc
	private static final int COLEMAN = 30873;
	// Misc
	private static final int MIN_LVL = 52;
	// Monsters
	private static final Map<Integer, Integer> MONSTER_DROP_CHANCES = new HashMap<>();
	// Items
	private static final int RECIPE_OF_SUPPLY = 5870;
	private static final int SUPPLY_ITEMS = 5872;
	private static final int SUSPICIOUS_DOCUMENT_PIECE = 5871;
	
	static
	{
		MONSTER_DROP_CHANCES.put(20666, 50); // Taik Orc Seeker
		MONSTER_DROP_CHANCES.put(20669, 75); // Taik Orc Supply Leader
	}
	
	public Q00360_PlunderTheirSupplies()
	{
		super(360);
		addStartNpc(COLEMAN);
		addTalkId(COLEMAN);
		addKillId(MONSTER_DROP_CHANCES.keySet());
		registerQuestItems(SUPPLY_ITEMS, SUSPICIOUS_DOCUMENT_PIECE, RECIPE_OF_SUPPLY);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		String htmltext = null;
		if (st == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "30873-03.htm":
			case "30873-09.html":
			{
				htmltext = event;
				break;
			}
			case "30873-04.htm":
			{
				st.startQuest();
				htmltext = event;
				break;
			}
			case "30873-10.html":
			{
				st.exitQuest(false, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isPet)
	{
		final QuestState st = getQuestState(killer, false);
		if ((st == null) || !Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, false))
		{
			return super.onKill(npc, killer, isPet);
		}
		
		if (getRandom(100) < MONSTER_DROP_CHANCES.get(npc.getId()))
		{
			giveItems(killer, SUPPLY_ITEMS, 1);
			playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		
		if (getRandom(100) < 10)
		{
			if (getQuestItemsCount(killer, SUSPICIOUS_DOCUMENT_PIECE) < 4)
			{
				giveItems(killer, SUSPICIOUS_DOCUMENT_PIECE, 1);
			}
			else
			{
				giveItems(killer, RECIPE_OF_SUPPLY, 1);
				takeItems(killer, SUSPICIOUS_DOCUMENT_PIECE, -1);
			}
			playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		return super.onKill(npc, killer, isPet);
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
				htmltext = (player.getLevel() >= MIN_LVL) ? "30873-02.htm" : "30873-01.html";
				break;
			}
			case State.STARTED:
			{
				final long supplyCount = getQuestItemsCount(player, SUPPLY_ITEMS);
				final long recipeCount = getQuestItemsCount(player, RECIPE_OF_SUPPLY);
				if (supplyCount == 0)
				{
					if (recipeCount == 0)
					{
						htmltext = "30873-05.html";
					}
					else
					{
						giveAdena(player, (recipeCount * 6000), true);
						takeItems(player, RECIPE_OF_SUPPLY, -1);
						htmltext = "30873-08.html";
					}
				}
				else
				{
					if (recipeCount == 0)
					{
						giveAdena(player, ((supplyCount * 100) + 6000), true);
						takeItems(player, SUPPLY_ITEMS, -1);
						htmltext = "30873-06.html";
					}
					else
					{
						giveAdena(player, (((supplyCount * 100) + 6000) + (recipeCount * 6000)), true);
						takeItems(player, SUPPLY_ITEMS, -1);
						takeItems(player, RECIPE_OF_SUPPLY, -1);
						htmltext = "30873-07.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

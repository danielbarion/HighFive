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
package quests.Q00311_ExpulsionOfEvilSpirits;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Expulsion of Evil Spirits (311)
 * @author Zoey76
 */
public final class Q00311_ExpulsionOfEvilSpirits extends Quest
{
	// NPC
	private static final int CHAIREN = 32655;
	// Items
	private static final int PROTECTION_SOULS_PENDANT = 14848;
	private static final int SOUL_CORE_CONTAINING_EVIL_SPIRIT = 14881;
	private static final int RAGNA_ORCS_AMULET = 14882;
	// Misc
	private static final int MIN_LEVEL = 80;
	private static final int SOUL_CORE_COUNT = 10;
	private static final int RAGNA_ORCS_KILLS_COUNT = 100;
	private static final int RAGNA_ORCS_AMULET_COUNT = 10;
	// Monsters
	private static final Map<Integer, Double> MONSTERS = new HashMap<>();
	static
	{
		MONSTERS.put(22691, 0.694); // Ragna Orc
		MONSTERS.put(22692, 0.716); // Ragna Orc Warrior
		MONSTERS.put(22693, 0.736); // Ragna Orc Hero
		MONSTERS.put(22694, 0.712); // Ragna Orc Commander
		MONSTERS.put(22695, 0.698); // Ragna Orc Healer
		MONSTERS.put(22696, 0.692); // Ragna Orc Shaman
		MONSTERS.put(22697, 0.640); // Ragna Orc Seer
		MONSTERS.put(22698, 0.716); // Ragna Orc Archer
		MONSTERS.put(22699, 0.752); // Ragna Orc Sniper
		MONSTERS.put(22701, 0.716); // Varangka's Dre Vanul
		MONSTERS.put(22702, 0.662); // Varangka's Destroyer
	}
	
	public Q00311_ExpulsionOfEvilSpirits()
	{
		super(311);
		addStartNpc(CHAIREN);
		addTalkId(CHAIREN);
		addKillId(MONSTERS.keySet());
		registerQuestItems(SOUL_CORE_CONTAINING_EVIL_SPIRIT, RAGNA_ORCS_AMULET);
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
		if (player.getLevel() < MIN_LEVEL)
		{
			return null;
		}
		
		switch (event)
		{
			case "32655-03.htm":
			case "32655-15.html":
			{
				htmltext = event;
				break;
			}
			case "32655-04.htm":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32655-11.html":
			{
				if (getQuestItemsCount(player, SOUL_CORE_CONTAINING_EVIL_SPIRIT) >= SOUL_CORE_COUNT)
				{
					takeItems(player, SOUL_CORE_CONTAINING_EVIL_SPIRIT, SOUL_CORE_COUNT);
					giveItems(player, PROTECTION_SOULS_PENDANT, 1);
					htmltext = event;
				}
				else
				{
					htmltext = "32655-12.html";
				}
				break;
			}
			case "32655-13.html":
			{
				if (!hasQuestItems(player, SOUL_CORE_CONTAINING_EVIL_SPIRIT) && (getQuestItemsCount(player, RAGNA_ORCS_AMULET) >= RAGNA_ORCS_AMULET_COUNT))
				{
					qs.exitQuest(true, true);
					htmltext = event;
				}
				else
				{
					htmltext = "32655-14.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, 1, 2, npc);
		if (qs != null)
		{
			final int count = qs.getMemoStateEx(1) + 1;
			if ((count >= RAGNA_ORCS_KILLS_COUNT) && (getRandom(20) < ((count % 100) + 1)))
			{
				qs.setMemoStateEx(1, 0);
				giveItems(killer, SOUL_CORE_CONTAINING_EVIL_SPIRIT, 1);
				playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
			else
			{
				qs.setMemoStateEx(1, count);
			}
			
			giveItemRandomly(killer, npc, RAGNA_ORCS_AMULET, 1, 0, MONSTERS.get(npc.getId()), true);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isCreated())
		{
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "32655-01.htm" : "32655-02.htm";
		}
		else if (qs.isStarted())
		{
			htmltext = !hasQuestItems(player, SOUL_CORE_CONTAINING_EVIL_SPIRIT, RAGNA_ORCS_AMULET) ? "32655-05.html" : "32655-06.html";
		}
		return htmltext;
	}
}

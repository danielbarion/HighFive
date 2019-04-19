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
package quests.Q00626_ADarkTwilight;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * A Dark Twilight (626)<br>
 * Original Jython script by disKret.
 * @author Citizen
 */
public class Q00626_ADarkTwilight extends Quest
{
	// NPCs
	private static final int HIERARCH = 31517;
	// Items
	private static final int BLOOD_OF_SAINT = 7169;
	// Monsters
	private static final Map<Integer, Integer> MONSTERS = new HashMap<>();
	static
	{
		MONSTERS.put(21520, 641); // Eye of Splendor
		MONSTERS.put(21523, 648); // Flash of Splendor
		MONSTERS.put(21524, 692); // Blade of Splendor
		MONSTERS.put(21525, 710); // Blade of Splendor
		MONSTERS.put(21526, 772); // Wisdom of Splendor
		MONSTERS.put(21529, 639); // Soul of Splendor
		MONSTERS.put(21530, 683); // Victory of Splendor
		MONSTERS.put(21531, 767); // Punishment of Splendor
		MONSTERS.put(21532, 795); // Shout of Splendor
		MONSTERS.put(21535, 802); // Signet of Splendor
		MONSTERS.put(21536, 774); // Crown of Splendor
		MONSTERS.put(21539, 848); // Wailing of Splendor
		MONSTERS.put(21540, 880); // Wailing of Splendor
		MONSTERS.put(21658, 790); // Punishment of Splendor
	}
	
	// Misc
	private static final int MIN_LEVEL_REQUIRED = 60;
	private static final int ITEMS_COUNT_REQUIRED = 300;
	// Rewards
	private static final int ADENA_COUNT = 100000;
	private static final int XP_COUNT = 162773;
	private static final int SP_COUNT = 12500;
	
	public Q00626_ADarkTwilight()
	{
		super(626);
		addStartNpc(HIERARCH);
		addTalkId(HIERARCH);
		addKillId(MONSTERS.keySet());
		registerQuestItems(BLOOD_OF_SAINT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		String htmltext = event;
		switch (event)
		{
			case "31517-05.html":
			{
				break;
			}
			case "31517-02.htm":
			{
				qs.startQuest();
				break;
			}
			case "Exp":
			{
				if (getQuestItemsCount(player, BLOOD_OF_SAINT) < ITEMS_COUNT_REQUIRED)
				{
					return "31517-06.html";
				}
				addExpAndSp(player, XP_COUNT, SP_COUNT);
				qs.exitQuest(true, true);
				htmltext = "31517-07.html";
				break;
			}
			case "Adena":
			{
				if (getQuestItemsCount(player, BLOOD_OF_SAINT) < ITEMS_COUNT_REQUIRED)
				{
					return "31517-06.html";
				}
				giveAdena(player, ADENA_COUNT, true);
				qs.exitQuest(true, true);
				htmltext = "31517-07.html";
				break;
			}
			default:
			{
				htmltext = null;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(killer, 1);
		if (partyMember != null)
		{
			final QuestState qs = getQuestState(partyMember, false);
			final float chance = MONSTERS.get(npc.getId()) * Config.RATE_QUEST_DROP;
			if (getRandom(1000) < chance)
			{
				giveItems(partyMember, BLOOD_OF_SAINT, 1);
				if (getQuestItemsCount(partyMember, BLOOD_OF_SAINT) < ITEMS_COUNT_REQUIRED)
				{
					playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				else
				{
					qs.setCond(2, true);
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = (player.getLevel() >= MIN_LEVEL_REQUIRED) ? "31517-01.htm" : "31517-00.htm";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "31517-03.html";
						break;
					}
					case 2:
					{
						htmltext = "31517-04.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
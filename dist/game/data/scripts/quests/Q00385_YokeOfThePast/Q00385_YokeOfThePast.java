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
package quests.Q00385_YokeOfThePast;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Yoke of the Past (385)
 * @author Pandragon
 */
public final class Q00385_YokeOfThePast extends Quest
{
	// NPCs
	// @formatter:off
	private static final int[] ZIGGURATS =
	{
		31095, 31096, 31097, 31098, 31099, 31100, 31101,
		31102, 31103, 31104, 31105, 31106, 31107, 31108,
		31109, 31110, 31114, 31115, 31116, 31117, 31118,
		31119, 31120, 31121, 31122, 31123, 31124, 31125
	};
	// @formatter:on
	// Item
	private static final int SCROLL_OF_ANCIENT_MAGIC = 5902;
	// Reward
	private static final int BLANK_SCROLL = 5965;
	// Monsters
	private static final Map<Integer, Double> MONSTER_CHANCES = new HashMap<>();
	{
		MONSTER_CHANCES.put(21144, 0.306); // Catacomb Shadow
		MONSTER_CHANCES.put(21156, 0.994); // Purgatory Shadow
		MONSTER_CHANCES.put(21208, 0.146); // Hallowed Watchman
		MONSTER_CHANCES.put(21209, 0.166); // Hallowed Seer
		MONSTER_CHANCES.put(21210, 0.202); // Vault Guardian
		MONSTER_CHANCES.put(21211, 0.212); // Vault Seer
		MONSTER_CHANCES.put(21213, 0.274); // Hallowed Monk
		MONSTER_CHANCES.put(21214, 0.342); // Vault Sentinel
		MONSTER_CHANCES.put(21215, 0.360); // Vault Monk
		MONSTER_CHANCES.put(21217, 0.460); // Hallowed Priest
		MONSTER_CHANCES.put(21218, 0.558); // Vault Overlord
		MONSTER_CHANCES.put(21219, 0.578); // Vault Priest
		MONSTER_CHANCES.put(21221, 0.710); // Sepulcher Inquisitor
		MONSTER_CHANCES.put(21222, 0.842); // Sepulcher Archon
		MONSTER_CHANCES.put(21223, 0.862); // Sepulcher Inquisitor
		MONSTER_CHANCES.put(21224, 0.940); // Sepulcher Guardian
		MONSTER_CHANCES.put(21225, 0.970); // Sepulcher Sage
		MONSTER_CHANCES.put(21226, 0.202); // Sepulcher Guardian
		MONSTER_CHANCES.put(21227, 0.290); // Sepulcher Sage
		MONSTER_CHANCES.put(21228, 0.316); // Sepulcher Guard
		MONSTER_CHANCES.put(21229, 0.426); // Sepulcher Preacher
		MONSTER_CHANCES.put(21230, 0.646); // Sepulcher Guard
		MONSTER_CHANCES.put(21231, 0.654); // Sepulcher Preacher
		MONSTER_CHANCES.put(21236, 0.238); // Barrow Sentinel
		MONSTER_CHANCES.put(21237, 0.274); // Barrow Monk
		MONSTER_CHANCES.put(21238, 0.342); // Grave Sentinel
		MONSTER_CHANCES.put(21239, 0.360); // Grave Monk
		MONSTER_CHANCES.put(21240, 0.410); // Barrow Overlord
		MONSTER_CHANCES.put(21241, 0.460); // Barrow Priest
		MONSTER_CHANCES.put(21242, 0.558); // Grave Overlord
		MONSTER_CHANCES.put(21243, 0.578); // Grave Priest
		MONSTER_CHANCES.put(21244, 0.642); // Crypt Archon
		MONSTER_CHANCES.put(21245, 0.700); // Crypt Inquisitor
		MONSTER_CHANCES.put(21246, 0.842); // Tomb Archon
		MONSTER_CHANCES.put(21247, 0.862); // Tomb Inquisitor
		MONSTER_CHANCES.put(21248, 0.940); // Crypt Guardian
		MONSTER_CHANCES.put(21249, 0.970); // Crypt Sage
		MONSTER_CHANCES.put(21250, 0.798); // Tomb Guardian
		MONSTER_CHANCES.put(21251, 0.710); // Tomb Sage
		MONSTER_CHANCES.put(21252, 0.684); // Crypt Guard
		MONSTER_CHANCES.put(21253, 0.574); // Crypt Preacher
		MONSTER_CHANCES.put(21254, 0.354); // Tomb Guard
		MONSTER_CHANCES.put(21255, 0.250); // Tomb Preacher
	}
	// Misc
	private static final int MIN_LVL = 20;
	
	public Q00385_YokeOfThePast()
	{
		super(385);
		addStartNpc(ZIGGURATS);
		addTalkId(ZIGGURATS);
		addKillId(MONSTER_CHANCES.keySet());
		registerQuestItems(SCROLL_OF_ANCIENT_MAGIC);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs != null)
		{
			switch (event)
			{
				case "ziggurat-03.htm":
				case "ziggurat-04.htm":
				case "ziggurat-06.htm":
				case "ziggurat-07.htm":
				{
					htmltext = event;
					break;
				}
				case "ziggurat-05.htm":
				{
					if (qs.isCreated())
					{
						qs.startQuest();
						htmltext = event;
					}
					break;
				}
				case "ziggurat-10.html":
				{
					qs.exitQuest(true, true);
					htmltext = event;
					break;
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = (talker.getLevel() >= MIN_LVL) ? "ziggurat-01.htm" : "ziggurat-02.htm";
				break;
			}
			case State.STARTED:
			{
				if (hasQuestItems(talker, SCROLL_OF_ANCIENT_MAGIC))
				{
					rewardItems(talker, BLANK_SCROLL, getQuestItemsCount(talker, SCROLL_OF_ANCIENT_MAGIC));
					takeItems(talker, SCROLL_OF_ANCIENT_MAGIC, -1);
					htmltext = "ziggurat-09.html";
				}
				else
				{
					htmltext = "ziggurat-08.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, -1, 3, npc);
		if (qs != null)
		{
			giveItemRandomly(qs.getPlayer(), npc, SCROLL_OF_ANCIENT_MAGIC, 1, 0, MONSTER_CHANCES.get(npc.getId()), true);
		}
		return super.onKill(npc, killer, isSummon);
	}
}

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
package quests.Q00631_DeliciousTopChoiceMeat;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Delicious Top Choice Meat (631)
 * @author Adry_85
 */
public class Q00631_DeliciousTopChoiceMeat extends Quest
{
	// NPC
	private static final int TUNATUN = 31537;
	// Items
	private static final int TOP_QUALITY_MEAT = 7546;
	private static final int PRIME_MEAT = 15534;
	// Misc
	private static final int MIN_LEVEL = 82;
	private static final int PRIME_MEAT_COUNT = 120;
	// Rewards
	private static final int[] RECIPE =
	{
		10373, // Recipe - Icarus Sawsword (60%)
		10374, // Recipe - Icarus Disperser (60%)
		10375, // Recipe - Icarus Spirit (60%)
		10376, // Recipe - Icarus Heavy Arms (60%)
		10377, // Recipe - Icarus Trident (60%)
		10378, // Recipe - Icarus Hammer (60%)
		10379, // Recipe - Icarus Hand (60%)
		10380, // Recipe - Icarus Hall (60%)
		10381, // Recipe - Icarus Spitter (60%)
	};
	private static final int[] PIECE =
	{
		10397, // Icarus Sawsword Piece
		10398, // Icarus Disperser Piece
		10399, // Icarus Spirit Piece
		10400, // Icarus Heavy Arms Piece
		10401, // Icarus Trident Piece
		10402, // Icarus Hammer Piece
		10403, // Icarus Hand Piece
		10404, // Icarus Hall Piece
		10405, // Icarus Spitter Piece
	};
	private static final int GOLDEN_SPICE_CRATE = 15482;
	private static final int CRYSTAL_SPICE_COMPRESSED_PACK = 15483;
	private static final Map<Integer, Double> MOBS_MEAT = new HashMap<>();
	static
	{
		MOBS_MEAT.put(18878, 0.172); // Full Grown Kookaburra
		MOBS_MEAT.put(18879, 0.334); // Full Grown Kookaburra
		MOBS_MEAT.put(18885, 0.172); // Full Grown Cougar
		MOBS_MEAT.put(18886, 0.334); // Full Grown Cougar
		MOBS_MEAT.put(18892, 0.182); // Full Grown Buffalo
		MOBS_MEAT.put(18893, 0.349); // Full Grown Buffalo
		MOBS_MEAT.put(18899, 0.182); // Full Grown Grendel
		MOBS_MEAT.put(18900, 0.349); // Full Grown Grendel
	}
	
	public Q00631_DeliciousTopChoiceMeat()
	{
		super(631);
		addStartNpc(TUNATUN);
		addTalkId(TUNATUN);
		addKillId(MOBS_MEAT.keySet());
		registerQuestItems(TOP_QUALITY_MEAT, PRIME_MEAT);
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
			case "quest_accept":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					qs.startQuest();
					htmltext = "31537-02.html";
				}
				else
				{
					htmltext = "31537-03.html";
				}
				break;
			}
			case "31537-06.html":
			{
				if (qs.isCond(2) && (getQuestItemsCount(player, PRIME_MEAT) >= PRIME_MEAT_COUNT))
				{
					switch (getRandom(10))
					{
						case 0:
						{
							rewardItems(player, RECIPE[getRandom(RECIPE.length)], 1);
							break;
						}
						case 1:
						{
							rewardItems(player, PIECE[getRandom(PIECE.length)], 1);
							break;
						}
						case 2:
						{
							rewardItems(player, PIECE[getRandom(PIECE.length)], 2);
							break;
						}
						case 3:
						{
							rewardItems(player, PIECE[getRandom(PIECE.length)], 3);
							break;
						}
						case 4:
						{
							rewardItems(player, PIECE[getRandom(PIECE.length)], getRandom(5) + 2);
							break;
						}
						case 5:
						{
							rewardItems(player, PIECE[getRandom(PIECE.length)], getRandom(7) + 2);
							break;
						}
						case 6:
						{
							rewardItems(player, GOLDEN_SPICE_CRATE, 1);
							break;
						}
						case 7:
						{
							rewardItems(player, GOLDEN_SPICE_CRATE, 2);
							break;
						}
						case 8:
						{
							rewardItems(player, CRYSTAL_SPICE_COMPRESSED_PACK, 1);
							break;
						}
						case 9:
						{
							rewardItems(player, CRYSTAL_SPICE_COMPRESSED_PACK, 2);
							break;
						}
					}
					qs.exitQuest(true, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(player, 1, 3, npc);
		if ((qs != null) && giveItemRandomly(qs.getPlayer(), npc, PRIME_MEAT, 1, PRIME_MEAT_COUNT, MOBS_MEAT.get(npc.getId()), true))
		{
			qs.setCond(2, true);
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
			htmltext = "31537-01.htm";
		}
		else if (qs.isStarted())
		{
			if (qs.isCond(1))
			{
				if (getQuestItemsCount(player, PRIME_MEAT) < PRIME_MEAT_COUNT)
				{
					htmltext = "31537-04.html";
				}
			}
			else if (qs.isCond(2) && (getQuestItemsCount(player, PRIME_MEAT) >= PRIME_MEAT_COUNT))
			{
				htmltext = "31537-05.html";
			}
		}
		return htmltext;
	}
}

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
package quests.Q00280_TheFoodChain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.util.Util;

/**
 * The Food Chain (280)
 * @author xban1x
 */
public final class Q00280_TheFoodChain extends Quest
{
	// Npc
	private static final int BIXON = 32175;
	// Items
	private static final int GREY_KELTIR_TOOTH = 9809;
	private static final int BLACK_WOLF_TOOTH = 9810;
	// Monsters
	private static final Map<Integer, Integer> MONSTER_ITEM = new HashMap<>();
	private static final Map<Integer, List<ItemHolder>> MONSTER_CHANCE = new HashMap<>();
	static
	{
		MONSTER_ITEM.put(22229, GREY_KELTIR_TOOTH);
		MONSTER_ITEM.put(22230, GREY_KELTIR_TOOTH);
		MONSTER_ITEM.put(22231, GREY_KELTIR_TOOTH);
		MONSTER_ITEM.put(22232, BLACK_WOLF_TOOTH);
		MONSTER_ITEM.put(22233, BLACK_WOLF_TOOTH);
		MONSTER_CHANCE.put(22229, Arrays.asList(new ItemHolder(1000, 1)));
		MONSTER_CHANCE.put(22230, Arrays.asList(new ItemHolder(500, 1), new ItemHolder(1000, 2)));
		MONSTER_CHANCE.put(22231, Arrays.asList(new ItemHolder(1000, 2)));
		MONSTER_CHANCE.put(22232, Arrays.asList(new ItemHolder(1000, 3)));
		MONSTER_CHANCE.put(22233, Arrays.asList(new ItemHolder(500, 3), new ItemHolder(1000, 4)));
	}
	// Rewards
	private static final int[] REWARDS = new int[]
	{
		28,
		35,
		41,
		48,
		116,
	};
	// Misc
	private static final int MIN_LVL = 3;
	private static final int TEETH_COUNT = 25;
	
	public Q00280_TheFoodChain()
	{
		super(280);
		addStartNpc(BIXON);
		addTalkId(BIXON);
		addKillId(MONSTER_ITEM.keySet());
		registerQuestItems(GREY_KELTIR_TOOTH, BLACK_WOLF_TOOTH);
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
			case "32175-03.htm":
			{
				st.startQuest();
				htmltext = event;
				break;
			}
			case "32175-06.html":
			{
				if (hasAtLeastOneQuestItem(player, getRegisteredItemIds()))
				{
					final long greyTeeth = getQuestItemsCount(player, GREY_KELTIR_TOOTH);
					final long blackTeeth = getQuestItemsCount(player, BLACK_WOLF_TOOTH);
					giveAdena(player, 2 * (greyTeeth + blackTeeth), true);
					takeItems(player, -1, GREY_KELTIR_TOOTH, BLACK_WOLF_TOOTH);
					htmltext = event;
				}
				else
				{
					htmltext = "32175-07.html";
				}
			}
			case "32175-08.html":
			{
				htmltext = event;
				break;
			}
			case "32175-09.html":
			{
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
			case "32175-11.html":
			{
				final long greyTeeth = getQuestItemsCount(player, GREY_KELTIR_TOOTH);
				final long blackTeeth = getQuestItemsCount(player, BLACK_WOLF_TOOTH);
				if ((greyTeeth + blackTeeth) >= TEETH_COUNT)
				{
					if (greyTeeth >= TEETH_COUNT)
					{
						takeItems(player, GREY_KELTIR_TOOTH, TEETH_COUNT);
					}
					else
					{
						takeItems(player, GREY_KELTIR_TOOTH, greyTeeth);
						takeItems(player, BLACK_WOLF_TOOTH, TEETH_COUNT - greyTeeth);
					}
					rewardItems(player, REWARDS[getRandom(5)], 1);
					htmltext = event;
				}
				else
				{
					htmltext = "32175-10.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState st = getQuestState(killer, false);
		if ((st != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			final int chance = getRandom(1000);
			for (ItemHolder dropChance : MONSTER_CHANCE.get(npc.getId()))
			{
				if (chance < dropChance.getId())
				{
					giveItemRandomly(killer, MONSTER_ITEM.get(npc.getId()), dropChance.getCount(), 0, 1, true);
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState st = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				htmltext = (talker.getLevel() >= MIN_LVL) ? "32175-01.htm" : "32175-02.htm";
				break;
			}
			case State.STARTED:
			{
				if (hasAtLeastOneQuestItem(talker, getRegisteredItemIds()))
				{
					htmltext = "32175-05.html";
				}
				else
				{
					htmltext = "32175-04.html";
				}
				break;
			}
		}
		return htmltext;
	}
}
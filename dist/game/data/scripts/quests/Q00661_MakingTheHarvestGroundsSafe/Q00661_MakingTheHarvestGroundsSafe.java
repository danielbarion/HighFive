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
package quests.Q00661_MakingTheHarvestGroundsSafe;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemChanceHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Making the Harvest Grounds Safe (661)
 * @author Pandragon
 */
public final class Q00661_MakingTheHarvestGroundsSafe extends Quest
{
	// NPC
	private static final int NORMAN = 30210;
	// Items
	private static final int BIG_HORNET_STING = 8283;
	private static final int CLOUD_GEM = 8284;
	private static final int YOUNG_ARANEID_CLAW = 8285;
	// Monsters
	private final Map<Integer, ItemChanceHolder> MONSTER_CHANCES = new HashMap<>();
	{
		MONSTER_CHANCES.put(21095, new ItemChanceHolder(BIG_HORNET_STING, 0.508)); // Giant Poison Bee
		MONSTER_CHANCES.put(21096, new ItemChanceHolder(CLOUD_GEM, 0.5)); // Cloudy Beast
		MONSTER_CHANCES.put(21097, new ItemChanceHolder(YOUNG_ARANEID_CLAW, 0.516)); // Young Araneid
	}
	// Misc
	private static final int MIN_LVL = 21;
	
	public Q00661_MakingTheHarvestGroundsSafe()
	{
		super(661);
		addStartNpc(NORMAN);
		addTalkId(NORMAN);
		addKillId(MONSTER_CHANCES.keySet());
		registerQuestItems(BIG_HORNET_STING, CLOUD_GEM, YOUNG_ARANEID_CLAW);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "30210-01.htm":
			case "30210-02.htm":
			case "30210-04.html":
			case "30210-06.html":
			{
				htmltext = event;
				break;
			}
			case "30210-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30210-08.html":
			{
				final long stingCount = getQuestItemsCount(player, BIG_HORNET_STING);
				final long gemCount = getQuestItemsCount(player, CLOUD_GEM);
				final long clawCount = getQuestItemsCount(player, YOUNG_ARANEID_CLAW);
				long reward = (57 * stingCount) + (56 * gemCount) + (60 * clawCount);
				if ((stingCount + gemCount + clawCount) >= 10)
				{
					reward += 5773;
				}
				takeItems(player, BIG_HORNET_STING, -1);
				takeItems(player, CLOUD_GEM, -1);
				takeItems(player, YOUNG_ARANEID_CLAW, -1);
				giveAdena(player, reward, true);
				htmltext = event;
				break;
			}
			case "30210-09.html":
			{
				qs.exitQuest(true, true);
				htmltext = event;
				break;
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
				htmltext = (talker.getLevel() >= MIN_LVL) ? "30210-01.htm" : "30210-02.htm";
				break;
			}
			case State.STARTED:
			{
				if (hasQuestItems(talker, BIG_HORNET_STING, CLOUD_GEM, YOUNG_ARANEID_CLAW))
				{
					htmltext = "30210-04.html";
				}
				else
				{
					htmltext = "30210-05.html";
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
			final ItemChanceHolder item = MONSTER_CHANCES.get(npc.getId());
			giveItemRandomly(qs.getPlayer(), npc, item.getId(), item.getCount(), 0, item.getChance(), true);
		}
		return super.onKill(npc, killer, isSummon);
	}
}

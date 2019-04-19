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
package quests.Q00623_TheFinestFood;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * The Finest Food (623)
 * @author janiko
 */
public final class Q00623_TheFinestFood extends Quest
{
	// NPCs
	private static final int JEREMY = 31521;
	// Monsters
	private static final int THERMAL_BUFFALO = 21315;
	private static final int THERMAL_FLAVA = 21316;
	private static final int THERMAL_ANTELOPE = 21318;
	// Items
	private static final ItemHolder LEAF_OF_FLAVA = new ItemHolder(7199, 100);
	private static final ItemHolder BUFFALO_MEAT = new ItemHolder(7200, 100);
	private static final ItemHolder HORN_OF_ANTELOPE = new ItemHolder(7201, 100);
	// Rewards
	private static final ItemHolder RING_OF_AURAKYRA = new ItemHolder(6849, 1);
	private static final ItemHolder SEALED_SANDDRAGONS_EARING = new ItemHolder(6847, 1);
	private static final ItemHolder DRAGON_NECKLACE = new ItemHolder(6851, 1);
	// Misc
	private static final int MIN_LVL = 71;
	
	private static final Map<Integer, ItemHolder> MONSTER_DROPS = new HashMap<>();
	static
	{
		MONSTER_DROPS.put(THERMAL_BUFFALO, BUFFALO_MEAT);
		MONSTER_DROPS.put(THERMAL_FLAVA, LEAF_OF_FLAVA);
		MONSTER_DROPS.put(THERMAL_ANTELOPE, HORN_OF_ANTELOPE);
	}
	
	public Q00623_TheFinestFood()
	{
		super(623);
		addStartNpc(JEREMY);
		addTalkId(JEREMY);
		addKillId(THERMAL_BUFFALO, THERMAL_FLAVA, THERMAL_ANTELOPE);
		registerQuestItems(LEAF_OF_FLAVA.getId(), BUFFALO_MEAT.getId(), HORN_OF_ANTELOPE.getId());
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
			case "31521-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "31521-06.html":
			{
				if (qs.isCond(2))
				{
					if (hasAllItems(player, true, LEAF_OF_FLAVA, BUFFALO_MEAT, HORN_OF_ANTELOPE))
					{
						final int random = getRandom(1000);
						if (random < 120)
						{
							giveAdena(player, 25000, true);
							rewardItems(player, RING_OF_AURAKYRA);
						}
						else if (random < 240)
						{
							giveAdena(player, 65000, true);
							rewardItems(player, SEALED_SANDDRAGONS_EARING);
						}
						else if (random < 340)
						{
							giveAdena(player, 25000, true);
							rewardItems(player, DRAGON_NECKLACE);
						}
						else if (random < 940)
						{
							giveAdena(player, 73000, true);
							addExpAndSp(player, 230000, 18200);
						}
						qs.exitQuest(true, true);
						htmltext = event;
					}
					else
					{
						htmltext = "31521-07.html";
					}
				}
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
		switch (npc.getId())
		{
			case JEREMY:
			{
				if (qs.isCreated())
				{
					htmltext = (talker.getLevel() >= MIN_LVL) ? "31521-01.htm" : "31521-02.htm";
				}
				else if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "31521-04.html";
							break;
						}
						case 2:
						{
							htmltext = "31521-05.html";
							break;
						}
					}
				}
				else if (qs.isCompleted())
				{
					htmltext = getAlreadyCompletedMsg(talker);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, 1, 3, npc);
		final ItemHolder holder = MONSTER_DROPS.get(npc.getId());
		if ((qs != null) && giveItemRandomly(qs.getPlayer(), npc, holder.getId(), 1, holder.getCount(), 1, true) && hasAllItems(qs.getPlayer(), true, BUFFALO_MEAT, HORN_OF_ANTELOPE, LEAF_OF_FLAVA))
		{
			qs.setCond(2);
		}
		return super.onKill(npc, killer, isSummon);
	}
}
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
package quests.Q00692_HowtoOpposeEvil;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * How to Oppose Evil (692)
 * @author Gigiikun
 */
public final class Q00692_HowtoOpposeEvil extends Quest
{
	private static final int DILIOS = 32549;
	private static final int KIRKLAN = 32550;
	private static final int LEKONS_CERTIFICATE = 13857;
	private static final int[] QUEST_ITEMS =
	{
		13863,
		13864,
		13865,
		13866,
		13867,
		15535,
		15536
	};
	
	private static final Map<Integer, ItemHolder> QUEST_MOBS = new HashMap<>();
	static
	{
		// Seed of Infinity
		QUEST_MOBS.put(22509, new ItemHolder(13863, 500));
		QUEST_MOBS.put(22510, new ItemHolder(13863, 500));
		QUEST_MOBS.put(22511, new ItemHolder(13863, 500));
		QUEST_MOBS.put(22512, new ItemHolder(13863, 500));
		QUEST_MOBS.put(22513, new ItemHolder(13863, 500));
		QUEST_MOBS.put(22514, new ItemHolder(13863, 500));
		QUEST_MOBS.put(22515, new ItemHolder(13863, 500));
		// Seed of Destruction
		QUEST_MOBS.put(22537, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22538, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22539, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22540, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22541, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22542, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22543, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22544, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22546, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22547, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22548, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22549, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22550, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22551, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22552, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22593, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22596, new ItemHolder(13865, 250));
		QUEST_MOBS.put(22597, new ItemHolder(13865, 250));
		// Seed of Annihilation
		QUEST_MOBS.put(22746, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22747, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22748, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22749, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22750, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22751, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22752, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22753, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22754, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22755, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22756, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22757, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22758, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22759, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22760, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22761, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22762, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22763, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22764, new ItemHolder(15536, 125));
		QUEST_MOBS.put(22765, new ItemHolder(15536, 125));
	}
	
	public Q00692_HowtoOpposeEvil()
	{
		super(692);
		addStartNpc(DILIOS);
		addTalkId(DILIOS, KIRKLAN);
		addKillId(QUEST_MOBS.keySet());
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		if (event.equalsIgnoreCase("32549-03.htm"))
		{
			st.startQuest();
		}
		else if (event.equalsIgnoreCase("32550-04.htm"))
		{
			st.setCond(3);
		}
		else if (event.equalsIgnoreCase("32550-07.htm"))
		{
			if (!giveReward(player, 13863, 5, 13796, 1))
			{
				return "32550-08.htm";
			}
		}
		else if (event.equalsIgnoreCase("32550-09.htm"))
		{
			if (!giveReward(player, 13798, 1, 57, 5000))
			{
				return "32550-10.htm";
			}
		}
		else if (event.equalsIgnoreCase("32550-12.htm"))
		{
			if (!giveReward(player, 13865, 5, 13841, 1))
			{
				return "32550-13.htm";
			}
		}
		else if (event.equalsIgnoreCase("32550-14.htm"))
		{
			if (!giveReward(player, 13867, 1, 57, 5000))
			{
				return "32550-15.htm";
			}
		}
		else if (event.equalsIgnoreCase("32550-17.htm"))
		{
			if (!giveReward(player, 15536, 5, 15486, 1))
			{
				return "32550-18.htm";
			}
		}
		else if (event.equalsIgnoreCase("32550-19.htm"))
		{
			if (!giveReward(player, 15535, 1, 57, 5000))
			{
				return "32550-20.htm";
			}
		}
		return event;
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 3);
		if (partyMember == null)
		{
			return null;
		}
		final QuestState st = getQuestState(partyMember, false);
		final int npcId = npc.getId();
		if ((st != null) && QUEST_MOBS.containsKey(npcId))
		{
			int chance = (int) (QUEST_MOBS.get(npcId).getCount() * Config.RATE_QUEST_DROP);
			int numItems = chance / 1000;
			chance = chance % 1000;
			if (getRandom(1000) < chance)
			{
				numItems++;
			}
			if (numItems > 0)
			{
				giveItems(player, QUEST_MOBS.get(npcId).getId(), numItems);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return null;
	}
	
	@Override
	public final String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (st.isCreated())
		{
			htmltext = (player.getLevel() >= 75) ? "32549-01.htm" : "32549-00.htm";
		}
		else
		{
			if (npc.getId() == DILIOS)
			{
				if (st.isCond(1) && hasQuestItems(player, LEKONS_CERTIFICATE))
				{
					htmltext = "32549-04.htm";
					takeItems(player, LEKONS_CERTIFICATE, -1);
					st.setCond(2);
				}
				else if (st.isCond(2))
				{
					htmltext = "32549-05.htm";
				}
			}
			else
			{
				if (st.isCond(2))
				{
					htmltext = "32550-01.htm";
				}
				else if (st.isCond(3))
				{
					for (int i : QUEST_ITEMS)
					{
						if (getQuestItemsCount(player, i) > 0)
						{
							return "32550-05.htm";
						}
					}
					htmltext = "32550-04.htm";
				}
			}
		}
		return htmltext;
	}
	
	private static boolean giveReward(L2PcInstance player, int itemId, int minCount, int rewardItemId, long rewardCount)
	{
		long count = getQuestItemsCount(player, itemId);
		if (count < minCount)
		{
			return false;
		}
		
		count /= minCount;
		takeItems(player, itemId, count * minCount);
		rewardItems(player, rewardItemId, rewardCount * count);
		return true;
	}
}
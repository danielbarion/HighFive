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
package quests.Q00251_NoSecrets;

import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * No Secrets (251)
 * @author Dumpster
 */
public class Q00251_NoSecrets extends Quest
{
	public static final int PINAPS = 30201;
	public static final int DIARY = 15508;
	public static final int TABLE = 15509;
	
	private static final int[] MOBS =
	{
		22783,
		22785,
		22780,
		22782,
		22784
	};
	
	private static final int[] MOBS2 =
	{
		22775,
		22776,
		22778
	};
	
	public Q00251_NoSecrets()
	{
		super(251);
		addStartNpc(PINAPS);
		addTalkId(PINAPS);
		addKillId(MOBS);
		addKillId(MOBS2);
		registerQuestItems(DIARY, TABLE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		if (event.equals("30201-03.htm"))
		{
			st.startQuest();
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && st.isStarted() && st.isCond(1))
		{
			final int npcId = npc.getId();
			
			if (CommonUtil.contains(MOBS, npcId) && (getRandom(100) < 10) && (getQuestItemsCount(player, DIARY) < 10))
			{
				giveItems(player, DIARY, 1);
				if ((getQuestItemsCount(player, DIARY) >= 10) && (getQuestItemsCount(player, TABLE) >= 5))
				{
					st.setCond(2, true);
				}
				else
				{
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
			else if (CommonUtil.contains(MOBS2, npcId) && (getRandom(100) < 5) && (getQuestItemsCount(player, TABLE) < 5))
			{
				giveItems(player, TABLE, 1);
				if ((getQuestItemsCount(player, DIARY) >= 10) && (getQuestItemsCount(player, TABLE) >= 5))
				{
					st.setCond(2, true);
				}
				else
				{
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
		return super.onKill(npc, player, isSummon);
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
				htmltext = (player.getLevel() > 81) ? "30201-01.htm" : "30201-00.htm";
				break;
			}
			case State.STARTED:
			{
				if (st.isCond(1))
				{
					htmltext = "30201-05.htm";
				}
				else if ((st.isCond(2)) && (getQuestItemsCount(player, DIARY) >= 10) && (getQuestItemsCount(player, TABLE) >= 5))
				{
					htmltext = "30201-04.htm";
					giveAdena(player, 313355, true);
					addExpAndSp(player, 56787, 160578);
					st.exitQuest(false, true);
				}
				break;
			}
			case State.COMPLETED:
			{
				htmltext = "30201-06.htm";
				break;
			}
		}
		return htmltext;
	}
}

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
package quests.Q00637_ThroughOnceMore;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Through the Gate Once More (637)<br>
 * Original Jython script by BiTi! and DrLecter.
 * @author DS
 */
public final class Q00637_ThroughOnceMore extends Quest
{
	private static final int FLAURON = 32010;
	private static final int[] MOBS =
	{
		21565,
		21566,
		21567
	};
	private static final int VISITOR_MARK = 8064;
	private static final int FADED_MARK = 8065;
	private static final int NECRO_HEART = 8066;
	private static final int MARK = 8067;
	
	private static final double DROP_CHANCE = 90;
	
	public Q00637_ThroughOnceMore()
	{
		super(637);
		addStartNpc(FLAURON);
		addTalkId(FLAURON);
		addKillId(MOBS);
		registerQuestItems(NECRO_HEART);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		if ("32010-03.htm".equals(event))
		{
			st.startQuest();
		}
		else if ("32010-10.htm".equals(event))
		{
			st.exitQuest(true);
		}
		return event;
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && (st.getState() == State.STARTED))
		{
			final long count = getQuestItemsCount(player, NECRO_HEART);
			if (count < 10)
			{
				int chance = (int) (Config.RATE_QUEST_DROP * DROP_CHANCE);
				int numItems = chance / 100;
				chance = chance % 100;
				if (getRandom(100) < chance)
				{
					numItems++;
				}
				if (numItems > 0)
				{
					if ((count + numItems) >= 10)
					{
						numItems = 10 - (int) count;
						st.setCond(2, true);
					}
					else
					{
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					
					giveItems(player, NECRO_HEART, numItems);
				}
			}
		}
		return null;
	}
	
	@Override
	public final String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		
		final byte id = st.getState();
		if (id == State.CREATED)
		{
			if (player.getLevel() > 72)
			{
				if (hasQuestItems(player, FADED_MARK))
				{
					return "32010-02.htm";
				}
				if (hasQuestItems(player, VISITOR_MARK))
				{
					st.exitQuest(true);
					return "32010-01a.htm";
				}
				if (hasQuestItems(player, MARK))
				{
					st.exitQuest(true);
					return "32010-0.htm";
				}
			}
			st.exitQuest(true);
			return "32010-01.htm";
		}
		else if (id == State.STARTED)
		{
			if ((st.isCond(2)) && (getQuestItemsCount(player, NECRO_HEART) == 10))
			{
				takeItems(player, NECRO_HEART, 10);
				takeItems(player, FADED_MARK, 1);
				giveItems(player, MARK, 1);
				giveItems(player, 8273, 10);
				st.exitQuest(true, true);
				return "32010-05.htm";
			}
			return "32010-04.htm";
		}
		return getNoQuestMsg(player);
	}
}
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
package quests.Q00553_OlympiadUndefeated;

import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.olympiad.CompetitionType;
import com.l2jmobius.gameserver.model.olympiad.Participant;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Olympiad Undefeated (553)
 * @author lion
 */
public class Q00553_OlympiadUndefeated extends Quest
{
	// NPC
	private static final int MANAGER = 31688;
	// Items
	private static final int WIN_CONF_2 = 17244;
	private static final int WIN_CONF_5 = 17245;
	private static final int WIN_CONF_10 = 17246;
	private static final int OLY_CHEST = 17169;
	private static final int MEDAL_OF_GLORY = 21874;
	
	public Q00553_OlympiadUndefeated()
	{
		super(553);
		addStartNpc(MANAGER);
		addTalkId(MANAGER);
		registerQuestItems(WIN_CONF_2, WIN_CONF_5, WIN_CONF_10);
		addOlympiadMatchFinishId();
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		String htmltext = event;
		
		if (event.equalsIgnoreCase("31688-03.html"))
		{
			qs.startQuest();
		}
		else if (event.equalsIgnoreCase("31688-04.html"))
		{
			final long count = getQuestItemsCount(player, WIN_CONF_2) + getQuestItemsCount(player, WIN_CONF_5);
			
			if (count > 0)
			{
				giveItems(player, OLY_CHEST, count);
				if (count == 2)
				{
					giveItems(player, MEDAL_OF_GLORY, 3);
				}
				qs.exitQuest(QuestType.DAILY, true);
			}
			else
			{
				htmltext = getNoQuestMsg(player);
			}
		}
		return htmltext;
	}
	
	@Override
	public void onOlympiadMatchFinish(Participant winner, Participant looser, CompetitionType type)
	{
		if (winner != null)
		{
			final L2PcInstance player = winner.getPlayer();
			if (player == null)
			{
				return;
			}
			
			final QuestState qs = getQuestState(player, false);
			if ((qs != null) && qs.isStarted() && qs.isCond(1))
			{
				final int matches = qs.getInt("undefeatable") + 1;
				qs.set("undefeatable", String.valueOf(matches));
				switch (matches)
				{
					case 2:
					{
						if (!hasQuestItems(player, WIN_CONF_2))
						{
							giveItems(player, WIN_CONF_2, 1);
						}
						break;
					}
					case 5:
					{
						if (!hasQuestItems(player, WIN_CONF_5))
						{
							giveItems(player, WIN_CONF_5, 1);
						}
						break;
					}
					case 10:
					{
						if (!hasQuestItems(player, WIN_CONF_10))
						{
							giveItems(player, WIN_CONF_10, 1);
							qs.setCond(2);
						}
						break;
					}
				}
			}
		}
		
		if (looser != null)
		{
			final L2PcInstance player = looser.getPlayer();
			if (player == null)
			{
				return;
			}
			
			final QuestState qs = getQuestState(player, false);
			if ((qs != null) && qs.isStarted() && qs.isCond(1))
			{
				qs.unset("undefeatable");
				takeItems(player, WIN_CONF_2, -1);
				takeItems(player, WIN_CONF_5, -1);
				takeItems(player, WIN_CONF_10, -1);
			}
		}
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if ((player.getLevel() < 75) || !player.isNoble())
		{
			htmltext = "31688-00.htm";
		}
		else if (qs.isCreated())
		{
			htmltext = "31688-01.htm";
		}
		else if (qs.isCompleted())
		{
			if (qs.isNowAvailable())
			{
				qs.setState(State.CREATED);
				htmltext = (player.getLevel() < 75) || !player.isNoble() ? "31688-00.htm" : "31688-01.htm";
			}
			else
			{
				htmltext = "31688-05.html";
			}
		}
		else
		{
			final long count = getQuestItemsCount(player, WIN_CONF_2) + getQuestItemsCount(player, WIN_CONF_5) + getQuestItemsCount(player, WIN_CONF_10);
			if ((count == 3) && qs.isCond(2))
			{
				giveItems(player, OLY_CHEST, 4);
				giveItems(player, MEDAL_OF_GLORY, 5);
				qs.exitQuest(QuestType.DAILY, true);
				htmltext = "31688-04.html";
			}
			else
			{
				htmltext = "31688-w" + count + ".html";
			}
		}
		return htmltext;
	}
}

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
package quests.Q00016_TheComingDarkness;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00017_LightAndDarkness.Q00017_LightAndDarkness;

/**
 * The Coming Darkness (16)<br>
 * Original jython script by disKret.<br>
 * TODO: Zoey76: This quest is still not retail like, Altars AI is incomplete.
 * @author nonom
 */
public class Q00016_TheComingDarkness extends Quest
{
	// NPCs
	private static final int HIERARCH = 31517;
	private static final int EVIL_ALTAR_1 = 31512;
	private static final int EVIL_ALTAR_2 = 31513;
	private static final int EVIL_ALTAR_3 = 31514;
	private static final int EVIL_ALTAR_4 = 31515;
	private static final int EVIL_ALTAR_5 = 31516;
	// Item
	private static final int CRYSTAL_OF_SEAL = 7167;
	
	public Q00016_TheComingDarkness()
	{
		super(16);
		addStartNpc(HIERARCH);
		addTalkId(HIERARCH, EVIL_ALTAR_1, EVIL_ALTAR_2, EVIL_ALTAR_3, EVIL_ALTAR_4, EVIL_ALTAR_5);
		registerQuestItems(CRYSTAL_OF_SEAL);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final String htmltext = event;
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return htmltext;
		}
		
		final int cond = qs.getCond();
		switch (event)
		{
			case "31517-02.htm":
			{
				qs.startQuest();
				giveItems(player, CRYSTAL_OF_SEAL, 5);
				break;
			}
			case "31512-01.html":
			case "31513-01.html":
			case "31514-01.html":
			case "31515-01.html":
			case "31516-01.html":
			{
				final int npcId = Integer.parseInt(event.replace("-01.html", ""));
				if ((cond == (npcId - 31511)) && hasQuestItems(player, CRYSTAL_OF_SEAL))
				{
					takeItems(player, CRYSTAL_OF_SEAL, 1);
					qs.setCond(cond + 1, true);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		final QuestState qs2 = player.getQuestState(Q00017_LightAndDarkness.class.getSimpleName());
		if ((qs2 != null) && !qs2.isCompleted())
		{
			return "31517-04.html";
		}
		
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				htmltext = (player.getLevel() >= 62) ? "31517-00.htm" : "31517-05.html";
				break;
			}
			case State.STARTED:
			{
				final int npcId = npc.getId();
				if (npcId == HIERARCH)
				{
					if (qs.isCond(6))
					{
						addExpAndSp(player, 865187, 69172);
						qs.exitQuest(false, true);
						htmltext = "31517-03.html";
					}
					else
					{
						htmltext = "31517-02a.html";
					}
				}
				else if ((npcId - 31511) == qs.getCond())
				{
					htmltext = npcId + "-00.html";
				}
				else
				{
					htmltext = npcId + "-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

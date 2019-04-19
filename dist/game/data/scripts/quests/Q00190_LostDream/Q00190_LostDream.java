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
package quests.Q00190_LostDream;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q00187_NikolasHeart.Q00187_NikolasHeart;

/**
 * Lost Dream (190)
 * @author ivantotov
 */
public final class Q00190_LostDream extends Quest
{
	// NPCs
	private static final int JURIS = 30113;
	private static final int HEAD_BLACKSMITH_KUSTO = 30512;
	private static final int MAESTRO_NIKOLA = 30621;
	private static final int RESEARCHER_LORAIN = 30673;
	// Misc
	private static final int MIN_LEVEL = 42;
	private static final int MAX_LEVEL_FOR_EXP_SP = 48;
	
	public Q00190_LostDream()
	{
		super(190);
		addStartNpc(HEAD_BLACKSMITH_KUSTO);
		addTalkId(HEAD_BLACKSMITH_KUSTO, RESEARCHER_LORAIN, MAESTRO_NIKOLA, JURIS);
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
			case "30512-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = event;
				}
				break;
			}
			case "30512-06.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setMemoState(3);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "30113-02.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "30113-03.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
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
		final int memoState = qs.getMemoState();
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == HEAD_BLACKSMITH_KUSTO)
			{
				final QuestState q187 = player.getQuestState(Q00187_NikolasHeart.class.getSimpleName());
				if ((q187 != null) && q187.isCompleted())
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "30512-01.htm" : "30512-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case HEAD_BLACKSMITH_KUSTO:
				{
					if (memoState == 1)
					{
						htmltext = "30512-04.html";
					}
					else if (memoState == 2)
					{
						htmltext = "30512-05.html";
					}
					else if ((memoState >= 3) && (memoState <= 4))
					{
						htmltext = "30512-07.html";
					}
					else if (memoState == 5)
					{
						htmltext = "30512-08.html";
						giveAdena(player, 109427, true);
						if (player.getLevel() < MAX_LEVEL_FOR_EXP_SP)
						{
							addExpAndSp(player, 309467, 20614);
						}
						qs.exitQuest(false, true);
					}
					break;
				}
				case JURIS:
				{
					if (memoState == 1)
					{
						htmltext = "30113-01.html";
					}
					else if (memoState == 2)
					{
						htmltext = "30113-04.html";
					}
					break;
				}
				case MAESTRO_NIKOLA:
				{
					if (memoState == 4)
					{
						qs.setMemoState(5);
						qs.setCond(5, true);
						htmltext = "30621-01.html";
					}
					else if (memoState == 5)
					{
						htmltext = "30621-02.html";
					}
					break;
				}
				case RESEARCHER_LORAIN:
				{
					if (memoState == 3)
					{
						qs.setMemoState(4);
						qs.setCond(4, true);
						htmltext = "30673-01.html";
					}
					else if (memoState == 4)
					{
						htmltext = "30673-02.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted() && (npc.getId() == HEAD_BLACKSMITH_KUSTO))
		{
			htmltext = getAlreadyCompletedMsg(player);
		}
		return htmltext;
	}
}
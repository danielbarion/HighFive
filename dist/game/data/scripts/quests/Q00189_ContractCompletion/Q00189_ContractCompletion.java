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
package quests.Q00189_ContractCompletion;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q00186_ContractExecution.Q00186_ContractExecution;

/**
 * Contract Completion (189)
 * @author ivantotov
 */
public final class Q00189_ContractCompletion extends Quest
{
	// NPCs
	private static final int SHEGFIELD = 30068;
	private static final int HEAD_BLACKSMITH_KUSTO = 30512;
	private static final int RESEARCHER_LORAIN = 30673;
	private static final int BLUEPRINT_SELLER_LUKA = 31437;
	// Items
	private static final int SCROLL_OF_DECODING = 10370;
	// Misc
	private static final int MIN_LEVEL = 42;
	private static final int MAX_LEVEL_FOR_EXP_SP = 48;
	
	public Q00189_ContractCompletion()
	{
		super(189);
		addStartNpc(BLUEPRINT_SELLER_LUKA);
		addTalkId(BLUEPRINT_SELLER_LUKA, HEAD_BLACKSMITH_KUSTO, RESEARCHER_LORAIN, SHEGFIELD);
		registerQuestItems(SCROLL_OF_DECODING);
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
			case "31437-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					giveItems(player, SCROLL_OF_DECODING, 1);
					htmltext = event;
				}
				break;
			}
			case "30512-02.html":
			{
				if (qs.isMemoState(4))
				{
					giveAdena(player, 121527, true);
					if (player.getLevel() < MAX_LEVEL_FOR_EXP_SP)
					{
						addExpAndSp(player, 309467, 20614);
					}
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "30673-02.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					takeItems(player, SCROLL_OF_DECODING, -1);
					htmltext = event;
				}
				break;
			}
			case "30068-02.html":
			{
				if (qs.isMemoState(2))
				{
					htmltext = event;
				}
				break;
			}
			case "30068-03.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setMemoState(3);
					qs.setCond(3, true);
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
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == BLUEPRINT_SELLER_LUKA)
			{
				final QuestState q186 = player.getQuestState(Q00186_ContractExecution.class.getSimpleName());
				if ((q186 != null) && q186.isCompleted())
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "31437-01.htm" : "31437-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case BLUEPRINT_SELLER_LUKA:
				{
					if (qs.getMemoState() >= 1)
					{
						htmltext = "31437-04.html";
					}
					break;
				}
				case HEAD_BLACKSMITH_KUSTO:
				{
					if (qs.isMemoState(4))
					{
						htmltext = "30512-01.html";
					}
					break;
				}
				case RESEARCHER_LORAIN:
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30673-01.html";
							break;
						}
						case 2:
						{
							htmltext = "30673-03.html";
							break;
						}
						case 3:
						{
							qs.setMemoState(4);
							qs.setCond(4, true);
							htmltext = "30673-04.html";
							break;
						}
						case 4:
						{
							htmltext = "30673-05.html";
							break;
						}
					}
					break;
				}
				case SHEGFIELD:
				{
					switch (qs.getCond())
					{
						case 2:
						{
							htmltext = "30068-01.html";
							break;
						}
						case 3:
						{
							htmltext = "30068-04.html";
							break;
						}
					}
					break;
				}
			}
		}
		else if (qs.isCompleted() && (npc.getId() == BLUEPRINT_SELLER_LUKA))
		{
			htmltext = getAlreadyCompletedMsg(player);
		}
		return htmltext;
	}
}
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
package quests.Q00191_VainConclusion;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q00188_SealRemoval.Q00188_SealRemoval;

/**
 * Vain Conclusion (191)
 * @author ivantotov
 */
public final class Q00191_VainConclusion extends Quest
{
	// NPCs
	private static final int SHEGFIELD = 30068;
	private static final int HEAD_BLACKSMITH_KUSTO = 30512;
	private static final int RESEARCHER_LORAIN = 30673;
	private static final int DOROTHY_LOCKSMITH = 30970;
	// Items
	private static final int REPAIRED_METALLOGRAPH = 10371;
	// Misc
	private static final int MIN_LEVEL = 42;
	private static final int MAX_LEVEL_FOR_EXP_SP = 48;
	
	public Q00191_VainConclusion()
	{
		super(191);
		addStartNpc(DOROTHY_LOCKSMITH);
		addTalkId(DOROTHY_LOCKSMITH, HEAD_BLACKSMITH_KUSTO, RESEARCHER_LORAIN, SHEGFIELD);
		registerQuestItems(REPAIRED_METALLOGRAPH);
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
			case "30970-03.htm":
			{
				htmltext = event;
				break;
			}
			case "30970-04.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					giveItems(player, REPAIRED_METALLOGRAPH, 1);
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
			case "30512-02.html":
			{
				if (qs.isMemoState(4))
				{
					giveAdena(player, 117327, true);
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
					takeItems(player, REPAIRED_METALLOGRAPH, -1);
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
			if (npc.getId() == DOROTHY_LOCKSMITH)
			{
				final QuestState q188 = player.getQuestState(Q00188_SealRemoval.class.getSimpleName());
				if ((q188 != null) && q188.isCompleted())
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "30970-01.htm" : "30970-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case DOROTHY_LOCKSMITH:
				{
					if (qs.getMemoState() >= 1)
					{
						htmltext = "30970-05.html";
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
			}
		}
		else if (qs.isCompleted() && (npc.getId() == DOROTHY_LOCKSMITH))
		{
			htmltext = getAlreadyCompletedMsg(player);
		}
		return htmltext;
	}
}
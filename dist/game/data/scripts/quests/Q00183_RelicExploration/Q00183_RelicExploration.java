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
package quests.Q00183_RelicExploration;

import com.l2jmobius.gameserver.instancemanager.QuestManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q00184_ArtOfPersuasion.Q00184_ArtOfPersuasion;
import quests.Q00185_NikolasCooperation.Q00185_NikolasCooperation;

/**
 * Relic Exploration (183)
 * @author ivantotov
 */
public final class Q00183_RelicExploration extends Quest
{
	// NPCs
	private static final int HEAD_BLACKSMITH_KUSTO = 30512;
	private static final int MAESTRO_NIKOLA = 30621;
	private static final int RESEARCHER_LORAIN = 30673;
	// Misc
	private static final int MIN_LEVEL = 40;
	private static final int MAX_LEVEL_FOR_EXP_SP = 46;
	
	public Q00183_RelicExploration()
	{
		super(183);
		addStartNpc(HEAD_BLACKSMITH_KUSTO);
		addTalkId(HEAD_BLACKSMITH_KUSTO, RESEARCHER_LORAIN, MAESTRO_NIKOLA);
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
			case "30512-04.htm":
			{
				qs.startQuest();
				qs.setMemoState(1);
				htmltext = event;
				break;
			}
			case "30512-02.htm":
			{
				htmltext = event;
				break;
			}
			case "30621-02.html":
			{
				if (qs.isMemoState(2))
				{
					giveAdena(player, 18100, true);
					if (player.getLevel() < MAX_LEVEL_FOR_EXP_SP)
					{
						addExpAndSp(player, 60000, 3000);
					}
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "30673-02.html":
			case "30673-03.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "30673-04.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "Contract":
			{
				final QuestState qs184 = player.getQuestState(Q00184_ArtOfPersuasion.class.getSimpleName());
				final QuestState qs185 = player.getQuestState(Q00185_NikolasCooperation.class.getSimpleName());
				final Quest quest = QuestManager.getInstance().getQuest(Q00184_ArtOfPersuasion.class.getSimpleName());
				if ((quest != null) && (qs184 == null) && (qs185 == null))
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						quest.notifyEvent("30621-03.htm", npc, player);
					}
					else
					{
						quest.notifyEvent("30621-03a.html", npc, player);
					}
				}
				break;
			}
			case "Consideration":
			{
				final QuestState qs184 = player.getQuestState(Q00184_ArtOfPersuasion.class.getSimpleName());
				final QuestState qs185 = player.getQuestState(Q00185_NikolasCooperation.class.getSimpleName());
				final Quest quest = QuestManager.getInstance().getQuest(Q00185_NikolasCooperation.class.getSimpleName());
				if ((quest != null) && (qs184 == null) && (qs185 == null))
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						quest.notifyEvent("30621-03.htm", npc, player);
					}
					else
					{
						quest.notifyEvent("30621-03a.html", npc, player);
					}
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
			if (npc.getId() == HEAD_BLACKSMITH_KUSTO)
			{
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "30512-01.htm" : "30512-03.html";
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case HEAD_BLACKSMITH_KUSTO:
				{
					htmltext = "30512-05.html";
					break;
				}
				case MAESTRO_NIKOLA:
				{
					if (qs.isMemoState(2))
					{
						htmltext = "30621-01.html";
					}
					break;
				}
				case RESEARCHER_LORAIN:
				{
					if (qs.isMemoState(1))
					{
						htmltext = "30673-01.html";
					}
					else if (qs.isMemoState(2))
					{
						htmltext = "30673-05.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			htmltext = getAlreadyCompletedMsg(player);
		}
		return htmltext;
	}
}
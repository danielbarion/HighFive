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
package quests.Q00015_SweetWhispers;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Sweet Whisper (15)<br>
 * Original jython script by disKret.
 * @author nonom
 */
public class Q00015_SweetWhispers extends Quest
{
	// NPCs
	private static final int VLADIMIR = 31302;
	private static final int HIERARCH = 31517;
	private static final int M_NECROMANCER = 31518;
	
	public Q00015_SweetWhispers()
	{
		super(15);
		addStartNpc(VLADIMIR);
		addTalkId(VLADIMIR, HIERARCH, M_NECROMANCER);
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
		
		switch (event)
		{
			case "31302-01.html":
			{
				qs.startQuest();
				break;
			}
			case "31518-01.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2);
				}
				break;
			}
			case "31517-01.html":
			{
				if (qs.isCond(2))
				{
					addExpAndSp(player, 350531, 28204);
					qs.exitQuest(false, true);
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
		final int npcId = npc.getId();
		
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				if (npcId == VLADIMIR)
				{
					htmltext = (player.getLevel() >= 60) ? "31302-00.htm" : "31302-00a.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npcId)
				{
					case VLADIMIR:
					{
						if (qs.isCond(1))
						{
							htmltext = "31302-01a.html";
						}
						break;
					}
					case M_NECROMANCER:
					{
						final int cond = qs.getCond();
						if (cond == 1)
						{
							htmltext = "31518-00.html";
						}
						else if (cond == 2)
						{
							htmltext = "31518-01a.html";
						}
						break;
					}
					case HIERARCH:
					{
						if (qs.isCond(2))
						{
							htmltext = "31517-00.html";
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

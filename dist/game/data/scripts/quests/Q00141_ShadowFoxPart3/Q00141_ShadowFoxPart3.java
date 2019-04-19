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
package quests.Q00141_ShadowFoxPart3;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.instancemanager.QuestManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00140_ShadowFoxPart2.Q00140_ShadowFoxPart2;
import quests.Q00998_FallenAngelSelect.Q00998_FallenAngelSelect;

/**
 * Shadow Fox - 3 (141)
 * @author Nono
 */
public class Q00141_ShadowFoxPart3 extends Quest
{
	// NPCs
	private static final int NATOOLS = 30894;
	// Monsters
	private static final Map<Integer, Integer> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20135, 53); // Alligator
		MOBS.put(20791, 100); // Crokian Warrior
		MOBS.put(20792, 92); // Farhite
	}
	
	// Items
	private static final int PREDECESSORS_REPORT = 10350;
	// Misc
	private static final int MIN_LEVEL = 37;
	private static final int MAX_REWARD_LEVEL = 42;
	private static final int REPORT_COUNT = 30;
	
	public Q00141_ShadowFoxPart3()
	{
		super(141);
		addStartNpc(NATOOLS);
		addTalkId(NATOOLS);
		addKillId(MOBS.keySet());
		registerQuestItems(PREDECESSORS_REPORT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = event;
		switch (event)
		{
			case "30894-05.html":
			case "30894-10.html":
			case "30894-11.html":
			case "30894-12.html":
			case "30894-13.html":
			case "30894-14.html":
			case "30894-16.html":
			case "30894-17.html":
			case "30894-19.html":
			case "30894-20.html":
			{
				break;
			}
			case "30894-03.htm":
			{
				qs.startQuest();
				break;
			}
			case "30894-06.html":
			{
				qs.setCond(2, true);
				break;
			}
			case "30894-15.html":
			{
				qs.set("talk", "2");
				break;
			}
			case "30894-18.html":
			{
				qs.setCond(4, true);
				qs.unset("talk");
				break;
			}
			case "30894-21.html":
			{
				giveAdena(player, 88888, true);
				if (player.getLevel() <= MAX_REWARD_LEVEL)
				{
					addExpAndSp(player, 278005, 17058);
				}
				qs.exitQuest(false, true);
				
				final Quest q = QuestManager.getInstance().getQuest(Q00998_FallenAngelSelect.class.getSimpleName());
				if (q != null)
				{
					q.newQuestState(player).setState(State.STARTED);
				}
				break;
			}
			default:
			{
				htmltext = null;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance member = getRandomPartyMember(player, 2);
		if (member == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		final QuestState qs = getQuestState(member, false);
		if (getRandom(100) < MOBS.get(npc.getId()))
		{
			giveItems(member, PREDECESSORS_REPORT, 1);
			if (getQuestItemsCount(member, PREDECESSORS_REPORT) >= REPORT_COUNT)
			{
				qs.setCond(3, true);
			}
			else
			{
				playSound(member, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				final QuestState qst = player.getQuestState(Q00140_ShadowFoxPart2.class.getSimpleName());
				htmltext = (player.getLevel() >= MIN_LEVEL) ? ((qst != null) && qst.isCompleted()) ? "30894-01.htm" : "30894-00.html" : "30894-02.htm";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "30894-04.html";
						break;
					}
					case 2:
					{
						htmltext = "30894-07.html";
						break;
					}
					case 3:
					{
						if (qs.getInt("talk") == 1)
						{
							htmltext = "30894-09.html";
						}
						else if (qs.getInt("talk") == 2)
						{
							htmltext = "30894-16.html";
						}
						else
						{
							htmltext = "30894-08.html";
							takeItems(player, PREDECESSORS_REPORT, -1);
							qs.set("talk", "1");
						}
						break;
					}
					case 4:
					{
						htmltext = "30894-19.html";
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
		}
		return htmltext;
	}
}
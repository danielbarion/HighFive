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
package quests.Q00044_HelpTheSon;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Help The Son! (44)<br>
 * Original Jython script by zerghase.
 * @author malyelfik
 */
public class Q00044_HelpTheSon extends Quest
{
	// NPCs
	private static final int LUNDY = 30827;
	private static final int DRIKUS = 30505;
	// Monsters
	private static final int MAILLE_GUARD = 20921;
	private static final int MAILLE_SCOUT = 20920;
	private static final int MAILLE_LIZARDMAN = 20919;
	// Items
	private static final int WORK_HAMMER = 168;
	private static final int GEMSTONE_FRAGMENT = 7552;
	private static final int GEMSTONE = 7553;
	private static final int PET_TICKET = 7585;
	
	public Q00044_HelpTheSon()
	{
		super(44);
		addStartNpc(LUNDY);
		addTalkId(LUNDY, DRIKUS);
		addKillId(MAILLE_GUARD, MAILLE_LIZARDMAN, MAILLE_SCOUT);
		registerQuestItems(GEMSTONE, GEMSTONE_FRAGMENT);
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
		switch (event)
		{
			case "30827-01.htm":
			{
				qs.startQuest();
				break;
			}
			case "30827-03.html":
			{
				if (hasQuestItems(player, WORK_HAMMER))
				{
					takeItems(player, WORK_HAMMER, 1);
					qs.setCond(2, true);
				}
				else
				{
					htmltext = "30827-03a.html";
				}
				break;
			}
			case "30827-06.html":
			{
				if (getQuestItemsCount(player, GEMSTONE_FRAGMENT) == 30)
				{
					takeItems(player, GEMSTONE_FRAGMENT, -1);
					giveItems(player, GEMSTONE, 1);
					qs.setCond(4, true);
				}
				else
				{
					htmltext = "30827-06a.html";
				}
				break;
			}
			case "30505-02.html":
			{
				if (hasQuestItems(player, GEMSTONE))
				{
					takeItems(player, GEMSTONE, -1);
					qs.setCond(5, true);
				}
				else
				{
					htmltext = "30505-02a.html";
				}
				break;
			}
			case "30827-09.html":
			{
				giveItems(player, PET_TICKET, 1);
				qs.exitQuest(false, true);
				break;
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isCond(2))
		{
			giveItems(player, GEMSTONE_FRAGMENT, 1);
			if (getQuestItemsCount(player, GEMSTONE_FRAGMENT) == 30)
			{
				qs.setCond(3, true);
			}
			else
			{
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case LUNDY:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= 24) ? "30827-00.htm" : "30827-00a.html";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = hasQuestItems(player, WORK_HAMMER) ? "30827-02.html" : "30827-02a.html";
								break;
							}
							case 2:
							{
								htmltext = "30827-04.html";
								break;
							}
							case 3:
							{
								htmltext = "30827-05.html";
								break;
							}
							case 4:
							{
								htmltext = "30827-07.html";
								break;
							}
							case 5:
							{
								htmltext = "30827-08.html";
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
				break;
			}
			case DRIKUS:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 4:
						{
							htmltext = "30505-01.html";
							break;
						}
						case 5:
						{
							htmltext = "30505-03.html";
							break;
						}
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

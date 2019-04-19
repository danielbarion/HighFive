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
package quests.Q00042_HelpTheUncle;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Help The Uncle! (42)<br>
 * Original Jython script by zerghase.
 * @author malyelfik
 */
public class Q00042_HelpTheUncle extends Quest
{
	// NPCs
	private static final int WATERS = 30828;
	private static final int SOPHYA = 30735;
	// Monsters
	private static final int MONSTER_EYE_DESTROYER = 20068;
	private static final int MONSTER_EYE_GAZER = 20266;
	// Items
	private static final int TRIDENT = 291;
	private static final int MAP_PIECE = 7548;
	private static final int MAP = 7549;
	private static final int PET_TICKET = 7583;
	
	public Q00042_HelpTheUncle()
	{
		super(42);
		addStartNpc(WATERS);
		addTalkId(WATERS, SOPHYA);
		addKillId(MONSTER_EYE_DESTROYER, MONSTER_EYE_GAZER);
		registerQuestItems(MAP, MAP_PIECE);
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
			case "30828-01.htm":
			{
				qs.startQuest();
				break;
			}
			case "30828-03.html":
			{
				if (hasQuestItems(player, TRIDENT))
				{
					takeItems(player, TRIDENT, 1);
					qs.setCond(2, true);
				}
				else
				{
					htmltext = "30828-03a.html";
				}
				break;
			}
			case "30828-06.html":
			{
				if (getQuestItemsCount(player, MAP_PIECE) == 30)
				{
					takeItems(player, MAP_PIECE, -1);
					giveItems(player, MAP, 1);
					qs.setCond(4, true);
				}
				else
				{
					htmltext = "30828-06a.html";
				}
				break;
			}
			case "30735-02.html":
			{
				if (hasQuestItems(player, MAP))
				{
					takeItems(player, MAP, -1);
					qs.setCond(5, true);
				}
				else
				{
					htmltext = "30735-02a.html";
				}
				break;
			}
			case "30828-09.html":
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
			giveItems(player, MAP_PIECE, 1);
			if (getQuestItemsCount(player, MAP_PIECE) == 30)
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
			case WATERS:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= 25) ? "30828-00.htm" : "30828-00a.html";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = hasQuestItems(player, TRIDENT) ? "30828-02.html" : "30828-02a.html";
								break;
							}
							case 2:
							{
								htmltext = "30828-04.html";
								break;
							}
							case 3:
							{
								htmltext = "30828-05.html";
								break;
							}
							case 4:
							{
								htmltext = "30828-07.html";
								break;
							}
							case 5:
							{
								htmltext = "30828-08.html";
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
			case SOPHYA:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 4:
						{
							htmltext = "30735-01.html";
							break;
						}
						case 5:
						{
							htmltext = "30735-03.html";
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

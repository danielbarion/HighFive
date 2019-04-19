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
package quests.Q00137_TempleChampionPart1;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Temple Champion - 1 (137)
 * @author nonom
 */
public class Q00137_TempleChampionPart1 extends Quest
{
	// NPCs
	private static final int SYLVAIN = 30070;
	private static final int MOBS[] =
	{
		20083, // Granite Golem
		20144, // Hangman Tree
		20199, // Amber Basilisk
		20200, // Strain
		20201, // Ghoul
		20202, // Dead Seeker
	};
	// Items
	private static final int FRAGMENT = 10340;
	private static final int EXECUTOR = 10334;
	private static final int MISSIONARY = 10339;
	
	public Q00137_TempleChampionPart1()
	{
		super(137);
		addStartNpc(SYLVAIN);
		addTalkId(SYLVAIN);
		addKillId(MOBS);
		registerQuestItems(FRAGMENT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		switch (event)
		{
			case "30070-02.htm":
			{
				qs.startQuest();
				break;
			}
			case "30070-05.html":
			{
				qs.set("talk", "1");
				break;
			}
			case "30070-06.html":
			{
				qs.set("talk", "2");
				break;
			}
			case "30070-08.html":
			{
				qs.unset("talk");
				qs.setCond(2, true);
				break;
			}
			case "30070-16.html":
			{
				if (qs.isCond(3) && hasQuestItems(player, EXECUTOR) && hasQuestItems(player, MISSIONARY))
				{
					takeItems(player, EXECUTOR, -1);
					takeItems(player, MISSIONARY, -1);
					giveAdena(player, 69146, true);
					if (player.getLevel() < 41)
					{
						addExpAndSp(player, 219975, 13047);
					}
					qs.exitQuest(false, true);
				}
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isStarted() && qs.isCond(2) && (getQuestItemsCount(player, FRAGMENT) < 30))
		{
			giveItems(player, FRAGMENT, 1);
			if (getQuestItemsCount(player, FRAGMENT) >= 30)
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
		
		if (qs.isCompleted())
		{
			return getAlreadyCompletedMsg(player);
		}
		switch (qs.getCond())
		{
			case 1:
			{
				switch (qs.getInt("talk"))
				{
					case 1:
					{
						htmltext = "30070-05.html";
						break;
					}
					case 2:
					{
						htmltext = "30070-06.html";
						break;
					}
					default:
					{
						htmltext = "30070-03.html";
						break;
					}
				}
				break;
			}
			case 2:
			{
				htmltext = "30070-08.html";
				break;
			}
			case 3:
			{
				if (qs.getInt("talk") == 1)
				{
					htmltext = "30070-10.html";
				}
				else if (getQuestItemsCount(player, FRAGMENT) >= 30)
				{
					qs.set("talk", "1");
					htmltext = "30070-09.html";
					takeItems(player, FRAGMENT, -1);
				}
				break;
			}
			default:
			{
				htmltext = ((player.getLevel() >= 35) && hasQuestItems(player, EXECUTOR, MISSIONARY)) ? "30070-01.htm" : "30070-00.html";
				break;
			}
		}
		return htmltext;
	}
}

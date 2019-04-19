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
package quests.Q00278_HomeSecurity;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Home Security (278)
 * @author malyelfik
 */
public class Q00278_HomeSecurity extends Quest
{
	// NPC
	private static final int TUNATUN = 31537;
	private static final int[] MONSTER =
	{
		18905,
		18906,
		18907
	};
	// Item
	private static final int SEL_MAHUM_MANE = 15531;
	// Misc
	private static final int SEL_MAHUM_MANE_COUNT = 300;
	
	public Q00278_HomeSecurity()
	{
		super(278);
		addStartNpc(TUNATUN);
		addTalkId(TUNATUN);
		addKillId(MONSTER);
		registerQuestItems(SEL_MAHUM_MANE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "31537-02.htm":
			{
				htmltext = (player.getLevel() >= 82) ? "31537-02.htm" : "31537-03.html";
				break;
			}
			case "31537-04.htm":
			{
				qs.startQuest();
				break;
			}
			case "31537-07.html":
			{
				final int i0 = getRandom(100);
				
				if (i0 < 10)
				{
					giveItems(player, 960, 1);
				}
				else if (i0 < 19)
				{
					giveItems(player, 960, 2);
				}
				else if (i0 < 27)
				{
					giveItems(player, 960, 3);
				}
				else if (i0 < 34)
				{
					giveItems(player, 960, 4);
				}
				else if (i0 < 40)
				{
					giveItems(player, 960, 5);
				}
				else if (i0 < 45)
				{
					giveItems(player, 960, 6);
				}
				else if (i0 < 49)
				{
					giveItems(player, 960, 7);
				}
				else if (i0 < 52)
				{
					giveItems(player, 960, 8);
				}
				else if (i0 < 54)
				{
					giveItems(player, 960, 9);
				}
				else if (i0 < 55)
				{
					giveItems(player, 960, 10);
				}
				else if (i0 < 75)
				{
					giveItems(player, 9553, 1);
				}
				else if (i0 < 90)
				{
					giveItems(player, 9553, 2);
				}
				else
				{
					giveItems(player, 959, 1);
				}
				
				qs.exitQuest(true, true);
				htmltext = "31537-07.html";
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(player, 1, 3, npc);
		if (qs != null)
		{
			switch (npc.getId())
			{
				case 18905: // Farm Ravager (Crazy)
				{
					final int itemCount = (getRandom(1000) < 486) ? getRandom(6) + 1 : getRandom(5) + 1;
					if (giveItemRandomly(qs.getPlayer(), npc, SEL_MAHUM_MANE, itemCount, SEL_MAHUM_MANE_COUNT, 1.0, true))
					{
						qs.setCond(2, true);
					}
					break;
				}
				case 18906: // Farm Bandit
				case 18907: // Beast Devourer
				{
					if (giveItemRandomly(qs.getPlayer(), npc, SEL_MAHUM_MANE, 1, SEL_MAHUM_MANE_COUNT, 0.85, true))
					{
						qs.setCond(2, true);
					}
					break;
				}
			}
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isCreated())
		{
			htmltext = "31537-01.htm";
		}
		else if (qs.isStarted())
		{
			if (qs.isCond(1) || (getQuestItemsCount(player, SEL_MAHUM_MANE) < SEL_MAHUM_MANE_COUNT))
			{
				htmltext = "31537-06.html";
			}
			else if (qs.isCond(2) && (getQuestItemsCount(player, SEL_MAHUM_MANE) >= SEL_MAHUM_MANE_COUNT))
			{
				htmltext = "31537-05.html";
			}
		}
		return htmltext;
	}
}

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
package quests.Q00252_ItSmellsDelicious;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * It Smells Delicious! (252)<br>
 * Updated by corbin12, thanks VlLight for help.
 * @author Dumpster, jurchiks
 */
public class Q00252_ItSmellsDelicious extends Quest
{
	// NPC
	public static final int STAN = 30200;
	// Items
	public static final int DIARY = 15500;
	public static final int COOKBOOK_PAGE = 15501;
	// Monsters
	private static final int[] MOBS =
	{
		22786,
		22787,
		22788
	};
	private static final int CHEF = 18908;
	// Misc
	private static final double DIARY_CHANCE = 0.599;
	private static final int DIARY_MAX_COUNT = 10;
	private static final double COOKBOOK_PAGE_CHANCE = 0.36;
	private static final int COOKBOOK_PAGE_MAX_COUNT = 5;
	
	public Q00252_ItSmellsDelicious()
	{
		super(252);
		addStartNpc(STAN);
		addTalkId(STAN);
		addKillId(CHEF);
		addKillId(MOBS);
		registerQuestItems(DIARY, COOKBOOK_PAGE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "30200-04.htm":
			{
				htmltext = event;
				break;
			}
			case "30200-05.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30200-08.html":
			{
				if (qs.isCond(2))
				{
					giveAdena(player, 147656, true);
					addExpAndSp(player, 716238, 78324);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs;
		if (npc.getId() == CHEF) // only the killer gets quest items from the chef
		{
			qs = getQuestState(killer, false);
			if ((qs != null) && qs.isCond(1))
			{
				if (giveItemRandomly(killer, npc, COOKBOOK_PAGE, 1, COOKBOOK_PAGE_MAX_COUNT, COOKBOOK_PAGE_CHANCE, true))
				{
					if (hasMaxDiaries(qs))
					{
						qs.setCond(2, true);
					}
				}
			}
		}
		else
		{
			qs = getRandomPartyMemberState(killer, 1, 3, npc);
			if (qs != null)
			{
				if (giveItemRandomly(qs.getPlayer(), npc, DIARY, 1, DIARY_MAX_COUNT, DIARY_CHANCE, true))
				{
					if (hasMaxCookbookPages(qs))
					{
						qs.setCond(2, true);
					}
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public boolean checkPartyMember(QuestState qs, L2Npc npc)
	{
		return !hasMaxDiaries(qs);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isCreated())
		{
			htmltext = ((player.getLevel() >= 82) ? "30200-01.htm" : "30200-02.htm");
		}
		else if (qs.isStarted())
		{
			switch (qs.getCond())
			{
				case 1:
				{
					htmltext = "30200-06.html";
					break;
				}
				case 2:
				{
					if (hasMaxDiaries(qs) && hasMaxCookbookPages(qs))
					{
						htmltext = "30200-07.html";
					}
					break;
				}
			}
		}
		else
		{
			htmltext = "30200-03.html";
		}
		return htmltext;
	}
	
	private static boolean hasMaxDiaries(QuestState qs)
	{
		return (getQuestItemsCount(qs.getPlayer(), DIARY) >= DIARY_MAX_COUNT);
	}
	
	private static boolean hasMaxCookbookPages(QuestState qs)
	{
		return (getQuestItemsCount(qs.getPlayer(), COOKBOOK_PAGE) >= COOKBOOK_PAGE_MAX_COUNT);
	}
}

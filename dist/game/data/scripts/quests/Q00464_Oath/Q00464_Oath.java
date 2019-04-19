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
package quests.Q00464_Oath;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Oath (464)
 * @author malyelfik
 */
public class Q00464_Oath extends Quest
{
	// NPCs @formatter:off
	private static final int[][] NPC =
	{
		// NPC id, EXP, SP, Adena
		{32596,	0, 0, 0},
		{30657,	15449, 17696, 42910},
		{30839, 189377, 21692, 52599},
		{30899,	249180, 28542, 69210},
		{31350, 249180, 28542, 69210},
		{30539,	19408, 47062, 169442},
		{30297,	24146, 58551, 210806},
		{31960,	15449, 17696, 42910},
		{31588,	15449, 17696, 42910}
	};
	// @formatter:on
	
	// Items
	private static final int STRONGBOX = 15537;
	private static final int BOOK = 15538;
	private static final int BOOK2 = 15539;
	// Misc
	private static final int MIN_LEVEL = 82;
	
	// Monsters
	private static final Map<Integer, Integer> MOBS = new HashMap<>();
	static
	{
		MOBS.put(22799, 9);
		MOBS.put(22794, 6);
		MOBS.put(22800, 10);
		MOBS.put(22796, 9);
		MOBS.put(22798, 9);
		MOBS.put(22795, 8);
		MOBS.put(22797, 7);
		MOBS.put(22789, 5);
		MOBS.put(22791, 4);
		MOBS.put(22790, 5);
		MOBS.put(22792, 4);
		MOBS.put(22793, 5);
	}
	
	public Q00464_Oath()
	{
		super(464);
		for (int[] npc : NPC)
		{
			addTalkId(npc[0]);
		}
		addKillId(MOBS.keySet());
		addItemTalkId(STRONGBOX);
		registerQuestItems(BOOK, BOOK2);
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
			case "32596-04.html":
			{
				if (!hasQuestItems(player, BOOK))
				{
					return getNoQuestMsg(player);
				}
				
				final int cond = getRandom(2, 9);
				qs.set("npc", String.valueOf(NPC[cond - 1][0]));
				qs.setCond(cond, true);
				takeItems(player, BOOK, 1);
				giveItems(player, BOOK2, 1);
				switch (cond)
				{
					case 2:
					{
						htmltext = "32596-04.html";
						break;
					}
					case 3:
					{
						htmltext = "32596-04a.html";
						break;
					}
					case 4:
					{
						htmltext = "32596-04b.html";
						break;
					}
					case 5:
					{
						htmltext = "32596-04c.html";
						break;
					}
					case 6:
					{
						htmltext = "32596-04d.html";
						break;
					}
					case 7:
					{
						htmltext = "32596-04e.html";
						break;
					}
					case 8:
					{
						htmltext = "32596-04f.html";
						break;
					}
					case 9:
					{
						htmltext = "32596-04g.html";
						break;
					}
				}
				break;
			}
			case "end_quest":
			{
				if (!hasQuestItems(player, BOOK2))
				{
					return getNoQuestMsg(player);
				}
				
				final int i = qs.getCond() - 1;
				addExpAndSp(player, NPC[i][1], NPC[i][2]);
				giveAdena(player, NPC[i][3], true);
				qs.exitQuest(QuestType.DAILY, true);
				htmltext = npc.getId() + "-02.html";
				break;
			}
			case "32596-02.html":
			case "32596-03.html":
			{
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
	public String onItemTalk(L2ItemInstance item, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		boolean startQuest = false;
		switch (qs.getState())
		{
			case State.CREATED:
			{
				startQuest = true;
				break;
			}
			case State.STARTED:
			{
				htmltext = "strongbox-02.html";
				break;
			}
			case State.COMPLETED:
			{
				if (qs.isNowAvailable())
				{
					qs.setState(State.CREATED);
					startQuest = true;
				}
				else
				{
					htmltext = "strongbox-03.html";
				}
				break;
			}
		}
		
		if (startQuest)
		{
			if (player.getLevel() >= MIN_LEVEL)
			{
				qs.startQuest();
				takeItems(player, STRONGBOX, 1);
				giveItems(player, BOOK, 1);
				htmltext = "strongbox-01.htm";
			}
			else
			{
				htmltext = "strongbox-00.htm";
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (getRandom(1000) < MOBS.get(npc.getId()))
		{
			npc.dropItem(killer, STRONGBOX, 1);
		}
		
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isStarted())
		{
			final int npcId = npc.getId();
			
			if (npcId == NPC[0][0])
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "32596-01.html";
						break;
					}
					case 2:
					{
						htmltext = "32596-05.html";
						break;
					}
					case 3:
					{
						htmltext = "32596-05a.html";
						break;
					}
					case 4:
					{
						htmltext = "32596-05b.html";
						break;
					}
					case 5:
					{
						htmltext = "32596-05c.html";
						break;
					}
					case 6:
					{
						htmltext = "32596-05d.html";
						break;
					}
					case 7:
					{
						htmltext = "32596-05e.html";
						break;
					}
					case 8:
					{
						htmltext = "32596-05f.html";
						break;
					}
					case 9:
					{
						htmltext = "32596-05g.html";
						break;
					}
				}
			}
			else if ((qs.getCond() > 1) && (qs.getInt("npc") == npcId))
			{
				htmltext = npcId + "-01.html";
			}
		}
		return htmltext;
	}
}
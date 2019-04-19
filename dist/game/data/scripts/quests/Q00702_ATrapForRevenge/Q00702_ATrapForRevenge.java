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
package quests.Q00702_ATrapForRevenge;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q10273_GoodDayToFly.Q10273_GoodDayToFly;

/**
 * A Trap for Revenge (702)
 * @author malyelfik
 */
public class Q00702_ATrapForRevenge extends Quest
{
	// NPC
	private static final int PLENOS = 32563;
	private static final int LEKON = 32557;
	private static final int TENIUS = 32555;
	private static final int[] MONSTERS =
	{
		22612,
		22613,
		25632,
		22610,
		22611,
		25631,
		25626
	};
	// Items
	private static final int DRAKES_FLESH = 13877;
	private static final int ROTTEN_BLOOD = 13878;
	private static final int BAIT_FOR_DRAKES = 13879;
	private static final int VARIANT_DRAKE_WING_HORNS = 13880;
	private static final int EXTRACTED_RED_STAR_STONE = 14009;
	
	public Q00702_ATrapForRevenge()
	{
		super(702);
		addStartNpc(PLENOS);
		addTalkId(PLENOS, LEKON, TENIUS);
		addKillId(MONSTERS);
		registerQuestItems(DRAKES_FLESH, ROTTEN_BLOOD, BAIT_FOR_DRAKES, VARIANT_DRAKE_WING_HORNS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		if (event.equalsIgnoreCase("32563-04.htm"))
		{
			st.startQuest();
		}
		else if (event.equalsIgnoreCase("32563-07.html"))
		{
			htmltext = hasQuestItems(player, DRAKES_FLESH) ? "32563-08.html" : "32563-07.html";
		}
		else if (event.equalsIgnoreCase("32563-09.html"))
		{
			giveAdena(player, getQuestItemsCount(player, DRAKES_FLESH) * 100, false);
			takeItems(player, DRAKES_FLESH, -1);
		}
		else if (event.equalsIgnoreCase("32563-11.html"))
		{
			if (hasQuestItems(player, VARIANT_DRAKE_WING_HORNS))
			{
				giveAdena(player, getQuestItemsCount(player, VARIANT_DRAKE_WING_HORNS) * 200000, false);
				takeItems(player, VARIANT_DRAKE_WING_HORNS, -1);
				htmltext = "32563-12.html";
			}
			else
			{
				htmltext = "32563-11.html";
			}
		}
		else if (event.equalsIgnoreCase("32563-14.html"))
		{
			st.exitQuest(true, true);
		}
		else if (event.equalsIgnoreCase("32557-03.html"))
		{
			if (!hasQuestItems(player, ROTTEN_BLOOD) && (getQuestItemsCount(player, EXTRACTED_RED_STAR_STONE) < 100))
			{
				htmltext = "32557-03.html";
			}
			else if (hasQuestItems(player, ROTTEN_BLOOD) && (getQuestItemsCount(player, EXTRACTED_RED_STAR_STONE) < 100))
			{
				htmltext = "32557-04.html";
			}
			else if (!hasQuestItems(player, ROTTEN_BLOOD) && (getQuestItemsCount(player, EXTRACTED_RED_STAR_STONE) >= 100))
			{
				htmltext = "32557-05.html";
			}
			else if (hasQuestItems(player, ROTTEN_BLOOD) && (getQuestItemsCount(player, EXTRACTED_RED_STAR_STONE) >= 100))
			{
				giveItems(player, BAIT_FOR_DRAKES, 1);
				takeItems(player, ROTTEN_BLOOD, 1);
				takeItems(player, EXTRACTED_RED_STAR_STONE, 100);
				htmltext = "32557-06.html";
			}
		}
		else if (event.equalsIgnoreCase("32555-03.html"))
		{
			st.setCond(2, true);
		}
		else if (event.equalsIgnoreCase("32555-05.html"))
		{
			st.exitQuest(true, true);
		}
		else if (event.equalsIgnoreCase("32555-06.html"))
		{
			if (getQuestItemsCount(player, DRAKES_FLESH) < 100)
			{
				htmltext = "32555-06.html";
			}
			else
			{
				htmltext = "32555-07.html";
			}
		}
		else if (event.equalsIgnoreCase("32555-08.html"))
		{
			giveItems(player, ROTTEN_BLOOD, 1);
			takeItems(player, DRAKES_FLESH, 100);
		}
		else if (event.equalsIgnoreCase("32555-10.html"))
		{
			if (hasQuestItems(player, VARIANT_DRAKE_WING_HORNS))
			{
				htmltext = "32555-11.html";
			}
			else
			{
				htmltext = "32555-10.html";
			}
		}
		else if (event.equalsIgnoreCase("32555-15.html"))
		{
			final int i0 = getRandom(1000);
			final int i1 = getRandom(1000);
			
			if ((i0 >= 500) && (i1 >= 600))
			{
				giveAdena(player, getRandom(49917) + 125000, false);
				if (i1 < 720)
				{
					giveItems(player, 9628, getRandom(3) + 1);
					giveItems(player, 9629, getRandom(3) + 1);
				}
				else if (i1 < 840)
				{
					giveItems(player, 9629, getRandom(3) + 1);
					giveItems(player, 9630, getRandom(3) + 1);
				}
				else if (i1 < 960)
				{
					giveItems(player, 9628, getRandom(3) + 1);
					giveItems(player, 9630, getRandom(3) + 1);
				}
				else if (i1 < 1000)
				{
					giveItems(player, 9628, getRandom(3) + 1);
					giveItems(player, 9629, getRandom(3) + 1);
					giveItems(player, 9630, getRandom(3) + 1);
				}
				htmltext = "32555-15.html";
			}
			else if ((i0 >= 500) && (i1 < 600))
			{
				giveAdena(player, getRandom(49917) + 125000, false);
				if (i1 < 210)
				{
				}
				else if (i1 < 340)
				{
					giveItems(player, 9628, getRandom(3) + 1);
				}
				else if (i1 < 470)
				{
					giveItems(player, 9629, getRandom(3) + 1);
				}
				else if (i1 < 600)
				{
					giveItems(player, 9630, getRandom(3) + 1);
				}
				
				htmltext = "32555-16.html";
			}
			else if ((i0 < 500) && (i1 >= 600))
			{
				giveAdena(player, getRandom(49917) + 25000, false);
				if (i1 < 720)
				{
					giveItems(player, 9628, getRandom(3) + 1);
					giveItems(player, 9629, getRandom(3) + 1);
				}
				else if (i1 < 840)
				{
					giveItems(player, 9629, getRandom(3) + 1);
					giveItems(player, 9630, getRandom(3) + 1);
				}
				else if (i1 < 960)
				{
					giveItems(player, 9628, getRandom(3) + 1);
					giveItems(player, 9630, getRandom(3) + 1);
				}
				else if (i1 < 1000)
				{
					giveItems(player, 9628, getRandom(3) + 1);
					giveItems(player, 9629, getRandom(3) + 1);
					giveItems(player, 9630, getRandom(3) + 1);
				}
				htmltext = "32555-17.html";
			}
			else if ((i0 < 500) && (i1 < 600))
			{
				giveAdena(player, getRandom(49917) + 25000, false);
				if (i1 < 210)
				{
				}
				else if (i1 < 340)
				{
					giveItems(player, 9628, getRandom(3) + 1);
				}
				else if (i1 < 470)
				{
					giveItems(player, 9629, getRandom(3) + 1);
				}
				else if (i1 < 600)
				{
					giveItems(player, 9630, getRandom(3) + 1);
				}
				
				htmltext = "32555-18.html";
			}
			takeItems(player, VARIANT_DRAKE_WING_HORNS, 1);
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 2);
		if (partyMember == null)
		{
			return null;
		}
		final int chance = getRandom(1000);
		switch (npc.getId())
		{
			case 22612:
			{
				if (chance < 413)
				{
					giveItems(player, DRAKES_FLESH, 2);
				}
				else
				{
					giveItems(player, DRAKES_FLESH, 1);
				}
				break;
			}
			case 22613:
			{
				if (chance < 440)
				{
					giveItems(player, DRAKES_FLESH, 2);
				}
				else
				{
					giveItems(player, DRAKES_FLESH, 1);
				}
				break;
			}
			case 25632:
			{
				if (chance < 996)
				{
					giveItems(player, DRAKES_FLESH, 1);
				}
				break;
			}
			case 22610:
			{
				if (chance < 485)
				{
					giveItems(player, DRAKES_FLESH, 2);
				}
				else
				{
					giveItems(player, DRAKES_FLESH, 1);
				}
				break;
			}
			case 22611:
			{
				if (chance < 451)
				{
					giveItems(player, DRAKES_FLESH, 2);
				}
				else
				{
					giveItems(player, DRAKES_FLESH, 1);
				}
				break;
			}
			case 25631:
			{
				if (chance < 485)
				{
					giveItems(player, DRAKES_FLESH, 2);
				}
				else
				{
					giveItems(player, DRAKES_FLESH, 1);
				}
				break;
			}
			case 25626:
			{
				int count = 0;
				if (chance < 708)
				{
					count = getRandom(2) + 1;
				}
				else if (chance < 978)
				{
					count = getRandom(3) + 3;
				}
				else if (chance < 994)
				{
					count = getRandom(4) + 6;
				}
				else if (chance < 998)
				{
					count = getRandom(4) + 10;
				}
				else if (chance < 1000)
				{
					count = getRandom(5) + 14;
				}
				giveItems(player, VARIANT_DRAKE_WING_HORNS, count);
				break;
			}
		}
		playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (npc.getId() == PLENOS)
		{
			switch (st.getState())
			{
				case State.CREATED:
				{
					final QuestState prev = player.getQuestState(Q10273_GoodDayToFly.class.getSimpleName());
					htmltext = ((prev != null) && prev.isCompleted() && (player.getLevel() >= 78)) ? "32563-01.htm" : "32563-02.htm";
					break;
				}
				case State.STARTED:
				{
					htmltext = (st.isCond(1)) ? "32563-05.html" : "32563-06.html";
					break;
				}
			}
		}
		if (st.getState() == State.STARTED)
		{
			if (npc.getId() == LEKON)
			{
				switch (st.getCond())
				{
					case 1:
					{
						htmltext = "32557-01.html";
						break;
					}
					case 2:
					{
						htmltext = "32557-02.html";
						break;
					}
				}
			}
			else if (npc.getId() == TENIUS)
			{
				switch (st.getCond())
				{
					case 1:
					{
						htmltext = "32555-01.html";
						break;
					}
					case 2:
					{
						htmltext = "32555-04.html";
						break;
					}
				}
			}
		}
		return htmltext;
	}
}

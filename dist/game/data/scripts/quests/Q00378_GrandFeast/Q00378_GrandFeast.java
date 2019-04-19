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
package quests.Q00378_GrandFeast;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Grand Feast (378)
 * @author Adry_85
 */
public final class Q00378_GrandFeast extends Quest
{
	// NPC
	private static final int RANSPO = 30594;
	// Items
	private static final int JONAS_SALAD_RECIPE = 1455;
	private static final int JONAS_SAUCE_RECIPE = 1456;
	private static final int JONAS_STEAK_RECIPE = 1457;
	private static final int THEME_OF_THE_FEAST = 4421;
	private static final int OLD_WINE_15_YEAR = 5956;
	private static final int OLD_WINE_30_YEAR = 5957;
	private static final int OLD_WINE_60_YEAR = 5958;
	private static final int RITRONS_DESSERT_RECIPE = 5959;
	// Rewards
	private static final int CORAL_EARRING = 846;
	private static final int RED_CRESCENT_EARRING = 847;
	private static final int ENCHANTED_EARRING = 848;
	private static final int ENCHANTED_RING = 879;
	private static final int RING_OF_DEVOTION = 890;
	private static final int BLUE_DIAMOND_NECKLACE = 909;
	private static final int NECKLACE_OF_DEVOTION = 910;
	// Misc
	private static final int MIN_LEVEL = 20;
	
	public Q00378_GrandFeast()
	{
		super(378);
		addStartNpc(RANSPO);
		addTalkId(RANSPO);
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
			case "30594-02.htm":
			{
				qs.setMemoStateEx(1, 0);
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "30594-05.html":
			{
				final int i0 = qs.getMemoStateEx(1);
				if (hasQuestItems(player, OLD_WINE_15_YEAR))
				{
					takeItems(player, OLD_WINE_15_YEAR, 1);
					qs.setMemoStateEx(1, i0 + 10);
					qs.setCond(2, true);
					htmltext = event;
				}
				else
				{
					htmltext = "30594-08.html";
				}
				break;
			}
			case "30594-06.html":
			{
				final int i0 = qs.getMemoStateEx(1);
				if (hasQuestItems(player, OLD_WINE_30_YEAR))
				{
					takeItems(player, OLD_WINE_30_YEAR, 1);
					qs.setMemoStateEx(1, i0 + 20);
					qs.setCond(2, true);
					htmltext = event;
				}
				else
				{
					htmltext = "30594-08.html";
				}
				break;
			}
			case "30594-07.html":
			{
				final int i0 = qs.getMemoStateEx(1);
				if (hasQuestItems(player, OLD_WINE_60_YEAR))
				{
					takeItems(player, OLD_WINE_60_YEAR, 1);
					qs.setMemoStateEx(1, i0 + 30);
					qs.setCond(2, true);
					htmltext = event;
				}
				else
				{
					htmltext = "30594-08.html";
				}
				break;
			}
			case "30594-09.html":
			case "30594-18.html":
			{
				htmltext = event;
				break;
			}
			case "30594-12.html":
			{
				if (hasQuestItems(player, THEME_OF_THE_FEAST))
				{
					takeItems(player, THEME_OF_THE_FEAST, 1);
					qs.setCond(3, true);
					htmltext = event;
				}
				else
				{
					htmltext = "30594-08.html";
				}
				break;
			}
			case "30594-14.html":
			{
				final int i0 = qs.getMemoStateEx(1);
				if (hasQuestItems(player, JONAS_SALAD_RECIPE))
				{
					takeItems(player, JONAS_SALAD_RECIPE, 1);
					qs.setMemoStateEx(1, i0 + 1);
					qs.setCond(4, true);
					htmltext = event;
				}
				else
				{
					htmltext = "30594-17.html";
				}
				break;
			}
			case "30594-15.html":
			{
				final int i0 = qs.getMemoStateEx(1);
				if (hasQuestItems(player, JONAS_SAUCE_RECIPE))
				{
					takeItems(player, JONAS_SAUCE_RECIPE, 1);
					qs.setMemoStateEx(1, i0 + 2);
					qs.setCond(4, true);
					htmltext = event;
				}
				else
				{
					htmltext = "30594-17.html";
				}
				break;
			}
			case "30594-16.html":
			{
				final int i0 = qs.getMemoStateEx(1);
				if (hasQuestItems(player, JONAS_STEAK_RECIPE))
				{
					takeItems(player, JONAS_STEAK_RECIPE, 1);
					qs.setMemoStateEx(1, i0 + 3);
					qs.setCond(4, true);
					htmltext = event;
				}
				else
				{
					htmltext = "30594-17.html";
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
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "30594-01.htm" : "30594-03.html";
		}
		else if (qs.isStarted())
		{
			switch (qs.getCond())
			{
				case 1:
				{
					htmltext = "30594-04.html";
					break;
				}
				case 2:
				{
					htmltext = (hasQuestItems(player, THEME_OF_THE_FEAST)) ? "30594-11.html" : "30594-10.html";
					break;
				}
				case 3:
				{
					htmltext = "30594-13.html";
					break;
				}
				case 4:
				{
					if (hasQuestItems(player, RITRONS_DESSERT_RECIPE))
					{
						takeItems(player, RITRONS_DESSERT_RECIPE, 1);
						int item = 0;
						int adena = 0;
						long quantity = 0;
						switch (qs.getMemoStateEx(1))
						{
							case 11:
							{
								item = RED_CRESCENT_EARRING;
								quantity = 1;
								adena = 5700;
								break;
							}
							case 12:
							{
								item = CORAL_EARRING;
								quantity = 2;
								adena = 1200;
								break;
							}
							case 13:
							{
								item = ENCHANTED_RING;
								quantity = 1;
								adena = 8100;
								break;
							}
							case 21:
							{
								item = CORAL_EARRING;
								quantity = 2;
								break;
							}
							case 22:
							{
								item = ENCHANTED_RING;
								quantity = 1;
								adena = 6900;
								break;
							}
							case 23:
							{
								item = NECKLACE_OF_DEVOTION;
								quantity = 1;
								break;
							}
							case 31:
							{
								item = BLUE_DIAMOND_NECKLACE;
								quantity = 1;
								adena = 25400;
								break;
							}
							case 32:
							{
								item = RING_OF_DEVOTION;
								quantity = 2;
								adena = 8500;
								break;
							}
							case 33:
							{
								item = ENCHANTED_EARRING;
								quantity = 1;
								adena = 2200;
								break;
							}
						}
						giveItems(player, item, quantity);
						giveAdena(player, adena, true);
						qs.exitQuest(true, true);
						htmltext = "30594-20.html";
					}
					else
					{
						htmltext = "30594-19.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

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
package quests.Q00343_UnderTheShadowOfTheIvoryTower;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.CategoryType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Under The Shadow Of The Ivory Tower (343)
 * @author ivantotov
 */
public final class Q00343_UnderTheShadowOfTheIvoryTower extends Quest
{
	// NPCs
	private static final int MAGIC_TRADER_CEMA = 30834;
	private static final int LICH_KING_ICARUS = 30835;
	private static final int COLLECTOR_MARSHA = 30934;
	private static final int COLLECTOR_TRUMPIN = 30935;
	// Item
	private static final int NEBULITE_ORB = 4364;
	// Rewards
	private static final int TOWER_SHIELD = 103;
	private static final int NICKLACE_OF_MAGIC = 118;
	private static final int SAGES_BLOOD = 316;
	private static final int SQUARE_SHIELD = 630;
	private static final int SCROLL_OF_ESCAPE = 736;
	private static final int RING_OF_AGES = 885;
	private static final int NICKLACE_OF_MERMAID = 917;
	private static final int SCROLL_ENCHANT_WEAPON_C_GRADE = 951;
	private static final int SCROLL_ENCHANT_WEAPON_D_GRADE = 955;
	private static final int SPIRITSHOT_D_GRADE = 2510;
	private static final int SPIRITSHOT_C_GRADE = 2511;
	private static final int ECTOPLASM_LIQUEUR = 4365;
	// Monster
	private static final int MANASHEN_GARGOYLE = 20563;
	private static final int ENCHANTED_MONSTEREYE = 20564;
	private static final int ENCHANTED_STONE_GOLEM = 20565;
	private static final int ENCHANTED_IRON_GOLEM = 20566;
	// Misc
	private static final int MIN_LEVEL = 40;
	
	public Q00343_UnderTheShadowOfTheIvoryTower()
	{
		super(343);
		addStartNpc(MAGIC_TRADER_CEMA);
		addTalkId(MAGIC_TRADER_CEMA, LICH_KING_ICARUS, COLLECTOR_MARSHA, COLLECTOR_TRUMPIN);
		addKillId(MANASHEN_GARGOYLE, ENCHANTED_MONSTEREYE, ENCHANTED_STONE_GOLEM, ENCHANTED_IRON_GOLEM);
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
			case "30834-05.htm":
			{
				if (qs.isCreated())
				{
					qs.setMemoState(1);
					qs.setMemoStateEx(1, 0);
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30834-04.htm":
			{
				if (player.isInCategory(CategoryType.WIZARD_GROUP) && (player.getLevel() >= MIN_LEVEL))
				{
					htmltext = event;
				}
				break;
			}
			case "30834-08.html":
			{
				if (hasQuestItems(player, NEBULITE_ORB))
				{
					giveAdena(player, (getQuestItemsCount(player, NEBULITE_ORB) * 120), true);
					takeItems(player, NEBULITE_ORB, -1);
					htmltext = event;
				}
				else
				{
					htmltext = "30834-08a.html";
				}
				break;
			}
			case "30834-11.html":
			{
				qs.exitQuest(true, true);
				htmltext = event;
				break;
			}
			case "30835-02.html":
			{
				if (!hasQuestItems(player, ECTOPLASM_LIQUEUR))
				{
					htmltext = event;
				}
				else
				{
					final int chance = getRandom(1000);
					
					if (chance <= 119)
					{
						giveItems(player, SCROLL_ENCHANT_WEAPON_D_GRADE, 1);
					}
					else if (chance <= 169)
					{
						giveItems(player, SCROLL_ENCHANT_WEAPON_C_GRADE, 1);
					}
					else if (chance <= 329)
					{
						giveItems(player, SPIRITSHOT_C_GRADE, getRandom(200) + 401);
					}
					else if (chance <= 559)
					{
						giveItems(player, SPIRITSHOT_D_GRADE, getRandom(200) + 401);
					}
					else if (chance <= 561)
					{
						giveItems(player, SAGES_BLOOD, 1);
					}
					else if (chance <= 578)
					{
						giveItems(player, SQUARE_SHIELD, 1);
					}
					else if (chance <= 579)
					{
						giveItems(player, NICKLACE_OF_MAGIC, 1);
					}
					else if (chance <= 581)
					{
						giveItems(player, RING_OF_AGES, 1);
					}
					else if (chance <= 582)
					{
						giveItems(player, TOWER_SHIELD, 1);
					}
					else if (chance <= 584)
					{
						giveItems(player, NICKLACE_OF_MERMAID, 1);
					}
					else
					{
						giveItems(player, SCROLL_OF_ESCAPE, 1);
					}
					
					takeItems(player, ECTOPLASM_LIQUEUR, 1);
					htmltext = "30835-03.html";
				}
				break;
			}
			case "30934-05.html":
			{
				if (qs.isMemoState(1))
				{
					if (qs.getMemoStateEx(1) >= 25)
					{
						htmltext = event;
					}
					else if ((qs.getMemoStateEx(1) >= 1) && (qs.getMemoStateEx(1) < 25) && (getQuestItemsCount(player, NEBULITE_ORB) < 10))
					{
						htmltext = "30934-06.html";
					}
					else if ((qs.getMemoStateEx(1) >= 1) && (qs.getMemoStateEx(1) < 25) && (getQuestItemsCount(player, NEBULITE_ORB) > 10))
					{
						qs.setMemoState(2);
						takeItems(player, NEBULITE_ORB, 10);
						htmltext = "30934-07.html";
					}
				}
				break;
			}
			case "30934-08a.html":
			{
				if (qs.isMemoState(2))
				{
					final int i0 = getRandom(100);
					final int i1 = getRandom(3);
					
					if ((i0 < 20) && (i1 == 0))
					{
						qs.setMemoStateEx(1, qs.getMemoStateEx(1) + 4);
						qs.set("param1", 0);
						htmltext = event;
					}
					else if ((i0 < 20) && (i1 == 1))
					{
						qs.set("param1", 1);
						htmltext = "30934-08b.html";
					}
					else if ((i0 < 20) && (i1 == 2))
					{
						qs.set("param1", 2);
						htmltext = "30934-08c.html";
					}
					else if ((i0 >= 20) && (i0 < 50) && (i1 == 0))
					{
						if (getRandom(2) == 0)
						{
							qs.set("param1", 0);
						}
						else
						{
							qs.set("param1", 1);
						}
						htmltext = "30934-09a.html";
					}
					else if ((i0 >= 20) && (i0 < 50) && (i1 == 1))
					{
						if (getRandom(2) == 0)
						{
							qs.set("param1", 1);
						}
						else
						{
							qs.set("param1", 2);
						}
						htmltext = "30934-09b.html";
					}
					else if ((i0 >= 20) && (i0 < 50) && (i1 == 2))
					{
						if (getRandom(2) == 0)
						{
							qs.set("param1", 2);
						}
						else
						{
							qs.set("param1", 0);
						}
						htmltext = "30934-09c.html";
					}
					else
					{
						qs.set("param1", getRandom(3));
						htmltext = "30934-10.html";
					}
				}
				break;
			}
			case "30934-11a.html":
			{
				if (qs.isMemoState(2))
				{
					if (qs.getInt("param1") == 0)
					{
						giveItems(player, NEBULITE_ORB, 10);
						qs.set("param1", 4);
						htmltext = event;
					}
					else if (qs.getInt("param1") == 1)
					{
						htmltext = "30934-11b.html";
					}
					else if (qs.getInt("param1") == 2)
					{
						giveItems(player, NEBULITE_ORB, 20);
						qs.set("param1", 4);
						htmltext = "30934-11c.html";
					}
					qs.setMemoState(1);
				}
				break;
			}
			case "30934-12a.html":
			{
				if (qs.isMemoState(2))
				{
					if (qs.getInt("param1") == 0)
					{
						giveItems(player, NEBULITE_ORB, 20);
						qs.set("param1", 4);
						htmltext = event;
					}
					else if (qs.getInt("param1") == 1)
					{
						giveItems(player, NEBULITE_ORB, 10);
						qs.set("param1", 4);
						htmltext = "30934-12b.html";
					}
					else if (qs.getInt("param1") == 2)
					{
						htmltext = "30934-12c.html";
					}
					qs.setMemoState(1);
				}
				break;
			}
			case "30934-13a.html":
			{
				if (qs.isMemoState(2))
				{
					if (qs.getInt("param1") == 0)
					{
						htmltext = event;
					}
					else if (qs.getInt("param1") == 1)
					{
						giveItems(player, NEBULITE_ORB, 20);
						qs.set("param1", 4);
						htmltext = "30934-13b.html";
					}
					else if (qs.getInt("param1") == 2)
					{
						giveItems(player, NEBULITE_ORB, 10);
						qs.set("param1", 4);
						htmltext = "30934-13c.html";
					}
					qs.setMemoState(1);
				}
				break;
			}
			case "30935-03.html":
			{
				if (getQuestItemsCount(player, NEBULITE_ORB) < 10)
				{
					htmltext = event;
				}
				else
				{
					qs.set("param2", getRandom(2));
					htmltext = "30935-04.html";
				}
				break;
			}
			case "30935-05.html":
			{
				if ((qs.getInt("param1") == 0) && (qs.getInt("param2") == 0) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 1);
					qs.set("param2", 2);
					htmltext = event;
				}
				else if ((qs.getInt("param1") == 1) && (qs.getInt("param2") == 0) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 2);
					qs.set("param2", 2);
					htmltext = "30935-05a.html";
				}
				else if ((qs.getInt("param1") == 2) && (qs.getInt("param2") == 0) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 3);
					qs.set("param2", 2);
					htmltext = "30935-05b.html";
				}
				else if ((qs.getInt("param1") == 3) && (qs.getInt("param2") == 0) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 4);
					qs.set("param2", 2);
					htmltext = "30935-05c.html";
				}
				else if ((qs.getInt("param1") == 4) && (qs.getInt("param2") == 0) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 0);
					qs.set("param2", 2);
					giveItems(player, NEBULITE_ORB, 310);
					htmltext = "30935-05d.html";
				}
				else if ((qs.getInt("param2") == 1) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					takeItems(player, NEBULITE_ORB, 10);
					qs.set("param1", 0);
					qs.set("param2", 2);
					htmltext = "30935-06.html";
				}
				else if (getQuestItemsCount(player, NEBULITE_ORB) < 10)
				{
					htmltext = "30935-03.html";
				}
				break;
			}
			case "30935-07.html":
			{
				if ((qs.getInt("param2") == 0) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					takeItems(player, NEBULITE_ORB, 10);
					qs.set("param1", 0);
					qs.set("param2", 2);
					htmltext = event;
				}
				else if ((qs.getInt("param1") == 0) && (qs.getInt("param2") == 1) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 1);
					qs.set("param2", 2);
					htmltext = "30935-08.html";
				}
				else if ((qs.getInt("param1") == 1) && (qs.getInt("param2") == 1) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 2);
					qs.set("param2", 2);
					htmltext = "30935-08a.html";
				}
				else if ((qs.getInt("param1") == 2) && (qs.getInt("param2") == 1) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 3);
					qs.set("param2", 2);
					htmltext = "30935-08b.html";
				}
				else if ((qs.getInt("param1") == 3) && (qs.getInt("param2") == 1) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 4);
					qs.set("param2", 2);
					htmltext = "30935-08c.html";
				}
				else if ((qs.getInt("param1") == 4) && (qs.getInt("param2") == 1) && (getQuestItemsCount(player, NEBULITE_ORB) >= 10))
				{
					qs.set("param1", 0);
					qs.set("param2", 2);
					giveItems(player, NEBULITE_ORB, 310);
					htmltext = "30935-08d.html";
				}
				else if (getQuestItemsCount(player, NEBULITE_ORB) < 10)
				{
					htmltext = "30935-03.html";
				}
				break;
			}
			case "30935-09.html":
			{
				if (qs.getInt("param1") == 1)
				{
					qs.set("param1", 0);
					qs.set("param2", 2);
					giveItems(player, NEBULITE_ORB, 10);
					htmltext = event;
				}
				else if (qs.getInt("param1") == 2)
				{
					qs.set("param1", 0);
					qs.set("param2", 2);
					giveItems(player, NEBULITE_ORB, 30);
					htmltext = "30935-09a.html";
				}
				else if (qs.getInt("param1") == 3)
				{
					qs.set("param1", 0);
					qs.set("param2", 2);
					giveItems(player, NEBULITE_ORB, 70);
					htmltext = "30935-09b.html";
				}
				else if (qs.getInt("param1") == 4)
				{
					qs.set("param1", 0);
					qs.set("param2", 2);
					giveItems(player, NEBULITE_ORB, 150);
					htmltext = "30935-09c.html";
				}
				break;
			}
			case "30935-10.html":
			{
				qs.set("param1", 0);
				qs.set("param2", 2);
				htmltext = event;
				break;
			}
			case "30834-04a.html":
			case "30834-06a.html":
			case "30834-09a.html":
			case "30834-09b.html":
			case "30834-11a.html":
			case "30834-09.html":
			case "30834-10.html":
			case "30835-04.html":
			case "30835-05.html":
			case "30934-03a.html":
			case "30935-01.html":
			case "30935-01a.html":
			case "30935-01b.html":
			{
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case MANASHEN_GARGOYLE:
				case ENCHANTED_MONSTEREYE:
				{
					if (getRandom(100) < 63)
					{
						giveItems(killer, NEBULITE_ORB, 1);
						playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					
					if (qs.getMemoStateEx(1) > 1)
					{
						if (getRandom(100) <= 12)
						{
							qs.setMemoStateEx(1, qs.getMemoStateEx(1) - 1);
						}
					}
					break;
				}
				case ENCHANTED_STONE_GOLEM:
				{
					if (getRandom(100) < 65)
					{
						giveItems(killer, NEBULITE_ORB, 1);
						playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					
					if (qs.getMemoStateEx(1) > 1)
					{
						if (getRandom(100) <= 12)
						{
							qs.setMemoStateEx(1, qs.getMemoStateEx(1) - 1);
						}
					}
					break;
				}
				case ENCHANTED_IRON_GOLEM:
				{
					if (getRandom(100) < 68)
					{
						giveItems(killer, NEBULITE_ORB, 1);
						playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					
					if (qs.getMemoStateEx(1) > 1)
					{
						if (getRandom(100) <= 13)
						{
							qs.setMemoStateEx(1, qs.getMemoStateEx(1) - 1);
						}
					}
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == MAGIC_TRADER_CEMA)
			{
				if (player.isInCategory(CategoryType.WIZARD_GROUP))
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						htmltext = "30834-03.htm";
					}
					else
					{
						htmltext = "30834-02.htm";
					}
				}
				else
				{
					htmltext = "30834-01.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case MAGIC_TRADER_CEMA:
				{
					if (!hasQuestItems(player, NEBULITE_ORB))
					{
						htmltext = "30834-06.html";
					}
					else
					{
						htmltext = "30834-07.html";
					}
					break;
				}
				case LICH_KING_ICARUS:
				{
					htmltext = "30835-01.html";
					break;
				}
				case COLLECTOR_MARSHA:
				{
					if (qs.getMemoStateEx(1) == 0)
					{
						qs.setMemoStateEx(1, 1);
						htmltext = "30934-03.html";
					}
					else
					{
						qs.setMemoState(1);
						htmltext = "30934-04.html";
					}
					break;
				}
				case COLLECTOR_TRUMPIN:
				{
					qs.set("param1", 0);
					htmltext = "30935-01.html";
					break;
				}
			}
		}
		return htmltext;
	}
}

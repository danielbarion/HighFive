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
package quests.Q00117_TheOceanOfDistantStars;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * The Ocean of Distant Stars (117)
 * @author Adry_85
 */
public final class Q00117_TheOceanOfDistantStars extends Quest
{
	// NPCs
	private static final int OBI = 32052;
	private static final int ABEY = 32053;
	private static final int GHOST_OF_A_RAILROAD_ENGINEER = 32054;
	private static final int GHOST_OF_AN_ANCIENT_RAILROAD_ENGINEER = 32055;
	private static final int BOX = 32076;
	// Items
	private static final int ENGRAVED_HAMMER = 8488;
	private static final int BOOK_OF_GREY_STAR = 8495;
	// Misc
	private static final int MIN_LEVEL = 39;
	// Monsters
	private static final int BANDIT_WARRIOR = 22023;
	private static final int BANDIT_INSPECTOR = 22024;
	private static final Map<Integer, Double> MONSTER_DROP_CHANCES = new HashMap<>();
	static
	{
		MONSTER_DROP_CHANCES.put(BANDIT_WARRIOR, 0.179);
		MONSTER_DROP_CHANCES.put(BANDIT_INSPECTOR, 0.1);
	}
	
	public Q00117_TheOceanOfDistantStars()
	{
		super(117);
		addStartNpc(ABEY);
		addTalkId(ABEY, GHOST_OF_A_RAILROAD_ENGINEER, GHOST_OF_AN_ANCIENT_RAILROAD_ENGINEER, BOX, OBI);
		addKillId(BANDIT_WARRIOR, BANDIT_INSPECTOR);
		registerQuestItems(ENGRAVED_HAMMER, BOOK_OF_GREY_STAR);
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
			case "32053-02.htm":
			{
				qs.setMemoState(1);
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32053-06.html":
			{
				if (qs.isMemoState(3))
				{
					qs.setMemoState(4);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "32053-09.html":
			{
				if (qs.isMemoState(5) && hasQuestItems(player, ENGRAVED_HAMMER))
				{
					qs.setMemoState(6);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "32054-02.html":
			{
				if (qs.isMemoState(9))
				{
					htmltext = event;
				}
				break;
			}
			case "32054-03.html":
			{
				if (qs.isMemoState(9))
				{
					giveAdena(player, 17647, true);
					addExpAndSp(player, 107387, 7369);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "32055-02.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "32055-05.html":
			{
				if (qs.isMemoState(8))
				{
					if (hasQuestItems(player, ENGRAVED_HAMMER))
					{
						qs.setMemoState(9);
						qs.setCond(10, true);
						takeItems(player, ENGRAVED_HAMMER, -1);
						htmltext = event;
					}
					else
					{
						htmltext = "32055-06.html";
					}
				}
				break;
			}
			case "32076-02.html":
			{
				if (qs.isMemoState(4))
				{
					qs.setMemoState(5);
					qs.setCond(5, true);
					giveItems(player, ENGRAVED_HAMMER, 1);
					htmltext = event;
				}
				break;
			}
			case "32052-02.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setMemoState(3);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32052-05.html":
			{
				if (qs.isMemoState(6))
				{
					qs.setMemoState(7);
					qs.setCond(7, true);
					htmltext = event;
				}
				break;
			}
			case "32052-07.html":
			{
				if (qs.isMemoState(7) && hasQuestItems(player, BOOK_OF_GREY_STAR))
				{
					qs.setMemoState(8);
					qs.setCond(9, true);
					takeItems(player, BOOK_OF_GREY_STAR, -1);
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
		final QuestState qs = getRandomPartyMemberState(killer, 7, 3, npc);
		
		if ((qs == null) || !Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			return null;
		}
		
		if (giveItemRandomly(killer, npc, BOOK_OF_GREY_STAR, 1, 1, MONSTER_DROP_CHANCES.get(npc.getId()), true))
		{
			qs.setCond(8);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCompleted())
		{
			if (npc.getId() == ABEY)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
		}
		else if (qs.isCreated())
		{
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "32053-01.htm" : "32053-03.htm";
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case ABEY:
				{
					switch (qs.getMemoState())
					{
						case 1:
						{
							htmltext = "32053-04.html";
							break;
						}
						case 3:
						{
							htmltext = "32053-05.html";
							break;
						}
						case 4:
						{
							htmltext = "32053-07.html";
							break;
						}
						case 5:
						{
							if (hasQuestItems(player, ENGRAVED_HAMMER))
							{
								htmltext = "32053-08.html";
							}
							break;
						}
						case 6:
						{
							htmltext = "32053-10.html";
							break;
						}
					}
					break;
				}
				case GHOST_OF_A_RAILROAD_ENGINEER:
				{
					if (qs.isMemoState(9))
					{
						htmltext = "32054-01.html";
					}
					break;
				}
				case GHOST_OF_AN_ANCIENT_RAILROAD_ENGINEER:
				{
					switch (qs.getMemoState())
					{
						case 1:
						{
							htmltext = "32055-01.html";
							break;
						}
						case 2:
						{
							htmltext = "32055-03.html";
							break;
						}
						case 8:
						{
							htmltext = "32055-04.html";
							break;
						}
						case 9:
						{
							htmltext = "32055-07.html";
							break;
						}
					}
					break;
				}
				case BOX:
				{
					if (qs.isMemoState(4))
					{
						htmltext = "32076-01.html";
					}
					else if (qs.isMemoState(5))
					{
						htmltext = "32076-03.html";
					}
					break;
				}
				case OBI:
				{
					switch (qs.getMemoState())
					{
						case 2:
						{
							htmltext = "32052-01.html";
							break;
						}
						case 3:
						{
							htmltext = "32052-03.html";
							break;
						}
						case 6:
						{
							htmltext = "32052-04.html";
							break;
						}
						case 7:
						{
							htmltext = hasQuestItems(player, BOOK_OF_GREY_STAR) ? "32052-06.html" : "32052-08.html";
							break;
						}
						case 8:
						{
							htmltext = "32052-09.html";
							break;
						}
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

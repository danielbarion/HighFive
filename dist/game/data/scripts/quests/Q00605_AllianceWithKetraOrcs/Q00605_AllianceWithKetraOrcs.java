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
package quests.Q00605_AllianceWithKetraOrcs;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Alliance with Ketra Orcs (605)
 * @author malyelfik
 */
public class Q00605_AllianceWithKetraOrcs extends Quest
{
	private static class DropInfo
	{
		private final int _chance;
		private final int _minCond;
		private final int _itemId;
		
		public DropInfo(int chance, int minCond)
		{
			_chance = chance;
			_minCond = minCond;
			switch (_minCond)
			{
				case 1:
				{
					_itemId = VARKA_BADGE_SOLDIER;
					break;
				}
				case 2:
				{
					_itemId = VARKA_BADGE_OFFICER;
					break;
				}
				default:
				{
					_itemId = VARKA_BADGE_CAPTAIN;
					break;
				}
			}
		}
		
		public int getMinCond()
		{
			return _minCond;
		}
		
		public int getChance()
		{
			return _chance;
		}
		
		public int getItemId()
		{
			return _itemId;
		}
	}
	
	// NPC
	private static final int WAHKAN = 31371;
	// Monsters
	private static final Map<Integer, DropInfo> MOBS = new HashMap<>();
	static
	{
		MOBS.put(21350, new DropInfo(500, 1)); // Varka Silenos Recruit
		MOBS.put(21351, new DropInfo(500, 1)); // Varka Silenos Footman
		MOBS.put(21353, new DropInfo(509, 1)); // Varka Silenos Scout
		MOBS.put(21354, new DropInfo(521, 1)); // Varka Silenos Hunter
		MOBS.put(21355, new DropInfo(519, 1)); // Varka Silenos Shaman
		MOBS.put(21357, new DropInfo(500, 2)); // Varka Silenos Priest
		MOBS.put(21358, new DropInfo(500, 2)); // Varka Silenos Warrior
		MOBS.put(21360, new DropInfo(509, 2)); // Varka Silenos Medium
		MOBS.put(21361, new DropInfo(518, 2)); // Varka Silenos Magus
		MOBS.put(21362, new DropInfo(518, 2)); // Varka Silenos Officer
		MOBS.put(21364, new DropInfo(527, 2)); // Varka Silenos Seer
		MOBS.put(21365, new DropInfo(500, 3)); // Varka Silenos Great Magus
		MOBS.put(21366, new DropInfo(500, 3)); // Varka Silenos General
		MOBS.put(21368, new DropInfo(508, 3)); // Varka Silenos Great Seer
		MOBS.put(21369, new DropInfo(628, 2)); // Varka's Commander
		MOBS.put(21370, new DropInfo(604, 2)); // Varka's Elite Guard
		MOBS.put(21371, new DropInfo(627, 3)); // Varka's Head Magus
		MOBS.put(21372, new DropInfo(604, 3)); // Varka's Head Guard
		MOBS.put(21373, new DropInfo(649, 3)); // Varka's Prophet
		MOBS.put(21374, new DropInfo(626, 3)); // Prophet's Guard
		MOBS.put(21375, new DropInfo(626, 3)); // Disciple of Prophet
	}
	// Items
	private static final int VARKA_BADGE_SOLDIER = 7216;
	private static final int VARKA_BADGE_OFFICER = 7217;
	private static final int VARKA_BADGE_CAPTAIN = 7218;
	private static final int VALOR_TOTEM = 7219;
	private static final int WISDOM_TOTEM = 7220;
	private static final int[] KETRA_MARKS =
	{
		7211, // Mark of Ketra's Alliance - Level 1
		7212, // Mark of Ketra's Alliance - Level 2
		7213, // Mark of Ketra's Alliance - Level 3
		7214, // Mark of Ketra's Alliance - Level 4
		7215, // Mark of Ketra's Alliance - Level 5
	};
	private static final int[] VARKA_MARKS =
	{
		7221, // Mark of Varka's Alliance - Level 1
		7222, // Mark of Varka's Alliance - Level 2
		7223, // Mark of Varka's Alliance - Level 3
		7224, // Mark of Varka's Alliance - Level 4
		7225, // Mark of Varka's Alliance - Level 5
	};
	// Misc
	private static final int MIN_LEVEL = 74;
	private static final int[] SOLDIER_BADGE_COUNT =
	{
		100, // cond 1
		200, // cond 2
		300, // cond 3
		300, // cond 4
		400, // cond 5
	};
	private static final int[] OFFICER_BADGE_COUNT =
	{
		0, // cond 1
		100, // cond 2
		200, // cond 3
		300, // cond 4
		400, // cond 5
	};
	private static final int[] CAPTAIN_BADGE_COUNT =
	{
		0, // cond 1
		0, // cond 2
		100, // cond 3
		200, // cond 4
		200, // cond 5
	};
	
	public Q00605_AllianceWithKetraOrcs()
	{
		super(605);
		addStartNpc(WAHKAN);
		addTalkId(WAHKAN);
		addKillId(MOBS.keySet());
		registerQuestItems(VARKA_BADGE_SOLDIER, VARKA_BADGE_OFFICER, VARKA_BADGE_CAPTAIN);
	}
	
	private boolean canGetItem(QuestState st, int itemId)
	{
		int count = 0;
		switch (itemId)
		{
			case VARKA_BADGE_SOLDIER:
			{
				count = SOLDIER_BADGE_COUNT[st.getCond() - 1];
				break;
			}
			case VARKA_BADGE_OFFICER:
			{
				count = OFFICER_BADGE_COUNT[st.getCond() - 1];
				break;
			}
			case VARKA_BADGE_CAPTAIN:
			{
				count = CAPTAIN_BADGE_COUNT[st.getCond() - 1];
				break;
			}
		}
		if (getQuestItemsCount(st.getPlayer(), itemId) < count)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = event;
		switch (event)
		{
			case "31371-12a.html":
			case "31371-12b.html":
			case "31371-25.html":
			{
				break;
			}
			case "31371-04.htm":
			{
				if (hasAtLeastOneQuestItem(player, VARKA_MARKS))
				{
					return "31371-03.htm";
				}
				st.setState(State.STARTED);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ACCEPT);
				for (int i = 0; i < KETRA_MARKS.length; i++)
				{
					if (hasQuestItems(player, KETRA_MARKS[i]))
					{
						st.setCond(i + 2);
						return "31371-0" + (i + 5) + ".htm";
					}
				}
				st.setCond(1);
				break;
			}
			case "31371-12.html":
			{
				if (getQuestItemsCount(player, VARKA_BADGE_SOLDIER) < SOLDIER_BADGE_COUNT[0])
				{
					return getNoQuestMsg(player);
				}
				takeItems(player, VARKA_BADGE_SOLDIER, -1);
				giveItems(player, KETRA_MARKS[0], 1);
				st.setCond(2, true);
				break;
			}
			case "31371-15.html":
			{
				if ((getQuestItemsCount(player, VARKA_BADGE_SOLDIER) < SOLDIER_BADGE_COUNT[1]) || (getQuestItemsCount(player, VARKA_BADGE_OFFICER) < OFFICER_BADGE_COUNT[1]))
				{
					return getNoQuestMsg(player);
				}
				takeItems(player, -1, VARKA_BADGE_SOLDIER, VARKA_BADGE_OFFICER, KETRA_MARKS[0]);
				giveItems(player, KETRA_MARKS[1], 1);
				st.setCond(3, true);
				break;
			}
			case "31371-18.html":
			{
				if ((getQuestItemsCount(player, VARKA_BADGE_SOLDIER) < SOLDIER_BADGE_COUNT[2]) || (getQuestItemsCount(player, VARKA_BADGE_OFFICER) < OFFICER_BADGE_COUNT[2]) || (getQuestItemsCount(player, VARKA_BADGE_CAPTAIN) < CAPTAIN_BADGE_COUNT[2]))
				{
					return getNoQuestMsg(player);
				}
				takeItems(player, -1, VARKA_BADGE_SOLDIER, VARKA_BADGE_OFFICER, VARKA_BADGE_CAPTAIN, KETRA_MARKS[1]);
				giveItems(player, KETRA_MARKS[2], 1);
				st.setCond(4, true);
				break;
			}
			case "31371-21.html":
			{
				if (!hasQuestItems(player, VALOR_TOTEM) || (getQuestItemsCount(player, VARKA_BADGE_SOLDIER) < SOLDIER_BADGE_COUNT[3]) || (getQuestItemsCount(player, VARKA_BADGE_OFFICER) < OFFICER_BADGE_COUNT[3]) || (getQuestItemsCount(player, VARKA_BADGE_CAPTAIN) < CAPTAIN_BADGE_COUNT[3]))
				{
					return getNoQuestMsg(player);
				}
				takeItems(player, -1, VARKA_BADGE_SOLDIER, VARKA_BADGE_OFFICER, VARKA_BADGE_CAPTAIN, VALOR_TOTEM, KETRA_MARKS[2]);
				giveItems(player, KETRA_MARKS[3], 1);
				st.setCond(5, true);
				break;
			}
			case "31371-26.html":
			{
				takeItems(player, -1, KETRA_MARKS);
				takeItems(player, -1, VALOR_TOTEM, WISDOM_TOTEM);
				st.exitQuest(true, true);
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
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final L2PcInstance member = getRandomPartyMemberState(killer, State.STARTED);
		if (member != null)
		{
			final QuestState st = getQuestState(member, false);
			final DropInfo info = MOBS.get(npc.getId());
			if ((st.getCond() >= info.getMinCond()) && (st.getCond() < 6) && canGetItem(st, info.getItemId()) && (getRandom(1000) < info.getChance()))
			{
				giveItems(member, info.getItemId(), 1);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "31371-01.htm" : "31371-02.htm";
				break;
			}
			case State.STARTED:
			{
				switch (st.getCond())
				{
					case 1:
					{
						htmltext = (getQuestItemsCount(player, VARKA_BADGE_SOLDIER) >= SOLDIER_BADGE_COUNT[0]) ? "31371-11.html" : "31371-10.html";
						break;
					}
					case 2:
					{
						htmltext = (hasQuestItems(player, KETRA_MARKS[0]) && (getQuestItemsCount(player, VARKA_BADGE_SOLDIER) >= SOLDIER_BADGE_COUNT[1]) && (getQuestItemsCount(player, VARKA_BADGE_OFFICER) >= OFFICER_BADGE_COUNT[1])) ? "31371-14.html" : "31371-13.html";
						break;
					}
					case 3:
					{
						htmltext = (hasQuestItems(player, KETRA_MARKS[1]) && (getQuestItemsCount(player, VARKA_BADGE_SOLDIER) >= SOLDIER_BADGE_COUNT[2]) && (getQuestItemsCount(player, VARKA_BADGE_OFFICER) >= OFFICER_BADGE_COUNT[2]) && (getQuestItemsCount(player, VARKA_BADGE_CAPTAIN) >= CAPTAIN_BADGE_COUNT[2])) ? "31371-17.html" : "31371-16.html";
						break;
					}
					case 4:
					{
						htmltext = (hasQuestItems(player, KETRA_MARKS[2], VALOR_TOTEM) && (getQuestItemsCount(player, VARKA_BADGE_SOLDIER) >= SOLDIER_BADGE_COUNT[3]) && (getQuestItemsCount(player, VARKA_BADGE_OFFICER) >= OFFICER_BADGE_COUNT[3]) && (getQuestItemsCount(player, VARKA_BADGE_CAPTAIN) >= CAPTAIN_BADGE_COUNT[3])) ? "31371-20.html" : "31371-19.html";
						break;
					}
					case 5:
					{
						if (!hasQuestItems(player, KETRA_MARKS[3]) || !hasQuestItems(player, WISDOM_TOTEM) || (getQuestItemsCount(player, VARKA_BADGE_SOLDIER) < SOLDIER_BADGE_COUNT[4]) || (getQuestItemsCount(player, VARKA_BADGE_OFFICER) < OFFICER_BADGE_COUNT[4]) || (getQuestItemsCount(player, VARKA_BADGE_CAPTAIN) < CAPTAIN_BADGE_COUNT[4]))
						{
							return "31371-22.html";
						}
						st.setCond(6, true);
						takeItems(player, -1, VARKA_BADGE_SOLDIER, VARKA_BADGE_OFFICER, VARKA_BADGE_CAPTAIN, WISDOM_TOTEM, KETRA_MARKS[3]);
						giveItems(player, KETRA_MARKS[4], 1);
						htmltext = "31371-23.html";
						break;
					}
					case 6:
					{
						if (hasQuestItems(player, KETRA_MARKS[4]))
						{
							htmltext = "31371-24.html";
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
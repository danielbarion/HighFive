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
package quests.Q00463_IMustBeaGenius;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

/**
 * I Must Be a Genius (463)<br>
 * @author Gnacik, malyelfik
 * @version 2010-08-19 Based on Freya PTS
 */
public class Q00463_IMustBeaGenius extends Quest
{
	private static class DropInfo
	{
		private final int _count;
		private final int _chance;
		
		public DropInfo(int count, int chance)
		{
			_count = count;
			_chance = chance;
		}
		
		public int getCount()
		{
			return _count;
		}
		
		public int getSpecialChance()
		{
			return _chance;
		}
	}
	
	// NPC
	private static final int GUTENHAGEN = 32069;
	// Items
	private static final int CORPSE_LOG = 15510;
	private static final int COLLECTION = 15511;
	
	// Mobs
	private static final Map<Integer, DropInfo> MOBS = new HashMap<>();
	
	static
	{
		MOBS.put(22801, new DropInfo(5, 0));
		MOBS.put(22802, new DropInfo(5, 0));
		MOBS.put(22803, new DropInfo(5, 0));
		MOBS.put(22804, new DropInfo(-2, 1));
		MOBS.put(22805, new DropInfo(-2, 1));
		MOBS.put(22806, new DropInfo(-2, 1));
		MOBS.put(22807, new DropInfo(-1, -1));
		MOBS.put(22809, new DropInfo(2, 2));
		MOBS.put(22810, new DropInfo(-3, 3));
		MOBS.put(22811, new DropInfo(3, -1));
		MOBS.put(22812, new DropInfo(1, -1));
	}
	
	// Reward @formatter:off
	private static final int[][] REWARD =
	{
		// exp, sp, html
		{198725, 15892, 8},
		{278216, 22249, 8},
		{317961, 25427, 8},
		{357706, 28606, 9},
		{397451, 31784, 9},
		{596176, 47677, 9},
		{715411, 57212, 10},
		{794901, 63569, 10},
		{914137, 73104, 10},
		{1192352, 95353, 11}
	};
	
	// Misc @formatter:on
	private static final int MIN_LEVEL = 70;
	
	public Q00463_IMustBeaGenius()
	{
		super(463);
		addStartNpc(GUTENHAGEN);
		addTalkId(GUTENHAGEN);
		addKillId(MOBS.keySet());
		registerQuestItems(COLLECTION, CORPSE_LOG);
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
			case "32069-03.htm":
			{
				qs.startQuest();
				final int number = getRandom(51) + 550;
				qs.set("number", String.valueOf(number));
				qs.set("chance", String.valueOf(getRandom(4)));
				htmltext = getHtm(player, event).replace("%num%", String.valueOf(number));
				break;
			}
			case "32069-05.htm":
			{
				htmltext = getHtm(player, event).replace("%num%", qs.get("number"));
				break;
			}
			case "reward":
			{
				if (qs.isCond(2))
				{
					final int rnd = getRandom(REWARD.length);
					final String str = (REWARD[rnd][2] < 10) ? "0" + REWARD[rnd][2] : String.valueOf(REWARD[rnd][2]);
					
					addExpAndSp(player, REWARD[rnd][0], REWARD[rnd][1]);
					qs.exitQuest(QuestType.DAILY, true);
					htmltext = "32069-" + str + ".html";
				}
				break;
			}
			case "32069-02.htm":
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
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		
		if (qs.isCond(1))
		{
			boolean msg = false;
			final int number = MOBS.get(npc.getId()).getSpecialChance() == qs.getInt("chance") ? getRandom(100) + 1 : MOBS.get(npc.getId()).getCount();
			
			if (number > 0)
			{
				giveItems(player, CORPSE_LOG, number);
				msg = true;
			}
			else if ((number < 0) && ((getQuestItemsCount(player, CORPSE_LOG) + number) > 0))
			{
				takeItems(player, CORPSE_LOG, Math.abs(number));
				msg = true;
			}
			
			if (msg)
			{
				final NpcSay ns = new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.ATT_ATTACK_S1_RO_ROGUE_S2);
				ns.addStringParameter(player.getName());
				ns.addStringParameter(String.valueOf(number));
				npc.broadcastPacket(ns);
				
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				if (getQuestItemsCount(player, CORPSE_LOG) == qs.getInt("number"))
				{
					takeItems(player, CORPSE_LOG, -1);
					giveItems(player, COLLECTION, 1);
					qs.setCond(2, true);
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				if (!qs.isNowAvailable())
				{
					htmltext = "32069-07.htm";
					break;
				}
				qs.setState(State.CREATED);
				// fallthrou
			}
			case State.CREATED:
			{
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "32069-01.htm" : "32069-00.htm";
				break;
			}
			case State.STARTED:
			{
				if (qs.isCond(1))
				{
					htmltext = "32069-04.html";
				}
				else if (qs.getInt("var") == 1)
				{
					htmltext = "32069-06a.html";
				}
				else
				{
					takeItems(player, COLLECTION, -1);
					qs.set("var", "1");
					htmltext = "32069-06.html";
				}
				break;
			}
		}
		return htmltext;
	}
}
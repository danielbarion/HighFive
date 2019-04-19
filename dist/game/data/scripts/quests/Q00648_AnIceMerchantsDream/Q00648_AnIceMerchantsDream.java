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
package quests.Q00648_AnIceMerchantsDream;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q00115_TheOtherSideOfTruth.Q00115_TheOtherSideOfTruth;

/**
 * An Ice Merchant's Dream (648)
 * @author netvirus, Adry_85
 */
public final class Q00648_AnIceMerchantsDream extends Quest
{
	private static class DropInfo
	{
		private final double _firstChance;
		private final double _secondChance;
		
		public DropInfo(double firstChance, double secondChance)
		{
			_firstChance = firstChance;
			_secondChance = secondChance;
		}
		
		public double getFirstChance()
		{
			return _firstChance;
		}
		
		public double getSecondChance()
		{
			return _secondChance;
		}
	}
	
	// NPCs
	private static final int RAFFORTY = 32020;
	private static final int ICE_SHELF = 32023;
	// Items
	private static final int SILVER_HEMOCYTE = 8057;
	private static final int SILVER_ICE_CRYSTAL = 8077;
	private static final int BLACK_ICE_CRYSTAL = 8078;
	// Misc
	private static final int MIN_LVL = 53;
	// Monsters
	private static final Map<Integer, DropInfo> MONSTERS = new HashMap<>();
	static
	{
		MONSTERS.put(22080, new DropInfo(0.285, 0.048)); // Massive Maze Bandersnatch
		MONSTERS.put(22081, new DropInfo(0.443, 0.0)); // Lost Watcher
		MONSTERS.put(22082, new DropInfo(0.510, 0.0)); // Elder Lost Watcher
		MONSTERS.put(22083, new DropInfo(0.477, 0.049)); // Baby Panthera
		MONSTERS.put(22084, new DropInfo(0.477, 0.049)); // Panthera
		MONSTERS.put(22085, new DropInfo(0.420, 0.043)); // Lost Gargoyle
		MONSTERS.put(22086, new DropInfo(0.490, 0.050)); // Lost Gargoyle Youngling
		MONSTERS.put(22087, new DropInfo(0.787, 0.081)); // Pronghorn Spirit
		MONSTERS.put(22088, new DropInfo(0.480, 0.049)); // Pronghorn
		MONSTERS.put(22089, new DropInfo(0.550, 0.056)); // Ice Tarantula
		MONSTERS.put(22090, new DropInfo(0.570, 0.058)); // Frost Tarantula
		MONSTERS.put(22091, new DropInfo(0.623, 0.0)); // Lost Iron Golem
		MONSTERS.put(22092, new DropInfo(0.623, 0.0)); // Frost Iron Golem
		MONSTERS.put(22093, new DropInfo(0.910, 0.093)); // Lost Buffalo
		MONSTERS.put(22094, new DropInfo(0.553, 0.057)); // Frost Buffalo
		MONSTERS.put(22095, new DropInfo(0.593, 0.061)); // Ursus Cub
		MONSTERS.put(22096, new DropInfo(0.593, 0.061)); // Ursus
		MONSTERS.put(22097, new DropInfo(0.693, 0.071)); // Lost Yeti
		MONSTERS.put(22098, new DropInfo(0.717, 0.074)); // Frost Yeti
	}
	
	public Q00648_AnIceMerchantsDream()
	{
		super(648);
		addStartNpc(RAFFORTY);
		addTalkId(RAFFORTY, ICE_SHELF);
		addKillId(MONSTERS.keySet());
		registerQuestItems(SILVER_HEMOCYTE, SILVER_ICE_CRYSTAL, BLACK_ICE_CRYSTAL);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		final QuestState q115 = player.getQuestState(Q00115_TheOtherSideOfTruth.class.getSimpleName());
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "ACCEPT":
			{
				qs.startQuest();
				if ((q115 != null) && q115.isCompleted())
				{
					htmltext = "32020-04.htm";
				}
				else
				{
					qs.setCond(2);
					htmltext = "32020-05.htm";
				}
				break;
			}
			case "ASK":
			{
				if (qs.getCond() >= 1)
				{
					htmltext = ((q115 != null) && !q115.isCompleted()) ? "32020-14.html" : "32020-15.html";
				}
				break;
			}
			case "LATER":
			{
				if (qs.getCond() >= 1)
				{
					htmltext = ((q115 != null) && !q115.isCompleted()) ? "32020-19.html" : "32020-20.html";
				}
				break;
			}
			case "REWARD":
			{
				if (qs.getCond() >= 1)
				{
					final long silverCryCount = getQuestItemsCount(player, SILVER_ICE_CRYSTAL);
					final long blackCryCount = getQuestItemsCount(player, BLACK_ICE_CRYSTAL);
					if ((silverCryCount + blackCryCount) > 0)
					{
						giveAdena(player, (silverCryCount * 300) + (blackCryCount * 1200), true);
						takeItems(player, -1, SILVER_ICE_CRYSTAL, BLACK_ICE_CRYSTAL);
						htmltext = ((q115 != null) && !q115.isCompleted()) ? "32020-16.html" : "32020-17.html";
					}
					else
					{
						htmltext = "32020-18.html";
					}
				}
				break;
			}
			case "QUIT":
			{
				if (qs.getCond() >= 1)
				{
					if ((q115 != null) && !q115.isCompleted())
					{
						htmltext = "32020-21.html";
						qs.exitQuest(true, true);
					}
					else
					{
						htmltext = "32020-22.html";
					}
				}
				break;
			}
			case "32020-06.html":
			case "32020-07.html":
			case "32020-08.html":
			case "32020-09.html":
			{
				if (qs.getCond() >= 1)
				{
					htmltext = event;
				}
				break;
			}
			case "32020-23.html":
			{
				if (qs.getCond() >= 1)
				{
					qs.exitQuest(true, true);
					htmltext = event;
				}
				break;
			}
			case "32023-04.html":
			{
				if ((qs.getCond() >= 1) && hasQuestItems(player, SILVER_ICE_CRYSTAL) && (qs.getInt("ex") == 0))
				{
					qs.set("ex", (getRandom(4) + 1) * 10);
					htmltext = event;
				}
				break;
			}
			case "32023-05.html":
			{
				if ((qs.getCond() >= 1) && hasQuestItems(player, SILVER_ICE_CRYSTAL) && (qs.getInt("ex") > 0))
				{
					takeItems(player, SILVER_ICE_CRYSTAL, 1);
					final int val = qs.getInt("ex") + 1;
					qs.set("ex", val);
					playSound(player, QuestSound.ITEMSOUND_BROKEN_KEY);
					htmltext = event;
				}
				break;
			}
			case "32023-06.html":
			{
				if ((qs.getCond() >= 1) && hasQuestItems(player, SILVER_ICE_CRYSTAL) && (qs.getInt("ex") > 0))
				{
					takeItems(player, SILVER_ICE_CRYSTAL, 1);
					final int val = qs.getInt("ex") + 2;
					qs.set("ex", val);
					playSound(player, QuestSound.ITEMSOUND_BROKEN_KEY);
					htmltext = event;
				}
				break;
			}
			case "REPLY4":
			{
				if ((qs.getCond() >= 1) && (qs.getInt("ex") > 0))
				{
					final int ex = qs.getInt("ex");
					final int val1 = ex / 10;
					final int val2 = ex - (val1 * 10);
					if (val1 == val2)
					{
						htmltext = "32023-07.html";
						giveItems(player, BLACK_ICE_CRYSTAL, 1);
						playSound(player, QuestSound.ITEMSOUND_ENCHANT_SUCCESS);
					}
					else
					{
						htmltext = "32023-08.html";
						playSound(player, QuestSound.ITEMSOUND_ENCHANT_FAILED);
					}
					qs.set("ex", 0);
				}
				break;
			}
			case "REPLY5":
			{
				if ((qs.getCond() >= 1) && (qs.getInt("ex") > 0))
				{
					final int ex = qs.getInt("ex");
					final int val1 = ex / 10;
					final int val2 = (ex - (val1 * 10)) + 2;
					if (val1 == val2)
					{
						htmltext = "32023-07.html";
						giveItems(player, BLACK_ICE_CRYSTAL, 1);
						playSound(player, QuestSound.ITEMSOUND_ENCHANT_SUCCESS);
					}
					else
					{
						htmltext = "32023-08.html";
						playSound(player, QuestSound.ITEMSOUND_ENCHANT_FAILED);
					}
					qs.set("ex", 0);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, -1, 3, npc);
		if (qs != null)
		{
			final DropInfo info = MONSTERS.get(npc.getId());
			if (qs.getCond() >= 1)
			{
				giveItemRandomly(qs.getPlayer(), npc, SILVER_ICE_CRYSTAL, 1, 0, info.getFirstChance(), true);
			}
			
			if (info.getSecondChance() > 0)
			{
				final QuestState qs2 = qs.getPlayer().getQuestState(Q00115_TheOtherSideOfTruth.class.getSimpleName());
				if ((qs.getCond() >= 2) && (qs2 != null) && qs2.isCompleted())
				{
					giveItemRandomly(qs.getPlayer(), npc, SILVER_HEMOCYTE, 1, 0, info.getSecondChance(), true);
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		final QuestState qs2 = player.getQuestState(Q00115_TheOtherSideOfTruth.class.getSimpleName());
		String htmltext = getNoQuestMsg(player);
		switch (npc.getId())
		{
			case RAFFORTY:
			{
				if (qs.isCreated())
				{
					if (player.getLevel() < MIN_LVL)
					{
						htmltext = "32020-01.htm";
					}
					else
					{
						htmltext = ((qs2 != null) && qs2.isCompleted()) ? "32020-02.htm" : "32020-03.htm";
					}
				}
				else if (qs.isStarted())
				{
					final long hasQuestItems = getQuestItemsCount(player, SILVER_ICE_CRYSTAL, BLACK_ICE_CRYSTAL);
					if ((qs2 != null) && qs2.isCompleted())
					{
						htmltext = (hasQuestItems > 0) ? "32020-13.html" : "32020-11.html";
						if (qs.isCond(1))
						{
							qs.setCond(2, true);
						}
					}
					else
					{
						htmltext = (hasQuestItems > 0) ? "32020-12.html" : "32020-10.html";
					}
				}
				break;
			}
			case ICE_SHELF:
			{
				// TODO: In High Five this quest have an updated reward system.
				if (qs.isStarted())
				{
					if (hasQuestItems(player, SILVER_ICE_CRYSTAL))
					{
						final int val = qs.getInt("ex") % 10;
						if (val == 0)
						{
							htmltext = "32023-03.html";
							qs.set("ex", 0);
						}
						else
						{
							htmltext = "32023-09.html";
						}
					}
					else
					{
						htmltext = "32023-02.html";
					}
				}
				else
				{
					htmltext = "32023-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

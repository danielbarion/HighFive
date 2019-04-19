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
package quests.Q00108_JumbleTumbleDiamondFuss;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;
import com.l2jmobius.gameserver.util.Util;

import quests.Q00281_HeadForTheHills.Q00281_HeadForTheHills;

/**
 * Jumble, Tumble, Diamond Fuss (108)
 * @author Janiko
 */
public final class Q00108_JumbleTumbleDiamondFuss extends Quest
{
	// NPCs
	private static final int COLLECTOR_GOUPH = 30523;
	private static final int TRADER_REEP = 30516;
	private static final int CARRIER_TOROCCO = 30555;
	private static final int MINER_MARON = 30529;
	private static final int BLACKSMITH_BRUNON = 30526;
	private static final int WAREHOUSE_KEEPER_MURDOC = 30521;
	private static final int WAREHOUSE_KEEPER_AIRY = 30522;
	// Monsters
	private static final int GOBLIN_BRIGAND_LEADER = 20323;
	private static final int GOBLIN_BRIGAND_LIEUTENANT = 20324;
	private static final int BLADE_BAT = 20480;
	// Items
	private static final int GOUPHS_CONTRACT = 1559;
	private static final int REEPS_CONTRACT = 1560;
	private static final int ELVEN_WINE = 1561;
	private static final int BRUNONS_DICE = 1562;
	private static final int BRUNONS_CONTRACT = 1563;
	private static final int AQUAMARINE = 1564;
	private static final int CHRYSOBERYL = 1565;
	private static final int GEM_BOX = 1566;
	private static final int COAL_PIECE = 1567;
	private static final int BRUNONS_LETTER = 1568;
	private static final int BERRY_TART = 1569;
	private static final int BAT_DIAGRAM = 1570;
	private static final int STAR_DIAMOND = 1571;
	// Rewards
	private static final ItemHolder[] REWARDS =
	{
		new ItemHolder(1060, 100), // Lesser Healing Potion
		new ItemHolder(4412, 10), // Echo Crystal - Theme of Battle
		new ItemHolder(4413, 10), // Echo Crystal - Theme of Love
		new ItemHolder(4414, 10), // Echo Crystal - Theme of Solitude
		new ItemHolder(4415, 10), // Echo Crystal - Theme of Feast
		new ItemHolder(4416, 10), // Echo Crystal - Theme of Celebration
	};
	private static final int SILVERSMITH_HAMMER = 1511;
	// Misc
	private static final int MIN_LVL = 10;
	private static final int MAX_GEM_COUNT = 10;
	private static final Map<Integer, Double> GOBLIN_DROP_CHANCES = new HashMap<>();
	static
	{
		GOBLIN_DROP_CHANCES.put(GOBLIN_BRIGAND_LEADER, 0.8);
		GOBLIN_DROP_CHANCES.put(GOBLIN_BRIGAND_LIEUTENANT, 0.6);
	}
	
	public Q00108_JumbleTumbleDiamondFuss()
	{
		super(108);
		addStartNpc(COLLECTOR_GOUPH);
		addTalkId(COLLECTOR_GOUPH, TRADER_REEP, CARRIER_TOROCCO, MINER_MARON, BLACKSMITH_BRUNON, WAREHOUSE_KEEPER_MURDOC, WAREHOUSE_KEEPER_AIRY);
		addKillId(GOBLIN_BRIGAND_LEADER, GOBLIN_BRIGAND_LIEUTENANT, BLADE_BAT);
		registerQuestItems(GOUPHS_CONTRACT, REEPS_CONTRACT, ELVEN_WINE, BRUNONS_DICE, BRUNONS_CONTRACT, AQUAMARINE, CHRYSOBERYL, GEM_BOX, COAL_PIECE, BRUNONS_LETTER, BERRY_TART, BAT_DIAGRAM, STAR_DIAMOND);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		String htmltext = null;
		if (st == null)
		{
			return htmltext;
		}
		switch (event)
		{
			case "30523-04.htm":
			{
				if (st.isCreated())
				{
					st.startQuest();
					giveItems(player, GOUPHS_CONTRACT, 1);
					htmltext = event;
				}
				break;
			}
			case "30555-02.html":
			{
				if (st.isCond(2) && hasQuestItems(player, REEPS_CONTRACT))
				{
					takeItems(player, REEPS_CONTRACT, -1);
					giveItems(player, ELVEN_WINE, 1);
					st.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "30526-02.html":
			{
				if (st.isCond(4) && hasQuestItems(player, BRUNONS_DICE))
				{
					takeItems(player, BRUNONS_DICE, -1);
					giveItems(player, BRUNONS_CONTRACT, 1);
					st.setCond(5, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState st = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		
		switch (npc.getId())
		{
			case COLLECTOR_GOUPH:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						if (talker.getRace() != Race.DWARF)
						{
							htmltext = "30523-01.htm";
						}
						else if (talker.getLevel() < MIN_LVL)
						{
							htmltext = "30523-02.htm";
						}
						else
						{
							htmltext = "30523-03.htm";
						}
						break;
					}
					case State.STARTED:
					{
						switch (st.getCond())
						{
							case 1:
							{
								if (hasQuestItems(talker, GOUPHS_CONTRACT))
								{
									htmltext = "30523-05.html";
								}
								break;
							}
							case 2:
							case 3:
							case 4:
							case 5:
							case 6:
							{
								if (hasAtLeastOneQuestItem(talker, REEPS_CONTRACT, ELVEN_WINE, BRUNONS_DICE, BRUNONS_CONTRACT))
								{
									htmltext = "30523-06.html";
								}
								break;
							}
							case 7:
							{
								if (hasQuestItems(talker, GEM_BOX))
								{
									takeItems(talker, GEM_BOX, -1);
									giveItems(talker, COAL_PIECE, 1);
									st.setCond(8, true);
									htmltext = "30523-07.html";
								}
								break;
							}
							case 8:
							case 9:
							case 10:
							case 11:
							{
								if (hasAtLeastOneQuestItem(talker, COAL_PIECE, BRUNONS_LETTER, BERRY_TART, BAT_DIAGRAM))
								{
									htmltext = "30523-08.html";
								}
								break;
							}
							case 12:
							{
								if (hasQuestItems(talker, STAR_DIAMOND))
								{
									Q00281_HeadForTheHills.giveNewbieReward(talker);
									addExpAndSp(talker, 34565, 2962);
									giveAdena(talker, 14666, true);
									for (ItemHolder reward : REWARDS)
									{
										giveItems(talker, reward);
									}
									giveItems(talker, SILVERSMITH_HAMMER, 1);
									st.exitQuest(false, true);
									talker.sendPacket(new SocialAction(talker.getObjectId(), 3));
									htmltext = "30523-09.html";
								}
								break;
							}
							
						}
						break;
					}
					case State.COMPLETED:
					{
						htmltext = getAlreadyCompletedMsg(talker);
						break;
					}
				}
				break;
			}
			case TRADER_REEP:
			{
				switch (st.getCond())
				{
					case 1:
					{
						if (hasQuestItems(talker, GOUPHS_CONTRACT))
						{
							takeItems(talker, GOUPHS_CONTRACT, -1);
							giveItems(talker, REEPS_CONTRACT, 1);
							st.setCond(2, true);
							htmltext = "30516-01.html";
						}
						break;
					}
					case 2:
					{
						if (hasQuestItems(talker, REEPS_CONTRACT))
						{
							htmltext = "30516-02.html";
						}
						break;
					}
					default:
					{
						if (st.getCond() > 2)
						{
							htmltext = "30516-02.html";
						}
						break;
					}
				}
				break;
			}
			case CARRIER_TOROCCO:
			{
				switch (st.getCond())
				{
					case 2:
					{
						if (hasQuestItems(talker, REEPS_CONTRACT))
						{
							htmltext = "30555-01.html";
						}
						break;
					}
					case 3:
					{
						if (hasQuestItems(talker, ELVEN_WINE))
						{
							htmltext = "30555-03.html";
						}
						break;
					}
					case 7:
					{
						if (hasQuestItems(talker, GEM_BOX))
						{
							htmltext = "30555-04.html";
						}
						break;
					}
					default:
					{
						if (st.isStarted())
						{
							htmltext = "30555-05.html";
						}
						break;
					}
				}
				break;
			}
			case MINER_MARON:
			{
				switch (st.getCond())
				{
					case 3:
					{
						if (hasQuestItems(talker, ELVEN_WINE))
						{
							takeItems(talker, ELVEN_WINE, -1);
							giveItems(talker, BRUNONS_DICE, 1);
							st.setCond(4, true);
							htmltext = "30529-01.html";
						}
						break;
					}
					case 4:
					{
						if (hasQuestItems(talker, BRUNONS_DICE))
						{
							htmltext = "30529-02.html";
						}
						break;
					}
					default:
					{
						if (st.getCond() > 4)
						{
							htmltext = "30529-03.html";
						}
						break;
					}
				}
				break;
			}
			case BLACKSMITH_BRUNON:
			{
				switch (st.getCond())
				{
					case 4:
					{
						if (hasQuestItems(talker, BRUNONS_DICE))
						{
							htmltext = "30526-01.html";
						}
						break;
					}
					case 5:
					{
						if (hasQuestItems(talker, BRUNONS_CONTRACT))
						{
							htmltext = "30526-03.html";
						}
						break;
					}
					case 6:
					{
						if (hasQuestItems(talker, BRUNONS_CONTRACT) && (getQuestItemsCount(talker, AQUAMARINE) >= MAX_GEM_COUNT) && (getQuestItemsCount(talker, CHRYSOBERYL) >= MAX_GEM_COUNT))
						{
							takeItems(talker, -1, BRUNONS_CONTRACT, AQUAMARINE, CHRYSOBERYL);
							giveItems(talker, GEM_BOX, 1);
							st.setCond(7, true);
							htmltext = "30526-04.html";
						}
						break;
					}
					case 7:
					{
						if (hasQuestItems(talker, GEM_BOX))
						{
							htmltext = "30526-05.html";
						}
						break;
					}
					case 8:
					{
						if (hasQuestItems(talker, COAL_PIECE))
						{
							takeItems(talker, COAL_PIECE, -1);
							giveItems(talker, BRUNONS_LETTER, 1);
							st.setCond(9, true);
							htmltext = "30526-06.html";
						}
						break;
					}
					case 9:
					{
						if (hasQuestItems(talker, BRUNONS_LETTER))
						{
							htmltext = "30526-07.html";
						}
						break;
					}
					case 10:
					case 11:
					case 12:
					{
						if (hasAtLeastOneQuestItem(talker, BERRY_TART, BAT_DIAGRAM, STAR_DIAMOND))
						{
							htmltext = "30526-08.html";
						}
						break;
					}
				}
				break;
			}
			case WAREHOUSE_KEEPER_MURDOC:
			{
				switch (st.getCond())
				{
					case 9:
					{
						if (hasQuestItems(talker, BRUNONS_LETTER))
						{
							takeItems(talker, BRUNONS_LETTER, -1);
							giveItems(talker, BERRY_TART, 1);
							st.setCond(10, true);
							htmltext = "30521-01.html";
						}
						break;
					}
					case 10:
					{
						if (hasQuestItems(talker, BERRY_TART))
						{
							htmltext = "30521-02.html";
						}
						break;
					}
					case 11:
					case 12:
					{
						htmltext = "30521-03.html";
						break;
					}
				}
				break;
			}
			case WAREHOUSE_KEEPER_AIRY:
			{
				switch (st.getCond())
				{
					case 10:
					{
						if (hasQuestItems(talker, BERRY_TART))
						{
							takeItems(talker, BERRY_TART, -1);
							giveItems(talker, BAT_DIAGRAM, 1);
							st.setCond(11, true);
							htmltext = "30522-01.html";
						}
						break;
					}
					case 11:
					{
						if (hasQuestItems(talker, BAT_DIAGRAM))
						{
							htmltext = "30522-02.html";
						}
						break;
					}
					case 12:
					{
						if (hasQuestItems(talker, STAR_DIAMOND))
						{
							htmltext = "30522-03.html";
						}
						break;
					}
					default:
					{
						if (st.isStarted())
						{
							htmltext = "30522-04.html";
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState st = getQuestState(killer, false);
		if ((st != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case GOBLIN_BRIGAND_LEADER:
				case GOBLIN_BRIGAND_LIEUTENANT:
				{
					if (st.isCond(5) && hasQuestItems(killer, BRUNONS_CONTRACT))
					{
						final double dropChance = GOBLIN_DROP_CHANCES.get(npc.getId());
						boolean playSound = false;
						if (giveItemRandomly(killer, npc, AQUAMARINE, 1, MAX_GEM_COUNT, dropChance, false))
						{
							if (getQuestItemsCount(killer, CHRYSOBERYL) >= MAX_GEM_COUNT)
							{
								st.setCond(6, true);
								break;
							}
							
							playSound = true;
						}
						if (giveItemRandomly(killer, npc, CHRYSOBERYL, 1, MAX_GEM_COUNT, dropChance, false))
						{
							if (getQuestItemsCount(killer, AQUAMARINE) >= MAX_GEM_COUNT)
							{
								st.setCond(6, true);
								break;
							}
							
							playSound = true;
						}
						
						if (playSound)
						{
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case BLADE_BAT:
				{
					if (st.isCond(11) && hasQuestItems(killer, BAT_DIAGRAM))
					{
						if (giveItemRandomly(killer, npc, STAR_DIAMOND, 1, 1, 0.2, true))
						{
							takeItems(killer, BAT_DIAGRAM, -1);
							st.setCond(12);
						}
					}
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
}
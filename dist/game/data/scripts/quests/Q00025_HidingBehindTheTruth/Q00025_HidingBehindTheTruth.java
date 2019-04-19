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
package quests.Q00025_HidingBehindTheTruth;

import java.util.HashMap;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.interfaces.IPositionable;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

import quests.Q00024_InhabitantsOfTheForestOfTheDead.Q00024_InhabitantsOfTheForestOfTheDead;

/**
 * Hiding Behind the Truth (25)
 * @author Joxit
 */
public class Q00025_HidingBehindTheTruth extends Quest
{
	// NPCs
	private static final int HIGH_PRIEST_AGRIPEL = 31348;
	private static final int PRIEST_BENEDICT = 31349;
	private static final int MYSTERIOUS_WIZARD = 31522;
	private static final int TOMBSTONE = 31531;
	private static final int MAID_OF_LIDIA = 31532;
	private static final int BROKEN_BOOKSHELF2 = 31533;
	private static final int BROKEN_BOOKSHELF3 = 31534;
	private static final int BROKEN_BOOKSHELF4 = 31535;
	private static final int COFFIN = 31536;
	// Mobs
	private static final int TRIOL_PAWN = 27218;
	// Items
	private static final int MAP_FOREST_OF_THE_DEAD = 7063;
	private static final int CONTRACT = 7066;
	private static final int LIDAS_DRESS = 7155;
	private static final int TOTEM_DOLL2 = 7156;
	private static final int GEMSTONE_KEY = 7157;
	private static final int TOTEM_DOLL3 = 7158;
	// Rewards
	private static final int NECKLACE_OF_BLESSING = 936;
	private static final int EARING_OF_BLESSING = 874;
	private static final int RING_OF_BLESSING = 905;
	// Misc
	private static final int MIN_LVL = 66;
	private static final HashMap<Integer, Location> TRIOL_PAWN_LOC = new HashMap<>();
	private static final IPositionable COFFIN_LOC = new Location(60104, -35820, -681);
	
	public Q00025_HidingBehindTheTruth()
	{
		super(25);
		addStartNpc(PRIEST_BENEDICT);
		addTalkId(HIGH_PRIEST_AGRIPEL, PRIEST_BENEDICT, MYSTERIOUS_WIZARD, TOMBSTONE, MAID_OF_LIDIA, BROKEN_BOOKSHELF2, BROKEN_BOOKSHELF3, BROKEN_BOOKSHELF4, COFFIN);
		registerQuestItems(GEMSTONE_KEY, CONTRACT, TOTEM_DOLL3, TOTEM_DOLL2, LIDAS_DRESS);
		addAttackId(TRIOL_PAWN);
		TRIOL_PAWN_LOC.put(BROKEN_BOOKSHELF2, new Location(47142, -35941, -1623));
		TRIOL_PAWN_LOC.put(BROKEN_BOOKSHELF3, new Location(50055, -47020, -3396));
		TRIOL_PAWN_LOC.put(BROKEN_BOOKSHELF4, new Location(59712, -47568, -2720));
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		
		final QuestState qs = getQuestState(player, false);
		String htmltext = getNoQuestMsg(player);
		switch (event)
		{
			case "31349-06.html":
			case "31349-07.html":
			case "31349-08.html":
			case "31349-09.html":
			case "31522-08.html":
			case "31522-09.html":
			case "31522-07.html":
			case "31522-11.html":
			case "31348-04.html":
			case "31348-05.html":
			case "31348-06.html":
			case "31348-11.html":
			case "31348-07.html":
			case "31348-12.html":
			case "31348-14.html":
			case "31532-04.html":
			case "31532-05.html":
			case "31532-06.html":
			case "31532-14.html":
			case "31532-15.html":
			case "31532-16.html":
			case "31532-19.html":
			case "31532-20.html":
			{
				htmltext = event;
				break;
			}
			case "31349-03.html":
			{
				final QuestState q24 = player.getQuestState(Q00024_InhabitantsOfTheForestOfTheDead.class.getSimpleName());
				if (qs.isCreated() && (q24 != null) && q24.isCompleted() && (player.getLevel() >= MIN_LVL))
				{
					qs.setMemoState(1);
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "31349-05.html":
			{
				if (qs.isMemoState(1))
				{
					if (hasQuestItems(player, TOTEM_DOLL2))
					{
						htmltext = "31349-04.html";
					}
					else
					{
						qs.setCond(2, true);
						htmltext = event;
					}
				}
				break;
			}
			case "31349-10.html":
			{
				if (qs.isMemoState(1) && hasQuestItems(player, TOTEM_DOLL2))
				{
					qs.setMemoState(2);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "31522-04.html":
			{
				if (qs.isMemoState(6) && hasQuestItems(player, GEMSTONE_KEY))
				{
					qs.setMemoState(7);
					qs.setMemoStateEx(1, 20);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "31522-10.html":
			{
				if (qs.isMemoState(16))
				{
					qs.setMemoState(19);
					htmltext = event;
				}
				break;
			}
			case "31522-13.html":
			{
				if (qs.isMemoState(19))
				{
					qs.setMemoState(20);
					qs.setCond(16, true);
					htmltext = event;
				}
				break;
			}
			case "31522-16.html":
			{
				if (qs.isMemoState(24))
				{
					takeItems(player, MAP_FOREST_OF_THE_DEAD, -1);
					rewardItems(player, EARING_OF_BLESSING, 1);
					rewardItems(player, NECKLACE_OF_BLESSING, 1);
					addExpAndSp(player, 572277, 53750);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "31348-02.html":
			{
				if (qs.isMemoState(2))
				{
					takeItems(player, TOTEM_DOLL2, -1);
					qs.setMemoState(3);
					htmltext = event;
				}
				break;
			}
			case "31348-08.html":
			{
				if (qs.isMemoState(3))
				{
					giveItems(player, GEMSTONE_KEY, 1);
					qs.setMemoState(6);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "31348-10.html":
			{
				if (qs.isMemoState(20) && hasQuestItems(player, TOTEM_DOLL3))
				{
					takeItems(player, TOTEM_DOLL3, -1);
					qs.setMemoState(21);
					htmltext = event;
				}
				break;
			}
			case "31348-13.html":
			{
				if (qs.isMemoState(21))
				{
					qs.setMemoState(22);
					htmltext = event;
				}
				break;
			}
			case "31348-16.html":
			{
				if (qs.isMemoState(22))
				{
					qs.setMemoState(23);
					qs.setCond(17, true);
					htmltext = event;
				}
				break;
			}
			case "31348-17.html":
			{
				if (qs.isMemoState(22))
				{
					qs.setMemoState(24);
					qs.setCond(18, true);
					htmltext = event;
				}
				break;
			}
			case "31533-04.html":
			{
				if (qs.getMemoStateEx(npc.getId()) != 0)
				{
					htmltext = "31533-03.html";
				}
				else if (Rnd.get(60) > qs.getMemoStateEx(1))
				{
					qs.setMemoStateEx(1, qs.getMemoStateEx(1) + 20);
					qs.setMemoStateEx(npc.getId(), 1);
					htmltext = "31533-03.html";
				}
				else
				{
					qs.setMemoState(8);
					htmltext = event;
					playSound(player, QuestSound.AMDSOUND_HORROR_02);
				}
				break;
			}
			case "31533-05.html":
			{
				if (qs.isMemoState(8))
				{
					if (!hasQuestItems(player, TOTEM_DOLL3))
					{
						final int brokenDeskOwner = npc.getVariables().getInt("Q00025", 0);
						if (brokenDeskOwner == 0)
						{
							npc.getVariables().set("Q00025", player.getObjectId());
							final L2Npc triyol = addSpawn(TRIOL_PAWN, TRIOL_PAWN_LOC.get(npc.getId()), true, 0);
							triyol.getVariables().set("Q00025", npc);
							triyol.setScriptValue(player.getObjectId());
							startQuestTimer("SAY_TRIYOL", 500, triyol, player);
							startQuestTimer("DESPAWN_TRIYOL", 120000, triyol, player);
							triyol.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
							
							htmltext = event;
							qs.setCond(7);
						}
						else if (brokenDeskOwner == player.getObjectId())
						{
							htmltext = "31533-06.html";
						}
						else
						{
							htmltext = "31533-07.html";
						}
					}
					else
					{
						htmltext = "31533-08.html";
					}
				}
				break;
			}
			case "31533-09.html":
			{
				if (qs.isMemoState(8) && hasQuestItems(player, TOTEM_DOLL3, GEMSTONE_KEY))
				{
					giveItems(player, CONTRACT, 1);
					takeItems(player, GEMSTONE_KEY, -1);
					qs.setMemoState(9);
					qs.setCond(9);
					htmltext = event;
				}
				break;
			}
			case "SAY_TRIYOL":
			{
				npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.GENERAL, npc.getId(), NpcStringId.THAT_BOX_WAS_SEALED_BY_MY_MASTER_S1_DON_T_TOUCH_IT).addStringParameter(player.getName()));
				break;
			}
			case "DESPAWN_TRIYOL":
			{
				final L2Npc brokenDesk = npc.getVariables().getObject("Q00025", L2Npc.class);
				if (brokenDesk != null)
				{
					brokenDesk.getVariables().set("Q00025", 0);
				}
				npc.deleteMe();
				break;
			}
			case "31532-02.html":
			{
				if (qs.isMemoState(9) && hasQuestItems(player, CONTRACT))
				{
					takeItems(player, CONTRACT, -1);
					qs.setMemoState(10);
					htmltext = event;
				}
				break;
			}
			case "31532-07.html":
			{
				if (qs.isMemoState(10))
				{
					qs.setMemoState(11);
					playSound(player, QuestSound.SKILLSOUND_HORROR_1);
					qs.setCond(11);
					htmltext = event;
				}
				break;
			}
			case "31532-11.html":
			{
				if (qs.isMemoState(13))
				{
					final int memoStateEx = qs.getMemoStateEx(1);
					if (memoStateEx <= 3)
					{
						qs.setMemoStateEx(1, memoStateEx + 1);
						playSound(player, QuestSound.CHRSOUND_FDELF_CRY);
						htmltext = event;
					}
					else
					{
						qs.setMemoState(14);
						htmltext = "31532-12.html";
					}
				}
				break;
			}
			case "31532-17.html":
			{
				if (qs.isMemoState(14))
				{
					qs.setMemoState(15);
					htmltext = event;
				}
				break;
			}
			case "31532-21.html":
			{
				if (qs.isMemoState(15))
				{
					qs.setMemoState(16);
					qs.setCond(15);
					htmltext = event;
				}
				break;
			}
			case "31532-25.html":
			{
				if (qs.isMemoState(23))
				{
					takeItems(player, MAP_FOREST_OF_THE_DEAD, -1);
					rewardItems(player, EARING_OF_BLESSING, 1);
					rewardItems(player, RING_OF_BLESSING, 2);
					addExpAndSp(player, 572277, 53750);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "31531-02.html":
			{
				if (qs.isMemoState(11))
				{
					final L2Npc box = addSpawn(COFFIN, COFFIN_LOC, true, 0);
					startQuestTimer("DESPAWN_BOX", 20000, box, player);
					qs.setCond(12, true);
					htmltext = event;
				}
				break;
			}
			case "DESPAWN_BOX":
			{
				npc.deleteMe();
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (npc.getCurrentHp() <= (0.30 * npc.getMaxHp()))
		{
			final QuestState qs = getQuestState(attacker, false);
			if (qs.isMemoState(8) && !hasQuestItems(attacker, TOTEM_DOLL3) && (attacker.getObjectId() == npc.getScriptValue()))
			{
				giveItems(attacker, TOTEM_DOLL3, 1);
				qs.setCond(8, true);
				npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.GENERAL, npc.getId(), NpcStringId.YOU_VE_ENDED_MY_IMMORTAL_LIFE_YOU_RE_PROTECTED_BY_THE_FEUDAL_LORD_AREN_T_YOU));
				
				final L2Npc brokenDesk = npc.getVariables().getObject("Q00025", L2Npc.class);
				if (brokenDesk != null)
				{
					brokenDesk.getVariables().set("Q00025", 0);
				}
				npc.deleteMe();
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		switch (qs.getState())
		{
			case State.CREATED:
			{
				if (npc.getId() == PRIEST_BENEDICT)
				{
					final QuestState q24 = talker.getQuestState(Q00024_InhabitantsOfTheForestOfTheDead.class.getSimpleName());
					if ((q24 != null) && q24.isCompleted() && (talker.getLevel() >= MIN_LVL))
					{
						htmltext = "31349-01.htm";
					}
					else
					{
						htmltext = "31349-02.html";
					}
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case PRIEST_BENEDICT:
					{
						switch (qs.getMemoState())
						{
							case 1:
							{
								htmltext = "31349-03a.html";
								break;
							}
							case 2:
							{
								htmltext = "31349-11.html";
								break;
							}
						}
						break;
					}
					case MYSTERIOUS_WIZARD:
					{
						switch (qs.getMemoState())
						{
							case 1:
							{
								if (!hasQuestItems(talker, TOTEM_DOLL2))
								{
									giveItems(talker, TOTEM_DOLL2, 1);
									qs.setCond(3, true);
									htmltext = "31522-01.html";
								}
								else
								{
									htmltext = "31522-02.html";
								}
								break;
							}
							case 6:
							{
								if (hasQuestItems(talker, GEMSTONE_KEY))
								{
									htmltext = "31522-03.html";
								}
								break;
							}
							case 9:
							{
								if (hasQuestItems(talker, CONTRACT))
								{
									qs.setCond(10, true);
									htmltext = "31522-06.html";
								}
								break;
							}
							case 16:
							{
								htmltext = "31522-06a.html";
								break;
							}
							case 19:
							{
								htmltext = "31522-12.html";
								break;
							}
							case 20:
							{
								htmltext = "31522-14.html";
								break;
							}
							case 24:
							{
								htmltext = "31522-15.html";
								break;
							}
							case 23:
							{
								htmltext = "31522-15a.html";
								break;
							}
							default:
							{
								if ((qs.getMemoState() % 100) == 7)
								{
									htmltext = "31522-05.html";
								}
								break;
							}
						}
						break;
					}
					case HIGH_PRIEST_AGRIPEL:
					{
						switch (qs.getMemoState())
						{
							case 2:
							{
								htmltext = "31348-01.html";
								break;
							}
							case 3:
							{
								htmltext = "31348-03.html";
								break;
							}
							case 6:
							{
								htmltext = "31348-08a.html";
								break;
							}
							case 20:
							{
								if (hasQuestItems(talker, TOTEM_DOLL3))
								{
									htmltext = "31348-09.html";
								}
								break;
							}
							case 21:
							{
								htmltext = "31348-10a.html";
								break;
							}
							case 22:
							{
								htmltext = "31348-15.html";
								break;
							}
							case 23:
							{
								htmltext = "31348-18.html";
								break;
							}
							case 24:
							{
								htmltext = "31348-19.html";
								break;
							}
						}
						break;
					}
					case BROKEN_BOOKSHELF2:
					case BROKEN_BOOKSHELF3:
					case BROKEN_BOOKSHELF4:
					{
						if ((qs.getMemoState() % 100) == 7)
						{
							htmltext = "31533-01.html";
						}
						else if ((qs.getMemoState() % 100) >= 9)
						{
							htmltext = "31533-02.html";
						}
						else if (qs.isMemoState(8))
						{
							htmltext = "31533-04.html";
						}
						break;
					}
					case MAID_OF_LIDIA:
					{
						switch (qs.getMemoState())
						{
							case 9:
							{
								if (hasQuestItems(talker, CONTRACT))
								{
									htmltext = "31532-01.html";
								}
								break;
							}
							case 10:
							{
								htmltext = "31532-03.html";
								break;
							}
							case 11:
							{
								playSound(talker, QuestSound.SKILLSOUND_HORROR_1);
								htmltext = "31532-08.html";
								break;
							}
							case 12:
							{
								if (hasQuestItems(talker, LIDAS_DRESS))
								{
									takeItems(talker, LIDAS_DRESS, -1);
									qs.setMemoState(13);
									qs.setCond(14, true);
									htmltext = "31532-09.html";
								}
								break;
							}
							case 13:
							{
								qs.setMemoStateEx(1, 0);
								playSound(talker, QuestSound.CHRSOUND_FDELF_CRY);
								htmltext = "31532-10.html";
								break;
							}
							case 14:
							{
								htmltext = "31532-13.html";
								break;
							}
							case 15:
							{
								htmltext = "31532-18.html";
								break;
							}
							case 16:
							{
								htmltext = "31532-22.html";
								break;
							}
							case 23:
							{
								htmltext = "31532-23.html";
								break;
							}
							case 24:
							{
								htmltext = "31532-24.html";
								break;
							}
						}
						break;
					}
					case TOMBSTONE:
					{
						switch (qs.getMemoState())
						{
							case 11:
							{
								htmltext = "31531-01.html";
								break;
							}
							case 12:
							{
								htmltext = "31531-03.html";
								break;
							}
						}
						break;
					}
					case COFFIN:
					{
						if (qs.isMemoState(11))
						{
							giveItems(talker, LIDAS_DRESS, 1);
							cancelQuestTimer("DESPAWN_BOX", npc, talker);
							startQuestTimer("DESPAWN_BOX", 3000, npc, talker);
							qs.setMemoState(12);
							qs.setCond(13, true);
							htmltext = "31536-01.html";
						}
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				if (npc.getId() == PRIEST_BENEDICT)
				{
					htmltext = super.getAlreadyCompletedMsg(talker);
				}
				break;
			}
		}
		return htmltext;
	}
}

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
package quests.Q00022_TragedyInVonHellmannForest;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.QuestTimer;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Util;

import quests.Q00021_HiddenTruth.Q00021_HiddenTruth;

/**
 * Tragedy in Von Hellmann Forest (22)
 * @author Joxit
 */
public final class Q00022_TragedyInVonHellmannForest extends Quest
{
	// NPCs
	private static final int INNOCENTIN = 31328;
	private static final int TIFAREN = 31334;
	private static final int WELL = 31527;
	private static final int GHOST_OF_PRIEST = 31528;
	private static final int GHOST_OF_ADVENTURER = 31529;
	// Mobs
	private static final int[] MOBS =
	{
		21553, // Trampled Man
		21554, // Trampled Man
		21555, // Slaughter Executioner
		21556, // Slaughter Executioner
		21561, // Sacrificed Man
	};
	private static final int SOUL_OF_WELL = 27217;
	// Items
	private static final int CROSS_OF_EINHASAD = 7141;
	private static final int LOST_SKULL_OF_ELF = 7142;
	private static final int LETTER_OF_INNOCENTIN = 7143;
	private static final int JEWEL_OF_ADVENTURER_1 = 7144;
	private static final int JEWEL_OF_ADVENTURER_2 = 7145;
	private static final int SEALED_REPORT_BOX = 7146;
	private static final int REPORT_BOX = 7147;
	// Misc
	private static final int MIN_LVL = 63;
	private static final Location PRIEST_LOC = new Location(38354, -49777, -1128);
	private static final Location SOUL_WELL_LOC = new Location(34706, -54590, -2054);
	private static int _tifarenOwner = 0;
	private static L2Npc _soulWellNpc = null;
	
	public Q00022_TragedyInVonHellmannForest()
	{
		super(22);
		addKillId(MOBS);
		addKillId(SOUL_OF_WELL);
		addAttackId(SOUL_OF_WELL);
		addStartNpc(TIFAREN);
		addTalkId(INNOCENTIN, TIFAREN, WELL, GHOST_OF_PRIEST, GHOST_OF_ADVENTURER);
		registerQuestItems(LOST_SKULL_OF_ELF, CROSS_OF_EINHASAD, REPORT_BOX, JEWEL_OF_ADVENTURER_1, JEWEL_OF_ADVENTURER_2, SEALED_REPORT_BOX);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		switch (event)
		{
			case "31529-02.html":
			case "31529-04.html":
			case "31529-05.html":
			case "31529-06.html":
			case "31529-07.html":
			case "31529-09.html":
			case "31529-13.html":
			case "31529-13a.html":
			case "31528-02.html":
			case "31528-05.html":
			case "31528-06.html":
			case "31528-07.html":
			case "31328-13.html":
			case "31328-06.html":
			case "31328-05.html":
			case "31328-02.html":
			case "31328-07.html":
			case "31328-08.html":
			case "31328-14.html":
			case "31328-15.html":
			case "31328-16.html":
			case "31328-17.html":
			case "31328-18.html":
			case "31334-12.html":
			{
				htmltext = event;
				break;
			}
			case "31334-02.htm":
			{
				if (qs.isCreated())
				{
					final QuestState q21 = player.getQuestState(Q00021_HiddenTruth.class.getSimpleName());
					if ((player.getLevel() >= MIN_LVL) && (q21 != null) && q21.isCompleted())
					{
						htmltext = event;
					}
					else
					{
						htmltext = "31334-03.html";
					}
				}
				break;
			}
			case "31334-04.html":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "31334-06.html":
			{
				if (qs.isCond(3) && hasQuestItems(player, CROSS_OF_EINHASAD))
				{
					htmltext = event;
				}
				else
				{
					qs.setCond(2, true);
					htmltext = "31334-07.html";
				}
				break;
			}
			case "31334-08.html":
			{
				if (qs.isCond(3))
				{
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "31334-13.html":
			{
				final int cond = qs.getCond();
				if (((5 <= cond) && (cond <= 7)) && hasQuestItems(player, CROSS_OF_EINHASAD))
				{
					if (_tifarenOwner == 0)
					{
						_tifarenOwner = player.getObjectId();
						final L2Npc ghost2 = addSpawn(GHOST_OF_PRIEST, PRIEST_LOC, true, 0);
						ghost2.setScriptValue(player.getObjectId());
						startQuestTimer("DESPAWN_GHOST2", 1000 * 120, ghost2, player);
						ghost2.broadcastPacket(new NpcSay(ghost2.getObjectId(), ChatType.NPC_GENERAL, ghost2.getId(), NpcStringId.DID_YOU_CALL_ME_S1).addStringParameter(player.getName()));
						if (((cond == 5) || (cond == 6)) && hasQuestItems(player, LOST_SKULL_OF_ELF))
						{
							takeItems(player, LOST_SKULL_OF_ELF, -1);
							qs.setCond(7, true);
						}
						htmltext = event;
					}
					else
					{
						qs.setCond(6, true);
						htmltext = "31334-14.html";
					}
				}
				break;
			}
			case "31528-04.html":
			{
				if (npc.getScriptValue() == player.getObjectId())
				{
					playSound(player, QuestSound.AMBSOUND_HORROR_03);
					htmltext = event;
				}
				break;
			}
			case "31528-08.html":
			{
				final QuestTimer qt = getQuestTimer("DESPAWN_GHOST2", npc, player);
				if ((qt != null) && (npc.getScriptValue() == player.getObjectId()))
				{
					qt.cancelAndRemove();
					npc.setScriptValue(0);
					startQuestTimer("DESPAWN_GHOST2", 1000 * 3, npc, player);
					qs.setCond(8);
					htmltext = event;
				}
				break;
			}
			case "DESPAWN_GHOST2":
			{
				_tifarenOwner = 0;
				if (npc.getScriptValue() != 0)
				{
					npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.I_M_CONFUSED_MAYBE_IT_S_TIME_TO_GO_BACK));
				}
				npc.deleteMe();
				break;
			}
			case "31328-03.html":
			{
				if (qs.isCond(8))
				{
					takeItems(player, CROSS_OF_EINHASAD, -1);
					htmltext = event;
				}
				break;
			}
			case "31328-09.html":
			{
				if (qs.isCond(8))
				{
					giveItems(player, LETTER_OF_INNOCENTIN, 1);
					qs.setCond(9, true);
					htmltext = event;
				}
				break;
			}
			case "31328-11.html":
			{
				if (qs.isCond(14) && hasQuestItems(player, REPORT_BOX))
				{
					takeItems(player, REPORT_BOX, -1);
					qs.setCond(15, true);
					htmltext = event;
				}
				break;
			}
			case "31328-19.html":
			{
				if (qs.isCond(15))
				{
					qs.setCond(16, true);
					htmltext = event;
				}
				break;
			}
			case "31527-02.html":
			{
				if (qs.isCond(10) && (_soulWellNpc == null))
				{
					_soulWellNpc = addSpawn(SOUL_OF_WELL, SOUL_WELL_LOC, true, 0);
					startQuestTimer("activateSoulOfWell", 90000, _soulWellNpc, player);
					startQuestTimer("despawnSoulOfWell", 120000, _soulWellNpc, player);
					_soulWellNpc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
					playSound(player, QuestSound.SKILLSOUND_ANTARAS_FEAR);
					htmltext = event;
				}
				else
				{
					htmltext = "31527-03.html";
				}
				break;
			}
			case "activateSoulOfWell":
			{
				// this enables onAttack ELSE IF block which allows the player to proceed the quest
				npc.setScriptValue(1);
				break;
			}
			case "despawnSoulOfWell":
			{
				// if the player fails to proceed the quest in 2 minutes, the soul is unspawned
				if (!npc.isDead())
				{
					_soulWellNpc = null;
				}
				npc.deleteMe();
				break;
			}
			case "31529-03.html":
			{
				if (qs.isCond(9) && hasQuestItems(player, LETTER_OF_INNOCENTIN))
				{
					qs.setMemoState(8);
					htmltext = event;
				}
				break;
			}
			case "31529-08.html":
			{
				if (qs.isMemoState(8))
				{
					qs.setMemoState(9);
					htmltext = event;
				}
				break;
			}
			case "31529-11.html":
			{
				if (qs.isMemoState(9))
				{
					giveItems(player, JEWEL_OF_ADVENTURER_1, 1);
					qs.setCond(10, true);
					qs.setMemoState(10);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		final QuestState qs = getQuestState(attacker, false);
		
		if ((qs != null) && qs.isCond(10) && hasQuestItems(attacker, JEWEL_OF_ADVENTURER_1))
		{
			if (qs.isMemoState(10))
			{
				qs.setMemoState(11);
			}
			else if (npc.isScriptValue(1))
			{
				takeItems(attacker, JEWEL_OF_ADVENTURER_1, -1);
				giveItems(attacker, JEWEL_OF_ADVENTURER_2, 1);
				qs.setCond(11, true);
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (Util.checkIfInRange(Config.ALT_PARTY_RANGE, killer, npc, true))
		{
			if (npc.getId() == SOUL_OF_WELL)
			{
				_soulWellNpc = null;
			}
			else
			{
				final QuestState qs = getQuestState(killer, false);
				if ((qs != null) && qs.isCond(4) && hasQuestItems(killer, CROSS_OF_EINHASAD) && !hasQuestItems(killer, LOST_SKULL_OF_ELF) && (getRandom(100) < 10))
				{
					giveItems(killer, LOST_SKULL_OF_ELF, 1);
					qs.setCond(5, true);
				}
			}
		}
		
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		switch (npc.getId())
		{
			case TIFAREN:
			{
				switch (qs.getCond())
				{
					case 0:
					{
						if (qs.isCreated())
						{
							htmltext = "31334-01.htm";
						}
						else if (qs.isCompleted())
						{
							htmltext = getAlreadyCompletedMsg(talker);
						}
						break;
					}
					case 1:
					case 3:
					{
						htmltext = "31334-05.html";
						break;
					}
					case 4:
					case 5:
					{
						if (hasQuestItems(talker, CROSS_OF_EINHASAD))
						{
							if (!hasQuestItems(talker, LOST_SKULL_OF_ELF))
							{
								htmltext = "31334-09.html";
							}
							else if (_tifarenOwner == 0)
							{
								htmltext = "31334-10.html";
							}
							else
							{
								htmltext = "31334-11.html";
							}
						}
						break;
					}
					case 6:
					case 7:
					{
						if (hasQuestItems(talker, CROSS_OF_EINHASAD))
						{
							if (_tifarenOwner == 0)
							{
								htmltext = "31334-17.html";
							}
							else if (_tifarenOwner == talker.getObjectId())
							{
								htmltext = "31334-15.html";
							}
							else
							{
								htmltext = "31334-16.html";
								qs.setCond(6, true);
							}
						}
						break;
					}
					case 8:
					{
						if (hasQuestItems(talker, CROSS_OF_EINHASAD))
						{
							htmltext = "31334-18.html";
						}
						break;
					}
				}
				break;
			}
			case GHOST_OF_PRIEST:
			{
				playSound(talker, QuestSound.AMBSOUND_HORROR_15);
				if (npc.getScriptValue() == talker.getObjectId())
				{
					htmltext = "31528-01.html";
				}
				else
				{
					htmltext = "31528-03.html";
				}
				break;
			}
			case INNOCENTIN:
			{
				switch (qs.getCond())
				{
					case 2:
					{
						if (!hasQuestItems(talker, CROSS_OF_EINHASAD))
						{
							giveItems(talker, CROSS_OF_EINHASAD, 1);
							qs.setCond(3, true);
							htmltext = "31328-01.html";
						}
						break;
					}
					case 3:
					{
						if (hasQuestItems(talker, CROSS_OF_EINHASAD))
						{
							htmltext = "31328-01b.html";
						}
						break;
					}
					case 8:
					{
						if (hasQuestItems(talker, CROSS_OF_EINHASAD))
						{
							htmltext = "31328-02.html";
						}
						else
						{
							htmltext = "31328-04.html";
						}
						break;
					}
					case 9:
					{
						htmltext = "31328-09a.html";
						break;
					}
					case 14:
					{
						if (hasQuestItems(talker, REPORT_BOX))
						{
							htmltext = "31328-10.html";
						}
						break;
					}
					case 15:
					{
						htmltext = "31328-12.html";
						break;
					}
					case 16:
					{
						addExpAndSp(talker, 345966, 31578);
						qs.exitQuest(false, true);
						if (talker.getLevel() >= MIN_LVL)
						{
							htmltext = "31328-20.html";
						}
						else
						{
							htmltext = "31328-21.html";
						}
						break;
					}
				}
				break;
			}
			case WELL:
			{
				switch (qs.getCond())
				{
					case 10:
					{
						if (hasQuestItems(talker, JEWEL_OF_ADVENTURER_1))
						{
							htmltext = "31527-01.html";
							playSound(talker, QuestSound.AMBSOUND_HORROR_01);
						}
						break;
					}
					case 12:
					{
						if (hasQuestItems(talker, JEWEL_OF_ADVENTURER_2) && !hasQuestItems(talker, SEALED_REPORT_BOX))
						{
							giveItems(talker, SEALED_REPORT_BOX, 1);
							qs.setCond(13, true);
							htmltext = "31527-04.html";
						}
						break;
					}
					case 13:
					case 14:
					case 15:
					case 16:
					{
						htmltext = "31527-05.html";
						break;
					}
				}
				break;
			}
			case GHOST_OF_ADVENTURER:
			{
				switch (qs.getCond())
				{
					case 9:
					{
						if (hasQuestItems(talker, LETTER_OF_INNOCENTIN))
						{
							switch (qs.getMemoState())
							{
								case 0:
								{
									htmltext = "31529-01.html";
									break;
								}
								case 8:
								{
									htmltext = "31529-03a.html";
									break;
								}
								case 9:
								{
									htmltext = "31529-10.html";
									break;
								}
								default:
								{
									break;
								}
							}
						}
						break;
					}
					case 10:
					{
						if (hasQuestItems(talker, JEWEL_OF_ADVENTURER_1))
						{
							final int id = qs.getMemoState();
							if (id == 10)
							{
								htmltext = "31529-12.html";
							}
							else if (id == 11)
							{
								htmltext = "31529-14.html";
							}
						}
						break;
					}
					case 11:
					{
						if (hasQuestItems(talker, JEWEL_OF_ADVENTURER_2) && !hasQuestItems(talker, SEALED_REPORT_BOX))
						{
							htmltext = "31529-15.html";
							qs.setCond(12, true);
						}
						break;
					}
					case 13:
					{
						if (hasQuestItems(talker, JEWEL_OF_ADVENTURER_2) && hasQuestItems(talker, SEALED_REPORT_BOX))
						{
							giveItems(talker, REPORT_BOX, 1);
							takeItems(talker, SEALED_REPORT_BOX, -1);
							takeItems(talker, JEWEL_OF_ADVENTURER_2, -1);
							qs.setCond(14, true);
							htmltext = "31529-16.html";
						}
						break;
					}
					case 14:
					{
						if (hasQuestItems(talker, REPORT_BOX))
						{
							htmltext = "31529-17.html";
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
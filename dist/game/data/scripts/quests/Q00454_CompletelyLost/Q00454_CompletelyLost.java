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
package quests.Q00454_CompletelyLost;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.events.EventType;
import com.l2jmobius.gameserver.model.events.ListenerRegisterType;
import com.l2jmobius.gameserver.model.events.annotations.Id;
import com.l2jmobius.gameserver.model.events.annotations.RegisterEvent;
import com.l2jmobius.gameserver.model.events.annotations.RegisterType;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureAttacked;
import com.l2jmobius.gameserver.model.events.returns.TerminateReturn;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Broadcast;
import com.l2jmobius.gameserver.util.Util;

/**
 * Completely Lost (454)
 * @author Zoey76
 */
public final class Q00454_CompletelyLost extends Quest
{
	// NPCs
	private static final int INJURED_SOLDIER = 32738;
	private static final int ERMIAN = 32736;
	// Misc
	private static final int MIN_LEVEL = 84;
	private static final Location MOVE_TO = new Location(-180219, 186341, -10600);
	
	public Q00454_CompletelyLost()
	{
		super(454);
		addStartNpc(INJURED_SOLDIER);
		addTalkId(INJURED_SOLDIER, ERMIAN);
		addSpawnId(ERMIAN);
		addMoveFinishedId(INJURED_SOLDIER);
		addSeeCreatureId(INJURED_SOLDIER);
		addEventReceivedId(INJURED_SOLDIER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "QUEST_TIMER":
			{
				npc.broadcastEvent("SCE_IM_ERMIAN", 300, null);
				startQuestTimer("QUEST_TIMER", 100, npc, null);
				break;
			}
			case "SAY_TIMER1":
			{
				// TODO: npc.changeStatus(3);
				broadcastNpcSay(npc, NpcStringId.GASP);
				break;
			}
			case "SAY_TIMER2":
			{
				broadcastNpcSay(npc, NpcStringId.SOB_TO_SEE_ERMIAN_AGAIN_CAN_I_GO_TO_MY_FAMILY_NOW);
				startQuestTimer("EXPIRED_TIMER", 2000, npc, null);
				break;
			}
			case "CHECK_TIMER":
			{
				final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					final double dist = Util.calculateDistance(npc, leader, false, false);
					if (dist > 1000)
					{
						if (((dist > 5000) && (dist < 6900)) || ((dist > 31000) && (dist < 32000)))
						{
							npc.teleToLocation(leader);
						}
						else if (npc.getVariables().getInt("whisper", 0) == 0)
						{
							whisper(npc, leader, NpcStringId.WHERE_ARE_YOU_I_CAN_T_SEE_ANYTHING);
							npc.getVariables().set("whisper", 1);
						}
						else if (npc.getVariables().getInt("whisper", 0) == 1)
						{
							whisper(npc, leader, NpcStringId.WHERE_ARE_YOU_REALLY_I_CAN_T_FOLLOW_YOU_LIKE_THIS);
							npc.getVariables().set("whisper", 2);
						}
						else if (npc.getVariables().getInt("whisper", 0) == 2)
						{
							whisper(npc, leader, NpcStringId.I_M_SORRY_THIS_IS_IT_FOR_ME);
							npc.sendScriptEvent("SCE_A_SEED_ESCORT_QUEST_FAILURE", npc, null);
						}
					}
				}
				startQuestTimer("CHECK_TIMER", 2000, npc, null);
				break;
			}
			case "TIME_LIMIT1":
			{
				final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					startQuestTimer("TIME_LIMIT2", 150000, npc, null);
					whisper(npc, leader, NpcStringId.IS_IT_STILL_LONG_OFF);
				}
				break;
			}
			case "TIME_LIMIT2":
			{
				final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					startQuestTimer("TIME_LIMIT3", 150000, npc, null);
					whisper(npc, leader, NpcStringId.IS_ERMIAN_WELL_EVEN_I_CAN_T_BELIEVE_THAT_I_SURVIVED_IN_A_PLACE_LIKE_THIS);
				}
				break;
			}
			case "TIME_LIMIT3":
			{
				final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					startQuestTimer("TIME_LIMIT4", 150000, npc, null);
					whisper(npc, leader, NpcStringId.I_DON_T_KNOW_HOW_LONG_IT_S_BEEN_SINCE_I_PARTED_COMPANY_WITH_YOU_TIME_DOESN_T_SEEM_TO_MOVE_IT_JUST_FEELS_TOO_LONG);
				}
				break;
			}
			case "TIME_LIMIT4":
			{
				final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					startQuestTimer("TIME_LIMIT5", 150000, npc, null);
					whisper(npc, leader, NpcStringId.SORRY_TO_SAY_THIS_BUT_THE_PLACE_YOU_STRUCK_ME_BEFORE_NOW_HURTS_GREATLY);
				}
				break;
			}
			case "TIME_LIMIT5":
			{
				final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					whisper(npc, leader, NpcStringId.UGH_I_M_SORRY_IT_LOOKS_LIKE_THIS_IS_IT_FOR_ME_I_WANTED_TO_LIVE_AND_SEE_MY_FAMILY);
				}
				npc.sendScriptEvent("SCE_A_SEED_ESCORT_QUEST_FAILURE", npc, null);
				startQuestTimer("EXPIRED_TIMER", 2000, npc, null);
				break;
			}
			case "EXPIRED_TIMER":
			{
				npc.deleteMe();
				break;
			}
		}
		
		// For NPC-only timers, player is null and no further checks or actions are required.
		if (player == null)
		{
			return null;
		}
		
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "32738-04.htm":
			{
				if (qs.isCreated() && qs.isNowAvailable() && (player.getLevel() >= MIN_LEVEL))
				{
					if (npc.getVariables().getInt("quest_escort", 0) == 0)
					{
						npc.getVariables().set("leader", player);
						npc.getVariables().set("quest_escort", 1);
						if (player.isInParty())
						{
							npc.getVariables().set("partyId", player.getParty().getLeaderObjectId());
						}
						qs.setMemoState(1);
						qs.startQuest();
						htmltext = event;
					}
					else
					{
						final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
						if (leader.isInParty() && leader.getParty().containsPlayer(player))
						{
							qs.startQuest();
							qs.setMemoState(1);
							htmltext = getHtm(player, "32738-04a.htm");
							htmltext = htmltext.replaceAll("leader", leader.getName());
						}
						else
						{
							htmltext = getHtm(player, "32738-01b.htm");
							htmltext = htmltext.replaceAll("leader", leader.getName());
						}
					}
				}
				break;
			}
			case "agree1":
			{
				if (qs.isMemoState(1))
				{
					final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
					if (leader != null)
					{
						if (leader.isInParty())
						{
							qs.setMemoState(2);
							npc.sendScriptEvent("SCE_A_SEED_ESCORT_QUEST_START", npc, null);
							htmltext = "32738-06.html";
						}
						else
						{
							htmltext = "32738-05a.html";
						}
					}
				}
				break;
			}
			case "agree2":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					htmltext = "32738-06.html";
					npc.sendScriptEvent("SCE_A_SEED_ESCORT_QUEST_START", npc, null);
					final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
					if (leader != null)
					{
						if (leader.isInParty())
						{
							for (L2PcInstance member : leader.getParty().getMembers())
							{
								if (member != null)
								{
									final QuestState qsMember = getQuestState(member, false);
									if ((qsMember != null) && qsMember.isMemoState(1) //
										&& (npc.getVariables().getInt("partyId", 0) == leader.getParty().getLeaderObjectId()))
									{
										qsMember.setMemoState(2);
									}
								}
							}
						}
					}
				}
				break;
			}
			case "32738-07.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@RegisterEvent(EventType.ON_CREATURE_ATTACKED)
	@RegisterType(ListenerRegisterType.NPC)
	@Id(INJURED_SOLDIER)
	public TerminateReturn onAttacked(OnCreatureAttacked event)
	{
		final L2Npc npc = (L2Npc) event.getTarget();
		// TODO: npc.changeStatus(2);
		npc.getVariables().set("state", 1);
		npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
		startQuestTimer("SAY_TIMER1", 2000, npc, null);
		
		return new TerminateReturn(true, false, false);
	}
	
	@Override
	public String onEventReceived(String eventName, L2Npc sender, L2Npc receiver, L2Object reference)
	{
		switch (eventName)
		{
			case "SCE_IM_ERMIAN":
			{
				if (receiver.getVariables().getInt("state", 0) == 2)
				{
					receiver.getVariables().set("state", 3);
					receiver.getVariables().set("ermian", sender);
					receiver.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
					addMoveToDesire(receiver, MOVE_TO, 10000000);
					receiver.sendScriptEvent("SCE_A_SEED_ESCORT_QUEST_SUCCESS", receiver, null);
				}
				break;
			}
			case "SCE_A_SEED_ESCORT_QUEST_START":
			{
				final L2PcInstance leader = receiver.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					receiver.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, leader);
				}
				
				startQuestTimer("CHECK_TIMER", 1000, receiver, null);
				startQuestTimer("TIME_LIMIT1", 60000, receiver, null);
				receiver.getVariables().set("state", 2);
				receiver.getVariables().set("quest_escort", 99);
				break;
			}
			case "SCE_A_SEED_ESCORT_QUEST_SUCCESS":
			{
				final L2PcInstance leader = receiver.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					if (leader.isInParty())
					{
						for (L2PcInstance member : leader.getParty().getMembers())
						{
							if (member != null)
							{
								final QuestState qs = getQuestState(member, false);
								if ((qs != null) && qs.isMemoState(2))
								{
									qs.setMemoState(4);
								}
							}
						}
					}
					else
					{
						final QuestState qs = getQuestState(leader, false);
						if ((qs != null) && qs.isMemoState(2))
						{
							qs.setMemoState(4);
						}
					}
				}
				// Timers cleanup
				cancelQuestTimer("CHECK_TIMER", receiver, null);
				cancelQuestTimer("TIME_LIMIT1", receiver, null);
				cancelQuestTimer("TIME_LIMIT2", receiver, null);
				cancelQuestTimer("TIME_LIMIT3", receiver, null);
				cancelQuestTimer("TIME_LIMIT4", receiver, null);
				cancelQuestTimer("TIME_LIMIT5", receiver, null);
				break;
			}
			case "SCE_A_SEED_ESCORT_QUEST_FAILURE":
			{
				final L2PcInstance leader = receiver.getVariables().getObject("leader", L2PcInstance.class);
				if (leader != null)
				{
					if (leader.isInParty())
					{
						for (L2PcInstance member : leader.getParty().getMembers())
						{
							if (member != null)
							{
								final QuestState qs = getQuestState(member, false);
								if ((qs != null) && qs.isMemoState(2))
								{
									qs.setMemoState(3);
								}
							}
						}
					}
					else
					{
						final QuestState qs = getQuestState(leader, false);
						if ((qs != null) && qs.isMemoState(2))
						{
							qs.setMemoState(3);
						}
					}
				}
				receiver.deleteMe();
				// Timers cleanup
				cancelQuestTimer("CHECK_TIMER", receiver, null);
				cancelQuestTimer("TIME_LIMIT1", receiver, null);
				cancelQuestTimer("TIME_LIMIT2", receiver, null);
				cancelQuestTimer("TIME_LIMIT3", receiver, null);
				cancelQuestTimer("TIME_LIMIT4", receiver, null);
				cancelQuestTimer("TIME_LIMIT5", receiver, null);
				break;
			}
		}
		return super.onEventReceived(eventName, sender, receiver, reference);
	}
	
	@Override
	public void onMoveFinished(L2Npc npc)
	{
		final L2Npc ermian = npc.getVariables().getObject("ermian", L2Npc.class);
		if (ermian != null)
		{
			npc.setHeading(Util.calculateHeadingFrom(npc, ermian));
			startQuestTimer("SAY_TIMER2", 2000, npc, null);
		}
	}
	
	@Override
	public String onSeeCreature(L2Npc npc, L2Character creature, boolean isSummon)
	{
		if (creature.isPlayer() && (npc.getVariables().getInt("state", 0) == 0))
		{
			addAttackDesire(npc, creature.getActingPlayer(), 10);
		}
		return super.onSeeCreature(npc, creature, isSummon);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		startQuestTimer("QUEST_TIMER", 1000, npc, null);
		return super.onSpawn(npc);
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
					htmltext = "32738-02.htm";
					break;
				}
				qs.setState(State.CREATED);
			}
			case State.CREATED:
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					final int quest_escort = npc.getVariables().getInt("quest_escort", 0);
					if (quest_escort == 0)
					{
						htmltext = "32738-01.htm";
					}
					else if (quest_escort == 99)
					{
						htmltext = "32738-01c.htm";
					}
					else
					{
						final L2PcInstance leader = npc.getVariables().getObject("leader", L2PcInstance.class);
						if (leader.isInParty() && leader.getParty().containsPlayer(player))
						{
							htmltext = getHtm(player, "32738-01a.htm");
							htmltext = htmltext.replaceAll("leader", leader.getName());
							htmltext = htmltext.replaceAll("name", player.getName());
						}
						else
						{
							htmltext = getHtm(player, "32738-01b.htm");
							htmltext = htmltext.replaceAll("leader", leader.getName());
						}
					}
				}
				else
				{
					htmltext = "32738-03.htm";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case INJURED_SOLDIER:
					{
						if (qs.isMemoState(1))
						{
							htmltext = "32738-05.html";
						}
						else if (qs.isMemoState(2))
						{
							htmltext = "32738-08.html";
						}
						break;
					}
					case ERMIAN:
					{
						switch (qs.getMemoState())
						{
							case 1:
							case 2:
							{
								htmltext = "32736-01.html";
								break;
							}
							case 3:
							{
								qs.exitQuest(QuestType.DAILY, true);
								htmltext = "32736-02.html";
								break;
							}
							case 4:
							{
								final int group = getRandom(3);
								final int chance = getRandom(100);
								if (group == 0)
								{
									if (getRandomBoolean())
									{
										if (chance < 11)
										{
											giveItems(player, 15792, 1); // Recipe - Sealed Vesper Helmet (60%)
										}
										else if ((chance <= 11) && (chance < 22))
										{
											giveItems(player, 15798, 1); // Recipe - Sealed Vesper Gaiter (60%)
										}
										else if ((chance <= 22) && (chance < 33))
										{
											giveItems(player, 15795, 1); // Recipe - Sealed Vesper Breastplate (60%)
										}
										else if ((chance <= 33) && (chance < 44))
										{
											giveItems(player, 15801, 1); // Recipe - Sealed Vesper Gauntlet (60%)
										}
										else if ((chance <= 44) && (chance < 55))
										{
											giveItems(player, 15808, 1); // Recipe - Sealed Vesper Shield (60%)
										}
										else if ((chance <= 55) && (chance < 66))
										{
											giveItems(player, 15804, 1); // Recipe - Sealed Vesper Boots (60%)
										}
										else if ((chance <= 66) && (chance < 77))
										{
											giveItems(player, 15809, 1); // Recipe - Sealed Vesper Ring (70%)
										}
										else if ((chance <= 77) && (chance < 88))
										{
											giveItems(player, 15810, 1); // Recipe - Sealed Vesper Earring (70%)
										}
										else
										{
											giveItems(player, 15811, 1); // Recipe - Sealed Vesper Necklace (70%)
										}
									}
									else
									{
										if (chance < 11)
										{
											giveItems(player, 15660, 3); // Sealed Vesper Helmet Piece
										}
										else if ((chance <= 11) && (chance < 22))
										{
											giveItems(player, 15666, 3); // Sealed Vesper Gaiter Piece
										}
										else if ((chance <= 22) && (chance < 33))
										{
											giveItems(player, 15663, 3); // Sealed Vesper Breastplate Piece
										}
										else if ((chance <= 33) && (chance < 44))
										{
											giveItems(player, 15667, 3); // Sealed Vesper Gauntlet Piece
										}
										else if ((chance <= 44) && (chance < 55))
										{
											giveItems(player, 15669, 3); // Sealed Vesper Verteidiger Piece
										}
										else if ((chance <= 55) && (chance < 66))
										{
											giveItems(player, 15668, 3); // Sealed Vesper Boots Piece
										}
										else if ((chance <= 66) && (chance < 77))
										{
											giveItems(player, 15769, 3); // Sealed Vesper Ring Gem
										}
										else if ((chance <= 77) && (chance < 88))
										{
											giveItems(player, 15770, 3); // Sealed Vesper Earring Gem
										}
										else
										{
											giveItems(player, 15771, 3); // Sealed Vesper Necklace Gem
										}
									}
								}
								else if (group == 1)
								{
									if (getRandomBoolean())
									{
										if (chance < 12)
										{
											giveItems(player, 15805, 1); // Recipe - Sealed Vesper Leather Boots (60%)
										}
										else if ((chance <= 12) && (chance < 24))
										{
											giveItems(player, 15796, 1); // Recipe - Sealed Vesper Leather Breastplate (60%)
										}
										else if ((chance <= 24) && (chance < 36))
										{
											giveItems(player, 15793, 1); // Recipe - Sealed Vesper Leather Helmet (60%)
										}
										else if ((chance <= 36) && (chance < 48))
										{
											giveItems(player, 15799, 1); // Recipe - Sealed Vesper Leather Legging (60%)
										}
										else if ((chance <= 48) && (chance < 60))
										{
											giveItems(player, 15802, 1); // Recipe - Sealed Vesper Leather Gloves (60%)
										}
										else if ((chance <= 60) && (chance < 72))
										{
											giveItems(player, 15809, 1); // Recipe - Sealed Vesper Ring (70%)
										}
										else if ((chance <= 72) && (chance < 84))
										{
											giveItems(player, 15810, 1); // Recipe - Sealed Vesper Earring (70%)
										}
										else
										{
											giveItems(player, 15811, 1); // Recipe - Sealed Vesper Necklace (70%)
										}
									}
									else
									{
										if (chance < 12)
										{
											giveItems(player, 15672, 3); // Sealed Vesper Leather Boots Piece
										}
										else if ((chance <= 12) && (chance < 24))
										{
											giveItems(player, 15664, 3); // Sealed Vesper Leather Breastplate Piece
										}
										else if ((chance <= 24) && (chance < 36))
										{
											giveItems(player, 15661, 3); // Sealed Vesper Leather Helmet Piece
										}
										else if ((chance <= 36) && (chance < 48))
										{
											giveItems(player, 15670, 3); // Sealed Vesper Leather Legging Piece
										}
										else if ((chance <= 48) && (chance < 60))
										{
											giveItems(player, 15671, 3); // Sealed Vesper Leather Gloves Piece
										}
										else if ((chance <= 60) && (chance < 72))
										{
											giveItems(player, 15769, 3); // Sealed Vesper Ring Gem
										}
										else if ((chance <= 72) && (chance < 84))
										{
											giveItems(player, 15770, 3); // Sealed Vesper Earring Gem
										}
										else
										{
											giveItems(player, 15771, 3); // Sealed Vesper Necklace Gem
										}
									}
								}
								else if (getRandomBoolean())
								{
									if (chance < 11)
									{
										giveItems(player, 15800, 1);
									}
									else if ((chance <= 11) && (chance < 22)) // Recipe - Sealed Vesper Stockings (60%)
									{
										giveItems(player, 15803, 1); // Recipe - Sealed Vesper Gloves (60%)
									}
									else if ((chance <= 22) && (chance < 33))
									{
										giveItems(player, 15806, 1); // Recipe - Sealed Vesper Shoes (60%)
									}
									else if ((chance <= 33) && (chance < 44))
									{
										giveItems(player, 15807, 1); // Recipe - Sealed Vesper Sigil (60%)
									}
									else if ((chance <= 44) && (chance < 55))
									{
										giveItems(player, 15797, 1); // Recipe - Sealed Vesper Tunic (60%)
									}
									else if ((chance <= 55) && (chance < 66))
									{
										giveItems(player, 15794, 1); // Recipe - Sealed Vesper Circlet (60%)
									}
									else if ((chance <= 66) && (chance < 77))
									{
										giveItems(player, 15809, 1); // Recipe - Sealed Vesper Ring (70%)
									}
									else if ((chance <= 77) && (chance < 88))
									{
										giveItems(player, 15810, 1); // Recipe - Sealed Vesper Earring (70%)
									}
									else
									{
										giveItems(player, 15811, 1); // Recipe - Sealed Vesper Necklace (70%)
									}
								}
								else
								{
									if (chance < 11)
									{
										giveItems(player, 15673, 3); // Sealed Vesper Stockings Piece
									}
									else if ((chance <= 11) && (chance < 22))
									{
										giveItems(player, 15674, 3); // Sealed Vesper Gloves Piece
									}
									else if ((chance <= 22) && (chance < 33))
									{
										giveItems(player, 15675, 3); // Sealed Vesper Shoes Piece
									}
									else if ((chance <= 33) && (chance < 44))
									{
										giveItems(player, 15691, 3); // Sealed Vesper Sigil Piece
									}
									else if ((chance <= 44) && (chance < 55))
									{
										giveItems(player, 15665, 3); // Sealed Vesper Tunic Piece
									}
									else if ((chance <= 55) && (chance < 66))
									{
										giveItems(player, 15662, 3); // Sealed Vesper Circlet Piece
									}
									else if ((chance <= 66) && (chance < 77))
									{
										giveItems(player, 15769, 3); // Sealed Vesper Ring Gem
									}
									else if ((chance <= 77) && (chance < 88))
									{
										giveItems(player, 15770, 3); // Sealed Vesper Earring Gem
									}
									else
									{
										giveItems(player, 15771, 3); // Sealed Vesper Necklace Gem
									}
								}
								
								qs.exitQuest(QuestType.DAILY, true);
								htmltext = "32736-03.html";
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	/**
	 * Broadcast NPC string to all known players.
	 * @param npc the NPC
	 * @param stringId the NPC String
	 */
	private static void broadcastNpcSay(L2Npc npc, NpcStringId stringId)
	{
		Broadcast.toKnownPlayers(npc, new NpcSay(npc, ChatType.NPC_GENERAL, stringId));
	}
	
	/**
	 * Send a whisper to the given player.
	 * @param npc the NPC
	 * @param player the player
	 * @param stringId the NPC String
	 */
	private static void whisper(L2Npc npc, L2PcInstance player, NpcStringId stringId)
	{
		player.sendPacket(new NpcSay(npc.getObjectId(), ChatType.WHISPER, npc.getId(), stringId));
	}
}

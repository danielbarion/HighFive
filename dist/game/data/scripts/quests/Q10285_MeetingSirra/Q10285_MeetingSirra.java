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
package quests.Q10285_MeetingSirra;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

import quests.Q10284_AcquisitionOfDivineSword.Q10284_AcquisitionOfDivineSword;

/**
 * Meeting Sirra (10285)
 * @author Adry_85
 */
public final class Q10285_MeetingSirra extends Quest
{
	// NPCs
	private static final int RAFFORTY = 32020;
	private static final int FREYAS_STEWARD = 32029;
	private static final int JINIA = 32760;
	private static final int KEGOR = 32761;
	private static final int SIRRA = 32762;
	private static final int JINIA2 = 32781;
	// Misc
	private static final int MIN_LEVEL = 82;
	// Locations
	private static final Location EXIT_LOC = new Location(113793, -109342, -845, 0);
	private static final Location FREYA_LOC = new Location(103045, -124361, -2768, 0);
	
	public Q10285_MeetingSirra()
	{
		super(10285);
		addStartNpc(RAFFORTY);
		addTalkId(RAFFORTY, JINIA, KEGOR, SIRRA, JINIA2, FREYAS_STEWARD);
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
			case "32020-02.htm":
			{
				htmltext = event;
				break;
			}
			case "32020-03.htm":
			{
				qs.startQuest();
				qs.setMemoState(1);
				htmltext = event;
				break;
			}
			case "32760-02.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 0))
				{
					qs.set("ex", 1);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32760-05.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 2))
				{
					htmltext = event;
				}
				break;
			}
			case "32760-06.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 2))
				{
					final L2Npc sirra = addSpawn(SIRRA, -23905, -8790, -5384, 56238, false, 0, false, npc.getInstanceId());
					sirra.broadcastPacket(new NpcSay(sirra.getObjectId(), ChatType.NPC_GENERAL, sirra.getId(), NpcStringId.THERE_S_NOTHING_YOU_CAN_T_SAY_I_CAN_T_LISTEN_TO_YOU_ANYMORE));
					qs.set("ex", 3);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "32760-09.html":
			case "32760-10.html":
			case "32760-11.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 4))
				{
					htmltext = event;
				}
				break;
			}
			case "32760-12.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 4))
				{
					qs.set("ex", 5);
					qs.setCond(7, true);
					htmltext = event;
				}
				break;
			}
			case "32760-13.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 5))
				{
					qs.unset("ex");
					qs.setMemoState(2);
					final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
					world.removeAllowed(player);
					player.setInstanceId(0);
					htmltext = event;
				}
				break;
			}
			case "32760-14.html":
			{
				if (qs.isMemoState(2))
				{
					player.teleToLocation(EXIT_LOC, 0);
					htmltext = event;
				}
				break;
			}
			case "32761-02.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 1))
				{
					qs.set("ex", 2);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "32762-02.html":
			case "32762-03.html":
			case "32762-04.html":
			case "32762-05.html":
			case "32762-06.html":
			case "32762-07.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 3))
				{
					htmltext = event;
				}
				break;
			}
			case "32762-08.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 3))
				{
					qs.set("ex", 4);
					qs.setCond(6, true);
					htmltext = event;
					npc.deleteMe();
				}
				break;
			}
			case "32781-02.html":
			case "32781-03.html":
			{
				if (qs.isMemoState(2))
				{
					htmltext = event;
				}
				break;
			}
			case "TELEPORT":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					player.teleToLocation(FREYA_LOC, 0);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				if (npc.getId() == RAFFORTY)
				{
					htmltext = "32020-05.htm";
				}
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == RAFFORTY)
				{
					qs = player.getQuestState(Q10284_AcquisitionOfDivineSword.class.getSimpleName());
					htmltext = ((player.getLevel() >= MIN_LEVEL) && (qs != null) && qs.isCompleted()) ? "32020-01.htm" : "32020-04.htm";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case RAFFORTY:
					{
						switch (qs.getMemoState())
						{
							case 1:
							{
								htmltext = (player.getLevel() >= MIN_LEVEL) ? "32020-06.html" : "32020-09.html";
								break;
							}
							case 2:
							{
								htmltext = "32020-07.html";
								break;
							}
							case 3:
							{
								giveAdena(player, 283425, true);
								addExpAndSp(player, 939075, 83855);
								qs.exitQuest(false, true);
								htmltext = "32020-08.html";
								break;
							}
						}
						break;
					}
					case JINIA:
					{
						if (qs.isMemoState(1))
						{
							switch (qs.getInt("ex"))
							{
								case 0:
								{
									htmltext = "32760-01.html";
									break;
								}
								case 1:
								{
									htmltext = "32760-03.html";
									break;
								}
								case 2:
								{
									htmltext = "32760-04.html";
									break;
								}
								case 3:
								{
									htmltext = "32760-07.html";
									break;
								}
								case 4:
								{
									htmltext = "32760-08.html";
									break;
								}
								case 5:
								{
									htmltext = "32760-15.html";
									break;
								}
							}
						}
						break;
					}
					case KEGOR:
					{
						if (qs.isMemoState(1))
						{
							switch (qs.getInt("ex"))
							{
								case 1:
								{
									htmltext = "32761-01.html";
									break;
								}
								case 2:
								{
									htmltext = "32761-03.html";
									break;
								}
								case 3:
								{
									htmltext = "32761-04.html";
									break;
								}
							}
						}
						break;
					}
					case SIRRA:
					{
						if (qs.isMemoState(1))
						{
							final int state = qs.getInt("ex");
							if (state == 3)
							{
								htmltext = "32762-01.html";
							}
							else if (state == 4)
							{
								htmltext = "32762-09.html";
							}
						}
						break;
					}
					case JINIA2:
					{
						if (qs.isMemoState(2))
						{
							htmltext = "32781-01.html";
						}
						else if (qs.isMemoState(3))
						{
							htmltext = "32781-04.html";
						}
						break;
					}
					case FREYAS_STEWARD:
					{
						if (qs.isMemoState(2))
						{
							htmltext = "32029-01.html";
							qs.setCond(8, true);
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

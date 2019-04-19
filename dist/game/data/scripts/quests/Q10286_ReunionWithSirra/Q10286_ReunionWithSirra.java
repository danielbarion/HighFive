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
package quests.Q10286_ReunionWithSirra;

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

import quests.Q10285_MeetingSirra.Q10285_MeetingSirra;

/**
 * Reunion with Sirra (10286)
 * @author Adry_85
 */
public final class Q10286_ReunionWithSirra extends Quest
{
	// NPCs
	private static final int RAFFORTY = 32020;
	private static final int JINIA = 32760;
	private static final int SIRRA = 32762;
	private static final int JINIA2 = 32781;
	// Item
	private static final int BLACK_FROZEN_CORE = 15470;
	// Misc
	private static final int MIN_LEVEL = 82;
	// Location
	private static final Location EXIT_LOC = new Location(113793, -109342, -845, 0);
	
	public Q10286_ReunionWithSirra()
	{
		super(10286);
		addStartNpc(RAFFORTY);
		addTalkId(RAFFORTY, JINIA, SIRRA, JINIA2);
		registerQuestItems(BLACK_FROZEN_CORE);
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
				qs.startQuest();
				qs.setMemoState(1);
				htmltext = event;
				break;
			}
			case "32020-03.html":
			case "32760-02.html":
			case "32760-03.html":
			case "32760-04.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32760-05.html":
			{
				if (qs.isMemoState(1))
				{
					final L2Npc sirra = addSpawn(SIRRA, -23905, -8790, -5384, 56238, false, 0, false, npc.getInstanceId());
					sirra.broadcastPacket(new NpcSay(sirra.getObjectId(), ChatType.NPC_GENERAL, sirra.getId(), NpcStringId.YOU_ADVANCED_BRAVELY_BUT_GOT_SUCH_A_TINY_RESULT_HOHOHO));
					qs.set("ex", 1);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32760-07.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 2))
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
			case "32760-08.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setCond(5, true);
					player.teleToLocation(EXIT_LOC, 0);
					htmltext = event; // TODO: missing "jinia_npc_q10286_10.htm"
				}
				break;
			}
			case "32762-02.html":
			case "32762-03.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 1))
				{
					htmltext = event;
				}
				break;
			}
			case "32762-04.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex") == 1))
				{
					if (!hasQuestItems(player, BLACK_FROZEN_CORE))
					{
						giveItems(player, BLACK_FROZEN_CORE, 5);
					}
					qs.set("ex", 2);
					qs.setCond(4, true);
					htmltext = event;
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
					htmltext = "32020-05.html";
				}
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == RAFFORTY)
				{
					qs = player.getQuestState(Q10285_MeetingSirra.class.getSimpleName());
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
						if (qs.isMemoState(1))
						{
							htmltext = (player.getLevel() >= MIN_LEVEL) ? "32020-06.html" : "32020-08.html";
						}
						else if (qs.isMemoState(2))
						{
							htmltext = "32020-07.html";
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
									htmltext = "32760-05.html";
									break;
								}
								case 2:
								{
									htmltext = "32760-06.html";
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
							if (state == 1)
							{
								htmltext = "32762-01.html";
							}
							else if (state == 2)
							{
								htmltext = "32762-05.html";
							}
						}
						break;
					}
					case JINIA2:
					{
						if (qs.isMemoState(10))
						{
							addExpAndSp(player, 2152200, 181070);
							qs.exitQuest(false, true);
							htmltext = "32781-01.html";
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

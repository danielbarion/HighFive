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
package quests.Q10287_StoryOfThoseLeft;

import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q10286_ReunionWithSirra.Q10286_ReunionWithSirra;

/**
 * Story of Those Left (10287)
 * @author Adry_85
 */
public final class Q10287_StoryOfThoseLeft extends Quest
{
	// NPCs
	private static final int RAFFORTY = 32020;
	private static final int JINIA = 32760;
	private static final int KEGOR = 32761;
	// Misc
	private static final int MIN_LEVEL = 82;
	// Location
	private static final Location EXIT_LOC = new Location(113793, -109342, -845, 0);
	
	public Q10287_StoryOfThoseLeft()
	{
		super(10287);
		addStartNpc(RAFFORTY);
		addTalkId(RAFFORTY, JINIA, KEGOR);
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
			case "32020-08.html":
			{
				if (qs.isMemoState(2))
				{
					htmltext = event;
				}
				break;
			}
			case "32760-02.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32760-03.html":
			{
				if (qs.isMemoState(1))
				{
					qs.set("ex1", 1);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32760-06.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setCond(5, true);
					player.teleToLocation(EXIT_LOC, 0);
					htmltext = event; // TODO: missing "jinia_npc_q10287_06.htm"
				}
				break;
			}
			case "32761-02.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 0))
				{
					htmltext = event;
				}
				break;
			}
			case "32761-03.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 0))
				{
					qs.set("ex2", 1);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "10549":
			case "10550":
			case "10551":
			case "10552":
			case "10553":
			case "14219":
			{
				if (qs.isMemoState(2))
				{
					rewardItems(player, Integer.valueOf(event), 1);
					htmltext = "32020-09.html";
					qs.exitQuest(false, true);
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
					htmltext = "32020-04.html";
				}
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == RAFFORTY)
				{
					qs = player.getQuestState(Q10286_ReunionWithSirra.class.getSimpleName());
					htmltext = ((player.getLevel() >= MIN_LEVEL) && (qs != null) && qs.isCompleted()) ? "32020-01.htm" : "32020-03.htm";
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
							htmltext = (player.getLevel() >= MIN_LEVEL) ? "32020-05.html" : "32020-06.html";
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
							final int state1 = qs.getInt("ex1");
							final int state2 = qs.getInt("ex2");
							if ((state1 == 0) && (state2 == 0))
							{
								htmltext = "32760-01.html";
							}
							else if ((state1 == 1) && (state2 == 0))
							{
								htmltext = "32760-04.html";
							}
							else if ((state1 == 1) && (state2 == 1))
							{
								qs.setCond(5, true);
								qs.setMemoState(2);
								qs.unset("ex1");
								qs.unset("ex2");
								final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
								world.removeAllowed(player);
								player.setInstanceId(0);
								htmltext = "32760-05.html";
							}
						}
						break;
					}
					case KEGOR:
					{
						if (qs.isMemoState(1))
						{
							final int state1 = qs.getInt("ex1");
							final int state2 = qs.getInt("ex2");
							if ((state1 == 1) && (state2 == 0))
							{
								htmltext = "32761-01.html";
							}
							else if ((state1 == 0) && (state2 == 0))
							{
								htmltext = "32761-04.html";
							}
							else if ((state1 == 1) && (state2 == 1))
							{
								htmltext = "32761-05.html";
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
}

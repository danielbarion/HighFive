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
package quests.Q10284_AcquisitionOfDivineSword;

import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q10283_RequestOfIceMerchant.Q10283_RequestOfIceMerchant;

/**
 * Acquisition of Divine Sword (10284)
 * @author Adry_85
 */
public final class Q10284_AcquisitionOfDivineSword extends Quest
{
	// NPCs
	private static final int RAFFORTY = 32020;
	private static final int KRUN = 32653;
	private static final int TARUN = 32654;
	private static final int JINIA = 32760;
	// Misc
	private static final int MIN_LEVEL = 82;
	// Item
	private static final int COLD_RESISTANCE_POTION = 15514;
	// Location
	private static final Location EXIT_LOC = new Location(113793, -109342, -845, 0);
	
	public Q10284_AcquisitionOfDivineSword()
	{
		super(10284);
		addStartNpc(RAFFORTY);
		addTalkId(RAFFORTY, JINIA, TARUN, KRUN);
		registerQuestItems(COLD_RESISTANCE_POTION);
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
			case "32020-02.html":
			{
				qs.startQuest();
				qs.setMemoState(1);
				htmltext = event;
				break;
			}
			case "32020-03.html":
			case "32760-02a.html":
			case "32760-02b.html":
			case "32760-03a.html":
			case "32760-03b.html":
			case "32760-04a.html":
			case "32760-04b.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32760-02c.html":
			{
				if (qs.isMemoState(1))
				{
					qs.set("ex1", 1);
					htmltext = event;
				}
				break;
			}
			case "another_story":
			{
				if (qs.isMemoState(1))
				{
					if ((qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 0) && (qs.getInt("ex3") == 0))
					{
						htmltext = "32760-05a.html";
					}
					else if ((qs.getInt("ex1") == 0) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 0))
					{
						htmltext = "32760-05b.html";
					}
					else if ((qs.getInt("ex1") == 0) && (qs.getInt("ex2") == 0) && (qs.getInt("ex3") == 1))
					{
						htmltext = "32760-05c.html";
					}
					else if ((qs.getInt("ex1") == 0) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 1))
					{
						htmltext = "32760-05d.html";
					}
					else if ((qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 0) && (qs.getInt("ex3") == 1))
					{
						htmltext = "32760-05e.html";
					}
					else if ((qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 0))
					{
						htmltext = "32760-05f.html";
					}
					else if ((qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 1))
					{
						htmltext = "32760-05g.html";
					}
				}
				break;
			}
			case "32760-03c.html":
			{
				if (qs.isMemoState(1))
				{
					qs.set("ex2", 1);
					htmltext = event;
				}
				break;
			}
			case "32760-04c.html":
			{
				if (qs.isMemoState(1))
				{
					qs.set("ex3", 1);
					htmltext = event;
				}
				break;
			}
			case "32760-06.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 1))
				{
					htmltext = event;
				}
				break;
			}
			case "32760-07.html":
			{
				if (qs.isMemoState(1) && (qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 1))
				{
					qs.unset("ex1");
					qs.unset("ex2");
					qs.unset("ex3");
					qs.setCond(3, true);
					qs.setMemoState(2);
					final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
					world.removeAllowed(player);
					player.setInstanceId(0);
					htmltext = event;
				}
				break;
			}
			case "exit_instance":
			{
				if (qs.isMemoState(2))
				{
					player.teleToLocation(EXIT_LOC, 0);
				}
				break;
			}
			case "32654-02.html":
			case "32654-03.html":
			case "32653-02.html":
			case "32653-03.html":
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
					qs = player.getQuestState(Q10283_RequestOfIceMerchant.class.getSimpleName());
					htmltext = ((player.getLevel() >= MIN_LEVEL) && (qs != null) && qs.isCompleted()) ? "32020-01.htm" : "32020-04.html";
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
								htmltext = (player.getLevel() >= MIN_LEVEL) ? "32020-06.html" : "32020-08.html";
								break;
							}
							case 2:
							{
								htmltext = "32020-07.html";
								break;
							}
						}
						break;
					}
					case JINIA:
					{
						if (qs.isMemoState(1))
						{
							if ((qs.getInt("ex1") == 0) && (qs.getInt("ex2") == 0) && (qs.getInt("ex3") == 0))
							{
								htmltext = "32760-01.html";
							}
							else if ((qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 0) && (qs.getInt("ex3") == 0))
							{
								htmltext = "32760-01a.html";
							}
							else if ((qs.getInt("ex1") == 0) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 0))
							{
								htmltext = "32760-01b.html";
							}
							else if ((qs.getInt("ex1") == 0) && (qs.getInt("ex2") == 0) && (qs.getInt("ex3") == 1))
							{
								htmltext = "32760-01c.html";
							}
							else if ((qs.getInt("ex1") == 0) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 1))
							{
								htmltext = "32760-01d.html";
							}
							else if ((qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 0) && (qs.getInt("ex3") == 1))
							{
								htmltext = "32760-01e.html";
							}
							else if ((qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 0))
							{
								htmltext = "32760-01f.html";
							}
							else if ((qs.getInt("ex1") == 1) && (qs.getInt("ex2") == 1) && (qs.getInt("ex3") == 1))
							{
								htmltext = "32760-01g.html";
							}
						}
						break;
					}
					case TARUN:
					{
						switch (qs.getMemoState())
						{
							case 2:
							{
								htmltext = (player.getLevel() >= MIN_LEVEL) ? "32654-01.html" : "32654-05.html";
								break;
							}
							case 3:
							{
								giveAdena(player, 296425, true);
								addExpAndSp(player, 921805, 82230);
								qs.exitQuest(false, true);
								htmltext = "32654-04.html";
								break;
							}
						}
						break;
					}
					case KRUN:
					{
						switch (qs.getMemoState())
						{
							case 2:
							{
								htmltext = (player.getLevel() >= MIN_LEVEL) ? "32653-01.html" : "32653-05.html";
								break;
							}
							case 3:
							{
								giveAdena(player, 296425, true);
								addExpAndSp(player, 921805, 82230);
								qs.exitQuest(false, true);
								htmltext = "32653-04.html";
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
}

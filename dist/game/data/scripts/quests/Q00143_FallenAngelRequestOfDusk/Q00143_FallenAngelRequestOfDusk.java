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
package quests.Q00143_FallenAngelRequestOfDusk;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Fallen Angel - Request of Dusk (143)
 * @author Nono
 */
public class Q00143_FallenAngelRequestOfDusk extends Quest
{
	// NPCs
	private static final int TOBIAS = 30297;
	private static final int CASIAN = 30612;
	private static final int NATOOLS = 30894;
	private static final int ROCK = 32368;
	private static final int ANGEL = 32369;
	// Items
	private static final int SEALED_PROPHECY_PATH_OF_THE_GOD = 10354;
	private static final int PROPHECY_PATH_OF_THE_GOD = 10355;
	private static final int EMPTY_SOUND_CRYSTAL = 10356;
	private static final int ANGEL_MEDICINE = 10357;
	private static final int ANGELS_MESSAGE = 10358;
	// Misc
	private static final int MAX_REWARD_LEVEL = 43;
	private boolean isAngelSpawned = false;
	
	public Q00143_FallenAngelRequestOfDusk()
	{
		super(143);
		addTalkId(NATOOLS, TOBIAS, CASIAN, ROCK, ANGEL);
		registerQuestItems(SEALED_PROPHECY_PATH_OF_THE_GOD, PROPHECY_PATH_OF_THE_GOD, EMPTY_SOUND_CRYSTAL, ANGEL_MEDICINE, ANGELS_MESSAGE);
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
			case "30894-02.html":
			case "30297-04.html":
			case "30612-05.html":
			case "30612-06.html":
			case "30612-07.html":
			case "30612-08.html":
			case "32369-04.html":
			case "32369-05.html":
			case "32369-07.html":
			case "32369-08.html":
			case "32369-09.html":
			case "32369-10.html":
			{
				break;
			}
			case "30894-01.html":
			{
				qs.startQuest();
				break;
			}
			case "30894-03.html":
			{
				qs.setCond(2, true);
				giveItems(player, SEALED_PROPHECY_PATH_OF_THE_GOD, 1);
				break;
			}
			case "30297-03.html":
			{
				takeItems(player, SEALED_PROPHECY_PATH_OF_THE_GOD, -1);
				qs.set("talk", "1");
				break;
			}
			case "30297-05.html":
			{
				qs.unset("talk");
				qs.setCond(3, true);
				giveItems(player, PROPHECY_PATH_OF_THE_GOD, 1);
				giveItems(player, EMPTY_SOUND_CRYSTAL, 1);
				break;
			}
			case "30612-03.html":
			{
				takeItems(player, PROPHECY_PATH_OF_THE_GOD, -1);
				qs.set("talk", "1");
				break;
			}
			case "30612-09.html":
			{
				qs.unset("talk");
				qs.setCond(4, true);
				giveItems(player, ANGEL_MEDICINE, 1);
				break;
			}
			case "32368-04.html":
			{
				if (isAngelSpawned)
				{
					return "32368-03.html";
				}
				addSpawn(ANGEL, npc.getX() + 100, npc.getY() + 100, npc.getZ(), 0, false, 120000);
				startQuestTimer("despawn", 120000, null, player);
				isAngelSpawned = true;
				break;
			}
			case "32369-03.html":
			{
				takeItems(player, ANGEL_MEDICINE, -1);
				qs.set("talk", "1");
				break;
			}
			case "32369-06.html":
			{
				qs.set("talk", "2");
				break;
			}
			case "32369-11.html":
			{
				qs.unset("talk");
				takeItems(player, EMPTY_SOUND_CRYSTAL, -1);
				giveItems(player, ANGELS_MESSAGE, 1);
				qs.setCond(5, true);
				npc.deleteMe();
				isAngelSpawned = false;
				break;
			}
			case "despawn":
			{
				if (isAngelSpawned)
				{
					isAngelSpawned = false;
				}
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
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case NATOOLS:
			{
				switch (qs.getState())
				{
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "30894-01.html";
								break;
							}
							default:
							{
								htmltext = "30894-04.html";
								break;
							}
						}
						break;
					}
					case State.COMPLETED:
					{
						htmltext = getAlreadyCompletedMsg(player);
						break;
					}
				}
				break;
			}
			case TOBIAS:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30297-01.html";
							break;
						}
						case 2:
						{
							htmltext = qs.isSet("talk") ? "30297-04.html" : "30297-02.html";
							break;
						}
						case 3:
						case 4:
						{
							htmltext = "30297-06.html";
							break;
						}
						case 5:
						{
							giveAdena(player, 89046, true);
							if (player.getLevel() <= MAX_REWARD_LEVEL)
							{
								addExpAndSp(player, 223036, 13901);
							}
							qs.exitQuest(false, true);
							htmltext = "30297-07.html";
							break;
						}
					}
				}
				break;
			}
			case CASIAN:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						{
							htmltext = "30612-01.html";
							break;
						}
						case 3:
						{
							htmltext = qs.isSet("talk") ? "30612-04.html" : "30612-02.html";
							break;
						}
						default:
						{
							htmltext = "30612-10.html";
							break;
						}
					}
				}
				break;
			}
			case ROCK:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						{
							htmltext = "32368-01.html";
							break;
						}
						case 4:
						{
							htmltext = "32368-02.html";
							break;
						}
						case 5:
						{
							htmltext = "32368-05.html";
							break;
						}
					}
				}
				break;
			}
			case ANGEL:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						{
							htmltext = "32369-01.html";
							break;
						}
						case 4:
						{
							if (qs.getInt("talk") == 1)
							{
								htmltext = "32369-04.html";
							}
							else if (qs.getInt("talk") == 2)
							{
								htmltext = "32369-07.html";
							}
							else
							{
								htmltext = "32369-02.html";
							}
							break;
						}
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
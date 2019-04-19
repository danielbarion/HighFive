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
package quests.Q00142_FallenAngelRequestOfDawn;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Fallen Angel - Request of Dawn (142)
 * @author Nono
 */
public class Q00142_FallenAngelRequestOfDawn extends Quest
{
	// NPCs
	private static final int RAYMOND = 30289;
	private static final int CASIAN = 30612;
	private static final int NATOOLS = 30894;
	private static final int ROCK = 32368;
	// Monsters
	private static final int FALLEN_ANGEL = 27338;
	private static final Map<Integer, Integer> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20079, 338); // Ant
		MOBS.put(20080, 363); // Ant Captain
		MOBS.put(20081, 611); // Ant Overseer
		MOBS.put(20082, 371); // Ant Recruit
		MOBS.put(20084, 421); // Ant Patrol
		MOBS.put(20086, 371); // Ant Guard
		MOBS.put(20087, 900); // Ant Soldier
		MOBS.put(20088, 1000); // Ant Warrior Captain
		MOBS.put(20089, 431); // Noble Ant
		MOBS.put(20090, 917); // Noble Ant Leader
	}
	
	// Items
	private static final int CRYPTOGRAM_OF_THE_ANGEL_SEARCH = 10351;
	private static final int PROPHECY_FRAGMENT = 10352;
	private static final int FALLEN_ANGEL_BLOOD = 10353;
	// Misc
	private static final int MAX_REWARD_LEVEL = 43;
	private static final int FRAGMENT_COUNT = 30;
	private boolean isAngelSpawned = false;
	
	public Q00142_FallenAngelRequestOfDawn()
	{
		super(142);
		addTalkId(NATOOLS, RAYMOND, CASIAN, ROCK);
		addKillId(MOBS.keySet());
		addKillId(FALLEN_ANGEL);
		registerQuestItems(CRYPTOGRAM_OF_THE_ANGEL_SEARCH, PROPHECY_FRAGMENT, FALLEN_ANGEL_BLOOD);
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
			case "30289-03.html":
			case "30289-04.html":
			case "30612-03.html":
			case "30612-04.html":
			case "30612-06.html":
			case "30612-07.html":
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
				giveItems(player, CRYPTOGRAM_OF_THE_ANGEL_SEARCH, 1);
				qs.setCond(2, true);
				break;
			}
			case "30289-05.html":
			{
				qs.unset("talk");
				qs.setCond(3, true);
				break;
			}
			case "30612-05.html":
			{
				qs.set("talk", "2");
				break;
			}
			case "30612-08.html":
			{
				qs.unset("talk");
				qs.setCond(4, true);
				break;
			}
			case "32368-04.html":
			{
				if (isAngelSpawned)
				{
					return "32368-03.html";
				}
				addSpawn(FALLEN_ANGEL, npc.getX() + 100, npc.getY() + 100, npc.getZ(), 0, false, 120000);
				isAngelSpawned = true;
				startQuestTimer("despawn", 120000, null, player);
				break;
			}
			case "despawn":
			{
				if (isAngelSpawned)
				{
					isAngelSpawned = false;
				}
				break;
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
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs;
		if (npc.getId() == FALLEN_ANGEL)
		{
			qs = getQuestState(player, false);
			if (qs.isCond(5))
			{
				giveItems(player, FALLEN_ANGEL_BLOOD, 1);
				qs.setCond(6, true);
				isAngelSpawned = false;
			}
		}
		else
		{
			final L2PcInstance member = getRandomPartyMember(player, 4);
			if (member != null)
			{
				qs = getQuestState(member, false);
				if (getRandom(1000) < MOBS.get(npc.getId()))
				{
					giveItems(player, PROPHECY_FRAGMENT, 1);
					if (getQuestItemsCount(player, PROPHECY_FRAGMENT) >= FRAGMENT_COUNT)
					{
						takeItems(player, PROPHECY_FRAGMENT, -1);
						qs.setCond(5, true);
					}
					else
					{
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
				}
			}
		}
		return super.onKill(npc, player, isSummon);
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
			case RAYMOND:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30289-01.html";
							break;
						}
						case 2:
						{
							if (qs.isSet("talk"))
							{
								htmltext = "30289-03.html";
							}
							else
							{
								takeItems(player, CRYPTOGRAM_OF_THE_ANGEL_SEARCH, -1);
								qs.set("talk", "1");
								htmltext = "30289-02.html";
							}
							break;
						}
						case 3:
						case 4:
						case 5:
						{
							htmltext = "30289-06.html";
							break;
						}
						case 6:
						{
							giveAdena(player, 92676, true);
							if (player.getLevel() <= MAX_REWARD_LEVEL)
							{
								addExpAndSp(player, 223036, 13091);
							}
							qs.exitQuest(false, true);
							htmltext = "30289-07.html";
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
							if (qs.getInt("talk") == 1)
							{
								htmltext = "30612-03.html";
							}
							else if (qs.getInt("talk") == 2)
							{
								htmltext = "30612-06.html";
							}
							else
							{
								htmltext = "30612-02.html";
								qs.set("talk", "1");
							}
							break;
						}
						case 4:
						case 5:
						case 6:
						{
							htmltext = "30612-09.html";
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
						case 5:
						{
							htmltext = "32368-02.html";
							break;
						}
						case 6:
						{
							htmltext = "32368-05.html";
							break;
						}
						default:
						{
							htmltext = "32368-01.html";
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
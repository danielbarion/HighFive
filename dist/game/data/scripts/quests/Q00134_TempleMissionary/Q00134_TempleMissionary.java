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
package quests.Q00134_TempleMissionary;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Temple Missionary (134)
 * @author malyelfik
 */
public class Q00134_TempleMissionary extends Quest
{
	// NPCs
	private static final int GLYVKA = 30067;
	private static final int ROUKE = 31418;
	// Items
	private static final int GIANTS_EXPERIMENTAL_TOOL_FRAGMENT = 10335;
	private static final int GIANTS_EXPERIMENTAL_TOOL = 10336;
	private static final int GIANTS_TECHNOLOGY_REPORT = 10337;
	private static final int ROUKES_REPOT = 10338;
	private static final int BADGE_TEMPLE_MISSIONARY = 10339;
	// Monsters
	private static final int CRUMA_MARSHLANDS_TRAITOR = 27339;
	private static final Map<Integer, Integer> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20157, 78); // Marsh Stakato
		MOBS.put(20229, 75); // Stinger Wasp
		MOBS.put(20230, 86); // Marsh Stakato Worker
		MOBS.put(20231, 83); // Toad Lord
		MOBS.put(20232, 81); // Marsh Stakato Soldier
		MOBS.put(20233, 95); // Marsh Spider
		MOBS.put(20234, 96); // Marsh Stakato Drone
	}
	
	// Misc
	private static final int MIN_LEVEL = 35;
	private static final int MAX_REWARD_LEVEL = 41;
	private static final int FRAGMENT_COUNT = 10;
	private static final int REPORT_COUNT = 3;
	
	public Q00134_TempleMissionary()
	{
		super(134);
		addStartNpc(GLYVKA);
		addTalkId(GLYVKA, ROUKE);
		addKillId(CRUMA_MARSHLANDS_TRAITOR);
		addKillId(MOBS.keySet());
		registerQuestItems(GIANTS_EXPERIMENTAL_TOOL_FRAGMENT, GIANTS_EXPERIMENTAL_TOOL, GIANTS_TECHNOLOGY_REPORT, ROUKES_REPOT);
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
			case "30067-05.html":
			case "30067-09.html":
			case "31418-07.html":
			{
				break;
			}
			case "30067-03.htm":
			{
				qs.startQuest();
				break;
			}
			case "30067-06.html":
			{
				qs.setCond(2, true);
				break;
			}
			case "31418-03.html":
			{
				qs.setCond(3, true);
				break;
			}
			case "31418-08.html":
			{
				qs.setCond(5, true);
				giveItems(player, ROUKES_REPOT, 1);
				qs.unset("talk");
				break;
			}
			case "30067-10.html":
			{
				giveItems(player, BADGE_TEMPLE_MISSIONARY, 1);
				giveAdena(player, 15100, true);
				if (player.getLevel() < MAX_REWARD_LEVEL)
				{
					addExpAndSp(player, 30000, 2000);
				}
				qs.exitQuest(false, true);
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
		final L2PcInstance member = getRandomPartyMember(player, 3);
		if (member == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		final QuestState qs = getQuestState(member, false);
		if (npc.getId() == CRUMA_MARSHLANDS_TRAITOR)
		{
			giveItems(player, GIANTS_TECHNOLOGY_REPORT, 1);
			if (getQuestItemsCount(player, GIANTS_TECHNOLOGY_REPORT) >= REPORT_COUNT)
			{
				qs.setCond(4, true);
			}
			else
			{
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		else if (hasQuestItems(player, GIANTS_EXPERIMENTAL_TOOL))
		{
			takeItems(player, GIANTS_EXPERIMENTAL_TOOL, 1);
			if (getRandom(100) != 0)
			{
				addSpawn(CRUMA_MARSHLANDS_TRAITOR, npc.getX() + 20, npc.getY() + 20, npc.getZ(), npc.getHeading(), false, 60000);
			}
		}
		else if (getRandom(100) < MOBS.get(npc.getId()))
		{
			giveItems(player, GIANTS_EXPERIMENTAL_TOOL_FRAGMENT, 1);
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
			case GLYVKA:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? "30067-01.htm" : "30067-02.htm";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "30067-04.html";
								break;
							}
							case 2:
							case 3:
							case 4:
							{
								htmltext = "30067-07.html";
								break;
							}
							case 5:
							{
								if (qs.isSet("talk"))
								{
									htmltext = "30067-09.html";
								}
								else
								{
									takeItems(player, ROUKES_REPOT, -1);
									qs.set("talk", "1");
									htmltext = "30067-08.html";
								}
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
			case ROUKE:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "31418-01.html";
							break;
						}
						case 2:
						{
							htmltext = "31418-02.html";
							break;
						}
						case 3:
						{
							if ((getQuestItemsCount(player, GIANTS_EXPERIMENTAL_TOOL_FRAGMENT) < FRAGMENT_COUNT) && (getQuestItemsCount(player, GIANTS_TECHNOLOGY_REPORT) < REPORT_COUNT))
							{
								htmltext = "31418-04.html";
							}
							else if (getQuestItemsCount(player, GIANTS_EXPERIMENTAL_TOOL_FRAGMENT) >= FRAGMENT_COUNT)
							{
								final int count = (int) (getQuestItemsCount(player, GIANTS_EXPERIMENTAL_TOOL_FRAGMENT) / 10);
								takeItems(player, GIANTS_EXPERIMENTAL_TOOL_FRAGMENT, count * 10);
								giveItems(player, GIANTS_EXPERIMENTAL_TOOL, count);
								htmltext = "31418-05.html";
							}
							break;
						}
						case 4:
						{
							if (qs.isSet("talk"))
							{
								htmltext = "31418-07.html";
							}
							else if (getQuestItemsCount(player, GIANTS_TECHNOLOGY_REPORT) >= REPORT_COUNT)
							{
								takeItems(player, GIANTS_EXPERIMENTAL_TOOL_FRAGMENT, -1);
								takeItems(player, GIANTS_EXPERIMENTAL_TOOL, -1);
								takeItems(player, GIANTS_TECHNOLOGY_REPORT, -1);
								qs.set("talk", "1");
								htmltext = "31418-06.html";
							}
							break;
						}
						case 5:
						{
							htmltext = "31418-09.html";
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
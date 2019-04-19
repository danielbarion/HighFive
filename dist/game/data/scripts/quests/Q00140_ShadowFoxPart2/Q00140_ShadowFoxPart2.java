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
package quests.Q00140_ShadowFoxPart2;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00139_ShadowFoxPart1.Q00139_ShadowFoxPart1;

/**
 * Shadow Fox - 2 (140)
 * @author Nono
 */
public class Q00140_ShadowFoxPart2 extends Quest
{
	// NPCs
	private static final int KLUCK = 30895;
	private static final int XENOVIA = 30912;
	// Items
	private static final int DARK_CRYSTAL = 10347;
	private static final int DARK_OXYDE = 10348;
	private static final int CRYPTOGRAM_OF_THE_GODDESS_SWORD = 10349;
	// Monsters
	private static final Map<Integer, Integer> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20789, 45); // Crokian
		MOBS.put(20790, 58); // Dailaon
		MOBS.put(20791, 100); // Crokian Warrior
		MOBS.put(20792, 92); // Farhite
	}
	
	// Misc
	private static final int MIN_LEVEL = 37;
	private static final int MAX_REWARD_LEVEL = 42;
	private static final int CHANCE = 8;
	private static final int CRYSTAL_COUNT = 5;
	private static final int OXYDE_COUNT = 2;
	
	public Q00140_ShadowFoxPart2()
	{
		super(140);
		addStartNpc(KLUCK);
		addTalkId(KLUCK, XENOVIA);
		addKillId(MOBS.keySet());
		registerQuestItems(DARK_CRYSTAL, DARK_OXYDE, CRYPTOGRAM_OF_THE_GODDESS_SWORD);
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
			case "30895-05.html":
			case "30895-06.html":
			case "30912-03.html":
			case "30912-04.html":
			case "30912-05.html":
			case "30912-08.html":
			case "30895-10.html":
			{
				break;
			}
			case "30895-03.htm":
			{
				qs.startQuest();
				break;
			}
			case "30895-07.html":
			{
				qs.setCond(2, true);
				break;
			}
			case "30912-06.html":
			{
				qs.set("talk", "1");
				break;
			}
			case "30912-09.html":
			{
				qs.unset("talk");
				qs.setCond(3, true);
				break;
			}
			case "30912-14.html":
			{
				if (getRandom(10) < CHANCE)
				{
					if (getQuestItemsCount(player, DARK_OXYDE) < OXYDE_COUNT)
					{
						giveItems(player, DARK_OXYDE, 1);
						takeItems(player, DARK_CRYSTAL, 5);
						return "30912-12.html";
					}
					giveItems(player, CRYPTOGRAM_OF_THE_GODDESS_SWORD, 1);
					takeItems(player, DARK_CRYSTAL, -1);
					takeItems(player, DARK_OXYDE, -1);
					qs.setCond(4, true);
					return "30912-13.html";
				}
				takeItems(player, DARK_CRYSTAL, 5);
				break;
			}
			case "30895-11.html":
			{
				giveAdena(player, 18775, true);
				if (player.getLevel() <= MAX_REWARD_LEVEL)
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
		if (getRandom(100) < MOBS.get(npc.getId()))
		{
			giveItems(member, DARK_CRYSTAL, 1);
			playSound(member, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
			case KLUCK:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						final QuestState qst = player.getQuestState(Q00139_ShadowFoxPart1.class.getSimpleName());
						htmltext = (player.getLevel() >= MIN_LEVEL) ? ((qst != null) && qst.isCompleted()) ? "30895-01.htm" : "30895-00.htm" : "30895-02.htm";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "30895-04.html";
								break;
							}
							case 2:
							case 3:
							{
								htmltext = "30895-08.html";
								break;
							}
							case 4:
							{
								if (qs.isSet("talk"))
								{
									htmltext = "30895-10.html";
								}
								else
								{
									takeItems(player, CRYPTOGRAM_OF_THE_GODDESS_SWORD, -1);
									qs.set("talk", "1");
									htmltext = "30895-09.html";
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
			case XENOVIA:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30912-01.html";
							break;
						}
						case 2:
						{
							htmltext = qs.isSet("talk") ? "30912-07.html" : "30912-02.html";
							break;
						}
						case 3:
						{
							htmltext = (getQuestItemsCount(player, DARK_CRYSTAL) >= CRYSTAL_COUNT) ? "30912-11.html" : "30912-10.html";
							break;
						}
						case 4:
						{
							htmltext = "30912-15.html";
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
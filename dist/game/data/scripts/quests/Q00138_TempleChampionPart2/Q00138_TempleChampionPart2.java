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
package quests.Q00138_TempleChampionPart2;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q00137_TempleChampionPart1.Q00137_TempleChampionPart1;

/**
 * Temple Champion - 2 (138)
 * @author nonom
 */
public class Q00138_TempleChampionPart2 extends Quest
{
	// NPCs
	private static final int SYLVAIN = 30070;
	private static final int PUPINA = 30118;
	private static final int ANGUS = 30474;
	private static final int SLA = 30666;
	private static final int MOBS[] =
	{
		20176, // Wyrm
		20550, // Guardian Basilisk
		20551, // Road Scavenger
		20552, // Fettered Soul
	};
	// Items
	private static final int TEMPLE_MANIFESTO = 10341;
	private static final int RELICS_OF_THE_DARK_ELF_TRAINEE = 10342;
	private static final int ANGUS_RECOMMENDATION = 10343;
	private static final int PUPINAS_RECOMMENDATION = 10344;
	
	public Q00138_TempleChampionPart2()
	{
		super(138);
		addStartNpc(SYLVAIN);
		addTalkId(SYLVAIN, PUPINA, ANGUS, SLA);
		addKillId(MOBS);
		registerQuestItems(TEMPLE_MANIFESTO, RELICS_OF_THE_DARK_ELF_TRAINEE, ANGUS_RECOMMENDATION, PUPINAS_RECOMMENDATION);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		switch (event)
		{
			case "30070-02.htm":
			{
				qs.startQuest();
				giveItems(player, TEMPLE_MANIFESTO, 1);
				break;
			}
			case "30070-05.html":
			{
				giveAdena(player, 84593, true);
				if (player.getLevel() < 42)
				{
					addExpAndSp(player, 187062, 11307);
				}
				qs.exitQuest(false, true);
				break;
			}
			case "30070-03.html":
			{
				qs.setCond(2, true);
				break;
			}
			case "30118-06.html":
			{
				qs.setCond(3, true);
				break;
			}
			case "30118-09.html":
			{
				qs.setCond(6, true);
				giveItems(player, PUPINAS_RECOMMENDATION, 1);
				break;
			}
			case "30474-02.html":
			{
				qs.setCond(4, true);
				break;
			}
			case "30666-02.html":
			{
				if (hasQuestItems(player, PUPINAS_RECOMMENDATION))
				{
					qs.set("talk", "1");
					takeItems(player, PUPINAS_RECOMMENDATION, -1);
				}
				break;
			}
			case "30666-03.html":
			{
				if (hasQuestItems(player, TEMPLE_MANIFESTO))
				{
					qs.set("talk", "2");
					takeItems(player, TEMPLE_MANIFESTO, -1);
				}
				break;
			}
			case "30666-08.html":
			{
				qs.setCond(7, true);
				qs.unset("talk");
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isStarted() && qs.isCond(4) && (getQuestItemsCount(player, RELICS_OF_THE_DARK_ELF_TRAINEE) < 10))
		{
			giveItems(player, RELICS_OF_THE_DARK_ELF_TRAINEE, 1);
			if (getQuestItemsCount(player, RELICS_OF_THE_DARK_ELF_TRAINEE) >= 10)
			{
				playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
			}
			else
			{
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
			case SYLVAIN:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "30070-02.htm";
						break;
					}
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					{
						htmltext = "30070-03.html";
						break;
					}
					case 7:
					{
						htmltext = "30070-04.html";
						break;
					}
					default:
					{
						if (qs.isCompleted())
						{
							return getAlreadyCompletedMsg(player);
						}
						final QuestState qst = player.getQuestState(Q00137_TempleChampionPart1.class.getSimpleName());
						htmltext = (player.getLevel() >= 36) ? ((qst != null) && qst.isCompleted()) ? "30070-01.htm" : "30070-00a.htm" : "30070-00.htm";
						break;
					}
				}
				break;
			}
			case PUPINA:
			{
				switch (qs.getCond())
				{
					case 2:
					{
						htmltext = "30118-01.html";
						break;
					}
					case 3:
					case 4:
					{
						htmltext = "30118-07.html";
						break;
					}
					case 5:
					{
						htmltext = "30118-08.html";
						if (hasQuestItems(player, ANGUS_RECOMMENDATION))
						{
							takeItems(player, ANGUS_RECOMMENDATION, -1);
						}
						break;
					}
					case 6:
					{
						htmltext = "30118-10.html";
						break;
					}
				}
				break;
			}
			case ANGUS:
			{
				switch (qs.getCond())
				{
					case 3:
					{
						htmltext = "30474-01.html";
						break;
					}
					case 4:
					{
						if (getQuestItemsCount(player, RELICS_OF_THE_DARK_ELF_TRAINEE) >= 10)
						{
							takeItems(player, RELICS_OF_THE_DARK_ELF_TRAINEE, -1);
							giveItems(player, ANGUS_RECOMMENDATION, 1);
							qs.setCond(5, true);
							htmltext = "30474-04.html";
						}
						else
						{
							htmltext = "30474-03.html";
						}
						break;
					}
					case 5:
					{
						htmltext = "30474-05.html";
						break;
					}
				}
				break;
			}
			case SLA:
			{
				switch (qs.getCond())
				{
					case 6:
					{
						switch (qs.getInt("talk"))
						{
							case 1:
							{
								htmltext = "30666-02.html";
								break;
							}
							case 2:
							{
								htmltext = "30666-03.html";
								break;
							}
							default:
							{
								htmltext = "30666-01.html";
								break;
							}
						}
						break;
					}
					case 7:
					{
						htmltext = "30666-09.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

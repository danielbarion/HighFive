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
package quests.Q00038_DragonFangs;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Dragon Fangs (38)
 * @author Janiko
 */
public final class Q00038_DragonFangs extends Quest
{
	// NPCs
	private static final int IRIS = 30034;
	private static final int MAGISTER_ROHMER = 30344;
	private static final int GUARD_LUIS = 30386;
	// Monsters
	private static final int LIZARDMAN_SENTINEL = 21100;
	private static final int LIZARDMAN_SHAMAN = 21101;
	private static final int LIZARDMAN_LEADER = 20356;
	private static final int LIZARDMAN_SUB_LEADER = 20357;
	// Items
	private static final ItemHolder FEATHER = new ItemHolder(7173, 100);
	private static final int TOTEM_TOOTH_1ST = 7174;
	private static final ItemHolder TOTEM_TOOTH_2ND = new ItemHolder(7175, 50);
	private static final int LETTER_1ST = 7176;
	private static final int LETTER_2ND = 7177;
	// Rewards
	private static final int BONE_HELMET = 45;
	private static final int LEATHER_GAUNTLET = 605;
	private static final int ASPIS = 627;
	private static final int BLUE_BUCKSKIN_BOOTS = 1123;
	// Misc
	private static final int MIN_LVL = 19;
	
	public Q00038_DragonFangs()
	{
		super(38);
		addStartNpc(GUARD_LUIS);
		addTalkId(GUARD_LUIS, IRIS, MAGISTER_ROHMER);
		addKillId(LIZARDMAN_SENTINEL, LIZARDMAN_SHAMAN, LIZARDMAN_LEADER, LIZARDMAN_SUB_LEADER);
		registerQuestItems(FEATHER.getId(), TOTEM_TOOTH_1ST, TOTEM_TOOTH_2ND.getId(), LETTER_1ST, LETTER_2ND);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		switch (event)
		{
			case "30386-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30386-06.html":
			{
				if (qs.isCond(2))
				{
					if (hasItem(player, FEATHER))
					{
						qs.setCond(3, true);
						takeItem(player, FEATHER);
						giveItems(player, TOTEM_TOOTH_1ST, 1);
						htmltext = event;
					}
					else
					{
						htmltext = "30386-07.html";
					}
				}
				
				break;
			}
			case "30034-02.html":
			{
				if (qs.isCond(3))
				{
					if (hasQuestItems(player, TOTEM_TOOTH_1ST))
					{
						qs.setCond(4, true);
						takeItems(player, TOTEM_TOOTH_1ST, 1);
						giveItems(player, LETTER_1ST, 1);
						htmltext = event;
					}
					else
					{
						htmltext = "30034-03.html";
					}
				}
				break;
			}
			case "30034-06.html":
			{
				if (qs.isCond(5))
				{
					if (hasQuestItems(player, LETTER_2ND))
					{
						qs.setCond(6, true);
						takeItems(player, LETTER_2ND, 1);
						htmltext = event;
					}
					else
					{
						htmltext = "30034-07.html";
					}
				}
				break;
			}
			case "30034-10.html":
			{
				if (qs.isCond(7))
				{
					if (hasItem(player, TOTEM_TOOTH_2ND))
					{
						addExpAndSp(player, 435117, 23977);
						final int chance = getRandom(1000);
						if (chance < 250)
						{
							rewardItems(player, BONE_HELMET, 1);
							giveAdena(player, 5200, true);
						}
						else if (chance < 500)
						{
							rewardItems(player, ASPIS, 1);
							giveAdena(player, 1500, true);
						}
						else if (chance < 750)
						{
							rewardItems(player, BLUE_BUCKSKIN_BOOTS, 1);
							giveAdena(player, 3200, true);
						}
						else if (chance < 1000)
						{
							rewardItems(player, LEATHER_GAUNTLET, 1);
							giveAdena(player, 3200, true);
						}
						qs.exitQuest(false, true);
						htmltext = event;
					}
					else
					{
						htmltext = "30034-11.html";
					}
				}
				break;
			}
			case "30344-02.html":
			{
				if (qs.isCond(4))
				{
					if (hasQuestItems(player, LETTER_1ST))
					{
						qs.setCond(5, true);
						takeItems(player, LETTER_1ST, 1);
						giveItems(player, LETTER_2ND, 1);
						htmltext = event;
					}
					else
					{
						htmltext = "30344-03.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		switch (npc.getId())
		{
			case IRIS:
			{
				switch (qs.getCond())
				{
					case 3:
					{
						htmltext = "30034-01.html";
						break;
					}
					case 4:
					{
						htmltext = "30034-04.html";
						break;
					}
					case 5:
					{
						htmltext = "30034-05.html";
						break;
					}
					case 6:
					{
						htmltext = "30034-09.html";
						break;
					}
					case 7:
					{
						if (hasItem(talker, TOTEM_TOOTH_2ND))
						{
							htmltext = "30034-08.html";
						}
						break;
					}
				}
				break;
			}
			case MAGISTER_ROHMER:
			{
				if (qs.isCond(4))
				{
					htmltext = "30344-01.html";
				}
				else if (qs.isCond(5))
				{
					htmltext = "30344-04.html";
				}
				break;
			}
			case GUARD_LUIS:
			{
				if (qs.isCreated())
				{
					htmltext = (talker.getLevel() >= MIN_LVL) ? "30386-01.htm" : "30386-02.htm";
				}
				else if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30386-05.html";
							break;
						}
						case 2:
						{
							if (hasItem(talker, FEATHER))
							{
								htmltext = "30386-04.html";
							}
							break;
						}
						case 3:
						{
							htmltext = "30386-08.html";
							break;
						}
					}
				}
				else if (qs.isCompleted())
				{
					htmltext = getAlreadyCompletedMsg(talker);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		switch (npc.getId())
		{
			case LIZARDMAN_SUB_LEADER:
			case LIZARDMAN_SENTINEL:
			{
				final QuestState qs = getRandomPartyMemberState(killer, 1, 3, npc);
				if ((qs != null) && giveItemRandomly(qs.getPlayer(), npc, FEATHER.getId(), 1, FEATHER.getCount(), 1.0, true))
				{
					qs.setCond(2);
				}
				break;
			}
			case LIZARDMAN_LEADER:
			case LIZARDMAN_SHAMAN:
			{
				final QuestState qs = getRandomPartyMemberState(killer, 6, 3, npc);
				if ((qs != null) && giveItemRandomly(qs.getPlayer(), npc, TOTEM_TOOTH_2ND.getId(), 1, TOTEM_TOOTH_2ND.getCount(), 0.5, true))
				{
					qs.setCond(7);
				}
				break;
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
}
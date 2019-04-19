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
package quests.Q00340_SubjugationOfLizardmen;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Subjugation Of Lizardmen (340)
 * @author ivantotov
 */
public final class Q00340_SubjugationOfLizardmen extends Quest
{
	// NPCs
	private static final int HIGH_PRIESTESS_LEVIAN = 30037;
	private static final int PRIEST_ADONIUS = 30375;
	private static final int GUARD_WEISZ = 30385;
	private static final int CHEST_OF_BIFRONS = 30989;
	// Items
	private static final int TRADE_CARGO = 4255;
	private static final int AGNESS_HOLY_SYMBOL = 4256;
	private static final int AGNESS_ROSARY = 4257;
	private static final int SINISTER_TOTEM = 4258;
	// Monster
	private static final int FELIM_LIZARDMAN = 20008;
	private static final int FELIM_LIZARDMAN_SCOUT = 20010;
	private static final int FELIM_LIZARDMAN_WARRIOR = 20014;
	private static final int LANGK_LIZARDMAN_WARRIOR = 20024;
	private static final int LANGK_LIZARDMAN_SCOUT = 20027;
	private static final int LANGK_LIZARDMAN = 20030;
	// Raid Boss
	private static final int SERPENT_DEMON_BIFRONS = 25146;
	// Misc
	private static final int MIN_LEVEL = 17;
	
	public Q00340_SubjugationOfLizardmen()
	{
		super(340);
		addStartNpc(GUARD_WEISZ);
		addTalkId(GUARD_WEISZ, HIGH_PRIESTESS_LEVIAN, PRIEST_ADONIUS, CHEST_OF_BIFRONS);
		addKillId(FELIM_LIZARDMAN, FELIM_LIZARDMAN_SCOUT, FELIM_LIZARDMAN_WARRIOR, LANGK_LIZARDMAN_WARRIOR, LANGK_LIZARDMAN_SCOUT, LANGK_LIZARDMAN, SERPENT_DEMON_BIFRONS);
		registerQuestItems(TRADE_CARGO, AGNESS_HOLY_SYMBOL, AGNESS_ROSARY, SINISTER_TOTEM);
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
			case "30385-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = event;
				}
				break;
			}
			case "30385-04.html":
			case "30385-08.html":
			{
				htmltext = event;
				break;
			}
			case "30385-07.html":
			{
				takeItems(player, TRADE_CARGO, -1);
				qs.setMemoState(2);
				qs.setCond(2, true);
				htmltext = event;
				break;
			}
			case "30385-09.html":
			{
				if (getQuestItemsCount(player, TRADE_CARGO) >= 30)
				{
					giveAdena(player, 4090, true);
					takeItems(player, TRADE_CARGO, -1);
					qs.setMemoState(1);
					htmltext = event;
				}
				break;
			}
			case "30385-10.html":
			{
				if (getQuestItemsCount(player, TRADE_CARGO) >= 30)
				{
					giveAdena(player, 4090, true);
					takeItems(player, TRADE_CARGO, -1);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "30037-02.html":
			{
				qs.setMemoState(5);
				qs.setCond(5, true);
				htmltext = event;
				break;
			}
			case "30375-02.html":
			{
				qs.setMemoState(3);
				qs.setCond(3, true);
				htmltext = event;
				break;
			}
			case "30989-02.html":
			{
				if (qs.isMemoState(5))
				{
					qs.setMemoState(6);
					qs.setCond(6, true);
					giveItems(player, SINISTER_TOTEM, 1);
					htmltext = event;
				}
				else
				{
					htmltext = "30989-03.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case FELIM_LIZARDMAN:
				case FELIM_LIZARDMAN_SCOUT:
				{
					if (qs.isMemoState(1))
					{
						giveItemRandomly(killer, npc, TRADE_CARGO, 1, 30, 0.63, true);
					}
					break;
				}
				case FELIM_LIZARDMAN_WARRIOR:
				{
					if (qs.isMemoState(1))
					{
						giveItemRandomly(killer, npc, TRADE_CARGO, 1, 30, 0.68, true);
					}
					break;
				}
				case LANGK_LIZARDMAN_WARRIOR:
				{
					if (qs.isMemoState(3))
					{
						if (!hasQuestItems(killer, AGNESS_HOLY_SYMBOL) && (getRandom(100) <= 19))
						{
							giveItems(killer, AGNESS_HOLY_SYMBOL, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						else if (hasQuestItems(killer, AGNESS_HOLY_SYMBOL) && !hasQuestItems(killer, AGNESS_ROSARY) && (getRandom(100) <= 18))
						{
							giveItems(killer, AGNESS_ROSARY, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case LANGK_LIZARDMAN_SCOUT:
				case LANGK_LIZARDMAN:
				{
					if (qs.isMemoState(3))
					{
						if (!hasQuestItems(killer, AGNESS_HOLY_SYMBOL) && (getRandom(100) <= 18))
						{
							giveItems(killer, AGNESS_HOLY_SYMBOL, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						else if (hasQuestItems(killer, AGNESS_HOLY_SYMBOL) && !hasQuestItems(killer, AGNESS_ROSARY) && (getRandom(100) <= 18))
						{
							giveItems(killer, AGNESS_ROSARY, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case SERPENT_DEMON_BIFRONS:
				{
					addSpawn(CHEST_OF_BIFRONS, npc, true, 30000);
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		final int memoState = qs.getMemoState();
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == GUARD_WEISZ)
			{
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "30385-02.htm" : "30385-01.htm";
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case GUARD_WEISZ:
				{
					if (memoState == 1)
					{
						if (getQuestItemsCount(player, TRADE_CARGO) < 30)
						{
							htmltext = "30385-05.html";
						}
						else
						{
							htmltext = "30385-06.html";
						}
					}
					else if (memoState == 2)
					{
						htmltext = "30385-11.html";
					}
					else if ((memoState >= 3) && (memoState < 7))
					{
						htmltext = "30385-12.html";
					}
					else if (memoState == 7)
					{
						giveAdena(player, 14700, true);
						qs.exitQuest(false, true);
						htmltext = "30385-13.html";
					}
					break;
				}
				case HIGH_PRIESTESS_LEVIAN:
				{
					if (memoState == 4)
					{
						htmltext = "30037-01.html";
					}
					else if (memoState == 5)
					{
						htmltext = "30037-03.html";
					}
					else if (memoState == 6)
					{
						takeItems(player, SINISTER_TOTEM, 1);
						qs.setMemoState(7);
						qs.setCond(7, true);
						htmltext = "30037-04.html";
					}
					else if (memoState == 7)
					{
						htmltext = "30037-05.html";
					}
					break;
				}
				case PRIEST_ADONIUS:
				{
					if (memoState == 2)
					{
						htmltext = "30375-01.html";
					}
					else if (memoState == 3)
					{
						if (hasQuestItems(player, AGNESS_HOLY_SYMBOL, AGNESS_ROSARY))
						{
							takeItems(player, AGNESS_HOLY_SYMBOL, 1);
							takeItems(player, AGNESS_ROSARY, 1);
							qs.setMemoState(4);
							qs.setCond(4, true);
							htmltext = "30375-04.html";
						}
						else
						{
							htmltext = "30375-03.html";
						}
					}
					else if (memoState == 4)
					{
						htmltext = "30375-05.html";
					}
					else if (memoState >= 5)
					{
						htmltext = "30375-06.html";
					}
					break;
				}
				case CHEST_OF_BIFRONS:
				{
					if (memoState == 5)
					{
						htmltext = "30989-01.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == GUARD_WEISZ)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
		}
		return htmltext;
	}
}
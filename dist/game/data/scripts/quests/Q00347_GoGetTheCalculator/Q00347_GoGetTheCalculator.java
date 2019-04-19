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
package quests.Q00347_GoGetTheCalculator;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Go Get the Calculator (347)
 * @author Pandragon
 */
public final class Q00347_GoGetTheCalculator extends Quest
{
	// NPCs
	private static final int BRUNON = 30526;
	private static final int SILVERA = 30527;
	private static final int SPIRON = 30532;
	private static final int BALANKI = 30533;
	// Items
	private static final int STOLEN_CALCULATOR = 4285;
	private static final int GEMSTONE = 4286;
	// Monster
	private static final int GEMSTONE_BEAST = 20540;
	// Reward
	private static final int CALCULATOR = 4393;
	private static final int ADENA = 1500;
	// Misc
	private static final int MIN_LVL = 12;
	
	public Q00347_GoGetTheCalculator()
	{
		super(347);
		addStartNpc(BRUNON);
		addTalkId(BRUNON, SILVERA, SPIRON, BALANKI);
		addKillId(GEMSTONE_BEAST);
		registerQuestItems(STOLEN_CALCULATOR, GEMSTONE);
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
			case "30526-03.htm":
			case "30526-04.htm":
			case "30526-05.htm":
			case "30526-06.htm":
			case "30526-07.htm":
			case "30532-03.html":
			case "30532-04.html":
			{
				htmltext = event;
				break;
			}
			case "30526-08.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30526-10.html":
			{
				if (qs.isCond(6))
				{
					takeItems(player, STOLEN_CALCULATOR, -1);
					rewardItems(player, CALCULATOR, 1);
					qs.exitQuest(true, true);
					htmltext = event;
				}
				else
				{
					htmltext = "30526-09.html";
				}
				break;
			}
			case "30526-11.html":
			{
				if (qs.isCond(6))
				{
					takeItems(player, STOLEN_CALCULATOR, -1);
					giveAdena(player, ADENA, true);
					qs.exitQuest(true, true);
					htmltext = event;
				}
				break;
			}
			case "30532-02.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "30533-02.html":
			{
				if ((qs.isCond(2)) && (player.getAdena() > 100))
				{
					takeItems(player, Inventory.ADENA_ID, 100);
					qs.setCond(3, true);
					htmltext = event;
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
		switch (qs.getState())
		{
			case State.CREATED:
			{
				if (npc.getId() == BRUNON)
				{
					htmltext = (talker.getLevel() >= MIN_LVL) ? "30526-01.htm" : "30526-02.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case BRUNON:
					{
						if (hasQuestItems(talker, CALCULATOR))
						{
							qs.setCond(6);
						}
						switch (qs.getCond())
						{
							case 1:
							case 2:
							{
								htmltext = "30526-13.html";
								break;
							}
							case 3:
							case 4:
							{
								htmltext = "30526-14.html";
								break;
							}
							case 5:
							{
								htmltext = "30526-15.html";
								break;
							}
							case 6:
							{
								htmltext = "30526-09.html";
								break;
							}
						}
						break;
					}
					case SPIRON:
					{
						htmltext = qs.isCond(1) ? "30532-01.html" : "30532-05.html";
						break;
					}
					case BALANKI:
					{
						if (qs.isCond(2))
						{
							htmltext = "30533-01.html";
						}
						else if (qs.getCond() > 2)
						{
							htmltext = "30533-04.html";
						}
						else
						{
							htmltext = "30533-03.html";
						}
						break;
					}
					case SILVERA:
					{
						switch (qs.getCond())
						{
							case 1:
							case 2:
							{
								htmltext = "30527-01.html";
								break;
							}
							case 3:
							{
								qs.setCond(4, true);
								htmltext = "30527-02.html";
								break;
							}
							case 4:
							{
								htmltext = "30527-04.html";
								break;
							}
							case 5:
							{
								takeItems(talker, GEMSTONE, -1);
								giveItems(talker, STOLEN_CALCULATOR, 1);
								qs.setCond(6, true);
								htmltext = "30527-03.html";
								break;
							}
							case 6:
							{
								htmltext = "30527-05.html";
								break;
							}
						}
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(talker);
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, 4, 3, npc);
		if (qs != null)
		{
			if (giveItemRandomly(qs.getPlayer(), npc, GEMSTONE, 1, 10, 0.4, true))
			{
				qs.setCond(5);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
}

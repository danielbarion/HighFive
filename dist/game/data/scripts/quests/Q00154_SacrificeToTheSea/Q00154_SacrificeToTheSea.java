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
package quests.Q00154_SacrificeToTheSea;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Sacrifice to the Sea (154)
 * @author Pandragon
 */
public final class Q00154_SacrificeToTheSea extends Quest
{
	// NPCs
	private static final int ROCKSWELL = 30312;
	private static final int CRISTEL = 30051;
	private static final int ROLLFNAN = 30055;
	// Items
	private static final int FOX_FUR = 1032;
	private static final int FOX_FUR_YAM = 1033;
	private static final int MAIDEN_DOLL = 1034;
	// Monsters
	private static final int ELDER_KELTIR = 20544;
	private static final int YOUNG_KELTIR = 20545;
	private static final int KELTIR = 20481;
	// Reward
	private static final int MAGE_EARING = 113;
	// Misc
	private static final int MIN_LVL = 2;
	
	public Q00154_SacrificeToTheSea()
	{
		super(154);
		addStartNpc(ROCKSWELL);
		addTalkId(ROCKSWELL, CRISTEL, ROLLFNAN);
		addKillId(ELDER_KELTIR, YOUNG_KELTIR, KELTIR);
		registerQuestItems(FOX_FUR, FOX_FUR_YAM, MAIDEN_DOLL);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && event.equals("30312-03.htm"))
		{
			qs.startQuest();
			return event;
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		
		switch (npc.getId())
		{
			case ROCKSWELL:
			{
				if (qs.isCreated())
				{
					htmltext = ((talker.getLevel() >= MIN_LVL) ? "30312-01.htm" : "30312-02.htm");
				}
				else if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30312-04.html";
							break;
						}
						case 2:
						{
							htmltext = "30312-07.html";
							break;
						}
						case 3:
						{
							htmltext = "30312-05.html";
							break;
						}
						case 4:
						{
							takeItems(talker, MAIDEN_DOLL, -1);
							rewardItems(talker, MAGE_EARING, 1);
							addExpAndSp(talker, 0, 1000);
							qs.exitQuest(false, true);
							htmltext = "30312-06.html";
							break;
						}
					}
				}
				else
				{
					htmltext = getAlreadyCompletedMsg(talker);
				}
				break;
			}
			case CRISTEL:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "30051-02.html";
						break;
					}
					case 2:
					{
						takeItems(talker, FOX_FUR, -1);
						giveItems(talker, FOX_FUR_YAM, 1);
						qs.setCond(3, true);
						htmltext = "30051-01.html";
						break;
					}
					case 3:
					{
						htmltext = "30051-03.html";
						break;
					}
					case 4:
					{
						htmltext = "30051-04.html";
						break;
					}
				}
				break;
			}
			case ROLLFNAN:
			{
				switch (qs.getCond())
				{
					case 1:
					case 2:
					{
						htmltext = "30055-03.html";
						break;
					}
					case 3:
					{
						takeItems(talker, FOX_FUR_YAM, -1);
						giveItems(talker, MAIDEN_DOLL, 1);
						qs.setCond(4, true);
						htmltext = "30055-01.html";
						break;
					}
					case 4:
					{
						htmltext = "30055-02.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, 1, 3, npc);
		if ((qs != null) && giveItemRandomly(qs.getPlayer(), npc, FOX_FUR, 1, 10, 0.3, true))
		{
			qs.setCond(2);
		}
		return super.onKill(npc, killer, isSummon);
	}
}
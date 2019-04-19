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
package quests.Q00902_ReclaimOurEra;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.util.Util;

/**
 * Reclaim Our Era (902)
 * @author netvirus
 */
public final class Q00902_ReclaimOurEra extends Quest
{
	// Npc
	private static final int MATHIAS = 31340;
	// Misc
	private static final int MIN_LVL = 80;
	// Items
	private static final int SHATTERED_BONES = 21997;
	private static final int CANNIBALISTIC_STAKATO_LDR_CLAW = 21998;
	private static final int ANAIS_SCROLL = 21999;
	private static final int PROOF_OF_CHALLENGE = 21750;
	// Monsters
	private static final Map<Integer, Integer> MONSTER_DROPS = new HashMap<>();
	static
	{
		MONSTER_DROPS.put(25309, SHATTERED_BONES); // Varka's Hero Shadith
		MONSTER_DROPS.put(25312, SHATTERED_BONES); // Varka's Commander Mos
		MONSTER_DROPS.put(25315, SHATTERED_BONES); // Varka's Chief Horus
		MONSTER_DROPS.put(25299, SHATTERED_BONES); // Ketra's Hero Hekaton
		MONSTER_DROPS.put(25302, SHATTERED_BONES); // Ketra's Commander Tayr
		MONSTER_DROPS.put(25305, SHATTERED_BONES); // Ketra's Chief Brakki
		MONSTER_DROPS.put(25667, CANNIBALISTIC_STAKATO_LDR_CLAW); // Cannibalistic Stakato Chief
		MONSTER_DROPS.put(25668, CANNIBALISTIC_STAKATO_LDR_CLAW); // Cannibalistic Stakato Chief
		MONSTER_DROPS.put(25669, CANNIBALISTIC_STAKATO_LDR_CLAW); // Cannibalistic Stakato Chief
		MONSTER_DROPS.put(25670, CANNIBALISTIC_STAKATO_LDR_CLAW); // Cannibalistic Stakato Chief
		MONSTER_DROPS.put(25701, ANAIS_SCROLL); // Anais - Master of Splendor
	}
	
	public Q00902_ReclaimOurEra()
	{
		super(902);
		addStartNpc(MATHIAS);
		addTalkId(MATHIAS);
		addKillId(MONSTER_DROPS.keySet());
		registerQuestItems(SHATTERED_BONES, CANNIBALISTIC_STAKATO_LDR_CLAW, ANAIS_SCROLL);
	}
	
	private void giveItem(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isStarted() && !qs.isCond(5) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			giveItems(player, MONSTER_DROPS.get(npc.getId()), 1);
			qs.setCond(5, true);
		}
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
			case "31340-04.htm":
			{
				if (qs.isCreated())
				{
					htmltext = event;
				}
				break;
			}
			case "31340-05.html":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "31340-06.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "31340-07.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "31340-08.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "31340-10.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (killer.isInParty())
		{
			for (L2PcInstance member : killer.getParty().getMembers())
			{
				giveItem(npc, member);
			}
		}
		else
		{
			giveItem(npc, killer);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				if (!qs.isNowAvailable())
				{
					htmltext = "31340-02.htm";
					break;
				}
				qs.setState(State.CREATED);
			}
			case State.CREATED:
			{
				htmltext = (player.getLevel() >= MIN_LVL) ? "31340-01.htm" : "31340-03.htm";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "31340-09.html";
						break;
					}
					case 2:
					{
						htmltext = "31340-11.html";
						break;
					}
					case 3:
					{
						htmltext = "31340-12.html";
						break;
					}
					case 4:
					{
						htmltext = "31340-13.html";
						break;
					}
					case 5:
					{
						if (hasQuestItems(player, SHATTERED_BONES))
						{
							giveItems(player, PROOF_OF_CHALLENGE, 1);
							giveAdena(player, 134038, true);
						}
						else if (hasQuestItems(player, CANNIBALISTIC_STAKATO_LDR_CLAW))
						{
							giveItems(player, PROOF_OF_CHALLENGE, 3);
							giveAdena(player, 210119, true);
						}
						else if (hasQuestItems(player, ANAIS_SCROLL))
						{
							giveItems(player, PROOF_OF_CHALLENGE, 3);
							giveAdena(player, 348155, true);
						}
						qs.exitQuest(QuestType.DAILY, true);
						htmltext = "31340-14.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

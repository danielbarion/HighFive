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
package quests.Q00239_WontYouJoinUs;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00237_WindsOfChange.Q00237_WindsOfChange;
import quests.Q00238_SuccessFailureOfBusiness.Q00238_SuccessFailureOfBusiness;

/**
 * Won't You Join Us (239)<br>
 * Original Jython script by Bloodshed.
 * @author Joxit
 */
public class Q00239_WontYouJoinUs extends Quest
{
	// NPC
	private static final int ATHENIA = 32643;
	// Mobs
	private static final int WASTE_LANDFILL_MACHINE = 18805;
	private static final int SUPPRESSOR = 22656;
	private static final int EXTERMINATOR = 22657;
	// Items
	private static final int SUPPORT_CERTIFICATE = 14866;
	private static final int DESTROYED_MACHINE_PIECE = 14869;
	private static final int ENCHANTED_GOLEM_FRAGMENT = 14870;
	// Misc
	private static final int ENCHANTED_GOLEM_FRAGMENT_NEEDED = 20;
	private static final int DESTROYED_MACHINE_PIECE_NEEDED = 10;
	private static final int CHANCE_FOR_FRAGMENT = 80;
	private static final int MIN_LEVEL = 82;
	
	public Q00239_WontYouJoinUs()
	{
		super(239);
		addStartNpc(ATHENIA);
		addTalkId(ATHENIA);
		addKillId(WASTE_LANDFILL_MACHINE, SUPPRESSOR, EXTERMINATOR);
		registerQuestItems(DESTROYED_MACHINE_PIECE, ENCHANTED_GOLEM_FRAGMENT);
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
			case "32643-02.htm":
			{
				htmltext = event;
				break;
			}
			case "32643-03.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32643-07.html":
			{
				if (qs.isCond(2))
				{
					qs.setCond(3, true);
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
		if (npc.getId() == WASTE_LANDFILL_MACHINE)
		{
			final L2PcInstance partyMember = getRandomPartyMember(killer, 1);
			if (partyMember != null)
			{
				final QuestState qs = getQuestState(partyMember, false);
				if (getQuestItemsCount(partyMember, DESTROYED_MACHINE_PIECE) < DESTROYED_MACHINE_PIECE_NEEDED)
				{
					giveItems(partyMember, DESTROYED_MACHINE_PIECE, 1);
				}
				if (getQuestItemsCount(partyMember, DESTROYED_MACHINE_PIECE) == DESTROYED_MACHINE_PIECE_NEEDED)
				{
					qs.setCond(2, true);
				}
				else
				{
					playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
		else
		{
			final L2PcInstance partyMember = getRandomPartyMember(killer, 3);
			if ((partyMember != null) && (getRandom(100) < CHANCE_FOR_FRAGMENT))
			{
				final QuestState qs = getQuestState(partyMember, false);
				if (getQuestItemsCount(partyMember, ENCHANTED_GOLEM_FRAGMENT) < ENCHANTED_GOLEM_FRAGMENT_NEEDED)
				{
					giveItems(partyMember, ENCHANTED_GOLEM_FRAGMENT, 1);
				}
				if (getQuestItemsCount(partyMember, ENCHANTED_GOLEM_FRAGMENT) == ENCHANTED_GOLEM_FRAGMENT_NEEDED)
				{
					qs.setCond(4, true);
				}
				else
				{
					playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
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
				htmltext = "32643-11.html";
				break;
			}
			case State.CREATED:
			{
				final QuestState q237 = qs.getPlayer().getQuestState(Q00237_WindsOfChange.class.getSimpleName());
				final QuestState q238 = qs.getPlayer().getQuestState(Q00238_SuccessFailureOfBusiness.class.getSimpleName());
				if ((q238 != null) && q238.isCompleted())
				{
					htmltext = "32643-12.html";
				}
				else if ((q237 != null) && q237.isCompleted() && (player.getLevel() >= MIN_LEVEL) && hasQuestItems(player, SUPPORT_CERTIFICATE))
				{
					htmltext = "32643-01.htm";
				}
				else
				{
					htmltext = "32643-00.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = hasQuestItems(player, DESTROYED_MACHINE_PIECE) ? "32643-05.html" : "32643-04.html";
						break;
					}
					case 2:
					{
						if (getQuestItemsCount(player, DESTROYED_MACHINE_PIECE) == DESTROYED_MACHINE_PIECE_NEEDED)
						{
							htmltext = "32643-06.html";
							takeItems(player, DESTROYED_MACHINE_PIECE, -1);
						}
						break;
					}
					case 3:
					{
						htmltext = hasQuestItems(player, ENCHANTED_GOLEM_FRAGMENT) ? "32643-08.html" : "32643-09.html";
						break;
					}
					case 4:
					{
						if (getQuestItemsCount(player, ENCHANTED_GOLEM_FRAGMENT) == ENCHANTED_GOLEM_FRAGMENT_NEEDED)
						{
							htmltext = "32643-10.html";
							giveAdena(player, 283346, true);
							takeItems(player, SUPPORT_CERTIFICATE, 1);
							addExpAndSp(player, 1319736, 103553);
							qs.exitQuest(false, true);
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
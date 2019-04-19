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
package quests.Q00238_SuccessFailureOfBusiness;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00237_WindsOfChange.Q00237_WindsOfChange;
import quests.Q00239_WontYouJoinUs.Q00239_WontYouJoinUs;

/**
 * Success/Failure Of Business (238)<br>
 * Original Jython script by Bloodshed.
 * @author Joxit
 */
public class Q00238_SuccessFailureOfBusiness extends Quest
{
	// NPCs
	private static final int HELVETICA = 32641;
	// Mobs
	private static final int BRAZIER_OF_PURITY = 18806;
	private static final int EVIL_SPIRITS = 22658;
	private static final int GUARDIAN_SPIRITS = 22659;
	// Items
	private static final int VICINITY_OF_FOS = 14865;
	private static final int BROKEN_PIECE_OF_MAGIC_FORCE = 14867;
	private static final int GUARDIAN_SPIRIT_FRAGMENT = 14868;
	// Misc
	private static final int BROKEN_PIECE_OF_MAGIC_FORCE_NEEDED = 10;
	private static final int GUARDIAN_SPIRIT_FRAGMENT_NEEDED = 20;
	private static final int CHANCE_FOR_FRAGMENT = 80;
	private static final int MIN_LEVEL = 82;
	
	public Q00238_SuccessFailureOfBusiness()
	{
		super(238);
		addStartNpc(HELVETICA);
		addTalkId(HELVETICA);
		addKillId(BRAZIER_OF_PURITY, EVIL_SPIRITS, GUARDIAN_SPIRITS);
		registerQuestItems(BROKEN_PIECE_OF_MAGIC_FORCE, GUARDIAN_SPIRIT_FRAGMENT);
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
			case "32641-02.htm":
			{
				htmltext = event;
				break;
			}
			case "32641-03.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32641-06.html":
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
		if (npc.getId() == BRAZIER_OF_PURITY)
		{
			final L2PcInstance partyMember = getRandomPartyMember(killer, 1);
			if (partyMember != null)
			{
				final QuestState qs = getQuestState(partyMember, false);
				if (getQuestItemsCount(killer, BROKEN_PIECE_OF_MAGIC_FORCE) < BROKEN_PIECE_OF_MAGIC_FORCE_NEEDED)
				{
					giveItems(killer, BROKEN_PIECE_OF_MAGIC_FORCE, 1);
				}
				if (getQuestItemsCount(killer, BROKEN_PIECE_OF_MAGIC_FORCE) == BROKEN_PIECE_OF_MAGIC_FORCE_NEEDED)
				{
					qs.setCond(2, true);
				}
				else
				{
					playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
		else
		{
			final L2PcInstance partyMember = getRandomPartyMember(killer, 3);
			if ((partyMember != null) && (getRandom(100) < CHANCE_FOR_FRAGMENT))
			{
				final QuestState qs = getQuestState(partyMember, false);
				if (getQuestItemsCount(killer, GUARDIAN_SPIRIT_FRAGMENT) < GUARDIAN_SPIRIT_FRAGMENT_NEEDED)
				{
					giveItems(killer, GUARDIAN_SPIRIT_FRAGMENT, 1);
				}
				if (getQuestItemsCount(killer, GUARDIAN_SPIRIT_FRAGMENT) == GUARDIAN_SPIRIT_FRAGMENT_NEEDED)
				{
					qs.setCond(4, true);
				}
				else
				{
					playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
				htmltext = "32641-09.html";
				break;
			}
			case State.CREATED:
			{
				final QuestState q237 = qs.getPlayer().getQuestState(Q00237_WindsOfChange.class.getSimpleName());
				final QuestState q239 = qs.getPlayer().getQuestState(Q00239_WontYouJoinUs.class.getSimpleName());
				if ((q239 != null) && q239.isCompleted())
				{
					htmltext = "32641-10.html";
				}
				else if ((q237 != null) && q237.isCompleted() && (player.getLevel() >= MIN_LEVEL) && hasQuestItems(player, VICINITY_OF_FOS))
				{
					htmltext = "32641-01.htm";
				}
				else
				{
					htmltext = "32641-00.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "32641-04.html";
						break;
					}
					case 2:
					{
						if (getQuestItemsCount(player, BROKEN_PIECE_OF_MAGIC_FORCE) == BROKEN_PIECE_OF_MAGIC_FORCE_NEEDED)
						{
							htmltext = "32641-05.html";
							takeItems(player, BROKEN_PIECE_OF_MAGIC_FORCE, -1);
						}
						break;
					}
					case 3:
					{
						htmltext = "32641-07.html";
						break;
					}
					case 4:
					{
						if (getQuestItemsCount(player, GUARDIAN_SPIRIT_FRAGMENT) == GUARDIAN_SPIRIT_FRAGMENT_NEEDED)
						{
							htmltext = "32641-08.html";
							giveAdena(player, 283346, true);
							takeItems(player, VICINITY_OF_FOS, 1);
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
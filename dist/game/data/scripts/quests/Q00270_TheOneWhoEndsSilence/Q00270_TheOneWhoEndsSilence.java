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
package quests.Q00270_TheOneWhoEndsSilence;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.util.Util;

import quests.Q10288_SecretMission.Q10288_SecretMission;

/**
 * The One Who Ends Silence (270)
 * @author Zoey76
 */
public class Q00270_TheOneWhoEndsSilence extends Quest
{
	// NPC
	private static final int FAKE_GREYMORE = 32757;
	// Monsters
	private static final int SEEKER_SOLINA = 22790;
	private static final int SAVIOR_SOLINA = 22791;
	private static final int ASCETIC_SOLINA = 22793;
	private static final int DIVINITY_JUDGE = 22794;
	private static final int DIVINITY_MANAGER = 22795;
	private static final int DIVINITY_SUPERVISOR = 22796;
	private static final int DIVINITY_WORSHIPPER = 22797;
	private static final int DIVINITY_PROTECTOR = 22798;
	private static final int DIVINITY_FIGHTER = 22799;
	private static final int DIVINITY_MAGUS = 22800;
	// Items
	private static final int TATTERED_MONK_CLOTHES = 15526;
	// Misc
	private static final int MIN_LEVEL = 82;
	
	public Q00270_TheOneWhoEndsSilence()
	{
		super(270);
		addStartNpc(FAKE_GREYMORE);
		addTalkId(FAKE_GREYMORE);
		addKillId(SEEKER_SOLINA, SAVIOR_SOLINA, ASCETIC_SOLINA, DIVINITY_JUDGE, DIVINITY_MANAGER, DIVINITY_SUPERVISOR, DIVINITY_WORSHIPPER, DIVINITY_PROTECTOR, DIVINITY_FIGHTER, DIVINITY_MAGUS);
		registerQuestItems(TATTERED_MONK_CLOTHES);
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
		final long ragsCount = getQuestItemsCount(player, TATTERED_MONK_CLOTHES);
		switch (event)
		{
			case "32757-02.htm":
			{
				final QuestState qst = player.getQuestState(Q10288_SecretMission.class.getSimpleName());
				if ((player.getLevel() >= MIN_LEVEL) && (qst != null) && qst.isCompleted())
				{
					htmltext = event;
				}
				break;
			}
			case "32757-04.html":
			{
				final QuestState qst = player.getQuestState(Q10288_SecretMission.class.getSimpleName());
				if ((player.getLevel() >= MIN_LEVEL) && (qst != null) && qst.isCompleted())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "32757-08.html":
			{
				if (qs.isCond(1))
				{
					if (ragsCount == 0)
					{
						htmltext = "32757-06.html";
					}
					else if (ragsCount < 100)
					{
						htmltext = "32757-07.html";
					}
					else
					{
						htmltext = event;
					}
				}
				break;
			}
			case "rags100":
			{
				if (ragsCount >= 100)
				{
					if (getRandom(10) < 5)
					{
						if (getRandom(1000) < 438)
						{
							giveItems(player, 10373 + getRandom(9), 1);
						}
						else
						{
							giveItems(player, 10397 + getRandom(9), 1);
						}
					}
					else
					{
						rewardScroll(player, 1);
					}
					
					takeItems(player, TATTERED_MONK_CLOTHES, 100);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					htmltext = "32757-09.html";
				}
				else
				{
					htmltext = "32757-10.html";
				}
				break;
			}
			case "rags200":
			{
				if (ragsCount >= 200)
				{
					if (getRandom(1000) < 549)
					{
						giveItems(player, 10373 + getRandom(9), 1);
					}
					else
					{
						giveItems(player, 10397 + getRandom(9), 1);
					}
					rewardScroll(player, 2);
					
					takeItems(player, TATTERED_MONK_CLOTHES, 200);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					htmltext = "32757-09.html";
				}
				else
				{
					htmltext = "32757-10.html";
				}
				break;
			}
			case "rags300":
			{
				if (ragsCount >= 300)
				{
					giveItems(player, 10373 + getRandom(9), 1);
					giveItems(player, 10397 + getRandom(9), 1);
					rewardScroll(player, 3);
					
					takeItems(player, TATTERED_MONK_CLOTHES, 300);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					htmltext = "32757-09.html";
				}
				else
				{
					htmltext = "32757-10.html";
				}
				break;
			}
			case "rags400":
			{
				if (ragsCount >= 400)
				{
					giveItems(player, 10373 + getRandom(9), 1);
					giveItems(player, 10397 + getRandom(9), 1);
					rewardScroll(player, 3);
					
					if (getRandom(10) < 5)
					{
						if (getRandom(1000) < 438)
						{
							giveItems(player, 10373 + getRandom(9), 1);
						}
						else
						{
							giveItems(player, 10397 + getRandom(9), 1);
						}
					}
					else
					{
						rewardScroll(player, 1);
					}
					
					takeItems(player, TATTERED_MONK_CLOTHES, 400);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					htmltext = "32757-09.html";
				}
				else
				{
					htmltext = "32757-10.html";
				}
				break;
			}
			case "rags500":
			{
				if (ragsCount >= 500)
				{
					giveItems(player, 10373 + getRandom(9), 1);
					giveItems(player, 10397 + getRandom(9), 1);
					rewardScroll(player, 3);
					
					if (getRandom(1000) < 549)
					{
						giveItems(player, 10373 + getRandom(9), 1);
					}
					else
					{
						giveItems(player, 10397 + getRandom(9), 1);
					}
					
					rewardScroll(player, 2);
					takeItems(player, TATTERED_MONK_CLOTHES, 500);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					htmltext = "32757-09.html";
				}
				else
				{
					htmltext = "32757-10.html";
				}
				break;
			}
			case "exit1":
			{
				if (qs.isCond(1))
				{
					if (ragsCount >= 1)
					{
						htmltext = "32757-12.html";
					}
					else
					{
						qs.exitQuest(true, true);
						htmltext = "32757-13.html";
					}
				}
				break;
			}
			case "exit2":
			{
				if (qs.isCond(1))
				{
					qs.exitQuest(true, true);
					htmltext = "32757-13.html";
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
			case SEEKER_SOLINA:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 57, false);
				break;
			}
			case SAVIOR_SOLINA:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 55, false);
				break;
			}
			case ASCETIC_SOLINA:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 59, false);
				break;
			}
			case DIVINITY_JUDGE:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 698, false);
				break;
			}
			case DIVINITY_MANAGER:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 735, false);
				break;
			}
			case DIVINITY_SUPERVISOR:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 903, false);
				break;
			}
			case DIVINITY_WORSHIPPER:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 811, false);
				break;
			}
			case DIVINITY_PROTECTOR:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 884, true);
				break;
			}
			case DIVINITY_FIGHTER:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 893, true);
				break;
			}
			case DIVINITY_MAGUS:
			{
				giveItem(getRandomPartyMember(killer, 1), npc, 953, true);
				break;
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
			case State.CREATED:
			{
				final QuestState qst = player.getQuestState(Q10288_SecretMission.class.getSimpleName());
				htmltext = ((player.getLevel() >= MIN_LEVEL) && (qst != null) && qst.isCompleted()) ? "32757-01.htm" : "32757-03.html";
				break;
			}
			case State.STARTED:
			{
				if (qs.isCond(1))
				{
					htmltext = "32757-05.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	/**
	 * Wrapper for this repetitive reward.
	 * @param player the player.
	 * @param type the type.
	 */
	private void rewardScroll(L2PcInstance player, int type)
	{
		int chance;
		int scrollId = 5593;
		switch (type)
		{
			case 1:
			{
				chance = getRandom(100);
				if (chance < 1)
				{
					scrollId = 5593;
				}
				else if (chance < 28)
				{
					scrollId = 5594;
				}
				else if (chance < 61)
				{
					scrollId = 5595;
				}
				else
				{
					scrollId = 9898;
				}
				break;
			}
			case 2:
			{
				chance = getRandom(100);
				if (chance < 20)
				{
					scrollId = 5593;
				}
				else if (chance < 40)
				{
					scrollId = 5594;
				}
				else if (chance < 70)
				{
					scrollId = 5595;
				}
				else
				{
					scrollId = 9898;
				}
				break;
			}
			case 3:
			{
				chance = getRandom(1000);
				if (chance < 242)
				{
					scrollId = 5593;
				}
				else if (chance < 486)
				{
					scrollId = 5594;
				}
				else if (chance < 742)
				{
					scrollId = 5595;
				}
				else
				{
					scrollId = 9898;
				}
				break;
			}
		}
		giveItems(player, scrollId, 1);
	}
	
	/**
	 * Gives an item to one random party member with the proper condition, for the given parameters.
	 * @param player the random player to reward
	 * @param npc the killed npc
	 * @param chance the reward chance
	 * @param atLeastOne if {@code true} it will reward two items if the chance is meet and one if the chance is not meet, if {@code false} if the chance is not meet doesn't reward, otherwise reward one item
	 */
	private static void giveItem(L2PcInstance player, L2Npc npc, int chance, boolean atLeastOne)
	{
		if ((player != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			final int count = ((getRandom(1000) < chance) ? 1 : 0) + (atLeastOne ? 1 : 0);
			if (count > 0)
			{
				giveItems(player, TATTERED_MONK_CLOTHES, count);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
	}
}

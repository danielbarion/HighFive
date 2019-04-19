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
package quests.Q10289_FadeToBlack;

import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q10288_SecretMission.Q10288_SecretMission;

/**
 * Fade to Black (10289)
 * @author Plim
 */
public class Q10289_FadeToBlack extends Quest
{
	// NPC
	private static final int GREYMORE = 32757;
	// Items
	private static final int MARK_OF_SPLENDOR = 15527;
	private static final int MARK_OF_DARKNESS = 15528;
	// Monster
	private static final int ANAYS = 25701;
	
	public Q10289_FadeToBlack()
	{
		super(10289);
		addStartNpc(GREYMORE);
		addTalkId(GREYMORE);
		addKillId(ANAYS);
		registerQuestItems(MARK_OF_SPLENDOR, MARK_OF_DARKNESS);
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
			case "32757-02.htm":
			{
				htmltext = event;
				break;
			}
			case "32757-03.htm":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32757-06.html":
			{
				if (qs.isCond(2) && hasQuestItems(player, MARK_OF_DARKNESS))
				{
					htmltext = "32757-07.html";
				}
				else if (qs.isCond(3) && hasQuestItems(player, MARK_OF_SPLENDOR))
				{
					htmltext = "32757-08.html";
				}
				else
				{
					htmltext = event;
				}
				break;
			}
			case "11":
			case "12":
			case "13":
			case "14":
			case "15":
			case "16":
			case "17":
			case "18":
			case "19":
			case "20":
			case "21":
			case "22":
			case "23":
			case "24":
			case "25":
			case "26":
			case "27":
			case "28":
			case "29":
			case "30":
			{
				if (qs.isCond(3) && hasQuestItems(player, MARK_OF_SPLENDOR))
				{
					// see 32757-08.html for recipe list (all moirai armor 60%)
					switch (event)
					{
						case "11":
						{
							rewardItems(player, 15775, 1);
							giveAdena(player, 420920, true);
							break;
						}
						case "12":
						{
							rewardItems(player, 15776, 1);
							giveAdena(player, 420920, true);
							break;
						}
						case "13":
						{
							rewardItems(player, 15777, 1);
							giveAdena(player, 420920, true);
							break;
						}
						case "14":
						{
							rewardItems(player, 15778, 1);
							break;
						}
						case "15":
						{
							rewardItems(player, 15779, 1);
							giveAdena(player, 168360, true);
							break;
						}
						case "16":
						{
							rewardItems(player, 15780, 1);
							giveAdena(player, 168360, true);
							break;
						}
						case "17":
						{
							rewardItems(player, 15781, 1);
							giveAdena(player, 252540, true);
							break;
						}
						case "18":
						{
							rewardItems(player, 15782, 1);
							giveAdena(player, 357780, true);
							break;
						}
						case "19":
						{
							rewardItems(player, 15783, 1);
							giveAdena(player, 357780, true);
							break;
						}
						case "20":
						{
							rewardItems(player, 15784, 1);
							giveAdena(player, 505100, true);
							break;
						}
						case "21":
						{
							rewardItems(player, 15785, 1);
							giveAdena(player, 505100, true);
							break;
						}
						case "22":
						{
							rewardItems(player, 15786, 1);
							giveAdena(player, 505100, true);
							break;
						}
						case "23":
						{
							rewardItems(player, 15787, 1);
							giveAdena(player, 505100, true);
							break;
						}
						case "24":
						{
							rewardItems(player, 15787, 1);
							giveAdena(player, 505100, true);
							break;
						}
						case "25":
						{
							rewardItems(player, 15789, 1);
							giveAdena(player, 505100, true);
							break;
						}
						case "26":
						{
							rewardItems(player, 15790, 1);
							giveAdena(player, 496680, true);
							break;
						}
						case "27":
						{
							rewardItems(player, 15791, 1);
							giveAdena(player, 496680, true);
							break;
						}
						case "28":
						{
							rewardItems(player, 15792, 1);
							giveAdena(player, 563860, true);
							break;
						}
						case "29":
						{
							rewardItems(player, 15793, 1);
							giveAdena(player, 509040, true);
							break;
						}
						case "30":
						{
							rewardItems(player, 15794, 1);
							giveAdena(player, 454240, true);
							break;
						}
					}
					
					final long marksOfDarkness = getQuestItemsCount(player, MARK_OF_DARKNESS);
					if (marksOfDarkness > 0)
					{
						addExpAndSp(player, 55983 * marksOfDarkness, 136500 * (int) marksOfDarkness);
					}
					qs.exitQuest(false, true);
					htmltext = "32757-09.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc anays, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, -1, 3, anays);
		if (qs != null)
		{
			if (qs.getPlayer().isInParty())
			{
				// if player is in party, reward all members
				final L2Party party = qs.getPlayer().getParty();
				final int rnd = getRandom(party.getMemberCount());
				int idx = 0;
				
				for (L2PcInstance member : party.getMembers())
				{
					// only one lucky player will get the good item, the rest will get the bad one
					rewardPlayer(getQuestState(member, false), idx == rnd);
					idx++;
				}
			}
			else
			{
				// if no party, the winner gets it all
				rewardPlayer(qs, true);
			}
		}
		return super.onKill(anays, killer, isSummon);
	}
	
	@Override
	public boolean checkPartyMember(QuestState qs, L2Npc npc)
	{
		return qs.getCond() < 3;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isCreated())
		{
			final QuestState q10288 = player.getQuestState(Q10288_SecretMission.class.getSimpleName());
			if ((player.getLevel() < 82) || (q10288 == null) || !q10288.isCompleted())
			{
				htmltext = "32757-00.htm";
			}
			else
			{
				htmltext = "32757-01.htm";
			}
		}
		else if (qs.isStarted())
		{
			switch (qs.getCond())
			{
				case 1:
				{
					htmltext = "32757-04.html";
					break;
				}
				case 2:
				case 3:
				{
					htmltext = "32757-05.html";
					break;
				}
			}
		}
		else
		{
			htmltext = "32757-10.html";
		}
		return htmltext;
	}
	
	private static void rewardPlayer(QuestState qs, boolean isLucky)
	{
		if ((qs != null) && qs.isCond(1))
		{
			giveItems(qs.getPlayer(), isLucky ? MARK_OF_SPLENDOR : MARK_OF_DARKNESS, 1);
			qs.setCond(isLucky ? 3 : 2, true);
		}
	}
}

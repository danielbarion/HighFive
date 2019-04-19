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
package quests.Q00627_HeartInSearchOfPower;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Heart in Search of Power (627)
 * @author Citizen
 */
public class Q00627_HeartInSearchOfPower extends Quest
{
	// NPCs
	private static final int MYSTERIOUS_NECROMANCER = 31518;
	private static final int ENFEUX = 31519;
	// Items
	private static final int SEAL_OF_LIGHT = 7170;
	private static final int BEAD_OF_OBEDIENCE = 7171;
	private static final int GEM_OF_SAINTS = 7172;
	// Monsters
	private static final Map<Integer, Integer> MONSTERS = new HashMap<>();
	static
	{
		MONSTERS.put(21520, 661); // Eye of Splendor
		MONSTERS.put(21523, 668); // Flash of Splendor
		MONSTERS.put(21524, 714); // Blade of Splendor
		MONSTERS.put(21525, 714); // Blade of Splendor
		MONSTERS.put(21526, 796); // Wisdom of Splendor
		MONSTERS.put(21529, 659); // Soul of Splendor
		MONSTERS.put(21530, 704); // Victory of Splendor
		MONSTERS.put(21531, 791); // Punishment of Splendor
		MONSTERS.put(21532, 820); // Shout of Splendor
		MONSTERS.put(21535, 827); // Signet of Splendor
		MONSTERS.put(21536, 798); // Crown of Splendor
		MONSTERS.put(21539, 875); // Wailing of Splendor
		MONSTERS.put(21540, 875); // Wailing of Splendor
		MONSTERS.put(21658, 791); // Punishment of Splendor
	}
	// Misc
	private static final int MIN_LEVEL_REQUIRED = 60;
	private static final int BEAD_OF_OBEDIENCE_COUNT_REQUIRED = 300;
	// Rewards ID's
	private static final int ASOFE = 4043;
	private static final int THONS = 4044;
	private static final int ENRIA = 4042;
	private static final int MOLD_HARDENER = 4041;
	
	public Q00627_HeartInSearchOfPower()
	{
		super(627);
		addStartNpc(MYSTERIOUS_NECROMANCER);
		addTalkId(MYSTERIOUS_NECROMANCER, ENFEUX);
		addKillId(MONSTERS.keySet());
		registerQuestItems(SEAL_OF_LIGHT, BEAD_OF_OBEDIENCE, GEM_OF_SAINTS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		String htmltext = event;
		switch (event)
		{
			case "31518-02.htm":
			{
				qs.startQuest();
				break;
			}
			case "31518-06.html":
			{
				if (getQuestItemsCount(player, BEAD_OF_OBEDIENCE) < BEAD_OF_OBEDIENCE_COUNT_REQUIRED)
				{
					return "31518-05.html";
				}
				giveItems(player, SEAL_OF_LIGHT, 1);
				takeItems(player, BEAD_OF_OBEDIENCE, -1);
				qs.setCond(3);
				break;
			}
			case "Adena":
			case "Asofes":
			case "Thons":
			case "Enrias":
			case "Mold_Hardener":
			{
				if (!hasQuestItems(player, GEM_OF_SAINTS))
				{
					return "31518-11.html";
				}
				switch (event)
				{
					case "Adena":
					{
						giveAdena(player, 100000, true);
						break;
					}
					case "Asofes":
					{
						rewardItems(player, ASOFE, 13);
						giveAdena(player, 6400, true);
						break;
					}
					case "Thons":
					{
						rewardItems(player, THONS, 13);
						giveAdena(player, 6400, true);
						break;
					}
					case "Enrias":
					{
						rewardItems(player, ENRIA, 6);
						giveAdena(player, 13600, true);
						break;
					}
					case "Mold_Hardener":
					{
						rewardItems(player, MOLD_HARDENER, 3);
						giveAdena(player, 17200, true);
						break;
					}
				}
				htmltext = "31518-10.html";
				qs.exitQuest(true);
				break;
			}
			case "31519-02.html":
			{
				if (hasQuestItems(player, SEAL_OF_LIGHT) && qs.isCond(3))
				{
					giveItems(player, GEM_OF_SAINTS, 1);
					takeItems(player, SEAL_OF_LIGHT, -1);
					qs.setCond(4);
				}
				else
				{
					htmltext = getNoQuestMsg(player);
				}
				break;
			}
			case "31518-09.html":
			{
				break;
			}
			default:
			{
				htmltext = null;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(killer, 1);
		if (partyMember != null)
		{
			final QuestState qs = getQuestState(partyMember, false);
			final float chance = MONSTERS.get(npc.getId()) * Config.RATE_QUEST_DROP;
			if (getRandom(1000) < chance)
			{
				giveItems(partyMember, BEAD_OF_OBEDIENCE, 1);
				if (getQuestItemsCount(partyMember, BEAD_OF_OBEDIENCE) < BEAD_OF_OBEDIENCE_COUNT_REQUIRED)
				{
					playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				else
				{
					qs.setCond(2, true);
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
			case State.CREATED:
			{
				if (npc.getId() == MYSTERIOUS_NECROMANCER)
				{
					htmltext = (player.getLevel() >= MIN_LEVEL_REQUIRED) ? "31518-01.htm" : "31518-00.htm";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case MYSTERIOUS_NECROMANCER:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "31518-03.html";
								break;
							}
							case 2:
							{
								htmltext = "31518-04.html";
								break;
							}
							case 3:
							{
								htmltext = "31518-07.html";
								break;
							}
							case 4:
							{
								htmltext = "31518-08.html";
								break;
							}
						}
						break;
					}
					case ENFEUX:
					{
						switch (qs.getCond())
						{
							case 3:
							{
								htmltext = "31519-01.html";
								break;
							}
							case 4:
							{
								htmltext = "31519-03.html";
								break;
							}
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
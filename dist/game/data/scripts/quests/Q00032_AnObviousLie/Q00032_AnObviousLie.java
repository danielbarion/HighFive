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
package quests.Q00032_AnObviousLie;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * An Obvious Lie (32).
 * @author janiko
 */
public final class Q00032_AnObviousLie extends Quest
{
	// NPCs
	private static final int MAXIMILIAN = 30120;
	private static final int GENTLER = 30094;
	private static final int MIKI_THE_CAT = 31706;
	// Monster
	private static final int ALLIGATOR = 20135;
	// Items
	private static final int MAP_OF_GENTLER = 7165;
	private static final ItemHolder MEDICINAL_HERB = new ItemHolder(7166, 20);
	private static final ItemHolder SPIRIT_ORE = new ItemHolder(3031, 500);
	private static final ItemHolder THREAD = new ItemHolder(1868, 1000);
	private static final ItemHolder SUEDE = new ItemHolder(1866, 500);
	// Misc
	private static final int MIN_LVL = 45;
	// Reward
	private static final Map<String, Integer> EARS = new HashMap<>();
	{
		EARS.put("cat", 6843); // Cat Ears
		EARS.put("raccoon", 7680); // Raccoon ears
		EARS.put("rabbit", 7683); // Rabbit ears
	}
	
	public Q00032_AnObviousLie()
	{
		super(32);
		addStartNpc(MAXIMILIAN);
		addTalkId(MAXIMILIAN, GENTLER, MIKI_THE_CAT);
		addKillId(ALLIGATOR);
		registerQuestItems(MAP_OF_GENTLER, MEDICINAL_HERB.getId());
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
			case "30120-02.html":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30094-02.html":
			{
				if (qs.isCond(1))
				{
					giveItems(player, MAP_OF_GENTLER, 1);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "31706-02.html":
			{
				if (qs.isCond(2) && hasQuestItems(player, MAP_OF_GENTLER))
				{
					takeItems(player, MAP_OF_GENTLER, -1);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "30094-06.html":
			{
				if (qs.isCond(4) && hasItem(player, MEDICINAL_HERB))
				{
					takeItem(player, MEDICINAL_HERB);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "30094-09.html":
			{
				if (qs.isCond(5) && hasItem(player, SPIRIT_ORE))
				{
					takeItem(player, SPIRIT_ORE);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "30094-12.html":
			{
				if (qs.isCond(7))
				{
					qs.setCond(8, true);
					htmltext = event;
				}
				break;
			}
			case "30094-15.html":
			{
				htmltext = event;
				break;
			}
			case "31706-05.html":
			{
				if (qs.isCond(6))
				{
					qs.setCond(7, true);
					htmltext = event;
				}
				break;
			}
			case "cat":
			case "raccoon":
			case "rabbit":
			{
				if (qs.isCond(8) && takeAllItems(player, THREAD, SUEDE))
				{
					giveItems(player, EARS.get(event), 1);
					qs.exitQuest(false, true);
					htmltext = "30094-16.html";
				}
				else
				{
					htmltext = "30094-17.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, 3, 3, npc);
		if ((qs != null) && giveItemRandomly(qs.getPlayer(), npc, MEDICINAL_HERB.getId(), 1, MEDICINAL_HERB.getCount(), 1.0, true))
		{
			qs.setCond(4);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case MAXIMILIAN:
			{
				if (qs.isCreated())
				{
					htmltext = (player.getLevel() >= MIN_LVL) ? "30120-01.htm" : "30120-03.htm";
				}
				else if (qs.isStarted())
				{
					if (qs.isCond(1))
					{
						htmltext = "30120-04.html";
					}
				}
				else
				{
					htmltext = getAlreadyCompletedMsg(player);
				}
				break;
			}
			case GENTLER:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "30094-01.html";
						break;
					}
					case 2:
					{
						htmltext = "30094-03.html";
						break;
					}
					case 4:
					{
						htmltext = hasItem(player, MEDICINAL_HERB) ? "30094-04.html" : "30094-05.html";
						break;
					}
					case 5:
					{
						htmltext = hasItem(player, SPIRIT_ORE) ? "30094-07.html" : "30094-08.html";
						break;
					}
					case 6:
					{
						htmltext = "30094-10.html";
						break;
					}
					case 7:
					{
						htmltext = "30094-11.html";
						break;
					}
					case 8:
					{
						if (hasAllItems(player, true, THREAD, SUEDE))
						{
							htmltext = "30094-13.html";
						}
						else
						{
							htmltext = "30094-14.html";
						}
						break;
					}
				}
				break;
			}
			case MIKI_THE_CAT:
			{
				switch (qs.getCond())
				{
					case 2:
					{
						if (hasQuestItems(player, MAP_OF_GENTLER))
						{
							htmltext = "31706-01.html";
						}
						break;
					}
					case 3:
					case 4:
					case 5:
					{
						htmltext = "31706-03.html";
						break;
					}
					case 6:
					{
						htmltext = "31706-04.html";
						break;
					}
					case 7:
					{
						htmltext = "31706-06.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

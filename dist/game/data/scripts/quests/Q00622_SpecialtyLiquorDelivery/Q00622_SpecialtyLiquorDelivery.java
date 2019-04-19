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
package quests.Q00622_SpecialtyLiquorDelivery;

import java.util.Arrays;
import java.util.List;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Specialty Liquor Delivery (622)
 * @author Janiko
 */
public final class Q00622_SpecialtyLiquorDelivery extends Quest
{
	// NPCs
	private static final int JEREMY = 31521;
	private static final int PULIN = 31543;
	private static final int NAFF = 31544;
	private static final int CROCUS = 31545;
	private static final int KUBER = 31546;
	private static final int BOELIN = 31547;
	private static final int LIETTA = 31267;
	// Items
	private static final int SPECIAL_DRINK = 7197;
	private static final int SPECIAL_DRINK_PRICE = 7198;
	// Rewards
	private static final int QUICK_STEP_POTION = 734;
	private static final int SEALED_RING_OF_AURAKYRA = 6849;
	private static final int SEALED_SANDDRAGONS_EARING = 6847;
	private static final int SEALED_DRAGON_NECKLACE = 6851;
	// Misc
	private static final int MIN_LVL = 68;
	// Talkers
	private static final List<Integer> TALKERS = Arrays.asList(KUBER, CROCUS, NAFF, PULIN);
	
	public Q00622_SpecialtyLiquorDelivery()
	{
		super(622);
		addStartNpc(JEREMY);
		addTalkId(JEREMY, BOELIN, LIETTA);
		addTalkId(TALKERS);
		registerQuestItems(SPECIAL_DRINK, SPECIAL_DRINK_PRICE);
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
			case "31521-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					giveItems(player, SPECIAL_DRINK, 5);
					htmltext = event;
				}
				break;
			}
			case "31521-06.html":
			{
				if (qs.isCond(6))
				{
					if (getQuestItemsCount(player, SPECIAL_DRINK_PRICE) >= 5)
					{
						qs.setCond(7, true);
						takeItems(player, -1, SPECIAL_DRINK_PRICE);
						htmltext = event;
					}
					else
					{
						htmltext = "31521-07.html";
					}
				}
				break;
			}
			case "31547-02.html":
			{
				if (qs.isCond(1))
				{
					if (hasQuestItems(player, SPECIAL_DRINK))
					{
						qs.setCond(2, true);
						takeItems(player, SPECIAL_DRINK, 1);
						giveItems(player, SPECIAL_DRINK_PRICE, 1);
						htmltext = event;
					}
					else
					{
						htmltext = "31547-03.html";
					}
				}
				break;
			}
			case "31543-02.html":
			case "31544-02.html":
			case "31545-02.html":
			case "31546-02.html":
			{
				if (TALKERS.contains(npc.getId()) && qs.isCond(TALKERS.indexOf(npc.getId()) + 2))
				{
					if (hasQuestItems(player, SPECIAL_DRINK))
					{
						qs.setCond(qs.getCond() + 1, true);
						takeItems(player, SPECIAL_DRINK, 1);
						giveItems(player, SPECIAL_DRINK_PRICE, 1);
						htmltext = event;
					}
					else
					{
						htmltext = npc.getId() + "-03.html";
					}
				}
				break;
			}
			case "31267-02.html":
			{
				if (qs.isCond(7))
				{
					final int rnd = getRandom(1000);
					if (rnd < 800)
					{
						rewardItems(player, QUICK_STEP_POTION, 1);
						giveAdena(player, 18800, true);
					}
					else if (rnd < 880)
					{
						rewardItems(player, SEALED_RING_OF_AURAKYRA, 1);
					}
					else if (rnd < 960)
					{
						rewardItems(player, SEALED_SANDDRAGONS_EARING, 1);
					}
					else
					{
						rewardItems(player, SEALED_DRAGON_NECKLACE, 1);
					}
					qs.exitQuest(true, true);
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
		switch (npc.getId())
		{
			case JEREMY:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (talker.getLevel() >= MIN_LVL) ? "31521-01.htm" : "31521-02.htm";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "31521-04.html";
								break;
							}
							case 6:
							{
								if (hasQuestItems(talker, SPECIAL_DRINK_PRICE))
								{
									htmltext = "31521-05.html";
								}
								break;
							}
							case 7:
							{
								if (!hasQuestItems(talker, SPECIAL_DRINK))
								{
									htmltext = "31521-08.html";
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
				break;
			}
			case BOELIN:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							if (getQuestItemsCount(talker, SPECIAL_DRINK) >= 5)
							{
								htmltext = "31547-01.html";
							}
							break;
						}
						case 2:
						{
							htmltext = "31547-04.html";
							break;
						}
					}
				}
				break;
			}
			case KUBER:
			case CROCUS:
			case NAFF:
			case PULIN:
			{
				if (qs.isStarted())
				{
					final int cond = TALKERS.indexOf(npc.getId()) + 2;
					if (qs.isCond(cond) && hasQuestItems(talker, SPECIAL_DRINK_PRICE)) // 2,3,4,5
					{
						htmltext = npc.getId() + "-01.html";
					}
					else if (qs.isCond(cond + 1)) // 3,4,5,6
					{
						htmltext = npc.getId() + "-04.html";
					}
				}
				break;
			}
			case LIETTA:
			{
				if (qs.isStarted() && qs.isCond(7))
				{
					htmltext = "31267-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
}
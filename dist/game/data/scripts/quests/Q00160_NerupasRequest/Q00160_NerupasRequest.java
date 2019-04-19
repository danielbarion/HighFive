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
package quests.Q00160_NerupasRequest;

import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Nerupa's Request (160)
 * @author ivantotov
 */
public final class Q00160_NerupasRequest extends Quest
{
	// NPCs
	private static final int NERUPA = 30370;
	private static final int UNOREN = 30147;
	private static final int CREAMEES = 30149;
	private static final int JULIA = 30152;
	// Items
	private static final int SILVERY_SPIDERSILK = 1026;
	private static final int UNOS_RECEIPT = 1027;
	private static final int CELS_TICKET = 1028;
	private static final int NIGHTSHADE_LEAF = 1029;
	// Reward
	private static final int LESSER_HEALING_POTION = 1060;
	// Misc
	private static final int MIN_LEVEL = 3;
	
	public Q00160_NerupasRequest()
	{
		super(160);
		addStartNpc(NERUPA);
		addTalkId(NERUPA, UNOREN, CREAMEES, JULIA);
		registerQuestItems(SILVERY_SPIDERSILK, UNOS_RECEIPT, CELS_TICKET, NIGHTSHADE_LEAF);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && event.equals("30370-04.htm"))
		{
			qs.startQuest();
			if (!hasQuestItems(player, SILVERY_SPIDERSILK))
			{
				giveItems(player, SILVERY_SPIDERSILK, 1);
			}
			return event;
		}
		return null;
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
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == NERUPA)
				{
					if (player.getRace() != Race.ELF)
					{
						htmltext = "30370-01.htm";
					}
					else if (player.getLevel() < MIN_LEVEL)
					{
						htmltext = "30370-02.htm";
					}
					else
					{
						htmltext = "30370-03.htm";
					}
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case NERUPA:
					{
						if (hasAtLeastOneQuestItem(player, SILVERY_SPIDERSILK, UNOS_RECEIPT, CELS_TICKET))
						{
							htmltext = "30370-05.html";
						}
						else if (hasQuestItems(player, NIGHTSHADE_LEAF))
						{
							rewardItems(player, LESSER_HEALING_POTION, 5);
							addExpAndSp(player, 1000, 0);
							qs.exitQuest(false, true);
							htmltext = "30370-06.html";
						}
						break;
					}
					case UNOREN:
					{
						if (hasQuestItems(player, SILVERY_SPIDERSILK))
						{
							takeItems(player, SILVERY_SPIDERSILK, -1);
							if (!hasQuestItems(player, UNOS_RECEIPT))
							{
								giveItems(player, UNOS_RECEIPT, 1);
							}
							qs.setCond(2, true);
							htmltext = "30147-01.html";
						}
						else if (hasQuestItems(player, UNOS_RECEIPT))
						{
							htmltext = "30147-02.html";
						}
						else if (hasQuestItems(player, NIGHTSHADE_LEAF))
						{
							htmltext = "30147-03.html";
						}
						break;
					}
					case CREAMEES:
					{
						if (hasQuestItems(player, UNOS_RECEIPT))
						{
							takeItems(player, UNOS_RECEIPT, -1);
							if (!hasQuestItems(player, CELS_TICKET))
							{
								giveItems(player, CELS_TICKET, 1);
							}
							qs.setCond(3, true);
							htmltext = "30149-01.html";
						}
						else if (hasQuestItems(player, CELS_TICKET))
						{
							htmltext = "30149-02.html";
						}
						else if (hasQuestItems(player, NIGHTSHADE_LEAF))
						{
							htmltext = "30149-03.html";
						}
						break;
					}
					case JULIA:
					{
						if (hasQuestItems(player, CELS_TICKET))
						{
							takeItems(player, CELS_TICKET, -1);
							if (!hasQuestItems(player, NIGHTSHADE_LEAF))
							{
								giveItems(player, NIGHTSHADE_LEAF, 1);
							}
							qs.setCond(4, true);
							htmltext = "30152-01.html";
							
						}
						else if (hasQuestItems(player, NIGHTSHADE_LEAF))
						{
							htmltext = "30152-02.html";
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
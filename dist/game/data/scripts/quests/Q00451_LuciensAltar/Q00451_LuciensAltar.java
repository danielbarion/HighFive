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
package quests.Q00451_LuciensAltar;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Lucien's Altar (451)<br>
 * Original Jython script by Bloodshed.
 * @author malyelfik
 */
public class Q00451_LuciensAltar extends Quest
{
	// NPCs
	private static final int DAICHIR = 30537;
	private static final int[] ALTARS =
	{
		32706,
		32707,
		32708,
		32709,
		32710
	};
	// Items
	private static final int REPLENISHED_BEAD = 14877;
	private static final int DISCHARGED_BEAD = 14878;
	// Misc
	private static final int MIN_LEVEL = 80;
	
	public Q00451_LuciensAltar()
	{
		super(451);
		addStartNpc(DAICHIR);
		addTalkId(ALTARS);
		addTalkId(DAICHIR);
		registerQuestItems(REPLENISHED_BEAD, DISCHARGED_BEAD);
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
		if (event.equals("30537-04.htm"))
		{
			htmltext = event;
		}
		else if (event.equals("30537-05.htm"))
		{
			qs.startQuest();
			giveItems(player, REPLENISHED_BEAD, 5);
			htmltext = event;
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		final int npcId = npc.getId();
		if (npcId == DAICHIR)
		{
			switch (qs.getState())
			{
				case State.COMPLETED:
				{
					if (!qs.isNowAvailable())
					{
						htmltext = "30537-03.html";
						break;
					}
					qs.setState(State.CREATED);
					// fallthrou
				}
				case State.CREATED:
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "30537-01.htm" : "30537-02.htm";
					break;
				}
				case State.STARTED:
				{
					if (qs.isCond(1))
					{
						if (qs.isSet("32706") || qs.isSet("32707") || qs.isSet("32708") || qs.isSet("32709") || qs.isSet("32710"))
						{
							htmltext = "30537-10.html";
						}
						else
						{
							htmltext = "30537-09.html";
						}
					}
					else
					{
						giveAdena(player, 255380, true); // Tauti reward: 13 773 960 exp, 16 232 820 sp, 742 800 Adena
						qs.exitQuest(QuestType.DAILY, true);
						htmltext = "30537-08.html";
					}
					break;
				}
			}
		}
		else if (qs.isCond(1) && hasQuestItems(player, REPLENISHED_BEAD))
		{
			if (qs.getInt(String.valueOf(npcId)) == 0)
			{
				qs.set(String.valueOf(npcId), "1");
				takeItems(player, REPLENISHED_BEAD, 1);
				giveItems(player, DISCHARGED_BEAD, 1);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				
				if (getQuestItemsCount(player, DISCHARGED_BEAD) >= 5)
				{
					qs.setCond(2, true);
				}
				
				htmltext = "recharge.html";
			}
			else
			{
				htmltext = "findother.html";
			}
		}
		
		return htmltext;
	}
}
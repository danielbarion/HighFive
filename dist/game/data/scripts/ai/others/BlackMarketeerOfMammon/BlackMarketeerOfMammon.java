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
package ai.others.BlackMarketeerOfMammon;

import java.util.Calendar;

import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import ai.AbstractNpcAI;

/**
 * Black Marketeer of Mammon - Exchange Adena for AA.
 * @author Adry_85
 */
public final class BlackMarketeerOfMammon extends AbstractNpcAI
{
	// NPC
	private static final int BLACK_MARKETEER = 31092;
	// Misc
	private static final int MIN_LEVEL = 60;
	
	private BlackMarketeerOfMammon()
	{
		addStartNpc(BLACK_MARKETEER);
		addTalkId(BLACK_MARKETEER);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		return exchangeAvailable() ? "31092-01.html" : "31092-02.html";
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		if ("exchange".equals(event))
		{
			if (exchangeAvailable())
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					final QuestState qs = getQuestState(player, true);
					if ((qs.get("restartTime") != null) && !qs.isNowAvailable())
					{
						htmltext = "31092-03.html";
					}
					else
					{
						if (player.getAdena() >= 2000000)
						{
							qs.setState(State.STARTED);
							takeItems(player, Inventory.ADENA_ID, 2000000);
							giveItems(player, Inventory.ANCIENT_ADENA_ID, 500000);
							htmltext = "31092-04.html";
							qs.exitQuest(QuestType.DAILY, false);
						}
						else
						{
							htmltext = "31092-05.html";
						}
					}
				}
				else
				{
					htmltext = "31092-06.html";
				}
			}
			else
			{
				htmltext = "31092-02.html";
			}
		}
		
		return htmltext;
	}
	
	private boolean exchangeAvailable()
	{
		final Calendar currentTime = Calendar.getInstance();
		final Calendar minTime = Calendar.getInstance();
		minTime.set(Calendar.HOUR_OF_DAY, 20);
		minTime.set(Calendar.MINUTE, 0);
		minTime.set(Calendar.SECOND, 0);
		final Calendar maxtTime = Calendar.getInstance();
		maxtTime.set(Calendar.HOUR_OF_DAY, 23);
		maxtTime.set(Calendar.MINUTE, 59);
		maxtTime.set(Calendar.SECOND, 59);
		
		return (currentTime.compareTo(minTime) >= 0) && (currentTime.compareTo(maxtTime) <= 0);
	}
	
	public static void main(String[] args)
	{
		new BlackMarketeerOfMammon();
	}
}

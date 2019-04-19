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
package quests.Q00052_WilliesSpecialBait;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Willie's Special Bait (52)<br>
 * Original Jython script by Kilkenny.
 * @author nonom
 */
public class Q00052_WilliesSpecialBait extends Quest
{
	// NPCs
	private static final int WILLIE = 31574;
	private static final int TARLK_BASILISK = 20573;
	// Items
	private static final int TARLK_EYE = 7623;
	private static final int EARTH_FISHING_LURE = 7612;
	
	public Q00052_WilliesSpecialBait()
	{
		super(52);
		addStartNpc(WILLIE);
		addTalkId(WILLIE);
		addKillId(TARLK_BASILISK);
		registerQuestItems(TARLK_EYE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		String htmltext = event;
		switch (event)
		{
			case "31574-03.htm":
			{
				st.startQuest();
				break;
			}
			case "31574-07.html":
			{
				if (st.isCond(2) && (getQuestItemsCount(player, TARLK_EYE) >= 100))
				{
					htmltext = "31574-06.htm";
					giveItems(player, EARTH_FISHING_LURE, 4);
					st.exitQuest(false, true);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 1);
		if (partyMember == null)
		{
			return null;
		}
		
		final QuestState st = getQuestState(partyMember, false);
		if (getQuestItemsCount(player, TARLK_EYE) < 100)
		{
			final float chance = 33 * Config.RATE_QUEST_DROP;
			if (getRandom(100) < chance)
			{
				rewardItems(player, TARLK_EYE, 1);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		
		if (getQuestItemsCount(player, TARLK_EYE) >= 100)
		{
			st.setCond(2, true);
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (st.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				htmltext = (player.getLevel() >= 48) ? "31574-01.htm" : "31574-02.html";
				break;
			}
			case State.STARTED:
			{
				htmltext = (st.isCond(1)) ? "31574-05.html" : "31574-04.html";
				break;
			}
		}
		return htmltext;
	}
}

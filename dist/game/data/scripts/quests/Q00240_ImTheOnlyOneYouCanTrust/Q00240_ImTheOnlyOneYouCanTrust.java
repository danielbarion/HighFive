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
package quests.Q00240_ImTheOnlyOneYouCanTrust;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * I'm the Only One You Can Trust (240)
 * @author malyelfik
 */
public class Q00240_ImTheOnlyOneYouCanTrust extends Quest
{
	// NPC
	private static final int KINTAIJIN = 32640;
	// Monster
	private static final int[] MOBS =
	{
		22617,
		22618,
		22619,
		22620,
		22621,
		22622,
		22623,
		22624,
		22625,
		22626,
		22627,
		22628,
		22629,
		22630,
		22631,
		22632,
		22633
	};
	// Item
	private static final int STAKATO_FANG = 14879;
	
	public Q00240_ImTheOnlyOneYouCanTrust()
	{
		super(240);
		addStartNpc(KINTAIJIN);
		addTalkId(KINTAIJIN);
		addKillId(MOBS);
		registerQuestItems(STAKATO_FANG);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		if (event.equalsIgnoreCase("32640-3.htm"))
		{
			qs.startQuest();
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 1);
		if (partyMember == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		
		final QuestState qs = getQuestState(partyMember, false);
		giveItems(partyMember, STAKATO_FANG, 1);
		if (getQuestItemsCount(partyMember, STAKATO_FANG) >= 25)
		{
			qs.setCond(2, true);
		}
		else
		{
			playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		return super.onKill(npc, player, isSummon);
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
				htmltext = (player.getLevel() >= 81) ? "32640-1.htm" : "32640-0.htm";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = !hasQuestItems(player, STAKATO_FANG) ? "32640-8.html" : "32640-9.html";
						break;
					}
					case 2:
					{
						if (getQuestItemsCount(player, STAKATO_FANG) >= 25)
						{
							giveAdena(player, 147200, true);
							takeItems(player, STAKATO_FANG, -1);
							addExpAndSp(player, 589542, 36800);
							qs.exitQuest(false, true);
							htmltext = "32640-10.html";
						}
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				htmltext = "32640-11.html";
				break;
			}
		}
		return htmltext;
	}
}
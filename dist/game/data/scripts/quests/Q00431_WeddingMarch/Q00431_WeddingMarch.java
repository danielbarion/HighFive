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
package quests.Q00431_WeddingMarch;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Wedding March (431)<br>
 * Original Jython script by CubicVirtuoso.
 * @author eyjine
 */
public class Q00431_WeddingMarch extends Quest
{
	// NPC
	private static final int KANTABILON = 31042;
	// Monsters
	private static final int[] MOBS =
	{
		20786, // Lienrik
		20787, // Lienrik Lad
	};
	// Items
	private static final int SILVER_CRYSTAL = 7540;
	private static final int WEDDING_ECHO_CRYSTAL = 7062;
	// Misc
	private static final int MIN_LEVEL = 38;
	private static final int CRYSTAL_COUNT = 50;
	
	public Q00431_WeddingMarch()
	{
		super(431);
		addStartNpc(KANTABILON);
		addTalkId(KANTABILON);
		addKillId(MOBS);
		registerQuestItems(SILVER_CRYSTAL);
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
		if (event.equalsIgnoreCase("31042-02.htm"))
		{
			qs.startQuest();
			htmltext = event;
		}
		else if (event.equalsIgnoreCase("31042-06.html"))
		{
			if (getQuestItemsCount(player, SILVER_CRYSTAL) < CRYSTAL_COUNT)
			{
				return "31042-05.html";
			}
			giveItems(player, WEDDING_ECHO_CRYSTAL, 25);
			qs.exitQuest(true, true);
			htmltext = event;
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance member = getRandomPartyMember(player, 1);
		if ((member != null) && getRandomBoolean())
		{
			giveItems(member, SILVER_CRYSTAL, 1);
			if (getQuestItemsCount(member, SILVER_CRYSTAL) >= CRYSTAL_COUNT)
			{
				getQuestState(member, false).setCond(2, true);
			}
			else
			{
				playSound(member, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
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
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "31042-01.htm" : "31042-00.htm";
				break;
			}
			case State.STARTED:
			{
				htmltext = qs.isCond(1) ? "31042-03.html" : "31042-04.html";
				break;
			}
		}
		return htmltext;
	}
}
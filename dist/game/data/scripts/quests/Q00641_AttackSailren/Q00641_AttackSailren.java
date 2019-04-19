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
package quests.Q00641_AttackSailren;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00126_TheNameOfEvil2.Q00126_TheNameOfEvil2;

/**
 * Attack Sailren! (641)
 * @author Adry_85
 */
public class Q00641_AttackSailren extends Quest
{
	// NPC
	private static final int SHILENS_STONE_STATUE = 32109;
	// Items
	private static final int GAZKH_FRAGMENT = 8782;
	private static final int GAZKH = 8784;
	// Monsters
	private static int[] MOBS =
	{
		22196, // Velociraptor
		22197, // Velociraptor
		22198, // Velociraptor
		22218, // Velociraptor
		22223, // Velociraptor
		22199, // Pterosaur
	};
	
	public Q00641_AttackSailren()
	{
		super(641);
		addStartNpc(SHILENS_STONE_STATUE);
		addTalkId(SHILENS_STONE_STATUE);
		addKillId(MOBS);
		registerQuestItems(GAZKH_FRAGMENT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "32109-1.html":
			{
				qs.startQuest();
				break;
			}
			case "32109-2a.html":
			{
				if (getQuestItemsCount(player, GAZKH_FRAGMENT) >= 30)
				{
					giveItems(player, GAZKH, 1);
					qs.exitQuest(true, true);
				}
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 1);
		if (partyMember != null)
		{
			final QuestState qs = getQuestState(partyMember, false);
			if (qs != null)
			{
				giveItems(partyMember, GAZKH_FRAGMENT, 1);
				if (getQuestItemsCount(partyMember, GAZKH_FRAGMENT) < 30)
				{
					playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				else
				{
					qs.setCond(2, true);
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = getNoQuestMsg(player);
		QuestState qs = getQuestState(player, true);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				if (player.getLevel() < 77)
				{
					htmltext = "32109-0.htm";
				}
				else
				{
					qs = player.getQuestState(Q00126_TheNameOfEvil2.class.getSimpleName());
					htmltext = ((qs != null) && qs.isCompleted()) ? "32109-0a.htm" : "32109-0b.htm";
				}
				break;
			}
			case State.STARTED:
			{
				htmltext = qs.isCond(1) ? "32109-1a.html" : "32109-2.html";
				break;
			}
		}
		return htmltext;
	}
}

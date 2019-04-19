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
package ai.areas.FrozenLabyrinth.Jinia;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.QuestState;

import ai.AbstractNpcAI;
import quests.Q10286_ReunionWithSirra.Q10286_ReunionWithSirra;

/**
 * Jinia AI.
 * @author Adry_85
 */
public final class Jinia extends AbstractNpcAI
{
	// NPC
	private static final int JINIA = 32781;
	// Items
	private static final int FROZEN_CORE = 15469;
	private static final int BLACK_FROZEN_CORE = 15470;
	// Misc
	private static final int MIN_LEVEL = 82;
	
	private Jinia()
	{
		addStartNpc(JINIA);
		addFirstTalkId(JINIA);
		addTalkId(JINIA);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		switch (event)
		{
			case "32781-10.html":
			case "32781-11.html":
			{
				htmltext = event;
				break;
			}
			case "check":
			{
				if (hasAtLeastOneQuestItem(player, FROZEN_CORE, BLACK_FROZEN_CORE))
				{
					htmltext = "32781-03.html";
				}
				else
				{
					final QuestState qs = player.getQuestState(Q10286_ReunionWithSirra.class.getSimpleName());
					if ((qs != null) && qs.isCompleted())
					{
						giveItems(player, FROZEN_CORE, 1);
					}
					else
					{
						giveItems(player, BLACK_FROZEN_CORE, 1);
					}
					htmltext = "32781-04.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = player.getQuestState(Q10286_ReunionWithSirra.class.getSimpleName());
		if ((qs != null) && (player.getLevel() >= MIN_LEVEL))
		{
			if (qs.isCond(5) || qs.isCond(6))
			{
				return "32781-09.html";
			}
			else if (qs.isCond(7))
			{
				return "32781-01.html";
			}
		}
		return "32781-02.html";
	}
	
	public static void main(String[] args)
	{
		new Jinia();
	}
}
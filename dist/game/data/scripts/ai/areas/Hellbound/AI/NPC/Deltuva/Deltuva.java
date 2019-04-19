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
package ai.areas.Hellbound.AI.NPC.Deltuva;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.QuestState;

import ai.AbstractNpcAI;
import quests.Q00132_MatrasCuriosity.Q00132_MatrasCuriosity;

/**
 * Deltuva AI.
 * @author GKR
 */
public final class Deltuva extends AbstractNpcAI
{
	// NPCs
	private static final int DELTUVA = 32313;
	// Location
	private static final Location TELEPORT = new Location(17934, 283189, -9701);
	
	public Deltuva()
	{
		addStartNpc(DELTUVA);
		addTalkId(DELTUVA);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equalsIgnoreCase("teleport"))
		{
			final QuestState hostQuest = player.getQuestState(Q00132_MatrasCuriosity.class.getSimpleName());
			if ((hostQuest == null) || !hostQuest.isCompleted())
			{
				return "32313-02.htm";
			}
			player.teleToLocation(TELEPORT);
		}
		return super.onAdvEvent(event, npc, player);
	}
}
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
package ai.others.TownPets;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Town Pets AI
 * @author malyelfik
 */
public final class TownPets extends AbstractNpcAI
{
	// Pet IDs
	private static final int[] PETS =
	{
		31202, // Maximus
		31203, // Moon Dancer
		31204, // Georgio
		31205, // Katz
		31206, // Ten Ten
		31207, // Sardinia
		31208, // La Grange
		31209, // Misty Rain
		31266, // Kaiser
		31593, // Dorothy
		31758, // Rafi
		31955, // Ruby
	};
	
	private TownPets()
	{
		addSpawnId(PETS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equalsIgnoreCase("move"))
		{
			final int locX = (npc.getSpawn().getX() - 50) + getRandom(100);
			final int locY = (npc.getSpawn().getY() - 50) + getRandom(100);
			npc.setRunning();
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(locX, locY, npc.getZ(), 0));
			startQuestTimer("move", 5000, npc, null);
		}
		return null;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		startQuestTimer("move", 3000, npc, null);
		return super.onSpawn(npc);
	}
	
	public static void main(String[] args)
	{
		new TownPets();
	}
}
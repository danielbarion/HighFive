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
package ai.areas.PavelArchaic;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Pavel Archaic AI.
 * @author Gnacik, St3eT
 */
public final class PavelArchaic extends AbstractNpcAI
{
	private static final int SAFETY_DEVICE = 18917; // Pavel Safety Device
	private static final int PINCER_GOLEM = 22801; // Cruel Pincer Golem
	private static final int PINCER_GOLEM2 = 22802; // Cruel Pincer Golem
	private static final int PINCER_GOLEM3 = 22803; // Cruel Pincer Golem
	private static final int JACKHAMMER_GOLEM = 22804; // Horrifying Jackhammer Golem
	
	private PavelArchaic()
	{
		addKillId(SAFETY_DEVICE, PINCER_GOLEM, JACKHAMMER_GOLEM);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (getRandom(100) < 70)
		{
			final L2Npc golem1 = addSpawn(PINCER_GOLEM2, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, false);
			addAttackDesire(golem1, killer);
			
			final L2Npc golem2 = addSpawn(PINCER_GOLEM3, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, false);
			addAttackDesire(golem2, killer);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	public static void main(String[] args)
	{
		new PavelArchaic();
	}
}

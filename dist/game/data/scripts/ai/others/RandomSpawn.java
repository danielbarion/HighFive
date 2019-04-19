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
package ai.others;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;

import ai.AbstractNpcAI;

/**
 * Manages spawn of NPCs having several random spawn points.
 * @author GKR
 */
public final class RandomSpawn extends AbstractNpcAI
{
	private static final Map<Integer, Location[]> SPAWN_POINTS = new HashMap<>();
	static
	{
		// Keltas
		SPAWN_POINTS.put(22341, new Location[]
		{
			new Location(-27136, 250938, -3523),
			new Location(-29658, 252897, -3523),
			new Location(-27237, 251943, -3527),
			new Location(-28868, 250113, -3479)
		});
		// Keymaster
		SPAWN_POINTS.put(22361, new Location[]
		{
			new Location(14091, 250533, -1940),
			new Location(15762, 252440, -2015),
			new Location(19836, 256212, -2090),
			new Location(21940, 254107, -2010),
			new Location(17299, 252943, -2015),
		});
		// Typhoon
		SPAWN_POINTS.put(25539, new Location[]
		{
			new Location(-20641, 255370, -3235),
			new Location(-16157, 250993, -3058),
			new Location(-18269, 250721, -3151),
			new Location(-16532, 254864, -3223),
			new Location(-19055, 253489, -3440),
			new Location(-9684, 254256, -3148),
			new Location(-6209, 251924, -3189),
			new Location(-10547, 251359, -2929),
			new Location(-7254, 254997, -3261),
			new Location(-4883, 253171, -3322)
		});
		// Mutated Elpy
		SPAWN_POINTS.put(25604, new Location[]
		{
			new Location(-46080, 246368, -14183),
			new Location(-44816, 246368, -14183),
			new Location(-44224, 247440, -14184),
			new Location(-44896, 248464, -14183),
			new Location(-46064, 248544, -14183),
			new Location(-46720, 247424, -14183)
		});
	}
	
	public RandomSpawn()
	{
		addSpawnId(SPAWN_POINTS.keySet());
	}
	
	@Override
	public final String onSpawn(L2Npc npc)
	{
		final Location[] spawnlist = SPAWN_POINTS.get(npc.getId());
		final Location loc = spawnlist[getRandom(spawnlist.length)];
		if (!npc.isInsideRadius2D(loc, 200))
		{
			npc.getSpawn().setLocation(loc);
			ThreadPool.schedule(new Teleport(npc, loc), 100);
		}
		return super.onSpawn(npc);
	}
	
	private static class Teleport implements Runnable
	{
		private final L2Npc _npc;
		private final Location _loc;
		
		public Teleport(L2Npc npc, Location loc)
		{
			_npc = npc;
			_loc = loc;
		}
		
		@Override
		public void run()
		{
			_npc.teleToLocation(_loc, false);
		}
	}
	
	public static void main(String[] args)
	{
		new RandomSpawn();
	}
}

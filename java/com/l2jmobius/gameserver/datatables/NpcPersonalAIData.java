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
package com.l2jmobius.gameserver.datatables;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.model.L2Spawn;
import com.l2jmobius.gameserver.model.actor.L2Npc;

/**
 * This class holds parameter, specific to certain NPCs.<br>
 * It can be either general parameters overridden for certain NPC instance instead of template parameters(aggro range, for example), or some optional parameters, handled by datapack scripts.<br>
 * @author GKR
 */
public class NpcPersonalAIData
{
	private final Map<String, Map<String, Integer>> _AIData = new HashMap<>();
	
	/**
	 * Instantiates a new table.
	 */
	protected NpcPersonalAIData()
	{
	}
	
	/**
	 * Stores data for given spawn.
	 * @param spawnDat spawn to process
	 * @param data Map of AI values
	 */
	public void storeData(L2Spawn spawnDat, Map<String, Integer> data)
	{
		if ((data != null) && !data.isEmpty())
		{
			// check for spawn name. Since spawn name is key for AI Data, generate random name, if spawn name isn't specified
			if (spawnDat.getName() == null)
			{
				spawnDat.setName(Long.toString(Rnd.nextLong()));
			}
			
			_AIData.put(spawnDat.getName(), data);
		}
	}
	
	/**
	 * Gets AI value with given spawnName and paramName
	 * @param spawnName spawn name to check
	 * @param paramName parameter to check
	 * @return value of given parameter for given spawn name
	 */
	public int getAIValue(String spawnName, String paramName)
	{
		return hasAIValue(spawnName, paramName) ? _AIData.get(spawnName).get(paramName) : -1;
	}
	
	/**
	 * Verifies if there is AI value with given spawnName and paramName
	 * @param spawnName spawn name to check
	 * @param paramName parameter name to check
	 * @return {@code true} if parameter paramName is set for spawn spawnName, {@code false} otherwise
	 */
	public boolean hasAIValue(String spawnName, String paramName)
	{
		return (spawnName != null) && _AIData.containsKey(spawnName) && _AIData.get(spawnName).containsKey(paramName);
	}
	
	/**
	 * Initializes npc parameters by specified values.
	 * @param npc NPC to process
	 * @param spawn link to NPC's spawn
	 * @param spawnName name of spawn
	 */
	public void initializeNpcParameters(L2Npc npc, L2Spawn spawn, String spawnName)
	{
		if (_AIData.containsKey(spawnName))
		{
			final Map<String, Integer> map = _AIData.get(spawnName);
			
			try
			{
				for (String key : map.keySet())
				{
					switch (key)
					{
						case "disableRandomAnimation":
						{
							npc.setRandomAnimationEnabled((map.get(key) == 0));
							break;
						}
						case "disableRandomWalk":
						{
							npc.setRandomWalking((map.get(key) == 0));
							spawn.setRandomWalking((map.get(key) == 0));
							break;
						}
					}
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}
	
	/**
	 * Gets the single instance of NpcTable.
	 * @return single instance of NpcTable
	 */
	public static NpcPersonalAIData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final NpcPersonalAIData _instance = new NpcPersonalAIData();
	}
}
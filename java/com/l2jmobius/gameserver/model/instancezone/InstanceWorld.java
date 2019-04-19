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
package com.l2jmobius.gameserver.model.instancezone;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

/**
 * Basic instance zone data transfer object.
 * @author Zoey76
 */
public class InstanceWorld
{
	private Instance _instance;
	private final Set<L2PcInstance> _players = ConcurrentHashMap.newKeySet();
	private final StatsSet _parameters = new StatsSet();
	
	/**
	 * Sets the instance.
	 * @param instance the instance
	 */
	public void setInstance(Instance instance)
	{
		_instance = instance;
	}
	
	/**
	 * Gets the dynamically generated instance ID.
	 * @return the instance ID
	 */
	public int getInstanceId()
	{
		return _instance.getId();
	}
	
	/**
	 * Get template ID of instance world.
	 * @return instance template ID
	 */
	public int getTemplateId()
	{
		return _instance.getTemplateId();
	}
	
	public Set<L2PcInstance> getAllowed()
	{
		return _players;
	}
	
	public void removeAllowed(L2PcInstance player)
	{
		_players.remove(player);
	}
	
	public void addAllowed(L2PcInstance player)
	{
		if (!_players.contains(player))
		{
			_players.add(player);
		}
	}
	
	public boolean isAllowed(L2PcInstance player)
	{
		return _players.contains(player);
	}
	
	/**
	 * Set instance world parameter.
	 * @param key parameter name
	 * @param val parameter value
	 */
	public void setParameter(String key, Object val)
	{
		if (val == null)
		{
			_parameters.remove(key);
		}
		else
		{
			_parameters.set(key, val);
		}
	}
	
	/**
	 * Get instance world parameters.
	 * @return instance parameters
	 */
	public StatsSet getParameters()
	{
		return _parameters;
	}
	
	/**
	 * Get status of instance world.
	 * @return instance status, otherwise 0
	 */
	public int getStatus()
	{
		return _parameters.getInt("INSTANCE_STATUS", 0);
	}
	
	/**
	 * Check if instance status is equal to {@code status}.
	 * @param status number used for status comparison
	 * @return {@code true} when instance status and {@code status} are equal, otherwise {@code false}
	 */
	public boolean isStatus(int status)
	{
		return getStatus() == status;
	}
	
	/**
	 * Set status of instance world.
	 * @param value new world status
	 */
	public void setStatus(int value)
	{
		_parameters.set("INSTANCE_STATUS", value);
	}
	
	/**
	 * Increment instance world status
	 * @return new world status
	 */
	public int incStatus()
	{
		final int status = getStatus() + 1;
		setStatus(status);
		return status;
	}
	
	/**
	 * Get spawned NPCs from instance.
	 * @return set of NPCs from instance
	 */
	public List<L2Npc> getNpcs()
	{
		return _instance.getNpcs();
	}
	
	/**
	 * Get alive NPCs from instance.
	 * @return set of NPCs from instance
	 */
	public List<L2Npc> getAliveNpcs()
	{
		return _instance.getNpcs().stream().filter(n -> n.getCurrentHp() > 0).collect(Collectors.toList());
	}
	
	/**
	 * Get spawned NPCs from instance with specific IDs.
	 * @param id IDs of NPCs which should be found
	 * @return list of filtered NPCs from instance
	 */
	public List<L2Npc> getNpcs(int... id)
	{
		return _instance.getNpcs().stream().filter(n -> CommonUtil.contains(id, n.getId())).collect(Collectors.toList());
	}
	
	/**
	 * Get spawned NPCs from instance with specific IDs and class type.
	 * @param <T>
	 * @param clazz
	 * @param ids IDs of NPCs which should be found
	 * @return list of filtered NPCs from instance
	 */
	@SafeVarargs
	public final <T extends L2Character> List<T> getNpcs(Class<T> clazz, int... ids)
	{
		return _instance.getNpcs().stream().filter(n -> (ids.length == 0) || CommonUtil.contains(ids, n.getId())).filter(clazz::isInstance).map(clazz::cast).collect(Collectors.toList());
	}
	
	/**
	 * Get spawned and alive NPCs from instance with specific IDs and class type.
	 * @param <T>
	 * @param clazz
	 * @param ids IDs of NPCs which should be found
	 * @return list of filtered NPCs from instance
	 */
	@SafeVarargs
	public final <T extends L2Character> List<T> getAliveNpcs(Class<T> clazz, int... ids)
	{
		return _instance.getNpcs().stream().filter(n -> ((ids.length == 0) || CommonUtil.contains(ids, n.getId())) && (n.getCurrentHp() > 0)).filter(clazz::isInstance).map(clazz::cast).collect(Collectors.toList());
	}
	
	/**
	 * Get alive NPCs from instance with specific IDs.
	 * @param id IDs of NPCs which should be found
	 * @return list of filtered NPCs from instance
	 */
	public List<L2Npc> getAliveNpcs(int... id)
	{
		return _instance.getNpcs().stream().filter(n -> (n.getCurrentHp() > 0) && CommonUtil.contains(id, n.getId())).collect(Collectors.toList());
	}
	
	/**
	 * Get first found spawned NPC with specific ID.
	 * @param id ID of NPC to be found
	 * @return first found NPC with specified ID, otherwise {@code null}
	 */
	public L2Npc getNpc(int id)
	{
		return _instance.getNpcs().stream().filter(n -> n.getId() == id).findFirst().orElse(null);
	}
	
	/**
	 * Spawns group of instance NPCs
	 * @param groupName the name of group from XML definition to spawn
	 * @return list of spawned NPCs
	 */
	public List<L2Npc> spawnGroup(String groupName)
	{
		return _instance.spawnGroup(groupName);
	}
	
	/**
	 * Open a door if it is present in the instance and its not open.
	 * @param doorId the ID of the door to open
	 */
	public void openDoor(int doorId)
	{
		final L2DoorInstance door = _instance.getDoor(doorId);
		if ((door != null) && !door.isOpen())
		{
			door.openMe();
		}
	}
	
	/**
	 * Close a door if it is present in the instance and its open.
	 * @param doorId the ID of the door to close
	 */
	public void closeDoor(int doorId)
	{
		final L2DoorInstance door = _instance.getDoor(doorId);
		if ((door != null) && door.isOpen())
		{
			door.closeMe();
		}
	}
}

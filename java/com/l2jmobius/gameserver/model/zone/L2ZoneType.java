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
package com.l2jmobius.gameserver.model.zone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.events.EventDispatcher;
import com.l2jmobius.gameserver.model.events.ListenersContainer;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureZoneEnter;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureZoneExit;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.interfaces.ILocational;
import com.l2jmobius.gameserver.network.serverpackets.IClientOutgoingPacket;

/**
 * Abstract base class for any zone type handles basic operations.
 * @author durgus
 */
public abstract class L2ZoneType extends ListenersContainer
{
	protected static final Logger LOGGER = Logger.getLogger(L2ZoneType.class.getName());
	
	private final int _id;
	protected L2ZoneForm _zone;
	protected Map<Integer, L2Character> _characterList = new ConcurrentHashMap<>();
	
	/** Parameters to affect specific characters */
	private boolean _checkAffected = false;
	private String _name = null;
	private int _minLvl;
	private int _maxLvl;
	private int[] _race;
	private int[] _class;
	private char _classType;
	private InstanceType _target = InstanceType.L2Character; // default all chars
	private boolean _allowStore;
	protected boolean _enabled;
	private AbstractZoneSettings _settings;
	private int _instanceTemplateId = -1;
	private int _instanceId = -1;
	
	protected L2ZoneType(int id)
	{
		_id = id;
		
		_minLvl = 0;
		_maxLvl = 0xFF;
		
		_classType = 0;
		
		_race = null;
		_class = null;
		_allowStore = true;
		_enabled = true;
	}
	
	/**
	 * @return Returns the id.
	 */
	public int getId()
	{
		return _id;
	}
	
	/**
	 * Setup new parameters for this zone
	 * @param name
	 * @param value
	 */
	public void setParameter(String name, String value)
	{
		_checkAffected = true;
		
		// Zone name
		if (name.equals("name"))
		{
			_name = value;
		}
		// Minimum level
		else if (name.equals("affectedLvlMin"))
		{
			_minLvl = Integer.parseInt(value);
		}
		// Maximum level
		else if (name.equals("affectedLvlMax"))
		{
			_maxLvl = Integer.parseInt(value);
		}
		// Affected Races
		else if (name.equals("affectedRace"))
		{
			// Create a new array holding the affected race
			if (_race == null)
			{
				_race = new int[1];
				_race[0] = Integer.parseInt(value);
			}
			else
			{
				final int[] temp = new int[_race.length + 1];
				
				int i = 0;
				for (; i < _race.length; i++)
				{
					temp[i] = _race[i];
				}
				
				temp[i] = Integer.parseInt(value);
				
				_race = temp;
			}
		}
		// Affected classes
		else if (name.equals("affectedClassId"))
		{
			// Create a new array holding the affected classIds
			if (_class == null)
			{
				_class = new int[1];
				_class[0] = Integer.parseInt(value);
			}
			else
			{
				final int[] temp = new int[_class.length + 1];
				
				int i = 0;
				for (; i < _class.length; i++)
				{
					temp[i] = _class[i];
				}
				
				temp[i] = Integer.parseInt(value);
				
				_class = temp;
			}
		}
		// Affected class type
		else if (name.equals("affectedClassType"))
		{
			if (value.equals("Fighter"))
			{
				_classType = 1;
			}
			else
			{
				_classType = 2;
			}
		}
		else if (name.equals("targetClass"))
		{
			_target = Enum.valueOf(InstanceType.class, value);
		}
		else if (name.equals("allowStore"))
		{
			_allowStore = Boolean.parseBoolean(value);
		}
		else if (name.equals("default_enabled"))
		{
			_enabled = Boolean.parseBoolean(value);
		}
		else if (name.equals("instanceId"))
		{
			_instanceTemplateId = Integer.parseInt(value);
		}
		else
		{
			LOGGER.info(getClass().getSimpleName() + ": Unknown parameter - " + name + " in zone: " + _id);
		}
	}
	
	/**
	 * @param character the player to verify.
	 * @return {@code true} if the given character is affected by this zone, {@code false} otherwise.
	 */
	private boolean isAffected(L2Character character)
	{
		// Check instance
		if (_instanceTemplateId > 0)
		{
			final InstanceWorld world = InstanceManager.getInstance().getWorld(character);
			if ((world != null) && (world.getTemplateId() != _instanceTemplateId))
			{
				return false;
			}
		}
		
		// Check lvl
		if ((character.getLevel() < _minLvl) || (character.getLevel() > _maxLvl))
		{
			return false;
		}
		
		// check obj class
		if (!character.isInstanceTypes(_target))
		{
			return false;
		}
		
		if (character.isPlayer())
		{
			// Check class type
			if (_classType != 0)
			{
				if (((L2PcInstance) character).isMageClass())
				{
					if (_classType == 1)
					{
						return false;
					}
				}
				else if (_classType == 2)
				{
					return false;
				}
			}
			
			// Check race
			if (_race != null)
			{
				boolean ok = false;
				
				for (int element : _race)
				{
					if (character.getRace().ordinal() == element)
					{
						ok = true;
						break;
					}
				}
				
				if (!ok)
				{
					return false;
				}
			}
			
			// Check class
			if (_class != null)
			{
				boolean ok = false;
				
				for (int _clas : _class)
				{
					if (((L2PcInstance) character).getClassId().ordinal() == _clas)
					{
						ok = true;
						break;
					}
				}
				
				if (!ok)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Set the zone for this L2ZoneType Instance
	 * @param zone
	 */
	public void setZone(L2ZoneForm zone)
	{
		if (_zone != null)
		{
			throw new IllegalStateException("Zone already set");
		}
		_zone = zone;
	}
	
	/**
	 * Returns this zones zone form.
	 * @return {@link #_zone}
	 */
	public L2ZoneForm getZone()
	{
		return _zone;
	}
	
	/**
	 * Set the zone name.
	 * @param name
	 */
	public void setName(String name)
	{
		_name = name;
	}
	
	/**
	 * Returns zone name
	 * @return
	 */
	public String getName()
	{
		return _name;
	}
	
	/**
	 * Set the zone instanceId.
	 * @param instanceId
	 */
	public void setInstanceId(int instanceId)
	{
		_instanceId = instanceId;
	}
	
	/**
	 * Returns zone instanceId
	 * @return
	 */
	public int getInstanceId()
	{
		return _instanceId;
	}
	
	/**
	 * Set the zone instance Template Id .
	 * @param instanceTemplateId
	 */
	public void setInstanceTemplateId(int instanceTemplateId)
	{
		_instanceTemplateId = instanceTemplateId;
	}
	
	/**
	 * Returns zone instance TemplateId
	 * @return
	 */
	public int getInstanceTemplateId()
	{
		return _instanceTemplateId;
	}
	
	/**
	 * Checks if the given coordinates are within zone's plane
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInsideZone(int x, int y)
	{
		return isInsideZone(x, y, _zone.getHighZ());
	}
	
	/**
	 * Checks if the given coordinates are within the zone, ignores instanceId check
	 * @param loc
	 * @return
	 */
	public boolean isInsideZone(ILocational loc)
	{
		return isInsideZone(loc.getX(), loc.getY(), loc.getZ());
	}
	
	/**
	 * Checks if the given coordinates are within the zone, ignores instanceId check
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public boolean isInsideZone(int x, int y, int z)
	{
		return _zone.isInsideZone(x, y, z);
	}
	
	public double getDistanceToZone(int x, int y)
	{
		return _zone.getDistanceToZone(x, y);
	}
	
	public double getDistanceToZone(L2Object object)
	{
		return _zone.getDistanceToZone(object.getX(), object.getY());
	}
	
	public void revalidateInZone(L2Character character)
	{
		// If the object is inside the zone...
		if (isInsideZone(character))
		{
			// If the character can't be affected by this zone return
			if (_checkAffected && !isAffected(character))
			{
				return;
			}
			
			if (_characterList.putIfAbsent(character.getObjectId(), character) == null)
			{
				// Notify to scripts.
				EventDispatcher.getInstance().notifyEventAsync(new OnCreatureZoneEnter(character, this), this);
				// Notify Zone implementation.
				onEnter(character);
			}
		}
		else
		{
			removeCharacter(character);
		}
	}
	
	/**
	 * Force fully removes a character from the zone Should use during teleport / logoff
	 * @param character
	 */
	public void removeCharacter(L2Character character)
	{
		// Was the character inside this zone?
		if (_characterList.containsKey(character.getObjectId()))
		{
			// Notify to scripts.
			EventDispatcher.getInstance().notifyEventAsync(new OnCreatureZoneExit(character, this), this);
			
			// Unregister player.
			_characterList.remove(character.getObjectId());
			
			// Notify Zone implementation.
			onExit(character);
		}
	}
	
	/**
	 * Will scan the zones char list for the character
	 * @param character
	 * @return
	 */
	public boolean isCharacterInZone(L2Character character)
	{
		return _characterList.containsKey(character.getObjectId());
	}
	
	public AbstractZoneSettings getSettings()
	{
		return _settings;
	}
	
	public void setSettings(AbstractZoneSettings settings)
	{
		if (_settings != null)
		{
			_settings.clear();
		}
		_settings = settings;
	}
	
	protected abstract void onEnter(L2Character character);
	
	protected abstract void onExit(L2Character character);
	
	public void onDieInside(L2Character character)
	{
	}
	
	public void onReviveInside(L2Character character)
	{
	}
	
	public void onPlayerLoginInside(L2PcInstance player)
	{
	}
	
	public void onPlayerLogoutInside(L2PcInstance player)
	{
	}
	
	public Map<Integer, L2Character> getCharacters()
	{
		return _characterList;
	}
	
	public Collection<L2Character> getCharactersInside()
	{
		return _characterList.values();
	}
	
	public List<L2PcInstance> getPlayersInside()
	{
		final List<L2PcInstance> players = new ArrayList<>();
		for (L2Character ch : _characterList.values())
		{
			if ((ch != null) && ch.isPlayer())
			{
				players.add(ch.getActingPlayer());
			}
		}
		
		return players;
	}
	
	/**
	 * Broadcasts packet to all players inside the zone
	 * @param packet
	 */
	public void broadcastPacket(IClientOutgoingPacket packet)
	{
		if (_characterList.isEmpty())
		{
			return;
		}
		
		for (L2Character character : _characterList.values())
		{
			if ((character != null) && character.isPlayer())
			{
				character.sendPacket(packet);
			}
		}
	}
	
	public InstanceType getTargetType()
	{
		return _target;
	}
	
	public void setTargetType(InstanceType type)
	{
		_target = type;
		_checkAffected = true;
	}
	
	public boolean getAllowStore()
	{
		return _allowStore;
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "[" + _id + "]";
	}
	
	public void visualizeZone(int z)
	{
		_zone.visualizeZone(z);
	}
	
	public void setEnabled(boolean state)
	{
		_enabled = state;
	}
	
	public boolean isEnabled()
	{
		return _enabled;
	}
}
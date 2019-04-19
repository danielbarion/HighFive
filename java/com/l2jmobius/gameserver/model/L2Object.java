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
package com.l2jmobius.gameserver.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.handler.ActionHandler;
import com.l2jmobius.gameserver.handler.ActionShiftHandler;
import com.l2jmobius.gameserver.handler.IActionHandler;
import com.l2jmobius.gameserver.handler.IActionShiftHandler;
import com.l2jmobius.gameserver.idfactory.IdFactory;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.events.ListenersContainer;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.interfaces.IDecayable;
import com.l2jmobius.gameserver.model.interfaces.IIdentifiable;
import com.l2jmobius.gameserver.model.interfaces.ILocational;
import com.l2jmobius.gameserver.model.interfaces.INamable;
import com.l2jmobius.gameserver.model.interfaces.IPositionable;
import com.l2jmobius.gameserver.model.interfaces.ISpawnable;
import com.l2jmobius.gameserver.model.interfaces.IUniqueId;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import com.l2jmobius.gameserver.network.serverpackets.DeleteObject;
import com.l2jmobius.gameserver.network.serverpackets.ExSendUIEvent;
import com.l2jmobius.gameserver.network.serverpackets.IClientOutgoingPacket;
import com.l2jmobius.gameserver.util.Util;

/**
 * Base class for all interactive objects.
 */
public abstract class L2Object extends ListenersContainer implements IIdentifiable, INamable, ISpawnable, IUniqueId, IDecayable, IPositionable
{
	/** Name */
	private String _name;
	/** Object ID */
	private int _objectId;
	/** World Region */
	private L2WorldRegion _worldRegion;
	/** Instance type */
	private InstanceType _instanceType;
	private volatile Map<String, Object> _scripts;
	/** X coordinate */
	private volatile int _x = 0;
	/** Y coordinate */
	private volatile int _y = 0;
	/** Z coordinate */
	private volatile int _z = 0;
	/** Orientation */
	private volatile int _heading = 0;
	/** Instance id of object. 0 - Global */
	private volatile int _instanceId = 0;
	private boolean _isSpawned;
	private boolean _isInvisible;
	
	public L2Object(int objectId)
	{
		setInstanceType(InstanceType.L2Object);
		_objectId = objectId;
	}
	
	/**
	 * Gets the instance type of object.
	 * @return the instance type
	 */
	public final InstanceType getInstanceType()
	{
		return _instanceType;
	}
	
	/**
	 * Sets the instance type.
	 * @param newInstanceType the instance type to set
	 */
	protected final void setInstanceType(InstanceType newInstanceType)
	{
		_instanceType = newInstanceType;
	}
	
	/**
	 * Verifies if object is of any given instance types.
	 * @param instanceTypes the instance types to verify
	 * @return {@code true} if object is of any given instance types, {@code false} otherwise
	 */
	public final boolean isInstanceTypes(InstanceType... instanceTypes)
	{
		return _instanceType.isTypes(instanceTypes);
	}
	
	public final void onAction(L2PcInstance player)
	{
		onAction(player, true);
	}
	
	public void onAction(L2PcInstance player, boolean interact)
	{
		final IActionHandler handler = ActionHandler.getInstance().getHandler(getInstanceType());
		if (handler != null)
		{
			handler.action(player, this, interact);
		}
		
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	public void onActionShift(L2PcInstance player)
	{
		final IActionShiftHandler handler = ActionShiftHandler.getInstance().getHandler(getInstanceType());
		if (handler != null)
		{
			handler.action(player, this, true);
		}
		
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	public void onForcedAttack(L2PcInstance player)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	public void onSpawn()
	{
		broadcastInfo(); // Tempfix for invisible spawns.
	}
	
	@Override
	public boolean decayMe()
	{
		_isSpawned = false;
		L2World.getInstance().removeVisibleObject(this, _worldRegion);
		L2World.getInstance().removeObject(this);
		return true;
	}
	
	public void refreshID()
	{
		L2World.getInstance().removeObject(this);
		IdFactory.getInstance().releaseId(getObjectId());
		_objectId = IdFactory.getInstance().getNextId();
	}
	
	@Override
	public final boolean spawnMe()
	{
		synchronized (this)
		{
			// Set the x,y,z position of the L2Object spawn and update its _worldregion
			_isSpawned = true;
			setWorldRegion(L2World.getInstance().getRegion(this));
			
			// Add the L2Object spawn in the _allobjects of L2World
			L2World.getInstance().addObject(this);
			
			// Add the L2Object spawn to _visibleObjects and if necessary to _allplayers of its L2WorldRegion
			_worldRegion.addVisibleObject(this);
		}
		
		// this can synchronize on others instances, so it's out of synchronized, to avoid deadlocks
		// Add the L2Object spawn in the world as a visible object
		L2World.getInstance().addVisibleObject(this, getWorldRegion());
		
		onSpawn();
		
		return true;
	}
	
	public final void spawnMe(int x, int y, int z)
	{
		synchronized (this)
		{
			if (x > L2World.MAP_MAX_X)
			{
				x = L2World.MAP_MAX_X - 5000;
			}
			if (x < L2World.MAP_MIN_X)
			{
				x = L2World.MAP_MIN_X + 5000;
			}
			if (y > L2World.MAP_MAX_Y)
			{
				y = L2World.MAP_MAX_Y - 5000;
			}
			if (y < L2World.MAP_MIN_Y)
			{
				y = L2World.MAP_MIN_Y + 5000;
			}
			
			// Set the x,y,z position of the WorldObject. If flagged with _isSpawned, setXYZ will automatically update world region, so avoid that.
			setXYZ(x, y, z);
		}
		
		// Spawn and update its _worldregion
		spawnMe();
	}
	
	/**
	 * Verify if object can be attacked.
	 * @return {@code true} if object can be attacked, {@code false} otherwise
	 */
	public boolean canBeAttacked()
	{
		return false;
	}
	
	public abstract boolean isAutoAttackable(L2Character attacker);
	
	public final boolean isSpawned()
	{
		return _isSpawned;
	}
	
	public final void setSpawned(boolean value)
	{
		_isSpawned = value;
	}
	
	@Override
	public String getName()
	{
		return _name;
	}
	
	public void setName(String value)
	{
		_name = value;
	}
	
	@Override
	public final int getObjectId()
	{
		return _objectId;
	}
	
	public abstract void sendInfo(L2PcInstance activeChar);
	
	public void sendPacket(IClientOutgoingPacket... packets)
	{
	}
	
	public void sendPacket(SystemMessageId id)
	{
	}
	
	public L2PcInstance getActingPlayer()
	{
		return null;
	}
	
	/**
	 * Verify if object is instance of L2Attackable.
	 * @return {@code true} if object is instance of L2Attackable, {@code false} otherwise
	 */
	public boolean isAttackable()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2Character.
	 * @return {@code true} if object is instance of L2Character, {@code false} otherwise
	 */
	public boolean isCharacter()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2DoorInstance.
	 * @return {@code true} if object is instance of L2DoorInstance, {@code false} otherwise
	 */
	public boolean isDoor()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2MonsterInstance.
	 * @return {@code true} if object is instance of L2MonsterInstance, {@code false} otherwise
	 */
	public boolean isMonster()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2Npc.
	 * @return {@code true} if object is instance of L2Npc, {@code false} otherwise
	 */
	public boolean isNpc()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2PetInstance.
	 * @return {@code true} if object is instance of L2PetInstance, {@code false} otherwise
	 */
	public boolean isPet()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2PcInstance.
	 * @return {@code true} if object is instance of L2PcInstance, {@code false} otherwise
	 */
	public boolean isPlayer()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2Playable.
	 * @return {@code true} if object is instance of L2Playable, {@code false} otherwise
	 */
	public boolean isPlayable()
	{
		return false;
	}
	
	/**
	 * Verify if object is a fake player.
	 * @return {@code true} if object is a fake player, {@code false} otherwise
	 */
	public boolean isFakePlayer()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2ServitorInstance.
	 * @return {@code true} if object is instance of L2ServitorInstance, {@code false} otherwise
	 */
	public boolean isServitor()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2Summon.
	 * @return {@code true} if object is instance of L2Summon, {@code false} otherwise
	 */
	public boolean isSummon()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2TrapInstance.
	 * @return {@code true} if object is instance of L2TrapInstance, {@code false} otherwise
	 */
	public boolean isTrap()
	{
		return false;
	}
	
	/**
	 * Verify if object is instance of L2ItemInstance.
	 * @return {@code true} if object is instance of L2ItemInstance, {@code false} otherwise
	 */
	public boolean isItem()
	{
		return false;
	}
	
	/**
	 * Verifies if the object is a walker NPC.
	 * @return {@code true} if object is a walker NPC, {@code false} otherwise
	 */
	public boolean isWalker()
	{
		return false;
	}
	
	/**
	 * Verifies if this object is a vehicle.
	 * @return {@code true} if object is Vehicle, {@code false} otherwise
	 */
	public boolean isVehicle()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object Can be targeted
	 */
	public boolean isTargetable()
	{
		return true;
	}
	
	/**
	 * Check if the object is in the given zone Id.
	 * @param zone the zone Id to check
	 * @return {@code true} if the object is in that zone Id
	 */
	public boolean isInsideZone(ZoneId zone)
	{
		return false;
	}
	
	/**
	 * Check if current object has charged shot.
	 * @param type of the shot to be checked.
	 * @return {@code true} if the object has charged shot
	 */
	public boolean isChargedShot(ShotType type)
	{
		return false;
	}
	
	/**
	 * Charging shot into the current object.
	 * @param type of the shot to be charged.
	 * @param charged
	 */
	public void setChargedShot(ShotType type, boolean charged)
	{
	}
	
	/**
	 * Try to recharge a shot.
	 * @param physical skill are using Soul shots.
	 * @param magical skill are using Spirit shots.
	 */
	public void rechargeShots(boolean physical, boolean magical)
	{
	}
	
	/**
	 * @param <T>
	 * @param script
	 * @return
	 */
	public final <T> T addScript(T script)
	{
		if (_scripts == null)
		{
			// Double-checked locking
			synchronized (this)
			{
				if (_scripts == null)
				{
					_scripts = new ConcurrentHashMap<>();
				}
			}
		}
		_scripts.put(script.getClass().getName(), script);
		return script;
	}
	
	/**
	 * @param <T>
	 * @param script
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final <T> T removeScript(Class<T> script)
	{
		if (_scripts == null)
		{
			return null;
		}
		return (T) _scripts.remove(script.getName());
	}
	
	/**
	 * @param <T>
	 * @param script
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final <T> T getScript(Class<T> script)
	{
		if (_scripts == null)
		{
			return null;
		}
		return (T) _scripts.get(script.getName());
	}
	
	public void removeStatusListener(L2Character object)
	{
		
	}
	
	public final void setXYZInvisible(int x, int y, int z)
	{
		if (x > L2World.MAP_MAX_X)
		{
			x = L2World.MAP_MAX_X - 5000;
		}
		if (x < L2World.MAP_MIN_X)
		{
			x = L2World.MAP_MIN_X + 5000;
		}
		if (y > L2World.MAP_MAX_Y)
		{
			y = L2World.MAP_MAX_Y - 5000;
		}
		if (y < L2World.MAP_MIN_Y)
		{
			y = L2World.MAP_MIN_Y + 5000;
		}
		
		setXYZ(x, y, z);
		setSpawned(false);
	}
	
	public final void setLocationInvisible(ILocational loc)
	{
		setXYZInvisible(loc.getX(), loc.getY(), loc.getZ());
	}
	
	public final L2WorldRegion getWorldRegion()
	{
		return _worldRegion;
	}
	
	public void setWorldRegion(L2WorldRegion value)
	{
		_worldRegion = value;
	}
	
	/**
	 * Gets the X coordinate.
	 * @return the X coordinate
	 */
	@Override
	public int getX()
	{
		return _x;
	}
	
	/**
	 * Gets the Y coordinate.
	 * @return the Y coordinate
	 */
	@Override
	public int getY()
	{
		return _y;
	}
	
	/**
	 * Gets the Z coordinate.
	 * @return the Z coordinate
	 */
	@Override
	public int getZ()
	{
		return _z;
	}
	
	/**
	 * Gets the heading.
	 * @return the heading
	 */
	@Override
	public int getHeading()
	{
		return _heading;
	}
	
	/**
	 * Gets the instance ID.
	 * @return the instance ID
	 */
	@Override
	public int getInstanceId()
	{
		return _instanceId;
	}
	
	/**
	 * Gets the location object.
	 * @return the location object
	 */
	@Override
	public Location getLocation()
	{
		return new Location(_x, _y, _z, _heading, _instanceId);
	}
	
	/**
	 * Sets the x, y, z coordinate.
	 * @param newX the X coordinate
	 * @param newY the Y coordinate
	 * @param newZ the Z coordinate
	 */
	@Override
	public void setXYZ(int newX, int newY, int newZ)
	{
		_x = newX;
		_y = newY;
		_z = newZ;
		
		if (_isSpawned)
		{
			final L2WorldRegion newRegion = L2World.getInstance().getRegion(this);
			if ((newRegion != null) && (newRegion != _worldRegion))
			{
				if (_worldRegion != null)
				{
					_worldRegion.removeVisibleObject(this);
				}
				newRegion.addVisibleObject(this);
				L2World.getInstance().switchRegion(this, newRegion);
				setWorldRegion(newRegion);
			}
		}
	}
	
	/**
	 * Sets the x, y, z coordinate.
	 * @param loc the location object
	 */
	@Override
	public void setXYZ(ILocational loc)
	{
		setXYZ(loc.getX(), loc.getY(), loc.getZ());
	}
	
	/**
	 * Sets heading of object.
	 * @param newHeading the new heading
	 */
	@Override
	public void setHeading(int newHeading)
	{
		_heading = newHeading;
	}
	
	/**
	 * Sets the instance ID of object.<br>
	 * 0 - Global<br>
	 * @param instanceId the ID of the instance
	 */
	@Override
	public void setInstanceId(int instanceId)
	{
		if ((instanceId < 0) || (_instanceId == instanceId))
		{
			return;
		}
		
		final Instance oldI = InstanceManager.getInstance().getInstance(getInstanceId());
		final Instance newI = InstanceManager.getInstance().getInstance(instanceId);
		if (newI == null)
		{
			return;
		}
		
		if (isPlayer())
		{
			final L2PcInstance player = getActingPlayer();
			if ((_instanceId > 0) && (oldI != null))
			{
				oldI.removePlayer(_objectId);
				if (oldI.isShowTimer())
				{
					sendInstanceUpdate(oldI, true);
				}
			}
			if (instanceId > 0)
			{
				newI.addPlayer(_objectId);
				if (newI.isShowTimer())
				{
					sendInstanceUpdate(newI, false);
				}
			}
			if (player.hasSummon())
			{
				player.getSummon().setInstanceId(instanceId);
			}
		}
		else if (isNpc())
		{
			final L2Npc npc = (L2Npc) this;
			if ((_instanceId > 0) && (oldI != null))
			{
				oldI.removeNpc(npc);
			}
			if (instanceId > 0)
			{
				newI.addNpc(npc);
			}
		}
		
		_instanceId = instanceId;
	}
	
	/**
	 * Sends an instance update for player.
	 * @param instance the instance to update
	 * @param hide if {@code true} hide the player
	 */
	private final void sendInstanceUpdate(Instance instance, boolean hide)
	{
		final int startTime = (int) ((System.currentTimeMillis() - instance.getInstanceStartTime()) / 1000);
		final int endTime = (int) ((instance.getInstanceEndTime() - instance.getInstanceStartTime()) / 1000);
		if (instance.isTimerIncrease())
		{
			sendPacket(new ExSendUIEvent(getActingPlayer(), hide, true, startTime, endTime, instance.getTimerText()));
		}
		else
		{
			sendPacket(new ExSendUIEvent(getActingPlayer(), hide, false, endTime - startTime, 0, instance.getTimerText()));
		}
	}
	
	/**
	 * Sets location of object.
	 * @param loc the location object
	 */
	@Override
	public void setLocation(Location loc)
	{
		_x = loc.getX();
		_y = loc.getY();
		_z = loc.getZ();
		_heading = loc.getHeading();
		_instanceId = loc.getInstanceId();
	}
	
	/**
	 * Calculates 2D distance between this L2Object and given x, y, z.
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 * @return distance between object and given x, y, z.
	 */
	public double calculateDistance2D(int x, int y, int z)
	{
		return Math.sqrt(Math.pow(x - _x, 2) + Math.pow(y - _y, 2));
	}
	
	/**
	 * Calculates the 2D distance between this L2Object and given location.
	 * @param loc the location object
	 * @return distance between object and given location.
	 */
	public double calculateDistance2D(ILocational loc)
	{
		return calculateDistance2D(loc.getX(), loc.getY(), loc.getZ());
	}
	
	/**
	 * Calculates the 3D distance between this L2Object and given x, y, z.
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 * @return distance between object and given x, y, z.
	 */
	public double calculateDistance3D(int x, int y, int z)
	{
		return Math.sqrt(Math.pow(x - _x, 2) + Math.pow(y - _y, 2) + Math.pow(z - _z, 2));
	}
	
	/**
	 * Calculates 3D distance between this L2Object and given location.
	 * @param loc the location object
	 * @return distance between object and given location.
	 */
	public double calculateDistance3D(ILocational loc)
	{
		return calculateDistance3D(loc.getX(), loc.getY(), loc.getZ());
	}
	
	/**
	 * Calculates the non squared 2D distance between this L2Object and given x, y, z.
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 * @return distance between object and given x, y, z.
	 */
	public double calculateDistanceSq2D(int x, int y, int z)
	{
		return Math.pow(x - _x, 2) + Math.pow(y - _y, 2);
	}
	
	/**
	 * Calculates the non squared 2D distance between this L2Object and given location.
	 * @param loc the location object
	 * @return distance between object and given location.
	 */
	public double calculateDistanceSq2D(ILocational loc)
	{
		return calculateDistanceSq2D(loc.getX(), loc.getY(), loc.getZ());
	}
	
	/**
	 * Calculates the non squared 3D distance between this L2Object and given x, y, z.
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 * @return distance between object and given x, y, z.
	 */
	public double calculateDistanceSq3D(int x, int y, int z)
	{
		return Math.pow(x - _x, 2) + Math.pow(y - _y, 2) + Math.pow(z - _z, 2);
	}
	
	/**
	 * Calculates the non squared 3D distance between this L2Object and given location.
	 * @param loc the location object
	 * @return distance between object and given location.
	 */
	public double calculateDistanceSq3D(ILocational loc)
	{
		return calculateDistanceSq3D(loc.getX(), loc.getY(), loc.getZ());
	}
	
	/**
	 * Calculates the angle in degrees from this object to the given object.<br>
	 * The return value can be described as how much this object has to turn<br>
	 * to have the given object directly in front of it.
	 * @param target the object to which to calculate the angle
	 * @return the angle this object has to turn to have the given object in front of it
	 */
	public double calculateDirectionTo(ILocational target)
	{
		int heading = Util.calculateHeadingFrom(this, target) - _heading;
		if (heading < 0)
		{
			heading += 65535;
		}
		return Util.convertHeadingToDegree(heading);
	}
	
	/**
	 * @return {@code true} if this object is invisible, {@code false} otherwise.
	 */
	public boolean isInvisible()
	{
		return _isInvisible;
	}
	
	/**
	 * Sets this object as invisible or not
	 * @param invis
	 */
	public void setInvisible(boolean invis)
	{
		_isInvisible = invis;
		if (invis)
		{
			final DeleteObject deletePacket = new DeleteObject(this);
			L2World.getInstance().forEachVisibleObject(this, L2PcInstance.class, player ->
			{
				if (!isVisibleFor(player))
				{
					player.sendPacket(deletePacket);
				}
			});
		}
		
		// Broadcast information regarding the object to those which are suppose to see.
		broadcastInfo();
	}
	
	/**
	 * @param player
	 * @return {@code true} if player can see an invisible object if it's invisible, {@code false} otherwise.
	 */
	public boolean isVisibleFor(L2PcInstance player)
	{
		return !_isInvisible || player.canOverrideCond(PcCondOverride.SEE_ALL_PLAYERS);
	}
	
	/**
	 * Broadcasts describing info to known players.
	 */
	public void broadcastInfo()
	{
		L2World.getInstance().forEachVisibleObject(this, L2PcInstance.class, player ->
		{
			if (isVisibleFor(player))
			{
				sendInfo(player);
			}
		});
	}
	
	public boolean isInSurroundingRegion(L2Object worldObject)
	{
		if (worldObject == null)
		{
			return false;
		}
		
		final L2WorldRegion worldRegion = worldObject.getWorldRegion();
		if (worldRegion == null)
		{
			return false;
		}
		
		if (_worldRegion == null)
		{
			return false;
		}
		
		return worldRegion.isSurroundingRegion(_worldRegion);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (obj instanceof L2Object) && (((L2Object) obj).getObjectId() == getObjectId());
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + ":" + _name + "[" + _objectId + "]";
	}
}
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

import java.lang.reflect.Constructor;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.data.xml.impl.NpcData;
import com.l2jmobius.gameserver.datatables.NpcPersonalAIData;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jmobius.gameserver.model.interfaces.IIdentifiable;
import com.l2jmobius.gameserver.model.interfaces.INamable;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.model.zone.type.NpcSpawnTerritory;

/**
 * This class manages the spawn and respawn of a group of L2NpcInstance that are in the same are and have the same type.<br>
 * <B><U>Concept</U>:</B><br>
 * L2NpcInstance can be spawned either in a random position into a location area (if Lox=0 and Locy=0), either at an exact position.<br>
 * The heading of the L2NpcInstance can be a random heading if not defined (value= -1) or an exact heading (ex : merchant...).
 * @author Nightmare
 */
public class L2Spawn extends Location implements IIdentifiable, INamable
{
	protected static final Logger LOGGER = Logger.getLogger(L2Spawn.class.getName());
	
	/** String identifier of this spawn */
	private String _name;
	/** The link on the L2NpcTemplate object containing generic and static properties of this spawn (ex : RewardExp, RewardSP, AggroRange...) */
	private L2NpcTemplate _template;
	/** The maximum number of L2NpcInstance that can manage this L2Spawn */
	private int _maximumCount;
	/** The current number of L2NpcInstance managed by this L2Spawn */
	private int _currentCount;
	/** The current number of SpawnTask in progress or stand by of this L2Spawn */
	protected int _scheduledCount;
	/** The identifier of the location area where L2NpcInstance can be spawned */
	private int _locationId;
	/** Link to NPC spawn territory */
	private NpcSpawnTerritory _spawnTerritory = null;
	/** Minimum respawn delay */
	private int _respawnMinDelay;
	/** Maximum respawn delay */
	private int _respawnMaxDelay;
	/** The generic constructor of L2NpcInstance managed by this L2Spawn */
	private Constructor<? extends L2Npc> _constructor;
	/** If True a L2NpcInstance is respawned each time that another is killed */
	private boolean _doRespawn = true;
	private static List<SpawnListener> _spawnListeners = new CopyOnWriteArrayList<>();
	private final Deque<L2Npc> _spawnedNpcs = new ConcurrentLinkedDeque<>();
	private boolean _randomWalk = false; // Is random walk
	private int _spawnTemplateId = 0;
	
	/** The task launching the function doSpawn() */
	class SpawnTask implements Runnable
	{
		public SpawnTask()
		{
		}
		
		@Override
		public void run()
		{
			try
			{
				doSpawn();
			}
			catch (Exception e)
			{
				LOGGER.log(Level.WARNING, "", e);
			}
			
			_scheduledCount--;
		}
	}
	
	/**
	 * Constructor of L2Spawn.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Each L2Spawn owns generic and static properties (ex : RewardExp, RewardSP, AggroRange...).<br>
	 * All of those properties are stored in a different L2NpcTemplate for each type of L2Spawn. Each template is loaded once in the server cache memory (reduce memory use).<br>
	 * When a new instance of L2Spawn is created, server just create a link between the instance and the template.<br>
	 * This link is stored in <B>_template</B> Each L2NpcInstance is linked to a L2Spawn that manages its spawn and respawn (delay, location...).<br>
	 * This link is stored in <B>_spawn</B> of the L2NpcInstance.<br>
	 * <B><U> Actions</U>:</B><br>
	 * <ul>
	 * <li>Set the _template of the L2Spawn</li>
	 * <li>Calculate the implementationName used to generate the generic constructor of L2NpcInstance managed by this L2Spawn</li>
	 * <li>Create the generic constructor of L2NpcInstance managed by this L2Spawn</li>
	 * </ul>
	 * @param template The L2NpcTemplate to link to this L2Spawn
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws ClassCastException when template type is not subclass of L2Npc
	 */
	public L2Spawn(L2NpcTemplate template) throws SecurityException, ClassNotFoundException, NoSuchMethodException, ClassCastException
	{
		super(0, 0, 0);
		// Set the _template of the L2Spawn
		_template = template;
		
		if (_template == null)
		{
			return;
		}
		
		_constructor = Class.forName("com.l2jmobius.gameserver.model.actor.instance." + _template.getType() + "Instance").asSubclass(L2Npc.class).getConstructor(L2NpcTemplate.class);
	}
	
	/**
	 * Creates a spawn.
	 * @param npcId the NPC ID
	 * @throws ClassCastException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 */
	public L2Spawn(int npcId) throws SecurityException, ClassNotFoundException, NoSuchMethodException, ClassCastException
	{
		this(NpcData.getInstance().getTemplate(npcId));
	}
	
	/**
	 * @return the maximum number of L2NpcInstance that this L2Spawn can manage.
	 */
	public int getAmount()
	{
		return _maximumCount;
	}
	
	/**
	 * @return the String Identifier of this spawn.
	 */
	@Override
	public String getName()
	{
		return _name;
	}
	
	/**
	 * Set the String Identifier of this spawn.
	 * @param name
	 */
	public void setName(String name)
	{
		_name = name;
	}
	
	/**
	 * @return the Identifier of the location area where L2NpcInstance can be spawned.
	 */
	public int getLocationId()
	{
		return _locationId;
	}
	
	/**
	 * Gets the NPC ID.
	 * @return the NPC ID
	 */
	@Override
	public int getId()
	{
		return _template.getId();
	}
	
	/**
	 * @return min respawn delay.
	 */
	public int getRespawnMinDelay()
	{
		return _respawnMinDelay;
	}
	
	/**
	 * @return max respawn delay.
	 */
	public int getRespawnMaxDelay()
	{
		return _respawnMaxDelay;
	}
	
	/**
	 * Set the maximum number of L2NpcInstance that this L2Spawn can manage.
	 * @param amount
	 */
	public void setAmount(int amount)
	{
		_maximumCount = amount;
	}
	
	/**
	 * Set the Identifier of the location area where L2NpcInstance can be spawned.
	 * @param id
	 */
	public void setLocationId(int id)
	{
		_locationId = id;
	}
	
	/**
	 * Set Minimum Respawn Delay.
	 * @param date
	 */
	public void setRespawnMinDelay(int date)
	{
		_respawnMinDelay = date;
	}
	
	/**
	 * Set Maximum Respawn Delay.
	 * @param date
	 */
	public void setRespawnMaxDelay(int date)
	{
		_respawnMaxDelay = date;
	}
	
	/**
	 * Decrease the current number of L2NpcInstance of this L2Spawn and if necessary create a SpawnTask to launch after the respawn Delay. <B><U> Actions</U> :</B>
	 * <li>Decrease the current number of L2NpcInstance of this L2Spawn</li>
	 * <li>Check if respawn is possible to prevent multiple respawning caused by lag</li>
	 * <li>Update the current number of SpawnTask in progress or stand by of this L2Spawn</li>
	 * <li>Create a new SpawnTask to launch after the respawn Delay</li> <FONT COLOR=#FF0000><B> <U>Caution</U> : A respawn is possible ONLY if _doRespawn=True and _scheduledCount + _currentCount < _maximumCount</B></FONT>
	 * @param oldNpc
	 */
	public void decreaseCount(L2Npc oldNpc)
	{
		// sanity check
		if (_currentCount <= 0)
		{
			return;
		}
		
		// Decrease the current number of L2NpcInstance of this L2Spawn
		_currentCount--;
		
		// Remove this NPC from list of spawned
		_spawnedNpcs.remove(oldNpc);
		
		// Check if respawn is possible to prevent multiple respawning caused by lag
		if (_doRespawn && ((_scheduledCount + _currentCount) < _maximumCount))
		{
			// Update the current number of SpawnTask in progress or stand by of this L2Spawn
			_scheduledCount++;
			
			// Create a new SpawnTask to launch after the respawn Delay
			ThreadPool.schedule(new SpawnTask(), hasRespawnRandom() ? Rnd.get(_respawnMinDelay, _respawnMaxDelay) : _respawnMinDelay);
		}
	}
	
	/**
	 * Create the initial spawning and set _doRespawn to False, if respawn time set to 0, or set it to True otherwise.
	 * @return The number of L2NpcInstance that were spawned
	 */
	public int init()
	{
		while (_currentCount < _maximumCount)
		{
			doSpawn();
		}
		_doRespawn = _respawnMinDelay > 0;
		
		return _currentCount;
	}
	
	/**
	 * @return true if respawn enabled
	 */
	public boolean isRespawnEnabled()
	{
		return _doRespawn;
	}
	
	/**
	 * Set _doRespawn to False to stop respawn in this L2Spawn.
	 */
	public void stopRespawn()
	{
		_doRespawn = false;
	}
	
	/**
	 * Set _doRespawn to True to start or restart respawn in this L2Spawn.
	 */
	public void startRespawn()
	{
		_doRespawn = true;
	}
	
	public L2Npc doSpawn()
	{
		return _doRespawn ? doSpawn(false) : null;
	}
	
	/**
	 * Create the L2NpcInstance, add it to the world and lauch its OnSpawn action.<br>
	 * <B><U>Concept</U>:</B><br>
	 * L2NpcInstance can be spawned either in a random position into a location area (if Lox=0 and Locy=0), either at an exact position.<br>
	 * The heading of the L2NpcInstance can be a random heading if not defined (value= -1) or an exact heading (ex : merchant...).<br>
	 * <B><U>Actions for an random spawn into location area</U>:<I> (if Locx=0 and Locy=0)</I></B>
	 * <ul>
	 * <li>Get L2NpcInstance Init parameters and its generate an Identifier</li>
	 * <li>Call the constructor of the L2NpcInstance</li>
	 * <li>Calculate the random position in the location area (if Locx=0 and Locy=0) or get its exact position from the L2Spawn</li>
	 * <li>Set the position of the L2NpcInstance</li>
	 * <li>Set the HP and MP of the L2NpcInstance to the max</li>
	 * <li>Set the heading of the L2NpcInstance (random heading if not defined : value=-1)</li>
	 * <li>Link the L2NpcInstance to this L2Spawn</li>
	 * <li>Init other values of the L2NpcInstance (ex : from its L2CharTemplate for INT, STR, DEX...) and add it in the world</li>
	 * <li>Launch the action OnSpawn fo the L2NpcInstance</li>
	 * <li>Increase the current number of L2NpcInstance managed by this L2Spawn</li>
	 * </ul>
	 * @param isSummonSpawn
	 * @return
	 */
	public L2Npc doSpawn(boolean isSummonSpawn)
	{
		try
		{
			// Check if the L2Spawn is not a L2Pet or L2Minion or L2Decoy spawn
			if (_template.isType("L2Pet") || _template.isType("L2Decoy") || _template.isType("L2Trap"))
			{
				_currentCount++;
				return null;
			}
			
			// Call the constructor of the L2Npc
			final L2Npc npc = _constructor.newInstance(_template);
			npc.setInstanceId(getInstanceId()); // Must be done before object is spawned into visible world
			if (isSummonSpawn)
			{
				npc.setShowSummonAnimation(isSummonSpawn);
			}
			
			return initializeNpcInstance(npc);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "NPC " + _template.getId() + " class not found", e);
		}
		return null;
	}
	
	/**
	 * @param npc
	 * @return
	 */
	private L2Npc initializeNpcInstance(L2Npc npc)
	{
		int newlocx = 0;
		int newlocy = 0;
		int newlocz = 0;
		
		// If Locx and Locy are not defined, the L2NpcInstance must be spawned in an area defined by location or spawn territory
		// New method
		if (_spawnTerritory != null)
		{
			final Location loc = _spawnTerritory.getRandomPoint();
			newlocx = loc.getX();
			newlocy = loc.getY();
			newlocz = loc.getZ();
			setLocation(loc);
		}
		else if ((getX() == 0) && (getY() == 0))
		{
			LOGGER.warning("NPC " + npc + " doesn't have spawn location!");
			return null;
		}
		else
		{
			// The L2NpcInstance is spawned at the exact position (Lox, Locy, Locz)
			newlocx = getX();
			newlocy = getY();
			newlocz = getZ();
		}
		
		// If random spawn system is enabled
		if (Config.ENABLE_RANDOM_MONSTER_SPAWNS)
		{
			final int randX = newlocx + Rnd.get(Config.MOB_MIN_SPAWN_RANGE, Config.MOB_MAX_SPAWN_RANGE);
			final int randY = newlocy + Rnd.get(Config.MOB_MIN_SPAWN_RANGE, Config.MOB_MAX_SPAWN_RANGE);
			
			if (npc.isMonster() && !npc.isQuestMonster() && !npc.isWalker() && !npc.isInsideZone(ZoneId.NO_BOOKMARK) && (getInstanceId() == 0) && GeoEngine.getInstance().canMoveToTarget(newlocx, newlocy, newlocz, randX, randY, newlocz, getInstanceId()) && !getTemplate().isUndying() && !npc.isRaid() && !npc.isRaidMinion() && !Config.MOBS_LIST_NOT_RANDOM.contains(npc.getId()))
			{
				newlocx = randX;
				newlocy = randY;
			}
		}
		
		npc.stopAllEffects();
		
		npc.setIsDead(false);
		// Reset decay info
		npc.setDecayed(false);
		// Set the HP and MP of the L2NpcInstance to the max
		npc.setCurrentHpMp(npc.getMaxHp(), npc.getMaxMp());
		// Clear script variables
		if (npc.hasVariables())
		{
			npc.getVariables().getSet().clear();
		}
		// Set is not random walk default value
		npc.setRandomWalking(_randomWalk);
		
		// Set the heading of the L2NpcInstance (random heading if not defined)
		if (getHeading() == -1)
		{
			npc.setHeading(Rnd.get(61794));
		}
		else
		{
			npc.setHeading(getHeading());
		}
		
		if (npc.isAttackable())
		{
			((L2Attackable) npc).setChampion(false);
		}
		
		if (Config.CHAMPION_ENABLE)
		{
			// Set champion on next spawn
			if (npc.isMonster() && !npc.isQuestMonster() && !_template.isUndying() && !npc.isRaid() && !npc.isRaidMinion() && (Config.CHAMPION_FREQUENCY > 0) && (npc.getLevel() >= Config.CHAMP_MIN_LVL) && (npc.getLevel() <= Config.CHAMP_MAX_LVL) && (Config.CHAMPION_ENABLE_IN_INSTANCES || (getInstanceId() == 0)))
			{
				if (Rnd.get(100) < Config.CHAMPION_FREQUENCY)
				{
					((L2Attackable) npc).setChampion(true);
				}
			}
		}
		
		// Reset summoner
		npc.setSummoner(null);
		// Reset summoned list
		npc.resetSummonedNpcs();
		// Link the L2NpcInstance to this L2Spawn
		npc.setSpawn(this);
		
		// Spawn NPC
		npc.spawnMe(newlocx, newlocy, newlocz);
		
		notifyNpcSpawned(npc);
		
		// Check for overriden by spawnlist AIData
		if (_name != null)
		{
			NpcPersonalAIData.getInstance().initializeNpcParameters(npc, this, _name);
		}
		
		_spawnedNpcs.add(npc);
		
		// Increase the current number of L2NpcInstance managed by this L2Spawn
		_currentCount++;
		
		// Minions
		if (npc.isMonster() && NpcData.getMasterMonsterIDs().contains(npc.getId()))
		{
			((L2MonsterInstance) npc).getMinionList().spawnMinions(npc.getTemplate().getParameters().getMinionList("Privates"));
		}
		
		return npc;
	}
	
	public static void addSpawnListener(SpawnListener listener)
	{
		_spawnListeners.add(listener);
	}
	
	public static void removeSpawnListener(SpawnListener listener)
	{
		_spawnListeners.remove(listener);
	}
	
	public static void notifyNpcSpawned(L2Npc npc)
	{
		for (SpawnListener listener : _spawnListeners)
		{
			listener.npcSpawned(npc);
		}
	}
	
	/**
	 * Set bounds for random calculation and delay for respawn
	 * @param delay delay in seconds
	 * @param randomInterval random interval in seconds
	 */
	public void setRespawnDelay(int delay, int randomInterval)
	{
		if (delay != 0)
		{
			if (delay < 0)
			{
				LOGGER.warning("respawn delay is negative for spawn:" + this);
			}
			
			final int minDelay = delay - randomInterval;
			final int maxDelay = delay + randomInterval;
			
			_respawnMinDelay = Math.max(10, minDelay) * 1000;
			_respawnMaxDelay = Math.max(10, maxDelay) * 1000;
		}
		else
		{
			_respawnMinDelay = 0;
			_respawnMaxDelay = 0;
		}
	}
	
	public void setRespawnDelay(int delay)
	{
		setRespawnDelay(delay, 0);
	}
	
	public int getRespawnDelay()
	{
		return (_respawnMinDelay + _respawnMaxDelay) / 2;
	}
	
	public boolean hasRespawnRandom()
	{
		return _respawnMinDelay != _respawnMaxDelay;
	}
	
	public void setSpawnTerritory(NpcSpawnTerritory territory)
	{
		_spawnTerritory = territory;
	}
	
	public NpcSpawnTerritory getSpawnTerritory()
	{
		return _spawnTerritory;
	}
	
	public boolean isTerritoryBased()
	{
		return (_spawnTerritory != null) && (getX() == 0) && (getY() == 0);
	}
	
	public L2Npc getLastSpawn()
	{
		return _spawnedNpcs.peekLast();
	}
	
	public final Deque<L2Npc> getSpawnedNpcs()
	{
		return _spawnedNpcs;
	}
	
	public L2NpcTemplate getTemplate()
	{
		return _template;
	}
	
	public final boolean getRandomWalking()
	{
		return _randomWalk;
	}
	
	public final void setRandomWalking(boolean value)
	{
		_randomWalk = value;
	}
	
	public void setSpawnTemplateId(int npcSpawnTemplateId)
	{
		_spawnTemplateId = npcSpawnTemplateId;
	}
	
	public int getNpcSpawnTemplateId()
	{
		return _spawnTemplateId;
	}
	
	@Override
	public String toString()
	{
		return "L2Spawn ID: " + _template.getId() + " X: " + getX() + " Y: " + getY() + " Z: " + getZ() + " Heading: " + getHeading();
	}
}

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
package ai.areas.Hellbound;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.data.xml.impl.DoorData;
import com.l2jmobius.gameserver.instancemanager.GlobalVariablesManager;
import com.l2jmobius.gameserver.model.L2Spawn;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.util.Broadcast;

import ai.AbstractNpcAI;

/**
 * Hellbound Engine.
 * @author Zoey76
 */
public final class HellboundEngine extends AbstractNpcAI
{
	// @formatter:off
	private static final int[][] DOOR_LIST =
	{
		{ 19250001, 5 },
		{ 19250002, 5 },
		{ 20250001, 9 },
		{ 20250002, 7 }
	};
	private static final int[] MAX_TRUST =
	{
		0, 300000, 600000, 1000000, 1010000, 1400000, 1490000, 2000000, 2000001, 2500000, 4000000, 0
	};
	// @formatter:on
	// Monsters
	private static final int DEREK = 18465;
	// Engine
	private static final String ANNOUNCEMENT = "Hellbound has reached level: %lvl%";
	private static final int UPDATE_INTERVAL = 60000; // 1 minute.
	private static final String UPDATE_EVENT = "UPDATE";
	private int _cachedLevel = -1;
	private int _maxTrust = 0;
	private int _minTrust = 0;
	
	public HellboundEngine()
	{
		addKillId(HellboundPointData.getInstance().getPointsInfo().keySet());
		
		startQuestTimer(UPDATE_EVENT, 1000, null, null);
		
		LOGGER.info(HellboundEngine.class.getSimpleName() + ": Level: " + getLevel());
		LOGGER.info(HellboundEngine.class.getSimpleName() + ": Trust: " + getTrust());
		LOGGER.info(HellboundEngine.class.getSimpleName() + ": Status: " + (isLocked() ? "locked." : "unlocked."));
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equals(UPDATE_EVENT))
		{
			int level = getLevel();
			if ((level > 0) && (level == _cachedLevel))
			{
				if ((getTrust() == _maxTrust) && (level != 4)) // Only exclusion is kill of Derek.
				{
					level++;
					setLevel(level);
					onLevelChange(level);
				}
			}
			else
			{
				onLevelChange(level); // First run or changed by administrator.
			}
			startQuestTimer(UPDATE_EVENT, UPDATE_INTERVAL, null, null);
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	/**
	 * Adds and removes spawns for Hellbound given the conditions for spawn.
	 */
	private void doSpawn()
	{
		int added = 0;
		int deleted = 0;
		final HellboundSpawns hellboundSpawns = HellboundSpawns.getInstance();
		for (L2Spawn spawn : hellboundSpawns.getSpawns())
		{
			final L2Npc npc = spawn.getLastSpawn();
			if ((getLevel() < hellboundSpawns.getSpawnMinLevel(spawn.getId())) || (getLevel() > hellboundSpawns.getSpawnMaxLevel(spawn.getId())))
			{
				spawn.stopRespawn();
				
				if ((npc != null) && npc.isSpawned())
				{
					npc.deleteMe();
					deleted++;
				}
			}
			else
			{
				spawn.startRespawn();
				if (npc == null)
				{
					spawn.doSpawn();
					added++;
				}
				else
				{
					if (npc.isDecayed())
					{
						npc.setDecayed(false);
					}
					if (npc.isDead())
					{
						npc.doRevive();
					}
					if (!npc.isSpawned())
					{
						npc.setSpawned(true);
						added++;
					}
					
					npc.setCurrentHp(npc.getMaxHp());
					npc.setCurrentMp(npc.getMaxMp());
				}
			}
		}
		
		if (added > 0)
		{
			LOGGER.info(getClass().getSimpleName() + ": Spawned " + added + " NPCs.");
		}
		if (deleted > 0)
		{
			LOGGER.info(getClass().getSimpleName() + ": Removed " + deleted + " NPCs.");
		}
	}
	
	/**
	 * Gets the Hellbound level.
	 * @return the level
	 */
	public int getLevel()
	{
		return GlobalVariablesManager.getInstance().getInt("HBLevel", 0);
	}
	
	/**
	 * Sets the Hellbound level.
	 * @param lvl the level to set
	 */
	public void setLevel(int lvl)
	{
		if (lvl == getLevel())
		{
			return;
		}
		
		LOGGER.info(HellboundEngine.class.getSimpleName() + ": Changing level from " + getLevel() + " to " + lvl + ".");
		
		GlobalVariablesManager.getInstance().set("HBLevel", lvl);
	}
	
	public int getCachedLevel()
	{
		return _cachedLevel;
	}
	
	public int getMaxTrust()
	{
		return _maxTrust;
	}
	
	public int getMinTrust()
	{
		return _minTrust;
	}
	
	/**
	 * Gets the trust.
	 * @return the trust
	 */
	public int getTrust()
	{
		return GlobalVariablesManager.getInstance().getInt("HBTrust", 0);
	}
	
	/**
	 * Sets the truest.
	 * @param trust the trust to set
	 */
	private void setTrust(int trust)
	{
		GlobalVariablesManager.getInstance().set("HBTrust", trust);
	}
	
	/**
	 * Verifies if Hellbound is locked.
	 * @return {@code true} if Hellbound is locked, {@code false} otherwise
	 */
	public boolean isLocked()
	{
		return getLevel() <= 0;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final int npcId = npc.getId();
		final HellboundPointData hellboundPointData = HellboundPointData.getInstance();
		if (hellboundPointData.getPointsInfo().containsKey(npcId))
		{
			if ((getLevel() >= hellboundPointData.getMinHbLvl(npcId)) && (getLevel() <= hellboundPointData.getMaxHbLvl(npcId)) && ((hellboundPointData.getLowestTrustLimit(npcId) == 0) || (getTrust() > hellboundPointData.getLowestTrustLimit(npcId))))
			{
				updateTrust(hellboundPointData.getPointsAmount(npcId), true);
			}
			
			if ((npcId == DEREK) && (getLevel() == 4))
			{
				setLevel(5);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	/**
	 * Called on every level change.
	 * @param newLevel the new level
	 */
	public void onLevelChange(int newLevel)
	{
		try
		{
			setMaxTrust(MAX_TRUST[newLevel]);
			setMinTrust(MAX_TRUST[newLevel - 1]);
		}
		catch (Exception e)
		{
			setMaxTrust(0);
			setMinTrust(0);
		}
		
		updateTrust(0, false);
		
		doSpawn();
		
		for (int[] doorData : DOOR_LIST)
		{
			try
			{
				final L2DoorInstance door = DoorData.getInstance().getDoor(doorData[0]);
				if (door.isOpen())
				{
					if (newLevel < doorData[1])
					{
						door.closeMe();
					}
				}
				else
				{
					if (newLevel >= doorData[1])
					{
						door.openMe();
					}
				}
			}
			catch (Exception e)
			{
				LOGGER.warning(getClass().getSimpleName() + " : Doors problem!" + e.getMessage());
			}
		}
		
		if (_cachedLevel > 0)
		{
			Broadcast.toAllOnlinePlayers(ANNOUNCEMENT.replace("%lvl%", String.valueOf(newLevel)));
			LOGGER.info(HellboundEngine.class.getSimpleName() + ": New level: " + newLevel);
		}
		_cachedLevel = newLevel;
	}
	
	/**
	 * Sets the maximum trust for the current level.
	 * @param trust the maximum trust
	 */
	private void setMaxTrust(int trust)
	{
		_maxTrust = trust;
		if ((_maxTrust > 0) && (getTrust() > _maxTrust))
		{
			setTrust(_maxTrust);
		}
	}
	
	/**
	 * Sets the minimum trust for the current level.
	 * @param trust the minimum trust
	 */
	private void setMinTrust(int trust)
	{
		_minTrust = trust;
		
		if (getTrust() >= _maxTrust)
		{
			setTrust(_minTrust);
		}
	}
	
	@Override
	public boolean unload()
	{
		cancelQuestTimers(UPDATE_EVENT);
		return true;
	}
	
	/**
	 * Updates the trust.
	 * @param trust the trust
	 * @param useRates if {@code true} it will use Hellbound trust rates
	 */
	public synchronized void updateTrust(int trust, boolean useRates)
	{
		if (isLocked())
		{
			return;
		}
		
		int reward = trust;
		if (useRates)
		{
			reward = (int) (trust * (trust > 0 ? Config.RATE_HB_TRUST_INCREASE : Config.RATE_HB_TRUST_DECREASE));
		}
		
		final int finalTrust = Math.max(getTrust() + reward, _minTrust);
		if (_maxTrust > 0)
		{
			setTrust(Math.min(finalTrust, _maxTrust));
		}
		else
		{
			setTrust(finalTrust);
		}
	}
	
	public static HellboundEngine getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final HellboundEngine INSTANCE = new HellboundEngine();
	}
}
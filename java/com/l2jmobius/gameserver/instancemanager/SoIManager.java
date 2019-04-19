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
package com.l2jmobius.gameserver.instancemanager;

import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.data.xml.impl.DoorData;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.util.Util;

public class SoIManager
{
	protected static final Logger LOGGER = Logger.getLogger(SoIManager.class.getName());
	
	private static final long SOI_OPEN_TIME = 24 * 60 * 60 * 1000;
	
	private static final Location[] openSeedTeleportLocs =
	{
		new Location(-179537, 209551, -15504),
		new Location(-179779, 212540, -15520),
		new Location(-177028, 211135, -15520),
		new Location(-176355, 208043, -15520),
		new Location(-179284, 205990, -15520),
		new Location(-182268, 208218, -15520),
		new Location(-182069, 211140, -15520),
		new Location(-176036, 210002, -11948),
		new Location(-176039, 208203, -11949),
		new Location(-183288, 208205, -11939),
		new Location(-183290, 210004, -11939),
		new Location(-187776, 205696, -9536),
		new Location(-186327, 208286, -9536),
		new Location(-184429, 211155, -9536),
		new Location(-182811, 213871, -9504),
		new Location(-180921, 216789, -9536),
		new Location(-177264, 217760, -9536),
		new Location(-173727, 218169, -9536)
	};
	
	protected SoIManager()
	{
		checkStageAndSpawn();
		if (isSeedOpen())
		{
			openSeed(getOpenedTime());
		}
		LOGGER.info("Seed of Infinity Manager: Loaded. Current stage is: " + getCurrentStage());
	}
	
	public static int getCurrentStage()
	{
		return GlobalVariablesManager.getInstance().getInt("SoI_stage", 1);
	}
	
	public static long getOpenedTime()
	{
		if (getCurrentStage() != 3)
		{
			return 0;
		}
		return (GlobalVariablesManager.getInstance().getLong("SoI_opened", 0) * 1000) - System.currentTimeMillis();
	}
	
	public static void setCurrentStage(int stage)
	{
		if (getCurrentStage() == stage)
		{
			return;
		}
		if (stage == 3)
		{
			openSeed(SOI_OPEN_TIME);
		}
		else if (isSeedOpen())
		{
			closeSeed();
		}
		GlobalVariablesManager.getInstance().set("SoI_stage", stage);
		setCohemenesCount(0);
		setEkimusCount(0);
		setHoEDefCount(0);
		checkStageAndSpawn();
		LOGGER.info("Seed of Infinity Manager: Set to stage " + stage);
	}
	
	public static boolean isSeedOpen()
	{
		return getOpenedTime() > 0;
	}
	
	public static void openSeed(long time)
	{
		if (time <= 0)
		{
			return;
		}
		GlobalVariablesManager.getInstance().set("SoI_opened", (System.currentTimeMillis() + time) / 1000);
		LOGGER.info("Seed of Infinity Manager: Opening the seed for " + Util.formatTime((int) time / 1000));
		spawnOpenedSeed();
		DoorData.getInstance().getDoor(14240102).openMe();
		
		ThreadPool.schedule(() ->
		{
			closeSeed();
			setCurrentStage(4);
		}, time);
	}
	
	public static void closeSeed()
	{
		LOGGER.info("Seed of Infinity Manager: Closing the seed.");
		GlobalVariablesManager.getInstance().remove("SoI_opened");
		// EnergySeeds.SoiSeedStop();
		DoorData.getInstance().getDoor(14240102).closeMe();
		for (L2PcInstance ch : ZoneManager.getInstance().getZoneById(60010).getPlayersInside())
		{
			if (ch != null)
			{
				ch.teleToLocation(-183285, 205996, -12896);
			}
		}
	}
	
	public static void checkStageAndSpawn()
	{
		// EnergySeeds.SoiCloseMouthStop();
		// EnergySeeds.SoiMouthStop();
		// EnergySeeds.SoiAbyssGaze2Stop();
		// EnergySeeds.SoiAbyssGaze1Stop();
		switch (getCurrentStage())
		{
			case 1:
			case 4:
			{
				// EnergySeeds.SoiMouthSpawn();
				// EnergySeeds.SoiAbyssGaze2Spawn();
				break;
			}
			case 5:
			{
				// EnergySeeds.SoiCloseMouthSpawn();
				// EnergySeeds.SoiAbyssGaze2Spawn();
				break;
			}
			default:
			{
				// EnergySeeds.SoiCloseMouthSpawn();
				// EnergySeeds.SoiAbyssGaze1Spawn();
				break;
			}
		}
	}
	
	public static void notifyCohemenesKill()
	{
		if (getCurrentStage() == 1)
		{
			if (getCohemenesCount() < 9)
			{
				setCohemenesCount(getCohemenesCount() + 1);
			}
			else
			{
				setCurrentStage(2);
			}
		}
	}
	
	public static void notifyEkimusKill()
	{
		if (getCurrentStage() == 2)
		{
			if (getEkimusCount() < Config.SOI_EKIMUS_KILL_COUNT)
			{
				setEkimusCount(getEkimusCount() + 1);
			}
			else
			{
				setCurrentStage(3);
			}
		}
	}
	
	public static void notifyHoEDefSuccess()
	{
		if (getCurrentStage() == 4)
		{
			if (getHoEDefCount() < 9)
			{
				setHoEDefCount(getHoEDefCount() + 1);
			}
			else
			{
				setCurrentStage(5);
			}
		}
	}
	
	public static void setCohemenesCount(int i)
	{
		GlobalVariablesManager.getInstance().set("SoI_CohemenesCount", i);
	}
	
	public static void setEkimusCount(int i)
	{
		GlobalVariablesManager.getInstance().set("SoI_EkimusCount", i);
	}
	
	public static void setHoEDefCount(int i)
	{
		GlobalVariablesManager.getInstance().set("SoI_hoedefkillcount", i);
	}
	
	public static int getCohemenesCount()
	{
		return GlobalVariablesManager.getInstance().getInt("SoI_CohemenesCount", 0);
	}
	
	public static int getEkimusCount()
	{
		return GlobalVariablesManager.getInstance().getInt("SoI_EkimusCount", 0);
	}
	
	public static int getHoEDefCount()
	{
		return GlobalVariablesManager.getInstance().getInt("SoI_hoedefkillcount", 0);
	}
	
	private static void spawnOpenedSeed()
	{
		// EnergySeeds.SoiSeedSpawn();
	}
	
	public static void teleportInSeed(L2PcInstance player)
	{
		player.teleToLocation(openSeedTeleportLocs[Rnd.get(openSeedTeleportLocs.length)], false);
	}
	
	/**
	 * Gets the single instance of {@code GraciaSeedsManager}.
	 * @return single instance of {@code GraciaSeedsManager}
	 */
	public static SoIManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final SoIManager _instance = new SoIManager();
	}
}
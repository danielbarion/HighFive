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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.gameserver.data.xml.impl.NpcData;
import com.l2jmobius.gameserver.instancemanager.tasks.GrandBossManagerStoreTask;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2GrandBossInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.interfaces.IStorable;
import com.l2jmobius.gameserver.model.zone.type.L2BossZone;

/**
 * Grand Boss manager.
 * @author DaRkRaGe Revised by Emperorc
 */
public final class GrandBossManager implements IStorable
{
	// SQL queries
	private static final String DELETE_GRAND_BOSS_LIST = "DELETE FROM grandboss_list";
	private static final String INSERT_GRAND_BOSS_LIST = "INSERT INTO grandboss_list (player_id,zone) VALUES (?,?)";
	private static final String UPDATE_GRAND_BOSS_DATA = "UPDATE grandboss_data set loc_x = ?, loc_y = ?, loc_z = ?, heading = ?, respawn_time = ?, currentHP = ?, currentMP = ?, status = ? where boss_id = ?";
	private static final String UPDATE_GRAND_BOSS_DATA2 = "UPDATE grandboss_data set status = ? where boss_id = ?";
	
	protected static Logger LOGGER = Logger.getLogger(GrandBossManager.class.getName());
	
	protected static final Map<Integer, L2GrandBossInstance> BOSSES = new ConcurrentHashMap<>();
	
	protected static Map<Integer, StatsSet> _storedInfo = new HashMap<>();
	
	private final Map<Integer, Integer> _bossStatus = new ConcurrentHashMap<>();
	
	private final Map<Integer, L2BossZone> _zones = new ConcurrentHashMap<>();
	
	protected GrandBossManager()
	{
		init();
	}
	
	private void init()
	{
		try (Connection con = DatabaseFactory.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from grandboss_data ORDER BY boss_id"))
		{
			while (rs.next())
			{
				// Read all info from DB, and store it for AI to read and decide what to do
				// faster than accessing DB in real time
				final StatsSet info = new StatsSet();
				final int bossId = rs.getInt("boss_id");
				info.set("loc_x", rs.getInt("loc_x"));
				info.set("loc_y", rs.getInt("loc_y"));
				info.set("loc_z", rs.getInt("loc_z"));
				info.set("heading", rs.getInt("heading"));
				info.set("respawn_time", rs.getLong("respawn_time"));
				info.set("currentHP", rs.getDouble("currentHP"));
				info.set("currentMP", rs.getDouble("currentMP"));
				int status = rs.getInt("status");
				_bossStatus.put(bossId, status);
				_storedInfo.put(bossId, info);
				LOGGER.info(getClass().getSimpleName() + ": " + NpcData.getInstance().getTemplate(bossId).getName() + "(" + bossId + ") status is " + status + ".");
				if (status > 0)
				{
					LOGGER.info(getClass().getSimpleName() + ": Next spawn date of " + NpcData.getInstance().getTemplate(bossId).getName() + " is " + new Date(info.getLong("respawn_time")) + ".");
				}
			}
			LOGGER.info(getClass().getSimpleName() + ": Loaded " + _storedInfo.size() + " Instances");
		}
		catch (SQLException e)
		{
			LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": Could not load grandboss_data table: " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Error while initializing GrandBossManager: " + e.getMessage(), e);
		}
		ThreadPool.scheduleAtFixedRate(new GrandBossManagerStoreTask(), 5 * 60 * 1000, 5 * 60 * 1000);
	}
	
	/**
	 * Zone Functions
	 */
	public void initZones()
	{
		final Map<Integer, List<Integer>> zones = new HashMap<>();
		for (Integer zoneId : _zones.keySet())
		{
			zones.put(zoneId, new ArrayList<>());
		}
		
		try (Connection con = DatabaseFactory.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from grandboss_list ORDER BY player_id"))
		{
			while (rs.next())
			{
				zones.get(rs.getInt("zone")).add(rs.getInt("player_id"));
			}
			LOGGER.info(getClass().getSimpleName() + ": Initialized " + _zones.size() + " Grand Boss Zones");
		}
		catch (SQLException e)
		{
			LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": Could not load grandboss_list table: " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Error while initializing GrandBoss zones: " + e.getMessage(), e);
		}
		
		for (Entry<Integer, L2BossZone> e : _zones.entrySet())
		{
			e.getValue().setAllowedPlayers(zones.get(e.getKey()));
		}
		
		zones.clear();
	}
	
	public void addZone(L2BossZone zone)
	{
		_zones.put(zone.getId(), zone);
	}
	
	public L2BossZone getZone(int zoneId)
	{
		return _zones.get(zoneId);
	}
	
	public L2BossZone getZone(L2Character character)
	{
		return _zones.values().stream().filter(z -> z.isCharacterInZone(character)).findFirst().orElse(null);
	}
	
	public L2BossZone getZone(Location loc)
	{
		return getZone(loc.getX(), loc.getY(), loc.getZ());
	}
	
	public L2BossZone getZone(int x, int y, int z)
	{
		return _zones.values().stream().filter(zone -> zone.isInsideZone(x, y, z)).findFirst().orElse(null);
	}
	
	public boolean checkIfInZone(String zoneType, L2Object obj)
	{
		final L2BossZone temp = getZone(obj.getX(), obj.getY(), obj.getZ());
		return (temp != null) && temp.getName().equalsIgnoreCase(zoneType);
	}
	
	public boolean checkIfInZone(L2PcInstance player)
	{
		return (player != null) && (getZone(player.getX(), player.getY(), player.getZ()) != null);
	}
	
	public int getBossStatus(int bossId)
	{
		return _bossStatus.get(bossId);
	}
	
	public void setBossStatus(int bossId, int status)
	{
		_bossStatus.put(bossId, status);
		LOGGER.info(getClass().getSimpleName() + ": Updated " + NpcData.getInstance().getTemplate(bossId).getName() + "(" + bossId + ") status to " + status);
		updateDb(bossId, true);
	}
	
	/**
	 * Adds a L2GrandBossInstance to the list of bosses.
	 * @param boss
	 */
	public void addBoss(L2GrandBossInstance boss)
	{
		if (boss != null)
		{
			BOSSES.put(boss.getId(), boss);
		}
	}
	
	public L2GrandBossInstance getBoss(int bossId)
	{
		return BOSSES.get(bossId);
	}
	
	public StatsSet getStatsSet(int bossId)
	{
		return _storedInfo.get(bossId);
	}
	
	public void setStatsSet(int bossId, StatsSet info)
	{
		_storedInfo.put(bossId, info);
		updateDb(bossId, false);
	}
	
	@Override
	public boolean storeMe()
	{
		try (Connection con = DatabaseFactory.getConnection();
			Statement s = con.createStatement())
		{
			s.executeUpdate(DELETE_GRAND_BOSS_LIST);
			
			try (PreparedStatement insert = con.prepareStatement(INSERT_GRAND_BOSS_LIST))
			{
				for (Entry<Integer, L2BossZone> e : _zones.entrySet())
				{
					final List<Integer> list = e.getValue().getAllowedPlayers();
					if ((list == null) || list.isEmpty())
					{
						continue;
					}
					for (Integer player : list)
					{
						insert.setInt(1, player);
						insert.setInt(2, e.getKey());
						insert.executeUpdate();
						insert.clearParameters();
					}
				}
			}
			for (Entry<Integer, StatsSet> e : _storedInfo.entrySet())
			{
				final L2GrandBossInstance boss = BOSSES.get(e.getKey());
				final StatsSet info = e.getValue();
				if ((boss == null) || (info == null))
				{
					try (PreparedStatement update = con.prepareStatement(UPDATE_GRAND_BOSS_DATA2))
					{
						update.setInt(1, _bossStatus.get(e.getKey()));
						update.setInt(2, e.getKey());
						update.executeUpdate();
						update.clearParameters();
					}
				}
				else
				{
					try (PreparedStatement update = con.prepareStatement(UPDATE_GRAND_BOSS_DATA))
					{
						update.setInt(1, boss.getX());
						update.setInt(2, boss.getY());
						update.setInt(3, boss.getZ());
						update.setInt(4, boss.getHeading());
						update.setLong(5, info.getLong("respawn_time"));
						double hp = boss.getCurrentHp();
						double mp = boss.getCurrentMp();
						if (boss.isDead())
						{
							hp = boss.getMaxHp();
							mp = boss.getMaxMp();
						}
						update.setDouble(6, hp);
						update.setDouble(7, mp);
						update.setInt(8, _bossStatus.get(e.getKey()));
						update.setInt(9, e.getKey());
						update.executeUpdate();
						update.clearParameters();
					}
				}
			}
		}
		catch (SQLException e)
		{
			LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": Couldn't store grandbosses to database:" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	private void updateDb(int bossId, boolean statusOnly)
	{
		try (Connection con = DatabaseFactory.getConnection())
		{
			final L2GrandBossInstance boss = BOSSES.get(bossId);
			final StatsSet info = _storedInfo.get(bossId);
			
			if (statusOnly || (boss == null) || (info == null))
			{
				try (PreparedStatement ps = con.prepareStatement(UPDATE_GRAND_BOSS_DATA2))
				{
					ps.setInt(1, _bossStatus.get(bossId));
					ps.setInt(2, bossId);
					ps.executeUpdate();
				}
			}
			else
			{
				try (PreparedStatement ps = con.prepareStatement(UPDATE_GRAND_BOSS_DATA))
				{
					ps.setInt(1, boss.getX());
					ps.setInt(2, boss.getY());
					ps.setInt(3, boss.getZ());
					ps.setInt(4, boss.getHeading());
					ps.setLong(5, info.getLong("respawn_time"));
					double hp = boss.getCurrentHp();
					double mp = boss.getCurrentMp();
					if (boss.isDead())
					{
						hp = boss.getMaxHp();
						mp = boss.getMaxMp();
					}
					ps.setDouble(6, hp);
					ps.setDouble(7, mp);
					ps.setInt(8, _bossStatus.get(bossId));
					ps.setInt(9, bossId);
					ps.executeUpdate();
				}
			}
		}
		catch (SQLException e)
		{
			LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": Couldn't update grandbosses to database:" + e.getMessage(), e);
		}
	}
	
	/**
	 * Saves all Grand Boss info and then clears all info from memory, including all schedules.
	 */
	public void cleanUp()
	{
		storeMe();
		
		BOSSES.clear();
		_storedInfo.clear();
		_bossStatus.clear();
		_zones.clear();
	}
	
	public Map<Integer, L2BossZone> getZones()
	{
		return _zones;
	}
	
	/**
	 * Gets the single instance of {@code GrandBossManager}.
	 * @return single instance of {@code GrandBossManager}
	 */
	public static GrandBossManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final GrandBossManager _instance = new GrandBossManager();
	}
}

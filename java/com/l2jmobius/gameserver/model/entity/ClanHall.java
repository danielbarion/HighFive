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
package com.l2jmobius.gameserver.model.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.gameserver.data.sql.impl.ClanTable;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.zone.type.L2ClanHallZone;
import com.l2jmobius.gameserver.network.serverpackets.PledgeShowInfoUpdate;

public abstract class ClanHall
{
	protected static final Logger LOGGER = Logger.getLogger(ClanHall.class.getName());
	
	final int _clanHallId;
	private ArrayList<L2DoorInstance> _doors;
	private final String _name;
	private int _ownerId;
	private final String _desc;
	private final String _location;
	private L2ClanHallZone _zone;
	protected boolean _isFree = true;
	private final Map<Integer, ClanHallFunction> _functions;
	
	/** Clan Hall Functions */
	public static final int FUNC_TELEPORT = 1;
	public static final int FUNC_ITEM_CREATE = 2;
	public static final int FUNC_RESTORE_HP = 3;
	public static final int FUNC_RESTORE_MP = 4;
	public static final int FUNC_RESTORE_EXP = 5;
	public static final int FUNC_SUPPORT = 6;
	public static final int FUNC_DECO_FRONTPLATEFORM = 7; // Only Auctionable Halls
	public static final int FUNC_DECO_CURTAINS = 8; // Only Auctionable Halls
	
	public class ClanHallFunction
	{
		final int _type;
		private int _lvl;
		protected int _fee;
		protected int _tempFee;
		final long _rate;
		long _endDate;
		protected boolean _inDebt;
		public boolean _cwh; // first activating clanhall function is payed from player inventory, any others from clan warehouse
		
		public ClanHallFunction(int type, int lvl, int lease, int tempLease, long rate, long time, boolean cwh)
		{
			_type = type;
			_lvl = lvl;
			_fee = lease;
			_tempFee = tempLease;
			_rate = rate;
			_endDate = time;
			initializeTask(cwh);
		}
		
		public int getType()
		{
			return _type;
		}
		
		public int getLvl()
		{
			return _lvl;
		}
		
		public int getLease()
		{
			return _fee;
		}
		
		public long getRate()
		{
			return _rate;
		}
		
		public long getEndTime()
		{
			return _endDate;
		}
		
		public void setLvl(int lvl)
		{
			_lvl = lvl;
		}
		
		public void setLease(int lease)
		{
			_fee = lease;
		}
		
		public void setEndTime(long time)
		{
			_endDate = time;
		}
		
		private void initializeTask(boolean cwh)
		{
			if (_isFree)
			{
				return;
			}
			final long currentTime = System.currentTimeMillis();
			if (_endDate > currentTime)
			{
				ThreadPool.schedule(new FunctionTask(cwh), _endDate - currentTime);
			}
			else
			{
				ThreadPool.schedule(new FunctionTask(cwh), 0);
			}
		}
		
		private class FunctionTask implements Runnable
		{
			public FunctionTask(boolean cwh)
			{
				_cwh = cwh;
			}
			
			@Override
			public void run()
			{
				try
				{
					if (_isFree)
					{
						return;
					}
					if ((ClanTable.getInstance().getClan(getOwnerId()).getWarehouse().getAdena() >= _fee) || !_cwh)
					{
						final int fee = _endDate == -1 ? _tempFee : _fee;
						setEndTime(System.currentTimeMillis() + _rate);
						dbSave();
						if (_cwh)
						{
							ClanTable.getInstance().getClan(getOwnerId()).getWarehouse().destroyItemByItemId("CH_function_fee", Inventory.ADENA_ID, fee, null, null);
						}
						ThreadPool.schedule(new FunctionTask(true), _rate);
					}
					else
					{
						removeFunction(_type);
					}
				}
				catch (Exception e)
				{
					LOGGER.log(Level.SEVERE, "", e);
				}
			}
		}
		
		public void dbSave()
		{
			try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement("REPLACE INTO clanhall_functions (hall_id, type, lvl, lease, rate, endTime) VALUES (?,?,?,?,?,?)"))
			{
				ps.setInt(1, _clanHallId);
				ps.setInt(2, _type);
				ps.setInt(3, _lvl);
				ps.setInt(4, _fee);
				ps.setLong(5, _rate);
				ps.setLong(6, _endDate);
				ps.execute();
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Exception: ClanHall.updateFunctions(int type, int lvl, int lease, long rate, long time, boolean addNew): " + e.getMessage(), e);
			}
		}
	}
	
	public ClanHall(StatsSet set)
	{
		_clanHallId = set.getInt("id");
		_name = set.getString("name");
		_ownerId = set.getInt("ownerId");
		_desc = set.getString("desc");
		_location = set.getString("location");
		_functions = new ConcurrentHashMap<>();
		
		if (_ownerId <= 0)
		{
			return;
		}
		
		final L2Clan clan = ClanTable.getInstance().getClan(_ownerId);
		if (clan != null)
		{
			clan.setHideoutId(_clanHallId);
		}
		else
		{
			free();
		}
	}
	
	/**
	 * @return Id Of Clan hall
	 */
	public final int getId()
	{
		return _clanHallId;
	}
	
	/**
	 * @return the Clan Hall name.
	 */
	public final String getName()
	{
		return _name;
	}
	
	/**
	 * @return OwnerId
	 */
	public final int getOwnerId()
	{
		return _ownerId;
	}
	
	/**
	 * @return Desc
	 */
	public final String getDesc()
	{
		return _desc;
	}
	
	/**
	 * @return Location
	 */
	public final String getLocation()
	{
		return _location;
	}
	
	/**
	 * @return all DoorInstance
	 */
	public final ArrayList<L2DoorInstance> getDoors()
	{
		if (_doors == null)
		{
			_doors = new ArrayList<>();
		}
		return _doors;
	}
	
	/**
	 * @param doorId
	 * @return Door
	 */
	public final L2DoorInstance getDoor(int doorId)
	{
		if (doorId <= 0)
		{
			return null;
		}
		for (L2DoorInstance door : getDoors())
		{
			if (door.getId() == doorId)
			{
				return door;
			}
		}
		return null;
	}
	
	/**
	 * @param type
	 * @return function with id
	 */
	public ClanHallFunction getFunction(int type)
	{
		return _functions.get(type);
	}
	
	/**
	 * Sets this clan halls zone
	 * @param zone
	 */
	public void setZone(L2ClanHallZone zone)
	{
		_zone = zone;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return true if object is inside the zone
	 */
	public boolean checkIfInZone(int x, int y, int z)
	{
		return _zone.isInsideZone(x, y, z);
	}
	
	/**
	 * @return the zone of this clan hall
	 */
	public L2ClanHallZone getZone()
	{
		return _zone;
	}
	
	/** Free this clan hall */
	public void free()
	{
		_ownerId = 0;
		_isFree = true;
		for (Integer fc : _functions.keySet())
		{
			removeFunction(fc);
		}
		_functions.clear();
		updateDb();
	}
	
	/**
	 * Set owner if clan hall is free
	 * @param clan
	 */
	public void setOwner(L2Clan clan)
	{
		// Verify that this ClanHall is Free and Clan isn't null
		if ((_ownerId > 0) || (clan == null))
		{
			return;
		}
		_ownerId = clan.getId();
		_isFree = false;
		clan.setHideoutId(_clanHallId);
		// Announce to Online member new ClanHall
		clan.broadcastToOnlineMembers(new PledgeShowInfoUpdate(clan));
		updateDb();
	}
	
	/**
	 * Open or Close Door
	 * @param activeChar
	 * @param doorId
	 * @param open
	 */
	public void openCloseDoor(L2PcInstance activeChar, int doorId, boolean open)
	{
		if ((activeChar != null) && (activeChar.getClanId() == _ownerId))
		{
			openCloseDoor(doorId, open);
		}
	}
	
	public void openCloseDoor(int doorId, boolean open)
	{
		openCloseDoor(getDoor(doorId), open);
	}
	
	public void openCloseDoor(L2DoorInstance door, boolean open)
	{
		if (door != null)
		{
			if (open)
			{
				door.openMe();
			}
			else
			{
				door.closeMe();
			}
		}
	}
	
	public void openCloseDoors(L2PcInstance activeChar, boolean open)
	{
		if ((activeChar != null) && (activeChar.getClanId() == _ownerId))
		{
			openCloseDoors(open);
		}
	}
	
	public void openCloseDoors(boolean open)
	{
		for (L2DoorInstance door : getDoors())
		{
			if (door != null)
			{
				if (open)
				{
					door.openMe();
				}
				else
				{
					door.closeMe();
				}
			}
		}
	}
	
	/** Banish Foreigner */
	public void banishForeigners()
	{
		if (_zone != null)
		{
			_zone.banishForeigners(_ownerId);
		}
		else
		{
			LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": Zone is null for clan hall: " + _clanHallId + " " + _name);
		}
	}
	
	/** Load All Functions */
	protected void loadFunctions()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clanhall_functions WHERE hall_id = ?"))
		{
			ps.setInt(1, _clanHallId);
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					_functions.put(rs.getInt("type"), new ClanHallFunction(rs.getInt("type"), rs.getInt("lvl"), rs.getInt("lease"), 0, rs.getLong("rate"), rs.getLong("endTime"), true));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: ClanHall.loadFunctions(): " + e.getMessage(), e);
		}
	}
	
	/**
	 * Remove function In List and in DB
	 * @param functionType
	 */
	public void removeFunction(int functionType)
	{
		_functions.remove(functionType);
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM clanhall_functions WHERE hall_id=? AND type=?"))
		{
			ps.setInt(1, _clanHallId);
			ps.setInt(2, functionType);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: ClanHall.removeFunctions(int functionType): " + e.getMessage(), e);
		}
	}
	
	public boolean updateFunctions(L2PcInstance player, int type, int lvl, int lease, long rate, boolean addNew)
	{
		if ((player == null) || ((lease > 0) && !player.destroyItemByItemId("Consume", Inventory.ADENA_ID, lease, null, true)))
		{
			return false;
		}
		if (addNew)
		{
			_functions.put(type, new ClanHallFunction(type, lvl, lease, 0, rate, 0, false));
		}
		else if ((lvl == 0) && (lease == 0))
		{
			removeFunction(type);
		}
		else if ((lease - _functions.get(type).getLease()) > 0)
		{
			_functions.remove(type);
			_functions.put(type, new ClanHallFunction(type, lvl, lease, 0, rate, -1, false));
		}
		else
		{
			_functions.get(type).setLease(lease);
			_functions.get(type).setLvl(lvl);
			_functions.get(type).dbSave();
		}
		return true;
	}
	
	public int getGrade()
	{
		return 0;
	}
	
	public long getPaidUntil()
	{
		return 0;
	}
	
	public int getLease()
	{
		return 0;
	}
	
	public boolean isSiegableHall()
	{
		return false;
	}
	
	public boolean isFree()
	{
		return _isFree;
	}
	
	public abstract void updateDb();
}

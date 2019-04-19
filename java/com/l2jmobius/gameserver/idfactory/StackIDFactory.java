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
package com.l2jmobius.gameserver.idfactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import com.l2jmobius.Config;
import com.l2jmobius.commons.database.DatabaseFactory;

/**
 * This class ...
 * @version $Revision: 1.3.2.1.2.7 $ $Date: 2005/04/11 10:06:12 $
 */
public class StackIDFactory extends IdFactory
{
	private int _curOID;
	private int _tempOID;
	
	private final Stack<Integer> _freeOIDStack = new Stack<>();
	
	protected StackIDFactory()
	{
		super();
		_curOID = FIRST_OID;
		_tempOID = FIRST_OID;
		
		try (Connection con = DatabaseFactory.getConnection())
		{
			// con.createStatement().execute("drop table if exists tmp_obj_id");
			
			final Integer[] tmp_obj_ids = extractUsedObjectIDTable();
			if (tmp_obj_ids.length > 0)
			{
				_curOID = tmp_obj_ids[tmp_obj_ids.length - 1];
			}
			LOGGER.info("Max Id = " + _curOID);
			
			int N = tmp_obj_ids.length;
			for (int idx = 0; idx < N; idx++)
			{
				N = insertUntil(tmp_obj_ids, idx, N, con);
			}
			
			_curOID++;
			LOGGER.info("IdFactory: Next usable Object ID is: " + _curOID);
			_initialized = true;
		}
		catch (Exception e)
		{
			LOGGER.severe(getClass().getSimpleName() + ": Could not be initialized properly:" + e.getMessage());
		}
	}
	
	private int insertUntil(Integer[] tmp_obj_ids, int idx, int N, Connection con) throws SQLException
	{
		final int id = tmp_obj_ids[idx];
		if (id == _tempOID)
		{
			_tempOID++;
			return N;
		}
		// check these IDs not present in DB
		if (Config.BAD_ID_CHECKING)
		{
			for (String check : ID_CHECKS)
			{
				try (PreparedStatement ps = con.prepareStatement(check))
				{
					ps.setInt(1, _tempOID);
					// ps.setInt(1, _curOID);
					ps.setInt(2, id);
					try (ResultSet rs = ps.executeQuery())
					{
						if (rs.next())
						{
							final int badId = rs.getInt(1);
							LOGGER.severe("Bad ID " + badId + " in DB found by: " + check);
							throw new RuntimeException();
						}
					}
				}
			}
		}
		
		// int hole = id - _curOID;
		final int hole = (id - _tempOID) > (N - idx) ? N - idx : id - _tempOID;
		for (int i = 1; i <= hole; i++)
		{
			// log.info("Free ID added " + (_tempOID));
			_freeOIDStack.push(_tempOID);
			_tempOID++;
			// _curOID++;
		}
		if (hole < (N - idx))
		{
			_tempOID++;
		}
		return N - hole;
	}
	
	public static IdFactory getInstance()
	{
		return _instance;
	}
	
	@Override
	public synchronized int getNextId()
	{
		int id;
		if (!_freeOIDStack.empty())
		{
			id = _freeOIDStack.pop();
		}
		else
		{
			id = _curOID;
			_curOID += 1;
		}
		return id;
	}
	
	/**
	 * return a used Object ID back to the pool
	 * @param id
	 */
	@Override
	public synchronized void releaseId(int id)
	{
		_freeOIDStack.push(id);
	}
	
	@Override
	public int size()
	{
		return (FREE_OBJECT_ID_SIZE - _curOID) + FIRST_OID + _freeOIDStack.size();
	}
}
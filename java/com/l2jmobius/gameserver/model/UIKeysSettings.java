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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.gameserver.data.xml.impl.UIData;

/**
 * UI Keys Settings class.
 * @author mrTJO, Zoey76
 */
public class UIKeysSettings
{
	private static final Logger LOGGER = Logger.getLogger(UIKeysSettings.class.getName());
	
	private final int _playerObjId;
	private Map<Integer, List<ActionKey>> _storedKeys;
	private Map<Integer, List<Integer>> _storedCategories;
	private boolean _saved = true;
	
	public UIKeysSettings(int playerObjId)
	{
		_playerObjId = playerObjId;
		loadFromDB();
	}
	
	public void storeAll(Map<Integer, List<Integer>> catMap, Map<Integer, List<ActionKey>> keyMap)
	{
		_saved = false;
		_storedCategories = catMap;
		_storedKeys = keyMap;
	}
	
	public void storeCategories(Map<Integer, List<Integer>> catMap)
	{
		_saved = false;
		_storedCategories = catMap;
	}
	
	public Map<Integer, List<Integer>> getCategories()
	{
		return _storedCategories;
	}
	
	public void storeKeys(Map<Integer, List<ActionKey>> keyMap)
	{
		_saved = false;
		_storedKeys = keyMap;
	}
	
	public Map<Integer, List<ActionKey>> getKeys()
	{
		return _storedKeys;
	}
	
	public void loadFromDB()
	{
		getCatsFromDB();
		getKeysFromDB();
	}
	
	/**
	 * Save Categories and Mapped Keys into GameServer DataBase
	 */
	public void saveInDB()
	{
		String query;
		if (_saved)
		{
			return;
		}
		
		// TODO(Zoey76): Refactor this to use batch.
		query = "REPLACE INTO character_ui_categories (`charId`, `catId`, `order`, `cmdId`) VALUES ";
		for (int category : _storedCategories.keySet())
		{
			int order = 0;
			for (int key : _storedCategories.get(category))
			{
				query += "(" + _playerObjId + ", " + category + ", " + order++ + ", " + key + "),";
			}
		}
		query = query.substring(0, query.length() - 1) + "; ";
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement statement = con.prepareStatement(query))
		{
			statement.execute();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception: saveInDB(): " + e.getMessage(), e);
		}
		
		query = "REPLACE INTO character_ui_actions (`charId`, `cat`, `order`, `cmd`, `key`, `tgKey1`, `tgKey2`, `show`) VALUES";
		for (List<ActionKey> keyLst : _storedKeys.values())
		{
			int order = 0;
			for (ActionKey key : keyLst)
			{
				query += key.getSqlSaveString(_playerObjId, order++) + ",";
			}
		}
		query = query.substring(0, query.length() - 1) + ";";
		
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement statement = con.prepareStatement(query))
		{
			statement.execute();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception: saveInDB(): " + e.getMessage(), e);
		}
		_saved = true;
	}
	
	public void getCatsFromDB()
	{
		if (_storedCategories != null)
		{
			return;
		}
		
		_storedCategories = new HashMap<>();
		
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM character_ui_categories WHERE `charId` = ? ORDER BY `catId`, `order`"))
		{
			ps.setInt(1, _playerObjId);
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					UIData.addCategory(_storedCategories, rs.getInt("catId"), rs.getInt("cmdId"));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception: getCatsFromDB(): " + e.getMessage(), e);
		}
		
		if (_storedCategories.isEmpty())
		{
			_storedCategories = UIData.getInstance().getCategories();
		}
	}
	
	public void getKeysFromDB()
	{
		if (_storedKeys != null)
		{
			return;
		}
		
		_storedKeys = new HashMap<>();
		
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM character_ui_actions WHERE `charId` = ? ORDER BY `cat`, `order`"))
		{
			ps.setInt(1, _playerObjId);
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					final int cat = rs.getInt("cat");
					UIData.addKey(_storedKeys, cat, new ActionKey(cat, rs.getInt("cmd"), rs.getInt("key"), rs.getInt("tgKey1"), rs.getInt("tgKey2"), rs.getInt("show")));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception: getKeysFromDB(): " + e.getMessage(), e);
		}
		
		if (_storedKeys.isEmpty())
		{
			_storedKeys = UIData.getInstance().getKeys();
		}
	}
	
	public boolean isSaved()
	{
		return _saved;
	}
}

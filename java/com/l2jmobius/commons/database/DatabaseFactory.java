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
package com.l2jmobius.commons.database;

import java.sql.Connection;
import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Mobius
 */
public class DatabaseFactory
{
	private static final Logger LOGGER = Logger.getLogger(DatabaseFactory.class.getName());
	
	private static final HikariDataSource _hds = new HikariDataSource();
	
	public static void init()
	{
		_hds.setDriverClassName(Config.DATABASE_DRIVER);
		_hds.setJdbcUrl(Config.DATABASE_URL);
		_hds.setUsername(Config.DATABASE_LOGIN);
		_hds.setPassword(Config.DATABASE_PASSWORD);
		_hds.setMaximumPoolSize(Config.DATABASE_MAX_CONNECTIONS);
		_hds.setIdleTimeout(Config.DATABASE_MAX_IDLE_TIME);
		
		// Test if connection is valid.
		try
		{
			_hds.getConnection().close();
			LOGGER.info("Database: Initialized.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		Connection con = null;
		while (con == null)
		{
			try
			{
				con = _hds.getConnection();
			}
			catch (Exception e)
			{
				LOGGER.severe("DatabaseFactory: Cound not get a connection. " + e);
			}
		}
		return con;
	}
	
	public static void close()
	{
		try
		{
			_hds.close();
		}
		catch (Exception e)
		{
			LOGGER.severe("DatabaseFactory: There was a problem closing the data source. " + e);
		}
	}
}

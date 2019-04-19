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
package com.l2jmobius.tools.accountmanager;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;

import com.l2jmobius.Config;
import com.l2jmobius.Server;
import com.l2jmobius.commons.database.DatabaseFactory;

/**
 * This class SQL Account Manager
 * @author netimperia
 */
public class SQLAccountManager
{
	private static String _uname = "";
	private static String _pass = "";
	private static String _level = "";
	private static String _mode = "";
	
	public static void main(String[] args)
	{
		Server.serverMode = Server.MODE_LOGINSERVER;
		Config.load();
		DatabaseFactory.init();
		
		try (Scanner _scn = new Scanner(System.in))
		{
			while (true)
			{
				System.out.println("Please choose an option");
				System.out.println();
				System.out.println("1 - Create new account or update existing one (change pass and access level)");
				System.out.println("2 - Change access level");
				System.out.println("3 - Delete existing account");
				System.out.println("4 - List accounts and access levels");
				System.out.println("5 - Exit");
				while (!_mode.equals("1") && !_mode.equals("2") && !_mode.equals("3") && !_mode.equals("4") && !_mode.equals("5"))
				{
					System.out.print("Your choice: ");
					_mode = _scn.next();
				}
				
				if (_mode.equals("1") || _mode.equals("2") || _mode.equals("3"))
				{
					while (_uname.trim().isEmpty())
					{
						System.out.print("Username: ");
						_uname = _scn.next().toLowerCase();
					}
					
					if (_mode.equals("1"))
					{
						while (_pass.trim().isEmpty())
						{
							System.out.print("Password: ");
							_pass = _scn.next();
						}
					}
					
					if (_mode.equals("1") || _mode.equals("2"))
					{
						while (_level.trim().isEmpty())
						{
							System.out.print("Access level: ");
							_level = _scn.next();
						}
					}
				}
				
				if (_mode.equals("1"))
				{
					// Add or Update
					addOrUpdateAccount(_uname.trim(), _pass.trim(), _level.trim());
				}
				else if (_mode.equals("2"))
				{
					// Change Level
					changeAccountLevel(_uname.trim(), _level.trim());
				}
				else if (_mode.equals("3"))
				{
					// Delete
					System.out.print("WARNING: This will not delete the gameserver data (characters, items, etc..)");
					System.out.print(" it will only delete the account login server data.");
					System.out.println();
					System.out.print("Do you really want to delete this account? Y/N: ");
					final String yesno = _scn.next();
					if ((yesno != null) && yesno.equalsIgnoreCase("Y"))
					{
						deleteAccount(_uname.trim());
					}
					else
					{
						System.out.println("Deletion cancelled.");
					}
				}
				else if (_mode.equals("4"))
				{
					// List
					_mode = "";
					System.out.println();
					System.out.println("Please choose a listing mode");
					System.out.println();
					System.out.println("1 - Banned accounts only (accessLevel < 0)");
					System.out.println("2 - GM/privileged accounts (accessLevel > 0");
					System.out.println("3 - Regular accounts only (accessLevel = 0)");
					System.out.println("4 - List all");
					while (!_mode.equals("1") && !_mode.equals("2") && !_mode.equals("3") && !_mode.equals("4"))
					{
						System.out.print("Your choice: ");
						_mode = _scn.next();
					}
					System.out.println();
					printAccInfo(_mode);
				}
				else if (_mode.equals("5"))
				{
					System.exit(0);
				}
				
				_uname = "";
				_pass = "";
				_level = "";
				_mode = "";
				System.out.println();
			}
		}
	}
	
	private static void printAccInfo(String m)
	{
		int count = 0;
		String q = "SELECT login, accessLevel FROM accounts ";
		if (m.equals("1"))
		{
			q += "WHERE accessLevel < 0";
		}
		else if (m.equals("2"))
		{
			q += "WHERE accessLevel > 0";
		}
		else if (m.equals("3"))
		{
			q += "WHERE accessLevel = 0";
		}
		q += " ORDER BY login ASC";
		
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(q);
			ResultSet rset = ps.executeQuery())
		{
			while (rset.next())
			{
				System.out.println(rset.getString("login") + " -> " + rset.getInt("accessLevel"));
				count++;
			}
			
			System.out.println("Displayed accounts: " + count);
		}
		catch (SQLException e)
		{
			System.out.println("There was error while displaying accounts:");
			System.out.println(e.getMessage());
		}
	}
	
	private static void addOrUpdateAccount(String account, String password, String level)
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("REPLACE accounts(login, password, accessLevel) VALUES (?, ?, ?)"))
		{
			final MessageDigest md = MessageDigest.getInstance("SHA");
			final byte[] newPassword = md.digest(password.getBytes("UTF-8"));
			ps.setString(1, account);
			ps.setString(2, Base64.getEncoder().encodeToString(newPassword));
			ps.setString(3, level);
			if (ps.executeUpdate() > 0)
			{
				System.out.println("Account " + account + " has been created or updated");
			}
			else
			{
				System.out.println("Account " + account + " does not exist");
			}
		}
		catch (Exception e)
		{
			System.out.println("There was error while adding/updating account:");
			System.out.println(e.getMessage());
		}
	}
	
	private static void changeAccountLevel(String account, String level)
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE accounts SET accessLevel = ? WHERE login = ?"))
		{
			ps.setString(1, level);
			ps.setString(2, account);
			if (ps.executeUpdate() > 0)
			{
				System.out.println("Account " + account + " has been updated");
			}
			else
			{
				System.out.println("Account " + account + " does not exist");
			}
		}
		catch (SQLException e)
		{
			System.out.println("There was error while updating account:");
			System.out.println(e.getMessage());
		}
	}
	
	private static void deleteAccount(String account)
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM accounts WHERE login = ?"))
		{
			ps.setString(1, account);
			if (ps.executeUpdate() > 0)
			{
				System.out.println("Account " + account + " has been deleted");
			}
			else
			{
				System.out.println("Account " + account + " does not exist");
			}
		}
		catch (SQLException e)
		{
			System.out.println("There was error while deleting account:");
			System.out.println(e.getMessage());
		}
	}
}

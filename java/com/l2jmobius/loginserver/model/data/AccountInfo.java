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
package com.l2jmobius.loginserver.model.data;

import java.util.Objects;

/**
 * @author HorridoJoho
 */
public final class AccountInfo
{
	private final String _login;
	private final String _passHash;
	private final int _accessLevel;
	private final int _lastServer;
	
	public AccountInfo(String login, String passHash, int accessLevel, int lastServer)
	{
		Objects.requireNonNull(login, "login");
		Objects.requireNonNull(passHash, "passHash");
		
		if (login.isEmpty())
		{
			throw new IllegalArgumentException("login");
		}
		if (passHash.isEmpty())
		{
			throw new IllegalArgumentException("passHash");
		}
		
		_login = login.toLowerCase();
		_passHash = passHash;
		_accessLevel = accessLevel;
		_lastServer = lastServer;
	}
	
	public boolean checkPassHash(String passHash)
	{
		return _passHash.equals(passHash);
	}
	
	public String getLogin()
	{
		return _login;
	}
	
	public int getAccessLevel()
	{
		return _accessLevel;
	}
	
	public int getLastServer()
	{
		return _lastServer;
	}
}

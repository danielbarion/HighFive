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
package com.l2jmobius.loginserver.network.gameserverpackets;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.commons.network.BaseRecievePacket;
import com.l2jmobius.loginserver.LoginController;

/**
 * @author mrTJO
 */
public class RequestTempBan extends BaseRecievePacket
{
	private static final Logger LOGGER = Logger.getLogger(RequestTempBan.class.getName());
	
	private final String _accountName;
	@SuppressWarnings("unused")
	private String _banReason;
	private final String _ip;
	long _banTime;
	
	/**
	 * @param decrypt
	 */
	public RequestTempBan(byte[] decrypt)
	{
		super(decrypt);
		_accountName = readS();
		_ip = readS();
		_banTime = readQ();
		final boolean haveReason = readC() != 0;
		if (haveReason)
		{
			_banReason = readS();
		}
		banUser();
	}
	
	private void banUser()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO account_data VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE value=?"))
		{
			ps.setString(1, _accountName);
			ps.setString(2, "ban_temp");
			ps.setString(3, Long.toString(_banTime));
			ps.setString(4, Long.toString(_banTime));
			ps.execute();
		}
		catch (SQLException e)
		{
			LOGGER.warning(getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		try
		{
			LoginController.getInstance().addBanForAddress(_ip, _banTime);
		}
		catch (UnknownHostException e)
		{
		}
	}
}

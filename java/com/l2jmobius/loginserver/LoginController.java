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
package com.l2jmobius.loginserver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.spec.RSAKeyGenParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.l2jmobius.Config;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.commons.util.crypt.ScrambledKeyPair;
import com.l2jmobius.loginserver.GameServerTable.GameServerInfo;
import com.l2jmobius.loginserver.model.data.AccountInfo;
import com.l2jmobius.loginserver.network.L2LoginClient;
import com.l2jmobius.loginserver.network.gameserverpackets.ServerStatus;
import com.l2jmobius.loginserver.network.serverpackets.LoginFail.LoginFailReason;

public class LoginController
{
	protected static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
	
	private static LoginController _instance;
	
	/** Time before kicking the client if he didn't logged yet */
	public static final int LOGIN_TIMEOUT = 60 * 1000;
	
	/** Authed Clients on LoginServer */
	protected Map<String, L2LoginClient> _loginServerClients = new ConcurrentHashMap<>();
	
	private final Map<InetAddress, Integer> _failedLoginAttemps = new HashMap<>();
	private final Map<InetAddress, Long> _bannedIps = new ConcurrentHashMap<>();
	
	private final ScrambledKeyPair[] _keyPairs;
	private final KeyGenerator _blowfishKeyGenerator;
	
	// SQL Queries
	private static final String USER_INFO_SELECT = "SELECT login, password, IF(? > value OR value IS NULL, accessLevel, -1) AS accessLevel, lastServer FROM accounts LEFT JOIN (account_data) ON (account_data.account_name=accounts.login AND account_data.var=\"ban_temp\") WHERE login=?";
	private static final String AUTOCREATE_ACCOUNTS_INSERT = "INSERT INTO accounts (login, password, lastactive, accessLevel, lastIP) values (?, ?, ?, ?, ?)";
	private static final String ACCOUNT_INFO_UPDATE = "UPDATE accounts SET lastactive = ?, lastIP = ? WHERE login = ?";
	private static final String ACCOUNT_LAST_SERVER_UPDATE = "UPDATE accounts SET lastServer = ? WHERE login = ?";
	private static final String ACCOUNT_ACCESS_LEVEL_UPDATE = "UPDATE accounts SET accessLevel = ? WHERE login = ?";
	private static final String ACCOUNT_IPS_UPDATE = "UPDATE accounts SET pcIp = ?, hop1 = ?, hop2 = ?, hop3 = ?, hop4 = ? WHERE login = ?";
	private static final String ACCOUNT_IPAUTH_SELECT = "SELECT * FROM accounts_ipauth WHERE login = ?";
	
	private LoginController() throws GeneralSecurityException
	{
		LOGGER.info("Loading LoginController...");
		
		_keyPairs = new ScrambledKeyPair[10];
		_blowfishKeyGenerator = KeyGenerator.getInstance("Blowfish");
		final KeyPairGenerator rsaKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		final RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(1024, RSAKeyGenParameterSpec.F4);
		rsaKeyPairGenerator.initialize(spec);
		
		for (int i = 0; i < _keyPairs.length; i++)
		{
			_keyPairs[i] = new ScrambledKeyPair(rsaKeyPairGenerator.generateKeyPair());
		}
		
		LOGGER.info("Cached 10 KeyPairs for RSA communication.");
		
		final Thread purge = new PurgeThread();
		purge.setDaemon(true);
		purge.start();
	}
	
	public SecretKey generateBlowfishKey()
	{
		return _blowfishKeyGenerator.generateKey();
	}
	
	public SessionKey assignSessionKeyToClient(String account, L2LoginClient client)
	{
		final SessionKey key = new SessionKey(Rnd.nextInt(), Rnd.nextInt(), Rnd.nextInt(), Rnd.nextInt());
		
		_loginServerClients.put(account, client);
		return key;
	}
	
	public void removeAuthedLoginClient(String account)
	{
		if (account == null)
		{
			return;
		}
		_loginServerClients.remove(account);
	}
	
	public L2LoginClient getAuthedClient(String account)
	{
		return _loginServerClients.get(account);
	}
	
	public AccountInfo retriveAccountInfo(InetAddress clientAddr, String login, String password)
	{
		return retriveAccountInfo(clientAddr, login, password, true);
	}
	
	private void recordFailedLoginAttemp(InetAddress addr)
	{
		// We need to synchronize this!
		// When multiple connections from the same address fail to login at the
		// same time, unexpected behavior can happen.
		Integer failedLoginAttemps;
		synchronized (_failedLoginAttemps)
		{
			failedLoginAttemps = _failedLoginAttemps.get(addr);
			if (failedLoginAttemps == null)
			{
				failedLoginAttemps = 1;
			}
			else
			{
				++failedLoginAttemps;
			}
			
			_failedLoginAttemps.put(addr, failedLoginAttemps);
		}
		
		if (failedLoginAttemps >= Config.LOGIN_TRY_BEFORE_BAN)
		{
			addBanForAddress(addr, Config.LOGIN_BLOCK_AFTER_BAN * 1000);
			// we need to clear the failed login attempts here, so after the ip ban is over the client has another 5 attempts
			clearFailedLoginAttemps(addr);
			LOGGER.warning("Added banned address " + addr.getHostAddress() + "! Too many login attemps.");
		}
	}
	
	private void clearFailedLoginAttemps(InetAddress addr)
	{
		synchronized (_failedLoginAttemps)
		{
			_failedLoginAttemps.remove(addr);
		}
	}
	
	private AccountInfo retriveAccountInfo(InetAddress addr, String login, String password, boolean autoCreateIfEnabled)
	{
		try
		{
			final MessageDigest md = MessageDigest.getInstance("SHA");
			final byte[] raw = password.getBytes(StandardCharsets.UTF_8);
			final String hashBase64 = Base64.getEncoder().encodeToString(md.digest(raw));
			
			try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(USER_INFO_SELECT))
			{
				ps.setString(1, Long.toString(System.currentTimeMillis()));
				ps.setString(2, login);
				try (ResultSet rset = ps.executeQuery())
				{
					if (rset.next())
					{
						final AccountInfo info = new AccountInfo(rset.getString("login"), rset.getString("password"), rset.getInt("accessLevel"), rset.getInt("lastServer"));
						if (!info.checkPassHash(hashBase64))
						{
							// wrong password
							recordFailedLoginAttemp(addr);
							return null;
						}
						
						clearFailedLoginAttemps(addr);
						return info;
					}
				}
			}
			
			if (!autoCreateIfEnabled || !Config.AUTO_CREATE_ACCOUNTS)
			{
				// account does not exist and auto create account is not desired
				recordFailedLoginAttemp(addr);
				return null;
			}
			
			try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(AUTOCREATE_ACCOUNTS_INSERT))
			{
				ps.setString(1, login);
				ps.setString(2, hashBase64);
				ps.setLong(3, System.currentTimeMillis());
				ps.setInt(4, 0);
				ps.setString(5, addr.getHostAddress());
				ps.execute();
			}
			catch (Exception e)
			{
				LOGGER.log(Level.WARNING, "Exception while auto creating account for '" + login + "'!", e);
				return null;
			}
			
			LOGGER.info("Auto created account '" + login + "'.");
			return retriveAccountInfo(addr, login, password, false);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception while retriving account info for '" + login + "'!", e);
			return null;
		}
	}
	
	public AuthLoginResult tryCheckinAccount(L2LoginClient client, InetAddress address, AccountInfo info)
	{
		if (info.getAccessLevel() < 0)
		{
			return AuthLoginResult.ACCOUNT_BANNED;
		}
		
		AuthLoginResult ret = AuthLoginResult.INVALID_PASSWORD;
		// check auth
		if (canCheckin(client, address, info))
		{
			// login was successful, verify presence on Gameservers
			ret = AuthLoginResult.ALREADY_ON_GS;
			if (!isAccountInAnyGameServer(info.getLogin()))
			{
				// account isnt on any GS verify LS itself
				ret = AuthLoginResult.ALREADY_ON_LS;
				
				if (_loginServerClients.putIfAbsent(info.getLogin(), client) == null)
				{
					ret = AuthLoginResult.AUTH_SUCCESS;
				}
			}
		}
		return ret;
	}
	
	/**
	 * Adds the address to the ban list of the login server, with the given end time in milliseconds.
	 * @param address The Address to be banned.
	 * @param expiration Timestamp in milliseconds when this ban expires
	 * @throws UnknownHostException if the address is invalid.
	 */
	public void addBanForAddress(String address, long expiration) throws UnknownHostException
	{
		_bannedIps.putIfAbsent(InetAddress.getByName(address), expiration);
	}
	
	/**
	 * Adds the address to the ban list of the login server, with the given duration.
	 * @param address The Address to be banned.
	 * @param duration is milliseconds
	 */
	public void addBanForAddress(InetAddress address, long duration)
	{
		_bannedIps.putIfAbsent(address, System.currentTimeMillis() + duration);
	}
	
	public boolean isBannedAddress(InetAddress address) throws UnknownHostException
	{
		final String[] parts = address.getHostAddress().split("\\.");
		Long bi = _bannedIps.get(address);
		if (bi == null)
		{
			bi = _bannedIps.get(InetAddress.getByName(parts[0] + "." + parts[1] + "." + parts[2] + ".0"));
		}
		if (bi == null)
		{
			bi = _bannedIps.get(InetAddress.getByName(parts[0] + "." + parts[1] + ".0.0"));
		}
		if (bi == null)
		{
			bi = _bannedIps.get(InetAddress.getByName(parts[0] + ".0.0.0"));
		}
		if (bi != null)
		{
			if ((bi > 0) && (bi < System.currentTimeMillis()))
			{
				_bannedIps.remove(address);
				LOGGER.info("Removed expired ip address ban " + address.getHostAddress() + ".");
				return false;
			}
			return true;
		}
		return false;
	}
	
	public Map<InetAddress, Long> getBannedIps()
	{
		return _bannedIps;
	}
	
	/**
	 * Remove the specified address from the ban list
	 * @param address The address to be removed from the ban list
	 * @return true if the ban was removed, false if there was no ban for this ip
	 */
	public boolean removeBanForAddress(InetAddress address)
	{
		return _bannedIps.remove(address) != null;
	}
	
	/**
	 * Remove the specified address from the ban list
	 * @param address The address to be removed from the ban list
	 * @return true if the ban was removed, false if there was no ban for this ip or the address was invalid.
	 */
	public boolean removeBanForAddress(String address)
	{
		try
		{
			return removeBanForAddress(InetAddress.getByName(address));
		}
		catch (UnknownHostException e)
		{
			return false;
		}
	}
	
	public SessionKey getKeyForAccount(String account)
	{
		final L2LoginClient client = _loginServerClients.get(account);
		if (client != null)
		{
			return client.getSessionKey();
		}
		return null;
	}
	
	public boolean isAccountInAnyGameServer(String account)
	{
		final Collection<GameServerInfo> serverList = GameServerTable.getInstance().getRegisteredGameServers().values();
		for (GameServerInfo gsi : serverList)
		{
			final GameServerThread gst = gsi.getGameServerThread();
			if ((gst != null) && gst.hasAccountOnGameServer(account))
			{
				return true;
			}
		}
		return false;
	}
	
	public GameServerInfo getAccountOnGameServer(String account)
	{
		final Collection<GameServerInfo> serverList = GameServerTable.getInstance().getRegisteredGameServers().values();
		for (GameServerInfo gsi : serverList)
		{
			final GameServerThread gst = gsi.getGameServerThread();
			if ((gst != null) && gst.hasAccountOnGameServer(account))
			{
				return gsi;
			}
		}
		return null;
	}
	
	public void getCharactersOnAccount(String account)
	{
		for (GameServerInfo gsi : GameServerTable.getInstance().getRegisteredGameServers().values())
		{
			if (gsi.isAuthed())
			{
				gsi.getGameServerThread().requestCharacters(account);
			}
		}
	}
	
	/**
	 * @param client
	 * @param serverId
	 * @return
	 */
	public boolean isLoginPossible(L2LoginClient client, int serverId)
	{
		final GameServerInfo gsi = GameServerTable.getInstance().getRegisteredGameServerById(serverId);
		final int access = client.getAccessLevel();
		if ((gsi != null) && gsi.isAuthed())
		{
			final boolean loginOk = ((gsi.getCurrentPlayerCount() < gsi.getMaxPlayers()) && (gsi.getStatus() != ServerStatus.STATUS_GM_ONLY)) || (access > 0);
			
			if (loginOk && (client.getLastServer() != serverId))
			{
				try (Connection con = DatabaseFactory.getConnection();
					PreparedStatement ps = con.prepareStatement(ACCOUNT_LAST_SERVER_UPDATE))
				{
					ps.setInt(1, serverId);
					ps.setString(2, client.getAccount());
					ps.executeUpdate();
				}
				catch (Exception e)
				{
					LOGGER.log(Level.WARNING, "Could not set lastServer: " + e.getMessage(), e);
				}
			}
			return loginOk;
		}
		return false;
	}
	
	public void setAccountAccessLevel(String account, int banLevel)
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(ACCOUNT_ACCESS_LEVEL_UPDATE))
		{
			ps.setInt(1, banLevel);
			ps.setString(2, account);
			ps.executeUpdate();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Could not set accessLevel: " + e.getMessage(), e);
		}
	}
	
	public void setAccountLastTracert(String account, String pcIp, String hop1, String hop2, String hop3, String hop4)
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(ACCOUNT_IPS_UPDATE))
		{
			ps.setString(1, pcIp);
			ps.setString(2, hop1);
			ps.setString(3, hop2);
			ps.setString(4, hop3);
			ps.setString(5, hop4);
			ps.setString(6, account);
			ps.executeUpdate();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Could not set last tracert: " + e.getMessage(), e);
		}
	}
	
	public void setCharactersOnServer(String account, int charsNum, long[] timeToDel, int serverId)
	{
		final L2LoginClient client = _loginServerClients.get(account);
		
		if (client == null)
		{
			return;
		}
		
		if (charsNum > 0)
		{
			client.setCharsOnServ(serverId, charsNum);
		}
		
		if (timeToDel.length > 0)
		{
			client.serCharsWaitingDelOnServ(serverId, timeToDel);
		}
	}
	
	/**
	 * <p>
	 * This method returns one of the cached {@link ScrambledKeyPair ScrambledKeyPairs} for communication with Login Clients.
	 * </p>
	 * @return a scrambled keypair
	 */
	public ScrambledKeyPair getScrambledRSAKeyPair()
	{
		return _keyPairs[Rnd.get(10)];
	}
	
	/**
	 * @param client the client
	 * @param address client host address
	 * @param info the account info to checkin
	 * @return true when ok to checkin, false otherwise
	 */
	public boolean canCheckin(L2LoginClient client, InetAddress address, AccountInfo info)
	{
		try
		{
			final List<InetAddress> ipWhiteList = new ArrayList<>();
			final List<InetAddress> ipBlackList = new ArrayList<>();
			try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(ACCOUNT_IPAUTH_SELECT))
			{
				ps.setString(1, info.getLogin());
				try (ResultSet rset = ps.executeQuery())
				{
					String ip;
					String type;
					while (rset.next())
					{
						ip = rset.getString("ip");
						type = rset.getString("type");
						
						if (!isValidIPAddress(ip))
						{
							continue;
						}
						if (type.equals("allow"))
						{
							ipWhiteList.add(InetAddress.getByName(ip));
						}
						else if (type.equals("deny"))
						{
							ipBlackList.add(InetAddress.getByName(ip));
						}
					}
				}
			}
			
			// Check IP
			if (!ipWhiteList.isEmpty() || !ipBlackList.isEmpty())
			{
				if (!ipWhiteList.isEmpty() && !ipWhiteList.contains(address))
				{
					LOGGER.warning("Account checkin attemp from address(" + address.getHostAddress() + ") not present on whitelist for account '" + info.getLogin() + "'.");
					return false;
				}
				
				if (!ipBlackList.isEmpty() && ipBlackList.contains(address))
				{
					LOGGER.warning("Account checkin attemp from address(" + address.getHostAddress() + ") on blacklist for account '" + info.getLogin() + "'.");
					return false;
				}
			}
			
			client.setAccessLevel(info.getAccessLevel());
			client.setLastServer(info.getLastServer());
			try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(ACCOUNT_INFO_UPDATE))
			{
				ps.setLong(1, System.currentTimeMillis());
				ps.setString(2, address.getHostAddress());
				ps.setString(3, info.getLogin());
				ps.execute();
			}
			
			return true;
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Could not finish login process!", e);
			return false;
		}
	}
	
	public boolean isValidIPAddress(String ipAddress)
	{
		final String[] parts = ipAddress.split("\\.");
		if (parts.length != 4)
		{
			return false;
		}
		
		for (String s : parts)
		{
			final int i = Integer.parseInt(s);
			if ((i < 0) || (i > 255))
			{
				return false;
			}
		}
		return true;
	}
	
	public static void load() throws GeneralSecurityException
	{
		synchronized (LoginController.class)
		{
			if (_instance == null)
			{
				_instance = new LoginController();
			}
			else
			{
				throw new IllegalStateException("LoginController can only be loaded a single time.");
			}
		}
	}
	
	public static LoginController getInstance()
	{
		return _instance;
	}
	
	class PurgeThread extends Thread
	{
		public PurgeThread()
		{
			setName("PurgeThread");
		}
		
		@Override
		public void run()
		{
			while (!isInterrupted())
			{
				for (L2LoginClient client : _loginServerClients.values())
				{
					if (client == null)
					{
						continue;
					}
					if ((client.getConnectionStartTime() + LOGIN_TIMEOUT) < System.currentTimeMillis())
					{
						client.close(LoginFailReason.REASON_ACCESS_FAILED);
					}
				}
				
				try
				{
					Thread.sleep(LOGIN_TIMEOUT / 2);
				}
				catch (InterruptedException e)
				{
					return;
				}
			}
		}
	}
	
	public enum AuthLoginResult
	{
		INVALID_PASSWORD,
		ACCOUNT_BANNED,
		ALREADY_ON_LS,
		ALREADY_ON_GS,
		AUTH_SUCCESS
	}
}

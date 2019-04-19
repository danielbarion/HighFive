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
package com.l2jmobius.gameserver.model.entity.clanhall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.gameserver.data.sql.impl.ClanTable;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.CHSiegeManager;
import com.l2jmobius.gameserver.instancemanager.MapRegionManager;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.L2SiegeClan;
import com.l2jmobius.gameserver.model.L2SiegeClan.SiegeClanType;
import com.l2jmobius.gameserver.model.L2Spawn;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.entity.Siegable;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Broadcast;

/**
 * @author BiggBoss
 */
public abstract class ClanHallSiegeEngine extends Quest implements Siegable
{
	private static final String SQL_LOAD_ATTACKERS = "SELECT attacker_id FROM clanhall_siege_attackers WHERE clanhall_id = ?";
	private static final String SQL_SAVE_ATTACKERS = "INSERT INTO clanhall_siege_attackers VALUES (?,?)";
	private static final String SQL_LOAD_GUARDS = "SELECT * FROM clanhall_siege_guards WHERE clanHallId = ?";
	
	public static final int FORTRESS_RESSISTANCE = 21;
	public static final int DEVASTATED_CASTLE = 34;
	public static final int BANDIT_STRONGHOLD = 35;
	public static final int RAINBOW_SPRINGS = 62;
	public static final int BEAST_FARM = 63;
	public static final int FORTRESS_OF_DEAD = 64;
	
	protected final Logger _log;
	
	private final Map<Integer, L2SiegeClan> _attackers = new ConcurrentHashMap<>();
	private Collection<L2Spawn> _guards;
	
	public SiegableHall _hall;
	public ScheduledFuture<?> _siegeTask;
	public boolean _missionAccomplished = false;
	
	public ClanHallSiegeEngine(int hallId)
	{
		super(-1);
		_log = Logger.getLogger(getClass().getName());
		
		_hall = CHSiegeManager.getInstance().getSiegableHall(hallId);
		_hall.setSiege(this);
		
		_siegeTask = ThreadPool.schedule(new PrepareOwner(), _hall.getNextSiegeTime() - System.currentTimeMillis() - 3600000);
		LOGGER.config(_hall.getName() + " siege scheduled for " + _hall.getSiegeDate().getTime() + ".");
		loadAttackers();
	}
	
	public void loadAttackers()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_LOAD_ATTACKERS))
		{
			ps.setInt(1, _hall.getId());
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					final int id = rset.getInt("attacker_id");
					_attackers.put(id, new L2SiegeClan(id, SiegeClanType.ATTACKER));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(getName() + ": Could not load siege attackers!");
		}
	}
	
	public final void saveAttackers()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM clanhall_siege_attackers WHERE clanhall_id = ?"))
		{
			ps.setInt(1, _hall.getId());
			ps.execute();
			
			if (_attackers.size() > 0)
			{
				try (PreparedStatement insert = con.prepareStatement(SQL_SAVE_ATTACKERS))
				{
					for (L2SiegeClan clan : _attackers.values())
					{
						insert.setInt(1, _hall.getId());
						insert.setInt(2, clan.getClanId());
						insert.execute();
						insert.clearParameters();
					}
				}
			}
			LOGGER.config(getName() + ": Successfully saved attackers to database.");
		}
		catch (Exception e)
		{
			LOGGER.warning(getName() + ": Couldn't save attacker list!");
		}
	}
	
	public final void loadGuards()
	{
		if (_guards != null)
		{
			return;
		}
		_guards = new ArrayList<>();
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_LOAD_GUARDS))
		{
			ps.setInt(1, _hall.getId());
			final ResultSet rset = ps.executeQuery();
			while (rset.next())
			{
				final L2Spawn spawn = new L2Spawn(rset.getInt("npcId"));
				spawn.setXYZ(rset.getInt("x"), rset.getInt("y"), rset.getInt("z"));
				spawn.setHeading(rset.getInt("heading"));
				spawn.setRespawnDelay(rset.getInt("respawnDelay"));
				spawn.setAmount(1);
				_guards.add(spawn);
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(getName() + ": Couldnt load siege guards!");
		}
	}
	
	private final void spawnSiegeGuards()
	{
		for (L2Spawn guard : _guards)
		{
			guard.init();
			if (guard.getRespawnDelay() == 0)
			{
				guard.stopRespawn();
			}
		}
	}
	
	private final void unSpawnSiegeGuards()
	{
		if (_guards != null)
		{
			for (L2Spawn guard : _guards)
			{
				guard.stopRespawn();
				if (guard.getLastSpawn() != null)
				{
					guard.getLastSpawn().deleteMe();
				}
			}
		}
	}
	
	@Override
	public List<L2Npc> getFlag(L2Clan clan)
	{
		List<L2Npc> result = null;
		final L2SiegeClan sClan = getAttackerClan(clan);
		if (sClan != null)
		{
			result = sClan.getFlag();
		}
		return result;
	}
	
	public final Map<Integer, L2SiegeClan> getAttackers()
	{
		return _attackers;
	}
	
	@Override
	public boolean checkIsAttacker(L2Clan clan)
	{
		if (clan == null)
		{
			return false;
		}
		
		return _attackers.containsKey(clan.getId());
	}
	
	@Override
	public boolean checkIsDefender(L2Clan clan)
	{
		return false;
	}
	
	@Override
	public L2SiegeClan getAttackerClan(int clanId)
	{
		return _attackers.get(clanId);
	}
	
	@Override
	public L2SiegeClan getAttackerClan(L2Clan clan)
	{
		return getAttackerClan(clan.getId());
	}
	
	@Override
	public Collection<L2SiegeClan> getAttackerClans()
	{
		return Collections.unmodifiableCollection(_attackers.values());
	}
	
	@Override
	public List<L2PcInstance> getAttackersInZone()
	{
		final List<L2PcInstance> attackers = new ArrayList<>();
		for (L2PcInstance pc : _hall.getSiegeZone().getPlayersInside())
		{
			final L2Clan clan = pc.getClan();
			if ((clan != null) && _attackers.containsKey(clan.getId()))
			{
				attackers.add(pc);
			}
		}
		return attackers;
	}
	
	@Override
	public L2SiegeClan getDefenderClan(int clanId)
	{
		return null;
	}
	
	@Override
	public L2SiegeClan getDefenderClan(L2Clan clan)
	{
		return null;
	}
	
	@Override
	public List<L2SiegeClan> getDefenderClans()
	{
		return null;
	}
	
	public void prepareOwner()
	{
		if (_hall.getOwnerId() > 0)
		{
			final L2SiegeClan clan = new L2SiegeClan(_hall.getOwnerId(), SiegeClanType.ATTACKER);
			_attackers.put(clan.getClanId(), new L2SiegeClan(clan.getClanId(), SiegeClanType.ATTACKER));
		}
		
		_hall.free();
		_hall.banishForeigners();
		final SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.THE_REGISTRATION_TERM_FOR_S1_HAS_ENDED);
		msg.addString(getName());
		Broadcast.toAllOnlinePlayers(msg);
		_hall.updateSiegeStatus(SiegeStatus.WAITING_BATTLE);
		
		_siegeTask = ThreadPool.schedule(new SiegeStarts(), 3600000);
	}
	
	@Override
	public void startSiege()
	{
		if ((_attackers.size() < 1) && (_hall.getId() != 21)) // Fortress of resistance don't have attacker list
		{
			onSiegeEnds();
			_attackers.clear();
			_hall.updateNextSiege();
			_siegeTask = ThreadPool.schedule(new PrepareOwner(), _hall.getSiegeDate().getTimeInMillis());
			_hall.updateSiegeStatus(SiegeStatus.WAITING_BATTLE);
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST);
			sm.addString(_hall.getName());
			Broadcast.toAllOnlinePlayers(sm);
			return;
		}
		
		_hall.spawnDoor();
		loadGuards();
		spawnSiegeGuards();
		_hall.updateSiegeZone(true);
		
		final byte state = 1;
		for (L2SiegeClan sClan : _attackers.values())
		{
			final L2Clan clan = ClanTable.getInstance().getClan(sClan.getClanId());
			if (clan == null)
			{
				continue;
			}
			
			for (L2PcInstance pc : clan.getOnlineMembers(0))
			{
				pc.setSiegeState(state);
				pc.broadcastUserInfo();
				pc.setIsInHideoutSiege(true);
			}
		}
		
		_hall.updateSiegeStatus(SiegeStatus.RUNNING);
		onSiegeStarts();
		_siegeTask = ThreadPool.schedule(new SiegeEnds(), _hall.getSiegeLenght());
	}
	
	@Override
	public void endSiege()
	{
		final SystemMessage end = SystemMessage.getSystemMessage(SystemMessageId.THE_S1_SIEGE_HAS_FINISHED);
		end.addString(_hall.getName());
		Broadcast.toAllOnlinePlayers(end);
		
		final L2Clan winner = getWinner();
		SystemMessage finalMsg = null;
		if (_missionAccomplished && (winner != null))
		{
			_hall.setOwner(winner);
			winner.setHideoutId(_hall.getId());
			finalMsg = SystemMessage.getSystemMessage(SystemMessageId.CLAN_S1_IS_VICTORIOUS_OVER_S2_S_CASTLE_SIEGE);
			finalMsg.addString(winner.getName());
		}
		else
		{
			finalMsg = SystemMessage.getSystemMessage(SystemMessageId.THE_SIEGE_OF_S1_HAS_ENDED_IN_A_DRAW);
		}
		finalMsg.addString(_hall.getName());
		Broadcast.toAllOnlinePlayers(finalMsg);
		_missionAccomplished = false;
		
		_hall.updateSiegeZone(false);
		_hall.updateNextSiege();
		_hall.spawnDoor(false);
		_hall.banishForeigners();
		
		final byte state = 0;
		for (L2SiegeClan sClan : _attackers.values())
		{
			final L2Clan clan = ClanTable.getInstance().getClan(sClan.getClanId());
			if (clan == null)
			{
				continue;
			}
			
			for (L2PcInstance player : clan.getOnlineMembers(0))
			{
				player.setSiegeState(state);
				player.broadcastUserInfo();
				player.setIsInHideoutSiege(false);
			}
		}
		
		// Update pvp flag for winners when siege zone becomes inactive
		for (L2Character chr : _hall.getSiegeZone().getCharactersInside())
		{
			if ((chr != null) && chr.isPlayer())
			{
				chr.getActingPlayer().startPvPFlag();
			}
		}
		
		_attackers.clear();
		
		onSiegeEnds();
		
		_siegeTask = ThreadPool.schedule(new PrepareOwner(), _hall.getNextSiegeTime() - System.currentTimeMillis() - 3600000);
		LOGGER.config("Siege of " + _hall.getName() + " scheduled for " + _hall.getSiegeDate().getTime() + ".");
		
		_hall.updateSiegeStatus(SiegeStatus.REGISTERING);
		unSpawnSiegeGuards();
	}
	
	@Override
	public void updateSiege()
	{
		cancelSiegeTask();
		_siegeTask = ThreadPool.schedule(new PrepareOwner(), _hall.getNextSiegeTime() - 3600000);
		LOGGER.config(_hall.getName() + " siege scheduled for " + _hall.getSiegeDate().getTime() + ".");
	}
	
	public void cancelSiegeTask()
	{
		if (_siegeTask != null)
		{
			_siegeTask.cancel(false);
		}
	}
	
	@Override
	public Calendar getSiegeDate()
	{
		return _hall.getSiegeDate();
	}
	
	@Override
	public boolean giveFame()
	{
		return Config.CHS_ENABLE_FAME;
	}
	
	@Override
	public int getFameAmount()
	{
		return Config.CHS_FAME_AMOUNT;
	}
	
	@Override
	public int getFameFrequency()
	{
		return Config.CHS_FAME_FREQUENCY;
	}
	
	public final void broadcastNpcSay(L2Npc npc, ChatType type, NpcStringId messageId)
	{
		final NpcSay npcSay = new NpcSay(npc.getObjectId(), type, npc.getId(), messageId);
		final int sourceRegion = MapRegionManager.getInstance().getMapRegionLocId(npc);
		for (L2PcInstance pc : L2World.getInstance().getPlayers())
		{
			if ((pc != null) && (MapRegionManager.getInstance().getMapRegionLocId(pc) == sourceRegion))
			{
				pc.sendPacket(npcSay);
			}
		}
	}
	
	public Location getInnerSpawnLoc(L2PcInstance player)
	{
		return null;
	}
	
	public boolean canPlantFlag()
	{
		return true;
	}
	
	public boolean doorIsAutoAttackable()
	{
		return true;
	}
	
	public void onSiegeStarts()
	{
	}
	
	public void onSiegeEnds()
	{
	}
	
	public abstract L2Clan getWinner();
	
	public class PrepareOwner implements Runnable
	{
		@Override
		public void run()
		{
			prepareOwner();
		}
	}
	
	public class SiegeStarts implements Runnable
	{
		@Override
		public void run()
		{
			startSiege();
		}
	}
	
	public class SiegeEnds implements Runnable
	{
		@Override
		public void run()
		{
			endSiege();
		}
	}
}
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.entity.Couple;

/**
 * @author evill33t
 */
public final class CoupleManager
{
	private static final Logger LOGGER = Logger.getLogger(CoupleManager.class.getName());
	
	private final List<Couple> _couples = new CopyOnWriteArrayList<>();
	
	protected CoupleManager()
	{
		load();
	}
	
	public void reload()
	{
		_couples.clear();
		load();
	}
	
	private final void load()
	{
		try (Connection con = DatabaseFactory.getConnection();
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery("SELECT id FROM mods_wedding ORDER BY id"))
		{
			while (rs.next())
			{
				_couples.add(new Couple(rs.getInt("id")));
			}
			LOGGER.info(getClass().getSimpleName() + ": Loaded: " + _couples.size() + " couples(s)");
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: CoupleManager.load(): " + e.getMessage(), e);
		}
	}
	
	public final Couple getCouple(int coupleId)
	{
		final int index = getCoupleIndex(coupleId);
		return index >= 0 ? _couples.get(index) : null;
	}
	
	public void createCouple(L2PcInstance player1, L2PcInstance player2)
	{
		if ((player1 == null) || (player2 == null) || (player1.getPartnerId() != 0) || (player2.getPartnerId() != 0))
		{
			return;
		}
		
		final int player1id = player1.getObjectId();
		final int player2id = player2.getObjectId();
		
		final Couple couple = new Couple(player1, player2);
		_couples.add(couple);
		player1.setPartnerId(player2id);
		player2.setPartnerId(player1id);
		player1.setCoupleId(couple.getId());
		player2.setCoupleId(couple.getId());
	}
	
	public void deleteCouple(int coupleId)
	{
		final int index = getCoupleIndex(coupleId);
		final Couple couple = _couples.get(index);
		if (couple == null)
		{
			return;
		}
		final L2PcInstance player1 = L2World.getInstance().getPlayer(couple.getPlayer1Id());
		final L2PcInstance player2 = L2World.getInstance().getPlayer(couple.getPlayer2Id());
		if (player1 != null)
		{
			player1.setPartnerId(0);
			player1.setMarried(false);
			player1.setCoupleId(0);
		}
		if (player2 != null)
		{
			player2.setPartnerId(0);
			player2.setMarried(false);
			player2.setCoupleId(0);
		}
		couple.divorce();
		_couples.remove(index);
	}
	
	public final int getCoupleIndex(int coupleId)
	{
		int i = 0;
		for (Couple temp : _couples)
		{
			if ((temp != null) && (temp.getId() == coupleId))
			{
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public final List<Couple> getCouples()
	{
		return _couples;
	}
	
	public static CoupleManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final CoupleManager _instance = new CoupleManager();
	}
}

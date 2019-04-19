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
package com.l2jmobius.gameserver.instancemanager.games;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Broadcast;

public class Lottery
{
	public static final long SECOND = 1000;
	public static final long MINUTE = 60000;
	
	protected static final Logger LOGGER = Logger.getLogger(Lottery.class.getName());
	
	private static final String INSERT_LOTTERY = "INSERT INTO games(id, idnr, enddate, prize, newprize) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_PRICE = "UPDATE games SET prize=?, newprize=? WHERE id = 1 AND idnr = ?";
	private static final String UPDATE_LOTTERY = "UPDATE games SET finished=1, prize=?, newprize=?, number1=?, number2=?, prize1=?, prize2=?, prize3=? WHERE id=1 AND idnr=?";
	private static final String SELECT_LAST_LOTTERY = "SELECT idnr, prize, newprize, enddate, finished FROM games WHERE id = 1 ORDER BY idnr DESC LIMIT 1";
	private static final String SELECT_LOTTERY_ITEM = "SELECT enchant_level, custom_type2 FROM items WHERE item_id = 4442 AND custom_type1 = ?";
	private static final String SELECT_LOTTERY_TICKET = "SELECT number1, number2, prize1, prize2, prize3 FROM games WHERE id = 1 and idnr = ?";
	
	protected int _number;
	protected long _prize;
	protected boolean _isSellingTickets;
	protected boolean _isStarted;
	protected long _enddate;
	
	protected Lottery()
	{
		_number = 1;
		_prize = Config.ALT_LOTTERY_PRIZE;
		_isSellingTickets = false;
		_isStarted = false;
		_enddate = System.currentTimeMillis();
		
		if (Config.ALLOW_LOTTERY)
		{
			(new startLottery()).run();
		}
	}
	
	public static Lottery getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public int getId()
	{
		return _number;
	}
	
	public long getPrize()
	{
		return _prize;
	}
	
	public long getEndDate()
	{
		return _enddate;
	}
	
	public void increasePrize(long count)
	{
		_prize += count;
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE_PRICE))
		{
			ps.setLong(1, _prize);
			ps.setLong(2, _prize);
			ps.setInt(3, _number);
			ps.execute();
		}
		catch (SQLException e)
		{
			LOGGER.log(Level.WARNING, "Lottery: Could not increase current lottery prize: " + e.getMessage(), e);
		}
	}
	
	public boolean isSellableTickets()
	{
		return _isSellingTickets;
	}
	
	public boolean isStarted()
	{
		return _isStarted;
	}
	
	private class startLottery implements Runnable
	{
		protected startLottery()
		{
			// Do nothing
		}
		
		@Override
		public void run()
		{
			try (Connection con = DatabaseFactory.getConnection();
				Statement statement = con.createStatement();
				ResultSet rset = statement.executeQuery(SELECT_LAST_LOTTERY))
			{
				if (rset.next())
				{
					_number = rset.getInt("idnr");
					
					if (rset.getInt("finished") == 1)
					{
						_number++;
						_prize = rset.getLong("newprize");
					}
					else
					{
						_prize = rset.getLong("prize");
						_enddate = rset.getLong("enddate");
						
						if (_enddate <= (System.currentTimeMillis() + (2 * MINUTE)))
						{
							(new finishLottery()).run();
							return;
						}
						
						if (_enddate > System.currentTimeMillis())
						{
							_isStarted = true;
							ThreadPool.schedule(new finishLottery(), _enddate - System.currentTimeMillis());
							
							if (_enddate > (System.currentTimeMillis() + (12 * MINUTE)))
							{
								_isSellingTickets = true;
								ThreadPool.schedule(new stopSellingTickets(), _enddate - System.currentTimeMillis() - (10 * MINUTE));
							}
							return;
						}
					}
				}
			}
			catch (SQLException e)
			{
				LOGGER.log(Level.WARNING, "Lottery: Could not restore lottery data: " + e.getMessage(), e);
			}
			
			_isSellingTickets = true;
			_isStarted = true;
			
			Broadcast.toAllOnlinePlayers("Lottery tickets are now available for Lucky Lottery #" + _number + ".");
			final Calendar finishtime = Calendar.getInstance();
			finishtime.setTimeInMillis(_enddate);
			finishtime.set(Calendar.MINUTE, 0);
			finishtime.set(Calendar.SECOND, 0);
			
			if (finishtime.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			{
				finishtime.set(Calendar.HOUR_OF_DAY, 19);
				_enddate = finishtime.getTimeInMillis();
				_enddate += 604800000;
			}
			else
			{
				finishtime.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				finishtime.set(Calendar.HOUR_OF_DAY, 19);
				_enddate = finishtime.getTimeInMillis();
			}
			
			ThreadPool.schedule(new stopSellingTickets(), _enddate - System.currentTimeMillis() - (10 * MINUTE));
			ThreadPool.schedule(new finishLottery(), _enddate - System.currentTimeMillis());
			
			try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_LOTTERY))
			{
				ps.setInt(1, 1);
				ps.setInt(2, _number);
				ps.setLong(3, _enddate);
				ps.setLong(4, _prize);
				ps.setLong(5, _prize);
				ps.execute();
			}
			catch (SQLException e)
			{
				LOGGER.log(Level.WARNING, "Lottery: Could not store new lottery data: " + e.getMessage(), e);
			}
		}
	}
	
	private class stopSellingTickets implements Runnable
	{
		protected stopSellingTickets()
		{
			// Do nothing
		}
		
		@Override
		public void run()
		{
			_isSellingTickets = false;
			
			Broadcast.toAllOnlinePlayers(SystemMessage.getSystemMessage(SystemMessageId.LOTTERY_TICKET_SALES_HAVE_BEEN_TEMPORARILY_SUSPENDED));
		}
	}
	
	private class finishLottery implements Runnable
	{
		protected finishLottery()
		{
			// Do nothing
		}
		
		@Override
		public void run()
		{
			final int[] luckynums = new int[5];
			int luckynum = 0;
			
			for (int i = 0; i < 5; i++)
			{
				boolean found = true;
				
				while (found)
				{
					luckynum = Rnd.get(20) + 1;
					found = false;
					
					for (int j = 0; j < i; j++)
					{
						if (luckynums[j] == luckynum)
						{
							found = true;
						}
					}
				}
				
				luckynums[i] = luckynum;
			}
			
			int enchant = 0;
			int type2 = 0;
			
			for (int i = 0; i < 5; i++)
			{
				if (luckynums[i] < 17)
				{
					enchant += Math.pow(2, luckynums[i] - 1);
				}
				else
				{
					type2 += Math.pow(2, luckynums[i] - 17);
				}
			}
			
			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			int count4 = 0;
			
			try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_LOTTERY_ITEM))
			{
				ps.setInt(1, _number);
				try (ResultSet rset = ps.executeQuery())
				{
					while (rset.next())
					{
						int curenchant = rset.getInt("enchant_level") & enchant;
						int curtype2 = rset.getInt("custom_type2") & type2;
						
						if ((curenchant == 0) && (curtype2 == 0))
						{
							continue;
						}
						
						int count = 0;
						
						for (int i = 1; i <= 16; i++)
						{
							final int val = curenchant / 2;
							
							if (val != Math.round((double) curenchant / 2))
							{
								count++;
							}
							
							final int val2 = curtype2 / 2;
							
							if (val2 != ((double) curtype2 / 2))
							{
								count++;
							}
							
							curenchant = val;
							curtype2 = val2;
						}
						
						if (count == 5)
						{
							count1++;
						}
						else if (count == 4)
						{
							count2++;
						}
						else if (count == 3)
						{
							count3++;
						}
						else if (count > 0)
						{
							count4++;
						}
					}
				}
			}
			catch (SQLException e)
			{
				LOGGER.log(Level.WARNING, "Lottery: Could restore lottery data: " + e.getMessage(), e);
			}
			
			final long prize4 = count4 * Config.ALT_LOTTERY_2_AND_1_NUMBER_PRIZE;
			long prize1 = 0;
			long prize2 = 0;
			long prize3 = 0;
			
			if (count1 > 0)
			{
				prize1 = (long) (((_prize - prize4) * Config.ALT_LOTTERY_5_NUMBER_RATE) / count1);
			}
			
			if (count2 > 0)
			{
				prize2 = (long) (((_prize - prize4) * Config.ALT_LOTTERY_4_NUMBER_RATE) / count2);
			}
			
			if (count3 > 0)
			{
				prize3 = (long) (((_prize - prize4) * Config.ALT_LOTTERY_3_NUMBER_RATE) / count3);
			}
			
			final long newprize = _prize - (prize1 + prize2 + prize3 + prize4);
			SystemMessage sm;
			if (count1 > 0)
			{
				// There are winners.
				sm = SystemMessage.getSystemMessage(SystemMessageId.THE_PRIZE_AMOUNT_FOR_THE_WINNER_OF_LOTTERY_S1_IS_S2_ADENA_WE_HAVE_S3_FIRST_PRIZE_WINNERS);
				sm.addInt(_number);
				sm.addLong(_prize);
				sm.addLong(count1);
			}
			else
			{
				// There are no winners.
				sm = SystemMessage.getSystemMessage(SystemMessageId.THE_PRIZE_AMOUNT_FOR_LUCKY_LOTTERY_S1_IS_S2_ADENA_THERE_WAS_NO_FIRST_PRIZE_WINNER_IN_THIS_DRAWING_THEREFORE_THE_JACKPOT_WILL_BE_ADDED_TO_THE_NEXT_DRAWING);
				sm.addInt(_number);
				sm.addLong(_prize);
			}
			Broadcast.toAllOnlinePlayers(sm);
			
			try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_LOTTERY))
			{
				ps.setLong(1, _prize);
				ps.setLong(2, newprize);
				ps.setInt(3, enchant);
				ps.setInt(4, type2);
				ps.setLong(5, prize1);
				ps.setLong(6, prize2);
				ps.setLong(7, prize3);
				ps.setInt(8, _number);
				ps.execute();
			}
			catch (SQLException e)
			{
				LOGGER.log(Level.WARNING, "Lottery: Could not store finished lottery data: " + e.getMessage(), e);
			}
			
			ThreadPool.schedule(new startLottery(), MINUTE);
			_number++;
			
			_isStarted = false;
		}
	}
	
	public int[] decodeNumbers(int enchant, int type2)
	{
		final int res[] = new int[5];
		int id = 0;
		int nr = 1;
		
		while (enchant > 0)
		{
			final int val = enchant / 2;
			if (val != Math.round((double) enchant / 2))
			{
				res[id++] = nr;
			}
			enchant /= 2;
			nr++;
		}
		
		nr = 17;
		
		while (type2 > 0)
		{
			final int val = type2 / 2;
			if (val != ((double) type2 / 2))
			{
				res[id++] = nr;
			}
			type2 /= 2;
			nr++;
		}
		
		return res;
	}
	
	public long[] checkTicket(L2ItemInstance item)
	{
		return checkTicket(item.getCustomType1(), item.getEnchantLevel(), item.getCustomType2());
	}
	
	public long[] checkTicket(int id, int enchant, int type2)
	{
		final long res[] =
		{
			0,
			0
		};
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_LOTTERY_TICKET))
		{
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					int curenchant = rs.getInt("number1") & enchant;
					int curtype2 = rs.getInt("number2") & type2;
					
					if ((curenchant == 0) && (curtype2 == 0))
					{
						return res;
					}
					
					int count = 0;
					
					for (int i = 1; i <= 16; i++)
					{
						final int val = curenchant / 2;
						if (val != Math.round((double) curenchant / 2))
						{
							count++;
						}
						final int val2 = curtype2 / 2;
						if (val2 != ((double) curtype2 / 2))
						{
							count++;
						}
						curenchant = val;
						curtype2 = val2;
					}
					
					switch (count)
					{
						case 0:
						{
							break;
						}
						case 5:
						{
							res[0] = 1;
							res[1] = rs.getLong("prize1");
							break;
						}
						case 4:
						{
							res[0] = 2;
							res[1] = rs.getLong("prize2");
							break;
						}
						case 3:
						{
							res[0] = 3;
							res[1] = rs.getLong("prize3");
							break;
						}
						default:
						{
							res[0] = 4;
							res[1] = Config.ALT_LOTTERY_2_AND_1_NUMBER_PRIZE;
						}
					}
				}
			}
		}
		catch (SQLException e)
		{
			LOGGER.log(Level.WARNING, "Lottery: Could not check lottery ticket #" + id + ": " + e.getMessage(), e);
		}
		return res;
	}
	
	private static class SingletonHolder
	{
		protected static final Lottery _instance = new Lottery();
	}
}

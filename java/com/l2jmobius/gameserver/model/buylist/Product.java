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
package com.l2jmobius.gameserver.model.buylist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.gameserver.model.items.L2Item;

/**
 * @author NosBit
 */
public final class Product
{
	private static final Logger LOGGER = Logger.getLogger(Product.class.getName());
	
	private final int _buyListId;
	private final L2Item _item;
	private final long _price;
	private final long _restockDelay;
	private final long _maxCount;
	private AtomicLong _count = null;
	private ScheduledFuture<?> _restockTask = null;
	
	public Product(int buyListId, L2Item item, long price, long restockDelay, long maxCount)
	{
		_buyListId = buyListId;
		_item = item;
		_price = price;
		_restockDelay = restockDelay * 60000;
		_maxCount = maxCount;
		if (hasLimitedStock())
		{
			_count = new AtomicLong(maxCount);
		}
	}
	
	public int getBuyListId()
	{
		return _buyListId;
	}
	
	public L2Item getItem()
	{
		return _item;
	}
	
	public int getItemId()
	{
		return _item.getId();
	}
	
	public long getPrice()
	{
		return _price < 0 ? _item.getReferencePrice() : _price;
	}
	
	public long getRestockDelay()
	{
		return _restockDelay;
	}
	
	public long getMaxCount()
	{
		return _maxCount;
	}
	
	public long getCount()
	{
		if (_count == null)
		{
			return 0;
		}
		final long count = _count.get();
		return count > 0 ? count : 0;
	}
	
	public void setCount(long currentCount)
	{
		if (_count == null)
		{
			_count = new AtomicLong();
		}
		_count.set(currentCount);
	}
	
	public boolean decreaseCount(long val)
	{
		if (_count == null)
		{
			return false;
		}
		if ((_restockTask == null) || _restockTask.isDone())
		{
			_restockTask = ThreadPool.schedule(new RestockTask(), _restockDelay);
		}
		final boolean result = _count.addAndGet(-val) >= 0;
		save();
		return result;
	}
	
	public boolean hasLimitedStock()
	{
		return _maxCount > -1;
	}
	
	public void restartRestockTask(long nextRestockTime)
	{
		final long remainTime = nextRestockTime - System.currentTimeMillis();
		if (remainTime > 0)
		{
			_restockTask = ThreadPool.schedule(new RestockTask(), remainTime);
		}
		else
		{
			restock();
		}
	}
	
	public void restock()
	{
		setCount(_maxCount);
		save();
	}
	
	protected final class RestockTask implements Runnable
	{
		@Override
		public void run()
		{
			restock();
		}
	}
	
	private void save()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO `buylists`(`buylist_id`, `item_id`, `count`, `next_restock_time`) VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE `count` = ?, `next_restock_time` = ?"))
		{
			ps.setInt(1, _buyListId);
			ps.setInt(2, _item.getId());
			ps.setLong(3, getCount());
			ps.setLong(5, getCount());
			if ((_restockTask != null) && (_restockTask.getDelay(TimeUnit.MILLISECONDS) > 0))
			{
				final long nextRestockTime = System.currentTimeMillis() + _restockTask.getDelay(TimeUnit.MILLISECONDS);
				ps.setLong(4, nextRestockTime);
				ps.setLong(6, nextRestockTime);
			}
			else
			{
				ps.setLong(4, 0);
				ps.setLong(6, 0);
			}
			ps.executeUpdate();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Failed to save Product buylist_id:" + _buyListId + " item_id:" + _item.getId(), e);
		}
	}
}

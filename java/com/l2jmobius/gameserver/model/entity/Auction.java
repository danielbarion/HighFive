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
package com.l2jmobius.gameserver.model.entity;

import static com.l2jmobius.gameserver.model.itemcontainer.Inventory.ADENA_ID;
import static com.l2jmobius.gameserver.model.itemcontainer.Inventory.MAX_ADENA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.gameserver.data.sql.impl.ClanTable;
import com.l2jmobius.gameserver.enums.AuctionItemType;
import com.l2jmobius.gameserver.idfactory.IdFactory;
import com.l2jmobius.gameserver.instancemanager.ClanHallAuctionManager;
import com.l2jmobius.gameserver.instancemanager.ClanHallManager;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.ItemContainer;
import com.l2jmobius.gameserver.network.SystemMessageId;

public class Auction
{
	protected static final Logger LOGGER = Logger.getLogger(Auction.class.getName());
	private int _id = 0;
	private long _endDate;
	private int _highestBidderId = 0;
	private String _highestBidderName = "";
	private long _highestBidderMaxBid = 0;
	private int _itemId = 0;
	private String _itemName = "";
	private int _itemObjectId = 0;
	private final long _itemQuantity = 0;
	private String _itemType = "";
	private int _sellerId = 0;
	private String _sellerClanName = "";
	private String _sellerName = "";
	private long _currentBid = 0;
	private long _startingBid = 0;
	
	private final Map<Integer, Bidder> _bidders = new ConcurrentHashMap<>();
	
	private static final String[] ItemTypeName =
	{
		"ClanHall"
	};
	
	public static class Bidder
	{
		private final String _name; // TODO replace with objid
		private final String _clanName;
		private long _bid;
		private final Calendar _timeBid;
		
		public Bidder(String name, String clanName, long bid, long timeBid)
		{
			_name = name;
			_clanName = clanName;
			_bid = bid;
			_timeBid = Calendar.getInstance();
			_timeBid.setTimeInMillis(timeBid);
		}
		
		public String getName()
		{
			return _name;
		}
		
		public String getClanName()
		{
			return _clanName;
		}
		
		public long getBid()
		{
			return _bid;
		}
		
		public Calendar getTimeBid()
		{
			return _timeBid;
		}
		
		public void setTimeBid(long timeBid)
		{
			_timeBid.setTimeInMillis(timeBid);
		}
		
		public void setBid(long bid)
		{
			_bid = bid;
		}
	}
	
	/** Task Sheduler for endAuction */
	public class AutoEndTask implements Runnable
	{
		public AutoEndTask()
		{
		}
		
		@Override
		public void run()
		{
			try
			{
				endAuction();
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "", e);
			}
		}
	}
	
	/**
	 * Constructor
	 * @param auctionId
	 */
	public Auction(int auctionId)
	{
		_id = auctionId;
		load();
		startAutoTask();
	}
	
	public Auction(int itemId, L2Clan Clan, long delay, long bid, String name)
	{
		_id = itemId;
		_endDate = System.currentTimeMillis() + delay;
		_itemId = itemId;
		_itemName = name;
		_itemType = "ClanHall";
		_sellerId = Clan.getLeaderId();
		_sellerName = Clan.getLeaderName();
		_sellerClanName = Clan.getName();
		_startingBid = bid;
	}
	
	/** Load auctions */
	private void load()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("Select * from auction where id = ?"))
		{
			ps.setInt(1, _id);
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					_currentBid = rs.getLong("currentBid");
					_endDate = rs.getLong("endDate");
					_itemId = rs.getInt("itemId");
					_itemName = rs.getString("itemName");
					_itemObjectId = rs.getInt("itemObjectId");
					_itemType = rs.getString("itemType");
					_sellerId = rs.getInt("sellerId");
					_sellerClanName = rs.getString("sellerClanName");
					_sellerName = rs.getString("sellerName");
					_startingBid = rs.getLong("startingBid");
				}
			}
			loadBid();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception: Auction.load(): " + e.getMessage(), e);
		}
	}
	
	/** Load bidders **/
	private void loadBid()
	{
		_highestBidderId = 0;
		_highestBidderName = "";
		_highestBidderMaxBid = 0;
		
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT bidderId, bidderName, maxBid, clan_name, time_bid FROM auction_bid WHERE auctionId = ? ORDER BY maxBid DESC"))
		{
			ps.setInt(1, _id);
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					if (rs.isFirst())
					{
						_highestBidderId = rs.getInt("bidderId");
						_highestBidderName = rs.getString("bidderName");
						_highestBidderMaxBid = rs.getLong("maxBid");
					}
					_bidders.put(rs.getInt("bidderId"), new Bidder(rs.getString("bidderName"), rs.getString("clan_name"), rs.getLong("maxBid"), rs.getLong("time_bid")));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception: Auction.loadBid(): " + e.getMessage(), e);
		}
	}
	
	/** Task Manage */
	private void startAutoTask()
	{
		final long currentTime = System.currentTimeMillis();
		long taskDelay = 0;
		if (_endDate <= currentTime)
		{
			_endDate = currentTime + (7 * 24 * 3600000);
			saveAuctionDate();
		}
		else
		{
			taskDelay = _endDate - currentTime;
		}
		ThreadPool.schedule(new AutoEndTask(), taskDelay);
	}
	
	public static String getItemTypeName(AuctionItemType value)
	{
		return ItemTypeName[value.ordinal()];
	}
	
	/** Save Auction Data End */
	private void saveAuctionDate()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("Update auction set endDate = ? where id = ?"))
		{
			ps.setLong(1, _endDate);
			ps.setInt(2, _id);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: saveAuctionDate(): " + e.getMessage(), e);
		}
	}
	
	/**
	 * Set a bid
	 * @param bidder
	 * @param bid
	 */
	public synchronized void setBid(L2PcInstance bidder, long bid)
	{
		final long requiredAdena = _highestBidderName.equals(bidder.getClan().getLeaderName()) ? bid - _highestBidderMaxBid : bid;
		if ((((_highestBidderId > 0) && (bid > _highestBidderMaxBid)) || ((_highestBidderId == 0) && (bid >= _startingBid))) && takeItem(bidder, requiredAdena))
		{
			updateInDB(bidder, bid);
			bidder.getClan().setAuctionBiddedAt(_id, true);
			return;
		}
		if ((bid < _startingBid) || (bid <= _highestBidderMaxBid))
		{
			bidder.sendPacket(SystemMessageId.YOUR_BID_PRICE_MUST_BE_HIGHER_THAN_THE_MINIMUM_PRICE_CURRENTLY_BEING_BID);
		}
	}
	
	/**
	 * Returns the item to the clan warehouse.
	 * @param clanName the clan name
	 * @param quantity the Adena value
	 * @param penalty if {@code true} fees are applied
	 */
	private void returnItem(String clanName, long quantity, boolean penalty)
	{
		if (penalty)
		{
			quantity *= 0.9; // take 10% tax fee if needed
		}
		
		final L2Clan clan = ClanTable.getInstance().getClanByName(clanName);
		if (clan == null)
		{
			LOGGER.warning("Clan " + clanName + " doesn't exist!");
			return;
		}
		
		final ItemContainer cwh = clan.getWarehouse();
		if (cwh == null)
		{
			LOGGER.warning("There has been a problem with " + clanName + "'s clan warehouse!");
			return;
		}
		
		// avoid overflow on return
		final long limit = MAX_ADENA - cwh.getAdena();
		quantity = Math.min(quantity, limit);
		
		cwh.addItem("Outbidded", ADENA_ID, quantity, null, null);
	}
	
	/**
	 * Take Item in WHC
	 * @param bidder
	 * @param quantity
	 * @return
	 */
	private boolean takeItem(L2PcInstance bidder, long quantity)
	{
		if ((bidder.getClan() != null) && (bidder.getClan().getWarehouse().getAdena() >= quantity))
		{
			bidder.getClan().getWarehouse().destroyItemByItemId("Buy", ADENA_ID, quantity, bidder, bidder);
			return true;
		}
		bidder.sendPacket(SystemMessageId.THERE_IS_NOT_ENOUGH_ADENA_IN_THE_CLAN_HALL_WAREHOUSE);
		return false;
	}
	
	/**
	 * Update auction in DB
	 * @param bidder
	 * @param bid
	 */
	private void updateInDB(L2PcInstance bidder, long bid)
	{
		try (Connection con = DatabaseFactory.getConnection())
		{
			if (_bidders.get(bidder.getClanId()) != null)
			{
				try (PreparedStatement ps = con.prepareStatement("UPDATE auction_bid SET bidderId=?, bidderName=?, maxBid=?, time_bid=? WHERE auctionId=? AND bidderId=?"))
				{
					ps.setInt(1, bidder.getClanId());
					ps.setString(2, bidder.getClan().getLeaderName());
					ps.setLong(3, bid);
					ps.setLong(4, System.currentTimeMillis());
					ps.setInt(5, _id);
					ps.setInt(6, bidder.getClanId());
					ps.execute();
				}
			}
			else
			{
				try (PreparedStatement ps = con.prepareStatement("INSERT INTO auction_bid (id, auctionId, bidderId, bidderName, maxBid, clan_name, time_bid) VALUES (?, ?, ?, ?, ?, ?, ?)"))
				{
					ps.setInt(1, IdFactory.getInstance().getNextId());
					ps.setInt(2, _id);
					ps.setInt(3, bidder.getClanId());
					ps.setString(4, bidder.getName());
					ps.setLong(5, bid);
					ps.setString(6, bidder.getClan().getName());
					ps.setLong(7, System.currentTimeMillis());
					ps.execute();
				}
				if (L2World.getInstance().getPlayer(_highestBidderName) != null)
				{
					L2World.getInstance().getPlayer(_highestBidderName).sendMessage("You have been out bidded");
				}
			}
			
			_highestBidderId = bidder.getClanId();
			_highestBidderMaxBid = bid;
			_highestBidderName = bidder.getClan().getLeaderName();
			if (_bidders.get(_highestBidderId) == null)
			{
				_bidders.put(_highestBidderId, new Bidder(_highestBidderName, bidder.getClan().getName(), bid, Calendar.getInstance().getTimeInMillis()));
			}
			else
			{
				_bidders.get(_highestBidderId).setBid(bid);
				_bidders.get(_highestBidderId).setTimeBid(Calendar.getInstance().getTimeInMillis());
			}
			bidder.sendPacket(SystemMessageId.YOUR_BID_HAS_BEEN_SUCCESSFULLY_PLACED);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: Auction.updateInDB(L2PcInstance bidder, int bid): " + e.getMessage(), e);
		}
	}
	
	/** Remove bids */
	private void removeBids()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM auction_bid WHERE auctionId=?"))
		{
			ps.setInt(1, _id);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: Auction.deleteFromDB(): " + e.getMessage(), e);
		}
		
		for (Bidder b : _bidders.values())
		{
			if (ClanTable.getInstance().getClanByName(b.getClanName()).getHideoutId() == 0)
			{
				returnItem(b.getClanName(), b.getBid(), true); // 10 % tax
			}
			else if (L2World.getInstance().getPlayer(b.getName()) != null)
			{
				L2World.getInstance().getPlayer(b.getName()).sendMessage("Congratulation you have won ClanHall!");
			}
			ClanTable.getInstance().getClanByName(b.getClanName()).setAuctionBiddedAt(0, true);
		}
		_bidders.clear();
	}
	
	/** Remove auctions */
	public void deleteAuctionFromDB()
	{
		ClanHallAuctionManager.getInstance().getAuctions().remove(this);
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM auction WHERE itemId=?"))
		{
			ps.setInt(1, _itemId);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: Auction.deleteFromDB(): " + e.getMessage(), e);
		}
	}
	
	/** End of auction */
	public void endAuction()
	{
		if (ClanHallManager.getInstance().loaded())
		{
			if ((_highestBidderId == 0) && (_sellerId == 0))
			{
				startAutoTask();
				return;
			}
			if ((_highestBidderId == 0) && (_sellerId > 0))
			{
				/**
				 * If seller haven't sell ClanHall, auction removed, THIS MUST BE CONFIRMED
				 */
				final int aucId = ClanHallAuctionManager.getInstance().getAuctionIndex(_id);
				ClanHallAuctionManager.getInstance().getAuctions().remove(aucId);
				return;
			}
			if (_sellerId > 0)
			{
				returnItem(_sellerClanName, _highestBidderMaxBid, true);
				returnItem(_sellerClanName, ClanHallManager.getInstance().getAuctionableHallById(_itemId).getLease(), false);
			}
			deleteAuctionFromDB();
			final L2Clan Clan = ClanTable.getInstance().getClanByName(_bidders.get(_highestBidderId).getClanName());
			_bidders.remove(_highestBidderId);
			Clan.setAuctionBiddedAt(0, true);
			removeBids();
			ClanHallManager.getInstance().setOwner(_itemId, Clan);
		}
		else
		{
			/** Task waiting ClanHallManager is loaded every 3s */
			ThreadPool.schedule(new AutoEndTask(), 3000);
		}
	}
	
	/**
	 * Cancel bid
	 * @param bidder
	 */
	public synchronized void cancelBid(int bidder)
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM auction_bid WHERE auctionId=? AND bidderId=?"))
		{
			ps.setInt(1, _id);
			ps.setInt(2, bidder);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: Auction.cancelBid(String bidder): " + e.getMessage(), e);
		}
		
		returnItem(_bidders.get(bidder).getClanName(), _bidders.get(bidder).getBid(), true);
		ClanTable.getInstance().getClanByName(_bidders.get(bidder).getClanName()).setAuctionBiddedAt(0, true);
		_bidders.clear();
		loadBid();
	}
	
	/** Cancel auction */
	public void cancelAuction()
	{
		deleteAuctionFromDB();
		removeBids();
	}
	
	/** Confirm an auction */
	public void confirmAuction()
	{
		ClanHallAuctionManager.getInstance().getAuctions().add(this);
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO auction (id, sellerId, sellerName, sellerClanName, itemType, itemId, itemObjectId, itemName, itemQuantity, startingBid, currentBid, endDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"))
		{
			ps.setInt(1, _id);
			ps.setInt(2, _sellerId);
			ps.setString(3, _sellerName);
			ps.setString(4, _sellerClanName);
			ps.setString(5, _itemType);
			ps.setInt(6, _itemId);
			ps.setInt(7, _itemObjectId);
			ps.setString(8, _itemName);
			ps.setLong(9, _itemQuantity);
			ps.setLong(10, _startingBid);
			ps.setLong(11, _currentBid);
			ps.setLong(12, _endDate);
			ps.execute();
			ps.close();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception: Auction.load(): " + e.getMessage(), e);
		}
	}
	
	/**
	 * Get var auction
	 * @return
	 */
	public final int getId()
	{
		return _id;
	}
	
	public final long getCurrentBid()
	{
		return _currentBid;
	}
	
	public final long getEndDate()
	{
		return _endDate;
	}
	
	public final int getHighestBidderId()
	{
		return _highestBidderId;
	}
	
	public final String getHighestBidderName()
	{
		return _highestBidderName;
	}
	
	public final long getHighestBidderMaxBid()
	{
		return _highestBidderMaxBid;
	}
	
	public final int getItemId()
	{
		return _itemId;
	}
	
	public final String getItemName()
	{
		return _itemName;
	}
	
	public final int getItemObjectId()
	{
		return _itemObjectId;
	}
	
	public final long getItemQuantity()
	{
		return _itemQuantity;
	}
	
	public final String getItemType()
	{
		return _itemType;
	}
	
	public final int getSellerId()
	{
		return _sellerId;
	}
	
	public final String getSellerName()
	{
		return _sellerName;
	}
	
	public final String getSellerClanName()
	{
		return _sellerClanName;
	}
	
	public final long getStartingBid()
	{
		return _startingBid;
	}
	
	public final Map<Integer, Bidder> getBidders()
	{
		return _bidders;
	}
}

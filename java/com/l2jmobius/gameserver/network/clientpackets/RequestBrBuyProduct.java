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
package com.l2jmobius.gameserver.network.clientpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;

import com.l2jmobius.Config;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.gameserver.data.xml.impl.PrimeShopData;
import com.l2jmobius.gameserver.datatables.ItemTable;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.PrimeShopProductHolder;
import com.l2jmobius.gameserver.model.items.L2Item;
import com.l2jmobius.gameserver.network.L2GameClient;
import com.l2jmobius.gameserver.network.serverpackets.ExBrBuyProduct;
import com.l2jmobius.gameserver.network.serverpackets.ExBrGamePoint;
import com.l2jmobius.gameserver.network.serverpackets.StatusUpdate;

/**
 * @author Mobius
 */
public class RequestBrBuyProduct implements IClientIncomingPacket
{
	private int _productId;
	private int _count;
	
	@Override
	public boolean read(L2GameClient client, PacketReader packet)
	{
		_productId = packet.readD();
		_count = packet.readD();
		return true;
	}
	
	@Override
	public void run(L2GameClient client)
	{
		final L2PcInstance player = client.getActiveChar();
		if (player == null)
		{
			return;
		}
		
		if ((_count > 99) || (_count < 0))
		{
			return;
		}
		
		final PrimeShopProductHolder product = PrimeShopData.getInstance().getProduct(_productId);
		if (product == null)
		{
			player.sendPacket(new ExBrBuyProduct(ExBrBuyProduct.RESULT_WRONG_PRODUCT));
			return;
		}
		
		final long totalPoints = product.getPrice() * _count;
		if (totalPoints < 0)
		{
			player.sendPacket(new ExBrBuyProduct(ExBrBuyProduct.RESULT_WRONG_PRODUCT));
			return;
		}
		
		final long gamePointSize = Config.PRIME_SHOP_ITEM_ID == -1 ? player.getGamePoints() : player.getInventory().getInventoryItemCount(Config.PRIME_SHOP_ITEM_ID, -1);
		if (totalPoints > gamePointSize)
		{
			player.sendPacket(new ExBrBuyProduct(ExBrBuyProduct.RESULT_NOT_ENOUGH_POINTS));
			return;
		}
		
		final L2Item item = ItemTable.getInstance().getTemplate(product.getItemId());
		if (item == null)
		{
			player.sendPacket(new ExBrBuyProduct(ExBrBuyProduct.RESULT_WRONG_PRODUCT));
			return;
		}
		
		final int totalWeight = product.getItemWeight() * product.getItemCount() * _count;
		int totalCount = 0;
		totalCount += item.isStackable() ? 1 : product.getItemCount() * _count;
		if (!player.getInventory().validateCapacity(totalCount) || !player.getInventory().validateWeight(totalWeight))
		{
			player.sendPacket(new ExBrBuyProduct(ExBrBuyProduct.RESULT_INVENTORY_FULL));
			return;
		}
		
		// Pay for Item
		if (Config.PRIME_SHOP_ITEM_ID == -1)
		{
			player.setGamePoints(player.getGamePoints() - totalPoints);
		}
		else
		{
			player.getInventory().destroyItemByItemId("Buy Product" + _productId, Config.PRIME_SHOP_ITEM_ID, totalPoints, player, null);
		}
		
		// Buy Item
		player.getInventory().addItem("Buy Product" + _productId, product.getItemId(), product.getItemCount() * _count, player, null);
		
		final StatusUpdate su = new StatusUpdate(player.getObjectId());
		su.addAttribute(StatusUpdate.CUR_LOAD, player.getCurrentLoad());
		player.sendPacket(su);
		
		player.sendPacket(new ExBrGamePoint(player));
		player.sendPacket(new ExBrBuyProduct(ExBrBuyProduct.RESULT_OK));
		player.broadcastUserInfo();
		
		// Save transaction info at SQL table item_mall_transactions
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO item_mall_transactions (charId, productId, quantity) values (?,?,?)"))
		{
			statement.setLong(1, player.getObjectId());
			statement.setInt(2, product.getProductId());
			statement.setLong(3, _count);
			statement.executeUpdate();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Could not save Item Mall transaction: " + e.getMessage(), e);
		}
	}
}

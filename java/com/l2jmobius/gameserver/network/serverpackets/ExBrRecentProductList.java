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
package com.l2jmobius.gameserver.network.serverpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.data.xml.impl.PrimeShopData;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.PrimeShopProductHolder;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Mobius
 */
public class ExBrRecentProductList implements IClientOutgoingPacket
{
	private final List<PrimeShopProductHolder> _itemList = new ArrayList<>();
	
	public ExBrRecentProductList(L2PcInstance player)
	{
		final int playerObj = player.getObjectId();
		
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT productId FROM item_mall_transactions WHERE charId=? ORDER BY transactionTime DESC"))
		{
			statement.setInt(1, playerObj);
			try (ResultSet rset = statement.executeQuery())
			{
				while (rset.next())
				{
					final PrimeShopProductHolder product = PrimeShopData.getInstance().getProduct(rset.getInt("productId"));
					if ((product != null) && !_itemList.contains(product))
					{
						_itemList.add(product);
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Could not restore Item Mall transaction: " + e.getMessage(), e);
		}
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		if ((_itemList == null) || _itemList.isEmpty())
		{
			return false;
		}
		
		OutgoingPackets.EX_BR_RECENT_PRODUCT_LIST.writeId(packet);
		packet.writeD(_itemList.size());
		
		for (PrimeShopProductHolder product : _itemList)
		{
			packet.writeD(product.getProductId());
			packet.writeH(product.getCategory());
			packet.writeD(product.getPrice());
			packet.writeD(0x00); // category
			
			packet.writeD(0x00); // start sale
			packet.writeD(0x00); // end sale
			packet.writeC(0x00); // day week
			packet.writeC(0x00); // start hour
			packet.writeC(0x00); // start min
			packet.writeC(0x00); // end hour
			packet.writeC(0x00); // end min
			packet.writeD(0x00); // current stock
			packet.writeD(0x00); // max stock
		}
		return true;
	}
}

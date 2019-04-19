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

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.data.xml.impl.PrimeShopData;
import com.l2jmobius.gameserver.model.holders.PrimeShopProductHolder;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Mobius
 */
public class ExBrProductInfo implements IClientOutgoingPacket
{
	private final PrimeShopProductHolder _product;
	
	public ExBrProductInfo(int id)
	{
		_product = PrimeShopData.getInstance().getProduct(id);
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		if (_product == null)
		{
			return false;
		}
		
		OutgoingPackets.EX_BR_PRODUCT_INFO.writeId(packet);
		
		packet.writeD(_product.getProductId()); // product id
		packet.writeD(_product.getPrice()); // points
		packet.writeD(1); // components size
		packet.writeD(_product.getItemId()); // item id
		packet.writeD(_product.getItemCount()); // quality
		packet.writeD(_product.getItemWeight()); // weight
		packet.writeD(_product.isTradable() ? 1 : 0); // 0 - dont drop/trade
		return true;
	}
}

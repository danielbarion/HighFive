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

import java.util.Collection;

import com.l2jmobius.Config;
import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.model.buylist.L2BuyList;
import com.l2jmobius.gameserver.model.buylist.Product;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public final class BuyList implements IClientOutgoingPacket
{
	private final int _listId;
	private final Collection<Product> _list;
	private final long _money;
	private double _taxRate = 0;
	
	public BuyList(L2BuyList list, long currentMoney, double taxRate)
	{
		_listId = list.getListId();
		_list = list.getProducts();
		_money = currentMoney;
		_taxRate = taxRate;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_BUY_SELL_LIST.writeId(packet);
		packet.writeD(0x00);
		packet.writeQ(_money); // current money
		packet.writeD(_listId);
		
		packet.writeH(_list.size());
		
		for (Product product : _list)
		{
			if ((product.getCount() > 0) || !product.hasLimitedStock())
			{
				packet.writeD(product.getItemId());
				packet.writeD(product.getItemId());
				packet.writeD(0);
				packet.writeQ(product.getCount() < 0 ? 0 : product.getCount());
				packet.writeH(product.getItem().getType2());
				packet.writeH(product.getItem().getType1()); // Custom Type 1
				packet.writeH(0x00); // isEquipped
				packet.writeD(product.getItem().getBodyPart()); // Body Part
				packet.writeH(product.getItem().getDefaultEnchantLevel()); // Enchant
				packet.writeH(0x00); // Custom Type
				packet.writeD(0x00); // Augment
				packet.writeD(-1); // Mana
				packet.writeD(-9999); // Time
				packet.writeH(0x00); // Element Type
				packet.writeH(0x00); // Element Power
				for (byte i = 0; i < 6; i++)
				{
					packet.writeH(0x00);
				}
				// Enchant Effects
				packet.writeH(0x00);
				packet.writeH(0x00);
				packet.writeH(0x00);
				
				if ((product.getItemId() >= 3960) && (product.getItemId() <= 4026))
				{
					packet.writeQ((long) (product.getPrice() * Config.RATE_SIEGE_GUARDS_PRICE * (1 + _taxRate)));
				}
				else
				{
					packet.writeQ((long) (product.getPrice() * (1 + _taxRate)));
				}
			}
		}
		return true;
	}
}

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
import com.l2jmobius.gameserver.model.ItemInfo;
import com.l2jmobius.gameserver.model.TradeItem;
import com.l2jmobius.gameserver.model.itemcontainer.PcInventory;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;

/**
 * @author UnAfraid
 */
public abstract class AbstractItemPacket implements IClientOutgoingPacket
{
	protected void writeItem(PacketWriter packet, TradeItem item)
	{
		writeItem(packet, new ItemInfo(item));
	}
	
	protected void writeItem(PacketWriter packet, L2ItemInstance item)
	{
		writeItem(packet, new ItemInfo(item));
	}
	
	protected void writeItem(PacketWriter packet, ItemInfo item)
	{
		packet.writeD(item.getObjectId()); // ObjectId
		packet.writeD(item.getItem().getDisplayId()); // ItemId
		packet.writeD(item.getLocation()); // T1
		packet.writeQ(item.getCount()); // Quantity
		packet.writeH(item.getItem().getType2()); // Item Type 2 : 00-weapon, 01-shield/armor, 02-ring/earring/necklace, 03-questitem, 04-adena, 05-item
		packet.writeH(item.getCustomType1()); // Filler (always 0)
		packet.writeH(item.getEquipped()); // Equipped : 00-No, 01-yes
		packet.writeD(item.getItem().getBodyPart()); // Slot : 0006-lr.ear, 0008-neck, 0030-lr.finger, 0040-head, 0100-l.hand, 0200-gloves, 0400-chest, 0800-pants, 1000-feet, 4000-r.hand, 8000-r.hand
		packet.writeH(item.getEnchant()); // Enchant level (pet level shown in control item)
		packet.writeH(item.getCustomType2()); // Pet name exists or not shown in control item
		packet.writeD(item.getAugmentationBonus());
		packet.writeD(item.getMana());
		packet.writeD(item.getTime());
		writeItemElementalAndEnchant(packet, item);
	}
	
	protected void writeItemElementalAndEnchant(PacketWriter packet, ItemInfo item)
	{
		packet.writeH(item.getAttackElementType());
		packet.writeH(item.getAttackElementPower());
		for (byte i = 0; i < 6; i++)
		{
			packet.writeH(item.getElementDefAttr(i));
		}
		// Enchant Effects
		for (int op : item.getEnchantOptions())
		{
			packet.writeH(op);
		}
	}
	
	protected void writeInventoryBlock(PacketWriter packet, PcInventory inventory)
	{
		if (inventory.hasInventoryBlock())
		{
			packet.writeH(inventory.getBlockItems().length);
			packet.writeC(inventory.getBlockMode());
			for (int i : inventory.getBlockItems())
			{
				packet.writeD(i);
			}
		}
		else
		{
			packet.writeH(0x00);
		}
	}
}

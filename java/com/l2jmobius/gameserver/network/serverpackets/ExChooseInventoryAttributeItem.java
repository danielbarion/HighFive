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
import com.l2jmobius.gameserver.model.Elementals;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Kerberos
 */
public class ExChooseInventoryAttributeItem implements IClientOutgoingPacket
{
	private final int _itemId;
	private final byte _atribute;
	private final int _level;
	
	public ExChooseInventoryAttributeItem(L2ItemInstance item)
	{
		_itemId = item.getDisplayId();
		_atribute = Elementals.getItemElement(_itemId);
		if (_atribute == Elementals.NONE)
		{
			throw new IllegalArgumentException("Undefined Atribute item: " + item);
		}
		_level = Elementals.getMaxElementLevel(_itemId);
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_CHOOSE_INVENTORY_ATTRIBUTE_ITEM.writeId(packet);
		packet.writeD(_itemId);
		// Structure for now
		// Must be 0x01 for stone/crystal attribute type
		packet.writeD(_atribute == Elementals.FIRE ? 1 : 0); // Fire
		packet.writeD(_atribute == Elementals.WATER ? 1 : 0); // Water
		packet.writeD(_atribute == Elementals.WIND ? 1 : 0); // Wind
		packet.writeD(_atribute == Elementals.EARTH ? 1 : 0); // Earth
		packet.writeD(_atribute == Elementals.HOLY ? 1 : 0); // Holy
		packet.writeD(_atribute == Elementals.DARK ? 1 : 0); // Unholy
		packet.writeD(_level); // Item max attribute level
		return true;
	}
}

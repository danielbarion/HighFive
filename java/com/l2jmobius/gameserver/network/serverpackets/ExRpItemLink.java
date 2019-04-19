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
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author KenM
 */
public final class ExRpItemLink implements IClientOutgoingPacket
{
	private final L2ItemInstance _item;
	
	public ExRpItemLink(L2ItemInstance item)
	{
		_item = item;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_RP_ITEM_LINK.writeId(packet);
		packet.writeD(_item.getObjectId());
		packet.writeD(_item.getDisplayId());
		packet.writeD(_item.getLocationSlot());
		packet.writeQ(_item.getCount());
		packet.writeH(_item.getItem().getType2());
		packet.writeH(_item.getCustomType1());
		packet.writeH(_item.isEquipped() ? 0x01 : 0x00);
		packet.writeD(_item.getItem().getBodyPart());
		packet.writeH(_item.getEnchantLevel());
		packet.writeH(_item.getCustomType2());
		if (_item.isAugmented())
		{
			packet.writeD(_item.getAugmentation().getAugmentationId());
		}
		else
		{
			packet.writeD(0x00);
		}
		packet.writeD(_item.getMana());
		packet.writeD(_item.isTimeLimitedItem() ? (int) (_item.getRemainingTime() / 1000) : -9999);
		packet.writeH(_item.getAttackElementType());
		packet.writeH(_item.getAttackElementPower());
		for (byte i = 0; i < 6; i++)
		{
			packet.writeH(_item.getElementDefAttr(i));
		}
		// Enchant Effects
		for (int op : _item.getEnchantOptions())
		{
			packet.writeH(op);
		}
		return true;
	}
}

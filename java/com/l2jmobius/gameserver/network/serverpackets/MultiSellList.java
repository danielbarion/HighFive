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

import static com.l2jmobius.gameserver.data.xml.impl.MultisellData.PAGE_SIZE;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.model.multisell.Entry;
import com.l2jmobius.gameserver.model.multisell.Ingredient;
import com.l2jmobius.gameserver.model.multisell.ListContainer;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public final class MultiSellList implements IClientOutgoingPacket
{
	private int _size;
	private int _index;
	private final ListContainer _list;
	private final boolean _finished;
	
	public MultiSellList(ListContainer list, int index)
	{
		_list = list;
		_index = index;
		_size = list.getEntries().size() - index;
		if (_size > PAGE_SIZE)
		{
			_finished = false;
			_size = PAGE_SIZE;
		}
		else
		{
			_finished = true;
		}
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.MULTI_SELL_LIST.writeId(packet);
		packet.writeD(_list.getListId()); // list id
		packet.writeD(1 + (_index / PAGE_SIZE)); // page started from 1
		packet.writeD(_finished ? 1 : 0); // finished
		packet.writeD(PAGE_SIZE); // size of pages
		packet.writeD(_size); // list length
		
		Entry ent;
		while (_size-- > 0)
		{
			ent = _list.getEntries().get(_index++);
			packet.writeD(ent.getEntryId());
			packet.writeC(ent.isStackable() ? 1 : 0);
			packet.writeH(0x00); // C6
			packet.writeD(0x00); // C6
			packet.writeD(0x00); // T1
			packet.writeH(65534); // T1
			packet.writeH(0x00); // T1
			packet.writeH(0x00); // T1
			packet.writeH(0x00); // T1
			packet.writeH(0x00); // T1
			packet.writeH(0x00); // T1
			packet.writeH(0x00); // T1
			packet.writeH(0x00); // T1
			
			packet.writeH(ent.getProducts().size());
			packet.writeH(ent.getIngredients().size());
			
			for (Ingredient ing : ent.getProducts())
			{
				packet.writeD(ing.getItemId());
				if (ing.getTemplate() != null)
				{
					packet.writeD(ing.getTemplate().getBodyPart());
					packet.writeH(ing.getTemplate().getType2());
				}
				else
				{
					packet.writeD(0);
					packet.writeH(65535);
				}
				packet.writeQ(ing.getItemCount());
				if (ing.getItemInfo() != null)
				{
					packet.writeH(ing.getItemInfo().getEnchantLevel()); // enchant level
					packet.writeD(ing.getItemInfo().getAugmentId()); // augment id
					packet.writeD(0x00); // mana
					packet.writeH(ing.getItemInfo().getElementId()); // attack element
					packet.writeH(ing.getItemInfo().getElementPower()); // element power
					packet.writeH(ing.getItemInfo().getElementals()[0]); // fire
					packet.writeH(ing.getItemInfo().getElementals()[1]); // water
					packet.writeH(ing.getItemInfo().getElementals()[2]); // wind
					packet.writeH(ing.getItemInfo().getElementals()[3]); // earth
					packet.writeH(ing.getItemInfo().getElementals()[4]); // holy
					packet.writeH(ing.getItemInfo().getElementals()[5]); // dark
				}
				else
				{
					packet.writeH(ing.getEnchantLevel()); // enchant level
					packet.writeD(0x00); // augment id
					packet.writeD(0x00); // mana
					packet.writeH(0x00); // attack element
					packet.writeH(0x00); // element power
					packet.writeH(0x00); // fire
					packet.writeH(0x00); // water
					packet.writeH(0x00); // wind
					packet.writeH(0x00); // earth
					packet.writeH(0x00); // holy
					packet.writeH(0x00); // dark
				}
			}
			
			for (Ingredient ing : ent.getIngredients())
			{
				packet.writeD(ing.getItemId());
				packet.writeH(ing.getTemplate() != null ? ing.getTemplate().getType2() : 65535);
				packet.writeQ(ing.getItemCount());
				if (ing.getItemInfo() != null)
				{
					packet.writeH(ing.getItemInfo().getEnchantLevel()); // enchant level
					packet.writeD(ing.getItemInfo().getAugmentId()); // augment id
					packet.writeD(0x00); // mana
					packet.writeH(ing.getItemInfo().getElementId()); // attack element
					packet.writeH(ing.getItemInfo().getElementPower()); // element power
					packet.writeH(ing.getItemInfo().getElementals()[0]); // fire
					packet.writeH(ing.getItemInfo().getElementals()[1]); // water
					packet.writeH(ing.getItemInfo().getElementals()[2]); // wind
					packet.writeH(ing.getItemInfo().getElementals()[3]); // earth
					packet.writeH(ing.getItemInfo().getElementals()[4]); // holy
					packet.writeH(ing.getItemInfo().getElementals()[5]); // dark
				}
				else
				{
					packet.writeH(ing.getEnchantLevel()); // enchant level
					packet.writeD(0x00); // augment id
					packet.writeD(0x00); // mana
					packet.writeH(0x00); // attack element
					packet.writeH(0x00); // element power
					packet.writeH(0x00); // fire
					packet.writeH(0x00); // water
					packet.writeH(0x00); // wind
					packet.writeH(0x00); // earth
					packet.writeH(0x00); // holy
					packet.writeH(0x00); // dark
				}
			}
		}
		return true;
	}
}

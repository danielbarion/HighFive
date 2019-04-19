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
 * @author -Wooden-
 * @author UnAfraid, mrTJO
 */
public class PackageSendableList extends AbstractItemPacket
{
	private final L2ItemInstance[] _items;
	private final int _playerObjId;
	private final long _adena;
	
	public PackageSendableList(L2ItemInstance[] items, int playerObjId, long adena)
	{
		_items = items;
		_playerObjId = playerObjId;
		_adena = adena;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.PACKAGE_SENDABLE_LIST.writeId(packet);
		packet.writeD(_playerObjId);
		packet.writeQ(_adena);
		packet.writeD(_items.length);
		for (L2ItemInstance item : _items)
		{
			writeItem(packet, item);
			packet.writeD(item.getObjectId());
		}
		return true;
	}
}

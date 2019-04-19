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

import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.network.L2GameClient;
import com.l2jmobius.gameserver.network.serverpackets.PackageSendableList;

/**
 * @author -Wooden-
 * @author UnAfraid Thanks mrTJO
 */
public class RequestPackageSendableItemList implements IClientIncomingPacket
{
	private int _objectID;
	
	@Override
	public boolean read(L2GameClient client, PacketReader packet)
	{
		_objectID = packet.readD();
		return true;
	}
	
	@Override
	public void run(L2GameClient client)
	{
		final L2ItemInstance[] items = client.getActiveChar().getInventory().getAvailableItems(true, true, true);
		client.sendPacket(new PackageSendableList(items, _objectID, client.getActiveChar().getAdena()));
	}
}

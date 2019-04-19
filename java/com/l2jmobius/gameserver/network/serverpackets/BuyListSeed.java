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

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.instancemanager.CastleManorManager;
import com.l2jmobius.gameserver.model.SeedProduction;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author l3x
 */
public final class BuyListSeed implements IClientOutgoingPacket
{
	private final int _manorId;
	private final long _money;
	private final List<SeedProduction> _list = new ArrayList<>();
	
	public BuyListSeed(long currentMoney, int castleId)
	{
		_money = currentMoney;
		_manorId = castleId;
		
		for (SeedProduction s : CastleManorManager.getInstance().getSeedProduction(castleId, false))
		{
			if ((s.getAmount() > 0) && (s.getPrice() > 0))
			{
				_list.add(s);
			}
		}
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.BUY_LIST_SEED.writeId(packet);
		
		packet.writeQ(_money); // current money
		packet.writeD(_manorId); // manor id
		
		if (!_list.isEmpty())
		{
			packet.writeH(_list.size()); // list length
			for (SeedProduction s : _list)
			{
				packet.writeD(s.getId());
				packet.writeD(s.getId());
				packet.writeD(0x00);
				packet.writeQ(s.getAmount()); // item count
				packet.writeH(0x05); // Custom Type 2
				packet.writeH(0x00); // Custom Type 1
				packet.writeH(0x00); // Equipped
				packet.writeD(0x00); // Body Part
				packet.writeH(0x00); // Enchant
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
				packet.writeQ(s.getPrice()); // price
			}
			_list.clear();
		}
		else
		{
			packet.writeH(0x00);
		}
		return true;
	}
}
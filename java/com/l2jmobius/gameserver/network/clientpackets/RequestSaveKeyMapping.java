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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.gameserver.data.xml.impl.UIData;
import com.l2jmobius.gameserver.model.ActionKey;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.ConnectionState;
import com.l2jmobius.gameserver.network.L2GameClient;

/**
 * Request Save Key Mapping client packet.
 * @author mrTJO, Zoey76
 */
public class RequestSaveKeyMapping implements IClientIncomingPacket
{
	private final Map<Integer, List<ActionKey>> _keyMap = new HashMap<>();
	private final Map<Integer, List<Integer>> _catMap = new HashMap<>();
	
	@Override
	public boolean read(L2GameClient client, PacketReader packet)
	{
		int category = 0;
		
		packet.readD(); // Unknown
		packet.readD(); // Unknown
		final int _tabNum = packet.readD();
		for (int i = 0; i < _tabNum; i++)
		{
			final int cmd1Size = packet.readC();
			for (int j = 0; j < cmd1Size; j++)
			{
				UIData.addCategory(_catMap, category, packet.readC());
			}
			category++;
			
			final int cmd2Size = packet.readC();
			for (int j = 0; j < cmd2Size; j++)
			{
				UIData.addCategory(_catMap, category, packet.readC());
			}
			category++;
			
			final int cmdSize = packet.readD();
			for (int j = 0; j < cmdSize; j++)
			{
				final int cmd = packet.readD();
				final int key = packet.readD();
				final int tgKey1 = packet.readD();
				final int tgKey2 = packet.readD();
				final int show = packet.readD();
				UIData.addKey(_keyMap, i, new ActionKey(i, cmd, key, tgKey1, tgKey2, show));
			}
		}
		packet.readD();
		packet.readD();
		return true;
	}
	
	@Override
	public void run(L2GameClient client)
	{
		final L2PcInstance player = client.getActiveChar();
		if (!Config.STORE_UI_SETTINGS || (player == null) || (client.getConnectionState() != ConnectionState.IN_GAME))
		{
			return;
		}
		player.getUISettings().storeAll(_catMap, _keyMap);
	}
}

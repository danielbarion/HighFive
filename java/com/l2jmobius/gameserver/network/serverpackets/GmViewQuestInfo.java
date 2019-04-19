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

import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Tempy
 */
public class GmViewQuestInfo implements IClientOutgoingPacket
{
	private final L2PcInstance _activeChar;
	
	public GmViewQuestInfo(L2PcInstance cha)
	{
		_activeChar = cha;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.GM_VIEW_QUEST_INFO.writeId(packet);
		packet.writeS(_activeChar.getName());
		
		final List<Quest> questList = _activeChar.getAllActiveQuests();
		
		if (questList.size() == 0)
		{
			packet.writeC(0);
			packet.writeH(0);
			packet.writeH(0);
			return true;
		}
		
		packet.writeH(questList.size()); // quest count
		
		for (Quest q : questList)
		{
			packet.writeD(q.getId());
			
			final QuestState qs = _activeChar.getQuestState(q.getName());
			
			if (qs == null)
			{
				packet.writeD(0);
				continue;
			}
			
			packet.writeD(qs.getInt("cond")); // stage of quest progress
		}
		return true;
	}
}

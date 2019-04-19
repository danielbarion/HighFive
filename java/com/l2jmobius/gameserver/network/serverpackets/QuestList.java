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

public class QuestList implements IClientOutgoingPacket
{
	final L2PcInstance _player;
	
	public QuestList(L2PcInstance player)
	{
		_player = player;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		/**
		 * <pre>
		 * This text was wrote by XaKa
		 * QuestList packet structure:
		 * {
		 * 		1 byte - 0x80
		 * 		2 byte - Number of Quests
		 * 		for Quest in AvailibleQuests
		 * 		{
		 * 			4 byte - Quest ID
		 * 			4 byte - Quest Status
		 * 		}
		 * }
		 * 
		 * NOTE: The following special constructs are true for the 4-byte Quest Status:
		 * If the most significant bit is 0, this means that no progress-step got skipped.
		 * In this case, merely passing the rank of the latest step gets the client to mark
		 * it as current and mark all previous steps as complete.
		 * If the most significant bit is 1, it means that some steps may have been skipped.
		 * In that case, each bit represents a quest step (max 30) with 0 indicating that it was
		 * skipped and 1 indicating that it either got completed or is currently active (the client
		 * will automatically assume the largest step as active and all smaller ones as completed).
		 * For example, the following bit sequences will yield the same results:
		 * 1000 0000 0000 0000 0000 0011 1111 1111: Indicates some steps may be skipped but each of
		 * the first 10 steps did not get skipped and current step is the 10th.
		 * 0000 0000 0000 0000 0000 0000 0000 1010: Indicates that no steps were skipped and current is the 10th.
		 * It is speculated that the latter will be processed faster by the client, so it is preferred when no
		 * steps have been skipped.
		 * However, the sequence "1000 0000 0000 0000 0000 0010 1101 1111" indicates that the current step is
		 * the 10th but the 6th and 9th are not to be shown at all (not completed, either).
		 * </pre>
		 */
		
		final List<Quest> quests = _player.getAllActiveQuests();
		
		OutgoingPackets.QUEST_LIST.writeId(packet);
		packet.writeH(quests.size());
		for (Quest q : quests)
		{
			packet.writeD(q.getId());
			QuestState qs = _player.getQuestState(q.getName());
			if (qs == null)
			{
				packet.writeD(0);
				continue;
			}
			
			int states = qs.getInt("__compltdStateFlags");
			if (states > 0)
			{
				packet.writeD(states);
			}
			else
			{
				packet.writeD(qs.getInt("cond"));
			}
		}
		packet.writeB(new byte[128]);
		return true;
	}
}

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

import java.util.Arrays;
import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * MagicSkillLaunched server packet implementation.
 * @author UnAfraid
 */
public class MagicSkillLaunched implements IClientOutgoingPacket
{
	private final int _charObjId;
	private final int _skillId;
	private final int _skillLevel;
	private final List<L2Object> _targets;
	
	public MagicSkillLaunched(L2Character cha, int skillId, int skillLevel, L2Object... targets)
	{
		_charObjId = cha.getObjectId();
		_skillId = skillId;
		_skillLevel = skillLevel;
		
		//@formatter:off
		if (targets == null)
		{
			targets = new L2Object[] { cha };
		}
		//@formatter:on
		_targets = Arrays.asList(targets);
	}
	
	public MagicSkillLaunched(L2Character cha, int skillId, int skillLevel)
	{
		this(cha, skillId, skillId, cha);
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.MAGIC_SKILL_LAUNCHED.writeId(packet);
		packet.writeD(_charObjId);
		packet.writeD(_skillId);
		packet.writeD(_skillLevel);
		packet.writeD(_targets.size());
		for (L2Object target : _targets)
		{
			packet.writeD(target.getObjectId());
		}
		return true;
	}
}

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
import java.util.Collections;
import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.interfaces.IPositionable;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * MagicSkillUse server packet implementation.
 * @author UnAfraid, NosBit
 */
public final class MagicSkillUse implements IClientOutgoingPacket
{
	private final int _skillId;
	private final int _skillLevel;
	private final int _hitTime;
	private final int _reuseDelay;
	private final L2Character _activeChar;
	private final L2Character _target;
	private final List<Integer> _unknown = Collections.emptyList();
	private final List<Location> _groundLocations;
	
	public MagicSkillUse(L2Character cha, L2Character target, int skillId, int skillLevel, int hitTime, int reuseDelay)
	{
		_activeChar = cha;
		_target = target;
		_skillId = skillId;
		_skillLevel = skillLevel;
		_hitTime = hitTime;
		_reuseDelay = reuseDelay;
		_groundLocations = cha.isPlayer() && (cha.getActingPlayer().getCurrentSkillWorldPosition() != null) ? Arrays.asList(cha.getActingPlayer().getCurrentSkillWorldPosition()) : Collections.<Location> emptyList();
	}
	
	public MagicSkillUse(L2Character cha, int skillId, int skillLevel, int hitTime, int reuseDelay)
	{
		this(cha, cha, skillId, skillLevel, hitTime, reuseDelay);
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.MAGIC_SKILL_USE.writeId(packet);
		packet.writeD(_activeChar.getObjectId());
		packet.writeD(_target.getObjectId());
		packet.writeD(_skillId);
		packet.writeD(_skillLevel);
		packet.writeD(_hitTime);
		packet.writeD(_reuseDelay);
		packet.writeD(_activeChar.getX());
		packet.writeD(_activeChar.getY());
		packet.writeD(_activeChar.getZ());
		packet.writeH(_unknown.size()); // TODO: Implement me!
		for (int unknown : _unknown)
		{
			packet.writeH(unknown);
		}
		packet.writeH(_groundLocations.size());
		for (IPositionable target : _groundLocations)
		{
			packet.writeD(target.getX());
			packet.writeD(target.getY());
			packet.writeD(target.getZ());
		}
		packet.writeD(_target.getX());
		packet.writeD(_target.getY());
		packet.writeD(_target.getZ());
		return true;
	}
}

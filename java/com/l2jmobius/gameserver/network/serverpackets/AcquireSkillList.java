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
import com.l2jmobius.gameserver.model.base.AcquireSkillType;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * Acquire Skill List server packet implementation.
 */
public final class AcquireSkillList implements IClientOutgoingPacket
{
	private final List<Skill> _skills;
	private final AcquireSkillType _skillType;
	
	/**
	 * Private class containing learning skill information.
	 */
	private static class Skill
	{
		public int id;
		public int nextLevel;
		public int maxLevel;
		public int spCost;
		public int requirements;
		
		public Skill(int pId, int pNextLevel, int pMaxLevel, int pSpCost, int pRequirements)
		{
			id = pId;
			nextLevel = pNextLevel;
			maxLevel = pMaxLevel;
			spCost = pSpCost;
			requirements = pRequirements;
		}
	}
	
	public AcquireSkillList(AcquireSkillType type)
	{
		_skillType = type;
		_skills = new ArrayList<>();
	}
	
	public void addSkill(int id, int nextLevel, int maxLevel, int spCost, int requirements)
	{
		_skills.add(new Skill(id, nextLevel, maxLevel, spCost, requirements));
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		if (_skills.isEmpty())
		{
			return false;
		}
		
		OutgoingPackets.ACQUIRE_SKILL_LIST.writeId(packet);
		packet.writeD(_skillType.ordinal());
		packet.writeD(_skills.size());
		
		for (Skill temp : _skills)
		{
			packet.writeD(temp.id);
			packet.writeD(temp.nextLevel);
			packet.writeD(temp.maxLevel);
			packet.writeD(temp.spCost);
			packet.writeD(temp.requirements);
			if (_skillType == AcquireSkillType.SUBPLEDGE)
			{
				packet.writeD(0); // TODO: ?
			}
		}
		return true;
	}
}
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
import com.l2jmobius.gameserver.network.OutgoingPackets;

public final class SkillList implements IClientOutgoingPacket
{
	private final List<Skill> _skills = new ArrayList<>();
	
	static class Skill
	{
		public int id;
		public int level;
		public boolean passive;
		public boolean disabled;
		public boolean enchanted;
		
		Skill(int pId, int pLevel, boolean pPassive, boolean pDisabled, boolean pEnchanted)
		{
			id = pId;
			level = pLevel;
			passive = pPassive;
			disabled = pDisabled;
			enchanted = pEnchanted;
		}
	}
	
	public void addSkill(int id, int level, boolean passive, boolean disabled, boolean enchanted)
	{
		_skills.add(new Skill(id, level, passive, disabled, enchanted));
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.SKILL_LIST.writeId(packet);
		packet.writeD(_skills.size());
		
		for (Skill temp : _skills)
		{
			packet.writeD(temp.passive ? 1 : 0);
			packet.writeD(temp.level);
			packet.writeD(temp.id);
			packet.writeC(temp.disabled ? 1 : 0);
			packet.writeC(temp.enchanted ? 1 : 0);
		}
		return true;
	}
}

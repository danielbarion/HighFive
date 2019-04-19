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
package com.l2jmobius.gameserver.model;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

public final class L2EnchantSkillGroup
{
	private final int _id;
	private final List<EnchantSkillHolder> _enchantDetails = new ArrayList<>();
	
	public L2EnchantSkillGroup(int id)
	{
		_id = id;
	}
	
	public void addEnchantDetail(EnchantSkillHolder detail)
	{
		_enchantDetails.add(detail);
	}
	
	public int getId()
	{
		return _id;
	}
	
	public List<EnchantSkillHolder> getEnchantGroupDetails()
	{
		return _enchantDetails;
	}
	
	public static class EnchantSkillHolder
	{
		private final int _level;
		private final int _adenaCost;
		private final int _expCost;
		private final int _spCost;
		private final byte[] _rate;
		
		public EnchantSkillHolder(StatsSet set)
		{
			_level = set.getInt("level");
			_adenaCost = set.getInt("adena", 0);
			_expCost = set.getInt("exp", 0);
			_spCost = set.getInt("sp", 0);
			_rate = new byte[24];
			for (int i = 0; i < 24; i++)
			{
				_rate[i] = set.getByte("chance" + (76 + i), (byte) 0);
			}
		}
		
		/**
		 * @return Returns the level.
		 */
		public int getLevel()
		{
			return _level;
		}
		
		/**
		 * @return Returns the spCost.
		 */
		public int getSpCost()
		{
			return _spCost;
		}
		
		public int getExpCost()
		{
			return _expCost;
		}
		
		public int getAdenaCost()
		{
			return _adenaCost;
		}
		
		public byte getRate(L2PcInstance ply)
		{
			return ply.getLevel() < 76 ? 0 : _rate[ply.getLevel() - 76];
		}
	}
}
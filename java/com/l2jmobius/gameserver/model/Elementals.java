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

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.stats.Stats;
import com.l2jmobius.gameserver.model.stats.functions.FuncAdd;

public final class Elementals
{
	private static final Map<Integer, ElementalItems> TABLE = new HashMap<>();
	static
	{
		for (ElementalItems item : ElementalItems.values())
		{
			TABLE.put(item._itemId, item);
		}
	}
	
	public static final byte NONE = -1;
	public static final byte FIRE = 0;
	public static final byte WATER = 1;
	public static final byte WIND = 2;
	public static final byte EARTH = 3;
	public static final byte HOLY = 4;
	public static final byte DARK = 5;
	
	public static final int FIRST_WEAPON_BONUS = 20;
	public static final int NEXT_WEAPON_BONUS = 5;
	public static final int ARMOR_BONUS = 6;
	
	public static final int[] WEAPON_VALUES =
	{
		0, // Level 1
		25, // Level 2
		75, // Level 3
		150, // Level 4
		175, // Level 5
		225, // Level 6
		300, // Level 7
		325, // Level 8
		375, // Level 9
		450, // Level 10
		475, // Level 11
		525, // Level 12
		600, // Level 13
		Integer.MAX_VALUE
		// TODO: Higher stones
	};
	
	public static final int[] ARMOR_VALUES =
	{
		0, // Level 1
		12, // Level 2
		30, // Level 3
		60, // Level 4
		72, // Level 5
		90, // Level 6
		120, // Level 7
		132, // Level 8
		150, // Level 9
		180, // Level 10
		192, // Level 11
		210, // Level 12
		240, // Level 13
		Integer.MAX_VALUE
		// TODO: Higher stones
	};
	
	public enum ElementalItemType
	{
		Stone(3),
		Roughore(3),
		Crystal(6),
		Jewel(9),
		Energy(12);
		
		public int _maxLevel;
		
		private ElementalItemType(int maxLvl)
		{
			_maxLevel = maxLvl;
		}
	}
	
	public enum ElementalItems
	{
		fireStone(FIRE, 9546, ElementalItemType.Stone),
		waterStone(WATER, 9547, ElementalItemType.Stone),
		windStone(WIND, 9549, ElementalItemType.Stone),
		earthStone(EARTH, 9548, ElementalItemType.Stone),
		divineStone(HOLY, 9551, ElementalItemType.Stone),
		darkStone(DARK, 9550, ElementalItemType.Stone),
		
		fireRoughtore(FIRE, 10521, ElementalItemType.Roughore),
		waterRoughtore(WATER, 10522, ElementalItemType.Roughore),
		windRoughtore(WIND, 10524, ElementalItemType.Roughore),
		earthRoughtore(EARTH, 10523, ElementalItemType.Roughore),
		divineRoughtore(HOLY, 10526, ElementalItemType.Roughore),
		darkRoughtore(DARK, 10525, ElementalItemType.Roughore),
		
		fireCrystal(FIRE, 9552, ElementalItemType.Crystal),
		waterCrystal(WATER, 9553, ElementalItemType.Crystal),
		windCrystal(WIND, 9555, ElementalItemType.Crystal),
		earthCrystal(EARTH, 9554, ElementalItemType.Crystal),
		divineCrystal(HOLY, 9557, ElementalItemType.Crystal),
		darkCrystal(DARK, 9556, ElementalItemType.Crystal),
		
		fireJewel(FIRE, 9558, ElementalItemType.Jewel),
		waterJewel(WATER, 9559, ElementalItemType.Jewel),
		windJewel(WIND, 9561, ElementalItemType.Jewel),
		earthJewel(EARTH, 9560, ElementalItemType.Jewel),
		divineJewel(HOLY, 9563, ElementalItemType.Jewel),
		darkJewel(DARK, 9562, ElementalItemType.Jewel),
		
		// not yet supported by client (Freya pts)
		fireEnergy(FIRE, 9564, ElementalItemType.Energy),
		waterEnergy(WATER, 9565, ElementalItemType.Energy),
		windEnergy(WIND, 9567, ElementalItemType.Energy),
		earthEnergy(EARTH, 9566, ElementalItemType.Energy),
		divineEnergy(HOLY, 9569, ElementalItemType.Energy),
		darkEnergy(DARK, 9568, ElementalItemType.Energy);
		
		public byte _element;
		public int _itemId;
		public ElementalItemType _type;
		
		private ElementalItems(byte element, int itemId, ElementalItemType type)
		{
			_element = element;
			_itemId = itemId;
			_type = type;
		}
	}
	
	public static byte getItemElement(int itemId)
	{
		final ElementalItems item = TABLE.get(itemId);
		return item != null ? item._element : NONE;
	}
	
	public static ElementalItems getItemElemental(int itemId)
	{
		return TABLE.get(itemId);
	}
	
	public static int getMaxElementLevel(int itemId)
	{
		final ElementalItems item = TABLE.get(itemId);
		return item != null ? item._type._maxLevel : -1;
	}
	
	public static String getElementName(byte element)
	{
		switch (element)
		{
			case FIRE:
			{
				return "Fire";
			}
			case WATER:
			{
				return "Water";
			}
			case WIND:
			{
				return "Wind";
			}
			case EARTH:
			{
				return "Earth";
			}
			case DARK:
			{
				return "Dark";
			}
			case HOLY:
			{
				return "Holy";
			}
		}
		return "None";
	}
	
	public static byte getElementId(String name)
	{
		final String tmp = name.toLowerCase();
		if (tmp.equals("fire"))
		{
			return FIRE;
		}
		if (tmp.equals("water"))
		{
			return WATER;
		}
		if (tmp.equals("wind"))
		{
			return WIND;
		}
		if (tmp.equals("earth"))
		{
			return EARTH;
		}
		if (tmp.equals("dark"))
		{
			return DARK;
		}
		if (tmp.equals("holy"))
		{
			return HOLY;
		}
		return NONE;
	}
	
	public static byte getOppositeElement(byte element)
	{
		return (byte) (((element % 2) == 0) ? (element + 1) : (element - 1));
	}
	
	public static class ElementalStatBonus
	{
		private byte _elementalType;
		private int _elementalValue;
		private boolean _active;
		
		public ElementalStatBonus(byte type, int value)
		{
			_elementalType = type;
			_elementalValue = value;
			_active = false;
		}
		
		public void applyBonus(L2PcInstance player, boolean isArmor)
		{
			// make sure the bonuses are not applied twice..
			if (_active)
			{
				return;
			}
			
			switch (_elementalType)
			{
				case FIRE:
				{
					player.addStatFunc(new FuncAdd(isArmor ? Stats.FIRE_RES : Stats.FIRE_POWER, 0x40, this, _elementalValue, null));
					break;
				}
				case WATER:
				{
					player.addStatFunc(new FuncAdd(isArmor ? Stats.WATER_RES : Stats.WATER_POWER, 0x40, this, _elementalValue, null));
					break;
				}
				case WIND:
				{
					player.addStatFunc(new FuncAdd(isArmor ? Stats.WIND_RES : Stats.WIND_POWER, 0x40, this, _elementalValue, null));
					break;
				}
				case EARTH:
				{
					player.addStatFunc(new FuncAdd(isArmor ? Stats.EARTH_RES : Stats.EARTH_POWER, 0x40, this, _elementalValue, null));
					break;
				}
				case DARK:
				{
					player.addStatFunc(new FuncAdd(isArmor ? Stats.DARK_RES : Stats.DARK_POWER, 0x40, this, _elementalValue, null));
					break;
				}
				case HOLY:
				{
					player.addStatFunc(new FuncAdd(isArmor ? Stats.HOLY_RES : Stats.HOLY_POWER, 0x40, this, _elementalValue, null));
					break;
				}
			}
			
			_active = true;
		}
		
		public void removeBonus(L2PcInstance player)
		{
			// make sure the bonuses are not removed twice
			if (!_active)
			{
				return;
			}
			
			player.removeStatsOwner(this);
			
			_active = false;
		}
		
		public void setValue(int val)
		{
			_elementalValue = val;
		}
		
		public void setElement(byte type)
		{
			_elementalType = type;
		}
	}
	
	// non static:
	private ElementalStatBonus _bonus = null;
	private byte _element = NONE;
	private int _value = 0;
	
	public byte getElement()
	{
		return _element;
	}
	
	public void setElement(byte type)
	{
		_element = type;
		_bonus.setElement(type);
	}
	
	public int getValue()
	{
		return _value;
	}
	
	public void setValue(int val)
	{
		_value = val;
		_bonus.setValue(val);
	}
	
	@Override
	public String toString()
	{
		return getElementName(_element) + " +" + _value;
	}
	
	public Elementals(byte type, int value)
	{
		_element = type;
		_value = value;
		_bonus = new ElementalStatBonus(_element, _value);
	}
	
	public void applyBonus(L2PcInstance player, boolean isArmor)
	{
		_bonus.applyBonus(player, isArmor);
	}
	
	public void removeBonus(L2PcInstance player)
	{
		_bonus.removeBonus(player);
	}
	
	public void updateBonus(L2PcInstance player, boolean isArmor)
	{
		_bonus.removeBonus(player);
		_bonus.applyBonus(player, isArmor);
	}
}

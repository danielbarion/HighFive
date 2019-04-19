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
package com.l2jmobius.gameserver.model.multisell;

import com.l2jmobius.gameserver.model.Elementals;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;

/**
 * @author DS
 */
public class ItemInfo
{
	private final int _enchantLevel;
	private final int _augmentId;
	private final byte _elementId;
	private final int _elementPower;
	private final int[] _elementals = new int[6];
	
	public ItemInfo(L2ItemInstance item)
	{
		_enchantLevel = item.getEnchantLevel();
		_augmentId = item.getAugmentation() != null ? item.getAugmentation().getAugmentationId() : 0;
		_elementId = item.getAttackElementType();
		_elementPower = item.getAttackElementPower();
		_elementals[0] = item.getElementDefAttr(Elementals.FIRE);
		_elementals[1] = item.getElementDefAttr(Elementals.WATER);
		_elementals[2] = item.getElementDefAttr(Elementals.WIND);
		_elementals[3] = item.getElementDefAttr(Elementals.EARTH);
		_elementals[4] = item.getElementDefAttr(Elementals.HOLY);
		_elementals[5] = item.getElementDefAttr(Elementals.DARK);
	}
	
	public ItemInfo(int enchantLevel)
	{
		_enchantLevel = enchantLevel;
		_augmentId = 0;
		_elementId = Elementals.NONE;
		_elementPower = 0;
		_elementals[0] = 0;
		_elementals[1] = 0;
		_elementals[2] = 0;
		_elementals[3] = 0;
		_elementals[4] = 0;
		_elementals[5] = 0;
	}
	
	public final int getEnchantLevel()
	{
		return _enchantLevel;
	}
	
	public final int getAugmentId()
	{
		return _augmentId;
	}
	
	public final byte getElementId()
	{
		return _elementId;
	}
	
	public final int getElementPower()
	{
		return _elementPower;
	}
	
	public final int[] getElementals()
	{
		return _elementals;
	}
}
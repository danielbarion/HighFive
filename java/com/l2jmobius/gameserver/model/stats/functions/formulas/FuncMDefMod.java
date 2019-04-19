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
package com.l2jmobius.gameserver.model.stats.functions.formulas;

import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.stats.BaseStats;
import com.l2jmobius.gameserver.model.stats.Stats;
import com.l2jmobius.gameserver.model.stats.functions.AbstractFunction;

/**
 * @author UnAfraid
 */
public class FuncMDefMod extends AbstractFunction
{
	private static final FuncMDefMod _fmm_instance = new FuncMDefMod();
	
	public static AbstractFunction getInstance()
	{
		return _fmm_instance;
	}
	
	private FuncMDefMod()
	{
		super(Stats.MAGIC_DEFENCE, 1, null, 0, null);
	}
	
	@Override
	public double calc(L2Character effector, L2Character effected, Skill skill, double initVal)
	{
		double value = initVal;
		if (effector.isPlayer())
		{
			final L2PcInstance p = effector.getActingPlayer();
			if (!p.getInventory().isPaperdollSlotEmpty(Inventory.PAPERDOLL_LFINGER))
			{
				value -= p.getTemplate().getBaseDefBySlot(p.isTransformed() ? p.getTransformation().getBaseDefBySlot(p, Inventory.PAPERDOLL_LFINGER) : Inventory.PAPERDOLL_LFINGER);
			}
			if (!p.getInventory().isPaperdollSlotEmpty(Inventory.PAPERDOLL_RFINGER))
			{
				value -= p.getTemplate().getBaseDefBySlot(p.isTransformed() ? p.getTransformation().getBaseDefBySlot(p, Inventory.PAPERDOLL_RFINGER) : Inventory.PAPERDOLL_RFINGER);
			}
			if (!p.getInventory().isPaperdollSlotEmpty(Inventory.PAPERDOLL_LEAR))
			{
				value -= p.getTemplate().getBaseDefBySlot(p.isTransformed() ? p.getTransformation().getBaseDefBySlot(p, Inventory.PAPERDOLL_LEAR) : Inventory.PAPERDOLL_LEAR);
			}
			if (!p.getInventory().isPaperdollSlotEmpty(Inventory.PAPERDOLL_REAR))
			{
				value -= p.getTemplate().getBaseDefBySlot(p.isTransformed() ? p.getTransformation().getBaseDefBySlot(p, Inventory.PAPERDOLL_REAR) : Inventory.PAPERDOLL_REAR);
			}
			if (!p.getInventory().isPaperdollSlotEmpty(Inventory.PAPERDOLL_NECK))
			{
				value -= p.getTemplate().getBaseDefBySlot(p.isTransformed() ? p.getTransformation().getBaseDefBySlot(p, Inventory.PAPERDOLL_NECK) : Inventory.PAPERDOLL_NECK);
			}
		}
		else if (effector.isPet() && (effector.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_NECK) != 0))
		{
			value -= 13;
		}
		return value * BaseStats.MEN.calcBonus(effector) * effector.getLevelMod();
	}
}
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
package com.l2jmobius.gameserver.model.stats.functions;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

import com.l2jmobius.gameserver.enums.StatFunction;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.conditions.Condition;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.stats.Stats;

/**
 * Function template.
 * @author mkizub, Zoey76
 */
public final class FuncTemplate
{
	private static final Logger LOG = Logger.getLogger(FuncTemplate.class.getName());
	
	private final Condition _attachCond;
	private final Condition _applayCond;
	private final Constructor<?> _constructor;
	private final Stats _stat;
	private final int _order;
	private final double _value;
	
	public FuncTemplate(Condition attachCond, Condition applayCond, String functionName, int order, Stats stat, double value)
	{
		final StatFunction function = StatFunction.valueOf(functionName.toUpperCase());
		if (order >= 0)
		{
			_order = order;
		}
		else
		{
			_order = function.getOrder();
		}
		
		_attachCond = attachCond;
		_applayCond = applayCond;
		_stat = stat;
		_value = value;
		
		try
		{
			final Class<?> functionClass = Class.forName("com.l2jmobius.gameserver.model.stats.functions.Func" + function.getName());
			_constructor = functionClass.getConstructor(Stats.class, // Stats to update
				Integer.TYPE, // Order of execution
				Object.class, // Owner
				Double.TYPE, // Value for function
				Condition.class // Condition
			);
		}
		catch (ClassNotFoundException | NoSuchMethodException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Gets the function stat.
	 * @return the stat.
	 */
	public Stats getStat()
	{
		return _stat;
	}
	
	/**
	 * Gets the function priority order.
	 * @return the order
	 */
	public int getOrder()
	{
		return _order;
	}
	
	/**
	 * Gets the function value.
	 * @return the value
	 */
	public double getValue()
	{
		return _value;
	}
	
	/**
	 * Gets the functions for skills.
	 * @param caster the caster
	 * @param target the target
	 * @param skill the skill
	 * @param owner the owner
	 * @return the function if conditions are met, {@code null} otherwise
	 */
	public AbstractFunction getFunc(L2Character caster, L2Character target, Skill skill, Object owner)
	{
		return getFunc(caster, target, skill, null, owner);
	}
	
	/**
	 * Gets the functions for items.
	 * @param caster the caster
	 * @param target the target
	 * @param item the item
	 * @param owner the owner
	 * @return the function if conditions are met, {@code null} otherwise
	 */
	public AbstractFunction getFunc(L2Character caster, L2Character target, L2ItemInstance item, Object owner)
	{
		return getFunc(caster, target, null, item, owner);
	}
	
	/**
	 * Gets the functions for skills and items.
	 * @param caster the caster
	 * @param target the target
	 * @param skill the skill
	 * @param item the item
	 * @param owner the owner
	 * @return the function if conditions are met, {@code null} otherwise
	 */
	private AbstractFunction getFunc(L2Character caster, L2Character target, Skill skill, L2ItemInstance item, Object owner)
	{
		if ((_attachCond != null) && !_attachCond.test(caster, target, skill))
		{
			return null;
		}
		try
		{
			return (AbstractFunction) _constructor.newInstance(_stat, _order, owner, _value, _applayCond);
		}
		catch (Exception e)
		{
			LOG.warning(FuncTemplate.class.getSimpleName() + ": " + e.getMessage());
		}
		return null;
	}
}

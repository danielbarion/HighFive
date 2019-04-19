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
package com.l2jmobius.gameserver.model.variables;

import java.util.concurrent.atomic.AtomicBoolean;

import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.interfaces.IRestorable;
import com.l2jmobius.gameserver.model.interfaces.IStorable;

/**
 * @author UnAfraid
 */
public abstract class AbstractVariables extends StatsSet implements IRestorable, IStorable
{
	private final AtomicBoolean _hasChanges = new AtomicBoolean(false);
	
	/**
	 * Overriding following methods to prevent from doing useless database operations if there is no changes since player's login.
	 */
	
	@Override
	public final void set(String name, boolean value)
	{
		_hasChanges.compareAndSet(false, true);
		super.set(name, value);
	}
	
	@Override
	public final void set(String name, double value)
	{
		_hasChanges.compareAndSet(false, true);
		super.set(name, value);
	}
	
	@Override
	public final void set(String name, Enum<?> value)
	{
		_hasChanges.compareAndSet(false, true);
		super.set(name, value);
	}
	
	@Override
	public final void set(String name, int value)
	{
		_hasChanges.compareAndSet(false, true);
		super.set(name, value);
	}
	
	@Override
	public final void set(String name, long value)
	{
		_hasChanges.compareAndSet(false, true);
		super.set(name, value);
	}
	
	@Override
	public final void set(String name, String value)
	{
		_hasChanges.compareAndSet(false, true);
		super.set(name, value);
	}
	
	/**
	 * Return true if there exists a record for the variable name.
	 * @param name
	 * @return
	 */
	public boolean hasVariable(String name)
	{
		return getSet().keySet().contains(name);
	}
	
	/**
	 * @return {@code true} if changes are made since last load/save.
	 */
	public final boolean hasChanges()
	{
		return _hasChanges.get();
	}
	
	/**
	 * Atomically sets the value to the given updated value if the current value {@code ==} the expected value.
	 * @param expect
	 * @param update
	 * @return {@code true} if successful. {@code false} return indicates that the actual value was not equal to the expected value.
	 */
	public final boolean compareAndSetChanges(boolean expect, boolean update)
	{
		return _hasChanges.compareAndSet(expect, update);
	}
	
	/**
	 * Removes variable
	 * @param name
	 */
	@Override
	public final void remove(String name)
	{
		_hasChanges.compareAndSet(false, true);
		getSet().remove(name);
	}
}

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
package com.l2jmobius.gameserver.model.holders;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.l2jmobius.gameserver.model.punishment.PunishmentTask;
import com.l2jmobius.gameserver.model.punishment.PunishmentType;

/**
 * @author UnAfraid
 */
public final class PunishmentHolder
{
	private final Map<String, Map<PunishmentType, PunishmentTask>> _holder = new ConcurrentHashMap<>();
	
	/**
	 * Stores the punishment tasks in the Map.
	 * @param task
	 */
	public void addPunishment(PunishmentTask task)
	{
		if (!task.isExpired())
		{
			_holder.computeIfAbsent(String.valueOf(task.getKey()), k -> new ConcurrentHashMap<>()).put(task.getType(), task);
		}
	}
	
	/**
	 * Removes previously stopped tasks from the Map.
	 * @param task
	 */
	public void stopPunishment(PunishmentTask task)
	{
		final String key = String.valueOf(task.getKey());
		if (!_holder.containsKey(key))
		{
			return;
		}
		task.stopPunishment();
		final Map<PunishmentType, PunishmentTask> punishments = _holder.get(key);
		punishments.remove(task.getType());
		if (punishments.isEmpty())
		{
			_holder.remove(key);
		}
	}
	
	/**
	 * @param key
	 * @param type
	 * @return {@code true} if Map contains the current key and type, {@code false} otherwise.
	 */
	public boolean hasPunishment(String key, PunishmentType type)
	{
		return getPunishment(key, type) != null;
	}
	
	/**
	 * @param key
	 * @param type
	 * @return {@link PunishmentTask} by specified key and type if exists, null otherwise.
	 */
	public PunishmentTask getPunishment(String key, PunishmentType type)
	{
		return _holder.containsKey(key) ? _holder.get(key).get(type) : null;
	}
}

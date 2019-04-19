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
package com.l2jmobius.gameserver.model.events.impl.character;

import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.events.EventType;
import com.l2jmobius.gameserver.model.events.impl.IBaseEvent;
import com.l2jmobius.gameserver.model.zone.L2ZoneType;

/**
 * @author UnAfraid
 */
public class OnCreatureZoneEnter implements IBaseEvent
{
	private final L2Character _creature;
	private final L2ZoneType _zone;
	
	public OnCreatureZoneEnter(L2Character creature, L2ZoneType zone)
	{
		_creature = creature;
		_zone = zone;
	}
	
	public L2Character getCreature()
	{
		return _creature;
	}
	
	public L2ZoneType getZone()
	{
		return _zone;
	}
	
	@Override
	public EventType getType()
	{
		return EventType.ON_CREATURE_ZONE_ENTER;
	}
}

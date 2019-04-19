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
package com.l2jmobius.gameserver.enums;

/**
 * @author kombat
 */
public enum TriggerType
{
	// You hit an enemy
	ON_HIT(1),
	// You hit an enemy - was crit
	ON_CRIT(2),
	// You cast a skill
	ON_CAST(4),
	// You cast a skill - it was a physical one
	ON_PHYSICAL(8),
	// You cast a skill - it was a magic one
	ON_MAGIC(16),
	// You cast a skill - it was a magic one - good magic
	ON_MAGIC_GOOD(32),
	// You cast a skill - it was a magic one - offensive magic
	ON_MAGIC_OFFENSIVE(64),
	// You are attacked by enemy
	ON_ATTACKED(128),
	// You are attacked by enemy - by hit
	ON_ATTACKED_HIT(256),
	// You are attacked by enemy - by hit - was crit
	ON_ATTACKED_CRIT(512),
	// A skill was casted on you
	ON_HIT_BY_SKILL(1024),
	// An evil skill was casted on you
	ON_HIT_BY_OFFENSIVE_SKILL(2048),
	// A good skill was casted on you
	ON_HIT_BY_GOOD_MAGIC(4096),
	// Evading melee attack
	ON_EVADED_HIT(8192),
	// Effect only - on start
	ON_START(16384),
	// Effect only - each second
	ON_ACTION_TIME(32768),
	// Effect only - on exit
	ON_EXIT(65536);
	
	private final int _mask;
	
	private TriggerType(int mask)
	{
		_mask = mask;
	}
	
	public final boolean check(int event)
	{
		return (_mask & event) != 0; // Trigger (sub-)type contains event (sub-)type
	}
}

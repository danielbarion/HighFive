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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.l2jmobius.gameserver.model.actor.L2Npc;

public class L2SiegeClan
{
	private int _clanId = 0;
	private final List<L2Npc> _flag = new CopyOnWriteArrayList<>();
	private SiegeClanType _type;
	
	public enum SiegeClanType
	{
		OWNER,
		DEFENDER,
		ATTACKER,
		DEFENDER_PENDING
	}
	
	public L2SiegeClan(int clanId, SiegeClanType type)
	{
		_clanId = clanId;
		_type = type;
	}
	
	public int getNumFlags()
	{
		return _flag.size();
	}
	
	public void addFlag(L2Npc flag)
	{
		_flag.add(flag);
	}
	
	public boolean removeFlag(L2Npc flag)
	{
		final boolean ret = _flag.remove(flag);
		flag.deleteMe();
		return ret;
	}
	
	public void removeFlags()
	{
		_flag.forEach(f -> f.decayMe());
		_flag.clear();
	}
	
	public final int getClanId()
	{
		return _clanId;
	}
	
	public final List<L2Npc> getFlag()
	{
		return _flag;
	}
	
	public SiegeClanType getType()
	{
		return _type;
	}
	
	public void setType(SiegeClanType setType)
	{
		_type = setType;
	}
}

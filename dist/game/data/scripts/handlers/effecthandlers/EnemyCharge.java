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
package handlers.effecthandlers;

import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.conditions.Condition;
import com.l2jmobius.gameserver.model.effects.AbstractEffect;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.network.serverpackets.FlyToLocation;
import com.l2jmobius.gameserver.network.serverpackets.FlyToLocation.FlyType;
import com.l2jmobius.gameserver.network.serverpackets.ValidateLocation;

/**
 * Enemy Charge effect implementation.
 */
public final class EnemyCharge extends AbstractEffect
{
	public EnemyCharge(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
	}
	
	@Override
	public boolean isInstant()
	{
		return true;
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		if (info.getEffected().isMovementDisabled())
		{
			return;
		}
		
		// Get current position of the L2Character
		final int curX = info.getEffector().getX();
		final int curY = info.getEffector().getY();
		final int curZ = info.getEffector().getZ();
		
		// Calculate distance (dx,dy) between current position and destination
		final double dx = info.getEffected().getX() - curX;
		final double dy = info.getEffected().getY() - curY;
		final double dz = info.getEffected().getZ() - curZ;
		final double distance = Math.sqrt((dx * dx) + (dy * dy));
		if (distance > 2000)
		{
			LOGGER.info("EffectEnemyCharge was going to use invalid coordinates for characters, getEffector: " + curX + "," + curY + " and getEffected: " + info.getEffected().getX() + "," + info.getEffected().getY());
			return;
		}
		
		int offset = Math.max((int) distance - info.getSkill().getFlyRadius(), 30);
		
		// approximation for moving closer when z coordinates are different
		// TODO: handle Z axis movement better
		offset -= Math.abs(dz);
		if (offset < 5)
		{
			offset = 5;
		}
		
		// If no distance
		if ((distance < 1) || ((distance - offset) <= 0))
		{
			return;
		}
		
		// Calculate movement angles needed
		final double sin = dy / distance;
		final double cos = dx / distance;
		
		// Calculate the new destination with offset included
		final int x = curX + (int) ((distance - offset) * cos);
		final int y = curY + (int) ((distance - offset) * sin);
		final int z = info.getEffected().getZ();
		
		final Location destination = GeoEngine.getInstance().canMoveToTargetLoc(info.getEffector().getX(), info.getEffector().getY(), info.getEffector().getZ(), x, y, z, info.getEffector().getInstanceId());
		
		info.getEffector().broadcastPacket(new FlyToLocation(info.getEffector(), destination, FlyType.CHARGE));
		
		// maybe is need force set X,Y,Z
		info.getEffector().setXYZ(destination);
		info.getEffector().broadcastPacket(new ValidateLocation(info.getEffector()));
	}
}

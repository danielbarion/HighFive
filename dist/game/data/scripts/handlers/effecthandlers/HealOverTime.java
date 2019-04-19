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

import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.conditions.Condition;
import com.l2jmobius.gameserver.model.effects.AbstractEffect;
import com.l2jmobius.gameserver.model.skills.AbnormalType;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.network.serverpackets.ExRegenMax;

/**
 * Heal Over Time effect implementation.
 */
public final class HealOverTime extends AbstractEffect
{
	private final double _power;
	
	public HealOverTime(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
		
		_power = params.getDouble("power", 0);
	}
	
	@Override
	public boolean onActionTime(BuffInfo info)
	{
		if (info.getEffected().isDead() || info.getEffected().isDoor())
		{
			return false;
		}
		
		double hp = info.getEffected().getCurrentHp();
		final double maxhp = info.getEffected().getMaxRecoverableHp();
		
		// Not needed to set the HP and send update packet if player is already at max HP
		if (hp >= maxhp)
		{
			return false;
		}
		
		hp += _power * getTicksMultiplier();
		hp = Math.min(hp, maxhp);
		info.getEffected().setCurrentHp(hp);
		return info.getSkill().isToggle();
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		if (info.getEffected().isPlayer() && (getTicks() > 0) && (info.getSkill().getAbnormalType() == AbnormalType.HP_RECOVER))
		{
			info.getEffected().sendPacket(new ExRegenMax(info.getAbnormalTime(), getTicks(), _power));
		}
	}
}

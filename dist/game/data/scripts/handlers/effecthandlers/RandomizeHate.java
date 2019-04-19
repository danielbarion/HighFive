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

import java.util.List;
import java.util.stream.Collectors;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.conditions.Condition;
import com.l2jmobius.gameserver.model.effects.AbstractEffect;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.model.stats.Formulas;

/**
 * Randomize Hate effect implementation.
 */
public final class RandomizeHate extends AbstractEffect
{
	private final int _chance;
	
	public RandomizeHate(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
		
		_chance = params.getInt("chance", 100);
	}
	
	@Override
	public boolean calcSuccess(BuffInfo info)
	{
		return Formulas.calcProbability(_chance, info.getEffector(), info.getEffected(), info.getSkill());
	}
	
	@Override
	public boolean isInstant()
	{
		return true;
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		if ((info.getEffected() == null) || (info.getEffected() == info.getEffector()) || !info.getEffected().isAttackable())
		{
			return;
		}
		
		final L2Attackable effectedMob = (L2Attackable) info.getEffected();
		final List<L2Character> aggroList = effectedMob.getAggroList().keySet().stream().filter(c -> c != info.getEffector()).collect(Collectors.toList());
		if (aggroList.isEmpty())
		{
			return;
		}
		
		// Choosing randomly a new target
		final L2Character target = aggroList.get(Rnd.get(aggroList.size()));
		final int hate = effectedMob.getHating(info.getEffector());
		effectedMob.stopHating(info.getEffector());
		effectedMob.addDamageHate(target, 0, hate);
	}
}
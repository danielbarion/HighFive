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

import com.l2jmobius.gameserver.enums.EffectCalculationType;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.stat.CharStat;
import com.l2jmobius.gameserver.model.conditions.Condition;
import com.l2jmobius.gameserver.model.effects.AbstractEffect;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.model.stats.Stats;
import com.l2jmobius.gameserver.model.stats.functions.FuncAdd;
import com.l2jmobius.gameserver.model.stats.functions.FuncMul;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

/**
 * @author Zealar
 */
public final class MaxHp extends AbstractEffect
{
	private final double _power;
	private final EffectCalculationType _type;
	private final boolean _heal;
	
	public MaxHp(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
		
		_type = params.getEnum("type", EffectCalculationType.class, EffectCalculationType.DIFF);
		switch (_type)
		{
			case DIFF:
			{
				_power = params.getInt("power", 0);
				break;
			}
			default:
			{
				_power = 1 + (params.getInt("power", 0) / 100.0);
			}
		}
		_heal = params.getBoolean("heal", false);
		
		if (params.isEmpty())
		{
			LOGGER.warning(getClass().getSimpleName() + ": must have parameters.");
		}
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		final L2Character effected = info.getEffected();
		final CharStat charStat = effected.getStat();
		final double currentHp = effected.getCurrentHp();
		double amount = _power;
		
		synchronized (charStat)
		{
			switch (_type)
			{
				case DIFF:
				{
					
					charStat.getActiveChar().addStatFunc(new FuncAdd(Stats.MAX_HP, 1, this, _power, null));
					if (_heal)
					{
						effected.setCurrentHp((currentHp + _power));
					}
					break;
				}
				case PER:
				{
					final double maxHp = effected.getMaxHp();
					charStat.getActiveChar().addStatFunc(new FuncMul(Stats.MAX_HP, 1, this, _power, null));
					if (_heal)
					{
						amount = (_power - 1) * maxHp;
						effected.setCurrentHp(currentHp + amount);
					}
					break;
				}
			}
		}
		if (_heal)
		{
			final L2Character caster = info.getEffector();
			if ((caster != null) && (caster != effected))
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S2_HP_HAS_BEEN_RESTORED_BY_C1);
				sm.addString(caster.getName());
				sm.addInt((int) amount);
				effected.sendPacket(sm);
			}
			else
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HP_HAS_BEEN_RESTORED);
				sm.addInt((int) amount);
				effected.sendPacket(sm);
			}
		}
	}
	
	@Override
	public void onExit(BuffInfo info)
	{
		final CharStat charStat = info.getEffected().getStat();
		synchronized (charStat)
		{
			charStat.getActiveChar().removeStatsOwner(this);
		}
	}
	
}

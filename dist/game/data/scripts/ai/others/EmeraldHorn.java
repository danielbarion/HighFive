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
package ai.others;

import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Emerald Horn AI.
 * @author Zoey76
 * @since 2.6.0.0
 */
public class EmeraldHorn extends AbstractNpcAI
{
	private static final int EMERALD_HORN = 25718;
	// Skills
	private static final SkillHolder REFLECT_ATTACK = new SkillHolder(6823, 1);
	private static final SkillHolder PIERCING_STORM = new SkillHolder(6824, 1);
	private static final SkillHolder BLEED_LVL_1 = new SkillHolder(6825, 1);
	private static final SkillHolder BLEED_LVL_2 = new SkillHolder(6825, 2);
	// Variables
	private static final String HIGH_DAMAGE_FLAG = "HIGH_DAMAGE_FLAG";
	private static final String TOTAL_DAMAGE_COUNT = "TOTAL_DAMAGE_COUNT";
	private static final String CAST_FLAG = "CAST_FLAG";
	// Timers
	private static final String DAMAGE_TIMER_15S = "DAMAGE_TIMER_15S";
	// Misc
	private static final int MAX_CHASE_DIST = 2500;
	
	public EmeraldHorn()
	{
		addAttackId(EMERALD_HORN);
		addSpellFinishedId(EMERALD_HORN);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (Util.calculateDistance(npc, npc.getSpawn(), false, false) > MAX_CHASE_DIST)
		{
			npc.teleToLocation(npc.getSpawn().getX(), npc.getSpawn().getY(), npc.getSpawn().getZ());
		}
		
		if (npc.isAffectedBySkill(REFLECT_ATTACK.getSkillId()))
		{
			if (npc.getVariables().getBoolean(CAST_FLAG, false))
			{
				npc.getVariables().set(TOTAL_DAMAGE_COUNT, npc.getVariables().getInt(TOTAL_DAMAGE_COUNT) + damage);
			}
		}
		
		if (npc.getVariables().getInt(TOTAL_DAMAGE_COUNT) > 5000)
		{
			addSkillCastDesire(npc, attacker, BLEED_LVL_2, 99999);
			npc.getVariables().set(TOTAL_DAMAGE_COUNT, 0);
			npc.getVariables().set(CAST_FLAG, false);
			npc.getVariables().set(HIGH_DAMAGE_FLAG, true);
		}
		
		if (npc.getVariables().getInt(TOTAL_DAMAGE_COUNT) > 10000)
		{
			addSkillCastDesire(npc, attacker, BLEED_LVL_1, 99999);
			npc.getVariables().set(TOTAL_DAMAGE_COUNT, 0);
			npc.getVariables().set(CAST_FLAG, false);
			npc.getVariables().set(HIGH_DAMAGE_FLAG, true);
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onSpellFinished(L2Npc npc, L2PcInstance player, Skill skill)
	{
		if (getRandom(5) < 1)
		{
			npc.getVariables().set(TOTAL_DAMAGE_COUNT, 0);
			npc.getVariables().set(CAST_FLAG, true);
			addSkillCastDesire(npc, npc, REFLECT_ATTACK, 99999);
			startQuestTimer(DAMAGE_TIMER_15S, 15 * 1000, npc, player);
		}
		return super.onSpellFinished(npc, player, skill);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (DAMAGE_TIMER_15S.equals(event))
		{
			if (!npc.getVariables().getBoolean(HIGH_DAMAGE_FLAG, false))
			{
				final L2Character mostHated = ((L2Attackable) npc).getMostHated();
				if (mostHated != null)
				{
					if (mostHated.isDead())
					{
						((L2Attackable) npc).stopHating(mostHated);
					}
					else
					{
						addSkillCastDesire(npc, mostHated, PIERCING_STORM, 99999);
					}
				}
			}
			npc.getVariables().set(CAST_FLAG, false);
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	public static void main(String[] args)
	{
		new EmeraldHorn();
	}
}

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

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Bleeding Fly AI.
 * @author Zoey76
 * @since 2.6.0.0
 */
public class BleedingFly extends AbstractNpcAI
{
	// NPCs
	private static final int BLEEDING_FLY = 25720;
	private static final int PARASITIC_LEECH = 25734;
	// Skills
	private static final SkillHolder SUMMON_PARASITE_LEECH = new SkillHolder(6832, 1);
	private static final SkillHolder NPC_ACUMEN_LVL_3 = new SkillHolder(6915, 3);
	// Variables
	private static final String MID_HP_FLAG = "MID_HP_FLAG";
	private static final String LOW_HP_FLAG = "LOW_HP_FLAG";
	private static final String MID_HP_MINION_COUNT = "MID_HP_MINION_COUNT";
	private static final String LOW_HP_MINION_COUNT = "LOW_HP_MINION_COUNT";
	// Timers
	private static final String TIMER_MID_HP = "TIMER_MID_HP";
	private static final String TIMER_LOW_HP = "TIMER_LOW_HP";
	// Misc
	private static final int MAX_CHASE_DIST = 2500;
	private static final double MID_HP_PERCENTAGE = 0.50;
	private static final double MIN_HP_PERCENTAGE = 0.25;
	
	public BleedingFly()
	{
		addAttackId(BLEEDING_FLY);
		addSpawnId(BLEEDING_FLY);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		npc.getVariables().set(MID_HP_MINION_COUNT, 5);
		npc.getVariables().set(LOW_HP_MINION_COUNT, 10);
		return super.onSpawn(npc);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (Util.calculateDistance(npc, npc.getSpawn(), false, false) > MAX_CHASE_DIST)
		{
			npc.teleToLocation(npc.getSpawn().getX(), npc.getSpawn().getY(), npc.getSpawn().getZ());
		}
		
		if ((npc.getCurrentHp() < (npc.getMaxHp() * MID_HP_PERCENTAGE)) && !npc.getVariables().getBoolean(MID_HP_FLAG, false))
		{
			npc.getVariables().set(MID_HP_FLAG, true);
			startQuestTimer(TIMER_MID_HP, 1000, npc, null);
		}
		
		if ((npc.getCurrentHp() < (npc.getMaxHp() * MIN_HP_PERCENTAGE)) && !npc.getVariables().getBoolean(LOW_HP_FLAG, false))
		{
			npc.getVariables().set(MID_HP_FLAG, false);
			npc.getVariables().set(LOW_HP_FLAG, true);
			startQuestTimer(TIMER_LOW_HP, 1000, npc, null);
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (npc.isDead())
		{
			return super.onAdvEvent(event, npc, player);
		}
		
		if (TIMER_MID_HP.equals(event))
		{
			if (npc.getVariables().getInt(MID_HP_MINION_COUNT) > 0)
			{
				npc.getVariables().set(MID_HP_MINION_COUNT, npc.getVariables().getInt(MID_HP_MINION_COUNT) - 1);
				addSkillCastDesire(npc, npc, SUMMON_PARASITE_LEECH, 99999);
				addSpawn(PARASITIC_LEECH, npc.getX() + getRandom(150), npc.getY() + getRandom(150), npc.getZ(), npc.getHeading(), false, 0);
				addSpawn(PARASITIC_LEECH, npc.getX() + getRandom(150), npc.getY() + getRandom(150), npc.getZ(), npc.getHeading(), false, 0);
				
				if (npc.getVariables().getBoolean(MID_HP_FLAG, false))
				{
					startQuestTimer(TIMER_MID_HP, 140000, npc, null);
				}
			}
		}
		else if (TIMER_LOW_HP.equals(event))
		{
			if (npc.getVariables().getInt(LOW_HP_MINION_COUNT) > 0)
			{
				npc.getVariables().set(LOW_HP_MINION_COUNT, npc.getVariables().getInt(LOW_HP_MINION_COUNT) - 1);
				addSkillCastDesire(npc, npc, SUMMON_PARASITE_LEECH, 99999);
				addSkillCastDesire(npc, npc, NPC_ACUMEN_LVL_3, 99999);
				addSpawn(PARASITIC_LEECH, npc.getX() + getRandom(150), npc.getY() + getRandom(150), npc.getZ(), npc.getHeading(), false, 0);
				addSpawn(PARASITIC_LEECH, npc.getX() + getRandom(150), npc.getY() + getRandom(150), npc.getZ(), npc.getHeading(), false, 0);
				
				if (npc.getVariables().getBoolean(LOW_HP_FLAG, false))
				{
					startQuestTimer(TIMER_LOW_HP, 80000, npc, null);
				}
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	public static void main(String[] args)
	{
		new BleedingFly();
	}
}

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
package ai.areas.StakatoNest;

import java.util.List;

import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jmobius.gameserver.util.Broadcast;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Stakato Nest AI.
 * @author Gnacik
 */
public final class StakatoNest extends AbstractNpcAI
{
	// @formatter:off
	// List of all mobs just for register
	private static final int[] STAKATO_MOBS =
	{
		18793, 18794, 18795, 18796, 18797, 18798, 22617, 22618, 22619, 22620,
		22621, 22622, 22623, 22624, 22625, 22626, 22627, 22628, 22629, 22630,
		22631, 22632, 22633, 25667
	};
	// Coocons
	private static final int[] COCOONS =
	{
		18793, 18794, 18795, 18796, 18797, 18798
	};
	// @formatter:on
	// Cannibalistic Stakato Leader
	private static final int STAKATO_LEADER = 22625;
	
	// Spike Stakato Nurse
	private static final int STAKATO_NURSE = 22630;
	// Spike Stakato Nurse (Changed)
	private static final int STAKATO_NURSE_2 = 22631;
	// Spiked Stakato Baby
	private static final int STAKATO_BABY = 22632;
	// Spiked Stakato Captain
	private static final int STAKATO_CAPTAIN = 22629;
	
	// Female Spiked Stakato
	private static final int STAKATO_FEMALE = 22620;
	// Male Spiked Stakato
	private static final int STAKATO_MALE = 22621;
	// Male Spiked Stakato (Changed)
	private static final int STAKATO_MALE_2 = 22622;
	// Spiked Stakato Guard
	private static final int STAKATO_GUARD = 22619;
	
	// Cannibalistic Stakato Chief
	private static final int STAKATO_CHIEF = 25667;
	// Growth Accelerator
	private static final int GROWTH_ACCELERATOR = 2905;
	// Small Stakato Cocoon
	private static final int SMALL_COCOON = 14833;
	// Large Stakato Cocoon
	private static final int LARGE_COCOON = 14834;
	
	private StakatoNest()
	{
		registerMobs(STAKATO_MOBS);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		final L2MonsterInstance mob = (L2MonsterInstance) npc;
		
		if ((mob.getId() == STAKATO_LEADER) && (getRandom(1000) < 100) && (mob.getCurrentHp() < (mob.getMaxHp() * 0.3)))
		{
			final L2MonsterInstance _follower = checkMinion(npc);
			
			if (_follower != null)
			{
				final double _hp = _follower.getCurrentHp();
				
				if (_hp > (_follower.getMaxHp() * 0.3))
				{
					mob.abortAttack();
					mob.abortCast();
					mob.setHeading(Util.calculateHeadingFrom(mob, _follower));
					mob.doCast(SkillData.getInstance().getSkill(4484, 1));
					mob.setCurrentHp(mob.getCurrentHp() + _hp);
					_follower.doDie(_follower);
					_follower.deleteMe();
				}
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final L2MonsterInstance monster;
		switch (npc.getId())
		{
			case STAKATO_NURSE:
			{
				monster = checkMinion(npc);
				if (monster != null)
				{
					Broadcast.toSelfAndKnownPlayers(npc, new MagicSkillUse(npc, 2046, 1, 1000, 0));
					for (int i = 0; i < 3; i++)
					{
						final L2Npc spawned = addSpawn(STAKATO_CAPTAIN, monster, true);
						addAttackDesire(spawned, killer);
					}
				}
				break;
			}
			case STAKATO_BABY:
			{
				monster = ((L2MonsterInstance) npc).getLeader();
				if ((monster != null) && !monster.isDead())
				{
					startQuestTimer("nurse_change", 5000, monster, killer);
				}
				break;
			}
			case STAKATO_MALE:
			{
				monster = checkMinion(npc);
				if (monster != null)
				{
					Broadcast.toSelfAndKnownPlayers(npc, new MagicSkillUse(npc, 2046, 1, 1000, 0));
					for (int i = 0; i < 3; i++)
					{
						final L2Npc spawned = addSpawn(STAKATO_GUARD, monster, true);
						addAttackDesire(spawned, killer);
					}
				}
				break;
			}
			case STAKATO_FEMALE:
			{
				monster = ((L2MonsterInstance) npc).getLeader();
				if ((monster != null) && !monster.isDead())
				{
					startQuestTimer("male_change", 5000, monster, killer);
				}
				break;
			}
			case STAKATO_CHIEF:
			{
				if (killer.isInParty())
				{
					final List<L2PcInstance> party = killer.getParty().getMembers();
					for (L2PcInstance member : party)
					{
						giveCocoon(member, npc);
					}
				}
				else
				{
					giveCocoon(killer, npc);
				}
				break;
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isSummon)
	{
		if (CommonUtil.contains(COCOONS, npc.getId()) && CommonUtil.contains(targets, npc) && (skill.getId() == GROWTH_ACCELERATOR))
		{
			npc.doDie(caster);
			final L2Npc spawned = addSpawn(STAKATO_CHIEF, npc.getX(), npc.getY(), npc.getZ(), Util.calculateHeadingFrom(npc, caster), false, 0, true);
			addAttackDesire(spawned, caster);
		}
		return super.onSkillSee(npc, caster, skill, targets, isSummon);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ((npc == null) || (player == null) || npc.isDead())
		{
			return null;
		}
		
		int npcId = 0;
		switch (event)
		{
			case "nurse_change":
			{
				npcId = STAKATO_NURSE_2;
				break;
			}
			case "male_change":
			{
				npcId = STAKATO_MALE_2;
				break;
			}
		}
		if (npcId > 0)
		{
			npc.getSpawn().decreaseCount(npc);
			npc.deleteMe();
			final L2Npc spawned = addSpawn(npcId, npc.getX(), npc.getY(), npc.getZ(), npc.getHeading(), false, 0, true);
			addAttackDesire(spawned, player);
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	private static L2MonsterInstance checkMinion(L2Npc npc)
	{
		final L2MonsterInstance mob = (L2MonsterInstance) npc;
		if (mob.hasMinions())
		{
			final List<L2MonsterInstance> minion = mob.getMinionList().getSpawnedMinions();
			if ((minion != null) && !minion.isEmpty() && (minion.get(0) != null) && !minion.get(0).isDead())
			{
				return minion.get(0);
			}
		}
		return null;
	}
	
	private static void giveCocoon(L2PcInstance player, L2Npc npc)
	{
		player.addItem("StakatoCocoon", ((getRandom(100) > 80) ? LARGE_COCOON : SMALL_COCOON), 1, npc, true);
	}
	
	public static void main(String[] args)
	{
		new StakatoNest();
	}
}

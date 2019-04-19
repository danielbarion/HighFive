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
package ai.areas.DragonValley;

import java.util.EnumMap;

import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.L2Playable;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.skills.Skill;

import ai.AbstractNpcAI;

/**
 * Dragon Valley AI.
 * @author St3eT
 */
public final class DragonValley extends AbstractNpcAI
{
	// NPC
	private static final int NECROMANCER_OF_THE_VALLEY = 22858;
	private static final int EXPLODING_ORC_GHOST = 22818;
	private static final int WRATHFUL_ORC_GHOST = 22819;
	private static final int DRAKOS_ASSASSIN = 22823;
	private static final int[] SUMMON_NPC =
	{
		22822, // Drakos Warrior
		22824, // Drakos Guardian
		22862, // Drakos Hunter
	};
	private static final int[] SPAWN_ANIMATION =
	{
		22826, // Scorpion Bones
		22823, // Drakos Assassin
		22828, // Parasitic Leech
	
	};
	private static final int[] SPOIL_REACT_MONSTER =
	{
		22822, // Drakos Warrior
		22823, // Drakos Assassin
		22824, // Drakos Guardian
		22825, // Giant Scorpion Bones
		22826, // Scorpion Bones
		22827, // Batwing Drake
		22828, // Parasitic Leech
		22829, // Emerald Drake
		22830, // Gem Dragon
		22831, // Dragon Tracker of the Valley
		22832, // Dragon Scout of the Valley
		22833, // Sand Drake Tracker
		22834, // Dust Dragon Tracker
		22860, // Hungry Parasitic Leech
		22861, // Hard Scorpion Bones
		22862, // Drakos Hunter
	};
	// Items
	private static final int GREATER_HERB_OF_MANA = 8604;
	private static final int SUPERIOR_HERB_OF_MANA = 8605;
	// Skills
	private static final SkillHolder SELF_DESTRUCTION = new SkillHolder(6850, 1);
	private static final SkillHolder MORALE_BOOST1 = new SkillHolder(6885, 1);
	private static final SkillHolder MORALE_BOOST2 = new SkillHolder(6885, 2);
	private static final SkillHolder MORALE_BOOST3 = new SkillHolder(6885, 3);
	// Misc
	private static final int MIN_DISTANCE = 1500;
	private static final int MIN_MEMBERS = 3;
	private static final int MIN_LVL = 80;
	private static final int CLASS_LVL = 3;
	private static final EnumMap<ClassId, Double> CLASS_POINTS = new EnumMap<>(ClassId.class);
	{
		CLASS_POINTS.put(ClassId.ADVENTURER, 0.2);
		CLASS_POINTS.put(ClassId.ARCANA_LORD, 1.5);
		CLASS_POINTS.put(ClassId.ARCHMAGE, 0.3);
		CLASS_POINTS.put(ClassId.CARDINAL, -0.6);
		CLASS_POINTS.put(ClassId.DOMINATOR, 0.2);
		CLASS_POINTS.put(ClassId.DOOMBRINGER, 0.2);
		CLASS_POINTS.put(ClassId.DOOMCRYER, 0.1);
		CLASS_POINTS.put(ClassId.DREADNOUGHT, 0.7);
		CLASS_POINTS.put(ClassId.DUELIST, 0.2);
		CLASS_POINTS.put(ClassId.ELEMENTAL_MASTER, 1.4);
		CLASS_POINTS.put(ClassId.EVA_SAINT, -0.6);
		CLASS_POINTS.put(ClassId.EVA_TEMPLAR, 0.8);
		CLASS_POINTS.put(ClassId.FEMALE_SOUL_HOUND, 0.4);
		CLASS_POINTS.put(ClassId.FORTUNE_SEEKER, 0.9);
		CLASS_POINTS.put(ClassId.GHOST_HUNTER, 0.2);
		CLASS_POINTS.put(ClassId.GHOST_SENTINEL, 0.2);
		CLASS_POINTS.put(ClassId.GRAND_KHAVATARI, 0.2);
		CLASS_POINTS.put(ClassId.HELL_KNIGHT, 0.6);
		CLASS_POINTS.put(ClassId.HIEROPHANT, 0.0);
		CLASS_POINTS.put(ClassId.JUDICATOR, 0.1);
		CLASS_POINTS.put(ClassId.MOONLIGHT_SENTINEL, 0.2);
		CLASS_POINTS.put(ClassId.MAESTRO, 0.7);
		CLASS_POINTS.put(ClassId.MALE_SOUL_HOUND, 0.4);
		CLASS_POINTS.put(ClassId.MYSTIC_MUSE, 0.3);
		CLASS_POINTS.put(ClassId.PHOENIX_KNIGHT, 0.6);
		CLASS_POINTS.put(ClassId.SAGITTARIUS, 0.2);
		CLASS_POINTS.put(ClassId.SHILLIEN_SAINT, -0.6);
		CLASS_POINTS.put(ClassId.SHILLIEN_TEMPLAR, 0.8);
		CLASS_POINTS.put(ClassId.SOULTAKER, 0.3);
		CLASS_POINTS.put(ClassId.SPECTRAL_DANCER, 0.4);
		CLASS_POINTS.put(ClassId.SPECTRAL_MASTER, 1.4);
		CLASS_POINTS.put(ClassId.STORM_SCREAMER, 0.3);
		CLASS_POINTS.put(ClassId.SWORD_MUSE, 0.4);
		CLASS_POINTS.put(ClassId.TITAN, 0.3);
		CLASS_POINTS.put(ClassId.TRICKSTER, 0.5);
		CLASS_POINTS.put(ClassId.WIND_RIDER, 0.2);
	}
	
	private DragonValley()
	{
		addAttackId(NECROMANCER_OF_THE_VALLEY);
		addAttackId(SUMMON_NPC);
		addKillId(NECROMANCER_OF_THE_VALLEY);
		addKillId(SPOIL_REACT_MONSTER);
		addSpawnId(EXPLODING_ORC_GHOST, NECROMANCER_OF_THE_VALLEY);
		addSpawnId(SPOIL_REACT_MONSTER);
		addSpellFinishedId(EXPLODING_ORC_GHOST);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equals("SELF_DESTRUCTION") && (npc != null) && !npc.isDead())
		{
			final L2Playable playable = npc.getVariables().getObject("playable", L2Playable.class);
			
			if ((playable != null) && (npc.calculateDistance3D(playable) < 250))
			{
				npc.disableCoreAI(true);
				npc.doCast(SELF_DESTRUCTION.getSkill());
			}
			else if (playable != null)
			{
				startQuestTimer("SELF_DESTRUCTION", 3000, npc, null);
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (npc.getId() == NECROMANCER_OF_THE_VALLEY)
		{
			spawnGhost(npc, attacker, isSummon, 1);
		}
		else
		{
			if ((npc.getCurrentHp() < (npc.getMaxHp() / 2)) && (getRandom(100) < 5) && npc.isScriptValue(0))
			{
				npc.setScriptValue(1);
				final int rnd = getRandom(3, 5);
				for (int i = 0; i < rnd; i++)
				{
					final L2Playable playable = isSummon ? attacker.getSummon() : attacker;
					final L2Npc minion = addSpawn(DRAKOS_ASSASSIN, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), true, 0, true);
					addAttackDesire(minion, playable);
				}
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (npc.getId() == NECROMANCER_OF_THE_VALLEY)
		{
			spawnGhost(npc, killer, isSummon, 20);
		}
		else if (((L2Attackable) npc).isSpoiled())
		{
			npc.dropItem(killer, getRandom(GREATER_HERB_OF_MANA, SUPERIOR_HERB_OF_MANA), 1);
			manageMoraleBoost(killer, npc);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		((L2Attackable) npc).setOnKillDelay(0);
		if (npc.getId() == EXPLODING_ORC_GHOST)
		{
			startQuestTimer("SELF_DESTRUCTION", 3000, npc, null);
		}
		else if (CommonUtil.contains(SPAWN_ANIMATION, npc.getId()))
		{
			npc.setShowSummonAnimation(true);
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onSpellFinished(L2Npc npc, L2PcInstance player, Skill skill)
	{
		if (skill == SELF_DESTRUCTION.getSkill())
		{
			npc.doDie(player);
		}
		return super.onSpellFinished(npc, player, skill);
	}
	
	private void manageMoraleBoost(L2PcInstance player, L2Npc npc)
	{
		double points = 0;
		int moraleBoostLv = 0;
		
		if (player.isInParty() && (player.getParty().getMemberCount() >= MIN_MEMBERS) && (npc != null))
		{
			for (L2PcInstance member : player.getParty().getMembers())
			{
				if ((member.getLevel() >= MIN_LVL) && (member.getClassId().level() >= CLASS_LVL) && (npc.calculateDistance3D(member) < MIN_DISTANCE))
				{
					points += CLASS_POINTS.get(member.getClassId());
				}
			}
			
			if (points >= 3)
			{
				moraleBoostLv = 3;
			}
			else if (points >= 2)
			{
				moraleBoostLv = 2;
			}
			else if (points >= 1)
			{
				moraleBoostLv = 1;
			}
			
			for (L2PcInstance member : player.getParty().getMembers())
			{
				if (npc.calculateDistance3D(member) < MIN_DISTANCE)
				{
					switch (moraleBoostLv)
					{
						case 1:
						{
							MORALE_BOOST1.getSkill().applyEffects(member, member);
							break;
						}
						case 2:
						{
							MORALE_BOOST2.getSkill().applyEffects(member, member);
							break;
						}
						case 3:
						{
							MORALE_BOOST3.getSkill().applyEffects(member, member);
							break;
						}
					}
				}
			}
		}
	}
	
	private void spawnGhost(L2Npc npc, L2PcInstance player, boolean isSummon, int chance)
	{
		if ((npc.getScriptValue() < 2) && (getRandom(100) < chance))
		{
			int val = npc.getScriptValue();
			final L2Playable attacker = isSummon ? player.getSummon() : player;
			final L2Npc ghost1 = addSpawn(EXPLODING_ORC_GHOST, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
			ghost1.getVariables().set("playable", attacker);
			addAttackDesire(ghost1, attacker);
			val++;
			if ((val < 2) && (getRandomBoolean()))
			{
				final L2Npc ghost2 = addSpawn(WRATHFUL_ORC_GHOST, npc.getX(), npc.getY(), npc.getZ() + 20, npc.getHeading(), false, 0, false);
				addAttackDesire(ghost2, attacker);
				val++;
			}
			npc.setScriptValue(val);
		}
	}
	
	public static void main(String[] args)
	{
		new DragonValley();
	}
}
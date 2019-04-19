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
package ai.areas.BeastFarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2TamedBeastInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.serverpackets.AbstractNpcInfo;

import ai.AbstractNpcAI;
import quests.Q00020_BringUpWithLove.Q00020_BringUpWithLove;

/**
 * Growth-capable mobs: Polymorphing upon successful feeding.<br>
 * Updated to Freya.
 * @author Fulminus, Gigiikun
 */
public final class BeastFarm extends AbstractNpcAI
{
	private static final int GOLDEN_SPICE = 15474;
	private static final int CRYSTAL_SPICE = 15475;
	private static final int SKILL_GOLDEN_SPICE = 9049;
	private static final int SKILL_CRYSTAL_SPICE = 9050;
	private static final int SKILL_BLESSED_GOLDEN_SPICE = 9051;
	private static final int SKILL_BLESSED_CRYSTAL_SPICE = 9052;
	private static final int SKILL_SGRADE_GOLDEN_SPICE = 9053;
	private static final int SKILL_SGRADE_CRYSTAL_SPICE = 9054;
	private static final int[] TAMED_BEASTS =
	{
		18869,
		18870,
		18871,
		18872
	};
	private static final int TAME_CHANCE = 20;
	protected static final int[] SPECIAL_SPICE_CHANCES =
	{
		33,
		75
	};
	
	// all mobs that can eat...
	private static final int[] FEEDABLE_BEASTS =
	{
		// Kookaburras
		18873,
		18874,
		18875,
		18876,
		18877,
		18878,
		18879,
		// Cougars
		18880,
		18881,
		18882,
		18883,
		18884,
		18885,
		18886,
		// Buffalos
		18887,
		18888,
		18889,
		18890,
		18891,
		18892,
		18893,
		// Grendels
		18894,
		18895,
		18896,
		18897,
		18898,
		18899,
		18900
	};
	
	private static final Map<Integer, Integer> FEED_INFO = new ConcurrentHashMap<>();
	private static final Map<Integer, GrowthCapableMob> GROWTH_CAPABLE_MONSTERS = new HashMap<>();
	private static List<TamedBeast> TAMED_BEAST_DATA = new ArrayList<>();
	
	private BeastFarm()
	{
		addSkillSeeId(FEEDABLE_BEASTS);
		addKillId(FEEDABLE_BEASTS);
		
		GrowthCapableMob temp;
		
		// Kookabura
		temp = new GrowthCapableMob(100, 0, 18869);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18874);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18875);
		temp.addNpcIdForSkillId(SKILL_BLESSED_GOLDEN_SPICE, 18869);
		temp.addNpcIdForSkillId(SKILL_BLESSED_CRYSTAL_SPICE, 18869);
		temp.addNpcIdForSkillId(SKILL_SGRADE_GOLDEN_SPICE, 18878);
		temp.addNpcIdForSkillId(SKILL_SGRADE_CRYSTAL_SPICE, 18879);
		GROWTH_CAPABLE_MONSTERS.put(18873, temp);
		
		temp = new GrowthCapableMob(40, 1, 18869);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18876);
		GROWTH_CAPABLE_MONSTERS.put(18874, temp);
		
		temp = new GrowthCapableMob(40, 1, 18869);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18877);
		GROWTH_CAPABLE_MONSTERS.put(18875, temp);
		
		temp = new GrowthCapableMob(25, 2, 18869);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18878);
		GROWTH_CAPABLE_MONSTERS.put(18876, temp);
		
		temp = new GrowthCapableMob(25, 2, 18869);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18879);
		GROWTH_CAPABLE_MONSTERS.put(18877, temp);
		
		// Cougar
		temp = new GrowthCapableMob(100, 0, 18870);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18881);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18882);
		temp.addNpcIdForSkillId(SKILL_BLESSED_GOLDEN_SPICE, 18870);
		temp.addNpcIdForSkillId(SKILL_BLESSED_CRYSTAL_SPICE, 18870);
		temp.addNpcIdForSkillId(SKILL_SGRADE_GOLDEN_SPICE, 18885);
		temp.addNpcIdForSkillId(SKILL_SGRADE_CRYSTAL_SPICE, 18886);
		GROWTH_CAPABLE_MONSTERS.put(18880, temp);
		
		temp = new GrowthCapableMob(40, 1, 18870);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18883);
		GROWTH_CAPABLE_MONSTERS.put(18881, temp);
		
		temp = new GrowthCapableMob(40, 1, 18870);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18884);
		GROWTH_CAPABLE_MONSTERS.put(18882, temp);
		
		temp = new GrowthCapableMob(25, 2, 18870);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18885);
		GROWTH_CAPABLE_MONSTERS.put(18883, temp);
		
		temp = new GrowthCapableMob(25, 2, 18870);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18886);
		GROWTH_CAPABLE_MONSTERS.put(18884, temp);
		
		// Buffalo
		temp = new GrowthCapableMob(100, 0, 18871);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18888);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18889);
		temp.addNpcIdForSkillId(SKILL_BLESSED_GOLDEN_SPICE, 18871);
		temp.addNpcIdForSkillId(SKILL_BLESSED_CRYSTAL_SPICE, 18871);
		temp.addNpcIdForSkillId(SKILL_SGRADE_GOLDEN_SPICE, 18892);
		temp.addNpcIdForSkillId(SKILL_SGRADE_CRYSTAL_SPICE, 18893);
		GROWTH_CAPABLE_MONSTERS.put(18887, temp);
		
		temp = new GrowthCapableMob(40, 1, 18871);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18890);
		GROWTH_CAPABLE_MONSTERS.put(18888, temp);
		
		temp = new GrowthCapableMob(40, 1, 18871);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18891);
		GROWTH_CAPABLE_MONSTERS.put(18889, temp);
		
		temp = new GrowthCapableMob(25, 2, 18871);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18892);
		GROWTH_CAPABLE_MONSTERS.put(18890, temp);
		
		temp = new GrowthCapableMob(25, 2, 18871);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18893);
		GROWTH_CAPABLE_MONSTERS.put(18891, temp);
		
		// Grendel
		temp = new GrowthCapableMob(100, 0, 18872);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18895);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18896);
		temp.addNpcIdForSkillId(SKILL_BLESSED_GOLDEN_SPICE, 18872);
		temp.addNpcIdForSkillId(SKILL_BLESSED_CRYSTAL_SPICE, 18872);
		temp.addNpcIdForSkillId(SKILL_SGRADE_GOLDEN_SPICE, 18899);
		temp.addNpcIdForSkillId(SKILL_SGRADE_CRYSTAL_SPICE, 18900);
		GROWTH_CAPABLE_MONSTERS.put(18894, temp);
		
		temp = new GrowthCapableMob(40, 1, 18872);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18897);
		GROWTH_CAPABLE_MONSTERS.put(18895, temp);
		
		temp = new GrowthCapableMob(40, 1, 18872);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18898);
		GROWTH_CAPABLE_MONSTERS.put(18896, temp);
		
		temp = new GrowthCapableMob(25, 2, 18872);
		temp.addNpcIdForSkillId(SKILL_GOLDEN_SPICE, 18899);
		GROWTH_CAPABLE_MONSTERS.put(18897, temp);
		
		temp = new GrowthCapableMob(25, 2, 18872);
		temp.addNpcIdForSkillId(SKILL_CRYSTAL_SPICE, 18900);
		GROWTH_CAPABLE_MONSTERS.put(18898, temp);
		
		// Tamed beasts data
		TAMED_BEAST_DATA.add(new TamedBeast("%name% of Focus", new SkillHolder(6432, 1), new SkillHolder(6668, 1)));
		TAMED_BEAST_DATA.add(new TamedBeast("%name% of Guiding", new SkillHolder(6433, 1), new SkillHolder(6670, 1)));
		TAMED_BEAST_DATA.add(new TamedBeast("%name% of Swifth", new SkillHolder(6434, 1), new SkillHolder(6667, 1)));
		TAMED_BEAST_DATA.add(new TamedBeast("Berserker %name%", new SkillHolder(6671, 1)));
		TAMED_BEAST_DATA.add(new TamedBeast("%name% of Protect", new SkillHolder(6669, 1), new SkillHolder(6672, 1)));
		TAMED_BEAST_DATA.add(new TamedBeast("%name% of Vigor", new SkillHolder(6431, 1), new SkillHolder(6666, 1)));
	}
	
	public void spawnNext(L2Npc npc, L2PcInstance player, int nextNpcId, int food)
	{
		// remove the feedinfo of the mob that got despawned, if any
		if (FEED_INFO.containsKey(npc.getObjectId()))
		{
			if (FEED_INFO.get(npc.getObjectId()) == player.getObjectId())
			{
				FEED_INFO.remove(npc.getObjectId());
			}
		}
		// despawn the old mob
		// TODO: same code? FIXED?
		/*
		 * if (_GrowthCapableMobs.get(npc.getNpcId()).getGrowthLevel() == 0) { npc.deleteMe(); } else {
		 */
		npc.deleteMe();
		// }
		
		// if this is finally a trained mob, then despawn any other trained mobs that the
		// player might have and initialize the Tamed Beast.
		if (CommonUtil.contains(TAMED_BEASTS, nextNpcId))
		{
			final L2TamedBeastInstance nextNpc = new L2TamedBeastInstance(nextNpcId, player, food, npc.getX(), npc.getY(), npc.getZ(), true);
			
			final TamedBeast beast = TAMED_BEAST_DATA.get(getRandom(TAMED_BEAST_DATA.size()));
			String name = beast.getName();
			switch (nextNpcId)
			{
				case 18869:
				{
					name = name.replace("%name%", "Alpine Kookaburra");
					break;
				}
				case 18870:
				{
					name = name.replace("%name%", "Alpine Cougar");
					break;
				}
				case 18871:
				{
					name = name.replace("%name%", "Alpine Buffalo");
					break;
				}
				case 18872:
				{
					name = name.replace("%name%", "Alpine Grendel");
					break;
				}
			}
			nextNpc.setName(name);
			nextNpc.broadcastPacket(new AbstractNpcInfo.NpcInfo(nextNpc, player));
			nextNpc.setRunning();
			
			final SkillData st = SkillData.getInstance();
			for (SkillHolder sh : beast.getSkills())
			{
				nextNpc.addBeastSkill(st.getSkill(sh.getSkillId(), sh.getSkillLevel()));
			}
			
			Q00020_BringUpWithLove.checkJewelOfInnocence(player);
		}
		else
		{
			// if not trained, the newly spawned mob will automatically be agro against its feeder
			// (what happened to "never bite the hand that feeds you" anyway?!)
			final L2Attackable nextNpc = (L2Attackable) addSpawn(nextNpcId, npc);
			
			// register the player in the feedinfo for the mob that just spawned
			FEED_INFO.put(nextNpc.getObjectId(), player.getObjectId());
			nextNpc.setRunning();
			nextNpc.addDamageHate(player, 0, 99999);
			nextNpc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
			
			player.setTarget(nextNpc);
		}
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isSummon)
	{
		// this behavior is only run when the target of skill is the passed npc (chest)
		// i.e. when the player is attempting to open the chest using a skill
		if (!CommonUtil.contains(targets, npc))
		{
			return super.onSkillSee(npc, caster, skill, targets, isSummon);
		}
		// gather some values on local variables
		final int npcId = npc.getId();
		final int skillId = skill.getId();
		// check if the npc and skills used are valid for this script. Exit if invalid.
		if (!CommonUtil.contains(FEEDABLE_BEASTS, npcId) || ((skillId != SKILL_GOLDEN_SPICE) && (skillId != SKILL_CRYSTAL_SPICE) && (skillId != SKILL_BLESSED_GOLDEN_SPICE) && (skillId != SKILL_BLESSED_CRYSTAL_SPICE) && (skillId != SKILL_SGRADE_GOLDEN_SPICE) && (skillId != SKILL_SGRADE_CRYSTAL_SPICE)))
		{
			return super.onSkillSee(npc, caster, skill, targets, isSummon);
		}
		
		// first gather some values on local variables
		final int objectId = npc.getObjectId();
		int growthLevel = 3; // if a mob is in FEEDABLE_BEASTS but not in _GrowthCapableMobs, then it's at max growth (3)
		if (GROWTH_CAPABLE_MONSTERS.containsKey(npcId))
		{
			growthLevel = GROWTH_CAPABLE_MONSTERS.get(npcId).getGrowthLevel();
		}
		
		// prevent exploit which allows 2 players to simultaneously raise the same 0-growth beast
		// If the mob is at 0th level (when it still listens to all feeders) lock it to the first feeder!
		if ((growthLevel == 0) && FEED_INFO.containsKey(objectId))
		{
			return super.onSkillSee(npc, caster, skill, targets, isSummon);
		}
		
		FEED_INFO.put(objectId, caster.getObjectId());
		
		// display the social action of the beast eating the food.
		npc.broadcastSocialAction(2);
		
		int food = 0;
		if ((skillId == SKILL_GOLDEN_SPICE) || (skillId == SKILL_BLESSED_GOLDEN_SPICE))
		{
			food = GOLDEN_SPICE;
		}
		else if ((skillId == SKILL_CRYSTAL_SPICE) || (skillId == SKILL_BLESSED_CRYSTAL_SPICE))
		{
			food = CRYSTAL_SPICE;
		}
		
		// if this pet can't grow, it's all done.
		if (GROWTH_CAPABLE_MONSTERS.containsKey(npcId))
		{
			// do nothing if this mob doesn't eat the specified food (food gets consumed but has no effect).
			final int newNpcId = GROWTH_CAPABLE_MONSTERS.get(npcId).getLeveledNpcId(skillId);
			if (newNpcId == -1)
			{
				if (growthLevel == 0)
				{
					FEED_INFO.remove(objectId);
					npc.setRunning();
					((L2Attackable) npc).addDamageHate(caster, 0, 1);
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, caster);
				}
				return super.onSkillSee(npc, caster, skill, targets, isSummon);
			}
			else if ((growthLevel > 0) && (FEED_INFO.get(objectId) != caster.getObjectId()))
			{
				// check if this is the same player as the one who raised it from growth 0.
				// if no, then do not allow a chance to raise the pet (food gets consumed but has no effect).
				return super.onSkillSee(npc, caster, skill, targets, isSummon);
			}
			spawnNext(npc, caster, newNpcId, food);
		}
		else
		{
			caster.sendMessage("The beast spit out the feed instead of eating it.");
			npc.dropItem(caster, food, 1);
		}
		return super.onSkillSee(npc, caster, skill, targets, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		// remove the feedinfo of the mob that got killed, if any
		if (FEED_INFO.containsKey(npc.getObjectId()))
		{
			FEED_INFO.remove(npc.getObjectId());
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	// all mobs that grow by eating
	private static class GrowthCapableMob
	{
		private final int _chance;
		private final int _growthLevel;
		private final int _tameNpcId;
		private final Map<Integer, Integer> _skillSuccessNpcIdList = new HashMap<>();
		
		public GrowthCapableMob(int chance, int growthLevel, int tameNpcId)
		{
			_chance = chance;
			_growthLevel = growthLevel;
			_tameNpcId = tameNpcId;
		}
		
		public void addNpcIdForSkillId(int skillId, int npcId)
		{
			_skillSuccessNpcIdList.put(skillId, npcId);
		}
		
		public int getGrowthLevel()
		{
			return _growthLevel;
		}
		
		public int getLeveledNpcId(int skillId)
		{
			if (!_skillSuccessNpcIdList.containsKey(skillId))
			{
				return -1;
			}
			else if ((skillId == SKILL_BLESSED_GOLDEN_SPICE) || (skillId == SKILL_BLESSED_CRYSTAL_SPICE) || (skillId == SKILL_SGRADE_GOLDEN_SPICE) || (skillId == SKILL_SGRADE_CRYSTAL_SPICE))
			{
				if (getRandom(100) < SPECIAL_SPICE_CHANCES[0])
				{
					if (getRandom(100) < SPECIAL_SPICE_CHANCES[1])
					{
						return _skillSuccessNpcIdList.get(skillId);
					}
					else if ((skillId == SKILL_BLESSED_GOLDEN_SPICE) || (skillId == SKILL_SGRADE_GOLDEN_SPICE))
					{
						return _skillSuccessNpcIdList.get(SKILL_GOLDEN_SPICE);
					}
					else
					{
						return _skillSuccessNpcIdList.get(SKILL_CRYSTAL_SPICE);
					}
				}
				return -1;
			}
			else if ((_growthLevel == 2) && (getRandom(100) < TAME_CHANCE))
			{
				return _tameNpcId;
			}
			else if (getRandom(100) < _chance)
			{
				return _skillSuccessNpcIdList.get(skillId);
			}
			else
			{
				return -1;
			}
		}
	}
	
	private static class TamedBeast
	{
		private final String name;
		private final SkillHolder[] sh;
		
		public TamedBeast(String beastName, SkillHolder... holders)
		{
			name = beastName;
			sh = holders;
		}
		
		public String getName()
		{
			return name;
		}
		
		public SkillHolder[] getSkills()
		{
			return sh;
		}
	}
	
	public static void main(String[] args)
	{
		new BeastFarm();
	}
}

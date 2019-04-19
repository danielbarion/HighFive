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
package ai.areas.Gracia.AI;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.instancemanager.QuestManager;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.NpcStringId;

import ai.AbstractNpcAI;
import ai.areas.Gracia.AI.NPC.Nemo.Nemo;

/**
 * Maguen AI.
 * @author St3eT
 */
public final class Maguen extends AbstractNpcAI
{
	// NPC
	private static final int MAGUEN = 18839; // Wild Maguen
	private static final int[] ELITES =
	{
		22750, // Elite Bgurent (Bistakon)
		22751, // Elite Brakian (Bistakon)
		22752, // Elite Groikan (Bistakon)
		22753, // Elite Treykan (Bistakon)
		22757, // Elite Turtlelian (Reptilikon)
		22758, // Elite Krajian (Reptilikon)
		22759, // Elite Tardyon (Reptilikon)
		22763, // Elite Kanibi (Kokracon)
		22764, // Elite Kiriona (Kokracon)
		22765, // Elite Kaiona (Kokracon)
	};
	// Item
	private static final int MAGUEN_PET = 15488; // Maguen Pet Collar
	private static final int ELITE_MAGUEN_PET = 15489; // Elite Maguen Pet Collar
	// Skills
	private static final SkillHolder MACHINE = new SkillHolder(9060, 1); // Maguen Machine
	private static final SkillHolder B_BUFF_1 = new SkillHolder(6343, 1); // Maguen Plasma - Power
	private static final SkillHolder B_BUFF_2 = new SkillHolder(6343, 2); // Maguen Plasma - Power
	private static final SkillHolder C_BUFF_1 = new SkillHolder(6365, 1); // Maguen Plasma - Speed
	private static final SkillHolder C_BUFF_2 = new SkillHolder(6365, 2); // Maguen Plasma - Speed
	private static final SkillHolder R_BUFF_1 = new SkillHolder(6366, 1); // Maguen Plasma - Critical
	private static final SkillHolder R_BUFF_2 = new SkillHolder(6366, 2); // Maguen Plasma - Critical
	private static final SkillHolder B_PLASMA1 = new SkillHolder(6367, 1); // Maguen Plasma - Bistakon
	private static final SkillHolder B_PLASMA2 = new SkillHolder(6367, 2); // Maguen Plasma - Bistakon
	private static final SkillHolder B_PLASMA3 = new SkillHolder(6367, 3); // Maguen Plasma - Bistakon
	private static final SkillHolder C_PLASMA1 = new SkillHolder(6368, 1); // Maguen Plasma - Cokrakon
	private static final SkillHolder C_PLASMA2 = new SkillHolder(6368, 2); // Maguen Plasma - Cokrakon
	private static final SkillHolder C_PLASMA3 = new SkillHolder(6368, 3); // Maguen Plasma - Cokrakon
	private static final SkillHolder R_PLASMA1 = new SkillHolder(6369, 1); // Maguen Plasma - Reptilikon
	private static final SkillHolder R_PLASMA2 = new SkillHolder(6369, 2); // Maguen Plasma - Reptilikon
	private static final SkillHolder R_PLASMA3 = new SkillHolder(6369, 3); // Maguen Plasma - Reptilikon
	
	public Maguen()
	{
		addKillId(ELITES);
		addSkillSeeId(MAGUEN);
		addSpellFinishedId(MAGUEN);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ((npc == null) || (player == null))
		{
			return null;
		}
		
		switch (event)
		{
			case "SPAWN_MAGUEN":
			{
				final L2Npc maguen = addSpawn(MAGUEN, npc.getLocation(), true, 60000, true);
				maguen.getVariables().set("SUMMON_PLAYER", player);
				maguen.setTitle(player.getName());
				maguen.setRunning();
				maguen.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, player);
				maguen.broadcastStatusUpdate();
				showOnScreenMsg(player, NpcStringId.MAGUEN_APPEARANCE, 2, 4000);
				startQuestTimer("DIST_CHECK_TIMER", 1000, maguen, player);
				break;
			}
			case "DIST_CHECK_TIMER":
			{
				if ((npc.calculateDistance3D(player) < 100) && (npc.getVariables().getInt("IS_NEAR_PLAYER") == 0))
				{
					npc.getVariables().set("IS_NEAR_PLAYER", 1);
					startQuestTimer("FIRST_TIMER", 4000, npc, player);
				}
				else
				{
					startQuestTimer("DIST_CHECK_TIMER", 1000, npc, player);
				}
				break;
			}
			case "FIRST_TIMER":
			{
				npc.getAI().stopFollow();
				final int randomEffect = getRandom(1, 3);
				npc.setDisplayEffect(randomEffect);
				npc.getVariables().set("NPC_EFFECT", randomEffect);
				startQuestTimer("SECOND_TIMER", 5000 + getRandom(300), npc, player);
				npc.broadcastSocialAction(getRandom(1, 3));
				break;
			}
			case "SECOND_TIMER":
			{
				final int randomEffect = getRandom(1, 3);
				npc.setDisplayEffect(4);
				npc.setDisplayEffect(randomEffect);
				npc.getVariables().set("NPC_EFFECT", randomEffect);
				startQuestTimer("THIRD_TIMER", 4600 + getRandom(600), npc, player);
				npc.broadcastSocialAction(getRandom(1, 3));
				break;
			}
			case "THIRD_TIMER":
			{
				final int randomEffect = getRandom(1, 3);
				npc.setDisplayEffect(4);
				npc.setDisplayEffect(randomEffect);
				npc.getVariables().set("NPC_EFFECT", randomEffect);
				startQuestTimer("FORTH_TIMER", 4200 + getRandom(900), npc, player);
				npc.broadcastSocialAction(getRandom(1, 3));
				break;
			}
			case "FORTH_TIMER":
			{
				npc.getVariables().set("NPC_EFFECT", 0);
				npc.setDisplayEffect(4);
				startQuestTimer("END_TIMER", 500, npc, player);
				npc.broadcastSocialAction(getRandom(1, 3));
				break;
			}
			case "END_TIMER":
			{
				if (npc.getVariables().getInt("TEST_MAGUEN") == 1)
				{
					player.getEffectList().stopSkillEffects(true, B_PLASMA1.getSkill().getAbnormalType());
					player.getEffectList().stopSkillEffects(true, C_PLASMA1.getSkill().getAbnormalType());
					player.getEffectList().stopSkillEffects(true, R_PLASMA1.getSkill().getAbnormalType());
					nemoAi().notifyEvent("DECREASE_COUNT", npc, player);
				}
				npc.doDie(null);
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onSpellFinished(L2Npc npc, L2PcInstance player, Skill skill)
	{
		final BuffInfo b_info = player.getEffectList().getBuffInfoByAbnormalType(B_PLASMA1.getSkill().getAbnormalType());
		final BuffInfo c_info = player.getEffectList().getBuffInfoByAbnormalType(C_PLASMA1.getSkill().getAbnormalType());
		final BuffInfo r_info = player.getEffectList().getBuffInfoByAbnormalType(R_PLASMA1.getSkill().getAbnormalType());
		
		final int b = b_info == null ? 0 : b_info.getSkill().getAbnormalLvl();
		final int c = c_info == null ? 0 : c_info.getSkill().getAbnormalLvl();
		final int r = r_info == null ? 0 : r_info.getSkill().getAbnormalLvl();
		
		if ((b == 3) && (c == 0) && (r == 0))
		{
			showOnScreenMsg(player, NpcStringId.ENOUGH_MAGUEN_PLASMA_BISTAKON_HAVE_GATHERED, 2, 4000);
			player.getEffectList().stopSkillEffects(true, B_PLASMA1.getSkill().getAbnormalType());
			npc.setTarget(player);
			npc.doCast((getRandom(100) < 70) ? B_BUFF_1.getSkill() : B_BUFF_2.getSkill());
			maguenPetChance(player);
			startQuestTimer("END_TIMER", 3000, npc, player);
		}
		else if ((b == 0) && (c == 3) && (r == 0))
		{
			showOnScreenMsg(player, NpcStringId.ENOUGH_MAGUEN_PLASMA_COKRAKON_HAVE_GATHERED, 2, 4000);
			player.getEffectList().stopSkillEffects(true, C_PLASMA1.getSkill().getAbnormalType());
			npc.setTarget(player);
			npc.doCast((getRandom(100) < 70) ? C_BUFF_1.getSkill() : C_BUFF_2.getSkill());
			maguenPetChance(player);
			startQuestTimer("END_TIMER", 3000, npc, player);
		}
		else if ((b == 0) && (c == 0) && (r == 3))
		{
			showOnScreenMsg(player, NpcStringId.ENOUGH_MAGUEN_PLASMA_LEPTILIKON_HAVE_GATHERED, 2, 4000);
			player.getEffectList().stopSkillEffects(true, R_PLASMA1.getSkill().getAbnormalType());
			npc.setTarget(player);
			npc.doCast((getRandom(100) < 70) ? R_BUFF_1.getSkill() : R_BUFF_2.getSkill());
			maguenPetChance(player);
			startQuestTimer("END_TIMER", 3000, npc, player);
		}
		else if ((b + c + r) == 3)
		{
			if ((b == 1) && (c == 1) && (r == 1))
			{
				player.getEffectList().stopSkillEffects(true, B_PLASMA1.getSkill().getAbnormalType());
				player.getEffectList().stopSkillEffects(true, C_PLASMA1.getSkill().getAbnormalType());
				player.getEffectList().stopSkillEffects(true, R_PLASMA1.getSkill().getAbnormalType());
				showOnScreenMsg(player, NpcStringId.THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_AND_ARE_HARMONIZED, 2, 4000);
				SkillHolder skillToCast = null;
				switch (getRandom(3))
				{
					case 0:
					{
						skillToCast = (getRandom(100) < 70) ? B_BUFF_1 : B_BUFF_2;
						break;
					}
					case 1:
					{
						skillToCast = (getRandom(100) < 70) ? C_BUFF_1 : C_BUFF_2;
						break;
					}
					case 2:
					{
						skillToCast = (getRandom(100) < 70) ? R_BUFF_1 : R_BUFF_2;
						break;
					}
				}
				
				if (skillToCast != null)
				{
					npc.setTarget(player);
					npc.doCast(skillToCast.getSkill());
				}
				maguenPetChance(player);
				startQuestTimer("END_TIMER", 3000, npc, player);
			}
			else
			{
				showOnScreenMsg(player, NpcStringId.THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_BUT_THEY_ARE_RAMMING_INTO_EACH_OTHER_EXPLODING_AND_DYING, 2, 4000);
				player.getEffectList().stopSkillEffects(true, B_PLASMA1.getSkill().getAbnormalType());
				player.getEffectList().stopSkillEffects(true, C_PLASMA1.getSkill().getAbnormalType());
				player.getEffectList().stopSkillEffects(true, R_PLASMA1.getSkill().getAbnormalType());
			}
		}
		else
		{
			startQuestTimer("END_TIMER", 1000, npc, player);
		}
		npc.setDisplayEffect(4);
		return super.onSpellFinished(npc, player, skill);
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isSummon)
	{
		if ((skill == MACHINE.getSkill()) && (caster == npc.getVariables().getObject("SUMMON_PLAYER", L2PcInstance.class)))
		{
			if ((npc.getVariables().getInt("NPC_EFFECT") != 0) && (npc.getVariables().getInt("BLOCKED_SKILLSEE") == 0))
			{
				final BuffInfo i1_info = caster.getEffectList().getBuffInfoByAbnormalType(B_PLASMA1.getSkill().getAbnormalType());
				final BuffInfo i2_info = caster.getEffectList().getBuffInfoByAbnormalType(C_PLASMA1.getSkill().getAbnormalType());
				final BuffInfo i3_info = caster.getEffectList().getBuffInfoByAbnormalType(R_PLASMA1.getSkill().getAbnormalType());
				
				final int i1 = i1_info == null ? 0 : i1_info.getSkill().getAbnormalLvl();
				final int i2 = i2_info == null ? 0 : i2_info.getSkill().getAbnormalLvl();
				final int i3 = i3_info == null ? 0 : i3_info.getSkill().getAbnormalLvl();
				
				caster.getEffectList().stopSkillEffects(true, B_PLASMA1.getSkill().getAbnormalType());
				caster.getEffectList().stopSkillEffects(true, C_PLASMA1.getSkill().getAbnormalType());
				caster.getEffectList().stopSkillEffects(true, R_PLASMA1.getSkill().getAbnormalType());
				cancelQuestTimer("FIRST_TIMER", npc, caster);
				cancelQuestTimer("SECOND_TIMER", npc, caster);
				cancelQuestTimer("THIRD_TIMER", npc, caster);
				cancelQuestTimer("FORTH_TIMER", npc, caster);
				npc.getVariables().set("BLOCKED_SKILLSEE", 1);
				
				SkillHolder skillToCast = null;
				switch (npc.getVariables().getInt("NPC_EFFECT"))
				{
					case 1:
					{
						switch (i1)
						{
							case 0:
							{
								skillToCast = B_PLASMA1;
								break;
							}
							case 1:
							{
								skillToCast = B_PLASMA2;
								break;
							}
							case 2:
							{
								skillToCast = B_PLASMA3;
								break;
							}
						}
						break;
					}
					case 2:
					{
						switch (i2)
						{
							case 0:
							{
								skillToCast = C_PLASMA1;
								break;
							}
							case 1:
							{
								skillToCast = C_PLASMA2;
								break;
							}
							case 2:
							{
								skillToCast = C_PLASMA3;
								break;
							}
						}
						break;
					}
					case 3:
					{
						switch (i3)
						{
							case 0:
							{
								skillToCast = R_PLASMA1;
								break;
							}
							case 1:
							{
								skillToCast = R_PLASMA2;
								break;
							}
							case 2:
							{
								skillToCast = R_PLASMA3;
								break;
							}
						}
						break;
					}
				}
				
				if (skillToCast != null)
				{
					npc.setTarget(caster);
					npc.doCast(skillToCast.getSkill());
				}
			}
		}
		return super.onSkillSee(npc, caster, skill, targets, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (killer.isInParty())
		{
			final L2PcInstance partyMember = getRandomPartyMember(killer);
			final int i0 = 10 + (10 * killer.getParty().getMemberCount());
			
			if ((getRandom(1000) < i0) && (npc.calculateDistance3D(killer) < 2000) && (npc.calculateDistance3D(partyMember) < 2000))
			{
				notifyEvent("SPAWN_MAGUEN", npc, partyMember);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	private void maguenPetChance(L2PcInstance player)
	{
		final int chance1 = getRandom(10000);
		final int chance2 = getRandom(20);
		if ((chance1 == 0) && (chance2 != 0))
		{
			giveItems(player, MAGUEN_PET, 1);
		}
		else if ((chance1 == 0) && (chance2 == 0))
		{
			giveItems(player, ELITE_MAGUEN_PET, 1);
		}
	}
	
	private Quest nemoAi()
	{
		return QuestManager.getInstance().getQuest(Nemo.class.getSimpleName());
	}
}
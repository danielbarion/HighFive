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
package ai.bosses.Orfen;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.GrandBossManager;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2Spawn;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2GrandBossInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.zone.type.L2BossZone;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.network.serverpackets.PlaySound;

import ai.AbstractNpcAI;

/**
 * Orfen's AI
 * @author Emperorc
 */
public final class Orfen extends AbstractNpcAI
{
	private static final Location[] POS =
	{
		new Location(43728, 17220, -4342),
		new Location(55024, 17368, -5412),
		new Location(53504, 21248, -5486),
		new Location(53248, 24576, -5262)
	};
	
	private static final NpcStringId[] TEXT =
	{
		NpcStringId.S1_STOP_KIDDING_YOURSELF_ABOUT_YOUR_OWN_POWERLESSNESS,
		NpcStringId.S1_I_LL_MAKE_YOU_FEEL_WHAT_TRUE_FEAR_IS,
		NpcStringId.YOU_RE_REALLY_STUPID_TO_HAVE_CHALLENGED_ME_S1_GET_READY,
		NpcStringId.S1_DO_YOU_THINK_THAT_S_GOING_TO_WORK
	};
	
	private static final int ORFEN = 29014;
	// private static final int RAIKEL = 29015;
	private static final int RAIKEL_LEOS = 29016;
	// private static final int RIBA = 29017;
	private static final int RIBA_IREN = 29018;
	
	private static boolean _IsTeleported;
	private static final List<L2Attackable> MINIONS = new CopyOnWriteArrayList<>();
	private static L2BossZone ZONE;
	
	private static final byte ALIVE = 0;
	private static final byte DEAD = 1;
	
	private Orfen()
	{
		final int[] mobs =
		{
			ORFEN,
			RAIKEL_LEOS,
			RIBA_IREN
		};
		registerMobs(mobs);
		_IsTeleported = false;
		ZONE = GrandBossManager.getInstance().getZone(POS[0]);
		final StatsSet info = GrandBossManager.getInstance().getStatsSet(ORFEN);
		if (GrandBossManager.getInstance().getBossStatus(ORFEN) == DEAD)
		{
			// load the unlock date and time for Orfen from DB
			final long temp = info.getLong("respawn_time") - System.currentTimeMillis();
			// if Orfen is locked until a certain time, mark it so and start the unlock timer
			// the unlock time has not yet expired.
			if (temp > 0)
			{
				startQuestTimer("orfen_unlock", temp, null, null);
			}
			else
			{
				// the time has already expired while the server was offline. Immediately spawn Orfen.
				final int i = getRandom(10);
				Location loc;
				if (i < 4)
				{
					loc = POS[1];
				}
				else if (i < 7)
				{
					loc = POS[2];
				}
				else
				{
					loc = POS[3];
				}
				final L2GrandBossInstance orfen = (L2GrandBossInstance) addSpawn(ORFEN, loc, false, 0);
				GrandBossManager.getInstance().setBossStatus(ORFEN, ALIVE);
				spawnBoss(orfen);
			}
		}
		else
		{
			final int loc_x = info.getInt("loc_x");
			final int loc_y = info.getInt("loc_y");
			final int loc_z = info.getInt("loc_z");
			final int heading = info.getInt("heading");
			double hp = info.getDouble("currentHP");
			double mp = info.getDouble("currentMP");
			final L2GrandBossInstance orfen = (L2GrandBossInstance) addSpawn(ORFEN, loc_x, loc_y, loc_z, heading, false, 0);
			orfen.setCurrentHpMp(hp, mp);
			spawnBoss(orfen);
		}
	}
	
	public void setSpawnPoint(L2Npc npc, int index)
	{
		((L2Attackable) npc).clearAggroList();
		npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null, null);
		final L2Spawn spawn = npc.getSpawn();
		spawn.setLocation(POS[index]);
		npc.teleToLocation(POS[index], false);
	}
	
	public void spawnBoss(L2GrandBossInstance npc)
	{
		GrandBossManager.getInstance().addBoss(npc);
		npc.broadcastPacket(new PlaySound(1, "BS01_A", 1, npc.getObjectId(), npc.getX(), npc.getY(), npc.getZ()));
		startQuestTimer("check_orfen_pos", 10000, npc, null, true);
		// Spawn minions
		final int x = npc.getX();
		final int y = npc.getY();
		L2Attackable mob;
		mob = (L2Attackable) addSpawn(RAIKEL_LEOS, x + 100, y + 100, npc.getZ(), 0, false, 0);
		mob.setIsRaidMinion(true);
		MINIONS.add(mob);
		mob = (L2Attackable) addSpawn(RAIKEL_LEOS, x + 100, y - 100, npc.getZ(), 0, false, 0);
		mob.setIsRaidMinion(true);
		MINIONS.add(mob);
		mob = (L2Attackable) addSpawn(RAIKEL_LEOS, x - 100, y + 100, npc.getZ(), 0, false, 0);
		mob.setIsRaidMinion(true);
		MINIONS.add(mob);
		mob = (L2Attackable) addSpawn(RAIKEL_LEOS, x - 100, y - 100, npc.getZ(), 0, false, 0);
		mob.setIsRaidMinion(true);
		MINIONS.add(mob);
		startQuestTimer("check_minion_loc", 10000, npc, null, true);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equalsIgnoreCase("orfen_unlock"))
		{
			final int i = getRandom(10);
			Location loc;
			if (i < 4)
			{
				loc = POS[1];
			}
			else if (i < 7)
			{
				loc = POS[2];
			}
			else
			{
				loc = POS[3];
			}
			final L2GrandBossInstance orfen = (L2GrandBossInstance) addSpawn(ORFEN, loc, false, 0);
			GrandBossManager.getInstance().setBossStatus(ORFEN, ALIVE);
			spawnBoss(orfen);
		}
		else if (event.equalsIgnoreCase("check_orfen_pos"))
		{
			if ((_IsTeleported && (npc.getCurrentHp() > (npc.getMaxHp() * 0.95))) || (!ZONE.isInsideZone(npc) && !_IsTeleported))
			{
				setSpawnPoint(npc, getRandom(3) + 1);
				_IsTeleported = false;
			}
			else if (_IsTeleported && !ZONE.isInsideZone(npc))
			{
				setSpawnPoint(npc, 0);
			}
		}
		else if (event.equalsIgnoreCase("check_minion_loc"))
		{
			for (L2Attackable mob : MINIONS)
			{
				if (!npc.isInsideRadius2D(mob, 3000))
				{
					mob.teleToLocation(npc.getLocation());
					((L2Attackable) npc).clearAggroList();
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null, null);
				}
			}
		}
		else if (event.equalsIgnoreCase("despawn_minions"))
		{
			for (L2Attackable mob : MINIONS)
			{
				if (mob != null)
				{
					mob.decayMe();
				}
			}
			MINIONS.clear();
		}
		else if (event.equalsIgnoreCase("spawn_minion"))
		{
			final L2Attackable mob = (L2Attackable) addSpawn(RAIKEL_LEOS, npc.getX(), npc.getY(), npc.getZ(), 0, false, 0);
			mob.setIsRaidMinion(true);
			MINIONS.add(mob);
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isSummon)
	{
		if (npc.getId() == ORFEN)
		{
			final L2Character originalCaster = isSummon ? caster.getSummon() : caster;
			if ((skill.getEffectPoint() > 0) && (getRandom(5) == 0) && npc.isInsideRadius2D(originalCaster, 1000))
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), TEXT[getRandom(4)]);
				packet.addStringParameter(caster.getName());
				npc.broadcastPacket(packet);
				originalCaster.teleToLocation(npc.getLocation());
				npc.setTarget(originalCaster);
				npc.doCast(SkillData.getInstance().getSkill(4064, 1));
			}
		}
		return super.onSkillSee(npc, caster, skill, targets, isSummon);
	}
	
	@Override
	public String onFactionCall(L2Npc npc, L2Npc caller, L2PcInstance attacker, boolean isSummon)
	{
		if ((caller == null) || (npc == null) || npc.isCastingNow())
		{
			return super.onFactionCall(npc, caller, attacker, isSummon);
		}
		final int npcId = npc.getId();
		final int callerId = caller.getId();
		if ((npcId == RAIKEL_LEOS) && (getRandom(20) == 0))
		{
			npc.setTarget(attacker);
			npc.doCast(SkillData.getInstance().getSkill(4067, 4));
		}
		else if (npcId == RIBA_IREN)
		{
			int chance = 1;
			if (callerId == ORFEN)
			{
				chance = 9;
			}
			if ((callerId != RIBA_IREN) && (caller.getCurrentHp() < (caller.getMaxHp() / 2.0)) && (getRandom(10) < chance))
			{
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null, null);
				npc.setTarget(caller);
				npc.doCast(SkillData.getInstance().getSkill(4516, 1));
			}
		}
		return super.onFactionCall(npc, caller, attacker, isSummon);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		final int npcId = npc.getId();
		if (npcId == ORFEN)
		{
			if (!_IsTeleported && ((npc.getCurrentHp() - damage) < (npc.getMaxHp() / 2)))
			{
				_IsTeleported = true;
				setSpawnPoint(npc, 0);
			}
			else if (npc.isInsideRadius2D(attacker, 1000) && !npc.isInsideRadius2D(attacker, 300) && (getRandom(10) == 0))
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npcId, TEXT[getRandom(3)]);
				packet.addStringParameter(attacker.getName());
				npc.broadcastPacket(packet);
				attacker.teleToLocation(npc.getLocation());
				npc.setTarget(attacker);
				npc.doCast(SkillData.getInstance().getSkill(4064, 1));
			}
		}
		else if ((npcId == RIBA_IREN) && !npc.isCastingNow() && ((npc.getCurrentHp() - damage) < (npc.getMaxHp() / 2.0)))
		{
			npc.setTarget(attacker);
			npc.doCast(SkillData.getInstance().getSkill(4516, 1));
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (npc.getId() == ORFEN)
		{
			npc.broadcastPacket(new PlaySound(1, "BS02_D", 1, npc.getObjectId(), npc.getX(), npc.getY(), npc.getZ()));
			GrandBossManager.getInstance().setBossStatus(ORFEN, DEAD);
			// Calculate Min and Max respawn times randomly.
			final long respawnTime = (Config.ORFEN_SPAWN_INTERVAL + getRandom(-Config.ORFEN_SPAWN_RANDOM, Config.ORFEN_SPAWN_RANDOM)) * 3600000;
			startQuestTimer("orfen_unlock", respawnTime, null, null);
			// also save the respawn time so that the info is maintained past reboots
			final StatsSet info = GrandBossManager.getInstance().getStatsSet(ORFEN);
			info.set("respawn_time", System.currentTimeMillis() + respawnTime);
			GrandBossManager.getInstance().setStatsSet(ORFEN, info);
			cancelQuestTimer("check_minion_loc", npc, null);
			cancelQuestTimer("check_orfen_pos", npc, null);
			startQuestTimer("despawn_minions", 20000, null, null);
			cancelQuestTimers("spawn_minion");
		}
		else if ((GrandBossManager.getInstance().getBossStatus(ORFEN) == ALIVE) && (npc.getId() == RAIKEL_LEOS))
		{
			MINIONS.remove(npc);
			startQuestTimer("spawn_minion", 360000, npc, null);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	public static void main(String[] args)
	{
		new Orfen();
	}
}

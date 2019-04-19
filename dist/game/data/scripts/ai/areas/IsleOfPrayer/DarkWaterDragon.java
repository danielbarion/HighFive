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
package ai.areas.IsleOfPrayer;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.NpcData;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Dark Water Dragon's AI.
 */
public final class DarkWaterDragon extends AbstractNpcAI
{
	private static final int DRAGON = 22267;
	private static final int SHADE1 = 22268;
	private static final int SHADE2 = 22269;
	private static final int FAFURION = 18482;
	private static final int DETRACTOR1 = 22270;
	private static final int DETRACTOR2 = 22271;
	private static final Set<Integer> SECOND_SPAWN = ConcurrentHashMap.newKeySet(); // Used to track if second Shades were already spawned
	private static Set<Integer> MY_TRACKING_SET = ConcurrentHashMap.newKeySet(); // Used to track instances of npcs
	private static Map<Integer, L2PcInstance> ID_MAP = new ConcurrentHashMap<>(); // Used to track instances of npcs
	
	private DarkWaterDragon()
	{
		final int[] mobs =
		{
			DRAGON,
			SHADE1,
			SHADE2,
			FAFURION,
			DETRACTOR1,
			DETRACTOR2
		};
		addKillId(mobs);
		addAttackId(mobs);
		addSpawnId(mobs);
		MY_TRACKING_SET.clear();
		SECOND_SPAWN.clear();
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (npc != null)
		{
			if (event.equalsIgnoreCase("first_spawn")) // timer to start timer "1"
			{
				startQuestTimer("1", 40000, npc, null, true); // spawns detractor every 40 seconds
			}
			else if (event.equalsIgnoreCase("second_spawn")) // timer to start timer "2"
			{
				startQuestTimer("2", 40000, npc, null, true); // spawns detractor every 40 seconds
			}
			else if (event.equalsIgnoreCase("third_spawn")) // timer to start timer "3"
			{
				startQuestTimer("3", 40000, npc, null, true); // spawns detractor every 40 seconds
			}
			else if (event.equalsIgnoreCase("fourth_spawn")) // timer to start timer "4"
			{
				startQuestTimer("4", 40000, npc, null, true); // spawns detractor every 40 seconds
			}
			else if (event.equalsIgnoreCase("1")) // spawns a detractor
			{
				addSpawn(DETRACTOR1, npc.getX() + 100, npc.getY() + 100, npc.getZ(), 0, false, 40000);
			}
			else if (event.equalsIgnoreCase("2")) // spawns a detractor
			{
				addSpawn(DETRACTOR2, npc.getX() + 100, npc.getY() - 100, npc.getZ(), 0, false, 40000);
			}
			else if (event.equalsIgnoreCase("3")) // spawns a detractor
			{
				addSpawn(DETRACTOR1, npc.getX() - 100, npc.getY() + 100, npc.getZ(), 0, false, 40000);
			}
			else if (event.equalsIgnoreCase("4")) // spawns a detractor
			{
				addSpawn(DETRACTOR2, npc.getX() - 100, npc.getY() - 100, npc.getZ(), 0, false, 40000);
			}
			else if (event.equalsIgnoreCase("fafurion_despawn")) // Fafurion Kindred disappears and drops reward
			{
				cancelQuestTimer("fafurion_poison", npc, null);
				cancelQuestTimer("1", npc, null);
				cancelQuestTimer("2", npc, null);
				cancelQuestTimer("3", npc, null);
				cancelQuestTimer("4", npc, null);
				
				MY_TRACKING_SET.remove(npc.getObjectId());
				player = ID_MAP.remove(npc.getObjectId());
				if (player != null)
				{
					((L2Attackable) npc).doItemDrop(NpcData.getInstance().getTemplate(18485), player);
				}
				
				npc.deleteMe();
			}
			else if (event.equalsIgnoreCase("fafurion_poison")) // Reduces Fafurions hp like it is poisoned
			{
				if (npc.getCurrentHp() <= 500)
				{
					cancelQuestTimer("fafurion_despawn", npc, null);
					cancelQuestTimer("first_spawn", npc, null);
					cancelQuestTimer("second_spawn", npc, null);
					cancelQuestTimer("third_spawn", npc, null);
					cancelQuestTimer("fourth_spawn", npc, null);
					cancelQuestTimer("1", npc, null);
					cancelQuestTimer("2", npc, null);
					cancelQuestTimer("3", npc, null);
					cancelQuestTimer("4", npc, null);
					MY_TRACKING_SET.remove(npc.getObjectId());
					ID_MAP.remove(npc.getObjectId());
				}
				npc.reduceCurrentHp(500, npc, null); // poison kills Fafurion if he is not healed
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		final int npcId = npc.getId();
		final int npcObjId = npc.getObjectId();
		if (npcId == DRAGON)
		{
			if (!MY_TRACKING_SET.contains(npcObjId)) // this allows to handle multiple instances of npc
			{
				MY_TRACKING_SET.add(npcObjId);
				// Spawn first 5 shades on first attack on Dark Water Dragon
				final L2Character originalAttacker = isSummon ? attacker.getSummon() : attacker;
				spawnShade(originalAttacker, SHADE1, npc.getX() + 100, npc.getY() + 100, npc.getZ());
				spawnShade(originalAttacker, SHADE2, npc.getX() + 100, npc.getY() - 100, npc.getZ());
				spawnShade(originalAttacker, SHADE1, npc.getX() - 100, npc.getY() + 100, npc.getZ());
				spawnShade(originalAttacker, SHADE2, npc.getX() - 100, npc.getY() - 100, npc.getZ());
				spawnShade(originalAttacker, SHADE1, npc.getX() - 150, npc.getY() + 150, npc.getZ());
			}
			else if ((npc.getCurrentHp() < (npc.getMaxHp() / 2.0)) && !(SECOND_SPAWN.contains(npcObjId)))
			{
				SECOND_SPAWN.add(npcObjId);
				// Spawn second 5 shades on half hp of on Dark Water Dragon
				final L2Character originalAttacker = isSummon ? attacker.getSummon() : attacker;
				spawnShade(originalAttacker, SHADE2, npc.getX() + 100, npc.getY() + 100, npc.getZ());
				spawnShade(originalAttacker, SHADE1, npc.getX() + 100, npc.getY() - 100, npc.getZ());
				spawnShade(originalAttacker, SHADE2, npc.getX() - 100, npc.getY() + 100, npc.getZ());
				spawnShade(originalAttacker, SHADE1, npc.getX() - 100, npc.getY() - 100, npc.getZ());
				spawnShade(originalAttacker, SHADE2, npc.getX() - 150, npc.getY() + 150, npc.getZ());
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final int npcId = npc.getId();
		final int npcObjId = npc.getObjectId();
		if (npcId == DRAGON)
		{
			MY_TRACKING_SET.remove(npcObjId);
			SECOND_SPAWN.remove(npcObjId);
			final L2Attackable faf = (L2Attackable) addSpawn(FAFURION, npc.getX(), npc.getY(), npc.getZ(), 0, false, 0); // spawns Fafurion Kindred when Dard Water Dragon is dead
			ID_MAP.put(faf.getObjectId(), killer);
		}
		else if (npcId == FAFURION)
		{
			cancelQuestTimer("fafurion_poison", npc, null);
			cancelQuestTimer("fafurion_despawn", npc, null);
			cancelQuestTimer("first_spawn", npc, null);
			cancelQuestTimer("second_spawn", npc, null);
			cancelQuestTimer("third_spawn", npc, null);
			cancelQuestTimer("fourth_spawn", npc, null);
			cancelQuestTimer("1", npc, null);
			cancelQuestTimer("2", npc, null);
			cancelQuestTimer("3", npc, null);
			cancelQuestTimer("4", npc, null);
			MY_TRACKING_SET.remove(npcObjId);
			ID_MAP.remove(npcObjId);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		final int npcId = npc.getId();
		final int npcObjId = npc.getObjectId();
		if ((npcId == FAFURION) && !MY_TRACKING_SET.contains(npcObjId))
		{
			MY_TRACKING_SET.add(npcObjId);
			// Spawn 4 Detractors on spawn of Fafurion
			final int x = npc.getX();
			final int y = npc.getY();
			addSpawn(DETRACTOR2, x + 100, y + 100, npc.getZ(), 0, false, 40000);
			addSpawn(DETRACTOR1, x + 100, y - 100, npc.getZ(), 0, false, 40000);
			addSpawn(DETRACTOR2, x - 100, y + 100, npc.getZ(), 0, false, 40000);
			addSpawn(DETRACTOR1, x - 100, y - 100, npc.getZ(), 0, false, 40000);
			startQuestTimer("first_spawn", 2000, npc, null);
			startQuestTimer("second_spawn", 4000, npc, null);
			startQuestTimer("third_spawn", 8000, npc, null);
			startQuestTimer("fourth_spawn", 10000, npc, null);
			startQuestTimer("fafurion_poison", 3000, npc, null, true);
			startQuestTimer("fafurion_despawn", 120000, npc, null);
		}
		return super.onSpawn(npc);
	}
	
	public void spawnShade(L2Character attacker, int npcId, int x, int y, int z)
	{
		final L2Npc shade = addSpawn(npcId, x, y, z, 0, false, 0);
		shade.setRunning();
		((L2Attackable) shade).addDamageHate(attacker, 0, 999);
		shade.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);
	}
	
	public static void main(String[] args)
	{
		new DarkWaterDragon();
	}
}

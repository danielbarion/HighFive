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
package ai.areas.ForgeOfTheGods;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Forge of the Gods AI
 * @author nonom, malyelfik
 */
public final class ForgeOfTheGods extends AbstractNpcAI
{
	// NPCs
	private static final int[] FOG_MOBS =
	{
		22634, // Scarlet Stakato Worker
		22635, // Scarlet Stakato Soldier
		22636, // Scarlet Stakato Noble
		22637, // Tepra Scorpion
		22638, // Tepra Scarab
		22639, // Assassin Beetle
		22640, // Mercenary of Destruction
		22641, // Knight of Destruction
		22642, // Lavastone Golem
		22643, // Magma Golem
		22644, // Arimanes of Destruction
		22645, // Balor of Destruction
		22646, // Ashuras of Destruction
		22647, // Lavasillisk
		22648, // Blazing Ifrit
		22649, // Magma Drake
	};
	
	private static final int[] LAVASAURUSES =
	{
		18799, // Newborn Lavasaurus
		18800, // Fledgling Lavasaurus
		18801, // Adult Lavasaurus
		18802, // Elderly Lavasaurus
		18803, // Ancient Lavasaurus
	};
	
	private static final int REFRESH = 15;
	
	private static final int MOBCOUNT_BONUS_MIN = 3;
	
	private static final int BONUS_UPPER_LV01 = 5;
	private static final int BONUS_UPPER_LV02 = 10;
	private static final int BONUS_UPPER_LV03 = 15;
	private static final int BONUS_UPPER_LV04 = 20;
	private static final int BONUS_UPPER_LV05 = 35;
	
	private static final int BONUS_LOWER_LV01 = 5;
	private static final int BONUS_LOWER_LV02 = 10;
	private static final int BONUS_LOWER_LV03 = 15;
	
	private static final int FORGE_BONUS01 = 20;
	private static final int FORGE_BONUS02 = 40;
	
	private static int _npcCount = 0;
	
	// private static int _npcsAlive = 0; TODO: Require zone spawn support
	
	private ForgeOfTheGods()
	{
		addKillId(FOG_MOBS);
		addSpawnId(LAVASAURUSES);
		startQuestTimer("refresh", REFRESH * 1000, null, null, true);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "suicide":
			{
				if (npc != null)
				{
					npc.doDie(null);
				}
				break;
			}
			case "refresh":
			{
				_npcCount = 0;
				break;
			}
		}
		return null;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final int rand = getRandom(100);
		L2Npc mob = null;
		_npcCount++;
		
		// For monsters at Forge of the Gods - Lower level
		if (npc.getSpawn().getZ() < -5000) // && (_npcsAlive < 48))
		{
			if ((_npcCount > BONUS_LOWER_LV03) && (rand <= FORGE_BONUS02))
			{
				mob = addSpawn(LAVASAURUSES[4], npc, true);
			}
			else if (_npcCount > BONUS_LOWER_LV02)
			{
				mob = spawnLavasaurus(npc, rand, LAVASAURUSES[4], LAVASAURUSES[3]);
			}
			else if (_npcCount > BONUS_LOWER_LV01)
			{
				mob = spawnLavasaurus(npc, rand, LAVASAURUSES[3], LAVASAURUSES[2]);
			}
			else if (_npcCount >= MOBCOUNT_BONUS_MIN)
			{
				mob = spawnLavasaurus(npc, rand, LAVASAURUSES[2], LAVASAURUSES[1]);
			}
		}
		else if ((_npcCount > BONUS_UPPER_LV05) && (rand <= FORGE_BONUS02))
		{
			mob = addSpawn(LAVASAURUSES[1], npc, true);
		}
		else if (_npcCount > BONUS_UPPER_LV04)
		{
			mob = spawnLavasaurus(npc, rand, LAVASAURUSES[4], LAVASAURUSES[3]);
		}
		else if (_npcCount > BONUS_UPPER_LV03)
		{
			mob = spawnLavasaurus(npc, rand, LAVASAURUSES[3], LAVASAURUSES[2]);
		}
		else if (_npcCount > BONUS_UPPER_LV02)
		{
			mob = spawnLavasaurus(npc, rand, LAVASAURUSES[2], LAVASAURUSES[1]);
		}
		else if (_npcCount > BONUS_UPPER_LV01)
		{
			mob = spawnLavasaurus(npc, rand, LAVASAURUSES[1], LAVASAURUSES[0]);
		}
		else if ((_npcCount >= MOBCOUNT_BONUS_MIN) && (rand <= FORGE_BONUS01))
		{
			mob = addSpawn(LAVASAURUSES[0], npc, true);
		}
		if (mob != null)
		{
			((L2Attackable) mob).addDamageHate(killer, 0, 9999);
			mob.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public final String onSpawn(L2Npc npc)
	{
		startQuestTimer("suicide", 60000, npc, null);
		return super.onSpawn(npc);
	}
	
	private L2Npc spawnLavasaurus(L2Npc npc, int rand, int... mobs)
	{
		if (mobs.length < 2)
		{
			return null;
		}
		
		L2Npc mob = null;
		if (rand <= FORGE_BONUS01)
		{
			mob = addSpawn(mobs[0], npc, true);
		}
		else if (rand <= FORGE_BONUS02)
		{
			mob = addSpawn(mobs[1], npc, true);
		}
		return mob;
	}
	
	public static void main(String[] args)
	{
		new ForgeOfTheGods();
	}
}
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
package ai.areas.Hellbound.AI;

import java.util.List;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.taskmanager.DecayTaskManager;

import ai.AbstractNpcAI;
import ai.areas.Hellbound.HellboundEngine;

/**
 * Hellbound Slaves AI.
 * @author DS
 */
public final class Slaves extends AbstractNpcAI
{
	// NPCs
	private static final int[] MASTERS =
	{
		22320, // Junior Watchman
		22321, // Junior Summoner
	};
	// Locations
	private static final Location MOVE_TO = new Location(-25451, 252291, -3252, 3500);
	// Misc
	private static final int TRUST_REWARD = 10;
	
	public Slaves()
	{
		addSpawnId(MASTERS);
		addKillId(MASTERS);
	}
	
	@Override
	public final String onSpawn(L2Npc npc)
	{
		((L2MonsterInstance) npc).enableMinions(HellboundEngine.getInstance().getLevel() < 5);
		((L2MonsterInstance) npc).setOnKillDelay(1000);
		return super.onSpawn(npc);
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (((L2MonsterInstance) npc).getMinionList() != null)
		{
			final List<L2MonsterInstance> slaves = ((L2MonsterInstance) npc).getMinionList().getSpawnedMinions();
			if ((slaves != null) && !slaves.isEmpty())
			{
				for (L2MonsterInstance slave : slaves)
				{
					if ((slave == null) || slave.isDead())
					{
						continue;
					}
					slave.clearAggroList();
					slave.abortAttack();
					slave.abortCast();
					slave.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THANK_YOU_FOR_SAVING_ME_FROM_THE_CLUTCHES_OF_EVIL);
					
					if ((HellboundEngine.getInstance().getLevel() >= 1) && (HellboundEngine.getInstance().getLevel() <= 2))
					{
						HellboundEngine.getInstance().updateTrust(TRUST_REWARD, false);
					}
					slave.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, MOVE_TO);
					DecayTaskManager.getInstance().add(slave);
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
}
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

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.NpcStringId;

import ai.AbstractNpcAI;

/**
 * Rooney AI
 * @author malyelfik
 */
public final class Rooney extends AbstractNpcAI
{
	// NPC
	private static final int ROONEY = 32049;
	// Locations
	private static final Location[] LOCATIONS =
	{
		new Location(175937, -112167, -5550),
		new Location(178896, -112425, -5860),
		new Location(180628, -115992, -6135),
		new Location(183010, -114753, -6135),
		new Location(184496, -116773, -6135),
		new Location(181857, -109491, -5865),
		new Location(178917, -107633, -5853),
		new Location(178804, -110080, -5853),
		new Location(182221, -106806, -6025),
		new Location(186488, -109715, -5915),
		new Location(183847, -119231, -3113),
		new Location(185193, -120342, -3113),
		new Location(188047, -120867, -3113),
		new Location(189734, -120471, -3113),
		new Location(188754, -118940, -3313),
		new Location(190022, -116803, -3313),
		new Location(188443, -115814, -3313),
		new Location(186421, -114614, -3313),
		new Location(185188, -113307, -3313),
		new Location(187378, -112946, -3313),
		new Location(189815, -113425, -3313),
		new Location(189301, -111327, -3313),
		new Location(190289, -109176, -3313),
		new Location(187783, -110478, -3313),
		new Location(185889, -109990, -3313),
		new Location(181881, -109060, -3695),
		new Location(183570, -111344, -3675),
		new Location(182077, -112567, -3695),
		new Location(180127, -112776, -3698),
		new Location(179155, -108629, -3695),
		new Location(176282, -109510, -3698),
		new Location(176071, -113163, -3515),
		new Location(179376, -117056, -3640),
		new Location(179760, -115385, -3640),
		new Location(177950, -119691, -4140),
		new Location(177037, -120820, -4340),
		new Location(181125, -120148, -3702),
		new Location(182212, -117969, -3352),
		new Location(186074, -118154, -3312)
	};
	
	private Rooney()
	{
		addSeeCreatureId(ROONEY);
		addSpawn(ROONEY, LOCATIONS[getRandom(LOCATIONS.length)], false, 0);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (event.equals("teleport") && !npc.isDecayed())
		{
			final int aiVal = npc.getScriptValue();
			switch (aiVal)
			{
				case 1:
				{
					npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.HURRY_HURRY);
					break;
				}
				case 2:
				{
					npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.I_AM_NOT_THAT_TYPE_OF_PERSON_WHO_STAYS_IN_ONE_PLACE_FOR_A_LONG_TIME);
					break;
				}
				case 3:
				{
					npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.IT_S_HARD_FOR_ME_TO_KEEP_STANDING_LIKE_THIS);
					break;
				}
				case 4:
				{
					npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.WHY_DON_T_I_GO_THAT_WAY_THIS_TIME);
					break;
				}
				default:
				{
					npc.teleToLocation(LOCATIONS[getRandom(LOCATIONS.length)], false);
					npc.setScriptValue(0);
					return null;
				}
			}
			npc.setScriptValue(aiVal + 1);
			startQuestTimer("teleport", 60000, npc, null);
		}
		return null;
	}
	
	@Override
	public String onSeeCreature(L2Npc npc, L2Character creature, boolean isSummon)
	{
		if (creature.isPlayer() && npc.isScriptValue(0))
		{
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.WELCOME);
			startQuestTimer("teleport", 60000, npc, null);
			npc.setScriptValue(1);
		}
		return super.onSeeCreature(npc, creature, isSummon);
	}
	
	public static void main(String[] args)
	{
		new Rooney();
	}
}
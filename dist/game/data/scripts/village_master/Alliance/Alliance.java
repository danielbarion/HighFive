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
package village_master.Alliance;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * @author UnAfraid
 */
public final class Alliance extends AbstractNpcAI
{
	// @formatter:off
	private static final int[] NPCS =
	{
		30026,30031,30037,30066,30070,30109,30115,30120,30154,30174,
		30175,30176,30187,30191,30195,30288,30289,30290,30297,30358,
		30373,30462,30474,30498,30499,30500,30503,30504,30505,30508,
		30511,30512,30513,30520,30525,30565,30594,30595,30676,30677,
		30681,30685,30687,30689,30694,30699,30704,30845,30847,30849,
		30854,30857,30862,30865,30894,30897,30900,30905,30910,30913,
		31269,31272,31276,31279,31285,31288,31314,31317,31321,31324,
		31326,31328,31331,31334,31336,31755,31958,31961,31965,31968,
		31974,31977,31996,32092,32093,32094,32095,32096,32097,32098,
		32145,32146,32147,32150,32153,32154,32157,32158,32160,32171,
		32193,32196,32199,32202,32205,32206,32209,32210,32213,32214,
		32217,32218,32221,32222,32225,32226,32229,32230,32233,32234
	};
	// @formatter:on
	
	private Alliance()
	{
		addStartNpc(NPCS);
		addTalkId(NPCS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (!"9001-01.htm".equals(event) && (player.getClan() == null))
		{
			return "9001-04.htm";
		}
		return event;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		return "9001-01.htm";
	}
	
	public static void main(String[] args)
	{
		new Alliance();
	}
}

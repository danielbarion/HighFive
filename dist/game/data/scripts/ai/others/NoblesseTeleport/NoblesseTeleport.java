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
package ai.others.NoblesseTeleport;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Noblesse teleport AI.<br>
 * Original Jython script by Ham Wong.
 * @author Plim
 */
public final class NoblesseTeleport extends AbstractNpcAI
{
	// Item
	private static final int OLYMPIAD_TOKEN = 13722;
	// NPCs
	private static final int[] NPCs =
	{
		30006,
		30059,
		30080,
		30134,
		30146,
		30177,
		30233,
		30256,
		30320,
		30540,
		30576,
		30836,
		30848,
		30878,
		30899,
		31275,
		31320,
		31964,
		32163
	};
	
	private NoblesseTeleport()
	{
		addStartNpc(NPCs);
		addTalkId(NPCs);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ("teleportWithToken".equals(event))
		{
			if (hasQuestItems(player, OLYMPIAD_TOKEN))
			{
				npc.showChatWindow(player, 3);
			}
			else
			{
				return "noble-nopass.htm";
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		return player.isNoble() ? "nobleteleporter.htm" : "nobleteleporter-no.htm";
	}
	
	public static void main(String[] args)
	{
		new NoblesseTeleport();
	}
}

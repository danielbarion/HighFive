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
package ai.areas.Gracia.instances.SecretArea;

import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;

import instances.AbstractInstance;

/**
 * Secret Area in the Keucereus Fortress instance zone.
 * @author Gladicek
 */
public final class SecretArea extends AbstractInstance
{
	// NPCs
	private static final int GINBY = 32566;
	private static final int LELRIKIA = 32567;
	// Locations
	private static final Location[] TELEPORTS =
	{
		new Location(-23758, -8959, -5384),
		new Location(-185057, 242821, 1576)
	};
	// Misc
	private static final int TEMPLATE_ID = 117;
	private static final int ENTER = 0;
	private static final int EXIT = 1;
	
	public SecretArea()
	{
		addStartNpc(GINBY);
		addTalkId(GINBY);
		addTalkId(LELRIKIA);
	}
	
	@Override
	public void onEnterInstance(L2PcInstance player, InstanceWorld world, boolean firstEntrance)
	{
		if (firstEntrance)
		{
			world.addAllowed(player);
		}
		teleportPlayer(player, TELEPORTS[ENTER], world.getInstanceId());
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final String htmltext = getNoQuestMsg(player);
		if ((npc.getId() == GINBY) && event.equalsIgnoreCase("enter"))
		{
			enterInstance(player, TEMPLATE_ID);
			return "32566-01.html";
		}
		else if ((npc.getId() == LELRIKIA) && event.equalsIgnoreCase("exit"))
		{
			teleportPlayer(player, TELEPORTS[EXIT], 0);
			return "32567-01.html";
		}
		return htmltext;
	}
}

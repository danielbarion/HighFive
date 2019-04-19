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
package handlers.admincommandhandlers;

import java.util.Calendar;
import java.util.StringTokenizer;

import com.l2jmobius.gameserver.handler.IAdminCommandHandler;
import com.l2jmobius.gameserver.instancemanager.SoDManager;
import com.l2jmobius.gameserver.instancemanager.SoIManager;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;

public class AdminGraciaSeeds implements IAdminCommandHandler
{
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_gracia_seeds",
		"admin_kill_tiat",
		"admin_set_sodstate",
		"admin_set_soistage"
	};
	
	@Override
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		final StringTokenizer st = new StringTokenizer(command, " ");
		final String actualCommand = st.nextToken(); // Get actual command
		
		String val = "";
		if (st.countTokens() >= 1)
		{
			val = st.nextToken();
		}
		
		if (actualCommand.equalsIgnoreCase("admin_kill_tiat"))
		{
			SoDManager.getInstance().increaseSoDTiatKilled();
		}
		else if (actualCommand.equalsIgnoreCase("admin_set_sodstate"))
		{
			SoDManager.getInstance().setSoDState(Integer.parseInt(val), true);
		}
		else if (actualCommand.equalsIgnoreCase("admin_set_soistage"))
		{
			SoIManager.setCurrentStage(Integer.parseInt(val));
		}
		
		showMenu(activeChar);
		return true;
	}
	
	private void showMenu(L2PcInstance activeChar)
	{
		final NpcHtmlMessage html = new NpcHtmlMessage();
		html.setFile(activeChar, "data/html/admin/graciaseeds.htm");
		// Seed of destruction
		html.replace("%sodstage%", String.valueOf(SoDManager.getInstance().getSoDState()));
		html.replace("%sodtiatkill%", String.valueOf(SoDManager.getInstance().getSoDTiatKilled()));
		if (SoDManager.getInstance().getSoDTimeForNextStateChange() > 0)
		{
			final Calendar nextChangeDate = Calendar.getInstance();
			nextChangeDate.setTimeInMillis(System.currentTimeMillis() + SoDManager.getInstance().getSoDTimeForNextStateChange());
			html.replace("%sodtime%", nextChangeDate.getTime().toString());
		}
		else
		{
			html.replace("%sodtime%", "-1");
		}
		
		// Seed of infinity
		html.replace("%soistage%", SoIManager.getCurrentStage());
		html.replace("%twinkills%", "N/A");
		html.replace("%cohemeneskills%", SoIManager.getCohemenesCount());
		html.replace("%ekimuskills%", SoIManager.getEkimusCount());
		html.replace("%soitime%", "N/A");
		activeChar.sendPacket(html);
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
}

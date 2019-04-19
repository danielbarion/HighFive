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
import java.util.List;
import java.util.StringTokenizer;

import com.l2jmobius.gameserver.handler.IAdminCommandHandler;
import com.l2jmobius.gameserver.instancemanager.GlobalVariablesManager;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager;
import com.l2jmobius.gameserver.model.TerritoryWard;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jmobius.gameserver.util.BuilderUtil;

/**
 * Admin comand handler for Territory War System This class handles following admin commands:
 * @author Gigiikun
 */
public class AdminTerritoryWar implements IAdminCommandHandler
{
	private static final String[] _adminCommands =
	{
		"admin_territory_war",
		"admin_territory_war_time",
		"admin_territory_war_start",
		"admin_territory_war_end",
		"admin_territory_wards_list"
	};
	
	@Override
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		final StringTokenizer st = new StringTokenizer(command);
		command = st.nextToken();
		
		if (command.equals("admin_territory_war"))
		{
			showMainPage(activeChar);
		}
		else if (command.equalsIgnoreCase("admin_territory_war_time"))
		{
			if (st.hasMoreTokens())
			{
				final Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(TerritoryWarManager.getInstance().getTWStartTimeInMillis());
				
				final String val = st.nextToken();
				if ("month".equals(val))
				{
					final int month = cal.get(Calendar.MONTH) + Integer.parseInt(st.nextToken());
					if ((cal.getActualMinimum(Calendar.MONTH) > month) || (cal.getActualMaximum(Calendar.MONTH) < month))
					{
						BuilderUtil.sendSysMessage(activeChar, "Unable to change Siege Date - Incorrect month value only " + cal.getActualMinimum(Calendar.MONTH) + "-" + cal.getActualMaximum(Calendar.MONTH) + " is accepted!");
						return false;
					}
					cal.set(Calendar.MONTH, month);
				}
				else if ("day".equals(val))
				{
					final int day = Integer.parseInt(st.nextToken());
					if ((cal.getActualMinimum(Calendar.DAY_OF_MONTH) > day) || (cal.getActualMaximum(Calendar.DAY_OF_MONTH) < day))
					{
						BuilderUtil.sendSysMessage(activeChar, "Unable to change Siege Date - Incorrect day value only " + cal.getActualMinimum(Calendar.DAY_OF_MONTH) + "-" + cal.getActualMaximum(Calendar.DAY_OF_MONTH) + " is accepted!");
						return false;
					}
					cal.set(Calendar.DAY_OF_MONTH, day);
				}
				else if ("hour".equals(val))
				{
					final int hour = Integer.parseInt(st.nextToken());
					if ((cal.getActualMinimum(Calendar.HOUR_OF_DAY) > hour) || (cal.getActualMaximum(Calendar.HOUR_OF_DAY) < hour))
					{
						BuilderUtil.sendSysMessage(activeChar, "Unable to change Siege Date - Incorrect hour value only " + cal.getActualMinimum(Calendar.HOUR_OF_DAY) + "-" + cal.getActualMaximum(Calendar.HOUR_OF_DAY) + " is accepted!");
						return false;
					}
					cal.set(Calendar.HOUR_OF_DAY, hour);
				}
				else if ("min".equals(val))
				{
					final int min = Integer.parseInt(st.nextToken());
					if ((cal.getActualMinimum(Calendar.MINUTE) > min) || (cal.getActualMaximum(Calendar.MINUTE) < min))
					{
						BuilderUtil.sendSysMessage(activeChar, "Unable to change Siege Date - Incorrect minute value only " + cal.getActualMinimum(Calendar.MINUTE) + "-" + cal.getActualMaximum(Calendar.MINUTE) + " is accepted!");
						return false;
					}
					cal.set(Calendar.MINUTE, min);
				}
				
				if (cal.getTimeInMillis() < Calendar.getInstance().getTimeInMillis())
				{
					BuilderUtil.sendSysMessage(activeChar, "Unable to change TW Date!");
				}
				else if (cal.getTimeInMillis() != TerritoryWarManager.getInstance().getTWStartTimeInMillis())
				{
					TerritoryWarManager.getInstance().setTWStartTimeInMillis(cal.getTimeInMillis());
					GlobalVariablesManager.getInstance().set(TerritoryWarManager.GLOBAL_VARIABLE, cal.getTimeInMillis());
				}
			}
			showSiegeTimePage(activeChar);
		}
		else if (command.equalsIgnoreCase("admin_territory_war_start"))
		{
			TerritoryWarManager.getInstance().setTWStartTimeInMillis(System.currentTimeMillis());
		}
		else if (command.equalsIgnoreCase("admin_territory_war_end"))
		{
			TerritoryWarManager.getInstance().setTWStartTimeInMillis(System.currentTimeMillis() - TerritoryWarManager.WARLENGTH);
		}
		else if (command.equalsIgnoreCase("admin_territory_wards_list"))
		{
			// build beginning of html page
			final NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(0, 1);
			final StringBuilder sb = new StringBuilder();
			sb.append("<html><title>Territory War</title><body><br><center><font color=\"LEVEL\">Active Wards List:</font></center>");
			
			// get,build & send current Wards list
			if (TerritoryWarManager.getInstance().isTWInProgress())
			{
				final List<TerritoryWard> territoryWardList = TerritoryWarManager.getInstance().getAllTerritoryWards();
				for (TerritoryWard ward : territoryWardList)
				{
					if (ward.getNpc() != null)
					{
						sb.append("<table width=270><tr>");
						sb.append("<td width=135 ALIGN=\"LEFT\">" + ward.getNpc().getName() + "</td>");
						sb.append("<td width=135 ALIGN=\"RIGHT\"><button value=\"TeleTo\" action=\"bypass -h admin_move_to " + ward.getNpc().getX() + " " + ward.getNpc().getY() + " " + ward.getNpc().getZ() + "\" width=50 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_ct1.button_df\"></td>");
						sb.append("</tr></table>");
					}
					else if (ward.getPlayer() != null)
					{
						sb.append("<table width=270><tr>");
						sb.append("<td width=135 ALIGN=\"LEFT\">" + ward.getPlayer().getActiveWeaponInstance().getItemName() + " - " + ward.getPlayer().getName() + "</td>");
						sb.append("<td width=135 ALIGN=\"RIGHT\"><button value=\"TeleTo\" action=\"bypass -h admin_move_to " + ward.getPlayer().getX() + " " + ward.getPlayer().getY() + " " + ward.getPlayer().getZ() + "\" width=50 height=20 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_ct1.button_df\"></td>");
						sb.append("</tr></table>");
					}
				}
				sb.append("<br><center><button value=\"Back\" action=\"bypass -h admin_territory_war\" width=50 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></center></body></html>");
				npcHtmlMessage.setHtml(sb.toString());
				activeChar.sendPacket(npcHtmlMessage);
			}
			else
			{
				sb.append("<br><br><center>The Ward List is empty!<br>TW has probably NOT started!");
				sb.append("<br><button value=\"Back\" action=\"bypass -h admin_territory_war\" width=50 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></center></body></html>");
				npcHtmlMessage.setHtml(sb.toString());
				activeChar.sendPacket(npcHtmlMessage);
			}
		}
		return true;
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return _adminCommands;
	}
	
	private void showSiegeTimePage(L2PcInstance activeChar)
	{
		final NpcHtmlMessage adminReply = new NpcHtmlMessage();
		adminReply.setFile(activeChar, "data/html/admin/territorywartime.htm");
		adminReply.replace("%time%", TerritoryWarManager.getInstance().getTWStart().getTime().toString());
		activeChar.sendPacket(adminReply);
	}
	
	private void showMainPage(L2PcInstance activeChar)
	{
		AdminHtml.showAdminHtml(activeChar, "territorywar.htm");
	}
}

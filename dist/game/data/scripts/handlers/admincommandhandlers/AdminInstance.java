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

import java.util.StringTokenizer;

import com.l2jmobius.gameserver.handler.IAdminCommandHandler;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.util.BuilderUtil;
import com.l2jmobius.gameserver.util.Util;

/**
 * @author evill33t, GodKratos
 */
public class AdminInstance implements IAdminCommandHandler
{
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_setinstance",
		"admin_ghoston",
		"admin_ghostoff",
		"admin_createinstance",
		"admin_destroyinstance",
		"admin_listinstances"
	};
	
	@Override
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		final StringTokenizer st = new StringTokenizer(command);
		st.nextToken();
		
		// create new instance
		if (command.startsWith("admin_createinstance"))
		{
			final String[] parts = command.split(" ");
			if ((parts.length != 3) || !Util.isDigit(parts[2]))
			{
				BuilderUtil.sendSysMessage(activeChar, "Example: //createinstance <id> <templateId> - ids => 300000 are reserved for dynamic instances");
			}
			else
			{
				try
				{
					final int id = Integer.parseInt(parts[1]);
					if ((id < 300000) && InstanceManager.getInstance().createInstanceFromTemplate(id, Integer.parseInt(parts[2])))
					{
						BuilderUtil.sendSysMessage(activeChar, "Instance created.");
					}
					else
					{
						BuilderUtil.sendSysMessage(activeChar, "Failed to create instance.");
					}
					return true;
				}
				catch (Exception e)
				{
					BuilderUtil.sendSysMessage(activeChar, "Failed loading: " + parts[1] + " " + parts[2]);
					return false;
				}
			}
		}
		else if (command.startsWith("admin_listinstances"))
		{
			int counter = 0;
			for (Instance instance : InstanceManager.getInstance().getInstances().values())
			{
				final InstanceWorld world = InstanceManager.getInstance().getWorld(instance.getId());
				if (world != null)
				{
					counter++;
					BuilderUtil.sendSysMessage(activeChar, "Id: " + instance.getId() + " Name: " + InstanceManager.getInstance().getInstanceIdName(world.getTemplateId()));
				}
			}
			if (counter == 0)
			{
				BuilderUtil.sendSysMessage(activeChar, "No active instances.");
			}
		}
		else if (command.startsWith("admin_setinstance"))
		{
			try
			{
				final int val = Integer.parseInt(st.nextToken());
				if (InstanceManager.getInstance().getInstance(val) == null)
				{
					BuilderUtil.sendSysMessage(activeChar, "Instance " + val + " doesnt exist.");
					return false;
				}
				
				final L2Object target = activeChar.getTarget();
				if ((target == null) || target.isSummon()) // Don't separate summons from masters
				{
					BuilderUtil.sendSysMessage(activeChar, "Incorrect target.");
					return false;
				}
				target.setInstanceId(val);
				if (target.isPlayer())
				{
					final L2PcInstance player = (L2PcInstance) target;
					player.sendMessage("Admin set your instance to:" + val);
					player.teleToLocation(player.getLocation());
				}
				BuilderUtil.sendSysMessage(activeChar, "Moved " + target.getName() + " to instance " + target.getInstanceId() + ".");
				return true;
			}
			catch (Exception e)
			{
				BuilderUtil.sendSysMessage(activeChar, "Use //setinstance id");
			}
		}
		else if (command.startsWith("admin_destroyinstance"))
		{
			try
			{
				final int val = Integer.parseInt(st.nextToken());
				InstanceManager.getInstance().destroyInstance(val);
				BuilderUtil.sendSysMessage(activeChar, "Instance destroyed");
			}
			catch (Exception e)
			{
				BuilderUtil.sendSysMessage(activeChar, "Use //destroyinstance id");
			}
		}
		return true;
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
}
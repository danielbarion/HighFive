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

import com.l2jmobius.gameserver.handler.IAdminCommandHandler;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.PcInventory;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.network.serverpackets.InventoryUpdate;

/**
 * @author Mobius
 */
public class AdminDestroyItems implements IAdminCommandHandler
{
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_destroy_items",
		"admin_destroy_all_items",
		"admin_destroyitems",
		"admin_destroyallitems"
	};
	
	@Override
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		final PcInventory inventory = activeChar.getInventory();
		final InventoryUpdate iu = new InventoryUpdate();
		for (L2ItemInstance item : inventory.getItems())
		{
			if (item.isEquipped() && !command.contains("all"))
			{
				continue;
			}
			iu.addRemovedItem(item);
			inventory.destroyItem("Admin Destroy", item, activeChar, null);
		}
		activeChar.sendPacket(iu);
		return true;
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
}

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
package com.l2jmobius.gameserver.model.itemcontainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.gameserver.enums.ItemLocation;
import com.l2jmobius.gameserver.idfactory.IdFactory;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;

/**
 * @author DS
 */
public class Mail extends ItemContainer
{
	private final int _ownerId;
	private int _messageId;
	
	public Mail(int objectId, int messageId)
	{
		_ownerId = objectId;
		_messageId = messageId;
	}
	
	@Override
	public String getName()
	{
		return "Mail";
	}
	
	@Override
	public L2PcInstance getOwner()
	{
		return null;
	}
	
	@Override
	public ItemLocation getBaseLocation()
	{
		return ItemLocation.MAIL;
	}
	
	public int getMessageId()
	{
		return _messageId;
	}
	
	public void setNewMessageId(int messageId)
	{
		_messageId = messageId;
		for (L2ItemInstance item : _items)
		{
			if (item == null)
			{
				continue;
			}
			
			item.setItemLocation(getBaseLocation(), messageId);
		}
		
		updateDatabase();
	}
	
	public void returnToWh(ItemContainer wh)
	{
		for (L2ItemInstance item : _items)
		{
			if (item == null)
			{
				continue;
			}
			if (wh != null)
			{
				transferItem("Expire", item.getObjectId(), item.getCount(), wh, null, null);
			}
			else
			{
				item.setItemLocation(ItemLocation.WAREHOUSE);
			}
		}
	}
	
	@Override
	protected void addItem(L2ItemInstance item)
	{
		super.addItem(item);
		item.setItemLocation(getBaseLocation(), _messageId);
	}
	
	@Override
	public void updateDatabase()
	{
		_items.forEach(i -> i.updateDatabase(true));
	}
	
	@Override
	public void restore()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT object_id, item_id, count, enchant_level, loc, loc_data, custom_type1, custom_type2, mana_left, time FROM items WHERE owner_id=? AND loc=? AND loc_data=?"))
		{
			ps.setInt(1, _ownerId);
			ps.setString(2, getBaseLocation().name());
			ps.setInt(3, _messageId);
			try (ResultSet inv = ps.executeQuery())
			{
				L2ItemInstance item;
				while (inv.next())
				{
					item = L2ItemInstance.restoreFromDb(_ownerId, inv);
					if (item == null)
					{
						continue;
					}
					
					L2World.getInstance().addObject(item);
					
					// If stackable item is found just add to current quantity
					if (item.isStackable() && (getItemByItemId(item.getId()) != null))
					{
						addItem("Restore", item, null, null);
					}
					else
					{
						addItem(item);
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "could not restore container:", e);
		}
	}
	
	@Override
	public int getOwnerId()
	{
		return _ownerId;
	}
	
	@Override
	public void deleteMe()
	{
		_items.forEach(i ->
		{
			i.updateDatabase(true);
			i.deleteMe();
			L2World.getInstance().removeObject(i);
			IdFactory.getInstance().releaseId(i.getObjectId());
		});
		_items.clear();
	}
}
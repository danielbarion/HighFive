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
package com.l2jmobius.gameserver.data.xml.impl;

import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jmobius.Config;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.commons.util.IGameXmlReader;
import com.l2jmobius.commons.util.file.filter.NumericNameFilter;
import com.l2jmobius.gameserver.datatables.ItemTable;
import com.l2jmobius.gameserver.model.buylist.L2BuyList;
import com.l2jmobius.gameserver.model.buylist.Product;
import com.l2jmobius.gameserver.model.items.L2Item;

/**
 * Loads buy lists for NPCs.
 * @author NosBit
 */
public final class BuyListData implements IGameXmlReader
{
	private static final Logger LOGGER = Logger.getLogger(BuyListData.class.getName());
	
	private final Map<Integer, L2BuyList> _buyLists = new HashMap<>();
	private static final FileFilter NUMERIC_FILTER = new NumericNameFilter();
	
	protected BuyListData()
	{
		load();
	}
	
	@Override
	public synchronized void load()
	{
		_buyLists.clear();
		parseDatapackDirectory("data/buylists", false);
		if (Config.CUSTOM_BUYLIST_LOAD)
		{
			parseDatapackDirectory("data/buylists/custom", false);
		}
		
		LOGGER.info(getClass().getSimpleName() + ": Loaded " + _buyLists.size() + " BuyLists.");
		
		try (Connection con = DatabaseFactory.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM `buylists`"))
		{
			while (rs.next())
			{
				final int buyListId = rs.getInt("buylist_id");
				final int itemId = rs.getInt("item_id");
				final long count = rs.getLong("count");
				final long nextRestockTime = rs.getLong("next_restock_time");
				final L2BuyList buyList = getBuyList(buyListId);
				if (buyList == null)
				{
					LOGGER.warning("BuyList found in database but not loaded from xml! BuyListId: " + buyListId);
					continue;
				}
				final Product product = buyList.getProductByItemId(itemId);
				if (product == null)
				{
					LOGGER.warning("ItemId found in database but not loaded from xml! BuyListId: " + buyListId + " ItemId: " + itemId);
					continue;
				}
				if (count < product.getMaxCount())
				{
					product.setCount(count);
					product.restartRestockTask(nextRestockTime);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Failed to load buyList data from database.", e);
		}
	}
	
	@Override
	public void parseDocument(Document doc, File f)
	{
		try
		{
			final int buyListId = Integer.parseInt(f.getName().replaceAll(".xml", ""));
			
			for (Node node = doc.getFirstChild(); node != null; node = node.getNextSibling())
			{
				if ("list".equalsIgnoreCase(node.getNodeName()))
				{
					final L2BuyList buyList = new L2BuyList(buyListId);
					for (Node list_node = node.getFirstChild(); list_node != null; list_node = list_node.getNextSibling())
					{
						if ("item".equalsIgnoreCase(list_node.getNodeName()))
						{
							int itemId = -1;
							long price = -1;
							long restockDelay = -1;
							long count = -1;
							final NamedNodeMap attrs = list_node.getAttributes();
							Node attr = attrs.getNamedItem("id");
							itemId = Integer.parseInt(attr.getNodeValue());
							attr = attrs.getNamedItem("price");
							if (attr != null)
							{
								price = Long.parseLong(attr.getNodeValue());
							}
							attr = attrs.getNamedItem("restock_delay");
							if (attr != null)
							{
								restockDelay = Long.parseLong(attr.getNodeValue());
							}
							attr = attrs.getNamedItem("count");
							if (attr != null)
							{
								count = Long.parseLong(attr.getNodeValue());
							}
							final L2Item item = ItemTable.getInstance().getTemplate(itemId);
							if (item != null)
							{
								if ((price > -1) && (item.getReferencePrice() > price) && (buyList.getNpcsAllowed() != null))
								{
									LOGGER.warning("Item price is too low. BuyList:" + buyList.getListId() + " ItemID:" + itemId + " File:" + f.getName());
									LOGGER.warning("Setting price to reference price " + item.getReferencePrice() + " instead of " + price + ".");
									buyList.addProduct(new Product(buyList.getListId(), item, item.getReferencePrice(), restockDelay, count));
								}
								else
								{
									buyList.addProduct(new Product(buyList.getListId(), item, price, restockDelay, count));
								}
							}
							else
							{
								LOGGER.warning("Item not found. BuyList:" + buyList.getListId() + " ItemID:" + itemId + " File:" + f.getName());
							}
						}
						else if ("npcs".equalsIgnoreCase(list_node.getNodeName()))
						{
							for (Node npcs_node = list_node.getFirstChild(); npcs_node != null; npcs_node = npcs_node.getNextSibling())
							{
								if ("npc".equalsIgnoreCase(npcs_node.getNodeName()))
								{
									buyList.addAllowedNpc(Integer.parseInt(npcs_node.getTextContent()));
								}
							}
						}
					}
					_buyLists.put(buyList.getListId(), buyList);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Failed to load buyList data from xml File:" + f.getName(), e);
		}
	}
	
	@Override
	public FileFilter getCurrentFileFilter()
	{
		return NUMERIC_FILTER;
	}
	
	public L2BuyList getBuyList(int listId)
	{
		return _buyLists.get(listId);
	}
	
	public static BuyListData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final BuyListData _instance = new BuyListData();
	}
}

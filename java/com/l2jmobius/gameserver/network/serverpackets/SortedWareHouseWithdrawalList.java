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
package com.l2jmobius.gameserver.network.serverpackets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.data.xml.impl.RecipeData;
import com.l2jmobius.gameserver.model.L2RecipeList;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.L2Item;
import com.l2jmobius.gameserver.model.items.L2WarehouseItem;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.items.type.CrystalType;
import com.l2jmobius.gameserver.model.items.type.EtcItemType;
import com.l2jmobius.gameserver.model.items.type.MaterialType;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class SortedWareHouseWithdrawalList implements IClientOutgoingPacket
{
	public static final int PRIVATE = 1;
	public static final int CLAN = 2;
	public static final int CASTLE = 3; // not sure
	public static final int FREIGHT = 4; // not sure
	
	private long _playerAdena;
	private List<L2WarehouseItem> _objects = new ArrayList<>();
	private int _whType;
	
	public enum WarehouseListType
	{
		WEAPON,
		ARMOR,
		ETCITEM,
		MATERIAL,
		RECIPE,
		AMULETT,
		SPELLBOOK,
		SHOT,
		SCROLL,
		CONSUMABLE,
		SEED,
		POTION,
		QUEST,
		PET,
		OTHER,
		ALL
	}
	
	/** sort order A..Z */
	public static final byte A2Z = 1;
	/** sort order Z..A */
	public static final byte Z2A = -1;
	/** sort order Grade non..S */
	public static final byte GRADE = 2;
	/** sort order Recipe Level 1..9 */
	public static final byte LEVEL = 3;
	/** sort order type */
	public static final byte TYPE = 4;
	/** sort order body part (wearing) */
	public static final byte WEAR = 5;
	/** Maximum Items to put into list */
	public static final int MAX_SORT_LIST_ITEMS = 300;
	
	/**
	 * This will instantiate the Warehouselist the Player asked for
	 * @param player who calls for the itemlist
	 * @param type is the Warehouse Type
	 * @param itemtype is the Itemtype to sort for
	 * @param sortorder is the integer Sortorder like 1 for A..Z (use public constant)
	 */
	public SortedWareHouseWithdrawalList(L2PcInstance player, int type, WarehouseListType itemtype, byte sortorder)
	{
		_whType = type;
		
		_playerAdena = player.getAdena();
		if (player.getActiveWarehouse() == null)
		{
			// Something went wrong!
			LOGGER.warning("error while sending withdraw request to: " + player.getName());
			return;
		}
		
		switch (itemtype)
		{
			case WEAPON:
			{
				_objects = createWeaponList(player.getActiveWarehouse().getItems());
				break;
			}
			case ARMOR:
			{
				_objects = createArmorList(player.getActiveWarehouse().getItems());
				break;
			}
			case ETCITEM:
			{
				_objects = createEtcItemList(player.getActiveWarehouse().getItems());
				break;
			}
			case MATERIAL:
			{
				_objects = createMatList(player.getActiveWarehouse().getItems());
				break;
			}
			case RECIPE:
			{
				_objects = createRecipeList(player.getActiveWarehouse().getItems());
				break;
			}
			case AMULETT:
			{
				_objects = createAmulettList(player.getActiveWarehouse().getItems());
				break;
			}
			case SPELLBOOK:
			{
				_objects = createSpellbookList(player.getActiveWarehouse().getItems());
				break;
			}
			case CONSUMABLE:
			{
				_objects = createConsumableList(player.getActiveWarehouse().getItems());
				break;
			}
			case SHOT:
			{
				_objects = createShotList(player.getActiveWarehouse().getItems());
				break;
			}
			case SCROLL:
			{
				_objects = createScrollList(player.getActiveWarehouse().getItems());
				break;
			}
			case SEED:
			{
				_objects = createSeedList(player.getActiveWarehouse().getItems());
				break;
			}
			case OTHER:
			{
				_objects = createOtherList(player.getActiveWarehouse().getItems());
				break;
			}
			case ALL:
			default:
			{
				_objects = createAllList(player.getActiveWarehouse().getItems());
				break;
			}
		}
		
		try
		{
			switch (sortorder)
			{
				case A2Z:
				case Z2A:
				{
					Collections.sort(_objects, new WarehouseItemNameComparator(sortorder));
					break;
				}
				case GRADE:
				{
					if ((itemtype == WarehouseListType.ARMOR) || (itemtype == WarehouseListType.WEAPON))
					{
						Collections.sort(_objects, new WarehouseItemNameComparator(A2Z));
						Collections.sort(_objects, new WarehouseItemGradeComparator(A2Z));
					}
					break;
				}
				case LEVEL:
				{
					if (itemtype == WarehouseListType.RECIPE)
					{
						Collections.sort(_objects, new WarehouseItemNameComparator(A2Z));
						Collections.sort(_objects, new WarehouseItemRecipeComparator(A2Z));
					}
					break;
				}
				case TYPE:
				{
					if (itemtype == WarehouseListType.MATERIAL)
					{
						Collections.sort(_objects, new WarehouseItemNameComparator(A2Z));
						Collections.sort(_objects, new WarehouseItemTypeComparator(A2Z));
					}
					break;
				}
				case WEAR:
				{
					if (itemtype == WarehouseListType.ARMOR)
					{
						Collections.sort(_objects, new WarehouseItemNameComparator(A2Z));
						Collections.sort(_objects, new WarehouseItemBodypartComparator(A2Z));
					}
					break;
				}
			}
		}
		catch (Exception e)
		{
		}
	}
	
	/**
	 * This public method return the integer of the Sortorder by its name. If you want to have another, add the Comparator and the Constant.
	 * @param order
	 * @return the integer of the sortorder or 1 as default value
	 */
	public static byte getOrder(String order)
	{
		if (order == null)
		{
			return A2Z;
		}
		else if (order.startsWith("A2Z"))
		{
			return A2Z;
		}
		else if (order.startsWith("Z2A"))
		{
			return Z2A;
		}
		else if (order.startsWith("GRADE"))
		{
			return GRADE;
		}
		else if (order.startsWith("TYPE"))
		{
			return TYPE;
		}
		else if (order.startsWith("WEAR"))
		{
			return WEAR;
		}
		else
		{
			try
			{
				return Byte.parseByte(order);
			}
			catch (NumberFormatException ex)
			{
				return A2Z;
			}
		}
	}
	
	/**
	 * This is the common Comparator to sort the items by Name
	 */
	private static class WarehouseItemNameComparator implements Comparator<L2WarehouseItem>
	{
		private byte order = 0;
		
		protected WarehouseItemNameComparator(byte sortOrder)
		{
			order = sortOrder;
		}
		
		@Override
		public int compare(L2WarehouseItem o1, L2WarehouseItem o2)
		{
			if ((o1.getType2() == L2Item.TYPE2_MONEY) && (o2.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? Z2A : A2Z);
			}
			if ((o2.getType2() == L2Item.TYPE2_MONEY) && (o1.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? A2Z : Z2A);
			}
			final String s1 = o1.getItemName();
			final String s2 = o2.getItemName();
			return (order == A2Z ? s1.compareTo(s2) : s2.compareTo(s1));
		}
	}
	
	/**
	 * This Comparator is used to sort by Recipe Level
	 */
	private static class WarehouseItemRecipeComparator implements Comparator<L2WarehouseItem>
	{
		private int order = 0;
		
		private RecipeData rd = null;
		
		protected WarehouseItemRecipeComparator(int sortOrder)
		{
			order = sortOrder;
			rd = RecipeData.getInstance();
		}
		
		@Override
		public int compare(L2WarehouseItem o1, L2WarehouseItem o2)
		{
			if ((o1.getType2() == L2Item.TYPE2_MONEY) && (o2.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? Z2A : A2Z);
			}
			if ((o2.getType2() == L2Item.TYPE2_MONEY) && (o1.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? A2Z : Z2A);
			}
			if ((o1.isEtcItem() && (o1.getItemType() == EtcItemType.RECIPE)) && (o2.isEtcItem() && (o2.getItemType() == EtcItemType.RECIPE)))
			{
				try
				{
					final L2RecipeList rp1 = rd.getRecipeByItemId(o1.getItemId());
					final L2RecipeList rp2 = rd.getRecipeByItemId(o2.getItemId());
					
					if (rp1 == null)
					{
						return (order == A2Z ? A2Z : Z2A);
					}
					if (rp2 == null)
					{
						return (order == A2Z ? Z2A : A2Z);
					}
					
					final Integer i1 = rp1.getLevel();
					final Integer i2 = rp2.getLevel();
					
					return (order == A2Z ? i1.compareTo(i2) : i2.compareTo(i1));
				}
				catch (Exception e)
				{
					return 0;
				}
			}
			
			final String s1 = o1.getItemName();
			final String s2 = o2.getItemName();
			return (order == A2Z ? s1.compareTo(s2) : s2.compareTo(s1));
		}
	}
	
	/**
	 * This Comparator is used to sort the Items by BodyPart
	 */
	private static class WarehouseItemBodypartComparator implements Comparator<L2WarehouseItem>
	{
		private byte order = 0;
		
		protected WarehouseItemBodypartComparator(byte sortOrder)
		{
			order = sortOrder;
		}
		
		@Override
		public int compare(L2WarehouseItem o1, L2WarehouseItem o2)
		{
			if ((o1.getType2() == L2Item.TYPE2_MONEY) && (o2.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? Z2A : A2Z);
			}
			if ((o2.getType2() == L2Item.TYPE2_MONEY) && (o1.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? A2Z : Z2A);
			}
			final Integer i1 = o1.getBodyPart();
			final Integer i2 = o2.getBodyPart();
			return (order == A2Z ? i1.compareTo(i2) : i2.compareTo(i1));
		}
	}
	
	/**
	 * This Comparator is used to sort by the Item Grade (e.g. Non..S-Grade)
	 */
	private static class WarehouseItemGradeComparator implements Comparator<L2WarehouseItem>
	{
		byte order = 0;
		
		protected WarehouseItemGradeComparator(byte sortOrder)
		{
			order = sortOrder;
		}
		
		@Override
		public int compare(L2WarehouseItem o1, L2WarehouseItem o2)
		{
			if ((o1.getType2() == L2Item.TYPE2_MONEY) && (o2.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? Z2A : A2Z);
			}
			if ((o2.getType2() == L2Item.TYPE2_MONEY) && (o1.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? A2Z : Z2A);
			}
			final CrystalType i1 = o1.getItemGrade();
			final CrystalType i2 = o2.getItemGrade();
			return (order == A2Z ? i1.compareTo(i2) : i2.compareTo(i1));
		}
	}
	
	/**
	 * This Comparator will sort by Item Type. Unfortunatly this will only have a good result if the Database Table for the ETCITEM.TYPE column is fixed!
	 */
	private static class WarehouseItemTypeComparator implements Comparator<L2WarehouseItem>
	{
		byte order = 0;
		
		protected WarehouseItemTypeComparator(byte sortOrder)
		{
			order = sortOrder;
		}
		
		@Override
		public int compare(L2WarehouseItem o1, L2WarehouseItem o2)
		{
			if ((o1.getType2() == L2Item.TYPE2_MONEY) && (o2.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? Z2A : A2Z);
			}
			if ((o2.getType2() == L2Item.TYPE2_MONEY) && (o1.getType2() != L2Item.TYPE2_MONEY))
			{
				return (order == A2Z ? A2Z : Z2A);
			}
			try
			{
				final MaterialType i1 = o1.getItem().getMaterialType();
				final MaterialType i2 = o2.getItem().getMaterialType();
				return (order == A2Z ? i1.compareTo(i2) : i2.compareTo(i1));
			}
			catch (Exception e)
			{
				return 0;
			}
		}
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Weapon</li>
	 * <li>Arrow</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createWeaponList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if (item.isWeapon() || (item.getItem().getType2() == L2Item.TYPE2_WEAPON) || (item.isEtcItem() && (item.getItemType() == EtcItemType.ARROW)) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Armor</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createArmorList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if (item.isArmor() || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Everything which is no Weapon/Armor</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createEtcItemList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if (item.isEtcItem() || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Materials</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createMatList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && (item.getEtcItem().getItemType() == EtcItemType.MATERIAL)) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Recipes</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createRecipeList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && (item.getEtcItem().getItemType() == EtcItemType.RECIPE)) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Amulett</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createAmulettList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && (item.getItemName().toUpperCase().startsWith("AMULET"))) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Spellbook & Dwarven Drafts</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createSpellbookList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && (!item.getItemName().toUpperCase().startsWith("AMULET"))) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Consumables (Potions, Shots, ...)</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createConsumableList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && ((item.getEtcItem().getItemType() == EtcItemType.SCROLL) || (item.getEtcItem().getItemType() == EtcItemType.SHOT))) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Shots</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createShotList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && (item.getEtcItem().getItemType() == EtcItemType.SHOT)) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Scrolls/Potions</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createScrollList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && (item.getEtcItem().getItemType() == EtcItemType.SCROLL)) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Seeds</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createSeedList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && (item.getEtcItem().getItemType() == EtcItemType.SEED)) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>Everything which is no Weapon/Armor, Material, Recipe, Spellbook, Scroll or Shot</li>
	 * <li>Money</li>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createOtherList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if ((item.isEtcItem() && ((item.getEtcItem().getItemType() != EtcItemType.MATERIAL) && (item.getEtcItem().getItemType() != EtcItemType.RECIPE) && (item.getEtcItem().getItemType() != EtcItemType.SCROLL) && (item.getEtcItem().getItemType() != EtcItemType.SHOT))) || (item.getItem().getType2() == L2Item.TYPE2_MONEY))
			{
				if (list.size() < MAX_SORT_LIST_ITEMS)
				{
					list.add(new L2WarehouseItem(item));
				}
				else
				{
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * This method is used to limit the given Warehouse List to:
	 * <li>no limit</li> This may sound strange but we return the given Array as a List<L2WarehouseItem>
	 * @param _items complete Warehouse List
	 * @return limited Item List
	 */
	private List<L2WarehouseItem> createAllList(L2ItemInstance[] _items)
	{
		final List<L2WarehouseItem> list = new ArrayList<>();
		for (L2ItemInstance item : _items)
		{
			if (list.size() < MAX_SORT_LIST_ITEMS)
			{
				list.add(new L2WarehouseItem(item));
			}
			else
			{
				continue;
			}
		}
		return list;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.WAREHOUSE_WITHDRAW_LIST.writeId(packet);
		/*
		 * 0x01-Private Warehouse 0x02-Clan Warehouse 0x03-Castle Warehouse 0x04-Warehouse
		 */
		packet.writeH(_whType);
		packet.writeQ(_playerAdena);
		packet.writeH(_objects.size());
		
		for (L2WarehouseItem item : _objects)
		{
			packet.writeD(item.getObjectId());
			packet.writeD(item.getItem().getDisplayId());
			packet.writeD(item.getLocationSlot());
			packet.writeQ(item.getCount());
			packet.writeH(item.getItem().getType2());
			packet.writeH(item.getCustomType1());
			packet.writeH(0x00); // Can't be equipped in WH
			packet.writeD(item.getItem().getBodyPart());
			packet.writeH(item.getEnchantLevel());
			packet.writeH(item.getCustomType2());
			if (item.isAugmented())
			{
				packet.writeD(item.getAugmentationId());
			}
			else
			{
				packet.writeD(0x00);
			}
			packet.writeD(item.getMana());
			packet.writeD(item.getTime());
			packet.writeH(item.getAttackElementType());
			packet.writeH(item.getAttackElementPower());
			for (byte i = 0; i < 6; i++)
			{
				packet.writeH(item.getElementDefAttr(i));
			}
			// Enchant Effects
			for (int op : item.getEnchantOptions())
			{
				packet.writeH(op);
			}
			packet.writeD(item.getObjectId());
		}
		return true;
	}
}

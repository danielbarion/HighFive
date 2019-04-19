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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.commons.database.DatabaseFactory;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.data.xml.impl.ArmorSetsData;
import com.l2jmobius.gameserver.datatables.ItemTable;
import com.l2jmobius.gameserver.enums.ItemLocation;
import com.l2jmobius.gameserver.enums.PrivateStoreType;
import com.l2jmobius.gameserver.model.L2ArmorSet;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.PcCondOverride;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.items.L2Item;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.items.type.EtcItemType;
import com.l2jmobius.gameserver.model.items.type.WeaponType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.serverpackets.SkillCoolTime;

/**
 * This class manages inventory
 * @version $Revision: 1.13.2.9.2.12 $ $Date: 2005/03/29 23:15:15 $ rewritten 23.2.2006 by Advi
 */
public abstract class Inventory extends ItemContainer
{
	protected static final Logger LOGGER = Logger.getLogger(Inventory.class.getName());
	
	public interface PaperdollListener
	{
		void notifyEquiped(int slot, L2ItemInstance inst, Inventory inventory);
		
		void notifyUnequiped(int slot, L2ItemInstance inst, Inventory inventory);
	}
	
	// Common Items
	public static final int ADENA_ID = 57;
	public static final int ANCIENT_ADENA_ID = 5575;
	
	public static final long MAX_ADENA = Config.MAX_ADENA;
	
	public static final int PAPERDOLL_UNDER = 0;
	public static final int PAPERDOLL_HEAD = 1;
	public static final int PAPERDOLL_HAIR = 2;
	public static final int PAPERDOLL_HAIR2 = 3;
	public static final int PAPERDOLL_NECK = 4;
	public static final int PAPERDOLL_RHAND = 5;
	public static final int PAPERDOLL_CHEST = 6;
	public static final int PAPERDOLL_LHAND = 7;
	public static final int PAPERDOLL_REAR = 8;
	public static final int PAPERDOLL_LEAR = 9;
	public static final int PAPERDOLL_GLOVES = 10;
	public static final int PAPERDOLL_LEGS = 11;
	public static final int PAPERDOLL_FEET = 12;
	public static final int PAPERDOLL_RFINGER = 13;
	public static final int PAPERDOLL_LFINGER = 14;
	public static final int PAPERDOLL_LBRACELET = 15;
	public static final int PAPERDOLL_RBRACELET = 16;
	public static final int PAPERDOLL_DECO1 = 17;
	public static final int PAPERDOLL_DECO2 = 18;
	public static final int PAPERDOLL_DECO3 = 19;
	public static final int PAPERDOLL_DECO4 = 20;
	public static final int PAPERDOLL_DECO5 = 21;
	public static final int PAPERDOLL_DECO6 = 22;
	public static final int PAPERDOLL_CLOAK = 23;
	public static final int PAPERDOLL_BELT = 24;
	public static final int PAPERDOLL_TOTALSLOTS = 25;
	
	// Speed percentage mods
	public static final double MAX_ARMOR_WEIGHT = 12000;
	
	private final L2ItemInstance[] _paperdoll;
	private final List<PaperdollListener> _paperdollListeners;
	
	// protected to be accessed from child classes only
	protected int _totalWeight;
	
	// used to quickly check for using of items of special type
	private int _wearedMask;
	
	// Recorder of alterations in inventory
	private static final class ChangeRecorder implements PaperdollListener
	{
		private final Inventory _inventory;
		private final List<L2ItemInstance> _changed;
		
		/**
		 * Constructor of the ChangeRecorder
		 * @param inventory
		 */
		ChangeRecorder(Inventory inventory)
		{
			_inventory = inventory;
			_changed = new ArrayList<>();
			_inventory.addPaperdollListener(this);
		}
		
		/**
		 * Add alteration in inventory when item equipped
		 * @param slot
		 * @param item
		 * @param inventory
		 */
		@Override
		public void notifyEquiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (!_changed.contains(item))
			{
				_changed.add(item);
			}
		}
		
		/**
		 * Add alteration in inventory when item unequipped
		 * @param slot
		 * @param item
		 * @param inventory
		 */
		@Override
		public void notifyUnequiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (!_changed.contains(item))
			{
				_changed.add(item);
			}
		}
		
		/**
		 * Returns alterations in inventory
		 * @return L2ItemInstance[] : array of altered items
		 */
		public L2ItemInstance[] getChangedItems()
		{
			return _changed.toArray(new L2ItemInstance[_changed.size()]);
		}
	}
	
	private static final class BowCrossRodListener implements PaperdollListener
	{
		private static BowCrossRodListener instance = new BowCrossRodListener();
		
		public static BowCrossRodListener getInstance()
		{
			return instance;
		}
		
		@Override
		public void notifyUnequiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (slot != PAPERDOLL_RHAND)
			{
				return;
			}
			
			if (item.getItemType() == WeaponType.BOW)
			{
				final L2ItemInstance arrow = inventory.getPaperdollItem(PAPERDOLL_LHAND);
				
				if (arrow != null)
				{
					inventory.setPaperdollItem(PAPERDOLL_LHAND, null);
				}
			}
			else if (item.getItemType() == WeaponType.CROSSBOW)
			{
				final L2ItemInstance bolts = inventory.getPaperdollItem(PAPERDOLL_LHAND);
				
				if (bolts != null)
				{
					inventory.setPaperdollItem(PAPERDOLL_LHAND, null);
				}
			}
			else if (item.getItemType() == WeaponType.FISHINGROD)
			{
				final L2ItemInstance lure = inventory.getPaperdollItem(PAPERDOLL_LHAND);
				
				if (lure != null)
				{
					inventory.setPaperdollItem(PAPERDOLL_LHAND, null);
				}
			}
		}
		
		@Override
		public void notifyEquiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (slot != PAPERDOLL_RHAND)
			{
				return;
			}
			
			if (item.getItemType() == WeaponType.BOW)
			{
				final L2ItemInstance arrow = inventory.findArrowForBow(item.getItem());
				
				if (arrow != null)
				{
					inventory.setPaperdollItem(PAPERDOLL_LHAND, arrow);
				}
			}
			else if (item.getItemType() == WeaponType.CROSSBOW)
			{
				final L2ItemInstance bolts = inventory.findBoltForCrossBow(item.getItem());
				
				if (bolts != null)
				{
					inventory.setPaperdollItem(PAPERDOLL_LHAND, bolts);
				}
			}
		}
	}
	
	private static final class StatsListener implements PaperdollListener
	{
		private static StatsListener instance = new StatsListener();
		
		public static StatsListener getInstance()
		{
			return instance;
		}
		
		@Override
		public void notifyUnequiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			inventory.getOwner().removeStatsOwner(item);
		}
		
		@Override
		public void notifyEquiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			inventory.getOwner().addStatFuncs(item.getStatFuncs(inventory.getOwner()));
		}
	}
	
	private static final class ItemSkillsListener implements PaperdollListener
	{
		private static ItemSkillsListener instance = new ItemSkillsListener();
		
		public static ItemSkillsListener getInstance()
		{
			return instance;
		}
		
		@Override
		public void notifyUnequiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (!inventory.getOwner().isPlayer())
			{
				return;
			}
			final L2PcInstance player = (L2PcInstance) inventory.getOwner();
			
			Skill enchant4Skill;
			Skill itemSkill;
			final L2Item it = item.getItem();
			boolean update = false;
			boolean updateTimeStamp = false;
			
			// Remove augmentation bonuses on unequip
			if (item.isAugmented())
			{
				item.getAugmentation().removeBonus(player);
			}
			
			item.removeElementAttrBonus(player);
			
			// Remove skills bestowed from +4 armor
			if (item.getEnchantLevel() >= 4)
			{
				enchant4Skill = it.getEnchant4Skill();
				
				if (enchant4Skill != null)
				{
					player.removeSkill(enchant4Skill, false, enchant4Skill.isPassive());
					update = true;
				}
			}
			
			item.clearEnchantStats();
			
			final SkillHolder[] skills = it.getSkills();
			
			if (skills != null)
			{
				for (SkillHolder skillInfo : skills)
				{
					if (skillInfo == null)
					{
						continue;
					}
					
					itemSkill = skillInfo.getSkill();
					
					if (itemSkill != null)
					{
						player.removeSkill(itemSkill, false, itemSkill.isPassive());
						update = true;
					}
					else
					{
						LOGGER.warning("Inventory.ItemSkillsListener.Weapon: Incorrect skill: " + skillInfo + ".");
					}
				}
			}
			
			if (item.isArmor())
			{
				for (L2ItemInstance itm : inventory.getItems())
				{
					if (!itm.isEquipped() || (itm.getItem().getSkills() == null) || itm.equals(item))
					{
						continue;
					}
					for (SkillHolder sk : itm.getItem().getSkills())
					{
						if (player.getSkillLevel(sk.getSkillId()) != 0)
						{
							continue;
						}
						
						itemSkill = sk.getSkill();
						
						if (itemSkill != null)
						{
							player.addSkill(itemSkill, false);
							
							if (itemSkill.isActive())
							{
								if (!player.hasSkillReuse(itemSkill.getReuseHashCode()))
								{
									final int equipDelay = item.getEquipReuseDelay();
									if (equipDelay > 0)
									{
										player.addTimeStamp(itemSkill, equipDelay);
										player.disableSkill(itemSkill, equipDelay);
									}
								}
								updateTimeStamp = true;
							}
							update = true;
						}
					}
				}
			}
			
			// Apply skill, if weapon have "skills on unequip"
			final Skill unequipSkill = it.getUnequipSkill();
			if (unequipSkill != null)
			{
				unequipSkill.activateSkill(player, player);
			}
			
			if (update)
			{
				player.sendSkillList();
				
				if (updateTimeStamp)
				{
					player.sendPacket(new SkillCoolTime(player));
				}
			}
		}
		
		@Override
		public void notifyEquiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (!inventory.getOwner().isPlayer())
			{
				return;
			}
			
			final L2PcInstance player = (L2PcInstance) inventory.getOwner();
			
			Skill enchant4Skill;
			Skill itemSkill;
			final L2Item it = item.getItem();
			boolean update = false;
			boolean updateTimeStamp = false;
			
			// Apply augmentation bonuses on equip
			if (item.isAugmented())
			{
				item.getAugmentation().applyBonus(player);
			}
			
			item.updateElementAttrBonus(player);
			
			// Add skills bestowed from +4 armor
			if (item.getEnchantLevel() >= 4)
			{
				enchant4Skill = it.getEnchant4Skill();
				
				if (enchant4Skill != null)
				{
					player.addSkill(enchant4Skill, false);
					update = true;
				}
			}
			
			item.applyEnchantStats();
			
			final SkillHolder[] skills = it.getSkills();
			
			if (skills != null)
			{
				for (SkillHolder skillInfo : skills)
				{
					if (skillInfo == null)
					{
						continue;
					}
					
					itemSkill = skillInfo.getSkill();
					
					if (itemSkill != null)
					{
						itemSkill.setReferenceItemId(item.getId());
						player.addSkill(itemSkill, false);
						
						if (itemSkill.isActive())
						{
							if (!player.hasSkillReuse(itemSkill.getReuseHashCode()))
							{
								final int equipDelay = item.getEquipReuseDelay();
								if (equipDelay > 0)
								{
									player.addTimeStamp(itemSkill, equipDelay);
									player.disableSkill(itemSkill, equipDelay);
								}
							}
							updateTimeStamp = true;
						}
						update = true;
					}
					else
					{
						LOGGER.warning("Inventory.ItemSkillsListener.Weapon: Incorrect skill: " + skillInfo + ".");
					}
				}
			}
			
			if (update)
			{
				player.sendSkillList();
				
				if (updateTimeStamp)
				{
					player.sendPacket(new SkillCoolTime(player));
				}
			}
		}
	}
	
	private static final class ArmorSetListener implements PaperdollListener
	{
		private static ArmorSetListener instance = new ArmorSetListener();
		
		public static ArmorSetListener getInstance()
		{
			return instance;
		}
		
		@Override
		public void notifyEquiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (!inventory.getOwner().isPlayer())
			{
				return;
			}
			
			final L2PcInstance player = (L2PcInstance) inventory.getOwner();
			
			// Checks if player is wearing a chest item
			final L2ItemInstance chestItem = inventory.getPaperdollItem(PAPERDOLL_CHEST);
			
			if (chestItem == null)
			{
				return;
			}
			
			// Checks for armor set for the equipped chest.
			if (!ArmorSetsData.getInstance().isArmorSet(chestItem.getId()))
			{
				return;
			}
			final L2ArmorSet armorSet = ArmorSetsData.getInstance().getSet(chestItem.getId());
			boolean update = false;
			boolean updateTimeStamp = false;
			// Checks if equipped item is part of set
			if (armorSet.containItem(slot, item.getId()))
			{
				if (armorSet.containAll(player))
				{
					Skill itemSkill;
					final List<SkillHolder> skills = armorSet.getSkills();
					
					if (skills != null)
					{
						for (SkillHolder holder : skills)
						{
							
							itemSkill = holder.getSkill();
							if (itemSkill != null)
							{
								player.addSkill(itemSkill, false);
								
								if (itemSkill.isActive())
								{
									if (!player.hasSkillReuse(itemSkill.getReuseHashCode()))
									{
										final int equipDelay = item.getEquipReuseDelay();
										if (equipDelay > 0)
										{
											player.addTimeStamp(itemSkill, equipDelay);
											player.disableSkill(itemSkill, equipDelay);
										}
									}
									updateTimeStamp = true;
								}
								update = true;
							}
							else
							{
								LOGGER.warning("Inventory.ArmorSetListener: Incorrect skill: " + holder + ".");
							}
						}
					}
					
					if (armorSet.containShield(player)) // has shield from set
					{
						for (SkillHolder holder : armorSet.getShieldSkillId())
						{
							if (holder.getSkill() != null)
							{
								player.addSkill(holder.getSkill(), false);
								update = true;
							}
							else
							{
								LOGGER.warning("Inventory.ArmorSetListener: Incorrect skill: " + holder + ".");
							}
						}
					}
					
					if (armorSet.isEnchanted6(player)) // has all parts of set enchanted to 6 or more
					{
						for (SkillHolder holder : armorSet.getEnchant6skillId())
						{
							if (holder.getSkill() != null)
							{
								player.addSkill(holder.getSkill(), false);
								update = true;
							}
							else
							{
								LOGGER.warning("Inventory.ArmorSetListener: Incorrect skill: " + holder + ".");
							}
						}
					}
				}
			}
			else if (armorSet.containShield(item.getId()))
			{
				for (SkillHolder holder : armorSet.getShieldSkillId())
				{
					if (holder.getSkill() != null)
					{
						player.addSkill(holder.getSkill(), false);
						update = true;
					}
					else
					{
						LOGGER.warning("Inventory.ArmorSetListener: Incorrect skill: " + holder + ".");
					}
				}
			}
			
			if (update)
			{
				player.sendSkillList();
				
				if (updateTimeStamp)
				{
					player.sendPacket(new SkillCoolTime(player));
				}
			}
		}
		
		@Override
		public void notifyUnequiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (!inventory.getOwner().isPlayer())
			{
				return;
			}
			
			final L2PcInstance player = (L2PcInstance) inventory.getOwner();
			
			boolean remove = false;
			Skill itemSkill;
			List<SkillHolder> skills = null;
			List<SkillHolder> shieldSkill = null; // shield skill
			List<SkillHolder> skillId6 = null; // enchant +6 skill
			
			if (slot == PAPERDOLL_CHEST)
			{
				if (!ArmorSetsData.getInstance().isArmorSet(item.getId()))
				{
					return;
				}
				final L2ArmorSet armorSet = ArmorSetsData.getInstance().getSet(item.getId());
				remove = true;
				skills = armorSet.getSkills();
				shieldSkill = armorSet.getShieldSkillId();
				skillId6 = armorSet.getEnchant6skillId();
			}
			else
			{
				final L2ItemInstance chestItem = inventory.getPaperdollItem(PAPERDOLL_CHEST);
				if (chestItem == null)
				{
					return;
				}
				
				final L2ArmorSet armorSet = ArmorSetsData.getInstance().getSet(chestItem.getId());
				if (armorSet == null)
				{
					return;
				}
				
				if (armorSet.containItem(slot, item.getId())) // removed part of set
				{
					remove = true;
					skills = armorSet.getSkills();
					shieldSkill = armorSet.getShieldSkillId();
					skillId6 = armorSet.getEnchant6skillId();
				}
				else if (armorSet.containShield(item.getId())) // removed shield
				{
					remove = true;
					shieldSkill = armorSet.getShieldSkillId();
				}
			}
			
			if (remove)
			{
				if (skills != null)
				{
					for (SkillHolder holder : skills)
					{
						itemSkill = holder.getSkill();
						if (itemSkill != null)
						{
							player.removeSkill(itemSkill, false, itemSkill.isPassive());
						}
						else
						{
							LOGGER.warning("Inventory.ArmorSetListener: Incorrect skill: " + holder + ".");
						}
					}
				}
				
				if (shieldSkill != null)
				{
					for (SkillHolder holder : shieldSkill)
					{
						itemSkill = holder.getSkill();
						if (itemSkill != null)
						{
							player.removeSkill(itemSkill, false, itemSkill.isPassive());
						}
						else
						{
							LOGGER.warning("Inventory.ArmorSetListener: Incorrect skill: " + holder + ".");
						}
					}
				}
				
				if (skillId6 != null)
				{
					for (SkillHolder holder : skillId6)
					{
						itemSkill = holder.getSkill();
						if (itemSkill != null)
						{
							player.removeSkill(itemSkill, false, itemSkill.isPassive());
						}
						else
						{
							LOGGER.warning("Inventory.ArmorSetListener: Incorrect skill: " + holder + ".");
						}
					}
				}
				
				player.checkItemRestriction();
				player.sendSkillList();
			}
		}
	}
	
	private static final class BraceletListener implements PaperdollListener
	{
		private static BraceletListener instance = new BraceletListener();
		
		public static BraceletListener getInstance()
		{
			return instance;
		}
		
		@Override
		public void notifyUnequiped(int slot, L2ItemInstance item, Inventory inventory)
		{
			if (item.getItem().getBodyPart() == L2Item.SLOT_R_BRACELET)
			{
				inventory.unEquipItemInSlot(PAPERDOLL_DECO1);
				inventory.unEquipItemInSlot(PAPERDOLL_DECO2);
				inventory.unEquipItemInSlot(PAPERDOLL_DECO3);
				inventory.unEquipItemInSlot(PAPERDOLL_DECO4);
				inventory.unEquipItemInSlot(PAPERDOLL_DECO5);
				inventory.unEquipItemInSlot(PAPERDOLL_DECO6);
			}
		}
		
		// Note (April 3, 2009): Currently on equip, talismans do not display properly, do we need checks here to fix this?
		@Override
		public void notifyEquiped(int slot, L2ItemInstance item, Inventory inventory)
		{
		}
	}
	
	/**
	 * Constructor of the inventory
	 */
	protected Inventory()
	{
		_paperdoll = new L2ItemInstance[PAPERDOLL_TOTALSLOTS];
		_paperdollListeners = new ArrayList<>();
		
		if (this instanceof PcInventory)
		{
			addPaperdollListener(ArmorSetListener.getInstance());
			addPaperdollListener(BowCrossRodListener.getInstance());
			addPaperdollListener(ItemSkillsListener.getInstance());
			addPaperdollListener(BraceletListener.getInstance());
		}
		
		// common
		addPaperdollListener(StatsListener.getInstance());
		
	}
	
	protected abstract ItemLocation getEquipLocation();
	
	/**
	 * Returns the instance of new ChangeRecorder
	 * @return ChangeRecorder
	 */
	private ChangeRecorder newRecorder()
	{
		return new ChangeRecorder(this);
	}
	
	/**
	 * Drop item from inventory and updates database
	 * @param process : String Identifier of process triggering this action
	 * @param item : L2ItemInstance to be dropped
	 * @param actor : L2PcInstance Player requesting the item drop
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the destroyed item or the updated item in inventory
	 */
	public L2ItemInstance dropItem(String process, L2ItemInstance item, L2PcInstance actor, Object reference)
	{
		if (item == null)
		{
			return null;
		}
		
		synchronized (item)
		{
			if (!_items.contains(item))
			{
				return null;
			}
			
			removeItem(item);
			item.setOwnerId(process, 0, actor, reference);
			item.setItemLocation(ItemLocation.VOID);
			item.setLastChange(L2ItemInstance.REMOVED);
			
			item.updateDatabase();
			refreshWeight();
		}
		return item;
	}
	
	/**
	 * Drop item from inventory by using its <B>objectID</B> and updates database
	 * @param process : String Identifier of process triggering this action
	 * @param objectId : int Item Instance identifier of the item to be dropped
	 * @param count : int Quantity of items to be dropped
	 * @param actor : L2PcInstance Player requesting the item drop
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the destroyed item or the updated item in inventory
	 */
	public L2ItemInstance dropItem(String process, int objectId, long count, L2PcInstance actor, Object reference)
	{
		L2ItemInstance item = getItemByObjectId(objectId);
		if (item == null)
		{
			return null;
		}
		
		synchronized (item)
		{
			if (!_items.contains(item))
			{
				return null;
			}
			
			// Adjust item quantity and create new instance to drop
			// Directly drop entire item
			if (item.getCount() > count)
			{
				item.changeCount(process, -count, actor, reference);
				item.setLastChange(L2ItemInstance.MODIFIED);
				item.updateDatabase();
				
				item = ItemTable.getInstance().createItem(process, item.getId(), count, actor, reference);
				item.updateDatabase();
				refreshWeight();
				return item;
			}
		}
		return dropItem(process, item, actor, reference);
	}
	
	/**
	 * Adds item to inventory for further adjustments and Equip it if necessary (itemlocation defined)
	 * @param item : L2ItemInstance to be added from inventory
	 */
	@Override
	protected void addItem(L2ItemInstance item)
	{
		super.addItem(item);
		if (item.isEquipped())
		{
			equipItem(item);
		}
	}
	
	/**
	 * Removes item from inventory for further adjustments.
	 * @param item : L2ItemInstance to be removed from inventory
	 */
	@Override
	protected boolean removeItem(L2ItemInstance item)
	{
		// Unequip item if equiped
		for (int i = 0; i < _paperdoll.length; i++)
		{
			if (_paperdoll[i] == item)
			{
				unEquipItemInSlot(i);
			}
		}
		return super.removeItem(item);
	}
	
	/**
	 * @param slot the slot.
	 * @return the item in the paperdoll slot
	 */
	public L2ItemInstance getPaperdollItem(int slot)
	{
		return _paperdoll[slot];
	}
	
	/**
	 * @param slot the slot.
	 * @return {@code true} if specified paperdoll slot is empty, {@code false} otherwise
	 */
	public boolean isPaperdollSlotEmpty(int slot)
	{
		return _paperdoll[slot] == null;
	}
	
	public static int getPaperdollIndex(int slot)
	{
		switch (slot)
		{
			case L2Item.SLOT_UNDERWEAR:
			{
				return PAPERDOLL_UNDER;
			}
			case L2Item.SLOT_R_EAR:
			{
				return PAPERDOLL_REAR;
			}
			case L2Item.SLOT_LR_EAR:
			case L2Item.SLOT_L_EAR:
			{
				return PAPERDOLL_LEAR;
			}
			case L2Item.SLOT_NECK:
			{
				return PAPERDOLL_NECK;
			}
			case L2Item.SLOT_R_FINGER:
			case L2Item.SLOT_LR_FINGER:
			{
				return PAPERDOLL_RFINGER;
			}
			case L2Item.SLOT_L_FINGER:
			{
				return PAPERDOLL_LFINGER;
			}
			case L2Item.SLOT_HEAD:
			{
				return PAPERDOLL_HEAD;
			}
			case L2Item.SLOT_R_HAND:
			case L2Item.SLOT_LR_HAND:
			{
				return PAPERDOLL_RHAND;
			}
			case L2Item.SLOT_L_HAND:
			{
				return PAPERDOLL_LHAND;
			}
			case L2Item.SLOT_GLOVES:
			{
				return PAPERDOLL_GLOVES;
			}
			case L2Item.SLOT_CHEST:
			case L2Item.SLOT_FULL_ARMOR:
			case L2Item.SLOT_ALLDRESS:
			{
				return PAPERDOLL_CHEST;
			}
			case L2Item.SLOT_LEGS:
			{
				return PAPERDOLL_LEGS;
			}
			case L2Item.SLOT_FEET:
			{
				return PAPERDOLL_FEET;
			}
			case L2Item.SLOT_BACK:
			{
				return PAPERDOLL_CLOAK;
			}
			case L2Item.SLOT_HAIR:
			case L2Item.SLOT_HAIRALL:
			{
				return PAPERDOLL_HAIR;
			}
			case L2Item.SLOT_HAIR2:
			{
				return PAPERDOLL_HAIR2;
			}
			case L2Item.SLOT_R_BRACELET:
			{
				return PAPERDOLL_RBRACELET;
			}
			case L2Item.SLOT_L_BRACELET:
			{
				return PAPERDOLL_LBRACELET;
			}
			case L2Item.SLOT_DECO:
			{
				return PAPERDOLL_DECO1; // return first we deal with it later
			}
			case L2Item.SLOT_BELT:
			{
				return PAPERDOLL_BELT;
			}
		}
		return -1;
	}
	
	/**
	 * Returns the item in the paperdoll L2Item slot
	 * @param slot identifier
	 * @return L2ItemInstance
	 */
	public L2ItemInstance getPaperdollItemByL2ItemId(int slot)
	{
		final int index = getPaperdollIndex(slot);
		if (index == -1)
		{
			return null;
		}
		return _paperdoll[index];
	}
	
	/**
	 * Returns the ID of the item in the paperdoll slot
	 * @param slot : int designating the slot
	 * @return int designating the ID of the item
	 */
	public int getPaperdollItemId(int slot)
	{
		final L2ItemInstance item = _paperdoll[slot];
		if (item != null)
		{
			return item.getId();
		}
		return 0;
	}
	
	/**
	 * Returns the ID of the item in the paperdoll slot
	 * @param slot : int designating the slot
	 * @return int designating the ID of the item
	 */
	public int getPaperdollItemDisplayId(int slot)
	{
		final L2ItemInstance item = _paperdoll[slot];
		return (item != null) ? item.getDisplayId() : 0;
	}
	
	public int getPaperdollAugmentationId(int slot)
	{
		final L2ItemInstance item = _paperdoll[slot];
		return ((item != null) && (item.getAugmentation() != null)) ? item.getAugmentation().getAugmentationId() : 0;
	}
	
	/**
	 * Returns the objectID associated to the item in the paperdoll slot
	 * @param slot : int pointing out the slot
	 * @return int designating the objectID
	 */
	public int getPaperdollObjectId(int slot)
	{
		final L2ItemInstance item = _paperdoll[slot];
		return (item != null) ? item.getObjectId() : 0;
	}
	
	/**
	 * Adds new inventory's paperdoll listener.
	 * @param listener the new listener
	 */
	public synchronized void addPaperdollListener(PaperdollListener listener)
	{
		if (!_paperdollListeners.contains(listener))
		{
			_paperdollListeners.add(listener);
		}
	}
	
	/**
	 * Removes a paperdoll listener.
	 * @param listener the listener to be deleted
	 */
	public synchronized void removePaperdollListener(PaperdollListener listener)
	{
		_paperdollListeners.remove(listener);
	}
	
	/**
	 * Equips an item in the given slot of the paperdoll.<br>
	 * <U><I>Remark :</I></U> The item <B>must be</B> in the inventory already.
	 * @param slot : int pointing out the slot of the paperdoll
	 * @param item : L2ItemInstance pointing out the item to add in slot
	 * @return L2ItemInstance designating the item placed in the slot before
	 */
	public synchronized L2ItemInstance setPaperdollItem(int slot, L2ItemInstance item)
	{
		final L2ItemInstance old = _paperdoll[slot];
		if (old != item)
		{
			if (old != null)
			{
				_paperdoll[slot] = null;
				// Put old item from paperdoll slot to base location
				old.setItemLocation(getBaseLocation());
				old.setLastChange(L2ItemInstance.MODIFIED);
				// Get the mask for paperdoll
				int mask = 0;
				for (int i = 0; i < PAPERDOLL_TOTALSLOTS; i++)
				{
					final L2ItemInstance pi = _paperdoll[i];
					if (pi != null)
					{
						mask |= pi.getItem().getItemMask();
					}
				}
				_wearedMask = mask;
				// Notify all paperdoll listener in order to unequip old item in slot
				for (PaperdollListener listener : _paperdollListeners)
				{
					if (listener == null)
					{
						continue;
					}
					
					listener.notifyUnequiped(slot, old, this);
				}
				old.updateDatabase();
			}
			// Add new item in slot of paperdoll
			if (item != null)
			{
				_paperdoll[slot] = item;
				item.setItemLocation(getEquipLocation(), slot);
				item.setLastChange(L2ItemInstance.MODIFIED);
				_wearedMask |= item.getItem().getItemMask();
				for (PaperdollListener listener : _paperdollListeners)
				{
					if (listener == null)
					{
						continue;
					}
					
					listener.notifyEquiped(slot, item, this);
				}
				item.updateDatabase();
			}
		}
		return old;
	}
	
	/**
	 * @return the mask of wore item
	 */
	public int getWearedMask()
	{
		return _wearedMask;
	}
	
	public int getSlotFromItem(L2ItemInstance item)
	{
		int slot = -1;
		final int location = item.getLocationSlot();
		switch (location)
		{
			case PAPERDOLL_UNDER:
			{
				slot = L2Item.SLOT_UNDERWEAR;
				break;
			}
			case PAPERDOLL_LEAR:
			{
				slot = L2Item.SLOT_L_EAR;
				break;
			}
			case PAPERDOLL_REAR:
			{
				slot = L2Item.SLOT_R_EAR;
				break;
			}
			case PAPERDOLL_NECK:
			{
				slot = L2Item.SLOT_NECK;
				break;
			}
			case PAPERDOLL_RFINGER:
			{
				slot = L2Item.SLOT_R_FINGER;
				break;
			}
			case PAPERDOLL_LFINGER:
			{
				slot = L2Item.SLOT_L_FINGER;
				break;
			}
			case PAPERDOLL_HAIR:
			{
				slot = L2Item.SLOT_HAIR;
				break;
			}
			case PAPERDOLL_HAIR2:
			{
				slot = L2Item.SLOT_HAIR2;
				break;
			}
			case PAPERDOLL_HEAD:
			{
				slot = L2Item.SLOT_HEAD;
				break;
			}
			case PAPERDOLL_RHAND:
			{
				slot = L2Item.SLOT_R_HAND;
				break;
			}
			case PAPERDOLL_LHAND:
			{
				slot = L2Item.SLOT_L_HAND;
				break;
			}
			case PAPERDOLL_GLOVES:
			{
				slot = L2Item.SLOT_GLOVES;
				break;
			}
			case PAPERDOLL_CHEST:
			{
				slot = item.getItem().getBodyPart();
				break;
			}
			case PAPERDOLL_LEGS:
			{
				slot = L2Item.SLOT_LEGS;
				break;
			}
			case PAPERDOLL_CLOAK:
			{
				slot = L2Item.SLOT_BACK;
				break;
			}
			case PAPERDOLL_FEET:
			{
				slot = L2Item.SLOT_FEET;
				break;
			}
			case PAPERDOLL_LBRACELET:
			{
				slot = L2Item.SLOT_L_BRACELET;
				break;
			}
			case PAPERDOLL_RBRACELET:
			{
				slot = L2Item.SLOT_R_BRACELET;
				break;
			}
			case PAPERDOLL_DECO1:
			case PAPERDOLL_DECO2:
			case PAPERDOLL_DECO3:
			case PAPERDOLL_DECO4:
			case PAPERDOLL_DECO5:
			case PAPERDOLL_DECO6:
			{
				slot = L2Item.SLOT_DECO;
				break;
			}
			case PAPERDOLL_BELT:
			{
				slot = L2Item.SLOT_BELT;
				break;
			}
		}
		return slot;
	}
	
	/**
	 * Unequips item in body slot and returns alterations.<BR>
	 * <B>If you dont need return value use {@link Inventory#unEquipItemInBodySlot(int)} instead</B>
	 * @param slot : int designating the slot of the paperdoll
	 * @return L2ItemInstance[] : list of changes
	 */
	public L2ItemInstance[] unEquipItemInBodySlotAndRecord(int slot)
	{
		final ChangeRecorder recorder = newRecorder();
		
		try
		{
			unEquipItemInBodySlot(slot);
		}
		finally
		{
			removePaperdollListener(recorder);
		}
		return recorder.getChangedItems();
	}
	
	/**
	 * Sets item in slot of the paperdoll to null value
	 * @param pdollSlot : int designating the slot
	 * @return L2ItemInstance designating the item in slot before change
	 */
	public L2ItemInstance unEquipItemInSlot(int pdollSlot)
	{
		return setPaperdollItem(pdollSlot, null);
	}
	
	/**
	 * Unequips item in slot and returns alterations<BR>
	 * <B>If you dont need return value use {@link Inventory#unEquipItemInSlot(int)} instead</B>
	 * @param slot : int designating the slot
	 * @return L2ItemInstance[] : list of items altered
	 */
	public L2ItemInstance[] unEquipItemInSlotAndRecord(int slot)
	{
		final ChangeRecorder recorder = newRecorder();
		
		try
		{
			unEquipItemInSlot(slot);
			if (getOwner().isPlayer())
			{
				((L2PcInstance) getOwner()).refreshExpertisePenalty();
			}
		}
		finally
		{
			removePaperdollListener(recorder);
		}
		return recorder.getChangedItems();
	}
	
	/**
	 * Unequips item in slot (i.e. equips with default value)
	 * @param slot : int designating the slot
	 * @return {@link L2ItemInstance} designating the item placed in the slot
	 */
	public L2ItemInstance unEquipItemInBodySlot(int slot)
	{
		int pdollSlot = -1;
		
		switch (slot)
		{
			case L2Item.SLOT_L_EAR:
			{
				pdollSlot = PAPERDOLL_LEAR;
				break;
			}
			case L2Item.SLOT_R_EAR:
			{
				pdollSlot = PAPERDOLL_REAR;
				break;
			}
			case L2Item.SLOT_NECK:
			{
				pdollSlot = PAPERDOLL_NECK;
				break;
			}
			case L2Item.SLOT_R_FINGER:
			{
				pdollSlot = PAPERDOLL_RFINGER;
				break;
			}
			case L2Item.SLOT_L_FINGER:
			{
				pdollSlot = PAPERDOLL_LFINGER;
				break;
			}
			case L2Item.SLOT_HAIR:
			{
				pdollSlot = PAPERDOLL_HAIR;
				break;
			}
			case L2Item.SLOT_HAIR2:
			{
				pdollSlot = PAPERDOLL_HAIR2;
				break;
			}
			case L2Item.SLOT_HAIRALL:
			{
				setPaperdollItem(PAPERDOLL_HAIR, null);
				pdollSlot = PAPERDOLL_HAIR;
				break;
			}
			case L2Item.SLOT_HEAD:
			{
				pdollSlot = PAPERDOLL_HEAD;
				break;
			}
			case L2Item.SLOT_R_HAND:
			case L2Item.SLOT_LR_HAND:
			{
				pdollSlot = PAPERDOLL_RHAND;
				break;
			}
			case L2Item.SLOT_L_HAND:
			{
				pdollSlot = PAPERDOLL_LHAND;
				break;
			}
			case L2Item.SLOT_GLOVES:
			{
				pdollSlot = PAPERDOLL_GLOVES;
				break;
			}
			case L2Item.SLOT_CHEST:
			case L2Item.SLOT_ALLDRESS:
			case L2Item.SLOT_FULL_ARMOR:
			{
				pdollSlot = PAPERDOLL_CHEST;
				break;
			}
			case L2Item.SLOT_LEGS:
			{
				pdollSlot = PAPERDOLL_LEGS;
				break;
			}
			case L2Item.SLOT_BACK:
			{
				pdollSlot = PAPERDOLL_CLOAK;
				break;
			}
			case L2Item.SLOT_FEET:
			{
				pdollSlot = PAPERDOLL_FEET;
				break;
			}
			case L2Item.SLOT_UNDERWEAR:
			{
				pdollSlot = PAPERDOLL_UNDER;
				break;
			}
			case L2Item.SLOT_L_BRACELET:
			{
				pdollSlot = PAPERDOLL_LBRACELET;
				break;
			}
			case L2Item.SLOT_R_BRACELET:
			{
				pdollSlot = PAPERDOLL_RBRACELET;
				break;
			}
			case L2Item.SLOT_DECO:
			{
				pdollSlot = PAPERDOLL_DECO1;
				break;
			}
			case L2Item.SLOT_BELT:
			{
				pdollSlot = PAPERDOLL_BELT;
				break;
			}
			default:
			{
				LOGGER.info("Unhandled slot type: " + slot);
				LOGGER.info(CommonUtil.getTraceString(Thread.currentThread().getStackTrace()));
			}
		}
		if (pdollSlot >= 0)
		{
			final L2ItemInstance old = setPaperdollItem(pdollSlot, null);
			if (old != null)
			{
				if (getOwner().isPlayer())
				{
					((L2PcInstance) getOwner()).refreshExpertisePenalty();
				}
			}
			return old;
		}
		return null;
	}
	
	/**
	 * Equips item and returns list of alterations<BR>
	 * <B>If you don't need return value use {@link Inventory#equipItem(L2ItemInstance)} instead</B>
	 * @param item : L2ItemInstance corresponding to the item
	 * @return L2ItemInstance[] : list of alterations
	 */
	public L2ItemInstance[] equipItemAndRecord(L2ItemInstance item)
	{
		final ChangeRecorder recorder = newRecorder();
		
		try
		{
			equipItem(item);
		}
		finally
		{
			removePaperdollListener(recorder);
		}
		return recorder.getChangedItems();
	}
	
	/**
	 * Equips item in slot of paperdoll.
	 * @param item : L2ItemInstance designating the item and slot used.
	 */
	public void equipItem(L2ItemInstance item)
	{
		if (getOwner().isPlayer())
		{
			if (((L2PcInstance) getOwner()).getPrivateStoreType() != PrivateStoreType.NONE)
			{
				return;
			}
			
			final L2PcInstance player = (L2PcInstance) getOwner();
			
			if (!player.canOverrideCond(PcCondOverride.ITEM_CONDITIONS) && !player.isHero() && item.isHeroItem())
			{
				return;
			}
		}
		
		final int targetSlot = item.getItem().getBodyPart();
		
		// Check if player is using Formal Wear and item isn't Wedding Bouquet.
		final L2ItemInstance formal = getPaperdollItem(PAPERDOLL_CHEST);
		if ((item.getId() != 21163) && (formal != null) && (formal.getItem().getBodyPart() == L2Item.SLOT_ALLDRESS))
		{
			// only chest target can pass this
			switch (targetSlot)
			{
				case L2Item.SLOT_LR_HAND:
				case L2Item.SLOT_L_HAND:
				case L2Item.SLOT_R_HAND:
				case L2Item.SLOT_LEGS:
				case L2Item.SLOT_FEET:
				case L2Item.SLOT_GLOVES:
				case L2Item.SLOT_HEAD:
				{
					return;
				}
			}
		}
		
		switch (targetSlot)
		{
			case L2Item.SLOT_LR_HAND:
			{
				setPaperdollItem(PAPERDOLL_LHAND, null);
				setPaperdollItem(PAPERDOLL_RHAND, item);
				break;
			}
			case L2Item.SLOT_L_HAND:
			{
				final L2ItemInstance rh = getPaperdollItem(PAPERDOLL_RHAND);
				if ((rh != null) && (rh.getItem().getBodyPart() == L2Item.SLOT_LR_HAND) && !(((rh.getItemType() == WeaponType.BOW) && (item.getItemType() == EtcItemType.ARROW)) || ((rh.getItemType() == WeaponType.CROSSBOW) && (item.getItemType() == EtcItemType.BOLT)) || ((rh.getItemType() == WeaponType.FISHINGROD) && (item.getItemType() == EtcItemType.LURE))))
				{
					setPaperdollItem(PAPERDOLL_RHAND, null);
				}
				setPaperdollItem(PAPERDOLL_LHAND, item);
				break;
			}
			case L2Item.SLOT_R_HAND:
			{
				// don't care about arrows, listener will unequip them (hopefully)
				setPaperdollItem(PAPERDOLL_RHAND, item);
				break;
			}
			case L2Item.SLOT_L_EAR:
			case L2Item.SLOT_R_EAR:
			case L2Item.SLOT_LR_EAR:
			{
				if (_paperdoll[PAPERDOLL_LEAR] == null)
				{
					setPaperdollItem(PAPERDOLL_LEAR, item);
				}
				else if (_paperdoll[PAPERDOLL_REAR] == null)
				{
					setPaperdollItem(PAPERDOLL_REAR, item);
				}
				else
				{
					setPaperdollItem(PAPERDOLL_LEAR, item);
				}
				break;
			}
			case L2Item.SLOT_L_FINGER:
			case L2Item.SLOT_R_FINGER:
			case L2Item.SLOT_LR_FINGER:
			{
				if (_paperdoll[PAPERDOLL_LFINGER] == null)
				{
					setPaperdollItem(PAPERDOLL_LFINGER, item);
				}
				else if (_paperdoll[PAPERDOLL_RFINGER] == null)
				{
					setPaperdollItem(PAPERDOLL_RFINGER, item);
				}
				else
				{
					setPaperdollItem(PAPERDOLL_LFINGER, item);
				}
				break;
			}
			case L2Item.SLOT_NECK:
			{
				setPaperdollItem(PAPERDOLL_NECK, item);
				break;
			}
			case L2Item.SLOT_FULL_ARMOR:
			{
				setPaperdollItem(PAPERDOLL_LEGS, null);
				setPaperdollItem(PAPERDOLL_CHEST, item);
				break;
			}
			case L2Item.SLOT_CHEST:
			{
				setPaperdollItem(PAPERDOLL_CHEST, item);
				break;
			}
			case L2Item.SLOT_LEGS:
			{
				// handle full armor
				final L2ItemInstance chest = getPaperdollItem(PAPERDOLL_CHEST);
				if ((chest != null) && (chest.getItem().getBodyPart() == L2Item.SLOT_FULL_ARMOR))
				{
					setPaperdollItem(PAPERDOLL_CHEST, null);
				}
				setPaperdollItem(PAPERDOLL_LEGS, item);
				break;
			}
			case L2Item.SLOT_FEET:
			{
				setPaperdollItem(PAPERDOLL_FEET, item);
				break;
			}
			case L2Item.SLOT_GLOVES:
			{
				setPaperdollItem(PAPERDOLL_GLOVES, item);
				break;
			}
			case L2Item.SLOT_HEAD:
			{
				setPaperdollItem(PAPERDOLL_HEAD, item);
				break;
			}
			case L2Item.SLOT_HAIR:
			{
				final L2ItemInstance hair = getPaperdollItem(PAPERDOLL_HAIR);
				if ((hair != null) && (hair.getItem().getBodyPart() == L2Item.SLOT_HAIRALL))
				{
					setPaperdollItem(PAPERDOLL_HAIR2, null);
				}
				else
				{
					setPaperdollItem(PAPERDOLL_HAIR, null);
				}
				setPaperdollItem(PAPERDOLL_HAIR, item);
				break;
			}
			case L2Item.SLOT_HAIR2:
			{
				final L2ItemInstance hair2 = getPaperdollItem(PAPERDOLL_HAIR);
				if ((hair2 != null) && (hair2.getItem().getBodyPart() == L2Item.SLOT_HAIRALL))
				{
					setPaperdollItem(PAPERDOLL_HAIR, null);
				}
				else
				{
					setPaperdollItem(PAPERDOLL_HAIR2, null);
				}
				setPaperdollItem(PAPERDOLL_HAIR2, item);
				break;
			}
			case L2Item.SLOT_HAIRALL:
			{
				setPaperdollItem(PAPERDOLL_HAIR2, null);
				setPaperdollItem(PAPERDOLL_HAIR, item);
				break;
			}
			case L2Item.SLOT_UNDERWEAR:
			{
				setPaperdollItem(PAPERDOLL_UNDER, item);
				break;
			}
			case L2Item.SLOT_BACK:
			{
				setPaperdollItem(PAPERDOLL_CLOAK, item);
				break;
			}
			case L2Item.SLOT_L_BRACELET:
			{
				setPaperdollItem(PAPERDOLL_LBRACELET, item);
				break;
			}
			case L2Item.SLOT_R_BRACELET:
			{
				setPaperdollItem(PAPERDOLL_RBRACELET, item);
				break;
			}
			case L2Item.SLOT_DECO:
			{
				equipTalisman(item);
				break;
			}
			case L2Item.SLOT_BELT:
			{
				setPaperdollItem(PAPERDOLL_BELT, item);
				break;
			}
			case L2Item.SLOT_ALLDRESS:
			{
				// formal dress
				setPaperdollItem(PAPERDOLL_LEGS, null);
				setPaperdollItem(PAPERDOLL_LHAND, null);
				setPaperdollItem(PAPERDOLL_RHAND, null);
				setPaperdollItem(PAPERDOLL_HEAD, null);
				setPaperdollItem(PAPERDOLL_FEET, null);
				setPaperdollItem(PAPERDOLL_GLOVES, null);
				setPaperdollItem(PAPERDOLL_CHEST, item);
				break;
			}
			default:
			{
				LOGGER.warning("Unknown body slot " + targetSlot + " for Item ID:" + item.getId());
			}
		}
	}
	
	/**
	 * Refresh the weight of equipment loaded
	 */
	@Override
	protected void refreshWeight()
	{
		long weight = 0;
		
		for (L2ItemInstance item : _items)
		{
			if ((item != null) && (item.getItem() != null))
			{
				weight += item.getItem().getWeight() * item.getCount();
			}
		}
		_totalWeight = (int) Math.min(weight, Integer.MAX_VALUE);
	}
	
	/**
	 * @return the totalWeight.
	 */
	public int getTotalWeight()
	{
		return _totalWeight;
	}
	
	/**
	 * Return the L2ItemInstance of the arrows needed for this bow.
	 * @param bow : L2Item designating the bow
	 * @return L2ItemInstance pointing out arrows for bow
	 */
	public L2ItemInstance findArrowForBow(L2Item bow)
	{
		if (bow == null)
		{
			return null;
		}
		
		L2ItemInstance arrow = null;
		
		for (L2ItemInstance item : getItems())
		{
			if (item.isEtcItem() && (item.getItem().getCrystalTypePlus() == bow.getCrystalTypePlus()) && (item.getEtcItem().getItemType() == EtcItemType.ARROW))
			{
				arrow = item;
				break;
			}
		}
		
		// Get the L2ItemInstance corresponding to the item identifier and return it
		return arrow;
	}
	
	/**
	 * Return the L2ItemInstance of the bolts needed for this crossbow.
	 * @param crossbow : L2Item designating the crossbow
	 * @return L2ItemInstance pointing out bolts for crossbow
	 */
	public L2ItemInstance findBoltForCrossBow(L2Item crossbow)
	{
		L2ItemInstance bolt = null;
		
		for (L2ItemInstance item : getItems())
		{
			if (item.isEtcItem() && (item.getItem().getCrystalTypePlus() == crossbow.getCrystalTypePlus()) && (item.getEtcItem().getItemType() == EtcItemType.BOLT))
			{
				bolt = item;
				break;
			}
		}
		
		// Get the L2ItemInstance corresponding to the item identifier and return it
		return bolt;
	}
	
	/**
	 * Get back items in inventory from database
	 */
	@Override
	public void restore()
	{
		try (Connection con = DatabaseFactory.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT object_id, item_id, count, enchant_level, loc, loc_data, custom_type1, custom_type2, mana_left, time FROM items WHERE owner_id=? AND (loc=? OR loc=?) ORDER BY loc_data"))
		{
			statement.setInt(1, getOwnerId());
			statement.setString(2, getBaseLocation().name());
			statement.setString(3, getEquipLocation().name());
			try (ResultSet inv = statement.executeQuery())
			{
				L2ItemInstance item;
				while (inv.next())
				{
					item = L2ItemInstance.restoreFromDb(getOwnerId(), inv);
					if (item == null)
					{
						continue;
					}
					
					if (getOwner().isPlayer())
					{
						final L2PcInstance player = (L2PcInstance) getOwner();
						
						if (!player.canOverrideCond(PcCondOverride.ITEM_CONDITIONS) && !player.isHero() && item.isHeroItem())
						{
							item.setItemLocation(ItemLocation.INVENTORY);
						}
					}
					
					L2World.getInstance().addObject(item);
					
					// If stackable item is found in inventory just add to current quantity
					if (item.isStackable() && (getItemByItemId(item.getId()) != null))
					{
						addItem("Restore", item, getOwner().getActingPlayer(), null);
					}
					else
					{
						addItem(item);
					}
				}
			}
			refreshWeight();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Could not restore inventory: " + e.getMessage(), e);
		}
	}
	
	public int getTalismanSlots()
	{
		return getOwner().getActingPlayer().getStat().getTalismanSlots();
	}
	
	private void equipTalisman(L2ItemInstance item)
	{
		if (getTalismanSlots() == 0)
		{
			return;
		}
		
		// find same (or incompatible) talisman type
		for (int i = PAPERDOLL_DECO1; i < (PAPERDOLL_DECO1 + getTalismanSlots()); i++)
		{
			if (_paperdoll[i] != null)
			{
				if (getPaperdollItemId(i) == item.getId())
				{
					// overwtite
					setPaperdollItem(i, item);
					return;
				}
			}
		}
		
		// no free slot found - put on first free
		for (int i = PAPERDOLL_DECO1; i < (PAPERDOLL_DECO1 + getTalismanSlots()); i++)
		{
			if (_paperdoll[i] == null)
			{
				setPaperdollItem(i, item);
				return;
			}
		}
		
		// no free slots - put on first
		setPaperdollItem(PAPERDOLL_DECO1, item);
	}
	
	public boolean canEquipCloak()
	{
		return getOwner().getActingPlayer().getStat().canEquipCloak();
	}
	
	/**
	 * Re-notify to paperdoll listeners every equipped item
	 */
	public void reloadEquippedItems()
	{
		int slot;
		
		for (L2ItemInstance item : _paperdoll)
		{
			if (item == null)
			{
				continue;
			}
			
			slot = item.getLocationSlot();
			
			for (PaperdollListener listener : _paperdollListeners)
			{
				if (listener == null)
				{
					continue;
				}
				
				listener.notifyUnequiped(slot, item, this);
				listener.notifyEquiped(slot, item, this);
			}
		}
	}
}

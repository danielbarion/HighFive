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
package com.l2jmobius.gameserver.network.clientpackets;

import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.gameserver.data.xml.impl.EnchantItemData;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.L2Item;
import com.l2jmobius.gameserver.model.items.enchant.EnchantResultType;
import com.l2jmobius.gameserver.model.items.enchant.EnchantScroll;
import com.l2jmobius.gameserver.model.items.enchant.EnchantSupportItem;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.skills.CommonSkill;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.L2GameClient;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.EnchantResult;
import com.l2jmobius.gameserver.network.serverpackets.InventoryUpdate;
import com.l2jmobius.gameserver.network.serverpackets.ItemList;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jmobius.gameserver.network.serverpackets.StatusUpdate;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Util;

public final class RequestEnchantItem implements IClientIncomingPacket
{
	protected static final Logger LOGGER_ENCHANT = Logger.getLogger("enchant.items");
	
	private int _objectId;
	private int _supportId;
	
	@Override
	public boolean read(L2GameClient client, PacketReader packet)
	{
		_objectId = packet.readD();
		_supportId = packet.readD();
		return true;
	}
	
	@Override
	public void run(L2GameClient client)
	{
		final L2PcInstance activeChar = client.getActiveChar();
		if ((activeChar == null) || (_objectId == 0))
		{
			return;
		}
		
		if (!activeChar.isOnline() || client.isDetached())
		{
			activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
			return;
		}
		
		if (activeChar.isProcessingTransaction() || activeChar.isInStoreMode())
		{
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_ENCHANT_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
			activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
			return;
		}
		
		L2ItemInstance item = activeChar.getInventory().getItemByObjectId(_objectId);
		L2ItemInstance scroll = activeChar.getInventory().getItemByObjectId(activeChar.getActiveEnchantItemId());
		L2ItemInstance support = activeChar.getInventory().getItemByObjectId(activeChar.getActiveEnchantSupportItemId());
		
		if ((item == null) || (scroll == null))
		{
			activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
			return;
		}
		
		// template for scroll
		final EnchantScroll scrollTemplate = EnchantItemData.getInstance().getEnchantScroll(scroll);
		
		// scroll not found in list
		if (scrollTemplate == null)
		{
			return;
		}
		
		// template for support item, if exist
		EnchantSupportItem supportTemplate = null;
		if (support != null)
		{
			if (support.getObjectId() != _supportId)
			{
				activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
				return;
			}
			supportTemplate = EnchantItemData.getInstance().getSupportItem(support);
		}
		
		// first validation check - also over enchant check
		if (!scrollTemplate.isValid(item, supportTemplate) || (Config.DISABLE_OVER_ENCHANTING && (item.getEnchantLevel() == scrollTemplate.getMaxEnchantLevel())))
		{
			activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
			activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
			activeChar.sendPacket(new EnchantResult(2, 0, 0));
			return;
		}
		
		// fast auto-enchant cheat check
		if ((activeChar.getActiveEnchantTimestamp() == 0) || ((System.currentTimeMillis() - activeChar.getActiveEnchantTimestamp()) < 2000))
		{
			Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " use autoenchant program ", Config.DEFAULT_PUNISH);
			activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
			activeChar.sendPacket(new EnchantResult(2, 0, 0));
			return;
		}
		
		// attempting to destroy scroll
		scroll = activeChar.getInventory().destroyItem("Enchant", scroll.getObjectId(), 1, activeChar, item);
		if (scroll == null)
		{
			activeChar.sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT_2);
			Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to enchant with a scroll he doesn't have", Config.DEFAULT_PUNISH);
			activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
			activeChar.sendPacket(new EnchantResult(2, 0, 0));
			return;
		}
		
		// attempting to destroy support if exist
		if (support != null)
		{
			support = activeChar.getInventory().destroyItem("Enchant", support.getObjectId(), 1, activeChar, item);
			if (support == null)
			{
				activeChar.sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT_2);
				Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to enchant with a support item he doesn't have", Config.DEFAULT_PUNISH);
				activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
				activeChar.sendPacket(new EnchantResult(2, 0, 0));
				return;
			}
		}
		
		final InventoryUpdate iu = new InventoryUpdate();
		synchronized (item)
		{
			// last validation check
			if ((item.getOwnerId() != activeChar.getObjectId()) || (item.isEnchantable() == 0))
			{
				activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
				activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
				activeChar.sendPacket(new EnchantResult(2, 0, 0));
				return;
			}
			
			final EnchantResultType resultType = scrollTemplate.calculateSuccess(activeChar, item, supportTemplate);
			switch (resultType)
			{
				case ERROR:
				{
					activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
					activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
					activeChar.sendPacket(new EnchantResult(2, 0, 0));
					break;
				}
				case SUCCESS:
				{
					Skill enchant4Skill = null;
					final L2Item it = item.getItem();
					// Increase enchant level only if scroll's base template has chance, some armors can success over +20 but they shouldn't have increased.
					if (scrollTemplate.getChance(activeChar, item) > 0)
					{
						item.setEnchantLevel(item.getEnchantLevel() + 1);
						item.updateDatabase();
					}
					activeChar.sendPacket(new EnchantResult(0, 0, 0));
					
					if (Config.LOG_ITEM_ENCHANTS)
					{
						if (item.getEnchantLevel() > 0)
						{
							if (support == null)
							{
								LOGGER_ENCHANT.info("Success, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
							}
							else
							{
								LOGGER_ENCHANT.info("Success, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
							}
						}
						else if (support == null)
						{
							LOGGER_ENCHANT.info("Success, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
						}
						else
						{
							LOGGER_ENCHANT.info("Success, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
						}
					}
					
					// announce the success
					final int minEnchantAnnounce = item.isArmor() ? 6 : 7;
					final int maxEnchantAnnounce = item.isArmor() ? 0 : 15;
					if ((item.getEnchantLevel() == minEnchantAnnounce) || (item.getEnchantLevel() == maxEnchantAnnounce))
					{
						final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_SUCCESSFULLY_ENCHANTED_A_S2_S3);
						sm.addString(activeChar.getName());
						sm.addInt(item.getEnchantLevel());
						sm.addItemName(item);
						activeChar.broadcastPacket(sm);
						
						final Skill skill = CommonSkill.FIREWORK.getSkill();
						if (skill != null)
						{
							activeChar.broadcastPacket(new MagicSkillUse(activeChar, activeChar, skill.getId(), skill.getLevel(), skill.getHitTime(), skill.getReuseDelay()));
						}
					}
					
					if ((item.isArmor()) && (item.getEnchantLevel() == 4) && item.isEquipped())
					{
						enchant4Skill = it.getEnchant4Skill();
						if (enchant4Skill != null)
						{
							// add skills bestowed from +4 armor
							activeChar.addSkill(enchant4Skill, false);
							activeChar.sendSkillList();
						}
					}
					break;
				}
				case FAILURE:
				{
					if (scrollTemplate.isSafe())
					{
						// safe enchant - remain old value
						activeChar.sendPacket(SystemMessageId.ENCHANT_FAILED_THE_ENCHANT_LEVEL_FOR_THE_CORRESPONDING_ITEM_WILL_BE_EXACTLY_RETAINED);
						activeChar.sendPacket(new EnchantResult(5, 0, 0));
						
						if (Config.LOG_ITEM_ENCHANTS)
						{
							if (item.getEnchantLevel() > 0)
							{
								if (support == null)
								{
									LOGGER_ENCHANT.info("Safe Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
								}
								else
								{
									LOGGER_ENCHANT.info("Safe Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
								}
							}
							else if (support == null)
							{
								LOGGER_ENCHANT.info("Safe Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
							}
							else
							{
								LOGGER_ENCHANT.info("Safe Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
							}
						}
					}
					else
					{
						// unequip item on enchant failure to avoid item skills stack
						if (item.isEquipped())
						{
							if (item.getEnchantLevel() > 0)
							{
								final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_EQUIPMENT_S1_S2_HAS_BEEN_REMOVED);
								sm.addInt(item.getEnchantLevel());
								sm.addItemName(item);
								activeChar.sendPacket(sm);
							}
							else
							{
								final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_DISARMED);
								sm.addItemName(item);
								activeChar.sendPacket(sm);
							}
							
							final L2ItemInstance[] unequiped = activeChar.getInventory().unEquipItemInSlotAndRecord(item.getLocationSlot());
							for (L2ItemInstance itm : unequiped)
							{
								iu.addModifiedItem(itm);
							}
							
							activeChar.sendPacket(iu);
							activeChar.broadcastUserInfo();
						}
						
						if (scrollTemplate.isBlessed())
						{
							// blessed enchant - clear enchant value
							activeChar.sendPacket(SystemMessageId.THE_BLESSED_ENCHANT_FAILED_THE_ENCHANT_VALUE_OF_THE_ITEM_BECAME_0);
							
							item.setEnchantLevel(0);
							item.updateDatabase();
							activeChar.sendPacket(new EnchantResult(3, 0, 0));
							
							if (Config.LOG_ITEM_ENCHANTS)
							{
								if (item.getEnchantLevel() > 0)
								{
									if (support == null)
									{
										LOGGER_ENCHANT.info("Blessed Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
									}
									else
									{
										LOGGER_ENCHANT.info("Blessed Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
									}
								}
								else if (support == null)
								{
									LOGGER_ENCHANT.info("Blessed Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
								}
								else
								{
									LOGGER_ENCHANT.info("Blessed Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
								}
							}
						}
						else
						{
							// enchant failed, destroy item
							if (activeChar.getInventory().destroyItem("Enchant", item, activeChar, null) == null)
							{
								// unable to destroy item, cheater ?
								Util.handleIllegalPlayerAction(activeChar, "Unable to delete item on enchant failure from player " + activeChar.getName() + ", possible cheater !", Config.DEFAULT_PUNISH);
								activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
								activeChar.sendPacket(new EnchantResult(2, 0, 0));
								
								if (Config.LOG_ITEM_ENCHANTS)
								{
									if (item.getEnchantLevel() > 0)
									{
										if (support == null)
										{
											LOGGER_ENCHANT.info("Unable to destroy, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
										}
										else
										{
											LOGGER_ENCHANT.info("Unable to destroy, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
										}
									}
									else if (support == null)
									{
										LOGGER_ENCHANT.info("Unable to destroy, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
									}
									else
									{
										LOGGER_ENCHANT.info("Unable to destroy, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
									}
								}
								return;
							}
							
							L2World.getInstance().removeObject(item);
							
							final int crystalId = item.getItem().getCrystalItemId();
							if ((crystalId != 0) && item.getItem().isCrystallizable())
							{
								int count = item.getCrystalCount() - ((item.getItem().getCrystalCount() + 1) / 2);
								count = count < 1 ? 1 : count;
								activeChar.getInventory().addItem("Enchant", crystalId, count, activeChar, item);
								
								final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S2_S1_S);
								sm.addItemName(crystalId);
								sm.addLong(count);
								activeChar.sendPacket(sm);
								activeChar.sendPacket(new EnchantResult(1, crystalId, count));
							}
							else
							{
								activeChar.sendPacket(new EnchantResult(4, 0, 0));
							}
							
							if (Config.LOG_ITEM_ENCHANTS)
							{
								if (item.getEnchantLevel() > 0)
								{
									if (support == null)
									{
										LOGGER_ENCHANT.info("Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
									}
									else
									{
										LOGGER_ENCHANT.info("Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", +" + item.getEnchantLevel() + " " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
									}
								}
								else if (support == null)
								{
									LOGGER_ENCHANT.info("Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "]");
								}
								else
								{
									LOGGER_ENCHANT.info("Fail, Character:" + activeChar.getName() + " [" + activeChar.getObjectId() + "] Account:" + activeChar.getAccountName() + " IP:" + activeChar.getIPAddress() + ", " + item.getName() + "(" + item.getCount() + ") [" + item.getObjectId() + "], " + scroll.getName() + "(" + scroll.getCount() + ") [" + scroll.getObjectId() + "], " + support.getName() + "(" + support.getCount() + ") [" + support.getObjectId() + "]");
								}
							}
						}
					}
					break;
				}
			}
			
			final StatusUpdate su = new StatusUpdate(activeChar);
			su.addAttribute(StatusUpdate.CUR_LOAD, activeChar.getCurrentLoad());
			activeChar.sendPacket(su);
			if (!Config.FORCE_INVENTORY_UPDATE)
			{
				if (scroll.getCount() == 0)
				{
					iu.addRemovedItem(scroll);
				}
				else
				{
					iu.addModifiedItem(scroll);
				}
				
				if (item.getCount() == 0)
				{
					iu.addRemovedItem(item);
				}
				else
				{
					iu.addModifiedItem(item);
				}
				
				if (support != null)
				{
					if (support.getCount() == 0)
					{
						iu.addRemovedItem(support);
					}
					else
					{
						iu.addModifiedItem(support);
					}
				}
				
				activeChar.sendPacket(iu);
			}
			else
			{
				activeChar.sendPacket(new ItemList(activeChar, true));
			}
			
			activeChar.broadcastUserInfo();
			activeChar.setActiveEnchantItemId(L2PcInstance.ID_NONE);
		}
	}
}

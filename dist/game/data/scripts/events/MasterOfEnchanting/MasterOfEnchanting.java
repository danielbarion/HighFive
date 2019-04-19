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
package events.MasterOfEnchanting;

import java.util.Date;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.LongTimeEvent;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

/**
 * Master of Enchanting event AI.
 * @author Gnacik
 */
public final class MasterOfEnchanting extends LongTimeEvent
{
	// NPC
	private static final int MASTER_YOGI = 32599;
	// Items
	private static final int MASTER_YOGI_STAFF = 13539;
	private static final int MASTER_YOGI_SCROLL = 13540;
	// Misc
	private static final int STAFF_PRICE = 1000000;
	private static final int SCROLL_24_PRICE = 5000000;
	private static final int SCROLL_24_TIME = 6;
	private static final int SCROLL_1_PRICE = 500000;
	private static final int SCROLL_10_PRICE = 5000000;
	
	private static final int[] HAT_SHADOW_REWARD =
	{
		13074,
		13075,
		13076
	};
	private static final int[] HAT_EVENT_REWARD =
	{
		13518,
		13519,
		13522
	};
	private static final int[] CRYSTAL_REWARD =
	{
		9570,
		9571,
		9572
	};
	
	@SuppressWarnings("deprecation")
	private static final Date EVENT_START = new Date(2011, 7, 1);
	
	private MasterOfEnchanting()
	{
		addStartNpc(MASTER_YOGI);
		addFirstTalkId(MASTER_YOGI);
		addTalkId(MASTER_YOGI);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		if (event.equalsIgnoreCase("buy_staff"))
		{
			if (!hasQuestItems(player, MASTER_YOGI_STAFF) && (getQuestItemsCount(player, Inventory.ADENA_ID) > STAFF_PRICE))
			{
				takeItems(player, Inventory.ADENA_ID, STAFF_PRICE);
				giveItems(player, MASTER_YOGI_STAFF, 1);
				htmltext = "32599-staffbuyed.htm";
			}
			else
			{
				htmltext = "32599-staffcant.htm";
			}
		}
		else if (event.equalsIgnoreCase("buy_scroll_24"))
		{
			final long curTime = System.currentTimeMillis();
			final String value = player.getVariables().getString("MasterOfEnchanting");
			final long reuse = value == "" ? 0 : Long.parseLong(value);
			if (player.getCreateDate().after(EVENT_START))
			{
				return "32599-bidth.htm";
			}
			
			if (curTime > reuse)
			{
				if (getQuestItemsCount(player, Inventory.ADENA_ID) > SCROLL_24_PRICE)
				{
					takeItems(player, Inventory.ADENA_ID, SCROLL_24_PRICE);
					giveItems(player, MASTER_YOGI_SCROLL, 24);
					player.getVariables().set("MasterOfEnchanting", Long.toString(System.currentTimeMillis() + (SCROLL_24_TIME * 3600000)));
					htmltext = "32599-scroll24.htm";
				}
				else
				{
					htmltext = "32599-s24-no.htm";
				}
			}
			else
			{
				final long remainingTime = (reuse - curTime) / 1000;
				final int hours = (int) remainingTime / 3600;
				final int minutes = ((int) remainingTime % 3600) / 60;
				if (hours > 0)
				{
					final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S1_HOURS_S_AND_S2_MINUTE_S_REMAINING_UNTIL_THE_ITEM_CAN_BE_PURCHASED_AGAIN);
					sm.addInt(hours);
					sm.addInt(minutes);
					player.sendPacket(sm);
					htmltext = "32599-scroll24.htm";
				}
				else if (minutes > 0)
				{
					final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S1_MINUTE_S_REMAINING_UNTIL_THE_ITEM_CAN_BE_PURCHASED_AGAIN);
					sm.addInt(minutes);
					player.sendPacket(sm);
					htmltext = "32599-scroll24.htm";
				}
				// Little glitch. There is no SystemMessage with seconds only.
				// If time is less than 1 minute player can buy scrolls
				else if (getQuestItemsCount(player, Inventory.ADENA_ID) > SCROLL_24_PRICE)
				{
					takeItems(player, Inventory.ADENA_ID, SCROLL_24_PRICE);
					giveItems(player, MASTER_YOGI_SCROLL, 24);
					player.getVariables().set("MasterOfEnchanting", Long.toString(System.currentTimeMillis() + (SCROLL_24_TIME * 3600000)));
					htmltext = "32599-scroll24.htm";
				}
				else
				{
					htmltext = "32599-s24-no.htm";
				}
			}
		}
		else if (event.equalsIgnoreCase("buy_scroll_1"))
		{
			if (getQuestItemsCount(player, Inventory.ADENA_ID) > SCROLL_1_PRICE)
			{
				takeItems(player, Inventory.ADENA_ID, SCROLL_1_PRICE);
				giveItems(player, MASTER_YOGI_SCROLL, 1);
				htmltext = "32599-scroll-ok.htm";
			}
			else
			{
				htmltext = "32599-s1-no.htm";
			}
		}
		else if (event.equalsIgnoreCase("buy_scroll_10"))
		{
			if (getQuestItemsCount(player, Inventory.ADENA_ID) > SCROLL_10_PRICE)
			{
				takeItems(player, Inventory.ADENA_ID, SCROLL_10_PRICE);
				giveItems(player, MASTER_YOGI_SCROLL, 10);
				htmltext = "32599-scroll-ok.htm";
			}
			else
			{
				htmltext = "32599-s10-no.htm";
			}
		}
		else if (event.equalsIgnoreCase("receive_reward"))
		{
			if ((getItemEquipped(player, Inventory.PAPERDOLL_RHAND) == MASTER_YOGI_STAFF) && (getEnchantLevel(player, MASTER_YOGI_STAFF) > 3))
			{
				switch (getEnchantLevel(player, MASTER_YOGI_STAFF))
				{
					case 4:
					{
						giveItems(player, 6406, 1); // Firework
						break;
					}
					case 5:
					{
						giveItems(player, 6406, 2); // Firework
						giveItems(player, 6407, 1); // Large Firework
						break;
					}
					case 6:
					{
						giveItems(player, 6406, 3); // Firework
						giveItems(player, 6407, 2); // Large Firework
						break;
					}
					case 7:
					{
						giveItems(player, HAT_SHADOW_REWARD[getRandom(3)], 1);
						break;
					}
					case 8:
					{
						giveItems(player, 955, 1); // Scroll: Enchant Weapon (D)
						break;
					}
					case 9:
					{
						giveItems(player, 955, 1); // Scroll: Enchant Weapon (D)
						giveItems(player, 956, 1); // Scroll: Enchant Armor (D)
						break;
					}
					case 10:
					{
						giveItems(player, 951, 1); // Scroll: Enchant Weapon (C)
						break;
					}
					case 11:
					{
						giveItems(player, 951, 1); // Scroll: Enchant Weapon (C)
						giveItems(player, 952, 1); // Scroll: Enchant Armor (C)
						break;
					}
					case 12:
					{
						giveItems(player, 948, 1); // Scroll: Enchant Armor (B)
						break;
					}
					case 13:
					{
						giveItems(player, 729, 1); // Scroll: Enchant Weapon (A)
						break;
					}
					case 14:
					{
						giveItems(player, HAT_EVENT_REWARD[getRandom(3)], 1);
						break;
					}
					case 15:
					{
						giveItems(player, 13992, 1); // Grade S Accessory Chest (Event)
						break;
					}
					case 16:
					{
						giveItems(player, 8762, 1); // Top-Grade Life Stone: level 76
						break;
					}
					case 17:
					{
						giveItems(player, 959, 1); // Scroll: Enchant Weapon (S)
						break;
					}
					case 18:
					{
						giveItems(player, 13991, 1); // Grade S Armor Chest (Event)
						break;
					}
					case 19:
					{
						giveItems(player, 13990, 1); // Grade S Weapon Chest (Event)
						break;
					}
					case 20:
					{
						giveItems(player, CRYSTAL_REWARD[getRandom(3)], 1); // Red/Blue/Green Soul Crystal - Stage 14
						break;
					}
					case 21:
					{
						giveItems(player, 8762, 1); // Top-Grade Life Stone: level 76
						giveItems(player, 8752, 1); // High-Grade Life Stone: level 76
						giveItems(player, CRYSTAL_REWARD[getRandom(3)], 1); // Red/Blue/Green Soul Crystal - Stage 14
						break;
					}
					case 22:
					{
						giveItems(player, 13989, 1); // S80 Grade Armor Chest (Event)
						break;
					}
					case 23:
					{
						giveItems(player, 13988, 1); // S80 Grade Weapon Chest (Event)
						break;
					}
					default:
					{
						if (getEnchantLevel(player, MASTER_YOGI_STAFF) > 23)
						{
							giveItems(player, 13988, 1); // S80 Grade Weapon Chest (Event)
						}
					}
				}
				takeItems(player, MASTER_YOGI_STAFF, 1);
				htmltext = "32599-rewardok.htm";
			}
			else
			{
				htmltext = "32599-rewardnostaff.htm";
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		return npc.getId() + ".htm";
	}
	
	public static void main(String[] args)
	{
		new MasterOfEnchanting();
	}
}

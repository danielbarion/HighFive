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
package handlers.bypasshandlers;

import java.text.DateFormat;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.handler.IBypassHandler;
import com.l2jmobius.gameserver.idfactory.IdFactory;
import com.l2jmobius.gameserver.instancemanager.games.Lottery;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import com.l2jmobius.gameserver.network.serverpackets.InventoryUpdate;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class Loto implements IBypassHandler
{
	private static final String[] COMMANDS =
	{
		"Loto"
	};
	
	@Override
	public boolean useBypass(String command, L2PcInstance activeChar, L2Character target)
	{
		if (!target.isNpc())
		{
			return false;
		}
		
		int val = 0;
		try
		{
			val = Integer.parseInt(command.substring(5));
		}
		catch (IndexOutOfBoundsException ioobe)
		{
		}
		catch (NumberFormatException nfe)
		{
		}
		if (val == 0)
		{
			// new loto ticket
			for (int i = 0; i < 5; i++)
			{
				activeChar.setLoto(i, 0);
			}
		}
		showLotoWindow(activeChar, (L2Npc) target, val);
		
		return false;
	}
	
	/**
	 * Open a Loto window on client with the text of the L2NpcInstance.<BR>
	 * <BR>
	 * <B><U> Actions</U> :</B><BR>
	 * <BR>
	 * <li>Get the text of the selected HTML file in function of the npcId and of the page number</li>
	 * <li>Send a Server->Client NpcHtmlMessage containing the text of the L2NpcInstance to the L2PcInstance</li>
	 * <li>Send a Server->Client ActionFailed to the L2PcInstance in order to avoid that the client wait another packet</li><BR>
	 * @param player The L2PcInstance that talk with the L2NpcInstance
	 * @param npc L2Npc loto instance
	 * @param val The number of the page of the L2NpcInstance to display
	 */
	// 0 - first buy lottery ticket window
	// 1-20 - buttons
	// 21 - second buy lottery ticket window
	// 22 - selected ticket with 5 numbers
	// 23 - current lottery jackpot
	// 24 - Previous winning numbers/Prize claim
	// >24 - check lottery ticket by item object id
	public static void showLotoWindow(L2PcInstance player, L2Npc npc, int val)
	{
		final int npcId = npc.getTemplate().getId();
		String filename;
		SystemMessage sm;
		final NpcHtmlMessage html = new NpcHtmlMessage(npc.getObjectId());
		
		if (val == 0) // 0 - first buy lottery ticket window
		{
			filename = (npc.getHtmlPath(npcId, 1));
			html.setFile(player, filename);
		}
		else if ((val >= 1) && (val <= 21)) // 1-20 - buttons, 21 - second buy lottery ticket window
		{
			if (!Lottery.getInstance().isStarted())
			{
				// tickets can't be sold
				player.sendPacket(SystemMessageId.LOTTERY_TICKETS_ARE_NOT_CURRENTLY_BEING_SOLD);
				return;
			}
			if (!Lottery.getInstance().isSellableTickets())
			{
				// tickets can't be sold
				player.sendPacket(SystemMessageId.TICKETS_FOR_THE_CURRENT_LOTTERY_ARE_NO_LONGER_AVAILABLE);
				return;
			}
			
			filename = (npc.getHtmlPath(npcId, 5));
			html.setFile(player, filename);
			
			int count = 0;
			int found = 0;
			// counting buttons and unsetting button if found
			for (int i = 0; i < 5; i++)
			{
				if (player.getLoto(i) == val)
				{
					// unsetting button
					player.setLoto(i, 0);
					found = 1;
				}
				else if (player.getLoto(i) > 0)
				{
					count++;
				}
			}
			
			// if not rearched limit 5 and not unseted value
			if ((count < 5) && (found == 0) && (val <= 20))
			{
				for (int i = 0; i < 5; i++)
				{
					if (player.getLoto(i) == 0)
					{
						player.setLoto(i, val);
						break;
					}
				}
			}
			
			// setting pusshed buttons
			count = 0;
			for (int i = 0; i < 5; i++)
			{
				if (player.getLoto(i) > 0)
				{
					count++;
					String button = String.valueOf(player.getLoto(i));
					if (player.getLoto(i) < 10)
					{
						button = "0" + button;
					}
					final String search = "fore=\"L2UI.lottoNum" + button + "\" back=\"L2UI.lottoNum" + button + "a_check\"";
					final String replace = "fore=\"L2UI.lottoNum" + button + "a_check\" back=\"L2UI.lottoNum" + button + "\"";
					html.replace(search, replace);
				}
			}
			
			if (count == 5)
			{
				final String search = "0\">Return";
				final String replace = "22\">Your lucky numbers have been selected above.";
				html.replace(search, replace);
			}
		}
		else if (val == 22) // 22 - selected ticket with 5 numbers
		{
			if (!Lottery.getInstance().isStarted())
			{
				// tickets can't be sold
				player.sendPacket(SystemMessageId.LOTTERY_TICKETS_ARE_NOT_CURRENTLY_BEING_SOLD);
				return;
			}
			if (!Lottery.getInstance().isSellableTickets())
			{
				// tickets can't be sold
				player.sendPacket(SystemMessageId.TICKETS_FOR_THE_CURRENT_LOTTERY_ARE_NO_LONGER_AVAILABLE);
				return;
			}
			
			final long price = Config.ALT_LOTTERY_TICKET_PRICE;
			final int lotonumber = Lottery.getInstance().getId();
			int enchant = 0;
			int type2 = 0;
			
			for (int i = 0; i < 5; i++)
			{
				if (player.getLoto(i) == 0)
				{
					return;
				}
				
				if (player.getLoto(i) < 17)
				{
					enchant += Math.pow(2, player.getLoto(i) - 1);
				}
				else
				{
					type2 += Math.pow(2, player.getLoto(i) - 17);
				}
			}
			if (player.getAdena() < price)
			{
				sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				player.sendPacket(sm);
				return;
			}
			if (!player.reduceAdena("Loto", price, npc, true))
			{
				return;
			}
			Lottery.getInstance().increasePrize(price);
			
			sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S1);
			sm.addItemName(4442);
			player.sendPacket(sm);
			
			final L2ItemInstance item = new L2ItemInstance(IdFactory.getInstance().getNextId(), 4442);
			item.setCount(1);
			item.setCustomType1(lotonumber);
			item.setEnchantLevel(enchant);
			item.setCustomType2(type2);
			player.getInventory().addItem("Loto", item, player, npc);
			
			final InventoryUpdate iu = new InventoryUpdate();
			iu.addItem(item);
			final L2ItemInstance adenaupdate = player.getInventory().getItemByItemId(57);
			iu.addModifiedItem(adenaupdate);
			player.sendPacket(iu);
			
			filename = (npc.getHtmlPath(npcId, 6));
			html.setFile(player, filename);
		}
		else if (val == 23) // 23 - current lottery jackpot
		{
			filename = (npc.getHtmlPath(npcId, 3));
			html.setFile(player, filename);
		}
		else if (val == 24) // 24 - Previous winning numbers/Prize claim
		{
			filename = (npc.getHtmlPath(npcId, 4));
			html.setFile(player, filename);
			
			final int lotonumber = Lottery.getInstance().getId();
			String message = "";
			for (L2ItemInstance item : player.getInventory().getItems())
			{
				if (item == null)
				{
					continue;
				}
				if ((item.getId() == 4442) && (item.getCustomType1() < lotonumber))
				{
					message = message + "<a action=\"bypass -h npc_%objectId%_Loto " + item.getObjectId() + "\">" + item.getCustomType1() + " Event Number ";
					final int[] numbers = Lottery.getInstance().decodeNumbers(item.getEnchantLevel(), item.getCustomType2());
					for (int i = 0; i < 5; i++)
					{
						message += numbers[i] + " ";
					}
					final long[] check = Lottery.getInstance().checkTicket(item);
					if (check[0] > 0)
					{
						switch ((int) check[0])
						{
							case 1:
							{
								message += "- 1st Prize";
								break;
							}
							case 2:
							{
								message += "- 2nd Prize";
								break;
							}
							case 3:
							{
								message += "- 3th Prize";
								break;
							}
							case 4:
							{
								message += "- 4th Prize";
								break;
							}
						}
						message += " " + check[1] + "a.";
					}
					message += "</a><br>";
				}
			}
			if (message.isEmpty())
			{
				message += "There has been no winning lottery ticket.<br>";
			}
			html.replace("%result%", message);
		}
		else if (val == 25) // 25 - lottery instructions
		{
			filename = (npc.getHtmlPath(npcId, 2));
			html.setFile(player, filename);
		}
		else if (val > 25) // >25 - check lottery ticket by item object id
		{
			final int lotonumber = Lottery.getInstance().getId();
			final L2ItemInstance item = player.getInventory().getItemByObjectId(val);
			if ((item == null) || (item.getId() != 4442) || (item.getCustomType1() >= lotonumber))
			{
				return;
			}
			final long[] check = Lottery.getInstance().checkTicket(item);
			
			sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_DISAPPEARED);
			sm.addItemName(4442);
			player.sendPacket(sm);
			
			final long adena = check[1];
			if (adena > 0)
			{
				player.addAdena("Loto", adena, npc, true);
			}
			player.destroyItem("Loto", item, npc, false);
			return;
		}
		html.replace("%objectId%", String.valueOf(npc.getObjectId()));
		html.replace("%race%", Integer.toString(Lottery.getInstance().getId()));
		html.replace("%adena%", Long.toString(Lottery.getInstance().getPrize()));
		html.replace("%ticket_price%", Long.toString(Config.ALT_LOTTERY_TICKET_PRICE));
		html.replace("%prize5%", Float.toString(Config.ALT_LOTTERY_5_NUMBER_RATE * 100));
		html.replace("%prize4%", Float.toString(Config.ALT_LOTTERY_4_NUMBER_RATE * 100));
		html.replace("%prize3%", Float.toString(Config.ALT_LOTTERY_3_NUMBER_RATE * 100));
		html.replace("%prize2%", Long.toString(Config.ALT_LOTTERY_2_AND_1_NUMBER_PRIZE));
		html.replace("%enddate%", "" + DateFormat.getDateInstance().format(Lottery.getInstance().getEndDate()));
		player.sendPacket(html);
		
		// Send a Server->Client ActionFailed to the L2PcInstance in order to avoid that the client wait another packet
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	@Override
	public String[] getBypassList()
	{
		return COMMANDS;
	}
}

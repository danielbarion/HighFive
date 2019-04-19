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
package custom.NewbieCoupons;

import com.l2jmobius.gameserver.data.xml.impl.MultisellData;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;

/**
 * Newbie Weapon/Accesories Coupons for the Hellbound opening event.<br>
 * Original Jython script by Vice.
 * @author Nyaran
 */
public final class NewbieCoupons extends AbstractNpcAI
{
	private static final int COUPON_ONE = 7832;
	private static final int COUPON_TWO = 7833;
	
	private static final int[] NPCs =
	{
		30598,
		30599,
		30600,
		30601,
		30602,
		31076,
		31077,
		32135
	};
	
	private static final int WEAPON_MULTISELL = 305986001;
	private static final int ACCESORIES_MULTISELL = 305986002;
	
	// enable/disable coupon give
	private static final boolean NEWBIE_COUPONS_ENABLED = true;
	
	/*
	 * Newbie/one time rewards section Any quest should rely on a unique bit, but it could be shared among quests that were mutually exclusive or race restricted. Bit //1 isn't used for backwards compatibility. This script uses 2 bits, one for newbie coupons and another for travelers These 2 bits
	 * happen to be the same used by the Miss Queen script
	 */
	private static final int NEWBIE_WEAPON = 16;
	private static final int NEWBIE_ACCESORY = 32;
	
	private NewbieCoupons()
	{
		for (int i : NPCs)
		{
			addStartNpc(i);
			addTalkId(i);
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		if (!NEWBIE_COUPONS_ENABLED)
		{
			return htmltext;
		}
		
		final int newbie = player.getNewbie();
		final int level = player.getLevel();
		final int occupation_level = player.getClassId().level();
		final int pkkills = player.getPkKills();
		if (event.equals("newbie_give_weapon_coupon"))
		{
			/*
			 * TODO: check if this is the very first character for this account would need a bit of SQL, or a core method to determine it. This condition should be stored by the core in the account_data table upon character creation.
			 */
			if ((level >= 6) && (level <= 39) && (pkkills == 0) && (occupation_level == 0))
			{
				// check the player state against this quest newbie rewarding mark.
				if ((newbie | NEWBIE_WEAPON) != newbie)
				{
					player.setNewbie(newbie | NEWBIE_WEAPON);
					giveItems(player, COUPON_ONE, 5);
					htmltext = "30598-2.htm"; // here's the coupon you requested
				}
				else
				{
					htmltext = "30598-1.htm"; // you got a coupon already!
				}
			}
			else
			{
				htmltext = "30598-3.htm"; // you're not eligible to get a coupon (level caps, pkkills or already changed class)
			}
		}
		else if (event.equals("newbie_give_armor_coupon"))
		{
			if ((level >= 6) && (level <= 39) && (pkkills == 0) && (occupation_level == 1))
			{
				// check the player state against this quest newbie rewarding mark.
				if ((newbie | NEWBIE_ACCESORY) != newbie)
				{
					player.setNewbie(newbie | NEWBIE_ACCESORY);
					giveItems(player, COUPON_TWO, 1);
					htmltext = "30598-5.htm"; // here's the coupon you requested
				}
				else
				{
					htmltext = "30598-4.htm"; // you got a coupon already!
				}
			}
			else
			{
				htmltext = "30598-6.htm"; // you're not eligible to get a coupon (level caps, pkkills or didnt change class yet)
			}
		}
		else if (event.equals("newbie_show_weapon"))
		{
			if ((level >= 6) && (level <= 39) && (pkkills == 0) && (occupation_level == 0))
			{
				MultisellData.getInstance().separateAndSend(WEAPON_MULTISELL, player, npc, false);
				return null;
			}
			htmltext = "30598-7.htm"; // you're not eligible to use warehouse
		}
		else if (event.equals("newbie_show_armor"))
		{
			if ((level >= 6) && (level <= 39) && (pkkills == 0) && (occupation_level > 0))
			{
				MultisellData.getInstance().separateAndSend(ACCESORIES_MULTISELL, player, npc, false);
				return null;
			}
			htmltext = "30598-8.htm"; // you're not eligible to use warehouse
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		getQuestState(player, true);
		
		return "30598.htm";
	}
	
	public static void main(String args[])
	{
		new NewbieCoupons();
	}
}

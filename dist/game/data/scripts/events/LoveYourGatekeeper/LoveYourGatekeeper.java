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
package events.LoveYourGatekeeper;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.LongTimeEvent;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

/**
 * Love Your Gatekeeper event.
 * @author Gladicek
 */
public final class LoveYourGatekeeper extends LongTimeEvent
{
	// NPC
	private static final int GATEKEEPER = 32477;
	// Item
	private static final int GATEKEEPER_TRANSFORMATION_STICK = 12814;
	// Skills
	private static SkillHolder TELEPORTER_TRANSFORM = new SkillHolder(5655, 1);
	// Misc
	private static final int HOURS = 24;
	private static final int PRICE = 10000;
	private static final String REUSE = LoveYourGatekeeper.class.getSimpleName() + "_reuse";
	
	private LoveYourGatekeeper()
	{
		addStartNpc(GATEKEEPER);
		addFirstTalkId(GATEKEEPER);
		addTalkId(GATEKEEPER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "transform_stick":
			{
				if (player.getAdena() >= PRICE)
				{
					final long reuse = player.getVariables().getLong(REUSE, 0);
					if (reuse > System.currentTimeMillis())
					{
						final long remainingTime = (reuse - System.currentTimeMillis()) / 1000;
						final int hours = (int) (remainingTime / 3600);
						final int minutes = (int) ((remainingTime % 3600) / 60);
						final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_WILL_BE_AVAILABLE_FOR_RE_USE_AFTER_S2_HOUR_S_S3_MINUTE_S);
						sm.addItemName(GATEKEEPER_TRANSFORMATION_STICK);
						sm.addInt(hours);
						sm.addInt(minutes);
						player.sendPacket(sm);
					}
					else
					{
						takeItems(player, Inventory.ADENA_ID, PRICE);
						giveItems(player, GATEKEEPER_TRANSFORMATION_STICK, 1);
						player.getVariables().set(REUSE, System.currentTimeMillis() + (HOURS * 3600000));
					}
				}
				else
				{
					return "32477-3.htm";
				}
				return null;
			}
			case "transform":
			{
				if (!player.isTransformed())
				{
					player.doCast(TELEPORTER_TRANSFORM.getSkill());
				}
				return null;
			}
		}
		return event;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		return "32477.htm";
	}
	
	public static void main(String[] args)
	{
		new LoveYourGatekeeper();
	}
}
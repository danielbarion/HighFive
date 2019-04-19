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
package ai.areas.Hellbound.AI.NPC.Hude;

import com.l2jmobius.gameserver.data.xml.impl.MultisellData;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;
import ai.areas.Hellbound.HellboundEngine;

/**
 * Hude AI.
 * @author DS
 */
public final class Hude extends AbstractNpcAI
{
	// NPCs
	private static final int HUDE = 32298;
	// Items
	private static final int BASIC_CERT = 9850;
	private static final int STANDART_CERT = 9851;
	private static final int PREMIUM_CERT = 9852;
	private static final int MARK_OF_BETRAYAL = 9676;
	private static final int LIFE_FORCE = 9681;
	private static final int CONTAINED_LIFE_FORCE = 9682;
	private static final int MAP = 9994;
	private static final int STINGER = 10012;
	
	public Hude()
	{
		addFirstTalkId(HUDE);
		addStartNpc(HUDE);
		addTalkId(HUDE);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "scertif":
			{
				if (HellboundEngine.getInstance().getLevel() > 3)
				{
					if (hasQuestItems(player, BASIC_CERT) && (getQuestItemsCount(player, MARK_OF_BETRAYAL) >= 30) && (getQuestItemsCount(player, STINGER) >= 60))
					{
						takeItems(player, MARK_OF_BETRAYAL, 30);
						takeItems(player, STINGER, 60);
						takeItems(player, BASIC_CERT, 1);
						giveItems(player, STANDART_CERT, 1);
						return "32298-04a.htm";
					}
				}
				return "32298-04b.htm";
			}
			case "pcertif":
			{
				if (HellboundEngine.getInstance().getLevel() > 6)
				{
					if (hasQuestItems(player, STANDART_CERT) && (getQuestItemsCount(player, LIFE_FORCE) >= 56) && (getQuestItemsCount(player, CONTAINED_LIFE_FORCE) >= 14))
					{
						takeItems(player, LIFE_FORCE, 56);
						takeItems(player, CONTAINED_LIFE_FORCE, 14);
						takeItems(player, STANDART_CERT, 1);
						giveItems(player, PREMIUM_CERT, 1);
						giveItems(player, MAP, 1);
						return "32298-06a.htm";
					}
				}
				return "32298-06b.htm";
			}
			case "multisell1":
			{
				if (hasQuestItems(player, STANDART_CERT) || hasQuestItems(player, PREMIUM_CERT))
				{
					MultisellData.getInstance().separateAndSend(322980001, player, npc, false);
				}
				break;
			}
			case "multisell2":
			{
				if (hasQuestItems(player, PREMIUM_CERT))
				{
					MultisellData.getInstance().separateAndSend(322980002, player, npc, false);
				}
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		if (!hasAtLeastOneQuestItem(player, BASIC_CERT, STANDART_CERT, PREMIUM_CERT))
		{
			htmltext = "32298-01.htm";
		}
		else if (hasQuestItems(player, BASIC_CERT) && !hasAtLeastOneQuestItem(player, STANDART_CERT, PREMIUM_CERT))
		{
			htmltext = "32298-03.htm";
		}
		else if (hasQuestItems(player, STANDART_CERT) && !hasQuestItems(player, PREMIUM_CERT))
		{
			htmltext = "32298-05.htm";
		}
		else if (hasQuestItems(player, PREMIUM_CERT))
		{
			htmltext = "32298-07.htm";
		}
		return htmltext;
	}
}
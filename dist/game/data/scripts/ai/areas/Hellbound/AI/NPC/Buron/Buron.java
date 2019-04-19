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
package ai.areas.Hellbound.AI.NPC.Buron;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;

import ai.AbstractNpcAI;
import ai.areas.Hellbound.HellboundEngine;

/**
 * Buron AI.
 * @author DS
 */
public final class Buron extends AbstractNpcAI
{
	private static final int BURON = 32345;
	private static final int HELMET = 9669;
	private static final int TUNIC = 9670;
	private static final int PANTS = 9671;
	private static final int DARION_BADGE = 9674;
	
	public Buron()
	{
		addFirstTalkId(BURON);
		addStartNpc(BURON);
		addTalkId(BURON);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		if ("Rumor".equalsIgnoreCase(event))
		{
			htmltext = "32345-" + HellboundEngine.getInstance().getLevel() + "r.htm";
		}
		else
		{
			if (HellboundEngine.getInstance().getLevel() < 2)
			{
				htmltext = "32345-lowlvl.htm";
			}
			else
			{
				if (getQuestItemsCount(player, DARION_BADGE) >= 10)
				{
					takeItems(player, DARION_BADGE, 10);
					if (event.equalsIgnoreCase("Tunic"))
					{
						player.addItem("Quest", TUNIC, 1, npc, true);
					}
					else if (event.equalsIgnoreCase("Helmet"))
					{
						player.addItem("Quest", HELMET, 1, npc, true);
					}
					else if (event.equalsIgnoreCase("Pants"))
					{
						player.addItem("Quest", PANTS, 1, npc, true);
					}
					htmltext = null;
				}
				else
				{
					htmltext = "32345-noitems.htm";
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		getQuestState(player, true);
		switch (HellboundEngine.getInstance().getLevel())
		{
			case 1:
			{
				return "32345-01.htm";
			}
			case 2:
			case 3:
			case 4:
			{
				return "32345-02.htm";
			}
			default:
			{
				return "32345-01a.htm";
			}
		}
	}
}
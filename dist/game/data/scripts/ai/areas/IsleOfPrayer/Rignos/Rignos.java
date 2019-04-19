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
package ai.areas.IsleOfPrayer.Rignos;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;

import ai.AbstractNpcAI;

/**
 * Rignos AI.
 * @author St3eT
 */
public class Rignos extends AbstractNpcAI
{
	// NPC
	private static final int RIGNOS = 32349; // Rignos
	// Item
	private static final int STAMP = 10013; // Race Stamp
	private static final int KEY = 9694; // Secret Key
	// Skill
	private static final SkillHolder TIMER = new SkillHolder(5239, 5); // Event Timer
	// Misc
	private static final int MIN_LV = 78;
	
	private Rignos()
	{
		addStartNpc(RIGNOS);
		addTalkId(RIGNOS);
		addFirstTalkId(RIGNOS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "32349-03.html":
			{
				return event;
			}
			case "startRace":
			{
				if (npc.isScriptValue(0))
				{
					npc.setScriptValue(1);
					startQuestTimer("TIME_OUT", 1800000, npc, null);
					TIMER.getSkill().applyEffects(player, player);
					if (player.hasSummon())
					{
						TIMER.getSkill().applyEffects(player.getSummon(), player.getSummon());
					}
					
					if (hasQuestItems(player, STAMP))
					{
						takeItems(player, STAMP, -1);
					}
				}
				break;
			}
			case "exchange":
			{
				if (getQuestItemsCount(player, STAMP) >= 4)
				{
					giveItems(player, KEY, 3);
					takeItems(player, STAMP, -1);
				}
				break;
			}
			case "TIME_OUT":
			{
				npc.setScriptValue(0);
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = (npc.isScriptValue(0) && (player.getLevel() >= MIN_LV)) ? "32349.html" : "32349-02.html";
		if (getQuestItemsCount(player, STAMP) >= 4)
		{
			htmltext = "32349-01.html";
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new Rignos();
	}
}
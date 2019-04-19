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
package ai.areas.Gracia.AI.NPC.AbyssGaze;

import com.l2jmobius.gameserver.instancemanager.SoIManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.QuestState;

import ai.AbstractNpcAI;

public class AbyssGaze extends AbstractNpcAI
{
	// NPC
	private static final int ABYSS_GATEKEEPER = 32539;
	
	public AbyssGaze()
	{
		addStartNpc(ABYSS_GATEKEEPER);
		addFirstTalkId(ABYSS_GATEKEEPER);
		addTalkId(ABYSS_GATEKEEPER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);
		}
		
		if (event.equals("request_permission"))
		{
			if ((SoIManager.getCurrentStage() == 2) || (SoIManager.getCurrentStage() == 5))
			{
				htmltext = "32539-2.htm";
			}
			else if ((SoIManager.getCurrentStage() == 3) && SoIManager.isSeedOpen())
			{
				htmltext = "32539-3.htm";
			}
			else
			{
				htmltext = "32539-1.htm";
			}
		}
		else if (event.equals("enter_seed"))
		{
			if (SoIManager.getCurrentStage() == 3)
			{
				return null;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState qs = player.getQuestState(getName());
		if (qs == null)
		{
			qs = newQuestState(player);
		}
		return "32539.htm";
	}
}
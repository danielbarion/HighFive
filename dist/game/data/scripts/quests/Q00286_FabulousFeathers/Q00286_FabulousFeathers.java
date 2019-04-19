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
package quests.Q00286_FabulousFeathers;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Fabulous Feathers (286).
 * @author xban1x
 */
public final class Q00286_FabulousFeathers extends Quest
{
	// NPC
	private static final int ERINU = 32164;
	// Item
	private static final ItemHolder COMMANDERS_FEATHER = new ItemHolder(9746, 80);
	// Monsters
	private static final Map<Integer, Double> MOB_DROP_CHANCES = new HashMap<>();
	static
	{
		MOB_DROP_CHANCES.put(22251, 0.748); // Shady Muertos Captain
		MOB_DROP_CHANCES.put(22253, 0.772); // Shady Muertos Warrior
		MOB_DROP_CHANCES.put(22254, 0.772); // Shady Muertos Archer
		MOB_DROP_CHANCES.put(22255, 0.796); // Shady Muertos Commander
		MOB_DROP_CHANCES.put(22256, 0.952); // Shady Muertos Wizard
	}
	// Misc
	private static final int MIN_LVL = 17;
	
	public Q00286_FabulousFeathers()
	{
		super(286);
		addStartNpc(ERINU);
		addTalkId(ERINU);
		addKillId(MOB_DROP_CHANCES.keySet());
		registerQuestItems(COMMANDERS_FEATHER.getId());
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String html = null;
		if (qs == null)
		{
			return html;
		}
		switch (event)
		{
			case "32164-03.htm":
			{
				qs.startQuest();
				html = event;
				break;
			}
			case "32164-06.html":
			{
				if (qs.isCond(2) && hasItem(player, COMMANDERS_FEATHER))
				{
					takeItem(player, COMMANDERS_FEATHER);
					giveAdena(player, 4160, true);
					qs.exitQuest(true, true);
					html = event;
				}
				else
				{
					html = "32164-07.html";
				}
				break;
			}
		}
		return html;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, 1, 3, npc);
		if (qs != null)
		{
			if (giveItemRandomly(qs.getPlayer(), npc, COMMANDERS_FEATHER.getId(), 1, COMMANDERS_FEATHER.getCount(), MOB_DROP_CHANCES.get(npc.getId()), true))
			{
				qs.setCond(2);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String html = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			html = ((player.getLevel() >= MIN_LVL) ? "32164-01.htm" : "32164-02.htm");
		}
		else if (qs.isStarted())
		{
			html = ((qs.isCond(2) && hasItem(player, COMMANDERS_FEATHER)) ? "32164-04.html" : "32164-05.html");
		}
		return html;
	}
}

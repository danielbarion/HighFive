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
package quests.Q00157_RecoverSmuggledGoods;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Recover Smuggled Goods (157)
 * @author xban1x
 */
public class Q00157_RecoverSmuggledGoods extends Quest
{
	// NPC
	private static final int WILFORD = 30005;
	// Monster
	private static final int GIANT_TOAD = 20121;
	// Items
	private static final int BUCKLER = 20;
	private static final int ADAMANTITE_ORE = 1024;
	// Misc
	private static final int MIN_LVL = 5;
	
	public Q00157_RecoverSmuggledGoods()
	{
		super(157);
		addStartNpc(WILFORD);
		addTalkId(WILFORD);
		addKillId(GIANT_TOAD);
		registerQuestItems(ADAMANTITE_ORE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		String htmltext = null;
		if (st != null)
		{
			switch (event)
			{
				case "30005-03.htm":
				{
					htmltext = event;
					break;
				}
				case "30005-04.htm":
				{
					st.startQuest();
					htmltext = event;
					break;
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState st = getQuestState(killer, false);
		if ((st != null) && st.isCond(1) && (getRandom(10) < 4) && (getQuestItemsCount(killer, ADAMANTITE_ORE) < 20))
		{
			giveItems(killer, ADAMANTITE_ORE, 1);
			if (getQuestItemsCount(killer, ADAMANTITE_ORE) >= 20)
			{
				st.setCond(2, true);
			}
			else
			{
				playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				htmltext = player.getLevel() >= MIN_LVL ? "30005-02.htm" : "30005-01.htm";
				break;
			}
			case State.STARTED:
			{
				if (st.isCond(2) && (getQuestItemsCount(player, ADAMANTITE_ORE) >= 20))
				{
					giveItems(player, BUCKLER, 1);
					st.exitQuest(false, true);
					htmltext = "30005-06.html";
				}
				else
				{
					htmltext = "30005-05.html";
				}
				break;
			}
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
		}
		return htmltext;
	}
}

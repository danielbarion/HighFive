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
package quests.Q00690_JudesRequest;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Jude's Request (690)
 * @author malyelfik
 */
public class Q00690_JudesRequest extends Quest
{
	// NPCs
	private static final int JUDE = 32356;
	private static final int LESSER_EVIL = 22398;
	private static final int GREATER_EVIL = 22399;
	// Items
	private static final int EVIL_WEAPON = 10327;
	private static final int[][] REWARDS =
	{
		{
			10373,
			10374,
			10375,
			10376,
			10377,
			10378,
			10379,
			10380,
			10381
		},
		{
			10397,
			10398,
			10399,
			10400,
			10401,
			10402,
			10403,
			10404,
			10405
		}
	};
	
	public Q00690_JudesRequest()
	{
		super(690);
		addStartNpc(JUDE);
		addTalkId(JUDE);
		addKillId(LESSER_EVIL, GREATER_EVIL);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState st = getQuestState(player, false);
		
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		if (event.equalsIgnoreCase("32356-03.htm"))
		{
			st.startQuest();
		}
		else if (event.equalsIgnoreCase("32356-07.htm"))
		{
			if (getQuestItemsCount(player, EVIL_WEAPON) >= 200)
			{
				giveItems(player, REWARDS[0][getRandom(REWARDS[0].length)], 1);
				takeItems(player, EVIL_WEAPON, 200);
				playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
				htmltext = "32356-07.htm";
			}
			else
			{
				htmltext = "32356-07a.htm";
			}
		}
		else if (event.equalsIgnoreCase("32356-08.htm"))
		{
			takeItems(player, EVIL_WEAPON, -1);
			st.exitQuest(true, true);
		}
		else if (event.equalsIgnoreCase("32356-09.htm"))
		{
			if (getQuestItemsCount(player, EVIL_WEAPON) >= 5)
			{
				giveItems(player, REWARDS[1][getRandom(REWARDS[1].length)], 1);
				takeItems(player, EVIL_WEAPON, 5);
				playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
				htmltext = "32356-09.htm";
			}
			else
			{
				htmltext = "32356-09a.htm";
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 1);
		if (partyMember == null)
		{
			return null;
		}
		
		final int npcId = npc.getId();
		int chance = 0;
		if (npcId == LESSER_EVIL)
		{
			chance = 173;
		}
		else if (npcId == GREATER_EVIL)
		{
			chance = 246;
		}
		// Apply the quest drop rate:
		chance *= Config.RATE_QUEST_DROP;
		// Normalize
		chance %= 1000;
		
		if (getRandom(1000) <= chance)
		{
			giveItems(player, EVIL_WEAPON, Math.max(chance / 1000, 1));
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		return null;
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
				if (player.getLevel() >= 78)
				{
					htmltext = "32356-01.htm";
				}
				else
				{
					htmltext = "32356-02.htm";
				}
				break;
			}
			case State.STARTED:
			{
				if (getQuestItemsCount(player, EVIL_WEAPON) >= 200)
				{
					htmltext = "32356-04.htm";
				}
				else if (getQuestItemsCount(player, EVIL_WEAPON) < 5)
				{
					htmltext = "32356-05a.htm";
				}
				else
				{
					htmltext = "32356-05.htm";
				}
				break;
			}
		}
		return htmltext;
	}
}

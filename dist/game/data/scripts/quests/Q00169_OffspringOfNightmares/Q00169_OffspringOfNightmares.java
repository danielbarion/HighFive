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
package quests.Q00169_OffspringOfNightmares;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;

/**
 * Offspring of Nightmares (169)
 * @author xban1x
 */
public class Q00169_OffspringOfNightmares extends Quest
{
	// NPC
	private static final int VLASTY = 30145;
	// Monsters
	private static final int LESSER_DARK_HORROR = 20025;
	private static final int DARK_HORROR = 20105;
	// Items
	private static final int BONE_GAITERS = 31;
	private static final int CRACKED_SKULL = 1030;
	private static final int PERFECT_SKULL = 1031;
	// Misc
	private static final int MIN_LVL = 15;
	
	public Q00169_OffspringOfNightmares()
	{
		super(169);
		addStartNpc(VLASTY);
		addTalkId(VLASTY);
		addKillId(LESSER_DARK_HORROR, DARK_HORROR);
		registerQuestItems(CRACKED_SKULL, PERFECT_SKULL);
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
				case "30145-03.htm":
				{
					st.startQuest();
					htmltext = event;
					break;
				}
				case "30145-07.html":
				{
					if (st.isCond(2) && hasQuestItems(player, PERFECT_SKULL))
					{
						giveItems(player, BONE_GAITERS, 1);
						addExpAndSp(player, 17475, 818);
						giveAdena(player, 17030 + (10 * getQuestItemsCount(player, CRACKED_SKULL)), true);
						st.exitQuest(false, true);
						showOnScreenMsg(player, NpcStringId.LAST_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE, 2, 5000); // TODO: Newbie Guide
						htmltext = event;
					}
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
		if ((st != null) && st.isStarted())
		{
			if ((getRandom(10) > 7) && !hasQuestItems(killer, PERFECT_SKULL))
			{
				giveItems(killer, PERFECT_SKULL, 1);
				st.setCond(2, true);
			}
			else if (getRandom(10) > 4)
			{
				giveItems(killer, CRACKED_SKULL, 1);
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
				htmltext = (player.getRace() == Race.DARK_ELF) ? (player.getLevel() >= MIN_LVL) ? "30145-02.htm" : "30145-01.htm" : "30145-00.htm";
				break;
			}
			case State.STARTED:
			{
				if (hasQuestItems(player, CRACKED_SKULL) && !hasQuestItems(player, PERFECT_SKULL))
				{
					htmltext = "30145-05.html";
				}
				else if (st.isCond(2) && hasQuestItems(player, PERFECT_SKULL))
				{
					htmltext = "30145-06.html";
				}
				else if (!hasQuestItems(player, CRACKED_SKULL, PERFECT_SKULL))
				{
					htmltext = "30145-04.html";
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
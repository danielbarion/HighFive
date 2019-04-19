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
package quests.Q00906_TheCallOfValakas;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.util.Util;

/**
 * The Call of Valakas (906)
 * @author Zoey76
 */
public class Q00906_TheCallOfValakas extends Quest
{
	// NPC
	private static final int KLEIN = 31540;
	// Monster
	private static final int LAVASAURUS_ALPHA = 29029;
	// Items
	private static final int LAVASAURUS_ALPHA_FRAGMENT = 21993;
	private static final int SCROLL_VALAKAS_CALL = 21895;
	private static final int VACUALITE_FLOATING_STONE = 7267;
	// Misc
	private static final int MIN_LEVEL = 83;
	
	public Q00906_TheCallOfValakas()
	{
		super(906);
		addStartNpc(KLEIN);
		addTalkId(KLEIN);
		addKillId(LAVASAURUS_ALPHA);
		registerQuestItems(LAVASAURUS_ALPHA_FRAGMENT);
	}
	
	@Override
	public void actionForEachPlayer(L2PcInstance player, L2Npc npc, boolean isSummon)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			giveItems(player, LAVASAURUS_ALPHA_FRAGMENT, 1);
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			qs.setCond(2, true);
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		if ((player.getLevel() >= MIN_LEVEL) && hasQuestItems(player, VACUALITE_FLOATING_STONE))
		{
			switch (event)
			{
				case "31540-05.htm":
				{
					htmltext = event;
					break;
				}
				case "31540-06.html":
				{
					qs.startQuest();
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
		executeForEachPlayer(killer, npc, isSummon, true, false);
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "31540-03.html";
				}
				else if (!hasQuestItems(player, VACUALITE_FLOATING_STONE))
				{
					htmltext = "31540-04.html";
				}
				else
				{
					htmltext = "31540-01.htm";
				}
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "31540-07.html";
						break;
					}
					case 2:
					{
						giveItems(player, SCROLL_VALAKAS_CALL, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						qs.exitQuest(QuestType.DAILY, true);
						htmltext = "31540-08.html";
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				if (!qs.isNowAvailable())
				{
					htmltext = "31540-02.html";
				}
				else
				{
					qs.setState(State.CREATED);
					if (player.getLevel() < MIN_LEVEL)
					{
						htmltext = "31540-03.html";
					}
					else if (!hasQuestItems(player, VACUALITE_FLOATING_STONE))
					{
						htmltext = "31540-04.html";
					}
					else
					{
						htmltext = "31540-01.htm";
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

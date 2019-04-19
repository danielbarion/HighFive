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
package quests.Q10283_RequestOfIceMerchant;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q00115_TheOtherSideOfTruth.Q00115_TheOtherSideOfTruth;

/**
 * Request of Ice Merchant (10283)
 * @author Adry_85
 * @since 2.6.0.0
 */
public class Q10283_RequestOfIceMerchant extends Quest
{
	// NPCs
	private static final int RAFFORTY = 32020;
	private static final int KIER = 32022;
	private static final int JINIA = 32760;
	// Misc
	private static final int MIN_LEVEL = 82;
	// Variables
	private boolean isBusy = false;
	private int talker = 0;
	
	public Q10283_RequestOfIceMerchant()
	{
		super(10283);
		addStartNpc(RAFFORTY);
		addTalkId(RAFFORTY, KIER, JINIA);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ((npc.getId() == JINIA) && "DESPAWN".equals(event))
		{
			isBusy = false;
			talker = 0;
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "32020-03.htm":
			{
				htmltext = event;
				break;
			}
			case "32020-04.htm":
			{
				st.startQuest();
				st.setMemoState(1);
				htmltext = event;
				break;
			}
			case "32020-05.html":
			case "32020-06.html":
			{
				if (st.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32020-07.html":
			{
				if (st.isMemoState(1))
				{
					st.setMemoState(2);
					st.setCond(2);
					htmltext = event;
				}
				break;
			}
			case "32022-02.html":
			{
				if (st.isMemoState(2))
				{
					if (!isBusy)
					{
						isBusy = true;
						talker = player.getObjectId();
						st.setCond(3);
						addSpawn(JINIA, 104476, -107535, -3688, 44954, false, 0, false);
					}
					else
					{
						htmltext = (talker == player.getObjectId() ? event : "32022-03.html");
					}
				}
				break;
			}
			case "32760-02.html":
			case "32760-03.html":
			{
				if (st.isMemoState(2))
				{
					htmltext = event;
				}
				break;
			}
			case "32760-04.html":
			{
				if (st.isMemoState(2))
				{
					giveAdena(player, 190000, true);
					addExpAndSp(player, 627000, 50300);
					st.exitQuest(false, true);
					htmltext = event;
					startQuestTimer("DESPAWN", 2000, npc, null);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (st.isCompleted())
		{
			if (npc.getId() == RAFFORTY)
			{
				htmltext = "32020-02.html";
			}
			else if (npc.getId() == JINIA)
			{
				htmltext = "32760-06.html";
			}
		}
		else if (st.isCreated())
		{
			final QuestState st1 = player.getQuestState(Q00115_TheOtherSideOfTruth.class.getSimpleName());
			htmltext = ((player.getLevel() >= MIN_LEVEL) && (st1 != null) && (st1.isCompleted())) ? "32020-01.htm" : "32020-08.htm";
		}
		else if (st.isStarted())
		{
			switch (npc.getId())
			{
				case RAFFORTY:
				{
					if (st.isMemoState(1))
					{
						htmltext = "32020-09.html";
					}
					else if (st.isMemoState(2))
					{
						htmltext = "32020-10.html";
					}
					break;
				}
				case KIER:
				{
					if (st.isMemoState(2))
					{
						htmltext = "32022-01.html";
					}
					break;
				}
				case JINIA:
				{
					if (st.isMemoState(2))
					{
						htmltext = (talker == player.getObjectId() ? "32760-01.html" : "32760-05.html");
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

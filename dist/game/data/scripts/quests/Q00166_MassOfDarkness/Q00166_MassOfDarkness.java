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
package quests.Q00166_MassOfDarkness;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;

/**
 * Mass of Darkness (166)
 * @author xban1x
 */
public class Q00166_MassOfDarkness extends Quest
{
	// NPCs
	private static final int UNDRIAS = 30130;
	private static final int IRIA = 30135;
	private static final int DORANKUS = 30139;
	private static final int TRUDY = 30143;
	// Items
	private static final int UNDRIAS_LETTER = 1088;
	private static final int CEREMONIAL_DAGGER = 1089;
	private static final int DREVIANT_WINE = 1090;
	private static final int GARMIELS_SCRIPTURE = 1091;
	// Misc
	private static final int MIN_LVL = 2;
	private static final Map<Integer, Integer> NPCs = new HashMap<>();
	static
	{
		NPCs.put(IRIA, CEREMONIAL_DAGGER);
		NPCs.put(DORANKUS, DREVIANT_WINE);
		NPCs.put(TRUDY, GARMIELS_SCRIPTURE);
	}
	
	public Q00166_MassOfDarkness()
	{
		super(166);
		addStartNpc(UNDRIAS);
		addTalkId(UNDRIAS, IRIA, DORANKUS, TRUDY);
		registerQuestItems(UNDRIAS_LETTER, CEREMONIAL_DAGGER, DREVIANT_WINE, GARMIELS_SCRIPTURE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && event.equals("30130-03.htm"))
		{
			st.startQuest();
			giveItems(player, UNDRIAS_LETTER, 1);
			return event;
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case UNDRIAS:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getRace() == Race.DARK_ELF) ? (player.getLevel() >= MIN_LVL) ? "30130-02.htm" : "30130-01.htm" : "30130-00.htm";
						break;
					}
					case State.STARTED:
					{
						if (st.isCond(2) && hasQuestItems(player, UNDRIAS_LETTER, CEREMONIAL_DAGGER, DREVIANT_WINE, GARMIELS_SCRIPTURE))
						{
							showOnScreenMsg(player, NpcStringId.DELIVERY_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE, 2, 5000); // TODO: Newbie Guide
							addExpAndSp(player, 5672, 466);
							giveAdena(player, 2966, true);
							st.exitQuest(false, true);
							htmltext = "30130-05.html";
						}
						else
						{
							htmltext = "30130-04.html";
						}
						break;
					}
					case State.COMPLETED:
					{
						htmltext = getAlreadyCompletedMsg(player);
						break;
					}
				}
				break;
			}
			case IRIA:
			case DORANKUS:
			case TRUDY:
			{
				if (st.isStarted())
				{
					final int npcId = npc.getId();
					final int itemId = NPCs.get(npcId);
					if (st.isCond(1) && !hasQuestItems(player, itemId))
					{
						giveItems(player, itemId, 1);
						if (hasQuestItems(player, CEREMONIAL_DAGGER, DREVIANT_WINE, GARMIELS_SCRIPTURE))
						{
							st.setCond(2, true);
						}
						htmltext = npcId + "-01.html";
					}
					else
					{
						htmltext = npcId + "-02.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
}
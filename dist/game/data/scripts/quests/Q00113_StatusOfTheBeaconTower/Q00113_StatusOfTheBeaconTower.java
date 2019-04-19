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
package quests.Q00113_StatusOfTheBeaconTower;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Status of the Beacon Tower (113)<br>
 * Original Jython script by Kerberos.
 * @author malyelfik
 */
public class Q00113_StatusOfTheBeaconTower extends Quest
{
	// NPCs
	private static final int MOIRA = 31979;
	private static final int TORRANT = 32016;
	// Items
	private static final int FLAME_BOX = 14860;
	private static final int FIRE_BOX = 8086;
	
	public Q00113_StatusOfTheBeaconTower()
	{
		super(113);
		addStartNpc(MOIRA);
		addTalkId(MOIRA, TORRANT);
		registerQuestItems(FIRE_BOX, FLAME_BOX);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = event;
		switch (event)
		{
			case "31979-02.htm":
			{
				qs.startQuest();
				giveItems(player, FLAME_BOX, 1);
				break;
			}
			case "32016-02.html":
			{
				if (hasQuestItems(player, FIRE_BOX))
				{
					giveAdena(player, 21578, true);
					addExpAndSp(player, 76665, 5333);
				}
				else
				{
					giveAdena(player, 154800, true);
					addExpAndSp(player, 619300, 44200);
				}
				qs.exitQuest(false, true);
				break;
			}
			default:
			{
				htmltext = null;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case MOIRA:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= 80) ? "31979-01.htm" : "31979-00.htm";
						break;
					}
					case State.STARTED:
					{
						htmltext = "31979-03.html";
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
			case TORRANT:
			{
				if (qs.isStarted())
				{
					htmltext = "32016-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
}
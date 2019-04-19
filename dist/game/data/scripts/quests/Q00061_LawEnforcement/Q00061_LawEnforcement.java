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
package quests.Q00061_LawEnforcement;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Law Enforcement (61)
 * @author Adry_85
 */
public final class Q00061_LawEnforcement extends Quest
{
	// NPCs
	private static final int LIANE = 32222;
	private static final int KEKROPUS = 32138;
	private static final int EINDBURGH = 32469;
	// Misc
	private static final int MIN_LEVEL = 76;
	
	public Q00061_LawEnforcement()
	{
		super(61);
		addStartNpc(LIANE);
		addTalkId(LIANE, KEKROPUS, EINDBURGH);
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
		switch (event)
		{
			case "32222-02.htm":
			{
				htmltext = event;
				break;
			}
			case "32222-03.htm":
			{
				qs.setMemoState(1);
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32138-01.html":
			case "32138-02.html":
			{
				if (qs.isMemoState(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32138-03.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					htmltext = event;
				}
				break;
			}
			case "32138-04.html":
			case "32138-05.html":
			case "32138-06.html":
			case "32138-07.html":
			{
				if (qs.isMemoState(2) || qs.isMemoState(3))
				{
					htmltext = event;
				}
				break;
			}
			case "32138-08.html":
			{
				if (qs.isMemoState(2) || qs.isMemoState(3))
				{
					qs.setMemoState(4);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "32138-09.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(3);
					htmltext = event;
				}
				break;
			}
			case "32469-02.html":
			{
				if (qs.isMemoState(4))
				{
					qs.setMemoState(5);
					htmltext = event;
				}
				break;
			}
			case "32469-03.html":
			case "32469-04.html":
			case "32469-05.html":
			case "32469-06.html":
			case "32469-07.html":
			{
				if (qs.isMemoState(5))
				{
					htmltext = event;
				}
				break;
			}
			case "32469-08.html":
			case "32469-09.html":
			{
				if (qs.isMemoState(5))
				{
					player.setClassId(136);
					// SystemMessage and cast skill is done by setClassId
					player.broadcastUserInfo();
					giveAdena(player, 26000, true);
					qs.exitQuest(false, true);
					htmltext = event;
				}
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
		if (qs.isCompleted() && (npc.getId() == LIANE))
		{
			htmltext = getAlreadyCompletedMsg(player);
		}
		else if (qs.isCreated())
		{
			if (player.getLevel() >= MIN_LEVEL)
			{
				if (player.getClassId() == ClassId.INSPECTOR)
				{
					return getHtm(player, "32222-01.htm").replace("%name%", player.getName());
				}
				htmltext = "32222-04.htm";
			}
			else
			{
				htmltext = "32222-05.htm";
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case LIANE:
				{
					if (qs.isMemoState(1))
					{
						htmltext = "32222-06.html";
					}
					break;
				}
				case KEKROPUS:
				{
					switch (qs.getMemoState())
					{
						case 1:
						{
							htmltext = "32138-01.html";
							break;
						}
						case 2:
						{
							htmltext = "32138-03.html";
							break;
						}
						case 3:
						{
							htmltext = "32138-10.html";
							break;
						}
						case 4:
						{
							htmltext = "32138-10.html";
							break;
						}
					}
					break;
				}
				case EINDBURGH:
				{
					if (qs.isMemoState(4))
					{
						return getHtm(player, "32469-01.html").replace("%name%", player.getName());
					}
					if (qs.isMemoState(5))
					{
						htmltext = "32469-02.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

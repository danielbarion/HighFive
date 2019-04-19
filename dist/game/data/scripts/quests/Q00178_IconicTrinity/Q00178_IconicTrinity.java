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
package quests.Q00178_IconicTrinity;

import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Iconic Trinity (178)
 * @author ivantotov
 */
public final class Q00178_IconicTrinity extends Quest
{
	// NPCs
	private static final int HIERARCH_KEKROPUS = 32138;
	private static final int ICON_OF_THE_PAST = 32255;
	private static final int ICON_OF_THE_PRESENT = 32256;
	private static final int ICON_OF_THE_FUTURE = 32257;
	// Reward
	private static final int SCROLL_ENCHANT_ARMOR_D_GRADE = 956;
	// Misc
	private static final int MIN_LEVEL = 17;
	private static final int TWENTY_LEVEL = 20;
	
	public Q00178_IconicTrinity()
	{
		super(178);
		addStartNpc(HIERARCH_KEKROPUS);
		addTalkId(HIERARCH_KEKROPUS, ICON_OF_THE_PAST, ICON_OF_THE_PRESENT, ICON_OF_THE_FUTURE);
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
			case "32138-05.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = event;
				}
				break;
			}
			case "32255-11.html":
			case "32256-11.html":
			case "32256-12.html":
			case "32256-13.html":
			{
				htmltext = getHtm(player, event);
				htmltext = htmltext.replaceAll("%name1%", player.getName());
				break;
			}
			case "32138-14.htm":
			{
				if ((qs.isMemoState(10) && (player.getLevel() <= TWENTY_LEVEL) && (player.getClassId() == ClassId.MALE_SOLDIER)) || (player.getClassId() == ClassId.FEMALE_SOLDIER))
				{
					giveItems(player, SCROLL_ENCHANT_ARMOR_D_GRADE, 1);
					addExpAndSp(player, 20123, 976);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "32138-17.html":
			{
				if ((qs.isMemoState(10) && (player.getLevel() > TWENTY_LEVEL) && (player.getClassId() != ClassId.MALE_SOLDIER)) || (player.getClassId() != ClassId.FEMALE_SOLDIER))
				{
					giveItems(player, SCROLL_ENCHANT_ARMOR_D_GRADE, 1);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "32255-02.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					htmltext = event;
				}
				break;
			}
			case "32255-03.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setMemoStateEx(1, 0);
					htmltext = event;
				}
				break;
			}
			case "PASS1_1":
			{
				if (qs.isMemoState(2))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 1);
					htmltext = "32255-04.html";
				}
				break;
			}
			case "PASS1_2":
			{
				if (qs.isMemoState(2))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 10);
					htmltext = "32255-05.html";
				}
				break;
			}
			case "PASS1_3":
			{
				if (qs.isMemoState(2))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 100);
					htmltext = "32255-06.html";
				}
				break;
			}
			case "PASS1_4":
			{
				if (qs.isMemoState(2))
				{
					if (qs.getMemoStateEx(1) == 111)
					{
						qs.setMemoState(3);
						qs.setMemoStateEx(1, 0);
						htmltext = "32255-07.html";
					}
					else if (qs.getMemoStateEx(1) != 111)
					{
						htmltext = "32255-08.html";
					}
				}
				break;
			}
			case "32255-13.html":
			{
				if (qs.isMemoState(3))
				{
					qs.setMemoState(4);
					qs.setCond(2, true);
					htmltext = getHtm(player, event);
					htmltext = htmltext.replaceAll("%name1%", player.getName());
				}
				break;
			}
			case "32256-02.html":
			{
				if (qs.isMemoState(4))
				{
					qs.setMemoState(5);
					htmltext = event;
				}
				break;
			}
			case "32256-03.html":
			{
				if (qs.isMemoState(5))
				{
					qs.setMemoStateEx(1, 0);
					htmltext = event;
				}
				break;
			}
			case "PASS2_1":
			{
				if (qs.isMemoState(5))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 1);
					htmltext = "32256-04.html";
				}
				break;
			}
			case "PASS2_2":
			{
				if (qs.isMemoState(5))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 10);
					htmltext = "32256-05.html";
				}
				break;
			}
			case "PASS2_3":
			{
				if (qs.isMemoState(5))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 100);
					htmltext = "32256-06.html";
				}
				break;
			}
			case "PASS2_4":
			{
				if (qs.isMemoState(5))
				{
					if (qs.getMemoStateEx(1) == 111)
					{
						qs.setMemoState(6);
						qs.setMemoStateEx(1, 0);
						htmltext = "32256-07.html";
					}
					else if (qs.getMemoStateEx(1) != 111)
					{
						htmltext = "32256-08.html";
					}
				}
				break;
			}
			case "32256-14.html":
			{
				if (qs.isMemoState(6))
				{
					qs.setMemoState(7);
					qs.setCond(3, true);
					htmltext = getHtm(player, event);
					htmltext = htmltext.replaceAll("%name1%", player.getName());
				}
				break;
			}
			case "32257-02.html":
			{
				if (qs.isMemoState(7))
				{
					qs.setMemoState(8);
					htmltext = event;
				}
				break;
			}
			case "32257-03.html":
			{
				if (qs.isMemoState(8))
				{
					qs.setMemoStateEx(1, 0);
					htmltext = event;
				}
				break;
			}
			case "PASS3_1":
			{
				if (qs.isMemoState(8))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 1);
					htmltext = "32257-04.html";
				}
				break;
			}
			case "PASS3_2":
			{
				if (qs.isMemoState(8))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 10);
					htmltext = "32257-05.html";
				}
				break;
			}
			case "PASS3_3":
			{
				if (qs.isMemoState(8))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 100);
					htmltext = "32257-06.html";
				}
				break;
			}
			case "PASS3_4":
			{
				if (qs.isMemoState(8))
				{
					final int i0 = qs.getMemoStateEx(1);
					qs.setMemoStateEx(1, i0 + 1000);
					htmltext = "32257-07.html";
				}
				break;
			}
			case "PASS3_5":
			{
				if (qs.isMemoState(8))
				{
					if (qs.getMemoStateEx(1) == 1111)
					{
						qs.setMemoState(9);
						qs.setMemoStateEx(1, 0);
						htmltext = "32257-08.html";
					}
					else if (qs.getMemoStateEx(1) != 1111)
					{
						htmltext = "32257-09.html";
					}
				}
				break;
			}
			case "32257-12.html":
			{
				if (qs.isMemoState(9))
				{
					qs.setMemoState(10);
					qs.setCond(4, true);
					htmltext = getHtm(player, event);
					htmltext = htmltext.replaceAll("%name1%", player.getName());
				}
				break;
			}
			case "32138-13.html":
			case "32138-16.html":
			case "32255-04.html":
			case "32255-05.html":
			case "32255-06.html":
			case "32255-07.html":
			case "32255-08.html":
			case "32255-09.html":
			case "32255-10.html":
			case "32255-12.html":
			case "32256-04.html":
			case "32256-05.html":
			case "32256-06.html":
			case "32256-07.html":
			case "32256-08.html":
			case "32256-09.html":
			case "32256-10.html":
			case "32257-04.html":
			case "32257-05.html":
			case "32257-06.html":
			case "32257-07.html":
			case "32257-08.html":
			case "32257-09.html":
			case "32257-10.html":
			case "32257-11.html":
			{
				htmltext = event;
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
		if (qs.isCreated())
		{
			if (npc.getId() == HIERARCH_KEKROPUS)
			{
				if (player.getRace() != Race.KAMAEL)
				{
					htmltext = "32138-03.htm";
				}
				else if (player.getLevel() >= MIN_LEVEL)
				{
					htmltext = "32138-01.htm";
				}
				else
				{
					htmltext = "32138-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case HIERARCH_KEKROPUS:
				{
					switch (qs.getMemoState())
					{
						case 1:
						case 2:
						{
							htmltext = "32138-06.html";
							break;
						}
						case 3:
						{
							htmltext = "32138-07.html";
							break;
						}
						case 4:
						case 5:
						{
							htmltext = "32138-08.html";
							break;
						}
						case 6:
						{
							htmltext = "32138-09.html";
							break;
						}
						case 7:
						case 8:
						{
							htmltext = "32138-10.html";
							break;
						}
						case 9:
						{
							htmltext = "32138-11.html";
							break;
						}
						case 10:
						{
							if (((player.getLevel() <= TWENTY_LEVEL) && (player.getClassId() == ClassId.MALE_SOLDIER)) || (player.getClassId() == ClassId.FEMALE_SOLDIER))
							{
								htmltext = "32138-12.html";
							}
							else
							{
								htmltext = "32138-15.html";
							}
							break;
						}
					}
					break;
				}
				case ICON_OF_THE_PAST:
				{
					switch (qs.getMemoState())
					{
						case 1:
						{
							htmltext = "32255-01.html";
							break;
						}
						case 2:
						{
							qs.setMemoStateEx(1, 0);
							htmltext = "32255-03.html";
							break;
						}
						case 3:
						{
							htmltext = "32255-09.html";
							break;
						}
						case 4:
						case 5:
						{
							htmltext = "32255-14.html";
							break;
						}
					}
					break;
				}
				case ICON_OF_THE_PRESENT:
				{
					switch (qs.getMemoState())
					{
						case 4:
						{
							htmltext = "32256-01.html";
							break;
						}
						case 5:
						{
							qs.setMemoStateEx(1, 0);
							htmltext = "32256-03.html";
							break;
						}
						case 6:
						{
							htmltext = "32256-09.html";
							break;
						}
						case 7:
						case 8:
						{
							htmltext = "32256-15.html";
							break;
						}
					}
					break;
				}
				case ICON_OF_THE_FUTURE:
				{
					switch (qs.getMemoState())
					{
						case 7:
						{
							htmltext = "32257-01.html";
							break;
						}
						case 8:
						{
							qs.setMemoStateEx(1, 0);
							htmltext = "32257-03.html";
							break;
						}
						case 9:
						{
							htmltext = "32257-10.html";
							break;
						}
						case 10:
						{
							htmltext = "32257-13.html";
							break;
						}
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == HIERARCH_KEKROPUS)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
		}
		return htmltext;
	}
}
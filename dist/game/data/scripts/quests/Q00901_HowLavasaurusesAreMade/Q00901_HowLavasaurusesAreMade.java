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
package quests.Q00901_HowLavasaurusesAreMade;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * How Lavasauruses Are Made (901)
 * @author UnAfraid, nonom, malyelfik
 */
public class Q00901_HowLavasaurusesAreMade extends Quest
{
	// NPC
	private static final int ROONEY = 32049;
	// Monsters
	private static final int LAVASAURUS_NEWBORN = 18799;
	private static final int LAVASAURUS_FLEDGIING = 18800;
	private static final int LAVASAURUS_ADULT = 18801;
	private static final int LAVASAURUS_ELDERLY = 18802;
	// Items
	private static final int FRAGMENT_STONE = 21909;
	private static final int FRAGMENT_HEAD = 21910;
	private static final int FRAGMENT_BODY = 21911;
	private static final int FRAGMENT_HORN = 21912;
	// Rewards
	private static final int TOTEM_OF_BODY = 21899;
	private static final int TOTEM_OF_SPIRIT = 21900;
	private static final int TOTEM_OF_COURAGE = 21901;
	private static final int TOTEM_OF_FORTITUDE = 21902;
	
	public Q00901_HowLavasaurusesAreMade()
	{
		super(901);
		addStartNpc(ROONEY);
		addTalkId(ROONEY);
		addKillId(LAVASAURUS_NEWBORN, LAVASAURUS_FLEDGIING, LAVASAURUS_ADULT, LAVASAURUS_ELDERLY);
		registerQuestItems(FRAGMENT_STONE, FRAGMENT_HORN, FRAGMENT_HEAD, FRAGMENT_BODY);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		String htmltext = event;
		switch (event)
		{
			case "32049-03.htm":
			case "32049-08.html":
			case "32049-09.html":
			case "32049-10.html":
			case "32049-11.html":
			{
				break;
			}
			case "32049-04.htm":
			{
				qs.startQuest();
				break;
			}
			case "32049-12.html":
			{
				giveItems(player, TOTEM_OF_BODY, 1);
				qs.exitQuest(QuestType.DAILY, true);
				break;
			}
			case "32049-13.html":
			{
				giveItems(player, TOTEM_OF_SPIRIT, 1);
				qs.exitQuest(QuestType.DAILY, true);
				break;
			}
			case "32049-14.html":
			{
				giveItems(player, TOTEM_OF_FORTITUDE, 1);
				qs.exitQuest(QuestType.DAILY, true);
				break;
			}
			case "32049-15.html":
			{
				giveItems(player, TOTEM_OF_COURAGE, 1);
				qs.exitQuest(QuestType.DAILY, true);
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
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isCond(1))
		{
			switch (npc.getId())
			{
				case LAVASAURUS_NEWBORN:
				{
					giveQuestItems(qs, FRAGMENT_STONE);
					break;
				}
				case LAVASAURUS_FLEDGIING:
				{
					giveQuestItems(qs, FRAGMENT_HEAD);
					break;
				}
				case LAVASAURUS_ADULT:
				{
					giveQuestItems(qs, FRAGMENT_BODY);
					break;
				}
				case LAVASAURUS_ELDERLY:
				{
					giveQuestItems(qs, FRAGMENT_HORN);
					break;
				}
			}
		}
		return super.onKill(npc, player, isSummon);
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
				htmltext = (player.getLevel() >= 76) ? "32049-01.htm" : "32049-02.htm";
				break;
			}
			case State.STARTED:
			{
				if (qs.isCond(1))
				{
					htmltext = "32049-05.html";
				}
				else if (qs.isCond(2))
				{
					if (gotAllQuestItems(player))
					{
						takeItems(player, FRAGMENT_STONE, -1);
						takeItems(player, FRAGMENT_HEAD, -1);
						takeItems(player, FRAGMENT_BODY, -1);
						takeItems(player, FRAGMENT_HORN, -1);
						htmltext = "32049-06.html";
					}
					else
					{
						htmltext = "32049-07.html";
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				if (qs.isNowAvailable())
				{
					qs.setState(State.CREATED);
					htmltext = (qs.getPlayer().getLevel() >= 76) ? "32049-01.htm" : "32049-02.html";
				}
				else
				{
					htmltext = "32049-16.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	private static void giveQuestItems(QuestState qs, int itemId)
	{
		final L2PcInstance player = qs.getPlayer();
		if (getQuestItemsCount(player, itemId) < 10)
		{
			giveItems(player, itemId, 1);
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		else if (gotAllQuestItems(player))
		{
			qs.setCond(2, true);
		}
	}
	
	private static boolean gotAllQuestItems(L2PcInstance player)
	{
		return (getQuestItemsCount(player, FRAGMENT_STONE) >= 10) && (getQuestItemsCount(player, FRAGMENT_HEAD) >= 10) && (getQuestItemsCount(player, FRAGMENT_BODY) >= 10) && (getQuestItemsCount(player, FRAGMENT_HORN) >= 10);
	}
}
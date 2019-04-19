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
package quests.Q00139_ShadowFoxPart1;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00138_TempleChampionPart2.Q00138_TempleChampionPart2;

/**
 * Shadow Fox - 1 (139)
 * @author Nono
 */
public class Q00139_ShadowFoxPart1 extends Quest
{
	// NPC
	private static final int MIA = 30896;
	// Monsters
	private static final int MOBS[] =
	{
		20784, // Tasaba Lizardman
		20785, // Tasaba Lizardman Shaman
		21639, // Tasaba Lizardman
		21640, // Tasaba Lizardman Shaman
	};
	// Items
	private static final int FRAGMENT = 10345;
	private static final int CHEST = 10346;
	// Misc
	private static final int MIN_LEVEL = 37;
	private static final int MAX_REWARD_LEVEL = 42;
	private static final int DROP_CHANCE = 68;
	
	public Q00139_ShadowFoxPart1()
	{
		super(139);
		addStartNpc(MIA);
		addTalkId(MIA);
		addKillId(MOBS);
		registerQuestItems(FRAGMENT, CHEST);
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
			case "30896-02.htm":
			{
				if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "30896-03.htm";
				}
				break;
			}
			case "30896-04.htm":
			{
				qs.startQuest();
				break;
			}
			case "30896-11.html":
			{
				qs.set("talk", "1");
				break;
			}
			case "30896-13.html":
			{
				qs.setCond(2, true);
				qs.unset("talk");
				break;
			}
			case "30896-17.html":
			{
				if (getRandom(20) < 3)
				{
					takeItems(player, FRAGMENT, 10);
					takeItems(player, CHEST, 1);
					return "30896-16.html";
				}
				takeItems(player, FRAGMENT, -1);
				takeItems(player, CHEST, -1);
				qs.set("talk", "1");
				break;
			}
			case "30896-19.html":
			{
				giveAdena(player, 14050, true);
				if (player.getLevel() <= MAX_REWARD_LEVEL)
				{
					addExpAndSp(player, 30000, 2000);
				}
				qs.exitQuest(false, true);
				break;
			}
			case "30896-06.html":
			case "30896-07.html":
			case "30896-08.html":
			case "30896-09.html":
			case "30896-10.html":
			case "30896-12.html":
			case "30896-18.html":
			{
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
		final L2PcInstance member = getRandomPartyMember(player, 2);
		if (member == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		final QuestState st = getQuestState(member, false);
		if (!st.isSet("talk") && (getRandom(100) < DROP_CHANCE))
		{
			final int itemId = (getRandom(11) == 0) ? CHEST : FRAGMENT;
			giveItems(player, itemId, 1);
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
				final QuestState qst = player.getQuestState(Q00138_TempleChampionPart2.class.getSimpleName());
				htmltext = ((qst != null) && qst.isCompleted()) ? "30896-01.htm" : "30896-00.html";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = qs.isSet("talk") ? "30896-11.html" : "30896-05.html";
						break;
					}
					case 2:
					{
						htmltext = qs.isSet("talk") ? "30896-18.html" : ((getQuestItemsCount(player, FRAGMENT) >= 10) && (getQuestItemsCount(player, CHEST) >= 1)) ? "30896-15.html" : "30896-14.html";
						break;
					}
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
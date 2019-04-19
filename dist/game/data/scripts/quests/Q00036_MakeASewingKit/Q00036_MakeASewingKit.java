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
package quests.Q00036_MakeASewingKit;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Make a Sewing Kit (36)
 * @author malyelfik
 */
public class Q00036_MakeASewingKit extends Quest
{
	// NPC
	private static final int FERRIS = 30847;
	// Monster
	private static final int ENCHANTED_IRON_GOLEM = 20566;
	// Items
	private static final int ARTISANS_FRAME = 1891;
	private static final int ORIHARUKON = 1893;
	private static final int SEWING_KIT = 7078;
	private static final int ENCHANTED_IRON = 7163;
	// Misc
	private static final int MIN_LEVEL = 60;
	private static final int IRON_COUNT = 5;
	private static final int COUNT = 10;
	
	public Q00036_MakeASewingKit()
	{
		super(36);
		addStartNpc(FERRIS);
		addTalkId(FERRIS);
		addKillId(ENCHANTED_IRON_GOLEM);
		registerQuestItems(ENCHANTED_IRON);
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
			case "30847-03.htm":
			{
				qs.startQuest();
				break;
			}
			case "30847-06.html":
			{
				if (getQuestItemsCount(player, ENCHANTED_IRON) < IRON_COUNT)
				{
					return getNoQuestMsg(player);
				}
				takeItems(player, ENCHANTED_IRON, -1);
				qs.setCond(3, true);
				break;
			}
			case "30847-09.html":
			{
				if ((getQuestItemsCount(player, ARTISANS_FRAME) >= COUNT) && (getQuestItemsCount(player, ORIHARUKON) >= COUNT))
				{
					takeItems(player, ARTISANS_FRAME, 10);
					takeItems(player, ORIHARUKON, 10);
					giveItems(player, SEWING_KIT, 1);
					qs.exitQuest(false, true);
				}
				else
				{
					htmltext = "30847-10.html";
				}
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
		final L2PcInstance member = getRandomPartyMember(player, 1);
		if ((member != null) && getRandomBoolean())
		{
			giveItems(player, ENCHANTED_IRON, 1);
			if (getQuestItemsCount(player, ENCHANTED_IRON) >= IRON_COUNT)
			{
				getQuestState(member, false).setCond(2, true);
			}
			else
			{
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "30847-01.htm" : "30847-02.html";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "30847-04.html";
						break;
					}
					case 2:
					{
						htmltext = "30847-05.html";
						break;
					}
					case 3:
					{
						htmltext = ((getQuestItemsCount(player, ARTISANS_FRAME) >= COUNT) && (getQuestItemsCount(player, ORIHARUKON) >= COUNT)) ? "30847-07.html" : "30847-08.html";
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
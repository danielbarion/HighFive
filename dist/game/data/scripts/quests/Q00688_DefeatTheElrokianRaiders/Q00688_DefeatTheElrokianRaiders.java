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
package quests.Q00688_DefeatTheElrokianRaiders;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Defeat the Elrokian Raiders! (688)
 * @author Adry_85
 */
public class Q00688_DefeatTheElrokianRaiders extends Quest
{
	// NPCs
	private static final int ELROKI = 22214;
	private static final int DINN = 32105;
	// Item
	private static final int DINOSAUR_FANG_NECKLACE = 8785;
	// Misc
	private static final int MIN_LEVEL = 75;
	private static final int DROP_RATE = 448;
	
	public Q00688_DefeatTheElrokianRaiders()
	{
		super(688);
		addStartNpc(DINN);
		addTalkId(DINN);
		addKillId(ELROKI);
		registerQuestItems(DINOSAUR_FANG_NECKLACE);
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
			case "32105-02.htm":
			case "32105-10.html":
			{
				htmltext = event;
				break;
			}
			case "32105-03.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32105-06.html":
			{
				if (hasQuestItems(player, DINOSAUR_FANG_NECKLACE))
				{
					giveAdena(player, 3000 * getQuestItemsCount(player, DINOSAUR_FANG_NECKLACE), true);
					takeItems(player, DINOSAUR_FANG_NECKLACE, -1);
					htmltext = event;
				}
				break;
			}
			case "donation":
			{
				if (getQuestItemsCount(player, DINOSAUR_FANG_NECKLACE) < 100)
				{
					htmltext = "32105-07.html";
				}
				else
				{
					if (getRandom(1000) < 500)
					{
						giveAdena(player, 450000, true);
						htmltext = "32105-08.html";
					}
					else
					{
						giveAdena(player, 150000, true);
						htmltext = "32105-09.html";
					}
					takeItems(player, DINOSAUR_FANG_NECKLACE, 100);
				}
				break;
			}
			case "32105-11.html":
			{
				if (hasQuestItems(player, DINOSAUR_FANG_NECKLACE))
				{
					giveAdena(player, 3000 * getQuestItemsCount(player, DINOSAUR_FANG_NECKLACE), true);
				}
				qs.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 1);
		if (partyMember == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		
		final float chance = DROP_RATE * Config.RATE_QUEST_DROP;
		if (getRandom(1000) < chance)
		{
			rewardItems(partyMember, DINOSAUR_FANG_NECKLACE, 1);
			playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
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
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "32105-01.htm" : "32105-04.html";
				break;
			}
			case State.STARTED:
			{
				htmltext = hasQuestItems(player, DINOSAUR_FANG_NECKLACE) ? "32105-05.html" : "32105-12.html";
				break;
			}
		}
		return htmltext;
	}
}

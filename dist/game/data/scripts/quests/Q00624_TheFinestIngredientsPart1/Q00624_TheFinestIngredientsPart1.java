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
package quests.Q00624_TheFinestIngredientsPart1;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * The Finest Ingredients - Part 1.
 * @author Citizen, jurchiks, xban1x
 */
public final class Q00624_TheFinestIngredientsPart1 extends Quest
{
	// NPC
	private static final int JEREMY = 31521;
	// Items
	private static final int TRUNK_OF_NEPENTHES = 7202;
	private static final int FOOT_OF_BANDERSNATCHLING = 7203;
	private static final int SECRET_SPICE = 7204;
	// Rewards
	private static final int ICE_CRYSTAL = 7080;
	private static final int SOY_SAUCE_JAR = 7205;
	// Misc
	private static final int MIN_LVL = 73;
	private static final Map<Integer, Integer> MONSTER_DROPS = new HashMap<>();
	static
	{
		MONSTER_DROPS.put(21314, FOOT_OF_BANDERSNATCHLING); // Hot Springs Bandersnatchling
		MONSTER_DROPS.put(21317, SECRET_SPICE); // Hot Springs Atroxspawn
		MONSTER_DROPS.put(21319, TRUNK_OF_NEPENTHES); // Hot Springs Nepenthes
		MONSTER_DROPS.put(21321, SECRET_SPICE); // Hot Springs Atrox
	}
	
	public Q00624_TheFinestIngredientsPart1()
	{
		super(624);
		addStartNpc(JEREMY);
		addTalkId(JEREMY);
		addKillId(MONSTER_DROPS.keySet());
		registerQuestItems(TRUNK_OF_NEPENTHES, FOOT_OF_BANDERSNATCHLING, SECRET_SPICE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		String htmltext = null;
		if (st != null)
		{
			switch (event)
			{
				case "31521-02.htm":
				{
					st.startQuest();
					htmltext = event;
					break;
				}
				case "31521-05.html":
				{
					if (st.isCond(2) && (getQuestItemsCount(player, getRegisteredItemIds()) == 150))
					{
						giveItems(player, ICE_CRYSTAL, 1);
						giveItems(player, SOY_SAUCE_JAR, 1);
						st.exitQuest(true, true);
						htmltext = "31521-05.html";
					}
					else
					{
						htmltext = "31521-06.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(killer, 1);
		if ((partyMember != null) && partyMember.isInsideRadius3D(npc, Config.ALT_PARTY_RANGE))
		{
			final int item = MONSTER_DROPS.get(npc.getId());
			final long count = getQuestItemsCount(partyMember, item);
			if ((count + 1) >= 50)
			{
				if (count < 50)
				{
					giveItems(partyMember, item, 50 - count);
					playSound(partyMember, QuestSound.ITEMSOUND_QUEST_FANFARE_MIDDLE);
				}
				if (getQuestItemsCount(partyMember, getRegisteredItemIds()) == 150)
				{
					getQuestState(partyMember, false).setCond(2, true);
				}
			}
			else
			{
				giveItems(partyMember, item, 1);
				playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				htmltext = (player.getLevel() >= MIN_LVL) ? "31521-01.htm" : "31521-00.htm";
				break;
			}
			case State.STARTED:
			{
				switch (st.getCond())
				{
					case 1:
					{
						htmltext = "31521-03.html";
						break;
					}
					case 2:
					{
						htmltext = "31521-04.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
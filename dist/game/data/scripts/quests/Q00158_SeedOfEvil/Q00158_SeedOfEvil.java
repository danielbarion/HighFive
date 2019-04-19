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
package quests.Q00158_SeedOfEvil;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

/**
 * Seed of Evil (158)
 * @author malyelfik
 */
public class Q00158_SeedOfEvil extends Quest
{
	// NPC
	private static final int BIOTIN = 30031;
	// Monster
	private static final int NERKAS = 27016;
	// Items
	private static final int ENCHANT_ARMOR_D = 956;
	private static final int CLAY_TABLET = 1025;
	// Misc
	private static final int MIN_LEVEL = 21;
	
	public Q00158_SeedOfEvil()
	{
		super(158);
		addStartNpc(BIOTIN);
		addTalkId(BIOTIN);
		addAttackId(NERKAS);
		addKillId(NERKAS);
		registerQuestItems(CLAY_TABLET);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && event.equalsIgnoreCase("30031-03.htm"))
		{
			st.startQuest();
			return event;
		}
		return null;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (npc.isScriptValue(0))
		{
			npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.HOW_DARE_YOU_CHALLENGE_ME));
			npc.setScriptValue(1);
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState st = getQuestState(killer, false);
		if ((st != null) && !hasQuestItems(killer, CLAY_TABLET))
		{
			giveItems(killer, CLAY_TABLET, 1);
			st.setCond(2, true);
		}
		npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.THE_POWER_OF_LORD_BELETH_RULES_THE_WHOLE_WORLD));
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
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "30031-02.htm" : "30031-01.html";
				break;
			}
			case State.STARTED:
			{
				if (st.isCond(1))
				{
					htmltext = "30031-04.html";
				}
				else if (st.isCond(2) && hasQuestItems(player, CLAY_TABLET))
				{
					giveItems(player, ENCHANT_ARMOR_D, 1);
					addExpAndSp(player, 17818, 927);
					giveAdena(player, 1495, true);
					st.exitQuest(false, true);
					htmltext = "30031-05.html";
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
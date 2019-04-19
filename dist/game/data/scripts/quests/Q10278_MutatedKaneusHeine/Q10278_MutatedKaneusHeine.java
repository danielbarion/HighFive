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
package quests.Q10278_MutatedKaneusHeine;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Mutated Kaneus - Heine (10278)<br>
 * Original Jython script by Gnacik on 2010-06-29.
 * @author nonom
 */
public class Q10278_MutatedKaneusHeine extends Quest
{
	// NPCs
	private static final int GOSTA = 30916;
	private static final int MINEVIA = 30907;
	private static final int BLADE_OTIS = 18562;
	private static final int WEIRD_BUNEI = 18564;
	// Items
	private static final int TISSUE_BO = 13834;
	private static final int TISSUE_WB = 13835;
	
	public Q10278_MutatedKaneusHeine()
	{
		super(10278);
		addStartNpc(GOSTA);
		addTalkId(GOSTA, MINEVIA);
		addKillId(BLADE_OTIS, WEIRD_BUNEI);
		registerQuestItems(TISSUE_BO, TISSUE_WB);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "30916-03.htm":
			{
				st.startQuest();
				break;
			}
			case "30907-03.htm":
			{
				giveAdena(player, 50000, true);
				st.exitQuest(false, true);
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		QuestState st = getQuestState(killer, false);
		if (st == null)
		{
			return null;
		}
		
		final int npcId = npc.getId();
		if (killer.getParty() != null)
		{
			final List<L2PcInstance> PartyMembers = new ArrayList<>();
			for (L2PcInstance member : killer.getParty().getMembers())
			{
				st = getQuestState(member, false);
				if ((st != null) && st.isStarted() && (((npcId == BLADE_OTIS) && !hasQuestItems(member, TISSUE_BO)) || ((npcId == WEIRD_BUNEI) && !hasQuestItems(member, TISSUE_WB))))
				{
					PartyMembers.add(member);
				}
			}
			
			if (!PartyMembers.isEmpty())
			{
				rewardItem(npcId, PartyMembers.get(getRandom(PartyMembers.size())));
			}
		}
		else if (st.isStarted())
		{
			rewardItem(npcId, killer);
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
			case GOSTA:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() > 37) ? "30916-01.htm" : "30916-00.htm";
						break;
					}
					case State.STARTED:
					{
						htmltext = (hasQuestItems(player, TISSUE_BO) && hasQuestItems(player, TISSUE_WB)) ? "30916-05.htm" : "30916-04.htm";
						break;
					}
					case State.COMPLETED:
					{
						htmltext = "30916-06.htm";
						break;
					}
				}
				break;
			}
			case MINEVIA:
			{
				switch (st.getState())
				{
					case State.STARTED:
					{
						htmltext = (hasQuestItems(player, TISSUE_BO) && hasQuestItems(player, TISSUE_WB)) ? "30907-02.htm" : "30907-01.htm";
						break;
					}
					case State.COMPLETED:
					{
						htmltext = getAlreadyCompletedMsg(player);
						break;
					}
					default:
					{
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	/**
	 * @param npcId the ID of the killed monster
	 * @param player
	 */
	private final void rewardItem(int npcId, L2PcInstance player)
	{
		if ((npcId == BLADE_OTIS) && !hasQuestItems(player, TISSUE_BO))
		{
			giveItems(player, TISSUE_BO, 1);
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		else if ((npcId == WEIRD_BUNEI) && !hasQuestItems(player, TISSUE_WB))
		{
			giveItems(player, TISSUE_WB, 1);
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
	}
}

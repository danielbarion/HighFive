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
package quests.Q00453_NotStrongEnoughAlone;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.serverpackets.ExQuestNpcLogList;
import com.l2jmobius.gameserver.util.Util;

import quests.Q10282_ToTheSeedOfAnnihilation.Q10282_ToTheSeedOfAnnihilation;

/**
 * Not Strong Enough Alone (453)
 * @author malyelfik
 */
public class Q00453_NotStrongEnoughAlone extends Quest
{
	// NPC
	private static final int KLEMIS = 32734;
	private static final int[] MONSTER1 =
	{
		22746,
		22747,
		22748,
		22749,
		22750,
		22751,
		22752,
		22753
	};
	private static final int[] MONSTER2 =
	{
		22754,
		22755,
		22756,
		22757,
		22758,
		22759
	};
	private static final int[] MONSTER3 =
	{
		22760,
		22761,
		22762,
		22763,
		22764,
		22765
	};
	// Reward
	private static final int[][] REWARD =
	{
		{
			15815,
			15816,
			15817,
			15818,
			15819,
			15820,
			15821,
			15822,
			15823,
			15824,
			15825
		},
		{
			15634,
			15635,
			15636,
			15637,
			15638,
			15639,
			15640,
			15641,
			15642,
			15643,
			15644
		}
	};
	
	public Q00453_NotStrongEnoughAlone()
	{
		super(453);
		addStartNpc(KLEMIS);
		addTalkId(KLEMIS);
		addKillId(MONSTER1);
		addKillId(MONSTER2);
		addKillId(MONSTER3);
	}
	
	private void increaseKill(L2PcInstance player, L2Npc npc)
	{
		final QuestState qs = getQuestState(player, false);
		
		if (qs == null)
		{
			return;
		}
		
		int npcId = npc.getId();
		
		if (Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			final ExQuestNpcLogList log = new ExQuestNpcLogList(getId());
			
			if (CommonUtil.contains(MONSTER1, npcId) && qs.isCond(2))
			{
				if (npcId == MONSTER1[4])
				{
					npcId = MONSTER1[0];
				}
				else if (npcId == MONSTER1[5])
				{
					npcId = MONSTER1[1];
				}
				else if (npcId == MONSTER1[6])
				{
					npcId = MONSTER1[2];
				}
				else if (npcId == MONSTER1[7])
				{
					npcId = MONSTER1[3];
				}
				
				final int i = qs.getInt(String.valueOf(npcId));
				if (i < 15)
				{
					qs.set(Integer.toString(npcId), Integer.toString(i + 1));
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				
				checkProgress(qs, 15, MONSTER1[0], MONSTER1[1], MONSTER1[2], MONSTER1[3]);
				
				log.addNpc(MONSTER1[0], qs.getInt(String.valueOf(MONSTER1[0])));
				log.addNpc(MONSTER1[1], qs.getInt(String.valueOf(MONSTER1[1])));
				log.addNpc(MONSTER1[2], qs.getInt(String.valueOf(MONSTER1[2])));
				log.addNpc(MONSTER1[3], qs.getInt(String.valueOf(MONSTER1[3])));
			}
			else if (CommonUtil.contains(MONSTER2, npcId) && qs.isCond(3))
			{
				if (npcId == MONSTER2[3])
				{
					npcId = MONSTER2[0];
				}
				else if (npcId == MONSTER2[4])
				{
					npcId = MONSTER2[1];
				}
				else if (npcId == MONSTER2[5])
				{
					npcId = MONSTER2[2];
				}
				
				final int i = qs.getInt(String.valueOf(npcId));
				if (i < 20)
				{
					qs.set(Integer.toString(npcId), Integer.toString(i + 1));
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				
				checkProgress(qs, 20, MONSTER2[0], MONSTER2[1], MONSTER2[2]);
				
				log.addNpc(MONSTER2[0], qs.getInt(String.valueOf(MONSTER2[0])));
				log.addNpc(MONSTER2[1], qs.getInt(String.valueOf(MONSTER2[1])));
				log.addNpc(MONSTER2[2], qs.getInt(String.valueOf(MONSTER2[2])));
			}
			else if (CommonUtil.contains(MONSTER3, npcId) && qs.isCond(4))
			{
				if (npcId == MONSTER3[3])
				{
					npcId = MONSTER3[0];
				}
				else if (npcId == MONSTER3[4])
				{
					npcId = MONSTER3[1];
				}
				else if (npcId == MONSTER3[5])
				{
					npcId = MONSTER3[2];
				}
				
				final int i = qs.getInt(String.valueOf(npcId));
				if (i < 20)
				{
					qs.set(Integer.toString(npcId), Integer.toString(i + 1));
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				
				checkProgress(qs, 20, MONSTER3[0], MONSTER3[1], MONSTER3[2]);
				
				log.addNpc(MONSTER3[0], qs.getInt(String.valueOf(MONSTER3[0])));
				log.addNpc(MONSTER3[1], qs.getInt(String.valueOf(MONSTER3[1])));
				log.addNpc(MONSTER3[2], qs.getInt(String.valueOf(MONSTER3[2])));
			}
			player.sendPacket(log);
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final String htmltext = event;
		final QuestState qs = getQuestState(player, false);
		
		if (qs == null)
		{
			return htmltext;
		}
		
		if (event.equalsIgnoreCase("32734-06.htm"))
		{
			qs.startQuest();
		}
		else if (event.equalsIgnoreCase("32734-07.html"))
		{
			qs.setCond(2, true);
		}
		else if (event.equalsIgnoreCase("32734-08.html"))
		{
			qs.setCond(3, true);
		}
		else if (event.equalsIgnoreCase("32734-09.html"))
		{
			qs.setCond(4, true);
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		if (player.getParty() != null)
		{
			for (L2PcInstance member : player.getParty().getMembers())
			{
				increaseKill(member, npc);
			}
		}
		else
		{
			increaseKill(player, npc);
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		final QuestState prev = player.getQuestState(Q10282_ToTheSeedOfAnnihilation.class.getSimpleName());
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				if ((player.getLevel() >= 84) && (prev != null) && prev.isCompleted())
				{
					htmltext = "32734-01.htm";
				}
				else
				{
					htmltext = "32734-03.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "32734-10.html";
						break;
					}
					case 2:
					{
						htmltext = "32734-11.html";
						break;
					}
					case 3:
					{
						htmltext = "32734-12.html";
						break;
					}
					case 4:
					{
						htmltext = "32734-13.html";
						break;
					}
					case 5:
					{
						giveItems(player, REWARD[getRandom(REWARD.length)][getRandom(REWARD[0].length)], 1);
						qs.exitQuest(QuestType.DAILY, true);
						htmltext = "32734-14.html";
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				if (!qs.isNowAvailable())
				{
					htmltext = "32734-02.htm";
				}
				else
				{
					qs.setState(State.CREATED);
					if ((player.getLevel() >= 84) && (prev != null) && (prev.getState() == State.COMPLETED))
					{
						htmltext = "32734-01.htm";
					}
					else
					{
						htmltext = "32734-03.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	private static void checkProgress(QuestState qs, int count, int... mobs)
	{
		for (int mob : mobs)
		{
			if (qs.getInt(String.valueOf(mob)) < count)
			{
				return;
			}
		}
		qs.setCond(5, true);
	}
}

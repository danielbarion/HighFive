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
package quests.Q00714_PathToBecomingALordSchuttgart;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.CastleManager;
import com.l2jmobius.gameserver.instancemanager.FortManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.entity.Castle;
import com.l2jmobius.gameserver.model.entity.Fort;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

import quests.Q00114_ResurrectionOfAnOldManager.Q00114_ResurrectionOfAnOldManager;
import quests.Q00120_PavelsLastResearch.Q00120_PavelsLastResearch;
import quests.Q00121_PavelTheGiant.Q00121_PavelTheGiant;

public class Q00714_PathToBecomingALordSchuttgart extends Quest
{
	private static final int August = 35555;
	private static final int Newyear = 31961;
	private static final int Yasheni = 31958;
	private static final int GolemShard = 17162;
	
	private static final int ShuttgartCastle = 9;
	
	public Q00714_PathToBecomingALordSchuttgart()
	{
		super(714);
		addStartNpc(August);
		addTalkId(August);
		addTalkId(Newyear);
		addTalkId(Yasheni);
		for (int i = 22801; i < 22812; i++)
		{
			addKillId(i);
		}
		_questItemIds = new int[]
		{
			GolemShard
		};
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = player.getQuestState(getName());
		final Castle castle = CastleManager.getInstance().getCastleById(ShuttgartCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		
		if (event.equals("august_q714_03.htm"))
		{
			qs.startQuest();
		}
		else if (event.equals("august_q714_05.htm"))
		{
			qs.setCond(2);
		}
		else if (event.equals("newyear_q714_03.htm"))
		{
			qs.setCond(3);
		}
		else if (event.equals("yasheni_q714_02.htm"))
		{
			qs.setCond(5);
		}
		else if (event.equals("august_q714_08.htm"))
		{
			if (castle.getOwner().getLeader().getPlayerInstance() != null)
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_SCHUTTGART_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_SCHUTTGART);
				packet.addStringParameter(player.getName());
				npc.broadcastPacket(packet);
				
				/**
				 * Territory terr = TerritoryWarManager.getInstance().getTerritory(castle.getId()); terr.setLordId(castleOwner.getObjectId()); terr.updateDataInDB(); terr.updateState();
				 */
				
				qs.exitQuest(true, true);
			}
		}
		return event;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		final Castle castle = CastleManager.getInstance().getCastleById(ShuttgartCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		
		switch (npc.getId())
		{
			case August:
			{
				if (qs.isCond(0))
				{
					if (castleOwner == qs.getPlayer())
					{
						if (!hasFort())
						{
							htmltext = "august_q714_01.htm";
						}
						else
						{
							htmltext = "august_q714_00.htm";
							qs.exitQuest(true);
						}
					}
					else
					{
						htmltext = "august_q714_00a.htm";
						qs.exitQuest(true);
					}
				}
				else if (qs.isCond(1))
				{
					htmltext = "august_q714_04.htm";
				}
				else if (qs.isCond(2))
				{
					htmltext = "august_q714_06.htm";
				}
				else if (qs.isCond(7))
				{
					htmltext = "august_q714_07.htm";
				}
				break;
			}
			case Newyear:
			{
				if (qs.isCond(2))
				{
					htmltext = "newyear_q714_01.htm";
				}
				else if (qs.isCond(3))
				{
					final QuestState q1 = qs.getPlayer().getQuestState(Q00114_ResurrectionOfAnOldManager.class.getSimpleName());
					final QuestState q2 = qs.getPlayer().getQuestState(Q00120_PavelsLastResearch.class.getSimpleName());
					final QuestState q3 = qs.getPlayer().getQuestState(Q00121_PavelTheGiant.class.getSimpleName());
					if ((q3 != null) && q3.isCompleted())
					{
						if ((q1 != null) && q1.isCompleted())
						{
							if ((q2 != null) && q2.isCompleted())
							{
								qs.setCond(4);
								htmltext = "newyear_q714_04.htm";
							}
							else
							{
								htmltext = "newyear_q714_04a.htm";
							}
						}
						else
						{
							htmltext = "newyear_q714_04b.htm";
						}
					}
					else
					{
						htmltext = "newyear_q714_04c.htm";
					}
				}
				break;
			}
			case Yasheni:
			{
				if (qs.isCond(4))
				{
					htmltext = "yasheni_q714_01.htm";
				}
				else if (qs.isCond(5))
				{
					htmltext = "yasheni_q714_03.htm";
				}
				else if (qs.isCond(6))
				{
					takeItems(player, GolemShard, -1);
					qs.setCond(7);
					htmltext = "yasheni_q714_04.htm";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance killer, boolean isPet)
	{
		final QuestState qs = killer.getQuestState(getName());
		if ((qs != null) && qs.isCond(5))
		{
			if (getQuestItemsCount(killer, GolemShard) < 300)
			{
				giveItems(killer, GolemShard, 1);
			}
			if (getQuestItemsCount(killer, GolemShard) >= 300)
			{
				qs.setCond(6);
			}
		}
		return null;
	}
	
	private boolean hasFort()
	{
		for (Fort fortress : FortManager.getInstance().getForts())
		{
			if (fortress.getContractedCastleId() == ShuttgartCastle)
			{
				return true;
			}
		}
		return false;
	}
}
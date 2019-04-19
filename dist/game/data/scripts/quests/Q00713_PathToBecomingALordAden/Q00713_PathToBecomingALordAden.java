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
package quests.Q00713_PathToBecomingALordAden;

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

public class Q00713_PathToBecomingALordAden extends Quest
{
	private static final int Logan = 35274;
	private static final int Orven = 30857;
	private static final int[] Orcs =
	{
		20669,
		20665
	};
	
	private static final int AdenCastle = 5;
	
	public Q00713_PathToBecomingALordAden()
	{
		super(713);
		addStartNpc(Logan);
		addTalkId(Logan);
		addTalkId(Orven);
		addKillId(Orcs);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = player.getQuestState(getName());
		final Castle castle = CastleManager.getInstance().getCastleById(AdenCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		
		if (event.equals("logan_q713_02.htm"))
		{
			qs.startQuest();
		}
		else if (event.equals("orven_q713_03.htm"))
		{
			qs.setCond(2);
		}
		else if (event.equals("logan_q713_05.htm"))
		{
			if (castle.getOwner().getLeader().getPlayerInstance() != null)
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_ADEN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_ADEN);
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
		final Castle castle = CastleManager.getInstance().getCastleById(AdenCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		
		switch (npc.getId())
		{
			case Logan:
			{
				if (qs.isCond(0))
				{
					if (castleOwner == qs.getPlayer())
					{
						if (!hasFort())
						{
							htmltext = "logan_q713_01.htm";
						}
						else
						{
							htmltext = "logan_q713_00.htm";
							qs.exitQuest(true);
						}
					}
					else
					{
						htmltext = "logan_q713_00a.htm";
						qs.exitQuest(true);
					}
				}
				else if (qs.isCond(1))
				{
					htmltext = "logan_q713_03.htm";
				}
				else if (qs.isCond(7))
				{
					htmltext = "logan_q713_04.htm";
				}
				break;
			}
			case Orven:
			{
				if (qs.isCond(1))
				{
					htmltext = "orven_q713_01.htm";
				}
				else if (qs.isCond(2))
				{
					htmltext = "orven_q713_04.htm";
				}
				else if (qs.isCond(4))
				{
					htmltext = "orven_q713_05.htm";
				}
				else if (qs.isCond(5))
				{
					qs.setCond(7);
					htmltext = "orven_q713_06.htm";
				}
				else if (qs.isCond(7))
				{
					htmltext = "orven_q713_06.htm";
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
		if ((qs != null) && qs.isCond(4))
		{
			if (qs.getInt("mobs") < 100)
			{
				qs.set("mobs", String.valueOf(qs.getInt("mobs") + 1));
			}
			else
			{
				qs.setCond(5);
			}
		}
		return null;
	}
	
	private boolean hasFort()
	{
		for (Fort fortress : FortManager.getInstance().getForts())
		{
			if (fortress.getContractedCastleId() == AdenCastle)
			{
				return true;
			}
		}
		return false;
	}
}
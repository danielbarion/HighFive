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
package quests.Q00715_PathToBecomingALordGoddard;

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

public class Q00715_PathToBecomingALordGoddard extends Quest
{
	private static final int Alfred = 35363;
	
	private static final int WaterSpiritAshutar = 25316;
	private static final int FireSpiritNastron = 25306;
	
	private static final int GoddardCastle = 7;
	
	public Q00715_PathToBecomingALordGoddard()
	{
		super(715);
		addStartNpc(Alfred);
		addTalkId(Alfred);
		addKillId(WaterSpiritAshutar);
		addKillId(FireSpiritNastron);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = player.getQuestState(getName());
		final Castle castle = CastleManager.getInstance().getCastleById(GoddardCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		
		if (event.equals("alfred_q715_03.htm"))
		{
			qs.startQuest();
		}
		else if (event.equals("alfred_q715_04a.htm"))
		{
			qs.setCond(3);
		}
		else if (event.equals("alfred_q715_04b.htm"))
		{
			qs.setCond(2);
		}
		else if (event.equals("alfred_q715_08.htm"))
		{
			if (castle.getOwner().getLeader().getPlayerInstance() != null)
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_GODDARD_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_GODDARD);
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
		final Castle castle = CastleManager.getInstance().getCastleById(GoddardCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		
		if (qs.isCond(0))
		{
			if (castleOwner == qs.getPlayer())
			{
				if (!hasFort())
				{
					htmltext = "alfred_q715_01.htm";
				}
				else
				{
					htmltext = "alfred_q715_00.htm";
					qs.exitQuest(true);
				}
			}
			else
			{
				htmltext = "alfred_q715_00a.htm";
				qs.exitQuest(true);
			}
		}
		else if (qs.isCond(1))
		{
			htmltext = "alfred_q715_03.htm";
		}
		else if (qs.isCond(2))
		{
			htmltext = "alfred_q715_05b.htm";
		}
		else if (qs.isCond(3))
		{
			htmltext = "alfred_q715_05a.htm";
		}
		else if (qs.isCond(4))
		{
			qs.setCond(6);
			htmltext = "alfred_q715_06b.htm";
		}
		else if (qs.isCond(5))
		{
			qs.setCond(7);
			htmltext = "alfred_q715_06a.htm";
		}
		else if (qs.isCond(6))
		{
			htmltext = "alfred_q715_06b.htm";
		}
		else if (qs.isCond(7))
		{
			htmltext = "alfred_q715_06a.htm";
		}
		else if (qs.isCond(8) || qs.isCond(9))
		{
			htmltext = "alfred_q715_07.htm";
		}
		return htmltext;
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		final QuestState qs = player.getQuestState(getName());
		if (qs == null)
		{
			return null;
		}
		if (qs.isCond(2) && (npc.getId() == FireSpiritNastron))
		{
			qs.setCond(4);
		}
		else if (qs.isCond(3) && (npc.getId() == WaterSpiritAshutar))
		{
			qs.setCond(5);
		}
		
		if (qs.isCond(6) && (npc.getId() == WaterSpiritAshutar))
		{
			qs.setCond(9);
		}
		else if (qs.isCond(7) && (npc.getId() == FireSpiritNastron))
		{
			qs.setCond(8);
		}
		return null;
	}
	
	private boolean hasFort()
	{
		for (Fort fortress : FortManager.getInstance().getForts())
		{
			if (fortress.getContractedCastleId() == GoddardCastle)
			{
				return true;
			}
		}
		return false;
	}
}
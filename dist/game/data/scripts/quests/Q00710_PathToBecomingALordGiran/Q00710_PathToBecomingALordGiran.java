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
package quests.Q00710_PathToBecomingALordGiran;

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

public class Q00710_PathToBecomingALordGiran extends Quest
{
	private static final int Saul = 35184;
	private static final int Gesto = 30511;
	private static final int Felton = 30879;
	private static final int CargoBox = 32243;
	
	private static final int FreightChest = 13014;
	private static final int GestoBox = 13013;
	
	private static final int[] Mobs =
	{
		20832,
		20833,
		20835,
		21602,
		21603,
		21604,
		21605,
		21606,
		21607,
		21608,
		21609
	};
	
	private static final int GiranCastle = 3;
	
	public Q00710_PathToBecomingALordGiran()
	{
		super(710);
		addStartNpc(Saul);
		addTalkId(Saul);
		addTalkId(Gesto);
		addTalkId(Felton);
		addTalkId(CargoBox);
		_questItemIds = new int[]
		{
			FreightChest,
			GestoBox
		};
		addKillId(Mobs);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = player.getQuestState(getName());
		final Castle castle = CastleManager.getInstance().getCastleById(GiranCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		if (event.equals("saul_q710_03.htm"))
		{
			qs.startQuest();
		}
		else if (event.equals("gesto_q710_03.htm"))
		{
			qs.setCond(3);
		}
		else if (event.equals("felton_q710_02.htm"))
		{
			qs.setCond(4);
		}
		else if (event.equals("saul_q710_07.htm"))
		{
			if (castle.getOwner().getLeader().getPlayerInstance() != null)
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_GIRAN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_GIRAN);
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
		final Castle castle = CastleManager.getInstance().getCastleById(GiranCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		switch (npc.getId())
		{
			case Saul:
			{
				if (qs.isCond(0))
				{
					if (castleOwner == qs.getPlayer())
					{
						if (!hasFort())
						{
							htmltext = "saul_q710_01.htm";
						}
						else
						{
							htmltext = "saul_q710_00.htm";
							qs.exitQuest(true);
						}
					}
					else
					{
						htmltext = "saul_q710_00a.htm";
						qs.exitQuest(true);
					}
				}
				else if (qs.isCond(1))
				{
					qs.setCond(2);
					htmltext = "saul_q710_04.htm";
				}
				else if (qs.isCond(2))
				{
					htmltext = "saul_q710_05.htm";
				}
				else if (qs.isCond(9))
				{
					htmltext = "saul_q710_06.htm";
				}
				break;
			}
			case Gesto:
			{
				if (qs.isCond(2))
				{
					htmltext = "gesto_q710_01.htm";
				}
				else if (qs.isCond(3) || qs.isCond(4))
				{
					htmltext = "gesto_q710_04.htm";
				}
				else if (qs.isCond(5))
				{
					takeItems(player, FreightChest, -1);
					qs.setCond(7);
					htmltext = "gesto_q710_05.htm";
				}
				else if (qs.isCond(7))
				{
					htmltext = "gesto_q710_06.htm";
				}
				else if (qs.isCond(8))
				{
					takeItems(player, GestoBox, -1);
					qs.setCond(9);
					htmltext = "gesto_q710_07.htm";
				}
				else if (qs.isCond(9))
				{
					htmltext = "gesto_q710_07.htm";
				}
				break;
			}
			case Felton:
			{
				if (qs.isCond(3))
				{
					htmltext = "felton_q710_01.htm";
				}
				else if (qs.isCond(4))
				{
					htmltext = "felton_q710_03.htm";
				}
				break;
			}
			case CargoBox:
			{
				if (qs.isCond(4))
				{
					qs.setCond(5);
					giveItems(player, FreightChest, 1);
					htmltext = "box_q710_01.htm";
				}
				else if (qs.isCond(5))
				{
					htmltext = "box_q710_02.htm";
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
		
		if ((qs != null) && qs.isCond(7))
		{
			if (getQuestItemsCount(killer, GestoBox) < 300)
			{
				giveItems(killer, GestoBox, 1);
			}
			if (getQuestItemsCount(killer, GestoBox) >= 300)
			{
				qs.setCond(8);
			}
		}
		return null;
	}
	
	private boolean hasFort()
	{
		for (Fort fortress : FortManager.getInstance().getForts())
		{
			if (fortress.getContractedCastleId() == GiranCastle)
			{
				return true;
			}
		}
		return false;
	}
}
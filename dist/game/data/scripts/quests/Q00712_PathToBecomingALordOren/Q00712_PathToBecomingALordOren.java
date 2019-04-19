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
 */package quests.Q00712_PathToBecomingALordOren;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.CastleManager;
import com.l2jmobius.gameserver.instancemanager.FortManager;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.entity.Castle;
import com.l2jmobius.gameserver.model.entity.Fort;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

public class Q00712_PathToBecomingALordOren extends Quest
{
	private static final int Brasseur = 35226;
	private static final int Croop = 30676;
	private static final int Marty = 30169;
	private static final int Valleria = 30176;
	
	private static final int NebuliteOrb = 13851;
	
	private static final int[] OelMahims =
	{
		20575,
		20576
	};
	
	private static final int OrenCastle = 4;
	
	public Q00712_PathToBecomingALordOren()
	{
		super(712);
		addStartNpc(new int[]
		{
			Brasseur,
			Marty
		});
		addTalkId(Brasseur);
		addTalkId(Croop);
		addTalkId(Marty);
		addTalkId(Valleria);
		_questItemIds = new int[]
		{
			NebuliteOrb
		};
		addKillId(OelMahims);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = player.getQuestState(getName());
		final Castle castle = CastleManager.getInstance().getCastleById(OrenCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		if (event.equals("brasseur_q712_03.htm"))
		{
			qs.startQuest();
		}
		else if (event.equals("croop_q712_03.htm"))
		{
			qs.setCond(3);
		}
		else if (event.equals("marty_q712_02.htm"))
		{
			if (isLordAvailable(3, qs))
			{
				castleOwner.getQuestState(getName()).setCond(4);
				qs.setState(State.STARTED);
			}
		}
		else if (event.equals("valleria_q712_02.htm"))
		{
			if (isLordAvailable(4, qs))
			{
				castleOwner.getQuestState(getName()).setCond(5);
				qs.exitQuest(true);
			}
		}
		else if (event.equals("croop_q712_05.htm"))
		{
			qs.setCond(6);
		}
		else if (event.equals("croop_q712_07.htm"))
		{
			takeItems(player, NebuliteOrb, -1);
			qs.setCond(8);
		}
		else if (event.equals("brasseur_q712_06.htm"))
		{
			if (castleOwner != null)
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_OREN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_OREN);
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
		final Castle castle = CastleManager.getInstance().getCastleById(OrenCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		
		switch (npc.getId())
		{
			case Brasseur:
			{
				if (qs.isCond(0))
				{
					if (castleOwner == qs.getPlayer())
					{
						if (!hasFort())
						{
							htmltext = "brasseur_q712_01.htm";
						}
						else
						{
							htmltext = "brasseur_q712_00.htm";
							qs.exitQuest(true);
						}
					}
					else
					{
						htmltext = "brasseur_q712_00a.htm";
						qs.exitQuest(true);
					}
				}
				else if (qs.isCond(1))
				{
					qs.setCond(2);
					htmltext = "brasseur_q712_04.htm";
				}
				else if (qs.isCond(2))
				{
					htmltext = "brasseur_q712_04.htm";
				}
				else if (qs.isCond(8))
				{
					htmltext = "brasseur_q712_05.htm";
				}
				break;
			}
			case Croop:
			{
				if (qs.isCond(2))
				{
					htmltext = "croop_q712_01.htm";
				}
				else if (qs.isCond(3) || qs.isCond(4))
				{
					htmltext = "croop_q712_03.htm";
				}
				else if (qs.isCond(5))
				{
					htmltext = "croop_q712_04.htm";
				}
				else if (qs.isCond(6))
				{
					htmltext = "croop_q712_05.htm";
				}
				else if (qs.isCond(7))
				{
					htmltext = "croop_q712_06.htm";
				}
				else if (qs.isCond(8))
				{
					htmltext = "croop_q712_08.htm";
				}
				break;
			}
			case Marty:
			{
				if (qs.isCond(0))
				{
					if (isLordAvailable(3, qs))
					{
						htmltext = "marty_q712_01.htm";
					}
					else
					{
						htmltext = "marty_q712_00.htm";
					}
				}
				break;
			}
			case Valleria:
			{
				if ((qs.getState() == State.STARTED) && isLordAvailable(4, qs))
				{
					htmltext = "valleria_q712_01.htm";
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
		if ((qs != null) && qs.isCond(6))
		{
			if (getQuestItemsCount(killer, NebuliteOrb) < 300)
			{
				giveItems(killer, NebuliteOrb, 1);
			}
			if (getQuestItemsCount(killer, NebuliteOrb) >= 300)
			{
				qs.setCond(7);
			}
		}
		return null;
	}
	
	private boolean isLordAvailable(int cond, QuestState qs)
	{
		final Castle castle = CastleManager.getInstance().getCastleById(OrenCastle);
		final L2Clan owner = castle.getOwner();
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		if (owner != null)
		{
			if ((castleOwner != null) && (castleOwner != qs.getPlayer()) && (owner == qs.getPlayer().getClan()) && (castleOwner.getQuestState(getName()) != null) && (castleOwner.getQuestState(getName()).isCond(cond)))
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean hasFort()
	{
		for (Fort fortress : FortManager.getInstance().getForts())
		{
			if (fortress.getContractedCastleId() == OrenCastle)
			{
				return true;
			}
		}
		return false;
	}
}

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
package quests.Q00711_PathToBecomingALordInnadril;

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

public class Q00711_PathToBecomingALordInnadril extends Quest
{
	private static final int Neurath = 35316;
	private static final int IasonHeine = 30969;
	
	private static final int InnadrilCastle = 6;
	private static final int[] mobs =
	{
		20789,
		20790,
		20791,
		20792,
		20793,
		20804,
		20805,
		20806,
		20807,
		20808
	};
	
	public Q00711_PathToBecomingALordInnadril()
	{
		super(711);
		addStartNpc(Neurath);
		addTalkId(Neurath);
		addTalkId(IasonHeine);
		addKillId(mobs);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState qs = player.getQuestState(getName());
		final Castle castle = CastleManager.getInstance().getCastleById(InnadrilCastle);
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		if (event.equals("neurath_q711_03.htm"))
		{
			qs.startQuest();
		}
		else if (event.equals("neurath_q711_05.htm"))
		{
			qs.setCond(2);
		}
		else if (event.equals("neurath_q711_08.htm"))
		{
			if (isLordAvailable(2, qs))
			{
				castleOwner.getQuestState(getName()).set("confidant", String.valueOf(qs.getPlayer().getObjectId()));
				castleOwner.getQuestState(getName()).setCond(3);
				qs.setState(State.STARTED);
			}
			else
			{
				htmltext = "neurath_q711_07a.htm";
			}
			
		}
		else if (event.equals("heine_q711_03.htm"))
		{
			if (isLordAvailable(3, qs))
			{
				castleOwner.getQuestState(getName()).setCond(4);
			}
			else
			{
				htmltext = "heine_q711_00a.htm";
			}
		}
		else if (event.equals("neurath_q711_12.htm"))
		{
			if (castleOwner != null)
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_INNADRIL_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_INNADRIL);
				packet.addStringParameter(player.getName());
				npc.broadcastPacket(packet);
				
				/**
				 * Territory terr = TerritoryWarManager.getInstance().getTerritory(castle.getId()); terr.setLordId(castleOwner.getObjectId()); terr.updateDataInDB(); terr.updateState();
				 */
				
				qs.exitQuest(true, true);
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		final Castle castle = CastleManager.getInstance().getCastleById(InnadrilCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		
		switch (npc.getId())
		{
			case Neurath:
			{
				if (qs.isCond(0))
				{
					if (castleOwner == qs.getPlayer())
					{
						if (!hasFort())
						{
							htmltext = "neurath_q711_01.htm";
						}
						else
						{
							htmltext = "neurath_q711_00.htm";
							qs.exitQuest(true);
						}
					}
					else if (isLordAvailable(2, qs))
					{
						if (castleOwner.calculateDistance2D(npc) <= 200)
						{
							htmltext = "neurath_q711_07.htm";
						}
						else
						{
							htmltext = "neurath_q711_07a.htm";
						}
					}
					else if (qs.getState() == State.STARTED)
					{
						htmltext = "neurath_q711_00b.htm";
					}
					else
					{
						htmltext = "neurath_q711_00a.htm";
						qs.exitQuest(true);
					}
				}
				else if (qs.isCond(1))
				{
					htmltext = "neurath_q711_04.htm";
				}
				else if (qs.isCond(2))
				{
					htmltext = "neurath_q711_06.htm";
				}
				else if (qs.isCond(3))
				{
					htmltext = "neurath_q711_09.htm";
				}
				else if (qs.isCond(4))
				{
					qs.setCond(5);
					htmltext = "neurath_q711_10.htm";
				}
				else if (qs.isCond(5))
				{
					htmltext = "neurath_q711_10.htm";
				}
				else if (qs.isCond(6))
				{
					htmltext = "neurath_q711_11.htm";
				}
				break;
			}
			case IasonHeine:
			{
				if ((qs.getState() == State.STARTED) && qs.isCond(0))
				{
					if (isLordAvailable(3, qs))
					{
						if (castleOwner.getQuestState(getName()).getInt("confidant") == qs.getPlayer().getObjectId())
						{
							htmltext = "heine_q711_01.htm";
						}
						else
						{
							htmltext = "heine_q711_00.htm";
						}
					}
					else if (isLordAvailable(4, qs))
					{
						if (castleOwner.getQuestState(getName()).getInt("confidant") == qs.getPlayer().getObjectId())
						{
							htmltext = "heine_q711_03.htm";
						}
						else
						{
							htmltext = "heine_q711_00.htm";
						}
					}
					else
					{
						htmltext = "heine_q711_00a.htm";
					}
				}
				break;
			}
		}
		
		return htmltext;
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		final QuestState qs = player.getQuestState(getName());
		
		if ((qs != null) && qs.isCond(5))
		{
			if (qs.getInt("mobs") < 99)
			{
				qs.set("mobs", String.valueOf(qs.getInt("mobs") + 1));
			}
			else
			{
				qs.setCond(6);
			}
		}
		return null;
	}
	
	private boolean isLordAvailable(int cond, QuestState qs)
	{
		final Castle castle = CastleManager.getInstance().getCastleById(InnadrilCastle);
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
			if (fortress.getContractedCastleId() == InnadrilCastle)
			{
				return true;
			}
		}
		return false;
	}
}
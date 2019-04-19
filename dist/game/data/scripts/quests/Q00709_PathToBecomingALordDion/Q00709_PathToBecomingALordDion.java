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
package quests.Q00709_PathToBecomingALordDion;

import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.commons.util.Rnd;
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

public class Q00709_PathToBecomingALordDion extends Quest
{
	private static final int Crosby = 35142;
	private static final int Rouke = 31418;
	private static final int Sophia = 30735;
	private static final int MandragoraRoot = 13849;
	private static final int BloodyAxeAide = 27392;
	private static final int Epaulette = 13850;
	private static final int[] OlMahums =
	{
		20208,
		20209,
		20210,
		20211,
		BloodyAxeAide
	};
	private static final int[] Manragoras =
	{
		20154,
		20155,
		20156
	};
	private static final int DionCastle = 2;
	
	public Q00709_PathToBecomingALordDion()
	{
		super(709);
		addStartNpc(Crosby);
		addTalkId(Crosby);
		addTalkId(Sophia);
		addTalkId(Rouke);
		_questItemIds = new int[]
		{
			Epaulette,
			MandragoraRoot
		};
		addKillId(OlMahums);
		addKillId(Manragoras);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState qs = player.getQuestState(getName());
		final Castle castle = CastleManager.getInstance().getCastleById(DionCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		if (event.equals("crosby_q709_03.htm"))
		{
			qs.startQuest();
		}
		else if (event.equals("crosby_q709_06.htm"))
		{
			if (isLordAvailable(2, qs))
			{
				castleOwner.getQuestState(getName()).set("confidant", String.valueOf(qs.getPlayer().getObjectId()));
				castleOwner.getQuestState(getName()).setCond(3);
				qs.setState(State.STARTED);
			}
			else
			{
				htmltext = "crosby_q709_05a.htm";
			}
		}
		else if (event.equals("rouke_q709_03.htm"))
		{
			if (isLordAvailable(3, qs))
			{
				castleOwner.getQuestState(getName()).setCond(4);
			}
			else
			{
				htmltext = "crosby_q709_05a.htm";
			}
		}
		else if (event.equals("sophia_q709_02.htm"))
		{
			qs.set("cond", "6");
		}
		else if (event.equals("sophia_q709_05.htm"))
		{
			takeItems(player, Epaulette, 1);
			qs.set("cond", "8");
		}
		else if (event.equals("rouke_q709_05.htm"))
		{
			if (isLordAvailable(8, qs))
			{
				takeItems(player, MandragoraRoot, -1);
				castleOwner.getQuestState(getName()).setCond(9);
			}
		}
		else if (event.equals("crosby_q709_10.htm"))
		{
			if (castleOwner != null)
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_DION_LONG_MAY_HE_REIGN);
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
		final Castle castle = CastleManager.getInstance().getCastleById(DionCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		
		switch (npc.getId())
		{
			case Crosby:
			{
				if (qs.isCond(0))
				{
					if (castleOwner == qs.getPlayer())
					{
						if (!hasFort())
						{
							htmltext = "crosby_q709_01.htm";
						}
						else
						{
							htmltext = "crosby_q709_00.htm";
							qs.exitQuest(true);
						}
					}
					else if (isLordAvailable(2, qs))
					{
						if (castleOwner.calculateDistance2D(npc) <= 200)
						{
							htmltext = "crosby_q709_05.htm";
						}
						else
						{
							htmltext = "crosby_q709_05a.htm";
						}
					}
					else
					{
						htmltext = "crosby_q709_00a.htm";
						qs.exitQuest(true);
					}
				}
				else if (qs.isCond(1))
				{
					qs.set("cond", "2");
					htmltext = "crosby_q709_04.htm";
				}
				else if (qs.isCond(2) || qs.isCond(3))
				{
					htmltext = "crosby_q709_04a.htm";
				}
				else if (qs.isCond(4))
				{
					qs.set("cond", "5");
					htmltext = "crosby_q709_07.htm";
				}
				else if (qs.isCond(5))
				{
					htmltext = "crosby_q709_07.htm";
				}
				else if ((qs.getCond() > 5) && (qs.getCond() < 9))
				{
					htmltext = "crosby_q709_08.htm";
				}
				else if (qs.isCond(9))
				{
					htmltext = "crosby_q709_09.htm";
				}
				break;
			}
			case Rouke:
			{
				if ((qs.getState() == State.STARTED) && qs.isCond(0) && isLordAvailable(3, qs))
				{
					if (castleOwner.getQuestState(getName()).getInt("confidant") == qs.getPlayer().getObjectId())
					{
						htmltext = "rouke_q709_01.htm";
					}
				}
				else if ((qs.getState() == State.STARTED) && qs.isCond(0) && isLordAvailable(8, qs))
				{
					if (getQuestItemsCount(player, MandragoraRoot) >= 100)
					{
						htmltext = "rouke_q709_04.htm";
					}
					else
					{
						htmltext = "rouke_q709_04a.htm";
					}
				}
				else if ((qs.getState() == State.STARTED) && qs.isCond(0) && isLordAvailable(9, qs))
				{
					htmltext = "rouke_q709_06.htm";
				}
				break;
			}
			case Sophia:
			{
				if (qs.isCond(5))
				{
					htmltext = "sophia_q709_01.htm";
				}
				else if (qs.isCond(6))
				{
					htmltext = "sophia_q709_03.htm";
				}
				else if (qs.isCond(7))
				{
					htmltext = "sophia_q709_04.htm";
				}
				else if (qs.isCond(8))
				{
					htmltext = "sophia_q709_06.htm";
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
		
		if ((qs != null) && qs.isCond(6) && CommonUtil.contains(OlMahums, npc.getId()))
		{
			if ((npc.getId() != BloodyAxeAide) && (Rnd.get(9) == 0))
			{
				addSpawn(BloodyAxeAide, npc, true, 300000);
			}
			else if (npc.getId() == BloodyAxeAide)
			{
				giveItems(killer, Epaulette, 1);
				qs.setCond(7);
			}
		}
		if ((qs != null) && (qs.getState() == State.STARTED) && qs.isCond(0) && isLordAvailable(8, qs) && CommonUtil.contains(Manragoras, npc.getId()))
		{
			if (getQuestItemsCount(killer, MandragoraRoot) < 100)
			{
				giveItems(killer, MandragoraRoot, 1);
			}
		}
		return null;
	}
	
	private boolean isLordAvailable(int cond, QuestState qs)
	{
		final Castle castle = CastleManager.getInstance().getCastleById(DionCastle);
		final L2Clan owner = castle.getOwner();
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		if (owner != null)
		{
			if ((castleOwner != null) && (castleOwner != qs.getPlayer()) && (owner == qs.getPlayer().getClan()) && (castleOwner.getQuestState(getName()) != null) && castleOwner.getQuestState(getName()).isCond(cond))
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
			if (fortress.getContractedCastleId() == DionCastle)
			{
				return true;
			}
		}
		return false;
	}
}
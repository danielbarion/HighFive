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
package quests.Q00708_PathToBecomingALordGludio;

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

public class Q00708_PathToBecomingALordGludio extends Quest
{
	private static final int Sayres = 35100;
	private static final int Pinter = 30298;
	private static final int Bathis = 30332;
	private static final int HeadlessKnight = 20280;
	
	private static final int HeadlessKnightsArmor = 13848;
	
	private static final int[] Mobs =
	{
		20045,
		20051,
		20099,
		HeadlessKnight
	};
	
	private static final int GludioCastle = 1;
	
	public Q00708_PathToBecomingALordGludio()
	{
		super(708);
		addStartNpc(Sayres);
		addTalkId(Sayres);
		addTalkId(Pinter);
		addTalkId(Bathis);
		addKillId(Mobs);
		_questItemIds = new int[]
		{
			HeadlessKnightsArmor
		};
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		final QuestState qs = player.getQuestState(getName());
		
		final Castle castle = CastleManager.getInstance().getCastleById(GludioCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		if (event.equals("sayres_q708_03.htm"))
		{
			qs.startQuest();
		}
		else if (event.equals("sayres_q708_05.htm"))
		{
			qs.setCond(2);
		}
		else if (event.equals("sayres_q708_08.htm"))
		{
			if (isLordAvailable(2, qs))
			{
				castleOwner.getQuestState(getName()).set("confidant", String.valueOf(qs.getPlayer().getObjectId()));
				castleOwner.getQuestState(getName()).setCond(3);
				qs.setState(State.STARTED);
			}
			else
			{
				htmltext = "sayres_q708_05a.htm";
			}
		}
		else if (event.equals("pinter_q708_03.htm"))
		{
			if (isLordAvailable(3, qs))
			{
				castleOwner.getQuestState(getName()).setCond(4);
			}
			else
			{
				htmltext = "pinter_q708_03a.htm";
			}
		}
		else if (event.equals("bathis_q708_02.htm"))
		{
			qs.setCond(6);
		}
		else if (event.equals("bathis_q708_05.htm"))
		{
			takeItems(player, HeadlessKnightsArmor, 1);
			qs.setCond(8);
			npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.LISTEN_YOU_VILLAGERS_OUR_LIEGE_WHO_WILL_SOON_BECOME_A_LORD_HAS_DEFEATED_THE_HEADLESS_KNIGHT_YOU_CAN_NOW_REST_EASY));
		}
		else if (event.equals("pinter_q708_05.htm"))
		{
			if (isLordAvailable(8, qs))
			{
				takeItems(player, 1867, 100);
				takeItems(player, 1865, 100);
				takeItems(player, 1869, 100);
				takeItems(player, 1879, 50);
				castleOwner.getQuestState(getName()).setCond(9);
			}
			else
			{
				htmltext = "pinter_q708_03a.htm";
			}
		}
		else if (event.equals("sayres_q708_12.htm"))
		{
			if (castleOwner != null)
			{
				final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_SHOUT, npc.getId(), NpcStringId.S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_GLUDIO_LONG_MAY_HE_REIGN);
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
		final Castle castle = CastleManager.getInstance().getCastleById(GludioCastle);
		if (castle.getOwner() == null)
		{
			return "Castle has no lord";
		}
		final L2PcInstance castleOwner = castle.getOwner().getLeader().getPlayerInstance();
		
		switch (npc.getId())
		{
			case Sayres:
			{
				if (qs.isCond(0))
				{
					if (castleOwner == qs.getPlayer())
					{
						if (!hasFort())
						{
							htmltext = "sayres_q708_01.htm";
						}
						else
						{
							htmltext = "sayres_q708_00.htm";
							qs.exitQuest(true);
						}
					}
					else if (isLordAvailable(2, qs))
					{
						if (castleOwner.calculateDistance2D(npc) <= 200)
						{
							htmltext = "sayres_q708_07.htm";
						}
						else
						{
							htmltext = "sayres_q708_05a.htm";
						}
					}
					else if (qs.getState() == State.STARTED)
					{
						htmltext = "sayres_q708_08a.htm";
					}
					else
					{
						htmltext = "sayres_q708_00a.htm";
						qs.exitQuest(true);
					}
				}
				else if (qs.isCond(1))
				{
					htmltext = "sayres_q708_04.htm";
				}
				else if (qs.isCond(2))
				{
					htmltext = "sayres_q708_06.htm";
				}
				else if (qs.isCond(4))
				{
					qs.set("cond", "5");
					htmltext = "sayres_q708_09.htm";
				}
				else if (qs.isCond(5))
				{
					htmltext = "sayres_q708_10.htm";
				}
				else if ((qs.getCond() > 5) && (qs.getCond() < 9))
				{
					htmltext = "sayres_q708_08.htm";
				}
				else if (qs.isCond(9))
				{
					htmltext = "sayres_q708_11.htm";
				}
				break;
			}
			case Pinter:
			{
				if ((qs.getState() == State.STARTED) && qs.isCond(0) && isLordAvailable(3, qs))
				{
					if (castleOwner.getQuestState(getName()).getInt("confidant") == qs.getPlayer().getObjectId())
					{
						htmltext = "pinter_q708_01.htm";
					}
				}
				else if ((qs.getState() == State.STARTED) && qs.isCond(0) && isLordAvailable(8, qs))
				{
					if ((getQuestItemsCount(player, 1867) >= 100) && (getQuestItemsCount(player, 1865) >= 100) && (getQuestItemsCount(player, 1869) >= 100) && (getQuestItemsCount(player, 1879) >= 50))
					{
						htmltext = "pinter_q708_04.htm";
					}
					else
					{
						htmltext = "pinter_q708_04a.htm";
					}
				}
				else if ((qs.getState() == State.STARTED) && qs.isCond(0) && isLordAvailable(9, qs))
				{
					htmltext = "pinter_q708_06.htm";
				}
				break;
			}
			case Bathis:
			{
				if (qs.isCond(5))
				{
					htmltext = "bathis_q708_01.htm";
				}
				else if (qs.isCond(6))
				{
					htmltext = "bathis_q708_03.htm";
				}
				else if (qs.isCond(7))
				{
					htmltext = "bathis_q708_04.htm";
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
		
		if ((qs != null) && qs.isCond(6))
		{
			if ((npc.getId() != HeadlessKnight) && (Rnd.get(9) == 0))
			{
				addSpawn(HeadlessKnight, npc, true, 300000);
			}
			else if (npc.getId() == HeadlessKnight)
			{
				giveItems(killer, HeadlessKnightsArmor, 1);
				qs.setCond(7);
			}
		}
		return null;
	}
	
	private boolean isLordAvailable(int cond, QuestState qs)
	{
		final Castle castle = CastleManager.getInstance().getCastleById(GludioCastle);
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
			if (fortress.getContractedCastleId() == GludioCastle)
			{
				return true;
			}
		}
		return false;
	}
}
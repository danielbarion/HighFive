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
package instances.DarkCloudMansion;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

import instances.AbstractInstance;

/**
 * Dark Cloud Mansion instance zone.
 */
public final class DarkCloudMansion extends AbstractInstance
{
	// NPCs
	private static int YIYEN = 32282;
	private static int SOFaith = 32288; // Symbol of Faith
	private static int SOAdversity = 32289; // Symbol of Adversity
	private static int SOAdventure = 32290; // Symbol of Adventure
	private static int SOTruth = 32291; // Symbol of Truth
	private static int BSM = 32324; // Black Stone Monolith
	private static int SC = 22402; // Shadow Column
	// Mobs
	private static int[] CCG =
	{
		18369,
		18370
	}; // Chromatic Crystal Golem
	private static int[] BM =
	{
		22272,
		22273,
		22274
	}; // Beleth's Minions
	private static int[] HG =
	{
		22264,
		22264
	}; // [22318,22319] #Hall Guards
	private static int[] BS =
	{
		18371,
		18372,
		18373,
		18374,
		18375,
		18376,
		18377
	}; // Beleth's Samples
	private static int[] TOKILL =
	{
		18371,
		18372,
		18373,
		18374,
		18375,
		18376,
		18377,
		// 22318,
		// 22319,
		22272,
		22273,
		22274,
		18369,
		18370,
		22402,
		22264
	};
	
	// Items
	private static int CC = 9690; // Contaminated Crystal
	// Misc
	private static final int TEMPLATE_ID = 9;
	private static int D1 = 24230001; // Starting Room
	private static int D2 = 24230002; // First Room
	private static int D3 = 24230005; // Second Room
	private static int D4 = 24230003; // Third Room
	private static int D5 = 24230004; // Forth Room
	private static int D6 = 24230006; // Fifth Room
	private static int W1 = 24230007; // Wall 1
	// private static int W2 = 24230008; // Wall 2
	// private static int W3 = 24230009; // Wall 3
	// private static int W4 = 24230010; // Wall 4
	// private static int W5 = 24230011; // Wall 5
	// private static int W6 = 24230012; // Wall 6
	// private static int W7 = 24230013; // Wall 7
	private static boolean noRndWalk = true;
	private static NpcStringId[] _spawnChat =
	{
		NpcStringId.I_M_THE_REAL_ONE,
		NpcStringId.PICK_ME,
		NpcStringId.TRUST_ME,
		NpcStringId.NOT_THAT_DUDE_I_M_THE_REAL_ONE,
		NpcStringId.DON_T_BE_FOOLED_DON_T_BE_FOOLED_I_M_THE_REAL_ONE
	};
	private static NpcStringId[] _decayChat =
	{
		NpcStringId.I_M_THE_REAL_ONE_PHEW,
		NpcStringId.CAN_T_YOU_EVEN_FIND_OUT,
		NpcStringId.FIND_ME
	};
	private static NpcStringId[] _successChat =
	{
		NpcStringId.HUH_HOW_DID_YOU_KNOW_IT_WAS_ME,
		NpcStringId.EXCELLENT_CHOICE_TEEHEE,
		NpcStringId.YOU_VE_DONE_WELL,
		NpcStringId.OH_VERY_SENSIBLE
	};
	private static NpcStringId[] _faildChat =
	{
		NpcStringId.YOU_VE_BEEN_FOOLED,
		NpcStringId.SORRY_BUT_I_M_THE_FAKE_ONE
	};
	// @formatter:off
	// Second room - random monolith order
	private static int[][] MonolithOrder = new int[][]
	{
		{1, 2, 3, 4, 5, 6},
		{6, 5, 4, 3, 2, 1},
		{4 ,5, 6, 3, 2, 1},
		{2, 6, 3, 5, 1, 4},
		{4, 1, 5, 6, 2, 3},
		{3, 5, 1, 6, 2, 4},
		{6, 1, 3, 4, 5, 2},
		{5, 6, 1, 2, 4, 3},
		{5, 2, 6, 3, 4, 1},
		{1, 5, 2, 6, 3, 4},
		{1, 2, 3, 6, 5, 4},
		{6, 4, 3, 1, 5, 2},
		{3, 5, 2, 4, 1, 6},
		{3, 2, 4, 5, 1, 6},
		{5, 4, 3, 1, 6, 2},
	};
	// Second room - golem spawn locatons - random
	private static int[][] GolemSpawn = new int[][]
	{
		{CCG[0], 148060, 181389},
		{CCG[1], 147910, 181173},
		{CCG[0], 147810, 181334},
		{CCG[1], 147713, 181179},
		{CCG[0], 147569, 181410},
		{CCG[1], 147810, 181517},
		{CCG[0], 147805, 181281},
	};
	
	// forth room - random shadow column
	private static int[][] ColumnRows = new int[][]
	{
		{1, 1, 0, 1, 0},
		{0, 1, 1, 0, 1},
		{1, 0, 1, 1, 0},
		{0, 1, 0, 1, 1},
		{1, 0, 1, 0, 1},
	};
	
	// Fifth room - beleth order
	private static int[][] Beleths = new int[][]
	{
		{1, 0, 1, 0, 1, 0, 0},
		{0, 0, 1, 0, 1, 1, 0},
		{0, 0, 0, 1, 0, 1, 1},
		{1, 0, 1, 1, 0, 0, 0},
		{1, 1, 0, 0, 0, 1, 0},
		{0, 1, 0, 1, 0, 1, 0},
		{0, 0, 0, 1, 1, 1, 0},
		{1, 0, 1, 0, 0, 1, 0},
		{0, 1, 1, 0, 0, 0, 1},
	};
	// @formatter:on
	
	private DarkCloudMansion()
	{
		addFirstTalkId(BSM, SOTruth);
		addStartNpc(YIYEN);
		addTalkId(YIYEN, SOTruth);
		addAttackId(SC);
		addAttackId(BS);
		addAttackId(CCG);
		addKillId(TOKILL);
	}
	
	protected static class DMCNpc
	{
		public L2Npc npc;
		public boolean isDead = false;
		public L2Npc golem = null;
		public int status = 0;
		public int order = 0;
		public int count = 0;
	}
	
	protected static class DMCRoom
	{
		public List<DMCNpc> npcList = new ArrayList<>();
		public int counter = 0;
		public int reset = 0;
		public int founded = 0;
		public int[] Order;
	}
	
	@Override
	protected boolean checkConditions(L2PcInstance player)
	{
		final L2Party party = player.getParty();
		if (party == null)
		{
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_CURRENTLY_IN_A_PARTY_SO_YOU_CANNOT_ENTER);
			return false;
		}
		if (party.getLeader() != player)
		{
			player.sendPacket(SystemMessageId.ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER);
			return false;
		}
		if (party.getMemberCount() > 2)
		{
			player.sendPacket(SystemMessageId.YOU_CANNOT_ENTER_DUE_TO_THE_PARTY_HAVING_EXCEEDED_THE_LIMIT);
			return false;
		}
		for (L2PcInstance partyMember : party.getMembers())
		{
			if (partyMember.getLevel() < 78)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY);
				sm.addPcName(partyMember);
				player.sendPacket(sm);
				return false;
			}
			if (!partyMember.isInsideRadius3D(player, 1000))
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_IN_A_LOCATION_WHICH_CANNOT_BE_ENTERED_THEREFORE_IT_CANNOT_BE_PROCESSED);
				sm.addPcName(partyMember);
				player.sendPacket(sm);
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void onEnterInstance(L2PcInstance player, InstanceWorld world, boolean firstEntrance)
	{
		if (firstEntrance)
		{
			runStartRoom(world);
			final L2Party party = player.getParty();
			if (party != null)
			{
				for (L2PcInstance partyMember : party.getMembers())
				{
					if (partyMember.getQuestState(getName()) == null)
					{
						newQuestState(partyMember);
					}
					world.addAllowed(partyMember);
					teleportPlayer(partyMember, new Location(146534, 180464, -6117), world.getInstanceId());
				}
			}
		}
		else
		{
			teleportPlayer(player, new Location(146534, 180464, -6117), world.getInstanceId());
		}
	}
	
	protected void runStartRoom(InstanceWorld world)
	{
		world.setStatus(0);
		final DMCRoom StartRoom = new DMCRoom();
		DMCNpc thisnpc;
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[0], 146817, 180335, -6117, 0, false, 0, false, world.getInstanceId());
		StartRoom.npcList.add(thisnpc);
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[0], 146741, 180589, -6117, 0, false, 0, false, world.getInstanceId());
		StartRoom.npcList.add(thisnpc);
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		world.setParameter("StartRoom", StartRoom);
	}
	
	protected void spawnHall(InstanceWorld world)
	{
		final DMCRoom Hall = new DMCRoom();
		DMCNpc thisnpc;
		world.getParameters().remove("Hall"); // remove room instance to avoid adding mob every time
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[1], 147217, 180112, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		Hall.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[2], 147217, 180209, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		Hall.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[1], 148521, 180112, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		Hall.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[0], 148521, 180209, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		Hall.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[1], 148525, 180910, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		Hall.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[2], 148435, 180910, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		Hall.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[1], 147242, 180910, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		Hall.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BM[2], 147242, 180819, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		Hall.npcList.add(thisnpc);
		
		world.setParameter("Hall", Hall);
	}
	
	protected void runHall(InstanceWorld world)
	{
		spawnHall(world);
		world.setStatus(1);
		world.openDoor(D1);
	}
	
	protected void runFirstRoom(InstanceWorld world)
	{
		final DMCRoom FirstRoom = new DMCRoom();
		DMCNpc thisnpc;
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(HG[1], 147842, 179837, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		FirstRoom.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(HG[0], 147711, 179708, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		FirstRoom.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(HG[1], 147842, 179552, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		FirstRoom.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(HG[0], 147964, 179708, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		FirstRoom.npcList.add(thisnpc);
		
		world.setParameter("FirstRoom", FirstRoom);
		world.setStatus(2);
		world.openDoor(D2);
	}
	
	protected void runHall2(InstanceWorld world)
	{
		addSpawn(SOFaith, 147818, 179643, -6117, 0, false, 0, false, world.getInstanceId());
		spawnHall(world);
		world.setStatus(3);
	}
	
	protected void runSecondRoom(InstanceWorld world)
	{
		final DMCRoom SecondRoom = new DMCRoom();
		DMCNpc thisnpc;
		
		// TODO: find a better way to initialize to [1,0,0,0,0,0,0]
		SecondRoom.Order = new int[7];
		SecondRoom.Order[0] = 1;
		for (int i = 1; i < 7; i++)
		{
			SecondRoom.Order[i] = 0;
		}
		
		final int i = getRandom(MonolithOrder.length);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BSM, 147800, 181150, -6117, 0, false, 0, false, world.getInstanceId());
		thisnpc.order = MonolithOrder[i][0];
		SecondRoom.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BSM, 147900, 181215, -6117, 0, false, 0, false, world.getInstanceId());
		thisnpc.order = MonolithOrder[i][1];
		SecondRoom.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BSM, 147900, 181345, -6117, 0, false, 0, false, world.getInstanceId());
		thisnpc.order = MonolithOrder[i][2];
		SecondRoom.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BSM, 147800, 181410, -6117, 0, false, 0, false, world.getInstanceId());
		thisnpc.order = MonolithOrder[i][3];
		SecondRoom.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BSM, 147700, 181345, -6117, 0, false, 0, false, world.getInstanceId());
		thisnpc.order = MonolithOrder[i][4];
		SecondRoom.npcList.add(thisnpc);
		
		thisnpc = new DMCNpc();
		thisnpc.npc = addSpawn(BSM, 147700, 181215, -6117, 0, false, 0, false, world.getInstanceId());
		thisnpc.order = MonolithOrder[i][5];
		SecondRoom.npcList.add(thisnpc);
		
		world.setParameter("SecondRoom", SecondRoom);
		world.setStatus(4);
		world.openDoor(D3);
	}
	
	protected void runHall3(InstanceWorld world)
	{
		addSpawn(SOAdversity, 147808, 181281, -6117, 16383, false, 0, false, world.getInstanceId());
		spawnHall(world);
		world.setStatus(5);
	}
	
	protected void runThirdRoom(InstanceWorld world)
	{
		final DMCRoom ThirdRoom = new DMCRoom();
		final DMCNpc thisnpc = new DMCNpc();
		thisnpc.isDead = false;
		thisnpc.npc = addSpawn(BM[1], 148765, 180450, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[2], 148865, 180190, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[1], 148995, 180190, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[0], 149090, 180450, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[1], 148995, 180705, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[2], 148865, 180705, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		world.setParameter("ThirdRoom", ThirdRoom);
		world.setStatus(6);
		world.openDoor(D4);
	}
	
	protected void runThirdRoom2(InstanceWorld world)
	{
		addSpawn(SOAdventure, 148910, 178397, -6117, 16383, false, 0, false, world.getInstanceId());
		final DMCRoom ThirdRoom = new DMCRoom();
		final DMCNpc thisnpc = new DMCNpc();
		thisnpc.isDead = false;
		thisnpc.npc = addSpawn(BM[1], 148765, 180450, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[2], 148865, 180190, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[1], 148995, 180190, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[0], 149090, 180450, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[1], 148995, 180705, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		thisnpc.npc = addSpawn(BM[2], 148865, 180705, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			thisnpc.npc.setRandomWalking(false);
		}
		ThirdRoom.npcList.add(thisnpc);
		world.setParameter("ThirdRoom2", ThirdRoom);
		world.setStatus(8);
	}
	
	protected void runForthRoom(InstanceWorld world)
	{
		final DMCRoom ForthRoom = new DMCRoom();
		ForthRoom.counter = 0;
		DMCNpc thisnpc;
		final int temp[] = new int[7];
		final int templist[][] = new int[7][5];
		int xx = 0;
		
		for (int i = 0; i < 7; i++)
		{
			temp[i] = getRandom(ColumnRows.length);
		}
		
		for (int i = 0; i < 7; i++)
		{
			templist[i] = ColumnRows[temp[i]];
		}
		
		for (int x = 148660; x < 149285; x += 125)
		{
			int yy = 0;
			for (int y = 179280; y > 178405; y -= 125)
			{
				thisnpc = new DMCNpc();
				thisnpc.npc = addSpawn(SC, x, y, -6115, 16215, false, 0, false, world.getInstanceId());
				thisnpc.status = templist[yy][xx];
				thisnpc.order = yy;
				ForthRoom.npcList.add(thisnpc);
				yy++;
			}
			xx++;
		}
		// TODO: unify this into previous loop
		for (DMCNpc npc : ForthRoom.npcList)
		{
			if (npc.status == 0)
			{
				npc.npc.setIsInvul(true);
			}
		}
		
		world.setParameter("ForthRoom", ForthRoom);
		world.setStatus(7);
		world.openDoor(D5);
	}
	
	protected void runFifthRoom(InstanceWorld world)
	{
		spawnFifthRoom(world);
		world.setStatus(9);
		world.openDoor(D6);
	}
	
	private void spawnFifthRoom(InstanceWorld world)
	{
		int idx = 0;
		int temp[] = new int[6];
		final DMCRoom FifthRoom = new DMCRoom();
		DMCNpc thisnpc;
		
		temp = Beleths[getRandom(Beleths.length)];
		
		FifthRoom.reset = 0;
		FifthRoom.founded = 0;
		
		for (int x = 148720; x < 149175; x += 65)
		{
			thisnpc = new DMCNpc();
			thisnpc.npc = addSpawn(BS[idx], x, 182145, -6117, 48810, false, 0, false, world.getInstanceId());
			thisnpc.npc.setRandomWalking(false);
			thisnpc.order = idx;
			thisnpc.status = temp[idx];
			thisnpc.count = 0;
			FifthRoom.npcList.add(thisnpc);
			if ((temp[idx] == 1) && (getRandom(100) < 95))
			{
				thisnpc.npc.broadcastPacket(new NpcSay(thisnpc.npc.getObjectId(), ChatType.GENERAL, thisnpc.npc.getId(), _spawnChat[getRandom(_spawnChat.length)]));
			}
			else if ((temp[idx] != 1) && (getRandom(100) < 67))
			{
				thisnpc.npc.broadcastPacket(new NpcSay(thisnpc.npc.getObjectId(), ChatType.GENERAL, thisnpc.npc.getId(), _spawnChat[getRandom(_spawnChat.length)]));
			}
			idx++;
		}
		
		world.setParameter("FifthRoom", FifthRoom);
	}
	
	protected boolean checkKillProgress(L2Npc npc, DMCRoom room)
	{
		boolean cont = true;
		for (DMCNpc npcobj : room.npcList)
		{
			if (npcobj.npc == npc)
			{
				npcobj.isDead = true;
			}
			if (npcobj.isDead == false)
			{
				cont = false;
			}
		}
		
		return cont;
	}
	
	protected void spawnRndGolem(InstanceWorld world, DMCNpc npc)
	{
		if (npc.golem != null)
		{
			return;
		}
		
		final int i = getRandom(GolemSpawn.length);
		final int mobId = GolemSpawn[i][0];
		final int x = GolemSpawn[i][1];
		final int y = GolemSpawn[i][2];
		
		npc.golem = addSpawn(mobId, x, y, -6117, 0, false, 0, false, world.getInstanceId());
		if (noRndWalk)
		{
			npc.golem.setRandomWalking(false);
		}
	}
	
	protected void checkStone(L2Npc npc, int order[], DMCNpc npcObj, InstanceWorld world)
	{
		for (int i = 1; i < 7; i++)
		{
			// if there is a non zero value in the precedent step, the sequence is ok
			if ((order[i] == 0) && (order[i - 1] != 0))
			{
				if ((npcObj.order == i) && (npcObj.status == 0))
				{
					order[i] = 1;
					npcObj.status = 1;
					npcObj.isDead = true;
					npc.broadcastPacket(new MagicSkillUse(npc, npc, 5441, 1, 1, 0));
					return;
				}
			}
		}
		
		spawnRndGolem(world, npcObj);
	}
	
	protected void endInstance(InstanceWorld world)
	{
		world.setStatus(10);
		addSpawn(SOTruth, 148911, 181940, -6117, 16383, false, 0, false, world.getInstanceId());
		world.getParameters().remove("StartRoom");
		world.getParameters().remove("Hall");
		world.getParameters().remove("FirstRoom");
		world.getParameters().remove("SecondRoom");
		world.getParameters().remove("ThirdRoom");
		world.getParameters().remove("ForthRoom");
		world.getParameters().remove("FifthRoom");
	}
	
	protected void checkBelethSample(InstanceWorld world, L2Npc npc, L2PcInstance player)
	{
		final DMCRoom FifthRoom = world.getParameters().getObject("FifthRoom", DMCRoom.class);
		
		for (DMCNpc mob : FifthRoom.npcList)
		{
			if (mob.npc == npc)
			{
				if (mob.count == 0)
				{
					mob.count = 1;
					if (mob.status == 1)
					{
						mob.npc.broadcastPacket(new NpcSay(mob.npc.getObjectId(), ChatType.NPC_GENERAL, mob.npc.getId(), _successChat[getRandom(_successChat.length)]));
						FifthRoom.founded += 1;
						startQuestTimer("decayMe", 1500, npc, player);
					}
					else
					{
						FifthRoom.reset = 1;
						mob.npc.broadcastPacket(new NpcSay(mob.npc.getObjectId(), ChatType.NPC_GENERAL, mob.npc.getId(), _faildChat[getRandom(_faildChat.length)]));
						startQuestTimer("decayChatBelethSamples", 4000, npc, player);
						startQuestTimer("decayBelethSamples", 4500, npc, player);
					}
				}
				else
				{
					return;
				}
			}
		}
	}
	
	protected void killedBelethSample(InstanceWorld world, L2Npc npc)
	{
		int decayedSamples = 0;
		final DMCRoom FifthRoom = world.getParameters().getObject("FifthRoom", DMCRoom.class);
		
		for (DMCNpc mob : FifthRoom.npcList)
		{
			if (mob.npc == npc)
			{
				decayedSamples += 1;
				mob.count = 2;
			}
			else
			{
				if (mob.count == 2)
				{
					decayedSamples += 1;
				}
			}
		}
		
		if (FifthRoom.reset == 1)
		{
			for (DMCNpc mob : FifthRoom.npcList)
			{
				if ((mob.count == 0) || ((mob.status == 1) && (mob.count != 2)))
				{
					decayedSamples += 1;
					mob.npc.decayMe();
					mob.count = 2;
				}
			}
			if (decayedSamples == 7)
			{
				startQuestTimer("respawnFifth", 6000, npc, null);
			}
		}
		else
		{
			if ((FifthRoom.reset == 0) && (FifthRoom.founded == 3))
			{
				for (DMCNpc mob : FifthRoom.npcList)
				{
					mob.npc.decayMe();
				}
				endInstance(world);
			}
		}
	}
	
	protected boolean allStonesDone(InstanceWorld world)
	{
		final DMCRoom SecondRoom = world.getParameters().getObject("SecondRoom", DMCRoom.class);
		
		for (DMCNpc mob : SecondRoom.npcList)
		{
			if (mob.isDead)
			{
				continue;
			}
			return false;
		}
		
		return true;
	}
	
	protected void removeMonoliths(InstanceWorld world)
	{
		final DMCRoom SecondRoom = world.getParameters().getObject("SecondRoom", DMCRoom.class);
		
		for (DMCNpc mob : SecondRoom.npcList)
		{
			mob.npc.decayMe();
		}
	}
	
	protected void chkShadowColumn(InstanceWorld world, L2Npc npc)
	{
		final DMCRoom ForthRoom = world.getParameters().getObject("ForthRoom", DMCRoom.class);
		
		for (DMCNpc mob : ForthRoom.npcList)
		{
			if (mob.npc == npc)
			{
				for (int i = 0; i < 7; i++)
				{
					if ((mob.order == i) && (ForthRoom.counter == i))
					{
						world.openDoor(W1 + i);
						ForthRoom.counter += 1;
						if (ForthRoom.counter == 7)
						{
							runThirdRoom2(world);
						}
					}
				}
			}
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (npc == null)
		{
			return "";
		}
		
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world == null)
		{
			return "";
		}
		
		final DMCRoom FifthRoom = world.getParameters().getObject("FifthRoom", DMCRoom.class);
		if (FifthRoom != null)
		{
			if (event.equalsIgnoreCase("decayMe"))
			{
				for (DMCNpc mob : FifthRoom.npcList)
				{
					if ((mob.npc == npc) || ((FifthRoom.reset == 0) && (FifthRoom.founded == 3)))
					{
						mob.npc.decayMe();
						mob.count = 2;
					}
				}
				if ((FifthRoom.reset == 0) && (FifthRoom.founded == 3))
				{
					endInstance(world);
				}
			}
			else if (event.equalsIgnoreCase("decayBelethSamples"))
			{
				for (DMCNpc mob : FifthRoom.npcList)
				{
					if (mob.count == 0)
					{
						mob.npc.decayMe();
						mob.count = 2;
					}
				}
			}
			else if (event.equalsIgnoreCase("decayChatBelethSamples"))
			{
				for (DMCNpc mob : FifthRoom.npcList)
				{
					if (mob.status == 1)
					{
						mob.npc.broadcastPacket(new NpcSay(mob.npc.getObjectId(), ChatType.NPC_GENERAL, mob.npc.getId(), _decayChat[getRandom(_decayChat.length)]));
					}
				}
			}
			else if (event.equalsIgnoreCase("respawnFifth"))
			{
				spawnFifthRoom(world);
			}
		}
		
		return "";
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if (world.getStatus() == 0)
			{
				if (checkKillProgress(npc, world.getParameters().getObject("StartRoom", DMCRoom.class)))
				{
					runHall(world);
				}
			}
			if (world.getStatus() == 1)
			{
				if (checkKillProgress(npc, world.getParameters().getObject("Hall", DMCRoom.class)))
				{
					runFirstRoom(world);
				}
			}
			if (world.getStatus() == 2)
			{
				if (checkKillProgress(npc, world.getParameters().getObject("FirstRoom", DMCRoom.class)))
				{
					runHall2(world);
				}
			}
			if (world.getStatus() == 3)
			{
				if (checkKillProgress(npc, world.getParameters().getObject("Hall", DMCRoom.class)))
				{
					runSecondRoom(world);
				}
			}
			if (world.getStatus() == 4)
			{
				final DMCRoom SecondRoom = world.getParameters().getObject("SecondRoom", DMCRoom.class);
				for (DMCNpc mob : SecondRoom.npcList)
				{
					if (mob.golem == npc)
					{
						mob.golem = null;
					}
				}
			}
			if (world.getStatus() == 5)
			{
				if (checkKillProgress(npc, world.getParameters().getObject("Hall", DMCRoom.class)))
				{
					runThirdRoom(world);
				}
			}
			if (world.getStatus() == 6)
			{
				if (checkKillProgress(npc, world.getParameters().getObject("ThirdRoom", DMCRoom.class)))
				{
					runForthRoom(world);
				}
			}
			if (world.getStatus() == 7)
			{
				chkShadowColumn(world, npc);
			}
			if (world.getStatus() == 8)
			{
				if (checkKillProgress(npc, world.getParameters().getObject("ThirdRoom2", DMCRoom.class)))
				{
					runFifthRoom(world);
				}
			}
			if (world.getStatus() == 9)
			{
				killedBelethSample(world, npc);
			}
		}
		return "";
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon, Skill skill)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if (world.getStatus() == 7)
			{
				final DMCRoom ForthRoom = world.getParameters().getObject("ForthRoom", DMCRoom.class);
				for (DMCNpc mob : ForthRoom.npcList)
				{
					if (mob.npc == npc)
					{
						if (mob.npc.isInvul() && (getRandom(100) < 12))
						{
							addSpawn(BM[getRandom(BM.length)], attacker.getX(), attacker.getY(), attacker.getZ(), 0, false, 0, false, world.getInstanceId());
						}
					}
				}
			}
			if (world.getStatus() == 9)
			{
				checkBelethSample(world, npc, attacker);
			}
		}
		
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if (world.getStatus() == 4)
			{
				final DMCRoom SecondRoom = world.getParameters().getObject("SecondRoom", DMCRoom.class);
				for (DMCNpc mob : SecondRoom.npcList)
				{
					if (mob.npc == npc)
					{
						checkStone(npc, SecondRoom.Order, mob, world);
					}
				}
				
				if (allStonesDone(world))
				{
					removeMonoliths(world);
					runHall3(world);
				}
			}
			
			if ((npc.getId() == SOTruth) && (world.getStatus() == 10))
			{
				npc.showChatWindow(player);
				
				if (!hasQuestItems(player, CC))
				{
					giveItems(player, CC, 1);
				}
			}
		}
		
		return "";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final int npcId = npc.getId();
		if (npcId == YIYEN)
		{
			enterInstance(player, TEMPLATE_ID);
		}
		else
		{
			final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
			if (world == null)
			{
				return "";
			}
			
			if (npcId == SOTruth)
			{
				if (world.isAllowed(player))
				{
					world.removeAllowed(player);
				}
				teleportPlayer(player, new Location(139968, 150367, -3111), 0);
				final int instanceId = npc.getInstanceId();
				final Instance instance = InstanceManager.getInstance().getInstance(instanceId);
				if (instance.getPlayers().isEmpty())
				{
					InstanceManager.getInstance().destroyInstance(instanceId);
				}
				return "";
			}
		}
		return "";
	}
	
	public static void main(String[] args)
	{
		new DarkCloudMansion();
	}
}

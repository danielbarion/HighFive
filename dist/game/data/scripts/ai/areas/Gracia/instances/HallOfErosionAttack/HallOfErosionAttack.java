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
package ai.areas.Gracia.instances.HallOfErosionAttack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.instancemanager.SoIManager;
import com.l2jmobius.gameserver.model.L2CommandChannel;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;
import com.l2jmobius.gameserver.network.serverpackets.IClientOutgoingPacket;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;
import quests.Q00696_ConquerTheHallOfErosion.Q00696_ConquerTheHallOfErosion;

public class HallOfErosionAttack extends AbstractNpcAI
{
	protected class HEAWorld extends InstanceWorld
	{
		public List<L2Npc> npcList = new ArrayList<>();
		public List<L2Npc> deadTumors = new ArrayList<>();
		public int tumorCount = 0;
		public L2Npc cohemenes = null;
		protected L2Npc deadTumor;
		public boolean isBossAttacked = false;
		public long startTime = 0;
		
		public synchronized void addTumorCount(int value)
		{
			tumorCount += value;
		}
		
		public HEAWorld()
		{
		}
	}
	
	private static final int INSTANCEID = 119;
	private static final int INSTANCEPENALTY = 24;
	private static final int MOUTHOFEKIMUS = 32537;
	private static final int TUMOR_ALIVE = 18708;
	private static final int TUMOR_DEAD = 32535;
	private static int COHEMENES = 25634;
	
	protected boolean conquestEnded = false;
	private long tumorRespawnTime;
	
	private static final int[] ENTER_TELEPORT =
	{
		-179659,
		211061,
		-12784
	};
	
	private static int[] NOTMOVE =
	{
		18668,
		18711,
		18708,
		TUMOR_DEAD
	};
	
	private static final int[] mobs =
	{
		22516,
		22520,
		22522,
		22524
	};
	
	//@formatter:off
	private static final int[][] ROOMS_MOBS =
	{
		{22516, -180364, 211944, -12019, 0, 60, 1},
		{22516, -181616, 211413, -12015, 0, 60, 1},
		{22520, -181404, 211042, -12023, 0, 60, 1},
		{22522, -181558, 212227, -12035, 0, 60, 1},
		{22522, -180459, 212322, -12018, 0, 60, 1},
		{22524, -180428, 211180, -12014, 0, 60, 1},
		{22524, -180718, 212162, -12028, 0, 60, 1},
		
		{22532, -183114, 209397, -11923, 0, 60, 1},
		{22532, -182917, 210495, -11925, 0, 60, 1},
		{22516, -183918, 210225, -11934, 0, 60, 1},
		{22532, -183862, 209909, -11932, 0, 60, 1},
		{22532, -183246, 210631, -11923, 0, 60, 1},
		{22522, -182971, 210522, -11924, 0, 60, 1},
		{22522, -183485, 209406, -11921, 0, 60, 1},
		
		{22516, -183032, 208822, -11923, 0, 60, 1},
		{22516, -182709, 207817, -11929, 0, 60, 1},
		{22520, -182964, 207746, -11924, 0, 60, 1},
		{22520, -183385, 208847, -11922, 0, 60, 1},
		{22526, -183684, 208847, -11926, 0, 60, 1},
		{22526, -183530, 208725, -11926, 0, 60, 1},
		{22532, -183968, 207603, -11928, 0, 60, 1},
		{22532, -183608, 208567, -11926, 0, 60, 1},
		
		{22526, -181471, 207159, -12020, 0, 60, 1},
		{22526, -180213, 207042, -12013, 0, 60, 1},
		{22532, -180213, 206506, -12010, 0, 60, 1},
		{22532, -181720, 206643, -12016, 0, 60, 1},
		{22516, -181743, 206643, -12018, 0, 60, 1},
		{22516, -181028, 205739, -12030, 0, 60, 1},
		{22520, -181431, 205980, -12040, 0, 60, 1},
		
		{22524, -178964, 207168, -12014, 0, 60, 1},
		{22524, -177658, 207037, -12019, 0, 60, 1},
		{22522, -177730, 206558, -12016, 0, 60, 1},
		{22522, -179132, 206650, -12011, 0, 60, 1},
		{22526, -179132, 206155, -12017, 0, 60, 1},
		{22526, -178277, 205754, -12031, 0, 60, 1},
		{22516, -178716, 205802, -12020, 0, 60, 1},
		
		{22532, -176565, 207839, -11929, 0, 60, 1},
		{22532, -176281, 208822, -11923, 0, 60, 1},
		{22520, -175791, 208804, -11923, 0, 60, 1},
		{22520, -176259, 207689, -11923, 0, 60, 1},
		{22526, -175849, 207508, -11929, 0, 60, 1},
		{22526, -175453, 208250, -11930, 0, 60, 1},
		{22524, -175738, 207914, -11946, 0, 60, 1},
		
		{22526, -176339, 209425, -11923, 0, 60, 1},
		{22526, -176586, 210424, -11928, 0, 60, 1},
		{22516, -176586, 210546, -11923, 0, 60, 1},
		{22516, -175847, 209365, -11922, 0, 60, 1},
		{22520, -175496, 209498, -11924, 0, 60, 1},
		{22520, -175538, 210252, -11940, 0, 60, 1},
		{22524, -175527, 209744, -11928, 0, 60, 1},
		
		{22520, -177940, 210876, -12005, 0, 60, 1},
		{22520, -178935, 210903, -12018, 0, 60, 1},
		{22522, -179331, 211365, -12013, 0, 60, 1},
		{22522, -177637, 211579, -12015, 0, 60, 1},
		{22526, -177837, 212356, -12037, 0, 60, 1},
		{22526, -179030, 212261, -12018, 0, 60, 1},
		{22532, -178367, 212328, -12031, 0, 60, 1}
	};
	
	private static final int[][] ROOMS_TUMORS =
	{
		{18780, -180911, 211652, -12029, 49151, 240, 1},
		{18780, -180911, 206551, -12029, 16384, 240, 1},
		{18780, -178417, 206558, -12032, 16384, 240, 1},
		{18780, -178418, 211653, -12029, 49151, 240, 1},
		{18708, -183290, 210004, -11948, 61439, 0, 1},
		{18708, -183288, 208205, -11948, 4096, 0, 1},
		{18708, -176039, 208203, -11948, 28672, 0, 1},
		{18708, -176036, 210002, -11948, 36863, 0, 1},
		{18668, -179664, 209443, -12476, 16384, 120, 1},
		{18668, -179093, 209738, -12480, 40279, 120, 1},
		{18668, -178248, 209688, -12479, 24320, 120, 1},
		{18668, -177998, 209100, -12480, 16304, 120, 1},
		{18668, -178246, 208493, -12480, 8968, 120, 1},
		{18668, -178808, 208339, -12480, -1540, 120, 1},
		{18668, -179663, 208738, -12480, 0, 120, 1},
		{18668, -180498, 208330, -12467, 3208, 120, 1},
		{18668, -181070, 208502, -12467, -7552, 120, 1},
		{18668, -181310, 209097, -12467, -16408, 120, 1},
		{18668, -181069, 209698, -12467, -24792, 120, 1},
		{18668, -180228, 209744, -12467, 25920, 120, 1}
	};
	
	private static int[][] COHEMENES_SPAWN =
	{
		{25634, -178472, 211823, -12025, 0, 0, -1},
		{25634, -180926, 211887, -12029, 0, 0, -1},
		{25634, -180906, 206635, -12032, 0, 0, -1},
		{25634, -178492, 206426, -12023, 0, 0, -1}
	};
	//@formatter:on
	
	public HallOfErosionAttack()
	{
		addStartNpc(MOUTHOFEKIMUS);
		addTalkId(MOUTHOFEKIMUS);
		addStartNpc(TUMOR_DEAD);
		addTalkId(TUMOR_DEAD);
		
		addSpawnId(COHEMENES);
		addSpawnId(NOTMOVE);
		
		addAggroRangeEnterId(18668);
		
		addAttackId(COHEMENES);
		
		addKillId(TUMOR_ALIVE);
		addKillId(COHEMENES);
		addKillId(18711);
		
		tumorRespawnTime = 180 * 1000;
	}
	
	private void teleportPlayer(L2PcInstance player, int[] coords, int instanceId)
	{
		player.setInstanceId(instanceId);
		player.teleToLocation(coords[0], coords[1], coords[2]);
	}
	
	private boolean checkConditions(L2PcInstance player)
	{
		if (player.isGM())
		{
			return true;
		}
		
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
		
		final L2CommandChannel channel = party.getCommandChannel();
		if (channel == null)
		{
			player.sendPacket(SystemMessageId.YOU_CANNOT_ENTER_BECAUSE_YOU_ARE_NOT_ASSOCIATED_WITH_THE_CURRENT_COMMAND_CHANNEL);
			return false;
		}
		
		if (channel.getLeader() != player)
		{
			player.sendPacket(SystemMessageId.ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER);
			return false;
		}
		
		if ((party.getCommandChannel().getMembers().size() < Config.EROSION_ATTACK_MIN_PLAYERS) || (party.getCommandChannel().getMembers().size() > Config.EROSION_ATTACK_MAX_PLAYERS))// 18 27
		{
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY);
			party.getCommandChannel().broadcastPacket(sm);
			return false;
		}
		
		for (L2PcInstance partyMember : party.getCommandChannel().getMembers())
		{
			if ((partyMember.getLevel() < 75) || (partyMember.getLevel() > 85))
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(2097);
				sm.addPcName(partyMember);
				party.getCommandChannel().broadcastPacket(sm);
				return false;
			}
			
			if (!Util.checkIfInRange(1000, player, partyMember, true))
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(2096);
				sm.addPcName(partyMember);
				party.getCommandChannel().broadcastPacket(sm);
				return false;
			}
			
			final Long reentertime = InstanceManager.getInstance().getInstanceTime(partyMember.getObjectId(), INSTANCEID);
			if (System.currentTimeMillis() < reentertime)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(2100);
				sm.addPcName(partyMember);
				party.getCommandChannel().broadcastPacket(sm);
				return false;
			}
			
			final QuestState qs = partyMember.getQuestState(Q00696_ConquerTheHallOfErosion.class.getSimpleName());
			if ((qs == null) || !qs.isCond(1))
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_QUEST_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED);
				sm.addPcName(partyMember);
				player.getParty().getCommandChannel().broadcastPacket(sm);
				return false;
			}
		}
		return true;
	}
	
	protected void enterInstance(L2PcInstance player, int[] coords)
	{
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		
		if (world != null)
		{
			if (!(world instanceof HEAWorld))
			{
				player.sendPacket(SystemMessageId.YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON);
				return;
			}
			teleportPlayer(player, coords, world.getInstanceId());
			return;
		}
		
		if (checkConditions(player))
		{
			world = new HEAWorld();
			world.setInstance(InstanceManager.getInstance().createDynamicInstance(INSTANCEID));
			((HEAWorld) world).startTime = System.currentTimeMillis();
			InstanceManager.getInstance().addWorld(world);
			LOGGER.info("Hall Of Erosion Attack started " + INSTANCEID + " Instance: " + world.getInstanceId() + " created by player: " + player.getName());
			
			if (player.isInParty())
			{
				for (L2PcInstance partyMember : player.getParty().isInCommandChannel() ? player.getParty().getCommandChannel().getMembers() : player.getParty().getMembers())
				{
					teleportPlayer(partyMember, coords, world.getInstanceId());
					world.addAllowed(partyMember);
				}
			}
			else
			{
				teleportPlayer(player, coords, world.getInstanceId());
				world.addAllowed(player);
			}
			runTumors((HEAWorld) world);
		}
	}
	
	protected void runTumors(HEAWorld world)
	{
		for (int[] spawn : ROOMS_MOBS)
		{
			for (int i = 0; i < spawn[6]; i++)
			{
				final L2Npc npc = addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.getInstanceId());
				npc.getSpawn().setRespawnDelay(spawn[5]);
				npc.getSpawn().setAmount(1);
				if (spawn[5] > 0)
				{
					npc.getSpawn().startRespawn();
				}
				else
				{
					npc.getSpawn().stopRespawn();
				}
			}
		}
		
		for (int[] spawn : ROOMS_TUMORS)
		{
			for (int i = 0; i < spawn[6]; i++)
			{
				final L2Npc npc = addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.getInstanceId());
				npc.getSpawn().setRespawnDelay(spawn[5]);
				npc.getSpawn().setAmount(1);
				if (spawn[5] > 0)
				{
					npc.getSpawn().startRespawn();
				}
				else
				{
					npc.getSpawn().stopRespawn();
				}
				world.npcList.add(npc);
			}
		}
		broadCastPacket(world, new ExShowScreenMessage(NpcStringId.YOU_CAN_HEAR_THE_UNDEAD_OF_EKIMUS_RUSHING_TOWARD_YOU_S1_S2_IT_HAS_NOW_BEGUN, 2, 8000));
	}
	
	protected void stopTumors(HEAWorld world)
	{
		if (!world.npcList.isEmpty())
		{
			for (L2Npc npc : world.npcList)
			{
				if (npc != null)
				{
					npc.deleteMe();
				}
			}
		}
		world.npcList.clear();
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getPlayerWorld(player);
		if (tmpworld instanceof HEAWorld)
		{
			final HEAWorld world = (HEAWorld) tmpworld;
			
			if (event.startsWith("warp"))
			{
				L2Npc victim = null;
				victim = world.deadTumor;
				if (victim != null)
				{
					world.deadTumors.add(victim);
				}
				
				player.destroyItemByItemId("SOI", 13797, 1, player, true);
				final Location loc = world.deadTumors.get(Rnd.get(world.deadTumors.size())).getLocation();
				if (loc != null)
				{
					broadCastPacket(world, new ExShowScreenMessage(NpcStringId.S1_S_PARTY_HAS_MOVED_TO_A_DIFFERENT_LOCATION_THROUGH_THE_CRACK_IN_THE_TUMOR, 2, 8000));
					for (L2PcInstance partyMember : player.getParty().getMembers())
					{
						if (partyMember.isInsideRadius3D(player, 500))
						{
							partyMember.teleToLocation(loc, true);
						}
					}
				}
			}
		}
		return "";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		if (npc.getId() == MOUTHOFEKIMUS)
		{
			enterInstance(player, ENTER_TELEPORT);
			return "";
		}
		return "";
	}
	
	@Override
	public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof HEAWorld)
		{
			final HEAWorld world = (HEAWorld) tmpworld;
			
			if (npc.getId() == 18668)
			{
				for (int i = 0; i < Rnd.get(1, 4); i++)
				{
					addSpawn(mobs[Rnd.get(mobs.length)], npc.getLocation(), world.getInstanceId());
				}
				npc.doDie(npc);
			}
		}
		return super.onAggroRangeEnter(npc, player, isSummon);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon, Skill skill)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof HEAWorld)
		{
			final HEAWorld world = (HEAWorld) tmpworld;
			if (!world.isBossAttacked)
			{
				world.isBossAttacked = true;
				final Calendar reenter = Calendar.getInstance();
				reenter.add(Calendar.HOUR, INSTANCEPENALTY);
				
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.INSTANT_ZONE_S1_S_ENTRY_HAS_BEEN_RESTRICTED_YOU_CAN_CHECK_THE_NEXT_POSSIBLE_ENTRY_TIME_BY_USING_THE_COMMAND_INSTANCEZONE);
				sm.addInstanceName(INSTANCEID);
				
				for (L2PcInstance player : tmpworld.getAllowed())
				{
					if ((player != null) && player.isOnline())
					{
						InstanceManager.getInstance().setInstanceTime(player.getObjectId(), INSTANCEID, reenter.getTimeInMillis());
						player.sendPacket(sm);
					}
				}
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon, skill);
	}
	
	@Override
	public final String onSpawn(L2Npc npc)
	{
		if (CommonUtil.contains(NOTMOVE, npc.getId()))
		{
			npc.setRandomWalking(false);
			npc.setIsImmobilized(true);
		}
		
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof HEAWorld)
		{
			final HEAWorld world = (HEAWorld) tmpworld;
			if (npc.getId() == TUMOR_ALIVE)
			{
				world.addTumorCount(1);
				if ((world.tumorCount == 4) && (world.cohemenes != null))
				{
					world.cohemenes.deleteMe();
					broadCastPacket(world, new ExShowScreenMessage(NpcStringId.YOU_HAVE_FAILED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE, 2, 8000));
					finishInstance(world);
					conquestEnded = true;
					stopTumors(world);
				}
			}
			
			if (npc.getId() == TUMOR_DEAD)
			{
				final int tag = world.getParameters().getInt("tag", -1);
				world.setParameter("tag", tag + 1);
			}
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof HEAWorld)
		{
			final HEAWorld world = (HEAWorld) tmpworld;
			final Location loc = npc.getLocation();
			if (npc.getId() == TUMOR_ALIVE)
			{
				world.addTumorCount(-1);
				npc.dropItem(player, 13797, Rnd.get(2, 5));
				npc.deleteMe();
				world.deadTumor = addSpawn(TUMOR_DEAD, loc, world.getInstanceId());
				world.deadTumors.add(world.deadTumor);
				ThreadPool.schedule(new TumorRevival(world.deadTumor, world), tumorRespawnTime);
				ThreadPool.schedule(new RegenerationCoffinSpawn(world.deadTumor, world), 20000);
				if (world.tumorCount >= 1)
				{
					broadCastPacket(world, new ExShowScreenMessage(NpcStringId.THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NIN_ORDER_TO_DRAW_OUT_THE_COWARDLY_COHEMENES_YOU_MUST_DESTROY_ALL_THE_TUMORS, 2, 8000));
				}
				
				if ((world.tumorCount == 0) && (world.cohemenes == null))
				{
					broadCastPacket(world, new ExShowScreenMessage(NpcStringId.ALL_THE_TUMORS_INSIDE_S1_HAVE_BEEN_DESTROYED_DRIVEN_INTO_A_CORNER_COHEMENES_APPEARS_CLOSE_BY, 2, 8000));
					final int[] spawn = COHEMENES_SPAWN[Rnd.get(0, COHEMENES_SPAWN.length - 1)];
					final L2Npc n = addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.getInstanceId());
					n.broadcastPacket(new NpcSay(n.getObjectId(), ChatType.SHOUT, n.getId(), NpcStringId.C_MON_C_MON_SHOW_YOUR_FACE_YOU_LITTLE_RATS_LET_ME_SEE_WHAT_THE_DOOMED_WEAKLINGS_ARE_SCHEMING));
					world.cohemenes = n;
				}
			}
			if (npc.getId() == COHEMENES)
			{
				npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.SHOUT, npc.getId(), NpcStringId.KEU_I_WILL_LEAVE_FOR_NOW_BUT_DON_T_THINK_THIS_IS_OVER_THE_SEED_OF_INFINITY_CAN_NEVER_DIE));
				for (L2PcInstance plr : world.getAllowed())
				{
					if (plr != null)
					{
						final QuestState st = plr.getQuestState(Q00696_ConquerTheHallOfErosion.class.getSimpleName());
						if ((st != null) && (st.getInt("cond") == 1))
						{
							st.set("cohemenes", "1");
						}
					}
				}
				broadCastPacket(world, new ExShowScreenMessage(NpcStringId.CONGRATULATIONS_YOU_HAVE_SUCCEEDED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE, 2, 8000));
				world.cohemenes = null;
				conquestEnded = true;
				finishInstance(world);
				stopTumors(world);
				SoIManager.notifyCohemenesKill();
			}
			
			if (npc.getId() == 18711)
			{
				tumorRespawnTime += 10 * 1000;
			}
		}
		return "";
	}
	
	private static void finishInstance(InstanceWorld world)
	{
		if (world instanceof HEAWorld)
		{
			final Calendar reenter = Calendar.getInstance();
			reenter.set(Calendar.MINUTE, 30);
			
			if (reenter.get(Calendar.HOUR_OF_DAY) >= 6)
			{
				reenter.add(Calendar.DATE, 1);
			}
			reenter.set(Calendar.HOUR_OF_DAY, 6);
			
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.INSTANT_ZONE_S1_S_ENTRY_HAS_BEEN_RESTRICTED_YOU_CAN_CHECK_THE_NEXT_POSSIBLE_ENTRY_TIME_BY_USING_THE_COMMAND_INSTANCEZONE);
			sm.addInstanceName(INSTANCEID);
			
			for (L2PcInstance plr : world.getAllowed())
			{
				if (plr != null)
				{
					InstanceManager.getInstance().setInstanceTime(plr.getObjectId(), INSTANCEID, reenter.getTimeInMillis());
					if (plr.isOnline())
					{
						plr.sendPacket(sm);
					}
				}
			}
			final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
			inst.setDuration(5 * 60000);
			inst.setEmptyDestroyTime(0);
		}
	}
	
	private class TumorRevival implements Runnable
	{
		private final L2Npc _deadTumor;
		private final HEAWorld _world;
		
		public TumorRevival(L2Npc deadTumor, HEAWorld world)
		{
			_deadTumor = world.deadTumor;
			_world = world;
		}
		
		@Override
		public void run()
		{
			if (conquestEnded)
			{
				return;
			}
			
			final L2Npc tumor = addSpawn(TUMOR_ALIVE, _deadTumor.getLocation(), _world.getInstanceId());
			_world.npcList.add(tumor);
			_deadTumor.deleteMe();
			final int tag = _world.getParameters().getInt("tag", -1);
			_world.setParameter("tag", tag - 1);
		}
	}
	
	private class RegenerationCoffinSpawn implements Runnable
	{
		private final L2Npc _deadTumor;
		private final HEAWorld _world;
		
		public RegenerationCoffinSpawn(L2Npc deadTumor, HEAWorld world)
		{
			_deadTumor = world.deadTumor;
			_world = world;
		}
		
		@Override
		public void run()
		{
			if (conquestEnded)
			{
				return;
			}
			for (int i = 0; i < 4; i++)
			{
				final L2Npc worm = addSpawn(18710, _deadTumor.getLocation(), _world.getInstanceId());
				_world.npcList.add(worm);
			}
		}
	}
	
	protected void broadCastPacket(HEAWorld world, IClientOutgoingPacket packet)
	{
		for (L2PcInstance player : world.getAllowed())
		{
			if ((player != null) && player.isOnline() && (player.getInstanceId() == world.getInstanceId()))
			{
				player.sendPacket(packet);
			}
		}
	}
}
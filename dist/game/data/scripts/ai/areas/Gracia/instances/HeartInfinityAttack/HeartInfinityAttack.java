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
package ai.areas.Gracia.instances.HeartInfinityAttack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.instancemanager.SoIManager;
import com.l2jmobius.gameserver.instancemanager.ZoneManager;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
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

public class HeartInfinityAttack extends AbstractNpcAI
{
	private class HIAWorld extends InstanceWorld
	{
		public List<L2Npc> npcList = new ArrayList<>();
		public List<L2Npc> startroom = new ArrayList<>();
		public List<L2Npc> deadTumors = new ArrayList<>();
		protected L2Npc ekimus;
		protected L2Npc deadTumor;
		protected List<L2Npc> hounds = new ArrayList<>(2);
		public int tumorCount = 0;
		public boolean isBossAttacked = false;
		public long startTime = 0;
		protected ScheduledFuture<?> timerTask;
		
		public synchronized void addTumorCount(int value)
		{
			tumorCount += value;
		}
		
		public HIAWorld()
		{
		}
	}
	
	private static final String qn = "HeartInfinityAttack";
	private static final int INSTANCEID = 121;
	private static final int INSTANCEPENALTY = 24;
	
	private static final int ABYSSGAZE = 32540;
	private static final int ALIVETUMOR = 18708;
	private static final int DEADTUMOR = 32535;
	private static final int EKIMUS = 29150;
	private static final int HOUND = 29151;
	private static final int COFFIN = 18710;
	
	private long tumorRespawnTime;
	private boolean conquestBegun = false;
	protected boolean conquestEnded = false;
	private boolean houndBlocked = false;
	
	private static final int[] ENTER_TELEPORT =
	{
		-179284,
		205990,
		-15520
	};
	
	private static int[] NOTMOVE =
	{
		18668,
		18711,
		ALIVETUMOR,
		DEADTUMOR
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
		{22520, -179949, 206751, -15521, 0, 60, 1},
		{22522, -179949, 206505, -15522, 0, 60, 1},
		{22532, -180207, 206362, -15512, 0, 60, 1},
		{22520, -180046, 206109, -15511, 0, 60, 1},
		{22526, -179843, 205686, -15511, 0, 60, 1},
		{22522, -179658, 206002, -15518, 0, 60, 1},
		{22526, -179465, 205648, -15498, 0, 60, 1},
		{22520, -179262, 205896, -15536, 0, 60, 1},
		{22526, -178907, 205950, -15509, 0, 60, 1},
		{22532, -179128, 206423, -15528, 0, 60, 1},
		{22522, -179262, 206890, -15524, 0, 60, 1},
		{22516, -177238, 208020, -15521, 0, 60, 1},
		{22522, -177202, 207533, -15516, 0, 60, 1},
		{22532, -176900, 207515, -15511, 0, 60, 1},
		{22524, -176847, 208022, -15523, 0, 60, 1},
		{22516, -176187, 207648, -15499, 0, 60, 1},
		{22522, -176044, 208039, -15524, 0, 60, 1},
		{22532, -176346, 208233, -15533, 0, 60, 1},
		{22524, -176440, 208594, -15527, 0, 60, 1},
		{22516, -176938, 208543, -15529, 0, 60, 1},
		{22524, -176763, 208108, -15523, 0, 60, 1},
		{22522, -176483, 207762, -15520, 0, 60, 1},
		{22526, -177125, 210766, -15517, 0, 60, 1},
		{22520, -176810, 210587, -15515, 0, 60, 1},
		{22522, -176820, 210975, -15528, 0, 60, 1},
		{22524, -176412, 210873, -15510, 0, 60, 1},
		{22526, -176261, 211197, -15511, 0, 60, 1},
		{22520, -176241, 211649, -15503, 0, 60, 1},
		{22522, -176656, 211884, -15516, 0, 60, 1},
		{22524, -176798, 211540, -15541, 0, 60, 1},
		{22526, -177130, 211694, -15533, 0, 60, 1},
		{22520, -177436, 211384, -15519, 0, 60, 1},
		{22522, -177138, 211159, -15524, 0, 60, 1},
		{22526, -179311, 212338, -15521, 0, 60, 1},
		{22522, -178985, 212502, -15513, 0, 60, 1},
		{22516, -179313, 212730, -15522, 0, 60, 1},
		{22520, -178966, 213036, -15510, 0, 60, 1},
		{22526, -179352, 213172, -15511, 0, 60, 1},
		{22522, -179696, 213427, -15514, 0, 60, 1},
		{22516, -180096, 213140, -15519, 0, 60, 1},
		{22520, -179957, 212724, -15530, 0, 60, 1},
		{22522, -180240, 212578, -15518, 0, 60, 1},
		{22520, -179891, 212271, -15520, 0, 60, 1},
		{22516, -179593, 212488, -15523, 0, 60, 1},
		{22516, -181746, 211163, -15515, 0, 60, 1},
		{22522, -182002, 211394, -15522, 0, 60, 1},
		{22526, -181796, 211647, -15511, 0, 60, 1},
		{22516, -182160, 211615, -15518, 0, 60, 1},
		{22520, -182322, 211843, -15510, 0, 60, 1},
		{22522, -182720, 211686, -15519, 0, 60, 1},
		{22520, -182721, 211231, -15535, 0, 60, 1},
		{22516, -182957, 210874, -15515, 0, 60, 1},
		{22526, -182705, 210606, -15526, 0, 60, 1},
		{22520, -182359, 210871, -15526, 0, 60, 1},
		{22522, -182051, 210644, -15521, 0, 60, 1},
		{22524, -181994, 208452, -15514, 0, 60, 1},
		{22516, -182342, 208661, -15510, 0, 60, 1},
		{22526, -182471, 208349, -15519, 0, 60, 1},
		{22520, -182960, 208335, -15504, 0, 60, 1},
		{22524, -182751, 207925, -15514, 0, 60, 1},
		{22516, -182671, 207442, -15531, 0, 60, 1},
		{22520, -182276, 207438, -15527, 0, 60, 1},
		{22526, -181975, 207353, -15520, 0, 60, 1},
		{22524, -181989, 207795, -15527, 0, 60, 1},
		{22516, -181744, 207997, -15520, 0, 60, 1},
		{22520, -182318, 208070, -15523, 0, 60, 1},
		{18667, -178430, 211520, -15504, 28672, 120, 1},
		{18668, -177341, 209129, -15504, 16384, 120, 1},
		{18711, -178067, 207862, -15504, 8192, 120, 1},
		{18667, -180679, 207614, -15520, 61439, 120, 1},
		{18668, -181761, 209949, -15520, 49151, 120, 1},
		{18711, -180328, 211686, -15504, 36863, 120, 1}
	};
	
	private static final int[][] ROOMS_TUMORS =
	{
		{18708, -179779, 212540, -15520, 49151, 0, 1},
		{18708, -177028, 211135, -15520, 36863, 0, 1},
		{18708, -176355, 208043, -15520, 28672, 0, 1},
		{18708, -179284, 205990, -15520, 16384, 0, 1},
		{18708, -182268, 208218, -15520, 4096, 0, 1},
		{18708, -182069, 211140, -15520, 61439, 0, 1}
	};
	
	protected static final int[][] ROOMS_BOSSES =
	{
		{25637, -179303, 213090, -15504, 49151, 0, 1},
		{25638, -176426, 211219, -15504, 36863, 0, 1},
		{25639, -177040, 207870, -15504, 28672, 0, 1},
		{25640, -179762, 206479, -15504, 16384, 0, 1},
		{25641, -182388, 207599, -15504, 4096, 0, 1},
		{25642, -182733, 211096, -15504, 61439, 0, 1}
	};
	//@formatter:on
	
	public HeartInfinityAttack()
	{
		addStartNpc(ABYSSGAZE);
		addTalkId(ABYSSGAZE);
		addStartNpc(DEADTUMOR);
		addTalkId(DEADTUMOR);
		addStartNpc(32536);
		addTalkId(32536);
		
		for (int id : NOTMOVE)
		{
			addSpawnId(id);
		}
		
		addAggroRangeEnterId(18668);
		
		addAttackId(EKIMUS);
		
		addKillId(ALIVETUMOR);
		addKillId(EKIMUS);
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
		if ((party.getCommandChannel() == null) || (party.getCommandChannel().getLeader() != player))
		{
			player.sendPacket(SystemMessageId.ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER);
			return false;
		}
		if ((party.getCommandChannel().getMembers().size() < Config.HEART_ATTACK_MIN_PLAYERS) || (party.getCommandChannel().getMembers().size() > Config.HEART_ATTACK_MAX_PLAYERS))
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
		}
		return true;
	}
	
	protected void enterInstance(L2PcInstance player, int[] coords)
	{
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		
		if (world != null)
		{
			if (!(world instanceof HIAWorld))
			{
				player.sendPacket(SystemMessageId.YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON);
				return;
			}
			teleportPlayer(player, coords, world.getInstanceId());
			return;
		}
		
		if (checkConditions(player))
		{
			world = new HIAWorld();
			world.setInstance(InstanceManager.getInstance().createDynamicInstance(INSTANCEID));
			InstanceManager.getInstance().addWorld(world);
			LOGGER.info("Heart Infinity Attack started " + INSTANCEID + " Instance: " + world.getInstanceId() + " created by player: " + player.getName());
			
			if ((player.getParty() == null) || (player.getParty().getCommandChannel() == null))
			{
				teleportPlayer(player, coords, world.getInstanceId());
				world.addAllowed(player);
			}
			else
			{
				for (L2PcInstance partyMember : player.getParty().getCommandChannel().getMembers())
				{
					teleportPlayer(partyMember, coords, world.getInstanceId());
					world.addAllowed(partyMember);
					if (partyMember.getQuestState(qn) == null)
					{
						newQuestState(partyMember);
					}
				}
			}
			broadCastPacket(((HIAWorld) world), new ExShowScreenMessage(NpcStringId.YOU_WILL_PARTICIPATE_IN_S1_S2_SHORTLY_BE_PREPARED_FOR_ANYTHING, 2, 8000));
			final L2Npc npc = addSpawn(32536, -179376, 206111, -15538, 16384, false, 0, false, ((HIAWorld) world).getInstanceId());
			((HIAWorld) world).startroom.add(npc);
		}
	}
	
	protected void notifyEchmusEntrance(HIAWorld world)
	{
		if (conquestBegun)
		{
			return;
		}
		
		conquestBegun = true;
		ThreadPool.schedule(() ->
		{
			playMovie(world, Movie.SC_ECHMUS_OPENING);
			ThreadPool.schedule(() -> conquestBegins(world), 62500);
		}, 20000);
	}
	
	protected void conquestBegins(HIAWorld world)
	{
		if (!world.startroom.isEmpty())
		{
			for (L2Npc npc : world.startroom)
			{
				if (npc != null)
				{
					npc.deleteMe();
				}
			}
			world.startroom.clear();
		}
		
		for (int[] spawn : ROOMS_TUMORS)
		{
			for (int i = 0; i < spawn[6]; i++)
			{
				final L2Npc npc = addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.getInstanceId());
				npc.setCurrentHp(npc.getMaxHp() * .5);
				world.npcList.add(npc);
			}
		}
		
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
		
		InstanceManager.getInstance().getInstance(world.getInstanceId()).getDoor(14240102).openMe();
		broadCastPacket(world, new ExShowScreenMessage(NpcStringId.YOU_CAN_HEAR_THE_UNDEAD_OF_EKIMUS_RUSHING_TOWARD_YOU_S1_S2_IT_HAS_NOW_BEGUN, 2, 8000));
		world.ekimus = addSpawn(EKIMUS, -179537, 208854, -15504, 16384, false, 0, false, world.getInstanceId());
		final NpcSay cs = new NpcSay(world.ekimus.getObjectId(), ChatType.SHOUT, world.ekimus.getId(), NpcStringId.I_SHALL_ACCEPT_YOUR_CHALLENGE_S1_COME_AND_DIE_IN_THE_ARMS_OF_IMMORTALITY);
		world.ekimus.broadcastPacket(cs);
		world.hounds.add(addSpawn(HOUND, -179224, 209624, -15504, 16384, false, 0, false, world.getInstanceId()));
		world.hounds.add(addSpawn(HOUND, -179880, 209464, -15504, 16384, false, 0, false, world.getInstanceId()));
		world.startTime = System.currentTimeMillis();
		world.timerTask = ThreadPool.scheduleAtFixedRate(new TimerTask(world), 298 * 1000, 5 * 60 * 1000);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getPlayerWorld(player);
		if (tmpworld instanceof HIAWorld)
		{
			final HIAWorld world = (HIAWorld) tmpworld;
			
			if (event.startsWith("warpechmus"))
			{
				broadCastPacket(world, new ExShowScreenMessage(NpcStringId.S1_S_PARTY_HAS_MOVED_TO_A_DIFFERENT_LOCATION_THROUGH_THE_CRACK_IN_THE_TUMOR, 2, 8000));
				for (L2PcInstance partyMember : player.getParty().getMembers())
				{
					if (partyMember.isInsideRadius3D(player, 800))
					{
						partyMember.teleToLocation(-179548, 209584, -15504, true);
					}
				}
				notifyEchmusEntrance(world);
			}
			else if (event.startsWith("reenterechmus"))
			{
				player.destroyItemByItemId("SOI", 13797, 3, player, true);
				notifyEkimusRoomEntrance(world);
				for (L2PcInstance partyMember : player.getParty().getMembers())
				{
					if (partyMember.isInsideRadius3D(player, 400))
					{
						partyMember.teleToLocation(-179548, 209584, -15504, true);
					}
				}
			}
			else if (event.startsWith("warp"))
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
		final int npcId = npc.getId();
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		
		if (npcId == ABYSSGAZE)
		{
			enterInstance(player, ENTER_TELEPORT);
		}
		return "";
	}
	
	@Override
	public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof HIAWorld)
		{
			final HIAWorld world = (HIAWorld) tmpworld;
			
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
		if (tmpworld instanceof HIAWorld)
		{
			final HIAWorld world = (HIAWorld) tmpworld;
			if (!world.isBossAttacked)
			{
				world.isBossAttacked = true;
				final Calendar reenter = Calendar.getInstance();
				reenter.add(Calendar.HOUR, INSTANCEPENALTY);
				
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.INSTANT_ZONE_S1_S_ENTRY_HAS_BEEN_RESTRICTED_YOU_CAN_CHECK_THE_NEXT_POSSIBLE_ENTRY_TIME_BY_USING_THE_COMMAND_INSTANCEZONE);
				sm.addInstanceName(INSTANCEID);
				
				for (L2PcInstance player : tmpworld.getAllowed())
				{
					if (player != null)
					{
						InstanceManager.getInstance().setInstanceTime(player.getObjectId(), INSTANCEID, reenter.getTimeInMillis());
						if (player.isOnline())
						{
							player.sendPacket(sm);
						}
					}
				}
			}
			
			if (npc.getId() == EKIMUS)
			{
				for (L2Npc mob : world.hounds)
				{
					((L2MonsterInstance) mob).addDamageHate(attacker, 0, 500);
					mob.setRunning();
					mob.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);
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
		
		if (npc.getId() == HOUND)
		{
			npc.setRandomWalking(false);
			npc.setIsImmobilized(true);
		}
		
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof HIAWorld)
		{
			final HIAWorld world = (HIAWorld) tmpworld;
			if (npc.getId() == ALIVETUMOR)
			{
				world.addTumorCount(1);
				handleEkimusStats(world);
			}
			
			if (npc.getId() == DEADTUMOR)
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
		if (tmpworld instanceof HIAWorld)
		{
			final HIAWorld world = (HIAWorld) tmpworld;
			final Location loc = npc.getLocation();
			if (npc.getId() == ALIVETUMOR)
			{
				world.addTumorCount(-1);
				npc.dropItem(player, 13797, Rnd.get(2, 5));
				npc.deleteMe();
				world.deadTumor = addSpawn(DEADTUMOR, loc, world.getInstanceId());
				world.deadTumors.add(world.deadTumor);
				ThreadPool.schedule(new TumorRevival(world.deadTumor, world), tumorRespawnTime);
				ThreadPool.schedule(new RegenerationCoffinSpawn(world.deadTumor, world), 20000);
				if (world.tumorCount < 1)
				{
					houndBlocked = true;
					for (L2Npc hound : world.hounds)
					{
						hound.setIsInvul(true);
					}
					broadCastPacket(world, new ExShowScreenMessage(NpcStringId.WITH_ALL_CONNECTIONS_TO_THE_TUMOR_SEVERED_EKIMUS_HAS_LOST_ITS_POWER_TO_CONTROL_THE_FERAL_HOUND, 2, 8000));
				}
				else
				{
					broadCastPacket(world, new ExShowScreenMessage(NpcStringId.THE_TUMOR_INSIDE_S1_THAT_HAS_PROVIDED_ENERGY_N_TO_EKIMUS_IS_DESTROYED, 2, 8000));
				}
				handleEkimusStats(world);
			}
			
			if (npc.getId() == EKIMUS)
			{
				conquestConclusion(world);
				SoIManager.notifyEkimusKill();
			}
			
			if (npc.getId() == 18711)
			{
				tumorRespawnTime += 8 * 1000;
			}
		}
		return "";
	}
	
	private class TumorRevival implements Runnable
	{
		private final L2Npc _deadTumor;
		private final HIAWorld _world;
		
		public TumorRevival(L2Npc deadTumor, HIAWorld world)
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
			final L2Npc alivetumor = addSpawn(ALIVETUMOR, _deadTumor.getLocation(), _world.getInstanceId());
			alivetumor.setCurrentHp(alivetumor.getMaxHp() * .25);
			notifyTumorRevival(_world);
			_world.npcList.add(alivetumor);
			_deadTumor.deleteMe();
			final int tag = _world.getParameters().getInt("tag", -1);
			_world.setParameter("tag", tag - 1);
		}
	}
	
	private class TimerTask implements Runnable
	{
		private final HIAWorld _world;
		
		TimerTask(HIAWorld world)
		{
			_world = world;
		}
		
		@Override
		public void run()
		{
			final long time = ((_world.startTime + (25 * 60 * 1000)) - System.currentTimeMillis()) / 60000;
			if (time == 0)
			{
				broadCastPacket(_world, new ExShowScreenMessage(NpcStringId.YOU_HAVE_FAILED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE, 2, 8000));
				if (_world != null)
				{
					conquestEnded = true;
					final Instance inst = InstanceManager.getInstance().getInstance(_world.getInstanceId());
					if (inst != null)
					{
						inst.removeNpcs();
						if (inst.getPlayers().isEmpty())
						{
							inst.setDuration(5 * 60000);
						}
						else
						{
							inst.setDuration(5 * 60000);
							inst.setEmptyDestroyTime(5 * 60000);
						}
					}
				}
			}
			else
			{
				for (int[] spawn : ROOMS_BOSSES)
				{
					for (int i = 0; i < spawn[6]; i++)
					{
						addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, _world.getInstanceId());
					}
				}
			}
		}
	}
	
	protected void notifyTumorRevival(HIAWorld world)
	{
		if ((world.tumorCount == 1) && houndBlocked)
		{
			houndBlocked = false;
			for (L2Npc hound : world.hounds)
			{
				hound.setIsInvul(false);
			}
			broadCastPacket(world, new ExShowScreenMessage(NpcStringId.WITH_THE_CONNECTION_TO_THE_TUMOR_RESTORED_EKIMUS_HAS_REGAINED_CONTROL_OVER_THE_FERAL_HOUND, 2, 8000));
		}
		else
		{
			broadCastPacket(world, new ExShowScreenMessage(NpcStringId.THE_TUMOR_INSIDE_S1_HAS_BEEN_COMPLETELY_RESURRECTED_N_AND_STARTED_TO_ENERGIZE_EKIMUS_AGAIN, 2, 8000));
		}
		handleEkimusStats(world);
	}
	
	private class RegenerationCoffinSpawn implements Runnable
	{
		private final L2Npc _deadTumor;
		private final HIAWorld _world;
		
		public RegenerationCoffinSpawn(L2Npc deadTumor, HIAWorld world)
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
				final L2Npc worm = addSpawn(COFFIN, _deadTumor.getLocation(), _world.getInstanceId());
				_world.npcList.add(worm);
			}
		}
	}
	
	private void handleEkimusStats(HIAWorld world)
	{
		// TODO FIXME
		// double[] a = getStatMultiplier(world);
		// world.ekimus.removeStatsOwner(this);
		// world.ekimus.addStatFunc(new FuncGet(Stats.POWER_ATTACK, 0x30, this, world.ekimus.getTemplate().getBasePAtk() * 3));
		// world.ekimus.addStatFunc(new FuncGet(Stats.MAGIC_ATTACK, 0x30, this, world.ekimus.getTemplate().getBaseMAtk() * 10));
		// world.ekimus.addStatFunc(new FuncGet(Stats.POWER_DEFENCE, 0x30, this, world.ekimus.getTemplate().getBasePDef() * a[1]));
		// world.ekimus.addStatFunc(new FuncGet(Stats.MAGIC_DEFENCE, 0x30, this, world.ekimus.getTemplate().getBaseMDef() * a[0]));
		// world.ekimus.addStatFunc(new FuncGet(Stats.REGENERATE_HP_RATE, 0x30, this, world.ekimus.getTemplate().getBaseHpReg() * a[2]));
	}
	
	@SuppressWarnings("unused")
	private double[] getStatMultiplier(HIAWorld world)
	{
		final double[] a = new double[3];
		switch (world.tumorCount)
		{
			case 6:
			{
				a[0] = 2;
				a[1] = 1;
				a[2] = 4;
				break;
			}
			case 5:
			{
				a[0] = 1.9;
				a[1] = 0.9;
				a[2] = 3.5;
				break;
			}
			case 4:
			{
				a[0] = 1.5;
				a[1] = 0.6;
				a[2] = 3.0;
				break;
			}
			case 3:
			{
				a[0] = 1.0;
				a[1] = 0.4;
				a[2] = 2.5;
				break;
			}
			case 2:
			{
				a[0] = 0.7;
				a[1] = 0.3;
				a[2] = 2.0;
				break;
			}
			case 1:
			{
				a[0] = 0.3;
				a[1] = 0.15;
				a[2] = 1.0;
				break;
			}
			case 0:
			{
				a[0] = 0.12;
				a[1] = 0.06;
				a[2] = 0.25;
				break;
			}
		}
		return a;
	}
	
	public void notifyEkimusRoomEntrance(HIAWorld world)
	{
		for (L2PcInstance ch : ZoneManager.getInstance().getZoneById(200032).getPlayersInside())
		{
			if (ch != null)
			{
				ch.teleToLocation(-179537, 211233, -15472, true);
			}
		}
		
		ThreadPool.schedule(() -> broadCastPacket(world, new ExShowScreenMessage(NpcStringId.EKIMUS_HAS_SENSED_ABNORMAL_ACTIVITY_NTHE_ADVANCING_PARTY_IS_FORCEFULLY_EXPELLED, 2, 8000)), 10000);
	}
	
	protected void conquestConclusion(HIAWorld world)
	{
		if (world.timerTask != null)
		{
			world.timerTask.cancel(false);
		}
		
		broadCastPacket(world, new ExShowScreenMessage(NpcStringId.CONGRATULATIONS_YOU_HAVE_SUCCEEDED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE, 2, 8000));
		conquestEnded = true;
		final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
		if (inst != null)
		{
			inst.removeNpcs();
			if (inst.getPlayers().isEmpty())
			{
				inst.setDuration(5 * 60000);
			}
			else
			{
				inst.setDuration(5 * 60000);
				inst.setEmptyDestroyTime(5 * 60000);
			}
		}
	}
	
	protected void broadCastPacket(HIAWorld world, IClientOutgoingPacket packet)
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
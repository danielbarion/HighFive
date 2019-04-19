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
package ai.areas.Hellbound.AI.Zones.TowerOfNaia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.DoorData;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.GlobalVariablesManager;
import com.l2jmobius.gameserver.instancemanager.ZoneManager;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.zone.L2ZoneType;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.util.MinionList;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Tower Of Naia.
 * @author GKR
 */
public final class TowerOfNaia extends AbstractNpcAI
{
	// Challenge states
	private static final int STATE_SPORE_CHALLENGE_IN_PROGRESS = 1;
	private static final int STATE_SPORE_CHALLENGE_SUCCESSFULL = 2;
	private static final int STATE_SPORE_IDLE_TOO_LONG = 3;
	
	// Some constants
	private static final int SELF_DESPAWN_LIMIT = 600; // Challenge discontinues after 600 self-despawns by timer
	private static final int ELEMENT_INDEX_LIMIT = 120; // Epidos spawns when index reaches 120 points
	
	private static final int LOCK = 18491;
	private static final int CONTROLLER = 18492;
	private static final int ROOM_MANAGER_FIRST = 18494;
	private static final int ROOM_MANAGER_LAST = 18505;
	private static final int MUTATED_ELPY = 25604;
	private static final int SPORE_BASIC = 25613;
	private static final int SPORE_FIRE = 25605;
	private static final int SPORE_WATER = 25606;
	private static final int SPORE_WIND = 25607;
	private static final int SPORE_EARTH = 25608;
	private static final int DWARVEN_GHOST = 32370;
	private static final int[] EPIDOSES =
	{
		25610,
		25609,
		25612,
		25611
	}; // Order is important!
	private static final int[] TOWER_MONSTERS =
	{
		18490,
		22393,
		22394,
		22395,
		22411,
		22412,
		22413,
		22439,
		22440,
		22441,
		22442
	};
	private static final int[] ELEMENTS =
	{
		25605,
		25606,
		25607,
		25608
	};
	private static final int[] OPPOSITE_ELEMENTS =
	{
		25606,
		25605,
		25608,
		25607
	};
	private static final String[] ELEMENTS_NAME =
	{
		"Fire",
		"Water",
		"Wind",
		"Earth"
	};
	//@formatter:off
	private static final int[][] SPORES_MOVE_POINTS =
	{
		{-46080, 246368, -14183},
		{-44816, 246368, -14183},
		{-44224, 247440, -14184},
		{-44896, 248464, -14183},
		{-46064, 248544, -14183},
		{-46720, 247424, -14183},
	};
	private static final int[][] SPORES_MERGE_POSITION =
	{
		{-45488, 246768, -14183},
		{-44767, 247419, -14183},
		{-46207, 247417, -14183},
		{-45462, 248174, -14183},
	};
	//@formatter:on
	private static final NpcStringId[] SPORES_NPCSTRING_ID =
	{
		NpcStringId.IT_S_S1,
		NpcStringId.S1_IS_STRONG,
		NpcStringId.IT_S_ALWAYS_S1,
		NpcStringId.S1_WON_T_DO
	};
	
	private static Map<Integer, int[]> DOORS = new HashMap<>();
	private static Map<Integer, Integer> ZONES = new HashMap<>();
	private static Map<Integer, int[][]> SPAWNS = new HashMap<>();
	
	private L2MonsterInstance _lock;
	private final L2Npc _controller;
	private int _counter;
	private final AtomicInteger _despawnedSporesCount = new AtomicInteger();
	private final int[] _indexCount =
	{
		0,
		0
	};
	private int _challengeState;
	private int _winIndex;
	
	private final Map<Integer, Boolean> _activeRooms = new HashMap<>();
	private final Map<Integer, List<L2Npc>> _spawns = new ConcurrentHashMap<>();
	private final Set<L2Npc> _sporeSpawn = ConcurrentHashMap.newKeySet();
	static
	{
		// Format: entrance_door, exit_door
		DOORS.put(18494, new int[]
		{
			18250001,
			18250002
		});
		DOORS.put(18495, new int[]
		{
			18250003,
			18250004
		});
		DOORS.put(18496, new int[]
		{
			18250005,
			18250006
		});
		DOORS.put(18497, new int[]
		{
			18250007,
			18250008
		});
		DOORS.put(18498, new int[]
		{
			18250009,
			18250010
		});
		DOORS.put(18499, new int[]
		{
			18250011,
			18250101
		});
		DOORS.put(18500, new int[]
		{
			18250013,
			18250014
		});
		DOORS.put(18501, new int[]
		{
			18250015,
			18250102
		});
		DOORS.put(18502, new int[]
		{
			18250017,
			18250018
		});
		DOORS.put(18503, new int[]
		{
			18250019,
			18250103
		});
		DOORS.put(18504, new int[]
		{
			18250021,
			18250022
		});
		DOORS.put(18505, new int[]
		{
			18250023,
			18250024
		});
		
		ZONES.put(18494, 200020);
		ZONES.put(18495, 200021);
		ZONES.put(18496, 200022);
		ZONES.put(18497, 200023);
		ZONES.put(18498, 200024);
		ZONES.put(18499, 200025);
		ZONES.put(18500, 200026);
		ZONES.put(18501, 200027);
		ZONES.put(18502, 200028);
		ZONES.put(18503, 200029);
		ZONES.put(18504, 200030);
		ZONES.put(18505, 200031);
		//@formatter:off
		SPAWNS.put(18494, new int[][]
		{
			{22393, -46371, 246400, -9120, 0},
			{22394, -46435, 245830, -9120, 0},
			{22394, -46536, 246275, -9120, 0},
			{22393, -46239, 245996, -9120, 0},
			{22394, -46229, 246347, -9120, 0},
			{22394, -46019, 246198, -9120, 0},
		});
		SPAWNS.put(18495, new int[][]
		{
			{22439, -48146, 249597, -9124, -16280},
			{22439, -48144, 248711, -9124, 16368},
			{22439, -48704, 249597, -9104, -16380},
			{22439, -49219, 249596, -9104, -16400},
			{22439, -49715, 249601, -9104, -16360},
			{22439, -49714, 248696, -9104, 15932},
			{22439, -49225, 248710, -9104, 16512},
			{22439, -48705, 248708, -9104, 16576},
		});
		SPAWNS.put(18496, new int[][]
		{
			{22441, -51176, 246055, -9984, 0},
			{22441, -51699, 246190, -9984, 0},
			{22442, -52060, 245956, -9984, 0},
			{22442, -51565, 246433, -9984, 0},
		});
		SPAWNS.put(18497, new int[][]
		{
			{22440, -49754, 243866, -9968, -16328},
			{22440, -49754, 242940, -9968, 16336},
			{22440, -48733, 243858, -9968, -16208},
			{22440, -48745, 242936, -9968, 16320},
			{22440, -49264, 242946, -9968, 16312},
			{22440, -49268, 243869, -9968, -16448},
			{22440, -48186, 242934, -9968, 16576},
			{22440, -48185, 243855, -9968, -16448},
		});
		SPAWNS.put(18498, new int[][]
		{
			{22411, -46355, 246375, -9984, 0},
			{22411, -46167, 246160, -9984, 0},
			{22393, -45952, 245748, -9984, 0},
			{22394, -46428, 246254, -9984, 0},
			{22393, -46490, 245871, -9984, 0},
			{22394, -45877, 246309, -9984, 0},
		});
		SPAWNS.put(18499, new int[][]
		{
			{22395, -48730, 248067, -9984, 0},
			{22395, -49112, 248250, -9984, 0},
		});
		SPAWNS.put(18500, new int[][]
		{
			{22393, -51954, 246475, -10848, 0},
			{22394, -51421, 246512, -10848, 0},
			{22394, -51404, 245951, -10848, 0},
			{22393, -51913, 246206, -10848, 0},
			{22394, -51663, 245979, -10848, 0},
			{22394, -51969, 245809, -10848, 0},
			{22412, -51259, 246357, -10848, 0},
		});
		SPAWNS.put(18501, new int[][]
		{
			{22395, -48856, 243949, -10848, 0},
			{22395, -49144, 244190, -10848, 0},
		});
		SPAWNS.put(18502, new int[][]
		{
			{22441, -46471, 246135, -11704, 0},
			{22441, -46449, 245997, -11704, 0},
			{22441, -46235, 246187, -11704, 0},
			{22441, -46513, 246326, -11704, 0},
			{22441, -45889, 246313, -11704, 0},
		});
		SPAWNS.put(18503, new int[][]
		{
			{22395, -49067, 248050, -11712, 0},
			{22395, -48957, 248223, -11712, 0},
		});
		SPAWNS.put(18504, new int[][]
		{
			{22413, -51748, 246138, -12568, 0},
			{22413, -51279, 246200, -12568, 0},
			{22413, -51787, 246594, -12568, 0},
			{22413, -51892, 246544, -12568, 0},
			{22413, -51500, 245781, -12568, 0},
			{22413, -51941, 246045, -12568, 0},
		});
		SPAWNS.put(18505, new int[][]
		{
			{18490, -48238, 243347, -13376, 0},
			{18490, -48462, 244022, -13376, 0},
			{18490, -48050, 244045, -13376, 0},
			{18490, -48229, 243823, -13376, 0},
			{18490, -47871, 243208, -13376, 0},
			{18490, -48255, 243528, -13376, 0},
			{18490, -48461, 243780, -13376, 0},
			{18490, -47983, 243197, -13376, 0},
			{18490, -47841, 243819, -13376, 0},
			{18490, -48646, 243764, -13376, 0},
			{18490, -47806, 243850, -13376, 0},
			{18490, -48456, 243447, -13376, 0},
		});
		//@formatter:on
	}
	
	public TowerOfNaia()
	{
		addFirstTalkId(CONTROLLER);
		addStartNpc(CONTROLLER, DWARVEN_GHOST);
		addTalkId(CONTROLLER, DWARVEN_GHOST);
		addAttackId(LOCK);
		addKillId(LOCK, MUTATED_ELPY, SPORE_BASIC);
		addSpawnId(MUTATED_ELPY, SPORE_BASIC);
		
		for (int npcId = SPORE_FIRE; npcId <= SPORE_EARTH; npcId++)
		{
			addKillId(npcId);
			addSpawnId(npcId);
		}
		
		for (int npcId = ROOM_MANAGER_FIRST; npcId <= ROOM_MANAGER_LAST; npcId++)
		{
			addFirstTalkId(npcId);
			addTalkId(npcId);
			addStartNpc(npcId);
			initRoom(npcId);
		}
		
		for (int npcId : TOWER_MONSTERS)
		{
			addKillId(npcId);
		}
		
		_lock = (L2MonsterInstance) addSpawn(LOCK, 16409, 244438, 11620, -1048, false, 0, false);
		_controller = addSpawn(CONTROLLER, 16608, 244420, 11620, 31264, false, 0, false);
		_counter = 90;
		initSporeChallenge();
		spawnElpy();
	}
	
	@Override
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final int npcId = npc.getId();
		
		if (npcId == CONTROLLER)
		{
			if (_lock == null)
			{
				return "18492-02.htm";
			}
			return "18492-01.htm";
		}
		
		else if ((npcId >= ROOM_MANAGER_FIRST) && (npcId <= ROOM_MANAGER_LAST))
		{
			if (_activeRooms.containsKey(npcId) && !_activeRooms.get(npcId))
			{
				if (player.getParty() == null)
				{
					player.sendPacket(SystemMessageId.YOU_MUST_BE_IN_A_PARTY_IN_ORDER_TO_OPERATE_THE_MACHINE);
					return null;
				}
				return "manager.htm";
			}
		}
		return super.onFirstTalk(npc, player);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = event;
		
		// Timer. Spawns Naia Lock
		if (event.equalsIgnoreCase("spawn_lock"))
		{
			htmltext = null;
			_lock = (L2MonsterInstance) addSpawn(LOCK, 16409, 244438, 11620, -1048, false, 0, false);
			_counter = 90;
		}
		
		// Timer. Depending of _challengeState despans all spawned spores, or spores, reached assembly point
		else if (event.equalsIgnoreCase("despawn_total"))
		{
			// Spores is not attacked too long - despawn them all, reinit values
			if (_challengeState == STATE_SPORE_IDLE_TOO_LONG)
			{
				removeSpores();
				initSporeChallenge();
			}
			// Spores are moving to assembly point. Despawn all reached, check for reached spores count.
			else if ((_challengeState == STATE_SPORE_CHALLENGE_SUCCESSFULL) && (_winIndex >= 0))
			{
				// Requirements are met, despawn all spores, spawn Epidos
				if ((_despawnedSporesCount.get() >= 10) || _sporeSpawn.isEmpty())
				{
					removeSpores();
					_despawnedSporesCount.set(0);
					final int[] coords = SPORES_MERGE_POSITION[_winIndex];
					addSpawn(EPIDOSES[_winIndex], coords[0], coords[1], coords[2], 0, false, 0, false);
					initSporeChallenge();
				}
				// Requirements aren't met, despawn reached spores
				else
				{
					final Iterator<L2Npc> it = _sporeSpawn.iterator();
					while (it.hasNext())
					{
						final L2Npc spore = it.next();
						if ((spore != null) && !spore.isDead() && (spore.getX() == spore.getSpawn().getX()) && (spore.getY() == spore.getSpawn().getY()))
						{
							spore.deleteMe();
							it.remove();
							_despawnedSporesCount.incrementAndGet();
						}
					}
					startQuestTimer("despawn_total", 3000, null, null);
				}
			}
		}
		
		if (npc == null)
		{
			return null;
		}
		
		final int npcId = npc.getId();
		
		if (event.equalsIgnoreCase("despawn_spore") && !npc.isDead() && (_challengeState == STATE_SPORE_CHALLENGE_IN_PROGRESS))
		{
			htmltext = null;
			
			_sporeSpawn.remove(npc);
			npc.deleteMe();
			
			if (npcId == SPORE_BASIC)
			{
				spawnRandomSpore();
				spawnRandomSpore();
			}
			
			else if ((npcId >= SPORE_FIRE) && (npcId <= SPORE_EARTH))
			{
				_despawnedSporesCount.incrementAndGet();
				
				if (_despawnedSporesCount.get() < SELF_DESPAWN_LIMIT)
				{
					spawnOppositeSpore(npcId);
				}
				else
				{
					_challengeState = STATE_SPORE_IDLE_TOO_LONG;
					startQuestTimer("despawn_total", 60000, null, null);
				}
			}
		}
		else if (event.equalsIgnoreCase("18492-05.htm"))
		{
			if ((_lock == null) || (_lock.getCurrentHp() > (_lock.getMaxHp() / 10)))
			{
				htmltext = null;
				if (_lock != null)
				{
					_lock.deleteMe();
					_lock = null;
				}
				cancelQuestTimers("spawn_lock");
				startQuestTimer("spawn_lock", 300000, null, null);
				npc.setTarget(player);
				npc.doCast(SkillData.getInstance().getSkill(5527, 1));
			}
		}
		else if (event.equalsIgnoreCase("teleport") && (_lock != null))
		{
			htmltext = null;
			final L2Party party = player.getParty();
			if (party != null)
			{
				if (Util.checkIfInRange(3000, party.getLeader(), npc, true))
				{
					for (L2PcInstance partyMember : party.getMembers())
					{
						if (Util.checkIfInRange(2000, partyMember, npc, true))
						{
							partyMember.teleToLocation(-47271, 246098, -9120, true);
						}
					}
					_lock.deleteMe();
					_lock = null;
					cancelQuestTimers("spawn_lock");
					startQuestTimer("spawn_lock", 1200000, null, null);
				}
				else
				{
					npc.setTarget(player);
					npc.doCast(SkillData.getInstance().getSkill(5527, 1));
				}
			}
			else
			{
				player.teleToLocation(-47271, 246098, -9120);
				_lock.deleteMe();
				_lock = null;
				cancelQuestTimers("spawn_lock");
				startQuestTimer("spawn_lock", 1200000, null, null);
			}
		}
		else if (event.equalsIgnoreCase("go") && _activeRooms.containsKey(npcId) && !_activeRooms.get(npcId))
		{
			htmltext = null;
			final L2Party party = player.getParty();
			
			if (party != null)
			{
				removeForeigners(npcId, party);
				startRoom(npcId);
				ThreadPool.schedule(new StopRoomTask(npcId), 300000);
			}
			else
			{
				player.sendPacket(SystemMessageId.YOU_MUST_BE_IN_A_PARTY_IN_ORDER_TO_OPERATE_THE_MACHINE);
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon, Skill skill)
	{
		if ((_lock != null) && (npc.getObjectId() == _lock.getObjectId()))
		{
			final int remaindedHpPercent = (int) ((npc.getCurrentHp() * 100) / npc.getMaxHp());
			
			if ((remaindedHpPercent <= _counter) && (_controller != null))
			{
				if (_counter == 50)
				{
					MinionList.spawnMinion(_lock, 18493);
				}
				
				else if (_counter == 10)
				{
					MinionList.spawnMinion(_lock, 18493);
					MinionList.spawnMinion(_lock, 18493);
				}
				_controller.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.EMERGENCY_EMERGENCY_THE_OUTER_WALL_IS_WEAKENING_RAPIDLY);
				_counter -= 10;
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon, skill);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final int npcId = npc.getId();
		
		if (npcId == LOCK)
		{
			_lock = null;
			cancelQuestTimers("spawn_lock");
			startQuestTimer("spawn_lock", 300000, null, null);
		}
		
		else if (Arrays.binarySearch(TOWER_MONSTERS, npcId) >= 0)
		{
			int managerId = 0;
			
			for (L2ZoneType zone : ZoneManager.getInstance().getZones(npc.getX(), npc.getY(), npc.getZ()))
			{
				if (ZONES.containsValue(zone.getId()))
				{
					for (int i : ZONES.keySet())
					{
						if (ZONES.get(i) == zone.getId())
						{
							managerId = i;
							break;
						}
					}
				}
			}
			
			if ((managerId > 0) && _spawns.containsKey(managerId))
			{
				final List<L2Npc> spawned = _spawns.get(managerId);
				spawned.remove(npc);
				if (spawned.isEmpty() && DOORS.containsKey(managerId))
				{
					final int[] doorList = DOORS.get(managerId);
					DoorData.getInstance().getDoor(doorList[1]).openMe();
					_spawns.remove(managerId);
				}
			}
		}
		else if (npcId == MUTATED_ELPY)
		{
			_challengeState = STATE_SPORE_CHALLENGE_IN_PROGRESS;
			markElpyRespawn();
			DoorData.getInstance().getDoor(18250025).closeMe();
			ZoneManager.getInstance().getZoneById(200100).setEnabled(true);
			
			for (int i = 0; i < 10; i++)
			{
				addSpawn(SPORE_BASIC, -45474, 247450, -13994, 49152, false, 0, false);
			}
		}
		else if ((npcId == SPORE_BASIC) && (_challengeState == STATE_SPORE_CHALLENGE_IN_PROGRESS))
		{
			_sporeSpawn.remove(npc);
			spawnRandomSpore();
			spawnRandomSpore();
		}
		else if ((npcId >= SPORE_FIRE) && (npcId <= SPORE_EARTH) && ((_challengeState == STATE_SPORE_CHALLENGE_IN_PROGRESS) || (_challengeState == STATE_SPORE_CHALLENGE_SUCCESSFULL)))
		{
			_sporeSpawn.remove(npc);
			
			if (_challengeState == STATE_SPORE_CHALLENGE_IN_PROGRESS)
			{
				_despawnedSporesCount.decrementAndGet();
				final int sporeGroup = getSporeGroup(npcId);
				
				if (sporeGroup >= 0)
				{
					if ((npcId == SPORE_FIRE) || (npcId == SPORE_WIND))
					{
						_indexCount[sporeGroup] += 2;
					}
					else
					{
						_indexCount[sporeGroup] -= 2;
					}
					
					if (_indexCount[Math.abs(sporeGroup - 1)] > 0)
					{
						_indexCount[Math.abs(sporeGroup - 1)]--;
					}
					else if (_indexCount[Math.abs(sporeGroup - 1)] < 0)
					{
						_indexCount[Math.abs(sporeGroup - 1)]++;
					}
					
					if ((Math.abs(_indexCount[sporeGroup]) < ELEMENT_INDEX_LIMIT) && (Math.abs(_indexCount[sporeGroup]) > 0) && ((_indexCount[sporeGroup] % 20) == 0) && (getRandom(100) < 50))
					{
						final String el = ELEMENTS_NAME[Arrays.binarySearch(ELEMENTS, npcId)];
						for (L2Npc spore : _sporeSpawn)
						{
							if ((spore != null) && !spore.isDead() && (spore.getId() == npcId))
							{
								spore.broadcastSay(ChatType.NPC_GENERAL, SPORES_NPCSTRING_ID[getRandom(4)], el);
							}
						}
					}
					if (Math.abs(_indexCount[sporeGroup]) < ELEMENT_INDEX_LIMIT)
					{
						if ((((_indexCount[sporeGroup] > 0) && ((npcId == SPORE_FIRE) || (npcId == SPORE_WIND))) || ((_indexCount[sporeGroup] <= 0) && ((npcId == SPORE_WATER) || (npcId == SPORE_EARTH)))) && (getRandom(1000) > 200))
						{
							spawnOppositeSpore(npcId);
						}
						else
						{
							spawnRandomSpore();
						}
					}
					else
					// index value was reached
					{
						_challengeState = STATE_SPORE_CHALLENGE_SUCCESSFULL;
						_despawnedSporesCount.set(0);
						_winIndex = Arrays.binarySearch(ELEMENTS, npcId);
						final int[] coord = SPORES_MERGE_POSITION[_winIndex];
						
						for (L2Npc spore : _sporeSpawn)
						{
							if ((spore != null) && !spore.isDead())
							{
								moveTo(spore, coord);
							}
						}
						
						startQuestTimer("despawn_total", 3000, null, null);
					}
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public final String onSpawn(L2Npc npc)
	{
		final int npcId = npc.getId();
		
		if (npcId == MUTATED_ELPY)
		{
			DoorData.getInstance().getDoor(18250025).openMe();
			ZoneManager.getInstance().getZoneById(200100).setEnabled(false);
			ZoneManager.getInstance().getZoneById(200101).setEnabled(true);
			ZoneManager.getInstance().getZoneById(200101).setEnabled(false);
		}
		else if (((npcId == SPORE_BASIC) || ((npcId >= SPORE_FIRE) && (npcId <= SPORE_EARTH))) && (_challengeState == STATE_SPORE_CHALLENGE_IN_PROGRESS))
		{
			_sporeSpawn.add(npc);
			npc.setWalking();
			final int[] coord = SPORES_MOVE_POINTS[getRandom(SPORES_MOVE_POINTS.length)];
			npc.getSpawn().setXYZ(coord[0], coord[1], coord[2]);
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(coord[0], coord[1], coord[2], 0));
			startQuestTimer("despawn_spore", 60000, npc, null);
		}
		return super.onSpawn(npc);
	}
	
	private int getSporeGroup(int sporeId)
	{
		int ret;
		switch (sporeId)
		{
			case SPORE_FIRE:
			case SPORE_WATER:
			{
				ret = 0;
				break;
			}
			case SPORE_WIND:
			case SPORE_EARTH:
			{
				ret = 1;
				break;
			}
			default:
			{
				ret = -1;
			}
		}
		return ret;
	}
	
	protected void initRoom(int managerId)
	{
		removeAllPlayers(managerId);
		_activeRooms.put(managerId, false);
		
		if (DOORS.containsKey(managerId))
		{
			final int[] doorList = DOORS.get(managerId);
			DoorData.getInstance().getDoor(doorList[0]).openMe();
			DoorData.getInstance().getDoor(doorList[1]).closeMe();
		}
		
		if (_spawns.containsKey(managerId) && (_spawns.get(managerId) != null))
		{
			for (L2Npc npc : _spawns.get(managerId))
			{
				if ((npc != null) && !npc.isDead())
				{
					npc.deleteMe();
				}
			}
			
			_spawns.get(managerId).clear();
			_spawns.remove(managerId);
		}
	}
	
	private void initSporeChallenge()
	{
		_despawnedSporesCount.set(0);
		_challengeState = 0;
		_winIndex = -1;
		_indexCount[0] = 0;
		_indexCount[1] = 0;
		ZoneManager.getInstance().getZoneById(200100).setEnabled(false);
		ZoneManager.getInstance().getZoneById(200101).setEnabled(false);
		ZoneManager.getInstance().getZoneById(200101).setEnabled(true);
	}
	
	private void markElpyRespawn()
	{
		final long respawnTime = (getRandom(43200, 216000) * 1000) + System.currentTimeMillis();
		GlobalVariablesManager.getInstance().set("elpy_respawn_time", respawnTime);
	}
	
	private int moveTo(L2Npc npc, int[] coords)
	{
		int time = 0;
		if (npc != null)
		{
			final double distance = npc.calculateDistance3D(coords[0], coords[1], coords[2]);
			final int heading = Util.calculateHeadingFrom(npc.getX(), npc.getY(), coords[0], coords[1]);
			time = (int) ((distance / npc.getWalkSpeed()) * 1000);
			npc.setWalking();
			npc.disableCoreAI(true);
			npc.setRandomWalking(false);
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(coords[0], coords[1], coords[2], heading));
			npc.getSpawn().setXYZ(coords[0], coords[1], coords[2]);
		}
		return time == 0 ? 100 : time;
	}
	
	private void spawnElpy()
	{
		final long respawnTime = GlobalVariablesManager.getInstance().getLong("elpy_respawn_time", 0);
		if (respawnTime <= System.currentTimeMillis())
		{
			addSpawn(MUTATED_ELPY, -45474, 247450, -13994, 49152, false, 0, false);
		}
		else
		{
			ThreadPool.schedule(() -> addSpawn(MUTATED_ELPY, -45474, 247450, -13994, 49152, false, 0, false), respawnTime - System.currentTimeMillis());
		}
	}
	
	private L2Npc spawnRandomSpore()
	{
		return addSpawn(getRandom(SPORE_FIRE, SPORE_EARTH), -45474, 247450, -13994, 49152, false, 0, false);
	}
	
	private L2Npc spawnOppositeSpore(int srcSporeId)
	{
		final int idx = Arrays.binarySearch(ELEMENTS, srcSporeId);
		return idx >= 0 ? addSpawn(OPPOSITE_ELEMENTS[idx], -45474, 247450, -13994, 49152, false, 0, false) : null;
	}
	
	private void startRoom(int managerId)
	{
		_activeRooms.put(managerId, true);
		
		if (DOORS.containsKey(managerId))
		{
			final int[] doorList = DOORS.get(managerId);
			DoorData.getInstance().getDoor(doorList[0]).closeMe();
		}
		
		if (SPAWNS.containsKey(managerId))
		{
			final int[][] spawnList = SPAWNS.get(managerId);
			final List<L2Npc> spawned = new CopyOnWriteArrayList<>();
			for (int[] spawn : spawnList)
			{
				final L2Npc spawnedNpc = addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false);
				spawned.add(spawnedNpc);
			}
			if (!spawned.isEmpty())
			{
				_spawns.put(managerId, spawned);
			}
		}
	}
	
	private void removeForeigners(int managerId, L2Party party)
	{
		if ((party != null) && ZONES.containsKey(managerId) && (ZoneManager.getInstance().getZoneById(ZONES.get(managerId)) != null))
		{
			final L2ZoneType zone = ZoneManager.getInstance().getZoneById(ZONES.get(managerId));
			for (L2PcInstance player : zone.getPlayersInside())
			{
				if (player != null)
				{
					final L2Party charParty = player.getParty();
					if ((charParty == null) || (charParty.getLeaderObjectId() != party.getLeaderObjectId()))
					{
						player.teleToLocation(16110, 243841, 11616);
					}
				}
			}
		}
	}
	
	private void removeAllPlayers(int managerId)
	{
		if (ZONES.containsKey(managerId) && (ZoneManager.getInstance().getZoneById(ZONES.get(managerId)) != null))
		{
			final L2ZoneType zone = ZoneManager.getInstance().getZoneById(ZONES.get(managerId));
			for (L2PcInstance player : zone.getPlayersInside())
			{
				if (player != null)
				{
					player.teleToLocation(16110, 243841, 11616);
				}
			}
		}
	}
	
	private void removeSpores()
	{
		for (L2Npc spore : _sporeSpawn)
		{
			if ((spore != null) && !spore.isDead())
			{
				spore.deleteMe();
			}
		}
		_sporeSpawn.clear();
		cancelQuestTimers("despawn_spore");
	}
	
	private class StopRoomTask implements Runnable
	{
		private final int _managerId;
		
		public StopRoomTask(int managerId)
		{
			_managerId = managerId;
		}
		
		@Override
		public void run()
		{
			initRoom(_managerId);
		}
	}
}
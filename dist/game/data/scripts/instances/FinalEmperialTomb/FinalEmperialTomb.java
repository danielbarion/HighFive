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
package instances.FinalEmperialTomb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.commons.util.IGameXmlReader;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.L2CommandChannel;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.L2Territory;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.PcCondOverride;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.effects.L2EffectType;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.Earthquake;
import com.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;
import com.l2jmobius.gameserver.network.serverpackets.IClientOutgoingPacket;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillCanceled;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;
import com.l2jmobius.gameserver.network.serverpackets.SpecialCamera;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Util;

import instances.AbstractInstance;

/**
 * Final Emperial Tomb instance zone. TODO:<br>
 * Test when Frintezza song use 5008 effect skill.<br>
 * Test deeply Scarlet van Halisha's AI.<br>
 * Use proper zone spawn system.
 * @author Gigiikun
 */
public final class FinalEmperialTomb extends AbstractInstance implements IGameXmlReader
{
	Logger LOGGER = Logger.getLogger(FinalEmperialTomb.class.getName());
	
	protected static class FETSpawn
	{
		public boolean isZone = false;
		public boolean isNeededNextFlag = false;
		public int npcId;
		public int x = 0;
		public int y = 0;
		public int z = 0;
		public int h = 0;
		public int zone = 0;
		public int count = 0;
	}
	
	private static class FrintezzaSong
	{
		public SkillHolder skill;
		public SkillHolder effectSkill;
		public NpcStringId songName;
		public int chance;
		
		public FrintezzaSong(SkillHolder sk, SkillHolder esk, NpcStringId sn, int ch)
		{
			skill = sk;
			effectSkill = esk;
			songName = sn;
			chance = ch;
		}
	}
	
	// NPCs
	private static final int GUIDE = 32011;
	private static final int CUBE = 29061;
	private static final int SCARLET1 = 29046;
	private static final int SCARLET2 = 29047;
	private static final int FRINTEZZA = 29045;
	private static final int[] PORTRAITS =
	{
		29048,
		29049
	};
	static final int[] DEMONS =
	{
		29050,
		29051
	};
	private static final int HALL_ALARM = 18328;
	private static final int HALL_KEEPER_CAPTAIN = 18329;
	// Items
	private static final int HALL_KEEPER_SUICIDAL_SOLDIER = 18333;
	private static final int DARK_CHOIR_PLAYER = 18339;
	private static final int[] AI_DISABLED_MOBS =
	{
		18328
	};
	private static final int DEWDROP_OF_DESTRUCTION_ITEM_ID = 8556;
	private static final int FIRST_SCARLET_WEAPON = 8204;
	private static final int SECOND_SCARLET_WEAPON = 7903;
	// Skills
	private static final int DEWDROP_OF_DESTRUCTION_SKILL_ID = 2276;
	private static final int SOUL_BREAKING_ARROW_SKILL_ID = 2234;
	protected static final SkillHolder INTRO_SKILL = new SkillHolder(5004, 1);
	private static final SkillHolder FIRST_MORPH_SKILL = new SkillHolder(5017, 1);
	
	protected static final FrintezzaSong[] FRINTEZZASONGLIST =
	{
		new FrintezzaSong(new SkillHolder(5007, 1), new SkillHolder(5008, 1), NpcStringId.REQUIEM_OF_HATRED, 5),
		new FrintezzaSong(new SkillHolder(5007, 2), new SkillHolder(5008, 2), NpcStringId.RONDO_OF_SOLITUDE, 50),
		new FrintezzaSong(new SkillHolder(5007, 3), new SkillHolder(5008, 3), NpcStringId.FRENETIC_TOCCATA, 70),
		new FrintezzaSong(new SkillHolder(5007, 4), new SkillHolder(5008, 4), NpcStringId.FUGUE_OF_JUBILATION, 90),
		new FrintezzaSong(new SkillHolder(5007, 5), new SkillHolder(5008, 5), NpcStringId.HYPNOTIC_MAZURKA, 100),
	};
	// Locations
	private static final Location ENTER_TELEPORT = new Location(-88015, -141153, -9168);
	protected static final Location MOVE_TO_CENTER = new Location(-87904, -141296, -9168, 0);
	// Misc
	private static final int TEMPLATE_ID = 136; // this is the client number
	private static final int MIN_PLAYERS = 9;
	private static final int MAX_PLAYERS = 27;
	private static final int TIME_BETWEEN_DEMON_SPAWNS = 20000;
	private static final int MAX_DEMONS = 24;
	private static final boolean debug = false;
	private final Map<Integer, L2Territory> _spawnZoneList = new HashMap<>();
	private final Map<Integer, List<FETSpawn>> _spawnList = new HashMap<>();
	private int _spawnCount = 0;
	private final List<Integer> _mustKillMobsId = new ArrayList<>();
	protected static final int[] FIRST_ROOM_DOORS =
	{
		17130051,
		17130052,
		17130053,
		17130054,
		17130055,
		17130056,
		17130057,
		17130058
	};
	protected static final int[] SECOND_ROOM_DOORS =
	{
		17130061,
		17130062,
		17130063,
		17130064,
		17130065,
		17130066,
		17130067,
		17130068,
		17130069,
		17130070
	};
	protected static final int[] FIRST_ROUTE_DOORS =
	{
		17130042,
		17130043
	};
	protected static final int[] SECOND_ROUTE_DOORS =
	{
		17130045,
		17130046
	};
	// @formatter:off
	protected static final int[][] PORTRAIT_SPAWNS =
	{
		{29048, -89381, -153981, -9168, 3368, -89378, -153968, -9168, 3368},
		{29048, -86234, -152467, -9168, 37656, -86261, -152492, -9168, 37656},
		{29049, -89342, -152479, -9168, -5152, -89311, -152491, -9168, -5152},
		{29049, -86189, -153968, -9168, 29456, -86217, -153956, -9168, 29456},
	};
	// @formatter:on
	
	private FinalEmperialTomb()
	{
		load();
		addAttackId(SCARLET1, FRINTEZZA);
		addAttackId(PORTRAITS);
		addStartNpc(GUIDE, CUBE);
		addTalkId(GUIDE, CUBE);
		addKillId(HALL_ALARM, HALL_KEEPER_CAPTAIN, DARK_CHOIR_PLAYER, SCARLET2);
		addKillId(PORTRAITS);
		addKillId(DEMONS);
		addKillId(_mustKillMobsId);
		addSkillSeeId(PORTRAITS);
		addSpellFinishedId(HALL_KEEPER_SUICIDAL_SOLDIER);
	}
	
	@Override
	public void load()
	{
		_spawnCount = 0;
		_spawnList.clear();
		_spawnZoneList.clear();
		parseDatapackFile("data/scripts/instances/FinalEmperialTomb/final_emperial_tomb.xml");
		LOGGER.info("[Final Emperial Tomb] Loaded " + _spawnZoneList.size() + " spawn zones data.");
		LOGGER.info("[Final Emperial Tomb] Loaded " + _spawnCount + " spawns data.");
	}
	
	@Override
	public void parseDocument(Document doc, File f)
	{
		final Node first = doc.getFirstChild();
		if ((first != null) && "list".equalsIgnoreCase(first.getNodeName()))
		{
			for (Node n = first.getFirstChild(); n != null; n = n.getNextSibling())
			{
				if ("npc".equalsIgnoreCase(n.getNodeName()))
				{
					for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
					{
						if ("spawn".equalsIgnoreCase(d.getNodeName()))
						{
							NamedNodeMap attrs = d.getAttributes();
							Node att = attrs.getNamedItem("npcId");
							if (att == null)
							{
								LOGGER.severe("[Final Emperial Tomb] Missing npcId in npc List, skipping");
								continue;
							}
							final int npcId = Integer.parseInt(attrs.getNamedItem("npcId").getNodeValue());
							
							att = attrs.getNamedItem("flag");
							if (att == null)
							{
								LOGGER.severe("[Final Emperial Tomb] Missing flag in npc List npcId: " + npcId + ", skipping");
								continue;
							}
							
							final int flag = Integer.parseInt(attrs.getNamedItem("flag").getNodeValue());
							_spawnList.putIfAbsent(flag, new ArrayList<>());
							
							for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
							{
								if ("loc".equalsIgnoreCase(cd.getNodeName()))
								{
									attrs = cd.getAttributes();
									final FETSpawn spw = new FETSpawn();
									spw.npcId = npcId;
									
									att = attrs.getNamedItem("x");
									if (att != null)
									{
										spw.x = Integer.parseInt(att.getNodeValue());
									}
									else
									{
										continue;
									}
									att = attrs.getNamedItem("y");
									if (att != null)
									{
										spw.y = Integer.parseInt(att.getNodeValue());
									}
									else
									{
										continue;
									}
									att = attrs.getNamedItem("z");
									if (att != null)
									{
										spw.z = Integer.parseInt(att.getNodeValue());
									}
									else
									{
										continue;
									}
									att = attrs.getNamedItem("heading");
									if (att != null)
									{
										spw.h = Integer.parseInt(att.getNodeValue());
									}
									else
									{
										continue;
									}
									att = attrs.getNamedItem("mustKill");
									if (att != null)
									{
										spw.isNeededNextFlag = Boolean.parseBoolean(att.getNodeValue());
									}
									if (spw.isNeededNextFlag)
									{
										_mustKillMobsId.add(npcId);
									}
									_spawnList.get(flag).add(spw);
									_spawnCount++;
								}
								else if ("zone".equalsIgnoreCase(cd.getNodeName()))
								{
									attrs = cd.getAttributes();
									final FETSpawn spw = new FETSpawn();
									spw.npcId = npcId;
									spw.isZone = true;
									
									att = attrs.getNamedItem("id");
									if (att != null)
									{
										spw.zone = Integer.parseInt(att.getNodeValue());
									}
									else
									{
										continue;
									}
									att = attrs.getNamedItem("count");
									if (att != null)
									{
										spw.count = Integer.parseInt(att.getNodeValue());
									}
									else
									{
										continue;
									}
									att = attrs.getNamedItem("mustKill");
									if (att != null)
									{
										spw.isNeededNextFlag = Boolean.parseBoolean(att.getNodeValue());
									}
									if (spw.isNeededNextFlag)
									{
										_mustKillMobsId.add(npcId);
									}
									_spawnList.get(flag).add(spw);
									_spawnCount++;
								}
							}
						}
					}
				}
				else if ("spawnZones".equalsIgnoreCase(n.getNodeName()))
				{
					for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
					{
						if ("zone".equalsIgnoreCase(d.getNodeName()))
						{
							NamedNodeMap attrs = d.getAttributes();
							Node att = attrs.getNamedItem("id");
							if (att == null)
							{
								LOGGER.severe("[Final Emperial Tomb] Missing id in spawnZones List, skipping");
								continue;
							}
							final int id = Integer.parseInt(att.getNodeValue());
							att = attrs.getNamedItem("minZ");
							if (att == null)
							{
								LOGGER.severe("[Final Emperial Tomb] Missing minZ in spawnZones List id: " + id + ", skipping");
								continue;
							}
							final int minz = Integer.parseInt(att.getNodeValue());
							att = attrs.getNamedItem("maxZ");
							if (att == null)
							{
								LOGGER.severe("[Final Emperial Tomb] Missing maxZ in spawnZones List id: " + id + ", skipping");
								continue;
							}
							final int maxz = Integer.parseInt(att.getNodeValue());
							final L2Territory ter = new L2Territory(id);
							
							for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
							{
								if ("point".equalsIgnoreCase(cd.getNodeName()))
								{
									attrs = cd.getAttributes();
									int x;
									int y;
									att = attrs.getNamedItem("x");
									if (att != null)
									{
										x = Integer.parseInt(att.getNodeValue());
									}
									else
									{
										continue;
									}
									att = attrs.getNamedItem("y");
									if (att != null)
									{
										y = Integer.parseInt(att.getNodeValue());
									}
									else
									{
										continue;
									}
									
									ter.add(x, y, minz, maxz, 0);
								}
							}
							
							_spawnZoneList.put(id, ter);
						}
					}
				}
			}
		}
	}
	
	@Override
	protected boolean checkConditions(L2PcInstance player)
	{
		if (debug || player.canOverrideCond(PcCondOverride.INSTANCE_CONDITIONS))
		{
			return true;
		}
		
		final L2Party party = player.getParty();
		if (party == null)
		{
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_CURRENTLY_IN_A_PARTY_SO_YOU_CANNOT_ENTER);
			return false;
		}
		
		final L2CommandChannel channel = player.getParty().getCommandChannel();
		if (channel == null)
		{
			player.sendPacket(SystemMessageId.YOU_CANNOT_ENTER_BECAUSE_YOU_ARE_NOT_ASSOCIATED_WITH_THE_CURRENT_COMMAND_CHANNEL);
			return false;
		}
		else if (channel.getLeader() != player)
		{
			player.sendPacket(SystemMessageId.ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER);
			return false;
		}
		else if (player.getInventory().getItemByItemId(8073) == null)
		{
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_ITEM_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED);
			sm.addPcName(player);
			player.sendPacket(sm);
			return false;
		}
		else if ((channel.getMemberCount() < MIN_PLAYERS) || (channel.getMemberCount() > MAX_PLAYERS))
		{
			player.sendPacket(SystemMessageId.YOU_CANNOT_ENTER_DUE_TO_THE_PARTY_HAVING_EXCEEDED_THE_LIMIT);
			return false;
		}
		for (L2PcInstance channelMember : channel.getMembers())
		{
			if (channelMember.getLevel() < 80)
			{
				party.broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.C1_S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY).addPcName(channelMember));
				return false;
			}
			if (!Util.checkIfInRange(1000, player, channelMember, true))
			{
				party.broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.C1_IS_IN_A_LOCATION_WHICH_CANNOT_BE_ENTERED_THEREFORE_IT_CANNOT_BE_PROCESSED).addPcName(channelMember));
				return false;
			}
			final Long reentertime = InstanceManager.getInstance().getInstanceTime(channelMember.getObjectId(), TEMPLATE_ID);
			if (System.currentTimeMillis() < reentertime)
			{
				party.broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.C1_MAY_NOT_RE_ENTER_YET).addPcName(channelMember));
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
			controlStatus(world);
			
			if ((player.getParty() == null) || (player.getParty().getCommandChannel() == null))
			{
				if (player.getInventory().getInventoryItemCount(DEWDROP_OF_DESTRUCTION_ITEM_ID, -1) > 0)
				{
					player.destroyItemByItemId(getName(), DEWDROP_OF_DESTRUCTION_ITEM_ID, player.getInventory().getInventoryItemCount(DEWDROP_OF_DESTRUCTION_ITEM_ID, -1), null, true);
				}
				world.addAllowed(player);
				teleportPlayer(player, ENTER_TELEPORT, world.getInstanceId(), false);
			}
			else
			{
				for (L2PcInstance channelMember : player.getParty().getCommandChannel().getMembers())
				{
					if (player.getInventory().getInventoryItemCount(DEWDROP_OF_DESTRUCTION_ITEM_ID, -1) > 0)
					{
						channelMember.destroyItemByItemId(getName(), DEWDROP_OF_DESTRUCTION_ITEM_ID, channelMember.getInventory().getInventoryItemCount(DEWDROP_OF_DESTRUCTION_ITEM_ID, -1), null, true);
					}
					world.addAllowed(channelMember);
					teleportPlayer(channelMember, ENTER_TELEPORT, world.getInstanceId(), false);
				}
			}
		}
		else
		{
			teleportPlayer(player, ENTER_TELEPORT, world.getInstanceId(), false);
		}
	}
	
	protected synchronized boolean checkKillProgress(L2Npc mob, InstanceWorld world)
	{
		final List<L2Npc> npcList = world.getParameters().getList("npcList", L2Npc.class, new ArrayList<>());
		if (npcList.contains(mob))
		{
			npcList.remove(mob);
		}
		return npcList.isEmpty();
	}
	
	private void spawnFlaggedNPCs(InstanceWorld world, int flag)
	{
		for (FETSpawn spw : _spawnList.get(flag))
		{
			if (spw.isZone)
			{
				for (int i = 0; i < spw.count; i++)
				{
					if (_spawnZoneList.containsKey(spw.zone))
					{
						final Location location = _spawnZoneList.get(spw.zone).getRandomPoint();
						if (location != null)
						{
							spawn(world, spw.npcId, location.getX(), location.getY(), GeoEngine.getInstance().getHeight(location.getX(), location.getY(), location.getZ()), getRandom(65535), spw.isNeededNextFlag);
						}
					}
					else
					{
						LOGGER.info("[Final Emperial Tomb] Missing zone: " + spw.zone);
					}
				}
			}
			else
			{
				spawn(world, spw.npcId, spw.x, spw.y, spw.z, spw.h, spw.isNeededNextFlag);
			}
		}
	}
	
	protected synchronized void controlStatus(InstanceWorld world)
	{
		if (debug)
		{
			LOGGER.info("[Final Emperial Tomb] Starting " + world.getStatus() + ". status.");
		}
		world.setParameter("npcList", new ArrayList<>());
		switch (world.getStatus())
		{
			case 0:
			{
				spawnFlaggedNPCs(world, 0);
				break;
			}
			case 1:
			{
				for (int doorId : FIRST_ROUTE_DOORS)
				{
					world.openDoor(doorId);
				}
				spawnFlaggedNPCs(world, world.getStatus());
				break;
			}
			case 2:
			{
				for (int doorId : SECOND_ROUTE_DOORS)
				{
					world.openDoor(doorId);
				}
				ThreadPool.schedule(new IntroTask(world, 0), 600000);
				break;
			}
			case 3: // first morph
			{
				ScheduledFuture<?> songEffectTask = world.getParameters().getObject("songEffectTask", ScheduledFuture.class);
				if (songEffectTask != null)
				{
					songEffectTask.cancel(false);
				}
				songEffectTask = null;
				final L2Npc activeScarlet = world.getParameters().getObject("activeScarlet", L2Npc.class);
				activeScarlet.setIsInvul(true);
				if (activeScarlet.isCastingNow())
				{
					activeScarlet.abortCast();
				}
				handleReenterTime(world);
				activeScarlet.doCast(FIRST_MORPH_SKILL.getSkill());
				ThreadPool.schedule(new SongTask(world, 2), 1500);
				break;
			}
			case 4: // second morph
			{
				world.setParameter("isVideo", true);
				broadCastPacket(world, new MagicSkillCanceled(world.getParameters().getObject("frintezza", L2Npc.class).getObjectId()));
				ScheduledFuture<?> songEffectTask = world.getParameters().getObject("songEffectTask", ScheduledFuture.class);
				if (songEffectTask != null)
				{
					songEffectTask.cancel(false);
				}
				songEffectTask = null;
				ThreadPool.schedule(new IntroTask(world, 23), 2000);
				ThreadPool.schedule(new IntroTask(world, 24), 2100);
				break;
			}
			case 5: // raid success
			{
				world.setParameter("isVideo", true);
				broadCastPacket(world, new MagicSkillCanceled(world.getParameters().getObject("frintezza", L2Npc.class).getObjectId()));
				ScheduledFuture<?> songTask = world.getParameters().getObject("songTask", ScheduledFuture.class);
				if (songTask != null)
				{
					songTask.cancel(true);
				}
				ScheduledFuture<?> songEffectTask = world.getParameters().getObject("songEffectTask", ScheduledFuture.class);
				if (songEffectTask != null)
				{
					songEffectTask.cancel(false);
				}
				songTask = null;
				songEffectTask = null;
				ThreadPool.schedule(new IntroTask(world, 33), 500);
				break;
			}
			case 6: // open doors
			{
				InstanceManager.getInstance().getInstance(world.getInstanceId()).setDuration(300000);
				for (int doorId : FIRST_ROOM_DOORS)
				{
					world.openDoor(doorId);
				}
				for (int doorId : FIRST_ROUTE_DOORS)
				{
					world.openDoor(doorId);
				}
				for (int doorId : SECOND_ROUTE_DOORS)
				{
					world.openDoor(doorId);
				}
				for (int doorId : SECOND_ROOM_DOORS)
				{
					world.closeDoor(doorId);
				}
				break;
			}
		}
		world.incStatus();
	}
	
	protected synchronized void spawn(InstanceWorld world, int npcId, int x, int y, int z, int h, boolean addToKillTable)
	{
		final L2Npc npc = addSpawn(npcId, x, y, z, h, false, 0, false, world.getInstanceId());
		if (addToKillTable)
		{
			final List<L2Npc> npcList = world.getParameters().getList("npcList", L2Npc.class, new ArrayList<>());
			npcList.add(npc);
			world.setParameter("npcList", npcList);
		}
		npc.setRandomWalking(false);
		if (npc.isInstanceTypes(InstanceType.L2Attackable))
		{
			((L2Attackable) npc).setSeeThroughSilentMove(true);
		}
		if (CommonUtil.contains(AI_DISABLED_MOBS, npcId))
		{
			npc.disableCoreAI(true);
		}
		if (npcId == DARK_CHOIR_PLAYER)
		{
			world.setParameter("darkChoirPlayerCount", world.getParameters().getInt("darkChoirPlayerCount", 0) + 1);
		}
	}
	
	private class DemonSpawnTask implements Runnable
	{
		private final InstanceWorld _world;
		
		DemonSpawnTask(InstanceWorld world)
		{
			_world = world;
		}
		
		@Override
		public void run()
		{
			Map<L2Npc, Integer> portraits = _world.getParameters().getMap("portraits", L2Npc.class, Integer.class);
			if ((InstanceManager.getInstance().getWorld(_world.getInstanceId()) != _world) || (portraits == null) || portraits.isEmpty())
			{
				if (debug)
				{
					LOGGER.info("[Final Emperial Tomb] Instance is deleted or all Portraits is killed.");
				}
				return;
			}
			final List<L2Npc> demons = _world.getParameters().getList("demons", L2Npc.class, new ArrayList<>());
			for (int i : portraits.values())
			{
				if (_world.getAliveNpcs(DEMONS).size() > MAX_DEMONS)
				{
					break;
				}
				final L2MonsterInstance demon = (L2MonsterInstance) addSpawn(PORTRAIT_SPAWNS[i][0] + 2, PORTRAIT_SPAWNS[i][5], PORTRAIT_SPAWNS[i][6], PORTRAIT_SPAWNS[i][7], PORTRAIT_SPAWNS[i][8], false, 0, false, _world.getInstanceId());
				demons.add(demon);
			}
			_world.setParameter("demons", demons);
			ThreadPool.schedule(new DemonSpawnTask(_world), TIME_BETWEEN_DEMON_SPAWNS);
		}
	}
	
	private class SoulBreakingArrow implements Runnable
	{
		private final L2Npc _npc;
		
		protected SoulBreakingArrow(L2Npc npc)
		{
			_npc = npc;
		}
		
		@Override
		public void run()
		{
			_npc.setScriptValue(0);
		}
	}
	
	private class SongTask implements Runnable
	{
		private final InstanceWorld _world;
		private final int _status;
		
		SongTask(InstanceWorld world, int status)
		{
			_world = world;
			_status = status;
		}
		
		@Override
		public void run()
		{
			if (InstanceManager.getInstance().getWorld(_world.getInstanceId()) != _world)
			{
				return;
			}
			switch (_status)
			{
				case 0: // new song play
				{
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					if (_world.getParameters().getBoolean("isVideo", false))
					{
						_world.setParameter("songTask", ThreadPool.schedule(new SongTask(_world, 0), 1000));
					}
					else if ((frintezza != null) && !frintezza.isDead())
					{
						if (frintezza.getScriptValue() != 1)
						{
							final int rnd = getRandom(100);
							for (FrintezzaSong element : FRINTEZZASONGLIST)
							{
								if (rnd < element.chance)
								{
									_world.setParameter("OnSong", element);
									broadCastPacket(_world, new ExShowScreenMessage(2, -1, 2, 0, 0, 0, 0, true, 4000, false, null, element.songName, null));
									broadCastPacket(_world, new MagicSkillUse(frintezza, frintezza, element.skill.getSkillId(), element.skill.getSkillLevel(), element.skill.getSkill().getHitTime(), 0));
									_world.setParameter("songEffectTask", ThreadPool.schedule(new SongTask(_world, 1), element.skill.getSkill().getHitTime() - 10000));
									_world.setParameter("songTask", ThreadPool.schedule(new SongTask(_world, 0), element.skill.getSkill().getHitTime()));
									break;
								}
							}
						}
						else
						{
							ThreadPool.schedule(new SoulBreakingArrow(frintezza), 35000);
						}
					}
					break;
				}
				case 1: // Frintezza song effect
				{
					ScheduledFuture<?> songEffectTask = _world.getParameters().getObject("songEffectTask", ScheduledFuture.class);
					if (songEffectTask != null)
					{
						songEffectTask.cancel(false);
					}
					songEffectTask = null;
					final Skill skill = _world.getParameters().getObject("OnSong", FrintezzaSong.class).effectSkill.getSkill();
					if (skill == null)
					{
						return;
					}
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					if ((frintezza != null) && !frintezza.isDead() && (activeScarlet != null) && !activeScarlet.isDead())
					{
						final List<L2Character> targetList = new ArrayList<>();
						if (skill.hasEffectType(L2EffectType.STUN) || skill.isDebuff())
						{
							for (L2PcInstance player : _world.getAllowed())
							{
								if ((player != null) && player.isOnline() && (player.getInstanceId() == _world.getInstanceId()))
								{
									if (!player.isDead())
									{
										targetList.add(player);
									}
									if (player.hasSummon() && !player.getSummon().isDead())
									{
										targetList.add(player.getSummon());
									}
								}
							}
						}
						else
						{
							targetList.add(activeScarlet);
						}
						if (!targetList.isEmpty())
						{
							frintezza.doCast(skill, targetList.get(0), targetList.toArray(new L2Character[targetList.size()]));
						}
					}
					break;
				}
				case 2: // finish morph
				{
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					activeScarlet.setRHandId(SECOND_SCARLET_WEAPON);
					activeScarlet.setIsInvul(false);
					break;
				}
			}
		}
	}
	
	private class IntroTask implements Runnable
	{
		private final InstanceWorld _world;
		private final int _status;
		private volatile boolean running = false;
		
		IntroTask(InstanceWorld world, int status)
		{
			_world = world;
			_status = status;
		}
		
		@Override
		public void run()
		{
			if (running)
			{
				return;
			}
			running = true;
			
			switch (_status)
			{
				case 0:
				{
					ThreadPool.schedule(new IntroTask(_world, 1), 27000);
					ThreadPool.schedule(new IntroTask(_world, 2), 30000);
					broadCastPacket(_world, new Earthquake(-87784, -155083, -9087, 45, 27));
					break;
				}
				case 1:
				{
					for (int doorId : FIRST_ROOM_DOORS)
					{
						_world.closeDoor(doorId);
					}
					for (int doorId : FIRST_ROUTE_DOORS)
					{
						_world.closeDoor(doorId);
					}
					for (int doorId : SECOND_ROOM_DOORS)
					{
						_world.closeDoor(doorId);
					}
					for (int doorId : SECOND_ROUTE_DOORS)
					{
						_world.closeDoor(doorId);
					}
					addSpawn(29061, -87904, -141296, -9168, 0, false, 0, false, _world.getInstanceId());
					break;
				}
				case 2:
				{
					final L2Npc frintezzaDummy = addSpawn(29052, -87784, -155083, -9087, 16048, false, 0, false, _world.getInstanceId());
					frintezzaDummy.setIsInvul(true);
					frintezzaDummy.setIsImmobilized(true);
					_world.setParameter("frintezzaDummy", frintezzaDummy);
					final L2Npc overheadDummy = addSpawn(29052, -87784, -153298, -9175, 16384, false, 0, false, _world.getInstanceId());
					overheadDummy.setIsInvul(true);
					overheadDummy.setIsImmobilized(true);
					overheadDummy.setCollisionHeight(600);
					_world.setParameter("overheadDummy", overheadDummy);
					final L2Npc portraitDummy1 = addSpawn(29052, -89566, -153168, -9165, 16048, false, 0, false, _world.getInstanceId());
					portraitDummy1.setIsImmobilized(true);
					portraitDummy1.setIsInvul(true);
					_world.setParameter("portraitDummy1", portraitDummy1);
					final L2Npc portraitDummy3 = addSpawn(29052, -86004, -153168, -9165, 16048, false, 0, false, _world.getInstanceId());
					portraitDummy3.setIsImmobilized(true);
					portraitDummy3.setIsInvul(true);
					_world.setParameter("portraitDummy3", portraitDummy3);
					final L2Npc scarletDummy = addSpawn(29053, -87784, -153298, -9175, 16384, false, 0, false, _world.getInstanceId());
					scarletDummy.setIsInvul(true);
					scarletDummy.setIsImmobilized(true);
					_world.setParameter("scarletDummy", scarletDummy);
					stopPc();
					ThreadPool.schedule(new IntroTask(_world, 3), 1000);
					break;
				}
				case 3:
				{
					final L2Npc overheadDummy = _world.getParameters().getObject("overheadDummy", L2Npc.class);
					broadCastPacket(_world, new SpecialCamera(overheadDummy, 0, 75, -89, 0, 100, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(overheadDummy, 0, 75, -89, 0, 100, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(overheadDummy, 300, 90, -10, 6500, 7000, 0, 0, 1, 0, 0));
					final L2Npc frintezza = addSpawn(FRINTEZZA, -87780, -155086, -9080, 16384, false, 0, false, _world.getInstanceId());
					frintezza.setIsImmobilized(true);
					frintezza.setIsInvul(true);
					frintezza.disableAllSkills();
					_world.setParameter("frintezza", frintezza);
					final List<L2Npc> demons = _world.getParameters().getList("demons", L2Npc.class, new ArrayList<>());
					for (int[] element : PORTRAIT_SPAWNS)
					{
						final L2Npc demon = addSpawn(element[0] + 2, element[5], element[6], element[7], element[8], false, 0, false, _world.getInstanceId());
						demon.setIsImmobilized(true);
						demon.disableAllSkills();
						demons.add(demon);
					}
					_world.setParameter("demons", demons);
					ThreadPool.schedule(new IntroTask(_world, 4), 6500);
					break;
				}
				case 4:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("frintezzaDummy", L2Npc.class), 1800, 90, 8, 6500, 7000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 5), 900);
					break;
				}
				case 5:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("frintezzaDummy", L2Npc.class), 140, 90, 10, 2500, 4500, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 6), 4000);
					break;
				}
				case 6:
				{
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					broadCastPacket(_world, new SpecialCamera(frintezza, 40, 75, -10, 0, 1000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(frintezza, 40, 75, -10, 0, 12000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 7), 1350);
					break;
				}
				case 7:
				{
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					broadCastPacket(_world, new SocialAction(frintezza.getObjectId(), 2));
					ThreadPool.schedule(new IntroTask(_world, 8), 7000);
					break;
				}
				case 8:
				{
					L2Npc frintezzaDummy = _world.getParameters().getObject("frintezzaDummy", L2Npc.class);
					frintezzaDummy.deleteMe();
					frintezzaDummy = null;
					ThreadPool.schedule(new IntroTask(_world, 9), 1000);
					break;
				}
				case 9:
				{
					final List<L2Npc> demons = _world.getParameters().getList("demons", L2Npc.class);
					broadCastPacket(_world, new SocialAction(demons.get(1).getObjectId(), 1));
					broadCastPacket(_world, new SocialAction(demons.get(2).getObjectId(), 1));
					ThreadPool.schedule(new IntroTask(_world, 10), 400);
					break;
				}
				case 10:
				{
					final List<L2Npc> demons = _world.getParameters().getList("demons", L2Npc.class);
					final L2Npc portraitDummy1 = _world.getParameters().getObject("portraitDummy1", L2Npc.class);
					final L2Npc portraitDummy3 = _world.getParameters().getObject("portraitDummy3", L2Npc.class);
					broadCastPacket(_world, new SocialAction(demons.get(0).getObjectId(), 1));
					broadCastPacket(_world, new SocialAction(demons.get(3).getObjectId(), 1));
					sendPacketX(new SpecialCamera(portraitDummy1, 1000, 118, 0, 0, 1000, 0, 0, 1, 0, 0), new SpecialCamera(portraitDummy3, 1000, 62, 0, 0, 1000, 0, 0, 1, 0, 0), -87784);
					sendPacketX(new SpecialCamera(portraitDummy1, 1000, 118, 0, 0, 10000, 0, 0, 1, 0, 0), new SpecialCamera(portraitDummy3, 1000, 62, 0, 0, 10000, 0, 0, 1, 0, 0), -87784);
					ThreadPool.schedule(new IntroTask(_world, 11), 2000);
					break;
				}
				case 11:
				{
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					L2Npc portraitDummy1 = _world.getParameters().getObject("portraitDummy1", L2Npc.class);
					L2Npc portraitDummy3 = _world.getParameters().getObject("portraitDummy3", L2Npc.class);
					broadCastPacket(_world, new SpecialCamera(frintezza, 240, 90, 0, 0, 1000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(frintezza, 240, 90, 25, 5500, 10000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SocialAction(frintezza.getObjectId(), 3));
					portraitDummy1.deleteMe();
					portraitDummy3.deleteMe();
					portraitDummy1 = null;
					portraitDummy3 = null;
					ThreadPool.schedule(new IntroTask(_world, 12), 4500);
					break;
				}
				case 12:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("frintezza", L2Npc.class), 100, 195, 35, 0, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 13), 700);
					break;
				}
				case 13:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("frintezza", L2Npc.class), 100, 195, 35, 0, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 14), 1300);
					break;
				}
				case 14:
				{
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					broadCastPacket(_world, new ExShowScreenMessage(NpcStringId.MOURNFUL_CHORALE_PRELUDE, 2, 5000));
					broadCastPacket(_world, new SpecialCamera(frintezza, 120, 180, 45, 1500, 10000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new MagicSkillUse(frintezza, frintezza, 5006, 1, 34000, 0));
					ThreadPool.schedule(new IntroTask(_world, 15), 1500);
					break;
				}
				case 15:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("frintezza", L2Npc.class), 520, 135, 45, 8000, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 16), 7500);
					break;
				}
				case 16:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("frintezza", L2Npc.class), 1500, 110, 25, 10000, 13000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 17), 9500);
					break;
				}
				case 17:
				{
					final L2Npc overheadDummy = _world.getParameters().getObject("overheadDummy", L2Npc.class);
					final L2Npc scarletDummy = _world.getParameters().getObject("scarletDummy", L2Npc.class);
					broadCastPacket(_world, new SpecialCamera(overheadDummy, 930, 160, -20, 0, 1000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(overheadDummy, 600, 180, -25, 0, 10000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new MagicSkillUse(scarletDummy, overheadDummy, 5004, 1, 5800, 0));
					ThreadPool.schedule(new IntroTask(_world, 18), 5000);
					break;
				}
				case 18:
				{
					final L2Npc activeScarlet = addSpawn(29046, -87789, -153295, -9176, 16384, false, 0, false, _world.getInstanceId());
					activeScarlet.setRHandId(FIRST_SCARLET_WEAPON);
					activeScarlet.setIsInvul(true);
					activeScarlet.setIsImmobilized(true);
					activeScarlet.disableAllSkills();
					broadCastPacket(_world, new SocialAction(activeScarlet.getObjectId(), 3));
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("scarletDummy", L2Npc.class), 800, 180, 10, 1000, 10000, 0, 0, 1, 0, 0));
					_world.setParameter("activeScarlet", activeScarlet);
					ThreadPool.schedule(new IntroTask(_world, 19), 2100);
					break;
				}
				case 19:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("activeScarlet", L2Npc.class), 300, 60, 8, 0, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 20), 2000);
					break;
				}
				case 20:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("activeScarlet", L2Npc.class), 500, 90, 10, 3000, 5000, 0, 0, 1, 0, 0));
					_world.setParameter("songTask", ThreadPool.schedule(new SongTask(_world, 0), 100));
					ThreadPool.schedule(new IntroTask(_world, 21), 3000);
					break;
				}
				case 21:
				{
					final Map<L2Npc, Integer> portraitsC = new ConcurrentHashMap<>();
					for (int i = 0; i < PORTRAIT_SPAWNS.length; i++)
					{
						final L2Npc portrait = addSpawn(PORTRAIT_SPAWNS[i][0], PORTRAIT_SPAWNS[i][1], PORTRAIT_SPAWNS[i][2], PORTRAIT_SPAWNS[i][3], PORTRAIT_SPAWNS[i][4], false, 0, false, _world.getInstanceId());
						portraitsC.put(portrait, i);
					}
					_world.setParameter("portraits", portraitsC);
					L2Npc scarletDummy = _world.getParameters().getObject("scarletDummy", L2Npc.class);
					L2Npc overheadDummy = _world.getParameters().getObject("overheadDummy", L2Npc.class);
					overheadDummy.deleteMe();
					scarletDummy.deleteMe();
					overheadDummy = null;
					scarletDummy = null;
					ThreadPool.schedule(new IntroTask(_world, 22), 2000);
					break;
				}
				case 22:
				{
					for (L2Npc demon : _world.getAliveNpcs(DEMONS))
					{
						demon.setIsImmobilized(false);
						demon.enableAllSkills();
					}
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					activeScarlet.setIsInvul(false);
					activeScarlet.setIsImmobilized(false);
					activeScarlet.enableAllSkills();
					activeScarlet.setRunning();
					activeScarlet.doCast(INTRO_SKILL.getSkill());
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					frintezza.enableAllSkills();
					frintezza.disableCoreAI(true);
					frintezza.setIsMortal(false);
					startPc();
					ThreadPool.schedule(new DemonSpawnTask(_world), TIME_BETWEEN_DEMON_SPAWNS);
					break;
				}
				case 23:
				{
					broadCastPacket(_world, new SocialAction(_world.getParameters().getObject("frintezza", L2Npc.class).getObjectId(), 4));
					break;
				}
				case 24:
				{
					stopPc();
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					broadCastPacket(_world, new SpecialCamera(frintezza, 250, 120, 15, 0, 1000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(frintezza, 250, 120, 15, 0, 10000, 0, 0, 1, 0, 0));
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					activeScarlet.abortAttack();
					activeScarlet.abortCast();
					activeScarlet.setIsInvul(true);
					activeScarlet.setIsImmobilized(true);
					activeScarlet.disableAllSkills();
					ThreadPool.schedule(new IntroTask(_world, 25), 7000);
					break;
				}
				case 25:
				{
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					broadCastPacket(_world, new MagicSkillUse(frintezza, frintezza, 5006, 1, 34000, 0));
					broadCastPacket(_world, new SpecialCamera(frintezza, 500, 70, 15, 3000, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 26), 3000);
					break;
				}
				case 26:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("frintezza", L2Npc.class), 2500, 90, 12, 6000, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 27), 3000);
					break;
				}
				case 27:
				{
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					_world.getParameters().set("scarlet_x", activeScarlet.getX());
					_world.getParameters().set("scarlet_y", activeScarlet.getY());
					_world.getParameters().set("scarlet_z", activeScarlet.getZ());
					_world.getParameters().set("scarlet_h", activeScarlet.getHeading());
					final int scarlet_a;
					if (activeScarlet.getHeading() < 32768)
					{
						scarlet_a = Math.abs(180 - (int) (activeScarlet.getHeading() / 182.044444444));
					}
					else
					{
						scarlet_a = Math.abs(540 - (int) (activeScarlet.getHeading() / 182.044444444));
					}
					_world.getParameters().set("scarlet_a", scarlet_a);
					broadCastPacket(_world, new SpecialCamera(activeScarlet, 250, scarlet_a, 12, 0, 1000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(activeScarlet, 250, scarlet_a, 12, 0, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 28), 500);
					break;
				}
				case 28:
				{
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					activeScarlet.doDie(activeScarlet);
					broadCastPacket(_world, new SpecialCamera(activeScarlet, 450, _world.getParameters().getInt("scarlet_a", 0), 14, 8000, 8000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 29), 6250);
					ThreadPool.schedule(new IntroTask(_world, 30), 7200);
					break;
				}
				case 29:
				{
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					activeScarlet.deleteMe();
					break;
				}
				case 30:
				{
					final L2Npc activeScarlet = addSpawn(SCARLET2, _world.getParameters().getInt("scarlet_x", 0), _world.getParameters().getInt("scarlet_y", 0), _world.getParameters().getInt("scarlet_z", 0), _world.getParameters().getInt("scarlet_h", 0), false, 0, false, _world.getInstanceId());
					activeScarlet.setIsInvul(true);
					activeScarlet.setIsImmobilized(true);
					activeScarlet.disableAllSkills();
					broadCastPacket(_world, new SpecialCamera(activeScarlet, 450, _world.getParameters().getInt("scarlet_a", 0), 12, 500, 14000, 0, 0, 1, 0, 0));
					_world.setParameter("activeScarlet", activeScarlet);
					ThreadPool.schedule(new IntroTask(_world, 31), 8100);
					break;
				}
				case 31:
				{
					broadCastPacket(_world, new SocialAction(_world.getParameters().getObject("activeScarlet", L2Npc.class).getObjectId(), 2));
					ThreadPool.schedule(new IntroTask(_world, 32), 9000);
					break;
				}
				case 32:
				{
					startPc();
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					activeScarlet.setIsInvul(false);
					activeScarlet.setIsImmobilized(false);
					activeScarlet.enableAllSkills();
					_world.setParameter("isVideo", false);
					break;
				}
				case 33:
				{
					final L2Npc activeScarlet = _world.getParameters().getObject("activeScarlet", L2Npc.class);
					final int scarlet_a = _world.getParameters().getInt("scarlet_a", 0);
					broadCastPacket(_world, new SpecialCamera(activeScarlet, 300, scarlet_a - 180, 5, 0, 7000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(activeScarlet, 200, scarlet_a, 85, 4000, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 34), 7400);
					ThreadPool.schedule(new IntroTask(_world, 35), 7500);
					break;
				}
				case 34:
				{
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					frintezza.doDie(frintezza);
					break;
				}
				case 35:
				{
					final L2Npc frintezza = _world.getParameters().getObject("frintezza", L2Npc.class);
					broadCastPacket(_world, new SpecialCamera(frintezza, 100, 120, 5, 0, 7000, 0, 0, 1, 0, 0));
					broadCastPacket(_world, new SpecialCamera(frintezza, 100, 90, 5, 5000, 15000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 36), 7000);
					break;
				}
				case 36:
				{
					broadCastPacket(_world, new SpecialCamera(_world.getParameters().getObject("frintezza", L2Npc.class), 900, 90, 25, 7000, 10000, 0, 0, 1, 0, 0));
					ThreadPool.schedule(new IntroTask(_world, 37), 9000);
					break;
				}
				case 37:
				{
					controlStatus(_world);
					_world.setParameter("isVideo", false);
					startPc();
					break;
				}
			}
			
			running = false;
		}
		
		private void stopPc()
		{
			for (L2PcInstance player : _world.getAllowed())
			{
				if ((player != null) && player.isOnline() && (player.getInstanceId() == _world.getInstanceId()))
				{
					player.abortAttack();
					player.abortCast();
					player.disableAllSkills();
					player.setTarget(null);
					player.stopMove(null);
					player.setIsImmobilized(true);
					player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				}
			}
		}
		
		private void startPc()
		{
			for (L2PcInstance player : _world.getAllowed())
			{
				if ((player != null) && player.isOnline() && (player.getInstanceId() == _world.getInstanceId()))
				{
					player.enableAllSkills();
					player.setIsImmobilized(false);
				}
			}
		}
		
		private void sendPacketX(IClientOutgoingPacket packet1, IClientOutgoingPacket packet2, int x)
		{
			for (L2PcInstance player : _world.getAllowed())
			{
				if ((player != null) && player.isOnline() && (player.getInstanceId() == _world.getInstanceId()))
				{
					if (player.getX() < x)
					{
						player.sendPacket(packet1);
					}
					else
					{
						player.sendPacket(packet2);
					}
				}
			}
		}
	}
	
	private class StatusTask implements Runnable
	{
		private final InstanceWorld _world;
		private final int _status;
		
		StatusTask(InstanceWorld world, int status)
		{
			_world = world;
			_status = status;
		}
		
		@Override
		public void run()
		{
			if (InstanceManager.getInstance().getWorld(_world.getInstanceId()) != _world)
			{
				return;
			}
			switch (_status)
			{
				case 0:
				{
					ThreadPool.schedule(new StatusTask(_world, 1), 2000);
					for (int doorId : FIRST_ROOM_DOORS)
					{
						_world.openDoor(doorId);
					}
					break;
				}
				case 1:
				{
					addAggroToMobs();
					break;
				}
				case 2:
				{
					ThreadPool.schedule(new StatusTask(_world, 3), 100);
					for (int doorId : SECOND_ROOM_DOORS)
					{
						_world.openDoor(doorId);
					}
					break;
				}
				case 3:
				{
					addAggroToMobs();
					break;
				}
				case 4:
				{
					controlStatus(_world);
					break;
				}
			}
		}
		
		private void addAggroToMobs()
		{
			L2PcInstance target = _world.getAllowed().stream().findAny().get();
			if ((target == null) || (target.getInstanceId() != _world.getInstanceId()) || target.isDead() || target.isFakeDeath())
			{
				for (L2PcInstance plr : _world.getAllowed())
				{
					if ((plr != null) && (plr.getInstanceId() == _world.getInstanceId()) && !plr.isDead() && !plr.isFakeDeath())
					{
						target = plr;
						break;
					}
					target = null;
				}
			}
			for (L2Npc mob : _world.getParameters().getList("npcList", L2Npc.class, new ArrayList<>()))
			{
				mob.setRunning();
				if (target != null)
				{
					((L2MonsterInstance) mob).addDamageHate(target, 0, 500);
					mob.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
				}
				else
				{
					mob.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, MOVE_TO_CENTER);
				}
			}
		}
	}
	
	protected void broadCastPacket(InstanceWorld world, IClientOutgoingPacket packet)
	{
		for (L2PcInstance player : world.getAllowed())
		{
			if ((player != null) && player.isOnline() && (player.getInstanceId() == world.getInstanceId()))
			{
				player.sendPacket(packet);
			}
		}
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon, Skill skill)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if ((npc.getId() == SCARLET1) && (world.getStatus() == 3) && (npc.getCurrentHp() < (npc.getMaxHp() * 0.80)))
			{
				controlStatus(world);
			}
			else if ((npc.getId() == SCARLET1) && (world.getStatus() == 4) && (npc.getCurrentHp() < (npc.getMaxHp() * 0.20)))
			{
				controlStatus(world);
			}
		}
		return null;
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isSummon)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if (skill != null)
			{
				// When Dewdrop of Destruction is used on Portraits they suicide.
				if (CommonUtil.contains(PORTRAITS, npc.getId()) && (skill.getId() == DEWDROP_OF_DESTRUCTION_SKILL_ID))
				{
					npc.doDie(caster);
				}
				else if ((npc.getId() == FRINTEZZA) && (skill.getId() == SOUL_BREAKING_ARROW_SKILL_ID))
				{
					npc.setScriptValue(1);
					npc.setTarget(null);
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				}
			}
		}
		return null;
	}
	
	@Override
	public String onSpellFinished(L2Npc npc, L2PcInstance player, Skill skill)
	{
		if (skill.isSuicideAttack())
		{
			return onKill(npc, null, false);
		}
		return super.onSpellFinished(npc, player, skill);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if (npc.getId() == HALL_ALARM)
			{
				ThreadPool.schedule(new StatusTask(world, 0), 2000);
				if (debug)
				{
					LOGGER.info("[Final Emperial Tomb] Hall alarm is disabled, doors will open!");
				}
			}
			else if (npc.getId() == DARK_CHOIR_PLAYER)
			{
				final int darkChoirPlayerCount = world.getParameters().getInt("darkChoirPlayerCount", 0) - 1;
				world.setParameter("darkChoirPlayerCount", darkChoirPlayerCount);
				if (darkChoirPlayerCount < 1)
				{
					ThreadPool.schedule(new StatusTask(world, 2), 2000);
					if (debug)
					{
						LOGGER.info("[Final Emperial Tomb] All Dark Choir Players are killed, doors will open!");
					}
				}
			}
			else if (npc.getId() == SCARLET2)
			{
				controlStatus(world);
			}
			else if (world.getStatus() <= 2)
			{
				if (npc.getId() == HALL_KEEPER_CAPTAIN)
				{
					if (getRandom(100) < 5)
					{
						npc.dropItem(player, DEWDROP_OF_DESTRUCTION_ITEM_ID, 1);
					}
				}
				
				if (checkKillProgress(npc, world))
				{
					controlStatus(world);
				}
			}
			else if (CommonUtil.contains(DEMONS, npc.getId()))
			{
				final List<L2Npc> demons = world.getParameters().getList("demons", L2Npc.class);
				demons.remove(npc);
				world.setParameter("demons", demons);
			}
			else if (CommonUtil.contains(PORTRAITS, npc.getId()))
			{
				Map<L2Npc, Integer> portraits = world.getParameters().getMap("portraits", L2Npc.class, Integer.class);
				portraits.remove(npc);
				world.setParameter("portraits", portraits);
			}
		}
		return "";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		getQuestState(player, true);
		if (npc.getId() == GUIDE)
		{
			enterInstance(player, TEMPLATE_ID);
		}
		else if (npc.getId() == CUBE)
		{
			player.teleToLocation(181380 + getRandom(50), -80903 + getRandom(50), -2731);
			return null;
		}
		return "";
	}
	
	public static void main(String[] args)
	{
		new FinalEmperialTomb();
	}
}

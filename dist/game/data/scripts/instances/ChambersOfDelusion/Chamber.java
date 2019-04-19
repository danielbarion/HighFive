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
package instances.ChambersOfDelusion;

import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.Earthquake;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Util;

import instances.AbstractInstance;

/**
 * Chambers of Delusion superclass.
 * @author GKR
 */
public abstract class Chamber extends AbstractInstance
{
	// Items
	private static final int ENRIA = 4042;
	private static final int ASOFE = 4043;
	private static final int THONS = 4044;
	private static final int LEONARD = 9628;
	private static final int DELUSION_MARK = 15311;
	
	// NPCs
	private final int ENTRANCE_GATEKEEPER;
	private final int ROOM_GATEKEEPER_FIRST;
	private final int ROOM_GATEKEEPER_LAST;
	private final int AENKINEL;
	private final int BOX;
	
	// Skills
	private static final SkillHolder SUCCESS_SKILL = new SkillHolder(5758, 1);
	private static final SkillHolder FAIL_SKILL = new SkillHolder(5376, 4);
	
	private static final int ROOM_CHANGE_INTERVAL = 480; // 8 min
	private static final int ROOM_CHANGE_RANDOM_TIME = 120; // 2 min
	
	// Instance restart time
	private static final int RESET_HOUR = 6;
	private static final int RESET_MIN = 30;
	
	// Following values vary between scripts
	private final int INSTANCEID;
	
	protected Location[] ROOM_ENTER_POINTS;
	// Misc
	private static final String RETURN = Chamber.class.getSimpleName() + "_return";
	
	protected Chamber(String name, int instanceId, int entranceGKId, int roomGKFirstId, int roomGKLastId, int aenkinelId, int boxId)
	{
		INSTANCEID = instanceId;
		ENTRANCE_GATEKEEPER = entranceGKId;
		ROOM_GATEKEEPER_FIRST = roomGKFirstId;
		ROOM_GATEKEEPER_LAST = roomGKLastId;
		AENKINEL = aenkinelId;
		BOX = boxId;
		
		addStartNpc(ENTRANCE_GATEKEEPER);
		addTalkId(ENTRANCE_GATEKEEPER);
		for (int i = ROOM_GATEKEEPER_FIRST; i <= ROOM_GATEKEEPER_LAST; i++)
		{
			addStartNpc(i);
			addTalkId(i);
		}
		addKillId(AENKINEL);
		addAttackId(BOX);
		addSpellFinishedId(BOX);
		addEventReceivedId(BOX);
	}
	
	private boolean isBigChamber()
	{
		return ((INSTANCEID == 131) || (INSTANCEID == 132));
	}
	
	private boolean isBossRoom(InstanceWorld world)
	{
		return (world.getParameters().getInt("currentRoom", 0) == (ROOM_ENTER_POINTS.length - 1));
	}
	
	@Override
	protected boolean checkConditions(L2PcInstance player)
	{
		final L2Party party = player.getParty();
		if (party == null)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_ARE_NOT_CURRENTLY_IN_A_PARTY_SO_YOU_CANNOT_ENTER));
			return false;
		}
		
		if (party.getLeader() != player)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER));
			return false;
		}
		
		for (L2PcInstance partyMember : party.getMembers())
		{
			if (partyMember.getLevel() < 80)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY);
				sm.addPcName(partyMember);
				party.broadcastPacket(sm);
				return false;
			}
			
			if (!Util.checkIfInRange(1000, player, partyMember, true))
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_IN_A_LOCATION_WHICH_CANNOT_BE_ENTERED_THEREFORE_IT_CANNOT_BE_PROCESSED);
				sm.addPcName(partyMember);
				party.broadcastPacket(sm);
				return false;
			}
			
			if (isBigChamber())
			{
				final long reentertime = InstanceManager.getInstance().getInstanceTime(partyMember.getObjectId(), INSTANCEID);
				
				if (System.currentTimeMillis() < reentertime)
				{
					final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_MAY_NOT_RE_ENTER_YET);
					sm.addPcName(partyMember);
					party.broadcastPacket(sm);
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void markRestriction(InstanceWorld world)
	{
		if (world != null)
		{
			final Calendar reenter = Calendar.getInstance();
			final Calendar now = Calendar.getInstance();
			reenter.set(Calendar.MINUTE, RESET_MIN);
			reenter.set(Calendar.HOUR_OF_DAY, RESET_HOUR);
			if (reenter.before(now))
			{
				reenter.add(Calendar.DAY_OF_WEEK, 1);
			}
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.INSTANT_ZONE_S1_S_ENTRY_HAS_BEEN_RESTRICTED_YOU_CAN_CHECK_THE_NEXT_POSSIBLE_ENTRY_TIME_BY_USING_THE_COMMAND_INSTANCEZONE);
			sm.addString(InstanceManager.getInstance().getInstanceIdName(world.getTemplateId()));
			// set instance reenter time for all allowed players
			for (L2PcInstance player : world.getAllowed())
			{
				if ((player != null) && player.isOnline())
				{
					InstanceManager.getInstance().setInstanceTime(player.getObjectId(), world.getTemplateId(), reenter.getTimeInMillis());
					player.sendPacket(sm);
				}
			}
		}
	}
	
	protected void changeRoom(InstanceWorld world)
	{
		final L2Party party = world.getParameters().getObject("PartyInside", L2Party.class);
		final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
		
		if ((party == null) || (inst == null))
		{
			return;
		}
		
		int newRoom = world.getParameters().getInt("currentRoom", 0);
		
		// Do nothing, if there are raid room of Sqare or Tower Chamber
		if (isBigChamber() && isBossRoom(world))
		{
			return;
		}
		
		// Teleport to raid room 10 min or lesser before instance end time for Tower and Square Chambers
		else if (isBigChamber() && ((inst.getInstanceEndTime() - System.currentTimeMillis()) < 600000))
		{
			newRoom = ROOM_ENTER_POINTS.length - 1;
		}
		
		// 10% chance for teleport to raid room if not here already for Northern, Southern, Western and Eastern Chambers
		else if (!isBigChamber() && !isBossRoom(world) && (getRandom(100) < 10))
		{
			newRoom = ROOM_ENTER_POINTS.length - 1;
		}
		
		else
		{
			while (newRoom == world.getParameters().getInt("currentRoom", 0)) // otherwise teleport to another room, except current
			{
				newRoom = getRandom(ROOM_ENTER_POINTS.length - 1);
			}
		}
		
		for (L2PcInstance partyMember : party.getMembers())
		{
			if (world.getInstanceId() == partyMember.getInstanceId())
			{
				partyMember.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				teleportPlayer(partyMember, ROOM_ENTER_POINTS[newRoom], world.getInstanceId());
			}
		}
		
		world.setParameter("currentRoom", newRoom);
		
		// Do not schedule room change for Square and Tower Chambers, if raid room is reached
		if (isBigChamber() && isBossRoom(world))
		{
			inst.setDuration((int) ((inst.getInstanceEndTime() - System.currentTimeMillis()) + 1200000)); // Add 20 min to instance time if raid room is reached
			
			for (L2Npc npc : inst.getNpcs())
			{
				if (npc.getId() == ROOM_GATEKEEPER_LAST)
				{
					npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.TWENTY_MINUTES_ARE_ADDED_TO_THE_REMAINING_TIME_IN_THE_INSTANT_ZONE));
				}
			}
		}
		else
		{
			scheduleRoomChange(world, false);
		}
	}
	
	private void enter(InstanceWorld world)
	{
		final L2Party party = world.getParameters().getObject("PartyInside", L2Party.class);
		
		if (party == null)
		{
			return;
		}
		
		for (L2PcInstance partyMember : party.getMembers())
		{
			if (hasQuestItems(partyMember, DELUSION_MARK))
			{
				takeItems(partyMember, DELUSION_MARK, -1);
			}
			
			if (party.isLeader(partyMember))
			{
				giveItems(partyMember, DELUSION_MARK, 1);
			}
			
			// Save location for teleport back into main hall
			partyMember.getVariables().set(RETURN, partyMember.getX() + ";" + partyMember.getY() + ";" + partyMember.getZ());
			
			partyMember.setInstanceId(world.getInstanceId());
			world.addAllowed(partyMember);
		}
		
		changeRoom(world);
	}
	
	protected void earthQuake(InstanceWorld world)
	{
		final L2Party party = world.getParameters().getObject("PartyInside", L2Party.class);
		
		if (party == null)
		{
			return;
		}
		
		for (L2PcInstance partyMember : party.getMembers())
		{
			if (world.getInstanceId() == partyMember.getInstanceId())
			{
				partyMember.sendPacket(new Earthquake(partyMember.getX(), partyMember.getY(), partyMember.getZ(), 20, 10));
			}
		}
	}
	
	@Override
	public void onEnterInstance(L2PcInstance player, InstanceWorld world, boolean firstEntrance)
	{
		if (firstEntrance)
		{
			world.setParameter("PartyInside", player.getParty());
			world.setParameter("banishTask", ThreadPool.scheduleAtFixedRate(new BanishTask(world), 60000, 60000));
			enter(world);
		}
		else
		{
			teleportPlayer(player, ROOM_ENTER_POINTS[world.getParameters().getInt("currentRoom", 0)], world.getInstanceId());
		}
	}
	
	protected void exitInstance(L2PcInstance player)
	{
		if ((player == null) || !player.isOnline() || (player.getInstanceId() == 0))
		{
			return;
		}
		final Instance inst = InstanceManager.getInstance().getInstance(player.getInstanceId());
		final Location ret = inst.getExitLoc();
		final String return_point = player.getVariables().getString(RETURN, null);
		if (return_point != null)
		{
			final String[] coords = return_point.split(";");
			if (coords.length == 3)
			{
				try
				{
					final int x = Integer.parseInt(coords[0]);
					final int y = Integer.parseInt(coords[1]);
					final int z = Integer.parseInt(coords[2]);
					ret.setLocation(new Location(x, y, z));
				}
				catch (Exception e)
				{
				}
			}
		}
		
		teleportPlayer(player, ret, 0);
		final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		if (world != null)
		{
			world.removeAllowed((player));
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = "";
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		
		if ((player != null) && (world != null) && (npc.getId() >= ROOM_GATEKEEPER_FIRST) && (npc.getId() <= ROOM_GATEKEEPER_LAST))
		{
			// Change room from dialog
			if (event.equals("next_room"))
			{
				if (player.getParty() == null)
				{
					htmltext = getHtm(player, "data/scripts/instances/ChambersOfDelusion/no_party.html");
				}
				else if (player.getParty().getLeaderObjectId() != player.getObjectId())
				{
					htmltext = getHtm(player, "data/scripts/instances/ChambersOfDelusion/no_leader.html");
				}
				else if (hasQuestItems(player, DELUSION_MARK))
				{
					takeItems(player, DELUSION_MARK, 1);
					stopRoomChangeTask(world);
					changeRoom(world);
				}
				else
				{
					htmltext = getHtm(player, "data/scripts/instances/ChambersOfDelusion/no_item.html");
				}
			}
			else if (event.equals("go_out"))
			{
				if (player.getParty() == null)
				{
					htmltext = getHtm(player, "data/scripts/instances/ChambersOfDelusion/no_party.html");
				}
				else if (player.getParty().getLeaderObjectId() != player.getObjectId())
				{
					htmltext = getHtm(player, "data/scripts/instances/ChambersOfDelusion/no_leader.html");
				}
				else
				{
					final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
					
					stopRoomChangeTask(world);
					stopBanishTask(world);
					
					for (L2PcInstance partyMember : player.getParty().getMembers())
					{
						exitInstance(partyMember);
					}
					
					inst.setEmptyDestroyTime(0);
				}
			}
			else if (event.equals("look_party"))
			{
				if ((player.getParty() != null) && (player.getParty() == world.getParameters().getObject("PartyInside", L2Party.class)))
				{
					teleportPlayer(player, ROOM_ENTER_POINTS[world.getParameters().getInt("currentRoom", 0)], world.getInstanceId(), false);
				}
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isPet, Skill skill)
	{
		if (!npc.isBusy() && (npc.getCurrentHp() < (npc.getMaxHp() / 10)))
		{
			npc.setBusy(true);
			if (getRandom(100) < 25) // 25% chance to reward
			{
				if (getRandom(100) < 33)
				{
					npc.dropItem(attacker, ENRIA, (int) (3 * Config.RATE_QUEST_DROP));
				}
				if (getRandom(100) < 50)
				{
					npc.dropItem(attacker, THONS, (int) (4 * Config.RATE_QUEST_DROP));
				}
				if (getRandom(100) < 50)
				{
					npc.dropItem(attacker, ASOFE, (int) (4 * Config.RATE_QUEST_DROP));
				}
				if (getRandom(100) < 16)
				{
					npc.dropItem(attacker, LEONARD, (int) (2 * Config.RATE_QUEST_DROP));
				}
				
				npc.broadcastEvent("SCE_LUCKY", 2000, null);
				npc.doCast(SUCCESS_SKILL.getSkill());
			}
			else
			{
				npc.broadcastEvent("SCE_DREAM_FIRE_IN_THE_HOLE", 2000, null);
			}
		}
		
		return super.onAttack(npc, attacker, damage, isPet, skill);
	}
	
	@Override
	public String onEventReceived(String eventName, L2Npc sender, L2Npc receiver, L2Object reference)
	{
		switch (eventName)
		{
			case "SCE_LUCKY":
			{
				receiver.setBusy(true);
				receiver.doCast(SUCCESS_SKILL.getSkill());
				break;
			}
			case "SCE_DREAM_FIRE_IN_THE_HOLE":
			{
				receiver.setBusy(true);
				receiver.doCast(FAIL_SKILL.getSkill());
				break;
			}
		}
		return null;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		if (world != null)
		{
			final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
			
			if (isBigChamber())
			{
				markRestriction(world); // Set reenter restriction
				if ((inst.getInstanceEndTime() - System.currentTimeMillis()) > 300000)
				{
					inst.setDuration(300000); // Finish instance in 5 minutes
				}
			}
			else
			{
				stopRoomChangeTask(world);
				scheduleRoomChange(world, true);
			}
			
			inst.spawnGroup("boxes");
		}
		
		return super.onKill(npc, player, isPet);
	}
	
	@Override
	public String onSpellFinished(L2Npc npc, L2PcInstance player, Skill skill)
	{
		if ((npc.getId() == BOX) && ((skill.getId() == 5376) || (skill.getId() == 5758)) && !npc.isDead())
		{
			npc.doDie(player);
		}
		return super.onSpellFinished(npc, player, skill);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final int npcId = npc.getId();
		getQuestState(player, true);
		
		if (npcId == ENTRANCE_GATEKEEPER)
		{
			if (checkConditions(player))
			{
				enterInstance(player, INSTANCEID);
			}
		}
		return "";
	}
	
	protected L2Party getPartyInside(InstanceWorld world)
	{
		return world.getParameters().getObject("PartyInside", L2Party.class);
	}
	
	protected void scheduleRoomChange(InstanceWorld world, boolean bossRoom)
	{
		final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
		final long nextInterval = bossRoom ? 60000 : (ROOM_CHANGE_INTERVAL + getRandom(ROOM_CHANGE_RANDOM_TIME)) * 1000;
		
		// Schedule next room change only if remaining time is enough
		if ((inst.getInstanceEndTime() - System.currentTimeMillis()) > nextInterval)
		{
			world.setParameter("roomChangeTask", ThreadPool.schedule(new ChangeRoomTask(world), nextInterval - 5000));
		}
	}
	
	protected void stopBanishTask(InstanceWorld world)
	{
		final ScheduledFuture<?> banishTask = world.getParameters().getObject("banishTask", ScheduledFuture.class);
		if (banishTask != null)
		{
			banishTask.cancel(true);
		}
	}
	
	protected void stopRoomChangeTask(InstanceWorld world)
	{
		final ScheduledFuture<?> roomChangeTask = world.getParameters().getObject("roomChangeTask", ScheduledFuture.class);
		if (roomChangeTask != null)
		{
			roomChangeTask.cancel(true);
		}
	}
	
	protected class BanishTask implements Runnable
	{
		final InstanceWorld _world;
		
		public BanishTask(InstanceWorld world)
		{
			_world = world;
		}
		
		@Override
		public void run()
		{
			final Instance inst = InstanceManager.getInstance().getInstance(_world.getInstanceId());
			if ((inst == null) || ((inst.getInstanceEndTime() - System.currentTimeMillis()) < 60000))
			{
				final ScheduledFuture<?> banishTask = _world.getParameters().getObject("banishTask", ScheduledFuture.class);
				if (banishTask != null)
				{
					banishTask.cancel(true);
				}
			}
			else
			{
				for (int objId : inst.getPlayers())
				{
					final L2PcInstance pl = L2World.getInstance().getPlayer(objId);
					if ((pl != null) && pl.isOnline())
					{
						final L2Party party = _world.getParameters().getObject("PartyInside", L2Party.class);
						if ((party == null) || !pl.isInParty() || (party != pl.getParty()))
						{
							exitInstance(pl);
						}
					}
				}
			}
		}
	}
	
	protected class ChangeRoomTask implements Runnable
	{
		final InstanceWorld _world;
		
		public ChangeRoomTask(InstanceWorld world)
		{
			_world = world;
		}
		
		@Override
		public void run()
		{
			try
			{
				earthQuake(_world);
				Thread.sleep(5000);
				changeRoom(_world);
			}
			catch (Exception e)
			{
				LOGGER.log(Level.WARNING, getClass().getSimpleName() + " ChangeRoomTask exception : " + e.getMessage(), e);
			}
		}
	}
}

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
package ai.areas.Hellbound.Instances.UrbanArea;

import java.util.concurrent.ScheduledFuture;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.PcCondOverride;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2QuestGuardInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Util;

import ai.areas.Hellbound.HellboundEngine;
import instances.AbstractInstance;

/**
 * Urban Area instance zone.
 * @author GKR
 */
public final class UrbanArea extends AbstractInstance
{
	// NPCs
	private static final int TOMBSTONE = 32343;
	private static final int KANAF = 32346;
	private static final int KEYMASTER = 22361;
	private static final int AMASKARI = 22449;
	private static final int DOWNTOWN_NATIVE = 32358;
	private static final int TOWN_GUARD = 22359;
	private static final int TOWN_PATROL = 22360;
	// Items
	private static final int KEY = 9714;
	// Skills
	private static final SkillHolder STONE = new SkillHolder(4616, 1);
	// Locations
	private static final Location AMASKARI_SPAWN_POINT = new Location(19424, 253360, -2032, 16860);
	private static final Location ENTRY_POINT = new Location(14117, 255434, -2016);
	protected static final Location EXIT_POINT = new Location(16262, 283651, -9700);
	// Misc
	private static final int MIN_LV = 78;
	private static final int TEMPLATE_ID = 2;
	
	private static final NpcStringId[] NPCSTRING_ID =
	{
		NpcStringId.INVADER,
		NpcStringId.YOU_HAVE_DONE_WELL_IN_FINDING_ME_BUT_I_CANNOT_JUST_HAND_YOU_THE_KEY
	};
	
	private static final NpcStringId[] NATIVES_NPCSTRING_ID =
	{
		NpcStringId.THANK_YOU_FOR_SAVING_ME,
		NpcStringId.GUARDS_ARE_COMING_RUN,
		NpcStringId.NOW_I_CAN_ESCAPE_ON_MY_OWN
	};
	
	public UrbanArea()
	{
		addFirstTalkId(DOWNTOWN_NATIVE);
		addStartNpc(KANAF, DOWNTOWN_NATIVE);
		addTalkId(KANAF, DOWNTOWN_NATIVE);
		addAttackId(TOWN_GUARD, KEYMASTER);
		addAggroRangeEnterId(TOWN_GUARD);
		addKillId(AMASKARI);
		addSpawnId(DOWNTOWN_NATIVE, TOWN_GUARD, TOWN_PATROL, KEYMASTER);
	}
	
	@Override
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (!npc.isAffectedBySkill(STONE.getSkillId()))
		{
			return "32358-02.htm";
		}
		return "32358-01.htm";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		if (npc.getId() == KANAF)
		{
			if (!player.canOverrideCond(PcCondOverride.INSTANCE_CONDITIONS))
			{
				if (HellboundEngine.getInstance().getLevel() < 10)
				{
					htmltext = "32346-lvl.htm";
				}
				
				if (player.getParty() == null)
				{
					htmltext = "32346-party.htm";
				}
			}
			
			if (htmltext == null)
			{
				enterInstance(player, TEMPLATE_ID);
			}
		}
		else if (npc.getId() == TOMBSTONE)
		{
			final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
			if (world != null)
			{
				final L2Party party = player.getParty();
				
				if (party == null)
				{
					htmltext = "32343-02.htm";
				}
				else if (npc.isBusy())
				{
					htmltext = "32343-02c.htm";
				}
				else if (player.getInventory().getInventoryItemCount(KEY, -1, false) >= 1)
				{
					for (L2PcInstance partyMember : party.getMembers())
					{
						if (!Util.checkIfInRange(300, npc, partyMember, true))
						{
							return "32343-02b.htm";
						}
					}
					
					if (player.destroyItemByItemId("Quest", KEY, 1, npc, true))
					{
						npc.setBusy(true);
						// destroy instance after 5 min
						final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
						inst.setDuration(5 * 60000);
						inst.setEmptyDestroyTime(0);
						ThreadPool.schedule(new ExitInstance(party, world), 285000);
						htmltext = "32343-02d.htm";
					}
				}
				else
				{
					htmltext = "32343-02a.htm";
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if (npc.getId() == DOWNTOWN_NATIVE)
			{
				if (event.equalsIgnoreCase("rebuff") && !world.getParameters().getBoolean("isAmaskariDead", false))
				{
					STONE.getSkill().applyEffects(npc, npc);
				}
				else if (event.equalsIgnoreCase("break_chains"))
				{
					if (!npc.isAffectedBySkill(STONE.getSkillId()) || world.getParameters().getBoolean("isAmaskariDead", false))
					{
						npc.broadcastSay(ChatType.NPC_GENERAL, NATIVES_NPCSTRING_ID[0]);
						npc.broadcastSay(ChatType.NPC_GENERAL, NATIVES_NPCSTRING_ID[2]);
					}
					else
					{
						cancelQuestTimer("rebuff", npc, null);
						if (npc.isAffectedBySkill(STONE.getSkillId()))
						{
							npc.stopSkillEffects(false, STONE.getSkillId());
						}
						
						npc.broadcastSay(ChatType.NPC_GENERAL, NATIVES_NPCSTRING_ID[0]);
						npc.broadcastSay(ChatType.NPC_GENERAL, NATIVES_NPCSTRING_ID[1]);
						HellboundEngine.getInstance().updateTrust(10, true);
						npc.scheduleDespawn(3000);
						// Try to call Amaskari
						final L2Npc spawnedAmaskari = world.getParameters().getObject("spawnedAmaskari", L2Npc.class);
						if ((spawnedAmaskari != null) && !spawnedAmaskari.isDead() && (getRandom(1000) < 25) && Util.checkIfInRange(5000, npc, spawnedAmaskari, false))
						{
							final ScheduledFuture<?> activeAmaskariCall = world.getParameters().getObject("activeAmaskariCall", ScheduledFuture.class);
							if (activeAmaskariCall != null)
							{
								activeAmaskariCall.cancel(true);
							}
							world.setParameter("activeAmaskariCall", ThreadPool.schedule(new CallAmaskari(npc), 25000));
						}
					}
				}
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public final String onSpawn(L2Npc npc)
	{
		if (npc.getId() == DOWNTOWN_NATIVE)
		{
			((L2QuestGuardInstance) npc).setPassive(true);
			((L2QuestGuardInstance) npc).setAutoAttackable(false);
			STONE.getSkill().applyEffects(npc, npc);
			startQuestTimer("rebuff", 357000, npc, null);
		}
		else if ((npc.getId() == TOWN_GUARD) || (npc.getId() == KEYMASTER))
		{
			npc.setBusy(false);
			npc.setBusyMessage("");
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if (!npc.isBusy())
			{
				npc.broadcastSay(ChatType.NPC_GENERAL, NPCSTRING_ID[0]);
				npc.setBusy(true);
				
				final L2Npc spawnedAmaskari = world.getParameters().getObject("spawnedAmaskari", L2Npc.class);
				if ((spawnedAmaskari != null) && !spawnedAmaskari.isDead() && (getRandom(1000) < 25) && Util.checkIfInRange(1000, npc, spawnedAmaskari, false))
				{
					final ScheduledFuture<?> activeAmaskariCall = world.getParameters().getObject("activeAmaskariCall", ScheduledFuture.class);
					if (activeAmaskariCall != null)
					{
						activeAmaskariCall.cancel(true);
					}
					world.setParameter("activeAmaskariCall", ThreadPool.schedule(new CallAmaskari(npc), 25000));
				}
			}
		}
		return super.onAggroRangeEnter(npc, player, isSummon);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon, Skill skill)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			if (!world.getParameters().getBoolean("isAmaskariDead", false) && !(npc.getBusyMessage().equalsIgnoreCase("atk") || npc.isBusy()))
			{
				int msgId;
				int range;
				switch (npc.getId())
				{
					case TOWN_GUARD:
					{
						msgId = 0;
						range = 1000;
						break;
					}
					case KEYMASTER:
					{
						msgId = 1;
						range = 5000;
						break;
					}
					default:
					{
						msgId = -1;
						range = 0;
					}
				}
				if (msgId >= 0)
				{
					npc.broadcastSay(ChatType.NPC_GENERAL, NPCSTRING_ID[msgId], range);
				}
				npc.setBusy(true);
				npc.setBusyMessage("atk");
				
				final L2Npc spawnedAmaskari = world.getParameters().getObject("spawnedAmaskari", L2Npc.class);
				if ((spawnedAmaskari != null) && !spawnedAmaskari.isDead() && (getRandom(1000) < 25) && Util.checkIfInRange(range, npc, spawnedAmaskari, false))
				{
					final ScheduledFuture<?> activeAmaskariCall = world.getParameters().getObject("activeAmaskariCall", ScheduledFuture.class);
					if (activeAmaskariCall != null)
					{
						activeAmaskariCall.cancel(true);
					}
					world.setParameter("activeAmaskariCall", ThreadPool.schedule(new CallAmaskari(npc), 25000));
				}
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon, skill);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			world.setParameter("isAmaskariDead", true);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	protected boolean checkConditions(L2PcInstance player)
	{
		if (player.canOverrideCond(PcCondOverride.INSTANCE_CONDITIONS))
		{
			return true;
		}
		
		final L2Party party = player.getParty();
		
		if ((party == null) || !party.isLeader(player))
		{
			player.sendPacket(SystemMessageId.ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER);
			return false;
		}
		
		for (L2PcInstance partyMember : party.getMembers())
		{
			if (partyMember.getLevel() < MIN_LV)
			{
				party.broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.C1_S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY).addPcName(partyMember));
				return false;
			}
			
			if (!Util.checkIfInRange(1000, player, partyMember, true))
			{
				party.broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.C1_IS_IN_A_LOCATION_WHICH_CANNOT_BE_ENTERED_THEREFORE_IT_CANNOT_BE_PROCESSED).addPcName(partyMember));
				return false;
			}
			
			if (InstanceManager.getInstance().getPlayerWorld(player) != null)
			{
				party.broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON).addPcName(partyMember));
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
			if (player.getParty() == null)
			{
				teleportPlayer(player, ENTRY_POINT, world.getInstanceId());
				world.addAllowed(player);
			}
			else
			{
				for (L2PcInstance partyMember : player.getParty().getMembers())
				{
					teleportPlayer(partyMember, ENTRY_POINT, world.getInstanceId());
					world.addAllowed(partyMember);
				}
			}
			world.setParameter("spawnedAmaskari", addSpawn(AMASKARI, AMASKARI_SPAWN_POINT, false, 0, false, world.getInstanceId()));
		}
		else
		{
			teleportPlayer(player, ENTRY_POINT, world.getInstanceId());
		}
	}
	
	private static class CallAmaskari implements Runnable
	{
		private final L2Npc _caller;
		
		public CallAmaskari(L2Npc caller)
		{
			_caller = caller;
		}
		
		@Override
		public void run()
		{
			if ((_caller != null) && !_caller.isDead())
			{
				final InstanceWorld world = InstanceManager.getInstance().getWorld(_caller);
				if (world != null)
				{
					final L2Npc spawnedAmaskari = world.getParameters().getObject("spawnedAmaskari", L2Npc.class);
					if ((spawnedAmaskari != null) && !spawnedAmaskari.isDead())
					{
						spawnedAmaskari.teleToLocation(_caller.getLocation());
						spawnedAmaskari.broadcastPacket(new NpcSay(spawnedAmaskari.getObjectId(), ChatType.NPC_GENERAL, spawnedAmaskari.getId(), NpcStringId.I_LL_MAKE_YOU_FEEL_SUFFERING_LIKE_A_FLAME_THAT_IS_NEVER_EXTINGUISHED));
					}
				}
			}
		}
	}
	
	private class ExitInstance implements Runnable
	{
		private final L2Party _party;
		private final InstanceWorld _world;
		
		public ExitInstance(L2Party party, InstanceWorld world)
		{
			_party = party;
			_world = world;
		}
		
		@Override
		public void run()
		{
			if ((_party != null) && (_world != null))
			{
				for (L2PcInstance partyMember : _party.getMembers())
				{
					if ((partyMember != null) && !partyMember.isDead())
					{
						_world.removeAllowed(partyMember);
						teleportPlayer(partyMember, EXIT_POINT, 0);
					}
				}
			}
		}
	}
}

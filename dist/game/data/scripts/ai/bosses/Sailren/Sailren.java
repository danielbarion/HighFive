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
package ai.bosses.Sailren;

import com.l2jmobius.gameserver.instancemanager.GlobalVariablesManager;
import com.l2jmobius.gameserver.instancemanager.ZoneManager;
import com.l2jmobius.gameserver.model.TeleportWhereType;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2RaidBossInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.zone.type.L2NoRestartZone;
import com.l2jmobius.gameserver.network.serverpackets.SpecialCamera;

import ai.AbstractNpcAI;

/**
 * Sailren AI.
 * @author St3eT
 */
public final class Sailren extends AbstractNpcAI
{
	// NPCs
	private static final int STATUE = 32109; // Shilen's Stone Statue
	private static final int MOVIE_NPC = 32110; // Invisible NPC for movie
	private static final int SAILREN = 29065; // Sailren
	private static final int VELOCIRAPTOR = 22218; // Velociraptor
	private static final int PTEROSAUR = 22199; // Pterosaur
	private static final int TREX = 22217; // Tyrannosaurus
	private static final int CUBIC = 32107; // Teleportation Cubic
	// Item
	private static final int GAZKH = 8784; // Gazkh
	// Skill
	private static final SkillHolder ANIMATION = new SkillHolder(5090, 1);
	// Zone
	private static final L2NoRestartZone zone = ZoneManager.getInstance().getZoneById(70049, L2NoRestartZone.class);
	// Misc
	private static final int RESPAWN = 1; // Respawn time (in hours)
	private static final int MAX_TIME = 3200; // Max time for Sailren fight (in minutes)
	private static Status STATUS = Status.ALIVE;
	private static int _killCount = 0;
	private static long _lastAttack = 0;
	
	private enum Status
	{
		ALIVE,
		IN_FIGHT,
		DEAD
	}
	
	private Sailren()
	{
		addStartNpc(STATUE, CUBIC);
		addTalkId(STATUE, CUBIC);
		addFirstTalkId(STATUE);
		addKillId(VELOCIRAPTOR, PTEROSAUR, TREX, SAILREN);
		addAttackId(VELOCIRAPTOR, PTEROSAUR, TREX, SAILREN);
		
		final long remain = GlobalVariablesManager.getInstance().getLong("SailrenRespawn", 0) - System.currentTimeMillis();
		if (remain > 0)
		{
			STATUS = Status.DEAD;
			startQuestTimer("CLEAR_STATUS", remain, null, null);
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "32109-01.html":
			case "32109-01a.html":
			case "32109-02a.html":
			case "32109-03a.html":
			{
				return event;
			}
			case "enter":
			{
				String htmltext = null;
				if (!player.isInParty())
				{
					htmltext = "32109-01.html";
				}
				else if (STATUS == Status.DEAD)
				{
					htmltext = "32109-04.html";
				}
				else if (STATUS == Status.IN_FIGHT)
				{
					htmltext = "32109-05.html";
				}
				else if (!player.getParty().isLeader(player))
				{
					htmltext = "32109-03.html";
				}
				else if (!hasQuestItems(player, GAZKH))
				{
					htmltext = "32109-02.html";
				}
				else
				{
					takeItems(player, GAZKH, 1);
					STATUS = Status.IN_FIGHT;
					_lastAttack = System.currentTimeMillis();
					for (L2PcInstance member : player.getParty().getMembers())
					{
						if (member.isInsideRadius3D(npc, 1000))
						{
							member.teleToLocation(27549, -6638, -2008);
						}
					}
					startQuestTimer("SPAWN_VELOCIRAPTOR", 60000, null, null);
					startQuestTimer("TIME_OUT", MAX_TIME * 1000, null, null);
					startQuestTimer("CHECK_ATTACK", 120000, null, null);
				}
				return htmltext;
			}
			case "teleportOut":
			{
				player.teleToLocation(TeleportWhereType.TOWN);
				break;
			}
			case "SPAWN_VELOCIRAPTOR":
			{
				for (int i = 0; i < 3; i++)
				{
					addSpawn(VELOCIRAPTOR, 27313 + getRandom(150), -6766 + getRandom(150), -1975, 0, false, 0);
				}
				break;
			}
			case "SPAWN_SAILREN":
			{
				final L2RaidBossInstance sailren = (L2RaidBossInstance) addSpawn(SAILREN, 27549, -6638, -2008, 0, false, 0);
				final L2Npc movieNpc = addSpawn(MOVIE_NPC, sailren.getX(), sailren.getY(), sailren.getZ() + 30, 0, false, 26000);
				sailren.setIsInvul(true);
				sailren.setIsImmobilized(true);
				zone.broadcastPacket(new SpecialCamera(movieNpc, 60, 110, 30, 4000, 1500, 20000, 0, 65, 1, 0, 0));
				
				startQuestTimer("ATTACK", 24600, sailren, null);
				startQuestTimer("ANIMATION", 2000, movieNpc, null);
				startQuestTimer("CAMERA_1", 4100, movieNpc, null);
				break;
			}
			case "ANIMATION":
			{
				if (npc != null)
				{
					npc.setTarget(npc);
					npc.doCast(ANIMATION.getSkill());
					startQuestTimer("ANIMATION", 2000, npc, null);
				}
				break;
			}
			case "CAMERA_1":
			{
				zone.broadcastPacket(new SpecialCamera(npc, 100, 180, 30, 3000, 1500, 20000, 0, 50, 1, 0, 0));
				startQuestTimer("CAMERA_2", 3000, npc, null);
				break;
			}
			case "CAMERA_2":
			{
				zone.broadcastPacket(new SpecialCamera(npc, 150, 270, 25, 3000, 1500, 20000, 0, 30, 1, 0, 0));
				startQuestTimer("CAMERA_3", 3000, npc, null);
				break;
			}
			case "CAMERA_3":
			{
				zone.broadcastPacket(new SpecialCamera(npc, 160, 360, 20, 3000, 1500, 20000, 10, 15, 1, 0, 0));
				startQuestTimer("CAMERA_4", 3000, npc, null);
				break;
			}
			case "CAMERA_4":
			{
				zone.broadcastPacket(new SpecialCamera(npc, 160, 450, 10, 3000, 1500, 20000, 0, 10, 1, 0, 0));
				startQuestTimer("CAMERA_5", 3000, npc, null);
				break;
			}
			case "CAMERA_5":
			{
				zone.broadcastPacket(new SpecialCamera(npc, 160, 560, 0, 3000, 1500, 20000, 0, 10, 1, 0, 0));
				startQuestTimer("CAMERA_6", 7000, npc, null);
				break;
			}
			case "CAMERA_6":
			{
				zone.broadcastPacket(new SpecialCamera(npc, 70, 560, 0, 500, 1500, 7000, -15, 20, 1, 0, 0));
				break;
			}
			case "ATTACK":
			{
				npc.setIsInvul(false);
				npc.setIsImmobilized(false);
				break;
			}
			case "CLEAR_STATUS":
			{
				STATUS = Status.ALIVE;
				break;
			}
			case "TIME_OUT":
			{
				if (STATUS == Status.IN_FIGHT)
				{
					STATUS = Status.ALIVE;
				}
				for (L2Character charInside : zone.getCharactersInside())
				{
					if (charInside != null)
					{
						if (charInside.isPlayer())
						{
							charInside.teleToLocation(TeleportWhereType.TOWN);
						}
						else if (charInside.isNpc())
						{
							charInside.deleteMe();
						}
					}
				}
				break;
			}
			case "CHECK_ATTACK":
			{
				if (!zone.getPlayersInside().isEmpty() && ((_lastAttack + 600000) < System.currentTimeMillis()))
				{
					cancelQuestTimer("TIME_OUT", null, null);
					notifyEvent("TIME_OUT", null, null);
				}
				else
				{
					startQuestTimer("CHECK_ATTACK", 120000, null, null);
				}
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (zone.isCharacterInZone(attacker))
		{
			_lastAttack = System.currentTimeMillis();
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (zone.isCharacterInZone(killer))
		{
			switch (npc.getId())
			{
				case SAILREN:
				{
					STATUS = Status.DEAD;
					addSpawn(CUBIC, 27644, -6638, -2008, 0, false, 300000);
					final long respawnTime = RESPAWN * 3600000;
					GlobalVariablesManager.getInstance().set("SailrenRespawn", System.currentTimeMillis() + respawnTime);
					cancelQuestTimer("CHECK_ATTACK", null, null);
					cancelQuestTimer("TIME_OUT", null, null);
					startQuestTimer("CLEAR_STATUS", respawnTime, null, null);
					startQuestTimer("TIME_OUT", 300000, null, null);
					break;
				}
				case VELOCIRAPTOR:
				{
					_killCount++;
					if (_killCount == 3)
					{
						final L2Npc pterosaur = addSpawn(PTEROSAUR, 27313, -6766, -1975, 0, false, 0);
						addAttackDesire(pterosaur, killer);
						_killCount = 0;
					}
					break;
				}
				case PTEROSAUR:
				{
					final L2Npc trex = addSpawn(TREX, 27313, -6766, -1975, 0, false, 0);
					addAttackDesire(trex, killer);
					break;
				}
				case TREX:
				{
					startQuestTimer("SPAWN_SAILREN", 180000, null, null);
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public boolean unload(boolean removeFromList)
	{
		if (STATUS == Status.IN_FIGHT)
		{
			LOGGER.info(getClass().getSimpleName() + ": Script is being unloaded while Sailren is active, clearing zone.");
			notifyEvent("TIME_OUT", null, null);
		}
		return super.unload(removeFromList);
	}
	
	public static void main(String[] args)
	{
		new Sailren();
	}
}

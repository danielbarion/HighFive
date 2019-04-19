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
package instances.SSQMonasteryOfSilence;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;

import instances.AbstractInstance;
import quests.Q10294_SevenSignsToTheMonasteryOfSilence.Q10294_SevenSignsToTheMonasteryOfSilence;
import quests.Q10295_SevenSignsSolinasTomb.Q10295_SevenSignsSolinasTomb;
import quests.Q10296_SevenSignsOneWhoSeeksThePowerOfTheSeal.Q10296_SevenSignsOneWhoSeeksThePowerOfTheSeal;

/**
 * Monastery of Silence instance zone.
 * @author Adry_85
 */
public final class SSQMonasteryOfSilence extends AbstractInstance
{
	// NPCs
	private static final int ELCADIA_INSTANCE = 32787;
	private static final int ERIS_EVIL_THOUGHTS = 32792;
	private static final int RELIC_GUARDIAN = 32803;
	private static final int RELIC_WATCHER1 = 32804;
	private static final int RELIC_WATCHER2 = 32805;
	private static final int RELIC_WATCHER3 = 32806;
	private static final int RELIC_WATCHER4 = 32807;
	private static final int ODD_GLOBE = 32815;
	private static final int TELEPORT_CONTROL_DEVICE1 = 32817;
	private static final int TELEPORT_CONTROL_DEVICE2 = 32818;
	private static final int TELEPORT_CONTROL_DEVICE3 = 32819;
	private static final int TELEPORT_CONTROL_DEVICE4 = 32820;
	private static final int TOMB_OF_THE_SAINTESS = 32843;
	// Monsters
	private static final int TRAINEE_OF_REST = 27403;
	private static final int SUPPLICANT_OF_REST = 27404;
	private static final int ETIS_VAN_ETINA = 18949;
	private static final int SOLINAS_GUARDIAN_1 = 18952;
	private static final int SOLINAS_GUARDIAN_2 = 18953;
	private static final int SOLINAS_GUARDIAN_3 = 18954;
	private static final int SOLINAS_GUARDIAN_4 = 18955;
	private static final int GUARDIAN_OF_THE_TOMB_1 = 18956;
	private static final int GUARDIAN_OF_THE_TOMB_2 = 18957;
	private static final int GUARDIAN_OF_THE_TOMB_3 = 18958;
	private static final int GUARDIAN_OF_THE_TOMB_4 = 18959;
	// Items
	private static final int SCROLL_OF_ABSTINENCE = 17228;
	private static final int SHIELD_OF_SACRIFICE = 17229;
	private static final int SWORD_OF_HOLY_SPIRIT = 17230;
	private static final int STAFF_OF_BLESSING = 17231;
	// Skills
	private static final SkillHolder[] BUFFS =
	{
		new SkillHolder(6725, 1), // Bless the Blood of Elcadia
		new SkillHolder(6728, 1), // Recharge of Elcadia
		new SkillHolder(6730, 1), // Greater Battle Heal of Elcadia
	};
	// Locations
	private static final Location START_LOC = new Location(120717, -86879, -3424);
	private static final Location EXIT_LOC = new Location(115983, -87351, -3397);
	private static final Location CENTRAL_ROOM_LOC = new Location(85794, -249788, -8320);
	private static final Location SOUTH_WATCHERS_ROOM_LOC = new Location(85798, -246566, -8320);
	private static final Location WEST_WATCHERS_ROOM_LOC = new Location(82531, -249405, -8320);
	private static final Location EAST_WATCHERS_ROOM_LOC = new Location(88665, -249784, -8320);
	private static final Location NORTH_WATCHERS_ROOM_LOC = new Location(85792, -252336, -8320);
	private static final Location BACK_LOC = new Location(120710, -86971, -3392);
	private static final Location START_LOC_Q10295 = new Location(45545, -249423, -6788);
	private static final Location CASKET_ROOM_LOC = new Location(56033, -252944, -6792);
	private static final Location SOLINAS_RESTING_PLACE_LOC = new Location(55955, -250394, -6792);
	private static final Location DIRECTORS_ROOM_LOC = new Location(120717, -86879, -3424);
	private static final Location GUARDIAN_OF_THE_TOMB_1_LOC = new Location(55498, -252781, -6752, 0);
	private static final Location GUARDIAN_OF_THE_TOMB_2_LOC = new Location(55520, -252160, -6752, 0);
	private static final Location GUARDIAN_OF_THE_TOMB_3_LOC = new Location(56635, -252776, -6752, -32180);
	private static final Location GUARDIAN_OF_THE_TOMB_4_LOC = new Location(56672, -252156, -6754, 32252);
	private static final Location SOLINAS_GUARDIAN_1_LOC = new Location(45399, -253051, -6765, 16584);
	private static final Location SOLINAS_GUARDIAN_2_LOC = new Location(48736, -249632, -6768, -32628);
	private static final Location SOLINAS_GUARDIAN_3_LOC = new Location(45392, -246303, -6768, -16268);
	private static final Location SOLINAS_GUARDIAN_4_LOC = new Location(42016, -249648, -6764, 0);
	private static final Location ELCADIA_LOC = new Location(115927, -87005, -3392);
	private static final Location SPACE_LOC = new Location(76736, -241021, -10780);
	private static final Location ETIS_VAN_ETINA_LOC = new Location(76625, -240824, -10832, 0);
	private static final Location[] SLAVE_SPAWN_1_LOC =
	{
		new Location(55680, -252832, -6752),
		new Location(55825, -252792, -6752),
		new Location(55687, -252718, -6752),
		new Location(55824, -252679, -6752),
	};
	private static final Location[] SLAVE_SPAWN_2_LOC =
	{
		new Location(55672, -252099, -6751),
		new Location(55810, -252262, -6752),
		new Location(55824, -252112, -6752),
		new Location(55669, -252227, -6752),
	};
	private static final Location[] SLAVE_SPAWN_3_LOC =
	{
		new Location(56480, -252833, -6751),
		new Location(56481, -252725, -6752),
		new Location(56368, -252787, -6752),
		new Location(56368, -252669, -6752),
	};
	private static final Location[] SLAVE_SPAWN_4_LOC =
	{
		new Location(56463, -252225, -6751),
		new Location(56469, -252108, -6752),
		new Location(56336, -252168, -6752),
		new Location(56336, -252288, -6752),
	};
	// NpcString
	private static final NpcStringId[] ELCADIA_DIALOGS_Q010294 =
	{
		NpcStringId.WE_MUST_SEARCH_HIGH_AND_LOW_IN_EVERY_ROOM_FOR_THE_READING_DESK_THAT_CONTAINS_THE_BOOK_WE_SEEK,
		NpcStringId.REMEMBER_THE_CONTENT_OF_THE_BOOKS_THAT_YOU_FOUND_YOU_CAN_T_TAKE_THEM_OUT_WITH_YOU,
		NpcStringId.IT_SEEMS_THAT_YOU_CANNOT_REMEMBER_TO_THE_ROOM_OF_THE_WATCHER_WHO_FOUND_THE_BOOK
	};
	
	private static final NpcStringId[] ELCADIA_DIALOGS_Q010295 =
	{
		NpcStringId.THE_GUARDIAN_OF_THE_SEAL_DOESN_T_SEEM_TO_GET_INJURED_AT_ALL_UNTIL_THE_BARRIER_IS_DESTROYED,
		NpcStringId.THE_DEVICE_LOCATED_IN_THE_ROOM_IN_FRONT_OF_THE_GUARDIAN_OF_THE_SEAL_IS_DEFINITELY_THE_BARRIER_THAT_CONTROLS_THE_GUARDIAN_S_POWER,
		NpcStringId.TO_REMOVE_THE_BARRIER_YOU_MUST_FIND_THE_RELICS_THAT_FIT_THE_BARRIER_AND_ACTIVATE_THE_DEVICE
	};
	// Misc
	private static final int TEMPLATE_ID = 151;
	// Doors
	private static final int TOMB_DOOR = 21100018;
	private static final int[] DOORS =
	{
		21100014,
		21100001,
		21100006,
		21100010,
		21100003,
		21100008,
		21100012,
		21100016,
		21100002,
		21100015,
		21100005,
		21100004,
		21100009,
		21100007,
		21100013,
		21100011
	};
	
	private static final int[] FAKE_TOMB_DOORS =
	{
		21100101,
		21100102,
		21100103,
		21100104
	};
	
	private SSQMonasteryOfSilence()
	{
		addFirstTalkId(TELEPORT_CONTROL_DEVICE1, TELEPORT_CONTROL_DEVICE2, TELEPORT_CONTROL_DEVICE3, TELEPORT_CONTROL_DEVICE4);
		addKillId(SOLINAS_GUARDIAN_1, SOLINAS_GUARDIAN_2, SOLINAS_GUARDIAN_3, SOLINAS_GUARDIAN_4, GUARDIAN_OF_THE_TOMB_1, GUARDIAN_OF_THE_TOMB_2, GUARDIAN_OF_THE_TOMB_3, GUARDIAN_OF_THE_TOMB_4, ETIS_VAN_ETINA);
		addSpawnId(ERIS_EVIL_THOUGHTS, TOMB_OF_THE_SAINTESS);
		addStartNpc(ODD_GLOBE, TELEPORT_CONTROL_DEVICE1, TELEPORT_CONTROL_DEVICE2, TELEPORT_CONTROL_DEVICE3, TELEPORT_CONTROL_DEVICE4, ERIS_EVIL_THOUGHTS);
		addTalkId(ODD_GLOBE, ERIS_EVIL_THOUGHTS, RELIC_GUARDIAN, RELIC_WATCHER1, RELIC_WATCHER2, RELIC_WATCHER3, RELIC_WATCHER4, TELEPORT_CONTROL_DEVICE1, TELEPORT_CONTROL_DEVICE2, TELEPORT_CONTROL_DEVICE3, TELEPORT_CONTROL_DEVICE4, ERIS_EVIL_THOUGHTS);
	}
	
	@Override
	public void onEnterInstance(L2PcInstance player, InstanceWorld world, boolean firstEntrance)
	{
		if (firstEntrance)
		{
			world.addAllowed(player);
		}
		teleportPlayer(player, START_LOC, world.getInstanceId(), false);
		spawnElcadia(player, world);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			switch (event)
			{
				case "TELE2":
				{
					teleportPlayer(player, CENTRAL_ROOM_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(CENTRAL_ROOM_LOC, world.getInstanceId(), 0);
					startQuestTimer("START_MOVIE", 2000, npc, player);
					break;
				}
				case "EXIT":
				{
					cancelQuestTimer("FOLLOW", npc, player);
					cancelQuestTimer("DIALOG", npc, player);
					teleportPlayer(player, EXIT_LOC, 0);
					world.getParameters().getObject("elcadia", L2Npc.class).deleteMe();
					break;
				}
				case "START_MOVIE":
				{
					playMovie(player, Movie.SSQ2_HOLY_BURIAL_GROUND_OPENING);
					break;
				}
				case "BACK":
				{
					teleportPlayer(player, BACK_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(BACK_LOC, world.getInstanceId(), 0);
					break;
				}
				case "EAST":
				{
					teleportPlayer(player, EAST_WATCHERS_ROOM_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(EAST_WATCHERS_ROOM_LOC, world.getInstanceId(), 0);
					break;
				}
				case "WEST":
				{
					teleportPlayer(player, WEST_WATCHERS_ROOM_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(WEST_WATCHERS_ROOM_LOC, world.getInstanceId(), 0);
					break;
				}
				case "NORTH":
				{
					teleportPlayer(player, NORTH_WATCHERS_ROOM_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(NORTH_WATCHERS_ROOM_LOC, world.getInstanceId(), 0);
					break;
				}
				case "SOUTH":
				{
					teleportPlayer(player, SOUTH_WATCHERS_ROOM_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(SOUTH_WATCHERS_ROOM_LOC, world.getInstanceId(), 0);
					break;
				}
				case "CENTER":
				{
					teleportPlayer(player, CENTRAL_ROOM_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(CENTRAL_ROOM_LOC, world.getInstanceId(), 0);
					break;
				}
				case "FOLLOW":
				{
					npc.setRunning();
					npc.getAI().startFollow(player);
					if (player.isInCombat())
					{
						npc.doCast(BUFFS[getRandom(BUFFS.length)].getSkill());
					}
					startQuestTimer("FOLLOW", 5000, npc, player);
					break;
				}
				case "DIALOG":
				{
					final QuestState st_Q10294 = player.getQuestState(Q10294_SevenSignsToTheMonasteryOfSilence.class.getSimpleName());
					final QuestState st_Q10295 = player.getQuestState(Q10295_SevenSignsSolinasTomb.class.getSimpleName());
					if ((st_Q10294 != null) && st_Q10294.isStarted())
					{
						npc.broadcastSay(ChatType.NPC_GENERAL, ELCADIA_DIALOGS_Q010294[getRandom(ELCADIA_DIALOGS_Q010294.length)]);
					}
					if ((st_Q10295 != null) && st_Q10295.isMemoState(1))
					{
						npc.broadcastSay(ChatType.NPC_GENERAL, ELCADIA_DIALOGS_Q010295[getRandom(ELCADIA_DIALOGS_Q010295.length)]);
					}
					startQuestTimer("DIALOG", 10000, npc, player);
					break;
				}
				case "ENTER_Q10295":
				{
					teleportPlayer(player, START_LOC_Q10295, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(START_LOC_Q10295, world.getInstanceId(), 0);
					startQuestTimer("START_MOVIE_Q10295", 2000, npc, player);
					break;
				}
				case "START_MOVIE_Q10295":
				{
					playMovie(player, Movie.SSQ2_SOLINA_TOMB_OPENING);
					break;
				}
				case "CASKET_ROOM":
				{
					teleportPlayer(player, CASKET_ROOM_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(CASKET_ROOM_LOC, world.getInstanceId(), 0);
					break;
				}
				case "SOLINAS_RESTING_PLACE":
				{
					teleportPlayer(player, SOLINAS_RESTING_PLACE_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(SOLINAS_RESTING_PLACE_LOC, world.getInstanceId(), 0);
					break;
				}
				case "ERIS_OFFICE":
				{
					teleportPlayer(player, START_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(START_LOC, world.getInstanceId(), 0);
					break;
				}
				case "OPEN_DOORS":
				{
					for (int doorId : DOORS)
					{
						world.openDoor(doorId);
					}
					break;
				}
				case "DIRECTORS_ROOM":
				{
					teleportPlayer(player, DIRECTORS_ROOM_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(DIRECTORS_ROOM_LOC, world.getInstanceId(), 0);
					break;
				}
				case "USE_SCROLL":
				{
					// TODO (Adry_85): Missing area debuff
					if (hasQuestItems(player, SCROLL_OF_ABSTINENCE))
					{
						takeItems(player, SCROLL_OF_ABSTINENCE, 1);
						addSpawn(SOLINAS_GUARDIAN_1, SOLINAS_GUARDIAN_1_LOC, false, 0, false, world.getInstanceId());
					}
					break;
				}
				case "USE_SHIELD":
				{
					// TODO (Adry_85): Missing area debuff
					if (hasQuestItems(player, SHIELD_OF_SACRIFICE))
					{
						takeItems(player, SHIELD_OF_SACRIFICE, 1);
						addSpawn(SOLINAS_GUARDIAN_2, SOLINAS_GUARDIAN_2_LOC, false, 0, false, world.getInstanceId());
					}
					break;
				}
				case "USE_SWORD":
				{
					// TODO (Adry_85): Missing area debuff
					if (hasQuestItems(player, SWORD_OF_HOLY_SPIRIT))
					{
						takeItems(player, SWORD_OF_HOLY_SPIRIT, 1);
						addSpawn(SOLINAS_GUARDIAN_3, SOLINAS_GUARDIAN_3_LOC, false, 0, false, world.getInstanceId());
					}
					break;
				}
				case "USE_STAFF":
				{
					// TODO (Adry_85): Missing area debuff
					if (hasQuestItems(player, STAFF_OF_BLESSING))
					{
						takeItems(player, STAFF_OF_BLESSING, 1);
						addSpawn(SOLINAS_GUARDIAN_4, SOLINAS_GUARDIAN_4_LOC, false, 0, false, world.getInstanceId());
					}
					break;
				}
				case "CLOSE_TOMB_DOORS":
				{
					for (int doorId : FAKE_TOMB_DOORS)
					{
						world.closeDoor(doorId);
					}
					break;
				}
				case "TOMB_GUARDIAN_SPAWN":
				{
					for (int doorId : FAKE_TOMB_DOORS)
					{
						world.openDoor(doorId);
					}
					
					addSpawn(GUARDIAN_OF_THE_TOMB_1, GUARDIAN_OF_THE_TOMB_1_LOC, false, 0, false, world.getInstanceId());
					
					for (Location LOC : SLAVE_SPAWN_1_LOC)
					{
						L2Attackable mob = (L2Attackable) addSpawn(TRAINEE_OF_REST, LOC, false, 0, false, world.getInstanceId());
						mob.setRunning();
						mob.addDamageHate(player, 0, 999);
						mob.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
					}
					
					addSpawn(GUARDIAN_OF_THE_TOMB_2, GUARDIAN_OF_THE_TOMB_2_LOC, false, 0, false, world.getInstanceId());
					
					for (Location LOC : SLAVE_SPAWN_2_LOC)
					{
						L2Attackable mob = (L2Attackable) addSpawn(TRAINEE_OF_REST, LOC, false, 0, false, world.getInstanceId());
						mob.setRunning();
						mob.addDamageHate(player, 0, 999);
						mob.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
					}
					
					addSpawn(GUARDIAN_OF_THE_TOMB_3, GUARDIAN_OF_THE_TOMB_3_LOC, false, 0, false, world.getInstanceId());
					
					for (Location LOC : SLAVE_SPAWN_3_LOC)
					{
						L2Attackable mob = (L2Attackable) addSpawn(SUPPLICANT_OF_REST, LOC, false, 0, false, world.getInstanceId());
						mob.setRunning();
						mob.addDamageHate(player, 0, 999);
						mob.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
					}
					
					addSpawn(GUARDIAN_OF_THE_TOMB_4, GUARDIAN_OF_THE_TOMB_4_LOC, false, 0, false, world.getInstanceId());
					
					for (Location LOC : SLAVE_SPAWN_4_LOC)
					{
						L2Attackable mob = (L2Attackable) addSpawn(SUPPLICANT_OF_REST, LOC, false, 0, false, world.getInstanceId());
						mob.setRunning();
						mob.addDamageHate(player, 0, 999);
						mob.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
					}
					return "32843-01.html";
				}
				case "START_MOVIE_Q10296":
				{
					playMovie(player, Movie.SSQ2_BOSS_OPENING);
					startQuestTimer("TELEPORT_SPACE", 60000, npc, player);
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(ELCADIA_LOC, world.getInstanceId(), 0);
					break;
				}
				case "TELEPORT_SPACE":
				{
					teleportPlayer(player, SPACE_LOC, world.getInstanceId());
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(SPACE_LOC, world.getInstanceId(), 0);
					addSpawn(ETIS_VAN_ETINA, ETIS_VAN_ETINA_LOC, false, 0, false, world.getInstanceId());
					break;
				}
				case "TELEPORT_TO_PLAYER":
				{
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(player.getX(), player.getY(), player.getZ(), 0, world.getInstanceId());
					break;
				}
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		InstanceWorld world = InstanceManager.getInstance().getWorld(npc);
		if (world != null)
		{
			switch (npc.getId())
			{
				case GUARDIAN_OF_THE_TOMB_1:
				case GUARDIAN_OF_THE_TOMB_2:
				case GUARDIAN_OF_THE_TOMB_3:
				case GUARDIAN_OF_THE_TOMB_4:
				{
					final int deadTombGuardianCount = world.getParameters().getInt("deadTombGuardianCount", 0) + 1;
					world.setParameter("deadTombGuardianCount", deadTombGuardianCount);
					if (deadTombGuardianCount == 4)
					{
						world.openDoor(TOMB_DOOR);
						final QuestState st = player.getQuestState(Q10295_SevenSignsSolinasTomb.class.getSimpleName());
						if ((st != null) && st.isMemoState(2))
						{
							st.setMemoState(3);
						}
					}
					break;
				}
				case SOLINAS_GUARDIAN_1:
				case SOLINAS_GUARDIAN_2:
				case SOLINAS_GUARDIAN_3:
				case SOLINAS_GUARDIAN_4:
				{
					final int deadSolinaGuardianCount = world.getParameters().getInt("deadSolinaGuardianCount", 0) + 1;
					world.setParameter("deadSolinaGuardianCount", deadSolinaGuardianCount);
					if (deadSolinaGuardianCount == 4)
					{
						playMovie(player, Movie.SSQ2_SOLINA_TOMB_CLOSING);
						final QuestState st = player.getQuestState(Q10295_SevenSignsSolinasTomb.class.getSimpleName());
						if ((st != null) && st.isMemoState(1))
						{
							st.setMemoState(2);
						}
					}
					break;
				}
				case ETIS_VAN_ETINA:
				{
					playMovie(player, Movie.SSQ2_BOSS_CLOSING);
					world.getParameters().getObject("elcadia", L2Npc.class).teleToLocation(ELCADIA_LOC, world.getInstanceId(), 0);
					startQuestTimer("TELEPORT_TO_PLAYER", 63000, npc, player);
					final QuestState st = player.getQuestState(Q10296_SevenSignsOneWhoSeeksThePowerOfTheSeal.class.getSimpleName());
					if ((st != null) && st.isMemoState(2))
					{
						st.setMemoState(3);
					}
					break;
				}
			}
			
		}
		return null;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		switch (npc.getId())
		{
			case ERIS_EVIL_THOUGHTS:
			{
				startQuestTimer("OPEN_DOORS", 1000, npc, null);
				break;
			}
			case TOMB_OF_THE_SAINTESS:
			{
				startQuestTimer("CLOSE_TOMB_DOORS", 1000, npc, null);
				break;
			}
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		if (npc.getId() == ODD_GLOBE)
		{
			enterInstance(talker, TEMPLATE_ID);
		}
		return super.onTalk(npc, talker);
	}
	
	protected void spawnElcadia(L2PcInstance player, InstanceWorld world)
	{
		if (world.getParameters().getObject("elcadia", L2Npc.class) != null)
		{
			world.getParameters().getObject("elcadia", L2Npc.class).deleteMe();
		}
		final L2Npc elcadia = addSpawn(ELCADIA_INSTANCE, player.getX(), player.getY(), player.getZ(), 0, false, 0, false, world.getInstanceId());
		world.setParameter("elcadia", elcadia);
		startQuestTimer("FOLLOW", 5000, elcadia, player);
		startQuestTimer("DIALOG", 10000, elcadia, player);
	}
	
	public static void main(String[] args)
	{
		new SSQMonasteryOfSilence();
	}
}

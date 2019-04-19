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
package instances.CrystalCaverns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.InstanceReenterType;
import com.l2jmobius.gameserver.enums.TrapAction;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.PcCondOverride;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2TrapInstance;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.skills.targets.L2TargetType;
import com.l2jmobius.gameserver.model.zone.L2ZoneType;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import com.l2jmobius.gameserver.network.serverpackets.CreatureSay;
import com.l2jmobius.gameserver.network.serverpackets.FlyToLocation;
import com.l2jmobius.gameserver.network.serverpackets.FlyToLocation.FlyType;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jmobius.gameserver.network.serverpackets.PlaySound;
import com.l2jmobius.gameserver.network.serverpackets.SpecialCamera;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.network.serverpackets.ValidateLocation;
import com.l2jmobius.gameserver.util.Util;

import instances.AbstractInstance;
import quests.Q00131_BirdInACage.Q00131_BirdInACage;

/**
 * Crystal Caverns instance zone.<br>
 * TODO: 1. Kechi's Henchmans spawn animation is missing.<br>
 * 2. NPC related Traps are not supported by core, so Darnels and Lahm door trap is not working.<br>
 * 3. Need retail spawn for Coral Garden (EmeraldSteam/Square - done).<br>
 * 4. Baylor Raid is missing a lot of things This script takes the best elements of different versions and combines them into one script to get the most optimal and retail-like experience.<br>
 * Original sources: theone, L2JEmu, L2JOfficial, L2JFree Contributing authors: TGS, Lantoc, Janiii, Gigiikun, RosT Please maintain consistency between the Crystal Caverns scripts.
 */
public final class CrystalCaverns extends AbstractInstance
{
	protected static class CrystalGolem
	{
		protected L2ItemInstance foodItem = null;
		protected boolean isAtDestination = false;
		protected Location oldLoc = null;
	}
	
	private class CCWorld extends InstanceWorld
	{
		public Map<L2Npc, Boolean> npcList1 = new HashMap<>();
		public L2Npc tears;
		public boolean isUsedInvulSkill = false;
		public long dragonScaleStart = 0;
		public int dragonScaleNeed = 0;
		public int cleanedRooms = 0;
		public long endTime = 0;
		public List<L2Npc> copys = new ArrayList<>();
		public Map<L2Npc, CrystalGolem> crystalGolems = new HashMap<>();
		public int correctGolems = 0;
		public boolean[] OracleTriggered =
		{
			false,
			false,
			false
		};
		public int kechisHenchmanSpawn = 0;
		public int[] roomsStatus =
		{
			0,
			0,
			0,
			0
		}; // 0: not spawned, 1: spawned, 2: cleared
		public Map<L2DoorInstance, L2PcInstance> openedDoors = new ConcurrentHashMap<>();
		public Map<Integer, Map<L2Npc, Boolean>> npcList2 = new HashMap<>();
		public Map<L2Npc, L2Npc> oracles = new HashMap<>();
		public List<L2Npc> keyKeepers = new ArrayList<>();
		public List<L2Npc> guards = new ArrayList<>();
		public List<L2Npc> oracle = new ArrayList<>();
		// baylor variables
		protected final List<L2PcInstance> _raiders = new ArrayList<>();
		protected int _raidStatus = 0;
		protected long _dragonClawStart = 0;
		protected int _dragonClawNeed = 0;
		protected final List<L2Npc> _animationMobs = new ArrayList<>();
		protected L2Npc _camera = null;
		protected L2Npc _baylor = null;
		protected L2Npc _alarm = null;
		
		public CCWorld(Long time)
		{
			endTime = time;
		}
	}
	
	// Items
	private static final int WHITE_SEED = 9597;
	private static final int BLACK_SEED = 9598;
	private static final int CONT_CRYSTAL = 9690; // Contaminated Crystal
	private static final int RED_CORAL = 9692; // Red Coral
	private static final int CRYSTALFOOD = 9693; // Food item for Crystal Golems
	private static final int RACE_KEY = 9694; // Race Key for Emerald doors
	private static final int BOSS_CRYSTAL_1 = 9695; // Clear Crystal
	private static final int BOSS_CRYSTAL_2 = 9696; // Clear Crystal
	private static final int BOSS_CRYSTAL_3 = 9697; // Clear Crystal
	// NPCs
	private static final int ORACLE_GUIDE_1 = 32281;
	private static final int ORACLE_GUIDE_2 = 32278;
	private static final int ORACLE_GUIDE_3 = 32280;
	private static final int ORACLE_GUIDE_4 = 32279;
	private static final int CRYSTAL_GOLEM = 32328;
	private static final int[] DOOR_OPENING_TRAP =
	{
		18378,
		143682,
		142492,
		-11886,
		16384
	};
	private static final int GK1 = 22275;
	private static final int GK2 = 22277;
	private static final int TOURMALINE = 22292;
	private static final int TEROD = 22301;
	private static final int DOLPH = 22299;
	private static final int WEYLIN = 22298;
	private static final int GUARDIAN = 22303;
	private static final int GUARDIAN2 = 22304;
	private static final int TEARS = 25534;
	private static final int TEARS_COPY = 25535;
	private static final int KECHI = 25532;
	private static final int KECHIGUARD = 25533;
	private static final int BAYLOR = 29099;
	private static final int DARNEL = 25531;
	private static final int ALARMID = 18474;
	// private static final int[] BOSSCR = {9695,9696,9697};
	private static final int[] CGMOBS =
	{
		22311,
		22312,
		22313,
		22314,
		22315,
		22316,
		22317
	};
	private static final int[] SPAWN =
	{
		60000,
		120000,
		90000,
		60000,
		50000,
		40000
	}; // Kechi Hencmans spawn times
	private static final int[] MOBLIST =
	{
		22279,
		22280,
		22281,
		22282,
		22283,
		22285,
		22286,
		22287,
		22288,
		22289,
		22293,
		22294,
		22295,
		22296,
		22297,
		22305,
		22306,
		22307,
		22416,
		22418,
		22419,
		22420
	};
	
	// Locations
	private static final Location START_LOC = new Location(143348, 148707, -11972);
	// Misc
	private static final int TEMPLATE_ID = 10;
	private static final boolean debug = false;
	private static final int DOOR1 = 24220021;
	private static final int DOOR2 = 24220024;
	private static final int DOOR3 = 24220023;
	private static final int DOOR4 = 24220061;
	private static final int DOOR5 = 24220025;
	private static final int DOOR6 = 24220022;
	private static final int[] ZONES =
	{
		20105,
		20106,
		20107
	};
	// @formatter:off
	private static final int[][] ALARMSPAWN =
	{
		{153572, 141277, -12738},
		{153572, 142852, -12738},
		{154358, 142075, -12738},
		{152788, 142075, -12738}
	};
	// Oracle order
	private static final int[][] ordreOracle1 =
	{
		{32274, 147090, 152505, -12169, 31613},
		{32275, 147090, 152575, -12169, 31613},
		{32274, 147090, 152645, -12169, 31613},
		{32274, 147090, 152715, -12169, 31613}
	};
	
	private static final int[][] ordreOracle2 =
	{
		{32274, 149783, 152505, -12169, 31613},
		// {32274, 149783, 152575, -12169, 31613},
		{32274, 149783, 152645, -12169, 31613},
		{32276, 149783, 152715, -12169, 31613}
	};
	
	private static final int[][] ordreOracle3 =
	{
		{32274, 152461, 152505, -12169, 31613},
		// {32274, 152461, 152575, -12169, 31613},
		{32277, 152461, 152645, -12169, 31613},
		// {32274, 152461, 152715, -12169, 31613}
	};
	private static int[][] HALL_SPAWNS =
	{
		{141842, 152556, -11814, 50449},
		{141503, 153395, -11814, 40738},
		{141070, 153201, -11814, 39292},
		{141371, 152986, -11814, 35575},
		{141602, 154188, -11814, 24575},
		{141382, 154719, -11814, 37640},
		{141376, 154359, -11814, 12054},
		{140895, 154383, -11814, 37508},
		{140972, 154740, -11814, 52690},
		{141045, 154504, -11814, 50674},
		{140757, 152740, -11814, 39463},
		{140406, 152376, -11814, 16599},
		{140268, 152007, -11817, 45316},
		{139996, 151485, -11814, 47403},
		{140378, 151190, -11814, 58116},
		{140521, 150711, -11815, 55997},
		{140816, 150215, -11814, 53682},
		{141528, 149909, -11814, 22020},
		{141644, 150360, -11817, 13283},
		{142048, 150695, -11815, 5929},
		{141852, 151065, -11817, 27071},
		{142408, 151211, -11815, 2402},
		{142481, 151762, -11815, 12876},
		{141929, 152193, -11815, 27511},
		{142083, 151791, -11814, 47176},
		{141435, 150402, -11814, 41798},
		{140390, 151199, -11814, 50069},
		{140557, 151849, -11814, 45293},
		{140964, 153445, -11814, 56672},
		{142851, 154109, -11814, 24920},
		{142379, 154725, -11814, 30342},
		{142816, 154712, -11814, 33193},
		{142276, 154223, -11814, 33922},
		{142459, 154490, -11814, 33184},
		{142819, 154372, -11814, 21318},
		{141157, 154541, -11814, 27090},
		{141095, 150281, -11814, 55186}
	};
	
	// first spawns
	private static int[][] FIRST_SPAWNS =
	{
		{22276, 148109, 149601, -12132, 34490},
		{22276, 148017, 149529, -12132, 33689},
		{22278, 148065, 151202, -12132, 35323},
		{22278, 147966, 151117, -12132, 33234},
		{22279, 144063, 150238, -12132, 29654},
		{22279, 144300, 149118, -12135, 5520},
		{22279, 144397, 149337, -12132, 644},
		{22279, 144426, 150639, -12132, 50655},
		{22282, 145841, 151097, -12132, 31810},
		{22282, 144387, 149958, -12132, 61173},
		{22282, 145821, 149498, -12132, 31490},
		{22282, 146619, 149694, -12132, 33374},
		{22282, 146669, 149244, -12132, 31360},
		{22284, 144147, 151375, -12132, 58395},
		{22284, 144485, 151067, -12132, 64786},
		{22284, 144356, 149571, -12132, 63516},
		{22285, 144151, 150962, -12132, 664},
		{22285, 146657, 151365, -12132, 33154},
		{22285, 146623, 150857, -12132, 28034},
		{22285, 147046, 151089, -12132, 32941},
		{22285, 145704, 151255, -12132, 32523},
		{22285, 145359, 151101, -12132, 32767},
		{22285, 147785, 150817, -12132, 27423},
		{22285, 147727, 151375, -12132, 37117},
		{22285, 145428, 149494, -12132, 890},
		{22285, 145601, 149682, -12132, 32442},
		{22285, 147003, 149476, -12132, 31554},
		{22285, 147738, 149210, -12132, 20971},
		{22285, 147769, 149757, -12132, 34980}
	};
	
	// Emerald Square
	private static int[][] EMERALD_SPAWNS =
	{
		{22280, 144437, 143395, -11969, 34248},
		{22281, 149241, 143735, -12230, 24575},
		{22281, 147917, 146861, -12289, 60306},
		{22281, 144406, 147782, -12133, 14349},
		{22281, 144960, 146881, -12039, 23881},
		{22281, 144985, 147679, -12135, 27594},
		{22283, 147784, 143540, -12222, 2058},
		{22283, 149091, 143491, -12230, 24836},
		{22287, 144479, 147569, -12133, 20723},
		{22287, 145158, 146986, -12058, 21970},
		{22287, 145142, 147175, -12092, 24420},
		{22287, 145110, 147133, -12088, 22465},
		{22287, 144664, 146604, -12028, 14861},
		{22287, 144596, 146600, -12028, 14461},
		{22288, 143925, 146773, -12037, 10813},
		{22288, 144415, 147070, -12069, 8568},
		{22288, 143794, 145584, -12027, 14849},
		{22288, 143429, 146166, -12030, 4078},
		{22288, 144477, 147009, -12056, 8752},
		{22289, 142577, 145319, -12029, 5403},
		{22289, 143831, 146902, -12051, 9717},
		{22289, 143714, 146705, -12028, 10044},
		{22289, 143937, 147134, -12078, 7517},
		{22293, 143356, 145287, -12027, 8126},
		{22293, 143462, 144352, -12008, 25905},
		{22293, 143745, 142529, -11882, 17102},
		{22293, 144574, 144032, -12005, 34668},
		{22295, 143992, 142419, -11884, 19697},
		{22295, 144671, 143966, -12004, 32088},
		{22295, 144440, 143269, -11957, 34169},
		{22295, 142642, 146362, -12028, 281},
		{22295, 143865, 142707, -11881, 21326},
		{22295, 143573, 142530, -11879, 16141},
		{22295, 143148, 146039, -12031, 65014},
		{22295, 143001, 144853, -12014, 0},
		{22296, 147505, 146580, -12260, 59041},
		{22296, 149366, 146932, -12358, 39407},
		{22296, 149284, 147029, -12352, 41120},
		{22296, 149439, 143940, -12230, 23189},
		{22296, 147698, 143995, -12220, 27028},
		{22296, 141885, 144969, -12007, 2526},
		{22296, 147843, 143763, -12220, 28386},
		{22296, 144753, 143650, -11982, 35429},
		{22296, 147613, 146760, -12271, 56296}
	};
	
	private static int[][] ROOM1_SPAWNS =
	{
		{22288, 143114, 140027, -11888, 15025},
		{22288, 142173, 140973, -11888, 55698},
		{22289, 143210, 140577, -11888, 17164},
		{22289, 142638, 140107, -11888, 6571},
		{22297, 142547, 140938, -11888, 48556},
		{22298, 142690, 140479, -11887, 7663}
	};
	
	private static int[][] ROOM2_SPAWNS =
	{
		{22303, 146276, 141483, -11880, 34643},
		{22287, 145707, 142161, -11880, 28799},
		{22288, 146857, 142129, -11880, 33647},
		{22288, 146869, 142000, -11880, 31215},
		{22289, 146897, 140880, -11880, 19210}
	};
	
	private static int[][] ROOM3_SPAWNS =
	{
		{22302, 145123, 143713, -12808, 65323},
		{22294, 145188, 143331, -12808, 496},
		{22294, 145181, 144104, -12808, 64415},
		{22293, 144994, 143431, -12808, 65431},
		{22293, 144976, 143915, -12808, 61461}
	};
	
	private static int[][] ROOM4_SPAWNS =
	{
		{22304, 150563, 142240, -12108, 16454},
		{22294, 150769, 142495, -12108, 16870},
		{22281, 150783, 141995, -12108, 20033},
		{22283, 150273, 141983, -12108, 16043},
		{22294, 150276, 142492, -12108, 13540}
	};
	
	// Steam Corridor
	private static int[][] STEAM1_SPAWNS =
	{
		{22305, 145260, 152387, -12165, 32767},
		{22305, 144967, 152390, -12165, 30464},
		{22305, 145610, 152586, -12165, 17107},
		{22305, 145620, 152397, -12165, 8191},
		{22418, 146081, 152847, -12165, 31396},
		{22418, 146795, 152641, -12165, 33850},
		// {22308, 145093, 152502, -12165, 31841},
		// {22308, 146158, 152776, -12165, 30810},
		// {22308, 146116, 152976, -12133, 32571}
	
	};
	private static int[][] STEAM2_SPAWNS =
	{
		{22306, 147740, 152767, -12165, 65043},
		{22306, 148215, 152828, -12165, 970},
		{22306, 147743, 152846, -12165, 64147},
		// {22308, 147849, 152854, -12165, 60534},
		// {22308, 147754, 152908, -12141, 59827},
		// {22308, 148194, 152681, -12165, 63620},
		// {22308, 147767, 152939, -12133, 63381},
		// {22309, 147737, 152671, -12165, 65320},
		{22418, 148207, 152725, -12165, 61801},
		{22419, 149058, 152828, -12165, 64564}
	};
	
	private static int[][] STEAM3_SPAWNS =
	{
		{22307, 150735, 152316, -12145, 31930},
		{22307, 150725, 152467, -12165, 33635},
		{22307, 151058, 152316, -12146, 65342},
		{22307, 151057, 152461, -12165, 2171},
		// {22308, 150794, 152455, -12165, 31613},
		// {22308, 150665, 152383, -12165, 32767},
		// {22308, 151697, 152621, -12167, 31423},
		// {22309, 151061, 152581, -12165, 6228},
		// {22309, 150653, 152253, -12132, 31343},
		// {22309, 150628, 152431, -12165, 33022},
		// {22309, 151620, 152487, -12165, 30114},
		// {22309, 151672, 152544, -12165, 31846},
		// {22309, 150488, 152350, -12165, 29072},
		// {22310, 151139, 152238, -12132, 1069}
	};
	
	private static int[][] STEAM4_SPAWNS =
	{
		// {22308, 151707, 150199, -12165, 32859},
		// {22308, 152091, 150140, -12165, 32938},
		// {22308, 149757, 150204, -12138, 65331},
		// {22308, 149950, 150307, -12132, 62437},
		// {22308, 149901, 150322, -12132, 62136},
		// {22309, 150071, 150173, -12165, 64943},
		{22416, 151636, 150280, -12142, 36869},
		{22416, 149893, 150232, -12165, 64258},
		{22416, 149864, 150110, -12165, 65054},
		{22416, 151926, 150218, -12165, 31613},
		{22420, 149986, 150051, -12165, 105},
		{22420, 151970, 149997, -12165, 32170},
		{22420, 150744, 150006, -12165, 63},
		// {22417, 149782, 150188, -12151, 64001}
	};
	// @formatter:on
	private static final int DRAGONSCALETIME = 3000;
	private static final int DRAGONCLAWTIME = 3000;
	
	private CrystalCaverns()
	{
		addStartNpc(ORACLE_GUIDE_1, ORACLE_GUIDE_4);
		addTalkId(ORACLE_GUIDE_1, ORACLE_GUIDE_3, ORACLE_GUIDE_4, 32275, 32276, 32277);
		addFirstTalkId(ORACLE_GUIDE_1, ORACLE_GUIDE_2, ORACLE_GUIDE_4, CRYSTAL_GOLEM, 32274, 32275, 32276, 32277);
		addKillId(TEARS, GK1, GK2, TEROD, WEYLIN, DOLPH, DARNEL, KECHI, GUARDIAN, GUARDIAN2, TOURMALINE, BAYLOR, ALARMID);
		addSkillSeeId(BAYLOR, 25534, 32275, 32276, 32277);
		addTrapActionId(DOOR_OPENING_TRAP[0]);
		addSpellFinishedId(BAYLOR);
		addAttackId(TEARS);
		addKillId(MOBLIST);
		addKillId(CGMOBS);
		for (int zones : ZONES)
		{
			addEnterZoneId(zones);
			addExitZoneId(zones);
		}
	}
	
	@Override
	protected boolean checkConditions(L2PcInstance player)
	{
		if (debug || player.canOverrideCond(PcCondOverride.INSTANCE_CONDITIONS))
		{
			if (debug)
			{
				return true;
			}
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
		for (L2PcInstance partyMember : party.getMembers())
		{
			if (partyMember.getLevel() < 78)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY);
				sm.addPcName(partyMember);
				party.broadcastPacket(sm);
				return false;
			}
			final L2ItemInstance item = partyMember.getInventory().getItemByItemId(CONT_CRYSTAL);
			if (item == null)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_ITEM_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED);
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
			final Long reentertime = InstanceManager.getInstance().getInstanceTime(partyMember.getObjectId(), TEMPLATE_ID);
			if (System.currentTimeMillis() < reentertime)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_MAY_NOT_RE_ENTER_YET);
				sm.addPcName(partyMember);
				party.broadcastPacket(sm);
				return false;
			}
		}
		return true;
	}
	
	private boolean checkOracleConditions(L2PcInstance player)
	{
		if (debug)
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
		for (L2PcInstance partyMember : party.getMembers())
		{
			final L2ItemInstance item = partyMember.getInventory().getItemByItemId(RED_CORAL);
			if (item == null)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_ITEM_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED);
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
		}
		return true;
	}
	
	private boolean checkBaylorConditions(L2PcInstance player)
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
		for (L2PcInstance partyMember : party.getMembers())
		{
			final L2ItemInstance item1 = partyMember.getInventory().getItemByItemId(BOSS_CRYSTAL_1);
			final L2ItemInstance item2 = partyMember.getInventory().getItemByItemId(BOSS_CRYSTAL_2);
			final L2ItemInstance item3 = partyMember.getInventory().getItemByItemId(BOSS_CRYSTAL_3);
			if ((item1 == null) || (item2 == null) || (item3 == null))
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_ITEM_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED);
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
		}
		return true;
	}
	
	// this should be handled from skill effect
	private void Throw(L2Character effector, L2Character effected)
	{
		// Get current position of the L2Character
		final int curX = effected.getX();
		final int curY = effected.getY();
		final int curZ = effected.getZ();
		
		// Calculate distance between effector and effected current position
		final double dx = effector.getX() - curX;
		final double dy = effector.getY() - curY;
		final double dz = effector.getZ() - curZ;
		final double distance = Math.sqrt((dx * dx) + (dy * dy));
		int offset = Math.min((int) distance + 300, 1400);
		
		double cos;
		double sin;
		
		// approximation for moving futher when z coordinates are different
		// TODO: handle Z axis movement better
		offset += Math.abs(dz);
		if (offset < 5)
		{
			offset = 5;
		}
		
		if (distance < 1)
		{
			return;
		}
		// Calculate movement angles needed
		sin = dy / distance;
		cos = dx / distance;
		
		// Calculate the new destination with offset included
		final int _x = effector.getX() - (int) (offset * cos);
		final int _y = effector.getY() - (int) (offset * sin);
		final int _z = effected.getZ();
		
		final Location destination = GeoEngine.getInstance().canMoveToTargetLoc(effected.getX(), effected.getY(), effected.getZ(), _x, _y, _z, effected.getInstanceId());
		
		effected.broadcastPacket(new FlyToLocation(effected, destination, FlyType.THROW_UP));
		
		// maybe is need force set X,Y,Z
		effected.setXYZ(destination);
		effected.broadcastPacket(new ValidateLocation(effected));
	}
	
	@Override
	public void onEnterInstance(L2PcInstance player, InstanceWorld world, boolean firstEntrance)
	{
		if (firstEntrance)
		{
			if (player.getParty() == null)
			{
				// this can happen only if debug is true
				player.sendMessage("Welcome to Crystal Caverns.");
				teleportPlayer(player, START_LOC, world.getInstanceId());
				world.addAllowed(player);
			}
			else
			{
				for (L2PcInstance partyMember : player.getParty().getMembers())
				{
					partyMember.sendMessage("Welcome to Crystal Caverns.");
					teleportPlayer(partyMember, START_LOC, world.getInstanceId());
					world.addAllowed(partyMember);
				}
			}
			runOracle((CCWorld) world);
		}
		else
		{
			teleportPlayer(player, START_LOC, world.getInstanceId());
		}
	}
	
	protected void stopAttack(L2PcInstance player)
	{
		player.setTarget(null);
		player.abortAttack();
		player.abortCast();
		player.breakAttack();
		player.breakCast();
		player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		final L2Summon pet = player.getSummon();
		if (pet != null)
		{
			pet.setTarget(null);
			pet.abortAttack();
			pet.abortCast();
			pet.breakAttack();
			pet.breakCast();
			pet.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		}
	}
	
	protected void runOracle(CCWorld world)
	{
		world.setStatus(0);
		
		world.oracle.add(addSpawn(ORACLE_GUIDE_1, 143172, 148894, -11975, 0, false, 0, false, world.getInstanceId()));
	}
	
	protected void runEmerald(CCWorld world)
	{
		world.setStatus(1);
		runFirst(world);
		world.openDoor(DOOR1);
	}
	
	protected void runCoral(CCWorld world)
	{
		world.setStatus(1);
		runHall(world);
		world.openDoor(DOOR2);
		world.openDoor(DOOR5);
	}
	
	protected void runHall(CCWorld world)
	{
		world.setStatus(2);
		
		for (int[] spawn : HALL_SPAWNS)
		{
			final L2Npc mob = addSpawn(CGMOBS[getRandom(CGMOBS.length)], spawn[0], spawn[1], spawn[2], spawn[3], false, 0, false, world.getInstanceId());
			world.npcList1.put(mob, false);
		}
	}
	
	protected void runFirst(CCWorld world)
	{
		world.setStatus(2);
		
		world.keyKeepers.add(addSpawn(GK1, 148206, 149486, -12140, 32308, false, 0, false, world.getInstanceId()));
		world.keyKeepers.add(addSpawn(GK2, 148203, 151093, -12140, 31100, false, 0, false, world.getInstanceId()));
		
		for (int[] spawn : FIRST_SPAWNS)
		{
			addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.getInstanceId());
		}
	}
	
	protected void runEmeraldSquare(CCWorld world)
	{
		world.setStatus(3);
		
		final Map<L2Npc, Boolean> spawnList = new HashMap<>();
		for (int[] spawn : EMERALD_SPAWNS)
		{
			final L2Npc mob = addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.getInstanceId());
			spawnList.put(mob, false);
		}
		world.npcList2.put(0, spawnList);
	}
	
	protected void runEmeraldRooms(CCWorld world, int[][] spawnList, int room)
	{
		final Map<L2Npc, Boolean> spawned = new HashMap<>();
		for (int[] spawn : spawnList)
		{
			final L2Npc mob = addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.getInstanceId());
			spawned.put(mob, false);
		}
		if (room == 1)
		{
			addSpawn(32359, 142110, 139896, -11888, 8033, false, 0, false, world.getInstanceId());
		}
		world.npcList2.put(room, spawned);
		world.roomsStatus[room - 1] = 1;
	}
	
	protected void runDarnel(CCWorld world)
	{
		world.setStatus(9);
		
		addSpawn(DARNEL, 152759, 145949, -12588, 21592, false, 0, false, world.getInstanceId());
		// TODO: missing traps
		world.openDoor(24220005);
		world.openDoor(24220006);
	}
	
	protected void runSteamRooms(CCWorld world, int[][] spawnList, int status)
	{
		world.setStatus(status);
		
		final Map<L2Npc, Boolean> spawned = new HashMap<>();
		for (int[] spawn : spawnList)
		{
			final L2Npc mob = addSpawn(spawn[0], spawn[1], spawn[2], spawn[3], spawn[4], false, 0, false, world.getInstanceId());
			spawned.put(mob, false);
		}
		world.npcList2.put(0, spawned);
	}
	
	protected void runSteamOracles(CCWorld world, int[][] oracleOrder)
	{
		world.oracles.clear();
		for (int[] oracle : oracleOrder)
		{
			world.oracles.put(addSpawn(oracle[0], oracle[1], oracle[2], oracle[3], oracle[4], false, 0, false, world.getInstanceId()), null);
		}
	}
	
	protected boolean checkKillProgress(int room, L2Npc mob, CCWorld world)
	{
		if (world.npcList2.get(room).containsKey(mob))
		{
			world.npcList2.get(room).put(mob, true);
		}
		for (boolean isDead : world.npcList2.get(room).values())
		{
			if (!isDead)
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (npc.getId() == ORACLE_GUIDE_1)
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof CCWorld)
			{
				final CCWorld world = (CCWorld) tmpworld;
				if ((world.getStatus() == 0) && world.oracle.contains(npc))
				{
					return "32281.htm";// TODO: Missing HTML.
				}
			}
			npc.showChatWindow(player);
			return null;
		}
		else if ((npc.getId() >= 32275) && (npc.getId() <= 32277))
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof CCWorld)
			{
				final CCWorld world = (CCWorld) tmpworld;
				if (!world.OracleTriggered[npc.getId() - 32275])
				{
					return "no.htm"; // TODO: Missing HTML.
				}
				npc.showChatWindow(player);
				return null;
			}
		}
		else if (npc.getId() == 32274)
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof CCWorld)
			{
				return "no.htm"; // TODO: Missing HTML.
			}
		}
		else if (npc.getId() == 32279)
		{
			final QuestState st = player.getQuestState(Q00131_BirdInACage.class.getSimpleName());
			return (st != null) && !st.isCompleted() ? "32279-01.htm" : "32279.htm";
		}
		else if (npc.getId() == CRYSTAL_GOLEM)
		{
			player.sendPacket(ActionFailed.STATIC_PACKET);
		}
		return "";
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isSummon)
	{
		
		boolean doReturn = true;
		for (L2Object obj : targets)
		{
			if (obj == npc)
			{
				doReturn = false;
			}
		}
		if (doReturn)
		{
			return super.onSkillSee(npc, caster, skill, targets, isSummon);
		}
		
		switch (skill.getId())
		{
			case 1011:
			case 1015:
			case 1217:
			case 1218:
			case 1401:
			case 2360:
			case 2369:
			case 5146:
			{
				doReturn = false;
				break;
			}
			default:
			{
				doReturn = true;
			}
		}
		if (doReturn)
		{
			return super.onSkillSee(npc, caster, skill, targets, isSummon);
		}
		
		if ((npc.getId() >= 32275) && (npc.getId() <= 32277) && (skill.getId() != 2360) && (skill.getId() != 2369))
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if ((tmpworld instanceof CCWorld) && (getRandom(100) < 15))
			{
				for (L2Npc oracle : ((CCWorld) tmpworld).oracles.keySet())
				{
					if (oracle != npc)
					{
						oracle.decayMe();
					}
				}
				((CCWorld) tmpworld).OracleTriggered[npc.getId() - 32275] = true;
			}
		}
		else if (npc.isInvul() && (npc.getId() == BAYLOR) && (skill.getId() == 2360) && (caster != null))
		{
			if (caster.getParty() == null)
			{
				return super.onSkillSee(npc, caster, skill, targets, isSummon);
			}
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof CCWorld)
			{
				final CCWorld world = (CCWorld) tmpworld;
				
				if (((world._dragonClawStart + DRAGONCLAWTIME) <= System.currentTimeMillis()) || (world._dragonClawNeed <= 0))
				{
					world._dragonClawStart = System.currentTimeMillis();
					world._dragonClawNeed = caster.getParty().getMemberCount() - 1;
				}
				else
				{
					world._dragonClawNeed--;
				}
				if (world._dragonClawNeed == 0)
				{
					npc.stopSkillEffects(false, 5225);
					npc.broadcastPacket(new MagicSkillUse(npc, npc, 5480, 1, 4000, 0));
					if (world._raidStatus == 3)
					{
						world._raidStatus++;
					}
				}
			}
		}
		else if (npc.isInvul() && (npc.getId() == TEARS) && (skill.getId() == 2369) && (caster != null))
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof CCWorld)
			{
				final CCWorld world = (CCWorld) tmpworld;
				if (caster.getParty() == null)
				{
					return super.onSkillSee(npc, caster, skill, targets, isSummon);
				}
				else if (((world.dragonScaleStart + DRAGONSCALETIME) <= System.currentTimeMillis()) || (world.dragonScaleNeed <= 0))
				{
					world.dragonScaleStart = System.currentTimeMillis();
					world.dragonScaleNeed = caster.getParty().getMemberCount() - 1;
				}
				else
				{
					world.dragonScaleNeed--;
				}
				if ((world.dragonScaleNeed == 0) && (getRandom(100) < 80))
				{
					npc.setIsInvul(false);
				}
			}
		}
		return super.onSkillSee(npc, caster, skill, targets, isSummon);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon, Skill skill)
	{
		if (npc.getId() == TEARS)
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof CCWorld)
			{
				final CCWorld world = (CCWorld) tmpworld;
				if ((world.getStatus() != 4) && (attacker != null))
				{
					// Lucky cheater, the code only kicks his/her ass out of the dungeon
					teleportPlayer(attacker, new Location(149361, 172327, -945), 0);
					world.removeAllowed(attacker);
				}
				else if (world.tears != npc)
				{
					return "";
				}
				else if (!world.copys.isEmpty())
				{
					boolean notAOE = true;
					if ((skill != null) && ((skill.getTargetType() == L2TargetType.AREA) || (skill.getTargetType() == L2TargetType.FRONT_AREA) || (skill.getTargetType() == L2TargetType.BEHIND_AREA) || (skill.getTargetType() == L2TargetType.AURA) || (skill.getTargetType() == L2TargetType.FRONT_AURA) || (skill.getTargetType() == L2TargetType.BEHIND_AURA)))
					{
						notAOE = false;
					}
					if (notAOE)
					{
						for (L2Npc copy : world.copys)
						{
							copy.onDecay();
						}
						world.copys.clear();
					}
					return "";
				}
				
				final int maxHp = npc.getMaxHp();
				final double nowHp = npc.getStatus().getCurrentHp();
				final int rand = getRandom(1000);
				
				if ((nowHp < (maxHp * 0.4)) && (rand < 5))
				{
					final L2Party party = attacker.getParty();
					if (party != null)
					{
						for (L2PcInstance partyMember : party.getMembers())
						{
							stopAttack(partyMember);
						}
					}
					else
					{
						stopAttack(attacker);
					}
					final L2Character target = npc.getAI().getAttackTarget();
					for (int i = 0; i < 10; i++)
					{
						final L2Npc copy = addSpawn(TEARS_COPY, npc.getX(), npc.getY(), npc.getZ(), 0, false, 0, false, attacker.getInstanceId());
						copy.setRunning();
						((L2Attackable) copy).addDamageHate(target, 0, 99999);
						copy.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
						copy.setCurrentHp(nowHp);
						world.copys.add(copy);
					}
				}
				else if ((nowHp < (maxHp * 0.15)) && !world.isUsedInvulSkill)
				{
					if ((rand > 994) || (nowHp < (maxHp * 0.1)))
					{
						world.isUsedInvulSkill = true;
						npc.setIsInvul(true);
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public String onSpellFinished(L2Npc npc, L2PcInstance player, Skill skill)
	{
		if ((npc.getId() == BAYLOR) && (skill.getId() == 5225))
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof CCWorld)
			{
				((CCWorld) tmpworld)._raidStatus++;
			}
		}
		return super.onSpellFinished(npc, player, skill);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof CCWorld)
		{
			final CCWorld world = (CCWorld) tmpworld;
			if (event.equalsIgnoreCase("TeleportOut"))
			{
				teleportPlayer(player, new Location(149413, 173078, -5014), 0);
			}
			else if (event.equalsIgnoreCase("TeleportParme"))
			{
				teleportPlayer(player, new Location(153689, 142226, -9750), world.getInstanceId());
			}
			else if (event.equalsIgnoreCase("Timer2") || event.equalsIgnoreCase("Timer3") || event.equalsIgnoreCase("Timer4") || event.equalsIgnoreCase("Timer5"))
			{
				if (player.getInstanceId() == world.getInstanceId())
				{
					teleportPlayer(player, new Location(144653, 152606, -12126), world.getInstanceId());
					player.stopSkillEffects(true, 5239);
					SkillData.getInstance().getSkill(5239, 1).applyEffects(player, player);
					startQuestTimer("Timer2", 300000, npc, player);
				}
			}
			else if (event.equalsIgnoreCase("Timer21") || event.equalsIgnoreCase("Timer31") || event.equalsIgnoreCase("Timer41") || event.equalsIgnoreCase("Timer51"))
			{
				InstanceManager.getInstance().getInstance(world.getInstanceId()).removeNpcs();
				world.npcList2.clear();
				runSteamRooms(world, STEAM1_SPAWNS, 22);
				startQuestTimer("Timer21", 300000, npc, null);
			}
			
			else if (event.equalsIgnoreCase("checkKechiAttack"))
			{
				if (npc.isInCombat())
				{
					startQuestTimer("spawnGuards", SPAWN[0], npc, null);
					cancelQuestTimers("checkKechiAttack");
					world.closeDoor(DOOR4);
					world.closeDoor(DOOR3);
				}
				else
				{
					startQuestTimer("checkKechiAttack", 1000, npc, null);
				}
			}
			else if (event.equalsIgnoreCase("spawnGuards"))
			{
				world.kechisHenchmanSpawn++;
				world.guards.add(addSpawn(KECHIGUARD, 153622, 149699, -12131, 56890, false, 0, false, world.getInstanceId()));
				world.guards.add(addSpawn(KECHIGUARD, 153609, 149622, -12131, 64023, false, 0, false, world.getInstanceId()));
				world.guards.add(addSpawn(KECHIGUARD, 153606, 149428, -12131, 64541, false, 0, false, world.getInstanceId()));
				world.guards.add(addSpawn(KECHIGUARD, 153601, 149534, -12131, 64901, false, 0, false, world.getInstanceId()));
				world.guards.add(addSpawn(KECHIGUARD, 153620, 149354, -12131, 1164, false, 0, false, world.getInstanceId()));
				world.guards.add(addSpawn(KECHIGUARD, 153637, 149776, -12131, 61733, false, 0, false, world.getInstanceId()));
				world.guards.add(addSpawn(KECHIGUARD, 153638, 149292, -12131, 64071, false, 0, false, world.getInstanceId()));
				world.guards.add(addSpawn(KECHIGUARD, 153647, 149857, -12131, 59402, false, 0, false, world.getInstanceId()));
				world.guards.add(addSpawn(KECHIGUARD, 153661, 149227, -12131, 65275, false, 0, false, world.getInstanceId()));
				if (world.kechisHenchmanSpawn <= 5)
				{
					startQuestTimer("spawnGuards", SPAWN[world.kechisHenchmanSpawn], npc, null);
				}
				else
				{
					cancelQuestTimers("spawnGuards");
				}
			}
			else if (event.equalsIgnoreCase("EmeraldSteam"))
			{
				runEmerald(world);
				for (L2Npc oracle : world.oracle)
				{
					oracle.decayMe();
				}
			}
			else if (event.equalsIgnoreCase("CoralGarden"))
			{
				runCoral(world);
				for (L2Npc oracle : world.oracle)
				{
					oracle.decayMe();
				}
			}
			else if (event.equalsIgnoreCase("spawn_oracle"))
			{
				addSpawn(32271, 153572, 142075, -9728, 10800, false, 0, false, world.getInstanceId());
				addSpawn((getRandom(10) < 5 ? 29116 : 29117), npc.getX(), npc.getY(), npc.getZ(), npc.getHeading(), false, 0, false, world.getInstanceId()); // Baylor's Chest
				addSpawn(ORACLE_GUIDE_4, 153572, 142075, -12738, 10800, false, 0, false, world.getInstanceId());
				cancelQuestTimer("baylor_despawn", npc, null);
				cancelQuestTimers("baylor_skill");
			}
			else if (event.equalsIgnoreCase("baylorEffect0"))
			{
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				npc.broadcastSocialAction(1);
				startQuestTimer("baylorCamera0", 11000, npc, null);
				startQuestTimer("baylorEffect1", 19000, npc, null);
			}
			else if (event.equalsIgnoreCase("baylorCamera0"))
			{
				npc.broadcastPacket(new SpecialCamera(npc, 500, -45, 170, 5000, 9000, 0, 0, 1, 0, 0));
			}
			else if (event.equalsIgnoreCase("baylorEffect1"))
			{
				npc.broadcastPacket(new SpecialCamera(npc, 300, 0, 120, 2000, 5000, 0, 0, 1, 0, 0));
				npc.broadcastSocialAction(3);
				startQuestTimer("baylorEffect2", 4000, npc, null);
			}
			else if (event.equalsIgnoreCase("baylorEffect2"))
			{
				npc.broadcastPacket(new SpecialCamera(npc, 747, 0, 160, 2000, 3000, 0, 0, 1, 0, 0));
				npc.broadcastPacket(new MagicSkillUse(npc, npc, 5402, 1, 2000, 0));
				startQuestTimer("RaidStart", 2000, npc, null);
			}
			else if (event.equalsIgnoreCase("BaylorMinions"))
			{
				for (int i = 0; i < 10; i++)
				{
					final int radius = 300;
					final int x = (int) (radius * Math.cos(i * 0.618));
					final int y = (int) (radius * Math.sin(i * 0.618));
					final L2Npc mob = addSpawn(29104, 153571 + x, 142075 + y, -12737, 0, false, 0, false, world.getInstanceId());
					mob.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
					world._animationMobs.add(mob);
				}
				startQuestTimer("baylorEffect0", 200, npc, null);
			}
			else if (event.equalsIgnoreCase("RaidStart"))
			{
				world._camera.decayMe();
				world._camera = null;
				npc.setIsParalyzed(false);
				for (L2PcInstance p : world._raiders)
				{
					p.setIsParalyzed(false);
					Throw(npc, p);
					if (p.getSummon() != null)
					{
						Throw(npc, p.getSummon());
					}
				}
				world._raidStatus = 0;
				for (L2Npc mob : world._animationMobs)
				{
					mob.doDie(mob);
				}
				world._animationMobs.clear();
				startQuestTimer("baylor_despawn", 60000, npc, null, true);
				startQuestTimer("checkBaylorAttack", 1000, npc, null);
			}
			else if (event.equalsIgnoreCase("checkBaylorAttack"))
			{
				if (npc.isInCombat())
				{
					cancelQuestTimers("checkBaylorAttack");
					startQuestTimer("baylor_alarm", 40000, npc, null);
					startQuestTimer("baylor_skill", 5000, npc, null, true);
					world._raidStatus++;
				}
				else
				{
					startQuestTimer("checkBaylorAttack", 1000, npc, null);
				}
			}
			else if (event.equalsIgnoreCase("baylor_alarm"))
			{
				if (world._alarm == null)
				{
					final int[] spawnLoc = ALARMSPAWN[getRandom(ALARMSPAWN.length)];
					npc.addSkill(SkillData.getInstance().getSkill(5244, 1));
					npc.addSkill(SkillData.getInstance().getSkill(5245, 1));
					world._alarm = addSpawn(ALARMID, spawnLoc[0], spawnLoc[1], spawnLoc[2], 10800, false, 0, false, world.getInstanceId());
					world._alarm.disableCoreAI(true);
					world._alarm.setIsImmobilized(true);
					world._alarm.broadcastPacket(new CreatureSay(world._alarm.getObjectId(), ChatType.SHOUT, world._alarm.getName(), NpcStringId.AN_ALARM_HAS_BEEN_SET_OFF_EVERYBODY_WILL_BE_IN_DANGER_IF_THEY_ARE_NOT_TAKEN_CARE_OF_IMMEDIATELY));
				}
			}
			else if (event.equalsIgnoreCase("baylor_skill"))
			{
				if (world._baylor == null)
				{
					cancelQuestTimers("baylor_skill");
				}
				else
				{
					final int maxHp = npc.getMaxHp();
					final double nowHp = npc.getStatus().getCurrentHp();
					final int rand = getRandom(100);
					
					if ((nowHp < (maxHp * 0.2)) && (world._raidStatus < 3) && !npc.isAffectedBySkill(5224) && !npc.isAffectedBySkill(5225))
					{
						if ((nowHp < (maxHp * 0.15)) && (world._raidStatus == 2))
						{
							npc.doCast(SkillData.getInstance().getSkill(5225, 1));
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.SHOUT, npc.getName(), NpcStringId.DEMON_KING_BELETH_GIVE_ME_THE_POWER_AAAHH));
						}
						else if ((rand < 10) || (nowHp < (maxHp * 0.15)))
						{
							npc.doCast(SkillData.getInstance().getSkill(5225, 1));
							npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.SHOUT, npc.getName(), NpcStringId.DEMON_KING_BELETH_GIVE_ME_THE_POWER_AAAHH));
							startQuestTimer("baylor_remove_invul", 30000, world._baylor, null);
						}
					}
					else if ((nowHp < (maxHp * 0.3)) && (rand > 50) && !npc.isAffectedBySkill(5225) && !npc.isAffectedBySkill(5224))
					{
						npc.doCast(SkillData.getInstance().getSkill(5224, 1));
					}
					else if (rand < 33)
					{
						npc.setTarget(world._raiders.get(getRandom(world._raiders.size())));
						npc.doCast(SkillData.getInstance().getSkill(5229, 1));
					}
				}
			}
			else if (event.equalsIgnoreCase("baylor_remove_invul"))
			{
				npc.stopSkillEffects(false, 5225);
			}
			else if (event.equalsIgnoreCase("Baylor"))
			{
				world._baylor = addSpawn(29099, 153572, 142075, -12738, 10800, false, 0, false, world.getInstanceId());
				world._baylor.setIsParalyzed(true);
				world._camera = addSpawn(29120, 153273, 141400, -12738, 10800, false, 0, false, world.getInstanceId());
				world._camera.broadcastPacket(new SpecialCamera(world._camera, 700, -45, 160, 500, 15200, 0, 0, 1, 0, 0));
				startQuestTimer("baylorMinions", 2000, world._baylor, null);
			}
			else if (!event.endsWith("Food"))
			{
				return "";
			}
			else if (event.equalsIgnoreCase("autoFood"))
			{
				if (!world.crystalGolems.containsKey(npc))
				{
					world.crystalGolems.put(npc, new CrystalGolem());
				}
				if ((world.getStatus() != 3) || !world.crystalGolems.containsKey(npc) || (world.crystalGolems.get(npc).foodItem != null) || world.crystalGolems.get(npc).isAtDestination)
				{
					return "";
				}
				
				final CrystalGolem cryGolem = world.crystalGolems.get(npc);
				int minDist = 300000;
				for (L2ItemInstance object : L2World.getInstance().getVisibleObjectsInRange(npc, L2ItemInstance.class, 300))
				{
					if (object.getId() == CRYSTALFOOD)
					{
						final int dx = npc.getX() - object.getX();
						final int dy = npc.getY() - object.getY();
						final int d = (dx * dx) + (dy * dy);
						if (d < minDist)
						{
							minDist = d;
							cryGolem.foodItem = object;
						}
					}
				}
				
				if (minDist != 300000)
				{
					startQuestTimer("getFood", 2000, npc, null);
				}
				else
				{
					if (getRandom(100) < 5)
					{
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.SHOUT, npc.getName(), NpcStringId.AH_I_M_HUNGRY));
					}
					startQuestTimer("autoFood", 2000, npc, null);
				}
				return "";
			}
			else if (!world.crystalGolems.containsKey(npc) || world.crystalGolems.get(npc).isAtDestination)
			{
				return "";
			}
			else if (event.equalsIgnoreCase("backFood"))
			{
				if (npc.getAI().getIntention() == CtrlIntention.AI_INTENTION_ACTIVE)
				{
					cancelQuestTimers("backFood");
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null);
					world.crystalGolems.get(npc).foodItem = null;
					startQuestTimer("autoFood", 2000, npc, null);
				}
			}
			else if (event.equalsIgnoreCase("reachFood"))
			{
				final CrystalGolem cryGolem = world.crystalGolems.get(npc);
				int dx;
				int dy;
				if ((cryGolem.foodItem == null) || !cryGolem.foodItem.isSpawned())
				{
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, cryGolem.oldLoc);
					cancelQuestTimers("reachFood");
					startQuestTimer("backFood", 2000, npc, null, true);
					return "";
				}
				else if (npc.getAI().getIntention() == CtrlIntention.AI_INTENTION_ACTIVE)
				{
					L2World.getInstance().removeVisibleObject(cryGolem.foodItem, cryGolem.foodItem.getWorldRegion());
					L2World.getInstance().removeObject(cryGolem.foodItem);
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null);
					cryGolem.foodItem = null;
					dx = npc.getX() - 142999;
					dy = npc.getY() - 151671;
					final int d1 = (dx * dx) + (dy * dy);
					dx = npc.getX() - 139494;
					dy = npc.getY() - 151668;
					final int d2 = (dx * dx) + (dy * dy);
					if ((d1 < 10000) || (d2 < 10000))
					{
						npc.broadcastPacket(new MagicSkillUse(npc, npc, 5441, 1, 1, 0));
						cryGolem.isAtDestination = true;
						world.correctGolems++;
						if (world.correctGolems >= 2)
						{
							world.openDoor(24220026);
							world.setStatus(4);
						}
					}
					else
					{
						startQuestTimer("autoFood", 2000, npc, null);
					}
					cancelQuestTimers("reachFood");
				}
				return "";
			}
			else if (event.equalsIgnoreCase("getFood"))
			{
				final CrystalGolem cryGolem = world.crystalGolems.get(npc);
				final Location newLoc = new Location(cryGolem.foodItem.getX(), cryGolem.foodItem.getY(), cryGolem.foodItem.getZ(), 0);
				cryGolem.oldLoc = new Location(npc.getX(), npc.getY(), npc.getZ(), npc.getHeading());
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, newLoc);
				startQuestTimer("reachFood", 2000, npc, null, true);
				cancelQuestTimers("getFood");
			}
		}
		return "";
	}
	
	private void giveRewards(L2PcInstance player, int instanceId, int bossCry, boolean isBaylor)
	{
		final int num = Math.max((int) Config.RATE_DEATH_DROP_CHANCE_MULTIPLIER, 1);
		
		final L2Party party = player.getParty();
		if (party != null)
		{
			for (L2PcInstance partyMember : party.getMembers())
			{
				if (partyMember.getInstanceId() == instanceId)
				{
					if (!isBaylor && hasQuestItems(partyMember, CONT_CRYSTAL))
					{
						takeItems(partyMember, CONT_CRYSTAL, 1);
						giveItems(partyMember, bossCry, 1);
					}
					if (getRandom(10) < 5)
					{
						giveItems(partyMember, WHITE_SEED, num);
					}
					else
					{
						giveItems(partyMember, BLACK_SEED, num);
					}
				}
			}
		}
		else if (player.getInstanceId() == instanceId)
		{
			if (!isBaylor && hasQuestItems(player, CONT_CRYSTAL))
			{
				takeItems(player, CONT_CRYSTAL, 1);
				giveItems(player, bossCry, 1);
			}
			if (getRandom(10) < 5)
			{
				giveItems(player, WHITE_SEED, num);
			}
			else
			{
				giveItems(player, BLACK_SEED, num);
			}
		}
		
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof CCWorld)
		{
			final CCWorld world = (CCWorld) tmpworld;
			if ((world.getStatus() == 2) && world.npcList1.containsKey(npc))
			{
				world.npcList1.put(npc, true);
				for (boolean isDead : world.npcList1.values())
				{
					if (!isDead)
					{
						return "";
					}
				}
				world.setStatus(3);
				world.tears = addSpawn(TEARS, 144298, 154420, -11854, 32767, false, 0, false, world.getInstanceId()); // Tears
				final CrystalGolem crygolem1 = new CrystalGolem();
				final CrystalGolem crygolem2 = new CrystalGolem();
				world.crystalGolems.put(addSpawn(CRYSTAL_GOLEM, 140547, 151670, -11813, 32767, false, 0, false, world.getInstanceId()), crygolem1);
				world.crystalGolems.put(addSpawn(CRYSTAL_GOLEM, 141941, 151684, -11813, 63371, false, 0, false, world.getInstanceId()), crygolem2);
				for (L2Npc crygolem : world.crystalGolems.keySet())
				{
					startQuestTimer("autoFood", 2000, crygolem, null);
				}
			}
			else if ((world.getStatus() == 4) && (npc.getId() == TEARS))
			{
				InstanceManager.getInstance().getInstance(world.getInstanceId()).setDuration(300000);
				addSpawn(32280, 144312, 154420, -11855, 0, false, 0, false, world.getInstanceId());
				giveRewards(player, npc.getInstanceId(), BOSS_CRYSTAL_3, false);
			}
			else if ((world.getStatus() == 2) && world.keyKeepers.contains(npc))
			{
				if (npc.getId() == GK1)
				{
					npc.dropItem(player, 9698, 1);
					runEmeraldSquare(world);
				}
				else if (npc.getId() == GK2)
				{
					npc.dropItem(player, 9699, 1);
					runSteamRooms(world, STEAM1_SPAWNS, 22);
					final L2Party party = player.getParty();
					if (party != null)
					{
						for (L2PcInstance partyMember : party.getMembers())
						{
							if (partyMember.getInstanceId() == world.getInstanceId())
							{
								SkillData.getInstance().getSkill(5239, 1).applyEffects(partyMember, partyMember);
								startQuestTimer("Timer2", 300000, npc, partyMember);
							}
						}
					}
					else
					{
						SkillData.getInstance().getSkill(5239, 1).applyEffects(player, player);
						startQuestTimer("Timer2", 300000, npc, player);
					}
					startQuestTimer("Timer21", 300000, npc, null);
				}
				for (L2Npc gk : world.keyKeepers)
				{
					if (gk != npc)
					{
						gk.decayMe();
					}
				}
			}
			else if (world.getStatus() == 3)
			{
				if (checkKillProgress(0, npc, world))
				{
					world.setStatus(4);
					addSpawn(TOURMALINE, 148202, 144791, -12235, 0, false, 0, false, world.getInstanceId());
				}
				else
				{
					return "";
				}
			}
			else if (world.getStatus() == 4)
			{
				if (npc.getId() == TOURMALINE)
				{
					world.setStatus(5);
					addSpawn(TEROD, 147777, 146780, -12281, 0, false, 0, false, world.getInstanceId());
				}
			}
			else if (world.getStatus() == 5)
			{
				if (npc.getId() == TEROD)
				{
					world.setStatus(6);
					addSpawn(TOURMALINE, 143694, 142659, -11882, 0, false, 0, false, world.getInstanceId());
				}
			}
			else if (world.getStatus() == 6)
			{
				if (npc.getId() == TOURMALINE)
				{
					world.setStatus(7);
					addSpawn(DOLPH, 142054, 143288, -11825, 0, false, 0, false, world.getInstanceId());
				}
			}
			else if (world.getStatus() == 7)
			{
				if (npc.getId() == DOLPH)
				{
					world.setStatus(8);
					// first door opener trap
					addTrap(DOOR_OPENING_TRAP[0], DOOR_OPENING_TRAP[1], DOOR_OPENING_TRAP[2], DOOR_OPENING_TRAP[3], DOOR_OPENING_TRAP[4], null, world.getInstanceId());
				}
			}
			else if (world.getStatus() == 8)
			{
				for (int i = 0; i < 4; i++)
				{
					if ((world.roomsStatus[i] == 1) && checkKillProgress(i + 1, npc, world))
					{
						world.roomsStatus[i] = 2;
					}
					if (world.roomsStatus[i] == 2)
					{
						world.cleanedRooms++;
						if (world.cleanedRooms == 21)
						{
							runDarnel(world);
						}
					}
				}
			}
			else if ((world.getStatus() >= 22) && (world.getStatus() <= 25))
			{
				if (npc.getId() == 22416)
				{
					for (L2Npc oracle : world.oracles.keySet())
					{
						if (world.oracles.get(oracle) == npc)
						{
							world.oracles.put(oracle, null);
						}
					}
				}
				if (checkKillProgress(0, npc, world))
				{
					world.npcList2.clear();
					int[][] oracleOrder;
					switch (world.getStatus())
					{
						case 22:
						{
							world.closeDoor(DOOR6);
							oracleOrder = ordreOracle1;
							break;
						}
						case 23:
						{
							oracleOrder = ordreOracle2;
							break;
						}
						case 24:
						{
							oracleOrder = ordreOracle3;
							break;
						}
						case 25:
						{
							world.setStatus(26);
							final L2Party party = player.getParty();
							if (party != null)
							{
								for (L2PcInstance partyMember : party.getMembers())
								{
									partyMember.stopSkillEffects(true, 5239);
								}
							}
							cancelQuestTimers("Timer5");
							cancelQuestTimers("Timer51");
							world.openDoor(DOOR3);
							world.openDoor(DOOR4);
							final L2Npc kechi = addSpawn(KECHI, 154069, 149525, -12158, 51165, false, 0, false, world.getInstanceId());
							startQuestTimer("checkKechiAttack", 1000, kechi, null);
							return "";
						}
						default:
						{
							LOGGER.warning("CrystalCavern-SteamCorridor: status " + world.getStatus() + " error. OracleOrder not found in " + world.getInstanceId());
							return "";
						}
					}
					runSteamOracles(world, oracleOrder);
				}
			}
			else if (((world.getStatus() == 9) && (npc.getId() == DARNEL)) || ((world.getStatus() == 26) && (npc.getId() == KECHI)))
			{
				InstanceManager.getInstance().getInstance(world.getInstanceId()).setDuration(300000);
				int bossCry;
				if (npc.getId() == KECHI)
				{
					bossCry = BOSS_CRYSTAL_2;
					cancelQuestTimers("spawnGuards");
					addSpawn(32280, 154077, 149527, -12159, 0, false, 0, false, world.getInstanceId());
				}
				else if (npc.getId() == DARNEL)
				{
					bossCry = BOSS_CRYSTAL_1;
					addSpawn(32280, 152761, 145950, -12588, 0, false, 0, false, world.getInstanceId());
				}
				else
				{
					// something is wrong
					return "";
				}
				giveRewards(player, npc.getInstanceId(), bossCry, false);
			}
			if (npc.getId() == ALARMID)
			{
				world._baylor.removeSkill(5244);
				world._baylor.removeSkill(5245);
				world._alarm = null;
				if ((world._baylor.getMaxHp() * 0.3) < world._baylor.getStatus().getCurrentHp())
				{
					startQuestTimer("baylor_alarm", 40000, world._baylor, null);
				}
			}
			else if (npc.getId() == BAYLOR)
			{
				world.setStatus(31);
				world._baylor = null;
				npc.broadcastPacket(new PlaySound(1, "BS01_D", 1, npc.getObjectId(), npc.getX(), npc.getY(), npc.getZ()));
				final Instance baylorInstance = InstanceManager.getInstance().getInstance(npc.getInstanceId());
				baylorInstance.setDuration(300000);
				startQuestTimer("spawn_oracle", 1000, npc, null);
				giveRewards(player, npc.getInstanceId(), -1, true);
			}
		}
		return "";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final int npcId = npc.getId();
		QuestState st = getQuestState(player, false);
		if (st == null)
		{
			st = newQuestState(player);
		}
		if (npcId == ORACLE_GUIDE_1)
		{
			final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
			if (world != null)
			{
				if (world.getTemplateId() == TEMPLATE_ID)
				{
					onEnterInstance(player, world, false);
					return "";
				}
				player.sendPacket(SystemMessageId.YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON);
				return "";
			}
			
			if (checkConditions(player))
			{
				final InstanceWorld instance = new CCWorld(System.currentTimeMillis() + 5400000);
				instance.setInstance(InstanceManager.getInstance().createDynamicInstance(TEMPLATE_ID));
				InstanceManager.getInstance().addWorld(instance);
				onEnterInstance(player, instance, true);
				
				final Instance inst = InstanceManager.getInstance().getInstance(instance.getInstanceId());
				if (inst.getReenterType() == InstanceReenterType.ON_ENTER)
				{
					handleReenterTime(instance);
				}
				
				if (inst.isRemoveBuffEnabled())
				{
					handleRemoveBuffs(instance);
				}
			}
			
			return "";
		}
		
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof CCWorld)
		{
			final CCWorld world = (CCWorld) tmpworld;
			if (npcId == CRYSTAL_GOLEM)
			{
			}
			else if ((npc.getId() >= 32275) && (npc.getId() <= 32277) && world.OracleTriggered[npc.getId() - 32275])
			{
				boolean doTeleport = false;
				Location loc = null;
				final L2Party party = player.getParty();
				doTeleport = true;
				switch (npc.getId())
				{
					case 32275:
					{
						if (world.getStatus() == 22)
						{
							runSteamRooms(world, STEAM2_SPAWNS, 23);
						}
						loc = new Location(147529, 152587, -12169);
						cancelQuestTimers("Timer2");
						cancelQuestTimers("Timer21");
						if (party != null)
						{
							for (L2PcInstance partyMember : party.getMembers())
							{
								if (partyMember.getInstanceId() == world.getInstanceId())
								{
									partyMember.stopSkillEffects(true, 5239);
									SkillData.getInstance().getSkill(5239, 2).applyEffects(partyMember, partyMember);
									startQuestTimer("Timer3", 600000, npc, partyMember);
								}
							}
						}
						else
						{
							player.stopSkillEffects(true, 5239);
							SkillData.getInstance().getSkill(5239, 2).applyEffects(player, player);
							startQuestTimer("Timer3", 600000, npc, player);
						}
						startQuestTimer("Timer31", 600000, npc, null);
						break;
					}
					case 32276:
					{
						if (world.getStatus() == 23)
						{
							runSteamRooms(world, STEAM3_SPAWNS, 24);
						}
						loc = new Location(150194, 152610, -12169);
						cancelQuestTimers("Timer3");
						cancelQuestTimers("Timer31");
						if (party != null)
						{
							for (L2PcInstance partyMember : party.getMembers())
							{
								if (partyMember.getInstanceId() == world.getInstanceId())
								{
									partyMember.stopSkillEffects(true, 5239);
									SkillData.getInstance().getSkill(5239, 4).applyEffects(partyMember, partyMember);
									startQuestTimer("Timer4", 1200000, npc, partyMember);
								}
							}
						}
						else
						{
							player.stopSkillEffects(true, 5239);
							SkillData.getInstance().getSkill(5239, 4).applyEffects(player, player);
							startQuestTimer("Timer4", 1200000, npc, player);
						}
						startQuestTimer("Timer41", 1200000, npc, null);
						break;
					}
					case 32277:
					{
						if (world.getStatus() == 24)
						{
							runSteamRooms(world, STEAM4_SPAWNS, 25);
						}
						loc = new Location(149743, 149986, -12141);
						cancelQuestTimers("Timer4");
						cancelQuestTimers("Timer41");
						if (party != null)
						{
							for (L2PcInstance partyMember : party.getMembers())
							{
								if (partyMember.getInstanceId() == world.getInstanceId())
								{
									partyMember.stopSkillEffects(true, 5239);
									SkillData.getInstance().getSkill(5239, 3).applyEffects(partyMember, partyMember);
									startQuestTimer("Timer5", 900000, npc, partyMember);
								}
							}
						}
						else
						{
							player.stopSkillEffects(true, 5239);
							SkillData.getInstance().getSkill(5239, 3).applyEffects(player, player);
							startQuestTimer("Timer5", 900000, npc, player);
						}
						startQuestTimer("Timer51", 900000, npc, null);
						break;
					}
					default:
					{
						// something is wrong
						doTeleport = false;
					}
				}
				if (doTeleport && (loc != null))
				{
					if (!checkOracleConditions(player))
					{
						return "";
					}
					else if (party != null)
					{
						for (L2PcInstance partyMember : party.getMembers())
						{
							partyMember.destroyItemByItemId("Quest", RED_CORAL, 1, player, true);
							teleportPlayer(partyMember, loc, npc.getInstanceId());
						}
					}
					else
					{
						teleportPlayer(player, loc, npc.getInstanceId());
					}
				}
			}
			else if (npc.getId() == ORACLE_GUIDE_3)
			{
				if ((world.getStatus() < 30) && checkBaylorConditions(player))
				{
					world._raiders.clear();
					final L2Party party = player.getParty();
					if (party == null)
					{
						world._raiders.add(player);
					}
					else
					{
						for (L2PcInstance partyMember : party.getMembers())
						{
							// int rnd = getRandom(100);
							// partyMember.destroyItemByItemId("Quest", (rnd < 33 ? BOSS_CRYSTAL_1:(rnd < 67 ? BOSS_CRYSTAL_2:BOSS_CRYSTAL_3)), 1, partyMember, true); Crystals are no longer beign cunsumed while entering to Baylor Lair.
							world._raiders.add(partyMember);
						}
					}
				}
				else
				{
					return "";
				}
				world.setStatus(30);
				final long time = world.endTime - System.currentTimeMillis();
				final Instance baylorInstance = InstanceManager.getInstance().getInstance(world.getInstanceId());
				baylorInstance.setDuration((int) time);
				
				final int radius = 150;
				int i = 0;
				final int members = world._raiders.size();
				for (L2PcInstance p : world._raiders)
				{
					final int x = (int) (radius * Math.cos((i * 2 * Math.PI) / members));
					final int y = (int) (radius * Math.sin((i++ * 2 * Math.PI) / members));
					p.teleToLocation(new Location(153571 + x, 142075 + y, -12737));
					final L2Summon pet = p.getSummon();
					if (pet != null)
					{
						pet.teleToLocation(new Location(153571 + x, 142075 + y, -12737), true);
						pet.broadcastPacket(new ValidateLocation(pet));
					}
					p.setIsParalyzed(true);
					p.broadcastPacket(new ValidateLocation(p));
				}
				startQuestTimer("Baylor", 30000, npc, null);
			}
			else if ((npc.getId() == ORACLE_GUIDE_4) && (world.getStatus() == 31))
			{
				teleportPlayer(player, new Location(153522, 144212, -9747), npc.getInstanceId());
			}
		}
		return "";
	}
	
	@Override
	public String onTrapAction(L2TrapInstance trap, L2Character trigger, TrapAction action)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(trap);
		if (tmpworld instanceof CCWorld)
		{
			final CCWorld world = (CCWorld) tmpworld;
			switch (action)
			{
				case TRAP_DISARMED:
				{
					if (trap.getId() == DOOR_OPENING_TRAP[0])
					{
						world.openDoor(24220001);
						runEmeraldRooms(world, ROOM1_SPAWNS, 1);
					}
					break;
				}
			}
		}
		return null;
	}
	
	@Override
	public String onEnterZone(L2Character character, L2ZoneType zone)
	{
		if (character.isPlayer())
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(character);
			if (tmpworld instanceof CCWorld)
			{
				final CCWorld world = (CCWorld) tmpworld;
				if (world.getStatus() == 8)
				{
					int room;
					int[][] spawns;
					switch (zone.getId())
					{
						case 20105:
						{
							spawns = ROOM2_SPAWNS;
							room = 2;
							break;
						}
						case 20106:
						{
							spawns = ROOM3_SPAWNS;
							room = 3;
							break;
						}
						case 20107:
						{
							spawns = ROOM4_SPAWNS;
							room = 4;
							break;
						}
						default:
						{
							return super.onEnterZone(character, zone);
						}
					}
					for (L2DoorInstance door : InstanceManager.getInstance().getInstance(world.getInstanceId()).getDoors())
					{
						if (door.getId() == (room + 24220000))
						{
							if (door.isOpen())
							{
								return "";
							}
							
							if (!hasQuestItems((L2PcInstance) character, RACE_KEY))
							{
								return "";
							}
							if (world.roomsStatus[zone.getId() - 20104] == 0)
							{
								runEmeraldRooms(world, spawns, room);
							}
							door.openMe();
							takeItems((L2PcInstance) character, RACE_KEY, 1);
							world.openedDoors.put(door, (L2PcInstance) character);
							break;
						}
					}
				}
			}
		}
		return super.onEnterZone(character, zone);
	}
	
	@Override
	public String onExitZone(L2Character character, L2ZoneType zone)
	{
		if (character.isPlayer())
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(character);
			if (tmpworld instanceof CCWorld)
			{
				final CCWorld world = (CCWorld) tmpworld;
				if (world.getStatus() == 8)
				{
					int doorId;
					switch (zone.getId())
					{
						case 20105:
						{
							doorId = 24220002;
							break;
						}
						case 20106:
						{
							doorId = 24220003;
							break;
						}
						case 20107:
						{
							doorId = 24220004;
							break;
						}
						default:
						{
							return super.onExitZone(character, zone);
						}
					}
					for (L2DoorInstance door : InstanceManager.getInstance().getInstance(world.getInstanceId()).getDoors())
					{
						if (door.getId() == doorId)
						{
							if (door.isOpen() && (world.openedDoors.get(door) == character))
							{
								door.closeMe();
								world.openedDoors.remove(door);
							}
							break;
						}
					}
					
				}
			}
		}
		return super.onExitZone(character, zone);
	}
	
	public static void main(String[] args)
	{
		new CrystalCaverns();
	}
}

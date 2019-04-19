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
package ai.areas.Gracia.instances.SeedOfDestruction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.instancemanager.SoDManager;
import com.l2jmobius.gameserver.model.L2CommandChannel;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.zone.L2ZoneType;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

public class SeedOfDestruction extends AbstractNpcAI
{
	private class SODWorld extends InstanceWorld
	{
		public Map<L2Npc, Boolean> npcList = new HashMap<>();
		public int killedDevice = 0;
		public int deviceSpawnedMobCount = 0;
		public boolean ZoneWaitForTP = true;
		public List<L2Npc> _mags = new ArrayList<>();
		L2Npc _tiat;
		L2Npc _naezdTR1;
		L2Npc _naezdTR2;
		L2Npc _naezdTL1;
		L2Npc _naezdTL2;
		
		public SODWorld(Long time)
		{
			InstanceManager.getInstance();
		}
	}
	
	// @formatter:off
	private static final boolean DEBUG = false;
	private static final int INSTANCE_ID = 110; // this is the client number
	private static final int MIN_PLAYERS = Config.MIN_TIAT_PLAYERS;
	private static final int MAX_PLAYERS = Config.MAX_TIAT_PLAYERS;
	private static final int MAX_DEVICE_SPAWNED_MOB_COUNT = 100;
	private static final int EXIT_TIME = 5; // exit time
	private int _numAtk = 0;
	
	// NPCs
	private static final int ALENOS = 32526;
	private static final int TELEPORT = 32601;
	private static final int OBELISK = 18776;
	private static final int POWERFUL_DEVICE = 18777;
	private static final int THRONE_POWERFUL_DEVICE = 18778;
	private static final int SPAWN_DEVICE = 18696;
	private static final int TIAT = 29163;
	private static final int NAEZD = 29162;
	private static final int[] SPAWN_MOB_IDS = {22536, 22537, 22538, 22539, 22540, 22541, 22542, 22543, 22544, 22547, 22550, 22551, 22552, 22596};
	private static final int[] MOB_IDS = {22536, 22537, 22538, 22539, 22540, 22541, 22542, 22543, 22544, 22547, 22550, 22551, 22552, 22596, 29162};
	private static final Location MOVE_TO_TIAT = new Location(-250403, 207273, -11952, 16384);
	
    // Traps/Skills
    private static final SkillHolder TRAP_HOLD = new SkillHolder(4186, 9); // 18720-18728
    private static final SkillHolder TRAP_STUN = new SkillHolder(4072, 10); // 18729-18736
    private static final SkillHolder TRAP_DAMAGE = new SkillHolder(5340, 4); // 18737-18774
    
	// Doors/Walls/Zones
	private static final int[] ATTACKABLE_DOORS = {12240005, 12240006, 12240007, 12240008, 12240009, 12240010, 12240013, 12240014, 12240015, 12240016, 12240017, 12240018, 12240021, 12240022, 12240023, 12240024, 12240025, 12240026, 12240028, 12240029, 12240030};
	private static final int[] ENTRANCE_ROOM_DOORS = {12240001, 12240002};
	private static final int[] SQUARE_DOORS = {12240003, 12240004, 12240011, 12240012, 12240019, 12240020};
	private static final int SCOUTPASS_DOOR = 12240027;
	private static final int THRONE_DOOR = 12240031;
	
	// Spawns
	private static final int[][] ENTRANCE_UPPER_SPAWNS =
	{
		{22549, -239298, 220214, -9792, 32637},
		{22549, -239294, 220064, -9760, 30975},
		{22549, -239290, 219890, -9760, 29173},
		{22549, -239294, 219728, -9800, 27648},
		{22549, -239294, 220137, -9768, 31778},
		{22549, -239294, 219806, -9776, 28351},
		{22593, -239294, 219984, -9752, 32767}
	};
	private static final int[][] ENTRANCE_GROUND_SPAWNS_1 =
	{
		{22541, -240417, 220200, -10144, 32775},
		{22541, -240417, 220100, -10143, 32775},
		{22541, -240417, 220000, -10143, 32775},
		{22541, -240417, 219900, -10143, 32775},
		{22541, -240417, 219800, -10143, 32775},
		{22550, -240156, 220200, -10143, 32775},
		{22550, -240156, 220100, -10143, 32775},
		{22550, -240156, 220000, -10143, 32775},
		{22550, -240156, 219900, -10143, 32775},
		{22550, -240156, 219800, -10143, 32775},
		{22551, -239900, 220200, -10143, 32775},
		{22551, -239900, 220100, -10143, 32775},
		{22551, -239900, 220000, -10143, 32775},
		{22551, -239900, 219900, -10143, 32775},
		{22551, -239900, 219800, -10143, 32775},
		{22552, -239821, 220546, -10143, 32775},
		{22552, -240414, 219420, -10143, 32775},
		{22552, -240399, 220547, -10143, 32775},
		{22552, -239866, 219986, -10143, 32775},
		{22552, -239833, 219419, -10143, 32775},
		{22596, -240064, 219966, -10143, 32775}
	};
	private static final int[][] ENTRANCE_GROUND_SPAWNS_2 =
	{
		{22541, -238845, 219557, -10144, 22378},
		{22541, -238971, 220184, -10144, 40988},
		{22541, -238498, 219515, -10144, 47694},
		{22544, -238728, 220066, -10144, 35011},
		{22544, -238524, 220521, -10144, 0},
		{22544, -238675, 220632, -10135, 16075},
		{22597, -239151, 220044, -10128, 32767},
		{22597, -239152, 219988, -10128, 32767},
		{22597, -239152, 219934, -10128, 32767},
		{32601, -238310, 219984, -10104, 0}
	};
	private static final int[][] SQUARE_SPAWNS_STATIC =
	{
		{18776, -245729, 217075, -12208, 0},
		{32528, -246367, 216720, -12231, 0},
		{32601, -245820, 220921, -12091, 0}
	};
	private static final int[][] FORT_PORTALS =
	{
		{18696, -251079, 209589, -11965, 16384},
		{18696, -249728, 209585, -11965, 16384}
	};
	private static final int[][] THRONE_PORTALS =
	{
		{18696, -248776, 209587, -11965, 16384},
		{18696, -248784, 206331, -11967, 16384},
		{18696, -252021, 206328, -11965, 16384},
		{18696, -252028, 209584, -11965, 16384}
	};
	private static final int[][] SQUARE_SPAWNS_MAIN =
	{
		{22541, -245714, 216761, -12208, 11487},
		{22541, -245563, 217294, -12208, 33720},
		{22544, -246104, 217132, -12208, 62589},
		{22543, -245857, 217391, -12208, 57177},
		{22544, -245408, 216484, -12224, 18467},
		{22543, -247062, 216734, -12227, 23550},
		{22541, -244854, 218073, -12224, 0},
		{22543, -245052, 217890, -12224, 0},
		{22544, -245459, 218451, -12224, 0},
		{22543, -247057, 217496, -12227, 33700},
		{22543, -246631, 217874, -12227, 34490},
		{22541, -246571, 218141, -12227, 10158},
		{22547, -246398, 217639, -12227, 33120},
		{22542, -245427, 217725, -12227, 36580},
		{22544, -244803, 217927, -12227, 5277},
		{22547, -245291, 217577, -12227, 51169},
		{22540, -245607, 217891, -12227, 20383},
		{22544, -245685, 218236, -12227, 6661},
		{22543, -245808, 218103, -12227, 41367},
		{22541, -245931, 217830, -12227, 44736},
		{22543, -245579, 217581, -12227, 57648},
		{22540, -245447, 218056, -12227, 23464},
		{22544, -245521, 216836, -12210, 52857},
		{22543, -245248, 216647, -12227, 7152},
		{22543, -245985, 216854, -12209, 4136},
		{22542, -246093, 217649, -12227, 59266},
		{22544, -245460, 217107, -12209, 41136},
		{22541, -245721, 217415, -12209, 51094},
		{22544, -246027, 217339, -12209, 44597},
		{22540, -245981, 218357, -12227, 36676},
		{22541, -246027, 218150, -12227, 46567},
		{22544, -246158, 217947, -12227, 42906},
		{22540, -246255, 218286, -12227, 13265},
		{22541, -246642, 217365, -12227, 28136},
		{22543, -246202, 217349, -12224, 39524},
		{22543, -246508, 216967, -12227, 47366},
		{22547, -246915, 216896, -12227, 43613},
		{22540, -245805, 216469, -12227, 6356},
		{22542, -244730, 217074, -12227, 1751},
		{22541, -244853, 217477, -12227, 21465},
		{22540, -245141, 217286, -12227, 39311},
		{22544, -244639, 217274, -12229, 63192},
		{22541, -244880, 216618, -12227, 65082},
		{22544, -244755, 216345, -12229, 4993},
		{22547, -245129, 216296, -12227, 32123},
		{22542, -245407, 216273, -12227, 29439},
		{22541, -245389, 215889, -12227, 40959},
		{22541, -246288, 216516, -12227, 56087},
		{22544, -246252, 216765, -12227, 14886},
		{22542, -246067, 216269, -12229, 52690},
		{22544, -246301, 216092, -12227, 39522},
		{22541, -246056, 215948, -12227, 59993},
		{22540, -245786, 215821, -12227, 60950},
		{22547, -245841, 216144, -12229, 7010},
		{22543, -246883, 217291, -12227, 35363},
		{22543, -246642, 216317, -12227, 35363},
		{22544, -246935, 216456, -12227, 27131}
	};
	private static final int[][] SQUARE_SPAWNS_HALF =
	{
		{22541, -245714, 216761, -12208, 11487},
		{22543, -245857, 217391, -12208, 57177},
		{22544, -245408, 216484, -12224, 18467},
		{22543, -245052, 217890, -12224, 0},
		{22544, -245459, 218451, -12224, 0},
		{22541, -246571, 218141, -12227, 10158},
		{22547, -246398, 217639, -12227, 33120},
		{22547, -245291, 217577, -12227, 51169},
		{22540, -245607, 217891, -12227, 20383},
		{22541, -245931, 217830, -12227, 44736},
		{22543, -245579, 217581, -12227, 57648},
		{22543, -245248, 216647, -12227, 7152},
		{22543, -245985, 216854, -12209, 4136},
		{22541, -245721, 217415, -12209, 51094},
		{22544, -246027, 217339, -12209, 44597},
		{22544, -246158, 217947, -12227, 42906},
		{22540, -246255, 218286, -12227, 13265},
		{22543, -246508, 216967, -12227, 47366},
		{22547, -246915, 216896, -12227, 43613},
		{22541, -244853, 217477, -12227, 21465},
		{22540, -245141, 217286, -12227, 39311},
		{22544, -244755, 216345, -12229, 4993},
		{22547, -245129, 216296, -12227, 32123},
		{22541, -246288, 216516, -12227, 56087},
		{22544, -246252, 216765, -12227, 14886},
		{22541, -246056, 215948, -12227, 59993},
		{22540, -245786, 215821, -12227, 60950},
		{22543, -246642, 216317, -12227, 35363},
		{22544, -246935, 216456, -12227, 27131}
	};
	private static final int[][] CORRIDOR_SPAWNS_UPPER =
	{
		{22593, -245888, 211792, -12000, 16384},
		{22593, -246029, 211797, -12016, 16384},
		{22593, -246119, 211796, -12040, 16384},
		{22593, -245754, 211796, -12016, 16384},
		{22593, -245652, 211793, -12032, 16384},
		{22547, -245892, 208165, -12128, 16384},
		{22593, -246032, 208176, -12144, 16384},
		{22593, -246128, 208176, -12168, 16384},
		{22593, -245728, 208176, -12144, 16384},
		{22593, -245632, 208160, -12176, 16384},
		{22549, -242559, 213163, -12168, 16384},
		{22549, -242912, 213166, -12136, 16384},
		{22549, -242676, 213164, -12136, 16384},
		{22549, -243003, 213166, -12152, 16384},
		{22593, -242800, 213152, -12128, 16384},
		{22549, -241168, 218256, -12016, 32767},
		{22549, -241168, 218176, -12008, 32767},
		{22593, -241173, 218104, -12000, 32767},
		{22549, -241168, 218336, -12040, 32767},
		{22549, -241168, 218032, -12008, 32767},
		{22549, -241168, 217936, -12016, 32767},
		{22549, -241168, 217840, -12048, 32767},
		{22549, -238634, 215891, -12264, 16384},
		{22593, -238736, 215888, -12256, 16384},
		{22549, -238545, 215891, -12272, 16384},
		{22549, -238853, 215888, -12264, 16384},
		{22549, -238466, 215890, -12304, 16384},
		{22549, -238939, 215886, -12280, 16384},
		{22549, -242026, 209558, -12320, 16384},
		{22549, -242103, 209561, -12320, 16384},
		{22549, -242180, 209563, -12320, 16384},
		{22549, -242270, 209565, -12320, 16384},
		{22549, -242349, 209565, -12320, 16384},
		{22549, -242494, 209567, -12312, 16384},
		{22549, -242422, 209568, -12312, 16384},
		{22549, -242661, 209569, -12272, 16384},
		{22549, -242560, 209568, -12296, 16384},
		{22549, -243616, 209568, -12320, 16384},
		{22549, -243035, 209559, -12296, 16384},
		{22549, -242920, 209561, -12264, 16384},
		{22593, -242800, 209557, -12256, 16384},
		{22549, -243537, 209567, -12320, 16384},
		{22549, -243375, 209564, -12320, 16384},
		{22549, -243448, 209565, -12320, 16384},
		{22549, -243310, 209564, -12320, 16384},
		{22549, -243239, 209563, -12320, 16384},
		{22549, -243158, 209561, -12312, 16384},
		{22549, -239033, 215892, -12316, 17195},
		{22593, -242969, 206669, -12416, 32764},
		{22593, -242969, 206541, -12392, 32764},
		{22593, -242969, 206349, -12392, 32764},
		{22593, -242969, 206237, -12416, 32764},
		{22593, -242969, 206445, -12384, 32764},
		{22549, -239881, 208591, -12576, 32764},
		{22549, -239881, 208447, -12576, 32764},
		{22549, -239881, 208511, -12576, 32764},
		{22549, -239897, 208351, -12576, 32764},
		{22549, -239897, 208271, -12576, 32764},
		{22549, -239897, 208207, -12568, 32764},
		{22549, -239897, 208143, -12568, 32764},
		{22549, -239897, 207135, -12576, 32764},
		{22549, -239897, 207199, -12576, 32764},
		{22549, -239897, 207263, -12576, 32764},
		{22549, -239897, 207327, -12576, 32764},
		{22549, -239897, 207391, -12576, 32764},
		{22549, -239897, 207455, -12568, 32764},
		{22549, -239897, 207519, -12568, 32764},
		{22549, -238912, 212272, -12400, 16384},
		{22593, -238738, 212263, -12384, 16384},
		{22549, -238464, 212256, -12432, 16384},
		{22549, -238960, 212272, -12416, 16384},
		{22549, -238848, 212272, -12392, 16384},
		{22549, -238640, 212272, -12392, 16384},
		{22549, -238560, 212272, -12400, 16384},
		{22547, -239792, 207824, -12512, 32764},
		{22549, -239840, 207680, -12528, 32764},
		{22549, -239840, 207776, -12512, 32764},
		{22549, -239840, 207888, -12520, 32764},
		{22549, -239840, 207984, -12528, 32764},
		{22549, -239893, 207943, -12528, 32764},
		{22549, -239893, 208036, -12544, 32764},
		{22549, -239890, 207623, -12536, 32764},
		{22549, -239890, 207714, -12520, 32764},
		{22593, -239885, 207822, -12512, 32764}
	};
	private static final int[][] CORRIDOR_SPAWNS_GROUND =
	{
		{22597, -245879, 211967, -12384, 16384},
		{22541, -245436, 211460, -12384, 0},
		{18720, -245888, 213056, -12376, 0},
		{18737, -245696, 212608, -12384, 0},
		{18721, -245920, 212256, -12384, 0},
		{22541, -245997, 208982, -12512, 0},
		{22541, -245733, 209162, -12512, 0},
		{18739, -245888, 211296, -12384, 0},
		{18722, -245888, 211008, -12384, 0},
		{22540, -246513, 207291, -12512, 0},
		{22541, -245909, 208682, -12512, 0},
		{22597, -245882, 208369, -12512, 16384},
		{22540, -245445, 207291, -12512, 0},
		{18740, -245888, 209984, -12464, 0},
		{18729, -245664, 209376, -12512, 0},
		{18723, -246048, 209376, -12512, 0},
		{18724, -246080, 208960, -12512, 0},
		{18730, -246080, 208608, -12512, 0},
		{22546, -243926, 205814, -12768, 0},
		{18742, -245632, 208608, -12512, 0},
		{18741, -245632, 208960, -12512, 0},
		{18731, -246048, 207744, -12512, 0},
		{18744, -246048, 207424, -12512, 0},
		{18725, -245664, 207424, -12512, 0},
		{18743, -245664, 207776, -12512, 0},
		{18745, -245888, 206816, -12512, 0},
		{18777, -241936, 206432, -12744, 0},
		{22596, -242770, 206496, -12760, 32767},
		{22596, -242770, 206384, -12760, 32767},
		{22596, -242770, 206439, -12760, 32767},
		{22544, -242620, 205989, -12768, 0},
		{22541, -243611, 206269, -12768, 0},
		{22541, -243233, 206633, -12768, 0},
		{22597, -243177, 206476, -12768, 16384},
		{22544, -242416, 207081, -12760, 0},
		{22544, -242586, 206990, -12768, 0},
		{18732, -243872, 206464, -12768, 0},
		{18771, -243808, 206112, -12768, 0},
		{22541, -243978, 206204, -12768, 0},
		{22544, -243849, 206017, -12768, 0},
		{18749, -243520, 206464, -12768, 0},
		{18748, -243520, 206112, -12768, 0},
		{22546, -240767, 208544, -12896, 0},
		{18753, -242528, 206336, -12768, 0},
		{18754, -242144, 206336, -12760, 0},
		{18755, -242496, 206592, -12768, 0},
		{18756, -242176, 206656, -12768, 0},
		{18758, -242176, 206976, -12768, 0},
		{22541, -245316, 211237, -12380, 8721},
		{22541, -246223, 211076, -12389, 28836},
		{22540, -245284, 207708, -12518, 1253},
		{22540, -245836, 207628, -12518, 34643},
		{22544, -243850, 206761, -12774, 15146},
		{22546, -244017, 206672, -12774, 37875},
		{22541, -243715, 206647, -12774, 64674},
		{22544, -242237, 206167, -12774, 50344},
		{22544, -242190, 206866, -12774, 6294},
		{22548, -242723, 213682, -12512, 0},
		{22548, -242987, 213922, -12512, 0},
		{22548, -242195, 214102, -12512, 0},
		{22541, -241550, 218362, -12384, 0},
		{22596, -240992, 218032, -12368, 32767},
		{22596, -240992, 218080, -12368, 32767},
		{22596, -240992, 218176, -12368, 32767},
		{22596, -240992, 218128, -12368, 32767},
		{22541, -241994, 218628, -12384, 44063},
		{22541, -242296, 218109, -12384, 38227},
		{22597, -238784, 215696, -12624, 16384},
		{22597, -238696, 215696, -12624, 16384},
		{22596, -238736, 215696, -12624, 16384},
		{22541, -238634, 215102, -12640, 0},
		{22548, -238901, 215168, -12640, 0},
		{22541, -238812, 215564, -12640, 0},
		{22548, -239435, 215333, -12640, 0},
		{22546, -238492, 213146, -12768, 0},
		{22546, -238844, 213326, -12768, 0},
		{22596, -238736, 212400, -12760, 16384},
		{22546, -238932, 212606, -12768, 15087},
		{22546, -238668, 212606, -12768, 0},
		{22597, -238784, 212400, -12752, 16384},
		{22597, -238688, 212400, -12752, 16384},
		{22596, -238688, 212096, -12752, 16384},
		{22596, -238784, 212095, -12752, 16384},
		{22541, -238733, 212038, -12760, 16311},
		{18777, -238736, 211248, -12744, 0},
		{22596, -238736, 212096, -12752, 16384},
		{22541, -238332, 211956, -12768, 30167},
		{22546, -238736, 211527, -12768, 0},
		{22543, -243503, 212324, -12512, 0},
		{22540, -242613, 212489, -12512, 0},
		{22543, -243147, 212852, -12504, 0},
		{22540, -242524, 212786, -12512, 0},
		{22540, -242346, 212588, -12512, 0},
		{22548, -242282, 209234, -12640, 0},
		{22546, -243627, 209944, -12640, 0},
		{22548, -242638, 208640, -12632, 0},
		{22548, -242104, 208838, -12640, 0},
		{22596, -239696, 207776, -12888, 32767},
		{22596, -239696, 207872, -12880, 32767},
		{22596, -239696, 207824, -12888, 32767},
		{22546, -240767, 207452, -12896, 0},
		{22596, -240064, 207888, -12888, 32767},
		{22596, -240016, 207872, -12880, 32767},
		{22596, -240064, 207760, -12888, 32767},
		{22596, -240016, 207776, -12880, 32767},
		{22596, -240048, 207824, -12888, 32767},
		{22548, -239100, 208369, -12896, 0},
		{22548, -239495, 208098, -12896, 40057},
		{22548, -239333, 208181, -12896, 41061},
		{18777, -238848, 207824, -12864, 0},
		{22539, -242826, 210708, -12645, 30758},
		{22548, -243039, 210619, -12645, 2023},
		{22548, -242570, 210599, -12645, 59674},
		{22546, -242791, 210481, -12645, 24959},
		{22548, -242925, 208656, -12631, 21437},
		{22541, -243013, 209077, -12645, 33289},
		{22547, -242759, 209004, -12645, 60509},
		{22548, -243293, 208875, -12637, 29114},
		{22541, -242335, 208954, -12639, 21264},
		{22546, -240636, 208019, -12901, 26402},
		{22548, -239507, 207680, -12901, 59320},
		{22548, -239134, 207369, -12901, 30709},
		{22548, -239376, 207273, -12896, 31526},
		{22548, -239263, 207801, -12901, 26788},
		{22539, -239070, 216742, -12644, 37062},
		{22539, -238494, 216907, -12644, 1821},
		{22546, -238485, 216645, -12644, 49855},
		{22546, -238364, 216961, -12644, 16658},
		{22546, -238901, 217073, -12644, 12726},
		{22548, -238425, 215265, -12647, 27478},
		{22539, -238495, 213307, -12773, 22966},
		{22539, -238720, 213414, -12773, 42528},
		{22546, -239014, 213051, -12773, 2058},
		{22546, -239086, 211737, -12773, 12789},
		{22541, -238688, 211910, -12773, 63943},
		{22546, -238472, 211813, -12773, 21462},
		{22541, -242276, 217966, -12389, 34120},
		{22540, -242085, 218093, -12389, 36123}
	};
	private static final int[][] SCOUTPASS_SPAWNS_UPPER =
	{
		{22593, -249696, 220496, -12128, 0},
		{22549, -249696, 220416, -12120, 0},
		{22549, -249696, 220576, -12128, 0},
		{22593, -249696, 220640, -12128, 0},
		{22549, -249696, 220704, -12128, 0},
		{22593, -249696, 220768, -12128, 0},
		{22549, -249712, 219984, -12072, 0},
		{22549, -249696, 220848, -12128, 0},
		{22549, -249712, 219888, -12080, 0},
		{22549, -249712, 220272, -12088, 0},
		{22549, -249712, 220176, -12072, 0},
		{22593, -249712, 220080, -12064, 0},
		{22549, -249714, 219498, -12128, 0},
		{22593, -249721, 219421, -12128, 0},
		{22549, -249732, 219349, -12128, 0},
		{22593, -249730, 219266, -12128, 0},
		{22549, -249717, 219565, -12128, 0},
		{22593, -249727, 219649, -12128, 0},
		{22549, -249728, 219728, -12120, 0}
	};
	private static final int[][] SCOUTPASS_SPAWNS_GROUND =
	{
		{22538, -248393, 219615, -12448, 0},
		{22539, -250349, 219423, -12440, 0},
		{22539, -250315, 220060, -12448, 0},
		{22539, -250553, 220879, -12448, 0},
		{22538, -250451, 220879, -12448, 0},
		{22538, -250553, 220242, -12448, 0},
		{22540, -250485, 220333, -12448, 0},
		{22538, -250485, 219423, -12448, 0},
		{22540, -250553, 219969, -12440, 0},
		{22540, -250417, 219423, -12448, 0},
		{22540, -248671, 220289, -12453, 53437},
		{22546, -248743, 219598, -12453, 61908},
		{22546, -248778, 219835, -12453, 10079},
		{22539, -248779, 220177, -12453, 757}
	};
	private static final int[][] PREFORT_SPAWNS =
	{
		{22540, -249856, 216729, -12248, 0},
		{22541, -250718, 217437, -12288, 0},
		{22541, -250184, 217107, -12288, 0},
		{22544, -251074, 217317, -12288, 0},
		{22540, -250540, 217497, -12288, 0},
		{22546, -250184, 217287, -12288, 0},
		{22546, -250362, 217347, -12288, 0},
		{22547, -252676, 217137, -12288, 0},
		{22544, -253032, 217137, -12288, 0},
		{22546, -252320, 217617, -12288, 0},
		{22546, -253032, 217437, -12288, 0},
		{22546, -252142, 217437, -12288, 0},
		{22536, -252675, 215945, -12208, 0},
		{22537, -252707, 215897, -12208, 0},
		{22539, -252835, 215945, -12216, 0},
		{22542, -252835, 216009, -12224, 0},
		{22546, -252786, 216450, -12248, 0},
		{22539, -253056, 216510, -12248, 0},
		{22541, -252786, 216675, -12248, 0},
		{22541, -252726, 216525, -12248, 0},
		{22541, -253086, 216555, -12248, 0},
		{22540, -252606, 216645, -12248, 0},
		{22540, -252606, 216540, -12248, 0},
		{22537, -250206, 215762, -12208, 0},
		{22536, -250078, 215906, -12208, 0},
		{22539, -250270, 215874, -12200, 0},
		{22542, -249886, 215794, -12208, 0},
		{22546, -251536, 215728, -12208, 16384},
		{22546, -251345, 215712, -12208, 16384},
		{22546, -251265, 215856, -12208, 16384},
		{22546, -251584, 215856, -12208, 16384},
		{22546, -251425, 215856, -12208, 16384},
		{22540, -249891, 216649, -12248, 0},
		{22547, -250031, 216585, -12248, 0},
		{22548, -250136, 216505, -12248, 0},
		{22544, -251106, 216546, -12248, 0},
		{22544, -251596, 216578, -12248, 0},
		{22548, -249961, 216697, -12248, 0},
		{22547, -251631, 216690, -12248, 0},
		{22542, -251771, 216674, -12248, 0},
		{22544, -251456, 216482, -12248, 0},
		{22543, -251701, 216514, -12248, 0},
		{22546, -251529, 217852, -12328, 0},
		{22538, -251476, 217718, -12296, 0},
		{22537, -251422, 217874, -12328, 0},
		{22546, -252266, 217005, -12288, 0},
		{22546, -252215, 216823, -12256, 0},
		{22538, -252215, 216823, -12256, 0},
		{22537, -252106, 216942, -12280, 0},
		{22546, -251378, 217733, -12301, 2743},
		{22543, -251364, 216662, -12253, 2891},
		{22548, -250154, 216667, -12253, 4364},
		{22541, -249982, 216487, -12253, 57106}
	};
	private static final int[][] FORT_SPAWNS_UPPER =
	{
		{22549, -252747, 213732, -11552, 0},
		{22549, -252779, 213627, -11552, 0},
		{22549, -252781, 213527, -11552, 0},
		{22549, -252736, 213452, -11552, 0},
		{22549, -252665, 213390, -11552, 0},
		{22549, -252337, 213645, -11552, 0},
		{22549, -252342, 213712, -11552, 0},
		{22549, -252383, 213776, -11552, 0},
		{22549, -252443, 213819, -11552, 0},
		{22549, -250462, 213441, -11552, 0},
		{22549, -250269, 213387, -11552, 0},
		{22549, -250378, 213389, -11552, 0},
		{22549, -252480, 213364, -11552, 49152},
		{22549, -252407, 213415, -11552, 49152},
		{22549, -252362, 213478, -11552, 49152},
		{22549, -252569, 213372, -11552, 49152},
		{22549, -252603, 213852, -11552, 16384},
		{22549, -252674, 213818, -11552, 16384},
		{22549, -252339, 213566, -11552, 16384},
		{18778, -252556, 213600, -11544, 16384},
		{22549, -252016, 214446, -11704, 16384},
		{22549, -252096, 214446, -11704, 16384},
		{22549, -252160, 214446, -11704, 16384},
		{22549, -252288, 214446, -11704, 16384},
		{22549, -252224, 214446, -11704, 16384},
		{22549, -250172, 213429, -11552, 16384},
		{18778, -250324, 213588, -11544, 16384},
		{22593, -251326, 214460, -11640, 16384},
		{22549, -250097, 213617, -11552, 16384},
		{22549, -252514, 213830, -11552, 16384},
		{22549, -250110, 213513, -11552, 16384},
		{22549, -250534, 213627, -11552, 32767},
		{22549, -250151, 213713, -11552, 16384},
		{22549, -250543, 213547, -11552, 32767},
		{22549, -250515, 213689, -11552, 32767},
		{22549, -250432, 213808, -11552, 16384},
		{22549, -250485, 213745, -11552, 32767},
		{22549, -250368, 213824, -11552, 16384},
		{22549, -250222, 213775, -11552, 16384},
		{22549, -250288, 213824, -11552, 16384},
		{22593, -251550, 214460, -11640, 16384},
		{22593, -251730, 214460, -11640, 16384},
		{22549, -250644, 214446, -11704, 16384},
		{22549, -250832, 214446, -11704, 16384},
		{22549, -250704, 214446, -11704, 16384},
		{22549, -250768, 214446, -11704, 16384},
		{22549, -250896, 214446, -11704, 16384},
		{22537, -251434, 214460, -11640, 16384},
		{22593, -251157, 214564, -11640, 16384},
		{22593, -251154, 214222, -11640, 16384},
		{22593, -251720, 214564, -11640, 16384},
		{22593, -251718, 214222, -11640, 16384},
		{22593, -251722, 214290, -11640, 16384},
		{22593, -251633, 214290, -11640, 16384},
		{22593, -251540, 214290, -11640, 16384},
		{22593, -251331, 214290, -11640, 16384},
		{22593, -251147, 214290, -11640, 16384},
		{22593, -251237, 214290, -11640, 16384},
		{22593, -251146, 214460, -11640, 16384},
		{22593, -251643, 214460, -11640, 16384},
		{22593, -251237, 214460, -11640, 16384},
		{22549, -252407, 213415, -11552, 3865},
		{22549, -252480, 213364, -11552, 49152}
	};
	private static final int[][] FORT_SPAWNS_GROUND =
	{
		{22596, -251494, 214912, -12088, 16384},
		{22596, -251280, 214911, -12088, 16384},
		{22596, -251565, 214912, -12088, 16384},
		{22549, -251921, 213570, -12080, 16384},
		{22597, -251929, 213686, -12080, 16384},
		{22536, -251264, 214224, -12080, 16384},
		{22596, -250803, 213178, -12056, 32767},
		{22547, -250732, 213177, -12024, 32767},
		{22596, -250797, 213103, -12048, 32767},
		{22596, -250799, 213260, -12048, 32767},
		{22549, -251114, 213570, -12080, 16384},
		{22547, -251522, 213558, -12080, 16384},
		{22593, -251424, 213565, -12080, 16384},
		{22547, -251332, 213563, -12080, 16384},
		{22593, -251264, 213566, -12080, 16384},
		{22593, -251183, 213564, -12080, 16384},
		{22549, -251781, 213572, -12080, 16384},
		{22593, -251696, 213566, -12080, 16384},
		{22593, -251613, 213561, -12080, 16384},
		{22597, -251114, 213686, -12080, 16384},
		{22549, -251854, 213572, -12080, 16384},
		{22549, -251039, 213570, -12080, 16384},
		{22549, -250960, 213573, -12080, 16384},
		{22597, -250966, 213689, -12080, 16384},
		{22597, -251040, 213689, -12080, 16384},
		{22597, -251261, 213681, -12080, 16384},
		{22597, -251183, 213684, -12080, 16384},
		{22597, -251853, 213683, -12080, 16384},
		{22597, -251774, 213683, -12080, 16384},
		{22597, -251693, 213683, -12080, 16384},
		{22597, -251606, 213684, -12080, 16384},
		{22596, -251351, 214912, -12088, 16384},
		{22596, -251338, 213683, -12080, 16384},
		{22596, -251522, 213683, -12080, 16384},
		{22596, -251430, 213684, -12080, 16384},
		{22537, -251431, 213567, -12080, 16384},
		{22537, -251289, 213567, -12080, 16384},
		{22536, -251373, 214220, -12080, 16384},
		{22536, -251482, 214219, -12080, 16384},
		{22536, -251600, 214224, -12080, 16384},
		{22537, -251566, 213565, -12088, 15826},
		{22593, -251495, 214792, -12080, 16384},
		{22593, -251281, 214786, -12080, 16384},
		{22593, -251352, 214787, -12080, 16384},
		{22593, -251568, 214789, -12080, 16384},
		{22596, -251441, 214672, -12080, 16384},
		{22547, -251427, 214790, -12080, 16384},
		{22596, -251281, 214671, -12080, 16384},
		{22596, -251361, 214671, -12080, 16384},
		{22596, -251505, 214672, -12080, 16384},
		{22596, -251569, 214672, -12080, 16384},
		{22596, -251424, 214912, -12088, 16384},
		{22537, -251262, 214226, -12080, 18077},
		{22596, -252094, 213194, -12048, 0},
		{22547, -252210, 213189, -11992, 0},
		{22596, -252096, 213120, -12048, 0},
		{22596, -252089, 213271, -12048, 0}
	};
	private static int[][] FIVETR =
	{
		{-250123, 207338, -11966},
		{-249948, 207338, -11951},
		{-249773, 207338, -11946},
		{-250687, 207338, -11968},
		{-250862, 207338, -11963},
		{-251037, 207338, -11958}
	};
	private static int[][] FORTR =
	{
		{-251037, 207563, -11954},
		{-250862, 207563, -11949},
		{-250687, 207563, -11944},
		{-250123, 207563, -11966},
		{-249948, 207563, -11961},
		{-249773, 207563, -11956}
	};
	private static int[][] FRETR =
	{
		{22547, -250123, 207738, -11939},
		{22546, -249948, 207738, -11934},
		{22546, -249773, 207738, -11949},
		{22547, -250687, 207738, -11909},
		{22546, -250862, 207738, -11904},
		{22546, -251037, 207738, -11889}
	};
	private static int[][] TWOTR =
	{
		{-251562, 207913, -11969},
		{-251387, 207913, -11964},
		{-251212, 207913, -11959},
		{-251037, 207913, -11954},
		{-250862, 207913, -11949},
		{-250687, 207913, -11944},
		{-250123, 207913, -11909},
		{-249948, 207913, -11904},
		{-249773, 207913, -11899},
		{-249598, 207913, -11894},
		{-249423, 207913, -11894},
		{-249248, 207913, -11889}
	};
	private static int[][] ONETR =
	{
		{-251562, 208088, -11969},
		{-251387, 208088, -11964},
		{-251212, 208088, -11959},
		{-251037, 208088, -11954},
		{-250862, 208088, -11949},
		{-250687, 208088, -11944},
		{-250123, 208088, -11909},
		{-249948, 208088, -11904},
		{-249773, 208088, -11899},
		{-249598, 208088, -11894},
		{-249423, 208088, -11894},
		{-249248, 208088, -11889}
	};
	// @formatter:on
	
	// Initialization at 6:30 am on Wednesday and Saturday
	private static final int RESET_HOUR = 6;
	private static final int RESET_MIN = 30;
	private static final int RESET_DAY_1 = 4;
	private static final int RESET_DAY_2 = 7;
	
	protected class teleCoord
	{
		int instanceId;
		int x;
		int y;
		int z;
	}
	
	public SeedOfDestruction()
	{
		addStartNpc(ALENOS);
		addTalkId(ALENOS);
		addStartNpc(TELEPORT);
		addTalkId(TELEPORT);
		addFirstTalkId(TELEPORT);
		addAttackId(OBELISK);
		addSpawnId(OBELISK);
		addKillId(OBELISK);
		addSpawnId(POWERFUL_DEVICE);
		addKillId(POWERFUL_DEVICE);
		addSpawnId(THRONE_POWERFUL_DEVICE);
		addKillId(THRONE_POWERFUL_DEVICE);
		addAttackId(TIAT);
		addKillId(TIAT);
		addKillId(SPAWN_DEVICE);
		addKillId(NAEZD);
		
		for (int mobId : MOB_IDS)
		{
			addKillId(mobId);
		}
		
		addEnterZoneId(25253);
	}
	
	private boolean checkConditions(L2PcInstance player)
	{
		if (DEBUG || player.isGM())
		{
			LOGGER.info("SoD is now in test mode - DEBUG OR GM PLAYER");
			return true;
		}
		if (player.getParty() == null)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_ARE_NOT_CURRENTLY_IN_A_PARTY_SO_YOU_CANNOT_ENTER));
			return false;
		}
		final L2CommandChannel channel = player.getParty().getCommandChannel();
		if (channel == null)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_CANNOT_ENTER_BECAUSE_YOU_ARE_NOT_ASSOCIATED_WITH_THE_CURRENT_COMMAND_CHANNEL));
			return false;
		}
		else if (channel.getLeader() != player)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER));
			return false;
		}
		else if ((channel.getMemberCount() < MIN_PLAYERS) || (channel.getMemberCount() > MAX_PLAYERS))
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_CANNOT_ENTER_DUE_TO_THE_PARTY_HAVING_EXCEEDED_THE_LIMIT));
			return false;
		}
		for (L2PcInstance channelMember : channel.getMembers())
		{
			if (channelMember.getLevel() < Config.MIN_TIAT_LEVEL)
			{
				final SystemMessage sm = (SystemMessage.getSystemMessage(SystemMessageId.C1_S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY));
				sm.addPcName(channelMember);
				channel.broadcastPacket(sm);
				return false;
			}
			if (!Util.checkIfInRange(1000, player, channelMember, true))
			{
				final SystemMessage sm = (SystemMessage.getSystemMessage(SystemMessageId.C1_IS_IN_A_LOCATION_WHICH_CANNOT_BE_ENTERED_THEREFORE_IT_CANNOT_BE_PROCESSED));
				sm.addPcName(channelMember);
				channel.broadcastPacket(sm);
				return false;
			}
			final Long reentertime = InstanceManager.getInstance().getInstanceTime(channelMember.getObjectId(), INSTANCE_ID);
			if (System.currentTimeMillis() < reentertime)
			{
				final SystemMessage sm = (SystemMessage.getSystemMessage(SystemMessageId.C1_MAY_NOT_RE_ENTER_YET));
				sm.addPcName(channelMember);
				channel.broadcastPacket(sm);
				return false;
			}
		}
		return true;
	}
	
	private int checkworld(L2PcInstance player)
	{
		final InstanceWorld checkworld = InstanceManager.getInstance().getPlayerWorld(player);
		if (checkworld != null)
		{
			if (!(checkworld instanceof SODWorld))
			{
				return 0;
			}
			return 1;
		}
		return 2;
	}
	
	protected int enterInstance(L2PcInstance player, teleCoord teleto)
	{
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		final int inst = checkworld(player);
		if (inst == 0)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON));
			return 0;
		}
		else if (inst == 1)
		{
			teleto.instanceId = world.getInstanceId();
			teleportplayer(player, teleto, (SODWorld) world);
			return world.getInstanceId();
		}
		// New instance
		else
		{
			if (!checkConditions(player))
			{
				return 0;
			}
			world = new SODWorld(System.currentTimeMillis() + 5400000);
			world.setInstance(InstanceManager.getInstance().createDynamicInstance(INSTANCE_ID));
			InstanceManager.getInstance().addWorld(world);
			spawnState((SODWorld) world);
			final int instanceId = world.getInstanceId();
			for (L2DoorInstance door : InstanceManager.getInstance().getInstance(instanceId).getDoors())
			{
				if (CommonUtil.contains(ATTACKABLE_DOORS, door.getId()))
				{
					door.setIsAttackableDoor(true);
				}
			}
			LOGGER.info("Seed of Destruction started " + INSTANCE_ID + " Instance: " + instanceId + " created by player: " + player.getName());
			((SODWorld) world).ZoneWaitForTP = true;
			teleto.instanceId = instanceId;
			
			if (player.getParty() == null)
			{
				player.sendMessage("Welcome to Seed of Destruction. Time to finish the instance is 130 minutes.");
				InstanceManager.getInstance().setInstanceTime(player.getObjectId(), INSTANCE_ID, (System.currentTimeMillis()));
				teleportplayer(player, teleto, (SODWorld) world);
				removeBuffs(player);
				world.addAllowed(player);
			}
			else if (player.getParty().getCommandChannel() != null)
			{
				for (L2PcInstance channelMember : player.getParty().getCommandChannel().getMembers())
				{
					player.sendMessage("Welcome to Seed of Destruction. Time to finish the instance is 130 minutes.");
					InstanceManager.getInstance().setInstanceTime(channelMember.getObjectId(), INSTANCE_ID, (System.currentTimeMillis()));
					teleportplayer(channelMember, teleto, (SODWorld) world);
					removeBuffs(channelMember);
					world.addAllowed(channelMember);
				}
			}
			else
			{
				for (L2PcInstance partyMember : player.getParty().getMembers())
				{
					player.sendMessage("Welcome to Seed of Destruction. Time to finish the instance is 130 minutes.");
					InstanceManager.getInstance().setInstanceTime(partyMember.getObjectId(), INSTANCE_ID, (System.currentTimeMillis()));
					teleportplayer(partyMember, teleto, (SODWorld) world);
					removeBuffs(partyMember);
					world.addAllowed(partyMember);
				}
			}
			return instanceId;
		}
	}
	
	private static void removeBuffs(L2Character ch)
	{
		ch.stopAllEffectsExceptThoseThatLastThroughDeath();
		if (ch.hasSummon())
		{
			ch.getSummon().stopAllEffectsExceptThoseThatLastThroughDeath();
		}
	}
	
	private void teleportplayerEnergy(L2PcInstance player, teleCoord teleto)
	{
		player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		player.setInstanceId(teleto.instanceId);
		player.teleToLocation(teleto.x, teleto.y, teleto.z);
		return;
	}
	
	private void teleportplayer(L2PcInstance player, teleCoord teleto, SODWorld world)
	{
		player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		player.setInstanceId(teleto.instanceId);
		player.teleToLocation(teleto.x, teleto.y, teleto.z);
		final L2Summon pet = player.getSummon();
		if (pet != null)
		{
			pet.setInstanceId(teleto.instanceId);
			pet.teleToLocation(teleto.x, teleto.y, teleto.z);
		}
	}
	
	protected boolean checkKillProgress(L2Npc mob, SODWorld world)
	{
		if (world.npcList.containsKey(mob))
		{
			world.npcList.put(mob, true);
		}
		for (boolean isDead : world.npcList.values())
		{
			if (!isDead)
			{
				return false;
			}
		}
		return true;
	}
	
	protected void spawnState(SODWorld world)
	{
		world.npcList.clear();
		switch (world.getStatus())
		{
			case 0:
			{
				spawn(world, ENTRANCE_GROUND_SPAWNS_1, true, false);
				spawn(world, ENTRANCE_GROUND_SPAWNS_2, false, false);
				spawn(world, ENTRANCE_UPPER_SPAWNS, false, true);
				world.closeDoor(SCOUTPASS_DOOR);
				world.closeDoor(THRONE_DOOR);
				break;
			}
			case 1:
			{
				final ExShowScreenMessage message1 = new ExShowScreenMessage(NpcStringId.THE_ENEMIES_HAVE_ATTACKED_EVERYONE_COME_OUT_AND_FIGHT_URGH, 5, 5000);
				sendScreenMessage(world, message1);
				for (int i : ENTRANCE_ROOM_DOORS)
				{
					world.openDoor(i);
				}
				spawn(world, SQUARE_SPAWNS_STATIC, false, true);
				spawn(world, SQUARE_SPAWNS_MAIN, true, false);
				break;
			}
			case 2:
			case 3:
			{
				// handled elsewhere
				return;
			}
			case 4:
			{
				final ExShowScreenMessage message2 = new ExShowScreenMessage(NpcStringId.OBELISK_HAS_COLLAPSED_DON_T_LET_THE_ENEMIES_JUMP_AROUND_WILDLY_ANYMORE, 5, 5000);
				sendScreenMessage(world, message2);
				for (int i : SQUARE_DOORS)
				{
					world.openDoor(i);
				}
				spawn(world, CORRIDOR_SPAWNS_UPPER, false, true);
				spawn(world, CORRIDOR_SPAWNS_GROUND, false, false);
				world.killedDevice = 0;
				break;
			}
			case 5:
			{
				world.openDoor(SCOUTPASS_DOOR);
				spawn(world, SQUARE_SPAWNS_HALF, false, false);
				spawn(world, SCOUTPASS_SPAWNS_UPPER, false, true);
				spawn(world, SCOUTPASS_SPAWNS_GROUND, false, false);
				spawn(world, PREFORT_SPAWNS, false, false);
				final ExShowScreenMessage message3 = new ExShowScreenMessage(NpcStringId.ENEMIES_ARE_TRYING_TO_DESTROY_THE_FORTRESS_EVERYONE_DEFEND_THE_FORTRESS, 5, 5000);
				sendScreenMessage(world, message3);
				spawn(world, FORT_SPAWNS_UPPER, false, true);
				spawn(world, FORT_SPAWNS_GROUND, false, false);
				world.killedDevice = 0;
				break;
			}
			case 6:
			{
				world.openDoor(THRONE_DOOR);
				break;
			}
			case 7:
			{
				// handled elsewhere
				return;
			}
			case 8:
			{
				break;
			}
			case 9:
			{
				// instance end
				break;
			}
		}
		world.incStatus();
	}
	
	protected void spawn(SODWorld world, int[][] spawnTable, boolean addToKillTable, boolean isImmobilized)
	{
		for (int[] mob : spawnTable)
		{
			// traps
			if ((mob[0] >= 18720) && (mob[0] <= 18774))
			{
				Skill skill = null;
				if (mob[0] <= 18728)
				{
					skill = TRAP_HOLD.getSkill();
				}
				else if (mob[0] <= 18736)
				{
					skill = TRAP_STUN.getSkill();
				}
				else
				// if (mob[0] <= 18774)
				{
					skill = TRAP_DAMAGE.getSkill();
				}
				addTrap(mob[0], mob[1], mob[2], mob[3], mob[4], skill, world.getInstanceId());
				continue;
			}
			
			final L2Npc npc = addSpawn(mob[0], mob[1], mob[2], mob[3], mob[4], false, 0, false, world.getInstanceId());
			if (addToKillTable)
			{
				world.npcList.put(npc, false);
			}
			npc.setIsImmobilized(TIAT == mob[0] ? true : isImmobilized);
			npc.setRandomWalking(false);
			if (npc.isAttackable())
			{
				((L2Attackable) npc).setSeeThroughSilentMove(true);
			}
			if (mob[0] == SPAWN_DEVICE)
			{
				npc.disableCoreAI(true);
				startQuestTimer("Spawn", 30000, npc, null, true); // Edit Spawn Mob Time 30000 = 30 sec
			}
		}
	}
	
	protected void setInstanceTimeRestrictions(SODWorld world)
	{
		final Calendar reenter = Calendar.getInstance();
		reenter.set(Calendar.MINUTE, RESET_MIN);
		reenter.set(Calendar.HOUR_OF_DAY, RESET_HOUR);
		// if time is >= RESET_HOUR - roll to the next day
		if (reenter.getTimeInMillis() <= System.currentTimeMillis())
		{
			reenter.add(Calendar.DAY_OF_MONTH, 1);
		}
		if (reenter.get(Calendar.DAY_OF_WEEK) <= RESET_DAY_1)
		{
			while (reenter.get(Calendar.DAY_OF_WEEK) != RESET_DAY_1)
			{
				reenter.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		else
		{
			while (reenter.get(Calendar.DAY_OF_WEEK) != RESET_DAY_2)
			{
				reenter.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		
		final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_FEEL_REFRESHED_EVERYTHING_APPEARS_CLEAR);
		sm.addString(InstanceManager.getInstance().getInstanceIdName(INSTANCE_ID));
		
		// set instance reenter time for all allowed players
	}
	
	private void sendScreenMessage(SODWorld world, ExShowScreenMessage message)
	{
		for (L2PcInstance player : world.getAllowed())
		{
			if (player != null)
			{
				player.sendPacket(message);
			}
		}
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isPet)
	{
		return super.onSkillSee(npc, caster, skill, targets, isPet);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		npc.disableCoreAI(true);
		return super.onSpawn(npc);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isPet, Skill skill)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof SODWorld)
		{
			final SODWorld world = (SODWorld) tmpworld;
			if ((world.getStatus() == 2) && (npc.getId() == OBELISK))
			{
				world.setStatus(4);
				spawn(world, SQUARE_SPAWNS_MAIN, false, false);
			}
			else if ((world.getStatus() == 3) && (npc.getId() == OBELISK))
			{
				world.setStatus(4);
				spawn(world, SQUARE_SPAWNS_HALF, false, false);
			}
			else if ((world.getStatus() <= 8) && (npc.getId() == TIAT))
			{
				if (npc.getCurrentHp() < (npc.getMaxHp() / 2))
				{
					if (_numAtk < 1)
					{
						final ExShowScreenMessage message4 = new ExShowScreenMessage(NpcStringId.COME_OUT_WARRIORS_PROTECT_SEED_OF_DESTRUCTION, 5, 5000);
						sendScreenMessage(world, message4);
						world._tiat.doCast(SkillData.getInstance().getSkill(5818, 1));
						world._tiat.doCast(SkillData.getInstance().getSkill(181, 1));
						world.deviceSpawnedMobCount = 0;
						spawn(world, THRONE_PORTALS, false, true);
						_numAtk++;
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc != null ? npc : player);
		if (tmpworld instanceof SODWorld)
		{
			final SODWorld world = (SODWorld) tmpworld;
			if (event.equals("ThroneSpawn"))
			{
				world._tiat = addSpawn(TIAT, -250400, 207271, -11961, 16285, false, 0, false, world.getInstanceId());
				world._tiat.setRandomWalking(false);
				world._naezdTR1 = addSpawn(NAEZD, -250154, 207203, -11970, 33818, false, 0, false, world.getInstanceId());
				world._naezdTR1.setRandomWalking(false);
				world._naezdTR2 = addSpawn(NAEZD, -250209, 206941, -11966, 27379, false, 0, false, world.getInstanceId());
				world._naezdTR2.setRandomWalking(false);
				world._naezdTL1 = addSpawn(NAEZD, -250652, 207203, -11970, 0, false, 0, false, world.getInstanceId());
				world._naezdTL1.setRandomWalking(false);
				world._naezdTL2 = addSpawn(NAEZD, -250597, 206941, -11966, 6867, false, 0, false, world.getInstanceId());
				world._naezdTL2.setRandomWalking(false);
				
				for (int i = 0; i < 12; i++)
				{
					final L2Npc npc1 = addSpawn(22543, ONETR[i][0], ONETR[i][1], ONETR[i][2], 16285, false, 0, false, world.getInstanceId());
					npc1.setRandomWalking(false);
					world._mags.add(npc1);
					
					final L2Npc npc2 = addSpawn(22541, TWOTR[i][0], TWOTR[i][1], TWOTR[i][2], 16285, false, 0, false, world.getInstanceId());
					npc2.setRandomWalking(false);
				}
				for (int i = 0; i < 6; i++)
				{
					final L2Npc npc3 = addSpawn(FRETR[i][0], FRETR[i][1], FRETR[i][2], FRETR[i][3], 16285, false, 0, false, world.getInstanceId());
					npc3.setRandomWalking(false);
					
					final L2Npc npc4 = addSpawn(22536, FORTR[i][0], FORTR[i][1], FORTR[i][2], 16285, false, 0, false, world.getInstanceId());
					npc4.setRandomWalking(false);
					
					final L2Npc npc5 = addSpawn(22537, FIVETR[i][0], FIVETR[i][1], FIVETR[i][2], 16285, false, 0, false, world.getInstanceId());
					npc5.setRandomWalking(false);
				}
				
				spawn(world, FORT_PORTALS, false, true);
			}
			else if (event.equals("KillTiatPart1"))
			{
				playMovie(world, Movie.SC_BOSS_TIAT_ENDING_SUCCES);
				InstanceManager.getInstance().getInstance(world.getInstanceId()).getNpcs().forEach(L2Npc::deleteMe);
			}
			else if (event.equals("Spawn"))
			{
				if (world.getStatus() <= 7)
				{
					final L2PcInstance target = world.getAllowed().stream().findAny().get();
					if ((world.deviceSpawnedMobCount < MAX_DEVICE_SPAWNED_MOB_COUNT) && (target != null) && ((npc != null) && (target.getInstanceId() == npc.getInstanceId())) && !target.isDead())
					{
						final L2Attackable mob = (L2Attackable) addSpawn(SPAWN_MOB_IDS[Rnd.get(SPAWN_MOB_IDS.length)], npc.getSpawn().getX(), npc.getSpawn().getY(), npc.getSpawn().getZ(), npc.getSpawn().getHeading(), false, 0, false, world.getInstanceId());
						world.deviceSpawnedMobCount++;
						mob.setSeeThroughSilentMove(true);
						mob.setRunning();
						mob.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, MOVE_TO_TIAT);
					}
				}
			}
		}
		return "";
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		if (npc.getId() == SPAWN_DEVICE)
		{
			cancelQuestTimer("Spawn", npc, null);
			return "";
		}
		final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
		if (tmpworld instanceof SODWorld)
		{
			final SODWorld world = (SODWorld) tmpworld;
			if (world.getStatus() == 1)
			{
				if (checkKillProgress(npc, world))
				{
					spawnState(world);
				}
			}
			else if (world.getStatus() == 2)
			{
				if (checkKillProgress(npc, world))
				{
					world.incStatus();
				}
			}
			else if ((world.getStatus() == 4) && (npc.getId() == OBELISK))
			{
				spawnState(world);
			}
			else if ((world.getStatus() == 5) && (npc.getId() == POWERFUL_DEVICE))
			{
				world.killedDevice++;
				if (world.killedDevice >= 3)
				{
					spawnState(world);
				}
			}
			else if ((world.getStatus() == 6) && (npc.getId() == THRONE_POWERFUL_DEVICE))
			{
				world.killedDevice++;
				if (world.killedDevice >= 2)
				{
					spawnState(world);
				}
			}
			else if (world.getStatus() >= 7)
			{
				if (npc.getId() == TIAT)
				{
					world.incStatus();
					SoDManager.getInstance().increaseSoDTiatKilled();
					for (L2Npc mob : InstanceManager.getInstance().getInstance(world.getInstanceId()).getNpcs())
					{
						mob.deleteMe();
					}
					final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
					inst.setDuration(EXIT_TIME * 60000);
					inst.setEmptyDestroyTime(0);
					startQuestTimer("KillTiatPart1", 250, world._tiat, null);
					_numAtk = 0;
					cancelQuestTimer("Spawn", npc, null);
					return "";
				}
				else if (npc.getId() == NAEZD)
				{
					final L2Attackable mob = (L2Attackable) addSpawn(npc.getId(), npc.getSpawn().getX(), npc.getSpawn().getY(), npc.getSpawn().getZ(), npc.getSpawn().getHeading(), false, 0, false, world.getInstanceId());
					mob.setRandomWalking(false);
					mob.setSeeThroughSilentMove(true);
					mob.setIsRaidMinion(true);
				}
				else if (npc.getId() == SPAWN_DEVICE)
				{
					LOGGER.info("portal kill");
					final Skill skilla = SkillData.getInstance().getSkill(5699, 7);
					skilla.applyEffects(world._tiat, world._tiat);
					final Skill skillb = SkillData.getInstance().getSkill(5700, 7);
					skillb.applyEffects(world._tiat, world._tiat);
					cancelQuestTimer("Spawn", npc, null);
					return "";
				}
			}
		}
		return "";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final int npcId = npc.getId();
		QuestState qs = player.getQuestState(getName());
		if (qs == null)
		{
			qs = newQuestState(player);
		}
		if (npcId == ALENOS)
		{
			if (SoDManager.getInstance().getSoDState() == 1)
			{
				final teleCoord tele = new teleCoord();
				tele.x = -242759;
				tele.y = 219981;
				tele.z = -9986;
				enterInstance(player, tele);
			}
			else if (SoDManager.getInstance().getSoDState() == 2)
			{
				final teleCoord tele = new teleCoord();
				tele.x = -245800;
				tele.y = 220488;
				tele.z = -12112;
				tele.instanceId = 0;
				teleportplayerEnergy(player, tele);
			}
		}
		else if (npcId == TELEPORT)
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof SODWorld)
			{
				final SODWorld world = (SODWorld) tmpworld;
				final teleCoord tele = new teleCoord();
				tele.x = -245802;
				tele.y = 220528;
				tele.z = -12104;
				tele.instanceId = player.getInstanceId();
				teleportplayer(player, tele, world);
			}
		}
		return "";
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (DEBUG)
		{
			LOGGER.info("FirstTalkEvent: NPC " + npc.getId() + ".");
		}
		
		if (npc.getId() == TELEPORT)
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc);
			if (tmpworld instanceof SODWorld)
			{
				final SODWorld world = (SODWorld) tmpworld;
				if (world.getStatus() == 2)
				{
					return "32601-1.htm";
				}
				if ((world.getStatus() > 2) && (world.getStatus() < 9))
				{
					return "32601-2.htm";
				}
				if (world.getStatus() == 9)
				{
					return "32601-3.htm";
				}
			}
			npc.showChatWindow(player);
			return null;
		}
		return "";
	}
	
	@Override
	public String onEnterZone(L2Character character, L2ZoneType zone)
	{
		if (character.isPlayer())
		{
			final InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(character);
			if (tmpworld instanceof SODWorld)
			{
				final SODWorld world = (SODWorld) tmpworld;
				if ((zone.getId() == 25253) && world.ZoneWaitForTP)
				{
					startQuestTimer("ThroneSpawn", 55000, null, (L2PcInstance) character);
					playMovie(world, Movie.SC_BOSS_TIAT_OPENING);
					world.ZoneWaitForTP = false;
				}
			}
		}
		return super.onEnterZone(character, zone);
	}
}
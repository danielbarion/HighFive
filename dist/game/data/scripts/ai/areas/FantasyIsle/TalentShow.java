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
package ai.areas.FantasyIsle;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.GameTimeController;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.PlaySound;

import ai.AbstractNpcAI;

/**
 * Talent Show AI.
 * @author Kerberos
 */
public final class TalentShow extends AbstractNpcAI
{
	private static int MC = 32433;
	// @formatter:off
	private static int[] SINGERS =
	{
		32431, 32432
	};
	private static int[] CIRCUS =
	{
		32442, 32443, 32444, 32445, 32446
	};
	private static int[] INDIVIDUALS =
	{
		32439, 32440, 32441
	};
	private static int[] SHOWSTUFF =
	{
		32424, 32425, 32426, 32427, 32428
	};
	// @formatter:on
	private static boolean IS_STARTED = false;
	private static NpcStringId[] MESSAGES =
	{
		NpcStringId.HOW_COME_PEOPLE_ARE_NOT_HERE_WE_ARE_ABOUT_TO_START_THE_SHOW_HMM,
		NpcStringId.UGH_I_HAVE_BUTTERFLIES_IN_MY_STOMACH_THE_SHOW_STARTS_SOON,
		NpcStringId.THANK_YOU_ALL_FOR_COMING_HERE_TONIGHT,
		NpcStringId.IT_IS_AN_HONOR_TO_HAVE_THE_SPECIAL_SHOW_TODAY,
		NpcStringId.FANTASY_ISLE_IS_FULLY_COMMITTED_TO_YOUR_HAPPINESS,
		NpcStringId.NOW_I_D_LIKE_TO_INTRODUCE_THE_MOST_BEAUTIFUL_SINGER_IN_ADEN_PLEASE_WELCOME_LEYLA_MIRA,
		NpcStringId.HERE_SHE_COMES,
		NpcStringId.THANK_YOU_VERY_MUCH_LEYLA,
		NpcStringId.NOW_WE_RE_IN_FOR_A_REAL_TREAT,
		NpcStringId.JUST_BACK_FROM_THEIR_WORLD_TOUR_PUT_YOUR_HANDS_TOGETHER_FOR_THE_FANTASY_ISLE_CIRCUS,
		NpcStringId.COME_ON_EVERYONE,
		NpcStringId.DID_YOU_LIKE_IT_THAT_WAS_SO_AMAZING,
		NpcStringId.NOW_WE_ALSO_INVITED_INDIVIDUALS_WITH_SPECIAL_TALENTS,
		NpcStringId.LET_S_WELCOME_THE_FIRST_PERSON_HERE,
		NpcStringId.OH,
		NpcStringId.OKAY_NOW_HERE_COMES_THE_NEXT_PERSON_COME_ON_UP_PLEASE,
		NpcStringId.OH_IT_LOOKS_LIKE_SOMETHING_GREAT_IS_GOING_TO_HAPPEN_RIGHT,
		NpcStringId.OH_MY,
		NpcStringId.THAT_S_G_GREAT_NOW_HERE_COMES_THE_LAST_PERSON,
		NpcStringId.NOW_THIS_IS_THE_END_OF_TODAY_S_SHOW,
		NpcStringId.HOW_WAS_IT_I_HOPE_YOU_ALL_ENJOYED_IT,
		NpcStringId.PLEASE_REMEMBER_THAT_FANTASY_ISLE_IS_ALWAYS_PLANNING_A_LOT_OF_GREAT_SHOWS_FOR_YOU,
		NpcStringId.WELL_I_WISH_I_COULD_CONTINUE_ALL_NIGHT_LONG_BUT_THIS_IS_IT_FOR_TODAY_THANK_YOU,
		NpcStringId.WE_LOVE_YOU
	};
	
	private class ShoutInfo
	{
		private final NpcStringId _npcStringId;
		private final String _nextEvent;
		private final int _time;
		
		public ShoutInfo(NpcStringId npcStringId, String nextEvent, int time)
		{
			_npcStringId = npcStringId;
			_nextEvent = nextEvent;
			_time = time;
		}
		
		/**
		 * @return the _npcStringId
		 */
		public NpcStringId getNpcStringId()
		{
			return _npcStringId;
		}
		
		/**
		 * @return the _nextEvent
		 */
		public String getNextEvent()
		{
			return _nextEvent;
		}
		
		/**
		 * @return the _time
		 */
		public int getTime()
		{
			return _time;
		}
	}
	
	private class WalkInfo
	{
		private final Location _charPos;
		private final String _nextEvent;
		private final int _time;
		
		public WalkInfo(Location charPos, String nextEvent, int time)
		{
			_charPos = charPos;
			_nextEvent = nextEvent;
			_time = time;
		}
		
		/**
		 * @return the _charPos
		 */
		public Location getCharPos()
		{
			return _charPos;
		}
		
		/**
		 * @return the _nextEvent
		 */
		public String getNextEvent()
		{
			return _nextEvent;
		}
		
		/**
		 * @return the _time
		 */
		public int getTime()
		{
			return _time;
		}
	}
	
	private static final Map<String, ShoutInfo> TALKS = new HashMap<>();
	private static final Map<String, WalkInfo> WALKS = new HashMap<>();
	
	private TalentShow()
	{
		addSpawnId(32433, 32431, 32432, 32442, 32443, 32444, 32445, 32446, 32424, 32425, 32426, 32427, 32428);
		load();
		scheduleTimer();
	}
	
	private void load()
	{
		// TODO put this stuff in Routes.xml
		TALKS.put("1", new ShoutInfo(MESSAGES[1], "2", 1000));
		TALKS.put("2", new ShoutInfo(MESSAGES[2], "3", 6000));
		TALKS.put("3", new ShoutInfo(MESSAGES[3], "4", 4000));
		TALKS.put("4", new ShoutInfo(MESSAGES[4], "5", 5000));
		TALKS.put("5", new ShoutInfo(MESSAGES[5], "6", 3000));
		TALKS.put("8", new ShoutInfo(MESSAGES[9], "9", 5000));
		TALKS.put("9", new ShoutInfo(MESSAGES[10], "10", 5000));
		TALKS.put("12", new ShoutInfo(MESSAGES[12], "13", 5000));
		TALKS.put("13", new ShoutInfo(MESSAGES[13], "14", 5000));
		TALKS.put("15", new ShoutInfo(MESSAGES[14], "16", 5000));
		TALKS.put("16", new ShoutInfo(MESSAGES[15], "17", 5000));
		TALKS.put("18", new ShoutInfo(MESSAGES[17], "19", 5000));
		TALKS.put("19", new ShoutInfo(MESSAGES[18], "20", 5000));
		TALKS.put("21", new ShoutInfo(MESSAGES[19], "22", 5000));
		TALKS.put("22", new ShoutInfo(MESSAGES[20], "23", 400));
		TALKS.put("25", new ShoutInfo(MESSAGES[21], "26", 5000));
		TALKS.put("26", new ShoutInfo(MESSAGES[22], "27", 5400));
		
		WALKS.put("npc1_1", new WalkInfo(new Location(-56546, -56384, -2008, 0), "npc1_2", 1200));
		WALKS.put("npc1_2", new WalkInfo(new Location(-56597, -56384, -2008, 0), "npc1_3", 1200));
		WALKS.put("npc1_3", new WalkInfo(new Location(-56596, -56428, -2008, 0), "npc1_4", 1200));
		WALKS.put("npc1_4", new WalkInfo(new Location(-56593, -56474, -2008, 0), "npc1_5", 1000));
		WALKS.put("npc1_5", new WalkInfo(new Location(-56542, -56474, -2008, 0), "npc1_6", 1000));
		WALKS.put("npc1_6", new WalkInfo(new Location(-56493, -56473, -2008, 0), "npc1_7", 2000));
		WALKS.put("npc1_7", new WalkInfo(new Location(-56495, -56425, -2008, 0), "npc1_1", 4000));
		WALKS.put("npc2_1", new WalkInfo(new Location(-56550, -56291, -2008, 0), "npc2_2", 1200));
		WALKS.put("npc2_2", new WalkInfo(new Location(-56601, -56293, -2008, 0), "npc2_3", 1200));
		WALKS.put("npc2_3", new WalkInfo(new Location(-56603, -56247, -2008, 0), "npc2_4", 1200));
		WALKS.put("npc2_4", new WalkInfo(new Location(-56605, -56203, -2008, 0), "npc2_5", 1000));
		WALKS.put("npc2_5", new WalkInfo(new Location(-56553, -56202, -2008, 0), "npc2_6", 1100));
		WALKS.put("npc2_6", new WalkInfo(new Location(-56504, -56200, -2008, 0), "npc2_7", 2000));
		WALKS.put("npc2_7", new WalkInfo(new Location(-56503, -56243, -2008, 0), "npc2_1", 4000));
		WALKS.put("npc3_1", new WalkInfo(new Location(-56500, -56290, -2008, 0), "npc3_2", 1200));
		WALKS.put("npc3_2", new WalkInfo(new Location(-56551, -56313, -2008, 0), "npc3_3", 1200));
		WALKS.put("npc3_3", new WalkInfo(new Location(-56601, -56293, -2008, 0), "npc3_4", 1200));
		WALKS.put("npc3_4", new WalkInfo(new Location(-56651, -56294, -2008, 0), "npc3_5", 1200));
		WALKS.put("npc3_5", new WalkInfo(new Location(-56653, -56250, -2008, 0), "npc3_6", 1200));
		WALKS.put("npc3_6", new WalkInfo(new Location(-56654, -56204, -2008, 0), "npc3_7", 1200));
		WALKS.put("npc3_7", new WalkInfo(new Location(-56605, -56203, -2008, 0), "npc3_8", 1200));
		WALKS.put("npc3_8", new WalkInfo(new Location(-56554, -56202, -2008, 0), "npc3_9", 1200));
		WALKS.put("npc3_9", new WalkInfo(new Location(-56503, -56200, -2008, 0), "npc3_10", 1200));
		WALKS.put("npc3_10", new WalkInfo(new Location(-56502, -56244, -2008, 0), "npc3_1", 900));
		WALKS.put("npc4_1", new WalkInfo(new Location(-56495, -56381, -2008, 0), "npc4_2", 1200));
		WALKS.put("npc4_2", new WalkInfo(new Location(-56548, -56383, -2008, 0), "npc4_3", 1200));
		WALKS.put("npc4_3", new WalkInfo(new Location(-56597, -56383, -2008, 0), "npc4_4", 1200));
		WALKS.put("npc4_4", new WalkInfo(new Location(-56643, -56385, -2008, 0), "npc4_5", 1200));
		WALKS.put("npc4_5", new WalkInfo(new Location(-56639, -56436, -2008, 0), "npc4_6", 1200));
		WALKS.put("npc4_6", new WalkInfo(new Location(-56639, -56473, -2008, 0), "npc4_7", 1200));
		WALKS.put("npc4_7", new WalkInfo(new Location(-56589, -56473, -2008, 0), "npc4_8", 1200));
		WALKS.put("npc4_8", new WalkInfo(new Location(-56541, -56473, -2008, 0), "npc4_9", 1200));
		WALKS.put("npc4_9", new WalkInfo(new Location(-56496, -56473, -2008, 0), "npc4_10", 1200));
		WALKS.put("npc4_10", new WalkInfo(new Location(-56496, -56429, -2008, 0), "npc4_1", 900));
		WALKS.put("npc5_1", new WalkInfo(new Location(-56549, -56335, -2008, 0), "npc5_2", 1000));
		WALKS.put("npc5_2", new WalkInfo(new Location(-56599, -56337, -2008, 0), "npc5_3", 2000));
		WALKS.put("npc5_3", new WalkInfo(new Location(-56649, -56341, -2008, 0), "npc5_4", 26000));
		WALKS.put("npc5_4", new WalkInfo(new Location(-56600, -56341, -2008, 0), "npc5_5", 1000));
		WALKS.put("npc5_5", new WalkInfo(new Location(-56553, -56341, -2008, 0), "npc5_6", 1000));
		WALKS.put("npc5_6", new WalkInfo(new Location(-56508, -56331, -2008, 0), "npc5_2", 8000));
		WALKS.put("npc6_1", new WalkInfo(new Location(-56595, -56428, -2008, 0), "npc6_2", 1000));
		WALKS.put("npc6_2", new WalkInfo(new Location(-56596, -56383, -2008, 0), "npc6_3", 1000));
		WALKS.put("npc6_3", new WalkInfo(new Location(-56648, -56384, -2008, 0), "npc6_4", 1000));
		WALKS.put("npc6_4", new WalkInfo(new Location(-56645, -56429, -2008, 0), "npc6_5", 1000));
		WALKS.put("npc6_5", new WalkInfo(new Location(-56644, -56475, -2008, 0), "npc6_6", 1000));
		WALKS.put("npc6_6", new WalkInfo(new Location(-56595, -56473, -2008, 0), "npc6_7", 1000));
		WALKS.put("npc6_7", new WalkInfo(new Location(-56542, -56473, -2008, 0), "npc6_8", 1000));
		WALKS.put("npc6_8", new WalkInfo(new Location(-56492, -56472, -2008, 0), "npc6_9", 1200));
		WALKS.put("npc6_9", new WalkInfo(new Location(-56495, -56426, -2008, 0), "npc6_10", 2000));
		WALKS.put("npc6_10", new WalkInfo(new Location(-56540, -56426, -2008, 0), "npc6_1", 3000));
		WALKS.put("npc7_1", new WalkInfo(new Location(-56603, -56249, -2008, 0), "npc7_2", 1000));
		WALKS.put("npc7_2", new WalkInfo(new Location(-56601, -56294, -2008, 0), "npc7_3", 1000));
		WALKS.put("npc7_3", new WalkInfo(new Location(-56651, -56295, -2008, 0), "npc7_4", 1000));
		WALKS.put("npc7_4", new WalkInfo(new Location(-56653, -56248, -2008, 0), "npc7_5", 1000));
		WALKS.put("npc7_5", new WalkInfo(new Location(-56605, -56203, -2008, 0), "npc7_6", 1000));
		WALKS.put("npc7_6", new WalkInfo(new Location(-56554, -56202, -2008, 0), "npc7_7", 1000));
		WALKS.put("npc7_7", new WalkInfo(new Location(-56504, -56201, -2008, 0), "npc7_8", 1000));
		WALKS.put("npc7_8", new WalkInfo(new Location(-56502, -56247, -2008, 0), "npc7_9", 1200));
		WALKS.put("npc7_9", new WalkInfo(new Location(-56549, -56248, -2008, 0), "npc7_10", 2000));
		WALKS.put("npc7_10", new WalkInfo(new Location(-56549, -56248, -2008, 0), "npc7_1", 3000));
		WALKS.put("npc8_1", new WalkInfo(new Location(-56493, -56426, -2008, 0), "npc8_2", 1000));
		WALKS.put("npc8_2", new WalkInfo(new Location(-56497, -56381, -2008, 0), "npc8_3", 1200));
		WALKS.put("npc8_3", new WalkInfo(new Location(-56544, -56381, -2008, 0), "npc8_4", 1200));
		WALKS.put("npc8_4", new WalkInfo(new Location(-56596, -56383, -2008, 0), "npc8_5", 1200));
		WALKS.put("npc8_5", new WalkInfo(new Location(-56594, -56428, -2008, 0), "npc8_6", 900));
		WALKS.put("npc8_6", new WalkInfo(new Location(-56645, -56429, -2008, 0), "npc8_7", 1200));
		WALKS.put("npc8_7", new WalkInfo(new Location(-56647, -56384, -2008, 0), "npc8_8", 1200));
		WALKS.put("npc8_8", new WalkInfo(new Location(-56649, -56362, -2008, 0), "npc8_9", 9200));
		WALKS.put("npc8_9", new WalkInfo(new Location(-56654, -56429, -2008, 0), "npc8_10", 1200));
		WALKS.put("npc8_10", new WalkInfo(new Location(-56644, -56474, -2008, 0), "npc8_11", 900));
		WALKS.put("npc8_11", new WalkInfo(new Location(-56593, -56473, -2008, 0), "npc8_12", 1100));
		WALKS.put("npc8_12", new WalkInfo(new Location(-56543, -56472, -2008, 0), "npc8_13", 1200));
		WALKS.put("npc8_13", new WalkInfo(new Location(-56491, -56471, -2008, 0), "npc8_1", 1200));
		WALKS.put("npc9_1", new WalkInfo(new Location(-56505, -56246, -2008, 0), "npc9_2", 1000));
		WALKS.put("npc9_2", new WalkInfo(new Location(-56504, -56291, -2008, 0), "npc9_3", 1200));
		WALKS.put("npc9_3", new WalkInfo(new Location(-56550, -56291, -2008, 0), "npc9_4", 1200));
		WALKS.put("npc9_4", new WalkInfo(new Location(-56600, -56292, -2008, 0), "npc9_5", 1200));
		WALKS.put("npc9_5", new WalkInfo(new Location(-56603, -56248, -2008, 0), "npc9_6", 900));
		WALKS.put("npc9_6", new WalkInfo(new Location(-56653, -56249, -2008, 0), "npc9_7", 1200));
		WALKS.put("npc9_7", new WalkInfo(new Location(-56651, -56294, -2008, 0), "npc9_8", 1200));
		WALKS.put("npc9_8", new WalkInfo(new Location(-56650, -56316, -2008, 0), "npc9_9", 9200));
		WALKS.put("npc9_9", new WalkInfo(new Location(-56660, -56250, -2008, 0), "npc9_10", 1200));
		WALKS.put("npc9_10", new WalkInfo(new Location(-56656, -56205, -2008, 0), "npc9_11", 900));
		WALKS.put("npc9_11", new WalkInfo(new Location(-56606, -56204, -2008, 0), "npc9_12", 1100));
		WALKS.put("npc9_12", new WalkInfo(new Location(-56554, -56203, -2008, 0), "npc9_13", 1200));
		WALKS.put("npc9_13", new WalkInfo(new Location(-56506, -56203, -2008, 0), "npc9_1", 1200));
		WALKS.put("24", new WalkInfo(new Location(-56730, -56340, -2008, 0), "25", 1800));
		WALKS.put("27", new WalkInfo(new Location(-56702, -56340, -2008, 0), "29", 1800));
	}
	
	private void scheduleTimer()
	{
		final int gameTime = GameTimeController.getInstance().getGameTime();
		final int hours = (gameTime / 60) % 24;
		final int minutes = gameTime % 60;
		int hourDiff;
		int minDiff;
		hourDiff = 20 - hours;
		if (hourDiff < 0)
		{
			hourDiff = 24 - (hourDiff *= -1);
		}
		minDiff = 30 - minutes;
		if (minDiff < 0)
		{
			minDiff = 60 - (minDiff *= -1);
		}
		long diff;
		hourDiff *= 3600000;
		minDiff *= 60000;
		diff = hourDiff + minDiff;
		LOGGER.info("Fantasy Isle: MC show script starting at " + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(System.currentTimeMillis() + diff) + " and is scheduled each next 4 hours.");
		startQuestTimer("Start", diff, null, null); // first start
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		if (IS_STARTED)
		{
			switch (npc.getId())
			{
				case 32433:
				{
					npc.broadcastSay(ChatType.NPC_SHOUT, MESSAGES[0]);
					startQuestTimer("1", 30000, npc, null);
					break;
				}
				case 32431:
				{
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56657, -56338, -2008, 33102));
					startQuestTimer("social1", 6000, npc, null, true);
					startQuestTimer("7", 215000, npc, null);
					break;
				}
				case 32432:
				{
					startQuestTimer("social1", 6000, npc, null, true);
					startQuestTimer("7", 215000, npc, null);
					break;
				}
				case 32442:
				case 32443:
				case 32444:
				case 32445:
				case 32446:
				{
					startQuestTimer("11", 100000, npc, null);
					break;
				}
				case 32424:
				case 32425:
				case 32426:
				case 32427:
				case 32428:
				{
					startQuestTimer("social1", 5500, npc, null);
					startQuestTimer("social1", 12500, npc, null);
					startQuestTimer("28", 19700, npc, null);
					break;
				}
			}
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ((event == null) || event.isEmpty())
		{
			LOGGER.warning("MC_Show: Null/Empty event for npc " + npc + " and player " + player + "!");
			return null;
		}
		
		if (event.equalsIgnoreCase("Start"))
		{
			IS_STARTED = true;
			addSpawn(MC, -56698, -56430, -2008, 32768, false, 0);
			startQuestTimer("Start", 14400000L, null, null); // repeat
		}
		else if ((npc != null) && IS_STARTED)
		{
			// TODO switch on event
			if (event.equalsIgnoreCase("6"))
			{
				npc.broadcastSay(ChatType.NPC_SHOUT, MESSAGES[6]);
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56511, -56647, -2008, 36863));
				npc.broadcastPacket(new PlaySound(1, "NS22_F", 0, 0, 0, 0, 0));
				addSpawn(SINGERS[0], -56344, -56328, -2008, 32768, false, 224000);
				addSpawn(SINGERS[1], -56552, -56245, -2008, 36863, false, 224000);
				addSpawn(SINGERS[1], -56546, -56426, -2008, 28672, false, 224000);
				addSpawn(SINGERS[1], -56570, -56473, -2008, 28672, false, 224000);
				addSpawn(SINGERS[1], -56594, -56516, -2008, 28672, false, 224000);
				addSpawn(SINGERS[1], -56580, -56203, -2008, 36863, false, 224000);
				addSpawn(SINGERS[1], -56606, -56157, -2008, 36863, false, 224000);
				startQuestTimer("7", 215000, npc, null);
			}
			else if (event.equalsIgnoreCase("7"))
			{
				switch (npc.getId())
				{
					case 32433:
					{
						npc.broadcastSay(ChatType.NPC_SHOUT, MESSAGES[7]);
						npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56698, -56430, -2008, 32768));
						startQuestTimer("8", 12000, npc, null);
						break;
					}
					default:
					{
						cancelQuestTimer("social1", npc, null);
						npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56594, -56064, -2008, 32768));
						break;
					}
				}
			}
			else if (event.equalsIgnoreCase("10"))
			{
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56483, -56665, -2034, 32768));
				npc.broadcastPacket(new PlaySound(1, "TP05_F", 0, 0, 0, 0, 0));
				startQuestTimer("npc1_1", 3000, addSpawn(CIRCUS[0], -56495, -56375, -2008, 32768, false, 101000), null);
				startQuestTimer("npc2_1", 3000, addSpawn(CIRCUS[0], -56491, -56289, -2008, 32768, false, 101000), null);
				startQuestTimer("npc3_1", 3000, addSpawn(CIRCUS[1], -56502, -56246, -2008, 32768, false, 101000), null);
				startQuestTimer("npc4_1", 3000, addSpawn(CIRCUS[1], -56496, -56429, -2008, 32768, false, 101000), null);
				startQuestTimer("npc5_1", 3500, addSpawn(CIRCUS[2], -56505, -56334, -2008, 32768, false, 101000), null);
				startQuestTimer("npc6_1", 4000, addSpawn(CIRCUS[3], -56545, -56427, -2008, 32768, false, 101000), null);
				startQuestTimer("npc7_1", 4000, addSpawn(CIRCUS[3], -56552, -56248, -2008, 32768, false, 101000), null);
				startQuestTimer("npc8_1", 3000, addSpawn(CIRCUS[4], -56493, -56473, -2008, 32768, false, 101000), null);
				startQuestTimer("npc9_1", 3000, addSpawn(CIRCUS[4], -56504, -56201, -2008, 32768, false, 101000), null);
				startQuestTimer("11", 100000, npc, null);
			}
			else if (event.equalsIgnoreCase("11"))
			{
				switch (npc.getId())
				{
					case 32433:
					{
						npc.broadcastSay(ChatType.NPC_SHOUT, MESSAGES[11]);
						npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56698, -56430, -2008, 32768));
						startQuestTimer("12", 5000, npc, null);
						break;
					}
					default:
					{
						npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56343, -56330, -2008, 32768));
						break;
					}
				}
			}
			else if (event.equalsIgnoreCase("14"))
			{
				startQuestTimer("social1", 2000, addSpawn(INDIVIDUALS[0], -56700, -56385, -2008, 32768, false, 49000), null);
				startQuestTimer("15", 7000, npc, null);
			}
			else if (event.equalsIgnoreCase("17"))
			{
				npc.broadcastSay(ChatType.NPC_SHOUT, MESSAGES[16]);
				startQuestTimer("social1", 2000, addSpawn(INDIVIDUALS[1], -56700, -56340, -2008, 32768, false, 32000), null);
				startQuestTimer("18", 9000, npc, null);
			}
			else if (event.equalsIgnoreCase("20"))
			{
				startQuestTimer("social1", 2000, addSpawn(INDIVIDUALS[2], -56703, -56296, -2008, 32768, false, 13000), null);
				startQuestTimer("21", 8000, npc, null);
			}
			else if (event.equalsIgnoreCase("23"))
			{
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56702, -56340, -2008, 32768));
				startQuestTimer("24", 2800, npc, null);
				addSpawn(SHOWSTUFF[0], -56672, -56406, -2000, 32768, false, 20900);
				addSpawn(SHOWSTUFF[1], -56648, -56368, -2000, 32768, false, 20900);
				addSpawn(SHOWSTUFF[2], -56608, -56338, -2000, 32768, false, 20900);
				addSpawn(SHOWSTUFF[3], -56652, -56307, -2000, 32768, false, 20900);
				addSpawn(SHOWSTUFF[4], -56672, -56272, -2000, 32768, false, 20900);
			}
			else if (event.equalsIgnoreCase("28"))
			{
				npc.broadcastSay(ChatType.NPC_SHOUT, MESSAGES[23]);
				startQuestTimer("social1", 1, npc, null);
			}
			else if (event.equalsIgnoreCase("29"))
			{
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(-56730, -56340, -2008, 32768));
				startQuestTimer("clean_npc", 4100, npc, null);
				startQuestTimer("timer_check", 60000, null, null, true);
			}
			else if (event.equalsIgnoreCase("social1"))
			{
				npc.broadcastSocialAction(1);
			}
			else if (event.equalsIgnoreCase("clean_npc"))
			{
				IS_STARTED = false;
				npc.deleteMe();
			}
			else if (TALKS.containsKey(event))
			{
				final ShoutInfo si = TALKS.get(event);
				if (si != null)
				{
					npc.broadcastSay(ChatType.NPC_SHOUT, si.getNpcStringId());
					startQuestTimer(si.getNextEvent(), si.getTime(), npc, null);
				}
			}
			else if (WALKS.containsKey(event))
			{
				final WalkInfo wi = WALKS.get(event);
				if (wi != null)
				{
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, wi.getCharPos());
					startQuestTimer(wi.getNextEvent(), wi.getTime(), npc, null);
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		new TalentShow();
	}
}

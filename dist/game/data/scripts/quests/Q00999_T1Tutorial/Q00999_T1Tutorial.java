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
package quests.Q00999_T1Tutorial;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

public class Q00999_T1Tutorial extends Quest
{
	// Items
	private static int RECOMMENDATION_01 = 1067;
	private static int RECOMMENDATION_02 = 1068;
	private static int LEAF_OF_MOTHERTREE = 1069;
	private static int BLOOD_OF_JUNDIN = 1070;
	private static int LICENSE_OF_MINER = 1498;
	private static int VOUCHER_OF_FLAME = 1496;
	private static int SOULSHOT_NOVICE = 5789;
	private static int SPIRITSHOT_NOVICE = 5790;
	private static int BLUE_GEM = 6353;
	private static int DIPLOMA = 9881;
	// NPCs
	private static final int[] NPCS =
	{
		30008,
		30009,
		30017,
		30019,
		30129,
		30131,
		30573,
		30575,
		30370,
		30528,
		30530,
		30400,
		30401,
		30402,
		30403,
		30404,
		32133,
		32134
	};
	private static final HashMap<Object, Object[]> Event = new HashMap<>();
	private static final Map<Integer, Talk> Talks = new HashMap<>();
	
	private static class Talk
	{
		public int raceId;
		public String[] htmlfiles;
		public int npcTyp;
		public int item;
		
		public Talk(int _raceId, String[] _htmlfiles, int _npcTyp, int _item)
		{
			raceId = _raceId;
			htmlfiles = _htmlfiles;
			npcTyp = _npcTyp;
			item = _item;
		}
	}
	
	public Q00999_T1Tutorial()
	{
		super(-1);
		// @formatter:off
		Event.put("32133_02", new Object[]{"32133-03.htm", -119692, 44504, 380, DIPLOMA, 0x7b, SOULSHOT_NOVICE, 200, 0x7c, SOULSHOT_NOVICE, 200});
		Event.put("30008_02", new Object[]{"30008-03.htm", 0, 0, 0, RECOMMENDATION_01, 0x00, SOULSHOT_NOVICE, 200, 0x00, 0, 0});
		Event.put("30008_04", new Object[]{"30008-04.htm", -84058, 243239, -3730, 0, 0x00, 0, 0, 0, 0, 0});
		Event.put("30017_02", new Object[]{"30017-03.htm", 0, 0, 0, RECOMMENDATION_02, 0x0a, SPIRITSHOT_NOVICE, 100, 0x00, 0, 0});
		Event.put("30017_04", new Object[]{"30017-04.htm", -84058, 243239, -3730, 0, 0x0a, 0, 0, 0x00, 0, 0});
		Event.put("30370_02", new Object[]{"30370-03.htm", 0, 0, 0, LEAF_OF_MOTHERTREE, 0x19, SPIRITSHOT_NOVICE, 100, 0x12, SOULSHOT_NOVICE, 200});
		Event.put("30370_04", new Object[]{"30370-04.htm", 45491, 48359, -3086, 0, 0x19, 0, 0, 0x12, 0, 0});
		Event.put("30129_02", new Object[]{"30129-03.htm", 0, 0, 0, BLOOD_OF_JUNDIN, 0x26, SPIRITSHOT_NOVICE, 100, 0x1f, SOULSHOT_NOVICE, 200});
		Event.put("30129_04", new Object[]{"30129-04.htm", 12116, 16666, -4610, 0, 0x26, 0, 0, 0x1f, 0, 0});
		Event.put("30528_02", new Object[]{"30528-03.htm", 0, 0, 0, LICENSE_OF_MINER, 0x35, SOULSHOT_NOVICE, 200, 0x00, 0, 0});
		Event.put("30528_04", new Object[]{"30528-04.htm", 115642, -178046, -941, 0, 0x35, 0, 0, 0x00, 0, 0});
		Event.put("30573_02", new Object[]{"30573-03.htm", 0, 0, 0, VOUCHER_OF_FLAME, 0x31, SPIRITSHOT_NOVICE, 100, 0x2c, SOULSHOT_NOVICE, 200});
		Event.put("30573_04", new Object[]{"30573-04.htm", -45067, -113549, -235, 0, 0x31, 0, 0, 0x2c, 0, 0});
		Talks.put(30017, new Talk(0, new String[]{"30017-01.htm", "30017-02.htm", "30017-04.htm"}, 0, 0));
		Talks.put(30008, new Talk(0, new String[]{"30008-01.htm", "30008-02.htm", "30008-04.htm"}, 0, 0));
		Talks.put(30370, new Talk(1, new String[]{"30370-01.htm", "30370-02.htm", "30370-04.htm"}, 0, 0));
		Talks.put(30129, new Talk(2, new String[]{"30129-01.htm", "30129-02.htm", "30129-04.htm"}, 0, 0));
		Talks.put(30573, new Talk(3, new String[]{"30573-01.htm", "30573-02.htm", "30573-04.htm"}, 0, 0));
		Talks.put(30528, new Talk(4, new String[]{"30528-01.htm", "30528-02.htm", "30528-04.htm"}, 0, 0));
		Talks.put(30018, new Talk(0, new String[]{"30131-01.htm", "", "30019-03a.htm", "30019-04.htm",}, 1, RECOMMENDATION_02));
		Talks.put(30019, new Talk(0, new String[]{"30131-01.htm", "", "30019-03a.htm", "30019-04.htm",}, 1, RECOMMENDATION_02));
		Talks.put(30020, new Talk(0, new String[]{"30131-01.htm", "", "30019-03a.htm", "30019-04.htm",}, 1, RECOMMENDATION_02));
		Talks.put(30021, new Talk(0, new String[]{"30131-01.htm", "", "30019-03a.htm", "30019-04.htm",}, 1, RECOMMENDATION_02));
		Talks.put(30009, new Talk(0, new String[]{"30530-01.htm", "30009-03.htm", "", "30009-04.htm",}, 1, RECOMMENDATION_01));
		Talks.put(30011, new Talk(0, new String[]{"30530-01.htm", "30009-03.htm", "", "30009-04.htm",}, 1, RECOMMENDATION_01));
		Talks.put(30012, new Talk(0, new String[]{"30530-01.htm", "30009-03.htm", "", "30009-04.htm",}, 1, RECOMMENDATION_01));
		Talks.put(30056, new Talk(0, new String[]{"30530-01.htm", "30009-03.htm", "", "30009-04.htm",}, 1, RECOMMENDATION_01));
		Talks.put(30400, new Talk(1, new String[]{"30131-01.htm", "30400-03.htm", "30400-03a.htm", "30400-04.htm",}, 1, LEAF_OF_MOTHERTREE));
		Talks.put(30401, new Talk(1, new String[]{"30131-01.htm", "30400-03.htm", "30400-03a.htm", "30400-04.htm",}, 1, LEAF_OF_MOTHERTREE));
		Talks.put(30402, new Talk(1, new String[]{"30131-01.htm", "30400-03.htm", "30400-03a.htm", "30400-04.htm",}, 1, LEAF_OF_MOTHERTREE));
		Talks.put(30403, new Talk(1, new String[]{"30131-01.htm", "30400-03.htm", "30400-03a.htm", "30400-04.htm",}, 1, LEAF_OF_MOTHERTREE));
		Talks.put(30131, new Talk(2, new String[]{"30131-01.htm", "30131-03.htm", "30131-03a.htm", "30131-04.htm",}, 1, BLOOD_OF_JUNDIN));
		Talks.put(30404, new Talk(2, new String[]{"30131-01.htm", "30131-03.htm", "30131-03a.htm", "30131-04.htm",}, 1, BLOOD_OF_JUNDIN));
		Talks.put(30574, new Talk(3, new String[]{"30575-01.htm", "30575-03.htm", "30575-03a.htm", "30575-04.htm",}, 1, VOUCHER_OF_FLAME));
		Talks.put(30575, new Talk(3, new String[]{"30575-01.htm", "30575-03.htm", "30575-03a.htm", "30575-04.htm",}, 1, VOUCHER_OF_FLAME));
		Talks.put(30530, new Talk(4, new String[]{"30530-01.htm", "30530-03.htm", "", "30530-04.htm",}, 1, LICENSE_OF_MINER));
		Talks.put(32133, new Talk(5, new String[]{"32133-01.htm", "32133-02.htm", "32133-04.htm"}, 0, 0));
		Talks.put(32134, new Talk(5, new String[]{"32134-01.htm", "32134-03.htm", "", "32134-04.htm",}, 1, DIPLOMA));
		// @formatter:on
		for (int startNpc : NPCS)
		{
			addStartNpc(startNpc);
		}
		for (int FirstTalkId : NPCS)
		{
			addFirstTalkId(FirstTalkId);
		}
		for (int TalkId : NPCS)
		{
			addTalkId(TalkId);
		}
		addKillId(18342);
		addKillId(20001);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (Config.DISABLE_TUTORIAL)
		{
			return null;
		}
		
		String htmltext = event;
		
		final QuestState st = player.getQuestState(getName());
		final QuestState qs = player.getQuestState("Q00255_Tutorial");
		if (qs == null)
		{
			return htmltext;
		}
		
		final int Ex = qs.getInt("Ex");
		
		if (event.equalsIgnoreCase("TimerEx_NewbieHelper"))
		{
			if (Ex == 0)
			{
				if (player.getClassId().isMage())
				{
					st.playTutorialVoice("tutorial_voice_009b");
				}
				else
				{
					st.playTutorialVoice("tutorial_voice_009a");
				}
				qs.set("Ex", "1");
			}
			else if (Ex == 3)
			{
				st.playTutorialVoice("tutorial_voice_010a");
				qs.set("Ex", "4");
			}
			return null;
		}
		else if (event.equalsIgnoreCase("TimerEx_GrandMaster"))
		{
			if (Ex >= 4)
			{
				st.showQuestionMark(7);
				playSound(player, "ItemSound.quest_tutorial");
				st.playTutorialVoice("tutorial_voice_025");
			}
			return null;
		}
		else if (event.equalsIgnoreCase("isle"))
		{
			addRadar(player, -119692, 44504, 380);
			player.teleToLocation(-120050, 44500, 360);
			htmltext = "<html><body>" + npc.getName() + ":<br>Go to the <font color=\"LEVEL\">Isle of Souls</font> and meet the <font color=\"LEVEL\">Newbie Guide</font> there to learn a number of important tips. He will also give you an item to assist your development.<br>Follow the direction arrow above your head and it will lead you to the Newbie Guide. Good luck!</body></html>";
		}
		else
		{
			final Object[] map = Event.get(event);
			htmltext = (String) map[0];
			final int radarX = (Integer) map[1];
			final int radarY = (Integer) map[2];
			final int radarZ = (Integer) map[3];
			final int item = (Integer) map[4];
			final int classId1 = (Integer) map[5];
			final int gift1 = (Integer) map[6];
			final int count1 = (Integer) map[7];
			final int classId2 = (Integer) map[8];
			final int gift2 = (Integer) map[9];
			final int count2 = (Integer) map[10];
			if (radarX != 0)
			{
				addRadar(player, radarX, radarY, radarZ);
			}
			if ((getQuestItemsCount(player, item) > 0) && (st.getInt("onlyone") == 0))
			{
				addExpAndSp(player, 0, 50);
				startQuestTimer("TimerEx_GrandMaster", 60000, npc, player);
				takeItems(player, item, 1);
				st.set("step", "3");
				if (Ex <= 3)
				{
					qs.set("Ex", "4");
				}
				if (player.getClassId().getId() == classId1)
				{
					giveItems(player, gift1, count1);
					if (gift1 == SPIRITSHOT_NOVICE)
					{
						st.playTutorialVoice("tutorial_voice_027");
					}
					else
					{
						st.playTutorialVoice("tutorial_voice_026");
					}
				}
				else if (player.getClassId().getId() == classId2)
				{
					if (gift2 != 0)
					{
						giveItems(player, gift2, count2);
						st.playTutorialVoice("tutorial_voice_026");
					}
				}
				st.set("step", "4");
				st.set("onlyone", "1");
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		if (Config.DISABLE_TUTORIAL)
		{
			return null;
		}
		
		String htmltext = "";
		
		QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			st = newQuestState(player);
		}
		
		final QuestState qs = player.getQuestState("Q00255_Tutorial");
		if ((qs == null) || qs.isCompleted())
		{
			npc.showChatWindow(player);
			return null;
		}
		
		final int onlyone = st.getInt("onlyone");
		final int Ex = qs.getInt("Ex");
		final int step = st.getInt("step");
		final Talk talk = Talks.get(npc.getId());
		
		if (((player.getLevel() >= 10) || (onlyone != 0)) && (talk.npcTyp == 1))
		{
			htmltext = "30575-05.htm";
		}
		else if ((onlyone == 0) && (player.getLevel() < 10))
		{
			if ((talk != null) && (player.getRace().ordinal() == talk.raceId))
			{
				htmltext = talk.htmlfiles[0];
				if (talk.npcTyp == 1)
				{
					if ((step == 0) && (Ex < 0))
					{
						qs.set("Ex", "0");
						startQuestTimer("TimerEx_NewbieHelper", 30000, npc, player);
						if (player.getClassId().isMage())
						{
							st.set("step", "1");
							st.setState(State.STARTED);
						}
						else
						{
							htmltext = "30530-01.htm";
							st.set("step", "1");
							st.setState(State.STARTED);
						}
					}
					else if ((step == 1) && !hasQuestItems(player, talk.item) && (Ex <= 2))
					{
						if (hasQuestItems(player, BLUE_GEM))
						{
							takeItems(player, BLUE_GEM, -1);
							giveItems(player, talk.item, 1);
							st.set("step", "2");
							qs.set("Ex", "3");
							startQuestTimer("TimerEx_NewbieHelper", 30000, npc, player);
							qs.set("ucMemo", "3");
							if (player.getClassId().isMage())
							{
								st.playTutorialVoice("tutorial_voice_027");
								giveItems(player, SPIRITSHOT_NOVICE, 100);
								htmltext = talk.htmlfiles[2];
								if (htmltext.equals(""))
								{
									htmltext = "<html><body>I`m sorry. I only help warriors. Please go to another Newbie Helper who may assist you.</body></html>";
								}
							}
							else
							{
								st.playTutorialVoice("tutorial_voice_026");
								giveItems(player, SOULSHOT_NOVICE, 200);
								htmltext = talk.htmlfiles[1];
								if (htmltext.equals(""))
								{
									htmltext = "<html><body>I`m sorry. I only help mystics. Please go to another Newbie Helper who may assist you.</body></html>";
								}
							}
						}
						else
						{
							if (player.getClassId().isMage())
							{
								htmltext = "30131-02.htm";
							}
							if (player.getRace().ordinal() == 3)
							{
								htmltext = "30575-02.htm";
							}
							else
							{
								htmltext = "30530-02.htm";
							}
						}
					}
					else if (step == 2)
					{
						htmltext = talk.htmlfiles[3];
					}
				}
				else if (talk.npcTyp == 0)
				{
					if (step == 1)
					{
						htmltext = talk.htmlfiles[0];
					}
					else if (step == 2)
					{
						htmltext = talk.htmlfiles[1];
					}
					else if (step == 3)
					{
						htmltext = talk.htmlfiles[2];
					}
				}
			}
		}
		else if (step == 4)
		{
			if (player.getLevel() < 10)
			{
				htmltext = npc.getId() + "-04.htm";
			}
		}
		
		if ((htmltext == null) || htmltext.equals(""))
		{
			npc.showChatWindow(player);
		}
		
		npc.showChatWindow(player);
		
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = player.getQuestState(getName());
		if (st == null)
		{
			return null;
		}
		
		final QuestState qs = player.getQuestState("Q00255_Tutorial");
		if (qs == null)
		{
			return null;
		}
		
		final int Ex = qs.getInt("Ex");
		
		if (Ex <= 1)
		{
			st.playTutorialVoice("tutorial_voice_011");
			st.showQuestionMark(3);
			qs.set("Ex", "2");
		}
		
		if ((Ex == 2) && !hasQuestItems(player, BLUE_GEM))
		{
			((L2MonsterInstance) npc).dropItem(player, BLUE_GEM, 1);
			playSound(player, "ItemSound.quest_tutorial");
		}
		return null;
	}
}
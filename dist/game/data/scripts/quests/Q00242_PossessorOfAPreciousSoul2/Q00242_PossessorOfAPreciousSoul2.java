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
package quests.Q00242_PossessorOfAPreciousSoul2;

import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00241_PossessorOfAPreciousSoul1.Q00241_PossessorOfAPreciousSoul1;

/**
 * Possessor Of A PreciousSoul part 2 (242)<br>
 * Original Jython script by disKret.
 * @author mjaniko, Joxit
 */
public class Q00242_PossessorOfAPreciousSoul2 extends Quest
{
	// NPCs
	private static final int VIRGIL = 31742;
	private static final int KASSANDRA = 31743;
	private static final int OGMAR = 31744;
	private static final int FALLEN_UNICORN = 31746;
	private static final int PURE_UNICORN = 31747;
	private static final int CORNERSTONE = 31748;
	private static final int MYSTERIOUS_KNIGHT = 31751;
	private static final int ANGEL_CORPSE = 31752;
	private static final int KALIS = 30759;
	private static final int MATILD = 30738;
	private static final int RESTRAINER_OF_GLORY = 27317;
	// Items
	private static final int VIRGILS_LETTER = 7677;
	private static final int GOLDEN_HAIR = 7590;
	private static final int ORB_OF_BINDING = 7595;
	private static final int SORCERY_INGREDIENT = 7596;
	private static final int CARADINE_LETTER = 7678;
	// Rewards
	private static final int CHANCE_FOR_HAIR = 20;
	
	public Q00242_PossessorOfAPreciousSoul2()
	{
		super(242);
		addStartNpc(VIRGIL);
		addTalkId(VIRGIL, KASSANDRA, OGMAR, MYSTERIOUS_KNIGHT, ANGEL_CORPSE, KALIS, MATILD, FALLEN_UNICORN, CORNERSTONE, PURE_UNICORN);
		addKillId(RESTRAINER_OF_GLORY);
		registerQuestItems(GOLDEN_HAIR, ORB_OF_BINDING, SORCERY_INGREDIENT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		if (!player.isSubClassActive())
		{
			return "no_sub.html";
		}
		
		switch (event)
		{
			case "31742-02.html":
				st.startQuest();
				takeItems(player, VIRGILS_LETTER, -1);
				break;
			case "31743-05.html":
				if (st.isCond(1))
				{
					st.setCond(2, true);
				}
				break;
			case "31744-02.html":
				if (st.isCond(2))
				{
					st.setCond(3, true);
				}
				break;
			case "31751-02.html":
				if (st.isCond(3))
				{
					st.setCond(4, true);
				}
				break;
			case "30759-02.html":
				if (st.isCond(6))
				{
					st.setCond(7, true);
				}
				break;
			case "30738-02.html":
				if (st.isCond(7))
				{
					st.setCond(8, true);
					giveItems(player, SORCERY_INGREDIENT, 1);
				}
				break;
			case "30759-05.html":
				if (st.isCond(8))
				{
					takeItems(player, GOLDEN_HAIR, -1);
					takeItems(player, SORCERY_INGREDIENT, -1);
					st.set("awaitsDrops", "1");
					st.setCond(9, true);
					st.set("cornerstones", "0");
				}
				break;
			case "PURE_UNICORN":
				npc.getSpawn().stopRespawn();
				npc.deleteMe();
				final L2Npc npc_pure = addSpawn(PURE_UNICORN, 85884, -76588, -3470, 30000);
				startQuestTimer("FALLEN_UNICORN", 30000, npc_pure, player);
				return null;
			case "FALLEN_UNICORN":
				final L2Npc npc_fallen = addSpawn(FALLEN_UNICORN, 85884, -76588, -3470, 0);
				npc_fallen.getSpawn().startRespawn();
				return null;
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, "awaitsDrops", "1");
		if (partyMember == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		
		final QuestState st = getQuestState(partyMember, false);
		if (st.isCond(9) && (getQuestItemsCount(partyMember, ORB_OF_BINDING) < 4))
		{
			giveItems(partyMember, ORB_OF_BINDING, 1);
			playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		if (getQuestItemsCount(partyMember, ORB_OF_BINDING) >= 4)
		{
			st.unset("awaitsDrops");
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (st.isStarted() && !player.isSubClassActive())
		{
			return "no_sub.html";
		}
		
		switch (npc.getId())
		{
			case VIRGIL:
				switch (st.getState())
				{
					case State.CREATED:
						final QuestState qs = player.getQuestState(Q00241_PossessorOfAPreciousSoul1.class.getSimpleName());
						if ((qs != null) && qs.isCompleted())
						{
							htmltext = (player.isSubClassActive() && (player.getLevel() >= 60)) ? "31742-01.htm" : "31742-00.htm";
						}
						break;
					case State.STARTED:
						switch (st.getCond())
						{
							case 1:
								htmltext = "31742-03.html";
								break;
							case 11:
								htmltext = "31742-04.html";
								giveItems(player, CARADINE_LETTER, 1);
								addExpAndSp(player, 455764, 0);
								st.exitQuest(false, true);
								break;
						}
						break;
					case State.COMPLETED:
						htmltext = getAlreadyCompletedMsg(player);
						break;
				}
				break;
			case KASSANDRA:
				switch (st.getCond())
				{
					case 1:
						htmltext = "31743-01.html";
						break;
					case 2:
						htmltext = "31743-06.html";
						break;
					case 11:
						htmltext = "31743-07.html";
						break;
				}
				break;
			case OGMAR:
				switch (st.getCond())
				{
					case 2:
						htmltext = "31744-01.html";
						break;
					case 3:
						htmltext = "31744-03.html";
						break;
				}
				break;
			case MYSTERIOUS_KNIGHT:
				switch (st.getCond())
				{
					case 3:
						htmltext = "31751-01.html";
						break;
					case 4:
						htmltext = "31751-03.html";
						break;
					case 5:
						if (hasQuestItems(player, GOLDEN_HAIR))
						{
							st.setCond(6, true);
							htmltext = "31751-04.html";
						}
						break;
					case 6:
						htmltext = "31751-05.html";
						break;
				}
				break;
			case ANGEL_CORPSE:
				switch (st.getCond())
				{
					case 4:
						npc.doDie(npc);
						if (CHANCE_FOR_HAIR >= getRandom(100))
						{
							giveItems(player, GOLDEN_HAIR, 1);
							st.setCond(5, true);
							htmltext = "31752-01.html";
						}
						else
						{
							htmltext = "31752-02.html";
						}
						break;
					case 5:
						htmltext = "31752-02.html";
						break;
				}
				break;
			case KALIS:
				switch (st.getCond())
				{
					case 6:
						htmltext = "30759-01.html";
						break;
					case 7:
						htmltext = "30759-03.html";
						break;
					case 8:
						if (hasQuestItems(player, SORCERY_INGREDIENT))
						{
							htmltext = "30759-04.html";
						}
						break;
					case 9:
						htmltext = "30759-06.html";
						break;
				}
				break;
			case MATILD:
				switch (st.getCond())
				{
					case 7:
						htmltext = "30738-01.html";
						break;
					case 8:
						htmltext = "30738-03.html";
						break;
				}
				break;
			case CORNERSTONE:
				if (st.isCond(9))
				{
					if (hasQuestItems(player, ORB_OF_BINDING))
					{
						htmltext = "31748-02.html";
						takeItems(player, ORB_OF_BINDING, 1);
						npc.doDie(npc);
						
						st.set("cornerstones", Integer.toString(st.getInt("cornerstones") + 1));
						if (st.getInt("cornerstones") == 4)
						{
							st.setCond(10);
						}
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						npc.setTarget(player);
						npc.doCast(SkillData.getInstance().getSkill(4546, 1));
					}
					else
					{
						htmltext = "31748-01.html";
					}
				}
				break;
			case FALLEN_UNICORN:
				switch (st.getCond())
				{
					case 9:
						htmltext = "31746-01.html";
						break;
					case 10:
						htmltext = "31746-02.html";
						startQuestTimer("PURE_UNICORN", 3000, npc, player);
						break;
				}
				break;
			case PURE_UNICORN:
				switch (st.getCond())
				{
					case 10:
						st.setCond(11, true);
						htmltext = "31747-01.html";
						break;
					case 11:
						htmltext = "31747-02.html";
						break;
				}
				break;
		}
		return htmltext;
	}
}

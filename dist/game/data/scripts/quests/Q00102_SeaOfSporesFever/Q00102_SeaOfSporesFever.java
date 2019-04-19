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
package quests.Q00102_SeaOfSporesFever;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Sea of Spores Fever (102)
 * @author xban1x
 */
public class Q00102_SeaOfSporesFever extends Quest
{
	// NPCs
	private static final int COBENDELL = 30156;
	private static final int BERROS = 30217;
	private static final int VELTRESS = 30219;
	private static final int RAYEN = 30221;
	private static final int ALBERIUS = 30284;
	private static final int GARTRANDELL = 30285;
	// Monsters
	private static final int DRYAD = 20013;
	private static final int DRYAD_ELDER = 20019;
	// Items
	private static final int SWORD_OF_SENTINEL = 743;
	private static final int STAFF_OF_SENTINEL = 744;
	private static final int ALBERIUS_LIST = 746;
	private static final int ALBERIUS_LETTER = 964;
	private static final int EVERGREEN_AMULET = 965;
	private static final int DRYADS_TEAR = 966;
	private static final int LESSER_HEALING_POTION = 1060;
	private static final int COBENDELLS_MEDICINE1 = 1130;
	private static final int COBENDELLS_MEDICINE2 = 1131;
	private static final int COBENDELLS_MEDICINE3 = 1132;
	private static final int COBENDELLS_MEDICINE4 = 1133;
	private static final int COBENDELLS_MEDICINE5 = 1134;
	private static final int SOULSHOT_NO_GRADE = 1835;
	private static final int SPIRITSHOT_NO_GRADE = 2509;
	private static final int ECHO_CRYSTAL_THEME_OF_BATTLE = 4412;
	private static final int ECHO_CRYSTAL_THEME_OF_LOVE = 4413;
	private static final int ECHO_CRYSTAL_THEME_OF_SOLITUDE = 4414;
	private static final int ECHO_CRYSTAL_THEME_OF_FEAST = 4415;
	private static final int ECHO_CRYSTAL_THEME_OF_CELEBRATION = 4416;
	// Misc
	private static final int MIN_LVL = 12;
	private static final Map<Integer, Integer> SENTINELS = new HashMap<>();
	static
	{
		SENTINELS.put(GARTRANDELL, COBENDELLS_MEDICINE5);
		SENTINELS.put(RAYEN, COBENDELLS_MEDICINE4);
		SENTINELS.put(VELTRESS, COBENDELLS_MEDICINE3);
		SENTINELS.put(BERROS, COBENDELLS_MEDICINE2);
		SENTINELS.put(ALBERIUS, COBENDELLS_MEDICINE1);
	}
	
	public Q00102_SeaOfSporesFever()
	{
		super(102);
		addStartNpc(ALBERIUS);
		addTalkId(ALBERIUS, COBENDELL, GARTRANDELL, BERROS, VELTRESS, RAYEN);
		addKillId(DRYAD, DRYAD_ELDER);
		registerQuestItems(ALBERIUS_LIST, ALBERIUS_LETTER, EVERGREEN_AMULET, DRYADS_TEAR, COBENDELLS_MEDICINE1, COBENDELLS_MEDICINE2, COBENDELLS_MEDICINE3, COBENDELLS_MEDICINE4, COBENDELLS_MEDICINE5);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && event.equals("30284-02.htm"))
		{
			st.startQuest();
			giveItems(player, ALBERIUS_LETTER, 1);
			return event;
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState st = getQuestState(killer, false);
		if ((st != null) && st.isCond(2) && (getRandom(10) < 3))
		{
			giveItems(killer, DRYADS_TEAR, 1);
			if (getQuestItemsCount(killer, DRYADS_TEAR) < 10)
			{
				playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
			else
			{
				st.setCond(3, true);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case ALBERIUS:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = player.getRace() == Race.ELF ? player.getLevel() >= MIN_LVL ? "30284-07.htm" : "30284-08.htm" : "30284-00.htm";
						break;
					}
					case State.STARTED:
					{
						switch (st.getCond())
						{
							case 1:
							{
								if (hasQuestItems(player, ALBERIUS_LETTER))
								{
									htmltext = "30284-03.html";
								}
								break;
							}
							case 2:
							{
								if (hasQuestItems(player, EVERGREEN_AMULET))
								{
									htmltext = "30284-09.html";
								}
								break;
							}
							case 4:
							{
								if (hasQuestItems(player, COBENDELLS_MEDICINE1))
								{
									takeItems(player, COBENDELLS_MEDICINE1, 1);
									giveItems(player, ALBERIUS_LIST, 1);
									st.setCond(5);
									htmltext = "30284-04.html";
								}
								break;
							}
							case 5:
							{
								if (hasAtLeastOneQuestItem(player, COBENDELLS_MEDICINE1, COBENDELLS_MEDICINE2, COBENDELLS_MEDICINE3, COBENDELLS_MEDICINE4, COBENDELLS_MEDICINE5))
								{
									htmltext = "30284-05.html";
								}
								break;
							}
							case 6:
							{
								if (!hasAtLeastOneQuestItem(player, COBENDELLS_MEDICINE1, COBENDELLS_MEDICINE2, COBENDELLS_MEDICINE3, COBENDELLS_MEDICINE4, COBENDELLS_MEDICINE5))
								{
									giveItems(player, LESSER_HEALING_POTION, 100);
									giveItems(player, ECHO_CRYSTAL_THEME_OF_BATTLE, 10);
									giveItems(player, ECHO_CRYSTAL_THEME_OF_LOVE, 10);
									giveItems(player, ECHO_CRYSTAL_THEME_OF_SOLITUDE, 10);
									giveItems(player, ECHO_CRYSTAL_THEME_OF_FEAST, 10);
									giveItems(player, ECHO_CRYSTAL_THEME_OF_CELEBRATION, 10);
									if (player.isMageClass())
									{
										giveItems(player, STAFF_OF_SENTINEL, 1);
										giveItems(player, SPIRITSHOT_NO_GRADE, 500);
									}
									else
									{
										giveItems(player, SWORD_OF_SENTINEL, 1);
										giveItems(player, SOULSHOT_NO_GRADE, 500);
									}
									addExpAndSp(player, 30202, 1339);
									giveAdena(player, 6331, true);
									st.exitQuest(false, true);
									htmltext = "30284-06.html";
								}
								break;
							}
						}
						break;
					}
					case State.COMPLETED:
					{
						htmltext = getAlreadyCompletedMsg(player);
						break;
					}
				}
				break;
			}
			case COBENDELL:
			{
				switch (st.getCond())
				{
					case 1:
					{
						if (hasQuestItems(player, ALBERIUS_LETTER))
						{
							takeItems(player, ALBERIUS_LETTER, 1);
							giveItems(player, EVERGREEN_AMULET, 1);
							st.setCond(2, true);
							htmltext = "30156-03.html";
						}
						break;
					}
					case 2:
					{
						if (hasQuestItems(player, EVERGREEN_AMULET) && (getQuestItemsCount(player, DRYADS_TEAR) < 10))
						{
							htmltext = "30156-04.html";
						}
						break;
					}
					case 3:
					{
						if (getQuestItemsCount(player, DRYADS_TEAR) >= 10)
						{
							takeItems(player, EVERGREEN_AMULET, -1);
							takeItems(player, DRYADS_TEAR, -1);
							giveItems(player, COBENDELLS_MEDICINE1, 1);
							giveItems(player, COBENDELLS_MEDICINE2, 1);
							giveItems(player, COBENDELLS_MEDICINE3, 1);
							giveItems(player, COBENDELLS_MEDICINE4, 1);
							giveItems(player, COBENDELLS_MEDICINE5, 1);
							st.setCond(4, true);
							htmltext = "30156-05.html";
						}
						break;
					}
					case 4:
					{
						if (hasAtLeastOneQuestItem(player, COBENDELLS_MEDICINE1, COBENDELLS_MEDICINE2, COBENDELLS_MEDICINE3, COBENDELLS_MEDICINE4, COBENDELLS_MEDICINE5))
						{
							htmltext = "30156-06.html";
						}
						break;
					}
					case 5:
					{
						if (hasAtLeastOneQuestItem(player, COBENDELLS_MEDICINE1, COBENDELLS_MEDICINE2, COBENDELLS_MEDICINE3, COBENDELLS_MEDICINE4, COBENDELLS_MEDICINE5))
						{
							htmltext = "30156-07.html";
						}
						break;
					}
				}
				break;
			}
			case GARTRANDELL:
			case RAYEN:
			case VELTRESS:
			case BERROS:
			{
				if (hasQuestItems(player, ALBERIUS_LIST, SENTINELS.get(npc.getId())))
				{
					takeItems(player, SENTINELS.get(npc.getId()), -1);
					if (!hasAtLeastOneQuestItem(player, COBENDELLS_MEDICINE1, COBENDELLS_MEDICINE2, COBENDELLS_MEDICINE3, COBENDELLS_MEDICINE4, COBENDELLS_MEDICINE5))
					{
						st.setCond(6);
					}
					htmltext = npc.getId() + "-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

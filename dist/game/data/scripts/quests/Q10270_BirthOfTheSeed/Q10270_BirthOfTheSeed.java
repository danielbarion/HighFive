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
package quests.Q10270_BirthOfTheSeed;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

import quests.Q10272_LightFragment.Q10272_LightFragment;

/**
 * Birth of the Seed (10270)
 * @author Adry_85
 * @since 2.6.0.0
 */
public final class Q10270_BirthOfTheSeed extends Quest
{
	// NPCs
	private static final int ARTIUS = 32559;
	private static final int PLENOS = 32563;
	private static final int GINBY = 32566;
	private static final int LELRIKIA = 32567;
	// Monsters
	private static final int COHEMENES = 25634;
	private static final int YEHAN_KLODEKUS = 25665;
	private static final int YEHAN_KLANIKUS = 25666;
	// Items
	private static final int YEHAN_KLODEKUS_BADGE = 13868;
	private static final int YEHAN_KLANIKUS_BADGE = 13869;
	private static final int LICH_CRYSTAL = 13870;
	// Misc
	private static final int MIN_LEVEL = 75;
	// Location
	private static final Location INSTANCE_EXIT = new Location(-185057, 242821, 1576);
	
	public Q10270_BirthOfTheSeed()
	{
		super(10270);
		addStartNpc(PLENOS);
		addTalkId(PLENOS, GINBY, LELRIKIA, ARTIUS);
		addKillId(COHEMENES, YEHAN_KLODEKUS, YEHAN_KLANIKUS);
		registerQuestItems(YEHAN_KLODEKUS_BADGE, YEHAN_KLANIKUS_BADGE, LICH_CRYSTAL);
	}
	
	@Override
	public void actionForEachPlayer(L2PcInstance player, L2Npc npc, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && st.isMemoState(2) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			switch (npc.getId())
			{
				case YEHAN_KLODEKUS:
				{
					if (!hasQuestItems(player, YEHAN_KLODEKUS_BADGE))
					{
						giveItems(player, YEHAN_KLODEKUS_BADGE, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					break;
				}
				case YEHAN_KLANIKUS:
				{
					if (!hasQuestItems(player, YEHAN_KLANIKUS_BADGE))
					{
						giveItems(player, YEHAN_KLANIKUS_BADGE, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					break;
				}
				case COHEMENES:
				{
					if (!hasQuestItems(player, LICH_CRYSTAL))
					{
						giveItems(player, LICH_CRYSTAL, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					break;
				}
			}
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "32563-02.htm":
			{
				htmltext = event;
				break;
			}
			case "32563-03.htm":
			{
				st.startQuest();
				st.setMemoState(1);
				playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
				htmltext = event;
				break;
			}
			case "32566-02.html":
			{
				if (st.isMemoState(4))
				{
					final QuestState st1 = player.getQuestState(Q10272_LightFragment.class.getSimpleName());
					if ((st1 == null) || (st1.isStarted() && (st1.getMemoState() < 10)))
					{
						htmltext = event;
					}
					else if ((st1.isStarted() && (st1.getMemoState() >= 10)) || st1.isCompleted())
					{
						htmltext = "32566-03.html";
					}
				}
				break;
			}
			case "32566-04.html":
			{
				if (st.isMemoState(4))
				{
					if (getQuestItemsCount(player, Inventory.ADENA_ID) < 10000)
					{
						htmltext = event;
					}
					else
					{
						takeItems(player, Inventory.ADENA_ID, 10000);
						st.setMemoState(5);
						htmltext = "32566-05.html";
					}
				}
				break;
			}
			case "32566-06.html":
			{
				if (st.isMemoState(5))
				{
					htmltext = event;
				}
				break;
			}
			case "32567-02.html":
			case "32567-03.html":
			{
				if (st.isMemoState(10))
				{
					htmltext = event;
				}
				break;
			}
			case "32567-04.html":
			{
				if (st.isMemoState(10))
				{
					st.setMemoState(11);
					st.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "32567-05.html":
			{
				if (st.isMemoState(11))
				{
					st.setMemoState(20);
					player.setInstanceId(0);
					player.teleToLocation(INSTANCE_EXIT, true);
					htmltext = event;
				}
				break;
			}
			case "32559-02.html":
			{
				if (st.isMemoState(1))
				{
					st.setMemoState(2);
					st.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "32559-08.html":
			{
				if (st.isMemoState(3))
				{
					final QuestState st1 = player.getQuestState(Q10272_LightFragment.class.getSimpleName());
					if ((st1 == null) || (st1.isStarted() && (st1.getMemoState() < 10)))
					{
						st.setMemoState(4);
						st.setCond(4, true);
						htmltext = event;
					}
				}
				break;
			}
			case "32559-10.html":
			{
				if (st.isMemoState(3))
				{
					final QuestState st1 = player.getQuestState(Q10272_LightFragment.class.getSimpleName());
					if ((st1 != null) && ((st1.isStarted() && (st1.getMemoState() >= 10)) || st1.isCompleted()))
					{
						st.setMemoState(4);
						st.setCond(4, true);
						htmltext = event;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		executeForEachPlayer(killer, npc, isSummon, true, false);
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (st.isCompleted())
		{
			if (npc.getId() == PLENOS)
			{
				htmltext = "32563-05.html";
			}
			else if (npc.getId() == ARTIUS)
			{
				htmltext = "32559-03.html";
			}
		}
		else if (st.isCreated())
		{
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "32563-01.htm" : "32563-04.htm";
		}
		else if (st.isStarted())
		{
			switch (npc.getId())
			{
				case PLENOS:
				{
					if (st.isMemoState(1))
					{
						htmltext = "32563-06.html";
					}
					break;
				}
				case GINBY:
				{
					final int memoState = st.getMemoState();
					if (memoState == 4)
					{
						htmltext = "32566-01.html";
					}
					else if (memoState < 4)
					{
						htmltext = "32566-07.html";
					}
					else if (memoState == 5)
					{
						htmltext = "32566-06.html";
					}
					else if ((memoState >= 10) && (memoState < 20))
					{
						htmltext = "32566-08.html";
					}
					else if (memoState == 20)
					{
						htmltext = "32566-09.html";
					}
					break;
				}
				case LELRIKIA:
				{
					final int memoState = st.getMemoState();
					if (memoState == 10)
					{
						htmltext = "32567-01.html";
					}
					else if (memoState == 11)
					{
						htmltext = "32567-06.html";
					}
					break;
				}
				case ARTIUS:
				{
					switch (st.getMemoState())
					{
						case 1:
						{
							htmltext = "32559-01.html";
							break;
						}
						case 2:
						{
							if (hasQuestItems(player, YEHAN_KLODEKUS_BADGE, YEHAN_KLANIKUS_BADGE, LICH_CRYSTAL))
							{
								st.setMemoState(3);
								st.setCond(3, true);
								takeItems(player, -1, YEHAN_KLODEKUS_BADGE, YEHAN_KLANIKUS_BADGE, LICH_CRYSTAL);
								htmltext = "32559-04.html";
							}
							else
							{
								if (!hasQuestItems(player, YEHAN_KLODEKUS_BADGE) && !hasQuestItems(player, YEHAN_KLANIKUS_BADGE) && !hasQuestItems(player, LICH_CRYSTAL))
								{
									htmltext = "32559-05.html";
								}
								else
								{
									htmltext = "32559-06.html";
								}
							}
							break;
						}
						case 3:
						{
							final QuestState st1 = player.getQuestState(Q10272_LightFragment.class.getSimpleName());
							if ((st1 == null) || (st1.isStarted() && (st1.getMemoState() < 10)))
							{
								htmltext = "32559-07.html";
							}
							else if ((st1.isStarted() && (st1.getMemoState() >= 10)) || st1.isCompleted())
							{
								htmltext = "32559-09.html";
							}
							break;
						}
						case 20:
						{
							if (player.getLevel() >= MIN_LEVEL)
							{
								giveAdena(player, 133590, true);
								addExpAndSp(player, 625343, 48222);
								st.exitQuest(false, true);
								htmltext = "32559-11.html";
							}
							break;
						}
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

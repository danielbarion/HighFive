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
package quests.Q00062_PathOfTheTrooper;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;
import com.l2jmobius.gameserver.util.Util;

/**
 * Path Of The Trooper (62)
 * @author ivantotov
 */
public final class Q00062_PathOfTheTrooper extends Quest
{
	// NPCs
	private static final int MASTER_SHUBAIN = 32194;
	private static final int MASTER_GWAIN = 32197;
	// Items
	private static final int FELIM_LIZARDMAN_HEAD = 9749;
	private static final int VENOMOUS_SPIDERS_LEG = 9750;
	private static final int TUMRAN_BUGBEAR_HEART = 9751;
	private static final int SHUBAINS_RECOMMENDATION = 9752;
	// Reward
	private static final int GWAINS_RECOMMENDATION = 9753;
	// Monster
	private static final int FELIM_LIZARDMAN_WARRIOR = 20014;
	private static final int VENOMOUS_SPIDER = 20038;
	private static final int TUMRAN_BUGBEAR = 20062;
	// Misc
	private static final int MIN_LEVEL = 18;
	
	public Q00062_PathOfTheTrooper()
	{
		super(62);
		addStartNpc(MASTER_GWAIN);
		addTalkId(MASTER_GWAIN, MASTER_SHUBAIN);
		addKillId(FELIM_LIZARDMAN_WARRIOR, VENOMOUS_SPIDER, TUMRAN_BUGBEAR);
		registerQuestItems(FELIM_LIZARDMAN_HEAD, VENOMOUS_SPIDERS_LEG, TUMRAN_BUGBEAR_HEART, SHUBAINS_RECOMMENDATION);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "ACCEPT":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = "32197-06.htm";
				}
				break;
			}
			case "32194-02.html":
			{
				if (qs.isCond(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case FELIM_LIZARDMAN_WARRIOR:
				{
					if (qs.isCond(2) && (getQuestItemsCount(killer, FELIM_LIZARDMAN_HEAD) < 5))
					{
						giveItems(killer, FELIM_LIZARDMAN_HEAD, 1);
						if (getQuestItemsCount(killer, FELIM_LIZARDMAN_HEAD) == 5)
						{
							playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						else
						{
							playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case VENOMOUS_SPIDER:
				{
					if (qs.isCond(3) && (getQuestItemsCount(killer, VENOMOUS_SPIDERS_LEG) < 10))
					{
						giveItems(killer, VENOMOUS_SPIDERS_LEG, 1);
						if (getQuestItemsCount(killer, VENOMOUS_SPIDERS_LEG) == 10)
						{
							playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						else
						{
							playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case TUMRAN_BUGBEAR:
				{
					if (qs.isCond(5) && !hasQuestItems(killer, TUMRAN_BUGBEAR_HEART) && (getRandom(1000) < 500))
					{
						giveItems(killer, TUMRAN_BUGBEAR_HEART, 1);
						playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_MIDDLE);
					}
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (qs.isCreated())
		{
			if (npc.getId() == MASTER_GWAIN)
			{
				if (player.getRace() == Race.KAMAEL)
				{
					if (player.getClassId() == ClassId.MALE_SOLDIER)
					{
						if (player.getLevel() >= MIN_LEVEL)
						{
							htmltext = "32197-01.htm";
						}
						else
						{
							htmltext = "32197-02.html";
						}
					}
					else
					{
						htmltext = "32197-03.html";
					}
				}
				else
				{
					htmltext = "32197-04.html";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case MASTER_GWAIN:
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						{
							htmltext = "32197-07.html";
							break;
						}
						case 4:
						{
							takeItems(player, SHUBAINS_RECOMMENDATION, 1);
							qs.setMemoState(5);
							qs.setCond(5, true);
							htmltext = "32197-08.html";
							break;
						}
						case 5:
						{
							if (!hasQuestItems(player, TUMRAN_BUGBEAR_HEART))
							{
								htmltext = "32197-09.html";
							}
							else
							{
								giveAdena(player, 163800, true);
								takeItems(player, TUMRAN_BUGBEAR_HEART, 1);
								giveItems(player, GWAINS_RECOMMENDATION, 1);
								final int level = player.getLevel();
								if (level >= 20)
								{
									addExpAndSp(player, 320534, 20848);
								}
								else if (level == 19)
								{
									addExpAndSp(player, 456128, 27546);
								}
								else
								{
									addExpAndSp(player, 591724, 34244);
								}
								qs.exitQuest(false, true);
								player.sendPacket(new SocialAction(player.getObjectId(), 3));
								htmltext = "32197-10.html";
							}
							break;
						}
					}
					break;
				}
				case MASTER_SHUBAIN:
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "32194-01.html";
							break;
						}
						case 2:
						{
							if (getQuestItemsCount(player, FELIM_LIZARDMAN_HEAD) < 5)
							{
								htmltext = "32194-03.html";
							}
							else
							{
								takeItems(player, FELIM_LIZARDMAN_HEAD, -1);
								qs.setMemoState(3);
								qs.setCond(3, true);
								htmltext = "32194-04.html";
							}
							break;
						}
						case 3:
						{
							if (getQuestItemsCount(player, VENOMOUS_SPIDERS_LEG) < 10)
							{
								htmltext = "32194-05.html";
							}
							else
							{
								takeItems(player, VENOMOUS_SPIDERS_LEG, -1);
								giveItems(player, SHUBAINS_RECOMMENDATION, 1);
								qs.setMemoState(4);
								qs.setCond(4, true);
								htmltext = "32194-06.html";
							}
							break;
						}
						case 4:
						{
							htmltext = "32194-07.html";
							break;
						}
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == MASTER_GWAIN)
			{
				htmltext = "32197-05.html";
			}
		}
		return htmltext;
	}
}
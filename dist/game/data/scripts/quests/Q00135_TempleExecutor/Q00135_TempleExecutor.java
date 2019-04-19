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
package quests.Q00135_TempleExecutor;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Temple Executor (135)
 * @author malyelfik
 */
public class Q00135_TempleExecutor extends Quest
{
	// NPCs
	private static final int SHEGFIELD = 30068;
	private static final int PANO = 30078;
	private static final int ALEX = 30291;
	private static final int SONIN = 31773;
	// Items
	private static final int STOLEN_CARGO = 10328;
	private static final int HATE_CRYSTAL = 10329;
	private static final int OLD_TREASURE_MAP = 10330;
	private static final int SONINS_CREDENTIALS = 10331;
	private static final int PANOS_CREDENTIALS = 10332;
	private static final int ALEXS_CREDENTIALS = 10333;
	private static final int BADGE_TEMPLE_EXECUTOR = 10334;
	// Monsters
	private static final Map<Integer, Integer> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20781, 439); // Delu Lizardman Shaman
		MOBS.put(21104, 439); // Delu Lizardman Supplier
		MOBS.put(21105, 504); // Delu Lizardman Special Agent
		MOBS.put(21106, 423); // Cursed Seer
		MOBS.put(21107, 902); // Delu Lizardman Commander
	}
	
	// Misc
	private static final int MIN_LEVEL = 35;
	private static final int ITEM_COUNT = 10;
	private static final int MAX_REWARD_LEVEL = 41;
	
	public Q00135_TempleExecutor()
	{
		super(135);
		addStartNpc(SHEGFIELD);
		addTalkId(SHEGFIELD, ALEX, SONIN, PANO);
		addKillId(MOBS.keySet());
		registerQuestItems(STOLEN_CARGO, HATE_CRYSTAL, OLD_TREASURE_MAP, SONINS_CREDENTIALS, PANOS_CREDENTIALS, ALEXS_CREDENTIALS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = event;
		switch (event)
		{
			case "30291-02a.html":
			case "30291-04.html":
			case "30291-05.html":
			case "30291-06.html":
			case "30068-08.html":
			case "30068-09.html":
			case "30068-10.html":
			{
				break;
			}
			case "30068-03.htm":
			{
				qs.startQuest();
				break;
			}
			case "30068-04.html":
			{
				qs.setCond(2, true);
				break;
			}
			case "30291-07.html":
			{
				qs.unset("talk");
				qs.setCond(3, true);
				break;
			}
			case "30068-11.html":
			{
				giveItems(player, BADGE_TEMPLE_EXECUTOR, 1);
				giveAdena(player, 16924, true);
				if (player.getLevel() < MAX_REWARD_LEVEL)
				{
					addExpAndSp(player, 30000, 2000);
				}
				qs.exitQuest(false, true);
				break;
			}
			default:
			{
				htmltext = null;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance member = getRandomPartyMember(player, 3);
		if (member == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		final QuestState qs = getQuestState(member, false);
		if (getRandom(1000) < MOBS.get(npc.getId()))
		{
			if (getQuestItemsCount(player, STOLEN_CARGO) < ITEM_COUNT)
			{
				giveItems(player, STOLEN_CARGO, 1);
			}
			else if (getQuestItemsCount(player, HATE_CRYSTAL) < ITEM_COUNT)
			{
				giveItems(player, HATE_CRYSTAL, 1);
			}
			else
			{
				giveItems(player, OLD_TREASURE_MAP, 1);
			}
			
			if ((getQuestItemsCount(player, STOLEN_CARGO) >= ITEM_COUNT) && (getQuestItemsCount(player, HATE_CRYSTAL) >= ITEM_COUNT) && (getQuestItemsCount(player, OLD_TREASURE_MAP) >= ITEM_COUNT))
			{
				qs.setCond(4, true);
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case SHEGFIELD:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? "30068-01.htm" : "30068-02.htm";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1: // 1
							{
								qs.setCond(2, true);
								htmltext = "30068-04.html";
								break;
							}
							case 2: // 2, 3
							case 3: // 4
							{
								htmltext = "30068-05.html";
								break;
							}
							case 4: // 5
							{
								htmltext = "30068-06.html";
								break;
							}
							case 5:
							{
								if (qs.isSet("talk"))
								{
									htmltext = "30068-08.html";
								}
								else if (hasQuestItems(player, PANOS_CREDENTIALS, SONINS_CREDENTIALS, ALEXS_CREDENTIALS))
								{
									takeItems(player, SONINS_CREDENTIALS, -1);
									takeItems(player, PANOS_CREDENTIALS, -1);
									takeItems(player, ALEXS_CREDENTIALS, -1);
									qs.set("talk", "1");
									htmltext = "30068-07.html";
								}
								else
								{
									htmltext = "30068-06.html";
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
			case ALEX:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30291-01.html";
							break;
						}
						case 2:
						{
							if (qs.isSet("talk"))
							{
								htmltext = "30291-03.html";
							}
							else
							{
								qs.set("talk", "1");
								htmltext = "30291-02.html";
							}
							break;
						}
						case 3:
						{
							htmltext = "30291-08.html"; // 4
							break;
						}
						case 4:
						{
							if (hasQuestItems(player, PANOS_CREDENTIALS, SONINS_CREDENTIALS))
							{
								if (getQuestItemsCount(player, OLD_TREASURE_MAP) < ITEM_COUNT)
								{
									return htmltext;
								}
								qs.setCond(5, true);
								takeItems(player, OLD_TREASURE_MAP, -1);
								giveItems(player, ALEXS_CREDENTIALS, 1);
								htmltext = "30291-10.html";
							}
							else
							{
								htmltext = "30291-09.html";
							}
							break;
						}
						case 5:
						{
							htmltext = "30291-11.html";
							break;
						}
					}
				}
				break;
			}
			case PANO:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30078-01.html";
							break;
						}
						case 2:
						{
							htmltext = "30078-02.html";
							break;
						}
						case 3:
						{
							htmltext = "30078-03.html";
							break;
						}
						case 4:
						{
							if (!qs.isSet("Pano"))
							{
								if (getQuestItemsCount(player, HATE_CRYSTAL) < ITEM_COUNT)
								{
									return htmltext;
								}
								takeItems(player, HATE_CRYSTAL, -1);
								giveItems(player, PANOS_CREDENTIALS, 1);
								qs.set("Pano", "1");
								htmltext = "30078-04.html";
							}
							break;
						}
						case 5:
						{
							htmltext = "30078-05.html";
							break;
						}
					}
				}
				break;
			}
			case SONIN:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "31773-01.html";
							break;
						}
						case 2:
						{
							htmltext = "31773-02.html";
							break;
						}
						case 3:
						{
							htmltext = "31773-03.html";
							break;
						}
						case 4:
						{
							if (!qs.isSet("Sonin"))
							{
								if (getQuestItemsCount(player, STOLEN_CARGO) < ITEM_COUNT)
								{
									return htmltext;
								}
								takeItems(player, STOLEN_CARGO, -1);
								giveItems(player, SONINS_CREDENTIALS, 1);
								qs.set("Sonin", "1");
								htmltext = "31773-04.html";
								break;
							}
						}
						case 5:
						{
							htmltext = "31773-05.html";
							break;
						}
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
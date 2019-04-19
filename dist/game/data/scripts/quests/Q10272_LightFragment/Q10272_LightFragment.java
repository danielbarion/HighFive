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
package quests.Q10272_LightFragment;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q10271_TheEnvelopingDarkness.Q10271_TheEnvelopingDarkness;

/**
 * Light Fragment (10272)
 * @author Gladicek
 */
public class Q10272_LightFragment extends Quest
{
	private static final int ORBYU = 32560;
	private static final int ARTIUS = 32559;
	private static final int GINBY = 32566;
	private static final int LELRIKIA = 32567;
	private static final int LEKON = 32557;
	private static final int[] MOBS =
	{
		22536, // Royal Guard Captain
		22537, // Dragon Steed Troop Grand Magician
		22538, // Dragon Steed Troop Commander
		22539, // Dragon Steed Troops No 1 Battalion Commander
		22540, // White Dragon Leader
		22541, // Dragon Steed Troop Infantry
		22542, // Dragon Steed Troop Magic Leader
		22543, // Dragon Steed Troop Magician
		22544, // Dragon Steed Troop Magic Soldier
		22547, // Dragon Steed Troop Healer
		22550, // Savage Warrior
		22551, // Priest of Darkness
		22552, // Mutation Drake
		22596
		// White Dragon Leader
	};
	private static final int FRAGMENT_POWDER = 13853;
	private static final int LIGHT_FRAGMENT_POWDER = 13854;
	private static final int LIGHT_FRAGMENT = 13855;
	private static final double DROP_CHANCE = 60;
	
	public Q10272_LightFragment()
	{
		super(10272);
		addStartNpc(ORBYU);
		addTalkId(ORBYU, ARTIUS, GINBY, LELRIKIA, LEKON);
		addKillId(MOBS);
		registerQuestItems(FRAGMENT_POWDER, LIGHT_FRAGMENT_POWDER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "32560-06.html":
			{
				st.startQuest();
				break;
			}
			case "32559-03.html":
			{
				st.setCond(2, true);
				break;
			}
			case "32559-07.html":
			{
				st.setCond(3, true);
				break;
			}
			case "pay":
			{
				if (getQuestItemsCount(player, Inventory.ADENA_ID) >= 10000)
				{
					takeItems(player, Inventory.ADENA_ID, 10000);
					event = "32566-05.html";
				}
				else
				{
					event = "32566-04a.html";
				}
				break;
			}
			case "32567-04.html":
			{
				st.setCond(4, true);
				break;
			}
			case "32559-12.html":
			{
				st.setCond(5, true);
				break;
			}
			case "32557-03.html":
			{
				if (getQuestItemsCount(player, LIGHT_FRAGMENT_POWDER) >= 100)
				{
					takeItems(player, LIGHT_FRAGMENT_POWDER, 100);
					st.set("wait", "1");
				}
				else
				{
					event = "32557-04.html";
				}
				break;
			}
			default:
			{
				break;
			}
		}
		return event;
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && st.isCond(5))
		{
			final long count = getQuestItemsCount(player, FRAGMENT_POWDER);
			if (count < 100)
			{
				int chance = (int) (Config.RATE_QUEST_DROP * DROP_CHANCE);
				int numItems = chance / 100;
				chance = chance % 100;
				if (getRandom(100) < chance)
				{
					numItems++;
				}
				if (numItems > 0)
				{
					if ((count + numItems) > 100)
					{
						numItems = 100 - (int) count;
					}
					if (numItems > 0)
					{
						giveItems(player, FRAGMENT_POWDER, numItems);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = getNoQuestMsg(player);
		QuestState st = getQuestState(player, true);
		
		switch (npc.getId())
		{
			case ORBYU:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						if (player.getLevel() < 75)
						{
							htmltext = "32560-03.html";
						}
						else
						{
							st = player.getQuestState(Q10271_TheEnvelopingDarkness.class.getSimpleName());
							htmltext = ((st != null) && st.isCompleted()) ? "32560-01.htm" : "32560-02.html";
						}
						break;
					}
					case State.STARTED:
					{
						htmltext = "32560-06.html";
						break;
					}
					case State.COMPLETED:
					{
						htmltext = "32560-04.html";
						break;
					}
				}
				break;
			}
			case ARTIUS:
			{
				if (st.isCompleted())
				{
					htmltext = "32559-19.html";
				}
				else
				{
					switch (st.getCond())
					{
						case 1:
						{
							htmltext = "32559-01.html";
							break;
						}
						case 2:
						{
							htmltext = "32559-04.html";
							break;
						}
						case 3:
						{
							htmltext = "32559-08.html";
							break;
						}
						case 4:
						{
							htmltext = "32559-10.html";
							break;
						}
						case 5:
						{
							if (getQuestItemsCount(player, FRAGMENT_POWDER) >= 100)
							{
								htmltext = "32559-15.html";
								st.setCond(6, true);
							}
							else
							{
								htmltext = hasQuestItems(player, FRAGMENT_POWDER) ? "32559-14.html" : "32559-13.html";
							}
							break;
						}
						case 6:
						{
							if (getQuestItemsCount(player, LIGHT_FRAGMENT_POWDER) < 100)
							{
								htmltext = "32559-16.html";
							}
							else
							{
								htmltext = "32559-17.html";
								st.setCond(7, true);
							}
							break;
						}
						case 7:
						{
							// TODO Nothing here?
							break;
						}
						case 8:
						{
							htmltext = "32559-18.html";
							giveAdena(player, 556980, true);
							addExpAndSp(player, 1009016, 91363);
							st.exitQuest(false, true);
							break;
						}
					}
				}
				break;
			}
			case GINBY:
			{
				switch (st.getCond())
				{
					case 1:
					case 2:
					{
						htmltext = "32566-02.html";
						break;
					}
					case 3:
					{
						htmltext = "32566-01.html";
						break;
					}
					case 4:
					{
						htmltext = "32566-09.html";
						break;
					}
					case 5:
					{
						htmltext = "32566-10.html";
						break;
					}
					case 6:
					{
						htmltext = "32566-10.html";
						break;
					}
				}
				break;
			}
			case LELRIKIA:
			{
				switch (st.getCond())
				{
					case 3:
					{
						htmltext = "32567-01.html";
						break;
					}
					case 4:
					{
						htmltext = "32567-05.html";
						break;
					}
				}
				break;
			}
			case LEKON:
			{
				switch (st.getCond())
				{
					case 7:
					{
						if (st.getInt("wait") == 1)
						{
							htmltext = "32557-05.html";
							st.unset("wait");
							st.setCond(8, true);
							giveItems(player, LIGHT_FRAGMENT, 1);
						}
						else
						{
							htmltext = "32557-01.html";
						}
						break;
					}
					case 8:
					{
						htmltext = "32557-06.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

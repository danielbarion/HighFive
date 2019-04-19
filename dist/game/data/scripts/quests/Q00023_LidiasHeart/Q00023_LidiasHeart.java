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
package quests.Q00023_LidiasHeart;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

import quests.Q00022_TragedyInVonHellmannForest.Q00022_TragedyInVonHellmannForest;
import quests.Q00024_InhabitantsOfTheForestOfTheDead.Q00024_InhabitantsOfTheForestOfTheDead;

/**
 * Lidia's Heart (23)
 * @author ivantotov
 */
public final class Q00023_LidiasHeart extends Quest
{
	// NPCs
	private static final int HIGH_PRIEST_INNOCENTIN = 31328;
	private static final int TRADER_VIOLET = 31386;
	private static final int TOMBSTONE = 31523;
	private static final int GHOST_OF_VON_HELLMANN = 31524;
	private static final int BROKEN_BOOKSHELF = 31526;
	private static final int BOX = 31530;
	// Items
	private static final int LIDIAS_DIARY = 7064;
	private static final int SILVER_KEY = 7149;
	private static final int SILVER_SPEAR = 7150;
	// Reward
	private static final int MAP_FOREST_OF_THE_DEAD = 7063;
	private static final int LIDIAS_HAIRPIN = 7148;
	// Misc
	private static final int MIN_LEVEL = 64;
	// Locations
	private static final Location GHOST_SPAWN = new Location(51432, -54570, -3136);
	
	public Q00023_LidiasHeart()
	{
		super(23);
		addStartNpc(HIGH_PRIEST_INNOCENTIN);
		addTalkId(HIGH_PRIEST_INNOCENTIN, TRADER_VIOLET, TOMBSTONE, GHOST_OF_VON_HELLMANN, BROKEN_BOOKSHELF, BOX);
		addSpawnId(GHOST_OF_VON_HELLMANN);
		registerQuestItems(LIDIAS_DIARY, SILVER_KEY, SILVER_SPEAR);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ("DESPAWN".equals(event))
		{
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			if (npc0 != null)
			{
				npc0.getVariables().set("SPAWNED", false);
			}
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		
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
				if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "31328-02.htm";
				}
				else
				{
					if (!hasQuestItems(player, MAP_FOREST_OF_THE_DEAD))
					{
						giveItems(player, MAP_FOREST_OF_THE_DEAD, 1);
					}
					giveItems(player, SILVER_KEY, 1);
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = "31328-03.htm";
				}
				break;
			}
			case "31328-05.html":
			case "31328-06.html":
			case "31328-10.html":
			case "31328-11.html":
			case "31328-16.html":
			case "31328-17.html":
			case "31328-18.html":
			case "31524-03.html":
			case "31526-04.html":
			case "31526-05.html":
			case "31526-07a.html":
			case "31526-09.html":
			{
				htmltext = event;
				break;
			}
			case "31328-07.html":
			{
				if (qs.isMemoState(1))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "31328-12.html":
			{
				if (qs.isMemoState(5) || qs.isMemoState(6))
				{
					qs.setMemoState(6);
					qs.setCond(5);
					htmltext = event;
				}
				break;
			}
			case "31328-13.html":
			{
				if (qs.isMemoState(5) || qs.isMemoState(6))
				{
					qs.setMemoState(7);
					htmltext = event;
				}
				break;
			}
			case "31328-19.html":
			{
				playSound(player, QuestSound.AMBSOUND_MT_CREAK);
				htmltext = event;
				break;
			}
			case "31328-20.html":
			{
				if (qs.isMemoState(7))
				{
					qs.setMemoState(8);
					qs.setCond(6);
					htmltext = event;
				}
				break;
			}
			case "31328-21.html":
			{
				qs.setCond(5);
				htmltext = event;
				break;
			}
			case "31523-02.html":
			{
				if (qs.isMemoState(8) || qs.isMemoState(9))
				{
					playSound(player, QuestSound.SKILLSOUND_HORROR_02);
					if (!npc.getVariables().getBoolean("SPAWNED", false))
					{
						npc.getVariables().set("SPAWNED", true);
						final L2Npc ghost = addSpawn(npc, GHOST_OF_VON_HELLMANN, GHOST_SPAWN, false, 0);
						ghost.getVariables().set("npc0", npc);
						htmltext = event;
					}
					else
					{
						htmltext = "31523-03.html";
					}
				}
				break;
			}
			case "31523-06.html":
			{
				if (qs.isMemoState(9))
				{
					giveItems(player, SILVER_KEY, 1);
					qs.setMemoState(10);
					qs.setCond(8);
					htmltext = event;
				}
				break;
			}
			case "31524-02.html":
			{
				playSound(player, QuestSound.CHRSOUND_MHFIGHTER_CRY);
				htmltext = event;
				break;
			}
			case "31524-04.html":
			{
				if (qs.isMemoState(8))
				{
					takeItems(player, LIDIAS_DIARY, 1);
					qs.setMemoState(9);
					qs.setCond(7);
					htmltext = event;
				}
				break;
			}
			case "31526-02.html":
			{
				if (qs.isMemoState(2) && hasQuestItems(player, SILVER_KEY))
				{
					takeItems(player, SILVER_KEY, -1);
					qs.setMemoState(3);
					htmltext = event;
				}
				break;
			}
			case "31526-06.html":
			{
				if (!hasQuestItems(player, LIDIAS_HAIRPIN))
				{
					giveItems(player, LIDIAS_HAIRPIN, 1);
				}
				qs.setMemoState(qs.getMemoState() + 1);
				if (hasQuestItems(player, LIDIAS_DIARY))
				{
					qs.setCond(4);
				}
				htmltext = event;
				break;
			}
			case "31526-08.html":
			{
				playSound(player, QuestSound.ITEMSOUND_ARMOR_LEATHER);
				htmltext = event;
				break;
			}
			case "31526-10.html":
			{
				playSound(player, QuestSound.AMBSOUND_EG_DRON);
				htmltext = event;
				break;
			}
			case "31526-11.html":
			{
				giveItems(player, LIDIAS_DIARY, 1);
				qs.setMemoState(qs.getMemoState() + 1);
				if (hasQuestItems(player, LIDIAS_HAIRPIN))
				{
					qs.setCond(4);
				}
				htmltext = event;
				break;
			}
			case "31530-02.html":
			{
				if (qs.isMemoState(11) && hasQuestItems(player, SILVER_KEY))
				{
					giveItems(player, SILVER_SPEAR, 1);
					takeItems(player, SILVER_KEY, -1);
					playSound(player, QuestSound.ITEMSOUND_WEAPON_SPEAR);
					qs.setCond(10);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == HIGH_PRIEST_INNOCENTIN)
			{
				final QuestState q22 = player.getQuestState(Q00022_TragedyInVonHellmannForest.class.getSimpleName());
				if ((q22 != null) && q22.isCompleted())
				{
					htmltext = "31328-01.htm";
				}
				else
				{
					htmltext = "31328-01a.html";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case HIGH_PRIEST_INNOCENTIN:
				{
					switch (qs.getMemoState())
					{
						case 1:
						{
							htmltext = "31328-04.html";
							break;
						}
						case 2:
						{
							htmltext = "31328-08.html";
							break;
						}
						case 5:
						{
							htmltext = "31328-09.html";
							break;
						}
						case 6:
						{
							htmltext = "31328-14.html";
							break;
						}
						case 7:
						{
							htmltext = "31328-15.html";
							break;
						}
						case 8:
						{
							qs.setCond(6, true);
							htmltext = "31328-22.html";
							break;
						}
					}
					break;
				}
				case TRADER_VIOLET:
				{
					switch (qs.getMemoState())
					{
						case 10:
						{
							if (hasQuestItems(player, SILVER_KEY))
							{
								qs.setMemoState(11);
								qs.setCond(9, true);
								htmltext = "31386-01.html";
							}
							break;
						}
						case 11:
						{
							if (!hasQuestItems(player, SILVER_SPEAR))
							{
								htmltext = "31386-02.html";
							}
							else
							{
								giveAdena(player, 350000, true);
								addExpAndSp(player, 456893, 42112);
								qs.exitQuest(false, true);
								htmltext = "31386-03.html";
							}
							break;
						}
					}
					break;
				}
				case TOMBSTONE:
				{
					switch (qs.getMemoState())
					{
						case 8:
						{
							htmltext = "31523-01.html";
							break;
						}
						case 9:
						{
							htmltext = "31523-04.html";
							break;
						}
						case 10:
						{
							htmltext = "31523-05.html";
							break;
						}
					}
					break;
				}
				case GHOST_OF_VON_HELLMANN:
				{
					final int memoState = qs.getMemoState();
					if (memoState == 8)
					{
						htmltext = "31524-01.html";
					}
					else if (memoState == 9)
					{
						if (!hasQuestItems(player, SILVER_KEY))
						{
							htmltext = "31524-05.html";
						}
					}
					else if ((memoState == 9) || (memoState == 10))
					{
						if (hasQuestItems(player, SILVER_KEY))
						{
							qs.setMemoState(10);
							htmltext = "31524-06.html";
						}
					}
					break;
				}
				case BROKEN_BOOKSHELF:
				{
					switch (qs.getMemoState())
					{
						case 2:
						{
							if (hasQuestItems(player, SILVER_KEY))
							{
								qs.setCond(3, true);
								htmltext = "31526-01.html";
							}
							break;
						}
						case 3:
						{
							htmltext = "31526-03.html";
							break;
						}
						case 4:
						{
							if (hasQuestItems(player, LIDIAS_HAIRPIN))
							{
								htmltext = "31526-07.html";
							}
							else if (hasQuestItems(player, LIDIAS_DIARY))
							{
								htmltext = "31526-12.html";
							}
							break;
						}
						case 5:
						{
							if (hasQuestItems(player, LIDIAS_HAIRPIN, LIDIAS_DIARY))
							{
								htmltext = "31526-13.html";
							}
							break;
						}
					}
					break;
				}
				case BOX:
				{
					if (qs.getMemoState() == 11)
					{
						if (hasQuestItems(player, SILVER_KEY))
						{
							htmltext = "31530-01.html";
						}
						else if (hasQuestItems(player, SILVER_SPEAR))
						{
							htmltext = "31530-03.html";
						}
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == HIGH_PRIEST_INNOCENTIN)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
			else if (npc.getId() == TRADER_VIOLET)
			{
				final QuestState q24 = player.getQuestState(Q00024_InhabitantsOfTheForestOfTheDead.class.getSimpleName());
				if ((q24 == null))
				{
					htmltext = "31386-04.html";
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		startQuestTimer("DESPAWN", 300000, npc, null);
		npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.WHO_AWOKE_ME));
		return super.onSpawn(npc);
	}
}

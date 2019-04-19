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
package quests.Q00365_DevilsLegacy;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Devil's Legacy (365)
 * @author Adry_85
 */
public final class Q00365_DevilsLegacy extends Quest
{
	// NPCs
	private static final int COLLOB = 30092;
	private static final int RANDOLF = 30095;
	// Item
	private static final int PIRATES_TREASURE_CHEST = 5873;
	// Rewards
	private static final int ENCHANT_WEAPON_C = 951;
	private static final int ENCHANT_ARMOR_C = 952;
	private static final int ENCHANT_WEAPON_D = 955;
	private static final int ENCHANT_ARMOR_D = 956;
	private static final int THREAD = 1868;
	private static final int ANIMAL_BONE = 1872;
	private static final int COKES = 1879;
	private static final int STEEL = 1880;
	private static final int COARSE_BONE_POWDER = 1881;
	private static final int LEATHER = 1882;
	private static final int CORD = 1884;
	// Misc
	private static final int MIN_LEVEL = 39;
	// Skill
	private static SkillHolder POISON = new SkillHolder(4035, 2);
	// Mobs
	private static final Map<Integer, Double> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20836, 0.47); // pirates_zombie
		MOBS.put(20845, 0.40); // pirates_zombie_captain
		MOBS.put(21629, 0.40); // pirates_zombie_captain_1
		MOBS.put(21630, 0.40); // pirates_zombie_captain_2
	}
	
	public Q00365_DevilsLegacy()
	{
		super(365);
		addStartNpc(RANDOLF);
		addTalkId(RANDOLF, COLLOB);
		addKillId(MOBS.keySet());
		registerQuestItems(PIRATES_TREASURE_CHEST);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "30095-02.htm":
			{
				qs.startQuest();
				qs.setMemoState(1);
				htmltext = event;
				break;
			}
			case "30095-05.html":
			{
				qs.exitQuest(true, true);
				htmltext = event;
				break;
			}
			case "30095-06.html":
			{
				htmltext = event;
				break;
			}
			case "REWARD":
			{
				if (!qs.isMemoState(1))
				{
					htmltext = "30092-04.html";
				}
				else if (!hasQuestItems(player, PIRATES_TREASURE_CHEST))
				{
					htmltext = "30092-02.html";
				}
				else if (player.getAdena() < 600)
				{
					htmltext = "30092-03.html";
				}
				else
				{
					final int itemId;
					final int chance;
					if (getRandom(100) < 80)
					{
						chance = getRandom(100);
						if (chance < 1)
						{
							itemId = ENCHANT_WEAPON_D;
						}
						else if (chance < 4)
						{
							itemId = ENCHANT_ARMOR_D;
						}
						else if (chance < 36)
						{
							itemId = THREAD;
						}
						else if (chance < 68)
						{
							itemId = CORD;
						}
						else
						{
							itemId = ANIMAL_BONE;
						}
						htmltext = "30092-05.html";
					}
					else
					{
						chance = getRandom(1000);
						if (chance < 10)
						{
							itemId = ENCHANT_WEAPON_C;
						}
						else if (chance < 40)
						{
							itemId = ENCHANT_ARMOR_C;
						}
						else if (chance < 60)
						{
							itemId = ENCHANT_WEAPON_D;
						}
						else if (chance < 260)
						{
							itemId = ENCHANT_ARMOR_D;
						}
						else if (chance < 445)
						{
							itemId = COKES;
						}
						else if (chance < 630)
						{
							itemId = STEEL;
						}
						else if (chance < 815)
						{
							itemId = LEATHER;
						}
						else
						{
							itemId = COARSE_BONE_POWDER;
						}
						npc.setTarget(player);
						npc.doCast(POISON.getSkill());
						npc.setCurrentMp(npc.getMaxMp());
						qs.setMemoState(2);
						htmltext = "30092-06.html";
					}
					takeItems(player, PIRATES_TREASURE_CHEST, 1);
					takeItems(player, Inventory.ADENA_ID, 600);
					rewardItems(player, itemId, 1);
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(player, -1, 3, npc);
		if (qs != null)
		{
			giveItemRandomly(qs.getPlayer(), npc, PIRATES_TREASURE_CHEST, 1, 0, MOBS.get(npc.getId()), true);
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
			case RANDOLF:
			{
				if (qs.isCreated())
				{
					htmltext = ((player.getLevel() >= MIN_LEVEL) ? "30095-01.htm" : "30095-03.html");
				}
				else if (qs.isStarted())
				{
					if (hasQuestItems(player, PIRATES_TREASURE_CHEST))
					{
						final long chestCount = getQuestItemsCount(player, PIRATES_TREASURE_CHEST);
						giveAdena(player, (chestCount * 400) + 19800, true);
						takeItems(player, PIRATES_TREASURE_CHEST, -1);
						htmltext = "30095-04.html";
					}
					else
					{
						htmltext = "30095-07.html";
					}
				}
				break;
			}
			case COLLOB:
			{
				if (qs.isStarted())
				{
					htmltext = (qs.isMemoState(1) ? "30092-01.html" : "30092-07.html");
				}
				break;
			}
		}
		return htmltext;
	}
}

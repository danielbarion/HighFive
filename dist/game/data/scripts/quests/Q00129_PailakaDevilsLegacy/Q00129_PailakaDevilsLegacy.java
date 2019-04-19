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
package quests.Q00129_PailakaDevilsLegacy;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Pailaka - Devil's Legacy (129)
 * @author Gnacik, St3eT
 */
public final class Q00129_PailakaDevilsLegacy extends Quest
{
	// NPCs
	private static final int KAMS = 18629; // Kams (Panuka)
	private static final int ALKASO = 18631; // Alkaso (Panuka)
	private static final int LEMATAN = 18633; // Lematan
	private static final int SURVIVOR = 32498; // Devil's Isle Survivor
	private static final int SUPPORTER = 32501; // Devil's Isle Supporter
	private static final int ADVENTURER1 = 32508; // Dwarf Adventurer
	private static final int ADVENTURER2 = 32511; // Dwarf Adventurer
	// Items
	private static final int SWORD = 13042; // Ancient Legacy Sword
	private static final int ENH_SWORD1 = 13043; // Enhanced Ancient Legacy Sword
	private static final int ENH_SWORD2 = 13044; // Complete Ancient Legacy Sword
	private static final int SCROLL_1 = 13046; // Pailaka Weapon Upgrade Stage 1
	private static final int SCROLL_2 = 13047; // Pailaka Weapon Upgrade Stage 2
	private static final int SHIELD = 13032; // Pailaka Instant Shield
	private static final int HEALING_POTION = 13033; // Quick Healing Potion
	private static final int ANTIDOTE_POTION = 13048; // Pailaka Antidote
	private static final int DIVINE_POTION = 13049; // Divine Soul
	private static final int DEFENCE_POTION = 13059; // Long-Range Defense Increasing Potion
	private static final int PAILAKA_KEY = 13150; // Pailaka All-Purpose Key
	private static final int BRACELET = 13295; // Pailaka Bracelet
	private static final int ESCAPE = 736; // Scroll of Escape
	// Skills
	private static final SkillHolder VITALITY_REPLENISHING = new SkillHolder(5774, 2); // Pailaka Reward Vitality Replenishing
	// Misc
	private static final int MIN_LEVEL = 61;
	private static final int MAX_LEVEL = 67;
	private static final int EXIT_TIME = 5;
	
	public Q00129_PailakaDevilsLegacy()
	{
		super(129);
		addStartNpc(SURVIVOR);
		addFirstTalkId(SURVIVOR, SUPPORTER, ADVENTURER1, ADVENTURER2);
		addTalkId(SURVIVOR, SUPPORTER, ADVENTURER1, ADVENTURER2);
		addKillId(KAMS, ALKASO, LEMATAN);
		registerQuestItems(SWORD, ENH_SWORD1, ENH_SWORD2, SCROLL_1, SCROLL_2, SHIELD, HEALING_POTION, ANTIDOTE_POTION, DIVINE_POTION, DEFENCE_POTION, PAILAKA_KEY);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "32498-02.htm":
			case "32498-03.htm":
			case "32498-04.htm":
			{
				htmltext = event;
				break;
			}
			case "32498-05.htm":
			{
				if (!qs.isStarted())
				{
					htmltext = event;
					qs.startQuest();
				}
				break;
			}
			case "32501-02.htm":
			case "32501-04.htm":
			{
				htmltext = event;
				break;
			}
			case "32501-03.htm":
			{
				if (qs.isCond(2))
				{
					giveItems(player, SWORD, 1);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if ((npc.getId() != ADVENTURER2) || (qs == null) || !qs.isCompleted())
		{
			return npc.getId() + ".htm";
		}
		return "32511-03.htm";
	}
	
	@Override
	public final String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case SURVIVOR:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						if (player.getLevel() < MIN_LEVEL)
						{
							htmltext = "32498-11.htm";
						}
						else if (player.getLevel() > MAX_LEVEL)
						{
							htmltext = "32498-12.htm";
						}
						else
						{
							htmltext = "32498-01.htm";
						}
						break;
					}
					case State.STARTED:
					{
						htmltext = qs.getCond() > 1 ? "32498-08.htm" : "32498-06.htm";
						break;
					}
					case State.COMPLETED:
					{
						htmltext = "32498-10.htm";
						break;
					}
					default:
					{
						htmltext = "32498-01.htm";
						break;
					}
				}
				break;
			}
			case SUPPORTER:
			{
				htmltext = qs.getCond() > 2 ? "32501-04.htm" : "32501-01.htm";
				break;
			}
			case ADVENTURER1:
			{
				if (player.hasSummon())
				{
					htmltext = "32508-07.htm";
				}
				else if (hasQuestItems(player, SWORD))
				{
					if (hasQuestItems(player, SCROLL_1))
					{
						takeItems(player, SWORD, -1);
						takeItems(player, SCROLL_1, -1);
						giveItems(player, ENH_SWORD1, 1);
						htmltext = "32508-03.htm";
					}
					else
					{
						htmltext = "32508-02.htm";
					}
				}
				else if (hasQuestItems(player, ENH_SWORD1))
				{
					if (hasQuestItems(player, SCROLL_2))
					{
						takeItems(player, ENH_SWORD1, -1);
						takeItems(player, SCROLL_2, -1);
						giveItems(player, ENH_SWORD2, 1);
						htmltext = "32508-05.htm";
					}
					htmltext = "32508-04.htm";
				}
				else if (hasQuestItems(player, ENH_SWORD2))
				{
					htmltext = "32508-06.htm";
				}
				else
				{
					htmltext = "32508-00.htm";
				}
				
				break;
			}
			case ADVENTURER2:
			{
				if (player.hasSummon())
				{
					htmltext = "32511-02.htm";
				}
				else
				{
					final Instance inst = InstanceManager.getInstance().getInstance(npc.getInstanceId());
					qs.exitQuest(false, true);
					inst.setDuration(EXIT_TIME * 60000);
					inst.setEmptyDestroyTime(0);
					if (inst.containsPlayer(player.getObjectId()))
					{
						npc.setTarget(player);
						npc.doCast(VITALITY_REPLENISHING.getSkill());
						addExpAndSp(player, 10800000, 950000);
						rewardItems(player, BRACELET, 1);
						rewardItems(player, ESCAPE, 1);
					}
					else
					{
						htmltext = "32511-01.htm";
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getQuestState(player, false);
		
		if ((qs != null) && qs.isStarted())
		{
			switch (npc.getId())
			{
				case KAMS:
				{
					if (hasQuestItems(player, SWORD))
					{
						giveItems(player, SCROLL_1, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					break;
				}
				case ALKASO:
				{
					if (hasQuestItems(player, ENH_SWORD1))
					{
						giveItems(player, SCROLL_2, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					break;
				}
				case LEMATAN:
				{
					if (qs.isCond(3))
					{
						qs.setCond(4, true);
					}
					break;
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
}
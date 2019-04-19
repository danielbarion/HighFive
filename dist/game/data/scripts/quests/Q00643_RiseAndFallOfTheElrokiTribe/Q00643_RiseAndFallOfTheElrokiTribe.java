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
package quests.Q00643_RiseAndFallOfTheElrokiTribe;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Rise and Fall of the Elroki Tribe (643)
 * @author Adry_85
 */
public class Q00643_RiseAndFallOfTheElrokiTribe extends Quest
{
	// NPCs
	private static final int SINGSING = 32106;
	private static final int KARAKAWEI = 32117;
	// Item
	private static final int BONES_OF_A_PLAINS_DINOSAUR = 8776;
	// Misc
	private static final int MIN_LEVEL = 75;
	private static final int CHANCE_MOBS1 = 116;
	private static final int CHANCE_MOBS2 = 360;
	private static final int CHANCE_DEINO = 558;
	private boolean isFirstTalk = true;
	// Rewards
	private static final int[] PIECE =
	{
		8712, // Sirra's Blade Edge
		8713, // Sword of Ipos Blade
		8714, // Barakiel's Axe Piece
		8715, // Behemoth's Tuning Fork Piece
		8716, // Naga Storm Piece
		8717, // Tiphon's Spear Edge
		8718, // Shyeed's Bow Shaft
		8719, // Sobekk's Hurricane Edge
		8720, // Themis' Tongue Piece
		8721, // Cabrio's Hand Head
		8722, // Daimon Crystal Fragment
	};
	// Mobs
	private static final int[] MOBS1 =
	{
		22200, // Ornithomimus
		22201, // Ornithomimus
		22202, // Ornithomimus
		22204, // Deinonychus
		22205, // Deinonychus
		22208, // Pachycephalosaurus
		22209, // Pachycephalosaurus
		22210, // Pachycephalosaurus
		22211, // Wild Strider
		22212, // Wild Strider
		22213, // Wild Strider
		22219, // Ornithomimus
		22220, // Deinonychus
		22221, // Pachycephalosaurus
		22222, // Wild Strider
		22224, // Ornithomimus
		22225, // Deinonychus
		22226, // Pachycephalosaurus
		22227, // Wild Strider
	};
	
	private static final int[] MOBS2 =
	{
		22742, // Ornithomimus
		22743, // Deinonychus
		22744, // Ornithomimus
		22745, // Deinonychus
	};
	
	private static final int DEINONYCHUS = 22203;
	
	public Q00643_RiseAndFallOfTheElrokiTribe()
	{
		super(643);
		addStartNpc(SINGSING);
		addTalkId(SINGSING, KARAKAWEI);
		addKillId(MOBS1);
		addKillId(MOBS2);
		addKillId(DEINONYCHUS);
		registerQuestItems(BONES_OF_A_PLAINS_DINOSAUR);
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
			case "32106-02.htm":
			case "32106-04.htm":
			case "32106-05.html":
			case "32106-10.html":
			case "32106-13.html":
			case "32117-02.html":
			case "32117-06.html":
			case "32117-07.html":
			{
				htmltext = event;
				break;
			}
			case "quest_accept":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					qs.startQuest();
					htmltext = "32106-03.html";
				}
				else
				{
					htmltext = "32106-07.html";
				}
				break;
			}
			case "32106-09.html":
			{
				giveAdena(player, 1374 * getQuestItemsCount(player, BONES_OF_A_PLAINS_DINOSAUR), true);
				takeItems(player, BONES_OF_A_PLAINS_DINOSAUR, -1);
				htmltext = event;
				break;
			}
			case "exit":
			{
				if (!hasQuestItems(player, BONES_OF_A_PLAINS_DINOSAUR))
				{
					htmltext = "32106-11.html";
				}
				else
				{
					giveAdena(player, 1374 * getQuestItemsCount(player, BONES_OF_A_PLAINS_DINOSAUR), true);
					htmltext = "32106-12.html";
				}
				qs.exitQuest(true, true);
				break;
			}
			case "exchange":
			{
				if (getQuestItemsCount(player, BONES_OF_A_PLAINS_DINOSAUR) < 300)
				{
					htmltext = "32117-04.html";
				}
				else
				{
					rewardItems(player, PIECE[getRandom(PIECE.length)], 5);
					takeItems(player, BONES_OF_A_PLAINS_DINOSAUR, 300);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					htmltext = "32117-05.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 1);
		if (partyMember == null)
		{
			return super.onKill(npc, player, isSummon);
		}
		
		final int npcId = npc.getId();
		
		if (CommonUtil.contains(MOBS1, npcId))
		{
			final float chance = CHANCE_MOBS1 * Config.RATE_QUEST_DROP;
			if (getRandom(1000) < chance)
			{
				rewardItems(partyMember, BONES_OF_A_PLAINS_DINOSAUR, 2);
			}
			else
			{
				rewardItems(partyMember, BONES_OF_A_PLAINS_DINOSAUR, 1);
			}
			playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		
		if (CommonUtil.contains(MOBS2, npcId) && (getRandom(1000) < (CHANCE_MOBS2 * Config.RATE_QUEST_DROP)))
		{
			rewardItems(partyMember, BONES_OF_A_PLAINS_DINOSAUR, 1);
			playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		
		if ((npcId == DEINONYCHUS) && (getRandom(1000) < (CHANCE_DEINO * Config.RATE_QUEST_DROP)))
		{
			rewardItems(partyMember, BONES_OF_A_PLAINS_DINOSAUR, 1);
			playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = (player.getLevel() >= MIN_LEVEL) ? "32106-01.htm" : "32106-06.html";
				break;
			}
			case State.STARTED:
			{
				if (npc.getId() == SINGSING)
				{
					htmltext = hasQuestItems(player, BONES_OF_A_PLAINS_DINOSAUR) ? "32106-08.html" : "32106-14.html";
				}
				else if (npc.getId() == KARAKAWEI)
				{
					if (isFirstTalk)
					{
						isFirstTalk = false;
						htmltext = "32117-01.html";
					}
					else
					{
						htmltext = "32117-03.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

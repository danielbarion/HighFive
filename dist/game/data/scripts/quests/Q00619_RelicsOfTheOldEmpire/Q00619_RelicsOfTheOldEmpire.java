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
package quests.Q00619_RelicsOfTheOldEmpire;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Relics of the Old Empire (619)
 * @author Adry_85, jurchiks
 */
public final class Q00619_RelicsOfTheOldEmpire extends Quest
{
	private static final class DropInfo
	{
		public final double _dropChance;
		public final int _doubleItemChance;
		public final boolean _dropEntrancePass;
		
		public DropInfo(double dropChance, int doubleItemChance, boolean dropEntrancePass)
		{
			_dropChance = dropChance;
			_doubleItemChance = doubleItemChance;
			_dropEntrancePass = dropEntrancePass;
		}
		
		public double getDropChance()
		{
			return _dropChance;
		}
		
		public int getDoubleItemChance()
		{
			return _doubleItemChance;
		}
		
		public boolean getDropEntrancePass()
		{
			return _dropEntrancePass;
		}
	}
	
	// NPC
	private static final int GHOST_OF_ADVENTURER = 31538;
	// Items
	private static final int ENTRANCE_PASS_TO_THE_SEPULCHER = 7075;
	private static final int BROKEN_RELIC_PART = 7254;
	// Misc
	private static final int MIN_LEVEL = 74;
	private static final int REQUIRED_RELIC_COUNT = 1000;
	// Reward
	private static final int[] RECIPES =
	{
		6881, // Recipe: Forgotten Blade (60%)
		6883, // Recipe: Basalt Battlehammer (60%)
		6885, // Recipe: Imperial Staff (60%)
		6887, // Recipe: Angel Slayer (60%)
		6891, // Recipe: Dragon Hunter Axe (60%)
		6893, // Recipe: Saint Spear (60%)
		6895, // Recipe: Demon Splinter (60%)
		6897, // Recipe: Heavens Divider (60%)
		6899, // Recipe: Arcana Mace (60%)
		7580, // Recipe: Draconic Bow (60%)
	};
	// Mobs
	private static final Map<Integer, DropInfo> MOBS = new HashMap<>();
	static
	{
		MOBS.put(21396, new DropInfo(0.51, 0, true)); // carrion_scarab
		MOBS.put(21397, new DropInfo(0.50, 0, true)); // carrion_scarab_a
		MOBS.put(21398, new DropInfo(0.95, 0, true)); // soldier_scarab
		MOBS.put(21399, new DropInfo(0.84, 0, true)); // soldier_scarab_a
		MOBS.put(21400, new DropInfo(0.76, 0, true)); // hexa_beetle
		MOBS.put(21401, new DropInfo(0.67, 0, true)); // hexa_beetle_a
		MOBS.put(21402, new DropInfo(0.69, 0, true)); // katraxith
		MOBS.put(21403, new DropInfo(0.80, 0, true)); // katraxith_a
		MOBS.put(21404, new DropInfo(0.90, 0, true)); // tera_beetle
		MOBS.put(21405, new DropInfo(0.64, 0, true)); // tera_beetle_a
		MOBS.put(21406, new DropInfo(0.87, 0, true)); // imperial_knight
		MOBS.put(21407, new DropInfo(0.56, 0, true)); // imperial_knight_a
		MOBS.put(21408, new DropInfo(0.82, 0, true)); // imperial_guard
		MOBS.put(21409, new DropInfo(0.92, 0, true)); // imperial_guard_a
		MOBS.put(21410, new DropInfo(0.81, 0, true)); // guardian_scarab
		MOBS.put(21411, new DropInfo(0.66, 0, true)); // guardian_scarab_a
		MOBS.put(21412, new DropInfo(1.00, 6, true)); // ustralith
		MOBS.put(21413, new DropInfo(0.81, 0, true)); // ustralith_a
		MOBS.put(21414, new DropInfo(0.79, 0, true)); // imperial_assassin
		MOBS.put(21415, new DropInfo(0.80, 0, true)); // imperial_assassin_a
		MOBS.put(21416, new DropInfo(0.82, 0, true)); // imperial_warlord
		MOBS.put(21417, new DropInfo(1.00, 27, true)); // imperial_warlord_a
		MOBS.put(21418, new DropInfo(0.66, 0, true)); // imperial_highguard
		MOBS.put(21419, new DropInfo(0.67, 0, true)); // imperial_highguard_a
		MOBS.put(21420, new DropInfo(0.82, 0, true)); // ashuras
		MOBS.put(21421, new DropInfo(0.77, 0, true)); // ashuras_a
		MOBS.put(21422, new DropInfo(0.88, 0, true)); // imperial_dancer
		MOBS.put(21423, new DropInfo(0.94, 0, true)); // imperial_dancer_a
		MOBS.put(21424, new DropInfo(1.00, 19, true)); // ashikenas
		MOBS.put(21425, new DropInfo(1.00, 21, true)); // ashikenas_a
		MOBS.put(21426, new DropInfo(1.00, 8, true)); // abraxian
		MOBS.put(21427, new DropInfo(0.74, 0, true)); // abraxian_a
		MOBS.put(21428, new DropInfo(0.76, 0, true)); // hasturan
		MOBS.put(21429, new DropInfo(0.80, 0, true)); // hasturan_a
		MOBS.put(21430, new DropInfo(1.00, 10, true)); // ahrimanes
		MOBS.put(21431, new DropInfo(0.94, 0, true)); // ahrimanes_a
		MOBS.put(21432, new DropInfo(1.00, 34, true)); // chakram_beetle
		MOBS.put(21433, new DropInfo(1.00, 34, true)); // jamadhr_beetle
		MOBS.put(21434, new DropInfo(1.00, 90, true)); // priest_of_blood
		MOBS.put(21435, new DropInfo(1.00, 60, true)); // sacrifice_guide
		MOBS.put(21436, new DropInfo(1.00, 66, true)); // sacrifice_bearer
		MOBS.put(21437, new DropInfo(0.69, 0, true)); // sacrifice_scarab
		MOBS.put(21798, new DropInfo(0.33, 0, true)); // guard_skeleton_2d
		MOBS.put(21799, new DropInfo(0.61, 0, true)); // guard_skeleton_3d
		MOBS.put(21800, new DropInfo(0.31, 0, true)); // guard_undead
		MOBS.put(18120, new DropInfo(1.00, 28, false)); // r11_roomboss_strong
		MOBS.put(18121, new DropInfo(1.00, 21, false)); // r11_roomboss_weak
		MOBS.put(18122, new DropInfo(0.93, 0, false)); // r11_roomboss_teleport
		MOBS.put(18123, new DropInfo(1.00, 28, false)); // r12_roomboss_strong
		MOBS.put(18124, new DropInfo(1.00, 21, false)); // r12_roomboss_weak
		MOBS.put(18125, new DropInfo(0.93, 0, false)); // r12_roomboss_teleport
		MOBS.put(18126, new DropInfo(1.00, 28, false)); // r13_roomboss_strong
		MOBS.put(18127, new DropInfo(1.00, 21, false)); // r13_roomboss_weak
		MOBS.put(18128, new DropInfo(0.93, 0, false)); // r13_roomboss_teleport
		MOBS.put(18129, new DropInfo(1.00, 28, false)); // r14_roomboss_strong
		MOBS.put(18130, new DropInfo(1.00, 21, false)); // r14_roomboss_weak
		MOBS.put(18131, new DropInfo(0.93, 0, false)); // r14_roomboss_teleport
		MOBS.put(18132, new DropInfo(1.00, 30, false)); // r1_beatle_healer
		MOBS.put(18133, new DropInfo(1.00, 20, false)); // r1_scorpion_warrior
		MOBS.put(18134, new DropInfo(0.90, 0, false)); // r1_warrior_longatk1_h
		MOBS.put(18135, new DropInfo(1.00, 20, false)); // r1_warrior_longatk2
		MOBS.put(18136, new DropInfo(1.00, 20, false)); // r1_warrior_selfbuff
		MOBS.put(18137, new DropInfo(0.89, 0, false)); // r1_wizard_h
		MOBS.put(18138, new DropInfo(1.00, 19, false)); // r1_wizard_clanbuff
		MOBS.put(18139, new DropInfo(1.00, 17, false)); // r1_wizard_debuff
		MOBS.put(18140, new DropInfo(1.00, 19, false)); // r1_wizard_selfbuff
		MOBS.put(18141, new DropInfo(0.76, 0, false)); // r21_scarab_roombosss
		MOBS.put(18142, new DropInfo(0.76, 0, false)); // r22_scarab_roombosss
		MOBS.put(18143, new DropInfo(0.76, 0, false)); // r23_scarab_roombosss
		MOBS.put(18144, new DropInfo(0.76, 0, false)); // r24_scarab_roombosss
		MOBS.put(18145, new DropInfo(0.65, 0, false)); // r2_wizard_clanbuff
		MOBS.put(18146, new DropInfo(0.66, 0, false)); // r2_warrior_longatk2
		MOBS.put(18147, new DropInfo(0.62, 0, false)); // r2_wizard
		MOBS.put(18148, new DropInfo(0.72, 0, false)); // r2_warrior
		MOBS.put(18149, new DropInfo(0.63, 0, false)); // r2_bomb
		MOBS.put(18166, new DropInfo(0.92, 0, false)); // r3_warrior
		MOBS.put(18167, new DropInfo(0.92, 0, false)); // r3_warrior_longatk1_h
		MOBS.put(18168, new DropInfo(0.93, 0, false)); // r3_warrior_longatk2
		MOBS.put(18169, new DropInfo(0.90, 0, false)); // r3_warrior_selfbuff
		MOBS.put(18170, new DropInfo(0.90, 0, false)); // r3_wizard_h
		MOBS.put(18171, new DropInfo(0.94, 0, false)); // r3_wizard_clanbuff
		MOBS.put(18172, new DropInfo(0.89, 0, false)); // r3_wizard_selfbuff
		MOBS.put(18173, new DropInfo(0.99, 0, false)); // r41_roomboss_strong
		MOBS.put(18174, new DropInfo(1.00, 22, false)); // r41_roomboss_weak
		MOBS.put(18175, new DropInfo(0.93, 0, false)); // r41_roomboss_teleport
		MOBS.put(18176, new DropInfo(0.99, 0, false)); // r42_roomboss_strong
		MOBS.put(18177, new DropInfo(1.00, 22, false)); // r42_roomboss_weak
		MOBS.put(18178, new DropInfo(0.93, 0, false)); // r42_roomboss_teleport
		MOBS.put(18179, new DropInfo(0.99, 0, false)); // r43_roomboss_strong
		MOBS.put(18180, new DropInfo(1.00, 22, false)); // r43_roomboss_weak
		MOBS.put(18181, new DropInfo(0.93, 0, false)); // r43_roomboss_teleport
		MOBS.put(18183, new DropInfo(1.00, 22, false)); // r44_roomboss_weak
		MOBS.put(18183, new DropInfo(0.99, 0, false)); // r44_roomboss_strong
		MOBS.put(18184, new DropInfo(0.93, 0, false)); // r44_roomboss_teleport
		MOBS.put(18185, new DropInfo(1.00, 23, false)); // r4_healer_srddmagic
		MOBS.put(18186, new DropInfo(1.00, 24, false)); // r4_hearler_srdebuff
		MOBS.put(18187, new DropInfo(1.00, 20, false)); // r4_warrior
		MOBS.put(18188, new DropInfo(0.90, 0, false)); // r4_warrior_longatk1_h
		MOBS.put(18189, new DropInfo(1.00, 20, false)); // r4_warrior_longatk2
		MOBS.put(18190, new DropInfo(1.00, 20, false)); // r4_warrior_selfbuff
		MOBS.put(18191, new DropInfo(0.89, 0, false)); // r4_wizard_h
		MOBS.put(18192, new DropInfo(1.00, 19, false)); // r4_wizard_clanbuff
		MOBS.put(18193, new DropInfo(1.00, 17, false)); // r4_wizard_debuff
		MOBS.put(18194, new DropInfo(1.00, 19, false)); // r4_wizard_selfbuff
		MOBS.put(18195, new DropInfo(0.91, 0, false)); // r4_bomb
		MOBS.put(18220, new DropInfo(1.00, 24, false)); // r5_healer1
		MOBS.put(18221, new DropInfo(1.00, 27, false)); // r5_healer2
		MOBS.put(18222, new DropInfo(1.00, 21, false)); // r5_warrior
		MOBS.put(18223, new DropInfo(0.90, 0, false)); // r5_warrior_longatk1_h
		MOBS.put(18224, new DropInfo(1.00, 22, false)); // r5_warrior_longatk2
		MOBS.put(18225, new DropInfo(1.00, 21, false)); // r5_warrior_sbuff
		MOBS.put(18226, new DropInfo(0.89, 0, false)); // r5_wizard_h
		MOBS.put(18227, new DropInfo(1.00, 53, false)); // r5_wizard_clanbuff
		MOBS.put(18228, new DropInfo(1.00, 15, false)); // r5_wizard_debuff
		MOBS.put(18229, new DropInfo(1.00, 19, false)); // r5_wizard_slefbuff
		MOBS.put(18230, new DropInfo(0.49, 0, false)); // r5_bomb
	}
	
	// @formatter:off
	private static final int[] ARCHON_OF_HALISHA =
	{
		18212, 18213, 18214, 18215, 18216, 18217, 18218, 18219
	};
	// @formatter:on
	
	public Q00619_RelicsOfTheOldEmpire()
	{
		super(619);
		addStartNpc(GHOST_OF_ADVENTURER);
		addTalkId(GHOST_OF_ADVENTURER);
		addKillId(MOBS.keySet());
		addKillId(ARCHON_OF_HALISHA);
		registerQuestItems(BROKEN_RELIC_PART);
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
			case "31538-02.htm":
			{
				st.startQuest();
				htmltext = event;
				break;
			}
			case "31538-05.html":
			{
				htmltext = event;
				break;
			}
			case "31538-06.html":
			{
				if (getQuestItemsCount(player, BROKEN_RELIC_PART) >= REQUIRED_RELIC_COUNT)
				{
					rewardItems(player, RECIPES[getRandom(RECIPES.length)], 1);
					takeItems(player, BROKEN_RELIC_PART, REQUIRED_RELIC_COUNT);
					htmltext = event;
				}
				break;
			}
			case "31538-08.html":
			{
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getRandomPartyMemberState(player, -1, 3, npc);
		if (st != null)
		{
			final int npcId = npc.getId();
			if (CommonUtil.contains(ARCHON_OF_HALISHA, npcId))
			{
				final int itemCount = ((getRandom(100) < 79) ? 4 : 3);
				giveItemRandomly(player, npc, BROKEN_RELIC_PART, itemCount, 0, 1.0, true);
			}
			else
			{
				final DropInfo info = MOBS.get(npcId);
				final int itemCount;
				
				if (info.getDoubleItemChance() > 0)
				{
					itemCount = ((getRandom(100) < info.getDoubleItemChance()) ? 2 : 1);
				}
				else
				{
					itemCount = 1;
				}
				
				giveItemRandomly(player, npc, BROKEN_RELIC_PART, itemCount, 0, info.getDropChance(), true);
				
				if (info.getDropEntrancePass())
				{
					giveItemRandomly(player, npc, ENTRANCE_PASS_TO_THE_SEPULCHER, 1, 0, 1.0 / 30, false);
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (st.isCreated())
		{
			htmltext = ((player.getLevel() >= MIN_LEVEL) ? "31538-01.htm" : "31538-03.html");
		}
		else if (st.isStarted())
		{
			htmltext = ((getQuestItemsCount(player, BROKEN_RELIC_PART) >= REQUIRED_RELIC_COUNT) ? "31538-04.html" : "31538-07.html");
		}
		return htmltext;
	}
}

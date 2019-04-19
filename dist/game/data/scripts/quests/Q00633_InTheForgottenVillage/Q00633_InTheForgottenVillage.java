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
package quests.Q00633_InTheForgottenVillage;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemChanceHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * In The Forgotten Village (633)
 * @author netvirus
 */
public final class Q00633_InTheForgottenVillage extends Quest
{
	// NPC
	private static final int MINA = 31388;
	// Items
	private static final int RIB_BONE_OF_A_BLACK_MAGUS = 7544;
	private static final int ZOMBIES_LIVER = 7545;
	// Misc
	private static final int MIN_LVL = 65;
	private static final int RIB_BONE_REQUIRED_COUNT = 200;
	// Mobs
	private static final Map<Integer, ItemChanceHolder> MOBS_DROP_CHANCES = new HashMap<>();
	static
	{
		MOBS_DROP_CHANCES.put(21553, new ItemChanceHolder(ZOMBIES_LIVER, 0.417)); // Trampled Man
		MOBS_DROP_CHANCES.put(21554, new ItemChanceHolder(ZOMBIES_LIVER, 0.417)); // Trampled Man
		MOBS_DROP_CHANCES.put(21557, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.394)); // Bone Snatcher
		MOBS_DROP_CHANCES.put(21558, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.394)); // Bone Snatcher
		MOBS_DROP_CHANCES.put(21559, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.436)); // Bone Maker
		MOBS_DROP_CHANCES.put(21560, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.430)); // Bone Shaper
		MOBS_DROP_CHANCES.put(21561, new ItemChanceHolder(ZOMBIES_LIVER, 0.538)); // Sacrificed Man
		MOBS_DROP_CHANCES.put(21563, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.436)); // Bone Collector
		MOBS_DROP_CHANCES.put(21564, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.414)); // Skull Collector
		MOBS_DROP_CHANCES.put(21565, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.420)); // Bone Animator
		MOBS_DROP_CHANCES.put(21566, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.460)); // Skull Animator
		MOBS_DROP_CHANCES.put(21567, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.549)); // Bone Slayer
		MOBS_DROP_CHANCES.put(21570, new ItemChanceHolder(ZOMBIES_LIVER, 0.508)); // Ghost of Betrayer
		MOBS_DROP_CHANCES.put(21572, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.465)); // Bone Sweeper
		MOBS_DROP_CHANCES.put(21574, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.586)); // Bone Grinder
		MOBS_DROP_CHANCES.put(21575, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.329)); // Bone Grinder
		MOBS_DROP_CHANCES.put(21578, new ItemChanceHolder(ZOMBIES_LIVER, 0.649)); // Behemoth Zombie
		MOBS_DROP_CHANCES.put(21580, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.462)); // Bone Caster
		MOBS_DROP_CHANCES.put(21581, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.505)); // Bone Puppeteer
		MOBS_DROP_CHANCES.put(21583, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.475)); // Bone Scavenger
		MOBS_DROP_CHANCES.put(21584, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.475)); // Bone Scavenger
		MOBS_DROP_CHANCES.put(21596, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.543)); // Requiem Lord
		MOBS_DROP_CHANCES.put(21597, new ItemChanceHolder(ZOMBIES_LIVER, 0.510)); // Requiem Behemoth
		MOBS_DROP_CHANCES.put(21598, new ItemChanceHolder(ZOMBIES_LIVER, 0.572)); // Requiem Behemoth
		MOBS_DROP_CHANCES.put(21599, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.580)); // Requiem Priest
		MOBS_DROP_CHANCES.put(21600, new ItemChanceHolder(ZOMBIES_LIVER, 0.561)); // Requiem Behemoth
		MOBS_DROP_CHANCES.put(21601, new ItemChanceHolder(RIB_BONE_OF_A_BLACK_MAGUS, 0.677)); // Requiem Behemoth
	}
	
	public Q00633_InTheForgottenVillage()
	{
		super(633);
		addStartNpc(MINA);
		addTalkId(MINA);
		addKillId(MOBS_DROP_CHANCES.keySet());
		registerQuestItems(RIB_BONE_OF_A_BLACK_MAGUS, ZOMBIES_LIVER);
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
			case "31388-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "31388-04.html":
			case "31388-05.html":
			case "31388-06.html":
			{
				if (qs.isStarted())
				{
					htmltext = event;
				}
				break;
			}
			case "31388-07.html":
			{
				if (qs.isCond(2))
				{
					if (getQuestItemsCount(player, RIB_BONE_OF_A_BLACK_MAGUS) >= RIB_BONE_REQUIRED_COUNT)
					{
						giveAdena(player, 25000, true);
						addExpAndSp(player, 305235, 0);
						takeItems(player, RIB_BONE_OF_A_BLACK_MAGUS, -1);
						qs.setCond(1, true);
						htmltext = event;
					}
					else
					{
						htmltext = "31388-08.html";
					}
				}
				break;
			}
			case "31388-09.html":
			{
				if (qs.isStarted())
				{
					qs.exitQuest(true, true);
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
		final QuestState qs = getRandomPartyMemberState(killer, -1, 3, npc);
		if (qs != null)
		{
			final ItemChanceHolder info = MOBS_DROP_CHANCES.get(npc.getId());
			switch (info.getId())
			{
				case RIB_BONE_OF_A_BLACK_MAGUS:
				{
					if (qs.isCond(1) && giveItemRandomly(qs.getPlayer(), npc, RIB_BONE_OF_A_BLACK_MAGUS, 1, RIB_BONE_REQUIRED_COUNT, info.getChance(), true))
					{
						qs.setCond(2);
					}
					break;
				}
				case ZOMBIES_LIVER:
				{
					giveItemRandomly(qs.getPlayer(), npc, ZOMBIES_LIVER, 1, 0, info.getChance(), true);
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
			htmltext = ((player.getLevel() >= MIN_LVL) ? "31388-01.htm" : "31388-02.htm");
		}
		else if (qs.isStarted())
		{
			htmltext = ((getQuestItemsCount(player, RIB_BONE_OF_A_BLACK_MAGUS) >= RIB_BONE_REQUIRED_COUNT) ? "31388-04.html" : "31388-05.html");
		}
		return htmltext;
	}
}

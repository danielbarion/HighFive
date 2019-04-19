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
package quests.Q00373_SupplierOfReagents;

import java.util.HashMap;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q00235_MimirsElixir.Q00235_MimirsElixir;

/**
 * Supplier of Reagents (373)
 * @author Joxit
 */
public final class Q00373_SupplierOfReagents extends Quest
{
	// NPCs
	private static final int WESLEY = 30166;
	private static final int ALCHEMIST_MIXING_URN = 31149;
	// Mobs
	private static final int HALLATE_MAID = 20822;
	private static final int HALLATE_GUARDIAN = 21061;
	private static final int HAMES_ORC_SHAMAN = 21115;
	private static final int LAVA_WYRM = 21111;
	private static final int CRENDION = 20813;
	private static final int PLATINUM_TRIBE_SHAMAN = 20828;
	private static final int PLATINUM_GUARDIAN_SHAMAN = 21066;
	// Items
	private static final int REAGENT_POUNCH1 = 6007;
	private static final int REAGENT_POUNCH2 = 6008;
	private static final int REAGENT_POUNCH3 = 6009;
	private static final int REAGENT_BOX = 6010;
	private static final int WYRM_BLOOD = 6011;
	private static final int LAVA_STONE = 6012;
	private static final int MOONSTONE_SHARD = 6013;
	private static final int ROTTEN_BONE = 6014;
	private static final int DEMONS_BLOOD = 6015;
	private static final int INFERNIUM_ORE = 6016;
	private static final int BLOOD_ROOT = 6017;
	private static final int VOLCANIC_ASH = 6018;
	private static final int QUICKSILVER = 6019;
	private static final int SULFUR = 6020;
	private static final int DRACOPLASM = 6021;
	private static final int MAGMA_DUST = 6022;
	private static final int MOON_DUST = 6023;
	private static final int NECROPLASM = 6024;
	private static final int DEMONPLASM = 6025;
	private static final int INFERNO_DUST = 6026;
	private static final int DRACONIC_ESSENCE = 6027;
	private static final int FIRE_ESSENCE = 6028;
	private static final int LUNARGENT = 6029;
	private static final int MIDNIGHT_OIL = 6030;
	private static final int DEMONIC_ESSENCE = 6031;
	private static final int ABYSS_OIL = 6032;
	private static final int HELLFIRE_OIL = 6033;
	private static final int NIGHTMARE_OIL = 6034;
	private static final int PURE_SILVER = 6320;
	private static final int MIXING_MANUAL = 6317;
	private static final int WESLEYS_MIXING_STONE = 5904;
	// Misc
	private static final int MIN_LVL = 57;
	private static final HashMap<String, Integer> HTML_TO_MEMO_STATE = new HashMap<>(20);
	private static final HashMap<Integer, ItemHolder> MEMO_STATE_TO_ITEM = new HashMap<>(20);
	private static final HashMap<Integer, Entry> MEMO_STATE_TO_REWARD = new HashMap<>(15);
	
	static
	{
		// List of ingredients to mix
		HTML_TO_MEMO_STATE.put("31149-03.html", 11);
		HTML_TO_MEMO_STATE.put("31149-04.html", 12);
		HTML_TO_MEMO_STATE.put("31149-05.html", 13);
		HTML_TO_MEMO_STATE.put("31149-06.html", 14);
		HTML_TO_MEMO_STATE.put("31149-07.html", 15);
		HTML_TO_MEMO_STATE.put("31149-08.html", 16);
		HTML_TO_MEMO_STATE.put("31149-09.html", 17);
		HTML_TO_MEMO_STATE.put("31149-10.html", 18);
		HTML_TO_MEMO_STATE.put("31149-11.html", 19);
		HTML_TO_MEMO_STATE.put("31149-12.html", 20);
		HTML_TO_MEMO_STATE.put("31149-13.html", 21);
		HTML_TO_MEMO_STATE.put("31149-14.html", 22);
		HTML_TO_MEMO_STATE.put("31149-15.html", 23);
		HTML_TO_MEMO_STATE.put("31149-16.html", 24);
		MEMO_STATE_TO_ITEM.put(11, new ItemHolder(WYRM_BLOOD, 10));
		MEMO_STATE_TO_ITEM.put(12, new ItemHolder(LAVA_STONE, 10));
		MEMO_STATE_TO_ITEM.put(13, new ItemHolder(MOONSTONE_SHARD, 10));
		MEMO_STATE_TO_ITEM.put(14, new ItemHolder(ROTTEN_BONE, 10));
		MEMO_STATE_TO_ITEM.put(15, new ItemHolder(DEMONS_BLOOD, 10));
		MEMO_STATE_TO_ITEM.put(16, new ItemHolder(INFERNIUM_ORE, 10));
		MEMO_STATE_TO_ITEM.put(17, new ItemHolder(DRACOPLASM, 10));
		MEMO_STATE_TO_ITEM.put(18, new ItemHolder(MAGMA_DUST, 10));
		MEMO_STATE_TO_ITEM.put(19, new ItemHolder(MOON_DUST, 10));
		MEMO_STATE_TO_ITEM.put(20, new ItemHolder(NECROPLASM, 10));
		MEMO_STATE_TO_ITEM.put(21, new ItemHolder(DEMONPLASM, 10));
		MEMO_STATE_TO_ITEM.put(22, new ItemHolder(INFERNO_DUST, 10));
		MEMO_STATE_TO_ITEM.put(23, new ItemHolder(FIRE_ESSENCE, 1));
		MEMO_STATE_TO_ITEM.put(24, new ItemHolder(LUNARGENT, 1));
		
		// List of catalysts to mix
		HTML_TO_MEMO_STATE.put("31149-19.html", 1100);
		HTML_TO_MEMO_STATE.put("31149-20.html", 1200);
		HTML_TO_MEMO_STATE.put("31149-21.html", 1300);
		HTML_TO_MEMO_STATE.put("31149-22.html", 1400);
		HTML_TO_MEMO_STATE.put("31149-23.html", 1500);
		HTML_TO_MEMO_STATE.put("31149-24.html", 1600);
		MEMO_STATE_TO_ITEM.put(1100, new ItemHolder(BLOOD_ROOT, 1));
		MEMO_STATE_TO_ITEM.put(1200, new ItemHolder(VOLCANIC_ASH, 1));
		MEMO_STATE_TO_ITEM.put(1300, new ItemHolder(QUICKSILVER, 1));
		MEMO_STATE_TO_ITEM.put(1400, new ItemHolder(SULFUR, 1));
		MEMO_STATE_TO_ITEM.put(1500, new ItemHolder(DEMONIC_ESSENCE, 1));
		MEMO_STATE_TO_ITEM.put(1600, new ItemHolder(MIDNIGHT_OIL, 1));
		
		// The reward is the sum of ingredient and catalyst
		MEMO_STATE_TO_REWARD.put(1111, new Entry(DRACOPLASM, "31149-30.html"));
		MEMO_STATE_TO_REWARD.put(1212, new Entry(MAGMA_DUST, "31149-31.html"));
		MEMO_STATE_TO_REWARD.put(1213, new Entry(MOON_DUST, "31149-32.html"));
		MEMO_STATE_TO_REWARD.put(1114, new Entry(NECROPLASM, "31149-33.html"));
		MEMO_STATE_TO_REWARD.put(1115, new Entry(DEMONPLASM, "31149-34.html"));
		MEMO_STATE_TO_REWARD.put(1216, new Entry(INFERNO_DUST, "31149-35.html"));
		MEMO_STATE_TO_REWARD.put(1317, new Entry(DRACONIC_ESSENCE, "31149-36.html"));
		MEMO_STATE_TO_REWARD.put(1418, new Entry(FIRE_ESSENCE, "31149-37.html"));
		MEMO_STATE_TO_REWARD.put(1319, new Entry(LUNARGENT, "31149-38.html"));
		MEMO_STATE_TO_REWARD.put(1320, new Entry(MIDNIGHT_OIL, "31149-39.html"));
		MEMO_STATE_TO_REWARD.put(1421, new Entry(DEMONIC_ESSENCE, "31149-40.html"));
		MEMO_STATE_TO_REWARD.put(1422, new Entry(ABYSS_OIL, "31149-41.html"));
		MEMO_STATE_TO_REWARD.put(1523, new Entry(HELLFIRE_OIL, "31149-42.html"));
		MEMO_STATE_TO_REWARD.put(1624, new Entry(NIGHTMARE_OIL, "31149-43.html"));
		MEMO_STATE_TO_REWARD.put(1324, new Entry(PURE_SILVER, "31149-46.html"));
	}
	
	public Q00373_SupplierOfReagents()
	{
		super(373);
		addStartNpc(WESLEY);
		addKillId(HALLATE_GUARDIAN, HALLATE_MAID, HAMES_ORC_SHAMAN, LAVA_WYRM, CRENDION, PLATINUM_GUARDIAN_SHAMAN, PLATINUM_TRIBE_SHAMAN);
		addTalkId(WESLEY, ALCHEMIST_MIXING_URN);
		registerQuestItems(WESLEYS_MIXING_STONE, MIXING_MANUAL);
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
			case "30166-03.htm":
			case "30166-06.html":
			case "30166-04a.html":
			case "30166-04b.html":
			case "30166-04c.html":
			case "30166-04d.html":
			case "31149-18.html":
			{
				htmltext = event;
				break;
			}
			case "30166-04.html":
			{
				if ((player.getLevel() >= MIN_LVL) && qs.isCreated())
				{
					giveItems(player, WESLEYS_MIXING_STONE, 1);
					giveItems(player, MIXING_MANUAL, 1);
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30166-07.html":
			{
				qs.exitQuest(true, true);
				htmltext = event;
				break;
			}
			case "31149-02.html":
			{
				qs.setMemoState(0);
				qs.setMemoStateEx(1, 0);
				htmltext = event;
				break;
			}
			case "31149-03.html":
			case "31149-04.html":
			case "31149-05.html":
			case "31149-06.html":
			case "31149-07.html":
			case "31149-08.html":
			case "31149-09.html":
			case "31149-10.html":
			case "31149-11.html":
			case "31149-12.html":
			case "31149-13.html":
			case "31149-14.html":
			case "31149-15.html":
			case "31149-16.html":
			case "31149-19.html":
			case "31149-20.html":
			case "31149-21.html":
			case "31149-22.html":
			case "31149-23.html":
			case "31149-24.html":
			{
				final int memoState = HTML_TO_MEMO_STATE.get(event);
				if (hasItem(player, MEMO_STATE_TO_ITEM.get(memoState)))
				{
					// If the player has the chosen item (ingredient or catalyst), we save it (for the catalyst or the reward)
					qs.setMemoState(qs.getMemoState() + memoState);
					htmltext = event;
					playSound(player, QuestSound.SKILLSOUND_LIQUID_MIX);
				}
				else
				{
					// If the player has not the chosen catalyst, we take the ingredient previously saved (if not null)
					takeItem(player, MEMO_STATE_TO_ITEM.get(qs.getMemoState()));
					if (event.equals("31149-19.html"))
					{
						htmltext = "31149-25.html";
					}
					else
					{
						htmltext = "31149-17.html";
					}
				}
				break;
			}
			case "31149-26.html":
			{
				if (qs.isMemoState(1324))
				{
					htmltext = "31149-26a.html";
				}
				else
				{
					htmltext = event;
				}
				break;
			}
			case "31149-27.html":
			{
				qs.setMemoStateEx(1, 1); // Temperature Salamander
				htmltext = event;
				break;
			}
			case "31149-28a.html":
			{
				if (getRandom(100) < 33)
				{
					qs.setMemoStateEx(1, 3); // Temperature Ifrit
				}
				else
				{
					qs.setMemoStateEx(1, 0);
				}
				htmltext = event;
				break;
			}
			case "31149-29a.html":
			{
				if (getRandom(100) < 20)
				{
					qs.setMemoStateEx(1, 5); // Temperature Phoenix
				}
				else
				{
					qs.setMemoStateEx(1, 0);
				}
				htmltext = event;
				break;
			}
			case "mixitems":
			{
				final int memoState = qs.getMemoState();
				final ItemHolder item1 = MEMO_STATE_TO_ITEM.get(memoState % 100);
				final ItemHolder item2 = MEMO_STATE_TO_ITEM.get((memoState / 100) * 100);
				final Entry reward = MEMO_STATE_TO_REWARD.get(memoState);
				final QuestState q235 = player.getQuestState(Q00235_MimirsElixir.class.getSimpleName());
				if ((reward == null) || qs.isMemoStateEx(1, 0))
				{
					takeItem(player, item1);
					takeItem(player, item2);
					htmltext = (reward == null) ? "31149-44.html" : "31149-45.html";
					playSound(player, QuestSound.SKILLSOUND_LIQUID_FAIL);
				}
				else if ((memoState != 1324) || ((memoState == 1324) && (q235 != null) && q235.isStarted() && !hasQuestItems(player, reward.getItem())))
				{
					if ((item1 != null) && (item2 != null) && hasItem(player, item1) && hasItem(player, item2))
					{
						takeItem(player, item1);
						takeItem(player, item2);
						giveItems(player, reward.getItem(), (memoState == 1324) ? 1 : qs.getMemoStateEx(1));
						qs.setMemoState(0);
						qs.setMemoStateEx(1, 0);
						htmltext = reward.getHtml();
						playSound(player, QuestSound.SKILLSOUND_LIQUID_SUCCESS);
					}
					else
					{
						htmltext = "31149-44.html";
						playSound(player, QuestSound.SKILLSOUND_LIQUID_FAIL);
					}
				}
				else
				{
					htmltext = "31149-44.html";
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
			switch (npc.getId())
			{
				case HALLATE_GUARDIAN:
				{
					final int chance = getRandom(1000);
					if (chance < 766)
					{
						giveItemRandomly(qs.getPlayer(), npc, DEMONS_BLOOD, 3, 0, 1, true);
					}
					else if (chance < 876)
					{
						giveItemRandomly(qs.getPlayer(), npc, MOONSTONE_SHARD, 1, 0, 1, true);
					}
					break;
				}
				case HALLATE_MAID:
				{
					final int chance = getRandom(100);
					if (chance < 45)
					{
						giveItemRandomly(qs.getPlayer(), npc, REAGENT_POUNCH1, 1, 0, 1, true);
					}
					else if (chance < 65)
					{
						giveItemRandomly(qs.getPlayer(), npc, VOLCANIC_ASH, 1, 0, 1, true);
					}
					break;
				}
				case HAMES_ORC_SHAMAN:
				{
					if (getRandom(1000) < 616)
					{
						giveItemRandomly(qs.getPlayer(), npc, REAGENT_POUNCH3, 1, 0, 1, true);
					}
					break;
				}
				case LAVA_WYRM:
				{
					final int chance = getRandom(1000);
					if (chance < 666)
					{
						giveItemRandomly(qs.getPlayer(), npc, WYRM_BLOOD, 1, 0, 1, true);
					}
					else if (chance < 989)
					{
						giveItemRandomly(qs.getPlayer(), npc, LAVA_STONE, 1, 0, 1, true);
					}
					break;
				}
				case CRENDION:
				{
					if (getRandom(1000) < 618)
					{
						giveItemRandomly(qs.getPlayer(), npc, ROTTEN_BONE, 1, 0, 1, true);
					}
					else
					{
						giveItemRandomly(qs.getPlayer(), npc, QUICKSILVER, 1, 0, 1, true);
					}
					break;
				}
				case PLATINUM_GUARDIAN_SHAMAN:
				{
					if (getRandom(1000) < 444)
					{
						giveItemRandomly(qs.getPlayer(), npc, REAGENT_BOX, 1, 0, 1, true);
					}
					break;
				}
				case PLATINUM_TRIBE_SHAMAN:
				{
					if (getRandom(1000) < 658)
					{
						giveItemRandomly(qs.getPlayer(), npc, REAGENT_POUNCH2, 1, 0, 1, true);
					}
					else
					{
						giveItemRandomly(qs.getPlayer(), npc, QUICKSILVER, 2, 0, 1, true);
					}
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		
		if (qs.isCreated())
		{
			if (talker.getLevel() < MIN_LVL)
			{
				htmltext = "30166-01.html";
			}
			else
			{
				htmltext = "30166-02.htm";
			}
		}
		else if (qs.isStarted())
		{
			if (npc.getId() == WESLEY)
			{
				htmltext = "30166-05.html";
			}
			else
			{
				htmltext = "31149-01.html";
			}
		}
		
		return htmltext;
	}
	
	private static final class Entry
	{
		private final int item;
		private final String html;
		
		public Entry(int item, String html)
		{
			this.item = item;
			this.html = html;
		}
		
		public int getItem()
		{
			return item;
		}
		
		public String getHtml()
		{
			return html;
		}
	}
}

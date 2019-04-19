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
package quests.Q00620_FourGoblets;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.instancemanager.FourSepulchersManager;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Four Goblets (620)
 * @author Adry_85
 * @since 2.6.0.0
 */
public class Q00620_FourGoblets extends Quest
{
	// NPCs
	private static final int GHOST_OF_WIGOTH_1 = 31452;
	private static final int NAMELESS_SPIRIT = 31453;
	private static final int GHOST_OF_WIGOTH_2 = 31454;
	private static final int GHOST_CHAMBERLAIN_OF_ELMOREDEN_1 = 31919;
	private static final int CONQUERORS_SEPULCHER_MANAGER = 31921;
	private static final int EMPERORS_SEPULCHER_MANAGER = 31922;
	private static final int GREAT_SAGES_SEPULCHER_MANAGER = 31923;
	private static final int JUDGES_SEPULCHER_MANAGER = 31924;
	// Items
	private static final int BROKEN_RELIC_PART = 7254;
	private static final int SEALED_BOX = 7255;
	private static final int GOBLET_OF_ALECTIA = 7256;
	private static final int GOBLET_OF_TISHAS = 7257;
	private static final int GOBLET_OF_MEKARA = 7258;
	private static final int GOBLET_OF_MORIGUL = 7259;
	private static final int CHAPEL_KEY = 7260;
	private static final int USED_GRAVE_PASS = 7261;
	private static final int ANTIQUE_BROOCH = 7262;
	// Misc
	private static final int MIN_LEVEL = 74;
	// Locations
	private static final Location ENTER_LOC = new Location(170000, -88250, -2912, 0);
	private static final Location EXIT_LOC = new Location(169584, -91008, -2912, 0);
	// Rewards
	private static final ItemHolder CORD = new ItemHolder(1884, 42);
	private static final ItemHolder METALLIC_FIBER = new ItemHolder(1895, 36);
	private static final ItemHolder MITHRIL_ORE = new ItemHolder(1876, 4);
	private static final ItemHolder COARSE_BONE_POWDER = new ItemHolder(1881, 6);
	private static final ItemHolder METALLIC_THREAD = new ItemHolder(5549, 8);
	private static final ItemHolder ORIHARUKON_ORE = new ItemHolder(1874, 1);
	private static final ItemHolder COMPOUND_BRAID = new ItemHolder(1889, 1);
	private static final ItemHolder ADAMANTITE_NUGGET = new ItemHolder(1877, 1);
	private static final ItemHolder CRAFTED_LEATHER = new ItemHolder(1894, 1);
	private static final ItemHolder ASOFE = new ItemHolder(4043, 1);
	private static final ItemHolder SYNTETHIC_COKES = new ItemHolder(1888, 1);
	private static final ItemHolder MOLD_LUBRICANT = new ItemHolder(4040, 1);
	private static final ItemHolder MITHRIL_ALLOY = new ItemHolder(1890, 1);
	private static final ItemHolder DURABLE_METAL_PLATE = new ItemHolder(5550, 1);
	private static final ItemHolder ORIHARUKON = new ItemHolder(1893, 1);
	private static final ItemHolder MAESTRO_ANVIL_LOCK = new ItemHolder(4046, 1);
	private static final ItemHolder MAESTRO_MOLD = new ItemHolder(4048, 1);
	private static final ItemHolder BRAIDED_HEMP = new ItemHolder(1878, 8);
	private static final ItemHolder LEATHER = new ItemHolder(1882, 24);
	private static final ItemHolder COKES = new ItemHolder(1879, 4);
	private static final ItemHolder STEEL = new ItemHolder(1880, 6);
	private static final ItemHolder HIGH_GRADE_SUEDE = new ItemHolder(1885, 6);
	private static final ItemHolder STONE_OF_PURITY = new ItemHolder(1875, 1);
	private static final ItemHolder STEEL_MOLD = new ItemHolder(1883, 1);
	private static final ItemHolder METAL_HARDENER = new ItemHolder(5220, 1);
	private static final ItemHolder MOLD_GLUE = new ItemHolder(4039, 1);
	private static final ItemHolder THONS = new ItemHolder(4044, 1);
	private static final ItemHolder VARNISH_OF_PURITY = new ItemHolder(1887, 1);
	private static final ItemHolder ENRIA = new ItemHolder(4042, 1);
	private static final ItemHolder SILVER_MOLD = new ItemHolder(1886, 1);
	private static final ItemHolder MOLD_HARDENER = new ItemHolder(4041, 1);
	private static final ItemHolder BLACKSMITHS_FRAMES = new ItemHolder(1892, 1);
	private static final ItemHolder ARTISANS_FRAMES = new ItemHolder(1891, 1);
	private static final ItemHolder CRAFTSMAN_MOLD = new ItemHolder(4047, 1);
	private static final ItemHolder ENCHANT_ARMOR_A_GRADE = new ItemHolder(730, 1);
	private static final ItemHolder ENCHANT_ARMOR_B_GRADE = new ItemHolder(948, 1);
	private static final ItemHolder ENCHANT_ARMOR_S_GRADE = new ItemHolder(960, 1);
	private static final ItemHolder ENCHANT_WEAPON_A_GRADE = new ItemHolder(729, 1);
	private static final ItemHolder ENCHANT_WEAPON_B_GRADE = new ItemHolder(947, 1);
	private static final ItemHolder ENCHANT_WEAPON_S_GRADE = new ItemHolder(959, 1);
	private static final ItemHolder SEALED_TATEOSSIAN_EARRING_PART = new ItemHolder(6698, 1);
	private static final ItemHolder SEALED_TATEOSSIAN_RING_GEM = new ItemHolder(6699, 1);
	private static final ItemHolder SEALED_TATEOSSIAN_NECKLACE_CHAIN = new ItemHolder(6700, 1);
	private static final ItemHolder SEALED_IMPERIAL_CRUSADER_BREASTPLATE_PART = new ItemHolder(6701, 1);
	private static final ItemHolder SEALED_IMPERIAL_CRUSADER_GAITERS_PATTERN = new ItemHolder(6702, 1);
	private static final ItemHolder SEALED_IMPERIAL_CRUSADER_GAUNTLETS_DESIGN = new ItemHolder(6703, 1);
	private static final ItemHolder SEALED_IMPERIAL_CRUSADER_BOOTS_DESIGN = new ItemHolder(6704, 1);
	private static final ItemHolder SEALED_IMPERIAL_CRUSADER_SHIELD_PART = new ItemHolder(6705, 1);
	private static final ItemHolder SEALED_IMPERIAL_CRUSADER_HELMET_PATTERN = new ItemHolder(6706, 1);
	private static final ItemHolder SEALED_DRACONIC_LEATHER_ARMOR_PART = new ItemHolder(6707, 1);
	private static final ItemHolder SEALED_DRACONIC_LEATHER_GLOVES_FABRIC = new ItemHolder(6708, 1);
	private static final ItemHolder SEALED_DRACONIC_LEATHER_BOOTS_DESIGN = new ItemHolder(6709, 1);
	private static final ItemHolder SEALED_DRACONIC_LEATHER_HELMET_PATTERN = new ItemHolder(6710, 1);
	private static final ItemHolder SEALED_MAJOR_ARCANA_ROBE_PART = new ItemHolder(6711, 1);
	private static final ItemHolder SEALED_MAJOR_ARCANA_GLOVES_FABRIC = new ItemHolder(6712, 1);
	private static final ItemHolder SEALED_MAJOR_ARCANA_BOOTS_DESIGN = new ItemHolder(6713, 1);
	private static final ItemHolder SEALED_MAJOR_ARCANA_CIRCLET_PATTERN = new ItemHolder(6714, 1);
	private static final ItemHolder FORGOTTEN_BLADE_EDGE = new ItemHolder(6688, 1);
	private static final ItemHolder BASALT_BATTLEHAMMER_HEAD = new ItemHolder(6689, 1);
	private static final ItemHolder IMPERIAL_STAFF_HEAD = new ItemHolder(6690, 1);
	private static final ItemHolder ANGEL_SLAYER_BLADE = new ItemHolder(6691, 1);
	private static final ItemHolder DRACONIC_BOW_SHAFT = new ItemHolder(7579, 1);
	private static final ItemHolder DRAGON_HUNTER_AXE_BLADE = new ItemHolder(6693, 1);
	private static final ItemHolder SAINT_SPEAR_BLADE = new ItemHolder(6694, 1);
	private static final ItemHolder DEMON_SPLINTER_BLADE = new ItemHolder(6695, 1);
	private static final ItemHolder HEAVENS_DIVIDER_EDGE = new ItemHolder(6696, 1);
	private static final ItemHolder ARCANA_MACE_HEAD = new ItemHolder(6697, 1);
	// Mobs
	private static final int HALISHA_ALECTIA = 25339;
	private static final int HALISHA_TISHAS = 25342;
	private static final int HALISHA_MEKARA = 25346;
	private static final int HALISHA_MORIGUL = 25349;
	private static final Map<Integer, Double> MOB1 = new HashMap<>();
	private static final Map<Integer, Integer> MOB2 = new HashMap<>();
	private static final Map<Integer, Integer> MOB3 = new HashMap<>();
	static
	{
		MOB1.put(18141, 0.9);
		MOB1.put(18142, 0.9);
		MOB1.put(18143, 0.9);
		MOB1.put(18144, 0.9);
		MOB1.put(18145, 0.76);
		MOB1.put(18146, 0.78);
		MOB1.put(18147, 0.73);
		MOB1.put(18148, 0.85);
		MOB1.put(18149, 0.75);
		MOB1.put(18230, 0.58);
		MOB2.put(18120, 51);
		MOB2.put(18121, 44);
		MOB2.put(18122, 10);
		MOB2.put(18123, 51);
		MOB2.put(18124, 44);
		MOB2.put(18125, 10);
		MOB2.put(18126, 51);
		MOB2.put(18127, 44);
		MOB2.put(18128, 10);
		MOB2.put(18129, 51);
		MOB2.put(18130, 44);
		MOB2.put(18131, 10);
		MOB2.put(18132, 54);
		MOB2.put(18133, 42);
		MOB2.put(18134, 7);
		MOB2.put(18135, 42);
		MOB2.put(18136, 42);
		MOB2.put(18137, 6);
		MOB2.put(18138, 41);
		MOB2.put(18139, 39);
		MOB2.put(18140, 41);
		MOB2.put(18166, 8);
		MOB2.put(18167, 7);
		MOB2.put(18168, 10);
		MOB2.put(18169, 6);
		MOB2.put(18170, 7);
		MOB2.put(18171, 11);
		MOB2.put(18172, 6);
		MOB2.put(18173, 17);
		MOB2.put(18174, 45);
		MOB2.put(18175, 10);
		MOB2.put(18176, 17);
		MOB2.put(18177, 45);
		MOB2.put(18178, 10);
		MOB2.put(18179, 17);
		MOB2.put(18180, 45);
		MOB2.put(18181, 10);
		MOB2.put(18182, 17);
		MOB2.put(18183, 45);
		MOB2.put(18184, 10);
		MOB2.put(18185, 46);
		MOB2.put(18186, 47);
		MOB2.put(18187, 42);
		MOB2.put(18188, 7);
		MOB2.put(18189, 42);
		MOB2.put(18190, 42);
		MOB2.put(18191, 6);
		MOB2.put(18192, 41);
		MOB2.put(18193, 39);
		MOB2.put(18194, 41);
		MOB2.put(18195, 8);
		MOB2.put(18220, 47);
		MOB2.put(18221, 51);
		MOB2.put(18222, 43);
		MOB2.put(18223, 7);
		MOB2.put(18224, 44);
		MOB2.put(18225, 43);
		MOB2.put(18226, 6);
		MOB2.put(18227, 82);
		MOB2.put(18229, 41);
		MOB3.put(18212, 50);
		MOB3.put(18213, 50);
		MOB3.put(18214, 50);
		MOB3.put(18215, 50);
		MOB3.put(18216, 50);
		MOB3.put(18217, 50);
		MOB3.put(18218, 50);
		MOB3.put(18219, 50);
	}
	
	public Q00620_FourGoblets()
	{
		super(620);
		addStartNpc(NAMELESS_SPIRIT);
		addTalkId(NAMELESS_SPIRIT, GHOST_OF_WIGOTH_1, GHOST_OF_WIGOTH_2, GHOST_CHAMBERLAIN_OF_ELMOREDEN_1, CONQUERORS_SEPULCHER_MANAGER, EMPERORS_SEPULCHER_MANAGER, GREAT_SAGES_SEPULCHER_MANAGER, JUDGES_SEPULCHER_MANAGER);
		addKillId(HALISHA_ALECTIA, HALISHA_TISHAS, HALISHA_MEKARA, HALISHA_MORIGUL);
		addKillId(MOB1.keySet());
		addKillId(MOB2.keySet());
		addKillId(MOB3.keySet());
		registerQuestItems(SEALED_BOX, GOBLET_OF_ALECTIA, GOBLET_OF_TISHAS, GOBLET_OF_MEKARA, GOBLET_OF_MORIGUL, USED_GRAVE_PASS);
	}
	
	@Override
	public void actionForEachPlayer(L2PcInstance player, L2Npc npc, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			switch (npc.getId())
			{
				case HALISHA_ALECTIA:
				{
					if (!hasQuestItems(player, GOBLET_OF_ALECTIA) && !hasQuestItems(player, ANTIQUE_BROOCH))
					{
						giveItems(player, GOBLET_OF_ALECTIA, 1);
					}
					
					st.setMemoStateEx(1, 2);
					break;
				}
				case HALISHA_TISHAS:
				{
					if (!hasQuestItems(player, GOBLET_OF_TISHAS) && !hasQuestItems(player, ANTIQUE_BROOCH))
					{
						giveItems(player, GOBLET_OF_TISHAS, 1);
					}
					
					st.setMemoStateEx(1, 2);
					break;
				}
				case HALISHA_MEKARA:
				{
					if (!hasQuestItems(player, GOBLET_OF_MEKARA) && !hasQuestItems(player, ANTIQUE_BROOCH))
					{
						giveItems(player, GOBLET_OF_MEKARA, 1);
					}
					
					st.setMemoStateEx(1, 2);
					break;
				}
				case HALISHA_MORIGUL:
				{
					if (!hasQuestItems(player, GOBLET_OF_MORIGUL) && !hasQuestItems(player, ANTIQUE_BROOCH))
					{
						giveItems(player, GOBLET_OF_MORIGUL, 1);
					}
					
					st.setMemoStateEx(1, 2);
					break;
				}
			}
		}
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
			case "31453-02.htm":
			case "31453-03.htm":
			case "31453-04.htm":
			case "31453-05.htm":
			case "31453-06.htm":
			case "31453-07.htm":
			case "31453-08.htm":
			case "31453-09.htm":
			case "31453-10.htm":
			case "31453-11.htm":
			case "31453-16.html":
			case "31453-17.html":
			case "31453-18.html":
			case "31453-19.html":
			case "31453-20.html":
			case "31453-21.html":
			case "31453-22.html":
			case "31453-23.html":
			case "31453-24.html":
			case "31453-25.html":
			case "31453-27.html":
			case "31452-04.html":
			{
				htmltext = event;
				break;
			}
			case "31453-12.htm":
			{
				st.setMemoState(0);
				st.startQuest();
				if (hasQuestItems(player, ANTIQUE_BROOCH))
				{
					st.setCond(2);
				}
				htmltext = event;
				break;
			}
			case "31453-15.html":
			{
				takeItems(player, -1, CHAPEL_KEY, USED_GRAVE_PASS);
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
			case "31453-28.html":
			{
				if (hasQuestItems(player, GOBLET_OF_ALECTIA, GOBLET_OF_TISHAS, GOBLET_OF_MEKARA, GOBLET_OF_MORIGUL))
				{
					giveItems(player, ANTIQUE_BROOCH, 1);
					st.setCond(2, true);
					takeItems(player, 1, GOBLET_OF_ALECTIA, GOBLET_OF_TISHAS, GOBLET_OF_MEKARA, GOBLET_OF_MORIGUL);
					htmltext = event;
				}
				break;
			}
			case "31454-02.html":
			{
				player.teleToLocation(ENTER_LOC, 0);
				htmltext = event;
				break;
			}
			case "31454-04.html":
			{
				final int memoStateEx = st.getMemoStateEx(1);
				if (((memoStateEx == 2) || (memoStateEx == 3)) && (getQuestItemsCount(player, BROKEN_RELIC_PART) >= 1000))
				{
					htmltext = event;
				}
				break;
			}
			case "6881":
			case "6883":
			case "6885":
			case "6887":
			case "6891":
			case "6893":
			case "6895":
			case "6897":
			case "6899":
			case "7580":
			{
				final int memoStateEx = st.getMemoStateEx(1);
				if (((memoStateEx == 2) || (memoStateEx == 3)) && (getQuestItemsCount(player, BROKEN_RELIC_PART) >= 1000))
				{
					giveItems(player, Integer.valueOf(event), 1);
					takeItems(player, BROKEN_RELIC_PART, 1000);
					htmltext = "31454-05.html";
				}
				break;
			}
			case "31454-07.html":
			{
				final int memoStateEx = st.getMemoStateEx(1);
				if (((memoStateEx == 2) || (memoStateEx == 3)) && hasQuestItems(player, SEALED_BOX))
				{
					if (getRandom(100) < 100) // TODO (Adry_85): Check random function.
					{
						
						boolean i2 = getReward(player);
						htmltext = ((i2 == true) ? event : "31454-08.html");
					}
					else
					{
						takeItems(player, SEALED_BOX, 1);
						htmltext = "31454-09.html";
					}
				}
				break;
			}
			case "EXIT":
			{
				takeItems(player, CHAPEL_KEY, -1);
				player.teleToLocation(EXIT_LOC, 0);
				return "";
			}
			case "31919-02.html":
			{
				if (hasQuestItems(player, SEALED_BOX))
				{
					if (getRandom(100) < 50)
					{
						
						boolean i2 = getReward(player);
						htmltext = ((i2 == true) ? event : "31919-03.html");
					}
					else
					{
						takeItems(player, SEALED_BOX, 1);
						htmltext = "31919-04.html";
					}
				}
				else
				{
					htmltext = "31919-05.html";
				}
				break;
			}
			case "ENTER":
			{
				// TODO (Adry_85): Need rework
				FourSepulchersManager.getInstance().tryEntry(npc, player);
				return "";
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		switch (npc.getId())
		{
			case HALISHA_ALECTIA:
			case HALISHA_TISHAS:
			case HALISHA_MEKARA:
			case HALISHA_MORIGUL:
			{
				executeForEachPlayer(player, npc, isSummon, true, false);
				break;
			}
			default:
			{
				final QuestState st = getRandomPartyMemberState(player, -1, 3, npc);
				if (st != null)
				{
					int npcId = npc.getId();
					if (MOB1.containsKey(npcId))
					{
						giveItemRandomly(st.getPlayer(), npc, SEALED_BOX, 1, 0, MOB1.get(npcId), true);
					}
					else if (MOB2.containsKey(npcId))
					{
						final int itemCount = ((getRandom(100) < MOB2.get(npc.getId())) ? 2 : 1);
						giveItemRandomly(st.getPlayer(), npc, SEALED_BOX, itemCount, 0, 1.0, true);
					}
					else
					{
						final int itemCount = ((getRandom(100) < MOB3.get(npc.getId())) ? 5 : 4);
						giveItemRandomly(st.getPlayer(), npc, SEALED_BOX, itemCount, 0, 1.0, true);
					}
				}
				break;
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
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "31453-01.htm" : "31453-13.html";
		}
		else if (st.isStarted())
		{
			switch (npc.getId())
			{
				case NAMELESS_SPIRIT:
				{
					if (!hasQuestItems(player, ANTIQUE_BROOCH))
					{
						if (hasQuestItems(player, GOBLET_OF_ALECTIA, GOBLET_OF_TISHAS, GOBLET_OF_MEKARA, GOBLET_OF_MORIGUL))
						{
							htmltext = "31453-26.html";
						}
						else
						{
							htmltext = "31453-14.html";
						}
					}
					else
					{
						htmltext = "31453-29.html";
					}
					break;
				}
				case GHOST_OF_WIGOTH_1:
				{
					if (!hasQuestItems(player, ANTIQUE_BROOCH))
					{
						if (hasQuestItems(player, GOBLET_OF_ALECTIA, GOBLET_OF_TISHAS, GOBLET_OF_MEKARA, GOBLET_OF_MORIGUL))
						{
							htmltext = "31452-01.html";
						}
						else
						{
							if ((getQuestItemsCount(player, GOBLET_OF_ALECTIA, GOBLET_OF_TISHAS, GOBLET_OF_MEKARA, GOBLET_OF_MORIGUL)) < 3)
							{
								htmltext = "31452-02.html";
							}
							else
							{
								htmltext = "31452-03.html";
							}
						}
					}
					else
					{
						htmltext = "31452-05.html";
					}
					break;
				}
				case GHOST_OF_WIGOTH_2:
				{
					final int memoStateEx = st.getMemoStateEx(1);
					final long brokenRelicPartCount = getQuestItemsCount(player, BROKEN_RELIC_PART);
					if (memoStateEx == 2)
					{
						if (hasQuestItems(player, GOBLET_OF_ALECTIA, GOBLET_OF_TISHAS, GOBLET_OF_MEKARA, GOBLET_OF_MORIGUL))
						{
							if (!hasQuestItems(player, SEALED_BOX))
							{
								htmltext = ((brokenRelicPartCount < 1000) ? "31454-01.html" : "31454-03.html");
							}
							else
							{
								htmltext = ((brokenRelicPartCount < 1000) ? "31454-06.html" : "31454-10.html");
							}
							
							st.setMemoStateEx(1, 3);
						}
						else
						{
							if (!hasQuestItems(player, SEALED_BOX))
							{
								htmltext = ((brokenRelicPartCount < 1000) ? "31454-11.html" : "31454-12.html");
							}
							else
							{
								htmltext = ((brokenRelicPartCount < 1000) ? "31454-13.html" : "31454-14.html");
							}
							
							st.setMemoStateEx(1, 3);
						}
					}
					else if (memoStateEx == 3)
					{
						if (!hasQuestItems(player, SEALED_BOX))
						{
							htmltext = ((brokenRelicPartCount < 1000) ? "31454-15.html" : "31454-12.html");
						}
						else
						{
							htmltext = ((brokenRelicPartCount < 1000) ? "31454-13.html" : "31454-14.html");
						}
						
						st.setMemoStateEx(1, 3);
					}
					break;
				}
				case GHOST_CHAMBERLAIN_OF_ELMOREDEN_1:
				{
					htmltext = "31919-01.html";
					break;
				}
				case CONQUERORS_SEPULCHER_MANAGER:
				{
					htmltext = "31921-01.html";
					break;
				}
				case EMPERORS_SEPULCHER_MANAGER:
				{
					htmltext = "31922-01.html";
					break;
				}
				case GREAT_SAGES_SEPULCHER_MANAGER:
				{
					htmltext = "31923-01.html";
					break;
				}
				case JUDGES_SEPULCHER_MANAGER:
				{
					htmltext = "31924-01.html";
					break;
				}
			}
		}
		return htmltext;
	}
	
	private boolean getReward(L2PcInstance player)
	{
		boolean i2 = false;
		switch (getRandom(5))
		{
			case 0:
			{
				i2 = true;
				giveAdena(player, 10000, true);
				break;
			}
			case 1:
			{
				if (getRandom(1000) < 848)
				{
					i2 = true;
					int i1 = getRandom(1000);
					if (i1 < 43)
					{
						giveItems(player, CORD);
					}
					else if (i1 < 66)
					{
						giveItems(player, METALLIC_FIBER);
					}
					else if (i1 < 184)
					{
						giveItems(player, MITHRIL_ORE);
					}
					else if (i1 < 250)
					{
						giveItems(player, COARSE_BONE_POWDER);
					}
					else if (i1 < 287)
					{
						giveItems(player, METALLIC_THREAD);
					}
					else if (i1 < 484)
					{
						giveItems(player, ORIHARUKON_ORE);
					}
					else if (i1 < 681)
					{
						giveItems(player, COMPOUND_BRAID);
					}
					else if (i1 < 799)
					{
						giveItems(player, ADAMANTITE_NUGGET);
					}
					else if (i1 < 902)
					{
						giveItems(player, CRAFTED_LEATHER);
					}
					else
					{
						giveItems(player, ASOFE);
					}
				}
				
				if (getRandom(1000) < 323)
				{
					i2 = true;
					int i1 = getRandom(1000);
					if (i1 < 335)
					{
						giveItems(player, SYNTETHIC_COKES);
					}
					else if (i1 < 556)
					{
						giveItems(player, MOLD_LUBRICANT);
					}
					else if (i1 < 725)
					{
						giveItems(player, MITHRIL_ALLOY);
					}
					else if (i1 < 872)
					{
						giveItems(player, DURABLE_METAL_PLATE);
					}
					else if (i1 < 962)
					{
						giveItems(player, ORIHARUKON);
					}
					else if (i1 < 986)
					{
						giveItems(player, MAESTRO_ANVIL_LOCK);
					}
					else
					{
						giveItems(player, MAESTRO_MOLD);
					}
				}
				break;
			}
			case 2:
			{
				if (getRandom(1000) < 847)
				{
					i2 = true;
					int i1 = getRandom(1000);
					if (i1 < 148)
					{
						giveItems(player, BRAIDED_HEMP);
					}
					else if (i1 < 175)
					{
						giveItems(player, LEATHER);
					}
					else if (i1 < 273)
					{
						giveItems(player, COKES);
					}
					else if (i1 < 322)
					{
						giveItems(player, STEEL);
					}
					else if (i1 < 357)
					{
						giveItems(player, HIGH_GRADE_SUEDE);
					}
					else if (i1 < 554)
					{
						giveItems(player, STONE_OF_PURITY);
					}
					else if (i1 < 685)
					{
						giveItems(player, STEEL_MOLD);
					}
					else if (i1 < 803)
					{
						giveItems(player, METAL_HARDENER);
					}
					else if (i1 < 901)
					{
						giveItems(player, MOLD_GLUE);
					}
					else
					{
						giveItems(player, THONS);
					}
				}
				
				if (getRandom(1000) < 251)
				{
					i2 = true;
					int i1 = getRandom(1000);
					if (i1 < 350)
					{
						giveItems(player, VARNISH_OF_PURITY);
					}
					else if (i1 < 587)
					{
						giveItems(player, ENRIA);
					}
					else if (i1 < 798)
					{
						giveItems(player, SILVER_MOLD);
					}
					else if (i1 < 922)
					{
						giveItems(player, MOLD_HARDENER);
					}
					else if (i1 < 966)
					{
						giveItems(player, BLACKSMITHS_FRAMES);
					}
					else if (i1 < 996)
					{
						giveItems(player, ARTISANS_FRAMES);
					}
					else
					{
						giveItems(player, CRAFTSMAN_MOLD);
					}
				}
				break;
			}
			case 3:
			{
				if (getRandom(1000) < 31)
				{
					i2 = true;
					int i1 = getRandom(1000);
					if (i1 < 223)
					{
						giveItems(player, ENCHANT_ARMOR_A_GRADE);
					}
					else if (i1 < 893)
					{
						giveItems(player, ENCHANT_ARMOR_B_GRADE);
					}
					else
					{
						giveItems(player, ENCHANT_ARMOR_S_GRADE);
					}
				}
				
				if (getRandom(1000) < 5)
				{
					i2 = true;
					int i1 = getRandom(1000);
					if (i1 < 202)
					{
						giveItems(player, ENCHANT_WEAPON_A_GRADE);
					}
					else if (i1 < 928)
					{
						giveItems(player, ENCHANT_WEAPON_B_GRADE);
					}
					else
					{
						giveItems(player, ENCHANT_WEAPON_S_GRADE);
					}
				}
				break;
			}
			case 4:
			{
				if (getRandom(1000) < 329)
				{
					i2 = true;
					int i1 = getRandom(1000);
					if (i1 < 88)
					{
						giveItems(player, SEALED_TATEOSSIAN_EARRING_PART);
					}
					else if (i1 < 185)
					{
						giveItems(player, SEALED_TATEOSSIAN_RING_GEM);
					}
					else if (i1 < 238)
					{
						giveItems(player, SEALED_TATEOSSIAN_NECKLACE_CHAIN);
					}
					else if (i1 < 262)
					{
						giveItems(player, SEALED_IMPERIAL_CRUSADER_BREASTPLATE_PART);
					}
					else if (i1 < 292)
					{
						giveItems(player, SEALED_IMPERIAL_CRUSADER_GAITERS_PATTERN);
					}
					else if (i1 < 356)
					{
						giveItems(player, SEALED_IMPERIAL_CRUSADER_GAUNTLETS_DESIGN);
					}
					else if (i1 < 420)
					{
						giveItems(player, SEALED_IMPERIAL_CRUSADER_BOOTS_DESIGN);
					}
					else if (i1 < 482)
					{
						giveItems(player, SEALED_IMPERIAL_CRUSADER_SHIELD_PART);
					}
					else if (i1 < 554)
					{
						giveItems(player, SEALED_IMPERIAL_CRUSADER_HELMET_PATTERN);
					}
					else if (i1 < 576)
					{
						giveItems(player, SEALED_DRACONIC_LEATHER_ARMOR_PART);
					}
					else if (i1 < 640)
					{
						giveItems(player, SEALED_DRACONIC_LEATHER_GLOVES_FABRIC);
					}
					else if (i1 < 704)
					{
						giveItems(player, SEALED_DRACONIC_LEATHER_BOOTS_DESIGN);
					}
					else if (i1 < 777)
					{
						giveItems(player, SEALED_DRACONIC_LEATHER_HELMET_PATTERN);
					}
					else if (i1 < 799)
					{
						giveItems(player, SEALED_MAJOR_ARCANA_ROBE_PART);
					}
					else if (i1 < 863)
					{
						giveItems(player, SEALED_MAJOR_ARCANA_GLOVES_FABRIC);
					}
					else if (i1 < 927)
					{
						giveItems(player, SEALED_MAJOR_ARCANA_BOOTS_DESIGN);
					}
					else
					{
						giveItems(player, SEALED_MAJOR_ARCANA_CIRCLET_PATTERN);
					}
				}
				
				if (getRandom(1000) < 54)
				{
					i2 = true;
					int i1 = getRandom(1000);
					if (i1 < 100)
					{
						giveItems(player, FORGOTTEN_BLADE_EDGE);
					}
					else if (i1 < 198)
					{
						giveItems(player, BASALT_BATTLEHAMMER_HEAD);
					}
					else if (i1 < 298)
					{
						giveItems(player, IMPERIAL_STAFF_HEAD);
					}
					else if (i1 < 398)
					{
						giveItems(player, ANGEL_SLAYER_BLADE);
					}
					else if (i1 < 499)
					{
						giveItems(player, DRACONIC_BOW_SHAFT);
					}
					else if (i1 < 601)
					{
						giveItems(player, DRAGON_HUNTER_AXE_BLADE);
					}
					else if (i1 < 703)
					{
						giveItems(player, SAINT_SPEAR_BLADE);
					}
					else if (i1 < 801)
					{
						giveItems(player, DEMON_SPLINTER_BLADE);
					}
					else if (i1 < 902)
					{
						giveItems(player, HEAVENS_DIVIDER_EDGE);
					}
					else
					{
						giveItems(player, ARCANA_MACE_HEAD);
					}
				}
				break;
			}
		}
		
		takeItems(player, SEALED_BOX, 1);
		return i2;
	}
}

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
package quests.Q00419_GetAPet;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Get A Pet (419)
 * @author ivantotov
 */
public final class Q00419_GetAPet extends Quest
{
	// NPCs
	private static final int GUARD_METTY = 30072;
	private static final int ACCESSORY_MERCHANT_ELICE = 30091;
	private static final int GATEKEEPER_BELLA = 30256;
	private static final int PET_MENAGER_MARTIN = 30731;
	// Items
	private static final int ANIMAL_LOVERS_LIST = 3417;
	private static final int ANIMAL_SLAYERS_1ST_LIST = 3418;
	private static final int ANIMAL_SLAYERS_2ND_LIST = 3419;
	private static final int ANIMAL_SLAYERS_3RD_LIST = 3420;
	private static final int ANIMAL_SLAYERS_4TH_LIST = 3421;
	private static final int ANIMAL_SLAYERS_5TH_LIST = 3422;
	private static final int BLOODY_FANG = 3423;
	private static final int BLOODY_CLAW = 3424;
	private static final int BLOODY_NAIL = 3425;
	private static final int BLOODY_KASHA_FANG = 3426;
	private static final int BLOODY_TARANTULA_NAIL = 3427;
	private static final int ANIMAL_SLAYERS_LIST = 10164;
	private static final int BLOODY_RED_CLAW = 10165;
	// Reward
	private static final int WOLF_COLLAR = 2375;
	// Monster
	private static final int LESSER_DARK_HORROR = 20025;
	private static final int PROWLER = 20034;
	private static final int GIANT_SPIDER = 20103;
	private static final int DARK_HORROR = 20105;
	private static final int TALON_SPIDER = 20106;
	private static final int BLADE_SPIDER = 20108;
	private static final int HOOK_SPIDER = 20308;
	private static final int HUNTER_TARANTULA = 20403;
	private static final int CRIMSON_SPIDER = 20460;
	private static final int PINCER_SPIDER = 20466;
	private static final int KASHA_SPIDER = 20474;
	private static final int KASHA_FANG_SPIDER = 20476;
	private static final int KASHA_BLADE_SPIDER = 20478;
	private static final int PLUNDER_TARANTULA = 20508;
	private static final int CRIMSON_SPIDER2 = 22244;
	// Misc
	private static final int MIN_LEVEL = 15;
	// Links
	private static final Map<Integer, String> LINKS = new HashMap<>();
	
	static
	{
		LINKS.put(1110001, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Can be used for item transportation.</a><br>");
		LINKS.put(1110002, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Can help during hunting by assisting in attacks.</a><br>");
		LINKS.put(1110003, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">Can be sent to the village to buy items.</a><br>");
		LINKS.put(1110004, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Can be traded or sold to a new owner for adena.</a><br>");
		LINKS.put(1110005, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110006, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">When taking down a monster, always have a pet's company.</a><br>");
		LINKS.put(1110007, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Tell your pet to pick up items.</a><br>");
		LINKS.put(1110008, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Tell your pet to attack monsters first.</a><br>");
		LINKS.put(1110009, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Let your pet do what it wants.</a><br>");
		LINKS.put(1110010, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110011, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">10 hours</a><br>");
		LINKS.put(1110012, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">15 hours</a><br>");
		LINKS.put(1110013, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">It won't disappear.</a><br>");
		LINKS.put(1110014, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">25 hours</a><br>");
		LINKS.put(1110015, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110016, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">Dire Wolf</a><br>");
		LINKS.put(1110017, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Air Wolf</a><br>");
		LINKS.put(1110018, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Turek Wolf</a><br>");
		LINKS.put(1110019, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Kasha Wolf</a><br>");
		LINKS.put(1110020, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110021, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">It's tail is always pointing straight down.</a><br>");
		LINKS.put(1110022, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">It's tail is always curled up.</a><br>");
		LINKS.put(1110023, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">It's tail is always wagging back and forth.</a><br>");
		LINKS.put(1110024, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">What are you talking about?! A wolf doesn't have a tail.</a><br>");
		LINKS.put(1110025, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110026, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Raccoon</a><br>");
		LINKS.put(1110027, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Jackal</a><br>");
		LINKS.put(1110028, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Fox</a><br>");
		LINKS.put(1110029, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Shepherd Dog</a><br>");
		LINKS.put(1110030, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">None of the above.</a><br>");
		LINKS.put(1110031, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">1.4 km</a><br>");
		LINKS.put(1110032, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">2.4 km</a><br>");
		LINKS.put(1110033, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">3.4 km</a><br>");
		LINKS.put(1110034, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">4.4 km</a><br>");
		LINKS.put(1110035, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110036, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">Male</a><br>");
		LINKS.put(1110037, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Female</a><br>");
		LINKS.put(1110038, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">A baby that was born last year</a><br>");
		LINKS.put(1110039, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">A baby that was born two years ago</a><br>");
		LINKS.put(1110040, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110041, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Goat</a><br>");
		LINKS.put(1110042, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Meat of a dead animal</a><br>");
		LINKS.put(1110043, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Berries</a><br>");
		LINKS.put(1110044, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Wild Bird</a><br>");
		LINKS.put(1110045, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">None of the above.</a><br>");
		LINKS.put(1110046, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Breeding season is January-February.</a><br>");
		LINKS.put(1110047, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">Pregnancy is nine months.</a><br>");
		LINKS.put(1110048, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Babies are born in April-June.</a><br>");
		LINKS.put(1110049, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Has up to ten offspring at one time.</a><br>");
		LINKS.put(1110050, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110051, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">3-6 years</a><br>");
		LINKS.put(1110052, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">6-9 years</a><br>");
		LINKS.put(1110053, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">9-12 years</a><br>");
		LINKS.put(1110054, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">12-15 years</a><br>");
		LINKS.put(1110055, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110056, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Wolves gather and move in groups of 7-13 animals.</a><br>");
		LINKS.put(1110057, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Wolves can eat a whole calf in one sitting.</a><br>");
		LINKS.put(1110058, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">If they have water, wolves can live for 5-6 days without eating anything.</a><br>");
		LINKS.put(1110059, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">A pregnant wolf makes its home in a wide open place to have its babies.</a><br>");
		LINKS.put(1110060, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110061, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">A grown wolf is still not as heavy as a fully-grown male adult human.</a><br>");
		LINKS.put(1110062, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">A wolf changes into a werewolf during a full-moon.</a><br>");
		LINKS.put(1110063, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">The color of a wolf's fur is the same as the place where it lives.</a><br>");
		LINKS.put(1110064, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">A wolf enjoys eating Dwarves.</a><br>");
		LINKS.put(1110065, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
		LINKS.put(1110066, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Talking Island - Wolf</a><br>");
		LINKS.put(1110067, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Dark Forest - Ashen Wolf</a><br>");
		LINKS.put(1110068, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">Elven Forest - Gray Wolf</a><br>");
		LINKS.put(1110069, "<a action=\"bypass -h Quest Q00419_GetAPet QUESTIONS\">Orc - Black Wolf</a><br>");
		LINKS.put(1110070, "<a action=\"bypass -h Quest Q00419_GetAPet 30731-14.html\">None of the above.</a><br>");
	}
	
	public Q00419_GetAPet()
	{
		super(419);
		addStartNpc(PET_MENAGER_MARTIN);
		addTalkId(PET_MENAGER_MARTIN, GUARD_METTY, ACCESSORY_MERCHANT_ELICE, GATEKEEPER_BELLA);
		addKillId(LESSER_DARK_HORROR, PROWLER, GIANT_SPIDER, DARK_HORROR, TALON_SPIDER, BLADE_SPIDER, HOOK_SPIDER, HUNTER_TARANTULA, CRIMSON_SPIDER, PINCER_SPIDER, KASHA_SPIDER, KASHA_FANG_SPIDER, KASHA_BLADE_SPIDER, PLUNDER_TARANTULA, CRIMSON_SPIDER2);
		registerQuestItems(ANIMAL_LOVERS_LIST, ANIMAL_SLAYERS_1ST_LIST, ANIMAL_SLAYERS_2ND_LIST, ANIMAL_SLAYERS_3RD_LIST, ANIMAL_SLAYERS_4TH_LIST, ANIMAL_SLAYERS_5TH_LIST, BLOODY_FANG, BLOODY_CLAW, BLOODY_NAIL, BLOODY_KASHA_FANG, BLOODY_TARANTULA_NAIL, ANIMAL_SLAYERS_LIST, BLOODY_RED_CLAW);
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
			case "ACCEPT":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					if (player.getRace() == Race.HUMAN)
					{
						giveItems(player, ANIMAL_SLAYERS_1ST_LIST, 1);
						htmltext = "30731-04.htm";
					}
					else if (player.getRace() == Race.ELF)
					{
						giveItems(player, ANIMAL_SLAYERS_2ND_LIST, 1);
						htmltext = "30731-05.htm";
					}
					else if (player.getRace() == Race.DARK_ELF)
					{
						giveItems(player, ANIMAL_SLAYERS_3RD_LIST, 1);
						htmltext = "30731-06.htm";
					}
					else if (player.getRace() == Race.ORC)
					{
						giveItems(player, ANIMAL_SLAYERS_4TH_LIST, 1);
						htmltext = "30731-07.htm";
					}
					else if (player.getRace() == Race.DWARF)
					{
						giveItems(player, ANIMAL_SLAYERS_5TH_LIST, 1);
						htmltext = "30731-08.htm";
					}
					else if (player.getRace() == Race.KAMAEL)
					{
						giveItems(player, ANIMAL_SLAYERS_LIST, 1);
						htmltext = "30731-08a.htm";
					}
				}
				break;
			}
			case "30731-03.htm":
			case "30072-02.html":
			case "30091-02.html":
			case "30256-02.html":
			case "30256-03.html":
			{
				htmltext = event;
				break;
			}
			case "30731-12.html":
			{
				if (player.getRace() == Race.HUMAN)
				{
					if (hasQuestItems(player, ANIMAL_SLAYERS_1ST_LIST) && (getQuestItemsCount(player, BLOODY_FANG) >= 50))
					{
						takeItems(player, ANIMAL_SLAYERS_1ST_LIST, -1);
						takeItems(player, BLOODY_FANG, -1);
						giveItems(player, ANIMAL_LOVERS_LIST, 1);
					}
				}
				else if (player.getRace() == Race.ELF)
				{
					if (hasQuestItems(player, ANIMAL_SLAYERS_2ND_LIST) && (getQuestItemsCount(player, BLOODY_CLAW) >= 50))
					{
						takeItems(player, ANIMAL_SLAYERS_2ND_LIST, -1);
						takeItems(player, BLOODY_CLAW, -1);
						giveItems(player, ANIMAL_LOVERS_LIST, 1);
					}
				}
				else if (player.getRace() == Race.DARK_ELF)
				{
					if (hasQuestItems(player, ANIMAL_SLAYERS_3RD_LIST) && (getQuestItemsCount(player, BLOODY_NAIL) >= 50))
					{
						takeItems(player, ANIMAL_SLAYERS_3RD_LIST, -1);
						takeItems(player, BLOODY_NAIL, -1);
						giveItems(player, ANIMAL_LOVERS_LIST, 1);
					}
				}
				else if (player.getRace() == Race.ORC)
				{
					if (hasQuestItems(player, ANIMAL_SLAYERS_4TH_LIST) && (getQuestItemsCount(player, BLOODY_KASHA_FANG) >= 50))
					{
						takeItems(player, ANIMAL_SLAYERS_4TH_LIST, -1);
						takeItems(player, BLOODY_KASHA_FANG, -1);
						giveItems(player, ANIMAL_LOVERS_LIST, 1);
					}
				}
				else if (player.getRace() == Race.DWARF)
				{
					if (hasQuestItems(player, ANIMAL_SLAYERS_5TH_LIST) && (getQuestItemsCount(player, BLOODY_TARANTULA_NAIL) >= 50))
					{
						takeItems(player, ANIMAL_SLAYERS_5TH_LIST, -1);
						takeItems(player, BLOODY_TARANTULA_NAIL, -1);
						giveItems(player, ANIMAL_LOVERS_LIST, 1);
					}
				}
				else if (player.getRace() == Race.KAMAEL)
				{
					if (hasQuestItems(player, ANIMAL_SLAYERS_LIST) && (getQuestItemsCount(player, BLOODY_RED_CLAW) >= 50))
					{
						takeItems(player, ANIMAL_SLAYERS_LIST, -1);
						takeItems(player, BLOODY_RED_CLAW, -1);
						giveItems(player, ANIMAL_LOVERS_LIST, 1);
					}
				}
				qs.setMemoState(0);
				htmltext = event;
				break;
			}
			
			case "QUESTIONS":
			{
				if (((qs.getMemoState() & 15) == 10) && hasQuestItems(player, ANIMAL_LOVERS_LIST))
				{
					takeItems(player, ANIMAL_LOVERS_LIST, -1);
					giveItems(player, WOLF_COLLAR, 1);
					qs.exitQuest(true, true);
					htmltext = "30731-15.html";
				}
				else
				{
					boolean findResponse = false;
					int linkId = 0;
					while (!findResponse)
					{
						final int randomLinkOffset = getRandom(14) + 4;
						int i7 = 1;
						for (int i = 1; i <= randomLinkOffset; i++)
						{
							i7 *= 2;
						}
						
						if (((i7 & qs.getMemoState()) == 0) && (randomLinkOffset < 18))
						{
							findResponse = true;
							qs.setMemoState((qs.getMemoState() + 1) | i7);
							linkId = 1110000 + (5 * (randomLinkOffset - 4));
							htmltext = "30731-" + (20 + (randomLinkOffset - 4)) + ".htm";
						}
					}
					
					int linkCount = 1;
					int replyOffset1 = 0;
					int replyOffset2 = 0;
					int replyOffset3 = 0;
					int replyOffset4 = 0;
					int i8 = 0;
					while (linkCount < 5)
					{
						final int randomReplyOffset = getRandom(4) + 1;
						int i7 = 1;
						for (int i = 1; i <= randomReplyOffset; i++)
						{
							i7 *= 2;
						}
						
						if (((i7 & i8) == 0) && (randomReplyOffset < 5))
						{
							if (linkCount == 1)
							{
								replyOffset1 = randomReplyOffset;
							}
							else if (linkCount == 2)
							{
								replyOffset2 = randomReplyOffset;
							}
							else if (linkCount == 3)
							{
								replyOffset3 = randomReplyOffset;
							}
							else if (linkCount == 4)
							{
								replyOffset4 = randomReplyOffset;
							}
							linkCount++;
							i8 = i8 | i7;
						}
					}
					
					htmltext = getHtm(player, htmltext);
					htmltext = htmltext.replace("<?reply1?>", LINKS.get(linkId + replyOffset1));
					htmltext = htmltext.replace("<?reply2?>", LINKS.get(linkId + replyOffset2));
					htmltext = htmltext.replace("<?reply3?>", LINKS.get(linkId + replyOffset3));
					htmltext = htmltext.replace("<?reply4?>", LINKS.get(linkId + replyOffset4));
					htmltext = htmltext.replace("<?reply5?>", LINKS.get(linkId + 5));
				}
				break;
			}
			case "30731-14.html":
			{
				qs.setMemoState(0);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case LESSER_DARK_HORROR:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_3RD_LIST) && (getQuestItemsCount(killer, BLOODY_NAIL) < 50))
					{
						if (getRandom(100) < 60)
						{
							giveItems(killer, BLOODY_NAIL, 1);
							if (getQuestItemsCount(killer, BLOODY_NAIL) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case PROWLER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_3RD_LIST) && (getQuestItemsCount(killer, BLOODY_NAIL) < 50))
					{
						if (getRandom(100) < 100)
						{
							giveItems(killer, BLOODY_NAIL, 1);
							if (getQuestItemsCount(killer, BLOODY_NAIL) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case GIANT_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_1ST_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_FANG) < 50) && (getRandom(100) < 60))
						{
							giveItems(killer, BLOODY_FANG, 1);
							if (getQuestItemsCount(killer, BLOODY_FANG) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case DARK_HORROR:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_3RD_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_NAIL) < 50) && (getRandom(100) < 75))
						{
							giveItems(killer, BLOODY_NAIL, 1);
							if (getQuestItemsCount(killer, BLOODY_NAIL) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case TALON_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_1ST_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_FANG) < 50) && (getRandom(100) < 75))
						{
							giveItems(killer, BLOODY_FANG, 1);
							if (getQuestItemsCount(killer, BLOODY_FANG) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case BLADE_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_1ST_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_FANG) < 50) && (getRandom(100) < 100))
						{
							giveItems(killer, BLOODY_FANG, 1);
							if (getQuestItemsCount(killer, BLOODY_FANG) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case HOOK_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_2ND_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_CLAW) < 50) && (getRandom(100) < 75))
						{
							giveItems(killer, BLOODY_CLAW, 1);
							if (getQuestItemsCount(killer, BLOODY_CLAW) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case HUNTER_TARANTULA:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_5TH_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_TARANTULA_NAIL) < 50) && (getRandom(100) < 75))
						{
							giveItems(killer, BLOODY_TARANTULA_NAIL, 1);
							if (getQuestItemsCount(killer, BLOODY_TARANTULA_NAIL) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case CRIMSON_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_2ND_LIST) && (getQuestItemsCount(killer, BLOODY_CLAW) < 50))
					{
						if (getRandom(100) < 60)
						{
							giveItems(killer, BLOODY_CLAW, 1);
							if (getQuestItemsCount(killer, BLOODY_CLAW) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case PINCER_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_2ND_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_CLAW) < 50) && (getRandom(100) < 100))
						{
							giveItems(killer, BLOODY_CLAW, 1);
							if (getQuestItemsCount(killer, BLOODY_CLAW) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case KASHA_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_4TH_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_KASHA_FANG) < 50) && (getRandom(100) < 60))
						{
							giveItems(killer, BLOODY_KASHA_FANG, 1);
							if (getQuestItemsCount(killer, BLOODY_KASHA_FANG) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case KASHA_FANG_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_4TH_LIST) && (getQuestItemsCount(killer, BLOODY_KASHA_FANG) < 50))
					{
						if (getRandom(100) < 75)
						{
							giveItems(killer, BLOODY_KASHA_FANG, 1);
							if (getQuestItemsCount(killer, BLOODY_KASHA_FANG) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case KASHA_BLADE_SPIDER:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_4TH_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_KASHA_FANG) < 50) && (getRandom(100) < 100))
						{
							giveItems(killer, BLOODY_KASHA_FANG, 1);
							if (getQuestItemsCount(killer, BLOODY_KASHA_FANG) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case PLUNDER_TARANTULA:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_5TH_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_TARANTULA_NAIL) < 50) && (getRandom(100) < 100))
						{
							giveItems(killer, BLOODY_TARANTULA_NAIL, 1);
							if (getQuestItemsCount(killer, BLOODY_TARANTULA_NAIL) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case CRIMSON_SPIDER2:
				{
					if (hasQuestItems(killer, ANIMAL_SLAYERS_LIST))
					{
						if ((getQuestItemsCount(killer, BLOODY_RED_CLAW) < 50) && (getRandom(100) < 75))
						{
							giveItems(killer, BLOODY_RED_CLAW, 1);
							if (getQuestItemsCount(killer, BLOODY_RED_CLAW) >= 50)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
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
			if (npc.getId() == PET_MENAGER_MARTIN)
			{
				if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "30731-01.htm";
				}
				else
				{
					htmltext = "30731-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case PET_MENAGER_MARTIN:
				{
					if (hasQuestItems(player, ANIMAL_SLAYERS_LIST))
					{
						if (getQuestItemsCount(player, BLOODY_RED_CLAW) < 50)
						{
							if (!hasQuestItems(player, BLOODY_RED_CLAW))
							{
								htmltext = "30731-09.html";
							}
							else
							{
								htmltext = "30731-10.html";
							}
						}
						else
						{
							htmltext = "30731-11.html";
						}
					}
					else if (hasQuestItems(player, ANIMAL_SLAYERS_1ST_LIST))
					{
						if (getQuestItemsCount(player, BLOODY_FANG) < 50)
						{
							if (!hasQuestItems(player, BLOODY_FANG))
							{
								htmltext = "30731-09.html";
							}
							else
							{
								htmltext = "30731-10.html";
							}
						}
						else
						{
							htmltext = "30731-11.html";
						}
					}
					else if (hasQuestItems(player, ANIMAL_SLAYERS_2ND_LIST))
					{
						if (getQuestItemsCount(player, BLOODY_CLAW) < 50)
						{
							if (!hasQuestItems(player, BLOODY_CLAW))
							{
								htmltext = "30731-09.html";
							}
							else
							{
								htmltext = "30731-10.html";
							}
						}
						else
						{
							htmltext = "30731-11.html";
						}
					}
					else if (hasQuestItems(player, ANIMAL_SLAYERS_3RD_LIST))
					{
						if (getQuestItemsCount(player, BLOODY_NAIL) < 50)
						{
							if (!hasQuestItems(player, BLOODY_NAIL))
							{
								htmltext = "30731-09.html";
							}
							else
							{
								htmltext = "30731-10.html";
							}
						}
						else
						{
							htmltext = "30731-11.html";
						}
					}
					else if (hasQuestItems(player, ANIMAL_SLAYERS_4TH_LIST))
					{
						if (getQuestItemsCount(player, BLOODY_KASHA_FANG) < 50)
						{
							if (!hasQuestItems(player, BLOODY_KASHA_FANG))
							{
								htmltext = "30731-09.html";
							}
							else
							{
								htmltext = "30731-10.html";
							}
						}
						else
						{
							htmltext = "30731-11.html";
						}
					}
					else if (hasQuestItems(player, ANIMAL_SLAYERS_5TH_LIST))
					{
						if (getQuestItemsCount(player, BLOODY_TARANTULA_NAIL) < 50)
						{
							if (!hasQuestItems(player, BLOODY_TARANTULA_NAIL))
							{
								htmltext = "30731-09.html";
							}
							else
							{
								htmltext = "30731-10.html";
							}
						}
						else
						{
							htmltext = "30731-11.html";
						}
					}
					else if (hasQuestItems(player, ANIMAL_LOVERS_LIST))
					{
						if ((qs.getMemoState() != 14) && (qs.getMemoState() != 1879048192))
						{
							htmltext = "30731-16.html";
						}
						else
						{
							qs.setMemoState(1879048192);
							htmltext = "30731-13.html";
						}
						
					}
					break;
				}
				case GUARD_METTY:
				{
					if (hasQuestItems(player, ANIMAL_LOVERS_LIST))
					{
						qs.setMemoState(qs.getMemoState() | 4);
						htmltext = "30072-01.html";
					}
					break;
				}
				case ACCESSORY_MERCHANT_ELICE:
				{
					if (hasQuestItems(player, ANIMAL_LOVERS_LIST))
					{
						qs.setMemoState(qs.getMemoState() | 8);
						htmltext = "30091-01.html";
					}
					break;
				}
				case GATEKEEPER_BELLA:
				{
					if (hasQuestItems(player, ANIMAL_LOVERS_LIST))
					{
						qs.setMemoState(qs.getMemoState() | 2);
						htmltext = "30256-01.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

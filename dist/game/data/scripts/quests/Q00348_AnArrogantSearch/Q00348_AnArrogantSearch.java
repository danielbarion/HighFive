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
package quests.Q00348_AnArrogantSearch;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

/**
 * An Arrogant Search (348)
 * @author Adry_85
 * @since 2.6.0.0
 */
public class Q00348_AnArrogantSearch extends Quest
{
	// NPCs
	private static final int HARNE = 30144;
	private static final int MARTIEN = 30645;
	private static final int SIR_GUSTAV_ATHEBALDT = 30760;
	private static final int HARDIN = 30832;
	private static final int HANELLIN = 30864;
	private static final int IASON_HEINE = 30969;
	private static final int HOLY_ARK_OF_SECRECY_1 = 30977;
	private static final int HOLY_ARK_OF_SECRECY_2 = 30978;
	private static final int HOLY_ARK_OF_SECRECY_3 = 30979;
	private static final int ARK_GUARDIANS_CORPSE = 30980;
	private static final int CLAUDIA_ATHEBALDT = 31001;
	// Items
	private static final int GREATER_HEALING_POTION = 1061;
	private static final int ANTIDOTE = 1831;
	private static final int TITANS_POWERSTONE = 4287;
	private static final int HANELLINS_1ST_LETTER = 4288;
	private static final int HANELLINS_2ND_LETTER = 4289;
	private static final int HANELLINS_3RD_LETTER = 4290;
	private static final int FIRST_KEY_OF_ARK = 4291;
	private static final int SECOND_KEY_OF_ARK = 4292;
	private static final int THIRD_KEY_OF_ARK = 4293;
	private static final int WHITE_FABRIC_1 = 4294;
	private static final int BLOODED_FABRIC = 4295;
	private static final int BOOK_OF_SAINT = 4397;
	private static final int BLOOD_OF_SAINT = 4398;
	private static final int BOUGH_OF_SAINT = 4399;
	private static final int WHITE_FABRIC_2 = 4400;
	private static final int SHELL_OF_MONSTERS = 14857;
	// Misc
	private static final int MIN_LEVEL = 60;
	private static final double MIN_HP_PERCENTAGE = 0.3;
	// Variables
	private static final String I_QUEST0 = "I_QUEST0";
	// Rewards
	private static final int ANIMAL_BONE = 1872;
	private static final int ORIHARUKON_ORE = 1874;
	private static final int COKES = 1879;
	private static final int COARSE_BONE_POWDER = 1881;
	private static final int VARNISH_OF_PURITY = 1887;
	private static final int SYNTHETIC_COKES = 1888;
	private static final int ENRIA = 4042;
	private static final int GREAT_SWORD_BLADE = 4104;
	private static final int HEAVY_WAR_AXE_HEAD = 4105;
	private static final int SPRITES_STAFF_HEAD = 4106;
	private static final int KESHANBERK_BLADE = 4107;
	private static final int SWORD_OF_VALHALLA_BLADE = 4108;
	private static final int KRIS_EDGE = 4109;
	private static final int HELL_KNIFE_EDGE = 4110;
	private static final int ARTHRO_NAIL_BLADE = 4111;
	private static final int DARK_ELVEN_LONGBOW_SHAFT = 4112;
	private static final int GREAT_AXE_HEAD = 4113;
	private static final int SWORD_OF_DAMASCUS_BLADE = 4114;
	private static final int LANCE_BLADE = 4115;
	private static final int ART_OF_BATTLE_AXE_BLADE = 4117;
	private static final int EVIL_SPIRIT_HEAD = 4118;
	private static final int DEMONS_DAGGER_EDGE = 4119;
	private static final int BELLION_CESTUS_EDGE = 4120;
	private static final int BOW_OF_PERIL_SHAFT = 4121;
	// Quest Monsters
	private static final int ARK_GUARDIAN_ELBEROTH = 27182;
	private static final int ARK_GUARDIAN_SHADOWFANG = 27183;
	private static final int ANGEL_KILLER = 27184;
	// Monsters
	private static final int YINTZU = 20647;
	private static final int PALIOTE = 20648;
	private static final int PLATINUM_TRIBE_SHAMAN = 20828;
	private static final int PLATINUM_TRIBE_OVERLORD = 20829;
	private static final int GUARDIAN_ANGEL = 20830;
	private static final int SEAL_ANGEL_1 = 20831;
	private static final int SEAL_ANGEL_2 = 20860;
	
	public Q00348_AnArrogantSearch()
	{
		super(348);
		addAttackId(ARK_GUARDIAN_ELBEROTH, ARK_GUARDIAN_SHADOWFANG, ANGEL_KILLER, PLATINUM_TRIBE_SHAMAN, PLATINUM_TRIBE_OVERLORD);
		addSpawnId(ARK_GUARDIAN_ELBEROTH, ARK_GUARDIAN_SHADOWFANG, ANGEL_KILLER);
		addStartNpc(HANELLIN);
		addTalkId(HANELLIN, IASON_HEINE, HOLY_ARK_OF_SECRECY_1, HOLY_ARK_OF_SECRECY_2, HOLY_ARK_OF_SECRECY_3, ARK_GUARDIANS_CORPSE, CLAUDIA_ATHEBALDT, HARNE, MARTIEN, SIR_GUSTAV_ATHEBALDT, HARDIN);
		addKillId(ARK_GUARDIAN_ELBEROTH, ARK_GUARDIAN_SHADOWFANG, YINTZU, PALIOTE, PLATINUM_TRIBE_SHAMAN, PLATINUM_TRIBE_OVERLORD, GUARDIAN_ANGEL, SEAL_ANGEL_1, SEAL_ANGEL_2);
		registerQuestItems(SHELL_OF_MONSTERS, TITANS_POWERSTONE, HANELLINS_1ST_LETTER, HANELLINS_2ND_LETTER, HANELLINS_3RD_LETTER, FIRST_KEY_OF_ARK, SECOND_KEY_OF_ARK, THIRD_KEY_OF_ARK, WHITE_FABRIC_1, BOOK_OF_SAINT, BLOOD_OF_SAINT, BOUGH_OF_SAINT, WHITE_FABRIC_2);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (npc.getId())
		{
			case ARK_GUARDIAN_ELBEROTH:
			case ARK_GUARDIAN_SHADOWFANG:
			case ANGEL_KILLER:
			{
				if ("DESPAWN".equals(event))
				{
					npc.deleteMe();
					return super.onAdvEvent(event, npc, player);
				}
			}
		}
		
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "30864-02.htm":
			case "30864-03.htm":
			case "30864-10.html":
			case "30864-11.html":
			case "30864-12.html":
			case "30864-25.html":
			case "31001-02.html":
			case "30144-02.html":
			case "30645-02.html":
			{
				htmltext = event;
				break;
			}
			case "30864-04.htm":
			{
				st.setMemoState(1);
				st.startQuest();
				st.setCond(2);
				htmltext = event;
				break;
			}
			case "30864-08.html":
			{
				int memoState = st.getMemoState();
				if (((memoState == 1) && hasAtLeastOneQuestItem(player, TITANS_POWERSTONE, SHELL_OF_MONSTERS)) || (memoState == 2))
				{
					st.setMemoStateEx(0, 4);
					st.setMemoStateEx(1, 0);
					st.setMemoState(4);
					st.setCond(4);
					htmltext = event;
				}
				break;
			}
			case "30864-09.html":
			{
				if (st.isMemoState(4) && (st.getMemoStateEx(1) == 0))
				{
					giveItems(player, HANELLINS_1ST_LETTER, 1);
					giveItems(player, HANELLINS_2ND_LETTER, 1);
					giveItems(player, HANELLINS_3RD_LETTER, 1);
					st.setMemoState(5);
					st.setCond(5);
					htmltext = event;
				}
				break;
			}
			case "30864-26.html":
			{
				if (st.isMemoState(10) && (getQuestItemsCount(player, WHITE_FABRIC_1) == 1))
				{
					st.setMemoState(11);
					htmltext = event;
				}
				break;
			}
			case "30864-27.html":
			{
				if (st.isMemoState(11) && (getQuestItemsCount(player, WHITE_FABRIC_1) == 1) && (st.getMemoStateEx(1) > 0))
				{
					switch (st.getMemoStateEx(1))
					{
						case 1:
						{
							giveAdena(player, 43000, true);
							break;
						}
						case 2:
						{
							giveAdena(player, 4000, true);
							break;
						}
						case 3:
						{
							giveAdena(player, 13000, true);
							break;
						}
					}
					
					st.setMemoStateEx(0, 12);
					st.setMemoStateEx(1, 100);
					st.setCond(24);
					htmltext = event;
				}
				else
				{
					htmltext = "30864-28.html";
				}
				break;
			}
			case "30864-29.html":
			{
				if (st.isMemoState(11) && (st.getMemoStateEx(1) == 0) && (getQuestItemsCount(player, WHITE_FABRIC_1) == 1))
				{
					giveAdena(player, 49000, true);
					st.setMemoState(12); // Custom line
					st.setMemoStateEx(0, 12);
					st.setMemoStateEx(1, 20000);
					st.setCond(24);
					htmltext = event;
				}
				break;
			}
			case "30864-30.html":
			{
				if (st.isMemoState(11) && (st.getMemoStateEx(1) == 0) && (getQuestItemsCount(player, WHITE_FABRIC_1) == 1))
				{
					st.setMemoState(13); // Custom line
					st.setMemoStateEx(0, 13);
					st.setMemoStateEx(1, 20000);
					st.setCond(25);
					htmltext = event;
				}
				break;
			}
			case "30864-43.html":
			{
				if (st.isMemoState(15))
				{
					st.setMemoState(16);
					htmltext = event;
				}
				break;
			}
			case "30864-44.html":
			{
				if (st.isMemoState(15) || st.isMemoState(16))
				{
					if (hasQuestItems(player, BLOODED_FABRIC))
					{
						giveItems(player, WHITE_FABRIC_1, 9);
					}
					else
					{
						giveItems(player, WHITE_FABRIC_1, 10);
					}
				}
				
				st.setMemoState(17); // Custom line
				st.setMemoStateEx(0, 17);
				st.setMemoStateEx(1, 0);
				st.setCond(26);
				htmltext = event;
				break;
			}
			case "30864-47.html":
			{
				if (st.isMemoState(17) && (getQuestItemsCount(player, BLOODED_FABRIC) >= 10) && !hasQuestItems(player, WHITE_FABRIC_1))
				{
					st.setMemoState(18); // Custom line
					st.setMemoStateEx(0, 18);
					st.setMemoStateEx(1, 0);
					st.setCond(27);
					htmltext = event;
				}
				break;
			}
			case "30864-50.html":
			{
				if (st.isMemoState(19))
				{
					giveItems(player, WHITE_FABRIC_1, 10);
					st.setMemoState(17); // Custom line
					st.setMemoStateEx(0, 17);
					st.setMemoStateEx(1, 0);
					st.setCond(29);
					htmltext = event;
				}
				break;
			}
			case "30864-51.html":
			{
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		switch (npc.getId())
		{
			case ARK_GUARDIAN_ELBEROTH:
			{
				if (!npc.getVariables().getBoolean(I_QUEST0, false))
				{
					npc.getVariables().set(I_QUEST0, true);
					npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.SORRY_ABOUT_THIS_BUT_I_MUST_KILL_YOU_NOW));
				}
				break;
			}
			case ARK_GUARDIAN_SHADOWFANG:
			{
				if (!npc.getVariables().getBoolean(I_QUEST0, false))
				{
					npc.getVariables().set(I_QUEST0, true);
					npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.I_SHALL_DRENCH_THIS_MOUNTAIN_WITH_YOUR_BLOOD));
				}
				break;
			}
			case ANGEL_KILLER:
			{
				final QuestState st = getQuestState(attacker, false);
				if ((st.getMemoStateEx(0) < 8) && !hasQuestItems(attacker, FIRST_KEY_OF_ARK) && !hasQuestItems(attacker, BLOOD_OF_SAINT))
				{
					if ((((st.getMemoStateEx(1) % 100) / 10) == 1))
					{
						if (npc.getCurrentHp() < (npc.getMaxHp() * MIN_HP_PERCENTAGE))
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 10);
							if ((st.getMemoStateEx(1) % 10) == 0)
							{
								clearRadar(attacker);
								addRadar(attacker, -2908, 44128, -2712);
							}
							else
							{
								st.setCond(19, true);
							}
							
							npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.HA_THAT_WAS_FUN_IF_YOU_WISH_TO_FIND_THE_KEY_SEARCH_THE_CORPSE));
							npc.deleteMe();
						}
					}
					else if ((((st.getMemoStateEx(1) % 100) / 10) == 2))
					{
						npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.WE_DON_T_HAVE_ANY_FURTHER_BUSINESS_TO_DISCUSS_HAVE_YOU_SEARCHED_THE_CORPSE_FOR_THE_KEY));
						npc.deleteMe();
					}
				}
				else if (hasAtLeastOneQuestItem(attacker, FIRST_KEY_OF_ARK, BLOOD_OF_SAINT))
				{
					npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.WE_DON_T_HAVE_ANY_FURTHER_BUSINESS_TO_DISCUSS_HAVE_YOU_SEARCHED_THE_CORPSE_FOR_THE_KEY));
					npc.deleteMe();
				}
				break;
			}
			case PLATINUM_TRIBE_SHAMAN:
			{
				final QuestState st = getRandomPartyMemberState(attacker, -1, 3, npc);
				if ((st != null) && npc.isInsideRadius3D(attacker, Config.ALT_PARTY_RANGE))
				{
					if (((st.getMemoStateEx(0) == 12) || (st.getMemoStateEx(0) == 13)) && hasQuestItems(st.getPlayer(), WHITE_FABRIC_1))
					{
						if (st.getMemoStateEx(0) == 12)
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 60);
							if ((st.getMemoStateEx(1) + 60) > 80000)
							{
								giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
								takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
								st.exitQuest(true, true);
							}
						}
						
						if (st.getMemoStateEx(0) == 13)
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 60);
							if ((st.getMemoStateEx(1) + 60) > 100000)
							{
								giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
								takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
								st.setMemoState(14); // Custom line
								st.setMemoStateEx(0, 14);
								playSound(st.getPlayer(), QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
						}
					}
				}
				break;
			}
			case PLATINUM_TRIBE_OVERLORD:
			{
				final QuestState st = getRandomPartyMemberState(attacker, -1, 3, npc);
				if ((st != null) && npc.isInsideRadius3D(attacker, Config.ALT_PARTY_RANGE))
				{
					if (((st.getMemoStateEx(0) == 12) || (st.getMemoStateEx(0) == 13)) && hasQuestItems(st.getPlayer(), WHITE_FABRIC_1))
					{
						if (st.getMemoStateEx(0) == 12)
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 70);
							if ((st.getMemoStateEx(1) + 70) > 80000)
							{
								giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
								takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
								st.exitQuest(true, true);
							}
						}
						
						if (st.getMemoStateEx(0) == 13)
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 70);
							if ((st.getMemoStateEx(1) + 70) > 100000)
							{
								giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
								takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
								st.setMemoState(14); // Custom line
								st.setMemoStateEx(0, 14);
								playSound(st.getPlayer(), QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
						}
					}
				}
				break;
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getRandomPartyMemberState(player, -1, 3, npc);
		if ((st != null) && npc.isInsideRadius3D(player, Config.ALT_PARTY_RANGE))
		{
			switch (npc.getId())
			{
				case ARK_GUARDIAN_ELBEROTH:
				{
					if (npc.isInsideRadius3D(player, Config.ALT_PARTY_RANGE))
					{
						if ((st.getMemoStateEx(0) < 8) && (((st.getMemoStateEx(1) % 1000) / 100) == 1) && !hasQuestItems(st.getPlayer(), SECOND_KEY_OF_ARK) && !hasQuestItems(st.getPlayer(), BOOK_OF_SAINT))
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 100);
							if ((st.getMemoStateEx(1) % 10) != 0)
							{
								st.setCond(11);
							}
							
							giveItems(st.getPlayer(), SECOND_KEY_OF_ARK, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.YOU_FOOLS_WILL_GET_WHAT_S_COMING_TO_YOU));
						}
					}
					break;
				}
				case ARK_GUARDIAN_SHADOWFANG:
				{
					if (npc.isInsideRadius3D(player, Config.ALT_PARTY_RANGE))
					{
						if ((st.getMemoStateEx(0) < 8) && (((st.getMemoStateEx(1) % 10000) / 1000) == 1) && !hasQuestItems(st.getPlayer(), THIRD_KEY_OF_ARK) && !hasQuestItems(st.getPlayer(), BOUGH_OF_SAINT))
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 1000);
							if ((st.getMemoStateEx(1) % 10) != 0)
							{
								st.setCond(15);
							}
							
							giveItems(st.getPlayer(), THIRD_KEY_OF_ARK, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.YOU_GUYS_WOULDN_T_KNOW_THE_SEVEN_SEALS_ARE_ARRRGH));
						}
					}
					break;
				}
				case YINTZU:
				case PALIOTE:
				{
					if (npc.isInsideRadius3D(player, Config.ALT_PARTY_RANGE) && st.isMemoState(1) && !hasQuestItems(st.getPlayer(), SHELL_OF_MONSTERS))
					{
						giveItems(st.getPlayer(), SHELL_OF_MONSTERS, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
					break;
				}
				case PLATINUM_TRIBE_SHAMAN:
				{
					if (((st.getMemoStateEx(0) == 12) || (st.getMemoStateEx(0) == 13)) && hasQuestItems(st.getPlayer(), WHITE_FABRIC_1))
					{
						if (st.getMemoStateEx(0) == 12)
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 600);
							if ((st.getMemoStateEx(1) + 600) > 80000)
							{
								giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
								takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
								st.exitQuest(true, true);
							}
						}
						
						if (st.getMemoStateEx(0) == 13)
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 600);
							if ((st.getMemoStateEx(1) + 600) > 100000)
							{
								giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
								takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
								st.setMemoState(14); // Custom line
								st.setMemoStateEx(0, 14);
								playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
						}
					}
					break;
				}
				case PLATINUM_TRIBE_OVERLORD:
				{
					if (((st.getMemoStateEx(0) == 12) || (st.getMemoStateEx(0) == 13)) && hasQuestItems(st.getPlayer(), WHITE_FABRIC_1))
					{
						if (st.getMemoStateEx(0) == 12)
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 700);
							if ((st.getMemoStateEx(1) + 700) > 80000)
							{
								giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
								takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
								st.exitQuest(true, true);
							}
						}
						
						if (st.getMemoStateEx(0) == 13)
						{
							st.setMemoStateEx(1, st.getMemoStateEx(1) + 700);
							if ((st.getMemoStateEx(1) + 700) > 100000)
							{
								giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
								takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
								st.setMemoState(14); // Custom line
								st.setMemoStateEx(0, 14);
								playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
						}
					}
					break;
				}
				case GUARDIAN_ANGEL:
				case SEAL_ANGEL_1:
				case SEAL_ANGEL_2:
				{
					if ((st.getMemoStateEx(0) == 17) && hasQuestItems(st.getPlayer(), WHITE_FABRIC_1))
					{
						int i0 = st.getMemoStateEx(1) + getRandom(100) + 100;
						st.setMemoStateEx(1, i0);
						if ((st.getMemoStateEx(1) + i0) > 750)
						{
							giveItems(st.getPlayer(), BLOODED_FABRIC, 1);
							takeItems(st.getPlayer(), WHITE_FABRIC_1, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							st.setMemoStateEx(1, 0);
						}
					}
					break;
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		switch (npc.getId())
		{
			case ARK_GUARDIAN_ELBEROTH:
			{
				npc.getVariables().set(I_QUEST0, false);
				npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.THAT_DOESN_T_BELONG_TO_YOU_DON_T_TOUCH_IT));
				startQuestTimer("DESPAWN", 600000, npc, null);
				break;
			}
			case ARK_GUARDIAN_SHADOWFANG:
			{
				npc.getVariables().set(I_QUEST0, false);
				npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.GET_OUT_OF_MY_SIGHT_YOU_INFIDELS));
				startQuestTimer("DESPAWN", 600000, npc, null);
				break;
			}
			case ANGEL_KILLER:
			{
				npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.I_HAVE_THE_KEY_WHY_DON_T_YOU_COME_AND_TAKE_IT));
				startQuestTimer("DESPAWN", 600000, npc, null);
			}
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (st.isCreated())
		{
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "30864-01.htm" : "30864-05.html";
		}
		else if (st.isStarted())
		{
			switch (npc.getId())
			{
				case HANELLIN:
				{
					switch (st.getMemoState())
					{
						case 1:
						case 2:
						{
							int memoState = st.getMemoState();
							if ((memoState == 1) && !hasQuestItems(player, TITANS_POWERSTONE) && !hasQuestItems(player, SHELL_OF_MONSTERS))
							{
								htmltext = "30864-06.html";
							}
							else if (((memoState == 1) && hasQuestItems(player, TITANS_POWERSTONE)) || hasQuestItems(player, SHELL_OF_MONSTERS) || (memoState == 2))
							{
								if (hasQuestItems(player, SHELL_OF_MONSTERS))
								{
									takeItems(player, SHELL_OF_MONSTERS, 1);
								}
								
								if (hasQuestItems(player, TITANS_POWERSTONE))
								{
									takeItems(player, TITANS_POWERSTONE, 1);
								}
								
								st.setMemoState(2);
								htmltext = "30864-07.html";
							}
							break;
						}
						case 4:
						{
							switch (st.getMemoStateEx(1))
							{
								case 0:
								{
									st.setMemoState(5);
									htmltext = "30864-09.html";
									giveItems(player, HANELLINS_1ST_LETTER, 1);
									giveItems(player, HANELLINS_2ND_LETTER, 1);
									giveItems(player, HANELLINS_3RD_LETTER, 1);
									st.setCond(5, true);
									break;
								}
								case 1:
								{
									st.setMemoState(5);
									htmltext = "30864-13.html";
									giveItems(player, HANELLINS_1ST_LETTER, 1);
									st.setCond(6, true);
									break;
								}
								case 2:
								{
									st.setMemoState(5);
									htmltext = "30864-14.html";
									giveItems(player, HANELLINS_2ND_LETTER, 1);
									st.setCond(7, true);
									break;
								}
								case 3:
								{
									st.setMemoState(5);
									htmltext = "30864-15.html";
									giveItems(player, HANELLINS_3RD_LETTER, 1);
									st.setCond(8, true);
									break;
								}
							}
							break;
						}
						case 5:
						{
							if ((st.getMemoStateEx(1) % 10) == 0)
							{
								htmltext = "30864-16.html";
							}
							else
							{
								switch (st.getMemoStateEx(1))
								{
									case 1:
									{
										htmltext = "30864-17.html";
										break;
									}
									case 2:
									{
										htmltext = "30864-18.html";
										break;
									}
									case 3:
									{
										htmltext = "30864-19.html";
										break;
									}
								}
							}
							
							// Custom part
							if (hasQuestItems(player, BOOK_OF_SAINT, BLOOD_OF_SAINT, BOUGH_OF_SAINT))
							{
								takeItems(player, 1, BOOK_OF_SAINT, BLOOD_OF_SAINT, BOUGH_OF_SAINT);
								st.setMemoState(9);
								htmltext = "30864-21.html";
								st.setCond(22, true);
							}
							break;
						}
						case 6:
						case 7:
						{
							htmltext = "30864-20.html";
							break;
						}
						case 8:
						{
							if (hasQuestItems(player, BOOK_OF_SAINT, BLOOD_OF_SAINT, BOUGH_OF_SAINT))
							{
								takeItems(player, 1, BOOK_OF_SAINT, BLOOD_OF_SAINT, BOUGH_OF_SAINT);
								st.setMemoState(9);
								htmltext = "30864-21.html";
								st.setCond(22, true);
							}
							else
							{
								switch (st.getMemoStateEx(1))
								{
									case 0:
									{
										htmltext = "30864-22.html";
										break;
									}
									case 1:
									{
										if (hasQuestItems(player, BLOOD_OF_SAINT) && !hasAtLeastOneQuestItem(player, BOOK_OF_SAINT, BOUGH_OF_SAINT))
										{
											htmltext = "30864-33.html";
										}
										else if (!hasQuestItems(player, BLOOD_OF_SAINT, WHITE_FABRIC_2))
										{
											htmltext = "30864-36.html";
										}
										break;
									}
									case 2:
									{
										if (hasQuestItems(player, BOOK_OF_SAINT) && !hasAtLeastOneQuestItem(player, BLOOD_OF_SAINT, BOUGH_OF_SAINT))
										{
											htmltext = "30864-34.html";
										}
										else if (!hasQuestItems(player, BOOK_OF_SAINT, WHITE_FABRIC_2))
										{
											htmltext = "30864-37.html";
										}
										break;
									}
									case 3:
									{
										if (hasQuestItems(player, BOUGH_OF_SAINT) && !hasAtLeastOneQuestItem(player, BLOOD_OF_SAINT, BOOK_OF_SAINT))
										{
											htmltext = "30864-35.html";
										}
										else if (!hasQuestItems(player, BOUGH_OF_SAINT, WHITE_FABRIC_2))
										{
											htmltext = "30864-38.html";
										}
										break;
									}
								}
							}
							
							if ((getQuestItemsCount(player, WHITE_FABRIC_2) > 1) && (st.getMemoStateEx(1) > 0))
							{
								htmltext = "30864-40.html";
							}
							
							if ((getQuestItemsCount(player, WHITE_FABRIC_2) == 1) && (st.getMemoStateEx(1) > 0) && !hasQuestItems(player, BOOK_OF_SAINT, BLOOD_OF_SAINT, BOUGH_OF_SAINT))
							{
								giveItems(player, WHITE_FABRIC_1, 1);
								takeItems(player, WHITE_FABRIC_2, 1);
								st.setMemoState(10);
								htmltext = "30864-41.html";
							}
							break;
						}
						case 9:
						{
							final long antidoteCount = getQuestItemsCount(player, ANTIDOTE);
							if ((antidoteCount < 5) || !hasQuestItems(player, GREATER_HEALING_POTION))
							{
								htmltext = "30864-23.html";
							}
							else if ((antidoteCount >= 5) && hasQuestItems(player, GREATER_HEALING_POTION))
							{
								if (st.getMemoStateEx(1) == 0)
								{
									htmltext = "30864-24.html";
									giveItems(player, WHITE_FABRIC_1, 1);
									st.setMemoState(10);
									takeItems(player, ANTIDOTE, 5);
									takeItems(player, GREATER_HEALING_POTION, 1);
								}
								else
								{
									giveItems(player, WHITE_FABRIC_2, 3);
									takeItems(player, ANTIDOTE, 5);
									takeItems(player, GREATER_HEALING_POTION, 1);
									st.setMemoState(10);
									st.setCond(23, true);
									htmltext = "30864-39.html";
								}
							}
							break;
						}
						case 10:
						{
							if (getQuestItemsCount(player, WHITE_FABRIC_1) == 1)
							{
								htmltext = "30864-25.html";
							}
							
							if ((getQuestItemsCount(player, WHITE_FABRIC_2) > 1) && (st.getMemoStateEx(1) > 0))
							{
								htmltext = "30864-40.html";
							}
							
							if ((getQuestItemsCount(player, WHITE_FABRIC_2) == 1) && (st.getMemoStateEx(1) > 0) && !hasQuestItems(player, BOOK_OF_SAINT, BLOOD_OF_SAINT, BOUGH_OF_SAINT))
							{
								giveItems(player, WHITE_FABRIC_1, 1);
								takeItems(player, WHITE_FABRIC_2, 1);
								st.setMemoState(10);
								htmltext = "30864-41.html";
							}
							break;
						}
						case 11:
						{
							if (getQuestItemsCount(player, WHITE_FABRIC_1) == 1)
							{
								if ((st.getMemoStateEx(1) > 0))
								{
									switch (st.getMemoStateEx(1))
									{
										case 1:
										{
											giveAdena(player, 43000, true);
											break;
										}
										case 2:
										{
											giveAdena(player, 4000, true);
											break;
										}
										case 3:
										{
											giveAdena(player, 13000, true);
											break;
										}
									}
									
									st.setMemoStateEx(0, 12);
									st.setMemoStateEx(1, 100);
									st.setCond(24, true);
									htmltext = "30864-27.html";
								}
								else if ((st.getMemoStateEx(1) == 0))
								{
									htmltext = "30864-28.html";
								}
							}
							break;
						}
						case 12:
						{
							if (getQuestItemsCount(player, WHITE_FABRIC_1) == 1)
							{
								htmltext = "30864-31.html";
							}
							break;
						}
						case 13:
						{
							if (getQuestItemsCount(player, WHITE_FABRIC_1) == 1)
							{
								htmltext = "30864-32.html";
							}
							break;
						}
						case 14:
						{
							getReward(player);
							st.setMemoState(15);
							htmltext = "30864-42.html";
							break;
						}
						case 15:
						{
							htmltext = "30864-43.html";
							break;
						}
						case 16:
						{
							if (hasQuestItems(player, BLOODED_FABRIC))
							{
								giveItems(player, WHITE_FABRIC_1, 9);
							}
							else
							{
								giveItems(player, WHITE_FABRIC_1, 10);
							}
							
							st.setMemoState(17); // Custom line
							st.setMemoStateEx(0, 17);
							st.setMemoStateEx(1, 0);
							st.setCond(26, true);
							htmltext = "30864-44.html";
							break;
						}
						case 17:
						{
							if (hasQuestItems(player, WHITE_FABRIC_1))
							{
								htmltext = "30864-45.html";
							}
							else
							{
								final long bloodedFabricCount = getQuestItemsCount(player, BLOODED_FABRIC);
								if (bloodedFabricCount >= 10)
								{
									htmltext = "30864-46.html";
								}
								else
								{
									giveAdena(player, (bloodedFabricCount * 1000) + 4000, true);
									takeItems(player, BLOODED_FABRIC, -1);
									st.exitQuest(true, true);
									htmltext = "30864-48.html";
								}
							}
							break;
						}
						case 18:
						{
							final int memoStateEx = st.getMemoStateEx(1);
							if ((memoStateEx % 10) < 7)
							{
								int i1 = 0;
								int i2 = 0;
								int i0 = memoStateEx % 10;
								if (i0 >= 4)
								{
									i1 += 6;
									i0 -= 4;
									i2 += 1;
								}
								
								if (i0 >= 2)
								{
									i0 -= 2;
									i1 += 1;
									i2 += 1;
								}
								
								if (i0 >= 1)
								{
									i1 += 3;
									i2 += 1;
									i0 -= 1;
								}
								
								if (i0 == 0)
								{
									final long bloodedFabricCount = getQuestItemsCount(player, BLOODED_FABRIC);
									if ((bloodedFabricCount + i1) >= 10)
									{
										htmltext = "30864-52.html";
									}
									else
									{
										htmltext = "30864-53.html";
										if (i2 == 2)
										{
											giveAdena(player, 24000, true);
										}
										else if (i2 == 1)
										{
											giveAdena(player, 12000, true);
										}
										
										st.exitQuest(true, true);
									}
								}
							}
							else if ((memoStateEx % 10) == 7)
							{
								htmltext = "30864-54.html";
								st.setCond(28, true);
								getReward(player);
								st.setMemoState(19);
							}
							break;
						}
						case 19:
						{
							htmltext = "30864-49.html";
							break;
						}
					}
					break;
				}
				case IASON_HEINE:
				{
					if (st.getMemoStateEx(0) == 18)
					{
						if ((st.getMemoStateEx(1) % 8) < 4)
						{
							if (getQuestItemsCount(player, BLOODED_FABRIC) >= 6)
							{
								takeItems(player, BLOODED_FABRIC, 6);
								st.setMemoStateEx(1, st.getMemoStateEx(1) + 4);
								htmltext = "30969-01.html";
							}
							else
							{
								htmltext = "30969-02.html";
							}
						}
						else
						{
							htmltext = "30969-03.html";
						}
					}
					break;
				}
				case HOLY_ARK_OF_SECRECY_1:
				{
					if (hasQuestItems(player, FIRST_KEY_OF_ARK))
					{
						giveItems(player, BLOOD_OF_SAINT, 1);
						clearRadar(player);
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							if (hasQuestItems(player, BOOK_OF_SAINT, BOUGH_OF_SAINT))
							{
								st.setCond(21, true);
							}
						}
						else
						{
							st.setCond(20, true);
						}
						
						takeItems(player, FIRST_KEY_OF_ARK, 1);
						st.setMemoStateEx(1, st.getMemoStateEx(1) - 20);
						if ((((st.getMemoStateEx(1) - 20) % 100) / 10) == 0)
						{
							st.setMemoStateEx(0, st.getMemoStateEx(0) + 1);
						}
						
						if (((st.getMemoStateEx(1) - 20) % 10) == 1)
						{
							st.setMemoStateEx(0, 8);
						}
						
						htmltext = "30977-01.html";
					}
					else
					{
						if ((st.getMemoState() <= 8) && (((st.getMemoStateEx(1) % 100) / 10) == 0) && hasQuestItems(player, BLOOD_OF_SAINT))
						{
							htmltext = "30977-02.html";
						}
						else if ((st.getMemoState() < 8) && (((st.getMemoStateEx(1) % 100) / 10) == 1) && !hasQuestItems(player, BLOOD_OF_SAINT))
						{
							htmltext = "30977-03.html";
						}
					}
					break;
				}
				case HOLY_ARK_OF_SECRECY_2:
				{
					if (hasQuestItems(player, SECOND_KEY_OF_ARK))
					{
						giveItems(player, BOOK_OF_SAINT, 1);
						takeItems(player, SECOND_KEY_OF_ARK, 1);
						clearRadar(player);
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							if (hasQuestItems(player, BLOOD_OF_SAINT, BOUGH_OF_SAINT))
							{
								st.setCond(21, true);
							}
						}
						else
						{
							st.setCond(12, true);
						}
						
						st.setMemoStateEx(1, st.getMemoStateEx(1) - 200);
						if ((((st.getMemoStateEx(1) - 200) % 1000) / 100) == 0)
						{
							st.setMemoStateEx(0, st.getMemoStateEx(0) + 1);
						}
						
						if (((st.getMemoStateEx(1) - 200) % 10) == 2)
						{
							st.setMemoStateEx(0, 8);
						}
						
						htmltext = "30978-01.html";
					}
					else
					{
						if ((st.getMemoState() < 8) && (((st.getMemoStateEx(1) % 1000) / 100) == 1))
						{
							htmltext = "30978-02.html";
							if ((st.getMemoStateEx(1) % 10) != 0)
							{
								st.setCond(10, true);
							}
							
							// TODO (Adry_85): Missing Question Mark
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							addSpawn(ARK_GUARDIAN_ELBEROTH, player.getX(), player.getY(), player.getZ(), 0, false, 0, false); // ark_guardian_elberoth
						}
						else if ((st.getMemoState() <= 8) && (((st.getMemoStateEx(1) % 1000) / 100) == 0) && hasQuestItems(player, BOOK_OF_SAINT))
						{
							htmltext = "30978-03.html";
						}
					}
					break;
				}
				case HOLY_ARK_OF_SECRECY_3:
				{
					if (hasQuestItems(player, THIRD_KEY_OF_ARK))
					{
						giveItems(player, BOUGH_OF_SAINT, 1);
						takeItems(player, THIRD_KEY_OF_ARK, 1);
						clearRadar(player);
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							if (hasQuestItems(player, BLOOD_OF_SAINT, BOOK_OF_SAINT))
							{
								st.setCond(21, true);
							}
						}
						else
						{
							st.setCond(16, true);
						}
						
						st.setMemoStateEx(1, st.getMemoStateEx(1) - 2000);
						if ((((st.getMemoStateEx(1) - 2000) % 10000) / 1000) == 0)
						{
							st.setMemoStateEx(0, st.getMemoStateEx(0) + 1);
						}
						
						if (((st.getMemoStateEx(1) - 2000) % 10) == 3)
						{
							st.setMemoStateEx(0, 8);
						}
						
						htmltext = "30979-01.html";
					}
					else
					{
						if ((st.getMemoState() < 8) && (((st.getMemoStateEx(1) % 10000) / 1000) == 1))
						{
							htmltext = "30979-02.html";
							if ((st.getMemoStateEx(1) % 10) != 0)
							{
								st.setCond(14, true);
							}
							
							// TODO (Adry_85): Missing Question Mark
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							addSpawn(ARK_GUARDIAN_SHADOWFANG, player.getX(), player.getY(), player.getZ(), 0, false, 0, false); // ark_guardian_shadowfang
						}
						else if ((st.getMemoState() <= 8) && (((st.getMemoStateEx(1) % 10000) / 1000) == 0) && hasQuestItems(player, BOUGH_OF_SAINT))
						{
							htmltext = "30979-03.html";
						}
					}
					break;
				}
				case ARK_GUARDIANS_CORPSE:
				{
					if ((st.getMemoState() < 8) && (((st.getMemoStateEx(1) % 100) / 10) == 1) && !hasQuestItems(player, FIRST_KEY_OF_ARK) && !hasQuestItems(player, BLOOD_OF_SAINT))
					{
						htmltext = "30980-02.html";
						clearRadar(player);
						if ((st.getMemoStateEx(1) % 10) != 0)
						{
							st.setCond(18, true);
						}
						
						// TODO (Adry_85): Missing Question Mark
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						addSpawn(ANGEL_KILLER, player.getX(), player.getY(), player.getZ(), 0, false, 0, false); // angel_killer
					}
					else if ((st.getMemoState() < 8) && (((st.getMemoStateEx(1) % 100) / 10) == 2) && !hasQuestItems(player, FIRST_KEY_OF_ARK) && !hasQuestItems(player, BLOOD_OF_SAINT))
					{
						giveItems(player, FIRST_KEY_OF_ARK, 1);
						addRadar(player, -418, 44174, -3568);
						htmltext = "30980-03.html";
					}
					else if (hasAtLeastOneQuestItem(player, FIRST_KEY_OF_ARK, BLOOD_OF_SAINT))
					{
						htmltext = "30980-01.html";
					}
					break;
				}
				case CLAUDIA_ATHEBALDT:
				{
					if (hasQuestItems(player, HANELLINS_2ND_LETTER))
					{
						int i0 = st.getMemoStateEx(1) + 100;
						if ((i0 % 10) == 0)
						{
							addRadar(player, 181472, 7158, -2725);
						}
						else
						{
							st.setCond(9, true);
						}
						
						st.setMemoStateEx(1, i0);
						takeItems(player, HANELLINS_2ND_LETTER, 1);
						htmltext = "31001-01.html";
					}
					else if ((st.getMemoState() < 8) && (((st.getMemoStateEx(1) % 1000) / 100) == 1) && !hasQuestItems(player, SECOND_KEY_OF_ARK))
					{
						// retail typo
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							addRadar(player, 181472, 7158, -2725);
						}
						
						htmltext = "31001-03.html";
					}
					else if (hasQuestItems(player, SECOND_KEY_OF_ARK))
					{
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							addRadar(player, 181472, 7158, -2725);
						}
						
						htmltext = "31001-04.html";
					}
					else if (hasQuestItems(player, BOOK_OF_SAINT))
					{
						htmltext = "31001-05.html";
					}
					break;
				}
				case HARNE:
				{
					if (hasQuestItems(player, HANELLINS_1ST_LETTER))
					{
						int i0 = st.getMemoStateEx(1) + 10;
						if ((i0 % 10) == 0)
						{
							addRadar(player, 2908, 44128, -2712);
						}
						else
						{
							st.setCond(17, true);
						}
						
						st.setMemoStateEx(1, i0);
						takeItems(player, HANELLINS_1ST_LETTER, 1);
						htmltext = "30144-01.html";
					}
					else if ((st.getMemoState() < 8) && (((st.getMemoStateEx(1) % 100) / 10) == 1) && !hasQuestItems(player, FIRST_KEY_OF_ARK))
					{
						// retail typo
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							addRadar(player, 2908, 44128, -2712);
						}
						
						htmltext = "30144-03.html";
					}
					else if (hasQuestItems(player, FIRST_KEY_OF_ARK))
					{
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							addRadar(player, 2908, 44128, -2712);
						}
						
						htmltext = "30144-04.html";
					}
					else if (hasQuestItems(player, BLOOD_OF_SAINT))
					{
						htmltext = "30144-05.html";
					}
					break;
				}
				case MARTIEN:
				{
					if (hasQuestItems(player, HANELLINS_3RD_LETTER))
					{
						int i0 = st.getMemoStateEx(1) + 1000;
						if ((i0 % 10) == 0)
						{
							addRadar(player, 50693, 158674, 376);
						}
						else
						{
							st.setCond(13, true);
						}
						
						st.setMemoStateEx(1, i0);
						takeItems(player, HANELLINS_3RD_LETTER, 1);
						htmltext = "30645-01.html";
					}
					else if ((st.getMemoState() < 8) && (((st.getMemoStateEx(1) % 10000) / 1000) == 1) && !hasQuestItems(player, THIRD_KEY_OF_ARK))
					{
						// retail typo
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							addRadar(player, 50693, 158674, 376);
						}
						
						htmltext = "30645-03.html";
					}
					else if (hasQuestItems(player, THIRD_KEY_OF_ARK))
					{
						if ((st.getMemoStateEx(1) % 10) == 0)
						{
							addRadar(player, 50693, 158674, 376);
						}
						
						htmltext = "30645-04.html";
					}
					else if (hasQuestItems(player, BOUGH_OF_SAINT))
					{
						htmltext = "30645-05.html";
					}
					break;
				}
				case SIR_GUSTAV_ATHEBALDT:
				{
					if (st.getMemoStateEx(0) == 18)
					{
						if ((st.getMemoStateEx(1) % 2) == 0)
						{
							if (getQuestItemsCount(player, BLOODED_FABRIC) >= 3)
							{
								takeItems(player, BLOODED_FABRIC, 3);
								st.setMemoStateEx(1, st.getMemoStateEx(1) + 1);
								htmltext = "30760-01.html";
							}
							else
							{
								htmltext = "30760-02.html";
							}
						}
						else if ((st.getMemoStateEx(1) % 2) == 1)
						{
							htmltext = "30760-03.html";
						}
					}
					break;
				}
				case HARDIN:
				{
					if (st.getMemoStateEx(0) == 18)
					{
						if ((st.getMemoStateEx(1) % 4) < 2)
						{
							if (getQuestItemsCount(player, BLOODED_FABRIC) >= 1)
							{
								takeItems(player, BLOODED_FABRIC, 1);
								st.setMemoStateEx(1, st.getMemoStateEx(1) + 2);
								htmltext = "30832-01.html";
							}
							else if (getQuestItemsCount(player, BLOODED_FABRIC) < 3)
							{
								htmltext = "30832-02.html";
							}
						}
						else
						{
							htmltext = "30832-03.html";
						}
					}
					break;
				}
			}
		}
		return htmltext;
	}
	
	private void getReward(L2PcInstance player)
	{
		final ClassId playerClassId = player.getClassId();
		final int playerLevel = player.getLevel();
		if ((playerClassId == ClassId.TREASURE_HUNTER) || (playerClassId == ClassId.PLAINS_WALKER) || (playerClassId == ClassId.ABYSS_WALKER) || (playerClassId == ClassId.ADVENTURER) || (playerClassId == ClassId.WIND_RIDER) || (playerClassId == ClassId.GHOST_HUNTER) || (playerClassId == ClassId.MALE_SOULBREAKER) || (playerClassId == ClassId.FEMALE_SOULBREAKER) || (playerClassId == ClassId.MALE_SOUL_HOUND) || (playerClassId == ClassId.FEMALE_SOUL_HOUND) || (playerClassId == ClassId.INSPECTOR) || (playerClassId == ClassId.JUDICATOR))
		{
			if (playerLevel < 69)
			{
				giveItems(player, KRIS_EDGE, 1);
				giveItems(player, SYNTHETIC_COKES, 2);
			}
			else
			{
				giveItems(player, DEMONS_DAGGER_EDGE, 1);
				giveItems(player, COKES, 2);
			}
		}
		else if ((playerClassId == ClassId.TYRANT) || (playerClassId == ClassId.GRAND_KHAVATARI))
		{
			if (playerLevel < 69)
			{
				giveItems(player, ARTHRO_NAIL_BLADE, 1);
				giveItems(player, SYNTHETIC_COKES, 2);
				giveItems(player, COKES, 1);
			}
			else
			{
				giveItems(player, BELLION_CESTUS_EDGE, 1);
				giveItems(player, ORIHARUKON_ORE, 2);
			}
		}
		else if ((playerClassId == ClassId.PALADIN) || (playerClassId == ClassId.DARK_AVENGER) || (playerClassId == ClassId.PROPHET) || (playerClassId == ClassId.TEMPLE_KNIGHT) || (playerClassId == ClassId.SWORDSINGER) || (playerClassId == ClassId.SHILLIEN_KNIGHT) || (playerClassId == ClassId.BLADEDANCER) || (playerClassId == ClassId.SHILLIEN_ELDER) || (playerClassId == ClassId.PHOENIX_KNIGHT) || (playerClassId == ClassId.HELL_KNIGHT) || (playerClassId == ClassId.HIEROPHANT) || (playerClassId == ClassId.EVA_TEMPLAR) || (playerClassId == ClassId.SWORD_MUSE) || (playerClassId == ClassId.SHILLIEN_TEMPLAR) || (playerClassId == ClassId.SPECTRAL_DANCER) || (playerClassId == ClassId.SHILLIEN_SAINT))
		{
			if (playerLevel < 69)
			{
				giveItems(player, KESHANBERK_BLADE, 1);
				giveItems(player, SYNTHETIC_COKES, 2);
			}
			else
			{
				giveItems(player, SWORD_OF_DAMASCUS_BLADE, 1);
				giveItems(player, ORIHARUKON_ORE, 2);
			}
		}
		else if ((playerClassId == ClassId.HAWKEYE) || (playerClassId == ClassId.SILVER_RANGER) || (playerClassId == ClassId.PHANTOM_RANGER) || (playerClassId == ClassId.SAGITTARIUS) || (playerClassId == ClassId.MOONLIGHT_SENTINEL) || (playerClassId == ClassId.GHOST_SENTINEL) || (playerClassId == ClassId.ARBALESTER) || (playerClassId == ClassId.TRICKSTER))
		{
			if (playerLevel < 69)
			{
				giveItems(player, DARK_ELVEN_LONGBOW_SHAFT, 1);
				giveItems(player, SYNTHETIC_COKES, 2);
			}
			else
			{
				giveItems(player, BOW_OF_PERIL_SHAFT, 1);
				giveItems(player, COARSE_BONE_POWDER, 9);
			}
		}
		else if ((playerClassId == ClassId.GLADIATOR) || (playerClassId == ClassId.BISHOP) || (playerClassId == ClassId.ELDER) || (playerClassId == ClassId.DUELIST) || (playerClassId == ClassId.CARDINAL) || (playerClassId == ClassId.EVA_SAINT))
		{
			if (playerLevel < 69)
			{
				giveItems(player, HEAVY_WAR_AXE_HEAD, 1);
				giveItems(player, SYNTHETIC_COKES, 2);
				giveItems(player, COKES, 1);
			}
			else
			{
				giveItems(player, ART_OF_BATTLE_AXE_BLADE, 1);
				giveItems(player, ORIHARUKON_ORE, 2);
			}
		}
		else if ((playerClassId == ClassId.WARLORD) || (playerClassId == ClassId.BOUNTY_HUNTER) || (playerClassId == ClassId.WARSMITH) || (playerClassId == ClassId.DREADNOUGHT) || (playerClassId == ClassId.FORTUNE_SEEKER) || (playerClassId == ClassId.MAESTRO))
		{
			if (playerLevel < 63)
			{
				giveItems(player, GREAT_AXE_HEAD, 1);
				giveItems(player, ENRIA, 1);
				giveItems(player, COKES, 1);
			}
			else
			{
				giveItems(player, LANCE_BLADE, 1);
				giveItems(player, ORIHARUKON_ORE, 2);
			}
		}
		else if ((playerClassId == ClassId.SORCERER) || (playerClassId == ClassId.SPELLSINGER) || (playerClassId == ClassId.OVERLORD) || (playerClassId == ClassId.ARCHMAGE) || (playerClassId == ClassId.MYSTIC_MUSE) || (playerClassId == ClassId.DOMINATOR))
		{
			if (playerLevel < 63)
			{
				giveItems(player, SPRITES_STAFF_HEAD, 1);
				giveItems(player, ORIHARUKON_ORE, 4);
				giveItems(player, COARSE_BONE_POWDER, 1);
			}
			else
			{
				giveItems(player, EVIL_SPIRIT_HEAD, 1);
				giveItems(player, ANIMAL_BONE, 5);
			}
		}
		else if ((playerClassId == ClassId.NECROMANCER) || (playerClassId == ClassId.SPELLHOWLER) || (playerClassId == ClassId.SOULTAKER) || (playerClassId == ClassId.STORM_SCREAMER))
		{
			giveItems(player, HELL_KNIFE_EDGE, 1);
			giveItems(player, SYNTHETIC_COKES, 2);
			giveItems(player, ANIMAL_BONE, 2);
		}
		else if ((playerClassId == ClassId.DESTROYER) || (playerClassId == ClassId.TITAN) || (playerClassId == ClassId.BERSERKER) || (playerClassId == ClassId.DOOMBRINGER))
		{
			giveItems(player, GREAT_SWORD_BLADE, 1);
			giveItems(player, VARNISH_OF_PURITY, 2);
			giveItems(player, SYNTHETIC_COKES, 2);
		}
		else if ((playerClassId == ClassId.ELEMENTAL_SUMMONER) || (playerClassId == ClassId.PHANTOM_SUMMONER) || (playerClassId == ClassId.ELEMENTAL_MASTER) || (playerClassId == ClassId.SPECTRAL_MASTER))
		{
			giveItems(player, SWORD_OF_DAMASCUS_BLADE, 1);
			giveItems(player, ENRIA, 1);
		}
		else if ((playerClassId == ClassId.WARCRYER) || (playerClassId == ClassId.DOOMCRYER))
		{
			giveItems(player, SWORD_OF_VALHALLA_BLADE, 1);
			giveItems(player, ORIHARUKON_ORE, 1);
			giveItems(player, VARNISH_OF_PURITY, 1);
		}
		else if ((playerClassId == ClassId.WARLOCK) || (playerClassId == ClassId.ARCANA_LORD))
		{
			giveItems(player, ART_OF_BATTLE_AXE_BLADE, 1);
			giveItems(player, ENRIA, 1);
		}
		else
		{
			giveAdena(player, 49000, true);
		}
	}
}

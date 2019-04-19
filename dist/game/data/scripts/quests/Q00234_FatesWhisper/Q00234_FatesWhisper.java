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
package quests.Q00234_FatesWhisper;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Util;

/**
 * Fate's Whisper (234)
 * @author Zealar
 */
public final class Q00234_FatesWhisper extends Quest
{
	// NPCs
	private static final int ZENKIN = 30178;
	private static final int CLIFF = 30182;
	private static final int MASTER_KASPAR = 30833;
	private static final int HEAD_BLACKSMITH_FERRIS = 30847;
	private static final int MAESTRO_LEORIN = 31002;
	private static final int COFFER_OF_THE_DEAD = 31027;
	private static final int CHEST_OF_KERNON = 31028;
	private static final int CHEST_OF_GOLKONDA = 31029;
	private static final int CHEST_OF_HALLATE = 31030;
	// Quest Items
	private static final int Q_BLOODY_FABRIC_Q0234 = 14361;
	private static final int Q_WHITE_FABRIC_Q0234 = 14362;
	private static final int Q_STAR_OF_DESTINY = 5011;
	private static final int Q_PIPETTE_KNIFE = 4665;
	private static final int Q_REIRIAS_SOULORB = 4666;
	private static final int Q_INFERNIUM_SCEPTER_1 = 4667;
	private static final int Q_INFERNIUM_SCEPTER_2 = 4668;
	private static final int Q_INFERNIUM_SCEPTER_3 = 4669;
	private static final int Q_MAESTRO_REORINS_HAMMER = 4670;
	private static final int Q_MAESTRO_REORINS_MOLD = 4671;
	private static final int Q_INFERNIUM_VARNISH = 4672;
	private static final int Q_RED_PIPETTE_KNIFE = 4673;
	// Other Items
	private static final int CRYSTAL_B = 1460;
	// Monsters
	private static final int PLATINUM_TRIBE_GRUNT = 20823;
	private static final int PLATINUM_TRIBE_ARCHER = 20826;
	private static final int PLATINUM_TRIBE_WARRIOR = 20827;
	private static final int PLATINUM_TRIBE_SHAMAN = 20828;
	private static final int PLATINUM_TRIBE_LORD = 20829;
	private static final int GUARDIAN_ANGEL = 20830;
	private static final int SEAL_ANGEL = 20831;
	private static final int SEAL_ANGEL_R = 20860;
	
	private static final int DOMB_DEATH_CABRIO = 25035;
	private static final int KERNON = 25054;
	private static final int GOLKONDA_LONGHORN = 25126;
	private static final int HALLATE_THE_DEATH_LORD = 25220;
	private static final int BAIUM = 29020;
	
	// B-grade
	private static final int SWORD_OF_DAMASCUS = 79;
	private static final int SWORD_OF_DAMASCUS_FOCUS = 4717;
	private static final int SWORD_OF_DAMASCUS_CRT_DAMAGE = 4718;
	private static final int SWORD_OF_DAMASCUS_HASTE = 4719;
	private static final int HAZARD_BOW = 287;
	private static final int HAZARD_BOW_GUIDENCE = 4828;
	private static final int HAZARD_BOW_QUICKRECOVERY = 4829;
	private static final int HAZARD_BOW_CHEAPSHOT = 4830;
	private static final int LANCIA = 97;
	private static final int LANCIA_ANGER = 4858;
	private static final int LANCIA_CRT_STUN = 4859;
	private static final int LANCIA_LONGBLOW = 4860;
	private static final int ART_OF_BATTLE_AXE = 175;
	private static final int ART_OF_BATTLE_AXE_HEALTH = 4753;
	private static final int ART_OF_BATTLE_AXE_RSK_FOCUS = 4754;
	private static final int ART_OF_BATTLE_AXE_HASTE = 4755;
	private static final int STAFF_OF_EVIL_SPRIT = 210;
	private static final int STAFF_OF_EVIL_SPRIT_MAGICFOCUS = 4900;
	private static final int STAFF_OF_EVIL_SPRIT_MAGICBLESSTHEBODY = 4901;
	private static final int STAFF_OF_EVIL_SPRIT_MAGICPOISON = 4902;
	private static final int DEMONS_SWORD = 234;
	private static final int DEMONS_SWORD_CRT_BLEED = 4780;
	private static final int DEMONS_SWORD_CRT_POISON = 4781;
	private static final int DEMONS_SWORD_MIGHTMOTAL = 4782;
	private static final int BELLION_CESTUS = 268;
	private static final int BELLION_CESTUS_CRT_DRAIN = 4804;
	private static final int BELLION_CESTUS_CRT_POISON = 4805;
	private static final int BELLION_CESTUS_RSK_HASTE = 4806;
	private static final int DEADMANS_GLORY = 171;
	private static final int DEADMANS_GLORY_ANGER = 4750;
	private static final int DEADMANS_GLORY_HEALTH = 4751;
	private static final int DEADMANS_GLORY_HASTE = 4752;
	private static final int SAMURAI_LONGSWORD_SAMURAI_LONGSWORD = 2626;
	private static final int GUARDIANS_SWORD = 7883;
	private static final int GUARDIANS_SWORD_CRT_DRAIN = 8105;
	private static final int GUARDIANS_SWORD_HEALTH = 8106;
	private static final int GUARDIANS_SWORD_CRT_BLEED = 8107;
	private static final int TEARS_OF_WIZARD = 7889;
	private static final int TEARS_OF_WIZARD_ACUMEN = 8117;
	private static final int TEARS_OF_WIZARD_MAGICPOWER = 8118;
	private static final int TEARS_OF_WIZARD_UPDOWN = 8119;
	private static final int STAR_BUSTER = 7901;
	private static final int STAR_BUSTER_HEALTH = 8132;
	private static final int STAR_BUSTER_HASTE = 8133;
	private static final int STAR_BUSTER_RSK_FOCUS = 8134;
	private static final int BONE_OF_KAIM_VANUL = 7893;
	private static final int BONE_OF_KAIM_VANUL_MANAUP = 8144;
	private static final int BONE_OF_KAIM_VANUL_MAGICSILENCE = 8145;
	private static final int BONE_OF_KAIM_VANUL_UPDOWN = 8146;
	// A-grade
	private static final int TALLUM_BLADE = 80;
	private static final int CARNIUM_BOW = 288;
	private static final int HALBARD = 98;
	private static final int ELEMENTAL_SWORD = 150;
	private static final int DASPARIONS_STAFF = 212;
	private static final int BLOODY_ORCHID = 235;
	private static final int BLOOD_TORNADO = 269;
	private static final int METEOR_SHOWER = 2504;
	private static final int KSHANBERK_KSHANBERK = 5233;
	private static final int INFERNO_MASTER = 7884;
	private static final int EYE_OF_SOUL = 7894;
	private static final int HAMMER_OF_DESTROYER = 7899;
	
	public Q00234_FatesWhisper()
	{
		super(234);
		addStartNpc(MAESTRO_LEORIN);
		addTalkId(ZENKIN, CLIFF, MASTER_KASPAR, HEAD_BLACKSMITH_FERRIS, MAESTRO_LEORIN);
		addTalkId(COFFER_OF_THE_DEAD, CHEST_OF_KERNON, CHEST_OF_HALLATE, CHEST_OF_GOLKONDA);
		
		addKillId(PLATINUM_TRIBE_GRUNT, PLATINUM_TRIBE_ARCHER, PLATINUM_TRIBE_WARRIOR, PLATINUM_TRIBE_SHAMAN, PLATINUM_TRIBE_LORD, GUARDIAN_ANGEL, SEAL_ANGEL, SEAL_ANGEL_R);
		addKillId(DOMB_DEATH_CABRIO, KERNON, GOLKONDA_LONGHORN, HALLATE_THE_DEATH_LORD);
		
		addSpawnId(COFFER_OF_THE_DEAD, CHEST_OF_KERNON, CHEST_OF_HALLATE, CHEST_OF_GOLKONDA);
		addAttackId(BAIUM);
		registerQuestItems(Q_BLOODY_FABRIC_Q0234, Q_WHITE_FABRIC_Q0234, Q_PIPETTE_KNIFE, Q_REIRIAS_SOULORB, Q_INFERNIUM_SCEPTER_1, Q_INFERNIUM_SCEPTER_2, Q_INFERNIUM_SCEPTER_3, Q_MAESTRO_REORINS_HAMMER, Q_MAESTRO_REORINS_MOLD, Q_INFERNIUM_VARNISH, Q_RED_PIPETTE_KNIFE);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		switch (npc.getId())
		{
			case COFFER_OF_THE_DEAD:
			{
				startQuestTimer("23401", 1000 * 120, npc, null);
				break;
			}
			case CHEST_OF_KERNON:
			{
				startQuestTimer("23402", 1000 * 120, npc, null);
				break;
			}
			case CHEST_OF_HALLATE:
			{
				startQuestTimer("23403", 1000 * 120, npc, null);
				break;
			}
			case CHEST_OF_GOLKONDA:
			{
				startQuestTimer("23404", 1000 * 120, npc, null);
				break;
			}
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		final String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case ZENKIN:
			{
				switch (qs.getMemoState())
				{
					case 6:
					{
						return "30178-01.html";
					}
					case 7:
					{
						return "30178-03.html";
					}
					case 8:
					{
						return "30178-04.html";
					}
				}
				break;
			}
			case CLIFF:
			{
				if (qs.isMemoState(4) && !hasQuestItems(player, Q_INFERNIUM_VARNISH))
				{
					return "30182-01.html";
				}
				if (qs.isMemoState(4) && hasQuestItems(player, Q_INFERNIUM_VARNISH))
				{
					return "30182-05.html";
				}
				if (qs.getMemoState() >= 5)
				{
					return "30182-06.html";
				}
			}
			case MASTER_KASPAR:
			{
				if (qs.isMemoState(7))
				{
					return "30833-01.html";
				}
				if (qs.isMemoState(8) && !hasQuestItems(player, Q_RED_PIPETTE_KNIFE) && !hasQuestItems(player, Q_BLOODY_FABRIC_Q0234, Q_WHITE_FABRIC_Q0234))
				{
					return "30833-03.html";
				}
				if (qs.isMemoState(8) && hasQuestItems(player, Q_RED_PIPETTE_KNIFE) && !hasQuestItems(player, Q_BLOODY_FABRIC_Q0234, Q_WHITE_FABRIC_Q0234))
				{
					giveItems(player, Q_MAESTRO_REORINS_MOLD, 1);
					takeItems(player, Q_RED_PIPETTE_KNIFE, 1);
					qs.setMemoState(9);
					qs.setCond(10, true);
					qs.showQuestionMark(234);
					return "30833-04.html";
				}
				if (qs.isMemoState(8) && !hasQuestItems(player, Q_RED_PIPETTE_KNIFE) && (getQuestItemsCount(player, Q_BLOODY_FABRIC_Q0234) < 30) && ((getQuestItemsCount(player, Q_BLOODY_FABRIC_Q0234) + getQuestItemsCount(player, Q_WHITE_FABRIC_Q0234)) >= 30))
				{
					return "30833-03c.html";
				}
				if (qs.isMemoState(8) && !hasQuestItems(player, Q_RED_PIPETTE_KNIFE) && (getQuestItemsCount(player, Q_BLOODY_FABRIC_Q0234) >= 30) && ((getQuestItemsCount(player, Q_BLOODY_FABRIC_Q0234) + getQuestItemsCount(player, Q_WHITE_FABRIC_Q0234)) >= 30))
				{
					giveItems(player, Q_MAESTRO_REORINS_MOLD, 1);
					takeItems(player, Q_BLOODY_FABRIC_Q0234, -1);
					qs.setMemoState(9);
					qs.setCond(10, true);
					qs.showQuestionMark(234);
					return "30833-03d.html";
				}
				if (qs.isMemoState(8) && !hasQuestItems(player, Q_RED_PIPETTE_KNIFE) && ((getQuestItemsCount(player, Q_BLOODY_FABRIC_Q0234) + getQuestItemsCount(player, Q_WHITE_FABRIC_Q0234)) < 30) && ((getQuestItemsCount(player, Q_BLOODY_FABRIC_Q0234) + getQuestItemsCount(player, Q_WHITE_FABRIC_Q0234)) > 0))
				{
					giveItems(player, Q_WHITE_FABRIC_Q0234, 30 - getQuestItemsCount(player, Q_WHITE_FABRIC_Q0234));
					takeItems(player, Q_BLOODY_FABRIC_Q0234, -1);
					return "30833-03e.html";
				}
				if (qs.getMemoState() >= 9)
				{
					return "30833-05.html";
				}
				break;
			}
			case HEAD_BLACKSMITH_FERRIS:
			{
				if (qs.isMemoState(5))
				{
					if (hasQuestItems(player, Q_MAESTRO_REORINS_HAMMER))
					{
						return "30847-02.html";
					}
					giveItems(player, Q_MAESTRO_REORINS_HAMMER, 1);
					return "30847-01.html";
				}
				if (qs.getMemoState() >= 6)
				{
					return "30847-03.html";
				}
				break;
			}
			case MAESTRO_LEORIN:
			{
				if (qs.isCreated() && (player.getLevel() >= 75))
				{
					return "31002-01.htm";
				}
				if (qs.isCreated() && (player.getLevel() < 75))
				{
					return "31002-01a.htm";
				}
				if (qs.isCompleted())
				{
					return getAlreadyCompletedMsg(player);
				}
				if (qs.isMemoState(1) && !hasQuestItems(player, Q_REIRIAS_SOULORB))
				{
					return "31002-09.html";
				}
				if (qs.isMemoState(1) && hasQuestItems(player, Q_REIRIAS_SOULORB))
				{
					return "31002-10.html";
				}
				if (qs.isMemoState(2) && !hasQuestItems(player, Q_INFERNIUM_SCEPTER_1, Q_INFERNIUM_SCEPTER_2, Q_INFERNIUM_SCEPTER_3))
				{
					return "31002-12.html";
				}
				if (qs.isMemoState(2) && hasQuestItems(player, Q_INFERNIUM_SCEPTER_1, Q_INFERNIUM_SCEPTER_2, Q_INFERNIUM_SCEPTER_3))
				{
					return "31002-13.html";
				}
				if (qs.isMemoState(4) && !hasQuestItems(player, Q_INFERNIUM_VARNISH))
				{
					return "31002-15.html";
				}
				if (qs.isMemoState(4) && hasQuestItems(player, Q_INFERNIUM_VARNISH))
				{
					return "31002-16.html";
				}
				if (qs.isMemoState(5) && !hasQuestItems(player, Q_MAESTRO_REORINS_HAMMER))
				{
					return "31002-18.html";
				}
				if (qs.isMemoState(5) && hasQuestItems(player, Q_MAESTRO_REORINS_HAMMER))
				{
					return "31002-19.html";
				}
				if ((qs.getMemoState() < 9) && (qs.getMemoState() >= 6))
				{
					return "31002-21.html";
				}
				if (qs.isMemoState(9) && hasQuestItems(player, Q_MAESTRO_REORINS_MOLD))
				{
					return "31002-22.html";
				}
				if (qs.isMemoState(10) && (getQuestItemsCount(player, CRYSTAL_B) < 984))
				{
					return "31002-24.html";
				}
				if (qs.isMemoState(10) && (getQuestItemsCount(player, CRYSTAL_B) >= 984))
				{
					return "31002-25.html";
				}
				switch (qs.getMemoState())
				{
					case 11:
					{
						if (hasAtLeastOneQuestItem(player, SWORD_OF_DAMASCUS, SWORD_OF_DAMASCUS_FOCUS, SWORD_OF_DAMASCUS_CRT_DAMAGE, SWORD_OF_DAMASCUS_HASTE))
						{
							return "31002-35.html";
						}
						return "31002-35a.html";
					}
					case 12:
					{
						if (hasAtLeastOneQuestItem(player, HAZARD_BOW_GUIDENCE, HAZARD_BOW_QUICKRECOVERY, HAZARD_BOW_CHEAPSHOT, HAZARD_BOW))
						{
							return "31002-36.html";
						}
						return "31002-36a.html";
					}
					case 13:
					{
						if (hasAtLeastOneQuestItem(player, LANCIA_ANGER, LANCIA_CRT_STUN, LANCIA_LONGBLOW, LANCIA))
						{
							return "31002-37.html";
						}
						return "31002-37a.html";
					}
					case 14:
					{
						if (hasAtLeastOneQuestItem(player, ART_OF_BATTLE_AXE_HEALTH, ART_OF_BATTLE_AXE_RSK_FOCUS, ART_OF_BATTLE_AXE_HASTE, ART_OF_BATTLE_AXE))
						{
							return "31002-38.html";
						}
						return "31002-38a.html";
					}
					case 15:
					{
						if (hasAtLeastOneQuestItem(player, STAFF_OF_EVIL_SPRIT_MAGICFOCUS, STAFF_OF_EVIL_SPRIT_MAGICBLESSTHEBODY, STAFF_OF_EVIL_SPRIT_MAGICPOISON, STAFF_OF_EVIL_SPRIT))
						{
							return "31002-39.html";
						}
						return "31002-39a.html";
					}
					case 16:
					{
						if (hasAtLeastOneQuestItem(player, DEMONS_SWORD_CRT_BLEED, DEMONS_SWORD_CRT_POISON, DEMONS_SWORD_MIGHTMOTAL, DEMONS_SWORD))
						{
							return "31002-40.html";
						}
						return "31002-40a.html";
					}
					case 17:
					{
						if (hasAtLeastOneQuestItem(player, BELLION_CESTUS_CRT_DRAIN, BELLION_CESTUS_CRT_POISON, BELLION_CESTUS_RSK_HASTE, BELLION_CESTUS))
						{
							return "31002-41.html";
						}
						return "31002-41a.html";
					}
					case 18:
					{
						if (hasAtLeastOneQuestItem(player, DEADMANS_GLORY_ANGER, DEADMANS_GLORY_HEALTH, DEADMANS_GLORY_HASTE, DEADMANS_GLORY))
						{
							return "31002-42.html";
						}
						return "31002-42a.html";
					}
					case 19:
					{
						if (hasAtLeastOneQuestItem(player, SAMURAI_LONGSWORD_SAMURAI_LONGSWORD))
						{
							return "31002-43.html";
						}
						return "31002-43a.html";
					}
					case 41:
					{
						if (hasAtLeastOneQuestItem(player, GUARDIANS_SWORD, GUARDIANS_SWORD_CRT_DRAIN, GUARDIANS_SWORD_HEALTH, GUARDIANS_SWORD_CRT_BLEED))
						{
							return "31002-43b.html";
						}
						return "31002-43c.html";
					}
					case 42:
					{
						if (hasAtLeastOneQuestItem(player, TEARS_OF_WIZARD, TEARS_OF_WIZARD_ACUMEN, TEARS_OF_WIZARD_MAGICPOWER, TEARS_OF_WIZARD_UPDOWN))
						{
							return "31002-43d.html";
						}
						return "31002-43e.html";
					}
					case 43:
					{
						if (hasAtLeastOneQuestItem(player, STAR_BUSTER, STAR_BUSTER_HEALTH, STAR_BUSTER_HASTE, STAR_BUSTER_RSK_FOCUS))
						{
							return "31002-43f.html";
						}
						return "31002-43g.html";
					}
					case 44:
					{
						if (hasAtLeastOneQuestItem(player, BONE_OF_KAIM_VANUL, BONE_OF_KAIM_VANUL_MANAUP, BONE_OF_KAIM_VANUL_MAGICSILENCE, BONE_OF_KAIM_VANUL_UPDOWN))
						{
							return "31002-43h.html";
						}
						return "31002-43i.html";
					}
				}
				break;
			}
			case COFFER_OF_THE_DEAD:
			{
				if (qs.isMemoState(1) && !hasQuestItems(player, Q_REIRIAS_SOULORB))
				{
					giveItems(player, Q_REIRIAS_SOULORB, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					return "31027-01.html";
				}
				if ((qs.getMemoState() > 1) || hasQuestItems(player, Q_REIRIAS_SOULORB))
				{
					return "31027-02.html";
				}
				break;
			}
			case CHEST_OF_KERNON:
			{
				if (qs.isMemoState(2) && !hasQuestItems(player, Q_INFERNIUM_SCEPTER_1))
				{
					giveItems(player, Q_INFERNIUM_SCEPTER_1, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					return "31028-01.html";
				}
				if ((qs.getMemoState() != 2) || hasQuestItems(player, Q_INFERNIUM_SCEPTER_1))
				{
					return "31028-02.html";
				}
				break;
			}
			case CHEST_OF_GOLKONDA:
			{
				if (qs.isMemoState(2) && !hasQuestItems(player, Q_INFERNIUM_SCEPTER_2))
				{
					giveItems(player, Q_INFERNIUM_SCEPTER_2, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					return "31029-01.html";
				}
				if ((qs.getMemoState() != 2) || hasQuestItems(player, Q_INFERNIUM_SCEPTER_2))
				{
					return "31029-02.html";
				}
				break;
			}
			case CHEST_OF_HALLATE:
			{
				if (qs.isMemoState(2) && !hasQuestItems(player, Q_INFERNIUM_SCEPTER_3))
				{
					giveItems(player, Q_INFERNIUM_SCEPTER_3, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					return "31030-01.html";
				}
				if ((qs.getMemoState() != 2) || hasQuestItems(player, Q_INFERNIUM_SCEPTER_3))
				{
					return "31030-02.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (player == null)
		{
			if (event.equals("23401") || event.equals("23402") || event.equals("23403") || event.equals("23404"))
			{
				npc.decayMe();
			}
			return super.onAdvEvent(event, npc, player);
		}
		
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		final String htmltext = null;
		
		if (event.equals("QUEST_ACCEPTED"))
		{
			qs.setMemoState(1);
			qs.startQuest();
			qs.showQuestionMark(234);
			playSound(player, QuestSound.ITEMSOUND_QUEST_ACCEPT);
			return "31002-06.html";
		}
		if (event.contains(".htm"))
		{
			return event;
		}
		
		final int npcId = npc.getId();
		final int eventID = Integer.parseInt(event);
		
		switch (npcId)
		{
			case ZENKIN:
			{
				switch (eventID)
				{
					case 1:
					{
						qs.setMemoState(7);
						qs.setCond(6);
						qs.showQuestionMark(234);
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						return "30178-02.html";
					}
				}
			}
			case CLIFF:
			{
				switch (eventID)
				{
					case 1:
					{
						return "30182-02.html";
					}
					case 2:
					{
						return "30182-03.html";
					}
					case 3:
					{
						if ((qs.getMemoState() == 4) && !hasQuestItems(player, Q_INFERNIUM_VARNISH))
						{
							giveItems(player, Q_INFERNIUM_VARNISH, 1);
							return "30182-04.html";
						}
					}
				}
				break;
			}
			case MASTER_KASPAR:
			{
				switch (eventID)
				{
					case 1:
					{
						if (qs.isMemoState(7))
						{
							return "30833-02.html";
						}
						break;
					}
					case 2:
					{
						if (qs.isMemoState(7))
						{
							giveItems(player, Q_PIPETTE_KNIFE, 1);
							qs.setMemoState(8);
							qs.setCond(7, true);
							qs.showQuestionMark(234);
							return "30833-03a.html";
						}
						break;
					}
					case 3:
					{
						if (qs.isMemoState(7))
						{
							giveItems(player, Q_WHITE_FABRIC_Q0234, 30);
							qs.setMemoState(8);
							qs.setCond(8, true);
							qs.showQuestionMark(234);
							return "30833-03b.html";
						}
						break;
					}
				}
				break;
			}
			case MAESTRO_LEORIN:
			{
				switch (eventID)
				{
					case 1:
					{
						return "31002-02.htm";
					}
					case 2:
					{
						return "31002-03.html";
					}
					case 3:
					{
						return "31002-04.html";
					}
					case 4:
					{
						if (!qs.isCompleted() && (player.getLevel() >= 75))
						{
							return "31002-05.html";
						}
						break;
					}
					case 5:
					{
						if (qs.isMemoState(1) && hasQuestItems(player, Q_REIRIAS_SOULORB))
						{
							takeItems(player, Q_REIRIAS_SOULORB, 1);
							qs.setMemoState(2);
							qs.setCond(2, true);
							qs.showQuestionMark(234);
							return "31002-11.html";
						}
						break;
					}
					case 6:
					{
						if (qs.isMemoState(2) && hasQuestItems(player, Q_INFERNIUM_SCEPTER_1, Q_INFERNIUM_SCEPTER_2, Q_INFERNIUM_SCEPTER_3))
						{
							takeItems(player, Q_INFERNIUM_SCEPTER_1, -1);
							takeItems(player, Q_INFERNIUM_SCEPTER_2, -1);
							takeItems(player, Q_INFERNIUM_SCEPTER_3, -1);
							qs.setMemoState(4);
							qs.setCond(3, true);
							qs.showQuestionMark(234);
							return "31002-14.html";
						}
						break;
					}
					case 7:
					{
						if (qs.isMemoState(4) && hasQuestItems(player, Q_INFERNIUM_VARNISH))
						{
							takeItems(player, Q_INFERNIUM_VARNISH, 1);
							qs.setMemoState(5);
							qs.setCond(4, true);
							qs.showQuestionMark(234);
							return "31002-17.html";
						}
						break;
					}
					case 8:
					{
						if (qs.isMemoState(5) && hasQuestItems(player, Q_MAESTRO_REORINS_HAMMER))
						{
							takeItems(player, Q_MAESTRO_REORINS_HAMMER, 1);
							qs.setMemoState(6);
							qs.setCond(5, true);
							qs.showQuestionMark(234);
							return "31002-20.html";
						}
						break;
					}
					case 9:
					{
						if (qs.isMemoState(9) && hasQuestItems(player, Q_MAESTRO_REORINS_MOLD))
						{
							takeItems(player, Q_MAESTRO_REORINS_MOLD, 1);
							qs.setMemoState(10);
							qs.setCond(11, true);
							qs.showQuestionMark(234);
							return "31002-23.html";
						}
						break;
					}
					case 10:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(11);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-26.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 11:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(19);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-26a.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 12:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(12);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-27.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 13:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(13);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-28.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 14:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(14);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-29.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 15:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(15);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-30.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 16:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(16);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-31.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 17:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(17);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-32.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 18:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(18);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-33.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 41:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(41);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-33a.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 42:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(42);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-33b.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 43:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(43);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-33c.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 44:
					{
						if (qs.isMemoState(10))
						{
							if (getQuestItemsCount(player, CRYSTAL_B) >= 984)
							{
								takeItems(player, CRYSTAL_B, 984);
								qs.setMemoState(44);
								qs.setCond(12, true);
								qs.showQuestionMark(234);
								return "31002-33d.html";
							}
							return "31002-34.html";
						}
						break;
					}
					case 21:
					{
						if (calculateReward(qs, player, TALLUM_BLADE))
						{
							return "31002-44.html";
						}
						break;
					}
					case 22:
					{
						if (calculateReward(qs, player, CARNIUM_BOW))
						{
							return "31002-44.html";
						}
						break;
					}
					case 23:
					{
						if (calculateReward(qs, player, HALBARD))
						{
							return "31002-44.html";
						}
						break;
					}
					case 24:
					{
						if (calculateReward(qs, player, ELEMENTAL_SWORD))
						{
							return "31002-44.html";
						}
						break;
					}
					case 25:
					{
						if (calculateReward(qs, player, DASPARIONS_STAFF))
						{
							return "31002-44.html";
						}
						break;
					}
					case 26:
					{
						if (calculateReward(qs, player, BLOODY_ORCHID))
						{
							return "31002-44.html";
						}
						break;
					}
					case 27:
					{
						if (calculateReward(qs, player, BLOOD_TORNADO))
						{
							return "31002-44.html";
						}
						break;
					}
					case 28:
					{
						if (calculateReward(qs, player, METEOR_SHOWER))
						{
							return "31002-44.html";
						}
						break;
					}
					case 29:
					{
						if (calculateReward(qs, player, KSHANBERK_KSHANBERK))
						{
							return "31002-44.html";
						}
						break;
					}
					case 30:
					{
						if (calculateReward(qs, player, INFERNO_MASTER))
						{
							return "31002-44.html";
						}
						break;
					}
					case 31:
					{
						if (calculateReward(qs, player, EYE_OF_SOUL))
						{
							return "31002-44.html";
						}
						break;
					}
					case 32:
					{
						if (calculateReward(qs, player, HAMMER_OF_DESTROYER))
						{
							return "31002-44.html";
						}
						break;
					}
				}
			}
			
		}
		return htmltext;
	}
	
	private boolean calculateReward(QuestState qs, L2PcInstance player, int REWARD)
	{
		switch (qs.getMemoState())
		{
			case 11:
			{
				return getReward(qs, player, SWORD_OF_DAMASCUS, SWORD_OF_DAMASCUS_FOCUS, SWORD_OF_DAMASCUS_CRT_DAMAGE, SWORD_OF_DAMASCUS_HASTE, REWARD);
			}
			case 12:
			{
				return getReward(qs, player, HAZARD_BOW, HAZARD_BOW_GUIDENCE, HAZARD_BOW_QUICKRECOVERY, HAZARD_BOW_CHEAPSHOT, REWARD);
			}
			case 13:
			{
				return getReward(qs, player, LANCIA, LANCIA_ANGER, LANCIA_CRT_STUN, LANCIA_LONGBLOW, REWARD);
			}
			case 14:
			{
				return getReward(qs, player, ART_OF_BATTLE_AXE, ART_OF_BATTLE_AXE_HEALTH, ART_OF_BATTLE_AXE_RSK_FOCUS, ART_OF_BATTLE_AXE_HASTE, REWARD);
			}
			case 15:
			{
				return getReward(qs, player, STAFF_OF_EVIL_SPRIT, STAFF_OF_EVIL_SPRIT_MAGICFOCUS, STAFF_OF_EVIL_SPRIT_MAGICBLESSTHEBODY, STAFF_OF_EVIL_SPRIT_MAGICPOISON, REWARD);
			}
			case 16:
			{
				return getReward(qs, player, DEMONS_SWORD, DEMONS_SWORD_CRT_BLEED, DEMONS_SWORD_CRT_POISON, DEMONS_SWORD_MIGHTMOTAL, REWARD);
			}
			case 17:
			{
				return getReward(qs, player, BELLION_CESTUS, BELLION_CESTUS_CRT_DRAIN, BELLION_CESTUS_CRT_POISON, BELLION_CESTUS_RSK_HASTE, REWARD);
			}
			case 18:
			{
				return getReward(qs, player, DEADMANS_GLORY, DEADMANS_GLORY_ANGER, DEADMANS_GLORY_HEALTH, DEADMANS_GLORY_HASTE, REWARD);
			}
			case 19:
			{
				return getReward(qs, player, SAMURAI_LONGSWORD_SAMURAI_LONGSWORD, 0, 0, 0, REWARD);
			}
			case 41:
			{
				return getReward(qs, player, GUARDIANS_SWORD, GUARDIANS_SWORD_CRT_DRAIN, GUARDIANS_SWORD_HEALTH, GUARDIANS_SWORD_CRT_BLEED, REWARD);
			}
			case 42:
			{
				return getReward(qs, player, TEARS_OF_WIZARD, TEARS_OF_WIZARD_ACUMEN, TEARS_OF_WIZARD_MAGICPOWER, TEARS_OF_WIZARD_UPDOWN, REWARD);
			}
			case 43:
			{
				return getReward(qs, player, STAR_BUSTER, STAR_BUSTER_HEALTH, STAR_BUSTER_HASTE, STAR_BUSTER_RSK_FOCUS, REWARD);
			}
			case 44:
			{
				return getReward(qs, player, BONE_OF_KAIM_VANUL, BONE_OF_KAIM_VANUL_MANAUP, BONE_OF_KAIM_VANUL_MAGICSILENCE, BONE_OF_KAIM_VANUL_UPDOWN, REWARD);
			}
		}
		return false;
	}
	
	private boolean getReward(QuestState qs, L2PcInstance player, int ITEM1, int ITEM2, int ITEM3, int ITEM4, int REWARD)
	{
		if (hasAtLeastOneQuestItem(player, ITEM1, ITEM2, ITEM3, ITEM4))
		{
			giveItems(player, REWARD, 1);
			giveItems(player, Q_STAR_OF_DESTINY, 1);
			if (hasQuestItems(player, ITEM1))
			{
				takeItems(player, ITEM1, 1);
			}
			else if (hasQuestItems(player, ITEM2))
			{
				takeItems(player, ITEM2, 1);
			}
			else if (hasQuestItems(player, ITEM3))
			{
				takeItems(player, ITEM3, 1);
			}
			else if (hasQuestItems(player, ITEM4))
			{
				takeItems(player, ITEM4, 1);
			}
			qs.exitQuest(false, true);
			player.broadcastSocialAction(3);
			return true;
		}
		return false;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		switch (npc.getId())
		{
			case DOMB_DEATH_CABRIO:
			{
				addSpawn(COFFER_OF_THE_DEAD, npc.getLocation());
				return super.onKill(npc, killer, isSummon);
			}
			case KERNON:
			{
				addSpawn(CHEST_OF_KERNON, npc.getLocation());
				return super.onKill(npc, killer, isSummon);
			}
			case GOLKONDA_LONGHORN:
			{
				addSpawn(CHEST_OF_GOLKONDA, npc.getLocation());
				return super.onKill(npc, killer, isSummon);
			}
			case HALLATE_THE_DEATH_LORD:
			{
				addSpawn(CHEST_OF_HALLATE, npc.getLocation());
				return super.onKill(npc, killer, isSummon);
			}
		}
		final QuestState qs = getRandomPlayerFromParty(killer, npc, 8);
		if (qs != null)
		{
			switch (npc.getId())
			{
				case PLATINUM_TRIBE_GRUNT:
				case PLATINUM_TRIBE_ARCHER:
				case PLATINUM_TRIBE_WARRIOR:
				case PLATINUM_TRIBE_SHAMAN:
				case PLATINUM_TRIBE_LORD:
				case GUARDIAN_ANGEL:
				case SEAL_ANGEL:
				case SEAL_ANGEL_R:
				{
					final L2PcInstance member = qs.getPlayer();
					if ((member != null) && (getQuestItemsCount(member, Q_WHITE_FABRIC_Q0234) != 0))
					{
						takeItems(member, Q_WHITE_FABRIC_Q0234, 1);
						giveItemRandomly(member, npc, Q_BLOODY_FABRIC_Q0234, 1, 0, 1, false);
						if (getQuestItemsCount(member, Q_BLOODY_FABRIC_Q0234) >= 29)
						{
							qs.setCond(9, true);
							qs.showQuestionMark(234);
						}
						else
						{
							playSound(member, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
			}
			
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		final QuestState qs = getQuestState(attacker, false);
		if ((qs != null) && (npc.getId() == BAIUM))
		{
			if ((attacker.getActiveWeaponItem() != null) && (attacker.getActiveWeaponItem().getId() == Q_PIPETTE_KNIFE))
			{
				takeItems(attacker, Q_PIPETTE_KNIFE, 1);
				giveItems(attacker, Q_RED_PIPETTE_KNIFE, 1);
				playSound(attacker, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.GENERAL, npc.getId(), NpcStringId.WHO_DARES_TO_TRY_AND_STEAL_MY_NOBLE_BLOOD));
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	private QuestState getRandomPlayerFromParty(L2PcInstance player, L2Npc npc, int memoState)
	{
		final QuestState qs = getQuestState(player, false);
		final List<QuestState> candidates = new ArrayList<>();
		
		if ((qs != null) && qs.isStarted() && (qs.getMemoState() == memoState) && !hasQuestItems(player, Q_WHITE_FABRIC_Q0234))
		{
			candidates.add(qs);
			candidates.add(qs);
		}
		
		if (player.isInParty())
		{
			player.getParty().getMembers().stream().forEach(pm ->
			{
				
				final QuestState qss = getQuestState(pm, false);
				if ((qss != null) && qss.isStarted() && (qss.getMemoState() == memoState) && !hasQuestItems(player, Q_WHITE_FABRIC_Q0234) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, pm, true))
				{
					candidates.add(qss);
				}
			});
		}
		return candidates.isEmpty() ? null : candidates.get(getRandom(candidates.size()));
	}
}

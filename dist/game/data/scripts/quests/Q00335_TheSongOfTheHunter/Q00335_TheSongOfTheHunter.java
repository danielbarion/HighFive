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
package quests.Q00335_TheSongOfTheHunter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.L2Weapon;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.CreatureSay;

/**
 * The Song of the Hunter (335)
 * @author Zoey76
 * @since 2.6.0.0
 */
public class Q00335_TheSongOfTheHunter extends Quest
{
	// NPCs
	private static final int GREY = 30744;
	private static final int TOR = 30745;
	private static final int CYBELLIN = 30746;
	// Monsters
	private static final int BREKA_ORC_SHAMAN = 20269;
	private static final int BREKA_ORC_WARRIOR = 20271;
	private static final int GUARDIAN_BASILISK = 20550;
	private static final int FETTERED_SOUL = 20552;
	private static final int WINDSUS = 20553;
	private static final int GRANDIS = 20554;
	private static final int GIANT_FUNGUS = 20555;
	private static final int GIANT_MONSTEREYE = 20556;
	private static final int DIRE_WYRM = 20557;
	private static final int ROTTING_TREE = 20558;
	private static final int TRISALIM_SPIDER = 20560;
	private static final int TRISALIM_TARANTULA = 20561;
	private static final int SPORE_ZOMBIE = 20562;
	private static final int MANASHEN_GARGOYLE = 20563;
	private static final int ENCHANTED_STONE_GOLEM = 20565;
	private static final int ENCHANTED_GARGOYLE = 20567;
	private static final int TARLK_BUGBEAR_WARRIOR = 20571;
	private static final int LETO_LIZARDMAN_ARCHER = 20578;
	private static final int LETO_LIZARDMAN_SOLDIER = 20579;
	private static final int LETO_LIZARDMAN_SHAMAN = 20581;
	private static final int LETO_LIZARDMAN_OVERLORD = 20582;
	private static final int TIMAK_ORC_WARRIOR = 20586;
	private static final int TIMAK_ORC_OVERLORD = 20588;
	private static final int FLINE = 20589;
	private static final int LIELE = 20590;
	private static final int VALLEY_TREANT = 20591;
	private static final int SATYR = 20592;
	private static final int UNICORN = 20593;
	private static final int FOREST_RUNNER = 20594;
	private static final int VALLEY_TREANT_ELDER = 20597;
	private static final int SATYR_ELDER = 20598;
	private static final int UNICORN_ELDER = 20599;
	private static final int KARUL_BUGBEAR = 20600;
	private static final int TAMLIN_ORC = 20601;
	private static final int TAMLIN_ORC_ARCHER = 20602;
	private static final int KRONBE_SPIDER = 20603;
	private static final int TAIK_ORC_ARCHER = 20631;
	private static final int TAIK_ORC_WARRIOR = 20632;
	private static final int TAIK_ORC_SHAMAN = 20633;
	private static final int TAIK_ORC_CAPTAIN = 20634;
	private static final int MIRROR = 20639;
	private static final int HARIT_LIZARDMAN_GRUNT = 20641;
	private static final int HARIT_LIZARDMAN_ARCHER = 20642;
	private static final int HARIT_LIZARDMAN_WARRIOR = 20643;
	private static final int GRAVE_WANDERER = 20659;
	private static final int ARCHER_OF_GREED = 20660;
	private static final int HATAR_RATMAN_THIEF = 20661;
	private static final int HATAR_RATMAN_BOSS = 20662;
	private static final int DEPRIVE = 20664;
	private static final int FARCRAN = 20667;
	private static final int TAIRIM = 20675;
	private static final int JUDGE_OF_MARSH = 20676;
	private static final int VANOR_SILENOS_GRUNT = 20682;
	private static final int VANOR_SILENOS_SCOUT = 20683;
	private static final int VANOR_SILENOS_WARRIOR = 20684;
	private static final int VANOR_SILENOS_CHIEFTAIN = 20686;
	private static final int BREKA_OVERLORD_HAKA = 27140;
	private static final int BREKA_OVERLORD_JAKA = 27141;
	private static final int BREKA_OVERLORD_MARKA = 27142;
	private static final int WINDSUS_ALEPH = 27143;
	private static final int TARLK_RAIDER_ATHU = 27144;
	private static final int TARLK_RAIDER_LANKA = 27145;
	private static final int TARLK_RAIDER_TRISKA = 27146;
	private static final int TARLK_RAIDER_MOTURA = 27147;
	private static final int TARLK_RAIDER_KALATH = 27148;
	private static final int GREMLIN_FILCHER = 27149;
	private static final int BLACK_LEGION_STORMTROOPER = 27150;
	private static final int LETO_SHAMAN_KETZ = 27156;
	private static final int LETO_CHIEF_NARAK = 27157;
	private static final int TIMAK_RAIDER_KAIKEE = 27158;
	private static final int TIMAK_OVERLORD_OKUN = 27159;
	private static final int GOK_MAGOK = 27160;
	private static final int TAIK_OVERLORD_KAKRAN = 27161;
	private static final int HATAR_CHIEFTAIN_KUBEL = 27162;
	private static final int VANOR_ELDER_KERUNOS = 27163;
	private static final int KARUL_CHIEF_OROOTO = 27164;
	// Misc
	private static final int MIN_LEVEL = 35;
	private static final int MAX_LEVEL = 45;
	private static final int CYBELLINS_DAGGER = 3471;
	private static final int FIRST_CIRCLE_HUNTER_LICENSE = 3692;
	private static final int SECOND_CIRCLE_HUNTER_LICENSE = 3693;
	private static final int LAUREL_LEAF_PIN = 3694;
	private static final int TEST_INSTRUCTIONS_1 = 3695;
	private static final int TEST_INSTRUCTIONS_2 = 3696;
	private static final int CYBELLINS_REQUEST = 3697;
	private static final int BLOOD_CRYSTAL_PURITY_1 = 3698;
	private static final int BLOOD_CRYSTAL_PURITY_2 = 3699;
	private static final int BLOOD_CRYSTAL_PURITY_3 = 3700;
	private static final int BLOOD_CRYSTAL_PURITY_4 = 3701;
	private static final int BLOOD_CRYSTAL_PURITY_5 = 3702;
	private static final int BLOOD_CRYSTAL_PURITY_6 = 3703;
	private static final int BLOOD_CRYSTAL_PURITY_7 = 3704;
	private static final int BLOOD_CRYSTAL_PURITY_8 = 3705;
	private static final int BLOOD_CRYSTAL_PURITY_9 = 3706;
	private static final int BLOOD_CRYSTAL_PURITY_10 = 3707;
	private static final int BROKEN_BLOOD_CRYSTAL = 3708;
	private static final int GUARDIAN_BASILISK_SCALE = 3709;
	private static final int KARUT_WEED = 3710;
	private static final int HAKAS_HEAD = 3711;
	private static final int JAKAS_HEAD = 3712;
	private static final int MARKAS_HEAD = 3713;
	private static final int WINDSUS_ALEPH_SKIN = 3714;
	private static final int INDIGO_SPIRIT_ORE = 3715;
	private static final int SPORESEA_SEED = 3716;
	private static final int TIMAK_ORC_TOTEM = 3717;
	private static final int TRISALIM_SILK = 3718;
	private static final int AMBROSIUS_FRUIT = 3719;
	private static final int BALEFIRE_CRYSTAL = 3720;
	private static final int IMPERIAL_ARROWHEAD = 3721;
	private static final int ATHUS_HEAD = 3722;
	private static final int LANKAS_HEAD = 3723;
	private static final int TRISKAS_HEAD = 3724;
	private static final int MOTURAS_HEAD = 3725;
	private static final int KALATHS_HEAD = 3726;
	private static final int FIRST_CIRCLE_REQUEST_1C = 3727;
	private static final int FIRST_CIRCLE_REQUEST_2C = 3728;
	private static final int FIRST_CIRCLE_REQUEST_3C = 3729;
	private static final int FIRST_CIRCLE_REQUEST_4C = 3730;
	private static final int FIRST_CIRCLE_REQUEST_5C = 3731;
	private static final int FIRST_CIRCLE_REQUEST_6C = 3732;
	private static final int FIRST_CIRCLE_REQUEST_7C = 3733;
	private static final int FIRST_CIRCLE_REQUEST_8C = 3734;
	private static final int FIRST_CIRCLE_REQUEST_9C = 3735;
	private static final int FIRST_CIRCLE_REQUEST_10C = 3736;
	private static final int FIRST_CIRCLE_REQUEST_11C = 3737;
	private static final int FIRST_CIRCLE_REQUEST_12C = 3738;
	private static final int FIRST_CIRCLE_REQUEST_1B = 3739;
	private static final int FIRST_CIRCLE_REQUEST_2B = 3740;
	private static final int FIRST_CIRCLE_REQUEST_3B = 3741;
	private static final int FIRST_CIRCLE_REQUEST_4B = 3742;
	private static final int FIRST_CIRCLE_REQUEST_5B = 3743;
	private static final int FIRST_CIRCLE_REQUEST_6B = 3744;
	private static final int FIRST_CIRCLE_REQUEST_1A = 3745;
	private static final int FIRST_CIRCLE_REQUEST_2A = 3746;
	private static final int FIRST_CIRCLE_REQUEST_3A = 3747;
	private static final int SECOND_CIRCLE_REQUEST_1C = 3748;
	private static final int SECOND_CIRCLE_REQUEST_2C = 3749;
	private static final int SECOND_CIRCLE_REQUEST_3C = 3750;
	private static final int SECOND_CIRCLE_REQUEST_4C = 3751;
	private static final int SECOND_CIRCLE_REQUEST_5C = 3752;
	private static final int SECOND_CIRCLE_REQUEST_6C = 3753;
	private static final int SECOND_CIRCLE_REQUEST_7C = 3754;
	private static final int SECOND_CIRCLE_REQUEST_8C = 3755;
	private static final int SECOND_CIRCLE_REQUEST_9C = 3756;
	private static final int SECOND_CIRCLE_REQUEST_10C = 3757;
	private static final int SECOND_CIRCLE_REQUEST_11C = 3758;
	private static final int SECOND_CIRCLE_REQUEST_12C = 3759;
	private static final int SECOND_CIRCLE_REQUEST_1B = 3760;
	private static final int SECOND_CIRCLE_REQUEST_2B = 3761;
	private static final int SECOND_CIRCLE_REQUEST_3B = 3762;
	private static final int SECOND_CIRCLE_REQUEST_4B = 3763;
	private static final int SECOND_CIRCLE_REQUEST_5B = 3764;
	private static final int SECOND_CIRCLE_REQUEST_6B = 3765;
	private static final int SECOND_CIRCLE_REQUEST_1A = 3766;
	private static final int SECOND_CIRCLE_REQUEST_2A = 3767;
	private static final int SECOND_CIRCLE_REQUEST_3A = 3768;
	private static final int CHARM_OF_KADESH = 3769;
	private static final int TIMAK_JADE_NECKLACE = 3770;
	private static final int ENCHANTED_GOLEM_SHARD = 3771;
	private static final int GIANT_MONSTER_EYE_MEAT = 3772;
	private static final int DIRE_WYRM_EGG = 3773;
	private static final int GUARDIAN_BASILISK_TALON = 3774;
	private static final int REVENANTS_CHAINS = 3775;
	private static final int WINDSUS_TUSK = 3776;
	private static final int GRANDISS_SKULL = 3777;
	private static final int TAIK_OBSIDIAN_AMULET = 3778;
	private static final int KARUL_BUGBEAR_HEAD = 3779;
	private static final int TAMLIN_IVORY_CHARM = 3780;
	private static final int FANG_OF_NARAK = 3781;
	private static final int ENCHANTED_GARGOYLES_HORN = 3782;
	private static final int COILED_SERPENT_TOTEM = 3783;
	private static final int TOTEM_OF_KADESH = 3784;
	private static final int KAIKIS_HEAD = 3785;
	private static final int KRONBE_VENOM_SAC = 3786;
	private static final int EVAS_CHARM = 3787;
	private static final int TITANS_TABLET = 3788;
	private static final int BOOK_OF_SHUNAIMAN = 3789;
	private static final int ROTTING_TREE_SPORES = 3790;
	private static final int TRISALIM_VENOM_SAC = 3791;
	private static final int TAIK_ORC_TOTEM = 3792;
	private static final int HARIT_BARBED_NECKLACE = 3793;
	private static final int COIN_OF_OLD_EMPIRE = 3794;
	private static final int SKIN_OF_FARCRAN = 3795;
	private static final int TEMPEST_SHARD = 3796;
	private static final int TSUNAMI_SHARD = 3797;
	private static final int SATYR_MANE = 3798;
	private static final int HAMADRYAD_SHARD = 3799;
	private static final int VANOR_SILENOS_MANE = 3800;
	private static final int TALK_BUGBEAR_TOTEM = 3801;
	private static final int OKUNS_HEAD = 3802;
	private static final int KAKRANS_HEAD = 3803;
	private static final int NARCISSUSS_SOULSTONE = 3804;
	private static final int DEPRIVE_EYE = 3805;
	private static final int UNICORNS_HORN = 3806;
	private static final int KERUNOSS_GOLD_MANE = 3807;
	private static final int SKULL_OF_EXECUTED = 3808;
	private static final int BUST_OF_TRAVIS = 3809;
	private static final int SWORD_OF_CADMUS = 3810;
	
	// Rewards
	// @formatter:off
	private static final int[][] REWARDS = {
		{ FIRST_CIRCLE_REQUEST_1C, CHARM_OF_KADESH, 40, 2090 },
		{ FIRST_CIRCLE_REQUEST_3C, ENCHANTED_GOLEM_SHARD, 50, 9480 },
		{ FIRST_CIRCLE_REQUEST_4C, GIANT_MONSTER_EYE_MEAT, 30, 9110 },
		{ FIRST_CIRCLE_REQUEST_5C, DIRE_WYRM_EGG, 40, 8690 },
		{ FIRST_CIRCLE_REQUEST_6C, GUARDIAN_BASILISK_TALON, 100, 9480 },
		{ FIRST_CIRCLE_REQUEST_7C, REVENANTS_CHAINS, 50, 11280 },
		{ FIRST_CIRCLE_REQUEST_8C, WINDSUS_TUSK, 30, 9640 },
		{ FIRST_CIRCLE_REQUEST_9C, GRANDISS_SKULL, 100, 9180 },
		{ FIRST_CIRCLE_REQUEST_10C, TAIK_OBSIDIAN_AMULET, 50, 5160 },
		{ FIRST_CIRCLE_REQUEST_11C, KARUL_BUGBEAR_HEAD, 30, 3140 },
		{ FIRST_CIRCLE_REQUEST_12C, TAMLIN_IVORY_CHARM, 40, 3160 },
		{ FIRST_CIRCLE_REQUEST_1B, FANG_OF_NARAK, 1, 6370 },
		{ FIRST_CIRCLE_REQUEST_2B, ENCHANTED_GARGOYLES_HORN, 50, 19080 },
		{ FIRST_CIRCLE_REQUEST_3B, COILED_SERPENT_TOTEM, 50, 19080 },
		{ FIRST_CIRCLE_REQUEST_4B, TOTEM_OF_KADESH, 1, 5790 },
		{ FIRST_CIRCLE_REQUEST_5B, KAIKIS_HEAD, 1, 8560 },
		{ FIRST_CIRCLE_REQUEST_6B, KRONBE_VENOM_SAC, 30, 8320 },
		{ FIRST_CIRCLE_REQUEST_1A, EVAS_CHARM, 30, 30310 },
		{ FIRST_CIRCLE_REQUEST_2A, TITANS_TABLET, 1, 27540 },
		{ FIRST_CIRCLE_REQUEST_3A, BOOK_OF_SHUNAIMAN, 1, 20560 },
		{ SECOND_CIRCLE_REQUEST_1C, ROTTING_TREE_SPORES, 40, 6850 },
		{ SECOND_CIRCLE_REQUEST_2C, TRISALIM_VENOM_SAC, 40, 7250 },
		{ SECOND_CIRCLE_REQUEST_3C, TAIK_ORC_TOTEM, 50, 7160 },
		{ SECOND_CIRCLE_REQUEST_4C, HARIT_BARBED_NECKLACE, 40, 6580 },
		{ SECOND_CIRCLE_REQUEST_5C, COIN_OF_OLD_EMPIRE, 20, 10100 },
		{ SECOND_CIRCLE_REQUEST_6C, SKIN_OF_FARCRAN, 30, 13000 },
		{ SECOND_CIRCLE_REQUEST_7C, TEMPEST_SHARD, 40, 7660 },
		{ SECOND_CIRCLE_REQUEST_8C, TSUNAMI_SHARD, 40, 7660 },
		{ SECOND_CIRCLE_REQUEST_9C, SATYR_MANE, 40, 11260 },
		{ SECOND_CIRCLE_REQUEST_10C, HAMADRYAD_SHARD, 40, 7660 },
		{ SECOND_CIRCLE_REQUEST_11C, VANOR_SILENOS_MANE, 30, 8810 },
		{ SECOND_CIRCLE_REQUEST_12C, TALK_BUGBEAR_TOTEM, 30, 7350 },
		{ SECOND_CIRCLE_REQUEST_1B, OKUNS_HEAD, 1, 8760 },
		{ SECOND_CIRCLE_REQUEST_2B, KAKRANS_HEAD, 1, 9380 },
		{ SECOND_CIRCLE_REQUEST_3B, NARCISSUSS_SOULSTONE, 40, 17820 },
		{ SECOND_CIRCLE_REQUEST_4B, DEPRIVE_EYE, 20, 17540 },
		{ SECOND_CIRCLE_REQUEST_5B, UNICORNS_HORN, 20, 14160 },
		{ SECOND_CIRCLE_REQUEST_6B, KERUNOSS_GOLD_MANE, 1, 15960 },
		{ SECOND_CIRCLE_REQUEST_1A, SKULL_OF_EXECUTED, 20, 39100 },
		{ SECOND_CIRCLE_REQUEST_2A, BUST_OF_TRAVIS, 1, 39550 },
		{ SECOND_CIRCLE_REQUEST_3A, SWORD_OF_CADMUS, 10, 41200 }
	};
	// @formatter:on
	
	// Monsters drop
	// @formatter:off
	private static final int[][] DROPLIST = {
		{ BREKA_ORC_SHAMAN, FIRST_CIRCLE_REQUEST_3B, COILED_SERPENT_TOTEM, 1, 50, 93 },
		{ BREKA_ORC_WARRIOR, FIRST_CIRCLE_REQUEST_3B, COILED_SERPENT_TOTEM, 1, 50, 100 },
		{ GUARDIAN_BASILISK, TEST_INSTRUCTIONS_1, GUARDIAN_BASILISK_SCALE, 1, 40, 90 },
		{ GUARDIAN_BASILISK, FIRST_CIRCLE_REQUEST_6C, GUARDIAN_BASILISK_TALON, (getRandom(100) < 60) ? 2 : 1, 100, 100 },
		{ FETTERED_SOUL, FIRST_CIRCLE_REQUEST_7C, REVENANTS_CHAINS, 1, 50, 100 },
		{ WINDSUS, FIRST_CIRCLE_REQUEST_8C, WINDSUS_TUSK, 1, 30, 63 },
		{ GRANDIS, FIRST_CIRCLE_REQUEST_9C, GRANDISS_SKULL, 2, 100, 100 },
		{ GIANT_FUNGUS, TEST_INSTRUCTIONS_1, SPORESEA_SEED, 1, 30, 84 },
		{ GIANT_MONSTEREYE, FIRST_CIRCLE_REQUEST_4C, GIANT_MONSTER_EYE_MEAT, 1, 30, 60 },
		{ DIRE_WYRM, FIRST_CIRCLE_REQUEST_5C, DIRE_WYRM_EGG, 1, 40, 90 },
		{ ROTTING_TREE, SECOND_CIRCLE_REQUEST_1C, ROTTING_TREE_SPORES, 1, 40, 77 },
		{ TRISALIM_SPIDER, TEST_INSTRUCTIONS_2, TRISALIM_SILK, 1, 20, 60 },
		{ TRISALIM_SPIDER, SECOND_CIRCLE_REQUEST_2C, TRISALIM_VENOM_SAC, 1, 40, 76 },
		{ TRISALIM_TARANTULA, TEST_INSTRUCTIONS_2, TRISALIM_SILK, 1, 20, 60 },
		{ TRISALIM_TARANTULA, SECOND_CIRCLE_REQUEST_2C, TRISALIM_VENOM_SAC, 1, 40, 85 },
		{ SPORE_ZOMBIE, SECOND_CIRCLE_REQUEST_2C, TRISALIM_VENOM_SAC, 1, 30, 60 },
		{ MANASHEN_GARGOYLE, TEST_INSTRUCTIONS_1, INDIGO_SPIRIT_ORE, 1, 20, 60 },
		{ ENCHANTED_STONE_GOLEM, FIRST_CIRCLE_REQUEST_3C, ENCHANTED_GOLEM_SHARD, 1, 50, 100 },
		{ ENCHANTED_STONE_GOLEM, TEST_INSTRUCTIONS_1, INDIGO_SPIRIT_ORE, 1, 20, 62 },
		{ ENCHANTED_GARGOYLE, FIRST_CIRCLE_REQUEST_2B, ENCHANTED_GARGOYLES_HORN, 1, 50, 60 },
		{ TARLK_BUGBEAR_WARRIOR, SECOND_CIRCLE_REQUEST_12C, TALK_BUGBEAR_TOTEM, 1, 30, 73 },
		{ LETO_LIZARDMAN_ARCHER, FIRST_CIRCLE_REQUEST_1C, CHARM_OF_KADESH, 1, 40, 90 },
		{ LETO_LIZARDMAN_SOLDIER, FIRST_CIRCLE_REQUEST_1C, CHARM_OF_KADESH, 1, 40, 93 },
		{ LETO_LIZARDMAN_SHAMAN, TEST_INSTRUCTIONS_1, KARUT_WEED, 1, 20, 60 },
		{ LETO_LIZARDMAN_OVERLORD, TEST_INSTRUCTIONS_1, KARUT_WEED, 1, 20, 60 },
		{ TIMAK_ORC_WARRIOR, FIRST_CIRCLE_REQUEST_2C, TIMAK_JADE_NECKLACE, 1, 50, 95 },
		{ TIMAK_ORC_WARRIOR, TEST_INSTRUCTIONS_2, TIMAK_ORC_TOTEM, 1, 20, 60 },
		{ TIMAK_ORC_OVERLORD, FIRST_CIRCLE_REQUEST_2C, TIMAK_JADE_NECKLACE, 1, 50, 100 },
		{ FLINE, SECOND_CIRCLE_REQUEST_7C, TEMPEST_SHARD, 1, 40, 59 },
		{ LIELE, SECOND_CIRCLE_REQUEST_8C, TSUNAMI_SHARD, 1, 40, 61 },
		{ VALLEY_TREANT, TEST_INSTRUCTIONS_2, AMBROSIUS_FRUIT, 1, 30, 85 },
		{ SATYR, SECOND_CIRCLE_REQUEST_9C, SATYR_MANE, 1, 40, 90 },
		{ UNICORN, SECOND_CIRCLE_REQUEST_5B, UNICORNS_HORN, 1, 20, 78 },
		{ FOREST_RUNNER, SECOND_CIRCLE_REQUEST_10C, HAMADRYAD_SHARD, 1, 40, 74 },
		{ VALLEY_TREANT_ELDER, TEST_INSTRUCTIONS_2, AMBROSIUS_FRUIT, 1, 30, 85 },
		{ SATYR_ELDER, SECOND_CIRCLE_REQUEST_9C, SATYR_MANE, 1, 40, 100 },
		{ UNICORN_ELDER, SECOND_CIRCLE_REQUEST_5B, UNICORNS_HORN, 1, 20, 96 },
		{ KARUL_BUGBEAR, FIRST_CIRCLE_REQUEST_11C, KARUL_BUGBEAR_HEAD, 1, 30, 60 },
		{ TAMLIN_ORC, FIRST_CIRCLE_REQUEST_12C, TAMLIN_IVORY_CHARM, 1, 40, 72 },
		{ TAMLIN_ORC_ARCHER, FIRST_CIRCLE_REQUEST_12C, TAMLIN_IVORY_CHARM, 1, 40, 90 },
		{ KRONBE_SPIDER, FIRST_CIRCLE_REQUEST_6B, KRONBE_VENOM_SAC, 1, 30, 60 },
		{ TAIK_ORC_ARCHER, FIRST_CIRCLE_REQUEST_10C, TAIK_OBSIDIAN_AMULET, 1, 50, 100 },
		{ TAIK_ORC_WARRIOR, FIRST_CIRCLE_REQUEST_10C, TAIK_OBSIDIAN_AMULET, 1, 50, 93 },
		{ TAIK_ORC_SHAMAN, SECOND_CIRCLE_REQUEST_3C, TAIK_ORC_TOTEM, 1, 50, 63 },
		{ TAIK_ORC_CAPTAIN, SECOND_CIRCLE_REQUEST_3C, TAIK_ORC_TOTEM, 1, 50, 99 },
		{ MIRROR, SECOND_CIRCLE_REQUEST_3B, NARCISSUSS_SOULSTONE, 1, 40, 96 },
		{ HARIT_LIZARDMAN_GRUNT, SECOND_CIRCLE_REQUEST_4C, HARIT_BARBED_NECKLACE, 1, 40, 98 },
		{ HARIT_LIZARDMAN_ARCHER, SECOND_CIRCLE_REQUEST_4C, HARIT_BARBED_NECKLACE, 1, 40, 98 },
		{ HARIT_LIZARDMAN_WARRIOR, SECOND_CIRCLE_REQUEST_4C, HARIT_BARBED_NECKLACE, 1, 40, 100 },
		{ GRAVE_WANDERER, SECOND_CIRCLE_REQUEST_1A, SKULL_OF_EXECUTED, 1, 20, 83 },
		{ ARCHER_OF_GREED, TEST_INSTRUCTIONS_2, IMPERIAL_ARROWHEAD, 1, 20, 60 },
		{ HATAR_RATMAN_THIEF, SECOND_CIRCLE_REQUEST_5C, COIN_OF_OLD_EMPIRE, 1, 20, 60 },
		{ HATAR_RATMAN_BOSS, SECOND_CIRCLE_REQUEST_5C, COIN_OF_OLD_EMPIRE, 1, 20, 62 },
		{ DEPRIVE, SECOND_CIRCLE_REQUEST_4B, DEPRIVE_EYE, 1, 20, 87 },
		{ FARCRAN, SECOND_CIRCLE_REQUEST_6C, SKIN_OF_FARCRAN, 1, 30, 100 },
		{ TAIRIM, TEST_INSTRUCTIONS_2, BALEFIRE_CRYSTAL, 1, 20, 60 },
		{ JUDGE_OF_MARSH, SECOND_CIRCLE_REQUEST_3A, SWORD_OF_CADMUS, 1, 10, 74 },
		{ VANOR_SILENOS_GRUNT, SECOND_CIRCLE_REQUEST_11C, VANOR_SILENOS_MANE, 1, 30, 80 },
		{ VANOR_SILENOS_SCOUT, SECOND_CIRCLE_REQUEST_11C, VANOR_SILENOS_MANE, 1, 30, 95 },
		{ VANOR_SILENOS_WARRIOR, SECOND_CIRCLE_REQUEST_11C, VANOR_SILENOS_MANE, 1, 30, 100 },
		{ BREKA_OVERLORD_HAKA, TEST_INSTRUCTIONS_1, HAKAS_HEAD, 1, 1, 100 },
		{ BREKA_OVERLORD_JAKA, TEST_INSTRUCTIONS_1, JAKAS_HEAD, 1, 1, 100 },
		{ BREKA_OVERLORD_MARKA, TEST_INSTRUCTIONS_1, MARKAS_HEAD, 1, 1, 100 },
		{ WINDSUS_ALEPH, TEST_INSTRUCTIONS_1, WINDSUS_ALEPH_SKIN, 1, 1, 100 },
		{ TARLK_RAIDER_ATHU, TEST_INSTRUCTIONS_2, ATHUS_HEAD, 1, 1, 100 },
		{ TARLK_RAIDER_LANKA, TEST_INSTRUCTIONS_2, LANKAS_HEAD, 1, 1, 100 },
		{ TARLK_RAIDER_TRISKA, TEST_INSTRUCTIONS_2, TRISKAS_HEAD, 1, 1, 100 },
		{ TARLK_RAIDER_MOTURA, TEST_INSTRUCTIONS_2, MOTURAS_HEAD, 1, 1, 100 },
		{ TARLK_RAIDER_KALATH, TEST_INSTRUCTIONS_2, KALATHS_HEAD, 1, 1, 100 },
		{ LETO_SHAMAN_KETZ, FIRST_CIRCLE_REQUEST_4B, TOTEM_OF_KADESH, 1, 1, 100 },
		{ LETO_CHIEF_NARAK, FIRST_CIRCLE_REQUEST_1B, FANG_OF_NARAK, 1, 1, 100 },
		{ TIMAK_RAIDER_KAIKEE, FIRST_CIRCLE_REQUEST_5B, KAIKIS_HEAD, 1, 1, 100 },
		{ TIMAK_OVERLORD_OKUN, SECOND_CIRCLE_REQUEST_1B, OKUNS_HEAD, 1, 1, 100 },
		{ GOK_MAGOK, FIRST_CIRCLE_REQUEST_2A, TITANS_TABLET, 1, 1, 100 },
		{ TAIK_OVERLORD_KAKRAN, SECOND_CIRCLE_REQUEST_2B, KAKRANS_HEAD, 1, 1, 100 },
		{ HATAR_CHIEFTAIN_KUBEL, SECOND_CIRCLE_REQUEST_2A, BUST_OF_TRAVIS, 1, 1, 100 },
		{ VANOR_ELDER_KERUNOS, SECOND_CIRCLE_REQUEST_6B, KERUNOSS_GOLD_MANE, 1, 1, 100 },
		{ KARUL_CHIEF_OROOTO, FIRST_CIRCLE_REQUEST_3A, BOOK_OF_SHUNAIMAN, 1, 1, 100 }
	};
	// @formatter:on
	
	// Links
	private static final Map<Integer, String> LINKS = new HashMap<>();
	static
	{
		LINKS.put(33520, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10a.html\">C: 40 Totems of Kadesh</a><br>");
		LINKS.put(33521, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10b.html\">C: 50 Jade Necklaces of Timak</a><br>");
		LINKS.put(33522, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10c.html\">C: 50 Enchanted Golem Shards</a><br>");
		LINKS.put(33523, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10d.html\">C: 30 Pieces Monster Eye Meat</a><br>");
		LINKS.put(33524, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10e.html\">C: 40 Eggs of Dire Wyrm</a><br>");
		LINKS.put(33525, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10f.html\">C: 100 Claws of Guardian Basilisk</a><br>");
		LINKS.put(33526, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10g.html\">C: 50 Revenant Chains </a><br>");
		LINKS.put(33527, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10h.html\">C: 30 Windsus Tusks</a><br>");
		LINKS.put(33528, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10i.html\">C: 100 Skulls of Grandis</a><br>");
		LINKS.put(33529, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10j.html\">C: 50 Taik Obsidian Amulets</a><br>");
		LINKS.put(33530, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10k.html\">C: 30 Heads of Karul Bugbear</a><br>");
		LINKS.put(33531, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-10l.html\">C: 40 Ivory Charms of Tamlin</a><br>");
		LINKS.put(33532, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-11a.html\">B: Situation Preparation - Leto Chief</a><br>");
		LINKS.put(33533, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-11b.html\">B: 50 Enchanted Gargoyle Horns</a><br>");
		LINKS.put(33534, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-11c.html\">B: 50 Coiled Serpent Totems</a><br>");
		LINKS.put(33535, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-11d.html\">B: Situation Preparation - Sorcerer Catch of Leto</a><br>");
		LINKS.put(33536, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-11e.html\">B: Situation Preparation - Timak Raider Kaikee</a><br>");
		LINKS.put(33537, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-11f.html\">B: 30 Kronbe Venom Sacs</a><br>");
		LINKS.put(33538, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-12a.html\">A: 30 Charms of Eva</a><br>");
		LINKS.put(33539, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-12b.html\">A: Titan's Tablet</a><br>");
		LINKS.put(33540, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-12c.html\">A: Book of Shunaiman</a><br>");
		LINKS.put(33541, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13a.html\">C: 40 Rotted Tree Spores</a><br>");
		LINKS.put(33542, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13b.html\">C: 40 Trisalim Venom Sacs</a><br>");
		LINKS.put(33543, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13c.html\">C: 50 Totems of Taik Orc</a><br>");
		LINKS.put(33544, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13d.html\">C: 40 Harit Barbed Necklaces</a><br>");
		LINKS.put(33545, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13e.html\">C: 20 Coins of Ancient Empire</a><br>");
		LINKS.put(33546, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13f.html\">C: 30 Skins of Farkran</a><br>");
		LINKS.put(33547, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13g.html\">C: 40 Tempest Shards</a><br>");
		LINKS.put(33548, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13k.html\">C: 30 Vanor Silenos Manes</a><br>");
		LINKS.put(33549, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13i.html\">C: 40 Manes of Pan Ruem</a><br>");
		LINKS.put(33550, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13j.html\">C: hamadryad shards</a><br>");
		LINKS.put(33551, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13k.html\">C: 30 Manes of Vanor Silenos</a><br>");
		LINKS.put(33552, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-13l.html\">C: 30 Totems of Talk Bugbears</a><br>");
		LINKS.put(33553, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-14a.html\">B: Situation Preparation - Overlord Okun of Timak</a><br>");
		LINKS.put(33554, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-14b.html\">B: Situation Preparation - Overlord Kakran of Taik</a><br>");
		LINKS.put(33555, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-14c.html\">B: 40 Narcissus Soulstones</a><br>");
		LINKS.put(33556, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-14d.html\">B: 20 Eyes of Deprived</a><br>");
		LINKS.put(33557, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-14e.html\">B: 20 Unicorn Horns</a><br>");
		LINKS.put(33558, "<a action=\"bypass -h Quest Q00335_TheSongOfTheHunter 30745-14f.html\">B: Kerunos's Gold Mane</a><br>");
	}
	
	public Q00335_TheSongOfTheHunter()
	{
		super(335);
		addStartNpc(GREY);
		addTalkId(GREY, TOR, CYBELLIN);
		addKillId(BREKA_ORC_SHAMAN, BREKA_ORC_WARRIOR, GUARDIAN_BASILISK, FETTERED_SOUL, WINDSUS, GRANDIS, GIANT_FUNGUS, GIANT_MONSTEREYE, DIRE_WYRM, ROTTING_TREE, TRISALIM_SPIDER, TRISALIM_TARANTULA, SPORE_ZOMBIE, MANASHEN_GARGOYLE, ENCHANTED_STONE_GOLEM, ENCHANTED_GARGOYLE, TARLK_BUGBEAR_WARRIOR, LETO_LIZARDMAN_ARCHER, LETO_LIZARDMAN_SOLDIER, LETO_LIZARDMAN_SHAMAN, LETO_LIZARDMAN_OVERLORD, TIMAK_ORC_WARRIOR, TIMAK_ORC_OVERLORD, FLINE, LIELE, VALLEY_TREANT, SATYR, UNICORN, FOREST_RUNNER, VALLEY_TREANT_ELDER, SATYR_ELDER, UNICORN_ELDER, KARUL_BUGBEAR, TAMLIN_ORC, TAMLIN_ORC_ARCHER, KRONBE_SPIDER, TAIK_ORC_ARCHER, TAIK_ORC_WARRIOR, TAIK_ORC_SHAMAN, TAIK_ORC_CAPTAIN, MIRROR, HARIT_LIZARDMAN_GRUNT, HARIT_LIZARDMAN_ARCHER, HARIT_LIZARDMAN_WARRIOR, GRAVE_WANDERER, ARCHER_OF_GREED, HATAR_RATMAN_THIEF, HATAR_RATMAN_BOSS, DEPRIVE, FARCRAN, TAIRIM, JUDGE_OF_MARSH, VANOR_SILENOS_GRUNT, VANOR_SILENOS_SCOUT, VANOR_SILENOS_WARRIOR, BREKA_OVERLORD_HAKA, BREKA_OVERLORD_JAKA, BREKA_OVERLORD_MARKA, WINDSUS_ALEPH, TARLK_RAIDER_ATHU, TARLK_RAIDER_LANKA, TARLK_RAIDER_TRISKA, TARLK_RAIDER_MOTURA, TARLK_RAIDER_KALATH, GREMLIN_FILCHER, LETO_SHAMAN_KETZ, LETO_CHIEF_NARAK, TIMAK_RAIDER_KAIKEE, TIMAK_OVERLORD_OKUN, GOK_MAGOK, TAIK_OVERLORD_KAKRAN, HATAR_CHIEFTAIN_KUBEL, VANOR_ELDER_KERUNOS, KARUL_CHIEF_OROOTO, VANOR_SILENOS_CHIEFTAIN);
		registerQuestItems(CYBELLINS_DAGGER, FIRST_CIRCLE_HUNTER_LICENSE, SECOND_CIRCLE_HUNTER_LICENSE, LAUREL_LEAF_PIN, TEST_INSTRUCTIONS_1, TEST_INSTRUCTIONS_2, CYBELLINS_REQUEST, BLOOD_CRYSTAL_PURITY_1, BLOOD_CRYSTAL_PURITY_2, BLOOD_CRYSTAL_PURITY_3, BLOOD_CRYSTAL_PURITY_4, BLOOD_CRYSTAL_PURITY_5, BLOOD_CRYSTAL_PURITY_6, BLOOD_CRYSTAL_PURITY_7, BLOOD_CRYSTAL_PURITY_8, BLOOD_CRYSTAL_PURITY_9, BLOOD_CRYSTAL_PURITY_10, BROKEN_BLOOD_CRYSTAL, GUARDIAN_BASILISK_SCALE, KARUT_WEED, HAKAS_HEAD, JAKAS_HEAD, MARKAS_HEAD, WINDSUS_ALEPH_SKIN, INDIGO_SPIRIT_ORE, SPORESEA_SEED, TIMAK_ORC_TOTEM, TRISALIM_SILK, AMBROSIUS_FRUIT, BALEFIRE_CRYSTAL, IMPERIAL_ARROWHEAD, ATHUS_HEAD, LANKAS_HEAD, TRISKAS_HEAD, MOTURAS_HEAD, KALATHS_HEAD, FIRST_CIRCLE_REQUEST_1C, FIRST_CIRCLE_REQUEST_2C, FIRST_CIRCLE_REQUEST_3C, FIRST_CIRCLE_REQUEST_4C, FIRST_CIRCLE_REQUEST_5C, FIRST_CIRCLE_REQUEST_6C, FIRST_CIRCLE_REQUEST_7C, FIRST_CIRCLE_REQUEST_8C, FIRST_CIRCLE_REQUEST_9C, FIRST_CIRCLE_REQUEST_10C, FIRST_CIRCLE_REQUEST_11C, FIRST_CIRCLE_REQUEST_12C, FIRST_CIRCLE_REQUEST_1B, FIRST_CIRCLE_REQUEST_2B, FIRST_CIRCLE_REQUEST_3B, FIRST_CIRCLE_REQUEST_4B, FIRST_CIRCLE_REQUEST_5B, FIRST_CIRCLE_REQUEST_6B, FIRST_CIRCLE_REQUEST_1A, FIRST_CIRCLE_REQUEST_2A, FIRST_CIRCLE_REQUEST_3A, SECOND_CIRCLE_REQUEST_1C, SECOND_CIRCLE_REQUEST_2C, SECOND_CIRCLE_REQUEST_3C, SECOND_CIRCLE_REQUEST_4C, SECOND_CIRCLE_REQUEST_5C, SECOND_CIRCLE_REQUEST_6C, SECOND_CIRCLE_REQUEST_7C, SECOND_CIRCLE_REQUEST_8C, SECOND_CIRCLE_REQUEST_9C, SECOND_CIRCLE_REQUEST_10C, SECOND_CIRCLE_REQUEST_11C, SECOND_CIRCLE_REQUEST_12C, SECOND_CIRCLE_REQUEST_1B, SECOND_CIRCLE_REQUEST_2B, SECOND_CIRCLE_REQUEST_3B, SECOND_CIRCLE_REQUEST_4B, SECOND_CIRCLE_REQUEST_5B, SECOND_CIRCLE_REQUEST_6B, SECOND_CIRCLE_REQUEST_1A, SECOND_CIRCLE_REQUEST_2A, SECOND_CIRCLE_REQUEST_3A, CHARM_OF_KADESH, TIMAK_JADE_NECKLACE, ENCHANTED_GOLEM_SHARD, GIANT_MONSTER_EYE_MEAT, DIRE_WYRM_EGG, GUARDIAN_BASILISK_TALON, REVENANTS_CHAINS, WINDSUS_TUSK, GRANDISS_SKULL, TAIK_OBSIDIAN_AMULET, KARUL_BUGBEAR_HEAD, TAMLIN_IVORY_CHARM, FANG_OF_NARAK, ENCHANTED_GARGOYLES_HORN, COILED_SERPENT_TOTEM, TOTEM_OF_KADESH, KAIKIS_HEAD, KRONBE_VENOM_SAC, EVAS_CHARM, TITANS_TABLET, BOOK_OF_SHUNAIMAN, ROTTING_TREE_SPORES, TRISALIM_VENOM_SAC, TAIK_ORC_TOTEM, HARIT_BARBED_NECKLACE, COIN_OF_OLD_EMPIRE, SKIN_OF_FARCRAN, TEMPEST_SHARD, TSUNAMI_SHARD, SATYR_MANE, HAMADRYAD_SHARD, VANOR_SILENOS_MANE, TALK_BUGBEAR_TOTEM, OKUNS_HEAD, KAKRANS_HEAD, NARCISSUSS_SOULSTONE, DEPRIVE_EYE, UNICORNS_HORN, KERUNOSS_GOLD_MANE, SKULL_OF_EXECUTED, BUST_OF_TRAVIS, SWORD_OF_CADMUS);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			htmltext = player.getLevel() < MIN_LEVEL ? "30744-01.htm" : "30744-02.htm";
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case GREY:
				{
					if (hasQuestItems(player, TEST_INSTRUCTIONS_1))
					{
						final long count = Arrays.asList(getQuestItemsCount(player, GUARDIAN_BASILISK_SCALE) >= 40, getQuestItemsCount(player, KARUT_WEED) >= 20, (getQuestItemsCount(player, HAKAS_HEAD) + getQuestItemsCount(player, JAKAS_HEAD) + getQuestItemsCount(player, MARKAS_HEAD)) >= 3, hasQuestItems(player, WINDSUS_ALEPH_SKIN), getQuestItemsCount(player, INDIGO_SPIRIT_ORE) >= 20, getQuestItemsCount(player, SPORESEA_SEED) >= 30).stream().filter(b -> b).count();
						if (count < 3)
						{
							htmltext = "30744-05.html";
						}
						else
						{
							qs.setCond(2, true);
							giveItems(player, FIRST_CIRCLE_HUNTER_LICENSE, 1);
							takeItems(player, GUARDIAN_BASILISK_SCALE, -1);
							takeItems(player, KARUT_WEED, -1);
							takeItems(player, HAKAS_HEAD, -1);
							takeItems(player, JAKAS_HEAD, -1);
							takeItems(player, MARKAS_HEAD, -1);
							takeItems(player, WINDSUS_ALEPH_SKIN, -1);
							takeItems(player, INDIGO_SPIRIT_ORE, -1);
							takeItems(player, SPORESEA_SEED, -1);
							takeItems(player, TEST_INSTRUCTIONS_1, -1);
							htmltext = "30744-06.html";
						}
					}
					
					if (hasQuestItems(player, FIRST_CIRCLE_HUNTER_LICENSE))
					{
						if (player.getLevel() < MAX_LEVEL)
						{
							htmltext = "30744-07.html";
						}
						else if (!hasQuestItems(player, TEST_INSTRUCTIONS_2))
						{
							htmltext = "30744-08.html";
						}
					}
					
					if (hasQuestItems(player, TEST_INSTRUCTIONS_2))
					{
						final long count = Arrays.asList(getQuestItemsCount(player, TIMAK_ORC_TOTEM) >= 20, getQuestItemsCount(player, TRISALIM_SILK) >= 20, getQuestItemsCount(player, AMBROSIUS_FRUIT) >= 30, getQuestItemsCount(player, BALEFIRE_CRYSTAL) >= 20, getQuestItemsCount(player, IMPERIAL_ARROWHEAD) >= 20, ((getQuestItemsCount(player, ATHUS_HEAD) + getQuestItemsCount(player, LANKAS_HEAD) + getQuestItemsCount(player, TRISKAS_HEAD) + getQuestItemsCount(player, MOTURAS_HEAD) + getQuestItemsCount(player, KALATHS_HEAD)) >= 5)).stream().filter(b -> b).count();
						if (count < 3)
						{
							htmltext = "30744-11.html";
						}
						else
						{
							qs.setCond(3, true);
							giveItems(player, SECOND_CIRCLE_HUNTER_LICENSE, 1);
							takeItems(player, TRISALIM_SILK, -1);
							takeItems(player, TIMAK_ORC_TOTEM, -1);
							takeItems(player, AMBROSIUS_FRUIT, -1);
							takeItems(player, BALEFIRE_CRYSTAL, -1);
							takeItems(player, IMPERIAL_ARROWHEAD, -1);
							takeItems(player, ATHUS_HEAD, -1);
							takeItems(player, LANKAS_HEAD, -1);
							takeItems(player, TRISKAS_HEAD, -1);
							takeItems(player, MOTURAS_HEAD, -1);
							takeItems(player, KALATHS_HEAD, -1);
							takeItems(player, TEST_INSTRUCTIONS_2, -1);
							takeItems(player, FIRST_CIRCLE_HUNTER_LICENSE, -1);
							htmltext = "30744-12.html";
						}
					}
					
					if (hasQuestItems(player, SECOND_CIRCLE_HUNTER_LICENSE))
					{
						htmltext = "30744-14.html";
					}
					break;
				}
				case CYBELLIN:
				{
					if (!hasQuestItems(player, SECOND_CIRCLE_HUNTER_LICENSE) && !hasQuestItems(player, FIRST_CIRCLE_HUNTER_LICENSE))
					{
						htmltext = "30746-01.html";
					}
					else if (!hasQuestItems(player, CYBELLINS_REQUEST))
					{
						htmltext = "30746-02.html";
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_1))
					{
						htmltext = "30746-04.html";
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_2) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_3) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_4) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_5) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_6) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_7) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_8) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_9))
					{
						htmltext = "30746-05.html";
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_10))
					{
						giveAdena(player, 870400, true);
						takeItems(player, BLOOD_CRYSTAL_PURITY_10, -1);
						htmltext = "30746-05a.html";
					}
					else if (!hasQuestItems(player, BROKEN_BLOOD_CRYSTAL))
					{
						htmltext = "30746-08.html";
					}
					else
					{
						takeItems(player, BROKEN_BLOOD_CRYSTAL, -1);
						htmltext = "30746-09.html";
					}
					break;
				}
				case TOR:
				{
					if (!hasQuestItems(player, SECOND_CIRCLE_HUNTER_LICENSE) && !hasQuestItems(player, FIRST_CIRCLE_HUNTER_LICENSE))
					{
						htmltext = "30745-01a.html";
					}
					else
					{
						final long requestCount = getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_1C, FIRST_CIRCLE_REQUEST_2C, FIRST_CIRCLE_REQUEST_3C, FIRST_CIRCLE_REQUEST_4C, FIRST_CIRCLE_REQUEST_5C, FIRST_CIRCLE_REQUEST_6C, FIRST_CIRCLE_REQUEST_7C, FIRST_CIRCLE_REQUEST_8C, FIRST_CIRCLE_REQUEST_9C, FIRST_CIRCLE_REQUEST_10C, FIRST_CIRCLE_REQUEST_11C, FIRST_CIRCLE_REQUEST_12C, FIRST_CIRCLE_REQUEST_1B, FIRST_CIRCLE_REQUEST_2B, FIRST_CIRCLE_REQUEST_3B, FIRST_CIRCLE_REQUEST_4B, FIRST_CIRCLE_REQUEST_5B, FIRST_CIRCLE_REQUEST_6B, FIRST_CIRCLE_REQUEST_1A, FIRST_CIRCLE_REQUEST_2A, FIRST_CIRCLE_REQUEST_3A, SECOND_CIRCLE_REQUEST_1C, SECOND_CIRCLE_REQUEST_2C, SECOND_CIRCLE_REQUEST_3C, SECOND_CIRCLE_REQUEST_4C, SECOND_CIRCLE_REQUEST_5C, SECOND_CIRCLE_REQUEST_6C, SECOND_CIRCLE_REQUEST_7C, SECOND_CIRCLE_REQUEST_8C, SECOND_CIRCLE_REQUEST_9C, SECOND_CIRCLE_REQUEST_10C, SECOND_CIRCLE_REQUEST_11C, SECOND_CIRCLE_REQUEST_12C, SECOND_CIRCLE_REQUEST_1B, SECOND_CIRCLE_REQUEST_2B, SECOND_CIRCLE_REQUEST_3B, SECOND_CIRCLE_REQUEST_4B, SECOND_CIRCLE_REQUEST_5B, SECOND_CIRCLE_REQUEST_6B, SECOND_CIRCLE_REQUEST_1A, SECOND_CIRCLE_REQUEST_2A, SECOND_CIRCLE_REQUEST_3A);
						if (hasQuestItems(player, FIRST_CIRCLE_HUNTER_LICENSE))
						{
							if (requestCount == 0)
							{
								if (player.getLevel() < MAX_LEVEL)
								{
									htmltext = "30745-01b.html";
								}
								else
								{
									if (hasQuestItems(player, SECOND_CIRCLE_HUNTER_LICENSE))
									{
										htmltext = "30745-03.html";
									}
									else
									{
										htmltext = "30745-03a.html";
									}
								}
							}
							else
							{
								htmltext = reward(player, qs, REWARDS);
							}
						}
						else if (hasQuestItems(player, SECOND_CIRCLE_HUNTER_LICENSE))
						{
							if (requestCount == 0)
							{
								htmltext = "30745-03b.html";
							}
							else
							{
								htmltext = reward(player, qs, REWARDS);
							}
						}
					}
					break;
				}
			}
		}
		return htmltext;
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
			case "30744-03.htm":
			{
				qs.startQuest();
				if (!hasQuestItems(player, TEST_INSTRUCTIONS_1))
				{
					giveItems(player, TEST_INSTRUCTIONS_1, 1);
				}
				qs.setMemoState(0);
				htmltext = event;
				break;
			}
			case "30744-04.html":
			case "30744-04a.html":
			case "30744-04b.html":
			case "30744-04c.html":
			case "30744-04d.html":
			case "30744-04e.html":
			case "30744-04f.html":
			case "30744-07.html":
			case "30744-07a.html":
			case "30744-07b.html":
			case "30744-08.html":
			case "30744-08a.html":
			case "30744-10.html":
			case "30744-10a.html":
			case "30744-10b.html":
			case "30744-10c.html":
			case "30744-10d.html":
			case "30744-10e.html":
			case "30744-10f.html":
			case "30744-14.html":
			case "30744-14a.html":
			case "30744-15.html":
			case "30744-18.html":
			case "30745-09.html":
			case "30746-03a.html":
			case "30746-07.html":
			case "30745-04.html":
			case "30745-05a.html":
			case "30745-05c.html":
			{
				htmltext = event;
				break;
			}
			case "30744-09.html":
			{
				if ((getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_1C) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_2C) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_3C) + //
					getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_4C) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_4C) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_6C) + //
					getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_7C) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_8C) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_9C) + //
					getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_10C) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_11C) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_12C) + //
					getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_1B) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_2B) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_3B) + //
					getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_4B) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_5B) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_6B) + //
					getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_1A) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_2A) + getQuestItemsCount(player, FIRST_CIRCLE_REQUEST_3A)) == 0)
				{
					giveItems(player, TEST_INSTRUCTIONS_2, 1);
					htmltext = event;
				}
				else
				{
					htmltext = "30744-09a.html";
				}
				break;
			}
			case "30744-16.html":
			{
				qs.exitQuest(true, true);
				
				takeItems(player, -1, FIRST_CIRCLE_HUNTER_LICENSE, SECOND_CIRCLE_HUNTER_LICENSE, LAUREL_LEAF_PIN, TEST_INSTRUCTIONS_1, TEST_INSTRUCTIONS_2, CYBELLINS_REQUEST, BLOOD_CRYSTAL_PURITY_1, BLOOD_CRYSTAL_PURITY_2, BLOOD_CRYSTAL_PURITY_3, BLOOD_CRYSTAL_PURITY_4, BLOOD_CRYSTAL_PURITY_5, BLOOD_CRYSTAL_PURITY_6, BLOOD_CRYSTAL_PURITY_7, BLOOD_CRYSTAL_PURITY_8, BLOOD_CRYSTAL_PURITY_9, BLOOD_CRYSTAL_PURITY_10, BROKEN_BLOOD_CRYSTAL, CYBELLINS_DAGGER, GUARDIAN_BASILISK_SCALE, KARUT_WEED, HAKAS_HEAD, JAKAS_HEAD, MARKAS_HEAD, WINDSUS_ALEPH_SKIN, INDIGO_SPIRIT_ORE, SPORESEA_SEED, TIMAK_ORC_TOTEM, TRISALIM_SILK, AMBROSIUS_FRUIT, BALEFIRE_CRYSTAL, IMPERIAL_ARROWHEAD, ATHUS_HEAD, LANKAS_HEAD, TRISKAS_HEAD, MOTURAS_HEAD, KALATHS_HEAD, FIRST_CIRCLE_REQUEST_1C, FIRST_CIRCLE_REQUEST_2C, FIRST_CIRCLE_REQUEST_3C, FIRST_CIRCLE_REQUEST_4C, FIRST_CIRCLE_REQUEST_5C, FIRST_CIRCLE_REQUEST_6C, FIRST_CIRCLE_REQUEST_7C, FIRST_CIRCLE_REQUEST_8C, FIRST_CIRCLE_REQUEST_9C, FIRST_CIRCLE_REQUEST_10C, FIRST_CIRCLE_REQUEST_11C, FIRST_CIRCLE_REQUEST_12C, FIRST_CIRCLE_REQUEST_1B, FIRST_CIRCLE_REQUEST_2B, FIRST_CIRCLE_REQUEST_3B, FIRST_CIRCLE_REQUEST_4B, FIRST_CIRCLE_REQUEST_5B, FIRST_CIRCLE_REQUEST_6B, FIRST_CIRCLE_REQUEST_1A, FIRST_CIRCLE_REQUEST_2A, FIRST_CIRCLE_REQUEST_3A, SECOND_CIRCLE_REQUEST_1C, SECOND_CIRCLE_REQUEST_2C, SECOND_CIRCLE_REQUEST_3C, SECOND_CIRCLE_REQUEST_4C, SECOND_CIRCLE_REQUEST_5C, SECOND_CIRCLE_REQUEST_6C, SECOND_CIRCLE_REQUEST_7C, SECOND_CIRCLE_REQUEST_8C, SECOND_CIRCLE_REQUEST_9C, SECOND_CIRCLE_REQUEST_10C, SECOND_CIRCLE_REQUEST_11C, SECOND_CIRCLE_REQUEST_12C, SECOND_CIRCLE_REQUEST_1B, SECOND_CIRCLE_REQUEST_2B, SECOND_CIRCLE_REQUEST_3B, SECOND_CIRCLE_REQUEST_4B, SECOND_CIRCLE_REQUEST_5B, SECOND_CIRCLE_REQUEST_6B, SECOND_CIRCLE_REQUEST_1A, SECOND_CIRCLE_REQUEST_2A, SECOND_CIRCLE_REQUEST_3A, CHARM_OF_KADESH, TIMAK_JADE_NECKLACE, ENCHANTED_GOLEM_SHARD, GIANT_MONSTER_EYE_MEAT, DIRE_WYRM_EGG, GUARDIAN_BASILISK_TALON, REVENANTS_CHAINS, WINDSUS_TUSK, GRANDISS_SKULL, TAIK_OBSIDIAN_AMULET, KARUL_BUGBEAR_HEAD, TAMLIN_IVORY_CHARM, FANG_OF_NARAK, ENCHANTED_GARGOYLES_HORN, COILED_SERPENT_TOTEM, TOTEM_OF_KADESH, KAIKIS_HEAD, KRONBE_VENOM_SAC, EVAS_CHARM, TITANS_TABLET, BOOK_OF_SHUNAIMAN, ROTTING_TREE_SPORES, TRISALIM_VENOM_SAC, TAIK_ORC_TOTEM, HARIT_BARBED_NECKLACE, COIN_OF_OLD_EMPIRE, SKIN_OF_FARCRAN, TEMPEST_SHARD, TSUNAMI_SHARD, SATYR_MANE, HAMADRYAD_SHARD, VANOR_SILENOS_MANE, TALK_BUGBEAR_TOTEM, OKUNS_HEAD, KAKRANS_HEAD, NARCISSUSS_SOULSTONE, DEPRIVE_EYE, UNICORNS_HORN, KERUNOSS_GOLD_MANE, SKULL_OF_EXECUTED, BUST_OF_TRAVIS, SWORD_OF_CADMUS);
				
				// TODO(Zoey76): This is dead code.
				if (getQuestItemsCount(player, LAUREL_LEAF_PIN) < 20)
				{
					htmltext = event;
				}
				else
				{
					giveAdena(player, 20000, true);
					htmltext = "30744-17.html";
				}
				break;
			}
			case "30745-02.html":
			{
				if (!hasQuestItems(player, TEST_INSTRUCTIONS_2))
				{
					htmltext = event;
				}
				else
				{
					htmltext = "30745-03.html";
				}
				break;
			}
			case "LIST_1":
			{
				int i0 = 0;
				int i1 = 0;
				int i2 = 0;
				int i3 = 0;
				int i4 = 0;
				int i5 = 0;
				
				if (qs.isMemoState(0))
				{
					while ((i0 == i1) || (i1 == i2) || (i2 == i3) || (i3 == i4) || (i0 == i4) || (i0 == i2) || (i0 == i3) || (i1 == i3) || (i1 == i4) || (i2 == i4))
					{
						if (!hasQuestItems(player, LAUREL_LEAF_PIN))
						{
							i0 = getRandom(12);
							i1 = getRandom(12);
							i2 = getRandom(12);
							i3 = getRandom(12);
							i4 = getRandom(12);
							qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
						}
						else if (getQuestItemsCount(player, LAUREL_LEAF_PIN) < 4)
						{
							if (getRandom(100) < 20)
							{
								i0 = getRandom(6) + 12;
								i1 = getRandom(12);
								i2 = getRandom(6);
								i3 = getRandom(6) + 6;
								i4 = getRandom(12);
								qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
							}
							else
							{
								i0 = getRandom(12);
								i1 = getRandom(12);
								i2 = getRandom(12);
								i3 = getRandom(12);
								i4 = getRandom(12);
								qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
							}
						}
						else if (getRandom(100) < 20)
						{
							i0 = getRandom(6) + 12;
							if (getRandom(20) == 0)
							{
								i1 = getRandom(2) + 18;
							}
							else
							{
								i1 = getRandom(12);
							}
							i2 = getRandom(6);
							i3 = getRandom(6) + 6;
							i4 = getRandom(12);
							qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
						}
						else
						{
							i0 = getRandom(12);
							if (getRandom(20) == 0)
							{
								i1 = getRandom(2) + 18;
							}
							else
							{
								i1 = getRandom(12);
							}
							i2 = getRandom(6);
							i3 = getRandom(6) + 6;
							i4 = getRandom(12);
							qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
						}
					}
					
					i0 += 33520;
					i1 += 33520;
					i2 += 33520;
					i3 += 33520;
					i4 += 33520;
					
					htmltext = getHtml(player, "30745-16.html", i0, i1, i2, i3, i4);
				}
				else
				{
					i5 = qs.getMemoState();
					i0 = i5 % 32;
					i5 /= 32;
					i1 = i5 % 32;
					i5 /= 32;
					i2 = i5 % 32;
					i5 /= 32;
					i3 = i5 % 32;
					i5 /= 32;
					i4 = i5 % 32;
					i5 /= 32;
					i0 += 33520;
					i1 += 33520;
					i2 += 33520;
					i3 += 33520;
					i4 += 33520;
					
					htmltext = getHtml(player, "30745-16.html", i4, i3, i2, i1, i0);
				}
				break;
			}
			case "LIST_2":
			{
				int i0 = 0;
				int i1 = 0;
				int i2 = 0;
				int i3 = 0;
				int i4 = 0;
				int i5 = 0;
				
				if (qs.isMemoState(0))
				{
					while ((i0 == i1) || (i1 == i2) || (i2 == i3) || (i3 == i4) || (i0 == i4) || (i0 == i2) || (i0 == i3) || (i1 == i3) || (i1 == i4) || (i2 == i4))
					{
						if (!hasQuestItems(player, LAUREL_LEAF_PIN))
						{
							i0 = getRandom(10);
							i1 = getRandom(10);
							i2 = getRandom(5);
							i3 = getRandom(5) + 5;
							i4 = getRandom(10);
							qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
						}
						else if (getQuestItemsCount(player, LAUREL_LEAF_PIN) < 4)
						{
							if (getRandom(100) < 20)
							{
								i0 = getRandom(6) + 10;
								i1 = getRandom(10);
								i2 = getRandom(5);
								i3 = getRandom(5) + 5;
								i4 = getRandom(10);
								qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
							}
							else
							{
								i0 = getRandom(10);
								i1 = getRandom(10);
								i2 = getRandom(5);
								i3 = getRandom(5) + 5;
								i4 = getRandom(10);
								qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
							}
						}
						else if (getRandom(100) < 20)
						{
							i0 = getRandom(6) + 10;
							if (getRandom(20) == 0)
							{
								i1 = getRandom(3) + 16;
							}
							else
							{
								i1 = getRandom(10);
							}
							i2 = getRandom(5);
							i3 = getRandom(5) + 5;
							i4 = getRandom(10);
							qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
						}
						else
						{
							i0 = getRandom(10);
							if (getRandom(20) == 0)
							{
								i1 = getRandom(3) + 16;
							}
							else
							{
								i1 = getRandom(10);
							}
							i2 = getRandom(5);
							i3 = getRandom(5) + 5;
							i4 = getRandom(10);
							qs.setMemoState((i0 * 32 * 32 * 32 * 32) + (i1 * 32 * 32 * 32) + (i2 * 32 * 32) + (i3 * 32 * 1) + (i4 * 1 * 1));
						}
					}
					
					i0 = 33520 + (i0 + 20);
					i1 = 33520 + (i1 + 20);
					i2 = 33520 + (i2 + 20);
					i3 = 33520 + (i3 + 20);
					i4 = 33520 + (i4 + 20);
					
					htmltext = getHtml(player, "30745-16.html", i0, i1, i2, i3, i4);
				}
				else
				{
					i5 = qs.getMemoState();
					i0 = i5 % 32;
					i5 /= 32;
					i1 = i5 % 32;
					i5 /= 32;
					i2 = i5 % 32;
					i5 /= 32;
					i3 = i5 % 32;
					i5 /= 32;
					i4 = i5 % 32;
					i5 /= 32;
					i0 = 33520 + (i0 + 20);
					i1 = 33520 + (i1 + 20);
					i2 = 33520 + (i2 + 20);
					i3 = 33520 + (i3 + 20);
					i4 = 33520 + (i4 + 20);
					
					htmltext = getHtml(player, "30745-16.html", i4, i3, i2, i1, i0);
				}
				break;
			}
			case "30746-03.html":
			{
				if (!hasQuestItems(player, CYBELLINS_DAGGER))
				{
					giveItems(player, CYBELLINS_DAGGER, 1);
				}
				if (getQuestItemsCount(player, CYBELLINS_REQUEST) == 0)
				{
					giveItems(player, CYBELLINS_REQUEST, 1);
				}
				giveItems(player, BLOOD_CRYSTAL_PURITY_1, 1);
				if (hasQuestItems(player, BROKEN_BLOOD_CRYSTAL))
				{
					takeItems(player, BROKEN_BLOOD_CRYSTAL, -1);
				}
				htmltext = event;
				break;
			}
			case "30746-06.html":
			{
				if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_2))
				{
					giveAdena(player, 3400, true);
				}
				else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_3))
				{
					giveAdena(player, 6800, true);
				}
				else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_4))
				{
					giveAdena(player, 13600, true);
				}
				else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_5))
				{
					giveAdena(player, 27200, true);
				}
				else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_6))
				{
					giveAdena(player, 54400, true);
				}
				else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_7))
				{
					giveAdena(player, 108800, true);
				}
				else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_8))
				{
					giveAdena(player, 217600, true);
				}
				else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_9))
				{
					giveAdena(player, 435200, true);
				}
				else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_10))
				{
					giveAdena(player, 870400, true);
				}
				takeItems(player, BLOOD_CRYSTAL_PURITY_2, -1);
				takeItems(player, BLOOD_CRYSTAL_PURITY_3, -1);
				takeItems(player, BLOOD_CRYSTAL_PURITY_4, -1);
				takeItems(player, BLOOD_CRYSTAL_PURITY_5, -1);
				takeItems(player, BLOOD_CRYSTAL_PURITY_6, -1);
				takeItems(player, BLOOD_CRYSTAL_PURITY_7, -1);
				takeItems(player, BLOOD_CRYSTAL_PURITY_8, -1);
				takeItems(player, BLOOD_CRYSTAL_PURITY_9, -1);
				takeItems(player, BLOOD_CRYSTAL_PURITY_10, -1);
				htmltext = event;
				break;
			}
			case "30746-10.html":
			{
				takeItems(player, BLOOD_CRYSTAL_PURITY_1, -1);
				takeItems(player, CYBELLINS_DAGGER, -1);
				takeItems(player, CYBELLINS_REQUEST, -1);
				htmltext = event;
				break;
			}
			case "30745-05b.html":
			{
				htmltext = event;
				if (hasQuestItems(player, LAUREL_LEAF_PIN))
				{
					takeItems(player, LAUREL_LEAF_PIN, 1);
				}
				
				takeItems(player, -1, FIRST_CIRCLE_REQUEST_1C, FIRST_CIRCLE_REQUEST_2C, FIRST_CIRCLE_REQUEST_3C, FIRST_CIRCLE_REQUEST_4C, FIRST_CIRCLE_REQUEST_5C, FIRST_CIRCLE_REQUEST_6C, FIRST_CIRCLE_REQUEST_7C, FIRST_CIRCLE_REQUEST_8C, FIRST_CIRCLE_REQUEST_9C, FIRST_CIRCLE_REQUEST_10C, FIRST_CIRCLE_REQUEST_11C, FIRST_CIRCLE_REQUEST_12C);
				takeItems(player, -1, FIRST_CIRCLE_REQUEST_1B, FIRST_CIRCLE_REQUEST_2B, FIRST_CIRCLE_REQUEST_3B, FIRST_CIRCLE_REQUEST_4B, FIRST_CIRCLE_REQUEST_5B, FIRST_CIRCLE_REQUEST_6B);
				takeItems(player, -1, FIRST_CIRCLE_REQUEST_1A, FIRST_CIRCLE_REQUEST_2A, FIRST_CIRCLE_REQUEST_3A);
				takeItems(player, -1, SECOND_CIRCLE_REQUEST_1C, SECOND_CIRCLE_REQUEST_2C, SECOND_CIRCLE_REQUEST_3C, SECOND_CIRCLE_REQUEST_4C, SECOND_CIRCLE_REQUEST_5C, SECOND_CIRCLE_REQUEST_6C, SECOND_CIRCLE_REQUEST_7C, SECOND_CIRCLE_REQUEST_8C, SECOND_CIRCLE_REQUEST_9C, SECOND_CIRCLE_REQUEST_10C, SECOND_CIRCLE_REQUEST_11C, SECOND_CIRCLE_REQUEST_12C);
				takeItems(player, -1, SECOND_CIRCLE_REQUEST_1B, SECOND_CIRCLE_REQUEST_2B, SECOND_CIRCLE_REQUEST_3B, SECOND_CIRCLE_REQUEST_4B, SECOND_CIRCLE_REQUEST_5B, SECOND_CIRCLE_REQUEST_6B);
				takeItems(player, -1, SECOND_CIRCLE_REQUEST_1A, SECOND_CIRCLE_REQUEST_2A, SECOND_CIRCLE_REQUEST_3A);
				takeItems(player, -1, CHARM_OF_KADESH, TIMAK_JADE_NECKLACE, ENCHANTED_GOLEM_SHARD, GIANT_MONSTER_EYE_MEAT, DIRE_WYRM_EGG, GUARDIAN_BASILISK_TALON);
				takeItems(player, -1, REVENANTS_CHAINS, WINDSUS_TUSK, GRANDISS_SKULL, TAIK_OBSIDIAN_AMULET, KARUL_BUGBEAR_HEAD, TAMLIN_IVORY_CHARM);
				takeItems(player, -1, FANG_OF_NARAK, ENCHANTED_GARGOYLES_HORN, COILED_SERPENT_TOTEM, TOTEM_OF_KADESH, KAIKIS_HEAD, KRONBE_VENOM_SAC);
				takeItems(player, -1, EVAS_CHARM, TITANS_TABLET, BOOK_OF_SHUNAIMAN, ROTTING_TREE_SPORES, TRISALIM_VENOM_SAC, TAIK_ORC_TOTEM);
				takeItems(player, -1, HARIT_BARBED_NECKLACE, COIN_OF_OLD_EMPIRE, SKIN_OF_FARCRAN, TEMPEST_SHARD, TSUNAMI_SHARD, SATYR_MANE);
				takeItems(player, -1, HAMADRYAD_SHARD, VANOR_SILENOS_MANE, TALK_BUGBEAR_TOTEM, OKUNS_HEAD, KAKRANS_HEAD, NARCISSUSS_SOULSTONE);
				takeItems(player, -1, DEPRIVE_EYE, UNICORNS_HORN, KERUNOSS_GOLD_MANE, SKULL_OF_EXECUTED, BUST_OF_TRAVIS, SWORD_OF_CADMUS);
				break;
			}
			case "30745-10a.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_1C, 1);
				htmltext = event;
				break;
			}
			case "30745-10b.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_2C, 1);
				htmltext = event;
				break;
			}
			case "30745-10c.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_3C, 1);
				htmltext = event;
				break;
			}
			case "30745-10d.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_4C, 1);
				htmltext = event;
				break;
			}
			case "30745-10e.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_5C, 1);
				htmltext = event;
				break;
			}
			case "30745-10f.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_6C, 1);
				htmltext = event;
				break;
			}
			case "30745-10g.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_7C, 1);
				htmltext = event;
				break;
			}
			case "30745-10h.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_8C, 1);
				htmltext = event;
				break;
			}
			case "30745-10i.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_9C, 1);
				htmltext = event;
				break;
			}
			case "30745-10j.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_10C, 1);
				htmltext = event;
				break;
			}
			case "30745-10k.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_11C, 1);
				htmltext = event;
				break;
			}
			case "30745-10l.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_12C, 1);
				htmltext = event;
				break;
			}
			case "30745-11a.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_1B, 1);
				htmltext = event;
				break;
			}
			case "30745-11b.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_2B, 1);
				htmltext = event;
				break;
			}
			case "30745-11c.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_3B, 1);
				htmltext = event;
				break;
			}
			case "30745-11d.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_4B, 1);
				htmltext = event;
				break;
			}
			case "30745-11e.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_5B, 1);
				htmltext = event;
				break;
			}
			case "30745-11f.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_6B, 1);
				htmltext = event;
				break;
			}
			case "30745-12a.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_1A, 1);
				htmltext = event;
				break;
			}
			case "30745-12b.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_2A, 1);
				htmltext = event;
				break;
			}
			case "30745-12c.html":
			{
				giveItems(player, FIRST_CIRCLE_REQUEST_3A, 1);
				htmltext = event;
				break;
			}
			case "30745-13a.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_1C, 1);
				htmltext = event;
				break;
			}
			case "30745-13b.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_2C, 1);
				htmltext = event;
				break;
			}
			case "30745-13c.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_3C, 1);
				htmltext = event;
				break;
			}
			case "30745-13d.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_4C, 1);
				htmltext = event;
				break;
			}
			case "30745-13e.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_5C, 1);
				htmltext = event;
				break;
			}
			case "30745-13f.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_6C, 1);
				htmltext = event;
				break;
			}
			case "30745-13g.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_7C, 1);
				htmltext = event;
				break;
			}
			case "30745-13k.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_11C, 1);
				htmltext = event;
				break;
			}
			case "30745-13i.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_9C, 1);
				htmltext = event;
				break;
			}
			case "30745-13j.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_10C, 1);
				htmltext = event;
				break;
			}
			case "30745-13l.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_12C, 1);
				htmltext = event;
				break;
			}
			case "30745-14a.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_1B, 1);
				htmltext = event;
				break;
			}
			case "30745-14b.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_2B, 1);
				htmltext = event;
				break;
			}
			case "30745-14c.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_3B, 1);
				htmltext = event;
				break;
			}
			case "30745-14d.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_4B, 1);
				htmltext = event;
				break;
			}
			case "30745-14e.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_5B, 1);
				htmltext = event;
				break;
			}
			case "30745-14f.html":
			{
				giveItems(player, SECOND_CIRCLE_REQUEST_6B, 1);
				htmltext = event;
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
			for (int i = DROPLIST.length - 1; i >= 0; i--)
			{
				final int[] droplist = DROPLIST[i];
				if (npc.getId() == droplist[0])
				{
					if (hasQuestItems(qs.getPlayer(), droplist[1]) && giveItemRandomly(qs.getPlayer(), npc, droplist[2], droplist[3], droplist[4], droplist[5] / 100d, true))
					{
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					}
				}
			}
			
			switch (npc.getId())
			{
				case BREKA_ORC_WARRIOR:
				{
					if (hasQuestItems(qs.getPlayer(), TEST_INSTRUCTIONS_1) && ((getQuestItemsCount(qs.getPlayer(), HAKAS_HEAD) + getQuestItemsCount(qs.getPlayer(), JAKAS_HEAD) + getQuestItemsCount(qs.getPlayer(), MARKAS_HEAD)) < 3))
					{
						if (getRandom(10) < 2)
						{
							if (!hasQuestItems(qs.getPlayer(), HAKAS_HEAD))
							{
								addSpawn(BREKA_OVERLORD_HAKA, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
							}
							else if (!hasQuestItems(qs.getPlayer(), JAKAS_HEAD))
							{
								addSpawn(BREKA_OVERLORD_JAKA, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
							}
							else if (!hasQuestItems(qs.getPlayer(), MARKAS_HEAD))
							{
								addSpawn(BREKA_OVERLORD_MARKA, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
							}
						}
					}
					break;
				}
				case WINDSUS:
				{
					if (hasQuestItems(qs.getPlayer(), TEST_INSTRUCTIONS_1) && !hasQuestItems(qs.getPlayer(), WINDSUS_ALEPH_SKIN) && (getRandom(10) < 2))
					{
						addSpawn(WINDSUS_ALEPH, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					break;
				}
				case GRANDIS:
				{
					if (hasQuestItems(qs.getPlayer(), FIRST_CIRCLE_REQUEST_2A) && !hasQuestItems(qs.getPlayer(), TITANS_TABLET) && (getRandom(10) < 2))
					{
						addSpawn(GOK_MAGOK, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					break;
				}
				case TARLK_BUGBEAR_WARRIOR:
				{
					if (hasQuestItems(qs.getPlayer(), TEST_INSTRUCTIONS_2) && ((getQuestItemsCount(qs.getPlayer(), ATHUS_HEAD) + getQuestItemsCount(qs.getPlayer(), LANKAS_HEAD) + getQuestItemsCount(qs.getPlayer(), TRISKAS_HEAD) + getQuestItemsCount(qs.getPlayer(), MOTURAS_HEAD) + getQuestItemsCount(qs.getPlayer(), KALATHS_HEAD)) < 5))
					{
						if (getRandom(10) < 2)
						{
							if (!hasQuestItems(qs.getPlayer(), ATHUS_HEAD))
							{
								addSpawn(TARLK_RAIDER_ATHU, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
							}
							else if (!hasQuestItems(qs.getPlayer(), LANKAS_HEAD))
							{
								addSpawn(TARLK_RAIDER_LANKA, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
							}
							else if (!hasQuestItems(qs.getPlayer(), TRISKAS_HEAD))
							{
								addSpawn(TARLK_RAIDER_TRISKA, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
							}
							else if (!hasQuestItems(qs.getPlayer(), MOTURAS_HEAD))
							{
								addSpawn(TARLK_RAIDER_MOTURA, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
							}
							else if (!hasQuestItems(qs.getPlayer(), KALATHS_HEAD))
							{
								addSpawn(TARLK_RAIDER_KALATH, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
							}
						}
					}
					break;
				}
				case LETO_LIZARDMAN_SHAMAN:
				{
					if (hasQuestItems(qs.getPlayer(), FIRST_CIRCLE_REQUEST_4B) && !hasQuestItems(qs.getPlayer(), TOTEM_OF_KADESH) && (getRandom(10) < 2))
					{
						addSpawn(LETO_SHAMAN_KETZ, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					
					evolveBloodCrystal(qs.getPlayer());
					break;
				}
				case LETO_LIZARDMAN_OVERLORD:
				{
					if (hasQuestItems(qs.getPlayer(), FIRST_CIRCLE_REQUEST_1B) && !hasQuestItems(qs.getPlayer(), FANG_OF_NARAK) && (getRandom(10) < 2))
					{
						addSpawn(LETO_CHIEF_NARAK, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					
					evolveBloodCrystal(qs.getPlayer());
					break;
				}
				case TIMAK_ORC_WARRIOR:
				{
					if (hasQuestItems(qs.getPlayer(), FIRST_CIRCLE_REQUEST_5B) && !hasQuestItems(qs.getPlayer(), KAIKIS_HEAD) && (getRandom(10) < 2))
					{
						addSpawn(TIMAK_RAIDER_KAIKEE, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					break;
				}
				case TIMAK_ORC_OVERLORD:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_1B) && !hasQuestItems(qs.getPlayer(), OKUNS_HEAD) && (getRandom(10) == 0))
					{
						addSpawn(TIMAK_OVERLORD_OKUN, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					break;
				}
				case FLINE:
				case LIELE:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_7C) && (getQuestItemsCount(qs.getPlayer(), TEMPEST_SHARD) < 40) && (getRandom(20) < 2))
					{
						addSpawn(GREMLIN_FILCHER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEY_RE_ALL_MINE));
					}
					break;
				}
				case FOREST_RUNNER:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_10C) && (getQuestItemsCount(qs.getPlayer(), HAMADRYAD_SHARD) < 40) && (getRandom(20) < 2))
					{
						addSpawn(GREMLIN_FILCHER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEY_RE_ALL_MINE));
					}
					break;
				}
				case KARUL_BUGBEAR:
				{
					if (hasQuestItems(qs.getPlayer(), FIRST_CIRCLE_REQUEST_3A) && !hasQuestItems(qs.getPlayer(), BOOK_OF_SHUNAIMAN) && (getRandom(10) < 2))
					{
						addSpawn(KARUL_CHIEF_OROOTO, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					break;
				}
				case TAIK_ORC_CAPTAIN:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_2B) && !hasQuestItems(qs.getPlayer(), KAKRANS_HEAD) && (getRandom(10) < 2))
					{
						addSpawn(TAIK_OVERLORD_KAKRAN, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					break;
				}
				case MIRROR:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_3B) && (getQuestItemsCount(qs.getPlayer(), NARCISSUSS_SOULSTONE) < 40) && (getRandom(20) < 2))
					{
						addSpawn(GREMLIN_FILCHER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEY_RE_ALL_MINE));
					}
					break;
				}
				case HATAR_RATMAN_THIEF:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_5C) && (getQuestItemsCount(qs.getPlayer(), COIN_OF_OLD_EMPIRE) < 20) && (getRandom(20) < 2))
					{
						addSpawn(GREMLIN_FILCHER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEY_RE_ALL_MINE));
					}
					break;
				}
				case HATAR_RATMAN_BOSS:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_5C) && (getQuestItemsCount(qs.getPlayer(), COIN_OF_OLD_EMPIRE) < 20) && (getRandom(20) < 2))
					{
						addSpawn(GREMLIN_FILCHER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEY_RE_ALL_MINE));
					}
					
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_2A) && !hasQuestItems(qs.getPlayer(), BUST_OF_TRAVIS) && (getRandom(10) < 2))
					{
						addSpawn(HATAR_CHIEFTAIN_KUBEL, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					break;
				}
				case VANOR_SILENOS_CHIEFTAIN:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_6B) && !hasQuestItems(qs.getPlayer(), KERUNOSS_GOLD_MANE) && (getRandom(10) < 2))
					{
						addSpawn(VANOR_ELDER_KERUNOS, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
					}
					break;
				}
				case GREMLIN_FILCHER:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_7C) && (getQuestItemsCount(qs.getPlayer(), TEMPEST_SHARD) < 40))
					{
						if (giveItemRandomly(qs.getPlayer(), npc, TEMPEST_SHARD, 5, 40, 1.0, true))
						{
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.PRETTY_GOOD));
					}
					
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_8C) && (getQuestItemsCount(qs.getPlayer(), TSUNAMI_SHARD) < 40))
					{
						if (giveItemRandomly(qs.getPlayer(), npc, TSUNAMI_SHARD, 5, 40, 1.0, true))
						{
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.PRETTY_GOOD));
					}
					
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_10C) && (getQuestItemsCount(qs.getPlayer(), HAMADRYAD_SHARD) < 40))
					{
						if (giveItemRandomly(qs.getPlayer(), npc, HAMADRYAD_SHARD, 5, 40, 1.0, true))
						{
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.PRETTY_GOOD));
					}
					
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_3B) && (getQuestItemsCount(qs.getPlayer(), NARCISSUSS_SOULSTONE) < 40))
					{
						if (giveItemRandomly(qs.getPlayer(), npc, NARCISSUSS_SOULSTONE, 5, 40, 1.0, true))
						{
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.PRETTY_GOOD));
					}
					
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_5C) && (getQuestItemsCount(qs.getPlayer(), COIN_OF_OLD_EMPIRE) < 20))
					{
						if (giveItemRandomly(qs.getPlayer(), npc, COIN_OF_OLD_EMPIRE, 3, 20, 1.0, true))
						{
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						}
						
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.PRETTY_GOOD));
					}
					break;
				}
				case GOK_MAGOK:
				{
					if (hasQuestItems(qs.getPlayer(), FIRST_CIRCLE_REQUEST_2A) && !hasQuestItems(qs.getPlayer(), TITANS_TABLET) && (getRandom(2) == 0))
					{
						addSpawn(BLACK_LEGION_STORMTROOPER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						addSpawn(BLACK_LEGION_STORMTROOPER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.WE_LL_TAKE_THE_PROPERTY_OF_THE_ANCIENT_EMPIRE));
					}
					break;
				}
				case HATAR_CHIEFTAIN_KUBEL:
				{
					if (hasQuestItems(qs.getPlayer(), SECOND_CIRCLE_REQUEST_2A) && !hasQuestItems(qs.getPlayer(), BUST_OF_TRAVIS) && (getRandom(2) == 0))
					{
						addSpawn(BLACK_LEGION_STORMTROOPER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						addSpawn(BLACK_LEGION_STORMTROOPER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.WE_LL_TAKE_THE_PROPERTY_OF_THE_ANCIENT_EMPIRE));
					}
					break;
				}
				case KARUL_CHIEF_OROOTO:
				{
					if (hasQuestItems(qs.getPlayer(), FIRST_CIRCLE_REQUEST_3A) && !hasQuestItems(qs.getPlayer(), BOOK_OF_SHUNAIMAN) && (getRandom(2) == 0))
					{
						addSpawn(BLACK_LEGION_STORMTROOPER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						addSpawn(BLACK_LEGION_STORMTROOPER, npc.getX(), npc.getY(), npc.getZ() + 10, npc.getHeading(), false, 0, true);
						npc.broadcastPacket(new CreatureSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getName(), NpcStringId.WE_LL_TAKE_THE_PROPERTY_OF_THE_ANCIENT_EMPIRE));
					}
					break;
				}
				case LETO_LIZARDMAN_ARCHER:
				case LETO_LIZARDMAN_SOLDIER:
				case HARIT_LIZARDMAN_GRUNT:
				case HARIT_LIZARDMAN_ARCHER:
				case HARIT_LIZARDMAN_WARRIOR:
				{
					evolveBloodCrystal(qs.getPlayer());
					break;
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	private String reward(L2PcInstance player, QuestState qs, int[][] rewards)
	{
		for (int i = rewards.length - 1; i >= 0; i--)
		{
			final int[] reward = rewards[i];
			if (hasQuestItems(player, reward[0]))
			{
				if (getQuestItemsCount(player, reward[1]) >= reward[2])
				{
					giveItems(player, LAUREL_LEAF_PIN, 1);
					giveAdena(player, reward[3], true);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					qs.setMemoState(0);
					takeItems(player, reward[0], -1);
					if (reward[1] == GIANT_MONSTER_EYE_MEAT)
					{
						takeItems(player, reward[1], reward[2]);
					}
					else
					{
						takeItems(player, reward[1], -1);
					}
					return (hasQuestItems(player, FIRST_CIRCLE_HUNTER_LICENSE) ? "30745-06a.html" : "30745-06b.html");
				}
				return "30745-05.html";
			}
		}
		return null;
	}
	
	private String getHtml(L2PcInstance player, String htmlName, int i0, int i1, int i2, int i3, int i4)
	{
		String html = getHtm(player, htmlName);
		html = html.replace("<?reply1?>", LINKS.get(i0));
		html = html.replace("<?reply2?>", LINKS.get(i1));
		html = html.replace("<?reply3?>", LINKS.get(i2));
		html = html.replace("<?reply4?>", LINKS.get(i3));
		html = html.replace("<?reply5?>", LINKS.get(i4));
		return html;
	}
	
	private void evolveBloodCrystal(L2PcInstance player)
	{
		final L2Weapon weapon = player.getActiveWeaponItem();
		if ((weapon != null) && (weapon.getId() == CYBELLINS_DAGGER) && (hasQuestItems(player, FIRST_CIRCLE_HUNTER_LICENSE) || hasQuestItems(player, SECOND_CIRCLE_HUNTER_LICENSE)))
		{
			if (getRandom(100) < 60)
			{
				if (hasQuestItems(player, CYBELLINS_REQUEST))
				{
					if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_1))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_2, 1);
						takeItems(player, BLOOD_CRYSTAL_PURITY_1, -1);
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_2))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_3, 1);
						takeItems(player, BLOOD_CRYSTAL_PURITY_2, -1);
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_3))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_4, 1);
						takeItems(player, BLOOD_CRYSTAL_PURITY_3, -1);
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_4))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_5, 1);
						takeItems(player, BLOOD_CRYSTAL_PURITY_4, -1);
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_5))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_6, 1);
						takeItems(player, BLOOD_CRYSTAL_PURITY_5, -1);
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_6))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_7, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_JACKPOT);
						takeItems(player, BLOOD_CRYSTAL_PURITY_6, -1);
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_7))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_8, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_JACKPOT);
						takeItems(player, BLOOD_CRYSTAL_PURITY_7, -1);
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_8))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_9, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_JACKPOT);
						takeItems(player, BLOOD_CRYSTAL_PURITY_8, -1);
					}
					else if (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_9))
					{
						giveItems(player, BLOOD_CRYSTAL_PURITY_10, 1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_JACKPOT);
						takeItems(player, BLOOD_CRYSTAL_PURITY_9, -1);
					}
				}
			}
			else if (hasQuestItems(player, CYBELLINS_REQUEST) && (hasQuestItems(player, BLOOD_CRYSTAL_PURITY_1) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_2) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_3) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_4) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_5) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_6) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_7) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_8) || hasQuestItems(player, BLOOD_CRYSTAL_PURITY_9)))
			{
				takeItems(player, -1, BLOOD_CRYSTAL_PURITY_1, BLOOD_CRYSTAL_PURITY_2, BLOOD_CRYSTAL_PURITY_3, BLOOD_CRYSTAL_PURITY_4, BLOOD_CRYSTAL_PURITY_5, BLOOD_CRYSTAL_PURITY_6, BLOOD_CRYSTAL_PURITY_7, BLOOD_CRYSTAL_PURITY_8, BLOOD_CRYSTAL_PURITY_9);
				giveItems(player, BROKEN_BLOOD_CRYSTAL, 1);
			}
		}
	}
}

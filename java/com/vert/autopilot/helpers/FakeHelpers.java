package com.vert.autopilot.helpers;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.data.xml.impl.ExperienceData;
import com.l2jmobius.gameserver.data.xml.impl.PlayerTemplateData;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.idfactory.IdFactory;
import com.l2jmobius.gameserver.model.actor.appearance.PcAppearance;
import com.l2jmobius.gameserver.model.actor.templates.L2PcTemplate;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;

import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.FakePlayerNameManager;
import com.vert.autopilot.ai.FakePlayerAI;
import com.vert.autopilot.ai.FallbackAI;
import com.vert.autopilot.ai.occupations.*;

import java.util.*;

/**
 * @author vert
 */
public class FakeHelpers {
    public static int[][] getFighterBuffs() {
        return new int[][] {
                { 1048, 6 }, // Blessed Soul
                { 1045, 6 }, // Blessed Body
                { 1204, 2 }, // Wind Walk
                { 1035, 4 }, // Mental Shield
                { 1062, 2 }, // Berserker Spirit
                { 1086, 2 }, // Haste
                { 1077, 3 }, // Focus
                { 1388, 3 }, // Greater Might
                { 1242, 3 }, // Death Whisper
                { 1036, 2 }, // Magic Barrier
                { 1268, 4 }, // Vampiric Rage
                { 274, 1 }, // Dance of Fire
                { 273, 1 }, // Dance of Fury
                { 271, 1 }, // Dance of the Warrior
                { 310, 1 }, // Dance of the Vampire
                { 267, 1 }, // Song of Warding
                { 268, 1 }, // Song of Wind
                { 349, 1 }, // Song of Renewal
                { 264, 1 }, // Song of Earth
                { 269, 1 }, // Song of Hunter
                { 364, 1 }, // Song of Champion
                { 1363, 1 }, // Chant of Victory
                { 4699, 5 }, // Blessing of Queen
                { 4703, 4 }, // Gift of Seraphim
                { 1499, 1 } // Improved Combat
        };
    }

    public static int[][] getMageBuffs() {
        return new int[][] {
                { 1048, 6 }, // Blessed Soul
                { 1045, 6 }, // Blessed Body
                { 1204, 2 }, // Wind Walk
                { 1499, 1 }, // Improved Combat
                { 1035, 4 }, // Mental Shield
                { 4351, 6 }, // Concentration
                { 1397, 3 }, // Clarity
                { 1036, 2 }, // Magic Barrier
                { 1045, 6 }, // Bless the Body
                { 1303, 2 }, // Wild Magic
                { 1085, 3 }, // Acumen
                { 1062, 2 }, // Berserker Spirit
                { 1059, 3 }, // Empower
                { 1389, 3 }, // Greater Shield
                { 273, 1 }, // Dance of the mystic
                { 276, 1 }, // Dance of concentration
                { 365, 1 }, // Dance of Siren
                { 268, 1 }, // Song of Wind
                { 267, 1 }, // Song of Warding
                { 349, 1 }, // Song of Renewal
                { 264, 1 }, // Song of Earth
                { 1413, 1 }, // Magnus\' Chant
                { 4703, 4 } // Gift of Seraphim
        };
    }

    public static int getTestTargetRange() {
        return 2000;
    }

    public static FakePlayer createFakePlayer(String occupation, String level) {
        int objectId = IdFactory.getInstance().getNextId();
        String accountName = "AutoPilot";
        String playerName = FakePlayerNameManager.INSTANCE.getRandomAvailableName();

        ClassId classId = getFakeSelectedClass(occupation);

        final L2PcTemplate template = PlayerTemplateData.getInstance().getTemplate(classId);
        PcAppearance app = getRandomAppearance(template.getRace());
        FakePlayer player = new FakePlayer(objectId, template, accountName, app);

        player.setName(playerName);
        player.setAccessLevel(0);

        // Add player into database table
//        PlayerInfoTable.getInstance().addPlayer(objectId, accountName, playerName, player.getAccessLevel().getLevel());
        player.setBaseClass(player.getClassId());
        setLevel(player, getFakeSelectedLevel(level));
        player.rewardSkills();

        giveArmorsByClass(player);
//        Weapon random enchanted from +7 between +20
//        giveWeaponsByClass(player,true);
        giveWeaponsByClass(player,false);
        player.heal();

        return player;
    }

    public static ClassId getFakeSelectedClass(String occupation) {
        ClassId classId;

        switch (occupation) {
            case "Sagittarius":
                classId = ClassId.SAGITTARIUS;
                break;
            case "MoonlightSent":
                classId = ClassId.MOONLIGHT_SENTINEL;
                break;
            case "GhostSentinel":
                classId = ClassId.GHOST_SENTINEL;
                break;
            case "Archmage":
                classId = ClassId.ARCHMAGE;
                break;
            case "MysticMuse":
                classId = ClassId.MYSTIC_MUSE;
                break;
            case "StormScreamer":
                classId = ClassId.STORM_SCREAMER;
                break;
            default:
                classId = getThirdClasses().get(Rnd.get(0, getThirdClasses().size() - 1));
                break;
        }

        return classId;
    }

    public static int getFakeSelectedLevel(String selectedLevel) {
        int level = 1;

        if (selectedLevel == null) {
            // Todo: add one configuration to the default level
            return 79;
        }

        if (Integer.parseInt(selectedLevel, 10) > 87) {
            selectedLevel = "86";
        }

        for (int indexLevel = 1; indexLevel < 87; indexLevel ++) {
            if (Integer.parseInt(selectedLevel, 10) == indexLevel) {
                level = indexLevel;
                break;
            }
        }

        return level;
    }

    public static FakePlayer createRandomFakePlayer() {
        int objectId = IdFactory.getInstance().getNextId();
        String accountName = "AutoPilot";
        String playerName = FakePlayerNameManager.INSTANCE.getRandomAvailableName();

        ClassId classId = getThirdClasses().get(Rnd.get(0, getThirdClasses().size() - 1));

        final L2PcTemplate template = PlayerTemplateData.getInstance().getTemplate(classId);
        PcAppearance app = getRandomAppearance(template.getRace());
        FakePlayer player = new FakePlayer(objectId, template, accountName, app);

        player.setName(playerName);
        player.setAccessLevel(0);

        // Add player into database table
//        PlayerInfoTable.getInstance().addPlayer(objectId, accountName, playerName, player.getAccessLevel().getLevel());
        player.setBaseClass(player.getClassId());
        setLevel(player, 78);
        player.rewardSkills();

        giveArmorsByClass(player);
//        Weapon random enchanted from +7 between +20
//        giveWeaponsByClass(player,true);
        giveWeaponsByClass(player,false);
        player.heal();

        return player;
    }

    public static void giveArmorsByClass(FakePlayer player) {
        List<Integer> itemIds = new ArrayList<>();
        switch (player.getClassId()) {
            case ARCHMAGE:
            case SOULTAKER:
            case HIEROPHANT:
            case ARCANA_LORD:
            case CARDINAL:
            case MYSTIC_MUSE:
            case ELEMENTAL_MASTER:
            case EVA_SAINT:
            case STORM_SCREAMER:
            case SPECTRAL_MASTER:
            case SHILLIEN_SAINT:
            case DOMINATOR:
            case DOOMCRYER:
                itemIds = getMageArmorByGrade(player);
                break;
            case DUELIST:
            case DREADNOUGHT:
            case PHOENIX_KNIGHT:
            case SWORD_MUSE:
            case HELL_KNIGHT:
            case SPECTRAL_DANCER:
            case EVA_TEMPLAR:
            case SHILLIEN_TEMPLAR:
            case TITAN:
            case MAESTRO:
                itemIds = Arrays.asList(6373, 6374, 6375, 6376, 6378, 858, 858, 889, 889, 920);
                break;
            case SAGITTARIUS:
            case ADVENTURER:
            case WIND_RIDER:
            case MOONLIGHT_SENTINEL:
            case GHOST_HUNTER:
            case GHOST_SENTINEL:
            case FORTUNE_SEEKER:
            case GRAND_KHAVATARI:
                itemIds = getLightArmorByGrade(player);
                break;
            default:
                break;
        }
        for (int id : itemIds) {
            player.getInventory().addItem("Armors", id, 1, player, null);
            L2ItemInstance item = player.getInventory().getItemByItemId(id);
            // @Todo: check this line in FakeHelpers
            // enchant the item??
            player.getInventory().equipItemAndRecord(item);
            player.getInventory().reloadEquippedItems();
            player.broadcastCharInfo();
        }
    }

    public static List<Integer> getMageArmorByGrade(FakePlayer player) {
        List<Integer> itemsIds = new ArrayList<>();

        switch (getPlayerGrade(player)) {
            case "S84":
                // Todo: Need add S84 sets
                if (Rnd.get(0, 1) == 0) {
                    /**
                     * 15611 = Moirai Tunic;
                     * 15614 = Moirai Stockings;
                     * 15608 = Moirai Circlet;
                     * 15617 = Moirai Gloves;
                     * 15620 = Moirai Shoes;
                     * 15723 = Moirai Ring;
                     * 15724 = Moirai Earring;
                     * 15725 = Moirai Necklace;
                     */
                    itemsIds = Arrays.asList(15611, 15614, 15608, 15617, 15620, 15723, 15723, 15724, 15724, 15725);
                } else {
                    /**
                     * 9432 = Dynasty Tunic;
                     * 9437 = Dynasty Stockings;
                     * 9438 = Dynasty Circlet;
                     * 9439 = Dynasty Gloves (Robe);
                     * 9440 = Dynasty Shoes (Robe);
                     * 15723 = Moirai Ring;
                     * 15724 = Moirai Earring;
                     * 15725 = Moirai Necklace;
                     */
                    itemsIds = Arrays.asList(9432, 9437, 9438, 9439, 9440, 15723, 15723, 15724, 15724, 15725);
                }
                break;
            case "S80":
                if (Rnd.get(0, 1) == 0) {
                    /**
                     * 15611 = Moirai Tunic;
                     * 15614 = Moirai Stockings;
                     * 15608 = Moirai Circlet;
                     * 15617 = Moirai Gloves;
                     * 15620 = Moirai Shoes;
                     * 15723 = Moirai Ring;
                     * 15724 = Moirai Earring;
                     * 15725 = Moirai Necklace;
                     */
                    itemsIds = Arrays.asList(15611, 15614, 15608, 15617, 15620, 15723, 15723, 15724, 15724, 15725);
                } else {
                    /**
                     * 9432 = Dynasty Tunic;
                     * 9437 = Dynasty Stockings;
                     * 9438 = Dynasty Circlet;
                     * 9439 = Dynasty Gloves (Robe);
                     * 9440 = Dynasty Shoes (Robe);
                     * 15723 = Moirai Ring;
                     * 15724 = Moirai Earring;
                     * 15725 = Moirai Necklace;
                     */
                    itemsIds = Arrays.asList(9432, 9437, 9438, 9439, 9440, 15723, 15723, 15724, 15724, 15725);
                }
                break;
            case "S":
                /**
                 * 6383 = Major Arcana Robe;
                 * 6384 = Major Arcana Helmet;
                 * 6385 = Major Arcana Gloves;
                 * 6386 = Major Arcana Boots;
                 * 858 = Tateossian Earring;
                 * 889 = Tateossian Ring;
                 * 920 = Tateossian Necklace;
                 */
                itemsIds = Arrays.asList(6383, 6384, 6385, 6386, 858, 858, 889, 889, 920);
                break;
            case "A":
                /**
                 * 2407 = Dark Crystal Robe;
                 * 512 = Dark Crystal Helmet;
                 * 5767 = Dark Crystal Gloves;
                 * 5779 = Dark Crystal Boots;
                 * 862 = Majestic Earring;
                 * 893 = Majestic Ring;
                 * 924 = Majestic Necklace;
                 */
                itemsIds = Arrays.asList(2407, 512, 5767, 5779, 862, 862, 893, 893, 924);
                break;
            case "B":
                /**
                 * 2406 = Avadon Robe;
                 * 2415 = Avadon Ciclet;
                 * 5716 = Avadon Gloves (Robe);
                 * 5732 = Avadon Boots (Robe);
                 * 14345 = Earring Of Black Ore;
                 * 14346 = Ring Of Black Ore;
                 * 14347 = Necklace Of Black Ore;
                 */
                itemsIds = Arrays.asList(2406, 2415, 5716, 5732, 14345, 14345, 14346, 14346, 14347);
                break;
            case "C":
                /**
                 * 439 = Karmian Tunic;
                 * 471 = Karmian Stocking;
                 * 2454 = Karmian Gloves;
                 * 2430 = Karmian Boots;
                 * 2414 = Full Plate Helmet;
                 * 888 = Blessed Ring;
                 * 857 = Blessed Earring;
                 * 919 = Blessed Necklace;
                 */
                itemsIds = Arrays.asList(439, 471, 2454, 2430, 2414, 888, 888, 857, 857, 919);
                break;
            case "D":
                /**
                 * 437 = Mithril Tunic;
                 * 470 = Mithril Stocking;
                 * 2450 = Mithril Gloves;
                 * 2424 = Manticore Skin Boots;
                 * 2411 = Brigandine Helmet;
                 * 881 = Elven Ring;
                 * 850 = Elven Earring;
                 * 913 = Elven Necklace;
                 */
                itemsIds = Arrays.asList(437, 470, 2450, 2424, 2411, 881, 881, 850, 850, 913);
                break;
            case "no-grade":
                /**
                 * 1101 = Tunic of Devotion;
                 * 1104 = Stocking of Devotion;
                 * 44 = Leather Helmet;
                 */
                itemsIds = Arrays.asList(1101, 1104, 44);
                break;
        }

        return itemsIds;
    }

    public static List<Integer> getLightArmorByGrade(FakePlayer player) {
        List<Integer> itemsIds = new ArrayList<>();

        switch (getPlayerGrade(player)) {
            case "S84":
                // Todo: Need add S84 sets
                if (Rnd.get(0, 1) == 0) {
                    /**
                     * 15610 = Moirai Leather Breastplate;
                     * 15613 = Moirai Leather Legging;
                     * 15607 = Moirai Leather Helmet;
                     * 15616 = Moirai Leather Gloves;
                     * 15619 = Moirai Leather Boots;
                     * 15723 = Moirai Ring;
                     * 15724 = Moirai Earring;
                     * 15725 = Moirai Necklace;
                     */
                    itemsIds = Arrays.asList(15610, 15613, 15607, 15616, 15619, 15723, 15723, 15724, 15724, 15725);
                } else {
                    /**
                     * 9425 = Dynasty Leather Armor;
                     * 9428 = Dynasty Leather Leggings;
                     * 9429 = Dynasty Leather Helmet;
                     * 9430 = Dynasty Leather Gloves;
                     * 9431 = Dynasty Leather Boots;
                     * 15723 = Moirai Ring;
                     * 15724 = Moirai Earring;
                     * 15725 = Moirai Necklace;
                     */
                    itemsIds = Arrays.asList(9425, 9428, 9429, 9430, 9431, 15723, 15723, 15724, 15724, 15725);
                }
                break;
            case "S80":
                if (Rnd.get(0, 1) == 0) {
                    /**
                     * 15610 = Moirai Leather Breastplate;
                     * 15613 = Moirai Leather Legging;
                     * 15607 = Moirai Leather Helmet;
                     * 15616 = Moirai Leather Gloves;
                     * 15619 = Moirai Leather Boots;
                     * 15723 = Moirai Ring;
                     * 15724 = Moirai Earring;
                     * 15725 = Moirai Necklace;
                     */
                    itemsIds = Arrays.asList(15610, 15613, 15607, 15616, 15619, 15723, 15723, 15724, 15724, 15725);
                } else {
                    /**
                     * 9425 = Dynasty Leather Armor;
                     * 9428 = Dynasty Leather Leggings;
                     * 9429 = Dynasty Leather Helmet;
                     * 9430 = Dynasty Leather Gloves;
                     * 9431 = Dynasty Leather Boots;
                     * 15723 = Moirai Ring;
                     * 15724 = Moirai Earring;
                     * 15725 = Moirai Necklace;
                     */
                    itemsIds = Arrays.asList(9425, 9428, 9429, 9430, 9431, 15723, 15723, 15724, 15724, 15725);
                }
                break;
            case "S":
                /**
                 * 6379 = Draconic Leather Armor;
                 * 6382 = Draconic Helmet;
                 * 6380 = Draconic Gloves;
                 * 6381 = Draconic Boots;
                 * 858 = Tateossian Earring;
                 * 889 = Tateossian Ring;
                 * 920 = Tateossian Necklace;
                 */
                itemsIds = Arrays.asList(6379, 6380, 6381, 6382, 858, 858, 889, 889, 920);
                break;
            case "A":
                if (Rnd.get(0, 1) == 0) {
                    /**
                     * 2385 = Dark Crystal Leather Armor;
                     * 2389 = Dark Crystal Leggings;
                     * 512 = Dark Crystal Helmet;
                     * 5766 = Dark Crystal Gloves (Light);
                     * 5778 = Dark Crystal Boots (Light);
                     * 862 = Majestic Earring;
                     * 893 = Majestic Ring;
                     * 924 = Majestic Necklace;
                     */
                    itemsIds = Arrays.asList(2385, 2389, 512, 5766, 5778, 862, 862, 893, 893, 924);
                } else {
                    /**
                     * 2393 = Tallum Leather Armor;
                     * 547 = Tallum Helm;
                     * 5769 = Tallum Gloves (Light);
                     * 5781 = Tallum Boots (Light);
                     * 862 = Majestic Earring;
                     * 893 = Majestic Ring;
                     * 924 = Majestic Necklace;
                     */
                    itemsIds = Arrays.asList(2385, 2389, 512, 5766, 5778, 862, 862, 893, 893, 924);
                }
                break;
            case "B":
                /**
                 * 2390 = Avadon Leather Armor;
                 * 2415 = Avadon Ciclet;
                 * 5715 = Avadon Gloves (Light);
                 * 5731 = Avadon Boots (Light);
                 * 14345 = Earring Of Black Ore;
                 * 14346 = Ring Of Black Ore;
                 * 14347 = Necklace Of Black Ore;
                 */
                itemsIds = Arrays.asList(2390, 2415, 5715, 5731, 14345, 14345, 14346, 14346, 14347);
                break;
            case "C":
                /**
                 * 400 = Theca Leather Armor;
                 * 420 = Theca Leather Gaiters;
                 * 2460 = Theca Leather Gloves;
                 * 2436 = Theca Leather Boots;
                 * 2414 = Full Plate Helmet;
                 * 888 = Blessed Ring;
                 * 857 = Blessed Earring;
                 * 919 = Blessed Necklace;
                 */
                itemsIds = Arrays.asList(400, 420, 2460, 2436, 2414, 888, 888, 857, 857, 919);
                break;
            case "D":
                /**
                 * 437 = Salamander Skin Mail;
                 * 607 = Ogre Power Gauntlets;
                 * 2427 = Salamander Skin Boots;
                 * 2411 = Brigandine Helmet;
                 * 881 = Elven Ring;
                 * 850 = Elven Earring;
                 * 913 = Elven Necklace;
                 */
                itemsIds = Arrays.asList(437, 607, 2427, 2411, 881, 881, 850, 850, 913);
                break;
            case "no-grade":
                /**
                 * 23 = Wooden Breastplate;
                 * 2386 = Wooden Gaiters;
                 * 43 = Wooden helmet;
                 */
                itemsIds = Arrays.asList(23, 2386, 43);
                break;
        }

        return itemsIds;
    }

    public static String getPlayerGrade(FakePlayer player) {
        String grade = "no-grade";
        Integer playerLevel = player.getLevel();

        if (playerLevel >= 84) {
            grade = "S84";
        } else if (playerLevel >= 80) {
            grade = "S80";
        } else if (playerLevel >= 76) {
            grade = "S";
        } else if (playerLevel >= 61) {
            grade = "A";
        } else if (playerLevel >= 52) {
            grade = "B";
        } else if (playerLevel >= 40) {
            grade = "C";
        } else if (playerLevel >= 20) {
            grade = "D";
        }

        return grade;
    }

    public static void giveWeaponsByClass(FakePlayer player, boolean randomlyEnchant) {
        List<Integer> itemsIds = new ArrayList<>();
        int randomNumber;

        switch (player.getClassId()) {
            /**
             * ======================
             * Initial Mage Classes =
             * ======================
             */
            case MAGE:
            case ELVEN_MAGE:
            case DARK_MAGE:
            case ORC_MAGE:
                /**
                 * 6355 = Mage Staff - for Beginners
                 */
                itemsIds = Arrays.asList(6355);
                break;

            /**
             * ===============
             * D-Grade Mages =
             * ===============
             */

            /**
             * Single Handed Blunts
             */
            case WIZARD:
            case CLERIC:
            case ELVEN_WIZARD:
            case ORACLE:
            case DARK_WIZARD:
            case SHILLIEN_ORACLE:
            case ORC_SHAMAN:
                /**
                 * 189 = Staff of Life
                 */
                itemsIds = Arrays.asList(189);
                break;

            /**
             * ===================
             * C-B-A-Grade Mages =
             * ===================
             */

            /**
             * Single Handed Swords
             */
            case SORCERER:
            case NECROMANCER:
            case WARLOCK:
            case BISHOP:
            case PROPHET:
            case SPELLSINGER:
            case ELEMENTAL_SUMMONER:
            case ELDER:
            case SPELLHOWLER:
            case PHANTOM_SUMMONER:
            case SHILLIEN_ELDER:
            case OVERLORD:
            case WARCRYER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 6313 = Homunkulus's Sword (Acumen)
                         */
                        itemsIds = Arrays.asList(6313);
                        break;

                    case "B":
                        /**
                         * 7722 = Sword of Valhala (Acumen)
                         */
                        itemsIds = Arrays.asList(7722);
                        break;

                    case "A":
                        /**
                         * 5643 = Sword of Miracles (Acumen)
                         */
                        itemsIds = Arrays.asList(5643);
                        break;
                }
                break;

            /**
             * =======================
             * S-S80-S84-Grade Mages =
             * =======================
             */

            /**
             * Single Handed Swords
             */
            case ARCHMAGE:
            case SOULTAKER:
            case ARCANA_LORD:
            case CARDINAL:
            case HIEROPHANT:
            case MYSTIC_MUSE:
            case ELEMENTAL_MASTER:
            case EVA_SAINT:
            case STORM_SCREAMER:
            case SPECTRAL_MASTER:
            case SHILLIEN_SAINT:
            case DOMINATOR:
            case DOOMCRYER:
                switch (getPlayerGrade(player)) {
                    case "S":
                        if (Rnd.get(0, 1) == 0) {
                            /**
                             * 6608 = Arcana Mace
                             */
                            itemsIds = Arrays.asList(6608);
                        } else {
                            /**
                             * 9860 = Dynasty Phantom (Acumen)
                             */
                            itemsIds = Arrays.asList(9860);
                        }
                        break;

                    case "S80":
                        /**
                         * 10440 = Icarus Spirit
                         */
                        itemsIds = Arrays.asList(10440);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14125 = Vesper Buster (Acumen)
                             */
                            itemsIds = Arrays.asList(14125);
                        } else if (randomNumber == 1) {
                            /**
                             * 15856 = Veniplant Sword (Acumen)
                             */
                            itemsIds = Arrays.asList(15856);
                        } else if (randomNumber == 2) {
                            /**
                             * 15900 = Archangel Sword (Acumen)
                             */
                            itemsIds = Arrays.asList(15900);
                        }
                        break;
                }
                break;

            /**
             * =========================
             * Initial Fighter Classes =
             * =========================
             */
            case FIGHTER:
            case ELVEN_FIGHTER:
            case DARK_FIGHTER:
            case ORC_FIGHTER:
            case DWARVEN_FIGHTER:
            case MALE_SOLDIER:
            case FEMALE_SOLDIER:
                /**
                 * 6354 = Falchion - for Beginners
                 */
                itemsIds = Arrays.asList(6354);
                break;

            /**
             * ==================
             * D-Grade Fighters =
             * ==================
             */

            /**
             * Sword & Shield
             */
            case WARRIOR:
            case KNIGHT:
            case ELVEN_KNIGHT:
            case PALUS_KNIGHT:
                /**
                 * 2499 = Elven Long Sword
                 * 2493 = Brigandine Shield
                 */
                itemsIds = Arrays.asList(2499, 2493);
                break;

            /**
             * Hammer & Shield
             */
            case ORC_RAIDER:
            case SCAVENGER:
            case ARTISAN:
                /**
                 * 159 = Bonebreaker
                 * 2493 = Brigandine Shield
                 */
                itemsIds = Arrays.asList(159, 2493);
                break;

            /**
             * Dagger or Bow
             */
            case ROGUE:
            case ELVEN_SCOUT:
            case ASSASSIN:
                if (Rnd.get(0, 1) == 0) {
                    /**
                     * 225 = Mithril Dagger
                     */
                    itemsIds = Arrays.asList(225);
                } else {
                    /**
                     * 280 = Light Crossbow
                     */
                    itemsIds = Arrays.asList(280);
                }
                break;

            /**
             * Fist
             */
            case ORC_MONK:
                /**
                 * 262 = Scallop Jamadhr
                 */
                itemsIds = Arrays.asList(262);
                break;

            /**
             * Crossbow
             */
            case WARDER:
                /**
                 * 9996 = Hand Crossbow
                 */
                itemsIds = Arrays.asList(9996);
                break;

            /**
             * Ancient Sword
             */
            case TROOPER:
                /**
                 * 9226 = General's Katzbalger
                 */
                itemsIds = Arrays.asList(9226);
                break;

            /**
             * ======================
             * C-B-A-Grade Fighters =
             * ======================
             */

            /**
             * Dual Swords
             */
            case GLADIATOR:
            case BLADEDANCER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 2599 = Raid Sword*Raid Sword
                         */
                        itemsIds = Arrays.asList(2599);
                        break;

                    case "B":
                        /**
                         * 2626 = Samurai Long Sword*Samurai Long Sword
                         */
                        itemsIds = Arrays.asList(2626);
                        break;

                    case "A":
                        /**
                         * 5706 = Damascus*Damascus
                         */
                        itemsIds = Arrays.asList(5706);
                        break;
                }
            break;

            /**
             * Polearms
             */
            case WARLORD:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 4852 = Orcish Poleaxe (Critical Stun)
                         */
                        itemsIds = Arrays.asList(4852);
                        break;

                    case "B":
                        /**
                         * 4859 = Lance (Critical Stun)
                         */
                        itemsIds = Arrays.asList(4859);
                        break;

                    case "A":
                        /**
                         * 4861 = Halberd (Critical Stun)
                         */
                        itemsIds = Arrays.asList(4861);
                        break;
                }
                break;

            /**
             * Sword & Shield
             */
            case PALADIN:
            case DARK_AVENGER:
            case TEMPLE_KNIGHT:
            case SWORDSINGER:
            case SHILLIEN_KNIGHT:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 4708 = Samurai Longsword (Focus)
                         * 2497 = Full Plate Shield
                         */
                        itemsIds = Arrays.asList(4708, 2497);
                        break;

                    case "B":
                        /**
                         * 4708 = Keshanberk (Focus)
                         * 673 = Avadon Shield
                         */
                        itemsIds = Arrays.asList(4715, 673);
                        break;

                    case "A":
                        /**
                         * 5648 = Dark Legion's Edge (Health)
                         * 641 = Dark Crystal Shield
                         */
                        itemsIds = Arrays.asList(5648, 641);
                        break;
                }
                break;

            /**
             * Hammer & Shield
             */
            case WARSMITH:
            case BOUNTY_HUNTER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 4745 = Yaksa Mace (Health)
                         * 2497 = Full Plate Shield
                         */
                        itemsIds = Arrays.asList(4745, 2497);
                        break;

                    case "B":
                        /**
                         * 4753 = Art of Battle Axe (Health)
                         * 673 = Avadon Shield
                         */
                        itemsIds = Arrays.asList(4753, 673);
                        break;

                    case "A":
                        /**
                         * 5602 = Elysian (Health)
                         * 641 = Dark Crystal Shield
                         */
                        itemsIds = Arrays.asList(5602, 641);
                        break;
                }
                break;

            /**
             * Bows
             */
            case HAWKEYE:
            case SILVER_RANGER:
            case PHANTOM_RANGER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 4822 = Eminence Bow (Guidance)
                         */
                        itemsIds = Arrays.asList(4822);
                        break;

                    case "B":
                        /**
                         * 4826 = Dark Elven Long Bow (Critical Bleed)
                         */
                        itemsIds = Arrays.asList(4826);
                        break;

                    case "A":
                        /**
                         * 5612 = Soul Bow (Quick Recovery)
                         */
                        itemsIds = Arrays.asList(5612);
                        break;
                }
                break;

            /**
             * Daggers
             */
            case TREASURE_HUNTER:
            case PLAINS_WALKER:
            case ABYSS_WALKER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 4776 = Crystal Dagger (Mortal Strike)
                         */
                        itemsIds = Arrays.asList(4776);
                        break;

                    case "B":
                        /**
                         * 4782 = Demon's Dagger (Mortal Strike)
                         */
                        itemsIds = Arrays.asList(4782);
                        break;

                    case "A":
                        /**
                         * 5618 = Soul Separator (Critical Damage)
                         */
                        itemsIds = Arrays.asList(5618);
                        break;
                }
                break;

            /**
             * Fist
             */
            case TYRANT:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 4797 = Great Pata (Rsk. Haste)
                         */
                        itemsIds = Arrays.asList(4797);
                        break;

                    case "B":
                        /**
                         * 4803 = Arthro Nail (Rsk. Haste)
                         */
                        itemsIds = Arrays.asList(4803);
                        break;

                    case "A":
                        /**
                         * 5625 = Dragon Grinder (Health)
                         */
                        itemsIds = Arrays.asList(5625);
                        break;
                }
                break;

            /**
             * Crossbow
             */
            case ARBALESTER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 9301 = Sharpshooter (Guidance)
                         */
                        itemsIds = Arrays.asList(9301);
                        break;

                    case "B":
                        /**
                         * 9314 = Peacemaker (Critical Bleed)
                         */
                        itemsIds = Arrays.asList(9314);
                        break;

                    case "A":
                        /**
                         * 9350 = Reaper (Quick Recovery)
                         */
                        itemsIds = Arrays.asList(9350);
                        break;
                }
                break;

            /**
             * Ancient Sword
             */
            case BERSERKER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 9297 = Saber Tooth
                         */
                        itemsIds = Arrays.asList(9297);
                        break;

                    case "B":
                        /**
                         * 9311 = Innominate Victory (Focus)
                         */
                        itemsIds = Arrays.asList(9311);
                        break;

                    case "A":
                        /**
                         * 9345 = Undertaker (Health)
                         */
                        itemsIds = Arrays.asList(9345);
                        break;
                }
                break;

            /**
             * Double Handed Swords
             */
            case DESTROYER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 6347 = Berserker Blade
                         */
                        itemsIds = Arrays.asList(6347);
                        break;

                    case "B":
                        /**
                         * 4725 = Great Sword (Focus)
                         */
                        itemsIds = Arrays.asList(4725);
                        break;

                    case "A":
                        /**
                         * 5644 = Dragon Slayer (Health)
                         */
                        itemsIds = Arrays.asList(5644);
                        break;
                }
                break;

            /**
             * Single Handed Swords
             */
            case MALE_SOULBREAKER:
            case FEMALE_SOULBREAKER:
                switch (getPlayerGrade(player)) {
                    case "C":
                        /**
                         * 9293 = Admiral's Estoc (Focus)
                         */
                        itemsIds = Arrays.asList(9293);
                        break;

                    case "B":
                        /**
                         * 9306 = Military Fleuret (Focus)
                         */
                        itemsIds = Arrays.asList(9306);
                        break;

                    case "A":
                        /**
                         * 9342 = Lacerator (Health)
                         */
                        itemsIds = Arrays.asList(9342);
                        break;
                }
                break;

            /**
             * ==========================
             * S-S80-S84-Grade Fighters =
             * ==========================
             */

            /**
             * Dual Swords
             */
            case DUELIST:
            case SPECTRAL_DANCER:
                switch (getPlayerGrade(player)) {
                    case "S":
                        if (Rnd.get(0, 1) == 0) {
                            /**
                             * 6580 = Tallum Blade*Dark Legion's Edge
                             */
                            itemsIds = Arrays.asList(6580);
                        } else {
                            /**
                             * 10004 = Dynasty Dual Sword
                             */
                            itemsIds = Arrays.asList(10004);
                        }
                        break;

                    case "S80":
                        /**
                         * 10415 = Icarus Dual Sword
                         */
                        itemsIds = Arrays.asList(10415);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 52 = Vesper Dual Sword
                             */
                            itemsIds = Arrays.asList(52);
                        } else if (randomNumber == 1) {
                            /**
                             * 16154 = Periel Dual Sword
                             */
                            itemsIds = Arrays.asList(16154);
                        } else if (randomNumber == 2) {
                            /**
                             * 16158 = Eternal Core Dual Sword
                             */
                            itemsIds = Arrays.asList(16158);
                        }
                        break;
                }
                break;

            /**
             * Polearms
             */
            case DREADNOUGHT:
                switch (getPlayerGrade(player)) {
                    case "S":
                        if (Rnd.get(0, 1) == 0) {
                            /**
                             * 6601 = Saint Spear (Haste)
                             */
                            itemsIds = Arrays.asList(6601);
                        } else {
                            /**
                             * 9870 = Dynasty Halberd (Critical Stun)
                             */
                            itemsIds = Arrays.asList(9870);
                        }
                        break;

                    case "S80":
                        /**
                         * 10450 = Icarus Trident (Critical Stun)
                         */
                        itemsIds = Arrays.asList(10450);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14135 = Vesper Stormer (Haste)
                             */
                            itemsIds = Arrays.asList(14135);
                        } else if (randomNumber == 1) {
                            /**
                             * 15848 = Doubletop Spear (Haste)
                             */
                            itemsIds = Arrays.asList(15848);
                        } else if (randomNumber == 2) {
                            /**
                             * 15889 = Demitelum (Haste)
                             */
                            itemsIds = Arrays.asList(15889);
                        }
                        break;
                }
                break;

            /**
             * Sword & Shield
             */
            case PHOENIX_KNIGHT:
            case HELL_KNIGHT:
            case EVA_TEMPLAR:
            case SWORD_MUSE:
            case SHILLIEN_TEMPLAR:
                switch (getPlayerGrade(player)) {
                    case "S":
                        if (Rnd.get(0, 1) == 0) {
                            /**
                             * 6583 = Forgotten Blade (Focus)
                             * 6377 = Imperial Crusader Shield
                             */
                            itemsIds = Arrays.asList(6583, 6377);
                        } else {
                            /**
                             * 9854 = Dynasty Sword (Focus)
                             * 9441 = Dynasty Shield
                             */
                            itemsIds = Arrays.asList(9854, 9441);
                        }
                        break;

                    case "S80":
                        /**
                         * 10434 = Icarus Sawsword (Focus)
                         * 9441 = Dynasty Shield
                         */
                        itemsIds = Arrays.asList(10434, 9441);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14120 = Vesper Cutter (Focus)
                             * 13471 = Vesper Shield
                             */
                            itemsIds = Arrays.asList(14120, 13471);
                        } else if (randomNumber == 1) {
                            /**
                             * 15830 = Periel Sword (Focus)
                             * 15604 = Vorpal Shield
                             */
                            itemsIds = Arrays.asList(15830, 15604);
                        } else if (randomNumber == 2) {
                            /**
                             * 15871 = Eternal Core Sword (Focus)
                             * 15587 = Elegia Shield
                             */
                            itemsIds = Arrays.asList(15871, 15587);
                        }
                        break;
                }
                break;

            /**
             * Hammer & Shield
             */
            case MAESTRO:
            case FORTUNE_SEEKER:
                switch (getPlayerGrade(player)) {
                    case "S":
                        if (Rnd.get(0, 1) == 0) {
                            /**
                             * 6585 = Basalt Battlehammer (Health)
                             * 6377 = Imperial Crusader Shield
                             */
                            itemsIds = Arrays.asList(6585, 6377);
                        } else {
                            /**
                             * 9873 = Dynasty Cudgel (Health)
                             * 9441 = Dynasty Shield
                             */
                            itemsIds = Arrays.asList(9873, 9441);
                        }
                        break;

                    case "S80":
                        /**
                         * 10454 = Icarus Hammer (Rsk. Focus)
                         * 9441 = Dynasty Shield
                         */
                        itemsIds = Arrays.asList(10454, 9441);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14137 = Vesper Avenger (Health)
                             * 13471 = Vesper Shield
                             */
                            itemsIds = Arrays.asList(14137, 13471);
                        } else if (randomNumber == 1) {
                            /**
                             * 15835 = Vigwik Axe (Health)
                             * 15604 = Vorpal Shield
                             */
                            itemsIds = Arrays.asList(15835, 15604);
                        } else if (randomNumber == 2) {
                            /**
                             * 15879 = Eversor Mace (Health)
                             * 15587 = Elegia Shield
                             */
                            itemsIds = Arrays.asList(15879, 15587);
                        }
                        break;
                }
                break;

            /**
             * Bow
             */
            case SAGITTARIUS:
            case MOONLIGHT_SENTINEL:
            case GHOST_SENTINEL:
                switch (getPlayerGrade(player)) {
                    case "S":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 6594 = Shining Bow (Focus)
                             */
                            itemsIds = Arrays.asList(6594);
                        } else if (randomNumber == 1) {
                            /**
                             * 7577 = Draconic Bow (Focus)
                             */
                            itemsIds = Arrays.asList(7577);
                        } else if (randomNumber == 2) {
                            /**
                             * 9865 = Dynasty Bow (Focus)
                             */
                            itemsIds = Arrays.asList(9865);
                        }
                        break;

                    case "S80":
                        /**
                         * 10445 = Icarus Spitter (Focus)
                         */
                        itemsIds = Arrays.asList(10445);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14149 = Vesper Thrower (Focus)
                             */
                            itemsIds = Arrays.asList(14149);
                        } else if (randomNumber == 1) {
                            /**
                             * 15859 = Skull Carnium Bow (Focus)
                             */
                            itemsIds = Arrays.asList(15859);
                        } else if (randomNumber == 2) {
                            /**
                             * 15093 = Recurve Thorne Bow (Focus)
                             */
                            itemsIds = Arrays.asList(15093);
                        }
                        break;
                }
                break;
            /**
             * Daggers
             */
            case ADVENTURER:
            case WIND_RIDER:
            case GHOST_HUNTER:
                switch (getPlayerGrade(player)) {
                    case "S":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 6590 = Angel Slayer (Critical Damage)
                             */
                            itemsIds = Arrays.asList(6590);
                        } else if (randomNumber == 1) {
                            /**
                             * 9868 = Dynasty Knife (Critical Damage)
                             */
                            itemsIds = Arrays.asList(9868);
                        } else if (randomNumber == 2) {
                            /**
                             * 13882 = Dynasty Dual Daggers
                             */
                            itemsIds = Arrays.asList(13882);
                        }
                        break;

                    case "S80":
                        randomNumber = Rnd.get(0, 1);
                        if (randomNumber == 0) {
                            /**
                             * 10448 = Icarus Disperser (Critical Damage)
                             */
                            itemsIds = Arrays.asList(10448);
                        } else if (randomNumber == 1) {
                            /**
                             * 13883 = Icarus Dual Daggers
                             */
                            itemsIds = Arrays.asList(13883);
                        }
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 5);
                        if (randomNumber == 0) {
                            /**
                             * 14127 = Vesper Shaper (Critical Damage)
                             */
                            itemsIds = Arrays.asList(14127);
                        } else if (randomNumber == 1) {
                            /**
                             * 15834 = Skull Edge (Critical Damage)
                             */
                            itemsIds = Arrays.asList(15834);
                        } else if (randomNumber == 2) {
                            /**
                             * 15875 = Mamba Edge (Critical Damage)
                             */
                            itemsIds = Arrays.asList(15875);
                        } else if (randomNumber == 3) {
                            /**
                             * 13884 = Vesper Dual Daggers
                             */
                            itemsIds = Arrays.asList(13884);
                        } else if (randomNumber == 4) {
                            /**
                             * 16152 = Skull Edge Dual Daggers
                             */
                            itemsIds = Arrays.asList(16152);
                        } else if (randomNumber == 5) {
                            /**
                             * 16156 = Mamba Edge Dual Daggers
                             */
                            itemsIds = Arrays.asList(16156);
                        }
                        break;
                }
                break;

            /**
             * Fist
             */
            case GRAND_KHAVATARI:
                switch (getPlayerGrade(player)) {
                    case "S":
                        randomNumber = Rnd.get(0, 1);
                        if (randomNumber == 0) {
                            /**
                             * 6604 = Demon Splinter (Critical Stun)
                             */
                            itemsIds = Arrays.asList(6604);
                        } else if (randomNumber == 1) {
                            /**
                             * 9879 = Dynasty Bagh-Nakh (Focus)
                             */
                            itemsIds = Arrays.asList(9879);
                        }
                        break;

                    case "S80":
                        /**
                         * 10459 = Icarus Hand (Focus)
                         */
                        itemsIds = Arrays.asList(10459);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14132 = Vesper Fighter (Critical Stun)
                             */
                            itemsIds = Arrays.asList(14132);
                        } else if (randomNumber == 1) {
                            /**
                             * 15845 = Octo Claw (Critical Stun)
                             */
                            itemsIds = Arrays.asList(15845);
                        } else if (randomNumber == 2) {
                            /**
                             * 15886 = Jade Claw (Critical Stun)
                             */
                            itemsIds = Arrays.asList(15886);
                        }
                        break;
                }
                break;

            /**
             * Crossbow
             */
            case TRICKSTER:
                switch (getPlayerGrade(player)) {
                    case "S":
                        randomNumber = Rnd.get(0, 1);
                        if (randomNumber == 0) {
                            /**
                             * 9374 = Sarunga (Focus)
                             */
                            itemsIds = Arrays.asList(9374);
                        } else if (randomNumber == 1) {
                            /**
                             * 9387 = Dynasty Crossbow (Focus)
                             */
                            itemsIds = Arrays.asList(9387);
                        }
                        break;

                    case "S80":
                        /**
                         * 10469 = Icarus Shooter (Focus)
                         */
                        itemsIds = Arrays.asList(10469);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14155 = Vesper Shooter (Focus)
                             */
                            itemsIds = Arrays.asList(14155);
                        } else if (randomNumber == 1) {
                            /**
                             * 15868 = Dominion Crossbow (Focus)
                             */
                            itemsIds = Arrays.asList(15868);
                        } else if (randomNumber == 2) {
                            /**
                             * 15912 = Thorne Crossbow (Focus)
                             */
                            itemsIds = Arrays.asList(15912);
                        }
                        break;
                }
                break;

            /**
             * Ancient Sword
             */
            case DOOMBRINGER:
                switch (getPlayerGrade(player)) {
                    case "S":
                        randomNumber = Rnd.get(0, 1);
                        if (randomNumber == 0) {
                            /**
                             * 9371 = Gran (Focus)
                             */
                            itemsIds = Arrays.asList(9371);
                        } else if (randomNumber == 1) {
                            /**
                             * 9381 = Dynasty Ancient Sword (Focus)
                             */
                            itemsIds = Arrays.asList(9381);
                        }
                        break;

                    case "S80":
                        /**
                         * 10464 = Icarus Wingblade (Focus)
                         */
                        itemsIds = Arrays.asList(10464);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14159 = Vesper Nagan (Focus)
                             */
                            itemsIds = Arrays.asList(14159);
                        } else if (randomNumber == 1) {
                            /**
                             * 15866 = Finale Blade (Focus)
                             */
                            itemsIds = Arrays.asList(15866);
                        } else if (randomNumber == 2) {
                            /**
                             * 15907 = Pyseal Blade (Focus)
                             */
                            itemsIds = Arrays.asList(15907);
                        }
                        break;
                }
                break;

            /**
             * Double Handed Swords
             */
            case TITAN:
                switch (getPlayerGrade(player)) {
                    case "S":
                        randomNumber = Rnd.get(0, 1);
                        if (randomNumber == 0) {
                            /**
                             * 6607 = Heavens Divider (Focus)
                             */
                            itemsIds = Arrays.asList(6607);
                        } else if (randomNumber == 1) {
                            /**
                             * 9857 = Dynasty Blade (Focus)
                             */
                            itemsIds = Arrays.asList(9857);
                        }
                        break;

                    case "S80":
                        /**
                         * 10437 = Icarus Heavy Arms (Focus)
                         */
                        itemsIds = Arrays.asList(10437);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14123 = Vesper Slasher (Focus)
                             */
                            itemsIds = Arrays.asList(14123);
                        } else if (randomNumber == 1) {
                            /**
                             * 15842 = Feather Eye Blade (Focus)
                             */
                            itemsIds = Arrays.asList(15842);
                        } else if (randomNumber == 2) {
                            /**
                             * 15883 = Lava Saw (Focus)
                             */
                            itemsIds = Arrays.asList(15883);
                        }
                        break;
                }
                break;

            /**
             * Single Handed Swords
             */
            case MALE_SOUL_HOUND:
            case FEMALE_SOUL_HOUND:
                switch (getPlayerGrade(player)) {
                    case "S":
                        randomNumber = Rnd.get(0, 1);
                        if (randomNumber == 0) {
                            /**
                             * 9367 = Laevateinn (Focus)
                             */
                            itemsIds = Arrays.asList(9367);
                        } else if (randomNumber == 1) {
                            /**
                             * 9377 = Dynasty Rapier (Focus)
                             */
                            itemsIds = Arrays.asList(9377);
                        }
                        break;

                    case "S80":
                        /**
                         * 10461 = Icarus Stinger (Focus)
                         */
                        itemsIds = Arrays.asList(10461);
                        break;

                    case "S84":
                        randomNumber = Rnd.get(0, 2);
                        if (randomNumber == 0) {
                            /**
                             * 14153 = Vesper Pincer (Focus)
                             */
                            itemsIds = Arrays.asList(14153);
                        } else if (randomNumber == 1) {
                            /**
                             * 15863 = Gemtail Rapier (Focus)
                             */
                            itemsIds = Arrays.asList(15863);
                        } else if (randomNumber == 2) {
                            /**
                             * 15904 = Heavenstare Rapier (Focus)
                             */
                            itemsIds = Arrays.asList(15904);
                        }
                        break;
                }
                break;

                //TODO: Add Judicator class

        }
        for (int id : itemsIds) {
            player.getInventory().addItem("Weapon", id, 1, player, null);
            L2ItemInstance item = player.getInventory().getItemByItemId(id);

            if(randomlyEnchant) {
                item.setEnchantLevel(Rnd.get(7, 20));
            }

            player.getInventory().equipItemAndRecord(item);
            player.getInventory().reloadEquippedItems();
        }
    }

    public static List<ClassId> getThirdClasses() {
        // todo: add summoners and kamael classes
        List<ClassId> classes = new ArrayList<>();

        /*
         * classes.add(ClassId.EVAS_SAINT); classes.add(ClassId.SHILLIEN_TEMPLAR);
         * classes.add(ClassId.SPECTRAL_DANCER); classes.add(ClassId.GHOST_HUNTER);
         *
         * classes.add(ClassId.PHOENIX_KNIGHT);
         * classes.add(ClassId.HELL_KNIGHT);
         *
         * classes.add(ClassId.HIEROPHANT); classes.add(ClassId.EVAS_TEMPLAR);
         * classes.add(ClassId.SWORD_MUSE);
         *
         * classes.add(ClassId.DOOMCRYER); classes.add(ClassId.FORTUNE_SEEKER);
         * classes.add(ClassId.MAESTRO);
         */

        // classes.add(ClassId.ARCANA_LORD);
        // classes.add(ClassId.ELEMENTAL_MASTER);
        // classes.add(ClassId.SPECTRAL_MASTER);
        // classes.add(ClassId.SHILLIEN_SAINT);

        classes.add(ClassId.SAGITTARIUS);
        classes.add(ClassId.MOONLIGHT_SENTINEL);
        classes.add(ClassId.ARCHMAGE);
//        classes.add(ClassId.SOULTAKER);
        classes.add(ClassId.MYSTIC_MUSE);
        classes.add(ClassId.STORM_SCREAMER);
        classes.add(ClassId.GHOST_SENTINEL);
//        classes.add(ClassId.ADVENTURER);
//        classes.add(ClassId.WIND_RIDER);
//        classes.add(ClassId.DOMINATOR);
//        classes.add(ClassId.TITAN);
//        classes.add(ClassId.CARDINAL);
//        classes.add(ClassId.DUELIST);
//        classes.add(ClassId.GRAND_KHAVATARI);
//        classes.add(ClassId.DREADNOUGHT);

        return classes;
    }

    public static Map<ClassId, Class<? extends FakePlayerAI>> getAllAIs() {
        Map<ClassId, Class<? extends FakePlayerAI>> ais = new HashMap<>();
        ais.put(ClassId.STORM_SCREAMER, StormScreamerAI.class);
        ais.put(ClassId.MYSTIC_MUSE, MysticMuseAI.class);
        ais.put(ClassId.ARCHMAGE, ArchmageAI.class);
        ais.put(ClassId.SOULTAKER, SoultakerAI.class);
        ais.put(ClassId.SAGITTARIUS, SagittariusAI.class);
        ais.put(ClassId.MOONLIGHT_SENTINEL, MoonlightSentinelAI.class);
        ais.put(ClassId.GHOST_SENTINEL, GhostSentinelAI.class);
        ais.put(ClassId.ADVENTURER, AdventurerAI.class);
        ais.put(ClassId.WIND_RIDER, WindRiderAI.class);
        ais.put(ClassId.GHOST_HUNTER, GhostHunterAI.class);
        ais.put(ClassId.DOMINATOR, DominatorAI.class);
        ais.put(ClassId.TITAN, TitanAI.class);
        ais.put(ClassId.CARDINAL, CardinalAI.class);
        ais.put(ClassId.DUELIST, DuelistAI.class);
        ais.put(ClassId.GRAND_KHAVATARI, GrandKhavatariAI.class);
        ais.put(ClassId.DREADNOUGHT, DreadnoughtAI.class);

        return ais;
    }

    // Todo: check the param "race" and remove him if not needed
    public static PcAppearance getRandomAppearance(Race race) {
        Boolean randomSex = Rnd.get(0, 1) == 0;
        int hairStyle = Rnd.get(0, randomSex == false ? 4 : 6);
        int hairColor = Rnd.get(0, 3);
        int faceId = Rnd.get(0, 2);

        return new PcAppearance((byte) faceId, (byte) hairColor, (byte) hairStyle, randomSex);
    }

    public static void setLevel(FakePlayer player, int level) {
        if (level >= 1 && level <= ExperienceData.getInstance().getMaxLevel()) {
            long pXp = player.getExp();
            long tXp = ExperienceData.getInstance().getExpForLevel(level);

            if (pXp > tXp) {
                player.removeExpAndSp(pXp - tXp, 0);

            } else if (pXp < tXp) {
                player.addExpAndSp(tXp - pXp, 0);
            }
        }
    }

    public static Class<? extends FakePlayerAI> getAIbyClassId(ClassId classId) {
        Class<? extends FakePlayerAI> ai = getAllAIs().get(classId);
        if (ai == null) {
            return FallbackAI.class;
        }

        return ai;
    }
}

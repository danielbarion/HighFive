package com.vert.fakeplayer.helpers;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.data.xml.impl.ExperienceData;
import com.l2jmobius.gameserver.data.xml.impl.PlayerTemplateData;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.idfactory.IdFactory;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Decoy;
import com.l2jmobius.gameserver.model.actor.appearance.PcAppearance;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.templates.L2PcTemplate;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;

import com.vert.fakeplayer.FakePlayer;
import com.vert.fakeplayer.FakePlayerNameManager;
import com.vert.fakeplayer.ai.FakePlayerAI;
import com.vert.fakeplayer.ai.FallbackAI;
import com.vert.fakeplayer.ai.occupations.*;

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
        return 800;
    }



    public static FakePlayer createFakePlayer(String occupation) {
        int objectId = IdFactory.getInstance().getNextId();
        String accountName = "AutoPilot";
        String playerName = FakePlayerNameManager.INSTANCE.getRandomAvailableName();

        ClassId classId;

        System.out.println(occupation);

        switch (occupation) {
            case "Sagittarius":
                classId = ClassId.SAGITTARIUS;
                break;
            case "MoonlightSent":
                classId = ClassId.MOONLIGHT_SENTINEL;
                break;
            default:
                classId = getThirdClasses().get(Rnd.get(0, getThirdClasses().size() - 1));
                break;
        }

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
        List<Integer> itemIds = new ArrayList<>();
        switch (player.getClassId()) {
            case FORTUNE_SEEKER:
            case GHOST_HUNTER:
            case WIND_RIDER:
            case ADVENTURER:
                itemIds = Arrays.asList(6590);
                break;
            case SAGITTARIUS:
            case MOONLIGHT_SENTINEL:
            case GHOST_SENTINEL:
                itemIds = Arrays.asList(7577);
                break;
            case PHOENIX_KNIGHT:
            case SWORD_MUSE:
            case HELL_KNIGHT:
            case EVA_TEMPLAR:
            case SHILLIEN_TEMPLAR:
                itemIds = Arrays.asList(6583, 6377);
                break;
            case MAESTRO:
                itemIds = Arrays.asList(6585, 6377);
                break;
            case TITAN:
                itemIds = Arrays.asList(6607);
                break;
            case DUELIST:
            case SPECTRAL_DANCER:
                itemIds = Arrays.asList(6580);
                break;
            case DREADNOUGHT:
                itemIds = Arrays.asList(6599);
                break;
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
                itemIds = Arrays.asList(6608);
                break;
            case GRAND_KHAVATARI:
                itemIds = Arrays.asList(6602);
                break;
            default:
                break;
        }
        for (int id : itemIds) {
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
//        classes.add(ClassId.ARCHMAGE);
//        classes.add(ClassId.SOULTAKER);
//        classes.add(ClassId.MYSTIC_MUSE);
//        classes.add(ClassId.STORM_SCREAMER);
//        classes.add(ClassId.GHOST_SENTINEL);
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

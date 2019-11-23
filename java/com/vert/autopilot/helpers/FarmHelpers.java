package com.vert.autopilot.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vert
 */
public class FarmHelpers {
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

    public static Map<String, String> getMappedPlaces() {
        Map<String, String> mappedPlaces = new HashMap<>();

//        ArrayList

//        mappedPlaces.put("test", );

        return mappedPlaces;
    }

}

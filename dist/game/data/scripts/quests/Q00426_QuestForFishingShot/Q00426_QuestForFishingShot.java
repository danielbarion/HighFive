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
package quests.Q00426_QuestForFishingShot;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Quest for Fishing Shot (426)<br>
 * @author Zealar
 */
public class Q00426_QuestForFishingShot extends Quest
{
	private static final int[] NPC = new int[]
	{
		31562, // Klufe
		31563, // Perelin
		31564, // Mishini
		31565, // Ogord
		31566, // Ropfi
		31567, // Bleaker
		31568, // Pamfus
		31569, // Cyano
		31570, // Lanosco
		31571, // Hufs
		31572, // O'Fulle
		31573, // Monakan
		31574, // Willie
		31575, // Litulon
		31576, // Berix
		31577, // Linnaeus
		31578, // Hilgendorf
		31579, // Klaus
		31696, // Platis
		31697, // Eindarkner
		31989, // Batidae
		32007, // Galba
		32348, // Burang
	};
	private static final Map<Integer, ChanceReward> MOBS = new HashMap<>(250);
	static
	{
		MOBS.put(20005, new ChanceReward(45, 1)); // Imp Elder
		MOBS.put(20013, new ChanceReward(100, 1)); // Dryad
		MOBS.put(20016, new ChanceReward(100, 1)); // Stone Golem
		MOBS.put(20017, new ChanceReward(115, 1)); // Vuku Orc Fighter
		MOBS.put(20030, new ChanceReward(105, 1)); // Langk Lizardman
		MOBS.put(20132, new ChanceReward(70, 1)); // Werewolf
		MOBS.put(20038, new ChanceReward(135, 1)); // Venomous Spider
		MOBS.put(20044, new ChanceReward(125, 1)); // Lirein Elder
		MOBS.put(20046, new ChanceReward(100, 1)); // Stink Zombie
		MOBS.put(20047, new ChanceReward(100, 1)); // Sukar Wererat Leader
		MOBS.put(20050, new ChanceReward(140, 1)); // Arachnid Predator
		MOBS.put(20058, new ChanceReward(140, 1)); // Ol Mahum Guard
		MOBS.put(20063, new ChanceReward(160, 1)); // Ol Mahum Shooter
		MOBS.put(20066, new ChanceReward(170, 1)); // Ol Mahum Captain
		MOBS.put(20070, new ChanceReward(180, 1)); // Lesser Basilisk
		MOBS.put(20074, new ChanceReward(195, 1)); // Androscorpio
		MOBS.put(20077, new ChanceReward(205, 1)); // Androscorpio Hunter
		MOBS.put(20078, new ChanceReward(205, 1)); // Whispering Wind
		MOBS.put(20079, new ChanceReward(205, 1)); // Ant
		MOBS.put(20080, new ChanceReward(220, 1)); // Ant Captain
		MOBS.put(20081, new ChanceReward(370, 1)); // Ant Overseer
		MOBS.put(20083, new ChanceReward(245, 1)); // Granite Golem
		MOBS.put(20084, new ChanceReward(255, 1)); // Ant Patrol
		MOBS.put(20085, new ChanceReward(265, 1)); // Puncher
		MOBS.put(20087, new ChanceReward(565, 1)); // Ant Soldier
		MOBS.put(20088, new ChanceReward(605, 1)); // Ant Warrior Captain
		MOBS.put(20089, new ChanceReward(250, 1)); // Noble Ant
		MOBS.put(20100, new ChanceReward(85, 1)); // Skeleton Archer
		MOBS.put(20103, new ChanceReward(110, 1)); // Giant Spider
		MOBS.put(20105, new ChanceReward(110, 1)); // Dark Horror
		MOBS.put(20115, new ChanceReward(190, 1)); // Undine Noble
		MOBS.put(20120, new ChanceReward(20, 1)); // Wolf
		MOBS.put(20131, new ChanceReward(45, 1)); // Orc Grunt
		MOBS.put(20135, new ChanceReward(360, 1)); // Alligator
		MOBS.put(20157, new ChanceReward(235, 1)); // Marsh Stakato
		MOBS.put(20162, new ChanceReward(195, 1)); // Ogre
		MOBS.put(20176, new ChanceReward(280, 1)); // Wyrm
		MOBS.put(20211, new ChanceReward(170, 1)); // Ol Mahum Captain
		MOBS.put(20225, new ChanceReward(160, 1)); // Giant Mist Leech
		MOBS.put(20227, new ChanceReward(180, 1)); // Horror Mist Ripper
		MOBS.put(20230, new ChanceReward(260, 1)); // Marsh Stakato Worker
		MOBS.put(20232, new ChanceReward(245, 1)); // Marsh Stakato Soldier
		MOBS.put(20234, new ChanceReward(290, 1)); // Marsh Stakato Drone
		MOBS.put(20241, new ChanceReward(700, 1)); // Hunter Gargoyle
		MOBS.put(20267, new ChanceReward(215, 1)); // Breka Orc
		MOBS.put(20268, new ChanceReward(295, 1)); // Breka Orc Archer
		MOBS.put(20269, new ChanceReward(255, 1)); // Breka Orc Shaman
		MOBS.put(20270, new ChanceReward(365, 1)); // Breka Orc Overlord
		MOBS.put(20271, new ChanceReward(295, 1)); // Breka Orc Warrior
		MOBS.put(20286, new ChanceReward(700, 1)); // Hunter Gargoyle
		MOBS.put(20308, new ChanceReward(110, 1)); // Hook Spider
		MOBS.put(20312, new ChanceReward(45, 1)); // Rakeclaw Imp Hunter
		MOBS.put(20317, new ChanceReward(20, 1)); // Black Wolf
		MOBS.put(20324, new ChanceReward(85, 1)); // Goblin Brigand Lieutenant
		MOBS.put(20333, new ChanceReward(100, 1)); // Greystone Golem
		MOBS.put(20341, new ChanceReward(100, 1)); // Undead Slave
		MOBS.put(20346, new ChanceReward(85, 1)); // Darkstone Golem
		MOBS.put(20349, new ChanceReward(850, 1)); // Cave Bat
		MOBS.put(20356, new ChanceReward(165, 1)); // Langk Lizardman Leader
		MOBS.put(20357, new ChanceReward(140, 1)); // Langk Lizardman Lieutenant
		MOBS.put(20363, new ChanceReward(70, 1)); // Maraku Werewolf
		MOBS.put(20368, new ChanceReward(85, 1)); // Grave Keeper
		MOBS.put(20371, new ChanceReward(100, 1)); // Mist Terror
		MOBS.put(20386, new ChanceReward(85, 1)); // Balor Orc Fighter
		MOBS.put(20389, new ChanceReward(90, 1)); // Boogle Ratman
		MOBS.put(20403, new ChanceReward(110, 1)); // Hunter Tarantula
		MOBS.put(20404, new ChanceReward(95, 1)); // Silent Horror
		MOBS.put(20433, new ChanceReward(100, 1)); // Festering Bat
		MOBS.put(20436, new ChanceReward(140, 1)); // Ol Mahum Supplier
		MOBS.put(20448, new ChanceReward(45, 1)); // Utuku Orc Grunt
		MOBS.put(20456, new ChanceReward(20, 1)); // Ashen Wolf
		MOBS.put(20463, new ChanceReward(85, 1)); // Dungeon Skeleton Archer
		MOBS.put(20470, new ChanceReward(45, 1)); // Kaboo Orc Grunt
		MOBS.put(20471, new ChanceReward(85, 1)); // Kaboo Orc Fighter
		MOBS.put(20475, new ChanceReward(20, 1)); // Kasha Wolf
		MOBS.put(20478, new ChanceReward(110, 1)); // Kasha Blade Spider
		MOBS.put(20487, new ChanceReward(90, 1)); // Kuruka Ratman
		MOBS.put(20511, new ChanceReward(100, 1)); // Pitchstone Golem
		MOBS.put(20525, new ChanceReward(20, 1)); // Gray Wolf
		MOBS.put(20528, new ChanceReward(100, 1)); // Goblin Lord
		MOBS.put(20536, new ChanceReward(15, 1)); // Elder Brown Keltir
		MOBS.put(20537, new ChanceReward(15, 1)); // Elder Red Keltir
		MOBS.put(20538, new ChanceReward(15, 1)); // Elder Prairie Keltir
		MOBS.put(20539, new ChanceReward(15, 1)); // Elder Longtail Keltir
		MOBS.put(20544, new ChanceReward(15, 1)); // Elder Keltir
		MOBS.put(20550, new ChanceReward(300, 1)); // Guardian Basilisk
		MOBS.put(20551, new ChanceReward(300, 1)); // Road Scavenger
		MOBS.put(20552, new ChanceReward(650, 1)); // Fettered Soul
		MOBS.put(20553, new ChanceReward(335, 1)); // Windsus
		MOBS.put(20554, new ChanceReward(390, 1)); // Grandis
		MOBS.put(20555, new ChanceReward(350, 1)); // Giant Fungus
		MOBS.put(20557, new ChanceReward(390, 1)); // Dire Wyrm
		MOBS.put(20559, new ChanceReward(420, 1)); // Rotting Golem
		MOBS.put(20560, new ChanceReward(440, 1)); // Trisalim Spider
		MOBS.put(20562, new ChanceReward(485, 1)); // Spore Zombie
		MOBS.put(20573, new ChanceReward(545, 1)); // Tarlk Basilisk
		MOBS.put(20575, new ChanceReward(645, 1)); // Oel Mahum Warrior
		MOBS.put(20630, new ChanceReward(350, 1)); // Taik Orc
		MOBS.put(20632, new ChanceReward(475, 1)); // Taik Orc Warrior
		MOBS.put(20634, new ChanceReward(960, 1)); // Taik Orc Captain
		MOBS.put(20636, new ChanceReward(495, 1)); // Forest of Mirrors Ghost
		MOBS.put(20638, new ChanceReward(540, 1)); // Forest of Mirrors Ghost
		MOBS.put(20641, new ChanceReward(680, 1)); // Harit Lizardman Grunt
		MOBS.put(20643, new ChanceReward(660, 1)); // Harit Lizardman Warrior
		MOBS.put(20644, new ChanceReward(645, 1)); // Harit Lizardman Shaman
		MOBS.put(20659, new ChanceReward(440, 1)); // Grave Wanderer
		MOBS.put(20661, new ChanceReward(575, 1)); // Hatar Ratman Thief
		MOBS.put(20663, new ChanceReward(525, 1)); // Hatar Hanishee
		MOBS.put(20665, new ChanceReward(680, 1)); // Taik Orc Supply
		MOBS.put(20667, new ChanceReward(730, 1)); // Farcran
		MOBS.put(20766, new ChanceReward(210, 1)); // Scout of the Plains
		MOBS.put(20781, new ChanceReward(270, 1)); // Delu Lizardman Shaman
		MOBS.put(20783, new ChanceReward(140, 1)); // Dread Wolf
		MOBS.put(20784, new ChanceReward(155, 1)); // Tasaba Lizardman
		MOBS.put(20786, new ChanceReward(170, 1)); // Lienrik
		MOBS.put(20788, new ChanceReward(325, 1)); // Rakul
		MOBS.put(20790, new ChanceReward(390, 1)); // Dailaon
		MOBS.put(20792, new ChanceReward(620, 1)); // Farhite
		MOBS.put(20794, new ChanceReward(635, 1)); // Blade Stakato
		MOBS.put(20796, new ChanceReward(640, 1)); // Blade Stakato Warrior
		MOBS.put(20798, new ChanceReward(850, 1)); // Water Giant
		MOBS.put(20800, new ChanceReward(740, 1)); // Eva's Seeker
		MOBS.put(20802, new ChanceReward(900, 1)); // Theeder Mage
		MOBS.put(20804, new ChanceReward(775, 1)); // Crokian Lad
		MOBS.put(20806, new ChanceReward(805, 1)); // Crokian Lad Warrior
		MOBS.put(20833, new ChanceReward(455, 1)); // Zaken's Archer
		MOBS.put(20834, new ChanceReward(680, 1)); // Mardian
		MOBS.put(20836, new ChanceReward(785, 1)); // Pirate Zombie
		MOBS.put(20837, new ChanceReward(835, 1)); // Tainted Ogre
		MOBS.put(20839, new ChanceReward(430, 1)); // Unpleasant Humming
		MOBS.put(20841, new ChanceReward(460, 1)); // Fiend Archer
		MOBS.put(20845, new ChanceReward(605, 1)); // Pirate Zombie Captain
		MOBS.put(20847, new ChanceReward(570, 1)); // Veil Master
		MOBS.put(20849, new ChanceReward(585, 1)); // Light Worm
		MOBS.put(20936, new ChanceReward(290, 1)); // Tanor Silenos
		MOBS.put(20937, new ChanceReward(315, 1)); // Tanor Silenos Grunt
		MOBS.put(20939, new ChanceReward(385, 1)); // Tanor Silenos Warrior
		MOBS.put(20940, new ChanceReward(500, 1)); // Tanor Silenos Shaman
		MOBS.put(20941, new ChanceReward(460, 1)); // Tanor Silenos Chieftain
		MOBS.put(20943, new ChanceReward(345, 1)); // Nightmare Keeper
		MOBS.put(20944, new ChanceReward(335, 1)); // Nightmare Lord
		MOBS.put(21100, new ChanceReward(125, 1)); // Langk Lizardman Sentinel
		MOBS.put(21101, new ChanceReward(155, 1)); // Langk Lizardman Shaman
		MOBS.put(21103, new ChanceReward(215, 1)); // Roughly Hewn Rock Golem
		MOBS.put(21105, new ChanceReward(310, 1)); // Delu Lizardman Special Agent
		MOBS.put(21107, new ChanceReward(600, 1)); // Delu Lizardman Commander
		MOBS.put(21117, new ChanceReward(120, 1)); // Kasha Imp
		MOBS.put(21023, new ChanceReward(170, 1)); // Sobbing Wind
		MOBS.put(21024, new ChanceReward(175, 1)); // Babbling Wind
		MOBS.put(21025, new ChanceReward(185, 1)); // Giggling Wind
		MOBS.put(21026, new ChanceReward(200, 1)); // Singing Wind
		MOBS.put(21034, new ChanceReward(195, 1)); // Ogre
		MOBS.put(21125, new ChanceReward(12, 1)); // Northern Trimden
		MOBS.put(21263, new ChanceReward(650, 1)); // Ol Mahum Transcender
		MOBS.put(21520, new ChanceReward(880, 1)); // Eye of Splendor
		MOBS.put(21526, new ChanceReward(970, 1)); // Wisdom of Splendor
		MOBS.put(21536, new ChanceReward(985, 1)); // Crown of Splendor
		MOBS.put(21602, new ChanceReward(555, 1)); // Zaken's Pikeman
		MOBS.put(21603, new ChanceReward(750, 1)); // Zaken's Pikeman
		MOBS.put(21605, new ChanceReward(620, 1)); // Zaken's Archer
		MOBS.put(21606, new ChanceReward(875, 1)); // Zaken's Archer
		MOBS.put(21611, new ChanceReward(590, 1)); // Unpleasant Humming
		MOBS.put(21612, new ChanceReward(835, 1)); // Unpleasant Humming
		MOBS.put(21617, new ChanceReward(615, 1)); // Fiend Archer
		MOBS.put(21618, new ChanceReward(875, 1)); // Fiend Archer
		MOBS.put(21635, new ChanceReward(775, 1)); // Veil Master
		MOBS.put(21638, new ChanceReward(165, 1)); // Dread Wolf
		MOBS.put(21639, new ChanceReward(185, 1)); // Tasaba Lizardman
		MOBS.put(21641, new ChanceReward(195, 1)); // Ogre
		MOBS.put(21644, new ChanceReward(170, 1)); // Lienrik
		MOBS.put(22231, new ChanceReward(10, 1)); // Dominant Grey Keltir
		MOBS.put(22233, new ChanceReward(20, 1)); // Dominant Black Wolf
		MOBS.put(22234, new ChanceReward(30, 1)); // Green Goblin
		MOBS.put(22235, new ChanceReward(35, 1)); // Mountain Werewolf
		MOBS.put(22237, new ChanceReward(55, 1)); // Mountain Fungus
		MOBS.put(22238, new ChanceReward(70, 1)); // Mountain Werewolf Chief
		MOBS.put(22241, new ChanceReward(80, 1)); // Colossus
		MOBS.put(22244, new ChanceReward(90, 1)); // Crimson Spider
		MOBS.put(22247, new ChanceReward(90, 1)); // Grotto Golem
		MOBS.put(22250, new ChanceReward(90, 1)); // Grotto Leopard
		MOBS.put(22252, new ChanceReward(95, 1)); // Grotto Grizzly
		MOBS.put(20579, new ChanceReward(420, 2)); // Leto Lizardman Soldier
		MOBS.put(20639, new ChanceReward(280, 2)); // Mirror
		MOBS.put(20646, new ChanceReward(145, 2)); // Halingka
		MOBS.put(20648, new ChanceReward(120, 2)); // Paliote
		MOBS.put(20650, new ChanceReward(460, 2)); // Kranrot
		MOBS.put(20651, new ChanceReward(260, 2)); // Gamlin
		MOBS.put(20652, new ChanceReward(335, 2)); // Leogul
		MOBS.put(20657, new ChanceReward(630, 2)); // Lesser Giant Mage
		MOBS.put(20658, new ChanceReward(570, 2)); // Lesser Giant Elder
		MOBS.put(20808, new ChanceReward(50, 2)); // Nos Lad
		MOBS.put(20809, new ChanceReward(865, 2)); // Ghost of the Tower
		MOBS.put(20832, new ChanceReward(700, 2)); // Zaken's Pikeman
		MOBS.put(20979, new ChanceReward(980, 2)); // Elmoradan's Maid
		MOBS.put(20991, new ChanceReward(665, 2)); // Swamp Tribe
		MOBS.put(20994, new ChanceReward(590, 2)); // Garden Guard Leader
		MOBS.put(21261, new ChanceReward(170, 2)); // Ol Mahum Transcender
		MOBS.put(21263, new ChanceReward(795, 2)); // Ol Mahum Transcender
		MOBS.put(21508, new ChanceReward(100, 2)); // Splinter Stakato
		MOBS.put(21510, new ChanceReward(280, 2)); // Splinter Stakato Soldier
		MOBS.put(21511, new ChanceReward(995, 2)); // Splinter Stakato Drone
		MOBS.put(21512, new ChanceReward(995, 2)); // Splinter Stakato Drone
		MOBS.put(21514, new ChanceReward(185, 2)); // Needle Stakato Worker
		MOBS.put(21516, new ChanceReward(495, 2)); // Needle Stakato Drone
		MOBS.put(21517, new ChanceReward(495, 2)); // Needle Stakato Drone
		MOBS.put(21518, new ChanceReward(255, 2)); // Frenzy Stakato Soldier
		MOBS.put(21636, new ChanceReward(950, 2)); // Veil Master
		MOBS.put(20655, new ChanceReward(110, 3)); // Lesser Giant Shooter
		MOBS.put(20656, new ChanceReward(150, 3)); // Lesser Giant Scout
		MOBS.put(20772, new ChanceReward(105, 3)); // Barif's Pet
		MOBS.put(20810, new ChanceReward(50, 3)); // Hallate's Seer
		MOBS.put(20812, new ChanceReward(490, 3)); // Archer of Despair
		MOBS.put(20814, new ChanceReward(775, 3)); // Blader of Despair
		MOBS.put(20816, new ChanceReward(875, 3)); // Hallate's Royal Guard
		MOBS.put(20819, new ChanceReward(280, 3)); // Archer of Abyss
		MOBS.put(20955, new ChanceReward(670, 3)); // Ghostly Warrior
		MOBS.put(20978, new ChanceReward(555, 3)); // Elmoradan's Archer Escort
		MOBS.put(21058, new ChanceReward(355, 3)); // Beast Lord
		MOBS.put(21060, new ChanceReward(45, 3)); // Beast Seer
		MOBS.put(21075, new ChanceReward(110, 3)); // Slaughter Bathin
		MOBS.put(21078, new ChanceReward(610, 3)); // Magus Valac
		MOBS.put(21081, new ChanceReward(955, 3)); // Power Angel Amon
		MOBS.put(21264, new ChanceReward(920, 3)); // Ol Mahum Transcender
		MOBS.put(20815, new ChanceReward(205, 4)); // Hound Dog of Hallate
		MOBS.put(20822, new ChanceReward(100, 4)); // Hallate's Maid
		MOBS.put(20824, new ChanceReward(665, 4)); // Hallate's Commander
		MOBS.put(20825, new ChanceReward(620, 4)); // Hallate's Inspector
		MOBS.put(20983, new ChanceReward(205, 4)); // Binder
		MOBS.put(21314, new ChanceReward(145, 4)); // Hot Springs Bandersnatchling
		MOBS.put(21316, new ChanceReward(235, 4)); // Hot Springs Flava
		MOBS.put(21318, new ChanceReward(280, 4)); // Hot Springs Antelope
		MOBS.put(21320, new ChanceReward(355, 4)); // Hot Springs Yeti
		MOBS.put(21322, new ChanceReward(430, 4)); // Hot Springs Bandersnatch
		MOBS.put(21376, new ChanceReward(280, 4)); // Scarlet Stakato Worker
		MOBS.put(21378, new ChanceReward(375, 4)); // Scarlet Stakato Noble
		MOBS.put(21380, new ChanceReward(375, 4)); // Tepra Scarab
		MOBS.put(21387, new ChanceReward(640, 4)); // Arimanes of Destruction
		MOBS.put(21393, new ChanceReward(935, 4)); // Magma Drake
		MOBS.put(21395, new ChanceReward(855, 4)); // Elder Lavasaurus
		MOBS.put(21652, new ChanceReward(375, 4)); // Scarlet Stakato Noble
		MOBS.put(21655, new ChanceReward(640, 4)); // Arimanes of Destruction
		MOBS.put(21657, new ChanceReward(935, 4)); // Magma Drake
		MOBS.put(20828, new ChanceReward(935, 5)); // Platinum Tribe Shaman
		MOBS.put(21061, new ChanceReward(530, 5)); // Hallate's Guardian
		MOBS.put(21069, new ChanceReward(825, 5)); // Platinum Guardian Prefect
		MOBS.put(21382, new ChanceReward(125, 5)); // Mercenary of Destruction
		MOBS.put(21384, new ChanceReward(400, 5)); // Necromancer of Destruction
		MOBS.put(21390, new ChanceReward(750, 5)); // Ashuras of Destruction
		MOBS.put(21654, new ChanceReward(400, 5)); // Necromancer of Destruction
		MOBS.put(21656, new ChanceReward(750, 5)); // Ashuras of Destruction
		MOBS.put(22634, new ChanceReward(280, 4)); // Scarlet Stakato Worker
		MOBS.put(22636, new ChanceReward(375, 4)); // Scarlet Stakato Noble
		MOBS.put(22638, new ChanceReward(375, 4)); // Tepra Scarab
		MOBS.put(22640, new ChanceReward(125, 5)); // Mercenary of Destruction
		MOBS.put(22644, new ChanceReward(640, 4)); // Arimanes of Destruction
		MOBS.put(22646, new ChanceReward(750, 5)); // Ashuras of Destruction
		MOBS.put(22649, new ChanceReward(935, 4)); // Magma Drake
	}
	private static final Map<Integer, ChanceReward> MOBS_SPECIAL = new HashMap<>(5);
	static
	{
		MOBS_SPECIAL.put(20829, new ChanceReward(115, 6)); // Platinum Tribe Overlord
		MOBS_SPECIAL.put(20859, new ChanceReward(890, 8)); // Guardian Angel
		MOBS_SPECIAL.put(21066, new ChanceReward(5, 5)); // Platinum Guardian Shaman
		MOBS_SPECIAL.put(21068, new ChanceReward(565, 11)); // Guardian Archangel
		MOBS_SPECIAL.put(21071, new ChanceReward(400, 12)); // Seal Archangel
	}
	private static final int SWEET_FLUID = 7586;
	
	public Q00426_QuestForFishingShot()
	{
		super(426);
		addStartNpc(NPC);
		addTalkId(NPC);
		addKillId(MOBS.keySet());
		registerQuestItems(SWEET_FLUID);
	}
	
	private static class ChanceReward
	{
		final int chance;
		final int reward;
		
		ChanceReward(int chance, int reward)
		{
			this.chance = chance;
			this.reward = reward;
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		switch (event)
		{
			case "QUEST_ACEPT":
			{
				qs.startQuest();
				return "03.htm";
			}
			case "1":
			{
				return "06.html";
			}
			case "2":
			{
				return "07.html";
			}
			case "3":
			{
				qs.exitQuest(true);
				return "08.html";
			}
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, -1, 2, npc);
		if (qs != null)
		{
			if (MOBS_SPECIAL.containsKey(npc.getId()))
			{
				if (Rnd.get(1000) <= MOBS_SPECIAL.get(npc.getId()).chance)
				{
					rewardItems(qs.getPlayer(), SWEET_FLUID, MOBS_SPECIAL.get(npc.getId()).reward + 1);
				}
				else
				{
					rewardItems(qs.getPlayer(), SWEET_FLUID, MOBS_SPECIAL.get(npc.getId()).reward);
				}
				playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
			else
			{
				if (Rnd.get(1000) <= MOBS.get(npc.getId()).chance)
				{
					rewardItems(qs.getPlayer(), SWEET_FLUID, MOBS.get(npc.getId()).reward);
					playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final String htmltext = getNoQuestMsg(player);
		final QuestState st = getQuestState(player, true);
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				return "01.htm";
			}
			case State.STARTED:
			{
				if (!hasQuestItems(player, SWEET_FLUID))
				{
					return "04.html";
				}
				return "05.html";
			}
		}
		return htmltext;
	}
}
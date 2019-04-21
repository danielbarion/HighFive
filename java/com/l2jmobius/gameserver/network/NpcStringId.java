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
package com.l2jmobius.gameserver.network;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.model.clientstrings.Builder;
import com.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;

public final class NpcStringId
{
	private static final Logger LOGGER = Logger.getLogger(NpcStringId.class.getName());
	private static final NSLocalisation[] EMPTY_NSL_ARRAY = new NSLocalisation[0];
	public static final NpcStringId[] EMPTY_ARRAY = new NpcStringId[0];
	
	private static Map<Integer, NpcStringId> VALUES = new HashMap<>();
	
	@ClientString(id = 1, message = "Hello! I am $s1. You are $s2, right? Hehehe")
	public static NpcStringId HELLO_I_AM_S1_YOU_ARE_S2_RIGHT_HEHEHE;
	
	@ClientString(id = 2, message = "$s1--$s2--$s3--$s4//$s5 Hehehe")
	public static NpcStringId S1_S2_S3_S4_S5_HEHEHE;
	
	@ClientString(id = 3, message = "none")
	public static NpcStringId NONE;
	
	@ClientString(id = 4, message = "none")
	public static NpcStringId NONE_2;
	
	@ClientString(id = 5, message = "Withdraw the fee for the next time at $s1/$s2 $s3")
	public static NpcStringId WITHDRAW_THE_FEE_FOR_THE_NEXT_TIME_AT_S1_S2_S3;
	
	@ClientString(id = 6, message = "(<font color='FFAABB'>$s1</font> Adena/$s2 Day(s))")
	public static NpcStringId FONT_COLOR_FFAABB_S1_FONT_ADENA_S2_DAY_S;
	
	@ClientString(id = 7, message = "$s1<a action='bypass -h menu_select?ask=-270&reply=$s2000'>Deactivate</a>$s3")
	public static NpcStringId S1_A_ACTION_BYPASS_H_MENU_SELECT_ASK_270_REPLY_S2000_DEACTIVATE_A_S3;
	
	@ClientString(id = 8, message = "Stage")
	public static NpcStringId STAGE;
	
	@ClientString(id = 9, message = "Stage $s1")
	public static NpcStringId STAGE_S1;
	
	@ClientString(id = 10, message = "$s1%%")
	public static NpcStringId S1;
	
	@ClientString(id = 101, message = "Letters of Love")
	public static NpcStringId LETTERS_OF_LOVE;
	
	@ClientString(id = 102, message = "Letters of Love (In Progress)")
	public static NpcStringId LETTERS_OF_LOVE_IN_PROGRESS;
	
	@ClientString(id = 103, message = "Letters of Love (Done)")
	public static NpcStringId LETTERS_OF_LOVE_DONE;
	
	@ClientString(id = 201, message = "What Women Want")
	public static NpcStringId WHAT_WOMEN_WANT;
	
	@ClientString(id = 202, message = "What Women Want (In Progress)")
	public static NpcStringId WHAT_WOMEN_WANT_IN_PROGRESS;
	
	@ClientString(id = 203, message = "What Women Want (Done)")
	public static NpcStringId WHAT_WOMEN_WANT_DONE;
	
	@ClientString(id = 301, message = "Will the Seal be Broken?")
	public static NpcStringId WILL_THE_SEAL_BE_BROKEN;
	
	@ClientString(id = 302, message = "Will the Seal be Broken? (In Progress)")
	public static NpcStringId WILL_THE_SEAL_BE_BROKEN_IN_PROGRESS;
	
	@ClientString(id = 303, message = "Will the Seal be Broken? (Done)")
	public static NpcStringId WILL_THE_SEAL_BE_BROKEN_DONE;
	
	@ClientString(id = 401, message = "Long Live the Pa'agrio Lord")
	public static NpcStringId LONG_LIVE_THE_PA_AGRIO_LORD;
	
	@ClientString(id = 402, message = "Long Live the Pa'agrio Lord (In Progress)")
	public static NpcStringId LONG_LIVE_THE_PA_AGRIO_LORD_IN_PROGRESS;
	
	@ClientString(id = 403, message = "Long Live the Pa'agrio Lord (Done)")
	public static NpcStringId LONG_LIVE_THE_PA_AGRIO_LORD_DONE;
	
	@ClientString(id = 501, message = "Miner's Favor")
	public static NpcStringId MINER_S_FAVOR;
	
	@ClientString(id = 502, message = "Miner's Favor (In Progress)")
	public static NpcStringId MINER_S_FAVOR_IN_PROGRESS;
	
	@ClientString(id = 503, message = "Miner's Favor (Done)")
	public static NpcStringId MINER_S_FAVOR_DONE;
	
	@ClientString(id = 601, message = "Step Into the Future")
	public static NpcStringId STEP_INTO_THE_FUTURE;
	
	@ClientString(id = 602, message = "Step Into the Future (In Progress)")
	public static NpcStringId STEP_INTO_THE_FUTURE_IN_PROGRESS;
	
	@ClientString(id = 603, message = "Step Into the Future (Done)")
	public static NpcStringId STEP_INTO_THE_FUTURE_DONE;
	
	@ClientString(id = 701, message = "A Trip Begins")
	public static NpcStringId A_TRIP_BEGINS;
	
	@ClientString(id = 702, message = "A Trip Begins (In Progress)")
	public static NpcStringId A_TRIP_BEGINS_IN_PROGRESS;
	
	@ClientString(id = 703, message = "A Trip Begins (Done)")
	public static NpcStringId A_TRIP_BEGINS_DONE;
	
	@ClientString(id = 801, message = "An Adventure Begins")
	public static NpcStringId AN_ADVENTURE_BEGINS;
	
	@ClientString(id = 802, message = "An Adventure Begins (In Progress)")
	public static NpcStringId AN_ADVENTURE_BEGINS_IN_PROGRESS;
	
	@ClientString(id = 803, message = "An Adventure Begins (Done)")
	public static NpcStringId AN_ADVENTURE_BEGINS_DONE;
	
	@ClientString(id = 901, message = "Into the City of Humans")
	public static NpcStringId INTO_THE_CITY_OF_HUMANS;
	
	@ClientString(id = 902, message = "Into the City of Humans (In Progress)")
	public static NpcStringId INTO_THE_CITY_OF_HUMANS_IN_PROGRESS;
	
	@ClientString(id = 903, message = "Into the City of Humans (Done)")
	public static NpcStringId INTO_THE_CITY_OF_HUMANS_DONE;
	
	@ClientString(id = 1001, message = "Into the World")
	public static NpcStringId INTO_THE_WORLD;
	
	@ClientString(id = 1002, message = "Into the World (In Progress)")
	public static NpcStringId INTO_THE_WORLD_IN_PROGRESS;
	
	@ClientString(id = 1003, message = "Into the World (Done)")
	public static NpcStringId INTO_THE_WORLD_DONE;
	
	@ClientString(id = 1101, message = "Secret meeting with Ketra Orcs")
	public static NpcStringId SECRET_MEETING_WITH_KETRA_ORCS;
	
	@ClientString(id = 1102, message = "Secret Meeting with Ketra Orcs (In Progress)")
	public static NpcStringId SECRET_MEETING_WITH_KETRA_ORCS_IN_PROGRESS;
	
	@ClientString(id = 1103, message = "Secret meeting with Ketra Orcs (Done)")
	public static NpcStringId SECRET_MEETING_WITH_KETRA_ORCS_DONE;
	
	@ClientString(id = 1201, message = "Secret meeting with Varka Silenos")
	public static NpcStringId SECRET_MEETING_WITH_VARKA_SILENOS;
	
	@ClientString(id = 1202, message = "Secret Meeting with Varka Silenos (In Progress)")
	public static NpcStringId SECRET_MEETING_WITH_VARKA_SILENOS_IN_PROGRESS;
	
	@ClientString(id = 1203, message = "Secret meeting with Varka Silenos (Done)")
	public static NpcStringId SECRET_MEETING_WITH_VARKA_SILENOS_DONE;
	
	@ClientString(id = 1301, message = "Parcel Delivery")
	public static NpcStringId PARCEL_DELIVERY;
	
	@ClientString(id = 1302, message = "Parcel Delivery (In Progress)")
	public static NpcStringId PARCEL_DELIVERY_IN_PROGRESS;
	
	@ClientString(id = 1303, message = "Parcel Delivery (Done)")
	public static NpcStringId PARCEL_DELIVERY_DONE;
	
	@ClientString(id = 1401, message = "Whereabouts of the Archaeologist")
	public static NpcStringId WHEREABOUTS_OF_THE_ARCHAEOLOGIST;
	
	@ClientString(id = 1402, message = "Whereabouts of the Archaeologist (In Progress)")
	public static NpcStringId WHEREABOUTS_OF_THE_ARCHAEOLOGIST_IN_PROGRESS;
	
	@ClientString(id = 1403, message = "Whereabouts of the Archaeologist (Done)")
	public static NpcStringId WHEREABOUTS_OF_THE_ARCHAEOLOGIST_DONE;
	
	@ClientString(id = 1501, message = "Sweet Whispers")
	public static NpcStringId SWEET_WHISPERS;
	
	@ClientString(id = 1502, message = "Sweet Whispers (In Progress)")
	public static NpcStringId SWEET_WHISPERS_IN_PROGRESS;
	
	@ClientString(id = 1503, message = "Sweet Whispers (Done)")
	public static NpcStringId SWEET_WHISPERS_DONE;
	
	@ClientString(id = 1601, message = "The Coming Darkness")
	public static NpcStringId THE_COMING_DARKNESS;
	
	@ClientString(id = 1602, message = "The Coming Darkness (In Progress)")
	public static NpcStringId THE_COMING_DARKNESS_IN_PROGRESS;
	
	@ClientString(id = 1603, message = "The Coming Darkness (Done)")
	public static NpcStringId THE_COMING_DARKNESS_DONE;
	
	@ClientString(id = 1701, message = "Light and Darkness")
	public static NpcStringId LIGHT_AND_DARKNESS;
	
	@ClientString(id = 1702, message = "Light and Darkness (In Progress)")
	public static NpcStringId LIGHT_AND_DARKNESS_IN_PROGRESS;
	
	@ClientString(id = 1703, message = "Light and Darkness (Done)")
	public static NpcStringId LIGHT_AND_DARKNESS_DONE;
	
	@ClientString(id = 1801, message = "Meeting with the Golden Ram")
	public static NpcStringId MEETING_WITH_THE_GOLDEN_RAM;
	
	@ClientString(id = 1802, message = "Meeting with the Golden Ram (In Progress)")
	public static NpcStringId MEETING_WITH_THE_GOLDEN_RAM_IN_PROGRESS;
	
	@ClientString(id = 1803, message = "Meeting with the Golden Ram (Done)")
	public static NpcStringId MEETING_WITH_THE_GOLDEN_RAM_DONE;
	
	@ClientString(id = 1901, message = "Go to the Pastureland")
	public static NpcStringId GO_TO_THE_PASTURELAND;
	
	@ClientString(id = 1902, message = "Go to the Pastureland (In Progress)")
	public static NpcStringId GO_TO_THE_PASTURELAND_IN_PROGRESS;
	
	@ClientString(id = 1903, message = "Go to the Pastureland (Done)")
	public static NpcStringId GO_TO_THE_PASTURELAND_DONE;
	
	@ClientString(id = 2001, message = "Bring Up With Love")
	public static NpcStringId BRING_UP_WITH_LOVE;
	
	@ClientString(id = 2002, message = "Bring Up With Love (In Progress)")
	public static NpcStringId BRING_UP_WITH_LOVE_IN_PROGRESS;
	
	@ClientString(id = 2003, message = "Bring Up With Love (Done)")
	public static NpcStringId BRING_UP_WITH_LOVE_DONE;
	
	@ClientString(id = 2004, message = "What did you just do to me?")
	public static NpcStringId WHAT_DID_YOU_JUST_DO_TO_ME;
	
	@ClientString(id = 2005, message = "Are you trying to tame me? Don't do that!")
	public static NpcStringId ARE_YOU_TRYING_TO_TAME_ME_DON_T_DO_THAT;
	
	@ClientString(id = 2006, message = "Don't give such a thing. You can endanger yourself!")
	public static NpcStringId DON_T_GIVE_SUCH_A_THING_YOU_CAN_ENDANGER_YOURSELF;
	
	@ClientString(id = 2007, message = "Yuck! What is this? It tastes terrible!")
	public static NpcStringId YUCK_WHAT_IS_THIS_IT_TASTES_TERRIBLE;
	
	@ClientString(id = 2008, message = "I'm hungry. Give me a little more, please.")
	public static NpcStringId I_M_HUNGRY_GIVE_ME_A_LITTLE_MORE_PLEASE;
	
	@ClientString(id = 2009, message = "What is this? Is this edible?")
	public static NpcStringId WHAT_IS_THIS_IS_THIS_EDIBLE;
	
	@ClientString(id = 2010, message = "Don't worry about me.")
	public static NpcStringId DON_T_WORRY_ABOUT_ME;
	
	@ClientString(id = 2011, message = "Thank you. That was delicious! ")
	public static NpcStringId THANK_YOU_THAT_WAS_DELICIOUS;
	
	@ClientString(id = 2012, message = "I think I am starting to like you!")
	public static NpcStringId I_THINK_I_AM_STARTING_TO_LIKE_YOU;
	
	@ClientString(id = 2013, message = "Eeeeek! Eeeeek!")
	public static NpcStringId EEEEEK_EEEEEK;
	
	@ClientString(id = 2014, message = "Don't keep trying to tame me. I don't want to be tamed.")
	public static NpcStringId DON_T_KEEP_TRYING_TO_TAME_ME_I_DON_T_WANT_TO_BE_TAMED;
	
	@ClientString(id = 2015, message = "It is just food to me. Although it may also be your hand.")
	public static NpcStringId IT_IS_JUST_FOOD_TO_ME_ALTHOUGH_IT_MAY_ALSO_BE_YOUR_HAND;
	
	@ClientString(id = 2016, message = "If I keep eating like this, won't I become fat? *chomp chomp*")
	public static NpcStringId IF_I_KEEP_EATING_LIKE_THIS_WON_T_I_BECOME_FAT_CHOMP_CHOMP;
	
	@ClientString(id = 2017, message = "Why do you keep feeding me?")
	public static NpcStringId WHY_DO_YOU_KEEP_FEEDING_ME;
	
	@ClientString(id = 2018, message = "Don't trust me. I'm afraid I may betray you later.")
	public static NpcStringId DON_T_TRUST_ME_I_M_AFRAID_I_MAY_BETRAY_YOU_LATER;
	
	@ClientString(id = 2019, message = "Grrrrr....")
	public static NpcStringId GRRRRR;
	
	@ClientString(id = 2020, message = "You brought this upon yourself...!")
	public static NpcStringId YOU_BROUGHT_THIS_UPON_YOURSELF;
	
	@ClientString(id = 2021, message = "I feel strange...! I keep having these evil thoughts...!")
	public static NpcStringId I_FEEL_STRANGE_I_KEEP_HAVING_THESE_EVIL_THOUGHTS;
	
	@ClientString(id = 2022, message = "Alas... so this is how it all ends...")
	public static NpcStringId ALAS_SO_THIS_IS_HOW_IT_ALL_ENDS;
	
	@ClientString(id = 2023, message = "I don't feel so good... Oh, my mind is very troubled...!")
	public static NpcStringId I_DON_T_FEEL_SO_GOOD_OH_MY_MIND_IS_VERY_TROUBLED;
	
	@ClientString(id = 2024, message = "$s1, so what do you think it is like to be tamed? ")
	public static NpcStringId S1_SO_WHAT_DO_YOU_THINK_IT_IS_LIKE_TO_BE_TAMED;
	
	@ClientString(id = 2025, message = "$s1, whenever I see spice, I think I will miss your hand that used to feed it to me.")
	public static NpcStringId S1_WHENEVER_I_SEE_SPICE_I_THINK_I_WILL_MISS_YOUR_HAND_THAT_USED_TO_FEED_IT_TO_ME;
	
	@ClientString(id = 2026, message = "$s1, don't go to the village. I don't have the strength to follow you.")
	public static NpcStringId S1_DON_T_GO_TO_THE_VILLAGE_I_DON_T_HAVE_THE_STRENGTH_TO_FOLLOW_YOU;
	
	@ClientString(id = 2027, message = "Thank you for trusting me, $s1. I hope I will be helpful to you.")
	public static NpcStringId THANK_YOU_FOR_TRUSTING_ME_S1_I_HOPE_I_WILL_BE_HELPFUL_TO_YOU;
	
	@ClientString(id = 2028, message = "$s1, will I be able to help you?")
	public static NpcStringId S1_WILL_I_BE_ABLE_TO_HELP_YOU;
	
	@ClientString(id = 2029, message = "I guess it's just my animal magnetism.")
	public static NpcStringId I_GUESS_IT_S_JUST_MY_ANIMAL_MAGNETISM;
	
	@ClientString(id = 2030, message = "Too much spicy food makes me sweat like a beast.")
	public static NpcStringId TOO_MUCH_SPICY_FOOD_MAKES_ME_SWEAT_LIKE_A_BEAST;
	
	@ClientString(id = 2031, message = "Animals need love too.")
	public static NpcStringId ANIMALS_NEED_LOVE_TOO;
	
	@ClientString(id = 2032, message = "What'd I miss? What'd I miss?")
	public static NpcStringId WHAT_D_I_MISS_WHAT_D_I_MISS;
	
	@ClientString(id = 2033, message = "I just know before this is over, I'm gonna need a lot of serious therapy.")
	public static NpcStringId I_JUST_KNOW_BEFORE_THIS_IS_OVER_I_M_GONNA_NEED_A_LOT_OF_SERIOUS_THERAPY;
	
	@ClientString(id = 2034, message = "I sense great wisdom in you... I'm an animal, and I got instincts.")
	public static NpcStringId I_SENSE_GREAT_WISDOM_IN_YOU_I_M_AN_ANIMAL_AND_I_GOT_INSTINCTS;
	
	@ClientString(id = 2035, message = "Remember, I'm here to help.")
	public static NpcStringId REMEMBER_I_M_HERE_TO_HELP;
	
	@ClientString(id = 2036, message = "Are we there yet?")
	public static NpcStringId ARE_WE_THERE_YET;
	
	@ClientString(id = 2037, message = "That really made me feel good to see that.")
	public static NpcStringId THAT_REALLY_MADE_ME_FEEL_GOOD_TO_SEE_THAT;
	
	@ClientString(id = 2038, message = "Oh, no, no, no, Nooooo!")
	public static NpcStringId OH_NO_NO_NO_NOOOOO;
	
	@ClientString(id = 2101, message = "Hidden Truth")
	public static NpcStringId HIDDEN_TRUTH;
	
	@ClientString(id = 2102, message = "Hidden Truth (In Progress)")
	public static NpcStringId HIDDEN_TRUTH_IN_PROGRESS;
	
	@ClientString(id = 2103, message = "Hidden Truth (Done)")
	public static NpcStringId HIDDEN_TRUTH_DONE;
	
	@ClientString(id = 2150, message = "Who awoke me?")
	public static NpcStringId WHO_AWOKE_ME;
	
	@ClientString(id = 2151, message = "My master has instructed me to be your guide, $s1.")
	public static NpcStringId MY_MASTER_HAS_INSTRUCTED_ME_TO_BE_YOUR_GUIDE_S1;
	
	@ClientString(id = 2152, message = "Please check this bookcase, $s1.")
	public static NpcStringId PLEASE_CHECK_THIS_BOOKCASE_S1;
	
	@ClientString(id = 2201, message = "Tragedy in Von Hellmann Forest")
	public static NpcStringId TRAGEDY_IN_VON_HELLMANN_FOREST;
	
	@ClientString(id = 2202, message = "Tragedy in Von Hellmann Forest (In Progress)")
	public static NpcStringId TRAGEDY_IN_VON_HELLMANN_FOREST_IN_PROGRESS;
	
	@ClientString(id = 2203, message = "Tragedy in Von Hellmann Forest (Done)")
	public static NpcStringId TRAGEDY_IN_VON_HELLMANN_FOREST_DONE;
	
	@ClientString(id = 2250, message = "Did you call me, $s1?")
	public static NpcStringId DID_YOU_CALL_ME_S1;
	
	@ClientString(id = 2251, message = "I'm confused! Maybe it's time to go back.")
	public static NpcStringId I_M_CONFUSED_MAYBE_IT_S_TIME_TO_GO_BACK;
	
	@ClientString(id = 2301, message = "Lidia's Heart")
	public static NpcStringId LIDIA_S_HEART;
	
	@ClientString(id = 2302, message = "Lidia's Heart (In Progress)")
	public static NpcStringId LIDIA_S_HEART_IN_PROGRESS;
	
	@ClientString(id = 2303, message = "Lidia's Heart (Done)")
	public static NpcStringId LIDIA_S_HEART_DONE;
	
	@ClientString(id = 2401, message = "Inhabitants of the Forest of the Dead")
	public static NpcStringId INHABITANTS_OF_THE_FOREST_OF_THE_DEAD;
	
	@ClientString(id = 2402, message = "Inhabitants of the Forest of the Dead (In Progress)")
	public static NpcStringId INHABITANTS_OF_THE_FOREST_OF_THE_DEAD_IN_PROGRESS;
	
	@ClientString(id = 2403, message = "Inhabitants of the Forest of the Dead (Done)")
	public static NpcStringId INHABITANTS_OF_THE_FOREST_OF_THE_DEAD_DONE;
	
	@ClientString(id = 2450, message = "That sign!")
	public static NpcStringId THAT_SIGN;
	
	@ClientString(id = 2501, message = "Hiding Behind the Truth")
	public static NpcStringId HIDING_BEHIND_THE_TRUTH;
	
	@ClientString(id = 2502, message = "Hiding Behind the Truth (In Progress)")
	public static NpcStringId HIDING_BEHIND_THE_TRUTH_IN_PROGRESS;
	
	@ClientString(id = 2503, message = "Hiding Behind the Truth (Done)")
	public static NpcStringId HIDING_BEHIND_THE_TRUTH_DONE;
	
	@ClientString(id = 2550, message = "That box was sealed by my master, $s1! Don't touch it!")
	public static NpcStringId THAT_BOX_WAS_SEALED_BY_MY_MASTER_S1_DON_T_TOUCH_IT;
	
	@ClientString(id = 2551, message = "You've ended my immortal life! You're protected by the feudal lord, aren't you?")
	public static NpcStringId YOU_VE_ENDED_MY_IMMORTAL_LIFE_YOU_RE_PROTECTED_BY_THE_FEUDAL_LORD_AREN_T_YOU;
	
	@ClientString(id = 2601, message = "Tired of Waiting")
	public static NpcStringId TIRED_OF_WAITING;
	
	@ClientString(id = 2602, message = "Tired of Waiting (In Progress)")
	public static NpcStringId TIRED_OF_WAITING_IN_PROGRESS;
	
	@ClientString(id = 2603, message = "Tired of Waiting (Done)")
	public static NpcStringId TIRED_OF_WAITING_DONE;
	
	@ClientString(id = 2701, message = "Chest Caught with a Bait of Wind")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_WIND;
	
	@ClientString(id = 2702, message = "Chest Caught with a Bait of Wind (In Progress)")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_WIND_IN_PROGRESS;
	
	@ClientString(id = 2703, message = "Chest Caught with a Bait of Wind (Done)")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_WIND_DONE;
	
	@ClientString(id = 2801, message = "Chest Caught with a Bait of Icy Air")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_ICY_AIR;
	
	@ClientString(id = 2802, message = "Chest Caught with a Bait of Icy Air (In Progress)")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_ICY_AIR_IN_PROGRESS;
	
	@ClientString(id = 2803, message = "Chest Caught with a Bait of Icy Air (Done)")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_ICY_AIR_DONE;
	
	@ClientString(id = 2901, message = "Chest Caught with a Bait of Earth")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_EARTH;
	
	@ClientString(id = 2902, message = "Chest Caught with a Bait of Earth (In Progress)")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_EARTH_IN_PROGRESS;
	
	@ClientString(id = 2903, message = "Chest Caught with a Bait of Earth (Done)")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_EARTH_DONE;
	
	@ClientString(id = 3001, message = "Chest Caught with a Bait of Flame")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_FLAME;
	
	@ClientString(id = 3002, message = "Chest Caught with a Bait of Flame (In Progress)")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_FLAME_IN_PROGRESS;
	
	@ClientString(id = 3003, message = "Chest Caught with a Bait of Flame (Done)")
	public static NpcStringId CHEST_CAUGHT_WITH_A_BAIT_OF_FLAME_DONE;
	
	@ClientString(id = 3101, message = "Secret Buried in the Swamp")
	public static NpcStringId SECRET_BURIED_IN_THE_SWAMP;
	
	@ClientString(id = 3102, message = "Secret Buried in the Swamp (In Progress)")
	public static NpcStringId SECRET_BURIED_IN_THE_SWAMP_IN_PROGRESS;
	
	@ClientString(id = 3103, message = "Secret Buried in the Swamp (Done)")
	public static NpcStringId SECRET_BURIED_IN_THE_SWAMP_DONE;
	
	@ClientString(id = 3201, message = "An Obvious Lie")
	public static NpcStringId AN_OBVIOUS_LIE;
	
	@ClientString(id = 3202, message = "An Obvious Lie (In Progress)")
	public static NpcStringId AN_OBVIOUS_LIE_IN_PROGRESS;
	
	@ClientString(id = 3203, message = "An Obvious Lie (Done)")
	public static NpcStringId AN_OBVIOUS_LIE_DONE;
	
	@ClientString(id = 3301, message = "Make a Pair of Dress Shoes")
	public static NpcStringId MAKE_A_PAIR_OF_DRESS_SHOES;
	
	@ClientString(id = 3302, message = "Make a Pair of Dress Shoes (In Progress)")
	public static NpcStringId MAKE_A_PAIR_OF_DRESS_SHOES_IN_PROGRESS;
	
	@ClientString(id = 3303, message = "Make a Pair of Dress Shoes (Done)")
	public static NpcStringId MAKE_A_PAIR_OF_DRESS_SHOES_DONE;
	
	@ClientString(id = 3401, message = "In Search of Cloth")
	public static NpcStringId IN_SEARCH_OF_CLOTH;
	
	@ClientString(id = 3402, message = "In Search of Cloth (In Progress)")
	public static NpcStringId IN_SEARCH_OF_CLOTH_IN_PROGRESS;
	
	@ClientString(id = 3403, message = "In Search of Cloth (Done)")
	public static NpcStringId IN_SEARCH_OF_CLOTH_DONE;
	
	@ClientString(id = 3501, message = "Find Glittering Jewelry")
	public static NpcStringId FIND_GLITTERING_JEWELRY;
	
	@ClientString(id = 3502, message = "Find Glittering Jewelry (In Progress)")
	public static NpcStringId FIND_GLITTERING_JEWELRY_IN_PROGRESS;
	
	@ClientString(id = 3503, message = "Find Glittering Jewelry (Done)")
	public static NpcStringId FIND_GLITTERING_JEWELRY_DONE;
	
	@ClientString(id = 3601, message = "Make a Sewing Kit")
	public static NpcStringId MAKE_A_SEWING_KIT;
	
	@ClientString(id = 3602, message = "Make a Sewing Kit (In Progress)")
	public static NpcStringId MAKE_A_SEWING_KIT_IN_PROGRESS;
	
	@ClientString(id = 3603, message = "Make a Sewing Kit (Done)")
	public static NpcStringId MAKE_A_SEWING_KIT_DONE;
	
	@ClientString(id = 3701, message = "Make Formal Wear")
	public static NpcStringId MAKE_FORMAL_WEAR;
	
	@ClientString(id = 3702, message = "Make Formal Wear (In Progress)")
	public static NpcStringId MAKE_FORMAL_WEAR_IN_PROGRESS;
	
	@ClientString(id = 3703, message = "Make Formal Wear (Done)")
	public static NpcStringId MAKE_FORMAL_WEAR_DONE;
	
	@ClientString(id = 3801, message = "Dragon Fangs")
	public static NpcStringId DRAGON_FANGS;
	
	@ClientString(id = 3802, message = "Dragon Fangs (In Progress)")
	public static NpcStringId DRAGON_FANGS_IN_PROGRESS;
	
	@ClientString(id = 3803, message = "Dragon Fangs (Done)")
	public static NpcStringId DRAGON_FANGS_DONE;
	
	@ClientString(id = 3901, message = "Red-eyed Invader")
	public static NpcStringId RED_EYED_INVADER;
	
	@ClientString(id = 3902, message = "Red-eyed Invader (In Progress)")
	public static NpcStringId RED_EYED_INVADER_IN_PROGRESS;
	
	@ClientString(id = 3903, message = "Red-eyed Invader (Done)")
	public static NpcStringId RED_EYED_INVADER_DONE;
	
	@ClientString(id = 4001, message = "A Special Order")
	public static NpcStringId A_SPECIAL_ORDER;
	
	@ClientString(id = 4002, message = "A Special Order (In Progress)")
	public static NpcStringId A_SPECIAL_ORDER_IN_PROGRESS;
	
	@ClientString(id = 4003, message = "A Special Order (Done)")
	public static NpcStringId A_SPECIAL_ORDER_DONE;
	
	@ClientString(id = 4101, message = "The Adventurer's Challenge")
	public static NpcStringId THE_ADVENTURER_S_CHALLENGE;
	
	@ClientString(id = 4102, message = "The Adventurer's Challenge (In Progress)")
	public static NpcStringId THE_ADVENTURER_S_CHALLENGE_IN_PROGRESS;
	
	@ClientString(id = 4103, message = "The Adventurer's Challenge (Done)")
	public static NpcStringId THE_ADVENTURER_S_CHALLENGE_DONE;
	
	@ClientString(id = 4151, message = "Delivery duty complete. \\n Go find the Newbie Guide.")
	public static NpcStringId DELIVERY_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;
	
	@ClientString(id = 4152, message = "Acquisition of Soulshot for beginners complete. \\n Go find the Newbie Guide.")
	public static NpcStringId ACQUISITION_OF_SOULSHOT_FOR_BEGINNERS_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;
	
	@ClientString(id = 4153, message = "Acquisition of Weapon Exchange coupon for beginners complete. \\n Go speak with the Newbie Guide.")
	public static NpcStringId ACQUISITION_OF_WEAPON_EXCHANGE_COUPON_FOR_BEGINNERS_COMPLETE_N_GO_SPEAK_WITH_THE_NEWBIE_GUIDE;
	
	@ClientString(id = 4154, message = "Acquisition of race-specific weapon complete. \\n Go find the Newbie Guide.")
	public static NpcStringId ACQUISITION_OF_RACE_SPECIFIC_WEAPON_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;
	
	@ClientString(id = 4155, message = "Last duty complete. \\n Go find the Newbie Guide.")
	public static NpcStringId LAST_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;
	
	@ClientString(id = 4201, message = "Help the Uncle!")
	public static NpcStringId HELP_THE_UNCLE;
	
	@ClientString(id = 4202, message = "Help the Uncle! (In Progress)")
	public static NpcStringId HELP_THE_UNCLE_IN_PROGRESS;
	
	@ClientString(id = 4203, message = "Help the Uncle! (Done)")
	public static NpcStringId HELP_THE_UNCLE_DONE;
	
	@ClientString(id = 4301, message = "Help the Sister!")
	public static NpcStringId HELP_THE_SISTER;
	
	@ClientString(id = 4302, message = "Help the Sister! (In Progress)")
	public static NpcStringId HELP_THE_SISTER_IN_PROGRESS;
	
	@ClientString(id = 4303, message = "Help the Sister! (Done)")
	public static NpcStringId HELP_THE_SISTER_DONE;
	
	@ClientString(id = 4401, message = "Help the Son!")
	public static NpcStringId HELP_THE_SON;
	
	@ClientString(id = 4402, message = "Help the Son! (In Progress)")
	public static NpcStringId HELP_THE_SON_IN_PROGRESS;
	
	@ClientString(id = 4403, message = "Help the Son! (Done)")
	public static NpcStringId HELP_THE_SON_DONE;
	
	@ClientString(id = 4501, message = "To Talking Island")
	public static NpcStringId TO_TALKING_ISLAND;
	
	@ClientString(id = 4502, message = "To Talking Island (In Progress)")
	public static NpcStringId TO_TALKING_ISLAND_IN_PROGRESS;
	
	@ClientString(id = 4503, message = "To Talking Island (Done)")
	public static NpcStringId TO_TALKING_ISLAND_DONE;
	
	@ClientString(id = 4601, message = "Once More In the Arms of the Mother Tree")
	public static NpcStringId ONCE_MORE_IN_THE_ARMS_OF_THE_MOTHER_TREE;
	
	@ClientString(id = 4602, message = "Once More In the Arms of the Mother Tree (In Progress)")
	public static NpcStringId ONCE_MORE_IN_THE_ARMS_OF_THE_MOTHER_TREE_IN_PROGRESS;
	
	@ClientString(id = 4603, message = "Once More In the Arms of the Mother Tree (Done)")
	public static NpcStringId ONCE_MORE_IN_THE_ARMS_OF_THE_MOTHER_TREE_DONE;
	
	@ClientString(id = 4701, message = "Into the Dark Elven Forest")
	public static NpcStringId INTO_THE_DARK_ELVEN_FOREST;
	
	@ClientString(id = 4702, message = "Into the Dark Elven Forest (In Progress)")
	public static NpcStringId INTO_THE_DARK_ELVEN_FOREST_IN_PROGRESS;
	
	@ClientString(id = 4703, message = "Into the Dark Elven Forest (Done)")
	public static NpcStringId INTO_THE_DARK_ELVEN_FOREST_DONE;
	
	@ClientString(id = 4801, message = "To the Immortal Plateau")
	public static NpcStringId TO_THE_IMMORTAL_PLATEAU;
	
	@ClientString(id = 4802, message = "To the Immortal Plateau (In Progress)")
	public static NpcStringId TO_THE_IMMORTAL_PLATEAU_IN_PROGRESS;
	
	@ClientString(id = 4803, message = "To the Immortal Plateau (Done)")
	public static NpcStringId TO_THE_IMMORTAL_PLATEAU_DONE;
	
	@ClientString(id = 4901, message = "The Road Home")
	public static NpcStringId THE_ROAD_HOME;
	
	@ClientString(id = 4902, message = "The Road Home (In Progress)")
	public static NpcStringId THE_ROAD_HOME_IN_PROGRESS;
	
	@ClientString(id = 4903, message = "The Road Home (Done)")
	public static NpcStringId THE_ROAD_HOME_DONE;
	
	@ClientString(id = 5001, message = "Lanosco's Special Bait")
	public static NpcStringId LANOSCO_S_SPECIAL_BAIT;
	
	@ClientString(id = 5002, message = "Lanosco's Special Bait (In Progress)")
	public static NpcStringId LANOSCO_S_SPECIAL_BAIT_IN_PROGRESS;
	
	@ClientString(id = 5003, message = "Lanosco's Special Bait (Done)")
	public static NpcStringId LANOSCO_S_SPECIAL_BAIT_DONE;
	
	@ClientString(id = 5101, message = "O'Fulle's Special Bait")
	public static NpcStringId O_FULLE_S_SPECIAL_BAIT;
	
	@ClientString(id = 5102, message = "O'Fulle's Special Bait (In Progress)")
	public static NpcStringId O_FULLE_S_SPECIAL_BAIT_IN_PROGRESS;
	
	@ClientString(id = 5103, message = "O'Fulle's Special Bait (Done)")
	public static NpcStringId O_FULLE_S_SPECIAL_BAIT_DONE;
	
	@ClientString(id = 5201, message = "Willie's Special Bait")
	public static NpcStringId WILLIE_S_SPECIAL_BAIT;
	
	@ClientString(id = 5202, message = "Willie's Special Bait (In Progress)")
	public static NpcStringId WILLIE_S_SPECIAL_BAIT_IN_PROGRESS;
	
	@ClientString(id = 5203, message = "Willie's Special Bait (Done)")
	public static NpcStringId WILLIE_S_SPECIAL_BAIT_DONE;
	
	@ClientString(id = 5301, message = "Linnaeus' Special Bait")
	public static NpcStringId LINNAEUS_SPECIAL_BAIT;
	
	@ClientString(id = 5302, message = "Linnaeus' Special Bait (In Progress)")
	public static NpcStringId LINNAEUS_SPECIAL_BAIT_IN_PROGRESS;
	
	@ClientString(id = 5303, message = "Linnaeus' Special Bait (Done)")
	public static NpcStringId LINNAEUS_SPECIAL_BAIT_DONE;
	
	@ClientString(id = 6001, message = "Good Work's Reward")
	public static NpcStringId GOOD_WORK_S_REWARD;
	
	@ClientString(id = 6002, message = "Good Work's Reward (In Progress)")
	public static NpcStringId GOOD_WORK_S_REWARD_IN_PROGRESS;
	
	@ClientString(id = 6003, message = "Good Work's Reward (Done)")
	public static NpcStringId GOOD_WORK_S_REWARD_DONE;
	
	@ClientString(id = 6051, message = "$s1! I must kill you. Blame your own curiosity.")
	public static NpcStringId S1_I_MUST_KILL_YOU_BLAME_YOUR_OWN_CURIOSITY;
	
	@ClientString(id = 6052, message = "You have good luck. I shall return.")
	public static NpcStringId YOU_HAVE_GOOD_LUCK_I_SHALL_RETURN;
	
	@ClientString(id = 6053, message = "You are strong. This was a mistake.")
	public static NpcStringId YOU_ARE_STRONG_THIS_WAS_A_MISTAKE;
	
	@ClientString(id = 6054, message = "Who are you to join in the battle? How upsetting.")
	public static NpcStringId WHO_ARE_YOU_TO_JOIN_IN_THE_BATTLE_HOW_UPSETTING;
	
	@ClientString(id = 6101, message = "Law Enforcement")
	public static NpcStringId LAW_ENFORCEMENT;
	
	@ClientString(id = 6102, message = "Law Enforcement (In Progress)")
	public static NpcStringId LAW_ENFORCEMENT_IN_PROGRESS;
	
	@ClientString(id = 6103, message = "Law Enforcement (Done)")
	public static NpcStringId LAW_ENFORCEMENT_DONE;
	
	@ClientString(id = 6201, message = "Path of the Trooper")
	public static NpcStringId PATH_OF_THE_TROOPER;
	
	@ClientString(id = 6202, message = "Path of the Trooper (In Progress)")
	public static NpcStringId PATH_OF_THE_TROOPER_IN_PROGRESS;
	
	@ClientString(id = 6203, message = "Path of the Trooper (Done)")
	public static NpcStringId PATH_OF_THE_TROOPER_DONE;
	
	@ClientString(id = 6301, message = "Path of the Warder")
	public static NpcStringId PATH_OF_THE_WARDER;
	
	@ClientString(id = 6302, message = "Path of the Warder (In Progress)")
	public static NpcStringId PATH_OF_THE_WARDER_IN_PROGRESS;
	
	@ClientString(id = 6303, message = "Path of the Warder (Done)")
	public static NpcStringId PATH_OF_THE_WARDER_DONE;
	
	@ClientString(id = 6401, message = "Certified Berserker")
	public static NpcStringId CERTIFIED_BERSERKER;
	
	@ClientString(id = 6402, message = "Certified Berserker (In Progress)")
	public static NpcStringId CERTIFIED_BERSERKER_IN_PROGRESS;
	
	@ClientString(id = 6403, message = "Certified Berserker (Done)")
	public static NpcStringId CERTIFIED_BERSERKER_DONE;
	
	@ClientString(id = 6451, message = "$s1, did you come to help me?")
	public static NpcStringId S1_DID_YOU_COME_TO_HELP_ME;
	
	@ClientString(id = 6501, message = "Certified Soul Breaker")
	public static NpcStringId CERTIFIED_SOUL_BREAKER;
	
	@ClientString(id = 6502, message = "Certified Soul Breaker (In Progress)")
	public static NpcStringId CERTIFIED_SOUL_BREAKER_IN_PROGRESS;
	
	@ClientString(id = 6503, message = "Certified Soul Breaker (Done)")
	public static NpcStringId CERTIFIED_SOUL_BREAKER_DONE;
	
	@ClientString(id = 6551, message = "Drats! How could I be so wrong??")
	public static NpcStringId DRATS_HOW_COULD_I_BE_SO_WRONG;
	
	@ClientString(id = 6552, message = "$s1! Step back from the confounded box! I will take it myself!")
	public static NpcStringId S1_STEP_BACK_FROM_THE_CONFOUNDED_BOX_I_WILL_TAKE_IT_MYSELF;
	
	@ClientString(id = 6553, message = "$s1! I will be back soon. Stay there and don't you dare wander off!")
	public static NpcStringId S1_I_WILL_BE_BACK_SOON_STAY_THERE_AND_DON_T_YOU_DARE_WANDER_OFF;
	
	@ClientString(id = 6554, message = "Grr. I've been hit... ")
	public static NpcStringId GRR_I_VE_BEEN_HIT;
	
	@ClientString(id = 6555, message = "Grr! Who are you and why have you stopped me?")
	public static NpcStringId GRR_WHO_ARE_YOU_AND_WHY_HAVE_YOU_STOPPED_ME;
	
	@ClientString(id = 6556, message = "I am late!")
	public static NpcStringId I_AM_LATE;
	
	@ClientString(id = 6557, message = "Good luck!")
	public static NpcStringId GOOD_LUCK;
	
	@ClientString(id = 6601, message = "Certified Arbalester")
	public static NpcStringId CERTIFIED_ARBALESTER;
	
	@ClientString(id = 6602, message = "Certified Arbalester (In Progress)")
	public static NpcStringId CERTIFIED_ARBALESTER_IN_PROGRESS;
	
	@ClientString(id = 6603, message = "Certified Arbalester (Done)")
	public static NpcStringId CERTIFIED_ARBALESTER_DONE;
	
	@ClientString(id = 6701, message = "Saga of the Doombringer")
	public static NpcStringId SAGA_OF_THE_DOOMBRINGER;
	
	@ClientString(id = 6702, message = "Saga of the Doombringer (In Progress)")
	public static NpcStringId SAGA_OF_THE_DOOMBRINGER_IN_PROGRESS;
	
	@ClientString(id = 6703, message = "Saga of the Doombringer (Done)")
	public static NpcStringId SAGA_OF_THE_DOOMBRINGER_DONE;
	
	@ClientString(id = 6750, message = "$s1! You seek the forbidden knowledge and I cannot let you have it!")
	public static NpcStringId S1_YOU_SEEK_THE_FORBIDDEN_KNOWLEDGE_AND_I_CANNOT_LET_YOU_HAVE_IT;
	
	@ClientString(id = 6751, message = "Is this all I am allowed to have?...")
	public static NpcStringId IS_THIS_ALL_I_AM_ALLOWED_TO_HAVE;
	
	@ClientString(id = 6752, message = "You defeated me, but our doom approaches...")
	public static NpcStringId YOU_DEFEATED_ME_BUT_OUR_DOOM_APPROACHES;
	
	@ClientString(id = 6753, message = "$s1! Who are you? Why are you bothering my minions?")
	public static NpcStringId S1_WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS;
	
	@ClientString(id = 6754, message = "Beefcake!!")
	public static NpcStringId BEEFCAKE;
	
	@ClientString(id = 6755, message = "Grr! Why are you sticking your nose in our business?")
	public static NpcStringId GRR_WHY_ARE_YOU_STICKING_YOUR_NOSE_IN_OUR_BUSINESS;
	
	@ClientString(id = 6756, message = "Farewell and watch your back!")
	public static NpcStringId FAREWELL_AND_WATCH_YOUR_BACK;
	
	@ClientString(id = 6757, message = "Kamael! Good to see you. I have something to ask you...")
	public static NpcStringId KAMAEL_GOOD_TO_SEE_YOU_I_HAVE_SOMETHING_TO_ASK_YOU;
	
	@ClientString(id = 6758, message = "$s1! Go get him!!")
	public static NpcStringId S1_GO_GET_HIM;
	
	@ClientString(id = 6759, message = "$s1! What are you doing? Attack him!")
	public static NpcStringId S1_WHAT_ARE_YOU_DOING_ATTACK_HIM;
	
	@ClientString(id = 6760, message = "$s1! Is ? your full potential?")
	public static NpcStringId S1_IS_YOUR_FULL_POTENTIAL;
	
	@ClientString(id = 6761, message = "Thanks! I must go and hunt down those that oppose me.")
	public static NpcStringId THANKS_I_MUST_GO_AND_HUNT_DOWN_THOSE_THAT_OPPOSE_ME;
	
	@ClientString(id = 6762, message = "You are so stubborn... I must follow him now...")
	public static NpcStringId YOU_ARE_SO_STUBBORN_I_MUST_FOLLOW_HIM_NOW;
	
	@ClientString(id = 6763, message = "Seek enlightenment from the Tablet.")
	public static NpcStringId SEEK_ENLIGHTENMENT_FROM_THE_TABLET;
	
	@ClientString(id = 6764, message = "Arrogant beings! You are all doomed!")
	public static NpcStringId ARROGANT_BEINGS_YOU_ARE_ALL_DOOMED;
	
	@ClientString(id = 6765, message = "My time in your world has come to an end. Consider yourselves lucky...")
	public static NpcStringId MY_TIME_IN_YOUR_WORLD_HAS_COME_TO_AN_END_CONSIDER_YOURSELVES_LUCKY;
	
	@ClientString(id = 6766, message = "$s1! How dare you!!!")
	public static NpcStringId S1_HOW_DARE_YOU;
	
	@ClientString(id = 6767, message = "$s1! Ahhaa! Your god forsakes you!")
	public static NpcStringId S1_AHHAA_YOUR_GOD_FORSAKES_YOU;
	
	@ClientString(id = 6801, message = "Saga of the Soul Hound")
	public static NpcStringId SAGA_OF_THE_SOUL_HOUND;
	
	@ClientString(id = 6802, message = "Saga of the Soul Hound (In Progress)")
	public static NpcStringId SAGA_OF_THE_SOUL_HOUND_IN_PROGRESS;
	
	@ClientString(id = 6803, message = "Saga of the Soul Hound (Done)")
	public static NpcStringId SAGA_OF_THE_SOUL_HOUND_DONE;
	
	@ClientString(id = 6851, message = "$s1! Your time is up. Prepare to die a horrible death. ")
	public static NpcStringId S1_YOUR_TIME_IS_UP_PREPARE_TO_DIE_A_HORRIBLE_DEATH;
	
	@ClientString(id = 6852, message = "Consider yourself lucky. The next time we meet, you will die - PERMANENTLY!")
	public static NpcStringId CONSIDER_YOURSELF_LUCKY_THE_NEXT_TIME_WE_MEET_YOU_WILL_DIE_PERMANENTLY;
	
	@ClientString(id = 6853, message = "Fare thee well! We shall meet again.")
	public static NpcStringId FARE_THEE_WELL_WE_SHALL_MEET_AGAIN;
	
	@ClientString(id = 6854, message = "$s1! Who are you? And better yet, why are you bothering my minions?")
	public static NpcStringId S1_WHO_ARE_YOU_AND_BETTER_YET_WHY_ARE_YOU_BOTHERING_MY_MINIONS;
	
	@ClientString(id = 6855, message = "Strength beyond strength!!")
	public static NpcStringId STRENGTH_BEYOND_STRENGTH;
	
	@ClientString(id = 6856, message = "Grr! Why are you sticking your nose where it doesn't belong?")
	public static NpcStringId GRR_WHY_ARE_YOU_STICKING_YOUR_NOSE_WHERE_IT_DOESN_T_BELONG;
	
	@ClientString(id = 6857, message = "You've won for now, but we will meet again!")
	public static NpcStringId YOU_VE_WON_FOR_NOW_BUT_WE_WILL_MEET_AGAIN;
	
	@ClientString(id = 6858, message = "Are they tired of following me?")
	public static NpcStringId ARE_THEY_TIRED_OF_FOLLOWING_ME;
	
	@ClientString(id = 6859, message = "$s1! Can you help me?")
	public static NpcStringId S1_CAN_YOU_HELP_ME;
	
	@ClientString(id = 6860, message = "Is that all you got, little $s1?")
	public static NpcStringId IS_THAT_ALL_YOU_GOT_LITTLE_S1;
	
	@ClientString(id = 6861, message = "$s1! Wake up fool! Don't let him get away!")
	public static NpcStringId S1_WAKE_UP_FOOL_DON_T_LET_HIM_GET_AWAY;
	
	@ClientString(id = 6862, message = "The path is clear, but be careful!")
	public static NpcStringId THE_PATH_IS_CLEAR_BUT_BE_CAREFUL;
	
	@ClientString(id = 6863, message = "I must follow someone now. See you around!")
	public static NpcStringId I_MUST_FOLLOW_SOMEONE_NOW_SEE_YOU_AROUND;
	
	@ClientString(id = 6864, message = "May we meet again.")
	public static NpcStringId MAY_WE_MEET_AGAIN;
	
	@ClientString(id = 6865, message = "Curses on those who blaspheme the Gods!")
	public static NpcStringId CURSES_ON_THOSE_WHO_BLASPHEME_THE_GODS;
	
	@ClientString(id = 6866, message = "Einhasad is calling me! You are lucky and I shall continue my punishment later!")
	public static NpcStringId EINHASAD_IS_CALLING_ME_YOU_ARE_LUCKY_AND_I_SHALL_CONTINUE_MY_PUNISHMENT_LATER;
	
	@ClientString(id = 6867, message = "By the power vested in me by the gods, you $s1, shall die!")
	public static NpcStringId BY_THE_POWER_VESTED_IN_ME_BY_THE_GODS_YOU_S1_SHALL_DIE;
	
	@ClientString(id = 6868, message = "$s1! I will not forget you!")
	public static NpcStringId S1_I_WILL_NOT_FORGET_YOU;
	
	@ClientString(id = 6901, message = "Saga of the Trickster")
	public static NpcStringId SAGA_OF_THE_TRICKSTER;
	
	@ClientString(id = 6902, message = "Saga of the Trickster (In Progress)")
	public static NpcStringId SAGA_OF_THE_TRICKSTER_IN_PROGRESS;
	
	@ClientString(id = 6903, message = "Saga of the Trickster (Done)")
	public static NpcStringId SAGA_OF_THE_TRICKSTER_DONE;
	
	@ClientString(id = 6950, message = "$s1! How dare you interfere! You shall pay for this!")
	public static NpcStringId S1_HOW_DARE_YOU_INTERFERE_YOU_SHALL_PAY_FOR_THIS;
	
	@ClientString(id = 6951, message = "Beleth is calling me. You are lucky but still a fool...")
	public static NpcStringId BELETH_IS_CALLING_ME_YOU_ARE_LUCKY_BUT_STILL_A_FOOL;
	
	@ClientString(id = 6952, message = "I shall take my leave, but will never surrender!")
	public static NpcStringId I_SHALL_TAKE_MY_LEAVE_BUT_WILL_NEVER_SURRENDER;
	
	@ClientString(id = 6953, message = "$s1! Who are you? Why are you bothering my minions?")
	public static NpcStringId S1_WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS_2;
	
	@ClientString(id = 6954, message = "Cower before my awesome and mighty power!!")
	public static NpcStringId COWER_BEFORE_MY_AWESOME_AND_MIGHTY_POWER;
	
	@ClientString(id = 6955, message = "Grr! Can't you find something better to do with your time?")
	public static NpcStringId GRR_CAN_T_YOU_FIND_SOMETHING_BETTER_TO_DO_WITH_YOUR_TIME;
	
	@ClientString(id = 6956, message = "I shall take my leave, but your head will be mine some day.. Oh yes..yes it will!")
	public static NpcStringId I_SHALL_TAKE_MY_LEAVE_BUT_YOUR_HEAD_WILL_BE_MINE_SOME_DAY_OH_YES_YES_IT_WILL;
	
	@ClientString(id = 6957, message = "My children are stronger!")
	public static NpcStringId MY_CHILDREN_ARE_STRONGER;
	
	@ClientString(id = 6958, message = "$s1! Let's kill them all.")
	public static NpcStringId S1_LET_S_KILL_THEM_ALL;
	
	@ClientString(id = 6959, message = "$s1! Come my children...")
	public static NpcStringId S1_COME_MY_CHILDREN;
	
	@ClientString(id = 6960, message = "$s1! Muster your strength... Pick them off like chickens.")
	public static NpcStringId S1_MUSTER_YOUR_STRENGTH_PICK_THEM_OFF_LIKE_CHICKENS;
	
	@ClientString(id = 6961, message = "Thank you my children... Someday, we will meet again.")
	public static NpcStringId THANK_YOU_MY_CHILDREN_SOMEDAY_WE_WILL_MEET_AGAIN;
	
	@ClientString(id = 6962, message = "My children... Seek my enemies.")
	public static NpcStringId MY_CHILDREN_SEEK_MY_ENEMIES;
	
	@ClientString(id = 6963, message = "My children... I grant you my blessings...")
	public static NpcStringId MY_CHILDREN_I_GRANT_YOU_MY_BLESSINGS;
	
	@ClientString(id = 6964, message = "You worthless beings!")
	public static NpcStringId YOU_WORTHLESS_BEINGS;
	
	@ClientString(id = 6965, message = "My time on the material plane has ended. You are lucky...")
	public static NpcStringId MY_TIME_ON_THE_MATERIAL_PLANE_HAS_ENDED_YOU_ARE_LUCKY;
	
	@ClientString(id = 6966, message = "$s1! Worthless beings! Begone!")
	public static NpcStringId S1_WORTHLESS_BEINGS_BEGONE;
	
	@ClientString(id = 6967, message = "$s1! You are the meaning of the word 'danger'...")
	public static NpcStringId S1_YOU_ARE_THE_MEANING_OF_THE_WORD_DANGER;
	
	@ClientString(id = 7001, message = "Saga of the Phoenix Knight")
	public static NpcStringId SAGA_OF_THE_PHOENIX_KNIGHT;
	
	@ClientString(id = 7002, message = "Saga of the Phoenix Knight (In Progress)")
	public static NpcStringId SAGA_OF_THE_PHOENIX_KNIGHT_IN_PROGRESS;
	
	@ClientString(id = 7003, message = "Saga of the Phoenix Knight (Done)")
	public static NpcStringId SAGA_OF_THE_PHOENIX_KNIGHT_DONE;
	
	@ClientString(id = 7050, message = "You made it here, $s1. I'll show my strength. Die! ")
	public static NpcStringId YOU_MADE_IT_HERE_S1_I_LL_SHOW_MY_STRENGTH_DIE;
	
	@ClientString(id = 7051, message = "Ha! You failed! Are you ready to quit?")
	public static NpcStringId HA_YOU_FAILED_ARE_YOU_READY_TO_QUIT;
	
	@ClientString(id = 7052, message = "I'm the strongest! I lost everything to win!")
	public static NpcStringId I_M_THE_STRONGEST_I_LOST_EVERYTHING_TO_WIN;
	
	@ClientString(id = 7053, message = "$s1! Are you doing this because they're Halisha's minions?")
	public static NpcStringId S1_ARE_YOU_DOING_THIS_BECAUSE_THEY_RE_HALISHA_S_MINIONS;
	
	@ClientString(id = 7054, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA;
	
	@ClientString(id = 7055, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS;
	
	@ClientString(id = 7056, message = "This fight is a waste of time. Goodbye!")
	public static NpcStringId THIS_FIGHT_IS_A_WASTE_OF_TIME_GOODBYE;
	
	@ClientString(id = 7057, message = "Every cloud has a silver lining, don't you think?")
	public static NpcStringId EVERY_CLOUD_HAS_A_SILVER_LINING_DON_T_YOU_THINK;
	
	@ClientString(id = 7058, message = "$s1! Don't listen to this demon.")
	public static NpcStringId S1_DON_T_LISTEN_TO_THIS_DEMON;
	
	@ClientString(id = 7059, message = "$s1! Hesitation betrays your heart. Fight!")
	public static NpcStringId S1_HESITATION_BETRAYS_YOUR_HEART_FIGHT;
	
	@ClientString(id = 7060, message = "$s1! Isn't protecting somebody worthy? Isn't that justice?")
	public static NpcStringId S1_ISN_T_PROTECTING_SOMEBODY_WORTHY_ISN_T_THAT_JUSTICE;
	
	@ClientString(id = 7061, message = "I have urgent business! I gotta go.")
	public static NpcStringId I_HAVE_URGENT_BUSINESS_I_GOTTA_GO;
	
	@ClientString(id = 7062, message = "Are my efforts in vain? Is this the end?")
	public static NpcStringId ARE_MY_EFFORTS_IN_VAIN_IS_THIS_THE_END;
	
	@ClientString(id = 7063, message = "Goodbye, friend. I hope to see you again.")
	public static NpcStringId GOODBYE_FRIEND_I_HOPE_TO_SEE_YOU_AGAIN;
	
	@ClientString(id = 7064, message = "Knights are always foolish!")
	public static NpcStringId KNIGHTS_ARE_ALWAYS_FOOLISH;
	
	@ClientString(id = 7065, message = "I'll show mercy on you for now.")
	public static NpcStringId I_LL_SHOW_MERCY_ON_YOU_FOR_NOW;
	
	@ClientString(id = 7066, message = "$s1! Your justice is just hypocrisy if you give up on what you've promised to protect.")
	public static NpcStringId S1_YOUR_JUSTICE_IS_JUST_HYPOCRISY_IF_YOU_GIVE_UP_ON_WHAT_YOU_VE_PROMISED_TO_PROTECT;
	
	@ClientString(id = 7067, message = "$s1...Don't think you've won! Your dark shadow will always follow you...hypocrite!")
	public static NpcStringId S1_DON_T_THINK_YOU_VE_WON_YOUR_DARK_SHADOW_WILL_ALWAYS_FOLLOW_YOU_HYPOCRITE;
	
	@ClientString(id = 7101, message = "Saga of Eva's Templar")
	public static NpcStringId SAGA_OF_EVA_S_TEMPLAR;
	
	@ClientString(id = 7102, message = "Saga of Eva's Templar (In Progress)")
	public static NpcStringId SAGA_OF_EVA_S_TEMPLAR_IN_PROGRESS;
	
	@ClientString(id = 7103, message = "Saga of Eva's Templar (Done)")
	public static NpcStringId SAGA_OF_EVA_S_TEMPLAR_DONE;
	
	@ClientString(id = 7150, message = "A temple knight guards the Mother Tree! $s1! Has Human contact made you forget that?")
	public static NpcStringId A_TEMPLE_KNIGHT_GUARDS_THE_MOTHER_TREE_S1_HAS_HUMAN_CONTACT_MADE_YOU_FORGET_THAT;
	
	@ClientString(id = 7151, message = "I must stop. Remember, the ones you're protecting will someday defeat the Elves.")
	public static NpcStringId I_MUST_STOP_REMEMBER_THE_ONES_YOU_RE_PROTECTING_WILL_SOMEDAY_DEFEAT_THE_ELVES;
	
	@ClientString(id = 7152, message = "What! That will just strengthen the enemy!")
	public static NpcStringId WHAT_THAT_WILL_JUST_STRENGTHEN_THE_ENEMY;
	
	@ClientString(id = 7153, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1;
	
	@ClientString(id = 7154, message = "My spirit is releasing from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASING_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA;
	
	@ClientString(id = 7155, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_2;
	
	@ClientString(id = 7156, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE;
	
	@ClientString(id = 7157, message = "I'm not like my brother Panacea. Ghost of the past, begone!")
	public static NpcStringId I_M_NOT_LIKE_MY_BROTHER_PANACEA_GHOST_OF_THE_PAST_BEGONE;
	
	@ClientString(id = 7158, message = "The Elves no longer rule! Help me, $s1!")
	public static NpcStringId THE_ELVES_NO_LONGER_RULE_HELP_ME_S1;
	
	@ClientString(id = 7159, message = "Don't give up, $s1! He' a demon from the past!")
	public static NpcStringId DON_T_GIVE_UP_S1_HE_A_DEMON_FROM_THE_PAST;
	
	@ClientString(id = 7160, message = "Be proud, $s1. We protect this world together.")
	public static NpcStringId BE_PROUD_S1_WE_PROTECT_THIS_WORLD_TOGETHER;
	
	@ClientString(id = 7161, message = "I have to go. I've got some business to take care of.")
	public static NpcStringId I_HAVE_TO_GO_I_VE_GOT_SOME_BUSINESS_TO_TAKE_CARE_OF;
	
	@ClientString(id = 7162, message = "Ugh! Don't let him get away!")
	public static NpcStringId UGH_DON_T_LET_HIM_GET_AWAY;
	
	@ClientString(id = 7163, message = "Don't forget your pride. You're protecting the world!")
	public static NpcStringId DON_T_FORGET_YOUR_PRIDE_YOU_RE_PROTECTING_THE_WORLD;
	
	@ClientString(id = 7164, message = "Ha, ha, ha!...")
	public static NpcStringId HA_HA_HA;
	
	@ClientString(id = 7165, message = "Kuh, huh...")
	public static NpcStringId KUH_HUH;
	
	@ClientString(id = 7166, message = "Aah! Kuh...$s1!...Kuh, huh...")
	public static NpcStringId AAH_KUH_S1_KUH_HUH;
	
	@ClientString(id = 7167, message = "$s1!...Re... mem... Ugh...Uh...")
	public static NpcStringId S1_RE_MEM_UGH_UH;
	
	@ClientString(id = 7201, message = "Saga of the Sword Muse")
	public static NpcStringId SAGA_OF_THE_SWORD_MUSE;
	
	@ClientString(id = 7202, message = "Saga of the Sword Muse (In Progress)")
	public static NpcStringId SAGA_OF_THE_SWORD_MUSE_IN_PROGRESS;
	
	@ClientString(id = 7203, message = "Saga of the Sword Muse (Done)")
	public static NpcStringId SAGA_OF_THE_SWORD_MUSE_DONE;
	
	@ClientString(id = 7250, message = "$s1, You'd better listen.")
	public static NpcStringId S1_YOU_D_BETTER_LISTEN;
	
	@ClientString(id = 7251, message = "Huh? It's curtain time! I won't get any money.")
	public static NpcStringId HUH_IT_S_CURTAIN_TIME_I_WON_T_GET_ANY_MONEY;
	
	@ClientString(id = 7252, message = "Ugh...It can't be...!")
	public static NpcStringId UGH_IT_CAN_T_BE;
	
	@ClientString(id = 7253, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_2;
	
	@ClientString(id = 7254, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_2;
	
	@ClientString(id = 7255, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_3;
	
	@ClientString(id = 7256, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_2;
	
	@ClientString(id = 7257, message = "You won't get away this time, Narcissus!")
	public static NpcStringId YOU_WON_T_GET_AWAY_THIS_TIME_NARCISSUS;
	
	@ClientString(id = 7258, message = "$s1! Help me!")
	public static NpcStringId S1_HELP_ME;
	
	@ClientString(id = 7259, message = "You must be aware of your audience when singing, %s! => Join us $s1! A song that nobody listens to is empty.")
	public static NpcStringId YOU_MUST_BE_AWARE_OF_YOUR_AUDIENCE_WHEN_SINGING_S_JOIN_US_S1_A_SONG_THAT_NOBODY_LISTENS_TO_IS_EMPTY;
	
	@ClientString(id = 7260, message = "You must work harder to be victorious, $s1.")
	public static NpcStringId YOU_MUST_WORK_HARDER_TO_BE_VICTORIOUS_S1;
	
	@ClientString(id = 7261, message = "My song is over, I must go. Goodbye!")
	public static NpcStringId MY_SONG_IS_OVER_I_MUST_GO_GOODBYE;
	
	@ClientString(id = 7262, message = "How could I miss!")
	public static NpcStringId HOW_COULD_I_MISS;
	
	@ClientString(id = 7263, message = "Don't forget. Song comes with harmony.")
	public static NpcStringId DON_T_FORGET_SONG_COMES_WITH_HARMONY;
	
	@ClientString(id = 7264, message = "Sing. Everyone will listen.")
	public static NpcStringId SING_EVERYONE_WILL_LISTEN;
	
	@ClientString(id = 7265, message = "You don't deserve my blessing.")
	public static NpcStringId YOU_DON_T_DESERVE_MY_BLESSING;
	
	@ClientString(id = 7266, message = "Do you reject my blessing, $s1?")
	public static NpcStringId DO_YOU_REJECT_MY_BLESSING_S1;
	
	@ClientString(id = 7267, message = "But why, $s1. Everyone would praise you!")
	public static NpcStringId BUT_WHY_S1_EVERYONE_WOULD_PRAISE_YOU;
	
	@ClientString(id = 7301, message = "Saga of the Duelist")
	public static NpcStringId SAGA_OF_THE_DUELIST;
	
	@ClientString(id = 7302, message = "Saga of the Duelist (In Progress)")
	public static NpcStringId SAGA_OF_THE_DUELIST_IN_PROGRESS;
	
	@ClientString(id = 7303, message = "Saga of the Duelist (Done)")
	public static NpcStringId SAGA_OF_THE_DUELIST_DONE;
	
	@ClientString(id = 7350, message = "$s1! Attack me? I'm immortal! I'm unrivaled!")
	public static NpcStringId S1_ATTACK_ME_I_M_IMMORTAL_I_M_UNRIVALED;
	
	@ClientString(id = 7351, message = "Ha! I'm immortal. This scar will soon heal. You'll die next time.")
	public static NpcStringId HA_I_M_IMMORTAL_THIS_SCAR_WILL_SOON_HEAL_YOU_LL_DIE_NEXT_TIME;
	
	@ClientString(id = 7352, message = "Metellus! You promised me an immortal life! How could I, Swordmaster Iron, lose?")
	public static NpcStringId METELLUS_YOU_PROMISED_ME_AN_IMMORTAL_LIFE_HOW_COULD_I_SWORDMASTER_IRON_LOSE;
	
	@ClientString(id = 7353, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_3;
	
	@ClientString(id = 7354, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_3;
	
	@ClientString(id = 7355, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_4;
	
	@ClientString(id = 7356, message = "This is a waste of time... Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_3;
	
	@ClientString(id = 7357, message = "Fallen Angel? It's worth trying.")
	public static NpcStringId FALLEN_ANGEL_IT_S_WORTH_TRYING;
	
	@ClientString(id = 7358, message = "Hey $s1! Why don't you join? It's your best shot!")
	public static NpcStringId HEY_S1_WHY_DON_T_YOU_JOIN_IT_S_YOUR_BEST_SHOT;
	
	@ClientString(id = 7359, message = "Are you interested in immortal life, $s1? It's too boring for me.")
	public static NpcStringId ARE_YOU_INTERESTED_IN_IMMORTAL_LIFE_S1_IT_S_TOO_BORING_FOR_ME;
	
	@ClientString(id = 7360, message = "Excellent, $s1! Show me what you learned when your life was on the line!")
	public static NpcStringId EXCELLENT_S1_SHOW_ME_WHAT_YOU_LEARNED_WHEN_YOUR_LIFE_WAS_ON_THE_LINE;
	
	@ClientString(id = 7361, message = "I have to go take a break.")
	public static NpcStringId I_HAVE_TO_GO_TAKE_A_BREAK;
	
	@ClientString(id = 7362, message = "You missed?! What a fool you are!")
	public static NpcStringId YOU_MISSED_WHAT_A_FOOL_YOU_ARE;
	
	@ClientString(id = 7363, message = "Fighting without risk, training without danger and growing without hardship will only lead to an inflated ego. Don't forget.")
	public static NpcStringId FIGHTING_WITHOUT_RISK_TRAINING_WITHOUT_DANGER_AND_GROWING_WITHOUT_HARDSHIP_WILL_ONLY_LEAD_TO_AN_INFLATED_EGO_DON_T_FORGET;
	
	@ClientString(id = 7364, message = "Do you want an immortal life?")
	public static NpcStringId DO_YOU_WANT_AN_IMMORTAL_LIFE;
	
	@ClientString(id = 7365, message = "Think it over. An immortal life and assured victory...")
	public static NpcStringId THINK_IT_OVER_AN_IMMORTAL_LIFE_AND_ASSURED_VICTORY;
	
	@ClientString(id = 7366, message = "That's good, $s1! Do you want my blessing to improve your skills?")
	public static NpcStringId THAT_S_GOOD_S1_DO_YOU_WANT_MY_BLESSING_TO_IMPROVE_YOUR_SKILLS;
	
	@ClientString(id = 7367, message = "$s1! Why do you reject guaranteed victory?")
	public static NpcStringId S1_WHY_DO_YOU_REJECT_GUARANTEED_VICTORY;
	
	@ClientString(id = 7401, message = "Saga of the Dreadnought")
	public static NpcStringId SAGA_OF_THE_DREADNOUGHT;
	
	@ClientString(id = 7402, message = "Saga of the Dreadnought (In Progress)")
	public static NpcStringId SAGA_OF_THE_DREADNOUGHT_IN_PROGRESS;
	
	@ClientString(id = 7403, message = "Saga of the Dreadnought (Done)")
	public static NpcStringId SAGA_OF_THE_DREADNOUGHT_DONE;
	
	@ClientString(id = 7450, message = "In the name of gods, I punish you, $s1! You can't rival us all, no matter how strong you think you are!")
	public static NpcStringId IN_THE_NAME_OF_GODS_I_PUNISH_YOU_S1_YOU_CAN_T_RIVAL_US_ALL_NO_MATTER_HOW_STRONG_YOU_THINK_YOU_ARE;
	
	@ClientString(id = 7451, message = "I have to stop for now, but I'll defeat the power of the dragon yet!")
	public static NpcStringId I_HAVE_TO_STOP_FOR_NOW_BUT_I_LL_DEFEAT_THE_POWER_OF_THE_DRAGON_YET;
	
	@ClientString(id = 7452, message = "It is...The power that shouldn't live!")
	public static NpcStringId IT_IS_THE_POWER_THAT_SHOULDN_T_LIVE;
	
	@ClientString(id = 7453, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_4;
	
	@ClientString(id = 7454, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_4;
	
	@ClientString(id = 7455, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_5;
	
	@ClientString(id = 7456, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_4;
	
	@ClientString(id = 7457, message = "Isn't it unwise for an angel to interfere in Human affairs?")
	public static NpcStringId ISN_T_IT_UNWISE_FOR_AN_ANGEL_TO_INTERFERE_IN_HUMAN_AFFAIRS;
	
	@ClientString(id = 7458, message = "This is tough! $s1, would you help me?")
	public static NpcStringId THIS_IS_TOUGH_S1_WOULD_YOU_HELP_ME;
	
	@ClientString(id = 7459, message = "$s1! He's keeping an eye on all those in succession to the blood of dragons, including you!")
	public static NpcStringId S1_HE_S_KEEPING_AN_EYE_ON_ALL_THOSE_IN_SUCCESSION_TO_THE_BLOOD_OF_DRAGONS_INCLUDING_YOU;
	
	@ClientString(id = 7460, message = "Attack the rear, $s1! If I'm killed, you're next!")
	public static NpcStringId ATTACK_THE_REAR_S1_IF_I_M_KILLED_YOU_RE_NEXT;
	
	@ClientString(id = 7461, message = "I can't stay any longer. There are too many eyes on us. Farewell!")
	public static NpcStringId I_CAN_T_STAY_ANY_LONGER_THERE_ARE_TOO_MANY_EYES_ON_US_FAREWELL;
	
	@ClientString(id = 7462, message = "Get away? You're still alive. But...")
	public static NpcStringId GET_AWAY_YOU_RE_STILL_ALIVE_BUT;
	
	@ClientString(id = 7463, message = "If we can't avoid this fight, we'll need great strength. It's the only way to peace.")
	public static NpcStringId IF_WE_CAN_T_AVOID_THIS_FIGHT_WE_LL_NEED_GREAT_STRENGTH_IT_S_THE_ONLY_WAY_TO_PEACE;
	
	@ClientString(id = 7464, message = "Warlord, you'll never learn the techniques of the dragon!")
	public static NpcStringId WARLORD_YOU_LL_NEVER_LEARN_THE_TECHNIQUES_OF_THE_DRAGON;
	
	@ClientString(id = 7465, message = "Hey, why bother with this. Isn't it your mother's business?")
	public static NpcStringId HEY_WHY_BOTHER_WITH_THIS_ISN_T_IT_YOUR_MOTHER_S_BUSINESS;
	
	@ClientString(id = 7466, message = "$s1! Are you against your mother's wishes? You're not worthy of the secrets of Shilen's children!")
	public static NpcStringId S1_ARE_YOU_AGAINST_YOUR_MOTHER_S_WISHES_YOU_RE_NOT_WORTHY_OF_THE_SECRETS_OF_SHILEN_S_CHILDREN;
	
	@ClientString(id = 7467, message = "Excellent technique, $s1. Unfortunately, you're the one destined for tragedy!")
	public static NpcStringId EXCELLENT_TECHNIQUE_S1_UNFORTUNATELY_YOU_RE_THE_ONE_DESTINED_FOR_TRAGEDY;
	
	@ClientString(id = 7501, message = "Saga of the Titan")
	public static NpcStringId SAGA_OF_THE_TITAN;
	
	@ClientString(id = 7502, message = "Saga of the Titan (In Progress)")
	public static NpcStringId SAGA_OF_THE_TITAN_IN_PROGRESS;
	
	@ClientString(id = 7503, message = "Saga of the Titan (Done)")
	public static NpcStringId SAGA_OF_THE_TITAN_DONE;
	
	@ClientString(id = 7550, message = "$s1! You may follow me, but an Orc is no match for my giant's strength!")
	public static NpcStringId S1_YOU_MAY_FOLLOW_ME_BUT_AN_ORC_IS_NO_MATCH_FOR_MY_GIANT_S_STRENGTH;
	
	@ClientString(id = 7551, message = "Kuh...My body fails..This is the end!")
	public static NpcStringId KUH_MY_BODY_FAILS_THIS_IS_THE_END;
	
	@ClientString(id = 7552, message = "How could I lose with the powers of a giant? Aargh!!!")
	public static NpcStringId HOW_COULD_I_LOSE_WITH_THE_POWERS_OF_A_GIANT_AARGH;
	
	@ClientString(id = 7553, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_5;
	
	@ClientString(id = 7554, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_5;
	
	@ClientString(id = 7555, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_6;
	
	@ClientString(id = 7556, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_5;
	
	@ClientString(id = 7557, message = "That's the enemy.")
	public static NpcStringId THAT_S_THE_ENEMY;
	
	@ClientString(id = 7558, message = "Hmm.. $s1! Will you just stand there doing nothing?")
	public static NpcStringId HMM_S1_WILL_YOU_JUST_STAND_THERE_DOING_NOTHING;
	
	@ClientString(id = 7559, message = "$s1. Nothing risked, nothing gained. Only those who fight enjoy the spoils of victory.")
	public static NpcStringId S1_NOTHING_RISKED_NOTHING_GAINED_ONLY_THOSE_WHO_FIGHT_ENJOY_THE_SPOILS_OF_VICTORY;
	
	@ClientString(id = 7560, message = "A sword isn't jewelry, $s1. Don't you agree?")
	public static NpcStringId A_SWORD_ISN_T_JEWELRY_S1_DON_T_YOU_AGREE;
	
	@ClientString(id = 7561, message = "With no fight, I have no reason to stay.")
	public static NpcStringId WITH_NO_FIGHT_I_HAVE_NO_REASON_TO_STAY;
	
	@ClientString(id = 7562, message = "Miss...")
	public static NpcStringId MISS;
	
	@ClientString(id = 7563, message = "Pick your battles wisely, or you'll regret it.")
	public static NpcStringId PICK_YOUR_BATTLES_WISELY_OR_YOU_LL_REGRET_IT;
	
	@ClientString(id = 7564, message = "What a fool to challenge the giant of the Oroka tribe!")
	public static NpcStringId WHAT_A_FOOL_TO_CHALLENGE_THE_GIANT_OF_THE_OROKA_TRIBE;
	
	@ClientString(id = 7565, message = "Running low on steam. I must withdraw.")
	public static NpcStringId RUNNING_LOW_ON_STEAM_I_MUST_WITHDRAW;
	
	@ClientString(id = 7566, message = "$s1. You're the one who defeated Guardian Muhark!")
	public static NpcStringId S1_YOU_RE_THE_ONE_WHO_DEFEATED_GUARDIAN_MUHARK;
	
	@ClientString(id = 7567, message = "$s1....! I must succeed...")
	public static NpcStringId S1_I_MUST_SUCCEED;
	
	@ClientString(id = 7601, message = "Saga of the Grand Khavatari")
	public static NpcStringId SAGA_OF_THE_GRAND_KHAVATARI;
	
	@ClientString(id = 7602, message = "Saga of the Grand Khavatari (In Progress)")
	public static NpcStringId SAGA_OF_THE_GRAND_KHAVATARI_IN_PROGRESS;
	
	@ClientString(id = 7603, message = "Saga of the Grand Khavatari (Done)")
	public static NpcStringId SAGA_OF_THE_GRAND_KHAVATARI_DONE;
	
	@ClientString(id = 7650, message = "$s1... Would you fight Uruz, who has reached the power of Azira?")
	public static NpcStringId S1_WOULD_YOU_FIGHT_URUZ_WHO_HAS_REACHED_THE_POWER_OF_AZIRA;
	
	@ClientString(id = 7651, message = "I can't handle the power of Azira yet. First...")
	public static NpcStringId I_CAN_T_HANDLE_THE_POWER_OF_AZIRA_YET_FIRST;
	
	@ClientString(id = 7652, message = "This can't be happening! I have the power of Azira! How could I fall so easily?")
	public static NpcStringId THIS_CAN_T_BE_HAPPENING_I_HAVE_THE_POWER_OF_AZIRA_HOW_COULD_I_FALL_SO_EASILY;
	
	@ClientString(id = 7653, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_6;
	
	@ClientString(id = 7654, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_6;
	
	@ClientString(id = 7655, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_7;
	
	@ClientString(id = 7656, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_6;
	
	@ClientString(id = 7657, message = "Azira, born from the Evil Flame, I'll kill you with my bare hands!")
	public static NpcStringId AZIRA_BORN_FROM_THE_EVIL_FLAME_I_LL_KILL_YOU_WITH_MY_BARE_HANDS;
	
	@ClientString(id = 7658, message = "$s1! In the name of Khavatari Hubai, strike this evil with your fists!")
	public static NpcStringId S1_IN_THE_NAME_OF_KHAVATARI_HUBAI_STRIKE_THIS_EVIL_WITH_YOUR_FISTS;
	
	@ClientString(id = 7659, message = "$s1! Attack from both sides! Hit hard!")
	public static NpcStringId S1_ATTACK_FROM_BOTH_SIDES_HIT_HARD;
	
	@ClientString(id = 7660, message = "$s1! Strike with all your heart. It must work.")
	public static NpcStringId S1_STRIKE_WITH_ALL_YOUR_HEART_IT_MUST_WORK;
	
	@ClientString(id = 7661, message = "Damn! It's time to go. Until next time.")
	public static NpcStringId DAMN_IT_S_TIME_TO_GO_UNTIL_NEXT_TIME;
	
	@ClientString(id = 7662, message = "Evil spirit of flame! I won't give up!")
	public static NpcStringId EVIL_SPIRIT_OF_FLAME_I_WON_T_GIVE_UP;
	
	@ClientString(id = 7663, message = "My fist works even on the evil spirit. Don't forget!")
	public static NpcStringId MY_FIST_WORKS_EVEN_ON_THE_EVIL_SPIRIT_DON_T_FORGET;
	
	@ClientString(id = 7664, message = "Foolish Khavatari...Do you think your power will work on me? I'm the source of your martial art!")
	public static NpcStringId FOOLISH_KHAVATARI_DO_YOU_THINK_YOUR_POWER_WILL_WORK_ON_ME_I_M_THE_SOURCE_OF_YOUR_MARTIAL_ART;
	
	@ClientString(id = 7665, message = "No more games...")
	public static NpcStringId NO_MORE_GAMES;
	
	@ClientString(id = 7666, message = "$s1...Are you next after Khavatari? Do you know who I am?")
	public static NpcStringId S1_ARE_YOU_NEXT_AFTER_KHAVATARI_DO_YOU_KNOW_WHO_I_AM;
	
	@ClientString(id = 7667, message = "$s1...Kashu. Not a bad attack. I can't hold on much longer.")
	public static NpcStringId S1_KASHU_NOT_A_BAD_ATTACK_I_CAN_T_HOLD_ON_MUCH_LONGER;
	
	@ClientString(id = 7701, message = "Saga of the Dominator")
	public static NpcStringId SAGA_OF_THE_DOMINATOR;
	
	@ClientString(id = 7702, message = "Saga of the Dominator (In Progress)")
	public static NpcStringId SAGA_OF_THE_DOMINATOR_IN_PROGRESS;
	
	@ClientString(id = 7703, message = "Saga of the Dominator (Done)")
	public static NpcStringId SAGA_OF_THE_DOMINATOR_DONE;
	
	@ClientString(id = 7750, message = "$s1, Akkan, you can't be my rival! I'll kill everything! I'll be the king!")
	public static NpcStringId S1_AKKAN_YOU_CAN_T_BE_MY_RIVAL_I_LL_KILL_EVERYTHING_I_LL_BE_THE_KING;
	
	@ClientString(id = 7751, message = "Ha! I'll show mercy on you this time. I know well of your technique!")
	public static NpcStringId HA_I_LL_SHOW_MERCY_ON_YOU_THIS_TIME_I_KNOW_WELL_OF_YOUR_TECHNIQUE;
	
	@ClientString(id = 7752, message = "I have in me the blood of a king! How could I lose?!")
	public static NpcStringId I_HAVE_IN_ME_THE_BLOOD_OF_A_KING_HOW_COULD_I_LOSE;
	
	@ClientString(id = 7753, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_7;
	
	@ClientString(id = 7754, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_7;
	
	@ClientString(id = 7755, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_8;
	
	@ClientString(id = 7756, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_7;
	
	@ClientString(id = 7757, message = "Are you....tyrant!")
	public static NpcStringId ARE_YOU_TYRANT;
	
	@ClientString(id = 7758, message = "You're not a king! You're just a tyrant! $s1, we must fight together!")
	public static NpcStringId YOU_RE_NOT_A_KING_YOU_RE_JUST_A_TYRANT_S1_WE_MUST_FIGHT_TOGETHER;
	
	@ClientString(id = 7759, message = "Such rule is ruining the country! $s1, I can't bear this tyranny any longer!")
	public static NpcStringId SUCH_RULE_IS_RUINING_THE_COUNTRY_S1_I_CAN_T_BEAR_THIS_TYRANNY_ANY_LONGER;
	
	@ClientString(id = 7760, message = "$s1, leaders must always resist tyranny!")
	public static NpcStringId S1_LEADERS_MUST_ALWAYS_RESIST_TYRANNY;
	
	@ClientString(id = 7761, message = "I stayed too long. I'll be punished for being away so long.")
	public static NpcStringId I_STAYED_TOO_LONG_I_LL_BE_PUNISHED_FOR_BEING_AWAY_SO_LONG;
	
	@ClientString(id = 7762, message = "He got away, Dammit! We must catch this dark soul!")
	public static NpcStringId HE_GOT_AWAY_DAMMIT_WE_MUST_CATCH_THIS_DARK_SOUL;
	
	@ClientString(id = 7763, message = "What is a king? What must one do to be a good king? Think it over.")
	public static NpcStringId WHAT_IS_A_KING_WHAT_MUST_ONE_DO_TO_BE_A_GOOD_KING_THINK_IT_OVER;
	
	@ClientString(id = 7764, message = "Kneel down before me! Foolish people!")
	public static NpcStringId KNEEL_DOWN_BEFORE_ME_FOOLISH_PEOPLE;
	
	@ClientString(id = 7765, message = "It's time for the king to leave! Bow your head and say goodbye!")
	public static NpcStringId IT_S_TIME_FOR_THE_KING_TO_LEAVE_BOW_YOUR_HEAD_AND_SAY_GOODBYE;
	
	@ClientString(id = 7766, message = "$s1! You dare to fight me? A king's power must be great to rule!")
	public static NpcStringId S1_YOU_DARE_TO_FIGHT_ME_A_KING_S_POWER_MUST_BE_GREAT_TO_RULE;
	
	@ClientString(id = 7767, message = "You would fight the king, $s1? Traitor!")
	public static NpcStringId YOU_WOULD_FIGHT_THE_KING_S1_TRAITOR;
	
	@ClientString(id = 7801, message = "Saga of the Doomcryer")
	public static NpcStringId SAGA_OF_THE_DOOMCRYER;
	
	@ClientString(id = 7802, message = "Saga of the Doomcryer (In Progress)")
	public static NpcStringId SAGA_OF_THE_DOOMCRYER_IN_PROGRESS;
	
	@ClientString(id = 7803, message = "Saga of the Doomcryer (Done)")
	public static NpcStringId SAGA_OF_THE_DOOMCRYER_DONE;
	
	@ClientString(id = 7850, message = "Tejakar Sharuhi! $s1, I'll show you the power of Sharuhi Mouth Mudaha!")
	public static NpcStringId TEJAKAR_SHARUHI_S1_I_LL_SHOW_YOU_THE_POWER_OF_SHARUHI_MOUTH_MUDAHA;
	
	@ClientString(id = 7851, message = "Aaargh! My soul won't keep quiet. Now I must take my leave.")
	public static NpcStringId AAARGH_MY_SOUL_WON_T_KEEP_QUIET_NOW_I_MUST_TAKE_MY_LEAVE;
	
	@ClientString(id = 7852, message = "No, Sharuhi. You're soul is mine!")
	public static NpcStringId NO_SHARUHI_YOU_RE_SOUL_IS_MINE;
	
	@ClientString(id = 7853, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_8;
	
	@ClientString(id = 7854, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_8;
	
	@ClientString(id = 7855, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_9;
	
	@ClientString(id = 7856, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_8;
	
	@ClientString(id = 7857, message = "Tejakar Oroca! Tejakar Duda-Mara!")
	public static NpcStringId TEJAKAR_OROCA_TEJAKAR_DUDA_MARA;
	
	@ClientString(id = 7858, message = "$s1, we must fight this soul together to prevent an everlasting winter!")
	public static NpcStringId S1_WE_MUST_FIGHT_THIS_SOUL_TOGETHER_TO_PREVENT_AN_EVERLASTING_WINTER;
	
	@ClientString(id = 7859, message = "$s1! The soul responds to you. May your attack quiet him!")
	public static NpcStringId S1_THE_SOUL_RESPONDS_TO_YOU_MAY_YOUR_ATTACK_QUIET_HIM;
	
	@ClientString(id = 7860, message = "$s1! Calm Sharuhi! He doesn't listen to me anymore.")
	public static NpcStringId S1_CALM_SHARUHI_HE_DOESN_T_LISTEN_TO_ME_ANYMORE;
	
	@ClientString(id = 7861, message = "It's time to go! May the eternal flame bless you!")
	public static NpcStringId IT_S_TIME_TO_GO_MAY_THE_ETERNAL_FLAME_BLESS_YOU;
	
	@ClientString(id = 7862, message = "He left...That's too bad..Too bad...")
	public static NpcStringId HE_LEFT_THAT_S_TOO_BAD_TOO_BAD;
	
	@ClientString(id = 7863, message = "Don't forget your strong will now!")
	public static NpcStringId DON_T_FORGET_YOUR_STRONG_WILL_NOW;
	
	@ClientString(id = 7864, message = "Ha! Nobody will rule over me anymore!")
	public static NpcStringId HA_NOBODY_WILL_RULE_OVER_ME_ANYMORE;
	
	@ClientString(id = 7865, message = "Freedom... freedom... freedom!")
	public static NpcStringId FREEDOM_FREEDOM_FREEDOM;
	
	@ClientString(id = 7866, message = "$s1, You released me, but you also want to catch me. Ha!")
	public static NpcStringId S1_YOU_RELEASED_ME_BUT_YOU_ALSO_WANT_TO_CATCH_ME_HA;
	
	@ClientString(id = 7867, message = "...$s1...Me?....All right...I'll help you.")
	public static NpcStringId S1_ME_ALL_RIGHT_I_LL_HELP_YOU;
	
	@ClientString(id = 7901, message = "Saga of the Adventurer")
	public static NpcStringId SAGA_OF_THE_ADVENTURER;
	
	@ClientString(id = 7902, message = "Saga of the Adventurer (In Progress)")
	public static NpcStringId SAGA_OF_THE_ADVENTURER_IN_PROGRESS;
	
	@ClientString(id = 7903, message = "Saga of the Adventurer (Done)")
	public static NpcStringId SAGA_OF_THE_ADVENTURER_DONE;
	
	@ClientString(id = 7950, message = "Get out of here! This place is forbidden by god.")
	public static NpcStringId GET_OUT_OF_HERE_THIS_PLACE_IS_FORBIDDEN_BY_GOD;
	
	@ClientString(id = 7951, message = "Einhasad is calling me.")
	public static NpcStringId EINHASAD_IS_CALLING_ME;
	
	@ClientString(id = 7952, message = "You killed me! Aren't you afraid of god's curse?")
	public static NpcStringId YOU_KILLED_ME_AREN_T_YOU_AFRAID_OF_GOD_S_CURSE;
	
	@ClientString(id = 7953, message = "You bother my minions, $s1. Do you want to die?")
	public static NpcStringId YOU_BOTHER_MY_MINIONS_S1_DO_YOU_WANT_TO_DIE;
	
	@ClientString(id = 7954, message = "What the hell... I lost.")
	public static NpcStringId WHAT_THE_HELL_I_LOST;
	
	@ClientString(id = 7955, message = "Who are you? Why are you interfering in our business?")
	public static NpcStringId WHO_ARE_YOU_WHY_ARE_YOU_INTERFERING_IN_OUR_BUSINESS;
	
	@ClientString(id = 7956, message = "You're strong. I'll get you next time!")
	public static NpcStringId YOU_RE_STRONG_I_LL_GET_YOU_NEXT_TIME;
	
	@ClientString(id = 7957, message = "We meet again. I'll have you this time!")
	public static NpcStringId WE_MEET_AGAIN_I_LL_HAVE_YOU_THIS_TIME;
	
	@ClientString(id = 7958, message = "A worthy opponent. $s1. Help me!")
	public static NpcStringId A_WORTHY_OPPONENT_S1_HELP_ME;
	
	@ClientString(id = 7959, message = "$s1! Hurry before he gets away!")
	public static NpcStringId S1_HURRY_BEFORE_HE_GETS_AWAY;
	
	@ClientString(id = 7960, message = "I'll kill you!")
	public static NpcStringId I_LL_KILL_YOU;
	
	@ClientString(id = 7961, message = "Why don't you fight me someday?")
	public static NpcStringId WHY_DON_T_YOU_FIGHT_ME_SOMEDAY;
	
	@ClientString(id = 7962, message = "I missed again. Dammit!")
	public static NpcStringId I_MISSED_AGAIN_DAMMIT;
	
	@ClientString(id = 7963, message = "I'm sure we'll meet again someday.")
	public static NpcStringId I_M_SURE_WE_LL_MEET_AGAIN_SOMEDAY;
	
	@ClientString(id = 7964, message = "Curse those who defy the gods!")
	public static NpcStringId CURSE_THOSE_WHO_DEFY_THE_GODS;
	
	@ClientString(id = 7965, message = "Einhasad is calling me.")
	public static NpcStringId EINHASAD_IS_CALLING_ME_2;
	
	@ClientString(id = 7966, message = "You would fight me, a messenger of the gods?")
	public static NpcStringId YOU_WOULD_FIGHT_ME_A_MESSENGER_OF_THE_GODS;
	
	@ClientString(id = 7967, message = "$s1! I won't forget you.")
	public static NpcStringId S1_I_WON_T_FORGET_YOU;
	
	@ClientString(id = 8001, message = "Saga of the Wind Rider")
	public static NpcStringId SAGA_OF_THE_WIND_RIDER;
	
	@ClientString(id = 8002, message = "Saga of the Wind Rider (In Progress)")
	public static NpcStringId SAGA_OF_THE_WIND_RIDER_IN_PROGRESS;
	
	@ClientString(id = 8003, message = "Saga of the Wind Rider (Done)")
	public static NpcStringId SAGA_OF_THE_WIND_RIDER_DONE;
	
	@ClientString(id = 8050, message = "$s1! How could you desecrate a holy place?")
	public static NpcStringId S1_HOW_COULD_YOU_DESECRATE_A_HOLY_PLACE;
	
	@ClientString(id = 8051, message = "Leave before you are severely punished!")
	public static NpcStringId LEAVE_BEFORE_YOU_ARE_SEVERELY_PUNISHED;
	
	@ClientString(id = 8052, message = "Einhasad, don't give up on me!")
	public static NpcStringId EINHASAD_DON_T_GIVE_UP_ON_ME;
	
	@ClientString(id = 8053, message = "$s1, so you're the one who's looking for me?")
	public static NpcStringId S1_SO_YOU_RE_THE_ONE_WHO_S_LOOKING_FOR_ME;
	
	@ClientString(id = 8054, message = "A mere mortal has defeated me!")
	public static NpcStringId A_MERE_MORTAL_HAS_DEFEATED_ME;
	
	@ClientString(id = 8055, message = "How cowardly to intrude in other people's business.")
	public static NpcStringId HOW_COWARDLY_TO_INTRUDE_IN_OTHER_PEOPLE_S_BUSINESS;
	
	@ClientString(id = 8056, message = "Time is up.")
	public static NpcStringId TIME_IS_UP;
	
	@ClientString(id = 8057, message = "I'll kill you with my sword!")
	public static NpcStringId I_LL_KILL_YOU_WITH_MY_SWORD;
	
	@ClientString(id = 8058, message = "Help me!")
	public static NpcStringId HELP_ME;
	
	@ClientString(id = 8059, message = "Don't miss!")
	public static NpcStringId DON_T_MISS;
	
	@ClientString(id = 8060, message = "Keep pushing!")
	public static NpcStringId KEEP_PUSHING;
	
	@ClientString(id = 8061, message = "I'll get him. You'll have your revenge.")
	public static NpcStringId I_LL_GET_HIM_YOU_LL_HAVE_YOUR_REVENGE;
	
	@ClientString(id = 8062, message = "I missed him again. I'll kill you.")
	public static NpcStringId I_MISSED_HIM_AGAIN_I_LL_KILL_YOU;
	
	@ClientString(id = 8063, message = "I must follow him.")
	public static NpcStringId I_MUST_FOLLOW_HIM;
	
	@ClientString(id = 8064, message = "Curse those who defy the gods!")
	public static NpcStringId CURSE_THOSE_WHO_DEFY_THE_GODS_2;
	
	@ClientString(id = 8065, message = "Einhasad is calling me.")
	public static NpcStringId EINHASAD_IS_CALLING_ME_3;
	
	@ClientString(id = 8066, message = "You would fight me, a messenger of the gods?")
	public static NpcStringId YOU_WOULD_FIGHT_ME_A_MESSENGER_OF_THE_GODS_2;
	
	@ClientString(id = 8067, message = "$s1! I won't forget you.")
	public static NpcStringId S1_I_WON_T_FORGET_YOU_2;
	
	@ClientString(id = 8101, message = "Saga of the Ghost Hunter")
	public static NpcStringId SAGA_OF_THE_GHOST_HUNTER;
	
	@ClientString(id = 8102, message = "Saga of the Ghost Hunter (In Progress)")
	public static NpcStringId SAGA_OF_THE_GHOST_HUNTER_IN_PROGRESS;
	
	@ClientString(id = 8103, message = "Saga of the Ghost Hunter (Done)")
	public static NpcStringId SAGA_OF_THE_GHOST_HUNTER_DONE;
	
	@ClientString(id = 8150, message = "$s1, you should leave if you fear god's wrath!")
	public static NpcStringId S1_YOU_SHOULD_LEAVE_IF_YOU_FEAR_GOD_S_WRATH;
	
	@ClientString(id = 8151, message = "What's going on?")
	public static NpcStringId WHAT_S_GOING_ON;
	
	@ClientString(id = 8152, message = "I'll see you again!")
	public static NpcStringId I_LL_SEE_YOU_AGAIN;
	
	@ClientString(id = 8153, message = "Who are you? Why are you bothering my minions?")
	public static NpcStringId WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS;
	
	@ClientString(id = 8154, message = "No way!!!")
	public static NpcStringId NO_WAY;
	
	@ClientString(id = 8155, message = "Why are you sticking your nose in our business?")
	public static NpcStringId WHY_ARE_YOU_STICKING_YOUR_NOSE_IN_OUR_BUSINESS;
	
	@ClientString(id = 8156, message = "Who are you? How can a creature from the netherworld be so powerful?")
	public static NpcStringId WHO_ARE_YOU_HOW_CAN_A_CREATURE_FROM_THE_NETHERWORLD_BE_SO_POWERFUL;
	
	@ClientString(id = 8157, message = "Is this the end?")
	public static NpcStringId IS_THIS_THE_END;
	
	@ClientString(id = 8158, message = "Show me what you're made of. Kill him!")
	public static NpcStringId SHOW_ME_WHAT_YOU_RE_MADE_OF_KILL_HIM;
	
	@ClientString(id = 8159, message = "You think you can get him with that?")
	public static NpcStringId YOU_THINK_YOU_CAN_GET_HIM_WITH_THAT;
	
	@ClientString(id = 8160, message = "Pull yourself together! He's trying to get away.")
	public static NpcStringId PULL_YOURSELF_TOGETHER_HE_S_TRYING_TO_GET_AWAY;
	
	@ClientString(id = 8161, message = "Tell the Black Cat that I got his paid back.")
	public static NpcStringId TELL_THE_BLACK_CAT_THAT_I_GOT_HIS_PAID_BACK;
	
	@ClientString(id = 8162, message = "Black Cat, he'll blame me.")
	public static NpcStringId BLACK_CAT_HE_LL_BLAME_ME;
	
	@ClientString(id = 8163, message = "I gotta' go now.")
	public static NpcStringId I_GOTTA_GO_NOW;
	
	@ClientString(id = 8164, message = "Curse those who defy the gods!")
	public static NpcStringId CURSE_THOSE_WHO_DEFY_THE_GODS_3;
	
	@ClientString(id = 8165, message = "Einhasad is calling me.")
	public static NpcStringId EINHASAD_IS_CALLING_ME_4;
	
	@ClientString(id = 8166, message = "I'll kill you in the name of god.")
	public static NpcStringId I_LL_KILL_YOU_IN_THE_NAME_OF_GOD;
	
	@ClientString(id = 8167, message = "$s1! See you later.")
	public static NpcStringId S1_SEE_YOU_LATER;
	
	@ClientString(id = 8201, message = "Saga of the Sagittarius")
	public static NpcStringId SAGA_OF_THE_SAGITTARIUS;
	
	@ClientString(id = 8202, message = "Saga of the Sagittarius (In Progress)")
	public static NpcStringId SAGA_OF_THE_SAGITTARIUS_IN_PROGRESS;
	
	@ClientString(id = 8203, message = "Saga of the Sagittarius (Done)")
	public static NpcStringId SAGA_OF_THE_SAGITTARIUS_DONE;
	
	@ClientString(id = 8250, message = "$s1! How could you desecrate a holy place?")
	public static NpcStringId S1_HOW_COULD_YOU_DESECRATE_A_HOLY_PLACE_2;
	
	@ClientString(id = 8251, message = "Get out before you're punished!")
	public static NpcStringId GET_OUT_BEFORE_YOU_RE_PUNISHED;
	
	@ClientString(id = 8252, message = "Einhasad, please don't give up on me!")
	public static NpcStringId EINHASAD_PLEASE_DON_T_GIVE_UP_ON_ME;
	
	@ClientString(id = 8253, message = "$s1, are you looking for me?")
	public static NpcStringId S1_ARE_YOU_LOOKING_FOR_ME;
	
	@ClientString(id = 8254, message = "A mere mortal is killing me!")
	public static NpcStringId A_MERE_MORTAL_IS_KILLING_ME;
	
	@ClientString(id = 8255, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_10;
	
	@ClientString(id = 8256, message = "Mortal, don't you recognize my greatness?")
	public static NpcStringId MORTAL_DON_T_YOU_RECOGNIZE_MY_GREATNESS;
	
	@ClientString(id = 8257, message = "I'll get you this time.")
	public static NpcStringId I_LL_GET_YOU_THIS_TIME;
	
	@ClientString(id = 8258, message = "I'll never forget the taste of his steel, $s1! Let's fight him together!")
	public static NpcStringId I_LL_NEVER_FORGET_THE_TASTE_OF_HIS_STEEL_S1_LET_S_FIGHT_HIM_TOGETHER;
	
	@ClientString(id = 8259, message = "$s1! Pull yourself together. We'll miss him!")
	public static NpcStringId S1_PULL_YOURSELF_TOGETHER_WE_LL_MISS_HIM;
	
	@ClientString(id = 8260, message = "$s1! He's trying to get away.")
	public static NpcStringId S1_HE_S_TRYING_TO_GET_AWAY;
	
	@ClientString(id = 8261, message = "I missed again! Next time...")
	public static NpcStringId I_MISSED_AGAIN_NEXT_TIME;
	
	@ClientString(id = 8262, message = "Dammit! Failed again!")
	public static NpcStringId DAMMIT_FAILED_AGAIN;
	
	@ClientString(id = 8263, message = "I gotta' go now.")
	public static NpcStringId I_GOTTA_GO_NOW_2;
	
	@ClientString(id = 8264, message = "Curse those who defy the gods!")
	public static NpcStringId CURSE_THOSE_WHO_DEFY_THE_GODS_4;
	
	@ClientString(id = 8265, message = "Einhasad is calling me.")
	public static NpcStringId EINHASAD_IS_CALLING_ME_5;
	
	@ClientString(id = 8266, message = "You would fight me, a messenger of the gods?")
	public static NpcStringId YOU_WOULD_FIGHT_ME_A_MESSENGER_OF_THE_GODS_3;
	
	@ClientString(id = 8267, message = "$s1! I won't forget you.")
	public static NpcStringId S1_I_WON_T_FORGET_YOU_3;
	
	@ClientString(id = 8301, message = "Saga of the Moonlight Sentinel")
	public static NpcStringId SAGA_OF_THE_MOONLIGHT_SENTINEL;
	
	@ClientString(id = 8302, message = "Saga of the Moonlight Sentinel (In Progress)")
	public static NpcStringId SAGA_OF_THE_MOONLIGHT_SENTINEL_IN_PROGRESS;
	
	@ClientString(id = 8303, message = "Saga of the Moonlight Sentinel (Done)")
	public static NpcStringId SAGA_OF_THE_MOONLIGHT_SENTINEL_DONE;
	
	@ClientString(id = 8350, message = "$s1! How could you desecrate a holy place?")
	public static NpcStringId S1_HOW_COULD_YOU_DESECRATE_A_HOLY_PLACE_3;
	
	@ClientString(id = 8351, message = "Get out before you're punished!")
	public static NpcStringId GET_OUT_BEFORE_YOU_RE_PUNISHED_2;
	
	@ClientString(id = 8352, message = "Einhasad, don't give up on me!")
	public static NpcStringId EINHASAD_DON_T_GIVE_UP_ON_ME_2;
	
	@ClientString(id = 8353, message = "You are the one who's looking for me, $s1?")
	public static NpcStringId YOU_ARE_THE_ONE_WHO_S_LOOKING_FOR_ME_S1;
	
	@ClientString(id = 8354, message = "A mere mortal has killed me!")
	public static NpcStringId A_MERE_MORTAL_HAS_KILLED_ME;
	
	@ClientString(id = 8355, message = "Who are you? This is none of your business!")
	public static NpcStringId WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS;
	
	@ClientString(id = 8356, message = "Mortal, don't you recognize my greatness?")
	public static NpcStringId MORTAL_DON_T_YOU_RECOGNIZE_MY_GREATNESS_2;
	
	@ClientString(id = 8357, message = "I'll get you this time.")
	public static NpcStringId I_LL_GET_YOU_THIS_TIME_2;
	
	@ClientString(id = 8358, message = "I'll never forget the taste of his steel, $s1! Let's fight him together!")
	public static NpcStringId I_LL_NEVER_FORGET_THE_TASTE_OF_HIS_STEEL_S1_LET_S_FIGHT_HIM_TOGETHER_2;
	
	@ClientString(id = 8359, message = "$s1! Pull yourself together. ")
	public static NpcStringId S1_PULL_YOURSELF_TOGETHER;
	
	@ClientString(id = 8360, message = "$s1! He'll get away!")
	public static NpcStringId S1_HE_LL_GET_AWAY;
	
	@ClientString(id = 8361, message = "I missed again! Next time...")
	public static NpcStringId I_MISSED_AGAIN_NEXT_TIME_2;
	
	@ClientString(id = 8362, message = "Dammit! Failed again!")
	public static NpcStringId DAMMIT_FAILED_AGAIN_2;
	
	@ClientString(id = 8363, message = "I gotta' go now.")
	public static NpcStringId I_GOTTA_GO_NOW_3;
	
	@ClientString(id = 8364, message = "Curse those who defy the gods!")
	public static NpcStringId CURSE_THOSE_WHO_DEFY_THE_GODS_5;
	
	@ClientString(id = 8365, message = "Einhasad is calling me.")
	public static NpcStringId EINHASAD_IS_CALLING_ME_6;
	
	@ClientString(id = 8366, message = "You would fight me, a messenger of the gods?")
	public static NpcStringId YOU_WOULD_FIGHT_ME_A_MESSENGER_OF_THE_GODS_4;
	
	@ClientString(id = 8367, message = "$s1! I won't forget you.")
	public static NpcStringId S1_I_WON_T_FORGET_YOU_4;
	
	@ClientString(id = 8401, message = "Saga of the Ghost Sentinel")
	public static NpcStringId SAGA_OF_THE_GHOST_SENTINEL;
	
	@ClientString(id = 8402, message = "Saga of the Ghost Sentinel (In Progress)")
	public static NpcStringId SAGA_OF_THE_GHOST_SENTINEL_IN_PROGRESS;
	
	@ClientString(id = 8403, message = "Saga of the Ghost Sentinel (Done)")
	public static NpcStringId SAGA_OF_THE_GHOST_SENTINEL_DONE;
	
	@ClientString(id = 8450, message = "$s1! How could you desecrate a holy place?")
	public static NpcStringId S1_HOW_COULD_YOU_DESECRATE_A_HOLY_PLACE_4;
	
	@ClientString(id = 8451, message = "Get out before you're punished!")
	public static NpcStringId GET_OUT_BEFORE_YOU_RE_PUNISHED_3;
	
	@ClientString(id = 8452, message = "Einhasad, please don't forsake me!")
	public static NpcStringId EINHASAD_PLEASE_DON_T_FORSAKE_ME;
	
	@ClientString(id = 8453, message = "Looking for me, $s1?")
	public static NpcStringId LOOKING_FOR_ME_S1;
	
	@ClientString(id = 8454, message = "A mere mortal is killing me!")
	public static NpcStringId A_MERE_MORTAL_IS_KILLING_ME_2;
	
	@ClientString(id = 8455, message = "Who are you? This is none of your business!")
	public static NpcStringId WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS_2;
	
	@ClientString(id = 8456, message = "Mortal! Don't you recognize my greatness?")
	public static NpcStringId MORTAL_DON_T_YOU_RECOGNIZE_MY_GREATNESS_3;
	
	@ClientString(id = 8457, message = "I'll get you this time.")
	public static NpcStringId I_LL_GET_YOU_THIS_TIME_3;
	
	@ClientString(id = 8458, message = "I'll never forget the taste of his steel, $s1! Let's fight him together!")
	public static NpcStringId I_LL_NEVER_FORGET_THE_TASTE_OF_HIS_STEEL_S1_LET_S_FIGHT_HIM_TOGETHER_3;
	
	@ClientString(id = 8459, message = "$s1! Pull yourself together!")
	public static NpcStringId S1_PULL_YOURSELF_TOGETHER_2;
	
	@ClientString(id = 8460, message = "$s1! He'll get away!")
	public static NpcStringId S1_HE_LL_GET_AWAY_2;
	
	@ClientString(id = 8461, message = "I missed again! Next time...")
	public static NpcStringId I_MISSED_AGAIN_NEXT_TIME_3;
	
	@ClientString(id = 8462, message = "Dammit! Failed again!")
	public static NpcStringId DAMMIT_FAILED_AGAIN_3;
	
	@ClientString(id = 8463, message = "I gotta' go now.")
	public static NpcStringId I_GOTTA_GO_NOW_4;
	
	@ClientString(id = 8464, message = "Curse those who defy the gods!")
	public static NpcStringId CURSE_THOSE_WHO_DEFY_THE_GODS_6;
	
	@ClientString(id = 8465, message = "Einhasad is calling me.")
	public static NpcStringId EINHASAD_IS_CALLING_ME_7;
	
	@ClientString(id = 8466, message = "You would fight me, a messenger of the gods?")
	public static NpcStringId YOU_WOULD_FIGHT_ME_A_MESSENGER_OF_THE_GODS_5;
	
	@ClientString(id = 8467, message = "$s1! I won't forget you.")
	public static NpcStringId S1_I_WON_T_FORGET_YOU_5;
	
	@ClientString(id = 8501, message = "Saga of the Cardinal")
	public static NpcStringId SAGA_OF_THE_CARDINAL;
	
	@ClientString(id = 8502, message = "Saga of the Cardinal (In Progress)")
	public static NpcStringId SAGA_OF_THE_CARDINAL_IN_PROGRESS;
	
	@ClientString(id = 8503, message = "Saga of the Cardinal (Done)")
	public static NpcStringId SAGA_OF_THE_CARDINAL_DONE;
	
	@ClientString(id = 8550, message = "$s1! Bishop, how foolish to go against the will of god!")
	public static NpcStringId S1_BISHOP_HOW_FOOLISH_TO_GO_AGAINST_THE_WILL_OF_GOD;
	
	@ClientString(id = 8551, message = "Your faith is stronger than I thought. I'll pay you back next time.")
	public static NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_I_LL_PAY_YOU_BACK_NEXT_TIME;
	
	@ClientString(id = 8552, message = "Tanakia! Forgive me. I couldn't fulfill your dream!")
	public static NpcStringId TANAKIA_FORGIVE_ME_I_COULDN_T_FULFILL_YOUR_DREAM;
	
	@ClientString(id = 8553, message = "$s1, you are the won who's been bothering my minions?")
	public static NpcStringId S1_YOU_ARE_THE_WON_WHO_S_BEEN_BOTHERING_MY_MINIONS;
	
	@ClientString(id = 8554, message = "Damn! You've beaten me.")
	public static NpcStringId DAMN_YOU_VE_BEATEN_ME;
	
	@ClientString(id = 8555, message = "Who are you? This isn't your business, coward.")
	public static NpcStringId WHO_ARE_YOU_THIS_ISN_T_YOUR_BUSINESS_COWARD;
	
	@ClientString(id = 8556, message = "How weak. I'll forgive you this time because you made me laugh.")
	public static NpcStringId HOW_WEAK_I_LL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH;
	
	@ClientString(id = 8557, message = "You are stronger than I thought, but I'm no weakling!")
	public static NpcStringId YOU_ARE_STRONGER_THAN_I_THOUGHT_BUT_I_M_NO_WEAKLING;
	
	@ClientString(id = 8558, message = "He's got a tough shell. $s1! Let's fight together and crack his skull!")
	public static NpcStringId HE_S_GOT_A_TOUGH_SHELL_S1_LET_S_FIGHT_TOGETHER_AND_CRACK_HIS_SKULL;
	
	@ClientString(id = 8559, message = "$s1! Pull yourself together.")
	public static NpcStringId S1_PULL_YOURSELF_TOGETHER_3;
	
	@ClientString(id = 8560, message = "$s1! We won't beat him unless we give it our all. Come on!")
	public static NpcStringId S1_WE_WON_T_BEAT_HIM_UNLESS_WE_GIVE_IT_OUR_ALL_COME_ON;
	
	@ClientString(id = 8561, message = "I'll follow him.")
	public static NpcStringId I_LL_FOLLOW_HIM;
	
	@ClientString(id = 8562, message = "I missed again! He's hard to follow.")
	public static NpcStringId I_MISSED_AGAIN_HE_S_HARD_TO_FOLLOW;
	
	@ClientString(id = 8563, message = "We'll see what the future brings.")
	public static NpcStringId WE_LL_SEE_WHAT_THE_FUTURE_BRINGS;
	
	@ClientString(id = 8564, message = "For Shilen!")
	public static NpcStringId FOR_SHILEN;
	
	@ClientString(id = 8565, message = "I'll be back. I'll deal with you then.")
	public static NpcStringId I_LL_BE_BACK_I_LL_DEAL_WITH_YOU_THEN;
	
	@ClientString(id = 8566, message = "$s1! Are you going to fight me?")
	public static NpcStringId S1_ARE_YOU_GOING_TO_FIGHT_ME;
	
	@ClientString(id = 8567, message = "$s1! I'll pay you back. I won't forget you.")
	public static NpcStringId S1_I_LL_PAY_YOU_BACK_I_WON_T_FORGET_YOU;
	
	@ClientString(id = 8601, message = "Saga of the Hierophant")
	public static NpcStringId SAGA_OF_THE_HIEROPHANT;
	
	@ClientString(id = 8602, message = "Saga of the Hierophant (In Progress)")
	public static NpcStringId SAGA_OF_THE_HIEROPHANT_IN_PROGRESS;
	
	@ClientString(id = 8603, message = "Saga of the Hierophant (Done)")
	public static NpcStringId SAGA_OF_THE_HIEROPHANT_DONE;
	
	@ClientString(id = 8650, message = "$s1! Prophet, how foolish to go against the will of god!")
	public static NpcStringId S1_PROPHET_HOW_FOOLISH_TO_GO_AGAINST_THE_WILL_OF_GOD;
	
	@ClientString(id = 8651, message = "Your faith is stronger than I thought. I'll deal with you next time.")
	public static NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_I_LL_DEAL_WITH_YOU_NEXT_TIME;
	
	@ClientString(id = 8652, message = "Tanakia! Forgive me. I couldn't fulfill your dream!")
	public static NpcStringId TANAKIA_FORGIVE_ME_I_COULDN_T_FULFILL_YOUR_DREAM_2;
	
	@ClientString(id = 8653, message = "Are you the one who's been bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_S_BEEN_BOTHERING_MY_MINIONS_S1;
	
	@ClientString(id = 8654, message = "Damn! I can't believe I've been beaten by you!")
	public static NpcStringId DAMN_I_CAN_T_BELIEVE_I_VE_BEEN_BEATEN_BY_YOU;
	
	@ClientString(id = 8655, message = "Who are you? This is none of your business, coward.")
	public static NpcStringId WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS_COWARD;
	
	@ClientString(id = 8656, message = "How weak. I'll forgive you this time because you made me laugh.")
	public static NpcStringId HOW_WEAK_I_LL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH_2;
	
	@ClientString(id = 8657, message = "I'll destroy the darkness surrounding the world with the power of light!")
	public static NpcStringId I_LL_DESTROY_THE_DARKNESS_SURROUNDING_THE_WORLD_WITH_THE_POWER_OF_LIGHT;
	
	@ClientString(id = 8658, message = "$s1! Fight the Fallen Angel with me. Show the true power of light!")
	public static NpcStringId S1_FIGHT_THE_FALLEN_ANGEL_WITH_ME_SHOW_THE_TRUE_POWER_OF_LIGHT;
	
	@ClientString(id = 8659, message = "$s1! Go! We must stop fighting here.")
	public static NpcStringId S1_GO_WE_MUST_STOP_FIGHTING_HERE;
	
	@ClientString(id = 8660, message = "We mustn't lose, $s1! Pull yourself together!")
	public static NpcStringId WE_MUSTN_T_LOSE_S1_PULL_YOURSELF_TOGETHER;
	
	@ClientString(id = 8661, message = "We'll meet again if fate wills it.")
	public static NpcStringId WE_LL_MEET_AGAIN_IF_FATE_WILLS_IT;
	
	@ClientString(id = 8662, message = "I'll follow the cowardly devil.")
	public static NpcStringId I_LL_FOLLOW_THE_COWARDLY_DEVIL;
	
	@ClientString(id = 8663, message = "We'll meet again if fate wills it.")
	public static NpcStringId WE_LL_MEET_AGAIN_IF_FATE_WILLS_IT_2;
	
	@ClientString(id = 8664, message = "For Shilen!")
	public static NpcStringId FOR_SHILEN_2;
	
	@ClientString(id = 8665, message = "I'll be back. I'll deal with you then.")
	public static NpcStringId I_LL_BE_BACK_I_LL_DEAL_WITH_YOU_THEN_2;
	
	@ClientString(id = 8666, message = "$s1! Are you going to fight me?")
	public static NpcStringId S1_ARE_YOU_GOING_TO_FIGHT_ME_2;
	
	@ClientString(id = 8667, message = "$s1! I'll pay you back. I won't forget you.")
	public static NpcStringId S1_I_LL_PAY_YOU_BACK_I_WON_T_FORGET_YOU_2;
	
	@ClientString(id = 8701, message = "Saga of Eva's Saint")
	public static NpcStringId SAGA_OF_EVA_S_SAINT;
	
	@ClientString(id = 8702, message = "Saga of Eva's Saint (In Progress)")
	public static NpcStringId SAGA_OF_EVA_S_SAINT_IN_PROGRESS;
	
	@ClientString(id = 8703, message = "Saga of Eva's Saint (Done)")
	public static NpcStringId SAGA_OF_EVA_S_SAINT_DONE;
	
	@ClientString(id = 8750, message = "$s1! Elder, it's foolish of you to go against the will of the gods.")
	public static NpcStringId S1_ELDER_IT_S_FOOLISH_OF_YOU_TO_GO_AGAINST_THE_WILL_OF_THE_GODS;
	
	@ClientString(id = 8751, message = "Your faith is stronger than I thought. I'll pay you back next time.")
	public static NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_I_LL_PAY_YOU_BACK_NEXT_TIME_2;
	
	@ClientString(id = 8752, message = "Tanakia! Forgive me. I couldn't fulfill your dream!")
	public static NpcStringId TANAKIA_FORGIVE_ME_I_COULDN_T_FULFILL_YOUR_DREAM_3;
	
	@ClientString(id = 8753, message = "Are you the one who's been bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_S_BEEN_BOTHERING_MY_MINIONS_S1_2;
	
	@ClientString(id = 8754, message = "Damn! I can't believe I've been beaten by you.")
	public static NpcStringId DAMN_I_CAN_T_BELIEVE_I_VE_BEEN_BEATEN_BY_YOU_2;
	
	@ClientString(id = 8755, message = "Who are you? This is none of your business, coward.")
	public static NpcStringId WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS_COWARD_2;
	
	@ClientString(id = 8756, message = "How weak. I'll forgive you this time because you made me laugh.")
	public static NpcStringId HOW_WEAK_I_LL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH_3;
	
	@ClientString(id = 8757, message = "You're stronger than I thought, but I'm no weakling either!")
	public static NpcStringId YOU_RE_STRONGER_THAN_I_THOUGHT_BUT_I_M_NO_WEAKLING_EITHER;
	
	@ClientString(id = 8758, message = "He's got a tough shell. $s1! Let's fight together and crack his skull!")
	public static NpcStringId HE_S_GOT_A_TOUGH_SHELL_S1_LET_S_FIGHT_TOGETHER_AND_CRACK_HIS_SKULL_2;
	
	@ClientString(id = 8759, message = "$s1! Pull yourself together.")
	public static NpcStringId S1_PULL_YOURSELF_TOGETHER_4;
	
	@ClientString(id = 8760, message = "$s1! We'll never win unless we give it our all. Come on!")
	public static NpcStringId S1_WE_LL_NEVER_WIN_UNLESS_WE_GIVE_IT_OUR_ALL_COME_ON;
	
	@ClientString(id = 8761, message = "I'll follow him.")
	public static NpcStringId I_LL_FOLLOW_HIM_2;
	
	@ClientString(id = 8762, message = "I missed again! He's hard to follow.")
	public static NpcStringId I_MISSED_AGAIN_HE_S_HARD_TO_FOLLOW_2;
	
	@ClientString(id = 8763, message = "We'll see what the future brings.")
	public static NpcStringId WE_LL_SEE_WHAT_THE_FUTURE_BRINGS_2;
	
	@ClientString(id = 8764, message = "For Shilen!")
	public static NpcStringId FOR_SHILEN_3;
	
	@ClientString(id = 8765, message = "I'll be back. I'll deal with you then.")
	public static NpcStringId I_LL_BE_BACK_I_LL_DEAL_WITH_YOU_THEN_3;
	
	@ClientString(id = 8766, message = "$s1! Are you going to fight me?")
	public static NpcStringId S1_ARE_YOU_GOING_TO_FIGHT_ME_3;
	
	@ClientString(id = 8767, message = "$s1! I'll pay you back. I won't forget you.")
	public static NpcStringId S1_I_LL_PAY_YOU_BACK_I_WON_T_FORGET_YOU_3;
	
	@ClientString(id = 8801, message = "Saga of the Archmage")
	public static NpcStringId SAGA_OF_THE_ARCHMAGE;
	
	@ClientString(id = 8802, message = "Saga of the Archmage (In Progress)")
	public static NpcStringId SAGA_OF_THE_ARCHMAGE_IN_PROGRESS;
	
	@ClientString(id = 8803, message = "Saga of the Archmage (Done)")
	public static NpcStringId SAGA_OF_THE_ARCHMAGE_DONE;
	
	@ClientString(id = 8850, message = "Are you $s1? Oh! I have the Resonance Amulet!")
	public static NpcStringId ARE_YOU_S1_OH_I_HAVE_THE_RESONANCE_AMULET;
	
	@ClientString(id = 8851, message = "You're feistier than I thought! I'll quit here for today.")
	public static NpcStringId YOU_RE_FEISTIER_THAN_I_THOUGHT_I_LL_QUIT_HERE_FOR_TODAY;
	
	@ClientString(id = 8852, message = "Aaargh! I can't believe I lost...")
	public static NpcStringId AAARGH_I_CAN_T_BELIEVE_I_LOST;
	
	@ClientString(id = 8853, message = "Are you the one who's been bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_S_BEEN_BOTHERING_MY_MINIONS_S1_3;
	
	@ClientString(id = 8854, message = "Yikes! You're tough!")
	public static NpcStringId YIKES_YOU_RE_TOUGH;
	
	@ClientString(id = 8855, message = "Why do you interfere in other people's business...")
	public static NpcStringId WHY_DO_YOU_INTERFERE_IN_OTHER_PEOPLE_S_BUSINESS;
	
	@ClientString(id = 8856, message = "I'll stop here for today.")
	public static NpcStringId I_LL_STOP_HERE_FOR_TODAY;
	
	@ClientString(id = 8857, message = "I won't miss you this time!")
	public static NpcStringId I_WON_T_MISS_YOU_THIS_TIME;
	
	@ClientString(id = 8858, message = "Dammit! This is too hard by myself... $s1! Give me a hand!")
	public static NpcStringId DAMMIT_THIS_IS_TOO_HARD_BY_MYSELF_S1_GIVE_ME_A_HAND;
	
	@ClientString(id = 8859, message = "$s1! Hurry up, we'll miss him.")
	public static NpcStringId S1_HURRY_UP_WE_LL_MISS_HIM;
	
	@ClientString(id = 8860, message = "$s1! Come on. Hurry up!")
	public static NpcStringId S1_COME_ON_HURRY_UP;
	
	@ClientString(id = 8861, message = "I gotta' go follow him.")
	public static NpcStringId I_GOTTA_GO_FOLLOW_HIM;
	
	@ClientString(id = 8862, message = "Hey, quit running! Stop!")
	public static NpcStringId HEY_QUIT_RUNNING_STOP;
	
	@ClientString(id = 8863, message = "See you next time~")
	public static NpcStringId SEE_YOU_NEXT_TIME;
	
	@ClientString(id = 8864, message = "What? Think you can get in my way?")
	public static NpcStringId WHAT_THINK_YOU_CAN_GET_IN_MY_WAY;
	
	@ClientString(id = 8865, message = "You are so weak. I gotta' go now!")
	public static NpcStringId YOU_ARE_SO_WEAK_I_GOTTA_GO_NOW;
	
	@ClientString(id = 8866, message = "$s1! Good. I'll help you.")
	public static NpcStringId S1_GOOD_I_LL_HELP_YOU;
	
	@ClientString(id = 8867, message = "$s1, you're stronger than I thought. See you next time.")
	public static NpcStringId S1_YOU_RE_STRONGER_THAN_I_THOUGHT_SEE_YOU_NEXT_TIME;
	
	@ClientString(id = 8901, message = "Saga of the Mystic Muse")
	public static NpcStringId SAGA_OF_THE_MYSTIC_MUSE;
	
	@ClientString(id = 8902, message = "Saga of the Mystic Muse (In Progress)")
	public static NpcStringId SAGA_OF_THE_MYSTIC_MUSE_IN_PROGRESS;
	
	@ClientString(id = 8903, message = "Saga of the Mystic Muse (Done)")
	public static NpcStringId SAGA_OF_THE_MYSTIC_MUSE_DONE;
	
	@ClientString(id = 8950, message = "Are you $s1? Oh! I have the Resonance Amulet!")
	public static NpcStringId ARE_YOU_S1_OH_I_HAVE_THE_RESONANCE_AMULET_2;
	
	@ClientString(id = 8951, message = "You're feistier than I thought! I'll stop here today.")
	public static NpcStringId YOU_RE_FEISTIER_THAN_I_THOUGHT_I_LL_STOP_HERE_TODAY;
	
	@ClientString(id = 8952, message = "Aargh! I can't believe I lost...")
	public static NpcStringId AARGH_I_CAN_T_BELIEVE_I_LOST;
	
	@ClientString(id = 8953, message = "Are you the one who's been bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_S_BEEN_BOTHERING_MY_MINIONS_S1_4;
	
	@ClientString(id = 8954, message = "Yikes! You're tough!")
	public static NpcStringId YIKES_YOU_RE_TOUGH_2;
	
	@ClientString(id = 8955, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_11;
	
	@ClientString(id = 8956, message = "I'll stop here today.")
	public static NpcStringId I_LL_STOP_HERE_TODAY;
	
	@ClientString(id = 8957, message = "I'll get you this time!")
	public static NpcStringId I_LL_GET_YOU_THIS_TIME_4;
	
	@ClientString(id = 8958, message = "Damn! It's too much by myself...$s1! Give me a hand!")
	public static NpcStringId DAMN_IT_S_TOO_MUCH_BY_MYSELF_S1_GIVE_ME_A_HAND;
	
	@ClientString(id = 8959, message = "$s1! Hurry, we'll miss him.")
	public static NpcStringId S1_HURRY_WE_LL_MISS_HIM;
	
	@ClientString(id = 8960, message = "$s1! Hurry, please!")
	public static NpcStringId S1_HURRY_PLEASE;
	
	@ClientString(id = 8961, message = "I gotta' go follow him now.")
	public static NpcStringId I_GOTTA_GO_FOLLOW_HIM_NOW;
	
	@ClientString(id = 8962, message = "Are you running away? Stop!")
	public static NpcStringId ARE_YOU_RUNNING_AWAY_STOP;
	
	@ClientString(id = 8963, message = "See you next time~")
	public static NpcStringId SEE_YOU_NEXT_TIME_2;
	
	@ClientString(id = 8964, message = "Do you think you can stop me?")
	public static NpcStringId DO_YOU_THINK_YOU_CAN_STOP_ME;
	
	@ClientString(id = 8965, message = "You're so weak. I gotta' go now...")
	public static NpcStringId YOU_RE_SO_WEAK_I_GOTTA_GO_NOW;
	
	@ClientString(id = 8966, message = "You're $s1! Good. I'll help you.")
	public static NpcStringId YOU_RE_S1_GOOD_I_LL_HELP_YOU;
	
	@ClientString(id = 8967, message = "$s1! You're stronger than I thought. See you next time.")
	public static NpcStringId S1_YOU_RE_STRONGER_THAN_I_THOUGHT_SEE_YOU_NEXT_TIME_2;
	
	@ClientString(id = 9001, message = "Saga of the Storm Screamer")
	public static NpcStringId SAGA_OF_THE_STORM_SCREAMER;
	
	@ClientString(id = 9002, message = "Saga of the Storm Screamer (In Progress)")
	public static NpcStringId SAGA_OF_THE_STORM_SCREAMER_IN_PROGRESS;
	
	@ClientString(id = 9003, message = "Saga of the Storm Screamer (Done)")
	public static NpcStringId SAGA_OF_THE_STORM_SCREAMER_DONE;
	
	@ClientString(id = 9050, message = "Are you $s1? Oh! I have a Resonance Amulet!")
	public static NpcStringId ARE_YOU_S1_OH_I_HAVE_A_RESONANCE_AMULET;
	
	@ClientString(id = 9051, message = "Hey, you're more tenacious than I thought! I'll stop here today.")
	public static NpcStringId HEY_YOU_RE_MORE_TENACIOUS_THAN_I_THOUGHT_I_LL_STOP_HERE_TODAY;
	
	@ClientString(id = 9052, message = "Aargh! I can't believe I lost...")
	public static NpcStringId AARGH_I_CAN_T_BELIEVE_I_LOST_2;
	
	@ClientString(id = 9053, message = "Are you the one who's been bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_S_BEEN_BOTHERING_MY_MINIONS_S1_5;
	
	@ClientString(id = 9054, message = "Yikes! You're tough!")
	public static NpcStringId YIKES_YOU_RE_TOUGH_3;
	
	@ClientString(id = 9055, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_12;
	
	@ClientString(id = 9056, message = "I'll stop here today.")
	public static NpcStringId I_LL_STOP_HERE_TODAY_2;
	
	@ClientString(id = 9057, message = "I won't miss you this time!")
	public static NpcStringId I_WON_T_MISS_YOU_THIS_TIME_2;
	
	@ClientString(id = 9058, message = "Dammit! I can't do this alone, $s1! Give me a hand!")
	public static NpcStringId DAMMIT_I_CAN_T_DO_THIS_ALONE_S1_GIVE_ME_A_HAND;
	
	@ClientString(id = 9059, message = "$s1! Hurry or we'll miss him.")
	public static NpcStringId S1_HURRY_OR_WE_LL_MISS_HIM;
	
	@ClientString(id = 9060, message = "$s1! Hurry up!")
	public static NpcStringId S1_HURRY_UP;
	
	@ClientString(id = 9061, message = "I gotta' follow him now.")
	public static NpcStringId I_GOTTA_FOLLOW_HIM_NOW;
	
	@ClientString(id = 9062, message = "Hey, are you running? Stop!")
	public static NpcStringId HEY_ARE_YOU_RUNNING_STOP;
	
	@ClientString(id = 9063, message = "See you next time~")
	public static NpcStringId SEE_YOU_NEXT_TIME_3;
	
	@ClientString(id = 9064, message = "Do you think you can stop me?")
	public static NpcStringId DO_YOU_THINK_YOU_CAN_STOP_ME_2;
	
	@ClientString(id = 9065, message = "You're so weak. I gotta' go now...")
	public static NpcStringId YOU_RE_SO_WEAK_I_GOTTA_GO_NOW_2;
	
	@ClientString(id = 9066, message = "Oh! You're $s1! Good. I'll help you.")
	public static NpcStringId OH_YOU_RE_S1_GOOD_I_LL_HELP_YOU;
	
	@ClientString(id = 9067, message = "$s1. You're stronger than I thought. See you next time.")
	public static NpcStringId S1_YOU_RE_STRONGER_THAN_I_THOUGHT_SEE_YOU_NEXT_TIME_3;
	
	@ClientString(id = 9101, message = "Saga of the Arcana Lord")
	public static NpcStringId SAGA_OF_THE_ARCANA_LORD;
	
	@ClientString(id = 9102, message = "Saga of the Arcana Lord (In Progress)")
	public static NpcStringId SAGA_OF_THE_ARCANA_LORD_IN_PROGRESS;
	
	@ClientString(id = 9103, message = "Saga of the Arcana Lord (Done)")
	public static NpcStringId SAGA_OF_THE_ARCANA_LORD_DONE;
	
	@ClientString(id = 9150, message = "You carouse with evil spirits, $s1! You're not worthy of the holy wisdom!")
	public static NpcStringId YOU_CAROUSE_WITH_EVIL_SPIRITS_S1_YOU_RE_NOT_WORTHY_OF_THE_HOLY_WISDOM;
	
	@ClientString(id = 9151, message = "You're so stubborn! I can't boss you around any more, can I?")
	public static NpcStringId YOU_RE_SO_STUBBORN_I_CAN_T_BOSS_YOU_AROUND_ANY_MORE_CAN_I;
	
	@ClientString(id = 9152, message = "How could it happen? Defeated by a Human!")
	public static NpcStringId HOW_COULD_IT_HAPPEN_DEFEATED_BY_A_HUMAN;
	
	@ClientString(id = 9153, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_9;
	
	@ClientString(id = 9154, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_9;
	
	@ClientString(id = 9155, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_13;
	
	@ClientString(id = 9156, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_9;
	
	@ClientString(id = 9157, message = "My master sent me here! I'll give you a hand.")
	public static NpcStringId MY_MASTER_SENT_ME_HERE_I_LL_GIVE_YOU_A_HAND;
	
	@ClientString(id = 9158, message = "Meow~! Master $s1, help me!")
	public static NpcStringId MEOW_MASTER_S1_HELP_ME;
	
	@ClientString(id = 9159, message = "Master $s1. Punish him so he can't bother Belinda!")
	public static NpcStringId MASTER_S1_PUNISH_HIM_SO_HE_CAN_T_BOTHER_BELINDA;
	
	@ClientString(id = 9160, message = "Master $s1, We'll miss him!")
	public static NpcStringId MASTER_S1_WE_LL_MISS_HIM;
	
	@ClientString(id = 9161, message = "Meow~! My master is calling. Meow! I gotta' go now~!")
	public static NpcStringId MEOW_MY_MASTER_IS_CALLING_MEOW_I_GOTTA_GO_NOW;
	
	@ClientString(id = 9162, message = "Meow~! I missed him. Meow!")
	public static NpcStringId MEOW_I_MISSED_HIM_MEOW;
	
	@ClientString(id = 9163, message = "Good luck! Meow~! I gotta' go now.")
	public static NpcStringId GOOD_LUCK_MEOW_I_GOTTA_GO_NOW;
	
	@ClientString(id = 9164, message = "Curiosity killed the cat? I'll show you!")
	public static NpcStringId CURIOSITY_KILLED_THE_CAT_I_LL_SHOW_YOU;
	
	@ClientString(id = 9165, message = "That's all for today...!")
	public static NpcStringId THAT_S_ALL_FOR_TODAY;
	
	@ClientString(id = 9166, message = "Are you trying to take Belinda from me, $s1? I'll show you!")
	public static NpcStringId ARE_YOU_TRYING_TO_TAKE_BELINDA_FROM_ME_S1_I_LL_SHOW_YOU;
	
	@ClientString(id = 9167, message = "Belinda! I love you! Yikes!!!")
	public static NpcStringId BELINDA_I_LOVE_YOU_YIKES;
	
	@ClientString(id = 9201, message = "Saga of the Elemental Master")
	public static NpcStringId SAGA_OF_THE_ELEMENTAL_MASTER;
	
	@ClientString(id = 9202, message = "Saga of the Elemental Master (In Progress)")
	public static NpcStringId SAGA_OF_THE_ELEMENTAL_MASTER_IN_PROGRESS;
	
	@ClientString(id = 9203, message = "Saga of the Elemental Master (Done)")
	public static NpcStringId SAGA_OF_THE_ELEMENTAL_MASTER_DONE;
	
	@ClientString(id = 9250, message = "You carouse with evil spirits, $s1! You're not worthy of the holy wisdom!")
	public static NpcStringId YOU_CAROUSE_WITH_EVIL_SPIRITS_S1_YOU_RE_NOT_WORTHY_OF_THE_HOLY_WISDOM_2;
	
	@ClientString(id = 9251, message = "You're stubborn as a mule! Guess I can't boss you around any more!")
	public static NpcStringId YOU_RE_STUBBORN_AS_A_MULE_GUESS_I_CAN_T_BOSS_YOU_AROUND_ANY_MORE;
	
	@ClientString(id = 9252, message = "How could it be?...Defeated by an Elf!")
	public static NpcStringId HOW_COULD_IT_BE_DEFEATED_BY_AN_ELF;
	
	@ClientString(id = 9253, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_10;
	
	@ClientString(id = 9254, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_10;
	
	@ClientString(id = 9255, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_14;
	
	@ClientString(id = 9256, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_10;
	
	@ClientString(id = 9257, message = "I came to help you. It's the will of Radyss.")
	public static NpcStringId I_CAME_TO_HELP_YOU_IT_S_THE_WILL_OF_RADYSS;
	
	@ClientString(id = 9258, message = "$s1! Fight with me!")
	public static NpcStringId S1_FIGHT_WITH_ME;
	
	@ClientString(id = 9259, message = "$s1! We must defeat him!")
	public static NpcStringId S1_WE_MUST_DEFEAT_HIM;
	
	@ClientString(id = 9260, message = "$s1. There's no time. We must defeat him!")
	public static NpcStringId S1_THERE_S_NO_TIME_WE_MUST_DEFEAT_HIM;
	
	@ClientString(id = 9261, message = "Radyss is calling me. I gotta' go now.")
	public static NpcStringId RADYSS_IS_CALLING_ME_I_GOTTA_GO_NOW;
	
	@ClientString(id = 9262, message = "I was unable to avenge my brother.")
	public static NpcStringId I_WAS_UNABLE_TO_AVENGE_MY_BROTHER;
	
	@ClientString(id = 9263, message = "May you be blessed.")
	public static NpcStringId MAY_YOU_BE_BLESSED;
	
	@ClientString(id = 9264, message = "The proud, repent! The foolish, awaken! Sinners, die!")
	public static NpcStringId THE_PROUD_REPENT_THE_FOOLISH_AWAKEN_SINNERS_DIE;
	
	@ClientString(id = 9265, message = "Hell's master is calling. Atonement will have to wait!")
	public static NpcStringId HELL_S_MASTER_IS_CALLING_ATONEMENT_WILL_HAVE_TO_WAIT;
	
	@ClientString(id = 9266, message = "$s1, I'll remember your name, heathen.")
	public static NpcStringId S1_I_LL_REMEMBER_YOUR_NAME_HEATHEN;
	
	@ClientString(id = 9267, message = "I won't forget the name of one who doesn't obey holy judgment, $s1!")
	public static NpcStringId I_WON_T_FORGET_THE_NAME_OF_ONE_WHO_DOESN_T_OBEY_HOLY_JUDGMENT_S1;
	
	@ClientString(id = 9301, message = "Saga of the Spectral Master")
	public static NpcStringId SAGA_OF_THE_SPECTRAL_MASTER;
	
	@ClientString(id = 9302, message = "Saga of the Spectral Master (In Progress)")
	public static NpcStringId SAGA_OF_THE_SPECTRAL_MASTER_IN_PROGRESS;
	
	@ClientString(id = 9303, message = "Saga of the Spectral Master (Done)")
	public static NpcStringId SAGA_OF_THE_SPECTRAL_MASTER_DONE;
	
	@ClientString(id = 9350, message = "You carouse with evil spirits, $s1! You're not worthy of the holy wisdom!")
	public static NpcStringId YOU_CAROUSE_WITH_EVIL_SPIRITS_S1_YOU_RE_NOT_WORTHY_OF_THE_HOLY_WISDOM_3;
	
	@ClientString(id = 9351, message = "You're stubborn as a mule! I guess I can't boss you around any more!")
	public static NpcStringId YOU_RE_STUBBORN_AS_A_MULE_I_GUESS_I_CAN_T_BOSS_YOU_AROUND_ANY_MORE;
	
	@ClientString(id = 9352, message = "Could it be...? Defeated by a Dark Elf!")
	public static NpcStringId COULD_IT_BE_DEFEATED_BY_A_DARK_ELF;
	
	@ClientString(id = 9353, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_11;
	
	@ClientString(id = 9354, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_11;
	
	@ClientString(id = 9355, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_15;
	
	@ClientString(id = 9356, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_11;
	
	@ClientString(id = 9357, message = "Shadow Summoner, I came here to help you.")
	public static NpcStringId SHADOW_SUMMONER_I_CAME_HERE_TO_HELP_YOU;
	
	@ClientString(id = 9358, message = "Shadow Summoner, $s1! Fight with me!")
	public static NpcStringId SHADOW_SUMMONER_S1_FIGHT_WITH_ME;
	
	@ClientString(id = 9359, message = "$s1, You'll die if you don't kill him!")
	public static NpcStringId S1_YOU_LL_DIE_IF_YOU_DON_T_KILL_HIM;
	
	@ClientString(id = 9360, message = "Hurry, $s1! Don't miss him!")
	public static NpcStringId HURRY_S1_DON_T_MISS_HIM;
	
	@ClientString(id = 9361, message = "I can't hold on any longer...")
	public static NpcStringId I_CAN_T_HOLD_ON_ANY_LONGER;
	
	@ClientString(id = 9362, message = "After all that...I missed him...")
	public static NpcStringId AFTER_ALL_THAT_I_MISSED_HIM;
	
	@ClientString(id = 9363, message = "Shadow summoner! May you be blessed!")
	public static NpcStringId SHADOW_SUMMONER_MAY_YOU_BE_BLESSED;
	
	@ClientString(id = 9364, message = "My master sent me here to kill you!")
	public static NpcStringId MY_MASTER_SENT_ME_HERE_TO_KILL_YOU;
	
	@ClientString(id = 9365, message = "The shadow is calling me...")
	public static NpcStringId THE_SHADOW_IS_CALLING_ME;
	
	@ClientString(id = 9366, message = "$s1, you want to die early? I'll send you to the darkness!")
	public static NpcStringId S1_YOU_WANT_TO_DIE_EARLY_I_LL_SEND_YOU_TO_THE_DARKNESS;
	
	@ClientString(id = 9367, message = "You deal in darkness, $s1! I'll pay you back.")
	public static NpcStringId YOU_DEAL_IN_DARKNESS_S1_I_LL_PAY_YOU_BACK;
	
	@ClientString(id = 9401, message = "Saga of the Soultaker")
	public static NpcStringId SAGA_OF_THE_SOULTAKER;
	
	@ClientString(id = 9402, message = "Saga of the Soultaker (In Progress)")
	public static NpcStringId SAGA_OF_THE_SOULTAKER_IN_PROGRESS;
	
	@ClientString(id = 9403, message = "Saga of the Soultaker (Done)")
	public static NpcStringId SAGA_OF_THE_SOULTAKER_DONE;
	
	@ClientString(id = 9450, message = "You're $s1? I won't be like Hindemith!")
	public static NpcStringId YOU_RE_S1_I_WON_T_BE_LIKE_HINDEMITH;
	
	@ClientString(id = 9451, message = "You're feistier than I thought! I'll stop here for today.")
	public static NpcStringId YOU_RE_FEISTIER_THAN_I_THOUGHT_I_LL_STOP_HERE_FOR_TODAY;
	
	@ClientString(id = 9452, message = "Aargh! I can't believe I lost...")
	public static NpcStringId AARGH_I_CAN_T_BELIEVE_I_LOST_3;
	
	@ClientString(id = 9453, message = "Are you the one who is bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_IS_BOTHERING_MY_MINIONS_S1;
	
	@ClientString(id = 9454, message = "Yikes! You're tough!")
	public static NpcStringId YIKES_YOU_RE_TOUGH_4;
	
	@ClientString(id = 9455, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_16;
	
	@ClientString(id = 9456, message = "I'll stop here for today.")
	public static NpcStringId I_LL_STOP_HERE_FOR_TODAY_2;
	
	@ClientString(id = 9457, message = "I can't let you commune with Tablet of Vision! Give me the Resonance Amulet!")
	public static NpcStringId I_CAN_T_LET_YOU_COMMUNE_WITH_TABLET_OF_VISION_GIVE_ME_THE_RESONANCE_AMULET;
	
	@ClientString(id = 9458, message = "Dammit! I can't do this alone, $s1! Give me a hand!")
	public static NpcStringId DAMMIT_I_CAN_T_DO_THIS_ALONE_S1_GIVE_ME_A_HAND_2;
	
	@ClientString(id = 9459, message = "$s1! Hurry or we'll miss him.")
	public static NpcStringId S1_HURRY_OR_WE_LL_MISS_HIM_2;
	
	@ClientString(id = 9460, message = "$s1! Please hurry!")
	public static NpcStringId S1_PLEASE_HURRY;
	
	@ClientString(id = 9461, message = "I must follow him now.")
	public static NpcStringId I_MUST_FOLLOW_HIM_NOW;
	
	@ClientString(id = 9462, message = "Are you running? Stop!")
	public static NpcStringId ARE_YOU_RUNNING_STOP;
	
	@ClientString(id = 9463, message = "See you next time.")
	public static NpcStringId SEE_YOU_NEXT_TIME_4;
	
	@ClientString(id = 9464, message = "Are you betraying me? I thought something was wrong...I'll stop here.")
	public static NpcStringId ARE_YOU_BETRAYING_ME_I_THOUGHT_SOMETHING_WAS_WRONG_I_LL_STOP_HERE;
	
	@ClientString(id = 9465, message = "I gotta' go now.")
	public static NpcStringId I_GOTTA_GO_NOW_5;
	
	@ClientString(id = 9466, message = "You're $s1? Even two of you can't stop me!")
	public static NpcStringId YOU_RE_S1_EVEN_TWO_OF_YOU_CAN_T_STOP_ME;
	
	@ClientString(id = 9467, message = "Dammit! My Resonance Amulet...$s1, I'll never forget to pay you back.")
	public static NpcStringId DAMMIT_MY_RESONANCE_AMULET_S1_I_LL_NEVER_FORGET_TO_PAY_YOU_BACK;
	
	@ClientString(id = 9501, message = "Saga of the Hell Knight")
	public static NpcStringId SAGA_OF_THE_HELL_KNIGHT;
	
	@ClientString(id = 9502, message = "Saga of the Hell Knight (In Progress)")
	public static NpcStringId SAGA_OF_THE_HELL_KNIGHT_IN_PROGRESS;
	
	@ClientString(id = 9503, message = "Saga of the Hell Knight (Done)")
	public static NpcStringId SAGA_OF_THE_HELL_KNIGHT_DONE;
	
	@ClientString(id = 9550, message = "Are you... $s1? I won't be like Waldstein!")
	public static NpcStringId ARE_YOU_S1_I_WON_T_BE_LIKE_WALDSTEIN;
	
	@ClientString(id = 9551, message = "You're feistier than I thought! I'll stop here for today.")
	public static NpcStringId YOU_RE_FEISTIER_THAN_I_THOUGHT_I_LL_STOP_HERE_FOR_TODAY_2;
	
	@ClientString(id = 9552, message = "Yikes! I can't believe I lost...")
	public static NpcStringId YIKES_I_CAN_T_BELIEVE_I_LOST;
	
	@ClientString(id = 9553, message = "Are you the one bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_BOTHERING_MY_MINIONS_S1;
	
	@ClientString(id = 9554, message = "Yikes! You're tough!")
	public static NpcStringId YIKES_YOU_RE_TOUGH_5;
	
	@ClientString(id = 9555, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_17;
	
	@ClientString(id = 9556, message = "I'll stop here for today.")
	public static NpcStringId I_LL_STOP_HERE_FOR_TODAY_3;
	
	@ClientString(id = 9557, message = "You can't commune with the Tablet of Vision! Give me the Resonance Amulet!")
	public static NpcStringId YOU_CAN_T_COMMUNE_WITH_THE_TABLET_OF_VISION_GIVE_ME_THE_RESONANCE_AMULET;
	
	@ClientString(id = 9558, message = "Dammit! I can't do this alone, $s1! Give me a hand!")
	public static NpcStringId DAMMIT_I_CAN_T_DO_THIS_ALONE_S1_GIVE_ME_A_HAND_3;
	
	@ClientString(id = 9559, message = "$s1! Hurry or we'll miss him.")
	public static NpcStringId S1_HURRY_OR_WE_LL_MISS_HIM_3;
	
	@ClientString(id = 9560, message = "$s1! Please hurry!")
	public static NpcStringId S1_PLEASE_HURRY_2;
	
	@ClientString(id = 9561, message = "I gotta' go follow him.")
	public static NpcStringId I_GOTTA_GO_FOLLOW_HIM_2;
	
	@ClientString(id = 9562, message = "Are you running? Stop!")
	public static NpcStringId ARE_YOU_RUNNING_STOP_2;
	
	@ClientString(id = 9563, message = "See you next time.")
	public static NpcStringId SEE_YOU_NEXT_TIME_5;
	
	@ClientString(id = 9564, message = "Are you betraying me? I thought something was wrong...I'll stop here.")
	public static NpcStringId ARE_YOU_BETRAYING_ME_I_THOUGHT_SOMETHING_WAS_WRONG_I_LL_STOP_HERE_2;
	
	@ClientString(id = 9565, message = "I gotta go now...")
	public static NpcStringId I_GOTTA_GO_NOW_6;
	
	@ClientString(id = 9566, message = "You're... $s1? Even two of you can't stop me!")
	public static NpcStringId YOU_RE_S1_EVEN_TWO_OF_YOU_CAN_T_STOP_ME_2;
	
	@ClientString(id = 9567, message = "Dammit! My Resonance Amulet...$s1, I'll never forget this.")
	public static NpcStringId DAMMIT_MY_RESONANCE_AMULET_S1_I_LL_NEVER_FORGET_THIS;
	
	@ClientString(id = 9601, message = "Saga of the Spectral Dancer")
	public static NpcStringId SAGA_OF_THE_SPECTRAL_DANCER;
	
	@ClientString(id = 9602, message = "Saga of the Spectral Dancer (In Progress)")
	public static NpcStringId SAGA_OF_THE_SPECTRAL_DANCER_IN_PROGRESS;
	
	@ClientString(id = 9603, message = "Saga of the Spectral Dancer (Done)")
	public static NpcStringId SAGA_OF_THE_SPECTRAL_DANCER_DONE;
	
	@ClientString(id = 9650, message = "You're $s1? I'll kill you for Hallate!")
	public static NpcStringId YOU_RE_S1_I_LL_KILL_YOU_FOR_HALLATE;
	
	@ClientString(id = 9651, message = "You're tougher than I thought, but you still can't rival me!")
	public static NpcStringId YOU_RE_TOUGHER_THAN_I_THOUGHT_BUT_YOU_STILL_CAN_T_RIVAL_ME;
	
	@ClientString(id = 9652, message = "Hallate! Forgive me! I can't help you.")
	public static NpcStringId HALLATE_FORGIVE_ME_I_CAN_T_HELP_YOU;
	
	@ClientString(id = 9653, message = "Are you the one who's been bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_S_BEEN_BOTHERING_MY_MINIONS_S1_6;
	
	@ClientString(id = 9654, message = "Dammit! I can't believe you beat me!")
	public static NpcStringId DAMMIT_I_CAN_T_BELIEVE_YOU_BEAT_ME;
	
	@ClientString(id = 9655, message = "Who are you? Mind your own business, coward.")
	public static NpcStringId WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_COWARD;
	
	@ClientString(id = 9656, message = "How weak. I'll forgive you this time because you made me laugh.")
	public static NpcStringId HOW_WEAK_I_LL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH_4;
	
	@ClientString(id = 9657, message = "Purgatory Lord, I won't fail this time.")
	public static NpcStringId PURGATORY_LORD_I_WON_T_FAIL_THIS_TIME;
	
	@ClientString(id = 9658, message = "$s1! Now's the time to put your training to the test!")
	public static NpcStringId S1_NOW_S_THE_TIME_TO_PUT_YOUR_TRAINING_TO_THE_TEST;
	
	@ClientString(id = 9659, message = "$s1! Your sword skills can't be that bad.")
	public static NpcStringId S1_YOUR_SWORD_SKILLS_CAN_T_BE_THAT_BAD;
	
	@ClientString(id = 9660, message = "$s1! Show your strength!")
	public static NpcStringId S1_SHOW_YOUR_STRENGTH;
	
	@ClientString(id = 9661, message = "I have some pressing business. I have to go.")
	public static NpcStringId I_HAVE_SOME_PRESSING_BUSINESS_I_HAVE_TO_GO;
	
	@ClientString(id = 9662, message = "I missed him! Dammit.")
	public static NpcStringId I_MISSED_HIM_DAMMIT;
	
	@ClientString(id = 9663, message = "Try again sometime.")
	public static NpcStringId TRY_AGAIN_SOMETIME;
	
	@ClientString(id = 9664, message = "I'll kill anyone who gets in my way!")
	public static NpcStringId I_LL_KILL_ANYONE_WHO_GETS_IN_MY_WAY;
	
	@ClientString(id = 9665, message = "This is pathetic! You make me laugh.")
	public static NpcStringId THIS_IS_PATHETIC_YOU_MAKE_ME_LAUGH;
	
	@ClientString(id = 9666, message = "$s1! Are you trying to get in my way?")
	public static NpcStringId S1_ARE_YOU_TRYING_TO_GET_IN_MY_WAY;
	
	@ClientString(id = 9667, message = "$s1! When I come back, I'll kill you.")
	public static NpcStringId S1_WHEN_I_COME_BACK_I_LL_KILL_YOU;
	
	@ClientString(id = 9701, message = "Saga of the Shillien Templar")
	public static NpcStringId SAGA_OF_THE_SHILLIEN_TEMPLAR;
	
	@ClientString(id = 9702, message = "Saga of the Shillien Templar (In Progress)")
	public static NpcStringId SAGA_OF_THE_SHILLIEN_TEMPLAR_IN_PROGRESS;
	
	@ClientString(id = 9703, message = "Saga of the Shillien Templar (Done)")
	public static NpcStringId SAGA_OF_THE_SHILLIEN_TEMPLAR_DONE;
	
	@ClientString(id = 9750, message = "$s1? Wake up! Time to die!")
	public static NpcStringId S1_WAKE_UP_TIME_TO_DIE;
	
	@ClientString(id = 9751, message = "You're tougher than I thought! I'll be back!")
	public static NpcStringId YOU_RE_TOUGHER_THAN_I_THOUGHT_I_LL_BE_BACK;
	
	@ClientString(id = 9752, message = "I lost? It can't be!")
	public static NpcStringId I_LOST_IT_CAN_T_BE;
	
	@ClientString(id = 9753, message = "Are you the one who’s been bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_S_BEEN_BOTHERING_MY_MINIONS_S1_7;
	
	@ClientString(id = 9754, message = "Dammit! I can't believe you beat me!")
	public static NpcStringId DAMMIT_I_CAN_T_BELIEVE_YOU_BEAT_ME_2;
	
	@ClientString(id = 9755, message = "Who are you? Mind your own business, coward.")
	public static NpcStringId WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_COWARD_2;
	
	@ClientString(id = 9756, message = "How weak. I'll forgive you this time because you made me laugh.")
	public static NpcStringId HOW_WEAK_I_LL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH_5;
	
	@ClientString(id = 9757, message = "You're a cunning fiend. I won't fail again.")
	public static NpcStringId YOU_RE_A_CUNNING_FIEND_I_WON_T_FAIL_AGAIN;
	
	@ClientString(id = 9758, message = "$s1! It's after you! Fight!")
	public static NpcStringId S1_IT_S_AFTER_YOU_FIGHT;
	
	@ClientString(id = 9759, message = "$s1! You have to fight better than that if you expect to survive!")
	public static NpcStringId S1_YOU_HAVE_TO_FIGHT_BETTER_THAN_THAT_IF_YOU_EXPECT_TO_SURVIVE;
	
	@ClientString(id = 9760, message = "$s1! Pull yourself together. Fight!")
	public static NpcStringId S1_PULL_YOURSELF_TOGETHER_FIGHT;
	
	@ClientString(id = 9761, message = "I'll catch the cunning fiend.")
	public static NpcStringId I_LL_CATCH_THE_CUNNING_FIEND;
	
	@ClientString(id = 9762, message = "I missed him again! He's clever!")
	public static NpcStringId I_MISSED_HIM_AGAIN_HE_S_CLEVER;
	
	@ClientString(id = 9763, message = "Don't cower like a puppy next time!")
	public static NpcStringId DON_T_COWER_LIKE_A_PUPPY_NEXT_TIME;
	
	@ClientString(id = 9764, message = "I have only one goal. Get out of my way.")
	public static NpcStringId I_HAVE_ONLY_ONE_GOAL_GET_OUT_OF_MY_WAY;
	
	@ClientString(id = 9765, message = "Just wait. You'll get yours!")
	public static NpcStringId JUST_WAIT_YOU_LL_GET_YOURS;
	
	@ClientString(id = 9766, message = "$s1! You're a coward, aren't you!")
	public static NpcStringId S1_YOU_RE_A_COWARD_AREN_T_YOU;
	
	@ClientString(id = 9767, message = "$s1! I'll kill you next time. ")
	public static NpcStringId S1_I_LL_KILL_YOU_NEXT_TIME;
	
	@ClientString(id = 9801, message = "Saga of the Shillien Saint")
	public static NpcStringId SAGA_OF_THE_SHILLIEN_SAINT;
	
	@ClientString(id = 9802, message = "Saga of the Shillien Saint (In Progress)")
	public static NpcStringId SAGA_OF_THE_SHILLIEN_SAINT_IN_PROGRESS;
	
	@ClientString(id = 9803, message = "Saga of the Shillien Saint (Done)")
	public static NpcStringId SAGA_OF_THE_SHILLIEN_SAINT_DONE;
	
	@ClientString(id = 9850, message = "$s1! How foolish to act against the will of god.")
	public static NpcStringId S1_HOW_FOOLISH_TO_ACT_AGAINST_THE_WILL_OF_GOD;
	
	@ClientString(id = 9851, message = "Your faith is stronger than I thought. I'll get you next time.")
	public static NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_I_LL_GET_YOU_NEXT_TIME;
	
	@ClientString(id = 9852, message = "Tanakia, forgive me! I couldn't fulfill your dream!")
	public static NpcStringId TANAKIA_FORGIVE_ME_I_COULDN_T_FULFILL_YOUR_DREAM_4;
	
	@ClientString(id = 9853, message = "Are you the one who's been bothering my minions, $s1?")
	public static NpcStringId ARE_YOU_THE_ONE_WHO_S_BEEN_BOTHERING_MY_MINIONS_S1_8;
	
	@ClientString(id = 9854, message = "Dammit! I can't believe you beat me!")
	public static NpcStringId DAMMIT_I_CAN_T_BELIEVE_YOU_BEAT_ME_3;
	
	@ClientString(id = 9855, message = "Who are you? Mind your own business, you coward!")
	public static NpcStringId WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_YOU_COWARD;
	
	@ClientString(id = 9856, message = "How weak. I'll forgive you this time because you made me laugh.")
	public static NpcStringId HOW_WEAK_I_LL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH_6;
	
	@ClientString(id = 9857, message = "Tanakia, your lie has already been exposed!")
	public static NpcStringId TANAKIA_YOUR_LIE_HAS_ALREADY_BEEN_EXPOSED;
	
	@ClientString(id = 9858, message = "$s1! Help me overcome this.")
	public static NpcStringId S1_HELP_ME_OVERCOME_THIS;
	
	@ClientString(id = 9859, message = "$s1! We can't defeat Tanakia this way.")
	public static NpcStringId S1_WE_CAN_T_DEFEAT_TANAKIA_THIS_WAY;
	
	@ClientString(id = 9860, message = "$s1! Here's our chance. Don't squander the opportunity!")
	public static NpcStringId S1_HERE_S_OUR_CHANCE_DON_T_SQUANDER_THE_OPPORTUNITY;
	
	@ClientString(id = 9861, message = "Goodbye. We'll meet again if fate allows.")
	public static NpcStringId GOODBYE_WE_LL_MEET_AGAIN_IF_FATE_ALLOWS;
	
	@ClientString(id = 9862, message = "I'll follow Tanakia to correct this falsehood.")
	public static NpcStringId I_LL_FOLLOW_TANAKIA_TO_CORRECT_THIS_FALSEHOOD;
	
	@ClientString(id = 9863, message = "I'll be back if fate allows.")
	public static NpcStringId I_LL_BE_BACK_IF_FATE_ALLOWS;
	
	@ClientString(id = 9864, message = "For Shilen!")
	public static NpcStringId FOR_SHILEN_4;
	
	@ClientString(id = 9865, message = "I'll be back. You'll pay.")
	public static NpcStringId I_LL_BE_BACK_YOU_LL_PAY;
	
	@ClientString(id = 9866, message = "$s1! Are you trying to spoil my plan?")
	public static NpcStringId S1_ARE_YOU_TRYING_TO_SPOIL_MY_PLAN;
	
	@ClientString(id = 9867, message = "$s1! I won't forget you. You'll pay.")
	public static NpcStringId S1_I_WON_T_FORGET_YOU_YOU_LL_PAY;
	
	@ClientString(id = 9901, message = "Saga of the Fortune Seeker")
	public static NpcStringId SAGA_OF_THE_FORTUNE_SEEKER;
	
	@ClientString(id = 9902, message = "Saga of the Fortune Seeker (In Progress)")
	public static NpcStringId SAGA_OF_THE_FORTUNE_SEEKER_IN_PROGRESS;
	
	@ClientString(id = 9903, message = "Saga of the Fortune Seeker (Done)")
	public static NpcStringId SAGA_OF_THE_FORTUNE_SEEKER_DONE;
	
	@ClientString(id = 9950, message = "$s1, You have an affinity for dangerous ideas. Are you ready to die?")
	public static NpcStringId S1_YOU_HAVE_AN_AFFINITY_FOR_DANGEROUS_IDEAS_ARE_YOU_READY_TO_DIE;
	
	@ClientString(id = 9951, message = "My time is up...")
	public static NpcStringId MY_TIME_IS_UP;
	
	@ClientString(id = 9952, message = "I can't believe I must kneel to a Human!")
	public static NpcStringId I_CAN_T_BELIEVE_I_MUST_KNEEL_TO_A_HUMAN;
	
	@ClientString(id = 9953, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_12;
	
	@ClientString(id = 9954, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_12;
	
	@ClientString(id = 9955, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_18;
	
	@ClientString(id = 9956, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_12;
	
	@ClientString(id = 9957, message = "Minervia! What's the matter?")
	public static NpcStringId MINERVIA_WHAT_S_THE_MATTER;
	
	@ClientString(id = 9958, message = "The princess is in danger. Why are you staring?")
	public static NpcStringId THE_PRINCESS_IS_IN_DANGER_WHY_ARE_YOU_STARING;
	
	@ClientString(id = 9959, message = "Master $s1! Come on, Hurry up!")
	public static NpcStringId MASTER_S1_COME_ON_HURRY_UP;
	
	@ClientString(id = 9960, message = "We can't fail! Master $s1, Pull yourself together!")
	public static NpcStringId WE_CAN_T_FAIL_MASTER_S1_PULL_YOURSELF_TOGETHER;
	
	@ClientString(id = 9961, message = "What am I doing... I gotta' go! Goodbye.")
	public static NpcStringId WHAT_AM_I_DOING_I_GOTTA_GO_GOODBYE;
	
	@ClientString(id = 9962, message = "Dammit! I missed...!")
	public static NpcStringId DAMMIT_I_MISSED;
	
	@ClientString(id = 9963, message = "Sorry, but I must say goodbye again... Good luck to you!")
	public static NpcStringId SORRY_BUT_I_MUST_SAY_GOODBYE_AGAIN_GOOD_LUCK_TO_YOU;
	
	@ClientString(id = 9964, message = "I can't yield the secret of the tablet!")
	public static NpcStringId I_CAN_T_YIELD_THE_SECRET_OF_THE_TABLET;
	
	@ClientString(id = 9965, message = "I'll stop here for now...")
	public static NpcStringId I_LL_STOP_HERE_FOR_NOW;
	
	@ClientString(id = 9966, message = "$s1, you dared to leave scar on my face! I'll kill you!!!")
	public static NpcStringId S1_YOU_DARED_TO_LEAVE_SCAR_ON_MY_FACE_I_LL_KILL_YOU;
	
	@ClientString(id = 9967, message = "$s1, I won't forget your name...Ha!")
	public static NpcStringId S1_I_WON_T_FORGET_YOUR_NAME_HA;
	
	@ClientString(id = 10001, message = "Saga of the Maestro")
	public static NpcStringId SAGA_OF_THE_MAESTRO;
	
	@ClientString(id = 10002, message = "Saga of the Maestro (In Progress)")
	public static NpcStringId SAGA_OF_THE_MAESTRO_IN_PROGRESS;
	
	@ClientString(id = 10003, message = "Saga of the Maestro (Done)")
	public static NpcStringId SAGA_OF_THE_MAESTRO_DONE;
	
	@ClientString(id = 10050, message = "$s1? You have an affinity for bad ideas. Are you ready to die?")
	public static NpcStringId S1_YOU_HAVE_AN_AFFINITY_FOR_BAD_IDEAS_ARE_YOU_READY_TO_DIE;
	
	@ClientString(id = 10051, message = "My time is up...")
	public static NpcStringId MY_TIME_IS_UP_2;
	
	@ClientString(id = 10052, message = "I can't believe I must kneel before a Human!")
	public static NpcStringId I_CAN_T_BELIEVE_I_MUST_KNEEL_BEFORE_A_HUMAN;
	
	@ClientString(id = 10053, message = "You dare to disturb the order of the shrine! Die, $s1!")
	public static NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1_13;
	
	@ClientString(id = 10054, message = "My spirit is released from this shell. I'm getting close to Halisha...")
	public static NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_I_M_GETTING_CLOSE_TO_HALISHA_13;
	
	@ClientString(id = 10055, message = "Mind your own business!")
	public static NpcStringId MIND_YOUR_OWN_BUSINESS_19;
	
	@ClientString(id = 10056, message = "This is a waste of time. Goodbye!")
	public static NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE_13;
	
	@ClientString(id = 10057, message = "You thief! Give me the Resonance Amulet!")
	public static NpcStringId YOU_THIEF_GIVE_ME_THE_RESONANCE_AMULET;
	
	@ClientString(id = 10058, message = "Ugh! $s1, Help me!")
	public static NpcStringId UGH_S1_HELP_ME;
	
	@ClientString(id = 10059, message = "$s1. Please, help me! Together we can beat him.")
	public static NpcStringId S1_PLEASE_HELP_ME_TOGETHER_WE_CAN_BEAT_HIM;
	
	@ClientString(id = 10060, message = "$s1! Are you going to let a guild member die?")
	public static NpcStringId S1_ARE_YOU_GOING_TO_LET_A_GUILD_MEMBER_DIE;
	
	@ClientString(id = 10061, message = "I'm sorry, but I gotta' go first!")
	public static NpcStringId I_M_SORRY_BUT_I_GOTTA_GO_FIRST;
	
	@ClientString(id = 10062, message = "Aaaah! I couldn't get the Resonance Amulet.")
	public static NpcStringId AAAAH_I_COULDN_T_GET_THE_RESONANCE_AMULET;
	
	@ClientString(id = 10063, message = "Take care! I gotta' go now~!")
	public static NpcStringId TAKE_CARE_I_GOTTA_GO_NOW;
	
	@ClientString(id = 10064, message = "I'm sorry, but it's my job to kill you now!")
	public static NpcStringId I_M_SORRY_BUT_IT_S_MY_JOB_TO_KILL_YOU_NOW;
	
	@ClientString(id = 10065, message = "What a waste of time!")
	public static NpcStringId WHAT_A_WASTE_OF_TIME;
	
	@ClientString(id = 10066, message = "$s1! How could you do this? I'll kill you!")
	public static NpcStringId S1_HOW_COULD_YOU_DO_THIS_I_LL_KILL_YOU;
	
	@ClientString(id = 10067, message = "$s1! I'll pay you back!")
	public static NpcStringId S1_I_LL_PAY_YOU_BACK;
	
	@ClientString(id = 10068, message = "Why don't you just die?!")
	public static NpcStringId WHY_DON_T_YOU_JUST_DIE;
	
	@ClientString(id = 10069, message = "Taste the sting of Level 5 Spoil!")
	public static NpcStringId TASTE_THE_STING_OF_LEVEL_5_SPOIL;
	
	@ClientString(id = 10070, message = "The item is already inside you...")
	public static NpcStringId THE_ITEM_IS_ALREADY_INSIDE_YOU;
	
	@ClientString(id = 10071, message = "This potion you're making me drink is worth its weight in gold!")
	public static NpcStringId THIS_POTION_YOU_RE_MAKING_ME_DRINK_IS_WORTH_ITS_WEIGHT_IN_GOLD;
	
	@ClientString(id = 10072, message = "This potion is prepared from the ground gall of a bear. Be careful - it packs quite a punch!")
	public static NpcStringId THIS_POTION_IS_PREPARED_FROM_THE_GROUND_GALL_OF_A_BEAR_BE_CAREFUL_IT_PACKS_QUITE_A_PUNCH;
	
	@ClientString(id = 10073, message = "How can you use a potion on a newbie...")
	public static NpcStringId HOW_CAN_YOU_USE_A_POTION_ON_A_NEWBIE;
	
	@ClientString(id = 10074, message = "Listen to me, $s1! Unless you have prior authorization, you can't carry a weapon here!")
	public static NpcStringId LISTEN_TO_ME_S1_UNLESS_YOU_HAVE_PRIOR_AUTHORIZATION_YOU_CAN_T_CARRY_A_WEAPON_HERE;
	
	@ClientString(id = 10075, message = "Dear $s1, may the blessings of Einhasad be with you always.")
	public static NpcStringId DEAR_S1_MAY_THE_BLESSINGS_OF_EINHASAD_BE_WITH_YOU_ALWAYS;
	
	@ClientString(id = 10076, message = "Dear brother $s1, follow the path of light with me...")
	public static NpcStringId DEAR_BROTHER_S1_FOLLOW_THE_PATH_OF_LIGHT_WITH_ME;
	
	@ClientString(id = 10077, message = "$s1, why would you choose the path of darkness?!")
	public static NpcStringId S1_WHY_WOULD_YOU_CHOOSE_THE_PATH_OF_DARKNESS;
	
	@ClientString(id = 10078, message = "$s1! How dare you defy the will of Einhasad!")
	public static NpcStringId S1_HOW_DARE_YOU_DEFY_THE_WILL_OF_EINHASAD;
	
	@ClientString(id = 10079, message = "The door to the 3rd floor of the altar is now open.")
	public static NpcStringId THE_DOOR_TO_THE_3RD_FLOOR_OF_THE_ALTAR_IS_NOW_OPEN;
	
	@ClientString(id = 10101, message = "Sword of Solidarity")
	public static NpcStringId SWORD_OF_SOLIDARITY;
	
	@ClientString(id = 10102, message = "Sword of Solidarity (In Progress)")
	public static NpcStringId SWORD_OF_SOLIDARITY_IN_PROGRESS;
	
	@ClientString(id = 10103, message = "Sword of Solidarity (Done)")
	public static NpcStringId SWORD_OF_SOLIDARITY_DONE;
	
	@ClientString(id = 10201, message = "Sea of Spores Fever")
	public static NpcStringId SEA_OF_SPORES_FEVER;
	
	@ClientString(id = 10202, message = "Sea of Spores Fever (In Progress)")
	public static NpcStringId SEA_OF_SPORES_FEVER_IN_PROGRESS;
	
	@ClientString(id = 10203, message = "Sea of Spores Fever (Done)")
	public static NpcStringId SEA_OF_SPORES_FEVER_DONE;
	
	@ClientString(id = 10301, message = "Spirit of Craftsman")
	public static NpcStringId SPIRIT_OF_CRAFTSMAN;
	
	@ClientString(id = 10302, message = "Spirit of Craftsman (In Progress)")
	public static NpcStringId SPIRIT_OF_CRAFTSMAN_IN_PROGRESS;
	
	@ClientString(id = 10303, message = "Spirit of Craftsman (Done)")
	public static NpcStringId SPIRIT_OF_CRAFTSMAN_DONE;
	
	@ClientString(id = 10401, message = "Spirit of Mirrors")
	public static NpcStringId SPIRIT_OF_MIRRORS;
	
	@ClientString(id = 10402, message = "Spirit of Mirrors (In Progress)")
	public static NpcStringId SPIRIT_OF_MIRRORS_IN_PROGRESS;
	
	@ClientString(id = 10403, message = "Spirit of Mirrors (Done)")
	public static NpcStringId SPIRIT_OF_MIRRORS_DONE;
	
	@ClientString(id = 10501, message = "Skirmish with the Orcs")
	public static NpcStringId SKIRMISH_WITH_THE_ORCS;
	
	@ClientString(id = 10502, message = "Skirmish with the Orcs (In Progress)")
	public static NpcStringId SKIRMISH_WITH_THE_ORCS_IN_PROGRESS;
	
	@ClientString(id = 10503, message = "Skirmish with the Orcs (Done)")
	public static NpcStringId SKIRMISH_WITH_THE_ORCS_DONE;
	
	@ClientString(id = 10601, message = "Forgotten Truth")
	public static NpcStringId FORGOTTEN_TRUTH;
	
	@ClientString(id = 10602, message = "Forgotten Truth (In Progress)")
	public static NpcStringId FORGOTTEN_TRUTH_IN_PROGRESS;
	
	@ClientString(id = 10603, message = "Forgotten Truth (Done)")
	public static NpcStringId FORGOTTEN_TRUTH_DONE;
	
	@ClientString(id = 10701, message = "Merciless Punishment")
	public static NpcStringId MERCILESS_PUNISHMENT;
	
	@ClientString(id = 10702, message = "Merciless Punishment (In Progress)")
	public static NpcStringId MERCILESS_PUNISHMENT_IN_PROGRESS;
	
	@ClientString(id = 10703, message = "Merciless Punishment (Done)")
	public static NpcStringId MERCILESS_PUNISHMENT_DONE;
	
	@ClientString(id = 10801, message = "Jumble, Tumble, Diamond Fuss")
	public static NpcStringId JUMBLE_TUMBLE_DIAMOND_FUSS;
	
	@ClientString(id = 10802, message = "Jumble, Tumble, Diamond Fuss (In Progress)")
	public static NpcStringId JUMBLE_TUMBLE_DIAMOND_FUSS_IN_PROGRESS;
	
	@ClientString(id = 10803, message = "Jumble, Tumble, Diamond Fuss (Done)")
	public static NpcStringId JUMBLE_TUMBLE_DIAMOND_FUSS_DONE;
	
	@ClientString(id = 10901, message = "In Search of the Nest")
	public static NpcStringId IN_SEARCH_OF_THE_NEST;
	
	@ClientString(id = 10902, message = "In Search of the Nest (In Progress)")
	public static NpcStringId IN_SEARCH_OF_THE_NEST_IN_PROGRESS;
	
	@ClientString(id = 10903, message = "In Search of the Nest (Done)")
	public static NpcStringId IN_SEARCH_OF_THE_NEST_DONE;
	
	@ClientString(id = 11001, message = "To the Primeval Isle")
	public static NpcStringId TO_THE_PRIMEVAL_ISLE;
	
	@ClientString(id = 11002, message = "To the Primeval Isle (In Progress)")
	public static NpcStringId TO_THE_PRIMEVAL_ISLE_IN_PROGRESS;
	
	@ClientString(id = 11003, message = "To the Primeval Isle (Done)")
	public static NpcStringId TO_THE_PRIMEVAL_ISLE_DONE;
	
	@ClientString(id = 11101, message = "Elrokian Hunters")
	public static NpcStringId ELROKIAN_HUNTERS;
	
	@ClientString(id = 11102, message = "Elrokian Hunter's Proof (In Progress)")
	public static NpcStringId ELROKIAN_HUNTER_S_PROOF_IN_PROGRESS;
	
	@ClientString(id = 11103, message = "Elrokian Hunter's Proof (Done)")
	public static NpcStringId ELROKIAN_HUNTER_S_PROOF_DONE;
	
	@ClientString(id = 11201, message = "Walk of Fate")
	public static NpcStringId WALK_OF_FATE;
	
	@ClientString(id = 11202, message = "Walk of Fate (In Progress)")
	public static NpcStringId WALK_OF_FATE_IN_PROGRESS;
	
	@ClientString(id = 11203, message = "Walk of Fate (Done)")
	public static NpcStringId WALK_OF_FATE_DONE;
	
	@ClientString(id = 11301, message = "Status of the Beacon Tower")
	public static NpcStringId STATUS_OF_THE_BEACON_TOWER;
	
	@ClientString(id = 11302, message = "Status of the Beacon Tower (In Progress)")
	public static NpcStringId STATUS_OF_THE_BEACON_TOWER_IN_PROGRESS;
	
	@ClientString(id = 11303, message = "Status of the Beacon Tower (Done)")
	public static NpcStringId STATUS_OF_THE_BEACON_TOWER_DONE;
	
	@ClientString(id = 11401, message = "Resurrection of an Old Manager")
	public static NpcStringId RESURRECTION_OF_AN_OLD_MANAGER;
	
	@ClientString(id = 11402, message = "Resurrection of an Old Manager (In Progress)")
	public static NpcStringId RESURRECTION_OF_AN_OLD_MANAGER_IN_PROGRESS;
	
	@ClientString(id = 11403, message = "Resurrection of an Old Manager (Done)")
	public static NpcStringId RESURRECTION_OF_AN_OLD_MANAGER_DONE;
	
	@ClientString(id = 11450, message = "You, $s1, you attacked Wendy. Prepare to die!")
	public static NpcStringId YOU_S1_YOU_ATTACKED_WENDY_PREPARE_TO_DIE;
	
	@ClientString(id = 11451, message = "$s1, your enemy was driven out. I will now withdraw and await your next command.")
	public static NpcStringId S1_YOUR_ENEMY_WAS_DRIVEN_OUT_I_WILL_NOW_WITHDRAW_AND_AWAIT_YOUR_NEXT_COMMAND;
	
	@ClientString(id = 11452, message = "This enemy is far too powerful for me to fight. I must withdraw.")
	public static NpcStringId THIS_ENEMY_IS_FAR_TOO_POWERFUL_FOR_ME_TO_FIGHT_I_MUST_WITHDRAW;
	
	@ClientString(id = 11453, message = "The radio signal detector is responding. # A suspicious pile of stones catches your eye.")
	public static NpcStringId THE_RADIO_SIGNAL_DETECTOR_IS_RESPONDING_A_SUSPICIOUS_PILE_OF_STONES_CATCHES_YOUR_EYE;
	
	@ClientString(id = 11501, message = "The Other Side of Truth")
	public static NpcStringId THE_OTHER_SIDE_OF_TRUTH;
	
	@ClientString(id = 11502, message = "The Other Side of Truth (In Progress)")
	public static NpcStringId THE_OTHER_SIDE_OF_TRUTH_IN_PROGRESS;
	
	@ClientString(id = 11503, message = "The Other Side of Truth (Done)")
	public static NpcStringId THE_OTHER_SIDE_OF_TRUTH_DONE;
	
	@ClientString(id = 11550, message = "This looks like the right place... ")
	public static NpcStringId THIS_LOOKS_LIKE_THE_RIGHT_PLACE;
	
	@ClientString(id = 11551, message = "I see someone. Is this fate?")
	public static NpcStringId I_SEE_SOMEONE_IS_THIS_FATE;
	
	@ClientString(id = 11552, message = "We meet again. ")
	public static NpcStringId WE_MEET_AGAIN;
	
	@ClientString(id = 11553, message = "Don't bother trying to find out more about me. Follow your own destiny. ")
	public static NpcStringId DON_T_BOTHER_TRYING_TO_FIND_OUT_MORE_ABOUT_ME_FOLLOW_YOUR_OWN_DESTINY;
	
	@ClientString(id = 11601, message = "Beyond the Hills of Winter")
	public static NpcStringId BEYOND_THE_HILLS_OF_WINTER;
	
	@ClientString(id = 11602, message = "Beyond the Hills of Winter (In Progress)")
	public static NpcStringId BEYOND_THE_HILLS_OF_WINTER_IN_PROGRESS;
	
	@ClientString(id = 11603, message = "Beyond the Hills of Winter (Done)")
	public static NpcStringId BEYOND_THE_HILLS_OF_WINTER_DONE;
	
	@ClientString(id = 11701, message = "The Ocean of Distant Stars")
	public static NpcStringId THE_OCEAN_OF_DISTANT_STARS;
	
	@ClientString(id = 11702, message = "The Ocean of Distant Stars (In Progress)")
	public static NpcStringId THE_OCEAN_OF_DISTANT_STARS_IN_PROGRESS;
	
	@ClientString(id = 11703, message = "The Ocean of Distant Stars (Done)")
	public static NpcStringId THE_OCEAN_OF_DISTANT_STARS_DONE;
	
	@ClientString(id = 11801, message = "To Lead and Be Led")
	public static NpcStringId TO_LEAD_AND_BE_LED;
	
	@ClientString(id = 11802, message = "To Lead and Be Led (In Progress)")
	public static NpcStringId TO_LEAD_AND_BE_LED_IN_PROGRESS;
	
	@ClientString(id = 11803, message = "To Lead and Be Led (Done)")
	public static NpcStringId TO_LEAD_AND_BE_LED_DONE;
	
	@ClientString(id = 11804, message = "To Lead and Be Led (Sponsor)")
	public static NpcStringId TO_LEAD_AND_BE_LED_SPONSOR;
	
	@ClientString(id = 11901, message = "Last Imperial Prince")
	public static NpcStringId LAST_IMPERIAL_PRINCE;
	
	@ClientString(id = 11902, message = "Last Imperial Prince (In Progress)")
	public static NpcStringId LAST_IMPERIAL_PRINCE_IN_PROGRESS;
	
	@ClientString(id = 11903, message = "Last Imperial Prince (Done)")
	public static NpcStringId LAST_IMPERIAL_PRINCE_DONE;
	
	@ClientString(id = 12001, message = "Pavel's Last Research")
	public static NpcStringId PAVEL_S_LAST_RESEARCH;
	
	@ClientString(id = 12002, message = "Pavel's Last Research (In Progress)")
	public static NpcStringId PAVEL_S_LAST_RESEARCH_IN_PROGRESS;
	
	@ClientString(id = 12003, message = "Pavel's Last Research (Done)")
	public static NpcStringId PAVEL_S_LAST_RESEARCH_DONE;
	
	@ClientString(id = 12101, message = "Pavel the Giant")
	public static NpcStringId PAVEL_THE_GIANT;
	
	@ClientString(id = 12102, message = "Pavel the Giant (In Progress)")
	public static NpcStringId PAVEL_THE_GIANT_IN_PROGRESS;
	
	@ClientString(id = 12103, message = "Pavel the Giant (Done)")
	public static NpcStringId PAVEL_THE_GIANT_DONE;
	
	@ClientString(id = 12201, message = "Ominous News")
	public static NpcStringId OMINOUS_NEWS;
	
	@ClientString(id = 12202, message = "Ominous News (In Progress)")
	public static NpcStringId OMINOUS_NEWS_IN_PROGRESS;
	
	@ClientString(id = 12203, message = "Ominous News (Done)")
	public static NpcStringId OMINOUS_NEWS_DONE;
	
	@ClientString(id = 12301, message = "The Leader and the Follower")
	public static NpcStringId THE_LEADER_AND_THE_FOLLOWER;
	
	@ClientString(id = 12302, message = "The Leader and the Follower (In Progress)")
	public static NpcStringId THE_LEADER_AND_THE_FOLLOWER_IN_PROGRESS;
	
	@ClientString(id = 12303, message = "The Leader and the Follower (Done)")
	public static NpcStringId THE_LEADER_AND_THE_FOLLOWER_DONE;
	
	@ClientString(id = 12304, message = "The Leader and the Follower (Sponsor)")
	public static NpcStringId THE_LEADER_AND_THE_FOLLOWER_SPONSOR;
	
	@ClientString(id = 12401, message = "Meeting the Elroki")
	public static NpcStringId MEETING_THE_ELROKI;
	
	@ClientString(id = 12402, message = "Meeting the Elroki (In Progress)")
	public static NpcStringId MEETING_THE_ELROKI_IN_PROGRESS;
	
	@ClientString(id = 12403, message = "Meeting the Elroki (Done)")
	public static NpcStringId MEETING_THE_ELROKI_DONE;
	
	@ClientString(id = 12501, message = "The Name of Evil 1")
	public static NpcStringId THE_NAME_OF_EVIL_1;
	
	@ClientString(id = 12502, message = "The Name of Evil 1 (In Progress)")
	public static NpcStringId THE_NAME_OF_EVIL_1_IN_PROGRESS;
	
	@ClientString(id = 12503, message = "The Name of Evil 1 (Done)")
	public static NpcStringId THE_NAME_OF_EVIL_1_DONE;
	
	@ClientString(id = 12601, message = "The Name of Evil 2")
	public static NpcStringId THE_NAME_OF_EVIL_2;
	
	@ClientString(id = 12602, message = "The Name of Evil 2 (In Progress)")
	public static NpcStringId THE_NAME_OF_EVIL_2_IN_PROGRESS;
	
	@ClientString(id = 12603, message = "The Name of Evil 2 (Done)")
	public static NpcStringId THE_NAME_OF_EVIL_2_DONE;
	
	@ClientString(id = 12701, message = "Kamael: A Window to the Future")
	public static NpcStringId KAMAEL_A_WINDOW_TO_THE_FUTURE;
	
	@ClientString(id = 12702, message = "Kamael: A Window to the Future (In Progress)")
	public static NpcStringId KAMAEL_A_WINDOW_TO_THE_FUTURE_IN_PROGRESS;
	
	@ClientString(id = 12703, message = "Kamael: A Window to the Future (Done)")
	public static NpcStringId KAMAEL_A_WINDOW_TO_THE_FUTURE_DONE;
	
	@ClientString(id = 12801, message = "Pailaka - Song of Ice and Fire")
	public static NpcStringId PAILAKA_SONG_OF_ICE_AND_FIRE;
	
	@ClientString(id = 12802, message = "Pailaka - Song of Ice and Fire (In Progress)")
	public static NpcStringId PAILAKA_SONG_OF_ICE_AND_FIRE_IN_PROGRESS;
	
	@ClientString(id = 12803, message = "Pailaka - Song of Ice and Fire (done)")
	public static NpcStringId PAILAKA_SONG_OF_ICE_AND_FIRE_DONE;
	
	@ClientString(id = 12901, message = "Pailaka - Devil's Legacy")
	public static NpcStringId PAILAKA_DEVIL_S_LEGACY;
	
	@ClientString(id = 12902, message = "Pailaka - Devil's Legacy (In Progress)")
	public static NpcStringId PAILAKA_DEVIL_S_LEGACY_IN_PROGRESS;
	
	@ClientString(id = 12903, message = "Pailaka - Devil's Legacy (done)")
	public static NpcStringId PAILAKA_DEVIL_S_LEGACY_DONE;
	
	@ClientString(id = 13001, message = "Path to Hellbound")
	public static NpcStringId PATH_TO_HELLBOUND;
	
	@ClientString(id = 13002, message = "Path to Hellbound (In Progress)")
	public static NpcStringId PATH_TO_HELLBOUND_IN_PROGRESS;
	
	@ClientString(id = 13003, message = "Path to Hellbound (Done)")
	public static NpcStringId PATH_TO_HELLBOUND_DONE;
	
	@ClientString(id = 13101, message = "Bird in a Cage")
	public static NpcStringId BIRD_IN_A_CAGE;
	
	@ClientString(id = 13102, message = "Bird in a Cage (In Progress)")
	public static NpcStringId BIRD_IN_A_CAGE_IN_PROGRESS;
	
	@ClientString(id = 13103, message = "Bird in a Cage (Done)")
	public static NpcStringId BIRD_IN_A_CAGE_DONE;
	
	@ClientString(id = 13201, message = "Curiosity of a Matras")
	public static NpcStringId CURIOSITY_OF_A_MATRAS;
	
	@ClientString(id = 13202, message = "Curiosity of a Matras (In Progress)")
	public static NpcStringId CURIOSITY_OF_A_MATRAS_IN_PROGRESS;
	
	@ClientString(id = 13203, message = "Curiosity of a Matras (Done)")
	public static NpcStringId CURIOSITY_OF_A_MATRAS_DONE;
	
	@ClientString(id = 13301, message = "That's Bloody Hot!")
	public static NpcStringId THAT_S_BLOODY_HOT;
	
	@ClientString(id = 13302, message = "That's Bloody Hot! (In Progress)")
	public static NpcStringId THAT_S_BLOODY_HOT_IN_PROGRESS;
	
	@ClientString(id = 13303, message = "That's Bloody Hot! (Done)")
	public static NpcStringId THAT_S_BLOODY_HOT_DONE;
	
	@ClientString(id = 13401, message = "Temple Missionary")
	public static NpcStringId TEMPLE_MISSIONARY;
	
	@ClientString(id = 13402, message = "Temple Missionary (In Progress)")
	public static NpcStringId TEMPLE_MISSIONARY_IN_PROGRESS;
	
	@ClientString(id = 13403, message = "Temple Missionary (Done)")
	public static NpcStringId TEMPLE_MISSIONARY_DONE;
	
	@ClientString(id = 13501, message = "Temple Executor")
	public static NpcStringId TEMPLE_EXECUTOR;
	
	@ClientString(id = 13502, message = "Temple Executor (In Progress)")
	public static NpcStringId TEMPLE_EXECUTOR_IN_PROGRESS;
	
	@ClientString(id = 13503, message = "Temple Executor (Done)")
	public static NpcStringId TEMPLE_EXECUTOR_DONE;
	
	@ClientString(id = 13601, message = "More Than Meets The Eye")
	public static NpcStringId MORE_THAN_MEETS_THE_EYE;
	
	@ClientString(id = 13602, message = "More Than Meets The Eye (In Progress)")
	public static NpcStringId MORE_THAN_MEETS_THE_EYE_IN_PROGRESS;
	
	@ClientString(id = 13603, message = "More Than Meets The Eye (Done)")
	public static NpcStringId MORE_THAN_MEETS_THE_EYE_DONE;
	
	@ClientString(id = 13701, message = "Temple Champion - 1")
	public static NpcStringId TEMPLE_CHAMPION_1;
	
	@ClientString(id = 13702, message = "Temple Champion - 1 (In Progress)")
	public static NpcStringId TEMPLE_CHAMPION_1_IN_PROGRESS;
	
	@ClientString(id = 13703, message = "Temple Champion - 1 (Done)")
	public static NpcStringId TEMPLE_CHAMPION_1_DONE;
	
	@ClientString(id = 13801, message = "Temple Champion - 2")
	public static NpcStringId TEMPLE_CHAMPION_2;
	
	@ClientString(id = 13802, message = "Temple Champion - 2 (In Progress)")
	public static NpcStringId TEMPLE_CHAMPION_2_IN_PROGRESS;
	
	@ClientString(id = 13803, message = "Temple Champion - 2 (Done)")
	public static NpcStringId TEMPLE_CHAMPION_2_DONE;
	
	@ClientString(id = 13901, message = "Shadow Fox - 1")
	public static NpcStringId SHADOW_FOX_1;
	
	@ClientString(id = 13902, message = "Shadow Fox - 1 (In Progress)")
	public static NpcStringId SHADOW_FOX_1_IN_PROGRESS;
	
	@ClientString(id = 13903, message = "Shadow Fox - 1 (Done)")
	public static NpcStringId SHADOW_FOX_1_DONE;
	
	@ClientString(id = 14001, message = "Shadow Fox - 2")
	public static NpcStringId SHADOW_FOX_2;
	
	@ClientString(id = 14002, message = "Shadow Fox - 2 (In Progress)")
	public static NpcStringId SHADOW_FOX_2_IN_PROGRESS;
	
	@ClientString(id = 14003, message = "Shadow Fox - 2 (Done)")
	public static NpcStringId SHADOW_FOX_2_DONE;
	
	@ClientString(id = 14101, message = "Shadow Fox - 3")
	public static NpcStringId SHADOW_FOX_3;
	
	@ClientString(id = 14102, message = "Shadow Fox - 3 (In Progress)")
	public static NpcStringId SHADOW_FOX_3_IN_PROGRESS;
	
	@ClientString(id = 14103, message = "Shadow Fox - 3 (Done)")
	public static NpcStringId SHADOW_FOX_3_DONE;
	
	@ClientString(id = 14201, message = "Fallen Angel - Request of Dawn")
	public static NpcStringId FALLEN_ANGEL_REQUEST_OF_DAWN;
	
	@ClientString(id = 14202, message = "Fallen Angel - Request of Dawn (In Progress)")
	public static NpcStringId FALLEN_ANGEL_REQUEST_OF_DAWN_IN_PROGRESS;
	
	@ClientString(id = 14203, message = "Fallen Angel - Request of Dawn (Done)")
	public static NpcStringId FALLEN_ANGEL_REQUEST_OF_DAWN_DONE;
	
	@ClientString(id = 14204, message = "Fallen Angel - Select")
	public static NpcStringId FALLEN_ANGEL_SELECT;
	
	@ClientString(id = 14301, message = "Fallen Angel - Request of Dusk")
	public static NpcStringId FALLEN_ANGEL_REQUEST_OF_DUSK;
	
	@ClientString(id = 14302, message = "Fallen Angel - Request of Dusk (In Progress)")
	public static NpcStringId FALLEN_ANGEL_REQUEST_OF_DUSK_IN_PROGRESS;
	
	@ClientString(id = 14303, message = "Fallen Angel - Request of Dusk (Done)")
	public static NpcStringId FALLEN_ANGEL_REQUEST_OF_DUSK_DONE;
	
	@ClientString(id = 14401, message = "Pailaka - Injured Dragon")
	public static NpcStringId PAILAKA_INJURED_DRAGON;
	
	@ClientString(id = 14402, message = "Pailaka - Injured Dragon (In Progress)")
	public static NpcStringId PAILAKA_INJURED_DRAGON_IN_PROGRESS;
	
	@ClientString(id = 14403, message = "Pailaka - Injured Dragon (done)")
	public static NpcStringId PAILAKA_INJURED_DRAGON_DONE;
	
	@ClientString(id = 14701, message = "Path to Becoming an Elite Mercenary")
	public static NpcStringId PATH_TO_BECOMING_AN_ELITE_MERCENARY;
	
	@ClientString(id = 14702, message = "Path to Becoming an Elite Mercenary (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_AN_ELITE_MERCENARY_IN_PROGRESS;
	
	@ClientString(id = 14703, message = "Path to Becoming an Elite Mercenary (Done)")
	public static NpcStringId PATH_TO_BECOMING_AN_ELITE_MERCENARY_DONE;
	
	@ClientString(id = 14801, message = "Path to Becoming an Exalted Mercenary")
	public static NpcStringId PATH_TO_BECOMING_AN_EXALTED_MERCENARY;
	
	@ClientString(id = 14802, message = "Path to Becoming an Exalted Mercenary (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_AN_EXALTED_MERCENARY_IN_PROGRESS;
	
	@ClientString(id = 14803, message = "Path to Becoming an Exalted Mercenary (Done)")
	public static NpcStringId PATH_TO_BECOMING_AN_EXALTED_MERCENARY_DONE;
	
	@ClientString(id = 15101, message = "Cure for Fever")
	public static NpcStringId CURE_FOR_FEVER;
	
	@ClientString(id = 15102, message = "Cure for Fever (In Progress)")
	public static NpcStringId CURE_FOR_FEVER_IN_PROGRESS;
	
	@ClientString(id = 15103, message = "Cure for Fever (Done)")
	public static NpcStringId CURE_FOR_FEVER_DONE;
	
	@ClientString(id = 15201, message = "Shards of Golem")
	public static NpcStringId SHARDS_OF_GOLEM;
	
	@ClientString(id = 15202, message = "Shards of Golem (In Progress)")
	public static NpcStringId SHARDS_OF_GOLEM_IN_PROGRESS;
	
	@ClientString(id = 15203, message = "Shards of Golem (Done)")
	public static NpcStringId SHARDS_OF_GOLEM_DONE;
	
	@ClientString(id = 15301, message = "Deliver Goods")
	public static NpcStringId DELIVER_GOODS;
	
	@ClientString(id = 15302, message = "Deliver Goods (In Progress)")
	public static NpcStringId DELIVER_GOODS_IN_PROGRESS;
	
	@ClientString(id = 15303, message = "Deliver Goods (Done)")
	public static NpcStringId DELIVER_GOODS_DONE;
	
	@ClientString(id = 15401, message = "Sacrifice to the Sea")
	public static NpcStringId SACRIFICE_TO_THE_SEA;
	
	@ClientString(id = 15402, message = "Sacrifice to the Sea (In Progress)")
	public static NpcStringId SACRIFICE_TO_THE_SEA_IN_PROGRESS;
	
	@ClientString(id = 15403, message = "Sacrifice to the Sea (Done)")
	public static NpcStringId SACRIFICE_TO_THE_SEA_DONE;
	
	@ClientString(id = 15501, message = "Find Sir Windawood")
	public static NpcStringId FIND_SIR_WINDAWOOD;
	
	@ClientString(id = 15502, message = "Find Sir Windawood (In Progress)")
	public static NpcStringId FIND_SIR_WINDAWOOD_IN_PROGRESS;
	
	@ClientString(id = 15503, message = "Find Sir Windawood (Done)")
	public static NpcStringId FIND_SIR_WINDAWOOD_DONE;
	
	@ClientString(id = 15601, message = "Millennium Love")
	public static NpcStringId MILLENNIUM_LOVE;
	
	@ClientString(id = 15602, message = "Millennium Love (In Progress)")
	public static NpcStringId MILLENNIUM_LOVE_IN_PROGRESS;
	
	@ClientString(id = 15603, message = "Millennium Love (Done)")
	public static NpcStringId MILLENNIUM_LOVE_DONE;
	
	@ClientString(id = 15701, message = "Recover Smuggled Goods")
	public static NpcStringId RECOVER_SMUGGLED_GOODS;
	
	@ClientString(id = 15702, message = "Recover Smuggled Goods (In Progress)")
	public static NpcStringId RECOVER_SMUGGLED_GOODS_IN_PROGRESS;
	
	@ClientString(id = 15703, message = "Recover Smuggled Goods (Done)")
	public static NpcStringId RECOVER_SMUGGLED_GOODS_DONE;
	
	@ClientString(id = 15801, message = "Seed of Evil")
	public static NpcStringId SEED_OF_EVIL;
	
	@ClientString(id = 15802, message = "Seed of Evil (In Progress)")
	public static NpcStringId SEED_OF_EVIL_IN_PROGRESS;
	
	@ClientString(id = 15803, message = "Seed of Evil (Done)")
	public static NpcStringId SEED_OF_EVIL_DONE;
	
	@ClientString(id = 15804, message = "... How dare you challenge me!")
	public static NpcStringId HOW_DARE_YOU_CHALLENGE_ME;
	
	@ClientString(id = 15805, message = "The power of Lord Beleth rules the whole world...!")
	public static NpcStringId THE_POWER_OF_LORD_BELETH_RULES_THE_WHOLE_WORLD;
	
	@ClientString(id = 15901, message = "Protect the Water Source")
	public static NpcStringId PROTECT_THE_WATER_SOURCE;
	
	@ClientString(id = 15902, message = "Protect the Water Source (In Progress)")
	public static NpcStringId PROTECT_THE_WATER_SOURCE_IN_PROGRESS;
	
	@ClientString(id = 15903, message = "Protect the Water Source (Done)")
	public static NpcStringId PROTECT_THE_WATER_SOURCE_DONE;
	
	@ClientString(id = 16001, message = "Nerupa's Request")
	public static NpcStringId NERUPA_S_REQUEST;
	
	@ClientString(id = 16002, message = "Nerupa's Request (In Progress)")
	public static NpcStringId NERUPA_S_REQUEST_IN_PROGRESS;
	
	@ClientString(id = 16003, message = "Nerupa's Request (Done)")
	public static NpcStringId NERUPA_S_REQUEST_DONE;
	
	@ClientString(id = 16101, message = "Fruit of the Mother Tree")
	public static NpcStringId FRUIT_OF_THE_MOTHER_TREE;
	
	@ClientString(id = 16102, message = "Fruit of the Mother Tree (In Progress)")
	public static NpcStringId FRUIT_OF_THE_MOTHER_TREE_IN_PROGRESS;
	
	@ClientString(id = 16103, message = "Fruit of the Mother Tree (Done)")
	public static NpcStringId FRUIT_OF_THE_MOTHER_TREE_DONE;
	
	@ClientString(id = 16201, message = "Curse of the Fortress")
	public static NpcStringId CURSE_OF_THE_FORTRESS;
	
	@ClientString(id = 16202, message = "Curse of the Fortress (In Progress)")
	public static NpcStringId CURSE_OF_THE_FORTRESS_IN_PROGRESS;
	
	@ClientString(id = 16203, message = "Curse of the Fortress (Done)")
	public static NpcStringId CURSE_OF_THE_FORTRESS_DONE;
	
	@ClientString(id = 16301, message = "Legacy of the Poet")
	public static NpcStringId LEGACY_OF_THE_POET;
	
	@ClientString(id = 16302, message = "Legacy of the Poet (In Progress)")
	public static NpcStringId LEGACY_OF_THE_POET_IN_PROGRESS;
	
	@ClientString(id = 16303, message = "Legacy of the Poet (Done)")
	public static NpcStringId LEGACY_OF_THE_POET_DONE;
	
	@ClientString(id = 16401, message = "Blood Fiend")
	public static NpcStringId BLOOD_FIEND;
	
	@ClientString(id = 16402, message = "Blood Fiend (In Progress)")
	public static NpcStringId BLOOD_FIEND_IN_PROGRESS;
	
	@ClientString(id = 16403, message = "Blood Fiend (Done)")
	public static NpcStringId BLOOD_FIEND_DONE;
	
	@ClientString(id = 16404, message = "I will taste your blood!")
	public static NpcStringId I_WILL_TASTE_YOUR_BLOOD;
	
	@ClientString(id = 16405, message = "I have fulfilled my contract with Trader Creamees.")
	public static NpcStringId I_HAVE_FULFILLED_MY_CONTRACT_WITH_TRADER_CREAMEES;
	
	@ClientString(id = 16501, message = "Shilen's Hunt")
	public static NpcStringId SHILEN_S_HUNT;
	
	@ClientString(id = 16502, message = "Shilen's Hunt (In Progress)")
	public static NpcStringId SHILEN_S_HUNT_IN_PROGRESS;
	
	@ClientString(id = 16503, message = "Shilen's Hunt (Done)")
	public static NpcStringId SHILEN_S_HUNT_DONE;
	
	@ClientString(id = 16601, message = "Mass of Darkness")
	public static NpcStringId MASS_OF_DARKNESS;
	
	@ClientString(id = 16602, message = "Mass of Darkness (In Progress)")
	public static NpcStringId MASS_OF_DARKNESS_IN_PROGRESS;
	
	@ClientString(id = 16603, message = "Mass of Darkness (Done)")
	public static NpcStringId MASS_OF_DARKNESS_DONE;
	
	@ClientString(id = 16701, message = "Dwarven Kinship")
	public static NpcStringId DWARVEN_KINSHIP;
	
	@ClientString(id = 16702, message = "Dwarven Kinship (In Progress)")
	public static NpcStringId DWARVEN_KINSHIP_IN_PROGRESS;
	
	@ClientString(id = 16703, message = "Dwarven Kinship (Done)")
	public static NpcStringId DWARVEN_KINSHIP_DONE;
	
	@ClientString(id = 16801, message = "Deliver Supplies")
	public static NpcStringId DELIVER_SUPPLIES;
	
	@ClientString(id = 16802, message = "Deliver Supplies (In Progress)")
	public static NpcStringId DELIVER_SUPPLIES_IN_PROGRESS;
	
	@ClientString(id = 16803, message = "Deliver Supplies (Done)")
	public static NpcStringId DELIVER_SUPPLIES_DONE;
	
	@ClientString(id = 16901, message = "Offspring of Nightmares")
	public static NpcStringId OFFSPRING_OF_NIGHTMARES;
	
	@ClientString(id = 16902, message = "Offspring of Nightmares (In Progress)")
	public static NpcStringId OFFSPRING_OF_NIGHTMARES_IN_PROGRESS;
	
	@ClientString(id = 16903, message = "Offspring of Nightmares (Done)")
	public static NpcStringId OFFSPRING_OF_NIGHTMARES_DONE;
	
	@ClientString(id = 17001, message = "Dangerous Seduction")
	public static NpcStringId DANGEROUS_SEDUCTION;
	
	@ClientString(id = 17002, message = "Dangerous Seduction (In Progress)")
	public static NpcStringId DANGEROUS_SEDUCTION_IN_PROGRESS;
	
	@ClientString(id = 17003, message = "Dangerous Seduction (Done)")
	public static NpcStringId DANGEROUS_SEDUCTION_DONE;
	
	@ClientString(id = 17004, message = "I'll cast you into an eternal nightmare!")
	public static NpcStringId I_LL_CAST_YOU_INTO_AN_ETERNAL_NIGHTMARE;
	
	@ClientString(id = 17005, message = "Send my soul to Lich King Icarus...")
	public static NpcStringId SEND_MY_SOUL_TO_LICH_KING_ICARUS;
	
	@ClientString(id = 17101, message = "Acts of Evil")
	public static NpcStringId ACTS_OF_EVIL;
	
	@ClientString(id = 17102, message = "Acts of Evil (In Progress)")
	public static NpcStringId ACTS_OF_EVIL_IN_PROGRESS;
	
	@ClientString(id = 17103, message = "Acts of Evil (Done)")
	public static NpcStringId ACTS_OF_EVIL_DONE;
	
	@ClientString(id = 17151, message = "You should consider going back...")
	public static NpcStringId YOU_SHOULD_CONSIDER_GOING_BACK;
	
	@ClientString(id = 17201, message = "New Horizons")
	public static NpcStringId NEW_HORIZONS;
	
	@ClientString(id = 17202, message = "New Horizons (In Progress)")
	public static NpcStringId NEW_HORIZONS_IN_PROGRESS;
	
	@ClientString(id = 17203, message = "New Horizons (Done)")
	public static NpcStringId NEW_HORIZONS_DONE;
	
	@ClientString(id = 17301, message = "To the Isle of Souls")
	public static NpcStringId TO_THE_ISLE_OF_SOULS;
	
	@ClientString(id = 17302, message = "To the Isle of Souls (In Progress)")
	public static NpcStringId TO_THE_ISLE_OF_SOULS_IN_PROGRESS;
	
	@ClientString(id = 17303, message = "To the Isle of Souls (Done)")
	public static NpcStringId TO_THE_ISLE_OF_SOULS_DONE;
	
	@ClientString(id = 17401, message = "Supply Check")
	public static NpcStringId SUPPLY_CHECK;
	
	@ClientString(id = 17402, message = "Supply Check (In Progress)")
	public static NpcStringId SUPPLY_CHECK_IN_PROGRESS;
	
	@ClientString(id = 17403, message = "Supply Check (Done)")
	public static NpcStringId SUPPLY_CHECK_DONE;
	
	@ClientString(id = 17501, message = "The Way of the Warrior")
	public static NpcStringId THE_WAY_OF_THE_WARRIOR;
	
	@ClientString(id = 17502, message = "The Way of the Warrior (In Progress)")
	public static NpcStringId THE_WAY_OF_THE_WARRIOR_IN_PROGRESS;
	
	@ClientString(id = 17503, message = "The Way of the Warrior (Done)")
	public static NpcStringId THE_WAY_OF_THE_WARRIOR_DONE;
	
	@ClientString(id = 17601, message = "Steps for Honor")
	public static NpcStringId STEPS_FOR_HONOR;
	
	@ClientString(id = 17602, message = "Steps for Honor (In Progress)")
	public static NpcStringId STEPS_FOR_HONOR_IN_PROGRESS;
	
	@ClientString(id = 17603, message = "Steps for Honor (completed)")
	public static NpcStringId STEPS_FOR_HONOR_COMPLETED;
	
	@ClientString(id = 17801, message = "Iconic Trinity")
	public static NpcStringId ICONIC_TRINITY;
	
	@ClientString(id = 17802, message = "Iconic Trinity (In Progress)")
	public static NpcStringId ICONIC_TRINITY_IN_PROGRESS;
	
	@ClientString(id = 17803, message = "Iconic Trinity (Done)")
	public static NpcStringId ICONIC_TRINITY_DONE;
	
	@ClientString(id = 17901, message = "Into the Large Cavern")
	public static NpcStringId INTO_THE_LARGE_CAVERN;
	
	@ClientString(id = 17902, message = "Into the Large Cavern (In Progress)")
	public static NpcStringId INTO_THE_LARGE_CAVERN_IN_PROGRESS;
	
	@ClientString(id = 17903, message = "Into the Large Cavern (Done)")
	public static NpcStringId INTO_THE_LARGE_CAVERN_DONE;
	
	@ClientString(id = 17951, message = "The Veiled Creator...")
	public static NpcStringId THE_VEILED_CREATOR;
	
	@ClientString(id = 17952, message = "The Conspiracy of the Ancient Race")
	public static NpcStringId THE_CONSPIRACY_OF_THE_ANCIENT_RACE;
	
	@ClientString(id = 17953, message = "Chaos and Time...")
	public static NpcStringId CHAOS_AND_TIME;
	
	@ClientString(id = 18201, message = "New Recruits")
	public static NpcStringId NEW_RECRUITS;
	
	@ClientString(id = 18202, message = "New Recruits (In Progress)")
	public static NpcStringId NEW_RECRUITS_IN_PROGRESS;
	
	@ClientString(id = 18203, message = "New Recruits (Done)")
	public static NpcStringId NEW_RECRUITS_DONE;
	
	@ClientString(id = 18301, message = "Relics Exploration")
	public static NpcStringId RELICS_EXPLORATION;
	
	@ClientString(id = 18302, message = "Relics Exploration (In Progress)")
	public static NpcStringId RELICS_EXPLORATION_IN_PROGRESS;
	
	@ClientString(id = 18303, message = "Relics Exploration (Done)")
	public static NpcStringId RELICS_EXPLORATION_DONE;
	
	@ClientString(id = 18401, message = "Art of Persuasion")
	public static NpcStringId ART_OF_PERSUASION;
	
	@ClientString(id = 18402, message = "Art of Persuasion (In Progress)")
	public static NpcStringId ART_OF_PERSUASION_IN_PROGRESS;
	
	@ClientString(id = 18403, message = "Art of Persuasion (Done)")
	public static NpcStringId ART_OF_PERSUASION_DONE;
	
	@ClientString(id = 18404, message = "Nikola's Cooperation")
	public static NpcStringId NIKOLA_S_COOPERATION;
	
	@ClientString(id = 18451, message = "Intruder Alert! The alarm will self-destruct in 2 minutes.")
	public static NpcStringId INTRUDER_ALERT_THE_ALARM_WILL_SELF_DESTRUCT_IN_2_MINUTES;
	
	@ClientString(id = 18452, message = "The alarm will self-destruct in 60 seconds. Enter passcode to override.")
	public static NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;
	
	@ClientString(id = 18453, message = "The alarm will self-destruct in 30 seconds. Enter passcode to override.")
	public static NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;
	
	@ClientString(id = 18454, message = "The alarm will self-destruct in 10 seconds. Enter passcode to override.")
	public static NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;
	
	@ClientString(id = 18455, message = "Recorder crushed.")
	public static NpcStringId RECORDER_CRUSHED;
	
	@ClientString(id = 18501, message = "Nikola's Cooperation")
	public static NpcStringId NIKOLA_S_COOPERATION_2;
	
	@ClientString(id = 18502, message = "Nikola's Cooperation (In Progress)")
	public static NpcStringId NIKOLA_S_COOPERATION_IN_PROGRESS;
	
	@ClientString(id = 18503, message = "Nikola's Cooperation (Done)")
	public static NpcStringId NIKOLA_S_COOPERATION_DONE;
	
	@ClientString(id = 18551, message = "Intruder Alert! The alarm will self-destruct in 2 minutes.")
	public static NpcStringId INTRUDER_ALERT_THE_ALARM_WILL_SELF_DESTRUCT_IN_2_MINUTES_2;
	
	@ClientString(id = 18552, message = "The alarm will self-destruct in 60 seconds. Please evacuate immediately!")
	public static NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;
	
	@ClientString(id = 18553, message = "The alarm will self-destruct in 30 seconds. Please evacuate immediately!")
	public static NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;
	
	@ClientString(id = 18554, message = "The alarm will self-destruct in 10 seconds. Please evacuate immediately!")
	public static NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;
	
	@ClientString(id = 18601, message = "Contract Execution")
	public static NpcStringId CONTRACT_EXECUTION;
	
	@ClientString(id = 18602, message = "Contract Execution (In Progress)")
	public static NpcStringId CONTRACT_EXECUTION_IN_PROGRESS;
	
	@ClientString(id = 18603, message = "Contract Execution (Done)")
	public static NpcStringId CONTRACT_EXECUTION_DONE;
	
	@ClientString(id = 18701, message = "Nikola's Heart")
	public static NpcStringId NIKOLA_S_HEART;
	
	@ClientString(id = 18702, message = "Nikola's Heart (In Progress)")
	public static NpcStringId NIKOLA_S_HEART_IN_PROGRESS;
	
	@ClientString(id = 18703, message = "Nikola's Heart (Done)")
	public static NpcStringId NIKOLA_S_HEART_DONE;
	
	@ClientString(id = 18801, message = "Seal Removal")
	public static NpcStringId SEAL_REMOVAL;
	
	@ClientString(id = 18802, message = "Seal Removal (In Progress)")
	public static NpcStringId SEAL_REMOVAL_IN_PROGRESS;
	
	@ClientString(id = 18803, message = "Seal Removal (Done)")
	public static NpcStringId SEAL_REMOVAL_DONE;
	
	@ClientString(id = 18901, message = "Contract Completion")
	public static NpcStringId CONTRACT_COMPLETION;
	
	@ClientString(id = 18902, message = "Contract Completion (In Progress)")
	public static NpcStringId CONTRACT_COMPLETION_IN_PROGRESS;
	
	@ClientString(id = 18903, message = "Contract Completion (Done)")
	public static NpcStringId CONTRACT_COMPLETION_DONE;
	
	@ClientString(id = 19001, message = "Lost Dream")
	public static NpcStringId LOST_DREAM;
	
	@ClientString(id = 19002, message = "Lost Dream (In Progress)")
	public static NpcStringId LOST_DREAM_IN_PROGRESS;
	
	@ClientString(id = 19003, message = "Lost Dream (Done)")
	public static NpcStringId LOST_DREAM_DONE;
	
	@ClientString(id = 19101, message = "Vain Conclusion")
	public static NpcStringId VAIN_CONCLUSION;
	
	@ClientString(id = 19102, message = "Vain Conclusion (In Progress)")
	public static NpcStringId VAIN_CONCLUSION_IN_PROGRESS;
	
	@ClientString(id = 19103, message = "Vain Conclusion (Done)")
	public static NpcStringId VAIN_CONCLUSION_DONE;
	
	@ClientString(id = 19201, message = "Seven Signs, Series of Doubt")
	public static NpcStringId SEVEN_SIGNS_SERIES_OF_DOUBT;
	
	@ClientString(id = 19202, message = "Seven Signs, Series of Doubt (In Progress)")
	public static NpcStringId SEVEN_SIGNS_SERIES_OF_DOUBT_IN_PROGRESS;
	
	@ClientString(id = 19203, message = "Seven Signs, Series of Doubt (Done)")
	public static NpcStringId SEVEN_SIGNS_SERIES_OF_DOUBT_DONE;
	
	@ClientString(id = 19301, message = "Seven Signs, Dying Message")
	public static NpcStringId SEVEN_SIGNS_DYING_MESSAGE;
	
	@ClientString(id = 19302, message = "Seven Signs, Dying Message (In Progress)")
	public static NpcStringId SEVEN_SIGNS_DYING_MESSAGE_IN_PROGRESS;
	
	@ClientString(id = 19303, message = "Seven Signs, Dying Message (Done)")
	public static NpcStringId SEVEN_SIGNS_DYING_MESSAGE_DONE;
	
	@ClientString(id = 19304, message = "$s1! You are not the owner of that item.")
	public static NpcStringId S1_YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM;
	
	@ClientString(id = 19305, message = "Next time, you will not escape!")
	public static NpcStringId NEXT_TIME_YOU_WILL_NOT_ESCAPE;
	
	@ClientString(id = 19306, message = "$s1! You may have won this time... But next time, I will surely capture you!")
	public static NpcStringId S1_YOU_MAY_HAVE_WON_THIS_TIME_BUT_NEXT_TIME_I_WILL_SURELY_CAPTURE_YOU;
	
	@ClientString(id = 19401, message = "Seven Signs, Mammon's Contract")
	public static NpcStringId SEVEN_SIGNS_MAMMON_S_CONTRACT;
	
	@ClientString(id = 19402, message = "Seven Signs, Mammon's Contract (In Progress)")
	public static NpcStringId SEVEN_SIGNS_MAMMON_S_CONTRACT_IN_PROGRESS;
	
	@ClientString(id = 19403, message = "Seven Signs, Mammon's Contract (Done)")
	public static NpcStringId SEVEN_SIGNS_MAMMON_S_CONTRACT_DONE;
	
	@ClientString(id = 19501, message = "Seven Signs, Secret Ritual of the Priests")
	public static NpcStringId SEVEN_SIGNS_SECRET_RITUAL_OF_THE_PRIESTS;
	
	@ClientString(id = 19502, message = "Seven Signs, Secret Ritual of the Priests (In Progress)")
	public static NpcStringId SEVEN_SIGNS_SECRET_RITUAL_OF_THE_PRIESTS_IN_PROGRESS;
	
	@ClientString(id = 19503, message = "Seven Signs, Secret Ritual of the Priests (Done)")
	public static NpcStringId SEVEN_SIGNS_SECRET_RITUAL_OF_THE_PRIESTS_DONE;
	
	@ClientString(id = 19504, message = "Intruder! Protect the Priests of Dawn!")
	public static NpcStringId INTRUDER_PROTECT_THE_PRIESTS_OF_DAWN;
	
	@ClientString(id = 19505, message = "Who are you?! A new face like you can't approach this place!")
	public static NpcStringId WHO_ARE_YOU_A_NEW_FACE_LIKE_YOU_CAN_T_APPROACH_THIS_PLACE;
	
	@ClientString(id = 19506, message = "How dare you intrude with that transformation! Get lost!")
	public static NpcStringId HOW_DARE_YOU_INTRUDE_WITH_THAT_TRANSFORMATION_GET_LOST;
	
	@ClientString(id = 19601, message = "Seven Signs, Seal of the Emperor")
	public static NpcStringId SEVEN_SIGNS_SEAL_OF_THE_EMPEROR;
	
	@ClientString(id = 19602, message = "Seven Signs, Seal of the Emperor (In Progress)")
	public static NpcStringId SEVEN_SIGNS_SEAL_OF_THE_EMPEROR_IN_PROGRESS;
	
	@ClientString(id = 19603, message = "Seven Signs, Seal of the Emperor (Done)")
	public static NpcStringId SEVEN_SIGNS_SEAL_OF_THE_EMPEROR_DONE;
	
	@ClientString(id = 19604, message = "Who dares summon the Merchant of Mammon?!")
	public static NpcStringId WHO_DARES_SUMMON_THE_MERCHANT_OF_MAMMON;
	
	@ClientString(id = 19605, message = "The ancient promise to the Emperor has been fulfilled.")
	public static NpcStringId THE_ANCIENT_PROMISE_TO_THE_EMPEROR_HAS_BEEN_FULFILLED;
	
	@ClientString(id = 19606, message = "For the eternity of Einhasad!!!")
	public static NpcStringId FOR_THE_ETERNITY_OF_EINHASAD;
	
	@ClientString(id = 19607, message = "Dear Shillien's offsprings! You are not capable of confronting us!")
	public static NpcStringId DEAR_SHILLIEN_S_OFFSPRINGS_YOU_ARE_NOT_CAPABLE_OF_CONFRONTING_US;
	
	@ClientString(id = 19608, message = "I'll show you the real power of Einhasad!")
	public static NpcStringId I_LL_SHOW_YOU_THE_REAL_POWER_OF_EINHASAD;
	
	@ClientString(id = 19609, message = "Dear Military Force of Light! Go destroy the offsprings of Shillien!!!")
	public static NpcStringId DEAR_MILITARY_FORCE_OF_LIGHT_GO_DESTROY_THE_OFFSPRINGS_OF_SHILLIEN;
	
	@ClientString(id = 19610, message = "Everything is owing to $s1. Thank you.")
	public static NpcStringId EVERYTHING_IS_OWING_TO_S1_THANK_YOU;
	
	@ClientString(id = 19611, message = "My power's weakening.. Hurry and turn on the sealing device!!!")
	public static NpcStringId MY_POWER_S_WEAKENING_HURRY_AND_TURN_ON_THE_SEALING_DEVICE;
	
	@ClientString(id = 19612, message = "All 4 sealing devices must be turned on!!!")
	public static NpcStringId ALL_4_SEALING_DEVICES_MUST_BE_TURNED_ON;
	
	@ClientString(id = 19613, message = "Lilith's attack is getting stronger! Go ahead and turn it on!")
	public static NpcStringId LILITH_S_ATTACK_IS_GETTING_STRONGER_GO_AHEAD_AND_TURN_IT_ON;
	
	@ClientString(id = 19614, message = "Dear $s1, give me more strength.")
	public static NpcStringId DEAR_S1_GIVE_ME_MORE_STRENGTH;
	
	@ClientString(id = 19615, message = "You, such a fool! The victory over this war belongs to Shilien!!!")
	public static NpcStringId YOU_SUCH_A_FOOL_THE_VICTORY_OVER_THIS_WAR_BELONGS_TO_SHILIEN;
	
	@ClientString(id = 19616, message = "How dare you try to contend against me in strength? Ridiculous.")
	public static NpcStringId HOW_DARE_YOU_TRY_TO_CONTEND_AGAINST_ME_IN_STRENGTH_RIDICULOUS;
	
	@ClientString(id = 19617, message = "Anakim! In the name of Great Shilien, I will cut your throat!")
	public static NpcStringId ANAKIM_IN_THE_NAME_OF_GREAT_SHILIEN_I_WILL_CUT_YOUR_THROAT;
	
	@ClientString(id = 19618, message = "You cannot be the match of Lilith. I'll teach you a lesson!")
	public static NpcStringId YOU_CANNOT_BE_THE_MATCH_OF_LILITH_I_LL_TEACH_YOU_A_LESSON;
	
	@ClientString(id = 19619, message = "I must go back to Shilien just like this. Outrageous.")
	public static NpcStringId I_MUST_GO_BACK_TO_SHILIEN_JUST_LIKE_THIS_OUTRAGEOUS;
	
	@ClientString(id = 19701, message = "Seven Signs, the Sacred Book of Seal")
	public static NpcStringId SEVEN_SIGNS_THE_SACRED_BOOK_OF_SEAL;
	
	@ClientString(id = 19702, message = "Seven Signs, the Sacred Book of Seal (In Progress)")
	public static NpcStringId SEVEN_SIGNS_THE_SACRED_BOOK_OF_SEAL_IN_PROGRESS;
	
	@ClientString(id = 19703, message = "Seven Signs, the Sacred Book of Seal (Done)")
	public static NpcStringId SEVEN_SIGNS_THE_SACRED_BOOK_OF_SEAL_DONE;
	
	@ClientString(id = 19801, message = "Seven Signs, Embryo")
	public static NpcStringId SEVEN_SIGNS_EMBRYO;
	
	@ClientString(id = 19802, message = "Seven Signs, Embryo (In Progress)")
	public static NpcStringId SEVEN_SIGNS_EMBRYO_IN_PROGRESS;
	
	@ClientString(id = 19803, message = "Seven Signs, Embryo (Done)")
	public static NpcStringId SEVEN_SIGNS_EMBRYO_DONE;
	
	@ClientString(id = 19804, message = "Death to the enemies of the Lords of Dawn!!!")
	public static NpcStringId DEATH_TO_THE_ENEMIES_OF_THE_LORDS_OF_DAWN;
	
	@ClientString(id = 19805, message = "We will be with you always...")
	public static NpcStringId WE_WILL_BE_WITH_YOU_ALWAYS;
	
	@ClientString(id = 19806, message = "You are not the owner of that item.")
	public static NpcStringId YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM;
	
	@ClientString(id = 19807, message = "Embryo...")
	public static NpcStringId EMBRYO;
	
	@ClientString(id = 20101, message = "Fighter's Tutorial")
	public static NpcStringId FIGHTER_S_TUTORIAL;
	
	@ClientString(id = 20102, message = "Fighter's Tutorial (In Progress)")
	public static NpcStringId FIGHTER_S_TUTORIAL_IN_PROGRESS;
	
	@ClientString(id = 20103, message = "Fighter's Tutorial (Done)")
	public static NpcStringId FIGHTER_S_TUTORIAL_DONE;
	
	@ClientString(id = 20201, message = "Mystic's Tutorial")
	public static NpcStringId MYSTIC_S_TUTORIAL;
	
	@ClientString(id = 20202, message = "Mystic's Tutorial (In Progress)")
	public static NpcStringId MYSTIC_S_TUTORIAL_IN_PROGRESS;
	
	@ClientString(id = 20203, message = "Mystic's Tutorial (Done)")
	public static NpcStringId MYSTIC_S_TUTORIAL_DONE;
	
	@ClientString(id = 20301, message = "Elf's Tutorial")
	public static NpcStringId ELF_S_TUTORIAL;
	
	@ClientString(id = 20302, message = "Elf's Tutorial (In Progress)")
	public static NpcStringId ELF_S_TUTORIAL_IN_PROGRESS;
	
	@ClientString(id = 20303, message = "Elf's Tutorial (Done)")
	public static NpcStringId ELF_S_TUTORIAL_DONE;
	
	@ClientString(id = 20401, message = "Dark Elf's Tutorial")
	public static NpcStringId DARK_ELF_S_TUTORIAL;
	
	@ClientString(id = 20402, message = "Dark Elf's Tutorial (In Progress)")
	public static NpcStringId DARK_ELF_S_TUTORIAL_IN_PROGRESS;
	
	@ClientString(id = 20403, message = "Dark Elf's Tutorial (Done)")
	public static NpcStringId DARK_ELF_S_TUTORIAL_DONE;
	
	@ClientString(id = 20501, message = "Orc's Tutorial")
	public static NpcStringId ORC_S_TUTORIAL;
	
	@ClientString(id = 20502, message = "Orc's Tutorial (In Progress)")
	public static NpcStringId ORC_S_TUTORIAL_IN_PROGRESS;
	
	@ClientString(id = 20503, message = "Orc's Tutorial (Done)")
	public static NpcStringId ORC_S_TUTORIAL_DONE;
	
	@ClientString(id = 20601, message = "Dwarf's Tutorial")
	public static NpcStringId DWARF_S_TUTORIAL;
	
	@ClientString(id = 20602, message = "Dwarf's Tutorial (In Progress)")
	public static NpcStringId DWARF_S_TUTORIAL_IN_PROGRESS;
	
	@ClientString(id = 20603, message = "Dwarf's Tutorial (Done)")
	public static NpcStringId DWARF_S_TUTORIAL_DONE;
	
	@ClientString(id = 20901, message = "Kamael Tutorial")
	public static NpcStringId KAMAEL_TUTORIAL;
	
	@ClientString(id = 21101, message = "Trial of the Challenger")
	public static NpcStringId TRIAL_OF_THE_CHALLENGER;
	
	@ClientString(id = 21102, message = "Trial of the Challenger (In Progress)")
	public static NpcStringId TRIAL_OF_THE_CHALLENGER_IN_PROGRESS;
	
	@ClientString(id = 21103, message = "Trial of the Challenger (Done)")
	public static NpcStringId TRIAL_OF_THE_CHALLENGER_DONE;
	
	@ClientString(id = 21201, message = "Trial of Duty")
	public static NpcStringId TRIAL_OF_DUTY;
	
	@ClientString(id = 21202, message = "Trial of Duty (In Progress)")
	public static NpcStringId TRIAL_OF_DUTY_IN_PROGRESS;
	
	@ClientString(id = 21203, message = "Trial of Duty (Done)")
	public static NpcStringId TRIAL_OF_DUTY_DONE;
	
	@ClientString(id = 21301, message = "Trial of the Seeker")
	public static NpcStringId TRIAL_OF_THE_SEEKER;
	
	@ClientString(id = 21302, message = "Trial of the Seeker (In Progress)")
	public static NpcStringId TRIAL_OF_THE_SEEKER_IN_PROGRESS;
	
	@ClientString(id = 21303, message = "Trial of the Seeker (Done)")
	public static NpcStringId TRIAL_OF_THE_SEEKER_DONE;
	
	@ClientString(id = 21401, message = "Trial of the Scholar")
	public static NpcStringId TRIAL_OF_THE_SCHOLAR;
	
	@ClientString(id = 21402, message = "Trial of the Scholar (In Progress)")
	public static NpcStringId TRIAL_OF_THE_SCHOLAR_IN_PROGRESS;
	
	@ClientString(id = 21403, message = "Trial of the Scholar (Done)")
	public static NpcStringId TRIAL_OF_THE_SCHOLAR_DONE;
	
	@ClientString(id = 21501, message = "Trial of the Pilgrim")
	public static NpcStringId TRIAL_OF_THE_PILGRIM;
	
	@ClientString(id = 21502, message = "Trial of the Pilgrim (In Progress)")
	public static NpcStringId TRIAL_OF_THE_PILGRIM_IN_PROGRESS;
	
	@ClientString(id = 21503, message = "Trial of the Pilgrim (Done)")
	public static NpcStringId TRIAL_OF_THE_PILGRIM_DONE;
	
	@ClientString(id = 21601, message = "Trial of the Guildsman")
	public static NpcStringId TRIAL_OF_THE_GUILDSMAN;
	
	@ClientString(id = 21602, message = "Trial of the Guildsman (In Progress)")
	public static NpcStringId TRIAL_OF_THE_GUILDSMAN_IN_PROGRESS;
	
	@ClientString(id = 21603, message = "Trial of the Guildsman (Done)")
	public static NpcStringId TRIAL_OF_THE_GUILDSMAN_DONE;
	
	@ClientString(id = 21701, message = "Testimony of Trust")
	public static NpcStringId TESTIMONY_OF_TRUST;
	
	@ClientString(id = 21702, message = "Testimony of Trust (In Progress)")
	public static NpcStringId TESTIMONY_OF_TRUST_IN_PROGRESS;
	
	@ClientString(id = 21703, message = "Testimony of Trust (Done)")
	public static NpcStringId TESTIMONY_OF_TRUST_DONE;
	
	@ClientString(id = 21801, message = "Testimony of Life")
	public static NpcStringId TESTIMONY_OF_LIFE;
	
	@ClientString(id = 21802, message = "Testimony of Life (In Progress)")
	public static NpcStringId TESTIMONY_OF_LIFE_IN_PROGRESS;
	
	@ClientString(id = 21803, message = "Testimony of Life (Done)")
	public static NpcStringId TESTIMONY_OF_LIFE_DONE;
	
	@ClientString(id = 21901, message = "Testimony of Fate")
	public static NpcStringId TESTIMONY_OF_FATE;
	
	@ClientString(id = 21902, message = "Testimony of Fate (In Progress)")
	public static NpcStringId TESTIMONY_OF_FATE_IN_PROGRESS;
	
	@ClientString(id = 21903, message = "Testimony of Fate (Done)")
	public static NpcStringId TESTIMONY_OF_FATE_DONE;
	
	@ClientString(id = 22001, message = "Testimony of Glory")
	public static NpcStringId TESTIMONY_OF_GLORY;
	
	@ClientString(id = 22002, message = "Testimony of Glory (In Progress)")
	public static NpcStringId TESTIMONY_OF_GLORY_IN_PROGRESS;
	
	@ClientString(id = 22003, message = "Testimony of Glory (Done)")
	public static NpcStringId TESTIMONY_OF_GLORY_DONE;
	
	@ClientString(id = 22051, message = "Is it a lackey of Kakai?!")
	public static NpcStringId IS_IT_A_LACKEY_OF_KAKAI;
	
	@ClientString(id = 22052, message = "Too late!")
	public static NpcStringId TOO_LATE;
	
	@ClientString(id = 22053, message = "Is it a lackey of Kakai?!")
	public static NpcStringId IS_IT_A_LACKEY_OF_KAKAI_2;
	
	@ClientString(id = 22054, message = "Too late!")
	public static NpcStringId TOO_LATE_2;
	
	@ClientString(id = 22055, message = "How regretful! Unjust dishonor!")
	public static NpcStringId HOW_REGRETFUL_UNJUST_DISHONOR;
	
	@ClientString(id = 22056, message = "I'll get revenge someday!!")
	public static NpcStringId I_LL_GET_REVENGE_SOMEDAY;
	
	@ClientString(id = 22057, message = "Indignant and unfair death!")
	public static NpcStringId INDIGNANT_AND_UNFAIR_DEATH;
	
	@ClientString(id = 22101, message = "Testimony of Prosperity")
	public static NpcStringId TESTIMONY_OF_PROSPERITY;
	
	@ClientString(id = 22102, message = "Testimony of Prosperity (In Progress)")
	public static NpcStringId TESTIMONY_OF_PROSPERITY_IN_PROGRESS;
	
	@ClientString(id = 22103, message = "Testimony of Prosperity (Done)")
	public static NpcStringId TESTIMONY_OF_PROSPERITY_DONE;
	
	@ClientString(id = 22201, message = "Test of the Duelist")
	public static NpcStringId TEST_OF_THE_DUELIST;
	
	@ClientString(id = 22202, message = "Test of the Duelist (In Progress)")
	public static NpcStringId TEST_OF_THE_DUELIST_IN_PROGRESS;
	
	@ClientString(id = 22203, message = "Test of the Duelist (Done)")
	public static NpcStringId TEST_OF_THE_DUELIST_DONE;
	
	@ClientString(id = 22301, message = "Test of the Champion")
	public static NpcStringId TEST_OF_THE_CHAMPION;
	
	@ClientString(id = 22302, message = "Test of the Champion (In Progress)")
	public static NpcStringId TEST_OF_THE_CHAMPION_IN_PROGRESS;
	
	@ClientString(id = 22303, message = "Test of the Champion (Done)")
	public static NpcStringId TEST_OF_THE_CHAMPION_DONE;
	
	@ClientString(id = 22401, message = "Test of Sagittarius")
	public static NpcStringId TEST_OF_SAGITTARIUS;
	
	@ClientString(id = 22402, message = "Test of Sagittarius (In Progress)")
	public static NpcStringId TEST_OF_SAGITTARIUS_IN_PROGRESS;
	
	@ClientString(id = 22403, message = "Test of Sagittarius (Done)")
	public static NpcStringId TEST_OF_SAGITTARIUS_DONE;
	
	@ClientString(id = 22501, message = "Test of the Searcher")
	public static NpcStringId TEST_OF_THE_SEARCHER;
	
	@ClientString(id = 22502, message = "Test of the Searcher (In Progress)")
	public static NpcStringId TEST_OF_THE_SEARCHER_IN_PROGRESS;
	
	@ClientString(id = 22503, message = "Test of the Searcher (Done)")
	public static NpcStringId TEST_OF_THE_SEARCHER_DONE;
	
	@ClientString(id = 22601, message = "Test of the Healer")
	public static NpcStringId TEST_OF_THE_HEALER;
	
	@ClientString(id = 22602, message = "Test of the Healer (In Progress)")
	public static NpcStringId TEST_OF_THE_HEALER_IN_PROGRESS;
	
	@ClientString(id = 22603, message = "Test of the Healer (Done)")
	public static NpcStringId TEST_OF_THE_HEALER_DONE;
	
	@ClientString(id = 22701, message = "Test of the Reformer")
	public static NpcStringId TEST_OF_THE_REFORMER;
	
	@ClientString(id = 22702, message = "Test of the Reformer (In Progress)")
	public static NpcStringId TEST_OF_THE_REFORMER_IN_PROGRESS;
	
	@ClientString(id = 22703, message = "Test of the Reformer (Done)")
	public static NpcStringId TEST_OF_THE_REFORMER_DONE;
	
	@ClientString(id = 22719, message = "The concealed truth will always be revealed...!")
	public static NpcStringId THE_CONCEALED_TRUTH_WILL_ALWAYS_BE_REVEALED;
	
	@ClientString(id = 22720, message = "Cowardly guy!")
	public static NpcStringId COWARDLY_GUY;
	
	@ClientString(id = 22801, message = "Test of Magus")
	public static NpcStringId TEST_OF_MAGUS;
	
	@ClientString(id = 22802, message = "Test of Magus (In Progress)")
	public static NpcStringId TEST_OF_MAGUS_IN_PROGRESS;
	
	@ClientString(id = 22803, message = "Test of Magus (Done)")
	public static NpcStringId TEST_OF_MAGUS_DONE;
	
	@ClientString(id = 22819, message = "I am a tree of nothing... a tree... that knows where to return...")
	public static NpcStringId I_AM_A_TREE_OF_NOTHING_A_TREE_THAT_KNOWS_WHERE_TO_RETURN;
	
	@ClientString(id = 22820, message = "I am a creature that shows the truth of the place deep in my heart...")
	public static NpcStringId I_AM_A_CREATURE_THAT_SHOWS_THE_TRUTH_OF_THE_PLACE_DEEP_IN_MY_HEART;
	
	@ClientString(id = 22821, message = "I am a mirror of darkness... a virtual image of darkness...")
	public static NpcStringId I_AM_A_MIRROR_OF_DARKNESS_A_VIRTUAL_IMAGE_OF_DARKNESS;
	
	@ClientString(id = 22901, message = "Test of Witchcraft")
	public static NpcStringId TEST_OF_WITCHCRAFT;
	
	@ClientString(id = 22902, message = "Test of Witchcraft (In Progress)")
	public static NpcStringId TEST_OF_WITCHCRAFT_IN_PROGRESS;
	
	@ClientString(id = 22903, message = "Test of Witchcraft (Done)")
	public static NpcStringId TEST_OF_WITCHCRAFT_DONE;
	
	@ClientString(id = 22933, message = "I absolutely cannot give it to you! It is my precious jewel!")
	public static NpcStringId I_ABSOLUTELY_CANNOT_GIVE_IT_TO_YOU_IT_IS_MY_PRECIOUS_JEWEL;
	
	@ClientString(id = 22934, message = "I'll take your lives later!")
	public static NpcStringId I_LL_TAKE_YOUR_LIVES_LATER;
	
	@ClientString(id = 22935, message = "That sword is really...!")
	public static NpcStringId THAT_SWORD_IS_REALLY;
	
	@ClientString(id = 22936, message = "No! I haven't completely finished the command for destruction and slaughter yet!!!")
	public static NpcStringId NO_I_HAVEN_T_COMPLETELY_FINISHED_THE_COMMAND_FOR_DESTRUCTION_AND_SLAUGHTER_YET;
	
	@ClientString(id = 22937, message = "How dare you wake me! Now you shall die!")
	public static NpcStringId HOW_DARE_YOU_WAKE_ME_NOW_YOU_SHALL_DIE;
	
	@ClientString(id = 23001, message = "Test of the Summoner")
	public static NpcStringId TEST_OF_THE_SUMMONER;
	
	@ClientString(id = 23002, message = "Test of the Summoner (In Progress)")
	public static NpcStringId TEST_OF_THE_SUMMONER_IN_PROGRESS;
	
	@ClientString(id = 23003, message = "Test of the Summoner (Done)")
	public static NpcStringId TEST_OF_THE_SUMMONER_DONE;
	
	@ClientString(id = 23060, message = "START DUEL")
	public static NpcStringId START_DUEL;
	
	@ClientString(id = 23061, message = "RULE VIOLATION")
	public static NpcStringId RULE_VIOLATION;
	
	@ClientString(id = 23062, message = "I LOSE")
	public static NpcStringId I_LOSE;
	
	@ClientString(id = 23063, message = "Whhiisshh!")
	public static NpcStringId WHHIISSHH;
	
	@ClientString(id = 23064, message = "Rule violation!")
	public static NpcStringId RULE_VIOLATION_2;
	
	@ClientString(id = 23065, message = "I'm sorry, Lord!")
	public static NpcStringId I_M_SORRY_LORD;
	
	@ClientString(id = 23066, message = "Whish! Fight!")
	public static NpcStringId WHISH_FIGHT;
	
	@ClientString(id = 23067, message = "Rule violation!")
	public static NpcStringId RULE_VIOLATION_3;
	
	@ClientString(id = 23068, message = "Lost! Sorry, Lord!")
	public static NpcStringId LOST_SORRY_LORD;
	
	@ClientString(id = 23069, message = "START DUEL")
	public static NpcStringId START_DUEL_2;
	
	@ClientString(id = 23070, message = "RULE VIOLATION")
	public static NpcStringId RULE_VIOLATION_4;
	
	@ClientString(id = 23071, message = "I LOSE")
	public static NpcStringId I_LOSE_2;
	
	@ClientString(id = 23072, message = "So shall we start?!")
	public static NpcStringId SO_SHALL_WE_START;
	
	@ClientString(id = 23073, message = "Rule violation!!!")
	public static NpcStringId RULE_VIOLATION_5;
	
	@ClientString(id = 23074, message = "Ugh! I lost...!")
	public static NpcStringId UGH_I_LOST;
	
	@ClientString(id = 23075, message = "I'll walk all over you!")
	public static NpcStringId I_LL_WALK_ALL_OVER_YOU;
	
	@ClientString(id = 23076, message = "Rule violation!!!")
	public static NpcStringId RULE_VIOLATION_6;
	
	@ClientString(id = 23077, message = "Ugh! Can this be happening?!")
	public static NpcStringId UGH_CAN_THIS_BE_HAPPENING;
	
	@ClientString(id = 23078, message = "It's the natural result!")
	public static NpcStringId IT_S_THE_NATURAL_RESULT;
	
	@ClientString(id = 23079, message = "Ho, ho! I win!")
	public static NpcStringId HO_HO_I_WIN;
	
	@ClientString(id = 23080, message = "I WIN")
	public static NpcStringId I_WIN;
	
	@ClientString(id = 23081, message = "Whish! I won!")
	public static NpcStringId WHISH_I_WON;
	
	@ClientString(id = 23082, message = "Whhiisshh!")
	public static NpcStringId WHHIISSHH_2;
	
	@ClientString(id = 23083, message = "I WIN")
	public static NpcStringId I_WIN_2;
	
	@ClientString(id = 23101, message = "Test of the Maestro")
	public static NpcStringId TEST_OF_THE_MAESTRO;
	
	@ClientString(id = 23102, message = "Test of the Maestro (In Progress)")
	public static NpcStringId TEST_OF_THE_MAESTRO_IN_PROGRESS;
	
	@ClientString(id = 23103, message = "Test of the Maestro (Done)")
	public static NpcStringId TEST_OF_THE_MAESTRO_DONE;
	
	@ClientString(id = 23201, message = "Test of the Lord")
	public static NpcStringId TEST_OF_THE_LORD;
	
	@ClientString(id = 23202, message = "Test of the Lord (In Progress)")
	public static NpcStringId TEST_OF_THE_LORD_IN_PROGRESS;
	
	@ClientString(id = 23203, message = "Test of the Lord (Done)")
	public static NpcStringId TEST_OF_THE_LORD_DONE;
	
	@ClientString(id = 23301, message = "Test of the War Spirit")
	public static NpcStringId TEST_OF_THE_WAR_SPIRIT;
	
	@ClientString(id = 23302, message = "Test of the War Spirit (In Progress)")
	public static NpcStringId TEST_OF_THE_WAR_SPIRIT_IN_PROGRESS;
	
	@ClientString(id = 23303, message = "Test of the War Spirit (Done)")
	public static NpcStringId TEST_OF_THE_WAR_SPIRIT_DONE;
	
	@ClientString(id = 23401, message = "Fate's Whisper")
	public static NpcStringId FATE_S_WHISPER;
	
	@ClientString(id = 23402, message = "Fate's Whisper (In Progress)")
	public static NpcStringId FATE_S_WHISPER_IN_PROGRESS;
	
	@ClientString(id = 23403, message = "Fate's Whisper (Done)")
	public static NpcStringId FATE_S_WHISPER_DONE;
	
	@ClientString(id = 23434, message = "Who dares to try and steal my noble blood?")
	public static NpcStringId WHO_DARES_TO_TRY_AND_STEAL_MY_NOBLE_BLOOD;
	
	@ClientString(id = 23501, message = "Mimir's Elixir")
	public static NpcStringId MIMIR_S_ELIXIR;
	
	@ClientString(id = 23502, message = "Mimir's Elixir (In Progress)")
	public static NpcStringId MIMIR_S_ELIXIR_IN_PROGRESS;
	
	@ClientString(id = 23503, message = "Mimir's Elixir (Done)")
	public static NpcStringId MIMIR_S_ELIXIR_DONE;
	
	@ClientString(id = 23601, message = "Seeds of Chaos")
	public static NpcStringId SEEDS_OF_CHAOS;
	
	@ClientString(id = 23602, message = "Seeds of Chaos (In Progress)")
	public static NpcStringId SEEDS_OF_CHAOS_IN_PROGRESS;
	
	@ClientString(id = 23603, message = "Seeds of Chaos (Done)")
	public static NpcStringId SEEDS_OF_CHAOS_DONE;
	
	@ClientString(id = 23651, message = "$s1! Finally, we meet!")
	public static NpcStringId S1_FINALLY_WE_MEET;
	
	@ClientString(id = 23652, message = "Hmm, where did my friend go?")
	public static NpcStringId HMM_WHERE_DID_MY_FRIEND_GO;
	
	@ClientString(id = 23653, message = "Best of luck with your future endeavours.")
	public static NpcStringId BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS;
	
	@ClientString(id = 23661, message = "$s1! Did you wait for long?")
	public static NpcStringId S1_DID_YOU_WAIT_FOR_LONG;
	
	@ClientString(id = 23662, message = "Hmm, where did my friend go?")
	public static NpcStringId HMM_WHERE_DID_MY_FRIEND_GO_2;
	
	@ClientString(id = 23663, message = "Best of luck with your future endeavours.")
	public static NpcStringId BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS_2;
	
	@ClientString(id = 23671, message = "Did you bring what I asked, $s1?")
	public static NpcStringId DID_YOU_BRING_WHAT_I_ASKED_S1;
	
	@ClientString(id = 23672, message = "Hmm, where did my friend go?")
	public static NpcStringId HMM_WHERE_DID_MY_FRIEND_GO_3;
	
	@ClientString(id = 23673, message = "Best of luck with your future endeavours.")
	public static NpcStringId BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS_3;
	
	@ClientString(id = 23681, message = "Hmm? Is someone approaching?")
	public static NpcStringId HMM_IS_SOMEONE_APPROACHING;
	
	@ClientString(id = 23682, message = "Graaah, we're being attacked!")
	public static NpcStringId GRAAAH_WE_RE_BEING_ATTACKED;
	
	@ClientString(id = 23683, message = "In that case, I wish you good luck.")
	public static NpcStringId IN_THAT_CASE_I_WISH_YOU_GOOD_LUCK;
	
	@ClientString(id = 23685, message = "$s1, has everything been found?")
	public static NpcStringId S1_HAS_EVERYTHING_BEEN_FOUND;
	
	@ClientString(id = 23686, message = "Graaah, we're being attacked!")
	public static NpcStringId GRAAAH_WE_RE_BEING_ATTACKED_2;
	
	@ClientString(id = 23687, message = "Safe travels!")
	public static NpcStringId SAFE_TRAVELS;
	
	@ClientString(id = 23701, message = "Winds of Change")
	public static NpcStringId WINDS_OF_CHANGE;
	
	@ClientString(id = 23702, message = "Winds of Change (In Progress)")
	public static NpcStringId WINDS_OF_CHANGE_IN_PROGRESS;
	
	@ClientString(id = 23703, message = "Winds of Change (Done)")
	public static NpcStringId WINDS_OF_CHANGE_DONE;
	
	@ClientString(id = 23801, message = "Success/Failure of Business")
	public static NpcStringId SUCCESS_FAILURE_OF_BUSINESS;
	
	@ClientString(id = 23802, message = "Success/Failure of Business (In Progress)")
	public static NpcStringId SUCCESS_FAILURE_OF_BUSINESS_IN_PROGRESS;
	
	@ClientString(id = 23803, message = "Success/Failure of Business (Done)")
	public static NpcStringId SUCCESS_FAILURE_OF_BUSINESS_DONE;
	
	@ClientString(id = 23901, message = "Won't You Join Us?")
	public static NpcStringId WON_T_YOU_JOIN_US;
	
	@ClientString(id = 23902, message = "Won't You Join Us? (In Progress)")
	public static NpcStringId WON_T_YOU_JOIN_US_IN_PROGRESS;
	
	@ClientString(id = 23903, message = "Won't You Join Us? (Done)")
	public static NpcStringId WON_T_YOU_JOIN_US_DONE;
	
	@ClientString(id = 24001, message = "I'm the Only One You Can Trust")
	public static NpcStringId I_M_THE_ONLY_ONE_YOU_CAN_TRUST;
	
	@ClientString(id = 24002, message = "I'm the Only One You Can Trust (In Progress)")
	public static NpcStringId I_M_THE_ONLY_ONE_YOU_CAN_TRUST_IN_PROGRESS;
	
	@ClientString(id = 24003, message = "I'm the Only One You Can Trust (Done)")
	public static NpcStringId I_M_THE_ONLY_ONE_YOU_CAN_TRUST_DONE;
	
	@ClientString(id = 24101, message = "Path of the Noblesse, Precious Soul - 1")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_1;
	
	@ClientString(id = 24102, message = "Path of the Noblesse, Precious Soul - 1 (In Progress)")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_1_IN_PROGRESS;
	
	@ClientString(id = 24103, message = "Path of the Noblesse, Precious Soul - 1 (Done)")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_1_DONE;
	
	@ClientString(id = 24201, message = "Path of the Noblesse, Precious Soul - 2")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_2;
	
	@ClientString(id = 24202, message = "Path of the Noblesse, Precious Soul - 2 (In Progress)")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_2_IN_PROGRESS;
	
	@ClientString(id = 24203, message = "Path of the Noblesse, Precious Soul - 2 (Done)")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_2_DONE;
	
	@ClientString(id = 24601, message = "Path of the Noblesse, Precious Soul - 3")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_3;
	
	@ClientString(id = 24602, message = "Path of the Noblesse, Precious Soul - 3 (In Progress)")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_3_IN_PROGRESS;
	
	@ClientString(id = 24603, message = "Path of the Noblesse, Precious Soul - 3 (Done)")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_3_DONE;
	
	@ClientString(id = 24701, message = "Path of the Noblesse, Precious Soul - 4")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_4;
	
	@ClientString(id = 24702, message = "Path of the Noblesse, Precious Soul - 4 (In Progress)")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_4_IN_PROGRESS;
	
	@ClientString(id = 24703, message = "Path of the Noblesse, Precious Soul - 4 (Done)")
	public static NpcStringId PATH_OF_THE_NOBLESSE_PRECIOUS_SOUL_4_DONE;
	
	@ClientString(id = 24901, message = "Poisoned Plains of the Lizardmen")
	public static NpcStringId POISONED_PLAINS_OF_THE_LIZARDMEN;
	
	@ClientString(id = 24902, message = "Poisoned Plains of the Lizardmen (In Progress)")
	public static NpcStringId POISONED_PLAINS_OF_THE_LIZARDMEN_IN_PROGRESS;
	
	@ClientString(id = 24903, message = "Poisoned Plains of the Lizardmen (Done)")
	public static NpcStringId POISONED_PLAINS_OF_THE_LIZARDMEN_DONE;
	
	@ClientString(id = 25001, message = "Watch What You Eat")
	public static NpcStringId WATCH_WHAT_YOU_EAT;
	
	@ClientString(id = 25002, message = "Watch What You Eat (In Progress)")
	public static NpcStringId WATCH_WHAT_YOU_EAT_IN_PROGRESS;
	
	@ClientString(id = 25003, message = "Watch What You Eat (Done)")
	public static NpcStringId WATCH_WHAT_YOU_EAT_DONE;
	
	@ClientString(id = 25101, message = "No Secrets")
	public static NpcStringId NO_SECRETS;
	
	@ClientString(id = 25102, message = "No Secrets (In Progress)")
	public static NpcStringId NO_SECRETS_IN_PROGRESS;
	
	@ClientString(id = 25103, message = "No Secrets (Done)")
	public static NpcStringId NO_SECRETS_DONE;
	
	@ClientString(id = 25201, message = "It Smells Delicious!")
	public static NpcStringId IT_SMELLS_DELICIOUS;
	
	@ClientString(id = 25202, message = "It Smells Delicious! (In Progress)")
	public static NpcStringId IT_SMELLS_DELICIOUS_IN_PROGRESS;
	
	@ClientString(id = 25203, message = "It Smells Delicious! (Done)")
	public static NpcStringId IT_SMELLS_DELICIOUS_DONE;
	
	@ClientString(id = 25401, message = "Legendary Tales")
	public static NpcStringId LEGENDARY_TALES;
	
	@ClientString(id = 25402, message = "Legendary Tales (In Progress)")
	public static NpcStringId LEGENDARY_TALES_IN_PROGRESS;
	
	@ClientString(id = 25403, message = "Legendary Tales (Done)")
	public static NpcStringId LEGENDARY_TALES_DONE;
	
	@ClientString(id = 25701, message = "The Guard is Busy")
	public static NpcStringId THE_GUARD_IS_BUSY;
	
	@ClientString(id = 25702, message = "The Guard is Busy (In Progress)")
	public static NpcStringId THE_GUARD_IS_BUSY_IN_PROGRESS;
	
	@ClientString(id = 25703, message = "The Guard is Busy (Done)")
	public static NpcStringId THE_GUARD_IS_BUSY_DONE;
	
	@ClientString(id = 25801, message = "Bring Wolf Pelts")
	public static NpcStringId BRING_WOLF_PELTS;
	
	@ClientString(id = 25802, message = "Bring Wolf Pelts (In Progress)")
	public static NpcStringId BRING_WOLF_PELTS_IN_PROGRESS;
	
	@ClientString(id = 25901, message = "Request from the Farm Owner ")
	public static NpcStringId REQUEST_FROM_THE_FARM_OWNER;
	
	@ClientString(id = 25902, message = "Request from the Farm Owner (In Progress)")
	public static NpcStringId REQUEST_FROM_THE_FARM_OWNER_IN_PROGRESS;
	
	@ClientString(id = 26001, message = "Orc Hunting")
	public static NpcStringId ORC_HUNTING;
	
	@ClientString(id = 26002, message = "Orc Hunting (In Progress)")
	public static NpcStringId ORC_HUNTING_IN_PROGRESS;
	
	@ClientString(id = 26101, message = "Collector's Dream")
	public static NpcStringId COLLECTOR_S_DREAM;
	
	@ClientString(id = 26102, message = "Collector's Dream (In Progress)")
	public static NpcStringId COLLECTOR_S_DREAM_IN_PROGRESS;
	
	@ClientString(id = 26201, message = "Trade with the Ivory Tower")
	public static NpcStringId TRADE_WITH_THE_IVORY_TOWER;
	
	@ClientString(id = 26202, message = "Trade with the Ivory Tower (In Progress)")
	public static NpcStringId TRADE_WITH_THE_IVORY_TOWER_IN_PROGRESS;
	
	@ClientString(id = 26301, message = "Orc Subjugation")
	public static NpcStringId ORC_SUBJUGATION;
	
	@ClientString(id = 26302, message = "Orc Subjugation (In Progress)")
	public static NpcStringId ORC_SUBJUGATION_IN_PROGRESS;
	
	@ClientString(id = 26401, message = "Keen Claws")
	public static NpcStringId KEEN_CLAWS;
	
	@ClientString(id = 26402, message = "Keen Claws (In Progress)")
	public static NpcStringId KEEN_CLAWS_IN_PROGRESS;
	
	@ClientString(id = 26501, message = "Bonds of Slavery")
	public static NpcStringId BONDS_OF_SLAVERY;
	
	@ClientString(id = 26502, message = "Bonds of Slavery (In Progress)")
	public static NpcStringId BONDS_OF_SLAVERY_IN_PROGRESS;
	
	@ClientString(id = 26601, message = "Pleas of Pixies")
	public static NpcStringId PLEAS_OF_PIXIES;
	
	@ClientString(id = 26602, message = "Pleas of Pixies (In Progress)")
	public static NpcStringId PLEAS_OF_PIXIES_IN_PROGRESS;
	
	@ClientString(id = 26701, message = "Wrath of Verdure")
	public static NpcStringId WRATH_OF_VERDURE;
	
	@ClientString(id = 26702, message = "Wrath of Verdure (In Progress)")
	public static NpcStringId WRATH_OF_VERDURE_IN_PROGRESS;
	
	@ClientString(id = 26801, message = "Traces of Evil")
	public static NpcStringId TRACES_OF_EVIL;
	
	@ClientString(id = 26802, message = "Traces of Evil (In Progress)")
	public static NpcStringId TRACES_OF_EVIL_IN_PROGRESS;
	
	@ClientString(id = 26901, message = "Invention Ambition")
	public static NpcStringId INVENTION_AMBITION;
	
	@ClientString(id = 26902, message = "Invention Ambition (In Progress)")
	public static NpcStringId INVENTION_AMBITION_IN_PROGRESS;
	
	@ClientString(id = 27001, message = "The One Who Ends Silence")
	public static NpcStringId THE_ONE_WHO_ENDS_SILENCE;
	
	@ClientString(id = 27002, message = "The One Who Ends Silence (In Progress)")
	public static NpcStringId THE_ONE_WHO_ENDS_SILENCE_IN_PROGRESS;
	
	@ClientString(id = 27101, message = "Proof of Valor")
	public static NpcStringId PROOF_OF_VALOR;
	
	@ClientString(id = 27102, message = "Proof of Valor (In Progress)")
	public static NpcStringId PROOF_OF_VALOR_IN_PROGRESS;
	
	@ClientString(id = 27201, message = "Wrath of Ancestors")
	public static NpcStringId WRATH_OF_ANCESTORS;
	
	@ClientString(id = 27202, message = "Wrath of Ancestors (In Progress)")
	public static NpcStringId WRATH_OF_ANCESTORS_IN_PROGRESS;
	
	@ClientString(id = 27301, message = "Invaders of the Holy Land")
	public static NpcStringId INVADERS_OF_THE_HOLY_LAND;
	
	@ClientString(id = 27302, message = "Invaders of the Holy Land (In Progress)")
	public static NpcStringId INVADERS_OF_THE_HOLY_LAND_IN_PROGRESS;
	
	@ClientString(id = 27401, message = "Skirmish with the Werewolves")
	public static NpcStringId SKIRMISH_WITH_THE_WEREWOLVES;
	
	@ClientString(id = 27402, message = "Skirmish with the Werewolves (In Progress)")
	public static NpcStringId SKIRMISH_WITH_THE_WEREWOLVES_IN_PROGRESS;
	
	@ClientString(id = 27501, message = "Dark Winged Spies")
	public static NpcStringId DARK_WINGED_SPIES;
	
	@ClientString(id = 27502, message = "Dark Winged Spies (In Progress)")
	public static NpcStringId DARK_WINGED_SPIES_IN_PROGRESS;
	
	@ClientString(id = 27601, message = "Totem of the Hestui")
	public static NpcStringId TOTEM_OF_THE_HESTUI;
	
	@ClientString(id = 27602, message = "Totem of the Hestui (In Progress)")
	public static NpcStringId TOTEM_OF_THE_HESTUI_IN_PROGRESS;
	
	@ClientString(id = 27701, message = "Gatekeeper's Offering")
	public static NpcStringId GATEKEEPER_S_OFFERING;
	
	@ClientString(id = 27702, message = "Gatekeeper's Offering (In Progress)")
	public static NpcStringId GATEKEEPER_S_OFFERING_IN_PROGRESS;
	
	@ClientString(id = 27801, message = "Home Security")
	public static NpcStringId HOME_SECURITY;
	
	@ClientString(id = 27802, message = "Home Security (In Progress)")
	public static NpcStringId HOME_SECURITY_IN_PROGRESS;
	
	@ClientString(id = 27901, message = "Target of Opportunity")
	public static NpcStringId TARGET_OF_OPPORTUNITY;
	
	@ClientString(id = 27902, message = "Target of Opportunity (In Progress)")
	public static NpcStringId TARGET_OF_OPPORTUNITY_IN_PROGRESS;
	
	@ClientString(id = 28001, message = "The Food Chain")
	public static NpcStringId THE_FOOD_CHAIN;
	
	@ClientString(id = 28002, message = "The Food Chain (In Progress)")
	public static NpcStringId THE_FOOD_CHAIN_IN_PROGRESS;
	
	@ClientString(id = 28101, message = "Head for the Hills!")
	public static NpcStringId HEAD_FOR_THE_HILLS;
	
	@ClientString(id = 28102, message = "Head for the Hills! (In Progress)")
	public static NpcStringId HEAD_FOR_THE_HILLS_IN_PROGRESS;
	
	@ClientString(id = 28301, message = "The Few, The Proud, The Brave")
	public static NpcStringId THE_FEW_THE_PROUD_THE_BRAVE;
	
	@ClientString(id = 28302, message = "The Few, The Proud, The Brave (In Progress)")
	public static NpcStringId THE_FEW_THE_PROUD_THE_BRAVE_IN_PROGRESS;
	
	@ClientString(id = 28401, message = "Muertos Hunting")
	public static NpcStringId MUERTOS_HUNTING;
	
	@ClientString(id = 28402, message = "Muertos Hunting (In Progress)")
	public static NpcStringId MUERTOS_HUNTING_IN_PROGRESS;
	
	@ClientString(id = 28601, message = "Fabulous Feathers")
	public static NpcStringId FABULOUS_FEATHERS;
	
	@ClientString(id = 28602, message = "Fabulous Feathers (In Progress)")
	public static NpcStringId FABULOUS_FEATHERS_IN_PROGRESS;
	
	@ClientString(id = 28701, message = "Figuring It Out!")
	public static NpcStringId FIGURING_IT_OUT;
	
	@ClientString(id = 28702, message = "Figuring It Out! (In Progress)")
	public static NpcStringId FIGURING_IT_OUT_IN_PROGRESS;
	
	@ClientString(id = 28801, message = "Handle With Care")
	public static NpcStringId HANDLE_WITH_CARE;
	
	@ClientString(id = 28802, message = "Handle With Care (In Progress)")
	public static NpcStringId HANDLE_WITH_CARE_IN_PROGRESS;
	
	@ClientString(id = 28901, message = "No More Soup For You")
	public static NpcStringId NO_MORE_SOUP_FOR_YOU;
	
	@ClientString(id = 28902, message = "No More Soup For You (In Progress)")
	public static NpcStringId NO_MORE_SOUP_FOR_YOU_IN_PROGRESS;
	
	@ClientString(id = 29001, message = "Threat Removal")
	public static NpcStringId THREAT_REMOVAL;
	
	@ClientString(id = 29002, message = "Threat Removal (In Progress)")
	public static NpcStringId THREAT_REMOVAL_IN_PROGRESS;
	
	@ClientString(id = 29101, message = "Revenge of the Redbonnet")
	public static NpcStringId REVENGE_OF_THE_REDBONNET;
	
	@ClientString(id = 29102, message = "Revenge of the Redbonnet (In Progress)")
	public static NpcStringId REVENGE_OF_THE_REDBONNET_IN_PROGRESS;
	
	@ClientString(id = 29201, message = "Brigands Sweep")
	public static NpcStringId BRIGANDS_SWEEP;
	
	@ClientString(id = 29202, message = "Brigands Sweep (In Progress)")
	public static NpcStringId BRIGANDS_SWEEP_IN_PROGRESS;
	
	@ClientString(id = 29301, message = "The Hidden Veins")
	public static NpcStringId THE_HIDDEN_VEINS;
	
	@ClientString(id = 29302, message = "The Hidden Veins (In Progress)")
	public static NpcStringId THE_HIDDEN_VEINS_IN_PROGRESS;
	
	@ClientString(id = 29401, message = "Covert Business")
	public static NpcStringId COVERT_BUSINESS;
	
	@ClientString(id = 29402, message = "Covert Business (In Progress)")
	public static NpcStringId COVERT_BUSINESS_IN_PROGRESS;
	
	@ClientString(id = 29501, message = "Dreaming of the Skies")
	public static NpcStringId DREAMING_OF_THE_SKIES;
	
	@ClientString(id = 29502, message = "Dreaming of the Skies (In Progress)")
	public static NpcStringId DREAMING_OF_THE_SKIES_IN_PROGRESS;
	
	@ClientString(id = 29601, message = "Tarantula's Spider Silk")
	public static NpcStringId TARANTULA_S_SPIDER_SILK;
	
	@ClientString(id = 29602, message = "Tarantula's Spider Silk (In Progress)")
	public static NpcStringId TARANTULA_S_SPIDER_SILK_IN_PROGRESS;
	
	@ClientString(id = 29701, message = "Gatekeeper's Favor")
	public static NpcStringId GATEKEEPER_S_FAVOR;
	
	@ClientString(id = 29702, message = "Gatekeeper's Favor (In Progress)")
	public static NpcStringId GATEKEEPER_S_FAVOR_IN_PROGRESS;
	
	@ClientString(id = 29801, message = "Lizardmen's Conspiracy")
	public static NpcStringId LIZARDMEN_S_CONSPIRACY;
	
	@ClientString(id = 29802, message = "Lizardmen's Conspiracy (In Progress)")
	public static NpcStringId LIZARDMEN_S_CONSPIRACY_IN_PROGRESS;
	
	@ClientString(id = 29901, message = "Gather Ingredients for Pie")
	public static NpcStringId GATHER_INGREDIENTS_FOR_PIE;
	
	@ClientString(id = 29902, message = "Gather Ingredients for Pie (In Progress)")
	public static NpcStringId GATHER_INGREDIENTS_FOR_PIE_IN_PROGRESS;
	
	@ClientString(id = 30001, message = "Leto Lizardmen Hunting")
	public static NpcStringId LETO_LIZARDMEN_HUNTING;
	
	@ClientString(id = 30002, message = "Leto Lizardmen Hunting (In Progress)")
	public static NpcStringId LETO_LIZARDMEN_HUNTING_IN_PROGRESS;
	
	@ClientString(id = 30301, message = "Collect Arrowheads")
	public static NpcStringId COLLECT_ARROWHEADS;
	
	@ClientString(id = 30302, message = "Collect Arrowheads (In Progress)")
	public static NpcStringId COLLECT_ARROWHEADS_IN_PROGRESS;
	
	@ClientString(id = 30601, message = "Crystals of Fire and Ice")
	public static NpcStringId CRYSTALS_OF_FIRE_AND_ICE;
	
	@ClientString(id = 30602, message = "Crystals of Fire and Ice (In Progress)")
	public static NpcStringId CRYSTALS_OF_FIRE_AND_ICE_IN_PROGRESS;
	
	@ClientString(id = 30701, message = "Control Device of the Giants")
	public static NpcStringId CONTROL_DEVICE_OF_THE_GIANTS;
	
	@ClientString(id = 30702, message = "Control Device of the Giants (In Progress)")
	public static NpcStringId CONTROL_DEVICE_OF_THE_GIANTS_IN_PROGRESS;
	
	@ClientString(id = 30801, message = "Reed Field Maintenance")
	public static NpcStringId REED_FIELD_MAINTENANCE;
	
	@ClientString(id = 30802, message = "Reed Field Maintenance (In Progress)")
	public static NpcStringId REED_FIELD_MAINTENANCE_IN_PROGRESS;
	
	@ClientString(id = 30901, message = "For a Good Cause")
	public static NpcStringId FOR_A_GOOD_CAUSE;
	
	@ClientString(id = 30902, message = "For a Good Cause (In Progress)")
	public static NpcStringId FOR_A_GOOD_CAUSE_IN_PROGRESS;
	
	@ClientString(id = 31001, message = "Only What Remains")
	public static NpcStringId ONLY_WHAT_REMAINS;
	
	@ClientString(id = 31002, message = "Only What Remains (In Progress)")
	public static NpcStringId ONLY_WHAT_REMAINS_IN_PROGRESS;
	
	@ClientString(id = 31101, message = "Expulsion of Evil Spirits")
	public static NpcStringId EXPULSION_OF_EVIL_SPIRITS;
	
	@ClientString(id = 31102, message = "Expulsion of Evil Spirits (In Progress)")
	public static NpcStringId EXPULSION_OF_EVIL_SPIRITS_IN_PROGRESS;
	
	@ClientString(id = 31201, message = "Take Advantage of the Crisis!")
	public static NpcStringId TAKE_ADVANTAGE_OF_THE_CRISIS;
	
	@ClientString(id = 31202, message = "Take Advantage of the Crisis! (In Progress)")
	public static NpcStringId TAKE_ADVANTAGE_OF_THE_CRISIS_IN_PROGRESS;
	
	@ClientString(id = 31301, message = "Collect Spores")
	public static NpcStringId COLLECT_SPORES;
	
	@ClientString(id = 31302, message = "Collect Spores (In Progress)")
	public static NpcStringId COLLECT_SPORES_IN_PROGRESS;
	
	@ClientString(id = 31601, message = "Destroy Plague Carriers")
	public static NpcStringId DESTROY_PLAGUE_CARRIERS;
	
	@ClientString(id = 31602, message = "Destroy Plague Carriers (In Progress)")
	public static NpcStringId DESTROY_PLAGUE_CARRIERS_IN_PROGRESS;
	
	@ClientString(id = 31603, message = "Why do you oppress us so?")
	public static NpcStringId WHY_DO_YOU_OPPRESS_US_SO;
	
	@ClientString(id = 31701, message = "Catch the Wind")
	public static NpcStringId CATCH_THE_WIND;
	
	@ClientString(id = 31702, message = "Catch the Wind (In Progress)")
	public static NpcStringId CATCH_THE_WIND_IN_PROGRESS;
	
	@ClientString(id = 31901, message = "Scent of Death")
	public static NpcStringId SCENT_OF_DEATH;
	
	@ClientString(id = 31902, message = "Scent of Death (In Progress)")
	public static NpcStringId SCENT_OF_DEATH_IN_PROGRESS;
	
	@ClientString(id = 32001, message = "Bones Tell the Future")
	public static NpcStringId BONES_TELL_THE_FUTURE;
	
	@ClientString(id = 32002, message = "Bones Tell the Future (In Progress)")
	public static NpcStringId BONES_TELL_THE_FUTURE_IN_PROGRESS;
	
	@ClientString(id = 32401, message = "Sweetest Venom")
	public static NpcStringId SWEETEST_VENOM;
	
	@ClientString(id = 32402, message = "Sweetest Venom (In Progress)")
	public static NpcStringId SWEETEST_VENOM_IN_PROGRESS;
	
	@ClientString(id = 32501, message = "Grim Collector")
	public static NpcStringId GRIM_COLLECTOR;
	
	@ClientString(id = 32502, message = "Grim Collector (In Progress)")
	public static NpcStringId GRIM_COLLECTOR_IN_PROGRESS;
	
	@ClientString(id = 32601, message = "Vanquish Remnants")
	public static NpcStringId VANQUISH_REMNANTS;
	
	@ClientString(id = 32602, message = "Vanquish Remnants (In Progress)")
	public static NpcStringId VANQUISH_REMNANTS_IN_PROGRESS;
	
	@ClientString(id = 32701, message = "Recover the Farmland")
	public static NpcStringId RECOVER_THE_FARMLAND;
	
	@ClientString(id = 32702, message = "Recover the Farmland (In Progress)")
	public static NpcStringId RECOVER_THE_FARMLAND_IN_PROGRESS;
	
	@ClientString(id = 32801, message = "Sense for Business")
	public static NpcStringId SENSE_FOR_BUSINESS;
	
	@ClientString(id = 32802, message = "Sense for Business (In Progress)")
	public static NpcStringId SENSE_FOR_BUSINESS_IN_PROGRESS;
	
	@ClientString(id = 32901, message = "Curiosity of a Dwarf")
	public static NpcStringId CURIOSITY_OF_A_DWARF;
	
	@ClientString(id = 32902, message = "Curiosity of a Dwarf (In Progress)")
	public static NpcStringId CURIOSITY_OF_A_DWARF_IN_PROGRESS;
	
	@ClientString(id = 33001, message = "Adept of Taste")
	public static NpcStringId ADEPT_OF_TASTE;
	
	@ClientString(id = 33002, message = "Adept of Taste (In Progress)")
	public static NpcStringId ADEPT_OF_TASTE_IN_PROGRESS;
	
	@ClientString(id = 33101, message = "Arrow of Vengeance")
	public static NpcStringId ARROW_OF_VENGEANCE;
	
	@ClientString(id = 33102, message = "Arrow of Vengeance (In Progress)")
	public static NpcStringId ARROW_OF_VENGEANCE_IN_PROGRESS;
	
	@ClientString(id = 33301, message = "Hunt of the Black Lion")
	public static NpcStringId HUNT_OF_THE_BLACK_LION;
	
	@ClientString(id = 33302, message = "Hunt of the Black Lion (In Progress)")
	public static NpcStringId HUNT_OF_THE_BLACK_LION_IN_PROGRESS;
	
	@ClientString(id = 33401, message = "The Wishing Potion")
	public static NpcStringId THE_WISHING_POTION;
	
	@ClientString(id = 33402, message = "The Wishing Potion (In Progress)")
	public static NpcStringId THE_WISHING_POTION_IN_PROGRESS;
	
	@ClientString(id = 33403, message = "The Wishing Potion (Done)")
	public static NpcStringId THE_WISHING_POTION_DONE;
	
	@ClientString(id = 33409, message = "Don't interrupt my rest again")
	public static NpcStringId DON_T_INTERRUPT_MY_REST_AGAIN;
	
	@ClientString(id = 33410, message = "You're a great devil now...")
	public static NpcStringId YOU_RE_A_GREAT_DEVIL_NOW;
	
	@ClientString(id = 33411, message = "Oh, it's not an opponent of mine. Ha, ha, ha!")
	public static NpcStringId OH_IT_S_NOT_AN_OPPONENT_OF_MINE_HA_HA_HA;
	
	@ClientString(id = 33412, message = "Oh... Great Demon King...")
	public static NpcStringId OH_GREAT_DEMON_KING;
	
	@ClientString(id = 33413, message = "Revenge is Overlord Ramsebalius of the evil world!")
	public static NpcStringId REVENGE_IS_OVERLORD_RAMSEBALIUS_OF_THE_EVIL_WORLD;
	
	@ClientString(id = 33414, message = "Bonaparterius, Abyss King, will punish you")
	public static NpcStringId BONAPARTERIUS_ABYSS_KING_WILL_PUNISH_YOU;
	
	@ClientString(id = 33415, message = "OK, everybody pray fervently!")
	public static NpcStringId OK_EVERYBODY_PRAY_FERVENTLY;
	
	@ClientString(id = 33416, message = "Both hands to heaven! Everybody yell together!")
	public static NpcStringId BOTH_HANDS_TO_HEAVEN_EVERYBODY_YELL_TOGETHER;
	
	@ClientString(id = 33417, message = "One! Two! May your dreams come true!")
	public static NpcStringId ONE_TWO_MAY_YOUR_DREAMS_COME_TRUE;
	
	@ClientString(id = 33418, message = "Who killed my underling devil?")
	public static NpcStringId WHO_KILLED_MY_UNDERLING_DEVIL;
	
	@ClientString(id = 33420, message = "I will make your love come true~ love, love, love~")
	public static NpcStringId I_WILL_MAKE_YOUR_LOVE_COME_TRUE_LOVE_LOVE_LOVE;
	
	@ClientString(id = 33421, message = "I have wisdom in me. I am the box of wisdom!")
	public static NpcStringId I_HAVE_WISDOM_IN_ME_I_AM_THE_BOX_OF_WISDOM;
	
	@ClientString(id = 33422, message = "Oh, oh, oh!")
	public static NpcStringId OH_OH_OH;
	
	@ClientString(id = 33423, message = "Do you want us to love you? Oh.")
	public static NpcStringId DO_YOU_WANT_US_TO_LOVE_YOU_OH;
	
	@ClientString(id = 33424, message = "Who is calling the Lord of Darkness!")
	public static NpcStringId WHO_IS_CALLING_THE_LORD_OF_DARKNESS;
	
	@ClientString(id = 33425, message = "I am a great empire, Bonaparterius!")
	public static NpcStringId I_AM_A_GREAT_EMPIRE_BONAPARTERIUS;
	
	@ClientString(id = 33426, message = "Let your head down before the Lord!")
	public static NpcStringId LET_YOUR_HEAD_DOWN_BEFORE_THE_LORD;
	
	@ClientString(id = 33501, message = "The Song of the Hunter")
	public static NpcStringId THE_SONG_OF_THE_HUNTER;
	
	@ClientString(id = 33502, message = "The Song of the Hunter (In Progress)")
	public static NpcStringId THE_SONG_OF_THE_HUNTER_IN_PROGRESS;
	
	@ClientString(id = 33511, message = "We'll take the property of the ancient empire!")
	public static NpcStringId WE_LL_TAKE_THE_PROPERTY_OF_THE_ANCIENT_EMPIRE;
	
	@ClientString(id = 33512, message = "Show me the pretty sparkling things! They're all mine!")
	public static NpcStringId SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEY_RE_ALL_MINE;
	
	@ClientString(id = 33513, message = "Pretty good!")
	public static NpcStringId PRETTY_GOOD;
	
	@ClientString(id = 33520, message = "<a action='bypass -h menu_select?ask=335&reply=5'>C: 40 Totems of Kadesh</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_5_C_40_TOTEMS_OF_KADESH_A_BR;
	
	@ClientString(id = 33521, message = "<a action='bypass -h menu_select?ask=335&reply=6'>C: 50 Jade Necklaces of Timak</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_6_C_50_JADE_NECKLACES_OF_TIMAK_A_BR;
	
	@ClientString(id = 33522, message = "<a action='bypass -h menu_select?ask=335&reply=7'>C: 50 Enchanted Golem Shards</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_7_C_50_ENCHANTED_GOLEM_SHARDS_A_BR;
	
	@ClientString(id = 33523, message = "<a action='bypass -h menu_select?ask=335&reply=8'>C: 30 Pieces Monster Eye Meat</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_8_C_30_PIECES_MONSTER_EYE_MEAT_A_BR;
	
	@ClientString(id = 33524, message = "<a action='bypass -h menu_select?ask=335&reply=9'>C: 40 Eggs of Dire Wyrm</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_9_C_40_EGGS_OF_DIRE_WYRM_A_BR;
	
	@ClientString(id = 33525, message = "<a action='bypass -h menu_select?ask=335&reply=10'>C: 100 Claws of Guardian Basilisk</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_10_C_100_CLAWS_OF_GUARDIAN_BASILISK_A_BR;
	
	@ClientString(id = 33526, message = "<a action='bypass -h menu_select?ask=335&reply=11'>C: 50 Revenant Chains </a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_11_C_50_REVENANT_CHAINS_A_BR;
	
	@ClientString(id = 33527, message = "<a action='bypass -h menu_select?ask=335&reply=12'>C: 30 Windsus Tusks</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_12_C_30_WINDSUS_TUSKS_A_BR;
	
	@ClientString(id = 33528, message = "<a action='bypass -h menu_select?ask=335&reply=13'>C: 100 Skulls of Grandis</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_13_C_100_SKULLS_OF_GRANDIS_A_BR;
	
	@ClientString(id = 33529, message = "<a action='bypass -h menu_select?ask=335&reply=14'>C: 50 Taik Obsidian Amulets</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_14_C_50_TAIK_OBSIDIAN_AMULETS_A_BR;
	
	@ClientString(id = 33530, message = "<a action='bypass -h menu_select?ask=335&reply=15'>C: 30 Heads of Karul Bugbear</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_15_C_30_HEADS_OF_KARUL_BUGBEAR_A_BR;
	
	@ClientString(id = 33531, message = "<a action='bypass -h menu_select?ask=335&reply=16'>C: 40 Ivory Charms of Tamlin</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_16_C_40_IVORY_CHARMS_OF_TAMLIN_A_BR;
	
	@ClientString(id = 33532, message = "<a action='bypass -h menu_select?ask=335&reply=17'>B: Situation Preparation - Leto Chief</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_17_B_SITUATION_PREPARATION_LETO_CHIEF_A_BR;
	
	@ClientString(id = 33533, message = "<a action='bypass -h menu_select?ask=335&reply=18'>B: 50 Enchanted Gargoyle Horns</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_18_B_50_ENCHANTED_GARGOYLE_HORNS_A_BR;
	
	@ClientString(id = 33534, message = "<a action='bypass -h menu_select?ask=335&reply=19'>B: 50 Coiled Serpent Totems</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_19_B_50_COILED_SERPENT_TOTEMS_A_BR;
	
	@ClientString(id = 33535, message = "<a action='bypass -h menu_select?ask=335&reply=20'>B: Situation Preparation - Sorcerer Catch of Leto</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_20_B_SITUATION_PREPARATION_SORCERER_CATCH_OF_LETO_A_BR;
	
	@ClientString(id = 33536, message = "<a action='bypass -h menu_select?ask=335&reply=21'>B: Situation Preparation - Timak Raider Kaikee</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_21_B_SITUATION_PREPARATION_TIMAK_RAIDER_KAIKEE_A_BR;
	
	@ClientString(id = 33537, message = "<a action='bypass -h menu_select?ask=335&reply=22'>B: 30 Kronbe Venom Sacs</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_22_B_30_KRONBE_VENOM_SACS_A_BR;
	
	@ClientString(id = 33538, message = "<a action='bypass -h menu_select?ask=335&reply=23'>A: 30 Charms of Eva</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_23_A_30_CHARMS_OF_EVA_A_BR;
	
	@ClientString(id = 33539, message = "<a action='bypass -h menu_select?ask=335&reply=24'>A: Titan's Tablet</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_24_A_TITAN_S_TABLET_A_BR;
	
	@ClientString(id = 33540, message = "<a action='bypass -h menu_select?ask=335&reply=25'>A: Book of Shunaiman</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_25_A_BOOK_OF_SHUNAIMAN_A_BR;
	
	@ClientString(id = 33541, message = "<a action='bypass -h menu_select?ask=335&reply=26'>C: 40 Rotted Tree Spores</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_26_C_40_ROTTED_TREE_SPORES_A_BR;
	
	@ClientString(id = 33542, message = "<a action='bypass -h menu_select?ask=335&reply=27'>C: 40 Trisalim Venom Sacs</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_27_C_40_TRISALIM_VENOM_SACS_A_BR;
	
	@ClientString(id = 33543, message = "<a action='bypass -h menu_select?ask=335&reply=28'>C: 50 Totems of Taik Orc</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_28_C_50_TOTEMS_OF_TAIK_ORC_A_BR;
	
	@ClientString(id = 33544, message = "<a action='bypass -h menu_select?ask=335&reply=29'>C: 40 Harit Barbed Necklaces</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_29_C_40_HARIT_BARBED_NECKLACES_A_BR;
	
	@ClientString(id = 33545, message = "<a action='bypass -h menu_select?ask=335&reply=30'>C: 20 Coins of Ancient Empire</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_30_C_20_COINS_OF_ANCIENT_EMPIRE_A_BR;
	
	@ClientString(id = 33546, message = "<a action='bypass -h menu_select?ask=335&reply=31'>C: 30 Skins of Farkran</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_31_C_30_SKINS_OF_FARKRAN_A_BR;
	
	@ClientString(id = 33547, message = "<a action='bypass -h menu_select?ask=335&reply=32'>C: 40 Tempest Shards</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_32_C_40_TEMPEST_SHARDS_A_BR;
	
	@ClientString(id = 33548, message = "<a action='bypass -h menu_select?ask=335&reply=36'>C: 30 Vanor Silenos Manes</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_36_C_30_VANOR_SILENOS_MANES_A_BR;
	
	@ClientString(id = 33549, message = "<a action='bypass -h menu_select?ask=335&reply=34'>C: 40 Manes of Pan Ruem</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_34_C_40_MANES_OF_PAN_RUEM_A_BR;
	
	@ClientString(id = 33550, message = "<a action='bypass -h menu_select?ask=335&reply=35'>C: hamadryad shards</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_35_C_HAMADRYAD_SHARDS_A_BR;
	
	@ClientString(id = 33551, message = "<a action='bypass -h menu_select?ask=335&reply=36'>C: 30 Manes of Vanor Silenos</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_36_C_30_MANES_OF_VANOR_SILENOS_A_BR;
	
	@ClientString(id = 33552, message = "<a action='bypass -h menu_select?ask=335&reply=37'>C: 30 Totems of Talk Bugbears</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_37_C_30_TOTEMS_OF_TALK_BUGBEARS_A_BR;
	
	@ClientString(id = 33553, message = "<a action='bypass -h menu_select?ask=335&reply=38'>B: Situation Preparation - Overlord Okun of Timak</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_38_B_SITUATION_PREPARATION_OVERLORD_OKUN_OF_TIMAK_A_BR;
	
	@ClientString(id = 33554, message = "<a action='bypass -h menu_select?ask=335&reply=39'>B: Situation Preparation - Overlord Kakran of Taik</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_39_B_SITUATION_PREPARATION_OVERLORD_KAKRAN_OF_TAIK_A_BR;
	
	@ClientString(id = 33555, message = "<a action='bypass -h menu_select?ask=335&reply=40'>B: 40 Narcissus Soulstones</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_40_B_40_NARCISSUS_SOULSTONES_A_BR;
	
	@ClientString(id = 33556, message = "<a action='bypass -h menu_select?ask=335&reply=41'>B: 20 Eyes of Deprived</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_41_B_20_EYES_OF_DEPRIVED_A_BR;
	
	@ClientString(id = 33557, message = "<a action='bypass -h menu_select?ask=335&reply=42'>B: 20 Unicorn Horns</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_42_B_20_UNICORN_HORNS_A_BR;
	
	@ClientString(id = 33558, message = "<a action='bypass -h menu_select?ask=335&reply=43'>B: Kerunos's Gold Mane</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_335_REPLY_43_B_KERUNOS_S_GOLD_MANE_A_BR;
	
	@ClientString(id = 33601, message = "Coins of Magic")
	public static NpcStringId COINS_OF_MAGIC;
	
	@ClientString(id = 33602, message = "Coins of Magic (In Progress)")
	public static NpcStringId COINS_OF_MAGIC_IN_PROGRESS;
	
	@ClientString(id = 33701, message = "Audience with the Land Dragon ")
	public static NpcStringId AUDIENCE_WITH_THE_LAND_DRAGON;
	
	@ClientString(id = 33702, message = "Audience with the Land Dragon (In Progress)")
	public static NpcStringId AUDIENCE_WITH_THE_LAND_DRAGON_IN_PROGRESS;
	
	@ClientString(id = 33801, message = "Alligator Hunter")
	public static NpcStringId ALLIGATOR_HUNTER;
	
	@ClientString(id = 33802, message = "Alligator Hunter (In Progress)")
	public static NpcStringId ALLIGATOR_HUNTER_IN_PROGRESS;
	
	@ClientString(id = 34001, message = "Subjugation of Lizardmen")
	public static NpcStringId SUBJUGATION_OF_LIZARDMEN;
	
	@ClientString(id = 34002, message = "Subjugation of Lizardmen (In Progress)")
	public static NpcStringId SUBJUGATION_OF_LIZARDMEN_IN_PROGRESS;
	
	@ClientString(id = 34101, message = "Hunting for Wild Beasts")
	public static NpcStringId HUNTING_FOR_WILD_BEASTS;
	
	@ClientString(id = 34102, message = "Hunting for Wild Beasts (In Progress)")
	public static NpcStringId HUNTING_FOR_WILD_BEASTS_IN_PROGRESS;
	
	@ClientString(id = 34301, message = "Under the Shadow of the Ivory Tower")
	public static NpcStringId UNDER_THE_SHADOW_OF_THE_IVORY_TOWER;
	
	@ClientString(id = 34302, message = "Under the Shadow of the Ivory Tower (In Progress)")
	public static NpcStringId UNDER_THE_SHADOW_OF_THE_IVORY_TOWER_IN_PROGRESS;
	
	@ClientString(id = 34401, message = "1000 Years, the End of Lamentation")
	public static NpcStringId A_HUNDRED0_YEARS_THE_END_OF_LAMENTATION;
	
	@ClientString(id = 34402, message = "1000 Years, the End of Lamentation (In Progress)")
	public static NpcStringId A_HUNDRED0_YEARS_THE_END_OF_LAMENTATION_IN_PROGRESS;
	
	@ClientString(id = 34501, message = "Method to Raise the Dead")
	public static NpcStringId METHOD_TO_RAISE_THE_DEAD;
	
	@ClientString(id = 34502, message = "Method to Raise the Dead (In Progress)")
	public static NpcStringId METHOD_TO_RAISE_THE_DEAD_IN_PROGRESS;
	
	@ClientString(id = 34701, message = "Go Get the Calculator")
	public static NpcStringId GO_GET_THE_CALCULATOR;
	
	@ClientString(id = 34702, message = "Go Get the Calculator (In Progress)")
	public static NpcStringId GO_GET_THE_CALCULATOR_IN_PROGRESS;
	
	@ClientString(id = 34801, message = "An Arrogant Search")
	public static NpcStringId AN_ARROGANT_SEARCH;
	
	@ClientString(id = 34802, message = "An Arrogant Search (In Progress)")
	public static NpcStringId AN_ARROGANT_SEARCH_IN_PROGRESS;
	
	@ClientString(id = 34830, message = "Ha, that was fun! If you wish to find the key, search the corpse.")
	public static NpcStringId HA_THAT_WAS_FUN_IF_YOU_WISH_TO_FIND_THE_KEY_SEARCH_THE_CORPSE;
	
	@ClientString(id = 34831, message = "I have the key. Why don't you come and take it?")
	public static NpcStringId I_HAVE_THE_KEY_WHY_DON_T_YOU_COME_AND_TAKE_IT;
	
	@ClientString(id = 34832, message = "You fools will get what's coming to you!")
	public static NpcStringId YOU_FOOLS_WILL_GET_WHAT_S_COMING_TO_YOU;
	
	@ClientString(id = 34833, message = "Sorry about this, but I must kill you now.")
	public static NpcStringId SORRY_ABOUT_THIS_BUT_I_MUST_KILL_YOU_NOW;
	
	@ClientString(id = 34834, message = "I have the key. Why don't you come and take it?")
	public static NpcStringId I_HAVE_THE_KEY_WHY_DON_T_YOU_COME_AND_TAKE_IT_2;
	
	@ClientString(id = 34835, message = "You guys wouldn't know... the seven seals are... Arrrgh!")
	public static NpcStringId YOU_GUYS_WOULDN_T_KNOW_THE_SEVEN_SEALS_ARE_ARRRGH;
	
	@ClientString(id = 34836, message = "I shall drench this mountain with your blood!")
	public static NpcStringId I_SHALL_DRENCH_THIS_MOUNTAIN_WITH_YOUR_BLOOD;
	
	@ClientString(id = 34837, message = "That doesn't belong to you. Don't touch it!")
	public static NpcStringId THAT_DOESN_T_BELONG_TO_YOU_DON_T_TOUCH_IT;
	
	@ClientString(id = 34838, message = "Get out of my sight, you infidels!")
	public static NpcStringId GET_OUT_OF_MY_SIGHT_YOU_INFIDELS;
	
	@ClientString(id = 34839, message = "We don't have any further business to discuss... Have you searched the corpse for the key?")
	public static NpcStringId WE_DON_T_HAVE_ANY_FURTHER_BUSINESS_TO_DISCUSS_HAVE_YOU_SEARCHED_THE_CORPSE_FOR_THE_KEY;
	
	@ClientString(id = 35001, message = "Enhance Your Weapon")
	public static NpcStringId ENHANCE_YOUR_WEAPON;
	
	@ClientString(id = 35002, message = "Enhance Your Weapon (In Progress)")
	public static NpcStringId ENHANCE_YOUR_WEAPON_IN_PROGRESS;
	
	@ClientString(id = 35051, message = "$s1 has earned a stage $s2 Blue Soul Crystal.")
	public static NpcStringId S1_HAS_EARNED_A_STAGE_S2_BLUE_SOUL_CRYSTAL;
	
	@ClientString(id = 35052, message = "$s1 has earned a stage $s2 Red Soul Crystal.")
	public static NpcStringId S1_HAS_EARNED_A_STAGE_S2_RED_SOUL_CRYSTAL;
	
	@ClientString(id = 35053, message = "$s1 has earned a stage $s2 Green Soul Crystal.")
	public static NpcStringId S1_HAS_EARNED_A_STAGE_S2_GREEN_SOUL_CRYSTAL;
	
	@ClientString(id = 35054, message = "$s1 has earned a Stage $s2 Blue Cursed Soul Crystal.")
	public static NpcStringId S1_HAS_EARNED_A_STAGE_S2_BLUE_CURSED_SOUL_CRYSTAL;
	
	@ClientString(id = 35055, message = "$s1 has earned a Stage $s2 Red Cursed Soul Crystal.")
	public static NpcStringId S1_HAS_EARNED_A_STAGE_S2_RED_CURSED_SOUL_CRYSTAL;
	
	@ClientString(id = 35056, message = "$s1 has earned a Stage $s2 Green Cursed Soul Crystal.")
	public static NpcStringId S1_HAS_EARNED_A_STAGE_S2_GREEN_CURSED_SOUL_CRYSTAL;
	
	@ClientString(id = 35101, message = "Black Swan")
	public static NpcStringId BLACK_SWAN;
	
	@ClientString(id = 35102, message = "Black Swan (In Progress)")
	public static NpcStringId BLACK_SWAN_IN_PROGRESS;
	
	@ClientString(id = 35201, message = "Help Rood Raise A New Pet!")
	public static NpcStringId HELP_ROOD_RAISE_A_NEW_PET;
	
	@ClientString(id = 35202, message = "Help Rood Raise A New Pet! (In Progress)")
	public static NpcStringId HELP_ROOD_RAISE_A_NEW_PET_IN_PROGRESS;
	
	@ClientString(id = 35301, message = "Power of Darkness")
	public static NpcStringId POWER_OF_DARKNESS;
	
	@ClientString(id = 35302, message = "Power of Darkness (In Progress)")
	public static NpcStringId POWER_OF_DARKNESS_IN_PROGRESS;
	
	@ClientString(id = 35401, message = "Conquest of Alligator Island")
	public static NpcStringId CONQUEST_OF_ALLIGATOR_ISLAND;
	
	@ClientString(id = 35402, message = "Conquest of Alligator Island (In Progress)")
	public static NpcStringId CONQUEST_OF_ALLIGATOR_ISLAND_IN_PROGRESS;
	
	@ClientString(id = 35501, message = "Family Honor")
	public static NpcStringId FAMILY_HONOR;
	
	@ClientString(id = 35502, message = "Family Honor (In Progress)")
	public static NpcStringId FAMILY_HONOR_IN_PROGRESS;
	
	@ClientString(id = 35601, message = "Dig Up the Sea of Spores!")
	public static NpcStringId DIG_UP_THE_SEA_OF_SPORES;
	
	@ClientString(id = 35602, message = "Dig Up the Sea of Spores! (In Progress)")
	public static NpcStringId DIG_UP_THE_SEA_OF_SPORES_IN_PROGRESS;
	
	@ClientString(id = 35701, message = "Warehouse Keeper's Ambition")
	public static NpcStringId WAREHOUSE_KEEPER_S_AMBITION;
	
	@ClientString(id = 35702, message = "Warehouse Keeper's Ambition (In Progress)")
	public static NpcStringId WAREHOUSE_KEEPER_S_AMBITION_IN_PROGRESS;
	
	@ClientString(id = 35801, message = "Illegitimate Child of a Goddess")
	public static NpcStringId ILLEGITIMATE_CHILD_OF_A_GODDESS;
	
	@ClientString(id = 35802, message = "Illegitimate Child of a Goddess (In Progress)")
	public static NpcStringId ILLEGITIMATE_CHILD_OF_A_GODDESS_IN_PROGRESS;
	
	@ClientString(id = 35901, message = "For a Sleepless Deadman")
	public static NpcStringId FOR_A_SLEEPLESS_DEADMAN;
	
	@ClientString(id = 35902, message = "For a Sleepless Deadman (In Progress)")
	public static NpcStringId FOR_A_SLEEPLESS_DEADMAN_IN_PROGRESS;
	
	@ClientString(id = 36001, message = "Plunder the Supplies")
	public static NpcStringId PLUNDER_THE_SUPPLIES;
	
	@ClientString(id = 36002, message = "Plunder the Supplies (In Progress)")
	public static NpcStringId PLUNDER_THE_SUPPLIES_IN_PROGRESS;
	
	@ClientString(id = 36201, message = "Bard's Mandolin")
	public static NpcStringId BARD_S_MANDOLIN;
	
	@ClientString(id = 36202, message = "Bard's Mandolin (In Progress)")
	public static NpcStringId BARD_S_MANDOLIN_IN_PROGRESS;
	
	@ClientString(id = 36301, message = "Sorrowful Sound of Flute")
	public static NpcStringId SORROWFUL_SOUND_OF_FLUTE;
	
	@ClientString(id = 36302, message = "Sorrowful Sound of Flute (In Progress)")
	public static NpcStringId SORROWFUL_SOUND_OF_FLUTE_IN_PROGRESS;
	
	@ClientString(id = 36401, message = "Jovial Accordion")
	public static NpcStringId JOVIAL_ACCORDION;
	
	@ClientString(id = 36402, message = "Jovial Accordion (In Progress)")
	public static NpcStringId JOVIAL_ACCORDION_IN_PROGRESS;
	
	@ClientString(id = 36501, message = "Demon's Legacy")
	public static NpcStringId DEMON_S_LEGACY;
	
	@ClientString(id = 36502, message = "Demon's Legacy (In Progress)")
	public static NpcStringId DEMON_S_LEGACY_IN_PROGRESS;
	
	@ClientString(id = 36601, message = "Silver Haired Shaman")
	public static NpcStringId SILVER_HAIRED_SHAMAN;
	
	@ClientString(id = 36602, message = "Silver Haired Shaman (In Progress)")
	public static NpcStringId SILVER_HAIRED_SHAMAN_IN_PROGRESS;
	
	@ClientString(id = 36701, message = "Electrifying Recharge!")
	public static NpcStringId ELECTRIFYING_RECHARGE;
	
	@ClientString(id = 36702, message = "Electrifying Recharge! (In Progress)")
	public static NpcStringId ELECTRIFYING_RECHARGE_IN_PROGRESS;
	
	@ClientString(id = 36801, message = "Trespassing into the Holy Ground")
	public static NpcStringId TRESPASSING_INTO_THE_HOLY_GROUND;
	
	@ClientString(id = 36802, message = "Trespassing into the Holy Ground (In Progress)")
	public static NpcStringId TRESPASSING_INTO_THE_HOLY_GROUND_IN_PROGRESS;
	
	@ClientString(id = 36901, message = "Collector of Jewels")
	public static NpcStringId COLLECTOR_OF_JEWELS;
	
	@ClientString(id = 36902, message = "Collector of Jewels (In Progress)")
	public static NpcStringId COLLECTOR_OF_JEWELS_IN_PROGRESS;
	
	@ClientString(id = 37001, message = "An Elder Sows Seeds")
	public static NpcStringId AN_ELDER_SOWS_SEEDS;
	
	@ClientString(id = 37002, message = "An Elder Sows Seeds (In Progress)")
	public static NpcStringId AN_ELDER_SOWS_SEEDS_IN_PROGRESS;
	
	@ClientString(id = 37101, message = "Shrieks of Ghosts")
	public static NpcStringId SHRIEKS_OF_GHOSTS;
	
	@ClientString(id = 37102, message = "Shrieks of Ghosts")
	public static NpcStringId SHRIEKS_OF_GHOSTS_2;
	
	@ClientString(id = 37201, message = "Legacy of Insolence")
	public static NpcStringId LEGACY_OF_INSOLENCE;
	
	@ClientString(id = 37202, message = "Legacy of Insolence (In Progress)")
	public static NpcStringId LEGACY_OF_INSOLENCE_IN_PROGRESS;
	
	@ClientString(id = 37301, message = "Supplier of Reagents")
	public static NpcStringId SUPPLIER_OF_REAGENTS;
	
	@ClientString(id = 37302, message = "Supplier of Reagents (In Progress)")
	public static NpcStringId SUPPLIER_OF_REAGENTS_IN_PROGRESS;
	
	@ClientString(id = 37401, message = "Whisper of Dreams - Part 1")
	public static NpcStringId WHISPER_OF_DREAMS_PART_1;
	
	@ClientString(id = 37402, message = "Whisper of Dreams - Part 1 (In Progress)")
	public static NpcStringId WHISPER_OF_DREAMS_PART_1_IN_PROGRESS;
	
	@ClientString(id = 37501, message = "Whisper of Dreams - Part 2")
	public static NpcStringId WHISPER_OF_DREAMS_PART_2;
	
	@ClientString(id = 37502, message = "Whisper of Dreams - Part 2 (In Progress)")
	public static NpcStringId WHISPER_OF_DREAMS_PART_2_IN_PROGRESS;
	
	@ClientString(id = 37601, message = "Exploration of the Giants' Cave - Part 1")
	public static NpcStringId EXPLORATION_OF_THE_GIANTS_CAVE_PART_1;
	
	@ClientString(id = 37602, message = "Exploration of the Giants' Cave - Part 1 (In Progress)")
	public static NpcStringId EXPLORATION_OF_THE_GIANTS_CAVE_PART_1_IN_PROGRESS;
	
	@ClientString(id = 37701, message = "Exploration of the Giants' Cave - Part 2")
	public static NpcStringId EXPLORATION_OF_THE_GIANTS_CAVE_PART_2;
	
	@ClientString(id = 37702, message = "Exploration of the Giants' Cave - Part 2 (In Progress)")
	public static NpcStringId EXPLORATION_OF_THE_GIANTS_CAVE_PART_2_IN_PROGRESS;
	
	@ClientString(id = 37801, message = "Grand Feast")
	public static NpcStringId GRAND_FEAST;
	
	@ClientString(id = 37802, message = "Grand Feast (In Progress)")
	public static NpcStringId GRAND_FEAST_IN_PROGRESS;
	
	@ClientString(id = 37901, message = "Fantasy Wine")
	public static NpcStringId FANTASY_WINE;
	
	@ClientString(id = 37902, message = "Fantasy Wine (In Progress)")
	public static NpcStringId FANTASY_WINE_IN_PROGRESS;
	
	@ClientString(id = 38001, message = "Bring Out the Flavor of Ingredients!")
	public static NpcStringId BRING_OUT_THE_FLAVOR_OF_INGREDIENTS;
	
	@ClientString(id = 38002, message = "Bring Out the Flavor of Ingredients! (In Progress)")
	public static NpcStringId BRING_OUT_THE_FLAVOR_OF_INGREDIENTS_IN_PROGRESS;
	
	@ClientString(id = 38101, message = "Let's Become a Royal Member!")
	public static NpcStringId LET_S_BECOME_A_ROYAL_MEMBER;
	
	@ClientString(id = 38102, message = "Let's Become a Royal Member! (In Progress)")
	public static NpcStringId LET_S_BECOME_A_ROYAL_MEMBER_IN_PROGRESS;
	
	@ClientString(id = 38201, message = "Kail's Magic Coin")
	public static NpcStringId KAIL_S_MAGIC_COIN;
	
	@ClientString(id = 38202, message = "Kail's Magic Coin (In Progress)")
	public static NpcStringId KAIL_S_MAGIC_COIN_IN_PROGRESS;
	
	@ClientString(id = 38301, message = "Treasure Hunt")
	public static NpcStringId TREASURE_HUNT;
	
	@ClientString(id = 38302, message = "Treasure Hunt (In Progress)")
	public static NpcStringId TREASURE_HUNT_IN_PROGRESS;
	
	@ClientString(id = 38401, message = "Warehouse Keeper's Pastime")
	public static NpcStringId WAREHOUSE_KEEPER_S_PASTIME;
	
	@ClientString(id = 38402, message = "Warehouse Keeper's Pastime (In Progress)")
	public static NpcStringId WAREHOUSE_KEEPER_S_PASTIME_IN_PROGRESS;
	
	@ClientString(id = 38451, message = "Slot $s1: $s2")
	public static NpcStringId SLOT_S1_S2;
	
	@ClientString(id = 38501, message = "Yoke of the Past")
	public static NpcStringId YOKE_OF_THE_PAST;
	
	@ClientString(id = 38502, message = "Yoke of the Past (In Progress)")
	public static NpcStringId YOKE_OF_THE_PAST_IN_PROGRESS;
	
	@ClientString(id = 38601, message = "Stolen Dignity")
	public static NpcStringId STOLEN_DIGNITY;
	
	@ClientString(id = 38602, message = "Stolen Dignity (In Progress)")
	public static NpcStringId STOLEN_DIGNITY_IN_PROGRESS;
	
	@ClientString(id = 40101, message = "Path of the Warrior")
	public static NpcStringId PATH_OF_THE_WARRIOR;
	
	@ClientString(id = 40102, message = "Path of the Warrior (In Progress)")
	public static NpcStringId PATH_OF_THE_WARRIOR_IN_PROGRESS;
	
	@ClientString(id = 40201, message = "Path of the Human Knight")
	public static NpcStringId PATH_OF_THE_HUMAN_KNIGHT;
	
	@ClientString(id = 40202, message = "Path of the Human Knight (In Progress)")
	public static NpcStringId PATH_OF_THE_HUMAN_KNIGHT_IN_PROGRESS;
	
	@ClientString(id = 40301, message = "Path of the Rogue")
	public static NpcStringId PATH_OF_THE_ROGUE;
	
	@ClientString(id = 40302, message = "Path of the Rogue (In Progress)")
	public static NpcStringId PATH_OF_THE_ROGUE_IN_PROGRESS;
	
	@ClientString(id = 40306, message = "You childish fool, do you think you can catch me?")
	public static NpcStringId YOU_CHILDISH_FOOL_DO_YOU_THINK_YOU_CAN_CATCH_ME;
	
	@ClientString(id = 40307, message = "I must do something about this shameful incident...")
	public static NpcStringId I_MUST_DO_SOMETHING_ABOUT_THIS_SHAMEFUL_INCIDENT;
	
	@ClientString(id = 40308, message = "What, do you dare to challenge me!")
	public static NpcStringId WHAT_DO_YOU_DARE_TO_CHALLENGE_ME;
	
	@ClientString(id = 40309, message = "The red-eyed thieves will revenge!")
	public static NpcStringId THE_RED_EYED_THIEVES_WILL_REVENGE;
	
	@ClientString(id = 40310, message = "Go ahead, you child!")
	public static NpcStringId GO_AHEAD_YOU_CHILD;
	
	@ClientString(id = 40311, message = "My friends are sure to revenge!")
	public static NpcStringId MY_FRIENDS_ARE_SURE_TO_REVENGE;
	
	@ClientString(id = 40312, message = "You ruthless fool, I will show you what real fighting is all about!")
	public static NpcStringId YOU_RUTHLESS_FOOL_I_WILL_SHOW_YOU_WHAT_REAL_FIGHTING_IS_ALL_ABOUT;
	
	@ClientString(id = 40313, message = "Ahh, how can it end like this... it is not fair!")
	public static NpcStringId AHH_HOW_CAN_IT_END_LIKE_THIS_IT_IS_NOT_FAIR;
	
	@ClientString(id = 40401, message = "Path of the Human Wizard")
	public static NpcStringId PATH_OF_THE_HUMAN_WIZARD;
	
	@ClientString(id = 40402, message = "Path of the Human Wizard (In Progress)")
	public static NpcStringId PATH_OF_THE_HUMAN_WIZARD_IN_PROGRESS;
	
	@ClientString(id = 40501, message = "Path of the Cleric")
	public static NpcStringId PATH_OF_THE_CLERIC;
	
	@ClientString(id = 40502, message = "Path of the Cleric (In Progress)")
	public static NpcStringId PATH_OF_THE_CLERIC_IN_PROGRESS;
	
	@ClientString(id = 40601, message = "Path of the Elven Knight")
	public static NpcStringId PATH_OF_THE_ELVEN_KNIGHT;
	
	@ClientString(id = 40602, message = "Path of the Elven Knight (In Progress)")
	public static NpcStringId PATH_OF_THE_ELVEN_KNIGHT_IN_PROGRESS;
	
	@ClientString(id = 40701, message = "Path of the Elven Scout")
	public static NpcStringId PATH_OF_THE_ELVEN_SCOUT;
	
	@ClientString(id = 40702, message = "Path of the Elven Scout (In Progress)")
	public static NpcStringId PATH_OF_THE_ELVEN_SCOUT_IN_PROGRESS;
	
	@ClientString(id = 40801, message = "Path of the Elven Wizard")
	public static NpcStringId PATH_OF_THE_ELVEN_WIZARD;
	
	@ClientString(id = 40802, message = "Path of the Elven Wizard (In Progress)")
	public static NpcStringId PATH_OF_THE_ELVEN_WIZARD_IN_PROGRESS;
	
	@ClientString(id = 40901, message = "Path of the Elven Oracle")
	public static NpcStringId PATH_OF_THE_ELVEN_ORACLE;
	
	@ClientString(id = 40902, message = "Path of the Elven Oracle (In Progress)")
	public static NpcStringId PATH_OF_THE_ELVEN_ORACLE_IN_PROGRESS;
	
	@ClientString(id = 40909, message = "The sacred flame is ours!")
	public static NpcStringId THE_SACRED_FLAME_IS_OURS;
	
	@ClientString(id = 40910, message = "Arrghh...we shall never.. surrender... ")
	public static NpcStringId ARRGHH_WE_SHALL_NEVER_SURRENDER;
	
	@ClientString(id = 40911, message = "The sacred flame is ours")
	public static NpcStringId THE_SACRED_FLAME_IS_OURS_2;
	
	@ClientString(id = 40912, message = "The sacred flame is ours")
	public static NpcStringId THE_SACRED_FLAME_IS_OURS_3;
	
	@ClientString(id = 40913, message = "As you wish, master!")
	public static NpcStringId AS_YOU_WISH_MASTER;
	
	@ClientString(id = 41001, message = "Path of the Palus Knight")
	public static NpcStringId PATH_OF_THE_PALUS_KNIGHT;
	
	@ClientString(id = 41002, message = "Path of the Palus Knight (In Progress)")
	public static NpcStringId PATH_OF_THE_PALUS_KNIGHT_IN_PROGRESS;
	
	@ClientString(id = 41101, message = "Path of the Assassin")
	public static NpcStringId PATH_OF_THE_ASSASSIN;
	
	@ClientString(id = 41102, message = "Path of the Assassin (In Progress)")
	public static NpcStringId PATH_OF_THE_ASSASSIN_IN_PROGRESS;
	
	@ClientString(id = 41201, message = "Path of the Dark Wizard")
	public static NpcStringId PATH_OF_THE_DARK_WIZARD;
	
	@ClientString(id = 41202, message = "Path of the Dark Wizard (In Progress)")
	public static NpcStringId PATH_OF_THE_DARK_WIZARD_IN_PROGRESS;
	
	@ClientString(id = 41301, message = "Path of the Shillien Oracle")
	public static NpcStringId PATH_OF_THE_SHILLIEN_ORACLE;
	
	@ClientString(id = 41302, message = "Path of the Shillien Oracle (In Progress)")
	public static NpcStringId PATH_OF_THE_SHILLIEN_ORACLE_IN_PROGRESS;
	
	@ClientString(id = 41401, message = "Path of the Orc Raider")
	public static NpcStringId PATH_OF_THE_ORC_RAIDER;
	
	@ClientString(id = 41402, message = "Path of the Orc Raider (In Progress)")
	public static NpcStringId PATH_OF_THE_ORC_RAIDER_IN_PROGRESS;
	
	@ClientString(id = 41501, message = "Path of the Orc Monk")
	public static NpcStringId PATH_OF_THE_ORC_MONK;
	
	@ClientString(id = 41502, message = "Path of the Orc Monk (In Progress)")
	public static NpcStringId PATH_OF_THE_ORC_MONK_IN_PROGRESS;
	
	@ClientString(id = 41601, message = "Path of the Orc Shaman")
	public static NpcStringId PATH_OF_THE_ORC_SHAMAN;
	
	@ClientString(id = 41602, message = "Path of the Orc Shaman (In Progress)")
	public static NpcStringId PATH_OF_THE_ORC_SHAMAN_IN_PROGRESS;
	
	@ClientString(id = 41651, message = "My dear friend of $s1, who has gone on ahead of me!")
	public static NpcStringId MY_DEAR_FRIEND_OF_S1_WHO_HAS_GONE_ON_AHEAD_OF_ME;
	
	@ClientString(id = 41652, message = "Listen to Tejakar Gandi, young Oroka! The spirit of the slain leopard is calling you, $s1!")
	public static NpcStringId LISTEN_TO_TEJAKAR_GANDI_YOUNG_OROKA_THE_SPIRIT_OF_THE_SLAIN_LEOPARD_IS_CALLING_YOU_S1;
	
	@ClientString(id = 41701, message = "Path of the Scavenger")
	public static NpcStringId PATH_OF_THE_SCAVENGER;
	
	@ClientString(id = 41702, message = "Path of the Scavenger (In Progress)")
	public static NpcStringId PATH_OF_THE_SCAVENGER_IN_PROGRESS;
	
	@ClientString(id = 41801, message = "Path of the Artisan")
	public static NpcStringId PATH_OF_THE_ARTISAN;
	
	@ClientString(id = 41802, message = "Path of the Artisan (In Progress)")
	public static NpcStringId PATH_OF_THE_ARTISAN_IN_PROGRESS;
	
	@ClientString(id = 41901, message = "Get a Pet")
	public static NpcStringId GET_A_PET;
	
	@ClientString(id = 41902, message = "Get a Pet (In Progress)")
	public static NpcStringId GET_A_PET_IN_PROGRESS;
	
	@ClientString(id = 42001, message = "Little Wing")
	public static NpcStringId LITTLE_WING;
	
	@ClientString(id = 42002, message = "Little Wing (In Progress)")
	public static NpcStringId LITTLE_WING_IN_PROGRESS;
	
	@ClientString(id = 42046, message = "Hey! Everybody watch the eggs!")
	public static NpcStringId HEY_EVERYBODY_WATCH_THE_EGGS;
	
	@ClientString(id = 42047, message = "I thought I'd caught one share... Whew!")
	public static NpcStringId I_THOUGHT_I_D_CAUGHT_ONE_SHARE_WHEW;
	
	@ClientString(id = 42048, message = "The stone... the Elven stone... broke...")
	public static NpcStringId THE_STONE_THE_ELVEN_STONE_BROKE;
	
	@ClientString(id = 42049, message = "If the eggs get taken, we're dead!")
	public static NpcStringId IF_THE_EGGS_GET_TAKEN_WE_RE_DEAD;
	
	@ClientString(id = 42101, message = "Little Wing's Big Adventure")
	public static NpcStringId LITTLE_WING_S_BIG_ADVENTURE;
	
	@ClientString(id = 42102, message = "Little Wing's Big Adventure (In Progress)")
	public static NpcStringId LITTLE_WING_S_BIG_ADVENTURE_IN_PROGRESS;
	
	@ClientString(id = 42111, message = "Give me a Fairy Leaf...!")
	public static NpcStringId GIVE_ME_A_FAIRY_LEAF;
	
	@ClientString(id = 42112, message = "Why do you bother me again?")
	public static NpcStringId WHY_DO_YOU_BOTHER_ME_AGAIN;
	
	@ClientString(id = 42113, message = "Hey, you've already drunk the essence of wind!")
	public static NpcStringId HEY_YOU_VE_ALREADY_DRUNK_THE_ESSENCE_OF_WIND;
	
	@ClientString(id = 42114, message = "Leave now, before you incur the wrath of the guardian ghost...")
	public static NpcStringId LEAVE_NOW_BEFORE_YOU_INCUR_THE_WRATH_OF_THE_GUARDIAN_GHOST;
	
	@ClientString(id = 42115, message = "Hey, you've already drunk the essence of a star!")
	public static NpcStringId HEY_YOU_VE_ALREADY_DRUNK_THE_ESSENCE_OF_A_STAR;
	
	@ClientString(id = 42116, message = "Hey, you've already drunk the essence of dusk!")
	public static NpcStringId HEY_YOU_VE_ALREADY_DRUNK_THE_ESSENCE_OF_DUSK;
	
	@ClientString(id = 42117, message = "Hey, you've already drunk the essence of the abyss!")
	public static NpcStringId HEY_YOU_VE_ALREADY_DRUNK_THE_ESSENCE_OF_THE_ABYSS;
	
	@ClientString(id = 42118, message = "We must protect the fairy tree!")
	public static NpcStringId WE_MUST_PROTECT_THE_FAIRY_TREE;
	
	@ClientString(id = 42119, message = "Get out of the sacred tree, you scoundrels!")
	public static NpcStringId GET_OUT_OF_THE_SACRED_TREE_YOU_SCOUNDRELS;
	
	@ClientString(id = 42120, message = "Death to the thieves of the pure water of the world!")
	public static NpcStringId DEATH_TO_THE_THIEVES_OF_THE_PURE_WATER_OF_THE_WORLD;
	
	@ClientString(id = 42201, message = "Repent Your Sins")
	public static NpcStringId REPENT_YOUR_SINS;
	
	@ClientString(id = 42202, message = "Repent Your Sins (In Progress)")
	public static NpcStringId REPENT_YOUR_SINS_IN_PROGRESS;
	
	@ClientString(id = 42231, message = "Hey, it seems like you need my help, doesn't it?")
	public static NpcStringId HEY_IT_SEEMS_LIKE_YOU_NEED_MY_HELP_DOESN_T_IT;
	
	@ClientString(id = 42232, message = "Almost got it... Ouch! Stop! Damn these bloody manacles!")
	public static NpcStringId ALMOST_GOT_IT_OUCH_STOP_DAMN_THESE_BLOODY_MANACLES;
	
	@ClientString(id = 42233, message = "Oh, that smarts!")
	public static NpcStringId OH_THAT_SMARTS;
	
	@ClientString(id = 42234, message = "Hey, master! Pay attention! I'm dying over here!")
	public static NpcStringId HEY_MASTER_PAY_ATTENTION_I_M_DYING_OVER_HERE;
	
	@ClientString(id = 42235, message = "What have I done to deserve this?")
	public static NpcStringId WHAT_HAVE_I_DONE_TO_DESERVE_THIS;
	
	@ClientString(id = 42236, message = "Oh, this is just great! What are you going to do now?")
	public static NpcStringId OH_THIS_IS_JUST_GREAT_WHAT_ARE_YOU_GOING_TO_DO_NOW;
	
	@ClientString(id = 42237, message = "You inconsiderate moron! Can't you even take care of little old me?!")
	public static NpcStringId YOU_INCONSIDERATE_MORON_CAN_T_YOU_EVEN_TAKE_CARE_OF_LITTLE_OLD_ME;
	
	@ClientString(id = 42238, message = "Oh no! The man who eats one's sins has died! Penitence is further away~!")
	public static NpcStringId OH_NO_THE_MAN_WHO_EATS_ONE_S_SINS_HAS_DIED_PENITENCE_IS_FURTHER_AWAY;
	
	@ClientString(id = 42239, message = "Using a special skill here could trigger a bloodbath!")
	public static NpcStringId USING_A_SPECIAL_SKILL_HERE_COULD_TRIGGER_A_BLOODBATH;
	
	@ClientString(id = 42240, message = "Hey, what do you expect of me?")
	public static NpcStringId HEY_WHAT_DO_YOU_EXPECT_OF_ME;
	
	@ClientString(id = 42241, message = "Ugggggh! Push! It's not coming out!")
	public static NpcStringId UGGGGGH_PUSH_IT_S_NOT_COMING_OUT;
	
	@ClientString(id = 42242, message = "Ah, I missed the mark!")
	public static NpcStringId AH_I_MISSED_THE_MARK;
	
	@ClientString(id = 42243, message = "Yawwwwn! It's so boring here. We should go and find some action!")
	public static NpcStringId YAWWWWN_IT_S_SO_BORING_HERE_WE_SHOULD_GO_AND_FIND_SOME_ACTION;
	
	@ClientString(id = 42244, message = "Hey, if you continue to waste time you will never finish your penance!")
	public static NpcStringId HEY_IF_YOU_CONTINUE_TO_WASTE_TIME_YOU_WILL_NEVER_FINISH_YOUR_PENANCE;
	
	@ClientString(id = 42245, message = "I know you don't like me. The feeling is mutual!")
	public static NpcStringId I_KNOW_YOU_DON_T_LIKE_ME_THE_FEELING_IS_MUTUAL;
	
	@ClientString(id = 42246, message = "I need a drink.")
	public static NpcStringId I_NEED_A_DRINK;
	
	@ClientString(id = 42247, message = "Oh, this is dragging on too long... At this rate I won't make it home before the seven seals are broken.")
	public static NpcStringId OH_THIS_IS_DRAGGING_ON_TOO_LONG_AT_THIS_RATE_I_WON_T_MAKE_IT_HOME_BEFORE_THE_SEVEN_SEALS_ARE_BROKEN;
	
	@ClientString(id = 42301, message = "Take Your Best Shot")
	public static NpcStringId TAKE_YOUR_BEST_SHOT;
	
	@ClientString(id = 42302, message = "Take Your Best Shot (In Progress)")
	public static NpcStringId TAKE_YOUR_BEST_SHOT_IN_PROGRESS;
	
	@ClientString(id = 42601, message = "Quest for Fishing shot")
	public static NpcStringId QUEST_FOR_FISHING_SHOT;
	
	@ClientString(id = 42602, message = "Quest for Fishing shot (In Progress)")
	public static NpcStringId QUEST_FOR_FISHING_SHOT_IN_PROGRESS;
	
	@ClientString(id = 43101, message = "Wedding March")
	public static NpcStringId WEDDING_MARCH;
	
	@ClientString(id = 43102, message = "Wedding March (In Progress)")
	public static NpcStringId WEDDING_MARCH_IN_PROGRESS;
	
	@ClientString(id = 43201, message = "Birthday Party Song")
	public static NpcStringId BIRTHDAY_PARTY_SONG;
	
	@ClientString(id = 43202, message = "Birthday Party Song (In Progress)")
	public static NpcStringId BIRTHDAY_PARTY_SONG_IN_PROGRESS;
	
	@ClientString(id = 45001, message = "Grave Robber Rescue")
	public static NpcStringId GRAVE_ROBBER_RESCUE;
	
	@ClientString(id = 45002, message = "Grave Robber Rescue (In Progress)")
	public static NpcStringId GRAVE_ROBBER_RESCUE_IN_PROGRESS;
	
	@ClientString(id = 45003, message = "Grave Robber Rescue (Done)")
	public static NpcStringId GRAVE_ROBBER_RESCUE_DONE;
	
	@ClientString(id = 45101, message = "Lucien's Altar")
	public static NpcStringId LUCIEN_S_ALTAR;
	
	@ClientString(id = 45102, message = "Lucien's Altar (In Progress)")
	public static NpcStringId LUCIEN_S_ALTAR_IN_PROGRESS;
	
	@ClientString(id = 45103, message = "Lucien's Altar (Done)")
	public static NpcStringId LUCIEN_S_ALTAR_DONE;
	
	@ClientString(id = 45201, message = "Finding the Lost Soldiers")
	public static NpcStringId FINDING_THE_LOST_SOLDIERS;
	
	@ClientString(id = 45202, message = "Finding the Lost Soldiers (In Progress)")
	public static NpcStringId FINDING_THE_LOST_SOLDIERS_IN_PROGRESS;
	
	@ClientString(id = 45203, message = "Finding the Lost Soldiers (Done)")
	public static NpcStringId FINDING_THE_LOST_SOLDIERS_DONE;
	
	@ClientString(id = 45301, message = "Not Strong Enough Alone")
	public static NpcStringId NOT_STRONG_ENOUGH_ALONE;
	
	@ClientString(id = 45302, message = "Not Strong Enough Alone (In Progress)")
	public static NpcStringId NOT_STRONG_ENOUGH_ALONE_IN_PROGRESS;
	
	@ClientString(id = 45303, message = "Not Strong Enough Alone (Done)")
	public static NpcStringId NOT_STRONG_ENOUGH_ALONE_DONE;
	
	@ClientString(id = 45401, message = "Completely Lost ")
	public static NpcStringId COMPLETELY_LOST;
	
	@ClientString(id = 45402, message = "Completely Lost (In Progress)")
	public static NpcStringId COMPLETELY_LOST_IN_PROGRESS;
	
	@ClientString(id = 45403, message = "Completely Lost (Done)")
	public static NpcStringId COMPLETELY_LOST_DONE;
	
	@ClientString(id = 45501, message = "Wings of Sand")
	public static NpcStringId WINGS_OF_SAND;
	
	@ClientString(id = 45502, message = "Wings of Sand (In Progress)")
	public static NpcStringId WINGS_OF_SAND_IN_PROGRESS;
	
	@ClientString(id = 45503, message = "Wings of Sand (Done)")
	public static NpcStringId WINGS_OF_SAND_DONE;
	
	@ClientString(id = 45601, message = "Don't Know, Don't Care")
	public static NpcStringId DON_T_KNOW_DON_T_CARE;
	
	@ClientString(id = 45602, message = "Don't Know, Don't Care (In Progress)")
	public static NpcStringId DON_T_KNOW_DON_T_CARE_IN_PROGRESS;
	
	@ClientString(id = 45603, message = "Don't Know, Don't Care (Done)")
	public static NpcStringId DON_T_KNOW_DON_T_CARE_DONE;
	
	@ClientString(id = 45650, message = "$s1 received a $s2 item as a reward from the separated soul.")
	public static NpcStringId S1_RECEIVED_A_S2_ITEM_AS_A_REWARD_FROM_THE_SEPARATED_SOUL;
	
	@ClientString(id = 45651, message = "Sealed Vorpal Helmet")
	public static NpcStringId SEALED_VORPAL_HELMET;
	
	@ClientString(id = 45652, message = "Sealed Vorpal Leather Helmet")
	public static NpcStringId SEALED_VORPAL_LEATHER_HELMET;
	
	@ClientString(id = 45653, message = "Sealed Vorpal Circlet")
	public static NpcStringId SEALED_VORPAL_CIRCLET;
	
	@ClientString(id = 45654, message = "Sealed Vorpal Breastplate")
	public static NpcStringId SEALED_VORPAL_BREASTPLATE;
	
	@ClientString(id = 45655, message = "Sealed Vorpal Leather Breastplate")
	public static NpcStringId SEALED_VORPAL_LEATHER_BREASTPLATE;
	
	@ClientString(id = 45656, message = "Sealed Vorpal Tunic")
	public static NpcStringId SEALED_VORPAL_TUNIC;
	
	@ClientString(id = 45657, message = "Sealed Vorpal Gaiters")
	public static NpcStringId SEALED_VORPAL_GAITERS;
	
	@ClientString(id = 45658, message = "Sealed Vorpal Leather Legging")
	public static NpcStringId SEALED_VORPAL_LEATHER_LEGGING;
	
	@ClientString(id = 45659, message = "Sealed Vorpal Stocking")
	public static NpcStringId SEALED_VORPAL_STOCKING;
	
	@ClientString(id = 45660, message = "Sealed Vorpal Gauntlet")
	public static NpcStringId SEALED_VORPAL_GAUNTLET;
	
	@ClientString(id = 45661, message = "Sealed Vorpal Leather Gloves")
	public static NpcStringId SEALED_VORPAL_LEATHER_GLOVES;
	
	@ClientString(id = 45662, message = "Sealed Vorpal Gloves")
	public static NpcStringId SEALED_VORPAL_GLOVES;
	
	@ClientString(id = 45663, message = "Sealed Vorpal Boots")
	public static NpcStringId SEALED_VORPAL_BOOTS;
	
	@ClientString(id = 45664, message = "Sealed Vorpal Leather Boots")
	public static NpcStringId SEALED_VORPAL_LEATHER_BOOTS;
	
	@ClientString(id = 45665, message = "Sealed Vorpal Shoes")
	public static NpcStringId SEALED_VORPAL_SHOES;
	
	@ClientString(id = 45666, message = "Sealed Vorpal Shield")
	public static NpcStringId SEALED_VORPAL_SHIELD;
	
	@ClientString(id = 45667, message = "Sealed Vorpal Sigil")
	public static NpcStringId SEALED_VORPAL_SIGIL;
	
	@ClientString(id = 45668, message = "Sealed Vorpal Ring")
	public static NpcStringId SEALED_VORPAL_RING;
	
	@ClientString(id = 45669, message = "Sealed Vorpal Earring")
	public static NpcStringId SEALED_VORPAL_EARRING;
	
	@ClientString(id = 45670, message = "Sealed Vorpal Necklace")
	public static NpcStringId SEALED_VORPAL_NECKLACE;
	
	@ClientString(id = 45671, message = "Periel Sword")
	public static NpcStringId PERIEL_SWORD;
	
	@ClientString(id = 45672, message = "Skull Edge")
	public static NpcStringId SKULL_EDGE;
	
	@ClientString(id = 45673, message = "Vigwik Axe")
	public static NpcStringId VIGWIK_AXE;
	
	@ClientString(id = 45674, message = "Devilish Maul")
	public static NpcStringId DEVILISH_MAUL;
	
	@ClientString(id = 45675, message = "Feather Eye Blade")
	public static NpcStringId FEATHER_EYE_BLADE;
	
	@ClientString(id = 45676, message = "Octo Claw")
	public static NpcStringId OCTO_CLAW;
	
	@ClientString(id = 45677, message = "Doubletop Spear")
	public static NpcStringId DOUBLETOP_SPEAR;
	
	@ClientString(id = 45678, message = "Rising Star")
	public static NpcStringId RISING_STAR;
	
	@ClientString(id = 45679, message = "Black Visage")
	public static NpcStringId BLACK_VISAGE;
	
	@ClientString(id = 45680, message = "Veniplant Sword")
	public static NpcStringId VENIPLANT_SWORD;
	
	@ClientString(id = 45681, message = "Skull Carnium Bow")
	public static NpcStringId SKULL_CARNIUM_BOW;
	
	@ClientString(id = 45682, message = "Gemtail Rapier")
	public static NpcStringId GEMTAIL_RAPIER;
	
	@ClientString(id = 45683, message = "Finale Blade")
	public static NpcStringId FINALE_BLADE;
	
	@ClientString(id = 45684, message = "Dominion Crossbow")
	public static NpcStringId DOMINION_CROSSBOW;
	
	@ClientString(id = 45685, message = "Blessed Weapon Enchant Scroll - S Grade")
	public static NpcStringId BLESSED_WEAPON_ENCHANT_SCROLL_S_GRADE;
	
	@ClientString(id = 45686, message = "Blessed Armor Enchant Scroll - S Grade")
	public static NpcStringId BLESSED_ARMOR_ENCHANT_SCROLL_S_GRADE;
	
	@ClientString(id = 45687, message = "Fire Crystal")
	public static NpcStringId FIRE_CRYSTAL;
	
	@ClientString(id = 45688, message = "Water Crystal")
	public static NpcStringId WATER_CRYSTAL;
	
	@ClientString(id = 45689, message = "Earth Crystal")
	public static NpcStringId EARTH_CRYSTAL;
	
	@ClientString(id = 45690, message = "Wind Crystal")
	public static NpcStringId WIND_CRYSTAL;
	
	@ClientString(id = 45691, message = "Holy Crystal")
	public static NpcStringId HOLY_CRYSTAL;
	
	@ClientString(id = 45692, message = "Dark Crystal")
	public static NpcStringId DARK_CRYSTAL;
	
	@ClientString(id = 45693, message = "Weapon Enchant Scroll - S Grade")
	public static NpcStringId WEAPON_ENCHANT_SCROLL_S_GRADE;
	
	@ClientString(id = 45701, message = "Lost and Found")
	public static NpcStringId LOST_AND_FOUND;
	
	@ClientString(id = 45702, message = "Lost and Found (In Progress)")
	public static NpcStringId LOST_AND_FOUND_IN_PROGRESS;
	
	@ClientString(id = 45703, message = "Lost and Found (Done)")
	public static NpcStringId LOST_AND_FOUND_DONE;
	
	@ClientString(id = 45801, message = "Perfect Form")
	public static NpcStringId PERFECT_FORM;
	
	@ClientString(id = 45802, message = "Perfect Form (In Progress)")
	public static NpcStringId PERFECT_FORM_IN_PROGRESS;
	
	@ClientString(id = 45803, message = "Perfect Form (Done)")
	public static NpcStringId PERFECT_FORM_DONE;
	
	@ClientString(id = 46101, message = "Rumble in the Base")
	public static NpcStringId RUMBLE_IN_THE_BASE;
	
	@ClientString(id = 46102, message = "Rumble in the Base (In Progress)")
	public static NpcStringId RUMBLE_IN_THE_BASE_IN_PROGRESS;
	
	@ClientString(id = 46103, message = "Rumble in the Base (Done)")
	public static NpcStringId RUMBLE_IN_THE_BASE_DONE;
	
	@ClientString(id = 46301, message = "I Must Be a Genius")
	public static NpcStringId I_MUST_BE_A_GENIUS;
	
	@ClientString(id = 46302, message = "I Must Be a Genius (In Progress)")
	public static NpcStringId I_MUST_BE_A_GENIUS_IN_PROGRESS;
	
	@ClientString(id = 46303, message = "I Must Be a Genius (Done)")
	public static NpcStringId I_MUST_BE_A_GENIUS_DONE;
	
	@ClientString(id = 46350, message = "Att... attack... $s1.. Ro... rogue... $s2..")
	public static NpcStringId ATT_ATTACK_S1_RO_ROGUE_S2;
	
	@ClientString(id = 46401, message = "Oath")
	public static NpcStringId OATH;
	
	@ClientString(id = 46402, message = "Oath (In Progress)")
	public static NpcStringId OATH_IN_PROGRESS;
	
	@ClientString(id = 46403, message = "Oath (Done)")
	public static NpcStringId OATH_DONE;
	
	@ClientString(id = 50101, message = "Proof of Clan Alliance")
	public static NpcStringId PROOF_OF_CLAN_ALLIANCE;
	
	@ClientString(id = 50102, message = "Proof of Clan Alliance (In Progress)")
	public static NpcStringId PROOF_OF_CLAN_ALLIANCE_IN_PROGRESS;
	
	@ClientString(id = 50110, message = "##########Bingo!##########")
	public static NpcStringId BINGO;
	
	@ClientString(id = 50301, message = "Pursuit of Clan Ambition!")
	public static NpcStringId PURSUIT_OF_CLAN_AMBITION;
	
	@ClientString(id = 50302, message = "Pursuit of Clan Ambition! (In Progress)")
	public static NpcStringId PURSUIT_OF_CLAN_AMBITION_IN_PROGRESS;
	
	@ClientString(id = 50338, message = "Blood and honor!")
	public static NpcStringId BLOOD_AND_HONOR;
	
	@ClientString(id = 50339, message = "Curse of the gods on the one that defiles the property of the empire!")
	public static NpcStringId CURSE_OF_THE_GODS_ON_THE_ONE_THAT_DEFILES_THE_PROPERTY_OF_THE_EMPIRE;
	
	@ClientString(id = 50340, message = "War and death!")
	public static NpcStringId WAR_AND_DEATH;
	
	@ClientString(id = 50341, message = "Ambition and power!")
	public static NpcStringId AMBITION_AND_POWER;
	
	@ClientString(id = 50401, message = "Competition for the Bandit Stronghold")
	public static NpcStringId COMPETITION_FOR_THE_BANDIT_STRONGHOLD;
	
	@ClientString(id = 50402, message = "Competition for the Bandit Stronghold (In Progress)")
	public static NpcStringId COMPETITION_FOR_THE_BANDIT_STRONGHOLD_IN_PROGRESS;
	
	@ClientString(id = 50501, message = "Offering Dedicated to Shilen")
	public static NpcStringId OFFERING_DEDICATED_TO_SHILEN;
	
	@ClientString(id = 50502, message = "Offering Dedicated to Shilen (In Progress)")
	public static NpcStringId OFFERING_DEDICATED_TO_SHILEN_IN_PROGRESS;
	
	@ClientString(id = 50503, message = "$s1 has won the main event for players under level $s2, and earned $s3 points!")
	public static NpcStringId S1_HAS_WON_THE_MAIN_EVENT_FOR_PLAYERS_UNDER_LEVEL_S2_AND_EARNED_S3_POINTS;
	
	@ClientString(id = 50504, message = "$s1 has earned $s2 points in the main event for unlimited levels.")
	public static NpcStringId S1_HAS_EARNED_S2_POINTS_IN_THE_MAIN_EVENT_FOR_UNLIMITED_LEVELS;
	
	@ClientString(id = 50701, message = "Into the Chaos")
	public static NpcStringId INTO_THE_CHAOS;
	
	@ClientString(id = 50702, message = "Into the Chaos (In Progress)")
	public static NpcStringId INTO_THE_CHAOS_IN_PROGRESS;
	
	@ClientString(id = 50801, message = "A Clan's Reputation")
	public static NpcStringId A_CLAN_S_REPUTATION;
	
	@ClientString(id = 50802, message = "A Clan's Reputation (In Progress)")
	public static NpcStringId A_CLAN_S_REPUTATION_IN_PROGRESS;
	
	@ClientString(id = 50851, message = "You have successfully completed a clan quest. $s1 points have been added to your clan's reputation score.")
	public static NpcStringId YOU_HAVE_SUCCESSFULLY_COMPLETED_A_CLAN_QUEST_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 50901, message = "A Clan's Fame")
	public static NpcStringId A_CLAN_S_FAME;
	
	@ClientString(id = 50902, message = "A Clan's Fame (In Progress)")
	public static NpcStringId A_CLAN_S_FAME_IN_PROGRESS;
	
	@ClientString(id = 51001, message = "A Clan's Reputation")
	public static NpcStringId A_CLAN_S_REPUTATION_2;
	
	@ClientString(id = 51002, message = "A Clan's Reputation (In Progress)")
	public static NpcStringId A_CLAN_S_REPUTATION_IN_PROGRESS_2;
	
	@ClientString(id = 51101, message = "Awl Under Foot")
	public static NpcStringId AWL_UNDER_FOOT;
	
	@ClientString(id = 51102, message = "Awl Under Foot (In Progress)")
	public static NpcStringId AWL_UNDER_FOOT_IN_PROGRESS;
	
	@ClientString(id = 51201, message = "Hidden Blade")
	public static NpcStringId HIDDEN_BLADE;
	
	@ClientString(id = 51202, message = "Hidden Blade (In Progress)")
	public static NpcStringId HIDDEN_BLADE_IN_PROGRESS;
	
	@ClientString(id = 55101, message = "Olympiad Starter")
	public static NpcStringId OLYMPIAD_STARTER;
	
	@ClientString(id = 55102, message = "Olympiad Starter (In Progress)")
	public static NpcStringId OLYMPIAD_STARTER_IN_PROGRESS;
	
	@ClientString(id = 55103, message = "Olympiad Starter (Done)")
	public static NpcStringId OLYMPIAD_STARTER_DONE;
	
	@ClientString(id = 55201, message = "Olympiad Veteran")
	public static NpcStringId OLYMPIAD_VETERAN;
	
	@ClientString(id = 55202, message = "Olympiad Veteran (In Progress)")
	public static NpcStringId OLYMPIAD_VETERAN_IN_PROGRESS;
	
	@ClientString(id = 55203, message = "Olympiad Veteran (Done)")
	public static NpcStringId OLYMPIAD_VETERAN_DONE;
	
	@ClientString(id = 55301, message = "Olympiad Undefeated")
	public static NpcStringId OLYMPIAD_UNDEFEATED;
	
	@ClientString(id = 55302, message = "Olympiad Undefeated (In Progress)")
	public static NpcStringId OLYMPIAD_UNDEFEATED_IN_PROGRESS;
	
	@ClientString(id = 55303, message = "Olympiad Undefeated (Done)")
	public static NpcStringId OLYMPIAD_UNDEFEATED_DONE;
	
	@ClientString(id = 60000, message = "The furnace will go out. Watch and see.")
	public static NpcStringId THE_FURNACE_WILL_GO_OUT_WATCH_AND_SEE;
	
	@ClientString(id = 60001, message = "There's about 1 minute left!")
	public static NpcStringId THERE_S_ABOUT_1_MINUTE_LEFT;
	
	@ClientString(id = 60002, message = "There's just 10 seconds left!")
	public static NpcStringId THERE_S_JUST_10_SECONDS_LEFT;
	
	@ClientString(id = 60003, message = "Now, light the furnace's fire.")
	public static NpcStringId NOW_LIGHT_THE_FURNACE_S_FIRE;
	
	@ClientString(id = 60004, message = "Time is up and you have failed. Any more will be difficult.")
	public static NpcStringId TIME_IS_UP_AND_YOU_HAVE_FAILED_ANY_MORE_WILL_BE_DIFFICULT;
	
	@ClientString(id = 60005, message = "Oh, you've succeeded.")
	public static NpcStringId OH_YOU_VE_SUCCEEDED;
	
	@ClientString(id = 60006, message = "Ah, is this failure? But it looks like I can keep going.")
	public static NpcStringId AH_IS_THIS_FAILURE_BUT_IT_LOOKS_LIKE_I_CAN_KEEP_GOING;
	
	@ClientString(id = 60007, message = "Ah, I've failed. Going further will be difficult.")
	public static NpcStringId AH_I_VE_FAILED_GOING_FURTHER_WILL_BE_DIFFICULT;
	
	@ClientString(id = 60008, message = "Furnace of Balance")
	public static NpcStringId FURNACE_OF_BALANCE;
	
	@ClientString(id = 60009, message = "Furnace of Protection")
	public static NpcStringId FURNACE_OF_PROTECTION;
	
	@ClientString(id = 60010, message = "Furnace of Will")
	public static NpcStringId FURNACE_OF_WILL;
	
	@ClientString(id = 60011, message = "Furnace of Magic")
	public static NpcStringId FURNACE_OF_MAGIC;
	
	@ClientString(id = 60012, message = "Divine energy is beginning to encircle.")
	public static NpcStringId DIVINE_ENERGY_IS_BEGINNING_TO_ENCIRCLE;
	
	@ClientString(id = 60013, message = "For the glory of Solina!")
	public static NpcStringId FOR_THE_GLORY_OF_SOLINA;
	
	@ClientString(id = 60014, message = "Punish all those who tread footsteps in this place.")
	public static NpcStringId PUNISH_ALL_THOSE_WHO_TREAD_FOOTSTEPS_IN_THIS_PLACE;
	
	@ClientString(id = 60015, message = "We are the sword of truth, the sword of Solina.")
	public static NpcStringId WE_ARE_THE_SWORD_OF_TRUTH_THE_SWORD_OF_SOLINA;
	
	@ClientString(id = 60016, message = "We raise our blades for the glory of Solina.")
	public static NpcStringId WE_RAISE_OUR_BLADES_FOR_THE_GLORY_OF_SOLINA;
	
	@ClientString(id = 60017, message = "For the glory of Solina!")
	public static NpcStringId FOR_THE_GLORY_OF_SOLINA_2;
	
	@ClientString(id = 60018, message = "Hey, don't go so fast.")
	public static NpcStringId HEY_DON_T_GO_SO_FAST;
	
	@ClientString(id = 60019, message = "It's hard to follow.")
	public static NpcStringId IT_S_HARD_TO_FOLLOW;
	
	@ClientString(id = 60020, message = "Huff huff. You're too fast. I can't follow anymore.")
	public static NpcStringId HUFF_HUFF_YOU_RE_TOO_FAST_I_CAN_T_FOLLOW_ANYMORE;
	
	@ClientString(id = 60021, message = "Ah... I think I remember this place.")
	public static NpcStringId AH_I_THINK_I_REMEMBER_THIS_PLACE;
	
	@ClientString(id = 60022, message = "Ah! Fresh air!")
	public static NpcStringId AH_FRESH_AIR;
	
	@ClientString(id = 60023, message = "What were you doing here?")
	public static NpcStringId WHAT_WERE_YOU_DOING_HERE;
	
	@ClientString(id = 60024, message = "I guess you're the silent type. Then are you looking for treasure like me?")
	public static NpcStringId I_GUESS_YOU_RE_THE_SILENT_TYPE_THEN_ARE_YOU_LOOKING_FOR_TREASURE_LIKE_ME;
	
	@ClientString(id = 60101, message = "Watching Eyes")
	public static NpcStringId WATCHING_EYES;
	
	@ClientString(id = 60102, message = "Watching Eyes (In Progress)")
	public static NpcStringId WATCHING_EYES_IN_PROGRESS;
	
	@ClientString(id = 60201, message = "Shadow of Light")
	public static NpcStringId SHADOW_OF_LIGHT;
	
	@ClientString(id = 60202, message = "Shadow of Light (In Progress)")
	public static NpcStringId SHADOW_OF_LIGHT_IN_PROGRESS;
	
	@ClientString(id = 60301, message = "Daimon the White-Eyed - Part 1")
	public static NpcStringId DAIMON_THE_WHITE_EYED_PART_1;
	
	@ClientString(id = 60302, message = "Daimon the White-Eyed - Part 1 (In Progress)")
	public static NpcStringId DAIMON_THE_WHITE_EYED_PART_1_IN_PROGRESS;
	
	@ClientString(id = 60401, message = "Daimon the White-Eyed - Part 2")
	public static NpcStringId DAIMON_THE_WHITE_EYED_PART_2;
	
	@ClientString(id = 60402, message = "Daimon the White-Eyed - Part 2 (In Progress)")
	public static NpcStringId DAIMON_THE_WHITE_EYED_PART_2_IN_PROGRESS;
	
	@ClientString(id = 60403, message = "Who is calling me?")
	public static NpcStringId WHO_IS_CALLING_ME;
	
	@ClientString(id = 60404, message = "Can light exist without darkness?")
	public static NpcStringId CAN_LIGHT_EXIST_WITHOUT_DARKNESS;
	
	@ClientString(id = 60501, message = "Alliance with Ketra Orcs")
	public static NpcStringId ALLIANCE_WITH_KETRA_ORCS;
	
	@ClientString(id = 60502, message = "Alliance with Ketra Orcs (In Progress)")
	public static NpcStringId ALLIANCE_WITH_KETRA_ORCS_IN_PROGRESS;
	
	@ClientString(id = 60601, message = "Battle against Varka Silenos")
	public static NpcStringId BATTLE_AGAINST_VARKA_SILENOS;
	
	@ClientString(id = 60602, message = "Battle against Varka Silenos (In Progress)")
	public static NpcStringId BATTLE_AGAINST_VARKA_SILENOS_IN_PROGRESS;
	
	@ClientString(id = 60701, message = "Prove Your Courage! (Ketra)")
	public static NpcStringId PROVE_YOUR_COURAGE_KETRA;
	
	@ClientString(id = 60702, message = "Prove Your Courage! (Ketra) (In Progress)")
	public static NpcStringId PROVE_YOUR_COURAGE_KETRA_IN_PROGRESS;
	
	@ClientString(id = 60801, message = "Slay the Enemy Commander! (Ketra)")
	public static NpcStringId SLAY_THE_ENEMY_COMMANDER_KETRA;
	
	@ClientString(id = 60802, message = "Slay the Enemy Commander! (Ketra) (In Progress)")
	public static NpcStringId SLAY_THE_ENEMY_COMMANDER_KETRA_IN_PROGRESS;
	
	@ClientString(id = 60901, message = "Magical Power of Water - Part 1")
	public static NpcStringId MAGICAL_POWER_OF_WATER_PART_1;
	
	@ClientString(id = 60902, message = "Magical Power of Water - Part 1 (In Progress)")
	public static NpcStringId MAGICAL_POWER_OF_WATER_PART_1_IN_PROGRESS;
	
	@ClientString(id = 60903, message = "You can't avoid the eyes of Udan!")
	public static NpcStringId YOU_CAN_T_AVOID_THE_EYES_OF_UDAN;
	
	@ClientString(id = 60904, message = "Udan has already seen your face!")
	public static NpcStringId UDAN_HAS_ALREADY_SEEN_YOUR_FACE;
	
	@ClientString(id = 61001, message = "Magical Power of Water - Part 2")
	public static NpcStringId MAGICAL_POWER_OF_WATER_PART_2;
	
	@ClientString(id = 61002, message = "Magical Power of Water - Part 2 (In Progress)")
	public static NpcStringId MAGICAL_POWER_OF_WATER_PART_2_IN_PROGRESS;
	
	@ClientString(id = 61050, message = "The magical power of water comes from the power of storm and hail! If you dare to confront it, only death will await you!")
	public static NpcStringId THE_MAGICAL_POWER_OF_WATER_COMES_FROM_THE_POWER_OF_STORM_AND_HAIL_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU;
	
	@ClientString(id = 61051, message = "The power of constraint is getting weaker. Your ritual has failed!")
	public static NpcStringId THE_POWER_OF_CONSTRAINT_IS_GETTING_WEAKER_YOUR_RITUAL_HAS_FAILED;
	
	@ClientString(id = 61101, message = "Alliance with Varka Silenos")
	public static NpcStringId ALLIANCE_WITH_VARKA_SILENOS;
	
	@ClientString(id = 61102, message = "Alliance with Varka Silenos (In Progress)")
	public static NpcStringId ALLIANCE_WITH_VARKA_SILENOS_IN_PROGRESS;
	
	@ClientString(id = 61201, message = "Battle against Ketra Orcs ")
	public static NpcStringId BATTLE_AGAINST_KETRA_ORCS;
	
	@ClientString(id = 61202, message = "Battle against Ketra Orcs (In Progress)")
	public static NpcStringId BATTLE_AGAINST_KETRA_ORCS_IN_PROGRESS;
	
	@ClientString(id = 61301, message = "Prove Your Courage! (Varka)")
	public static NpcStringId PROVE_YOUR_COURAGE_VARKA;
	
	@ClientString(id = 61302, message = "Prove Your Courage! (Varka) (In Progress)")
	public static NpcStringId PROVE_YOUR_COURAGE_VARKA_IN_PROGRESS;
	
	@ClientString(id = 61401, message = "Slay the Enemy Commander! (Varka)")
	public static NpcStringId SLAY_THE_ENEMY_COMMANDER_VARKA;
	
	@ClientString(id = 61402, message = "Slay the Enemy Commander! (Varka) (In Progress)")
	public static NpcStringId SLAY_THE_ENEMY_COMMANDER_VARKA_IN_PROGRESS;
	
	@ClientString(id = 61501, message = "Magical Power of Fire - Part 1")
	public static NpcStringId MAGICAL_POWER_OF_FIRE_PART_1;
	
	@ClientString(id = 61502, message = "Magical Power of Fire - Part 1 (In Progress)")
	public static NpcStringId MAGICAL_POWER_OF_FIRE_PART_1_IN_PROGRESS;
	
	@ClientString(id = 61503, message = "You can't avoid the eyes of Asefa!")
	public static NpcStringId YOU_CAN_T_AVOID_THE_EYES_OF_ASEFA;
	
	@ClientString(id = 61504, message = "Asefa has already seen your face!")
	public static NpcStringId ASEFA_HAS_ALREADY_SEEN_YOUR_FACE;
	
	@ClientString(id = 61601, message = "Magical Power of Fire - Part 2")
	public static NpcStringId MAGICAL_POWER_OF_FIRE_PART_2;
	
	@ClientString(id = 61602, message = "Magical Power of Fire - Part 2 (In Progress)")
	public static NpcStringId MAGICAL_POWER_OF_FIRE_PART_2_IN_PROGRESS;
	
	@ClientString(id = 61650, message = "The magical power of fire is also the power of flames and lava! If you dare to confront it, only death will await you!")
	public static NpcStringId THE_MAGICAL_POWER_OF_FIRE_IS_ALSO_THE_POWER_OF_FLAMES_AND_LAVA_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU;
	
	@ClientString(id = 61651, message = "The power of constraint is getting weaker. Your ritual has failed!")
	public static NpcStringId THE_POWER_OF_CONSTRAINT_IS_GETTING_WEAKER_YOUR_RITUAL_HAS_FAILED_2;
	
	@ClientString(id = 61701, message = "Gather the Flames")
	public static NpcStringId GATHER_THE_FLAMES;
	
	@ClientString(id = 61702, message = "Gather the Flames (In Progress)")
	public static NpcStringId GATHER_THE_FLAMES_IN_PROGRESS;
	
	@ClientString(id = 61801, message = "Into the Flames")
	public static NpcStringId INTO_THE_FLAMES;
	
	@ClientString(id = 61802, message = "Into the Flames (In Progress)")
	public static NpcStringId INTO_THE_FLAMES_IN_PROGRESS;
	
	@ClientString(id = 61901, message = "Relics of the Old Empire")
	public static NpcStringId RELICS_OF_THE_OLD_EMPIRE;
	
	@ClientString(id = 61902, message = "Relics of the Old Empire (In Progress)")
	public static NpcStringId RELICS_OF_THE_OLD_EMPIRE_IN_PROGRESS;
	
	@ClientString(id = 62001, message = "Four Goblets")
	public static NpcStringId FOUR_GOBLETS;
	
	@ClientString(id = 62002, message = "Four Goblets (In Progress)")
	public static NpcStringId FOUR_GOBLETS_IN_PROGRESS;
	
	@ClientString(id = 62101, message = "Egg Delivery")
	public static NpcStringId EGG_DELIVERY;
	
	@ClientString(id = 62102, message = "Egg Delivery (In Progress)")
	public static NpcStringId EGG_DELIVERY_IN_PROGRESS;
	
	@ClientString(id = 62201, message = "Specialty Liquor Delivery")
	public static NpcStringId SPECIALTY_LIQUOR_DELIVERY;
	
	@ClientString(id = 62202, message = "Specialty Liquor Delivery (In Progress)")
	public static NpcStringId SPECIALTY_LIQUOR_DELIVERY_IN_PROGRESS;
	
	@ClientString(id = 62301, message = "The Finest Food")
	public static NpcStringId THE_FINEST_FOOD;
	
	@ClientString(id = 62302, message = "The Finest Food (In Progress)")
	public static NpcStringId THE_FINEST_FOOD_IN_PROGRESS;
	
	@ClientString(id = 62401, message = "The Finest Ingredients - Part 1")
	public static NpcStringId THE_FINEST_INGREDIENTS_PART_1;
	
	@ClientString(id = 62402, message = "The Finest Ingredients - Part 1 (In Progress)")
	public static NpcStringId THE_FINEST_INGREDIENTS_PART_1_IN_PROGRESS;
	
	@ClientString(id = 62501, message = "The Finest Ingredients - Part 2")
	public static NpcStringId THE_FINEST_INGREDIENTS_PART_2;
	
	@ClientString(id = 62502, message = "The Finest Ingredients - Part 2 (In Progress)")
	public static NpcStringId THE_FINEST_INGREDIENTS_PART_2_IN_PROGRESS;
	
	@ClientString(id = 62503, message = "I smell something delicious...")
	public static NpcStringId I_SMELL_SOMETHING_DELICIOUS;
	
	@ClientString(id = 62504, message = "Oooh!")
	public static NpcStringId OOOH;
	
	@ClientString(id = 62601, message = "A Dark Twilight")
	public static NpcStringId A_DARK_TWILIGHT;
	
	@ClientString(id = 62602, message = "A Dark Twilight (In Progress)")
	public static NpcStringId A_DARK_TWILIGHT_IN_PROGRESS;
	
	@ClientString(id = 62701, message = "Heart in Search of Power")
	public static NpcStringId HEART_IN_SEARCH_OF_POWER;
	
	@ClientString(id = 62702, message = "Heart in Search of Power (In Progress)")
	public static NpcStringId HEART_IN_SEARCH_OF_POWER_IN_PROGRESS;
	
	@ClientString(id = 62801, message = "Hunt of the Golden Ram Mercenary Force")
	public static NpcStringId HUNT_OF_THE_GOLDEN_RAM_MERCENARY_FORCE;
	
	@ClientString(id = 62802, message = "Hunt of the Golden Ram Mercenary Force (In Progress)")
	public static NpcStringId HUNT_OF_THE_GOLDEN_RAM_MERCENARY_FORCE_IN_PROGRESS;
	
	@ClientString(id = 62901, message = "Clean up the Swamp of Screams")
	public static NpcStringId CLEAN_UP_THE_SWAMP_OF_SCREAMS;
	
	@ClientString(id = 62902, message = "Clean up the Swamp of Screams (In Progress)")
	public static NpcStringId CLEAN_UP_THE_SWAMP_OF_SCREAMS_IN_PROGRESS;
	
	@ClientString(id = 63101, message = "Delicious Top Choice Meat")
	public static NpcStringId DELICIOUS_TOP_CHOICE_MEAT;
	
	@ClientString(id = 63102, message = "Delicious Top Choice Meat (In Progress)")
	public static NpcStringId DELICIOUS_TOP_CHOICE_MEAT_IN_PROGRESS;
	
	@ClientString(id = 63201, message = "Necromancer's Request")
	public static NpcStringId NECROMANCER_S_REQUEST;
	
	@ClientString(id = 63202, message = "Necromancer's Request (In Progress)")
	public static NpcStringId NECROMANCER_S_REQUEST_IN_PROGRESS;
	
	@ClientString(id = 63301, message = "In the Forgotten Village")
	public static NpcStringId IN_THE_FORGOTTEN_VILLAGE;
	
	@ClientString(id = 63302, message = "In the Forgotten Village (In Progress)")
	public static NpcStringId IN_THE_FORGOTTEN_VILLAGE_IN_PROGRESS;
	
	@ClientString(id = 63401, message = "In Search of Fragments of Dimension")
	public static NpcStringId IN_SEARCH_OF_FRAGMENTS_OF_DIMENSION;
	
	@ClientString(id = 63402, message = "In Search of Fragments of Dimension (In Progress)")
	public static NpcStringId IN_SEARCH_OF_FRAGMENTS_OF_DIMENSION_IN_PROGRESS;
	
	@ClientString(id = 63501, message = "Into the Dimensional Rift")
	public static NpcStringId INTO_THE_DIMENSIONAL_RIFT;
	
	@ClientString(id = 63502, message = "Into the Dimensional Rift (In Progress)")
	public static NpcStringId INTO_THE_DIMENSIONAL_RIFT_IN_PROGRESS;
	
	@ClientString(id = 63601, message = "Truth Beyond the Gate")
	public static NpcStringId TRUTH_BEYOND_THE_GATE;
	
	@ClientString(id = 63602, message = "Truth Beyond the Gate (In Progress)")
	public static NpcStringId TRUTH_BEYOND_THE_GATE_IN_PROGRESS;
	
	@ClientString(id = 63701, message = "Through the Gate Once More")
	public static NpcStringId THROUGH_THE_GATE_ONCE_MORE;
	
	@ClientString(id = 63702, message = "Through the Gate Once More (In Progress)")
	public static NpcStringId THROUGH_THE_GATE_ONCE_MORE_IN_PROGRESS;
	
	@ClientString(id = 63801, message = "Seekers of the Holy Grail")
	public static NpcStringId SEEKERS_OF_THE_HOLY_GRAIL;
	
	@ClientString(id = 63802, message = "Seekers of the Holy Grail (In Progress)")
	public static NpcStringId SEEKERS_OF_THE_HOLY_GRAIL_IN_PROGRESS;
	
	@ClientString(id = 63901, message = "Guardians of the Holy Grail")
	public static NpcStringId GUARDIANS_OF_THE_HOLY_GRAIL;
	
	@ClientString(id = 63902, message = "Guardians of the Holy Grail (In Progress)")
	public static NpcStringId GUARDIANS_OF_THE_HOLY_GRAIL_IN_PROGRESS;
	
	@ClientString(id = 64001, message = "The Zero Hour")
	public static NpcStringId THE_ZERO_HOUR;
	
	@ClientString(id = 64002, message = "The Zero Hour (In Progress)")
	public static NpcStringId THE_ZERO_HOUR_IN_PROGRESS;
	
	@ClientString(id = 64003, message = "The decisive stage (Done)")
	public static NpcStringId THE_DECISIVE_STAGE_DONE;
	
	@ClientString(id = 64101, message = "Sailren's Charge!")
	public static NpcStringId SAILREN_S_CHARGE;
	
	@ClientString(id = 64102, message = "Sailren's Charge! (In Progress)")
	public static NpcStringId SAILREN_S_CHARGE_IN_PROGRESS;
	
	@ClientString(id = 64201, message = "A Powerful Primeval Creature")
	public static NpcStringId A_POWERFUL_PRIMEVAL_CREATURE;
	
	@ClientString(id = 64202, message = "A Powerful Primeval Creature (In Progress)")
	public static NpcStringId A_POWERFUL_PRIMEVAL_CREATURE_IN_PROGRESS;
	
	@ClientString(id = 64301, message = "Rise and Fall of the Elroki Tribe")
	public static NpcStringId RISE_AND_FALL_OF_THE_ELROKI_TRIBE;
	
	@ClientString(id = 64302, message = "Rise and Fall of the Elroki Tribe (In Progress)")
	public static NpcStringId RISE_AND_FALL_OF_THE_ELROKI_TRIBE_IN_PROGRESS;
	
	@ClientString(id = 64401, message = "Grave Robber Annihilation")
	public static NpcStringId GRAVE_ROBBER_ANNIHILATION;
	
	@ClientString(id = 64402, message = "Grave Robber Annihilation (In Progress)")
	public static NpcStringId GRAVE_ROBBER_ANNIHILATION_IN_PROGRESS;
	
	@ClientString(id = 64501, message = "Ghosts of Batur")
	public static NpcStringId GHOSTS_OF_BATUR;
	
	@ClientString(id = 64502, message = "Ghosts of Batur (In Progress)")
	public static NpcStringId GHOSTS_OF_BATUR_IN_PROGRESS;
	
	@ClientString(id = 64601, message = "Signs of Revolt")
	public static NpcStringId SIGNS_OF_REVOLT;
	
	@ClientString(id = 64602, message = "Signs of Revolt (In Progress)")
	public static NpcStringId SIGNS_OF_REVOLT_IN_PROGRESS;
	
	@ClientString(id = 64701, message = "Influx of Machines")
	public static NpcStringId INFLUX_OF_MACHINES;
	
	@ClientString(id = 64702, message = "Influx of Machines (In Progress)")
	public static NpcStringId INFLUX_OF_MACHINES_IN_PROGRESS;
	
	@ClientString(id = 64801, message = "An Ice Merchant's Dream")
	public static NpcStringId AN_ICE_MERCHANT_S_DREAM;
	
	@ClientString(id = 64802, message = "An Ice Merchant's Dream (In Progress)")
	public static NpcStringId AN_ICE_MERCHANT_S_DREAM_IN_PROGRESS;
	
	@ClientString(id = 64901, message = "A Looter and a Railroad Man")
	public static NpcStringId A_LOOTER_AND_A_RAILROAD_MAN;
	
	@ClientString(id = 64902, message = "A Looter and a Railroad Man (In Progress)")
	public static NpcStringId A_LOOTER_AND_A_RAILROAD_MAN_IN_PROGRESS;
	
	@ClientString(id = 65001, message = "A Broken Dream")
	public static NpcStringId A_BROKEN_DREAM;
	
	@ClientString(id = 65002, message = "A Broken Dream (In Progress)")
	public static NpcStringId A_BROKEN_DREAM_IN_PROGRESS;
	
	@ClientString(id = 65101, message = "Runaway Youth")
	public static NpcStringId RUNAWAY_YOUTH;
	
	@ClientString(id = 65102, message = "Runaway Youth (In Progress)")
	public static NpcStringId RUNAWAY_YOUTH_IN_PROGRESS;
	
	@ClientString(id = 65201, message = "An Aged Ex-Adventurer")
	public static NpcStringId AN_AGED_EX_ADVENTURER;
	
	@ClientString(id = 65202, message = "An Aged Ex-Adventurer (In Progress)")
	public static NpcStringId AN_AGED_EX_ADVENTURER_IN_PROGRESS;
	
	@ClientString(id = 65301, message = "Wild Maiden")
	public static NpcStringId WILD_MAIDEN;
	
	@ClientString(id = 65302, message = "Wild Maiden (In Progress)")
	public static NpcStringId WILD_MAIDEN_IN_PROGRESS;
	
	@ClientString(id = 65401, message = "Journey to a Settlement")
	public static NpcStringId JOURNEY_TO_A_SETTLEMENT;
	
	@ClientString(id = 65402, message = "Journey to a Settlement (In Progress)")
	public static NpcStringId JOURNEY_TO_A_SETTLEMENT_IN_PROGRESS;
	
	@ClientString(id = 65501, message = "A Grand Plan for Taming Wild Beasts")
	public static NpcStringId A_GRAND_PLAN_FOR_TAMING_WILD_BEASTS;
	
	@ClientString(id = 65502, message = "A Grand Plan for Taming Wild Beasts (In Progress)")
	public static NpcStringId A_GRAND_PLAN_FOR_TAMING_WILD_BEASTS_IN_PROGRESS;
	
	@ClientString(id = 65901, message = "I'd Rather Be Collecting Fairy Breath")
	public static NpcStringId I_D_RATHER_BE_COLLECTING_FAIRY_BREATH;
	
	@ClientString(id = 65902, message = "I'd Rather Be Collecting Fairy Breath (In Progress)")
	public static NpcStringId I_D_RATHER_BE_COLLECTING_FAIRY_BREATH_IN_PROGRESS;
	
	@ClientString(id = 66001, message = "Aiding the Floran Village.")
	public static NpcStringId AIDING_THE_FLORAN_VILLAGE;
	
	@ClientString(id = 66002, message = "Aiding the Floran Village (In Progress)")
	public static NpcStringId AIDING_THE_FLORAN_VILLAGE_IN_PROGRESS;
	
	@ClientString(id = 66101, message = "Making the Harvest Grounds Safe")
	public static NpcStringId MAKING_THE_HARVEST_GROUNDS_SAFE;
	
	@ClientString(id = 66102, message = "Making the Harvest Grounds Safe (In Progress)")
	public static NpcStringId MAKING_THE_HARVEST_GROUNDS_SAFE_IN_PROGRESS;
	
	@ClientString(id = 66201, message = "A Game of Cards")
	public static NpcStringId A_GAME_OF_CARDS;
	
	@ClientString(id = 66202, message = "A Game of Cards (In Progress)")
	public static NpcStringId A_GAME_OF_CARDS_IN_PROGRESS;
	
	@ClientString(id = 66300, message = "No such card")
	public static NpcStringId NO_SUCH_CARD;
	
	@ClientString(id = 66301, message = "Seductive Whispers")
	public static NpcStringId SEDUCTIVE_WHISPERS;
	
	@ClientString(id = 66302, message = "Seductive Whispers (In Progress)")
	public static NpcStringId SEDUCTIVE_WHISPERS_IN_PROGRESS;
	
	@ClientString(id = 66311, message = "<font color='ff453d'> Sun Card: 1 </font>")
	public static NpcStringId FONT_COLOR_FF453D_SUN_CARD_1_FONT;
	
	@ClientString(id = 66312, message = "<font color='ff453d'> Sun Card: 2 </font>")
	public static NpcStringId FONT_COLOR_FF453D_SUN_CARD_2_FONT;
	
	@ClientString(id = 66313, message = "<font color='ff453d'> Sun Card: 3 </font>")
	public static NpcStringId FONT_COLOR_FF453D_SUN_CARD_3_FONT;
	
	@ClientString(id = 66314, message = "<font color='ff453d'> Sun Card: 4 </font>")
	public static NpcStringId FONT_COLOR_FF453D_SUN_CARD_4_FONT;
	
	@ClientString(id = 66315, message = "<font color='ff453d'> Sun Card: 5 </font>")
	public static NpcStringId FONT_COLOR_FF453D_SUN_CARD_5_FONT;
	
	@ClientString(id = 66321, message = "<font color='fff802'> Moon Card: 1 </font>")
	public static NpcStringId FONT_COLOR_FFF802_MOON_CARD_1_FONT;
	
	@ClientString(id = 66322, message = "<font color='fff802'> Moon Card: 2 </font>")
	public static NpcStringId FONT_COLOR_FFF802_MOON_CARD_2_FONT;
	
	@ClientString(id = 66323, message = "<font color='fff802'> Moon Card: 3 </font>")
	public static NpcStringId FONT_COLOR_FFF802_MOON_CARD_3_FONT;
	
	@ClientString(id = 66324, message = "<font color='fff802'> Moon Card: 4 </font>")
	public static NpcStringId FONT_COLOR_FFF802_MOON_CARD_4_FONT;
	
	@ClientString(id = 66325, message = "<font color='fff802'> Moon Card: 5 </font>")
	public static NpcStringId FONT_COLOR_FFF802_MOON_CARD_5_FONT;
	
	@ClientString(id = 68801, message = "Defeat the Elrokian Raiders! ")
	public static NpcStringId DEFEAT_THE_ELROKIAN_RAIDERS;
	
	@ClientString(id = 68802, message = "Defeat the Elrokian Raiders! (In Progress)")
	public static NpcStringId DEFEAT_THE_ELROKIAN_RAIDERS_IN_PROGRESS;
	
	@ClientString(id = 69001, message = "Jude's Request")
	public static NpcStringId JUDE_S_REQUEST;
	
	@ClientString(id = 69002, message = "Jude's Request (In Progress)")
	public static NpcStringId JUDE_S_REQUEST_IN_PROGRESS;
	
	@ClientString(id = 69101, message = "Matras' Suspicious Request")
	public static NpcStringId MATRAS_SUSPICIOUS_REQUEST;
	
	@ClientString(id = 69102, message = "Matras' Suspicious Request (In Progress)")
	public static NpcStringId MATRAS_SUSPICIOUS_REQUEST_IN_PROGRESS;
	
	@ClientString(id = 69201, message = "How to Oppose Evil")
	public static NpcStringId HOW_TO_OPPOSE_EVIL;
	
	@ClientString(id = 69202, message = "How to Oppose Evil (In Progress)")
	public static NpcStringId HOW_TO_OPPOSE_EVIL_IN_PROGRESS;
	
	@ClientString(id = 69301, message = "Defeating Dragonkin Remnants")
	public static NpcStringId DEFEATING_DRAGONKIN_REMNANTS;
	
	@ClientString(id = 69302, message = "Defeating Dragonkin Remnants (In Progress)")
	public static NpcStringId DEFEATING_DRAGONKIN_REMNANTS_IN_PROGRESS;
	
	@ClientString(id = 69401, message = "Break Through the Hall of Suffering")
	public static NpcStringId BREAK_THROUGH_THE_HALL_OF_SUFFERING;
	
	@ClientString(id = 69402, message = "Break Through the Hall of Suffering (In Progress)")
	public static NpcStringId BREAK_THROUGH_THE_HALL_OF_SUFFERING_IN_PROGRESS;
	
	@ClientString(id = 69501, message = "Defend the Hall of Suffering")
	public static NpcStringId DEFEND_THE_HALL_OF_SUFFERING;
	
	@ClientString(id = 69502, message = "Defend the Hall of Suffering (In Progress)")
	public static NpcStringId DEFEND_THE_HALL_OF_SUFFERING_IN_PROGRESS;
	
	@ClientString(id = 69601, message = "Conquer the Hall of Erosion")
	public static NpcStringId CONQUER_THE_HALL_OF_EROSION;
	
	@ClientString(id = 69602, message = "Conquer the Hall of Erosion (In Progress)")
	public static NpcStringId CONQUER_THE_HALL_OF_EROSION_IN_PROGRESS;
	
	@ClientString(id = 69701, message = "Defend the Hall of Erosion")
	public static NpcStringId DEFEND_THE_HALL_OF_EROSION;
	
	@ClientString(id = 69702, message = "Defend the Hall of Erosion (In Progress)")
	public static NpcStringId DEFEND_THE_HALL_OF_EROSION_IN_PROGRESS;
	
	@ClientString(id = 69801, message = "Block the Lord's Escape")
	public static NpcStringId BLOCK_THE_LORD_S_ESCAPE;
	
	@ClientString(id = 69802, message = "Block the Lord's Escape (In Progress)")
	public static NpcStringId BLOCK_THE_LORD_S_ESCAPE_IN_PROGRESS;
	
	@ClientString(id = 69901, message = "Guardian of the Skies")
	public static NpcStringId GUARDIAN_OF_THE_SKIES;
	
	@ClientString(id = 69902, message = "Guardian of the Skies (In Progress)")
	public static NpcStringId GUARDIAN_OF_THE_SKIES_IN_PROGRESS;
	
	@ClientString(id = 70001, message = "Cursed Life")
	public static NpcStringId CURSED_LIFE;
	
	@ClientString(id = 70002, message = "Cursed Life (In Progress)")
	public static NpcStringId CURSED_LIFE_IN_PROGRESS;
	
	@ClientString(id = 70101, message = "Proof of Existence")
	public static NpcStringId PROOF_OF_EXISTENCE;
	
	@ClientString(id = 70102, message = "Proof of Existence (In Progress)")
	public static NpcStringId PROOF_OF_EXISTENCE_IN_PROGRESS;
	
	@ClientString(id = 70201, message = "A Trap for Revenge")
	public static NpcStringId A_TRAP_FOR_REVENGE;
	
	@ClientString(id = 70202, message = "A Trap for Revenge (In Progress)")
	public static NpcStringId A_TRAP_FOR_REVENGE_IN_PROGRESS;
	
	@ClientString(id = 70801, message = "Path to Becoming a Lord - Gludio")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_GLUDIO;
	
	@ClientString(id = 70802, message = "Path to Becoming a Lord - Gludio (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_GLUDIO_IN_PROGRESS;
	
	@ClientString(id = 70851, message = "Have you completed your preparations to become a lord?")
	public static NpcStringId HAVE_YOU_COMPLETED_YOUR_PREPARATIONS_TO_BECOME_A_LORD;
	
	@ClientString(id = 70852, message = "$s1. Now, depart!")
	public static NpcStringId S1_NOW_DEPART;
	
	@ClientString(id = 70853, message = "Go find Saius!")
	public static NpcStringId GO_FIND_SAIUS;
	
	@ClientString(id = 70854, message = "Listen, you villagers. Our liege, who will soon become a lord, has defeated the Headless Knight. You can now rest easy!")
	public static NpcStringId LISTEN_YOU_VILLAGERS_OUR_LIEGE_WHO_WILL_SOON_BECOME_A_LORD_HAS_DEFEATED_THE_HEADLESS_KNIGHT_YOU_CAN_NOW_REST_EASY;
	
	@ClientString(id = 70855, message = "$s1! Do you dare defy my subordinates?")
	public static NpcStringId S1_DO_YOU_DARE_DEFY_MY_SUBORDINATES;
	
	@ClientString(id = 70856, message = "Does my mission to block the supplies end here?")
	public static NpcStringId DOES_MY_MISSION_TO_BLOCK_THE_SUPPLIES_END_HERE;
	
	@ClientString(id = 70859, message = "$s1 has become lord of the Town of Gludio. Long may he reign!")
	public static NpcStringId S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_GLUDIO_LONG_MAY_HE_REIGN;
	
	@ClientString(id = 70901, message = "Path to Becoming a Lord - Dion")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_DION;
	
	@ClientString(id = 70902, message = "Path to Becoming a Lord - Dion (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_DION_IN_PROGRESS;
	
	@ClientString(id = 70951, message = "Have you completed your preparations to become a lord?")
	public static NpcStringId HAVE_YOU_COMPLETED_YOUR_PREPARATIONS_TO_BECOME_A_LORD_2;
	
	@ClientString(id = 70952, message = "$s1. Now, depart!")
	public static NpcStringId S1_NOW_DEPART_2;
	
	@ClientString(id = 70955, message = "$s1! Do you dare defy my subordinates?")
	public static NpcStringId S1_DO_YOU_DARE_DEFY_MY_SUBORDINATES_2;
	
	@ClientString(id = 70956, message = "Does my mission to block the supplies end here?")
	public static NpcStringId DOES_MY_MISSION_TO_BLOCK_THE_SUPPLIES_END_HERE_2;
	
	@ClientString(id = 70957, message = "You'll see! I won't forgive you next time!")
	public static NpcStringId YOU_LL_SEE_I_WON_T_FORGIVE_YOU_NEXT_TIME;
	
	@ClientString(id = 70959, message = "$s1 has become lord of the Town of Dion. Long may he reign!")
	public static NpcStringId S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_DION_LONG_MAY_HE_REIGN;
	
	@ClientString(id = 71001, message = "Path to Becoming a Lord - Giran")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_GIRAN;
	
	@ClientString(id = 71002, message = "Path to Becoming a Lord - Giran (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_GIRAN_IN_PROGRESS;
	
	@ClientString(id = 71051, message = "Have you completed your preparations to become a lord?")
	public static NpcStringId HAVE_YOU_COMPLETED_YOUR_PREPARATIONS_TO_BECOME_A_LORD_3;
	
	@ClientString(id = 71052, message = "It's the enemy! No mercy!")
	public static NpcStringId IT_S_THE_ENEMY_NO_MERCY;
	
	@ClientString(id = 71053, message = "What are you doing? We are still superior!")
	public static NpcStringId WHAT_ARE_YOU_DOING_WE_ARE_STILL_SUPERIOR;
	
	@ClientString(id = 71054, message = "How infuriating! This enemy...")
	public static NpcStringId HOW_INFURIATING_THIS_ENEMY;
	
	@ClientString(id = 71059, message = "$s1 has become the lord of the Town of Giran. May there be glory in the territory of Giran! ")
	public static NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_GIRAN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_GIRAN;
	
	@ClientString(id = 71101, message = "Path to Becoming a Lord - Innadril")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_INNADRIL;
	
	@ClientString(id = 71102, message = "Path to Becoming a Lord - Innadril (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_INNADRIL_IN_PROGRESS;
	
	@ClientString(id = 71151, message = "My liege! Where are you?")
	public static NpcStringId MY_LIEGE_WHERE_ARE_YOU;
	
	@ClientString(id = 71152, message = "$s1. Now, depart!")
	public static NpcStringId S1_NOW_DEPART_3;
	
	@ClientString(id = 71159, message = "$s1 has become the lord of the Town of Innadril. May there be glory in the territory of Innadril! ")
	public static NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_INNADRIL_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_INNADRIL;
	
	@ClientString(id = 71201, message = "Path to Becoming a Lord - Oren")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_OREN;
	
	@ClientString(id = 71202, message = "Path to Becoming a Lord - Oren (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_OREN_IN_PROGRESS;
	
	@ClientString(id = 71251, message = "Have you completed your preparations to become a lord?")
	public static NpcStringId HAVE_YOU_COMPLETED_YOUR_PREPARATIONS_TO_BECOME_A_LORD_4;
	
	@ClientString(id = 71252, message = "You have found all the Nebulite Orbs!")
	public static NpcStringId YOU_HAVE_FOUND_ALL_THE_NEBULITE_ORBS;
	
	@ClientString(id = 71259, message = "$s1 has become the lord of the Town of Oren. May there be glory in the territory of Oren! ")
	public static NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_OREN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_OREN;
	
	@ClientString(id = 71301, message = "Path to Becoming a Lord - Aden")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_ADEN;
	
	@ClientString(id = 71302, message = "Path to Becoming a Lord - Aden (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_ADEN_IN_PROGRESS;
	
	@ClientString(id = 71351, message = "$s1 has become the lord of the Town of Aden. May there be glory in the territory of Aden! ")
	public static NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_ADEN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_ADEN;
	
	@ClientString(id = 71401, message = "Path to Becoming a Lord - Schuttgart")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_SCHUTTGART;
	
	@ClientString(id = 71402, message = "Path to Becoming a Lord - Schuttgart (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_SCHUTTGART_IN_PROGRESS;
	
	@ClientString(id = 71451, message = "Have you completed your preparations to become a lord?")
	public static NpcStringId HAVE_YOU_COMPLETED_YOUR_PREPARATIONS_TO_BECOME_A_LORD_5;
	
	@ClientString(id = 71459, message = "$s1 has become the lord of the Town of Schuttgart. May there be glory in the territory of Schuttgart! ")
	public static NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_SCHUTTGART_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_SCHUTTGART;
	
	@ClientString(id = 71501, message = "Path to Becoming a Lord - Goddard")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_GODDARD;
	
	@ClientString(id = 71502, message = "Path to Becoming a Lord - Goddard (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_GODDARD_IN_PROGRESS;
	
	@ClientString(id = 71551, message = "$s1, I will remember you.")
	public static NpcStringId S1_I_WILL_REMEMBER_YOU;
	
	@ClientString(id = 71559, message = "$s1 has become the lord of the Town of Goddard. May there be glory in the territory of Goddard!")
	public static NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_GODDARD_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_GODDARD;
	
	@ClientString(id = 71601, message = "Path to Becoming a Lord - Rune")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_RUNE;
	
	@ClientString(id = 71602, message = "Path to Becoming a Lord - Rune (In Progress)")
	public static NpcStringId PATH_TO_BECOMING_A_LORD_RUNE_IN_PROGRESS;
	
	@ClientString(id = 71652, message = "$s1. Now, depart!")
	public static NpcStringId S1_NOW_DEPART_4;
	
	@ClientString(id = 71653, message = "Frederick is looking for you, my liege.")
	public static NpcStringId FREDERICK_IS_LOOKING_FOR_YOU_MY_LIEGE;
	
	@ClientString(id = 71654, message = "Ho ho! Did you think you could really stop trading with us?")
	public static NpcStringId HO_HO_DID_YOU_THINK_YOU_COULD_REALLY_STOP_TRADING_WITH_US;
	
	@ClientString(id = 71655, message = "You have charged into the temple.")
	public static NpcStringId YOU_HAVE_CHARGED_INTO_THE_TEMPLE;
	
	@ClientString(id = 71656, message = "You are in the midst of dealing with the heretic of Heretic Temple.")
	public static NpcStringId YOU_ARE_IN_THE_MIDST_OF_DEALING_WITH_THE_HERETIC_OF_HERETIC_TEMPLE;
	
	@ClientString(id = 71657, message = "The Heretic Temple is descending into chaos.")
	public static NpcStringId THE_HERETIC_TEMPLE_IS_DESCENDING_INTO_CHAOS;
	
	@ClientString(id = 71659, message = "$s1 has become the lord of the Town of Rune. May there be glory in the territory of Rune! ")
	public static NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_RUNE_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_RUNE;
	
	@ClientString(id = 71701, message = "For the Sake of the Territory - Gludio")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_GLUDIO;
	
	@ClientString(id = 71702, message = "For the Sake of the Territory - Gludio (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_GLUDIO_IN_PROGRESS;
	
	@ClientString(id = 71751, message = "$s1! Raise your weapons for the sake of the territory!")
	public static NpcStringId S1_RAISE_YOUR_WEAPONS_FOR_THE_SAKE_OF_THE_TERRITORY;
	
	@ClientString(id = 71752, message = "$s1! The war is over. Lower your sword for the sake of the future.")
	public static NpcStringId S1_THE_WAR_IS_OVER_LOWER_YOUR_SWORD_FOR_THE_SAKE_OF_THE_FUTURE;
	
	@ClientString(id = 71753, message = "$s1 Territory Badge(s) and $s2 Adenas")
	public static NpcStringId S1_TERRITORY_BADGE_S_AND_S2_ADENAS;
	
	@ClientString(id = 71754, message = "90 Territory Badges, $s1 score(s) in Individual Fame and $s2 Adenas")
	public static NpcStringId NINETY_TERRITORY_BADGES_S1_SCORE_S_IN_INDIVIDUAL_FAME_AND_S2_ADENAS;
	
	@ClientString(id = 71755, message = "90 Territory Badges, 450 scores in Individual Fame and $s2 Adenas")
	public static NpcStringId NINETY_TERRITORY_BADGES_450_SCORES_IN_INDIVIDUAL_FAME_AND_S2_ADENAS;
	
	@ClientString(id = 71801, message = "For the Sake of the Territory - Dion")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_DION;
	
	@ClientString(id = 71802, message = "For the Sake of the Territory - Dion (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_DION_IN_PROGRESS;
	
	@ClientString(id = 71901, message = "For the Sake of the Territory - Giran")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_GIRAN;
	
	@ClientString(id = 71902, message = "For the Sake of the Territory - Giran (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_GIRAN_IN_PROGRESS;
	
	@ClientString(id = 72001, message = "For the Sake of the Territory - Innadril")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_INNADRIL;
	
	@ClientString(id = 72002, message = "For the Sake of the Territory - Innadril (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_INNADRIL_IN_PROGRESS;
	
	@ClientString(id = 72101, message = "For the Sake of the Territory - Oren")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_OREN;
	
	@ClientString(id = 72102, message = "For the Sake of the Territory - Oren (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_OREN_IN_PROGRESS;
	
	@ClientString(id = 72201, message = "For the Sake of the Territory - Aden")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_ADEN;
	
	@ClientString(id = 72202, message = "For the Sake of the Territory - Aden (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_ADEN_IN_PROGRESS;
	
	@ClientString(id = 72301, message = "For the Sake of the Territory - Schuttgart")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_SCHUTTGART;
	
	@ClientString(id = 72302, message = "For the Sake of the Territory - Schuttgart (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_SCHUTTGART_IN_PROGRESS;
	
	@ClientString(id = 72401, message = "For the Sake of the Territory - Goddard")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_GODDARD;
	
	@ClientString(id = 72402, message = "For the Sake of the Territory - Goddard (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_GODDARD_IN_PROGRESS;
	
	@ClientString(id = 72501, message = "For the Sake of the Territory - Rune")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_RUNE;
	
	@ClientString(id = 72502, message = "For the Sake of the Territory - Rune (In Progress)")
	public static NpcStringId FOR_THE_SAKE_OF_THE_TERRITORY_RUNE_IN_PROGRESS;
	
	@ClientString(id = 72601, message = "Light within the Darkness")
	public static NpcStringId LIGHT_WITHIN_THE_DARKNESS;
	
	@ClientString(id = 72602, message = "Light within the Darkness (In Progress)")
	public static NpcStringId LIGHT_WITHIN_THE_DARKNESS_IN_PROGRESS;
	
	@ClientString(id = 72701, message = "Hope within the Darkness")
	public static NpcStringId HOPE_WITHIN_THE_DARKNESS;
	
	@ClientString(id = 72702, message = "Hope within the Darkness (In Progress)")
	public static NpcStringId HOPE_WITHIN_THE_DARKNESS_IN_PROGRESS;
	
	@ClientString(id = 72901, message = "Protect the Territory Catapult!")
	public static NpcStringId PROTECT_THE_TERRITORY_CATAPULT;
	
	@ClientString(id = 72902, message = "Protect the Territory Catapult! (In Progress)")
	public static NpcStringId PROTECT_THE_TERRITORY_CATAPULT_IN_PROGRESS;
	
	@ClientString(id = 72903, message = "The mercenary quest number is $s1; memostate1 is $s2; and memostate2 is $s3.")
	public static NpcStringId THE_MERCENARY_QUEST_NUMBER_IS_S1_MEMOSTATE1_IS_S2_AND_MEMOSTATE2_IS_S3;
	
	@ClientString(id = 72904, message = "user_connected event occurrence success. Siege Id is $s1, Number 728 memo2 is $s2. 729/memo2 is $s3, and 255/memo1 is $s4.")
	public static NpcStringId USER_CONNECTED_EVENT_OCCURRENCE_SUCCESS_SIEGE_ID_IS_S1_NUMBER_728_MEMO2_IS_S2_729_MEMO2_IS_S3_AND_255_MEMO1_IS_S4;
	
	@ClientString(id = 72905, message = "Territory Catapult dying event catapult's territory ID $s1, party status $s2.")
	public static NpcStringId TERRITORY_CATAPULT_DYING_EVENT_CATAPULT_S_TERRITORY_ID_S1_PARTY_STATUS_S2;
	
	@ClientString(id = 72951, message = "Protect the catapult of Gludio!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_GLUDIO;
	
	@ClientString(id = 72952, message = "Protect the catapult of Dion!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_DION;
	
	@ClientString(id = 72953, message = "Protect the catapult of Giran!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_GIRAN;
	
	@ClientString(id = 72954, message = "Protect the catapult of Oren!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_OREN;
	
	@ClientString(id = 72955, message = "Protect the catapult of Aden!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_ADEN;
	
	@ClientString(id = 72956, message = "Protect the catapult of Innadril!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_INNADRIL;
	
	@ClientString(id = 72957, message = "Protect the catapult of Goddard!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_GODDARD;
	
	@ClientString(id = 72958, message = "Protect the catapult of Rune!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_RUNE;
	
	@ClientString(id = 72959, message = "Protect the catapult of Schuttgart!")
	public static NpcStringId PROTECT_THE_CATAPULT_OF_SCHUTTGART;
	
	@ClientString(id = 72961, message = "The catapult of Gludio has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_GLUDIO_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72962, message = "The catapult of Dion has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_DION_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72963, message = "The catapult of Giran has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_GIRAN_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72964, message = "The catapult of Oren has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_OREN_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72965, message = "The catapult of Aden has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_ADEN_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72966, message = "The catapult of Innadril has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_INNADRIL_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72967, message = "The catapult of Goddard has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_GODDARD_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72968, message = "The catapult of Rune has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_RUNE_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72969, message = "The catapult of Schuttgart has been destroyed!")
	public static NpcStringId THE_CATAPULT_OF_SCHUTTGART_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 72981, message = "Gludio")
	public static NpcStringId GLUDIO;
	
	@ClientString(id = 72982, message = "Dion")
	public static NpcStringId DION;
	
	@ClientString(id = 72983, message = "Giran")
	public static NpcStringId GIRAN;
	
	@ClientString(id = 72984, message = "Oren")
	public static NpcStringId OREN;
	
	@ClientString(id = 72985, message = "Aden")
	public static NpcStringId ADEN;
	
	@ClientString(id = 72986, message = "Innadril")
	public static NpcStringId INNADRIL;
	
	@ClientString(id = 72987, message = "Goddard")
	public static NpcStringId GODDARD;
	
	@ClientString(id = 72988, message = "Rune")
	public static NpcStringId RUNE;
	
	@ClientString(id = 72989, message = "Schuttgart")
	public static NpcStringId SCHUTTGART;
	
	@ClientString(id = 73051, message = "Protect the supplies safe of Gludio!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GLUDIO;
	
	@ClientString(id = 73052, message = "Protect the supplies safe of Dion!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_DION;
	
	@ClientString(id = 73053, message = "Protect the supplies safe of Giran!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GIRAN;
	
	@ClientString(id = 73054, message = "Protect the supplies safe of Oren!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_OREN;
	
	@ClientString(id = 73055, message = "Protect the supplies safe of Aden!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_ADEN;
	
	@ClientString(id = 73056, message = "Protect the supplies safe of Innadril!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_INNADRIL;
	
	@ClientString(id = 73057, message = "Protect the supplies safe of Goddard!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GODDARD;
	
	@ClientString(id = 73058, message = "Protect the supplies safe of Rune!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_RUNE;
	
	@ClientString(id = 73059, message = "Protect the supplies safe of Schuttgart!")
	public static NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_SCHUTTGART;
	
	@ClientString(id = 73061, message = "The supplies safe of Gludio has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_GLUDIO_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73062, message = "The supplies safe of Dion has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_DION_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73063, message = "The supplies safe of Giran has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_GIRAN_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73064, message = "The supplies safe of Oren has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_OREN_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73065, message = "The supplies safe of Aden has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_ADEN_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73066, message = "The supplies safe of Innadril has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_INNADRIL_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73067, message = "The supplies safe of Goddard has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_GODDARD_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73068, message = "The supplies safe of Rune has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_RUNE_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73069, message = "The supplies safe of Schuttgart has been destroyed!")
	public static NpcStringId THE_SUPPLIES_SAFE_OF_SCHUTTGART_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 73101, message = "Protect the Military Association Leader")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER;
	
	@ClientString(id = 73102, message = "Protect the Military Association Leader (In Progress)")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_IN_PROGRESS;
	
	@ClientString(id = 73151, message = "Protect the Military Association Leader of Gludio!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GLUDIO;
	
	@ClientString(id = 73152, message = "Protect the Military Association Leader of Dion!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_DION;
	
	@ClientString(id = 73153, message = "Protect the Military Association Leader of Giran!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GIRAN;
	
	@ClientString(id = 73154, message = "Protect the Military Association Leader of Oren!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_OREN;
	
	@ClientString(id = 73155, message = "Protect the Military Association Leader of Aden!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_ADEN;
	
	@ClientString(id = 73156, message = "Protect the Military Association Leader of Innadril!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_INNADRIL;
	
	@ClientString(id = 73157, message = "Protect the Military Association Leader of Goddard!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GODDARD;
	
	@ClientString(id = 73158, message = "Protect the Military Association Leader of Rune!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_RUNE;
	
	@ClientString(id = 73159, message = "Protect the Military Association Leader of Schuttgart!")
	public static NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_SCHUTTGART;
	
	@ClientString(id = 73161, message = "The Military Association Leader of Gludio is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;
	
	@ClientString(id = 73162, message = "The Military Association Leader of Dion is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_DION_IS_DEAD;
	
	@ClientString(id = 73163, message = "The Military Association Leader of Giran is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;
	
	@ClientString(id = 73164, message = "The Military Association Leader of Oren is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;
	
	@ClientString(id = 73165, message = "The Military Association Leader of Aden is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;
	
	@ClientString(id = 73166, message = "The Military Association Leader of Innadril is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;
	
	@ClientString(id = 73167, message = "The Military Association Leader of Goddard is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;
	
	@ClientString(id = 73168, message = "The Military Association Leader of Rune is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;
	
	@ClientString(id = 73169, message = "The Military Association Leader of Schuttgart is dead!")
	public static NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;
	
	@ClientString(id = 73201, message = "Protect the Religious Association Leader")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER;
	
	@ClientString(id = 73202, message = "Protect the Religious Association Leader (In Progress)")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_IN_PROGRESS;
	
	@ClientString(id = 73251, message = "Protect the Religious Association Leader of Gludio!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GLUDIO;
	
	@ClientString(id = 73252, message = "Protect the Religious Association Leader of Dion!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_DION;
	
	@ClientString(id = 73253, message = "Protect the Religious Association Leader of Giran!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GIRAN;
	
	@ClientString(id = 73254, message = "Protect the Religious Association Leader of Oren!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_OREN;
	
	@ClientString(id = 73255, message = "Protect the Religious Association Leader of Aden!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_ADEN;
	
	@ClientString(id = 73256, message = "Protect the Religious Association Leader of Innadril!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_INNADRIL;
	
	@ClientString(id = 73257, message = "Protect the Religious Association Leader of Goddard!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GODDARD;
	
	@ClientString(id = 73258, message = "Protect the Religious Association Leader of Rune!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_RUNE;
	
	@ClientString(id = 73259, message = "Protect the Religious Association Leader of Schuttgart!")
	public static NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_SCHUTTGART;
	
	@ClientString(id = 73261, message = "The Religious Association Leader of Gludio is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;
	
	@ClientString(id = 73262, message = "The Religious Association Leader of Dion is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_DION_IS_DEAD;
	
	@ClientString(id = 73263, message = "The Religious Association Leader of Giran is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;
	
	@ClientString(id = 73264, message = "The Religious Association Leader of Oren is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;
	
	@ClientString(id = 73265, message = "The Religious Association Leader of Aden is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;
	
	@ClientString(id = 73266, message = "The Religious Association Leader of Innadril is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;
	
	@ClientString(id = 73267, message = "The Religious Association Leader of Goddard is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;
	
	@ClientString(id = 73268, message = "The Religious Association Leader of Rune is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;
	
	@ClientString(id = 73269, message = "The Religious Association Leader of Schuttgart is dead!")
	public static NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;
	
	@ClientString(id = 73301, message = "Protect the Economic Association Leader")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER;
	
	@ClientString(id = 73302, message = "Protect the Economic Association Leader (In Progress)")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_IN_PROGRESS;
	
	@ClientString(id = 73351, message = "Protect the Economic Association Leader of Gludio!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GLUDIO;
	
	@ClientString(id = 73352, message = "Protect the Economic Association Leader of Dion!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_DION;
	
	@ClientString(id = 73353, message = "Protect the Economic Association Leader of Giran!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GIRAN;
	
	@ClientString(id = 73354, message = "Protect the Economic Association Leader of Oren!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_OREN;
	
	@ClientString(id = 73355, message = "Protect the Economic Association Leader of Aden!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_ADEN;
	
	@ClientString(id = 73356, message = "Protect the Economic Association Leader of Innadril!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_INNADRIL;
	
	@ClientString(id = 73357, message = "Protect the Economic Association Leader of Goddard!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GODDARD;
	
	@ClientString(id = 73358, message = "Protect the Economic Association Leader of Rune!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_RUNE;
	
	@ClientString(id = 73359, message = "Protect the Economic Association Leader of Schuttgart!")
	public static NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_SCHUTTGART;
	
	@ClientString(id = 73361, message = "The Economic Association Leader of Gludio is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;
	
	@ClientString(id = 73362, message = "The Economic Association Leader of Dion is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_DION_IS_DEAD;
	
	@ClientString(id = 73363, message = "The Economic Association Leader of Giran is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;
	
	@ClientString(id = 73364, message = "The Economic Association Leader of Oren is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;
	
	@ClientString(id = 73365, message = "The Economic Association Leader of Aden is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;
	
	@ClientString(id = 73366, message = "The Economic Association Leader of Innadril is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;
	
	@ClientString(id = 73367, message = "The Economic Association Leader of Goddard is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;
	
	@ClientString(id = 73368, message = "The Economic Association Leader of Rune is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;
	
	@ClientString(id = 73369, message = "The Economic Association Leader of Schuttgart is dead!")
	public static NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;
	
	@ClientString(id = 73451, message = "Defeat $s1 enemy knights!")
	public static NpcStringId DEFEAT_S1_ENEMY_KNIGHTS;
	
	@ClientString(id = 73461, message = "You have defeated $s2 of $s1 knights.")
	public static NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_KNIGHTS;
	
	@ClientString(id = 73462, message = "You weakened the enemy's defense!")
	public static NpcStringId YOU_WEAKENED_THE_ENEMY_S_DEFENSE;
	
	@ClientString(id = 73551, message = "Defeat $s1 warriors and rogues!")
	public static NpcStringId DEFEAT_S1_WARRIORS_AND_ROGUES;
	
	@ClientString(id = 73561, message = "You have defeated $s2 of $s1 warriors and rogues.")
	public static NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_WARRIORS_AND_ROGUES;
	
	@ClientString(id = 73562, message = "You weakened the enemy's attack!")
	public static NpcStringId YOU_WEAKENED_THE_ENEMY_S_ATTACK;
	
	@ClientString(id = 73651, message = "Defeat $s1 wizards and summoners!")
	public static NpcStringId DEFEAT_S1_WIZARDS_AND_SUMMONERS;
	
	@ClientString(id = 73661, message = "You have defeated $s2 of $s1 enemies.")
	public static NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_ENEMIES;
	
	@ClientString(id = 73662, message = "You weakened the enemy's magic!")
	public static NpcStringId YOU_WEAKENED_THE_ENEMY_S_MAGIC;
	
	@ClientString(id = 73751, message = "Defeat $s1 healers and buffers.")
	public static NpcStringId DEFEAT_S1_HEALERS_AND_BUFFERS;
	
	@ClientString(id = 73761, message = "You have defeated $s2 of $s1 healers and buffers.")
	public static NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_HEALERS_AND_BUFFERS;
	
	@ClientString(id = 73762, message = "You have weakened the enemy's support!")
	public static NpcStringId YOU_HAVE_WEAKENED_THE_ENEMY_S_SUPPORT;
	
	@ClientString(id = 73851, message = "Defeat $s1 warsmiths and overlords.")
	public static NpcStringId DEFEAT_S1_WARSMITHS_AND_OVERLORDS;
	
	@ClientString(id = 73861, message = "You have defeated $s2 of $s1 warsmiths and overlords.")
	public static NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_WARSMITHS_AND_OVERLORDS;
	
	@ClientString(id = 73862, message = "You destroyed the enemy's professionals!")
	public static NpcStringId YOU_DESTROYED_THE_ENEMY_S_PROFESSIONALS;
	
	@ClientString(id = 90101, message = "How a Lavasaurus is Made")
	public static NpcStringId HOW_A_LAVASAURUS_IS_MADE;
	
	@ClientString(id = 90102, message = "How a Lavasaurus is Made (In Progress)")
	public static NpcStringId HOW_A_LAVASAURUS_IS_MADE_IN_PROGRESS;
	
	@ClientString(id = 90103, message = "How a Lavasaurus is Made (Done)")
	public static NpcStringId HOW_A_LAVASAURUS_IS_MADE_DONE;
	
	@ClientString(id = 90201, message = "Reclaim Our Era")
	public static NpcStringId RECLAIM_OUR_ERA;
	
	@ClientString(id = 90202, message = "Reclaim Our Era (In Progress)")
	public static NpcStringId RECLAIM_OUR_ERA_IN_PROGRESS;
	
	@ClientString(id = 90203, message = "Reclaim Our Era (Done)")
	public static NpcStringId RECLAIM_OUR_ERA_DONE;
	
	@ClientString(id = 90301, message = "The Call of Antharas")
	public static NpcStringId THE_CALL_OF_ANTHARAS;
	
	@ClientString(id = 90302, message = "The Call of Antharas (In Progress)")
	public static NpcStringId THE_CALL_OF_ANTHARAS_IN_PROGRESS;
	
	@ClientString(id = 90303, message = "The Call of Antharas (Done)")
	public static NpcStringId THE_CALL_OF_ANTHARAS_DONE;
	
	@ClientString(id = 90401, message = "Dragon Trophy - Antharas")
	public static NpcStringId DRAGON_TROPHY_ANTHARAS;
	
	@ClientString(id = 90402, message = "Dragon Trophy - Antharas (In Progress)")
	public static NpcStringId DRAGON_TROPHY_ANTHARAS_IN_PROGRESS;
	
	@ClientString(id = 90403, message = "Dragon Trophy - Antharas (Done)")
	public static NpcStringId DRAGON_TROPHY_ANTHARAS_DONE;
	
	@ClientString(id = 90501, message = "Refined Dragon Blood")
	public static NpcStringId REFINED_DRAGON_BLOOD;
	
	@ClientString(id = 90502, message = "Refined Dragon Blood (In Progress)")
	public static NpcStringId REFINED_DRAGON_BLOOD_IN_PROGRESS;
	
	@ClientString(id = 90503, message = "Refined Dragon Blood (Done)")
	public static NpcStringId REFINED_DRAGON_BLOOD_DONE;
	
	@ClientString(id = 90601, message = "The Call of Valakas")
	public static NpcStringId THE_CALL_OF_VALAKAS;
	
	@ClientString(id = 90602, message = "The Call of Valakas (In Progress)")
	public static NpcStringId THE_CALL_OF_VALAKAS_IN_PROGRESS;
	
	@ClientString(id = 90603, message = "The Call of Valakas (Done)")
	public static NpcStringId THE_CALL_OF_VALAKAS_DONE;
	
	@ClientString(id = 90701, message = "Dragon Trophy - Valakas")
	public static NpcStringId DRAGON_TROPHY_VALAKAS;
	
	@ClientString(id = 90702, message = "Dragon Trophy - Valakas (In Progress)")
	public static NpcStringId DRAGON_TROPHY_VALAKAS_IN_PROGRESS;
	
	@ClientString(id = 90703, message = "Dragon Trophy - Valakas (Done)")
	public static NpcStringId DRAGON_TROPHY_VALAKAS_DONE;
	
	@ClientString(id = 99601, message = "Tra la la... Today, I'm going to make another fun-filled trip. I wonder what I should look for this time...")
	public static NpcStringId TRA_LA_LA_TODAY_I_M_GOING_TO_MAKE_ANOTHER_FUN_FILLED_TRIP_I_WONDER_WHAT_I_SHOULD_LOOK_FOR_THIS_TIME;
	
	@ClientString(id = 99700, message = "What's this? Why am I being disturbed?")
	public static NpcStringId WHAT_S_THIS_WHY_AM_I_BEING_DISTURBED;
	
	@ClientString(id = 99701, message = "Ta-da! Here I am!")
	public static NpcStringId TA_DA_HERE_I_AM;
	
	@ClientString(id = 99702, message = "What are you looking at?")
	public static NpcStringId WHAT_ARE_YOU_LOOKING_AT;
	
	@ClientString(id = 99703, message = "If you give me nectar, this little squash will grow up quickly!")
	public static NpcStringId IF_YOU_GIVE_ME_NECTAR_THIS_LITTLE_SQUASH_WILL_GROW_UP_QUICKLY;
	
	@ClientString(id = 99704, message = "Are you my mommy?")
	public static NpcStringId ARE_YOU_MY_MOMMY;
	
	@ClientString(id = 99705, message = "Fancy meeting you here!")
	public static NpcStringId FANCY_MEETING_YOU_HERE;
	
	@ClientString(id = 99706, message = "Are you afraid of the big-bad squash?")
	public static NpcStringId ARE_YOU_AFRAID_OF_THE_BIG_BAD_SQUASH;
	
	@ClientString(id = 99707, message = "Impressive, aren't I?")
	public static NpcStringId IMPRESSIVE_AREN_T_I;
	
	@ClientString(id = 99708, message = "Obey me!!")
	public static NpcStringId OBEY_ME;
	
	@ClientString(id = 99709, message = "Raise me well and you'll be rewarded. Neglect me and suffer the consequences!")
	public static NpcStringId RAISE_ME_WELL_AND_YOU_LL_BE_REWARDED_NEGLECT_ME_AND_SUFFER_THE_CONSEQUENCES;
	
	@ClientString(id = 99710, message = "Transform!")
	public static NpcStringId TRANSFORM;
	
	@ClientString(id = 99711, message = "I feel different?")
	public static NpcStringId I_FEEL_DIFFERENT;
	
	@ClientString(id = 99712, message = "I'm bigger now! Bring it on!")
	public static NpcStringId I_M_BIGGER_NOW_BRING_IT_ON;
	
	@ClientString(id = 99713, message = "I'm not a kid anymore!")
	public static NpcStringId I_M_NOT_A_KID_ANYMORE;
	
	@ClientString(id = 99714, message = "Big time!")
	public static NpcStringId BIG_TIME;
	
	@ClientString(id = 99715, message = "Good luck!")
	public static NpcStringId GOOD_LUCK_2;
	
	@ClientString(id = 99716, message = "I'm all grown up now!")
	public static NpcStringId I_M_ALL_GROWN_UP_NOW;
	
	@ClientString(id = 99717, message = "If you let me go, I'll be your best friend.")
	public static NpcStringId IF_YOU_LET_ME_GO_I_LL_BE_YOUR_BEST_FRIEND;
	
	@ClientString(id = 99718, message = "I'm chuck full of goodness!")
	public static NpcStringId I_M_CHUCK_FULL_OF_GOODNESS;
	
	@ClientString(id = 99719, message = "Good job! Now what are you going to do?")
	public static NpcStringId GOOD_JOB_NOW_WHAT_ARE_YOU_GOING_TO_DO;
	
	@ClientString(id = 99720, message = "Keep it coming!")
	public static NpcStringId KEEP_IT_COMING;
	
	@ClientString(id = 99721, message = "That's what I'm talking about!")
	public static NpcStringId THAT_S_WHAT_I_M_TALKING_ABOUT;
	
	@ClientString(id = 99722, message = "May I have some more?")
	public static NpcStringId MAY_I_HAVE_SOME_MORE;
	
	@ClientString(id = 99723, message = "That hit the spot!")
	public static NpcStringId THAT_HIT_THE_SPOT;
	
	@ClientString(id = 99724, message = "I feel special!")
	public static NpcStringId I_FEEL_SPECIAL;
	
	@ClientString(id = 99725, message = "I think it's working!")
	public static NpcStringId I_THINK_IT_S_WORKING;
	
	@ClientString(id = 99726, message = "You DO understand!")
	public static NpcStringId YOU_DO_UNDERSTAND;
	
	@ClientString(id = 99727, message = "Yuck! What is this? Ha Ha just kidding!")
	public static NpcStringId YUCK_WHAT_IS_THIS_HA_HA_JUST_KIDDING;
	
	@ClientString(id = 99728, message = "A total of five and I'll be twice as alive!")
	public static NpcStringId A_TOTAL_OF_FIVE_AND_I_LL_BE_TWICE_AS_ALIVE;
	
	@ClientString(id = 99729, message = "Nectar is sublime!")
	public static NpcStringId NECTAR_IS_SUBLIME;
	
	@ClientString(id = 99730, message = "You call that a hit?")
	public static NpcStringId YOU_CALL_THAT_A_HIT;
	
	@ClientString(id = 99731, message = "Why are you hitting me? Ouch, stop it! Give me nectar!")
	public static NpcStringId WHY_ARE_YOU_HITTING_ME_OUCH_STOP_IT_GIVE_ME_NECTAR;
	
	@ClientString(id = 99732, message = "Stop or I'll wilt!")
	public static NpcStringId STOP_OR_I_LL_WILT;
	
	@ClientString(id = 99733, message = "I'm not fully grown yet! Oh well, do what you will. I'll fade away without nectar anyway!")
	public static NpcStringId I_M_NOT_FULLY_GROWN_YET_OH_WELL_DO_WHAT_YOU_WILL_I_LL_FADE_AWAY_WITHOUT_NECTAR_ANYWAY;
	
	@ClientString(id = 99734, message = "Go ahead and hit me again. It won't do you any good!")
	public static NpcStringId GO_AHEAD_AND_HIT_ME_AGAIN_IT_WON_T_DO_YOU_ANY_GOOD;
	
	@ClientString(id = 99735, message = "Woe is me! I'm wilting!")
	public static NpcStringId WOE_IS_ME_I_M_WILTING;
	
	@ClientString(id = 99736, message = "I'm not fully grown yet! How about some nectar to ease my pain?")
	public static NpcStringId I_M_NOT_FULLY_GROWN_YET_HOW_ABOUT_SOME_NECTAR_TO_EASE_MY_PAIN;
	
	@ClientString(id = 99737, message = "The end is near!")
	public static NpcStringId THE_END_IS_NEAR;
	
	@ClientString(id = 99738, message = "Pretty please... with sugar on top, give me some nectar!")
	public static NpcStringId PRETTY_PLEASE_WITH_SUGAR_ON_TOP_GIVE_ME_SOME_NECTAR;
	
	@ClientString(id = 99739, message = "If I die without nectar, you'll get nothing.")
	public static NpcStringId IF_I_DIE_WITHOUT_NECTAR_YOU_LL_GET_NOTHING;
	
	@ClientString(id = 99740, message = "I'm feeling better! Another thirty seconds and I'll be out of here!")
	public static NpcStringId I_M_FEELING_BETTER_ANOTHER_THIRTY_SECONDS_AND_I_LL_BE_OUT_OF_HERE;
	
	@ClientString(id = 99741, message = "Twenty seconds and it's ciao, baby!")
	public static NpcStringId TWENTY_SECONDS_AND_IT_S_CIAO_BABY;
	
	@ClientString(id = 99742, message = "Woohoo, just ten seconds left! nine... eight... seven!")
	public static NpcStringId WOOHOO_JUST_TEN_SECONDS_LEFT_NINE_EIGHT_SEVEN;
	
	@ClientString(id = 99743, message = "Give me nectar or I'll be gone in two minutes!")
	public static NpcStringId GIVE_ME_NECTAR_OR_I_LL_BE_GONE_IN_TWO_MINUTES;
	
	@ClientString(id = 99744, message = "Give me nectar or I'll be gone in one minute!")
	public static NpcStringId GIVE_ME_NECTAR_OR_I_LL_BE_GONE_IN_ONE_MINUTE;
	
	@ClientString(id = 99745, message = "So long suckers!")
	public static NpcStringId SO_LONG_SUCKERS;
	
	@ClientString(id = 99746, message = "I'm out of here!")
	public static NpcStringId I_M_OUT_OF_HERE;
	
	@ClientString(id = 99747, message = "I must be going! Have fun everybody!")
	public static NpcStringId I_MUST_BE_GOING_HAVE_FUN_EVERYBODY;
	
	@ClientString(id = 99748, message = "Time is up! Put your weapons down!")
	public static NpcStringId TIME_IS_UP_PUT_YOUR_WEAPONS_DOWN;
	
	@ClientString(id = 99749, message = "Good for me, bad for you!")
	public static NpcStringId GOOD_FOR_ME_BAD_FOR_YOU;
	
	@ClientString(id = 99750, message = "Soundtastic!")
	public static NpcStringId SOUNDTASTIC;
	
	@ClientString(id = 99751, message = "I can sing along if you like?")
	public static NpcStringId I_CAN_SING_ALONG_IF_YOU_LIKE;
	
	@ClientString(id = 99752, message = "I think you need some backup!")
	public static NpcStringId I_THINK_YOU_NEED_SOME_BACKUP;
	
	@ClientString(id = 99753, message = "Keep up that rhythm and you'll be a star!")
	public static NpcStringId KEEP_UP_THAT_RHYTHM_AND_YOU_LL_BE_A_STAR;
	
	@ClientString(id = 99754, message = "My heart yearns for more music.")
	public static NpcStringId MY_HEART_YEARNS_FOR_MORE_MUSIC;
	
	@ClientString(id = 99755, message = "You're out of tune again!")
	public static NpcStringId YOU_RE_OUT_OF_TUNE_AGAIN;
	
	@ClientString(id = 99756, message = "This is awful!")
	public static NpcStringId THIS_IS_AWFUL;
	
	@ClientString(id = 99757, message = "I think I broke something!")
	public static NpcStringId I_THINK_I_BROKE_SOMETHING;
	
	@ClientString(id = 99758, message = "What a lovely melody! Play it again!")
	public static NpcStringId WHAT_A_LOVELY_MELODY_PLAY_IT_AGAIN;
	
	@ClientString(id = 99759, message = "Music to my, uh... ears!")
	public static NpcStringId MUSIC_TO_MY_UH_EARS;
	
	@ClientString(id = 99760, message = "You need music lessons!")
	public static NpcStringId YOU_NEED_MUSIC_LESSONS;
	
	@ClientString(id = 99761, message = "I can't hear you!")
	public static NpcStringId I_CAN_T_HEAR_YOU;
	
	@ClientString(id = 99762, message = "You can't hurt me like that!")
	public static NpcStringId YOU_CAN_T_HURT_ME_LIKE_THAT;
	
	@ClientString(id = 99763, message = "I'm stronger than you are!")
	public static NpcStringId I_M_STRONGER_THAN_YOU_ARE;
	
	@ClientString(id = 99764, message = "No music? I'm out of here!")
	public static NpcStringId NO_MUSIC_I_M_OUT_OF_HERE;
	
	@ClientString(id = 99765, message = "That racket is getting on my nerves! Tone it down a bit!")
	public static NpcStringId THAT_RACKET_IS_GETTING_ON_MY_NERVES_TONE_IT_DOWN_A_BIT;
	
	@ClientString(id = 99766, message = "You can only hurt me through music!")
	public static NpcStringId YOU_CAN_ONLY_HURT_ME_THROUGH_MUSIC;
	
	@ClientString(id = 99767, message = "Only musical instruments can hurt me! Nothing else!")
	public static NpcStringId ONLY_MUSICAL_INSTRUMENTS_CAN_HURT_ME_NOTHING_ELSE;
	
	@ClientString(id = 99768, message = "Your skills are impressive, but sadly, useless...")
	public static NpcStringId YOUR_SKILLS_ARE_IMPRESSIVE_BUT_SADLY_USELESS;
	
	@ClientString(id = 99769, message = "Catch a Chrono for me please.")
	public static NpcStringId CATCH_A_CHRONO_FOR_ME_PLEASE;
	
	@ClientString(id = 99770, message = "You got me!")
	public static NpcStringId YOU_GOT_ME;
	
	@ClientString(id = 99771, message = "Now look at what you've done!")
	public static NpcStringId NOW_LOOK_AT_WHAT_YOU_VE_DONE;
	
	@ClientString(id = 99772, message = "You win!")
	public static NpcStringId YOU_WIN;
	
	@ClientString(id = 99773, message = "Squashed......")
	public static NpcStringId SQUASHED;
	
	@ClientString(id = 99774, message = "Don't tell anyone!")
	public static NpcStringId DON_T_TELL_ANYONE;
	
	@ClientString(id = 99775, message = "Gross! My guts are coming out!")
	public static NpcStringId GROSS_MY_GUTS_ARE_COMING_OUT;
	
	@ClientString(id = 99776, message = "Take it and go.")
	public static NpcStringId TAKE_IT_AND_GO;
	
	@ClientString(id = 99777, message = "I should've left when I could!")
	public static NpcStringId I_SHOULD_VE_LEFT_WHEN_I_COULD;
	
	@ClientString(id = 99778, message = "Now look what you have done!")
	public static NpcStringId NOW_LOOK_WHAT_YOU_HAVE_DONE;
	
	@ClientString(id = 99779, message = "I feel dirty!")
	public static NpcStringId I_FEEL_DIRTY;
	
	@ClientString(id = 99780, message = "Better luck next time!")
	public static NpcStringId BETTER_LUCK_NEXT_TIME;
	
	@ClientString(id = 99781, message = "Nice shot!")
	public static NpcStringId NICE_SHOT;
	
	@ClientString(id = 99782, message = "I'm not afraid of you!")
	public static NpcStringId I_M_NOT_AFRAID_OF_YOU;
	
	@ClientString(id = 99783, message = "If I knew this was going to happen, I would have stayed home!")
	public static NpcStringId IF_I_KNEW_THIS_WAS_GOING_TO_HAPPEN_I_WOULD_HAVE_STAYED_HOME;
	
	@ClientString(id = 99784, message = "Try harder or I'm out of here!")
	public static NpcStringId TRY_HARDER_OR_I_M_OUT_OF_HERE;
	
	@ClientString(id = 99785, message = "I'm tougher than I look!")
	public static NpcStringId I_M_TOUGHER_THAN_I_LOOK;
	
	@ClientString(id = 99786, message = "Good strike!")
	public static NpcStringId GOOD_STRIKE;
	
	@ClientString(id = 99787, message = "Oh my gourd!")
	public static NpcStringId OH_MY_GOURD;
	
	@ClientString(id = 99788, message = "That's all you've got?")
	public static NpcStringId THAT_S_ALL_YOU_VE_GOT;
	
	@ClientString(id = 99789, message = "Why me?")
	public static NpcStringId WHY_ME;
	
	@ClientString(id = 99790, message = "Bring me nectar!")
	public static NpcStringId BRING_ME_NECTAR;
	
	@ClientString(id = 99791, message = "I must have nectar to grow!")
	public static NpcStringId I_MUST_HAVE_NECTAR_TO_GROW;
	
	@ClientString(id = 99792, message = "Give me some nectar quickly or you'll get nothing!")
	public static NpcStringId GIVE_ME_SOME_NECTAR_QUICKLY_OR_YOU_LL_GET_NOTHING;
	
	@ClientString(id = 99793, message = "Please give me some nectar! I'm hungry!")
	public static NpcStringId PLEASE_GIVE_ME_SOME_NECTAR_I_M_HUNGRY;
	
	@ClientString(id = 99794, message = "Nectar please!")
	public static NpcStringId NECTAR_PLEASE;
	
	@ClientString(id = 99795, message = "Nectar will make me grow quickly!")
	public static NpcStringId NECTAR_WILL_MAKE_ME_GROW_QUICKLY;
	
	@ClientString(id = 99796, message = "Don't you want a bigger squash? Give me some nectar and I'll grow much larger!")
	public static NpcStringId DON_T_YOU_WANT_A_BIGGER_SQUASH_GIVE_ME_SOME_NECTAR_AND_I_LL_GROW_MUCH_LARGER;
	
	@ClientString(id = 99797, message = "If you raise me well, you'll get prizes! Or not...")
	public static NpcStringId IF_YOU_RAISE_ME_WELL_YOU_LL_GET_PRIZES_OR_NOT;
	
	@ClientString(id = 99798, message = "You are here for the stuff, eh? Well it's mine, all mine!")
	public static NpcStringId YOU_ARE_HERE_FOR_THE_STUFF_EH_WELL_IT_S_MINE_ALL_MINE;
	
	@ClientString(id = 99799, message = "Trust me... give me nectar and I'll become a giant squash!")
	public static NpcStringId TRUST_ME_GIVE_ME_NECTAR_AND_I_LL_BECOME_A_GIANT_SQUASH;
	
	@ClientString(id = 526701, message = "Journey to Gracia")
	public static NpcStringId JOURNEY_TO_GRACIA;
	
	@ClientString(id = 526702, message = "Journey to Gracia (In Progress)")
	public static NpcStringId JOURNEY_TO_GRACIA_IN_PROGRESS;
	
	@ClientString(id = 526703, message = "Journey to Gracia (Done)")
	public static NpcStringId JOURNEY_TO_GRACIA_DONE;
	
	@ClientString(id = 526801, message = "To the Seed of Infinity")
	public static NpcStringId TO_THE_SEED_OF_INFINITY;
	
	@ClientString(id = 526802, message = "To the Seed of Infinity (In Progress)")
	public static NpcStringId TO_THE_SEED_OF_INFINITY_IN_PROGRESS;
	
	@ClientString(id = 526803, message = "To the Seed of Infinity (Done)")
	public static NpcStringId TO_THE_SEED_OF_INFINITY_DONE;
	
	@ClientString(id = 526901, message = "To the Seed of Destruction")
	public static NpcStringId TO_THE_SEED_OF_DESTRUCTION;
	
	@ClientString(id = 526902, message = "To the Seed of Destruction (In Progress)")
	public static NpcStringId TO_THE_SEED_OF_DESTRUCTION_IN_PROGRESS;
	
	@ClientString(id = 526903, message = "To the Seed of Destruction (Done)")
	public static NpcStringId TO_THE_SEED_OF_DESTRUCTION_DONE;
	
	@ClientString(id = 527001, message = "Birth of the Seed")
	public static NpcStringId BIRTH_OF_THE_SEED;
	
	@ClientString(id = 527002, message = "Birth of the Seed (In Progress)")
	public static NpcStringId BIRTH_OF_THE_SEED_IN_PROGRESS;
	
	@ClientString(id = 527003, message = "Birth of the Seed (Done)")
	public static NpcStringId BIRTH_OF_THE_SEED_DONE;
	
	@ClientString(id = 527101, message = "The Enveloping Darkness")
	public static NpcStringId THE_ENVELOPING_DARKNESS;
	
	@ClientString(id = 527102, message = "The Enveloping Darkness (In Progress)")
	public static NpcStringId THE_ENVELOPING_DARKNESS_IN_PROGRESS;
	
	@ClientString(id = 527103, message = "The Enveloping Darkness (Done)")
	public static NpcStringId THE_ENVELOPING_DARKNESS_DONE;
	
	@ClientString(id = 527201, message = "Light Fragment")
	public static NpcStringId LIGHT_FRAGMENT;
	
	@ClientString(id = 527202, message = "Light Fragment (In Progress)")
	public static NpcStringId LIGHT_FRAGMENT_IN_PROGRESS;
	
	@ClientString(id = 527203, message = "Light Fragment (Done)")
	public static NpcStringId LIGHT_FRAGMENT_DONE;
	
	@ClientString(id = 527301, message = "Good Day to Fly")
	public static NpcStringId GOOD_DAY_TO_FLY;
	
	@ClientString(id = 527302, message = "Good Day to Fly (In Progress)")
	public static NpcStringId GOOD_DAY_TO_FLY_IN_PROGRESS;
	
	@ClientString(id = 527303, message = "Good Day to Fly (Done)")
	public static NpcStringId GOOD_DAY_TO_FLY_DONE;
	
	@ClientString(id = 527401, message = "Collecting in the Air")
	public static NpcStringId COLLECTING_IN_THE_AIR;
	
	@ClientString(id = 527402, message = "Collecting in the Air (In Progress)")
	public static NpcStringId COLLECTING_IN_THE_AIR_IN_PROGRESS;
	
	@ClientString(id = 527403, message = "Collecting in the Air (Done)")
	public static NpcStringId COLLECTING_IN_THE_AIR_DONE;
	
	@ClientString(id = 527501, message = "Containing the Attribute Power")
	public static NpcStringId CONTAINING_THE_ATTRIBUTE_POWER;
	
	@ClientString(id = 527502, message = "Containing the Attribute Power (In Progress)")
	public static NpcStringId CONTAINING_THE_ATTRIBUTE_POWER_IN_PROGRESS;
	
	@ClientString(id = 527503, message = "Containing the Attribute Power (Done)")
	public static NpcStringId CONTAINING_THE_ATTRIBUTE_POWER_DONE;
	
	@ClientString(id = 527601, message = "Mutated Kaneus - Gludio")
	public static NpcStringId MUTATED_KANEUS_GLUDIO;
	
	@ClientString(id = 527602, message = "Mutated Kaneus - Gludio (In Progress)")
	public static NpcStringId MUTATED_KANEUS_GLUDIO_IN_PROGRESS;
	
	@ClientString(id = 527603, message = "Mutated Kaneus - Gludio (Done)")
	public static NpcStringId MUTATED_KANEUS_GLUDIO_DONE;
	
	@ClientString(id = 527701, message = "Mutated Kaneus - Dion")
	public static NpcStringId MUTATED_KANEUS_DION;
	
	@ClientString(id = 527702, message = "Mutated Kaneus - Dion (In Progress)")
	public static NpcStringId MUTATED_KANEUS_DION_IN_PROGRESS;
	
	@ClientString(id = 527703, message = "Mutated Kaneus - Dion (Done)")
	public static NpcStringId MUTATED_KANEUS_DION_DONE;
	
	@ClientString(id = 527801, message = "Mutated Kaneus - Heine")
	public static NpcStringId MUTATED_KANEUS_HEINE;
	
	@ClientString(id = 527802, message = "Mutated Kaneus - Heine (In Progress)")
	public static NpcStringId MUTATED_KANEUS_HEINE_IN_PROGRESS;
	
	@ClientString(id = 527803, message = "Mutated Kaneus - Heine (Done)")
	public static NpcStringId MUTATED_KANEUS_HEINE_DONE;
	
	@ClientString(id = 527901, message = "Mutated Kaneus - Oren")
	public static NpcStringId MUTATED_KANEUS_OREN;
	
	@ClientString(id = 527902, message = "Mutated Kaneus - Oren (In Progress)")
	public static NpcStringId MUTATED_KANEUS_OREN_IN_PROGRESS;
	
	@ClientString(id = 527903, message = "Mutated Kaneus - Oren (Done)")
	public static NpcStringId MUTATED_KANEUS_OREN_DONE;
	
	@ClientString(id = 528001, message = "Mutated Kaneus - Schuttgart")
	public static NpcStringId MUTATED_KANEUS_SCHUTTGART;
	
	@ClientString(id = 528002, message = "Mutated Kaneus - Schuttgart (In Progress)")
	public static NpcStringId MUTATED_KANEUS_SCHUTTGART_IN_PROGRESS;
	
	@ClientString(id = 528003, message = "Mutated Kaneus - Schuttgart (Done)")
	public static NpcStringId MUTATED_KANEUS_SCHUTTGART_DONE;
	
	@ClientString(id = 528101, message = "Mutated Kaneus - Rune")
	public static NpcStringId MUTATED_KANEUS_RUNE;
	
	@ClientString(id = 528102, message = "Mutated Kaneus - Rune (In Progress)")
	public static NpcStringId MUTATED_KANEUS_RUNE_IN_PROGRESS;
	
	@ClientString(id = 528103, message = "Mutated Kaneus - Rune (Done)")
	public static NpcStringId MUTATED_KANEUS_RUNE_DONE;
	
	@ClientString(id = 528201, message = "To the Seed of Annihilation")
	public static NpcStringId TO_THE_SEED_OF_ANNIHILATION;
	
	@ClientString(id = 528202, message = "To the Seed of Annihilation (In Progress)")
	public static NpcStringId TO_THE_SEED_OF_ANNIHILATION_IN_PROGRESS;
	
	@ClientString(id = 528203, message = "To the Seed of Annihilation (Done)")
	public static NpcStringId TO_THE_SEED_OF_ANNIHILATION_DONE;
	
	@ClientString(id = 528301, message = "Request of Ice Merchant")
	public static NpcStringId REQUEST_OF_ICE_MERCHANT;
	
	@ClientString(id = 528302, message = "Request of Ice Merchant (In Progress)")
	public static NpcStringId REQUEST_OF_ICE_MERCHANT_IN_PROGRESS;
	
	@ClientString(id = 528303, message = "Request of Ice Merchant (Done)")
	public static NpcStringId REQUEST_OF_ICE_MERCHANT_DONE;
	
	@ClientString(id = 528401, message = "Acquisition of Divine Sword")
	public static NpcStringId ACQUISITION_OF_DIVINE_SWORD;
	
	@ClientString(id = 528402, message = "Acquisition of Divine Sword (In Progress)")
	public static NpcStringId ACQUISITION_OF_DIVINE_SWORD_IN_PROGRESS;
	
	@ClientString(id = 528403, message = "Acquisition of Divine Sword (Done)")
	public static NpcStringId ACQUISITION_OF_DIVINE_SWORD_DONE;
	
	@ClientString(id = 528501, message = "Meeting Sirra")
	public static NpcStringId MEETING_SIRRA;
	
	@ClientString(id = 528502, message = "Meeting Sirra (In Progress)")
	public static NpcStringId MEETING_SIRRA_IN_PROGRESS;
	
	@ClientString(id = 528503, message = "Meeting Sirra (Done)")
	public static NpcStringId MEETING_SIRRA_DONE;
	
	@ClientString(id = 528551, message = "There's nothing you can't say. I can't listen to you anymore!")
	public static NpcStringId THERE_S_NOTHING_YOU_CAN_T_SAY_I_CAN_T_LISTEN_TO_YOU_ANYMORE;
	
	@ClientString(id = 528601, message = "Reunion with Sirra")
	public static NpcStringId REUNION_WITH_SIRRA;
	
	@ClientString(id = 528602, message = "Reunion with Sirra (In Progress)")
	public static NpcStringId REUNION_WITH_SIRRA_IN_PROGRESS;
	
	@ClientString(id = 528603, message = "Reunion with Sirra (Done)")
	public static NpcStringId REUNION_WITH_SIRRA_DONE;
	
	@ClientString(id = 528651, message = "You advanced bravely but got such a tiny result. Hohoho.")
	public static NpcStringId YOU_ADVANCED_BRAVELY_BUT_GOT_SUCH_A_TINY_RESULT_HOHOHO;
	
	@ClientString(id = 528701, message = "Story of Those Left")
	public static NpcStringId STORY_OF_THOSE_LEFT;
	
	@ClientString(id = 528702, message = "Story of Those Left (In Progress)")
	public static NpcStringId STORY_OF_THOSE_LEFT_IN_PROGRESS;
	
	@ClientString(id = 528703, message = "Story of Those Left (Done)")
	public static NpcStringId STORY_OF_THOSE_LEFT_DONE;
	
	@ClientString(id = 528801, message = "Secret Mission")
	public static NpcStringId SECRET_MISSION;
	
	@ClientString(id = 528802, message = "Secret Mission (In Progress)")
	public static NpcStringId SECRET_MISSION_IN_PROGRESS;
	
	@ClientString(id = 528803, message = "Secret Mission (Done)")
	public static NpcStringId SECRET_MISSION_DONE;
	
	@ClientString(id = 528901, message = "Fade to Black")
	public static NpcStringId FADE_TO_BLACK;
	
	@ClientString(id = 528902, message = "Fade to Black (In Progress)")
	public static NpcStringId FADE_TO_BLACK_IN_PROGRESS;
	
	@ClientString(id = 528903, message = "Fade to Black (Done)")
	public static NpcStringId FADE_TO_BLACK_DONE;
	
	@ClientString(id = 529001, message = "Land Dragon Conqueror")
	public static NpcStringId LAND_DRAGON_CONQUEROR;
	
	@ClientString(id = 529002, message = "Land Dragon Conqueror (In Progress)")
	public static NpcStringId LAND_DRAGON_CONQUEROR_IN_PROGRESS;
	
	@ClientString(id = 529003, message = "Land Dragon Conqueror (Done)")
	public static NpcStringId LAND_DRAGON_CONQUEROR_DONE;
	
	@ClientString(id = 529101, message = "Fire Dragon Destroyer")
	public static NpcStringId FIRE_DRAGON_DESTROYER;
	
	@ClientString(id = 529102, message = "Fire Dragon Destroyer (In Progress)")
	public static NpcStringId FIRE_DRAGON_DESTROYER_IN_PROGRESS;
	
	@ClientString(id = 529103, message = "Fire Dragon Destroyer (Done)")
	public static NpcStringId FIRE_DRAGON_DESTROYER_DONE;
	
	@ClientString(id = 529201, message = "Seven Signs, Girl of Doubt")
	public static NpcStringId SEVEN_SIGNS_GIRL_OF_DOUBT;
	
	@ClientString(id = 529202, message = "Seven Signs, Girl of Doubt (In Progress)")
	public static NpcStringId SEVEN_SIGNS_GIRL_OF_DOUBT_IN_PROGRESS;
	
	@ClientString(id = 529203, message = "Seven Signs, Girl of Doubt (Done)")
	public static NpcStringId SEVEN_SIGNS_GIRL_OF_DOUBT_DONE;
	
	@ClientString(id = 529301, message = "Seven Signs, Forbidden Book of the Elmore-Aden Kingdom")
	public static NpcStringId SEVEN_SIGNS_FORBIDDEN_BOOK_OF_THE_ELMORE_ADEN_KINGDOM;
	
	@ClientString(id = 529302, message = "Seven Signs, Forbidden Book of the Elmore-Aden Kingdom (In Progress)")
	public static NpcStringId SEVEN_SIGNS_FORBIDDEN_BOOK_OF_THE_ELMORE_ADEN_KINGDOM_IN_PROGRESS;
	
	@ClientString(id = 529303, message = "Seven Signs, Forbidden Book of the Elmore-Aden Kingdom (Done)")
	public static NpcStringId SEVEN_SIGNS_FORBIDDEN_BOOK_OF_THE_ELMORE_ADEN_KINGDOM_DONE;
	
	@ClientString(id = 529401, message = "Seven Signs, To the Monastery of Silence")
	public static NpcStringId SEVEN_SIGNS_TO_THE_MONASTERY_OF_SILENCE;
	
	@ClientString(id = 529402, message = "Seven Signs, To the Monastery of Silence (In Progress)")
	public static NpcStringId SEVEN_SIGNS_TO_THE_MONASTERY_OF_SILENCE_IN_PROGRESS;
	
	@ClientString(id = 529403, message = "Seven Signs, To the Monastery of Silence (Done)")
	public static NpcStringId SEVEN_SIGNS_TO_THE_MONASTERY_OF_SILENCE_DONE;
	
	@ClientString(id = 529501, message = "Seven Signs, Solina Tomb")
	public static NpcStringId SEVEN_SIGNS_SOLINA_TOMB;
	
	@ClientString(id = 529502, message = "Seven Signs, Solina Tomb (In Progress)")
	public static NpcStringId SEVEN_SIGNS_SOLINA_TOMB_IN_PROGRESS;
	
	@ClientString(id = 529503, message = "Seven Signs, Solina Tomb (Done)")
	public static NpcStringId SEVEN_SIGNS_SOLINA_TOMB_DONE;
	
	@ClientString(id = 529601, message = "Seven Signs, One Who Seeks the Power of the Seal")
	public static NpcStringId SEVEN_SIGNS_ONE_WHO_SEEKS_THE_POWER_OF_THE_SEAL;
	
	@ClientString(id = 529602, message = "Seven Signs, One Who Seeks the Power of the Seal (In Progress)")
	public static NpcStringId SEVEN_SIGNS_ONE_WHO_SEEKS_THE_POWER_OF_THE_SEAL_IN_PROGRESS;
	
	@ClientString(id = 529603, message = "Seven Signs, One Who Seeks the Power of the Seal (Done)")
	public static NpcStringId SEVEN_SIGNS_ONE_WHO_SEEKS_THE_POWER_OF_THE_SEAL_DONE;
	
	@ClientString(id = 550101, message = "Cloak Embroidered with Souls - 1 (Zaken)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_1_ZAKEN;
	
	@ClientString(id = 550102, message = "Cloak Embroidered with Souls - 1 (Zaken) (In Progress)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_1_ZAKEN_IN_PROGRESS;
	
	@ClientString(id = 550103, message = "Cloak Embroidered with Souls - 1 (Zaken) (Complete)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_1_ZAKEN_COMPLETE;
	
	@ClientString(id = 550201, message = "Cloak Embroidered with Souls - 2 (Freya)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_2_FREYA;
	
	@ClientString(id = 550202, message = "Cloak Embroidered with Souls - 2 (Freya) (In Progress)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_2_FREYA_IN_PROGRESS;
	
	@ClientString(id = 550203, message = "Cloak Embroidered with Souls - 2 (Freya) (Complete)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_2_FREYA_COMPLETE;
	
	@ClientString(id = 550301, message = "Cloak Embroidered with Souls - 3 (Frintezza)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_3_FRINTEZZA;
	
	@ClientString(id = 550302, message = "Cloak Embroidered with Souls - 3 (Frintezza) (In Progress)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_3_FRINTEZZA_IN_PROGRESS;
	
	@ClientString(id = 550303, message = "Cloak Embroidered with Souls - 3 (Frintezza) (Complete)")
	public static NpcStringId CLOAK_EMBROIDERED_WITH_SOULS_3_FRINTEZZA_COMPLETE;
	
	@ClientString(id = 550401, message = "Jewel of Antharas")
	public static NpcStringId JEWEL_OF_ANTHARAS;
	
	@ClientString(id = 550402, message = "Jewel of Antharas (In Progress)")
	public static NpcStringId JEWEL_OF_ANTHARAS_IN_PROGRESS;
	
	@ClientString(id = 550403, message = "Jewel of Antharas (Done)")
	public static NpcStringId JEWEL_OF_ANTHARAS_DONE;
	
	@ClientString(id = 550501, message = "Jewel of Valakas")
	public static NpcStringId JEWEL_OF_VALAKAS;
	
	@ClientString(id = 550502, message = "Jewel of Valakas (In Progress)")
	public static NpcStringId JEWEL_OF_VALAKAS_IN_PROGRESS;
	
	@ClientString(id = 550503, message = "Jewel of Valakas (Done)")
	public static NpcStringId JEWEL_OF_VALAKAS_DONE;
	
	@ClientString(id = 1000001, message = "A non-permitted target has been discovered.")
	public static NpcStringId A_NON_PERMITTED_TARGET_HAS_BEEN_DISCOVERED;
	
	@ClientString(id = 1000002, message = "Intruder removal system initiated.")
	public static NpcStringId INTRUDER_REMOVAL_SYSTEM_INITIATED;
	
	@ClientString(id = 1000003, message = "Removing intruders.")
	public static NpcStringId REMOVING_INTRUDERS;
	
	@ClientString(id = 1000004, message = "A fatal error has occurred.")
	public static NpcStringId A_FATAL_ERROR_HAS_OCCURRED;
	
	@ClientString(id = 1000005, message = "System is being shut down...")
	public static NpcStringId SYSTEM_IS_BEING_SHUT_DOWN;
	
	@ClientString(id = 1000006, message = "......")
	public static NpcStringId EMPTY;
	
	@ClientString(id = 1000007, message = "We shall see about that!")
	public static NpcStringId WE_SHALL_SEE_ABOUT_THAT;
	
	@ClientString(id = 1000008, message = "I will definitely repay this humiliation!")
	public static NpcStringId I_WILL_DEFINITELY_REPAY_THIS_HUMILIATION;
	
	@ClientString(id = 1000009, message = "Retreat!")
	public static NpcStringId RETREAT;
	
	@ClientString(id = 1000010, message = "Tactical retreat!")
	public static NpcStringId TACTICAL_RETREAT;
	
	@ClientString(id = 1000011, message = "Mass fleeing!")
	public static NpcStringId MASS_FLEEING;
	
	@ClientString(id = 1000012, message = "It's stronger than expected!")
	public static NpcStringId IT_S_STRONGER_THAN_EXPECTED;
	
	@ClientString(id = 1000013, message = "I'll kill you next time!")
	public static NpcStringId I_LL_KILL_YOU_NEXT_TIME;
	
	@ClientString(id = 1000014, message = "I'll definitely kill you next time!")
	public static NpcStringId I_LL_DEFINITELY_KILL_YOU_NEXT_TIME;
	
	@ClientString(id = 1000015, message = "Oh! How strong!")
	public static NpcStringId OH_HOW_STRONG;
	
	@ClientString(id = 1000016, message = "Invader!")
	public static NpcStringId INVADER;
	
	@ClientString(id = 1000017, message = "There is no reason for you to kill me! I have nothing you need!")
	public static NpcStringId THERE_IS_NO_REASON_FOR_YOU_TO_KILL_ME_I_HAVE_NOTHING_YOU_NEED;
	
	@ClientString(id = 1000018, message = "Someday you will pay!")
	public static NpcStringId SOMEDAY_YOU_WILL_PAY;
	
	@ClientString(id = 1000019, message = "I won't just stand still while you hit me.")
	public static NpcStringId I_WON_T_JUST_STAND_STILL_WHILE_YOU_HIT_ME;
	
	@ClientString(id = 1000020, message = "Stop hitting!")
	public static NpcStringId STOP_HITTING;
	
	@ClientString(id = 1000021, message = "It hurts to the bone!")
	public static NpcStringId IT_HURTS_TO_THE_BONE;
	
	@ClientString(id = 1000022, message = "Am I the neighborhood drum for beating!")
	public static NpcStringId AM_I_THE_NEIGHBORHOOD_DRUM_FOR_BEATING;
	
	@ClientString(id = 1000023, message = "Follow me if you want!")
	public static NpcStringId FOLLOW_ME_IF_YOU_WANT;
	
	@ClientString(id = 1000024, message = "Surrender!")
	public static NpcStringId SURRENDER;
	
	@ClientString(id = 1000025, message = "Oh, I'm dead!")
	public static NpcStringId OH_I_M_DEAD;
	
	@ClientString(id = 1000026, message = "I'll be back!")
	public static NpcStringId I_LL_BE_BACK;
	
	@ClientString(id = 1000027, message = "I'll give you ten million arena if you let me live!")
	public static NpcStringId I_LL_GIVE_YOU_TEN_MILLION_ARENA_IF_YOU_LET_ME_LIVE;
	
	@ClientString(id = 1000028, message = "$s1. Stop kidding yourself about your own powerlessness!")
	public static NpcStringId S1_STOP_KIDDING_YOURSELF_ABOUT_YOUR_OWN_POWERLESSNESS;
	
	@ClientString(id = 1000029, message = "$s1. I'll make you feel what true fear is!")
	public static NpcStringId S1_I_LL_MAKE_YOU_FEEL_WHAT_TRUE_FEAR_IS;
	
	@ClientString(id = 1000030, message = "You're really stupid to have challenged me. $s1! Get ready!")
	public static NpcStringId YOU_RE_REALLY_STUPID_TO_HAVE_CHALLENGED_ME_S1_GET_READY;
	
	@ClientString(id = 1000031, message = "$s1. Do you think that's going to work?!")
	public static NpcStringId S1_DO_YOU_THINK_THAT_S_GOING_TO_WORK;
	
	@ClientString(id = 1000032, message = "I will definitely reclaim my honor which has been tarnished!")
	public static NpcStringId I_WILL_DEFINITELY_RECLAIM_MY_HONOR_WHICH_HAS_BEEN_TARNISHED;
	
	@ClientString(id = 1000033, message = "Show me the wrath of the knight whose honor has been downtrodden!")
	public static NpcStringId SHOW_ME_THE_WRATH_OF_THE_KNIGHT_WHOSE_HONOR_HAS_BEEN_DOWNTRODDEN;
	
	@ClientString(id = 1000034, message = "Death to the hypocrite!")
	public static NpcStringId DEATH_TO_THE_HYPOCRITE;
	
	@ClientString(id = 1000035, message = "I'll never sleep until I've shed my dishonor!")
	public static NpcStringId I_LL_NEVER_SLEEP_UNTIL_I_VE_SHED_MY_DISHONOR;
	
	@ClientString(id = 1000036, message = "I'm here for the ones that are cursing the world!")
	public static NpcStringId I_M_HERE_FOR_THE_ONES_THAT_ARE_CURSING_THE_WORLD;
	
	@ClientString(id = 1000037, message = "I'll turn you into a malignant spirit!")
	public static NpcStringId I_LL_TURN_YOU_INTO_A_MALIGNANT_SPIRIT;
	
	@ClientString(id = 1000038, message = "I'll curse you with the power of revenge and hate!")
	public static NpcStringId I_LL_CURSE_YOU_WITH_THE_POWER_OF_REVENGE_AND_HATE;
	
	@ClientString(id = 1000039, message = "For the glory of Gracia!")
	public static NpcStringId FOR_THE_GLORY_OF_GRACIA;
	
	@ClientString(id = 1000040, message = "Do you dare pit your power against me?")
	public static NpcStringId DO_YOU_DARE_PIT_YOUR_POWER_AGAINST_ME;
	
	@ClientString(id = 1000041, message = "I... I am defeated!!!")
	public static NpcStringId I_I_AM_DEFEATED;
	
	@ClientString(id = 1000042, message = "I am conveying the will of Nurka! Everybody get out of my way!")
	public static NpcStringId I_AM_CONVEYING_THE_WILL_OF_NURKA_EVERYBODY_GET_OUT_OF_MY_WAY;
	
	@ClientString(id = 1000043, message = "Those who stand against me shall die horribly!")
	public static NpcStringId THOSE_WHO_STAND_AGAINST_ME_SHALL_DIE_HORRIBLY;
	
	@ClientString(id = 1000044, message = "Do you dare to block my way?!")
	public static NpcStringId DO_YOU_DARE_TO_BLOCK_MY_WAY;
	
	@ClientString(id = 1000045, message = "My comrades will get revenge!")
	public static NpcStringId MY_COMRADES_WILL_GET_REVENGE;
	
	@ClientString(id = 1000046, message = "You heathen blasphemers of this holy place will be punished!")
	public static NpcStringId YOU_HEATHEN_BLASPHEMERS_OF_THIS_HOLY_PLACE_WILL_BE_PUNISHED;
	
	@ClientString(id = 1000047, message = "Step forward, you worthless creatures who challenge my authority!")
	public static NpcStringId STEP_FORWARD_YOU_WORTHLESS_CREATURES_WHO_CHALLENGE_MY_AUTHORITY;
	
	@ClientString(id = 1000048, message = "My creator... The unchanging faithfulness to my master... ")
	public static NpcStringId MY_CREATOR_THE_UNCHANGING_FAITHFULNESS_TO_MY_MASTER;
	
	@ClientString(id = 1000049, message = "Master of the tower... My master... master... Where is he?")
	public static NpcStringId MASTER_OF_THE_TOWER_MY_MASTER_MASTER_WHERE_IS_HE;
	
	@ClientString(id = 1000050, message = "I AM THE ONE CARRYING OUT THE WILL OF CORE.")
	public static NpcStringId I_AM_THE_ONE_CARRYING_OUT_THE_WILL_OF_CORE;
	
	@ClientString(id = 1000051, message = "DESTROY THE INVADER.")
	public static NpcStringId DESTROY_THE_INVADER;
	
	@ClientString(id = 1000052, message = "STRANGE CONDITION - DOESN'T WORK")
	public static NpcStringId STRANGE_CONDITION_DOESN_T_WORK;
	
	@ClientString(id = 1000053, message = "According to the command of Beleth... I'm going to observe you guys!")
	public static NpcStringId ACCORDING_TO_THE_COMMAND_OF_BELETH_I_M_GOING_TO_OBSERVE_YOU_GUYS;
	
	@ClientString(id = 1000054, message = "You people make me sick! No sense of loyalty whatsoever!")
	public static NpcStringId YOU_PEOPLE_MAKE_ME_SICK_NO_SENSE_OF_LOYALTY_WHATSOEVER;
	
	@ClientString(id = 1000055, message = "A challenge against me is the same as a challenge against Beleth...")
	public static NpcStringId A_CHALLENGE_AGAINST_ME_IS_THE_SAME_AS_A_CHALLENGE_AGAINST_BELETH;
	
	@ClientString(id = 1000056, message = "Beleth is always watching over you guys!")
	public static NpcStringId BELETH_IS_ALWAYS_WATCHING_OVER_YOU_GUYS;
	
	@ClientString(id = 1000057, message = "That was really close! Antharas opened its eyes!")
	public static NpcStringId THAT_WAS_REALLY_CLOSE_ANTHARAS_OPENED_ITS_EYES;
	
	@ClientString(id = 1000058, message = "You who disobey the will of Antharas! Die!")
	public static NpcStringId YOU_WHO_DISOBEY_THE_WILL_OF_ANTHARAS_DIE;
	
	@ClientString(id = 1000059, message = "Antharas has taken my life!")
	public static NpcStringId ANTHARAS_HAS_TAKEN_MY_LIFE;
	
	@ClientString(id = 1000060, message = "I crossed back over the marshlands of death to reclaim the treasure!")
	public static NpcStringId I_CROSSED_BACK_OVER_THE_MARSHLANDS_OF_DEATH_TO_RECLAIM_THE_TREASURE;
	
	@ClientString(id = 1000061, message = "Bring over and surrender your precious gold treasure to me!")
	public static NpcStringId BRING_OVER_AND_SURRENDER_YOUR_PRECIOUS_GOLD_TREASURE_TO_ME;
	
	@ClientString(id = 1000062, message = "I'll kill you in an instant!")
	public static NpcStringId I_LL_KILL_YOU_IN_AN_INSTANT;
	
	@ClientString(id = 1000063, message = "No! The treasure is still..!")
	public static NpcStringId NO_THE_TREASURE_IS_STILL;
	
	@ClientString(id = 1000064, message = "Invaders of Dragon Valley will never live to return!")
	public static NpcStringId INVADERS_OF_DRAGON_VALLEY_WILL_NEVER_LIVE_TO_RETURN;
	
	@ClientString(id = 1000065, message = "I am the guardian that honors the command of Antharas to watch over this place!")
	public static NpcStringId I_AM_THE_GUARDIAN_THAT_HONORS_THE_COMMAND_OF_ANTHARAS_TO_WATCH_OVER_THIS_PLACE;
	
	@ClientString(id = 1000066, message = "You've set foot in Dragon Valley without permission! The penalty is death!")
	public static NpcStringId YOU_VE_SET_FOOT_IN_DRAGON_VALLEY_WITHOUT_PERMISSION_THE_PENALTY_IS_DEATH;
	
	@ClientString(id = 1000067, message = "Antharas has taken my life!")
	public static NpcStringId ANTHARAS_HAS_TAKEN_MY_LIFE_2;
	
	@ClientString(id = 1000068, message = "The joy of killing! The ecstasy of looting! Hey guys, let's have a go at it again!")
	public static NpcStringId THE_JOY_OF_KILLING_THE_ECSTASY_OF_LOOTING_HEY_GUYS_LET_S_HAVE_A_GO_AT_IT_AGAIN;
	
	@ClientString(id = 1000069, message = "There really are still lots of folks in the world without fear! I'll teach you a lesson!")
	public static NpcStringId THERE_REALLY_ARE_STILL_LOTS_OF_FOLKS_IN_THE_WORLD_WITHOUT_FEAR_I_LL_TEACH_YOU_A_LESSON;
	
	@ClientString(id = 1000070, message = "If you hand over everything you've got, I'll at least spare your life!")
	public static NpcStringId IF_YOU_HAND_OVER_EVERYTHING_YOU_VE_GOT_I_LL_AT_LEAST_SPARE_YOUR_LIFE;
	
	@ClientString(id = 1000071, message = "Kneel down before one such as this!")
	public static NpcStringId KNEEL_DOWN_BEFORE_ONE_SUCH_AS_THIS;
	
	@ClientString(id = 1000072, message = "Honor the master's wishes and punish all the invaders!")
	public static NpcStringId HONOR_THE_MASTER_S_WISHES_AND_PUNISH_ALL_THE_INVADERS;
	
	@ClientString(id = 1000073, message = "Follow the master's wishes and punish the invaders!")
	public static NpcStringId FOLLOW_THE_MASTER_S_WISHES_AND_PUNISH_THE_INVADERS;
	
	@ClientString(id = 1000074, message = "Death is nothing more than a momentary rest...")
	public static NpcStringId DEATH_IS_NOTHING_MORE_THAN_A_MOMENTARY_REST;
	
	@ClientString(id = 1000075, message = "Listen! This is the end of the human era! Antharas has awoken!")
	public static NpcStringId LISTEN_THIS_IS_THE_END_OF_THE_HUMAN_ERA_ANTHARAS_HAS_AWOKEN;
	
	@ClientString(id = 1000076, message = "Present the lives of four people to Antharas!")
	public static NpcStringId PRESENT_THE_LIVES_OF_FOUR_PEOPLE_TO_ANTHARAS;
	
	@ClientString(id = 1000077, message = "This is unbelievable! How could I have lost to one so inferior to myself?")
	public static NpcStringId THIS_IS_UNBELIEVABLE_HOW_COULD_I_HAVE_LOST_TO_ONE_SO_INFERIOR_TO_MYSELF;
	
	@ClientString(id = 1000078, message = "I carry the power of darkness and have returned from the abyss.")
	public static NpcStringId I_CARRY_THE_POWER_OF_DARKNESS_AND_HAVE_RETURNED_FROM_THE_ABYSS;
	
	@ClientString(id = 1000079, message = "It's detestable.")
	public static NpcStringId IT_S_DETESTABLE;
	
	@ClientString(id = 1000080, message = "I finally find rest...")
	public static NpcStringId I_FINALLY_FIND_REST;
	
	@ClientString(id = 1000081, message = "Glory to Orfen!")
	public static NpcStringId GLORY_TO_ORFEN;
	
	@ClientString(id = 1000082, message = "In the name of Orfen, I can never forgive you who are invading this place!")
	public static NpcStringId IN_THE_NAME_OF_ORFEN_I_CAN_NEVER_FORGIVE_YOU_WHO_ARE_INVADING_THIS_PLACE;
	
	@ClientString(id = 1000083, message = "I'll make you pay the price for fearlessly entering Orfen's land!")
	public static NpcStringId I_LL_MAKE_YOU_PAY_THE_PRICE_FOR_FEARLESSLY_ENTERING_ORFEN_S_LAND;
	
	@ClientString(id = 1000084, message = "Even if you disappear into nothingness, you will still face the life-long suffering of the curse that I have given you.")
	public static NpcStringId EVEN_IF_YOU_DISAPPEAR_INTO_NOTHINGNESS_YOU_WILL_STILL_FACE_THE_LIFE_LONG_SUFFERING_OF_THE_CURSE_THAT_I_HAVE_GIVEN_YOU;
	
	@ClientString(id = 1000085, message = "I'll stand against anyone that makes light of the sacred place of the Elves!")
	public static NpcStringId I_LL_STAND_AGAINST_ANYONE_THAT_MAKES_LIGHT_OF_THE_SACRED_PLACE_OF_THE_ELVES;
	
	@ClientString(id = 1000086, message = "I will kill with my own hands anyone that defiles our home!")
	public static NpcStringId I_WILL_KILL_WITH_MY_OWN_HANDS_ANYONE_THAT_DEFILES_OUR_HOME;
	
	@ClientString(id = 1000087, message = "My brothers will never rest until we push you and your gang out of this valley!")
	public static NpcStringId MY_BROTHERS_WILL_NEVER_REST_UNTIL_WE_PUSH_YOU_AND_YOUR_GANG_OUT_OF_THIS_VALLEY;
	
	@ClientString(id = 1000088, message = "Until the day of destruction of Hestui!")
	public static NpcStringId UNTIL_THE_DAY_OF_DESTRUCTION_OF_HESTUI;
	
	@ClientString(id = 1000089, message = "If any intrepid Orcs remain, attack them!")
	public static NpcStringId IF_ANY_INTREPID_ORCS_REMAIN_ATTACK_THEM;
	
	@ClientString(id = 1000090, message = "I'll break your windpipe!")
	public static NpcStringId I_LL_BREAK_YOUR_WINDPIPE;
	
	@ClientString(id = 1000091, message = "Is revenge a failure?!")
	public static NpcStringId IS_REVENGE_A_FAILURE;
	
	@ClientString(id = 1000092, message = "The sparkling mithril of the dwarves and their pretty treasures! I'll get them all!")
	public static NpcStringId THE_SPARKLING_MITHRIL_OF_THE_DWARVES_AND_THEIR_PRETTY_TREASURES_I_LL_GET_THEM_ALL;
	
	@ClientString(id = 1000093, message = "Where are all the dreadful dwarves and their sparkling things?")
	public static NpcStringId WHERE_ARE_ALL_THE_DREADFUL_DWARVES_AND_THEIR_SPARKLING_THINGS;
	
	@ClientString(id = 1000094, message = "Hand over your pretty treasures!")
	public static NpcStringId HAND_OVER_YOUR_PRETTY_TREASURES;
	
	@ClientString(id = 1000095, message = "Hey! You should have run away!")
	public static NpcStringId HEY_YOU_SHOULD_HAVE_RUN_AWAY;
	
	@ClientString(id = 1000096, message = "DESTRUCTION - EXTINCTION - SLAUGHTER - COLLAPSE! DESTRUCTION - EXTINCTION - SLAUGHTER - COLLAPSE!")
	public static NpcStringId DESTRUCTION_EXTINCTION_SLAUGHTER_COLLAPSE_DESTRUCTION_EXTINCTION_SLAUGHTER_COLLAPSE;
	
	@ClientString(id = 1000097, message = "Destruction! Destruction! Destruction! Destruction!")
	public static NpcStringId DESTRUCTION_DESTRUCTION_DESTRUCTION_DESTRUCTION;
	
	@ClientString(id = 1000098, message = "Destruction! Destruction! Destruction. . .")
	public static NpcStringId DESTRUCTION_DESTRUCTION_DESTRUCTION;
	
	@ClientString(id = 1000099, message = "Ta-da! Uthanka has returned!")
	public static NpcStringId TA_DA_UTHANKA_HAS_RETURNED;
	
	@ClientString(id = 1000100, message = "Wah, ha, ha, ha! Uthanka has taken over this island today!")
	public static NpcStringId WAH_HA_HA_HA_UTHANKA_HAS_TAKEN_OVER_THIS_ISLAND_TODAY;
	
	@ClientString(id = 1000101, message = "Whew! He's quite a guy!")
	public static NpcStringId WHEW_HE_S_QUITE_A_GUY;
	
	@ClientString(id = 1000102, message = "How exasperating and unfair to have things happen in such a meaningless way like this...")
	public static NpcStringId HOW_EXASPERATING_AND_UNFAIR_TO_HAVE_THINGS_HAPPEN_IN_SUCH_A_MEANINGLESS_WAY_LIKE_THIS;
	
	@ClientString(id = 1000103, message = "This world should be filled with fear and sadness...")
	public static NpcStringId THIS_WORLD_SHOULD_BE_FILLED_WITH_FEAR_AND_SADNESS;
	
	@ClientString(id = 1000104, message = "I won't forgive the world that cursed me!")
	public static NpcStringId I_WON_T_FORGIVE_THE_WORLD_THAT_CURSED_ME;
	
	@ClientString(id = 1000105, message = "I'll make everyone feel the same suffering as me!")
	public static NpcStringId I_LL_MAKE_EVERYONE_FEEL_THE_SAME_SUFFERING_AS_ME;
	
	@ClientString(id = 1000106, message = "I'll give you a curse that you'll never be able to remove forever!")
	public static NpcStringId I_LL_GIVE_YOU_A_CURSE_THAT_YOU_LL_NEVER_BE_ABLE_TO_REMOVE_FOREVER;
	
	@ClientString(id = 1000107, message = "I'll get revenge on you who slaughtered my compatriots!")
	public static NpcStringId I_LL_GET_REVENGE_ON_YOU_WHO_SLAUGHTERED_MY_COMPATRIOTS;
	
	@ClientString(id = 1000108, message = "Those who are afraid should get away and those who are brave should fight!")
	public static NpcStringId THOSE_WHO_ARE_AFRAID_SHOULD_GET_AWAY_AND_THOSE_WHO_ARE_BRAVE_SHOULD_FIGHT;
	
	@ClientString(id = 1000109, message = "I've got power from Beleth so do you think I'll be easily defeated?!")
	public static NpcStringId I_VE_GOT_POWER_FROM_BELETH_SO_DO_YOU_THINK_I_LL_BE_EASILY_DEFEATED;
	
	@ClientString(id = 1000110, message = "I am leaving now, but soon someone will come who will teach you all a lesson!")
	public static NpcStringId I_AM_LEAVING_NOW_BUT_SOON_SOMEONE_WILL_COME_WHO_WILL_TEACH_YOU_ALL_A_LESSON;
	
	@ClientString(id = 1000111, message = "Hey guys, let's make a round of our territory!")
	public static NpcStringId HEY_GUYS_LET_S_MAKE_A_ROUND_OF_OUR_TERRITORY;
	
	@ClientString(id = 1000112, message = "The rumor is that there are wild, uncivilized ruffians who have recently arrived in my territory.")
	public static NpcStringId THE_RUMOR_IS_THAT_THERE_ARE_WILD_UNCIVILIZED_RUFFIANS_WHO_HAVE_RECENTLY_ARRIVED_IN_MY_TERRITORY;
	
	@ClientString(id = 1000113, message = "Do you know who I am?! I am Sirocco! Everyone, attack!")
	public static NpcStringId DO_YOU_KNOW_WHO_I_AM_I_AM_SIROCCO_EVERYONE_ATTACK;
	
	@ClientString(id = 1000114, message = "What's just happened?! The invincible Sirocco was defeated by someone like you?!")
	public static NpcStringId WHAT_S_JUST_HAPPENED_THE_INVINCIBLE_SIROCCO_WAS_DEFEATED_BY_SOMEONE_LIKE_YOU;
	
	@ClientString(id = 1000115, message = "Oh, I'm really hungry...")
	public static NpcStringId OH_I_M_REALLY_HUNGRY;
	
	@ClientString(id = 1000116, message = "I smell food. Ooh...")
	public static NpcStringId I_SMELL_FOOD_OOH;
	
	@ClientString(id = 1000117, message = "Ooh...")
	public static NpcStringId OOH;
	
	@ClientString(id = 1000118, message = "What does honey of this place taste like?!")
	public static NpcStringId WHAT_DOES_HONEY_OF_THIS_PLACE_TASTE_LIKE;
	
	@ClientString(id = 1000119, message = "Give me some sweet, delicious golden honey!")
	public static NpcStringId GIVE_ME_SOME_SWEET_DELICIOUS_GOLDEN_HONEY;
	
	@ClientString(id = 1000120, message = "If you give me some honey, I'll at least spare your life...")
	public static NpcStringId IF_YOU_GIVE_ME_SOME_HONEY_I_LL_AT_LEAST_SPARE_YOUR_LIFE;
	
	@ClientString(id = 1000121, message = "Only for lack of honey did I lose to the likes of you.")
	public static NpcStringId ONLY_FOR_LACK_OF_HONEY_DID_I_LOSE_TO_THE_LIKES_OF_YOU;
	
	@ClientString(id = 1000122, message = "Where is the traitor Kuroboros!?")
	public static NpcStringId WHERE_IS_THE_TRAITOR_KUROBOROS;
	
	@ClientString(id = 1000123, message = "Look in every nook and cranny around here!")
	public static NpcStringId LOOK_IN_EVERY_NOOK_AND_CRANNY_AROUND_HERE;
	
	@ClientString(id = 1000124, message = "Are you Lackey of Kuroboros?! I'll knock you out in one shot!")
	public static NpcStringId ARE_YOU_LACKEY_OF_KUROBOROS_I_LL_KNOCK_YOU_OUT_IN_ONE_SHOT;
	
	@ClientString(id = 1000125, message = "He just closed his eyes without disposing of the traitor... How unfair!")
	public static NpcStringId HE_JUST_CLOSED_HIS_EYES_WITHOUT_DISPOSING_OF_THE_TRAITOR_HOW_UNFAIR;
	
	@ClientString(id = 1000126, message = "Hell for unbelievers in Kuroboros!")
	public static NpcStringId HELL_FOR_UNBELIEVERS_IN_KUROBOROS;
	
	@ClientString(id = 1000127, message = "The person that does not believe in kuroboros, his life will soon become hell!")
	public static NpcStringId THE_PERSON_THAT_DOES_NOT_BELIEVE_IN_KUROBOROS_HIS_LIFE_WILL_SOON_BECOME_HELL;
	
	@ClientString(id = 1000128, message = "The lackey of that demented devil, the servant of a false god! I'll send that fool straight to hell!")
	public static NpcStringId THE_LACKEY_OF_THAT_DEMENTED_DEVIL_THE_SERVANT_OF_A_FALSE_GOD_I_LL_SEND_THAT_FOOL_STRAIGHT_TO_HELL;
	
	@ClientString(id = 1000129, message = "Uh... I'm not dying; I'm just disappearing for a moment... I'll resurrect again!")
	public static NpcStringId UH_I_M_NOT_DYING_I_M_JUST_DISAPPEARING_FOR_A_MOMENT_I_LL_RESURRECT_AGAIN;
	
	@ClientString(id = 1000130, message = "Hail to Kuroboros, the founder of our religion!")
	public static NpcStringId HAIL_TO_KUROBOROS_THE_FOUNDER_OF_OUR_RELIGION;
	
	@ClientString(id = 1000131, message = "Only those who believe in Patriarch Kuroboros shall receive salvation!")
	public static NpcStringId ONLY_THOSE_WHO_BELIEVE_IN_PATRIARCH_KUROBOROS_SHALL_RECEIVE_SALVATION;
	
	@ClientString(id = 1000132, message = "Are you the ones that Sharuk has incited?! You also should trust in Kuroboros and be saved!")
	public static NpcStringId ARE_YOU_THE_ONES_THAT_SHARUK_HAS_INCITED_YOU_ALSO_SHOULD_TRUST_IN_KUROBOROS_AND_BE_SAVED;
	
	@ClientString(id = 1000133, message = "Kuroboros will punish you.")
	public static NpcStringId KUROBOROS_WILL_PUNISH_YOU;
	
	@ClientString(id = 1000134, message = "You who have beautiful spirits that shine brightly! I have returned!")
	public static NpcStringId YOU_WHO_HAVE_BEAUTIFUL_SPIRITS_THAT_SHINE_BRIGHTLY_I_HAVE_RETURNED;
	
	@ClientString(id = 1000135, message = "You that are weary and exhausted... Entrust your souls to me.")
	public static NpcStringId YOU_THAT_ARE_WEARY_AND_EXHAUSTED_ENTRUST_YOUR_SOULS_TO_ME;
	
	@ClientString(id = 1000136, message = "The color of your soul is very attractive.")
	public static NpcStringId THE_COLOR_OF_YOUR_SOUL_IS_VERY_ATTRACTIVE;
	
	@ClientString(id = 1000137, message = "Those of you who live! Do you know how beautiful your souls are?!")
	public static NpcStringId THOSE_OF_YOU_WHO_LIVE_DO_YOU_KNOW_HOW_BEAUTIFUL_YOUR_SOULS_ARE;
	
	@ClientString(id = 1000138, message = "It... will... kill... everyone...")
	public static NpcStringId IT_WILL_KILL_EVERYONE;
	
	@ClientString(id = 1000139, message = "I'm... so... lonely...")
	public static NpcStringId I_M_SO_LONELY;
	
	@ClientString(id = 1000140, message = "My... enemy...!")
	public static NpcStringId MY_ENEMY;
	
	@ClientString(id = 1000141, message = "... Now... I'm not so lonely!")
	public static NpcStringId NOW_I_M_NOT_SO_LONELY;
	
	@ClientString(id = 1000142, message = "I will never forgive the Pixy Murika... that is trying to... kill us!")
	public static NpcStringId I_WILL_NEVER_FORGIVE_THE_PIXY_MURIKA_THAT_IS_TRYING_TO_KILL_US;
	
	@ClientString(id = 1000143, message = "Attack all the dull and stupid followers of Murika!")
	public static NpcStringId ATTACK_ALL_THE_DULL_AND_STUPID_FOLLOWERS_OF_MURIKA;
	
	@ClientString(id = 1000144, message = "I didn't have any idea about such ambitions!")
	public static NpcStringId I_DIDN_T_HAVE_ANY_IDEA_ABOUT_SUCH_AMBITIONS;
	
	@ClientString(id = 1000145, message = "This is not the end... It's just the beginning.")
	public static NpcStringId THIS_IS_NOT_THE_END_IT_S_JUST_THE_BEGINNING;
	
	@ClientString(id = 1000146, message = "Hey... Shall we have some fun for the first time in a long while?...")
	public static NpcStringId HEY_SHALL_WE_HAVE_SOME_FUN_FOR_THE_FIRST_TIME_IN_A_LONG_WHILE;
	
	@ClientString(id = 1000147, message = "There've been some things going around like crazy here recently...")
	public static NpcStringId THERE_VE_BEEN_SOME_THINGS_GOING_AROUND_LIKE_CRAZY_HERE_RECENTLY;
	
	@ClientString(id = 1000148, message = "Hey! Do you know who I am? I am Malex, Herald of Dagoniel! Attack!")
	public static NpcStringId HEY_DO_YOU_KNOW_WHO_I_AM_I_AM_MALEX_HERALD_OF_DAGONIEL_ATTACK;
	
	@ClientString(id = 1000149, message = "What's just happened?! The invincible Malex just lost to the likes of you?!")
	public static NpcStringId WHAT_S_JUST_HAPPENED_THE_INVINCIBLE_MALEX_JUST_LOST_TO_THE_LIKES_OF_YOU;
	
	@ClientString(id = 1000150, message = "It's something repeated in a vain life...")
	public static NpcStringId IT_S_SOMETHING_REPEATED_IN_A_VAIN_LIFE;
	
	@ClientString(id = 1000151, message = "Shake in fear, all you who value your lives!")
	public static NpcStringId SHAKE_IN_FEAR_ALL_YOU_WHO_VALUE_YOUR_LIVES;
	
	@ClientString(id = 1000152, message = "I'll make you feel suffering like a flame that is never extinguished!")
	public static NpcStringId I_LL_MAKE_YOU_FEEL_SUFFERING_LIKE_A_FLAME_THAT_IS_NEVER_EXTINGUISHED;
	
	@ClientString(id = 1000153, message = "Back to the dirt...")
	public static NpcStringId BACK_TO_THE_DIRT;
	
	@ClientString(id = 1000154, message = "Hail Varika!!")
	public static NpcStringId HAIL_VARIKA;
	
	@ClientString(id = 1000155, message = "Nobody can stop us!")
	public static NpcStringId NOBODY_CAN_STOP_US;
	
	@ClientString(id = 1000156, message = "You move slowly!")
	public static NpcStringId YOU_MOVE_SLOWLY;
	
	@ClientString(id = 1000157, message = "Varika! Go first!")
	public static NpcStringId VARIKA_GO_FIRST;
	
	@ClientString(id = 1000158, message = "Where am I? Who am I?")
	public static NpcStringId WHERE_AM_I_WHO_AM_I;
	
	@ClientString(id = 1000159, message = "Uh... My head hurts like it's going to burst! Who am I?")
	public static NpcStringId UH_MY_HEAD_HURTS_LIKE_IT_S_GOING_TO_BURST_WHO_AM_I;
	
	@ClientString(id = 1000160, message = "You jerk. You're a devil! You're a devil to have made me like this!")
	public static NpcStringId YOU_JERK_YOU_RE_A_DEVIL_YOU_RE_A_DEVIL_TO_HAVE_MADE_ME_LIKE_THIS;
	
	@ClientString(id = 1000161, message = "Where am I? What happened? Thank you!")
	public static NpcStringId WHERE_AM_I_WHAT_HAPPENED_THANK_YOU;
	
	@ClientString(id = 1000162, message = "Ukru Master!")
	public static NpcStringId UKRU_MASTER;
	
	@ClientString(id = 1000163, message = "Are you Matu?")
	public static NpcStringId ARE_YOU_MATU;
	
	@ClientString(id = 1000164, message = "Marak! Tubarin! Sabaracha!")
	public static NpcStringId MARAK_TUBARIN_SABARACHA;
	
	@ClientString(id = 1000165, message = "Pa'agrio Tama!")
	public static NpcStringId PA_AGRIO_TAMA;
	
	@ClientString(id = 1000166, message = "Accept the will of Icarus!")
	public static NpcStringId ACCEPT_THE_WILL_OF_ICARUS;
	
	@ClientString(id = 1000167, message = "The people who are blocking my way will not be forgiven...")
	public static NpcStringId THE_PEOPLE_WHO_ARE_BLOCKING_MY_WAY_WILL_NOT_BE_FORGIVEN;
	
	@ClientString(id = 1000168, message = "You are scum.")
	public static NpcStringId YOU_ARE_SCUM;
	
	@ClientString(id = 1000169, message = "You lack power.")
	public static NpcStringId YOU_LACK_POWER;
	
	@ClientString(id = 1000170, message = "Return")
	public static NpcStringId RETURN;
	
	@ClientString(id = 1000171, message = "Adena has been transferred.")
	public static NpcStringId ADENA_HAS_BEEN_TRANSFERRED;
	
	@ClientString(id = 1000172, message = "Event Number")
	public static NpcStringId EVENT_NUMBER;
	
	@ClientString(id = 1000173, message = "First Prize")
	public static NpcStringId FIRST_PRIZE;
	
	@ClientString(id = 1000174, message = "Second Prize")
	public static NpcStringId SECOND_PRIZE;
	
	@ClientString(id = 1000175, message = "Third Prize")
	public static NpcStringId THIRD_PRIZE;
	
	@ClientString(id = 1000176, message = "Fourth Prize")
	public static NpcStringId FOURTH_PRIZE;
	
	@ClientString(id = 1000177, message = "There has been no winning lottery ticket.")
	public static NpcStringId THERE_HAS_BEEN_NO_WINNING_LOTTERY_TICKET;
	
	@ClientString(id = 1000178, message = "The most recent winning lottery numbers")
	public static NpcStringId THE_MOST_RECENT_WINNING_LOTTERY_NUMBERS;
	
	@ClientString(id = 1000179, message = "Your lucky numbers have been selected above.")
	public static NpcStringId YOUR_LUCKY_NUMBERS_HAVE_BEEN_SELECTED_ABOVE;
	
	@ClientString(id = 1000180, message = "I wonder who it is that is lurking about..")
	public static NpcStringId I_WONDER_WHO_IT_IS_THAT_IS_LURKING_ABOUT;
	
	@ClientString(id = 1000181, message = "Sacred magical research is conducted here.")
	public static NpcStringId SACRED_MAGICAL_RESEARCH_IS_CONDUCTED_HERE;
	
	@ClientString(id = 1000182, message = "Behold the awesome power of magic!")
	public static NpcStringId BEHOLD_THE_AWESOME_POWER_OF_MAGIC;
	
	@ClientString(id = 1000183, message = "Your powers are impressive but you must not annoy our high level sorcerer.")
	public static NpcStringId YOUR_POWERS_ARE_IMPRESSIVE_BUT_YOU_MUST_NOT_ANNOY_OUR_HIGH_LEVEL_SORCERER;
	
	@ClientString(id = 1000184, message = "I am Barda, master of the Bandit Stronghold!")
	public static NpcStringId I_AM_BARDA_MASTER_OF_THE_BANDIT_STRONGHOLD;
	
	@ClientString(id = 1000185, message = "I, Master Barda, once owned that stronghold,")
	public static NpcStringId I_MASTER_BARDA_ONCE_OWNED_THAT_STRONGHOLD;
	
	@ClientString(id = 1000186, message = "Ah, very interesting!")
	public static NpcStringId AH_VERY_INTERESTING;
	
	@ClientString(id = 1000187, message = "You are more powerful than you appear. We'll meet again!")
	public static NpcStringId YOU_ARE_MORE_POWERFUL_THAN_YOU_APPEAR_WE_LL_MEET_AGAIN;
	
	@ClientString(id = 1000188, message = "You filthy sorcerers disgust me!")
	public static NpcStringId YOU_FILTHY_SORCERERS_DISGUST_ME;
	
	@ClientString(id = 1000189, message = "Why would you build a tower in our territory?")
	public static NpcStringId WHY_WOULD_YOU_BUILD_A_TOWER_IN_OUR_TERRITORY;
	
	@ClientString(id = 1000190, message = "Are you part of that evil gang of sorcerers?")
	public static NpcStringId ARE_YOU_PART_OF_THAT_EVIL_GANG_OF_SORCERERS;
	
	@ClientString(id = 1000191, message = "That is why I don't bother with anyone below the level of sorcerer.")
	public static NpcStringId THAT_IS_WHY_I_DON_T_BOTHER_WITH_ANYONE_BELOW_THE_LEVEL_OF_SORCERER;
	
	@ClientString(id = 1000192, message = "Ah, another beautiful day!")
	public static NpcStringId AH_ANOTHER_BEAUTIFUL_DAY;
	
	@ClientString(id = 1000193, message = "Our specialties are arson and looting.")
	public static NpcStringId OUR_SPECIALTIES_ARE_ARSON_AND_LOOTING;
	
	@ClientString(id = 1000194, message = "You will leave empty-handed!")
	public static NpcStringId YOU_WILL_LEAVE_EMPTY_HANDED;
	
	@ClientString(id = 1000195, message = "Ah, so you admire my treasure, do you? Try finding it! Ha!")
	public static NpcStringId AH_SO_YOU_ADMIRE_MY_TREASURE_DO_YOU_TRY_FINDING_IT_HA;
	
	@ClientString(id = 1000196, message = "Is everybody listening? Sirion has come back. Everyone chant and bow...")
	public static NpcStringId IS_EVERYBODY_LISTENING_SIRION_HAS_COME_BACK_EVERYONE_CHANT_AND_BOW;
	
	@ClientString(id = 1000197, message = "Bow down, you worthless humans!")
	public static NpcStringId BOW_DOWN_YOU_WORTHLESS_HUMANS;
	
	@ClientString(id = 1000198, message = "Very tacky!")
	public static NpcStringId VERY_TACKY;
	
	@ClientString(id = 1000199, message = "Don't think that you are invincible just because you defeated me!")
	public static NpcStringId DON_T_THINK_THAT_YOU_ARE_INVINCIBLE_JUST_BECAUSE_YOU_DEFEATED_ME;
	
	@ClientString(id = 1000200, message = "The material desires of mortals are ultimately meaningless.")
	public static NpcStringId THE_MATERIAL_DESIRES_OF_MORTALS_ARE_ULTIMATELY_MEANINGLESS;
	
	@ClientString(id = 1000201, message = "Do not forget the reason the Tower of Insolence collapsed.")
	public static NpcStringId DO_NOT_FORGET_THE_REASON_THE_TOWER_OF_INSOLENCE_COLLAPSED;
	
	@ClientString(id = 1000202, message = "You humans are all alike, full of greed and avarice!")
	public static NpcStringId YOU_HUMANS_ARE_ALL_ALIKE_FULL_OF_GREED_AND_AVARICE;
	
	@ClientString(id = 1000203, message = "All for nothing,")
	public static NpcStringId ALL_FOR_NOTHING;
	
	@ClientString(id = 1000204, message = "What are all these people doing here?")
	public static NpcStringId WHAT_ARE_ALL_THESE_PEOPLE_DOING_HERE;
	
	@ClientString(id = 1000205, message = "I must find the secret of eternal life, here among these rotten angels!")
	public static NpcStringId I_MUST_FIND_THE_SECRET_OF_ETERNAL_LIFE_HERE_AMONG_THESE_ROTTEN_ANGELS;
	
	@ClientString(id = 1000206, message = "Do you also seek the secret of immortality?")
	public static NpcStringId DO_YOU_ALSO_SEEK_THE_SECRET_OF_IMMORTALITY;
	
	@ClientString(id = 1000207, message = "I shall never reveal my secrets!")
	public static NpcStringId I_SHALL_NEVER_REVEAL_MY_SECRETS;
	
	@ClientString(id = 1000208, message = "Who dares enter this place?")
	public static NpcStringId WHO_DARES_ENTER_THIS_PLACE;
	
	@ClientString(id = 1000209, message = "This is no place for humans! You must leave immediately.")
	public static NpcStringId THIS_IS_NO_PLACE_FOR_HUMANS_YOU_MUST_LEAVE_IMMEDIATELY;
	
	@ClientString(id = 1000210, message = "You poor creatures! Too stupid to realize your own ignorance!")
	public static NpcStringId YOU_POOR_CREATURES_TOO_STUPID_TO_REALIZE_YOUR_OWN_IGNORANCE;
	
	@ClientString(id = 1000211, message = "You mustn't go there!")
	public static NpcStringId YOU_MUSTN_T_GO_THERE;
	
	@ClientString(id = 1000212, message = "Who dares disturb this marsh?")
	public static NpcStringId WHO_DARES_DISTURB_THIS_MARSH;
	
	@ClientString(id = 1000213, message = "The humans must not be allowed to destroy the marshland for their greedy purposes.")
	public static NpcStringId THE_HUMANS_MUST_NOT_BE_ALLOWED_TO_DESTROY_THE_MARSHLAND_FOR_THEIR_GREEDY_PURPOSES;
	
	@ClientString(id = 1000214, message = "You are a brave man...")
	public static NpcStringId YOU_ARE_A_BRAVE_MAN;
	
	@ClientString(id = 1000215, message = "You idiots! Some day you shall also be gone!")
	public static NpcStringId YOU_IDIOTS_SOME_DAY_YOU_SHALL_ALSO_BE_GONE;
	
	@ClientString(id = 1000216, message = "Someone has entered the forest...")
	public static NpcStringId SOMEONE_HAS_ENTERED_THE_FOREST;
	
	@ClientString(id = 1000217, message = "The forest is very quiet and peaceful.")
	public static NpcStringId THE_FOREST_IS_VERY_QUIET_AND_PEACEFUL;
	
	@ClientString(id = 1000218, message = "Stay here in this wonderful forest!")
	public static NpcStringId STAY_HERE_IN_THIS_WONDERFUL_FOREST;
	
	@ClientString(id = 1000219, message = "My... my souls...")
	public static NpcStringId MY_MY_SOULS;
	
	@ClientString(id = 1000220, message = "This forest is a dangerous place.")
	public static NpcStringId THIS_FOREST_IS_A_DANGEROUS_PLACE;
	
	@ClientString(id = 1000221, message = "Unless you leave this forest immediately you are bound to run into serious trouble.")
	public static NpcStringId UNLESS_YOU_LEAVE_THIS_FOREST_IMMEDIATELY_YOU_ARE_BOUND_TO_RUN_INTO_SERIOUS_TROUBLE;
	
	@ClientString(id = 1000222, message = "Leave now!")
	public static NpcStringId LEAVE_NOW;
	
	@ClientString(id = 1000223, message = "Why do you ignore my warning?")
	public static NpcStringId WHY_DO_YOU_IGNORE_MY_WARNING;
	
	@ClientString(id = 1000224, message = "Harits of the world... I bring you peace!")
	public static NpcStringId HARITS_OF_THE_WORLD_I_BRING_YOU_PEACE;
	
	@ClientString(id = 1000225, message = "Harits! Be courageous!")
	public static NpcStringId HARITS_BE_COURAGEOUS;
	
	@ClientString(id = 1000226, message = "I shall eat your still-beating heart!.")
	public static NpcStringId I_SHALL_EAT_YOUR_STILL_BEATING_HEART;
	
	@ClientString(id = 1000227, message = "Harits! Keep faith until the day I return... Never lose hope!")
	public static NpcStringId HARITS_KEEP_FAITH_UNTIL_THE_DAY_I_RETURN_NEVER_LOSE_HOPE;
	
	@ClientString(id = 1000228, message = "Even the giants are gone! There's nothing left to be afraid of now!")
	public static NpcStringId EVEN_THE_GIANTS_ARE_GONE_THERE_S_NOTHING_LEFT_TO_BE_AFRAID_OF_NOW;
	
	@ClientString(id = 1000229, message = "Have you heard of the giants? Their downfall was inevitable!")
	public static NpcStringId HAVE_YOU_HEARD_OF_THE_GIANTS_THEIR_DOWNFALL_WAS_INEVITABLE;
	
	@ClientString(id = 1000230, message = "What nerve! Do you dare to challenge me?")
	public static NpcStringId WHAT_NERVE_DO_YOU_DARE_TO_CHALLENGE_ME;
	
	@ClientString(id = 1000231, message = "You are as evil as the giants...")
	public static NpcStringId YOU_ARE_AS_EVIL_AS_THE_GIANTS;
	
	@ClientString(id = 1000232, message = "This dungeon is still in good condition!")
	public static NpcStringId THIS_DUNGEON_IS_STILL_IN_GOOD_CONDITION;
	
	@ClientString(id = 1000233, message = "This place is spectacular, wouldn't you say?")
	public static NpcStringId THIS_PLACE_IS_SPECTACULAR_WOULDN_T_YOU_SAY;
	
	@ClientString(id = 1000234, message = "You are very brave warriors!")
	public static NpcStringId YOU_ARE_VERY_BRAVE_WARRIORS;
	
	@ClientString(id = 1000235, message = "Are the giants truly gone for good?")
	public static NpcStringId ARE_THE_GIANTS_TRULY_GONE_FOR_GOOD;
	
	@ClientString(id = 1000236, message = "These graves are good.")
	public static NpcStringId THESE_GRAVES_ARE_GOOD;
	
	@ClientString(id = 1000237, message = "Gold and silver are meaningless to a dead man!")
	public static NpcStringId GOLD_AND_SILVER_ARE_MEANINGLESS_TO_A_DEAD_MAN;
	
	@ClientString(id = 1000238, message = "Why would those corrupt aristocrats bury such useful things?")
	public static NpcStringId WHY_WOULD_THOSE_CORRUPT_ARISTOCRATS_BURY_SUCH_USEFUL_THINGS;
	
	@ClientString(id = 1000239, message = "You filthy pig! Eat and be merry now that you have shirked your responsibilities!")
	public static NpcStringId YOU_FILTHY_PIG_EAT_AND_BE_MERRY_NOW_THAT_YOU_HAVE_SHIRKED_YOUR_RESPONSIBILITIES;
	
	@ClientString(id = 1000240, message = "Those thugs! It would be too merciful to rip them apart and chew them up one at a time!")
	public static NpcStringId THOSE_THUGS_IT_WOULD_BE_TOO_MERCIFUL_TO_RIP_THEM_APART_AND_CHEW_THEM_UP_ONE_AT_A_TIME;
	
	@ClientString(id = 1000241, message = "You accursed scoundrels!")
	public static NpcStringId YOU_ACCURSED_SCOUNDRELS;
	
	@ClientString(id = 1000242, message = "Hmm, could this be the assassin sent by those idiots from Aden?")
	public static NpcStringId HMM_COULD_THIS_BE_THE_ASSASSIN_SENT_BY_THOSE_IDIOTS_FROM_ADEN;
	
	@ClientString(id = 1000243, message = "I shall curse your name with my last breath!")
	public static NpcStringId I_SHALL_CURSE_YOUR_NAME_WITH_MY_LAST_BREATH;
	
	@ClientString(id = 1000244, message = "My beloved Lord Shilen.")
	public static NpcStringId MY_BELOVED_LORD_SHILEN;
	
	@ClientString(id = 1000245, message = "I must break the seal and release Lord Shilen as soon as possible...")
	public static NpcStringId I_MUST_BREAK_THE_SEAL_AND_RELEASE_LORD_SHILEN_AS_SOON_AS_POSSIBLE;
	
	@ClientString(id = 1000246, message = "You shall taste the vengeance of Lord Shilen!")
	public static NpcStringId YOU_SHALL_TASTE_THE_VENGEANCE_OF_LORD_SHILEN;
	
	@ClientString(id = 1000247, message = "Lord Shilen... some day... you will accomplish... this mission...")
	public static NpcStringId LORD_SHILEN_SOME_DAY_YOU_WILL_ACCOMPLISH_THIS_MISSION;
	
	@ClientString(id = 1000248, message = "Towards immortality...")
	public static NpcStringId TOWARDS_IMMORTALITY;
	
	@ClientString(id = 1000249, message = "He who desires immortality... Come unto me.")
	public static NpcStringId HE_WHO_DESIRES_IMMORTALITY_COME_UNTO_ME;
	
	@ClientString(id = 1000250, message = "You shall be sacrificed to gain my immortality!")
	public static NpcStringId YOU_SHALL_BE_SACRIFICED_TO_GAIN_MY_IMMORTALITY;
	
	@ClientString(id = 1000251, message = "Eternal life in front of my eyes... I have collapsed in such a worthless way like this...")
	public static NpcStringId ETERNAL_LIFE_IN_FRONT_OF_MY_EYES_I_HAVE_COLLAPSED_IN_SUCH_A_WORTHLESS_WAY_LIKE_THIS;
	
	@ClientString(id = 1000252, message = "Zaken, you are a cowardly cur!")
	public static NpcStringId ZAKEN_YOU_ARE_A_COWARDLY_CUR;
	
	@ClientString(id = 1000253, message = "You are immortal, aren't you, Zaken?")
	public static NpcStringId YOU_ARE_IMMORTAL_AREN_T_YOU_ZAKEN;
	
	@ClientString(id = 1000254, message = "Please return my body to me.")
	public static NpcStringId PLEASE_RETURN_MY_BODY_TO_ME;
	
	@ClientString(id = 1000255, message = "Finally... will I be able to rest?")
	public static NpcStringId FINALLY_WILL_I_BE_ABLE_TO_REST;
	
	@ClientString(id = 1000256, message = "What is all that racket?")
	public static NpcStringId WHAT_IS_ALL_THAT_RACKET;
	
	@ClientString(id = 1000257, message = "Master Gildor does not like to be disturbed.")
	public static NpcStringId MASTER_GILDOR_DOES_NOT_LIKE_TO_BE_DISTURBED;
	
	@ClientString(id = 1000258, message = "Please, just hold it down...")
	public static NpcStringId PLEASE_JUST_HOLD_IT_DOWN;
	
	@ClientString(id = 1000259, message = "If you disturb Master Gildor I won't be able to help you.")
	public static NpcStringId IF_YOU_DISTURB_MASTER_GILDOR_I_WON_T_BE_ABLE_TO_HELP_YOU;
	
	@ClientString(id = 1000260, message = "Who dares approach?")
	public static NpcStringId WHO_DARES_APPROACH;
	
	@ClientString(id = 1000261, message = "These reeds are my territory...")
	public static NpcStringId THESE_REEDS_ARE_MY_TERRITORY;
	
	@ClientString(id = 1000262, message = "You fools! Today you shall learn a lesson!")
	public static NpcStringId YOU_FOOLS_TODAY_YOU_SHALL_LEARN_A_LESSON;
	
	@ClientString(id = 1000263, message = "The past goes by... Is a new era beginning?...")
	public static NpcStringId THE_PAST_GOES_BY_IS_A_NEW_ERA_BEGINNING;
	
	@ClientString(id = 1000264, message = "This is the garden of Eva.")
	public static NpcStringId THIS_IS_THE_GARDEN_OF_EVA;
	
	@ClientString(id = 1000265, message = "The garden of Eva is a sacred place.")
	public static NpcStringId THE_GARDEN_OF_EVA_IS_A_SACRED_PLACE;
	
	@ClientString(id = 1000266, message = "Do you mean to insult Eva?")
	public static NpcStringId DO_YOU_MEAN_TO_INSULT_EVA;
	
	@ClientString(id = 1000267, message = "How rude! Eva's love is available to all, even to an ill-mannered lout like yourself!")
	public static NpcStringId HOW_RUDE_EVA_S_LOVE_IS_AVAILABLE_TO_ALL_EVEN_TO_AN_ILL_MANNERED_LOUT_LIKE_YOURSELF;
	
	@ClientString(id = 1000268, message = "This place once belonged to Lord Shilen.")
	public static NpcStringId THIS_PLACE_ONCE_BELONGED_TO_LORD_SHILEN;
	
	@ClientString(id = 1000269, message = "Leave this palace to us, spirits of Eva.")
	public static NpcStringId LEAVE_THIS_PALACE_TO_US_SPIRITS_OF_EVA;
	
	@ClientString(id = 1000270, message = "Why are you getting in our way?")
	public static NpcStringId WHY_ARE_YOU_GETTING_IN_OUR_WAY;
	
	@ClientString(id = 1000271, message = "Shilen... our Shilen!")
	public static NpcStringId SHILEN_OUR_SHILEN;
	
	@ClientString(id = 1000272, message = "All who fear of Fafurion... Leave this place at once!")
	public static NpcStringId ALL_WHO_FEAR_OF_FAFURION_LEAVE_THIS_PLACE_AT_ONCE;
	
	@ClientString(id = 1000273, message = "You are being punished in the name of Fafurion!")
	public static NpcStringId YOU_ARE_BEING_PUNISHED_IN_THE_NAME_OF_FAFURION;
	
	@ClientString(id = 1000274, message = "Oh, master... please forgive your humble servant...")
	public static NpcStringId OH_MASTER_PLEASE_FORGIVE_YOUR_HUMBLE_SERVANT;
	
	@ClientString(id = 1000275, message = "Prepare to die, foreign invaders! I am Gustav, the eternal ruler of this fortress and I have taken up my sword to repel thee!")
	public static NpcStringId PREPARE_TO_DIE_FOREIGN_INVADERS_I_AM_GUSTAV_THE_ETERNAL_RULER_OF_THIS_FORTRESS_AND_I_HAVE_TAKEN_UP_MY_SWORD_TO_REPEL_THEE;
	
	@ClientString(id = 1000276, message = "Glory to Aden, the Kingdom of the Lion! Glory to Sir Gustav, our immortal lord!")
	public static NpcStringId GLORY_TO_ADEN_THE_KINGDOM_OF_THE_LION_GLORY_TO_SIR_GUSTAV_OUR_IMMORTAL_LORD;
	
	@ClientString(id = 1000277, message = "Soldiers of Gustav, go forth and destroy the invaders!")
	public static NpcStringId SOLDIERS_OF_GUSTAV_GO_FORTH_AND_DESTROY_THE_INVADERS;
	
	@ClientString(id = 1000278, message = "This is unbelievable! Have I really been defeated? I shall return and take your head!")
	public static NpcStringId THIS_IS_UNBELIEVABLE_HAVE_I_REALLY_BEEN_DEFEATED_I_SHALL_RETURN_AND_TAKE_YOUR_HEAD;
	
	@ClientString(id = 1000279, message = "Could it be that I have reached my end? I cannot die without honor, without the permission of Sir Gustav!")
	public static NpcStringId COULD_IT_BE_THAT_I_HAVE_REACHED_MY_END_I_CANNOT_DIE_WITHOUT_HONOR_WITHOUT_THE_PERMISSION_OF_SIR_GUSTAV;
	
	@ClientString(id = 1000280, message = "Ah, the bitter taste of defeat... I fear my torments are not over...")
	public static NpcStringId AH_THE_BITTER_TASTE_OF_DEFEAT_I_FEAR_MY_TORMENTS_ARE_NOT_OVER;
	
	@ClientString(id = 1000281, message = "I follow the will of Fafurion.")
	public static NpcStringId I_FOLLOW_THE_WILL_OF_FAFURION;
	
	@ClientString(id = 1000282, message = "Tickets for the Lucky Lottery are now on sale!")
	public static NpcStringId TICKETS_FOR_THE_LUCKY_LOTTERY_ARE_NOW_ON_SALE;
	
	@ClientString(id = 1000283, message = "The Lucky Lottery drawing is about to begin!")
	public static NpcStringId THE_LUCKY_LOTTERY_DRAWING_IS_ABOUT_TO_BEGIN;
	
	@ClientString(id = 1000284, message = "The winning numbers for Lucky Lottery $s1 are $s2. Congratulations to the winners!")
	public static NpcStringId THE_WINNING_NUMBERS_FOR_LUCKY_LOTTERY_S1_ARE_S2_CONGRATULATIONS_TO_THE_WINNERS;
	
	@ClientString(id = 1000285, message = "You're too young to play Lucky Lottery!")
	public static NpcStringId YOU_RE_TOO_YOUNG_TO_PLAY_LUCKY_LOTTERY;
	
	@ClientString(id = 1000286, message = "$s1! Watch your back!!!")
	public static NpcStringId S1_WATCH_YOUR_BACK;
	
	@ClientString(id = 1000287, message = "$s1! Come on, I'll take you on!")
	public static NpcStringId S1_COME_ON_I_LL_TAKE_YOU_ON;
	
	@ClientString(id = 1000288, message = "$s1! How dare you interrupt our fight! Hey guys, help!")
	public static NpcStringId S1_HOW_DARE_YOU_INTERRUPT_OUR_FIGHT_HEY_GUYS_HELP;
	
	@ClientString(id = 1000289, message = "I'll help you! I'm no coward!")
	public static NpcStringId I_LL_HELP_YOU_I_M_NO_COWARD;
	
	@ClientString(id = 1000290, message = "Dear ultimate power!!!")
	public static NpcStringId DEAR_ULTIMATE_POWER;
	
	@ClientString(id = 1000291, message = "Everybody! Attack $s1!")
	public static NpcStringId EVERYBODY_ATTACK_S1;
	
	@ClientString(id = 1000292, message = "I will follow your order.")
	public static NpcStringId I_WILL_FOLLOW_YOUR_ORDER;
	
	@ClientString(id = 1000293, message = "Bet you didn't expect this!")
	public static NpcStringId BET_YOU_DIDN_T_EXPECT_THIS;
	
	@ClientString(id = 1000294, message = "Come out, you children of darkness!")
	public static NpcStringId COME_OUT_YOU_CHILDREN_OF_DARKNESS;
	
	@ClientString(id = 1000295, message = "Summon party members!")
	public static NpcStringId SUMMON_PARTY_MEMBERS;
	
	@ClientString(id = 1000296, message = "Master! Did you call me?")
	public static NpcStringId MASTER_DID_YOU_CALL_ME;
	
	@ClientString(id = 1000297, message = "You idiot!")
	public static NpcStringId YOU_IDIOT;
	
	@ClientString(id = 1000298, message = "What about this?")
	public static NpcStringId WHAT_ABOUT_THIS;
	
	@ClientString(id = 1000299, message = "Very impressive $s1! This is the last!")
	public static NpcStringId VERY_IMPRESSIVE_S1_THIS_IS_THE_LAST;
	
	@ClientString(id = 1000300, message = "Dawn")
	public static NpcStringId DAWN;
	
	@ClientString(id = 1000301, message = "Dusk")
	public static NpcStringId DUSK;
	
	@ClientString(id = 1000302, message = "Nothingness")
	public static NpcStringId NOTHINGNESS;
	
	@ClientString(id = 1000303, message = "This world will soon be annihilated!")
	public static NpcStringId THIS_WORLD_WILL_SOON_BE_ANNIHILATED;
	
	@ClientString(id = 1000304, message = "A curse upon you!")
	public static NpcStringId A_CURSE_UPON_YOU;
	
	@ClientString(id = 1000305, message = "The day of judgment is near!")
	public static NpcStringId THE_DAY_OF_JUDGMENT_IS_NEAR;
	
	@ClientString(id = 1000306, message = "I bestow upon you a blessing!")
	public static NpcStringId I_BESTOW_UPON_YOU_A_BLESSING;
	
	@ClientString(id = 1000307, message = "The first rule of fighting is to start by killing the weak ones!")
	public static NpcStringId THE_FIRST_RULE_OF_FIGHTING_IS_TO_START_BY_KILLING_THE_WEAK_ONES;
	
	@ClientString(id = 1000308, message = "Adena")
	public static NpcStringId ADENA;
	
	@ClientString(id = 1000309, message = "Ancient Adena")
	public static NpcStringId ANCIENT_ADENA;
	
	@ClientString(id = 1000310, message = "Dusk")
	public static NpcStringId DUSK_2;
	
	@ClientString(id = 1000311, message = "Dawn")
	public static NpcStringId DAWN_2;
	
	@ClientString(id = 1000312, message = "Level 31 or lower")
	public static NpcStringId LEVEL_31_OR_LOWER;
	
	@ClientString(id = 1000313, message = "Level 42 or lower")
	public static NpcStringId LEVEL_42_OR_LOWER;
	
	@ClientString(id = 1000314, message = "Level 53 or lower")
	public static NpcStringId LEVEL_53_OR_LOWER;
	
	@ClientString(id = 1000315, message = "Level 64 or lower")
	public static NpcStringId LEVEL_64_OR_LOWER;
	
	@ClientString(id = 1000316, message = "No Level Limit")
	public static NpcStringId NO_LEVEL_LIMIT;
	
	@ClientString(id = 1000317, message = "The main event will start in 2 minutes. Please register now.")
	public static NpcStringId THE_MAIN_EVENT_WILL_START_IN_2_MINUTES_PLEASE_REGISTER_NOW;
	
	@ClientString(id = 1000318, message = "The main event is now starting.")
	public static NpcStringId THE_MAIN_EVENT_IS_NOW_STARTING;
	
	@ClientString(id = 1000319, message = "The main event will close in 5 minutes.")
	public static NpcStringId THE_MAIN_EVENT_WILL_CLOSE_IN_5_MINUTES;
	
	@ClientString(id = 1000320, message = "The main event will finish in 2 minutes. Please prepare for the next game.")
	public static NpcStringId THE_MAIN_EVENT_WILL_FINISH_IN_2_MINUTES_PLEASE_PREPARE_FOR_THE_NEXT_GAME;
	
	@ClientString(id = 1000321, message = "The amount of SSQ contribution has increased by $s1.")
	public static NpcStringId THE_AMOUNT_OF_SSQ_CONTRIBUTION_HAS_INCREASED_BY_S1;
	
	@ClientString(id = 1000322, message = "No record exists")
	public static NpcStringId NO_RECORD_EXISTS;
	
	@ClientString(id = 1000324, message = "<a action='bypass -h menu_select?ask=-2&reply=324'>Gladiator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_324_GLADIATOR_A_BR;
	
	@ClientString(id = 1000325, message = "<a action='bypass -h menu_select?ask=-2&reply=325'>Warlord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_325_WARLORD_A_BR;
	
	@ClientString(id = 1000327, message = "<a action='bypass -h menu_select?ask=-2&reply=327'>Paladin</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_327_PALADIN_A_BR;
	
	@ClientString(id = 1000328, message = "<a action='bypass -h menu_select?ask=-2&reply=328'>Dark Avenger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_328_DARK_AVENGER_A_BR;
	
	@ClientString(id = 1000330, message = "<a action='bypass -h menu_select?ask=-2&reply=330'>Treasure Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_330_TREASURE_HUNTER_A_BR;
	
	@ClientString(id = 1000331, message = "<a action='bypass -h menu_select?ask=-2&reply=331'>Hawkeye</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_331_HAWKEYE_A_BR;
	
	@ClientString(id = 1000334, message = "<a action='bypass -h menu_select?ask=-2&reply=334'>Sorcerer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_334_SORCERER_A_BR;
	
	@ClientString(id = 1000335, message = "<a action='bypass -h menu_select?ask=-2&reply=335'>Necromancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_335_NECROMANCER_A_BR;
	
	@ClientString(id = 1000336, message = "<a action='bypass -h menu_select?ask=-2&reply=336'>Warlock</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_336_WARLOCK_A_BR;
	
	@ClientString(id = 1000338, message = "<a action='bypass -h menu_select?ask=-2&reply=338'>Bishop</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_338_BISHOP_A_BR;
	
	@ClientString(id = 1000339, message = "<a action='bypass -h menu_select?ask=-2&reply=339'>Prophet</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_339_PROPHET_A_BR;
	
	@ClientString(id = 1000342, message = "<a action='bypass -h menu_select?ask=-2&reply=342'>Temple Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_342_TEMPLE_KNIGHT_A_BR;
	
	@ClientString(id = 1000343, message = "<a action='bypass -h menu_select?ask=-2&reply=343'>Swordsinger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_343_SWORDSINGER_A_BR;
	
	@ClientString(id = 1000345, message = "<a action='bypass -h menu_select?ask=-2&reply=345'>Plainswalker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_345_PLAINSWALKER_A_BR;
	
	@ClientString(id = 1000346, message = "<a action='bypass -h menu_select?ask=-2&reply=346'>Silver Ranger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_346_SILVER_RANGER_A_BR;
	
	@ClientString(id = 1000349, message = "<a action='bypass -h menu_select?ask=-2&reply=349'>Spellsinger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_349_SPELLSINGER_A_BR;
	
	@ClientString(id = 1000350, message = "<a action='bypass -h menu_select?ask=-2&reply=350'>Elemental Summoner</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_350_ELEMENTAL_SUMMONER_A_BR;
	
	@ClientString(id = 1000352, message = "<a action='bypass -h menu_select?ask=-2&reply=352'>Elven Elder</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_352_ELVEN_ELDER_A_BR;
	
	@ClientString(id = 1000355, message = "<a action='bypass -h menu_select?ask=-2&reply=355'>Shillien Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_355_SHILLIEN_KNIGHT_A_BR;
	
	@ClientString(id = 1000356, message = "<a action='bypass -h menu_select?ask=-2&reply=356'>Bladedancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_356_BLADEDANCER_A_BR;
	
	@ClientString(id = 1000358, message = "<a action='bypass -h menu_select?ask=-2&reply=358'>Abyssal Walker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_358_ABYSSAL_WALKER_A_BR;
	
	@ClientString(id = 1000359, message = "<a action='bypass -h menu_select?ask=-2&reply=359'>Phantom Ranger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_359_PHANTOM_RANGER_A_BR;
	
	@ClientString(id = 1000362, message = "<a action='bypass -h menu_select?ask=-2&reply=362'>Spellhowler</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_362_SPELLHOWLER_A_BR;
	
	@ClientString(id = 1000363, message = "<a action='bypass -h menu_select?ask=-2&reply=363'>Phantom Summoner</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_363_PHANTOM_SUMMONER_A_BR;
	
	@ClientString(id = 1000365, message = "<a action='bypass -h menu_select?ask=-2&reply=365'>Shillien Elder</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_365_SHILLIEN_ELDER_A_BR;
	
	@ClientString(id = 1000368, message = "<a action='bypass -h menu_select?ask=-2&reply=368'>Destroyer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_368_DESTROYER_A_BR;
	
	@ClientString(id = 1000370, message = "<a action='bypass -h menu_select?ask=-2&reply=370'>Tyrant</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_370_TYRANT_A_BR;
	
	@ClientString(id = 1000373, message = "<a action='bypass -h menu_select?ask=-2&reply=373'>Overlord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_373_OVERLORD_A_BR;
	
	@ClientString(id = 1000374, message = "<a action='bypass -h menu_select?ask=-2&reply=374'>Warcryer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_374_WARCRYER_A_BR;
	
	@ClientString(id = 1000377, message = "<a action='bypass -h menu_select?ask=-2&reply=377'>Bounty Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_377_BOUNTY_HUNTER_A_BR;
	
	@ClientString(id = 1000379, message = "<a action='bypass -h menu_select?ask=-2&reply=379'>Warsmith</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_379_WARSMITH_A_BR;
	
	@ClientString(id = 1000380, message = "That will do! I'll move you to the outside soon.")
	public static NpcStringId THAT_WILL_DO_I_LL_MOVE_YOU_TO_THE_OUTSIDE_SOON;
	
	@ClientString(id = 1000381, message = "$s1! Watch your back!")
	public static NpcStringId S1_WATCH_YOUR_BACK_2;
	
	@ClientString(id = 1000382, message = "Your rear is practically unguarded!")
	public static NpcStringId YOUR_REAR_IS_PRACTICALLY_UNGUARDED;
	
	@ClientString(id = 1000383, message = "How dare you turn your back on me!")
	public static NpcStringId HOW_DARE_YOU_TURN_YOUR_BACK_ON_ME;
	
	@ClientString(id = 1000384, message = "$s1! I'll deal with you myself!")
	public static NpcStringId S1_I_LL_DEAL_WITH_YOU_MYSELF;
	
	@ClientString(id = 1000385, message = "$s1! This is personal!")
	public static NpcStringId S1_THIS_IS_PERSONAL;
	
	@ClientString(id = 1000386, message = "$s1! Leave us alone! This is between us!")
	public static NpcStringId S1_LEAVE_US_ALONE_THIS_IS_BETWEEN_US;
	
	@ClientString(id = 1000387, message = "$s1! Killing you will be a pleasure!")
	public static NpcStringId S1_KILLING_YOU_WILL_BE_A_PLEASURE;
	
	@ClientString(id = 1000388, message = "$s1! Hey! We're having a duel here!")
	public static NpcStringId S1_HEY_WE_RE_HAVING_A_DUEL_HERE;
	
	@ClientString(id = 1000389, message = "The duel is over! Attack!")
	public static NpcStringId THE_DUEL_IS_OVER_ATTACK;
	
	@ClientString(id = 1000390, message = "Foul! Kill the coward!")
	public static NpcStringId FOUL_KILL_THE_COWARD;
	
	@ClientString(id = 1000391, message = "How dare you interrupt a sacred duel! You must be taught a lesson!")
	public static NpcStringId HOW_DARE_YOU_INTERRUPT_A_SACRED_DUEL_YOU_MUST_BE_TAUGHT_A_LESSON;
	
	@ClientString(id = 1000392, message = "Die, you coward!")
	public static NpcStringId DIE_YOU_COWARD;
	
	@ClientString(id = 1000393, message = "What are you looking at?")
	public static NpcStringId WHAT_ARE_YOU_LOOKING_AT_2;
	
	@ClientString(id = 1000394, message = "Kill the coward!")
	public static NpcStringId KILL_THE_COWARD;
	
	@ClientString(id = 1000395, message = "I never thought I'd use this against a novice!")
	public static NpcStringId I_NEVER_THOUGHT_I_D_USE_THIS_AGAINST_A_NOVICE;
	
	@ClientString(id = 1000396, message = "You won't take me down easily.")
	public static NpcStringId YOU_WON_T_TAKE_ME_DOWN_EASILY;
	
	@ClientString(id = 1000397, message = "The battle has just begun!")
	public static NpcStringId THE_BATTLE_HAS_JUST_BEGUN;
	
	@ClientString(id = 1000398, message = "Kill $s1 first!")
	public static NpcStringId KILL_S1_FIRST;
	
	@ClientString(id = 1000399, message = "Attack $s1!")
	public static NpcStringId ATTACK_S1;
	
	@ClientString(id = 1000400, message = "Attack! Attack!")
	public static NpcStringId ATTACK_ATTACK;
	
	@ClientString(id = 1000401, message = "Over here!")
	public static NpcStringId OVER_HERE;
	
	@ClientString(id = 1000402, message = "Roger!")
	public static NpcStringId ROGER;
	
	@ClientString(id = 1000403, message = "Show yourselves!")
	public static NpcStringId SHOW_YOURSELVES;
	
	@ClientString(id = 1000404, message = "Forces of darkness! Follow me!")
	public static NpcStringId FORCES_OF_DARKNESS_FOLLOW_ME;
	
	@ClientString(id = 1000405, message = "Destroy the enemy, my brothers!")
	public static NpcStringId DESTROY_THE_ENEMY_MY_BROTHERS;
	
	@ClientString(id = 1000406, message = "Now the fun starts!")
	public static NpcStringId NOW_THE_FUN_STARTS;
	
	@ClientString(id = 1000407, message = "Enough fooling around. Get ready to die!")
	public static NpcStringId ENOUGH_FOOLING_AROUND_GET_READY_TO_DIE;
	
	@ClientString(id = 1000408, message = "You idiot! I've just been toying with you!")
	public static NpcStringId YOU_IDIOT_I_VE_JUST_BEEN_TOYING_WITH_YOU;
	
	@ClientString(id = 1000409, message = "Witness my true power!")
	public static NpcStringId WITNESS_MY_TRUE_POWER;
	
	@ClientString(id = 1000410, message = "Now the battle begins!")
	public static NpcStringId NOW_THE_BATTLE_BEGINS;
	
	@ClientString(id = 1000411, message = "I must admit, no one makes my blood boil quite like you do!")
	public static NpcStringId I_MUST_ADMIT_NO_ONE_MAKES_MY_BLOOD_BOIL_QUITE_LIKE_YOU_DO;
	
	@ClientString(id = 1000412, message = "You have more skill than I thought!")
	public static NpcStringId YOU_HAVE_MORE_SKILL_THAN_I_THOUGHT;
	
	@ClientString(id = 1000413, message = "I'll double my strength!")
	public static NpcStringId I_LL_DOUBLE_MY_STRENGTH;
	
	@ClientString(id = 1000414, message = "Prepare to die!")
	public static NpcStringId PREPARE_TO_DIE;
	
	@ClientString(id = 1000415, message = "All is lost! Prepare to meet the goddess of death!")
	public static NpcStringId ALL_IS_LOST_PREPARE_TO_MEET_THE_GODDESS_OF_DEATH;
	
	@ClientString(id = 1000416, message = "All is lost! The prophecy of destruction has been fulfilled!")
	public static NpcStringId ALL_IS_LOST_THE_PROPHECY_OF_DESTRUCTION_HAS_BEEN_FULFILLED;
	
	@ClientString(id = 1000417, message = "The end of time has come! The prophecy of destruction has been fulfilled!")
	public static NpcStringId THE_END_OF_TIME_HAS_COME_THE_PROPHECY_OF_DESTRUCTION_HAS_BEEN_FULFILLED;
	
	@ClientString(id = 1000418, message = "$s1! You bring an ill wind!")
	public static NpcStringId S1_YOU_BRING_AN_ILL_WIND;
	
	@ClientString(id = 1000419, message = "$s1! You might as well give up!")
	public static NpcStringId S1_YOU_MIGHT_AS_WELL_GIVE_UP;
	
	@ClientString(id = 1000420, message = "You don't have any hope! Your end has come!")
	public static NpcStringId YOU_DON_T_HAVE_ANY_HOPE_YOUR_END_HAS_COME;
	
	@ClientString(id = 1000421, message = "The prophecy of darkness has been fulfilled!")
	public static NpcStringId THE_PROPHECY_OF_DARKNESS_HAS_BEEN_FULFILLED;
	
	@ClientString(id = 1000422, message = "As foretold in the prophecy of darkness, the era of chaos has begun!")
	public static NpcStringId AS_FORETOLD_IN_THE_PROPHECY_OF_DARKNESS_THE_ERA_OF_CHAOS_HAS_BEGUN;
	
	@ClientString(id = 1000423, message = "The prophecy of darkness has come to pass!")
	public static NpcStringId THE_PROPHECY_OF_DARKNESS_HAS_COME_TO_PASS;
	
	@ClientString(id = 1000424, message = "$s1! I give you the blessing of prophecy!")
	public static NpcStringId S1_I_GIVE_YOU_THE_BLESSING_OF_PROPHECY;
	
	@ClientString(id = 1000425, message = "$s1! I bestow upon you the authority of the abyss!")
	public static NpcStringId S1_I_BESTOW_UPON_YOU_THE_AUTHORITY_OF_THE_ABYSS;
	
	@ClientString(id = 1000426, message = "Herald of the new era, open your eyes!")
	public static NpcStringId HERALD_OF_THE_NEW_ERA_OPEN_YOUR_EYES;
	
	@ClientString(id = 1000427, message = "Remember, kill the weaklings first!")
	public static NpcStringId REMEMBER_KILL_THE_WEAKLINGS_FIRST;
	
	@ClientString(id = 1000428, message = "Prepare to die, maggot!")
	public static NpcStringId PREPARE_TO_DIE_MAGGOT;
	
	@ClientString(id = 1000429, message = "That will do. Prepare to be released!")
	public static NpcStringId THAT_WILL_DO_PREPARE_TO_BE_RELEASED;
	
	@ClientString(id = 1000430, message = "Draw")
	public static NpcStringId DRAW;
	
	@ClientString(id = 1000431, message = "Rulers of the seal! I bring you wondrous gifts!")
	public static NpcStringId RULERS_OF_THE_SEAL_I_BRING_YOU_WONDROUS_GIFTS;
	
	@ClientString(id = 1000432, message = "Rulers of the seal! I have some excellent weapons to show you!")
	public static NpcStringId RULERS_OF_THE_SEAL_I_HAVE_SOME_EXCELLENT_WEAPONS_TO_SHOW_YOU;
	
	@ClientString(id = 1000433, message = "I've been so busy lately, in addition to planning my trip!")
	public static NpcStringId I_VE_BEEN_SO_BUSY_LATELY_IN_ADDITION_TO_PLANNING_MY_TRIP;
	
	@ClientString(id = 1000434, message = "Your treatment of weaklings is unforgivable!")
	public static NpcStringId YOUR_TREATMENT_OF_WEAKLINGS_IS_UNFORGIVABLE;
	
	@ClientString(id = 1000435, message = "I'm here to help you! Hi yah!")
	public static NpcStringId I_M_HERE_TO_HELP_YOU_HI_YAH;
	
	@ClientString(id = 1000436, message = "Justice will be served!")
	public static NpcStringId JUSTICE_WILL_BE_SERVED;
	
	@ClientString(id = 1000437, message = "On to immortal glory!")
	public static NpcStringId ON_TO_IMMORTAL_GLORY;
	
	@ClientString(id = 1000438, message = "Justice always avenges the powerless!")
	public static NpcStringId JUSTICE_ALWAYS_AVENGES_THE_POWERLESS;
	
	@ClientString(id = 1000439, message = "Are you hurt? Hang in there, we've almost got them!")
	public static NpcStringId ARE_YOU_HURT_HANG_IN_THERE_WE_VE_ALMOST_GOT_THEM;
	
	@ClientString(id = 1000440, message = "Why should I tell you my name, you creep!?")
	public static NpcStringId WHY_SHOULD_I_TELL_YOU_MY_NAME_YOU_CREEP;
	
	@ClientString(id = 1000441, message = "0 minute")
	public static NpcStringId ZERO_MINUTE;
	
	@ClientString(id = 1000442, message = "$s1 Minute(s)")
	public static NpcStringId S1_MINUTE_S;
	
	@ClientString(id = 1000443, message = "The defenders of $s1 castle will be teleported to the inner castle.")
	public static NpcStringId THE_DEFENDERS_OF_S1_CASTLE_WILL_BE_TELEPORTED_TO_THE_INNER_CASTLE;
	
	@ClientString(id = 1000444, message = "Sunday")
	public static NpcStringId SUNDAY;
	
	@ClientString(id = 1000445, message = "Monday")
	public static NpcStringId MONDAY;
	
	@ClientString(id = 1000446, message = "Tuesday")
	public static NpcStringId TUESDAY;
	
	@ClientString(id = 1000447, message = "Wednesday")
	public static NpcStringId WEDNESDAY;
	
	@ClientString(id = 1000448, message = "Thursday")
	public static NpcStringId THURSDAY;
	
	@ClientString(id = 1000449, message = "Friday")
	public static NpcStringId FRIDAY;
	
	@ClientString(id = 1000450, message = "Saturday")
	public static NpcStringId SATURDAY;
	
	@ClientString(id = 1000451, message = "Hour")
	public static NpcStringId HOUR;
	
	@ClientString(id = 1000452, message = "It's a good day to die! Welcome to hell, maggot!")
	public static NpcStringId IT_S_A_GOOD_DAY_TO_DIE_WELCOME_TO_HELL_MAGGOT;
	
	@ClientString(id = 1000453, message = "The Festival of Darkness will end in two minutes.")
	public static NpcStringId THE_FESTIVAL_OF_DARKNESS_WILL_END_IN_TWO_MINUTES;
	
	@ClientString(id = 1000454, message = "Noblesse Gate Pass")
	public static NpcStringId NOBLESSE_GATE_PASS;
	
	@ClientString(id = 1000455, message = " ")
	public static NpcStringId EMPTY_2;
	
	@ClientString(id = 1000456, message = "minute(s) have passed.")
	public static NpcStringId MINUTE_S_HAVE_PASSED;
	
	@ClientString(id = 1000457, message = "Game over. The teleport will appear momentarily.")
	public static NpcStringId GAME_OVER_THE_TELEPORT_WILL_APPEAR_MOMENTARILY;
	
	@ClientString(id = 1000458, message = "You, who are like the slugs crawling on the ground. The generosity and greatness of me, Daimon the White-Eyed is endless! Ha Ha Ha!")
	public static NpcStringId YOU_WHO_ARE_LIKE_THE_SLUGS_CRAWLING_ON_THE_GROUND_THE_GENEROSITY_AND_GREATNESS_OF_ME_DAIMON_THE_WHITE_EYED_IS_ENDLESS_HA_HA_HA;
	
	@ClientString(id = 1000459, message = "If you want to be the opponent of me, Daimon the White-Eyed, you should at least have the basic skills~!")
	public static NpcStringId IF_YOU_WANT_TO_BE_THE_OPPONENT_OF_ME_DAIMON_THE_WHITE_EYED_YOU_SHOULD_AT_LEAST_HAVE_THE_BASIC_SKILLS;
	
	@ClientString(id = 1000460, message = "You stupid creatures that are bound to the earth. You are suffering too much while dragging your fat, heavy bodies. I, Daimon, will lighten your burden.")
	public static NpcStringId YOU_STUPID_CREATURES_THAT_ARE_BOUND_TO_THE_EARTH_YOU_ARE_SUFFERING_TOO_MUCH_WHILE_DRAGGING_YOUR_FAT_HEAVY_BODIES_I_DAIMON_WILL_LIGHTEN_YOUR_BURDEN;
	
	@ClientString(id = 1000461, message = "A weak and stupid tribe like yours doesn't deserve to be my enemy! Bwa ha ha ha!")
	public static NpcStringId A_WEAK_AND_STUPID_TRIBE_LIKE_YOURS_DOESN_T_DESERVE_TO_BE_MY_ENEMY_BWA_HA_HA_HA;
	
	@ClientString(id = 1000462, message = "You dare to invade the territory of Daimon, the White-Eyed! Now, you will pay the price for your action!")
	public static NpcStringId YOU_DARE_TO_INVADE_THE_TERRITORY_OF_DAIMON_THE_WHITE_EYED_NOW_YOU_WILL_PAY_THE_PRICE_FOR_YOUR_ACTION;
	
	@ClientString(id = 1000463, message = "This is the grace of Daimon the White-Eyed, the great Monster Eye Lord! Ha Ha Ha!")
	public static NpcStringId THIS_IS_THE_GRACE_OF_DAIMON_THE_WHITE_EYED_THE_GREAT_MONSTER_EYE_LORD_HA_HA_HA;
	
	@ClientString(id = 1000464, message = "$s1! You have become a Hero of Duelists. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DUELISTS_CONGRATULATIONS;
	
	@ClientString(id = 1000465, message = "$s1! You have become a Hero of Dreadnoughts. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DREADNOUGHTS_CONGRATULATIONS;
	
	@ClientString(id = 1000466, message = "$s1! You have become a Hero of Phoenix Knights. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_PHOENIX_KNIGHTS_CONGRATULATIONS;
	
	@ClientString(id = 1000467, message = "$s1! You have become a Hero of Hell Knights. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_HELL_KNIGHTS_CONGRATULATIONS;
	
	@ClientString(id = 1000468, message = "$s1 You have become a Sagittarius Hero. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_SAGITTARIUS_HERO_CONGRATULATIONS;
	
	@ClientString(id = 1000469, message = "$s1! You have become a Hero of Adventurers. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ADVENTURERS_CONGRATULATIONS;
	
	@ClientString(id = 1000470, message = "$s1! You have become a Hero of Archmages. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ARCHMAGES_CONGRATULATIONS;
	
	@ClientString(id = 1000471, message = "$s1! You have become a Hero of Soultakers. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SOULTAKERS_CONGRATULATIONS;
	
	@ClientString(id = 1000472, message = "$s1! You have become a Hero of Arcana Lords. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ARCANA_LORDS_CONGRATULATIONS;
	
	@ClientString(id = 1000473, message = "$s1! You have become a Hero of Cardinals. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_CARDINALS_CONGRATULATIONS;
	
	@ClientString(id = 1000474, message = "$s1! You have become a Hero of Hierophants. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_HIEROPHANTS_CONGRATULATIONS;
	
	@ClientString(id = 1000475, message = "$s1! You have become a Hero of Eva's Templars. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_EVA_S_TEMPLARS_CONGRATULATIONS;
	
	@ClientString(id = 1000476, message = "$s1! You have become a Hero of Sword Muses. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SWORD_MUSES_CONGRATULATIONS;
	
	@ClientString(id = 1000477, message = "$s1! You have become a Hero of Wind Riders. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_WIND_RIDERS_CONGRATULATIONS;
	
	@ClientString(id = 1000478, message = "$s1! You have become a Hero of Moonlight Sentinels. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MOONLIGHT_SENTINELS_CONGRATULATIONS;
	
	@ClientString(id = 1000479, message = "$s1! You have become a Hero of Mystic Muses. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MYSTIC_MUSES_CONGRATULATIONS;
	
	@ClientString(id = 1000480, message = "$s1! You have become a Hero of Elemental Masters. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ELEMENTAL_MASTERS_CONGRATULATIONS;
	
	@ClientString(id = 1000481, message = "$s1! You have become a Hero of Eva's Saints. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_EVA_S_SAINTS_CONGRATULATIONS;
	
	@ClientString(id = 1000482, message = "$s1! You have become a Hero of the Shillien Templars. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_THE_SHILLIEN_TEMPLARS_CONGRATULATIONS;
	
	@ClientString(id = 1000483, message = "$s1! You have become a Hero of Spectral Dancers. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SPECTRAL_DANCERS_CONGRATULATIONS;
	
	@ClientString(id = 1000484, message = "$s1! You have become a Hero of Ghost Hunters. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GHOST_HUNTERS_CONGRATULATIONS;
	
	@ClientString(id = 1000485, message = "$s1! You have become a Hero of Ghost Sentinels. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GHOST_SENTINELS_CONGRATULATIONS;
	
	@ClientString(id = 1000486, message = "$s1! You have become a Hero of Storm Screamers. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_STORM_SCREAMERS_CONGRATULATIONS;
	
	@ClientString(id = 1000487, message = "$s1! You have become a Hero of Spectral Masters. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SPECTRAL_MASTERS_CONGRATULATIONS;
	
	@ClientString(id = 1000488, message = "$s1! You have become a Hero of the Shillien Saints. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_THE_SHILLIEN_SAINTS_CONGRATULATIONS;
	
	@ClientString(id = 1000489, message = "$s1! You have become a Hero of Titans. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_TITANS_CONGRATULATIONS;
	
	@ClientString(id = 1000490, message = "$s1! You have become a Hero of Grand Khavataris. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GRAND_KHAVATARIS_CONGRATULATIONS;
	
	@ClientString(id = 1000491, message = "$s1! You have become a Hero of Dominators. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOMINATORS_CONGRATULATIONS;
	
	@ClientString(id = 1000492, message = "$s1! You have become a Hero of Doomcryers. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOOMCRYERS_CONGRATULATIONS;
	
	@ClientString(id = 1000493, message = "$s1! You have become a Hero of Fortune Seekers. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_FORTUNE_SEEKERS_CONGRATULATIONS;
	
	@ClientString(id = 1000494, message = "$s1! You have become a Hero of Maestros. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MAESTROS_CONGRATULATIONS;
	
	@ClientString(id = 1000495, message = "**unregistered**")
	public static NpcStringId UNREGISTERED;
	
	@ClientString(id = 1000496, message = "$s1! You have become a Hero of Doombringers. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOOMBRINGERS_CONGRATULATIONS;
	
	@ClientString(id = 1000497, message = "$s1! You have become a Hero of Soul Hounds. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SOUL_HOUNDS_CONGRATULATIONS;
	
	@ClientString(id = 1000498, message = "$s1! You have become a Hero of Soul Hounds. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SOUL_HOUNDS_CONGRATULATIONS_2;
	
	@ClientString(id = 1000499, message = "$s1! You have become a Hero of Tricksters. Congratulations!")
	public static NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_TRICKSTERS_CONGRATULATIONS;
	
	@ClientString(id = 1000500, message = "You may now enter the Sepulcher.")
	public static NpcStringId YOU_MAY_NOW_ENTER_THE_SEPULCHER;
	
	@ClientString(id = 1000501, message = "If you place your hand on the stone statue in front of each sepulcher, you will be able to enter.")
	public static NpcStringId IF_YOU_PLACE_YOUR_HAND_ON_THE_STONE_STATUE_IN_FRONT_OF_EACH_SEPULCHER_YOU_WILL_BE_ABLE_TO_ENTER;
	
	@ClientString(id = 1000502, message = "The monsters have spawned!")
	public static NpcStringId THE_MONSTERS_HAVE_SPAWNED;
	
	@ClientString(id = 1000503, message = "Thank you for saving me.")
	public static NpcStringId THANK_YOU_FOR_SAVING_ME;
	
	@ClientString(id = 1000504, message = "Fewer than $s1")
	public static NpcStringId FEWER_THAN_S1;
	
	@ClientString(id = 1000505, message = "More than $s1")
	public static NpcStringId MORE_THAN_S1;
	
	@ClientString(id = 1000506, message = "Point")
	public static NpcStringId POINT;
	
	@ClientString(id = 1000507, message = "Competition")
	public static NpcStringId COMPETITION;
	
	@ClientString(id = 1000508, message = "Seal Validation")
	public static NpcStringId SEAL_VALIDATION;
	
	@ClientString(id = 1000509, message = "Preparation")
	public static NpcStringId PREPARATION;
	
	@ClientString(id = 1000510, message = "Dusk")
	public static NpcStringId DUSK_3;
	
	@ClientString(id = 1000511, message = "Dawn")
	public static NpcStringId DAWN_3;
	
	@ClientString(id = 1000512, message = "No Owner")
	public static NpcStringId NO_OWNER;
	
	@ClientString(id = 1000513, message = "This place is dangerous, $s1. Please turn back.")
	public static NpcStringId THIS_PLACE_IS_DANGEROUS_S1_PLEASE_TURN_BACK;
	
	@ClientString(id = 1000514, message = "Who disturbs my sacred sleep?")
	public static NpcStringId WHO_DISTURBS_MY_SACRED_SLEEP;
	
	@ClientString(id = 1000515, message = "Begone, thief! Let our bones rest in peace.")
	public static NpcStringId BEGONE_THIEF_LET_OUR_BONES_REST_IN_PEACE;
	
	@ClientString(id = 1000516, message = " Leave us be, Hestui scum!")
	public static NpcStringId LEAVE_US_BE_HESTUI_SCUM;
	
	@ClientString(id = 1000517, message = "Thieving Kakai, may bloodbugs gnaw you in your sleep!")
	public static NpcStringId THIEVING_KAKAI_MAY_BLOODBUGS_GNAW_YOU_IN_YOUR_SLEEP;
	
	@ClientString(id = 1000518, message = "Newbie Travel Token")
	public static NpcStringId NEWBIE_TRAVEL_TOKEN;
	
	@ClientString(id = 1000519, message = "Arrogant fool! You dare to challenge me, the Ruler of Flames? Here is your reward!")
	public static NpcStringId ARROGANT_FOOL_YOU_DARE_TO_CHALLENGE_ME_THE_RULER_OF_FLAMES_HERE_IS_YOUR_REWARD;
	
	@ClientString(id = 1000520, message = "$s1!!!! You cannot hope to defeat me with your meager strength.")
	public static NpcStringId S1_YOU_CANNOT_HOPE_TO_DEFEAT_ME_WITH_YOUR_MEAGER_STRENGTH;
	
	@ClientString(id = 1000521, message = "Not even the gods themselves could touch me. But you, $s1, you dare challenge me?! Ignorant mortal!")
	public static NpcStringId NOT_EVEN_THE_GODS_THEMSELVES_COULD_TOUCH_ME_BUT_YOU_S1_YOU_DARE_CHALLENGE_ME_IGNORANT_MORTAL;
	
	@ClientString(id = 1000522, message = "Requiem of Hatred")
	public static NpcStringId REQUIEM_OF_HATRED;
	
	@ClientString(id = 1000523, message = "Fugue of Jubilation")
	public static NpcStringId FUGUE_OF_JUBILATION;
	
	@ClientString(id = 1000524, message = "Frenetic Toccata")
	public static NpcStringId FRENETIC_TOCCATA;
	
	@ClientString(id = 1000525, message = "Hypnotic Mazurka")
	public static NpcStringId HYPNOTIC_MAZURKA;
	
	@ClientString(id = 1000526, message = "Mournful Chorale Prelude")
	public static NpcStringId MOURNFUL_CHORALE_PRELUDE;
	
	@ClientString(id = 1000527, message = "Rondo of Solitude")
	public static NpcStringId RONDO_OF_SOLITUDE;
	
	@ClientString(id = 1000528, message = "Olympiad Token")
	public static NpcStringId OLYMPIAD_TOKEN;
	
	@ClientString(id = 1001000, message = "The Kingdom of Aden")
	public static NpcStringId THE_KINGDOM_OF_ADEN;
	
	@ClientString(id = 1001001, message = "Gludio")
	public static NpcStringId GLUDIO_2;
	
	@ClientString(id = 1001002, message = "Dion")
	public static NpcStringId DION_2;
	
	@ClientString(id = 1001003, message = "Giran")
	public static NpcStringId GIRAN_2;
	
	@ClientString(id = 1001004, message = "Oren")
	public static NpcStringId OREN_2;
	
	@ClientString(id = 1001005, message = "Aden")
	public static NpcStringId ADEN_2;
	
	@ClientString(id = 1001006, message = "Innadril")
	public static NpcStringId INNADRIL_2;
	
	@ClientString(id = 1001007, message = "Goddard")
	public static NpcStringId GODDARD_2;
	
	@ClientString(id = 1001008, message = "Rune")
	public static NpcStringId RUNE_2;
	
	@ClientString(id = 1001009, message = "Schuttgart")
	public static NpcStringId SCHUTTGART_2;
	
	@ClientString(id = 1001100, message = "The Kingdom of Elmore")
	public static NpcStringId THE_KINGDOM_OF_ELMORE;
	
	@ClientString(id = 1010001, message = "Talking Island Village")
	public static NpcStringId TALKING_ISLAND_VILLAGE;
	
	@ClientString(id = 1010002, message = "The Elven Village")
	public static NpcStringId THE_ELVEN_VILLAGE;
	
	@ClientString(id = 1010003, message = "The Dark Elf Village")
	public static NpcStringId THE_DARK_ELF_VILLAGE;
	
	@ClientString(id = 1010004, message = "The Village of Gludin")
	public static NpcStringId THE_VILLAGE_OF_GLUDIN;
	
	@ClientString(id = 1010005, message = "The Town of Gludio")
	public static NpcStringId THE_TOWN_OF_GLUDIO;
	
	@ClientString(id = 1010006, message = "The Town of Dion")
	public static NpcStringId THE_TOWN_OF_DION;
	
	@ClientString(id = 1010007, message = "The Town of Giran")
	public static NpcStringId THE_TOWN_OF_GIRAN;
	
	@ClientString(id = 1010008, message = "Orc Village")
	public static NpcStringId ORC_VILLAGE;
	
	@ClientString(id = 1010009, message = "Dwarven Village")
	public static NpcStringId DWARVEN_VILLAGE;
	
	@ClientString(id = 1010010, message = "The Southern Part of the Dark Forest")
	public static NpcStringId THE_SOUTHERN_PART_OF_THE_DARK_FOREST;
	
	@ClientString(id = 1010011, message = "The Northeast Coast")
	public static NpcStringId THE_NORTHEAST_COAST;
	
	@ClientString(id = 1010012, message = "The Southern Entrance to the Wastelandss")
	public static NpcStringId THE_SOUTHERN_ENTRANCE_TO_THE_WASTELANDSS;
	
	@ClientString(id = 1010013, message = "Town of Oren")
	public static NpcStringId TOWN_OF_OREN;
	
	@ClientString(id = 1010014, message = "Ivory Tower")
	public static NpcStringId IVORY_TOWER;
	
	@ClientString(id = 1010015, message = "1st Floor Lobby")
	public static NpcStringId FIRST_FLOOR_LOBBY;
	
	@ClientString(id = 1010016, message = "Underground Shopping Area")
	public static NpcStringId UNDERGROUND_SHOPPING_AREA;
	
	@ClientString(id = 1010017, message = "2nd Floor Human Wizard Guild")
	public static NpcStringId SECOND_FLOOR_HUMAN_WIZARD_GUILD;
	
	@ClientString(id = 1010018, message = "3rd Floor Elven Wizard Guild")
	public static NpcStringId THIRD_FLOOR_ELVEN_WIZARD_GUILD;
	
	@ClientString(id = 1010019, message = "4th Floor Dark Wizard Guild")
	public static NpcStringId FOURTH_FLOOR_DARK_WIZARD_GUILD;
	
	@ClientString(id = 1010020, message = "Hunters Village")
	public static NpcStringId HUNTERS_VILLAGE;
	
	@ClientString(id = 1010021, message = "Giran Harbor")
	public static NpcStringId GIRAN_HARBOR;
	
	@ClientString(id = 1010022, message = "Hardin's Private Academy")
	public static NpcStringId HARDIN_S_PRIVATE_ACADEMY;
	
	@ClientString(id = 1010023, message = "Town of Aden")
	public static NpcStringId TOWN_OF_ADEN;
	
	@ClientString(id = 1010024, message = "Village Square")
	public static NpcStringId VILLAGE_SQUARE;
	
	@ClientString(id = 1010025, message = "North Gate Entrance")
	public static NpcStringId NORTH_GATE_ENTRANCE;
	
	@ClientString(id = 1010026, message = "East Gate Entrance")
	public static NpcStringId EAST_GATE_ENTRANCE;
	
	@ClientString(id = 1010027, message = "West Gate Entrance")
	public static NpcStringId WEST_GATE_ENTRANCE;
	
	@ClientString(id = 1010028, message = "South Gate Entrance")
	public static NpcStringId SOUTH_GATE_ENTRANCE;
	
	@ClientString(id = 1010029, message = "Entrance to Turek Orc Camp")
	public static NpcStringId ENTRANCE_TO_TUREK_ORC_CAMP;
	
	@ClientString(id = 1010030, message = "Entrance to Forgotten Temple")
	public static NpcStringId ENTRANCE_TO_FORGOTTEN_TEMPLE;
	
	@ClientString(id = 1010031, message = "Entrance to the Wastelands")
	public static NpcStringId ENTRANCE_TO_THE_WASTELANDS;
	
	@ClientString(id = 1010032, message = "Entrance to Abandoned Camp")
	public static NpcStringId ENTRANCE_TO_ABANDONED_CAMP;
	
	@ClientString(id = 1010033, message = "Entrance to Cruma Marshlands")
	public static NpcStringId ENTRANCE_TO_CRUMA_MARSHLANDS;
	
	@ClientString(id = 1010034, message = "Entrance to Execution Grounds")
	public static NpcStringId ENTRANCE_TO_EXECUTION_GROUNDS;
	
	@ClientString(id = 1010035, message = "Entrance to the Fortress of Resistance")
	public static NpcStringId ENTRANCE_TO_THE_FORTRESS_OF_RESISTANCE;
	
	@ClientString(id = 1010036, message = "Entrance to Floran Village")
	public static NpcStringId ENTRANCE_TO_FLORAN_VILLAGE;
	
	@ClientString(id = 1010037, message = "Neutral Zone")
	public static NpcStringId NEUTRAL_ZONE;
	
	@ClientString(id = 1010038, message = "Western Road of Giran")
	public static NpcStringId WESTERN_ROAD_OF_GIRAN;
	
	@ClientString(id = 1010039, message = "Eastern Road of Gludin Village")
	public static NpcStringId EASTERN_ROAD_OF_GLUDIN_VILLAGE;
	
	@ClientString(id = 1010040, message = "Entrance to the Fortress of Resistance")
	public static NpcStringId ENTRANCE_TO_THE_FORTRESS_OF_RESISTANCE_2;
	
	@ClientString(id = 1010041, message = "Entrance to Cruma Tower")
	public static NpcStringId ENTRANCE_TO_CRUMA_TOWER;
	
	@ClientString(id = 1010042, message = "Death Pass")
	public static NpcStringId DEATH_PASS;
	
	@ClientString(id = 1010043, message = "Northern part of the Marshlands")
	public static NpcStringId NORTHERN_PART_OF_THE_MARSHLANDS;
	
	@ClientString(id = 1010044, message = "Northeast of the Neutral Zone")
	public static NpcStringId NORTHEAST_OF_THE_NEUTRAL_ZONE;
	
	@ClientString(id = 1010045, message = "Immortal Plateau, Central Region")
	public static NpcStringId IMMORTAL_PLATEAU_CENTRAL_REGION;
	
	@ClientString(id = 1010046, message = "Immortal Plateau, Southern Region")
	public static NpcStringId IMMORTAL_PLATEAU_SOUTHERN_REGION;
	
	@ClientString(id = 1010047, message = "Immortal Plateau, Southeast Region")
	public static NpcStringId IMMORTAL_PLATEAU_SOUTHEAST_REGION;
	
	@ClientString(id = 1010048, message = "Frozen Waterfall")
	public static NpcStringId FROZEN_WATERFALL;
	
	@ClientString(id = 1010049, message = "Heine")
	public static NpcStringId HEINE;
	
	@ClientString(id = 1010050, message = "Tower of Insolence - 1st Floor")
	public static NpcStringId TOWER_OF_INSOLENCE_1ST_FLOOR;
	
	@ClientString(id = 1010051, message = "Tower of Insolence - 5th Floor")
	public static NpcStringId TOWER_OF_INSOLENCE_5TH_FLOOR;
	
	@ClientString(id = 1010052, message = "Tower of Insolence - 10th Floor")
	public static NpcStringId TOWER_OF_INSOLENCE_10TH_FLOOR;
	
	@ClientString(id = 1010053, message = "Coliseum")
	public static NpcStringId COLISEUM;
	
	@ClientString(id = 1010054, message = "Monster Derby")
	public static NpcStringId MONSTER_DERBY;
	
	@ClientString(id = 1010055, message = "Near the frontier post")
	public static NpcStringId NEAR_THE_FRONTIER_POST;
	
	@ClientString(id = 1010056, message = "Entrance to the Sea of Spores")
	public static NpcStringId ENTRANCE_TO_THE_SEA_OF_SPORES;
	
	@ClientString(id = 1010057, message = "An old battlefield")
	public static NpcStringId AN_OLD_BATTLEFIELD;
	
	@ClientString(id = 1010058, message = "Entrance to Enchanted Valley")
	public static NpcStringId ENTRANCE_TO_ENCHANTED_VALLEY;
	
	@ClientString(id = 1010059, message = "Entrance to the Tower of Insolence")
	public static NpcStringId ENTRANCE_TO_THE_TOWER_OF_INSOLENCE;
	
	@ClientString(id = 1010060, message = "Blazing Swamp")
	public static NpcStringId BLAZING_SWAMP;
	
	@ClientString(id = 1010061, message = "Entrance to the Cemetery")
	public static NpcStringId ENTRANCE_TO_THE_CEMETERY;
	
	@ClientString(id = 1010062, message = "Entrance to The Giant's Cave")
	public static NpcStringId ENTRANCE_TO_THE_GIANT_S_CAVE;
	
	@ClientString(id = 1010063, message = "Entrance to the Forest of Mirrors")
	public static NpcStringId ENTRANCE_TO_THE_FOREST_OF_MIRRORS;
	
	@ClientString(id = 1010064, message = "The Forbidden Gateway")
	public static NpcStringId THE_FORBIDDEN_GATEWAY;
	
	@ClientString(id = 1010065, message = "Entrance to the Tower of Insolence")
	public static NpcStringId ENTRANCE_TO_THE_TOWER_OF_INSOLENCE_2;
	
	@ClientString(id = 1010066, message = "Entrance to the Tanor Silenos Barracks")
	public static NpcStringId ENTRANCE_TO_THE_TANOR_SILENOS_BARRACKS;
	
	@ClientString(id = 1010067, message = "Dragon Valley")
	public static NpcStringId DRAGON_VALLEY;
	
	@ClientString(id = 1010068, message = "Oracle of Dawn")
	public static NpcStringId ORACLE_OF_DAWN;
	
	@ClientString(id = 1010069, message = "Oracle of Dusk")
	public static NpcStringId ORACLE_OF_DUSK;
	
	@ClientString(id = 1010070, message = "Necropolis of Sacrifice")
	public static NpcStringId NECROPOLIS_OF_SACRIFICE;
	
	@ClientString(id = 1010071, message = "The Pilgrim's Necropolis")
	public static NpcStringId THE_PILGRIM_S_NECROPOLIS;
	
	@ClientString(id = 1010072, message = "Necropolis of Worship")
	public static NpcStringId NECROPOLIS_OF_WORSHIP;
	
	@ClientString(id = 1010073, message = "The Patriot's Necropolis")
	public static NpcStringId THE_PATRIOT_S_NECROPOLIS;
	
	@ClientString(id = 1010074, message = "Necropolis of Devotion")
	public static NpcStringId NECROPOLIS_OF_DEVOTION;
	
	@ClientString(id = 1010075, message = "Necropolis of Martyrdom")
	public static NpcStringId NECROPOLIS_OF_MARTYRDOM;
	
	@ClientString(id = 1010076, message = "The Disciple's Necropolis ")
	public static NpcStringId THE_DISCIPLE_S_NECROPOLIS;
	
	@ClientString(id = 1010077, message = "The Saint's Necropolis")
	public static NpcStringId THE_SAINT_S_NECROPOLIS;
	
	@ClientString(id = 1010078, message = "The Catacomb of the Heretic")
	public static NpcStringId THE_CATACOMB_OF_THE_HERETIC;
	
	@ClientString(id = 1010079, message = "Catacomb of the Branded")
	public static NpcStringId CATACOMB_OF_THE_BRANDED;
	
	@ClientString(id = 1010080, message = "Catacomb of the Apostate")
	public static NpcStringId CATACOMB_OF_THE_APOSTATE;
	
	@ClientString(id = 1010081, message = "Catacomb of the Witch")
	public static NpcStringId CATACOMB_OF_THE_WITCH;
	
	@ClientString(id = 1010082, message = "Catacomb of Dark Omens")
	public static NpcStringId CATACOMB_OF_DARK_OMENS;
	
	@ClientString(id = 1010083, message = "Catacomb of the Forbidden Path")
	public static NpcStringId CATACOMB_OF_THE_FORBIDDEN_PATH;
	
	@ClientString(id = 1010084, message = "Entrance to the Ruins of Agony")
	public static NpcStringId ENTRANCE_TO_THE_RUINS_OF_AGONY;
	
	@ClientString(id = 1010085, message = "Entrance to the Ruins of Despair")
	public static NpcStringId ENTRANCE_TO_THE_RUINS_OF_DESPAIR;
	
	@ClientString(id = 1010086, message = "Entrance to the Ant Nest")
	public static NpcStringId ENTRANCE_TO_THE_ANT_NEST;
	
	@ClientString(id = 1010087, message = "Southern Dion")
	public static NpcStringId SOUTHERN_DION;
	
	@ClientString(id = 1010088, message = "Entrance to Dragon Valley")
	public static NpcStringId ENTRANCE_TO_DRAGON_VALLEY;
	
	@ClientString(id = 1010089, message = "Field of Silence")
	public static NpcStringId FIELD_OF_SILENCE;
	
	@ClientString(id = 1010090, message = "Field of Whispers")
	public static NpcStringId FIELD_OF_WHISPERS;
	
	@ClientString(id = 1010091, message = "Entrance to Alligator Island")
	public static NpcStringId ENTRANCE_TO_ALLIGATOR_ISLAND;
	
	@ClientString(id = 1010092, message = "Southern Plain of Oren")
	public static NpcStringId SOUTHERN_PLAIN_OF_OREN;
	
	@ClientString(id = 1010093, message = "Entrance to the Bandit Stronghold")
	public static NpcStringId ENTRANCE_TO_THE_BANDIT_STRONGHOLD;
	
	@ClientString(id = 1010094, message = "Windy Hill")
	public static NpcStringId WINDY_HILL;
	
	@ClientString(id = 1010095, message = "Orc Barracks")
	public static NpcStringId ORC_BARRACKS;
	
	@ClientString(id = 1010096, message = "Fellmere Harvesting Grounds")
	public static NpcStringId FELLMERE_HARVESTING_GROUNDS;
	
	@ClientString(id = 1010097, message = "Ruins of Agony")
	public static NpcStringId RUINS_OF_AGONY;
	
	@ClientString(id = 1010098, message = "Abandoned Camp")
	public static NpcStringId ABANDONED_CAMP;
	
	@ClientString(id = 1010099, message = "Red Rock Ridge")
	public static NpcStringId RED_ROCK_RIDGE;
	
	@ClientString(id = 1010100, message = "Langk Lizardman Dwellings")
	public static NpcStringId LANGK_LIZARDMAN_DWELLINGS;
	
	@ClientString(id = 1010101, message = "Ruins of Despair")
	public static NpcStringId RUINS_OF_DESPAIR;
	
	@ClientString(id = 1010102, message = "Windawood Manor")
	public static NpcStringId WINDAWOOD_MANOR;
	
	@ClientString(id = 1010103, message = "Northern Pathway to the Wastelands")
	public static NpcStringId NORTHERN_PATHWAY_TO_THE_WASTELANDS;
	
	@ClientString(id = 1010104, message = "Western Pathway to the Wastelands")
	public static NpcStringId WESTERN_PATHWAY_TO_THE_WASTELANDS;
	
	@ClientString(id = 1010105, message = "Southern Pathway to the Wastelands")
	public static NpcStringId SOUTHERN_PATHWAY_TO_THE_WASTELANDS;
	
	@ClientString(id = 1010106, message = "Forgotten Temple")
	public static NpcStringId FORGOTTEN_TEMPLE;
	
	@ClientString(id = 1010107, message = "South Entrance of Ant Nest")
	public static NpcStringId SOUTH_ENTRANCE_OF_ANT_NEST;
	
	@ClientString(id = 1010108, message = "East Entrance of Ant Nest")
	public static NpcStringId EAST_ENTRANCE_OF_ANT_NEST;
	
	@ClientString(id = 1010109, message = "West Entrance of Ant Nest")
	public static NpcStringId WEST_ENTRANCE_OF_ANT_NEST;
	
	@ClientString(id = 1010110, message = "Cruma Marshland")
	public static NpcStringId CRUMA_MARSHLAND;
	
	@ClientString(id = 1010111, message = "Plains of Dion")
	public static NpcStringId PLAINS_OF_DION;
	
	@ClientString(id = 1010112, message = "Bee Hive")
	public static NpcStringId BEE_HIVE;
	
	@ClientString(id = 1010113, message = "Fortress of Resistance")
	public static NpcStringId FORTRESS_OF_RESISTANCE;
	
	@ClientString(id = 1010114, message = "Execution Grounds")
	public static NpcStringId EXECUTION_GROUNDS;
	
	@ClientString(id = 1010115, message = "Tanor Canyon")
	public static NpcStringId TANOR_CANYON;
	
	@ClientString(id = 1010116, message = "Cruma Tower")
	public static NpcStringId CRUMA_TOWER;
	
	@ClientString(id = 1010117, message = "Three-way crossroads at Dragon Valley")
	public static NpcStringId THREE_WAY_CROSSROADS_AT_DRAGON_VALLEY;
	
	@ClientString(id = 1010118, message = "Breka's Stronghold")
	public static NpcStringId BREKA_S_STRONGHOLD;
	
	@ClientString(id = 1010119, message = "Gorgon Flower Garden")
	public static NpcStringId GORGON_FLOWER_GARDEN;
	
	@ClientString(id = 1010120, message = "Antharas's Lair")
	public static NpcStringId ANTHARAS_S_LAIR;
	
	@ClientString(id = 1010121, message = "Sea of Spores")
	public static NpcStringId SEA_OF_SPORES;
	
	@ClientString(id = 1010122, message = "Outlaw Forest")
	public static NpcStringId OUTLAW_FOREST;
	
	@ClientString(id = 1010123, message = "Forest of Evil and the Ivory Tower")
	public static NpcStringId FOREST_OF_EVIL_AND_THE_IVORY_TOWER;
	
	@ClientString(id = 1010124, message = "Timak Outpost ")
	public static NpcStringId TIMAK_OUTPOST;
	
	@ClientString(id = 1010125, message = "Great Plains of Oren")
	public static NpcStringId GREAT_PLAINS_OF_OREN;
	
	@ClientString(id = 1010126, message = "Alchemist's Hut")
	public static NpcStringId ALCHEMIST_S_HUT;
	
	@ClientString(id = 1010127, message = "Ancient Battleground")
	public static NpcStringId ANCIENT_BATTLEGROUND;
	
	@ClientString(id = 1010128, message = "Northern Pathway of the Enchanted Valley")
	public static NpcStringId NORTHERN_PATHWAY_OF_THE_ENCHANTED_VALLEY;
	
	@ClientString(id = 1010129, message = "Southern Pathway of the Enchanted Valley")
	public static NpcStringId SOUTHERN_PATHWAY_OF_THE_ENCHANTED_VALLEY;
	
	@ClientString(id = 1010130, message = "Hunters Valley")
	public static NpcStringId HUNTERS_VALLEY;
	
	@ClientString(id = 1010131, message = "Western Entrance of Blazing Swamp")
	public static NpcStringId WESTERN_ENTRANCE_OF_BLAZING_SWAMP;
	
	@ClientString(id = 1010132, message = "Eastern Entrance of Blazing Swamp")
	public static NpcStringId EASTERN_ENTRANCE_OF_BLAZING_SWAMP;
	
	@ClientString(id = 1010133, message = "Plains of Glory")
	public static NpcStringId PLAINS_OF_GLORY;
	
	@ClientString(id = 1010134, message = "War-Torn Plains")
	public static NpcStringId WAR_TORN_PLAINS;
	
	@ClientString(id = 1010135, message = "Northwestern Passage to the Forest of Mirrors")
	public static NpcStringId NORTHWESTERN_PASSAGE_TO_THE_FOREST_OF_MIRRORS;
	
	@ClientString(id = 1010136, message = "The Front of Anghel Waterfall")
	public static NpcStringId THE_FRONT_OF_ANGHEL_WATERFALL;
	
	@ClientString(id = 1010137, message = "South Entrance of Devastated Castle")
	public static NpcStringId SOUTH_ENTRANCE_OF_DEVASTATED_CASTLE;
	
	@ClientString(id = 1010138, message = "North Entrance of Devastated Castle")
	public static NpcStringId NORTH_ENTRANCE_OF_DEVASTATED_CASTLE;
	
	@ClientString(id = 1010139, message = "North Entrance of the Cemetery")
	public static NpcStringId NORTH_ENTRANCE_OF_THE_CEMETERY;
	
	@ClientString(id = 1010140, message = "South Entrance of the Cemetery")
	public static NpcStringId SOUTH_ENTRANCE_OF_THE_CEMETERY;
	
	@ClientString(id = 1010141, message = "West Entrance of the Cemetery")
	public static NpcStringId WEST_ENTRANCE_OF_THE_CEMETERY;
	
	@ClientString(id = 1010142, message = "Entrance of the Forbidden Gateway")
	public static NpcStringId ENTRANCE_OF_THE_FORBIDDEN_GATEWAY;
	
	@ClientString(id = 1010143, message = "Forsaken Plains")
	public static NpcStringId FORSAKEN_PLAINS;
	
	@ClientString(id = 1010144, message = "Tower of Insolence")
	public static NpcStringId TOWER_OF_INSOLENCE;
	
	@ClientString(id = 1010145, message = "The Giant's Cave")
	public static NpcStringId THE_GIANT_S_CAVE;
	
	@ClientString(id = 1010146, message = "Northern Part of the Field of Silence")
	public static NpcStringId NORTHERN_PART_OF_THE_FIELD_OF_SILENCE;
	
	@ClientString(id = 1010147, message = "Western Part of the Field of Silence")
	public static NpcStringId WESTERN_PART_OF_THE_FIELD_OF_SILENCE;
	
	@ClientString(id = 1010148, message = "Eastern Part of the Field of Silence")
	public static NpcStringId EASTERN_PART_OF_THE_FIELD_OF_SILENCE;
	
	@ClientString(id = 1010149, message = "Western Part of the Field of Whispers")
	public static NpcStringId WESTERN_PART_OF_THE_FIELD_OF_WHISPERS;
	
	@ClientString(id = 1010150, message = "Alligator Island")
	public static NpcStringId ALLIGATOR_ISLAND;
	
	@ClientString(id = 1010151, message = "Alligator Beach")
	public static NpcStringId ALLIGATOR_BEACH;
	
	@ClientString(id = 1010152, message = "Devil's Isle")
	public static NpcStringId DEVIL_S_ISLE;
	
	@ClientString(id = 1010153, message = "Garden of Eva")
	public static NpcStringId GARDEN_OF_EVA;
	
	@ClientString(id = 1010154, message = "Talking Island")
	public static NpcStringId TALKING_ISLAND;
	
	@ClientString(id = 1010155, message = "Elven Village")
	public static NpcStringId ELVEN_VILLAGE;
	
	@ClientString(id = 1010156, message = "Dark Elf Village")
	public static NpcStringId DARK_ELF_VILLAGE;
	
	@ClientString(id = 1010157, message = "Orc Village")
	public static NpcStringId ORC_VILLAGE_2;
	
	@ClientString(id = 1010158, message = "Dwarven Village")
	public static NpcStringId DWARVEN_VILLAGE_2;
	
	@ClientString(id = 1010159, message = "Scenic Deck of Iris Lake")
	public static NpcStringId SCENIC_DECK_OF_IRIS_LAKE;
	
	@ClientString(id = 1010160, message = "Altar of Rites")
	public static NpcStringId ALTAR_OF_RITES;
	
	@ClientString(id = 1010161, message = "Dark Forest, Waterfall")
	public static NpcStringId DARK_FOREST_WATERFALL;
	
	@ClientString(id = 1010162, message = "Three-way Crossroads of the Neutral Zone")
	public static NpcStringId THREE_WAY_CROSSROADS_OF_THE_NEUTRAL_ZONE;
	
	@ClientString(id = 1010163, message = "Dark Forest")
	public static NpcStringId DARK_FOREST;
	
	@ClientString(id = 1010164, message = "Swampland")
	public static NpcStringId SWAMPLAND;
	
	@ClientString(id = 1010165, message = "Black Rock Hill")
	public static NpcStringId BLACK_ROCK_HILL;
	
	@ClientString(id = 1010166, message = "Spider Nest")
	public static NpcStringId SPIDER_NEST;
	
	@ClientString(id = 1010167, message = "Elven Forest")
	public static NpcStringId ELVEN_FOREST;
	
	@ClientString(id = 1010168, message = "Obelisk of Victory")
	public static NpcStringId OBELISK_OF_VICTORY;
	
	@ClientString(id = 1010169, message = "Northern Territory of Talking Island")
	public static NpcStringId NORTHERN_TERRITORY_OF_TALKING_ISLAND;
	
	@ClientString(id = 1010170, message = "Southern Territory of Talking Island")
	public static NpcStringId SOUTHERN_TERRITORY_OF_TALKING_ISLAND;
	
	@ClientString(id = 1010171, message = "Evil Hunting Grounds")
	public static NpcStringId EVIL_HUNTING_GROUNDS;
	
	@ClientString(id = 1010172, message = "Maille Lizardmen Barracks")
	public static NpcStringId MAILLE_LIZARDMEN_BARRACKS;
	
	@ClientString(id = 1010173, message = "Ruins of Agony Bend")
	public static NpcStringId RUINS_OF_AGONY_BEND;
	
	@ClientString(id = 1010174, message = "The Entrance to the Ruins of Despair")
	public static NpcStringId THE_ENTRANCE_TO_THE_RUINS_OF_DESPAIR;
	
	@ClientString(id = 1010175, message = "Windmill Hill")
	public static NpcStringId WINDMILL_HILL;
	
	@ClientString(id = 1010176, message = "Red Rock Ridge")
	public static NpcStringId RED_ROCK_RIDGE_2;
	
	@ClientString(id = 1010177, message = "Floran Agricultural Area")
	public static NpcStringId FLORAN_AGRICULTURAL_AREA;
	
	@ClientString(id = 1010178, message = "Western Tanor Canyon")
	public static NpcStringId WESTERN_TANOR_CANYON;
	
	@ClientString(id = 1010179, message = "Plains of the Lizardmen")
	public static NpcStringId PLAINS_OF_THE_LIZARDMEN;
	
	@ClientString(id = 1010180, message = "Forest of Evil")
	public static NpcStringId FOREST_OF_EVIL;
	
	@ClientString(id = 1010181, message = "Fields of Massacre")
	public static NpcStringId FIELDS_OF_MASSACRE;
	
	@ClientString(id = 1010182, message = "Silent Valley")
	public static NpcStringId SILENT_VALLEY;
	
	@ClientString(id = 1010183, message = "Northern Area of the Immortal Plateau, Northern Region")
	public static NpcStringId NORTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_NORTHERN_REGION;
	
	@ClientString(id = 1010184, message = "Southern Area of the Immortal Plateau, Northern Region")
	public static NpcStringId SOUTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_NORTHERN_REGION;
	
	@ClientString(id = 1010185, message = "Northern Area of the Immortal Plateau, Southern Region")
	public static NpcStringId NORTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_SOUTHERN_REGION;
	
	@ClientString(id = 1010186, message = "Southern Area of the Immortal Plateau, Southern Region")
	public static NpcStringId SOUTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_SOUTHERN_REGION;
	
	@ClientString(id = 1010187, message = "Western Mining Zone")
	public static NpcStringId WESTERN_MINING_ZONE;
	
	@ClientString(id = 1010188, message = "Western Mining Zone (Central Shore)")
	public static NpcStringId WESTERN_MINING_ZONE_CENTRAL_SHORE;
	
	@ClientString(id = 1010189, message = "Eastern Mining Zone (Northeastern Shore)")
	public static NpcStringId EASTERN_MINING_ZONE_NORTHEASTERN_SHORE;
	
	@ClientString(id = 1010190, message = "Entrance to the Abandoned Coal Mines")
	public static NpcStringId ENTRANCE_TO_THE_ABANDONED_COAL_MINES;
	
	@ClientString(id = 1010191, message = "Entrance to the Mithril Mines")
	public static NpcStringId ENTRANCE_TO_THE_MITHRIL_MINES;
	
	@ClientString(id = 1010192, message = "West Area of the Devastated Castle")
	public static NpcStringId WEST_AREA_OF_THE_DEVASTATED_CASTLE;
	
	@ClientString(id = 1010193, message = "Tower of Insolence, 3rd Floor")
	public static NpcStringId TOWER_OF_INSOLENCE_3RD_FLOOR;
	
	@ClientString(id = 1010194, message = "Tower of Insolence, 5th Floor")
	public static NpcStringId TOWER_OF_INSOLENCE_5TH_FLOOR_2;
	
	@ClientString(id = 1010195, message = "Tower of Insolence, 7th Floor")
	public static NpcStringId TOWER_OF_INSOLENCE_7TH_FLOOR;
	
	@ClientString(id = 1010196, message = "Tower of Insolence, 10th Floor")
	public static NpcStringId TOWER_OF_INSOLENCE_10TH_FLOOR_2;
	
	@ClientString(id = 1010197, message = "Tower of Insolence, 13th Floor")
	public static NpcStringId TOWER_OF_INSOLENCE_13TH_FLOOR;
	
	@ClientString(id = 1010198, message = "Southern Shore of Innadril (Garden of Eva)")
	public static NpcStringId SOUTHERN_SHORE_OF_INNADRIL_GARDEN_OF_EVA;
	
	@ClientString(id = 1010199, message = "Town of Goddard")
	public static NpcStringId TOWN_OF_GODDARD;
	
	@ClientString(id = 1010200, message = "Rune Township")
	public static NpcStringId RUNE_TOWNSHIP;
	
	@ClientString(id = 1010201, message = "A delivery for Mr. Lector? Very good!")
	public static NpcStringId A_DELIVERY_FOR_MR_LECTOR_VERY_GOOD;
	
	@ClientString(id = 1010202, message = "I need a break!")
	public static NpcStringId I_NEED_A_BREAK;
	
	@ClientString(id = 1010203, message = "Hello, Mr. Lector! Long time no see, Mr. Jackson!")
	public static NpcStringId HELLO_MR_LECTOR_LONG_TIME_NO_SEE_MR_JACKSON;
	
	@ClientString(id = 1010204, message = "Lulu!")
	public static NpcStringId LULU;
	
	@ClientString(id = 1010205, message = "Where has he gone?")
	public static NpcStringId WHERE_HAS_HE_GONE;
	
	@ClientString(id = 1010206, message = "Have you seen Windawood?")
	public static NpcStringId HAVE_YOU_SEEN_WINDAWOOD;
	
	@ClientString(id = 1010207, message = "Where did he go?")
	public static NpcStringId WHERE_DID_HE_GO;
	
	@ClientString(id = 1010208, message = "The Mother Tree is slowly dying.")
	public static NpcStringId THE_MOTHER_TREE_IS_SLOWLY_DYING;
	
	@ClientString(id = 1010209, message = "How can we save the Mother Tree?")
	public static NpcStringId HOW_CAN_WE_SAVE_THE_MOTHER_TREE;
	
	@ClientString(id = 1010210, message = "The Mother Tree is always so gorgeous!")
	public static NpcStringId THE_MOTHER_TREE_IS_ALWAYS_SO_GORGEOUS;
	
	@ClientString(id = 1010211, message = "Lady Mirabel, may the peace of the lake be with you!")
	public static NpcStringId LADY_MIRABEL_MAY_THE_PEACE_OF_THE_LAKE_BE_WITH_YOU;
	
	@ClientString(id = 1010212, message = "You're a hard worker, Rayla!")
	public static NpcStringId YOU_RE_A_HARD_WORKER_RAYLA;
	
	@ClientString(id = 1010213, message = "You're a hard worker!")
	public static NpcStringId YOU_RE_A_HARD_WORKER;
	
	@ClientString(id = 1010214, message = "The mass of darkness will start in a couple of days. Pay more attention to the guard!")
	public static NpcStringId THE_MASS_OF_DARKNESS_WILL_START_IN_A_COUPLE_OF_DAYS_PAY_MORE_ATTENTION_TO_THE_GUARD;
	
	@ClientString(id = 1010215, message = "Have you seen Torocco today?")
	public static NpcStringId HAVE_YOU_SEEN_TOROCCO_TODAY;
	
	@ClientString(id = 1010216, message = "Have you seen Torocco?")
	public static NpcStringId HAVE_YOU_SEEN_TOROCCO;
	
	@ClientString(id = 1010217, message = "Where is that fool hiding?")
	public static NpcStringId WHERE_IS_THAT_FOOL_HIDING;
	
	@ClientString(id = 1010218, message = "Care to go a round?")
	public static NpcStringId CARE_TO_GO_A_ROUND;
	
	@ClientString(id = 1010219, message = "Have a nice day, Mr. Garita and Mion!")
	public static NpcStringId HAVE_A_NICE_DAY_MR_GARITA_AND_MION;
	
	@ClientString(id = 1010220, message = "Mr. Lid, Murdoc, and Airy! How are you doing?")
	public static NpcStringId MR_LID_MURDOC_AND_AIRY_HOW_ARE_YOU_DOING;
	
	@ClientString(id = 1010221, message = "A black moon... Now do you understand that he has opened his eyes?")
	public static NpcStringId A_BLACK_MOON_NOW_DO_YOU_UNDERSTAND_THAT_HE_HAS_OPENED_HIS_EYES;
	
	@ClientString(id = 1010222, message = "Clouds of blood are gathering. Soon, it will start to rain. The rain of crimson blood...")
	public static NpcStringId CLOUDS_OF_BLOOD_ARE_GATHERING_SOON_IT_WILL_START_TO_RAIN_THE_RAIN_OF_CRIMSON_BLOOD;
	
	@ClientString(id = 1010223, message = "While the foolish light was asleep, the darkness will awaken first. Uh huh huh...")
	public static NpcStringId WHILE_THE_FOOLISH_LIGHT_WAS_ASLEEP_THE_DARKNESS_WILL_AWAKEN_FIRST_UH_HUH_HUH;
	
	@ClientString(id = 1010224, message = "It is the deepest darkness. With its arrival, the world will soon die.")
	public static NpcStringId IT_IS_THE_DEEPEST_DARKNESS_WITH_ITS_ARRIVAL_THE_WORLD_WILL_SOON_DIE;
	
	@ClientString(id = 1010225, message = "Death is just a new beginning. Huhu... Fear not.")
	public static NpcStringId DEATH_IS_JUST_A_NEW_BEGINNING_HUHU_FEAR_NOT;
	
	@ClientString(id = 1010226, message = "Ahh! Beautiful goddess of death! Cover over the filth of this world with your darkness!")
	public static NpcStringId AHH_BEAUTIFUL_GODDESS_OF_DEATH_COVER_OVER_THE_FILTH_OF_THIS_WORLD_WITH_YOUR_DARKNESS;
	
	@ClientString(id = 1010227, message = "The goddess's resurrection has already begun. Huhu... Insignificant creatures like you can do nothing!")
	public static NpcStringId THE_GODDESS_S_RESURRECTION_HAS_ALREADY_BEGUN_HUHU_INSIGNIFICANT_CREATURES_LIKE_YOU_CAN_DO_NOTHING;
	
	@ClientString(id = 1010400, message = "Croak, Croak! Food like $s1 in this place?!")
	public static NpcStringId CROAK_CROAK_FOOD_LIKE_S1_IN_THIS_PLACE;
	
	@ClientString(id = 1010401, message = "$s1, How lucky I am!")
	public static NpcStringId S1_HOW_LUCKY_I_AM;
	
	@ClientString(id = 1010402, message = "Pray that you caught a wrong fish $s1!")
	public static NpcStringId PRAY_THAT_YOU_CAUGHT_A_WRONG_FISH_S1;
	
	@ClientString(id = 1010403, message = "Do you know what a frog tastes like?")
	public static NpcStringId DO_YOU_KNOW_WHAT_A_FROG_TASTES_LIKE;
	
	@ClientString(id = 1010404, message = "I will show you the power of a frog!")
	public static NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_A_FROG;
	
	@ClientString(id = 1010405, message = "I will swallow at a mouthful!")
	public static NpcStringId I_WILL_SWALLOW_AT_A_MOUTHFUL;
	
	@ClientString(id = 1010406, message = "Ugh, no chance. How could this elder pass away like this!")
	public static NpcStringId UGH_NO_CHANCE_HOW_COULD_THIS_ELDER_PASS_AWAY_LIKE_THIS;
	
	@ClientString(id = 1010407, message = "Croak! Croak! A frog is dying!")
	public static NpcStringId CROAK_CROAK_A_FROG_IS_DYING;
	
	@ClientString(id = 1010408, message = "A frog tastes bad! Yuck!")
	public static NpcStringId A_FROG_TASTES_BAD_YUCK;
	
	@ClientString(id = 1010409, message = "Kaak! $s1, What are you doing now?")
	public static NpcStringId KAAK_S1_WHAT_ARE_YOU_DOING_NOW;
	
	@ClientString(id = 1010410, message = "Huh, $s1 You pierced into the body of the Spirit with a needle. Are you ready?")
	public static NpcStringId HUH_S1_YOU_PIERCED_INTO_THE_BODY_OF_THE_SPIRIT_WITH_A_NEEDLE_ARE_YOU_READY;
	
	@ClientString(id = 1010411, message = "Ooh $s1 That's you. But, no lady is pleased with this savage invitation!")
	public static NpcStringId OOH_S1_THAT_S_YOU_BUT_NO_LADY_IS_PLEASED_WITH_THIS_SAVAGE_INVITATION;
	
	@ClientString(id = 1010412, message = "You made me angry!")
	public static NpcStringId YOU_MADE_ME_ANGRY;
	
	@ClientString(id = 1010413, message = "It is but a scratch! Is that all you can do?!")
	public static NpcStringId IT_IS_BUT_A_SCRATCH_IS_THAT_ALL_YOU_CAN_DO;
	
	@ClientString(id = 1010414, message = "Feel my pain!")
	public static NpcStringId FEEL_MY_PAIN;
	
	@ClientString(id = 1010415, message = "I'll get you for this!")
	public static NpcStringId I_LL_GET_YOU_FOR_THIS;
	
	@ClientString(id = 1010416, message = "I will tell fish not to take your bait!")
	public static NpcStringId I_WILL_TELL_FISH_NOT_TO_TAKE_YOUR_BAIT;
	
	@ClientString(id = 1010417, message = "You bothered such a weak spirit...Huh, Huh")
	public static NpcStringId YOU_BOTHERED_SUCH_A_WEAK_SPIRIT_HUH_HUH;
	
	@ClientString(id = 1010418, message = "Ke, ke, ke..$s1...I'm eating..Ke..")
	public static NpcStringId KE_KE_KE_S1_I_M_EATING_KE;
	
	@ClientString(id = 1010419, message = "Kuh..Ooh..$s1..Enmity...Fish....")
	public static NpcStringId KUH_OOH_S1_ENMITY_FISH;
	
	@ClientString(id = 1010420, message = "$s1...? Huh... Huh..huh...")
	public static NpcStringId S1_HUH_HUH_HUH;
	
	@ClientString(id = 1010421, message = "Ke, ke, ke, Rakul! Spin! Eh, eh, eh!")
	public static NpcStringId KE_KE_KE_RAKUL_SPIN_EH_EH_EH;
	
	@ClientString(id = 1010422, message = "Ah! Fafurion! Ah! Ah!")
	public static NpcStringId AH_FAFURION_AH_AH;
	
	@ClientString(id = 1010423, message = "Rakul! Rakul! Ra-kul! ")
	public static NpcStringId RAKUL_RAKUL_RA_KUL;
	
	@ClientString(id = 1010424, message = "Eh..Enmity...Fish...")
	public static NpcStringId EH_ENMITY_FISH;
	
	@ClientString(id = 1010425, message = "I won't be eaten up...Kah, ah, ah")
	public static NpcStringId I_WON_T_BE_EATEN_UP_KAH_AH_AH;
	
	@ClientString(id = 1010426, message = "Cough! Rakul! Cough, Cough! Keh...")
	public static NpcStringId COUGH_RAKUL_COUGH_COUGH_KEH;
	
	@ClientString(id = 1010427, message = "Glory to Fafurion! Death to $s1!")
	public static NpcStringId GLORY_TO_FAFURION_DEATH_TO_S1;
	
	@ClientString(id = 1010428, message = "$s1! You are the one who bothered my poor fish!")
	public static NpcStringId S1_YOU_ARE_THE_ONE_WHO_BOTHERED_MY_POOR_FISH;
	
	@ClientString(id = 1010429, message = "Fafurion! A curse upon $s1!")
	public static NpcStringId FAFURION_A_CURSE_UPON_S1;
	
	@ClientString(id = 1010430, message = "Giant Special Attack!")
	public static NpcStringId GIANT_SPECIAL_ATTACK;
	
	@ClientString(id = 1010431, message = "Know the enmity of fish!")
	public static NpcStringId KNOW_THE_ENMITY_OF_FISH;
	
	@ClientString(id = 1010432, message = "I will show you the power of a spear!")
	public static NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_A_SPEAR;
	
	@ClientString(id = 1010433, message = "Glory to Fafurion!")
	public static NpcStringId GLORY_TO_FAFURION;
	
	@ClientString(id = 1010434, message = "Yipes!")
	public static NpcStringId YIPES;
	
	@ClientString(id = 1010435, message = "An old soldier does not die..! but just disappear...!")
	public static NpcStringId AN_OLD_SOLDIER_DOES_NOT_DIE_BUT_JUST_DISAPPEAR;
	
	@ClientString(id = 1010436, message = "$s1, Take my challenge, the knight of water!")
	public static NpcStringId S1_TAKE_MY_CHALLENGE_THE_KNIGHT_OF_WATER;
	
	@ClientString(id = 1010437, message = "Discover $s1 in the treasure chest of fish!")
	public static NpcStringId DISCOVER_S1_IN_THE_TREASURE_CHEST_OF_FISH;
	
	@ClientString(id = 1010438, message = "$s1, I took your bait!")
	public static NpcStringId S1_I_TOOK_YOUR_BAIT;
	
	@ClientString(id = 1010439, message = "I will show you spearmanship used in Dragon King's Palace!")
	public static NpcStringId I_WILL_SHOW_YOU_SPEARMANSHIP_USED_IN_DRAGON_KING_S_PALACE;
	
	@ClientString(id = 1010440, message = "This is the last gift I give you!")
	public static NpcStringId THIS_IS_THE_LAST_GIFT_I_GIVE_YOU;
	
	@ClientString(id = 1010441, message = "Your bait was too delicious! Now, I will kill you!")
	public static NpcStringId YOUR_BAIT_WAS_TOO_DELICIOUS_NOW_I_WILL_KILL_YOU;
	
	@ClientString(id = 1010442, message = "What a regret! The enmity of my brethren!")
	public static NpcStringId WHAT_A_REGRET_THE_ENMITY_OF_MY_BRETHREN;
	
	@ClientString(id = 1010443, message = "I'll pay you back! Somebody will have my revenge!")
	public static NpcStringId I_LL_PAY_YOU_BACK_SOMEBODY_WILL_HAVE_MY_REVENGE;
	
	@ClientString(id = 1010444, message = "Cough... But, I won't be eaten up by you...!")
	public static NpcStringId COUGH_BUT_I_WON_T_BE_EATEN_UP_BY_YOU;
	
	@ClientString(id = 1010445, message = "....? $s1... I will kill you...")
	public static NpcStringId S1_I_WILL_KILL_YOU;
	
	@ClientString(id = 1010446, message = "$s1... How could you catch me from the deep sea...")
	public static NpcStringId S1_HOW_COULD_YOU_CATCH_ME_FROM_THE_DEEP_SEA;
	
	@ClientString(id = 1010447, message = "$s1... Do you think I am a fish?")
	public static NpcStringId S1_DO_YOU_THINK_I_AM_A_FISH;
	
	@ClientString(id = 1010448, message = "Ebibibi~")
	public static NpcStringId EBIBIBI;
	
	@ClientString(id = 1010449, message = "He, he, he. Do you want me to roast you well?")
	public static NpcStringId HE_HE_HE_DO_YOU_WANT_ME_TO_ROAST_YOU_WELL;
	
	@ClientString(id = 1010450, message = "You didn't keep your eyes on me because I come from the sea?")
	public static NpcStringId YOU_DIDN_T_KEEP_YOUR_EYES_ON_ME_BECAUSE_I_COME_FROM_THE_SEA;
	
	@ClientString(id = 1010451, message = "Eeek... I feel sick...yow...!")
	public static NpcStringId EEEK_I_FEEL_SICK_YOW;
	
	@ClientString(id = 1010452, message = "I have failed...")
	public static NpcStringId I_HAVE_FAILED;
	
	@ClientString(id = 1010453, message = "Activity of life... Is stopped... Chizifc...")
	public static NpcStringId ACTIVITY_OF_LIFE_IS_STOPPED_CHIZIFC;
	
	@ClientString(id = 1010454, message = "Growling! $s1~ Growling!")
	public static NpcStringId GROWLING_S1_GROWLING;
	
	@ClientString(id = 1010455, message = "I can smell $s1..!")
	public static NpcStringId I_CAN_SMELL_S1;
	
	@ClientString(id = 1010456, message = "Looks delicious, $s1!")
	public static NpcStringId LOOKS_DELICIOUS_S1;
	
	@ClientString(id = 1010457, message = "I will catch you!")
	public static NpcStringId I_WILL_CATCH_YOU;
	
	@ClientString(id = 1010458, message = "Uah, ah, ah, I couldn't eat anything for a long time!")
	public static NpcStringId UAH_AH_AH_I_COULDN_T_EAT_ANYTHING_FOR_A_LONG_TIME;
	
	@ClientString(id = 1010459, message = "I can swallow you at a mouthful!")
	public static NpcStringId I_CAN_SWALLOW_YOU_AT_A_MOUTHFUL;
	
	@ClientString(id = 1010460, message = "What?! I am defeated by the prey!")
	public static NpcStringId WHAT_I_AM_DEFEATED_BY_THE_PREY;
	
	@ClientString(id = 1010461, message = "You are my food! I have to kill you!")
	public static NpcStringId YOU_ARE_MY_FOOD_I_HAVE_TO_KILL_YOU;
	
	@ClientString(id = 1010462, message = "I can't believe I am eaten up by my prey... Gah!")
	public static NpcStringId I_CAN_T_BELIEVE_I_AM_EATEN_UP_BY_MY_PREY_GAH;
	
	@ClientString(id = 1010463, message = "....You caught me, $s1...?")
	public static NpcStringId YOU_CAUGHT_ME_S1;
	
	@ClientString(id = 1010464, message = "You're lucky to have even seen me, $s1.")
	public static NpcStringId YOU_RE_LUCKY_TO_HAVE_EVEN_SEEN_ME_S1;
	
	@ClientString(id = 1010465, message = "$s1, you can't leave here alive. Give up.")
	public static NpcStringId S1_YOU_CAN_T_LEAVE_HERE_ALIVE_GIVE_UP;
	
	@ClientString(id = 1010466, message = "I will show you the power of the deep sea!")
	public static NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_THE_DEEP_SEA;
	
	@ClientString(id = 1010467, message = "I will break the fishing pole!")
	public static NpcStringId I_WILL_BREAK_THE_FISHING_POLE;
	
	@ClientString(id = 1010468, message = "Your corpse will be good food for me!")
	public static NpcStringId YOUR_CORPSE_WILL_BE_GOOD_FOOD_FOR_ME;
	
	@ClientString(id = 1010469, message = "You are a good fisherman.")
	public static NpcStringId YOU_ARE_A_GOOD_FISHERMAN;
	
	@ClientString(id = 1010470, message = "Aren't you afraid of Fafurion?!")
	public static NpcStringId AREN_T_YOU_AFRAID_OF_FAFURION;
	
	@ClientString(id = 1010471, message = "You are excellent....")
	public static NpcStringId YOU_ARE_EXCELLENT;
	
	@ClientString(id = 1010472, message = "The Poison device has been activated.")
	public static NpcStringId THE_POISON_DEVICE_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 1010473, message = "The P. Atk. reduction device will be activated in 1 minute.")
	public static NpcStringId THE_P_ATK_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_1_MINUTE;
	
	@ClientString(id = 1010474, message = "The Defense reduction device will be activated in 2 minutes.")
	public static NpcStringId THE_DEFENSE_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_2_MINUTES;
	
	@ClientString(id = 1010475, message = "The HP regeneration reduction device will be activated in 3 minutes.")
	public static NpcStringId THE_HP_REGENERATION_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_3_MINUTES;
	
	@ClientString(id = 1010476, message = "The P. Atk. reduction device has been activated.")
	public static NpcStringId THE_P_ATK_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 1010477, message = "The Defense reduction device has been activated.")
	public static NpcStringId THE_DEFENSE_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 1010478, message = "The HP regeneration reduction device has been activated.")
	public static NpcStringId THE_HP_REGENERATION_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 1010479, message = "The poison device has now been destroyed.")
	public static NpcStringId THE_POISON_DEVICE_HAS_NOW_BEEN_DESTROYED;
	
	@ClientString(id = 1010480, message = "The P. Atk. reduction device has now been destroyed.")
	public static NpcStringId THE_P_ATK_REDUCTION_DEVICE_HAS_NOW_BEEN_DESTROYED;
	
	@ClientString(id = 1010481, message = "The Defense reduction device has been destroyed.")
	public static NpcStringId THE_DEFENSE_REDUCTION_DEVICE_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 1010482, message = "The HP regeneration reduction device will be activated in 3 minutes.")
	public static NpcStringId THE_HP_REGENERATION_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_3_MINUTES_2;
	
	@ClientString(id = 1010483, message = "$s1! Help me!!")
	public static NpcStringId S1_HELP_ME_2;
	
	@ClientString(id = 1010484, message = "Help me!!")
	public static NpcStringId HELP_ME_2;
	
	@ClientString(id = 1010485, message = "Entrance to the Cave of Trials")
	public static NpcStringId ENTRANCE_TO_THE_CAVE_OF_TRIALS;
	
	@ClientString(id = 1010486, message = "Inside the Elven Ruins")
	public static NpcStringId INSIDE_THE_ELVEN_RUINS;
	
	@ClientString(id = 1010487, message = "Entrance to the Elven Ruins")
	public static NpcStringId ENTRANCE_TO_THE_ELVEN_RUINS;
	
	@ClientString(id = 1010488, message = "Entrance to the School of Dark Arts")
	public static NpcStringId ENTRANCE_TO_THE_SCHOOL_OF_DARK_ARTS;
	
	@ClientString(id = 1010489, message = "Center of the School of Dark Arts")
	public static NpcStringId CENTER_OF_THE_SCHOOL_OF_DARK_ARTS;
	
	@ClientString(id = 1010490, message = "Entrance to the Elven Fortress")
	public static NpcStringId ENTRANCE_TO_THE_ELVEN_FORTRESS;
	
	@ClientString(id = 1010491, message = "Varka Silenos Stronghold")
	public static NpcStringId VARKA_SILENOS_STRONGHOLD;
	
	@ClientString(id = 1010492, message = "Ketra Orc Outpost")
	public static NpcStringId KETRA_ORC_OUTPOST;
	
	@ClientString(id = 1010493, message = "Rune Township Guild")
	public static NpcStringId RUNE_TOWNSHIP_GUILD;
	
	@ClientString(id = 1010494, message = "Rune Township Temple")
	public static NpcStringId RUNE_TOWNSHIP_TEMPLE;
	
	@ClientString(id = 1010495, message = "Rune Township Store")
	public static NpcStringId RUNE_TOWNSHIP_STORE;
	
	@ClientString(id = 1010496, message = "Entrance to the Forest of the Dead")
	public static NpcStringId ENTRANCE_TO_THE_FOREST_OF_THE_DEAD;
	
	@ClientString(id = 1010497, message = "Western Entrance to the Swamp of Screams")
	public static NpcStringId WESTERN_ENTRANCE_TO_THE_SWAMP_OF_SCREAMS;
	
	@ClientString(id = 1010498, message = "Entrance to the Forgotten Temple")
	public static NpcStringId ENTRANCE_TO_THE_FORGOTTEN_TEMPLE;
	
	@ClientString(id = 1010499, message = "Center of the Forgotten Temple")
	public static NpcStringId CENTER_OF_THE_FORGOTTEN_TEMPLE;
	
	@ClientString(id = 1010500, message = "Entrance to the Cruma Tower")
	public static NpcStringId ENTRANCE_TO_THE_CRUMA_TOWER;
	
	@ClientString(id = 1010501, message = "Cruma Tower - First Floor")
	public static NpcStringId CRUMA_TOWER_FIRST_FLOOR;
	
	@ClientString(id = 1010502, message = "Cruma Tower - Second Floor")
	public static NpcStringId CRUMA_TOWER_SECOND_FLOOR;
	
	@ClientString(id = 1010503, message = "Cruma Tower - Third Floor")
	public static NpcStringId CRUMA_TOWER_THIRD_FLOOR;
	
	@ClientString(id = 1010504, message = "Entrance to Devil's Isle")
	public static NpcStringId ENTRANCE_TO_DEVIL_S_ISLE;
	
	@ClientString(id = 1010505, message = "Sel Mahum Training Grounds (West Gate)")
	public static NpcStringId SEL_MAHUM_TRAINING_GROUNDS_WEST_GATE;
	
	@ClientString(id = 1010506, message = "Gludin Arena")
	public static NpcStringId GLUDIN_ARENA;
	
	@ClientString(id = 1010507, message = "Giran Arena")
	public static NpcStringId GIRAN_ARENA;
	
	@ClientString(id = 1010508, message = "Entrance to Antharas's Lair ")
	public static NpcStringId ENTRANCE_TO_ANTHARAS_S_LAIR;
	
	@ClientString(id = 1010509, message = "Antharas's Lair - 1st Level")
	public static NpcStringId ANTHARAS_S_LAIR_1ST_LEVEL;
	
	@ClientString(id = 1010510, message = "Antharas's Lair - 2nd Level")
	public static NpcStringId ANTHARAS_S_LAIR_2ND_LEVEL;
	
	@ClientString(id = 1010511, message = "Antharas's Lair - Magic Force Field Bridge")
	public static NpcStringId ANTHARAS_S_LAIR_MAGIC_FORCE_FIELD_BRIDGE;
	
	@ClientString(id = 1010512, message = "The Heart of Antharas's Lair")
	public static NpcStringId THE_HEART_OF_ANTHARAS_S_LAIR;
	
	@ClientString(id = 1010513, message = "East of the Field of Silence")
	public static NpcStringId EAST_OF_THE_FIELD_OF_SILENCE;
	
	@ClientString(id = 1010514, message = "West of the Field of Silence")
	public static NpcStringId WEST_OF_THE_FIELD_OF_SILENCE;
	
	@ClientString(id = 1010515, message = "East of the Field of Whispers")
	public static NpcStringId EAST_OF_THE_FIELD_OF_WHISPERS;
	
	@ClientString(id = 1010516, message = "West of the Field of Whispers")
	public static NpcStringId WEST_OF_THE_FIELD_OF_WHISPERS;
	
	@ClientString(id = 1010517, message = "Entrance to the Garden of Eva")
	public static NpcStringId ENTRANCE_TO_THE_GARDEN_OF_EVA;
	
	@ClientString(id = 1010518, message = "Entrance to Alligator Island")
	public static NpcStringId ENTRANCE_TO_ALLIGATOR_ISLAND_2;
	
	@ClientString(id = 1010519, message = "Alligator Beach")
	public static NpcStringId ALLIGATOR_BEACH_2;
	
	@ClientString(id = 1010520, message = "Northern part of Alligator Island")
	public static NpcStringId NORTHERN_PART_OF_ALLIGATOR_ISLAND;
	
	@ClientString(id = 1010521, message = "Central part of Alligator Island")
	public static NpcStringId CENTRAL_PART_OF_ALLIGATOR_ISLAND;
	
	@ClientString(id = 1010522, message = "Garden of Eva - 2nd Level")
	public static NpcStringId GARDEN_OF_EVA_2ND_LEVEL;
	
	@ClientString(id = 1010523, message = "Garden of Eva - 3rd Level")
	public static NpcStringId GARDEN_OF_EVA_3RD_LEVEL;
	
	@ClientString(id = 1010524, message = "Garden of Eva - 4th Level")
	public static NpcStringId GARDEN_OF_EVA_4TH_LEVEL;
	
	@ClientString(id = 1010525, message = "Garden of Eva - 5th Level")
	public static NpcStringId GARDEN_OF_EVA_5TH_LEVEL;
	
	@ClientString(id = 1010526, message = "Inside the Garden of Eva")
	public static NpcStringId INSIDE_THE_GARDEN_OF_EVA;
	
	@ClientString(id = 1010527, message = "Four Sepulchers")
	public static NpcStringId FOUR_SEPULCHERS;
	
	@ClientString(id = 1010528, message = "Imperial Tomb")
	public static NpcStringId IMPERIAL_TOMB;
	
	@ClientString(id = 1010529, message = "Shrine of Loyalty")
	public static NpcStringId SHRINE_OF_LOYALTY;
	
	@ClientString(id = 1010530, message = "Entrance to the Forge of the Gods")
	public static NpcStringId ENTRANCE_TO_THE_FORGE_OF_THE_GODS;
	
	@ClientString(id = 1010531, message = "Forge of the Gods - Top Level")
	public static NpcStringId FORGE_OF_THE_GODS_TOP_LEVEL;
	
	@ClientString(id = 1010532, message = "Forge of the Gods - Lower Level")
	public static NpcStringId FORGE_OF_THE_GODS_LOWER_LEVEL;
	
	@ClientString(id = 1010533, message = "Entrance to the Wall of Argos")
	public static NpcStringId ENTRANCE_TO_THE_WALL_OF_ARGOS;
	
	@ClientString(id = 1010534, message = "Varka Silenos Village")
	public static NpcStringId VARKA_SILENOS_VILLAGE;
	
	@ClientString(id = 1010535, message = "Ketra Orc Village")
	public static NpcStringId KETRA_ORC_VILLAGE;
	
	@ClientString(id = 1010536, message = "Entrance to the Hot Springs Region")
	public static NpcStringId ENTRANCE_TO_THE_HOT_SPRINGS_REGION;
	
	@ClientString(id = 1010537, message = "Wild Beast Pastures")
	public static NpcStringId WILD_BEAST_PASTURES;
	
	@ClientString(id = 1010538, message = "Entrance to the Valley of Saints")
	public static NpcStringId ENTRANCE_TO_THE_VALLEY_OF_SAINTS;
	
	@ClientString(id = 1010539, message = "Cursed Village")
	public static NpcStringId CURSED_VILLAGE;
	
	@ClientString(id = 1010540, message = "Southern Entrance of the Wild Beast Pastures")
	public static NpcStringId SOUTHERN_ENTRANCE_OF_THE_WILD_BEAST_PASTURES;
	
	@ClientString(id = 1010541, message = "Eastern Part of the Wild Beast Pastures")
	public static NpcStringId EASTERN_PART_OF_THE_WILD_BEAST_PASTURES;
	
	@ClientString(id = 1010542, message = "Western Part of the Wild Beast Pastures")
	public static NpcStringId WESTERN_PART_OF_THE_WILD_BEAST_PASTURES;
	
	@ClientString(id = 1010543, message = "Eastern Part of the Swamp of Screams")
	public static NpcStringId EASTERN_PART_OF_THE_SWAMP_OF_SCREAMS;
	
	@ClientString(id = 1010544, message = "Western Part of the Swamp of Screams")
	public static NpcStringId WESTERN_PART_OF_THE_SWAMP_OF_SCREAMS;
	
	@ClientString(id = 1010545, message = "Center of the Swamp of Screams")
	public static NpcStringId CENTER_OF_THE_SWAMP_OF_SCREAMS;
	
	@ClientString(id = 1010546, message = "Entrance to the Valley of Saints")
	public static NpcStringId ENTRANCE_TO_THE_VALLEY_OF_SAINTS_2;
	
	@ClientString(id = 1010547, message = "Aden Frontier Gateway")
	public static NpcStringId ADEN_FRONTIER_GATEWAY;
	
	@ClientString(id = 1010548, message = "Oren Frontier Gateway")
	public static NpcStringId OREN_FRONTIER_GATEWAY;
	
	@ClientString(id = 1010549, message = "Garden of Wild Beasts")
	public static NpcStringId GARDEN_OF_WILD_BEASTS;
	
	@ClientString(id = 1010550, message = "Devil's Pass")
	public static NpcStringId DEVIL_S_PASS;
	
	@ClientString(id = 1010551, message = "The bullets are being loaded.")
	public static NpcStringId THE_BULLETS_ARE_BEING_LOADED;
	
	@ClientString(id = 1010552, message = "You can start at the scheduled time.")
	public static NpcStringId YOU_CAN_START_AT_THE_SCHEDULED_TIME;
	
	@ClientString(id = 1010553, message = "Entrance to The Giant's Cave")
	public static NpcStringId ENTRANCE_TO_THE_GIANT_S_CAVE_2;
	
	@ClientString(id = 1010554, message = "Upper Level of The Giant's Cave")
	public static NpcStringId UPPER_LEVEL_OF_THE_GIANT_S_CAVE;
	
	@ClientString(id = 1010555, message = "Lower Level of The Giant's Cave")
	public static NpcStringId LOWER_LEVEL_OF_THE_GIANT_S_CAVE;
	
	@ClientString(id = 1010556, message = "Immortal Plateau, Northern Region")
	public static NpcStringId IMMORTAL_PLATEAU_NORTHERN_REGION;
	
	@ClientString(id = 1010557, message = "Elven Ruins")
	public static NpcStringId ELVEN_RUINS;
	
	@ClientString(id = 1010558, message = "Singing Waterfall")
	public static NpcStringId SINGING_WATERFALL;
	
	@ClientString(id = 1010559, message = "Talking Island, Northern Territory")
	public static NpcStringId TALKING_ISLAND_NORTHERN_TERRITORY;
	
	@ClientString(id = 1010560, message = "Elven Fortress")
	public static NpcStringId ELVEN_FORTRESS;
	
	@ClientString(id = 1010561, message = "Pilgrim's Temple")
	public static NpcStringId PILGRIM_S_TEMPLE;
	
	@ClientString(id = 1010562, message = "Gludin Harbor")
	public static NpcStringId GLUDIN_HARBOR;
	
	@ClientString(id = 1010563, message = "Shilen's Garden")
	public static NpcStringId SHILEN_S_GARDEN;
	
	@ClientString(id = 1010564, message = "School of Dark Arts")
	public static NpcStringId SCHOOL_OF_DARK_ARTS;
	
	@ClientString(id = 1010565, message = "Swamp of Screams")
	public static NpcStringId SWAMP_OF_SCREAMS;
	
	@ClientString(id = 1010566, message = "The Ant Nest")
	public static NpcStringId THE_ANT_NEST;
	
	@ClientString(id = 1010567, message = "Devil's Isle")
	public static NpcStringId DEVIL_S_ISLE_2;
	
	@ClientString(id = 1010568, message = "Wall of Argos")
	public static NpcStringId WALL_OF_ARGOS;
	
	@ClientString(id = 1010569, message = "Den of Evil")
	public static NpcStringId DEN_OF_EVIL;
	
	@ClientString(id = 1010570, message = "Iceman's Hut")
	public static NpcStringId ICEMAN_S_HUT;
	
	@ClientString(id = 1010571, message = "Crypts of Disgrace")
	public static NpcStringId CRYPTS_OF_DISGRACE;
	
	@ClientString(id = 1010572, message = "Plunderous Plains")
	public static NpcStringId PLUNDEROUS_PLAINS;
	
	@ClientString(id = 1010573, message = "Pavel Ruins")
	public static NpcStringId PAVEL_RUINS;
	
	@ClientString(id = 1010574, message = "Town of Schuttgart")
	public static NpcStringId TOWN_OF_SCHUTTGART;
	
	@ClientString(id = 1010575, message = "Monastery of Silence")
	public static NpcStringId MONASTERY_OF_SILENCE;
	
	@ClientString(id = 1010576, message = "Monastery of Silence: Rear Gate")
	public static NpcStringId MONASTERY_OF_SILENCE_REAR_GATE;
	
	@ClientString(id = 1010577, message = "Stakato Nest")
	public static NpcStringId STAKATO_NEST;
	
	@ClientString(id = 1010578, message = "How dare you trespass into my territory! Have you no fear?")
	public static NpcStringId HOW_DARE_YOU_TRESPASS_INTO_MY_TERRITORY_HAVE_YOU_NO_FEAR;
	
	@ClientString(id = 1010579, message = "Fools! Why haven't you fled yet? Prepare to learn a lesson!")
	public static NpcStringId FOOLS_WHY_HAVEN_T_YOU_FLED_YET_PREPARE_TO_LEARN_A_LESSON;
	
	@ClientString(id = 1010580, message = "Bwah-ha-ha! Your doom is at hand! Behold the Ultra Secret Super Weapon!")
	public static NpcStringId BWAH_HA_HA_YOUR_DOOM_IS_AT_HAND_BEHOLD_THE_ULTRA_SECRET_SUPER_WEAPON;
	
	@ClientString(id = 1010581, message = "Foolish, insignificant creatures! How dare you challenge me! ")
	public static NpcStringId FOOLISH_INSIGNIFICANT_CREATURES_HOW_DARE_YOU_CHALLENGE_ME;
	
	@ClientString(id = 1010582, message = "I see that none will challenge me now!")
	public static NpcStringId I_SEE_THAT_NONE_WILL_CHALLENGE_ME_NOW;
	
	@ClientString(id = 1010583, message = "Urggh! You will pay dearly for this insult.")
	public static NpcStringId URGGH_YOU_WILL_PAY_DEARLY_FOR_THIS_INSULT;
	
	@ClientString(id = 1010584, message = "What? You haven't even two pennies to rub together? Harumph!")
	public static NpcStringId WHAT_YOU_HAVEN_T_EVEN_TWO_PENNIES_TO_RUB_TOGETHER_HARUMPH;
	
	@ClientString(id = 1010585, message = "Forest of Mirrors")
	public static NpcStringId FOREST_OF_MIRRORS;
	
	@ClientString(id = 1010586, message = "The Center of the Forest of Mirrors")
	public static NpcStringId THE_CENTER_OF_THE_FOREST_OF_MIRRORS;
	
	@ClientString(id = 1010587, message = "Field of Silence (Western Section)")
	public static NpcStringId FIELD_OF_SILENCE_WESTERN_SECTION;
	
	@ClientString(id = 1010588, message = "Sky Wagon Relic")
	public static NpcStringId SKY_WAGON_RELIC;
	
	@ClientString(id = 1010589, message = "Dark Forest")
	public static NpcStringId DARK_FOREST_2;
	
	@ClientString(id = 1010590, message = "The Center of the Dark Forest")
	public static NpcStringId THE_CENTER_OF_THE_DARK_FOREST;
	
	@ClientString(id = 1010591, message = "Grave Robber Hideout")
	public static NpcStringId GRAVE_ROBBER_HIDEOUT;
	
	@ClientString(id = 1010592, message = "Forest of the Dead")
	public static NpcStringId FOREST_OF_THE_DEAD;
	
	@ClientString(id = 1010593, message = "The Center of the Forest of the Dead")
	public static NpcStringId THE_CENTER_OF_THE_FOREST_OF_THE_DEAD;
	
	@ClientString(id = 1010594, message = "Mithril Mines")
	public static NpcStringId MITHRIL_MINES;
	
	@ClientString(id = 1010595, message = "The Center of the Mithril Mines")
	public static NpcStringId THE_CENTER_OF_THE_MITHRIL_MINES;
	
	@ClientString(id = 1010596, message = "Abandoned Coal Mines")
	public static NpcStringId ABANDONED_COAL_MINES;
	
	@ClientString(id = 1010597, message = "The Center of the Abandoned Coal Mines")
	public static NpcStringId THE_CENTER_OF_THE_ABANDONED_COAL_MINES;
	
	@ClientString(id = 1010598, message = "Immortal Plateau, Western Region")
	public static NpcStringId IMMORTAL_PLATEAU_WESTERN_REGION;
	
	@ClientString(id = 1010599, message = "Bee Hive")
	public static NpcStringId BEE_HIVE_2;
	
	@ClientString(id = 1010600, message = "Valley of Saints")
	public static NpcStringId VALLEY_OF_SAINTS;
	
	@ClientString(id = 1010601, message = "The Center of the Valley of Saints")
	public static NpcStringId THE_CENTER_OF_THE_VALLEY_OF_SAINTS;
	
	@ClientString(id = 1010602, message = "Field of Whispers (Eastern Section)")
	public static NpcStringId FIELD_OF_WHISPERS_EASTERN_SECTION;
	
	@ClientString(id = 1010603, message = "Cave of Trials")
	public static NpcStringId CAVE_OF_TRIALS;
	
	@ClientString(id = 1010604, message = "Seal of Shilen")
	public static NpcStringId SEAL_OF_SHILEN;
	
	@ClientString(id = 1010605, message = "The Center of the Wall of Argos")
	public static NpcStringId THE_CENTER_OF_THE_WALL_OF_ARGOS;
	
	@ClientString(id = 1010606, message = "The Center of Alligator Island")
	public static NpcStringId THE_CENTER_OF_ALLIGATOR_ISLAND;
	
	@ClientString(id = 1010607, message = "Anghel Waterfall")
	public static NpcStringId ANGHEL_WATERFALL;
	
	@ClientString(id = 1010608, message = "Center of the Elven Ruins")
	public static NpcStringId CENTER_OF_THE_ELVEN_RUINS;
	
	@ClientString(id = 1010609, message = "Hot Springs")
	public static NpcStringId HOT_SPRINGS;
	
	@ClientString(id = 1010610, message = "The Center of the Hot Springs")
	public static NpcStringId THE_CENTER_OF_THE_HOT_SPRINGS;
	
	@ClientString(id = 1010611, message = "The Center of Dragon Valley")
	public static NpcStringId THE_CENTER_OF_DRAGON_VALLEY;
	
	@ClientString(id = 1010612, message = "Neutral Zone")
	public static NpcStringId NEUTRAL_ZONE_2;
	
	@ClientString(id = 1010613, message = "The Center of the Neutral Zone")
	public static NpcStringId THE_CENTER_OF_THE_NEUTRAL_ZONE;
	
	@ClientString(id = 1010614, message = "Cruma Marshlands")
	public static NpcStringId CRUMA_MARSHLANDS;
	
	@ClientString(id = 1010615, message = "The Center of the Cruma Marshlands")
	public static NpcStringId THE_CENTER_OF_THE_CRUMA_MARSHLANDS;
	
	@ClientString(id = 1010616, message = "Timak Outpost")
	public static NpcStringId TIMAK_OUTPOST_2;
	
	@ClientString(id = 1010617, message = "The Center of the Enchanted Valley")
	public static NpcStringId THE_CENTER_OF_THE_ENCHANTED_VALLEY;
	
	@ClientString(id = 1010618, message = "Enchanted Valley, Southern Region")
	public static NpcStringId ENCHANTED_VALLEY_SOUTHERN_REGION;
	
	@ClientString(id = 1010619, message = "Enchanted Valley, Northern Region")
	public static NpcStringId ENCHANTED_VALLEY_NORTHERN_REGION;
	
	@ClientString(id = 1010620, message = "Frost Lake")
	public static NpcStringId FROST_LAKE;
	
	@ClientString(id = 1010621, message = "Wastelands")
	public static NpcStringId WASTELANDS;
	
	@ClientString(id = 1010622, message = "Wastelands, Western Region")
	public static NpcStringId WASTELANDS_WESTERN_REGION;
	
	@ClientString(id = 1010623, message = "Who dares to covet the throne of our castle! Leave immediately or you will pay the price of your audacity with your very own blood!")
	public static NpcStringId WHO_DARES_TO_COVET_THE_THRONE_OF_OUR_CASTLE_LEAVE_IMMEDIATELY_OR_YOU_WILL_PAY_THE_PRICE_OF_YOUR_AUDACITY_WITH_YOUR_VERY_OWN_BLOOD;
	
	@ClientString(id = 1010624, message = "Hmm, those who are not of the bloodline are coming this way to take over the castle?! Humph! The bitter grudges of the dead. You must not make light of their power!")
	public static NpcStringId HMM_THOSE_WHO_ARE_NOT_OF_THE_BLOODLINE_ARE_COMING_THIS_WAY_TO_TAKE_OVER_THE_CASTLE_HUMPH_THE_BITTER_GRUDGES_OF_THE_DEAD_YOU_MUST_NOT_MAKE_LIGHT_OF_THEIR_POWER;
	
	@ClientString(id = 1010625, message = "Aargh...! If I die, then the magic force field of blood will...!")
	public static NpcStringId AARGH_IF_I_DIE_THEN_THE_MAGIC_FORCE_FIELD_OF_BLOOD_WILL;
	
	@ClientString(id = 1010626, message = "It's not over yet... It won't be... over... like this... Never...")
	public static NpcStringId IT_S_NOT_OVER_YET_IT_WON_T_BE_OVER_LIKE_THIS_NEVER;
	
	@ClientString(id = 1010627, message = "Oooh! Who poured nectar on my head while I was sleeping?")
	public static NpcStringId OOOH_WHO_POURED_NECTAR_ON_MY_HEAD_WHILE_I_WAS_SLEEPING;
	
	@ClientString(id = 1010628, message = "Please wait a moment.")
	public static NpcStringId PLEASE_WAIT_A_MOMENT;
	
	@ClientString(id = 1010629, message = "The word you need this time is $s1.")
	public static NpcStringId THE_WORD_YOU_NEED_THIS_TIME_IS_S1;
	
	@ClientString(id = 1010630, message = "Intruders! Sound the alarm!")
	public static NpcStringId INTRUDERS_SOUND_THE_ALARM;
	
	@ClientString(id = 1010631, message = "De-activate the alarm.")
	public static NpcStringId DE_ACTIVATE_THE_ALARM;
	
	@ClientString(id = 1010632, message = "Oh no! The defenses have failed. It is too dangerous to remain inside the castle. Flee! Every man for himself!")
	public static NpcStringId OH_NO_THE_DEFENSES_HAVE_FAILED_IT_IS_TOO_DANGEROUS_TO_REMAIN_INSIDE_THE_CASTLE_FLEE_EVERY_MAN_FOR_HIMSELF;
	
	@ClientString(id = 1010633, message = "The game has begun. Participants, prepare to learn an important word.")
	public static NpcStringId THE_GAME_HAS_BEGUN_PARTICIPANTS_PREPARE_TO_LEARN_AN_IMPORTANT_WORD;
	
	@ClientString(id = 1010634, message = "$s1 team's jackpot has $s2 percent of its HP remaining.")
	public static NpcStringId S1_TEAM_S_JACKPOT_HAS_S2_PERCENT_OF_ITS_HP_REMAINING;
	
	@ClientString(id = 1010635, message = "Undecided")
	public static NpcStringId UNDECIDED;
	
	@ClientString(id = 1010636, message = "Heh Heh... I see that the feast has begun! Be wary! The curse of the Hellmann family has poisoned this land!")
	public static NpcStringId HEH_HEH_I_SEE_THAT_THE_FEAST_HAS_BEGUN_BE_WARY_THE_CURSE_OF_THE_HELLMANN_FAMILY_HAS_POISONED_THIS_LAND;
	
	@ClientString(id = 1010637, message = "Arise, my faithful servants! You, my people who have inherited the blood. It is the calling of my daughter. The feast of blood will now begin!")
	public static NpcStringId ARISE_MY_FAITHFUL_SERVANTS_YOU_MY_PEOPLE_WHO_HAVE_INHERITED_THE_BLOOD_IT_IS_THE_CALLING_OF_MY_DAUGHTER_THE_FEAST_OF_BLOOD_WILL_NOW_BEGIN;
	
	@ClientString(id = 1010638, message = "I'll let you have the throne of the Lord of the Forest for now...! Get drunk with your victory...! And while you wait with fear and trembling under the pressure of your ill-gotten position, just you wait for the return of the true Lord...! Bwa ha ha ha ha...!")
	public static NpcStringId I_LL_LET_YOU_HAVE_THE_THRONE_OF_THE_LORD_OF_THE_FOREST_FOR_NOW_GET_DRUNK_WITH_YOUR_VICTORY_AND_WHILE_YOU_WAIT_WITH_FEAR_AND_TREMBLING_UNDER_THE_PRESSURE_OF_YOUR_ILL_GOTTEN_POSITION_JUST_YOU_WAIT_FOR_THE_RETURN_OF_THE_TRUE_LORD_BWA_HA_HA_HA_HA;
	
	@ClientString(id = 1010639, message = "Grarr! For the next 2 minutes or so, the game arena are will be cleaned. Throw any items you don't need to the floor now.")
	public static NpcStringId GRARR_FOR_THE_NEXT_2_MINUTES_OR_SO_THE_GAME_ARENA_ARE_WILL_BE_CLEANED_THROW_ANY_ITEMS_YOU_DON_T_NEED_TO_THE_FLOOR_NOW;
	
	@ClientString(id = 1010640, message = "Grarr! $s1 team is using the hot springs sulfur on the opponent's camp.")
	public static NpcStringId GRARR_S1_TEAM_IS_USING_THE_HOT_SPRINGS_SULFUR_ON_THE_OPPONENT_S_CAMP;
	
	@ClientString(id = 1010641, message = "Grarr! $s1 team is attempting to steal the jackpot.")
	public static NpcStringId GRARR_S1_TEAM_IS_ATTEMPTING_TO_STEAL_THE_JACKPOT;
	
	@ClientString(id = 1010642, message = "** Vacant Seat **")
	public static NpcStringId VACANT_SEAT;
	
	@ClientString(id = 1010643, message = "$s1 minute(s) are remaining.")
	public static NpcStringId S1_MINUTE_S_ARE_REMAINING;
	
	@ClientString(id = 1010644, message = "How dare you ruin the performance of the Dark Choir... Unforgivable!")
	public static NpcStringId HOW_DARE_YOU_RUIN_THE_PERFORMANCE_OF_THE_DARK_CHOIR_UNFORGIVABLE;
	
	@ClientString(id = 1010645, message = "Get rid of the invaders who interrupt the performance of the Dark Choir!")
	public static NpcStringId GET_RID_OF_THE_INVADERS_WHO_INTERRUPT_THE_PERFORMANCE_OF_THE_DARK_CHOIR;
	
	@ClientString(id = 1010646, message = "Don't you hear the music of death? Reveal the horror of the Dark Choir!")
	public static NpcStringId DON_T_YOU_HEAR_THE_MUSIC_OF_DEATH_REVEAL_THE_HORROR_OF_THE_DARK_CHOIR;
	
	@ClientString(id = 1010647, message = "The Immortal Plateau")
	public static NpcStringId THE_IMMORTAL_PLATEAU;
	
	@ClientString(id = 1010648, message = "Kamael Village")
	public static NpcStringId KAMAEL_VILLAGE;
	
	@ClientString(id = 1010649, message = "Isle of Souls Base")
	public static NpcStringId ISLE_OF_SOULS_BASE;
	
	@ClientString(id = 1010650, message = "Golden Hills Base")
	public static NpcStringId GOLDEN_HILLS_BASE;
	
	@ClientString(id = 1010651, message = "Mimir's Forest Base")
	public static NpcStringId MIMIR_S_FOREST_BASE;
	
	@ClientString(id = 1010652, message = "Isle of Souls Harbor")
	public static NpcStringId ISLE_OF_SOULS_HARBOR;
	
	@ClientString(id = 1010653, message = "Stronghold I")
	public static NpcStringId STRONGHOLD_I;
	
	@ClientString(id = 1010654, message = "Stronghold II")
	public static NpcStringId STRONGHOLD_II;
	
	@ClientString(id = 1010655, message = "Stronghold III")
	public static NpcStringId STRONGHOLD_III;
	
	@ClientString(id = 1010656, message = "Fortress West Gate")
	public static NpcStringId FORTRESS_WEST_GATE;
	
	@ClientString(id = 1010657, message = "Fortress East Gate")
	public static NpcStringId FORTRESS_EAST_GATE;
	
	@ClientString(id = 1010658, message = "Fortress North Gate")
	public static NpcStringId FORTRESS_NORTH_GATE;
	
	@ClientString(id = 1010659, message = "Fortress South Gate")
	public static NpcStringId FORTRESS_SOUTH_GATE;
	
	@ClientString(id = 1010660, message = "Front of the Valley Fortress")
	public static NpcStringId FRONT_OF_THE_VALLEY_FORTRESS;
	
	@ClientString(id = 1010661, message = "Goddard Town Square")
	public static NpcStringId GODDARD_TOWN_SQUARE;
	
	@ClientString(id = 1010662, message = "Front of the Goddard Castle Gate")
	public static NpcStringId FRONT_OF_THE_GODDARD_CASTLE_GATE;
	
	@ClientString(id = 1010663, message = "Gludio Town Square")
	public static NpcStringId GLUDIO_TOWN_SQUARE;
	
	@ClientString(id = 1010664, message = "Front of the Gludio Castle Gate")
	public static NpcStringId FRONT_OF_THE_GLUDIO_CASTLE_GATE;
	
	@ClientString(id = 1010665, message = "Giran Town Square")
	public static NpcStringId GIRAN_TOWN_SQUARE;
	
	@ClientString(id = 1010666, message = "Front of the Giran Castle Gate")
	public static NpcStringId FRONT_OF_THE_GIRAN_CASTLE_GATE;
	
	@ClientString(id = 1010667, message = "Front of the Southern Fortress")
	public static NpcStringId FRONT_OF_THE_SOUTHERN_FORTRESS;
	
	@ClientString(id = 1010668, message = "Front of the Swamp Fortress")
	public static NpcStringId FRONT_OF_THE_SWAMP_FORTRESS;
	
	@ClientString(id = 1010669, message = "Dion Town Square")
	public static NpcStringId DION_TOWN_SQUARE;
	
	@ClientString(id = 1010670, message = "Front of the Dion Castle Gate")
	public static NpcStringId FRONT_OF_THE_DION_CASTLE_GATE;
	
	@ClientString(id = 1010671, message = "Rune Town Square")
	public static NpcStringId RUNE_TOWN_SQUARE;
	
	@ClientString(id = 1010672, message = "Front of the Rune Castle Gate")
	public static NpcStringId FRONT_OF_THE_RUNE_CASTLE_GATE;
	
	@ClientString(id = 1010673, message = "Front of the White Sand Fortress")
	public static NpcStringId FRONT_OF_THE_WHITE_SAND_FORTRESS;
	
	@ClientString(id = 1010674, message = "Front of the Basin Fortress")
	public static NpcStringId FRONT_OF_THE_BASIN_FORTRESS;
	
	@ClientString(id = 1010675, message = "Front of the Ivory Fortress")
	public static NpcStringId FRONT_OF_THE_IVORY_FORTRESS;
	
	@ClientString(id = 1010676, message = "Schuttgart Town Square")
	public static NpcStringId SCHUTTGART_TOWN_SQUARE;
	
	@ClientString(id = 1010677, message = "Front of the Schuttgart Castle Gate")
	public static NpcStringId FRONT_OF_THE_SCHUTTGART_CASTLE_GATE;
	
	@ClientString(id = 1010678, message = "Aden Town Square")
	public static NpcStringId ADEN_TOWN_SQUARE;
	
	@ClientString(id = 1010679, message = "Front of the Aden Castle Gate")
	public static NpcStringId FRONT_OF_THE_ADEN_CASTLE_GATE;
	
	@ClientString(id = 1010680, message = "Front of the Shanty Fortress")
	public static NpcStringId FRONT_OF_THE_SHANTY_FORTRESS;
	
	@ClientString(id = 1010681, message = "Oren Town Square")
	public static NpcStringId OREN_TOWN_SQUARE;
	
	@ClientString(id = 1010682, message = "Front of the Oren Castle Gate")
	public static NpcStringId FRONT_OF_THE_OREN_CASTLE_GATE;
	
	@ClientString(id = 1010683, message = "Front of the Archaic Fortress")
	public static NpcStringId FRONT_OF_THE_ARCHAIC_FORTRESS;
	
	@ClientString(id = 1010684, message = "Front of the Innadril Castle Gate")
	public static NpcStringId FRONT_OF_THE_INNADRIL_CASTLE_GATE;
	
	@ClientString(id = 1010685, message = "Front of the Border Fortress")
	public static NpcStringId FRONT_OF_THE_BORDER_FORTRESS;
	
	@ClientString(id = 1010686, message = "Heine Town Square")
	public static NpcStringId HEINE_TOWN_SQUARE;
	
	@ClientString(id = 1010687, message = "Front of the Hive Fortress")
	public static NpcStringId FRONT_OF_THE_HIVE_FORTRESS;
	
	@ClientString(id = 1010688, message = "Front of the Narsell Fortress")
	public static NpcStringId FRONT_OF_THE_NARSELL_FORTRESS;
	
	@ClientString(id = 1010689, message = "Front of the Gludio Castle")
	public static NpcStringId FRONT_OF_THE_GLUDIO_CASTLE;
	
	@ClientString(id = 1010690, message = "Front of the Dion Castle")
	public static NpcStringId FRONT_OF_THE_DION_CASTLE;
	
	@ClientString(id = 1010691, message = "Front of the Giran Castle")
	public static NpcStringId FRONT_OF_THE_GIRAN_CASTLE;
	
	@ClientString(id = 1010692, message = "Front of the Oren Castle")
	public static NpcStringId FRONT_OF_THE_OREN_CASTLE;
	
	@ClientString(id = 1010693, message = "Front of the Aden Castle")
	public static NpcStringId FRONT_OF_THE_ADEN_CASTLE;
	
	@ClientString(id = 1010694, message = "Front of the Innadril Castle")
	public static NpcStringId FRONT_OF_THE_INNADRIL_CASTLE;
	
	@ClientString(id = 1010695, message = "Front of the Goddard Castle")
	public static NpcStringId FRONT_OF_THE_GODDARD_CASTLE;
	
	@ClientString(id = 1010696, message = "Front of the Rune Castle")
	public static NpcStringId FRONT_OF_THE_RUNE_CASTLE;
	
	@ClientString(id = 1010697, message = "Front of the Schuttgart Castle")
	public static NpcStringId FRONT_OF_THE_SCHUTTGART_CASTLE;
	
	@ClientString(id = 1010698, message = "Primeval Isle Wharf")
	public static NpcStringId PRIMEVAL_ISLE_WHARF;
	
	@ClientString(id = 1010699, message = "Isle of Prayer")
	public static NpcStringId ISLE_OF_PRAYER;
	
	@ClientString(id = 1010700, message = "Mithril Mines Western Entrance")
	public static NpcStringId MITHRIL_MINES_WESTERN_ENTRANCE;
	
	@ClientString(id = 1010701, message = "Mithril Mines Eastern Entrance")
	public static NpcStringId MITHRIL_MINES_EASTERN_ENTRANCE;
	
	@ClientString(id = 1010702, message = "The Giant's Cave Upper Layer")
	public static NpcStringId THE_GIANT_S_CAVE_UPPER_LAYER;
	
	@ClientString(id = 1010703, message = "The Giant's Cave Lower Layer")
	public static NpcStringId THE_GIANT_S_CAVE_LOWER_LAYER;
	
	@ClientString(id = 1010704, message = "Field of Silence Center")
	public static NpcStringId FIELD_OF_SILENCE_CENTER;
	
	@ClientString(id = 1010705, message = "Field of Whispers Center")
	public static NpcStringId FIELD_OF_WHISPERS_CENTER;
	
	@ClientString(id = 1010706, message = "Shyeed's Cavern")
	public static NpcStringId SHYEED_S_CAVERN;
	
	@ClientString(id = 1010707, message = "Sel Mahum Training Grounds (South Gate)")
	public static NpcStringId SEL_MAHUM_TRAINING_GROUNDS_SOUTH_GATE;
	
	@ClientString(id = 1010708, message = "Sel Mahum Training Grounds (Center)")
	public static NpcStringId SEL_MAHUM_TRAINING_GROUNDS_CENTER;
	
	@ClientString(id = 1010709, message = "Seed of Infinity Dock")
	public static NpcStringId SEED_OF_INFINITY_DOCK;
	
	@ClientString(id = 1010710, message = "Seed of Destruction Dock")
	public static NpcStringId SEED_OF_DESTRUCTION_DOCK;
	
	@ClientString(id = 1010711, message = "Seed of Annihilation Dock")
	public static NpcStringId SEED_OF_ANNIHILATION_DOCK;
	
	@ClientString(id = 1010712, message = "Town of Aden Einhasad Temple Priest Wood")
	public static NpcStringId TOWN_OF_ADEN_EINHASAD_TEMPLE_PRIEST_WOOD;
	
	@ClientString(id = 1010713, message = "Hunter's Village Separated Soul Front")
	public static NpcStringId HUNTER_S_VILLAGE_SEPARATED_SOUL_FRONT;
	
	@ClientString(id = 1029350, message = "What took so long? I waited for ever.")
	public static NpcStringId WHAT_TOOK_SO_LONG_I_WAITED_FOR_EVER;
	
	@ClientString(id = 1029351, message = "I must ask Librarian Sophia about the book.")
	public static NpcStringId I_MUST_ASK_LIBRARIAN_SOPHIA_ABOUT_THE_BOOK;
	
	@ClientString(id = 1029352, message = "This library... It's huge but there aren't many useful books, right?")
	public static NpcStringId THIS_LIBRARY_IT_S_HUGE_BUT_THERE_AREN_T_MANY_USEFUL_BOOKS_RIGHT;
	
	@ClientString(id = 1029353, message = "An underground library... I hate damp and smelly places...")
	public static NpcStringId AN_UNDERGROUND_LIBRARY_I_HATE_DAMP_AND_SMELLY_PLACES;
	
	@ClientString(id = 1029354, message = "The book that we seek is certainly here. Search inch by inch.")
	public static NpcStringId THE_BOOK_THAT_WE_SEEK_IS_CERTAINLY_HERE_SEARCH_INCH_BY_INCH;
	
	@ClientString(id = 1029450, message = "We must search high and low in every room for the reading desk that contains the book we seek.")
	public static NpcStringId WE_MUST_SEARCH_HIGH_AND_LOW_IN_EVERY_ROOM_FOR_THE_READING_DESK_THAT_CONTAINS_THE_BOOK_WE_SEEK;
	
	@ClientString(id = 1029451, message = "Remember the content of the books that you found. You can't take them out with you.")
	public static NpcStringId REMEMBER_THE_CONTENT_OF_THE_BOOKS_THAT_YOU_FOUND_YOU_CAN_T_TAKE_THEM_OUT_WITH_YOU;
	
	@ClientString(id = 1029452, message = "It seems that you cannot remember to the room of the watcher who found the book.")
	public static NpcStringId IT_SEEMS_THAT_YOU_CANNOT_REMEMBER_TO_THE_ROOM_OF_THE_WATCHER_WHO_FOUND_THE_BOOK;
	
	@ClientString(id = 1029453, message = "Your work here is done, so return to the central guardian.")
	public static NpcStringId YOUR_WORK_HERE_IS_DONE_SO_RETURN_TO_THE_CENTRAL_GUARDIAN;
	
	@ClientString(id = 1029460, message = "You foolish invaders who disturb the rest of Solina, be gone from this place.")
	public static NpcStringId YOU_FOOLISH_INVADERS_WHO_DISTURB_THE_REST_OF_SOLINA_BE_GONE_FROM_THIS_PLACE;
	
	@ClientString(id = 1029461, message = "I know not what you seek, but this truth cannot be handled by mere humans.")
	public static NpcStringId I_KNOW_NOT_WHAT_YOU_SEEK_BUT_THIS_TRUTH_CANNOT_BE_HANDLED_BY_MERE_HUMANS;
	
	@ClientString(id = 1029462, message = "I will not stand by and watch your foolish actions. I warn you, leave this place at once.")
	public static NpcStringId I_WILL_NOT_STAND_BY_AND_WATCH_YOUR_FOOLISH_ACTIONS_I_WARN_YOU_LEAVE_THIS_PLACE_AT_ONCE;
	
	@ClientString(id = 1029550, message = "The guardian of the seal doesn't seem to get injured at all until the barrier is destroyed.")
	public static NpcStringId THE_GUARDIAN_OF_THE_SEAL_DOESN_T_SEEM_TO_GET_INJURED_AT_ALL_UNTIL_THE_BARRIER_IS_DESTROYED;
	
	@ClientString(id = 1029551, message = "The device located in the room in front of the guardian of the seal is definitely the barrier that controls the guardian's power.")
	public static NpcStringId THE_DEVICE_LOCATED_IN_THE_ROOM_IN_FRONT_OF_THE_GUARDIAN_OF_THE_SEAL_IS_DEFINITELY_THE_BARRIER_THAT_CONTROLS_THE_GUARDIAN_S_POWER;
	
	@ClientString(id = 1029552, message = "To remove the barrier, you must find the relics that fit the barrier and activate the device.")
	public static NpcStringId TO_REMOVE_THE_BARRIER_YOU_MUST_FIND_THE_RELICS_THAT_FIT_THE_BARRIER_AND_ACTIVATE_THE_DEVICE;
	
	@ClientString(id = 1029553, message = "All the guardians were defeated, and the seal was removed. Teleport to the center.")
	public static NpcStringId ALL_THE_GUARDIANS_WERE_DEFEATED_AND_THE_SEAL_WAS_REMOVED_TELEPORT_TO_THE_CENTER;
	
	@ClientString(id = 1110001, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Can be used for item transportation.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_CAN_BE_USED_FOR_ITEM_TRANSPORTATION_A_BR;
	
	@ClientString(id = 1110002, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Can help during hunting by assisting in attacks. </a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_CAN_HELP_DURING_HUNTING_BY_ASSISTING_IN_ATTACKS_A_BR;
	
	@ClientString(id = 1110003, message = "<a action='bypass -h menu_select?ask=419&reply=1'>Can be sent to the village to buy items.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_CAN_BE_SENT_TO_THE_VILLAGE_TO_BUY_ITEMS_A_BR;
	
	@ClientString(id = 1110004, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Can be traded or sold to a new owner for adena.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_CAN_BE_TRADED_OR_SOLD_TO_A_NEW_OWNER_FOR_ADENA_A_BR;
	
	@ClientString(id = 1110005, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR;
	
	@ClientString(id = 1110006, message = "<a action='bypass -h menu_select?ask=419&reply=1'>When taking down a monster, always have a pet's company.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_WHEN_TAKING_DOWN_A_MONSTER_ALWAYS_HAVE_A_PET_S_COMPANY_A_BR;
	
	@ClientString(id = 1110007, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Tell your pet to pick up items.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_TELL_YOUR_PET_TO_PICK_UP_ITEMS_A_BR;
	
	@ClientString(id = 1110008, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Tell your pet to attack monsters first.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_TELL_YOUR_PET_TO_ATTACK_MONSTERS_FIRST_A_BR;
	
	@ClientString(id = 1110009, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Let your pet do what it wants.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_LET_YOUR_PET_DO_WHAT_IT_WANTS_A_BR;
	
	@ClientString(id = 1110010, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_2;
	
	@ClientString(id = 1110011, message = "<a action='bypass -h menu_select?ask=419&reply=0'>10 hours</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_10_HOURS_A_BR;
	
	@ClientString(id = 1110012, message = "<a action='bypass -h menu_select?ask=419&reply=0'>15 hours</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_15_HOURS_A_BR;
	
	@ClientString(id = 1110013, message = "<a action='bypass -h menu_select?ask=419&reply=1'>It won't disappear.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_IT_WON_T_DISAPPEAR_A_BR;
	
	@ClientString(id = 1110014, message = "<a action='bypass -h menu_select?ask=419&reply=0'>25 hours</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_25_HOURS_A_BR;
	
	@ClientString(id = 1110015, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_3;
	
	@ClientString(id = 1110016, message = "<a action='bypass -h menu_select?ask=419&reply=1'>Dire Wolf</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_DIRE_WOLF_A_BR;
	
	@ClientString(id = 1110017, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Air Wolf</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_AIR_WOLF_A_BR;
	
	@ClientString(id = 1110018, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Turek Wolf</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_TUREK_WOLF_A_BR;
	
	@ClientString(id = 1110019, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Kasha Wolf</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_KASHA_WOLF_A_BR;
	
	@ClientString(id = 1110020, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_4;
	
	@ClientString(id = 1110021, message = "<a action='bypass -h menu_select?ask=419&reply=1'>It's tail is always pointing straight down.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_IT_S_TAIL_IS_ALWAYS_POINTING_STRAIGHT_DOWN_A_BR;
	
	@ClientString(id = 1110022, message = "<a action='bypass -h menu_select?ask=419&reply=0'>It's tail is always curled up.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_IT_S_TAIL_IS_ALWAYS_CURLED_UP_A_BR;
	
	@ClientString(id = 1110023, message = "<a action='bypass -h menu_select?ask=419&reply=0'>It's tail is always wagging back and forth.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_IT_S_TAIL_IS_ALWAYS_WAGGING_BACK_AND_FORTH_A_BR;
	
	@ClientString(id = 1110024, message = "<a action='bypass -h menu_select?ask=419&reply=0'>What are you talking about?! A wolf doesn't have a tail.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_WHAT_ARE_YOU_TALKING_ABOUT_A_WOLF_DOESN_T_HAVE_A_TAIL_A_BR;
	
	@ClientString(id = 1110025, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_5;
	
	@ClientString(id = 1110026, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Raccoon</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_RACCOON_A_BR;
	
	@ClientString(id = 1110027, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Jackal</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_JACKAL_A_BR;
	
	@ClientString(id = 1110028, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Fox</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_FOX_A_BR;
	
	@ClientString(id = 1110029, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Shepherd Dog</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_SHEPHERD_DOG_A_BR;
	
	@ClientString(id = 1110030, message = "<a action='bypass -h menu_select?ask=419&reply=1'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_NONE_OF_THE_ABOVE_A_BR;
	
	@ClientString(id = 1110031, message = "<a action='bypass -h menu_select?ask=419&reply=0'>1.4 km</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_1_4_KM_A_BR;
	
	@ClientString(id = 1110032, message = "<a action='bypass -h menu_select?ask=419&reply=1'>2.4 km</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_2_4_KM_A_BR;
	
	@ClientString(id = 1110033, message = "<a action='bypass -h menu_select?ask=419&reply=0'>3.4 km</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_3_4_KM_A_BR;
	
	@ClientString(id = 1110034, message = "<a action='bypass -h menu_select?ask=419&reply=0'>4.4 km</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_4_4_KM_A_BR;
	
	@ClientString(id = 1110035, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_6;
	
	@ClientString(id = 1110036, message = "<a action='bypass -h menu_select?ask=419&reply=1'>Male</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_MALE_A_BR;
	
	@ClientString(id = 1110037, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Female</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_FEMALE_A_BR;
	
	@ClientString(id = 1110038, message = "<a action='bypass -h menu_select?ask=419&reply=0'>A baby that was born last year</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_A_BABY_THAT_WAS_BORN_LAST_YEAR_A_BR;
	
	@ClientString(id = 1110039, message = "<a action='bypass -h menu_select?ask=419&reply=0'>A baby that was born two years ago</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_A_BABY_THAT_WAS_BORN_TWO_YEARS_AGO_A_BR;
	
	@ClientString(id = 1110040, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_7;
	
	@ClientString(id = 1110041, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Goat</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_GOAT_A_BR;
	
	@ClientString(id = 1110042, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Meat of a dead animal</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_MEAT_OF_A_DEAD_ANIMAL_A_BR;
	
	@ClientString(id = 1110043, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Berries</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_BERRIES_A_BR;
	
	@ClientString(id = 1110044, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Wild Bird</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_WILD_BIRD_A_BR;
	
	@ClientString(id = 1110045, message = "<a action='bypass -h menu_select?ask=419&reply=1'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_NONE_OF_THE_ABOVE_A_BR_2;
	
	@ClientString(id = 1110046, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Breeding season is January-February.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_BREEDING_SEASON_IS_JANUARY_FEBRUARY_A_BR;
	
	@ClientString(id = 1110047, message = "<a action='bypass -h menu_select?ask=419&reply=1'>Pregnancy is nine months.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_PREGNANCY_IS_NINE_MONTHS_A_BR;
	
	@ClientString(id = 1110048, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Babies are born in April-June.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_BABIES_ARE_BORN_IN_APRIL_JUNE_A_BR;
	
	@ClientString(id = 1110049, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Has up to ten offspring at one time.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_HAS_UP_TO_TEN_OFFSPRING_AT_ONE_TIME_A_BR;
	
	@ClientString(id = 1110050, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_8;
	
	@ClientString(id = 1110051, message = "<a action='bypass -h menu_select?ask=419&reply=0'>3-6 years</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_3_6_YEARS_A_BR;
	
	@ClientString(id = 1110052, message = "<a action='bypass -h menu_select?ask=419&reply=0'>6-9 years</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_6_9_YEARS_A_BR;
	
	@ClientString(id = 1110053, message = "<a action='bypass -h menu_select?ask=419&reply=0'>9-12 years</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_9_12_YEARS_A_BR;
	
	@ClientString(id = 1110054, message = "<a action='bypass -h menu_select?ask=419&reply=1'>12-15 years</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_12_15_YEARS_A_BR;
	
	@ClientString(id = 1110055, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_9;
	
	@ClientString(id = 1110056, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Wolves gather and move in groups of 7-13 animals.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_WOLVES_GATHER_AND_MOVE_IN_GROUPS_OF_7_13_ANIMALS_A_BR;
	
	@ClientString(id = 1110057, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Wolves can eat a whole calf in one sitting.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_WOLVES_CAN_EAT_A_WHOLE_CALF_IN_ONE_SITTING_A_BR;
	
	@ClientString(id = 1110058, message = "<a action='bypass -h menu_select?ask=419&reply=0'>If they have water, wolves can live for 5-6 days without eating anything.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_IF_THEY_HAVE_WATER_WOLVES_CAN_LIVE_FOR_5_6_DAYS_WITHOUT_EATING_ANYTHING_A_BR;
	
	@ClientString(id = 1110059, message = "<a action='bypass -h menu_select?ask=419&reply=1'>A pregnant wolf makes its home in a wide open place to have its babies.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_A_PREGNANT_WOLF_MAKES_ITS_HOME_IN_A_WIDE_OPEN_PLACE_TO_HAVE_ITS_BABIES_A_BR;
	
	@ClientString(id = 1110060, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_10;
	
	@ClientString(id = 1110061, message = "<a action='bypass -h menu_select?ask=419&reply=1'>A grown wolf is still not as heavy as a fully-grown male adult human.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_A_GROWN_WOLF_IS_STILL_NOT_AS_HEAVY_AS_A_FULLY_GROWN_MALE_ADULT_HUMAN_A_BR;
	
	@ClientString(id = 1110062, message = "<a action='bypass -h menu_select?ask=419&reply=0'>A wolf changes into a werewolf during a full-moon.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_A_WOLF_CHANGES_INTO_A_WEREWOLF_DURING_A_FULL_MOON_A_BR;
	
	@ClientString(id = 1110063, message = "<a action='bypass -h menu_select?ask=419&reply=0'>The color of a wolf's fur is the same as the place where it lives.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_THE_COLOR_OF_A_WOLF_S_FUR_IS_THE_SAME_AS_THE_PLACE_WHERE_IT_LIVES_A_BR;
	
	@ClientString(id = 1110064, message = "<a action='bypass -h menu_select?ask=419&reply=0'>A wolf enjoys eating Dwarves.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_A_WOLF_ENJOYS_EATING_DWARVES_A_BR;
	
	@ClientString(id = 1110065, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_11;
	
	@ClientString(id = 1110066, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Talking Island - Wolf</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_TALKING_ISLAND_WOLF_A_BR;
	
	@ClientString(id = 1110067, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Dark Forest - Ashen Wolf</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_DARK_FOREST_ASHEN_WOLF_A_BR;
	
	@ClientString(id = 1110068, message = "<a action='bypass -h menu_select?ask=419&reply=0'>Elven Forest - Gray Wolf</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_ELVEN_FOREST_GRAY_WOLF_A_BR;
	
	@ClientString(id = 1110069, message = "<a action='bypass -h menu_select?ask=419&reply=1'>Orc - Black Wolf</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_1_ORC_BLACK_WOLF_A_BR;
	
	@ClientString(id = 1110070, message = "<a action='bypass -h menu_select?ask=419&reply=0'>None of the above.</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_419_REPLY_0_NONE_OF_THE_ABOVE_A_BR_12;
	
	@ClientString(id = 1110071, message = "... is the process of standing up.")
	public static NpcStringId IS_THE_PROCESS_OF_STANDING_UP;
	
	@ClientString(id = 1110072, message = "... is the process of sitting down.")
	public static NpcStringId IS_THE_PROCESS_OF_SITTING_DOWN;
	
	@ClientString(id = 1110073, message = "It is possible to use a skill while sitting down.")
	public static NpcStringId IT_IS_POSSIBLE_TO_USE_A_SKILL_WHILE_SITTING_DOWN;
	
	@ClientString(id = 1110074, message = "...is out of range.")
	public static NpcStringId IS_OUT_OF_RANGE;
	
	@ClientString(id = 1110075, message = "<a action='bypass -h menu_select?ask=255&reply=3' msg='811;Monster Derby'>Teleport to Monster Derby (Free)</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_255_REPLY_3_MSG_811_MONSTER_DERBY_TELEPORT_TO_MONSTER_DERBY_FREE_A_BR;
	
	@ClientString(id = 1111002, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Gladiator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_GLADIATOR_A_BR;
	
	@ClientString(id = 1111003, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Warlord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_WARLORD_A_BR;
	
	@ClientString(id = 1111005, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Paladin</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_PALADIN_A_BR;
	
	@ClientString(id = 1111006, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Dark Avenger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_DARK_AVENGER_A_BR;
	
	@ClientString(id = 1111008, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Treasure Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_TREASURE_HUNTER_A_BR;
	
	@ClientString(id = 1111009, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Hawkeye</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_HAWKEYE_A_BR;
	
	@ClientString(id = 1111012, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Sorcerer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SORCERER_A_BR;
	
	@ClientString(id = 1111013, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Necromancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_NECROMANCER_A_BR;
	
	@ClientString(id = 1111014, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Warlock</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_WARLOCK_A_BR;
	
	@ClientString(id = 1111016, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Bishop</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_BISHOP_A_BR;
	
	@ClientString(id = 1111017, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Prophet</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_PROPHET_A_BR;
	
	@ClientString(id = 1111020, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Temple Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_TEMPLE_KNIGHT_A_BR;
	
	@ClientString(id = 1111021, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Swordsinger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SWORDSINGER_A_BR;
	
	@ClientString(id = 1111023, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Plainswalker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_PLAINSWALKER_A_BR;
	
	@ClientString(id = 1111024, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Silver Ranger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SILVER_RANGER_A_BR;
	
	@ClientString(id = 1111027, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Spellsinger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SPELLSINGER_A_BR;
	
	@ClientString(id = 1111028, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Elemental Summoner</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_ELEMENTAL_SUMMONER_A_BR;
	
	@ClientString(id = 1111030, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Elven Elder</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_ELVEN_ELDER_A_BR;
	
	@ClientString(id = 1111033, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Shillien Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SHILLIEN_KNIGHT_A_BR;
	
	@ClientString(id = 1111034, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Bladedancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_BLADEDANCER_A_BR;
	
	@ClientString(id = 1111036, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Abyssal Walker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_ABYSSAL_WALKER_A_BR;
	
	@ClientString(id = 1111037, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Phantom Ranger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_PHANTOM_RANGER_A_BR;
	
	@ClientString(id = 1111040, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Spellhowler</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SPELLHOWLER_A_BR;
	
	@ClientString(id = 1111041, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Phantom Summoner</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_PHANTOM_SUMMONER_A_BR;
	
	@ClientString(id = 1111043, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Shillien Elder</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SHILLIEN_ELDER_A_BR;
	
	@ClientString(id = 1111046, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Destroyer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_DESTROYER_A_BR;
	
	@ClientString(id = 1111048, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Tyrant</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_TYRANT_A_BR;
	
	@ClientString(id = 1111051, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Overlord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_OVERLORD_A_BR;
	
	@ClientString(id = 1111052, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Warcryer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_WARCRYER_A_BR;
	
	@ClientString(id = 1111055, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Bounty Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_BOUNTY_HUNTER_A_BR;
	
	@ClientString(id = 1111057, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Warsmith</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_WARSMITH_A_BR;
	
	@ClientString(id = 1111058, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Duelist</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_DUELIST_A_BR;
	
	@ClientString(id = 1111059, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Dreadnought</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_DREADNOUGHT_A_BR;
	
	@ClientString(id = 1111060, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Phoenix Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_PHOENIX_KNIGHT_A_BR;
	
	@ClientString(id = 1111061, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Hell Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_HELL_KNIGHT_A_BR;
	
	@ClientString(id = 1111062, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Sagittarius</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SAGITTARIUS_A_BR;
	
	@ClientString(id = 1111063, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Adventurer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_ADVENTURER_A_BR;
	
	@ClientString(id = 1111064, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Archmage</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_ARCHMAGE_A_BR;
	
	@ClientString(id = 1111065, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Soultaker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SOULTAKER_A_BR;
	
	@ClientString(id = 1111066, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Arcana Lord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_ARCANA_LORD_A_BR;
	
	@ClientString(id = 1111067, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Cardinal</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_CARDINAL_A_BR;
	
	@ClientString(id = 1111068, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Hierophant</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_HIEROPHANT_A_BR;
	
	@ClientString(id = 1111069, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Eva's Templar</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_EVA_S_TEMPLAR_A_BR;
	
	@ClientString(id = 1111070, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Sword Muse</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SWORD_MUSE_A_BR;
	
	@ClientString(id = 1111071, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Wind Rider</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_WIND_RIDER_A_BR;
	
	@ClientString(id = 1111072, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Moonlight Sentinel</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_MOONLIGHT_SENTINEL_A_BR;
	
	@ClientString(id = 1111073, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Mystic Muse</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_MYSTIC_MUSE_A_BR;
	
	@ClientString(id = 1111074, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Elemental Master</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_ELEMENTAL_MASTER_A_BR;
	
	@ClientString(id = 1111075, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Eva's Saint</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_EVA_S_SAINT_A_BR;
	
	@ClientString(id = 1111076, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Shillien Templar</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SHILLIEN_TEMPLAR_A_BR;
	
	@ClientString(id = 1111077, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Spectral Dancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SPECTRAL_DANCER_A_BR;
	
	@ClientString(id = 1111078, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Ghost Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_GHOST_HUNTER_A_BR;
	
	@ClientString(id = 1111079, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Ghost Sentinel</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_GHOST_SENTINEL_A_BR;
	
	@ClientString(id = 1111080, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Storm Screamer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_STORM_SCREAMER_A_BR;
	
	@ClientString(id = 1111081, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Spectral Master</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SPECTRAL_MASTER_A_BR;
	
	@ClientString(id = 1111082, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Shillien Saint</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SHILLIEN_SAINT_A_BR;
	
	@ClientString(id = 1111083, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Titan</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_TITAN_A_BR;
	
	@ClientString(id = 1111084, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Grand Khavatari</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_GRAND_KHAVATARI_A_BR;
	
	@ClientString(id = 1111085, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Dominator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_DOMINATOR_A_BR;
	
	@ClientString(id = 1111086, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Doomcryer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_DOOMCRYER_A_BR;
	
	@ClientString(id = 1111087, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Fortune Seeker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_FORTUNE_SEEKER_A_BR;
	
	@ClientString(id = 1111088, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Maestro</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_MAESTRO_A_BR;
	
	@ClientString(id = 1112002, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Gladiator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_GLADIATOR_A_BR;
	
	@ClientString(id = 1112003, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Warlord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_WARLORD_A_BR;
	
	@ClientString(id = 1112005, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Paladin</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_PALADIN_A_BR;
	
	@ClientString(id = 1112006, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Dark Avenger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_DARK_AVENGER_A_BR;
	
	@ClientString(id = 1112008, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Treasure Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_TREASURE_HUNTER_A_BR;
	
	@ClientString(id = 1112009, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Hawkeye</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_HAWKEYE_A_BR;
	
	@ClientString(id = 1112012, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Sorcerer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SORCERER_A_BR;
	
	@ClientString(id = 1112013, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Necromancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_NECROMANCER_A_BR;
	
	@ClientString(id = 1112014, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Warlock</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_WARLOCK_A_BR;
	
	@ClientString(id = 1112016, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Bishop</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_BISHOP_A_BR;
	
	@ClientString(id = 1112017, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Prophet</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_PROPHET_A_BR;
	
	@ClientString(id = 1112020, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Temple Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_TEMPLE_KNIGHT_A_BR;
	
	@ClientString(id = 1112021, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Swordsinger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SWORDSINGER_A_BR;
	
	@ClientString(id = 1112023, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Plainswalker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_PLAINSWALKER_A_BR;
	
	@ClientString(id = 1112024, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Silver Ranger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SILVER_RANGER_A_BR;
	
	@ClientString(id = 1112027, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Spellsinger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SPELLSINGER_A_BR;
	
	@ClientString(id = 1112028, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Elemental Summoner</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_ELEMENTAL_SUMMONER_A_BR;
	
	@ClientString(id = 1112030, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Elven Elder</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_ELVEN_ELDER_A_BR;
	
	@ClientString(id = 1112033, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Shillien Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SHILLIEN_KNIGHT_A_BR;
	
	@ClientString(id = 1112034, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Bladedancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_BLADEDANCER_A_BR;
	
	@ClientString(id = 1112036, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Abyssal Walker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_ABYSSAL_WALKER_A_BR;
	
	@ClientString(id = 1112037, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Phantom Ranger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_PHANTOM_RANGER_A_BR;
	
	@ClientString(id = 1112040, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Spellhowler</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SPELLHOWLER_A_BR;
	
	@ClientString(id = 1112041, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Phantom Summoner</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_PHANTOM_SUMMONER_A_BR;
	
	@ClientString(id = 1112043, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Shillien Elder</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SHILLIEN_ELDER_A_BR;
	
	@ClientString(id = 1112046, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Destroyer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_DESTROYER_A_BR;
	
	@ClientString(id = 1112048, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Tyrant</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_TYRANT_A_BR;
	
	@ClientString(id = 1112051, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Overlord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_OVERLORD_A_BR;
	
	@ClientString(id = 1112052, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Warcryer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_WARCRYER_A_BR;
	
	@ClientString(id = 1112055, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Bounty Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_BOUNTY_HUNTER_A_BR;
	
	@ClientString(id = 1112057, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Warsmith</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_WARSMITH_A_BR;
	
	@ClientString(id = 1112058, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Duelist</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_DUELIST_A_BR;
	
	@ClientString(id = 1112059, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Dreadnought</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_DREADNOUGHT_A_BR;
	
	@ClientString(id = 1112060, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Phoenix Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_PHOENIX_KNIGHT_A_BR;
	
	@ClientString(id = 1112061, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Hell Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_HELL_KNIGHT_A_BR;
	
	@ClientString(id = 1112062, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Sagittarius</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SAGITTARIUS_A_BR;
	
	@ClientString(id = 1112063, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Adventurer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_ADVENTURER_A_BR;
	
	@ClientString(id = 1112064, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Archmage</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_ARCHMAGE_A_BR;
	
	@ClientString(id = 1112065, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Soultaker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SOULTAKER_A_BR;
	
	@ClientString(id = 1112066, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Arcana Lord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_ARCANA_LORD_A_BR;
	
	@ClientString(id = 1112067, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Cardinal</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_CARDINAL_A_BR;
	
	@ClientString(id = 1112068, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Hierophant</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_HIEROPHANT_A_BR;
	
	@ClientString(id = 1112069, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Eva's Templar</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_EVA_S_TEMPLAR_A_BR;
	
	@ClientString(id = 1112070, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Sword Muse</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SWORD_MUSE_A_BR;
	
	@ClientString(id = 1112071, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Wind Rider</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_WIND_RIDER_A_BR;
	
	@ClientString(id = 1112072, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Moonlight Sentinel</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_MOONLIGHT_SENTINEL_A_BR;
	
	@ClientString(id = 1112073, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Mystic Muse</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_MYSTIC_MUSE_A_BR;
	
	@ClientString(id = 1112074, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Elemental Master</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_ELEMENTAL_MASTER_A_BR;
	
	@ClientString(id = 1112075, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Eva's Saint</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_EVA_S_SAINT_A_BR;
	
	@ClientString(id = 1112076, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Shillien Templar</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SHILLIEN_TEMPLAR_A_BR;
	
	@ClientString(id = 1112077, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Spectral Dancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SPECTRAL_DANCER_A_BR;
	
	@ClientString(id = 1112078, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Ghost Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_GHOST_HUNTER_A_BR;
	
	@ClientString(id = 1112079, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Ghost Sentinel</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_GHOST_SENTINEL_A_BR;
	
	@ClientString(id = 1112080, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Storm Screamer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_STORM_SCREAMER_A_BR;
	
	@ClientString(id = 1112081, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Spectral Master</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SPECTRAL_MASTER_A_BR;
	
	@ClientString(id = 1112082, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Shillien Saint</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_SHILLIEN_SAINT_A_BR;
	
	@ClientString(id = 1112083, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Titan</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_TITAN_A_BR;
	
	@ClientString(id = 1112084, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Grand Khavatari</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_GRAND_KHAVATARI_A_BR;
	
	@ClientString(id = 1112085, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Dominator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_DOMINATOR_A_BR;
	
	@ClientString(id = 1112086, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Doomcryer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_DOOMCRYER_A_BR;
	
	@ClientString(id = 1112087, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Fortune Seeker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_FORTUNE_SEEKER_A_BR;
	
	@ClientString(id = 1112088, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=2'>Maestro</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_2_MAESTRO_A_BR;
	
	@ClientString(id = 1113002, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Gladiator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_GLADIATOR_A_BR;
	
	@ClientString(id = 1113003, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Warlord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_WARLORD_A_BR;
	
	@ClientString(id = 1113005, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Paladin</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_PALADIN_A_BR;
	
	@ClientString(id = 1113006, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Dark Avenger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_DARK_AVENGER_A_BR;
	
	@ClientString(id = 1113008, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Treasure Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_TREASURE_HUNTER_A_BR;
	
	@ClientString(id = 1113009, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Hawkeye</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_HAWKEYE_A_BR;
	
	@ClientString(id = 1113012, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Sorcerer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SORCERER_A_BR;
	
	@ClientString(id = 1113013, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Necromancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_NECROMANCER_A_BR;
	
	@ClientString(id = 1113014, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Warlock</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_WARLOCK_A_BR;
	
	@ClientString(id = 1113016, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Bishop</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_BISHOP_A_BR;
	
	@ClientString(id = 1113017, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Prophet</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_PROPHET_A_BR;
	
	@ClientString(id = 1113020, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Temple Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_TEMPLE_KNIGHT_A_BR;
	
	@ClientString(id = 1113021, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Swordsinger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SWORDSINGER_A_BR;
	
	@ClientString(id = 1113023, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Plainswalker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_PLAINSWALKER_A_BR;
	
	@ClientString(id = 1113024, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Silver Ranger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SILVER_RANGER_A_BR;
	
	@ClientString(id = 1113027, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Spellsinger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SPELLSINGER_A_BR;
	
	@ClientString(id = 1113028, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Elemental Summoner</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_ELEMENTAL_SUMMONER_A_BR;
	
	@ClientString(id = 1113030, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Elven Elder</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_ELVEN_ELDER_A_BR;
	
	@ClientString(id = 1113033, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Shillien Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SHILLIEN_KNIGHT_A_BR;
	
	@ClientString(id = 1113034, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Bladedancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_BLADEDANCER_A_BR;
	
	@ClientString(id = 1113036, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Abyssal Walker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_ABYSSAL_WALKER_A_BR;
	
	@ClientString(id = 1113037, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Phantom Ranger</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_PHANTOM_RANGER_A_BR;
	
	@ClientString(id = 1113040, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Spellhowler</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SPELLHOWLER_A_BR;
	
	@ClientString(id = 1113041, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Phantom Summoner</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_PHANTOM_SUMMONER_A_BR;
	
	@ClientString(id = 1113043, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Shillien Elder</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SHILLIEN_ELDER_A_BR;
	
	@ClientString(id = 1113046, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Destroyer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_DESTROYER_A_BR;
	
	@ClientString(id = 1113048, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Tyrant</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_TYRANT_A_BR;
	
	@ClientString(id = 1113051, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Overlord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_OVERLORD_A_BR;
	
	@ClientString(id = 1113052, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Warcryer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_WARCRYER_A_BR;
	
	@ClientString(id = 1113055, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Bounty Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_BOUNTY_HUNTER_A_BR;
	
	@ClientString(id = 1113057, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Warsmith</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_WARSMITH_A_BR;
	
	@ClientString(id = 1113058, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Duelist</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_DUELIST_A_BR;
	
	@ClientString(id = 1113059, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Dreadnought</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_DREADNOUGHT_A_BR;
	
	@ClientString(id = 1113060, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Phoenix Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_PHOENIX_KNIGHT_A_BR;
	
	@ClientString(id = 1113061, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Hell Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_HELL_KNIGHT_A_BR;
	
	@ClientString(id = 1113062, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Sagittarius</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SAGITTARIUS_A_BR;
	
	@ClientString(id = 1113063, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Adventurer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_ADVENTURER_A_BR;
	
	@ClientString(id = 1113064, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Archmage</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_ARCHMAGE_A_BR;
	
	@ClientString(id = 1113065, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Soultaker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SOULTAKER_A_BR;
	
	@ClientString(id = 1113066, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Arcana Lord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_ARCANA_LORD_A_BR;
	
	@ClientString(id = 1113067, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Cardinal</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_CARDINAL_A_BR;
	
	@ClientString(id = 1113068, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Hierophant</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_HIEROPHANT_A_BR;
	
	@ClientString(id = 1113069, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Eva's Templar</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_EVA_S_TEMPLAR_A_BR;
	
	@ClientString(id = 1113070, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Sword Muse</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SWORD_MUSE_A_BR;
	
	@ClientString(id = 1113071, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Wind Rider</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_WIND_RIDER_A_BR;
	
	@ClientString(id = 1113072, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Moonlight Sentinel</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_MOONLIGHT_SENTINEL_A_BR;
	
	@ClientString(id = 1113073, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Mystic Muse</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_MYSTIC_MUSE_A_BR;
	
	@ClientString(id = 1113074, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Elemental Master</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_ELEMENTAL_MASTER_A_BR;
	
	@ClientString(id = 1113075, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Eva's Saint</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_EVA_S_SAINT_A_BR;
	
	@ClientString(id = 1113076, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Shillien Templar</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SHILLIEN_TEMPLAR_A_BR;
	
	@ClientString(id = 1113077, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Spectral Dancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SPECTRAL_DANCER_A_BR;
	
	@ClientString(id = 1113078, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Ghost Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_GHOST_HUNTER_A_BR;
	
	@ClientString(id = 1113079, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Ghost Sentinel</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_GHOST_SENTINEL_A_BR;
	
	@ClientString(id = 1113080, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Storm Screamer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_STORM_SCREAMER_A_BR;
	
	@ClientString(id = 1113081, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Spectral Master</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SPECTRAL_MASTER_A_BR;
	
	@ClientString(id = 1113082, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Shillien Saint</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_SHILLIEN_SAINT_A_BR;
	
	@ClientString(id = 1113083, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Titan</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_TITAN_A_BR;
	
	@ClientString(id = 1113084, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Grand Khavatari</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_GRAND_KHAVATARI_A_BR;
	
	@ClientString(id = 1113085, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Dominator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_DOMINATOR_A_BR;
	
	@ClientString(id = 1113086, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Doomcryer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_DOOMCRYER_A_BR;
	
	@ClientString(id = 1113087, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Fortune Seeker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_FORTUNE_SEEKER_A_BR;
	
	@ClientString(id = 1113088, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=3'>Maestro</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_3_MAESTRO_A_BR;
	
	@ClientString(id = 1114088, message = "<a action='bypass -h menu_select?ask=-2&reply=380'>Duelist</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_380_DUELIST_A_BR;
	
	@ClientString(id = 1114089, message = "<a action='bypass -h menu_select?ask=-2&reply=381'>Dreadnought</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_381_DREADNOUGHT_A_BR;
	
	@ClientString(id = 1114090, message = "<a action='bypass -h menu_select?ask=-2&reply=382'>Phoenix Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_382_PHOENIX_KNIGHT_A_BR;
	
	@ClientString(id = 1114091, message = "<a action='bypass -h menu_select?ask=-2&reply=383'>Hell Knight</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_383_HELL_KNIGHT_A_BR;
	
	@ClientString(id = 1114092, message = "<a action='bypass -h menu_select?ask=-2&reply=384'>Sagittarius</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_384_SAGITTARIUS_A_BR;
	
	@ClientString(id = 1114093, message = "<a action='bypass -h menu_select?ask=-2&reply=385'>Adventurer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_385_ADVENTURER_A_BR;
	
	@ClientString(id = 1114094, message = "<a action='bypass -h menu_select?ask=-2&reply=386'>Archmage</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_386_ARCHMAGE_A_BR;
	
	@ClientString(id = 1114095, message = "<a action='bypass -h menu_select?ask=-2&reply=387'>Soultaker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_387_SOULTAKER_A_BR;
	
	@ClientString(id = 1114096, message = "<a action='bypass -h menu_select?ask=-2&reply=388'>Arcana Lord</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_388_ARCANA_LORD_A_BR;
	
	@ClientString(id = 1114097, message = "<a action='bypass -h menu_select?ask=-2&reply=389'>Cardinal</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_389_CARDINAL_A_BR;
	
	@ClientString(id = 1114098, message = "<a action='bypass -h menu_select?ask=-2&reply=390'>Hierophant</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_390_HIEROPHANT_A_BR;
	
	@ClientString(id = 1114099, message = "<a action='bypass -h menu_select?ask=-2&reply=391'>Eva's Templar</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_391_EVA_S_TEMPLAR_A_BR;
	
	@ClientString(id = 1114100, message = "<a action='bypass -h menu_select?ask=-2&reply=392'>Sword Muse</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_392_SWORD_MUSE_A_BR;
	
	@ClientString(id = 1114101, message = "<a action='bypass -h menu_select?ask=-2&reply=393'>Wind Rider</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_393_WIND_RIDER_A_BR;
	
	@ClientString(id = 1114102, message = "<a action='bypass -h menu_select?ask=-2&reply=394'>Moonlight Sentinel</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_394_MOONLIGHT_SENTINEL_A_BR;
	
	@ClientString(id = 1114103, message = "<a action='bypass -h menu_select?ask=-2&reply=395'>Mystic Muse</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_395_MYSTIC_MUSE_A_BR;
	
	@ClientString(id = 1114104, message = "<a action='bypass -h menu_select?ask=-2&reply=396'>Elemental Master</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_396_ELEMENTAL_MASTER_A_BR;
	
	@ClientString(id = 1114105, message = "<a action='bypass -h menu_select?ask=-2&reply=397'>Eva's Saint</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_397_EVA_S_SAINT_A_BR;
	
	@ClientString(id = 1114106, message = "<a action='bypass -h menu_select?ask=-2&reply=398'>Shillien Templar</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_398_SHILLIEN_TEMPLAR_A_BR;
	
	@ClientString(id = 1114107, message = "<a action='bypass -h menu_select?ask=-2&reply=399'>Spectral Dancer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_399_SPECTRAL_DANCER_A_BR;
	
	@ClientString(id = 1114108, message = "<a action='bypass -h menu_select?ask=-2&reply=400'>Ghost Hunter</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_400_GHOST_HUNTER_A_BR;
	
	@ClientString(id = 1114109, message = "<a action='bypass -h menu_select?ask=-2&reply=401'>Ghost Sentinel</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_401_GHOST_SENTINEL_A_BR;
	
	@ClientString(id = 1114110, message = "<a action='bypass -h menu_select?ask=-2&reply=402'>Storm Screamer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_402_STORM_SCREAMER_A_BR;
	
	@ClientString(id = 1114111, message = "<a action='bypass -h menu_select?ask=-2&reply=403'>Spectral Master</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_403_SPECTRAL_MASTER_A_BR;
	
	@ClientString(id = 1114112, message = "<a action='bypass -h menu_select?ask=-2&reply=404'>Shillien Saint</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_404_SHILLIEN_SAINT_A_BR;
	
	@ClientString(id = 1114113, message = "<a action='bypass -h menu_select?ask=-2&reply=405'>Titan</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_405_TITAN_A_BR;
	
	@ClientString(id = 1114114, message = "<a action='bypass -h menu_select?ask=-2&reply=406'>Grand Khavatari</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_406_GRAND_KHAVATARI_A_BR;
	
	@ClientString(id = 1114115, message = "<a action='bypass -h menu_select?ask=-2&reply=407'>Dominator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_407_DOMINATOR_A_BR;
	
	@ClientString(id = 1114116, message = "<a action='bypass -h menu_select?ask=-2&reply=408'>Doomcryer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_408_DOOMCRYER_A_BR;
	
	@ClientString(id = 1114117, message = "<a action='bypass -h menu_select?ask=-2&reply=409'>Fortune Seeker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_409_FORTUNE_SEEKER_A_BR;
	
	@ClientString(id = 1114118, message = "<a action='bypass -h menu_select?ask=-2&reply=410'>Maestro</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_410_MAESTRO_A_BR;
	
	@ClientString(id = 1115127, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Berserker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_BERSERKER_A_BR;
	
	@ClientString(id = 1115128, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Soul Breaker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SOUL_BREAKER_A_BR;
	
	@ClientString(id = 1115129, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Soul Breaker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SOUL_BREAKER_A_BR_2;
	
	@ClientString(id = 1115130, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Arbalester</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_ARBALESTER_A_BR;
	
	@ClientString(id = 1115131, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Doombringer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_DOOMBRINGER_A_BR;
	
	@ClientString(id = 1115132, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Soul Hound</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SOUL_HOUND_A_BR;
	
	@ClientString(id = 1115133, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Soul Hound</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_SOUL_HOUND_A_BR_2;
	
	@ClientString(id = 1115134, message = "<a action='bypass -h menu_select?ask=-2&reply=41&state=1'>Trickster</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_41_STATE_1_TRICKSTER_A_BR;
	
	@ClientString(id = 1116127, message = "<a action='bypass -h menu_select?ask=-2&reply=411'>Berserker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_411_BERSERKER_A_BR;
	
	@ClientString(id = 1116128, message = "<a action='bypass -h menu_select?ask=-2&reply=412'>Soul Breaker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_412_SOUL_BREAKER_A_BR;
	
	@ClientString(id = 1116129, message = "<a action='bypass -h menu_select?ask=-2&reply=413'>Soul Breaker</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_413_SOUL_BREAKER_A_BR;
	
	@ClientString(id = 1116130, message = "<a action='bypass -h menu_select?ask=-2&reply=414'>Arbalester</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_414_ARBALESTER_A_BR;
	
	@ClientString(id = 1116131, message = "<a action='bypass -h menu_select?ask=-2&reply=415'>Doombringer</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_415_DOOMBRINGER_A_BR;
	
	@ClientString(id = 1116132, message = "<a action='bypass -h menu_select?ask=-2&reply=416'>Soul Hound</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_416_SOUL_HOUND_A_BR;
	
	@ClientString(id = 1116133, message = "<a action='bypass -h menu_select?ask=-2&reply=417'>Soul Hound</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_417_SOUL_HOUND_A_BR;
	
	@ClientString(id = 1116134, message = "<a action='bypass -h menu_select?ask=-2&reply=418'>Trickster</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_418_TRICKSTER_A_BR;
	
	@ClientString(id = 1116135, message = "<a action='bypass -h menu_select?ask=-2&reply=419'>Inspector</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_419_INSPECTOR_A_BR;
	
	@ClientString(id = 1116136, message = "<a action='bypass -h menu_select?ask=-2&reply=420'>Judicator</a><br>")
	public static NpcStringId A_ACTION_BYPASS_H_MENU_SELECT_ASK_2_REPLY_420_JUDICATOR_A_BR;
	
	@ClientString(id = 1119999, message = " ")
	public static NpcStringId EMPTY_3;
	
	@ClientString(id = 1120001, message = "Greyclaw Kutus (lv23)")
	public static NpcStringId GREYCLAW_KUTUS_LV23;
	
	@ClientString(id = 1120002, message = "Turek Mercenary Captain (lv30)")
	public static NpcStringId TUREK_MERCENARY_CAPTAIN_LV30;
	
	@ClientString(id = 1120003, message = "Retreat Spider Cletu (lv42)")
	public static NpcStringId RETREAT_SPIDER_CLETU_LV42;
	
	@ClientString(id = 1120004, message = "Furious Thieles (lv55)")
	public static NpcStringId FURIOUS_THIELES_LV55;
	
	@ClientString(id = 1120005, message = "Spiteful Soul of Peasant Leader (lv50)")
	public static NpcStringId SPITEFUL_SOUL_OF_PEASANT_LEADER_LV50;
	
	@ClientString(id = 1120006, message = "The 3rd Underwater Guardian (lv60)")
	public static NpcStringId THE_3RD_UNDERWATER_GUARDIAN_LV60;
	
	@ClientString(id = 1120007, message = "Pan Dryad (lv25)")
	public static NpcStringId PAN_DRYAD_LV25;
	
	@ClientString(id = 1120008, message = "Breka Warlock Pastu (lv34)")
	public static NpcStringId BREKA_WARLOCK_PASTU_LV34;
	
	@ClientString(id = 1120009, message = "Stakato Queen Zyrnna (lv34)")
	public static NpcStringId STAKATO_QUEEN_ZYRNNA_LV34;
	
	@ClientString(id = 1120010, message = "Ketra Commander Atis (lv49)")
	public static NpcStringId KETRA_COMMANDER_ATIS_LV49;
	
	@ClientString(id = 1120011, message = "Atraiban (lv53)")
	public static NpcStringId ATRAIBAN_LV53;
	
	@ClientString(id = 1120012, message = "Eva's Guardian Millenu (lv58)")
	public static NpcStringId EVA_S_GUARDIAN_MILLENU_LV58;
	
	@ClientString(id = 1120013, message = "Shilen's Messenger Cabrio (lv70)")
	public static NpcStringId SHILEN_S_MESSENGER_CABRIO_LV70;
	
	@ClientString(id = 1120014, message = "Tirak (lv28)")
	public static NpcStringId TIRAK_LV28;
	
	@ClientString(id = 1120015, message = "Remmel (lv35)")
	public static NpcStringId REMMEL_LV35;
	
	@ClientString(id = 1120016, message = "Barion (lv47)")
	public static NpcStringId BARION_LV47;
	
	@ClientString(id = 1120017, message = "Karte (lv49)")
	public static NpcStringId KARTE_LV49;
	
	@ClientString(id = 1120018, message = "Verfa (lv51)")
	public static NpcStringId VERFA_LV51;
	
	@ClientString(id = 1120019, message = "Rahha (lv65)")
	public static NpcStringId RAHHA_LV65;
	
	@ClientString(id = 1120020, message = "Kernon (lv75)")
	public static NpcStringId KERNON_LV75;
	
	@ClientString(id = 1120021, message = "Beacon of Blue Sky (lv45)")
	public static NpcStringId BEACON_OF_BLUE_SKY_LV45;
	
	@ClientString(id = 1120022, message = "Unrequited Kael (lv24)")
	public static NpcStringId UNREQUITED_KAEL_LV24;
	
	@ClientString(id = 1120023, message = "Chertuba of Great Soul (lv35)")
	public static NpcStringId CHERTUBA_OF_GREAT_SOUL_LV35;
	
	@ClientString(id = 1120024, message = "Mystic of Storm Teruk (lv40)")
	public static NpcStringId MYSTIC_OF_STORM_TERUK_LV40;
	
	@ClientString(id = 1120025, message = "Shaka, Captain of the Red Flag (lv52)")
	public static NpcStringId SHAKA_CAPTAIN_OF_THE_RED_FLAG_LV52;
	
	@ClientString(id = 1120026, message = "Enchanted Forest Watcher Ruell (lv55)")
	public static NpcStringId ENCHANTED_FOREST_WATCHER_RUELL_LV55;
	
	@ClientString(id = 1120027, message = "Bloody Priest Rudelto (lv69)")
	public static NpcStringId BLOODY_PRIEST_RUDELTO_LV69;
	
	@ClientString(id = 1120028, message = "Princess Molrang (lv25)")
	public static NpcStringId PRINCESS_MOLRANG_LV25;
	
	@ClientString(id = 1120029, message = "Cat's Eye (lv30)")
	public static NpcStringId CAT_S_EYE_LV30;
	
	@ClientString(id = 1120030, message = "Leader of Cat Gang(lv39)")
	public static NpcStringId LEADER_OF_CAT_GANG_LV39;
	
	@ClientString(id = 1120031, message = "Timak Orc Chief Ranger (lv44)")
	public static NpcStringId TIMAK_ORC_CHIEF_RANGER_LV44;
	
	@ClientString(id = 1120032, message = "Crazy Mechanic Golem (lv43)")
	public static NpcStringId CRAZY_MECHANIC_GOLEM_LV43;
	
	@ClientString(id = 1120033, message = "Soulless Wild Boar (lv59)")
	public static NpcStringId SOULLESS_WILD_BOAR_LV59;
	
	@ClientString(id = 1120034, message = "Korim (lv70)")
	public static NpcStringId KORIM_LV70;
	
	@ClientString(id = 1120035, message = "Elf Renoa (lv29)")
	public static NpcStringId ELF_RENOA_LV29;
	
	@ClientString(id = 1120036, message = "Sejarr's Servitor (lv35)")
	public static NpcStringId SEJARR_S_SERVITOR_LV35;
	
	@ClientString(id = 1120037, message = "Rotten Tree Repiro (lv44)")
	public static NpcStringId ROTTEN_TREE_REPIRO_LV44;
	
	@ClientString(id = 1120038, message = "Shacram (lv45)")
	public static NpcStringId SHACRAM_LV45;
	
	@ClientString(id = 1120039, message = "Sorcerer Isirr (lv55)")
	public static NpcStringId SORCERER_ISIRR_LV55;
	
	@ClientString(id = 1120040, message = "Lidia, Ghost of the Well (lv60)")
	public static NpcStringId LIDIA_GHOST_OF_THE_WELL_LV60;
	
	@ClientString(id = 1120041, message = "Cloe, Priest of Antharas (lv74)")
	public static NpcStringId CLOE_PRIEST_OF_ANTHARAS_LV74;
	
	@ClientString(id = 1120042, message = "Meana, Agent of Beres (lv30)")
	public static NpcStringId MEANA_AGENT_OF_BERES_LV30;
	
	@ClientString(id = 1120043, message = "Icarus Sample 1 (lv40)")
	public static NpcStringId ICARUS_SAMPLE_1_LV40;
	
	@ClientString(id = 1120044, message = "Guilotine, Warden of the Execution Grounds (lv35)")
	public static NpcStringId GUILOTINE_WARDEN_OF_THE_EXECUTION_GROUNDS_LV35;
	
	@ClientString(id = 1120045, message = "Berun, Messenger of the Fairy Queen (lv50)")
	public static NpcStringId BERUN_MESSENGER_OF_THE_FAIRY_QUEEN_LV50;
	
	@ClientString(id = 1120046, message = "Hopeful Refugee Leo (lv56)")
	public static NpcStringId HOPEFUL_REFUGEE_LEO_LV56;
	
	@ClientString(id = 1120047, message = "Fierce Tiger King Angel (lv65)")
	public static NpcStringId FIERCE_TIGER_KING_ANGEL_LV65;
	
	@ClientString(id = 1120048, message = "Longhorn Golkonda (lv79)")
	public static NpcStringId LONGHORN_GOLKONDA_LV79;
	
	@ClientString(id = 1120049, message = "Langk Matriarch Rashkos (lv24)")
	public static NpcStringId LANGK_MATRIARCH_RASHKOS_LV24;
	
	@ClientString(id = 1120050, message = "Vuku Grand Seer Gharmash (lv33)")
	public static NpcStringId VUKU_GRAND_SEER_GHARMASH_LV33;
	
	@ClientString(id = 1120051, message = "Carnage Lord Gato (lv50)")
	public static NpcStringId CARNAGE_LORD_GATO_LV50;
	
	@ClientString(id = 1120052, message = "Leto Chief Talkin (lv40)")
	public static NpcStringId LETO_CHIEF_TALKIN_LV40;
	
	@ClientString(id = 1120053, message = "Sephia, Seer of Bereth (lv55)")
	public static NpcStringId SEPHIA_SEER_OF_BERETH_LV55;
	
	@ClientString(id = 1120054, message = "Hekaton Prime (lv65)")
	public static NpcStringId HEKATON_PRIME_LV65;
	
	@ClientString(id = 1120055, message = "Shuriel, Fire of Wrath (lv78)")
	public static NpcStringId SHURIEL_FIRE_OF_WRATH_LV78;
	
	@ClientString(id = 1120056, message = "Evil Spirit Bifrons (lv21)")
	public static NpcStringId EVIL_SPIRIT_BIFRONS_LV21;
	
	@ClientString(id = 1120057, message = "Zombie Lord Crowl (lv25)")
	public static NpcStringId ZOMBIE_LORD_CROWL_LV25;
	
	@ClientString(id = 1120058, message = "Flame Lord Shadar (lv35)")
	public static NpcStringId FLAME_LORD_SHADAR_LV35;
	
	@ClientString(id = 1120059, message = "Shaman King Selu (lv40)")
	public static NpcStringId SHAMAN_KING_SELU_LV40;
	
	@ClientString(id = 1120060, message = "King Tarlk (lv48)")
	public static NpcStringId KING_TARLK_LV48;
	
	@ClientString(id = 1120061, message = "Unicorn Paniel (lv54)")
	public static NpcStringId UNICORN_PANIEL_LV54;
	
	@ClientString(id = 1120062, message = "Giant Marpanak (lv60)")
	public static NpcStringId GIANT_MARPANAK_LV60;
	
	@ClientString(id = 1120063, message = "Roaring Skylancer (lv70)")
	public static NpcStringId ROARING_SKYLANCER_LV70;
	
	@ClientString(id = 1120064, message = "Ikuntai (lv25)")
	public static NpcStringId IKUNTAI_LV25;
	
	@ClientString(id = 1120065, message = "Ragraman (lv30)")
	public static NpcStringId RAGRAMAN_LV30;
	
	@ClientString(id = 1120066, message = "Lizardmen Leader Hellion (lv38)")
	public static NpcStringId LIZARDMEN_LEADER_HELLION_LV38;
	
	@ClientString(id = 1120067, message = "Tiger King Karuta (lv45)")
	public static NpcStringId TIGER_KING_KARUTA_LV45;
	
	@ClientString(id = 1120068, message = "Black Lilly (lv55)")
	public static NpcStringId BLACK_LILLY_LV55;
	
	@ClientString(id = 1120069, message = "Karum, Guardian of the Statue of the Giant (lv60)")
	public static NpcStringId KARUM_GUARDIAN_OF_THE_STATUE_OF_THE_GIANT_LV60;
	
	@ClientString(id = 1120070, message = "Demon Kuri (lv59)")
	public static NpcStringId DEMON_KURI_LV59;
	
	@ClientString(id = 1120071, message = "Tasaba Patriarch Hellena (lv35)")
	public static NpcStringId TASABA_PATRIARCH_HELLENA_LV35;
	
	@ClientString(id = 1120072, message = "Apepi (lv30)")
	public static NpcStringId APEPI_LV30;
	
	@ClientString(id = 1120073, message = "Cronos's Servitor Mumu (lv34)")
	public static NpcStringId CRONOS_S_SERVITOR_MUMU_LV34;
	
	@ClientString(id = 1120074, message = "Earth Protector Panathen (lv43)")
	public static NpcStringId EARTH_PROTECTOR_PANATHEN_LV43;
	
	@ClientString(id = 1120075, message = "Fafurion's Messenger Loch Ness (lv70)")
	public static NpcStringId FAFURION_S_MESSENGER_LOCH_NESS_LV70;
	
	@ClientString(id = 1120076, message = "Fafurion's Seer Sheshark (lv72)")
	public static NpcStringId FAFURION_S_SEER_SHESHARK_LV72;
	
	@ClientString(id = 1120077, message = "Crokian Padisha Sobekk (lv74)")
	public static NpcStringId CROKIAN_PADISHA_SOBEKK_LV74;
	
	@ClientString(id = 1120078, message = "Ocean's Flame Ashakiel (lv76)")
	public static NpcStringId OCEAN_S_FLAME_ASHAKIEL_LV76;
	
	@ClientString(id = 1120079, message = "Water Couatle Ateka (lv40)")
	public static NpcStringId WATER_COUATLE_ATEKA_LV40;
	
	@ClientString(id = 1120080, message = "Sebek (lv36)")
	public static NpcStringId SEBEK_LV36;
	
	@ClientString(id = 1120081, message = "Fafurion's Page Sika (lv40)")
	public static NpcStringId FAFURION_S_PAGE_SIKA_LV40;
	
	@ClientString(id = 1120082, message = "Cursed Clara (lv50)")
	public static NpcStringId CURSED_CLARA_LV50;
	
	@ClientString(id = 1120083, message = "Death Lord Hallate (lv73)")
	public static NpcStringId DEATH_LORD_HALLATE_LV73;
	
	@ClientString(id = 1120084, message = "Soul Collector Acheron (lv35)")
	public static NpcStringId SOUL_COLLECTOR_ACHERON_LV35;
	
	@ClientString(id = 1120085, message = "Roaring Lord Kastor (lv62)")
	public static NpcStringId ROARING_LORD_KASTOR_LV62;
	
	@ClientString(id = 1120086, message = "Storm Winged Naga (lv75)")
	public static NpcStringId STORM_WINGED_NAGA_LV75;
	
	@ClientString(id = 1120087, message = "Ragoth, Seer of Timak (lv57)")
	public static NpcStringId RAGOTH_SEER_OF_TIMAK_LV57;
	
	@ClientString(id = 1120088, message = "Spiteful Soul of Andras the Betrayer (lv69)")
	public static NpcStringId SPITEFUL_SOUL_OF_ANDRAS_THE_BETRAYER_LV69;
	
	@ClientString(id = 1120089, message = "Ancient Drake (lv60)")
	public static NpcStringId ANCIENT_DRAKE_LV60;
	
	@ClientString(id = 1120090, message = "Vanor Chief Kandra (lv72)")
	public static NpcStringId VANOR_CHIEF_KANDRA_LV72;
	
	@ClientString(id = 1120091, message = "Abyss Brukunt (lv59)")
	public static NpcStringId ABYSS_BRUKUNT_LV59;
	
	@ClientString(id = 1120092, message = "Harit Hero Tamash (lv55)")
	public static NpcStringId HARIT_HERO_TAMASH_LV55;
	
	@ClientString(id = 1120093, message = "Last Lesser Giant Olkuth (lv75)")
	public static NpcStringId LAST_LESSER_GIANT_OLKUTH_LV75;
	
	@ClientString(id = 1120094, message = "Last Lesser Giant Glaki (lv78)")
	public static NpcStringId LAST_LESSER_GIANT_GLAKI_LV78;
	
	@ClientString(id = 1120095, message = "Doom Blade Tanatos (lv72)")
	public static NpcStringId DOOM_BLADE_TANATOS_LV72;
	
	@ClientString(id = 1120096, message = "Palatanos of the Fearsome Power (lv75)")
	public static NpcStringId PALATANOS_OF_THE_FEARSOME_POWER_LV75;
	
	@ClientString(id = 1120097, message = "Palibati Queen Themis (lv70)")
	public static NpcStringId PALIBATI_QUEEN_THEMIS_LV70;
	
	@ClientString(id = 1120098, message = "Gargoyle Lord Tiphon (lv65)")
	public static NpcStringId GARGOYLE_LORD_TIPHON_LV65;
	
	@ClientString(id = 1120099, message = "Taik High Prefect Arak (lv60)")
	public static NpcStringId TAIK_HIGH_PREFECT_ARAK_LV60;
	
	@ClientString(id = 1120100, message = "Zaken's Butcher Krantz (lv55)")
	public static NpcStringId ZAKEN_S_BUTCHER_KRANTZ_LV55;
	
	@ClientString(id = 1120101, message = "Iron Giant Totem (lv45)")
	public static NpcStringId IRON_GIANT_TOTEM_LV45;
	
	@ClientString(id = 1120102, message = "Kernon's Faithful Servant Kelone (lv67)")
	public static NpcStringId KERNON_S_FAITHFUL_SERVANT_KELONE_LV67;
	
	@ClientString(id = 1120103, message = "Bloody Empress Decarbia (lv75)")
	public static NpcStringId BLOODY_EMPRESS_DECARBIA_LV75;
	
	@ClientString(id = 1120104, message = "Beast Lord Behemoth (lv70)")
	public static NpcStringId BEAST_LORD_BEHEMOTH_LV70;
	
	@ClientString(id = 1120105, message = "Partisan Leader Talakin (lv28)")
	public static NpcStringId PARTISAN_LEADER_TALAKIN_LV28;
	
	@ClientString(id = 1120106, message = "Carnamakos (lv50)")
	public static NpcStringId CARNAMAKOS_LV50;
	
	@ClientString(id = 1120107, message = "Death Lord Ipos (lv75)")
	public static NpcStringId DEATH_LORD_IPOS_LV75;
	
	@ClientString(id = 1120108, message = "Lilith's Witch Marilion (lv50)")
	public static NpcStringId LILITH_S_WITCH_MARILION_LV50;
	
	@ClientString(id = 1120109, message = "Pagan Watcher Cerberon (lv55)")
	public static NpcStringId PAGAN_WATCHER_CERBERON_LV55;
	
	@ClientString(id = 1120110, message = "Anakim's Nemesis Zakaron(lv70)")
	public static NpcStringId ANAKIM_S_NEMESIS_ZAKARON_LV70;
	
	@ClientString(id = 1120111, message = "Death Lord Shax (lv75)")
	public static NpcStringId DEATH_LORD_SHAX_LV75;
	
	@ClientString(id = 1120112, message = "Hestia, Guardian Deity of the Hot Springs (lv78)")
	public static NpcStringId HESTIA_GUARDIAN_DEITY_OF_THE_HOT_SPRINGS_LV78;
	
	@ClientString(id = 1120113, message = "Ketra's Hero Hekaton (lv80)")
	public static NpcStringId KETRA_S_HERO_HEKATON_LV80;
	
	@ClientString(id = 1120114, message = "Ketra's Commander Tayr (lv80)")
	public static NpcStringId KETRA_S_COMMANDER_TAYR_LV80;
	
	@ClientString(id = 1120115, message = "Ketra's Chief Braki (lv80)")
	public static NpcStringId KETRA_S_CHIEF_BRAKI_LV80;
	
	@ClientString(id = 1120116, message = "Varka's Hero Shadith (lv80)")
	public static NpcStringId VARKA_S_HERO_SHADITH_LV80;
	
	@ClientString(id = 1120117, message = "Varka's Commander Mos (lv80)")
	public static NpcStringId VARKA_S_COMMANDER_MOS_LV80;
	
	@ClientString(id = 1120118, message = "Varka's Chief Horus (lv80)")
	public static NpcStringId VARKA_S_CHIEF_HORUS_LV80;
	
	@ClientString(id = 1120119, message = "Ember (lv80)")
	public static NpcStringId EMBER_LV80;
	
	@ClientString(id = 1120120, message = "Demon's Agent Falston (lv66)")
	public static NpcStringId DEMON_S_AGENT_FALSTON_LV66;
	
	@ClientString(id = 1120121, message = "Barakiel, the Flame of Splendor (lv70)")
	public static NpcStringId BARAKIEL_THE_FLAME_OF_SPLENDOR_LV70;
	
	@ClientString(id = 1120122, message = "Eilhalder Von Hellmann (lv71)")
	public static NpcStringId EILHALDER_VON_HELLMANN_LV71;
	
	@ClientString(id = 1120123, message = "Giant Wastelands Basilisk (lv30)")
	public static NpcStringId GIANT_WASTELANDS_BASILISK_LV30;
	
	@ClientString(id = 1120124, message = "Gargoyle Lord Sirocco (lv35)")
	public static NpcStringId GARGOYLE_LORD_SIROCCO_LV35;
	
	@ClientString(id = 1120125, message = "Sukar Wererat Chief (lv21)")
	public static NpcStringId SUKAR_WERERAT_CHIEF_LV21;
	
	@ClientString(id = 1120126, message = "Tiger Hornet (lv26)")
	public static NpcStringId TIGER_HORNET_LV26;
	
	@ClientString(id = 1120127, message = "Tracker Leader Sharuk (lv23)")
	public static NpcStringId TRACKER_LEADER_SHARUK_LV23;
	
	@ClientString(id = 1120128, message = "Patriarch Kuroboros (lv26)")
	public static NpcStringId PATRIARCH_KUROBOROS_LV26;
	
	@ClientString(id = 1120129, message = "Kuroboros' Priest (lv23)")
	public static NpcStringId KUROBOROS_PRIEST_LV23;
	
	@ClientString(id = 1120130, message = "Soul Scavenger (lv25)")
	public static NpcStringId SOUL_SCAVENGER_LV25;
	
	@ClientString(id = 1120131, message = "Discarded Guardian (lv20)")
	public static NpcStringId DISCARDED_GUARDIAN_LV20;
	
	@ClientString(id = 1120132, message = "Malex, Herald of Dagoniel (lv21)")
	public static NpcStringId MALEX_HERALD_OF_DAGONIEL_LV21;
	
	@ClientString(id = 1120133, message = "Zombie Lord Ferkel (lv20)")
	public static NpcStringId ZOMBIE_LORD_FERKEL_LV20;
	
	@ClientString(id = 1120134, message = "Madness Beast (lv20)")
	public static NpcStringId MADNESS_BEAST_LV20;
	
	@ClientString(id = 1120135, message = "Kaysha, Herald of Icarus (lv21)")
	public static NpcStringId KAYSHA_HERALD_OF_ICARUS_LV21;
	
	@ClientString(id = 1120136, message = "Ghost of Sir Calibus (lv34)")
	public static NpcStringId GHOST_OF_SIR_CALIBUS_LV34;
	
	@ClientString(id = 1120137, message = "Evil Spirit Tempest (lv36)")
	public static NpcStringId EVIL_SPIRIT_TEMPEST_LV36;
	
	@ClientString(id = 1120138, message = "Red Eye Captain Trakia (lv35)")
	public static NpcStringId RED_EYE_CAPTAIN_TRAKIA_LV35;
	
	@ClientString(id = 1120139, message = "Nurka's Messenger (lv33)")
	public static NpcStringId NURKA_S_MESSENGER_LV33;
	
	@ClientString(id = 1120140, message = "Captain of the Queen's Royal Guard (lv32)")
	public static NpcStringId CAPTAIN_OF_THE_QUEEN_S_ROYAL_GUARD_LV32;
	
	@ClientString(id = 1120141, message = "Premo Prime (lv38)")
	public static NpcStringId PREMO_PRIME_LV38;
	
	@ClientString(id = 1120142, message = "Archon Suscepter (lv45)")
	public static NpcStringId ARCHON_SUSCEPTER_LV45;
	
	@ClientString(id = 1120143, message = "Eye of Beleth (lv35)")
	public static NpcStringId EYE_OF_BELETH_LV35;
	
	@ClientString(id = 1120144, message = "Skyla (lv32)")
	public static NpcStringId SKYLA_LV32;
	
	@ClientString(id = 1120145, message = "Corsair Captain Kylon (lv33)")
	public static NpcStringId CORSAIR_CAPTAIN_KYLON_LV33;
	
	@ClientString(id = 1120146, message = "Lord Ishka (lv60)")
	public static NpcStringId LORD_ISHKA_LV60;
	
	@ClientString(id = 1120147, message = "Road Scavenger Leader (lv40)")
	public static NpcStringId ROAD_SCAVENGER_LEADER_LV40;
	
	@ClientString(id = 1120148, message = "Necrosentinel Royal Guard (lv47)")
	public static NpcStringId NECROSENTINEL_ROYAL_GUARD_LV47;
	
	@ClientString(id = 1120149, message = "Nakondas (lv40)")
	public static NpcStringId NAKONDAS_LV40;
	
	@ClientString(id = 1120150, message = "Dread Avenger Kraven (lv44)")
	public static NpcStringId DREAD_AVENGER_KRAVEN_LV44;
	
	@ClientString(id = 1120151, message = "Orfen's Handmaiden (lv48)")
	public static NpcStringId ORFEN_S_HANDMAIDEN_LV48;
	
	@ClientString(id = 1120152, message = "Fairy Queen Timiniel (lv61)")
	public static NpcStringId FAIRY_QUEEN_TIMINIEL_LV61;
	
	@ClientString(id = 1120153, message = "Freki, Betrayer of Urutu (lv25)")
	public static NpcStringId FREKI_BETRAYER_OF_URUTU_LV25;
	
	@ClientString(id = 1120154, message = "Mammon's Collector Talloth (lv25)")
	public static NpcStringId MAMMON_S_COLLECTOR_TALLOTH_LV25;
	
	@ClientString(id = 1120155, message = "Flame Stone Golem (lv44)")
	public static NpcStringId FLAME_STONE_GOLEM_LV44;
	
	@ClientString(id = 1120156, message = "Bandit Leader Barda (lv55)")
	public static NpcStringId BANDIT_LEADER_BARDA_LV55;
	
	@ClientString(id = 1120157, message = "Timak Orc Gosmos (lv45)")
	public static NpcStringId TIMAK_ORC_GOSMOS_LV45;
	
	@ClientString(id = 1120158, message = "Thief Kelbar (lv44)")
	public static NpcStringId THIEF_KELBAR_LV44;
	
	@ClientString(id = 1120159, message = "Evil Spirit Cyrion (lv45)")
	public static NpcStringId EVIL_SPIRIT_CYRION_LV45;
	
	@ClientString(id = 1120160, message = "Enmity Ghost Ramdal (lv65)")
	public static NpcStringId ENMITY_GHOST_RAMDAL_LV65;
	
	@ClientString(id = 1120161, message = "Immortal Savior Mardil (lv71)")
	public static NpcStringId IMMORTAL_SAVIOR_MARDIL_LV71;
	
	@ClientString(id = 1120162, message = "Cherub Galaxia (lv79)")
	public static NpcStringId CHERUB_GALAXIA_LV79;
	
	@ClientString(id = 1120163, message = "Minas Anor (lv70)")
	public static NpcStringId MINAS_ANOR_LV70;
	
	@ClientString(id = 1120164, message = "Mirror of Oblivion (lv49)")
	public static NpcStringId MIRROR_OF_OBLIVION_LV49;
	
	@ClientString(id = 1120165, message = "Deadman Ereve (lv51)")
	public static NpcStringId DEADMAN_EREVE_LV51;
	
	@ClientString(id = 1120166, message = "Harit Guardian Garangky (lv56)")
	public static NpcStringId HARIT_GUARDIAN_GARANGKY_LV56;
	
	@ClientString(id = 1120167, message = "Gorgolos (lv64)")
	public static NpcStringId GORGOLOS_LV64;
	
	@ClientString(id = 1120168, message = " Utenus, the Last Titan (lv66)")
	public static NpcStringId UTENUS_THE_LAST_TITAN_LV66;
	
	@ClientString(id = 1120169, message = "Grave Robber Kim (lv52)")
	public static NpcStringId GRAVE_ROBBER_KIM_LV52;
	
	@ClientString(id = 1120170, message = "Ghost Knight Kabed (lv55)")
	public static NpcStringId GHOST_KNIGHT_KABED_LV55;
	
	@ClientString(id = 1120171, message = "Hisilrome, Priest of Shilen (lv65)")
	public static NpcStringId HISILROME_PRIEST_OF_SHILEN_LV65;
	
	@ClientString(id = 1120172, message = "Magus Kenishee (lv53)")
	public static NpcStringId MAGUS_KENISHEE_LV53;
	
	@ClientString(id = 1120173, message = "Zaken's Mate Tillion (lv50)")
	public static NpcStringId ZAKEN_S_MATE_TILLION_LV50;
	
	@ClientString(id = 1120174, message = "Water Spirit Lian (lv40)")
	public static NpcStringId WATER_SPIRIT_LIAN_LV40;
	
	@ClientString(id = 1120175, message = "Gwindorr (lv40)")
	public static NpcStringId GWINDORR_LV40;
	
	@ClientString(id = 1120176, message = "Eva's Spirit Niniel (lv55)")
	public static NpcStringId EVA_S_SPIRIT_NINIEL_LV55;
	
	@ClientString(id = 1120177, message = "Fafurion's Envoy Pingolpin (lv52)")
	public static NpcStringId FAFURION_S_ENVOY_PINGOLPIN_LV52;
	
	@ClientString(id = 1120178, message = "Fafurion's Henchman Istary (lv45)")
	public static NpcStringId FAFURION_S_HENCHMAN_ISTARY_LV45;
	
	@ClientString(id = 1120179, message = "Grave Robber Boss Akata (lv30)")
	public static NpcStringId GRAVE_ROBBER_BOSS_AKATA_LV30;
	
	@ClientString(id = 1120180, message = "Nellis' Vengeful Spirit (lv39)")
	public static NpcStringId NELLIS_VENGEFUL_SPIRIT_LV39;
	
	@ClientString(id = 1120181, message = "Rayito the Looter (lv37)")
	public static NpcStringId RAYITO_THE_LOOTER_LV37;
	
	@ClientString(id = 1120182, message = "Dark Shaman Varangka (lv53)")
	public static NpcStringId DARK_SHAMAN_VARANGKA_LV53;
	
	@ClientString(id = 1120183, message = "Gigantic Chaos Golem (lv52)")
	public static NpcStringId GIGANTIC_CHAOS_GOLEM_LV52;
	
	@ClientString(id = 1120184, message = "Captain of the Ice Queen's Royal Guard (lv59)")
	public static NpcStringId CAPTAIN_OF_THE_ICE_QUEEN_S_ROYAL_GUARD_LV59;
	
	@ClientString(id = 1120185, message = "Spiked Stakato Queen Shyeed (lv80)")
	public static NpcStringId SPIKED_STAKATO_QUEEN_SHYEED_LV80;
	
	@ClientString(id = 1120186, message = "Master Anays (lv80)")
	public static NpcStringId MASTER_ANAYS_LV80;
	
	@ClientString(id = 1120187, message = "High Priest Andreas Van Halter (lv80)")
	public static NpcStringId HIGH_PRIEST_ANDREAS_VAN_HALTER_LV80;
	
	@ClientString(id = 1120188, message = "Plague Golem (lv73)")
	public static NpcStringId PLAGUE_GOLEM_LV73;
	
	@ClientString(id = 1120189, message = "Flamestone Giant (lv76)")
	public static NpcStringId FLAMESTONE_GIANT_LV76;
	
	@ClientString(id = 1120190, message = "Lilith (lv80)")
	public static NpcStringId LILITH_LV80;
	
	@ClientString(id = 1120191, message = "Anakim (lv80)")
	public static NpcStringId ANAKIM_LV80;
	
	@ClientString(id = 1120192, message = "Ice Fairy Sirra (lv60)")
	public static NpcStringId ICE_FAIRY_SIRRA_LV60;
	
	@ClientString(id = 1120193, message = "Icicle Emperor Bumbalump (lv74)")
	public static NpcStringId ICICLE_EMPEROR_BUMBALUMP_LV74;
	
	@ClientString(id = 1120194, message = "Daimon the White-Eyed (lv78)")
	public static NpcStringId DAIMON_THE_WHITE_EYED_LV78;
	
	@ClientString(id = 1120195, message = "Soul of Fire Nastron (lv80)")
	public static NpcStringId SOUL_OF_FIRE_NASTRON_LV80;
	
	@ClientString(id = 1120196, message = "Soul of Water Ashutar (lv80)")
	public static NpcStringId SOUL_OF_WATER_ASHUTAR_LV80;
	
	@ClientString(id = 1120197, message = "Uruka (lv80)")
	public static NpcStringId URUKA_LV80;
	
	@ClientString(id = 1120198, message = "Sailren (lv80)")
	public static NpcStringId SAILREN_LV80;
	
	@ClientString(id = 1120199, message = "Typhoon (lv83)")
	public static NpcStringId TYPHOON_LV83;
	
	@ClientString(id = 1120200, message = "Valdstone (lv80)")
	public static NpcStringId VALDSTONE_LV80;
	
	@ClientString(id = 1120201, message = "Rok (lv83)")
	public static NpcStringId ROK_LV83;
	
	@ClientString(id = 1120202, message = "Enira (lv85)")
	public static NpcStringId ENIRA_LV85;
	
	@ClientString(id = 1120203, message = "Dius (lv85)")
	public static NpcStringId DIUS_LV85;
	
	@ClientString(id = 1120204, message = "Yehan Klodekus (lv81)")
	public static NpcStringId YEHAN_KLODEKUS_LV81;
	
	@ClientString(id = 1120205, message = "Yehan Klanikus (lv81)")
	public static NpcStringId YEHAN_KLANIKUS_LV81;
	
	@ClientString(id = 1120206, message = "Cannibalistic Stakato Chief (lv84)")
	public static NpcStringId CANNIBALISTIC_STAKATO_CHIEF_LV84;
	
	@ClientString(id = 1120207, message = "Spike Stakato Queen Shyeed (lv84)")
	public static NpcStringId SPIKE_STAKATO_QUEEN_SHYEED_LV84;
	
	@ClientString(id = 1120208, message = "Gwindorr (lv83)")
	public static NpcStringId GWINDORR_LV83;
	
	@ClientString(id = 1120209, message = "Sprite of Water (lv84)")
	public static NpcStringId SPRITE_OF_WATER_LV84;
	
	@ClientString(id = 1120210, message = "Giant Marpanak (lv82)")
	public static NpcStringId GIANT_MARPANAK_LV82;
	
	@ClientString(id = 1120211, message = "Gorgolos (lv82)")
	public static NpcStringId GORGOLOS_LV82;
	
	@ClientString(id = 1120212, message = "Last Titan Utenus (lv83)")
	public static NpcStringId LAST_TITAN_UTENUS_LV83;
	
	@ClientString(id = 1120213, message = "Hekaton Prime (lv83)")
	public static NpcStringId HEKATON_PRIME_LV83;
	
	@ClientString(id = 1120300, message = "Thank you... My book... Child...")
	public static NpcStringId THANK_YOU_MY_BOOK_CHILD;
	
	@ClientString(id = 1120301, message = "Killed by $s1")
	public static NpcStringId KILLED_BY_S1;
	
	@ClientString(id = 1121000, message = "Steward: Please wait a moment.")
	public static NpcStringId STEWARD_PLEASE_WAIT_A_MOMENT;
	
	@ClientString(id = 1121001, message = "Steward: Please restore the Queen's former appearance!")
	public static NpcStringId STEWARD_PLEASE_RESTORE_THE_QUEEN_S_FORMER_APPEARANCE;
	
	@ClientString(id = 1121002, message = "Steward: Waste no time! Please hurry!")
	public static NpcStringId STEWARD_WASTE_NO_TIME_PLEASE_HURRY;
	
	@ClientString(id = 1121003, message = "Steward: Was it indeed too much to ask...")
	public static NpcStringId STEWARD_WAS_IT_INDEED_TOO_MUCH_TO_ASK;
	
	@ClientString(id = 1121004, message = "Freya: Heathens! Feel my chill!")
	public static NpcStringId FREYA_HEATHENS_FEEL_MY_CHILL;
	
	@ClientString(id = 1121005, message = "Attention please! The gates will be closing shortly. All visitors to the Queen's Castle should leave immediately.")
	public static NpcStringId ATTENTION_PLEASE_THE_GATES_WILL_BE_CLOSING_SHORTLY_ALL_VISITORS_TO_THE_QUEEN_S_CASTLE_SHOULD_LEAVE_IMMEDIATELY;
	
	@ClientString(id = 1121006, message = "You cannot carry a weapon without authorization!")
	public static NpcStringId YOU_CANNOT_CARRY_A_WEAPON_WITHOUT_AUTHORIZATION;
	
	@ClientString(id = 1121007, message = "Are you trying to deceive me? I'm disappointed.")
	public static NpcStringId ARE_YOU_TRYING_TO_DECEIVE_ME_I_M_DISAPPOINTED;
	
	@ClientString(id = 1121008, message = "30 minutes remain.")
	public static NpcStringId THIRTY_MINUTES_REMAIN;
	
	@ClientString(id = 1121009, message = "20 minutes remain.")
	public static NpcStringId TWENTY_MINUTES_REMAIN;
	
	@ClientString(id = 1200001, message = "Chilly Coda")
	public static NpcStringId CHILLY_CODA;
	
	@ClientString(id = 1200002, message = "Burning Coda")
	public static NpcStringId BURNING_CODA;
	
	@ClientString(id = 1200003, message = "Blue Coda")
	public static NpcStringId BLUE_CODA;
	
	@ClientString(id = 1200004, message = "Red Coda")
	public static NpcStringId RED_CODA;
	
	@ClientString(id = 1200005, message = "Golden Coda")
	public static NpcStringId GOLDEN_CODA;
	
	@ClientString(id = 1200006, message = "Desert Coda")
	public static NpcStringId DESERT_CODA;
	
	@ClientString(id = 1200007, message = "Lute Coda")
	public static NpcStringId LUTE_CODA;
	
	@ClientString(id = 1200008, message = "Twin Coda")
	public static NpcStringId TWIN_CODA;
	
	@ClientString(id = 1200009, message = "Dark Coda")
	public static NpcStringId DARK_CODA;
	
	@ClientString(id = 1200010, message = "Shining Coda")
	public static NpcStringId SHINING_CODA;
	
	@ClientString(id = 1200011, message = "Chilly Cobol")
	public static NpcStringId CHILLY_COBOL;
	
	@ClientString(id = 1200012, message = "Burning Cobol")
	public static NpcStringId BURNING_COBOL;
	
	@ClientString(id = 1200013, message = "Blue Cobol")
	public static NpcStringId BLUE_COBOL;
	
	@ClientString(id = 1200014, message = "Red Cobol")
	public static NpcStringId RED_COBOL;
	
	@ClientString(id = 1200015, message = "Golden Cobol")
	public static NpcStringId GOLDEN_COBOL;
	
	@ClientString(id = 1200016, message = "Desert Cobol")
	public static NpcStringId DESERT_COBOL;
	
	@ClientString(id = 1200017, message = "Sea Cobol")
	public static NpcStringId SEA_COBOL;
	
	@ClientString(id = 1200018, message = "Thorn Cobol")
	public static NpcStringId THORN_COBOL;
	
	@ClientString(id = 1200019, message = "Dapple Cobol")
	public static NpcStringId DAPPLE_COBOL;
	
	@ClientString(id = 1200020, message = "Great Cobol")
	public static NpcStringId GREAT_COBOL;
	
	@ClientString(id = 1200021, message = "Chilly Codran")
	public static NpcStringId CHILLY_CODRAN;
	
	@ClientString(id = 1200022, message = "Burning Codran")
	public static NpcStringId BURNING_CODRAN;
	
	@ClientString(id = 1200023, message = "Blue Codran")
	public static NpcStringId BLUE_CODRAN;
	
	@ClientString(id = 1200024, message = "Red Codran")
	public static NpcStringId RED_CODRAN;
	
	@ClientString(id = 1200025, message = "Dapple Codran")
	public static NpcStringId DAPPLE_CODRAN;
	
	@ClientString(id = 1200026, message = "Desert Codran")
	public static NpcStringId DESERT_CODRAN;
	
	@ClientString(id = 1200027, message = "Sea Codran")
	public static NpcStringId SEA_CODRAN;
	
	@ClientString(id = 1200028, message = "Twin Codran")
	public static NpcStringId TWIN_CODRAN;
	
	@ClientString(id = 1200029, message = "Thorn Codran")
	public static NpcStringId THORN_CODRAN;
	
	@ClientString(id = 1200030, message = "Great Codran")
	public static NpcStringId GREAT_CODRAN;
	
	@ClientString(id = 1200031, message = "Alternative Dark Coda")
	public static NpcStringId ALTERNATIVE_DARK_CODA;
	
	@ClientString(id = 1200032, message = "Alternative Red Coda")
	public static NpcStringId ALTERNATIVE_RED_CODA;
	
	@ClientString(id = 1200033, message = "Alternative Chilly Coda")
	public static NpcStringId ALTERNATIVE_CHILLY_CODA;
	
	@ClientString(id = 1200034, message = "Alternative Blue Coda")
	public static NpcStringId ALTERNATIVE_BLUE_CODA;
	
	@ClientString(id = 1200035, message = "Alternative Golden Coda")
	public static NpcStringId ALTERNATIVE_GOLDEN_CODA;
	
	@ClientString(id = 1200036, message = "Alternative Lute Coda")
	public static NpcStringId ALTERNATIVE_LUTE_CODA;
	
	@ClientString(id = 1200037, message = "Alternative Desert Coda")
	public static NpcStringId ALTERNATIVE_DESERT_CODA;
	
	@ClientString(id = 1200038, message = "Alternative Red Cobol")
	public static NpcStringId ALTERNATIVE_RED_COBOL;
	
	@ClientString(id = 1200039, message = "Alternative Chilly Cobol")
	public static NpcStringId ALTERNATIVE_CHILLY_COBOL;
	
	@ClientString(id = 1200040, message = "Alternative Blue Cobol")
	public static NpcStringId ALTERNATIVE_BLUE_COBOL;
	
	@ClientString(id = 1200041, message = "Alternative Thorn Cobol")
	public static NpcStringId ALTERNATIVE_THORN_COBOL;
	
	@ClientString(id = 1200042, message = "Alternative Golden Cobol")
	public static NpcStringId ALTERNATIVE_GOLDEN_COBOL;
	
	@ClientString(id = 1200043, message = "Alternative Great Cobol")
	public static NpcStringId ALTERNATIVE_GREAT_COBOL;
	
	@ClientString(id = 1200044, message = "Alternative Red Codran")
	public static NpcStringId ALTERNATIVE_RED_CODRAN;
	
	@ClientString(id = 1200045, message = "Alternative Sea Codran")
	public static NpcStringId ALTERNATIVE_SEA_CODRAN;
	
	@ClientString(id = 1200046, message = "Alternative Chilly Codran")
	public static NpcStringId ALTERNATIVE_CHILLY_CODRAN;
	
	@ClientString(id = 1200047, message = "Alternative Blue Codran")
	public static NpcStringId ALTERNATIVE_BLUE_CODRAN;
	
	@ClientString(id = 1200048, message = "Alternative Twin Codran")
	public static NpcStringId ALTERNATIVE_TWIN_CODRAN;
	
	@ClientString(id = 1200049, message = "Alternative Great Codran")
	public static NpcStringId ALTERNATIVE_GREAT_CODRAN;
	
	@ClientString(id = 1200050, message = "Alternative Desert Codran")
	public static NpcStringId ALTERNATIVE_DESERT_CODRAN;
	
	@ClientString(id = 1300001, message = "We have broken through the gate! Destroy the encampment and move to the Command Post!")
	public static NpcStringId WE_HAVE_BROKEN_THROUGH_THE_GATE_DESTROY_THE_ENCAMPMENT_AND_MOVE_TO_THE_COMMAND_POST;
	
	@ClientString(id = 1300002, message = "The command gate has opened! Capture the flag quickly and raise it high to proclaim our victory!")
	public static NpcStringId THE_COMMAND_GATE_HAS_OPENED_CAPTURE_THE_FLAG_QUICKLY_AND_RAISE_IT_HIGH_TO_PROCLAIM_OUR_VICTORY;
	
	@ClientString(id = 1300003, message = "The gods have forsaken us... Retreat!!")
	public static NpcStringId THE_GODS_HAVE_FORSAKEN_US_RETREAT;
	
	@ClientString(id = 1300004, message = "You may have broken our arrows, but you will never break our will! Archers, retreat!")
	public static NpcStringId YOU_MAY_HAVE_BROKEN_OUR_ARROWS_BUT_YOU_WILL_NEVER_BREAK_OUR_WILL_ARCHERS_RETREAT;
	
	@ClientString(id = 1300005, message = "At last! The Magic Field that protects the fortress has weakened! Volunteers, stand back!")
	public static NpcStringId AT_LAST_THE_MAGIC_FIELD_THAT_PROTECTS_THE_FORTRESS_HAS_WEAKENED_VOLUNTEERS_STAND_BACK;
	
	@ClientString(id = 1300006, message = "Aiieeee! Command Center! This is guard unit! We need backup right away!")
	public static NpcStringId AIIEEEE_COMMAND_CENTER_THIS_IS_GUARD_UNIT_WE_NEED_BACKUP_RIGHT_AWAY;
	
	@ClientString(id = 1300007, message = "Fortress power disabled.")
	public static NpcStringId FORTRESS_POWER_DISABLED;
	
	@ClientString(id = 1300008, message = "Oh my, what has become of me??? My fame.. my friends.. lost.. all lost...")
	public static NpcStringId OH_MY_WHAT_HAS_BECOME_OF_ME_MY_FAME_MY_FRIENDS_LOST_ALL_LOST;
	
	@ClientString(id = 1300009, message = "Machine No. 1 - Power Off!")
	public static NpcStringId MACHINE_NO_1_POWER_OFF;
	
	@ClientString(id = 1300010, message = "Machine No. 2 - Power Off!")
	public static NpcStringId MACHINE_NO_2_POWER_OFF;
	
	@ClientString(id = 1300011, message = "Machine No. 3 - Power Off!")
	public static NpcStringId MACHINE_NO_3_POWER_OFF;
	
	@ClientString(id = 1300012, message = "Everyone, concentrate your attacks on $s1! Show the enemy your resolve!")
	public static NpcStringId EVERYONE_CONCENTRATE_YOUR_ATTACKS_ON_S1_SHOW_THE_ENEMY_YOUR_RESOLVE;
	
	@ClientString(id = 1300013, message = "Attacking the enemy's reinforcements is necessary. Time to die!")
	public static NpcStringId ATTACKING_THE_ENEMY_S_REINFORCEMENTS_IS_NECESSARY_TIME_TO_DIE;
	
	@ClientString(id = 1300014, message = "Spirit of Fire, unleash your power! Burn the enemy!!")
	public static NpcStringId SPIRIT_OF_FIRE_UNLEASH_YOUR_POWER_BURN_THE_ENEMY;
	
	@ClientString(id = 1300015, message = "Hey, these foes are tougher than they look. I'm going to need some help here.")
	public static NpcStringId HEY_THESE_FOES_ARE_TOUGHER_THAN_THEY_LOOK_I_M_GOING_TO_NEED_SOME_HELP_HERE;
	
	@ClientString(id = 1300016, message = "Do you need my power? You seem to be struggling.")
	public static NpcStringId DO_YOU_NEED_MY_POWER_YOU_SEEM_TO_BE_STRUGGLING;
	
	@ClientString(id = 1300017, message = "I'm rather busy here as well.")
	public static NpcStringId I_M_RATHER_BUSY_HERE_AS_WELL;
	
	@ClientString(id = 1300018, message = "Don't think that it's gonna end like this. Your ambition will soon be destroyed as well.")
	public static NpcStringId DON_T_THINK_THAT_IT_S_GONNA_END_LIKE_THIS_YOUR_AMBITION_WILL_SOON_BE_DESTROYED_AS_WELL;
	
	@ClientString(id = 1300019, message = "You must have been prepared to die!")
	public static NpcStringId YOU_MUST_HAVE_BEEN_PREPARED_TO_DIE;
	
	@ClientString(id = 1300020, message = "I feel so much grief that I can't even take care of myself. There isn't any reason for me to stay here any longer.")
	public static NpcStringId I_FEEL_SO_MUCH_GRIEF_THAT_I_CAN_T_EVEN_TAKE_CARE_OF_MYSELF_THERE_ISN_T_ANY_REASON_FOR_ME_TO_STAY_HERE_ANY_LONGER;
	
	@ClientString(id = 1300101, message = "Shanty Fortress")
	public static NpcStringId SHANTY_FORTRESS;
	
	@ClientString(id = 1300102, message = "Southern Fortress")
	public static NpcStringId SOUTHERN_FORTRESS;
	
	@ClientString(id = 1300103, message = "Hive Fortress")
	public static NpcStringId HIVE_FORTRESS;
	
	@ClientString(id = 1300104, message = "Valley Fortress")
	public static NpcStringId VALLEY_FORTRESS;
	
	@ClientString(id = 1300105, message = "Ivory Fortress")
	public static NpcStringId IVORY_FORTRESS;
	
	@ClientString(id = 1300106, message = "Narsell Fortress")
	public static NpcStringId NARSELL_FORTRESS;
	
	@ClientString(id = 1300107, message = "Basin Fortress")
	public static NpcStringId BASIN_FORTRESS;
	
	@ClientString(id = 1300108, message = "White Sands Fortress")
	public static NpcStringId WHITE_SANDS_FORTRESS;
	
	@ClientString(id = 1300109, message = "Borderland Fortress")
	public static NpcStringId BORDERLAND_FORTRESS;
	
	@ClientString(id = 1300110, message = "Swamp Fortress")
	public static NpcStringId SWAMP_FORTRESS;
	
	@ClientString(id = 1300111, message = "Archaic Fortress")
	public static NpcStringId ARCHAIC_FORTRESS;
	
	@ClientString(id = 1300112, message = "Floran Fortress")
	public static NpcStringId FLORAN_FORTRESS;
	
	@ClientString(id = 1300113, message = "Cloud Mountain Fortress")
	public static NpcStringId CLOUD_MOUNTAIN_FORTRESS;
	
	@ClientString(id = 1300114, message = "Tanor Fortress")
	public static NpcStringId TANOR_FORTRESS;
	
	@ClientString(id = 1300115, message = "Dragonspine Fortress")
	public static NpcStringId DRAGONSPINE_FORTRESS;
	
	@ClientString(id = 1300116, message = "Antharas's Fortress")
	public static NpcStringId ANTHARAS_S_FORTRESS;
	
	@ClientString(id = 1300117, message = "Western Fortress")
	public static NpcStringId WESTERN_FORTRESS;
	
	@ClientString(id = 1300118, message = "Hunter's Fortress")
	public static NpcStringId HUNTER_S_FORTRESS;
	
	@ClientString(id = 1300119, message = "Aaru Fortress")
	public static NpcStringId AARU_FORTRESS;
	
	@ClientString(id = 1300120, message = "Demon Fortress")
	public static NpcStringId DEMON_FORTRESS;
	
	@ClientString(id = 1300121, message = "Monastic Fortress")
	public static NpcStringId MONASTIC_FORTRESS;
	
	@ClientString(id = 1300122, message = "Independent State")
	public static NpcStringId INDEPENDENT_STATE;
	
	@ClientString(id = 1300123, message = "Nonpartisan")
	public static NpcStringId NONPARTISAN;
	
	@ClientString(id = 1300124, message = "Contract State")
	public static NpcStringId CONTRACT_STATE;
	
	@ClientString(id = 1300125, message = "First password has been entered.")
	public static NpcStringId FIRST_PASSWORD_HAS_BEEN_ENTERED;
	
	@ClientString(id = 1300126, message = "Second password has been entered.")
	public static NpcStringId SECOND_PASSWORD_HAS_BEEN_ENTERED;
	
	@ClientString(id = 1300127, message = "Password has not been entered.")
	public static NpcStringId PASSWORD_HAS_NOT_BEEN_ENTERED;
	
	@ClientString(id = 1300128, message = "Attempt $s1 / 3 is in progress. => This is the third attempt on $s1.")
	public static NpcStringId ATTEMPT_S1_3_IS_IN_PROGRESS_THIS_IS_THE_THIRD_ATTEMPT_ON_S1;
	
	@ClientString(id = 1300129, message = "The 1st Mark is correct.")
	public static NpcStringId THE_1ST_MARK_IS_CORRECT;
	
	@ClientString(id = 1300130, message = "The 2nd Mark is correct.")
	public static NpcStringId THE_2ND_MARK_IS_CORRECT;
	
	@ClientString(id = 1300131, message = "The Marks have not been assembled.")
	public static NpcStringId THE_MARKS_HAVE_NOT_BEEN_ASSEMBLED;
	
	@ClientString(id = 1300132, message = "Olympiad class-free team match is going to begin in Arena $s1 in a moment.")
	public static NpcStringId OLYMPIAD_CLASS_FREE_TEAM_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;
	
	@ClientString(id = 1300133, message = "Domain Fortress")
	public static NpcStringId DOMAIN_FORTRESS;
	
	@ClientString(id = 1300134, message = "Boundary Fortress")
	public static NpcStringId BOUNDARY_FORTRESS;
	
	@ClientString(id = 1300135, message = "$s1hour $s2minute")
	public static NpcStringId S1HOUR_S2MINUTE;
	
	@ClientString(id = 1300136, message = "Not designated")
	public static NpcStringId NOT_DESIGNATED;
	
	@ClientString(id = 1300137, message = "Warriors, have you come to help those who are imprisoned here?")
	public static NpcStringId WARRIORS_HAVE_YOU_COME_TO_HELP_THOSE_WHO_ARE_IMPRISONED_HERE;
	
	@ClientString(id = 1300138, message = "Take that, you weakling!")
	public static NpcStringId TAKE_THAT_YOU_WEAKLING;
	
	@ClientString(id = 1300139, message = "Behold my might!")
	public static NpcStringId BEHOLD_MY_MIGHT;
	
	@ClientString(id = 1300140, message = "Your mind is going blank...")
	public static NpcStringId YOUR_MIND_IS_GOING_BLANK;
	
	@ClientString(id = 1300141, message = "Ugh, it hurts down to the bones...")
	public static NpcStringId UGH_IT_HURTS_DOWN_TO_THE_BONES;
	
	@ClientString(id = 1300142, message = "I can't stand it anymore. Aah...")
	public static NpcStringId I_CAN_T_STAND_IT_ANYMORE_AAH;
	
	@ClientString(id = 1300143, message = "Kyaaak!")
	public static NpcStringId KYAAAK;
	
	@ClientString(id = 1300144, message = "Gasp! How can this be?")
	public static NpcStringId GASP_HOW_CAN_THIS_BE;
	
	@ClientString(id = 1300145, message = "I'll rip the flesh from your bones!")
	public static NpcStringId I_LL_RIP_THE_FLESH_FROM_YOUR_BONES;
	
	@ClientString(id = 1300146, message = "You'll flounder in delusion for the rest of your life!")
	public static NpcStringId YOU_LL_FLOUNDER_IN_DELUSION_FOR_THE_REST_OF_YOUR_LIFE;
	
	@ClientString(id = 1300147, message = "There is no escape from this place!")
	public static NpcStringId THERE_IS_NO_ESCAPE_FROM_THIS_PLACE;
	
	@ClientString(id = 1300148, message = "How dare you!")
	public static NpcStringId HOW_DARE_YOU;
	
	@ClientString(id = 1300149, message = "I shall defeat you.")
	public static NpcStringId I_SHALL_DEFEAT_YOU;
	
	@ClientString(id = 1300150, message = "Begin stage 1!")
	public static NpcStringId BEGIN_STAGE_1;
	
	@ClientString(id = 1300151, message = "Begin stage 2!")
	public static NpcStringId BEGIN_STAGE_2;
	
	@ClientString(id = 1300152, message = "Begin stage 3!")
	public static NpcStringId BEGIN_STAGE_3;
	
	@ClientString(id = 1300153, message = "You've done it! We believed in you, warrior. We want to show our sincerity, though it is small. Please give me some of your time.")
	public static NpcStringId YOU_VE_DONE_IT_WE_BELIEVED_IN_YOU_WARRIOR_WE_WANT_TO_SHOW_OUR_SINCERITY_THOUGH_IT_IS_SMALL_PLEASE_GIVE_ME_SOME_OF_YOUR_TIME;
	
	@ClientString(id = 1300154, message = "The Central Stronghold's compressor is working.")
	public static NpcStringId THE_CENTRAL_STRONGHOLD_S_COMPRESSOR_IS_WORKING;
	
	@ClientString(id = 1300155, message = "Stronghold I's compressor is working.")
	public static NpcStringId STRONGHOLD_I_S_COMPRESSOR_IS_WORKING;
	
	@ClientString(id = 1300156, message = "Stronghold II's compressor is working.")
	public static NpcStringId STRONGHOLD_II_S_COMPRESSOR_IS_WORKING;
	
	@ClientString(id = 1300157, message = "Stronghold III's compressor is working.")
	public static NpcStringId STRONGHOLD_III_S_COMPRESSOR_IS_WORKING;
	
	@ClientString(id = 1300158, message = "The Central Stronghold's compressor has been destroyed.")
	public static NpcStringId THE_CENTRAL_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 1300159, message = "Stronghold I's compressor has been destroyed.")
	public static NpcStringId STRONGHOLD_I_S_COMPRESSOR_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 1300160, message = "Stronghold II's compressor has been destroyed.")
	public static NpcStringId STRONGHOLD_II_S_COMPRESSOR_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 1300161, message = "Stronghold III's compressor has been destroyed.")
	public static NpcStringId STRONGHOLD_III_S_COMPRESSOR_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 1300162, message = "What a predicament... my attempts were unsuccessful.")
	public static NpcStringId WHAT_A_PREDICAMENT_MY_ATTEMPTS_WERE_UNSUCCESSFUL;
	
	@ClientString(id = 1300163, message = "Courage! Ambition! Passion! Mercenaries who want to realize their dream of fighting in the territory war, come to me! Fortune and glory are waiting for you!")
	public static NpcStringId COURAGE_AMBITION_PASSION_MERCENARIES_WHO_WANT_TO_REALIZE_THEIR_DREAM_OF_FIGHTING_IN_THE_TERRITORY_WAR_COME_TO_ME_FORTUNE_AND_GLORY_ARE_WAITING_FOR_YOU;
	
	@ClientString(id = 1300164, message = "Do you wish to fight? Are you afraid? No matter how hard you try, you have nowhere to run. But if you face it head on, our mercenary troop will help you out!")
	public static NpcStringId DO_YOU_WISH_TO_FIGHT_ARE_YOU_AFRAID_NO_MATTER_HOW_HARD_YOU_TRY_YOU_HAVE_NOWHERE_TO_RUN_BUT_IF_YOU_FACE_IT_HEAD_ON_OUR_MERCENARY_TROOP_WILL_HELP_YOU_OUT;
	
	@ClientString(id = 1300165, message = "Charge! Charge! Charge!")
	public static NpcStringId CHARGE_CHARGE_CHARGE;
	
	@ClientString(id = 1300166, message = "Olympiad class-free individual match is going to begin in Arena $s1 in a moment.")
	public static NpcStringId OLYMPIAD_CLASS_FREE_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;
	
	@ClientString(id = 1300167, message = "Olympiad class individual match is going to begin in Arena $s1 in a moment.")
	public static NpcStringId OLYMPIAD_CLASS_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;
	
	@ClientString(id = 1600001, message = "Another player is currently being buffed. Please try again in a moment.")
	public static NpcStringId ANOTHER_PLAYER_IS_CURRENTLY_BEING_BUFFED_PLEASE_TRY_AGAIN_IN_A_MOMENT;
	
	@ClientString(id = 1600002, message = "You cannot mount while you are polymorphed.")
	public static NpcStringId YOU_CANNOT_MOUNT_WHILE_YOU_ARE_POLYMORPHED;
	
	@ClientString(id = 1600003, message = "You cannot mount a Wyvern while in battle mode or while mounted on a Strider.")
	public static NpcStringId YOU_CANNOT_MOUNT_A_WYVERN_WHILE_IN_BATTLE_MODE_OR_WHILE_MOUNTED_ON_A_STRIDER;
	
	@ClientString(id = 1600004, message = "Boo-hoo... I hate...")
	public static NpcStringId BOO_HOO_I_HATE;
	
	@ClientString(id = 1600005, message = "See you later.")
	public static NpcStringId SEE_YOU_LATER;
	
	@ClientString(id = 1600006, message = "You've made a great choice.")
	public static NpcStringId YOU_VE_MADE_A_GREAT_CHOICE;
	
	@ClientString(id = 1600007, message = "Thanks! I feel more relaxed…")
	public static NpcStringId THANKS_I_FEEL_MORE_RELAXED;
	
	@ClientString(id = 1600008, message = "Did you see that Firecracker explode?")
	public static NpcStringId DID_YOU_SEE_THAT_FIRECRACKER_EXPLODE;
	
	@ClientString(id = 1600009, message = "I am nothing.")
	public static NpcStringId I_AM_NOTHING;
	
	@ClientString(id = 1600010, message = "I am telling the truth.")
	public static NpcStringId I_AM_TELLING_THE_TRUTH;
	
	@ClientString(id = 1600011, message = "You cannot mount a Wyvern while in battle mode or while mounted on a Strider.")
	public static NpcStringId YOU_CANNOT_MOUNT_A_WYVERN_WHILE_IN_BATTLE_MODE_OR_WHILE_MOUNTED_ON_A_STRIDER_2;
	
	@ClientString(id = 1600012, message = "It's free to go back to the village you teleported from.")
	public static NpcStringId IT_S_FREE_TO_GO_BACK_TO_THE_VILLAGE_YOU_TELEPORTED_FROM;
	
	@ClientString(id = 1600013, message = "If you collect 50 individual Treasure Sack Pieces, you can exchange them for a Treasure Sack.")
	public static NpcStringId IF_YOU_COLLECT_50_INDIVIDUAL_TREASURE_SACK_PIECES_YOU_CAN_EXCHANGE_THEM_FOR_A_TREASURE_SACK;
	
	@ClientString(id = 1600014, message = "You must be transformed into a Treasure Hunter to find a chest.")
	public static NpcStringId YOU_MUST_BE_TRANSFORMED_INTO_A_TREASURE_HUNTER_TO_FIND_A_CHEST;
	
	@ClientString(id = 1600015, message = "You'd better use the Transformation spell at the right moment since it doesn't last long.")
	public static NpcStringId YOU_D_BETTER_USE_THE_TRANSFORMATION_SPELL_AT_THE_RIGHT_MOMENT_SINCE_IT_DOESN_T_LAST_LONG;
	
	@ClientString(id = 1600016, message = "All of Fantasy Isle is a Peace Zone.")
	public static NpcStringId ALL_OF_FANTASY_ISLE_IS_A_PEACE_ZONE;
	
	@ClientString(id = 1600017, message = "If you need to go to Fantasy Isle, come see me.")
	public static NpcStringId IF_YOU_NEED_TO_GO_TO_FANTASY_ISLE_COME_SEE_ME;
	
	@ClientString(id = 1600018, message = "You can only purchase a Treasure Hunter Transformation Scroll once every 12 hours.")
	public static NpcStringId YOU_CAN_ONLY_PURCHASE_A_TREASURE_HUNTER_TRANSFORMATION_SCROLL_ONCE_EVERY_12_HOURS;
	
	@ClientString(id = 1600019, message = "If your means of arrival was a bit unconventional, then I'll be sending you back to Rune Township, which is the nearest town.")
	public static NpcStringId IF_YOUR_MEANS_OF_ARRIVAL_WAS_A_BIT_UNCONVENTIONAL_THEN_I_LL_BE_SENDING_YOU_BACK_TO_RUNE_TOWNSHIP_WHICH_IS_THE_NEAREST_TOWN;
	
	@ClientString(id = 1600020, message = "*Rattle*")
	public static NpcStringId RATTLE;
	
	@ClientString(id = 1600021, message = "*Bump*")
	public static NpcStringId BUMP;
	
	@ClientString(id = 1600022, message = "You will regret this.")
	public static NpcStringId YOU_WILL_REGRET_THIS;
	
	@ClientString(id = 1600023, message = "Care to challenge fate and test your luck?")
	public static NpcStringId CARE_TO_CHALLENGE_FATE_AND_TEST_YOUR_LUCK;
	
	@ClientString(id = 1600024, message = "Don't pass up the chance to win an S80 weapon.")
	public static NpcStringId DON_T_PASS_UP_THE_CHANCE_TO_WIN_AN_S80_WEAPON;
	
	@ClientString(id = 1800001, message = "(Queen Ant) # $s1's Command Channel has looting rights.")
	public static NpcStringId QUEEN_ANT_S1_S_COMMAND_CHANNEL_HAS_LOOTING_RIGHTS;
	
	@ClientString(id = 1800002, message = "(Core) # $s1's Command Channel has looting rights.")
	public static NpcStringId CORE_S1_S_COMMAND_CHANNEL_HAS_LOOTING_RIGHTS;
	
	@ClientString(id = 1800003, message = "(Orphen) # $s1's Command Channel has looting rights.")
	public static NpcStringId ORPHEN_S1_S_COMMAND_CHANNEL_HAS_LOOTING_RIGHTS;
	
	@ClientString(id = 1800004, message = "(Zaken) # $s1's Command Channel has looting rights.")
	public static NpcStringId ZAKEN_S1_S_COMMAND_CHANNEL_HAS_LOOTING_RIGHTS;
	
	@ClientString(id = 1800005, message = "(Queen Ant) Looting rules are no longer active.")
	public static NpcStringId QUEEN_ANT_LOOTING_RULES_ARE_NO_LONGER_ACTIVE;
	
	@ClientString(id = 1800006, message = "(Core) Looting rules are no longer active.")
	public static NpcStringId CORE_LOOTING_RULES_ARE_NO_LONGER_ACTIVE;
	
	@ClientString(id = 1800007, message = "(Orphen) Looting rules are no longer active.")
	public static NpcStringId ORPHEN_LOOTING_RULES_ARE_NO_LONGER_ACTIVE;
	
	@ClientString(id = 1800008, message = "(Zaken) Looting rules are no longer active.")
	public static NpcStringId ZAKEN_LOOTING_RULES_ARE_NO_LONGER_ACTIVE;
	
	@ClientString(id = 1800009, message = "# $s1's Command Channel has looting rights.")
	public static NpcStringId S1_S_COMMAND_CHANNEL_HAS_LOOTING_RIGHTS;
	
	@ClientString(id = 1800010, message = "Looting rules are no longer active.")
	public static NpcStringId LOOTING_RULES_ARE_NO_LONGER_ACTIVE;
	
	@ClientString(id = 1800011, message = "Our master now comes to claim our vengeance. Soon you will all be nothing more than dirt...")
	public static NpcStringId OUR_MASTER_NOW_COMES_TO_CLAIM_OUR_VENGEANCE_SOON_YOU_WILL_ALL_BE_NOTHING_MORE_THAN_DIRT;
	
	@ClientString(id = 1800012, message = "Death to those who challenge the Lords of Dawn!")
	public static NpcStringId DEATH_TO_THOSE_WHO_CHALLENGE_THE_LORDS_OF_DAWN;
	
	@ClientString(id = 1800013, message = "Death to those who challenge the Lord!")
	public static NpcStringId DEATH_TO_THOSE_WHO_CHALLENGE_THE_LORD;
	
	@ClientString(id = 1800014, message = "Oink oink! Pigs can do it too! Antharas-Surpassing Super Powered Pig Stun! How do like them apples?")
	public static NpcStringId OINK_OINK_PIGS_CAN_DO_IT_TOO_ANTHARAS_SURPASSING_SUPER_POWERED_PIG_STUN_HOW_DO_LIKE_THEM_APPLES;
	
	@ClientString(id = 1800015, message = "Oink oink! Take that! Valakas-Terrorizing Ultra Pig Fear! Ha ha!")
	public static NpcStringId OINK_OINK_TAKE_THAT_VALAKAS_TERRORIZING_ULTRA_PIG_FEAR_HA_HA;
	
	@ClientString(id = 1800016, message = "Oink oink! Go away! Stop bothering me!")
	public static NpcStringId OINK_OINK_GO_AWAY_STOP_BOTHERING_ME;
	
	@ClientString(id = 1800017, message = "Oink oink! Pigs of the world unite! Let's show them our strength!")
	public static NpcStringId OINK_OINK_PIGS_OF_THE_WORLD_UNITE_LET_S_SHOW_THEM_OUR_STRENGTH;
	
	@ClientString(id = 1800018, message = "You healed me. Thanks a lot! Oink oink!")
	public static NpcStringId YOU_HEALED_ME_THANKS_A_LOT_OINK_OINK;
	
	@ClientString(id = 1800019, message = "Oink oink! This treatment hurts too much! Are you sure that you're trying to heal me?")
	public static NpcStringId OINK_OINK_THIS_TREATMENT_HURTS_TOO_MUCH_ARE_YOU_SURE_THAT_YOU_RE_TRYING_TO_HEAL_ME;
	
	@ClientString(id = 1800020, message = "Oink oink! Transform with Moon Crystal Prism Power!")
	public static NpcStringId OINK_OINK_TRANSFORM_WITH_MOON_CRYSTAL_PRISM_POWER;
	
	@ClientString(id = 1800021, message = "Oink oink! Nooo! I don't want to go back to normal!")
	public static NpcStringId OINK_OINK_NOOO_I_DON_T_WANT_TO_GO_BACK_TO_NORMAL;
	
	@ClientString(id = 1800022, message = "Oink oink! I'm rich, so I've got plenty to share! Thanks!")
	public static NpcStringId OINK_OINK_I_M_RICH_SO_I_VE_GOT_PLENTY_TO_SHARE_THANKS;
	
	@ClientString(id = 1800023, message = "It's a weapon surrounded by an ominous aura. I'll discard it because it may be dangerous. Miss...!")
	public static NpcStringId IT_S_A_WEAPON_SURROUNDED_BY_AN_OMINOUS_AURA_I_LL_DISCARD_IT_BECAUSE_IT_MAY_BE_DANGEROUS_MISS;
	
	@ClientString(id = 1800024, message = "Thank you for saving me from the clutches of evil!")
	public static NpcStringId THANK_YOU_FOR_SAVING_ME_FROM_THE_CLUTCHES_OF_EVIL;
	
	@ClientString(id = 1800025, message = "That is it for today...let's retreat. Everyone pull back!")
	public static NpcStringId THAT_IS_IT_FOR_TODAY_LET_S_RETREAT_EVERYONE_PULL_BACK;
	
	@ClientString(id = 1800026, message = "Thank you for the rescue. It's a small gift.")
	public static NpcStringId THANK_YOU_FOR_THE_RESCUE_IT_S_A_SMALL_GIFT;
	
	@ClientString(id = 1800027, message = "$s1.. You don't have a Red Crystal...")
	public static NpcStringId S1_YOU_DON_T_HAVE_A_RED_CRYSTAL;
	
	@ClientString(id = 1800028, message = "$s1.. You don't have a Blue Crystal...")
	public static NpcStringId S1_YOU_DON_T_HAVE_A_BLUE_CRYSTAL;
	
	@ClientString(id = 1800029, message = "$s1.. You don't have a Clear Crystal...")
	public static NpcStringId S1_YOU_DON_T_HAVE_A_CLEAR_CRYSTAL;
	
	@ClientString(id = 1800030, message = "$s1.. If you are too far away from me...I can't let you go...")
	public static NpcStringId S1_IF_YOU_ARE_TOO_FAR_AWAY_FROM_ME_I_CAN_T_LET_YOU_GO;
	
	@ClientString(id = 1800031, message = "An alarm has been set off! Everybody will be in danger if they are not taken care of immediately!")
	public static NpcStringId AN_ALARM_HAS_BEEN_SET_OFF_EVERYBODY_WILL_BE_IN_DANGER_IF_THEY_ARE_NOT_TAKEN_CARE_OF_IMMEDIATELY;
	
	@ClientString(id = 1800032, message = "It will not be that easy to kill me!")
	public static NpcStringId IT_WILL_NOT_BE_THAT_EASY_TO_KILL_ME;
	
	@ClientString(id = 1800033, message = "No...You knew my weakness...")
	public static NpcStringId NO_YOU_KNEW_MY_WEAKNESS;
	
	@ClientString(id = 1800034, message = "Hello? Is anyone there?")
	public static NpcStringId HELLO_IS_ANYONE_THERE;
	
	@ClientString(id = 1800035, message = "Is no one there? How long have I been hiding? I have been starving for days and cannot hold out anymore!")
	public static NpcStringId IS_NO_ONE_THERE_HOW_LONG_HAVE_I_BEEN_HIDING_I_HAVE_BEEN_STARVING_FOR_DAYS_AND_CANNOT_HOLD_OUT_ANYMORE;
	
	@ClientString(id = 1800036, message = "If someone would give me some of those tasty Crystal Fragments, I would gladly tell them where Tears is hiding! Yummy, yummy!")
	public static NpcStringId IF_SOMEONE_WOULD_GIVE_ME_SOME_OF_THOSE_TASTY_CRYSTAL_FRAGMENTS_I_WOULD_GLADLY_TELL_THEM_WHERE_TEARS_IS_HIDING_YUMMY_YUMMY;
	
	@ClientString(id = 1800037, message = "Hey! You from above the ground! Let's share some Crystal Fragments, if you have any.")
	public static NpcStringId HEY_YOU_FROM_ABOVE_THE_GROUND_LET_S_SHARE_SOME_CRYSTAL_FRAGMENTS_IF_YOU_HAVE_ANY;
	
	@ClientString(id = 1800038, message = "Crispy and cold feeling! Teehee! Delicious!!")
	public static NpcStringId CRISPY_AND_COLD_FEELING_TEEHEE_DELICIOUS;
	
	@ClientString(id = 1800039, message = "Yummy! This is so tasty.")
	public static NpcStringId YUMMY_THIS_IS_SO_TASTY;
	
	@ClientString(id = 1800040, message = "Sniff, sniff! Give me more Crystal Fragments!")
	public static NpcStringId SNIFF_SNIFF_GIVE_ME_MORE_CRYSTAL_FRAGMENTS;
	
	@ClientString(id = 1800041, message = "How insensitive! It's not nice to give me just a piece! Can't you give me more?")
	public static NpcStringId HOW_INSENSITIVE_IT_S_NOT_NICE_TO_GIVE_ME_JUST_A_PIECE_CAN_T_YOU_GIVE_ME_MORE;
	
	@ClientString(id = 1800042, message = "Ah - I'm hungry!")
	public static NpcStringId AH_I_M_HUNGRY;
	
	@ClientString(id = 1800043, message = "I'm the real one!")
	public static NpcStringId I_M_THE_REAL_ONE;
	
	@ClientString(id = 1800044, message = "Pick me!")
	public static NpcStringId PICK_ME;
	
	@ClientString(id = 1800045, message = "Trust me!")
	public static NpcStringId TRUST_ME;
	
	@ClientString(id = 1800046, message = "Not that dude, I'm the real one!")
	public static NpcStringId NOT_THAT_DUDE_I_M_THE_REAL_ONE;
	
	@ClientString(id = 1800047, message = "Don't be fooled! Don't be fooled! I'm the real one!!")
	public static NpcStringId DON_T_BE_FOOLED_DON_T_BE_FOOLED_I_M_THE_REAL_ONE;
	
	@ClientString(id = 1800048, message = "Just act like the real one! Oops!")
	public static NpcStringId JUST_ACT_LIKE_THE_REAL_ONE_OOPS;
	
	@ClientString(id = 1800049, message = "You've been fooled!")
	public static NpcStringId YOU_VE_BEEN_FOOLED;
	
	@ClientString(id = 1800050, message = "Sorry, but...I'm the fake one.")
	public static NpcStringId SORRY_BUT_I_M_THE_FAKE_ONE;
	
	@ClientString(id = 1800051, message = "I'm the real one! Phew!!")
	public static NpcStringId I_M_THE_REAL_ONE_PHEW;
	
	@ClientString(id = 1800052, message = "Can't you even find out?")
	public static NpcStringId CAN_T_YOU_EVEN_FIND_OUT;
	
	@ClientString(id = 1800053, message = "Find me!")
	public static NpcStringId FIND_ME;
	
	@ClientString(id = 1800054, message = "Huh?! How did you know it was me?")
	public static NpcStringId HUH_HOW_DID_YOU_KNOW_IT_WAS_ME;
	
	@ClientString(id = 1800055, message = "Excellent choice! Teehee!")
	public static NpcStringId EXCELLENT_CHOICE_TEEHEE;
	
	@ClientString(id = 1800056, message = "You've done well!")
	public static NpcStringId YOU_VE_DONE_WELL;
	
	@ClientString(id = 1800057, message = "Oh... very sensible?")
	public static NpcStringId OH_VERY_SENSIBLE;
	
	@ClientString(id = 1800058, message = "Behold the mighty power of Baylor, foolish mortal!")
	public static NpcStringId BEHOLD_THE_MIGHTY_POWER_OF_BAYLOR_FOOLISH_MORTAL;
	
	@ClientString(id = 1800059, message = "No one is going to survive!")
	public static NpcStringId NO_ONE_IS_GOING_TO_SURVIVE;
	
	@ClientString(id = 1800060, message = "You'll see what hell is like...")
	public static NpcStringId YOU_LL_SEE_WHAT_HELL_IS_LIKE;
	
	@ClientString(id = 1800061, message = "You will be put in jail!")
	public static NpcStringId YOU_WILL_BE_PUT_IN_JAIL;
	
	@ClientString(id = 1800062, message = "Worthless creature... Go to hell!")
	public static NpcStringId WORTHLESS_CREATURE_GO_TO_HELL;
	
	@ClientString(id = 1800063, message = "I'll give you something that you'll never forget!")
	public static NpcStringId I_LL_GIVE_YOU_SOMETHING_THAT_YOU_LL_NEVER_FORGET;
	
	@ClientString(id = 1800064, message = "Why did you trust to choose me? Hahahaha....")
	public static NpcStringId WHY_DID_YOU_TRUST_TO_CHOOSE_ME_HAHAHAHA;
	
	@ClientString(id = 1800065, message = "I'll make you regret that you ever chose me...")
	public static NpcStringId I_LL_MAKE_YOU_REGRET_THAT_YOU_EVER_CHOSE_ME;
	
	@ClientString(id = 1800066, message = "Don't expect to get out alive..")
	public static NpcStringId DON_T_EXPECT_TO_GET_OUT_ALIVE;
	
	@ClientString(id = 1800067, message = "Demon King Beleth, give me the power! Aaahh!!!")
	public static NpcStringId DEMON_KING_BELETH_GIVE_ME_THE_POWER_AAAHH;
	
	@ClientString(id = 1800068, message = "No....... I feel the power of Fafurion.......")
	public static NpcStringId NO_I_FEEL_THE_POWER_OF_FAFURION;
	
	@ClientString(id = 1800069, message = "Fafurion! Please give power to this helpless witch!!")
	public static NpcStringId FAFURION_PLEASE_GIVE_POWER_TO_THIS_HELPLESS_WITCH;
	
	@ClientString(id = 1800070, message = "I can't help you much, but I can weaken the power of Baylor since I'm locked up here.")
	public static NpcStringId I_CAN_T_HELP_YOU_MUCH_BUT_I_CAN_WEAKEN_THE_POWER_OF_BAYLOR_SINCE_I_M_LOCKED_UP_HERE;
	
	@ClientString(id = 1800071, message = "Your skill is impressive. I'll admit that you are good enough to pass. Take the key and leave this place.")
	public static NpcStringId YOUR_SKILL_IS_IMPRESSIVE_I_LL_ADMIT_THAT_YOU_ARE_GOOD_ENOUGH_TO_PASS_TAKE_THE_KEY_AND_LEAVE_THIS_PLACE;
	
	@ClientString(id = 1800072, message = "Give me all you have! It’s the only way I'll let you live!!")
	public static NpcStringId GIVE_ME_ALL_YOU_HAVE_IT_S_THE_ONLY_WAY_I_LL_LET_YOU_LIVE;
	
	@ClientString(id = 1800073, message = "Hun.. hungry")
	public static NpcStringId HUN_HUNGRY;
	
	@ClientString(id = 1800074, message = "Don't be lazy! You bastards!")
	public static NpcStringId DON_T_BE_LAZY_YOU_BASTARDS;
	
	@ClientString(id = 1800075, message = "They are just henchmen of the Iron Castle... Why did we hide?")
	public static NpcStringId THEY_ARE_JUST_HENCHMEN_OF_THE_IRON_CASTLE_WHY_DID_WE_HIDE;
	
	@ClientString(id = 1800076, message = "Guys, show them our power!!!!")
	public static NpcStringId GUYS_SHOW_THEM_OUR_POWER;
	
	@ClientString(id = 1800077, message = "You have finally come here. But you will not be able to find the secret room!")
	public static NpcStringId YOU_HAVE_FINALLY_COME_HERE_BUT_YOU_WILL_NOT_BE_ABLE_TO_FIND_THE_SECRET_ROOM;
	
	@ClientString(id = 1800078, message = "You have done well in finding me, but I cannot just hand you the key!")
	public static NpcStringId YOU_HAVE_DONE_WELL_IN_FINDING_ME_BUT_I_CANNOT_JUST_HAND_YOU_THE_KEY;
	
	@ClientString(id = 1800079, message = "$s1 second(s) remaining.")
	public static NpcStringId S1_SECOND_S_REMAINING;
	
	@ClientString(id = 1800080, message = "Match begins in $s1 minute(s). Please gather around the administrator.")
	public static NpcStringId MATCH_BEGINS_IN_S1_MINUTE_S_PLEASE_GATHER_AROUND_THE_ADMINISTRATOR;
	
	@ClientString(id = 1800081, message = "The match is automatically canceled because you are too far from the admission manager.")
	public static NpcStringId THE_MATCH_IS_AUTOMATICALLY_CANCELED_BECAUSE_YOU_ARE_TOO_FAR_FROM_THE_ADMISSION_MANAGER;
	
	@ClientString(id = 1800082, message = "Ugh, I have butterflies in my stomach.. The show starts soon...")
	public static NpcStringId UGH_I_HAVE_BUTTERFLIES_IN_MY_STOMACH_THE_SHOW_STARTS_SOON;
	
	@ClientString(id = 1800083, message = "Thank you all for coming here tonight.")
	public static NpcStringId THANK_YOU_ALL_FOR_COMING_HERE_TONIGHT;
	
	@ClientString(id = 1800084, message = "It is an honor to have the special show today.")
	public static NpcStringId IT_IS_AN_HONOR_TO_HAVE_THE_SPECIAL_SHOW_TODAY;
	
	@ClientString(id = 1800085, message = "Fantasy Isle is fully committed to your happiness.")
	public static NpcStringId FANTASY_ISLE_IS_FULLY_COMMITTED_TO_YOUR_HAPPINESS;
	
	@ClientString(id = 1800086, message = "Now I'd like to introduce the most beautiful singer in Aden. Please welcome...Leyla Mira!")
	public static NpcStringId NOW_I_D_LIKE_TO_INTRODUCE_THE_MOST_BEAUTIFUL_SINGER_IN_ADEN_PLEASE_WELCOME_LEYLA_MIRA;
	
	@ClientString(id = 1800087, message = "Here she comes!")
	public static NpcStringId HERE_SHE_COMES;
	
	@ClientString(id = 1800088, message = "Thank you very much, Leyla!")
	public static NpcStringId THANK_YOU_VERY_MUCH_LEYLA;
	
	@ClientString(id = 1800089, message = "Now we're in for a real treat.")
	public static NpcStringId NOW_WE_RE_IN_FOR_A_REAL_TREAT;
	
	@ClientString(id = 1800090, message = "Just back from their world tour…put your hands together for the Fantasy Isle Circus!")
	public static NpcStringId JUST_BACK_FROM_THEIR_WORLD_TOUR_PUT_YOUR_HANDS_TOGETHER_FOR_THE_FANTASY_ISLE_CIRCUS;
	
	@ClientString(id = 1800091, message = "Come on ~ everyone")
	public static NpcStringId COME_ON_EVERYONE;
	
	@ClientString(id = 1800092, message = "Did you like it? That was so amazing.")
	public static NpcStringId DID_YOU_LIKE_IT_THAT_WAS_SO_AMAZING;
	
	@ClientString(id = 1800093, message = "Now we also invited individuals with special talents.")
	public static NpcStringId NOW_WE_ALSO_INVITED_INDIVIDUALS_WITH_SPECIAL_TALENTS;
	
	@ClientString(id = 1800094, message = "Let's welcome the first person here!")
	public static NpcStringId LET_S_WELCOME_THE_FIRST_PERSON_HERE;
	
	@ClientString(id = 1800095, message = ";;;;;;Oh")
	public static NpcStringId OH;
	
	@ClientString(id = 1800096, message = "Okay, now here comes the next person. Come on up please.")
	public static NpcStringId OKAY_NOW_HERE_COMES_THE_NEXT_PERSON_COME_ON_UP_PLEASE;
	
	@ClientString(id = 1800097, message = "Oh, it looks like something great is going to happen, right?")
	public static NpcStringId OH_IT_LOOKS_LIKE_SOMETHING_GREAT_IS_GOING_TO_HAPPEN_RIGHT;
	
	@ClientString(id = 1800098, message = "Oh, my ;;;;")
	public static NpcStringId OH_MY;
	
	@ClientString(id = 1800099, message = "That's g- .. great. Now, here comes the last person.")
	public static NpcStringId THAT_S_G_GREAT_NOW_HERE_COMES_THE_LAST_PERSON;
	
	@ClientString(id = 1800100, message = "Now this is the end of today's show.")
	public static NpcStringId NOW_THIS_IS_THE_END_OF_TODAY_S_SHOW;
	
	@ClientString(id = 1800101, message = "How was it? I hope you all enjoyed it.")
	public static NpcStringId HOW_WAS_IT_I_HOPE_YOU_ALL_ENJOYED_IT;
	
	@ClientString(id = 1800102, message = "Please remember that Fantasy Isle is always planning a lot of great shows for you.")
	public static NpcStringId PLEASE_REMEMBER_THAT_FANTASY_ISLE_IS_ALWAYS_PLANNING_A_LOT_OF_GREAT_SHOWS_FOR_YOU;
	
	@ClientString(id = 1800103, message = "Well, I wish I could continue all night long, but this is it for today. Thank you.")
	public static NpcStringId WELL_I_WISH_I_COULD_CONTINUE_ALL_NIGHT_LONG_BUT_THIS_IS_IT_FOR_TODAY_THANK_YOU;
	
	@ClientString(id = 1800104, message = "We love you.")
	public static NpcStringId WE_LOVE_YOU;
	
	@ClientString(id = 1800105, message = "How come people are not here... We are about to start the show.. Hmm")
	public static NpcStringId HOW_COME_PEOPLE_ARE_NOT_HERE_WE_ARE_ABOUT_TO_START_THE_SHOW_HMM;
	
	@ClientString(id = 1800106, message = "The opponent team canceled the match.")
	public static NpcStringId THE_OPPONENT_TEAM_CANCELED_THE_MATCH;
	
	@ClientString(id = 1800107, message = "It's not easy to obtain.")
	public static NpcStringId IT_S_NOT_EASY_TO_OBTAIN;
	
	@ClientString(id = 1800108, message = "You're out of your mind coming here...")
	public static NpcStringId YOU_RE_OUT_OF_YOUR_MIND_COMING_HERE;
	
	@ClientString(id = 1800109, message = "Enemy invasion! Hurry up!")
	public static NpcStringId ENEMY_INVASION_HURRY_UP;
	
	@ClientString(id = 1800110, message = "Process... shouldn't... be delayed... because of me...")
	public static NpcStringId PROCESS_SHOULDN_T_BE_DELAYED_BECAUSE_OF_ME;
	
	@ClientString(id = 1800111, message = "Alright, now Leodas is yours!")
	public static NpcStringId ALRIGHT_NOW_LEODAS_IS_YOURS;
	
	@ClientString(id = 1800112, message = "We might need new slaves... I'll be back soon, so wait!")
	public static NpcStringId WE_MIGHT_NEED_NEW_SLAVES_I_LL_BE_BACK_SOON_SO_WAIT;
	
	@ClientString(id = 1800113, message = "Time rift device activation successful!")
	public static NpcStringId TIME_RIFT_DEVICE_ACTIVATION_SUCCESSFUL;
	
	@ClientString(id = 1800114, message = "Freedom or death!!!")
	public static NpcStringId FREEDOM_OR_DEATH;
	
	@ClientString(id = 1800115, message = "This is the will of true warriors!!!")
	public static NpcStringId THIS_IS_THE_WILL_OF_TRUE_WARRIORS;
	
	@ClientString(id = 1800116, message = "We'll have dinner in hell!!!")
	public static NpcStringId WE_LL_HAVE_DINNER_IN_HELL;
	
	@ClientString(id = 1800117, message = "Detonator initialization- time set for $s1 minute(s) from now-")
	public static NpcStringId DETONATOR_INITIALIZATION_TIME_SET_FOR_S1_MINUTE_S_FROM_NOW;
	
	@ClientString(id = 1800118, message = "Zzzz- city interference error - forward effect created-")
	public static NpcStringId ZZZZ_CITY_INTERFERENCE_ERROR_FORWARD_EFFECT_CREATED;
	
	@ClientString(id = 1800119, message = "Zzzz- city interference error - recurrence effect created-")
	public static NpcStringId ZZZZ_CITY_INTERFERENCE_ERROR_RECURRENCE_EFFECT_CREATED;
	
	@ClientString(id = 1800120, message = "Guards are coming, run!")
	public static NpcStringId GUARDS_ARE_COMING_RUN;
	
	@ClientString(id = 1800121, message = "Now I can escape on my own!")
	public static NpcStringId NOW_I_CAN_ESCAPE_ON_MY_OWN;
	
	@ClientString(id = 1800122, message = "Thanks for your help!")
	public static NpcStringId THANKS_FOR_YOUR_HELP;
	
	@ClientString(id = 1800123, message = "Match cancelled. Opponent did not meet the stadium admission requirements.")
	public static NpcStringId MATCH_CANCELLED_OPPONENT_DID_NOT_MEET_THE_STADIUM_ADMISSION_REQUIREMENTS;
	
	@ClientString(id = 1800124, message = "Ha-ha yes, die slowly writhing in pain and agony!")
	public static NpcStringId HA_HA_YES_DIE_SLOWLY_WRITHING_IN_PAIN_AND_AGONY;
	
	@ClientString(id = 1800125, message = "More... need more... severe pain...")
	public static NpcStringId MORE_NEED_MORE_SEVERE_PAIN;
	
	@ClientString(id = 1800126, message = "Ahh! My life is being drained out!")
	public static NpcStringId AHH_MY_LIFE_IS_BEING_DRAINED_OUT;
	
	@ClientString(id = 1800127, message = "Something is burning inside my body!")
	public static NpcStringId SOMETHING_IS_BURNING_INSIDE_MY_BODY;
	
	@ClientString(id = 1800128, message = "$s1. Not adequate for the stadium level.")
	public static NpcStringId S1_NOT_ADEQUATE_FOR_THE_STADIUM_LEVEL;
	
	@ClientString(id = 1800129, message = "$s1, thank you... for giving me your life...")
	public static NpcStringId S1_THANK_YOU_FOR_GIVING_ME_YOUR_LIFE;
	
	@ClientString(id = 1800130, message = "I failed... Please forgive me, Darion...")
	public static NpcStringId I_FAILED_PLEASE_FORGIVE_ME_DARION;
	
	@ClientString(id = 1800131, message = "$s1, I'll be back... don't get comfortable...")
	public static NpcStringId S1_I_LL_BE_BACK_DON_T_GET_COMFORTABLE;
	
	@ClientString(id = 1800132, message = "If you think I'm giving up like this.. You're wrong!!")
	public static NpcStringId IF_YOU_THINK_I_M_GIVING_UP_LIKE_THIS_YOU_RE_WRONG;
	
	@ClientString(id = 1800133, message = "So you're just going to watch me suffer?!")
	public static NpcStringId SO_YOU_RE_JUST_GOING_TO_WATCH_ME_SUFFER;
	
	@ClientString(id = 1800134, message = "It's not over yet!!")
	public static NpcStringId IT_S_NOT_OVER_YET;
	
	@ClientString(id = 1800135, message = "HA-HA! You were so afraid of death... let me see... If you find me in time... maybe you can... find a way ...")
	public static NpcStringId HA_HA_YOU_WERE_SO_AFRAID_OF_DEATH_LET_ME_SEE_IF_YOU_FIND_ME_IN_TIME_MAYBE_YOU_CAN_FIND_A_WAY;
	
	@ClientString(id = 1800136, message = "Don't kill me please.. Something's strangling me...")
	public static NpcStringId DON_T_KILL_ME_PLEASE_SOMETHING_S_STRANGLING_ME;
	
	@ClientString(id = 1800137, message = "Who will be the lucky one tonight? Ha-ha! Curious, very curious!")
	public static NpcStringId WHO_WILL_BE_THE_LUCKY_ONE_TONIGHT_HA_HA_CURIOUS_VERY_CURIOUS;
	
	@ClientString(id = 1800138, message = "Squeak! This will be stronger than the stun the pig used last time!")
	public static NpcStringId SQUEAK_THIS_WILL_BE_STRONGER_THAN_THE_STUN_THE_PIG_USED_LAST_TIME;
	
	@ClientString(id = 1800139, message = "Squeak! Here it goes! Extremely scary, even to Valakas!")
	public static NpcStringId SQUEAK_HERE_IT_GOES_EXTREMELY_SCARY_EVEN_TO_VALAKAS;
	
	@ClientString(id = 1800140, message = "Squeak! Go away! Leave us alone!")
	public static NpcStringId SQUEAK_GO_AWAY_LEAVE_US_ALONE;
	
	@ClientString(id = 1800141, message = "Squeak! Guys, gather up! Let's show our power!")
	public static NpcStringId SQUEAK_GUYS_GATHER_UP_LET_S_SHOW_OUR_POWER;
	
	@ClientString(id = 1800142, message = "It's not like I'm giving this because I'm grateful~ Squeak!")
	public static NpcStringId IT_S_NOT_LIKE_I_M_GIVING_THIS_BECAUSE_I_M_GRATEFUL_SQUEAK;
	
	@ClientString(id = 1800143, message = "Squeak! Even if it is treatment, my bottom hurts so much!")
	public static NpcStringId SQUEAK_EVEN_IF_IT_IS_TREATMENT_MY_BOTTOM_HURTS_SO_MUCH;
	
	@ClientString(id = 1800144, message = "Squeak! Transform to Moon Crystal Prism Power!")
	public static NpcStringId SQUEAK_TRANSFORM_TO_MOON_CRYSTAL_PRISM_POWER;
	
	@ClientString(id = 1800145, message = "Squeak! Oh, no! I don't want to turn back again...")
	public static NpcStringId SQUEAK_OH_NO_I_DON_T_WANT_TO_TURN_BACK_AGAIN;
	
	@ClientString(id = 1800146, message = "Squeak! I'm specially giving you a lot since I'm rich. Thank you")
	public static NpcStringId SQUEAK_I_M_SPECIALLY_GIVING_YOU_A_LOT_SINCE_I_M_RICH_THANK_YOU;
	
	@ClientString(id = 1800147, message = "Oink-oink! Rage is boiling up inside of me! Power! Infinite power!!")
	public static NpcStringId OINK_OINK_RAGE_IS_BOILING_UP_INSIDE_OF_ME_POWER_INFINITE_POWER;
	
	@ClientString(id = 1800148, message = "Oink-oink! I'm really furious right now!")
	public static NpcStringId OINK_OINK_I_M_REALLY_FURIOUS_RIGHT_NOW;
	
	@ClientString(id = 1800149, message = "Squeak! Rage is boiling up inside of me! Power! Infinite power!!")
	public static NpcStringId SQUEAK_RAGE_IS_BOILING_UP_INSIDE_OF_ME_POWER_INFINITE_POWER;
	
	@ClientString(id = 1800150, message = "Squeak! I'm really furious right now!!")
	public static NpcStringId SQUEAK_I_M_REALLY_FURIOUS_RIGHT_NOW;
	
	@ClientString(id = 1800151, message = "Hall of Nightmares (Levels 20-30)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_20_30;
	
	@ClientString(id = 1800152, message = "Hall of Nightmares (Levels 25-35)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_25_35;
	
	@ClientString(id = 1800153, message = "Hall of Nightmares (Levels 30-40)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_30_40;
	
	@ClientString(id = 1800154, message = "Hall of Nightmares (Levels 35-45)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_35_45;
	
	@ClientString(id = 1800155, message = "Hall of Nightmares (Levels 40-50)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_40_50;
	
	@ClientString(id = 1800156, message = "Hall of Nightmares (Levels 45-55)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_45_55;
	
	@ClientString(id = 1800157, message = "Hall of Nightmares (Levels 50-60)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_50_60;
	
	@ClientString(id = 1800158, message = "Hall of Nightmares (Levels 55-65)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_55_65;
	
	@ClientString(id = 1800159, message = "Hall of Nightmares (Levels 60-70)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_60_70;
	
	@ClientString(id = 1800160, message = "Hall of Nightmares (Levels 65-75)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_65_75;
	
	@ClientString(id = 1800161, message = "Hall of Nightmares (Levels 70-80)")
	public static NpcStringId HALL_OF_NIGHTMARES_LEVELS_70_80;
	
	@ClientString(id = 1800162, message = "G Rank")
	public static NpcStringId G_RANK;
	
	@ClientString(id = 1800163, message = "Huh... No one would have guessed that a doomed creature would be so powerful...")
	public static NpcStringId HUH_NO_ONE_WOULD_HAVE_GUESSED_THAT_A_DOOMED_CREATURE_WOULD_BE_SO_POWERFUL;
	
	@ClientString(id = 1800164, message = "S-Grade")
	public static NpcStringId S_GRADE;
	
	@ClientString(id = 1800165, message = "A-Grade")
	public static NpcStringId A_GRADE;
	
	@ClientString(id = 1800166, message = "B-Grade")
	public static NpcStringId B_GRADE;
	
	@ClientString(id = 1800167, message = "C-Grade")
	public static NpcStringId C_GRADE;
	
	@ClientString(id = 1800168, message = "D-Grade")
	public static NpcStringId D_GRADE;
	
	@ClientString(id = 1800169, message = "F-Grade")
	public static NpcStringId F_GRADE;
	
	@ClientString(id = 1800170, message = "This is... This is a great achievement that is worthy of the true heroes of legend!")
	public static NpcStringId THIS_IS_THIS_IS_A_GREAT_ACHIEVEMENT_THAT_IS_WORTHY_OF_THE_TRUE_HEROES_OF_LEGEND;
	
	@ClientString(id = 1800171, message = "Admirable. You greatly decreased the speed of invasion through Kamaloka.")
	public static NpcStringId ADMIRABLE_YOU_GREATLY_DECREASED_THE_SPEED_OF_INVASION_THROUGH_KAMALOKA;
	
	@ClientString(id = 1800172, message = "Very good. Your skill makes you a model for other adventurers to follow.")
	public static NpcStringId VERY_GOOD_YOUR_SKILL_MAKES_YOU_A_MODEL_FOR_OTHER_ADVENTURERS_TO_FOLLOW;
	
	@ClientString(id = 1800173, message = "Good work. If all adventurers produce results like you, we will slowly start to see the glimmer of hope.")
	public static NpcStringId GOOD_WORK_IF_ALL_ADVENTURERS_PRODUCE_RESULTS_LIKE_YOU_WE_WILL_SLOWLY_START_TO_SEE_THE_GLIMMER_OF_HOPE;
	
	@ClientString(id = 1800174, message = "Unfortunately, it seems that Rim Kamaloka cannot be easily approached by everyone.")
	public static NpcStringId UNFORTUNATELY_IT_SEEMS_THAT_RIM_KAMALOKA_CANNOT_BE_EASILY_APPROACHED_BY_EVERYONE;
	
	@ClientString(id = 1800175, message = "How disappointing. It looks like I made a mistake in sending you inside Rim Kamaloka.")
	public static NpcStringId HOW_DISAPPOINTING_IT_LOOKS_LIKE_I_MADE_A_MISTAKE_IN_SENDING_YOU_INSIDE_RIM_KAMALOKA;
	
	@ClientString(id = 1800176, message = "Intruder alert. Intruder alert.")
	public static NpcStringId INTRUDER_ALERT_INTRUDER_ALERT;
	
	@ClientString(id = 1800177, message = "What are you doing? Hurry up and help me!")
	public static NpcStringId WHAT_ARE_YOU_DOING_HURRY_UP_AND_HELP_ME;
	
	@ClientString(id = 1800178, message = "I've had it up to here with you! I'll take care of you!")
	public static NpcStringId I_VE_HAD_IT_UP_TO_HERE_WITH_YOU_I_LL_TAKE_CARE_OF_YOU;
	
	@ClientString(id = 1800179, message = "Ah... My mind is a wreck.")
	public static NpcStringId AH_MY_MIND_IS_A_WRECK;
	
	@ClientString(id = 1800180, message = "If you thought that my subordinates would be so few, you are mistaken!")
	public static NpcStringId IF_YOU_THOUGHT_THAT_MY_SUBORDINATES_WOULD_BE_SO_FEW_YOU_ARE_MISTAKEN;
	
	@ClientString(id = 1800181, message = "There's not much I can do, but I want to help you.")
	public static NpcStringId THERE_S_NOT_MUCH_I_CAN_DO_BUT_I_WANT_TO_HELP_YOU;
	
	@ClientString(id = 1800182, message = "You $s1! Attack them!")
	public static NpcStringId YOU_S1_ATTACK_THEM;
	
	@ClientString(id = 1800183, message = "Come out! My subordinate! I summon you to drive them out!")
	public static NpcStringId COME_OUT_MY_SUBORDINATE_I_SUMMON_YOU_TO_DRIVE_THEM_OUT;
	
	@ClientString(id = 1800184, message = "There's not much I can do, but I will risk my life to help you!")
	public static NpcStringId THERE_S_NOT_MUCH_I_CAN_DO_BUT_I_WILL_RISK_MY_LIFE_TO_HELP_YOU;
	
	@ClientString(id = 1800185, message = "Arg! The pain is more than I can stand!")
	public static NpcStringId ARG_THE_PAIN_IS_MORE_THAN_I_CAN_STAND;
	
	@ClientString(id = 1800186, message = "Ahh! How did he find my weakness?")
	public static NpcStringId AHH_HOW_DID_HE_FIND_MY_WEAKNESS;
	
	@ClientString(id = 1800187, message = "We were able to successfully collect the Essence of Kamaloka from the Kanabions that you defeated. Here they are.")
	public static NpcStringId WE_WERE_ABLE_TO_SUCCESSFULLY_COLLECT_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_HERE_THEY_ARE;
	
	@ClientString(id = 1800188, message = "But we were able to collect somehow the Essence of Kamaloka from the Kanabions that you defeated. Here they are.")
	public static NpcStringId BUT_WE_WERE_ABLE_TO_COLLECT_SOMEHOW_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_HERE_THEY_ARE;
	
	@ClientString(id = 1800189, message = "I'm sorry, but we were unable to collect the Essence of Kamaloka from the Kanabions that you defeated because their dark energy was too weak.")
	public static NpcStringId I_M_SORRY_BUT_WE_WERE_UNABLE_TO_COLLECT_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_BECAUSE_THEIR_DARK_ENERGY_WAS_TOO_WEAK;
	
	@ClientString(id = 1800190, message = "Rather than simply defeating the enemies, you seem to understand our goal and purpose as well.")
	public static NpcStringId RATHER_THAN_SIMPLY_DEFEATING_THE_ENEMIES_YOU_SEEM_TO_UNDERSTAND_OUR_GOAL_AND_PURPOSE_AS_WELL;
	
	@ClientString(id = 1800191, message = "Dopplers and Voids possess an enhanced amount of the Kanabions' dark energy, so it is important to concentrate on defeating them when blocking the Kamalokians attack.")
	public static NpcStringId DOPPLERS_AND_VOIDS_POSSESS_AN_ENHANCED_AMOUNT_OF_THE_KANABIONS_DARK_ENERGY_SO_IT_IS_IMPORTANT_TO_CONCENTRATE_ON_DEFEATING_THEM_WHEN_BLOCKING_THE_KAMALOKIANS_ATTACK;
	
	@ClientString(id = 1800192, message = "Have you seen Kanabions being remade as new Kanabions sometimes? You can see it occur more often by inflicting great damage during an attack or at the moment you defeat them.")
	public static NpcStringId HAVE_YOU_SEEN_KANABIONS_BEING_REMADE_AS_NEW_KANABIONS_SOMETIMES_YOU_CAN_SEE_IT_OCCUR_MORE_OFTEN_BY_INFLICTING_GREAT_DAMAGE_DURING_AN_ATTACK_OR_AT_THE_MOMENT_YOU_DEFEAT_THEM;
	
	@ClientString(id = 1800193, message = "As in any other battle, it is critical to protect yourself while you are inside Rim Kamaloka. We do not want to attack recklessly.")
	public static NpcStringId AS_IN_ANY_OTHER_BATTLE_IT_IS_CRITICAL_TO_PROTECT_YOURSELF_WHILE_YOU_ARE_INSIDE_RIM_KAMALOKA_WE_DO_NOT_WANT_TO_ATTACK_RECKLESSLY;
	
	@ClientString(id = 1800194, message = "We value developing an individual's overall power rather than a one-time victory. If you relied on another person's support this time, why don't you try to rely on your own strength next time?")
	public static NpcStringId WE_VALUE_DEVELOPING_AN_INDIVIDUAL_S_OVERALL_POWER_RATHER_THAN_A_ONE_TIME_VICTORY_IF_YOU_RELIED_ON_ANOTHER_PERSON_S_SUPPORT_THIS_TIME_WHY_DON_T_YOU_TRY_TO_RELY_ON_YOUR_OWN_STRENGTH_NEXT_TIME;
	
	@ClientString(id = 1800195, message = "Are you sure that the battle just now was at the appropriate level? Bothering lower Kanabions, as if for mere entertainment, is considered to be a wasted battle for us.")
	public static NpcStringId ARE_YOU_SURE_THAT_THE_BATTLE_JUST_NOW_WAS_AT_THE_APPROPRIATE_LEVEL_BOTHERING_LOWER_KANABIONS_AS_IF_FOR_MERE_ENTERTAINMENT_IS_CONSIDERED_TO_BE_A_WASTED_BATTLE_FOR_US;
	
	@ClientString(id = 1800196, message = "The greatest victory involves using all available resources, eliminating all of the enemy's opportunities, and bringing it to the fastest possible end. Don't you think so?")
	public static NpcStringId THE_GREATEST_VICTORY_INVOLVES_USING_ALL_AVAILABLE_RESOURCES_ELIMINATING_ALL_OF_THE_ENEMY_S_OPPORTUNITIES_AND_BRINGING_IT_TO_THE_FASTEST_POSSIBLE_END_DON_T_YOU_THINK_SO;
	
	@ClientString(id = 1800197, message = "Emergency! Emergency! The outer wall is weakening rapidly!")
	public static NpcStringId EMERGENCY_EMERGENCY_THE_OUTER_WALL_IS_WEAKENING_RAPIDLY;
	
	@ClientString(id = 1800198, message = "The remaining strength of the outer wall is $s1!")
	public static NpcStringId THE_REMAINING_STRENGTH_OF_THE_OUTER_WALL_IS_S1;
	
	@ClientString(id = 1800199, message = "Pathfinder Savior")
	public static NpcStringId PATHFINDER_SAVIOR;
	
	@ClientString(id = 1800200, message = "Pathfinder Supporter")
	public static NpcStringId PATHFINDER_SUPPORTER;
	
	@ClientString(id = 1800201, message = "Some Kanabions who haven't fully adjusted yet to their new physical form are known to exhibit symptoms of an extremely weakened body structure sometimes. If you attack them at that moment, you will have great results.")
	public static NpcStringId SOME_KANABIONS_WHO_HAVEN_T_FULLY_ADJUSTED_YET_TO_THEIR_NEW_PHYSICAL_FORM_ARE_KNOWN_TO_EXHIBIT_SYMPTOMS_OF_AN_EXTREMELY_WEAKENED_BODY_STRUCTURE_SOMETIMES_IF_YOU_ATTACK_THEM_AT_THAT_MOMENT_YOU_WILL_HAVE_GREAT_RESULTS;
	
	@ClientString(id = 1800202, message = "Have you ever heard of $s1? They say it's a genuine $s2!")
	public static NpcStringId HAVE_YOU_EVER_HEARD_OF_S1_THEY_SAY_IT_S_A_GENUINE_S2;
	
	@ClientString(id = 1800203, message = "There are 5 minutes remaining to register for Kratei's cube match.")
	public static NpcStringId THERE_ARE_5_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEI_S_CUBE_MATCH;
	
	@ClientString(id = 1800204, message = "There are 3 minutes remaining to register for Kratei's cube match.")
	public static NpcStringId THERE_ARE_3_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEI_S_CUBE_MATCH;
	
	@ClientString(id = 1800205, message = "There are 1 minutes remaining to register for Kratei's cube match.")
	public static NpcStringId THERE_ARE_1_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEI_S_CUBE_MATCH;
	
	@ClientString(id = 1800206, message = "The match will begin in $s1 minute(s).")
	public static NpcStringId THE_MATCH_WILL_BEGIN_IN_S1_MINUTE_S;
	
	@ClientString(id = 1800207, message = "The match will begin shortly.")
	public static NpcStringId THE_MATCH_WILL_BEGIN_SHORTLY;
	
	@ClientString(id = 1800208, message = "You have $s1 minute(s) to register for the match.")
	public static NpcStringId YOU_HAVE_S1_MINUTE_S_TO_REGISTER_FOR_THE_MATCH;
	
	@ClientString(id = 1800209, message = "Ohh...oh...oh...")
	public static NpcStringId OHH_OH_OH;
	
	@ClientString(id = 1800210, message = "Fire")
	public static NpcStringId FIRE;
	
	@ClientString(id = 1800211, message = "Water")
	public static NpcStringId WATER;
	
	@ClientString(id = 1800212, message = "Wind")
	public static NpcStringId WIND;
	
	@ClientString(id = 1800213, message = "Earth")
	public static NpcStringId EARTH;
	
	@ClientString(id = 1800214, message = "...It's $s1...")
	public static NpcStringId IT_S_S1;
	
	@ClientString(id = 1800215, message = "...$s1 is strong...")
	public static NpcStringId S1_IS_STRONG;
	
	@ClientString(id = 1800216, message = "...It's always $s1...")
	public static NpcStringId IT_S_ALWAYS_S1;
	
	@ClientString(id = 1800217, message = "...$s1 won't do...")
	public static NpcStringId S1_WON_T_DO;
	
	@ClientString(id = 1800218, message = "You will be cursed for seeking the treasure!")
	public static NpcStringId YOU_WILL_BE_CURSED_FOR_SEEKING_THE_TREASURE;
	
	@ClientString(id = 1800219, message = "The airship has been summoned. It will automatically depart in 5 minutes.")
	public static NpcStringId THE_AIRSHIP_HAS_BEEN_SUMMONED_IT_WILL_AUTOMATICALLY_DEPART_IN_5_MINUTES;
	
	@ClientString(id = 1800220, message = "The regularly scheduled airship has arrived. It will depart for the Aden continent in 1 minute.")
	public static NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_HAS_ARRIVED_IT_WILL_DEPART_FOR_THE_ADEN_CONTINENT_IN_1_MINUTE;
	
	@ClientString(id = 1800221, message = "The regularly scheduled airship that flies to the Aden continent has departed.")
	public static NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_THAT_FLIES_TO_THE_ADEN_CONTINENT_HAS_DEPARTED;
	
	@ClientString(id = 1800222, message = "The regularly scheduled airship has arrived. It will depart for the Gracia continent in 1 minute.")
	public static NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_HAS_ARRIVED_IT_WILL_DEPART_FOR_THE_GRACIA_CONTINENT_IN_1_MINUTE;
	
	@ClientString(id = 1800223, message = "The regularly scheduled airship that flies to the Gracia continent has departed.")
	public static NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_THAT_FLIES_TO_THE_GRACIA_CONTINENT_HAS_DEPARTED;
	
	@ClientString(id = 1800224, message = "Another airship has been summoned to the wharf. Please try again later.")
	public static NpcStringId ANOTHER_AIRSHIP_HAS_BEEN_SUMMONED_TO_THE_WHARF_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 1800225, message = "Huh? The sky looks funny. What's that?")
	public static NpcStringId HUH_THE_SKY_LOOKS_FUNNY_WHAT_S_THAT;
	
	@ClientString(id = 1800226, message = "A powerful subordinate is being held by the Barrier Orb! This reaction means...!")
	public static NpcStringId A_POWERFUL_SUBORDINATE_IS_BEING_HELD_BY_THE_BARRIER_ORB_THIS_REACTION_MEANS;
	
	@ClientString(id = 1800227, message = "Be careful...! Something's coming...!")
	public static NpcStringId BE_CAREFUL_SOMETHING_S_COMING;
	
	@ClientString(id = 1800228, message = "You must first found a clan or belong to one.")
	public static NpcStringId YOU_MUST_FIRST_FOUND_A_CLAN_OR_BELONG_TO_ONE;
	
	@ClientString(id = 1800229, message = "There is no party currently challenging Ekimus. \\n If no party enters within $s1 seconds, the attack on the Heart of Immortality will fail...")
	public static NpcStringId THERE_IS_NO_PARTY_CURRENTLY_CHALLENGING_EKIMUS_N_IF_NO_PARTY_ENTERS_WITHIN_S1_SECONDS_THE_ATTACK_ON_THE_HEART_OF_IMMORTALITY_WILL_FAIL;
	
	@ClientString(id = 1800230, message = "Ekimus has gained strength from a tumor...")
	public static NpcStringId EKIMUS_HAS_GAINED_STRENGTH_FROM_A_TUMOR;
	
	@ClientString(id = 1800231, message = "Ekimus has been weakened by losing strength from a tumor!")
	public static NpcStringId EKIMUS_HAS_BEEN_WEAKENED_BY_LOSING_STRENGTH_FROM_A_TUMOR;
	
	@ClientString(id = 1800232, message = "The Soul Coffin has awakened Ekimus. If $s1 more Soul Coffin(s) are created, the defense of the Heart of Immortality will fail...")
	public static NpcStringId THE_SOUL_COFFIN_HAS_AWAKENED_EKIMUS_IF_S1_MORE_SOUL_COFFIN_S_ARE_CREATED_THE_DEFENSE_OF_THE_HEART_OF_IMMORTALITY_WILL_FAIL;
	
	@ClientString(id = 1800233, message = "C'mon, c'mon! Show your face, you little rats! Let me see what the doomed weaklings are scheming!")
	public static NpcStringId C_MON_C_MON_SHOW_YOUR_FACE_YOU_LITTLE_RATS_LET_ME_SEE_WHAT_THE_DOOMED_WEAKLINGS_ARE_SCHEMING;
	
	@ClientString(id = 1800234, message = "Impressive.... Hahaha it's so much fun, but I need to chill a little while. Argekunte, clear the way!")
	public static NpcStringId IMPRESSIVE_HAHAHA_IT_S_SO_MUCH_FUN_BUT_I_NEED_TO_CHILL_A_LITTLE_WHILE_ARGEKUNTE_CLEAR_THE_WAY;
	
	@ClientString(id = 1800235, message = "Kyahaha! Since the tumor has been resurrected, I no longer need to waste my time on you!")
	public static NpcStringId KYAHAHA_SINCE_THE_TUMOR_HAS_BEEN_RESURRECTED_I_NO_LONGER_NEED_TO_WASTE_MY_TIME_ON_YOU;
	
	@ClientString(id = 1800236, message = "Keu... I will leave for now... But don't think this is over... The Seed of Infinity can never die...")
	public static NpcStringId KEU_I_WILL_LEAVE_FOR_NOW_BUT_DON_T_THINK_THIS_IS_OVER_THE_SEED_OF_INFINITY_CAN_NEVER_DIE;
	
	@ClientString(id = 1800237, message = "Kahahaha! That guy's nothing! He can't even kill without my permission! See here! Ultimate forgotten magic! Deathless Guardian!")
	public static NpcStringId KAHAHAHA_THAT_GUY_S_NOTHING_HE_CAN_T_EVEN_KILL_WITHOUT_MY_PERMISSION_SEE_HERE_ULTIMATE_FORGOTTEN_MAGIC_DEATHLESS_GUARDIAN;
	
	@ClientString(id = 1800238, message = "I curse the day that I became your slave in order to escape death, Cohemenes! I swear that I shall see you die with my own eyes!")
	public static NpcStringId I_CURSE_THE_DAY_THAT_I_BECAME_YOUR_SLAVE_IN_ORDER_TO_ESCAPE_DEATH_COHEMENES_I_SWEAR_THAT_I_SHALL_SEE_YOU_DIE_WITH_MY_OWN_EYES;
	
	@ClientString(id = 1800239, message = "My enemy is dying, and my blood is boiling! What cruel curse is this!")
	public static NpcStringId MY_ENEMY_IS_DYING_AND_MY_BLOOD_IS_BOILING_WHAT_CRUEL_CURSE_IS_THIS;
	
	@ClientString(id = 1800240, message = "Hall of Suffering")
	public static NpcStringId HALL_OF_SUFFERING;
	
	@ClientString(id = 1800241, message = "Hall of Erosion")
	public static NpcStringId HALL_OF_EROSION;
	
	@ClientString(id = 1800242, message = "Heart of Immortality")
	public static NpcStringId HEART_OF_IMMORTALITY;
	
	@ClientString(id = 1800243, message = "Attack")
	public static NpcStringId ATTACK;
	
	@ClientString(id = 1800244, message = "Defend")
	public static NpcStringId DEFEND;
	
	@ClientString(id = 1800245, message = "Congratulations! You have succeeded at $s1 $s2! The instance will shortly expire.")
	public static NpcStringId CONGRATULATIONS_YOU_HAVE_SUCCEEDED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE;
	
	@ClientString(id = 1800246, message = "You have failed at $s1 $s2... The instance will shortly expire.")
	public static NpcStringId YOU_HAVE_FAILED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE;
	
	@ClientString(id = 1800247, message = "$s1's party has moved to a different location through the crack in the tumor!")
	public static NpcStringId S1_S_PARTY_HAS_MOVED_TO_A_DIFFERENT_LOCATION_THROUGH_THE_CRACK_IN_THE_TUMOR;
	
	@ClientString(id = 1800248, message = "$s1's party has entered the Chamber of Ekimus through the crack in the tumor!")
	public static NpcStringId S1_S_PARTY_HAS_ENTERED_THE_CHAMBER_OF_EKIMUS_THROUGH_THE_CRACK_IN_THE_TUMOR;
	
	@ClientString(id = 1800249, message = "Ekimus has sensed abnormal activity. \\nThe advancing party is forcefully expelled!")
	public static NpcStringId EKIMUS_HAS_SENSED_ABNORMAL_ACTIVITY_NTHE_ADVANCING_PARTY_IS_FORCEFULLY_EXPELLED;
	
	@ClientString(id = 1800250, message = "There aren't enough items. In order to summon the airship, you need 5 Energy Star Stones.")
	public static NpcStringId THERE_AREN_T_ENOUGH_ITEMS_IN_ORDER_TO_SUMMON_THE_AIRSHIP_YOU_NEED_5_ENERGY_STAR_STONES;
	
	@ClientString(id = 1800251, message = "The Soul Devourers who are greedy to eat the Seeds of Life that remain alive until the end have awakened...!")
	public static NpcStringId THE_SOUL_DEVOURERS_WHO_ARE_GREEDY_TO_EAT_THE_SEEDS_OF_LIFE_THAT_REMAIN_ALIVE_UNTIL_THE_END_HAVE_AWAKENED;
	
	@ClientString(id = 1800252, message = "The first Feral Hound of the Netherworld has awakened!")
	public static NpcStringId THE_FIRST_FERAL_HOUND_OF_THE_NETHERWORLD_HAS_AWAKENED;
	
	@ClientString(id = 1800253, message = "The second Feral Hound of the Netherworld has awakened!")
	public static NpcStringId THE_SECOND_FERAL_HOUND_OF_THE_NETHERWORLD_HAS_AWAKENED;
	
	@ClientString(id = 1800254, message = "Clinging on won't help you! Ultimate forgotten magic, Blade Turn!")
	public static NpcStringId CLINGING_ON_WON_T_HELP_YOU_ULTIMATE_FORGOTTEN_MAGIC_BLADE_TURN;
	
	@ClientString(id = 1800255, message = "Even special sauce can't help you! Ultimate forgotten magic, Force Shield!")
	public static NpcStringId EVEN_SPECIAL_SAUCE_CAN_T_HELP_YOU_ULTIMATE_FORGOTTEN_MAGIC_FORCE_SHIELD;
	
	@ClientString(id = 1800256, message = "You little doomed maggots! Even if you keep swarming, the power of Immortality will only grow stronger!")
	public static NpcStringId YOU_LITTLE_DOOMED_MAGGOTS_EVEN_IF_YOU_KEEP_SWARMING_THE_POWER_OF_IMMORTALITY_WILL_ONLY_GROW_STRONGER;
	
	@ClientString(id = 1800257, message = "The Airship Summon License has been awarded. Your clan can now summon an airship.")
	public static NpcStringId THE_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_AWARDED_YOUR_CLAN_CAN_NOW_SUMMON_AN_AIRSHIP;
	
	@ClientString(id = 1800258, message = "The Gracia treasure box has appeared!")
	public static NpcStringId THE_GRACIA_TREASURE_BOX_HAS_APPEARED;
	
	@ClientString(id = 1800259, message = "The Gracia treasure box will soon disappear!")
	public static NpcStringId THE_GRACIA_TREASURE_BOX_WILL_SOON_DISAPPEAR;
	
	@ClientString(id = 1800260, message = "You have been cursed by the tumor and have incurred $s1 damage.")
	public static NpcStringId YOU_HAVE_BEEN_CURSED_BY_THE_TUMOR_AND_HAVE_INCURRED_S1_DAMAGE;
	
	@ClientString(id = 1800261, message = "I shall accept your challenge, $s1! Come and die in the arms of immortality!")
	public static NpcStringId I_SHALL_ACCEPT_YOUR_CHALLENGE_S1_COME_AND_DIE_IN_THE_ARMS_OF_IMMORTALITY;
	
	@ClientString(id = 1800262, message = "You will participate in $s1 $s2 shortly. Be prepared for anything.")
	public static NpcStringId YOU_WILL_PARTICIPATE_IN_S1_S2_SHORTLY_BE_PREPARED_FOR_ANYTHING;
	
	@ClientString(id = 1800263, message = "You can hear the undead of Ekimus rushing toward you. $s1 $s2, it has now begun!")
	public static NpcStringId YOU_CAN_HEAR_THE_UNDEAD_OF_EKIMUS_RUSHING_TOWARD_YOU_S1_S2_IT_HAS_NOW_BEGUN;
	
	@ClientString(id = 1800264, message = "You can feel the surging energy of death from the tumor.")
	public static NpcStringId YOU_CAN_FEEL_THE_SURGING_ENERGY_OF_DEATH_FROM_THE_TUMOR;
	
	@ClientString(id = 1800265, message = "The area near the tumor is full of ominous energy.")
	public static NpcStringId THE_AREA_NEAR_THE_TUMOR_IS_FULL_OF_OMINOUS_ENERGY;
	
	@ClientString(id = 1800266, message = "You tried to drop us. How stupid!")
	public static NpcStringId YOU_TRIED_TO_DROP_US_HOW_STUPID;
	
	@ClientString(id = 1800267, message = "We are blood brethren. I can't fall so easily here and leave my brother behind.")
	public static NpcStringId WE_ARE_BLOOD_BRETHREN_I_CAN_T_FALL_SO_EASILY_HERE_AND_LEAVE_MY_BROTHER_BEHIND;
	
	@ClientString(id = 1800268, message = "You were always what I aspired to be. Do you think I would fall so easily here when I have a brother like that?")
	public static NpcStringId YOU_WERE_ALWAYS_WHAT_I_ASPIRED_TO_BE_DO_YOU_THINK_I_WOULD_FALL_SO_EASILY_HERE_WHEN_I_HAVE_A_BROTHER_LIKE_THAT;
	
	@ClientString(id = 1800269, message = "With all connections to the tumor severed, Ekimus has lost its power to control the Feral Hound!")
	public static NpcStringId WITH_ALL_CONNECTIONS_TO_THE_TUMOR_SEVERED_EKIMUS_HAS_LOST_ITS_POWER_TO_CONTROL_THE_FERAL_HOUND;
	
	@ClientString(id = 1800270, message = "With the connection to the tumor restored, Ekimus has regained control over the Feral Hound...")
	public static NpcStringId WITH_THE_CONNECTION_TO_THE_TUMOR_RESTORED_EKIMUS_HAS_REGAINED_CONTROL_OVER_THE_FERAL_HOUND;
	
	@ClientString(id = 1800271, message = "Woooong!")
	public static NpcStringId WOOOONG;
	
	@ClientString(id = 1800272, message = "Woong... Woong... Woo...")
	public static NpcStringId WOONG_WOONG_WOO;
	
	@ClientString(id = 1800273, message = "The enemies have attacked. Everyone come out and fight!!!! ... Urgh~!")
	public static NpcStringId THE_ENEMIES_HAVE_ATTACKED_EVERYONE_COME_OUT_AND_FIGHT_URGH;
	
	@ClientString(id = 1800274, message = "The tumor inside $s1 has been destroyed! \\nIn order to draw out the cowardly Cohemenes, you must destroy all the tumors!")
	public static NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NIN_ORDER_TO_DRAW_OUT_THE_COWARDLY_COHEMENES_YOU_MUST_DESTROY_ALL_THE_TUMORS;
	
	@ClientString(id = 1800275, message = "The tumor inside $s1 has completely revived. \\nThe restrengthened Cohemenes has fled deeper inside the seed...")
	public static NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NTHE_RESTRENGTHENED_COHEMENES_HAS_FLED_DEEPER_INSIDE_THE_SEED;
	
	@ClientString(id = 1800276, message = "The awarded Airship Summon License has been received.")
	public static NpcStringId THE_AWARDED_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_RECEIVED;
	
	@ClientString(id = 1800277, message = "You do not currently have an Airship Summon License. You can earn your Airship Summon License through Engineer Lekon.")
	public static NpcStringId YOU_DO_NOT_CURRENTLY_HAVE_AN_AIRSHIP_SUMMON_LICENSE_YOU_CAN_EARN_YOUR_AIRSHIP_SUMMON_LICENSE_THROUGH_ENGINEER_LEKON;
	
	@ClientString(id = 1800278, message = "The Airship Summon License has already been awarded.")
	public static NpcStringId THE_AIRSHIP_SUMMON_LICENSE_HAS_ALREADY_BEEN_AWARDED;
	
	@ClientString(id = 1800279, message = "If you have items, please give them to me.")
	public static NpcStringId IF_YOU_HAVE_ITEMS_PLEASE_GIVE_THEM_TO_ME;
	
	@ClientString(id = 1800280, message = "My stomach is empty.")
	public static NpcStringId MY_STOMACH_IS_EMPTY;
	
	@ClientString(id = 1800281, message = "I'm hungry, I'm hungry!")
	public static NpcStringId I_M_HUNGRY_I_M_HUNGRY;
	
	@ClientString(id = 1800282, message = "I'm still not full...")
	public static NpcStringId I_M_STILL_NOT_FULL;
	
	@ClientString(id = 1800283, message = "I'm still hungry~")
	public static NpcStringId I_M_STILL_HUNGRY;
	
	@ClientString(id = 1800284, message = "I feel a little woozy...")
	public static NpcStringId I_FEEL_A_LITTLE_WOOZY;
	
	@ClientString(id = 1800285, message = "Give me something to eat.")
	public static NpcStringId GIVE_ME_SOMETHING_TO_EAT;
	
	@ClientString(id = 1800286, message = "Now it's time to eat~")
	public static NpcStringId NOW_IT_S_TIME_TO_EAT;
	
	@ClientString(id = 1800287, message = "I also need a dessert.")
	public static NpcStringId I_ALSO_NEED_A_DESSERT;
	
	@ClientString(id = 1800288, message = "I'm still hungry.")
	public static NpcStringId I_M_STILL_HUNGRY_2;
	
	@ClientString(id = 1800289, message = "I'm full now, I don't want to eat anymore.")
	public static NpcStringId I_M_FULL_NOW_I_DON_T_WANT_TO_EAT_ANYMORE;
	
	@ClientString(id = 1800290, message = "I haven't eaten anything, I'm so weak~")
	public static NpcStringId I_HAVEN_T_EATEN_ANYTHING_I_M_SO_WEAK;
	
	@ClientString(id = 1800291, message = "Yum-yum, yum-yum")
	public static NpcStringId YUM_YUM_YUM_YUM;
	
	@ClientString(id = 1800292, message = "You've sustained $s1 damage as Tumor's shell started melting after touching the sacred seal on the shield! ")
	public static NpcStringId YOU_VE_SUSTAINED_S1_DAMAGE_AS_TUMOR_S_SHELL_STARTED_MELTING_AFTER_TOUCHING_THE_SACRED_SEAL_ON_THE_SHIELD;
	
	@ClientString(id = 1800293, message = "You've sustained $s1 damage as Soul Coffin's shell started melting after touching the sacred seal on the shield! ")
	public static NpcStringId YOU_VE_SUSTAINED_S1_DAMAGE_AS_SOUL_COFFIN_S_SHELL_STARTED_MELTING_AFTER_TOUCHING_THE_SACRED_SEAL_ON_THE_SHIELD;
	
	@ClientString(id = 1800294, message = "Raid rewards are given to the attacker <$s1>.")
	public static NpcStringId RAID_REWARDS_ARE_GIVEN_TO_THE_ATTACKER_S1;
	
	@ClientString(id = 1800295, message = "Obelisk has collapsed. Don't let the enemies jump around wildly anymore!!!!")
	public static NpcStringId OBELISK_HAS_COLLAPSED_DON_T_LET_THE_ENEMIES_JUMP_AROUND_WILDLY_ANYMORE;
	
	@ClientString(id = 1800296, message = "Enemies are trying to destroy the fortress. Everyone defend the fortress!!!!")
	public static NpcStringId ENEMIES_ARE_TRYING_TO_DESTROY_THE_FORTRESS_EVERYONE_DEFEND_THE_FORTRESS;
	
	@ClientString(id = 1800297, message = "Come out, warriors. Protect Seed of Destruction.")
	public static NpcStringId COME_OUT_WARRIORS_PROTECT_SEED_OF_DESTRUCTION;
	
	@ClientString(id = 1800298, message = "The undead of Ekimus is attacking Seed of Life. Defending Hall of Erosion will fail even if one Seed of Life is destroyed...")
	public static NpcStringId THE_UNDEAD_OF_EKIMUS_IS_ATTACKING_SEED_OF_LIFE_DEFENDING_HALL_OF_EROSION_WILL_FAIL_EVEN_IF_ONE_SEED_OF_LIFE_IS_DESTROYED;
	
	@ClientString(id = 1800299, message = "All the tumors inside $s1 have been destroyed! Driven into a corner, Cohemenes appears close by!")
	public static NpcStringId ALL_THE_TUMORS_INSIDE_S1_HAVE_BEEN_DESTROYED_DRIVEN_INTO_A_CORNER_COHEMENES_APPEARS_CLOSE_BY;
	
	@ClientString(id = 1800300, message = "The tumor inside $s1 has been destroyed! \\nThe nearby Undead that were attacking Seed of Life start losing their energy and run away!")
	public static NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NTHE_NEARBY_UNDEAD_THAT_WERE_ATTACKING_SEED_OF_LIFE_START_LOSING_THEIR_ENERGY_AND_RUN_AWAY;
	
	@ClientString(id = 1800301, message = "The tumor inside $s1 has completely revived. \\nRecovered nearby Undead are swarming toward Seed of Life...")
	public static NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NRECOVERED_NEARBY_UNDEAD_ARE_SWARMING_TOWARD_SEED_OF_LIFE;
	
	@ClientString(id = 1800302, message = "The tumor inside $s1 that has provided energy \\n to Ekimus is destroyed!")
	public static NpcStringId THE_TUMOR_INSIDE_S1_THAT_HAS_PROVIDED_ENERGY_N_TO_EKIMUS_IS_DESTROYED;
	
	@ClientString(id = 1800303, message = "The tumor inside $s1 has been completely resurrected \\n and started to energize Ekimus again...")
	public static NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_COMPLETELY_RESURRECTED_N_AND_STARTED_TO_ENERGIZE_EKIMUS_AGAIN;
	
	@ClientString(id = 1800304, message = "The tumor inside $s1 has been destroyed! \\nThe speed that Ekimus calls out his prey has slowed down!")
	public static NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NTHE_SPEED_THAT_EKIMUS_CALLS_OUT_HIS_PREY_HAS_SLOWED_DOWN;
	
	@ClientString(id = 1800305, message = "The tumor inside $s1 has completely revived. \\nEkimus started to regain his energy and is desperately looking for his prey...")
	public static NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NEKIMUS_STARTED_TO_REGAIN_HIS_ENERGY_AND_IS_DESPERATELY_LOOKING_FOR_HIS_PREY;
	
	@ClientString(id = 1800306, message = "Bring more, more souls...!")
	public static NpcStringId BRING_MORE_MORE_SOULS;
	
	@ClientString(id = 1800307, message = "The Hall of Erosion attack will fail unless you make a quick attack against the tumor!")
	public static NpcStringId THE_HALL_OF_EROSION_ATTACK_WILL_FAIL_UNLESS_YOU_MAKE_A_QUICK_ATTACK_AGAINST_THE_TUMOR;
	
	@ClientString(id = 1800308, message = "As the tumor was not threatened, Cohemenes completely ran away to deep inside the Seed...")
	public static NpcStringId AS_THE_TUMOR_WAS_NOT_THREATENED_COHEMENES_COMPLETELY_RAN_AWAY_TO_DEEP_INSIDE_THE_SEED;
	
	@ClientString(id = 1800309, message = "Your goal will be obstructed or be under a restraint.")
	public static NpcStringId YOUR_GOAL_WILL_BE_OBSTRUCTED_OR_BE_UNDER_A_RESTRAINT;
	
	@ClientString(id = 1800310, message = "You may face an unforeseen problem on your course toward the goal.")
	public static NpcStringId YOU_MAY_FACE_AN_UNFORESEEN_PROBLEM_ON_YOUR_COURSE_TOWARD_THE_GOAL;
	
	@ClientString(id = 1800311, message = "You may feel nervous and anxious because of unfavorable situations.")
	public static NpcStringId YOU_MAY_FEEL_NERVOUS_AND_ANXIOUS_BECAUSE_OF_UNFAVORABLE_SITUATIONS;
	
	@ClientString(id = 1800312, message = "Be warned when the situation is difficult because you may lose your judgment and make an irrational mistake.")
	public static NpcStringId BE_WARNED_WHEN_THE_SITUATION_IS_DIFFICULT_BECAUSE_YOU_MAY_LOSE_YOUR_JUDGMENT_AND_MAKE_AN_IRRATIONAL_MISTAKE;
	
	@ClientString(id = 1800313, message = "You may meet a trustworthy person or a good opportunity.")
	public static NpcStringId YOU_MAY_MEET_A_TRUSTWORTHY_PERSON_OR_A_GOOD_OPPORTUNITY;
	
	@ClientString(id = 1800314, message = "Your downward life starts taking an upturn.")
	public static NpcStringId YOUR_DOWNWARD_LIFE_STARTS_TAKING_AN_UPTURN;
	
	@ClientString(id = 1800315, message = "You will attract attention from people with your popularity.")
	public static NpcStringId YOU_WILL_ATTRACT_ATTENTION_FROM_PEOPLE_WITH_YOUR_POPULARITY;
	
	@ClientString(id = 1800316, message = "Your star of fortune says there'll be fish snapping at your bait.")
	public static NpcStringId YOUR_STAR_OF_FORTUNE_SAYS_THERE_LL_BE_FISH_SNAPPING_AT_YOUR_BAIT;
	
	@ClientString(id = 1800317, message = "There may be a conflict born of your dogmatic procedures.")
	public static NpcStringId THERE_MAY_BE_A_CONFLICT_BORN_OF_YOUR_DOGMATIC_PROCEDURES;
	
	@ClientString(id = 1800318, message = "Your wisdom and creativity may lead you to success with your goal.")
	public static NpcStringId YOUR_WISDOM_AND_CREATIVITY_MAY_LEAD_YOU_TO_SUCCESS_WITH_YOUR_GOAL;
	
	@ClientString(id = 1800319, message = "You may accomplish your goals if you diligently pursue them without giving up.")
	public static NpcStringId YOU_MAY_ACCOMPLISH_YOUR_GOALS_IF_YOU_DILIGENTLY_PURSUE_THEM_WITHOUT_GIVING_UP;
	
	@ClientString(id = 1800320, message = "You may get help if you go through the front door without seeking tricks or maneuvers.")
	public static NpcStringId YOU_MAY_GET_HELP_IF_YOU_GO_THROUGH_THE_FRONT_DOOR_WITHOUT_SEEKING_TRICKS_OR_MANEUVERS;
	
	@ClientString(id = 1800321, message = "A good result is on the way if you set a goal and bravely proceed toward it.")
	public static NpcStringId A_GOOD_RESULT_IS_ON_THE_WAY_IF_YOU_SET_A_GOAL_AND_BRAVELY_PROCEED_TOWARD_IT;
	
	@ClientString(id = 1800322, message = "Everything will be smoothly managed no matter how difficult.")
	public static NpcStringId EVERYTHING_WILL_BE_SMOOTHLY_MANAGED_NO_MATTER_HOW_DIFFICULT;
	
	@ClientString(id = 1800323, message = "Be firm and carefully scrutinize circumstances even when things are difficult.")
	public static NpcStringId BE_FIRM_AND_CAREFULLY_SCRUTINIZE_CIRCUMSTANCES_EVEN_WHEN_THINGS_ARE_DIFFICULT;
	
	@ClientString(id = 1800324, message = "Always think over to find neglected problems you haven't taken care of yet.")
	public static NpcStringId ALWAYS_THINK_OVER_TO_FIND_NEGLECTED_PROBLEMS_YOU_HAVEN_T_TAKEN_CARE_OF_YET;
	
	@ClientString(id = 1800325, message = "Financial fortune will greet your poor life.")
	public static NpcStringId FINANCIAL_FORTUNE_WILL_GREET_YOUR_POOR_LIFE;
	
	@ClientString(id = 1800326, message = "You may acquire wealth and fame after unlucky circumstances.")
	public static NpcStringId YOU_MAY_ACQUIRE_WEALTH_AND_FAME_AFTER_UNLUCKY_CIRCUMSTANCES;
	
	@ClientString(id = 1800327, message = "The difficult situations will turn to hope with unforeseen help.")
	public static NpcStringId THE_DIFFICULT_SITUATIONS_WILL_TURN_TO_HOPE_WITH_UNFORESEEN_HELP;
	
	@ClientString(id = 1800328, message = "A great tasks will result in success.")
	public static NpcStringId A_GREAT_TASK_WILL_RESULT_IN_SUCCESS;
	
	@ClientString(id = 1800329, message = "You may encounter a precious person who will lift your loneliness and discord.")
	public static NpcStringId YOU_MAY_ENCOUNTER_A_PRECIOUS_PERSON_WHO_WILL_LIFT_YOUR_LONELINESS_AND_DISCORD;
	
	@ClientString(id = 1800330, message = "People around you will encourage your every tasks in the future.")
	public static NpcStringId PEOPLE_AROUND_YOU_WILL_ENCOURAGE_YOUR_EVERY_TASK_IN_THE_FUTURE;
	
	@ClientString(id = 1800331, message = "Everything will be smoothly managed.")
	public static NpcStringId EVERYTHING_WILL_BE_SMOOTHLY_MANAGED;
	
	@ClientString(id = 1800332, message = "You will meet a person who can cherish your values if you maintain good ties with people.")
	public static NpcStringId YOU_WILL_MEET_A_PERSON_WHO_CAN_CHERISH_YOUR_VALUES_IF_YOU_MAINTAIN_GOOD_TIES_WITH_PEOPLE;
	
	@ClientString(id = 1800333, message = "Maintain cooperative attitude since you will meet someone to be of help.")
	public static NpcStringId MAINTAIN_COOPERATIVE_ATTITUDE_SINCE_YOU_WILL_MEET_SOMEONE_TO_BE_OF_HELP;
	
	@ClientString(id = 1800334, message = "Keep your moderation and ego in check even in successful phases of your life.")
	public static NpcStringId KEEP_YOUR_MODERATION_AND_EGO_IN_CHECK_EVEN_IN_SUCCESSFUL_PHASES_OF_YOUR_LIFE;
	
	@ClientString(id = 1800335, message = "When it comes to work, lifestyle and relationships you'll be better off to go by the text rather than tricks.")
	public static NpcStringId WHEN_IT_COMES_TO_WORK_LIFESTYLE_AND_RELATIONSHIPS_YOU_LL_BE_BETTER_OFF_TO_GO_BY_THE_TEXT_RATHER_THAN_TRICKS;
	
	@ClientString(id = 1800336, message = "Your tasks will receive substantial support since the surroundings will fully develop.")
	public static NpcStringId YOUR_TASK_WILL_RECEIVE_SUBSTANTIAL_SUPPORT_SINCE_THE_SURROUNDINGS_WILL_FULLY_DEVELOP;
	
	@ClientString(id = 1800337, message = "Your star of fortune indicate a success with mental and material assistance.")
	public static NpcStringId YOUR_STAR_OF_FORTUNE_INDICATE_A_SUCCESS_WITH_MENTAL_AND_MATERIAL_ASSISTANCE;
	
	@ClientString(id = 1800338, message = "You will enjoy popularity with your creative talents and clever acts.")
	public static NpcStringId YOU_WILL_ENJOY_POPULARITY_WITH_YOUR_CREATIVE_TALENTS_AND_CLEVER_ACTS;
	
	@ClientString(id = 1800339, message = "People will line up to be of assistance to you.")
	public static NpcStringId PEOPLE_WILL_LINE_UP_TO_BE_OF_ASSISTANCE_TO_YOU;
	
	@ClientString(id = 1800340, message = "You may meet someone to share your journey.")
	public static NpcStringId YOU_MAY_MEET_SOMEONE_TO_SHARE_YOUR_JOURNEY;
	
	@ClientString(id = 1800341, message = "You may achieve connections in many fields.")
	public static NpcStringId YOU_MAY_ACHIEVE_CONNECTIONS_IN_MANY_FIELDS;
	
	@ClientString(id = 1800342, message = "An attitude that continually studies and explores is needed, and always be sincere.")
	public static NpcStringId AN_ATTITUDE_THAT_CONTINUALLY_STUDIES_AND_EXPLORES_IS_NEEDED_AND_ALWAYS_BE_SINCERE;
	
	@ClientString(id = 1800343, message = "It's an image of a butterfly on a flower in warm spring air.")
	public static NpcStringId IT_S_AN_IMAGE_OF_A_BUTTERFLY_ON_A_FLOWER_IN_WARM_SPRING_AIR;
	
	@ClientString(id = 1800344, message = "Your goals will move smoothly with peace and happiness in your life.")
	public static NpcStringId YOUR_GOALS_WILL_MOVE_SMOOTHLY_WITH_PEACE_AND_HAPPINESS_IN_YOUR_LIFE;
	
	@ClientString(id = 1800345, message = "Love may sprout its leaves when you treat those around you with care.")
	public static NpcStringId LOVE_MAY_SPROUT_ITS_LEAVES_WHEN_YOU_TREAT_THOSE_AROUND_YOU_WITH_CARE;
	
	@ClientString(id = 1800346, message = "You may climb into a higher position with others' trust if you faithfully carry out your duties.")
	public static NpcStringId YOU_MAY_CLIMB_INTO_A_HIGHER_POSITION_WITH_OTHERS_TRUST_IF_YOU_FAITHFULLY_CARRY_OUT_YOUR_DUTIES;
	
	@ClientString(id = 1800347, message = "Everything can fall apart if you greedily aim by pure luck.")
	public static NpcStringId EVERYTHING_CAN_FALL_APART_IF_YOU_GREEDILY_AIM_BY_PURE_LUCK;
	
	@ClientString(id = 1800348, message = "Do not underestimate the importance of meeting people.")
	public static NpcStringId DO_NOT_UNDERESTIMATE_THE_IMPORTANCE_OF_MEETING_PEOPLE;
	
	@ClientString(id = 1800349, message = "An arrow will coalesce into the bow.")
	public static NpcStringId AN_ARROW_WILL_COALESCE_INTO_THE_BOW;
	
	@ClientString(id = 1800350, message = "A bony limb of a tree may bear its fruit.")
	public static NpcStringId A_BONY_LIMB_OF_A_TREE_MAY_BEAR_ITS_FRUIT;
	
	@ClientString(id = 1800351, message = "You will be rewarded for your efforts and accomplishments.")
	public static NpcStringId YOU_WILL_BE_REWARDED_FOR_YOUR_EFFORTS_AND_ACCOMPLISHMENTS;
	
	@ClientString(id = 1800352, message = "No matter where it lies, your faithful drive leads you to success.")
	public static NpcStringId NO_MATTER_WHERE_IT_LIES_YOUR_FAITHFUL_DRIVE_LEADS_YOU_TO_SUCCESS;
	
	@ClientString(id = 1800353, message = "People will be attracted to your loyalties.")
	public static NpcStringId PEOPLE_WILL_BE_ATTRACTED_TO_YOUR_LOYALTIES;
	
	@ClientString(id = 1800354, message = "You may trust yourself rather than others' talks.")
	public static NpcStringId YOU_MAY_TRUST_YOURSELF_RATHER_THAN_OTHERS_TALKS;
	
	@ClientString(id = 1800355, message = "Creative thinking away from the old viewpoint may help you.")
	public static NpcStringId CREATIVE_THINKING_AWAY_FROM_THE_OLD_VIEWPOINT_MAY_HELP_YOU;
	
	@ClientString(id = 1800356, message = "Patience without being impetuous of the results will only bear a positive outcome.")
	public static NpcStringId PATIENCE_WITHOUT_BEING_IMPETUOUS_OF_THE_RESULTS_WILL_ONLY_BEAR_A_POSITIVE_OUTCOME;
	
	@ClientString(id = 1800357, message = "The dead will come alive.")
	public static NpcStringId THE_DEAD_WILL_COME_ALIVE;
	
	@ClientString(id = 1800358, message = "There will be a shocking incident.")
	public static NpcStringId THERE_WILL_BE_A_SHOCKING_INCIDENT;
	
	@ClientString(id = 1800359, message = "You will enjoy a huge success after unforeseen luck comes before you.")
	public static NpcStringId YOU_WILL_ENJOY_A_HUGE_SUCCESS_AFTER_UNFORESEEN_LUCK_COMES_BEFORE_YOU;
	
	@ClientString(id = 1800360, message = "Do not give up since there may be a miraculous rescue from the course of despair.")
	public static NpcStringId DO_NOT_GIVE_UP_SINCE_THERE_MAY_BE_A_MIRACULOUS_RESCUE_FROM_THE_COURSE_OF_DESPAIR;
	
	@ClientString(id = 1800361, message = "An attitude to try one's best to pursue the goal is needed.")
	public static NpcStringId AN_ATTITUDE_TO_TRY_ONE_S_BEST_TO_PURSUE_THE_GOAL_IS_NEEDED;
	
	@ClientString(id = 1800362, message = "You may get a shot in the arm in your life after meeting a good person.")
	public static NpcStringId YOU_MAY_GET_A_SHOT_IN_THE_ARM_IN_YOUR_LIFE_AFTER_MEETING_A_GOOD_PERSON;
	
	@ClientString(id = 1800363, message = "You may get a big help in the course of your life.")
	public static NpcStringId YOU_MAY_GET_A_BIG_HELP_IN_THE_COURSE_OF_YOUR_LIFE;
	
	@ClientString(id = 1800364, message = "A rare opportunity will come to you so you may prosper.")
	public static NpcStringId A_RARE_OPPORTUNITY_WILL_COME_TO_YOU_SO_YOU_MAY_PROSPER;
	
	@ClientString(id = 1800365, message = "A hungry falcon will have meat.")
	public static NpcStringId A_HUNGRY_FALCON_WILL_HAVE_MEAT;
	
	@ClientString(id = 1800366, message = "A household in need will acquire a fortune and meat.")
	public static NpcStringId A_HOUSEHOLD_IN_NEED_WILL_ACQUIRE_A_FORTUNE_AND_MEAT;
	
	@ClientString(id = 1800367, message = "A hard situation will come to its end with materialistic and mental help from others.")
	public static NpcStringId A_HARD_SITUATION_WILL_COME_TO_ITS_END_WITH_MATERIALISTIC_AND_MENTAL_HELP_FROM_OTHERS;
	
	@ClientString(id = 1800368, message = "If you set a firm goal without surrender, there will be a person who can offer help and care.")
	public static NpcStringId IF_YOU_SET_A_FIRM_GOAL_WITHOUT_SURRENDER_THERE_WILL_BE_A_PERSON_WHO_CAN_OFFER_HELP_AND_CARE;
	
	@ClientString(id = 1800369, message = "You'll gain others' trust when you maintain a sincere and honest attitude.")
	public static NpcStringId YOU_LL_GAIN_OTHERS_TRUST_WHEN_YOU_MAINTAIN_A_SINCERE_AND_HONEST_ATTITUDE;
	
	@ClientString(id = 1800370, message = "Be independent at all times.")
	public static NpcStringId BE_INDEPENDENT_AT_ALL_TIMES;
	
	@ClientString(id = 1800371, message = "It's a wagon with no wheels.")
	public static NpcStringId IT_S_A_WAGON_WITH_NO_WHEELS;
	
	@ClientString(id = 1800372, message = "You've set a goal but there may be obstacles in reality.")
	public static NpcStringId YOU_VE_SET_A_GOAL_BUT_THERE_MAY_BE_OBSTACLES_IN_REALITY;
	
	@ClientString(id = 1800373, message = "You're running toward the goal but there won't be as many outcomes as you thought.")
	public static NpcStringId YOU_RE_RUNNING_TOWARD_THE_GOAL_BUT_THERE_WON_T_BE_AS_MANY_OUTCOMES_AS_YOU_THOUGHT;
	
	@ClientString(id = 1800374, message = "There are many things to consider after encountering hindrances.")
	public static NpcStringId THERE_ARE_MANY_THINGS_TO_CONSIDER_AFTER_ENCOUNTERING_HINDRANCES;
	
	@ClientString(id = 1800375, message = "A reckless move may bring a failure.")
	public static NpcStringId A_RECKLESS_MOVE_MAY_BRING_A_FAILURE;
	
	@ClientString(id = 1800376, message = "You may lose people's trust if you lack prudence at all times.")
	public static NpcStringId YOU_MAY_LOSE_PEOPLE_S_TRUST_IF_YOU_LACK_PRUDENCE_AT_ALL_TIMES;
	
	@ClientString(id = 1800377, message = "You may need to reflect on yourself with deliberation and wait for an opportunity.")
	public static NpcStringId YOU_MAY_NEED_TO_REFLECT_ON_YOURSELF_WITH_DELIBERATION_AND_WAIT_FOR_AN_OPPORTUNITY;
	
	@ClientString(id = 1800378, message = "A poor scholar receives a stipend.")
	public static NpcStringId A_POOR_SCHOLAR_RECEIVES_A_STIPEND;
	
	@ClientString(id = 1800379, message = "A scholar gets a pass toward fame and fortune.")
	public static NpcStringId A_SCHOLAR_GETS_A_PASS_TOWARD_FAME_AND_FORTUNE;
	
	@ClientString(id = 1800380, message = "Your ambition and dream will come true.")
	public static NpcStringId YOUR_AMBITION_AND_DREAM_WILL_COME_TRUE;
	
	@ClientString(id = 1800381, message = "Complicated problems around you may start being solved one after another.")
	public static NpcStringId COMPLICATED_PROBLEMS_AROUND_YOU_MAY_START_BEING_SOLVED_ONE_AFTER_ANOTHER;
	
	@ClientString(id = 1800382, message = "You will have a good result if you diligently pursue one goal without being dragged from your past.")
	public static NpcStringId YOU_WILL_HAVE_A_GOOD_RESULT_IF_YOU_DILIGENTLY_PURSUE_ONE_GOAL_WITHOUT_BEING_DRAGGED_FROM_YOUR_PAST;
	
	@ClientString(id = 1800383, message = "You may need to rid yourself of old and worn habits.")
	public static NpcStringId YOU_MAY_NEED_TO_RID_YOURSELF_OF_OLD_AND_WORN_HABITS;
	
	@ClientString(id = 1800384, message = "Be responsible with your tasks but do not hesitate to ask for colleagues' help.")
	public static NpcStringId BE_RESPONSIBLE_WITH_YOUR_TASKS_BUT_DO_NOT_HESITATE_TO_ASK_FOR_COLLEAGUES_HELP;
	
	@ClientString(id = 1800385, message = "Fish transforms into a dragon.")
	public static NpcStringId FISH_TRANSFORMS_INTO_A_DRAGON;
	
	@ClientString(id = 1800386, message = "Your dream may come true, and fame and fortune will come to you.")
	public static NpcStringId YOUR_DREAM_MAY_COME_TRUE_AND_FAME_AND_FORTUNE_WILL_COME_TO_YOU;
	
	@ClientString(id = 1800387, message = "What you've planed will be accomplished.")
	public static NpcStringId WHAT_YOU_VE_PLANED_WILL_BE_ACCOMPLISHED;
	
	@ClientString(id = 1800388, message = "You may acquire money or a new opportunity from a place you wouldn't have thought of.")
	public static NpcStringId YOU_MAY_ACQUIRE_MONEY_OR_A_NEW_OPPORTUNITY_FROM_A_PLACE_YOU_WOULDN_T_HAVE_THOUGHT_OF;
	
	@ClientString(id = 1800389, message = "There will be many offers to you, you may think them over carefully.")
	public static NpcStringId THERE_WILL_BE_MANY_OFFERS_TO_YOU_YOU_MAY_THINK_THEM_OVER_CAREFULLY;
	
	@ClientString(id = 1800390, message = "It may be a good idea not to become involved in others' business.")
	public static NpcStringId IT_MAY_BE_A_GOOD_IDEA_NOT_TO_BECOME_INVOLVED_IN_OTHERS_BUSINESS;
	
	@ClientString(id = 1800391, message = "Everything will go smoothly but be aware of danger from being careless and remiss.")
	public static NpcStringId EVERYTHING_WILL_GO_SMOOTHLY_BUT_BE_AWARE_OF_DANGER_FROM_BEING_CARELESS_AND_REMISS;
	
	@ClientString(id = 1800392, message = "If you sincerely care for someone you love, a big reward will return to you.")
	public static NpcStringId IF_YOU_SINCERELY_CARE_FOR_SOMEONE_YOU_LOVE_A_BIG_REWARD_WILL_RETURN_TO_YOU;
	
	@ClientString(id = 1800393, message = "A remedy is on its way for a serious illness.")
	public static NpcStringId A_REMEDY_IS_ON_ITS_WAY_FOR_A_SERIOUS_ILLNESS;
	
	@ClientString(id = 1800394, message = "You may acquire a precious medicine to recover after suffering a disease of a serious nature.")
	public static NpcStringId YOU_MAY_ACQUIRE_A_PRECIOUS_MEDICINE_TO_RECOVER_AFTER_SUFFERING_A_DISEASE_OF_A_SERIOUS_NATURE;
	
	@ClientString(id = 1800395, message = "You may realize your dream by meeting a man of distinction at a difficult time.")
	public static NpcStringId YOU_MAY_REALIZE_YOUR_DREAM_BY_MEETING_A_MAN_OF_DISTINCTION_AT_A_DIFFICULT_TIME;
	
	@ClientString(id = 1800396, message = "You may suffer one or two hardships on your journey.")
	public static NpcStringId YOU_MAY_SUFFER_ONE_OR_TWO_HARDSHIPS_ON_YOUR_JOURNEY;
	
	@ClientString(id = 1800397, message = "If you keep smiling without despair, people will come to trust you and offer help.")
	public static NpcStringId IF_YOU_KEEP_SMILING_WITHOUT_DESPAIR_PEOPLE_WILL_COME_TO_TRUST_YOU_AND_OFFER_HELP;
	
	@ClientString(id = 1800398, message = "Seek stability rather than dynamics in your life.")
	public static NpcStringId SEEK_STABILITY_RATHER_THAN_DYNAMICS_IN_YOUR_LIFE;
	
	@ClientString(id = 1800399, message = "It's a good idea to be careful and secure at all times.")
	public static NpcStringId IT_S_A_GOOD_IDEA_TO_BE_CAREFUL_AND_SECURE_AT_ALL_TIMES;
	
	@ClientString(id = 1800400, message = "You can't perform the job with bound hands.")
	public static NpcStringId YOU_CAN_T_PERFORM_THE_JOB_WITH_BOUND_HANDS;
	
	@ClientString(id = 1800401, message = "You may lose your drive and feel lost.")
	public static NpcStringId YOU_MAY_LOSE_YOUR_DRIVE_AND_FEEL_LOST;
	
	@ClientString(id = 1800402, message = "You may be unable to concentrate with so many problems occurring.")
	public static NpcStringId YOU_MAY_BE_UNABLE_TO_CONCENTRATE_WITH_SO_MANY_PROBLEMS_OCCURRING;
	
	@ClientString(id = 1800403, message = "Your achievement unfairly may go somewhere else.")
	public static NpcStringId YOUR_ACHIEVEMENT_UNFAIRLY_MAY_GO_SOMEWHERE_ELSE;
	
	@ClientString(id = 1800404, message = "Do not start a tasks that's not clear to you.")
	public static NpcStringId DO_NOT_START_A_TASK_THAT_S_NOT_CLEAR_TO_YOU;
	
	@ClientString(id = 1800405, message = "You will need to be prepared for all events.")
	public static NpcStringId YOU_WILL_NEED_TO_BE_PREPARED_FOR_ALL_EVENTS;
	
	@ClientString(id = 1800406, message = "Someone will acknowledge you if you relentlessly keep trying and do not give up when facing hardships.")
	public static NpcStringId SOMEONE_WILL_ACKNOWLEDGE_YOU_IF_YOU_RELENTLESSLY_KEEP_TRYING_AND_DO_NOT_GIVE_UP_WHEN_FACING_HARDSHIPS;
	
	@ClientString(id = 1800407, message = "You may perfect yourself like a dragon's horn decorates the dragon.")
	public static NpcStringId YOU_MAY_PERFECT_YOURSELF_LIKE_A_DRAGON_S_HORN_DECORATES_THE_DRAGON;
	
	@ClientString(id = 1800408, message = "Your true value starts to shine.")
	public static NpcStringId YOUR_TRUE_VALUE_STARTS_TO_SHINE;
	
	@ClientString(id = 1800409, message = "Your steady pursuit of new information and staying ahead of others will raise your value.")
	public static NpcStringId YOUR_STEADY_PURSUIT_OF_NEW_INFORMATION_AND_STAYING_AHEAD_OF_OTHERS_WILL_RAISE_YOUR_VALUE;
	
	@ClientString(id = 1800410, message = "Maintaining confidence with work or relationships may bring good results.")
	public static NpcStringId MAINTAINING_CONFIDENCE_WITH_WORK_OR_RELATIONSHIPS_MAY_BRING_GOOD_RESULTS;
	
	@ClientString(id = 1800411, message = "Learn to work with others since overconfidence will bear wrath.")
	public static NpcStringId LEARN_TO_WORK_WITH_OTHERS_SINCE_OVERCONFIDENCE_WILL_BEAR_WRATH;
	
	@ClientString(id = 1800412, message = "The dragon now acquires an eagle's wings.")
	public static NpcStringId THE_DRAGON_NOW_ACQUIRES_AN_EAGLE_S_WINGS;
	
	@ClientString(id = 1800413, message = "As the dragon flies high in the sky, your goals and dreams may come true.")
	public static NpcStringId AS_THE_DRAGON_FLIES_HIGH_IN_THE_SKY_YOUR_GOALS_AND_DREAMS_MAY_COME_TRUE;
	
	@ClientString(id = 1800414, message = "Luck enters into your work, hobby, family and love.")
	public static NpcStringId LUCK_ENTERS_INTO_YOUR_WORK_HOBBY_FAMILY_AND_LOVE;
	
	@ClientString(id = 1800415, message = "Whatever you do, it will accompany winning.")
	public static NpcStringId WHATEVER_YOU_DO_IT_WILL_ACCOMPANY_WINNING;
	
	@ClientString(id = 1800416, message = "It's as good as it gets with unforeseen fortune rolling your way.")
	public static NpcStringId IT_S_AS_GOOD_AS_IT_GETS_WITH_UNFORESEEN_FORTUNE_ROLLING_YOUR_WAY;
	
	@ClientString(id = 1800417, message = "A greedy act with no prudence will bring a surprise at the end.")
	public static NpcStringId A_GREEDY_ACT_WITH_NO_PRUDENCE_WILL_BRING_A_SURPRISE_AT_THE_END;
	
	@ClientString(id = 1800418, message = "Think carefully and act with caution at all times.")
	public static NpcStringId THINK_CAREFULLY_AND_ACT_WITH_CAUTION_AT_ALL_TIMES;
	
	@ClientString(id = 1800419, message = "If a tree doesn't have its roots, there will be no fruit.")
	public static NpcStringId IF_A_TREE_DOESN_T_HAVE_ITS_ROOTS_THERE_WILL_BE_NO_FRUIT;
	
	@ClientString(id = 1800420, message = "Hard work doesn't bear fruit.")
	public static NpcStringId HARD_WORK_DOESN_T_BEAR_FRUIT;
	
	@ClientString(id = 1800421, message = "Financial difficulties may bring an ordeal.")
	public static NpcStringId FINANCIAL_DIFFICULTIES_MAY_BRING_AN_ORDEAL;
	
	@ClientString(id = 1800422, message = "What used to be well managed may stumble one after another.")
	public static NpcStringId WHAT_USED_TO_BE_WELL_MANAGED_MAY_STUMBLE_ONE_AFTER_ANOTHER;
	
	@ClientString(id = 1800423, message = "A feeling of frustration may follow disappointment.")
	public static NpcStringId A_FEELING_OF_FRUSTRATION_MAY_FOLLOW_DISAPPOINTMENT;
	
	@ClientString(id = 1800424, message = "Be cautioned as unharnessed behavior at difficult times can ruin relationships.")
	public static NpcStringId BE_CAUTIONED_AS_UNHARNESSED_BEHAVIOR_AT_DIFFICULT_TIMES_CAN_RUIN_RELATIONSHIPS;
	
	@ClientString(id = 1800425, message = "Curtail greed and be grateful for small returns as modesty is needed.")
	public static NpcStringId CURTAIL_GREED_AND_BE_GRATEFUL_FOR_SMALL_RETURNS_AS_MODESTY_IS_NEEDED;
	
	@ClientString(id = 1800426, message = "The person that came under your wings will leave.")
	public static NpcStringId THE_PERSON_THAT_CAME_UNDER_YOUR_WINGS_WILL_LEAVE;
	
	@ClientString(id = 1800427, message = "Your work and relationship with colleagues will be well managed if you maintain your devotion.")
	public static NpcStringId YOUR_WORK_AND_RELATIONSHIP_WITH_COLLEAGUES_WILL_BE_WELL_MANAGED_IF_YOU_MAINTAIN_YOUR_DEVOTION;
	
	@ClientString(id = 1800428, message = "Calculating your profit in relationships without displaying any courteous manners will bring malicious gossip and ruin your value.")
	public static NpcStringId CALCULATING_YOUR_PROFIT_IN_RELATIONSHIPS_WITHOUT_DISPLAYING_ANY_COURTEOUS_MANNERS_WILL_BRING_MALICIOUS_GOSSIP_AND_RUIN_YOUR_VALUE;
	
	@ClientString(id = 1800429, message = "Consider other's situations and treat them sincerely at all times.")
	public static NpcStringId CONSIDER_OTHER_S_SITUATIONS_AND_TREAT_THEM_SINCERELY_AT_ALL_TIMES;
	
	@ClientString(id = 1800430, message = "Do not loosen up with your precautions.")
	public static NpcStringId DO_NOT_LOOSEN_UP_WITH_YOUR_PRECAUTIONS;
	
	@ClientString(id = 1800431, message = "Reflect other's opinions as a mistake always lies ahead of an arbitrary decision.")
	public static NpcStringId REFLECT_OTHER_S_OPINIONS_AS_A_MISTAKE_ALWAYS_LIES_AHEAD_OF_AN_ARBITRARY_DECISION;
	
	@ClientString(id = 1800432, message = "A blind man goes right through the door.")
	public static NpcStringId A_BLIND_MAN_GOES_RIGHT_THROUGH_THE_DOOR;
	
	@ClientString(id = 1800433, message = "A heart falls into hopelessness as things are in disarray.")
	public static NpcStringId A_HEART_FALLS_INTO_HOPELESSNESS_AS_THINGS_ARE_IN_DISARRAY;
	
	@ClientString(id = 1800434, message = "Hopelessness may fill your heart as your work falls into a maze.")
	public static NpcStringId HOPELESSNESS_MAY_FILL_YOUR_HEART_AS_YOUR_WORK_FALLS_INTO_A_MAZE;
	
	@ClientString(id = 1800435, message = "Difficulties lie ahead of an unforeseen problem even with your hard work.")
	public static NpcStringId DIFFICULTIES_LIE_AHEAD_OF_AN_UNFORESEEN_PROBLEM_EVEN_WITH_YOUR_HARD_WORK;
	
	@ClientString(id = 1800436, message = "There may be more occasions you will want to ask favors from others as you lose confidence in you.")
	public static NpcStringId THERE_MAY_BE_MORE_OCCASIONS_YOU_WILL_WANT_TO_ASK_FAVORS_FROM_OTHERS_AS_YOU_LOSE_CONFIDENCE_IN_YOU;
	
	@ClientString(id = 1800437, message = "Be brave and ambitious as no bird can fly into the sky by staying in their nest.")
	public static NpcStringId BE_BRAVE_AND_AMBITIOUS_AS_NO_BIRD_CAN_FLY_INTO_THE_SKY_BY_STAYING_IN_THEIR_NEST;
	
	@ClientString(id = 1800438, message = "It's a good idea not to start an unclear tasks and always look for someone you can trust and rely upon.")
	public static NpcStringId IT_S_A_GOOD_IDEA_NOT_TO_START_AN_UNCLEAR_TASK_AND_ALWAYS_LOOK_FOR_SOMEONE_YOU_CAN_TRUST_AND_RELY_UPON;
	
	@ClientString(id = 1800439, message = "Hunting won't be successful as the falcon lacks its claws.")
	public static NpcStringId HUNTING_WON_T_BE_SUCCESSFUL_AS_THE_FALCON_LACKS_ITS_CLAWS;
	
	@ClientString(id = 1800440, message = "A prepared plan won't move smoothly.")
	public static NpcStringId A_PREPARED_PLAN_WON_T_MOVE_SMOOTHLY;
	
	@ClientString(id = 1800441, message = "An easy tasks may fail if one is consumed by greed and overconfidence.")
	public static NpcStringId AN_EASY_TASK_MAY_FAIL_IF_ONE_IS_CONSUMED_BY_GREED_AND_OVERCONFIDENCE;
	
	@ClientString(id = 1800442, message = "Impatience may lie ahead as the situation is unfavorable.")
	public static NpcStringId IMPATIENCE_MAY_LIE_AHEAD_AS_THE_SITUATION_IS_UNFAVORABLE;
	
	@ClientString(id = 1800443, message = "Thoughtful foresight is needed before a disaster may fall upon you.")
	public static NpcStringId THOUGHTFUL_FORESIGHT_IS_NEEDED_BEFORE_A_DISASTER_MAY_FALL_UPON_YOU;
	
	@ClientString(id = 1800444, message = "Refrain from dictatorial acts as caring for others around you with dignity is much needed.")
	public static NpcStringId REFRAIN_FROM_DICTATORIAL_ACTS_AS_CARING_FOR_OTHERS_AROUND_YOU_WITH_DIGNITY_IS_MUCH_NEEDED;
	
	@ClientString(id = 1800445, message = "Things are messy with no good sign.")
	public static NpcStringId THINGS_ARE_MESSY_WITH_NO_GOOD_SIGN;
	
	@ClientString(id = 1800446, message = "You may fall into a vexing situation as bad circumstances will arise.")
	public static NpcStringId YOU_MAY_FALL_INTO_A_VEXING_SITUATION_AS_BAD_CIRCUMSTANCES_WILL_ARISE;
	
	@ClientString(id = 1800447, message = "Relationships with people may be contrary to your expectations.")
	public static NpcStringId RELATIONSHIPS_WITH_PEOPLE_MAY_BE_CONTRARY_TO_YOUR_EXPECTATIONS;
	
	@ClientString(id = 1800448, message = "Do not seek a quick fix as the problem needs a fundamental resolution.")
	public static NpcStringId DO_NOT_SEEK_A_QUICK_FIX_AS_THE_PROBLEM_NEEDS_A_FUNDAMENTAL_RESOLUTION;
	
	@ClientString(id = 1800449, message = "Seek peace in your heart as vulgar display of your emotions may harm you.")
	public static NpcStringId SEEK_PEACE_IN_YOUR_HEART_AS_VULGAR_DISPLAY_OF_YOUR_EMOTIONS_MAY_HARM_YOU;
	
	@ClientString(id = 1800450, message = "Information for success may come from the conversations with people around you.")
	public static NpcStringId INFORMATION_FOR_SUCCESS_MAY_COME_FROM_THE_CONVERSATIONS_WITH_PEOPLE_AROUND_YOU;
	
	@ClientString(id = 1800451, message = "Be confident and act reliantly at all times.")
	public static NpcStringId BE_CONFIDENT_AND_ACT_RELIANTLY_AT_ALL_TIMES;
	
	@ClientString(id = 1800452, message = "A child gets a treasure.")
	public static NpcStringId A_CHILD_GETS_A_TREASURE;
	
	@ClientString(id = 1800453, message = "Good fortune and opportunity may lie ahead as if one's born in a golden spoon.")
	public static NpcStringId GOOD_FORTUNE_AND_OPPORTUNITY_MAY_LIE_AHEAD_AS_IF_ONE_S_BORN_IN_A_GOLDEN_SPOON;
	
	@ClientString(id = 1800454, message = "Your life flows as if it's on a silk surface and unexpected fortune and success may come to you.")
	public static NpcStringId YOUR_LIFE_FLOWS_AS_IF_IT_S_ON_A_SILK_SURFACE_AND_UNEXPECTED_FORTUNE_AND_SUCCESS_MAY_COME_TO_YOU;
	
	@ClientString(id = 1800455, message = "Temporary luck may come to you with no effort.")
	public static NpcStringId TEMPORARY_LUCK_MAY_COME_TO_YOU_WITH_NO_EFFORT;
	
	@ClientString(id = 1800456, message = "Plan ahead with patience but execute with swiftness.")
	public static NpcStringId PLAN_AHEAD_WITH_PATIENCE_BUT_EXECUTE_WITH_SWIFTNESS;
	
	@ClientString(id = 1800457, message = "The abilities to amend, foresee and analyze may raise your value.")
	public static NpcStringId THE_ABILITIES_TO_AMEND_FORESEE_AND_ANALYZE_MAY_RAISE_YOUR_VALUE;
	
	@ClientString(id = 1800458, message = "Bigger mistakes will be on the road if you fail to correct a small mistake.")
	public static NpcStringId BIGGER_MISTAKES_WILL_BE_ON_THE_ROAD_IF_YOU_FAIL_TO_CORRECT_A_SMALL_MISTAKE;
	
	@ClientString(id = 1800459, message = "Don't be evasive to accept new findings or experiences.")
	public static NpcStringId DON_T_BE_EVASIVE_TO_ACCEPT_NEW_FINDINGS_OR_EXPERIENCES;
	
	@ClientString(id = 1800460, message = "Don't be irritated as the situations don't move as planned.")
	public static NpcStringId DON_T_BE_IRRITATED_AS_THE_SITUATIONS_DON_T_MOVE_AS_PLANNED;
	
	@ClientString(id = 1800461, message = "Be warned as you may be overwhelmed by surroundings if you lack a clear opinion.")
	public static NpcStringId BE_WARNED_AS_YOU_MAY_BE_OVERWHELMED_BY_SURROUNDINGS_IF_YOU_LACK_A_CLEAR_OPINION;
	
	@ClientString(id = 1800462, message = "You may live an affluent life even without possessions.")
	public static NpcStringId YOU_MAY_LIVE_AN_AFFLUENT_LIFE_EVEN_WITHOUT_POSSESSIONS;
	
	@ClientString(id = 1800463, message = "You will gain popularity as you help people with money you earnestly earned.")
	public static NpcStringId YOU_WILL_GAIN_POPULARITY_AS_YOU_HELP_PEOPLE_WITH_MONEY_YOU_EARNESTLY_EARNED;
	
	@ClientString(id = 1800464, message = "Your heart and body may be in health.")
	public static NpcStringId YOUR_HEART_AND_BODY_MAY_BE_IN_HEALTH;
	
	@ClientString(id = 1800465, message = "Be warned as you may be dragged to an unwanted direction if not cautious.")
	public static NpcStringId BE_WARNED_AS_YOU_MAY_BE_DRAGGED_TO_AN_UNWANTED_DIRECTION_IF_NOT_CAUTIOUS;
	
	@ClientString(id = 1800466, message = "You may meet many new people but it will be difficult to find a perfect person who wins your heart.")
	public static NpcStringId YOU_MAY_MEET_MANY_NEW_PEOPLE_BUT_IT_WILL_BE_DIFFICULT_TO_FIND_A_PERFECT_PERSON_WHO_WINS_YOUR_HEART;
	
	@ClientString(id = 1800467, message = "There may be an occasion where you are consoled by people.")
	public static NpcStringId THERE_MAY_BE_AN_OCCASION_WHERE_YOU_ARE_CONSOLED_BY_PEOPLE;
	
	@ClientString(id = 1800468, message = "It may not be a good time for a change even if there's tedium in daily life.")
	public static NpcStringId IT_MAY_NOT_BE_A_GOOD_TIME_FOR_A_CHANGE_EVEN_IF_THERE_S_TEDIUM_IN_DAILY_LIFE;
	
	@ClientString(id = 1800469, message = "The money you spend for yourself may act as an investment and bring you a return.")
	public static NpcStringId THE_MONEY_YOU_SPEND_FOR_YOURSELF_MAY_ACT_AS_AN_INVESTMENT_AND_BRING_YOU_A_RETURN;
	
	@ClientString(id = 1800470, message = "The money you spend for others will be wasted so be cautious.")
	public static NpcStringId THE_MONEY_YOU_SPEND_FOR_OTHERS_WILL_BE_WASTED_SO_BE_CAUTIOUS;
	
	@ClientString(id = 1800471, message = "Be warned so as not to have unnecessary expenses.")
	public static NpcStringId BE_WARNED_SO_AS_NOT_TO_HAVE_UNNECESSARY_EXPENSES;
	
	@ClientString(id = 1800472, message = "Your star indicated such good luck, participate in bonus giveaways or events.")
	public static NpcStringId YOUR_STAR_INDICATED_SUCH_GOOD_LUCK_PARTICIPATE_IN_BONUS_GIVEAWAYS_OR_EVENTS;
	
	@ClientString(id = 1800473, message = "You may grab unexpected luck.")
	public static NpcStringId YOU_MAY_GRAB_UNEXPECTED_LUCK;
	
	@ClientString(id = 1800474, message = "The person in your heart may naturally come to you.")
	public static NpcStringId THE_PERSON_IN_YOUR_HEART_MAY_NATURALLY_COME_TO_YOU;
	
	@ClientString(id = 1800475, message = "There will be a good result if you keep your own pace regardless of others' judgment.")
	public static NpcStringId THERE_WILL_BE_A_GOOD_RESULT_IF_YOU_KEEP_YOUR_OWN_PACE_REGARDLESS_OF_OTHERS_JUDGMENT;
	
	@ClientString(id = 1800476, message = "Be warned as unexpected luck may be wasted with your reckless comments.")
	public static NpcStringId BE_WARNED_AS_UNEXPECTED_LUCK_MAY_BE_WASTED_WITH_YOUR_RECKLESS_COMMENTS;
	
	@ClientString(id = 1800477, message = "Overconfidence will convince you to carry a tasks above your reach and there will be consequences.")
	public static NpcStringId OVERCONFIDENCE_WILL_CONVINCE_YOU_TO_CARRY_A_TASK_ABOVE_YOUR_REACH_AND_THERE_WILL_BE_CONSEQUENCES;
	
	@ClientString(id = 1800478, message = "Momentarily delay an important decision.")
	public static NpcStringId MOMENTARILY_DELAY_AN_IMPORTANT_DECISION;
	
	@ClientString(id = 1800479, message = "Trouble spots lie ahead when talking to superiors or people close to you.")
	public static NpcStringId TROUBLE_SPOTS_LIE_AHEAD_WHEN_TALKING_TO_SUPERIORS_OR_PEOPLE_CLOSE_TO_YOU;
	
	@ClientString(id = 1800480, message = "Be warned as your words can hurt others or others' words can hurt you.")
	public static NpcStringId BE_WARNED_AS_YOUR_WORDS_CAN_HURT_OTHERS_OR_OTHERS_WORDS_CAN_HURT_YOU;
	
	@ClientString(id = 1800481, message = "Make a loud boast and you may have to pay to cover unnecessary expenses.")
	public static NpcStringId MAKE_A_LOUD_BOAST_AND_YOU_MAY_HAVE_TO_PAY_TO_COVER_UNNECESSARY_EXPENSES;
	
	@ClientString(id = 1800482, message = "Skillful evasion is needed when dealing with people who pick fights as a disaster may arise from it.")
	public static NpcStringId SKILLFUL_EVASION_IS_NEEDED_WHEN_DEALING_WITH_PEOPLE_WHO_PICK_FIGHTS_AS_A_DISASTER_MAY_ARISE_FROM_IT;
	
	@ClientString(id = 1800483, message = "Keep a low profile as too strong an opinion will attract adverse reactions.")
	public static NpcStringId KEEP_A_LOW_PROFILE_AS_TOO_STRONG_AN_OPINION_WILL_ATTRACT_ADVERSE_REACTIONS;
	
	@ClientString(id = 1800484, message = "Do not unnecessarily provoke misunderstanding as you may be involved in malicious gossip.")
	public static NpcStringId DO_NOT_UNNECESSARILY_PROVOKE_MISUNDERSTANDING_AS_YOU_MAY_BE_INVOLVED_IN_MALICIOUS_GOSSIP;
	
	@ClientString(id = 1800485, message = "Check your belongings as you may lose what you possess.")
	public static NpcStringId CHECK_YOUR_BELONGINGS_AS_YOU_MAY_LOSE_WHAT_YOU_POSSESS;
	
	@ClientString(id = 1800486, message = "Be flexible enough to play up to others.")
	public static NpcStringId BE_FLEXIBLE_ENOUGH_TO_PLAY_UP_TO_OTHERS;
	
	@ClientString(id = 1800487, message = "Pay special attention when meeting or talking to people as relationships may go amiss.")
	public static NpcStringId PAY_SPECIAL_ATTENTION_WHEN_MEETING_OR_TALKING_TO_PEOPLE_AS_RELATIONSHIPS_MAY_GO_AMISS;
	
	@ClientString(id = 1800488, message = "When the important moment arrives, decide upon what you truly want without measuring others' judgment.")
	public static NpcStringId WHEN_THE_IMPORTANT_MOMENT_ARRIVES_DECIDE_UPON_WHAT_YOU_TRULY_WANT_WITHOUT_MEASURING_OTHERS_JUDGMENT;
	
	@ClientString(id = 1800489, message = "Luck will always follow you if you travel and read many books.")
	public static NpcStringId LUCK_WILL_ALWAYS_FOLLOW_YOU_IF_YOU_TRAVEL_AND_READ_MANY_BOOKS;
	
	@ClientString(id = 1800490, message = "Head to a place that needs your advice as good ideas and wisdom will flourish.")
	public static NpcStringId HEAD_TO_A_PLACE_THAT_NEEDS_YOUR_ADVICE_AS_GOOD_IDEAS_AND_WISDOM_WILL_FLOURISH;
	
	@ClientString(id = 1800491, message = "Someone's life may change upon your advice.")
	public static NpcStringId SOMEONE_S_LIFE_MAY_CHANGE_UPON_YOUR_ADVICE;
	
	@ClientString(id = 1800492, message = "It's a proper time to plan for the future rather than a short term plan.")
	public static NpcStringId IT_S_A_PROPER_TIME_TO_PLAN_FOR_THE_FUTURE_RATHER_THAN_A_SHORT_TERM_PLAN;
	
	@ClientString(id = 1800493, message = "Many thoughtful plans at present time will be of great help in the future.")
	public static NpcStringId MANY_THOUGHTFUL_PLANS_AT_PRESENT_TIME_WILL_BE_OF_GREAT_HELP_IN_THE_FUTURE;
	
	@ClientString(id = 1800494, message = "Patience may be needed as a big quarrel arises between you and a person close to you.")
	public static NpcStringId PATIENCE_MAY_BE_NEEDED_AS_A_BIG_QUARREL_ARISES_BETWEEN_YOU_AND_A_PERSON_CLOSE_TO_YOU;
	
	@ClientString(id = 1800495, message = "Do not ask for financial help when the time is difficult. Your pride will be hurt without gaining any money.")
	public static NpcStringId DO_NOT_ASK_FOR_FINANCIAL_HELP_WHEN_THE_TIME_IS_DIFFICULT_YOUR_PRIDE_WILL_BE_HURT_WITHOUT_GAINING_ANY_MONEY;
	
	@ClientString(id = 1800496, message = "Connection with a special person starts with a mere incident.")
	public static NpcStringId CONNECTION_WITH_A_SPECIAL_PERSON_STARTS_WITH_A_MERE_INCIDENT;
	
	@ClientString(id = 1800497, message = "Stubbornness regardless of the matter will only bear danger.")
	public static NpcStringId STUBBORNNESS_REGARDLESS_OF_THE_MATTER_WILL_ONLY_BEAR_DANGER;
	
	@ClientString(id = 1800498, message = "Keep good manners and value taciturnity as light-heartedness may bring misfortune.")
	public static NpcStringId KEEP_GOOD_MANNERS_AND_VALUE_TACITURNITY_AS_LIGHT_HEARTEDNESS_MAY_BRING_MISFORTUNE;
	
	@ClientString(id = 1800499, message = "You may meet the opposite sex.")
	public static NpcStringId YOU_MAY_MEET_THE_OPPOSITE_SEX;
	
	@ClientString(id = 1800500, message = "Greed by wanting to take wealth may bring unfortunate disaster.")
	public static NpcStringId GREED_BY_WANTING_TO_TAKE_WEALTH_MAY_BRING_UNFORTUNATE_DISASTER;
	
	@ClientString(id = 1800501, message = "Loss is ahead, refrain from investing. Try to save the money in your pockets.")
	public static NpcStringId LOSS_IS_AHEAD_REFRAIN_FROM_INVESTING_TRY_TO_SAVE_THE_MONEY_IN_YOUR_POCKETS;
	
	@ClientString(id = 1800502, message = "Your wealth luck is dim, avoid any offers.")
	public static NpcStringId YOUR_WEALTH_LUCK_IS_DIM_AVOID_ANY_OFFERS;
	
	@ClientString(id = 1800503, message = "A bigger challenge may be when delaying today's work.")
	public static NpcStringId A_BIGGER_CHALLENGE_MAY_BE_WHEN_DELAYING_TODAY_S_WORK;
	
	@ClientString(id = 1800504, message = "There will be difficulty, but a good result may be ahead when facing it responsibly.")
	public static NpcStringId THERE_WILL_BE_DIFFICULTY_BUT_A_GOOD_RESULT_MAY_BE_AHEAD_WHEN_FACING_IT_RESPONSIBLY;
	
	@ClientString(id = 1800505, message = "Even with some difficulties, expand the range of your scope where you are in charge. It will return to you as help.")
	public static NpcStringId EVEN_WITH_SOME_DIFFICULTIES_EXPAND_THE_RANGE_OF_YOUR_SCOPE_WHERE_YOU_ARE_IN_CHARGE_IT_WILL_RETURN_TO_YOU_AS_HELP;
	
	@ClientString(id = 1800506, message = "Focus on maintaining organized surroundings to help reduce your losses.")
	public static NpcStringId FOCUS_ON_MAINTAINING_ORGANIZED_SURROUNDINGS_TO_HELP_REDUCE_YOUR_LOSSES;
	
	@ClientString(id = 1800507, message = "Luck lies ahead when waiting for people rather than following them.")
	public static NpcStringId LUCK_LIES_AHEAD_WHEN_WAITING_FOR_PEOPLE_RATHER_THAN_FOLLOWING_THEM;
	
	@ClientString(id = 1800508, message = "Do not offer your hand first even when things are hasty. The relationship may fall apart.")
	public static NpcStringId DO_NOT_OFFER_YOUR_HAND_FIRST_EVEN_WHEN_THINGS_ARE_HASTY_THE_RELATIONSHIP_MAY_FALL_APART;
	
	@ClientString(id = 1800509, message = "Your wealth luck is rising, there will be some good result.")
	public static NpcStringId YOUR_WEALTH_LUCK_IS_RISING_THERE_WILL_BE_SOME_GOOD_RESULT;
	
	@ClientString(id = 1800510, message = "You may fall in danger each time when acting upon improvisation.")
	public static NpcStringId YOU_MAY_FALL_IN_DANGER_EACH_TIME_WHEN_ACTING_UPON_IMPROVISATION;
	
	@ClientString(id = 1800511, message = "Be warned as a childishly act before elders may ruin everything.")
	public static NpcStringId BE_WARNED_AS_A_CHILDISHLY_ACT_BEFORE_ELDERS_MAY_RUIN_EVERYTHING;
	
	@ClientString(id = 1800512, message = "Things will move effortlessly but luck will vanish with your audacity.")
	public static NpcStringId THINGS_WILL_MOVE_EFFORTLESSLY_BUT_LUCK_WILL_VANISH_WITH_YOUR_AUDACITY;
	
	@ClientString(id = 1800513, message = "Luck may be continued only when humility is maintained after success.")
	public static NpcStringId LUCK_MAY_BE_CONTINUED_ONLY_WHEN_HUMILITY_IS_MAINTAINED_AFTER_SUCCESS;
	
	@ClientString(id = 1800514, message = "A new person may appear to create a love triangle.")
	public static NpcStringId A_NEW_PERSON_MAY_APPEAR_TO_CREATE_A_LOVE_TRIANGLE;
	
	@ClientString(id = 1800515, message = "Look for someone with a similar style. It will open up for the good.")
	public static NpcStringId LOOK_FOR_SOMEONE_WITH_A_SIMILAR_STYLE_IT_WILL_OPEN_UP_FOR_THE_GOOD;
	
	@ClientString(id = 1800516, message = "An offer may soon be made to collaborate a tasks but delaying it will be a good idea.")
	public static NpcStringId AN_OFFER_MAY_SOON_BE_MADE_TO_COLLABORATE_A_TASK_BUT_DELAYING_IT_WILL_BE_A_GOOD_IDEA;
	
	@ClientString(id = 1800517, message = "Partnership is out of luck, avoid someone who rushes you to start a collaboration.")
	public static NpcStringId PARTNERSHIP_IS_OUT_OF_LUCK_AVOID_SOMEONE_WHO_RUSHES_YOU_TO_START_A_COLLABORATION;
	
	@ClientString(id = 1800518, message = "Focus on networking with like-minded people. They may join you for a big mission in the future.")
	public static NpcStringId FOCUS_ON_NETWORKING_WITH_LIKE_MINDED_PEOPLE_THEY_MAY_JOIN_YOU_FOR_A_BIG_MISSION_IN_THE_FUTURE;
	
	@ClientString(id = 1800519, message = "Be warned when someone says you are innocent as that's not a compliment.")
	public static NpcStringId BE_WARNED_WHEN_SOMEONE_SAYS_YOU_ARE_INNOCENT_AS_THAT_S_NOT_A_COMPLIMENT;
	
	@ClientString(id = 1800520, message = "You may be scammed. Be cautious as there may be a big loss by underestimating others.")
	public static NpcStringId YOU_MAY_BE_SCAMMED_BE_CAUTIOUS_AS_THERE_MAY_BE_A_BIG_LOSS_BY_UNDERESTIMATING_OTHERS;
	
	@ClientString(id = 1800521, message = "Luck at decision-making is dim, avoid subjective conclusions and rely on universal common-sense.")
	public static NpcStringId LUCK_AT_DECISION_MAKING_IS_DIM_AVOID_SUBJECTIVE_CONCLUSIONS_AND_RELY_ON_UNIVERSAL_COMMON_SENSE;
	
	@ClientString(id = 1800522, message = "Your weakness may invite hardships, cautiously take a strong position as needed.")
	public static NpcStringId YOUR_WEAKNESS_MAY_INVITE_HARDSHIPS_CAUTIOUSLY_TAKE_A_STRONG_POSITION_AS_NEEDED;
	
	@ClientString(id = 1800523, message = "Be wary of someone who talks and entertains too much. The person may bring you misfortune.")
	public static NpcStringId BE_WARY_OF_SOMEONE_WHO_TALKS_AND_ENTERTAINS_TOO_MUCH_THE_PERSON_MAY_BRING_YOU_MISFORTUNE;
	
	@ClientString(id = 1800524, message = "You may enjoy a beginner's luck.")
	public static NpcStringId YOU_MAY_ENJOY_A_BEGINNER_S_LUCK;
	
	@ClientString(id = 1800525, message = "Your wealth luck is strong but you should know when to withdraw.")
	public static NpcStringId YOUR_WEALTH_LUCK_IS_STRONG_BUT_YOU_SHOULD_KNOW_WHEN_TO_WITHDRAW;
	
	@ClientString(id = 1800526, message = "Already acquired wealth can be lost by greed.")
	public static NpcStringId ALREADY_ACQUIRED_WEALTH_CAN_BE_LOST_BY_GREED;
	
	@ClientString(id = 1800527, message = "Even if you can complete it by yourself, it's a good idea to have someone help you.")
	public static NpcStringId EVEN_IF_YOU_CAN_COMPLETE_IT_BY_YOURSELF_IT_S_A_GOOD_IDEA_TO_HAVE_SOMEONE_HELP_YOU;
	
	@ClientString(id = 1800528, message = "Make harmony with people the priority. Stubbornness may bring hardships.")
	public static NpcStringId MAKE_HARMONY_WITH_PEOPLE_THE_PRIORITY_STUBBORNNESS_MAY_BRING_HARDSHIPS;
	
	@ClientString(id = 1800529, message = "There may be a chance when you can see a new aspect of a close friend.")
	public static NpcStringId THERE_MAY_BE_A_CHANCE_WHEN_YOU_CAN_SEE_A_NEW_ASPECT_OF_A_CLOSE_FRIEND;
	
	@ClientString(id = 1800530, message = "Try to be close to someone different from you without any stereotypical judgment.")
	public static NpcStringId TRY_TO_BE_CLOSE_TO_SOMEONE_DIFFERENT_FROM_YOU_WITHOUT_ANY_STEREOTYPICAL_JUDGMENT;
	
	@ClientString(id = 1800531, message = "Good luck in becoming a leader with many followers. However, it'll only be after hard work.")
	public static NpcStringId GOOD_LUCK_IN_BECOMING_A_LEADER_WITH_MANY_FOLLOWERS_HOWEVER_IT_LL_ONLY_BE_AFTER_HARD_WORK;
	
	@ClientString(id = 1800532, message = "Your wealth luck is rising, expenditures will be followed by substantial income as you are able to sustain.")
	public static NpcStringId YOUR_WEALTH_LUCK_IS_RISING_EXPENDITURES_WILL_BE_FOLLOWED_BY_SUBSTANTIAL_INCOME_AS_YOU_ARE_ABLE_TO_SUSTAIN;
	
	@ClientString(id = 1800533, message = "Be cautious as your wealth luck can be either very good or very bad.")
	public static NpcStringId BE_CAUTIOUS_AS_YOUR_WEALTH_LUCK_CAN_BE_EITHER_VERY_GOOD_OR_VERY_BAD;
	
	@ClientString(id = 1800534, message = "Be warned as a small argument can distance you from a close friend.")
	public static NpcStringId BE_WARNED_AS_A_SMALL_ARGUMENT_CAN_DISTANCE_YOU_FROM_A_CLOSE_FRIEND;
	
	@ClientString(id = 1800535, message = "There is luck in love with a new person.")
	public static NpcStringId THERE_IS_LUCK_IN_LOVE_WITH_A_NEW_PERSON;
	
	@ClientString(id = 1800536, message = "A bigger fortune will be followed by your good deed.")
	public static NpcStringId A_BIGGER_FORTUNE_WILL_BE_FOLLOWED_BY_YOUR_GOOD_DEED;
	
	@ClientString(id = 1800537, message = "There may be a relationship breaking, try to eliminate misunderstandings.")
	public static NpcStringId THERE_MAY_BE_A_RELATIONSHIP_BREAKING_TRY_TO_ELIMINATE_MISUNDERSTANDINGS;
	
	@ClientString(id = 1800538, message = "Be cautious not to be emotionally moved even if it's convincing.")
	public static NpcStringId BE_CAUTIOUS_NOT_TO_BE_EMOTIONALLY_MOVED_EVEN_IF_IT_S_CONVINCING;
	
	@ClientString(id = 1800539, message = "Smiling will bring good luck.")
	public static NpcStringId SMILING_WILL_BRING_GOOD_LUCK;
	
	@ClientString(id = 1800540, message = "It's a good idea to let go of a small loss.")
	public static NpcStringId IT_S_A_GOOD_IDEA_TO_LET_GO_OF_A_SMALL_LOSS;
	
	@ClientString(id = 1800541, message = "Conveying your own truth may be difficult and easy misunderstandings will follow.")
	public static NpcStringId CONVEYING_YOUR_OWN_TRUTH_MAY_BE_DIFFICULT_AND_EASY_MISUNDERSTANDINGS_WILL_FOLLOW;
	
	@ClientString(id = 1800542, message = "There is good luck in a place with many people.")
	public static NpcStringId THERE_IS_GOOD_LUCK_IN_A_PLACE_WITH_MANY_PEOPLE;
	
	@ClientString(id = 1800543, message = "Try to avoid directness if you can.")
	public static NpcStringId TRY_TO_AVOID_DIRECTNESS_IF_YOU_CAN;
	
	@ClientString(id = 1800544, message = "Value substance opposed to the sake honor and look beyond what's in front of you.")
	public static NpcStringId VALUE_SUBSTANCE_OPPOSED_TO_THE_SAKE_HONOR_AND_LOOK_BEYOND_WHAT_S_IN_FRONT_OF_YOU;
	
	@ClientString(id = 1800545, message = "Expanding a relationship with humor may be a good idea.")
	public static NpcStringId EXPANDING_A_RELATIONSHIP_WITH_HUMOR_MAY_BE_A_GOOD_IDEA;
	
	@ClientString(id = 1800546, message = "An enjoyable event may be ahead if you accept a simple bet.")
	public static NpcStringId AN_ENJOYABLE_EVENT_MAY_BE_AHEAD_IF_YOU_ACCEPT_A_SIMPLE_BET;
	
	@ClientString(id = 1800547, message = "Being level-headed not focusing on emotions may help with relationships.")
	public static NpcStringId BEING_LEVEL_HEADED_NOT_FOCUSING_ON_EMOTIONS_MAY_HELP_WITH_RELATIONSHIPS;
	
	@ClientString(id = 1800548, message = "It's a good idea to take care of matters in sequential order without measuring their importance.")
	public static NpcStringId IT_S_A_GOOD_IDEA_TO_TAKE_CARE_OF_MATTERS_IN_SEQUENTIAL_ORDER_WITHOUT_MEASURING_THEIR_IMPORTANCE;
	
	@ClientString(id = 1800549, message = "A determined act after prepared research will attract people.")
	public static NpcStringId A_DETERMINED_ACT_AFTER_PREPARED_RESEARCH_WILL_ATTRACT_PEOPLE;
	
	@ClientString(id = 1800550, message = "A little humor may bring complete attention to you.")
	public static NpcStringId A_LITTLE_HUMOR_MAY_BRING_COMPLETE_ATTENTION_TO_YOU;
	
	@ClientString(id = 1800551, message = "It may not be a good time for an important decision, be wary of temptations and avoid monetary dealings.")
	public static NpcStringId IT_MAY_NOT_BE_A_GOOD_TIME_FOR_AN_IMPORTANT_DECISION_BE_WARY_OF_TEMPTATIONS_AND_AVOID_MONETARY_DEALINGS;
	
	@ClientString(id = 1800552, message = "Pay special attention to advice from a close friend.")
	public static NpcStringId PAY_SPECIAL_ATTENTION_TO_ADVICE_FROM_A_CLOSE_FRIEND;
	
	@ClientString(id = 1800553, message = "There may be moderate solutions to every problem when they're viewed from a 3rd party's point of view.")
	public static NpcStringId THERE_MAY_BE_MODERATE_SOLUTIONS_TO_EVERY_PROBLEM_WHEN_THEY_RE_VIEWED_FROM_A_3RD_PARTY_S_POINT_OF_VIEW;
	
	@ClientString(id = 1800554, message = "Dealings with close friends only bring frustration and headache, politely decline and mention another chance.")
	public static NpcStringId DEALINGS_WITH_CLOSE_FRIENDS_ONLY_BRING_FRUSTRATION_AND_HEADACHE_POLITELY_DECLINE_AND_MENTION_ANOTHER_CHANCE;
	
	@ClientString(id = 1800555, message = "There may be a problem at completion if the basic matters are not considered from the beginning.")
	public static NpcStringId THERE_MAY_BE_A_PROBLEM_AT_COMPLETION_IF_THE_BASIC_MATTERS_ARE_NOT_CONSIDERED_FROM_THE_BEGINNING;
	
	@ClientString(id = 1800556, message = "Distinguishing business from a private matter is needed to succeed.")
	public static NpcStringId DISTINGUISHING_BUSINESS_FROM_A_PRIVATE_MATTER_IS_NEEDED_TO_SUCCEED;
	
	@ClientString(id = 1800557, message = "A change in rules may be helpful when problems are persistent.")
	public static NpcStringId A_CHANGE_IN_RULES_MAY_BE_HELPFUL_WHEN_PROBLEMS_ARE_PERSISTENT;
	
	@ClientString(id = 1800558, message = "Preparing for an unforeseen situation will be difficult when small matters are ignored.")
	public static NpcStringId PREPARING_FOR_AN_UNFORESEEN_SITUATION_WILL_BE_DIFFICULT_WHEN_SMALL_MATTERS_ARE_IGNORED;
	
	@ClientString(id = 1800559, message = "Refrain from getting involved in others' business, try to be loose as a goose.")
	public static NpcStringId REFRAIN_FROM_GETTING_INVOLVED_IN_OTHERS_BUSINESS_TRY_TO_BE_LOOSE_AS_A_GOOSE;
	
	@ClientString(id = 1800560, message = "Being neutral is a good way to go, but clarity may be helpful contrary to your hesitance.")
	public static NpcStringId BEING_NEUTRAL_IS_A_GOOD_WAY_TO_GO_BUT_CLARITY_MAY_BE_HELPFUL_CONTRARY_TO_YOUR_HESITANCE;
	
	@ClientString(id = 1800561, message = "Be cautious of your own actions, the past may bring misunderstandings.")
	public static NpcStringId BE_CAUTIOUS_OF_YOUR_OWN_ACTIONS_THE_PAST_MAY_BRING_MISUNDERSTANDINGS;
	
	@ClientString(id = 1800562, message = "Pay attention to time management, emotions may waste your time.")
	public static NpcStringId PAY_ATTENTION_TO_TIME_MANAGEMENT_EMOTIONS_MAY_WASTE_YOUR_TIME;
	
	@ClientString(id = 1800563, message = "Heroism will be rewarded, but be careful not to display arrogance or lack of sincerity.")
	public static NpcStringId HEROISM_WILL_BE_REWARDED_BUT_BE_CAREFUL_NOT_TO_DISPLAY_ARROGANCE_OR_LACK_OF_SINCERITY;
	
	@ClientString(id = 1800564, message = "If you want to maintain relationship connections, offer reconciliation to those who had misunderstandings with you.")
	public static NpcStringId IF_YOU_WANT_TO_MAINTAIN_RELATIONSHIP_CONNECTIONS_OFFER_RECONCILIATION_TO_THOSE_WHO_HAD_MISUNDERSTANDINGS_WITH_YOU;
	
	@ClientString(id = 1800565, message = "Step forward to solve others' problems when they are unable.")
	public static NpcStringId STEP_FORWARD_TO_SOLVE_OTHERS_PROBLEMS_WHEN_THEY_ARE_UNABLE;
	
	@ClientString(id = 1800566, message = "There may be a little loss, but think of it as an investment for yourself.")
	public static NpcStringId THERE_MAY_BE_A_LITTLE_LOSS_BUT_THINK_OF_IT_AS_AN_INVESTMENT_FOR_YOURSELF;
	
	@ClientString(id = 1800567, message = "Avarice bears a bigger greed, being satisfied with moderation is needed.")
	public static NpcStringId AVARICE_BEARS_A_BIGGER_GREED_BEING_SATISFIED_WITH_MODERATION_IS_NEEDED;
	
	@ClientString(id = 1800568, message = "A rational analysis is needed as unplanned actions may bring criticism.")
	public static NpcStringId A_RATIONAL_ANALYSIS_IS_NEEDED_AS_UNPLANNED_ACTIONS_MAY_BRING_CRITICISM;
	
	@ClientString(id = 1800569, message = "Reflect upon your shortcomings before criticizing others.")
	public static NpcStringId REFLECT_UPON_YOUR_SHORTCOMINGS_BEFORE_CRITICIZING_OTHERS;
	
	@ClientString(id = 1800570, message = "Follow-up care is always needed after an emergency evasion.")
	public static NpcStringId FOLLOW_UP_CARE_IS_ALWAYS_NEEDED_AFTER_AN_EMERGENCY_EVASION;
	
	@ClientString(id = 1800571, message = "You may look for a new challenge but vast knowledge is required.")
	public static NpcStringId YOU_MAY_LOOK_FOR_A_NEW_CHALLENGE_BUT_VAST_KNOWLEDGE_IS_REQUIRED;
	
	@ClientString(id = 1800572, message = "When one puts aside their ego any misunderstanding will be solved.")
	public static NpcStringId WHEN_ONE_PUTS_ASIDE_THEIR_EGO_ANY_MISUNDERSTANDING_WILL_BE_SOLVED;
	
	@ClientString(id = 1800573, message = "Listen to the advice that's given to you with a humble attitude.")
	public static NpcStringId LISTEN_TO_THE_ADVICE_THAT_S_GIVEN_TO_YOU_WITH_A_HUMBLE_ATTITUDE;
	
	@ClientString(id = 1800574, message = "Equilibrium is achieved when one understands a downshift is evident after the rise.")
	public static NpcStringId EQUILIBRIUM_IS_ACHIEVED_WHEN_ONE_UNDERSTANDS_A_DOWNSHIFT_IS_EVIDENT_AFTER_THE_RISE;
	
	@ClientString(id = 1800575, message = "What you sow is what you reap, faithfully follow the plan.")
	public static NpcStringId WHAT_YOU_SOW_IS_WHAT_YOU_REAP_FAITHFULLY_FOLLOW_THE_PLAN;
	
	@ClientString(id = 1800576, message = "Meticulous preparation is needed as spontaneous actions only bear mental and monetary losses.")
	public static NpcStringId METICULOUS_PREPARATION_IS_NEEDED_AS_SPONTANEOUS_ACTIONS_ONLY_BEAR_MENTAL_AND_MONETARY_LOSSES;
	
	@ClientString(id = 1800577, message = "The right time to bear fruit is delayed while the farmer ponders opinions.")
	public static NpcStringId THE_RIGHT_TIME_TO_BEAR_FRUIT_IS_DELAYED_WHILE_THE_FARMER_PONDERS_OPINIONS;
	
	@ClientString(id = 1800578, message = "Help each other among close friends.")
	public static NpcStringId HELP_EACH_OTHER_AMONG_CLOSE_FRIENDS;
	
	@ClientString(id = 1800579, message = "Obsessing over a small profit will place people apart.")
	public static NpcStringId OBSESSING_OVER_A_SMALL_PROFIT_WILL_PLACE_PEOPLE_APART;
	
	@ClientString(id = 1800580, message = "Don't cling to the result of a gamble.")
	public static NpcStringId DON_T_CLING_TO_THE_RESULT_OF_A_GAMBLE;
	
	@ClientString(id = 1800581, message = "Small troubles and arguments are ahead, face them with a mature attitude.")
	public static NpcStringId SMALL_TROUBLES_AND_ARGUMENTS_ARE_AHEAD_FACE_THEM_WITH_A_MATURE_ATTITUDE;
	
	@ClientString(id = 1800582, message = "Neglecting a promise may put you in distress.")
	public static NpcStringId NEGLECTING_A_PROMISE_MAY_PUT_YOU_IN_DISTRESS;
	
	@ClientString(id = 1800583, message = "Delay any dealings as you may easily omit addressing what's important to you.")
	public static NpcStringId DELAY_ANY_DEALINGS_AS_YOU_MAY_EASILY_OMIT_ADDRESSING_WHAT_S_IMPORTANT_TO_YOU;
	
	@ClientString(id = 1800584, message = "A comparison to others may be helpful.")
	public static NpcStringId A_COMPARISON_TO_OTHERS_MAY_BE_HELPFUL;
	
	@ClientString(id = 1800585, message = "What you've endured will return as a benefit.")
	public static NpcStringId WHAT_YOU_VE_ENDURED_WILL_RETURN_AS_A_BENEFIT;
	
	@ClientString(id = 1800586, message = "Try to be courteous to the opposite sex and follow a virtuous path.")
	public static NpcStringId TRY_TO_BE_COURTEOUS_TO_THE_OPPOSITE_SEX_AND_FOLLOW_A_VIRTUOUS_PATH;
	
	@ClientString(id = 1800587, message = "Joy may come from small things.")
	public static NpcStringId JOY_MAY_COME_FROM_SMALL_THINGS;
	
	@ClientString(id = 1800588, message = "Be confident in your actions as good luck shadows the result.")
	public static NpcStringId BE_CONFIDENT_IN_YOUR_ACTIONS_AS_GOOD_LUCK_SHADOWS_THE_RESULT;
	
	@ClientString(id = 1800589, message = "Be confident without hesitation when your honesty is above reproach in dealings.")
	public static NpcStringId BE_CONFIDENT_WITHOUT_HESITATION_WHEN_YOUR_HONESTY_IS_ABOVE_REPROACH_IN_DEALINGS;
	
	@ClientString(id = 1800590, message = "A matter related to a close friend can isolate you, keep staying on the right path.")
	public static NpcStringId A_MATTER_RELATED_TO_A_CLOSE_FRIEND_CAN_ISOLATE_YOU_KEEP_STAYING_ON_THE_RIGHT_PATH;
	
	@ClientString(id = 1800591, message = "Too much focus on the result may bring continuous misfortune.")
	public static NpcStringId TOO_MUCH_FOCUS_ON_THE_RESULT_MAY_BRING_CONTINUOUS_MISFORTUNE;
	
	@ClientString(id = 1800592, message = "Be tenacious until the finish as halfway abandonment causes a troubled ending.")
	public static NpcStringId BE_TENACIOUS_UNTIL_THE_FINISH_AS_HALFWAY_ABANDONMENT_CAUSES_A_TROUBLED_ENDING;
	
	@ClientString(id = 1800593, message = "There will be no advantage in a group deal.")
	public static NpcStringId THERE_WILL_BE_NO_ADVANTAGE_IN_A_GROUP_DEAL;
	
	@ClientString(id = 1800594, message = "Refrain from stepping-up but take a moment to ponder to be flexible with situations.")
	public static NpcStringId REFRAIN_FROM_STEPPING_UP_BUT_TAKE_A_MOMENT_TO_PONDER_TO_BE_FLEXIBLE_WITH_SITUATIONS;
	
	@ClientString(id = 1800595, message = "There will be a small opportunity when information is best utilized.")
	public static NpcStringId THERE_WILL_BE_A_SMALL_OPPORTUNITY_WHEN_INFORMATION_IS_BEST_UTILIZED;
	
	@ClientString(id = 1800596, message = "Belongings are at loose ends, keep track of the things you value.")
	public static NpcStringId BELONGINGS_ARE_AT_LOOSE_ENDS_KEEP_TRACK_OF_THE_THINGS_YOU_VALUE;
	
	@ClientString(id = 1800597, message = "What you sow is what you reap, try your best.")
	public static NpcStringId WHAT_YOU_SOW_IS_WHAT_YOU_REAP_TRY_YOUR_BEST;
	
	@ClientString(id = 1800598, message = "With the beginner's attitude, shortcomings can be easily mended.")
	public static NpcStringId WITH_THE_BEGINNER_S_ATTITUDE_SHORTCOMINGS_CAN_BE_EASILY_MENDED;
	
	@ClientString(id = 1800599, message = "When facing difficulties, seek a totally different direction.")
	public static NpcStringId WHEN_FACING_DIFFICULTIES_SEEK_A_TOTALLY_DIFFERENT_DIRECTION;
	
	@ClientString(id = 1800600, message = "Lifetime savings can disappear with one-time greed.")
	public static NpcStringId LIFETIME_SAVINGS_CAN_DISAPPEAR_WITH_ONE_TIME_GREED;
	
	@ClientString(id = 1800601, message = "With your heart avoid extremes and peace will stay.")
	public static NpcStringId WITH_YOUR_HEART_AVOID_EXTREMES_AND_PEACE_WILL_STAY;
	
	@ClientString(id = 1800602, message = "Be cautious as instant recklessness may bring malicious gossip.")
	public static NpcStringId BE_CAUTIOUS_AS_INSTANT_RECKLESSNESS_MAY_BRING_MALICIOUS_GOSSIP;
	
	@ClientString(id = 1800603, message = "Be tenacious to the end because a strong luck with winning is ahead.")
	public static NpcStringId BE_TENACIOUS_TO_THE_END_BECAUSE_A_STRONG_LUCK_WITH_WINNING_IS_AHEAD;
	
	@ClientString(id = 1800604, message = "Be kind to and care for those close to you, they may help in the future.")
	public static NpcStringId BE_KIND_TO_AND_CARE_FOR_THOSE_CLOSE_TO_YOU_THEY_MAY_HELP_IN_THE_FUTURE;
	
	@ClientString(id = 1800605, message = "Positivity may bring good results.")
	public static NpcStringId POSITIVITY_MAY_BRING_GOOD_RESULTS;
	
	@ClientString(id = 1800606, message = "Be gracious to cover a close friend's fault.")
	public static NpcStringId BE_GRACIOUS_TO_COVER_A_CLOSE_FRIEND_S_FAULT;
	
	@ClientString(id = 1800607, message = "Be prepared for an expected cost.")
	public static NpcStringId BE_PREPARED_FOR_AN_EXPECTED_COST;
	
	@ClientString(id = 1800608, message = "Be considerate to others and avoid focusing only on winning or a wound will be left untreated.")
	public static NpcStringId BE_CONSIDERATE_TO_OTHERS_AND_AVOID_FOCUSING_ONLY_ON_WINNING_OR_A_WOUND_WILL_BE_LEFT_UNTREATED;
	
	@ClientString(id = 1800609, message = "An accessory or decoration may bring a good luck.")
	public static NpcStringId AN_ACCESSORY_OR_DECORATION_MAY_BRING_A_GOOD_LUCK;
	
	@ClientString(id = 1800610, message = "Only reflection and humility may bring success.")
	public static NpcStringId ONLY_REFLECTION_AND_HUMILITY_MAY_BRING_SUCCESS;
	
	@ClientString(id = 1800611, message = "A small misunderstanding may cause quarrels.")
	public static NpcStringId A_SMALL_MISUNDERSTANDING_MAY_CAUSE_QUARRELS;
	
	@ClientString(id = 1800612, message = "Avoid advancing beyond your ability and focus on the flowing stream.")
	public static NpcStringId AVOID_ADVANCING_BEYOND_YOUR_ABILITY_AND_FOCUS_ON_THE_FLOWING_STREAM;
	
	@ClientString(id = 1800613, message = "Considering others with a good heart before self-interest will bring a triumph.")
	public static NpcStringId CONSIDERING_OTHERS_WITH_A_GOOD_HEART_BEFORE_SELF_INTEREST_WILL_BRING_A_TRIUMPH;
	
	@ClientString(id = 1800614, message = "Visiting a place you've never been before may bring luck.")
	public static NpcStringId VISITING_A_PLACE_YOU_VE_NEVER_BEEN_BEFORE_MAY_BRING_LUCK;
	
	@ClientString(id = 1800615, message = "A good thing may happen in a place with a few people.")
	public static NpcStringId A_GOOD_THING_MAY_HAPPEN_IN_A_PLACE_WITH_A_FEW_PEOPLE;
	
	@ClientString(id = 1800616, message = "Being high-strung can cause loss of trust from others because it can be viewed as light-hearted, act sincerely but yet do not lack humor.")
	public static NpcStringId BEING_HIGH_STRUNG_CAN_CAUSE_LOSS_OF_TRUST_FROM_OTHERS_BECAUSE_IT_CAN_BE_VIEWED_AS_LIGHT_HEARTED_ACT_SINCERELY_BUT_YET_DO_NOT_LACK_HUMOR;
	
	@ClientString(id = 1800617, message = "Perfection at the finish can cover faulty work in the process.")
	public static NpcStringId PERFECTION_AT_THE_FINISH_CAN_COVER_FAULTY_WORK_IN_THE_PROCESS;
	
	@ClientString(id = 1800618, message = "Abstain from laziness, much work brings many gains and satisfactory rewards.")
	public static NpcStringId ABSTAIN_FROM_LAZINESS_MUCH_WORK_BRINGS_MANY_GAINS_AND_SATISFACTORY_REWARDS;
	
	@ClientString(id = 1800619, message = "Staying busy rather than being stationary will help.")
	public static NpcStringId STAYING_BUSY_RATHER_THAN_BEING_STATIONARY_WILL_HELP;
	
	@ClientString(id = 1800620, message = "Handling the work by yourself may lead you into temptation.")
	public static NpcStringId HANDLING_THE_WORK_BY_YOURSELF_MAY_LEAD_YOU_INTO_TEMPTATION;
	
	@ClientString(id = 1800621, message = "Pay attention to any small advice without being indifferent.")
	public static NpcStringId PAY_ATTENTION_TO_ANY_SMALL_ADVICE_WITHOUT_BEING_INDIFFERENT;
	
	@ClientString(id = 1800622, message = "Small things make up big things so even value trivial matters.")
	public static NpcStringId SMALL_THINGS_MAKE_UP_BIG_THINGS_SO_EVEN_VALUE_TRIVIAL_MATTERS;
	
	@ClientString(id = 1800623, message = "Action toward the result rather than waiting for the right circumstances may lead you to a fast success.")
	public static NpcStringId ACTION_TOWARD_THE_RESULT_RATHER_THAN_WAITING_FOR_THE_RIGHT_CIRCUMSTANCES_MAY_LEAD_YOU_TO_A_FAST_SUCCESS;
	
	@ClientString(id = 1800624, message = "Don't try to save small expenditures, it will lead to future returns.")
	public static NpcStringId DON_T_TRY_TO_SAVE_SMALL_EXPENDITURES_IT_WILL_LEAD_TO_FUTURE_RETURNS;
	
	@ClientString(id = 1800625, message = "Be cautious to control emotions as temptations are nearby.")
	public static NpcStringId BE_CAUTIOUS_TO_CONTROL_EMOTIONS_AS_TEMPTATIONS_ARE_NEARBY;
	
	@ClientString(id = 1800626, message = "Be warned as neglecting a matter because it's small can cause you trouble.")
	public static NpcStringId BE_WARNED_AS_NEGLECTING_A_MATTER_BECAUSE_IT_S_SMALL_CAN_CAUSE_YOU_TROUBLE;
	
	@ClientString(id = 1800627, message = "Spend when needed rather than trying to unconditionally save.")
	public static NpcStringId SPEND_WHEN_NEEDED_RATHER_THAN_TRYING_TO_UNCONDITIONALLY_SAVE;
	
	@ClientString(id = 1800628, message = "Prejudice will take you to a small gain with a big loss.")
	public static NpcStringId PREJUDICE_WILL_TAKE_YOU_TO_A_SMALL_GAIN_WITH_A_BIG_LOSS;
	
	@ClientString(id = 1800629, message = "Sweet food may bring good luck.")
	public static NpcStringId SWEET_FOOD_MAY_BRING_GOOD_LUCK;
	
	@ClientString(id = 1800630, message = "You may be paid for what you're owed or for your past loss.")
	public static NpcStringId YOU_MAY_BE_PAID_FOR_WHAT_YOU_RE_OWED_OR_FOR_YOUR_PAST_LOSS;
	
	@ClientString(id = 1800631, message = "There may be conflict in basic matters.")
	public static NpcStringId THERE_MAY_BE_CONFLICT_IN_BASIC_MATTERS;
	
	@ClientString(id = 1800632, message = "Be observant to close friends' small behaviors while refraining from excessive kindness.")
	public static NpcStringId BE_OBSERVANT_TO_CLOSE_FRIENDS_SMALL_BEHAVIORS_WHILE_REFRAINING_FROM_EXCESSIVE_KINDNESS;
	
	@ClientString(id = 1800633, message = "Do not show your distress nor lose your smile.")
	public static NpcStringId DO_NOT_SHOW_YOUR_DISTRESS_NOR_LOSE_YOUR_SMILE;
	
	@ClientString(id = 1800634, message = "Showing change may be of help.")
	public static NpcStringId SHOWING_CHANGE_MAY_BE_OF_HELP;
	
	@ClientString(id = 1800635, message = "The intended result may be on your way if the time is perfectly managed.")
	public static NpcStringId THE_INTENDED_RESULT_MAY_BE_ON_YOUR_WAY_IF_THE_TIME_IS_PERFECTLY_MANAGED;
	
	@ClientString(id = 1800636, message = "Hardships may arise if flexibility is not well played.")
	public static NpcStringId HARDSHIPS_MAY_ARISE_IF_FLEXIBILITY_IS_NOT_WELL_PLAYED;
	
	@ClientString(id = 1800637, message = "Keep cool headed because carelessness or inattentiveness may cause misfortune.")
	public static NpcStringId KEEP_COOL_HEADED_BECAUSE_CARELESSNESS_OR_INATTENTIVENESS_MAY_CAUSE_MISFORTUNE;
	
	@ClientString(id = 1800638, message = "Be cautious as you may get hurt after last night's sinister dream.")
	public static NpcStringId BE_CAUTIOUS_AS_YOU_MAY_GET_HURT_AFTER_LAST_NIGHT_S_SINISTER_DREAM;
	
	@ClientString(id = 1800639, message = "A strong wealth luck is ahead but be careful with emotions that may bring losses.")
	public static NpcStringId A_STRONG_WEALTH_LUCK_IS_AHEAD_BUT_BE_CAREFUL_WITH_EMOTIONS_THAT_MAY_BRING_LOSSES;
	
	@ClientString(id = 1800640, message = "Proceed as you wish when it's pertinent to the person you like.")
	public static NpcStringId PROCEED_AS_YOU_WISH_WHEN_IT_S_PERTINENT_TO_THE_PERSON_YOU_LIKE;
	
	@ClientString(id = 1800641, message = "You may deepen the relationship with the opposite sex through conversation.")
	public static NpcStringId YOU_MAY_DEEPEN_THE_RELATIONSHIP_WITH_THE_OPPOSITE_SEX_THROUGH_CONVERSATION;
	
	@ClientString(id = 1800642, message = "Investment into solid material may bring profit.")
	public static NpcStringId INVESTMENT_INTO_SOLID_MATERIAL_MAY_BRING_PROFIT;
	
	@ClientString(id = 1800643, message = "Investment into what you enjoy may be of help.")
	public static NpcStringId INVESTMENT_INTO_WHAT_YOU_ENJOY_MAY_BE_OF_HELP;
	
	@ClientString(id = 1800644, message = "Being busy may help catching up with many changes.")
	public static NpcStringId BEING_BUSY_MAY_HELP_CATCHING_UP_WITH_MANY_CHANGES;
	
	@ClientString(id = 1800645, message = "Choose substance over honor.")
	public static NpcStringId CHOOSE_SUBSTANCE_OVER_HONOR;
	
	@ClientString(id = 1800646, message = "Remember to decline any financial dealings because a good deed may return as resentment.")
	public static NpcStringId REMEMBER_TO_DECLINE_ANY_FINANCIAL_DEALINGS_BECAUSE_A_GOOD_DEED_MAY_RETURN_AS_RESENTMENT;
	
	@ClientString(id = 1800647, message = "Be careful not to make a mistake with a new person.")
	public static NpcStringId BE_CAREFUL_NOT_TO_MAKE_A_MISTAKE_WITH_A_NEW_PERSON;
	
	@ClientString(id = 1800648, message = "Do not be obsessive over a dragged out project since it won't get any better with more time.")
	public static NpcStringId DO_NOT_BE_OBSESSIVE_OVER_A_DRAGGED_OUT_PROJECT_SINCE_IT_WON_T_GET_ANY_BETTER_WITH_MORE_TIME;
	
	@ClientString(id = 1800649, message = "Do not yield what's rightfully yours or tolerate losses.")
	public static NpcStringId DO_NOT_YIELD_WHAT_S_RIGHTFULLY_YOURS_OR_TOLERATE_LOSSES;
	
	@ClientString(id = 1800650, message = "There's luck in relationships so become interested in the opposite sex.")
	public static NpcStringId THERE_S_LUCK_IN_RELATIONSHIPS_SO_BECOME_INTERESTED_IN_THE_OPPOSITE_SEX;
	
	@ClientString(id = 1800651, message = "Seeking others' help rather than trying by yourself may result in two birds with one stone.")
	public static NpcStringId SEEKING_OTHERS_HELP_RATHER_THAN_TRYING_BY_YOURSELF_MAY_RESULT_IN_TWO_BIRDS_WITH_ONE_STONE;
	
	@ClientString(id = 1800652, message = "Persuading the other may result in your gain.")
	public static NpcStringId PERSUADING_THE_OTHER_MAY_RESULT_IN_YOUR_GAIN;
	
	@ClientString(id = 1800653, message = "A good opportunity may come when keeping patience without excessiveness.")
	public static NpcStringId A_GOOD_OPPORTUNITY_MAY_COME_WHEN_KEEPING_PATIENCE_WITHOUT_EXCESSIVENESS;
	
	@ClientString(id = 1800654, message = "The opposite sex may bring fortune.")
	public static NpcStringId THE_OPPOSITE_SEX_MAY_BRING_FORTUNE;
	
	@ClientString(id = 1800655, message = "Doing favor for other people may bring fortune in the future.")
	public static NpcStringId DOING_FAVOR_FOR_OTHER_PEOPLE_MAY_BRING_FORTUNE_IN_THE_FUTURE;
	
	@ClientString(id = 1800656, message = "Luck may stay near if a smile is kept during difficult times.")
	public static NpcStringId LUCK_MAY_STAY_NEAR_IF_A_SMILE_IS_KEPT_DURING_DIFFICULT_TIMES;
	
	@ClientString(id = 1800657, message = "You may reveal your true self like iron is molten into an strong sword.")
	public static NpcStringId YOU_MAY_REVEAL_YOUR_TRUE_SELF_LIKE_IRON_IS_MOLTEN_INTO_AN_STRONG_SWORD;
	
	@ClientString(id = 1800658, message = "Your value will shine as your potential is finally realized.")
	public static NpcStringId YOUR_VALUE_WILL_SHINE_AS_YOUR_POTENTIAL_IS_FINALLY_REALIZED;
	
	@ClientString(id = 1800659, message = "Tenacious efforts in solving a difficult mission or hardship may bring good results as well as realizing your hidden potential.")
	public static NpcStringId TENACIOUS_EFFORTS_IN_SOLVING_A_DIFFICULT_MISSION_OR_HARDSHIP_MAY_BRING_GOOD_RESULTS_AS_WELL_AS_REALIZING_YOUR_HIDDEN_POTENTIAL;
	
	@ClientString(id = 1800660, message = "People will appreciate your positivity and joyful entertaining.")
	public static NpcStringId PEOPLE_WILL_APPRECIATE_YOUR_POSITIVITY_AND_JOYFUL_ENTERTAINING;
	
	@ClientString(id = 1800661, message = "Things will move smoothly with your full wisdom and abilities.")
	public static NpcStringId THINGS_WILL_MOVE_SMOOTHLY_WITH_YOUR_FULL_WISDOM_AND_ABILITIES;
	
	@ClientString(id = 1800662, message = "You may meet a sage who can help you find the right path.")
	public static NpcStringId YOU_MAY_MEET_A_SAGE_WHO_CAN_HELP_YOU_FIND_THE_RIGHT_PATH;
	
	@ClientString(id = 1800663, message = "Keen instinct and foresight will shine their values.")
	public static NpcStringId KEEN_INSTINCT_AND_FORESIGHT_WILL_SHINE_THEIR_VALUES;
	
	@ClientString(id = 1800664, message = "You may bring good luck to those around you.")
	public static NpcStringId YOU_MAY_BRING_GOOD_LUCK_TO_THOSE_AROUND_YOU;
	
	@ClientString(id = 1800665, message = "Your goal may be realized when emotional details are well defined.")
	public static NpcStringId YOUR_GOAL_MAY_BE_REALIZED_WHEN_EMOTIONAL_DETAILS_ARE_WELL_DEFINED;
	
	@ClientString(id = 1800666, message = "You may enjoy affluence after meeting a precious person.")
	public static NpcStringId YOU_MAY_ENJOY_AFFLUENCE_AFTER_MEETING_A_PRECIOUS_PERSON;
	
	@ClientString(id = 1800667, message = "You may meet the opposite sex who has materialistic attractions.")
	public static NpcStringId YOU_MAY_MEET_THE_OPPOSITE_SEX_WHO_HAS_MATERIALISTIC_ATTRACTIONS;
	
	@ClientString(id = 1800668, message = "A big success will follow all possible efforts in competition.")
	public static NpcStringId A_BIG_SUCCESS_WILL_FOLLOW_ALL_POSSIBLE_EFFORTS_IN_COMPETITION;
	
	@ClientString(id = 1800669, message = "A consequence from past actions will be on display.")
	public static NpcStringId A_CONSEQUENCE_FROM_PAST_ACTIONS_WILL_BE_ON_DISPLAY;
	
	@ClientString(id = 1800670, message = "Whatever happened to you and the other person will replay, but this time, the opposite will be the result.")
	public static NpcStringId WHATEVER_HAPPENED_TO_YOU_AND_THE_OTHER_PERSON_WILL_REPLAY_BUT_THIS_TIME_THE_OPPOSITE_WILL_BE_THE_RESULT;
	
	@ClientString(id = 1800671, message = "You may need to sacrifice for a higher cause.")
	public static NpcStringId YOU_MAY_NEED_TO_SACRIFICE_FOR_A_HIGHER_CAUSE;
	
	@ClientString(id = 1800672, message = "You may lose an item but will gain honor.")
	public static NpcStringId YOU_MAY_LOSE_AN_ITEM_BUT_WILL_GAIN_HONOR;
	
	@ClientString(id = 1800673, message = "A new trial or start may be successful as luck shadows changes.")
	public static NpcStringId A_NEW_TRIAL_OR_START_MAY_BE_SUCCESSFUL_AS_LUCK_SHADOWS_CHANGES;
	
	@ClientString(id = 1800674, message = "Be sophisticated without showing your true emotions as tricks and materialistic temptations lie ahead.")
	public static NpcStringId BE_SOPHISTICATED_WITHOUT_SHOWING_YOUR_TRUE_EMOTIONS_AS_TRICKS_AND_MATERIALISTIC_TEMPTATIONS_LIE_AHEAD;
	
	@ClientString(id = 1800675, message = "Do not attempt a dangerous adventure.")
	public static NpcStringId DO_NOT_ATTEMPT_A_DANGEROUS_ADVENTURE;
	
	@ClientString(id = 1800676, message = "Do not be afraid of change. A risk will be another opportunity.")
	public static NpcStringId DO_NOT_BE_AFRAID_OF_CHANGE_A_RISK_WILL_BE_ANOTHER_OPPORTUNITY;
	
	@ClientString(id = 1800677, message = "Be confident and act tenaciously at all times. You may be able to accomplish to perfection during somewhat unstable situations.")
	public static NpcStringId BE_CONFIDENT_AND_ACT_TENACIOUSLY_AT_ALL_TIMES_YOU_MAY_BE_ABLE_TO_ACCOMPLISH_TO_PERFECTION_DURING_SOMEWHAT_UNSTABLE_SITUATIONS;
	
	@ClientString(id = 1800678, message = "You may expect a bright and hopeful future.")
	public static NpcStringId YOU_MAY_EXPECT_A_BRIGHT_AND_HOPEFUL_FUTURE;
	
	@ClientString(id = 1800679, message = "A rest will promise a bigger development.")
	public static NpcStringId A_REST_WILL_PROMISE_A_BIGGER_DEVELOPMENT;
	
	@ClientString(id = 1800680, message = "Fully utilize positive views.")
	public static NpcStringId FULLY_UTILIZE_POSITIVE_VIEWS;
	
	@ClientString(id = 1800681, message = "Positive thinking and energetic actions will take you to the center of the glorious stage.")
	public static NpcStringId POSITIVE_THINKING_AND_ENERGETIC_ACTIONS_WILL_TAKE_YOU_TO_THE_CENTER_OF_THE_GLORIOUS_STAGE;
	
	@ClientString(id = 1800682, message = "Your self confidence and intuition may solve the difficulties.")
	public static NpcStringId YOUR_SELF_CONFIDENCE_AND_INTUITION_MAY_SOLVE_THE_DIFFICULTIES;
	
	@ClientString(id = 1800683, message = "Everything is brilliant and joyful, share it with others. A bigger fortune will follow.")
	public static NpcStringId EVERYTHING_IS_BRILLIANT_AND_JOYFUL_SHARE_IT_WITH_OTHERS_A_BIGGER_FORTUNE_WILL_FOLLOW;
	
	@ClientString(id = 1800684, message = "A fair assessment and reward for past actions lie ahead.")
	public static NpcStringId A_FAIR_ASSESSMENT_AND_REWARD_FOR_PAST_ACTIONS_LIE_AHEAD;
	
	@ClientString(id = 1800685, message = "Pay accurately the old liability or debt, if applicable. A new joy lies ahead.")
	public static NpcStringId PAY_ACCURATELY_THE_OLD_LIABILITY_OR_DEBT_IF_APPLICABLE_A_NEW_JOY_LIES_AHEAD;
	
	@ClientString(id = 1800686, message = "An excessive humility can harm you back.")
	public static NpcStringId AN_EXCESSIVE_HUMILITY_CAN_HARM_YOU_BACK;
	
	@ClientString(id = 1800687, message = "A reward for the past work will come through.")
	public static NpcStringId A_REWARD_FOR_THE_PAST_WORK_WILL_COME_THROUGH;
	
	@ClientString(id = 1800688, message = "Your past fruitless effort will finally be rewarded with something unexpected.")
	public static NpcStringId YOUR_PAST_FRUITLESS_EFFORT_WILL_FINALLY_BE_REWARDED_WITH_SOMETHING_UNEXPECTED;
	
	@ClientString(id = 1800689, message = "There's strong luck in a revival, abandon the old and create the new.")
	public static NpcStringId THERE_S_STRONG_LUCK_IN_A_REVIVAL_ABANDON_THE_OLD_AND_CREATE_THE_NEW;
	
	@ClientString(id = 1800690, message = "You may gain materialistic or mental aid from close friends.")
	public static NpcStringId YOU_MAY_GAIN_MATERIALISTIC_OR_MENTAL_AID_FROM_CLOSE_FRIENDS;
	
	@ClientString(id = 1800691, message = "A good beginning is awaiting you.")
	public static NpcStringId A_GOOD_BEGINNING_IS_AWAITING_YOU;
	
	@ClientString(id = 1800692, message = "You may meet the person you've longed to see.")
	public static NpcStringId YOU_MAY_MEET_THE_PERSON_YOU_VE_LONGED_TO_SEE;
	
	@ClientString(id = 1800693, message = "You may sustain a loss due to your kindness.")
	public static NpcStringId YOU_MAY_SUSTAIN_A_LOSS_DUE_TO_YOUR_KINDNESS;
	
	@ClientString(id = 1800694, message = "Closely observe people who pass by since you may meet a precious person who can help you.")
	public static NpcStringId CLOSELY_OBSERVE_PEOPLE_WHO_PASS_BY_SINCE_YOU_MAY_MEET_A_PRECIOUS_PERSON_WHO_CAN_HELP_YOU;
	
	@ClientString(id = 1800695, message = "Messenger, inform the patrons of the Keucereus Alliance Base! We're gathering brave adventurers to attack Tiat's Mounted Troop that's rooted in the Seed of Destruction.")
	public static NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_WE_RE_GATHERING_BRAVE_ADVENTURERS_TO_ATTACK_TIAT_S_MOUNTED_TROOP_THAT_S_ROOTED_IN_THE_SEED_OF_DESTRUCTION;
	
	@ClientString(id = 1800696, message = "Messenger, inform the patrons of the Keucereus Alliance Base! The Seed of Destruction is currently secured under the flag of the Keucereus Alliance!")
	public static NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_SEED_OF_DESTRUCTION_IS_CURRENTLY_SECURED_UNDER_THE_FLAG_OF_THE_KEUCEREUS_ALLIANCE;
	
	@ClientString(id = 1800697, message = "Messenger, inform the patrons of the Keucereus Alliance Base! Tiat's Mounted Troop is currently trying to retake Seed of Destruction! Commit all the available reinforcements into Seed of Destruction!")
	public static NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_TIAT_S_MOUNTED_TROOP_IS_CURRENTLY_TRYING_TO_RETAKE_SEED_OF_DESTRUCTION_COMMIT_ALL_THE_AVAILABLE_REINFORCEMENTS_INTO_SEED_OF_DESTRUCTION;
	
	@ClientString(id = 1800698, message = "Messenger, inform the brothers in Kucereus' clan outpost! Brave adventurers who have challenged the Seed of Infinity are currently infiltrating the Hall of Erosion through the defensively weak Hall of Suffering!")
	public static NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_BRAVE_ADVENTURERS_WHO_HAVE_CHALLENGED_THE_SEED_OF_INFINITY_ARE_CURRENTLY_INFILTRATING_THE_HALL_OF_EROSION_THROUGH_THE_DEFENSIVELY_WEAK_HALL_OF_SUFFERING;
	
	@ClientString(id = 1800699, message = "Messenger, inform the brothers in Kucereus' clan outpost! Sweeping the Seed of Infinity is currently complete to the Heart of the Seed. Ekimus is being directly attacked, and the Undead remaining in the Hall of Suffering are being eradicated!")
	public static NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_SWEEPING_THE_SEED_OF_INFINITY_IS_CURRENTLY_COMPLETE_TO_THE_HEART_OF_THE_SEED_EKIMUS_IS_BEING_DIRECTLY_ATTACKED_AND_THE_UNDEAD_REMAINING_IN_THE_HALL_OF_SUFFERING_ARE_BEING_ERADICATED;
	
	@ClientString(id = 1800700, message = "Messenger, inform the patrons of the Keucereus Alliance Base! The Seed of Infinity is currently secured under the flag of the Keucereus Alliance!")
	public static NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_SEED_OF_INFINITY_IS_CURRENTLY_SECURED_UNDER_THE_FLAG_OF_THE_KEUCEREUS_ALLIANCE;
	
	@ClientString(id = 1800701, message = "")
	public static NpcStringId EMPTY_4;
	
	@ClientString(id = 1800702, message = "Messenger, inform the patrons of the Keucereus Alliance Base! The resurrected Undead in the Seed of Infinity are pouring into the Hall of Suffering and the Hall of Erosion!")
	public static NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_RESURRECTED_UNDEAD_IN_THE_SEED_OF_INFINITY_ARE_POURING_INTO_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;
	
	@ClientString(id = 1800703, message = "Messenger, inform the brothers in Kucereus' clan outpost! Ekimus is about to be revived by the resurrected Undead in Seed of Infinity. Send all reinforcements to the Heart and the Hall of Suffering!")
	public static NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_EKIMUS_IS_ABOUT_TO_BE_REVIVED_BY_THE_RESURRECTED_UNDEAD_IN_SEED_OF_INFINITY_SEND_ALL_REINFORCEMENTS_TO_THE_HEART_AND_THE_HALL_OF_SUFFERING;
	
	@ClientString(id = 1800704, message = "Stabbing three times!")
	public static NpcStringId STABBING_THREE_TIMES;
	
	@ClientString(id = 1800705, message = "Poor creatures, feel the power of darkness!")
	public static NpcStringId POOR_CREATURES_FEEL_THE_POWER_OF_DARKNESS;
	
	@ClientString(id = 1800706, message = "Whoaaaaaa!!!!")
	public static NpcStringId WHOAAAAAA;
	
	@ClientString(id = 1800707, message = "You'll regret challenging me!!!!")
	public static NpcStringId YOU_LL_REGRET_CHALLENGING_ME;
	
	@ClientString(id = 1800708, message = "It's currently occupied by the enemy and our troops are attacking.")
	public static NpcStringId IT_S_CURRENTLY_OCCUPIED_BY_THE_ENEMY_AND_OUR_TROOPS_ARE_ATTACKING;
	
	@ClientString(id = 1800709, message = "It's under occupation by our forces, and I heard that Kucereus' clan is organizing the remnants.")
	public static NpcStringId IT_S_UNDER_OCCUPATION_BY_OUR_FORCES_AND_I_HEARD_THAT_KUCEREUS_CLAN_IS_ORGANIZING_THE_REMNANTS;
	
	@ClientString(id = 1800710, message = "Although we currently have control of it, the enemy is pushing back with a powerful attack.")
	public static NpcStringId ALTHOUGH_WE_CURRENTLY_HAVE_CONTROL_OF_IT_THE_ENEMY_IS_PUSHING_BACK_WITH_A_POWERFUL_ATTACK;
	
	@ClientString(id = 1800711, message = "It's under the enemy's occupation, and the military forces of adventurers and clan members are unleashing an onslaught upon the Hall of Suffering and the Hall of Erosion.")
	public static NpcStringId IT_S_UNDER_THE_ENEMY_S_OCCUPATION_AND_THE_MILITARY_FORCES_OF_ADVENTURERS_AND_CLAN_MEMBERS_ARE_UNLEASHING_AN_ONSLAUGHT_UPON_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;
	
	@ClientString(id = 1800712, message = "It's under enemy occupation, but the situation is currently favorable, and an infiltration route to the Heart has been secured. All that is left is the final battle with Ekimus and the clean-up of his followers hiding in the Hall of Suffering!")
	public static NpcStringId IT_S_UNDER_ENEMY_OCCUPATION_BUT_THE_SITUATION_IS_CURRENTLY_FAVORABLE_AND_AN_INFILTRATION_ROUTE_TO_THE_HEART_HAS_BEEN_SECURED_ALL_THAT_IS_LEFT_IS_THE_FINAL_BATTLE_WITH_EKIMUS_AND_THE_CLEAN_UP_OF_HIS_FOLLOWERS_HIDING_IN_THE_HALL_OF_SUFFERING;
	
	@ClientString(id = 1800713, message = "Our forces have occupied it and are currently investigating the depths.")
	public static NpcStringId OUR_FORCES_HAVE_OCCUPIED_IT_AND_ARE_CURRENTLY_INVESTIGATING_THE_DEPTHS;
	
	@ClientString(id = 1800714, message = "It's under occupation by our forces, but the enemy has resurrected and is attacking toward the Hall of Suffering and the Hall of Erosion.")
	public static NpcStringId IT_S_UNDER_OCCUPATION_BY_OUR_FORCES_BUT_THE_ENEMY_HAS_RESURRECTED_AND_IS_ATTACKING_TOWARD_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;
	
	@ClientString(id = 1800715, message = "It's under occupation by our forces, but the enemy has already overtaken the Hall of Erosion and is driving out our forces from the Hall of Suffering toward the Heart. It seems that Ekimus will revive shortly.")
	public static NpcStringId IT_S_UNDER_OCCUPATION_BY_OUR_FORCES_BUT_THE_ENEMY_HAS_ALREADY_OVERTAKEN_THE_HALL_OF_EROSION_AND_IS_DRIVING_OUT_OUR_FORCES_FROM_THE_HALL_OF_SUFFERING_TOWARD_THE_HEART_IT_SEEMS_THAT_EKIMUS_WILL_REVIVE_SHORTLY;
	
	@ClientString(id = 1800716, message = "")
	public static NpcStringId EMPTY_5;
	
	@ClientString(id = 1800717, message = "Tiat's followers are coming to retake the Seed of Destruction! Get ready to stop the enemies!")
	public static NpcStringId TIAT_S_FOLLOWERS_ARE_COMING_TO_RETAKE_THE_SEED_OF_DESTRUCTION_GET_READY_TO_STOP_THE_ENEMIES;
	
	@ClientString(id = 1800718, message = "It's hurting... I'm in pain... What can I do for the pain...")
	public static NpcStringId IT_S_HURTING_I_M_IN_PAIN_WHAT_CAN_I_DO_FOR_THE_PAIN;
	
	@ClientString(id = 1800719, message = "No... When I lose that one... I'll be in more pain...")
	public static NpcStringId NO_WHEN_I_LOSE_THAT_ONE_I_LL_BE_IN_MORE_PAIN;
	
	@ClientString(id = 1800720, message = "Hahahah!!! I captured Santa Claus!! There will be no gifts this year!!!")
	public static NpcStringId HAHAHAH_I_CAPTURED_SANTA_CLAUS_THERE_WILL_BE_NO_GIFTS_THIS_YEAR;
	
	@ClientString(id = 1800721, message = "Now! Why don’t you take up the challenge?")
	public static NpcStringId NOW_WHY_DON_T_YOU_TAKE_UP_THE_CHALLENGE;
	
	@ClientString(id = 1800722, message = "Come on, I'll take all of you on! ")
	public static NpcStringId COME_ON_I_LL_TAKE_ALL_OF_YOU_ON;
	
	@ClientString(id = 1800723, message = "How about it? I think I won?")
	public static NpcStringId HOW_ABOUT_IT_I_THINK_I_WON;
	
	@ClientString(id = 1800724, message = "Now!! Those of you who lost, go away!")
	public static NpcStringId NOW_THOSE_OF_YOU_WHO_LOST_GO_AWAY;
	
	@ClientString(id = 1800725, message = "What a bunch of losers.")
	public static NpcStringId WHAT_A_BUNCH_OF_LOSERS;
	
	@ClientString(id = 1800726, message = "I guess you came to rescue Santa. But you picked the wrong person.")
	public static NpcStringId I_GUESS_YOU_CAME_TO_RESCUE_SANTA_BUT_YOU_PICKED_THE_WRONG_PERSON;
	
	@ClientString(id = 1800727, message = "Ah, okay...")
	public static NpcStringId AH_OKAY;
	
	@ClientString(id = 1800728, message = "Agh!! I wasn’t going to do that!")
	public static NpcStringId AGH_I_WASN_T_GOING_TO_DO_THAT;
	
	@ClientString(id = 1800729, message = "You’re cursed!! Oh.. What?")
	public static NpcStringId YOU_RE_CURSED_OH_WHAT;
	
	@ClientString(id = 1800730, message = "Have you done nothing but rock-paper-scissors?? ")
	public static NpcStringId HAVE_YOU_DONE_NOTHING_BUT_ROCK_PAPER_SCISSORS;
	
	@ClientString(id = 1800731, message = "Stop it, no more... I did it because I was too lonely...")
	public static NpcStringId STOP_IT_NO_MORE_I_DID_IT_BECAUSE_I_WAS_TOO_LONELY;
	
	@ClientString(id = 1800732, message = "I have to release Santa... How infuriating!!!")
	public static NpcStringId I_HAVE_TO_RELEASE_SANTA_HOW_INFURIATING;
	
	@ClientString(id = 1800733, message = "I hate happy Merry Christmas!!! ")
	public static NpcStringId I_HATE_HAPPY_MERRY_CHRISTMAS;
	
	@ClientString(id = 1800734, message = "Oh. I'm bored.")
	public static NpcStringId OH_I_M_BORED;
	
	@ClientString(id = 1800735, message = "Shall I go to take a look if Santa is still there? Hehe")
	public static NpcStringId SHALL_I_GO_TO_TAKE_A_LOOK_IF_SANTA_IS_STILL_THERE_HEHE;
	
	@ClientString(id = 1800736, message = "Oh ho ho.... Merry Christmas!! ")
	public static NpcStringId OH_HO_HO_MERRY_CHRISTMAS;
	
	@ClientString(id = 1800737, message = "Santa could give nice presents only if he's released from the Turkey...")
	public static NpcStringId SANTA_COULD_GIVE_NICE_PRESENTS_ONLY_IF_HE_S_RELEASED_FROM_THE_TURKEY;
	
	@ClientString(id = 1800738, message = "Oh ho ho... Oh ho ho... Thank you. Ladies and gentlemen! I will repay you for sure.")
	public static NpcStringId OH_HO_HO_OH_HO_HO_THANK_YOU_LADIES_AND_GENTLEMEN_I_WILL_REPAY_YOU_FOR_SURE;
	
	@ClientString(id = 1800739, message = "Merry Christmas~ You’re doing a good job. ")
	public static NpcStringId MERRY_CHRISTMAS_YOU_RE_DOING_A_GOOD_JOB;
	
	@ClientString(id = 1800740, message = "Merry Christmas~ Thank you for rescuing me from that wretched Turkey. ")
	public static NpcStringId MERRY_CHRISTMAS_THANK_YOU_FOR_RESCUING_ME_FROM_THAT_WRETCHED_TURKEY;
	
	@ClientString(id = 1800741, message = "$s1 . I have prepared a gift for you. ")
	public static NpcStringId S1_I_HAVE_PREPARED_A_GIFT_FOR_YOU;
	
	@ClientString(id = 1800742, message = "I have a gift for $s1.")
	public static NpcStringId I_HAVE_A_GIFT_FOR_S1;
	
	@ClientString(id = 1800743, message = "Take a look at the inventory. I hope you like the gift I gave you. ")
	public static NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_I_HOPE_YOU_LIKE_THE_GIFT_I_GAVE_YOU;
	
	@ClientString(id = 1800744, message = "Take a look at the inventory. Perhaps there might be a big present~")
	public static NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_PERHAPS_THERE_MIGHT_BE_A_BIG_PRESENT;
	
	@ClientString(id = 1800745, message = "I’m tired of dealing with you. I’m leaving.")
	public static NpcStringId I_M_TIRED_OF_DEALING_WITH_YOU_I_M_LEAVING;
	
	@ClientString(id = 1800746, message = "When are you going to stop? I slowly started to be tired of it.")
	public static NpcStringId WHEN_ARE_YOU_GOING_TO_STOP_I_SLOWLY_STARTED_TO_BE_TIRED_OF_IT;
	
	@ClientString(id = 1800747, message = "Message from Santa Claus: Many blessings to $s1, who saved me~")
	public static NpcStringId MESSAGE_FROM_SANTA_CLAUS_MANY_BLESSINGS_TO_S1_WHO_SAVED_ME;
	
	@ClientString(id = 1800748, message = "I am already dead. You cannot kill me again...")
	public static NpcStringId I_AM_ALREADY_DEAD_YOU_CANNOT_KILL_ME_AGAIN;
	
	@ClientString(id = 1800749, message = "Oh followers of the Dragon of Darkness, fight by my side!")
	public static NpcStringId OH_FOLLOWERS_OF_THE_DRAGON_OF_DARKNESS_FIGHT_BY_MY_SIDE;
	
	@ClientString(id = 1800750, message = "The Dragon Race... are invading... Prepare... for battle...")
	public static NpcStringId THE_DRAGON_RACE_ARE_INVADING_PREPARE_FOR_BATTLE;
	
	@ClientString(id = 1800751, message = "$s1 rescued Santa Claus of $s2 territory from the turkey.")
	public static NpcStringId S1_RESCUED_SANTA_CLAUS_OF_S2_TERRITORY_FROM_THE_TURKEY;
	
	@ClientString(id = 1800752, message = "Santa Rescue Success!")
	public static NpcStringId SANTA_RESCUE_SUCCESS;
	
	@ClientString(id = 1800753, message = "$s1 received +$s2 $s3 through the weapon exchange coupon.")
	public static NpcStringId S1_RECEIVED_S2_S3_THROUGH_THE_WEAPON_EXCHANGE_COUPON;
	
	@ClientString(id = 1800754, message = "Don't go prattling on!")
	public static NpcStringId DON_T_GO_PRATTLING_ON;
	
	@ClientString(id = 1800755, message = "You lowlifes with not even an ounce of pride! You're not worthy of opposing me!")
	public static NpcStringId YOU_LOWLIFES_WITH_NOT_EVEN_AN_OUNCE_OF_PRIDE_YOU_RE_NOT_WORTHY_OF_OPPOSING_ME;
	
	@ClientString(id = 1800756, message = "Where am I? This doesn't look right at all… Oink oink!")
	public static NpcStringId WHERE_AM_I_THIS_DOESN_T_LOOK_RIGHT_AT_ALL_OINK_OINK;
	
	@ClientString(id = 1800757, message = "What's going on… How did I get here... Oink oink!")
	public static NpcStringId WHAT_S_GOING_ON_HOW_DID_I_GET_HERE_OINK_OINK;
	
	@ClientString(id = 1800758, message = "Not again, please leave me alone… Oink oink!")
	public static NpcStringId NOT_AGAIN_PLEASE_LEAVE_ME_ALONE_OINK_OINK;
	
	@ClientString(id = 1800759, message = "Wow! I feel so warm and fuzzy now!")
	public static NpcStringId WOW_I_FEEL_SO_WARM_AND_FUZZY_NOW;
	
	@ClientString(id = 1800760, message = "I feel so happy now.")
	public static NpcStringId I_FEEL_SO_HAPPY_NOW;
	
	@ClientString(id = 1800761, message = "Words cannot express my happiness!")
	public static NpcStringId WORDS_CANNOT_EXPRESS_MY_HAPPINESS;
	
	@ClientString(id = 1800762, message = "What's this feeling? Is this… happiness?")
	public static NpcStringId WHAT_S_THIS_FEELING_IS_THIS_HAPPINESS;
	
	@ClientString(id = 1800763, message = "I'm feeling, less angry… what is this witchcraft?")
	public static NpcStringId I_M_FEELING_LESS_ANGRY_WHAT_IS_THIS_WITCHCRAFT;
	
	@ClientString(id = 1800764, message = "Amazing. My hatred is completely gone!")
	public static NpcStringId AMAZING_MY_HATRED_IS_COMPLETELY_GONE;
	
	@ClientString(id = 1800765, message = "Urge to kill lessening… lessening…")
	public static NpcStringId URGE_TO_KILL_LESSENING_LESSENING;
	
	@ClientString(id = 1800766, message = "I'm all sorts of warm and fluffy now.")
	public static NpcStringId I_M_ALL_SORTS_OF_WARM_AND_FLUFFY_NOW;
	
	@ClientString(id = 1800767, message = "What's this? Food?")
	public static NpcStringId WHAT_S_THIS_FOOD;
	
	@ClientString(id = 1800768, message = "Not thanks… I… I think I'm good now.")
	public static NpcStringId NOT_THANKS_I_I_THINK_I_M_GOOD_NOW;
	
	@ClientString(id = 1800769, message = "Cheap move… I thought this was going to be fair.")
	public static NpcStringId CHEAP_MOVE_I_THOUGHT_THIS_WAS_GOING_TO_BE_FAIR;
	
	@ClientString(id = 1800770, message = "Fortune Timer: Reward increases 2 times if completed within 10 seconds!")
	public static NpcStringId FORTUNE_TIMER_REWARD_INCREASES_2_TIMES_IF_COMPLETED_WITHIN_10_SECONDS;
	
	@ClientString(id = 1800771, message = "Fortune Timer: Reward increases 2 times if completed within 40 seconds!")
	public static NpcStringId FORTUNE_TIMER_REWARD_INCREASES_2_TIMES_IF_COMPLETED_WITHIN_40_SECONDS;
	
	@ClientString(id = 1800772, message = "40 seconds are remaining.")
	public static NpcStringId FOURTY_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800773, message = "39 seconds are remaining.")
	public static NpcStringId THIRTY_NINE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800774, message = "38 seconds are remaining.")
	public static NpcStringId THIRTY_EIGHT_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800775, message = "37 seconds are remaining.")
	public static NpcStringId THIRTY_SEVEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800776, message = "36 seconds are remaining.")
	public static NpcStringId THIRTY_SIX_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800777, message = "35 seconds are remaining.")
	public static NpcStringId THIRTY_FIVE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800778, message = "34 seconds are remaining.")
	public static NpcStringId THIRTY_FOUR_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800779, message = "33 seconds are remaining.")
	public static NpcStringId THIRTY_THREE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800780, message = "32 seconds are remaining.")
	public static NpcStringId THIRTY_TWO_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800781, message = "31 seconds are remaining.")
	public static NpcStringId THIRTY_ONE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800782, message = "30 seconds are remaining.")
	public static NpcStringId THIRTY_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800783, message = "29 seconds are remaining.")
	public static NpcStringId TWENTY_NINE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800784, message = "28 seconds are remaining.")
	public static NpcStringId TWENTY_EIGHT_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800785, message = "27 seconds are remaining.")
	public static NpcStringId TWENTY_SEVEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800786, message = "26 seconds are remaining.")
	public static NpcStringId TWENTY_SIX_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800787, message = "25 seconds are remaining.")
	public static NpcStringId TWENTY_FIVE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800788, message = "24 seconds are remaining.")
	public static NpcStringId TWENTY_FOUR_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800789, message = "23 seconds are remaining.")
	public static NpcStringId TWENTY_THREE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800790, message = "22 seconds are remaining.")
	public static NpcStringId TWENTY_TWO_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800791, message = "21 seconds are remaining.")
	public static NpcStringId TWENTY_ONE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800792, message = "20 seconds are remaining.")
	public static NpcStringId TWENTY_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800793, message = "19 seconds are remaining.")
	public static NpcStringId NINETEEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800794, message = "18 seconds are remaining.")
	public static NpcStringId EIGHTEEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800795, message = "17 seconds are remaining.")
	public static NpcStringId SEVENTEEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800796, message = "16 seconds are remaining.")
	public static NpcStringId SIXTEEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800797, message = "15 seconds are remaining.")
	public static NpcStringId FIFTEEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800798, message = "14 seconds are remaining.")
	public static NpcStringId FOURTEEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800799, message = "13 seconds are remaining.")
	public static NpcStringId THIRTEEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800800, message = "12 seconds are remaining.")
	public static NpcStringId TWELVE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800801, message = "11 seconds are remaining.")
	public static NpcStringId ELEVEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800802, message = "10 seconds are remaining.")
	public static NpcStringId TEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800803, message = "9 seconds are remaining.")
	public static NpcStringId NINE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800804, message = "8 seconds are remaining.")
	public static NpcStringId EIGHT_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800805, message = "7 seconds are remaining.")
	public static NpcStringId SEVEN_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800806, message = "6 seconds are remaining.")
	public static NpcStringId SIX_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800807, message = "5 seconds are remaining.")
	public static NpcStringId FIVE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800808, message = "4 seconds are remaining.")
	public static NpcStringId FOUR_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800809, message = "3 seconds are remaining.")
	public static NpcStringId THREE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800810, message = "2 seconds are remaining.")
	public static NpcStringId TWO_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800811, message = "1 seconds are remaining.")
	public static NpcStringId ONE_SECONDS_ARE_REMAINING;
	
	@ClientString(id = 1800812, message = "Time up!")
	public static NpcStringId TIME_UP;
	
	@ClientString(id = 1800813, message = "Mission failed!")
	public static NpcStringId MISSION_FAILED;
	
	@ClientString(id = 1800814, message = "Mission success!")
	public static NpcStringId MISSION_SUCCESS;
	
	@ClientString(id = 1800815, message = "I'll kill you later, right now I'm raging at this guy.")
	public static NpcStringId I_LL_KILL_YOU_LATER_RIGHT_NOW_I_M_RAGING_AT_THIS_GUY;
	
	@ClientString(id = 1800816, message = "I'll nibble your toes off! *Quick, use the Bunny Pacifier Juice*")
	public static NpcStringId I_LL_NIBBLE_YOUR_TOES_OFF_QUICK_USE_THE_BUNNY_PACIFIER_JUICE;
	
	@ClientString(id = 1800817, message = "Here comes the furry flurry of fury! *Quick, use the Bunny Pacifier Juice*")
	public static NpcStringId HERE_COMES_THE_FURRY_FLURRY_OF_FURY_QUICK_USE_THE_BUNNY_PACIFIER_JUICE;
	
	@ClientString(id = 1800818, message = "I don't feel like killing anymore. Thanks, $s1~")
	public static NpcStringId I_DON_T_FEEL_LIKE_KILLING_ANYMORE_THANKS_S1;
	
	@ClientString(id = 1800819, message = "Squeee! Are you a rabbit whisperer $s1, cause I feel great.")
	public static NpcStringId SQUEEE_ARE_YOU_A_RABBIT_WHISPERER_S1_CAUSE_I_FEEL_GREAT;
	
	@ClientString(id = 1800820, message = "Boy, the next time I feel like going on a rampage I hope you are there $s1!")
	public static NpcStringId BOY_THE_NEXT_TIME_I_FEEL_LIKE_GOING_ON_A_RAMPAGE_I_HOPE_YOU_ARE_THERE_S1;
	
	@ClientString(id = 1800821, message = "I hope your happy because I'm not. Next time try bringing some friends.")
	public static NpcStringId I_HOPE_YOUR_HAPPY_BECAUSE_I_M_NOT_NEXT_TIME_TRY_BRINGING_SOME_FRIENDS;
	
	@ClientString(id = 1800822, message = "Rage level is rising… FEEL MY RAGE!")
	public static NpcStringId RAGE_LEVEL_IS_RISING_FEEL_MY_RAGE;
	
	@ClientString(id = 1800823, message = "Sniff sniff~ Do you smell the scent of a fresh-baked baguette?")
	public static NpcStringId SNIFF_SNIFF_DO_YOU_SMELL_THE_SCENT_OF_A_FRESH_BAKED_BAGUETTE;
	
	@ClientString(id = 1800824, message = "Who am I~? Let me know if you wanna buy my bread~~")
	public static NpcStringId WHO_AM_I_LET_ME_KNOW_IF_YOU_WANNA_BUY_MY_BREAD;
	
	@ClientString(id = 1800825, message = "I just want to make your weapons stronger~ Abra Kadabra~!")
	public static NpcStringId I_JUST_WANT_TO_MAKE_YOUR_WEAPONS_STRONGER_ABRA_KADABRA;
	
	@ClientString(id = 1800826, message = "What? You don't like it? What's the matter with you~ Like an amateur!")
	public static NpcStringId WHAT_YOU_DON_T_LIKE_IT_WHAT_S_THE_MATTER_WITH_YOU_LIKE_AN_AMATEUR;
	
	@ClientString(id = 1800827, message = "Hey~ Did you tell a lie on April Fool's Day? Don't talk to me if you didn't~!")
	public static NpcStringId HEY_DID_YOU_TELL_A_LIE_ON_APRIL_FOOL_S_DAY_DON_T_TALK_TO_ME_IF_YOU_DIDN_T;
	
	@ClientString(id = 1800828, message = "Grunt... What's... wrong with me...")
	public static NpcStringId GRUNT_WHAT_S_WRONG_WITH_ME;
	
	@ClientString(id = 1800829, message = "...Grunt... Oh...")
	public static NpcStringId GRUNT_OH;
	
	@ClientString(id = 1800830, message = "The grave robber warrior has been filled with dark energy and is attacking you!")
	public static NpcStringId THE_GRAVE_ROBBER_WARRIOR_HAS_BEEN_FILLED_WITH_DARK_ENERGY_AND_IS_ATTACKING_YOU;
	
	@ClientString(id = 1800831, message = "The altar guardian is scrutinizing you!! \\nThose who dare to challenge using the power of evil shall be punished with death.")
	public static NpcStringId THE_ALTAR_GUARDIAN_IS_SCRUTINIZING_YOU_NTHOSE_WHO_DARE_TO_CHALLENGE_USING_THE_POWER_OF_EVIL_SHALL_BE_PUNISHED_WITH_DEATH;
	
	@ClientString(id = 1800832, message = "Wait... Wait! Stop! Save me, and I'll give you 10,000,000 adena!!")
	public static NpcStringId WAIT_WAIT_STOP_SAVE_ME_AND_I_LL_GIVE_YOU_10_000_000_ADENA;
	
	@ClientString(id = 1800833, message = "I... don't want to fight...")
	public static NpcStringId I_DON_T_WANT_TO_FIGHT;
	
	@ClientString(id = 1800834, message = "Is this really necessary...?")
	public static NpcStringId IS_THIS_REALLY_NECESSARY;
	
	@ClientString(id = 1800835, message = "Th... Thanks... I could have become good friends with you...")
	public static NpcStringId TH_THANKS_I_COULD_HAVE_BECOME_GOOD_FRIENDS_WITH_YOU;
	
	@ClientString(id = 1800836, message = "I'll give you 10,000,000 adena, like I promised! I might be an orc who keeps my promises!")
	public static NpcStringId I_LL_GIVE_YOU_10_000_000_ADENA_LIKE_I_PROMISED_I_MIGHT_BE_AN_ORC_WHO_KEEPS_MY_PROMISES;
	
	@ClientString(id = 1800837, message = "Thanks, but that thing about 10,000,000 adena was a lie! See ya!!")
	public static NpcStringId THANKS_BUT_THAT_THING_ABOUT_10_000_000_ADENA_WAS_A_LIE_SEE_YA;
	
	@ClientString(id = 1800838, message = "You're pretty dumb to believe me!")
	public static NpcStringId YOU_RE_PRETTY_DUMB_TO_BELIEVE_ME;
	
	@ClientString(id = 1800839, message = "Ugh... A curse upon you...!")
	public static NpcStringId UGH_A_CURSE_UPON_YOU;
	
	@ClientString(id = 1800840, message = "I really... didn't want... to fight...")
	public static NpcStringId I_REALLY_DIDN_T_WANT_TO_FIGHT;
	
	@ClientString(id = 1800841, message = "Kasha's Eye is scrutinizing you!!")
	public static NpcStringId KASHA_S_EYE_IS_SCRUTINIZING_YOU;
	
	@ClientString(id = 1800842, message = "The Kasha's Eye gives you a strange feeling.")
	public static NpcStringId THE_KASHA_S_EYE_GIVES_YOU_A_STRANGE_FEELING;
	
	@ClientString(id = 1800843, message = "The evil aura of the Kasha's Eye seems to be increasing quickly!!")
	public static NpcStringId THE_EVIL_AURA_OF_THE_KASHA_S_EYE_SEEMS_TO_BE_INCREASING_QUICKLY;
	
	@ClientString(id = 1800844, message = "I protect the altar! You can't escape the altar!")
	public static NpcStringId I_PROTECT_THE_ALTAR_YOU_CAN_T_ESCAPE_THE_ALTAR;
	
	@ClientString(id = 1800845, message = "$s1! That stranger must be defeated. Here is the ultimate help!")
	public static NpcStringId S1_THAT_STRANGER_MUST_BE_DEFEATED_HERE_IS_THE_ULTIMATE_HELP;
	
	@ClientString(id = 1800846, message = "Look here!! $s1. Don't fall too far behind.")
	public static NpcStringId LOOK_HERE_S1_DON_T_FALL_TOO_FAR_BEHIND;
	
	@ClientString(id = 1800847, message = "Well done. $s1. Your help is much appreciated.")
	public static NpcStringId WELL_DONE_S1_YOUR_HELP_IS_MUCH_APPRECIATED;
	
	@ClientString(id = 1800848, message = "Who has awakened us from our slumber?")
	public static NpcStringId WHO_HAS_AWAKENED_US_FROM_OUR_SLUMBER;
	
	@ClientString(id = 1800849, message = "All will pay a severe price to me and these here.")
	public static NpcStringId ALL_WILL_PAY_A_SEVERE_PRICE_TO_ME_AND_THESE_HERE;
	
	@ClientString(id = 1800850, message = "Shyeed's cry is steadily dying down.")
	public static NpcStringId SHYEED_S_CRY_IS_STEADILY_DYING_DOWN;
	
	@ClientString(id = 1800851, message = "Alert! Alert! Damage detection recognized. Countermeasures enabled.")
	public static NpcStringId ALERT_ALERT_DAMAGE_DETECTION_RECOGNIZED_COUNTERMEASURES_ENABLED;
	
	@ClientString(id = 1800852, message = "Target recognition achieved. Attack sequence commencing.")
	public static NpcStringId TARGET_RECOGNITION_ACHIEVED_ATTACK_SEQUENCE_COMMENCING;
	
	@ClientString(id = 1800853, message = "Target. Threat. Level. Launching. Strongest. Countermeasure.")
	public static NpcStringId TARGET_THREAT_LEVEL_LAUNCHING_STRONGEST_COUNTERMEASURE;
	
	@ClientString(id = 1800854, message = "The purification field is being attacked. Guardian Spirits! Protect the Magic Force!!")
	public static NpcStringId THE_PURIFICATION_FIELD_IS_BEING_ATTACKED_GUARDIAN_SPIRITS_PROTECT_THE_MAGIC_FORCE;
	
	@ClientString(id = 1800855, message = "Protect the Braziers of Purity at all costs!!")
	public static NpcStringId PROTECT_THE_BRAZIERS_OF_PURITY_AT_ALL_COSTS;
	
	@ClientString(id = 1800856, message = "Defend our domain even at risk of your own life!")
	public static NpcStringId DEFEND_OUR_DOMAIN_EVEN_AT_RISK_OF_YOUR_OWN_LIFE;
	
	@ClientString(id = 1800857, message = "Peunglui muglanep!")
	public static NpcStringId PEUNGLUI_MUGLANEP;
	
	@ClientString(id = 1800858, message = "Naia waganagel peutagun!")
	public static NpcStringId NAIA_WAGANAGEL_PEUTAGUN;
	
	@ClientString(id = 1800859, message = "Drive device partial destruction impulse result")
	public static NpcStringId DRIVE_DEVICE_PARTIAL_DESTRUCTION_IMPULSE_RESULT;
	
	@ClientString(id = 1800860, message = "Even the Magic Force binds you, you will never be forgiven...")
	public static NpcStringId EVEN_THE_MAGIC_FORCE_BINDS_YOU_YOU_WILL_NEVER_BE_FORGIVEN;
	
	@ClientString(id = 1800861, message = "Oh giants, an intruder has been discovered.")
	public static NpcStringId OH_GIANTS_AN_INTRUDER_HAS_BEEN_DISCOVERED;
	
	@ClientString(id = 1800862, message = "All is vanity! but this cannot be the end!")
	public static NpcStringId ALL_IS_VANITY_BUT_THIS_CANNOT_BE_THE_END;
	
	@ClientString(id = 1800863, message = "Those who are in front of my eyes! will be destroyed!")
	public static NpcStringId THOSE_WHO_ARE_IN_FRONT_OF_MY_EYES_WILL_BE_DESTROYED;
	
	@ClientString(id = 1800864, message = "I am tired! Do not wake me up again!")
	public static NpcStringId I_AM_TIRED_DO_NOT_WAKE_ME_UP_AGAIN;
	
	@ClientString(id = 1800865, message = " Intruder detected ")
	public static NpcStringId INTRUDER_DETECTED;
	
	@ClientString(id = 1800866, message = "The candles can lead you to Zaken. Destroy him")
	public static NpcStringId THE_CANDLES_CAN_LEAD_YOU_TO_ZAKEN_DESTROY_HIM;
	
	@ClientString(id = 1800867, message = "Who dares awaken the mighty Zaken?")
	public static NpcStringId WHO_DARES_AWAKEN_THE_MIGHTY_ZAKEN;
	
	@ClientString(id = 1800868, message = "Ye not be finding me below the drink!")
	public static NpcStringId YE_NOT_BE_FINDING_ME_BELOW_THE_DRINK;
	
	@ClientString(id = 1800869, message = "Ye must be three sheets to the wind if yer lookin for me there.")
	public static NpcStringId YE_MUST_BE_THREE_SHEETS_TO_THE_WIND_IF_YER_LOOKIN_FOR_ME_THERE;
	
	@ClientString(id = 1800870, message = "Ye not be finding me in the Crows Nest.")
	public static NpcStringId YE_NOT_BE_FINDING_ME_IN_THE_CROWS_NEST;
	
	@ClientString(id = 1800871, message = "Sorry but this is all I have.. Give me a break!")
	public static NpcStringId SORRY_BUT_THIS_IS_ALL_I_HAVE_GIVE_ME_A_BREAK;
	
	@ClientString(id = 1800872, message = "Peunglui muglanep Naia waganagel peutagun!")
	public static NpcStringId PEUNGLUI_MUGLANEP_NAIA_WAGANAGEL_PEUTAGUN;
	
	@ClientString(id = 1800873, message = "Drive device entire destruction moving suspension")
	public static NpcStringId DRIVE_DEVICE_ENTIRE_DESTRUCTION_MOVING_SUSPENSION;
	
	@ClientString(id = 1800874, message = "Ah ah... From the Magic Force, no more... I will be freed")
	public static NpcStringId AH_AH_FROM_THE_MAGIC_FORCE_NO_MORE_I_WILL_BE_FREED;
	
	@ClientString(id = 1800875, message = "You guys are detected!")
	public static NpcStringId YOU_GUYS_ARE_DETECTED;
	
	@ClientString(id = 1800876, message = "What kind of creatures are you!")
	public static NpcStringId WHAT_KIND_OF_CREATURES_ARE_YOU;
	
	@ClientString(id = 1800877, message = "$s2 of level $s1 is acquired")
	public static NpcStringId S2_OF_LEVEL_S1_IS_ACQUIRED;
	
	@ClientString(id = 1800878, message = "Life Stone from the Beginning acquired")
	public static NpcStringId LIFE_STONE_FROM_THE_BEGINNING_ACQUIRED;
	
	@ClientString(id = 1800879, message = "When inventory weight/number are more than 80%, the Life Stone from the Beginning cannot be acquired.")
	public static NpcStringId WHEN_INVENTORY_WEIGHT_NUMBER_ARE_MORE_THAN_80_THE_LIFE_STONE_FROM_THE_BEGINNING_CANNOT_BE_ACQUIRED;
	
	@ClientString(id = 1800880, message = "You are under my thumb!!")
	public static NpcStringId YOU_ARE_UNDER_MY_THUMB;
	
	@ClientString(id = 1800881, message = "20 minutes are added to the remaining time in the Instant Zone.")
	public static NpcStringId TWENTY_MINUTES_ARE_ADDED_TO_THE_REMAINING_TIME_IN_THE_INSTANT_ZONE;
	
	@ClientString(id = 1800882, message = "Hurry hurry")
	public static NpcStringId HURRY_HURRY;
	
	@ClientString(id = 1800883, message = "I am not that type of person who stays in one place for a long time")
	public static NpcStringId I_AM_NOT_THAT_TYPE_OF_PERSON_WHO_STAYS_IN_ONE_PLACE_FOR_A_LONG_TIME;
	
	@ClientString(id = 1800884, message = "It's hard for me to keep standing like this")
	public static NpcStringId IT_S_HARD_FOR_ME_TO_KEEP_STANDING_LIKE_THIS;
	
	@ClientString(id = 1800885, message = "Why don't I go that way this time")
	public static NpcStringId WHY_DON_T_I_GO_THAT_WAY_THIS_TIME;
	
	@ClientString(id = 1800886, message = "Welcome!")
	public static NpcStringId WELCOME;
	
	@ClientString(id = 1800887, message = "Is that it? Is that the extent of your abilities? Put in a little more effort!!")
	public static NpcStringId IS_THAT_IT_IS_THAT_THE_EXTENT_OF_YOUR_ABILITIES_PUT_IN_A_LITTLE_MORE_EFFORT;
	
	@ClientString(id = 1800888, message = "Your abilities are pitiful... You are far from a worthy opponent.")
	public static NpcStringId YOUR_ABILITIES_ARE_PITIFUL_YOU_ARE_FAR_FROM_A_WORTHY_OPPONENT;
	
	@ClientString(id = 1800889, message = "Even after death you order me to wander around looking for the scapegoats!")
	public static NpcStringId EVEN_AFTER_DEATH_YOU_ORDER_ME_TO_WANDER_AROUND_LOOKING_FOR_THE_SCAPEGOATS;
	
	@ClientString(id = 1800890, message = "Here goes the Heatstroke! If you can withstand the hot heatstroke up to the 3rd stage, the Sultriness will come to you.")
	public static NpcStringId HERE_GOES_THE_HEATSTROKE_IF_YOU_CAN_WITHSTAND_THE_HOT_HEATSTROKE_UP_TO_THE_3RD_STAGE_THE_SULTRINESS_WILL_COME_TO_YOU;
	
	@ClientString(id = 1800891, message = "Just you wait! Humidity is a blistering fireball which can easily withstand plenty of Cool Air Cannon attacks!")
	public static NpcStringId JUST_YOU_WAIT_HUMIDITY_IS_A_BLISTERING_FIREBALL_WHICH_CAN_EASILY_WITHSTAND_PLENTY_OF_COOL_AIR_CANNON_ATTACKS;
	
	@ClientString(id = 1800892, message = "In order to defeat Humidity, you must obtain the Headstroke Prevention Effect from Doctor Ice and fire more than 10 rounds of the Cool Air Cannon on it!")
	public static NpcStringId IN_ORDER_TO_DEFEAT_HUMIDITY_YOU_MUST_OBTAIN_THE_HEADSTROKE_PREVENTION_EFFECT_FROM_DOCTOR_ICE_AND_FIRE_MORE_THAN_10_ROUNDS_OF_THE_COOL_AIR_CANNON_ON_IT;
	
	@ClientString(id = 1800893, message = "You are here, $s1! I'll teach you a lesson! Bring it on!")
	public static NpcStringId YOU_ARE_HERE_S1_I_LL_TEACH_YOU_A_LESSON_BRING_IT_ON;
	
	@ClientString(id = 1800894, message = "That's cold! Isn't it one of those Cool Packs? I hate anything that's cold!")
	public static NpcStringId THAT_S_COLD_ISN_T_IT_ONE_OF_THOSE_COOL_PACKS_I_HATE_ANYTHING_THAT_S_COLD;
	
	@ClientString(id = 1800895, message = "Huh! You've missed! Is that all you have?")
	public static NpcStringId HUH_YOU_VE_MISSED_IS_THAT_ALL_YOU_HAVE;
	
	@ClientString(id = 1800896, message = "I will give you precious things that I have stolen. So stop bothering me!")
	public static NpcStringId I_WILL_GIVE_YOU_PRECIOUS_THINGS_THAT_I_HAVE_STOLEN_SO_STOP_BOTHERING_ME;
	
	@ClientString(id = 1800897, message = "I was going to give you a jackpot item. You don't have enough inventory room. See you next time.")
	public static NpcStringId I_WAS_GOING_TO_GIVE_YOU_A_JACKPOT_ITEM_YOU_DON_T_HAVE_ENOUGH_INVENTORY_ROOM_SEE_YOU_NEXT_TIME;
	
	@ClientString(id = 1800898, message = "$s1 defeated the Sultriness and acquired item S84")
	public static NpcStringId S1_DEFEATED_THE_SULTRINESS_AND_ACQUIRED_ITEM_S84;
	
	@ClientString(id = 1800899, message = "$s1 defeated the Sultriness and acquired item S80")
	public static NpcStringId S1_DEFEATED_THE_SULTRINESS_AND_ACQUIRED_ITEM_S80;
	
	@ClientString(id = 1800900, message = "I am not here for you! Your Cool Pack attack does not work against me!")
	public static NpcStringId I_AM_NOT_HERE_FOR_YOU_YOUR_COOL_PACK_ATTACK_DOES_NOT_WORK_AGAINST_ME;
	
	@ClientString(id = 1800901, message = "Uh oh? Where are you hiding? There is nobody who matches my skills? Well, I guess I'd better get going....")
	public static NpcStringId UH_OH_WHERE_ARE_YOU_HIDING_THERE_IS_NOBODY_WHO_MATCHES_MY_SKILLS_WELL_I_GUESS_I_D_BETTER_GET_GOING;
	
	@ClientString(id = 1800902, message = "Why are you not responding? You don't even have any Cool Packs! You can't fight me!")
	public static NpcStringId WHY_ARE_YOU_NOT_RESPONDING_YOU_DON_T_EVEN_HAVE_ANY_COOL_PACKS_YOU_CAN_T_FIGHT_ME;
	
	@ClientString(id = 1800903, message = "Oh~! Where I be~? Who call me~?")
	public static NpcStringId OH_WHERE_I_BE_WHO_CALL_ME;
	
	@ClientString(id = 1800904, message = "Tada~! It's a watermelon~!")
	public static NpcStringId TADA_IT_S_A_WATERMELON;
	
	@ClientString(id = 1800905, message = ">_<... Did ya call me...?")
	public static NpcStringId DID_YA_CALL_ME;
	
	@ClientString(id = 1800906, message = "Enter the watermelon~! It's gonna grow and grow from now on!")
	public static NpcStringId ENTER_THE_WATERMELON_IT_S_GONNA_GROW_AND_GROW_FROM_NOW_ON;
	
	@ClientString(id = 1800907, message = "Oh, ouch~! Did I see you before~?")
	public static NpcStringId OH_OUCH_DID_I_SEE_YOU_BEFORE;
	
	@ClientString(id = 1800908, message = "A new season! Summer is all about the watermelon~!")
	public static NpcStringId A_NEW_SEASON_SUMMER_IS_ALL_ABOUT_THE_WATERMELON;
	
	@ClientString(id = 1800909, message = "Did ya call~? Ho! Thought you'd get something~?^^")
	public static NpcStringId DID_YA_CALL_HO_THOUGHT_YOU_D_GET_SOMETHING;
	
	@ClientString(id = 1800910, message = "Do you want to see my beautiful self~?")
	public static NpcStringId DO_YOU_WANT_TO_SEE_MY_BEAUTIFUL_SELF;
	
	@ClientString(id = 1800911, message = "Hohoho! Let's do it together~!")
	public static NpcStringId HOHOHO_LET_S_DO_IT_TOGETHER;
	
	@ClientString(id = 1800912, message = "It's a giant watermelon if you raise it right~ And a watermelon slice if you mess up~!")
	public static NpcStringId IT_S_A_GIANT_WATERMELON_IF_YOU_RAISE_IT_RIGHT_AND_A_WATERMELON_SLICE_IF_YOU_MESS_UP;
	
	@ClientString(id = 1800913, message = "Tada~ Transformation~ complete~!")
	public static NpcStringId TADA_TRANSFORMATION_COMPLETE;
	
	@ClientString(id = 1800914, message = "Am I a rain watermelon? Or a defective watermelon?")
	public static NpcStringId AM_I_A_RAIN_WATERMELON_OR_A_DEFECTIVE_WATERMELON;
	
	@ClientString(id = 1800915, message = "Now! I've gotten big~! Everyone, come at me!")
	public static NpcStringId NOW_I_VE_GOTTEN_BIG_EVERYONE_COME_AT_ME;
	
	@ClientString(id = 1800916, message = "Get bigger~ Get stronger~! Tell me your wish~~!")
	public static NpcStringId GET_BIGGER_GET_STRONGER_TELL_ME_YOUR_WISH;
	
	@ClientString(id = 1800917, message = "A watermelon slice's wish! But I'm bigger already?")
	public static NpcStringId A_WATERMELON_SLICE_S_WISH_BUT_I_M_BIGGER_ALREADY;
	
	@ClientString(id = 1800918, message = "A large watermelon's wish! Well, try to break me!")
	public static NpcStringId A_LARGE_WATERMELON_S_WISH_WELL_TRY_TO_BREAK_ME;
	
	@ClientString(id = 1800919, message = "I'm done growing~! I'm running away now~^^")
	public static NpcStringId I_M_DONE_GROWING_I_M_RUNNING_AWAY_NOW;
	
	@ClientString(id = 1800920, message = "If you let me go, I'll give you ten million adena!")
	public static NpcStringId IF_YOU_LET_ME_GO_I_LL_GIVE_YOU_TEN_MILLION_ADENA;
	
	@ClientString(id = 1800921, message = "Freedom~! What do you think I have inside?")
	public static NpcStringId FREEDOM_WHAT_DO_YOU_THINK_I_HAVE_INSIDE;
	
	@ClientString(id = 1800922, message = "OK~ OK~ Good job. You know what to do next, right?")
	public static NpcStringId OK_OK_GOOD_JOB_YOU_KNOW_WHAT_TO_DO_NEXT_RIGHT;
	
	@ClientString(id = 1800923, message = "Look here! Do it right! You spilled! This precious...")
	public static NpcStringId LOOK_HERE_DO_IT_RIGHT_YOU_SPILLED_THIS_PRECIOUS;
	
	@ClientString(id = 1800924, message = "Ah~ Refreshing! Spray a little more~!")
	public static NpcStringId AH_REFRESHING_SPRAY_A_LITTLE_MORE;
	
	@ClientString(id = 1800925, message = "Gulp gulp~ Great! But isn't there more?")
	public static NpcStringId GULP_GULP_GREAT_BUT_ISN_T_THERE_MORE;
	
	@ClientString(id = 1800926, message = "Can't you even aim right? Have you even been to the army?")
	public static NpcStringId CAN_T_YOU_EVEN_AIM_RIGHT_HAVE_YOU_EVEN_BEEN_TO_THE_ARMY;
	
	@ClientString(id = 1800927, message = "Did you mix this with water~? Why's it taste like this~!")
	public static NpcStringId DID_YOU_MIX_THIS_WITH_WATER_WHY_S_IT_TASTE_LIKE_THIS;
	
	@ClientString(id = 1800928, message = "Oh! Good! Do a little more. Yeah~")
	public static NpcStringId OH_GOOD_DO_A_LITTLE_MORE_YEAH;
	
	@ClientString(id = 1800929, message = "Hoho! It's not there! Over here~! Am I so small that you can even spray me right?")
	public static NpcStringId HOHO_IT_S_NOT_THERE_OVER_HERE_AM_I_SO_SMALL_THAT_YOU_CAN_EVEN_SPRAY_ME_RIGHT;
	
	@ClientString(id = 1800930, message = "Yuck! What is this~! Are you sure this is nectar~?")
	public static NpcStringId YUCK_WHAT_IS_THIS_ARE_YOU_SURE_THIS_IS_NECTAR;
	
	@ClientString(id = 1800931, message = "Do your best~! I become a big watermelon after just five bottles~!")
	public static NpcStringId DO_YOUR_BEST_I_BECOME_A_BIG_WATERMELON_AFTER_JUST_FIVE_BOTTLES;
	
	@ClientString(id = 1800932, message = "Of course~! Watermelon is the best nectar! Hahaha~!")
	public static NpcStringId OF_COURSE_WATERMELON_IS_THE_BEST_NECTAR_HAHAHA;
	
	@ClientString(id = 1800933, message = "Ahh! Good~ >_< Slap slap me~")
	public static NpcStringId AHH_GOOD_SLAP_SLAP_ME;
	
	@ClientString(id = 1800934, message = "Oww! You're just beating me now~ Give me nectar~")
	public static NpcStringId OWW_YOU_RE_JUST_BEATING_ME_NOW_GIVE_ME_NECTAR;
	
	@ClientString(id = 1800935, message = "Look~!! It's gonna break~!")
	public static NpcStringId LOOK_IT_S_GONNA_BREAK;
	
	@ClientString(id = 1800936, message = "Now! Are you trying to eat without doing the work? Fine~ Do what you want~ I'll hate you if you don't give me any nectar!")
	public static NpcStringId NOW_ARE_YOU_TRYING_TO_EAT_WITHOUT_DOING_THE_WORK_FINE_DO_WHAT_YOU_WANT_I_LL_HATE_YOU_IF_YOU_DON_T_GIVE_ME_ANY_NECTAR;
	
	@ClientString(id = 1800937, message = "Hit me more! Hit me more!")
	public static NpcStringId HIT_ME_MORE_HIT_ME_MORE;
	
	@ClientString(id = 1800938, message = "I'm gonna wither like this~ Damn it~!")
	public static NpcStringId I_M_GONNA_WITHER_LIKE_THIS_DAMN_IT;
	
	@ClientString(id = 1800939, message = "Hey you~ If I die like this, there'll be no item either~ Are you really so stingy with the nectar?")
	public static NpcStringId HEY_YOU_IF_I_DIE_LIKE_THIS_THERE_LL_BE_NO_ITEM_EITHER_ARE_YOU_REALLY_SO_STINGY_WITH_THE_NECTAR;
	
	@ClientString(id = 1800940, message = "It's just a little more!! Good luck~!")
	public static NpcStringId IT_S_JUST_A_LITTLE_MORE_GOOD_LUCK;
	
	@ClientString(id = 1800941, message = "Save me~ I'm about to die without tasting nectar even once~")
	public static NpcStringId SAVE_ME_I_M_ABOUT_TO_DIE_WITHOUT_TASTING_NECTAR_EVEN_ONCE;
	
	@ClientString(id = 1800942, message = "If I die like this, I'll just be a watermelon slice~!")
	public static NpcStringId IF_I_DIE_LIKE_THIS_I_LL_JUST_BE_A_WATERMELON_SLICE;
	
	@ClientString(id = 1800943, message = "I'm getting stronger~? I think I'll be able to run away in 30 seconds~ Hoho~")
	public static NpcStringId I_M_GETTING_STRONGER_I_THINK_I_LL_BE_ABLE_TO_RUN_AWAY_IN_30_SECONDS_HOHO;
	
	@ClientString(id = 1800944, message = "It's goodbye after 20 seconds~!")
	public static NpcStringId IT_S_GOODBYE_AFTER_20_SECONDS;
	
	@ClientString(id = 1800945, message = "Yeah, 10 seconds left~! 9... 8... 7...!")
	public static NpcStringId YEAH_10_SECONDS_LEFT_9_8_7;
	
	@ClientString(id = 1800946, message = "I'm leaving in 2 minutes if you don't give me any nectar~!")
	public static NpcStringId I_M_LEAVING_IN_2_MINUTES_IF_YOU_DON_T_GIVE_ME_ANY_NECTAR;
	
	@ClientString(id = 1800947, message = "I'm leaving in 1 minutes if you don't give me any nectar~!")
	public static NpcStringId I_M_LEAVING_IN_1_MINUTES_IF_YOU_DON_T_GIVE_ME_ANY_NECTAR;
	
	@ClientString(id = 1800948, message = "I'm leaving now~! Then, goodbye~!")
	public static NpcStringId I_M_LEAVING_NOW_THEN_GOODBYE;
	
	@ClientString(id = 1800949, message = "Sorry, but this large watermelon is disappearing here~!")
	public static NpcStringId SORRY_BUT_THIS_LARGE_WATERMELON_IS_DISAPPEARING_HERE;
	
	@ClientString(id = 1800950, message = "Too late~! Have a good time~!")
	public static NpcStringId TOO_LATE_HAVE_A_GOOD_TIME;
	
	@ClientString(id = 1800951, message = "Ding ding~ That's the bell. Put away your weapons and try for next time~!")
	public static NpcStringId DING_DING_THAT_S_THE_BELL_PUT_AWAY_YOUR_WEAPONS_AND_TRY_FOR_NEXT_TIME;
	
	@ClientString(id = 1800952, message = "Too bad~! You raised it up, too! -_-")
	public static NpcStringId TOO_BAD_YOU_RAISED_IT_UP_TOO;
	
	@ClientString(id = 1800953, message = "Oh~ What a nice sound~")
	public static NpcStringId OH_WHAT_A_NICE_SOUND;
	
	@ClientString(id = 1800954, message = "The instrument is nice, but there's no song. Shall I sing for you?")
	public static NpcStringId THE_INSTRUMENT_IS_NICE_BUT_THERE_S_NO_SONG_SHALL_I_SING_FOR_YOU;
	
	@ClientString(id = 1800955, message = "What beautiful music!")
	public static NpcStringId WHAT_BEAUTIFUL_MUSIC;
	
	@ClientString(id = 1800956, message = "I feel good~ Play some more!")
	public static NpcStringId I_FEEL_GOOD_PLAY_SOME_MORE;
	
	@ClientString(id = 1800957, message = "My heart is being captured by the sound of Crono!")
	public static NpcStringId MY_HEART_IS_BEING_CAPTURED_BY_THE_SOUND_OF_CRONO;
	
	@ClientString(id = 1800958, message = "Get the notes right~! Hey old man~ That was wrong!")
	public static NpcStringId GET_THE_NOTES_RIGHT_HEY_OLD_MAN_THAT_WAS_WRONG;
	
	@ClientString(id = 1800959, message = "I like it~!")
	public static NpcStringId I_LIKE_IT;
	
	@ClientString(id = 1800960, message = "Ooh~~ My body wants to open!")
	public static NpcStringId OOH_MY_BODY_WANTS_TO_OPEN;
	
	@ClientString(id = 1800961, message = "Oh~! This chord! My heart is being torn! Play a little more!")
	public static NpcStringId OH_THIS_CHORD_MY_HEART_IS_BEING_TORN_PLAY_A_LITTLE_MORE;
	
	@ClientString(id = 1800962, message = "It's this~ This! I wanted this sound! Why don't you try becoming a singer?")
	public static NpcStringId IT_S_THIS_THIS_I_WANTED_THIS_SOUND_WHY_DON_T_YOU_TRY_BECOMING_A_SINGER;
	
	@ClientString(id = 1800963, message = "You can try a hundred times on this~ You won't get anything good~!")
	public static NpcStringId YOU_CAN_TRY_A_HUNDRED_TIMES_ON_THIS_YOU_WON_T_GET_ANYTHING_GOOD;
	
	@ClientString(id = 1800964, message = "It hurts~! Play just the instrument~!")
	public static NpcStringId IT_HURTS_PLAY_JUST_THE_INSTRUMENT;
	
	@ClientString(id = 1800965, message = "Only good music can open my body!")
	public static NpcStringId ONLY_GOOD_MUSIC_CAN_OPEN_MY_BODY;
	
	@ClientString(id = 1800966, message = "Not this, but you know, that~ What you got as a Chronicle souvenir. Play with that!")
	public static NpcStringId NOT_THIS_BUT_YOU_KNOW_THAT_WHAT_YOU_GOT_AS_A_CHRONICLE_SOUVENIR_PLAY_WITH_THAT;
	
	@ClientString(id = 1800967, message = "Why~ You have no music? Boring... I'm leaving now~")
	public static NpcStringId WHY_YOU_HAVE_NO_MUSIC_BORING_I_M_LEAVING_NOW;
	
	@ClientString(id = 1800968, message = "Not those sharp things~! Use the ones that make nice sounds!")
	public static NpcStringId NOT_THOSE_SHARP_THINGS_USE_THE_ONES_THAT_MAKE_NICE_SOUNDS;
	
	@ClientString(id = 1800969, message = "Large watermelons only open with music~ Just striking with a weapon won't work~!")
	public static NpcStringId LARGE_WATERMELONS_ONLY_OPEN_WITH_MUSIC_JUST_STRIKING_WITH_A_WEAPON_WON_T_WORK;
	
	@ClientString(id = 1800970, message = "Strike with music~! Not with something like this. You need music~!")
	public static NpcStringId STRIKE_WITH_MUSIC_NOT_WITH_SOMETHING_LIKE_THIS_YOU_NEED_MUSIC;
	
	@ClientString(id = 1800971, message = "You're pretty amazing~! But it's all for nothing~~!")
	public static NpcStringId YOU_RE_PRETTY_AMAZING_BUT_IT_S_ALL_FOR_NOTHING;
	
	@ClientString(id = 1800972, message = "Use that on monsters, OK? I want Crono~!")
	public static NpcStringId USE_THAT_ON_MONSTERS_OK_I_WANT_CRONO;
	
	@ClientString(id = 1800973, message = "Everyone~ The watermelon is breaking!!!")
	public static NpcStringId EVERYONE_THE_WATERMELON_IS_BREAKING;
	
	@ClientString(id = 1800974, message = "It's like a watermelon slice~!")
	public static NpcStringId IT_S_LIKE_A_WATERMELON_SLICE;
	
	@ClientString(id = 1800975, message = "A goblin is coming out(?)!")
	public static NpcStringId A_GOBLIN_IS_COMING_OUT;
	
	@ClientString(id = 1800976, message = "Large watermelon~! Make a wish!!")
	public static NpcStringId LARGE_WATERMELON_MAKE_A_WISH;
	
	@ClientString(id = 1800977, message = "Don't tell anyone about my death~")
	public static NpcStringId DON_T_TELL_ANYONE_ABOUT_MY_DEATH;
	
	@ClientString(id = 1800978, message = "Ugh~ The red juice is flowing out!")
	public static NpcStringId UGH_THE_RED_JUICE_IS_FLOWING_OUT;
	
	@ClientString(id = 1800979, message = "This is all~")
	public static NpcStringId THIS_IS_ALL;
	
	@ClientString(id = 1800980, message = "Kyaahh!!! I'm mad...!")
	public static NpcStringId KYAAHH_I_M_MAD;
	
	@ClientString(id = 1800981, message = "Everyone~ This watermelon broke open!! The item is falling out!")
	public static NpcStringId EVERYONE_THIS_WATERMELON_BROKE_OPEN_THE_ITEM_IS_FALLING_OUT;
	
	@ClientString(id = 1800982, message = "Oh! It burst! The contents are spilling out~")
	public static NpcStringId OH_IT_BURST_THE_CONTENTS_ARE_SPILLING_OUT;
	
	@ClientString(id = 1800983, message = "Hohoho~ Play better!")
	public static NpcStringId HOHOHO_PLAY_BETTER;
	
	@ClientString(id = 1800984, message = "Oh~!! You're very talented, huh~?")
	public static NpcStringId OH_YOU_RE_VERY_TALENTED_HUH;
	
	@ClientString(id = 1800985, message = "Play some more! More! More! More!")
	public static NpcStringId PLAY_SOME_MORE_MORE_MORE_MORE;
	
	@ClientString(id = 1800986, message = "I eat hits and grow!!")
	public static NpcStringId I_EAT_HITS_AND_GROW;
	
	@ClientString(id = 1800987, message = "Buck up~ There isn't much time~")
	public static NpcStringId BUCK_UP_THERE_ISN_T_MUCH_TIME;
	
	@ClientString(id = 1800988, message = "Do you think I'll burst with just that~?")
	public static NpcStringId DO_YOU_THINK_I_LL_BURST_WITH_JUST_THAT;
	
	@ClientString(id = 1800989, message = "What a nice attack~ You might be able to kill a passing fly~")
	public static NpcStringId WHAT_A_NICE_ATTACK_YOU_MIGHT_BE_ABLE_TO_KILL_A_PASSING_FLY;
	
	@ClientString(id = 1800990, message = "Right there! A little to the right~! Ah~ Refreshing.")
	public static NpcStringId RIGHT_THERE_A_LITTLE_TO_THE_RIGHT_AH_REFRESHING;
	
	@ClientString(id = 1800991, message = "You call that hitting? Bring some more talented friends!")
	public static NpcStringId YOU_CALL_THAT_HITTING_BRING_SOME_MORE_TALENTED_FRIENDS;
	
	@ClientString(id = 1800992, message = "Don't think! Just hit! We're hitting!")
	public static NpcStringId DON_T_THINK_JUST_HIT_WE_RE_HITTING;
	
	@ClientString(id = 1800993, message = "I need nectar~ Gourd nectar!")
	public static NpcStringId I_NEED_NECTAR_GOURD_NECTAR;
	
	@ClientString(id = 1800994, message = "I can only grow by drinking nectar~")
	public static NpcStringId I_CAN_ONLY_GROW_BY_DRINKING_NECTAR;
	
	@ClientString(id = 1800995, message = "Grow me quick! If you're good, it's a large watermelon. If you're bad, it a watermelon slice~!")
	public static NpcStringId GROW_ME_QUICK_IF_YOU_RE_GOOD_IT_S_A_LARGE_WATERMELON_IF_YOU_RE_BAD_IT_A_WATERMELON_SLICE;
	
	@ClientString(id = 1800996, message = "Gimme nectar~ I'm hungry~")
	public static NpcStringId GIMME_NECTAR_I_M_HUNGRY;
	
	@ClientString(id = 1800997, message = "Hurry and bring me nectar... Not a necktie~... (sorry)")
	public static NpcStringId HURRY_AND_BRING_ME_NECTAR_NOT_A_NECKTIE_SORRY;
	
	@ClientString(id = 1800998, message = "Bring me nectar. Then, I'll drink and grow!")
	public static NpcStringId BRING_ME_NECTAR_THEN_I_LL_DRINK_AND_GROW;
	
	@ClientString(id = 1800999, message = "You wanna eat a tiny watermelon like me? Try giving me some nectar. I'll get huge~!")
	public static NpcStringId YOU_WANNA_EAT_A_TINY_WATERMELON_LIKE_ME_TRY_GIVING_ME_SOME_NECTAR_I_LL_GET_HUGE;
	
	@ClientString(id = 1801000, message = "Hehehe... Grow me well and you'll get a reward. Grow me bad and who knows what'll happen~")
	public static NpcStringId HEHEHE_GROW_ME_WELL_AND_YOU_LL_GET_A_REWARD_GROW_ME_BAD_AND_WHO_KNOWS_WHAT_LL_HAPPEN;
	
	@ClientString(id = 1801001, message = "You want a large watermelon? I'd like to be a watermelon slice~")
	public static NpcStringId YOU_WANT_A_LARGE_WATERMELON_I_D_LIKE_TO_BE_A_WATERMELON_SLICE;
	
	@ClientString(id = 1801002, message = "Trust me and bring me some nectar!! I'll become a large watermelon for you~!")
	public static NpcStringId TRUST_ME_AND_BRING_ME_SOME_NECTAR_I_LL_BECOME_A_LARGE_WATERMELON_FOR_YOU;
	
	@ClientString(id = 1801003, message = "I see. Beleth has recovered all of its magic power. What remains here is just its trace.")
	public static NpcStringId I_SEE_BELETH_HAS_RECOVERED_ALL_OF_ITS_MAGIC_POWER_WHAT_REMAINS_HERE_IS_JUST_ITS_TRACE;
	
	@ClientString(id = 1801004, message = "Command Channel Leader $s1, Beleth's Ring has been acquired.")
	public static NpcStringId COMMAND_CHANNEL_LEADER_S1_BELETH_S_RING_HAS_BEEN_ACQUIRED;
	
	@ClientString(id = 1801005, message = "You summoned me, so you must be confident, huh? Here I come! Jack game!")
	public static NpcStringId YOU_SUMMONED_ME_SO_YOU_MUST_BE_CONFIDENT_HUH_HERE_I_COME_JACK_GAME;
	
	@ClientString(id = 1801006, message = "Hello. Let's have a good Jack game.")
	public static NpcStringId HELLO_LET_S_HAVE_A_GOOD_JACK_GAME;
	
	@ClientString(id = 1801007, message = "I'm starting! Now, show me the card you want!")
	public static NpcStringId I_M_STARTING_NOW_SHOW_ME_THE_CARD_YOU_WANT;
	
	@ClientString(id = 1801008, message = "We'll start now! Show me the card you want!")
	public static NpcStringId WE_LL_START_NOW_SHOW_ME_THE_CARD_YOU_WANT;
	
	@ClientString(id = 1801009, message = "I'm showing the Rotten Pumpkin Card!")
	public static NpcStringId I_M_SHOWING_THE_ROTTEN_PUMPKIN_CARD;
	
	@ClientString(id = 1801010, message = "I'll be showing the Rotten Pumpkin Card!")
	public static NpcStringId I_LL_BE_SHOWING_THE_ROTTEN_PUMPKIN_CARD;
	
	@ClientString(id = 1801011, message = "I'm showing the Jack Pumpkin Card!")
	public static NpcStringId I_M_SHOWING_THE_JACK_PUMPKIN_CARD;
	
	@ClientString(id = 1801012, message = "I'll be showing the Jack Pumpkin Card!")
	public static NpcStringId I_LL_BE_SHOWING_THE_JACK_PUMPKIN_CARD;
	
	@ClientString(id = 1801013, message = "That's my precious Fantastic Chocolate Banana Ultra Favor Candy!!! I'm definitely winning the next round!!")
	public static NpcStringId THAT_S_MY_PRECIOUS_FANTASTIC_CHOCOLATE_BANANA_ULTRA_FAVOR_CANDY_I_M_DEFINITELY_WINNING_THE_NEXT_ROUND;
	
	@ClientString(id = 1801014, message = "It's my precious candy, but... I'll happily give it to you~!")
	public static NpcStringId IT_S_MY_PRECIOUS_CANDY_BUT_I_LL_HAPPILY_GIVE_IT_TO_YOU;
	
	@ClientString(id = 1801015, message = "The candy fell. I'll give you my toy chest instead.")
	public static NpcStringId THE_CANDY_FELL_I_LL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;
	
	@ClientString(id = 1801016, message = "Since the candy fell, I will give you my toy chest instead.")
	public static NpcStringId SINCE_THE_CANDY_FELL_I_WILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;
	
	@ClientString(id = 1801017, message = "You're not peeking at my card, are you? This time, I'll wager a special scroll.")
	public static NpcStringId YOU_RE_NOT_PEEKING_AT_MY_CARD_ARE_YOU_THIS_TIME_I_LL_WAGER_A_SPECIAL_SCROLL;
	
	@ClientString(id = 1801018, message = "We're getting serious now. If you win again, I'll give you a special scroll.")
	public static NpcStringId WE_RE_GETTING_SERIOUS_NOW_IF_YOU_WIN_AGAIN_I_LL_GIVE_YOU_A_SPECIAL_SCROLL;
	
	@ClientString(id = 1801019, message = "You could probably enter the underworld pro league!")
	public static NpcStringId YOU_COULD_PROBABLY_ENTER_THE_UNDERWORLD_PRO_LEAGUE;
	
	@ClientString(id = 1801020, message = "Even pros can't do this much. You're amazing.")
	public static NpcStringId EVEN_PROS_CAN_T_DO_THIS_MUCH_YOU_RE_AMAZING;
	
	@ClientString(id = 1801021, message = "Who's the monster here?! This time, I'll bet my precious Transformation Stick.")
	public static NpcStringId WHO_S_THE_MONSTER_HERE_THIS_TIME_I_LL_BET_MY_PRECIOUS_TRANSFORMATION_STICK;
	
	@ClientString(id = 1801022, message = "I lost again. I won't lose this time. I'm betting my Transformation Stick.")
	public static NpcStringId I_LOST_AGAIN_I_WON_T_LOSE_THIS_TIME_I_M_BETTING_MY_TRANSFORMATION_STICK;
	
	@ClientString(id = 1801023, message = "Lost again! Hmph. Next time, I'll bet an incredible gift! Wait for it if you want!")
	public static NpcStringId LOST_AGAIN_HMPH_NEXT_TIME_I_LL_BET_AN_INCREDIBLE_GIFT_WAIT_FOR_IT_IF_YOU_WANT;
	
	@ClientString(id = 1801024, message = "You're too good. Next time, I'll give you an incredible gift! Please wait for you.")
	public static NpcStringId YOU_RE_TOO_GOOD_NEXT_TIME_I_LL_GIVE_YOU_AN_INCREDIBLE_GIFT_PLEASE_WAIT_FOR_YOU;
	
	@ClientString(id = 1801025, message = "My pride can't handle you winning anymore!")
	public static NpcStringId MY_PRIDE_CAN_T_HANDLE_YOU_WINNING_ANYMORE;
	
	@ClientString(id = 1801026, message = "I would be embarrassing to lose again here...")
	public static NpcStringId I_WOULD_BE_EMBARRASSING_TO_LOSE_AGAIN_HERE;
	
	@ClientString(id = 1801027, message = "What's your name? I'm gonna remember you!")
	public static NpcStringId WHAT_S_YOUR_NAME_I_M_GONNA_REMEMBER_YOU;
	
	@ClientString(id = 1801028, message = "People from the above ground world are really good at games.")
	public static NpcStringId PEOPLE_FROM_THE_ABOVE_GROUND_WORLD_ARE_REALLY_GOOD_AT_GAMES;
	
	@ClientString(id = 1801029, message = "You've played a lot in the underworld, haven't you?!")
	public static NpcStringId YOU_VE_PLAYED_A_LOT_IN_THE_UNDERWORLD_HAVEN_T_YOU;
	
	@ClientString(id = 1801030, message = "I've never met someone so good before.")
	public static NpcStringId I_VE_NEVER_MET_SOMEONE_SO_GOOD_BEFORE;
	
	@ClientString(id = 1801031, message = "13 wins in a row. You're pretty lucky today, huh?")
	public static NpcStringId THIRTEEN_WINS_IN_A_ROW_YOU_RE_PRETTY_LUCKY_TODAY_HUH;
	
	@ClientString(id = 1801032, message = "I never thought I would see 13 wins in a row.")
	public static NpcStringId I_NEVER_THOUGHT_I_WOULD_SEE_13_WINS_IN_A_ROW;
	
	@ClientString(id = 1801033, message = "This is the highest record in my life! Next time, I'll give you my treasure -- the Golden Jack O'Lantern!")
	public static NpcStringId THIS_IS_THE_HIGHEST_RECORD_IN_MY_LIFE_NEXT_TIME_I_LL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_O_LANTERN;
	
	@ClientString(id = 1801034, message = "Even pros can't do 14 wins...! Next time, I will give you my treasure, the Golden Jack O'Lantern.")
	public static NpcStringId EVEN_PROS_CAN_T_DO_14_WINS_NEXT_TIME_I_WILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_O_LANTERN;
	
	@ClientString(id = 1801035, message = "I can't do this anymore! You win! I acknowledge you as the best I've ever met in all my 583 years!")
	public static NpcStringId I_CAN_T_DO_THIS_ANYMORE_YOU_WIN_I_ACKNOWLEDGE_YOU_AS_THE_BEST_I_VE_EVER_MET_IN_ALL_MY_583_YEARS;
	
	@ClientString(id = 1801036, message = "Playing any more is meaningless. You were my greatest opponent.")
	public static NpcStringId PLAYING_ANY_MORE_IS_MEANINGLESS_YOU_WERE_MY_GREATEST_OPPONENT;
	
	@ClientString(id = 1801037, message = "I won this round...! It was fun.")
	public static NpcStringId I_WON_THIS_ROUND_IT_WAS_FUN;
	
	@ClientString(id = 1801038, message = "I won this round. It was enjoyable.")
	public static NpcStringId I_WON_THIS_ROUND_IT_WAS_ENJOYABLE;
	
	@ClientString(id = 1801039, message = "Above world people are so fun...! Then, see you later!")
	public static NpcStringId ABOVE_WORLD_PEOPLE_ARE_SO_FUN_THEN_SEE_YOU_LATER;
	
	@ClientString(id = 1801040, message = "Call me again next time. I want to play again with you.")
	public static NpcStringId CALL_ME_AGAIN_NEXT_TIME_I_WANT_TO_PLAY_AGAIN_WITH_YOU;
	
	@ClientString(id = 1801041, message = "You wanna play some more? I'm out of presents, but I'll give you candy!")
	public static NpcStringId YOU_WANNA_PLAY_SOME_MORE_I_M_OUT_OF_PRESENTS_BUT_I_LL_GIVE_YOU_CANDY;
	
	@ClientString(id = 1801042, message = "Will you play some more? I don't have any more presents, but I will give you candy if you win.")
	public static NpcStringId WILL_YOU_PLAY_SOME_MORE_I_DON_T_HAVE_ANY_MORE_PRESENTS_BUT_I_WILL_GIVE_YOU_CANDY_IF_YOU_WIN;
	
	@ClientString(id = 1801043, message = "You're the best. Out of all the Jack's game players I've ever met... I give up!")
	public static NpcStringId YOU_RE_THE_BEST_OUT_OF_ALL_THE_JACK_S_GAME_PLAYERS_I_VE_EVER_MET_I_GIVE_UP;
	
	@ClientString(id = 1801044, message = "Wowww. Awesome. Really. I have never met someone as good as you before. Now... I can't play anymore.")
	public static NpcStringId WOWWW_AWESOME_REALLY_I_HAVE_NEVER_MET_SOMEONE_AS_GOOD_AS_YOU_BEFORE_NOW_I_CAN_T_PLAY_ANYMORE;
	
	@ClientString(id = 1801045, message = "$s1 has won $s2 Jack's games in a row.")
	public static NpcStringId S1_HAS_WON_S2_JACK_S_GAMES_IN_A_ROW;
	
	@ClientString(id = 1801046, message = "Congratulations! $s1 has won $s2 Jack's games in a row.")
	public static NpcStringId CONGRATULATIONS_S1_HAS_WON_S2_JACK_S_GAMES_IN_A_ROW;
	
	@ClientString(id = 1801047, message = "Congratulations on getting 1st place in Jack's game!")
	public static NpcStringId CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACK_S_GAME;
	
	@ClientString(id = 1801048, message = "Hello~! I'm Belldandy. Congratulations on winning 1st place in Jack's game... If you go and find my sibling Skooldie in the village, you'll get an amazing gift! Let's play Jack's game again!")
	public static NpcStringId HELLO_I_M_BELLDANDY_CONGRATULATIONS_ON_WINNING_1ST_PLACE_IN_JACK_S_GAME_IF_YOU_GO_AND_FIND_MY_SIBLING_SKOOLDIE_IN_THE_VILLAGE_YOU_LL_GET_AN_AMAZING_GIFT_LET_S_PLAY_JACK_S_GAME_AGAIN;
	
	@ClientString(id = 1801049, message = "Hmm. You're playing Jack's game for the first time, huh? You couldn't even take out your card at the right time~! My goodness...")
	public static NpcStringId HMM_YOU_RE_PLAYING_JACK_S_GAME_FOR_THE_FIRST_TIME_HUH_YOU_COULDN_T_EVEN_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME_MY_GOODNESS;
	
	@ClientString(id = 1801050, message = "Oh. You're not very familiar with Jack's game, right? You didn't take out your card at the right time...")
	public static NpcStringId OH_YOU_RE_NOT_VERY_FAMILIAR_WITH_JACK_S_GAME_RIGHT_YOU_DIDN_T_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME;
	
	@ClientString(id = 1801051, message = "You have to use the card skill on the mask before the gauge above my head disappears.")
	public static NpcStringId YOU_HAVE_TO_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;
	
	@ClientString(id = 1801052, message = "You must use the card skill on the mask before the gauge above my head disappears.")
	public static NpcStringId YOU_MUST_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;
	
	@ClientString(id = 1801053, message = "If you show the same card as me, you win. If they're different, I win. Understand? Now, let's go!")
	public static NpcStringId IF_YOU_SHOW_THE_SAME_CARD_AS_ME_YOU_WIN_IF_THEY_RE_DIFFERENT_I_WIN_UNDERSTAND_NOW_LET_S_GO;
	
	@ClientString(id = 1801054, message = "You will win if you show the same card as me. It's my victory if the cards are different. Well, let's start again~")
	public static NpcStringId YOU_WILL_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_IT_S_MY_VICTORY_IF_THE_CARDS_ARE_DIFFERENT_WELL_LET_S_START_AGAIN;
	
	@ClientString(id = 1801055, message = "Ack! You didn't show a card? You have to use the card skill before the gauge disappears. Hmph. Then, I'm going.")
	public static NpcStringId ACK_YOU_DIDN_T_SHOW_A_CARD_YOU_HAVE_TO_USE_THE_CARD_SKILL_BEFORE_THE_GAUGE_DISAPPEARS_HMPH_THEN_I_M_GOING;
	
	@ClientString(id = 1801056, message = "Ahh. You didn't show a card. You must use the card skill at the right time. It's unfortunate. Then, I will go now~")
	public static NpcStringId AHH_YOU_DIDN_T_SHOW_A_CARD_YOU_MUST_USE_THE_CARD_SKILL_AT_THE_RIGHT_TIME_IT_S_UNFORTUNATE_THEN_I_WILL_GO_NOW;
	
	@ClientString(id = 1801057, message = "Let's learn about the Jack's game together~! You can play with me 3 times.")
	public static NpcStringId LET_S_LEARN_ABOUT_THE_JACK_S_GAME_TOGETHER_YOU_CAN_PLAY_WITH_ME_3_TIMES;
	
	@ClientString(id = 1801058, message = "Let's start! Show the card you want! The card skill is attached to the mask.")
	public static NpcStringId LET_S_START_SHOW_THE_CARD_YOU_WANT_THE_CARD_SKILL_IS_ATTACHED_TO_THE_MASK;
	
	@ClientString(id = 1801059, message = "You showed the same card as me, so you win.")
	public static NpcStringId YOU_SHOWED_THE_SAME_CARD_AS_ME_SO_YOU_WIN;
	
	@ClientString(id = 1801060, message = "You showed a different card from me, so you lose.")
	public static NpcStringId YOU_SHOWED_A_DIFFERENT_CARD_FROM_ME_SO_YOU_LOSE;
	
	@ClientString(id = 1801061, message = "That was practice, so there's no candy even if you win~")
	public static NpcStringId THAT_WAS_PRACTICE_SO_THERE_S_NO_CANDY_EVEN_IF_YOU_WIN;
	
	@ClientString(id = 1801062, message = "It's unfortunate. Let's practice one more time.")
	public static NpcStringId IT_S_UNFORTUNATE_LET_S_PRACTICE_ONE_MORE_TIME;
	
	@ClientString(id = 1801063, message = "You gotta show the card at the right time. Use the card skill you want before the gauge above my head disappears!")
	public static NpcStringId YOU_GOTTA_SHOW_THE_CARD_AT_THE_RIGHT_TIME_USE_THE_CARD_SKILL_YOU_WANT_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;
	
	@ClientString(id = 1801064, message = "The card skills are attached to the Jack O'Lantern mask, right? That's what you use.")
	public static NpcStringId THE_CARD_SKILLS_ARE_ATTACHED_TO_THE_JACK_O_LANTERN_MASK_RIGHT_THAT_S_WHAT_YOU_USE;
	
	@ClientString(id = 1801065, message = "You win if you show the same card as me, and I win if the cards are different. OK, let's go~")
	public static NpcStringId YOU_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_AND_I_WIN_IF_THE_CARDS_ARE_DIFFERENT_OK_LET_S_GO;
	
	@ClientString(id = 1801066, message = "You didn't show a card again? We'll try again later. I'm gonna go now~")
	public static NpcStringId YOU_DIDN_T_SHOW_A_CARD_AGAIN_WE_LL_TRY_AGAIN_LATER_I_M_GONNA_GO_NOW;
	
	@ClientString(id = 1801067, message = "Now, do you understand a little about Jack's game? The real game's with Uldie and Belldandy. Well, see you later!")
	public static NpcStringId NOW_DO_YOU_UNDERSTAND_A_LITTLE_ABOUT_JACK_S_GAME_THE_REAL_GAME_S_WITH_ULDIE_AND_BELLDANDY_WELL_SEE_YOU_LATER;
	
	@ClientString(id = 1801068, message = "Hahahaha!")
	public static NpcStringId HAHAHAHA;
	
	@ClientString(id = 1801069, message = "Where are you looking?")
	public static NpcStringId WHERE_ARE_YOU_LOOKING;
	
	@ClientString(id = 1801070, message = "I'm right here.")
	public static NpcStringId I_M_RIGHT_HERE;
	
	@ClientString(id = 1801071, message = "Annoying concentration attacks are disrupting Valakas's concentration!\\nIf it continues, you may get a great opportunity!")
	public static NpcStringId ANNOYING_CONCENTRATION_ATTACKS_ARE_DISRUPTING_VALAKAS_S_CONCENTRATION_NIF_IT_CONTINUES_YOU_MAY_GET_A_GREAT_OPPORTUNITY;
	
	@ClientString(id = 1801072, message = "Some warrior's blow has left a huge gash between the great scales of Valakas!\\nValakas's P. Def. is greatly decreased!")
	public static NpcStringId SOME_WARRIOR_S_BLOW_HAS_LEFT_A_HUGE_GASH_BETWEEN_THE_GREAT_SCALES_OF_VALAKAS_NVALAKAS_S_P_DEF_IS_GREATLY_DECREASED;
	
	@ClientString(id = 1801073, message = "Annoying concentration attacks overwhelmed Valakas, making it forget its rage and become distracted!")
	public static NpcStringId ANNOYING_CONCENTRATION_ATTACKS_OVERWHELMED_VALAKAS_MAKING_IT_FORGET_ITS_RAGE_AND_BECOME_DISTRACTED;
	
	@ClientString(id = 1801074, message = "Long-range concentration attacks have enraged Valakas!\\nIf you continue, it may become a dangerous situation!")
	public static NpcStringId LONG_RANGE_CONCENTRATION_ATTACKS_HAVE_ENRAGED_VALAKAS_NIF_YOU_CONTINUE_IT_MAY_BECOME_A_DANGEROUS_SITUATION;
	
	@ClientString(id = 1801075, message = "Because the cowardly counterattacks continued, Valakas's fury has reached its maximum!\\nValakas's P. Atk. is greatly increased!")
	public static NpcStringId BECAUSE_THE_COWARDLY_COUNTERATTACKS_CONTINUED_VALAKAS_S_FURY_HAS_REACHED_ITS_MAXIMUM_NVALAKAS_S_P_ATK_IS_GREATLY_INCREASED;
	
	@ClientString(id = 1801076, message = "Valakas has been enraged by the long-range concentration attacks and is coming toward its target with even greater zeal!")
	public static NpcStringId VALAKAS_HAS_BEEN_ENRAGED_BY_THE_LONG_RANGE_CONCENTRATION_ATTACKS_AND_IS_COMING_TOWARD_ITS_TARGET_WITH_EVEN_GREATER_ZEAL;
	
	@ClientString(id = 1801077, message = "Listen, oh Tantas! I have returned! The Prophet Yugoros of the Black Abyss is with me, so do not be afraid!")
	public static NpcStringId LISTEN_OH_TANTAS_I_HAVE_RETURNED_THE_PROPHET_YUGOROS_OF_THE_BLACK_ABYSS_IS_WITH_ME_SO_DO_NOT_BE_AFRAID;
	
	@ClientString(id = 1801078, message = "Welcome, $s1! Let us see if you have brought a worthy offering for the Black Abyss!")
	public static NpcStringId WELCOME_S1_LET_US_SEE_IF_YOU_HAVE_BROUGHT_A_WORTHY_OFFERING_FOR_THE_BLACK_ABYSS;
	
	@ClientString(id = 1801079, message = "What a formidable foe! But I have the Abyss Weed given to me by the Black Abyss! Let me see...")
	public static NpcStringId WHAT_A_FORMIDABLE_FOE_BUT_I_HAVE_THE_ABYSS_WEED_GIVEN_TO_ME_BY_THE_BLACK_ABYSS_LET_ME_SEE;
	
	@ClientString(id = 1801080, message = "Muhahaha! Ah, this burning sensation in my mouth! The power of the Black Abyss is reviving me!")
	public static NpcStringId MUHAHAHA_AH_THIS_BURNING_SENSATION_IN_MY_MOUTH_THE_POWER_OF_THE_BLACK_ABYSS_IS_REVIVING_ME;
	
	@ClientString(id = 1801081, message = "No! How dare you stop me from using the Abyss Weed... Do you know what you have done?!")
	public static NpcStringId NO_HOW_DARE_YOU_STOP_ME_FROM_USING_THE_ABYSS_WEED_DO_YOU_KNOW_WHAT_YOU_HAVE_DONE;
	
	@ClientString(id = 1801082, message = "A limp creature like this is unworthy to be an offering... You have no right to sacrifice to the Black Abyss!")
	public static NpcStringId A_LIMP_CREATURE_LIKE_THIS_IS_UNWORTHY_TO_BE_AN_OFFERING_YOU_HAVE_NO_RIGHT_TO_SACRIFICE_TO_THE_BLACK_ABYSS;
	
	@ClientString(id = 1801083, message = "Listen, oh Tantas! The Black Abyss is famished! Find some fresh offerings!")
	public static NpcStringId LISTEN_OH_TANTAS_THE_BLACK_ABYSS_IS_FAMISHED_FIND_SOME_FRESH_OFFERINGS;
	
	@ClientString(id = 1801084, message = "Ah... How could I lose... Oh, Black Abyss, receive me...")
	public static NpcStringId AH_HOW_COULD_I_LOSE_OH_BLACK_ABYSS_RECEIVE_ME;
	
	@ClientString(id = 1801085, message = "Alarm system destroyed. Intruder excluded.")
	public static NpcStringId ALARM_SYSTEM_DESTROYED_INTRUDER_EXCLUDED;
	
	@ClientString(id = 1801086, message = "Begin stage 1")
	public static NpcStringId BEGIN_STAGE_1_2;
	
	@ClientString(id = 1801087, message = "Begin stage 2")
	public static NpcStringId BEGIN_STAGE_2_2;
	
	@ClientString(id = 1801088, message = "Begin stage 3")
	public static NpcStringId BEGIN_STAGE_3_2;
	
	@ClientString(id = 1801089, message = "Begin stage 4")
	public static NpcStringId BEGIN_STAGE_4;
	
	@ClientString(id = 1801090, message = "Time remaining until next battle")
	public static NpcStringId TIME_REMAINING_UNTIL_NEXT_BATTLE;
	
	@ClientString(id = 1801091, message = "The beast ate the feed, but it isn't showing a response, perhaps because it's already full.")
	public static NpcStringId THE_BEAST_ATE_THE_FEED_BUT_IT_ISN_T_SHOWING_A_RESPONSE_PERHAPS_BECAUSE_IT_S_ALREADY_FULL;
	
	@ClientString(id = 1801092, message = "The beast spit out the feed instead of eating it.")
	public static NpcStringId THE_BEAST_SPIT_OUT_THE_FEED_INSTEAD_OF_EATING_IT;
	
	@ClientString(id = 1801093, message = "The beast spit out the feed and is instead attacking you!")
	public static NpcStringId THE_BEAST_SPIT_OUT_THE_FEED_AND_IS_INSTEAD_ATTACKING_YOU;
	
	@ClientString(id = 1801094, message = "$s1 is leaving you because you don't have enough Golden Spices.")
	public static NpcStringId S1_IS_LEAVING_YOU_BECAUSE_YOU_DON_T_HAVE_ENOUGH_GOLDEN_SPICES;
	
	@ClientString(id = 1801095, message = "$s1 is leaving you because you don't have enough Crystal Spices.")
	public static NpcStringId S1_IS_LEAVING_YOU_BECAUSE_YOU_DON_T_HAVE_ENOUGH_CRYSTAL_SPICES;
	
	@ClientString(id = 1801096, message = "$s1. May the protection of the gods be upon you!")
	public static NpcStringId S1_MAY_THE_PROTECTION_OF_THE_GODS_BE_UPON_YOU;
	
	@ClientString(id = 1801097, message = "Freya has started to move.")
	public static NpcStringId FREYA_HAS_STARTED_TO_MOVE;
	
	@ClientString(id = 1801098, message = "How could I... fall... in a place like this...")
	public static NpcStringId HOW_COULD_I_FALL_IN_A_PLACE_LIKE_THIS;
	
	@ClientString(id = 1801099, message = "I can finally take a breather. By the way, who are you? Hmm... I think I know who sent you. ")
	public static NpcStringId I_CAN_FINALLY_TAKE_A_BREATHER_BY_THE_WAY_WHO_ARE_YOU_HMM_I_THINK_I_KNOW_WHO_SENT_YOU;
	
	@ClientString(id = 1801100, message = "$s1 of Balance")
	public static NpcStringId S1_OF_BALANCE;
	
	@ClientString(id = 1801101, message = "Swift $s1")
	public static NpcStringId SWIFT_S1;
	
	@ClientString(id = 1801102, message = "$s1 of Blessing")
	public static NpcStringId S1_OF_BLESSING;
	
	@ClientString(id = 1801103, message = "Sharp $s1")
	public static NpcStringId SHARP_S1;
	
	@ClientString(id = 1801104, message = "Useful $s1")
	public static NpcStringId USEFUL_S1;
	
	@ClientString(id = 1801105, message = "Reckless $s1")
	public static NpcStringId RECKLESS_S1;
	
	@ClientString(id = 1801106, message = "Alpen Kookaburra")
	public static NpcStringId ALPEN_KOOKABURRA;
	
	@ClientString(id = 1801107, message = "Alpen Cougar")
	public static NpcStringId ALPEN_COUGAR;
	
	@ClientString(id = 1801108, message = "Alpen Buffalo")
	public static NpcStringId ALPEN_BUFFALO;
	
	@ClientString(id = 1801109, message = "Alpen Grendel")
	public static NpcStringId ALPEN_GRENDEL;
	
	@ClientString(id = 1801110, message = "Battle end limit time")
	public static NpcStringId BATTLE_END_LIMIT_TIME;
	
	@ClientString(id = 1801111, message = "Strong magic power can be felt from somewhere!!")
	public static NpcStringId STRONG_MAGIC_POWER_CAN_BE_FELT_FROM_SOMEWHERE;
	
	@ClientString(id = 1801112, message = "How dare you attack my recruits!!")
	public static NpcStringId HOW_DARE_YOU_ATTACK_MY_RECRUITS;
	
	@ClientString(id = 1801113, message = "Who is disrupting the order?!")
	public static NpcStringId WHO_IS_DISRUPTING_THE_ORDER;
	
	@ClientString(id = 1801114, message = "The drillmaster is dead!")
	public static NpcStringId THE_DRILLMASTER_IS_DEAD;
	
	@ClientString(id = 1801115, message = "Line up the ranks!!")
	public static NpcStringId LINE_UP_THE_RANKS;
	
	@ClientString(id = 1801116, message = "I brought the food.")
	public static NpcStringId I_BROUGHT_THE_FOOD;
	
	@ClientString(id = 1801117, message = "Come and eat.")
	public static NpcStringId COME_AND_EAT;
	
	@ClientString(id = 1801118, message = "Looks delicious.")
	public static NpcStringId LOOKS_DELICIOUS;
	
	@ClientString(id = 1801119, message = "Let's go eat.")
	public static NpcStringId LET_S_GO_EAT;
	
	@ClientString(id = 1801120, message = "Archer. Give your breath for the intruder!")
	public static NpcStringId ARCHER_GIVE_YOUR_BREATH_FOR_THE_INTRUDER;
	
	@ClientString(id = 1801121, message = "My knights. Show your loyalty!!")
	public static NpcStringId MY_KNIGHTS_SHOW_YOUR_LOYALTY;
	
	@ClientString(id = 1801122, message = "I can take it no longer!!!")
	public static NpcStringId I_CAN_TAKE_IT_NO_LONGER;
	
	@ClientString(id = 1801123, message = "Archer. Heed my call!")
	public static NpcStringId ARCHER_HEED_MY_CALL;
	
	@ClientString(id = 1801124, message = "The space feels like its gradually starting to shake.")
	public static NpcStringId THE_SPACE_FEELS_LIKE_ITS_GRADUALLY_STARTING_TO_SHAKE;
	
	@ClientString(id = 1801125, message = "I can no longer stand by.")
	public static NpcStringId I_CAN_NO_LONGER_STAND_BY;
	
	@ClientString(id = 1801126, message = "Taklacan is gathering its strength and launching an attack.")
	public static NpcStringId TAKLACAN_IS_GATHERING_ITS_STRENGTH_AND_LAUNCHING_AN_ATTACK;
	
	@ClientString(id = 1801127, message = "Taklacan received Cokrakon and became weaker.")
	public static NpcStringId TAKLACAN_RECEIVED_COKRAKON_AND_BECAME_WEAKER;
	
	@ClientString(id = 1801128, message = "Cokrakon's power can be felt nearby.")
	public static NpcStringId COKRAKON_S_POWER_CAN_BE_FELT_NEARBY;
	
	@ClientString(id = 1801129, message = "Taklacan is preparing to hide itself.")
	public static NpcStringId TAKLACAN_IS_PREPARING_TO_HIDE_ITSELF;
	
	@ClientString(id = 1801130, message = "Taklacan disappears into the space of futility with a roar.")
	public static NpcStringId TAKLACAN_DISAPPEARS_INTO_THE_SPACE_OF_FUTILITY_WITH_A_ROAR;
	
	@ClientString(id = 1801131, message = "Torumba's body is starting to move.")
	public static NpcStringId TORUMBA_S_BODY_IS_STARTING_TO_MOVE;
	
	@ClientString(id = 1801132, message = "A response can be felt from Torumba's tough skin.")
	public static NpcStringId A_RESPONSE_CAN_BE_FELT_FROM_TORUMBA_S_TOUGH_SKIN;
	
	@ClientString(id = 1801133, message = "A mark remains on Torumba's tough skin.")
	public static NpcStringId A_MARK_REMAINS_ON_TORUMBA_S_TOUGH_SKIN;
	
	@ClientString(id = 1801134, message = "The light is beginning to weaken in Torumba's eyes.")
	public static NpcStringId THE_LIGHT_IS_BEGINNING_TO_WEAKEN_IN_TORUMBA_S_EYES;
	
	@ClientString(id = 1801135, message = "Torumba's left leg was damaged.")
	public static NpcStringId TORUMBA_S_LEFT_LEG_WAS_DAMAGED;
	
	@ClientString(id = 1801136, message = "Torumba's right leg was damaged.")
	public static NpcStringId TORUMBA_S_RIGHT_LEG_WAS_DAMAGED;
	
	@ClientString(id = 1801137, message = "Torumba's left arm was damaged.")
	public static NpcStringId TORUMBA_S_LEFT_ARM_WAS_DAMAGED;
	
	@ClientString(id = 1801138, message = "Torumba's right arm was damaged.")
	public static NpcStringId TORUMBA_S_RIGHT_ARM_WAS_DAMAGED;
	
	@ClientString(id = 1801139, message = "A deep wound appeared on Torumba's tough skin.")
	public static NpcStringId A_DEEP_WOUND_APPEARED_ON_TORUMBA_S_TOUGH_SKIN;
	
	@ClientString(id = 1801140, message = "The light is slowly fading from Torumba's eyes.")
	public static NpcStringId THE_LIGHT_IS_SLOWLY_FADING_FROM_TORUMBA_S_EYES;
	
	@ClientString(id = 1801141, message = "Torumba is preparing to hide its body.")
	public static NpcStringId TORUMBA_IS_PREPARING_TO_HIDE_ITS_BODY;
	
	@ClientString(id = 1801142, message = "Torumba disappeared into his space.")
	public static NpcStringId TORUMBA_DISAPPEARED_INTO_HIS_SPACE;
	
	@ClientString(id = 1801143, message = "Torumba is preparing to hide itself in the twisted space.")
	public static NpcStringId TORUMBA_IS_PREPARING_TO_HIDE_ITSELF_IN_THE_TWISTED_SPACE;
	
	@ClientString(id = 1801144, message = "Dopagen has started to move.")
	public static NpcStringId DOPAGEN_HAS_STARTED_TO_MOVE;
	
	@ClientString(id = 1801145, message = "Leptilikon's energy feels like it's being condensed.")
	public static NpcStringId LEPTILIKON_S_ENERGY_FEELS_LIKE_IT_S_BEING_CONDENSED;
	
	@ClientString(id = 1801146, message = "Dopagen is preparing to hide itself with a strange scent.")
	public static NpcStringId DOPAGEN_IS_PREPARING_TO_HIDE_ITSELF_WITH_A_STRANGE_SCENT;
	
	@ClientString(id = 1801147, message = "Dopagen is preparing to hide itself.")
	public static NpcStringId DOPAGEN_IS_PREPARING_TO_HIDE_ITSELF;
	
	@ClientString(id = 1801148, message = "Dopagen is disappearing in between the twisted space.")
	public static NpcStringId DOPAGEN_IS_DISAPPEARING_IN_BETWEEN_THE_TWISTED_SPACE;
	
	@ClientString(id = 1801149, message = "Maguen appearance!!!")
	public static NpcStringId MAGUEN_APPEARANCE;
	
	@ClientString(id = 1801150, message = "Enough Maguen Plasma - Bistakon have gathered.")
	public static NpcStringId ENOUGH_MAGUEN_PLASMA_BISTAKON_HAVE_GATHERED;
	
	@ClientString(id = 1801151, message = "Enough Maguen Plasma - Cokrakon have gathered.")
	public static NpcStringId ENOUGH_MAGUEN_PLASMA_COKRAKON_HAVE_GATHERED;
	
	@ClientString(id = 1801152, message = "Enough Maguen Plasma - Leptilikon have gathered.")
	public static NpcStringId ENOUGH_MAGUEN_PLASMA_LEPTILIKON_HAVE_GATHERED;
	
	@ClientString(id = 1801153, message = "The plasmas have filled the aeroscope and are harmonized.")
	public static NpcStringId THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_AND_ARE_HARMONIZED;
	
	@ClientString(id = 1801154, message = "The plasmas have filled the aeroscope but they are ramming into each other, exploding, and dying.")
	public static NpcStringId THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_BUT_THEY_ARE_RAMMING_INTO_EACH_OTHER_EXPLODING_AND_DYING;
	
	@ClientString(id = 1801155, message = "Amazing. $s1 took 100 of these soul stone fragments. What a complete swindler.")
	public static NpcStringId AMAZING_S1_TOOK_100_OF_THESE_SOUL_STONE_FRAGMENTS_WHAT_A_COMPLETE_SWINDLER;
	
	@ClientString(id = 1801156, message = "Hmm? Hey, did you give $s1 something? But it was just 1. Haha.")
	public static NpcStringId HMM_HEY_DID_YOU_GIVE_S1_SOMETHING_BUT_IT_WAS_JUST_1_HAHA;
	
	@ClientString(id = 1801157, message = "$s1 pulled one with $s2 digits. Lucky~ Not bad~")
	public static NpcStringId S1_PULLED_ONE_WITH_S2_DIGITS_LUCKY_NOT_BAD;
	
	@ClientString(id = 1801158, message = "It's better than losing it all, right? Or does this feel worse?")
	public static NpcStringId IT_S_BETTER_THAN_LOSING_IT_ALL_RIGHT_OR_DOES_THIS_FEEL_WORSE;
	
	@ClientString(id = 1801159, message = "Ahem~! $s1 has no luck at all. Try praying.")
	public static NpcStringId AHEM_S1_HAS_NO_LUCK_AT_ALL_TRY_PRAYING;
	
	@ClientString(id = 1801160, message = "Ah... It's over. What kind of guy is that? Damn... Fine, you $s1, take it and get outta here.")
	public static NpcStringId AH_IT_S_OVER_WHAT_KIND_OF_GUY_IS_THAT_DAMN_FINE_YOU_S1_TAKE_IT_AND_GET_OUTTA_HERE;
	
	@ClientString(id = 1801161, message = "A big piece is made up of little pieces. So here's a little piece~")
	public static NpcStringId A_BIG_PIECE_IS_MADE_UP_OF_LITTLE_PIECES_SO_HERE_S_A_LITTLE_PIECE;
	
	@ClientString(id = 1801162, message = "You don't feel bad, right? Are you sad? But don't cry~")
	public static NpcStringId YOU_DON_T_FEEL_BAD_RIGHT_ARE_YOU_SAD_BUT_DON_T_CRY;
	
	@ClientString(id = 1801163, message = "OK~ Who's next? It all depends on your fate and luck, right? At least come and take a look.")
	public static NpcStringId OK_WHO_S_NEXT_IT_ALL_DEPENDS_ON_YOUR_FATE_AND_LUCK_RIGHT_AT_LEAST_COME_AND_TAKE_A_LOOK;
	
	@ClientString(id = 1801164, message = "No one else? Don't worry~ I don't bite. Haha~!")
	public static NpcStringId NO_ONE_ELSE_DON_T_WORRY_I_DON_T_BITE_HAHA;
	
	@ClientString(id = 1801165, message = "There was someone who won 10,000 from me. A warrior shouldn't just be good at fighting, right? You've gotta be good in everything.")
	public static NpcStringId THERE_WAS_SOMEONE_WHO_WON_10_000_FROM_ME_A_WARRIOR_SHOULDN_T_JUST_BE_GOOD_AT_FIGHTING_RIGHT_YOU_VE_GOTTA_BE_GOOD_IN_EVERYTHING;
	
	@ClientString(id = 1801166, message = "OK~ Master of luck? That's you? Haha~! Well, anyone can come after all.")
	public static NpcStringId OK_MASTER_OF_LUCK_THAT_S_YOU_HAHA_WELL_ANYONE_CAN_COME_AFTER_ALL;
	
	@ClientString(id = 1801167, message = "Shedding blood is a given on the battlefield. At least it's safe here.")
	public static NpcStringId SHEDDING_BLOOD_IS_A_GIVEN_ON_THE_BATTLEFIELD_AT_LEAST_IT_S_SAFE_HERE;
	
	@ClientString(id = 1801168, message = "Gasp!")
	public static NpcStringId GASP;
	
	@ClientString(id = 1801169, message = "Is it still long off?")
	public static NpcStringId IS_IT_STILL_LONG_OFF;
	
	@ClientString(id = 1801170, message = "Is Ermian well? Even I can't believe that I survived in a place like this...")
	public static NpcStringId IS_ERMIAN_WELL_EVEN_I_CAN_T_BELIEVE_THAT_I_SURVIVED_IN_A_PLACE_LIKE_THIS;
	
	@ClientString(id = 1801171, message = "I don't know how long it's been since I parted company with you... Time doesn't seem to move... It just feels too long...")
	public static NpcStringId I_DON_T_KNOW_HOW_LONG_IT_S_BEEN_SINCE_I_PARTED_COMPANY_WITH_YOU_TIME_DOESN_T_SEEM_TO_MOVE_IT_JUST_FEELS_TOO_LONG;
	
	@ClientString(id = 1801172, message = "Sorry to say this, but... The place you struck me before now hurts greatly...")
	public static NpcStringId SORRY_TO_SAY_THIS_BUT_THE_PLACE_YOU_STRUCK_ME_BEFORE_NOW_HURTS_GREATLY;
	
	@ClientString(id = 1801173, message = "Ugh... I'm sorry... It looks like this is it for me... I wanted to live and see my family...")
	public static NpcStringId UGH_I_M_SORRY_IT_LOOKS_LIKE_THIS_IS_IT_FOR_ME_I_WANTED_TO_LIVE_AND_SEE_MY_FAMILY;
	
	@ClientString(id = 1801174, message = "Where are you? I can't see anything.")
	public static NpcStringId WHERE_ARE_YOU_I_CAN_T_SEE_ANYTHING;
	
	@ClientString(id = 1801175, message = "Where are you, really? I can't follow you like this.")
	public static NpcStringId WHERE_ARE_YOU_REALLY_I_CAN_T_FOLLOW_YOU_LIKE_THIS;
	
	@ClientString(id = 1801176, message = "I'm sorry... This is it for me.")
	public static NpcStringId I_M_SORRY_THIS_IS_IT_FOR_ME;
	
	@ClientString(id = 1801177, message = "Sob~ To see Ermian again... Can I go to my family now?")
	public static NpcStringId SOB_TO_SEE_ERMIAN_AGAIN_CAN_I_GO_TO_MY_FAMILY_NOW;
	
	@ClientString(id = 1801178, message = "Feel kind of funny… but also so joyful.")
	public static NpcStringId FEEL_KIND_OF_FUNNY_BUT_ALSO_SO_JOYFUL;
	
	@ClientString(id = 1801179, message = "I'm feeling, less angry… what is this witchcraft?")
	public static NpcStringId I_M_FEELING_LESS_ANGRY_WHAT_IS_THIS_WITCHCRAFT_2;
	
	@ClientString(id = 1801180, message = "Whew… I need a nap now.")
	public static NpcStringId WHEW_I_NEED_A_NAP_NOW;
	
	@ClientString(id = 1801181, message = "That tickles… I still want you dead.")
	public static NpcStringId THAT_TICKLES_I_STILL_WANT_YOU_DEAD;
	
	@ClientString(id = 1801182, message = "I'm on cloud nine!")
	public static NpcStringId I_M_ON_CLOUD_NINE;
	
	@ClientString(id = 1801183, message = "Thanks for taking care of my rage $s1!")
	public static NpcStringId THANKS_FOR_TAKING_CARE_OF_MY_RAGE_S1;
	
	@ClientString(id = 1801184, message = "Squeee! Are you a rabbit whisperer $s1, cause I feel great.")
	public static NpcStringId SQUEEE_ARE_YOU_A_RABBIT_WHISPERER_S1_CAUSE_I_FEEL_GREAT_2;
	
	@ClientString(id = 1801185, message = "Boy, the next time I feel like going on a rampage I hope you are there $s1!")
	public static NpcStringId BOY_THE_NEXT_TIME_I_FEEL_LIKE_GOING_ON_A_RAMPAGE_I_HOPE_YOU_ARE_THERE_S1_2;
	
	@ClientString(id = 1801186, message = "Wow! I feel so warm and fuzzy now!")
	public static NpcStringId WOW_I_FEEL_SO_WARM_AND_FUZZY_NOW_2;
	
	@ClientString(id = 1801187, message = "I feel so happy now.")
	public static NpcStringId I_FEEL_SO_HAPPY_NOW_2;
	
	@ClientString(id = 1801188, message = "Words cannot express my happiness!")
	public static NpcStringId WORDS_CANNOT_EXPRESS_MY_HAPPINESS_2;
	
	@ClientString(id = 1801189, message = "Magic power so strong that it could make you lose your mind can be felt from somewhere!!")
	public static NpcStringId MAGIC_POWER_SO_STRONG_THAT_IT_COULD_MAKE_YOU_LOSE_YOUR_MIND_CAN_BE_FELT_FROM_SOMEWHERE;
	
	@ClientString(id = 1801190, message = "Even though you bring something called a gift among your humans, it would just be problematic for me...")
	public static NpcStringId EVEN_THOUGH_YOU_BRING_SOMETHING_CALLED_A_GIFT_AMONG_YOUR_HUMANS_IT_WOULD_JUST_BE_PROBLEMATIC_FOR_ME;
	
	@ClientString(id = 1801191, message = "I just don't know what expression I should have it appeared on me. Are human's emotions like this feeling?")
	public static NpcStringId I_JUST_DON_T_KNOW_WHAT_EXPRESSION_I_SHOULD_HAVE_IT_APPEARED_ON_ME_ARE_HUMAN_S_EMOTIONS_LIKE_THIS_FEELING;
	
	@ClientString(id = 1801192, message = "The feeling of thanks is just too much distant memory for me...")
	public static NpcStringId THE_FEELING_OF_THANKS_IS_JUST_TOO_MUCH_DISTANT_MEMORY_FOR_ME;
	
	@ClientString(id = 1801193, message = "But I kind of miss it... Like I had felt this feeling before...")
	public static NpcStringId BUT_I_KIND_OF_MISS_IT_LIKE_I_HAD_FELT_THIS_FEELING_BEFORE;
	
	@ClientString(id = 1801194, message = "I am Ice Queen Freya... This feeling and emotion are nothing but a part of Melissa'a memories.")
	public static NpcStringId I_AM_ICE_QUEEN_FREYA_THIS_FEELING_AND_EMOTION_ARE_NOTHING_BUT_A_PART_OF_MELISSA_A_MEMORIES;
	
	@ClientString(id = 1801195, message = "Dear $s1... Think of this as my appreciation for the gift. Take this with you. There's nothing strange about it. It's just a bit of my capriciousness...")
	public static NpcStringId DEAR_S1_THINK_OF_THIS_AS_MY_APPRECIATION_FOR_THE_GIFT_TAKE_THIS_WITH_YOU_THERE_S_NOTHING_STRANGE_ABOUT_IT_IT_S_JUST_A_BIT_OF_MY_CAPRICIOUSNESS;
	
	@ClientString(id = 1801196, message = "The kindness of somebody is not a bad feeling... Dear $s1, I will take this gift out of respect your kindness.")
	public static NpcStringId THE_KINDNESS_OF_SOMEBODY_IS_NOT_A_BAD_FEELING_DEAR_S1_I_WILL_TAKE_THIS_GIFT_OUT_OF_RESPECT_YOUR_KINDNESS;
	
	@ClientString(id = 1801197, message = "$s1 $s2 $s3 minute(s) remaining!")
	public static NpcStringId S1_S2_S3_MINUTE_S_REMAINING;
	
	@ClientString(id = 1811000, message = "Fighter")
	public static NpcStringId FIGHTER;
	
	@ClientString(id = 1811001, message = "Warrior")
	public static NpcStringId WARRIOR;
	
	@ClientString(id = 1811002, message = "Gladiator")
	public static NpcStringId GLADIATOR;
	
	@ClientString(id = 1811003, message = "Warlord")
	public static NpcStringId WARLORD;
	
	@ClientString(id = 1811004, message = "Knight")
	public static NpcStringId KNIGHT;
	
	@ClientString(id = 1811005, message = "Paladin")
	public static NpcStringId PALADIN;
	
	@ClientString(id = 1811006, message = "Dark Avenger")
	public static NpcStringId DARK_AVENGER;
	
	@ClientString(id = 1811007, message = "Rogue")
	public static NpcStringId ROGUE;
	
	@ClientString(id = 1811008, message = "Treasure Hunter")
	public static NpcStringId TREASURE_HUNTER;
	
	@ClientString(id = 1811009, message = "Hawkeye")
	public static NpcStringId HAWKEYE;
	
	@ClientString(id = 1811010, message = "Mage")
	public static NpcStringId MAGE;
	
	@ClientString(id = 1811011, message = "Wizard")
	public static NpcStringId WIZARD;
	
	@ClientString(id = 1811012, message = "Sorcerer")
	public static NpcStringId SORCERER;
	
	@ClientString(id = 1811013, message = "Necromancer")
	public static NpcStringId NECROMANCER;
	
	@ClientString(id = 1811014, message = "Warlock")
	public static NpcStringId WARLOCK;
	
	@ClientString(id = 1811015, message = "Cleric")
	public static NpcStringId CLERIC;
	
	@ClientString(id = 1811016, message = "Bishop")
	public static NpcStringId BISHOP;
	
	@ClientString(id = 1811017, message = "Prophet")
	public static NpcStringId PROPHET;
	
	@ClientString(id = 1811018, message = "Elven Fighter")
	public static NpcStringId ELVEN_FIGHTER;
	
	@ClientString(id = 1811019, message = "Elven Knight")
	public static NpcStringId ELVEN_KNIGHT;
	
	@ClientString(id = 1811020, message = "Temple Knight")
	public static NpcStringId TEMPLE_KNIGHT;
	
	@ClientString(id = 1811021, message = "Sword Singer")
	public static NpcStringId SWORD_SINGER;
	
	@ClientString(id = 1811022, message = "Elven Scout")
	public static NpcStringId ELVEN_SCOUT;
	
	@ClientString(id = 1811023, message = "Plain Walker")
	public static NpcStringId PLAIN_WALKER;
	
	@ClientString(id = 1811024, message = "Silver Ranger")
	public static NpcStringId SILVER_RANGER;
	
	@ClientString(id = 1811025, message = "Elven Mage")
	public static NpcStringId ELVEN_MAGE;
	
	@ClientString(id = 1811026, message = "Elven Wizard")
	public static NpcStringId ELVEN_WIZARD;
	
	@ClientString(id = 1811027, message = "Spell Singer")
	public static NpcStringId SPELL_SINGER;
	
	@ClientString(id = 1811028, message = "Elemental Summoner")
	public static NpcStringId ELEMENTAL_SUMMONER;
	
	@ClientString(id = 1811029, message = "Oracle")
	public static NpcStringId ORACLE;
	
	@ClientString(id = 1811030, message = "Elder")
	public static NpcStringId ELDER;
	
	@ClientString(id = 1811031, message = "Dark Fighter")
	public static NpcStringId DARK_FIGHTER;
	
	@ClientString(id = 1811032, message = "Palace Knight")
	public static NpcStringId PALACE_KNIGHT;
	
	@ClientString(id = 1811033, message = "Shillien Knight")
	public static NpcStringId SHILLIEN_KNIGHT;
	
	@ClientString(id = 1811034, message = "Blade Dancer")
	public static NpcStringId BLADE_DANCER;
	
	@ClientString(id = 1811035, message = "Assassin")
	public static NpcStringId ASSASSIN;
	
	@ClientString(id = 1811036, message = "Abyss Walker")
	public static NpcStringId ABYSS_WALKER;
	
	@ClientString(id = 1811037, message = "Phantom Ranger")
	public static NpcStringId PHANTOM_RANGER;
	
	@ClientString(id = 1811038, message = "Dark Mage")
	public static NpcStringId DARK_MAGE;
	
	@ClientString(id = 1811039, message = "Dark Wizard")
	public static NpcStringId DARK_WIZARD;
	
	@ClientString(id = 1811040, message = "Spellhowler")
	public static NpcStringId SPELLHOWLER;
	
	@ClientString(id = 1811041, message = "Phantom Summoner")
	public static NpcStringId PHANTOM_SUMMONER;
	
	@ClientString(id = 1811042, message = "Shillien Oracle")
	public static NpcStringId SHILLIEN_ORACLE;
	
	@ClientString(id = 1811043, message = "Shillien Elder")
	public static NpcStringId SHILLIEN_ELDER;
	
	@ClientString(id = 1811044, message = "Orc Fighter")
	public static NpcStringId ORC_FIGHTER;
	
	@ClientString(id = 1811045, message = "Orc Raider")
	public static NpcStringId ORC_RAIDER;
	
	@ClientString(id = 1811046, message = "Destroyer")
	public static NpcStringId DESTROYER;
	
	@ClientString(id = 1811047, message = "Orc Monk")
	public static NpcStringId ORC_MONK;
	
	@ClientString(id = 1811048, message = "Tyrant")
	public static NpcStringId TYRANT;
	
	@ClientString(id = 1811049, message = "Orc Mage")
	public static NpcStringId ORC_MAGE;
	
	@ClientString(id = 1811050, message = "Orc Shaman")
	public static NpcStringId ORC_SHAMAN;
	
	@ClientString(id = 1811051, message = "Overlord")
	public static NpcStringId OVERLORD;
	
	@ClientString(id = 1811052, message = "Warcryer")
	public static NpcStringId WARCRYER;
	
	@ClientString(id = 1811053, message = "Dwarven Fighter")
	public static NpcStringId DWARVEN_FIGHTER;
	
	@ClientString(id = 1811054, message = "Scavenger")
	public static NpcStringId SCAVENGER;
	
	@ClientString(id = 1811055, message = "Bounty Hunter")
	public static NpcStringId BOUNTY_HUNTER;
	
	@ClientString(id = 1811056, message = "Artisan")
	public static NpcStringId ARTISAN;
	
	@ClientString(id = 1811057, message = "Warsmith")
	public static NpcStringId WARSMITH;
	
	@ClientString(id = 1811088, message = "Duelist")
	public static NpcStringId DUELIST;
	
	@ClientString(id = 1811089, message = "Dreadnought")
	public static NpcStringId DREADNOUGHT;
	
	@ClientString(id = 1811090, message = "Phoenix Knight")
	public static NpcStringId PHOENIX_KNIGHT;
	
	@ClientString(id = 1811091, message = "Hell Knight")
	public static NpcStringId HELL_KNIGHT;
	
	@ClientString(id = 1811092, message = "Sagittarius")
	public static NpcStringId SAGITTARIUS;
	
	@ClientString(id = 1811093, message = "Adventurer")
	public static NpcStringId ADVENTURER;
	
	@ClientString(id = 1811094, message = "Archmage")
	public static NpcStringId ARCHMAGE;
	
	@ClientString(id = 1811095, message = "Soultaker")
	public static NpcStringId SOULTAKER;
	
	@ClientString(id = 1811096, message = "Arcana Lord")
	public static NpcStringId ARCANA_LORD;
	
	@ClientString(id = 1811097, message = "Cardinal")
	public static NpcStringId CARDINAL;
	
	@ClientString(id = 1811098, message = "Hierophant")
	public static NpcStringId HIEROPHANT;
	
	@ClientString(id = 1811099, message = "Eva's Templar")
	public static NpcStringId EVA_S_TEMPLAR;
	
	@ClientString(id = 1811100, message = "Sword Muse")
	public static NpcStringId SWORD_MUSE;
	
	@ClientString(id = 1811101, message = "Wind Rider")
	public static NpcStringId WIND_RIDER;
	
	@ClientString(id = 1811102, message = "Moonlight Sentinel")
	public static NpcStringId MOONLIGHT_SENTINEL;
	
	@ClientString(id = 1811103, message = "Mystic Muse")
	public static NpcStringId MYSTIC_MUSE;
	
	@ClientString(id = 1811104, message = "Elemental Master")
	public static NpcStringId ELEMENTAL_MASTER;
	
	@ClientString(id = 1811105, message = "Eva's Saint")
	public static NpcStringId EVA_S_SAINT;
	
	@ClientString(id = 1811106, message = "Shillien Templar")
	public static NpcStringId SHILLIEN_TEMPLAR;
	
	@ClientString(id = 1811107, message = "Spectral Dancer")
	public static NpcStringId SPECTRAL_DANCER;
	
	@ClientString(id = 1811108, message = "Ghost Hunter")
	public static NpcStringId GHOST_HUNTER;
	
	@ClientString(id = 1811109, message = "Ghost Sentinel")
	public static NpcStringId GHOST_SENTINEL;
	
	@ClientString(id = 1811110, message = "Storm Screamer")
	public static NpcStringId STORM_SCREAMER;
	
	@ClientString(id = 1811111, message = "Spectral Master")
	public static NpcStringId SPECTRAL_MASTER;
	
	@ClientString(id = 1811112, message = "Shillien Saint")
	public static NpcStringId SHILLIEN_SAINT;
	
	@ClientString(id = 1811113, message = "Titan")
	public static NpcStringId TITAN;
	
	@ClientString(id = 1811114, message = "Grand Khavatari")
	public static NpcStringId GRAND_KHAVATARI;
	
	@ClientString(id = 1811115, message = "Dominator")
	public static NpcStringId DOMINATOR;
	
	@ClientString(id = 1811116, message = "Doom Cryer")
	public static NpcStringId DOOM_CRYER;
	
	@ClientString(id = 1811117, message = "Fortune Seeker")
	public static NpcStringId FORTUNE_SEEKER;
	
	@ClientString(id = 1811118, message = "Maestro")
	public static NpcStringId MAESTRO;
	
	@ClientString(id = 1811123, message = "Kamael Soldier")
	public static NpcStringId KAMAEL_SOLDIER;
	
	@ClientString(id = 1811124, message = "Kamael Soldier")
	public static NpcStringId KAMAEL_SOLDIER_2;
	
	@ClientString(id = 1811125, message = "Trooper")
	public static NpcStringId TROOPER;
	
	@ClientString(id = 1811126, message = "Warder")
	public static NpcStringId WARDER;
	
	@ClientString(id = 1811127, message = "Berserker")
	public static NpcStringId BERSERKER;
	
	@ClientString(id = 1811128, message = "Soul Breaker")
	public static NpcStringId SOUL_BREAKER;
	
	@ClientString(id = 1811129, message = "Soul Breaker")
	public static NpcStringId SOUL_BREAKER_2;
	
	@ClientString(id = 1811130, message = "Arbalester")
	public static NpcStringId ARBALESTER;
	
	@ClientString(id = 1811131, message = "Doombringer")
	public static NpcStringId DOOMBRINGER;
	
	@ClientString(id = 1811132, message = "Soul Hound")
	public static NpcStringId SOUL_HOUND;
	
	@ClientString(id = 1811133, message = "Soul Hound")
	public static NpcStringId SOUL_HOUND_2;
	
	@ClientString(id = 1811134, message = "Trickster")
	public static NpcStringId TRICKSTER;
	
	@ClientString(id = 1811135, message = "Inspector")
	public static NpcStringId INSPECTOR;
	
	@ClientString(id = 1811136, message = "Judicator")
	public static NpcStringId JUDICATOR;
	
	@ClientString(id = 1811137, message = "Who's there? If you disturb the temper of the great Land Dragon Antharas, I will never forgive you!")
	public static NpcStringId WHO_S_THERE_IF_YOU_DISTURB_THE_TEMPER_OF_THE_GREAT_LAND_DRAGON_ANTHARAS_I_WILL_NEVER_FORGIVE_YOU;
	
	@ClientString(id = 1900000, message = "Kehahaha!!! I captured Santa Claus!! There will be no gifts this year!!!")
	public static NpcStringId KEHAHAHA_I_CAPTURED_SANTA_CLAUS_THERE_WILL_BE_NO_GIFTS_THIS_YEAR;
	
	@ClientString(id = 1900001, message = "Now! Why don’t you take the challenge?")
	public static NpcStringId NOW_WHY_DON_T_YOU_TAKE_THE_CHALLENGE;
	
	@ClientString(id = 1900002, message = "Come on, I'll take all of you on!")
	public static NpcStringId COME_ON_I_LL_TAKE_ALL_OF_YOU_ON_2;
	
	@ClientString(id = 1900003, message = "Well? I win, right?")
	public static NpcStringId WELL_I_WIN_RIGHT;
	
	@ClientString(id = 1900004, message = "Now!! All of you losers, get out of here!")
	public static NpcStringId NOW_ALL_OF_YOU_LOSERS_GET_OUT_OF_HERE;
	
	@ClientString(id = 1900005, message = "What a bunch of losers.")
	public static NpcStringId WHAT_A_BUNCH_OF_LOSERS_2;
	
	@ClientString(id = 1900006, message = "I guess you came to rescue Santa. But you picked the wrong opponent.")
	public static NpcStringId I_GUESS_YOU_CAME_TO_RESCUE_SANTA_BUT_YOU_PICKED_THE_WRONG_OPPONENT;
	
	@ClientString(id = 1900007, message = "Oh, not bad...")
	public static NpcStringId OH_NOT_BAD;
	
	@ClientString(id = 1900008, message = "Agh!! That's not what I meant to do!")
	public static NpcStringId AGH_THAT_S_NOT_WHAT_I_MEANT_TO_DO;
	
	@ClientString(id = 1900009, message = "Curse you!! Huh... What...?")
	public static NpcStringId CURSE_YOU_HUH_WHAT;
	
	@ClientString(id = 1900010, message = "Have you done nothing but rock-paper-scissors?!")
	public static NpcStringId HAVE_YOU_DONE_NOTHING_BUT_ROCK_PAPER_SCISSORS_2;
	
	@ClientString(id = 1900011, message = "Stop it, no more... I did it because I was too lonely...")
	public static NpcStringId STOP_IT_NO_MORE_I_DID_IT_BECAUSE_I_WAS_TOO_LONELY_2;
	
	@ClientString(id = 1900012, message = "I have to release Santa... How infuriating!!!")
	public static NpcStringId I_HAVE_TO_RELEASE_SANTA_HOW_INFURIATING_2;
	
	@ClientString(id = 1900013, message = "I hate happy Merry Christmas!!!")
	public static NpcStringId I_HATE_HAPPY_MERRY_CHRISTMAS_2;
	
	@ClientString(id = 1900014, message = "Oh. I'm bored.")
	public static NpcStringId OH_I_M_BORED_2;
	
	@ClientString(id = 1900015, message = "Shall I go to see if Santa is still there? Hehe~")
	public static NpcStringId SHALL_I_GO_TO_SEE_IF_SANTA_IS_STILL_THERE_HEHE;
	
	@ClientString(id = 1900016, message = "Oh ho ho.... Merry Christmas!!")
	public static NpcStringId OH_HO_HO_MERRY_CHRISTMAS_2;
	
	@ClientString(id = 1900017, message = "Santa can give nice presents only if he's released from the Turkey...")
	public static NpcStringId SANTA_CAN_GIVE_NICE_PRESENTS_ONLY_IF_HE_S_RELEASED_FROM_THE_TURKEY;
	
	@ClientString(id = 1900018, message = "Oh ho ho... Oh ho ho... Thank you. Everyone! I will repay you for sure.")
	public static NpcStringId OH_HO_HO_OH_HO_HO_THANK_YOU_EVERYONE_I_WILL_REPAY_YOU_FOR_SURE;
	
	@ClientString(id = 1900019, message = "Merry Christmas~ Well done.")
	public static NpcStringId MERRY_CHRISTMAS_WELL_DONE;
	
	@ClientString(id = 1900020, message = "Merry Christmas~ Thank you for rescuing me from that wretched Turkey.")
	public static NpcStringId MERRY_CHRISTMAS_THANK_YOU_FOR_RESCUING_ME_FROM_THAT_WRETCHED_TURKEY_2;
	
	@ClientString(id = 1900021, message = "%s. I have prepared a gift for you.")
	public static NpcStringId S_I_HAVE_PREPARED_A_GIFT_FOR_YOU;
	
	@ClientString(id = 1900022, message = "I have a gift for %s.")
	public static NpcStringId I_HAVE_A_GIFT_FOR_S;
	
	@ClientString(id = 1900023, message = "Take a look at the inventory. I hope you like the gift I gave you.")
	public static NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_I_HOPE_YOU_LIKE_THE_GIFT_I_GAVE_YOU_2;
	
	@ClientString(id = 1900024, message = "Take a look at the inventory. Perhaps there will be a big present~")
	public static NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_PERHAPS_THERE_WILL_BE_A_BIG_PRESENT;
	
	@ClientString(id = 1900025, message = "I’m tired of dealing with you. I’m leaving.")
	public static NpcStringId I_M_TIRED_OF_DEALING_WITH_YOU_I_M_LEAVING_2;
	
	@ClientString(id = 1900026, message = "When are you going to stop? I'm slowly getting tired of this.")
	public static NpcStringId WHEN_ARE_YOU_GOING_TO_STOP_I_M_SLOWLY_GETTING_TIRED_OF_THIS;
	
	@ClientString(id = 1900027, message = "Message from Santa Claus: Many blessings to %s, who saved me~")
	public static NpcStringId MESSAGE_FROM_SANTA_CLAUS_MANY_BLESSINGS_TO_S_WHO_SAVED_ME;
	
	@ClientString(id = 1900028, message = "How dare you awaken me. Feel the pain of the flames.")
	public static NpcStringId HOW_DARE_YOU_AWAKEN_ME_FEEL_THE_PAIN_OF_THE_FLAMES;
	
	@ClientString(id = 1900029, message = "Who dares oppose the majesty of fire...?")
	public static NpcStringId WHO_DARES_OPPOSE_THE_MAJESTY_OF_FIRE;
	
	@ClientString(id = 1900030, message = "Oh... Ouch! No, not there! Not my bead!")
	public static NpcStringId OH_OUCH_NO_NOT_THERE_NOT_MY_BEAD;
	
	@ClientString(id = 1900031, message = "Co... Cold! That's cold! Ack! Ack!")
	public static NpcStringId CO_COLD_THAT_S_COLD_ACK_ACK;
	
	@ClientString(id = 1900032, message = "Please, %s... Don't hit me... Please.")
	public static NpcStringId PLEASE_S_DON_T_HIT_ME_PLEASE;
	
	@ClientString(id = 1900033, message = "Kuaaannggg! Shake in fear!")
	public static NpcStringId KUAAANNGGG_SHAKE_IN_FEAR;
	
	@ClientString(id = 1900034, message = "If you attack me right now, you're really going to get it!!!")
	public static NpcStringId IF_YOU_ATTACK_ME_RIGHT_NOW_YOU_RE_REALLY_GOING_TO_GET_IT;
	
	@ClientString(id = 1900035, message = "Just you wait! I'm going to show you my killer technique.")
	public static NpcStringId JUST_YOU_WAIT_I_M_GOING_TO_SHOW_YOU_MY_KILLER_TECHNIQUE;
	
	@ClientString(id = 1900036, message = "You don't dare attack me!")
	public static NpcStringId YOU_DON_T_DARE_ATTACK_ME;
	
	@ClientString(id = 1900037, message = "It's different from the spirit of fire. It's not the spirit of fire! Feel my wrath!")
	public static NpcStringId IT_S_DIFFERENT_FROM_THE_SPIRIT_OF_FIRE_IT_S_NOT_THE_SPIRIT_OF_FIRE_FEEL_MY_WRATH;
	
	@ClientString(id = 1900038, message = "Cold... This place... Is this where I die...?")
	public static NpcStringId COLD_THIS_PLACE_IS_THIS_WHERE_I_DIE;
	
	@ClientString(id = 1900039, message = "My body is cooling. Oh, Gran Kain... Forgive me...")
	public static NpcStringId MY_BODY_IS_COOLING_OH_GRAN_KAIN_FORGIVE_ME;
	
	@ClientString(id = 1900040, message = "Idiot! I only incur damage from bare-handed attacks!")
	public static NpcStringId IDIOT_I_ONLY_INCUR_DAMAGE_FROM_BARE_HANDED_ATTACKS;
	
	@ClientString(id = 1900041, message = "You summoned me, so you must be confident, huh? Here I come! Jack game!")
	public static NpcStringId YOU_SUMMONED_ME_SO_YOU_MUST_BE_CONFIDENT_HUH_HERE_I_COME_JACK_GAME_2;
	
	@ClientString(id = 1900042, message = "Hello. Let's have a good Jack game.")
	public static NpcStringId HELLO_LET_S_HAVE_A_GOOD_JACK_GAME_2;
	
	@ClientString(id = 1900043, message = "I'm starting! Now, show me the card you want!")
	public static NpcStringId I_M_STARTING_NOW_SHOW_ME_THE_CARD_YOU_WANT_2;
	
	@ClientString(id = 1900044, message = "We'll start now! Show me the card you want!")
	public static NpcStringId WE_LL_START_NOW_SHOW_ME_THE_CARD_YOU_WANT_2;
	
	@ClientString(id = 1900045, message = "I'm showing the Rotten Pumpkin Card!")
	public static NpcStringId I_M_SHOWING_THE_ROTTEN_PUMPKIN_CARD_2;
	
	@ClientString(id = 1900046, message = "I'll be showing the Rotten Pumpkin Card!")
	public static NpcStringId I_LL_BE_SHOWING_THE_ROTTEN_PUMPKIN_CARD_2;
	
	@ClientString(id = 1900047, message = "I'm showing the Jack Pumpkin Card!")
	public static NpcStringId I_M_SHOWING_THE_JACK_PUMPKIN_CARD_2;
	
	@ClientString(id = 1900048, message = "I'll be showing the Jack Pumpkin Card!")
	public static NpcStringId I_LL_BE_SHOWING_THE_JACK_PUMPKIN_CARD_2;
	
	@ClientString(id = 1900049, message = "That's my precious Fantastic Chocolate Banana Ultra Favor Candy!!! I'm definitely winning the next round!!")
	public static NpcStringId THAT_S_MY_PRECIOUS_FANTASTIC_CHOCOLATE_BANANA_ULTRA_FAVOR_CANDY_I_M_DEFINITELY_WINNING_THE_NEXT_ROUND_2;
	
	@ClientString(id = 1900050, message = "It's my precious candy, but... I'll happily give it to you~!")
	public static NpcStringId IT_S_MY_PRECIOUS_CANDY_BUT_I_LL_HAPPILY_GIVE_IT_TO_YOU_2;
	
	@ClientString(id = 1900051, message = "I'm out of candy. I'll give you my toy chest instead.")
	public static NpcStringId I_M_OUT_OF_CANDY_I_LL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;
	
	@ClientString(id = 1900052, message = "Since I'm out of candy, I will give you my toy chest instead.")
	public static NpcStringId SINCE_I_M_OUT_OF_CANDY_I_WILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;
	
	@ClientString(id = 1900053, message = "You're not peeking at my card, are you? This time, I'll wager a special scroll.")
	public static NpcStringId YOU_RE_NOT_PEEKING_AT_MY_CARD_ARE_YOU_THIS_TIME_I_LL_WAGER_A_SPECIAL_SCROLL_2;
	
	@ClientString(id = 1900054, message = "We're getting serious now. If you win again, I'll give you a special scroll.")
	public static NpcStringId WE_RE_GETTING_SERIOUS_NOW_IF_YOU_WIN_AGAIN_I_LL_GIVE_YOU_A_SPECIAL_SCROLL_2;
	
	@ClientString(id = 1900055, message = "You could probably enter the underworld pro league!")
	public static NpcStringId YOU_COULD_PROBABLY_ENTER_THE_UNDERWORLD_PRO_LEAGUE_2;
	
	@ClientString(id = 1900056, message = "Even pros can't do this much. You're amazing.")
	public static NpcStringId EVEN_PROS_CAN_T_DO_THIS_MUCH_YOU_RE_AMAZING_2;
	
	@ClientString(id = 1900057, message = "Who's the monster here?! This time, I'll bet my precious Transformation Stick.")
	public static NpcStringId WHO_S_THE_MONSTER_HERE_THIS_TIME_I_LL_BET_MY_PRECIOUS_TRANSFORMATION_STICK_2;
	
	@ClientString(id = 1900058, message = "I lost again. I won't lose this time. I'm betting my Transformation Stick.")
	public static NpcStringId I_LOST_AGAIN_I_WON_T_LOSE_THIS_TIME_I_M_BETTING_MY_TRANSFORMATION_STICK_2;
	
	@ClientString(id = 1900059, message = "Lost again! Hmph. Next time, I'll bet an incredible gift! Wait for it if you want!")
	public static NpcStringId LOST_AGAIN_HMPH_NEXT_TIME_I_LL_BET_AN_INCREDIBLE_GIFT_WAIT_FOR_IT_IF_YOU_WANT_2;
	
	@ClientString(id = 1900060, message = "You're too good. Next time, I'll give you an incredible gift! Please wait for it.")
	public static NpcStringId YOU_RE_TOO_GOOD_NEXT_TIME_I_LL_GIVE_YOU_AN_INCREDIBLE_GIFT_PLEASE_WAIT_FOR_IT;
	
	@ClientString(id = 1900061, message = "My pride can't handle you winning anymore!")
	public static NpcStringId MY_PRIDE_CAN_T_HANDLE_YOU_WINNING_ANYMORE_2;
	
	@ClientString(id = 1900062, message = "I would be embarrassed to lose again here...")
	public static NpcStringId I_WOULD_BE_EMBARRASSED_TO_LOSE_AGAIN_HERE;
	
	@ClientString(id = 1900063, message = "What's your name? I'm gonna remember you!")
	public static NpcStringId WHAT_S_YOUR_NAME_I_M_GONNA_REMEMBER_YOU_2;
	
	@ClientString(id = 1900064, message = "People from the above ground world are really good at games.")
	public static NpcStringId PEOPLE_FROM_THE_ABOVE_GROUND_WORLD_ARE_REALLY_GOOD_AT_GAMES_2;
	
	@ClientString(id = 1900065, message = "You've played a lot in the underworld, haven't you?!")
	public static NpcStringId YOU_VE_PLAYED_A_LOT_IN_THE_UNDERWORLD_HAVEN_T_YOU_2;
	
	@ClientString(id = 1900066, message = "I've never met someone so good before.")
	public static NpcStringId I_VE_NEVER_MET_SOMEONE_SO_GOOD_BEFORE_2;
	
	@ClientString(id = 1900067, message = "13 wins in a row. You're pretty lucky today, huh?")
	public static NpcStringId THIRTEEN_WINS_IN_A_ROW_YOU_RE_PRETTY_LUCKY_TODAY_HUH_2;
	
	@ClientString(id = 1900068, message = "I never thought I would see 13 wins in a row.")
	public static NpcStringId I_NEVER_THOUGHT_I_WOULD_SEE_13_WINS_IN_A_ROW_2;
	
	@ClientString(id = 1900069, message = "This is the highest record in my life! Next time, I'll give you my treasure -- the Golden Jack O'Lantern!")
	public static NpcStringId THIS_IS_THE_HIGHEST_RECORD_IN_MY_LIFE_NEXT_TIME_I_LL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_O_LANTERN_2;
	
	@ClientString(id = 1900070, message = "Even pros can't do 14 wins in a row...! Next time, I'll give you my treasure, the Golden Jack O'Lantern.")
	public static NpcStringId EVEN_PROS_CAN_T_DO_14_WINS_IN_A_ROW_NEXT_TIME_I_LL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_O_LANTERN;
	
	@ClientString(id = 1900071, message = "I can't do this anymore! You win! In all my 583 years, you're the best that I've seen!")
	public static NpcStringId I_CAN_T_DO_THIS_ANYMORE_YOU_WIN_IN_ALL_MY_583_YEARS_YOU_RE_THE_BEST_THAT_I_VE_SEEN;
	
	@ClientString(id = 1900072, message = "Playing any more is meaningless. You were my greatest opponent.")
	public static NpcStringId PLAYING_ANY_MORE_IS_MEANINGLESS_YOU_WERE_MY_GREATEST_OPPONENT_2;
	
	@ClientString(id = 1900073, message = "I won this round...! It was fun.")
	public static NpcStringId I_WON_THIS_ROUND_IT_WAS_FUN_2;
	
	@ClientString(id = 1900074, message = "I won this round. It was enjoyable.")
	public static NpcStringId I_WON_THIS_ROUND_IT_WAS_ENJOYABLE_2;
	
	@ClientString(id = 1900075, message = "Above world people are so fun...! Then, see you later!")
	public static NpcStringId ABOVE_WORLD_PEOPLE_ARE_SO_FUN_THEN_SEE_YOU_LATER_2;
	
	@ClientString(id = 1900076, message = "Call me again next time. I want to play again with you.")
	public static NpcStringId CALL_ME_AGAIN_NEXT_TIME_I_WANT_TO_PLAY_AGAIN_WITH_YOU_2;
	
	@ClientString(id = 1900077, message = "You wanna play some more? I'm out of presents, but I'll give you candy!")
	public static NpcStringId YOU_WANNA_PLAY_SOME_MORE_I_M_OUT_OF_PRESENTS_BUT_I_LL_GIVE_YOU_CANDY_2;
	
	@ClientString(id = 1900078, message = "Will you play some more? I don't have any more presents, but I will give you candy if you win.")
	public static NpcStringId WILL_YOU_PLAY_SOME_MORE_I_DON_T_HAVE_ANY_MORE_PRESENTS_BUT_I_WILL_GIVE_YOU_CANDY_IF_YOU_WIN_2;
	
	@ClientString(id = 1900079, message = "You're the best. Out of all the Jack's game players I've ever met... I give up!")
	public static NpcStringId YOU_RE_THE_BEST_OUT_OF_ALL_THE_JACK_S_GAME_PLAYERS_I_VE_EVER_MET_I_GIVE_UP_2;
	
	@ClientString(id = 1900080, message = "Wowww. Awesome. Really. I have never met someone as good as you before. Now... I can't play anymore.")
	public static NpcStringId WOWWW_AWESOME_REALLY_I_HAVE_NEVER_MET_SOMEONE_AS_GOOD_AS_YOU_BEFORE_NOW_I_CAN_T_PLAY_ANYMORE_2;
	
	@ClientString(id = 1900081, message = "%s has won %s Jack's games in a row.")
	public static NpcStringId S_HAS_WON_S_JACK_S_GAMES_IN_A_ROW;
	
	@ClientString(id = 1900082, message = "Congratulations! %s has won %s Jack's games in a row.")
	public static NpcStringId CONGRATULATIONS_S_HAS_WON_S_JACK_S_GAMES_IN_A_ROW;
	
	@ClientString(id = 1900083, message = "Congratulations on getting 1st place in Jack's game!")
	public static NpcStringId CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACK_S_GAME_2;
	
	@ClientString(id = 1900084, message = "Hello~! I'm Belldandy. Congratulations on getting 1st place in Jack's game. If you go and find my sibling Skooldie in the village, you'll get an amazing gift! Let's play Jack's game again!")
	public static NpcStringId HELLO_I_M_BELLDANDY_CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACK_S_GAME_IF_YOU_GO_AND_FIND_MY_SIBLING_SKOOLDIE_IN_THE_VILLAGE_YOU_LL_GET_AN_AMAZING_GIFT_LET_S_PLAY_JACK_S_GAME_AGAIN;
	
	@ClientString(id = 1900085, message = "Hmm. You're playing Jack's game for the first time, huh? You couldn't even take out your card at the right time~! My goodness...")
	public static NpcStringId HMM_YOU_RE_PLAYING_JACK_S_GAME_FOR_THE_FIRST_TIME_HUH_YOU_COULDN_T_EVEN_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME_MY_GOODNESS_2;
	
	@ClientString(id = 1900086, message = "Oh. You're not very familiar with Jack's game, right? You didn't take out your card at the right time...")
	public static NpcStringId OH_YOU_RE_NOT_VERY_FAMILIAR_WITH_JACK_S_GAME_RIGHT_YOU_DIDN_T_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME_2;
	
	@ClientString(id = 1900087, message = "You have to use the card skill on the mask before the gauge above my head disappears.")
	public static NpcStringId YOU_HAVE_TO_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS_2;
	
	@ClientString(id = 1900088, message = "You must use the card skill on the mask before the gauge above my head disappears.")
	public static NpcStringId YOU_MUST_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS_2;
	
	@ClientString(id = 1900089, message = "If you show the same card as me, you win. If they're different, I win. Understand? Now, let's go!")
	public static NpcStringId IF_YOU_SHOW_THE_SAME_CARD_AS_ME_YOU_WIN_IF_THEY_RE_DIFFERENT_I_WIN_UNDERSTAND_NOW_LET_S_GO_2;
	
	@ClientString(id = 1900090, message = "You will win if you show the same card as me. It's my victory if the cards are different. Well, let's start again~")
	public static NpcStringId YOU_WILL_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_IT_S_MY_VICTORY_IF_THE_CARDS_ARE_DIFFERENT_WELL_LET_S_START_AGAIN_2;
	
	@ClientString(id = 1900091, message = "Ack! You didn't show a card? You have to use the card skill before the gauge disappears. Hmph. Then, I'm going.")
	public static NpcStringId ACK_YOU_DIDN_T_SHOW_A_CARD_YOU_HAVE_TO_USE_THE_CARD_SKILL_BEFORE_THE_GAUGE_DISAPPEARS_HMPH_THEN_I_M_GOING_2;
	
	@ClientString(id = 1900092, message = "Ahh. You didn't show a card. You must use the card skill at the right time. It's unfortunate. Then, I will go now~")
	public static NpcStringId AHH_YOU_DIDN_T_SHOW_A_CARD_YOU_MUST_USE_THE_CARD_SKILL_AT_THE_RIGHT_TIME_IT_S_UNFORTUNATE_THEN_I_WILL_GO_NOW_2;
	
	@ClientString(id = 1900093, message = "Let's learn about the Jack's game together~! You can play with me 3 times.")
	public static NpcStringId LET_S_LEARN_ABOUT_THE_JACK_S_GAME_TOGETHER_YOU_CAN_PLAY_WITH_ME_3_TIMES_2;
	
	@ClientString(id = 1900094, message = "Let's start! Show the card you want! The card skill is attached to the mask.")
	public static NpcStringId LET_S_START_SHOW_THE_CARD_YOU_WANT_THE_CARD_SKILL_IS_ATTACHED_TO_THE_MASK_2;
	
	@ClientString(id = 1900095, message = "You showed the same card as me, so you win.")
	public static NpcStringId YOU_SHOWED_THE_SAME_CARD_AS_ME_SO_YOU_WIN_2;
	
	@ClientString(id = 1900096, message = "You showed a different card from me, so you lose.")
	public static NpcStringId YOU_SHOWED_A_DIFFERENT_CARD_FROM_ME_SO_YOU_LOSE_2;
	
	@ClientString(id = 1900097, message = "That was practice, so there's no candy even if you win~")
	public static NpcStringId THAT_WAS_PRACTICE_SO_THERE_S_NO_CANDY_EVEN_IF_YOU_WIN_2;
	
	@ClientString(id = 1900098, message = "It's unfortunate. Let's practice one more time.")
	public static NpcStringId IT_S_UNFORTUNATE_LET_S_PRACTICE_ONE_MORE_TIME_2;
	
	@ClientString(id = 1900099, message = "You gotta show the card at the right time. Use the card skill you want before the gauge above my head disappears!")
	public static NpcStringId YOU_GOTTA_SHOW_THE_CARD_AT_THE_RIGHT_TIME_USE_THE_CARD_SKILL_YOU_WANT_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS_2;
	
	@ClientString(id = 1900100, message = "The card skills are attached to the Jack O'Lantern mask, right? That's what you use.")
	public static NpcStringId THE_CARD_SKILLS_ARE_ATTACHED_TO_THE_JACK_O_LANTERN_MASK_RIGHT_THAT_S_WHAT_YOU_USE_2;
	
	@ClientString(id = 1900101, message = "You win if you show the same card as me, and I win if the cards are different. OK, let's go~")
	public static NpcStringId YOU_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_AND_I_WIN_IF_THE_CARDS_ARE_DIFFERENT_OK_LET_S_GO_2;
	
	@ClientString(id = 1900102, message = "You didn't show a card again? We'll try again later. I'm gonna go now~")
	public static NpcStringId YOU_DIDN_T_SHOW_A_CARD_AGAIN_WE_LL_TRY_AGAIN_LATER_I_M_GONNA_GO_NOW_2;
	
	@ClientString(id = 1900103, message = "Now, do you understand a little about Jack's game? The real game's with Uldie and Belldandy. Well, see you later!")
	public static NpcStringId NOW_DO_YOU_UNDERSTAND_A_LITTLE_ABOUT_JACK_S_GAME_THE_REAL_GAME_S_WITH_ULDIE_AND_BELLDANDY_WELL_SEE_YOU_LATER_2;
	
	@ClientString(id = 1900104, message = "Yawn~ Ahh... Hello~! Nice weather we're having.")
	public static NpcStringId YAWN_AHH_HELLO_NICE_WEATHER_WE_RE_HAVING;
	
	@ClientString(id = 1900105, message = "Ah, I'm hungry. Do you have any baby food? That's what I need to eat to get bigger.")
	public static NpcStringId AH_I_M_HUNGRY_DO_YOU_HAVE_ANY_BABY_FOOD_THAT_S_WHAT_I_NEED_TO_EAT_TO_GET_BIGGER;
	
	@ClientString(id = 1900106, message = "I gotta grow up fast. I want to pull Santa's sled, too.")
	public static NpcStringId I_GOTTA_GROW_UP_FAST_I_WANT_TO_PULL_SANTA_S_SLED_TOO;
	
	@ClientString(id = 1900107, message = "Yummy! I think I can grow up now!")
	public static NpcStringId YUMMY_I_THINK_I_CAN_GROW_UP_NOW;
	
	@ClientString(id = 1900108, message = "Thanks to you, I grew up into a Boy Rudolph!")
	public static NpcStringId THANKS_TO_YOU_I_GREW_UP_INTO_A_BOY_RUDOLPH;
	
	@ClientString(id = 1900109, message = "It's great weather for running around.")
	public static NpcStringId IT_S_GREAT_WEATHER_FOR_RUNNING_AROUND;
	
	@ClientString(id = 1900110, message = "What'll I be when I grow up? I wonder.")
	public static NpcStringId WHAT_LL_I_BE_WHEN_I_GROW_UP_I_WONDER;
	
	@ClientString(id = 1900111, message = "If you take good care of me, I'll never forget it!")
	public static NpcStringId IF_YOU_TAKE_GOOD_CARE_OF_ME_I_LL_NEVER_FORGET_IT;
	
	@ClientString(id = 1900112, message = "Please pet me lovingly. You can use the 'Hand of Warmth' skill under the action tab.")
	public static NpcStringId PLEASE_PET_ME_LOVINGLY_YOU_CAN_USE_THE_HAND_OF_WARMTH_SKILL_UNDER_THE_ACTION_TAB;
	
	@ClientString(id = 1900113, message = "I feel great! Thank you!")
	public static NpcStringId I_FEEL_GREAT_THANK_YOU;
	
	@ClientString(id = 1900114, message = "Woo. What a good feeling~ I gotta grow more and more!")
	public static NpcStringId WOO_WHAT_A_GOOD_FEELING_I_GOTTA_GROW_MORE_AND_MORE;
	
	@ClientString(id = 1900115, message = "Oh, yummy! If I keep eating this, I think I can grow up all the way.")
	public static NpcStringId OH_YUMMY_IF_I_KEEP_EATING_THIS_I_THINK_I_CAN_GROW_UP_ALL_THE_WAY;
	
	@ClientString(id = 1900116, message = "Yum yum. Delicious. Give me more of this!")
	public static NpcStringId YUM_YUM_DELICIOUS_GIVE_ME_MORE_OF_THIS;
	
	@ClientString(id = 1900117, message = "Wow. This taste. There's a whole new world in my mouth!")
	public static NpcStringId WOW_THIS_TASTE_THERE_S_A_WHOLE_NEW_WORLD_IN_MY_MOUTH;
	
	@ClientString(id = 1900118, message = "Yeah. It's so delicious. This is that star food!")
	public static NpcStringId YEAH_IT_S_SO_DELICIOUS_THIS_IS_THAT_STAR_FOOD;
	
	@ClientString(id = 1900119, message = "Pay some attention to me! Hmph.")
	public static NpcStringId PAY_SOME_ATTENTION_TO_ME_HMPH;
	
	@ClientString(id = 1900120, message = "Thank you. I was able to grow up into an adult. Here is my gift.")
	public static NpcStringId THANK_YOU_I_WAS_ABLE_TO_GROW_UP_INTO_AN_ADULT_HERE_IS_MY_GIFT;
	
	@ClientString(id = 1900121, message = "Thank you. %s. Now, I can pull the sled.")
	public static NpcStringId THANK_YOU_S_NOW_I_CAN_PULL_THE_SLED;
	
	@ClientString(id = 1900122, message = "%s. Thank you for taking care of me all this time. I enjoyed it very much.")
	public static NpcStringId S_THANK_YOU_FOR_TAKING_CARE_OF_ME_ALL_THIS_TIME_I_ENJOYED_IT_VERY_MUCH;
	
	@ClientString(id = 1900123, message = "%s. It won't be long now until it becomes time to pull the sled. It's too bad.")
	public static NpcStringId S_IT_WON_T_BE_LONG_NOW_UNTIL_IT_BECOMES_TIME_TO_PULL_THE_SLED_IT_S_TOO_BAD;
	
	@ClientString(id = 1900124, message = "I must return to Santa now. Thank you for everything!")
	public static NpcStringId I_MUST_RETURN_TO_SANTA_NOW_THANK_YOU_FOR_EVERYTHING;
	
	@ClientString(id = 1900125, message = "Hello. I'm a girl Rudolph. I was able to find my true self thanks to you.")
	public static NpcStringId HELLO_I_M_A_GIRL_RUDOLPH_I_WAS_ABLE_TO_FIND_MY_TRUE_SELF_THANKS_TO_YOU;
	
	@ClientString(id = 1900126, message = "This is my gift of thanks. Thank you for taking care of me~")
	public static NpcStringId THIS_IS_MY_GIFT_OF_THANKS_THANK_YOU_FOR_TAKING_CARE_OF_ME;
	
	@ClientString(id = 1900127, message = "%s. I was always grateful.")
	public static NpcStringId S_I_WAS_ALWAYS_GRATEFUL;
	
	@ClientString(id = 1900128, message = "I'm a little sad. It's time to leave now.")
	public static NpcStringId I_M_A_LITTLE_SAD_IT_S_TIME_TO_LEAVE_NOW;
	
	@ClientString(id = 1900129, message = "%s. The time has come for me to return to my home.")
	public static NpcStringId S_THE_TIME_HAS_COME_FOR_ME_TO_RETURN_TO_MY_HOME;
	
	@ClientString(id = 1900130, message = "%s. Thank you.")
	public static NpcStringId S_THANK_YOU;
	
	@ClientString(id = 1900131, message = "Hahaha!!! I captured Santa Claus!! Huh? Where is this? Who are you?")
	public static NpcStringId HAHAHA_I_CAPTURED_SANTA_CLAUS_HUH_WHERE_IS_THIS_WHO_ARE_YOU;
	
	@ClientString(id = 1900132, message = "...I lost at Rock Paper Scissors and was taken captive... I might as well take out my anger on you! Huh~ What?")
	public static NpcStringId I_LOST_AT_ROCK_PAPER_SCISSORS_AND_WAS_TAKEN_CAPTIVE_I_MIGHT_AS_WELL_TAKE_OUT_MY_ANGER_ON_YOU_HUH_WHAT;
	
	@ClientString(id = 1900133, message = "Nothing's working... I'm leaving! I'll definitely capture Santa again! Just you wait!")
	public static NpcStringId NOTHING_S_WORKING_I_M_LEAVING_I_LL_DEFINITELY_CAPTURE_SANTA_AGAIN_JUST_YOU_WAIT;
	
	@ClientString(id = 1900134, message = "I must raise Rudolph quickly. This year's Christmas gifts have to be delivered...")
	public static NpcStringId I_MUST_RAISE_RUDOLPH_QUICKLY_THIS_YEAR_S_CHRISTMAS_GIFTS_HAVE_TO_BE_DELIVERED;
	
	@ClientString(id = 1900135, message = "Merry Christmas~ Thanks to your efforts in raising Rudolph, the gift delivery was a success.")
	public static NpcStringId MERRY_CHRISTMAS_THANKS_TO_YOUR_EFFORTS_IN_RAISING_RUDOLPH_THE_GIFT_DELIVERY_WAS_A_SUCCESS;
	
	@ClientString(id = 1900136, message = "In 10 minutes, it will be 1 hour since you started raising me.")
	public static NpcStringId IN_10_MINUTES_IT_WILL_BE_1_HOUR_SINCE_YOU_STARTED_RAISING_ME;
	
	@ClientString(id = 1900137, message = "After 5 minutes, if my Full Feeling and Affection Level reach 99%, I can grow bigger.")
	public static NpcStringId AFTER_5_MINUTES_IF_MY_FULL_FEELING_AND_AFFECTION_LEVEL_REACH_99_I_CAN_GROW_BIGGER;
	
	@ClientString(id = 1900138, message = "The resupply time of %s hour(s) %s minute(s) %s second(s) remain for the Gift of Energy.")
	public static NpcStringId THE_RESUPPLY_TIME_OF_S_HOUR_S_S_MINUTE_S_S_SECOND_S_REMAIN_FOR_THE_GIFT_OF_ENERGY;
	
	@ClientString(id = 1900139, message = "Lucky! I'm Lucky, the spirit that loves adena")
	public static NpcStringId LUCKY_I_M_LUCKY_THE_SPIRIT_THAT_LOVES_ADENA;
	
	@ClientString(id = 1900140, message = "Lucky! I want to eat adena. Give it to me!")
	public static NpcStringId LUCKY_I_WANT_TO_EAT_ADENA_GIVE_IT_TO_ME;
	
	@ClientString(id = 1900141, message = "Lucky! If I eat too much adena, my wings disappear…")
	public static NpcStringId LUCKY_IF_I_EAT_TOO_MUCH_ADENA_MY_WINGS_DISAPPEAR;
	
	@ClientString(id = 1900142, message = "Yummy. Thanks! Lucky!")
	public static NpcStringId YUMMY_THANKS_LUCKY;
	
	@ClientString(id = 1900143, message = "Grrrr... Yuck…")
	public static NpcStringId GRRRR_YUCK;
	
	@ClientString(id = 1900144, message = "Lucky! The adena is so yummy! I'm getting bigger!")
	public static NpcStringId LUCKY_THE_ADENA_IS_SO_YUMMY_I_M_GETTING_BIGGER;
	
	@ClientString(id = 1900145, message = "Lucky! No more adena? Oh... I'm so heavy!")
	public static NpcStringId LUCKY_NO_MORE_ADENA_OH_I_M_SO_HEAVY;
	
	@ClientString(id = 1900146, message = "Lucky! I'm full~ Thanks for the yummy adena! Oh... I'm so heavy!")
	public static NpcStringId LUCKY_I_M_FULL_THANKS_FOR_THE_YUMMY_ADENA_OH_I_M_SO_HEAVY;
	
	@ClientString(id = 1900147, message = "Lucky! It wasn't enough adena. It's gotta be at least %s!")
	public static NpcStringId LUCKY_IT_WASN_T_ENOUGH_ADENA_IT_S_GOTTA_BE_AT_LEAST_S;
	
	@ClientString(id = 1900148, message = "Oh! My wings disappeared! Are you gonna hit me? If you hit me, I'll throw up everything that I ate!")
	public static NpcStringId OH_MY_WINGS_DISAPPEARED_ARE_YOU_GONNA_HIT_ME_IF_YOU_HIT_ME_I_LL_THROW_UP_EVERYTHING_THAT_I_ATE;
	
	@ClientString(id = 1900149, message = "Oh! My wings... Ack! Are you gonna hit me?! Scary, scary! If you hit me, something bad will happen!")
	public static NpcStringId OH_MY_WINGS_ACK_ARE_YOU_GONNA_HIT_ME_SCARY_SCARY_IF_YOU_HIT_ME_SOMETHING_BAD_WILL_HAPPEN;
	
	@ClientString(id = 1900150, message = "The evil Land Dragon Antharas has been defeated! ")
	public static NpcStringId THE_EVIL_LAND_DRAGON_ANTHARAS_HAS_BEEN_DEFEATED;
	
	@ClientString(id = 1900151, message = "The evil Fire Dragon Valakas has been defeated! ")
	public static NpcStringId THE_EVIL_FIRE_DRAGON_VALAKAS_HAS_BEEN_DEFEATED;
	
	@ClientString(id = 1900152, message = "To serve him now means you will be able to escape a worse situation.")
	public static NpcStringId TO_SERVE_HIM_NOW_MEANS_YOU_WILL_BE_ABLE_TO_ESCAPE_A_WORSE_SITUATION;
	
	@ClientString(id = 1900153, message = "Oh goddess of destruction, forgive us...")
	public static NpcStringId OH_GODDESS_OF_DESTRUCTION_FORGIVE_US;
	
	@ClientString(id = 1900154, message = "When the sky turns blood red and the earth begins to crumble… from the darkness… she will return.")
	public static NpcStringId WHEN_THE_SKY_TURNS_BLOOD_RED_AND_THE_EARTH_BEGINS_TO_CRUMBLE_FROM_THE_DARKNESS_SHE_WILL_RETURN;
	
	@ClientString(id = 1900155, message = "Earth energy is gathering near Antharas's legs.")
	public static NpcStringId EARTH_ENERGY_IS_GATHERING_NEAR_ANTHARAS_S_LEGS;
	
	@ClientString(id = 1900156, message = "Antharas starts to absorb the earth energy.")
	public static NpcStringId ANTHARAS_STARTS_TO_ABSORB_THE_EARTH_ENERGY;
	
	@ClientString(id = 1900157, message = "Antharas raises its thick tail.")
	public static NpcStringId ANTHARAS_RAISES_ITS_THICK_TAIL;
	
	@ClientString(id = 1900158, message = "You are overcome by the strength of Antharas.")
	public static NpcStringId YOU_ARE_OVERCOME_BY_THE_STRENGTH_OF_ANTHARAS;
	
	@ClientString(id = 1900159, message = "Antharas's eyes are filled with rage.")
	public static NpcStringId ANTHARAS_S_EYES_ARE_FILLED_WITH_RAGE;
	
	@ClientString(id = 1900160, message = "$s1, I can feel their presence from you.")
	public static NpcStringId S1_I_CAN_FEEL_THEIR_PRESENCE_FROM_YOU;
	
	@ClientString(id = 1900161, message = "$s1, brethren, come to my side and follow me.")
	public static NpcStringId S1_BRETHREN_COME_TO_MY_SIDE_AND_FOLLOW_ME;
	
	@ClientString(id = 1900162, message = "Antharas roars.")
	public static NpcStringId ANTHARAS_ROARS;
	
	@ClientString(id = 1900163, message = "Flame energy is being directed towards Valakas.")
	public static NpcStringId FLAME_ENERGY_IS_BEING_DIRECTED_TOWARDS_VALAKAS;
	
	@ClientString(id = 1900164, message = "You are overcome by the strength of Valakas.")
	public static NpcStringId YOU_ARE_OVERCOME_BY_THE_STRENGTH_OF_VALAKAS;
	
	@ClientString(id = 1900165, message = "Valakas's tail flails dangerously.")
	public static NpcStringId VALAKAS_S_TAIL_FLAILS_DANGEROUSLY;
	
	@ClientString(id = 1900166, message = "Valakas raises its tail.")
	public static NpcStringId VALAKAS_RAISES_ITS_TAIL;
	
	@ClientString(id = 1900167, message = "Valakas starts to absorb the flame energy.")
	public static NpcStringId VALAKAS_STARTS_TO_ABSORB_THE_FLAME_ENERGY;
	
	@ClientString(id = 1900168, message = "Valakas looks to its left.")
	public static NpcStringId VALAKAS_LOOKS_TO_ITS_LEFT;
	
	@ClientString(id = 1900169, message = "Valakas looks to its right.")
	public static NpcStringId VALAKAS_LOOKS_TO_ITS_RIGHT;
	
	@ClientString(id = 1900170, message = "By my authority, I command you, creature, turn to dust.")
	public static NpcStringId BY_MY_AUTHORITY_I_COMMAND_YOU_CREATURE_TURN_TO_DUST;
	
	@ClientString(id = 1900171, message = "By my wrath, I command you, creature, lose your mind.")
	public static NpcStringId BY_MY_WRATH_I_COMMAND_YOU_CREATURE_LOSE_YOUR_MIND;
	
	@ClientString(id = 1900172, message = "Show respect to the heroes who defeated the evil dragon and protected this Aden world!")
	public static NpcStringId SHOW_RESPECT_TO_THE_HEROES_WHO_DEFEATED_THE_EVIL_DRAGON_AND_PROTECTED_THIS_ADEN_WORLD;
	
	@ClientString(id = 1900173, message = "Shout to celebrate the victory of the heroes!")
	public static NpcStringId SHOUT_TO_CELEBRATE_THE_VICTORY_OF_THE_HEROES;
	
	@ClientString(id = 1900174, message = "Praise the achievement of the heroes and receive Nevit's blessing!")
	public static NpcStringId PRAISE_THE_ACHIEVEMENT_OF_THE_HEROES_AND_RECEIVE_NEVIT_S_BLESSING;
	
	@ClientString(id = 1900175, message = "Ugh… I think this is it for me…")
	public static NpcStringId UGH_I_THINK_THIS_IS_IT_FOR_ME;
	
	@ClientString(id = 1900176, message = "Valakas calls forth the servitor's master.")
	public static NpcStringId VALAKAS_CALLS_FORTH_THE_SERVITOR_S_MASTER;
	
	@ClientString(id = 1900177, message = "Plague, New Nightmare (Lv 80 to 85)")
	public static NpcStringId PLAGUE_NEW_NIGHTMARE_LV_80_TO_85;
	
	@ClientString(id = 1911111, message = "You will soon become the sacrifice for us, those full of deceit and sin whom you despise!")
	public static NpcStringId YOU_WILL_SOON_BECOME_THE_SACRIFICE_FOR_US_THOSE_FULL_OF_DECEIT_AND_SIN_WHOM_YOU_DESPISE;
	
	@ClientString(id = 1911112, message = "My brethren who are stronger than me will punish you. You will soon be covered in your own blood and crying in anguish!")
	public static NpcStringId MY_BRETHREN_WHO_ARE_STRONGER_THAN_ME_WILL_PUNISH_YOU_YOU_WILL_SOON_BE_COVERED_IN_YOUR_OWN_BLOOD_AND_CRYING_IN_ANGUISH;
	
	@ClientString(id = 1911113, message = "How could I lose against these worthless creatures...?")
	public static NpcStringId HOW_COULD_I_LOSE_AGAINST_THESE_WORTHLESS_CREATURES;
	
	@ClientString(id = 1911114, message = "Foolish creatures... the flames of hell are drawing closer.")
	public static NpcStringId FOOLISH_CREATURES_THE_FLAMES_OF_HELL_ARE_DRAWING_CLOSER;
	
	@ClientString(id = 1911115, message = "No matter how you struggle this place will soon be covered with your blood.")
	public static NpcStringId NO_MATTER_HOW_YOU_STRUGGLE_THIS_PLACE_WILL_SOON_BE_COVERED_WITH_YOUR_BLOOD;
	
	@ClientString(id = 1911116, message = "Those who set foot in this place shall not leave alive.")
	public static NpcStringId THOSE_WHO_SET_FOOT_IN_THIS_PLACE_SHALL_NOT_LEAVE_ALIVE;
	
	@ClientString(id = 1911117, message = "Worthless creatures, I will grant you eternal sleep in fire and brimstone.")
	public static NpcStringId WORTHLESS_CREATURES_I_WILL_GRANT_YOU_ETERNAL_SLEEP_IN_FIRE_AND_BRIMSTONE;
	
	@ClientString(id = 1911118, message = "If you wish to see hell, I will grant you your wish.")
	public static NpcStringId IF_YOU_WISH_TO_SEE_HELL_I_WILL_GRANT_YOU_YOUR_WISH;
	
	@ClientString(id = 1911119, message = "Elapsed Time : ")
	public static NpcStringId ELAPSED_TIME;
	
	@ClientString(id = 1911120, message = "Time Remaining : ")
	public static NpcStringId TIME_REMAINING;
	
	@ClientString(id = 10004431, message = "It teleports the guard members of the Aden Imperial Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ADEN_IMPERIAL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004432, message = "It teleports the guard members of the Gludio Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GLUDIO_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004433, message = "It teleports the guard members of the Dion Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_DION_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004434, message = "It teleports the guard members of the Giran Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GIRAN_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004435, message = "It teleports the guard members of the Dion Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_DION_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE_2;
	
	@ClientString(id = 10004436, message = "It teleports the guard members of the Aden Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ADEN_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004437, message = "It teleports the guard members of the Innadril Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_INNADRIL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004438, message = "It teleports the guard members of the Goddard Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GODDARD_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004439, message = "It teleports the guard members of the Rune Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_RUNE_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004440, message = "It teleports the guard members of the Schuttgart Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_SCHUTTGART_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	@ClientString(id = 10004441, message = "It teleports the guard members of the Elmore Imperial Castle to the inside of the castle.")
	public static NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ELMORE_IMPERIAL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	static
	{
		buildFastLookupTable();
	}
	
	private static void buildFastLookupTable()
	{
		for (Field field : NpcStringId.class.getDeclaredFields())
		{
			final int mod = field.getModifiers();
			if (Modifier.isStatic(mod) && Modifier.isPublic(mod) && field.getType().equals(NpcStringId.class) && field.isAnnotationPresent(ClientString.class))
			{
				try
				{
					final ClientString annotation = field.getAnnotationsByType(ClientString.class)[0];
					final NpcStringId nsId = new NpcStringId(annotation.id());
					nsId.setName(field.getName());
					nsId.setParamCount(parseMessageParameters(field.getName()));
					field.set(null, nsId);
					VALUES.put(nsId.getId(), nsId);
				}
				catch (Exception e)
				{
					LOGGER.log(Level.WARNING, "NpcStringId: Failed field access for '" + field.getName() + "'", e);
				}
			}
		}
	}
	
	private static int parseMessageParameters(String name)
	{
		int paramCount = 0;
		char c1;
		char c2;
		for (int i = 0; i < (name.length() - 1); i++)
		{
			c1 = name.charAt(i);
			if ((c1 == 'C') || (c1 == 'S'))
			{
				c2 = name.charAt(i + 1);
				if (Character.isDigit(c2))
				{
					paramCount = Math.max(paramCount, Character.getNumericValue(c2));
					i++;
				}
			}
		}
		return paramCount;
	}
	
	public static NpcStringId getNpcStringId(int id)
	{
		return getNpcStringIdOrDefault(id, new NpcStringId(id));
	}
	
	public static NpcStringId getNpcStringIdOrDefault(int id, NpcStringId defaultValue)
	{
		final NpcStringId nsi = getNpcStringIdInternal(id);
		return nsi == null ? defaultValue : nsi;
	}
	
	private static NpcStringId getNpcStringIdInternal(int id)
	{
		return VALUES.get(id);
	}
	
	public static NpcStringId getNpcStringId(String name)
	{
		try
		{
			return (NpcStringId) NpcStringId.class.getField(name).get(null);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static void reloadLocalisations()
	{
		for (NpcStringId nsId : VALUES.values())
		{
			if (nsId != null)
			{
				nsId.removeAllLocalisations();
			}
		}
		
		if (!Config.MULTILANG_NS_ENABLE)
		{
			LOGGER.log(Level.INFO, "NpcStringId: MultiLanguage disabled.");
			return;
		}
		
		final List<String> languages = Config.MULTILANG_NS_ALLOWED;
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringComments(true);
		
		File file;
		Node node;
		Document doc;
		NamedNodeMap nnmb;
		NpcStringId nsId;
		String text;
		for (String lang : languages)
		{
			file = new File(Config.DATAPACK_ROOT, "/lang/" + lang + "/ns/NpcStringLocalisation.xml");
			if (!file.isFile())
			{
				continue;
			}
			
			LOGGER.log(Level.INFO, "NpcStringId: Loading localisation for '" + lang + "'");
			
			try
			{
				doc = factory.newDocumentBuilder().parse(file);
				for (Node na = doc.getFirstChild(); na != null; na = na.getNextSibling())
				{
					if ("list".equals(na.getNodeName()))
					{
						for (Node nb = na.getFirstChild(); nb != null; nb = nb.getNextSibling())
						{
							if ("ns".equals(nb.getNodeName()))
							{
								nnmb = nb.getAttributes();
								node = nnmb.getNamedItem("id");
								if (node != null)
								{
									nsId = getNpcStringId(Integer.parseInt(node.getNodeValue()));
								}
								else
								{
									node = nnmb.getNamedItem("name");
									nsId = getNpcStringId(node.getNodeValue());
								}
								if (nsId == null)
								{
									LOGGER.log(Level.WARNING, "NpcStringId: Unknown NSID '" + node.getNodeValue() + "', lang '" + lang + "'.");
									continue;
								}
								
								node = nnmb.getNamedItem("text");
								if (node == null)
								{
									LOGGER.log(Level.WARNING, "NpcStringId: No text defined for NSID '" + nsId + "', lang '" + lang + "'.");
									continue;
								}
								
								text = node.getNodeValue();
								if (text.isEmpty() || (text.length() > 255))
								{
									LOGGER.log(Level.WARNING, "NpcStringId: Invalid text defined for NSID '" + nsId + "' (to long or empty), lang '" + lang + "'.");
									continue;
								}
								
								nsId.attachLocalizedText(lang, text);
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "NpcStringId: Failed loading '" + file + "'", e);
			}
		}
	}
	
	private final int _id;
	private String _name;
	private byte _params;
	private NSLocalisation[] _localisations;
	private ExShowScreenMessage _staticScreenMessage;
	
	protected NpcStringId(int id)
	{
		_id = id;
		_localisations = EMPTY_NSL_ARRAY;
	}
	
	public final int getId()
	{
		return _id;
	}
	
	private final void setName(String name)
	{
		_name = name;
	}
	
	public final String getName()
	{
		return _name;
	}
	
	public final int getParamCount()
	{
		return _params;
	}
	
	public final void setParamCount(int params)
	{
		if (params < 0)
		{
			throw new IllegalArgumentException("Invalid negative param count: " + params);
		}
		
		if (params > 10)
		{
			throw new IllegalArgumentException("Maximum param count exceeded: " + params);
		}
		
		if (params != 0)
		{
			_staticScreenMessage = null;
		}
		
		_params = (byte) params;
	}
	
	public final NSLocalisation getLocalisation(String lang)
	{
		NSLocalisation nsl;
		for (int i = _localisations.length; i-- > 0;)
		{
			nsl = _localisations[i];
			if (nsl.getLanguage().hashCode() == lang.hashCode())
			{
				return nsl;
			}
		}
		return null;
	}
	
	public final void attachLocalizedText(String lang, String text)
	{
		final int length = _localisations.length;
		final NSLocalisation[] localisations = Arrays.copyOf(_localisations, length + 1);
		localisations[length] = new NSLocalisation(lang, text);
		_localisations = localisations;
	}
	
	public final void removeAllLocalisations()
	{
		_localisations = EMPTY_NSL_ARRAY;
	}
	
	public final ExShowScreenMessage getStaticScreenMessage()
	{
		return _staticScreenMessage;
	}
	
	public final void setStaticSystemMessage(ExShowScreenMessage ns)
	{
		_staticScreenMessage = ns;
	}
	
	@Override
	public final String toString()
	{
		return "NS[" + getId() + ":" + getName() + "]";
	}
	
	public static final class NSLocalisation
	{
		private final String _lang;
		private final Builder _builder;
		
		public NSLocalisation(String lang, String text)
		{
			_lang = lang;
			_builder = Builder.newBuilder(text);
		}
		
		public final String getLanguage()
		{
			return _lang;
		}
		
		public final String getLocalisation(Object... params)
		{
			return _builder.toString(params);
		}
	}
}
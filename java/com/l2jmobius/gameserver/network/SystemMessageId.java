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
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public final class SystemMessageId
{
	private static final Logger LOGGER = Logger.getLogger(SystemMessageId.class.getName());
	private static final SMLocalisation[] EMPTY_SML_ARRAY = new SMLocalisation[0];
	public static final SystemMessageId[] EMPTY_ARRAY = new SystemMessageId[0];
	
	private static Map<Integer, SystemMessageId> VALUES = new HashMap<>();
	
	@ClientString(id = 0, message = "You have been disconnected from the server.")
	public static SystemMessageId YOU_HAVE_BEEN_DISCONNECTED_FROM_THE_SERVER;
	
	@ClientString(id = 1, message = "The server will be coming down in $s1 second(s). Please find a safe place to log out.")
	public static SystemMessageId THE_SERVER_WILL_BE_COMING_DOWN_IN_S1_SECOND_S_PLEASE_FIND_A_SAFE_PLACE_TO_LOG_OUT;
	
	@ClientString(id = 2, message = "$s1 does not exist.")
	public static SystemMessageId S1_DOES_NOT_EXIST;
	
	@ClientString(id = 3, message = "$s1 is not currently logged in.")
	public static SystemMessageId S1_IS_NOT_CURRENTLY_LOGGED_IN;
	
	@ClientString(id = 4, message = "You cannot ask yourself to apply to a clan.")
	public static SystemMessageId YOU_CANNOT_ASK_YOURSELF_TO_APPLY_TO_A_CLAN;
	
	@ClientString(id = 5, message = "$s1 already exists.")
	public static SystemMessageId S1_ALREADY_EXISTS;
	
	@ClientString(id = 6, message = "$s1 does not exist.")
	public static SystemMessageId S1_DOES_NOT_EXIST_2;
	
	@ClientString(id = 7, message = "You are already a member of $s1.")
	public static SystemMessageId YOU_ARE_ALREADY_A_MEMBER_OF_S1;
	
	@ClientString(id = 8, message = "You are already a member of another clan.")
	public static SystemMessageId YOU_ARE_ALREADY_A_MEMBER_OF_ANOTHER_CLAN;
	
	@ClientString(id = 9, message = "$s1 is not a clan leader.")
	public static SystemMessageId S1_IS_NOT_A_CLAN_LEADER;
	
	@ClientString(id = 10, message = "$s1 is already a member of another clan.")
	public static SystemMessageId S1_IS_ALREADY_A_MEMBER_OF_ANOTHER_CLAN;
	
	@ClientString(id = 11, message = "There are no applicants for this clan.")
	public static SystemMessageId THERE_ARE_NO_APPLICANTS_FOR_THIS_CLAN;
	
	@ClientString(id = 12, message = "Applicant information is incorrect.")
	public static SystemMessageId APPLICANT_INFORMATION_IS_INCORRECT;
	
	@ClientString(id = 13, message = "Unable to dissolve: your clan has requested to participate in a castle siege.")
	public static SystemMessageId UNABLE_TO_DISSOLVE_YOUR_CLAN_HAS_REQUESTED_TO_PARTICIPATE_IN_A_CASTLE_SIEGE;
	
	@ClientString(id = 14, message = "Unable to dissolve: your clan owns one or more castles or hideouts.")
	public static SystemMessageId UNABLE_TO_DISSOLVE_YOUR_CLAN_OWNS_ONE_OR_MORE_CASTLES_OR_HIDEOUTS;
	
	@ClientString(id = 15, message = "You are in siege.")
	public static SystemMessageId YOU_ARE_IN_SIEGE;
	
	@ClientString(id = 16, message = "You are not in siege.")
	public static SystemMessageId YOU_ARE_NOT_IN_SIEGE;
	
	@ClientString(id = 17, message = "The castle siege has begun.")
	public static SystemMessageId THE_CASTLE_SIEGE_HAS_BEGUN;
	
	@ClientString(id = 18, message = "The castle siege has ended.")
	public static SystemMessageId THE_CASTLE_SIEGE_HAS_ENDED;
	
	@ClientString(id = 19, message = "There is a new Lord of the castle!")
	public static SystemMessageId THERE_IS_A_NEW_LORD_OF_THE_CASTLE;
	
	@ClientString(id = 20, message = "The gate is being opened.")
	public static SystemMessageId THE_GATE_IS_BEING_OPENED;
	
	@ClientString(id = 21, message = "The gate is being destroyed.")
	public static SystemMessageId THE_GATE_IS_BEING_DESTROYED;
	
	@ClientString(id = 22, message = "Your target is out of range.")
	public static SystemMessageId YOUR_TARGET_IS_OUT_OF_RANGE;
	
	@ClientString(id = 23, message = "Not enough HP.")
	public static SystemMessageId NOT_ENOUGH_HP;
	
	@ClientString(id = 24, message = "Not enough MP.")
	public static SystemMessageId NOT_ENOUGH_MP;
	
	@ClientString(id = 25, message = "Rejuvenating HP.")
	public static SystemMessageId REJUVENATING_HP;
	
	@ClientString(id = 26, message = "Rejuvenating MP.")
	public static SystemMessageId REJUVENATING_MP;
	
	@ClientString(id = 27, message = "Your casting has been interrupted.")
	public static SystemMessageId YOUR_CASTING_HAS_BEEN_INTERRUPTED;
	
	@ClientString(id = 28, message = "You have obtained $s1 adena.")
	public static SystemMessageId YOU_HAVE_OBTAINED_S1_ADENA;
	
	@ClientString(id = 29, message = "You have obtained $s2 $s1.")
	public static SystemMessageId YOU_HAVE_OBTAINED_S2_S1;
	
	@ClientString(id = 30, message = "You have obtained $s1.")
	public static SystemMessageId YOU_HAVE_OBTAINED_S1;
	
	@ClientString(id = 31, message = "You cannot move while sitting.")
	public static SystemMessageId YOU_CANNOT_MOVE_WHILE_SITTING;
	
	@ClientString(id = 32, message = "You are unable to engage in combat. Please go to the nearest restart point.")
	public static SystemMessageId YOU_ARE_UNABLE_TO_ENGAGE_IN_COMBAT_PLEASE_GO_TO_THE_NEAREST_RESTART_POINT;
	
	@ClientString(id = 33, message = "You cannot move while casting.")
	public static SystemMessageId YOU_CANNOT_MOVE_WHILE_CASTING;
	
	@ClientString(id = 34, message = "Welcome to the World of Lineage II.")
	public static SystemMessageId WELCOME_TO_THE_WORLD_OF_LINEAGE_II;
	
	@ClientString(id = 35, message = "You hit for $s1 damage.")
	public static SystemMessageId YOU_HIT_FOR_S1_DAMAGE;
	
	@ClientString(id = 36, message = "$c1 hit you for $s2 damage.")
	public static SystemMessageId C1_HIT_YOU_FOR_S2_DAMAGE;
	
	@ClientString(id = 37, message = "$c1 hit you for $s2 damage.")
	public static SystemMessageId C1_HIT_YOU_FOR_S2_DAMAGE_2;
	
	@ClientString(id = 38, message = "The TGS2002 event begins!")
	public static SystemMessageId THE_TGS2002_EVENT_BEGINS;
	
	@ClientString(id = 39, message = "The TGS2002 event is over. Thank you very much.")
	public static SystemMessageId THE_TGS2002_EVENT_IS_OVER_THANK_YOU_VERY_MUCH;
	
	@ClientString(id = 40, message = "This is the TGS demo: the character will immediately be restored.")
	public static SystemMessageId THIS_IS_THE_TGS_DEMO_THE_CHARACTER_WILL_IMMEDIATELY_BE_RESTORED;
	
	@ClientString(id = 41, message = "You carefully nock an arrow.")
	public static SystemMessageId YOU_CAREFULLY_NOCK_AN_ARROW;
	
	@ClientString(id = 42, message = "You have avoided $c1's attack.")
	public static SystemMessageId YOU_HAVE_AVOIDED_C1_S_ATTACK;
	
	@ClientString(id = 43, message = "You have missed.")
	public static SystemMessageId YOU_HAVE_MISSED;
	
	@ClientString(id = 44, message = "Critical hit!")
	public static SystemMessageId CRITICAL_HIT;
	
	@ClientString(id = 45, message = "You have earned $s1 experience.")
	public static SystemMessageId YOU_HAVE_EARNED_S1_EXPERIENCE;
	
	@ClientString(id = 46, message = "You use $s1.")
	public static SystemMessageId YOU_USE_S1;
	
	@ClientString(id = 47, message = "You begin to use a(n) $s1.")
	public static SystemMessageId YOU_BEGIN_TO_USE_A_N_S1;
	
	@ClientString(id = 48, message = "$s1 is not available at this time: being prepared for reuse.")
	public static SystemMessageId S1_IS_NOT_AVAILABLE_AT_THIS_TIME_BEING_PREPARED_FOR_REUSE;
	
	@ClientString(id = 49, message = "You have equipped your $s1.")
	public static SystemMessageId YOU_HAVE_EQUIPPED_YOUR_S1;
	
	@ClientString(id = 50, message = "Your target cannot be found.")
	public static SystemMessageId YOUR_TARGET_CANNOT_BE_FOUND;
	
	@ClientString(id = 51, message = "You cannot use this on yourself.")
	public static SystemMessageId YOU_CANNOT_USE_THIS_ON_YOURSELF;
	
	@ClientString(id = 52, message = "You have earned $s1 adena.")
	public static SystemMessageId YOU_HAVE_EARNED_S1_ADENA;
	
	@ClientString(id = 53, message = "You have earned $s2 $s1(s).")
	public static SystemMessageId YOU_HAVE_EARNED_S2_S1_S;
	
	@ClientString(id = 54, message = "You have earned $s1.")
	public static SystemMessageId YOU_HAVE_EARNED_S1;
	
	@ClientString(id = 55, message = "You have failed to pick up $s1 adena.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_PICK_UP_S1_ADENA;
	
	@ClientString(id = 56, message = "You have failed to pick up $s1.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_PICK_UP_S1;
	
	@ClientString(id = 57, message = "You have failed to pick up $s2 $s1(s).")
	public static SystemMessageId YOU_HAVE_FAILED_TO_PICK_UP_S2_S1_S;
	
	@ClientString(id = 58, message = "You have failed to earn $s1 adena.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_EARN_S1_ADENA;
	
	@ClientString(id = 59, message = "You have failed to earn $s1.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_EARN_S1;
	
	@ClientString(id = 60, message = "You have failed to earn $s2 $s1(s).")
	public static SystemMessageId YOU_HAVE_FAILED_TO_EARN_S2_S1_S;
	
	@ClientString(id = 61, message = "Nothing happened.")
	public static SystemMessageId NOTHING_HAPPENED;
	
	@ClientString(id = 62, message = "Your $s1 has been successfully enchanted.")
	public static SystemMessageId YOUR_S1_HAS_BEEN_SUCCESSFULLY_ENCHANTED;
	
	@ClientString(id = 63, message = "Your +$S1 $S2 has been successfully enchanted.")
	public static SystemMessageId YOUR_S1_S2_HAS_BEEN_SUCCESSFULLY_ENCHANTED;
	
	@ClientString(id = 64, message = "The enchantment has failed! Your $s1 has been crystallized.")
	public static SystemMessageId THE_ENCHANTMENT_HAS_FAILED_YOUR_S1_HAS_BEEN_CRYSTALLIZED;
	
	@ClientString(id = 65, message = "The enchantment has failed! Your +$s1 $s2 has been crystallized.")
	public static SystemMessageId THE_ENCHANTMENT_HAS_FAILED_YOUR_S1_S2_HAS_BEEN_CRYSTALLIZED;
	
	@ClientString(id = 66, message = "$c1 is inviting you to join a party. Do you accept?")
	public static SystemMessageId C1_IS_INVITING_YOU_TO_JOIN_A_PARTY_DO_YOU_ACCEPT;
	
	@ClientString(id = 67, message = "$s1 has invited you to join their clan, $s2. Do you wish to join?")
	public static SystemMessageId S1_HAS_INVITED_YOU_TO_JOIN_THEIR_CLAN_S2_DO_YOU_WISH_TO_JOIN;
	
	@ClientString(id = 68, message = "Would you like to withdraw from clan $s1? If you leave, you will have to wait at least a day before joining another clan.")
	public static SystemMessageId WOULD_YOU_LIKE_TO_WITHDRAW_FROM_CLAN_S1_IF_YOU_LEAVE_YOU_WILL_HAVE_TO_WAIT_AT_LEAST_A_DAY_BEFORE_JOINING_ANOTHER_CLAN;
	
	@ClientString(id = 69, message = "Would you like to dismiss $s1 from the clan? If you do so, you will have to wait at least a day before accepting a new member.")
	public static SystemMessageId WOULD_YOU_LIKE_TO_DISMISS_S1_FROM_THE_CLAN_IF_YOU_DO_SO_YOU_WILL_HAVE_TO_WAIT_AT_LEAST_A_DAY_BEFORE_ACCEPTING_A_NEW_MEMBER;
	
	@ClientString(id = 70, message = "Do you wish to disperse the clan, $s1?")
	public static SystemMessageId DO_YOU_WISH_TO_DISPERSE_THE_CLAN_S1;
	
	@ClientString(id = 71, message = "How much $s1(s) do you wish to discard?")
	public static SystemMessageId HOW_MUCH_S1_S_DO_YOU_WISH_TO_DISCARD;
	
	@ClientString(id = 72, message = "How much $s1(s) do you wish to move?")
	public static SystemMessageId HOW_MUCH_S1_S_DO_YOU_WISH_TO_MOVE;
	
	@ClientString(id = 73, message = "How much $s1(s) do you wish to destroy?")
	public static SystemMessageId HOW_MUCH_S1_S_DO_YOU_WISH_TO_DESTROY;
	
	@ClientString(id = 74, message = "Do you wish to destroy your $s1?")
	public static SystemMessageId DO_YOU_WISH_TO_DESTROY_YOUR_S1;
	
	@ClientString(id = 75, message = "ID does not exist.")
	public static SystemMessageId ID_DOES_NOT_EXIST;
	
	@ClientString(id = 76, message = "Incorrect password.")
	public static SystemMessageId INCORRECT_PASSWORD;
	
	@ClientString(id = 77, message = "You cannot create another character. Please delete an existing character and try again.")
	public static SystemMessageId YOU_CANNOT_CREATE_ANOTHER_CHARACTER_PLEASE_DELETE_AN_EXISTING_CHARACTER_AND_TRY_AGAIN;
	
	@ClientString(id = 78, message = "When you delete a character, any items in his/her possession will also be deleted. Do you really wish to delete $s1?")
	public static SystemMessageId WHEN_YOU_DELETE_A_CHARACTER_ANY_ITEMS_IN_HIS_HER_POSSESSION_WILL_ALSO_BE_DELETED_DO_YOU_REALLY_WISH_TO_DELETE_S1;
	
	@ClientString(id = 79, message = "This name already exists.")
	public static SystemMessageId THIS_NAME_ALREADY_EXISTS;
	
	@ClientString(id = 80, message = "Your title cannot exceed 16 characters in length.  Please try again.")
	public static SystemMessageId YOUR_TITLE_CANNOT_EXCEED_16_CHARACTERS_IN_LENGTH_PLEASE_TRY_AGAIN;
	
	@ClientString(id = 81, message = "Please select your race.")
	public static SystemMessageId PLEASE_SELECT_YOUR_RACE;
	
	@ClientString(id = 82, message = "Please select your occupation.")
	public static SystemMessageId PLEASE_SELECT_YOUR_OCCUPATION;
	
	@ClientString(id = 83, message = "Please select your gender.")
	public static SystemMessageId PLEASE_SELECT_YOUR_GENDER;
	
	@ClientString(id = 84, message = "You may not attack in a peaceful zone.")
	public static SystemMessageId YOU_MAY_NOT_ATTACK_IN_A_PEACEFUL_ZONE;
	
	@ClientString(id = 85, message = "You may not attack this target in a peaceful zone.")
	public static SystemMessageId YOU_MAY_NOT_ATTACK_THIS_TARGET_IN_A_PEACEFUL_ZONE;
	
	@ClientString(id = 86, message = "Please enter your ID.")
	public static SystemMessageId PLEASE_ENTER_YOUR_ID;
	
	@ClientString(id = 87, message = "Please enter your password.")
	public static SystemMessageId PLEASE_ENTER_YOUR_PASSWORD;
	
	@ClientString(id = 88, message = "Your protocol version is different, please restart your client and run a full check.")
	public static SystemMessageId YOUR_PROTOCOL_VERSION_IS_DIFFERENT_PLEASE_RESTART_YOUR_CLIENT_AND_RUN_A_FULL_CHECK;
	
	@ClientString(id = 89, message = "Your protocol version is different, please continue.")
	public static SystemMessageId YOUR_PROTOCOL_VERSION_IS_DIFFERENT_PLEASE_CONTINUE;
	
	@ClientString(id = 90, message = "You are unable to connect to the server.")
	public static SystemMessageId YOU_ARE_UNABLE_TO_CONNECT_TO_THE_SERVER;
	
	@ClientString(id = 91, message = "Please select your hairstyle.")
	public static SystemMessageId PLEASE_SELECT_YOUR_HAIRSTYLE;
	
	@ClientString(id = 92, message = "$s1 has worn off.")
	public static SystemMessageId S1_HAS_WORN_OFF;
	
	@ClientString(id = 93, message = "You do not have enough SP for this.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_SP_FOR_THIS;
	
	@ClientString(id = 94, message = "2004-2011 © NC Interactive, Inc. All Rights Reserved.")
	public static SystemMessageId NC_INTERACTIVE_INC_ALL_RIGHTS_RESERVED;
	
	@ClientString(id = 95, message = "You have earned $s1 experience and $s2 SP.")
	public static SystemMessageId YOU_HAVE_EARNED_S1_EXPERIENCE_AND_S2_SP;
	
	@ClientString(id = 96, message = "Your level has increased!")
	public static SystemMessageId YOUR_LEVEL_HAS_INCREASED;
	
	@ClientString(id = 97, message = "This item cannot be moved.")
	public static SystemMessageId THIS_ITEM_CANNOT_BE_MOVED;
	
	@ClientString(id = 98, message = "This item cannot be discarded.")
	public static SystemMessageId THIS_ITEM_CANNOT_BE_DISCARDED;
	
	@ClientString(id = 99, message = "This item cannot be traded or sold.")
	public static SystemMessageId THIS_ITEM_CANNOT_BE_TRADED_OR_SOLD;
	
	@ClientString(id = 100, message = "$c1 is requesting a trade. Do you wish to continue?")
	public static SystemMessageId C1_IS_REQUESTING_A_TRADE_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 101, message = "You cannot exit the game while in combat.")
	public static SystemMessageId YOU_CANNOT_EXIT_THE_GAME_WHILE_IN_COMBAT;
	
	@ClientString(id = 102, message = "You cannot restart while in combat.")
	public static SystemMessageId YOU_CANNOT_RESTART_WHILE_IN_COMBAT;
	
	@ClientString(id = 103, message = "This ID is currently logged in.")
	public static SystemMessageId THIS_ID_IS_CURRENTLY_LOGGED_IN;
	
	@ClientString(id = 104, message = "You cannot change weapons during an attack.")
	public static SystemMessageId YOU_CANNOT_CHANGE_WEAPONS_DURING_AN_ATTACK;
	
	@ClientString(id = 105, message = "$c1 has been invited to the party.")
	public static SystemMessageId C1_HAS_BEEN_INVITED_TO_THE_PARTY;
	
	@ClientString(id = 106, message = "You have joined $s1's party.")
	public static SystemMessageId YOU_HAVE_JOINED_S1_S_PARTY;
	
	@ClientString(id = 107, message = "$c1 has joined the party.")
	public static SystemMessageId C1_HAS_JOINED_THE_PARTY;
	
	@ClientString(id = 108, message = "$c1 has left the party.")
	public static SystemMessageId C1_HAS_LEFT_THE_PARTY;
	
	@ClientString(id = 109, message = "Invalid target.")
	public static SystemMessageId INVALID_TARGET;
	
	@ClientString(id = 110, message = "$s1’s effect can be felt.")
	public static SystemMessageId S1_S_EFFECT_CAN_BE_FELT;
	
	@ClientString(id = 111, message = "Your shield defense has succeeded.")
	public static SystemMessageId YOUR_SHIELD_DEFENSE_HAS_SUCCEEDED;
	
	@ClientString(id = 112, message = "You have run out of arrows.")
	public static SystemMessageId YOU_HAVE_RUN_OUT_OF_ARROWS;
	
	@ClientString(id = 113, message = "$s1 cannot be used due to unsuitable terms.")
	public static SystemMessageId S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS;
	
	@ClientString(id = 114, message = "You have entered the shadow of the Mother Tree.")
	public static SystemMessageId YOU_HAVE_ENTERED_THE_SHADOW_OF_THE_MOTHER_TREE;
	
	@ClientString(id = 115, message = "You have left the shadow of the Mother Tree.")
	public static SystemMessageId YOU_HAVE_LEFT_THE_SHADOW_OF_THE_MOTHER_TREE;
	
	@ClientString(id = 116, message = "You have entered a peace zone.")
	public static SystemMessageId YOU_HAVE_ENTERED_A_PEACE_ZONE;
	
	@ClientString(id = 117, message = "You have left the peace zone.")
	public static SystemMessageId YOU_HAVE_LEFT_THE_PEACE_ZONE;
	
	@ClientString(id = 118, message = "You have requested a trade with $c1.")
	public static SystemMessageId YOU_HAVE_REQUESTED_A_TRADE_WITH_C1;
	
	@ClientString(id = 119, message = "$c1 has denied your request to trade.")
	public static SystemMessageId C1_HAS_DENIED_YOUR_REQUEST_TO_TRADE;
	
	@ClientString(id = 120, message = "You begin trading with $c1.")
	public static SystemMessageId YOU_BEGIN_TRADING_WITH_C1;
	
	@ClientString(id = 121, message = "$c1 has confirmed the trade.")
	public static SystemMessageId C1_HAS_CONFIRMED_THE_TRADE;
	
	@ClientString(id = 122, message = "You may no longer adjust items in the trade because the trade has been confirmed.")
	public static SystemMessageId YOU_MAY_NO_LONGER_ADJUST_ITEMS_IN_THE_TRADE_BECAUSE_THE_TRADE_HAS_BEEN_CONFIRMED;
	
	@ClientString(id = 123, message = "Your trade was successful.")
	public static SystemMessageId YOUR_TRADE_WAS_SUCCESSFUL;
	
	@ClientString(id = 124, message = "$c1 has cancelled the trade.")
	public static SystemMessageId C1_HAS_CANCELLED_THE_TRADE;
	
	@ClientString(id = 125, message = "Do you wish to exit the game?")
	public static SystemMessageId DO_YOU_WISH_TO_EXIT_THE_GAME;
	
	@ClientString(id = 126, message = "Do you wish to exit to the character select screen?")
	public static SystemMessageId DO_YOU_WISH_TO_EXIT_TO_THE_CHARACTER_SELECT_SCREEN;
	
	@ClientString(id = 127, message = "You have been disconnected from the server. Please login again.")
	public static SystemMessageId YOU_HAVE_BEEN_DISCONNECTED_FROM_THE_SERVER_PLEASE_LOGIN_AGAIN;
	
	@ClientString(id = 128, message = "Your character creation has failed.")
	public static SystemMessageId YOUR_CHARACTER_CREATION_HAS_FAILED;
	
	@ClientString(id = 129, message = "Your inventory is full.")
	public static SystemMessageId YOUR_INVENTORY_IS_FULL;
	
	@ClientString(id = 130, message = "Your warehouse is full.")
	public static SystemMessageId YOUR_WAREHOUSE_IS_FULL;
	
	@ClientString(id = 131, message = "$s1 has logged in.")
	public static SystemMessageId S1_HAS_LOGGED_IN;
	
	@ClientString(id = 132, message = "$s1 has been added to your friends list.")
	public static SystemMessageId S1_HAS_BEEN_ADDED_TO_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 133, message = "$s1 has been removed from your friends list.")
	public static SystemMessageId S1_HAS_BEEN_REMOVED_FROM_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 134, message = "Please check your friends list again.")
	public static SystemMessageId PLEASE_CHECK_YOUR_FRIENDS_LIST_AGAIN;
	
	@ClientString(id = 135, message = "$c1 did not reply to your invitation. Your invitation has been cancelled.")
	public static SystemMessageId C1_DID_NOT_REPLY_TO_YOUR_INVITATION_YOUR_INVITATION_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 136, message = "You have not replied to $c1's invitation. The offer has been cancelled.")
	public static SystemMessageId YOU_HAVE_NOT_REPLIED_TO_C1_S_INVITATION_THE_OFFER_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 137, message = "There are no more items in the shortcut.")
	public static SystemMessageId THERE_ARE_NO_MORE_ITEMS_IN_THE_SHORTCUT;
	
	@ClientString(id = 138, message = "Designate shortcut.")
	public static SystemMessageId DESIGNATE_SHORTCUT;
	
	@ClientString(id = 139, message = "$c1 has resisted your $s2.")
	public static SystemMessageId C1_HAS_RESISTED_YOUR_S2;
	
	@ClientString(id = 140, message = "Your skill was deactivated due to lack of MP.")
	public static SystemMessageId YOUR_SKILL_WAS_DEACTIVATED_DUE_TO_LACK_OF_MP;
	
	@ClientString(id = 141, message = "Once a trade is confirmed, the items involved cannot be moved again. If you wish to make a change, cancel the trade and start again.")
	public static SystemMessageId ONCE_A_TRADE_IS_CONFIRMED_THE_ITEMS_INVOLVED_CANNOT_BE_MOVED_AGAIN_IF_YOU_WISH_TO_MAKE_A_CHANGE_CANCEL_THE_TRADE_AND_START_AGAIN;
	
	@ClientString(id = 142, message = "You are already trading with someone.")
	public static SystemMessageId YOU_ARE_ALREADY_TRADING_WITH_SOMEONE;
	
	@ClientString(id = 143, message = "$c1 is already trading with another person. Please try again later.")
	public static SystemMessageId C1_IS_ALREADY_TRADING_WITH_ANOTHER_PERSON_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 144, message = "That is an incorrect target.")
	public static SystemMessageId THAT_IS_AN_INCORRECT_TARGET;
	
	@ClientString(id = 145, message = "That player is not online.")
	public static SystemMessageId THAT_PLAYER_IS_NOT_ONLINE;
	
	@ClientString(id = 146, message = "Chatting is now permitted.")
	public static SystemMessageId CHATTING_IS_NOW_PERMITTED;
	
	@ClientString(id = 147, message = "Chatting is currently prohibited.")
	public static SystemMessageId CHATTING_IS_CURRENTLY_PROHIBITED;
	
	@ClientString(id = 148, message = "You cannot use quest items.")
	public static SystemMessageId YOU_CANNOT_USE_QUEST_ITEMS;
	
	@ClientString(id = 149, message = "You cannot pick up or use items while trading.")
	public static SystemMessageId YOU_CANNOT_PICK_UP_OR_USE_ITEMS_WHILE_TRADING;
	
	@ClientString(id = 150, message = "You cannot discard or destroy an item while trading at a private store.")
	public static SystemMessageId YOU_CANNOT_DISCARD_OR_DESTROY_AN_ITEM_WHILE_TRADING_AT_A_PRIVATE_STORE;
	
	@ClientString(id = 151, message = "You cannot discard something that far away from you.")
	public static SystemMessageId YOU_CANNOT_DISCARD_SOMETHING_THAT_FAR_AWAY_FROM_YOU;
	
	@ClientString(id = 152, message = "You have invited the wrong target.")
	public static SystemMessageId YOU_HAVE_INVITED_THE_WRONG_TARGET;
	
	@ClientString(id = 153, message = "$c1 is on another task. Please try again later.")
	public static SystemMessageId C1_IS_ON_ANOTHER_TASK_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 154, message = "Only the leader can give out invitations.")
	public static SystemMessageId ONLY_THE_LEADER_CAN_GIVE_OUT_INVITATIONS;
	
	@ClientString(id = 155, message = "The party is full.")
	public static SystemMessageId THE_PARTY_IS_FULL;
	
	@ClientString(id = 156, message = "Drain was only 50 percent successful.")
	public static SystemMessageId DRAIN_WAS_ONLY_50_PERCENT_SUCCESSFUL;
	
	@ClientString(id = 157, message = "You resisted $c1's drain.")
	public static SystemMessageId YOU_RESISTED_C1_S_DRAIN;
	
	@ClientString(id = 158, message = "Your attack has failed.")
	public static SystemMessageId YOUR_ATTACK_HAS_FAILED;
	
	@ClientString(id = 159, message = "You resisted $c1's magic.")
	public static SystemMessageId YOU_RESISTED_C1_S_MAGIC;
	
	@ClientString(id = 160, message = "$c1 is a member of another party and cannot be invited.")
	public static SystemMessageId C1_IS_A_MEMBER_OF_ANOTHER_PARTY_AND_CANNOT_BE_INVITED;
	
	@ClientString(id = 161, message = "That player is not currently online.")
	public static SystemMessageId THAT_PLAYER_IS_NOT_CURRENTLY_ONLINE;
	
	@ClientString(id = 162, message = "You have moved too far away from the warehouse to perform that action.")
	public static SystemMessageId YOU_HAVE_MOVED_TOO_FAR_AWAY_FROM_THE_WAREHOUSE_TO_PERFORM_THAT_ACTION;
	
	@ClientString(id = 163, message = "You cannot destroy it because the number is incorrect.")
	public static SystemMessageId YOU_CANNOT_DESTROY_IT_BECAUSE_THE_NUMBER_IS_INCORRECT;
	
	@ClientString(id = 164, message = "Waiting for another reply.")
	public static SystemMessageId WAITING_FOR_ANOTHER_REPLY;
	
	@ClientString(id = 165, message = "You cannot add yourself to your own friend list.")
	public static SystemMessageId YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIEND_LIST;
	
	@ClientString(id = 166, message = "Friend list is not ready yet. Please register again later.")
	public static SystemMessageId FRIEND_LIST_IS_NOT_READY_YET_PLEASE_REGISTER_AGAIN_LATER;
	
	@ClientString(id = 167, message = "$c1 is already on your friend list.")
	public static SystemMessageId C1_IS_ALREADY_ON_YOUR_FRIEND_LIST;
	
	@ClientString(id = 168, message = "$c1 has sent a friend request.")
	public static SystemMessageId C1_HAS_SENT_A_FRIEND_REQUEST;
	
	@ClientString(id = 169, message = "Accept friendship 0/1 (1 to accept, 0 to deny)")
	public static SystemMessageId ACCEPT_FRIENDSHIP_0_1_1_TO_ACCEPT_0_TO_DENY;
	
	@ClientString(id = 170, message = "The user who requested to become friends is not found in the game.")
	public static SystemMessageId THE_USER_WHO_REQUESTED_TO_BECOME_FRIENDS_IS_NOT_FOUND_IN_THE_GAME;
	
	@ClientString(id = 171, message = "$c1 is not on your friend list.")
	public static SystemMessageId C1_IS_NOT_ON_YOUR_FRIEND_LIST;
	
	@ClientString(id = 172, message = "You lack the funds needed to pay for this transaction.")
	public static SystemMessageId YOU_LACK_THE_FUNDS_NEEDED_TO_PAY_FOR_THIS_TRANSACTION;
	
	@ClientString(id = 173, message = "You lack the funds needed to pay for this transaction.")
	public static SystemMessageId YOU_LACK_THE_FUNDS_NEEDED_TO_PAY_FOR_THIS_TRANSACTION_2;
	
	@ClientString(id = 174, message = "That person's inventory is full.")
	public static SystemMessageId THAT_PERSON_S_INVENTORY_IS_FULL;
	
	@ClientString(id = 175, message = "That skill has been de-activated as HP was fully recovered.")
	public static SystemMessageId THAT_SKILL_HAS_BEEN_DE_ACTIVATED_AS_HP_WAS_FULLY_RECOVERED;
	
	@ClientString(id = 176, message = "That person is in message refusal mode.")
	public static SystemMessageId THAT_PERSON_IS_IN_MESSAGE_REFUSAL_MODE;
	
	@ClientString(id = 177, message = "Message refusal mode.")
	public static SystemMessageId MESSAGE_REFUSAL_MODE;
	
	@ClientString(id = 178, message = "Message acceptance mode.")
	public static SystemMessageId MESSAGE_ACCEPTANCE_MODE;
	
	@ClientString(id = 179, message = "You cannot discard those items here.")
	public static SystemMessageId YOU_CANNOT_DISCARD_THOSE_ITEMS_HERE;
	
	@ClientString(id = 180, message = "You have $s1 day(s) left until deletion. Do you wish to cancel this action?")
	public static SystemMessageId YOU_HAVE_S1_DAY_S_LEFT_UNTIL_DELETION_DO_YOU_WISH_TO_CANCEL_THIS_ACTION;
	
	@ClientString(id = 181, message = "Cannot see target.")
	public static SystemMessageId CANNOT_SEE_TARGET;
	
	@ClientString(id = 182, message = "Do you wish to stop the currently selected '$s1' quest?")
	public static SystemMessageId DO_YOU_WISH_TO_STOP_THE_CURRENTLY_SELECTED_S1_QUEST;
	
	@ClientString(id = 183, message = "There are too many users on the server. Please try again later.")
	public static SystemMessageId THERE_ARE_TOO_MANY_USERS_ON_THE_SERVER_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 184, message = "Please try again later.")
	public static SystemMessageId PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 185, message = "You must first select a user to invite to your party.")
	public static SystemMessageId YOU_MUST_FIRST_SELECT_A_USER_TO_INVITE_TO_YOUR_PARTY;
	
	@ClientString(id = 186, message = "You must first select a user to invite to your clan.")
	public static SystemMessageId YOU_MUST_FIRST_SELECT_A_USER_TO_INVITE_TO_YOUR_CLAN;
	
	@ClientString(id = 187, message = "Select user to expel.")
	public static SystemMessageId SELECT_USER_TO_EXPEL;
	
	@ClientString(id = 188, message = "Please create your clan name.")
	public static SystemMessageId PLEASE_CREATE_YOUR_CLAN_NAME;
	
	@ClientString(id = 189, message = "Your clan has been created.")
	public static SystemMessageId YOUR_CLAN_HAS_BEEN_CREATED;
	
	@ClientString(id = 190, message = "You have failed to create a clan.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_CREATE_A_CLAN;
	
	@ClientString(id = 191, message = "Clan member $s1 has been expelled.")
	public static SystemMessageId CLAN_MEMBER_S1_HAS_BEEN_EXPELLED;
	
	@ClientString(id = 192, message = "You have failed to expel $s1 from the clan.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_EXPEL_S1_FROM_THE_CLAN;
	
	@ClientString(id = 193, message = "Clan has dispersed.")
	public static SystemMessageId CLAN_HAS_DISPERSED;
	
	@ClientString(id = 194, message = "You have failed to disperse the clan.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_DISPERSE_THE_CLAN;
	
	@ClientString(id = 195, message = "Entered the clan.")
	public static SystemMessageId ENTERED_THE_CLAN;
	
	@ClientString(id = 196, message = "$s1 declined your clan invitation.")
	public static SystemMessageId S1_DECLINED_YOUR_CLAN_INVITATION;
	
	@ClientString(id = 197, message = "You have withdrawn from the clan.")
	public static SystemMessageId YOU_HAVE_WITHDRAWN_FROM_THE_CLAN;
	
	@ClientString(id = 198, message = "You have failed to withdraw from the $s1 clan.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_WITHDRAW_FROM_THE_S1_CLAN;
	
	@ClientString(id = 199, message = "You have recently been dismissed from a clan. You are not allowed to join another clan for 24-hours.")
	public static SystemMessageId YOU_HAVE_RECENTLY_BEEN_DISMISSED_FROM_A_CLAN_YOU_ARE_NOT_ALLOWED_TO_JOIN_ANOTHER_CLAN_FOR_24_HOURS;
	
	@ClientString(id = 200, message = "You have withdrawn from the party.")
	public static SystemMessageId YOU_HAVE_WITHDRAWN_FROM_THE_PARTY;
	
	@ClientString(id = 201, message = "$c1 was expelled from the party.")
	public static SystemMessageId C1_WAS_EXPELLED_FROM_THE_PARTY;
	
	@ClientString(id = 202, message = "You have been expelled from the party.")
	public static SystemMessageId YOU_HAVE_BEEN_EXPELLED_FROM_THE_PARTY;
	
	@ClientString(id = 203, message = "The party has dispersed.")
	public static SystemMessageId THE_PARTY_HAS_DISPERSED;
	
	@ClientString(id = 204, message = "Incorrect name. Please try again.")
	public static SystemMessageId INCORRECT_NAME_PLEASE_TRY_AGAIN;
	
	@ClientString(id = 205, message = "Incorrect character name. Please try again.")
	public static SystemMessageId INCORRECT_CHARACTER_NAME_PLEASE_TRY_AGAIN;
	
	@ClientString(id = 206, message = "Please enter the name of the clan you wish to declare war on.")
	public static SystemMessageId PLEASE_ENTER_THE_NAME_OF_THE_CLAN_YOU_WISH_TO_DECLARE_WAR_ON;
	
	@ClientString(id = 207, message = "$s2 of the clan $s1 requests a declaration of war. Do you accept?")
	public static SystemMessageId S2_OF_THE_CLAN_S1_REQUESTS_A_DECLARATION_OF_WAR_DO_YOU_ACCEPT;
	
	@ClientString(id = 208, message = "Please include file type when entering file path.")
	public static SystemMessageId PLEASE_INCLUDE_FILE_TYPE_WHEN_ENTERING_FILE_PATH;
	
	@ClientString(id = 209, message = "The size of the image file is inappropriate. Please adjust to 16x12 pixels.")
	public static SystemMessageId THE_SIZE_OF_THE_IMAGE_FILE_IS_INAPPROPRIATE_PLEASE_ADJUST_TO_16X12_PIXELS;
	
	@ClientString(id = 210, message = "Cannot find file. Please enter precise path.")
	public static SystemMessageId CANNOT_FIND_FILE_PLEASE_ENTER_PRECISE_PATH;
	
	@ClientString(id = 211, message = "You can only register 16x12 pixel 256 color bmp files.")
	public static SystemMessageId YOU_CAN_ONLY_REGISTER_16X12_PIXEL_256_COLOR_BMP_FILES;
	
	@ClientString(id = 212, message = "You are not a clan member and cannot perform this action.")
	public static SystemMessageId YOU_ARE_NOT_A_CLAN_MEMBER_AND_CANNOT_PERFORM_THIS_ACTION;
	
	@ClientString(id = 213, message = "Not working. Please try again later.")
	public static SystemMessageId NOT_WORKING_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 214, message = "Your title has been changed.")
	public static SystemMessageId YOUR_TITLE_HAS_BEEN_CHANGED;
	
	@ClientString(id = 215, message = "War with the $s1 clan has begun.")
	public static SystemMessageId WAR_WITH_THE_S1_CLAN_HAS_BEGUN;
	
	@ClientString(id = 216, message = "War with the $s1 clan has ended.")
	public static SystemMessageId WAR_WITH_THE_S1_CLAN_HAS_ENDED;
	
	@ClientString(id = 217, message = "You have won the war over the $s1 clan!")
	public static SystemMessageId YOU_HAVE_WON_THE_WAR_OVER_THE_S1_CLAN;
	
	@ClientString(id = 218, message = "You have surrendered to the $s1 clan.")
	public static SystemMessageId YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN;
	
	@ClientString(id = 219, message = "Your clan leader has died. You have been defeated by the $s1 Clan.")
	public static SystemMessageId YOUR_CLAN_LEADER_HAS_DIED_YOU_HAVE_BEEN_DEFEATED_BY_THE_S1_CLAN;
	
	@ClientString(id = 220, message = "You have $s1 minute(s) left until the clan war ends.")
	public static SystemMessageId YOU_HAVE_S1_MINUTE_S_LEFT_UNTIL_THE_CLAN_WAR_ENDS;
	
	@ClientString(id = 221, message = "The time limit for the clan war is up. War with the $s1 clan is over.")
	public static SystemMessageId THE_TIME_LIMIT_FOR_THE_CLAN_WAR_IS_UP_WAR_WITH_THE_S1_CLAN_IS_OVER;
	
	@ClientString(id = 222, message = "$s1 has joined the clan.")
	public static SystemMessageId S1_HAS_JOINED_THE_CLAN;
	
	@ClientString(id = 223, message = "$s1 has withdrawn from the clan.")
	public static SystemMessageId S1_HAS_WITHDRAWN_FROM_THE_CLAN;
	
	@ClientString(id = 224, message = "$s1 did not respond: Invitation to the clan has been cancelled.")
	public static SystemMessageId S1_DID_NOT_RESPOND_INVITATION_TO_THE_CLAN_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 225, message = "You didn't respond to $s1's invitation: joining has been cancelled.")
	public static SystemMessageId YOU_DIDN_T_RESPOND_TO_S1_S_INVITATION_JOINING_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 226, message = "The $s1 clan did not respond: war proclamation has been refused.")
	public static SystemMessageId THE_S1_CLAN_DID_NOT_RESPOND_WAR_PROCLAMATION_HAS_BEEN_REFUSED;
	
	@ClientString(id = 227, message = "Clan war has been refused because you did not respond to $s1 clan's war proclamation.")
	public static SystemMessageId CLAN_WAR_HAS_BEEN_REFUSED_BECAUSE_YOU_DID_NOT_RESPOND_TO_S1_CLAN_S_WAR_PROCLAMATION;
	
	@ClientString(id = 228, message = "Request to end war has been denied.")
	public static SystemMessageId REQUEST_TO_END_WAR_HAS_BEEN_DENIED;
	
	@ClientString(id = 229, message = "You do not meet the criteria in order to create a clan.")
	public static SystemMessageId YOU_DO_NOT_MEET_THE_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN;
	
	@ClientString(id = 230, message = "You must wait 10 days before creating a new clan.")
	public static SystemMessageId YOU_MUST_WAIT_10_DAYS_BEFORE_CREATING_A_NEW_CLAN;
	
	@ClientString(id = 231, message = "After a clan member is dismissed from a clan, the clan must wait at least a day before accepting a new member.")
	public static SystemMessageId AFTER_A_CLAN_MEMBER_IS_DISMISSED_FROM_A_CLAN_THE_CLAN_MUST_WAIT_AT_LEAST_A_DAY_BEFORE_ACCEPTING_A_NEW_MEMBER;
	
	@ClientString(id = 232, message = "After leaving or having been dismissed from a clan, you must wait at least a day before joining another clan.")
	public static SystemMessageId AFTER_LEAVING_OR_HAVING_BEEN_DISMISSED_FROM_A_CLAN_YOU_MUST_WAIT_AT_LEAST_A_DAY_BEFORE_JOINING_ANOTHER_CLAN;
	
	@ClientString(id = 233, message = "The Academy/Royal Guard/Order of Knights is full and cannot accept new members at this time.")
	public static SystemMessageId THE_ACADEMY_ROYAL_GUARD_ORDER_OF_KNIGHTS_IS_FULL_AND_CANNOT_ACCEPT_NEW_MEMBERS_AT_THIS_TIME;
	
	@ClientString(id = 234, message = "The target must be a clan member.")
	public static SystemMessageId THE_TARGET_MUST_BE_A_CLAN_MEMBER;
	
	@ClientString(id = 235, message = "You are not authorized to bestow these rights.")
	public static SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_BESTOW_THESE_RIGHTS;
	
	@ClientString(id = 236, message = "Only the clan leader is enabled.")
	public static SystemMessageId ONLY_THE_CLAN_LEADER_IS_ENABLED;
	
	@ClientString(id = 237, message = "The clan leader could not be found.")
	public static SystemMessageId THE_CLAN_LEADER_COULD_NOT_BE_FOUND;
	
	@ClientString(id = 238, message = "Not joined in any clan.")
	public static SystemMessageId NOT_JOINED_IN_ANY_CLAN;
	
	@ClientString(id = 239, message = "A clan leader cannot withdraw from their own clan.")
	public static SystemMessageId A_CLAN_LEADER_CANNOT_WITHDRAW_FROM_THEIR_OWN_CLAN;
	
	@ClientString(id = 240, message = "You are currently involved in clan war.")
	public static SystemMessageId YOU_ARE_CURRENTLY_INVOLVED_IN_CLAN_WAR;
	
	@ClientString(id = 241, message = "Leader of the $s1 Clan is not logged in.")
	public static SystemMessageId LEADER_OF_THE_S1_CLAN_IS_NOT_LOGGED_IN;
	
	@ClientString(id = 242, message = "Select target.")
	public static SystemMessageId SELECT_TARGET;
	
	@ClientString(id = 243, message = "You cannot declare war on an allied clan.")
	public static SystemMessageId YOU_CANNOT_DECLARE_WAR_ON_AN_ALLIED_CLAN;
	
	@ClientString(id = 244, message = "You are not allowed to issue this challenge.")
	public static SystemMessageId YOU_ARE_NOT_ALLOWED_TO_ISSUE_THIS_CHALLENGE;
	
	@ClientString(id = 245, message = "It has not been 5 days since you refused a clan war. Do you wish to continue?")
	public static SystemMessageId IT_HAS_NOT_BEEN_5_DAYS_SINCE_YOU_REFUSED_A_CLAN_WAR_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 246, message = "That clan is currently at war.")
	public static SystemMessageId THAT_CLAN_IS_CURRENTLY_AT_WAR;
	
	@ClientString(id = 247, message = "You have already been at war with the $s1 clan: 5 days must pass before you can challenge this clan again.")
	public static SystemMessageId YOU_HAVE_ALREADY_BEEN_AT_WAR_WITH_THE_S1_CLAN_5_DAYS_MUST_PASS_BEFORE_YOU_CAN_CHALLENGE_THIS_CLAN_AGAIN;
	
	@ClientString(id = 248, message = "You cannot proclaim war: the $s1 clan does not have enough members.")
	public static SystemMessageId YOU_CANNOT_PROCLAIM_WAR_THE_S1_CLAN_DOES_NOT_HAVE_ENOUGH_MEMBERS;
	
	@ClientString(id = 249, message = "Do you wish to surrender to clan $s1?")
	public static SystemMessageId DO_YOU_WISH_TO_SURRENDER_TO_CLAN_S1;
	
	@ClientString(id = 250, message = "You have personally surrendered to the $s1 clan. You are no longer participating in this clan war.")
	public static SystemMessageId YOU_HAVE_PERSONALLY_SURRENDERED_TO_THE_S1_CLAN_YOU_ARE_NO_LONGER_PARTICIPATING_IN_THIS_CLAN_WAR;
	
	@ClientString(id = 251, message = "You cannot proclaim war: you are at war with another clan.")
	public static SystemMessageId YOU_CANNOT_PROCLAIM_WAR_YOU_ARE_AT_WAR_WITH_ANOTHER_CLAN;
	
	@ClientString(id = 252, message = "Enter the name of the clan you wish to surrender to.")
	public static SystemMessageId ENTER_THE_NAME_OF_THE_CLAN_YOU_WISH_TO_SURRENDER_TO;
	
	@ClientString(id = 253, message = "Enter the name of the clan you wish to end the war with.")
	public static SystemMessageId ENTER_THE_NAME_OF_THE_CLAN_YOU_WISH_TO_END_THE_WAR_WITH;
	
	@ClientString(id = 254, message = "A clan leader cannot personally surrender.")
	public static SystemMessageId A_CLAN_LEADER_CANNOT_PERSONALLY_SURRENDER;
	
	@ClientString(id = 255, message = "The $s1 Clan has requested to end war. Do you agree?")
	public static SystemMessageId THE_S1_CLAN_HAS_REQUESTED_TO_END_WAR_DO_YOU_AGREE;
	
	@ClientString(id = 256, message = "Enter Title")
	public static SystemMessageId ENTER_TITLE;
	
	@ClientString(id = 257, message = "Do you offer the $s1 clan a proposal to end the war?")
	public static SystemMessageId DO_YOU_OFFER_THE_S1_CLAN_A_PROPOSAL_TO_END_THE_WAR;
	
	@ClientString(id = 258, message = "You are not involved in a clan war.")
	public static SystemMessageId YOU_ARE_NOT_INVOLVED_IN_A_CLAN_WAR;
	
	@ClientString(id = 259, message = "Select clan members from list.")
	public static SystemMessageId SELECT_CLAN_MEMBERS_FROM_LIST;
	
	@ClientString(id = 260, message = "The clan reputation score was reduced because it hasn't been 5 days since refusing a clan war.")
	public static SystemMessageId THE_CLAN_REPUTATION_SCORE_WAS_REDUCED_BECAUSE_IT_HASN_T_BEEN_5_DAYS_SINCE_REFUSING_A_CLAN_WAR;
	
	@ClientString(id = 261, message = "Clan name is invalid.")
	public static SystemMessageId CLAN_NAME_IS_INVALID;
	
	@ClientString(id = 262, message = "Clan name's length is incorrect.")
	public static SystemMessageId CLAN_NAME_S_LENGTH_IS_INCORRECT;
	
	@ClientString(id = 263, message = "You have already requested the dissolution of your clan.")
	public static SystemMessageId YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN;
	
	@ClientString(id = 264, message = "You cannot dissolve a clan while engaged in a war.")
	public static SystemMessageId YOU_CANNOT_DISSOLVE_A_CLAN_WHILE_ENGAGED_IN_A_WAR;
	
	@ClientString(id = 265, message = "You cannot dissolve a clan during a siege or while protecting a castle.")
	public static SystemMessageId YOU_CANNOT_DISSOLVE_A_CLAN_DURING_A_SIEGE_OR_WHILE_PROTECTING_A_CASTLE;
	
	@ClientString(id = 266, message = "You cannot dissolve a clan while owning a clan hall or castle.")
	public static SystemMessageId YOU_CANNOT_DISSOLVE_A_CLAN_WHILE_OWNING_A_CLAN_HALL_OR_CASTLE;
	
	@ClientString(id = 267, message = "There are no requests to disperse.")
	public static SystemMessageId THERE_ARE_NO_REQUESTS_TO_DISPERSE;
	
	@ClientString(id = 268, message = "That player already belongs to another clan.")
	public static SystemMessageId THAT_PLAYER_ALREADY_BELONGS_TO_ANOTHER_CLAN;
	
	@ClientString(id = 269, message = "You cannot dismiss yourself.")
	public static SystemMessageId YOU_CANNOT_DISMISS_YOURSELF;
	
	@ClientString(id = 270, message = "You have already surrendered.")
	public static SystemMessageId YOU_HAVE_ALREADY_SURRENDERED;
	
	@ClientString(id = 271, message = "A player can only be granted a title if the clan is level 3 or above.")
	public static SystemMessageId A_PLAYER_CAN_ONLY_BE_GRANTED_A_TITLE_IF_THE_CLAN_IS_LEVEL_3_OR_ABOVE;
	
	@ClientString(id = 272, message = "A clan crest can only be registered when the clan's skill level is 3 or above.")
	public static SystemMessageId A_CLAN_CREST_CAN_ONLY_BE_REGISTERED_WHEN_THE_CLAN_S_SKILL_LEVEL_IS_3_OR_ABOVE;
	
	@ClientString(id = 273, message = "A clan war can only be declared when a clan's level is 3 or above.")
	public static SystemMessageId A_CLAN_WAR_CAN_ONLY_BE_DECLARED_WHEN_A_CLAN_S_LEVEL_IS_3_OR_ABOVE;
	
	@ClientString(id = 274, message = "Your clan's level has increased.")
	public static SystemMessageId YOUR_CLAN_S_LEVEL_HAS_INCREASED;
	
	@ClientString(id = 275, message = "The clan has failed to increase its level.")
	public static SystemMessageId THE_CLAN_HAS_FAILED_TO_INCREASE_ITS_LEVEL;
	
	@ClientString(id = 276, message = "You do not have the necessary materials or prerequisites to learn this skill.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_NECESSARY_MATERIALS_OR_PREREQUISITES_TO_LEARN_THIS_SKILL;
	
	@ClientString(id = 277, message = "You have earned $s1.")
	public static SystemMessageId YOU_HAVE_EARNED_S1_2;
	
	@ClientString(id = 278, message = "You do not have enough SP to learn this skill.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_SP_TO_LEARN_THIS_SKILL;
	
	@ClientString(id = 279, message = "You do not have enough adena.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_ADENA;
	
	@ClientString(id = 280, message = "You do not have any items to sell.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ANY_ITEMS_TO_SELL;
	
	@ClientString(id = 281, message = "You do not have enough adena to pay the fee.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_ADENA_TO_PAY_THE_FEE;
	
	@ClientString(id = 282, message = "You have not deposited any items in your warehouse.")
	public static SystemMessageId YOU_HAVE_NOT_DEPOSITED_ANY_ITEMS_IN_YOUR_WAREHOUSE;
	
	@ClientString(id = 283, message = "You have entered a combat zone.")
	public static SystemMessageId YOU_HAVE_ENTERED_A_COMBAT_ZONE;
	
	@ClientString(id = 284, message = "You have left a combat zone.")
	public static SystemMessageId YOU_HAVE_LEFT_A_COMBAT_ZONE;
	
	@ClientString(id = 285, message = "Clan $s1 has successfully engraved the holy artifact!")
	public static SystemMessageId CLAN_S1_HAS_SUCCESSFULLY_ENGRAVED_THE_HOLY_ARTIFACT;
	
	@ClientString(id = 286, message = "Your base is being attacked.")
	public static SystemMessageId YOUR_BASE_IS_BEING_ATTACKED;
	
	@ClientString(id = 287, message = "The opposing clan has started to engrave the holy artifact!")
	public static SystemMessageId THE_OPPOSING_CLAN_HAS_STARTED_TO_ENGRAVE_THE_HOLY_ARTIFACT;
	
	@ClientString(id = 288, message = "The castle gate has been destroyed.")
	public static SystemMessageId THE_CASTLE_GATE_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 289, message = "An outpost or headquarters cannot be built because one already exists.")
	public static SystemMessageId AN_OUTPOST_OR_HEADQUARTERS_CANNOT_BE_BUILT_BECAUSE_ONE_ALREADY_EXISTS;
	
	@ClientString(id = 290, message = "You cannot set up a base here.")
	public static SystemMessageId YOU_CANNOT_SET_UP_A_BASE_HERE;
	
	@ClientString(id = 291, message = "Clan $s1 is victorious over $s2's castle siege!")
	public static SystemMessageId CLAN_S1_IS_VICTORIOUS_OVER_S2_S_CASTLE_SIEGE;
	
	@ClientString(id = 292, message = "$s1 has announced the next castle siege time.")
	public static SystemMessageId S1_HAS_ANNOUNCED_THE_NEXT_CASTLE_SIEGE_TIME;
	
	@ClientString(id = 293, message = "The registration term for $s1 has ended.")
	public static SystemMessageId THE_REGISTRATION_TERM_FOR_S1_HAS_ENDED;
	
	@ClientString(id = 294, message = "You cannot summon the encampment because you are not a member of the siege clan involved in the castle / fortress / hideout siege.")
	public static SystemMessageId YOU_CANNOT_SUMMON_THE_ENCAMPMENT_BECAUSE_YOU_ARE_NOT_A_MEMBER_OF_THE_SIEGE_CLAN_INVOLVED_IN_THE_CASTLE_FORTRESS_HIDEOUT_SIEGE;
	
	@ClientString(id = 295, message = "$s1's siege was canceled because there were no clans that participated.")
	public static SystemMessageId S1_S_SIEGE_WAS_CANCELED_BECAUSE_THERE_WERE_NO_CLANS_THAT_PARTICIPATED;
	
	@ClientString(id = 296, message = "You received $s1 falling damage.")
	public static SystemMessageId YOU_RECEIVED_S1_FALLING_DAMAGE;
	
	@ClientString(id = 297, message = "You have taken $s1 damage because you were unable to breathe.")
	public static SystemMessageId YOU_HAVE_TAKEN_S1_DAMAGE_BECAUSE_YOU_WERE_UNABLE_TO_BREATHE;
	
	@ClientString(id = 298, message = "You have dropped $s1.")
	public static SystemMessageId YOU_HAVE_DROPPED_S1;
	
	@ClientString(id = 299, message = "$c1 has obtained $s3 $s2.")
	public static SystemMessageId C1_HAS_OBTAINED_S3_S2;
	
	@ClientString(id = 300, message = "$c1 has obtained $s2.")
	public static SystemMessageId C1_HAS_OBTAINED_S2;
	
	@ClientString(id = 301, message = "$s2 $s1 has disappeared.")
	public static SystemMessageId S2_S1_HAS_DISAPPEARED;
	
	@ClientString(id = 302, message = "$s1 has disappeared.")
	public static SystemMessageId S1_HAS_DISAPPEARED;
	
	@ClientString(id = 303, message = "Select item to enchant.")
	public static SystemMessageId SELECT_ITEM_TO_ENCHANT;
	
	@ClientString(id = 304, message = "Clan member $s1 has logged into game.")
	public static SystemMessageId CLAN_MEMBER_S1_HAS_LOGGED_INTO_GAME;
	
	@ClientString(id = 305, message = "The player declined to join your party.")
	public static SystemMessageId THE_PLAYER_DECLINED_TO_JOIN_YOUR_PARTY;
	
	@ClientString(id = 306, message = "You have failed to delete the character.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_DELETE_THE_CHARACTER;
	
	@ClientString(id = 307, message = "You cannot trade with a warehouse keeper.")
	public static SystemMessageId YOU_CANNOT_TRADE_WITH_A_WAREHOUSE_KEEPER;
	
	@ClientString(id = 308, message = "The player declined your clan invitation.")
	public static SystemMessageId THE_PLAYER_DECLINED_YOUR_CLAN_INVITATION;
	
	@ClientString(id = 309, message = "You have succeeded in expelling the clan member.")
	public static SystemMessageId YOU_HAVE_SUCCEEDED_IN_EXPELLING_THE_CLAN_MEMBER;
	
	@ClientString(id = 310, message = "You have failed to expel the clan member.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_EXPEL_THE_CLAN_MEMBER;
	
	@ClientString(id = 311, message = "The clan war declaration has been accepted.")
	public static SystemMessageId THE_CLAN_WAR_DECLARATION_HAS_BEEN_ACCEPTED;
	
	@ClientString(id = 312, message = "The clan war declaration has been refused.")
	public static SystemMessageId THE_CLAN_WAR_DECLARATION_HAS_BEEN_REFUSED;
	
	@ClientString(id = 313, message = "The cease war request has been accepted.")
	public static SystemMessageId THE_CEASE_WAR_REQUEST_HAS_BEEN_ACCEPTED;
	
	@ClientString(id = 314, message = "You have failed to surrender.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_SURRENDER;
	
	@ClientString(id = 315, message = "You have failed to personally surrender.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_PERSONALLY_SURRENDER;
	
	@ClientString(id = 316, message = "You have failed to withdraw from the party.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_WITHDRAW_FROM_THE_PARTY;
	
	@ClientString(id = 317, message = "You have failed to expel the party member.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_EXPEL_THE_PARTY_MEMBER;
	
	@ClientString(id = 318, message = "You have failed to disperse the party.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_DISPERSE_THE_PARTY;
	
	@ClientString(id = 319, message = "This door cannot be unlocked.")
	public static SystemMessageId THIS_DOOR_CANNOT_BE_UNLOCKED;
	
	@ClientString(id = 320, message = "You have failed to unlock the door.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_UNLOCK_THE_DOOR;
	
	@ClientString(id = 321, message = "It is not locked.")
	public static SystemMessageId IT_IS_NOT_LOCKED;
	
	@ClientString(id = 322, message = "Please decide on the sales price.")
	public static SystemMessageId PLEASE_DECIDE_ON_THE_SALES_PRICE;
	
	@ClientString(id = 323, message = "Your force has increased to level $s1.")
	public static SystemMessageId YOUR_FORCE_HAS_INCREASED_TO_LEVEL_S1;
	
	@ClientString(id = 324, message = "Your force has reached maximum capacity.")
	public static SystemMessageId YOUR_FORCE_HAS_REACHED_MAXIMUM_CAPACITY;
	
	@ClientString(id = 325, message = "The corpse has already disappeared.")
	public static SystemMessageId THE_CORPSE_HAS_ALREADY_DISAPPEARED;
	
	@ClientString(id = 326, message = "Select target from list.")
	public static SystemMessageId SELECT_TARGET_FROM_LIST;
	
	@ClientString(id = 327, message = "You cannot exceed 80 characters.")
	public static SystemMessageId YOU_CANNOT_EXCEED_80_CHARACTERS;
	
	@ClientString(id = 328, message = "Please input title using less than 128 characters.")
	public static SystemMessageId PLEASE_INPUT_TITLE_USING_LESS_THAN_128_CHARACTERS;
	
	@ClientString(id = 329, message = "Please input contents using less than 3000 characters.")
	public static SystemMessageId PLEASE_INPUT_CONTENTS_USING_LESS_THAN_3000_CHARACTERS;
	
	@ClientString(id = 330, message = "A one-line response may not exceed 128 characters.")
	public static SystemMessageId A_ONE_LINE_RESPONSE_MAY_NOT_EXCEED_128_CHARACTERS;
	
	@ClientString(id = 331, message = "You have acquired $s1 SP.")
	public static SystemMessageId YOU_HAVE_ACQUIRED_S1_SP;
	
	@ClientString(id = 332, message = "Do you want to be restored?")
	public static SystemMessageId DO_YOU_WANT_TO_BE_RESTORED;
	
	@ClientString(id = 333, message = "You have received $s1 damage by Core's barrier.")
	public static SystemMessageId YOU_HAVE_RECEIVED_S1_DAMAGE_BY_CORE_S_BARRIER;
	
	@ClientString(id = 334, message = "Please enter your private store display message.")
	public static SystemMessageId PLEASE_ENTER_YOUR_PRIVATE_STORE_DISPLAY_MESSAGE;
	
	@ClientString(id = 335, message = "$s1 has been aborted.")
	public static SystemMessageId S1_HAS_BEEN_ABORTED;
	
	@ClientString(id = 336, message = "You are attempting to crystallize $s1. Do you wish to continue?")
	public static SystemMessageId YOU_ARE_ATTEMPTING_TO_CRYSTALLIZE_S1_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 337, message = "The soulshot you are attempting to use does not match the grade of your equipped weapon.")
	public static SystemMessageId THE_SOULSHOT_YOU_ARE_ATTEMPTING_TO_USE_DOES_NOT_MATCH_THE_GRADE_OF_YOUR_EQUIPPED_WEAPON;
	
	@ClientString(id = 338, message = "You do not have enough soulshots for that.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_SOULSHOTS_FOR_THAT;
	
	@ClientString(id = 339, message = "Cannot use soulshots.")
	public static SystemMessageId CANNOT_USE_SOULSHOTS;
	
	@ClientString(id = 340, message = "Your private store is now open for business.")
	public static SystemMessageId YOUR_PRIVATE_STORE_IS_NOW_OPEN_FOR_BUSINESS;
	
	@ClientString(id = 341, message = "You do not have enough materials to perform that action.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_MATERIALS_TO_PERFORM_THAT_ACTION;
	
	@ClientString(id = 342, message = "Your soulshots are enabled.")
	public static SystemMessageId YOUR_SOULSHOTS_ARE_ENABLED;
	
	@ClientString(id = 343, message = "Sweeper failed, target not spoiled.")
	public static SystemMessageId SWEEPER_FAILED_TARGET_NOT_SPOILED;
	
	@ClientString(id = 344, message = "Your soulshots are disabled.")
	public static SystemMessageId YOUR_SOULSHOTS_ARE_DISABLED;
	
	@ClientString(id = 345, message = "Chat enabled.")
	public static SystemMessageId CHAT_ENABLED;
	
	@ClientString(id = 346, message = "Chat disabled.")
	public static SystemMessageId CHAT_DISABLED;
	
	@ClientString(id = 347, message = "Incorrect item count.")
	public static SystemMessageId INCORRECT_ITEM_COUNT;
	
	@ClientString(id = 348, message = "Incorrect item price.")
	public static SystemMessageId INCORRECT_ITEM_PRICE;
	
	@ClientString(id = 349, message = "Private store already closed.")
	public static SystemMessageId PRIVATE_STORE_ALREADY_CLOSED;
	
	@ClientString(id = 350, message = "Item out of stock.")
	public static SystemMessageId ITEM_OUT_OF_STOCK;
	
	@ClientString(id = 351, message = "Incorrect item count.")
	public static SystemMessageId INCORRECT_ITEM_COUNT_2;
	
	@ClientString(id = 352, message = "Incorrect item.")
	public static SystemMessageId INCORRECT_ITEM;
	
	@ClientString(id = 353, message = "Cannot purchase.")
	public static SystemMessageId CANNOT_PURCHASE;
	
	@ClientString(id = 354, message = "Cancel enchant.")
	public static SystemMessageId CANCEL_ENCHANT;
	
	@ClientString(id = 355, message = "Inappropriate enchant conditions.")
	public static SystemMessageId INAPPROPRIATE_ENCHANT_CONDITIONS;
	
	@ClientString(id = 356, message = "Reject resurrection.")
	public static SystemMessageId REJECT_RESURRECTION;
	
	@ClientString(id = 357, message = "It has already been spoiled.")
	public static SystemMessageId IT_HAS_ALREADY_BEEN_SPOILED;
	
	@ClientString(id = 358, message = "$s1 hour(s) until castle siege conclusion.")
	public static SystemMessageId S1_HOUR_S_UNTIL_CASTLE_SIEGE_CONCLUSION;
	
	@ClientString(id = 359, message = "$s1 minute(s) until castle siege conclusion.")
	public static SystemMessageId S1_MINUTE_S_UNTIL_CASTLE_SIEGE_CONCLUSION;
	
	@ClientString(id = 360, message = "This castle siege will end in $s1 second(s)!")
	public static SystemMessageId THIS_CASTLE_SIEGE_WILL_END_IN_S1_SECOND_S;
	
	@ClientString(id = 361, message = "Over-hit!")
	public static SystemMessageId OVER_HIT;
	
	@ClientString(id = 362, message = "You have acquired $s1 bonus experience from a successful over-hit.")
	public static SystemMessageId YOU_HAVE_ACQUIRED_S1_BONUS_EXPERIENCE_FROM_A_SUCCESSFUL_OVER_HIT;
	
	@ClientString(id = 363, message = "Chat available time: $s1 minute.")
	public static SystemMessageId CHAT_AVAILABLE_TIME_S1_MINUTE;
	
	@ClientString(id = 364, message = "Enter user's name to search.")
	public static SystemMessageId ENTER_USER_S_NAME_TO_SEARCH;
	
	@ClientString(id = 365, message = "Are you sure?")
	public static SystemMessageId ARE_YOU_SURE;
	
	@ClientString(id = 366, message = "Please select your hair color.")
	public static SystemMessageId PLEASE_SELECT_YOUR_HAIR_COLOR;
	
	@ClientString(id = 367, message = "You cannot remove that clan character at this time.")
	public static SystemMessageId YOU_CANNOT_REMOVE_THAT_CLAN_CHARACTER_AT_THIS_TIME;
	
	@ClientString(id = 368, message = "Equipped +$s1 $s2.")
	public static SystemMessageId EQUIPPED_S1_S2;
	
	@ClientString(id = 369, message = "You have obtained a +$s1 $s2.")
	public static SystemMessageId YOU_HAVE_OBTAINED_A_S1_S2;
	
	@ClientString(id = 370, message = "Failed to pick up $s1.")
	public static SystemMessageId FAILED_TO_PICK_UP_S1;
	
	@ClientString(id = 371, message = "Acquired +$s1 $s2.")
	public static SystemMessageId ACQUIRED_S1_S2;
	
	@ClientString(id = 372, message = "Failed to earn $s1.")
	public static SystemMessageId FAILED_TO_EARN_S1;
	
	@ClientString(id = 373, message = "You are trying to destroy +$s1 $s2. Do you wish to continue?")
	public static SystemMessageId YOU_ARE_TRYING_TO_DESTROY_S1_S2_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 374, message = "You are attempting to crystallize +$s1 $s2. Do you wish to continue?")
	public static SystemMessageId YOU_ARE_ATTEMPTING_TO_CRYSTALLIZE_S1_S2_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 375, message = "You have dropped +$s1 $s2.")
	public static SystemMessageId YOU_HAVE_DROPPED_S1_S2;
	
	@ClientString(id = 376, message = "$c1 has obtained +$s2$s3.")
	public static SystemMessageId C1_HAS_OBTAINED_S2_S3;
	
	@ClientString(id = 377, message = "$S1 $S2 disappeared.")
	public static SystemMessageId S1_S2_DISAPPEARED;
	
	@ClientString(id = 378, message = "$c1 purchased $s2.")
	public static SystemMessageId C1_PURCHASED_S2;
	
	@ClientString(id = 379, message = "$c1 purchased +$s2$s3.")
	public static SystemMessageId C1_PURCHASED_S2_S3;
	
	@ClientString(id = 380, message = "$c1 purchased $s3 $s2(s).")
	public static SystemMessageId C1_PURCHASED_S3_S2_S;
	
	@ClientString(id = 381, message = "The game client encountered an error and was unable to connect to the petition server.")
	public static SystemMessageId THE_GAME_CLIENT_ENCOUNTERED_AN_ERROR_AND_WAS_UNABLE_TO_CONNECT_TO_THE_PETITION_SERVER;
	
	@ClientString(id = 382, message = "Currently there are no users that have checked out a GM ID.")
	public static SystemMessageId CURRENTLY_THERE_ARE_NO_USERS_THAT_HAVE_CHECKED_OUT_A_GM_ID;
	
	@ClientString(id = 383, message = "Request confirmed to end consultation at petition server.")
	public static SystemMessageId REQUEST_CONFIRMED_TO_END_CONSULTATION_AT_PETITION_SERVER;
	
	@ClientString(id = 384, message = "The client is not logged onto the game server.")
	public static SystemMessageId THE_CLIENT_IS_NOT_LOGGED_ONTO_THE_GAME_SERVER;
	
	@ClientString(id = 385, message = "Request confirmed to begin consultation at petition server.")
	public static SystemMessageId REQUEST_CONFIRMED_TO_BEGIN_CONSULTATION_AT_PETITION_SERVER;
	
	@ClientString(id = 386, message = "The body of your petition must be more than five characters in length.")
	public static SystemMessageId THE_BODY_OF_YOUR_PETITION_MUST_BE_MORE_THAN_FIVE_CHARACTERS_IN_LENGTH;
	
	@ClientString(id = 387, message = "This ends the GM petition consultation. \\nPlease give us feedback on the petition service.")
	public static SystemMessageId THIS_ENDS_THE_GM_PETITION_CONSULTATION_NPLEASE_GIVE_US_FEEDBACK_ON_THE_PETITION_SERVICE;
	
	@ClientString(id = 388, message = "Not under petition consultation.")
	public static SystemMessageId NOT_UNDER_PETITION_CONSULTATION;
	
	@ClientString(id = 389, message = "Your petition application has been accepted. \\n - Receipt No. is $s1.")
	public static SystemMessageId YOUR_PETITION_APPLICATION_HAS_BEEN_ACCEPTED_N_RECEIPT_NO_IS_S1;
	
	@ClientString(id = 390, message = "You may only submit one petition (active) at a time.")
	public static SystemMessageId YOU_MAY_ONLY_SUBMIT_ONE_PETITION_ACTIVE_AT_A_TIME;
	
	@ClientString(id = 391, message = "Receipt No. $s1: petition cancelled.")
	public static SystemMessageId RECEIPT_NO_S1_PETITION_CANCELLED;
	
	@ClientString(id = 392, message = "Petition underway.")
	public static SystemMessageId PETITION_UNDERWAY;
	
	@ClientString(id = 393, message = "Failed to cancel petition. Please try again later.")
	public static SystemMessageId FAILED_TO_CANCEL_PETITION_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 394, message = "Starting petition consultation with $c1.")
	public static SystemMessageId STARTING_PETITION_CONSULTATION_WITH_C1;
	
	@ClientString(id = 395, message = "Ending petition consultation with $c1.")
	public static SystemMessageId ENDING_PETITION_CONSULTATION_WITH_C1;
	
	@ClientString(id = 396, message = "Please login after changing your temporary password.")
	public static SystemMessageId PLEASE_LOGIN_AFTER_CHANGING_YOUR_TEMPORARY_PASSWORD;
	
	@ClientString(id = 397, message = "This is not a paid account.")
	public static SystemMessageId THIS_IS_NOT_A_PAID_ACCOUNT;
	
	@ClientString(id = 398, message = "There is no time left on this account.")
	public static SystemMessageId THERE_IS_NO_TIME_LEFT_ON_THIS_ACCOUNT;
	
	@ClientString(id = 399, message = "System error.")
	public static SystemMessageId SYSTEM_ERROR;
	
	@ClientString(id = 400, message = "You are attempting to drop $s1. Do you wish to continue?")
	public static SystemMessageId YOU_ARE_ATTEMPTING_TO_DROP_S1_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 401, message = "You have too many ongoing quests.")
	public static SystemMessageId YOU_HAVE_TOO_MANY_ONGOING_QUESTS;
	
	@ClientString(id = 402, message = "You do not possess the correct ticket to board the boat.")
	public static SystemMessageId YOU_DO_NOT_POSSESS_THE_CORRECT_TICKET_TO_BOARD_THE_BOAT;
	
	@ClientString(id = 403, message = "You have exceeded your out-of-pocket adena limit.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_YOUR_OUT_OF_POCKET_ADENA_LIMIT;
	
	@ClientString(id = 404, message = "Your Create Item level is too low to register this recipe.")
	public static SystemMessageId YOUR_CREATE_ITEM_LEVEL_IS_TOO_LOW_TO_REGISTER_THIS_RECIPE;
	
	@ClientString(id = 405, message = "The total price of the product is too high.")
	public static SystemMessageId THE_TOTAL_PRICE_OF_THE_PRODUCT_IS_TOO_HIGH;
	
	@ClientString(id = 406, message = "Petition application accepted.")
	public static SystemMessageId PETITION_APPLICATION_ACCEPTED;
	
	@ClientString(id = 407, message = "Your petition is being processed.")
	public static SystemMessageId YOUR_PETITION_IS_BEING_PROCESSED;
	
	@ClientString(id = 408, message = "Set Period")
	public static SystemMessageId SET_PERIOD;
	
	@ClientString(id = 409, message = "Set Time-$s1: $s2: $s3")
	public static SystemMessageId SET_TIME_S1_S2_S3;
	
	@ClientString(id = 410, message = "Registration Period")
	public static SystemMessageId REGISTRATION_PERIOD;
	
	@ClientString(id = 411, message = "Registration TIme-$s1: $s2: $s3")
	public static SystemMessageId REGISTRATION_TIME_S1_S2_S3;
	
	@ClientString(id = 412, message = "Battle begins in $s1: $s2: $s4")
	public static SystemMessageId BATTLE_BEGINS_IN_S1_S2_S4;
	
	@ClientString(id = 413, message = "Battle ends in $s1: $s2: $s5")
	public static SystemMessageId BATTLE_ENDS_IN_S1_S2_S5;
	
	@ClientString(id = 414, message = "Standby")
	public static SystemMessageId STANDBY;
	
	@ClientString(id = 415, message = "Siege is underway")
	public static SystemMessageId SIEGE_IS_UNDERWAY;
	
	@ClientString(id = 416, message = "This item cannot be exchanged.")
	public static SystemMessageId THIS_ITEM_CANNOT_BE_EXCHANGED;
	
	@ClientString(id = 417, message = "$s1 has been disarmed.")
	public static SystemMessageId S1_HAS_BEEN_DISARMED;
	
	@ClientString(id = 418, message = "There is a significant difference between the item's price and its standard price. Please check again.")
	public static SystemMessageId THERE_IS_A_SIGNIFICANT_DIFFERENCE_BETWEEN_THE_ITEM_S_PRICE_AND_ITS_STANDARD_PRICE_PLEASE_CHECK_AGAIN;
	
	@ClientString(id = 419, message = "$s1 minute(s) of usage time left.")
	public static SystemMessageId S1_MINUTE_S_OF_USAGE_TIME_LEFT;
	
	@ClientString(id = 420, message = "Time expired.")
	public static SystemMessageId TIME_EXPIRED;
	
	@ClientString(id = 421, message = "Another person has logged in with the same account.")
	public static SystemMessageId ANOTHER_PERSON_HAS_LOGGED_IN_WITH_THE_SAME_ACCOUNT;
	
	@ClientString(id = 422, message = "You have exceeded the weight limit.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_THE_WEIGHT_LIMIT;
	
	@ClientString(id = 423, message = "You have cancelled the enchanting process.")
	public static SystemMessageId YOU_HAVE_CANCELLED_THE_ENCHANTING_PROCESS;
	
	@ClientString(id = 424, message = "Does not fit strengthening conditions of the scroll.")
	public static SystemMessageId DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL;
	
	@ClientString(id = 425, message = "Your Create Item level is too low to register this recipe.")
	public static SystemMessageId YOUR_CREATE_ITEM_LEVEL_IS_TOO_LOW_TO_REGISTER_THIS_RECIPE_2;
	
	@ClientString(id = 426, message = "Your account has been reported for intentionally not paying the cyber café fees.")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_REPORTED_FOR_INTENTIONALLY_NOT_PAYING_THE_CYBER_CAF_FEES;
	
	@ClientString(id = 427, message = "Please contact us.")
	public static SystemMessageId PLEASE_CONTACT_US;
	
	@ClientString(id = 428, message = "Use of your account has been limited due to alleged account theft. If you have nothing to do with account theft, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId USE_OF_YOUR_ACCOUNT_HAS_BEEN_LIMITED_DUE_TO_ALLEGED_ACCOUNT_THEFT_IF_YOU_HAVE_NOTHING_TO_DO_WITH_ACCOUNT_THEFT_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 429, message = "In accordance with company policy, your account has been suspended for submitting a false report. Submitting a false report to the Support Center may harm other players. For more information on account suspension, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId IN_ACCORDANCE_WITH_COMPANY_POLICY_YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_SUBMITTING_A_FALSE_REPORT_SUBMITTING_A_FALSE_REPORT_TO_THE_SUPPORT_CENTER_MAY_HARM_OTHER_PLAYERS_FOR_MORE_INFORMATION_ON_ACCOUNT_SUSPENSION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 430, message = "번역불필요 (Doesn't need to translate.)")
	public static SystemMessageId DOESN_T_NEED_TO_TRANSLATE;
	
	@ClientString(id = 431, message = "Your account has been suspended for violating the EULA, RoC and/or User Agreement. When a user violates the terms of the User Agreement, the company can impose a restriction on the applicable user's account. For more information on account suspension, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_VIOLATING_THE_EULA_ROC_AND_OR_USER_AGREEMENT_WHEN_A_USER_VIOLATES_THE_TERMS_OF_THE_USER_AGREEMENT_THE_COMPANY_CAN_IMPOSE_A_RESTRICTION_ON_THE_APPLICABLE_USER_S_ACCOUNT_FOR_MORE_INFORMATION_ON_ACCOUNT_SUSPENSION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 432, message = "Your account has been suspended for 7 days (retroactive to the day of disclosure), under Chapter 3, Section 14 of the Lineage II Service Use Agreement, for dealing or attempting to deal items or characters (accounts) within the game in exchange for cash or items of other games. Suspension of your account will automatically expire after 7 days. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_7_DAYS_RETROACTIVE_TO_THE_DAY_OF_DISCLOSURE_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE_AGREEMENT_FOR_DEALING_OR_ATTEMPTING_TO_DEAL_ITEMS_OR_CHARACTERS_ACCOUNTS_WITHIN_THE_GAME_IN_EXCHANGE_FOR_CASH_OR_ITEMS_OF_OTHER_GAMES_SUSPENSION_OF_YOUR_ACCOUNT_WILL_AUTOMATICALLY_EXPIRE_AFTER_7_DAYS_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 433, message = "Your account has been suspended, under Chapter 3, Section 14 of the Lineage II Service Use Agreement, for dealing or attempting to deal items or characters (accounts) within the game in exchange for cash or items of other games. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE_AGREEMENT_FOR_DEALING_OR_ATTEMPTING_TO_DEAL_ITEMS_OR_CHARACTERS_ACCOUNTS_WITHIN_THE_GAME_IN_EXCHANGE_FOR_CASH_OR_ITEMS_OF_OTHER_GAMES_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 434, message = "Your account has been suspended, under Chapter 3, Section 14 of the Lineage II Service Use Agreement, for unethical behavior or fraud. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE_AGREEMENT_FOR_UNETHICAL_BEHAVIOR_OR_FRAUD_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 435, message = "Your account has been suspended for unethical behavior. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_UNETHICAL_BEHAVIOR_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 436, message = "Your account has been suspended for abusing the game system or exploiting bug(s). Abusing bug(s) may cause critical situations as well as harm the game world's balance. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_ABUSING_THE_GAME_SYSTEM_OR_EXPLOITING_BUG_S_ABUSING_BUG_S_MAY_CAUSE_CRITICAL_SITUATIONS_AS_WELL_AS_HARM_THE_GAME_WORLD_S_BALANCE_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 437, message = "Your account has been suspended for using illegal software which has not been authorized by our company. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_USING_ILLEGAL_SOFTWARE_WHICH_HAS_NOT_BEEN_AUTHORIZED_BY_OUR_COMPANY_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 438, message = "Your account has been suspended for impersonating an official Game Master or staff member. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_IMPERSONATING_AN_OFFICIAL_GAME_MASTER_OR_STAFF_MEMBER_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 439, message = "In accordance with the company's User Agreement and Operational Policy this account has been suspended at the account holder's request. If you have any questions regarding your account please contact support at http://us.ncsoft.com/support.")
	public static SystemMessageId IN_ACCORDANCE_WITH_THE_COMPANY_S_USER_AGREEMENT_AND_OPERATIONAL_POLICY_THIS_ACCOUNT_HAS_BEEN_SUSPENDED_AT_THE_ACCOUNT_HOLDER_S_REQUEST_IF_YOU_HAVE_ANY_QUESTIONS_REGARDING_YOUR_ACCOUNT_PLEASE_CONTACT_SUPPORT_AT_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 440, message = "Because you are registered as a minor, your account has been suspended at the request of your parents or guardian. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId BECAUSE_YOU_ARE_REGISTERED_AS_A_MINOR_YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_AT_THE_REQUEST_OF_YOUR_PARENTS_OR_GUARDIAN_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 441, message = "Per our company's User Agreement, the use of this account has been suspended. If you have any questions regarding your account please contact support at http://us.ncsoft.com/support.")
	public static SystemMessageId PER_OUR_COMPANY_S_USER_AGREEMENT_THE_USE_OF_THIS_ACCOUNT_HAS_BEEN_SUSPENDED_IF_YOU_HAVE_ANY_QUESTIONS_REGARDING_YOUR_ACCOUNT_PLEASE_CONTACT_SUPPORT_AT_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 442, message = "Your account has been suspended, under Chapter 2, Section 7 of the Lineage II Service Use Agreement, for misappropriating payment under another player's account. For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_2_SECTION_7_OF_THE_LINEAGE_II_SERVICE_USE_AGREEMENT_FOR_MISAPPROPRIATING_PAYMENT_UNDER_ANOTHER_PLAYER_S_ACCOUNT_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 443, message = "Ownership of this account needs to be verified. For more information, Please submit a ticket at the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId OWNERSHIP_OF_THIS_ACCOUNT_NEEDS_TO_BE_VERIFIED_FOR_MORE_INFORMATION_PLEASE_SUBMIT_A_TICKET_AT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 444, message = "Since we have received a withdrawal request from the holder of this account access to all applicable accounts has been automatically suspended.")
	public static SystemMessageId SINCE_WE_HAVE_RECEIVED_A_WITHDRAWAL_REQUEST_FROM_THE_HOLDER_OF_THIS_ACCOUNT_ACCESS_TO_ALL_APPLICABLE_ACCOUNTS_HAS_BEEN_AUTOMATICALLY_SUSPENDED;
	
	@ClientString(id = 445, message = "(Reference Number Regarding Membership Withdrawal Request: $s1)")
	public static SystemMessageId REFERENCE_NUMBER_REGARDING_MEMBERSHIP_WITHDRAWAL_REQUEST_S1;
	
	@ClientString(id = 446, message = "For more information, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 447, message = ".")
	public static SystemMessageId EMPTY;
	
	@ClientString(id = 448, message = "There is a system error. Please log in again later.")
	public static SystemMessageId THERE_IS_A_SYSTEM_ERROR_PLEASE_LOG_IN_AGAIN_LATER;
	
	@ClientString(id = 449, message = "The password you have entered is incorrect.")
	public static SystemMessageId THE_PASSWORD_YOU_HAVE_ENTERED_IS_INCORRECT;
	
	@ClientString(id = 450, message = "Confirm your account information and log in again later.")
	public static SystemMessageId CONFIRM_YOUR_ACCOUNT_INFORMATION_AND_LOG_IN_AGAIN_LATER;
	
	@ClientString(id = 451, message = "The password you have entered is incorrect.")
	public static SystemMessageId THE_PASSWORD_YOU_HAVE_ENTERED_IS_INCORRECT_2;
	
	@ClientString(id = 452, message = "Please confirm your account information and try logging in again.")
	public static SystemMessageId PLEASE_CONFIRM_YOUR_ACCOUNT_INFORMATION_AND_TRY_LOGGING_IN_AGAIN;
	
	@ClientString(id = 453, message = "Your account information is incorrect.")
	public static SystemMessageId YOUR_ACCOUNT_INFORMATION_IS_INCORRECT;
	
	@ClientString(id = 454, message = "For more details, please contact our customer service center at http://us.ncsoft.com/support.")
	public static SystemMessageId FOR_MORE_DETAILS_PLEASE_CONTACT_OUR_CUSTOMER_SERVICE_CENTER_AT_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 455, message = "Account is already in use. Unable to log in.")
	public static SystemMessageId ACCOUNT_IS_ALREADY_IN_USE_UNABLE_TO_LOG_IN;
	
	@ClientString(id = 456, message = "Lineage II game services may be used by individuals 15 years of age or older except for PvP servers, which may only be used by adults 18 years of age and older. (Korea Only)")
	public static SystemMessageId LINEAGE_II_GAME_SERVICES_MAY_BE_USED_BY_INDIVIDUALS_15_YEARS_OF_AGE_OR_OLDER_EXCEPT_FOR_PVP_SERVERS_WHICH_MAY_ONLY_BE_USED_BY_ADULTS_18_YEARS_OF_AGE_AND_OLDER_KOREA_ONLY;
	
	@ClientString(id = 457, message = "We are currently undergoing game server maintenance. Please log in again later.")
	public static SystemMessageId WE_ARE_CURRENTLY_UNDERGOING_GAME_SERVER_MAINTENANCE_PLEASE_LOG_IN_AGAIN_LATER;
	
	@ClientString(id = 458, message = "Your game time has expired. You can not login.")
	public static SystemMessageId YOUR_GAME_TIME_HAS_EXPIRED_YOU_CAN_NOT_LOGIN;
	
	@ClientString(id = 459, message = "To continue playing, please purchase Lineage II")
	public static SystemMessageId TO_CONTINUE_PLAYING_PLEASE_PURCHASE_LINEAGE_II;
	
	@ClientString(id = 460, message = "either directly from the PlayNC Store or from any leading games retailer.")
	public static SystemMessageId EITHER_DIRECTLY_FROM_THE_PLAYNC_STORE_OR_FROM_ANY_LEADING_GAMES_RETAILER;
	
	@ClientString(id = 461, message = "Access failed.")
	public static SystemMessageId ACCESS_FAILED;
	
	@ClientString(id = 462, message = "Please try again later.")
	public static SystemMessageId PLEASE_TRY_AGAIN_LATER_2;
	
	@ClientString(id = 463, message = ".")
	public static SystemMessageId EMPTY_2;
	
	@ClientString(id = 464, message = "This feature is only available to alliance leaders.")
	public static SystemMessageId THIS_FEATURE_IS_ONLY_AVAILABLE_TO_ALLIANCE_LEADERS;
	
	@ClientString(id = 465, message = "You are not currently allied with any clans.")
	public static SystemMessageId YOU_ARE_NOT_CURRENTLY_ALLIED_WITH_ANY_CLANS;
	
	@ClientString(id = 466, message = "You have exceeded the limit.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_THE_LIMIT;
	
	@ClientString(id = 467, message = "You may not accept any clan within a day after expelling another clan.")
	public static SystemMessageId YOU_MAY_NOT_ACCEPT_ANY_CLAN_WITHIN_A_DAY_AFTER_EXPELLING_ANOTHER_CLAN;
	
	@ClientString(id = 468, message = "A clan that has withdrawn or been expelled cannot enter into an alliance within one day of withdrawal or expulsion.")
	public static SystemMessageId A_CLAN_THAT_HAS_WITHDRAWN_OR_BEEN_EXPELLED_CANNOT_ENTER_INTO_AN_ALLIANCE_WITHIN_ONE_DAY_OF_WITHDRAWAL_OR_EXPULSION;
	
	@ClientString(id = 469, message = "You may not ally with a clan you are currently at war with. That would be diabolical and treacherous.")
	public static SystemMessageId YOU_MAY_NOT_ALLY_WITH_A_CLAN_YOU_ARE_CURRENTLY_AT_WAR_WITH_THAT_WOULD_BE_DIABOLICAL_AND_TREACHEROUS;
	
	@ClientString(id = 470, message = "Only the clan leader may apply for withdrawal from the alliance.")
	public static SystemMessageId ONLY_THE_CLAN_LEADER_MAY_APPLY_FOR_WITHDRAWAL_FROM_THE_ALLIANCE;
	
	@ClientString(id = 471, message = "Alliance leaders cannot withdraw.")
	public static SystemMessageId ALLIANCE_LEADERS_CANNOT_WITHDRAW;
	
	@ClientString(id = 472, message = "You cannot expel yourself from the clan.")
	public static SystemMessageId YOU_CANNOT_EXPEL_YOURSELF_FROM_THE_CLAN;
	
	@ClientString(id = 473, message = "Different alliance.")
	public static SystemMessageId DIFFERENT_ALLIANCE;
	
	@ClientString(id = 474, message = "That clan does not exist.")
	public static SystemMessageId THAT_CLAN_DOES_NOT_EXIST;
	
	@ClientString(id = 475, message = "Different alliance.")
	public static SystemMessageId DIFFERENT_ALLIANCE_2;
	
	@ClientString(id = 476, message = "Please adjust the image size to 8x12.")
	public static SystemMessageId PLEASE_ADJUST_THE_IMAGE_SIZE_TO_8X12;
	
	@ClientString(id = 477, message = "No response. Invitation to join an alliance has been cancelled.")
	public static SystemMessageId NO_RESPONSE_INVITATION_TO_JOIN_AN_ALLIANCE_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 478, message = "No response. Your entrance to the alliance has been cancelled.")
	public static SystemMessageId NO_RESPONSE_YOUR_ENTRANCE_TO_THE_ALLIANCE_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 479, message = "$s1 has joined as a friend.")
	public static SystemMessageId S1_HAS_JOINED_AS_A_FRIEND;
	
	@ClientString(id = 480, message = "Please check your friends list.")
	public static SystemMessageId PLEASE_CHECK_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 481, message = "$s1 has been deleted from your friends list.")
	public static SystemMessageId S1_HAS_BEEN_DELETED_FROM_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 482, message = "You cannot add yourself to your own friend list.")
	public static SystemMessageId YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIEND_LIST_2;
	
	@ClientString(id = 483, message = "This function is inaccessible right now. Please try again later.")
	public static SystemMessageId THIS_FUNCTION_IS_INACCESSIBLE_RIGHT_NOW_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 484, message = "This player is already registered on your friends list.")
	public static SystemMessageId THIS_PLAYER_IS_ALREADY_REGISTERED_ON_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 485, message = "No new friend invitations may be accepted.")
	public static SystemMessageId NO_NEW_FRIEND_INVITATIONS_MAY_BE_ACCEPTED;
	
	@ClientString(id = 486, message = "The following user is not on your friends list.")
	public static SystemMessageId THE_FOLLOWING_USER_IS_NOT_ON_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 487, message = "======<Friends List>======")
	public static SystemMessageId FRIENDS_LIST;
	
	@ClientString(id = 488, message = "$s1 (Currently: Online)")
	public static SystemMessageId S1_CURRENTLY_ONLINE;
	
	@ClientString(id = 489, message = "$s1 (Currently: Offline)")
	public static SystemMessageId S1_CURRENTLY_OFFLINE;
	
	@ClientString(id = 490, message = "========================")
	public static SystemMessageId EMPTY_3;
	
	@ClientString(id = 491, message = "=======<Alliance Information>=======")
	public static SystemMessageId ALLIANCE_INFORMATION;
	
	@ClientString(id = 492, message = "Alliance Name: $s1")
	public static SystemMessageId ALLIANCE_NAME_S1;
	
	@ClientString(id = 493, message = "Connection: $s1 / Total $s2")
	public static SystemMessageId CONNECTION_S1_TOTAL_S2;
	
	@ClientString(id = 494, message = "Alliance Leader: $s2 of $s1")
	public static SystemMessageId ALLIANCE_LEADER_S2_OF_S1;
	
	@ClientString(id = 495, message = "Affiliated clans: Total $s1 clan(s)")
	public static SystemMessageId AFFILIATED_CLANS_TOTAL_S1_CLAN_S;
	
	@ClientString(id = 496, message = "=====<Clan Information>=====")
	public static SystemMessageId CLAN_INFORMATION;
	
	@ClientString(id = 497, message = "Clan Name: $s1")
	public static SystemMessageId CLAN_NAME_S1;
	
	@ClientString(id = 498, message = "Clan Leader: $s1")
	public static SystemMessageId CLAN_LEADER_S1;
	
	@ClientString(id = 499, message = "Clan Level: $s1")
	public static SystemMessageId CLAN_LEVEL_S1;
	
	@ClientString(id = 500, message = "------------------------")
	public static SystemMessageId EMPTY_4;
	
	@ClientString(id = 501, message = "========================")
	public static SystemMessageId EMPTY_5;
	
	@ClientString(id = 502, message = "You already belong to another alliance.")
	public static SystemMessageId YOU_ALREADY_BELONG_TO_ANOTHER_ALLIANCE;
	
	@ClientString(id = 503, message = "Your friend $s1 just logged in.")
	public static SystemMessageId YOUR_FRIEND_S1_JUST_LOGGED_IN;
	
	@ClientString(id = 504, message = "Only clan leaders may create alliances.")
	public static SystemMessageId ONLY_CLAN_LEADERS_MAY_CREATE_ALLIANCES;
	
	@ClientString(id = 505, message = "You cannot create a new alliance within 1 day of dissolution.")
	public static SystemMessageId YOU_CANNOT_CREATE_A_NEW_ALLIANCE_WITHIN_1_DAY_OF_DISSOLUTION;
	
	@ClientString(id = 506, message = "Incorrect alliance name. Please try again.")
	public static SystemMessageId INCORRECT_ALLIANCE_NAME_PLEASE_TRY_AGAIN;
	
	@ClientString(id = 507, message = "Incorrect length for an alliance name.")
	public static SystemMessageId INCORRECT_LENGTH_FOR_AN_ALLIANCE_NAME;
	
	@ClientString(id = 508, message = "That alliance name already exists.")
	public static SystemMessageId THAT_ALLIANCE_NAME_ALREADY_EXISTS;
	
	@ClientString(id = 509, message = "Unable to proceed. Clan ally is currently registered as an enemy for the siege.")
	public static SystemMessageId UNABLE_TO_PROCEED_CLAN_ALLY_IS_CURRENTLY_REGISTERED_AS_AN_ENEMY_FOR_THE_SIEGE;
	
	@ClientString(id = 510, message = "You have invited someone to your alliance.")
	public static SystemMessageId YOU_HAVE_INVITED_SOMEONE_TO_YOUR_ALLIANCE;
	
	@ClientString(id = 511, message = "You must first select a user to invite.")
	public static SystemMessageId YOU_MUST_FIRST_SELECT_A_USER_TO_INVITE;
	
	@ClientString(id = 512, message = "Do you really wish to withdraw from the alliance?")
	public static SystemMessageId DO_YOU_REALLY_WISH_TO_WITHDRAW_FROM_THE_ALLIANCE;
	
	@ClientString(id = 513, message = "Enter the name of the clan you wish to expel.")
	public static SystemMessageId ENTER_THE_NAME_OF_THE_CLAN_YOU_WISH_TO_EXPEL;
	
	@ClientString(id = 514, message = "Do you really wish to dissolve the alliance? You cannot create a new alliance for 1 day.")
	public static SystemMessageId DO_YOU_REALLY_WISH_TO_DISSOLVE_THE_ALLIANCE_YOU_CANNOT_CREATE_A_NEW_ALLIANCE_FOR_1_DAY;
	
	@ClientString(id = 515, message = "Enter a file name for the alliance crest.")
	public static SystemMessageId ENTER_A_FILE_NAME_FOR_THE_ALLIANCE_CREST;
	
	@ClientString(id = 516, message = "$s1 has invited you to be their friend.")
	public static SystemMessageId S1_HAS_INVITED_YOU_TO_BE_THEIR_FRIEND;
	
	@ClientString(id = 517, message = "You have accepted the alliance.")
	public static SystemMessageId YOU_HAVE_ACCEPTED_THE_ALLIANCE;
	
	@ClientString(id = 518, message = "You have failed to invite a clan into the alliance.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_INVITE_A_CLAN_INTO_THE_ALLIANCE;
	
	@ClientString(id = 519, message = "You have withdrawn from the alliance.")
	public static SystemMessageId YOU_HAVE_WITHDRAWN_FROM_THE_ALLIANCE;
	
	@ClientString(id = 520, message = "You have failed to withdraw from the alliance.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_WITHDRAW_FROM_THE_ALLIANCE;
	
	@ClientString(id = 521, message = "You have succeeded in expelling the clan.")
	public static SystemMessageId YOU_HAVE_SUCCEEDED_IN_EXPELLING_THE_CLAN;
	
	@ClientString(id = 522, message = "You have failed to expel a clan.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_EXPEL_A_CLAN;
	
	@ClientString(id = 523, message = "The alliance has been dissolved.")
	public static SystemMessageId THE_ALLIANCE_HAS_BEEN_DISSOLVED;
	
	@ClientString(id = 524, message = "You have failed to dissolve the alliance.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_DISSOLVE_THE_ALLIANCE;
	
	@ClientString(id = 525, message = "That person has been successfully added to your Friend List")
	public static SystemMessageId THAT_PERSON_HAS_BEEN_SUCCESSFULLY_ADDED_TO_YOUR_FRIEND_LIST;
	
	@ClientString(id = 526, message = "You have failed to add a friend to your friends list.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_ADD_A_FRIEND_TO_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 527, message = "$s1 leader, $s2, has requested an alliance.")
	public static SystemMessageId S1_LEADER_S2_HAS_REQUESTED_AN_ALLIANCE;
	
	@ClientString(id = 528, message = "Unable to find file at target location.")
	public static SystemMessageId UNABLE_TO_FIND_FILE_AT_TARGET_LOCATION;
	
	@ClientString(id = 529, message = "You may only register an 8 x 12 pixel, 256-color BMP.")
	public static SystemMessageId YOU_MAY_ONLY_REGISTER_AN_8_X_12_PIXEL_256_COLOR_BMP;
	
	@ClientString(id = 530, message = "Your Spiritshot does not match the weapon's grade.")
	public static SystemMessageId YOUR_SPIRITSHOT_DOES_NOT_MATCH_THE_WEAPON_S_GRADE;
	
	@ClientString(id = 531, message = "You do not have enough Spiritshot for that.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_SPIRITSHOT_FOR_THAT;
	
	@ClientString(id = 532, message = "You may not use Spiritshots.")
	public static SystemMessageId YOU_MAY_NOT_USE_SPIRITSHOTS;
	
	@ClientString(id = 533, message = "Your spiritshot has been enabled.")
	public static SystemMessageId YOUR_SPIRITSHOT_HAS_BEEN_ENABLED;
	
	@ClientString(id = 534, message = "Your spiritshot has been disabled.")
	public static SystemMessageId YOUR_SPIRITSHOT_HAS_BEEN_DISABLED;
	
	@ClientString(id = 535, message = "Enter a name for your pet.")
	public static SystemMessageId ENTER_A_NAME_FOR_YOUR_PET;
	
	@ClientString(id = 536, message = "How much adena do you wish to transfer to your Inventory?")
	public static SystemMessageId HOW_MUCH_ADENA_DO_YOU_WISH_TO_TRANSFER_TO_YOUR_INVENTORY;
	
	@ClientString(id = 537, message = "How much will you transfer?")
	public static SystemMessageId HOW_MUCH_WILL_YOU_TRANSFER;
	
	@ClientString(id = 538, message = "Your SP has decreased by $s1.")
	public static SystemMessageId YOUR_SP_HAS_DECREASED_BY_S1;
	
	@ClientString(id = 539, message = "Your Experience has decreased by $s1.")
	public static SystemMessageId YOUR_EXPERIENCE_HAS_DECREASED_BY_S1;
	
	@ClientString(id = 540, message = "Clan leaders may not be deleted. Dissolve the clan first and try again.")
	public static SystemMessageId CLAN_LEADERS_MAY_NOT_BE_DELETED_DISSOLVE_THE_CLAN_FIRST_AND_TRY_AGAIN;
	
	@ClientString(id = 541, message = "You may not delete a clan member. Withdraw from the clan first and try again.")
	public static SystemMessageId YOU_MAY_NOT_DELETE_A_CLAN_MEMBER_WITHDRAW_FROM_THE_CLAN_FIRST_AND_TRY_AGAIN;
	
	@ClientString(id = 542, message = "The NPC server is currently down. Pets and servitors cannot be summoned at this time.")
	public static SystemMessageId THE_NPC_SERVER_IS_CURRENTLY_DOWN_PETS_AND_SERVITORS_CANNOT_BE_SUMMONED_AT_THIS_TIME;
	
	@ClientString(id = 543, message = "You already have a pet.")
	public static SystemMessageId YOU_ALREADY_HAVE_A_PET;
	
	@ClientString(id = 544, message = "Your pet cannot carry this item.")
	public static SystemMessageId YOUR_PET_CANNOT_CARRY_THIS_ITEM;
	
	@ClientString(id = 545, message = "Your pet cannot carry any more items. Remove some, then try again.")
	public static SystemMessageId YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS_REMOVE_SOME_THEN_TRY_AGAIN;
	
	@ClientString(id = 546, message = "Your pet cannot carry any more items.")
	public static SystemMessageId YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS;
	
	@ClientString(id = 547, message = "Summoning your pet…")
	public static SystemMessageId SUMMONING_YOUR_PET;
	
	@ClientString(id = 548, message = "Your pet's name can be up to 8 characters in length.")
	public static SystemMessageId YOUR_PET_S_NAME_CAN_BE_UP_TO_8_CHARACTERS_IN_LENGTH;
	
	@ClientString(id = 549, message = "To create an alliance, your clan must be Level 5 or higher.")
	public static SystemMessageId TO_CREATE_AN_ALLIANCE_YOUR_CLAN_MUST_BE_LEVEL_5_OR_HIGHER;
	
	@ClientString(id = 550, message = "As you are currently schedule for clan dissolution, no alliance can be created.")
	public static SystemMessageId AS_YOU_ARE_CURRENTLY_SCHEDULE_FOR_CLAN_DISSOLUTION_NO_ALLIANCE_CAN_BE_CREATED;
	
	@ClientString(id = 551, message = "As you are currently schedule for clan dissolution, your clan level cannot be increased.")
	public static SystemMessageId AS_YOU_ARE_CURRENTLY_SCHEDULE_FOR_CLAN_DISSOLUTION_YOUR_CLAN_LEVEL_CANNOT_BE_INCREASED;
	
	@ClientString(id = 552, message = "As you are currently schedule for clan dissolution, you cannot register or delete a Clan Crest.")
	public static SystemMessageId AS_YOU_ARE_CURRENTLY_SCHEDULE_FOR_CLAN_DISSOLUTION_YOU_CANNOT_REGISTER_OR_DELETE_A_CLAN_CREST;
	
	@ClientString(id = 553, message = "The opposing clan has applied for dispersion.")
	public static SystemMessageId THE_OPPOSING_CLAN_HAS_APPLIED_FOR_DISPERSION;
	
	@ClientString(id = 554, message = "You cannot disperse the clans in your alliance.")
	public static SystemMessageId YOU_CANNOT_DISPERSE_THE_CLANS_IN_YOUR_ALLIANCE;
	
	@ClientString(id = 555, message = "You cannot move - you are too encumbered.")
	public static SystemMessageId YOU_CANNOT_MOVE_YOU_ARE_TOO_ENCUMBERED;
	
	@ClientString(id = 556, message = "You cannot move in this state.")
	public static SystemMessageId YOU_CANNOT_MOVE_IN_THIS_STATE;
	
	@ClientString(id = 557, message = "As your pet is currently out, its summoning item cannot be destroyed.")
	public static SystemMessageId AS_YOUR_PET_IS_CURRENTLY_OUT_ITS_SUMMONING_ITEM_CANNOT_BE_DESTROYED;
	
	@ClientString(id = 558, message = "As your pet is currently summoned, you cannot discard the summoning item.")
	public static SystemMessageId AS_YOUR_PET_IS_CURRENTLY_SUMMONED_YOU_CANNOT_DISCARD_THE_SUMMONING_ITEM;
	
	@ClientString(id = 559, message = "You have purchased $s2 from $c1.")
	public static SystemMessageId YOU_HAVE_PURCHASED_S2_FROM_C1;
	
	@ClientString(id = 560, message = "You have purchased +$s2 $s3 from $c1.")
	public static SystemMessageId YOU_HAVE_PURCHASED_S2_S3_FROM_C1;
	
	@ClientString(id = 561, message = "You have purchased $s3 $s2(s) from $c1.")
	public static SystemMessageId YOU_HAVE_PURCHASED_S3_S2_S_FROM_C1;
	
	@ClientString(id = 562, message = "You may not crystallize this item. Your crystallization skill level is too low.")
	public static SystemMessageId YOU_MAY_NOT_CRYSTALLIZE_THIS_ITEM_YOUR_CRYSTALLIZATION_SKILL_LEVEL_IS_TOO_LOW;
	
	@ClientString(id = 563, message = "Failed to disable attack target.")
	public static SystemMessageId FAILED_TO_DISABLE_ATTACK_TARGET;
	
	@ClientString(id = 564, message = "Failed to change attack target.")
	public static SystemMessageId FAILED_TO_CHANGE_ATTACK_TARGET;
	
	@ClientString(id = 565, message = "You don't have enough luck.")
	public static SystemMessageId YOU_DON_T_HAVE_ENOUGH_LUCK;
	
	@ClientString(id = 566, message = "Your confusion spell failed.")
	public static SystemMessageId YOUR_CONFUSION_SPELL_FAILED;
	
	@ClientString(id = 567, message = "Your fear spell failed.")
	public static SystemMessageId YOUR_FEAR_SPELL_FAILED;
	
	@ClientString(id = 568, message = "Cubic Summoning failed.")
	public static SystemMessageId CUBIC_SUMMONING_FAILED;
	
	@ClientString(id = 569, message = "Caution -- this item's price greatly differs from non-player run shops. Do you wish to continue?")
	public static SystemMessageId CAUTION_THIS_ITEM_S_PRICE_GREATLY_DIFFERS_FROM_NON_PLAYER_RUN_SHOPS_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 570, message = "How many $s1(s) do you want to purchase?")
	public static SystemMessageId HOW_MANY_S1_S_DO_YOU_WANT_TO_PURCHASE;
	
	@ClientString(id = 571, message = "How many $s1(s) do you want to purchase?")
	public static SystemMessageId HOW_MANY_S1_S_DO_YOU_WANT_TO_PURCHASE_2;
	
	@ClientString(id = 572, message = "Do you accept $c1's party invitation? (Item Distribution: Finders Keepers.)")
	public static SystemMessageId DO_YOU_ACCEPT_C1_S_PARTY_INVITATION_ITEM_DISTRIBUTION_FINDERS_KEEPERS;
	
	@ClientString(id = 573, message = "Do you accept $c1's party invitation? (Item Distribution: Random.)")
	public static SystemMessageId DO_YOU_ACCEPT_C1_S_PARTY_INVITATION_ITEM_DISTRIBUTION_RANDOM;
	
	@ClientString(id = 574, message = "Pets and Servitors are not available at this time.")
	public static SystemMessageId PETS_AND_SERVITORS_ARE_NOT_AVAILABLE_AT_THIS_TIME;
	
	@ClientString(id = 575, message = "How much adena do you wish to transfer to your pet?")
	public static SystemMessageId HOW_MUCH_ADENA_DO_YOU_WISH_TO_TRANSFER_TO_YOUR_PET;
	
	@ClientString(id = 576, message = "How much do you wish to transfer?")
	public static SystemMessageId HOW_MUCH_DO_YOU_WISH_TO_TRANSFER;
	
	@ClientString(id = 577, message = "You cannot summon during a trade or while using a private store.")
	public static SystemMessageId YOU_CANNOT_SUMMON_DURING_A_TRADE_OR_WHILE_USING_A_PRIVATE_STORE;
	
	@ClientString(id = 578, message = "You cannot summon during combat.")
	public static SystemMessageId YOU_CANNOT_SUMMON_DURING_COMBAT;
	
	@ClientString(id = 579, message = "A pet cannot be unsummoned during battle.")
	public static SystemMessageId A_PET_CANNOT_BE_UNSUMMONED_DURING_BATTLE;
	
	@ClientString(id = 580, message = "You may not use multiple pets or servitors at the same time.")
	public static SystemMessageId YOU_MAY_NOT_USE_MULTIPLE_PETS_OR_SERVITORS_AT_THE_SAME_TIME;
	
	@ClientString(id = 581, message = "There is a space in the name.")
	public static SystemMessageId THERE_IS_A_SPACE_IN_THE_NAME;
	
	@ClientString(id = 582, message = "Inappropriate character name.")
	public static SystemMessageId INAPPROPRIATE_CHARACTER_NAME;
	
	@ClientString(id = 583, message = "Name includes forbidden words.")
	public static SystemMessageId NAME_INCLUDES_FORBIDDEN_WORDS;
	
	@ClientString(id = 584, message = "This is already in use by another pet.")
	public static SystemMessageId THIS_IS_ALREADY_IN_USE_BY_ANOTHER_PET;
	
	@ClientString(id = 585, message = "Select the purchasing price")
	public static SystemMessageId SELECT_THE_PURCHASING_PRICE;
	
	@ClientString(id = 586, message = "Pet items cannot be registered as shortcuts.")
	public static SystemMessageId PET_ITEMS_CANNOT_BE_REGISTERED_AS_SHORTCUTS;
	
	@ClientString(id = 587, message = "Irregular system speed.")
	public static SystemMessageId IRREGULAR_SYSTEM_SPEED;
	
	@ClientString(id = 588, message = "Your pet's inventory is full.")
	public static SystemMessageId YOUR_PET_S_INVENTORY_IS_FULL;
	
	@ClientString(id = 589, message = "Dead pets cannot be returned to their summoning item.")
	public static SystemMessageId DEAD_PETS_CANNOT_BE_RETURNED_TO_THEIR_SUMMONING_ITEM;
	
	@ClientString(id = 590, message = "Your pet is dead and any attempt you make to give it something goes unrecognized.")
	public static SystemMessageId YOUR_PET_IS_DEAD_AND_ANY_ATTEMPT_YOU_MAKE_TO_GIVE_IT_SOMETHING_GOES_UNRECOGNIZED;
	
	@ClientString(id = 591, message = "An invalid character is included in the pet's name.")
	public static SystemMessageId AN_INVALID_CHARACTER_IS_INCLUDED_IN_THE_PET_S_NAME;
	
	@ClientString(id = 592, message = "Do you wish to dismiss your pet? Dismissing your pet will cause the pet necklace to disappear.")
	public static SystemMessageId DO_YOU_WISH_TO_DISMISS_YOUR_PET_DISMISSING_YOUR_PET_WILL_CAUSE_THE_PET_NECKLACE_TO_DISAPPEAR;
	
	@ClientString(id = 593, message = "Starving, grumpy and fed up, your pet has left.")
	public static SystemMessageId STARVING_GRUMPY_AND_FED_UP_YOUR_PET_HAS_LEFT;
	
	@ClientString(id = 594, message = "You may not restore a hungry pet.")
	public static SystemMessageId YOU_MAY_NOT_RESTORE_A_HUNGRY_PET;
	
	@ClientString(id = 595, message = "Your pet is very hungry.")
	public static SystemMessageId YOUR_PET_IS_VERY_HUNGRY;
	
	@ClientString(id = 596, message = "Your pet ate a little, but is still hungry.")
	public static SystemMessageId YOUR_PET_ATE_A_LITTLE_BUT_IS_STILL_HUNGRY;
	
	@ClientString(id = 597, message = "Your pet is very hungry. Please be careful.")
	public static SystemMessageId YOUR_PET_IS_VERY_HUNGRY_PLEASE_BE_CAREFUL;
	
	@ClientString(id = 598, message = "You may not chat while you are invisible.")
	public static SystemMessageId YOU_MAY_NOT_CHAT_WHILE_YOU_ARE_INVISIBLE;
	
	@ClientString(id = 599, message = "The GM has an important notice. Chat has been temporarily disabled.")
	public static SystemMessageId THE_GM_HAS_AN_IMPORTANT_NOTICE_CHAT_HAS_BEEN_TEMPORARILY_DISABLED;
	
	@ClientString(id = 600, message = "You may not equip a pet item.")
	public static SystemMessageId YOU_MAY_NOT_EQUIP_A_PET_ITEM;
	
	@ClientString(id = 601, message = "There are $S1 petitions currently on the waiting list.")
	public static SystemMessageId THERE_ARE_S1_PETITIONS_CURRENTLY_ON_THE_WAITING_LIST;
	
	@ClientString(id = 602, message = "The petition system is currently unavailable. Please try again later.")
	public static SystemMessageId THE_PETITION_SYSTEM_IS_CURRENTLY_UNAVAILABLE_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 603, message = "That item cannot be discarded or exchanged.")
	public static SystemMessageId THAT_ITEM_CANNOT_BE_DISCARDED_OR_EXCHANGED;
	
	@ClientString(id = 604, message = "You may not call forth a pet or summoned creature from this location.")
	public static SystemMessageId YOU_MAY_NOT_CALL_FORTH_A_PET_OR_SUMMONED_CREATURE_FROM_THIS_LOCATION;
	
	@ClientString(id = 605, message = "You can only enter up 128 names in your friends list.")
	public static SystemMessageId YOU_CAN_ONLY_ENTER_UP_128_NAMES_IN_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 606, message = "The Friend's List of the person you are trying to add is full, so registration is not possible.")
	public static SystemMessageId THE_FRIEND_S_LIST_OF_THE_PERSON_YOU_ARE_TRYING_TO_ADD_IS_FULL_SO_REGISTRATION_IS_NOT_POSSIBLE;
	
	@ClientString(id = 607, message = "You do not have any further skills to learn. Come back when you have reached Level $s1.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ANY_FURTHER_SKILLS_TO_LEARN_COME_BACK_WHEN_YOU_HAVE_REACHED_LEVEL_S1;
	
	@ClientString(id = 608, message = "$c1 has obtained $s3 $s2 by using sweeper.")
	public static SystemMessageId C1_HAS_OBTAINED_S3_S2_BY_USING_SWEEPER;
	
	@ClientString(id = 609, message = "$c1 has obtained $s2 by using sweeper.")
	public static SystemMessageId C1_HAS_OBTAINED_S2_BY_USING_SWEEPER;
	
	@ClientString(id = 610, message = "Your skill has been canceled due to lack of HP.")
	public static SystemMessageId YOUR_SKILL_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_HP;
	
	@ClientString(id = 611, message = "You have succeeded in Confusing the enemy.")
	public static SystemMessageId YOU_HAVE_SUCCEEDED_IN_CONFUSING_THE_ENEMY;
	
	@ClientString(id = 612, message = "The Spoil condition has been activated.")
	public static SystemMessageId THE_SPOIL_CONDITION_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 613, message = "======<Ignore List>======")
	public static SystemMessageId IGNORE_LIST;
	
	@ClientString(id = 614, message = "$c1 : $c2")
	public static SystemMessageId C1_C2;
	
	@ClientString(id = 615, message = "You have failed to register the user to your Ignore List.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_REGISTER_THE_USER_TO_YOUR_IGNORE_LIST;
	
	@ClientString(id = 616, message = "You have failed to delete the character.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_DELETE_THE_CHARACTER_2;
	
	@ClientString(id = 617, message = "$s1 has been added to your Ignore List.")
	public static SystemMessageId S1_HAS_BEEN_ADDED_TO_YOUR_IGNORE_LIST;
	
	@ClientString(id = 618, message = "$s1 has been removed from your Ignore List.")
	public static SystemMessageId S1_HAS_BEEN_REMOVED_FROM_YOUR_IGNORE_LIST;
	
	@ClientString(id = 619, message = "$s1 has placed you on his/her Ignore List.")
	public static SystemMessageId S1_HAS_PLACED_YOU_ON_HIS_HER_IGNORE_LIST;
	
	@ClientString(id = 620, message = "$s1 has placed you on his/her Ignore List.")
	public static SystemMessageId S1_HAS_PLACED_YOU_ON_HIS_HER_IGNORE_LIST_2;
	
	@ClientString(id = 621, message = "Game connection attempted through a restricted IP.")
	public static SystemMessageId GAME_CONNECTION_ATTEMPTED_THROUGH_A_RESTRICTED_IP;
	
	@ClientString(id = 622, message = "You may not make a declaration of war during an alliance battle.")
	public static SystemMessageId YOU_MAY_NOT_MAKE_A_DECLARATION_OF_WAR_DURING_AN_ALLIANCE_BATTLE;
	
	@ClientString(id = 623, message = "Your opponent has exceeded the number of simultaneous alliance battles allowed.")
	public static SystemMessageId YOUR_OPPONENT_HAS_EXCEEDED_THE_NUMBER_OF_SIMULTANEOUS_ALLIANCE_BATTLES_ALLOWED;
	
	@ClientString(id = 624, message = "Clan leader $s1 is not currently connected to the game server.")
	public static SystemMessageId CLAN_LEADER_S1_IS_NOT_CURRENTLY_CONNECTED_TO_THE_GAME_SERVER;
	
	@ClientString(id = 625, message = "Your request for an Alliance Battle truce has been denied.")
	public static SystemMessageId YOUR_REQUEST_FOR_AN_ALLIANCE_BATTLE_TRUCE_HAS_BEEN_DENIED;
	
	@ClientString(id = 626, message = "The $s1 clan did not respond: war proclamation has been refused.")
	public static SystemMessageId THE_S1_CLAN_DID_NOT_RESPOND_WAR_PROCLAMATION_HAS_BEEN_REFUSED_2;
	
	@ClientString(id = 627, message = "Clan battle has been refused because you did not respond to $s1's war proclamation.")
	public static SystemMessageId CLAN_BATTLE_HAS_BEEN_REFUSED_BECAUSE_YOU_DID_NOT_RESPOND_TO_S1_S_WAR_PROCLAMATION;
	
	@ClientString(id = 628, message = "You have already been at war with the $s1 clan: 5 days must pass before you can declare war again.")
	public static SystemMessageId YOU_HAVE_ALREADY_BEEN_AT_WAR_WITH_THE_S1_CLAN_5_DAYS_MUST_PASS_BEFORE_YOU_CAN_DECLARE_WAR_AGAIN;
	
	@ClientString(id = 629, message = "Your opponent has exceeded the number of simultaneous alliance battles allowed.")
	public static SystemMessageId YOUR_OPPONENT_HAS_EXCEEDED_THE_NUMBER_OF_SIMULTANEOUS_ALLIANCE_BATTLES_ALLOWED_2;
	
	@ClientString(id = 630, message = "War with clan $s1 has begun.")
	public static SystemMessageId WAR_WITH_CLAN_S1_HAS_BEGUN;
	
	@ClientString(id = 631, message = "War with clan $s1 is over.")
	public static SystemMessageId WAR_WITH_CLAN_S1_IS_OVER;
	
	@ClientString(id = 632, message = "You have won the war over the $s1 clan!")
	public static SystemMessageId YOU_HAVE_WON_THE_WAR_OVER_THE_S1_CLAN_2;
	
	@ClientString(id = 633, message = "You have surrendered to the $s1 clan.")
	public static SystemMessageId YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN_2;
	
	@ClientString(id = 634, message = "Your alliance leader has been slain. You have been defeated by the $s1 clan.")
	public static SystemMessageId YOUR_ALLIANCE_LEADER_HAS_BEEN_SLAIN_YOU_HAVE_BEEN_DEFEATED_BY_THE_S1_CLAN;
	
	@ClientString(id = 635, message = "The time limit for the clan war has been exceeded. War with the $s1 clan is over.")
	public static SystemMessageId THE_TIME_LIMIT_FOR_THE_CLAN_WAR_HAS_BEEN_EXCEEDED_WAR_WITH_THE_S1_CLAN_IS_OVER;
	
	@ClientString(id = 636, message = "You are not involved in a clan war.")
	public static SystemMessageId YOU_ARE_NOT_INVOLVED_IN_A_CLAN_WAR_2;
	
	@ClientString(id = 637, message = "A clan ally has registered itself to the opponent.")
	public static SystemMessageId A_CLAN_ALLY_HAS_REGISTERED_ITSELF_TO_THE_OPPONENT;
	
	@ClientString(id = 638, message = "You have already requested a Castle Siege.")
	public static SystemMessageId YOU_HAVE_ALREADY_REQUESTED_A_CASTLE_SIEGE;
	
	@ClientString(id = 639, message = "Your application has been denied because you have already submitted a request for another Castle Siege.")
	public static SystemMessageId YOUR_APPLICATION_HAS_BEEN_DENIED_BECAUSE_YOU_HAVE_ALREADY_SUBMITTED_A_REQUEST_FOR_ANOTHER_CASTLE_SIEGE;
	
	@ClientString(id = 640, message = "You have failed to refuse castle defense aid.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_REFUSE_CASTLE_DEFENSE_AID;
	
	@ClientString(id = 641, message = "You have failed to approve castle defense aid.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_APPROVE_CASTLE_DEFENSE_AID;
	
	@ClientString(id = 642, message = "You are already registered to the attacker side and must cancel your registration before submitting your request.")
	public static SystemMessageId YOU_ARE_ALREADY_REGISTERED_TO_THE_ATTACKER_SIDE_AND_MUST_CANCEL_YOUR_REGISTRATION_BEFORE_SUBMITTING_YOUR_REQUEST;
	
	@ClientString(id = 643, message = "You have already registered to the defender side and must cancel your registration before submitting your request.")
	public static SystemMessageId YOU_HAVE_ALREADY_REGISTERED_TO_THE_DEFENDER_SIDE_AND_MUST_CANCEL_YOUR_REGISTRATION_BEFORE_SUBMITTING_YOUR_REQUEST;
	
	@ClientString(id = 644, message = "You are not yet registered for the castle siege.")
	public static SystemMessageId YOU_ARE_NOT_YET_REGISTERED_FOR_THE_CASTLE_SIEGE;
	
	@ClientString(id = 645, message = "Only clans of level 5 or higher may register for a castle siege.")
	public static SystemMessageId ONLY_CLANS_OF_LEVEL_5_OR_HIGHER_MAY_REGISTER_FOR_A_CASTLE_SIEGE;
	
	@ClientString(id = 646, message = "You do not have the authority to modify the castle defender list.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_MODIFY_THE_CASTLE_DEFENDER_LIST;
	
	@ClientString(id = 647, message = "You do not have the authority to modify the siege time.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_MODIFY_THE_SIEGE_TIME;
	
	@ClientString(id = 648, message = "No more registrations may be accepted for the attacker side.")
	public static SystemMessageId NO_MORE_REGISTRATIONS_MAY_BE_ACCEPTED_FOR_THE_ATTACKER_SIDE;
	
	@ClientString(id = 649, message = "No more registrations may be accepted for the defender side.")
	public static SystemMessageId NO_MORE_REGISTRATIONS_MAY_BE_ACCEPTED_FOR_THE_DEFENDER_SIDE;
	
	@ClientString(id = 650, message = "You may not summon from your current location.")
	public static SystemMessageId YOU_MAY_NOT_SUMMON_FROM_YOUR_CURRENT_LOCATION;
	
	@ClientString(id = 651, message = "Place $s1 in the current location and direction. Do you wish to continue?")
	public static SystemMessageId PLACE_S1_IN_THE_CURRENT_LOCATION_AND_DIRECTION_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 652, message = "The target of the summoned monster is wrong.")
	public static SystemMessageId THE_TARGET_OF_THE_SUMMONED_MONSTER_IS_WRONG;
	
	@ClientString(id = 653, message = "You do not have the authority to position mercenaries.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_POSITION_MERCENARIES;
	
	@ClientString(id = 654, message = "You do not have the authority to cancel mercenary positioning.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_CANCEL_MERCENARY_POSITIONING;
	
	@ClientString(id = 655, message = "Mercenaries cannot be positioned here.")
	public static SystemMessageId MERCENARIES_CANNOT_BE_POSITIONED_HERE;
	
	@ClientString(id = 656, message = "This mercenary cannot be positioned anymore.")
	public static SystemMessageId THIS_MERCENARY_CANNOT_BE_POSITIONED_ANYMORE;
	
	@ClientString(id = 657, message = "Positioning cannot be done here because the distance between mercenaries is too short.")
	public static SystemMessageId POSITIONING_CANNOT_BE_DONE_HERE_BECAUSE_THE_DISTANCE_BETWEEN_MERCENARIES_IS_TOO_SHORT;
	
	@ClientString(id = 658, message = "This is not a mercenary of a castle that you own and so you cannot cancel its positioning.")
	public static SystemMessageId THIS_IS_NOT_A_MERCENARY_OF_A_CASTLE_THAT_YOU_OWN_AND_SO_YOU_CANNOT_CANCEL_ITS_POSITIONING;
	
	@ClientString(id = 659, message = "This is not the time for siege registration and so registrations cannot be accepted or rejected.")
	public static SystemMessageId THIS_IS_NOT_THE_TIME_FOR_SIEGE_REGISTRATION_AND_SO_REGISTRATIONS_CANNOT_BE_ACCEPTED_OR_REJECTED;
	
	@ClientString(id = 660, message = "This is not the time for siege registration and so registration and cancellation cannot be done.")
	public static SystemMessageId THIS_IS_NOT_THE_TIME_FOR_SIEGE_REGISTRATION_AND_SO_REGISTRATION_AND_CANCELLATION_CANNOT_BE_DONE;
	
	@ClientString(id = 661, message = "This character cannot be spoiled.")
	public static SystemMessageId THIS_CHARACTER_CANNOT_BE_SPOILED;
	
	@ClientString(id = 662, message = "The other player is rejecting friend invitations.")
	public static SystemMessageId THE_OTHER_PLAYER_IS_REJECTING_FRIEND_INVITATIONS;
	
	@ClientString(id = 663, message = "The siege time has been declared for $s. It is not possible to change the time after a siege time has been declared. Do you want to continue?")
	public static SystemMessageId THE_SIEGE_TIME_HAS_BEEN_DECLARED_FOR_S_IT_IS_NOT_POSSIBLE_TO_CHANGE_THE_TIME_AFTER_A_SIEGE_TIME_HAS_BEEN_DECLARED_DO_YOU_WANT_TO_CONTINUE;
	
	@ClientString(id = 664, message = "Please choose a person to receive.")
	public static SystemMessageId PLEASE_CHOOSE_A_PERSON_TO_RECEIVE;
	
	@ClientString(id = 665, message = "$s2 of $s1 alliance is applying for alliance war. Do you want to accept the challenge?")
	public static SystemMessageId S2_OF_S1_ALLIANCE_IS_APPLYING_FOR_ALLIANCE_WAR_DO_YOU_WANT_TO_ACCEPT_THE_CHALLENGE;
	
	@ClientString(id = 666, message = "A request for ceasefire has been received from $s1 alliance. Do you agree?")
	public static SystemMessageId A_REQUEST_FOR_CEASEFIRE_HAS_BEEN_RECEIVED_FROM_S1_ALLIANCE_DO_YOU_AGREE;
	
	@ClientString(id = 667, message = "You are registering on the attacking side of the $s1 siege. Do you want to continue?")
	public static SystemMessageId YOU_ARE_REGISTERING_ON_THE_ATTACKING_SIDE_OF_THE_S1_SIEGE_DO_YOU_WANT_TO_CONTINUE;
	
	@ClientString(id = 668, message = "You are registering on the defending side of the $s1 siege. Do you want to continue?")
	public static SystemMessageId YOU_ARE_REGISTERING_ON_THE_DEFENDING_SIDE_OF_THE_S1_SIEGE_DO_YOU_WANT_TO_CONTINUE;
	
	@ClientString(id = 669, message = "You are canceling your application to participate in the $s1 castle siege. Do you want to continue?")
	public static SystemMessageId YOU_ARE_CANCELING_YOUR_APPLICATION_TO_PARTICIPATE_IN_THE_S1_CASTLE_SIEGE_DO_YOU_WANT_TO_CONTINUE;
	
	@ClientString(id = 670, message = "You are declining the registration of clan $s1 as a defender. Do you want to continue?")
	public static SystemMessageId YOU_ARE_DECLINING_THE_REGISTRATION_OF_CLAN_S1_AS_A_DEFENDER_DO_YOU_WANT_TO_CONTINUE;
	
	@ClientString(id = 671, message = "You are accepting the registration of clan $s1 as a defender. Do you want to continue?")
	public static SystemMessageId YOU_ARE_ACCEPTING_THE_REGISTRATION_OF_CLAN_S1_AS_A_DEFENDER_DO_YOU_WANT_TO_CONTINUE;
	
	@ClientString(id = 672, message = "$s1 adena disappeared.")
	public static SystemMessageId S1_ADENA_DISAPPEARED;
	
	@ClientString(id = 673, message = "Only a clan leader whose clan is of level 2 or higher is allowed to participate in a clan hall auction.")
	public static SystemMessageId ONLY_A_CLAN_LEADER_WHOSE_CLAN_IS_OF_LEVEL_2_OR_HIGHER_IS_ALLOWED_TO_PARTICIPATE_IN_A_CLAN_HALL_AUCTION;
	
	@ClientString(id = 674, message = "It has not yet been seven days since canceling an auction.")
	public static SystemMessageId IT_HAS_NOT_YET_BEEN_SEVEN_DAYS_SINCE_CANCELING_AN_AUCTION;
	
	@ClientString(id = 675, message = "There are no clan halls up for auction.")
	public static SystemMessageId THERE_ARE_NO_CLAN_HALLS_UP_FOR_AUCTION;
	
	@ClientString(id = 676, message = "Since you have already submitted a bid, you are not allowed to participate in another auction at this time.")
	public static SystemMessageId SINCE_YOU_HAVE_ALREADY_SUBMITTED_A_BID_YOU_ARE_NOT_ALLOWED_TO_PARTICIPATE_IN_ANOTHER_AUCTION_AT_THIS_TIME;
	
	@ClientString(id = 677, message = "Your bid price must be higher than the minimum price currently being bid.")
	public static SystemMessageId YOUR_BID_PRICE_MUST_BE_HIGHER_THAN_THE_MINIMUM_PRICE_CURRENTLY_BEING_BID;
	
	@ClientString(id = 678, message = "You have submitted a bid for the auction of $s1.")
	public static SystemMessageId YOU_HAVE_SUBMITTED_A_BID_FOR_THE_AUCTION_OF_S1;
	
	@ClientString(id = 679, message = "You have canceled your bid.")
	public static SystemMessageId YOU_HAVE_CANCELED_YOUR_BID;
	
	@ClientString(id = 680, message = "You do not meet the requirements to participate in an auction.")
	public static SystemMessageId YOU_DO_NOT_MEET_THE_REQUIREMENTS_TO_PARTICIPATE_IN_AN_AUCTION;
	
	@ClientString(id = 681, message = "The clan does not own a clan hall.")
	public static SystemMessageId THE_CLAN_DOES_NOT_OWN_A_CLAN_HALL;
	
	@ClientString(id = 682, message = "You are moving to another village. Do you want to continue?")
	public static SystemMessageId YOU_ARE_MOVING_TO_ANOTHER_VILLAGE_DO_YOU_WANT_TO_CONTINUE;
	
	@ClientString(id = 683, message = "There are no priority rights on a sweeper.")
	public static SystemMessageId THERE_ARE_NO_PRIORITY_RIGHTS_ON_A_SWEEPER;
	
	@ClientString(id = 684, message = "You cannot position mercenaries during a siege.")
	public static SystemMessageId YOU_CANNOT_POSITION_MERCENARIES_DURING_A_SIEGE;
	
	@ClientString(id = 685, message = "You cannot apply for clan war with a clan that belongs to the same alliance.")
	public static SystemMessageId YOU_CANNOT_APPLY_FOR_CLAN_WAR_WITH_A_CLAN_THAT_BELONGS_TO_THE_SAME_ALLIANCE;
	
	@ClientString(id = 686, message = "You have received $s1 damage from the fire of magic.")
	public static SystemMessageId YOU_HAVE_RECEIVED_S1_DAMAGE_FROM_THE_FIRE_OF_MAGIC;
	
	@ClientString(id = 687, message = "You cannot move while frozen. Please wait.")
	public static SystemMessageId YOU_CANNOT_MOVE_WHILE_FROZEN_PLEASE_WAIT;
	
	@ClientString(id = 688, message = "Castle-owning clans are automatically registered on the defending side.")
	public static SystemMessageId CASTLE_OWNING_CLANS_ARE_AUTOMATICALLY_REGISTERED_ON_THE_DEFENDING_SIDE;
	
	@ClientString(id = 689, message = "A clan that owns a castle cannot participate in another siege.")
	public static SystemMessageId A_CLAN_THAT_OWNS_A_CASTLE_CANNOT_PARTICIPATE_IN_ANOTHER_SIEGE;
	
	@ClientString(id = 690, message = "You cannot register as an attacker because you are in an alliance with the castle-owning clan.")
	public static SystemMessageId YOU_CANNOT_REGISTER_AS_AN_ATTACKER_BECAUSE_YOU_ARE_IN_AN_ALLIANCE_WITH_THE_CASTLE_OWNING_CLAN;
	
	@ClientString(id = 691, message = "$s1 clan is already a member of $s2 alliance.")
	public static SystemMessageId S1_CLAN_IS_ALREADY_A_MEMBER_OF_S2_ALLIANCE;
	
	@ClientString(id = 692, message = "The other party is frozen. Please wait a moment.")
	public static SystemMessageId THE_OTHER_PARTY_IS_FROZEN_PLEASE_WAIT_A_MOMENT;
	
	@ClientString(id = 693, message = "The package that arrived is in another warehouse.")
	public static SystemMessageId THE_PACKAGE_THAT_ARRIVED_IS_IN_ANOTHER_WAREHOUSE;
	
	@ClientString(id = 694, message = "No packages have arrived.")
	public static SystemMessageId NO_PACKAGES_HAVE_ARRIVED;
	
	@ClientString(id = 695, message = "You cannot set the name of the pet.")
	public static SystemMessageId YOU_CANNOT_SET_THE_NAME_OF_THE_PET;
	
	@ClientString(id = 696, message = "Your account is restricted for not paying your PC room usage fees.")
	public static SystemMessageId YOUR_ACCOUNT_IS_RESTRICTED_FOR_NOT_PAYING_YOUR_PC_ROOM_USAGE_FEES;
	
	@ClientString(id = 697, message = "The item enchant value is strange.")
	public static SystemMessageId THE_ITEM_ENCHANT_VALUE_IS_STRANGE;
	
	@ClientString(id = 698, message = "The price is different than the same item on the sales list.")
	public static SystemMessageId THE_PRICE_IS_DIFFERENT_THAN_THE_SAME_ITEM_ON_THE_SALES_LIST;
	
	@ClientString(id = 699, message = "Currently not purchasing.")
	public static SystemMessageId CURRENTLY_NOT_PURCHASING;
	
	@ClientString(id = 700, message = "The purchase is complete.")
	public static SystemMessageId THE_PURCHASE_IS_COMPLETE;
	
	@ClientString(id = 701, message = "You do not have enough required items.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_REQUIRED_ITEMS;
	
	@ClientString(id = 702, message = "There are no GMs currently visible in the public list as they may be performing other functions at the moment.")
	public static SystemMessageId THERE_ARE_NO_GMS_CURRENTLY_VISIBLE_IN_THE_PUBLIC_LIST_AS_THEY_MAY_BE_PERFORMING_OTHER_FUNCTIONS_AT_THE_MOMENT;
	
	@ClientString(id = 703, message = "======<GM List>======")
	public static SystemMessageId GM_LIST;
	
	@ClientString(id = 704, message = "GM : $c1")
	public static SystemMessageId GM_C1;
	
	@ClientString(id = 705, message = "You cannot exclude yourself.")
	public static SystemMessageId YOU_CANNOT_EXCLUDE_YOURSELF;
	
	@ClientString(id = 706, message = "You can only enter up to 128 names in your block list.")
	public static SystemMessageId YOU_CAN_ONLY_ENTER_UP_TO_128_NAMES_IN_YOUR_BLOCK_LIST;
	
	@ClientString(id = 707, message = "You cannot teleport to a village that is in a siege.")
	public static SystemMessageId YOU_CANNOT_TELEPORT_TO_A_VILLAGE_THAT_IS_IN_A_SIEGE;
	
	@ClientString(id = 708, message = "You do not have the right to use the castle warehouse.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_THE_CASTLE_WAREHOUSE;
	
	@ClientString(id = 709, message = "You do not have the right to use the clan warehouse.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_THE_CLAN_WAREHOUSE;
	
	@ClientString(id = 710, message = "Only clans of clan level 1 or higher can use a clan warehouse.")
	public static SystemMessageId ONLY_CLANS_OF_CLAN_LEVEL_1_OR_HIGHER_CAN_USE_A_CLAN_WAREHOUSE;
	
	@ClientString(id = 711, message = "The $s1 siege has started.")
	public static SystemMessageId THE_S1_SIEGE_HAS_STARTED;
	
	@ClientString(id = 712, message = "The $s1 siege has finished.")
	public static SystemMessageId THE_S1_SIEGE_HAS_FINISHED;
	
	@ClientString(id = 713, message = "$s1/$s2/$s3 $s4:$s5")
	public static SystemMessageId S1_S2_S3_S4_S5;
	
	@ClientString(id = 714, message = "A trap device has been tripped.")
	public static SystemMessageId A_TRAP_DEVICE_HAS_BEEN_TRIPPED;
	
	@ClientString(id = 715, message = "The trap device has been stopped.")
	public static SystemMessageId THE_TRAP_DEVICE_HAS_BEEN_STOPPED;
	
	@ClientString(id = 716, message = "If a base camp does not exist, resurrection is not possible.")
	public static SystemMessageId IF_A_BASE_CAMP_DOES_NOT_EXIST_RESURRECTION_IS_NOT_POSSIBLE;
	
	@ClientString(id = 717, message = "The guardian tower has been destroyed and resurrection is not possible.")
	public static SystemMessageId THE_GUARDIAN_TOWER_HAS_BEEN_DESTROYED_AND_RESURRECTION_IS_NOT_POSSIBLE;
	
	@ClientString(id = 718, message = "The castle gates cannot be opened and closed during a siege.")
	public static SystemMessageId THE_CASTLE_GATES_CANNOT_BE_OPENED_AND_CLOSED_DURING_A_SIEGE;
	
	@ClientString(id = 719, message = "You failed at mixing the item.")
	public static SystemMessageId YOU_FAILED_AT_MIXING_THE_ITEM;
	
	@ClientString(id = 720, message = "The purchase price is higher than the amount of money that you have and so you cannot open a personal store.")
	public static SystemMessageId THE_PURCHASE_PRICE_IS_HIGHER_THAN_THE_AMOUNT_OF_MONEY_THAT_YOU_HAVE_AND_SO_YOU_CANNOT_OPEN_A_PERSONAL_STORE;
	
	@ClientString(id = 721, message = "You cannot create an alliance while participating in a siege.")
	public static SystemMessageId YOU_CANNOT_CREATE_AN_ALLIANCE_WHILE_PARTICIPATING_IN_A_SIEGE;
	
	@ClientString(id = 722, message = "You cannot dissolve an alliance while an affiliated clan is participating in a siege battle.")
	public static SystemMessageId YOU_CANNOT_DISSOLVE_AN_ALLIANCE_WHILE_AN_AFFILIATED_CLAN_IS_PARTICIPATING_IN_A_SIEGE_BATTLE;
	
	@ClientString(id = 723, message = "The opposing clan is participating in a siege battle.")
	public static SystemMessageId THE_OPPOSING_CLAN_IS_PARTICIPATING_IN_A_SIEGE_BATTLE;
	
	@ClientString(id = 724, message = "You cannot leave while participating in a siege battle.")
	public static SystemMessageId YOU_CANNOT_LEAVE_WHILE_PARTICIPATING_IN_A_SIEGE_BATTLE;
	
	@ClientString(id = 725, message = "You cannot banish a clan from an alliance while the clan is participating in a siege.")
	public static SystemMessageId YOU_CANNOT_BANISH_A_CLAN_FROM_AN_ALLIANCE_WHILE_THE_CLAN_IS_PARTICIPATING_IN_A_SIEGE;
	
	@ClientString(id = 726, message = "The frozen condition has started. Please wait a moment.")
	public static SystemMessageId THE_FROZEN_CONDITION_HAS_STARTED_PLEASE_WAIT_A_MOMENT;
	
	@ClientString(id = 727, message = "The frozen condition was removed.")
	public static SystemMessageId THE_FROZEN_CONDITION_WAS_REMOVED;
	
	@ClientString(id = 728, message = "You cannot apply for dissolution again within seven days after a previous application for dissolution.")
	public static SystemMessageId YOU_CANNOT_APPLY_FOR_DISSOLUTION_AGAIN_WITHIN_SEVEN_DAYS_AFTER_A_PREVIOUS_APPLICATION_FOR_DISSOLUTION;
	
	@ClientString(id = 729, message = "That item cannot be discarded.")
	public static SystemMessageId THAT_ITEM_CANNOT_BE_DISCARDED;
	
	@ClientString(id = 730, message = "You have submitted $s1 petition(s). \\n - You may submit $s2 more petition(s) today.")
	public static SystemMessageId YOU_HAVE_SUBMITTED_S1_PETITION_S_N_YOU_MAY_SUBMIT_S2_MORE_PETITION_S_TODAY;
	
	@ClientString(id = 731, message = "A petition has been received by the GM on behalf of $s1. The petition code is $s2.")
	public static SystemMessageId A_PETITION_HAS_BEEN_RECEIVED_BY_THE_GM_ON_BEHALF_OF_S1_THE_PETITION_CODE_IS_S2;
	
	@ClientString(id = 732, message = "$c1 has received a request for a consultation with the GM.")
	public static SystemMessageId C1_HAS_RECEIVED_A_REQUEST_FOR_A_CONSULTATION_WITH_THE_GM;
	
	@ClientString(id = 733, message = "We have received $s1 petitions from you today and that is the maximum that you can submit in one day. You cannot submit any more petitions.")
	public static SystemMessageId WE_HAVE_RECEIVED_S1_PETITIONS_FROM_YOU_TODAY_AND_THAT_IS_THE_MAXIMUM_THAT_YOU_CAN_SUBMIT_IN_ONE_DAY_YOU_CANNOT_SUBMIT_ANY_MORE_PETITIONS;
	
	@ClientString(id = 734, message = "You have failed at submitting a petition on behalf of someone else. $c1 already submitted a petition.")
	public static SystemMessageId YOU_HAVE_FAILED_AT_SUBMITTING_A_PETITION_ON_BEHALF_OF_SOMEONE_ELSE_C1_ALREADY_SUBMITTED_A_PETITION;
	
	@ClientString(id = 735, message = "You have failed at submitting a petition on behalf of $c1. The error number is $s2.")
	public static SystemMessageId YOU_HAVE_FAILED_AT_SUBMITTING_A_PETITION_ON_BEHALF_OF_C1_THE_ERROR_NUMBER_IS_S2;
	
	@ClientString(id = 736, message = "The petition was canceled. You may submit $s1 more petition(s) today.")
	public static SystemMessageId THE_PETITION_WAS_CANCELED_YOU_MAY_SUBMIT_S1_MORE_PETITION_S_TODAY;
	
	@ClientString(id = 737, message = "You have cancelled submitting a petition on behalf of $s1.")
	public static SystemMessageId YOU_HAVE_CANCELLED_SUBMITTING_A_PETITION_ON_BEHALF_OF_S1;
	
	@ClientString(id = 738, message = "You have not submitted a petition.")
	public static SystemMessageId YOU_HAVE_NOT_SUBMITTED_A_PETITION;
	
	@ClientString(id = 739, message = "You have failed at cancelling a petition on behalf of $c1. The error code is $s2.")
	public static SystemMessageId YOU_HAVE_FAILED_AT_CANCELLING_A_PETITION_ON_BEHALF_OF_C1_THE_ERROR_CODE_IS_S2;
	
	@ClientString(id = 740, message = "$c1 participated in a petition chat at the request of the GM.")
	public static SystemMessageId C1_PARTICIPATED_IN_A_PETITION_CHAT_AT_THE_REQUEST_OF_THE_GM;
	
	@ClientString(id = 741, message = "You have failed at adding $c1 to the petition chat. Petition has already been submitted.")
	public static SystemMessageId YOU_HAVE_FAILED_AT_ADDING_C1_TO_THE_PETITION_CHAT_PETITION_HAS_ALREADY_BEEN_SUBMITTED;
	
	@ClientString(id = 742, message = "You have failed at adding $c1 to the petition chat. The error code is $s2.")
	public static SystemMessageId YOU_HAVE_FAILED_AT_ADDING_C1_TO_THE_PETITION_CHAT_THE_ERROR_CODE_IS_S2;
	
	@ClientString(id = 743, message = "$c1 left the petition chat.")
	public static SystemMessageId C1_LEFT_THE_PETITION_CHAT;
	
	@ClientString(id = 744, message = "You failed at removing $s1 from the petition chat. The error code is $s2.")
	public static SystemMessageId YOU_FAILED_AT_REMOVING_S1_FROM_THE_PETITION_CHAT_THE_ERROR_CODE_IS_S2;
	
	@ClientString(id = 745, message = "You are currently not in a petition chat.")
	public static SystemMessageId YOU_ARE_CURRENTLY_NOT_IN_A_PETITION_CHAT;
	
	@ClientString(id = 746, message = "It is not currently a petition.")
	public static SystemMessageId IT_IS_NOT_CURRENTLY_A_PETITION;
	
	@ClientString(id = 747, message = "If you need help, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId IF_YOU_NEED_HELP_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 748, message = "The distance is too far and so the casting has been stopped.")
	public static SystemMessageId THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_STOPPED;
	
	@ClientString(id = 749, message = "The effect of $s1 has been removed.")
	public static SystemMessageId THE_EFFECT_OF_S1_HAS_BEEN_REMOVED;
	
	@ClientString(id = 750, message = "There are no other skills to learn.")
	public static SystemMessageId THERE_ARE_NO_OTHER_SKILLS_TO_LEARN;
	
	@ClientString(id = 751, message = "As there is a conflict in the siege relationship with a clan in the alliance, you cannot invite that clan to the alliance.")
	public static SystemMessageId AS_THERE_IS_A_CONFLICT_IN_THE_SIEGE_RELATIONSHIP_WITH_A_CLAN_IN_THE_ALLIANCE_YOU_CANNOT_INVITE_THAT_CLAN_TO_THE_ALLIANCE;
	
	@ClientString(id = 752, message = "That name cannot be used.")
	public static SystemMessageId THAT_NAME_CANNOT_BE_USED;
	
	@ClientString(id = 753, message = "You cannot position mercenaries here.")
	public static SystemMessageId YOU_CANNOT_POSITION_MERCENARIES_HERE;
	
	@ClientString(id = 754, message = "There are $s1 hours and $s2 minutes left in this week's usage time.")
	public static SystemMessageId THERE_ARE_S1_HOURS_AND_S2_MINUTES_LEFT_IN_THIS_WEEK_S_USAGE_TIME;
	
	@ClientString(id = 755, message = "There are $s1 minutes left in this week's usage time.")
	public static SystemMessageId THERE_ARE_S1_MINUTES_LEFT_IN_THIS_WEEK_S_USAGE_TIME;
	
	@ClientString(id = 756, message = "This week's usage time has finished.")
	public static SystemMessageId THIS_WEEK_S_USAGE_TIME_HAS_FINISHED;
	
	@ClientString(id = 757, message = "There are $s1 hours and $s2 minutes left in the fixed use time.")
	public static SystemMessageId THERE_ARE_S1_HOURS_AND_S2_MINUTES_LEFT_IN_THE_FIXED_USE_TIME;
	
	@ClientString(id = 758, message = "There are $s1 hour(s) $s2 minute(s) left in this week's play time.")
	public static SystemMessageId THERE_ARE_S1_HOUR_S_S2_MINUTE_S_LEFT_IN_THIS_WEEK_S_PLAY_TIME;
	
	@ClientString(id = 759, message = "There are $s1 minutes left in this week's play time.")
	public static SystemMessageId THERE_ARE_S1_MINUTES_LEFT_IN_THIS_WEEK_S_PLAY_TIME;
	
	@ClientString(id = 760, message = "$c1 cannot join the clan because one day has not yet passed since they left another clan.")
	public static SystemMessageId C1_CANNOT_JOIN_THE_CLAN_BECAUSE_ONE_DAY_HAS_NOT_YET_PASSED_SINCE_THEY_LEFT_ANOTHER_CLAN;
	
	@ClientString(id = 761, message = "$s1 clan cannot join the alliance because one day has not yet passed since they left another alliance.")
	public static SystemMessageId S1_CLAN_CANNOT_JOIN_THE_ALLIANCE_BECAUSE_ONE_DAY_HAS_NOT_YET_PASSED_SINCE_THEY_LEFT_ANOTHER_ALLIANCE;
	
	@ClientString(id = 762, message = "$c1 rolled $s2 and $s3's eye came out.")
	public static SystemMessageId C1_ROLLED_S2_AND_S3_S_EYE_CAME_OUT;
	
	@ClientString(id = 763, message = "You failed at sending the package because you are too far from the warehouse.")
	public static SystemMessageId YOU_FAILED_AT_SENDING_THE_PACKAGE_BECAUSE_YOU_ARE_TOO_FAR_FROM_THE_WAREHOUSE;
	
	@ClientString(id = 764, message = "You have been playing for an extended period of time. Please consider taking a break.")
	public static SystemMessageId YOU_HAVE_BEEN_PLAYING_FOR_AN_EXTENDED_PERIOD_OF_TIME_PLEASE_CONSIDER_TAKING_A_BREAK;
	
	@ClientString(id = 765, message = "GameGuard is already running. Please try running it again after rebooting.")
	public static SystemMessageId GAMEGUARD_IS_ALREADY_RUNNING_PLEASE_TRY_RUNNING_IT_AGAIN_AFTER_REBOOTING;
	
	@ClientString(id = 766, message = "There is a GameGuard initialization error. Please try running it again after rebooting.")
	public static SystemMessageId THERE_IS_A_GAMEGUARD_INITIALIZATION_ERROR_PLEASE_TRY_RUNNING_IT_AGAIN_AFTER_REBOOTING;
	
	@ClientString(id = 767, message = "The GameGuard file is damaged. Please reinstall GameGuard.")
	public static SystemMessageId THE_GAMEGUARD_FILE_IS_DAMAGED_PLEASE_REINSTALL_GAMEGUARD;
	
	@ClientString(id = 768, message = "A Windows system file is damaged. Please reinstall Internet Explorer.")
	public static SystemMessageId A_WINDOWS_SYSTEM_FILE_IS_DAMAGED_PLEASE_REINSTALL_INTERNET_EXPLORER;
	
	@ClientString(id = 769, message = "A hacking tool has been discovered. Please try playing again after closing unnecessary programs.")
	public static SystemMessageId A_HACKING_TOOL_HAS_BEEN_DISCOVERED_PLEASE_TRY_PLAYING_AGAIN_AFTER_CLOSING_UNNECESSARY_PROGRAMS;
	
	@ClientString(id = 770, message = "The GameGuard update was canceled. Please check your network connection status or firewall.")
	public static SystemMessageId THE_GAMEGUARD_UPDATE_WAS_CANCELED_PLEASE_CHECK_YOUR_NETWORK_CONNECTION_STATUS_OR_FIREWALL;
	
	@ClientString(id = 771, message = "The GameGuard update was canceled. Please try running it again after doing a virus scan or changing the settings in your PC management program.")
	public static SystemMessageId THE_GAMEGUARD_UPDATE_WAS_CANCELED_PLEASE_TRY_RUNNING_IT_AGAIN_AFTER_DOING_A_VIRUS_SCAN_OR_CHANGING_THE_SETTINGS_IN_YOUR_PC_MANAGEMENT_PROGRAM;
	
	@ClientString(id = 772, message = "There was a problem when running GameGuard.")
	public static SystemMessageId THERE_WAS_A_PROBLEM_WHEN_RUNNING_GAMEGUARD;
	
	@ClientString(id = 773, message = "The game or GameGuard files are damaged.")
	public static SystemMessageId THE_GAME_OR_GAMEGUARD_FILES_ARE_DAMAGED;
	
	@ClientString(id = 774, message = "Play time is no longer accumulating.")
	public static SystemMessageId PLAY_TIME_IS_NO_LONGER_ACCUMULATING;
	
	@ClientString(id = 775, message = "From here on, play time will be expended.")
	public static SystemMessageId FROM_HERE_ON_PLAY_TIME_WILL_BE_EXPENDED;
	
	@ClientString(id = 776, message = "The clan hall which was put up for auction has been awarded to $s1 clan.")
	public static SystemMessageId THE_CLAN_HALL_WHICH_WAS_PUT_UP_FOR_AUCTION_HAS_BEEN_AWARDED_TO_S1_CLAN;
	
	@ClientString(id = 777, message = "The clan hall which had been put up for auction was not sold and therefore has been re-listed.")
	public static SystemMessageId THE_CLAN_HALL_WHICH_HAD_BEEN_PUT_UP_FOR_AUCTION_WAS_NOT_SOLD_AND_THEREFORE_HAS_BEEN_RE_LISTED;
	
	@ClientString(id = 778, message = "You may not log out from this location.")
	public static SystemMessageId YOU_MAY_NOT_LOG_OUT_FROM_THIS_LOCATION;
	
	@ClientString(id = 779, message = "You may not restart in this location.")
	public static SystemMessageId YOU_MAY_NOT_RESTART_IN_THIS_LOCATION;
	
	@ClientString(id = 780, message = "Observation is only possible during a siege.")
	public static SystemMessageId OBSERVATION_IS_ONLY_POSSIBLE_DURING_A_SIEGE;
	
	@ClientString(id = 781, message = "Observers cannot participate.")
	public static SystemMessageId OBSERVERS_CANNOT_PARTICIPATE;
	
	@ClientString(id = 782, message = "You may not observe a siege with a pet or servitor summoned.")
	public static SystemMessageId YOU_MAY_NOT_OBSERVE_A_SIEGE_WITH_A_PET_OR_SERVITOR_SUMMONED;
	
	@ClientString(id = 783, message = "Lottery ticket sales have been temporarily suspended.")
	public static SystemMessageId LOTTERY_TICKET_SALES_HAVE_BEEN_TEMPORARILY_SUSPENDED;
	
	@ClientString(id = 784, message = "Tickets for the current lottery are no longer available.")
	public static SystemMessageId TICKETS_FOR_THE_CURRENT_LOTTERY_ARE_NO_LONGER_AVAILABLE;
	
	@ClientString(id = 785, message = "The results of lottery number $s1 have not yet been published.")
	public static SystemMessageId THE_RESULTS_OF_LOTTERY_NUMBER_S1_HAVE_NOT_YET_BEEN_PUBLISHED;
	
	@ClientString(id = 786, message = "Incorrect syntax.")
	public static SystemMessageId INCORRECT_SYNTAX;
	
	@ClientString(id = 787, message = "The tryouts are finished.")
	public static SystemMessageId THE_TRYOUTS_ARE_FINISHED;
	
	@ClientString(id = 788, message = "The finals are finished.")
	public static SystemMessageId THE_FINALS_ARE_FINISHED;
	
	@ClientString(id = 789, message = "The tryouts have begun.")
	public static SystemMessageId THE_TRYOUTS_HAVE_BEGUN;
	
	@ClientString(id = 790, message = "The finals have begun.")
	public static SystemMessageId THE_FINALS_HAVE_BEGUN;
	
	@ClientString(id = 791, message = "The final match is about to begin. Line up!")
	public static SystemMessageId THE_FINAL_MATCH_IS_ABOUT_TO_BEGIN_LINE_UP;
	
	@ClientString(id = 792, message = "The siege of the clan hall is finished.")
	public static SystemMessageId THE_SIEGE_OF_THE_CLAN_HALL_IS_FINISHED;
	
	@ClientString(id = 793, message = "The siege of the clan hall has begun.")
	public static SystemMessageId THE_SIEGE_OF_THE_CLAN_HALL_HAS_BEGUN;
	
	@ClientString(id = 794, message = "You are not authorized to do that.")
	public static SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT;
	
	@ClientString(id = 795, message = "Only clan leaders are authorized to set rights.")
	public static SystemMessageId ONLY_CLAN_LEADERS_ARE_AUTHORIZED_TO_SET_RIGHTS;
	
	@ClientString(id = 796, message = "Your remaining observation time is $s1 minutes.")
	public static SystemMessageId YOUR_REMAINING_OBSERVATION_TIME_IS_S1_MINUTES;
	
	@ClientString(id = 797, message = "You may create up to 48 macros.")
	public static SystemMessageId YOU_MAY_CREATE_UP_TO_48_MACROS;
	
	@ClientString(id = 798, message = "Item registration is irreversible. Do you wish to continue?")
	public static SystemMessageId ITEM_REGISTRATION_IS_IRREVERSIBLE_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 799, message = "The observation time has expired.")
	public static SystemMessageId THE_OBSERVATION_TIME_HAS_EXPIRED;
	
	@ClientString(id = 800, message = "You are too late. The registration period is over.")
	public static SystemMessageId YOU_ARE_TOO_LATE_THE_REGISTRATION_PERIOD_IS_OVER;
	
	@ClientString(id = 801, message = "Registration for the clan hall siege is closed.")
	public static SystemMessageId REGISTRATION_FOR_THE_CLAN_HALL_SIEGE_IS_CLOSED;
	
	@ClientString(id = 802, message = "Petitions are not being accepted at this time. You may submit your petition after $s1 a.m./p.m.")
	public static SystemMessageId PETITIONS_ARE_NOT_BEING_ACCEPTED_AT_THIS_TIME_YOU_MAY_SUBMIT_YOUR_PETITION_AFTER_S1_A_M_P_M;
	
	@ClientString(id = 803, message = "Enter the specifics of your petition.")
	public static SystemMessageId ENTER_THE_SPECIFICS_OF_YOUR_PETITION;
	
	@ClientString(id = 804, message = "Select your type and check the FAQ content.")
	public static SystemMessageId SELECT_YOUR_TYPE_AND_CHECK_THE_FAQ_CONTENT;
	
	@ClientString(id = 805, message = "Petitions are not being accepted at this time. You may submit your petition after $s1 a.m./p.m.")
	public static SystemMessageId PETITIONS_ARE_NOT_BEING_ACCEPTED_AT_THIS_TIME_YOU_MAY_SUBMIT_YOUR_PETITION_AFTER_S1_A_M_P_M_2;
	
	@ClientString(id = 806, message = "If you are unable to move, try typing '/unstuck'.")
	public static SystemMessageId IF_YOU_ARE_UNABLE_TO_MOVE_TRY_TYPING_UNSTUCK;
	
	@ClientString(id = 807, message = "This terrain is navigable. Prepare for transport to the nearest village.")
	public static SystemMessageId THIS_TERRAIN_IS_NAVIGABLE_PREPARE_FOR_TRANSPORT_TO_THE_NEAREST_VILLAGE;
	
	@ClientString(id = 808, message = "You are stuck. You may submit a petition by typing '/gm'.")
	public static SystemMessageId YOU_ARE_STUCK_YOU_MAY_SUBMIT_A_PETITION_BY_TYPING_GM;
	
	@ClientString(id = 809, message = "You are stuck. You will be transported to the nearest village in five minutes.")
	public static SystemMessageId YOU_ARE_STUCK_YOU_WILL_BE_TRANSPORTED_TO_THE_NEAREST_VILLAGE_IN_FIVE_MINUTES;
	
	@ClientString(id = 810, message = "Invalid macro. Refer to the Help file for instructions.")
	public static SystemMessageId INVALID_MACRO_REFER_TO_THE_HELP_FILE_FOR_INSTRUCTIONS;
	
	@ClientString(id = 811, message = "You have requested a teleport to ($s1). Do you wish to continue?")
	public static SystemMessageId YOU_HAVE_REQUESTED_A_TELEPORT_TO_S1_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 812, message = "The secret trap has inflicted $s1 damage on you.")
	public static SystemMessageId THE_SECRET_TRAP_HAS_INFLICTED_S1_DAMAGE_ON_YOU;
	
	@ClientString(id = 813, message = "You have been poisoned by a Secret Trap.")
	public static SystemMessageId YOU_HAVE_BEEN_POISONED_BY_A_SECRET_TRAP;
	
	@ClientString(id = 814, message = "Your speed has been decreased by a Secret Trap.")
	public static SystemMessageId YOUR_SPEED_HAS_BEEN_DECREASED_BY_A_SECRET_TRAP;
	
	@ClientString(id = 815, message = "The tryouts are about to begin. Line up!")
	public static SystemMessageId THE_TRYOUTS_ARE_ABOUT_TO_BEGIN_LINE_UP;
	
	@ClientString(id = 816, message = "Tickets are now available for Monster Race $s1!")
	public static SystemMessageId TICKETS_ARE_NOW_AVAILABLE_FOR_MONSTER_RACE_S1;
	
	@ClientString(id = 817, message = "Now selling tickets for Monster Race $s1!")
	public static SystemMessageId NOW_SELLING_TICKETS_FOR_MONSTER_RACE_S1;
	
	@ClientString(id = 818, message = "Ticket sales for the Monster Race will end in $s1 minute(s).")
	public static SystemMessageId TICKET_SALES_FOR_THE_MONSTER_RACE_WILL_END_IN_S1_MINUTE_S;
	
	@ClientString(id = 819, message = "Tickets sales are closed for Monster Race $s1. Odds are posted.")
	public static SystemMessageId TICKETS_SALES_ARE_CLOSED_FOR_MONSTER_RACE_S1_ODDS_ARE_POSTED;
	
	@ClientString(id = 820, message = "Monster Race $s2 will begin in $s1 minute(s)!")
	public static SystemMessageId MONSTER_RACE_S2_WILL_BEGIN_IN_S1_MINUTE_S;
	
	@ClientString(id = 821, message = "Monster Race $s1 will begin in 30 seconds!")
	public static SystemMessageId MONSTER_RACE_S1_WILL_BEGIN_IN_30_SECONDS;
	
	@ClientString(id = 822, message = "Monster Race $s1 is about to begin! Countdown in five seconds!")
	public static SystemMessageId MONSTER_RACE_S1_IS_ABOUT_TO_BEGIN_COUNTDOWN_IN_FIVE_SECONDS;
	
	@ClientString(id = 823, message = "The race will begin in $s1 second(s)!")
	public static SystemMessageId THE_RACE_WILL_BEGIN_IN_S1_SECOND_S;
	
	@ClientString(id = 824, message = "They're off!")
	public static SystemMessageId THEY_RE_OFF;
	
	@ClientString(id = 825, message = "Monster Race $s1 is finished!")
	public static SystemMessageId MONSTER_RACE_S1_IS_FINISHED;
	
	@ClientString(id = 826, message = "First prize goes to the player in lane $s1. Second prize goes to the player in lane $s2.")
	public static SystemMessageId FIRST_PRIZE_GOES_TO_THE_PLAYER_IN_LANE_S1_SECOND_PRIZE_GOES_TO_THE_PLAYER_IN_LANE_S2;
	
	@ClientString(id = 827, message = "You may not impose a block on a GM.")
	public static SystemMessageId YOU_MAY_NOT_IMPOSE_A_BLOCK_ON_A_GM;
	
	@ClientString(id = 828, message = "Are you sure you wish to delete the $s1 macro?")
	public static SystemMessageId ARE_YOU_SURE_YOU_WISH_TO_DELETE_THE_S1_MACRO;
	
	@ClientString(id = 829, message = "You cannot recommend yourself.")
	public static SystemMessageId YOU_CANNOT_RECOMMEND_YOURSELF;
	
	@ClientString(id = 830, message = "You have recommended $c1. You have $s2 recommendations left.")
	public static SystemMessageId YOU_HAVE_RECOMMENDED_C1_YOU_HAVE_S2_RECOMMENDATIONS_LEFT;
	
	@ClientString(id = 831, message = "You have been recommended by $c1.")
	public static SystemMessageId YOU_HAVE_BEEN_RECOMMENDED_BY_C1;
	
	@ClientString(id = 832, message = "That character has already been recommended.")
	public static SystemMessageId THAT_CHARACTER_HAS_ALREADY_BEEN_RECOMMENDED;
	
	@ClientString(id = 833, message = "You are not authorized to make further recommendations at this time. You will receive more recommendation credits each day at 1 p.m.")
	public static SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_MAKE_FURTHER_RECOMMENDATIONS_AT_THIS_TIME_YOU_WILL_RECEIVE_MORE_RECOMMENDATION_CREDITS_EACH_DAY_AT_1_P_M;
	
	@ClientString(id = 834, message = "$c1 has rolled a $s2.")
	public static SystemMessageId C1_HAS_ROLLED_A_S2;
	
	@ClientString(id = 835, message = "You may not throw the dice at this time. Try again later.")
	public static SystemMessageId YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIME_TRY_AGAIN_LATER;
	
	@ClientString(id = 836, message = "You have exceeded your inventory volume limit and cannot take this item.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_ITEM;
	
	@ClientString(id = 837, message = "Macro descriptions may contain up to 32 characters.")
	public static SystemMessageId MACRO_DESCRIPTIONS_MAY_CONTAIN_UP_TO_32_CHARACTERS;
	
	@ClientString(id = 838, message = "Enter the name of the macro.")
	public static SystemMessageId ENTER_THE_NAME_OF_THE_MACRO;
	
	@ClientString(id = 839, message = "That name is already assigned to another macro.")
	public static SystemMessageId THAT_NAME_IS_ALREADY_ASSIGNED_TO_ANOTHER_MACRO;
	
	@ClientString(id = 840, message = "That recipe is already registered.")
	public static SystemMessageId THAT_RECIPE_IS_ALREADY_REGISTERED;
	
	@ClientString(id = 841, message = "No further recipes may be registered.")
	public static SystemMessageId NO_FURTHER_RECIPES_MAY_BE_REGISTERED;
	
	@ClientString(id = 842, message = "You are not authorized to register a recipe.")
	public static SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_REGISTER_A_RECIPE;
	
	@ClientString(id = 843, message = "The siege of $s1 is finished.")
	public static SystemMessageId THE_SIEGE_OF_S1_IS_FINISHED;
	
	@ClientString(id = 844, message = "The siege to conquer $s1 has begun.")
	public static SystemMessageId THE_SIEGE_TO_CONQUER_S1_HAS_BEGUN;
	
	@ClientString(id = 845, message = "The deadline to register for the siege of $s1 has passed.")
	public static SystemMessageId THE_DEADLINE_TO_REGISTER_FOR_THE_SIEGE_OF_S1_HAS_PASSED;
	
	@ClientString(id = 846, message = "The siege of $s1 has been canceled due to lack of interest.")
	public static SystemMessageId THE_SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST;
	
	@ClientString(id = 847, message = "A clan that owns a clan hall may not participate in a clan hall siege.")
	public static SystemMessageId A_CLAN_THAT_OWNS_A_CLAN_HALL_MAY_NOT_PARTICIPATE_IN_A_CLAN_HALL_SIEGE;
	
	@ClientString(id = 848, message = "$s1 has been deleted.")
	public static SystemMessageId S1_HAS_BEEN_DELETED;
	
	@ClientString(id = 849, message = "$s1 cannot be found.")
	public static SystemMessageId S1_CANNOT_BE_FOUND;
	
	@ClientString(id = 850, message = "$s1 already exists.")
	public static SystemMessageId S1_ALREADY_EXISTS_2;
	
	@ClientString(id = 851, message = "$s1 has been added.")
	public static SystemMessageId S1_HAS_BEEN_ADDED;
	
	@ClientString(id = 852, message = "The recipe is incorrect.")
	public static SystemMessageId THE_RECIPE_IS_INCORRECT;
	
	@ClientString(id = 853, message = "You may not alter your recipe book while engaged in manufacturing.")
	public static SystemMessageId YOU_MAY_NOT_ALTER_YOUR_RECIPE_BOOK_WHILE_ENGAGED_IN_MANUFACTURING;
	
	@ClientString(id = 854, message = "You are missing $s2 $s1 required to create that.")
	public static SystemMessageId YOU_ARE_MISSING_S2_S1_REQUIRED_TO_CREATE_THAT;
	
	@ClientString(id = 855, message = "$s1 clan has defeated $s2.")
	public static SystemMessageId S1_CLAN_HAS_DEFEATED_S2;
	
	@ClientString(id = 856, message = "The siege of $s1 has ended in a draw.")
	public static SystemMessageId THE_SIEGE_OF_S1_HAS_ENDED_IN_A_DRAW;
	
	@ClientString(id = 857, message = "$s1 clan has won in the preliminary match of $s2.")
	public static SystemMessageId S1_CLAN_HAS_WON_IN_THE_PRELIMINARY_MATCH_OF_S2;
	
	@ClientString(id = 858, message = "The preliminary match of $s1 has ended in a draw.")
	public static SystemMessageId THE_PRELIMINARY_MATCH_OF_S1_HAS_ENDED_IN_A_DRAW;
	
	@ClientString(id = 859, message = "Please register a recipe.")
	public static SystemMessageId PLEASE_REGISTER_A_RECIPE;
	
	@ClientString(id = 860, message = "You may not build your headquarters in close proximity to another headquarters.")
	public static SystemMessageId YOU_MAY_NOT_BUILD_YOUR_HEADQUARTERS_IN_CLOSE_PROXIMITY_TO_ANOTHER_HEADQUARTERS;
	
	@ClientString(id = 861, message = "You have exceeded the maximum number of memos.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_THE_MAXIMUM_NUMBER_OF_MEMOS;
	
	@ClientString(id = 862, message = "Odds are not posted until ticket sales have closed.")
	public static SystemMessageId ODDS_ARE_NOT_POSTED_UNTIL_TICKET_SALES_HAVE_CLOSED;
	
	@ClientString(id = 863, message = "You feel the energy of fire.")
	public static SystemMessageId YOU_FEEL_THE_ENERGY_OF_FIRE;
	
	@ClientString(id = 864, message = "You feel the energy of water.")
	public static SystemMessageId YOU_FEEL_THE_ENERGY_OF_WATER;
	
	@ClientString(id = 865, message = "You feel the energy of wind.")
	public static SystemMessageId YOU_FEEL_THE_ENERGY_OF_WIND;
	
	@ClientString(id = 866, message = "You may no longer gather energy.")
	public static SystemMessageId YOU_MAY_NO_LONGER_GATHER_ENERGY;
	
	@ClientString(id = 867, message = "The energy is depleted.")
	public static SystemMessageId THE_ENERGY_IS_DEPLETED;
	
	@ClientString(id = 868, message = "The energy of fire has been delivered.")
	public static SystemMessageId THE_ENERGY_OF_FIRE_HAS_BEEN_DELIVERED;
	
	@ClientString(id = 869, message = "The energy of water has been delivered.")
	public static SystemMessageId THE_ENERGY_OF_WATER_HAS_BEEN_DELIVERED;
	
	@ClientString(id = 870, message = "The energy of wind has been delivered.")
	public static SystemMessageId THE_ENERGY_OF_WIND_HAS_BEEN_DELIVERED;
	
	@ClientString(id = 871, message = "The seed has been sown.")
	public static SystemMessageId THE_SEED_HAS_BEEN_SOWN;
	
	@ClientString(id = 872, message = "This seed may not be sown here.")
	public static SystemMessageId THIS_SEED_MAY_NOT_BE_SOWN_HERE;
	
	@ClientString(id = 873, message = "That character does not exist.")
	public static SystemMessageId THAT_CHARACTER_DOES_NOT_EXIST;
	
	@ClientString(id = 874, message = "The capacity of the warehouse has been exceeded.")
	public static SystemMessageId THE_CAPACITY_OF_THE_WAREHOUSE_HAS_BEEN_EXCEEDED;
	
	@ClientString(id = 875, message = "The transport of the cargo has been canceled.")
	public static SystemMessageId THE_TRANSPORT_OF_THE_CARGO_HAS_BEEN_CANCELED;
	
	@ClientString(id = 876, message = "The cargo was not delivered.")
	public static SystemMessageId THE_CARGO_WAS_NOT_DELIVERED;
	
	@ClientString(id = 877, message = "The symbol has been added.")
	public static SystemMessageId THE_SYMBOL_HAS_BEEN_ADDED;
	
	@ClientString(id = 878, message = "The symbol has been deleted.")
	public static SystemMessageId THE_SYMBOL_HAS_BEEN_DELETED;
	
	@ClientString(id = 879, message = "The manor system is currently under maintenance.")
	public static SystemMessageId THE_MANOR_SYSTEM_IS_CURRENTLY_UNDER_MAINTENANCE;
	
	@ClientString(id = 880, message = "The transaction is complete.")
	public static SystemMessageId THE_TRANSACTION_IS_COMPLETE;
	
	@ClientString(id = 881, message = "There is a discrepancy on the invoice.")
	public static SystemMessageId THERE_IS_A_DISCREPANCY_ON_THE_INVOICE;
	
	@ClientString(id = 882, message = "The seed quantity is incorrect.")
	public static SystemMessageId THE_SEED_QUANTITY_IS_INCORRECT;
	
	@ClientString(id = 883, message = "The seed information is incorrect.")
	public static SystemMessageId THE_SEED_INFORMATION_IS_INCORRECT;
	
	@ClientString(id = 884, message = "The manor information has been updated.")
	public static SystemMessageId THE_MANOR_INFORMATION_HAS_BEEN_UPDATED;
	
	@ClientString(id = 885, message = "The number of crops is incorrect.")
	public static SystemMessageId THE_NUMBER_OF_CROPS_IS_INCORRECT;
	
	@ClientString(id = 886, message = "The crops are priced incorrectly.")
	public static SystemMessageId THE_CROPS_ARE_PRICED_INCORRECTLY;
	
	@ClientString(id = 887, message = "The type is incorrect.")
	public static SystemMessageId THE_TYPE_IS_INCORRECT;
	
	@ClientString(id = 888, message = "No crops can be purchased at this time.")
	public static SystemMessageId NO_CROPS_CAN_BE_PURCHASED_AT_THIS_TIME;
	
	@ClientString(id = 889, message = "The seed was successfully sown.")
	public static SystemMessageId THE_SEED_WAS_SUCCESSFULLY_SOWN;
	
	@ClientString(id = 890, message = "The seed was not sown.")
	public static SystemMessageId THE_SEED_WAS_NOT_SOWN;
	
	@ClientString(id = 891, message = "You are not authorized to harvest.")
	public static SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_HARVEST;
	
	@ClientString(id = 892, message = "The harvest has failed.")
	public static SystemMessageId THE_HARVEST_HAS_FAILED;
	
	@ClientString(id = 893, message = "The harvest failed because the seed was not sown.")
	public static SystemMessageId THE_HARVEST_FAILED_BECAUSE_THE_SEED_WAS_NOT_SOWN;
	
	@ClientString(id = 894, message = "Up to $s1 recipes can be registered.")
	public static SystemMessageId UP_TO_S1_RECIPES_CAN_BE_REGISTERED;
	
	@ClientString(id = 895, message = "No recipes have been registered.")
	public static SystemMessageId NO_RECIPES_HAVE_BEEN_REGISTERED;
	
	@ClientString(id = 896, message = "Quest recipes can not be registered.")
	public static SystemMessageId QUEST_RECIPES_CAN_NOT_BE_REGISTERED;
	
	@ClientString(id = 897, message = "The fee to create the item is incorrect.")
	public static SystemMessageId THE_FEE_TO_CREATE_THE_ITEM_IS_INCORRECT;
	
	@ClientString(id = 898, message = "Only characters of level 10 or above are authorized to make recommendations.")
	public static SystemMessageId ONLY_CHARACTERS_OF_LEVEL_10_OR_ABOVE_ARE_AUTHORIZED_TO_MAKE_RECOMMENDATIONS;
	
	@ClientString(id = 899, message = "The symbol cannot be drawn.")
	public static SystemMessageId THE_SYMBOL_CANNOT_BE_DRAWN;
	
	@ClientString(id = 900, message = "No slot exists to draw the symbol.")
	public static SystemMessageId NO_SLOT_EXISTS_TO_DRAW_THE_SYMBOL;
	
	@ClientString(id = 901, message = "The symbol information cannot be found.")
	public static SystemMessageId THE_SYMBOL_INFORMATION_CANNOT_BE_FOUND;
	
	@ClientString(id = 902, message = "You don't possess the correct number of items.")
	public static SystemMessageId YOU_DON_T_POSSESS_THE_CORRECT_NUMBER_OF_ITEMS;
	
	@ClientString(id = 903, message = "You may not submit a petition while frozen. Be patient.")
	public static SystemMessageId YOU_MAY_NOT_SUBMIT_A_PETITION_WHILE_FROZEN_BE_PATIENT;
	
	@ClientString(id = 904, message = "Items cannot be discarded while in a private store.")
	public static SystemMessageId ITEMS_CANNOT_BE_DISCARDED_WHILE_IN_A_PRIVATE_STORE;
	
	@ClientString(id = 905, message = "The current score for the Humans is $s1.")
	public static SystemMessageId THE_CURRENT_SCORE_FOR_THE_HUMANS_IS_S1;
	
	@ClientString(id = 906, message = "The current score for the Elves is $s1.")
	public static SystemMessageId THE_CURRENT_SCORE_FOR_THE_ELVES_IS_S1;
	
	@ClientString(id = 907, message = "The current score for the Dark Elves is $s1.")
	public static SystemMessageId THE_CURRENT_SCORE_FOR_THE_DARK_ELVES_IS_S1;
	
	@ClientString(id = 908, message = "The current score for the Orcs is $s1.")
	public static SystemMessageId THE_CURRENT_SCORE_FOR_THE_ORCS_IS_S1;
	
	@ClientString(id = 909, message = "The current score for the Dwarves is $s1.")
	public static SystemMessageId THE_CURRENT_SCORE_FOR_THE_DWARVES_IS_S1;
	
	@ClientString(id = 910, message = "Current location : $s1, $s2, $s3 (Near Talking Island Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_TALKING_ISLAND_VILLAGE;
	
	@ClientString(id = 911, message = "Current location : $s1, $s2, $s3 (Near Gludin Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_GLUDIN_VILLAGE;
	
	@ClientString(id = 912, message = "Current location : $s1, $s2, $s3 (Near the Town of Gludio)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_TOWN_OF_GLUDIO;
	
	@ClientString(id = 913, message = "Current location : $s1, $s2, $s3 (Near the Neutral Zone)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_NEUTRAL_ZONE;
	
	@ClientString(id = 914, message = "Current location : $s1, $s2, $s3 (Near the Elven Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_ELVEN_VILLAGE;
	
	@ClientString(id = 915, message = "Current location : $s1, $s2, $s3 (Near the Dark Elf Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_DARK_ELF_VILLAGE;
	
	@ClientString(id = 916, message = "Current location : $s1, $s2, $s3 (Near the Town of Dion)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_TOWN_OF_DION;
	
	@ClientString(id = 917, message = "Current location : $s1, $s2, $s3 (Near the Floran Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_FLORAN_VILLAGE;
	
	@ClientString(id = 918, message = "Current location : $s1, $s2, $s3 (Near the Town of Giran)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_TOWN_OF_GIRAN;
	
	@ClientString(id = 919, message = "Current location : $s1, $s2, $s3 (Near Giran Harbor)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_GIRAN_HARBOR;
	
	@ClientString(id = 920, message = "Current location : $s1, $s2, $s3 (Near the Orc Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_ORC_VILLAGE;
	
	@ClientString(id = 921, message = "Current location : $s1, $s2, $s3 (Near the Dwarven Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_DWARVEN_VILLAGE;
	
	@ClientString(id = 922, message = "Current location : $s1, $s2, $s3 (Near the Town of Oren)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_TOWN_OF_OREN;
	
	@ClientString(id = 923, message = "Current location : $s1, $s2, $s3 (Near Hunters Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_HUNTERS_VILLAGE;
	
	@ClientString(id = 924, message = "Current location : $s1, $s2, $s3 (Near Aden Castle Town)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_ADEN_CASTLE_TOWN;
	
	@ClientString(id = 925, message = "Current location : $s1, $s2, $s3 (Near the Coliseum)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_COLISEUM;
	
	@ClientString(id = 926, message = "Current location : $s1, $s2, $s3 (Near Heine)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_HEINE;
	
	@ClientString(id = 927, message = "The current time is $s1:$s2.")
	public static SystemMessageId THE_CURRENT_TIME_IS_S1_S2;
	
	@ClientString(id = 928, message = "The current time is $s1:$s2.")
	public static SystemMessageId THE_CURRENT_TIME_IS_S1_S2_2;
	
	@ClientString(id = 929, message = "No compensation was given for the farm products.")
	public static SystemMessageId NO_COMPENSATION_WAS_GIVEN_FOR_THE_FARM_PRODUCTS;
	
	@ClientString(id = 930, message = "Lottery tickets are not currently being sold.")
	public static SystemMessageId LOTTERY_TICKETS_ARE_NOT_CURRENTLY_BEING_SOLD;
	
	@ClientString(id = 931, message = "The winning lottery ticket numbers have not yet been announced.")
	public static SystemMessageId THE_WINNING_LOTTERY_TICKET_NUMBERS_HAVE_NOT_YET_BEEN_ANNOUNCED;
	
	@ClientString(id = 932, message = "You cannot chat while in observation mode.")
	public static SystemMessageId YOU_CANNOT_CHAT_WHILE_IN_OBSERVATION_MODE;
	
	@ClientString(id = 933, message = "The seed pricing greatly differs from standard seed prices.")
	public static SystemMessageId THE_SEED_PRICING_GREATLY_DIFFERS_FROM_STANDARD_SEED_PRICES;
	
	@ClientString(id = 934, message = "It is a deleted recipe.")
	public static SystemMessageId IT_IS_A_DELETED_RECIPE;
	
	@ClientString(id = 935, message = "You do not have enough funds in the clan warehouse for the Manor to operate.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_FUNDS_IN_THE_CLAN_WAREHOUSE_FOR_THE_MANOR_TO_OPERATE;
	
	@ClientString(id = 936, message = "Use $s1.")
	public static SystemMessageId USE_S1;
	
	@ClientString(id = 937, message = "Currently preparing for private workshop.")
	public static SystemMessageId CURRENTLY_PREPARING_FOR_PRIVATE_WORKSHOP;
	
	@ClientString(id = 938, message = "The community server is currently offline.")
	public static SystemMessageId THE_COMMUNITY_SERVER_IS_CURRENTLY_OFFLINE;
	
	@ClientString(id = 939, message = "You cannot exchange while blocking everything.")
	public static SystemMessageId YOU_CANNOT_EXCHANGE_WHILE_BLOCKING_EVERYTHING;
	
	@ClientString(id = 940, message = "$s1 is blocking everything.")
	public static SystemMessageId S1_IS_BLOCKING_EVERYTHING;
	
	@ClientString(id = 941, message = "Restart at Talking Island Village.")
	public static SystemMessageId RESTART_AT_TALKING_ISLAND_VILLAGE;
	
	@ClientString(id = 942, message = "Restart at Gludin Village.")
	public static SystemMessageId RESTART_AT_GLUDIN_VILLAGE;
	
	@ClientString(id = 943, message = "Restart at the Town of Gludin.")
	public static SystemMessageId RESTART_AT_THE_TOWN_OF_GLUDIN;
	
	@ClientString(id = 944, message = "Restart at the Neutral Zone.")
	public static SystemMessageId RESTART_AT_THE_NEUTRAL_ZONE;
	
	@ClientString(id = 945, message = "Restart at the Elven Village.")
	public static SystemMessageId RESTART_AT_THE_ELVEN_VILLAGE;
	
	@ClientString(id = 946, message = "Restart at the Dark Elf Village.")
	public static SystemMessageId RESTART_AT_THE_DARK_ELF_VILLAGE;
	
	@ClientString(id = 947, message = "Restart at the Town of Dion.")
	public static SystemMessageId RESTART_AT_THE_TOWN_OF_DION;
	
	@ClientString(id = 948, message = "Restart at Floran Village.")
	public static SystemMessageId RESTART_AT_FLORAN_VILLAGE;
	
	@ClientString(id = 949, message = "Restart at the Town of Giran.")
	public static SystemMessageId RESTART_AT_THE_TOWN_OF_GIRAN;
	
	@ClientString(id = 950, message = "Restart at Giran Harbor.")
	public static SystemMessageId RESTART_AT_GIRAN_HARBOR;
	
	@ClientString(id = 951, message = "Restart at the Orc Village.")
	public static SystemMessageId RESTART_AT_THE_ORC_VILLAGE;
	
	@ClientString(id = 952, message = "Restart at the Dwarven Village.")
	public static SystemMessageId RESTART_AT_THE_DWARVEN_VILLAGE;
	
	@ClientString(id = 953, message = "Restart at the Town of Oren.")
	public static SystemMessageId RESTART_AT_THE_TOWN_OF_OREN;
	
	@ClientString(id = 954, message = "Restart at Hunters Village.")
	public static SystemMessageId RESTART_AT_HUNTERS_VILLAGE;
	
	@ClientString(id = 955, message = "Restart at the Town of Aden.")
	public static SystemMessageId RESTART_AT_THE_TOWN_OF_ADEN;
	
	@ClientString(id = 956, message = "Restart at the Coliseum.")
	public static SystemMessageId RESTART_AT_THE_COLISEUM;
	
	@ClientString(id = 957, message = "Restart at Heine.")
	public static SystemMessageId RESTART_AT_HEINE;
	
	@ClientString(id = 958, message = "Items cannot be discarded or destroyed while operating a private store or workshop.")
	public static SystemMessageId ITEMS_CANNOT_BE_DISCARDED_OR_DESTROYED_WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP;
	
	@ClientString(id = 959, message = "$s1 (*$s2) manufactured successfully.")
	public static SystemMessageId S1_S2_MANUFACTURED_SUCCESSFULLY;
	
	@ClientString(id = 960, message = "You failed to manufacture $s1.")
	public static SystemMessageId YOU_FAILED_TO_MANUFACTURE_S1;
	
	@ClientString(id = 961, message = "You are now blocking everything.")
	public static SystemMessageId YOU_ARE_NOW_BLOCKING_EVERYTHING;
	
	@ClientString(id = 962, message = "You are no longer blocking everything.")
	public static SystemMessageId YOU_ARE_NO_LONGER_BLOCKING_EVERYTHING;
	
	@ClientString(id = 963, message = "Please determine the manufacturing price.")
	public static SystemMessageId PLEASE_DETERMINE_THE_MANUFACTURING_PRICE;
	
	@ClientString(id = 964, message = "Chatting is prohibited for one minute.")
	public static SystemMessageId CHATTING_IS_PROHIBITED_FOR_ONE_MINUTE;
	
	@ClientString(id = 965, message = "The chatting prohibition has been removed.")
	public static SystemMessageId THE_CHATTING_PROHIBITION_HAS_BEEN_REMOVED;
	
	@ClientString(id = 966, message = "Chatting is currently prohibited. If you try to chat before the prohibition is removed, the prohibition time will increase even further.")
	public static SystemMessageId CHATTING_IS_CURRENTLY_PROHIBITED_IF_YOU_TRY_TO_CHAT_BEFORE_THE_PROHIBITION_IS_REMOVED_THE_PROHIBITION_TIME_WILL_INCREASE_EVEN_FURTHER;
	
	@ClientString(id = 967, message = "Do you accept $c1's party invitation? (Item Distribution: Random including spoil.)")
	public static SystemMessageId DO_YOU_ACCEPT_C1_S_PARTY_INVITATION_ITEM_DISTRIBUTION_RANDOM_INCLUDING_SPOIL;
	
	@ClientString(id = 968, message = "Do you accept $c1's party invitation? (Item Distribution: By Turn.)")
	public static SystemMessageId DO_YOU_ACCEPT_C1_S_PARTY_INVITATION_ITEM_DISTRIBUTION_BY_TURN;
	
	@ClientString(id = 969, message = "Do you accept $c1's party invitation? (Item Distribution: By Turn including spoil.)")
	public static SystemMessageId DO_YOU_ACCEPT_C1_S_PARTY_INVITATION_ITEM_DISTRIBUTION_BY_TURN_INCLUDING_SPOIL;
	
	@ClientString(id = 970, message = "$s2's MP has been drained by $c1.")
	public static SystemMessageId S2_S_MP_HAS_BEEN_DRAINED_BY_C1;
	
	@ClientString(id = 971, message = "Petitions cannot exceed 255 characters.")
	public static SystemMessageId PETITIONS_CANNOT_EXCEED_255_CHARACTERS;
	
	@ClientString(id = 972, message = "This pet cannot use this item.")
	public static SystemMessageId THIS_PET_CANNOT_USE_THIS_ITEM;
	
	@ClientString(id = 973, message = "Please input no more than the number you have.")
	public static SystemMessageId PLEASE_INPUT_NO_MORE_THAN_THE_NUMBER_YOU_HAVE;
	
	@ClientString(id = 974, message = "The soul crystal succeeded in absorbing a soul.")
	public static SystemMessageId THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL;
	
	@ClientString(id = 975, message = "The soul crystal was not able to absorb the soul.")
	public static SystemMessageId THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL;
	
	@ClientString(id = 976, message = "The soul crystal broke because it was not able to endure the soul energy.")
	public static SystemMessageId THE_SOUL_CRYSTAL_BROKE_BECAUSE_IT_WAS_NOT_ABLE_TO_ENDURE_THE_SOUL_ENERGY;
	
	@ClientString(id = 977, message = "The soul crystal caused resonation and failed at absorbing a soul.")
	public static SystemMessageId THE_SOUL_CRYSTAL_CAUSED_RESONATION_AND_FAILED_AT_ABSORBING_A_SOUL;
	
	@ClientString(id = 978, message = "The soul crystal is refusing to absorb the soul.")
	public static SystemMessageId THE_SOUL_CRYSTAL_IS_REFUSING_TO_ABSORB_THE_SOUL;
	
	@ClientString(id = 979, message = "The ferry has arrived at Talking Island Harbor.")
	public static SystemMessageId THE_FERRY_HAS_ARRIVED_AT_TALKING_ISLAND_HARBOR;
	
	@ClientString(id = 980, message = "The ferry will leave for Gludin Harbor after anchoring for ten minutes.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_GLUDIN_HARBOR_AFTER_ANCHORING_FOR_TEN_MINUTES;
	
	@ClientString(id = 981, message = "The ferry will leave for Gludin Harbor in five minutes.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_GLUDIN_HARBOR_IN_FIVE_MINUTES;
	
	@ClientString(id = 982, message = "The ferry will leave for Gludin Harbor in one minute.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_GLUDIN_HARBOR_IN_ONE_MINUTE;
	
	@ClientString(id = 983, message = "Those wishing to ride the ferry should make haste to get on.")
	public static SystemMessageId THOSE_WISHING_TO_RIDE_THE_FERRY_SHOULD_MAKE_HASTE_TO_GET_ON;
	
	@ClientString(id = 984, message = "The ferry will be leaving soon for Gludin Harbor.")
	public static SystemMessageId THE_FERRY_WILL_BE_LEAVING_SOON_FOR_GLUDIN_HARBOR;
	
	@ClientString(id = 985, message = "The ferry is leaving for Gludin Harbor.")
	public static SystemMessageId THE_FERRY_IS_LEAVING_FOR_GLUDIN_HARBOR;
	
	@ClientString(id = 986, message = "The ferry has arrived at Gludin Harbor.")
	public static SystemMessageId THE_FERRY_HAS_ARRIVED_AT_GLUDIN_HARBOR;
	
	@ClientString(id = 987, message = "The ferry will leave for Talking Island Harbor after anchoring for ten minutes.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_TALKING_ISLAND_HARBOR_AFTER_ANCHORING_FOR_TEN_MINUTES;
	
	@ClientString(id = 988, message = "The ferry will leave for Talking Island Harbor in five minutes.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_TALKING_ISLAND_HARBOR_IN_FIVE_MINUTES;
	
	@ClientString(id = 989, message = "The ferry will leave for Talking Island Harbor in one minute.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_TALKING_ISLAND_HARBOR_IN_ONE_MINUTE;
	
	@ClientString(id = 990, message = "The ferry will be leaving soon for Talking Island Harbor.")
	public static SystemMessageId THE_FERRY_WILL_BE_LEAVING_SOON_FOR_TALKING_ISLAND_HARBOR;
	
	@ClientString(id = 991, message = "The ferry is leaving for Talking Island Harbor.")
	public static SystemMessageId THE_FERRY_IS_LEAVING_FOR_TALKING_ISLAND_HARBOR;
	
	@ClientString(id = 992, message = "The ferry has arrived at Giran Harbor.")
	public static SystemMessageId THE_FERRY_HAS_ARRIVED_AT_GIRAN_HARBOR;
	
	@ClientString(id = 993, message = "The ferry will leave for Giran Harbor after anchoring for ten minutes.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_GIRAN_HARBOR_AFTER_ANCHORING_FOR_TEN_MINUTES;
	
	@ClientString(id = 994, message = "The ferry will leave for Giran Harbor in five minutes.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_GIRAN_HARBOR_IN_FIVE_MINUTES;
	
	@ClientString(id = 995, message = "The ferry will leave for Giran Harbor in one minute.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_GIRAN_HARBOR_IN_ONE_MINUTE;
	
	@ClientString(id = 996, message = "The ferry will be leaving soon for Giran Harbor.")
	public static SystemMessageId THE_FERRY_WILL_BE_LEAVING_SOON_FOR_GIRAN_HARBOR;
	
	@ClientString(id = 997, message = "The ferry is leaving for Giran Harbor.")
	public static SystemMessageId THE_FERRY_IS_LEAVING_FOR_GIRAN_HARBOR;
	
	@ClientString(id = 998, message = "The Innadril pleasure boat has arrived. It will anchor for ten minutes.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_HAS_ARRIVED_IT_WILL_ANCHOR_FOR_TEN_MINUTES;
	
	@ClientString(id = 999, message = "The Innadril pleasure boat will leave in five minutes.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_WILL_LEAVE_IN_FIVE_MINUTES;
	
	@ClientString(id = 1000, message = "The Innadril pleasure boat will leave in one minute.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_WILL_LEAVE_IN_ONE_MINUTE;
	
	@ClientString(id = 1001, message = "The Innadril pleasure boat will be leaving soon.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_WILL_BE_LEAVING_SOON;
	
	@ClientString(id = 1002, message = "The Innadril pleasure boat is leaving.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_IS_LEAVING;
	
	@ClientString(id = 1003, message = "Cannot process a monster race ticket.")
	public static SystemMessageId CANNOT_PROCESS_A_MONSTER_RACE_TICKET;
	
	@ClientString(id = 1004, message = "You have registered for a clan hall auction.")
	public static SystemMessageId YOU_HAVE_REGISTERED_FOR_A_CLAN_HALL_AUCTION;
	
	@ClientString(id = 1005, message = "There is not enough adena in the clan hall warehouse.")
	public static SystemMessageId THERE_IS_NOT_ENOUGH_ADENA_IN_THE_CLAN_HALL_WAREHOUSE;
	
	@ClientString(id = 1006, message = "Your bid has been successfully placed.")
	public static SystemMessageId YOUR_BID_HAS_BEEN_SUCCESSFULLY_PLACED;
	
	@ClientString(id = 1007, message = "The preliminary match registration for $s1 has finished.")
	public static SystemMessageId THE_PRELIMINARY_MATCH_REGISTRATION_FOR_S1_HAS_FINISHED;
	
	@ClientString(id = 1008, message = "A hungry strider cannot be mounted or dismounted.")
	public static SystemMessageId A_HUNGRY_STRIDER_CANNOT_BE_MOUNTED_OR_DISMOUNTED;
	
	@ClientString(id = 1009, message = "A strider cannot be ridden when dead.")
	public static SystemMessageId A_STRIDER_CANNOT_BE_RIDDEN_WHEN_DEAD;
	
	@ClientString(id = 1010, message = "A dead strider cannot be ridden.")
	public static SystemMessageId A_DEAD_STRIDER_CANNOT_BE_RIDDEN;
	
	@ClientString(id = 1011, message = "A strider in battle cannot be ridden.")
	public static SystemMessageId A_STRIDER_IN_BATTLE_CANNOT_BE_RIDDEN;
	
	@ClientString(id = 1012, message = "A strider cannot be ridden while in battle.")
	public static SystemMessageId A_STRIDER_CANNOT_BE_RIDDEN_WHILE_IN_BATTLE;
	
	@ClientString(id = 1013, message = "A strider can be ridden only when standing.")
	public static SystemMessageId A_STRIDER_CAN_BE_RIDDEN_ONLY_WHEN_STANDING;
	
	@ClientString(id = 1014, message = "Your pet gained $s1 experience points.")
	public static SystemMessageId YOUR_PET_GAINED_S1_EXPERIENCE_POINTS;
	
	@ClientString(id = 1015, message = "Your pet hit for $s1 damage.")
	public static SystemMessageId YOUR_PET_HIT_FOR_S1_DAMAGE;
	
	@ClientString(id = 1016, message = "Your pet received $s2 damage by $c1.")
	public static SystemMessageId YOUR_PET_RECEIVED_S2_DAMAGE_BY_C1;
	
	@ClientString(id = 1017, message = "Pet's critical hit!")
	public static SystemMessageId PET_S_CRITICAL_HIT;
	
	@ClientString(id = 1018, message = "Your pet uses $s1.")
	public static SystemMessageId YOUR_PET_USES_S1;
	
	@ClientString(id = 1019, message = "Your pet uses $s1.")
	public static SystemMessageId YOUR_PET_USES_S1_2;
	
	@ClientString(id = 1020, message = "Your pet picked up $s1.")
	public static SystemMessageId YOUR_PET_PICKED_UP_S1;
	
	@ClientString(id = 1021, message = "Your pet picked up $s2 $s1(s).")
	public static SystemMessageId YOUR_PET_PICKED_UP_S2_S1_S;
	
	@ClientString(id = 1022, message = "Your pet picked up +$s1 $s2.")
	public static SystemMessageId YOUR_PET_PICKED_UP_S1_S2;
	
	@ClientString(id = 1023, message = "Your pet picked up $s1 adena.")
	public static SystemMessageId YOUR_PET_PICKED_UP_S1_ADENA;
	
	@ClientString(id = 1024, message = "Your pet put on $s1.")
	public static SystemMessageId YOUR_PET_PUT_ON_S1;
	
	@ClientString(id = 1025, message = "Your pet took off $s1.")
	public static SystemMessageId YOUR_PET_TOOK_OFF_S1;
	
	@ClientString(id = 1026, message = "The summoned monster gave damage of $s1.")
	public static SystemMessageId THE_SUMMONED_MONSTER_GAVE_DAMAGE_OF_S1;
	
	@ClientString(id = 1027, message = "Servitor received $s2 damage caused by $s1.")
	public static SystemMessageId SERVITOR_RECEIVED_S2_DAMAGE_CAUSED_BY_S1;
	
	@ClientString(id = 1028, message = "Summoned monster's critical hit!")
	public static SystemMessageId SUMMONED_MONSTER_S_CRITICAL_HIT;
	
	@ClientString(id = 1029, message = "A summoned monster uses $s1.")
	public static SystemMessageId A_SUMMONED_MONSTER_USES_S1;
	
	@ClientString(id = 1030, message = "<Party Information>")
	public static SystemMessageId PARTY_INFORMATION;
	
	@ClientString(id = 1031, message = "Looting method: Finders keepers")
	public static SystemMessageId LOOTING_METHOD_FINDERS_KEEPERS;
	
	@ClientString(id = 1032, message = "Looting method: Random")
	public static SystemMessageId LOOTING_METHOD_RANDOM;
	
	@ClientString(id = 1033, message = "Looting method: Random including spoil")
	public static SystemMessageId LOOTING_METHOD_RANDOM_INCLUDING_SPOIL;
	
	@ClientString(id = 1034, message = "Looting method: By turn")
	public static SystemMessageId LOOTING_METHOD_BY_TURN;
	
	@ClientString(id = 1035, message = "Looting method: By turn including spoil")
	public static SystemMessageId LOOTING_METHOD_BY_TURN_INCLUDING_SPOIL;
	
	@ClientString(id = 1036, message = "You have exceeded the quantity that can be inputted.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED;
	
	@ClientString(id = 1037, message = "$c1 manufactured $s2.")
	public static SystemMessageId C1_MANUFACTURED_S2;
	
	@ClientString(id = 1038, message = "$c1 manufactured $s3 $s2(s).")
	public static SystemMessageId C1_MANUFACTURED_S3_S2_S;
	
	@ClientString(id = 1039, message = "Items left at the clan hall warehouse can only be retrieved by the clan leader. Do you want to continue?")
	public static SystemMessageId ITEMS_LEFT_AT_THE_CLAN_HALL_WAREHOUSE_CAN_ONLY_BE_RETRIEVED_BY_THE_CLAN_LEADER_DO_YOU_WANT_TO_CONTINUE;
	
	@ClientString(id = 1040, message = "Dimensional Items sent that way can be retrieved from any Dimensional Merchant in all regions. Do you wish to continue?")
	public static SystemMessageId DIMENSIONAL_ITEMS_SENT_THAT_WAY_CAN_BE_RETRIEVED_FROM_ANY_DIMENSIONAL_MERCHANT_IN_ALL_REGIONS_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 1041, message = "The next seed purchase price is $s1 adena.")
	public static SystemMessageId THE_NEXT_SEED_PURCHASE_PRICE_IS_S1_ADENA;
	
	@ClientString(id = 1042, message = "The next farm goods purchase price is $s1 adena.")
	public static SystemMessageId THE_NEXT_FARM_GOODS_PURCHASE_PRICE_IS_S1_ADENA;
	
	@ClientString(id = 1043, message = "At the current time, the '/unstuck' command cannot be used. Please send in a petition.")
	public static SystemMessageId AT_THE_CURRENT_TIME_THE_UNSTUCK_COMMAND_CANNOT_BE_USED_PLEASE_SEND_IN_A_PETITION;
	
	@ClientString(id = 1044, message = "Monster race payout information is not available while tickets are being sold.")
	public static SystemMessageId MONSTER_RACE_PAYOUT_INFORMATION_IS_NOT_AVAILABLE_WHILE_TICKETS_ARE_BEING_SOLD;
	
	@ClientString(id = 1045, message = "Currently, a monster race is not being set up.")
	public static SystemMessageId CURRENTLY_A_MONSTER_RACE_IS_NOT_BEING_SET_UP;
	
	@ClientString(id = 1046, message = "Monster race tickets are no longer available.")
	public static SystemMessageId MONSTER_RACE_TICKETS_ARE_NO_LONGER_AVAILABLE;
	
	@ClientString(id = 1047, message = "We did not succeed in producing $s1 item.")
	public static SystemMessageId WE_DID_NOT_SUCCEED_IN_PRODUCING_S1_ITEM;
	
	@ClientString(id = 1048, message = "While 'blocking' everything, whispering is not possible.")
	public static SystemMessageId WHILE_BLOCKING_EVERYTHING_WHISPERING_IS_NOT_POSSIBLE;
	
	@ClientString(id = 1049, message = "While 'blocking' everything, it is not possible to send invitations for organizing parties.")
	public static SystemMessageId WHILE_BLOCKING_EVERYTHING_IT_IS_NOT_POSSIBLE_TO_SEND_INVITATIONS_FOR_ORGANIZING_PARTIES;
	
	@ClientString(id = 1050, message = "There are no communities in my clan. Clan communities are allowed for clans with skill levels of 2 and higher.")
	public static SystemMessageId THERE_ARE_NO_COMMUNITIES_IN_MY_CLAN_CLAN_COMMUNITIES_ARE_ALLOWED_FOR_CLANS_WITH_SKILL_LEVELS_OF_2_AND_HIGHER;
	
	@ClientString(id = 1051, message = "Payment for your clan hall has not been made. Please make payment to your clan warehouse by $s1 tomorrow.")
	public static SystemMessageId PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_MAKE_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW;
	
	@ClientString(id = 1052, message = "The clan hall fee is one week overdue; therefore the clan hall ownership has been revoked.")
	public static SystemMessageId THE_CLAN_HALL_FEE_IS_ONE_WEEK_OVERDUE_THEREFORE_THE_CLAN_HALL_OWNERSHIP_HAS_BEEN_REVOKED;
	
	@ClientString(id = 1053, message = "It is not possible to resurrect in battlefields where a siege war is taking place.")
	public static SystemMessageId IT_IS_NOT_POSSIBLE_TO_RESURRECT_IN_BATTLEFIELDS_WHERE_A_SIEGE_WAR_IS_TAKING_PLACE;
	
	@ClientString(id = 1054, message = "You have entered a mystical land.")
	public static SystemMessageId YOU_HAVE_ENTERED_A_MYSTICAL_LAND;
	
	@ClientString(id = 1055, message = "You have left a mystical land.")
	public static SystemMessageId YOU_HAVE_LEFT_A_MYSTICAL_LAND;
	
	@ClientString(id = 1056, message = "You have exceeded the storage capacity of the castle's vault.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_THE_STORAGE_CAPACITY_OF_THE_CASTLE_S_VAULT;
	
	@ClientString(id = 1057, message = "This command can only be used in the relax server.")
	public static SystemMessageId THIS_COMMAND_CAN_ONLY_BE_USED_IN_THE_RELAX_SERVER;
	
	@ClientString(id = 1058, message = "The sales price for seeds is $s1 adena.")
	public static SystemMessageId THE_SALES_PRICE_FOR_SEEDS_IS_S1_ADENA;
	
	@ClientString(id = 1059, message = "The remaining purchasing amount is $s1 adena.")
	public static SystemMessageId THE_REMAINING_PURCHASING_AMOUNT_IS_S1_ADENA;
	
	@ClientString(id = 1060, message = "The remainder after selling the seeds is $s1.")
	public static SystemMessageId THE_REMAINDER_AFTER_SELLING_THE_SEEDS_IS_S1;
	
	@ClientString(id = 1061, message = "The recipe cannot be registered. You do not have the ability to create items.")
	public static SystemMessageId THE_RECIPE_CANNOT_BE_REGISTERED_YOU_DO_NOT_HAVE_THE_ABILITY_TO_CREATE_ITEMS;
	
	@ClientString(id = 1062, message = "Writing something new is possible after level 10.")
	public static SystemMessageId WRITING_SOMETHING_NEW_IS_POSSIBLE_AFTER_LEVEL_10;
	
	@ClientString(id = 1063, message = "The Petition Service is currently unavailable, please try again later; in the interim, if you become trapped or unable to move, please use the '/unstuck' command.")
	public static SystemMessageId THE_PETITION_SERVICE_IS_CURRENTLY_UNAVAILABLE_PLEASE_TRY_AGAIN_LATER_IN_THE_INTERIM_IF_YOU_BECOME_TRAPPED_OR_UNABLE_TO_MOVE_PLEASE_USE_THE_UNSTUCK_COMMAND;
	
	@ClientString(id = 1064, message = "The equipment, +$s1 $s2, has been removed.")
	public static SystemMessageId THE_EQUIPMENT_S1_S2_HAS_BEEN_REMOVED;
	
	@ClientString(id = 1065, message = "While operating a private store or workshop, you cannot discard, destroy, or trade an item.")
	public static SystemMessageId WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP_YOU_CANNOT_DISCARD_DESTROY_OR_TRADE_AN_ITEM;
	
	@ClientString(id = 1066, message = "$s1 HP has been restored.")
	public static SystemMessageId S1_HP_HAS_BEEN_RESTORED;
	
	@ClientString(id = 1067, message = "$s2 HP has been restored by $c1.")
	public static SystemMessageId S2_HP_HAS_BEEN_RESTORED_BY_C1;
	
	@ClientString(id = 1068, message = "$s1 MP has been restored.")
	public static SystemMessageId S1_MP_HAS_BEEN_RESTORED;
	
	@ClientString(id = 1069, message = "$s2 MP has been restored by $c1.")
	public static SystemMessageId S2_MP_HAS_BEEN_RESTORED_BY_C1;
	
	@ClientString(id = 1070, message = "You do not have 'read' permission.")
	public static SystemMessageId YOU_DO_NOT_HAVE_READ_PERMISSION;
	
	@ClientString(id = 1071, message = "You do not have 'write' permission.")
	public static SystemMessageId YOU_DO_NOT_HAVE_WRITE_PERMISSION;
	
	@ClientString(id = 1072, message = "You have obtained a ticket for the Monster Race #$s1 - Single.")
	public static SystemMessageId YOU_HAVE_OBTAINED_A_TICKET_FOR_THE_MONSTER_RACE_S1_SINGLE;
	
	@ClientString(id = 1073, message = "You have obtained a ticket for the Monster Race #$s1 - Single.")
	public static SystemMessageId YOU_HAVE_OBTAINED_A_TICKET_FOR_THE_MONSTER_RACE_S1_SINGLE_2;
	
	@ClientString(id = 1074, message = "You do not meet the age requirement to purchase a Monster Race Ticket.")
	public static SystemMessageId YOU_DO_NOT_MEET_THE_AGE_REQUIREMENT_TO_PURCHASE_A_MONSTER_RACE_TICKET;
	
	@ClientString(id = 1075, message = "The bid amount must be higher than the previous bid.")
	public static SystemMessageId THE_BID_AMOUNT_MUST_BE_HIGHER_THAN_THE_PREVIOUS_BID;
	
	@ClientString(id = 1076, message = "The game cannot be terminated at this time.")
	public static SystemMessageId THE_GAME_CANNOT_BE_TERMINATED_AT_THIS_TIME;
	
	@ClientString(id = 1077, message = "A GameGuard Execution error has occurred. Please send the *.erl file(s) located in the GameGuard folder to game@inca.co.kr.")
	public static SystemMessageId A_GAMEGUARD_EXECUTION_ERROR_HAS_OCCURRED_PLEASE_SEND_THE_ERL_FILE_S_LOCATED_IN_THE_GAMEGUARD_FOLDER_TO_GAME_INCA_CO_KR;
	
	@ClientString(id = 1078, message = "When a user's keyboard input exceeds a certain cumulative score a chat ban will be applied. This is done to discourage spamming. Please avoid posting the same message multiple times during a short period.")
	public static SystemMessageId WHEN_A_USER_S_KEYBOARD_INPUT_EXCEEDS_A_CERTAIN_CUMULATIVE_SCORE_A_CHAT_BAN_WILL_BE_APPLIED_THIS_IS_DONE_TO_DISCOURAGE_SPAMMING_PLEASE_AVOID_POSTING_THE_SAME_MESSAGE_MULTIPLE_TIMES_DURING_A_SHORT_PERIOD;
	
	@ClientString(id = 1079, message = " The target is currently banned from chatting.")
	public static SystemMessageId THE_TARGET_IS_CURRENTLY_BANNED_FROM_CHATTING;
	
	@ClientString(id = 1080, message = "Being permanent, are you sure you wish to use the facelift potion - Type A?")
	public static SystemMessageId BEING_PERMANENT_ARE_YOU_SURE_YOU_WISH_TO_USE_THE_FACELIFT_POTION_TYPE_A;
	
	@ClientString(id = 1081, message = "Being permanent, are you sure you wish to use the hair dye potion - Type A?")
	public static SystemMessageId BEING_PERMANENT_ARE_YOU_SURE_YOU_WISH_TO_USE_THE_HAIR_DYE_POTION_TYPE_A;
	
	@ClientString(id = 1082, message = "Do you wish to use the hair style change potion – Type A? It is permanent.")
	public static SystemMessageId DO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_TYPE_A_IT_IS_PERMANENT;
	
	@ClientString(id = 1083, message = "Facelift potion - Type A is being applied.")
	public static SystemMessageId FACELIFT_POTION_TYPE_A_IS_BEING_APPLIED;
	
	@ClientString(id = 1084, message = "Hair dye potion - Type A is being applied.")
	public static SystemMessageId HAIR_DYE_POTION_TYPE_A_IS_BEING_APPLIED;
	
	@ClientString(id = 1085, message = "The hair style change potion - Type A is being used.")
	public static SystemMessageId THE_HAIR_STYLE_CHANGE_POTION_TYPE_A_IS_BEING_USED;
	
	@ClientString(id = 1086, message = "Your facial appearance has been changed.")
	public static SystemMessageId YOUR_FACIAL_APPEARANCE_HAS_BEEN_CHANGED;
	
	@ClientString(id = 1087, message = "Your hair color has been changed.")
	public static SystemMessageId YOUR_HAIR_COLOR_HAS_BEEN_CHANGED;
	
	@ClientString(id = 1088, message = "Your hair style has been changed.")
	public static SystemMessageId YOUR_HAIR_STYLE_HAS_BEEN_CHANGED;
	
	@ClientString(id = 1089, message = "$c1 has obtained a first anniversary commemorative item.")
	public static SystemMessageId C1_HAS_OBTAINED_A_FIRST_ANNIVERSARY_COMMEMORATIVE_ITEM;
	
	@ClientString(id = 1090, message = "Being permanent, are you sure you wish to use the facelift potion - Type B?")
	public static SystemMessageId BEING_PERMANENT_ARE_YOU_SURE_YOU_WISH_TO_USE_THE_FACELIFT_POTION_TYPE_B;
	
	@ClientString(id = 1091, message = "Being permanent, are you sure you wish to use the facelift potion - Type C?")
	public static SystemMessageId BEING_PERMANENT_ARE_YOU_SURE_YOU_WISH_TO_USE_THE_FACELIFT_POTION_TYPE_C;
	
	@ClientString(id = 1092, message = "Being permanent, are you sure you wish to use the hair dye potion - Type B?")
	public static SystemMessageId BEING_PERMANENT_ARE_YOU_SURE_YOU_WISH_TO_USE_THE_HAIR_DYE_POTION_TYPE_B;
	
	@ClientString(id = 1093, message = "Being permanent, are you sure you wish to use the hair dye potion - Type C?")
	public static SystemMessageId BEING_PERMANENT_ARE_YOU_SURE_YOU_WISH_TO_USE_THE_HAIR_DYE_POTION_TYPE_C;
	
	@ClientString(id = 1094, message = "Being permanent, are you sure you wish to use the hair dye potion - Type D?")
	public static SystemMessageId BEING_PERMANENT_ARE_YOU_SURE_YOU_WISH_TO_USE_THE_HAIR_DYE_POTION_TYPE_D;
	
	@ClientString(id = 1095, message = "Do you wish to use the hair style change potion – Type B? It is permanent.")
	public static SystemMessageId DO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_TYPE_B_IT_IS_PERMANENT;
	
	@ClientString(id = 1096, message = "Do you wish to use the hair style change potion – Type C? It is permanent.")
	public static SystemMessageId DO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_TYPE_C_IT_IS_PERMANENT;
	
	@ClientString(id = 1097, message = "Do you wish to use the hair style change potion – Type D? It is permanent.")
	public static SystemMessageId DO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_TYPE_D_IT_IS_PERMANENT;
	
	@ClientString(id = 1098, message = "Do you wish to use the hair style change potion – Type E? It is permanent.")
	public static SystemMessageId DO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_TYPE_E_IT_IS_PERMANENT;
	
	@ClientString(id = 1099, message = "Do you wish to use the hair style change potion – Type F? It is permanent.")
	public static SystemMessageId DO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_TYPE_F_IT_IS_PERMANENT;
	
	@ClientString(id = 1100, message = "Do you wish to use the hair style change potion – Type G? It is permanent.")
	public static SystemMessageId DO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_TYPE_G_IT_IS_PERMANENT;
	
	@ClientString(id = 1101, message = "Facelift potion - Type B is being applied.")
	public static SystemMessageId FACELIFT_POTION_TYPE_B_IS_BEING_APPLIED;
	
	@ClientString(id = 1102, message = "Facelift potion - Type C is being applied.")
	public static SystemMessageId FACELIFT_POTION_TYPE_C_IS_BEING_APPLIED;
	
	@ClientString(id = 1103, message = "Hair dye potion - Type B is being applied.")
	public static SystemMessageId HAIR_DYE_POTION_TYPE_B_IS_BEING_APPLIED;
	
	@ClientString(id = 1104, message = "Hair dye potion - Type C is being applied.")
	public static SystemMessageId HAIR_DYE_POTION_TYPE_C_IS_BEING_APPLIED;
	
	@ClientString(id = 1105, message = "Hair dye potion - Type D is being applied.")
	public static SystemMessageId HAIR_DYE_POTION_TYPE_D_IS_BEING_APPLIED;
	
	@ClientString(id = 1106, message = "The hair style change potion - Type B is being used.")
	public static SystemMessageId THE_HAIR_STYLE_CHANGE_POTION_TYPE_B_IS_BEING_USED;
	
	@ClientString(id = 1107, message = "The hair style change potion - Type C is being used.")
	public static SystemMessageId THE_HAIR_STYLE_CHANGE_POTION_TYPE_C_IS_BEING_USED;
	
	@ClientString(id = 1108, message = "The hair style change potion - Type D is being used.")
	public static SystemMessageId THE_HAIR_STYLE_CHANGE_POTION_TYPE_D_IS_BEING_USED;
	
	@ClientString(id = 1109, message = "The hair style change potion - Type E is being used.")
	public static SystemMessageId THE_HAIR_STYLE_CHANGE_POTION_TYPE_E_IS_BEING_USED;
	
	@ClientString(id = 1110, message = "The hair style change potion - Type F is being used.")
	public static SystemMessageId THE_HAIR_STYLE_CHANGE_POTION_TYPE_F_IS_BEING_USED;
	
	@ClientString(id = 1111, message = "The hair style change potion - Type G is being used.")
	public static SystemMessageId THE_HAIR_STYLE_CHANGE_POTION_TYPE_G_IS_BEING_USED;
	
	@ClientString(id = 1112, message = "The prize amount for the winner of Lottery #$s1 is $s2 adena. We have $s3 first prize winners.")
	public static SystemMessageId THE_PRIZE_AMOUNT_FOR_THE_WINNER_OF_LOTTERY_S1_IS_S2_ADENA_WE_HAVE_S3_FIRST_PRIZE_WINNERS;
	
	@ClientString(id = 1113, message = "The prize amount for Lucky Lottery #$s1 is $s2 adena. There was no first prize winner in this drawing, therefore the jackpot will be added to the next drawing.")
	public static SystemMessageId THE_PRIZE_AMOUNT_FOR_LUCKY_LOTTERY_S1_IS_S2_ADENA_THERE_WAS_NO_FIRST_PRIZE_WINNER_IN_THIS_DRAWING_THEREFORE_THE_JACKPOT_WILL_BE_ADDED_TO_THE_NEXT_DRAWING;
	
	@ClientString(id = 1114, message = "Your clan may not register to participate in a siege while under a grace period of the clan's dissolution.")
	public static SystemMessageId YOUR_CLAN_MAY_NOT_REGISTER_TO_PARTICIPATE_IN_A_SIEGE_WHILE_UNDER_A_GRACE_PERIOD_OF_THE_CLAN_S_DISSOLUTION;
	
	@ClientString(id = 1115, message = "Individuals may not surrender during combat.")
	public static SystemMessageId INDIVIDUALS_MAY_NOT_SURRENDER_DURING_COMBAT;
	
	@ClientString(id = 1116, message = "You cannot leave a clan while engaged in combat.")
	public static SystemMessageId YOU_CANNOT_LEAVE_A_CLAN_WHILE_ENGAGED_IN_COMBAT;
	
	@ClientString(id = 1117, message = "A clan member may not be dismissed during combat.")
	public static SystemMessageId A_CLAN_MEMBER_MAY_NOT_BE_DISMISSED_DURING_COMBAT;
	
	@ClientString(id = 1118, message = "Unable to process this request until your inventory's weight and slot count are less than 80 percent of capacity.")
	public static SystemMessageId UNABLE_TO_PROCESS_THIS_REQUEST_UNTIL_YOUR_INVENTORY_S_WEIGHT_AND_SLOT_COUNT_ARE_LESS_THAN_80_PERCENT_OF_CAPACITY;
	
	@ClientString(id = 1119, message = "Quest was automatically canceled when you attempted to settle the accounts of your quest while your inventory exceeded 80 percent of capacity.")
	public static SystemMessageId QUEST_WAS_AUTOMATICALLY_CANCELED_WHEN_YOU_ATTEMPTED_TO_SETTLE_THE_ACCOUNTS_OF_YOUR_QUEST_WHILE_YOUR_INVENTORY_EXCEEDED_80_PERCENT_OF_CAPACITY;
	
	@ClientString(id = 1120, message = "You are still a member of the clan.")
	public static SystemMessageId YOU_ARE_STILL_A_MEMBER_OF_THE_CLAN;
	
	@ClientString(id = 1121, message = "You do not have the right to vote.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_VOTE;
	
	@ClientString(id = 1122, message = "There is no candidate.")
	public static SystemMessageId THERE_IS_NO_CANDIDATE;
	
	@ClientString(id = 1123, message = "Weight and volume limit have been exceeded. That skill is currently unavailable.")
	public static SystemMessageId WEIGHT_AND_VOLUME_LIMIT_HAVE_BEEN_EXCEEDED_THAT_SKILL_IS_CURRENTLY_UNAVAILABLE;
	
	@ClientString(id = 1124, message = "Your recipe book may not be accessed while using a skill.")
	public static SystemMessageId YOUR_RECIPE_BOOK_MAY_NOT_BE_ACCESSED_WHILE_USING_A_SKILL;
	
	@ClientString(id = 1125, message = "Item creation is not possible while engaged in a trade.")
	public static SystemMessageId ITEM_CREATION_IS_NOT_POSSIBLE_WHILE_ENGAGED_IN_A_TRADE;
	
	@ClientString(id = 1126, message = "You cannot enter a negative number.")
	public static SystemMessageId YOU_CANNOT_ENTER_A_NEGATIVE_NUMBER;
	
	@ClientString(id = 1127, message = "The reward must be less than 10 times the standard price.")
	public static SystemMessageId THE_REWARD_MUST_BE_LESS_THAN_10_TIMES_THE_STANDARD_PRICE;
	
	@ClientString(id = 1128, message = "A private store may not be opened while using a skill.")
	public static SystemMessageId A_PRIVATE_STORE_MAY_NOT_BE_OPENED_WHILE_USING_A_SKILL;
	
	@ClientString(id = 1129, message = "This is not allowed while riding a ferry or boat.")
	public static SystemMessageId THIS_IS_NOT_ALLOWED_WHILE_RIDING_A_FERRY_OR_BOAT;
	
	@ClientString(id = 1130, message = "You have dealt $s1 damage to your target and $s2 damage to the servitor.")
	public static SystemMessageId YOU_HAVE_DEALT_S1_DAMAGE_TO_YOUR_TARGET_AND_S2_DAMAGE_TO_THE_SERVITOR;
	
	@ClientString(id = 1131, message = "It is now midnight and the effect of $s1 can be felt.")
	public static SystemMessageId IT_IS_NOW_MIDNIGHT_AND_THE_EFFECT_OF_S1_CAN_BE_FELT;
	
	@ClientString(id = 1132, message = "It is dawn and the effect of $s1 will now disappear.")
	public static SystemMessageId IT_IS_DAWN_AND_THE_EFFECT_OF_S1_WILL_NOW_DISAPPEAR;
	
	@ClientString(id = 1133, message = "Since your HP has decreased, the effect of $s1 can be felt.")
	public static SystemMessageId SINCE_YOUR_HP_HAS_DECREASED_THE_EFFECT_OF_S1_CAN_BE_FELT;
	
	@ClientString(id = 1134, message = "Since your HP has increased, the effect of $s1 will disappear.")
	public static SystemMessageId SINCE_YOUR_HP_HAS_INCREASED_THE_EFFECT_OF_S1_WILL_DISAPPEAR;
	
	@ClientString(id = 1135, message = "While you are engaged in combat, you cannot operate a private store or private workshop.")
	public static SystemMessageId WHILE_YOU_ARE_ENGAGED_IN_COMBAT_YOU_CANNOT_OPERATE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP;
	
	@ClientString(id = 1136, message = "Since there was an account that used this IP and attempted to log in illegally, this account is not allowed to connect to the game server for $s1 minutes. Please use another game server.")
	public static SystemMessageId SINCE_THERE_WAS_AN_ACCOUNT_THAT_USED_THIS_IP_AND_ATTEMPTED_TO_LOG_IN_ILLEGALLY_THIS_ACCOUNT_IS_NOT_ALLOWED_TO_CONNECT_TO_THE_GAME_SERVER_FOR_S1_MINUTES_PLEASE_USE_ANOTHER_GAME_SERVER;
	
	@ClientString(id = 1137, message = "$c1 harvested $s3 $s2(s).")
	public static SystemMessageId C1_HARVESTED_S3_S2_S;
	
	@ClientString(id = 1138, message = "$c1 harvested $s2.")
	public static SystemMessageId C1_HARVESTED_S2;
	
	@ClientString(id = 1139, message = "The weight and volume limit of your inventory cannot be exceeded.")
	public static SystemMessageId THE_WEIGHT_AND_VOLUME_LIMIT_OF_YOUR_INVENTORY_CANNOT_BE_EXCEEDED;
	
	@ClientString(id = 1140, message = "Would you like to open the gate?")
	public static SystemMessageId WOULD_YOU_LIKE_TO_OPEN_THE_GATE;
	
	@ClientString(id = 1141, message = "Would you like to close the gate?")
	public static SystemMessageId WOULD_YOU_LIKE_TO_CLOSE_THE_GATE;
	
	@ClientString(id = 1142, message = "Since $s1 already exists nearby, you cannot summon it again.")
	public static SystemMessageId SINCE_S1_ALREADY_EXISTS_NEARBY_YOU_CANNOT_SUMMON_IT_AGAIN;
	
	@ClientString(id = 1143, message = "Since you do not have enough items to maintain the servitor's stay, the servitor has disappeared.")
	public static SystemMessageId SINCE_YOU_DO_NOT_HAVE_ENOUGH_ITEMS_TO_MAINTAIN_THE_SERVITOR_S_STAY_THE_SERVITOR_HAS_DISAPPEARED;
	
	@ClientString(id = 1144, message = "You don't have anybody to chat with in the game.")
	public static SystemMessageId YOU_DON_T_HAVE_ANYBODY_TO_CHAT_WITH_IN_THE_GAME;
	
	@ClientString(id = 1145, message = "$s2 has been created for $c1 after the payment of $s3 adena was received.")
	public static SystemMessageId S2_HAS_BEEN_CREATED_FOR_C1_AFTER_THE_PAYMENT_OF_S3_ADENA_WAS_RECEIVED;
	
	@ClientString(id = 1146, message = "$c1 created $s2 after receiving $s3 adena.")
	public static SystemMessageId C1_CREATED_S2_AFTER_RECEIVING_S3_ADENA;
	
	@ClientString(id = 1147, message = "$s2 $s3 have been created for $c1 at the price of $s4 adena.")
	public static SystemMessageId S2_S3_HAVE_BEEN_CREATED_FOR_C1_AT_THE_PRICE_OF_S4_ADENA;
	
	@ClientString(id = 1148, message = "$c1 created $s2 $s3 at the price of $s4 adena.")
	public static SystemMessageId C1_CREATED_S2_S3_AT_THE_PRICE_OF_S4_ADENA;
	
	@ClientString(id = 1149, message = "Your attempt to create $s2 for $c1 at the price of $s3 adena has failed.")
	public static SystemMessageId YOUR_ATTEMPT_TO_CREATE_S2_FOR_C1_AT_THE_PRICE_OF_S3_ADENA_HAS_FAILED;
	
	@ClientString(id = 1150, message = "$c1 has failed to create $s2 at the price of $s3 adena.")
	public static SystemMessageId C1_HAS_FAILED_TO_CREATE_S2_AT_THE_PRICE_OF_S3_ADENA;
	
	@ClientString(id = 1151, message = "$s2 is sold to $c1 for the price of $s3 adena.")
	public static SystemMessageId S2_IS_SOLD_TO_C1_FOR_THE_PRICE_OF_S3_ADENA;
	
	@ClientString(id = 1152, message = "$s2 $s3 have been sold to $c1 for $s4 adena.")
	public static SystemMessageId S2_S3_HAVE_BEEN_SOLD_TO_C1_FOR_S4_ADENA;
	
	@ClientString(id = 1153, message = "$s2 has been purchased from $c1 at the price of $s3 adena.")
	public static SystemMessageId S2_HAS_BEEN_PURCHASED_FROM_C1_AT_THE_PRICE_OF_S3_ADENA;
	
	@ClientString(id = 1154, message = "$s3 $s2 has been purchased from $c1 for $s4 adena.")
	public static SystemMessageId S3_S2_HAS_BEEN_PURCHASED_FROM_C1_FOR_S4_ADENA;
	
	@ClientString(id = 1155, message = "+$s2$s3 has been sold to $c1 at the price of $s4 adena.")
	public static SystemMessageId S2_S3_HAS_BEEN_SOLD_TO_C1_AT_THE_PRICE_OF_S4_ADENA;
	
	@ClientString(id = 1156, message = "+$s2$s3 has been purchased from $c1 at the price of $s4 adena.")
	public static SystemMessageId S2_S3_HAS_BEEN_PURCHASED_FROM_C1_AT_THE_PRICE_OF_S4_ADENA;
	
	@ClientString(id = 1157, message = "The preview state only lasts for 5 seconds. If you wish to continue, please click Confirm.")
	public static SystemMessageId THE_PREVIEW_STATE_ONLY_LASTS_FOR_5_SECONDS_IF_YOU_WISH_TO_CONTINUE_PLEASE_CLICK_CONFIRM;
	
	@ClientString(id = 1158, message = "You cannot dismount from this elevation.")
	public static SystemMessageId YOU_CANNOT_DISMOUNT_FROM_THIS_ELEVATION;
	
	@ClientString(id = 1159, message = "The ferry from Talking Island will arrive at Gludin Harbor in approximately 10 minutes.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_WILL_ARRIVE_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_10_MINUTES;
	
	@ClientString(id = 1160, message = "The ferry from Talking Island will be arriving at Gludin Harbor in approximately 5 minutes.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_5_MINUTES;
	
	@ClientString(id = 1161, message = "The ferry from Talking Island will be arriving at Gludin Harbor in approximately 1 minute.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_1_MINUTE;
	
	@ClientString(id = 1162, message = "The ferry from Giran Harbor will be arriving at Talking Island in approximately 15 minutes.")
	public static SystemMessageId THE_FERRY_FROM_GIRAN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_15_MINUTES;
	
	@ClientString(id = 1163, message = "The ferry from Giran Harbor will be arriving at Talking Island in approximately 10 minutes.")
	public static SystemMessageId THE_FERRY_FROM_GIRAN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_10_MINUTES;
	
	@ClientString(id = 1164, message = "The ferry from Giran Harbor will be arriving at Talking Island in approximately 5 minutes.")
	public static SystemMessageId THE_FERRY_FROM_GIRAN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_5_MINUTES;
	
	@ClientString(id = 1165, message = "The ferry from Giran Harbor will be arriving at Talking Island in approximately 1 minute.")
	public static SystemMessageId THE_FERRY_FROM_GIRAN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_1_MINUTE;
	
	@ClientString(id = 1166, message = "The ferry from Talking Island will be arriving at Giran Harbor in approximately 20 minutes.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_20_MINUTES;
	
	@ClientString(id = 1167, message = "The ferry from Talking Island will be arriving at Giran Harbor in approximately 15 minutes.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_15_MINUTES;
	
	@ClientString(id = 1168, message = "The ferry from Talking Island will be arriving at Giran Harbor in approximately 10 minutes.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_10_MINUTES;
	
	@ClientString(id = 1169, message = "The ferry from Talking Island will be arriving at Giran Harbor in approximately 5 minutes.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_5_MINUTES;
	
	@ClientString(id = 1170, message = "The ferry from Talking Island will be arriving at Giran Harbor in approximately 1 minute.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_1_MINUTE;
	
	@ClientString(id = 1171, message = "The Innadril pleasure boat will arrive in approximately 20 minutes.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_20_MINUTES;
	
	@ClientString(id = 1172, message = "The Innadril pleasure boat will arrive in approximately 15 minutes.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_15_MINUTES;
	
	@ClientString(id = 1173, message = "The Innadril pleasure boat will arrive in approximately 10 minutes.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_10_MINUTES;
	
	@ClientString(id = 1174, message = "The Innadril pleasure boat will arrive in approximately 5 minutes.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_5_MINUTES;
	
	@ClientString(id = 1175, message = "The Innadril pleasure boat will arrive in approximately 1 minute.")
	public static SystemMessageId THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_1_MINUTE;
	
	@ClientString(id = 1176, message = "The SSQ Competition period is underway.")
	public static SystemMessageId THE_SSQ_COMPETITION_PERIOD_IS_UNDERWAY;
	
	@ClientString(id = 1177, message = "This is the seal validation period.")
	public static SystemMessageId THIS_IS_THE_SEAL_VALIDATION_PERIOD;
	
	@ClientString(id = 1178, message = "This seal permits the group that holds it to exclusive entry to the dungeons opened by the Seal of Avarice during the seal validation period. It also permits trading with the Merchant of Mammon and permits meetings with Anakim or Lilith in the Disciple's Necropolis.")
	public static SystemMessageId THIS_SEAL_PERMITS_THE_GROUP_THAT_HOLDS_IT_TO_EXCLUSIVE_ENTRY_TO_THE_DUNGEONS_OPENED_BY_THE_SEAL_OF_AVARICE_DURING_THE_SEAL_VALIDATION_PERIOD_IT_ALSO_PERMITS_TRADING_WITH_THE_MERCHANT_OF_MAMMON_AND_PERMITS_MEETINGS_WITH_ANAKIM_OR_LILITH_IN_THE_DISCIPLE_S_NECROPOLIS;
	
	@ClientString(id = 1179, message = "This seal permits the group that holds it to enter the dungeon opened by the Seal of Gnosis, use the teleportation service offered by the priest in the village, and do business with the Blacksmith of Mammon. The Orator of Revelations appears and casts good magic on the winners, and the Preacher of Doom appears and casts bad magic on the losers.")
	public static SystemMessageId THIS_SEAL_PERMITS_THE_GROUP_THAT_HOLDS_IT_TO_ENTER_THE_DUNGEON_OPENED_BY_THE_SEAL_OF_GNOSIS_USE_THE_TELEPORTATION_SERVICE_OFFERED_BY_THE_PRIEST_IN_THE_VILLAGE_AND_DO_BUSINESS_WITH_THE_BLACKSMITH_OF_MAMMON_THE_ORATOR_OF_REVELATIONS_APPEARS_AND_CASTS_GOOD_MAGIC_ON_THE_WINNERS_AND_THE_PREACHER_OF_DOOM_APPEARS_AND_CASTS_BAD_MAGIC_ON_THE_LOSERS;
	
	@ClientString(id = 1180, message = "During the Seal Validation period, the cabal's maximum CP amount increases. In addition, the cabal possessing the seal will benefit from favorable changes in the cost to upgrade castle defense mercenaries, castle gates and walls; basic P. Def. of castle gates and walls; and the limit imposed on the castle tax rate. The use of siege war weapons will also be limited. If the Revolutionary Army of Dusk takes possession of this seal during the castle siege war, only the clan that owns the castle can come to its defense.")
	public static SystemMessageId DURING_THE_SEAL_VALIDATION_PERIOD_THE_CABAL_S_MAXIMUM_CP_AMOUNT_INCREASES_IN_ADDITION_THE_CABAL_POSSESSING_THE_SEAL_WILL_BENEFIT_FROM_FAVORABLE_CHANGES_IN_THE_COST_TO_UPGRADE_CASTLE_DEFENSE_MERCENARIES_CASTLE_GATES_AND_WALLS_BASIC_P_DEF_OF_CASTLE_GATES_AND_WALLS_AND_THE_LIMIT_IMPOSED_ON_THE_CASTLE_TAX_RATE_THE_USE_OF_SIEGE_WAR_WEAPONS_WILL_ALSO_BE_LIMITED_IF_THE_REVOLUTIONARY_ARMY_OF_DUSK_TAKES_POSSESSION_OF_THIS_SEAL_DURING_THE_CASTLE_SIEGE_WAR_ONLY_THE_CLAN_THAT_OWNS_THE_CASTLE_CAN_COME_TO_ITS_DEFENSE;
	
	@ClientString(id = 1181, message = "Do you really wish to change the title?")
	public static SystemMessageId DO_YOU_REALLY_WISH_TO_CHANGE_THE_TITLE;
	
	@ClientString(id = 1182, message = "Are you sure you wish to delete the clan crest?")
	public static SystemMessageId ARE_YOU_SURE_YOU_WISH_TO_DELETE_THE_CLAN_CREST;
	
	@ClientString(id = 1183, message = "This is the initial period.")
	public static SystemMessageId THIS_IS_THE_INITIAL_PERIOD;
	
	@ClientString(id = 1184, message = "This is a period when server statistics are calculated.")
	public static SystemMessageId THIS_IS_A_PERIOD_WHEN_SERVER_STATISTICS_ARE_CALCULATED;
	
	@ClientString(id = 1185, message = "days left until deletion.")
	public static SystemMessageId DAYS_LEFT_UNTIL_DELETION;
	
	@ClientString(id = 1186, message = "To create a new account, please visit the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId TO_CREATE_A_NEW_ACCOUNT_PLEASE_VISIT_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1187, message = "If you've forgotten your account information or password, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId IF_YOU_VE_FORGOTTEN_YOUR_ACCOUNT_INFORMATION_OR_PASSWORD_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1188, message = "Your selected target can no longer receive a recommendation.")
	public static SystemMessageId YOUR_SELECTED_TARGET_CAN_NO_LONGER_RECEIVE_A_RECOMMENDATION;
	
	@ClientString(id = 1189, message = "The temporary alliance of the Castle Attacker team is in effect. It will be dissolved when the Castle Lord is replaced.")
	public static SystemMessageId THE_TEMPORARY_ALLIANCE_OF_THE_CASTLE_ATTACKER_TEAM_IS_IN_EFFECT_IT_WILL_BE_DISSOLVED_WHEN_THE_CASTLE_LORD_IS_REPLACED;
	
	@ClientString(id = 1190, message = "The temporary alliance of the Castle Attacker team has been dissolved.")
	public static SystemMessageId THE_TEMPORARY_ALLIANCE_OF_THE_CASTLE_ATTACKER_TEAM_HAS_BEEN_DISSOLVED;
	
	@ClientString(id = 1191, message = "The ferry from Gludin Harbor will be arriving at Talking Island in approximately 10 minutes.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_10_MINUTES;
	
	@ClientString(id = 1192, message = "The ferry from Gludin Harbor will be arriving at Talking Island in approximately 5 minutes.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_5_MINUTES;
	
	@ClientString(id = 1193, message = "The ferry from Gludin Harbor will be arriving at Talking Island in approximately 1 minute.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_1_MINUTE;
	
	@ClientString(id = 1194, message = "A mercenary can be assigned to a position from the beginning of the Seal Validation period until the time when a siege starts.")
	public static SystemMessageId A_MERCENARY_CAN_BE_ASSIGNED_TO_A_POSITION_FROM_THE_BEGINNING_OF_THE_SEAL_VALIDATION_PERIOD_UNTIL_THE_TIME_WHEN_A_SIEGE_STARTS;
	
	@ClientString(id = 1195, message = "This mercenary cannot be assigned to a position by using the Seal of Strife.")
	public static SystemMessageId THIS_MERCENARY_CANNOT_BE_ASSIGNED_TO_A_POSITION_BY_USING_THE_SEAL_OF_STRIFE;
	
	@ClientString(id = 1196, message = "Your force has reached maximum capacity.")
	public static SystemMessageId YOUR_FORCE_HAS_REACHED_MAXIMUM_CAPACITY_2;
	
	@ClientString(id = 1197, message = "Summoning a servitor costs $s2 $s1.")
	public static SystemMessageId SUMMONING_A_SERVITOR_COSTS_S2_S1;
	
	@ClientString(id = 1198, message = "The item has been successfully crystallized.")
	public static SystemMessageId THE_ITEM_HAS_BEEN_SUCCESSFULLY_CRYSTALLIZED;
	
	@ClientString(id = 1199, message = "=======<Clan War Target>=======")
	public static SystemMessageId CLAN_WAR_TARGET;
	
	@ClientString(id = 1200, message = "= $s1 ($s2 Alliance)")
	public static SystemMessageId S1_S2_ALLIANCE;
	
	@ClientString(id = 1201, message = "Please select the quest you wish to abort.")
	public static SystemMessageId PLEASE_SELECT_THE_QUEST_YOU_WISH_TO_ABORT;
	
	@ClientString(id = 1202, message = "= $s1 (No alliance exists)")
	public static SystemMessageId S1_NO_ALLIANCE_EXISTS;
	
	@ClientString(id = 1203, message = "There is no clan war in progress.")
	public static SystemMessageId THERE_IS_NO_CLAN_WAR_IN_PROGRESS;
	
	@ClientString(id = 1204, message = "The screenshot has been saved. ($s1 $s2x$s3)")
	public static SystemMessageId THE_SCREENSHOT_HAS_BEEN_SAVED_S1_S2X_S3;
	
	@ClientString(id = 1205, message = "Your mailbox is full. There is a 100 message limit.")
	public static SystemMessageId YOUR_MAILBOX_IS_FULL_THERE_IS_A_100_MESSAGE_LIMIT;
	
	@ClientString(id = 1206, message = "The memo box is full. There is a 100 memo limit.")
	public static SystemMessageId THE_MEMO_BOX_IS_FULL_THERE_IS_A_100_MEMO_LIMIT;
	
	@ClientString(id = 1207, message = "Please make an entry in the field.")
	public static SystemMessageId PLEASE_MAKE_AN_ENTRY_IN_THE_FIELD;
	
	@ClientString(id = 1208, message = "$c1 died and dropped $s3 $s2.")
	public static SystemMessageId C1_DIED_AND_DROPPED_S3_S2;
	
	@ClientString(id = 1209, message = "Congratulations. Your raid was successful.")
	public static SystemMessageId CONGRATULATIONS_YOUR_RAID_WAS_SUCCESSFUL;
	
	@ClientString(id = 1210, message = "Seven Signs: The competition period has begun. Visit a Priest of Dawn or Priestess of Dusk to participate in the event.")
	public static SystemMessageId SEVEN_SIGNS_THE_COMPETITION_PERIOD_HAS_BEGUN_VISIT_A_PRIEST_OF_DAWN_OR_PRIESTESS_OF_DUSK_TO_PARTICIPATE_IN_THE_EVENT;
	
	@ClientString(id = 1211, message = "Seven Signs: The competition period has ended. The next quest event will start in one week.")
	public static SystemMessageId SEVEN_SIGNS_THE_COMPETITION_PERIOD_HAS_ENDED_THE_NEXT_QUEST_EVENT_WILL_START_IN_ONE_WEEK;
	
	@ClientString(id = 1212, message = "Seven Signs: The Lords of Dawn have obtained the Seal of Avarice.")
	public static SystemMessageId SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_AVARICE;
	
	@ClientString(id = 1213, message = "Seven Signs: The Lords of Dawn have obtained the Seal of Gnosis.")
	public static SystemMessageId SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_GNOSIS;
	
	@ClientString(id = 1214, message = "Seven Signs: The Lords of Dawn have obtained the Seal of Strife.")
	public static SystemMessageId SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_STRIFE;
	
	@ClientString(id = 1215, message = "Seven Signs: The Revolutionaries of Dusk have obtained the Seal of Avarice.")
	public static SystemMessageId SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_AVARICE;
	
	@ClientString(id = 1216, message = "Seven Signs: The Revolutionaries of Dusk have obtained the Seal of Gnosis.")
	public static SystemMessageId SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_GNOSIS;
	
	@ClientString(id = 1217, message = "Seven Signs: The Revolutionaries of Dusk have obtained the Seal of Strife.")
	public static SystemMessageId SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_STRIFE;
	
	@ClientString(id = 1218, message = "Seven Signs: The Seal Validation period has begun.")
	public static SystemMessageId SEVEN_SIGNS_THE_SEAL_VALIDATION_PERIOD_HAS_BEGUN;
	
	@ClientString(id = 1219, message = "Seven Signs: The Seal Validation period has ended.")
	public static SystemMessageId SEVEN_SIGNS_THE_SEAL_VALIDATION_PERIOD_HAS_ENDED;
	
	@ClientString(id = 1220, message = "Are you sure you wish to summon it?")
	public static SystemMessageId ARE_YOU_SURE_YOU_WISH_TO_SUMMON_IT;
	
	@ClientString(id = 1221, message = "Do you really wish to return it?")
	public static SystemMessageId DO_YOU_REALLY_WISH_TO_RETURN_IT;
	
	@ClientString(id = 1222, message = "Current Location: $s1, $s2, $s3 (GM Consultation Area)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_GM_CONSULTATION_AREA;
	
	@ClientString(id = 1223, message = "We depart for Talking Island in five minutes.")
	public static SystemMessageId WE_DEPART_FOR_TALKING_ISLAND_IN_FIVE_MINUTES;
	
	@ClientString(id = 1224, message = "We depart for Talking Island in one minute.")
	public static SystemMessageId WE_DEPART_FOR_TALKING_ISLAND_IN_ONE_MINUTE;
	
	@ClientString(id = 1225, message = "All aboard for Talking Island!")
	public static SystemMessageId ALL_ABOARD_FOR_TALKING_ISLAND;
	
	@ClientString(id = 1226, message = "We are now leaving for Talking Island.")
	public static SystemMessageId WE_ARE_NOW_LEAVING_FOR_TALKING_ISLAND;
	
	@ClientString(id = 1227, message = "You have $s1 unread messages.")
	public static SystemMessageId YOU_HAVE_S1_UNREAD_MESSAGES;
	
	@ClientString(id = 1228, message = "$c1 has blocked you. You cannot send mail to $c1.")
	public static SystemMessageId C1_HAS_BLOCKED_YOU_YOU_CANNOT_SEND_MAIL_TO_C1;
	
	@ClientString(id = 1229, message = "No more messages may be sent at this time. Each account is allowed 10 messages per day.")
	public static SystemMessageId NO_MORE_MESSAGES_MAY_BE_SENT_AT_THIS_TIME_EACH_ACCOUNT_IS_ALLOWED_10_MESSAGES_PER_DAY;
	
	@ClientString(id = 1230, message = "You are limited to five recipients at a time.")
	public static SystemMessageId YOU_ARE_LIMITED_TO_FIVE_RECIPIENTS_AT_A_TIME;
	
	@ClientString(id = 1231, message = "You've sent mail.")
	public static SystemMessageId YOU_VE_SENT_MAIL;
	
	@ClientString(id = 1232, message = "The message was not sent.")
	public static SystemMessageId THE_MESSAGE_WAS_NOT_SENT;
	
	@ClientString(id = 1233, message = "You've got mail.")
	public static SystemMessageId YOU_VE_GOT_MAIL;
	
	@ClientString(id = 1234, message = "The mail has been stored in your temporary mailbox.")
	public static SystemMessageId THE_MAIL_HAS_BEEN_STORED_IN_YOUR_TEMPORARY_MAILBOX;
	
	@ClientString(id = 1235, message = "Do you wish to delete all your friends?")
	public static SystemMessageId DO_YOU_WISH_TO_DELETE_ALL_YOUR_FRIENDS;
	
	@ClientString(id = 1236, message = "Please enter security card number.")
	public static SystemMessageId PLEASE_ENTER_SECURITY_CARD_NUMBER;
	
	@ClientString(id = 1237, message = "Please enter the card number for number $s1.")
	public static SystemMessageId PLEASE_ENTER_THE_CARD_NUMBER_FOR_NUMBER_S1;
	
	@ClientString(id = 1238, message = "Your temporary mailbox is full. No more mail can be stored; you have reached the 10 message limit.")
	public static SystemMessageId YOUR_TEMPORARY_MAILBOX_IS_FULL_NO_MORE_MAIL_CAN_BE_STORED_YOU_HAVE_REACHED_THE_10_MESSAGE_LIMIT;
	
	@ClientString(id = 1239, message = "The keyboard security module has failed to load. Please exit the game and try again.")
	public static SystemMessageId THE_KEYBOARD_SECURITY_MODULE_HAS_FAILED_TO_LOAD_PLEASE_EXIT_THE_GAME_AND_TRY_AGAIN;
	
	@ClientString(id = 1240, message = "Seven Signs: The Revolutionaries of Dusk have won.")
	public static SystemMessageId SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_WON;
	
	@ClientString(id = 1241, message = "Seven Signs: The Lords of Dawn have won.")
	public static SystemMessageId SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_WON;
	
	@ClientString(id = 1242, message = "Users who have not verified their age may not log in between the hours of 10:00 p.m. and 6:00 a.m.")
	public static SystemMessageId USERS_WHO_HAVE_NOT_VERIFIED_THEIR_AGE_MAY_NOT_LOG_IN_BETWEEN_THE_HOURS_OF_10_00_P_M_AND_6_00_A_M;
	
	@ClientString(id = 1243, message = "The security card number is invalid.")
	public static SystemMessageId THE_SECURITY_CARD_NUMBER_IS_INVALID;
	
	@ClientString(id = 1244, message = "Users who have not verified their age may not log in between the hours of 10:00 p.m. and 6:00 a.m. Logging off now.")
	public static SystemMessageId USERS_WHO_HAVE_NOT_VERIFIED_THEIR_AGE_MAY_NOT_LOG_IN_BETWEEN_THE_HOURS_OF_10_00_P_M_AND_6_00_A_M_LOGGING_OFF_NOW;
	
	@ClientString(id = 1245, message = "You will be logged out in $s1 minutes.")
	public static SystemMessageId YOU_WILL_BE_LOGGED_OUT_IN_S1_MINUTES;
	
	@ClientString(id = 1246, message = "$c1 has died and dropped $s2 adena.")
	public static SystemMessageId C1_HAS_DIED_AND_DROPPED_S2_ADENA;
	
	@ClientString(id = 1247, message = "The corpse is too old. The skill cannot be used.")
	public static SystemMessageId THE_CORPSE_IS_TOO_OLD_THE_SKILL_CANNOT_BE_USED;
	
	@ClientString(id = 1248, message = "You are out of feed. Mount status canceled.")
	public static SystemMessageId YOU_ARE_OUT_OF_FEED_MOUNT_STATUS_CANCELED;
	
	@ClientString(id = 1249, message = "You may only ride a wyvern while you're riding a strider.")
	public static SystemMessageId YOU_MAY_ONLY_RIDE_A_WYVERN_WHILE_YOU_RE_RIDING_A_STRIDER;
	
	@ClientString(id = 1250, message = "Do you really want to surrender? If you surrender during an alliance war, your Exp will drop the same as if you were to die once.")
	public static SystemMessageId DO_YOU_REALLY_WANT_TO_SURRENDER_IF_YOU_SURRENDER_DURING_AN_ALLIANCE_WAR_YOUR_EXP_WILL_DROP_THE_SAME_AS_IF_YOU_WERE_TO_DIE_ONCE;
	
	@ClientString(id = 1251, message = "Are you sure you want to dismiss the alliance? If you use the /allydismiss command, you will not be able to accept another clan to your alliance for one day.")
	public static SystemMessageId ARE_YOU_SURE_YOU_WANT_TO_DISMISS_THE_ALLIANCE_IF_YOU_USE_THE_ALLYDISMISS_COMMAND_YOU_WILL_NOT_BE_ABLE_TO_ACCEPT_ANOTHER_CLAN_TO_YOUR_ALLIANCE_FOR_ONE_DAY;
	
	@ClientString(id = 1252, message = "Are you sure you want to surrender? Exp penalty will be the same as death.")
	public static SystemMessageId ARE_YOU_SURE_YOU_WANT_TO_SURRENDER_EXP_PENALTY_WILL_BE_THE_SAME_AS_DEATH;
	
	@ClientString(id = 1253, message = "Are you sure you want to surrender? Exp penalty will be the same as death and you will not be allowed to participate in clan war.")
	public static SystemMessageId ARE_YOU_SURE_YOU_WANT_TO_SURRENDER_EXP_PENALTY_WILL_BE_THE_SAME_AS_DEATH_AND_YOU_WILL_NOT_BE_ALLOWED_TO_PARTICIPATE_IN_CLAN_WAR;
	
	@ClientString(id = 1254, message = "Thank you for submitting feedback.")
	public static SystemMessageId THANK_YOU_FOR_SUBMITTING_FEEDBACK;
	
	@ClientString(id = 1255, message = "GM consultation has begun.")
	public static SystemMessageId GM_CONSULTATION_HAS_BEGUN;
	
	@ClientString(id = 1256, message = "Please write the name after the command.")
	public static SystemMessageId PLEASE_WRITE_THE_NAME_AFTER_THE_COMMAND;
	
	@ClientString(id = 1257, message = "The special skill of a servitor or pet cannot be registered as a macro.")
	public static SystemMessageId THE_SPECIAL_SKILL_OF_A_SERVITOR_OR_PET_CANNOT_BE_REGISTERED_AS_A_MACRO;
	
	@ClientString(id = 1258, message = "$s1 has been crystallized.")
	public static SystemMessageId S1_HAS_BEEN_CRYSTALLIZED;
	
	@ClientString(id = 1259, message = "=======<Alliance Target>=======")
	public static SystemMessageId ALLIANCE_TARGET;
	
	@ClientString(id = 1260, message = "Seven Signs: Preparations have begun for the next quest event.")
	public static SystemMessageId SEVEN_SIGNS_PREPARATIONS_HAVE_BEGUN_FOR_THE_NEXT_QUEST_EVENT;
	
	@ClientString(id = 1261, message = "Seven Signs: The quest event period has begun. Speak with a Priest of Dawn or Dusk Priestess if you wish to participate in the event.")
	public static SystemMessageId SEVEN_SIGNS_THE_QUEST_EVENT_PERIOD_HAS_BEGUN_SPEAK_WITH_A_PRIEST_OF_DAWN_OR_DUSK_PRIESTESS_IF_YOU_WISH_TO_PARTICIPATE_IN_THE_EVENT;
	
	@ClientString(id = 1262, message = "Seven Signs: Quest event has ended. Results are being tallied.")
	public static SystemMessageId SEVEN_SIGNS_QUEST_EVENT_HAS_ENDED_RESULTS_ARE_BEING_TALLIED;
	
	@ClientString(id = 1263, message = "Seven Signs: This is the seal validation period. A new quest event period begins next Monday.")
	public static SystemMessageId SEVEN_SIGNS_THIS_IS_THE_SEAL_VALIDATION_PERIOD_A_NEW_QUEST_EVENT_PERIOD_BEGINS_NEXT_MONDAY;
	
	@ClientString(id = 1264, message = "This soul stone cannot currently absorb souls. Absorption has failed.")
	public static SystemMessageId THIS_SOUL_STONE_CANNOT_CURRENTLY_ABSORB_SOULS_ABSORPTION_HAS_FAILED;
	
	@ClientString(id = 1265, message = "You can't absorb souls without a soul stone.")
	public static SystemMessageId YOU_CAN_T_ABSORB_SOULS_WITHOUT_A_SOUL_STONE;
	
	@ClientString(id = 1266, message = "The exchange has ended.")
	public static SystemMessageId THE_EXCHANGE_HAS_ENDED;
	
	@ClientString(id = 1267, message = "Your contribution score has increased by $s1.")
	public static SystemMessageId YOUR_CONTRIBUTION_SCORE_HAS_INCREASED_BY_S1;
	
	@ClientString(id = 1268, message = "Do you wish to add $s1 as your sub class?")
	public static SystemMessageId DO_YOU_WISH_TO_ADD_S1_AS_YOUR_SUB_CLASS;
	
	@ClientString(id = 1269, message = "The new subclass has been added.")
	public static SystemMessageId THE_NEW_SUBCLASS_HAS_BEEN_ADDED;
	
	@ClientString(id = 1270, message = "You have successfully switched to your subclass.")
	public static SystemMessageId YOU_HAVE_SUCCESSFULLY_SWITCHED_TO_YOUR_SUBCLASS;
	
	@ClientString(id = 1271, message = "Do you wish to participate? Until the next seal validation period, you will be a member of the Lords of Dawn.")
	public static SystemMessageId DO_YOU_WISH_TO_PARTICIPATE_UNTIL_THE_NEXT_SEAL_VALIDATION_PERIOD_YOU_WILL_BE_A_MEMBER_OF_THE_LORDS_OF_DAWN;
	
	@ClientString(id = 1272, message = "Do you wish to participate? Until the next seal validation period, you will be a member of the Revolutionaries of Dusk.")
	public static SystemMessageId DO_YOU_WISH_TO_PARTICIPATE_UNTIL_THE_NEXT_SEAL_VALIDATION_PERIOD_YOU_WILL_BE_A_MEMBER_OF_THE_REVOLUTIONARIES_OF_DUSK;
	
	@ClientString(id = 1273, message = "You will participate in the Seven Signs as a member of the Lords of Dawn.")
	public static SystemMessageId YOU_WILL_PARTICIPATE_IN_THE_SEVEN_SIGNS_AS_A_MEMBER_OF_THE_LORDS_OF_DAWN;
	
	@ClientString(id = 1274, message = "You will participate in the Seven Signs as a member of the Revolutionaries of Dusk.")
	public static SystemMessageId YOU_WILL_PARTICIPATE_IN_THE_SEVEN_SIGNS_AS_A_MEMBER_OF_THE_REVOLUTIONARIES_OF_DUSK;
	
	@ClientString(id = 1275, message = "You've chosen to fight for the Seal of Avarice during this quest event period.")
	public static SystemMessageId YOU_VE_CHOSEN_TO_FIGHT_FOR_THE_SEAL_OF_AVARICE_DURING_THIS_QUEST_EVENT_PERIOD;
	
	@ClientString(id = 1276, message = "You've chosen to fight for the Seal of Gnosis during this quest event period.")
	public static SystemMessageId YOU_VE_CHOSEN_TO_FIGHT_FOR_THE_SEAL_OF_GNOSIS_DURING_THIS_QUEST_EVENT_PERIOD;
	
	@ClientString(id = 1277, message = "You've chosen to fight for the Seal of Strife during this quest event period.")
	public static SystemMessageId YOU_VE_CHOSEN_TO_FIGHT_FOR_THE_SEAL_OF_STRIFE_DURING_THIS_QUEST_EVENT_PERIOD;
	
	@ClientString(id = 1278, message = "The NPC server is not operating at this time.")
	public static SystemMessageId THE_NPC_SERVER_IS_NOT_OPERATING_AT_THIS_TIME;
	
	@ClientString(id = 1279, message = "Contribution level has exceeded the limit. You may not continue.")
	public static SystemMessageId CONTRIBUTION_LEVEL_HAS_EXCEEDED_THE_LIMIT_YOU_MAY_NOT_CONTINUE;
	
	@ClientString(id = 1280, message = "Magic Critical Hit!")
	public static SystemMessageId MAGIC_CRITICAL_HIT;
	
	@ClientString(id = 1281, message = "Your excellent shield defense was a success!")
	public static SystemMessageId YOUR_EXCELLENT_SHIELD_DEFENSE_WAS_A_SUCCESS;
	
	@ClientString(id = 1282, message = "Your Karma has been changed to $s1.")
	public static SystemMessageId YOUR_KARMA_HAS_BEEN_CHANGED_TO_S1;
	
	@ClientString(id = 1283, message = "The Lower Detail option has been activated.")
	public static SystemMessageId THE_LOWER_DETAIL_OPTION_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 1284, message = "The Lower Detail option has been deactivated.")
	public static SystemMessageId THE_LOWER_DETAIL_OPTION_HAS_BEEN_DEACTIVATED;
	
	@ClientString(id = 1285, message = "No inventory exists. You cannot purchase an item.")
	public static SystemMessageId NO_INVENTORY_EXISTS_YOU_CANNOT_PURCHASE_AN_ITEM;
	
	@ClientString(id = 1286, message = "(Until next Monday at 6:00 p.m.)")
	public static SystemMessageId UNTIL_NEXT_MONDAY_AT_6_00_P_M;
	
	@ClientString(id = 1287, message = "(Until today at 6:00 p.m.)")
	public static SystemMessageId UNTIL_TODAY_AT_6_00_P_M;
	
	@ClientString(id = 1288, message = "If trends continue, $s1 will win and the seal will belong to:")
	public static SystemMessageId IF_TRENDS_CONTINUE_S1_WILL_WIN_AND_THE_SEAL_WILL_BELONG_TO;
	
	@ClientString(id = 1289, message = "Since the seal was owned during the previous period and 10% or more people have participated.")
	public static SystemMessageId SINCE_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_10_OR_MORE_PEOPLE_HAVE_PARTICIPATED;
	
	@ClientString(id = 1290, message = "Although the seal was not owned, 35% or more people have participated.")
	public static SystemMessageId ALTHOUGH_THE_SEAL_WAS_NOT_OWNED_35_OR_MORE_PEOPLE_HAVE_PARTICIPATED;
	
	@ClientString(id = 1291, message = "Although the seal was owned during the previous period, less than 10% of people have voted.")
	public static SystemMessageId ALTHOUGH_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_LESS_THAN_10_OF_PEOPLE_HAVE_VOTED;
	
	@ClientString(id = 1292, message = "Since the seal was not owned during the previous period, and since less than 35 percent of people have voted.")
	public static SystemMessageId SINCE_THE_SEAL_WAS_NOT_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_SINCE_LESS_THAN_35_PERCENT_OF_PEOPLE_HAVE_VOTED;
	
	@ClientString(id = 1293, message = "If current trends continue, it will end in a tie.")
	public static SystemMessageId IF_CURRENT_TRENDS_CONTINUE_IT_WILL_END_IN_A_TIE;
	
	@ClientString(id = 1294, message = "The competition has ended in a tie. Therefore, nobody has been awarded the seal.")
	public static SystemMessageId THE_COMPETITION_HAS_ENDED_IN_A_TIE_THEREFORE_NOBODY_HAS_BEEN_AWARDED_THE_SEAL;
	
	@ClientString(id = 1295, message = "Subclasses may not be created or changed while a skill is in use.")
	public static SystemMessageId SUBCLASSES_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SKILL_IS_IN_USE;
	
	@ClientString(id = 1296, message = "You cannot open a Private Store here.")
	public static SystemMessageId YOU_CANNOT_OPEN_A_PRIVATE_STORE_HERE;
	
	@ClientString(id = 1297, message = "You cannot open a Private Workshop here.")
	public static SystemMessageId YOU_CANNOT_OPEN_A_PRIVATE_WORKSHOP_HERE;
	
	@ClientString(id = 1298, message = "Please confirm that you would like to exit the Monster Race Track.")
	public static SystemMessageId PLEASE_CONFIRM_THAT_YOU_WOULD_LIKE_TO_EXIT_THE_MONSTER_RACE_TRACK;
	
	@ClientString(id = 1299, message = "$c1's casting has been interrupted.")
	public static SystemMessageId C1_S_CASTING_HAS_BEEN_INTERRUPTED;
	
	@ClientString(id = 1300, message = "You are no longer trying on equipment.")
	public static SystemMessageId YOU_ARE_NO_LONGER_TRYING_ON_EQUIPMENT;
	
	@ClientString(id = 1301, message = "Only a Lord of Dawn may use this.")
	public static SystemMessageId ONLY_A_LORD_OF_DAWN_MAY_USE_THIS;
	
	@ClientString(id = 1302, message = "Only a Revolutionary of Dusk may use this.")
	public static SystemMessageId ONLY_A_REVOLUTIONARY_OF_DUSK_MAY_USE_THIS;
	
	@ClientString(id = 1303, message = "This may only be used during the quest event period.")
	public static SystemMessageId THIS_MAY_ONLY_BE_USED_DURING_THE_QUEST_EVENT_PERIOD;
	
	@ClientString(id = 1304, message = "The influence of the Seal of Strife has caused all defensive registrations to be canceled.")
	public static SystemMessageId THE_INFLUENCE_OF_THE_SEAL_OF_STRIFE_HAS_CAUSED_ALL_DEFENSIVE_REGISTRATIONS_TO_BE_CANCELED;
	
	@ClientString(id = 1305, message = "Seal Stones may only be transferred during the quest event period.")
	public static SystemMessageId SEAL_STONES_MAY_ONLY_BE_TRANSFERRED_DURING_THE_QUEST_EVENT_PERIOD;
	
	@ClientString(id = 1306, message = "You are no longer trying on equipment.")
	public static SystemMessageId YOU_ARE_NO_LONGER_TRYING_ON_EQUIPMENT_2;
	
	@ClientString(id = 1307, message = "Only during the seal validation period may you settle your account.")
	public static SystemMessageId ONLY_DURING_THE_SEAL_VALIDATION_PERIOD_MAY_YOU_SETTLE_YOUR_ACCOUNT;
	
	@ClientString(id = 1308, message = "Congratulations - You've completed a class transfer!")
	public static SystemMessageId CONGRATULATIONS_YOU_VE_COMPLETED_A_CLASS_TRANSFER;
	
	@ClientString(id = 1309, message = "To use this option, you must have the latest version of MSN Messenger installed on your computer.")
	public static SystemMessageId TO_USE_THIS_OPTION_YOU_MUST_HAVE_THE_LATEST_VERSION_OF_MSN_MESSENGER_INSTALLED_ON_YOUR_COMPUTER;
	
	@ClientString(id = 1310, message = "For full functionality, the latest version of MSN Messenger must be installed on your computer.")
	public static SystemMessageId FOR_FULL_FUNCTIONALITY_THE_LATEST_VERSION_OF_MSN_MESSENGER_MUST_BE_INSTALLED_ON_YOUR_COMPUTER;
	
	@ClientString(id = 1311, message = "Previous versions of MSN Messenger only provide the basic features for in-game MSN Messenger chat. Add/Delete Contacts and other MSN Messenger options are not available.")
	public static SystemMessageId PREVIOUS_VERSIONS_OF_MSN_MESSENGER_ONLY_PROVIDE_THE_BASIC_FEATURES_FOR_IN_GAME_MSN_MESSENGER_CHAT_ADD_DELETE_CONTACTS_AND_OTHER_MSN_MESSENGER_OPTIONS_ARE_NOT_AVAILABLE;
	
	@ClientString(id = 1312, message = "The latest version of MSN Messenger may be obtained from the MSN web site (http://messenger.msn.com).")
	public static SystemMessageId THE_LATEST_VERSION_OF_MSN_MESSENGER_MAY_BE_OBTAINED_FROM_THE_MSN_WEB_SITE_HTTP_MESSENGER_MSN_COM;
	
	@ClientString(id = 1313, message = "$s1, to better server our customers, all chat histories are stored and maintained by NCsoft. If you do not agree to have your chat records stored, please close the chat window now. For more information regarding this procedure, please visit our home page at www.PlayNC.com. Thank you!")
	public static SystemMessageId S1_TO_BETTER_SERVER_OUR_CUSTOMERS_ALL_CHAT_HISTORIES_ARE_STORED_AND_MAINTAINED_BY_NCSOFT_IF_YOU_DO_NOT_AGREE_TO_HAVE_YOUR_CHAT_RECORDS_STORED_PLEASE_CLOSE_THE_CHAT_WINDOW_NOW_FOR_MORE_INFORMATION_REGARDING_THIS_PROCEDURE_PLEASE_VISIT_OUR_HOME_PAGE_AT_WWW_PLAYNC_COM_THANK_YOU;
	
	@ClientString(id = 1314, message = "Please enter the passport ID of the person you wish to add to your contact list.")
	public static SystemMessageId PLEASE_ENTER_THE_PASSPORT_ID_OF_THE_PERSON_YOU_WISH_TO_ADD_TO_YOUR_CONTACT_LIST;
	
	@ClientString(id = 1315, message = "Deleting a contact will remove that contact from MSN Messenger as well. The contact can still check your online status and will not be blocked from sending you a message.")
	public static SystemMessageId DELETING_A_CONTACT_WILL_REMOVE_THAT_CONTACT_FROM_MSN_MESSENGER_AS_WELL_THE_CONTACT_CAN_STILL_CHECK_YOUR_ONLINE_STATUS_AND_WILL_NOT_BE_BLOCKED_FROM_SENDING_YOU_A_MESSAGE;
	
	@ClientString(id = 1316, message = "The contact will be deleted and blocked from your contact list.")
	public static SystemMessageId THE_CONTACT_WILL_BE_DELETED_AND_BLOCKED_FROM_YOUR_CONTACT_LIST;
	
	@ClientString(id = 1317, message = "Would you like to delete this contact?")
	public static SystemMessageId WOULD_YOU_LIKE_TO_DELETE_THIS_CONTACT;
	
	@ClientString(id = 1318, message = "Please select the contact you want to block or unblock.")
	public static SystemMessageId PLEASE_SELECT_THE_CONTACT_YOU_WANT_TO_BLOCK_OR_UNBLOCK;
	
	@ClientString(id = 1319, message = "Please select the name of the contact you wish to change to another group.")
	public static SystemMessageId PLEASE_SELECT_THE_NAME_OF_THE_CONTACT_YOU_WISH_TO_CHANGE_TO_ANOTHER_GROUP;
	
	@ClientString(id = 1320, message = "After selecting the group you wish to move your contact to, press the OK button.")
	public static SystemMessageId AFTER_SELECTING_THE_GROUP_YOU_WISH_TO_MOVE_YOUR_CONTACT_TO_PRESS_THE_OK_BUTTON;
	
	@ClientString(id = 1321, message = "Enter the name of the group you wish to add.")
	public static SystemMessageId ENTER_THE_NAME_OF_THE_GROUP_YOU_WISH_TO_ADD;
	
	@ClientString(id = 1322, message = "Select the group and enter the new name.")
	public static SystemMessageId SELECT_THE_GROUP_AND_ENTER_THE_NEW_NAME;
	
	@ClientString(id = 1323, message = "Select the group you wish to delete and click the OK button.")
	public static SystemMessageId SELECT_THE_GROUP_YOU_WISH_TO_DELETE_AND_CLICK_THE_OK_BUTTON;
	
	@ClientString(id = 1324, message = "Signing in...")
	public static SystemMessageId SIGNING_IN;
	
	@ClientString(id = 1325, message = "You've logged into another computer and have been logged out of the .NET Messenger Service on this computer.")
	public static SystemMessageId YOU_VE_LOGGED_INTO_ANOTHER_COMPUTER_AND_HAVE_BEEN_LOGGED_OUT_OF_THE_NET_MESSENGER_SERVICE_ON_THIS_COMPUTER;
	
	@ClientString(id = 1326, message = "$s1:")
	public static SystemMessageId S1;
	
	@ClientString(id = 1327, message = "The following message could not be delivered:")
	public static SystemMessageId THE_FOLLOWING_MESSAGE_COULD_NOT_BE_DELIVERED;
	
	@ClientString(id = 1328, message = "Members of the Revolutionaries of Dusk will not be resurrected.")
	public static SystemMessageId MEMBERS_OF_THE_REVOLUTIONARIES_OF_DUSK_WILL_NOT_BE_RESURRECTED;
	
	@ClientString(id = 1329, message = "You are currently blocked from using the Private Store and Private Workshop.")
	public static SystemMessageId YOU_ARE_CURRENTLY_BLOCKED_FROM_USING_THE_PRIVATE_STORE_AND_PRIVATE_WORKSHOP;
	
	@ClientString(id = 1330, message = "You may not open a Private Store or Private Workshop for another $s1 minute(s).")
	public static SystemMessageId YOU_MAY_NOT_OPEN_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP_FOR_ANOTHER_S1_MINUTE_S;
	
	@ClientString(id = 1331, message = "You are no longer blocked from using Private Stores or Private Workshops.")
	public static SystemMessageId YOU_ARE_NO_LONGER_BLOCKED_FROM_USING_PRIVATE_STORES_OR_PRIVATE_WORKSHOPS;
	
	@ClientString(id = 1332, message = "Items may not be used after your character or pet dies.")
	public static SystemMessageId ITEMS_MAY_NOT_BE_USED_AFTER_YOUR_CHARACTER_OR_PET_DIES;
	
	@ClientString(id = 1333, message = "The replay file is not accessible. Please verify that the replay.ini file exists in your Lineage 2 directory. Please note that footage from previous major updates are not accessible in newer updates.")
	public static SystemMessageId THE_REPLAY_FILE_IS_NOT_ACCESSIBLE_PLEASE_VERIFY_THAT_THE_REPLAY_INI_FILE_EXISTS_IN_YOUR_LINEAGE_2_DIRECTORY_PLEASE_NOTE_THAT_FOOTAGE_FROM_PREVIOUS_MAJOR_UPDATES_ARE_NOT_ACCESSIBLE_IN_NEWER_UPDATES;
	
	@ClientString(id = 1334, message = "Your recording has been stored in the Replay folder.")
	public static SystemMessageId YOUR_RECORDING_HAS_BEEN_STORED_IN_THE_REPLAY_FOLDER;
	
	@ClientString(id = 1335, message = "Your attempt to store this recording has failed.")
	public static SystemMessageId YOUR_ATTEMPT_TO_STORE_THIS_RECORDING_HAS_FAILED;
	
	@ClientString(id = 1336, message = "The replay file, $s1.$s2 has been corrupted, please check the file.")
	public static SystemMessageId THE_REPLAY_FILE_S1_S2_HAS_BEEN_CORRUPTED_PLEASE_CHECK_THE_FILE;
	
	@ClientString(id = 1337, message = "This will terminate the replay. Do you wish to continue?")
	public static SystemMessageId THIS_WILL_TERMINATE_THE_REPLAY_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 1338, message = "You have exceeded the maximum amount that may be transferred at one time.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_THE_MAXIMUM_AMOUNT_THAT_MAY_BE_TRANSFERRED_AT_ONE_TIME;
	
	@ClientString(id = 1339, message = "Once a macro is assigned to a shortcut, it cannot be run as part of a new macro.")
	public static SystemMessageId ONCE_A_MACRO_IS_ASSIGNED_TO_A_SHORTCUT_IT_CANNOT_BE_RUN_AS_PART_OF_A_NEW_MACRO;
	
	@ClientString(id = 1340, message = "This server cannot be accessed with the coupon you are using.")
	public static SystemMessageId THIS_SERVER_CANNOT_BE_ACCESSED_WITH_THE_COUPON_YOU_ARE_USING;
	
	@ClientString(id = 1341, message = "Incorrect name and/or email address.")
	public static SystemMessageId INCORRECT_NAME_AND_OR_EMAIL_ADDRESS;
	
	@ClientString(id = 1342, message = "You are already logged in.")
	public static SystemMessageId YOU_ARE_ALREADY_LOGGED_IN;
	
	@ClientString(id = 1343, message = "Incorrect email address and/or password. Your attempt to log into .NET Messenger Service has failed.")
	public static SystemMessageId INCORRECT_EMAIL_ADDRESS_AND_OR_PASSWORD_YOUR_ATTEMPT_TO_LOG_INTO_NET_MESSENGER_SERVICE_HAS_FAILED;
	
	@ClientString(id = 1344, message = "Your request to log into the .NET Messenger Service has failed. Please verify that you are currently connected to the internet.")
	public static SystemMessageId YOUR_REQUEST_TO_LOG_INTO_THE_NET_MESSENGER_SERVICE_HAS_FAILED_PLEASE_VERIFY_THAT_YOU_ARE_CURRENTLY_CONNECTED_TO_THE_INTERNET;
	
	@ClientString(id = 1345, message = "Click on the OK button after you have selected a contact name.")
	public static SystemMessageId CLICK_ON_THE_OK_BUTTON_AFTER_YOU_HAVE_SELECTED_A_CONTACT_NAME;
	
	@ClientString(id = 1346, message = "You are currently entering a chat message.")
	public static SystemMessageId YOU_ARE_CURRENTLY_ENTERING_A_CHAT_MESSAGE;
	
	@ClientString(id = 1347, message = "The Lineage II messenger could not carry out the task you requested.")
	public static SystemMessageId THE_LINEAGE_II_MESSENGER_COULD_NOT_CARRY_OUT_THE_TASK_YOU_REQUESTED;
	
	@ClientString(id = 1348, message = "$s1 has entered the chat room.")
	public static SystemMessageId S1_HAS_ENTERED_THE_CHAT_ROOM;
	
	@ClientString(id = 1349, message = "$s1 has left the chat room.")
	public static SystemMessageId S1_HAS_LEFT_THE_CHAT_ROOM;
	
	@ClientString(id = 1350, message = "Your status will be changed to indicate that you are 'off-line.' All chat windows currently open will be closed.")
	public static SystemMessageId YOUR_STATUS_WILL_BE_CHANGED_TO_INDICATE_THAT_YOU_ARE_OFF_LINE_ALL_CHAT_WINDOWS_CURRENTLY_OPEN_WILL_BE_CLOSED;
	
	@ClientString(id = 1351, message = "Click the Delete button after selecting the contact you wish to remove.")
	public static SystemMessageId CLICK_THE_DELETE_BUTTON_AFTER_SELECTING_THE_CONTACT_YOU_WISH_TO_REMOVE;
	
	@ClientString(id = 1352, message = "You have been added to $s1 ($s2)'s contact list.")
	public static SystemMessageId YOU_HAVE_BEEN_ADDED_TO_S1_S2_S_CONTACT_LIST;
	
	@ClientString(id = 1353, message = "You can set the option to show your status as always being off-line to all of your contacts.")
	public static SystemMessageId YOU_CAN_SET_THE_OPTION_TO_SHOW_YOUR_STATUS_AS_ALWAYS_BEING_OFF_LINE_TO_ALL_OF_YOUR_CONTACTS;
	
	@ClientString(id = 1354, message = "You are not allowed to chat with a contact while a chatting block is imposed.")
	public static SystemMessageId YOU_ARE_NOT_ALLOWED_TO_CHAT_WITH_A_CONTACT_WHILE_A_CHATTING_BLOCK_IS_IMPOSED;
	
	@ClientString(id = 1355, message = "That contact is currently blocked from chatting.")
	public static SystemMessageId THAT_CONTACT_IS_CURRENTLY_BLOCKED_FROM_CHATTING;
	
	@ClientString(id = 1356, message = "That contact is not currently logged in.")
	public static SystemMessageId THAT_CONTACT_IS_NOT_CURRENTLY_LOGGED_IN;
	
	@ClientString(id = 1357, message = "You have been blocked from chatting with that contact.")
	public static SystemMessageId YOU_HAVE_BEEN_BLOCKED_FROM_CHATTING_WITH_THAT_CONTACT;
	
	@ClientString(id = 1358, message = "You are being logged out...")
	public static SystemMessageId YOU_ARE_BEING_LOGGED_OUT;
	
	@ClientString(id = 1359, message = "$s1 has logged in.")
	public static SystemMessageId S1_HAS_LOGGED_IN_2;
	
	@ClientString(id = 1360, message = "You have received a message from $s1.")
	public static SystemMessageId YOU_HAVE_RECEIVED_A_MESSAGE_FROM_S1;
	
	@ClientString(id = 1361, message = "Due to a system error, you have been logged out of the .NET Messenger Service.")
	public static SystemMessageId DUE_TO_A_SYSTEM_ERROR_YOU_HAVE_BEEN_LOGGED_OUT_OF_THE_NET_MESSENGER_SERVICE;
	
	@ClientString(id = 1362, message = "Please select the contact you wish to delete. If you would like to delete a group, click the button next to My Status, and then use the Options menu.")
	public static SystemMessageId PLEASE_SELECT_THE_CONTACT_YOU_WISH_TO_DELETE_IF_YOU_WOULD_LIKE_TO_DELETE_A_GROUP_CLICK_THE_BUTTON_NEXT_TO_MY_STATUS_AND_THEN_USE_THE_OPTIONS_MENU;
	
	@ClientString(id = 1363, message = "Your request to participate to initiate an alliance war has been denied.")
	public static SystemMessageId YOUR_REQUEST_TO_PARTICIPATE_TO_INITIATE_AN_ALLIANCE_WAR_HAS_BEEN_DENIED;
	
	@ClientString(id = 1364, message = "The request for an alliance war has been rejected.")
	public static SystemMessageId THE_REQUEST_FOR_AN_ALLIANCE_WAR_HAS_BEEN_REJECTED;
	
	@ClientString(id = 1365, message = "$s2 of $s1 clan has surrendered as an individual.")
	public static SystemMessageId S2_OF_S1_CLAN_HAS_SURRENDERED_AS_AN_INDIVIDUAL;
	
	@ClientString(id = 1366, message = "In order to delete a group, you must not have any contacts listed under that group. Please transfer your contact(s) to another group before continuing with deletion.")
	public static SystemMessageId IN_ORDER_TO_DELETE_A_GROUP_YOU_MUST_NOT_HAVE_ANY_CONTACTS_LISTED_UNDER_THAT_GROUP_PLEASE_TRANSFER_YOUR_CONTACT_S_TO_ANOTHER_GROUP_BEFORE_CONTINUING_WITH_DELETION;
	
	@ClientString(id = 1367, message = "Only members of the group are allowed to add records.")
	public static SystemMessageId ONLY_MEMBERS_OF_THE_GROUP_ARE_ALLOWED_TO_ADD_RECORDS;
	
	@ClientString(id = 1368, message = "You can not try those items on at the same time.")
	public static SystemMessageId YOU_CAN_NOT_TRY_THOSE_ITEMS_ON_AT_THE_SAME_TIME;
	
	@ClientString(id = 1369, message = "You've exceeded the maximum.")
	public static SystemMessageId YOU_VE_EXCEEDED_THE_MAXIMUM;
	
	@ClientString(id = 1370, message = "Your message to $c1 did not reach its recipient. You cannot send mail to the GM staff.")
	public static SystemMessageId YOUR_MESSAGE_TO_C1_DID_NOT_REACH_ITS_RECIPIENT_YOU_CANNOT_SEND_MAIL_TO_THE_GM_STAFF;
	
	@ClientString(id = 1371, message = "It has been determined that you're not engaged in normal gameplay and a restriction has been imposed upon you. You may not move for $s1 minutes.")
	public static SystemMessageId IT_HAS_BEEN_DETERMINED_THAT_YOU_RE_NOT_ENGAGED_IN_NORMAL_GAMEPLAY_AND_A_RESTRICTION_HAS_BEEN_IMPOSED_UPON_YOU_YOU_MAY_NOT_MOVE_FOR_S1_MINUTES;
	
	@ClientString(id = 1372, message = "Your punishment will continue for $s1 minutes.")
	public static SystemMessageId YOUR_PUNISHMENT_WILL_CONTINUE_FOR_S1_MINUTES;
	
	@ClientString(id = 1373, message = "$c1 has picked up $s2 that was dropped by the Raid Boss.")
	public static SystemMessageId C1_HAS_PICKED_UP_S2_THAT_WAS_DROPPED_BY_THE_RAID_BOSS;
	
	@ClientString(id = 1374, message = "$c1 has picked up $s3 $s2(s) that were dropped by the Raid Boss.")
	public static SystemMessageId C1_HAS_PICKED_UP_S3_S2_S_THAT_WERE_DROPPED_BY_THE_RAID_BOSS;
	
	@ClientString(id = 1375, message = "$c1 has picked up $s2 adena that was dropped by the Raid Boss.")
	public static SystemMessageId C1_HAS_PICKED_UP_S2_ADENA_THAT_WAS_DROPPED_BY_THE_RAID_BOSS;
	
	@ClientString(id = 1376, message = "$c1 has picked up $s2 that was dropped by another character.")
	public static SystemMessageId C1_HAS_PICKED_UP_S2_THAT_WAS_DROPPED_BY_ANOTHER_CHARACTER;
	
	@ClientString(id = 1377, message = "$c1 has picked up $s3 $s2(s) that were dropped by another character.")
	public static SystemMessageId C1_HAS_PICKED_UP_S3_S2_S_THAT_WERE_DROPPED_BY_ANOTHER_CHARACTER;
	
	@ClientString(id = 1378, message = "$c1 has picked up +$s3 $s2 that was dropped by another character.")
	public static SystemMessageId C1_HAS_PICKED_UP_S3_S2_THAT_WAS_DROPPED_BY_ANOTHER_CHARACTER;
	
	@ClientString(id = 1379, message = "$c1 has obtained $s2 adena.")
	public static SystemMessageId C1_HAS_OBTAINED_S2_ADENA;
	
	@ClientString(id = 1380, message = "You can't summon a $s1 while on the battleground.")
	public static SystemMessageId YOU_CAN_T_SUMMON_A_S1_WHILE_ON_THE_BATTLEGROUND;
	
	@ClientString(id = 1381, message = "The party leader has obtained $s2 of $s1.")
	public static SystemMessageId THE_PARTY_LEADER_HAS_OBTAINED_S2_OF_S1;
	
	@ClientString(id = 1382, message = "To fulfill the quest, you must bring the chosen weapon. Are you sure you want to choose this weapon?")
	public static SystemMessageId TO_FULFILL_THE_QUEST_YOU_MUST_BRING_THE_CHOSEN_WEAPON_ARE_YOU_SURE_YOU_WANT_TO_CHOOSE_THIS_WEAPON;
	
	@ClientString(id = 1383, message = "Are you sure you want to exchange?")
	public static SystemMessageId ARE_YOU_SURE_YOU_WANT_TO_EXCHANGE;
	
	@ClientString(id = 1384, message = "$c1 has become the party leader.")
	public static SystemMessageId C1_HAS_BECOME_THE_PARTY_LEADER;
	
	@ClientString(id = 1385, message = "You are not allowed to dismount in this location.")
	public static SystemMessageId YOU_ARE_NOT_ALLOWED_TO_DISMOUNT_IN_THIS_LOCATION;
	
	@ClientString(id = 1386, message = "You are no longer immobile.")
	public static SystemMessageId YOU_ARE_NO_LONGER_IMMOBILE;
	
	@ClientString(id = 1387, message = "Please select the item you would like to try on.")
	public static SystemMessageId PLEASE_SELECT_THE_ITEM_YOU_WOULD_LIKE_TO_TRY_ON;
	
	@ClientString(id = 1388, message = "You have created a party room.")
	public static SystemMessageId YOU_HAVE_CREATED_A_PARTY_ROOM;
	
	@ClientString(id = 1389, message = "The party room's information has been revised.")
	public static SystemMessageId THE_PARTY_ROOM_S_INFORMATION_HAS_BEEN_REVISED;
	
	@ClientString(id = 1390, message = "You are not allowed to enter the party room.")
	public static SystemMessageId YOU_ARE_NOT_ALLOWED_TO_ENTER_THE_PARTY_ROOM;
	
	@ClientString(id = 1391, message = "You have exited the party room.")
	public static SystemMessageId YOU_HAVE_EXITED_THE_PARTY_ROOM;
	
	@ClientString(id = 1392, message = "$c1 has left the party room.")
	public static SystemMessageId C1_HAS_LEFT_THE_PARTY_ROOM;
	
	@ClientString(id = 1393, message = "You have been ousted from the party room.")
	public static SystemMessageId YOU_HAVE_BEEN_OUSTED_FROM_THE_PARTY_ROOM;
	
	@ClientString(id = 1394, message = "$c1 has been kicked from the party room.")
	public static SystemMessageId C1_HAS_BEEN_KICKED_FROM_THE_PARTY_ROOM;
	
	@ClientString(id = 1395, message = "The party room has been disbanded.")
	public static SystemMessageId THE_PARTY_ROOM_HAS_BEEN_DISBANDED;
	
	@ClientString(id = 1396, message = "The list of party rooms can only be viewed by a person who is not part of a party.")
	public static SystemMessageId THE_LIST_OF_PARTY_ROOMS_CAN_ONLY_BE_VIEWED_BY_A_PERSON_WHO_IS_NOT_PART_OF_A_PARTY;
	
	@ClientString(id = 1397, message = "The leader of the party room has changed.")
	public static SystemMessageId THE_LEADER_OF_THE_PARTY_ROOM_HAS_CHANGED;
	
	@ClientString(id = 1398, message = "We are recruiting party members.")
	public static SystemMessageId WE_ARE_RECRUITING_PARTY_MEMBERS;
	
	@ClientString(id = 1399, message = "Only the leader of the party can transfer party leadership to another player.")
	public static SystemMessageId ONLY_THE_LEADER_OF_THE_PARTY_CAN_TRANSFER_PARTY_LEADERSHIP_TO_ANOTHER_PLAYER;
	
	@ClientString(id = 1400, message = "Please select the person you wish to make the party leader.")
	public static SystemMessageId PLEASE_SELECT_THE_PERSON_YOU_WISH_TO_MAKE_THE_PARTY_LEADER;
	
	@ClientString(id = 1401, message = "Slow down, you are already the party leader.")
	public static SystemMessageId SLOW_DOWN_YOU_ARE_ALREADY_THE_PARTY_LEADER;
	
	@ClientString(id = 1402, message = "You may only transfer party leadership to another member of the party.")
	public static SystemMessageId YOU_MAY_ONLY_TRANSFER_PARTY_LEADERSHIP_TO_ANOTHER_MEMBER_OF_THE_PARTY;
	
	@ClientString(id = 1403, message = "You have failed to transfer party leadership.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_TRANSFER_PARTY_LEADERSHIP;
	
	@ClientString(id = 1404, message = "The owner of the private manufacturing store has changed the price for creating this item. Please check the new price before trying again.")
	public static SystemMessageId THE_OWNER_OF_THE_PRIVATE_MANUFACTURING_STORE_HAS_CHANGED_THE_PRICE_FOR_CREATING_THIS_ITEM_PLEASE_CHECK_THE_NEW_PRICE_BEFORE_TRYING_AGAIN;
	
	@ClientString(id = 1405, message = "$s1 CP has been restored.")
	public static SystemMessageId S1_CP_HAS_BEEN_RESTORED;
	
	@ClientString(id = 1406, message = "$s2 CP has been restored by $c1.")
	public static SystemMessageId S2_CP_HAS_BEEN_RESTORED_BY_C1;
	
	@ClientString(id = 1407, message = "You are using a computer that does not allow you to log in with two accounts at the same time.")
	public static SystemMessageId YOU_ARE_USING_A_COMPUTER_THAT_DOES_NOT_ALLOW_YOU_TO_LOG_IN_WITH_TWO_ACCOUNTS_AT_THE_SAME_TIME;
	
	@ClientString(id = 1408, message = "Your prepaid remaining usage time is $s1 hours and $s2 minutes. You have $s3 paid reservations left.")
	public static SystemMessageId YOUR_PREPAID_REMAINING_USAGE_TIME_IS_S1_HOURS_AND_S2_MINUTES_YOU_HAVE_S3_PAID_RESERVATIONS_LEFT;
	
	@ClientString(id = 1409, message = "Your prepaid usage time has expired. Your new prepaid reservation will be used. The remaining usage time is $s1 hours and $s2 minutes.")
	public static SystemMessageId YOUR_PREPAID_USAGE_TIME_HAS_EXPIRED_YOUR_NEW_PREPAID_RESERVATION_WILL_BE_USED_THE_REMAINING_USAGE_TIME_IS_S1_HOURS_AND_S2_MINUTES;
	
	@ClientString(id = 1410, message = "Your prepaid usage time has expired. You do not have any more prepaid reservations left.")
	public static SystemMessageId YOUR_PREPAID_USAGE_TIME_HAS_EXPIRED_YOU_DO_NOT_HAVE_ANY_MORE_PREPAID_RESERVATIONS_LEFT;
	
	@ClientString(id = 1411, message = "The number of your prepaid reservations has changed.")
	public static SystemMessageId THE_NUMBER_OF_YOUR_PREPAID_RESERVATIONS_HAS_CHANGED;
	
	@ClientString(id = 1412, message = "Your prepaid usage time has $s1 minutes left.")
	public static SystemMessageId YOUR_PREPAID_USAGE_TIME_HAS_S1_MINUTES_LEFT;
	
	@ClientString(id = 1413, message = "You do not meet the requirements to enter that party room.")
	public static SystemMessageId YOU_DO_NOT_MEET_THE_REQUIREMENTS_TO_ENTER_THAT_PARTY_ROOM;
	
	@ClientString(id = 1414, message = "The width and length should be 100 or more grids and less than 5000 grids respectively.")
	public static SystemMessageId THE_WIDTH_AND_LENGTH_SHOULD_BE_100_OR_MORE_GRIDS_AND_LESS_THAN_5000_GRIDS_RESPECTIVELY;
	
	@ClientString(id = 1415, message = "The command file is not set.")
	public static SystemMessageId THE_COMMAND_FILE_IS_NOT_SET;
	
	@ClientString(id = 1416, message = "The party representative of Team 1 has not been selected.")
	public static SystemMessageId THE_PARTY_REPRESENTATIVE_OF_TEAM_1_HAS_NOT_BEEN_SELECTED;
	
	@ClientString(id = 1417, message = "The party representative of Team 2 has not been selected.")
	public static SystemMessageId THE_PARTY_REPRESENTATIVE_OF_TEAM_2_HAS_NOT_BEEN_SELECTED;
	
	@ClientString(id = 1418, message = "The name of Team 1 has not yet been chosen.")
	public static SystemMessageId THE_NAME_OF_TEAM_1_HAS_NOT_YET_BEEN_CHOSEN;
	
	@ClientString(id = 1419, message = "The name of Team 2 has not yet been chosen.")
	public static SystemMessageId THE_NAME_OF_TEAM_2_HAS_NOT_YET_BEEN_CHOSEN;
	
	@ClientString(id = 1420, message = "The name of Team 1 and the name of Team 2 are identical.")
	public static SystemMessageId THE_NAME_OF_TEAM_1_AND_THE_NAME_OF_TEAM_2_ARE_IDENTICAL;
	
	@ClientString(id = 1421, message = "The race setup file has not been designated.")
	public static SystemMessageId THE_RACE_SETUP_FILE_HAS_NOT_BEEN_DESIGNATED;
	
	@ClientString(id = 1422, message = "Race setup file error - BuffCnt is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_BUFFCNT_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1423, message = "Race setup file error - BuffID$s1 is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_BUFFID_S1_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1424, message = "Race setup file error - BuffLv$s1 is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_BUFFLV_S1_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1425, message = "Race setup file error - DefaultAllow is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_DEFAULTALLOW_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1426, message = "Race setup file error - ExpSkillCnt is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_EXPSKILLCNT_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1427, message = "Race setup file error - ExpSkillID$s1 is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_EXPSKILLID_S1_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1428, message = "Race setup file error - ExpItemCnt is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_EXPITEMCNT_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1429, message = "Race setup file error - ExpItemID$s1 is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_EXPITEMID_S1_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1430, message = "Race setup file error - TeleportDelay is not specified.")
	public static SystemMessageId RACE_SETUP_FILE_ERROR_TELEPORTDELAY_IS_NOT_SPECIFIED;
	
	@ClientString(id = 1431, message = "The race will be stopped temporarily.")
	public static SystemMessageId THE_RACE_WILL_BE_STOPPED_TEMPORARILY;
	
	@ClientString(id = 1432, message = "Your opponent is currently in a petrified state.")
	public static SystemMessageId YOUR_OPPONENT_IS_CURRENTLY_IN_A_PETRIFIED_STATE;
	
	@ClientString(id = 1433, message = "The automatic use of $s1 has been activated.")
	public static SystemMessageId THE_AUTOMATIC_USE_OF_S1_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 1434, message = "The automatic use of $s1 has been deactivated.")
	public static SystemMessageId THE_AUTOMATIC_USE_OF_S1_HAS_BEEN_DEACTIVATED;
	
	@ClientString(id = 1435, message = "Due to insufficient $s1, the automatic use function has been deactivated.")
	public static SystemMessageId DUE_TO_INSUFFICIENT_S1_THE_AUTOMATIC_USE_FUNCTION_HAS_BEEN_DEACTIVATED;
	
	@ClientString(id = 1436, message = "Due to insufficient $s1, the automatic use function cannot be activated.")
	public static SystemMessageId DUE_TO_INSUFFICIENT_S1_THE_AUTOMATIC_USE_FUNCTION_CANNOT_BE_ACTIVATED;
	
	@ClientString(id = 1437, message = "Players are no longer allowed to play dice. Dice can no longer be purchased from a village store. However, you can still sell them to any village store.")
	public static SystemMessageId PLAYERS_ARE_NO_LONGER_ALLOWED_TO_PLAY_DICE_DICE_CAN_NO_LONGER_BE_PURCHASED_FROM_A_VILLAGE_STORE_HOWEVER_YOU_CAN_STILL_SELL_THEM_TO_ANY_VILLAGE_STORE;
	
	@ClientString(id = 1438, message = "There is no skill that enables enchant.")
	public static SystemMessageId THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT;
	
	@ClientString(id = 1439, message = "You do not have all of the items needed to enchant that skill.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_ENCHANT_THAT_SKILL;
	
	@ClientString(id = 1440, message = "Skill enchant was successful! $s1 has been enchanted.")
	public static SystemMessageId SKILL_ENCHANT_WAS_SUCCESSFUL_S1_HAS_BEEN_ENCHANTED;
	
	@ClientString(id = 1441, message = "Skill enchant failed. The skill will be initialized.")
	public static SystemMessageId SKILL_ENCHANT_FAILED_THE_SKILL_WILL_BE_INITIALIZED;
	
	@ClientString(id = 1442, message = "Remaining Time: $s1 second(s)")
	public static SystemMessageId REMAINING_TIME_S1_SECOND_S;
	
	@ClientString(id = 1443, message = "You do not have enough SP to enchant that skill.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_SP_TO_ENCHANT_THAT_SKILL;
	
	@ClientString(id = 1444, message = "You do not have enough experience (Exp) to enchant that skill.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_EXPERIENCE_EXP_TO_ENCHANT_THAT_SKILL;
	
	@ClientString(id = 1445, message = "Your previous subclass will be removed and replaced with the new subclass at level 40. Do you wish to continue?")
	public static SystemMessageId YOUR_PREVIOUS_SUBCLASS_WILL_BE_REMOVED_AND_REPLACED_WITH_THE_NEW_SUBCLASS_AT_LEVEL_40_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 1446, message = "The ferry from $s1 to $s2 has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_S1_TO_S2_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1447, message = "You cannot do that while fishing.")
	public static SystemMessageId YOU_CANNOT_DO_THAT_WHILE_FISHING;
	
	@ClientString(id = 1448, message = "Only fishing skills may be used at this time.")
	public static SystemMessageId ONLY_FISHING_SKILLS_MAY_BE_USED_AT_THIS_TIME;
	
	@ClientString(id = 1449, message = "You've got a bite!")
	public static SystemMessageId YOU_VE_GOT_A_BITE;
	
	@ClientString(id = 1450, message = "That fish is more determined than you are - it spit the hook!")
	public static SystemMessageId THAT_FISH_IS_MORE_DETERMINED_THAN_YOU_ARE_IT_SPIT_THE_HOOK;
	
	@ClientString(id = 1451, message = "Your bait was stolen by that fish!")
	public static SystemMessageId YOUR_BAIT_WAS_STOLEN_BY_THAT_FISH;
	
	@ClientString(id = 1452, message = "The bait has been lost because the fish got away.")
	public static SystemMessageId THE_BAIT_HAS_BEEN_LOST_BECAUSE_THE_FISH_GOT_AWAY;
	
	@ClientString(id = 1453, message = "You do not have a fishing pole equipped.")
	public static SystemMessageId YOU_DO_NOT_HAVE_A_FISHING_POLE_EQUIPPED;
	
	@ClientString(id = 1454, message = "You must put bait on your hook before you can fish.")
	public static SystemMessageId YOU_MUST_PUT_BAIT_ON_YOUR_HOOK_BEFORE_YOU_CAN_FISH;
	
	@ClientString(id = 1455, message = "You cannot fish while under water.")
	public static SystemMessageId YOU_CANNOT_FISH_WHILE_UNDER_WATER;
	
	@ClientString(id = 1456, message = "You cannot fish while riding as a passenger of a boat - it's against the rules.")
	public static SystemMessageId YOU_CANNOT_FISH_WHILE_RIDING_AS_A_PASSENGER_OF_A_BOAT_IT_S_AGAINST_THE_RULES;
	
	@ClientString(id = 1457, message = "You can't fish here.")
	public static SystemMessageId YOU_CAN_T_FISH_HERE;
	
	@ClientString(id = 1458, message = "Your attempt at fishing has been cancelled.")
	public static SystemMessageId YOUR_ATTEMPT_AT_FISHING_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 1459, message = "You do not have enough bait.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_BAIT;
	
	@ClientString(id = 1460, message = "You reel your line in and stop fishing.")
	public static SystemMessageId YOU_REEL_YOUR_LINE_IN_AND_STOP_FISHING;
	
	@ClientString(id = 1461, message = "You cast your line and start to fish.")
	public static SystemMessageId YOU_CAST_YOUR_LINE_AND_START_TO_FISH;
	
	@ClientString(id = 1462, message = "You may only use the Pumping skill while you are fishing.")
	public static SystemMessageId YOU_MAY_ONLY_USE_THE_PUMPING_SKILL_WHILE_YOU_ARE_FISHING;
	
	@ClientString(id = 1463, message = "You may only use the Reeling skill while you are fishing.")
	public static SystemMessageId YOU_MAY_ONLY_USE_THE_REELING_SKILL_WHILE_YOU_ARE_FISHING;
	
	@ClientString(id = 1464, message = "The fish has resisted your attempt to bring it in.")
	public static SystemMessageId THE_FISH_HAS_RESISTED_YOUR_ATTEMPT_TO_BRING_IT_IN;
	
	@ClientString(id = 1465, message = "Your pumping is successful, causing $s1 damage.")
	public static SystemMessageId YOUR_PUMPING_IS_SUCCESSFUL_CAUSING_S1_DAMAGE;
	
	@ClientString(id = 1466, message = "You failed to do anything with the fish and it regains $s1 HP.")
	public static SystemMessageId YOU_FAILED_TO_DO_ANYTHING_WITH_THE_FISH_AND_IT_REGAINS_S1_HP;
	
	@ClientString(id = 1467, message = "You reel that fish in closer and cause $s1 damage.")
	public static SystemMessageId YOU_REEL_THAT_FISH_IN_CLOSER_AND_CAUSE_S1_DAMAGE;
	
	@ClientString(id = 1468, message = "You failed to reel that fish in further and it regains $s1 HP.")
	public static SystemMessageId YOU_FAILED_TO_REEL_THAT_FISH_IN_FURTHER_AND_IT_REGAINS_S1_HP;
	
	@ClientString(id = 1469, message = "You caught something!")
	public static SystemMessageId YOU_CAUGHT_SOMETHING;
	
	@ClientString(id = 1470, message = "You cannot do that while fishing.")
	public static SystemMessageId YOU_CANNOT_DO_THAT_WHILE_FISHING_2;
	
	@ClientString(id = 1471, message = "You cannot do that while fishing.")
	public static SystemMessageId YOU_CANNOT_DO_THAT_WHILE_FISHING_3;
	
	@ClientString(id = 1472, message = "You look oddly at the fishing pole in disbelief and realize that you can't attack anything with this.")
	public static SystemMessageId YOU_LOOK_ODDLY_AT_THE_FISHING_POLE_IN_DISBELIEF_AND_REALIZE_THAT_YOU_CAN_T_ATTACK_ANYTHING_WITH_THIS;
	
	@ClientString(id = 1473, message = "$s1 is not sufficient.")
	public static SystemMessageId S1_IS_NOT_SUFFICIENT;
	
	@ClientString(id = 1474, message = "$s1 is not available.")
	public static SystemMessageId S1_IS_NOT_AVAILABLE;
	
	@ClientString(id = 1475, message = "You pet has dropped $s1.")
	public static SystemMessageId YOU_PET_HAS_DROPPED_S1;
	
	@ClientString(id = 1476, message = "You pet has dropped +$s1$s2.")
	public static SystemMessageId YOU_PET_HAS_DROPPED_S1_S2;
	
	@ClientString(id = 1477, message = "You pet has dropped $s2 of $s1.")
	public static SystemMessageId YOU_PET_HAS_DROPPED_S2_OF_S1;
	
	@ClientString(id = 1478, message = "You may only register a 64 x 64 pixel, 256-color BMP.")
	public static SystemMessageId YOU_MAY_ONLY_REGISTER_A_64_X_64_PIXEL_256_COLOR_BMP;
	
	@ClientString(id = 1479, message = "That is the wrong grade of soulshot for that fishing pole.")
	public static SystemMessageId THAT_IS_THE_WRONG_GRADE_OF_SOULSHOT_FOR_THAT_FISHING_POLE;
	
	@ClientString(id = 1480, message = "Are you sure you wish to remove yourself from the Grand Olympiad waiting list?")
	public static SystemMessageId ARE_YOU_SURE_YOU_WISH_TO_REMOVE_YOURSELF_FROM_THE_GRAND_OLYMPIAD_WAITING_LIST;
	
	@ClientString(id = 1481, message = "You have selected a class irrelevant individual match. Do you wish to participate?")
	public static SystemMessageId YOU_HAVE_SELECTED_A_CLASS_IRRELEVANT_INDIVIDUAL_MATCH_DO_YOU_WISH_TO_PARTICIPATE;
	
	@ClientString(id = 1482, message = "You've selected to join a class specific game. Continue?")
	public static SystemMessageId YOU_VE_SELECTED_TO_JOIN_A_CLASS_SPECIFIC_GAME_CONTINUE;
	
	@ClientString(id = 1483, message = "Are you ready to become a Hero?")
	public static SystemMessageId ARE_YOU_READY_TO_BECOME_A_HERO;
	
	@ClientString(id = 1484, message = "Are you sure this is the Hero weapon you wish to use?")
	public static SystemMessageId ARE_YOU_SURE_THIS_IS_THE_HERO_WEAPON_YOU_WISH_TO_USE;
	
	@ClientString(id = 1485, message = "The ferry from Talking Island to Gludin Harbor has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_TO_GLUDIN_HARBOR_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1486, message = "The ferry from Gludin Harbor to Talking Island has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_TO_TALKING_ISLAND_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1487, message = "The ferry from Giran Harbor to Talking Island has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_GIRAN_HARBOR_TO_TALKING_ISLAND_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1488, message = "The ferry from Talking Island to Giran Harbor has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_TALKING_ISLAND_TO_GIRAN_HARBOR_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1489, message = "The Innadril cruise service has been delayed.")
	public static SystemMessageId THE_INNADRIL_CRUISE_SERVICE_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1490, message = "Traded $s2 of $s1 crops.")
	public static SystemMessageId TRADED_S2_OF_S1_CROPS;
	
	@ClientString(id = 1491, message = "Failed in trading $s2 of $s1 crops.")
	public static SystemMessageId FAILED_IN_TRADING_S2_OF_S1_CROPS;
	
	@ClientString(id = 1492, message = "You will be moved to the Olympiad Stadium in $s1 second(s).")
	public static SystemMessageId YOU_WILL_BE_MOVED_TO_THE_OLYMPIAD_STADIUM_IN_S1_SECOND_S;
	
	@ClientString(id = 1493, message = "Your opponent made haste with their tail between their legs; the match has been cancelled.")
	public static SystemMessageId YOUR_OPPONENT_MADE_HASTE_WITH_THEIR_TAIL_BETWEEN_THEIR_LEGS_THE_MATCH_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 1494, message = "Your opponent does not meet the requirements to do battle; the match has been cancelled.")
	public static SystemMessageId YOUR_OPPONENT_DOES_NOT_MEET_THE_REQUIREMENTS_TO_DO_BATTLE_THE_MATCH_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 1495, message = "The match will start in $s1 second(s).")
	public static SystemMessageId THE_MATCH_WILL_START_IN_S1_SECOND_S;
	
	@ClientString(id = 1496, message = "The match has started. Fight!")
	public static SystemMessageId THE_MATCH_HAS_STARTED_FIGHT;
	
	@ClientString(id = 1497, message = "Congratulations, $c1! You win the match!")
	public static SystemMessageId CONGRATULATIONS_C1_YOU_WIN_THE_MATCH;
	
	@ClientString(id = 1498, message = "There is no victor; the match ends in a tie.")
	public static SystemMessageId THERE_IS_NO_VICTOR_THE_MATCH_ENDS_IN_A_TIE;
	
	@ClientString(id = 1499, message = "You will be moved back to town in $s1 second(s).")
	public static SystemMessageId YOU_WILL_BE_MOVED_BACK_TO_TOWN_IN_S1_SECOND_S;
	
	@ClientString(id = 1500, message = "$c1 does not meet the participation requirements. A subclass character cannot participate in the Olympiad.")
	public static SystemMessageId C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_A_SUBCLASS_CHARACTER_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD;
	
	@ClientString(id = 1501, message = "$c1 does not meet the participation requirements. Only Noblesse characters can participate in the Olympiad.")
	public static SystemMessageId C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_ONLY_NOBLESSE_CHARACTERS_CAN_PARTICIPATE_IN_THE_OLYMPIAD;
	
	@ClientString(id = 1502, message = "$c1 is already registered on the match waiting list.")
	public static SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_MATCH_WAITING_LIST;
	
	@ClientString(id = 1503, message = "You have been registered for the Grand Olympiad waiting list for a class specific match.")
	public static SystemMessageId YOU_HAVE_BEEN_REGISTERED_FOR_THE_GRAND_OLYMPIAD_WAITING_LIST_FOR_A_CLASS_SPECIFIC_MATCH;
	
	@ClientString(id = 1504, message = "You are currently registered for a 1v1 class irrelevant match.")
	public static SystemMessageId YOU_ARE_CURRENTLY_REGISTERED_FOR_A_1V1_CLASS_IRRELEVANT_MATCH;
	
	@ClientString(id = 1505, message = "You have been removed from the Grand Olympiad waiting list.")
	public static SystemMessageId YOU_HAVE_BEEN_REMOVED_FROM_THE_GRAND_OLYMPIAD_WAITING_LIST;
	
	@ClientString(id = 1506, message = "You are not currently registered for the Grand Olympiad.")
	public static SystemMessageId YOU_ARE_NOT_CURRENTLY_REGISTERED_FOR_THE_GRAND_OLYMPIAD;
	
	@ClientString(id = 1507, message = "You cannot equip that item in a Grand Olympiad match.")
	public static SystemMessageId YOU_CANNOT_EQUIP_THAT_ITEM_IN_A_GRAND_OLYMPIAD_MATCH;
	
	@ClientString(id = 1508, message = "You cannot use that item in a Grand Olympiad match.")
	public static SystemMessageId YOU_CANNOT_USE_THAT_ITEM_IN_A_GRAND_OLYMPIAD_MATCH;
	
	@ClientString(id = 1509, message = "You cannot use that skill in a Grand Olympiad match.")
	public static SystemMessageId YOU_CANNOT_USE_THAT_SKILL_IN_A_GRAND_OLYMPIAD_MATCH;
	
	@ClientString(id = 1510, message = "$c1 is making an attempt to resurrect you. If you choose this path, $s2 experience points will be returned to you. Do you want to be resurrected?")
	public static SystemMessageId C1_IS_MAKING_AN_ATTEMPT_TO_RESURRECT_YOU_IF_YOU_CHOOSE_THIS_PATH_S2_EXPERIENCE_POINTS_WILL_BE_RETURNED_TO_YOU_DO_YOU_WANT_TO_BE_RESURRECTED;
	
	@ClientString(id = 1511, message = "While a pet is being resurrected, it cannot help in resurrecting its master.")
	public static SystemMessageId WHILE_A_PET_IS_BEING_RESURRECTED_IT_CANNOT_HELP_IN_RESURRECTING_ITS_MASTER;
	
	@ClientString(id = 1512, message = "You cannot resurrect a pet while their owner is being resurrected.")
	public static SystemMessageId YOU_CANNOT_RESURRECT_A_PET_WHILE_THEIR_OWNER_IS_BEING_RESURRECTED;
	
	@ClientString(id = 1513, message = "Resurrection has already been proposed.")
	public static SystemMessageId RESURRECTION_HAS_ALREADY_BEEN_PROPOSED;
	
	@ClientString(id = 1514, message = "You cannot resurrect the owner of a pet while their pet is being resurrected.")
	public static SystemMessageId YOU_CANNOT_RESURRECT_THE_OWNER_OF_A_PET_WHILE_THEIR_PET_IS_BEING_RESURRECTED;
	
	@ClientString(id = 1515, message = "A pet cannot be resurrected while it's owner is in the process of resurrecting.")
	public static SystemMessageId A_PET_CANNOT_BE_RESURRECTED_WHILE_IT_S_OWNER_IS_IN_THE_PROCESS_OF_RESURRECTING;
	
	@ClientString(id = 1516, message = "The target is unavailable for seeding.")
	public static SystemMessageId THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING;
	
	@ClientString(id = 1517, message = "The Blessed Enchant failed. The enchant value of the item became 0.")
	public static SystemMessageId THE_BLESSED_ENCHANT_FAILED_THE_ENCHANT_VALUE_OF_THE_ITEM_BECAME_0;
	
	@ClientString(id = 1518, message = "You do not meet the required condition to equip that item.")
	public static SystemMessageId YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM;
	
	@ClientString(id = 1519, message = "The pet has been killed. If you don't resurrect it within 24 hours, the pet's body will disappear along with all the pet's items.")
	public static SystemMessageId THE_PET_HAS_BEEN_KILLED_IF_YOU_DON_T_RESURRECT_IT_WITHIN_24_HOURS_THE_PET_S_BODY_WILL_DISAPPEAR_ALONG_WITH_ALL_THE_PET_S_ITEMS;
	
	@ClientString(id = 1520, message = "Your servitor passed away.")
	public static SystemMessageId YOUR_SERVITOR_PASSED_AWAY;
	
	@ClientString(id = 1521, message = "Your servitor has vanished! You'll need to summon a new one.")
	public static SystemMessageId YOUR_SERVITOR_HAS_VANISHED_YOU_LL_NEED_TO_SUMMON_A_NEW_ONE;
	
	@ClientString(id = 1522, message = "Your pet's corpse has decayed!")
	public static SystemMessageId YOUR_PET_S_CORPSE_HAS_DECAYED;
	
	@ClientString(id = 1523, message = "You should release your pet or servitor so that it does not fall off of the boat and drown!")
	public static SystemMessageId YOU_SHOULD_RELEASE_YOUR_PET_OR_SERVITOR_SO_THAT_IT_DOES_NOT_FALL_OFF_OF_THE_BOAT_AND_DROWN;
	
	@ClientString(id = 1524, message = "$c1's pet gained $s2.")
	public static SystemMessageId C1_S_PET_GAINED_S2;
	
	@ClientString(id = 1525, message = "$c1's pet gained $s3 of $s2.")
	public static SystemMessageId C1_S_PET_GAINED_S3_OF_S2;
	
	@ClientString(id = 1526, message = "$c1's pet gained +$s2$s3.")
	public static SystemMessageId C1_S_PET_GAINED_S2_S3;
	
	@ClientString(id = 1527, message = "Your pet was hungry so it ate $s1.")
	public static SystemMessageId YOUR_PET_WAS_HUNGRY_SO_IT_ATE_S1;
	
	@ClientString(id = 1528, message = "You've sent a petition to the GM staff.")
	public static SystemMessageId YOU_VE_SENT_A_PETITION_TO_THE_GM_STAFF;
	
	@ClientString(id = 1529, message = "$c1 is inviting you to a Command Channel. Do you accept?")
	public static SystemMessageId C1_IS_INVITING_YOU_TO_A_COMMAND_CHANNEL_DO_YOU_ACCEPT;
	
	@ClientString(id = 1530, message = "Select a target or enter the name.")
	public static SystemMessageId SELECT_A_TARGET_OR_ENTER_THE_NAME;
	
	@ClientString(id = 1531, message = "Enter the name of the clan that you wish to declare war on.")
	public static SystemMessageId ENTER_THE_NAME_OF_THE_CLAN_THAT_YOU_WISH_TO_DECLARE_WAR_ON;
	
	@ClientString(id = 1532, message = "Enter the name of the clan that you wish to request a cease-fire with.")
	public static SystemMessageId ENTER_THE_NAME_OF_THE_CLAN_THAT_YOU_WISH_TO_REQUEST_A_CEASE_FIRE_WITH;
	
	@ClientString(id = 1533, message = "Attention: $c1 has picked up $s2.")
	public static SystemMessageId ATTENTION_C1_HAS_PICKED_UP_S2;
	
	@ClientString(id = 1534, message = "Attention: $c1 has picked up +$s2$s3.")
	public static SystemMessageId ATTENTION_C1_HAS_PICKED_UP_S2_S3;
	
	@ClientString(id = 1535, message = "Attention: $c1's pet has picked up $s2.")
	public static SystemMessageId ATTENTION_C1_S_PET_HAS_PICKED_UP_S2;
	
	@ClientString(id = 1536, message = "Attention: $c1's pet has picked up +$s2$s3.")
	public static SystemMessageId ATTENTION_C1_S_PET_HAS_PICKED_UP_S2_S3;
	
	@ClientString(id = 1537, message = "Current Location: $s1, $s2, $s3 (near Rune Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_RUNE_VILLAGE;
	
	@ClientString(id = 1538, message = "Current Location: $s1, $s2, $s3 (near the Town of Goddard)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_TOWN_OF_GODDARD;
	
	@ClientString(id = 1539, message = "Cargo has arrived at Talking Island Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_TALKING_ISLAND_VILLAGE;
	
	@ClientString(id = 1540, message = "Cargo has arrived at the Dark Elf Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_THE_DARK_ELF_VILLAGE;
	
	@ClientString(id = 1541, message = "Cargo has arrived at Elven Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_ELVEN_VILLAGE;
	
	@ClientString(id = 1542, message = "Cargo has arrived at Orc Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_ORC_VILLAGE;
	
	@ClientString(id = 1543, message = "Cargo has arrived at Dwarven Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_DWARVEN_VILLAGE;
	
	@ClientString(id = 1544, message = "Cargo has arrived at Aden Castle Town.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_ADEN_CASTLE_TOWN;
	
	@ClientString(id = 1545, message = "Cargo has arrived at the Town of Oren.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_THE_TOWN_OF_OREN;
	
	@ClientString(id = 1546, message = "Cargo has arrived at Hunters Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_HUNTERS_VILLAGE;
	
	@ClientString(id = 1547, message = "Cargo has arrived at the Town of Dion.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_THE_TOWN_OF_DION;
	
	@ClientString(id = 1548, message = "Cargo has arrived at Floran Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_FLORAN_VILLAGE;
	
	@ClientString(id = 1549, message = "Cargo has arrived at Gludin Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_GLUDIN_VILLAGE;
	
	@ClientString(id = 1550, message = "Cargo has arrived at the Town of Gludio.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_THE_TOWN_OF_GLUDIO;
	
	@ClientString(id = 1551, message = "Cargo has arrived at Giran Castle Town.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_GIRAN_CASTLE_TOWN;
	
	@ClientString(id = 1552, message = "Cargo has arrived at Heine.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_HEINE;
	
	@ClientString(id = 1553, message = "Cargo has arrived at Rune Village.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_RUNE_VILLAGE;
	
	@ClientString(id = 1554, message = "Cargo has arrived at the Town of Goddard.")
	public static SystemMessageId CARGO_HAS_ARRIVED_AT_THE_TOWN_OF_GODDARD;
	
	@ClientString(id = 1555, message = "Do you want to cancel character deletion?")
	public static SystemMessageId DO_YOU_WANT_TO_CANCEL_CHARACTER_DELETION;
	
	@ClientString(id = 1556, message = "Your clan notice has been saved.")
	public static SystemMessageId YOUR_CLAN_NOTICE_HAS_BEEN_SAVED;
	
	@ClientString(id = 1557, message = "Seed price should be more than $s1 and less than $s2.")
	public static SystemMessageId SEED_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	@ClientString(id = 1558, message = "The seed quantity should be more than $s1 and less than $s2.")
	public static SystemMessageId THE_SEED_QUANTITY_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	@ClientString(id = 1559, message = "Crop price should be more than $s1 and less than $s2.")
	public static SystemMessageId CROP_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	@ClientString(id = 1560, message = "The crop quantity should be more than $s1 and less than $s2 .")
	public static SystemMessageId THE_CROP_QUANTITY_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	@ClientString(id = 1561, message = "$s1 has declared a Clan War.")
	public static SystemMessageId S1_HAS_DECLARED_A_CLAN_WAR;
	
	@ClientString(id = 1562, message = "A Clan War has been declared against the clan, $s1. If you are killed during the Clan War by members of the opposing clan, you will only lose a quarter of the normal experience from death.")
	public static SystemMessageId A_CLAN_WAR_HAS_BEEN_DECLARED_AGAINST_THE_CLAN_S1_IF_YOU_ARE_KILLED_DURING_THE_CLAN_WAR_BY_MEMBERS_OF_THE_OPPOSING_CLAN_YOU_WILL_ONLY_LOSE_A_QUARTER_OF_THE_NORMAL_EXPERIENCE_FROM_DEATH;
	
	@ClientString(id = 1563, message = "The clan, $s1, cannot declare a clan war because their clan is level 2 or lower, and or they do not have enough members.")
	public static SystemMessageId THE_CLAN_S1_CANNOT_DECLARE_A_CLAN_WAR_BECAUSE_THEIR_CLAN_IS_LEVEL_2_OR_LOWER_AND_OR_THEY_DO_NOT_HAVE_ENOUGH_MEMBERS;
	
	@ClientString(id = 1564, message = "A clan war can only be declared if the clan is level 3 or above, and the number of clan members is fifteen or greater.")
	public static SystemMessageId A_CLAN_WAR_CAN_ONLY_BE_DECLARED_IF_THE_CLAN_IS_LEVEL_3_OR_ABOVE_AND_THE_NUMBER_OF_CLAN_MEMBERS_IS_FIFTEEN_OR_GREATER;
	
	@ClientString(id = 1565, message = "A clan war cannot be declared against a clan that does not exist!")
	public static SystemMessageId A_CLAN_WAR_CANNOT_BE_DECLARED_AGAINST_A_CLAN_THAT_DOES_NOT_EXIST;
	
	@ClientString(id = 1566, message = "The clan, $s1, has decided to stop the war.")
	public static SystemMessageId THE_CLAN_S1_HAS_DECIDED_TO_STOP_THE_WAR;
	
	@ClientString(id = 1567, message = "The war against $s1 Clan has been stopped.")
	public static SystemMessageId THE_WAR_AGAINST_S1_CLAN_HAS_BEEN_STOPPED;
	
	@ClientString(id = 1568, message = "The target for declaration is wrong.")
	public static SystemMessageId THE_TARGET_FOR_DECLARATION_IS_WRONG;
	
	@ClientString(id = 1569, message = "A declaration of Clan War against an allied clan can't be made.")
	public static SystemMessageId A_DECLARATION_OF_CLAN_WAR_AGAINST_AN_ALLIED_CLAN_CAN_T_BE_MADE;
	
	@ClientString(id = 1570, message = "A declaration of war against more than 30 Clans can't be made at the same time.")
	public static SystemMessageId A_DECLARATION_OF_WAR_AGAINST_MORE_THAN_30_CLANS_CAN_T_BE_MADE_AT_THE_SAME_TIME;
	
	@ClientString(id = 1571, message = "======<Clans You've Declared War On>======")
	public static SystemMessageId CLANS_YOU_VE_DECLARED_WAR_ON;
	
	@ClientString(id = 1572, message = "======<Clans That Have Declared War On You>======")
	public static SystemMessageId CLANS_THAT_HAVE_DECLARED_WAR_ON_YOU;
	
	@ClientString(id = 1573, message = "All is well. There are no clans that have declared war against your clan.")
	public static SystemMessageId ALL_IS_WELL_THERE_ARE_NO_CLANS_THAT_HAVE_DECLARED_WAR_AGAINST_YOUR_CLAN;
	
	@ClientString(id = 1574, message = "Command Channels can only be formed by a party leader who is also the leader of a level 5 clan.")
	public static SystemMessageId COMMAND_CHANNELS_CAN_ONLY_BE_FORMED_BY_A_PARTY_LEADER_WHO_IS_ALSO_THE_LEADER_OF_A_LEVEL_5_CLAN;
	
	@ClientString(id = 1575, message = "Your pet uses spiritshot.")
	public static SystemMessageId YOUR_PET_USES_SPIRITSHOT;
	
	@ClientString(id = 1576, message = "Your servitor uses spiritshot.")
	public static SystemMessageId YOUR_SERVITOR_USES_SPIRITSHOT;
	
	@ClientString(id = 1577, message = "Servitor uses the power of spirit.")
	public static SystemMessageId SERVITOR_USES_THE_POWER_OF_SPIRIT;
	
	@ClientString(id = 1578, message = "Items are not available for a private store or private manufacture.")
	public static SystemMessageId ITEMS_ARE_NOT_AVAILABLE_FOR_A_PRIVATE_STORE_OR_PRIVATE_MANUFACTURE;
	
	@ClientString(id = 1579, message = "$c1's pet gained $s2 adena.")
	public static SystemMessageId C1_S_PET_GAINED_S2_ADENA;
	
	@ClientString(id = 1580, message = "The Command Channel has been formed.")
	public static SystemMessageId THE_COMMAND_CHANNEL_HAS_BEEN_FORMED;
	
	@ClientString(id = 1581, message = "The Command Channel has been disbanded.")
	public static SystemMessageId THE_COMMAND_CHANNEL_HAS_BEEN_DISBANDED;
	
	@ClientString(id = 1582, message = "You have joined the Command Channel.")
	public static SystemMessageId YOU_HAVE_JOINED_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1583, message = "You were dismissed from the Command Channel.")
	public static SystemMessageId YOU_WERE_DISMISSED_FROM_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1584, message = "$c1's party has been dismissed from the Command Channel.")
	public static SystemMessageId C1_S_PARTY_HAS_BEEN_DISMISSED_FROM_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1585, message = "The Command Channel has been disbanded.")
	public static SystemMessageId THE_COMMAND_CHANNEL_HAS_BEEN_DISBANDED_2;
	
	@ClientString(id = 1586, message = "You have quit the Command Channel.")
	public static SystemMessageId YOU_HAVE_QUIT_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1587, message = "$c1's party has left the Command Channel.")
	public static SystemMessageId C1_S_PARTY_HAS_LEFT_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1588, message = "The Command Channel is activated only when there are at least 5 parties participating.")
	public static SystemMessageId THE_COMMAND_CHANNEL_IS_ACTIVATED_ONLY_WHEN_THERE_ARE_AT_LEAST_5_PARTIES_PARTICIPATING;
	
	@ClientString(id = 1589, message = "Command Channel authority has been transferred to $c1.")
	public static SystemMessageId COMMAND_CHANNEL_AUTHORITY_HAS_BEEN_TRANSFERRED_TO_C1;
	
	@ClientString(id = 1590, message = "===<Guild Info (Total Parties: $s1)>===")
	public static SystemMessageId GUILD_INFO_TOTAL_PARTIES_S1;
	
	@ClientString(id = 1591, message = "No user has been invited to the Command Channel.")
	public static SystemMessageId NO_USER_HAS_BEEN_INVITED_TO_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1592, message = "You can no longer set up a Command Channel.")
	public static SystemMessageId YOU_CAN_NO_LONGER_SET_UP_A_COMMAND_CHANNEL;
	
	@ClientString(id = 1593, message = "You do not have authority to invite someone to the Command Channel.")
	public static SystemMessageId YOU_DO_NOT_HAVE_AUTHORITY_TO_INVITE_SOMEONE_TO_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1594, message = "$c1's party is already a member of the Command Channel.")
	public static SystemMessageId C1_S_PARTY_IS_ALREADY_A_MEMBER_OF_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1595, message = "$s1 has succeeded.")
	public static SystemMessageId S1_HAS_SUCCEEDED;
	
	@ClientString(id = 1596, message = "You were hit by $s1!")
	public static SystemMessageId YOU_WERE_HIT_BY_S1;
	
	@ClientString(id = 1597, message = "$s1 has failed.")
	public static SystemMessageId S1_HAS_FAILED;
	
	@ClientString(id = 1598, message = "Soulshots and spiritshots are not available for a dead pet or servitor. Sad, isn't it?")
	public static SystemMessageId SOULSHOTS_AND_SPIRITSHOTS_ARE_NOT_AVAILABLE_FOR_A_DEAD_PET_OR_SERVITOR_SAD_ISN_T_IT;
	
	@ClientString(id = 1599, message = "You cannot 'observe' while you are in combat!")
	public static SystemMessageId YOU_CANNOT_OBSERVE_WHILE_YOU_ARE_IN_COMBAT;
	
	@ClientString(id = 1600, message = "Tomorrow's items will ALL be set to 0. Do you wish to continue?")
	public static SystemMessageId TOMORROW_S_ITEMS_WILL_ALL_BE_SET_TO_0_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 1601, message = "Tomorrow's items will all be set to the same value as today's items. Do you wish to continue?")
	public static SystemMessageId TOMORROW_S_ITEMS_WILL_ALL_BE_SET_TO_THE_SAME_VALUE_AS_TODAY_S_ITEMS_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 1602, message = "Only a party leader can access the Command Channel.")
	public static SystemMessageId ONLY_A_PARTY_LEADER_CAN_ACCESS_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1603, message = "Only the Command Channel creator can use the Raid Leader text.")
	public static SystemMessageId ONLY_THE_COMMAND_CHANNEL_CREATOR_CAN_USE_THE_RAID_LEADER_TEXT;
	
	@ClientString(id = 1604, message = "While dressed in formal wear, you can't use items that require all skills and casting operations.")
	public static SystemMessageId WHILE_DRESSED_IN_FORMAL_WEAR_YOU_CAN_T_USE_ITEMS_THAT_REQUIRE_ALL_SKILLS_AND_CASTING_OPERATIONS;
	
	@ClientString(id = 1605, message = "* Here, you can buy only seeds of $s1 Manor.")
	public static SystemMessageId HERE_YOU_CAN_BUY_ONLY_SEEDS_OF_S1_MANOR;
	
	@ClientString(id = 1606, message = "Congratulations - You've completed your third-class transfer quest!")
	public static SystemMessageId CONGRATULATIONS_YOU_VE_COMPLETED_YOUR_THIRD_CLASS_TRANSFER_QUEST;
	
	@ClientString(id = 1607, message = "$s1 adena has been withdrawn to pay for purchasing fees.")
	public static SystemMessageId S1_ADENA_HAS_BEEN_WITHDRAWN_TO_PAY_FOR_PURCHASING_FEES;
	
	@ClientString(id = 1608, message = "Due to insufficient adena you cannot buy another castle.")
	public static SystemMessageId DUE_TO_INSUFFICIENT_ADENA_YOU_CANNOT_BUY_ANOTHER_CASTLE;
	
	@ClientString(id = 1609, message = "War has already been declared against that clan… but I'll make note that you really don't like them.")
	public static SystemMessageId WAR_HAS_ALREADY_BEEN_DECLARED_AGAINST_THAT_CLAN_BUT_I_LL_MAKE_NOTE_THAT_YOU_REALLY_DON_T_LIKE_THEM;
	
	@ClientString(id = 1610, message = "Fool! You cannot declare war against your own clan!")
	public static SystemMessageId FOOL_YOU_CANNOT_DECLARE_WAR_AGAINST_YOUR_OWN_CLAN;
	
	@ClientString(id = 1611, message = "Party Leader: $c1")
	public static SystemMessageId PARTY_LEADER_C1;
	
	@ClientString(id = 1612, message = "=====<War List>=====")
	public static SystemMessageId WAR_LIST;
	
	@ClientString(id = 1613, message = "There is no clan listed on your War List.")
	public static SystemMessageId THERE_IS_NO_CLAN_LISTED_ON_YOUR_WAR_LIST;
	
	@ClientString(id = 1614, message = "You have joined a channel that was already open.")
	public static SystemMessageId YOU_HAVE_JOINED_A_CHANNEL_THAT_WAS_ALREADY_OPEN;
	
	@ClientString(id = 1615, message = "The number of remaining parties is $s1 until a channel is activated.")
	public static SystemMessageId THE_NUMBER_OF_REMAINING_PARTIES_IS_S1_UNTIL_A_CHANNEL_IS_ACTIVATED;
	
	@ClientString(id = 1616, message = "The Command Channel has been activated.")
	public static SystemMessageId THE_COMMAND_CHANNEL_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 1617, message = "You do not have the authority to use the Command Channel.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_USE_THE_COMMAND_CHANNEL;
	
	@ClientString(id = 1618, message = "The ferry from Rune Harbor to Gludin Harbor has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_RUNE_HARBOR_TO_GLUDIN_HARBOR_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1619, message = "The ferry from Gludin Harbor to Rune Harbor has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_TO_RUNE_HARBOR_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1620, message = "Welcome to Rune Harbor.")
	public static SystemMessageId WELCOME_TO_RUNE_HARBOR;
	
	@ClientString(id = 1621, message = "Departure for Gludin Harbor will take place in five minutes!")
	public static SystemMessageId DEPARTURE_FOR_GLUDIN_HARBOR_WILL_TAKE_PLACE_IN_FIVE_MINUTES;
	
	@ClientString(id = 1622, message = "Departure for Gludin Harbor will take place in one minute!")
	public static SystemMessageId DEPARTURE_FOR_GLUDIN_HARBOR_WILL_TAKE_PLACE_IN_ONE_MINUTE;
	
	@ClientString(id = 1623, message = "Make haste! We will be departing for Gludin Harbor shortly…")
	public static SystemMessageId MAKE_HASTE_WE_WILL_BE_DEPARTING_FOR_GLUDIN_HARBOR_SHORTLY;
	
	@ClientString(id = 1624, message = "We are now departing for Gludin Harbor. Hold on and enjoy the ride!")
	public static SystemMessageId WE_ARE_NOW_DEPARTING_FOR_GLUDIN_HARBOR_HOLD_ON_AND_ENJOY_THE_RIDE;
	
	@ClientString(id = 1625, message = "Departure for Rune Harbor will take place after anchoring for ten minutes.")
	public static SystemMessageId DEPARTURE_FOR_RUNE_HARBOR_WILL_TAKE_PLACE_AFTER_ANCHORING_FOR_TEN_MINUTES;
	
	@ClientString(id = 1626, message = "Departure for Rune Harbor will take place in five minutes!")
	public static SystemMessageId DEPARTURE_FOR_RUNE_HARBOR_WILL_TAKE_PLACE_IN_FIVE_MINUTES;
	
	@ClientString(id = 1627, message = "Departure for Rune Harbor will take place in one minute!")
	public static SystemMessageId DEPARTURE_FOR_RUNE_HARBOR_WILL_TAKE_PLACE_IN_ONE_MINUTE;
	
	@ClientString(id = 1628, message = "Make haste! We will be departing for Gludin Harbor shortly…")
	public static SystemMessageId MAKE_HASTE_WE_WILL_BE_DEPARTING_FOR_GLUDIN_HARBOR_SHORTLY_2;
	
	@ClientString(id = 1629, message = "We are now departing for Rune Harbor. Hold on and enjoy the ride!")
	public static SystemMessageId WE_ARE_NOW_DEPARTING_FOR_RUNE_HARBOR_HOLD_ON_AND_ENJOY_THE_RIDE;
	
	@ClientString(id = 1630, message = "The ferry from Rune Harbor will be arriving at Gludin Harbor in approximately 15 minutes.")
	public static SystemMessageId THE_FERRY_FROM_RUNE_HARBOR_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_15_MINUTES;
	
	@ClientString(id = 1631, message = "The ferry from Rune Harbor will be arriving at Gludin Harbor in approximately 10 minutes.")
	public static SystemMessageId THE_FERRY_FROM_RUNE_HARBOR_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_10_MINUTES;
	
	@ClientString(id = 1632, message = "The ferry from Rune Harbor will be arriving at Gludin Harbor in approximately 5 minutes.")
	public static SystemMessageId THE_FERRY_FROM_RUNE_HARBOR_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_5_MINUTES;
	
	@ClientString(id = 1633, message = "The ferry from Rune Harbor will be arriving at Gludin Harbor in approximately 1 minute.")
	public static SystemMessageId THE_FERRY_FROM_RUNE_HARBOR_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_1_MINUTE;
	
	@ClientString(id = 1634, message = "The ferry from Gludin Harbor will be arriving at Rune Harbor in approximately 15 minutes.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_RUNE_HARBOR_IN_APPROXIMATELY_15_MINUTES;
	
	@ClientString(id = 1635, message = "The ferry from Gludin Harbor will be arriving at Rune Harbor in approximately 10 minutes.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_RUNE_HARBOR_IN_APPROXIMATELY_10_MINUTES;
	
	@ClientString(id = 1636, message = "The ferry from Gludin Harbor will be arriving at Rune Harbor in approximately 5 minutes.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_RUNE_HARBOR_IN_APPROXIMATELY_5_MINUTES;
	
	@ClientString(id = 1637, message = "The ferry from Gludin Harbor will be arriving at Rune Harbor in approximately 1 minute.")
	public static SystemMessageId THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_RUNE_HARBOR_IN_APPROXIMATELY_1_MINUTE;
	
	@ClientString(id = 1638, message = "You cannot fish while using a recipe book, private manufacture or private store.")
	public static SystemMessageId YOU_CANNOT_FISH_WHILE_USING_A_RECIPE_BOOK_PRIVATE_MANUFACTURE_OR_PRIVATE_STORE;
	
	@ClientString(id = 1639, message = "Round $s1 of the Grand Olympiad Games has started!")
	public static SystemMessageId ROUND_S1_OF_THE_GRAND_OLYMPIAD_GAMES_HAS_STARTED;
	
	@ClientString(id = 1640, message = "Round $s1 of the Grand Olympiad Games has now ended.")
	public static SystemMessageId ROUND_S1_OF_THE_GRAND_OLYMPIAD_GAMES_HAS_NOW_ENDED;
	
	@ClientString(id = 1641, message = "Sharpen your swords, tighten the stitching in your armor, and make haste to a Grand Olympiad Manager! Battles in the Grand Olympiad Games are now taking place!")
	public static SystemMessageId SHARPEN_YOUR_SWORDS_TIGHTEN_THE_STITCHING_IN_YOUR_ARMOR_AND_MAKE_HASTE_TO_A_GRAND_OLYMPIAD_MANAGER_BATTLES_IN_THE_GRAND_OLYMPIAD_GAMES_ARE_NOW_TAKING_PLACE;
	
	@ClientString(id = 1642, message = "Much carnage has been left for the cleanup crew of the Olympiad Stadium. Battles in the Grand Olympiad Games are now over!")
	public static SystemMessageId MUCH_CARNAGE_HAS_BEEN_LEFT_FOR_THE_CLEANUP_CREW_OF_THE_OLYMPIAD_STADIUM_BATTLES_IN_THE_GRAND_OLYMPIAD_GAMES_ARE_NOW_OVER;
	
	@ClientString(id = 1643, message = "Current Location: $s1, $s2, $s3 (Dimensional Gap)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_DIMENSIONAL_GAP;
	
	@ClientString(id = 1644, message = "none")
	public static SystemMessageId NONE;
	
	@ClientString(id = 1645, message = "none")
	public static SystemMessageId NONE_2;
	
	@ClientString(id = 1646, message = "none")
	public static SystemMessageId NONE_3;
	
	@ClientString(id = 1647, message = "none")
	public static SystemMessageId NONE_4;
	
	@ClientString(id = 1648, message = "none")
	public static SystemMessageId NONE_5;
	
	@ClientString(id = 1649, message = "Play time is now accumulating.")
	public static SystemMessageId PLAY_TIME_IS_NOW_ACCUMULATING;
	
	@ClientString(id = 1650, message = "Due to high server traffic, your login attempt has failed. Please try again soon.")
	public static SystemMessageId DUE_TO_HIGH_SERVER_TRAFFIC_YOUR_LOGIN_ATTEMPT_HAS_FAILED_PLEASE_TRY_AGAIN_SOON;
	
	@ClientString(id = 1651, message = "The Grand Olympiad Games are not currently in progress.")
	public static SystemMessageId THE_GRAND_OLYMPIAD_GAMES_ARE_NOT_CURRENTLY_IN_PROGRESS;
	
	@ClientString(id = 1652, message = "You are now recording gameplay.")
	public static SystemMessageId YOU_ARE_NOW_RECORDING_GAMEPLAY;
	
	@ClientString(id = 1653, message = "Your recording has been successfully stored. ($s1)")
	public static SystemMessageId YOUR_RECORDING_HAS_BEEN_SUCCESSFULLY_STORED_S1;
	
	@ClientString(id = 1654, message = "The attempt to record the replay file has failed.")
	public static SystemMessageId THE_ATTEMPT_TO_RECORD_THE_REPLAY_FILE_HAS_FAILED;
	
	@ClientString(id = 1655, message = "You caught something smelly and scary, maybe you should throw it back!?")
	public static SystemMessageId YOU_CAUGHT_SOMETHING_SMELLY_AND_SCARY_MAYBE_YOU_SHOULD_THROW_IT_BACK;
	
	@ClientString(id = 1656, message = "You have successfully traded the item with the NPC.")
	public static SystemMessageId YOU_HAVE_SUCCESSFULLY_TRADED_THE_ITEM_WITH_THE_NPC;
	
	@ClientString(id = 1657, message = "$c1 has earned $s2 points in the Grand Olympiad Games.")
	public static SystemMessageId C1_HAS_EARNED_S2_POINTS_IN_THE_GRAND_OLYMPIAD_GAMES;
	
	@ClientString(id = 1658, message = "$c1 has lost $s2 points in the Grand Olympiad Games.")
	public static SystemMessageId C1_HAS_LOST_S2_POINTS_IN_THE_GRAND_OLYMPIAD_GAMES;
	
	@ClientString(id = 1659, message = "Current Location: $s1, $s2, $s3 (Cemetery of the Empire).")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_CEMETERY_OF_THE_EMPIRE;
	
	@ClientString(id = 1660, message = "Channel Creator: $c1")
	public static SystemMessageId CHANNEL_CREATOR_C1;
	
	@ClientString(id = 1661, message = "$c1 has obtained $s3 $s2s.")
	public static SystemMessageId C1_HAS_OBTAINED_S3_S2S;
	
	@ClientString(id = 1662, message = "The fish are no longer biting here because you've caught too many! Try fishing in another location.")
	public static SystemMessageId THE_FISH_ARE_NO_LONGER_BITING_HERE_BECAUSE_YOU_VE_CAUGHT_TOO_MANY_TRY_FISHING_IN_ANOTHER_LOCATION;
	
	@ClientString(id = 1663, message = "The clan crest was successfully registered. Remember, only a clan that owns a clan hall or castle can display a crest.")
	public static SystemMessageId THE_CLAN_CREST_WAS_SUCCESSFULLY_REGISTERED_REMEMBER_ONLY_A_CLAN_THAT_OWNS_A_CLAN_HALL_OR_CASTLE_CAN_DISPLAY_A_CREST;
	
	@ClientString(id = 1664, message = "The fish is resisting your efforts to haul it in! Look at that bobber go!")
	public static SystemMessageId THE_FISH_IS_RESISTING_YOUR_EFFORTS_TO_HAUL_IT_IN_LOOK_AT_THAT_BOBBER_GO;
	
	@ClientString(id = 1665, message = "You've worn that fish out! It can't even pull the bobber under the water!")
	public static SystemMessageId YOU_VE_WORN_THAT_FISH_OUT_IT_CAN_T_EVEN_PULL_THE_BOBBER_UNDER_THE_WATER;
	
	@ClientString(id = 1666, message = "You have obtained +$s1$s2.")
	public static SystemMessageId YOU_HAVE_OBTAINED_S1_S2;
	
	@ClientString(id = 1667, message = "Lethal Strike!")
	public static SystemMessageId LETHAL_STRIKE;
	
	@ClientString(id = 1668, message = "Your lethal strike was successful!")
	public static SystemMessageId YOUR_LETHAL_STRIKE_WAS_SUCCESSFUL;
	
	@ClientString(id = 1669, message = "There was nothing found inside.")
	public static SystemMessageId THERE_WAS_NOTHING_FOUND_INSIDE;
	
	@ClientString(id = 1670, message = "Due to your Reeling and/or Pumping skill being three or more levels higher than your Fishing skill, a 50 damage penalty will be applied.")
	public static SystemMessageId DUE_TO_YOUR_REELING_AND_OR_PUMPING_SKILL_BEING_THREE_OR_MORE_LEVELS_HIGHER_THAN_YOUR_FISHING_SKILL_A_50_DAMAGE_PENALTY_WILL_BE_APPLIED;
	
	@ClientString(id = 1671, message = "Your reeling was successful! (Mastery Penalty:$s1 )")
	public static SystemMessageId YOUR_REELING_WAS_SUCCESSFUL_MASTERY_PENALTY_S1;
	
	@ClientString(id = 1672, message = "Your pumping was successful! (Mastery Penalty:$s1 )")
	public static SystemMessageId YOUR_PUMPING_WAS_SUCCESSFUL_MASTERY_PENALTY_S1;
	
	@ClientString(id = 1673, message = "For the current Grand Olympiad you have participated in $s1 match(es). $s2 win(s) and $s3 defeat(s). You currently have $s4 Olympiad Point(s).")
	public static SystemMessageId FOR_THE_CURRENT_GRAND_OLYMPIAD_YOU_HAVE_PARTICIPATED_IN_S1_MATCH_ES_S2_WIN_S_AND_S3_DEFEAT_S_YOU_CURRENTLY_HAVE_S4_OLYMPIAD_POINT_S;
	
	@ClientString(id = 1674, message = "This command can only be used by a Noblesse.")
	public static SystemMessageId THIS_COMMAND_CAN_ONLY_BE_USED_BY_A_NOBLESSE;
	
	@ClientString(id = 1675, message = "A manor cannot be set up between 4:30 am and 8 pm.")
	public static SystemMessageId A_MANOR_CANNOT_BE_SET_UP_BETWEEN_4_30_AM_AND_8_PM;
	
	@ClientString(id = 1676, message = "You do not have a servitor or pet and therefore cannot use the automatic-use function.")
	public static SystemMessageId YOU_DO_NOT_HAVE_A_SERVITOR_OR_PET_AND_THEREFORE_CANNOT_USE_THE_AUTOMATIC_USE_FUNCTION;
	
	@ClientString(id = 1677, message = "A cease-fire during a Clan War can not be called while members of your clan are engaged in battle.")
	public static SystemMessageId A_CEASE_FIRE_DURING_A_CLAN_WAR_CAN_NOT_BE_CALLED_WHILE_MEMBERS_OF_YOUR_CLAN_ARE_ENGAGED_IN_BATTLE;
	
	@ClientString(id = 1678, message = "You have not declared a Clan War against the clan $s1.")
	public static SystemMessageId YOU_HAVE_NOT_DECLARED_A_CLAN_WAR_AGAINST_THE_CLAN_S1;
	
	@ClientString(id = 1679, message = "Only the creator of a command channel can issue a global command.")
	public static SystemMessageId ONLY_THE_CREATOR_OF_A_COMMAND_CHANNEL_CAN_ISSUE_A_GLOBAL_COMMAND;
	
	@ClientString(id = 1680, message = "$c1 has declined the channel invitation.")
	public static SystemMessageId C1_HAS_DECLINED_THE_CHANNEL_INVITATION;
	
	@ClientString(id = 1681, message = "Since $c1 did not respond, your channel invitation has failed.")
	public static SystemMessageId SINCE_C1_DID_NOT_RESPOND_YOUR_CHANNEL_INVITATION_HAS_FAILED;
	
	@ClientString(id = 1682, message = "Only the creator of a command channel can use the channel dismiss command.")
	public static SystemMessageId ONLY_THE_CREATOR_OF_A_COMMAND_CHANNEL_CAN_USE_THE_CHANNEL_DISMISS_COMMAND;
	
	@ClientString(id = 1683, message = "Only a party leader can leave a command channel.")
	public static SystemMessageId ONLY_A_PARTY_LEADER_CAN_LEAVE_A_COMMAND_CHANNEL;
	
	@ClientString(id = 1684, message = "A Clan War can not be declared against a clan that is being dissolved.")
	public static SystemMessageId A_CLAN_WAR_CAN_NOT_BE_DECLARED_AGAINST_A_CLAN_THAT_IS_BEING_DISSOLVED;
	
	@ClientString(id = 1685, message = "You are unable to equip this item when your PK count is greater than or equal to one.")
	public static SystemMessageId YOU_ARE_UNABLE_TO_EQUIP_THIS_ITEM_WHEN_YOUR_PK_COUNT_IS_GREATER_THAN_OR_EQUAL_TO_ONE;
	
	@ClientString(id = 1686, message = "Stones and mortar tumble to the earth - the castle wall has taken damage!")
	public static SystemMessageId STONES_AND_MORTAR_TUMBLE_TO_THE_EARTH_THE_CASTLE_WALL_HAS_TAKEN_DAMAGE;
	
	@ClientString(id = 1687, message = "This area cannot be entered while mounted atop of a Wyvern. You will be dismounted from your Wyvern if you do not leave!")
	public static SystemMessageId THIS_AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_ATOP_OF_A_WYVERN_YOU_WILL_BE_DISMOUNTED_FROM_YOUR_WYVERN_IF_YOU_DO_NOT_LEAVE;
	
	@ClientString(id = 1688, message = "You cannot enchant while operating a Private Store or Private Workshop.")
	public static SystemMessageId YOU_CANNOT_ENCHANT_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP;
	
	@ClientString(id = 1689, message = "$c1 is already registered on the class match waiting list.")
	public static SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST;
	
	@ClientString(id = 1690, message = "$c1 is already registered on the waiting list for the class irrelevant individual match.")
	public static SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_CLASS_IRRELEVANT_INDIVIDUAL_MATCH;
	
	@ClientString(id = 1691, message = "$c1 does not meet the participation requirements. You cannot participate in the Olympiad because your inventory slot exceeds 80%.")
	public static SystemMessageId C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_YOU_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD_BECAUSE_YOUR_INVENTORY_SLOT_EXCEEDS_80;
	
	@ClientString(id = 1692, message = "$c1 does not meet the participation requirements. You cannot participate in the Olympiad because you have changed to your sub-class.")
	public static SystemMessageId C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_YOU_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD_BECAUSE_YOU_HAVE_CHANGED_TO_YOUR_SUB_CLASS;
	
	@ClientString(id = 1693, message = "You may not observe a Grand Olympiad Games match while you are on the waiting list.")
	public static SystemMessageId YOU_MAY_NOT_OBSERVE_A_GRAND_OLYMPIAD_GAMES_MATCH_WHILE_YOU_ARE_ON_THE_WAITING_LIST;
	
	@ClientString(id = 1694, message = "Only a clan leader that is a Noblesse can view the Siege War Status window during a siege war.")
	public static SystemMessageId ONLY_A_CLAN_LEADER_THAT_IS_A_NOBLESSE_CAN_VIEW_THE_SIEGE_WAR_STATUS_WINDOW_DURING_A_SIEGE_WAR;
	
	@ClientString(id = 1695, message = "You can only use that during a Siege War!")
	public static SystemMessageId YOU_CAN_ONLY_USE_THAT_DURING_A_SIEGE_WAR;
	
	@ClientString(id = 1696, message = "Your accumulated play time is $s1.")
	public static SystemMessageId YOUR_ACCUMULATED_PLAY_TIME_IS_S1;
	
	@ClientString(id = 1697, message = "Your accumulated play time has reached Fatigue level, so you will receive experience or item drops at only 50 percent of the normal rate. For the sake of you physical and emotional health, we encourage you to log out as soon as possible and take a break before returning.")
	public static SystemMessageId YOUR_ACCUMULATED_PLAY_TIME_HAS_REACHED_FATIGUE_LEVEL_SO_YOU_WILL_RECEIVE_EXPERIENCE_OR_ITEM_DROPS_AT_ONLY_50_PERCENT_OF_THE_NORMAL_RATE_FOR_THE_SAKE_OF_YOU_PHYSICAL_AND_EMOTIONAL_HEALTH_WE_ENCOURAGE_YOU_TO_LOG_OUT_AS_SOON_AS_POSSIBLE_AND_TAKE_A_BREAK_BEFORE_RETURNING;
	
	@ClientString(id = 1698, message = "Your accumulated play time has reached Ill-health level, so you will no longer gain experience or item drops. For the sake of your physical and emotional health, please log out as soon as possible and take a break. Once you have been logged out for at least 5 hours, the experience and item drop rate penalties will be removed.")
	public static SystemMessageId YOUR_ACCUMULATED_PLAY_TIME_HAS_REACHED_ILL_HEALTH_LEVEL_SO_YOU_WILL_NO_LONGER_GAIN_EXPERIENCE_OR_ITEM_DROPS_FOR_THE_SAKE_OF_YOUR_PHYSICAL_AND_EMOTIONAL_HEALTH_PLEASE_LOG_OUT_AS_SOON_AS_POSSIBLE_AND_TAKE_A_BREAK_ONCE_YOU_HAVE_BEEN_LOGGED_OUT_FOR_AT_LEAST_5_HOURS_THE_EXPERIENCE_AND_ITEM_DROP_RATE_PENALTIES_WILL_BE_REMOVED;
	
	@ClientString(id = 1699, message = "You cannot dismiss a party member by force.")
	public static SystemMessageId YOU_CANNOT_DISMISS_A_PARTY_MEMBER_BY_FORCE;
	
	@ClientString(id = 1700, message = "You don't have enough spiritshots needed for a pet/servitor.")
	public static SystemMessageId YOU_DON_T_HAVE_ENOUGH_SPIRITSHOTS_NEEDED_FOR_A_PET_SERVITOR;
	
	@ClientString(id = 1701, message = "You don't have enough soulshots needed for a pet/servitor.")
	public static SystemMessageId YOU_DON_T_HAVE_ENOUGH_SOULSHOTS_NEEDED_FOR_A_PET_SERVITOR;
	
	@ClientString(id = 1702, message = "$s1 is using a third party program.")
	public static SystemMessageId S1_IS_USING_A_THIRD_PARTY_PROGRAM;
	
	@ClientString(id = 1703, message = "The previously investigated user is not using a third party program.")
	public static SystemMessageId THE_PREVIOUSLY_INVESTIGATED_USER_IS_NOT_USING_A_THIRD_PARTY_PROGRAM;
	
	@ClientString(id = 1704, message = "Please close the setup window for your private manufacturing store or private store, and try again.")
	public static SystemMessageId PLEASE_CLOSE_THE_SETUP_WINDOW_FOR_YOUR_PRIVATE_MANUFACTURING_STORE_OR_PRIVATE_STORE_AND_TRY_AGAIN;
	
	@ClientString(id = 1705, message = "PC Bang Points acquisition period. Points acquisition period left $s1 hour.")
	public static SystemMessageId PC_BANG_POINTS_ACQUISITION_PERIOD_POINTS_ACQUISITION_PERIOD_LEFT_S1_HOUR;
	
	@ClientString(id = 1706, message = "PC Bang Points use period. Points use period left $s1 hour.")
	public static SystemMessageId PC_BANG_POINTS_USE_PERIOD_POINTS_USE_PERIOD_LEFT_S1_HOUR;
	
	@ClientString(id = 1707, message = "You acquired $s1 PC Bang Point.")
	public static SystemMessageId YOU_ACQUIRED_S1_PC_BANG_POINT;
	
	@ClientString(id = 1708, message = "Double points! You acquired $s1 PC Bang Point.")
	public static SystemMessageId DOUBLE_POINTS_YOU_ACQUIRED_S1_PC_BANG_POINT;
	
	@ClientString(id = 1709, message = "You are using $s1 point.")
	public static SystemMessageId YOU_ARE_USING_S1_POINT;
	
	@ClientString(id = 1710, message = "You are short of accumulated points.")
	public static SystemMessageId YOU_ARE_SHORT_OF_ACCUMULATED_POINTS;
	
	@ClientString(id = 1711, message = "PC Bang Points use period has expired.")
	public static SystemMessageId PC_BANG_POINTS_USE_PERIOD_HAS_EXPIRED;
	
	@ClientString(id = 1712, message = "The PC Bang Points accumulation period has expired.")
	public static SystemMessageId THE_PC_BANG_POINTS_ACCUMULATION_PERIOD_HAS_EXPIRED;
	
	@ClientString(id = 1713, message = "The games may be delayed due to an insufficient number of players waiting.")
	public static SystemMessageId THE_GAMES_MAY_BE_DELAYED_DUE_TO_AN_INSUFFICIENT_NUMBER_OF_PLAYERS_WAITING;
	
	@ClientString(id = 1714, message = "Current Location: $s1, $s2, $s3 (Near the Town of Schuttgart)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_TOWN_OF_SCHUTTGART;
	
	@ClientString(id = 1715, message = "This is a Peaceful Zone\\n- PvP is not allowed in this area.")
	public static SystemMessageId THIS_IS_A_PEACEFUL_ZONE_N_PVP_IS_NOT_ALLOWED_IN_THIS_AREA;
	
	@ClientString(id = 1716, message = "Altered Zone")
	public static SystemMessageId ALTERED_ZONE;
	
	@ClientString(id = 1717, message = "Siege War Zone \\n- A siege is currently in progress in this area. \\n If a character dies in this zone, their resurrection ability may be restricted.")
	public static SystemMessageId SIEGE_WAR_ZONE_N_A_SIEGE_IS_CURRENTLY_IN_PROGRESS_IN_THIS_AREA_N_IF_A_CHARACTER_DIES_IN_THIS_ZONE_THEIR_RESURRECTION_ABILITY_MAY_BE_RESTRICTED;
	
	@ClientString(id = 1718, message = "General Field")
	public static SystemMessageId GENERAL_FIELD;
	
	@ClientString(id = 1719, message = "Seven Signs Zone \\n- Although a character's level may increase while in this area, HP and MP \\n will not be regenerated.")
	public static SystemMessageId SEVEN_SIGNS_ZONE_N_ALTHOUGH_A_CHARACTER_S_LEVEL_MAY_INCREASE_WHILE_IN_THIS_AREA_HP_AND_MP_N_WILL_NOT_BE_REGENERATED;
	
	@ClientString(id = 1720, message = "---")
	public static SystemMessageId EMPTY_6;
	
	@ClientString(id = 1721, message = "Combat Zone")
	public static SystemMessageId COMBAT_ZONE;
	
	@ClientString(id = 1722, message = "Please enter the name of the item you wish to search for.")
	public static SystemMessageId PLEASE_ENTER_THE_NAME_OF_THE_ITEM_YOU_WISH_TO_SEARCH_FOR;
	
	@ClientString(id = 1723, message = "Please take a moment to provide feedback about the petition service.")
	public static SystemMessageId PLEASE_TAKE_A_MOMENT_TO_PROVIDE_FEEDBACK_ABOUT_THE_PETITION_SERVICE;
	
	@ClientString(id = 1724, message = "A servitor whom is engaged in battle cannot be de-activated.")
	public static SystemMessageId A_SERVITOR_WHOM_IS_ENGAGED_IN_BATTLE_CANNOT_BE_DE_ACTIVATED;
	
	@ClientString(id = 1725, message = "You have earned $s1 raid point(s).")
	public static SystemMessageId YOU_HAVE_EARNED_S1_RAID_POINT_S;
	
	@ClientString(id = 1726, message = "$s1 has disappeared because its time period has expired.")
	public static SystemMessageId S1_HAS_DISAPPEARED_BECAUSE_ITS_TIME_PERIOD_HAS_EXPIRED;
	
	@ClientString(id = 1727, message = "$s1 has invited you to room <$s2>. Do you wish to accept?")
	public static SystemMessageId S1_HAS_INVITED_YOU_TO_ROOM_S2_DO_YOU_WISH_TO_ACCEPT;
	
	@ClientString(id = 1728, message = "The recipient of your invitation did not accept the party matching invitation.")
	public static SystemMessageId THE_RECIPIENT_OF_YOUR_INVITATION_DID_NOT_ACCEPT_THE_PARTY_MATCHING_INVITATION;
	
	@ClientString(id = 1729, message = "You cannot join a Command Channel while teleporting.")
	public static SystemMessageId YOU_CANNOT_JOIN_A_COMMAND_CHANNEL_WHILE_TELEPORTING;
	
	@ClientString(id = 1730, message = "To establish a Clan Academy, your clan must be Level 5 or higher.")
	public static SystemMessageId TO_ESTABLISH_A_CLAN_ACADEMY_YOUR_CLAN_MUST_BE_LEVEL_5_OR_HIGHER;
	
	@ClientString(id = 1731, message = "Only the clan leader can create a Clan Academy.")
	public static SystemMessageId ONLY_THE_CLAN_LEADER_CAN_CREATE_A_CLAN_ACADEMY;
	
	@ClientString(id = 1732, message = "To create a Clan Academy, a Blood Mark is needed.")
	public static SystemMessageId TO_CREATE_A_CLAN_ACADEMY_A_BLOOD_MARK_IS_NEEDED;
	
	@ClientString(id = 1733, message = "You do not have enough adena to create a Clan Academy.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_ADENA_TO_CREATE_A_CLAN_ACADEMY;
	
	@ClientString(id = 1734, message = "To join a Clan Academy, characters must be Level 40 or below, not belong another clan and not yet completed their 2nd class transfer.")
	public static SystemMessageId TO_JOIN_A_CLAN_ACADEMY_CHARACTERS_MUST_BE_LEVEL_40_OR_BELOW_NOT_BELONG_ANOTHER_CLAN_AND_NOT_YET_COMPLETED_THEIR_2ND_CLASS_TRANSFER;
	
	@ClientString(id = 1735, message = "$s1 does not meet the requirements to join a Clan Academy.")
	public static SystemMessageId S1_DOES_NOT_MEET_THE_REQUIREMENTS_TO_JOIN_A_CLAN_ACADEMY;
	
	@ClientString(id = 1736, message = "The Clan Academy has reached its maximum enrollment.")
	public static SystemMessageId THE_CLAN_ACADEMY_HAS_REACHED_ITS_MAXIMUM_ENROLLMENT;
	
	@ClientString(id = 1737, message = "Your clan has not established a Clan Academy but is eligible to do so.")
	public static SystemMessageId YOUR_CLAN_HAS_NOT_ESTABLISHED_A_CLAN_ACADEMY_BUT_IS_ELIGIBLE_TO_DO_SO;
	
	@ClientString(id = 1738, message = "Your clan has already established a Clan Academy.")
	public static SystemMessageId YOUR_CLAN_HAS_ALREADY_ESTABLISHED_A_CLAN_ACADEMY;
	
	@ClientString(id = 1739, message = "Would you like to create a Clan Academy?")
	public static SystemMessageId WOULD_YOU_LIKE_TO_CREATE_A_CLAN_ACADEMY;
	
	@ClientString(id = 1740, message = "Please enter the name of the Clan Academy.")
	public static SystemMessageId PLEASE_ENTER_THE_NAME_OF_THE_CLAN_ACADEMY;
	
	@ClientString(id = 1741, message = "Congratulations! The $s1's Clan Academy has been created.")
	public static SystemMessageId CONGRATULATIONS_THE_S1_S_CLAN_ACADEMY_HAS_BEEN_CREATED;
	
	@ClientString(id = 1742, message = "A message inviting $s1 to join the Clan Academy is being sent.")
	public static SystemMessageId A_MESSAGE_INVITING_S1_TO_JOIN_THE_CLAN_ACADEMY_IS_BEING_SENT;
	
	@ClientString(id = 1743, message = "To open a Clan Academy, the leader of a Level 5 clan or above must pay XX Proofs of Blood or a certain amount of adena.")
	public static SystemMessageId TO_OPEN_A_CLAN_ACADEMY_THE_LEADER_OF_A_LEVEL_5_CLAN_OR_ABOVE_MUST_PAY_XX_PROOFS_OF_BLOOD_OR_A_CERTAIN_AMOUNT_OF_ADENA;
	
	@ClientString(id = 1744, message = "There was no response to your invitation to join the Clan Academy, so the invitation has been rescinded.")
	public static SystemMessageId THERE_WAS_NO_RESPONSE_TO_YOUR_INVITATION_TO_JOIN_THE_CLAN_ACADEMY_SO_THE_INVITATION_HAS_BEEN_RESCINDED;
	
	@ClientString(id = 1745, message = "The recipient of your invitation to join the Clan Academy has declined.")
	public static SystemMessageId THE_RECIPIENT_OF_YOUR_INVITATION_TO_JOIN_THE_CLAN_ACADEMY_HAS_DECLINED;
	
	@ClientString(id = 1746, message = "You have already joined a Clan Academy.")
	public static SystemMessageId YOU_HAVE_ALREADY_JOINED_A_CLAN_ACADEMY;
	
	@ClientString(id = 1747, message = "$s1 has sent you an invitation to join the Clan Academy belonging to the $s2 clan. Do you accept?")
	public static SystemMessageId S1_HAS_SENT_YOU_AN_INVITATION_TO_JOIN_THE_CLAN_ACADEMY_BELONGING_TO_THE_S2_CLAN_DO_YOU_ACCEPT;
	
	@ClientString(id = 1748, message = "Clan Academy member $s1 has successfully completed the 2nd class transfer and obtained $s2 Clan Reputation points.")
	public static SystemMessageId CLAN_ACADEMY_MEMBER_S1_HAS_SUCCESSFULLY_COMPLETED_THE_2ND_CLASS_TRANSFER_AND_OBTAINED_S2_CLAN_REPUTATION_POINTS;
	
	@ClientString(id = 1749, message = "Congratulations! You will now graduate from the Clan Academy and leave your current clan. As a graduate of the academy, you can immediately join a clan as a regular member without being subject to any penalties.")
	public static SystemMessageId CONGRATULATIONS_YOU_WILL_NOW_GRADUATE_FROM_THE_CLAN_ACADEMY_AND_LEAVE_YOUR_CURRENT_CLAN_AS_A_GRADUATE_OF_THE_ACADEMY_YOU_CAN_IMMEDIATELY_JOIN_A_CLAN_AS_A_REGULAR_MEMBER_WITHOUT_BEING_SUBJECT_TO_ANY_PENALTIES;
	
	@ClientString(id = 1750, message = "$c1 does not meet the participation requirements. The owner of $s2 cannot participate in the Olympiad.")
	public static SystemMessageId C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_THE_OWNER_OF_S2_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD;
	
	@ClientString(id = 1751, message = "The Grand Master has given you a commemorative item.")
	public static SystemMessageId THE_GRAND_MASTER_HAS_GIVEN_YOU_A_COMMEMORATIVE_ITEM;
	
	@ClientString(id = 1752, message = "Since the clan has received a graduate of the Clan Academy, it has earned $s1 points toward its reputation score.")
	public static SystemMessageId SINCE_THE_CLAN_HAS_RECEIVED_A_GRADUATE_OF_THE_CLAN_ACADEMY_IT_HAS_EARNED_S1_POINTS_TOWARD_ITS_REPUTATION_SCORE;
	
	@ClientString(id = 1753, message = "The clan leader has decreed that that particular privilege cannot be granted to a Clan Academy member.")
	public static SystemMessageId THE_CLAN_LEADER_HAS_DECREED_THAT_THAT_PARTICULAR_PRIVILEGE_CANNOT_BE_GRANTED_TO_A_CLAN_ACADEMY_MEMBER;
	
	@ClientString(id = 1754, message = "That privilege cannot be granted to a Clan Academy member.")
	public static SystemMessageId THAT_PRIVILEGE_CANNOT_BE_GRANTED_TO_A_CLAN_ACADEMY_MEMBER;
	
	@ClientString(id = 1755, message = "$s2 has been designated as the apprentice of clan member $s1.")
	public static SystemMessageId S2_HAS_BEEN_DESIGNATED_AS_THE_APPRENTICE_OF_CLAN_MEMBER_S1;
	
	@ClientString(id = 1756, message = "Your apprentice, $s1, has logged in.")
	public static SystemMessageId YOUR_APPRENTICE_S1_HAS_LOGGED_IN;
	
	@ClientString(id = 1757, message = "Your apprentice, $c1, has logged out.")
	public static SystemMessageId YOUR_APPRENTICE_C1_HAS_LOGGED_OUT;
	
	@ClientString(id = 1758, message = "Your sponsor, $c1, has logged in.")
	public static SystemMessageId YOUR_SPONSOR_C1_HAS_LOGGED_IN;
	
	@ClientString(id = 1759, message = "Your sponsor, $c1, has logged out.")
	public static SystemMessageId YOUR_SPONSOR_C1_HAS_LOGGED_OUT;
	
	@ClientString(id = 1760, message = "Clan member $c1's title has been changed to $s2.")
	public static SystemMessageId CLAN_MEMBER_C1_S_TITLE_HAS_BEEN_CHANGED_TO_S2;
	
	@ClientString(id = 1761, message = "Clan member $c1's privilege level has been changed to $s2.")
	public static SystemMessageId CLAN_MEMBER_C1_S_PRIVILEGE_LEVEL_HAS_BEEN_CHANGED_TO_S2;
	
	@ClientString(id = 1762, message = "You do not have the right to dismiss an apprentice.")
	public static SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_DISMISS_AN_APPRENTICE;
	
	@ClientString(id = 1763, message = "$s2, clan member $c1's apprentice, has been removed.")
	public static SystemMessageId S2_CLAN_MEMBER_C1_S_APPRENTICE_HAS_BEEN_REMOVED;
	
	@ClientString(id = 1764, message = "This item can only be worn by a member of the Clan Academy.")
	public static SystemMessageId THIS_ITEM_CAN_ONLY_BE_WORN_BY_A_MEMBER_OF_THE_CLAN_ACADEMY;
	
	@ClientString(id = 1765, message = "As a graduate of the Clan Academy, you can no longer wear this item.")
	public static SystemMessageId AS_A_GRADUATE_OF_THE_CLAN_ACADEMY_YOU_CAN_NO_LONGER_WEAR_THIS_ITEM;
	
	@ClientString(id = 1766, message = "An application to join the clan has been sent to $c1 in $s2.")
	public static SystemMessageId AN_APPLICATION_TO_JOIN_THE_CLAN_HAS_BEEN_SENT_TO_C1_IN_S2;
	
	@ClientString(id = 1767, message = "An application to join the Clan Academy has been sent to $c1.")
	public static SystemMessageId AN_APPLICATION_TO_JOIN_THE_CLAN_ACADEMY_HAS_BEEN_SENT_TO_C1;
	
	@ClientString(id = 1768, message = "$c1 has invited you to join the Clan Academy of $s2 clan. Would you like to join?")
	public static SystemMessageId C1_HAS_INVITED_YOU_TO_JOIN_THE_CLAN_ACADEMY_OF_S2_CLAN_WOULD_YOU_LIKE_TO_JOIN;
	
	@ClientString(id = 1769, message = "$c1 has sent you an invitation to join the $s3 Order of Knights under the $s2 clan. Would you like to join?")
	public static SystemMessageId C1_HAS_SENT_YOU_AN_INVITATION_TO_JOIN_THE_S3_ORDER_OF_KNIGHTS_UNDER_THE_S2_CLAN_WOULD_YOU_LIKE_TO_JOIN;
	
	@ClientString(id = 1770, message = "The clan's reputation score has dropped below 0. The clan may face certain penalties as a result.")
	public static SystemMessageId THE_CLAN_S_REPUTATION_SCORE_HAS_DROPPED_BELOW_0_THE_CLAN_MAY_FACE_CERTAIN_PENALTIES_AS_A_RESULT;
	
	@ClientString(id = 1771, message = "Now that your clan level is above Level 5, it can accumulate clan reputation points.")
	public static SystemMessageId NOW_THAT_YOUR_CLAN_LEVEL_IS_ABOVE_LEVEL_5_IT_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS;
	
	@ClientString(id = 1772, message = "Since your clan was defeated in a siege, $s1 points have been deducted from your clan's reputation score and given to the opposing clan.")
	public static SystemMessageId SINCE_YOUR_CLAN_WAS_DEFEATED_IN_A_SIEGE_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_S_REPUTATION_SCORE_AND_GIVEN_TO_THE_OPPOSING_CLAN;
	
	@ClientString(id = 1773, message = "Since your clan emerged victorious from the siege, $s1 points have been added to your clan's reputation score.")
	public static SystemMessageId SINCE_YOUR_CLAN_EMERGED_VICTORIOUS_FROM_THE_SIEGE_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1774, message = "Your clan's newly acquired contested clan hall has added $s1 points to your clan's reputation score.")
	public static SystemMessageId YOUR_CLAN_S_NEWLY_ACQUIRED_CONTESTED_CLAN_HALL_HAS_ADDED_S1_POINTS_TO_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1775, message = "Clan member $c1 was an active member of the highest-ranked party in the Festival of Darkness. $s2 points have been added to your clan's reputation score.")
	public static SystemMessageId CLAN_MEMBER_C1_WAS_AN_ACTIVE_MEMBER_OF_THE_HIGHEST_RANKED_PARTY_IN_THE_FESTIVAL_OF_DARKNESS_S2_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1776, message = "Clan member $c1 was named a hero. $2s points have been added to your clan's reputation score.")
	public static SystemMessageId CLAN_MEMBER_C1_WAS_NAMED_A_HERO_2S_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1777, message = "You have successfully completed a clan quest. $s1 points have been added to your clan's reputation score.")
	public static SystemMessageId YOU_HAVE_SUCCESSFULLY_COMPLETED_A_CLAN_QUEST_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1778, message = "An opposing clan has captured your clan's contested clan hall. $s1 points have been deducted from your clan's reputation score.")
	public static SystemMessageId AN_OPPOSING_CLAN_HAS_CAPTURED_YOUR_CLAN_S_CONTESTED_CLAN_HALL_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1779, message = "After losing the contested clan hall, 300 points have been deducted from your clan's reputation score.")
	public static SystemMessageId AFTER_LOSING_THE_CONTESTED_CLAN_HALL_300_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1780, message = "Your clan has captured your opponent's contested clan hall. $s1 points have been deducted from your opponent's clan reputation score.")
	public static SystemMessageId YOUR_CLAN_HAS_CAPTURED_YOUR_OPPONENT_S_CONTESTED_CLAN_HALL_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_OPPONENT_S_CLAN_REPUTATION_SCORE;
	
	@ClientString(id = 1781, message = "Your clan has added $1s points to its clan reputation score.")
	public static SystemMessageId YOUR_CLAN_HAS_ADDED_1S_POINTS_TO_ITS_CLAN_REPUTATION_SCORE;
	
	@ClientString(id = 1782, message = "Your clan member, $c1, was killed. $s2 points have been deducted from your clan's reputation score and added to your opponent's clan reputation score.")
	public static SystemMessageId YOUR_CLAN_MEMBER_C1_WAS_KILLED_S2_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_S_REPUTATION_SCORE_AND_ADDED_TO_YOUR_OPPONENT_S_CLAN_REPUTATION_SCORE;
	
	@ClientString(id = 1783, message = "For killing an opposing clan member, $s1 points have been deducted from your opponents' clan reputation score.")
	public static SystemMessageId FOR_KILLING_AN_OPPOSING_CLAN_MEMBER_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_OPPONENTS_CLAN_REPUTATION_SCORE;
	
	@ClientString(id = 1784, message = "Your clan has failed to defend the castle. $s1 points have been deducted from your clan's reputation score and added to your opponents'.")
	public static SystemMessageId YOUR_CLAN_HAS_FAILED_TO_DEFEND_THE_CASTLE_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_S_REPUTATION_SCORE_AND_ADDED_TO_YOUR_OPPONENTS;
	
	@ClientString(id = 1785, message = "The clan you belong to has been initialized. $s1 points have been deducted from your clan reputation score.")
	public static SystemMessageId THE_CLAN_YOU_BELONG_TO_HAS_BEEN_INITIALIZED_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_REPUTATION_SCORE;
	
	@ClientString(id = 1786, message = "Your clan has failed to defend the castle. $s1 points have been deducted from your clan's reputation score.")
	public static SystemMessageId YOUR_CLAN_HAS_FAILED_TO_DEFEND_THE_CASTLE_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1787, message = "$s1 points have been deducted from the clan's reputation score.")
	public static SystemMessageId S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_THE_CLAN_S_REPUTATION_SCORE;
	
	@ClientString(id = 1788, message = "The clan skill $s1 has been added.")
	public static SystemMessageId THE_CLAN_SKILL_S1_HAS_BEEN_ADDED;
	
	@ClientString(id = 1789, message = "Since the Clan Reputation Score has dropped to 0 or lower, your clan skill(s) will be de-activated.")
	public static SystemMessageId SINCE_THE_CLAN_REPUTATION_SCORE_HAS_DROPPED_TO_0_OR_LOWER_YOUR_CLAN_SKILL_S_WILL_BE_DE_ACTIVATED;
	
	@ClientString(id = 1790, message = "The conditions necessary to increase the clan's level have not been met.")
	public static SystemMessageId THE_CONDITIONS_NECESSARY_TO_INCREASE_THE_CLAN_S_LEVEL_HAVE_NOT_BEEN_MET;
	
	@ClientString(id = 1791, message = "The conditions necessary to create a military unit have not been met.")
	public static SystemMessageId THE_CONDITIONS_NECESSARY_TO_CREATE_A_MILITARY_UNIT_HAVE_NOT_BEEN_MET;
	
	@ClientString(id = 1792, message = "Please assign a manager for your new Order of Knights.")
	public static SystemMessageId PLEASE_ASSIGN_A_MANAGER_FOR_YOUR_NEW_ORDER_OF_KNIGHTS;
	
	@ClientString(id = 1793, message = "$c1 has been selected as the captain of $s2.")
	public static SystemMessageId C1_HAS_BEEN_SELECTED_AS_THE_CAPTAIN_OF_S2;
	
	@ClientString(id = 1794, message = "The Knights of $s1 have been created.")
	public static SystemMessageId THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED;
	
	@ClientString(id = 1795, message = "The Royal Guard of $s1 have been created.")
	public static SystemMessageId THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED;
	
	@ClientString(id = 1796, message = "Your account has been temporarily suspended because of involvement in account theft or other abnormal game play which has harmed or inconvenienced other players. If you feel that you were not involved with any of these violations, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_TEMPORARILY_SUSPENDED_BECAUSE_OF_INVOLVEMENT_IN_ACCOUNT_THEFT_OR_OTHER_ABNORMAL_GAME_PLAY_WHICH_HAS_HARMED_OR_INCONVENIENCED_OTHER_PLAYERS_IF_YOU_FEEL_THAT_YOU_WERE_NOT_INVOLVED_WITH_ANY_OF_THESE_VIOLATIONS_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1797, message = "$c1 has been promoted to $s2.")
	public static SystemMessageId C1_HAS_BEEN_PROMOTED_TO_S2;
	
	@ClientString(id = 1798, message = "Clan lord privileges have been transferred to $c1.")
	public static SystemMessageId CLAN_LORD_PRIVILEGES_HAVE_BEEN_TRANSFERRED_TO_C1;
	
	@ClientString(id = 1799, message = "We are searching for BOT users. Please try again later.")
	public static SystemMessageId WE_ARE_SEARCHING_FOR_BOT_USERS_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 1800, message = "User $c1 has a history of using BOT.")
	public static SystemMessageId USER_C1_HAS_A_HISTORY_OF_USING_BOT;
	
	@ClientString(id = 1801, message = "The attempt to sell has failed.")
	public static SystemMessageId THE_ATTEMPT_TO_SELL_HAS_FAILED;
	
	@ClientString(id = 1802, message = "The attempt to trade has failed.")
	public static SystemMessageId THE_ATTEMPT_TO_TRADE_HAS_FAILED;
	
	@ClientString(id = 1803, message = "Participation requests are no longer being accepted.")
	public static SystemMessageId PARTICIPATION_REQUESTS_ARE_NO_LONGER_BEING_ACCEPTED;
	
	@ClientString(id = 1804, message = "Your account has been suspended for 7 days because an illicit cash/account transaction has been detected. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_7_DAYS_BECAUSE_AN_ILLICIT_CASH_ACCOUNT_TRANSACTION_HAS_BEEN_DETECTED_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1805, message = "Your account has been suspended for 30 days because an illicit cash/account transaction has been detected for the second time. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_30_DAYS_BECAUSE_AN_ILLICIT_CASH_ACCOUNT_TRANSACTION_HAS_BEEN_DETECTED_FOR_THE_SECOND_TIME_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1806, message = "Your account has been permanently suspended because an illicit cash/account transaction has been detected for the third time. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_PERMANENTLY_SUSPENDED_BECAUSE_AN_ILLICIT_CASH_ACCOUNT_TRANSACTION_HAS_BEEN_DETECTED_FOR_THE_THIRD_TIME_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1807, message = "Your account has been suspended for 30 days because of your involvement in an illicit cash transaction. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_30_DAYS_BECAUSE_OF_YOUR_INVOLVEMENT_IN_AN_ILLICIT_CASH_TRANSACTION_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1808, message = "Your account has been permanently suspended because of your involvement in an illicit cash/account transaction. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_PERMANENTLY_SUSPENDED_BECAUSE_OF_YOUR_INVOLVEMENT_IN_AN_ILLICIT_CASH_ACCOUNT_TRANSACTION_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1809, message = "Your account must be verified. For information on verification procedures, please visit the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_MUST_BE_VERIFIED_FOR_INFORMATION_ON_VERIFICATION_PROCEDURES_PLEASE_VISIT_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1810, message = "The refuse invitation state has been activated.")
	public static SystemMessageId THE_REFUSE_INVITATION_STATE_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 1811, message = "The refuse invitation state has been removed.")
	public static SystemMessageId THE_REFUSE_INVITATION_STATE_HAS_BEEN_REMOVED;
	
	@ClientString(id = 1812, message = "Since the refuse invitation state is currently activated, no invitation can be made.")
	public static SystemMessageId SINCE_THE_REFUSE_INVITATION_STATE_IS_CURRENTLY_ACTIVATED_NO_INVITATION_CAN_BE_MADE;
	
	@ClientString(id = 1813, message = "$s1 has $s2 hour(s) of usage time remaining.")
	public static SystemMessageId S1_HAS_S2_HOUR_S_OF_USAGE_TIME_REMAINING;
	
	@ClientString(id = 1814, message = "$s1 has $s2 minute(s) of usage time remaining.")
	public static SystemMessageId S1_HAS_S2_MINUTE_S_OF_USAGE_TIME_REMAINING;
	
	@ClientString(id = 1815, message = "$s2 was dropped in the $s1 region.")
	public static SystemMessageId S2_WAS_DROPPED_IN_THE_S1_REGION;
	
	@ClientString(id = 1816, message = "The owner of $s2 has appeared in the $s1 region.")
	public static SystemMessageId THE_OWNER_OF_S2_HAS_APPEARED_IN_THE_S1_REGION;
	
	@ClientString(id = 1817, message = "$s2's owner has logged into the $s1 region.")
	public static SystemMessageId S2_S_OWNER_HAS_LOGGED_INTO_THE_S1_REGION;
	
	@ClientString(id = 1818, message = "$s1 has disappeared.")
	public static SystemMessageId S1_HAS_DISAPPEARED_2;
	
	@ClientString(id = 1819, message = "An evil is pulsating from $s2 in $s1.")
	public static SystemMessageId AN_EVIL_IS_PULSATING_FROM_S2_IN_S1;
	
	@ClientString(id = 1820, message = "$s1 is currently asleep.")
	public static SystemMessageId S1_IS_CURRENTLY_ASLEEP;
	
	@ClientString(id = 1821, message = "$s2's evil presence is felt in $s1.")
	public static SystemMessageId S2_S_EVIL_PRESENCE_IS_FELT_IN_S1;
	
	@ClientString(id = 1822, message = "$s1 has been sealed.")
	public static SystemMessageId S1_HAS_BEEN_SEALED;
	
	@ClientString(id = 1823, message = "The registration period for a clan hall war has ended.")
	public static SystemMessageId THE_REGISTRATION_PERIOD_FOR_A_CLAN_HALL_WAR_HAS_ENDED;
	
	@ClientString(id = 1824, message = "You have been registered for a clan hall war. Please move to the left side of the clan hall's arena and get ready.")
	public static SystemMessageId YOU_HAVE_BEEN_REGISTERED_FOR_A_CLAN_HALL_WAR_PLEASE_MOVE_TO_THE_LEFT_SIDE_OF_THE_CLAN_HALL_S_ARENA_AND_GET_READY;
	
	@ClientString(id = 1825, message = "You have failed in your attempt to register for the clan hall war. Please try again.")
	public static SystemMessageId YOU_HAVE_FAILED_IN_YOUR_ATTEMPT_TO_REGISTER_FOR_THE_CLAN_HALL_WAR_PLEASE_TRY_AGAIN;
	
	@ClientString(id = 1826, message = "In $s1 minute(s), the game will begin. All players must hurry and move to the left side of the clan hall's arena.")
	public static SystemMessageId IN_S1_MINUTE_S_THE_GAME_WILL_BEGIN_ALL_PLAYERS_MUST_HURRY_AND_MOVE_TO_THE_LEFT_SIDE_OF_THE_CLAN_HALL_S_ARENA;
	
	@ClientString(id = 1827, message = "In $s1 minute(s), the game will begin. All players, please enter the arena now.")
	public static SystemMessageId IN_S1_MINUTE_S_THE_GAME_WILL_BEGIN_ALL_PLAYERS_PLEASE_ENTER_THE_ARENA_NOW;
	
	@ClientString(id = 1828, message = "In $s1 second(s), the game will begin.")
	public static SystemMessageId IN_S1_SECOND_S_THE_GAME_WILL_BEGIN;
	
	@ClientString(id = 1829, message = "The Command Channel is full.")
	public static SystemMessageId THE_COMMAND_CHANNEL_IS_FULL;
	
	@ClientString(id = 1830, message = "$c1 is not allowed to use the party room invite command. Please update the waiting list.")
	public static SystemMessageId C1_IS_NOT_ALLOWED_TO_USE_THE_PARTY_ROOM_INVITE_COMMAND_PLEASE_UPDATE_THE_WAITING_LIST;
	
	@ClientString(id = 1831, message = "$c1 does not meet the conditions of the party room. Please update the waiting list.")
	public static SystemMessageId C1_DOES_NOT_MEET_THE_CONDITIONS_OF_THE_PARTY_ROOM_PLEASE_UPDATE_THE_WAITING_LIST;
	
	@ClientString(id = 1832, message = "Only a room leader may invite others to a party room.")
	public static SystemMessageId ONLY_A_ROOM_LEADER_MAY_INVITE_OTHERS_TO_A_PARTY_ROOM;
	
	@ClientString(id = 1833, message = "All of $s1 will be dropped. Would you like to continue?")
	public static SystemMessageId ALL_OF_S1_WILL_BE_DROPPED_WOULD_YOU_LIKE_TO_CONTINUE;
	
	@ClientString(id = 1834, message = "The party room is full. No more characters can be invited in.")
	public static SystemMessageId THE_PARTY_ROOM_IS_FULL_NO_MORE_CHARACTERS_CAN_BE_INVITED_IN;
	
	@ClientString(id = 1835, message = "$s1 is full and cannot accept additional clan members at this time.")
	public static SystemMessageId S1_IS_FULL_AND_CANNOT_ACCEPT_ADDITIONAL_CLAN_MEMBERS_AT_THIS_TIME;
	
	@ClientString(id = 1836, message = "You cannot join a Clan Academy because you have successfully completed your 2nd class transfer.")
	public static SystemMessageId YOU_CANNOT_JOIN_A_CLAN_ACADEMY_BECAUSE_YOU_HAVE_SUCCESSFULLY_COMPLETED_YOUR_2ND_CLASS_TRANSFER;
	
	@ClientString(id = 1837, message = "$c1 has sent you an invitation to join the $s3 Royal Guard under the $s2 clan. Would you like to join?")
	public static SystemMessageId C1_HAS_SENT_YOU_AN_INVITATION_TO_JOIN_THE_S3_ROYAL_GUARD_UNDER_THE_S2_CLAN_WOULD_YOU_LIKE_TO_JOIN;
	
	@ClientString(id = 1838, message = "1. The coupon can be used once per character.")
	public static SystemMessageId ONE_THE_COUPON_CAN_BE_USED_ONCE_PER_CHARACTER;
	
	@ClientString(id = 1839, message = "2. A used serial number may not be used again.")
	public static SystemMessageId TWO_A_USED_SERIAL_NUMBER_MAY_NOT_BE_USED_AGAIN;
	
	@ClientString(id = 1840, message = "3. If you enter the incorrect serial number more than 5 times,\\n you may use it again after a certain amount of time passes.")
	public static SystemMessageId THREE_IF_YOU_ENTER_THE_INCORRECT_SERIAL_NUMBER_MORE_THAN_5_TIMES_N_YOU_MAY_USE_IT_AGAIN_AFTER_A_CERTAIN_AMOUNT_OF_TIME_PASSES;
	
	@ClientString(id = 1841, message = "This clan hall war has been cancelled. Not enough clans have registered.")
	public static SystemMessageId THIS_CLAN_HALL_WAR_HAS_BEEN_CANCELLED_NOT_ENOUGH_CLANS_HAVE_REGISTERED;
	
	@ClientString(id = 1842, message = "$c1 wishes to summon you from $s2. Do you accept?")
	public static SystemMessageId C1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT;
	
	@ClientString(id = 1843, message = "$c1 is engaged in combat and cannot be summoned.")
	public static SystemMessageId C1_IS_ENGAGED_IN_COMBAT_AND_CANNOT_BE_SUMMONED;
	
	@ClientString(id = 1844, message = "$c1 is dead at the moment and cannot be summoned.")
	public static SystemMessageId C1_IS_DEAD_AT_THE_MOMENT_AND_CANNOT_BE_SUMMONED;
	
	@ClientString(id = 1845, message = "Hero weapons cannot be destroyed.")
	public static SystemMessageId HERO_WEAPONS_CANNOT_BE_DESTROYED;
	
	@ClientString(id = 1846, message = "You are too far away from your mount to ride.")
	public static SystemMessageId YOU_ARE_TOO_FAR_AWAY_FROM_YOUR_MOUNT_TO_RIDE;
	
	@ClientString(id = 1847, message = "You caught a fish $s1 in length.")
	public static SystemMessageId YOU_CAUGHT_A_FISH_S1_IN_LENGTH;
	
	@ClientString(id = 1848, message = "Because of the size of fish caught, you will be registered in the ranking.")
	public static SystemMessageId BECAUSE_OF_THE_SIZE_OF_FISH_CAUGHT_YOU_WILL_BE_REGISTERED_IN_THE_RANKING;
	
	@ClientString(id = 1849, message = "All of $s1 will be discarded. Would you like to continue?")
	public static SystemMessageId ALL_OF_S1_WILL_BE_DISCARDED_WOULD_YOU_LIKE_TO_CONTINUE;
	
	@ClientString(id = 1850, message = "The Captain of the Order of Knights cannot be appointed.")
	public static SystemMessageId THE_CAPTAIN_OF_THE_ORDER_OF_KNIGHTS_CANNOT_BE_APPOINTED;
	
	@ClientString(id = 1851, message = "The Captain of the Royal Guard cannot be appointed.")
	public static SystemMessageId THE_CAPTAIN_OF_THE_ROYAL_GUARD_CANNOT_BE_APPOINTED;
	
	@ClientString(id = 1852, message = "The attempt to acquire the skill has failed because of an insufficient Clan Reputation Score.")
	public static SystemMessageId THE_ATTEMPT_TO_ACQUIRE_THE_SKILL_HAS_FAILED_BECAUSE_OF_AN_INSUFFICIENT_CLAN_REPUTATION_SCORE;
	
	@ClientString(id = 1853, message = "Quantity items of the same type cannot be exchanged at the same time.")
	public static SystemMessageId QUANTITY_ITEMS_OF_THE_SAME_TYPE_CANNOT_BE_EXCHANGED_AT_THE_SAME_TIME;
	
	@ClientString(id = 1854, message = "The item was converted successfully.")
	public static SystemMessageId THE_ITEM_WAS_CONVERTED_SUCCESSFULLY;
	
	@ClientString(id = 1855, message = "Another military unit is already using that name. Please enter a different name.")
	public static SystemMessageId ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME_PLEASE_ENTER_A_DIFFERENT_NAME;
	
	@ClientString(id = 1856, message = "Since your opponent is now the owner of $s1, the Olympiad has been cancelled.")
	public static SystemMessageId SINCE_YOUR_OPPONENT_IS_NOW_THE_OWNER_OF_S1_THE_OLYMPIAD_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 1857, message = "$c1 is the owner of $s2 and cannot participate in the Olympiad.")
	public static SystemMessageId C1_IS_THE_OWNER_OF_S2_AND_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD;
	
	@ClientString(id = 1858, message = "$c1 is currently dead and cannot participate in the Olympiad.")
	public static SystemMessageId C1_IS_CURRENTLY_DEAD_AND_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD;
	
	@ClientString(id = 1859, message = "You have exceeded the quantity that can be moved at one time.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_MOVED_AT_ONE_TIME;
	
	@ClientString(id = 1860, message = "The Clan Reputation Score is too low.")
	public static SystemMessageId THE_CLAN_REPUTATION_SCORE_IS_TOO_LOW;
	
	@ClientString(id = 1861, message = "The clan's crest has been deleted.")
	public static SystemMessageId THE_CLAN_S_CREST_HAS_BEEN_DELETED;
	
	@ClientString(id = 1862, message = "Clan skills will now be activated since the clan's reputation score is 0 or higher.")
	public static SystemMessageId CLAN_SKILLS_WILL_NOW_BE_ACTIVATED_SINCE_THE_CLAN_S_REPUTATION_SCORE_IS_0_OR_HIGHER;
	
	@ClientString(id = 1863, message = "$c1 purchased a clan item, reducing the Clan Reputation by $s2 points.")
	public static SystemMessageId C1_PURCHASED_A_CLAN_ITEM_REDUCING_THE_CLAN_REPUTATION_BY_S2_POINTS;
	
	@ClientString(id = 1864, message = "Your pet/servitor is unresponsive and will not obey any orders.")
	public static SystemMessageId YOUR_PET_SERVITOR_IS_UNRESPONSIVE_AND_WILL_NOT_OBEY_ANY_ORDERS;
	
	@ClientString(id = 1865, message = "Your pet/servitor is currently in a state of distress.")
	public static SystemMessageId YOUR_PET_SERVITOR_IS_CURRENTLY_IN_A_STATE_OF_DISTRESS;
	
	@ClientString(id = 1866, message = "MP was reduced by $s1.")
	public static SystemMessageId MP_WAS_REDUCED_BY_S1;
	
	@ClientString(id = 1867, message = "Your opponent's MP was reduced by $s1.")
	public static SystemMessageId YOUR_OPPONENT_S_MP_WAS_REDUCED_BY_S1;
	
	@ClientString(id = 1868, message = "You cannot exchange an item while it is being used.")
	public static SystemMessageId YOU_CANNOT_EXCHANGE_AN_ITEM_WHILE_IT_IS_BEING_USED;
	
	@ClientString(id = 1869, message = "$c1 has granted the Command Channel's master party the privilege of item looting.")
	public static SystemMessageId C1_HAS_GRANTED_THE_COMMAND_CHANNEL_S_MASTER_PARTY_THE_PRIVILEGE_OF_ITEM_LOOTING;
	
	@ClientString(id = 1870, message = "A Command Channel with looting rights already exists.")
	public static SystemMessageId A_COMMAND_CHANNEL_WITH_LOOTING_RIGHTS_ALREADY_EXISTS;
	
	@ClientString(id = 1871, message = "Do you want to dismiss $c1 from the clan?")
	public static SystemMessageId DO_YOU_WANT_TO_DISMISS_C1_FROM_THE_CLAN;
	
	@ClientString(id = 1872, message = "You have $s1 hour(s) and $s2 minute(s) left.")
	public static SystemMessageId YOU_HAVE_S1_HOUR_S_AND_S2_MINUTE_S_LEFT;
	
	@ClientString(id = 1873, message = "There are $s1 hour(s) and $s2 minute(s) left in the fixed use time for this PC Café.")
	public static SystemMessageId THERE_ARE_S1_HOUR_S_AND_S2_MINUTE_S_LEFT_IN_THE_FIXED_USE_TIME_FOR_THIS_PC_CAF;
	
	@ClientString(id = 1874, message = "There are $s1 minute(s) left for this individual user.")
	public static SystemMessageId THERE_ARE_S1_MINUTE_S_LEFT_FOR_THIS_INDIVIDUAL_USER;
	
	@ClientString(id = 1875, message = "There are $s1 minute(s) left in the fixed use time for this PC Café.")
	public static SystemMessageId THERE_ARE_S1_MINUTE_S_LEFT_IN_THE_FIXED_USE_TIME_FOR_THIS_PC_CAF;
	
	@ClientString(id = 1876, message = "Do you want to leave $s1 clan?")
	public static SystemMessageId DO_YOU_WANT_TO_LEAVE_S1_CLAN;
	
	@ClientString(id = 1877, message = "The game will end in $s1 minute(s).")
	public static SystemMessageId THE_GAME_WILL_END_IN_S1_MINUTE_S;
	
	@ClientString(id = 1878, message = "The game will end in $s1 second(s).")
	public static SystemMessageId THE_GAME_WILL_END_IN_S1_SECOND_S;
	
	@ClientString(id = 1879, message = "In $s1 minute(s), you will be teleported outside of the game arena.")
	public static SystemMessageId IN_S1_MINUTE_S_YOU_WILL_BE_TELEPORTED_OUTSIDE_OF_THE_GAME_ARENA;
	
	@ClientString(id = 1880, message = "In $s1 second(s), you will be teleported outside of the game arena.")
	public static SystemMessageId IN_S1_SECOND_S_YOU_WILL_BE_TELEPORTED_OUTSIDE_OF_THE_GAME_ARENA;
	
	@ClientString(id = 1881, message = "The preliminary match will begin in $s1 second(s). Prepare yourself.")
	public static SystemMessageId THE_PRELIMINARY_MATCH_WILL_BEGIN_IN_S1_SECOND_S_PREPARE_YOURSELF;
	
	@ClientString(id = 1882, message = "Characters cannot be created from this server.")
	public static SystemMessageId CHARACTERS_CANNOT_BE_CREATED_FROM_THIS_SERVER;
	
	@ClientString(id = 1883, message = "There are no offerings I own or I made a bid for.")
	public static SystemMessageId THERE_ARE_NO_OFFERINGS_I_OWN_OR_I_MADE_A_BID_FOR;
	
	@ClientString(id = 1884, message = "Enter the PC Room coupon serial number:")
	public static SystemMessageId ENTER_THE_PC_ROOM_COUPON_SERIAL_NUMBER;
	
	@ClientString(id = 1885, message = "This serial number cannot be entered. Please try again in $s1 minute(s).")
	public static SystemMessageId THIS_SERIAL_NUMBER_CANNOT_BE_ENTERED_PLEASE_TRY_AGAIN_IN_S1_MINUTE_S;
	
	@ClientString(id = 1886, message = "This serial number has already been used.")
	public static SystemMessageId THIS_SERIAL_NUMBER_HAS_ALREADY_BEEN_USED;
	
	@ClientString(id = 1887, message = "Invalid serial number. Your attempt to enter the number has failed $s1 time(s). You will be allowed to make $s2 more attempt(s).")
	public static SystemMessageId INVALID_SERIAL_NUMBER_YOUR_ATTEMPT_TO_ENTER_THE_NUMBER_HAS_FAILED_S1_TIME_S_YOU_WILL_BE_ALLOWED_TO_MAKE_S2_MORE_ATTEMPT_S;
	
	@ClientString(id = 1888, message = "Invalid serial number. Your attempt to enter the number has failed 5 times. Please try again in 4 hours.")
	public static SystemMessageId INVALID_SERIAL_NUMBER_YOUR_ATTEMPT_TO_ENTER_THE_NUMBER_HAS_FAILED_5_TIMES_PLEASE_TRY_AGAIN_IN_4_HOURS;
	
	@ClientString(id = 1889, message = "Congratulations! You have received $s1.")
	public static SystemMessageId CONGRATULATIONS_YOU_HAVE_RECEIVED_S1;
	
	@ClientString(id = 1890, message = "Since you have already used this coupon, you may not use this serial number.")
	public static SystemMessageId SINCE_YOU_HAVE_ALREADY_USED_THIS_COUPON_YOU_MAY_NOT_USE_THIS_SERIAL_NUMBER;
	
	@ClientString(id = 1891, message = "You may not use items in a private store or private work shop.")
	public static SystemMessageId YOU_MAY_NOT_USE_ITEMS_IN_A_PRIVATE_STORE_OR_PRIVATE_WORK_SHOP;
	
	@ClientString(id = 1892, message = "The replay file for the previous version cannot be played.")
	public static SystemMessageId THE_REPLAY_FILE_FOR_THE_PREVIOUS_VERSION_CANNOT_BE_PLAYED;
	
	@ClientString(id = 1893, message = "This file cannot be replayed.")
	public static SystemMessageId THIS_FILE_CANNOT_BE_REPLAYED;
	
	@ClientString(id = 1894, message = "A sub-class cannot be created or changed while you are over your weight limit.")
	public static SystemMessageId A_SUB_CLASS_CANNOT_BE_CREATED_OR_CHANGED_WHILE_YOU_ARE_OVER_YOUR_WEIGHT_LIMIT;
	
	@ClientString(id = 1895, message = "$c1 is in an area which blocks summoning.")
	public static SystemMessageId C1_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING;
	
	@ClientString(id = 1896, message = "$c1 has already been summoned.")
	public static SystemMessageId C1_HAS_ALREADY_BEEN_SUMMONED;
	
	@ClientString(id = 1897, message = "$s1 is required for summoning.")
	public static SystemMessageId S1_IS_REQUIRED_FOR_SUMMONING;
	
	@ClientString(id = 1898, message = "$c1 is currently trading or operating a private store and cannot be summoned.")
	public static SystemMessageId C1_IS_CURRENTLY_TRADING_OR_OPERATING_A_PRIVATE_STORE_AND_CANNOT_BE_SUMMONED;
	
	@ClientString(id = 1899, message = "Your target is in an area which blocks summoning.")
	public static SystemMessageId YOUR_TARGET_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING;
	
	@ClientString(id = 1900, message = "$c1 has entered the party room.")
	public static SystemMessageId C1_HAS_ENTERED_THE_PARTY_ROOM;
	
	@ClientString(id = 1901, message = "$s1 has sent an invitation to room <$s2>.")
	public static SystemMessageId S1_HAS_SENT_AN_INVITATION_TO_ROOM_S2;
	
	@ClientString(id = 1902, message = "Incompatible item grade. This item cannot be used.")
	public static SystemMessageId INCOMPATIBLE_ITEM_GRADE_THIS_ITEM_CANNOT_BE_USED;
	
	@ClientString(id = 1903, message = "Those of you who have requested NCOTP should run NCOTP \\n by using your cell phone to get the NCOTP \\n password and enter it within 1 minute.\\n If you have not requested NCOTP, leave this field blank and\\n click the Login button.")
	public static SystemMessageId THOSE_OF_YOU_WHO_HAVE_REQUESTED_NCOTP_SHOULD_RUN_NCOTP_N_BY_USING_YOUR_CELL_PHONE_TO_GET_THE_NCOTP_N_PASSWORD_AND_ENTER_IT_WITHIN_1_MINUTE_N_IF_YOU_HAVE_NOT_REQUESTED_NCOTP_LEAVE_THIS_FIELD_BLANK_AND_N_CLICK_THE_LOGIN_BUTTON;
	
	@ClientString(id = 1904, message = "A sub-class may not be created or changed while a servitor or pet is summoned.")
	public static SystemMessageId A_SUB_CLASS_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SERVITOR_OR_PET_IS_SUMMONED;
	
	@ClientString(id = 1905, message = "$s2 of $s1 will be replaced with $c4 of $s3.")
	public static SystemMessageId S2_OF_S1_WILL_BE_REPLACED_WITH_C4_OF_S3;
	
	@ClientString(id = 1906, message = "Select the combat unit\\n to transfer to.")
	public static SystemMessageId SELECT_THE_COMBAT_UNIT_N_TO_TRANSFER_TO;
	
	@ClientString(id = 1907, message = "Select the character who will\\n replace the current character.")
	public static SystemMessageId SELECT_THE_CHARACTER_WHO_WILL_N_REPLACE_THE_CURRENT_CHARACTER;
	
	@ClientString(id = 1908, message = "$c1 is in a state which prevents summoning.")
	public static SystemMessageId C1_IS_IN_A_STATE_WHICH_PREVENTS_SUMMONING;
	
	@ClientString(id = 1909, message = "==< List of Clan Academy Graduates During the Past Week >==")
	public static SystemMessageId LIST_OF_CLAN_ACADEMY_GRADUATES_DURING_THE_PAST_WEEK;
	
	@ClientString(id = 1910, message = "Graduates: $c1.")
	public static SystemMessageId GRADUATES_C1;
	
	@ClientString(id = 1911, message = "You cannot summon players who are currently participating in the Grand Olympiad.")
	public static SystemMessageId YOU_CANNOT_SUMMON_PLAYERS_WHO_ARE_CURRENTLY_PARTICIPATING_IN_THE_GRAND_OLYMPIAD;
	
	@ClientString(id = 1912, message = "Only those requesting NCOTP should make an entry into this field.")
	public static SystemMessageId ONLY_THOSE_REQUESTING_NCOTP_SHOULD_MAKE_AN_ENTRY_INTO_THIS_FIELD;
	
	@ClientString(id = 1913, message = "The remaining recycle time for $s1 is $s2 minute(s).")
	public static SystemMessageId THE_REMAINING_RECYCLE_TIME_FOR_S1_IS_S2_MINUTE_S;
	
	@ClientString(id = 1914, message = "The remaining recycle time for $s1 is $s2 second(s).")
	public static SystemMessageId THE_REMAINING_RECYCLE_TIME_FOR_S1_IS_S2_SECOND_S;
	
	@ClientString(id = 1915, message = "The game will end in $s1 second(s).")
	public static SystemMessageId THE_GAME_WILL_END_IN_S1_SECOND_S_2;
	
	@ClientString(id = 1916, message = "Your Death Penalty is now level $s1.")
	public static SystemMessageId YOUR_DEATH_PENALTY_IS_NOW_LEVEL_S1;
	
	@ClientString(id = 1917, message = "Your Death Penalty has been lifted.")
	public static SystemMessageId YOUR_DEATH_PENALTY_HAS_BEEN_LIFTED;
	
	@ClientString(id = 1918, message = "Your pet is too high level to control.")
	public static SystemMessageId YOUR_PET_IS_TOO_HIGH_LEVEL_TO_CONTROL;
	
	@ClientString(id = 1919, message = "The Grand Olympiad registration period has ended.")
	public static SystemMessageId THE_GRAND_OLYMPIAD_REGISTRATION_PERIOD_HAS_ENDED;
	
	@ClientString(id = 1920, message = "Your account is currently inactive because you have not logged into the game for some time. You may reactivate your account by visiting the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_IS_CURRENTLY_INACTIVE_BECAUSE_YOU_HAVE_NOT_LOGGED_INTO_THE_GAME_FOR_SOME_TIME_YOU_MAY_REACTIVATE_YOUR_ACCOUNT_BY_VISITING_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 1921, message = "$s2 hour(s) and $s3 minute(s) have passed since $s1 has killed.")
	public static SystemMessageId S2_HOUR_S_AND_S3_MINUTE_S_HAVE_PASSED_SINCE_S1_HAS_KILLED;
	
	@ClientString(id = 1922, message = "Because $s1 failed to kill for one full day, it has expired.")
	public static SystemMessageId BECAUSE_S1_FAILED_TO_KILL_FOR_ONE_FULL_DAY_IT_HAS_EXPIRED;
	
	@ClientString(id = 1923, message = "Court Magician: The portal has been created!")
	public static SystemMessageId COURT_MAGICIAN_THE_PORTAL_HAS_BEEN_CREATED;
	
	@ClientString(id = 1924, message = "Current Location: $s1, $s2, $s3 (near the Primeval Isle)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_PRIMEVAL_ISLE;
	
	@ClientString(id = 1925, message = "Due to the affects of the Seal of Strife, it is not possible to summon at this time.")
	public static SystemMessageId DUE_TO_THE_AFFECTS_OF_THE_SEAL_OF_STRIFE_IT_IS_NOT_POSSIBLE_TO_SUMMON_AT_THIS_TIME;
	
	@ClientString(id = 1926, message = "There is no opponent to receive your challenge for a duel.")
	public static SystemMessageId THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL;
	
	@ClientString(id = 1927, message = "$c1 has been challenged to a duel.")
	public static SystemMessageId C1_HAS_BEEN_CHALLENGED_TO_A_DUEL;
	
	@ClientString(id = 1928, message = "$c1's party has been challenged to a duel.")
	public static SystemMessageId C1_S_PARTY_HAS_BEEN_CHALLENGED_TO_A_DUEL;
	
	@ClientString(id = 1929, message = "$c1 has accepted your challenge to a duel. The duel will begin in a few moments.")
	public static SystemMessageId C1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	@ClientString(id = 1930, message = "You have accepted $c1's challenge a duel. The duel will begin in a few moments.")
	public static SystemMessageId YOU_HAVE_ACCEPTED_C1_S_CHALLENGE_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	@ClientString(id = 1931, message = "$c1 has declined your challenge to a duel.")
	public static SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL;
	
	@ClientString(id = 1932, message = "$c1 has declined your challenge to a duel.")
	public static SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL_2;
	
	@ClientString(id = 1933, message = "You have accepted $c1's challenge to a party duel. The duel will begin in a few moments.")
	public static SystemMessageId YOU_HAVE_ACCEPTED_C1_S_CHALLENGE_TO_A_PARTY_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	@ClientString(id = 1934, message = "$s1 has accepted your challenge to duel against their party. The duel will begin in a few moments.")
	public static SystemMessageId S1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_DUEL_AGAINST_THEIR_PARTY_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	@ClientString(id = 1935, message = "$c1 has declined your challenge to a party duel.")
	public static SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_PARTY_DUEL;
	
	@ClientString(id = 1936, message = "The opposing party has declined your challenge to a duel.")
	public static SystemMessageId THE_OPPOSING_PARTY_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL;
	
	@ClientString(id = 1937, message = "Since the person you challenged is not currently in a party, they cannot duel against your party.")
	public static SystemMessageId SINCE_THE_PERSON_YOU_CHALLENGED_IS_NOT_CURRENTLY_IN_A_PARTY_THEY_CANNOT_DUEL_AGAINST_YOUR_PARTY;
	
	@ClientString(id = 1938, message = "$c1 has challenged you to a duel.")
	public static SystemMessageId C1_HAS_CHALLENGED_YOU_TO_A_DUEL;
	
	@ClientString(id = 1939, message = "$c1's party has challenged your party to a duel.")
	public static SystemMessageId C1_S_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL;
	
	@ClientString(id = 1940, message = "You are unable to request a duel at this time.")
	public static SystemMessageId YOU_ARE_UNABLE_TO_REQUEST_A_DUEL_AT_THIS_TIME;
	
	@ClientString(id = 1941, message = "This is not a suitable place to challenge anyone or party to a duel.")
	public static SystemMessageId THIS_IS_NOT_A_SUITABLE_PLACE_TO_CHALLENGE_ANYONE_OR_PARTY_TO_A_DUEL;
	
	@ClientString(id = 1942, message = "The opposing party is currently unable to accept a challenge to a duel.")
	public static SystemMessageId THE_OPPOSING_PARTY_IS_CURRENTLY_UNABLE_TO_ACCEPT_A_CHALLENGE_TO_A_DUEL;
	
	@ClientString(id = 1943, message = "The opposing party is currently not in a suitable location for a duel.")
	public static SystemMessageId THE_OPPOSING_PARTY_IS_CURRENTLY_NOT_IN_A_SUITABLE_LOCATION_FOR_A_DUEL;
	
	@ClientString(id = 1944, message = "In a moment, you will be transported to the site where the duel will take place.")
	public static SystemMessageId IN_A_MOMENT_YOU_WILL_BE_TRANSPORTED_TO_THE_SITE_WHERE_THE_DUEL_WILL_TAKE_PLACE;
	
	@ClientString(id = 1945, message = "The duel will begin in $s1 second(s).")
	public static SystemMessageId THE_DUEL_WILL_BEGIN_IN_S1_SECOND_S;
	
	@ClientString(id = 1946, message = "$c1 has challenged you to a duel. Will you accept?")
	public static SystemMessageId C1_HAS_CHALLENGED_YOU_TO_A_DUEL_WILL_YOU_ACCEPT;
	
	@ClientString(id = 1947, message = "$c1's party has challenged your party to a duel. Will you accept?")
	public static SystemMessageId C1_S_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL_WILL_YOU_ACCEPT;
	
	@ClientString(id = 1948, message = "The duel will begin in $s1 second(s).")
	public static SystemMessageId THE_DUEL_WILL_BEGIN_IN_S1_SECOND_S_2;
	
	@ClientString(id = 1949, message = "Let the duel begin!")
	public static SystemMessageId LET_THE_DUEL_BEGIN;
	
	@ClientString(id = 1950, message = "$c1 has won the duel.")
	public static SystemMessageId C1_HAS_WON_THE_DUEL;
	
	@ClientString(id = 1951, message = "$c1's party has won the duel.")
	public static SystemMessageId C1_S_PARTY_HAS_WON_THE_DUEL;
	
	@ClientString(id = 1952, message = "The duel has ended in a tie.")
	public static SystemMessageId THE_DUEL_HAS_ENDED_IN_A_TIE;
	
	@ClientString(id = 1953, message = "Since $c1 was disqualified, $s2 has won.")
	public static SystemMessageId SINCE_C1_WAS_DISQUALIFIED_S2_HAS_WON;
	
	@ClientString(id = 1954, message = "Since $c1's party was disqualified, $s2's party has won.")
	public static SystemMessageId SINCE_C1_S_PARTY_WAS_DISQUALIFIED_S2_S_PARTY_HAS_WON;
	
	@ClientString(id = 1955, message = "Since $c1 withdrew from the duel, $s2 has won.")
	public static SystemMessageId SINCE_C1_WITHDREW_FROM_THE_DUEL_S2_HAS_WON;
	
	@ClientString(id = 1956, message = "Since $c1's party withdrew from the duel, $s2's party has won.")
	public static SystemMessageId SINCE_C1_S_PARTY_WITHDREW_FROM_THE_DUEL_S2_S_PARTY_HAS_WON;
	
	@ClientString(id = 1957, message = "Select the item to be augmented.")
	public static SystemMessageId SELECT_THE_ITEM_TO_BE_AUGMENTED;
	
	@ClientString(id = 1958, message = "Select the catalyst for augmentation.")
	public static SystemMessageId SELECT_THE_CATALYST_FOR_AUGMENTATION;
	
	@ClientString(id = 1959, message = "Requires $s2 $s1.")
	public static SystemMessageId REQUIRES_S2_S1;
	
	@ClientString(id = 1960, message = "This is not a suitable item.")
	public static SystemMessageId THIS_IS_NOT_A_SUITABLE_ITEM;
	
	@ClientString(id = 1961, message = "Gemstone quantity is incorrect.")
	public static SystemMessageId GEMSTONE_QUANTITY_IS_INCORRECT;
	
	@ClientString(id = 1962, message = "The item was successfully augmented!")
	public static SystemMessageId THE_ITEM_WAS_SUCCESSFULLY_AUGMENTED;
	
	@ClientString(id = 1963, message = "Select the item from which you wish to remove augmentation.")
	public static SystemMessageId SELECT_THE_ITEM_FROM_WHICH_YOU_WISH_TO_REMOVE_AUGMENTATION;
	
	@ClientString(id = 1964, message = "Augmentation removal can only be done on an augmented item.")
	public static SystemMessageId AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM;
	
	@ClientString(id = 1965, message = "Augmentation has been successfully removed from your $s1.")
	public static SystemMessageId AUGMENTATION_HAS_BEEN_SUCCESSFULLY_REMOVED_FROM_YOUR_S1;
	
	@ClientString(id = 1966, message = "Only the clan leader may issue commands.")
	public static SystemMessageId ONLY_THE_CLAN_LEADER_MAY_ISSUE_COMMANDS;
	
	@ClientString(id = 1967, message = "The gate is firmly locked. Please try again later.")
	public static SystemMessageId THE_GATE_IS_FIRMLY_LOCKED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 1968, message = "$s1's owner.")
	public static SystemMessageId S1_S_OWNER;
	
	@ClientString(id = 1969, message = "Area where $s1 appears.")
	public static SystemMessageId AREA_WHERE_S1_APPEARS;
	
	@ClientString(id = 1970, message = "Once an item is augmented, it cannot be augmented again.")
	public static SystemMessageId ONCE_AN_ITEM_IS_AUGMENTED_IT_CANNOT_BE_AUGMENTED_AGAIN;
	
	@ClientString(id = 1971, message = "The level of the hardener is too high to be used.")
	public static SystemMessageId THE_LEVEL_OF_THE_HARDENER_IS_TOO_HIGH_TO_BE_USED;
	
	@ClientString(id = 1972, message = "You cannot augment items while a private store or private workshop is in operation.")
	public static SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP_IS_IN_OPERATION;
	
	@ClientString(id = 1973, message = "You cannot augment items while frozen.")
	public static SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_FROZEN;
	
	@ClientString(id = 1974, message = "You cannot augment items while dead.")
	public static SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_DEAD;
	
	@ClientString(id = 1975, message = "You cannot augment items while engaged in trade activities.")
	public static SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_ENGAGED_IN_TRADE_ACTIVITIES;
	
	@ClientString(id = 1976, message = "You cannot augment items while paralyzed.")
	public static SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_PARALYZED;
	
	@ClientString(id = 1977, message = "You cannot augment items while fishing.")
	public static SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_FISHING;
	
	@ClientString(id = 1978, message = "You cannot augment items while sitting down.")
	public static SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_SITTING_DOWN;
	
	@ClientString(id = 1979, message = "$s1's remaining Mana is now 10.")
	public static SystemMessageId S1_S_REMAINING_MANA_IS_NOW_10;
	
	@ClientString(id = 1980, message = "$s1's remaining Mana is now 5.")
	public static SystemMessageId S1_S_REMAINING_MANA_IS_NOW_5;
	
	@ClientString(id = 1981, message = "$s1's remaining Mana is now 1. It will disappear soon.")
	public static SystemMessageId S1_S_REMAINING_MANA_IS_NOW_1_IT_WILL_DISAPPEAR_SOON;
	
	@ClientString(id = 1982, message = "$s1's remaining Mana is now 0, and the item has disappeared.")
	public static SystemMessageId S1_S_REMAINING_MANA_IS_NOW_0_AND_THE_ITEM_HAS_DISAPPEARED;
	
	@ClientString(id = 1983, message = "$s1")
	public static SystemMessageId S1_2;
	
	@ClientString(id = 1984, message = "Press the Augment button to begin.")
	public static SystemMessageId PRESS_THE_AUGMENT_BUTTON_TO_BEGIN;
	
	@ClientString(id = 1985, message = "$s1's drop area ($s2)")
	public static SystemMessageId S1_S_DROP_AREA_S2;
	
	@ClientString(id = 1986, message = "$s1's owner ($s2)")
	public static SystemMessageId S1_S_OWNER_S2;
	
	@ClientString(id = 1987, message = "$s1")
	public static SystemMessageId S1_3;
	
	@ClientString(id = 1988, message = "The ferry has arrived at Primeval Isle.")
	public static SystemMessageId THE_FERRY_HAS_ARRIVED_AT_PRIMEVAL_ISLE;
	
	@ClientString(id = 1989, message = "The ferry will leave for Rune Harbor after anchoring for three minutes.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_RUNE_HARBOR_AFTER_ANCHORING_FOR_THREE_MINUTES;
	
	@ClientString(id = 1990, message = "The ferry is now departing Primeval Isle for Rune Harbor.")
	public static SystemMessageId THE_FERRY_IS_NOW_DEPARTING_PRIMEVAL_ISLE_FOR_RUNE_HARBOR;
	
	@ClientString(id = 1991, message = "The ferry will leave for Primeval Isle after anchoring for three minutes.")
	public static SystemMessageId THE_FERRY_WILL_LEAVE_FOR_PRIMEVAL_ISLE_AFTER_ANCHORING_FOR_THREE_MINUTES;
	
	@ClientString(id = 1992, message = "The ferry is now departing Rune Harbor for Primeval Isle.")
	public static SystemMessageId THE_FERRY_IS_NOW_DEPARTING_RUNE_HARBOR_FOR_PRIMEVAL_ISLE;
	
	@ClientString(id = 1993, message = "The ferry from Primeval Isle to Rune Harbor has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_PRIMEVAL_ISLE_TO_RUNE_HARBOR_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1994, message = "The ferry from Rune Harbor to Primeval Isle has been delayed.")
	public static SystemMessageId THE_FERRY_FROM_RUNE_HARBOR_TO_PRIMEVAL_ISLE_HAS_BEEN_DELAYED;
	
	@ClientString(id = 1995, message = "$s1 channel filtering option")
	public static SystemMessageId S1_CHANNEL_FILTERING_OPTION;
	
	@ClientString(id = 1996, message = "The attack has been blocked.")
	public static SystemMessageId THE_ATTACK_HAS_BEEN_BLOCKED;
	
	@ClientString(id = 1997, message = "$c1 is performing a counterattack.")
	public static SystemMessageId C1_IS_PERFORMING_A_COUNTERATTACK;
	
	@ClientString(id = 1998, message = "You countered $c1's attack.")
	public static SystemMessageId YOU_COUNTERED_C1_S_ATTACK;
	
	@ClientString(id = 1999, message = "$c1 dodges the attack.")
	public static SystemMessageId C1_DODGES_THE_ATTACK;
	
	@ClientString(id = 2000, message = "You have avoided $c1's attack.")
	public static SystemMessageId YOU_HAVE_AVOIDED_C1_S_ATTACK_2;
	
	@ClientString(id = 2001, message = "Augmentation failed due to inappropriate conditions.")
	public static SystemMessageId AUGMENTATION_FAILED_DUE_TO_INAPPROPRIATE_CONDITIONS;
	
	@ClientString(id = 2002, message = "Trap failed.")
	public static SystemMessageId TRAP_FAILED;
	
	@ClientString(id = 2003, message = "You obtained an ordinary material.")
	public static SystemMessageId YOU_OBTAINED_AN_ORDINARY_MATERIAL;
	
	@ClientString(id = 2004, message = "You obtained a rare material.")
	public static SystemMessageId YOU_OBTAINED_A_RARE_MATERIAL;
	
	@ClientString(id = 2005, message = "You obtained a unique material.")
	public static SystemMessageId YOU_OBTAINED_A_UNIQUE_MATERIAL;
	
	@ClientString(id = 2006, message = "You obtained the only material of this kind.")
	public static SystemMessageId YOU_OBTAINED_THE_ONLY_MATERIAL_OF_THIS_KIND;
	
	@ClientString(id = 2007, message = "Please enter the recipient's name.")
	public static SystemMessageId PLEASE_ENTER_THE_RECIPIENT_S_NAME;
	
	@ClientString(id = 2008, message = "Please enter the text.")
	public static SystemMessageId PLEASE_ENTER_THE_TEXT;
	
	@ClientString(id = 2009, message = "You cannot exceed 1500 characters.")
	public static SystemMessageId YOU_CANNOT_EXCEED_1500_CHARACTERS;
	
	@ClientString(id = 2010, message = "$s2 $s1")
	public static SystemMessageId S2_S1;
	
	@ClientString(id = 2011, message = "The augmented item cannot be discarded.")
	public static SystemMessageId THE_AUGMENTED_ITEM_CANNOT_BE_DISCARDED;
	
	@ClientString(id = 2012, message = "$s1 has been activated.")
	public static SystemMessageId S1_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 2013, message = "Your seed or remaining purchase amount is inadequate.")
	public static SystemMessageId YOUR_SEED_OR_REMAINING_PURCHASE_AMOUNT_IS_INADEQUATE;
	
	@ClientString(id = 2014, message = "You cannot proceed because the manor cannot accept any more crops. All crops have been returned and no adena withdrawn.")
	public static SystemMessageId YOU_CANNOT_PROCEED_BECAUSE_THE_MANOR_CANNOT_ACCEPT_ANY_MORE_CROPS_ALL_CROPS_HAVE_BEEN_RETURNED_AND_NO_ADENA_WITHDRAWN;
	
	@ClientString(id = 2015, message = "A skill is ready to be used again.")
	public static SystemMessageId A_SKILL_IS_READY_TO_BE_USED_AGAIN;
	
	@ClientString(id = 2016, message = "A skill is ready to be used again but its re-use counter time has increased.")
	public static SystemMessageId A_SKILL_IS_READY_TO_BE_USED_AGAIN_BUT_ITS_RE_USE_COUNTER_TIME_HAS_INCREASED;
	
	@ClientString(id = 2017, message = "$c1 cannot duel because $c1 is currently engaged in a private store or manufacture.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE;
	
	@ClientString(id = 2018, message = "$c1 cannot duel because $c1 is currently fishing.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_FISHING;
	
	@ClientString(id = 2019, message = "$c1 cannot duel because $c1's HP or MP is below 50%.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_S_HP_OR_MP_IS_BELOW_50;
	
	@ClientString(id = 2020, message = "$c1 cannot make a challenge to a duel because $c1 is currently in a duel-prohibited area (Peaceful Zone / Seven Signs Zone / Near Water / Restart Prohibited Area).")
	public static SystemMessageId C1_CANNOT_MAKE_A_CHALLENGE_TO_A_DUEL_BECAUSE_C1_IS_CURRENTLY_IN_A_DUEL_PROHIBITED_AREA_PEACEFUL_ZONE_SEVEN_SIGNS_ZONE_NEAR_WATER_RESTART_PROHIBITED_AREA;
	
	@ClientString(id = 2021, message = "$c1 cannot duel because $c1 is currently engaged in battle.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_BATTLE;
	
	@ClientString(id = 2022, message = "$c1 cannot duel because $c1 is already engaged in a duel.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_ALREADY_ENGAGED_IN_A_DUEL;
	
	@ClientString(id = 2023, message = "$c1 cannot duel because $c1 is in a chaotic state.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_IN_A_CHAOTIC_STATE;
	
	@ClientString(id = 2024, message = "$c1 cannot duel because $c1 is participating in the Olympiad.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_THE_OLYMPIAD;
	
	@ClientString(id = 2025, message = "$c1 cannot duel because $c1 is participating in a clan hall war.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_CLAN_HALL_WAR;
	
	@ClientString(id = 2026, message = "$c1 cannot duel because $c1 is participating in a siege war.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_SIEGE_WAR;
	
	@ClientString(id = 2027, message = "$c1 cannot duel because $c1 is currently riding a boat, steed, or strider.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_RIDING_A_BOAT_STEED_OR_STRIDER;
	
	@ClientString(id = 2028, message = "$c1 cannot receive a duel challenge because $c1 is too far away.")
	public static SystemMessageId C1_CANNOT_RECEIVE_A_DUEL_CHALLENGE_BECAUSE_C1_IS_TOO_FAR_AWAY;
	
	@ClientString(id = 2029, message = "$c1 is currently teleporting and cannot participate in the Olympiad.")
	public static SystemMessageId C1_IS_CURRENTLY_TELEPORTING_AND_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD;
	
	@ClientString(id = 2030, message = "You are currently logging in.")
	public static SystemMessageId YOU_ARE_CURRENTLY_LOGGING_IN;
	
	@ClientString(id = 2031, message = "Please wait a moment.")
	public static SystemMessageId PLEASE_WAIT_A_MOMENT;
	
	@ClientString(id = 2032, message = "It is not the right time for purchasing the item.")
	public static SystemMessageId IT_IS_NOT_THE_RIGHT_TIME_FOR_PURCHASING_THE_ITEM;
	
	@ClientString(id = 2033, message = "A sub-class cannot be created or changed because you have exceeded your inventory limit.")
	public static SystemMessageId A_SUB_CLASS_CANNOT_BE_CREATED_OR_CHANGED_BECAUSE_YOU_HAVE_EXCEEDED_YOUR_INVENTORY_LIMIT;
	
	@ClientString(id = 2034, message = "There are $s1 hours(s) and $s2 minute(s) remaining until the item can be purchased again.")
	public static SystemMessageId THERE_ARE_S1_HOURS_S_AND_S2_MINUTE_S_REMAINING_UNTIL_THE_ITEM_CAN_BE_PURCHASED_AGAIN;
	
	@ClientString(id = 2035, message = "There are $s1 minute(s) remaining until the item can be purchased again.")
	public static SystemMessageId THERE_ARE_S1_MINUTE_S_REMAINING_UNTIL_THE_ITEM_CAN_BE_PURCHASED_AGAIN;
	
	@ClientString(id = 2036, message = "Unable to invite because the party is locked.")
	public static SystemMessageId UNABLE_TO_INVITE_BECAUSE_THE_PARTY_IS_LOCKED;
	
	@ClientString(id = 2037, message = "Unable to create character. You are unable to create a new character on the selected server. A restriction is in place which restricts users from creating characters on different servers where no previous character exists. Please choose another server.")
	public static SystemMessageId UNABLE_TO_CREATE_CHARACTER_YOU_ARE_UNABLE_TO_CREATE_A_NEW_CHARACTER_ON_THE_SELECTED_SERVER_A_RESTRICTION_IS_IN_PLACE_WHICH_RESTRICTS_USERS_FROM_CREATING_CHARACTERS_ON_DIFFERENT_SERVERS_WHERE_NO_PREVIOUS_CHARACTER_EXISTS_PLEASE_CHOOSE_ANOTHER_SERVER;
	
	@ClientString(id = 2038, message = "Some Lineage II features have been limited for free trials. Trial accounts aren’t allowed to drop items and/or Adena. To unlock all of the features of Lineage II, purchase the full version today.")
	public static SystemMessageId SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_AREN_T_ALLOWED_TO_DROP_ITEMS_AND_OR_ADENA_TO_UNLOCK_ALL_OF_THE_FEATURES_OF_LINEAGE_II_PURCHASE_THE_FULL_VERSION_TODAY;
	
	@ClientString(id = 2039, message = "Some Lineage II features have been limited for free trials. Trial accounts aren’t allowed to trade items and/or Adena. To unlock all of the features of Lineage II, purchase the full version today.")
	public static SystemMessageId SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_AREN_T_ALLOWED_TO_TRADE_ITEMS_AND_OR_ADENA_TO_UNLOCK_ALL_OF_THE_FEATURES_OF_LINEAGE_II_PURCHASE_THE_FULL_VERSION_TODAY;
	
	@ClientString(id = 2040, message = "Cannot trade items with the targeted user.")
	public static SystemMessageId CANNOT_TRADE_ITEMS_WITH_THE_TARGETED_USER;
	
	@ClientString(id = 2041, message = "Some Lineage II features have been limited for free trials. Trial accounts aren’t allowed to setup private stores. To unlock all of the features of Lineage II, purchase the full version today.")
	public static SystemMessageId SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_AREN_T_ALLOWED_TO_SETUP_PRIVATE_STORES_TO_UNLOCK_ALL_OF_THE_FEATURES_OF_LINEAGE_II_PURCHASE_THE_FULL_VERSION_TODAY;
	
	@ClientString(id = 2042, message = "This account has been suspended for non-payment based on the cell phone payment agreement.\\n Please submit proof of payment by fax (02-2186-3499) and contact customer service at 1600-0020.")
	public static SystemMessageId THIS_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_NON_PAYMENT_BASED_ON_THE_CELL_PHONE_PAYMENT_AGREEMENT_N_PLEASE_SUBMIT_PROOF_OF_PAYMENT_BY_FAX_02_2186_3499_AND_CONTACT_CUSTOMER_SERVICE_AT_1600_0020;
	
	@ClientString(id = 2043, message = "You have exceeded your inventory volume limit and may not take this quest item. Please make room in your inventory and try again.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_MAY_NOT_TAKE_THIS_QUEST_ITEM_PLEASE_MAKE_ROOM_IN_YOUR_INVENTORY_AND_TRY_AGAIN;
	
	@ClientString(id = 2044, message = "Some Lineage II features have been limited for free trials. Trial accounts aren’t allowed to set up private manufacturing stores. To unlock all of the features of Lineage II, purchase the full version today.")
	public static SystemMessageId SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_AREN_T_ALLOWED_TO_SET_UP_PRIVATE_MANUFACTURING_STORES_TO_UNLOCK_ALL_OF_THE_FEATURES_OF_LINEAGE_II_PURCHASE_THE_FULL_VERSION_TODAY;
	
	@ClientString(id = 2045, message = "Some Lineage II features have been limited for free trials. Trial accounts aren’t allowed to use private manufacturing stores. To unlock all of the features of Lineage II, purchase the full version today.")
	public static SystemMessageId SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_AREN_T_ALLOWED_TO_USE_PRIVATE_MANUFACTURING_STORES_TO_UNLOCK_ALL_OF_THE_FEATURES_OF_LINEAGE_II_PURCHASE_THE_FULL_VERSION_TODAY;
	
	@ClientString(id = 2046, message = "Some Lineage II features have been limited for free trials. Trial accounts aren’t allowed buy items from private stores. To unlock all of the features of Lineage II, purchase the full version today.")
	public static SystemMessageId SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_AREN_T_ALLOWED_BUY_ITEMS_FROM_PRIVATE_STORES_TO_UNLOCK_ALL_OF_THE_FEATURES_OF_LINEAGE_II_PURCHASE_THE_FULL_VERSION_TODAY;
	
	@ClientString(id = 2047, message = "Some Lineage II features have been limited for free trials. Trial accounts aren’t allowed to access clan warehouses. To unlock all of the features of Lineage II, purchase the full version today.")
	public static SystemMessageId SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_AREN_T_ALLOWED_TO_ACCESS_CLAN_WAREHOUSES_TO_UNLOCK_ALL_OF_THE_FEATURES_OF_LINEAGE_II_PURCHASE_THE_FULL_VERSION_TODAY;
	
	@ClientString(id = 2048, message = "The shortcut in use conflicts with $s1. Do you wish to reset the conflicting shortcuts and use the saved shortcut?")
	public static SystemMessageId THE_SHORTCUT_IN_USE_CONFLICTS_WITH_S1_DO_YOU_WISH_TO_RESET_THE_CONFLICTING_SHORTCUTS_AND_USE_THE_SAVED_SHORTCUT;
	
	@ClientString(id = 2049, message = "The shortcut will be applied and saved in the server. Will you continue?")
	public static SystemMessageId THE_SHORTCUT_WILL_BE_APPLIED_AND_SAVED_IN_THE_SERVER_WILL_YOU_CONTINUE;
	
	@ClientString(id = 2050, message = "$s1 clan is trying to display a flag.")
	public static SystemMessageId S1_CLAN_IS_TRYING_TO_DISPLAY_A_FLAG;
	
	@ClientString(id = 2051, message = "You must accept the User Agreement before this account can access Lineage II.\\n Please try again after accepting the agreement on the PlayNC website (http://us.ncsoft.com).")
	public static SystemMessageId YOU_MUST_ACCEPT_THE_USER_AGREEMENT_BEFORE_THIS_ACCOUNT_CAN_ACCESS_LINEAGE_II_N_PLEASE_TRY_AGAIN_AFTER_ACCEPTING_THE_AGREEMENT_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM;
	
	@ClientString(id = 2052, message = "A guardian's consent is required before this account can be used to play Lineage II.\\nPlease try again after this consent is provided.")
	public static SystemMessageId A_GUARDIAN_S_CONSENT_IS_REQUIRED_BEFORE_THIS_ACCOUNT_CAN_BE_USED_TO_PLAY_LINEAGE_II_NPLEASE_TRY_AGAIN_AFTER_THIS_CONSENT_IS_PROVIDED;
	
	@ClientString(id = 2053, message = "This account has declined the User Agreement or is pending a withdrawal request. \\nPlease try again after cancelling this request.")
	public static SystemMessageId THIS_ACCOUNT_HAS_DECLINED_THE_USER_AGREEMENT_OR_IS_PENDING_A_WITHDRAWAL_REQUEST_NPLEASE_TRY_AGAIN_AFTER_CANCELLING_THIS_REQUEST;
	
	@ClientString(id = 2054, message = "This account has been suspended. \\nFor more information, please call the Customer's Center (Tel. 1600-0020).")
	public static SystemMessageId THIS_ACCOUNT_HAS_BEEN_SUSPENDED_NFOR_MORE_INFORMATION_PLEASE_CALL_THE_CUSTOMER_S_CENTER_TEL_1600_0020;
	
	@ClientString(id = 2055, message = "Your account has been suspended from all game services.\\nFor more information, please visit the PlayNC website Customer's Center (http://us.ncsoft.com).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_NFOR_MORE_INFORMATION_PLEASE_VISIT_THE_PLAYNC_WEBSITE_CUSTOMER_S_CENTER_HTTP_US_NCSOFT_COM;
	
	@ClientString(id = 2056, message = "Your account has been converted to an integrated account, and is unable to be accessed. \\nPlease logon with the converted integrated account.")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_CONVERTED_TO_AN_INTEGRATED_ACCOUNT_AND_IS_UNABLE_TO_BE_ACCESSED_NPLEASE_LOGON_WITH_THE_CONVERTED_INTEGRATED_ACCOUNT;
	
	@ClientString(id = 2057, message = "You have blocked $c1.")
	public static SystemMessageId YOU_HAVE_BLOCKED_C1;
	
	@ClientString(id = 2058, message = "You already polymorphed and cannot polymorph again.")
	public static SystemMessageId YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN;
	
	@ClientString(id = 2059, message = "The nearby area is too narrow for you to polymorph. Please move to another area and try to polymorph again.")
	public static SystemMessageId THE_NEARBY_AREA_IS_TOO_NARROW_FOR_YOU_TO_POLYMORPH_PLEASE_MOVE_TO_ANOTHER_AREA_AND_TRY_TO_POLYMORPH_AGAIN;
	
	@ClientString(id = 2060, message = "You cannot polymorph into the desired form in water.")
	public static SystemMessageId YOU_CANNOT_POLYMORPH_INTO_THE_DESIRED_FORM_IN_WATER;
	
	@ClientString(id = 2061, message = "You are still under transform penalty and cannot be polymorphed.")
	public static SystemMessageId YOU_ARE_STILL_UNDER_TRANSFORM_PENALTY_AND_CANNOT_BE_POLYMORPHED;
	
	@ClientString(id = 2062, message = "You cannot polymorph when you have summoned a servitor/pet.")
	public static SystemMessageId YOU_CANNOT_POLYMORPH_WHEN_YOU_HAVE_SUMMONED_A_SERVITOR_PET;
	
	@ClientString(id = 2063, message = "You cannot polymorph while riding a pet.")
	public static SystemMessageId YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_PET;
	
	@ClientString(id = 2064, message = "You cannot polymorph while under the effect of a special skill.")
	public static SystemMessageId YOU_CANNOT_POLYMORPH_WHILE_UNDER_THE_EFFECT_OF_A_SPECIAL_SKILL;
	
	@ClientString(id = 2065, message = "That item cannot be taken off.")
	public static SystemMessageId THAT_ITEM_CANNOT_BE_TAKEN_OFF;
	
	@ClientString(id = 2066, message = "That weapon cannot perform any attacks.")
	public static SystemMessageId THAT_WEAPON_CANNOT_PERFORM_ANY_ATTACKS;
	
	@ClientString(id = 2067, message = "That weapon cannot use any other skill except the weapon's skill.")
	public static SystemMessageId THAT_WEAPON_CANNOT_USE_ANY_OTHER_SKILL_EXCEPT_THE_WEAPON_S_SKILL;
	
	@ClientString(id = 2068, message = "You do not have all of the items needed to untrain the enchant skill.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_UNTRAIN_THE_ENCHANT_SKILL;
	
	@ClientString(id = 2069, message = "Untrain of enchant skill was successful. Current level of enchant skill $s1 has been decreased by 1.")
	public static SystemMessageId UNTRAIN_OF_ENCHANT_SKILL_WAS_SUCCESSFUL_CURRENT_LEVEL_OF_ENCHANT_SKILL_S1_HAS_BEEN_DECREASED_BY_1;
	
	@ClientString(id = 2070, message = "Untrain of enchant skill was successful. Current level of enchant skill $s1 became 0 and enchant skill will be initialized.")
	public static SystemMessageId UNTRAIN_OF_ENCHANT_SKILL_WAS_SUCCESSFUL_CURRENT_LEVEL_OF_ENCHANT_SKILL_S1_BECAME_0_AND_ENCHANT_SKILL_WILL_BE_INITIALIZED;
	
	@ClientString(id = 2071, message = "You do not have all of the items needed to enchant skill route change.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_ENCHANT_SKILL_ROUTE_CHANGE;
	
	@ClientString(id = 2072, message = "Enchant skill route change was successful. Lv of enchant skill $s1 has been decreased by $s2.")
	public static SystemMessageId ENCHANT_SKILL_ROUTE_CHANGE_WAS_SUCCESSFUL_LV_OF_ENCHANT_SKILL_S1_HAS_BEEN_DECREASED_BY_S2;
	
	@ClientString(id = 2073, message = "Enchant skill route change was successful. Lv of enchant skill $s1 will remain.")
	public static SystemMessageId ENCHANT_SKILL_ROUTE_CHANGE_WAS_SUCCESSFUL_LV_OF_ENCHANT_SKILL_S1_WILL_REMAIN;
	
	@ClientString(id = 2074, message = "Skill enchant failed. Current level of enchant skill $s1 will remain unchanged.")
	public static SystemMessageId SKILL_ENCHANT_FAILED_CURRENT_LEVEL_OF_ENCHANT_SKILL_S1_WILL_REMAIN_UNCHANGED;
	
	@ClientString(id = 2075, message = "It is not an auction period.")
	public static SystemMessageId IT_IS_NOT_AN_AUCTION_PERIOD;
	
	@ClientString(id = 2076, message = "Bidding is not allowed because the maximum bidding price exceeds 100 billion.")
	public static SystemMessageId BIDDING_IS_NOT_ALLOWED_BECAUSE_THE_MAXIMUM_BIDDING_PRICE_EXCEEDS_100_BILLION;
	
	@ClientString(id = 2077, message = "Your bid must be higher than the current highest bid.")
	public static SystemMessageId YOUR_BID_MUST_BE_HIGHER_THAN_THE_CURRENT_HIGHEST_BID;
	
	@ClientString(id = 2078, message = "You do not have enough adena for this bid.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_ADENA_FOR_THIS_BID;
	
	@ClientString(id = 2079, message = "You currently have the highest bid, but the reserve has not been met.")
	public static SystemMessageId YOU_CURRENTLY_HAVE_THE_HIGHEST_BID_BUT_THE_RESERVE_HAS_NOT_BEEN_MET;
	
	@ClientString(id = 2080, message = "You have been outbid.")
	public static SystemMessageId YOU_HAVE_BEEN_OUTBID;
	
	@ClientString(id = 2081, message = "There are no funds presently due to you.")
	public static SystemMessageId THERE_ARE_NO_FUNDS_PRESENTLY_DUE_TO_YOU;
	
	@ClientString(id = 2082, message = "You have exceeded the total amount of adena allowed in inventory.")
	public static SystemMessageId YOU_HAVE_EXCEEDED_THE_TOTAL_AMOUNT_OF_ADENA_ALLOWED_IN_INVENTORY;
	
	@ClientString(id = 2083, message = "The auction has begun.")
	public static SystemMessageId THE_AUCTION_HAS_BEGUN;
	
	@ClientString(id = 2084, message = "Enemy Blood Pledges have intruded into the fortress.")
	public static SystemMessageId ENEMY_BLOOD_PLEDGES_HAVE_INTRUDED_INTO_THE_FORTRESS;
	
	@ClientString(id = 2085, message = "Shout and trade chatting cannot be used while possessing a cursed weapon.")
	public static SystemMessageId SHOUT_AND_TRADE_CHATTING_CANNOT_BE_USED_WHILE_POSSESSING_A_CURSED_WEAPON;
	
	@ClientString(id = 2086, message = "Search on user $c2 for third-party program use will be completed in $s1 minute(s).")
	public static SystemMessageId SEARCH_ON_USER_C2_FOR_THIRD_PARTY_PROGRAM_USE_WILL_BE_COMPLETED_IN_S1_MINUTE_S;
	
	@ClientString(id = 2087, message = "A fortress is under attack!")
	public static SystemMessageId A_FORTRESS_IS_UNDER_ATTACK;
	
	@ClientString(id = 2088, message = "$s1 minute(s) until the fortress battle starts.")
	public static SystemMessageId S1_MINUTE_S_UNTIL_THE_FORTRESS_BATTLE_STARTS;
	
	@ClientString(id = 2089, message = "$s1 second(s) until the fortress battle starts.")
	public static SystemMessageId S1_SECOND_S_UNTIL_THE_FORTRESS_BATTLE_STARTS;
	
	@ClientString(id = 2090, message = "The fortress battle $s1 has begun.")
	public static SystemMessageId THE_FORTRESS_BATTLE_S1_HAS_BEGUN;
	
	@ClientString(id = 2091, message = "Your account can only be used after changing your password and quiz. \\n Services will be available after changing your password and quiz from the PlayNC website (http://us.ncsoft.com).")
	public static SystemMessageId YOUR_ACCOUNT_CAN_ONLY_BE_USED_AFTER_CHANGING_YOUR_PASSWORD_AND_QUIZ_N_SERVICES_WILL_BE_AVAILABLE_AFTER_CHANGING_YOUR_PASSWORD_AND_QUIZ_FROM_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM;
	
	@ClientString(id = 2092, message = "You cannot bid due to a passed-in price.")
	public static SystemMessageId YOU_CANNOT_BID_DUE_TO_A_PASSED_IN_PRICE;
	
	@ClientString(id = 2093, message = "The bid amount was $s1 adena. Would you like to retrieve the bid amount?")
	public static SystemMessageId THE_BID_AMOUNT_WAS_S1_ADENA_WOULD_YOU_LIKE_TO_RETRIEVE_THE_BID_AMOUNT;
	
	@ClientString(id = 2094, message = "Another user is purchasing. Please try again later.")
	public static SystemMessageId ANOTHER_USER_IS_PURCHASING_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 2095, message = "Some Lineage II features have been limited for free trials. Trial accounts have limited chatting capabilities. To unlock all of the features of Lineage II, purchase the full version today.")
	public static SystemMessageId SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_HAVE_LIMITED_CHATTING_CAPABILITIES_TO_UNLOCK_ALL_OF_THE_FEATURES_OF_LINEAGE_II_PURCHASE_THE_FULL_VERSION_TODAY;
	
	@ClientString(id = 2096, message = "$c1 is in a location which cannot be entered, therefore it cannot be processed.")
	public static SystemMessageId C1_IS_IN_A_LOCATION_WHICH_CANNOT_BE_ENTERED_THEREFORE_IT_CANNOT_BE_PROCESSED;
	
	@ClientString(id = 2097, message = "$c1's level does not correspond to the requirements for entry.")
	public static SystemMessageId C1_S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY;
	
	@ClientString(id = 2098, message = "$c1's quest requirement is not sufficient and cannot be entered.")
	public static SystemMessageId C1_S_QUEST_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED;
	
	@ClientString(id = 2099, message = "$c1's item requirement is not sufficient and cannot be entered.")
	public static SystemMessageId C1_S_ITEM_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED;
	
	@ClientString(id = 2100, message = "$c1 may not re-enter yet.")
	public static SystemMessageId C1_MAY_NOT_RE_ENTER_YET;
	
	@ClientString(id = 2101, message = "You are not currently in a party, so you cannot enter.")
	public static SystemMessageId YOU_ARE_NOT_CURRENTLY_IN_A_PARTY_SO_YOU_CANNOT_ENTER;
	
	@ClientString(id = 2102, message = "You cannot enter due to the party having exceeded the limit.")
	public static SystemMessageId YOU_CANNOT_ENTER_DUE_TO_THE_PARTY_HAVING_EXCEEDED_THE_LIMIT;
	
	@ClientString(id = 2103, message = "You cannot enter because you are not associated with the current command channel.")
	public static SystemMessageId YOU_CANNOT_ENTER_BECAUSE_YOU_ARE_NOT_ASSOCIATED_WITH_THE_CURRENT_COMMAND_CHANNEL;
	
	@ClientString(id = 2104, message = "The maximum number of instance zones has been exceeded. You cannot enter.")
	public static SystemMessageId THE_MAXIMUM_NUMBER_OF_INSTANCE_ZONES_HAS_BEEN_EXCEEDED_YOU_CANNOT_ENTER;
	
	@ClientString(id = 2105, message = "You have entered another instance zone, therefore you cannot enter corresponding dungeon.")
	public static SystemMessageId YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON;
	
	@ClientString(id = 2106, message = "This dungeon will expire in $s1 minute(s). You will be forced out of the dungeon when the time expires.")
	public static SystemMessageId THIS_DUNGEON_WILL_EXPIRE_IN_S1_MINUTE_S_YOU_WILL_BE_FORCED_OUT_OF_THE_DUNGEON_WHEN_THE_TIME_EXPIRES;
	
	@ClientString(id = 2107, message = "This instance zone will be terminated in $s1 minute(s). You will be forced out of the dungeon when the time expires.")
	public static SystemMessageId THIS_INSTANCE_ZONE_WILL_BE_TERMINATED_IN_S1_MINUTE_S_YOU_WILL_BE_FORCED_OUT_OF_THE_DUNGEON_WHEN_THE_TIME_EXPIRES;
	
	@ClientString(id = 2108, message = "Your account has been suspended for 10 days for use of illegal software and may be permanently suspended. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_10_DAYS_FOR_USE_OF_ILLEGAL_SOFTWARE_AND_MAY_BE_PERMANENTLY_SUSPENDED_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2109, message = "During the server merge, your character name, $s1, conflicted with another. Your name may still be available. Please enter your desired name.")
	public static SystemMessageId DURING_THE_SERVER_MERGE_YOUR_CHARACTER_NAME_S1_CONFLICTED_WITH_ANOTHER_YOUR_NAME_MAY_STILL_BE_AVAILABLE_PLEASE_ENTER_YOUR_DESIRED_NAME;
	
	@ClientString(id = 2110, message = "This character name already exists or is an invalid name. Please enter a new name.")
	public static SystemMessageId THIS_CHARACTER_NAME_ALREADY_EXISTS_OR_IS_AN_INVALID_NAME_PLEASE_ENTER_A_NEW_NAME;
	
	@ClientString(id = 2111, message = "Enter a shortcut to assign.")
	public static SystemMessageId ENTER_A_SHORTCUT_TO_ASSIGN;
	
	@ClientString(id = 2112, message = "Sub-key can be CTRL, ALT, SHIFT and you may enter two sub-keys at a time. \\n Example) 'CTRL + ALT + A'")
	public static SystemMessageId SUB_KEY_CAN_BE_CTRL_ALT_SHIFT_AND_YOU_MAY_ENTER_TWO_SUB_KEYS_AT_A_TIME_N_EXAMPLE_CTRL_ALT_A;
	
	@ClientString(id = 2113, message = "CTRL, ALT, SHIFT keys may be used as sub-key in expanded sub-key mode, and only ALT may be used as a sub-key in standard sub-key mode.")
	public static SystemMessageId CTRL_ALT_SHIFT_KEYS_MAY_BE_USED_AS_SUB_KEY_IN_EXPANDED_SUB_KEY_MODE_AND_ONLY_ALT_MAY_BE_USED_AS_A_SUB_KEY_IN_STANDARD_SUB_KEY_MODE;
	
	@ClientString(id = 2114, message = "Forced attack and stand-in-place attacks assigned previously to Ctrl and Shift will be changed to Alt + Q and Alt + E when set as expanded sub-key mode, and CTRL and SHIFT will be available to assign to another shortcut. Will you continue?")
	public static SystemMessageId FORCED_ATTACK_AND_STAND_IN_PLACE_ATTACKS_ASSIGNED_PREVIOUSLY_TO_CTRL_AND_SHIFT_WILL_BE_CHANGED_TO_ALT_Q_AND_ALT_E_WHEN_SET_AS_EXPANDED_SUB_KEY_MODE_AND_CTRL_AND_SHIFT_WILL_BE_AVAILABLE_TO_ASSIGN_TO_ANOTHER_SHORTCUT_WILL_YOU_CONTINUE;
	
	@ClientString(id = 2115, message = "Your account has been suspended for abusing a bug related to the NCcoin. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_ABUSING_A_BUG_RELATED_TO_THE_NCCOIN_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2116, message = "Your account has been suspended for abusing a free NCcoin. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_ABUSING_A_FREE_NCCOIN_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2117, message = "Your account has been suspended for using another person's identification. If you were not involved with any of these violations, please verify your identity. For more information, please visit the PlayNC website Customer's Center (http://us.ncsoft.com).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_USING_ANOTHER_PERSON_S_IDENTIFICATION_IF_YOU_WERE_NOT_INVOLVED_WITH_ANY_OF_THESE_VIOLATIONS_PLEASE_VERIFY_YOUR_IDENTITY_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_PLAYNC_WEBSITE_CUSTOMER_S_CENTER_HTTP_US_NCSOFT_COM;
	
	@ClientString(id = 2118, message = "Your account has been suspended for misappropriating payment under another player's account. For more information, please visit the Customer Service Center of the PlayNC website (http://us.ncsoft.com).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_MISAPPROPRIATING_PAYMENT_UNDER_ANOTHER_PLAYER_S_ACCOUNT_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_CUSTOMER_SERVICE_CENTER_OF_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM;
	
	@ClientString(id = 2119, message = "Your account has been suspended from all game services after being detected with dealing an account.\\nFor more information, please visit the PlayNC website Customer's Center (http://us.ncsoft.com).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_AFTER_BEING_DETECTED_WITH_DEALING_AN_ACCOUNT_NFOR_MORE_INFORMATION_PLEASE_VISIT_THE_PLAYNC_WEBSITE_CUSTOMER_S_CENTER_HTTP_US_NCSOFT_COM;
	
	@ClientString(id = 2120, message = "Your account has been suspended for 10 days for using illegal software. Your account may be suspended permanently if you have been previously caught for the same violation. For more information, please visit the PlayNC website Customer's Center (http://us.ncsoft.com).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_10_DAYS_FOR_USING_ILLEGAL_SOFTWARE_YOUR_ACCOUNT_MAY_BE_SUSPENDED_PERMANENTLY_IF_YOU_HAVE_BEEN_PREVIOUSLY_CAUGHT_FOR_THE_SAME_VIOLATION_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_PLAYNC_WEBSITE_CUSTOMER_S_CENTER_HTTP_US_NCSOFT_COM;
	
	@ClientString(id = 2121, message = "Your account has been suspended from all game services for use of illegal software. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_FOR_USE_OF_ILLEGAL_SOFTWARE_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2122, message = "Your account has been suspended from all game services for use of illegal software. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_FOR_USE_OF_ILLEGAL_SOFTWARE_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT_2;
	
	@ClientString(id = 2123, message = "Your account has been suspended at your own request. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_AT_YOUR_OWN_REQUEST_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2124, message = "During the server merge, your clan name, $s1, conflicted with another. Your clan name may still be available. Please enter your desired name.")
	public static SystemMessageId DURING_THE_SERVER_MERGE_YOUR_CLAN_NAME_S1_CONFLICTED_WITH_ANOTHER_YOUR_CLAN_NAME_MAY_STILL_BE_AVAILABLE_PLEASE_ENTER_YOUR_DESIRED_NAME;
	
	@ClientString(id = 2125, message = "The clan name already exists or is an invalid name. Please enter aother clan name.")
	public static SystemMessageId THE_CLAN_NAME_ALREADY_EXISTS_OR_IS_AN_INVALID_NAME_PLEASE_ENTER_AOTHER_CLAN_NAME;
	
	@ClientString(id = 2126, message = "Your account has been suspended for regularly posting illegal messages. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_REGULARLY_POSTING_ILLEGAL_MESSAGES_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2127, message = "Your account has been suspended after being detected with an illegal message. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_AFTER_BEING_DETECTED_WITH_AN_ILLEGAL_MESSAGE_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2128, message = "Your account has been suspended from all game services for using the game for commercial purposes. For more information, please visit the Support Center on the PlayNC website (http://us.ncsoft.com/support/).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_FOR_USING_THE_GAME_FOR_COMMERCIAL_PURPOSES_FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2129, message = "The augmented item cannot be converted. Please convert after the augmentation has been removed.")
	public static SystemMessageId THE_AUGMENTED_ITEM_CANNOT_BE_CONVERTED_PLEASE_CONVERT_AFTER_THE_AUGMENTATION_HAS_BEEN_REMOVED;
	
	@ClientString(id = 2130, message = "You cannot convert this item.")
	public static SystemMessageId YOU_CANNOT_CONVERT_THIS_ITEM;
	
	@ClientString(id = 2131, message = "You have bid the highest price and have won the item. The item can be found in your personal warehouse.")
	public static SystemMessageId YOU_HAVE_BID_THE_HIGHEST_PRICE_AND_HAVE_WON_THE_ITEM_THE_ITEM_CAN_BE_FOUND_IN_YOUR_PERSONAL_WAREHOUSE;
	
	@ClientString(id = 2132, message = "You have entered a common sever.")
	public static SystemMessageId YOU_HAVE_ENTERED_A_COMMON_SEVER;
	
	@ClientString(id = 2133, message = "You have entered an adults-only sever.")
	public static SystemMessageId YOU_HAVE_ENTERED_AN_ADULTS_ONLY_SEVER;
	
	@ClientString(id = 2134, message = "You have entered a server for juveniles.")
	public static SystemMessageId YOU_HAVE_ENTERED_A_SERVER_FOR_JUVENILES;
	
	@ClientString(id = 2135, message = "Because of your Fatigue level, this is not allowed.")
	public static SystemMessageId BECAUSE_OF_YOUR_FATIGUE_LEVEL_THIS_IS_NOT_ALLOWED;
	
	@ClientString(id = 2136, message = "A clan name change application has been submitted.")
	public static SystemMessageId A_CLAN_NAME_CHANGE_APPLICATION_HAS_BEEN_SUBMITTED;
	
	@ClientString(id = 2137, message = "You are about to bid $s1 item with $s2 adena. Will you continue?")
	public static SystemMessageId YOU_ARE_ABOUT_TO_BID_S1_ITEM_WITH_S2_ADENA_WILL_YOU_CONTINUE;
	
	@ClientString(id = 2138, message = "Please enter a bid price.")
	public static SystemMessageId PLEASE_ENTER_A_BID_PRICE;
	
	@ClientString(id = 2139, message = "$c1's Pet.")
	public static SystemMessageId C1_S_PET;
	
	@ClientString(id = 2140, message = "$c1's Servitor.")
	public static SystemMessageId C1_S_SERVITOR;
	
	@ClientString(id = 2141, message = "You slightly resisted $c1's magic.")
	public static SystemMessageId YOU_SLIGHTLY_RESISTED_C1_S_MAGIC;
	
	@ClientString(id = 2142, message = "You cannot expel $c1 because $c1 is not a party member.")
	public static SystemMessageId YOU_CANNOT_EXPEL_C1_BECAUSE_C1_IS_NOT_A_PARTY_MEMBER;
	
	@ClientString(id = 2143, message = "You cannot add elemental power while operating a Private Store or Private Workshop.")
	public static SystemMessageId YOU_CANNOT_ADD_ELEMENTAL_POWER_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP;
	
	@ClientString(id = 2144, message = "Please select item to add elemental power.")
	public static SystemMessageId PLEASE_SELECT_ITEM_TO_ADD_ELEMENTAL_POWER;
	
	@ClientString(id = 2145, message = "Attribute item usage has been cancelled.")
	public static SystemMessageId ATTRIBUTE_ITEM_USAGE_HAS_BEEN_CANCELLED;
	
	@ClientString(id = 2146, message = "Elemental power enhancer usage requirement is not sufficient.")
	public static SystemMessageId ELEMENTAL_POWER_ENHANCER_USAGE_REQUIREMENT_IS_NOT_SUFFICIENT;
	
	@ClientString(id = 2147, message = "$s2 elemental power has been added successfully to $s1.")
	public static SystemMessageId S2_ELEMENTAL_POWER_HAS_BEEN_ADDED_SUCCESSFULLY_TO_S1;
	
	@ClientString(id = 2148, message = "$s3 elemental power has been added successfully to +$s1 $s2.")
	public static SystemMessageId S3_ELEMENTAL_POWER_HAS_BEEN_ADDED_SUCCESSFULLY_TO_S1_S2;
	
	@ClientString(id = 2149, message = "You have failed to add elemental power.")
	public static SystemMessageId YOU_HAVE_FAILED_TO_ADD_ELEMENTAL_POWER;
	
	@ClientString(id = 2150, message = "Another elemental power has already been added. This elemental power cannot be added.")
	public static SystemMessageId ANOTHER_ELEMENTAL_POWER_HAS_ALREADY_BEEN_ADDED_THIS_ELEMENTAL_POWER_CANNOT_BE_ADDED;
	
	@ClientString(id = 2151, message = "Your opponent has resistance to magic, the damage was decreased.")
	public static SystemMessageId YOUR_OPPONENT_HAS_RESISTANCE_TO_MAGIC_THE_DAMAGE_WAS_DECREASED;
	
	@ClientString(id = 2152, message = "The assigned shortcut will be deleted and the initial shortcut setting restored. Will you continue?")
	public static SystemMessageId THE_ASSIGNED_SHORTCUT_WILL_BE_DELETED_AND_THE_INITIAL_SHORTCUT_SETTING_RESTORED_WILL_YOU_CONTINUE;
	
	@ClientString(id = 2153, message = "You are currently logged into 10 of your accounts and can no longer access your other accounts.")
	public static SystemMessageId YOU_ARE_CURRENTLY_LOGGED_INTO_10_OF_YOUR_ACCOUNTS_AND_CAN_NO_LONGER_ACCESS_YOUR_OTHER_ACCOUNTS;
	
	@ClientString(id = 2154, message = "The target is not a flagpole so a flag cannot be displayed.")
	public static SystemMessageId THE_TARGET_IS_NOT_A_FLAGPOLE_SO_A_FLAG_CANNOT_BE_DISPLAYED;
	
	@ClientString(id = 2155, message = "A flag is already being displayed, another flag cannot be displayed.")
	public static SystemMessageId A_FLAG_IS_ALREADY_BEING_DISPLAYED_ANOTHER_FLAG_CANNOT_BE_DISPLAYED;
	
	@ClientString(id = 2156, message = "There are not enough necessary items to use the skill.")
	public static SystemMessageId THERE_ARE_NOT_ENOUGH_NECESSARY_ITEMS_TO_USE_THE_SKILL;
	
	@ClientString(id = 2157, message = "Bid will be attempted with $s1 adena.")
	public static SystemMessageId BID_WILL_BE_ATTEMPTED_WITH_S1_ADENA;
	
	@ClientString(id = 2158, message = "Force attack is impossible against a temporary allied member during a siege.")
	public static SystemMessageId FORCE_ATTACK_IS_IMPOSSIBLE_AGAINST_A_TEMPORARY_ALLIED_MEMBER_DURING_A_SIEGE;
	
	@ClientString(id = 2159, message = "Bidder exists, the auction time has been extended by 5 minutes.")
	public static SystemMessageId BIDDER_EXISTS_THE_AUCTION_TIME_HAS_BEEN_EXTENDED_BY_5_MINUTES;
	
	@ClientString(id = 2160, message = "Bidder exists, auction time has been extended by 3 minutes.")
	public static SystemMessageId BIDDER_EXISTS_AUCTION_TIME_HAS_BEEN_EXTENDED_BY_3_MINUTES;
	
	@ClientString(id = 2161, message = "There is not enough space to move, the skill cannot be used.")
	public static SystemMessageId THERE_IS_NOT_ENOUGH_SPACE_TO_MOVE_THE_SKILL_CANNOT_BE_USED;
	
	@ClientString(id = 2162, message = "Your soul count has increased by $s1. It is now at $s2.")
	public static SystemMessageId YOUR_SOUL_COUNT_HAS_INCREASED_BY_S1_IT_IS_NOW_AT_S2;
	
	@ClientString(id = 2163, message = "Soul cannot be increased anymore.")
	public static SystemMessageId SOUL_CANNOT_BE_INCREASED_ANYMORE;
	
	@ClientString(id = 2164, message = "The barracks have been seized.")
	public static SystemMessageId THE_BARRACKS_HAVE_BEEN_SEIZED;
	
	@ClientString(id = 2165, message = "The barracks function has been restored.")
	public static SystemMessageId THE_BARRACKS_FUNCTION_HAS_BEEN_RESTORED;
	
	@ClientString(id = 2166, message = "All barracks are occupied.")
	public static SystemMessageId ALL_BARRACKS_ARE_OCCUPIED;
	
	@ClientString(id = 2167, message = "A malicious skill cannot be used in a peace zone.")
	public static SystemMessageId A_MALICIOUS_SKILL_CANNOT_BE_USED_IN_A_PEACE_ZONE;
	
	@ClientString(id = 2168, message = "$c1 has acquired the flag.")
	public static SystemMessageId C1_HAS_ACQUIRED_THE_FLAG;
	
	@ClientString(id = 2169, message = "Your clan has been registered to $s1's fortress battle.")
	public static SystemMessageId YOUR_CLAN_HAS_BEEN_REGISTERED_TO_S1_S_FORTRESS_BATTLE;
	
	@ClientString(id = 2170, message = "A malicious skill cannot be used when an opponent is in the peace zone.")
	public static SystemMessageId A_MALICIOUS_SKILL_CANNOT_BE_USED_WHEN_AN_OPPONENT_IS_IN_THE_PEACE_ZONE;
	
	@ClientString(id = 2171, message = "This item cannot be crystallized.")
	public static SystemMessageId THIS_ITEM_CANNOT_BE_CRYSTALLIZED;
	
	@ClientString(id = 2172, message = "+$s1$s2's auction has ended.")
	public static SystemMessageId S1_S2_S_AUCTION_HAS_ENDED;
	
	@ClientString(id = 2173, message = "$s1's auction has ended.")
	public static SystemMessageId S1_S_AUCTION_HAS_ENDED;
	
	@ClientString(id = 2174, message = "$c1 cannot duel because $c1 is currently polymorphed.")
	public static SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_POLYMORPHED;
	
	@ClientString(id = 2175, message = "Party duel cannot be initiated due to a polymorphed party member.")
	public static SystemMessageId PARTY_DUEL_CANNOT_BE_INITIATED_DUE_TO_A_POLYMORPHED_PARTY_MEMBER;
	
	@ClientString(id = 2176, message = "$s1's $s2 attribute has been removed.")
	public static SystemMessageId S1_S_S2_ATTRIBUTE_HAS_BEEN_REMOVED;
	
	@ClientString(id = 2177, message = "+$s1$s2's $s3 attribute has been removed.")
	public static SystemMessageId S1_S2_S_S3_ATTRIBUTE_HAS_BEEN_REMOVED;
	
	@ClientString(id = 2178, message = "Attribute removal has failed.")
	public static SystemMessageId ATTRIBUTE_REMOVAL_HAS_FAILED;
	
	@ClientString(id = 2179, message = "You have the highest bid submitted in a Giran Castle auction.")
	public static SystemMessageId YOU_HAVE_THE_HIGHEST_BID_SUBMITTED_IN_A_GIRAN_CASTLE_AUCTION;
	
	@ClientString(id = 2180, message = "You have the highest bid submitted in an Aden Castle auction.")
	public static SystemMessageId YOU_HAVE_THE_HIGHEST_BID_SUBMITTED_IN_AN_ADEN_CASTLE_AUCTION;
	
	@ClientString(id = 2181, message = "You have highest the bid submitted in a Rune Castle auction.")
	public static SystemMessageId YOU_HAVE_HIGHEST_THE_BID_SUBMITTED_IN_A_RUNE_CASTLE_AUCTION;
	
	@ClientString(id = 2182, message = "You cannot polymorph while riding a boat.")
	public static SystemMessageId YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_BOAT;
	
	@ClientString(id = 2183, message = "The fortress battle of $s1 has finished.")
	public static SystemMessageId THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED;
	
	@ClientString(id = 2184, message = "$s1 is victorious in the fortress battle of $s2.")
	public static SystemMessageId S1_IS_VICTORIOUS_IN_THE_FORTRESS_BATTLE_OF_S2;
	
	@ClientString(id = 2185, message = "Only a party leader can make the request to enter.")
	public static SystemMessageId ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER;
	
	@ClientString(id = 2186, message = "Soul cannot be absorbed anymore.")
	public static SystemMessageId SOUL_CANNOT_BE_ABSORBED_ANYMORE;
	
	@ClientString(id = 2187, message = "The target is located where you cannot charge.")
	public static SystemMessageId THE_TARGET_IS_LOCATED_WHERE_YOU_CANNOT_CHARGE;
	
	@ClientString(id = 2188, message = "Another enchantment is in progress. Please complete the previous task, then try again")
	public static SystemMessageId ANOTHER_ENCHANTMENT_IS_IN_PROGRESS_PLEASE_COMPLETE_THE_PREVIOUS_TASK_THEN_TRY_AGAIN;
	
	@ClientString(id = 2189, message = "Current Location : $s1, $s2, $s3 (Near Kamael Village)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_KAMAEL_VILLAGE;
	
	@ClientString(id = 2190, message = "Current Location : $s1, $s2, $s3 (Near the south end of the Wastelands)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_SOUTH_END_OF_THE_WASTELANDS;
	
	@ClientString(id = 2191, message = "To apply selected options, the game needs to be reloaded. If you don't apply now, it will be applied when you start the game next time. Will you apply now?")
	public static SystemMessageId TO_APPLY_SELECTED_OPTIONS_THE_GAME_NEEDS_TO_BE_RELOADED_IF_YOU_DON_T_APPLY_NOW_IT_WILL_BE_APPLIED_WHEN_YOU_START_THE_GAME_NEXT_TIME_WILL_YOU_APPLY_NOW;
	
	@ClientString(id = 2192, message = "You have bid on an item auction.")
	public static SystemMessageId YOU_HAVE_BID_ON_AN_ITEM_AUCTION;
	
	@ClientString(id = 2193, message = "You are too far from the NPC for that to work.")
	public static SystemMessageId YOU_ARE_TOO_FAR_FROM_THE_NPC_FOR_THAT_TO_WORK;
	
	@ClientString(id = 2194, message = "Current polymorph form cannot be applied with corresponding effects.")
	public static SystemMessageId CURRENT_POLYMORPH_FORM_CANNOT_BE_APPLIED_WITH_CORRESPONDING_EFFECTS;
	
	@ClientString(id = 2195, message = "You do not have enough souls.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_SOULS;
	
	@ClientString(id = 2196, message = "No Owned Clan.")
	public static SystemMessageId NO_OWNED_CLAN;
	
	@ClientString(id = 2197, message = "Owned by clan $s1.")
	public static SystemMessageId OWNED_BY_CLAN_S1;
	
	@ClientString(id = 2198, message = "You currently have the highest bid in an item auction.")
	public static SystemMessageId YOU_CURRENTLY_HAVE_THE_HIGHEST_BID_IN_AN_ITEM_AUCTION;
	
	@ClientString(id = 2199, message = "You cannot enter this instance zone while the NPC server is down.")
	public static SystemMessageId YOU_CANNOT_ENTER_THIS_INSTANCE_ZONE_WHILE_THE_NPC_SERVER_IS_DOWN;
	
	@ClientString(id = 2200, message = "This instance zone will be terminated as the NPC server is down. You will be forcibly removed from the dungeon shortly.")
	public static SystemMessageId THIS_INSTANCE_ZONE_WILL_BE_TERMINATED_AS_THE_NPC_SERVER_IS_DOWN_YOU_WILL_BE_FORCIBLY_REMOVED_FROM_THE_DUNGEON_SHORTLY;
	
	@ClientString(id = 2201, message = "$s1 year(s) $s2 month(s) $s3 day(s)")
	public static SystemMessageId S1_YEAR_S_S2_MONTH_S_S3_DAY_S;
	
	@ClientString(id = 2202, message = "$s1 hour(s) $s2 minute(s) $s3 second(s)")
	public static SystemMessageId S1_HOUR_S_S2_MINUTE_S_S3_SECOND_S;
	
	@ClientString(id = 2203, message = "$s1/$s2")
	public static SystemMessageId S1_S2;
	
	@ClientString(id = 2204, message = "$s1 hour(s)")
	public static SystemMessageId S1_HOUR_S;
	
	@ClientString(id = 2205, message = "You have entered an area where the mini map cannot be used. Your mini map has been closed.")
	public static SystemMessageId YOU_HAVE_ENTERED_AN_AREA_WHERE_THE_MINI_MAP_CANNOT_BE_USED_YOUR_MINI_MAP_HAS_BEEN_CLOSED;
	
	@ClientString(id = 2206, message = "You have entered an area where the mini map can now be used.")
	public static SystemMessageId YOU_HAVE_ENTERED_AN_AREA_WHERE_THE_MINI_MAP_CAN_NOW_BE_USED;
	
	@ClientString(id = 2207, message = "This is an area where you cannot use the mini map. The mini map cannot be opened.")
	public static SystemMessageId THIS_IS_AN_AREA_WHERE_YOU_CANNOT_USE_THE_MINI_MAP_THE_MINI_MAP_CANNOT_BE_OPENED;
	
	@ClientString(id = 2208, message = "You do not meet the skill level requirements.")
	public static SystemMessageId YOU_DO_NOT_MEET_THE_SKILL_LEVEL_REQUIREMENTS;
	
	@ClientString(id = 2209, message = "This is an area where your radar cannot be used")
	public static SystemMessageId THIS_IS_AN_AREA_WHERE_YOUR_RADAR_CANNOT_BE_USED;
	
	@ClientString(id = 2210, message = "Your skill will be returned to an unenchanted state.")
	public static SystemMessageId YOUR_SKILL_WILL_BE_RETURNED_TO_AN_UNENCHANTED_STATE;
	
	@ClientString(id = 2211, message = "You must learn the Onyx Beast skill before you can acquire further skills.")
	public static SystemMessageId YOU_MUST_LEARN_THE_ONYX_BEAST_SKILL_BEFORE_YOU_CAN_ACQUIRE_FURTHER_SKILLS;
	
	@ClientString(id = 2212, message = "You have not completed the necessary quest for skill acquisition.")
	public static SystemMessageId YOU_HAVE_NOT_COMPLETED_THE_NECESSARY_QUEST_FOR_SKILL_ACQUISITION;
	
	@ClientString(id = 2213, message = "You cannot board a ship while you are polymorphed.")
	public static SystemMessageId YOU_CANNOT_BOARD_A_SHIP_WHILE_YOU_ARE_POLYMORPHED;
	
	@ClientString(id = 2214, message = "A new character will be created with the current settings. Continue?")
	public static SystemMessageId A_NEW_CHARACTER_WILL_BE_CREATED_WITH_THE_CURRENT_SETTINGS_CONTINUE;
	
	@ClientString(id = 2215, message = "$s1 P. Def.")
	public static SystemMessageId S1_P_DEF;
	
	@ClientString(id = 2216, message = "The CPU driver is not up-to-date. Please download the latest driver.")
	public static SystemMessageId THE_CPU_DRIVER_IS_NOT_UP_TO_DATE_PLEASE_DOWNLOAD_THE_LATEST_DRIVER;
	
	@ClientString(id = 2217, message = "The ballista has been successfully destroyed. The clan's reputation will be increased.")
	public static SystemMessageId THE_BALLISTA_HAS_BEEN_SUCCESSFULLY_DESTROYED_THE_CLAN_S_REPUTATION_WILL_BE_INCREASED;
	
	@ClientString(id = 2218, message = "This is a main class skill only.")
	public static SystemMessageId THIS_IS_A_MAIN_CLASS_SKILL_ONLY;
	
	@ClientString(id = 2219, message = "This squad skill has already been acquired.")
	public static SystemMessageId THIS_SQUAD_SKILL_HAS_ALREADY_BEEN_ACQUIRED;
	
	@ClientString(id = 2220, message = "The previous level skill has not been learned.")
	public static SystemMessageId THE_PREVIOUS_LEVEL_SKILL_HAS_NOT_BEEN_LEARNED;
	
	@ClientString(id = 2221, message = "Do you wish to activate the selected functions?")
	public static SystemMessageId DO_YOU_WISH_TO_ACTIVATE_THE_SELECTED_FUNCTIONS;
	
	@ClientString(id = 2222, message = "It will cost 150,000 adena to place scouts. Do you wish to continue?")
	public static SystemMessageId IT_WILL_COST_150_000_ADENA_TO_PLACE_SCOUTS_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 2223, message = "It will cost 200,000 adena for a fortress gate enhancement. Do you wish to continue?")
	public static SystemMessageId IT_WILL_COST_200_000_ADENA_FOR_A_FORTRESS_GATE_ENHANCEMENT_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 2224, message = "Your crossbow is preparing to fire.")
	public static SystemMessageId YOUR_CROSSBOW_IS_PREPARING_TO_FIRE;
	
	@ClientString(id = 2225, message = "There are no other skills to learn. Please come back after $s1nd class change.")
	public static SystemMessageId THERE_ARE_NO_OTHER_SKILLS_TO_LEARN_PLEASE_COME_BACK_AFTER_S1ND_CLASS_CHANGE;
	
	@ClientString(id = 2226, message = "Not enough bolts.")
	public static SystemMessageId NOT_ENOUGH_BOLTS;
	
	@ClientString(id = 2227, message = "It is not possible to register for the castle siege side or castle siege of a higher castle in the contract.")
	public static SystemMessageId IT_IS_NOT_POSSIBLE_TO_REGISTER_FOR_THE_CASTLE_SIEGE_SIDE_OR_CASTLE_SIEGE_OF_A_HIGHER_CASTLE_IN_THE_CONTRACT;
	
	@ClientString(id = 2228, message = "Instance zone time limit:")
	public static SystemMessageId INSTANCE_ZONE_TIME_LIMIT;
	
	@ClientString(id = 2229, message = "There is no instance zone under a time limit.")
	public static SystemMessageId THERE_IS_NO_INSTANCE_ZONE_UNDER_A_TIME_LIMIT;
	
	@ClientString(id = 2230, message = "$s1 will be available for re-use after $s2 hour(s) $s3 minute(s).")
	public static SystemMessageId S1_WILL_BE_AVAILABLE_FOR_RE_USE_AFTER_S2_HOUR_S_S3_MINUTE_S;
	
	@ClientString(id = 2231, message = "The supply items have not been provided because the castle you are in contract with doesn't have enough clan reputation.")
	public static SystemMessageId THE_SUPPLY_ITEMS_HAVE_NOT_BEEN_PROVIDED_BECAUSE_THE_CASTLE_YOU_ARE_IN_CONTRACT_WITH_DOESN_T_HAVE_ENOUGH_CLAN_REPUTATION;
	
	@ClientString(id = 2232, message = "$s1 will be crystallized before destruction. Will you continue?")
	public static SystemMessageId S1_WILL_BE_CRYSTALLIZED_BEFORE_DESTRUCTION_WILL_YOU_CONTINUE;
	
	@ClientString(id = 2233, message = "Siege registration is not possible due to your castle contract.")
	public static SystemMessageId SIEGE_REGISTRATION_IS_NOT_POSSIBLE_DUE_TO_YOUR_CASTLE_CONTRACT;
	
	@ClientString(id = 2234, message = "Do you wish to use this Kamael exclusive Hero Weapon?")
	public static SystemMessageId DO_YOU_WISH_TO_USE_THIS_KAMAEL_EXCLUSIVE_HERO_WEAPON;
	
	@ClientString(id = 2235, message = "The instance zone in use has been deleted and cannot be accessed.")
	public static SystemMessageId THE_INSTANCE_ZONE_IN_USE_HAS_BEEN_DELETED_AND_CANNOT_BE_ACCESSED;
	
	@ClientString(id = 2236, message = "You have $s1 minute(s) left on your wyvern.")
	public static SystemMessageId YOU_HAVE_S1_MINUTE_S_LEFT_ON_YOUR_WYVERN;
	
	@ClientString(id = 2237, message = "You have $s1 second(s) left on your wyvern.")
	public static SystemMessageId YOU_HAVE_S1_SECOND_S_LEFT_ON_YOUR_WYVERN;
	
	@ClientString(id = 2238, message = "You are participating in the siege of $s1. This siege is scheduled for 2 hours.")
	public static SystemMessageId YOU_ARE_PARTICIPATING_IN_THE_SIEGE_OF_S1_THIS_SIEGE_IS_SCHEDULED_FOR_2_HOURS;
	
	@ClientString(id = 2239, message = "The siege of $s1, in which you are participating, has finished.")
	public static SystemMessageId THE_SIEGE_OF_S1_IN_WHICH_YOU_ARE_PARTICIPATING_HAS_FINISHED;
	
	@ClientString(id = 2240, message = "You cannot register for a Clan Hall War when the Clan Lord is in the process of re-delegating clan authority to another leader.")
	public static SystemMessageId YOU_CANNOT_REGISTER_FOR_A_CLAN_HALL_WAR_WHEN_THE_CLAN_LORD_IS_IN_THE_PROCESS_OF_RE_DELEGATING_CLAN_AUTHORITY_TO_ANOTHER_LEADER;
	
	@ClientString(id = 2241, message = "You cannot apply for a Clan Lord delegation request if your clan has registered for a Clan Hall War.")
	public static SystemMessageId YOU_CANNOT_APPLY_FOR_A_CLAN_LORD_DELEGATION_REQUEST_IF_YOUR_CLAN_HAS_REGISTERED_FOR_A_CLAN_HALL_WAR;
	
	@ClientString(id = 2242, message = "Clan members cannot leave or be expelled when they are registered for a Clan Hall War.")
	public static SystemMessageId CLAN_MEMBERS_CANNOT_LEAVE_OR_BE_EXPELLED_WHEN_THEY_ARE_REGISTERED_FOR_A_CLAN_HALL_WAR;
	
	@ClientString(id = 2243, message = "During the Bandit Stronghold or Wild Beast Reserve clan hall war, the previous clan lord rather than the new clan lord participates in battle.")
	public static SystemMessageId DURING_THE_BANDIT_STRONGHOLD_OR_WILD_BEAST_RESERVE_CLAN_HALL_WAR_THE_PREVIOUS_CLAN_LORD_RATHER_THAN_THE_NEW_CLAN_LORD_PARTICIPATES_IN_BATTLE;
	
	@ClientString(id = 2244, message = "$s1 minute(s) remaining.")
	public static SystemMessageId S1_MINUTE_S_REMAINING;
	
	@ClientString(id = 2245, message = "$s1 second(s) remaining.")
	public static SystemMessageId S1_SECOND_S_REMAINING;
	
	@ClientString(id = 2246, message = "The contest will begin in $s1 minute(s).")
	public static SystemMessageId THE_CONTEST_WILL_BEGIN_IN_S1_MINUTE_S;
	
	@ClientString(id = 2247, message = "You cannot board an airship while transformed.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_TRANSFORMED;
	
	@ClientString(id = 2248, message = "You cannot board an airship while petrified.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_PETRIFIED;
	
	@ClientString(id = 2249, message = "You cannot board an airship while dead.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_DEAD;
	
	@ClientString(id = 2250, message = "You cannot board an airship while fishing.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_FISHING;
	
	@ClientString(id = 2251, message = "You cannot board an airship while in battle.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_BATTLE;
	
	@ClientString(id = 2252, message = "You cannot board an airship while in a duel.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_A_DUEL;
	
	@ClientString(id = 2253, message = "You cannot board an airship while sitting.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_SITTING;
	
	@ClientString(id = 2254, message = "You cannot board an airship while casting.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_CASTING;
	
	@ClientString(id = 2255, message = "You cannot board an airship when a cursed weapon is equipped.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHEN_A_CURSED_WEAPON_IS_EQUIPPED;
	
	@ClientString(id = 2256, message = "You cannot board an airship while holding a flag.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_HOLDING_A_FLAG;
	
	@ClientString(id = 2257, message = "You cannot board an airship while a pet or a servitor is summoned.")
	public static SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_PET_OR_A_SERVITOR_IS_SUMMONED;
	
	@ClientString(id = 2258, message = "You have already boarded another airship.")
	public static SystemMessageId YOU_HAVE_ALREADY_BOARDED_ANOTHER_AIRSHIP;
	
	@ClientString(id = 2259, message = "Current Location: $s1, $s2, $s3 (near Fantasy Isle)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_FANTASY_ISLE;
	
	@ClientString(id = 2260, message = "Your pet's hunger gauge is below 10%. If your pet isn't fed soon, it may run away.")
	public static SystemMessageId YOUR_PET_S_HUNGER_GAUGE_IS_BELOW_10_IF_YOUR_PET_ISN_T_FED_SOON_IT_MAY_RUN_AWAY;
	
	@ClientString(id = 2261, message = "$c1 has done $s3 points of damage to $c2.")
	public static SystemMessageId C1_HAS_DONE_S3_POINTS_OF_DAMAGE_TO_C2;
	
	@ClientString(id = 2262, message = "$c1 has received $s3 damage from $c2.")
	public static SystemMessageId C1_HAS_RECEIVED_S3_DAMAGE_FROM_C2;
	
	@ClientString(id = 2263, message = "$c1 has received damage of $s3 through $c2.")
	public static SystemMessageId C1_HAS_RECEIVED_DAMAGE_OF_S3_THROUGH_C2;
	
	@ClientString(id = 2264, message = "$c1 has evaded $c2's attack.")
	public static SystemMessageId C1_HAS_EVADED_C2_S_ATTACK;
	
	@ClientString(id = 2265, message = "$c1's attack went astray.")
	public static SystemMessageId C1_S_ATTACK_WENT_ASTRAY;
	
	@ClientString(id = 2266, message = "$c1 landed a critical hit!")
	public static SystemMessageId C1_LANDED_A_CRITICAL_HIT;
	
	@ClientString(id = 2267, message = "$c1 resisted $c2's drain.")
	public static SystemMessageId C1_RESISTED_C2_S_DRAIN;
	
	@ClientString(id = 2268, message = "$c1's attack failed.")
	public static SystemMessageId C1_S_ATTACK_FAILED;
	
	@ClientString(id = 2269, message = "$c1 resisted $c2's magic.")
	public static SystemMessageId C1_RESISTED_C2_S_MAGIC;
	
	@ClientString(id = 2270, message = "$c1 has received damage from $s2 through the fire of magic.")
	public static SystemMessageId C1_HAS_RECEIVED_DAMAGE_FROM_S2_THROUGH_THE_FIRE_OF_MAGIC;
	
	@ClientString(id = 2271, message = "$c1 weakly resisted $c2's magic.")
	public static SystemMessageId C1_WEAKLY_RESISTED_C2_S_MAGIC;
	
	@ClientString(id = 2272, message = "The key you assigned as a shortcut key is not available in regular chatting mode.")
	public static SystemMessageId THE_KEY_YOU_ASSIGNED_AS_A_SHORTCUT_KEY_IS_NOT_AVAILABLE_IN_REGULAR_CHATTING_MODE;
	
	@ClientString(id = 2273, message = "This skill cannot be learned while in the sub-class state. Please try again after changing to the main class.")
	public static SystemMessageId THIS_SKILL_CANNOT_BE_LEARNED_WHILE_IN_THE_SUB_CLASS_STATE_PLEASE_TRY_AGAIN_AFTER_CHANGING_TO_THE_MAIN_CLASS;
	
	@ClientString(id = 2274, message = "You entered an area where you cannot throw away items.")
	public static SystemMessageId YOU_ENTERED_AN_AREA_WHERE_YOU_CANNOT_THROW_AWAY_ITEMS;
	
	@ClientString(id = 2275, message = "You are in an area where you cannot cancel pet summoning.")
	public static SystemMessageId YOU_ARE_IN_AN_AREA_WHERE_YOU_CANNOT_CANCEL_PET_SUMMONING;
	
	@ClientString(id = 2276, message = "The rebel army recaptured the fortress.")
	public static SystemMessageId THE_REBEL_ARMY_RECAPTURED_THE_FORTRESS;
	
	@ClientString(id = 2277, message = "Party of $s1")
	public static SystemMessageId PARTY_OF_S1;
	
	@ClientString(id = 2278, message = "Remaining Time $s1:$s2")
	public static SystemMessageId REMAINING_TIME_S1_S2;
	
	@ClientString(id = 2279, message = "You can no longer add a quest to the Quest Alerts.")
	public static SystemMessageId YOU_CAN_NO_LONGER_ADD_A_QUEST_TO_THE_QUEST_ALERTS;
	
	@ClientString(id = 2280, message = "Damage is decreased because $c1 resisted $c2's magic.")
	public static SystemMessageId DAMAGE_IS_DECREASED_BECAUSE_C1_RESISTED_C2_S_MAGIC;
	
	@ClientString(id = 2281, message = "$c1 hit you for $s3 damage and hit your servitor for $s4.")
	public static SystemMessageId C1_HIT_YOU_FOR_S3_DAMAGE_AND_HIT_YOUR_SERVITOR_FOR_S4;
	
	@ClientString(id = 2282, message = "Leave Fantasy Isle.")
	public static SystemMessageId LEAVE_FANTASY_ISLE;
	
	@ClientString(id = 2283, message = "You cannot transform while sitting.")
	public static SystemMessageId YOU_CANNOT_TRANSFORM_WHILE_SITTING;
	
	@ClientString(id = 2284, message = "You have obtained all the points you can get today in a place other than Internet Café.")
	public static SystemMessageId YOU_HAVE_OBTAINED_ALL_THE_POINTS_YOU_CAN_GET_TODAY_IN_A_PLACE_OTHER_THAN_INTERNET_CAF;
	
	@ClientString(id = 2285, message = "This skill cannot remove this trap.")
	public static SystemMessageId THIS_SKILL_CANNOT_REMOVE_THIS_TRAP;
	
	@ClientString(id = 2286, message = "You cannot wear $s1 because you are not wearing a bracelet.")
	public static SystemMessageId YOU_CANNOT_WEAR_S1_BECAUSE_YOU_ARE_NOT_WEARING_A_BRACELET;
	
	@ClientString(id = 2287, message = "You cannot equip $s1 because you do not have any available slots.")
	public static SystemMessageId YOU_CANNOT_EQUIP_S1_BECAUSE_YOU_DO_NOT_HAVE_ANY_AVAILABLE_SLOTS;
	
	@ClientString(id = 2288, message = "Resurrection will occur in $s1 seconds.")
	public static SystemMessageId RESURRECTION_WILL_OCCUR_IN_S1_SECONDS;
	
	@ClientString(id = 2289, message = "The match between the parties cannot commence because one of the party members is being teleported.")
	public static SystemMessageId THE_MATCH_BETWEEN_THE_PARTIES_CANNOT_COMMENCE_BECAUSE_ONE_OF_THE_PARTY_MEMBERS_IS_BEING_TELEPORTED;
	
	@ClientString(id = 2290, message = "You cannot assign shortcut keys before you log in.")
	public static SystemMessageId YOU_CANNOT_ASSIGN_SHORTCUT_KEYS_BEFORE_YOU_LOG_IN;
	
	@ClientString(id = 2291, message = "You must be in a party in order to operate the machine.")
	public static SystemMessageId YOU_MUST_BE_IN_A_PARTY_IN_ORDER_TO_OPERATE_THE_MACHINE;
	
	@ClientString(id = 2292, message = "Agathion skills can be used only when your Agathion is summoned.")
	public static SystemMessageId AGATHION_SKILLS_CAN_BE_USED_ONLY_WHEN_YOUR_AGATHION_IS_SUMMONED;
	
	@ClientString(id = 2293, message = "Current location: $s1, $s2, $s3 (inside the Steel Citadel)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_INSIDE_THE_STEEL_CITADEL;
	
	@ClientString(id = 2294, message = "The width of the crest or insignia does not meet the standard requirements.")
	public static SystemMessageId THE_WIDTH_OF_THE_CREST_OR_INSIGNIA_DOES_NOT_MEET_THE_STANDARD_REQUIREMENTS;
	
	@ClientString(id = 2295, message = "The length of the crest or insignia does not meet the standard requirements.")
	public static SystemMessageId THE_LENGTH_OF_THE_CREST_OR_INSIGNIA_DOES_NOT_MEET_THE_STANDARD_REQUIREMENTS;
	
	@ClientString(id = 2296, message = "You have gained Vitality points.")
	public static SystemMessageId YOU_HAVE_GAINED_VITALITY_POINTS;
	
	@ClientString(id = 2297, message = "Round $s1")
	public static SystemMessageId ROUND_S1;
	
	@ClientString(id = 2298, message = "The color of the crest or insignia that you want to register does not meet the standard requirements.")
	public static SystemMessageId THE_COLOR_OF_THE_CREST_OR_INSIGNIA_THAT_YOU_WANT_TO_REGISTER_DOES_NOT_MEET_THE_STANDARD_REQUIREMENTS;
	
	@ClientString(id = 2299, message = "The file format of the crest or insignia that you want to register does not meet the standard requirements.")
	public static SystemMessageId THE_FILE_FORMAT_OF_THE_CREST_OR_INSIGNIA_THAT_YOU_WANT_TO_REGISTER_DOES_NOT_MEET_THE_STANDARD_REQUIREMENTS;
	
	@ClientString(id = 2300, message = "Failed to load keyboard security module. For effective gaming functionality, when the game is over, please check all the files in the Lineage II automatic update.")
	public static SystemMessageId FAILED_TO_LOAD_KEYBOARD_SECURITY_MODULE_FOR_EFFECTIVE_GAMING_FUNCTIONALITY_WHEN_THE_GAME_IS_OVER_PLEASE_CHECK_ALL_THE_FILES_IN_THE_LINEAGE_II_AUTOMATIC_UPDATE;
	
	@ClientString(id = 2301, message = "Current location: Steel Citadel")
	public static SystemMessageId CURRENT_LOCATION_STEEL_CITADEL;
	
	@ClientString(id = 2302, message = "Your Dimensional Item has arrived! Visit the Dimensional Merchant in any village to obtain it.")
	public static SystemMessageId YOUR_DIMENSIONAL_ITEM_HAS_ARRIVED_VISIT_THE_DIMENSIONAL_MERCHANT_IN_ANY_VILLAGE_TO_OBTAIN_IT;
	
	@ClientString(id = 2303, message = "There are $s2 second(s) remaining in $s1's re-use time.")
	public static SystemMessageId THERE_ARE_S2_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME;
	
	@ClientString(id = 2304, message = "There are $s2 minute(s), $s3 second(s) remaining in $s1's re-use time.")
	public static SystemMessageId THERE_ARE_S2_MINUTE_S_S3_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME;
	
	@ClientString(id = 2305, message = "There are $s2 hour(s), $s3 minute(s), and $s4 second(s) remaining in $s1's re-use time.")
	public static SystemMessageId THERE_ARE_S2_HOUR_S_S3_MINUTE_S_AND_S4_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME;
	
	@ClientString(id = 2306, message = "Your Charm of Courage is trying to resurrect you. Would you like to resurrect now?")
	public static SystemMessageId YOUR_CHARM_OF_COURAGE_IS_TRYING_TO_RESURRECT_YOU_WOULD_YOU_LIKE_TO_RESURRECT_NOW;
	
	@ClientString(id = 2307, message = "The target is using a Charm of Courage.")
	public static SystemMessageId THE_TARGET_IS_USING_A_CHARM_OF_COURAGE;
	
	@ClientString(id = 2308, message = "Remaining time: %s1 day(s)")
	public static SystemMessageId REMAINING_TIME_S1_DAY_S;
	
	@ClientString(id = 2309, message = "Remaining time: %s1 hour(s)")
	public static SystemMessageId REMAINING_TIME_S1_HOUR_S;
	
	@ClientString(id = 2310, message = "Remaining time: %s1 minute(s)")
	public static SystemMessageId REMAINING_TIME_S1_MINUTE_S;
	
	@ClientString(id = 2311, message = "You do not have a servitor.")
	public static SystemMessageId YOU_DO_NOT_HAVE_A_SERVITOR;
	
	@ClientString(id = 2312, message = "You do not have a pet.")
	public static SystemMessageId YOU_DO_NOT_HAVE_A_PET;
	
	@ClientString(id = 2313, message = "The Dimensional Item has arrived.")
	public static SystemMessageId THE_DIMENSIONAL_ITEM_HAS_ARRIVED;
	
	@ClientString(id = 2314, message = "Your Vitality is at maximum.")
	public static SystemMessageId YOUR_VITALITY_IS_AT_MAXIMUM;
	
	@ClientString(id = 2315, message = "Your Vitality has increased.")
	public static SystemMessageId YOUR_VITALITY_HAS_INCREASED;
	
	@ClientString(id = 2316, message = "Your Vitality has decreased.")
	public static SystemMessageId YOUR_VITALITY_HAS_DECREASED;
	
	@ClientString(id = 2317, message = "Your Vitality is fully exhausted.")
	public static SystemMessageId YOUR_VITALITY_IS_FULLY_EXHAUSTED;
	
	@ClientString(id = 2318, message = "Only an enhanced skill can be cancelled.")
	public static SystemMessageId ONLY_AN_ENHANCED_SKILL_CAN_BE_CANCELLED;
	
	@ClientString(id = 2319, message = "You have acquired $s1 reputation.")
	public static SystemMessageId YOU_HAVE_ACQUIRED_S1_REPUTATION;
	
	@ClientString(id = 2320, message = "Masterwork creation possible")
	public static SystemMessageId MASTERWORK_CREATION_POSSIBLE;
	
	@ClientString(id = 2321, message = "Current location: Inside Kamaloka")
	public static SystemMessageId CURRENT_LOCATION_INSIDE_KAMALOKA;
	
	@ClientString(id = 2322, message = "Current location: Inside Nia Kamaloka")
	public static SystemMessageId CURRENT_LOCATION_INSIDE_NIA_KAMALOKA;
	
	@ClientString(id = 2323, message = "Current location: Inside Rim Kamaloka")
	public static SystemMessageId CURRENT_LOCATION_INSIDE_RIM_KAMALOKA;
	
	@ClientString(id = 2324, message = "$c1, you cannot enter because you have insufficient PC cafe points.")
	public static SystemMessageId C1_YOU_CANNOT_ENTER_BECAUSE_YOU_HAVE_INSUFFICIENT_PC_CAFE_POINTS;
	
	@ClientString(id = 2325, message = "Another teleport is taking place. Please try again once the teleport in process ends.")
	public static SystemMessageId ANOTHER_TELEPORT_IS_TAKING_PLACE_PLEASE_TRY_AGAIN_ONCE_THE_TELEPORT_IN_PROCESS_ENDS;
	
	@ClientString(id = 2326, message = "You have acquired 50 Clan Fame Points.")
	public static SystemMessageId YOU_HAVE_ACQUIRED_50_CLAN_FAME_POINTS;
	
	@ClientString(id = 2327, message = "You don't have enough reputation to do that.")
	public static SystemMessageId YOU_DON_T_HAVE_ENOUGH_REPUTATION_TO_DO_THAT;
	
	@ClientString(id = 2328, message = "Only clans who are level 4 or above can register for battle at Devastated Castle and Fortress of the Dead.")
	public static SystemMessageId ONLY_CLANS_WHO_ARE_LEVEL_4_OR_ABOVE_CAN_REGISTER_FOR_BATTLE_AT_DEVASTATED_CASTLE_AND_FORTRESS_OF_THE_DEAD;
	
	@ClientString(id = 2329, message = "Vitality Level $s1 $s2")
	public static SystemMessageId VITALITY_LEVEL_S1_S2;
	
	@ClientString(id = 2330, message = ": Experience points boosted by $s1.")
	public static SystemMessageId EXPERIENCE_POINTS_BOOSTED_BY_S1;
	
	@ClientString(id = 2331, message = "<Rare> $s1")
	public static SystemMessageId RARE_S1;
	
	@ClientString(id = 2332, message = "<Supply> $s1")
	public static SystemMessageId SUPPLY_S1;
	
	@ClientString(id = 2333, message = "You cannot receive the Dimensional Item because you have exceed your inventory weight/quantity limit.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_THE_DIMENSIONAL_ITEM_BECAUSE_YOU_HAVE_EXCEED_YOUR_INVENTORY_WEIGHT_QUANTITY_LIMIT;
	
	@ClientString(id = 2334, message = "Score that shows a player's individual fame. Fame can be obtained by participating in a territory war, castle siege, fortress siege, hideout siege, the Underground Coliseum, the Festival of Darkness and the Olympiad.")
	public static SystemMessageId SCORE_THAT_SHOWS_A_PLAYER_S_INDIVIDUAL_FAME_FAME_CAN_BE_OBTAINED_BY_PARTICIPATING_IN_A_TERRITORY_WAR_CASTLE_SIEGE_FORTRESS_SIEGE_HIDEOUT_SIEGE_THE_UNDERGROUND_COLISEUM_THE_FESTIVAL_OF_DARKNESS_AND_THE_OLYMPIAD;
	
	@ClientString(id = 2335, message = "There are no more Dimensional Items to be found.")
	public static SystemMessageId THERE_ARE_NO_MORE_DIMENSIONAL_ITEMS_TO_BE_FOUND;
	
	@ClientString(id = 2336, message = "Half-Kill!")
	public static SystemMessageId HALF_KILL;
	
	@ClientString(id = 2337, message = "Your CP was drained because you were hit with a Half-Kill skill.")
	public static SystemMessageId YOUR_CP_WAS_DRAINED_BECAUSE_YOU_WERE_HIT_WITH_A_HALF_KILL_SKILL;
	
	@ClientString(id = 2338, message = "If it's a draw, the player who first entered is first")
	public static SystemMessageId IF_IT_S_A_DRAW_THE_PLAYER_WHO_FIRST_ENTERED_IS_FIRST;
	
	@ClientString(id = 2339, message = "Please place the item to be enchanted in the empty slot below.")
	public static SystemMessageId PLEASE_PLACE_THE_ITEM_TO_BE_ENCHANTED_IN_THE_EMPTY_SLOT_BELOW;
	
	@ClientString(id = 2340, message = "Please place the item for rate increase.")
	public static SystemMessageId PLEASE_PLACE_THE_ITEM_FOR_RATE_INCREASE;
	
	@ClientString(id = 2341, message = "The enchant will begin once you press the Start button below.")
	public static SystemMessageId THE_ENCHANT_WILL_BEGIN_ONCE_YOU_PRESS_THE_START_BUTTON_BELOW;
	
	@ClientString(id = 2342, message = "Success! The item is now a $s1.")
	public static SystemMessageId SUCCESS_THE_ITEM_IS_NOW_A_S1;
	
	@ClientString(id = 2343, message = "Failed. You have obtained $s2 of $s1.")
	public static SystemMessageId FAILED_YOU_HAVE_OBTAINED_S2_OF_S1;
	
	@ClientString(id = 2344, message = "You have been killed by an attack from $c1.")
	public static SystemMessageId YOU_HAVE_BEEN_KILLED_BY_AN_ATTACK_FROM_C1;
	
	@ClientString(id = 2345, message = "You have attacked and killed $c1.")
	public static SystemMessageId YOU_HAVE_ATTACKED_AND_KILLED_C1;
	
	@ClientString(id = 2346, message = "Your account may have been involved in identity theft. As such, it has been temporarily restricted. If this does not apply to you, you may obtain normal service by going through self-identification on the homepage. Please refer to the plaync homepage (www.plaync.co.kr) customer center (Lineage 2) clause 1:1 for more details.")
	public static SystemMessageId YOUR_ACCOUNT_MAY_HAVE_BEEN_INVOLVED_IN_IDENTITY_THEFT_AS_SUCH_IT_HAS_BEEN_TEMPORARILY_RESTRICTED_IF_THIS_DOES_NOT_APPLY_TO_YOU_YOU_MAY_OBTAIN_NORMAL_SERVICE_BY_GOING_THROUGH_SELF_IDENTIFICATION_ON_THE_HOMEPAGE_PLEASE_REFER_TO_THE_PLAYNC_HOMEPAGE_WWW_PLAYNC_CO_KR_CUSTOMER_CENTER_LINEAGE_2_CLAUSE_1_1_FOR_MORE_DETAILS;
	
	@ClientString(id = 2347, message = "$s1 seconds to game end!")
	public static SystemMessageId S1_SECONDS_TO_GAME_END;
	
	@ClientString(id = 2348, message = "You cannot use My Teleports during a battle.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_BATTLE;
	
	@ClientString(id = 2349, message = "You cannot use My Teleports while participating a large-scale battle such as a castle siege, fortress siege, or hideout siege.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_A_LARGE_SCALE_BATTLE_SUCH_AS_A_CASTLE_SIEGE_FORTRESS_SIEGE_OR_HIDEOUT_SIEGE;
	
	@ClientString(id = 2350, message = "You cannot use My Teleports during a duel.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_DUEL;
	
	@ClientString(id = 2351, message = "You cannot use My Teleports while flying.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_FLYING;
	
	@ClientString(id = 2352, message = "You cannot use My Teleports while participating in an Olympiad match.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_IN_AN_OLYMPIAD_MATCH;
	
	@ClientString(id = 2353, message = "You cannot use My Teleports while you are in a petrified or paralyzed state.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_IN_A_PETRIFIED_OR_PARALYZED_STATE;
	
	@ClientString(id = 2354, message = "You cannot use My Teleports while you are dead.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_DEAD;
	
	@ClientString(id = 2355, message = "You cannot use My Teleports in this area.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_IN_THIS_AREA;
	
	@ClientString(id = 2356, message = "You cannot use My Teleports underwater.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_UNDERWATER;
	
	@ClientString(id = 2357, message = "You cannot use My Teleports in an instant zone.")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_IN_AN_INSTANT_ZONE;
	
	@ClientString(id = 2358, message = "You have no space to save the teleport location.")
	public static SystemMessageId YOU_HAVE_NO_SPACE_TO_SAVE_THE_TELEPORT_LOCATION;
	
	@ClientString(id = 2359, message = "You cannot teleport because you do not have a teleport item.")
	public static SystemMessageId YOU_CANNOT_TELEPORT_BECAUSE_YOU_DO_NOT_HAVE_A_TELEPORT_ITEM;
	
	@ClientString(id = 2360, message = "My Teleports Spellbk: $s1")
	public static SystemMessageId MY_TELEPORTS_SPELLBK_S1;
	
	@ClientString(id = 2361, message = "Current location: $s1")
	public static SystemMessageId CURRENT_LOCATION_S1;
	
	@ClientString(id = 2362, message = "The saved teleport location will be deleted. Do you wish to continue?")
	public static SystemMessageId THE_SAVED_TELEPORT_LOCATION_WILL_BE_DELETED_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 2363, message = "Your account has been confirmed as using another person's name. All game services have been limited. Please inquire about additional details through the PlayNC (www.plaync.co.kr) customer center.")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_CONFIRMED_AS_USING_ANOTHER_PERSON_S_NAME_ALL_GAME_SERVICES_HAVE_BEEN_LIMITED_PLEASE_INQUIRE_ABOUT_ADDITIONAL_DETAILS_THROUGH_THE_PLAYNC_WWW_PLAYNC_CO_KR_CUSTOMER_CENTER;
	
	@ClientString(id = 2364, message = "The item has expired after its $s1 period.")
	public static SystemMessageId THE_ITEM_HAS_EXPIRED_AFTER_ITS_S1_PERIOD;
	
	@ClientString(id = 2365, message = "The designated item has expired after its $s1 period.")
	public static SystemMessageId THE_DESIGNATED_ITEM_HAS_EXPIRED_AFTER_ITS_S1_PERIOD;
	
	@ClientString(id = 2366, message = "The limited-time item has disappeared because the remaining time ran out.")
	public static SystemMessageId THE_LIMITED_TIME_ITEM_HAS_DISAPPEARED_BECAUSE_THE_REMAINING_TIME_RAN_OUT;
	
	@ClientString(id = 2367, message = "$s1's blessing has recovered HP by $s2.")
	public static SystemMessageId S1_S_BLESSING_HAS_RECOVERED_HP_BY_S2;
	
	@ClientString(id = 2368, message = "$s1's blessing has recovered MP by $s2.")
	public static SystemMessageId S1_S_BLESSING_HAS_RECOVERED_MP_BY_S2;
	
	@ClientString(id = 2369, message = "$s1's blessing has fully recovered HP and MP.")
	public static SystemMessageId S1_S_BLESSING_HAS_FULLY_RECOVERED_HP_AND_MP;
	
	@ClientString(id = 2370, message = "Resurrection will take place in the waiting room after $s1 seconds.")
	public static SystemMessageId RESURRECTION_WILL_TAKE_PLACE_IN_THE_WAITING_ROOM_AFTER_S1_SECONDS;
	
	@ClientString(id = 2371, message = "$c1 was reported as a BOT.")
	public static SystemMessageId C1_WAS_REPORTED_AS_A_BOT;
	
	@ClientString(id = 2372, message = "There is not much time remaining until the hunting helper pet leaves.")
	public static SystemMessageId THERE_IS_NOT_MUCH_TIME_REMAINING_UNTIL_THE_HUNTING_HELPER_PET_LEAVES;
	
	@ClientString(id = 2373, message = "The hunting helper pet is now leaving.")
	public static SystemMessageId THE_HUNTING_HELPER_PET_IS_NOW_LEAVING;
	
	@ClientString(id = 2374, message = "End match!")
	public static SystemMessageId END_MATCH;
	
	@ClientString(id = 2375, message = "The hunting helper pet cannot be returned because there is not much time remaining until it leaves.")
	public static SystemMessageId THE_HUNTING_HELPER_PET_CANNOT_BE_RETURNED_BECAUSE_THERE_IS_NOT_MUCH_TIME_REMAINING_UNTIL_IT_LEAVES;
	
	@ClientString(id = 2376, message = "You cannot receive a Dimensional Item during an exchange.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_A_DIMENSIONAL_ITEM_DURING_AN_EXCHANGE;
	
	@ClientString(id = 2377, message = "You cannot report a character who is in a peace zone or a battlefield.")
	public static SystemMessageId YOU_CANNOT_REPORT_A_CHARACTER_WHO_IS_IN_A_PEACE_ZONE_OR_A_BATTLEFIELD;
	
	@ClientString(id = 2378, message = "You cannot report when a clan war has been declared.")
	public static SystemMessageId YOU_CANNOT_REPORT_WHEN_A_CLAN_WAR_HAS_BEEN_DECLARED;
	
	@ClientString(id = 2379, message = "You cannot report a character who has not acquired any Exp. after connecting.")
	public static SystemMessageId YOU_CANNOT_REPORT_A_CHARACTER_WHO_HAS_NOT_ACQUIRED_ANY_EXP_AFTER_CONNECTING;
	
	@ClientString(id = 2380, message = "You cannot report this person again at this time.")
	public static SystemMessageId YOU_CANNOT_REPORT_THIS_PERSON_AGAIN_AT_THIS_TIME;
	
	@ClientString(id = 2381, message = "You cannot report this person again at this time.")
	public static SystemMessageId YOU_CANNOT_REPORT_THIS_PERSON_AGAIN_AT_THIS_TIME_2;
	
	@ClientString(id = 2382, message = "You cannot report this person again at this time.")
	public static SystemMessageId YOU_CANNOT_REPORT_THIS_PERSON_AGAIN_AT_THIS_TIME_3;
	
	@ClientString(id = 2383, message = "You cannot report this person again at this time.")
	public static SystemMessageId YOU_CANNOT_REPORT_THIS_PERSON_AGAIN_AT_THIS_TIME_4;
	
	@ClientString(id = 2384, message = "This item does not meet the requirements for the enhancement spellbook.")
	public static SystemMessageId THIS_ITEM_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_THE_ENHANCEMENT_SPELLBOOK;
	
	@ClientString(id = 2385, message = "This is an incorrect support enhancement spellbook.")
	public static SystemMessageId THIS_IS_AN_INCORRECT_SUPPORT_ENHANCEMENT_SPELLBOOK;
	
	@ClientString(id = 2386, message = "This item does not meet the requirements for the support enhancement spellbook.")
	public static SystemMessageId THIS_ITEM_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_THE_SUPPORT_ENHANCEMENT_SPELLBOOK;
	
	@ClientString(id = 2387, message = "Registration of the support enhancement spellbook has failed.")
	public static SystemMessageId REGISTRATION_OF_THE_SUPPORT_ENHANCEMENT_SPELLBOOK_HAS_FAILED;
	
	@ClientString(id = 2388, message = "A party cannot be formed in this area.")
	public static SystemMessageId A_PARTY_CANNOT_BE_FORMED_IN_THIS_AREA;
	
	@ClientString(id = 2389, message = "The maximum accumulation allowed of PC cafe points has been exceeded. You can no longer acquire PC cafe points.")
	public static SystemMessageId THE_MAXIMUM_ACCUMULATION_ALLOWED_OF_PC_CAFE_POINTS_HAS_BEEN_EXCEEDED_YOU_CAN_NO_LONGER_ACQUIRE_PC_CAFE_POINTS;
	
	@ClientString(id = 2390, message = "Your number of My Teleports slots has reached its maximum limit.")
	public static SystemMessageId YOUR_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_REACHED_ITS_MAXIMUM_LIMIT;
	
	@ClientString(id = 2391, message = "You have used the Feather of Blessing to resurrect.")
	public static SystemMessageId YOU_HAVE_USED_THE_FEATHER_OF_BLESSING_TO_RESURRECT;
	
	@ClientString(id = 2392, message = "The Dimensional Item cannot be located because of a temporary connection error.")
	public static SystemMessageId THE_DIMENSIONAL_ITEM_CANNOT_BE_LOCATED_BECAUSE_OF_A_TEMPORARY_CONNECTION_ERROR;
	
	@ClientString(id = 2393, message = "You have acquired $s1 PC Cafe points.")
	public static SystemMessageId YOU_HAVE_ACQUIRED_S1_PC_CAFE_POINTS;
	
	@ClientString(id = 2394, message = "That skill cannot be used because your pet/servitor lacks sufficient MP.")
	public static SystemMessageId THAT_SKILL_CANNOT_BE_USED_BECAUSE_YOUR_PET_SERVITOR_LACKS_SUFFICIENT_MP;
	
	@ClientString(id = 2395, message = "That skill cannot be used because your pet/servitor lacks sufficient HP.")
	public static SystemMessageId THAT_SKILL_CANNOT_BE_USED_BECAUSE_YOUR_PET_SERVITOR_LACKS_SUFFICIENT_HP;
	
	@ClientString(id = 2396, message = "That pet/servitor skill cannot be used because it is recharging.")
	public static SystemMessageId THAT_PET_SERVITOR_SKILL_CANNOT_BE_USED_BECAUSE_IT_IS_RECHARGING;
	
	@ClientString(id = 2397, message = "Please use a My Teleport Scroll.")
	public static SystemMessageId PLEASE_USE_A_MY_TELEPORT_SCROLL;
	
	@ClientString(id = 2398, message = "You have no open My Teleports slots.")
	public static SystemMessageId YOU_HAVE_NO_OPEN_MY_TELEPORTS_SLOTS;
	
	@ClientString(id = 2399, message = "$s1's ownership expires in $s2 minutes.")
	public static SystemMessageId S1_S_OWNERSHIP_EXPIRES_IN_S2_MINUTES;
	
	@ClientString(id = 2400, message = "Instant Zone currently in use: $s1")
	public static SystemMessageId INSTANT_ZONE_CURRENTLY_IN_USE_S1;
	
	@ClientString(id = 2401, message = "Clan lord $c2, who leads clan $s1, has been declared the lord of the $s3 territory.")
	public static SystemMessageId CLAN_LORD_C2_WHO_LEADS_CLAN_S1_HAS_BEEN_DECLARED_THE_LORD_OF_THE_S3_TERRITORY;
	
	@ClientString(id = 2402, message = "The Territory War request period has ended.")
	public static SystemMessageId THE_TERRITORY_WAR_REQUEST_PERIOD_HAS_ENDED;
	
	@ClientString(id = 2403, message = "The Territory War begins in 10 minutes!")
	public static SystemMessageId THE_TERRITORY_WAR_BEGINS_IN_10_MINUTES;
	
	@ClientString(id = 2404, message = "The Territory War begins in 5 minutes!")
	public static SystemMessageId THE_TERRITORY_WAR_BEGINS_IN_5_MINUTES;
	
	@ClientString(id = 2405, message = "The Territory War begins in 1 minute!")
	public static SystemMessageId THE_TERRITORY_WAR_BEGINS_IN_1_MINUTE;
	
	@ClientString(id = 2406, message = "$s1's territory war has begun.")
	public static SystemMessageId S1_S_TERRITORY_WAR_HAS_BEGUN;
	
	@ClientString(id = 2407, message = "$s1's territory war has ended.")
	public static SystemMessageId S1_S_TERRITORY_WAR_HAS_ENDED;
	
	@ClientString(id = 2408, message = "You are currently registered for a 3 vs. 3 class irrelevant team match.")
	public static SystemMessageId YOU_ARE_CURRENTLY_REGISTERED_FOR_A_3_VS_3_CLASS_IRRELEVANT_TEAM_MATCH;
	
	@ClientString(id = 2409, message = "The number of My Teleports slots has been increased.")
	public static SystemMessageId THE_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_BEEN_INCREASED;
	
	@ClientString(id = 2410, message = "You cannot use My Teleports to reach this area!")
	public static SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_TO_REACH_THIS_AREA;
	
	@ClientString(id = 2411, message = "$c1 has issued a party invitation which you automatically rejected. To receive party invitations, please change the Party Invitation Reject setting in the Options window.")
	public static SystemMessageId C1_HAS_ISSUED_A_PARTY_INVITATION_WHICH_YOU_AUTOMATICALLY_REJECTED_TO_RECEIVE_PARTY_INVITATIONS_PLEASE_CHANGE_THE_PARTY_INVITATION_REJECT_SETTING_IN_THE_OPTIONS_WINDOW;
	
	@ClientString(id = 2412, message = "Your birthday gift has been delivered! Visit the Dimensional Merchant in any village to obtain it.")
	public static SystemMessageId YOUR_BIRTHDAY_GIFT_HAS_BEEN_DELIVERED_VISIT_THE_DIMENSIONAL_MERCHANT_IN_ANY_VILLAGE_TO_OBTAIN_IT;
	
	@ClientString(id = 2413, message = "You are registering as a reserve for the Red Team. Do you wish to continue?")
	public static SystemMessageId YOU_ARE_REGISTERING_AS_A_RESERVE_FOR_THE_RED_TEAM_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 2414, message = "You are registering as a reserve for the Blue Team. Do you wish to continue?")
	public static SystemMessageId YOU_ARE_REGISTERING_AS_A_RESERVE_FOR_THE_BLUE_TEAM_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 2415, message = "You have registered as a reserve for the Red Team. When in battle, the team can change its composition using the Maintain Team Balance function.")
	public static SystemMessageId YOU_HAVE_REGISTERED_AS_A_RESERVE_FOR_THE_RED_TEAM_WHEN_IN_BATTLE_THE_TEAM_CAN_CHANGE_ITS_COMPOSITION_USING_THE_MAINTAIN_TEAM_BALANCE_FUNCTION;
	
	@ClientString(id = 2416, message = "You have registered as a reserve for the Blue Team. When in battle, the team can change its composition using the Maintain Team Balance function.")
	public static SystemMessageId YOU_HAVE_REGISTERED_AS_A_RESERVE_FOR_THE_BLUE_TEAM_WHEN_IN_BATTLE_THE_TEAM_CAN_CHANGE_ITS_COMPOSITION_USING_THE_MAINTAIN_TEAM_BALANCE_FUNCTION;
	
	@ClientString(id = 2417, message = "You are canceling your Aerial Cleft registration. Do you wish to continue?")
	public static SystemMessageId YOU_ARE_CANCELING_YOUR_AERIAL_CLEFT_REGISTRATION_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 2418, message = "The Aerial Cleft registration has been canceled.")
	public static SystemMessageId THE_AERIAL_CLEFT_REGISTRATION_HAS_BEEN_CANCELED;
	
	@ClientString(id = 2419, message = "The Aerial Cleft has been activated. Flight transformation will be possible in approximately 40 seconds.")
	public static SystemMessageId THE_AERIAL_CLEFT_HAS_BEEN_ACTIVATED_FLIGHT_TRANSFORMATION_WILL_BE_POSSIBLE_IN_APPROXIMATELY_40_SECONDS;
	
	@ClientString(id = 2420, message = "The battlefield closes in 1 minute.")
	public static SystemMessageId THE_BATTLEFIELD_CLOSES_IN_1_MINUTE;
	
	@ClientString(id = 2421, message = "The battlefield closes in 10 seconds.")
	public static SystemMessageId THE_BATTLEFIELD_CLOSES_IN_10_SECONDS;
	
	@ClientString(id = 2422, message = "EP, or Energy Points, refers to fuel.")
	public static SystemMessageId EP_OR_ENERGY_POINTS_REFERS_TO_FUEL;
	
	@ClientString(id = 2423, message = "EP can be refilled by using a $s1 while sailing on an airship.")
	public static SystemMessageId EP_CAN_BE_REFILLED_BY_USING_A_S1_WHILE_SAILING_ON_AN_AIRSHIP;
	
	@ClientString(id = 2424, message = "The collection has failed.")
	public static SystemMessageId THE_COLLECTION_HAS_FAILED;
	
	@ClientString(id = 2425, message = "The Aerial Cleft battlefield has been closed.")
	public static SystemMessageId THE_AERIAL_CLEFT_BATTLEFIELD_HAS_BEEN_CLOSED;
	
	@ClientString(id = 2426, message = "$c1 has been expelled from the team.")
	public static SystemMessageId C1_HAS_BEEN_EXPELLED_FROM_THE_TEAM;
	
	@ClientString(id = 2427, message = "The Red Team is victorious.")
	public static SystemMessageId THE_RED_TEAM_IS_VICTORIOUS;
	
	@ClientString(id = 2428, message = "The Blue Team is victorious.")
	public static SystemMessageId THE_BLUE_TEAM_IS_VICTORIOUS;
	
	@ClientString(id = 2429, message = "$c1 has been designated as the target.")
	public static SystemMessageId C1_HAS_BEEN_DESIGNATED_AS_THE_TARGET;
	
	@ClientString(id = 2430, message = "$c1 has fallen. The Red Team's points have increased.")
	public static SystemMessageId C1_HAS_FALLEN_THE_RED_TEAM_S_POINTS_HAVE_INCREASED;
	
	@ClientString(id = 2431, message = "$c2 has fallen. The Blue Team's points have increased.")
	public static SystemMessageId C2_HAS_FALLEN_THE_BLUE_TEAM_S_POINTS_HAVE_INCREASED;
	
	@ClientString(id = 2432, message = "The central stronghold's compressor has been destroyed.")
	public static SystemMessageId THE_CENTRAL_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 2433, message = "The first stronghold's compressor has been destroyed.")
	public static SystemMessageId THE_FIRST_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 2434, message = "The second stronghold's compressor has been destroyed.")
	public static SystemMessageId THE_SECOND_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 2435, message = "The third stronghold's compressor has been destroyed.")
	public static SystemMessageId THE_THIRD_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED;
	
	@ClientString(id = 2436, message = "The central stronghold's compressor is working.")
	public static SystemMessageId THE_CENTRAL_STRONGHOLD_S_COMPRESSOR_IS_WORKING;
	
	@ClientString(id = 2437, message = "The first stronghold's compressor is working.")
	public static SystemMessageId THE_FIRST_STRONGHOLD_S_COMPRESSOR_IS_WORKING;
	
	@ClientString(id = 2438, message = "The second stronghold's compressor is working.")
	public static SystemMessageId THE_SECOND_STRONGHOLD_S_COMPRESSOR_IS_WORKING;
	
	@ClientString(id = 2439, message = "The third stronghold's compressor is working.")
	public static SystemMessageId THE_THIRD_STRONGHOLD_S_COMPRESSOR_IS_WORKING;
	
	@ClientString(id = 2440, message = "$c1 is already registered on the waiting list for the 3 vs. 3 class irrelevant team match.")
	public static SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_3_VS_3_CLASS_IRRELEVANT_TEAM_MATCH;
	
	@ClientString(id = 2441, message = "Only a party leader can request a team match.")
	public static SystemMessageId ONLY_A_PARTY_LEADER_CAN_REQUEST_A_TEAM_MATCH;
	
	@ClientString(id = 2442, message = "The request cannot be made because the requirements have not been met. To participate in a team match, you must first form a 3-member party.")
	public static SystemMessageId THE_REQUEST_CANNOT_BE_MADE_BECAUSE_THE_REQUIREMENTS_HAVE_NOT_BEEN_MET_TO_PARTICIPATE_IN_A_TEAM_MATCH_YOU_MUST_FIRST_FORM_A_3_MEMBER_PARTY;
	
	@ClientString(id = 2443, message = "Flames filled with the Wrath of Valakas are engulfing you.")
	public static SystemMessageId FLAMES_FILLED_WITH_THE_WRATH_OF_VALAKAS_ARE_ENGULFING_YOU;
	
	@ClientString(id = 2444, message = "Flames filled with the Authority of Valakas are binding your mind.")
	public static SystemMessageId FLAMES_FILLED_WITH_THE_AUTHORITY_OF_VALAKAS_ARE_BINDING_YOUR_MIND;
	
	@ClientString(id = 2445, message = "The battlefield channel has been activated.")
	public static SystemMessageId THE_BATTLEFIELD_CHANNEL_HAS_BEEN_ACTIVATED;
	
	@ClientString(id = 2446, message = "The battlefield channel has been deactivated.")
	public static SystemMessageId THE_BATTLEFIELD_CHANNEL_HAS_BEEN_DEACTIVATED;
	
	@ClientString(id = 2447, message = "Five years have passed since this character's creation.")
	public static SystemMessageId FIVE_YEARS_HAVE_PASSED_SINCE_THIS_CHARACTER_S_CREATION;
	
	@ClientString(id = 2448, message = "Happy birthday! Alegria has sent you a birthday gift.")
	public static SystemMessageId HAPPY_BIRTHDAY_ALEGRIA_HAS_SENT_YOU_A_BIRTHDAY_GIFT;
	
	@ClientString(id = 2449, message = "There are $s1 days remaining until your birthday. On your birthday, you will receive a gift that Alegria has carefully prepared.")
	public static SystemMessageId THERE_ARE_S1_DAYS_REMAINING_UNTIL_YOUR_BIRTHDAY_ON_YOUR_BIRTHDAY_YOU_WILL_RECEIVE_A_GIFT_THAT_ALEGRIA_HAS_CAREFULLY_PREPARED;
	
	@ClientString(id = 2450, message = "$c1's birthday is $s3/$s4/$s2.")
	public static SystemMessageId C1_S_BIRTHDAY_IS_S3_S4_S2;
	
	@ClientString(id = 2451, message = "Your cloak has been unequipped because your armor set is no longer complete.")
	public static SystemMessageId YOUR_CLOAK_HAS_BEEN_UNEQUIPPED_BECAUSE_YOUR_ARMOR_SET_IS_NO_LONGER_COMPLETE;
	
	@ClientString(id = 2452, message = "You inventory currently exceeds the normal amount of inventory slots available, so your belt cannot be removed.")
	public static SystemMessageId YOU_INVENTORY_CURRENTLY_EXCEEDS_THE_NORMAL_AMOUNT_OF_INVENTORY_SLOTS_AVAILABLE_SO_YOUR_BELT_CANNOT_BE_REMOVED;
	
	@ClientString(id = 2453, message = "The cloak cannot be equipped because your armor set is not complete.")
	public static SystemMessageId THE_CLOAK_CANNOT_BE_EQUIPPED_BECAUSE_YOUR_ARMOR_SET_IS_NOT_COMPLETE;
	
	@ClientString(id = 2454, message = "Kresnik Class Airship")
	public static SystemMessageId KRESNIK_CLASS_AIRSHIP;
	
	@ClientString(id = 2455, message = "The airship must be summoned in order for you to board.")
	public static SystemMessageId THE_AIRSHIP_MUST_BE_SUMMONED_IN_ORDER_FOR_YOU_TO_BOARD;
	
	@ClientString(id = 2456, message = "In order to acquire an airship, the clan's level must be level 5 or higher.")
	public static SystemMessageId IN_ORDER_TO_ACQUIRE_AN_AIRSHIP_THE_CLAN_S_LEVEL_MUST_BE_LEVEL_5_OR_HIGHER;
	
	@ClientString(id = 2457, message = "An airship cannot be summoned because either you have not registered your airship license, or the airship has not yet been summoned.")
	public static SystemMessageId AN_AIRSHIP_CANNOT_BE_SUMMONED_BECAUSE_EITHER_YOU_HAVE_NOT_REGISTERED_YOUR_AIRSHIP_LICENSE_OR_THE_AIRSHIP_HAS_NOT_YET_BEEN_SUMMONED;
	
	@ClientString(id = 2458, message = "Your clan's airship is already being used by another clan member.")
	public static SystemMessageId YOUR_CLAN_S_AIRSHIP_IS_ALREADY_BEING_USED_BY_ANOTHER_CLAN_MEMBER;
	
	@ClientString(id = 2459, message = "The Airship Summon License has already been acquired.")
	public static SystemMessageId THE_AIRSHIP_SUMMON_LICENSE_HAS_ALREADY_BEEN_ACQUIRED;
	
	@ClientString(id = 2460, message = "The clan owned airship already exists.")
	public static SystemMessageId THE_CLAN_OWNED_AIRSHIP_ALREADY_EXISTS;
	
	@ClientString(id = 2461, message = "Airship Summon License registration can only be done by the clan leader.")
	public static SystemMessageId AIRSHIP_SUMMON_LICENSE_REGISTRATION_CAN_ONLY_BE_DONE_BY_THE_CLAN_LEADER;
	
	@ClientString(id = 2462, message = "An airship cannot be summoned because you don't have enough $s1.")
	public static SystemMessageId AN_AIRSHIP_CANNOT_BE_SUMMONED_BECAUSE_YOU_DON_T_HAVE_ENOUGH_S1;
	
	@ClientString(id = 2463, message = "The airship's fuel (EP) will soon run out.")
	public static SystemMessageId THE_AIRSHIP_S_FUEL_EP_WILL_SOON_RUN_OUT;
	
	@ClientString(id = 2464, message = "The airship's fuel (EP) has run out. The airship's speed will be greatly decreased in this condition.")
	public static SystemMessageId THE_AIRSHIP_S_FUEL_EP_HAS_RUN_OUT_THE_AIRSHIP_S_SPEED_WILL_BE_GREATLY_DECREASED_IN_THIS_CONDITION;
	
	@ClientString(id = 2465, message = "You have selected a 3 vs. 3 class irrelevant team match. Do you wish to participate?")
	public static SystemMessageId YOU_HAVE_SELECTED_A_3_VS_3_CLASS_IRRELEVANT_TEAM_MATCH_DO_YOU_WISH_TO_PARTICIPATE;
	
	@ClientString(id = 2466, message = "A pet on auxiliary mode cannot use skills.")
	public static SystemMessageId A_PET_ON_AUXILIARY_MODE_CANNOT_USE_SKILLS;
	
	@ClientString(id = 2467, message = "Do you wish to begin the game now?")
	public static SystemMessageId DO_YOU_WISH_TO_BEGIN_THE_GAME_NOW;
	
	@ClientString(id = 2468, message = "You have used a report point on $c1. You have $s2 points remaining on this account.")
	public static SystemMessageId YOU_HAVE_USED_A_REPORT_POINT_ON_C1_YOU_HAVE_S2_POINTS_REMAINING_ON_THIS_ACCOUNT;
	
	@ClientString(id = 2469, message = "You have used all available points. Points are reset everyday at noon.")
	public static SystemMessageId YOU_HAVE_USED_ALL_AVAILABLE_POINTS_POINTS_ARE_RESET_EVERYDAY_AT_NOON;
	
	@ClientString(id = 2470, message = "This character cannot make a report. You cannot make a report while located inside a peace zone or a battlefield, while you are an opposing clan member during a clan war, or while participating in the Olympiad.")
	public static SystemMessageId THIS_CHARACTER_CANNOT_MAKE_A_REPORT_YOU_CANNOT_MAKE_A_REPORT_WHILE_LOCATED_INSIDE_A_PEACE_ZONE_OR_A_BATTLEFIELD_WHILE_YOU_ARE_AN_OPPOSING_CLAN_MEMBER_DURING_A_CLAN_WAR_OR_WHILE_PARTICIPATING_IN_THE_OLYMPIAD;
	
	@ClientString(id = 2471, message = "This character cannot make a report. The target has already been reported by either your clan or alliance, or has already been reported from your current IP.")
	public static SystemMessageId THIS_CHARACTER_CANNOT_MAKE_A_REPORT_THE_TARGET_HAS_ALREADY_BEEN_REPORTED_BY_EITHER_YOUR_CLAN_OR_ALLIANCE_OR_HAS_ALREADY_BEEN_REPORTED_FROM_YOUR_CURRENT_IP;
	
	@ClientString(id = 2472, message = "This character cannot make a report because another character from this account has already done so.")
	public static SystemMessageId THIS_CHARACTER_CANNOT_MAKE_A_REPORT_BECAUSE_ANOTHER_CHARACTER_FROM_THIS_ACCOUNT_HAS_ALREADY_DONE_SO;
	
	@ClientString(id = 2473, message = "You have been reported as an illegal program user, so your chatting will be blocked for 10 minutes.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_CHATTING_WILL_BE_BLOCKED_FOR_10_MINUTES;
	
	@ClientString(id = 2474, message = "You have been reported as an illegal program user, so your party participation will be blocked for 60 minutes.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_PARTY_PARTICIPATION_WILL_BE_BLOCKED_FOR_60_MINUTES;
	
	@ClientString(id = 2475, message = "You have been reported as an illegal program user, so your party participation will be blocked for 120 minutes.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_PARTY_PARTICIPATION_WILL_BE_BLOCKED_FOR_120_MINUTES;
	
	@ClientString(id = 2476, message = "You have been reported as an illegal program user, so your party participation will be blocked for 180 minutes.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_PARTY_PARTICIPATION_WILL_BE_BLOCKED_FOR_180_MINUTES;
	
	@ClientString(id = 2477, message = "You have been reported as an illegal program user, so your actions will be restricted for 120 minutes.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_ACTIONS_WILL_BE_RESTRICTED_FOR_120_MINUTES;
	
	@ClientString(id = 2478, message = "You have been reported as an illegal program user, so your actions will be restricted for 180 minutes.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_ACTIONS_WILL_BE_RESTRICTED_FOR_180_MINUTES;
	
	@ClientString(id = 2479, message = "You have been reported as an illegal program user, so your actions will be restricted for 180 minutes.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_ACTIONS_WILL_BE_RESTRICTED_FOR_180_MINUTES_2;
	
	@ClientString(id = 2480, message = "You have been reported as an illegal program user, so movement is prohibited for 120 minutes.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_MOVEMENT_IS_PROHIBITED_FOR_120_MINUTES;
	
	@ClientString(id = 2481, message = "$c1 has been reported as an illegal program user and is currently being investigated.")
	public static SystemMessageId C1_HAS_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_IS_CURRENTLY_BEING_INVESTIGATED;
	
	@ClientString(id = 2482, message = "$c1 has been reported as an illegal program user and cannot join a party.")
	public static SystemMessageId C1_HAS_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_CANNOT_JOIN_A_PARTY;
	
	@ClientString(id = 2483, message = "You have been reported as an illegal program user, so chatting is not allowed.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_CHATTING_IS_NOT_ALLOWED;
	
	@ClientString(id = 2484, message = "You have been reported as an illegal program user, so participating in a party is not allowed.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_PARTICIPATING_IN_A_PARTY_IS_NOT_ALLOWED;
	
	@ClientString(id = 2485, message = "You have been reported as an illegal program user so your actions have been restricted.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_YOUR_ACTIONS_HAVE_BEEN_RESTRICTED;
	
	@ClientString(id = 2486, message = "You have been blocked due to verification that you are using a third party program. Subsequent violations may result in termination of your account rather than a penalty within the game.")
	public static SystemMessageId YOU_HAVE_BEEN_BLOCKED_DUE_TO_VERIFICATION_THAT_YOU_ARE_USING_A_THIRD_PARTY_PROGRAM_SUBSEQUENT_VIOLATIONS_MAY_RESULT_IN_TERMINATION_OF_YOUR_ACCOUNT_RATHER_THAN_A_PENALTY_WITHIN_THE_GAME;
	
	@ClientString(id = 2487, message = "You have been reported as an illegal program user, and your connection has been ended. Please contact our CS team to confirm your identity.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_CONNECTION_HAS_BEEN_ENDED_PLEASE_CONTACT_OUR_CS_TEAM_TO_CONFIRM_YOUR_IDENTITY;
	
	@ClientString(id = 2488, message = "You cannot enter the Aerial Cleft because you are below the required level. Entry is possible only at level 75 or above.")
	public static SystemMessageId YOU_CANNOT_ENTER_THE_AERIAL_CLEFT_BECAUSE_YOU_ARE_BELOW_THE_REQUIRED_LEVEL_ENTRY_IS_POSSIBLE_ONLY_AT_LEVEL_75_OR_ABOVE;
	
	@ClientString(id = 2489, message = "You must target a control device in order to perform this action.")
	public static SystemMessageId YOU_MUST_TARGET_A_CONTROL_DEVICE_IN_ORDER_TO_PERFORM_THIS_ACTION;
	
	@ClientString(id = 2490, message = "You cannot perform this action because you are too far away from the control device.")
	public static SystemMessageId YOU_CANNOT_PERFORM_THIS_ACTION_BECAUSE_YOU_ARE_TOO_FAR_AWAY_FROM_THE_CONTROL_DEVICE;
	
	@ClientString(id = 2491, message = "Your ship cannot teleport because it does not have enough fuel for the trip.")
	public static SystemMessageId YOUR_SHIP_CANNOT_TELEPORT_BECAUSE_IT_DOES_NOT_HAVE_ENOUGH_FUEL_FOR_THE_TRIP;
	
	@ClientString(id = 2492, message = "The airship has been summoned. It will automatically depart in %s minutes.")
	public static SystemMessageId THE_AIRSHIP_HAS_BEEN_SUMMONED_IT_WILL_AUTOMATICALLY_DEPART_IN_S_MINUTES;
	
	@ClientString(id = 2493, message = "Enter chat mode is automatically enabled when you are in a flying transformation state.")
	public static SystemMessageId ENTER_CHAT_MODE_IS_AUTOMATICALLY_ENABLED_WHEN_YOU_ARE_IN_A_FLYING_TRANSFORMATION_STATE;
	
	@ClientString(id = 2494, message = "Enter chat mode is automatically enabled when you are in airship control mode.")
	public static SystemMessageId ENTER_CHAT_MODE_IS_AUTOMATICALLY_ENABLED_WHEN_YOU_ARE_IN_AIRSHIP_CONTROL_MODE;
	
	@ClientString(id = 2495, message = "W (go forward), S (stop), A (turn left), D (turn right), E (increase altitude) and Q (decrease altitude).")
	public static SystemMessageId W_GO_FORWARD_S_STOP_A_TURN_LEFT_D_TURN_RIGHT_E_INCREASE_ALTITUDE_AND_Q_DECREASE_ALTITUDE;
	
	@ClientString(id = 2496, message = "When you click on a skill designated on your shortcut bar, that slot is activated. Once activated, you can press the spacebar to execute the designated skill.")
	public static SystemMessageId WHEN_YOU_CLICK_ON_A_SKILL_DESIGNATED_ON_YOUR_SHORTCUT_BAR_THAT_SLOT_IS_ACTIVATED_ONCE_ACTIVATED_YOU_CAN_PRESS_THE_SPACEBAR_TO_EXECUTE_THE_DESIGNATED_SKILL;
	
	@ClientString(id = 2497, message = "To stop receiving the above tip, please check the box next to Disable Game Tips from your Options menu.")
	public static SystemMessageId TO_STOP_RECEIVING_THE_ABOVE_TIP_PLEASE_CHECK_THE_BOX_NEXT_TO_DISABLE_GAME_TIPS_FROM_YOUR_OPTIONS_MENU;
	
	@ClientString(id = 2498, message = "While piloting an airship, you can change your altitude using the button at the center of the helm image.")
	public static SystemMessageId WHILE_PILOTING_AN_AIRSHIP_YOU_CAN_CHANGE_YOUR_ALTITUDE_USING_THE_BUTTON_AT_THE_CENTER_OF_THE_HELM_IMAGE;
	
	@ClientString(id = 2499, message = "You cannot collect because someone else is already collecting.")
	public static SystemMessageId YOU_CANNOT_COLLECT_BECAUSE_SOMEONE_ELSE_IS_ALREADY_COLLECTING;
	
	@ClientString(id = 2500, message = "Your collection has succeeded.")
	public static SystemMessageId YOUR_COLLECTION_HAS_SUCCEEDED;
	
	@ClientString(id = 2501, message = "You will be moved to the previous chatting channel tab.")
	public static SystemMessageId YOU_WILL_BE_MOVED_TO_THE_PREVIOUS_CHATTING_CHANNEL_TAB;
	
	@ClientString(id = 2502, message = "You will be moved to the next chatting channel tab.")
	public static SystemMessageId YOU_WILL_BE_MOVED_TO_THE_NEXT_CHATTING_CHANNEL_TAB;
	
	@ClientString(id = 2503, message = "The currently selected target will be cancelled.")
	public static SystemMessageId THE_CURRENTLY_SELECTED_TARGET_WILL_BE_CANCELLED;
	
	@ClientString(id = 2504, message = "Focus will be moved to chat window.")
	public static SystemMessageId FOCUS_WILL_BE_MOVED_TO_CHAT_WINDOW;
	
	@ClientString(id = 2505, message = "Opens or closes the inventory window.")
	public static SystemMessageId OPENS_OR_CLOSES_THE_INVENTORY_WINDOW;
	
	@ClientString(id = 2506, message = "Temporarily hides all open windows.")
	public static SystemMessageId TEMPORARILY_HIDES_ALL_OPEN_WINDOWS;
	
	@ClientString(id = 2507, message = "Closes all open windows.")
	public static SystemMessageId CLOSES_ALL_OPEN_WINDOWS;
	
	@ClientString(id = 2508, message = "Opens the GM manager window.")
	public static SystemMessageId OPENS_THE_GM_MANAGER_WINDOW;
	
	@ClientString(id = 2509, message = "Opens the GM petition window.")
	public static SystemMessageId OPENS_THE_GM_PETITION_WINDOW;
	
	@ClientString(id = 2510, message = "The buff in the party window is toggled. Buff for one input, debuff for two inputs, a song and dance for three inputs, turnoff for 4 inputs")
	public static SystemMessageId THE_BUFF_IN_THE_PARTY_WINDOW_IS_TOGGLED_BUFF_FOR_ONE_INPUT_DEBUFF_FOR_TWO_INPUTS_A_SONG_AND_DANCE_FOR_THREE_INPUTS_TURNOFF_FOR_4_INPUTS;
	
	@ClientString(id = 2511, message = "Activates or deactivates minimum frame function.")
	public static SystemMessageId ACTIVATES_OR_DEACTIVATES_MINIMUM_FRAME_FUNCTION;
	
	@ClientString(id = 2512, message = "Runs or closes the MSN messenger window.")
	public static SystemMessageId RUNS_OR_CLOSES_THE_MSN_MESSENGER_WINDOW;
	
	@ClientString(id = 2513, message = "Assign 1st slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_1ST_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2514, message = "Assign 2nd slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_2ND_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2515, message = "Assign 3rd slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_3RD_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2516, message = "Assign 4th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_4TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2517, message = "Assign 5th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_5TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2518, message = "Assign 6th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_6TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2519, message = "Assign 7th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_7TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2520, message = "Assign 8th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_8TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2521, message = "Assign 9th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_9TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2522, message = "Assign 10th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_10TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2523, message = "Assign 11th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_11TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2524, message = "Assign 12th slot shortcut in the shortcut base window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_12TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2525, message = "Assign 1st slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_1ST_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2526, message = "Assign 2nd slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_2ND_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2527, message = "Assign 3rd slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_3RD_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2528, message = "Assign 4th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_4TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2529, message = "Assign 5th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_5TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2530, message = "Assign 6th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_6TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2531, message = "Assign 7th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_7TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2532, message = "Assign 8th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_8TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2533, message = "Assign 9th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_9TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2534, message = "Assign 10th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_10TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2535, message = "Assign 11th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_11TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2536, message = "Assign 12th slot shortcut in the 1st shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_12TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2537, message = "Assign 1st slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_1ST_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2538, message = "Assign 2nd slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_2ND_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2539, message = "Assign 3rd slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_3RD_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2540, message = "Assign 4th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_4TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2541, message = "Assign 5th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_5TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2542, message = "Assign 6th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_6TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2543, message = "Assign 7th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_7TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2544, message = "Assign 8th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_8TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2545, message = "Assign 9th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_9TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2546, message = "Assign 10th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_10TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2547, message = "Assign 11th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_11TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2548, message = "Assign 12th slot shortcut in the 2nd shortcut expanded window. Combination of Ctrl and Shift cannot be assigned.")
	public static SystemMessageId ASSIGN_12TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE_ASSIGNED;
	
	@ClientString(id = 2549, message = "Move the shortcut page in the shortcut base window to page 1.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_1;
	
	@ClientString(id = 2550, message = "Move the shortcut page in the shortcut base window to page 2.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_2;
	
	@ClientString(id = 2551, message = "Move the shortcut page in the shortcut base window to page 3.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_3;
	
	@ClientString(id = 2552, message = "Move the shortcut page in the shortcut base window to page 4.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_4;
	
	@ClientString(id = 2553, message = "Move the shortcut page in the shortcut base window to page 5.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_5;
	
	@ClientString(id = 2554, message = "Move the shortcut page in the shortcut base window to page 6.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_6;
	
	@ClientString(id = 2555, message = "Move the shortcut page in the shortcut base window to page 7.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_7;
	
	@ClientString(id = 2556, message = "Move the shortcut page in the shortcut base window to page 8.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_8;
	
	@ClientString(id = 2557, message = "Move the shortcut page in the shortcut base window to page 9.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_9;
	
	@ClientString(id = 2558, message = "Move the shortcut page in the shortcut base window to page 10.")
	public static SystemMessageId MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_10;
	
	@ClientString(id = 2559, message = "Opens and closes the action window, executing character actions and game commands.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_ACTION_WINDOW_EXECUTING_CHARACTER_ACTIONS_AND_GAME_COMMANDS;
	
	@ClientString(id = 2560, message = "Opens and closes the game bulletin board.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_GAME_BULLETIN_BOARD;
	
	@ClientString(id = 2561, message = "Opens and closes the calculator.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_CALCULATOR;
	
	@ClientString(id = 2562, message = "Hides or shows the chat window, the window always shows by default.")
	public static SystemMessageId HIDES_OR_SHOWS_THE_CHAT_WINDOW_THE_WINDOW_ALWAYS_SHOWS_BY_DEFAULT;
	
	@ClientString(id = 2563, message = "Opens and closes the clan window, confirming information of the included clan and performs the various set-ups related to the clan.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_CLAN_WINDOW_CONFIRMING_INFORMATION_OF_THE_INCLUDED_CLAN_AND_PERFORMS_THE_VARIOUS_SET_UPS_RELATED_TO_THE_CLAN;
	
	@ClientString(id = 2564, message = "Opens and closes the status window, showing the detailed status of a character that you created.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_STATUS_WINDOW_SHOWING_THE_DETAILED_STATUS_OF_A_CHARACTER_THAT_YOU_CREATED;
	
	@ClientString(id = 2565, message = "Opens and closes the help window.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_HELP_WINDOW;
	
	@ClientString(id = 2566, message = "Opens or closes the inventory window.")
	public static SystemMessageId OPENS_OR_CLOSES_THE_INVENTORY_WINDOW_2;
	
	@ClientString(id = 2567, message = "Opens and closes the macro window for macro settings.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_MACRO_WINDOW_FOR_MACRO_SETTINGS;
	
	@ClientString(id = 2568, message = "Opens and closes the skill window, displaying the list of skills that you can use.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_SKILL_WINDOW_DISPLAYING_THE_LIST_OF_SKILLS_THAT_YOU_CAN_USE;
	
	@ClientString(id = 2569, message = "Hides or shows the menu window, the window shows by default.")
	public static SystemMessageId HIDES_OR_SHOWS_THE_MENU_WINDOW_THE_WINDOW_SHOWS_BY_DEFAULT;
	
	@ClientString(id = 2570, message = "Opens and closes the mini map, showing detailed information about the game world.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_MINI_MAP_SHOWING_DETAILED_INFORMATION_ABOUT_THE_GAME_WORLD;
	
	@ClientString(id = 2571, message = "Opens and closes the option window.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_OPTION_WINDOW;
	
	@ClientString(id = 2572, message = "Open and close the party matching window, useful in organizing a party by helping to easily find other members looking for a party.")
	public static SystemMessageId OPEN_AND_CLOSE_THE_PARTY_MATCHING_WINDOW_USEFUL_IN_ORGANIZING_A_PARTY_BY_HELPING_TO_EASILY_FIND_OTHER_MEMBERS_LOOKING_FOR_A_PARTY;
	
	@ClientString(id = 2573, message = "Open and close the quest journal, displaying the progress of quests.")
	public static SystemMessageId OPEN_AND_CLOSE_THE_QUEST_JOURNAL_DISPLAYING_THE_PROGRESS_OF_QUESTS;
	
	@ClientString(id = 2574, message = "Hides or re-opens the Radar Map, which always appears by default.")
	public static SystemMessageId HIDES_OR_RE_OPENS_THE_RADAR_MAP_WHICH_ALWAYS_APPEARS_BY_DEFAULT;
	
	@ClientString(id = 2575, message = "Hide or show the status window, the window will show by default.")
	public static SystemMessageId HIDE_OR_SHOW_THE_STATUS_WINDOW_THE_WINDOW_WILL_SHOW_BY_DEFAULT;
	
	@ClientString(id = 2576, message = "Opens and closes the system menu window, enables detailed menu selection.")
	public static SystemMessageId OPENS_AND_CLOSES_THE_SYSTEM_MENU_WINDOW_ENABLES_DETAILED_MENU_SELECTION;
	
	@ClientString(id = 2577, message = "Do not show drop items dropped in the world. Game performance speed can be enhanced by using this option.")
	public static SystemMessageId DO_NOT_SHOW_DROP_ITEMS_DROPPED_IN_THE_WORLD_GAME_PERFORMANCE_SPEED_CAN_BE_ENHANCED_BY_USING_THIS_OPTION;
	
	@ClientString(id = 2578, message = "A key to automatically send whispers to a targeted character.")
	public static SystemMessageId A_KEY_TO_AUTOMATICALLY_SEND_WHISPERS_TO_A_TARGETED_CHARACTER;
	
	@ClientString(id = 2579, message = "Turns off all game sounds.")
	public static SystemMessageId TURNS_OFF_ALL_GAME_SOUNDS;
	
	@ClientString(id = 2580, message = "Expands each shortcut window.")
	public static SystemMessageId EXPANDS_EACH_SHORTCUT_WINDOW;
	
	@ClientString(id = 2581, message = "Initialize user interface location to a default location.")
	public static SystemMessageId INITIALIZE_USER_INTERFACE_LOCATION_TO_A_DEFAULT_LOCATION;
	
	@ClientString(id = 2582, message = "Spin my character or mountable to the left.")
	public static SystemMessageId SPIN_MY_CHARACTER_OR_MOUNTABLE_TO_THE_LEFT;
	
	@ClientString(id = 2583, message = "Spin my character or mountable to the right.")
	public static SystemMessageId SPIN_MY_CHARACTER_OR_MOUNTABLE_TO_THE_RIGHT;
	
	@ClientString(id = 2584, message = "Spin my character or mountable forward.")
	public static SystemMessageId SPIN_MY_CHARACTER_OR_MOUNTABLE_FORWARD;
	
	@ClientString(id = 2585, message = "Spin my character or mountable to the rear.")
	public static SystemMessageId SPIN_MY_CHARACTER_OR_MOUNTABLE_TO_THE_REAR;
	
	@ClientString(id = 2586, message = "Continue moving in the present direction.")
	public static SystemMessageId CONTINUE_MOVING_IN_THE_PRESENT_DIRECTION;
	
	@ClientString(id = 2587, message = "Reduce the viewing point of my character or mountable.")
	public static SystemMessageId REDUCE_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE;
	
	@ClientString(id = 2588, message = "Enlarge the viewing point of my character or mountable.")
	public static SystemMessageId ENLARGE_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE;
	
	@ClientString(id = 2589, message = "Quickly spin in all directions the viewing point of my character or mountable.")
	public static SystemMessageId QUICKLY_SPIN_IN_ALL_DIRECTIONS_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE;
	
	@ClientString(id = 2590, message = "Opens the GM manager window.")
	public static SystemMessageId OPENS_THE_GM_MANAGER_WINDOW_2;
	
	@ClientString(id = 2591, message = "Opens the GM petition window.")
	public static SystemMessageId OPENS_THE_GM_PETITION_WINDOW_2;
	
	@ClientString(id = 2592, message = "Quickly switch the content of the expanded shortcut window.")
	public static SystemMessageId QUICKLY_SWITCH_THE_CONTENT_OF_THE_EXPANDED_SHORTCUT_WINDOW;
	
	@ClientString(id = 2593, message = "Advance by a set distance the viewing point of my character or mountable.")
	public static SystemMessageId ADVANCE_BY_A_SET_DISTANCE_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE;
	
	@ClientString(id = 2594, message = "Retreat by a set distance the viewing point of my character or mountable.")
	public static SystemMessageId RETREAT_BY_A_SET_DISTANCE_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE;
	
	@ClientString(id = 2595, message = "Reset the viewing point of my character or mountable.")
	public static SystemMessageId RESET_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE;
	
	@ClientString(id = 2596, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED;
	
	@ClientString(id = 2597, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_2;
	
	@ClientString(id = 2598, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_3;
	
	@ClientString(id = 2599, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_4;
	
	@ClientString(id = 2701, message = "The match is being prepared. Please try again later.")
	public static SystemMessageId THE_MATCH_IS_BEING_PREPARED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 2702, message = "You were excluded from the match because the registration count was not correct.")
	public static SystemMessageId YOU_WERE_EXCLUDED_FROM_THE_MATCH_BECAUSE_THE_REGISTRATION_COUNT_WAS_NOT_CORRECT;
	
	@ClientString(id = 2703, message = "Team members were modified because the teams were unbalanced.")
	public static SystemMessageId TEAM_MEMBERS_WERE_MODIFIED_BECAUSE_THE_TEAMS_WERE_UNBALANCED;
	
	@ClientString(id = 2704, message = "You cannot register because capacity has been exceeded.")
	public static SystemMessageId YOU_CANNOT_REGISTER_BECAUSE_CAPACITY_HAS_BEEN_EXCEEDED;
	
	@ClientString(id = 2705, message = "The match waiting time was extended by 1 minute.")
	public static SystemMessageId THE_MATCH_WAITING_TIME_WAS_EXTENDED_BY_1_MINUTE;
	
	@ClientString(id = 2706, message = "You cannot enter because you do not meet the requirements.")
	public static SystemMessageId YOU_CANNOT_ENTER_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS;
	
	@ClientString(id = 2707, message = "You must wait 10 seconds before attempting to register again.")
	public static SystemMessageId YOU_MUST_WAIT_10_SECONDS_BEFORE_ATTEMPTING_TO_REGISTER_AGAIN;
	
	@ClientString(id = 2708, message = "You cannot register while in possession of a cursed weapon.")
	public static SystemMessageId YOU_CANNOT_REGISTER_WHILE_IN_POSSESSION_OF_A_CURSED_WEAPON;
	
	@ClientString(id = 2709, message = "Applicants for the Olympiad, Underground Coliseum, or Kratei's Cube matches cannot register.")
	public static SystemMessageId APPLICANTS_FOR_THE_OLYMPIAD_UNDERGROUND_COLISEUM_OR_KRATEI_S_CUBE_MATCHES_CANNOT_REGISTER;
	
	@ClientString(id = 2710, message = "Current location: $s1, $s2, $s3 (near the Keucereus Alliance Base)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_NEAR_THE_KEUCEREUS_ALLIANCE_BASE;
	
	@ClientString(id = 2711, message = "Current location: $s1, $s2, $s3 (inside the Seed of Infinity)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_INSIDE_THE_SEED_OF_INFINITY;
	
	@ClientString(id = 2712, message = "Current location: $s1, $s2, $s3 (outside the Seed of Infinity)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_OUTSIDE_THE_SEED_OF_INFINITY;
	
	@ClientString(id = 2713, message = "------------------------------------------------------")
	public static SystemMessageId EMPTY_7;
	
	@ClientString(id = 2714, message = "----------------------------------------------------------------------")
	public static SystemMessageId EMPTY_8;
	
	@ClientString(id = 2715, message = "Airships cannot be boarded in the current area.")
	public static SystemMessageId AIRSHIPS_CANNOT_BE_BOARDED_IN_THE_CURRENT_AREA;
	
	@ClientString(id = 2716, message = "Current location: $s1, $s2, $s3 (inside Aerial Cleft)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_INSIDE_AERIAL_CLEFT;
	
	@ClientString(id = 2717, message = "The airship will dock at the wharf shortly.")
	public static SystemMessageId THE_AIRSHIP_WILL_DOCK_AT_THE_WHARF_SHORTLY;
	
	@ClientString(id = 2718, message = "That skill cannot be used because your target's location is too high or low.")
	public static SystemMessageId THAT_SKILL_CANNOT_BE_USED_BECAUSE_YOUR_TARGET_S_LOCATION_IS_TOO_HIGH_OR_LOW;
	
	@ClientString(id = 2719, message = "Only non-compressed 256 color bitmap files can be registered.")
	public static SystemMessageId ONLY_NON_COMPRESSED_256_COLOR_BITMAP_FILES_CAN_BE_REGISTERED;
	
	@ClientString(id = 2720, message = "Instant zone: $s1's entry has been restricted. You can check the next possible entry time by using the command '/instancezone.'")
	public static SystemMessageId INSTANT_ZONE_S1_S_ENTRY_HAS_BEEN_RESTRICTED_YOU_CAN_CHECK_THE_NEXT_POSSIBLE_ENTRY_TIME_BY_USING_THE_COMMAND_INSTANCEZONE;
	
	@ClientString(id = 2721, message = "You are too high to perform this action. Please lower your altitude and try again.")
	public static SystemMessageId YOU_ARE_TOO_HIGH_TO_PERFORM_THIS_ACTION_PLEASE_LOWER_YOUR_ALTITUDE_AND_TRY_AGAIN;
	
	@ClientString(id = 2722, message = "Another airship has already been summoned. Please try again later.")
	public static SystemMessageId ANOTHER_AIRSHIP_HAS_ALREADY_BEEN_SUMMONED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 2723, message = "The airship cannot be summoned because you don't have enough $s1.")
	public static SystemMessageId THE_AIRSHIP_CANNOT_BE_SUMMONED_BECAUSE_YOU_DON_T_HAVE_ENOUGH_S1;
	
	@ClientString(id = 2724, message = "The airship cannot be purchased because you don't have enough $s1.")
	public static SystemMessageId THE_AIRSHIP_CANNOT_BE_PURCHASED_BECAUSE_YOU_DON_T_HAVE_ENOUGH_S1;
	
	@ClientString(id = 2725, message = "You cannot summon the airship because you do not meet the requirements.")
	public static SystemMessageId YOU_CANNOT_SUMMON_THE_AIRSHIP_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS;
	
	@ClientString(id = 2726, message = "You cannot purchase the airship because you do not meet the requirements.")
	public static SystemMessageId YOU_CANNOT_PURCHASE_THE_AIRSHIP_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS;
	
	@ClientString(id = 2727, message = "You cannot board because you do not meet the requirements.")
	public static SystemMessageId YOU_CANNOT_BOARD_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS;
	
	@ClientString(id = 2728, message = "This action is prohibited while mounted or on an airship.")
	public static SystemMessageId THIS_ACTION_IS_PROHIBITED_WHILE_MOUNTED_OR_ON_AN_AIRSHIP;
	
	@ClientString(id = 2729, message = "You cannot control the helm while transformed.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_TRANSFORMED;
	
	@ClientString(id = 2730, message = "You cannot control the helm while you are petrified.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_YOU_ARE_PETRIFIED;
	
	@ClientString(id = 2731, message = "You cannot control the helm when you are dead.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHEN_YOU_ARE_DEAD;
	
	@ClientString(id = 2732, message = "You cannot control the helm while fishing.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_FISHING;
	
	@ClientString(id = 2733, message = "You cannot control the helm while in a battle.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_BATTLE;
	
	@ClientString(id = 2734, message = "You cannot control the helm while in a duel.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_DUEL;
	
	@ClientString(id = 2735, message = "You cannot control the helm while in a sitting position.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_SITTING_POSITION;
	
	@ClientString(id = 2736, message = "You cannot control the helm while using a skill.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_USING_A_SKILL;
	
	@ClientString(id = 2737, message = "You cannot control the helm while a cursed weapon is equipped.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_A_CURSED_WEAPON_IS_EQUIPPED;
	
	@ClientString(id = 2738, message = "You cannot control the helm while holding a flag.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_HOLDING_A_FLAG;
	
	@ClientString(id = 2739, message = "You cannot control the helm because you do not meet the requirements.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS;
	
	@ClientString(id = 2740, message = "This action is prohibited while steering.")
	public static SystemMessageId THIS_ACTION_IS_PROHIBITED_WHILE_STEERING;
	
	@ClientString(id = 2741, message = "You can control an airship by targeting the airship's helm and clicking the 'Steer' button from your Actions list.")
	public static SystemMessageId YOU_CAN_CONTROL_AN_AIRSHIP_BY_TARGETING_THE_AIRSHIP_S_HELM_AND_CLICKING_THE_STEER_BUTTON_FROM_YOUR_ACTIONS_LIST;
	
	@ClientString(id = 2742, message = "Any character riding the airship can control it.")
	public static SystemMessageId ANY_CHARACTER_RIDING_THE_AIRSHIP_CAN_CONTROL_IT;
	
	@ClientString(id = 2743, message = "If you restart while on an airship, you will return to the departure location.")
	public static SystemMessageId IF_YOU_RESTART_WHILE_ON_AN_AIRSHIP_YOU_WILL_RETURN_TO_THE_DEPARTURE_LOCATION;
	
	@ClientString(id = 2744, message = "If you press the 'Control Cancel' action button, you can exit the control state at any time.")
	public static SystemMessageId IF_YOU_PRESS_THE_CONTROL_CANCEL_ACTION_BUTTON_YOU_CAN_EXIT_THE_CONTROL_STATE_AT_ANY_TIME;
	
	@ClientString(id = 2745, message = "The 'Exit Airship' button allows you to disembark before the airship departs.")
	public static SystemMessageId THE_EXIT_AIRSHIP_BUTTON_ALLOWS_YOU_TO_DISEMBARK_BEFORE_THE_AIRSHIP_DEPARTS;
	
	@ClientString(id = 2746, message = "Use the 'Depart' button from your Destination Map to make the airship depart.")
	public static SystemMessageId USE_THE_DEPART_BUTTON_FROM_YOUR_DESTINATION_MAP_TO_MAKE_THE_AIRSHIP_DEPART;
	
	@ClientString(id = 2747, message = "The Destination Map contains convenient travel locations. Clicking on a dot will display how much fuel (EP) is consumed for that location.")
	public static SystemMessageId THE_DESTINATION_MAP_CONTAINS_CONVENIENT_TRAVEL_LOCATIONS_CLICKING_ON_A_DOT_WILL_DISPLAY_HOW_MUCH_FUEL_EP_IS_CONSUMED_FOR_THAT_LOCATION;
	
	@ClientString(id = 2748, message = "You have been reported as an illegal program user and cannot report other users.")
	public static SystemMessageId YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_CANNOT_REPORT_OTHER_USERS;
	
	@ClientString(id = 2749, message = "You have reached your crystallization limit and cannot crystallize any more.")
	public static SystemMessageId YOU_HAVE_REACHED_YOUR_CRYSTALLIZATION_LIMIT_AND_CANNOT_CRYSTALLIZE_ANY_MORE;
	
	@ClientString(id = 2750, message = "The $s1 ward has been destroyed! $c2 now has the territory ward.")
	public static SystemMessageId THE_S1_WARD_HAS_BEEN_DESTROYED_C2_NOW_HAS_THE_TERRITORY_WARD;
	
	@ClientString(id = 2751, message = "The character that acquired $s1's ward has been killed.")
	public static SystemMessageId THE_CHARACTER_THAT_ACQUIRED_S1_S_WARD_HAS_BEEN_KILLED;
	
	@ClientString(id = 2752, message = "The war for $s1 has been declared.")
	public static SystemMessageId THE_WAR_FOR_S1_HAS_BEEN_DECLARED;
	
	@ClientString(id = 2753, message = "This type of attack is prohibited when allied troops are the target.")
	public static SystemMessageId THIS_TYPE_OF_ATTACK_IS_PROHIBITED_WHEN_ALLIED_TROOPS_ARE_THE_TARGET;
	
	@ClientString(id = 2754, message = "You cannot be simultaneously registered for PVP matches such as the Olympiad, Underground Coliseum, Aerial Cleft, Kratei's Cube, and Handy's Block Checkers.")
	public static SystemMessageId YOU_CANNOT_BE_SIMULTANEOUSLY_REGISTERED_FOR_PVP_MATCHES_SUCH_AS_THE_OLYMPIAD_UNDERGROUND_COLISEUM_AERIAL_CLEFT_KRATEI_S_CUBE_AND_HANDY_S_BLOCK_CHECKERS;
	
	@ClientString(id = 2755, message = "$c1 has been designated as CAT (Combat Aerial Target).")
	public static SystemMessageId C1_HAS_BEEN_DESIGNATED_AS_CAT_COMBAT_AERIAL_TARGET;
	
	@ClientString(id = 2756, message = "Another player is probably controlling the target.")
	public static SystemMessageId ANOTHER_PLAYER_IS_PROBABLY_CONTROLLING_THE_TARGET;
	
	@ClientString(id = 2757, message = "The ship is already moving so you have failed to board.")
	public static SystemMessageId THE_SHIP_IS_ALREADY_MOVING_SO_YOU_HAVE_FAILED_TO_BOARD;
	
	@ClientString(id = 2758, message = "You cannot control the target while a pet or servitor is summoned.")
	public static SystemMessageId YOU_CANNOT_CONTROL_THE_TARGET_WHILE_A_PET_OR_SERVITOR_IS_SUMMONED;
	
	@ClientString(id = 2759, message = "When actions are prohibited, you cannot mount a mountable.")
	public static SystemMessageId WHEN_ACTIONS_ARE_PROHIBITED_YOU_CANNOT_MOUNT_A_MOUNTABLE;
	
	@ClientString(id = 2760, message = "When actions are prohibited, you cannot control the target.")
	public static SystemMessageId WHEN_ACTIONS_ARE_PROHIBITED_YOU_CANNOT_CONTROL_THE_TARGET;
	
	@ClientString(id = 2761, message = "You must target the one you wish to control.")
	public static SystemMessageId YOU_MUST_TARGET_THE_ONE_YOU_WISH_TO_CONTROL;
	
	@ClientString(id = 2762, message = "You cannot control because you are too far.")
	public static SystemMessageId YOU_CANNOT_CONTROL_BECAUSE_YOU_ARE_TOO_FAR;
	
	@ClientString(id = 2763, message = "You cannot enter the battlefield while in a party state.")
	public static SystemMessageId YOU_CANNOT_ENTER_THE_BATTLEFIELD_WHILE_IN_A_PARTY_STATE;
	
	@ClientString(id = 2764, message = "You cannot enter because the corresponding alliance channel's maximum number of entrants has been reached.")
	public static SystemMessageId YOU_CANNOT_ENTER_BECAUSE_THE_CORRESPONDING_ALLIANCE_CHANNEL_S_MAXIMUM_NUMBER_OF_ENTRANTS_HAS_BEEN_REACHED;
	
	@ClientString(id = 2765, message = "Only the alliance channel leader can attempt entry.")
	public static SystemMessageId ONLY_THE_ALLIANCE_CHANNEL_LEADER_CAN_ATTEMPT_ENTRY;
	
	@ClientString(id = 2766, message = "Seed of Infinity Stage 1 Attack In Progress")
	public static SystemMessageId SEED_OF_INFINITY_STAGE_1_ATTACK_IN_PROGRESS;
	
	@ClientString(id = 2767, message = "Seed of Infinity Stage 2 Attack In Progress")
	public static SystemMessageId SEED_OF_INFINITY_STAGE_2_ATTACK_IN_PROGRESS;
	
	@ClientString(id = 2768, message = "Seed of Infinity Conquest Complete")
	public static SystemMessageId SEED_OF_INFINITY_CONQUEST_COMPLETE;
	
	@ClientString(id = 2769, message = "Seed of Infinity Stage 1 Defense In Progress")
	public static SystemMessageId SEED_OF_INFINITY_STAGE_1_DEFENSE_IN_PROGRESS;
	
	@ClientString(id = 2770, message = "Seed of Infinity Stage 2 Defense In Progress")
	public static SystemMessageId SEED_OF_INFINITY_STAGE_2_DEFENSE_IN_PROGRESS;
	
	@ClientString(id = 2771, message = "Seed of Destruction Attack in Progress")
	public static SystemMessageId SEED_OF_DESTRUCTION_ATTACK_IN_PROGRESS;
	
	@ClientString(id = 2772, message = "Seed of Destruction Conquest Complete")
	public static SystemMessageId SEED_OF_DESTRUCTION_CONQUEST_COMPLETE;
	
	@ClientString(id = 2773, message = "Seed of Destruction Defense in Progress")
	public static SystemMessageId SEED_OF_DESTRUCTION_DEFENSE_IN_PROGRESS;
	
	@ClientString(id = 2774, message = "You can make another report in $s1-minute(s). You have $s2 points remaining on this account.")
	public static SystemMessageId YOU_CAN_MAKE_ANOTHER_REPORT_IN_S1_MINUTE_S_YOU_HAVE_S2_POINTS_REMAINING_ON_THIS_ACCOUNT;
	
	@ClientString(id = 2775, message = "The match cannot take place because a party member is in the process of boarding.")
	public static SystemMessageId THE_MATCH_CANNOT_TAKE_PLACE_BECAUSE_A_PARTY_MEMBER_IS_IN_THE_PROCESS_OF_BOARDING;
	
	@ClientString(id = 2776, message = "The effect of territory ward is disappearing.")
	public static SystemMessageId THE_EFFECT_OF_TERRITORY_WARD_IS_DISAPPEARING;
	
	@ClientString(id = 2777, message = "The airship summon license has been entered. Your clan can now summon the airship.")
	public static SystemMessageId THE_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_ENTERED_YOUR_CLAN_CAN_NOW_SUMMON_THE_AIRSHIP;
	
	@ClientString(id = 2778, message = "You cannot teleport while in possession of a ward.")
	public static SystemMessageId YOU_CANNOT_TELEPORT_WHILE_IN_POSSESSION_OF_A_WARD;
	
	@ClientString(id = 2779, message = "Further increase in altitude is not allowed.")
	public static SystemMessageId FURTHER_INCREASE_IN_ALTITUDE_IS_NOT_ALLOWED;
	
	@ClientString(id = 2780, message = "Further decrease in altitude is not allowed.")
	public static SystemMessageId FURTHER_DECREASE_IN_ALTITUDE_IS_NOT_ALLOWED;
	
	@ClientString(id = 2781, message = "Number of units: $s1")
	public static SystemMessageId NUMBER_OF_UNITS_S1;
	
	@ClientString(id = 2782, message = "Number of people: $s1")
	public static SystemMessageId NUMBER_OF_PEOPLE_S1;
	
	@ClientString(id = 2783, message = "No one is left from the opposing team, thus victory is yours.")
	public static SystemMessageId NO_ONE_IS_LEFT_FROM_THE_OPPOSING_TEAM_THUS_VICTORY_IS_YOURS;
	
	@ClientString(id = 2784, message = "The battlefield has been closed. The match has ended in a tie because the match lasted for $s1-minute(s) and $s2-second(s) and the requirements were not met.")
	public static SystemMessageId THE_BATTLEFIELD_HAS_BEEN_CLOSED_THE_MATCH_HAS_ENDED_IN_A_TIE_BECAUSE_THE_MATCH_LASTED_FOR_S1_MINUTE_S_AND_S2_SECOND_S_AND_THE_REQUIREMENTS_WERE_NOT_MET;
	
	@ClientString(id = 2785, message = "It's a large scaled airship for transportations and battles and can be owned by the unit of clan.")
	public static SystemMessageId IT_S_A_LARGE_SCALED_AIRSHIP_FOR_TRANSPORTATIONS_AND_BATTLES_AND_CAN_BE_OWNED_BY_THE_UNIT_OF_CLAN;
	
	@ClientString(id = 2786, message = "Start action is available only when controlling the airship.")
	public static SystemMessageId START_ACTION_IS_AVAILABLE_ONLY_WHEN_CONTROLLING_THE_AIRSHIP;
	
	@ClientString(id = 2787, message = "$c1 has drained you of $s2 HP.")
	public static SystemMessageId C1_HAS_DRAINED_YOU_OF_S2_HP;
	
	@ClientString(id = 2788, message = "Mercenary participation is requested in $s1 territory.")
	public static SystemMessageId MERCENARY_PARTICIPATION_IS_REQUESTED_IN_S1_TERRITORY;
	
	@ClientString(id = 2789, message = "Mercenary participation request is cancelled in $s1 territory.")
	public static SystemMessageId MERCENARY_PARTICIPATION_REQUEST_IS_CANCELLED_IN_S1_TERRITORY;
	
	@ClientString(id = 2790, message = "Clan participation is requested in $s1 territory.")
	public static SystemMessageId CLAN_PARTICIPATION_IS_REQUESTED_IN_S1_TERRITORY;
	
	@ClientString(id = 2791, message = "Clan participation request is cancelled in $s1 territory.")
	public static SystemMessageId CLAN_PARTICIPATION_REQUEST_IS_CANCELLED_IN_S1_TERRITORY;
	
	@ClientString(id = 2792, message = "50 clan reputation points will be awarded. Do you wish to continue?")
	public static SystemMessageId FIFTY_CLAN_REPUTATION_POINTS_WILL_BE_AWARDED_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 2793, message = "You must have a minimum of ($s1) people to enter this Instant Zone. Your request for entry is denied.")
	public static SystemMessageId YOU_MUST_HAVE_A_MINIMUM_OF_S1_PEOPLE_TO_ENTER_THIS_INSTANT_ZONE_YOUR_REQUEST_FOR_ENTRY_IS_DENIED;
	
	@ClientString(id = 2794, message = "The territory war channel and functions will now be deactivated.")
	public static SystemMessageId THE_TERRITORY_WAR_CHANNEL_AND_FUNCTIONS_WILL_NOW_BE_DEACTIVATED;
	
	@ClientString(id = 2795, message = "You've already requested a territory war in another territory elsewhere.")
	public static SystemMessageId YOU_VE_ALREADY_REQUESTED_A_TERRITORY_WAR_IN_ANOTHER_TERRITORY_ELSEWHERE;
	
	@ClientString(id = 2796, message = "The clan who owns the territory cannot participate in the territory war as mercenaries.")
	public static SystemMessageId THE_CLAN_WHO_OWNS_THE_TERRITORY_CANNOT_PARTICIPATE_IN_THE_TERRITORY_WAR_AS_MERCENARIES;
	
	@ClientString(id = 2797, message = "It is not a territory war registration period, so a request cannot be made at this time.")
	public static SystemMessageId IT_IS_NOT_A_TERRITORY_WAR_REGISTRATION_PERIOD_SO_A_REQUEST_CANNOT_BE_MADE_AT_THIS_TIME;
	
	@ClientString(id = 2798, message = "The territory war will end in $s1-hour(s).")
	public static SystemMessageId THE_TERRITORY_WAR_WILL_END_IN_S1_HOUR_S;
	
	@ClientString(id = 2799, message = "The territory war will end in $s1-minute(s).")
	public static SystemMessageId THE_TERRITORY_WAR_WILL_END_IN_S1_MINUTE_S;
	
	@ClientString(id = 2800, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_5;
	
	@ClientString(id = 2801, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_6;
	
	@ClientString(id = 2802, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_7;
	
	@ClientString(id = 2803, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_8;
	
	@ClientString(id = 2804, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_9;
	
	@ClientString(id = 2805, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_10;
	
	@ClientString(id = 2806, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_11;
	
	@ClientString(id = 2807, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_12;
	
	@ClientString(id = 2808, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_13;
	
	@ClientString(id = 2809, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_14;
	
	@ClientString(id = 2810, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_15;
	
	@ClientString(id = 2811, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_16;
	
	@ClientString(id = 2812, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_17;
	
	@ClientString(id = 2813, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_18;
	
	@ClientString(id = 2814, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_19;
	
	@ClientString(id = 2815, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_20;
	
	@ClientString(id = 2816, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 1 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_1_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2817, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 2 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_2_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2818, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 3 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_3_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2819, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 4 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_4_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2820, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 5 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_5_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2821, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 6 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_6_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2822, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 7 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_7_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2823, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 8 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_8_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2824, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 9 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_9_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2825, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 10 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_10_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2826, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 11 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_11_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2827, message = "Designate a shortcut key for the Flying Transformed Object Exclusive use shortcut window's No 12 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_12_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2828, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 1 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_1_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2829, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 2 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_2_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2830, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 3 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_3_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2831, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 4 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_4_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2832, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 5 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_5_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2833, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 6 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_6_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2834, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 7 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_7_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2835, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 8 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_8_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2836, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 9 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_9_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2837, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 10 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_10_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2838, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 11 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_11_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2839, message = "Designate a shortcut key for the Mountable Exclusive use shortcut window's No 12 slot. The CTRL and SHIFT keys cannot be designated.")
	public static SystemMessageId DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_12_SLOT_THE_CTRL_AND_SHIFT_KEYS_CANNOT_BE_DESIGNATED;
	
	@ClientString(id = 2840, message = "Execute the designated shortcut's action/skill/macro.")
	public static SystemMessageId EXECUTE_THE_DESIGNATED_SHORTCUT_S_ACTION_SKILL_MACRO;
	
	@ClientString(id = 2841, message = "Raise my character to the top.")
	public static SystemMessageId RAISE_MY_CHARACTER_TO_THE_TOP;
	
	@ClientString(id = 2842, message = "Lower my character to the bottom.")
	public static SystemMessageId LOWER_MY_CHARACTER_TO_THE_BOTTOM;
	
	@ClientString(id = 2843, message = "Raise the controlled mountable to the top.")
	public static SystemMessageId RAISE_THE_CONTROLLED_MOUNTABLE_TO_THE_TOP;
	
	@ClientString(id = 2844, message = "Lower the controlled mountable to the bottom.")
	public static SystemMessageId LOWER_THE_CONTROLLED_MOUNTABLE_TO_THE_BOTTOM;
	
	@ClientString(id = 2845, message = "Automatically send forward my character or mountable.")
	public static SystemMessageId AUTOMATICALLY_SEND_FORWARD_MY_CHARACTER_OR_MOUNTABLE;
	
	@ClientString(id = 2846, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_21;
	
	@ClientString(id = 2847, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_22;
	
	@ClientString(id = 2848, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_23;
	
	@ClientString(id = 2849, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_24;
	
	@ClientString(id = 2850, message = "No translation required")
	public static SystemMessageId NO_TRANSLATION_REQUIRED_25;
	
	@ClientString(id = 2851, message = "Stop all actions of my character.")
	public static SystemMessageId STOP_ALL_ACTIONS_OF_MY_CHARACTER;
	
	@ClientString(id = 2852, message = "Stop all actions of my controlled mountable.")
	public static SystemMessageId STOP_ALL_ACTIONS_OF_MY_CONTROLLED_MOUNTABLE;
	
	@ClientString(id = 2875, message = "If you join the clan academy, you can become a clan member and learn the game system until you become level 40. If you want more fun, we recommend that you join the clan academy.")
	public static SystemMessageId IF_YOU_JOIN_THE_CLAN_ACADEMY_YOU_CAN_BECOME_A_CLAN_MEMBER_AND_LEARN_THE_GAME_SYSTEM_UNTIL_YOU_BECOME_LEVEL_40_IF_YOU_WANT_MORE_FUN_WE_RECOMMEND_THAT_YOU_JOIN_THE_CLAN_ACADEMY;
	
	@ClientString(id = 2876, message = "If you become level 40, the second class change is available. If you complete the second class change, the character's capability is enhanced.")
	public static SystemMessageId IF_YOU_BECOME_LEVEL_40_THE_SECOND_CLASS_CHANGE_IS_AVAILABLE_IF_YOU_COMPLETE_THE_SECOND_CLASS_CHANGE_THE_CHARACTER_S_CAPABILITY_IS_ENHANCED;
	
	@ClientString(id = 2900, message = "$s1-second(s) to the end of territory war!")
	public static SystemMessageId S1_SECOND_S_TO_THE_END_OF_TERRITORY_WAR;
	
	@ClientString(id = 2901, message = "You cannot force attack a member of the same territory.")
	public static SystemMessageId YOU_CANNOT_FORCE_ATTACK_A_MEMBER_OF_THE_SAME_TERRITORY;
	
	@ClientString(id = 2902, message = "You've acquired the ward. Move quickly to your forces' outpost.")
	public static SystemMessageId YOU_VE_ACQUIRED_THE_WARD_MOVE_QUICKLY_TO_YOUR_FORCES_OUTPOST;
	
	@ClientString(id = 2903, message = "Territory war has begun.")
	public static SystemMessageId TERRITORY_WAR_HAS_BEGUN;
	
	@ClientString(id = 2904, message = "Territory war has ended.")
	public static SystemMessageId TERRITORY_WAR_HAS_ENDED;
	
	@ClientString(id = 2905, message = "Altitude cannot be decreased any further.")
	public static SystemMessageId ALTITUDE_CANNOT_BE_DECREASED_ANY_FURTHER;
	
	@ClientString(id = 2906, message = "Altitude cannot be increased any further.")
	public static SystemMessageId ALTITUDE_CANNOT_BE_INCREASED_ANY_FURTHER;
	
	@ClientString(id = 2907, message = "You have entered a potentially hostile environment so the airship's speed has been greatly decreased.")
	public static SystemMessageId YOU_HAVE_ENTERED_A_POTENTIALLY_HOSTILE_ENVIRONMENT_SO_THE_AIRSHIP_S_SPEED_HAS_BEEN_GREATLY_DECREASED;
	
	@ClientString(id = 2908, message = "As you are leaving the hostile environment, the airship's speed has been returned to normal.")
	public static SystemMessageId AS_YOU_ARE_LEAVING_THE_HOSTILE_ENVIRONMENT_THE_AIRSHIP_S_SPEED_HAS_BEEN_RETURNED_TO_NORMAL;
	
	@ClientString(id = 2909, message = "A servitor or pet cannot be summoned while on an airship.")
	public static SystemMessageId A_SERVITOR_OR_PET_CANNOT_BE_SUMMONED_WHILE_ON_AN_AIRSHIP;
	
	@ClientString(id = 2910, message = "You have entered an incorrect command.")
	public static SystemMessageId YOU_HAVE_ENTERED_AN_INCORRECT_COMMAND;
	
	@ClientString(id = 2911, message = "You've requested $c1 to be on your Friends List.")
	public static SystemMessageId YOU_VE_REQUESTED_C1_TO_BE_ON_YOUR_FRIENDS_LIST;
	
	@ClientString(id = 2912, message = "You've invited $c1 to join your clan.")
	public static SystemMessageId YOU_VE_INVITED_C1_TO_JOIN_YOUR_CLAN;
	
	@ClientString(id = 2913, message = "Clan $s1 has succeeded in capturing $s2's territory ward.")
	public static SystemMessageId CLAN_S1_HAS_SUCCEEDED_IN_CAPTURING_S2_S_TERRITORY_WARD;
	
	@ClientString(id = 2914, message = "The territory war will begin in 20 minutes! Territory related functions (i.e.: battlefield channel, Disguise Scrolls, Transformations, etc...) can now be used.")
	public static SystemMessageId THE_TERRITORY_WAR_WILL_BEGIN_IN_20_MINUTES_TERRITORY_RELATED_FUNCTIONS_I_E_BATTLEFIELD_CHANNEL_DISGUISE_SCROLLS_TRANSFORMATIONS_ETC_CAN_NOW_BE_USED;
	
	@ClientString(id = 2915, message = "This clan member cannot withdraw or be expelled while participating in a territory war.")
	public static SystemMessageId THIS_CLAN_MEMBER_CANNOT_WITHDRAW_OR_BE_EXPELLED_WHILE_PARTICIPATING_IN_A_TERRITORY_WAR;
	
	@ClientString(id = 2916, message = "$s1 in battle")
	public static SystemMessageId S1_IN_BATTLE;
	
	@ClientString(id = 2917, message = "Territories are at peace.")
	public static SystemMessageId TERRITORIES_ARE_AT_PEACE;
	
	@ClientString(id = 2918, message = "Only characters who are level 40 or above who have completed their second class transfer can register in a territory war.")
	public static SystemMessageId ONLY_CHARACTERS_WHO_ARE_LEVEL_40_OR_ABOVE_WHO_HAVE_COMPLETED_THEIR_SECOND_CLASS_TRANSFER_CAN_REGISTER_IN_A_TERRITORY_WAR;
	
	@ClientString(id = 2919, message = "While disguised, you cannot operate a private or manufacture store.")
	public static SystemMessageId WHILE_DISGUISED_YOU_CANNOT_OPERATE_A_PRIVATE_OR_MANUFACTURE_STORE;
	
	@ClientString(id = 2920, message = "No more airships can be summoned as the maximum airship limit has been met.")
	public static SystemMessageId NO_MORE_AIRSHIPS_CAN_BE_SUMMONED_AS_THE_MAXIMUM_AIRSHIP_LIMIT_HAS_BEEN_MET;
	
	@ClientString(id = 2921, message = "You cannot board the airship because the maximum number for occupants is met.")
	public static SystemMessageId YOU_CANNOT_BOARD_THE_AIRSHIP_BECAUSE_THE_MAXIMUM_NUMBER_FOR_OCCUPANTS_IS_MET;
	
	@ClientString(id = 2922, message = "Block Checker will end in 5 seconds!")
	public static SystemMessageId BLOCK_CHECKER_WILL_END_IN_5_SECONDS;
	
	@ClientString(id = 2923, message = "Block Checker will end in 4 seconds!!")
	public static SystemMessageId BLOCK_CHECKER_WILL_END_IN_4_SECONDS;
	
	@ClientString(id = 2924, message = "You cannot enter a Seed while in a flying transformation state.")
	public static SystemMessageId YOU_CANNOT_ENTER_A_SEED_WHILE_IN_A_FLYING_TRANSFORMATION_STATE;
	
	@ClientString(id = 2925, message = "Block Checker will end in 3 seconds!!!")
	public static SystemMessageId BLOCK_CHECKER_WILL_END_IN_3_SECONDS;
	
	@ClientString(id = 2926, message = "Block Checker will end in 2 seconds!!!!")
	public static SystemMessageId BLOCK_CHECKER_WILL_END_IN_2_SECONDS;
	
	@ClientString(id = 2927, message = "Block Checker will end in 1 second!!!!!")
	public static SystemMessageId BLOCK_CHECKER_WILL_END_IN_1_SECOND;
	
	@ClientString(id = 2928, message = "The $c1 team has won.")
	public static SystemMessageId THE_C1_TEAM_HAS_WON;
	
	@ClientString(id = 2929, message = "Your request cannot be processed because there's no enough available memory on your graphic card. Please try again after reducing the resolution.")
	public static SystemMessageId YOUR_REQUEST_CANNOT_BE_PROCESSED_BECAUSE_THERE_S_NO_ENOUGH_AVAILABLE_MEMORY_ON_YOUR_GRAPHIC_CARD_PLEASE_TRY_AGAIN_AFTER_REDUCING_THE_RESOLUTION;
	
	@ClientString(id = 2930, message = "A graphic card internal error has occurred. Please install the latest version of the graphic card driver and try again.")
	public static SystemMessageId A_GRAPHIC_CARD_INTERNAL_ERROR_HAS_OCCURRED_PLEASE_INSTALL_THE_LATEST_VERSION_OF_THE_GRAPHIC_CARD_DRIVER_AND_TRY_AGAIN;
	
	@ClientString(id = 2931, message = "The system file may have been damaged. After ending the game, please check the file using the Lineage II auto update.")
	public static SystemMessageId THE_SYSTEM_FILE_MAY_HAVE_BEEN_DAMAGED_AFTER_ENDING_THE_GAME_PLEASE_CHECK_THE_FILE_USING_THE_LINEAGE_II_AUTO_UPDATE;
	
	@ClientString(id = 2932, message = "$s1 adena")
	public static SystemMessageId S1_ADENA;
	
	@ClientString(id = 2933, message = "Thomas D. Turkey has appeared. Please save Santa.")
	public static SystemMessageId THOMAS_D_TURKEY_HAS_APPEARED_PLEASE_SAVE_SANTA;
	
	@ClientString(id = 2934, message = "You have defeated Thomas D. Turkey and rescued Santa.")
	public static SystemMessageId YOU_HAVE_DEFEATED_THOMAS_D_TURKEY_AND_RESCUED_SANTA;
	
	@ClientString(id = 2935, message = "You failed to rescue Santa, and Thomas D. Turkey has disappeared.")
	public static SystemMessageId YOU_FAILED_TO_RESCUE_SANTA_AND_THOMAS_D_TURKEY_HAS_DISAPPEARED;
	
	@ClientString(id = 2936, message = "The disguise scroll cannot be used because it is meant for use in a different territory.")
	public static SystemMessageId THE_DISGUISE_SCROLL_CANNOT_BE_USED_BECAUSE_IT_IS_MEANT_FOR_USE_IN_A_DIFFERENT_TERRITORY;
	
	@ClientString(id = 2937, message = "A territory owning clan member cannot use a disguise scroll.")
	public static SystemMessageId A_TERRITORY_OWNING_CLAN_MEMBER_CANNOT_USE_A_DISGUISE_SCROLL;
	
	@ClientString(id = 2938, message = "The disguise scroll cannot be used while you are engaged in a private store or manufacture workshop.")
	public static SystemMessageId THE_DISGUISE_SCROLL_CANNOT_BE_USED_WHILE_YOU_ARE_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE_WORKSHOP;
	
	@ClientString(id = 2939, message = "A disguise cannot be used when you are in a chaotic state.")
	public static SystemMessageId A_DISGUISE_CANNOT_BE_USED_WHEN_YOU_ARE_IN_A_CHAOTIC_STATE;
	
	@ClientString(id = 2940, message = "+3 to +9 enchant success can be improved with special items.")
	public static SystemMessageId THREE_TO_9_ENCHANT_SUCCESS_CAN_BE_IMPROVED_WITH_SPECIAL_ITEMS;
	
	@ClientString(id = 2941, message = "The request cannot be completed because the requirements are not met. In order to participate in a team match, all team members must have an Olympiad score of 1 or more.")
	public static SystemMessageId THE_REQUEST_CANNOT_BE_COMPLETED_BECAUSE_THE_REQUIREMENTS_ARE_NOT_MET_IN_ORDER_TO_PARTICIPATE_IN_A_TEAM_MATCH_ALL_TEAM_MEMBERS_MUST_HAVE_AN_OLYMPIAD_SCORE_OF_1_OR_MORE;
	
	@ClientString(id = 2942, message = "The first gift's remaining resupply time is $s1 hour(s) $s2 minute(s) $s3 second(s). (If you resummon the Agathion at the gift supply time, the supply time can take an additional 10 minutes.)")
	public static SystemMessageId THE_FIRST_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_HOUR_S_S2_MINUTE_S_S3_SECOND_S_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT_SUPPLY_TIME_THE_SUPPLY_TIME_CAN_TAKE_AN_ADDITIONAL_10_MINUTES;
	
	@ClientString(id = 2943, message = "The first gift's remaining resupply time is $s1 minute(s) $s2 second(s). (If you resummon the Agathion at the gift supply time, the supply time can take an additional 10 minutes.)")
	public static SystemMessageId THE_FIRST_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_MINUTE_S_S2_SECOND_S_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT_SUPPLY_TIME_THE_SUPPLY_TIME_CAN_TAKE_AN_ADDITIONAL_10_MINUTES;
	
	@ClientString(id = 2944, message = "The first gift's remaining resupply time is $s1 second(s). (If you resummon the Agathion at the gift supply time, the supply time can take an additional 10 minutes.)")
	public static SystemMessageId THE_FIRST_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_SECOND_S_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT_SUPPLY_TIME_THE_SUPPLY_TIME_CAN_TAKE_AN_ADDITIONAL_10_MINUTES;
	
	@ClientString(id = 2945, message = "The second gift's remaining resupply time is $s1 hour(s) $s2 minute(s) $s3 second(s). (If you resummon the Agathion at the gift supply time, the supply time can take an additional 1 hour 10 minutes.)")
	public static SystemMessageId THE_SECOND_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_HOUR_S_S2_MINUTE_S_S3_SECOND_S_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT_SUPPLY_TIME_THE_SUPPLY_TIME_CAN_TAKE_AN_ADDITIONAL_1_HOUR_10_MINUTES;
	
	@ClientString(id = 2946, message = "The second gift's remaining resupply time is $s1 minute(s) $s2 second(s). (If you resummon the Agathion at the gift supply time, the supply time can take an additional 1 hour 10 minutes.)")
	public static SystemMessageId THE_SECOND_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_MINUTE_S_S2_SECOND_S_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT_SUPPLY_TIME_THE_SUPPLY_TIME_CAN_TAKE_AN_ADDITIONAL_1_HOUR_10_MINUTES;
	
	@ClientString(id = 2947, message = "The second gift's remaining resupply time is $s1 second(s). (If you resummon the Agathion at the gift supply time, the supply time can take an additional 1 hour 10 minutes.)")
	public static SystemMessageId THE_SECOND_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_SECOND_S_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT_SUPPLY_TIME_THE_SUPPLY_TIME_CAN_TAKE_AN_ADDITIONAL_1_HOUR_10_MINUTES;
	
	@ClientString(id = 2955, message = "The territory war exclusive disguise and transformation can be used 20 minutes before the start of the territory war to 10 minutes after its end.")
	public static SystemMessageId THE_TERRITORY_WAR_EXCLUSIVE_DISGUISE_AND_TRANSFORMATION_CAN_BE_USED_20_MINUTES_BEFORE_THE_START_OF_THE_TERRITORY_WAR_TO_10_MINUTES_AFTER_ITS_END;
	
	@ClientString(id = 2956, message = "A user participating in the Olympiad cannot witness the battle.")
	public static SystemMessageId A_USER_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_WITNESS_THE_BATTLE;
	
	@ClientString(id = 2957, message = "A character born on February 29 will receive a gift on February 28.")
	public static SystemMessageId A_CHARACTER_BORN_ON_FEBRUARY_29_WILL_RECEIVE_A_GIFT_ON_FEBRUARY_28;
	
	@ClientString(id = 2958, message = "An Agathion has already been summoned.")
	public static SystemMessageId AN_AGATHION_HAS_ALREADY_BEEN_SUMMONED;
	
	@ClientString(id = 2959, message = "Your account has been temporarily suspended for playing the game in abnormal ways. If you did not use abnormal means, please visit the Support Center on the NCsoft website (http://us.ncsoft.com/support).")
	public static SystemMessageId YOUR_ACCOUNT_HAS_BEEN_TEMPORARILY_SUSPENDED_FOR_PLAYING_THE_GAME_IN_ABNORMAL_WAYS_IF_YOU_DID_NOT_USE_ABNORMAL_MEANS_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_NCSOFT_WEBSITE_HTTP_US_NCSOFT_COM_SUPPORT;
	
	@ClientString(id = 2960, message = "The item $s1 is required.")
	public static SystemMessageId THE_ITEM_S1_IS_REQUIRED;
	
	@ClientString(id = 2961, message = "$s2 unit(s) of the item $s1 is/are required.")
	public static SystemMessageId S2_UNIT_S_OF_THE_ITEM_S1_IS_ARE_REQUIRED;
	
	@ClientString(id = 2962, message = "This item cannot be used in the current transformation state.")
	public static SystemMessageId THIS_ITEM_CANNOT_BE_USED_IN_THE_CURRENT_TRANSFORMATION_STATE;
	
	@ClientString(id = 2963, message = "The opponent has not equipped $s1, so $s2 cannot be used.")
	public static SystemMessageId THE_OPPONENT_HAS_NOT_EQUIPPED_S1_SO_S2_CANNOT_BE_USED;
	
	@ClientString(id = 2964, message = "Being appointed as a Noblesse will cancel all related quests. Do you wish to continue?")
	public static SystemMessageId BEING_APPOINTED_AS_A_NOBLESSE_WILL_CANCEL_ALL_RELATED_QUESTS_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 2965, message = "You cannot purchase and re-purchase the same type of item at the same time.")
	public static SystemMessageId YOU_CANNOT_PURCHASE_AND_RE_PURCHASE_THE_SAME_TYPE_OF_ITEM_AT_THE_SAME_TIME;
	
	@ClientString(id = 2966, message = "It's a Payment Request transaction. Please attach the item.")
	public static SystemMessageId IT_S_A_PAYMENT_REQUEST_TRANSACTION_PLEASE_ATTACH_THE_ITEM;
	
	@ClientString(id = 2967, message = "You are attempting to send mail. Do you wish to proceed?")
	public static SystemMessageId YOU_ARE_ATTEMPTING_TO_SEND_MAIL_DO_YOU_WISH_TO_PROCEED;
	
	@ClientString(id = 2968, message = "The mail limit (240) has been exceeded and this cannot be forwarded.")
	public static SystemMessageId THE_MAIL_LIMIT_240_HAS_BEEN_EXCEEDED_AND_THIS_CANNOT_BE_FORWARDED;
	
	@ClientString(id = 2969, message = "The previous mail was forwarded less than 1 minute ago and this cannot be forwarded.")
	public static SystemMessageId THE_PREVIOUS_MAIL_WAS_FORWARDED_LESS_THAN_1_MINUTE_AGO_AND_THIS_CANNOT_BE_FORWARDED;
	
	@ClientString(id = 2970, message = "You cannot forward in a non-peace zone location.")
	public static SystemMessageId YOU_CANNOT_FORWARD_IN_A_NON_PEACE_ZONE_LOCATION;
	
	@ClientString(id = 2971, message = "You cannot forward during an exchange.")
	public static SystemMessageId YOU_CANNOT_FORWARD_DURING_AN_EXCHANGE;
	
	@ClientString(id = 2972, message = "You cannot forward because the private shop or workshop is in progress.")
	public static SystemMessageId YOU_CANNOT_FORWARD_BECAUSE_THE_PRIVATE_SHOP_OR_WORKSHOP_IS_IN_PROGRESS;
	
	@ClientString(id = 2973, message = "You cannot forward during an item enhancement or attribute enhancement.")
	public static SystemMessageId YOU_CANNOT_FORWARD_DURING_AN_ITEM_ENHANCEMENT_OR_ATTRIBUTE_ENHANCEMENT;
	
	@ClientString(id = 2974, message = "The item that you're trying to send cannot be forwarded because it isn't proper.")
	public static SystemMessageId THE_ITEM_THAT_YOU_RE_TRYING_TO_SEND_CANNOT_BE_FORWARDED_BECAUSE_IT_ISN_T_PROPER;
	
	@ClientString(id = 2975, message = "You cannot forward because you don't have enough adena.")
	public static SystemMessageId YOU_CANNOT_FORWARD_BECAUSE_YOU_DON_T_HAVE_ENOUGH_ADENA;
	
	@ClientString(id = 2976, message = "You cannot receive in a non-peace zone location.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_IN_A_NON_PEACE_ZONE_LOCATION;
	
	@ClientString(id = 2977, message = "You cannot receive during an exchange.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_DURING_AN_EXCHANGE;
	
	@ClientString(id = 2978, message = "You cannot receive because the private shop or workshop is in progress.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_BECAUSE_THE_PRIVATE_SHOP_OR_WORKSHOP_IS_IN_PROGRESS;
	
	@ClientString(id = 2979, message = "You cannot receive during an item enhancement or attribute enhancement.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_DURING_AN_ITEM_ENHANCEMENT_OR_ATTRIBUTE_ENHANCEMENT;
	
	@ClientString(id = 2980, message = "You cannot receive because you don't have enough adena.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_BECAUSE_YOU_DON_T_HAVE_ENOUGH_ADENA;
	
	@ClientString(id = 2981, message = "You could not receive because your inventory is full.")
	public static SystemMessageId YOU_COULD_NOT_RECEIVE_BECAUSE_YOUR_INVENTORY_IS_FULL;
	
	@ClientString(id = 2982, message = "You cannot cancel in a non-peace zone location.")
	public static SystemMessageId YOU_CANNOT_CANCEL_IN_A_NON_PEACE_ZONE_LOCATION;
	
	@ClientString(id = 2983, message = "You cannot cancel during an exchange.")
	public static SystemMessageId YOU_CANNOT_CANCEL_DURING_AN_EXCHANGE;
	
	@ClientString(id = 2984, message = "You cannot cancel because the private shop or workshop is in progress.")
	public static SystemMessageId YOU_CANNOT_CANCEL_BECAUSE_THE_PRIVATE_SHOP_OR_WORKSHOP_IS_IN_PROGRESS;
	
	@ClientString(id = 2985, message = "You cannot cancel during an item enhancement or attribute enhancement.")
	public static SystemMessageId YOU_CANNOT_CANCEL_DURING_AN_ITEM_ENHANCEMENT_OR_ATTRIBUTE_ENHANCEMENT;
	
	@ClientString(id = 2986, message = "Please set the amount of adena to send.")
	public static SystemMessageId PLEASE_SET_THE_AMOUNT_OF_ADENA_TO_SEND;
	
	@ClientString(id = 2987, message = "Please set the amount of adena to receive.")
	public static SystemMessageId PLEASE_SET_THE_AMOUNT_OF_ADENA_TO_RECEIVE;
	
	@ClientString(id = 2988, message = "You could not cancel receipt because your inventory is full.")
	public static SystemMessageId YOU_COULD_NOT_CANCEL_RECEIPT_BECAUSE_YOUR_INVENTORY_IS_FULL;
	
	@ClientString(id = 2989, message = "Dimensional Item $s1 is being used.")
	public static SystemMessageId DIMENSIONAL_ITEM_S1_IS_BEING_USED;
	
	@ClientString(id = 2990, message = "$2 units of Dimensional Item $s1 was consumed.")
	public static SystemMessageId TWO_UNITS_OF_DIMENSIONAL_ITEM_S1_WAS_CONSUMED;
	
	@ClientString(id = 2991, message = "True input must be entered by someone over 15 years old.")
	public static SystemMessageId TRUE_INPUT_MUST_BE_ENTERED_BY_SOMEONE_OVER_15_YEARS_OLD;
	
	@ClientString(id = 2992, message = "Please choose the 2nd stage type.")
	public static SystemMessageId PLEASE_CHOOSE_THE_2ND_STAGE_TYPE;
	
	@ClientString(id = 2993, message = "If the Command Channel leader leaves the party matching room, then the sessions ends. Do you really wish to exit the room?")
	public static SystemMessageId IF_THE_COMMAND_CHANNEL_LEADER_LEAVES_THE_PARTY_MATCHING_ROOM_THEN_THE_SESSIONS_ENDS_DO_YOU_REALLY_WISH_TO_EXIT_THE_ROOM;
	
	@ClientString(id = 2994, message = "The Command Channel matching room was cancelled.")
	public static SystemMessageId THE_COMMAND_CHANNEL_MATCHING_ROOM_WAS_CANCELLED;
	
	@ClientString(id = 2995, message = "This Command Channel matching room is already cancelled.")
	public static SystemMessageId THIS_COMMAND_CHANNEL_MATCHING_ROOM_IS_ALREADY_CANCELLED;
	
	@ClientString(id = 2996, message = "You cannot enter the Command Channel matching room because you do not meet the requirements.")
	public static SystemMessageId YOU_CANNOT_ENTER_THE_COMMAND_CHANNEL_MATCHING_ROOM_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS;
	
	@ClientString(id = 2997, message = "You exited from the Command Channel matching room.")
	public static SystemMessageId YOU_EXITED_FROM_THE_COMMAND_CHANNEL_MATCHING_ROOM;
	
	@ClientString(id = 2998, message = "You were expelled from the Command Channel matching room.")
	public static SystemMessageId YOU_WERE_EXPELLED_FROM_THE_COMMAND_CHANNEL_MATCHING_ROOM;
	
	@ClientString(id = 2999, message = "The Command Channel affiliated party's party member cannot use the matching screen.")
	public static SystemMessageId THE_COMMAND_CHANNEL_AFFILIATED_PARTY_S_PARTY_MEMBER_CANNOT_USE_THE_MATCHING_SCREEN;
	
	@ClientString(id = 3000, message = "The Command Channel matching room was created.")
	public static SystemMessageId THE_COMMAND_CHANNEL_MATCHING_ROOM_WAS_CREATED;
	
	@ClientString(id = 3001, message = "The Command Channel matching room information was edited.")
	public static SystemMessageId THE_COMMAND_CHANNEL_MATCHING_ROOM_INFORMATION_WAS_EDITED;
	
	@ClientString(id = 3002, message = "When the recipient doesn't exist or the character has been deleted, sending mail is not possible.")
	public static SystemMessageId WHEN_THE_RECIPIENT_DOESN_T_EXIST_OR_THE_CHARACTER_HAS_BEEN_DELETED_SENDING_MAIL_IS_NOT_POSSIBLE;
	
	@ClientString(id = 3003, message = "$c1 entered the Command Channel matching room.")
	public static SystemMessageId C1_ENTERED_THE_COMMAND_CHANNEL_MATCHING_ROOM;
	
	@ClientString(id = 3004, message = "I'm sorry to give you a satisfactory response.\\n\\nIf you send your comments regarding the unsatisfying parts, we will be able to provide even greater service.\\n\\nPlease send us your comments.")
	public static SystemMessageId I_M_SORRY_TO_GIVE_YOU_A_SATISFACTORY_RESPONSE_N_NIF_YOU_SEND_YOUR_COMMENTS_REGARDING_THE_UNSATISFYING_PARTS_WE_WILL_BE_ABLE_TO_PROVIDE_EVEN_GREATER_SERVICE_N_NPLEASE_SEND_US_YOUR_COMMENTS;
	
	@ClientString(id = 3005, message = "This skill cannot be enhanced.")
	public static SystemMessageId THIS_SKILL_CANNOT_BE_ENHANCED;
	
	@ClientString(id = 3006, message = "Newly used PC cafe $s1 points were withdrawn.")
	public static SystemMessageId NEWLY_USED_PC_CAFE_S1_POINTS_WERE_WITHDRAWN;
	
	@ClientString(id = 3007, message = "Shyeed's roar filled with wrath rings throughout the Stakato Nest.")
	public static SystemMessageId SHYEED_S_ROAR_FILLED_WITH_WRATH_RINGS_THROUGHOUT_THE_STAKATO_NEST;
	
	@ClientString(id = 3008, message = "The mail has arrived.")
	public static SystemMessageId THE_MAIL_HAS_ARRIVED;
	
	@ClientString(id = 3009, message = "Mail successfully sent.")
	public static SystemMessageId MAIL_SUCCESSFULLY_SENT;
	
	@ClientString(id = 3010, message = "Mail successfully returned.")
	public static SystemMessageId MAIL_SUCCESSFULLY_RETURNED;
	
	@ClientString(id = 3011, message = "Mail successfully cancelled.")
	public static SystemMessageId MAIL_SUCCESSFULLY_CANCELLED;
	
	@ClientString(id = 3012, message = "Mail successfully received.")
	public static SystemMessageId MAIL_SUCCESSFULLY_RECEIVED;
	
	@ClientString(id = 3013, message = "$c1 has successfully enchanted a +$s2 $s3.")
	public static SystemMessageId C1_HAS_SUCCESSFULLY_ENCHANTED_A_S2_S3;
	
	@ClientString(id = 3014, message = "Do you wish to erase the selected mail?")
	public static SystemMessageId DO_YOU_WISH_TO_ERASE_THE_SELECTED_MAIL;
	
	@ClientString(id = 3015, message = "Please select the mail to be deleted.")
	public static SystemMessageId PLEASE_SELECT_THE_MAIL_TO_BE_DELETED;
	
	@ClientString(id = 3016, message = "Item selection is possible up to 8.")
	public static SystemMessageId ITEM_SELECTION_IS_POSSIBLE_UP_TO_8;
	
	@ClientString(id = 3017, message = "You cannot use any skill enhancing system under your status. Check out the PC's current status.")
	public static SystemMessageId YOU_CANNOT_USE_ANY_SKILL_ENHANCING_SYSTEM_UNDER_YOUR_STATUS_CHECK_OUT_THE_PC_S_CURRENT_STATUS;
	
	@ClientString(id = 3018, message = "You cannot use skill enhancing system functions for the skills currently not acquired.")
	public static SystemMessageId YOU_CANNOT_USE_SKILL_ENHANCING_SYSTEM_FUNCTIONS_FOR_THE_SKILLS_CURRENTLY_NOT_ACQUIRED;
	
	@ClientString(id = 3019, message = "You cannot send a mail to yourself.")
	public static SystemMessageId YOU_CANNOT_SEND_A_MAIL_TO_YOURSELF;
	
	@ClientString(id = 3020, message = "When not entering the amount for the payment request, you cannot send any mail.")
	public static SystemMessageId WHEN_NOT_ENTERING_THE_AMOUNT_FOR_THE_PAYMENT_REQUEST_YOU_CANNOT_SEND_ANY_MAIL;
	
	@ClientString(id = 3021, message = "Stand-by for the game to begin")
	public static SystemMessageId STAND_BY_FOR_THE_GAME_TO_BEGIN;
	
	@ClientString(id = 3022, message = "The Kasha's Eye gives you a strange feeling.")
	public static SystemMessageId THE_KASHA_S_EYE_GIVES_YOU_A_STRANGE_FEELING;
	
	@ClientString(id = 3023, message = "I can feel that the energy being flown in the Kasha's eye is getting stronger rapidly.")
	public static SystemMessageId I_CAN_FEEL_THAT_THE_ENERGY_BEING_FLOWN_IN_THE_KASHA_S_EYE_IS_GETTING_STRONGER_RAPIDLY;
	
	@ClientString(id = 3024, message = "Kasha's eye pitches and tosses like it's about to explode.")
	public static SystemMessageId KASHA_S_EYE_PITCHES_AND_TOSSES_LIKE_IT_S_ABOUT_TO_EXPLODE;
	
	@ClientString(id = 3025, message = "$s2 has made a payment of $s1 Adena per your payment request mail.")
	public static SystemMessageId S2_HAS_MADE_A_PAYMENT_OF_S1_ADENA_PER_YOUR_PAYMENT_REQUEST_MAIL;
	
	@ClientString(id = 3026, message = "You cannot use the skill enhancing function on this level. You can use the corresponding function on levels higher than 76Lv .")
	public static SystemMessageId YOU_CANNOT_USE_THE_SKILL_ENHANCING_FUNCTION_ON_THIS_LEVEL_YOU_CAN_USE_THE_CORRESPONDING_FUNCTION_ON_LEVELS_HIGHER_THAN_76LV;
	
	@ClientString(id = 3027, message = "You cannot use the skill enhancing function in this class. You can use corresponding function when completing the third class change.")
	public static SystemMessageId YOU_CANNOT_USE_THE_SKILL_ENHANCING_FUNCTION_IN_THIS_CLASS_YOU_CAN_USE_CORRESPONDING_FUNCTION_WHEN_COMPLETING_THE_THIRD_CLASS_CHANGE;
	
	@ClientString(id = 3028, message = "You cannot use the skill enhancing function in this class. You can use the skill enhancing function under off-battle status, and cannot use the function while transforming, battling and on-board.")
	public static SystemMessageId YOU_CANNOT_USE_THE_SKILL_ENHANCING_FUNCTION_IN_THIS_CLASS_YOU_CAN_USE_THE_SKILL_ENHANCING_FUNCTION_UNDER_OFF_BATTLE_STATUS_AND_CANNOT_USE_THE_FUNCTION_WHILE_TRANSFORMING_BATTLING_AND_ON_BOARD;
	
	@ClientString(id = 3029, message = "$s1 returned the mail.")
	public static SystemMessageId S1_RETURNED_THE_MAIL;
	
	@ClientString(id = 3030, message = "You cannot cancel sent mail since the recipient received it.")
	public static SystemMessageId YOU_CANNOT_CANCEL_SENT_MAIL_SINCE_THE_RECIPIENT_RECEIVED_IT;
	
	@ClientString(id = 3031, message = "By using the skill of Einhasad's holy sword, defeat the evil Lilims!")
	public static SystemMessageId BY_USING_THE_SKILL_OF_EINHASAD_S_HOLY_SWORD_DEFEAT_THE_EVIL_LILIMS;
	
	@ClientString(id = 3032, message = "In order to help Anakim, activate the sealing device of the Emperor who is possessed by the evil magical curse! Magical curse is very powerful, so we must be careful!")
	public static SystemMessageId IN_ORDER_TO_HELP_ANAKIM_ACTIVATE_THE_SEALING_DEVICE_OF_THE_EMPEROR_WHO_IS_POSSESSED_BY_THE_EVIL_MAGICAL_CURSE_MAGICAL_CURSE_IS_VERY_POWERFUL_SO_WE_MUST_BE_CAREFUL;
	
	@ClientString(id = 3033, message = "By using the invisible skill, sneak into the Dawn's document storage!")
	public static SystemMessageId BY_USING_THE_INVISIBLE_SKILL_SNEAK_INTO_THE_DAWN_S_DOCUMENT_STORAGE;
	
	@ClientString(id = 3034, message = "The door in front of us is the entrance to the Dawn's document storage! Approach to the Code Input Device!")
	public static SystemMessageId THE_DOOR_IN_FRONT_OF_US_IS_THE_ENTRANCE_TO_THE_DAWN_S_DOCUMENT_STORAGE_APPROACH_TO_THE_CODE_INPUT_DEVICE;
	
	@ClientString(id = 3035, message = "My power's weakening. Please activate the sealing device possessed by Lilith's magical curse!")
	public static SystemMessageId MY_POWER_S_WEAKENING_PLEASE_ACTIVATE_THE_SEALING_DEVICE_POSSESSED_BY_LILITH_S_MAGICAL_CURSE;
	
	@ClientString(id = 3036, message = "You, such a fool! The victory over this war belongs to Shilien!")
	public static SystemMessageId YOU_SUCH_A_FOOL_THE_VICTORY_OVER_THIS_WAR_BELONGS_TO_SHILIEN;
	
	@ClientString(id = 3037, message = "Male guards can detect the concealment but the female guards cannot.")
	public static SystemMessageId MALE_GUARDS_CAN_DETECT_THE_CONCEALMENT_BUT_THE_FEMALE_GUARDS_CANNOT;
	
	@ClientString(id = 3038, message = "Female guards notice the disguises from far away better than the male guards do, so beware.")
	public static SystemMessageId FEMALE_GUARDS_NOTICE_THE_DISGUISES_FROM_FAR_AWAY_BETTER_THAN_THE_MALE_GUARDS_DO_SO_BEWARE;
	
	@ClientString(id = 3039, message = "By using the holy water of Einhasad, open the door possessed by the curse of flames.")
	public static SystemMessageId BY_USING_THE_HOLY_WATER_OF_EINHASAD_OPEN_THE_DOOR_POSSESSED_BY_THE_CURSE_OF_FLAMES;
	
	@ClientString(id = 3040, message = "By using the Court Magician's Magic Staff, open the door on which the magician's barrier is placed.")
	public static SystemMessageId BY_USING_THE_COURT_MAGICIAN_S_MAGIC_STAFF_OPEN_THE_DOOR_ON_WHICH_THE_MAGICIAN_S_BARRIER_IS_PLACED;
	
	@ClientString(id = 3041, message = "Around fifteen hundred years ago, the lands were riddled with heretics,")
	public static SystemMessageId AROUND_FIFTEEN_HUNDRED_YEARS_AGO_THE_LANDS_WERE_RIDDLED_WITH_HERETICS;
	
	@ClientString(id = 3042, message = "worshippers of Shilen, the Goddess of Death...")
	public static SystemMessageId WORSHIPPERS_OF_SHILEN_THE_GODDESS_OF_DEATH;
	
	@ClientString(id = 3043, message = "But a miracle happened at the enthronement of Shunaiman, the first emperor.")
	public static SystemMessageId BUT_A_MIRACLE_HAPPENED_AT_THE_ENTHRONEMENT_OF_SHUNAIMAN_THE_FIRST_EMPEROR;
	
	@ClientString(id = 3044, message = "Anakim, an angel of Einhasad, came down from the skies,")
	public static SystemMessageId ANAKIM_AN_ANGEL_OF_EINHASAD_CAME_DOWN_FROM_THE_SKIES;
	
	@ClientString(id = 3045, message = "surrounded by sacred flames and three pairs of wings.")
	public static SystemMessageId SURROUNDED_BY_SACRED_FLAMES_AND_THREE_PAIRS_OF_WINGS;
	
	@ClientString(id = 3046, message = "Thus empowered, the Emperor launched a war against 'Shilen's People.'")
	public static SystemMessageId THUS_EMPOWERED_THE_EMPEROR_LAUNCHED_A_WAR_AGAINST_SHILEN_S_PEOPLE;
	
	@ClientString(id = 3047, message = "The emperor's army led by Anakim attacked 'Shilen's People' relentlessly,")
	public static SystemMessageId THE_EMPEROR_S_ARMY_LED_BY_ANAKIM_ATTACKED_SHILEN_S_PEOPLE_RELENTLESSLY;
	
	@ClientString(id = 3048, message = "but in the end some survivors managed to hide in underground Catacombs.")
	public static SystemMessageId BUT_IN_THE_END_SOME_SURVIVORS_MANAGED_TO_HIDE_IN_UNDERGROUND_CATACOMBS;
	
	@ClientString(id = 3049, message = "A new leader emerged, Lilith, who sought to summon Shilen from the afterlife,")
	public static SystemMessageId A_NEW_LEADER_EMERGED_LILITH_WHO_SOUGHT_TO_SUMMON_SHILEN_FROM_THE_AFTERLIFE;
	
	@ClientString(id = 3050, message = "and to rebuild the Lilim army within the eight Necropolises.")
	public static SystemMessageId AND_TO_REBUILD_THE_LILIM_ARMY_WITHIN_THE_EIGHT_NECROPOLISES;
	
	@ClientString(id = 3051, message = "Now, in the midst of impending war, the merchant of Mammon struck a deal.")
	public static SystemMessageId NOW_IN_THE_MIDST_OF_IMPENDING_WAR_THE_MERCHANT_OF_MAMMON_STRUCK_A_DEAL;
	
	@ClientString(id = 3052, message = "He supplies Shunaiman with war funds in exchange for protection.")
	public static SystemMessageId HE_SUPPLIES_SHUNAIMAN_WITH_WAR_FUNDS_IN_EXCHANGE_FOR_PROTECTION;
	
	@ClientString(id = 3053, message = "And right now the document we're looking for is that contract.")
	public static SystemMessageId AND_RIGHT_NOW_THE_DOCUMENT_WE_RE_LOOKING_FOR_IS_THAT_CONTRACT;
	
	@ClientString(id = 3054, message = "Finally you're here! I'm Anakim, I need your help.")
	public static SystemMessageId FINALLY_YOU_RE_HERE_I_M_ANAKIM_I_NEED_YOUR_HELP;
	
	@ClientString(id = 3055, message = "It's the seal devices... I need you to destroy them while I distract Lilith!")
	public static SystemMessageId IT_S_THE_SEAL_DEVICES_I_NEED_YOU_TO_DESTROY_THEM_WHILE_I_DISTRACT_LILITH;
	
	@ClientString(id = 3056, message = "Please hurry. I don't have much time left!")
	public static SystemMessageId PLEASE_HURRY_I_DON_T_HAVE_MUCH_TIME_LEFT;
	
	@ClientString(id = 3057, message = "For Einhasad!")
	public static SystemMessageId FOR_EINHASAD;
	
	@ClientString(id = 3058, message = "Em.bry.o..")
	public static SystemMessageId EM_BRY_O;
	
	@ClientString(id = 3059, message = "$s1 did not receive it during the waiting time, so it was returned automatically. tntls.")
	public static SystemMessageId S1_DID_NOT_RECEIVE_IT_DURING_THE_WAITING_TIME_SO_IT_WAS_RETURNED_AUTOMATICALLY_TNTLS;
	
	@ClientString(id = 3060, message = "The sealing device glitters and moves. Activation complete normally!")
	public static SystemMessageId THE_SEALING_DEVICE_GLITTERS_AND_MOVES_ACTIVATION_COMPLETE_NORMALLY;
	
	@ClientString(id = 3061, message = "There comes a sound of opening the heavy door from somewhere.")
	public static SystemMessageId THERE_COMES_A_SOUND_OF_OPENING_THE_HEAVY_DOOR_FROM_SOMEWHERE;
	
	@ClientString(id = 3062, message = "Do you want to pay $s1 Adena?")
	public static SystemMessageId DO_YOU_WANT_TO_PAY_S1_ADENA;
	
	@ClientString(id = 3063, message = "Do you really want to forward?")
	public static SystemMessageId DO_YOU_REALLY_WANT_TO_FORWARD;
	
	@ClientString(id = 3064, message = "You have new mail.")
	public static SystemMessageId YOU_HAVE_NEW_MAIL;
	
	@ClientString(id = 3065, message = "Current location: Inside the Chamber of Delusion")
	public static SystemMessageId CURRENT_LOCATION_INSIDE_THE_CHAMBER_OF_DELUSION;
	
	@ClientString(id = 3066, message = "You cannot receive or send mail with attached items in non-peace zone regions.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_OR_SEND_MAIL_WITH_ATTACHED_ITEMS_IN_NON_PEACE_ZONE_REGIONS;
	
	@ClientString(id = 3067, message = "$s1 canceled the sent mail.")
	public static SystemMessageId S1_CANCELED_THE_SENT_MAIL;
	
	@ClientString(id = 3068, message = "The mail was returned due to the exceeded waiting time.")
	public static SystemMessageId THE_MAIL_WAS_RETURNED_DUE_TO_THE_EXCEEDED_WAITING_TIME;
	
	@ClientString(id = 3069, message = "Do you really want to return this mail to the sender?")
	public static SystemMessageId DO_YOU_REALLY_WANT_TO_RETURN_THIS_MAIL_TO_THE_SENDER;
	
	@ClientString(id = 3070, message = "Skill not available to be enhanced Check skill's Lv and current PC status.")
	public static SystemMessageId SKILL_NOT_AVAILABLE_TO_BE_ENHANCED_CHECK_SKILL_S_LV_AND_CURRENT_PC_STATUS;
	
	@ClientString(id = 3071, message = "Do you really want to reset? 10,000,000(10 million) Adena will be consumed.")
	public static SystemMessageId DO_YOU_REALLY_WANT_TO_RESET_10_000_000_10_MILLION_ADENA_WILL_BE_CONSUMED;
	
	@ClientString(id = 3072, message = "$s1 acquired the attached item to your mail.")
	public static SystemMessageId S1_ACQUIRED_THE_ATTACHED_ITEM_TO_YOUR_MAIL;
	
	@ClientString(id = 3073, message = "You have acquired $s2 $s1.")
	public static SystemMessageId YOU_HAVE_ACQUIRED_S2_S1;
	
	@ClientString(id = 3074, message = "The allowed length for recipient exceeded.")
	public static SystemMessageId THE_ALLOWED_LENGTH_FOR_RECIPIENT_EXCEEDED;
	
	@ClientString(id = 3075, message = "The allowed length for a title exceeded.")
	public static SystemMessageId THE_ALLOWED_LENGTH_FOR_A_TITLE_EXCEEDED;
	
	@ClientString(id = 3076, message = "The allowed length for a title exceeded.")
	public static SystemMessageId THE_ALLOWED_LENGTH_FOR_A_TITLE_EXCEEDED_2;
	
	@ClientString(id = 3077, message = "The mail limit (240) of the opponent's character has been exceeded and this cannot be forwarded.")
	public static SystemMessageId THE_MAIL_LIMIT_240_OF_THE_OPPONENT_S_CHARACTER_HAS_BEEN_EXCEEDED_AND_THIS_CANNOT_BE_FORWARDED;
	
	@ClientString(id = 3078, message = "You're making a request for payment. Do you want to proceed?")
	public static SystemMessageId YOU_RE_MAKING_A_REQUEST_FOR_PAYMENT_DO_YOU_WANT_TO_PROCEED;
	
	@ClientString(id = 3079, message = "There are items in your Pet Inventory rendering you unable to sell/trade/drop pet summoning items. Please empty your Pet Inventory.")
	public static SystemMessageId THERE_ARE_ITEMS_IN_YOUR_PET_INVENTORY_RENDERING_YOU_UNABLE_TO_SELL_TRADE_DROP_PET_SUMMONING_ITEMS_PLEASE_EMPTY_YOUR_PET_INVENTORY;
	
	@ClientString(id = 3080, message = "You cannot reset the Skill Link because there is not enough Adena.")
	public static SystemMessageId YOU_CANNOT_RESET_THE_SKILL_LINK_BECAUSE_THERE_IS_NOT_ENOUGH_ADENA;
	
	@ClientString(id = 3081, message = "You cannot receive it because you are under the condition that the opponent cannot acquire any Adena for payment.")
	public static SystemMessageId YOU_CANNOT_RECEIVE_IT_BECAUSE_YOU_ARE_UNDER_THE_CONDITION_THAT_THE_OPPONENT_CANNOT_ACQUIRE_ANY_ADENA_FOR_PAYMENT;
	
	@ClientString(id = 3082, message = "You cannot send mails to any character that has blocked you.")
	public static SystemMessageId YOU_CANNOT_SEND_MAILS_TO_ANY_CHARACTER_THAT_HAS_BLOCKED_YOU;
	
	@ClientString(id = 3083, message = "In the process of working on the previous clan declaration/retreat. Please try again later.")
	public static SystemMessageId IN_THE_PROCESS_OF_WORKING_ON_THE_PREVIOUS_CLAN_DECLARATION_RETREAT_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3084, message = "Currently, we are in the process of choosing a hero. Please try again later.")
	public static SystemMessageId CURRENTLY_WE_ARE_IN_THE_PROCESS_OF_CHOOSING_A_HERO_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3085, message = "You can summon the pet you are trying to summon now only when you own a hideout.")
	public static SystemMessageId YOU_CAN_SUMMON_THE_PET_YOU_ARE_TRYING_TO_SUMMON_NOW_ONLY_WHEN_YOU_OWN_A_HIDEOUT;
	
	@ClientString(id = 3086, message = "Would you like to give $s2 $s1?")
	public static SystemMessageId WOULD_YOU_LIKE_TO_GIVE_S2_S1;
	
	@ClientString(id = 3087, message = "This mail is being sent with a Payment Request. Would you like to continue?")
	public static SystemMessageId THIS_MAIL_IS_BEING_SENT_WITH_A_PAYMENT_REQUEST_WOULD_YOU_LIKE_TO_CONTINUE;
	
	@ClientString(id = 3088, message = "You have $s1 hours $s2 minutes and $s3 seconds left in the Proof of Space and Time. If Agathion is summoned within this time, 10 minutes or more can be added.")
	public static SystemMessageId YOU_HAVE_S1_HOURS_S2_MINUTES_AND_S3_SECONDS_LEFT_IN_THE_PROOF_OF_SPACE_AND_TIME_IF_AGATHION_IS_SUMMONED_WITHIN_THIS_TIME_10_MINUTES_OR_MORE_CAN_BE_ADDED;
	
	@ClientString(id = 3089, message = "You have $s1 minutes and $s2 seconds left in the Proof of Space and Time. If Agathion is summoned within this time, 10 minutes or more can be added.")
	public static SystemMessageId YOU_HAVE_S1_MINUTES_AND_S2_SECONDS_LEFT_IN_THE_PROOF_OF_SPACE_AND_TIME_IF_AGATHION_IS_SUMMONED_WITHIN_THIS_TIME_10_MINUTES_OR_MORE_CAN_BE_ADDED;
	
	@ClientString(id = 3090, message = "You have $s1 seconds left in the Proof of Space and Time. If Agathion is summoned within this time, 10 minutes or more can be added.")
	public static SystemMessageId YOU_HAVE_S1_SECONDS_LEFT_IN_THE_PROOF_OF_SPACE_AND_TIME_IF_AGATHION_IS_SUMMONED_WITHIN_THIS_TIME_10_MINUTES_OR_MORE_CAN_BE_ADDED;
	
	@ClientString(id = 3091, message = "You cannot delete characters on this server right now.")
	public static SystemMessageId YOU_CANNOT_DELETE_CHARACTERS_ON_THIS_SERVER_RIGHT_NOW;
	
	@ClientString(id = 3092, message = "Transaction completed.")
	public static SystemMessageId TRANSACTION_COMPLETED;
	
	@ClientString(id = 3093, message = "It exeeds the proper calculation range. Please enter again.")
	public static SystemMessageId IT_EXEEDS_THE_PROPER_CALCULATION_RANGE_PLEASE_ENTER_AGAIN;
	
	@ClientString(id = 3094, message = "A user currently participating in the Olympiad cannot send party and friend invitations.")
	public static SystemMessageId A_USER_CURRENTLY_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_SEND_PARTY_AND_FRIEND_INVITATIONS;
	
	@ClientString(id = 3095, message = "The certification failed because you did not enter a valid certification number or you did not enter a certification number at all. If you fail 3 times in a row, you will be blocked from the game for 30 minutes.")
	public static SystemMessageId THE_CERTIFICATION_FAILED_BECAUSE_YOU_DID_NOT_ENTER_A_VALID_CERTIFICATION_NUMBER_OR_YOU_DID_NOT_ENTER_A_CERTIFICATION_NUMBER_AT_ALL_IF_YOU_FAIL_3_TIMES_IN_A_ROW_YOU_WILL_BE_BLOCKED_FROM_THE_GAME_FOR_30_MINUTES;
	
	@ClientString(id = 3096, message = "Due to problems with communications, our telephone certification service is currently unavailable. Please try again later.")
	public static SystemMessageId DUE_TO_PROBLEMS_WITH_COMMUNICATIONS_OUR_TELEPHONE_CERTIFICATION_SERVICE_IS_CURRENTLY_UNAVAILABLE_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3097, message = "Due to problems with communications, telephone signals are being delayed. Please try again later.")
	public static SystemMessageId DUE_TO_PROBLEMS_WITH_COMMUNICATIONS_TELEPHONE_SIGNALS_ARE_BEING_DELAYED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3098, message = "The certification failed because the line was busy or the call was not received. Please try again.")
	public static SystemMessageId THE_CERTIFICATION_FAILED_BECAUSE_THE_LINE_WAS_BUSY_OR_THE_CALL_WAS_NOT_RECEIVED_PLEASE_TRY_AGAIN;
	
	@ClientString(id = 3099, message = "The telephone certification service subscription number is expired or incorrect. Please check the telephone certification service subscription information. (plaync > Security Center > Telephone Certification Service)")
	public static SystemMessageId THE_TELEPHONE_CERTIFICATION_SERVICE_SUBSCRIPTION_NUMBER_IS_EXPIRED_OR_INCORRECT_PLEASE_CHECK_THE_TELEPHONE_CERTIFICATION_SERVICE_SUBSCRIPTION_INFORMATION_PLAYNC_SECURITY_CENTER_TELEPHONE_CERTIFICATION_SERVICE;
	
	@ClientString(id = 3100, message = "The telephone certification service is currently being checked. Please try again later.")
	public static SystemMessageId THE_TELEPHONE_CERTIFICATION_SERVICE_IS_CURRENTLY_BEING_CHECKED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3101, message = "Due to heavy volume, the telephone certification service cannot be used at this time. Please try again later.")
	public static SystemMessageId DUE_TO_HEAVY_VOLUME_THE_TELEPHONE_CERTIFICATION_SERVICE_CANNOT_BE_USED_AT_THIS_TIME_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3102, message = "The telephone certification service use period has expired, so game play has been blocked. Please check the game play block setup. (plaync > Security Center > Telephone Certification Service)")
	public static SystemMessageId THE_TELEPHONE_CERTIFICATION_SERVICE_USE_PERIOD_HAS_EXPIRED_SO_GAME_PLAY_HAS_BEEN_BLOCKED_PLEASE_CHECK_THE_GAME_PLAY_BLOCK_SETUP_PLAYNC_SECURITY_CENTER_TELEPHONE_CERTIFICATION_SERVICE;
	
	@ClientString(id = 3103, message = "The telephone certification failed 3 times in a row, so game play has been blocked for 30 minutes. Please try again later.")
	public static SystemMessageId THE_TELEPHONE_CERTIFICATION_FAILED_3_TIMES_IN_A_ROW_SO_GAME_PLAY_HAS_BEEN_BLOCKED_FOR_30_MINUTES_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3104, message = "The number of uses of the daily telephone certification service has been exceeded.")
	public static SystemMessageId THE_NUMBER_OF_USES_OF_THE_DAILY_TELEPHONE_CERTIFICATION_SERVICE_HAS_BEEN_EXCEEDED;
	
	@ClientString(id = 3105, message = "Telephone certification is already underway. Please try again later.")
	public static SystemMessageId TELEPHONE_CERTIFICATION_IS_ALREADY_UNDERWAY_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3106, message = "Telephone certification is underway.")
	public static SystemMessageId TELEPHONE_CERTIFICATION_IS_UNDERWAY;
	
	@ClientString(id = 3107, message = "One moment please.")
	public static SystemMessageId ONE_MOMENT_PLEASE;
	
	@ClientString(id = 3108, message = "You are no longer protected from aggressive monsters.")
	public static SystemMessageId YOU_ARE_NO_LONGER_PROTECTED_FROM_AGGRESSIVE_MONSTERS;
	
	@ClientString(id = 3109, message = "$s1 has achieved $s2 wins in a row in Jack's game.")
	public static SystemMessageId S1_HAS_ACHIEVED_S2_WINS_IN_A_ROW_IN_JACK_S_GAME;
	
	@ClientString(id = 3110, message = "In reward for $s2 wins in a row, $s1님 has received $s4 of $s3(s).")
	public static SystemMessageId IN_REWARD_FOR_S2_WINS_IN_A_ROW_S1_HAS_RECEIVED_S4_OF_S3_S;
	
	@ClientString(id = 3111, message = "World: $s1 wins in a row ($s2 ppl)")
	public static SystemMessageId WORLD_S1_WINS_IN_A_ROW_S2_PPL;
	
	@ClientString(id = 3112, message = "My record: $s1 wins in a row")
	public static SystemMessageId MY_RECORD_S1_WINS_IN_A_ROW;
	
	@ClientString(id = 3113, message = "World: Below 4 wins in a row")
	public static SystemMessageId WORLD_BELOW_4_WINS_IN_A_ROW;
	
	@ClientString(id = 3114, message = "My record: Below 4 wins in a row")
	public static SystemMessageId MY_RECORD_BELOW_4_WINS_IN_A_ROW;
	
	@ClientString(id = 3115, message = "This is the Halloween event period.")
	public static SystemMessageId THIS_IS_THE_HALLOWEEN_EVENT_PERIOD;
	
	@ClientString(id = 3116, message = "No record of 10 or more wins in a row.")
	public static SystemMessageId NO_RECORD_OF_10_OR_MORE_WINS_IN_A_ROW;
	
	@ClientString(id = 3117, message = "You can no longer bestow attributes that are the opposite of the currently bestowed attribute.")
	public static SystemMessageId YOU_CAN_NO_LONGER_BESTOW_ATTRIBUTES_THAT_ARE_THE_OPPOSITE_OF_THE_CURRENTLY_BESTOWED_ATTRIBUTE;
	
	@ClientString(id = 3118, message = "Do you wish to accept $c1's $s2 request?")
	public static SystemMessageId DO_YOU_WISH_TO_ACCEPT_C1_S_S2_REQUEST;
	
	@ClientString(id = 3119, message = "The couple action was denied.")
	public static SystemMessageId THE_COUPLE_ACTION_WAS_DENIED;
	
	@ClientString(id = 3120, message = "The request cannot be completed because the target does not meet location requirements.")
	public static SystemMessageId THE_REQUEST_CANNOT_BE_COMPLETED_BECAUSE_THE_TARGET_DOES_NOT_MEET_LOCATION_REQUIREMENTS;
	
	@ClientString(id = 3121, message = "The couple action was cancelled.")
	public static SystemMessageId THE_COUPLE_ACTION_WAS_CANCELLED;
	
	@ClientString(id = 3122, message = "The size of the uploaded crest or insignia does not meet the standard requirements.")
	public static SystemMessageId THE_SIZE_OF_THE_UPLOADED_CREST_OR_INSIGNIA_DOES_NOT_MEET_THE_STANDARD_REQUIREMENTS;
	
	@ClientString(id = 3123, message = "$c1 is in Private Shop mode or in a battle and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_IN_PRIVATE_SHOP_MODE_OR_IN_A_BATTLE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3124, message = "$c1 is fishing and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_FISHING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3125, message = "$c1 is in a battle and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_IN_A_BATTLE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3126, message = "$c1 is already participating in a couple action and cannot be requested for another couple action.")
	public static SystemMessageId C1_IS_ALREADY_PARTICIPATING_IN_A_COUPLE_ACTION_AND_CANNOT_BE_REQUESTED_FOR_ANOTHER_COUPLE_ACTION;
	
	@ClientString(id = 3127, message = "$c1 is in a chaotic state and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_IN_A_CHAOTIC_STATE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3128, message = "$c1 is participating in the Olympiad and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_PARTICIPATING_IN_THE_OLYMPIAD_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3129, message = "$c1 is participating in a hideout siege and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_PARTICIPATING_IN_A_HIDEOUT_SIEGE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3130, message = "$c1 is in a castle siege and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_IN_A_CASTLE_SIEGE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3131, message = "$c1 is riding a ship, steed, or strider and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_RIDING_A_SHIP_STEED_OR_STRIDER_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3132, message = "$c1 is currently teleporting and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_CURRENTLY_TELEPORTING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3133, message = "$c1 is currently transforming and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_CURRENTLY_TRANSFORMING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3134, message = "Party loot was changed to '$s1'. Do you consent?")
	public static SystemMessageId PARTY_LOOT_WAS_CHANGED_TO_S1_DO_YOU_CONSENT;
	
	@ClientString(id = 3135, message = "Requesting approval for changing party loot to '$s1'.")
	public static SystemMessageId REQUESTING_APPROVAL_FOR_CHANGING_PARTY_LOOT_TO_S1;
	
	@ClientString(id = 3136, message = "Party loot can now be changed.")
	public static SystemMessageId PARTY_LOOT_CAN_NOW_BE_CHANGED;
	
	@ClientString(id = 3137, message = "Party loot change was cancelled.")
	public static SystemMessageId PARTY_LOOT_CHANGE_WAS_CANCELLED;
	
	@ClientString(id = 3138, message = "Party loot was changed to '$s1'.")
	public static SystemMessageId PARTY_LOOT_WAS_CHANGED_TO_S1;
	
	@ClientString(id = 3139, message = "$c1 is currently dead and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_CURRENTLY_DEAD_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3140, message = "The crest was successfully registered.")
	public static SystemMessageId THE_CREST_WAS_SUCCESSFULLY_REGISTERED;
	
	@ClientString(id = 3141, message = "$c1 is in the process of changing the party loot. Please try again later.")
	public static SystemMessageId C1_IS_IN_THE_PROCESS_OF_CHANGING_THE_PARTY_LOOT_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3142, message = "While party loot change is taking place, another 1:1 request cannot be made.")
	public static SystemMessageId WHILE_PARTY_LOOT_CHANGE_IS_TAKING_PLACE_ANOTHER_1_1_REQUEST_CANNOT_BE_MADE;
	
	@ClientString(id = 3143, message = "You can only register 8x12 pixel 256 color bmp files for clan crests.")
	public static SystemMessageId YOU_CAN_ONLY_REGISTER_8X12_PIXEL_256_COLOR_BMP_FILES_FOR_CLAN_CRESTS;
	
	@ClientString(id = 3144, message = "The $s2's attribute was successfully bestowed on $s1, and resistance to $s3 was increased.")
	public static SystemMessageId THE_S2_S_ATTRIBUTE_WAS_SUCCESSFULLY_BESTOWED_ON_S1_AND_RESISTANCE_TO_S3_WAS_INCREASED;
	
	@ClientString(id = 3145, message = "This item cannot be used because you are already participating in the quest that can be started with this item.")
	public static SystemMessageId THIS_ITEM_CANNOT_BE_USED_BECAUSE_YOU_ARE_ALREADY_PARTICIPATING_IN_THE_QUEST_THAT_CAN_BE_STARTED_WITH_THIS_ITEM;
	
	@ClientString(id = 3146, message = "Do you really wish to remove $s1's $s2 attribute?")
	public static SystemMessageId DO_YOU_REALLY_WISH_TO_REMOVE_S1_S_S2_ATTRIBUTE;
	
	@ClientString(id = 3147, message = "If you are not resurrected within $s1 minutes, you will be expelled from the instant zone.")
	public static SystemMessageId IF_YOU_ARE_NOT_RESURRECTED_WITHIN_S1_MINUTES_YOU_WILL_BE_EXPELLED_FROM_THE_INSTANT_ZONE;
	
	@ClientString(id = 3148, message = "The number of instant zones that can be created has been exceeded. Please try again later.")
	public static SystemMessageId THE_NUMBER_OF_INSTANT_ZONES_THAT_CAN_BE_CREATED_HAS_BEEN_EXCEEDED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3149, message = "One-piece upper and lower body armor can use enchant rate increasing items from +4.")
	public static SystemMessageId ONE_PIECE_UPPER_AND_LOWER_BODY_ARMOR_CAN_USE_ENCHANT_RATE_INCREASING_ITEMS_FROM_4;
	
	@ClientString(id = 3150, message = "You have requested a couple action with $c1.")
	public static SystemMessageId YOU_HAVE_REQUESTED_A_COUPLE_ACTION_WITH_C1;
	
	@ClientString(id = 3151, message = "$c1 accepted the couple action.")
	public static SystemMessageId C1_ACCEPTED_THE_COUPLE_ACTION;
	
	@ClientString(id = 3152, message = "$s1's $s2 attribute was removed, and resistance to $s3 was decreased.")
	public static SystemMessageId S1_S_S2_ATTRIBUTE_WAS_REMOVED_AND_RESISTANCE_TO_S3_WAS_DECREASED;
	
	@ClientString(id = 3153, message = "The attribute that you are trying to bestow has already reached its maximum, so you cannot proceed.")
	public static SystemMessageId THE_ATTRIBUTE_THAT_YOU_ARE_TRYING_TO_BESTOW_HAS_ALREADY_REACHED_ITS_MAXIMUM_SO_YOU_CANNOT_PROCEED;
	
	@ClientString(id = 3154, message = "This item can only have one attribute. An attribute has already been bestowed, so you cannot proceed.")
	public static SystemMessageId THIS_ITEM_CAN_ONLY_HAVE_ONE_ATTRIBUTE_AN_ATTRIBUTE_HAS_ALREADY_BEEN_BESTOWED_SO_YOU_CANNOT_PROCEED;
	
	@ClientString(id = 3155, message = "All attributes have already been maximized, so you cannot proceed.")
	public static SystemMessageId ALL_ATTRIBUTES_HAVE_ALREADY_BEEN_MAXIMIZED_SO_YOU_CANNOT_PROCEED;
	
	@ClientString(id = 3156, message = "You do not have enough funds to cancel this attribute.")
	public static SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_FUNDS_TO_CANCEL_THIS_ATTRIBUTE;
	
	@ClientString(id = 3157, message = "Do you really wish to delete the clan crest?")
	public static SystemMessageId DO_YOU_REALLY_WISH_TO_DELETE_THE_CLAN_CREST;
	
	@ClientString(id = 3158, message = "This is not the Lilith server. This command can only be used on the Lilith server.")
	public static SystemMessageId THIS_IS_NOT_THE_LILITH_SERVER_THIS_COMMAND_CAN_ONLY_BE_USED_ON_THE_LILITH_SERVER;
	
	@ClientString(id = 3159, message = "First, please select the shortcut key category to be changed.")
	public static SystemMessageId FIRST_PLEASE_SELECT_THE_SHORTCUT_KEY_CATEGORY_TO_BE_CHANGED;
	
	@ClientString(id = 3160, message = "+$s1$s2's $s3 attribute was removed, so resistance to $s4 was decreased.")
	public static SystemMessageId S1_S2_S_S3_ATTRIBUTE_WAS_REMOVED_SO_RESISTANCE_TO_S4_WAS_DECREASED;
	
	@ClientString(id = 3161, message = "Attribute enchant and attribute cancel cannot take place at the same time. Please complete the current task and try again.")
	public static SystemMessageId ATTRIBUTE_ENCHANT_AND_ATTRIBUTE_CANCEL_CANNOT_TAKE_PLACE_AT_THE_SAME_TIME_PLEASE_COMPLETE_THE_CURRENT_TASK_AND_TRY_AGAIN;
	
	@ClientString(id = 3162, message = "The skill cannot be used because the opponent is in a different instant zone.")
	public static SystemMessageId THE_SKILL_CANNOT_BE_USED_BECAUSE_THE_OPPONENT_IS_IN_A_DIFFERENT_INSTANT_ZONE;
	
	@ClientString(id = 3163, message = "The $s3's attribute was successfully bestowed on +$s1$s2, and resistance to $s4 was increased.")
	public static SystemMessageId THE_S3_S_ATTRIBUTE_WAS_SUCCESSFULLY_BESTOWED_ON_S1_S2_AND_RESISTANCE_TO_S4_WAS_INCREASED;
	
	@ClientString(id = 3164, message = "$c1 is set to refuse couple actions and cannot be requested for a couple action.")
	public static SystemMessageId C1_IS_SET_TO_REFUSE_COUPLE_ACTIONS_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	@ClientString(id = 3165, message = "No crest is registered.")
	public static SystemMessageId NO_CREST_IS_REGISTERED;
	
	@ClientString(id = 3166, message = "No insignia is registered.")
	public static SystemMessageId NO_INSIGNIA_IS_REGISTERED;
	
	@ClientString(id = 3167, message = "The crest was successfully deleted.")
	public static SystemMessageId THE_CREST_WAS_SUCCESSFULLY_DELETED;
	
	@ClientString(id = 3168, message = "$c1 is set to refuse party requests and cannot receive a party request.")
	public static SystemMessageId C1_IS_SET_TO_REFUSE_PARTY_REQUESTS_AND_CANNOT_RECEIVE_A_PARTY_REQUEST;
	
	@ClientString(id = 3169, message = "$c1 is set to refuse duel requests and cannot receive a duel request.")
	public static SystemMessageId C1_IS_SET_TO_REFUSE_DUEL_REQUESTS_AND_CANNOT_RECEIVE_A_DUEL_REQUEST;
	
	@ClientString(id = 3170, message = "Current location: $s1, $s2, $s3 (outside the Seed of Annihilation)")
	public static SystemMessageId CURRENT_LOCATION_S1_S2_S3_OUTSIDE_THE_SEED_OF_ANNIHILATION;
	
	@ClientString(id = 3171, message = "The gift's remaining resupply time is $s1 minute(s) $s2 second(s). (If you resummon the Agathion at the gift supply time, the supply time can take an additional 30 minutes.)")
	public static SystemMessageId THE_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_MINUTE_S_S2_SECOND_S_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT_SUPPLY_TIME_THE_SUPPLY_TIME_CAN_TAKE_AN_ADDITIONAL_30_MINUTES;
	
	@ClientString(id = 3172, message = "The gift's remaining resupply time is $s1 second(s). (If you resummon the Agathion at the gift supply time, the supply time can take an additional 30 minutes.)")
	public static SystemMessageId THE_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_SECOND_S_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT_SUPPLY_TIME_THE_SUPPLY_TIME_CAN_TAKE_AN_ADDITIONAL_30_MINUTES;
	
	@ClientString(id = 3173, message = "Hero exclusive items cannot be bestowed with attributes.")
	public static SystemMessageId HERO_EXCLUSIVE_ITEMS_CANNOT_BE_BESTOWED_WITH_ATTRIBUTES;
	
	@ClientString(id = 3174, message = "You dare to shatter the quiet of my castle, again.")
	public static SystemMessageId YOU_DARE_TO_SHATTER_THE_QUIET_OF_MY_CASTLE_AGAIN;
	
	@ClientString(id = 3175, message = "I, Freya Queen of Ice, shall curse you all with eternal winter sleep.")
	public static SystemMessageId I_FREYA_QUEEN_OF_ICE_SHALL_CURSE_YOU_ALL_WITH_ETERNAL_WINTER_SLEEP;
	
	@ClientString(id = 3176, message = "Your hearts will freeze to stillness... Even your memories shall disappear.")
	public static SystemMessageId YOUR_HEARTS_WILL_FREEZE_TO_STILLNESS_EVEN_YOUR_MEMORIES_SHALL_DISAPPEAR;
	
	@ClientString(id = 3177, message = "So you wish to challenge my powers!  Hahaha…")
	public static SystemMessageId SO_YOU_WISH_TO_CHALLENGE_MY_POWERS_HAHAHA;
	
	@ClientString(id = 3178, message = "Behold my frozen minions.")
	public static SystemMessageId BEHOLD_MY_FROZEN_MINIONS;
	
	@ClientString(id = 3179, message = "Obey my command and attack these invaders.")
	public static SystemMessageId OBEY_MY_COMMAND_AND_ATTACK_THESE_INVADERS;
	
	@ClientString(id = 3180, message = "No!  How could this be... You are but mere mortals?!")
	public static SystemMessageId NO_HOW_COULD_THIS_BE_YOU_ARE_BUT_MERE_MORTALS;
	
	@ClientString(id = 3181, message = "Muhaha... Very well. I will bring you your icy death!")
	public static SystemMessageId MUHAHA_VERY_WELL_I_WILL_BRING_YOU_YOUR_ICY_DEATH;
	
	@ClientString(id = 3182, message = "There's no turning back! This ends now!")
	public static SystemMessageId THERE_S_NO_TURNING_BACK_THIS_ENDS_NOW;
	
	@ClientString(id = 3183, message = "Oh furious winds of light, slice through the darkness and defeat this evil!")
	public static SystemMessageId OH_FURIOUS_WINDS_OF_LIGHT_SLICE_THROUGH_THE_DARKNESS_AND_DEFEAT_THIS_EVIL;
	
	@ClientString(id = 3184, message = "To die this way... Such a shameful defeat... Sirra... How could you do this to me…")
	public static SystemMessageId TO_DIE_THIS_WAY_SUCH_A_SHAMEFUL_DEFEAT_SIRRA_HOW_COULD_YOU_DO_THIS_TO_ME;
	
	@ClientString(id = 3185, message = "Freya! This is the end for you! Rawr.")
	public static SystemMessageId FREYA_THIS_IS_THE_END_FOR_YOU_RAWR;
	
	@ClientString(id = 3186, message = "Ah-hahahaha! Ice Queen, really? You didn't deserve this power.")
	public static SystemMessageId AH_HAHAHAHA_ICE_QUEEN_REALLY_YOU_DIDN_T_DESERVE_THIS_POWER;
	
	@ClientString(id = 3187, message = "Ahh.. This feeling... Now... All her power is mine. Hahaha-ha!")
	public static SystemMessageId AHH_THIS_FEELING_NOW_ALL_HER_POWER_IS_MINE_HAHAHA_HA;
	
	@ClientString(id = 3188, message = "You who feel warm life force coursing through your veins.")
	public static SystemMessageId YOU_WHO_FEEL_WARM_LIFE_FORCE_COURSING_THROUGH_YOUR_VEINS;
	
	@ClientString(id = 3189, message = "I shall take your last breath. But not this day, return to me.")
	public static SystemMessageId I_SHALL_TAKE_YOUR_LAST_BREATH_BUT_NOT_THIS_DAY_RETURN_TO_ME;
	
	@ClientString(id = 3190, message = "How dare you ignore my warning... Foolish creatures. Hahaha...")
	public static SystemMessageId HOW_DARE_YOU_IGNORE_MY_WARNING_FOOLISH_CREATURES_HAHAHA;
	
	@ClientString(id = 3191, message = "Say goodbye to sunlight and welcome eternal ice.")
	public static SystemMessageId SAY_GOODBYE_TO_SUNLIGHT_AND_WELCOME_ETERNAL_ICE;
	
	@ClientString(id = 3192, message = "Muhahaha... If you wish to be chilled to the bone, I'll gladly oblige.")
	public static SystemMessageId MUHAHAHA_IF_YOU_WISH_TO_BE_CHILLED_TO_THE_BONE_I_LL_GLADLY_OBLIGE;
	
	@ClientString(id = 3193, message = "How dare you enter my castle! Hahaha... Foolish ones... Leave this place before the frost chills your blood.")
	public static SystemMessageId HOW_DARE_YOU_ENTER_MY_CASTLE_HAHAHA_FOOLISH_ONES_LEAVE_THIS_PLACE_BEFORE_THE_FROST_CHILLS_YOUR_BLOOD;
	
	@ClientString(id = 3194, message = "Hmph! You will not dodge my blizzard again!")
	public static SystemMessageId HMPH_YOU_WILL_NOT_DODGE_MY_BLIZZARD_AGAIN;
	
	@ClientString(id = 3195, message = "All those who challenge my power shall feel the curse of ice.")
	public static SystemMessageId ALL_THOSE_WHO_CHALLENGE_MY_POWER_SHALL_FEEL_THE_CURSE_OF_ICE;
	
	@ClientString(id = 3196, message = "I will seal your hearts with ice. Not even your breath will escape.")
	public static SystemMessageId I_WILL_SEAL_YOUR_HEARTS_WITH_ICE_NOT_EVEN_YOUR_BREATH_WILL_ESCAPE;
	
	@ClientString(id = 3197, message = "Hahaha… You are much too weak. Even my snow flakes could defeat you.")
	public static SystemMessageId HAHAHA_YOU_ARE_MUCH_TOO_WEAK_EVEN_MY_SNOW_FLAKES_COULD_DEFEAT_YOU;
	
	@ClientString(id = 3198, message = "This is all futile.")
	public static SystemMessageId THIS_IS_ALL_FUTILE;
	
	@ClientString(id = 3199, message = "By my power, you will live in ice for all eternity.")
	public static SystemMessageId BY_MY_POWER_YOU_WILL_LIVE_IN_ICE_FOR_ALL_ETERNITY;
	
	@ClientString(id = 3200, message = "Oh, great power of destruction. Come forth and obey me!")
	public static SystemMessageId OH_GREAT_POWER_OF_DESTRUCTION_COME_FORTH_AND_OBEY_ME;
	
	@ClientString(id = 3201, message = "The deep cold and its unwavering eternity. Cover this world with your frigid silence.")
	public static SystemMessageId THE_DEEP_COLD_AND_ITS_UNWAVERING_ETERNITY_COVER_THIS_WORLD_WITH_YOUR_FRIGID_SILENCE;
	
	@ClientString(id = 3202, message = "Oh blizzard of frozen death... I summon thee... Oh frozen darkness. Heed my call!")
	public static SystemMessageId OH_BLIZZARD_OF_FROZEN_DEATH_I_SUMMON_THEE_OH_FROZEN_DARKNESS_HEED_MY_CALL;
	
	@ClientString(id = 3203, message = "Hahaha... Foolish ones. Today, I will not treat you so gently.")
	public static SystemMessageId HAHAHA_FOOLISH_ONES_TODAY_I_WILL_NOT_TREAT_YOU_SO_GENTLY;
	
	@ClientString(id = 3204, message = "This body is under my complete control now. You shall kneel before me.")
	public static SystemMessageId THIS_BODY_IS_UNDER_MY_COMPLETE_CONTROL_NOW_YOU_SHALL_KNEEL_BEFORE_ME;
	
	@ClientString(id = 3205, message = "You say you will defeat me? With all of my powers finally released? Ridiculous!")
	public static SystemMessageId YOU_SAY_YOU_WILL_DEFEAT_ME_WITH_ALL_OF_MY_POWERS_FINALLY_RELEASED_RIDICULOUS;
	
	@ClientString(id = 3206, message = "You are out of Recommendations. Try again later.")
	public static SystemMessageId YOU_ARE_OUT_OF_RECOMMENDATIONS_TRY_AGAIN_LATER;
	
	@ClientString(id = 3207, message = "You obtained $s1 Recommendation(s).")
	public static SystemMessageId YOU_OBTAINED_S1_RECOMMENDATION_S;
	
	@ClientString(id = 3208, message = "You will go to the Lineage 2 homepage. Do you wish to continue?")
	public static SystemMessageId YOU_WILL_GO_TO_THE_LINEAGE_2_HOMEPAGE_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 3209, message = "You obtained a Maguen Pet Collar.")
	public static SystemMessageId YOU_OBTAINED_A_MAGUEN_PET_COLLAR;
	
	@ClientString(id = 3210, message = "You obtained an Elite Maguen Pet Collar.")
	public static SystemMessageId YOU_OBTAINED_AN_ELITE_MAGUEN_PET_COLLAR;
	
	@ClientString(id = 3211, message = "You will go to the $s1. Do you wish to continue?")
	public static SystemMessageId YOU_WILL_GO_TO_THE_S1_DO_YOU_WISH_TO_CONTINUE;
	
	@ClientString(id = 3212, message = "When your pet's hunger gauge is at 0%, you cannot use your pet.")
	public static SystemMessageId WHEN_YOUR_PET_S_HUNGER_GAUGE_IS_AT_0_YOU_CANNOT_USE_YOUR_PET;
	
	@ClientString(id = 3213, message = "Your pet is starving and will not obey until it gets it's food. Feed your pet!")
	public static SystemMessageId YOUR_PET_IS_STARVING_AND_WILL_NOT_OBEY_UNTIL_IT_GETS_IT_S_FOOD_FEED_YOUR_PET;
	
	@ClientString(id = 3214, message = "$s1 was successfully added to your Contact List.")
	public static SystemMessageId S1_WAS_SUCCESSFULLY_ADDED_TO_YOUR_CONTACT_LIST;
	
	@ClientString(id = 3215, message = "The name $s1% doesn't exist. Please try another name.")
	public static SystemMessageId THE_NAME_S1_DOESN_T_EXIST_PLEASE_TRY_ANOTHER_NAME;
	
	@ClientString(id = 3216, message = "The name already exists on the added list.")
	public static SystemMessageId THE_NAME_ALREADY_EXISTS_ON_THE_ADDED_LIST;
	
	@ClientString(id = 3217, message = "The name is not currently registered.")
	public static SystemMessageId THE_NAME_IS_NOT_CURRENTLY_REGISTERED;
	
	@ClientString(id = 3218, message = "Do you really wish to delete $s1?")
	public static SystemMessageId DO_YOU_REALLY_WISH_TO_DELETE_S1;
	
	@ClientString(id = 3219, message = "$s1 was successfully deleted from your Contact List.")
	public static SystemMessageId S1_WAS_SUCCESSFULLY_DELETED_FROM_YOUR_CONTACT_LIST;
	
	@ClientString(id = 3220, message = "The name deletion was cancelled.")
	public static SystemMessageId THE_NAME_DELETION_WAS_CANCELLED;
	
	@ClientString(id = 3221, message = "You cannot add your own name.")
	public static SystemMessageId YOU_CANNOT_ADD_YOUR_OWN_NAME;
	
	@ClientString(id = 3222, message = "The maximum number of names (100) has been reached. You cannot register any more.")
	public static SystemMessageId THE_MAXIMUM_NUMBER_OF_NAMES_100_HAS_BEEN_REACHED_YOU_CANNOT_REGISTER_ANY_MORE;
	
	@ClientString(id = 3223, message = "The previous name is being registered. Please try again later.")
	public static SystemMessageId THE_PREVIOUS_NAME_IS_BEING_REGISTERED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3224, message = "The maximum matches you can participate in 1 week is 70.")
	public static SystemMessageId THE_MAXIMUM_MATCHES_YOU_CAN_PARTICIPATE_IN_1_WEEK_IS_70;
	
	@ClientString(id = 3225, message = "The total number of matches that can be entered in 1 week is 60 class irrelevant individual matches, 30 specific matches, and 10 team matches.")
	public static SystemMessageId THE_TOTAL_NUMBER_OF_MATCHES_THAT_CAN_BE_ENTERED_IN_1_WEEK_IS_60_CLASS_IRRELEVANT_INDIVIDUAL_MATCHES_30_SPECIFIC_MATCHES_AND_10_TEAM_MATCHES;
	
	@ClientString(id = 3226, message = "You cannot move while speaking to an NPC. One moment please.")
	public static SystemMessageId YOU_CANNOT_MOVE_WHILE_SPEAKING_TO_AN_NPC_ONE_MOMENT_PLEASE;
	
	@ClientString(id = 3227, message = "The large army of Elmore-Aden is advancing upon the Monastery of Silence.")
	public static SystemMessageId THE_LARGE_ARMY_OF_ELMORE_ADEN_IS_ADVANCING_UPON_THE_MONASTERY_OF_SILENCE;
	
	@ClientString(id = 3228, message = "Anais and his troops has fought them bravely,")
	public static SystemMessageId ANAIS_AND_HIS_TROOPS_HAS_FOUGHT_THEM_BRAVELY;
	
	@ClientString(id = 3229, message = "until they were betrayed by Jude, and slaughtered in battle.")
	public static SystemMessageId UNTIL_THEY_WERE_BETRAYED_BY_JUDE_AND_SLAUGHTERED_IN_BATTLE;
	
	@ClientString(id = 3230, message = "Many disciples were mercilessly killed,")
	public static SystemMessageId MANY_DISCIPLES_WERE_MERCILESSLY_KILLED;
	
	@ClientString(id = 3231, message = "Solina surrendered herself and became a prisoner of war.")
	public static SystemMessageId SOLINA_SURRENDERED_HERSELF_AND_BECAME_A_PRISONER_OF_WAR;
	
	@ClientString(id = 3232, message = "Because she was sentenced to execution for treason and heresy, her remaining disciples were forced to flee and hide.")
	public static SystemMessageId BECAUSE_SHE_WAS_SENTENCED_TO_EXECUTION_FOR_TREASON_AND_HERESY_HER_REMAINING_DISCIPLES_WERE_FORCED_TO_FLEE_AND_HIDE;
	
	@ClientString(id = 3233, message = "Jude stole Solina's holy items, the Scepter of Saints and the Book of Saints, which were stored in the monastery.")
	public static SystemMessageId JUDE_STOLE_SOLINA_S_HOLY_ITEMS_THE_SCEPTER_OF_SAINTS_AND_THE_BOOK_OF_SAINTS_WHICH_WERE_STORED_IN_THE_MONASTERY;
	
	@ClientString(id = 3234, message = "Through his possession of the items, he appointed himself Chief of the Embryos.")
	public static SystemMessageId THROUGH_HIS_POSSESSION_OF_THE_ITEMS_HE_APPOINTED_HIMSELF_CHIEF_OF_THE_EMBRYOS;
	
	@ClientString(id = 3235, message = "And then began plotting a historic conspiracy for his own selfish ambitions.")
	public static SystemMessageId AND_THEN_BEGAN_PLOTTING_A_HISTORIC_CONSPIRACY_FOR_HIS_OWN_SELFISH_AMBITIONS;
	
	@ClientString(id = 3236, message = "That ignorant Spirit babbles without end,")
	public static SystemMessageId THAT_IGNORANT_SPIRIT_BABBLES_WITHOUT_END;
	
	@ClientString(id = 3237, message = "now I'll make you disappear as well.")
	public static SystemMessageId NOW_I_LL_MAKE_YOU_DISAPPEAR_AS_WELL;
	
	@ClientString(id = 3238, message = "Are you… are you Jude van Etina? No this can't be!")
	public static SystemMessageId ARE_YOU_ARE_YOU_JUDE_VAN_ETINA_NO_THIS_CAN_T_BE;
	
	@ClientString(id = 3239, message = "You'd be hundreds of years old!")
	public static SystemMessageId YOU_D_BE_HUNDREDS_OF_YEARS_OLD;
	
	@ClientString(id = 3240, message = "That's right. Jude van Etina was a petty soul that died long ago.")
	public static SystemMessageId THAT_S_RIGHT_JUDE_VAN_ETINA_WAS_A_PETTY_SOUL_THAT_DIED_LONG_AGO;
	
	@ClientString(id = 3241, message = "Now I am the successor of Embryos, Etis van Etina!")
	public static SystemMessageId NOW_I_AM_THE_SUCCESSOR_OF_EMBRYOS_ETIS_VAN_ETINA;
	
	@ClientString(id = 3242, message = "Do not forget, woman of Devastation!!")
	public static SystemMessageId DO_NOT_FORGET_WOMAN_OF_DEVASTATION;
	
	@ClientString(id = 3243, message = "This is becoming too dangerous.")
	public static SystemMessageId THIS_IS_BECOMING_TOO_DANGEROUS;
	
	@ClientString(id = 3244, message = "Take that")
	public static SystemMessageId TAKE_THAT;
	
	@ClientString(id = 3245, message = "")
	public static SystemMessageId EMPTY_9;
	
	@ClientString(id = 3246, message = "")
	public static SystemMessageId EMPTY_10;
	
	@ClientString(id = 3247, message = "Argh... Ha ha ha, pretty impressive, as if you cut from the fabric of the gods.")
	public static SystemMessageId ARGH_HA_HA_HA_PRETTY_IMPRESSIVE_AS_IF_YOU_CUT_FROM_THE_FABRIC_OF_THE_GODS;
	
	@ClientString(id = 3248, message = "Yes... You're doing well…")
	public static SystemMessageId YES_YOU_RE_DOING_WELL;
	
	@ClientString(id = 3249, message = "I don't know what you are talking about…")
	public static SystemMessageId I_DON_T_KNOW_WHAT_YOU_ARE_TALKING_ABOUT;
	
	@ClientString(id = 3250, message = "But I will not allow you to have your way so easily, Etis van Etina.")
	public static SystemMessageId BUT_I_WILL_NOT_ALLOW_YOU_TO_HAVE_YOUR_WAY_SO_EASILY_ETIS_VAN_ETINA;
	
	@ClientString(id = 3251, message = "Ah ah ah ah ah... You can talk all you want,")
	public static SystemMessageId AH_AH_AH_AH_AH_YOU_CAN_TALK_ALL_YOU_WANT;
	
	@ClientString(id = 3252, message = "if you want to waste your last precious moments babbling pointlessly,")
	public static SystemMessageId IF_YOU_WANT_TO_WASTE_YOUR_LAST_PRECIOUS_MOMENTS_BABBLING_POINTLESSLY;
	
	@ClientString(id = 3253, message = "for soon my era will begin, the era of Embryos Muah ah ah ah ah.")
	public static SystemMessageId FOR_SOON_MY_ERA_WILL_BEGIN_THE_ERA_OF_EMBRYOS_MUAH_AH_AH_AH_AH;
	
	@ClientString(id = 3254, message = "An error has occurred at the arena, and all matches will handled at no cost.")
	public static SystemMessageId AN_ERROR_HAS_OCCURRED_AT_THE_ARENA_AND_ALL_MATCHES_WILL_HANDLED_AT_NO_COST;
	
	@ClientString(id = 3255, message = "Arcane Shield decreased your MP by $s1 instead of HP.")
	public static SystemMessageId ARCANE_SHIELD_DECREASED_YOUR_MP_BY_S1_INSTEAD_OF_HP;
	
	@ClientString(id = 3256, message = "MP became 0 and the Arcane Shield is disappearing.")
	public static SystemMessageId MP_BECAME_0_AND_THE_ARCANE_SHIELD_IS_DISAPPEARING;
	
	@ClientString(id = 3257, message = "")
	public static SystemMessageId EMPTY_11;
	
	@ClientString(id = 3258, message = "")
	public static SystemMessageId EMPTY_12;
	
	@ClientString(id = 3259, message = "You have acquired $s1 EXP (Bonus: $s2) and $s3 SP (Bonus: $s4).")
	public static SystemMessageId YOU_HAVE_ACQUIRED_S1_EXP_BONUS_S2_AND_S3_SP_BONUS_S4;
	
	@ClientString(id = 3260, message = "You cannot use the skill because the servitor has not been summoned.")
	public static SystemMessageId YOU_CANNOT_USE_THE_SKILL_BECAUSE_THE_SERVITOR_HAS_NOT_BEEN_SUMMONED;
	
	@ClientString(id = 3261, message = "You have $s1 match(es) remaining that you can participate in this week ($s2 1 vs 1 Class matches, $s3 1 vs 1 matches, & $s4 3 vs 3 Team matches).")
	public static SystemMessageId YOU_HAVE_S1_MATCH_ES_REMAINING_THAT_YOU_CAN_PARTICIPATE_IN_THIS_WEEK_S2_1_VS_1_CLASS_MATCHES_S3_1_VS_1_MATCHES_S4_3_VS_3_TEAM_MATCHES;
	
	@ClientString(id = 3262, message = "You can proceed only when the inventory weight is below 80 percent and the quantity is below 90 percent.")
	public static SystemMessageId YOU_CAN_PROCEED_ONLY_WHEN_THE_INVENTORY_WEIGHT_IS_BELOW_80_PERCENT_AND_THE_QUANTITY_IS_BELOW_90_PERCENT;
	
	@ClientString(id = 3263, message = "There are $s2 seconds remaining for $s1's re-use time. It is reset every day at 6:30 AM.")
	public static SystemMessageId THERE_ARE_S2_SECONDS_REMAINING_FOR_S1_S_RE_USE_TIME_IT_IS_RESET_EVERY_DAY_AT_6_30_AM;
	
	@ClientString(id = 3264, message = "There are $s2 minutes $s3 seconds remaining for $s1's re-use time. It is reset every day at 6:30 AM.")
	public static SystemMessageId THERE_ARE_S2_MINUTES_S3_SECONDS_REMAINING_FOR_S1_S_RE_USE_TIME_IT_IS_RESET_EVERY_DAY_AT_6_30_AM;
	
	@ClientString(id = 3265, message = "There are $s2 hours $s3 minutes $s4 seconds remaining for $s1's re-use time. It is reset every day at 6:30 AM.")
	public static SystemMessageId THERE_ARE_S2_HOURS_S3_MINUTES_S4_SECONDS_REMAINING_FOR_S1_S_RE_USE_TIME_IT_IS_RESET_EVERY_DAY_AT_6_30_AM;
	
	@ClientString(id = 3266, message = "The angel Nevit has blessed you from above. You are imbued with full Vitality as well as a Vitality Replenishing effect. And should you die, you will not lose Exp!")
	public static SystemMessageId THE_ANGEL_NEVIT_HAS_BLESSED_YOU_FROM_ABOVE_YOU_ARE_IMBUED_WITH_FULL_VITALITY_AS_WELL_AS_A_VITALITY_REPLENISHING_EFFECT_AND_SHOULD_YOU_DIE_YOU_WILL_NOT_LOSE_EXP;
	
	@ClientString(id = 3267, message = "You are starting to feel the effects of Nevit's Advent Blessing.")
	public static SystemMessageId YOU_ARE_STARTING_TO_FEEL_THE_EFFECTS_OF_NEVIT_S_ADVENT_BLESSING;
	
	@ClientString(id = 3268, message = "You are further infused with the blessings of Nevit! Continue to battle evil wherever it may lurk.")
	public static SystemMessageId YOU_ARE_FURTHER_INFUSED_WITH_THE_BLESSINGS_OF_NEVIT_CONTINUE_TO_BATTLE_EVIL_WHEREVER_IT_MAY_LURK;
	
	@ClientString(id = 3269, message = "Nevit's Advent Blessing shines strongly from above. You can almost see his divine aura.")
	public static SystemMessageId NEVIT_S_ADVENT_BLESSING_SHINES_STRONGLY_FROM_ABOVE_YOU_CAN_ALMOST_SEE_HIS_DIVINE_AURA;
	
	@ClientString(id = 3270, message = "$s1 seconds remaining")
	public static SystemMessageId S1_SECONDS_REMAINING;
	
	@ClientString(id = 3271, message = "Current Progress: $s1")
	public static SystemMessageId CURRENT_PROGRESS_S1;
	
	@ClientString(id = 3272, message = "$s1")
	public static SystemMessageId S1_4;
	
	@ClientString(id = 3274, message = "Angel Nevit's Blessing")
	public static SystemMessageId ANGEL_NEVIT_S_BLESSING;
	
	@ClientString(id = 3275, message = "Nevit's Advent Blessing has ended. Continue your journey and you will surely meet his favor again sometime soon.")
	public static SystemMessageId NEVIT_S_ADVENT_BLESSING_HAS_ENDED_CONTINUE_YOUR_JOURNEY_AND_YOU_WILL_SURELY_MEET_HIS_FAVOR_AGAIN_SOMETIME_SOON;
	
	@ClientString(id = 3277, message = "Nevit's Advent Blessing: $s1")
	public static SystemMessageId NEVIT_S_ADVENT_BLESSING_S1;
	
	@ClientString(id = 3282, message = "Free acounts below level 20 cannot shout or engage in buy/sell chatting.")
	public static SystemMessageId FREE_ACOUNTS_BELOW_LEVEL_20_CANNOT_SHOUT_OR_ENGAGE_IN_BUY_SELL_CHATTING;
	
	@ClientString(id = 3289, message = "This account has already received a gift. The gift can only be given once per account.")
	public static SystemMessageId THIS_ACCOUNT_HAS_ALREADY_RECEIVED_A_GIFT_THE_GIFT_CAN_ONLY_BE_GIVEN_ONCE_PER_ACCOUNT;
	
	@ClientString(id = 3315, message = "This password is incorrect. If you enter the wrong password 5 times in a row, you will be blocked from logging on for 8 hours.\\n(Number of attempts: $s1 time(s))")
	public static SystemMessageId THIS_PASSWORD_IS_INCORRECT_IF_YOU_ENTER_THE_WRONG_PASSWORD_5_TIMES_IN_A_ROW_YOU_WILL_BE_BLOCKED_FROM_LOGGING_ON_FOR_8_HOURS_N_NUMBER_OF_ATTEMPTS_S1_TIME_S;
	
	@ClientString(id = 3316, message = "If you enter the wrong password 5 times in a row, you will be blocked from logging on for 8 hours. The block can be removed after confirming your identity on the homepage.")
	public static SystemMessageId IF_YOU_ENTER_THE_WRONG_PASSWORD_5_TIMES_IN_A_ROW_YOU_WILL_BE_BLOCKED_FROM_LOGGING_ON_FOR_8_HOURS_THE_BLOCK_CAN_BE_REMOVED_AFTER_CONFIRMING_YOUR_IDENTITY_ON_THE_HOMEPAGE;
	
	@ClientString(id = 3317, message = "The character's password can only be entered via the mouse.")
	public static SystemMessageId THE_CHARACTER_S_PASSWORD_CAN_ONLY_BE_ENTERED_VIA_THE_MOUSE;
	
	@ClientString(id = 3318, message = "The two entered passwords do not match.\\nPlease try again.")
	public static SystemMessageId THE_TWO_ENTERED_PASSWORDS_DO_NOT_MATCH_NPLEASE_TRY_AGAIN;
	
	@ClientString(id = 3319, message = "You cannot use a password that only contains one number. Please enter again.")
	public static SystemMessageId YOU_CANNOT_USE_A_PASSWORD_THAT_ONLY_CONTAINS_ONE_NUMBER_PLEASE_ENTER_AGAIN;
	
	@ClientString(id = 3320, message = "You cannot use a password that has the same numbers as your account password. Please enter again.")
	public static SystemMessageId YOU_CANNOT_USE_A_PASSWORD_THAT_HAS_THE_SAME_NUMBERS_AS_YOUR_ACCOUNT_PASSWORD_PLEASE_ENTER_AGAIN;
	
	@ClientString(id = 3321, message = "You cannot use a password that contains repeated pattern numbers. Please enter again.")
	public static SystemMessageId YOU_CANNOT_USE_A_PASSWORD_THAT_CONTAINS_REPEATED_PATTERN_NUMBERS_PLEASE_ENTER_AGAIN;
	
	@ClientString(id = 3322, message = "You have successfully registered the character's password.")
	public static SystemMessageId YOU_HAVE_SUCCESSFULLY_REGISTERED_THE_CHARACTER_S_PASSWORD;
	
	@ClientString(id = 3323, message = "After safely logging in, enter the character's password. The character's password must be entered when a character enters or is deleted. (This is registered separately from the account password.)")
	public static SystemMessageId AFTER_SAFELY_LOGGING_IN_ENTER_THE_CHARACTER_S_PASSWORD_THE_CHARACTER_S_PASSWORD_MUST_BE_ENTERED_WHEN_A_CHARACTER_ENTERS_OR_IS_DELETED_THIS_IS_REGISTERED_SEPARATELY_FROM_THE_ACCOUNT_PASSWORD;
	
	@ClientString(id = 3324, message = "Note: The number sequence randomly changes each time you log in.")
	public static SystemMessageId NOTE_THE_NUMBER_SEQUENCE_RANDOMLY_CHANGES_EACH_TIME_YOU_LOG_IN;
	
	@ClientString(id = 3325, message = "After entering the current password, please enter the new password to be used. (This is registered separately from the account password.)")
	public static SystemMessageId AFTER_ENTERING_THE_CURRENT_PASSWORD_PLEASE_ENTER_THE_NEW_PASSWORD_TO_BE_USED_THIS_IS_REGISTERED_SEPARATELY_FROM_THE_ACCOUNT_PASSWORD;
	
	@ClientString(id = 3326, message = "The offer can be withdrawn within $s1 day(s) and $s2 hour(s).")
	public static SystemMessageId THE_OFFER_CAN_BE_WITHDRAWN_WITHIN_S1_DAY_S_AND_S2_HOUR_S;
	
	@ClientString(id = 3327, message = "The offer can be withdrawn within $s1 day(s).")
	public static SystemMessageId THE_OFFER_CAN_BE_WITHDRAWN_WITHIN_S1_DAY_S;
	
	@ClientString(id = 3328, message = "The offer can be withdrawn within $s1 hour(s).")
	public static SystemMessageId THE_OFFER_CAN_BE_WITHDRAWN_WITHIN_S1_HOUR_S;
	
	@ClientString(id = 3329, message = "The offer cannot be withdrawn.")
	public static SystemMessageId THE_OFFER_CANNOT_BE_WITHDRAWN;
	
	@ClientString(id = 3330, message = "Sale Item (can retract offer within $s1 minute(s))")
	public static SystemMessageId SALE_ITEM_CAN_RETRACT_OFFER_WITHIN_S1_MINUTE_S;
	
	@ClientString(id = 3375, message = "You cannot use a password that contains continuous numbers. Please enter again.")
	public static SystemMessageId YOU_CANNOT_USE_A_PASSWORD_THAT_CONTAINS_CONTINUOUS_NUMBERS_PLEASE_ENTER_AGAIN;
	
	@ClientString(id = 3376, message = "You have entered the wrong password 5 times in a row. This account has been blocked for 8 hours. The time remaining until the block ends is $s1 hour(s). The block can be removed after confirming your identity on the homepage.")
	public static SystemMessageId YOU_HAVE_ENTERED_THE_WRONG_PASSWORD_5_TIMES_IN_A_ROW_THIS_ACCOUNT_HAS_BEEN_BLOCKED_FOR_8_HOURS_THE_TIME_REMAINING_UNTIL_THE_BLOCK_ENDS_IS_S1_HOUR_S_THE_BLOCK_CAN_BE_REMOVED_AFTER_CONFIRMING_YOUR_IDENTITY_ON_THE_HOMEPAGE;
	
	@ClientString(id = 3377, message = "There was an error in the request.")
	public static SystemMessageId THERE_WAS_AN_ERROR_IN_THE_REQUEST;
	
	@ClientString(id = 3378, message = "There are currently too many users inquiring about the product inventory. Please try again later.")
	public static SystemMessageId THERE_ARE_CURRENTLY_TOO_MANY_USERS_INQUIRING_ABOUT_THE_PRODUCT_INVENTORY_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3379, message = "The previous request has not been completed yet. Please try again later.")
	public static SystemMessageId THE_PREVIOUS_REQUEST_HAS_NOT_BEEN_COMPLETED_YET_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3380, message = "The product inventory inquiry cannot be completed. Please try again later.")
	public static SystemMessageId THE_PRODUCT_INVENTORY_INQUIRY_CANNOT_BE_COMPLETED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3381, message = "The offer on the product has already been retracted.")
	public static SystemMessageId THE_OFFER_ON_THE_PRODUCT_HAS_ALREADY_BEEN_RETRACTED;
	
	@ClientString(id = 3382, message = "The product has already been received.")
	public static SystemMessageId THE_PRODUCT_HAS_ALREADY_BEEN_RECEIVED;
	
	@ClientString(id = 3383, message = "The selected product cannot be received on this server.")
	public static SystemMessageId THE_SELECTED_PRODUCT_CANNOT_BE_RECEIVED_ON_THIS_SERVER;
	
	@ClientString(id = 3384, message = "The selected product cannot be received through this character.")
	public static SystemMessageId THE_SELECTED_PRODUCT_CANNOT_BE_RECEIVED_THROUGH_THIS_CHARACTER;
	
	@ClientString(id = 3385, message = "Due to system error, the product inventory cannot be used. Please try again later.")
	public static SystemMessageId DUE_TO_SYSTEM_ERROR_THE_PRODUCT_INVENTORY_CANNOT_BE_USED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3386, message = "The product cannot be received because the game inventory weight/quantity limit has been exceeded. It can be received only when the inventory's weight and slot count are at less than 80% capacity.")
	public static SystemMessageId THE_PRODUCT_CANNOT_BE_RECEIVED_BECAUSE_THE_GAME_INVENTORY_WEIGHT_QUANTITY_LIMIT_HAS_BEEN_EXCEEDED_IT_CAN_BE_RECEIVED_ONLY_WHEN_THE_INVENTORY_S_WEIGHT_AND_SLOT_COUNT_ARE_AT_LESS_THAN_80_CAPACITY;
	
	@ClientString(id = 3387, message = "If you receive the selected product item in your own character's inventory, it will be deemed as the same item being used, and the offer retraction will be limited.\\n\\nDo you still wish to receive the selected item?")
	public static SystemMessageId IF_YOU_RECEIVE_THE_SELECTED_PRODUCT_ITEM_IN_YOUR_OWN_CHARACTER_S_INVENTORY_IT_WILL_BE_DEEMED_AS_THE_SAME_ITEM_BEING_USED_AND_THE_OFFER_RETRACTION_WILL_BE_LIMITED_N_NDO_YOU_STILL_WISH_TO_RECEIVE_THE_SELECTED_ITEM;
	
	@ClientString(id = 3390, message = "$s1Minute")
	public static SystemMessageId S1MINUTE;
	
	@ClientString(id = 3391, message = "There is an error certifying the character's password. ($s1)")
	public static SystemMessageId THERE_IS_AN_ERROR_CERTIFYING_THE_CHARACTER_S_PASSWORD_S1;
	
	@ClientString(id = 3410, message = "There are currently too many users so the product cannot be received. Please try again later.")
	public static SystemMessageId THERE_ARE_CURRENTLY_TOO_MANY_USERS_SO_THE_PRODUCT_CANNOT_BE_RECEIVED_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3411, message = "You cannot connect to the current product management server. Please try again later.")
	public static SystemMessageId YOU_CANNOT_CONNECT_TO_THE_CURRENT_PRODUCT_MANAGEMENT_SERVER_PLEASE_TRY_AGAIN_LATER;
	
	@ClientString(id = 3412, message = "The product was successfully received. Please check your game inventory.")
	public static SystemMessageId THE_PRODUCT_WAS_SUCCESSFULLY_RECEIVED_PLEASE_CHECK_YOUR_GAME_INVENTORY;
	
	@ClientString(id = 3413, message = "The product inventory cannot be used during trading, private shop, and workshop setup.")
	public static SystemMessageId THE_PRODUCT_INVENTORY_CANNOT_BE_USED_DURING_TRADING_PRIVATE_SHOP_AND_WORKSHOP_SETUP;
	
	@ClientString(id = 3416, message = "The password registration does not conform to the policy.")
	public static SystemMessageId THE_PASSWORD_REGISTRATION_DOES_NOT_CONFORM_TO_THE_POLICY;
	
	@ClientString(id = 3417, message = "The product to be received does not exist in the current product inventory.")
	public static SystemMessageId THE_PRODUCT_TO_BE_RECEIVED_DOES_NOT_EXIST_IN_THE_CURRENT_PRODUCT_INVENTORY;
	
	@ClientString(id = 3462, message = "You've got a new product. \\nClick the icon to see it in the Product Inventory.")
	public static SystemMessageId YOU_VE_GOT_A_NEW_PRODUCT_NCLICK_THE_ICON_TO_SEE_IT_IN_THE_PRODUCT_INVENTORY;
	
	@ClientString(id = 3463, message = "Current Location : Final Emperial Tomb")
	public static SystemMessageId CURRENT_LOCATION_FINAL_EMPERIAL_TOMB;
	
	@ClientString(id = 3487, message = "The offer can be withdrawn within $s1 hour(s) $s2 minute(s).")
	public static SystemMessageId THE_OFFER_CAN_BE_WITHDRAWN_WITHIN_S1_HOUR_S_S2_MINUTE_S;
	
	@ClientString(id = 3488, message = "The offer can be withdrawn within $s1 minute(s).")
	public static SystemMessageId THE_OFFER_CAN_BE_WITHDRAWN_WITHIN_S1_MINUTE_S;
	
	@ClientString(id = 6001, message = "The item has been successfully purchased.")
	public static SystemMessageId THE_ITEM_HAS_BEEN_SUCCESSFULLY_PURCHASED;
	
	@ClientString(id = 6002, message = "The item has failed to be purchased.")
	public static SystemMessageId THE_ITEM_HAS_FAILED_TO_BE_PURCHASED;
	
	@ClientString(id = 6003, message = "The item you selected cannot be purchased. Unfortunately, the sale period ended.")
	public static SystemMessageId THE_ITEM_YOU_SELECTED_CANNOT_BE_PURCHASED_UNFORTUNATELY_THE_SALE_PERIOD_ENDED;
	
	@ClientString(id = 6004, message = "Enchant failed. The enchant level for the corresponding item will be exactly retained.")
	public static SystemMessageId ENCHANT_FAILED_THE_ENCHANT_LEVEL_FOR_THE_CORRESPONDING_ITEM_WILL_BE_EXACTLY_RETAINED;
	
	@ClientString(id = 6005, message = "Game points are not enough.")
	public static SystemMessageId GAME_POINTS_ARE_NOT_ENOUGH;
	
	@ClientString(id = 6006, message = "The item cannot be received because the inventory weight/quantity limit has been exceeded.")
	public static SystemMessageId THE_ITEM_CANNOT_BE_RECEIVED_BECAUSE_THE_INVENTORY_WEIGHT_QUANTITY_LIMIT_HAS_BEEN_EXCEEDED;
	
	@ClientString(id = 6007, message = "Product Purchase Error - The user state is not right.")
	public static SystemMessageId PRODUCT_PURCHASE_ERROR_THE_USER_STATE_IS_NOT_RIGHT;
	
	@ClientString(id = 6008, message = "Product Purchase Error - The product is not right.")
	public static SystemMessageId PRODUCT_PURCHASE_ERROR_THE_PRODUCT_IS_NOT_RIGHT;
	
	@ClientString(id = 6009, message = "Product Purchase Error - The item within the product is not right.")
	public static SystemMessageId PRODUCT_PURCHASE_ERROR_THE_ITEM_WITHIN_THE_PRODUCT_IS_NOT_RIGHT;
	
	@ClientString(id = 6010, message = "The master account of your account has been restricted.")
	public static SystemMessageId THE_MASTER_ACCOUNT_OF_YOUR_ACCOUNT_HAS_BEEN_RESTRICTED;
	
	@ClientString(id = 6011, message = "You acquired $s1 Exp. and $s2 SP. (As a reward you receive $s3% more Exp.)")
	public static SystemMessageId YOU_ACQUIRED_S1_EXP_AND_S2_SP_AS_A_REWARD_YOU_RECEIVE_S3_MORE_EXP;
	
	@ClientString(id = 6012, message = "a blessing that increases Exp. by $1 $2")
	public static SystemMessageId A_BLESSING_THAT_INCREASES_EXP_BY_1_2;
	
	@ClientString(id = 6013, message = "It is not a blessing period. When you reach today's target, you can receive $s1%.")
	public static SystemMessageId IT_IS_NOT_A_BLESSING_PERIOD_WHEN_YOU_REACH_TODAY_S_TARGET_YOU_CAN_RECEIVE_S1;
	
	@ClientString(id = 6014, message = "It is Eva's blessing period. $s1% will be effective until $s2.")
	public static SystemMessageId IT_IS_EVA_S_BLESSING_PERIOD_S1_WILL_BE_EFFECTIVE_UNTIL_S2;
	
	@ClientString(id = 6015, message = "It is Eva's blessing period. Until $s1, Jack Sage can gift you with $s2%.")
	public static SystemMessageId IT_IS_EVA_S_BLESSING_PERIOD_UNTIL_S1_JACK_SAGE_CAN_GIFT_YOU_WITH_S2;
	
	@ClientString(id = 6016, message = "Progress: Event stage $s1.")
	public static SystemMessageId PROGRESS_EVENT_STAGE_S1;
	
	@ClientString(id = 6017, message = "Eva's Blessing Stage $s1 has begun.")
	public static SystemMessageId EVA_S_BLESSING_STAGE_S1_HAS_BEGUN;
	
	@ClientString(id = 6018, message = "Eva's Blessing Stage $s1 has ended.")
	public static SystemMessageId EVA_S_BLESSING_STAGE_S1_HAS_ENDED;
	
	@ClientString(id = 6019, message = "You cannot buy the item on this day of the week.")
	public static SystemMessageId YOU_CANNOT_BUY_THE_ITEM_ON_THIS_DAY_OF_THE_WEEK;
	
	@ClientString(id = 6020, message = "You cannot buy the item at this hour.")
	public static SystemMessageId YOU_CANNOT_BUY_THE_ITEM_AT_THIS_HOUR;
	
	@ClientString(id = 6021, message = "%s1 reached %s2 consecutive wins in Jack Game.")
	public static SystemMessageId S1_REACHED_S2_CONSECUTIVE_WINS_IN_JACK_GAME;
	
	@ClientString(id = 6022, message = "$s1 received $s4 $s3 as reward for $s2 consecutive wins.")
	public static SystemMessageId S1_RECEIVED_S4_S3_AS_REWARD_FOR_S2_CONSECUTIVE_WINS;
	
	@ClientString(id = 6023, message = "World: $s1 consecutive wins ($s2 ppl.)")
	public static SystemMessageId WORLD_S1_CONSECUTIVE_WINS_S2_PPL;
	
	@ClientString(id = 6024, message = "My Record: $s1 consecutive wins")
	public static SystemMessageId MY_RECORD_S1_CONSECUTIVE_WINS;
	
	@ClientString(id = 6025, message = "World: Under 4 consecutive wins")
	public static SystemMessageId WORLD_UNDER_4_CONSECUTIVE_WINS;
	
	@ClientString(id = 6026, message = "My Record: Under 4 consecutive wins")
	public static SystemMessageId MY_RECORD_UNDER_4_CONSECUTIVE_WINS;
	
	@ClientString(id = 6027, message = "It's Halloween Event period.")
	public static SystemMessageId IT_S_HALLOWEEN_EVENT_PERIOD;
	
	@ClientString(id = 6028, message = "No record over 10 consecutive wins.")
	public static SystemMessageId NO_RECORD_OVER_10_CONSECUTIVE_WINS;
	
	@ClientString(id = 6029, message = "Please help raise reindeer for Santa's Christmas delivery!")
	public static SystemMessageId PLEASE_HELP_RAISE_REINDEER_FOR_SANTA_S_CHRISTMAS_DELIVERY;
	
	@ClientString(id = 6030, message = "Santa has started delivering the Christmas gifts to Aden!")
	public static SystemMessageId SANTA_HAS_STARTED_DELIVERING_THE_CHRISTMAS_GIFTS_TO_ADEN;
	
	@ClientString(id = 6031, message = "Santa has completed the deliveries! See you in 1 hour!")
	public static SystemMessageId SANTA_HAS_COMPLETED_THE_DELIVERIES_SEE_YOU_IN_1_HOUR;
	
	@ClientString(id = 6032, message = "Santa is out delivering the gifts. Merry Christmas!")
	public static SystemMessageId SANTA_IS_OUT_DELIVERING_THE_GIFTS_MERRY_CHRISTMAS;
	
	@ClientString(id = 6033, message = "Only the top $s1 appear in the ranking, and only the top $s2 are recorded in My Best Ranking.")
	public static SystemMessageId ONLY_THE_TOP_S1_APPEAR_IN_THE_RANKING_AND_ONLY_THE_TOP_S2_ARE_RECORDED_IN_MY_BEST_RANKING;
	
	@ClientString(id = 6034, message = "$s1 have/has been initialized.")
	public static SystemMessageId S1_HAVE_HAS_BEEN_INITIALIZED;
	
	@ClientString(id = 6035, message = "When there are many players with the same score, they appear in the order in which they were achieved.")
	public static SystemMessageId WHEN_THERE_ARE_MANY_PLAYERS_WITH_THE_SAME_SCORE_THEY_APPEAR_IN_THE_ORDER_IN_WHICH_THEY_WERE_ACHIEVED;
	
	@ClientString(id = 6036, message = "Below $s1 points")
	public static SystemMessageId BELOW_S1_POINTS;
	
	@ClientString(id = 6037, message = "The Lovers' Jubilee has begun!")
	public static SystemMessageId THE_LOVERS_JUBILEE_HAS_BEGUN;
	
	@ClientString(id = 6038, message = "You can use Evangelist Mark. (/Evangelist on/off is used to toggle)")
	public static SystemMessageId YOU_CAN_USE_EVANGELIST_MARK_EVANGELIST_ON_OFF_IS_USED_TO_TOGGLE;
	
	@ClientString(id = 6039, message = "You have completed the initial level. Congratulations~!")
	public static SystemMessageId YOU_HAVE_COMPLETED_THE_INITIAL_LEVEL_CONGRATULATIONS;
	
	@ClientString(id = 6040, message = "Please type 'on/off' after the command.")
	public static SystemMessageId PLEASE_TYPE_ON_OFF_AFTER_THE_COMMAND;
	
	@ClientString(id = 6041, message = "This is the April Fools' Day event period.")
	public static SystemMessageId THIS_IS_THE_APRIL_FOOLS_DAY_EVENT_PERIOD;
	
	@ClientString(id = 6042, message = "The skill was canceled due to insufficient energy.")
	public static SystemMessageId THE_SKILL_WAS_CANCELED_DUE_TO_INSUFFICIENT_ENERGY;
	
	@ClientString(id = 6043, message = "You cannot replenish energy because you do not meet the requirements.")
	public static SystemMessageId YOU_CANNOT_REPLENISH_ENERGY_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS;
	
	@ClientString(id = 6044, message = "Energy was replenished by $s1.")
	public static SystemMessageId ENERGY_WAS_REPLENISHED_BY_S1;
	
	@ClientString(id = 6045, message = "$c1 acquired the Special Gift of April Fool's Day.")
	public static SystemMessageId C1_ACQUIRED_THE_SPECIAL_GIFT_OF_APRIL_FOOL_S_DAY;
	
	@ClientString(id = 6046, message = "The premium item for this account was provided. If the premium account is terminated, this item will be deleted.")
	public static SystemMessageId THE_PREMIUM_ITEM_FOR_THIS_ACCOUNT_WAS_PROVIDED_IF_THE_PREMIUM_ACCOUNT_IS_TERMINATED_THIS_ITEM_WILL_BE_DELETED;
	
	@ClientString(id = 6047, message = "The premium item cannot be received because the inventory weight/quantity limit has been exceeded.")
	public static SystemMessageId THE_PREMIUM_ITEM_CANNOT_BE_RECEIVED_BECAUSE_THE_INVENTORY_WEIGHT_QUANTITY_LIMIT_HAS_BEEN_EXCEEDED;
	
	@ClientString(id = 6048, message = "The remium account has been terminated. The provided premium item was deleted.")
	public static SystemMessageId THE_REMIUM_ACCOUNT_HAS_BEEN_TERMINATED_THE_PROVIDED_PREMIUM_ITEM_WAS_DELETED;
	
	@ClientString(id = 6049, message = "$s1% is on the your Ignore List. In order to whisper, they must be removed from your Ignore List. \\n\\nDo you wish to remove $s1% from your Ignore List?")
	public static SystemMessageId S1_IS_ON_THE_YOUR_IGNORE_LIST_IN_ORDER_TO_WHISPER_THEY_MUST_BE_REMOVED_FROM_YOUR_IGNORE_LIST_N_NDO_YOU_WISH_TO_REMOVE_S1_FROM_YOUR_IGNORE_LIST;
	
	@ClientString(id = 6050, message = "If you have a Maestro's Key, you can use it to open the treasure chest.")
	public static SystemMessageId IF_YOU_HAVE_A_MAESTRO_S_KEY_YOU_CAN_USE_IT_TO_OPEN_THE_TREASURE_CHEST;
	
	@ClientString(id = 6051, message = "'You can't log in with an unregistered PC.'")
	public static SystemMessageId YOU_CAN_T_LOG_IN_WITH_AN_UNREGISTERED_PC;
	
	@ClientString(id = 6052, message = "You've received $s1 gift(s) today, and you can receive $s2 more. The gift supply time is initialized at 6:30 am everyday.")
	public static SystemMessageId YOU_VE_RECEIVED_S1_GIFT_S_TODAY_AND_YOU_CAN_RECEIVE_S2_MORE_THE_GIFT_SUPPLY_TIME_IS_INITIALIZED_AT_6_30_AM_EVERYDAY;
	
	@ClientString(id = 6053, message = "You earned additional $s1 Exp. as PC Café Bonus.")
	public static SystemMessageId YOU_EARNED_ADDITIONAL_S1_EXP_AS_PC_CAF_BONUS;
	
	@ClientString(id = 6054, message = "You earned additional $s1 Reputation Points as PC Café Bonus.")
	public static SystemMessageId YOU_EARNED_ADDITIONAL_S1_REPUTATION_POINTS_AS_PC_CAF_BONUS;
	
	@ClientString(id = 6055, message = "Requirements are not satisfied. The clan membership cannot be changed.")
	public static SystemMessageId REQUIREMENTS_ARE_NOT_SATISFIED_THE_CLAN_MEMBERSHIP_CANNOT_BE_CHANGED;
	
	@ClientString(id = 6056, message = "$s1 (Using $s3 out of $s2)")
	public static SystemMessageId S1_USING_S3_OUT_OF_S2;
	
	@ClientString(id = 6057, message = "You couldn't obtain the PC Café access reward item. Please secure 10% or more of the inventory space and avoid Weight Penalty. (The item will be delivered again every five minutes.)")
	public static SystemMessageId YOU_COULDN_T_OBTAIN_THE_PC_CAF_ACCESS_REWARD_ITEM_PLEASE_SECURE_10_OR_MORE_OF_THE_INVENTORY_SPACE_AND_AVOID_WEIGHT_PENALTY_THE_ITEM_WILL_BE_DELIVERED_AGAIN_EVERY_FIVE_MINUTES;
	
	@ClientString(id = 6058, message = "You need to verify your email. Please check the email sent to your registered email account.")
	public static SystemMessageId YOU_NEED_TO_VERIFY_YOUR_EMAIL_PLEASE_CHECK_THE_EMAIL_SENT_TO_YOUR_REGISTERED_EMAIL_ACCOUNT;
	
	@ClientString(id = 6059, message = "ID or email")
	public static SystemMessageId ID_OR_EMAIL;
	
	@ClientString(id = 6060, message = "Password (up to 16 characters)")
	public static SystemMessageId PASSWORD_UP_TO_16_CHARACTERS;
	
	@ClientString(id = 6061, message = "Hero Chat is banned.")
	public static SystemMessageId HERO_CHAT_IS_BANNED;
	
	@ClientString(id = 6062, message = "Hero Chat is currently available.")
	public static SystemMessageId HERO_CHAT_IS_CURRENTLY_AVAILABLE;
	
	@ClientString(id = 6063, message = "Hero Chat has ended. You have $s1 minute(s) remaining until the next available session.")
	public static SystemMessageId HERO_CHAT_HAS_ENDED_YOU_HAVE_S1_MINUTE_S_REMAINING_UNTIL_THE_NEXT_AVAILABLE_SESSION;
	
	@ClientString(id = 6501, message = "You cannot bookmark this location because you do not have a My Teleport Flag.")
	public static SystemMessageId YOU_CANNOT_BOOKMARK_THIS_LOCATION_BECAUSE_YOU_DO_NOT_HAVE_A_MY_TELEPORT_FLAG;
	
	@ClientString(id = 6502, message = "My Teleport Flag: $s1")
	public static SystemMessageId MY_TELEPORT_FLAG_S1;
	
	@ClientString(id = 6503, message = "The evil Thomas D. Turkey has appeared. Please save Santa.")
	public static SystemMessageId THE_EVIL_THOMAS_D_TURKEY_HAS_APPEARED_PLEASE_SAVE_SANTA;
	
	@ClientString(id = 6504, message = "You won the battle against Thomas D. Turkey. Santa has been rescued.")
	public static SystemMessageId YOU_WON_THE_BATTLE_AGAINST_THOMAS_D_TURKEY_SANTA_HAS_BEEN_RESCUED;
	
	@ClientString(id = 6505, message = "You did not rescue Santa, and Thomas D. Turkey has disappeared.")
	public static SystemMessageId YOU_DID_NOT_RESCUE_SANTA_AND_THOMAS_D_TURKEY_HAS_DISAPPEARED;
	
	@ClientString(id = 6506, message = "Although you can't be certain, the air seems laden with the scent of freshly baked bread.")
	public static SystemMessageId ALTHOUGH_YOU_CAN_T_BE_CERTAIN_THE_AIR_SEEMS_LADEN_WITH_THE_SCENT_OF_FRESHLY_BAKED_BREAD;
	
	@ClientString(id = 6507, message = "You feel refreshed. Everything appears clear.")
	public static SystemMessageId YOU_FEEL_REFRESHED_EVERYTHING_APPEARS_CLEAR;
	
	static
	{
		buildFastLookupTable();
	}
	
	private static void buildFastLookupTable()
	{
		for (Field field : SystemMessageId.class.getDeclaredFields())
		{
			final int mod = field.getModifiers();
			if (Modifier.isStatic(mod) && Modifier.isPublic(mod) && field.getType().equals(SystemMessageId.class) && field.isAnnotationPresent(ClientString.class))
			{
				try
				{
					final ClientString annotation = field.getAnnotationsByType(ClientString.class)[0];
					final SystemMessageId smId = new SystemMessageId(annotation.id());
					smId.setName(field.getName());
					smId.setParamCount(parseMessageParameters(field.getName()));
					field.set(null, smId);
					VALUES.put(smId.getId(), smId);
				}
				catch (Exception e)
				{
					LOGGER.log(Level.WARNING, "SystemMessageId: Failed field access for '" + field.getName() + "'", e);
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
	
	public static SystemMessageId getSystemMessageId(int id)
	{
		final SystemMessageId smi = getSystemMessageIdInternal(id);
		return smi == null ? new SystemMessageId(id) : smi;
	}
	
	private static SystemMessageId getSystemMessageIdInternal(int id)
	{
		return VALUES.get(id);
	}
	
	public static SystemMessageId getSystemMessageId(String name)
	{
		try
		{
			return (SystemMessageId) SystemMessageId.class.getField(name).get(null);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static void reloadLocalisations()
	{
		for (SystemMessageId smId : VALUES.values())
		{
			if (smId != null)
			{
				smId.removeAllLocalisations();
			}
		}
		
		if (!Config.MULTILANG_SM_ENABLE)
		{
			LOGGER.log(Level.INFO, "SystemMessageId: MultiLanguage disabled.");
			return;
		}
		
		final List<String> languages = Config.MULTILANG_SM_ALLOWED;
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringComments(true);
		
		File file;
		Node node;
		Document doc;
		NamedNodeMap nnmb;
		SystemMessageId smId;
		String text;
		for (String lang : languages)
		{
			file = new File(Config.DATAPACK_ROOT, "/lang/" + lang + "/sm/SystemMessageLocalisation.xml");
			if (!file.isFile())
			{
				continue;
			}
			
			LOGGER.log(Level.INFO, "SystemMessageId: Loading localisation for '" + lang + "'");
			
			try
			{
				doc = factory.newDocumentBuilder().parse(file);
				for (Node na = doc.getFirstChild(); na != null; na = na.getNextSibling())
				{
					if ("list".equals(na.getNodeName()))
					{
						for (Node nb = na.getFirstChild(); nb != null; nb = nb.getNextSibling())
						{
							if ("sm".equals(nb.getNodeName()))
							{
								nnmb = nb.getAttributes();
								node = nnmb.getNamedItem("id");
								if (node != null)
								{
									smId = getSystemMessageId(Integer.parseInt(node.getNodeValue()));
								}
								else
								{
									node = nnmb.getNamedItem("name");
									smId = getSystemMessageId(node.getNodeValue());
								}
								if (smId == null)
								{
									LOGGER.log(Level.WARNING, "SystemMessageId: Unknown SMID '" + node.getNodeValue() + "', lang '" + lang + "'.");
									continue;
								}
								
								node = nnmb.getNamedItem("text");
								if (node == null)
								{
									LOGGER.log(Level.WARNING, "SystemMessageId: No text defined for SMID '" + smId + "', lang '" + lang + "'.");
									continue;
								}
								
								text = node.getNodeValue();
								if (text.isEmpty() || (text.length() > 255))
								{
									LOGGER.log(Level.WARNING, "SystemMessageId: Invalid text defined for SMID '" + smId + "' (to long or empty), lang '" + lang + "'.");
									continue;
								}
								
								smId.attachLocalizedText(lang, text);
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "SystemMessageId: Failed loading '" + file + "'", e);
			}
		}
	}
	
	private final int _id;
	private String _name;
	private byte _params;
	private SMLocalisation[] _localisations;
	private SystemMessage _staticSystemMessage;
	
	private SystemMessageId(int id)
	{
		_id = id;
		_localisations = EMPTY_SML_ARRAY;
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
			_staticSystemMessage = null;
		}
		
		_params = (byte) params;
	}
	
	public final SMLocalisation getLocalisation(String lang)
	{
		SMLocalisation sml;
		for (int i = _localisations.length; i-- > 0;)
		{
			sml = _localisations[i];
			if (sml.getLanguage().hashCode() == lang.hashCode())
			{
				return sml;
			}
		}
		return null;
	}
	
	public final void attachLocalizedText(String lang, String text)
	{
		final int length = _localisations.length;
		final SMLocalisation[] localisations = Arrays.copyOf(_localisations, length + 1);
		localisations[length] = new SMLocalisation(lang, text);
		_localisations = localisations;
	}
	
	public final void removeAllLocalisations()
	{
		_localisations = EMPTY_SML_ARRAY;
	}
	
	public final SystemMessage getStaticSystemMessage()
	{
		return _staticSystemMessage;
	}
	
	public final void setStaticSystemMessage(SystemMessage sm)
	{
		_staticSystemMessage = sm;
	}
	
	@Override
	public final String toString()
	{
		return "SM[" + getId() + ":" + getName() + "]";
	}
	
	public static final class SMLocalisation
	{
		private final String _lang;
		private final Builder _builder;
		
		public SMLocalisation(String lang, String text)
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

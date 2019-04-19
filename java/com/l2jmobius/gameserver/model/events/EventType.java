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
package com.l2jmobius.gameserver.model.events;

import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.model.events.impl.IBaseEvent;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureAttack;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureAttackAvoid;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureAttacked;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureDamageDealt;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureDamageReceived;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureKill;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureSkillUse;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureTeleported;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureZoneEnter;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureZoneExit;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcCanBeSeen;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcCreatureSee;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcEventReceived;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcFirstTalk;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcManorBypass;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcMoveFinished;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcMoveNodeArrived;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcMoveRouteFinished;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcSkillFinished;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcSkillSee;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcSpawn;
import com.l2jmobius.gameserver.model.events.impl.character.npc.OnNpcTeleport;
import com.l2jmobius.gameserver.model.events.impl.character.npc.attackable.OnAttackableAggroRangeEnter;
import com.l2jmobius.gameserver.model.events.impl.character.npc.attackable.OnAttackableAttack;
import com.l2jmobius.gameserver.model.events.impl.character.npc.attackable.OnAttackableFactionCall;
import com.l2jmobius.gameserver.model.events.impl.character.npc.attackable.OnAttackableHate;
import com.l2jmobius.gameserver.model.events.impl.character.npc.attackable.OnAttackableKill;
import com.l2jmobius.gameserver.model.events.impl.character.playable.OnPlayableExpChanged;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerAugment;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerBypass;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerChat;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerCreate;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerDelete;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerDlgAnswer;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerEquipItem;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerFameChanged;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerHennaAdd;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerHennaRemove;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerKarmaChanged;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerLevelChanged;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerLogin;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerLogout;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerMoveRequest;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerPKChanged;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerProfessionCancel;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerProfessionChange;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerPvPChanged;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerPvPKill;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerRestore;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerSelect;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerSkillLearn;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerSummonSpawn;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerSummonTalk;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerTransform;
import com.l2jmobius.gameserver.model.events.impl.character.player.clan.OnPlayerClanCreate;
import com.l2jmobius.gameserver.model.events.impl.character.player.clan.OnPlayerClanDestroy;
import com.l2jmobius.gameserver.model.events.impl.character.player.clan.OnPlayerClanJoin;
import com.l2jmobius.gameserver.model.events.impl.character.player.clan.OnPlayerClanLeaderChange;
import com.l2jmobius.gameserver.model.events.impl.character.player.clan.OnPlayerClanLeft;
import com.l2jmobius.gameserver.model.events.impl.character.player.clan.OnPlayerClanLvlUp;
import com.l2jmobius.gameserver.model.events.impl.character.player.clanwh.OnPlayerClanWHItemAdd;
import com.l2jmobius.gameserver.model.events.impl.character.player.clanwh.OnPlayerClanWHItemDestroy;
import com.l2jmobius.gameserver.model.events.impl.character.player.clanwh.OnPlayerClanWHItemTransfer;
import com.l2jmobius.gameserver.model.events.impl.character.player.inventory.OnPlayerItemAdd;
import com.l2jmobius.gameserver.model.events.impl.character.player.inventory.OnPlayerItemDestroy;
import com.l2jmobius.gameserver.model.events.impl.character.player.inventory.OnPlayerItemDrop;
import com.l2jmobius.gameserver.model.events.impl.character.player.inventory.OnPlayerItemPickup;
import com.l2jmobius.gameserver.model.events.impl.character.player.inventory.OnPlayerItemTransfer;
import com.l2jmobius.gameserver.model.events.impl.character.trap.OnTrapAction;
import com.l2jmobius.gameserver.model.events.impl.clan.OnClanWarFinish;
import com.l2jmobius.gameserver.model.events.impl.clan.OnClanWarStart;
import com.l2jmobius.gameserver.model.events.impl.events.OnTvTEventFinish;
import com.l2jmobius.gameserver.model.events.impl.events.OnTvTEventKill;
import com.l2jmobius.gameserver.model.events.impl.events.OnTvTEventRegistrationStart;
import com.l2jmobius.gameserver.model.events.impl.events.OnTvTEventStart;
import com.l2jmobius.gameserver.model.events.impl.item.OnItemBypassEvent;
import com.l2jmobius.gameserver.model.events.impl.item.OnItemCreate;
import com.l2jmobius.gameserver.model.events.impl.item.OnItemTalk;
import com.l2jmobius.gameserver.model.events.impl.olympiad.OnOlympiadMatchResult;
import com.l2jmobius.gameserver.model.events.impl.sieges.castle.OnCastleSiegeFinish;
import com.l2jmobius.gameserver.model.events.impl.sieges.castle.OnCastleSiegeOwnerChange;
import com.l2jmobius.gameserver.model.events.impl.sieges.castle.OnCastleSiegeStart;
import com.l2jmobius.gameserver.model.events.impl.sieges.fort.OnFortSiegeFinish;
import com.l2jmobius.gameserver.model.events.impl.sieges.fort.OnFortSiegeStart;
import com.l2jmobius.gameserver.model.events.returns.ChatFilterReturn;
import com.l2jmobius.gameserver.model.events.returns.TerminateReturn;

/**
 * @author UnAfraid
 */
public enum EventType
{
	// Attackable events
	ON_ATTACKABLE_AGGRO_RANGE_ENTER(OnAttackableAggroRangeEnter.class, void.class),
	ON_ATTACKABLE_ATTACK(OnAttackableAttack.class, void.class),
	ON_ATTACKABLE_FACTION_CALL(OnAttackableFactionCall.class, void.class),
	ON_ATTACKABLE_KILL(OnAttackableKill.class, void.class),
	
	// Castle events
	ON_CASTLE_SIEGE_FINISH(OnCastleSiegeFinish.class, void.class),
	ON_CASTLE_SIEGE_OWNER_CHANGE(OnCastleSiegeOwnerChange.class, void.class),
	ON_CASTLE_SIEGE_START(OnCastleSiegeStart.class, void.class),
	
	// Clan events
	ON_CLAN_WAR_FINISH(OnClanWarFinish.class, void.class),
	ON_CLAN_WAR_START(OnClanWarStart.class, void.class),
	
	// Creature events
	ON_CREATURE_ATTACK(OnCreatureAttack.class, void.class, TerminateReturn.class),
	ON_CREATURE_ATTACK_AVOID(OnCreatureAttackAvoid.class, void.class, void.class),
	ON_CREATURE_ATTACKED(OnCreatureAttacked.class, void.class, TerminateReturn.class),
	ON_CREATURE_DAMAGE_RECEIVED(OnCreatureDamageReceived.class, void.class),
	ON_CREATURE_DAMAGE_DEALT(OnCreatureDamageDealt.class, void.class),
	ON_CREATURE_KILL(OnCreatureKill.class, void.class, TerminateReturn.class),
	ON_CREATURE_SKILL_USE(OnCreatureSkillUse.class, void.class, TerminateReturn.class),
	ON_CREATURE_TELEPORTED(OnCreatureTeleported.class, void.class),
	ON_CREATURE_ZONE_ENTER(OnCreatureZoneEnter.class, void.class),
	ON_CREATURE_ZONE_EXIT(OnCreatureZoneExit.class, void.class),
	
	// Fortress events
	ON_FORT_SIEGE_FINISH(OnFortSiegeFinish.class, void.class),
	ON_FORT_SIEGE_START(OnFortSiegeStart.class, void.class),
	
	// Item events
	ON_ITEM_BYPASS_EVENT(OnItemBypassEvent.class, void.class),
	ON_ITEM_CREATE(OnItemCreate.class, void.class),
	ON_ITEM_TALK(OnItemTalk.class, void.class),
	
	// NPC events
	ON_NPC_CAN_BE_SEEN(OnNpcCanBeSeen.class, void.class, TerminateReturn.class),
	ON_NPC_CREATURE_SEE(OnNpcCreatureSee.class, void.class),
	ON_NPC_EVENT_RECEIVED(OnNpcEventReceived.class, void.class),
	ON_NPC_FIRST_TALK(OnNpcFirstTalk.class, void.class),
	ON_NPC_HATE(OnAttackableHate.class, void.class, TerminateReturn.class),
	ON_NPC_MOVE_FINISHED(OnNpcMoveFinished.class, void.class),
	ON_NPC_MOVE_NODE_ARRIVED(OnNpcMoveNodeArrived.class, void.class),
	ON_NPC_MOVE_ROUTE_FINISHED(OnNpcMoveRouteFinished.class, void.class),
	ON_NPC_QUEST_START(null, void.class),
	ON_NPC_SKILL_FINISHED(OnNpcSkillFinished.class, void.class),
	ON_NPC_SKILL_SEE(OnNpcSkillSee.class, void.class),
	ON_NPC_SPAWN(OnNpcSpawn.class, void.class),
	ON_NPC_TALK(null, void.class),
	ON_NPC_TELEPORT(OnNpcTeleport.class, void.class),
	ON_NPC_MANOR_BYPASS(OnNpcManorBypass.class, void.class),
	
	// Olympiad events
	ON_OLYMPIAD_MATCH_RESULT(OnOlympiadMatchResult.class, void.class),
	
	// Playable events
	ON_PLAYABLE_EXP_CHANGED(OnPlayableExpChanged.class, void.class, TerminateReturn.class),
	
	// Player events
	ON_PLAYER_AUGMENT(OnPlayerAugment.class, void.class),
	ON_PLAYER_BYPASS(OnPlayerBypass.class, void.class),
	ON_PLAYER_CHAT(OnPlayerChat.class, void.class, ChatFilterReturn.class),
	// Clan events
	ON_PLAYER_CLAN_CREATE(OnPlayerClanCreate.class, void.class),
	ON_PLAYER_CLAN_DESTROY(OnPlayerClanDestroy.class, void.class),
	ON_PLAYER_CLAN_JOIN(OnPlayerClanJoin.class, void.class),
	ON_PLAYER_CLAN_LEADER_CHANGE(OnPlayerClanLeaderChange.class, void.class),
	ON_PLAYER_CLAN_LEFT(OnPlayerClanLeft.class, void.class),
	ON_PLAYER_CLAN_LVLUP(OnPlayerClanLvlUp.class, void.class),
	// Clan warehouse events
	ON_PLAYER_CLAN_WH_ITEM_ADD(OnPlayerClanWHItemAdd.class, void.class),
	ON_PLAYER_CLAN_WH_ITEM_DESTROY(OnPlayerClanWHItemDestroy.class, void.class),
	ON_PLAYER_CLAN_WH_ITEM_TRANSFER(OnPlayerClanWHItemTransfer.class, void.class),
	ON_PLAYER_CREATE(OnPlayerCreate.class, void.class),
	ON_PLAYER_DELETE(OnPlayerDelete.class, void.class),
	ON_PLAYER_DLG_ANSWER(OnPlayerDlgAnswer.class, void.class, TerminateReturn.class),
	ON_PLAYER_EQUIP_ITEM(OnPlayerEquipItem.class, void.class),
	ON_PLAYER_FAME_CHANGED(OnPlayerFameChanged.class, void.class),
	// Henna events
	ON_PLAYER_HENNA_ADD(OnPlayerHennaAdd.class, void.class),
	ON_PLAYER_HENNA_REMOVE(OnPlayerHennaRemove.class, void.class),
	// Inventory events
	ON_PLAYER_ITEM_ADD(OnPlayerItemAdd.class, void.class),
	ON_PLAYER_ITEM_DESTROY(OnPlayerItemDestroy.class, void.class),
	ON_PLAYER_ITEM_DROP(OnPlayerItemDrop.class, void.class),
	ON_PLAYER_ITEM_PICKUP(OnPlayerItemPickup.class, void.class),
	ON_PLAYER_ITEM_TRANSFER(OnPlayerItemTransfer.class, void.class),
	// Other player events
	ON_PLAYER_KARMA_CHANGED(OnPlayerKarmaChanged.class, void.class),
	ON_PLAYER_LEVEL_CHANGED(OnPlayerLevelChanged.class, void.class),
	ON_PLAYER_LOGIN(OnPlayerLogin.class, void.class),
	ON_PLAYER_LOGOUT(OnPlayerLogout.class, void.class),
	ON_PLAYER_PK_CHANGED(OnPlayerPKChanged.class, void.class),
	ON_PLAYER_MOVE_REQUEST(OnPlayerMoveRequest.class, void.class, TerminateReturn.class),
	ON_PLAYER_PROFESSION_CHANGE(OnPlayerProfessionChange.class, void.class),
	ON_PLAYER_PROFESSION_CANCEL(OnPlayerProfessionCancel.class, void.class),
	ON_PLAYER_PVP_CHANGED(OnPlayerPvPChanged.class, void.class),
	ON_PLAYER_PVP_KILL(OnPlayerPvPKill.class, void.class),
	ON_PLAYER_RESTORE(OnPlayerRestore.class, void.class),
	ON_PLAYER_SELECT(OnPlayerSelect.class, void.class, TerminateReturn.class),
	ON_PLAYER_SKILL_LEARN(OnPlayerSkillLearn.class, void.class),
	ON_PLAYER_SUMMON_SPAWN(OnPlayerSummonSpawn.class, void.class),
	ON_PLAYER_SUMMON_TALK(OnPlayerSummonTalk.class, void.class),
	ON_PLAYER_TRANSFORM(OnPlayerTransform.class, void.class),
	
	// Trap events
	ON_TRAP_ACTION(OnTrapAction.class, void.class),
	
	// TvT events.
	ON_TVT_EVENT_FINISH(OnTvTEventFinish.class, void.class),
	ON_TVT_EVENT_KILL(OnTvTEventKill.class, void.class),
	ON_TVT_EVENT_REGISTRATION_START(OnTvTEventRegistrationStart.class, void.class),
	ON_TVT_EVENT_START(OnTvTEventStart.class, void.class);
	
	private final Class<? extends IBaseEvent> _eventClass;
	private final Class<?>[] _returnClass;
	
	private EventType(Class<? extends IBaseEvent> eventClass, Class<?>... returnClasss)
	{
		_eventClass = eventClass;
		_returnClass = returnClasss;
	}
	
	public Class<? extends IBaseEvent> getEventClass()
	{
		return _eventClass;
	}
	
	public Class<?>[] getReturnClasses()
	{
		return _returnClass;
	}
	
	public boolean isEventClass(Class<?> clazz)
	{
		return _eventClass == clazz;
	}
	
	public boolean isReturnClass(Class<?> clazz)
	{
		return CommonUtil.contains(_returnClass, clazz);
	}
}

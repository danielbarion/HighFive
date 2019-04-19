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
package com.l2jmobius.gameserver.ai;

import static com.l2jmobius.gameserver.ai.CtrlIntention.AI_INTENTION_ACTIVE;
import static com.l2jmobius.gameserver.ai.CtrlIntention.AI_INTENTION_ATTACK;
import static com.l2jmobius.gameserver.ai.CtrlIntention.AI_INTENTION_IDLE;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.GameTimeController;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.AISkillScope;
import com.l2jmobius.gameserver.enums.AIType;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.instancemanager.DimensionalRiftManager;
import com.l2jmobius.gameserver.instancemanager.ItemsOnGroundManager;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.L2Playable;
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2FestivalMonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2FriendlyMobInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2GrandBossInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2GuardInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2RaidBossInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2RiftInvaderInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2StaticObjectInstance;
import com.l2jmobius.gameserver.model.effects.L2EffectType;
import com.l2jmobius.gameserver.model.events.EventDispatcher;
import com.l2jmobius.gameserver.model.events.impl.character.npc.attackable.OnAttackableFactionCall;
import com.l2jmobius.gameserver.model.events.impl.character.npc.attackable.OnAttackableHate;
import com.l2jmobius.gameserver.model.events.returns.TerminateReturn;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.skills.AbnormalVisualEffect;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.skills.targets.L2TargetType;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.util.Util;

/**
 * This class manages AI of L2Attackable.
 * @author Zoey76
 */
public class L2AttackableAI extends L2CharacterAI
{
	// private static final Logger LOGGER = Logger.getLogger(L2AttackableAI.class.getName());
	
	/**
	 * Fear task.
	 * @author Zoey76
	 */
	public static class FearTask implements Runnable
	{
		private final L2AttackableAI _ai;
		private final L2Character _effector;
		private boolean _start;
		
		public FearTask(L2AttackableAI ai, L2Character effector, boolean start)
		{
			_ai = ai;
			_effector = effector;
			_start = start;
		}
		
		@Override
		public void run()
		{
			if (_effector != null)
			{
				final int fearTimeLeft = _ai.getFearTime() - FEAR_TICKS;
				_ai.setFearTime(fearTimeLeft);
				_ai.onEvtAfraid(_effector, _start);
				_start = false;
			}
		}
	}
	
	protected static final int FEAR_TICKS = 5;
	private static final int RANDOM_WALK_RATE = 30; // confirmed
	// private static final int MAX_DRIFT_RANGE = 300;
	private static final int MAX_ATTACK_TIMEOUT = 1200; // int ticks, i.e. 2min
	/** The L2Attackable AI task executed every 1s (call onEvtThink method). */
	private Future<?> _aiTask;
	/** The delay after which the attacked is stopped. */
	private int _attackTimeout;
	/** The L2Attackable aggro counter. */
	private int _globalAggro;
	/** The flag used to indicate that a thinking action is in progress, to prevent recursive thinking. */
	private boolean _thinking;
	private int _chaosTime = 0;
	private int _lastBuffTick;
	// Fear parameters
	private int _fearTime;
	private Future<?> _fearTask = null;
	
	/**
	 * Constructor of L2AttackableAI.
	 * @param creature the creature
	 */
	public L2AttackableAI(L2Attackable creature)
	{
		super(creature);
		_attackTimeout = Integer.MAX_VALUE;
		_globalAggro = -10; // 10 seconds timeout of ATTACK after respawn
	}
	
	/**
	 * <B><U> Actor is a L2GuardInstance</U> :</B>
	 * <ul>
	 * <li>The target isn't a Folk or a Door</li>
	 * <li>The target isn't dead, isn't invulnerable, isn't in silent moving mode AND too far (>100)</li>
	 * <li>The target is in the actor Aggro range and is at the same height</li>
	 * <li>The L2PcInstance target has karma (=PK)</li>
	 * <li>The L2MonsterInstance target is aggressive</li>
	 * </ul>
	 * <B><U> Actor is a L2SiegeGuardInstance</U> :</B>
	 * <ul>
	 * <li>The target isn't a Folk or a Door</li>
	 * <li>The target isn't dead, isn't invulnerable, isn't in silent moving mode AND too far (>100)</li>
	 * <li>The target is in the actor Aggro range and is at the same height</li>
	 * <li>A siege is in progress</li>
	 * <li>The L2PcInstance target isn't a Defender</li>
	 * </ul>
	 * <B><U> Actor is a L2FriendlyMobInstance</U> :</B>
	 * <ul>
	 * <li>The target isn't a Folk, a Door or another L2Npc</li>
	 * <li>The target isn't dead, isn't invulnerable, isn't in silent moving mode AND too far (>100)</li>
	 * <li>The target is in the actor Aggro range and is at the same height</li>
	 * <li>The L2PcInstance target has karma (=PK)</li>
	 * </ul>
	 * <B><U> Actor is a L2MonsterInstance</U> :</B>
	 * <ul>
	 * <li>The target isn't a Folk, a Door or another L2Npc</li>
	 * <li>The target isn't dead, isn't invulnerable, isn't in silent moving mode AND too far (>100)</li>
	 * <li>The target is in the actor Aggro range and is at the same height</li>
	 * <li>The actor is Aggressive</li>
	 * </ul>
	 * @param target The targeted L2Object
	 * @return True if the target is autoattackable (depends on the actor type).
	 */
	private boolean autoAttackCondition(L2Character target)
	{
		if ((target == null) || (getActiveChar() == null))
		{
			return false;
		}
		final L2Attackable me = getActiveChar();
		
		// Check if the target isn't invulnerable
		if (target.isInvul())
		{
			// However EffectInvincible requires to check GMs specially
			if (target.isPlayer() && target.isGM())
			{
				return false;
			}
			if (target.isSummon() && ((L2Summon) target).getOwner().isGM())
			{
				return false;
			}
		}
		
		// Check if the target isn't a Folk or a Door
		if (target.isDoor())
		{
			return false;
		}
		
		// Check if the target isn't dead, is in the Aggro range and is at the same height
		if (target.isAlikeDead() || (target.isPlayable() && !me.isInsideRadius3D(target, me.getAggroRange())))
		{
			return false;
		}
		
		// Check if the target is a L2Playable
		if (target.isPlayable())
		{
			// Check if the AI isn't a Raid Boss, can See Silent Moving players and the target isn't in silent move mode
			if (!(me.isRaid()) && !(me.canSeeThroughSilentMove()) && ((L2Playable) target).isSilentMovingAffected())
			{
				return false;
			}
		}
		
		// Gets the player if there is any.
		final L2PcInstance player = target.getActingPlayer();
		if (player != null)
		{
			// Don't take the aggro if the GM has the access level below or equal to GM_DONT_TAKE_AGGRO
			if (player.isGM() && !player.getAccessLevel().canTakeAggro())
			{
				return false;
			}
			
			// check if the target is within the grace period for JUST getting up from fake death
			if (player.isRecentFakeDeath())
			{
				return false;
			}
			
			if (Config.FACTION_SYSTEM_ENABLED && Config.FACTION_GUARDS_ENABLED && ((player.isGood() && ((L2Npc) _actor).getTemplate().isClan(Config.FACTION_EVIL_TEAM_NAME)) || (player.isEvil() && ((L2Npc) _actor).getTemplate().isClan(Config.FACTION_GOOD_TEAM_NAME))))
			{
				return true;
			}
			
			if (player.isInParty() && player.getParty().isInDimensionalRift())
			{
				final byte riftType = player.getParty().getDimensionalRift().getType();
				final byte riftRoom = player.getParty().getDimensionalRift().getCurrentRoom();
				
				if ((me instanceof L2RiftInvaderInstance) && !DimensionalRiftManager.getInstance().getRoom(riftType, riftRoom).checkIfInZone(me.getX(), me.getY(), me.getZ()))
				{
					return false;
				}
			}
		}
		
		// Check if the actor is a L2GuardInstance
		if (me instanceof L2GuardInstance)
		{
			// Check if the L2PcInstance target has karma (=PK)
			if ((player != null) && (player.getKarma() > 0))
			{
				return GeoEngine.getInstance().canSeeTarget(me, player); // Los Check
			}
			// Check if the L2MonsterInstance target is aggressive
			if (target.isMonster() && Config.GUARD_ATTACK_AGGRO_MOB)
			{
				return (((L2MonsterInstance) target).isAggressive() && GeoEngine.getInstance().canSeeTarget(me, target));
			}
			
			return false;
		}
		else if (me instanceof L2FriendlyMobInstance)
		{
			// Check if the target isn't another L2Npc
			if (target instanceof L2Npc)
			{
				return false;
			}
			
			// Check if the L2PcInstance target has karma (=PK)
			if (target.isPlayer() && (((L2PcInstance) target).getKarma() > 0))
			{
				return GeoEngine.getInstance().canSeeTarget(me, target); // Los Check
			}
			return false;
		}
		else
		{
			if (target.isAttackable())
			{
				if (!target.isAutoAttackable(me))
				{
					return false;
				}
				
				if (me.isChaos() && me.isInsideRadius2D(target, me.getAggroRange()))
				{
					if (((L2Attackable) target).isInMyClan(me))
					{
						return false;
					}
					// Los Check
					return GeoEngine.getInstance().canSeeTarget(me, target);
				}
			}
			
			if (target.isAttackable() || (target instanceof L2Npc))
			{
				return false;
			}
			
			// depending on config, do not allow mobs to attack _new_ players in peacezones,
			// unless they are already following those players from outside the peacezone.
			if (!Config.ALT_MOB_AGRO_IN_PEACEZONE && target.isInsideZone(ZoneId.PEACE))
			{
				return false;
			}
			
			if (me.isChampion() && Config.CHAMPION_PASSIVE)
			{
				return false;
			}
			
			// Check if the actor is Aggressive
			return (me.isAggressive() && GeoEngine.getInstance().canSeeTarget(me, target));
		}
	}
	
	public void startAITask()
	{
		// If not idle - create an AI task (schedule onEvtThink repeatedly)
		if (_aiTask == null)
		{
			_aiTask = ThreadPool.scheduleAtFixedRate(this::onEvtThink, 1000, 1000);
		}
	}
	
	@Override
	public void stopAITask()
	{
		if (_aiTask != null)
		{
			_aiTask.cancel(false);
			_aiTask = null;
		}
		super.stopAITask();
	}
	
	/**
	 * Set the Intention of this L2CharacterAI and create an AI Task executed every 1s (call onEvtThink method) for this L2Attackable.<br>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : If actor _knowPlayer isn't EMPTY, AI_INTENTION_IDLE will be change in AI_INTENTION_ACTIVE</B></FONT>
	 * @param intention The new Intention to set to the AI
	 * @param arg0 The first parameter of the Intention
	 * @param arg1 The second parameter of the Intention
	 */
	@Override
	synchronized void changeIntention(CtrlIntention intention, Object arg0, Object arg1)
	{
		if ((intention == AI_INTENTION_IDLE) || (intention == AI_INTENTION_ACTIVE))
		{
			// Check if actor is not dead
			final L2Attackable npc = getActiveChar();
			if (!npc.isAlikeDead())
			{
				// If its _knownPlayer isn't empty set the Intention to AI_INTENTION_ACTIVE
				if (!L2World.getInstance().getVisibleObjects(npc, L2PcInstance.class).isEmpty())
				{
					intention = AI_INTENTION_ACTIVE;
				}
				else if ((npc.getSpawn() != null) && !npc.isInsideRadius3D(npc.getSpawn().getLocation(), Config.MAX_DRIFT_RANGE + Config.MAX_DRIFT_RANGE))
				{
					intention = AI_INTENTION_ACTIVE;
				}
			}
			
			if (intention == AI_INTENTION_IDLE)
			{
				// Set the Intention of this L2AttackableAI to AI_INTENTION_IDLE
				super.changeIntention(AI_INTENTION_IDLE, null, null);
				
				// Stop AI task and detach AI from NPC
				stopAITask();
				
				// Cancel the AI
				_actor.detachAI();
				
				return;
			}
		}
		
		// Set the Intention of this L2AttackableAI to intention
		super.changeIntention(intention, arg0, arg1);
		
		// If not idle - create an AI task (schedule onEvtThink repeatedly)
		startAITask();
	}
	
	/**
	 * Manage the Attack Intention : Stop current Attack (if necessary), Calculate attack timeout, Start a new Attack and Launch Think Event.
	 * @param target The L2Character to attack
	 */
	@Override
	protected void onIntentionAttack(L2Character target)
	{
		// Calculate the attack timeout
		_attackTimeout = MAX_ATTACK_TIMEOUT + GameTimeController.getInstance().getGameTicks();
		
		// self and buffs
		if ((_lastBuffTick + 30) < GameTimeController.getInstance().getGameTicks())
		{
			for (Skill buff : getActiveChar().getTemplate().getAISkills(AISkillScope.BUFF))
			{
				if (checkSkillCastConditions(getActiveChar(), buff) && !_actor.isAffectedBySkill(buff.getId()))
				{
					_actor.setTarget(_actor);
					_actor.doCast(buff);
					_actor.setTarget(target);
					break;
				}
			}
			_lastBuffTick = GameTimeController.getInstance().getGameTicks();
		}
		
		// Manage the Attack Intention : Stop current Attack (if necessary), Start a new Attack and Launch Think Event
		super.onIntentionAttack(target);
	}
	
	@Override
	protected void onEvtAfraid(L2Character effector, boolean start)
	{
		if ((_fearTime > 0) && (_fearTask == null))
		{
			_fearTask = ThreadPool.scheduleAtFixedRate(new FearTask(this, effector, start), 0, FEAR_TICKS * 1000); // seconds
			_actor.startAbnormalVisualEffect(true, AbnormalVisualEffect.TURN_FLEE);
		}
		else
		{
			super.onEvtAfraid(effector, start);
			
			if ((_actor.isDead() || (_fearTime <= 0)) && (_fearTask != null))
			{
				_fearTask.cancel(true);
				_fearTask = null;
				_actor.stopAbnormalVisualEffect(true, AbnormalVisualEffect.TURN_FLEE);
				setIntention(AI_INTENTION_IDLE);
			}
		}
	}
	
	protected void thinkCast()
	{
		if (checkTargetLost(getCastTarget()))
		{
			setCastTarget(null);
			return;
		}
		if (maybeMoveToPawn(getCastTarget(), _actor.getMagicalAttackRange(_skill)))
		{
			return;
		}
		clientStopMoving(null);
		setIntention(AI_INTENTION_ACTIVE);
		_actor.doCast(_skill);
	}
	
	/**
	 * Manage AI standard thinks of a L2Attackable (called by onEvtThink). <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Update every 1s the _globalAggro counter to come close to 0</li>
	 * <li>If the actor is Aggressive and can attack, add all autoAttackable L2Character in its Aggro Range to its _aggroList, chose a target and order to attack it</li>
	 * <li>If the actor is a L2GuardInstance that can't attack, order to it to return to its home location</li>
	 * <li>If the actor is a L2MonsterInstance that can't attack, order to it to random walk (1/100)</li>
	 * </ul>
	 */
	protected void thinkActive()
	{
		final L2Attackable npc = getActiveChar();
		
		// Update every 1s the _globalAggro counter to come close to 0
		if (_globalAggro != 0)
		{
			if (_globalAggro < 0)
			{
				_globalAggro++;
			}
			else
			{
				_globalAggro--;
			}
		}
		
		// Add all autoAttackable L2Character in L2Attackable Aggro Range to its _aggroList with 0 damage and 1 hate
		// A L2Attackable isn't aggressive during 10s after its spawn because _globalAggro is set to -10
		if (_globalAggro >= 0)
		{
			L2World.getInstance().forEachVisibleObject(npc, L2Character.class, target ->
			{
				if ((target instanceof L2StaticObjectInstance))
				{
					return;
				}
				
				if (npc.isFakePlayer() && npc.isAggressive())
				{
					final List<L2ItemInstance> droppedItems = npc.getFakePlayerDrops();
					if (droppedItems.isEmpty())
					{
						L2Character nearestTarget = null;
						double closestDistance = Double.MAX_VALUE;
						for (L2Character t : L2World.getInstance().getVisibleObjectsInRange(npc, L2Character.class, npc.getAggroRange()))
						{
							if ((t == _actor) || (t == null) || t.isDead())
							{
								continue;
							}
							if ((Config.FAKE_PLAYER_AGGRO_FPC && t.isFakePlayer()) //
								|| (Config.FAKE_PLAYER_AGGRO_MONSTERS && t.isMonster() && !t.isFakePlayer()) //
								|| (Config.FAKE_PLAYER_AGGRO_PLAYERS && t.isPlayer()))
							{
								final int hating = npc.getHating(t);
								final double distance = npc.calculateDistance2D(t);
								if ((hating == 0) && (closestDistance > distance))
								{
									nearestTarget = t;
									closestDistance = distance;
								}
							}
						}
						if (nearestTarget != null)
						{
							npc.addDamageHate(nearestTarget, 0, 1);
						}
					}
					else if (!npc.isInCombat()) // must pickup items
					{
						final int itemIndex = npc.getFakePlayerDrops().size() - 1; // last item dropped - can also use 0 for first item dropped
						final L2ItemInstance droppedItem = npc.getFakePlayerDrops().get(itemIndex);
						if ((droppedItem != null) && droppedItem.isSpawned())
						{
							if (npc.calculateDistance2D(droppedItem) > 50)
							{
								moveTo(droppedItem);
							}
							else
							{
								npc.getFakePlayerDrops().remove(itemIndex);
								droppedItem.pickupMe(npc);
								if (Config.SAVE_DROPPED_ITEM)
								{
									ItemsOnGroundManager.getInstance().removeObject(droppedItem);
								}
								if (droppedItem.getItem().hasExImmediateEffect())
								{
									for (SkillHolder skillHolder : droppedItem.getItem().getSkills())
									{
										npc.doSimultaneousCast(skillHolder.getSkill());
									}
									npc.broadcastInfo(); // ? check if this is necessary
								}
							}
						}
						else
						{
							npc.getFakePlayerDrops().remove(itemIndex);
						}
						npc.setRunning();
					}
					return;
				}
				
				/*
				 * Check to see if this is a festival mob spawn. If it is, then check to see if the aggro trigger is a festival participant...if so, move to attack it.
				 */
				if ((npc instanceof L2FestivalMonsterInstance) && target.isPlayer())
				{
					final L2PcInstance targetPlayer = (L2PcInstance) target;
					
					if (!(targetPlayer.isFestivalParticipant()))
					{
						return;
					}
				}
				
				// For each L2Character check if the target is autoattackable
				if (autoAttackCondition(target)) // check aggression
				{
					if (target.isFakePlayer())
					{
						if (!npc.isFakePlayer() || (npc.isFakePlayer() && Config.FAKE_PLAYER_AGGRO_FPC))
						{
							final int hating = npc.getHating(target);
							if (hating == 0)
							{
								npc.addDamageHate(target, 0, 1);
							}
						}
						return;
					}
					if (target.isPlayable())
					{
						final TerminateReturn term = EventDispatcher.getInstance().notifyEvent(new OnAttackableHate(getActiveChar(), target.getActingPlayer(), target.isSummon()), getActiveChar(), TerminateReturn.class);
						if ((term != null) && term.terminate())
						{
							return;
						}
					}
					
					if (npc.getHating(target) == 0)
					{
						npc.addDamageHate(target, 0, 1);
					}
				}
			});
			
			// Chose a target from its aggroList
			final L2Character hated = npc.isConfused() ? getAttackTarget() : npc.getMostHated();
			
			// Order to the L2Attackable to attack the target
			if ((hated != null) && !npc.isCoreAIDisabled())
			{
				// Get the hate level of the L2Attackable against this L2Character target contained in _aggroList
				final int aggro = npc.getHating(hated);
				
				if ((aggro + _globalAggro) > 0)
				{
					// Set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance
					if (!npc.isRunning())
					{
						npc.setRunning();
					}
					
					// Set the AI Intention to AI_INTENTION_ATTACK
					setIntention(AI_INTENTION_ATTACK, hated);
				}
				
				return;
			}
		}
		
		// Chance to forget attackers after some time
		if ((npc.getCurrentHp() == npc.getMaxHp()) && (npc.getCurrentMp() == npc.getMaxMp()) && !npc.getAttackByList().isEmpty() && (Rnd.get(500) == 0))
		{
			npc.clearAggroList();
			npc.getAttackByList().clear();
		}
		
		// Check if the mob should not return to spawn point
		if (!npc.canReturnToSpawnPoint())
		{
			return;
		}
		
		// Check if the actor is a L2GuardInstance
		if ((npc instanceof L2GuardInstance) && !npc.isWalker())
		{
			if (Config.ENABLE_GUARD_RETURN && (npc.getSpawn() != null) && (Util.calculateDistance(npc, npc.getSpawn(), false, false) > 50) && /* !npc.isInsideZone(ZoneId.SIEGE) && */!npc.isCastingNow())
			{
				// Custom guard teleport to spawn.
				npc.clearAggroList();
				npc.doCast(SkillData.getInstance().getSkill(1050, 1));
			}
			else
			{
				// Order to the L2GuardInstance to return to its home location because there's no target to attack
				npc.returnHome();
			}
		}
		
		// If this is a festival monster, then it remains in the same location.
		if (npc instanceof L2FestivalMonsterInstance)
		{
			return;
		}
		
		// Minions following leader
		final L2Character leader = npc.getLeader();
		if ((leader != null) && !leader.isAlikeDead())
		{
			final int offset;
			final int minRadius = 30;
			
			if (npc.isRaidMinion())
			{
				offset = 500; // for Raids - need correction
			}
			else
			{
				offset = 200; // for normal minions - need correction :)
			}
			
			if (leader.isRunning())
			{
				npc.setRunning();
			}
			else
			{
				npc.setWalking();
			}
			
			if (npc.calculateDistanceSq2D(leader) > (offset * offset))
			{
				int x1 = Rnd.get(minRadius * 2, offset * 2); // x
				int y1 = Rnd.get(x1, offset * 2); // distance
				int z1;
				y1 = (int) Math.sqrt((y1 * y1) - (x1 * x1)); // y
				x1 = x1 > (offset + minRadius) ? (leader.getX() + x1) - offset : (leader.getX() - x1) + minRadius;
				y1 = y1 > (offset + minRadius) ? (leader.getY() + y1) - offset : (leader.getY() - y1) + minRadius;
				z1 = leader.getZ();
				// Move the actor to Location (x,y,z) server side AND client side by sending Server->Client packet CharMoveToLocation (broadcast)
				moveTo(x1, y1, z1);
				return;
			}
			if (Rnd.get(RANDOM_WALK_RATE) == 0)
			{
				for (Skill sk : npc.getTemplate().getAISkills(AISkillScope.BUFF))
				{
					if (cast(sk))
					{
						return;
					}
				}
			}
		}
		// Order to the L2MonsterInstance to random walk (1/100)
		else if ((npc.getSpawn() != null) && (Rnd.get(RANDOM_WALK_RATE) == 0) && npc.isRandomWalkingEnabled())
		{
			int x1 = 0;
			int y1 = 0;
			int z1 = 0;
			final int range = Config.MAX_DRIFT_RANGE;
			
			for (Skill sk : npc.getTemplate().getAISkills(AISkillScope.BUFF))
			{
				if (cast(sk))
				{
					return;
				}
			}
			
			x1 = npc.getSpawn().getX();
			y1 = npc.getSpawn().getY();
			z1 = npc.getSpawn().getZ();
			
			if (!npc.isInsideRadius2D(x1, y1, 0, range))
			{
				npc.setisReturningToSpawnPoint(true);
			}
			else
			{
				final int deltaX = Rnd.get(range * 2); // x
				int deltaY = Rnd.get(deltaX, range * 2); // distance
				deltaY = (int) Math.sqrt((deltaY * deltaY) - (deltaX * deltaX)); // y
				x1 = (deltaX + x1) - range;
				y1 = (deltaY + y1) - range;
				z1 = npc.getZ();
			}
			
			// Move the actor to Location (x,y,z) server side AND client side by sending Server->Client packet CharMoveToLocation (broadcast)
			final Location moveLoc = _actor.isFlying() ? new Location(x1, y1, z1) : GeoEngine.getInstance().canMoveToTargetLoc(npc.getX(), npc.getY(), npc.getZ(), x1, y1, z1, npc.getInstanceId());
			
			moveTo(moveLoc.getX(), moveLoc.getY(), moveLoc.getZ());
		}
	}
	
	/**
	 * Manage AI attack thinks of a L2Attackable (called by onEvtThink).<br>
	 * <b><u>Actions</u>:</b>
	 * <ul>
	 * <li>Update the attack timeout if actor is running</li>
	 * <li>If target is dead or timeout is expired, stop this attack and set the Intention to AI_INTENTION_ACTIVE</li>
	 * <li>Call all L2Object of its Faction inside the Faction Range</li>
	 * <li>Chose a target and order to attack it with magic skill or physical attack</li>
	 * </ul>
	 */
	protected void thinkAttack()
	{
		final L2Attackable npc = getActiveChar();
		if (npc.isCastingNow())
		{
			return;
		}
		
		if (npc.isCoreAIDisabled())
		{
			return;
		}
		
		final L2Character mostHate = npc.getMostHated();
		if (mostHate == null)
		{
			setIntention(AI_INTENTION_ACTIVE);
			return;
		}
		
		setAttackTarget(mostHate);
		npc.setTarget(mostHate);
		
		// Immobilize condition
		if (npc.isMovementDisabled())
		{
			movementDisable();
			return;
		}
		
		// Check if target is dead or if timeout is expired to stop this attack
		final L2Character originalAttackTarget = getAttackTarget();
		if ((originalAttackTarget == null) || originalAttackTarget.isAlikeDead())
		{
			// Stop hating this target after the attack timeout or if target is dead
			npc.stopHating(originalAttackTarget);
			return;
		}
		
		if (_attackTimeout < GameTimeController.getInstance().getGameTicks())
		{
			// Set the AI Intention to AI_INTENTION_ACTIVE
			setIntention(AI_INTENTION_ACTIVE);
			
			if (!_actor.isFakePlayer())
			{
				npc.setWalking();
			}
			
			// Monster teleport to spawn
			if (npc.isMonster() && (npc.getSpawn() != null) && (npc.getInstanceId() == 0))
			{
				npc.teleToLocation(npc.getSpawn(), false);
			}
			return;
		}
		
		final int collision = npc.getTemplate().getCollisionRadius();
		
		// Handle all L2Object of its Faction inside the Faction Range
		
		Set<Integer> clans = getActiveChar().getTemplate().getClans();
		if ((clans != null) && !clans.isEmpty())
		{
			final int factionRange = npc.getTemplate().getClanHelpRange() + collision;
			// Go through all L2Object that belong to its faction
			try
			{
				for (L2Npc called : L2World.getInstance().getVisibleObjectsInRange(npc, L2Npc.class, factionRange))
				{
					if (!getActiveChar().getTemplate().isClan(called.getTemplate().getClans()))
					{
						continue;
					}
					
					// Check if the L2Object is inside the Faction Range of the actor
					if (called.hasAI())
					{
						if ((Math.abs(originalAttackTarget.getZ() - called.getZ()) < 600) && npc.getAttackByList().contains(originalAttackTarget) && ((called.getAI()._intention == AI_INTENTION_IDLE) || (called.getAI()._intention == AI_INTENTION_ACTIVE)) && (called.getInstanceId() == npc.getInstanceId()))
						{
							if (originalAttackTarget.isPlayable())
							{
								if (originalAttackTarget.isInParty() && originalAttackTarget.getParty().isInDimensionalRift())
								{
									byte riftType = originalAttackTarget.getParty().getDimensionalRift().getType();
									byte riftRoom = originalAttackTarget.getParty().getDimensionalRift().getCurrentRoom();
									
									if ((npc instanceof L2RiftInvaderInstance) && !DimensionalRiftManager.getInstance().getRoom(riftType, riftRoom).checkIfInZone(npc.getX(), npc.getY(), npc.getZ()))
									{
										continue;
									}
								}
								
								// By default, when a faction member calls for help, attack the caller's attacker.
								// Notify the AI with EVT_AGGRESSION
								called.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, originalAttackTarget, 1);
								EventDispatcher.getInstance().notifyEventAsync(new OnAttackableFactionCall(called, getActiveChar(), originalAttackTarget.getActingPlayer(), originalAttackTarget.isSummon()), called);
							}
							else if (called.isAttackable() && (getAttackTarget() != null) && (called.getAI()._intention != AI_INTENTION_ATTACK))
							{
								((L2Attackable) called).addDamageHate(getAttackTarget(), 0, npc.getHating(getAttackTarget()));
								called.getAI().setIntention(AI_INTENTION_ATTACK, getAttackTarget());
							}
						}
					}
				}
			}
			catch (NullPointerException e)
			{
				// LOGGER.warning(getClass().getSimpleName() + ": There has been a problem trying to think the attack!", e);
			}
		}
		
		// Initialize data
		final int combinedCollision = collision + mostHate.getTemplate().getCollisionRadius();
		
		final List<Skill> aiSuicideSkills = npc.getTemplate().getAISkills(AISkillScope.SUICIDE);
		if (!aiSuicideSkills.isEmpty() && ((int) ((npc.getCurrentHp() / npc.getMaxHp()) * 100) < 30))
		{
			final Skill skill = aiSuicideSkills.get(Rnd.get(aiSuicideSkills.size()));
			if (Util.checkIfInRange(skill.getAffectRange(), getActiveChar(), mostHate, false) && npc.hasSkillChance() && cast(skill))
			{
				return;
			}
		}
		
		// ------------------------------------------------------
		// In case many mobs are trying to hit from same place, move a bit, circling around the target
		// Note from Gnacik:
		// On l2js because of that sometimes mobs don't attack player only running
		// around player without any sense, so decrease chance for now
		if (!npc.isMovementDisabled() && (Rnd.get(100) <= 3))
		{
			for (L2Attackable nearby : L2World.getInstance().getVisibleObjects(npc, L2Attackable.class))
			{
				if (npc.isInsideRadius2D(nearby, collision) && (nearby != mostHate))
				{
					int newX = combinedCollision + Rnd.get(40);
					newX = Rnd.nextBoolean() ? mostHate.getX() + newX : mostHate.getX() - newX;
					int newY = combinedCollision + Rnd.get(40);
					newY = Rnd.nextBoolean() ? mostHate.getY() + newY : mostHate.getY() - newY;
					
					if (!npc.isInsideRadius2D(newX, newY, 0, collision))
					{
						final int newZ = npc.getZ() + 30;
						
						// Mobius: Verify destination. Prevents wall collision issues and fixes monsters not avoiding obstacles.
						moveTo(GeoEngine.getInstance().canMoveToTargetLoc(npc.getX(), npc.getY(), npc.getZ(), newX, newY, newZ, npc.getInstanceId()));
					}
					return;
				}
			}
		}
		// Dodge if its needed
		if (!npc.isMovementDisabled() && (npc.getDodge() > 0))
		{
			if (Rnd.get(100) <= npc.getDodge())
			{
				// Micht: Keeping this one otherwise we should do 2 sqrt
				double distance2 = npc.calculateDistanceSq2D(mostHate);
				if (Math.sqrt(distance2) <= (60 + combinedCollision))
				{
					int posX = npc.getX();
					int posY = npc.getY();
					int posZ = npc.getZ() + 30;
					
					if (originalAttackTarget.getX() < posX)
					{
						posX += 300;
					}
					else
					{
						posX -= 300;
					}
					
					if (originalAttackTarget.getY() < posY)
					{
						posY += 300;
					}
					else
					{
						posY -= 300;
					}
					
					if (GeoEngine.getInstance().canMoveToTarget(npc.getX(), npc.getY(), npc.getZ(), posX, posY, posZ, npc.getInstanceId()))
					{
						setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(posX, posY, posZ, 0));
					}
					return;
				}
			}
		}
		
		// BOSS/Raid Minion Target Reconsider
		if (npc.isRaid() || npc.isRaidMinion())
		{
			_chaosTime++;
			if (npc instanceof L2RaidBossInstance)
			{
				if (!((L2MonsterInstance) npc).hasMinions())
				{
					if (_chaosTime > Config.RAID_CHAOS_TIME)
					{
						if (Rnd.get(100) <= (100 - ((npc.getCurrentHp() * 100) / npc.getMaxHp())))
						{
							aggroReconsider();
							_chaosTime = 0;
							return;
						}
					}
				}
				else
				{
					if (_chaosTime > Config.RAID_CHAOS_TIME)
					{
						if (Rnd.get(100) <= (100 - ((npc.getCurrentHp() * 200) / npc.getMaxHp())))
						{
							aggroReconsider();
							_chaosTime = 0;
							return;
						}
					}
				}
			}
			else if (npc instanceof L2GrandBossInstance)
			{
				if (_chaosTime > Config.GRAND_CHAOS_TIME)
				{
					double chaosRate = 100 - ((npc.getCurrentHp() * 300) / npc.getMaxHp());
					if (((chaosRate <= 10) && (Rnd.get(100) <= 10)) || ((chaosRate > 10) && (Rnd.get(100) <= chaosRate)))
					{
						aggroReconsider();
						_chaosTime = 0;
						return;
					}
				}
			}
			else
			{
				if (_chaosTime > Config.MINION_CHAOS_TIME)
				{
					if (Rnd.get(100) <= (100 - ((npc.getCurrentHp() * 200) / npc.getMaxHp())))
					{
						aggroReconsider();
						_chaosTime = 0;
						return;
					}
				}
			}
		}
		
		final List<Skill> generalSkills = npc.getTemplate().getAISkills(AISkillScope.GENERAL);
		if (!generalSkills.isEmpty())
		{
			// Heal Condition
			final List<Skill> aiHealSkills = npc.getTemplate().getAISkills(AISkillScope.HEAL);
			if (!aiHealSkills.isEmpty())
			{
				double percentage = (npc.getCurrentHp() / npc.getMaxHp()) * 100;
				if (npc.isMinion())
				{
					final L2Character leader = npc.getLeader();
					if ((leader != null) && !leader.isDead() && (Rnd.get(100) > ((leader.getCurrentHp() / leader.getMaxHp()) * 100)))
					{
						for (Skill healSkill : aiHealSkills)
						{
							if (healSkill.getTargetType() == L2TargetType.SELF)
							{
								continue;
							}
							
							if (!checkSkillCastConditions(npc, healSkill))
							{
								continue;
							}
							
							if (!Util.checkIfInRange((healSkill.getCastRange() + collision + leader.getTemplate().getCollisionRadius()), npc, leader, false) && !isParty(healSkill) && !npc.isMovementDisabled())
							{
								moveToPawn(leader, healSkill.getCastRange() + collision + leader.getTemplate().getCollisionRadius());
								return;
							}
							
							if (GeoEngine.getInstance().canSeeTarget(npc, leader))
							{
								clientStopMoving(null);
								final L2Object target = npc.getTarget();
								npc.setTarget(leader);
								npc.doCast(healSkill);
								npc.setTarget(target);
								// LOGGER.debug(this + " used heal skill " + healSkill + " on leader " + leader);
								return;
							}
						}
					}
				}
				
				if (Rnd.get(100) < ((100 - percentage) / 3))
				{
					for (Skill sk : aiHealSkills)
					{
						if (!checkSkillCastConditions(npc, sk))
						{
							continue;
						}
						
						clientStopMoving(null);
						final L2Object target = npc.getTarget();
						npc.setTarget(npc);
						npc.doCast(sk);
						npc.setTarget(target);
						// LOGGER.debug(this + " used heal skill " + sk + " on itself");
						return;
					}
				}
				
				for (Skill sk : aiHealSkills)
				{
					if (!checkSkillCastConditions(npc, sk))
					{
						continue;
					}
					
					if (sk.getTargetType() == L2TargetType.ONE)
					{
						for (L2Attackable obj : L2World.getInstance().getVisibleObjectsInRange(npc, L2Attackable.class, sk.getCastRange() + collision))
						{
							if (!obj.isDead())
							{
								continue;
							}
							
							if (!obj.isInMyClan(npc))
							{
								continue;
							}
							
							percentage = (obj.getCurrentHp() / obj.getMaxHp()) * 100;
							if (Rnd.get(100) < ((100 - percentage) / 10))
							{
								if (GeoEngine.getInstance().canSeeTarget(npc, obj))
								{
									clientStopMoving(null);
									final L2Object target = npc.getTarget();
									npc.setTarget(obj);
									npc.doCast(sk);
									npc.setTarget(target);
									// LOGGER.debug(this + " used heal skill " + sk + " on " + obj);
									return;
								}
							}
						}
					}
					
					if (isParty(sk))
					{
						clientStopMoving(null);
						npc.doCast(sk);
						return;
					}
				}
			}
			
			// Res Skill Condition
			final List<Skill> aiResSkills = npc.getTemplate().getAISkills(AISkillScope.RES);
			if (!aiResSkills.isEmpty())
			{
				if (npc.isMinion())
				{
					L2Character leader = npc.getLeader();
					if ((leader != null) && leader.isDead())
					{
						for (Skill sk : aiResSkills)
						{
							if (sk.getTargetType() == L2TargetType.SELF)
							{
								continue;
							}
							
							if (!checkSkillCastConditions(npc, sk))
							{
								continue;
							}
							
							if (!Util.checkIfInRange((sk.getCastRange() + collision + leader.getTemplate().getCollisionRadius()), npc, leader, false) && !isParty(sk) && !npc.isMovementDisabled())
							{
								moveToPawn(leader, sk.getCastRange() + collision + leader.getTemplate().getCollisionRadius());
								return;
							}
							
							if (GeoEngine.getInstance().canSeeTarget(npc, leader))
							{
								clientStopMoving(null);
								final L2Object target = npc.getTarget();
								npc.setTarget(leader);
								npc.doCast(sk);
								npc.setTarget(target);
								// LOGGER.debug(this + " used resurrection skill " + sk + " on leader " + leader);
								return;
							}
						}
					}
				}
				
				for (Skill sk : aiResSkills)
				{
					if (!checkSkillCastConditions(npc, sk))
					{
						continue;
					}
					if (sk.getTargetType() == L2TargetType.ONE)
					{
						for (L2Attackable obj : L2World.getInstance().getVisibleObjectsInRange(npc, L2Attackable.class, sk.getCastRange() + collision))
						{
							if (!obj.isDead())
							{
								continue;
							}
							
							if (!npc.isInMyClan(obj))
							{
								continue;
							}
							if (Rnd.get(100) < 10)
							{
								if (GeoEngine.getInstance().canSeeTarget(npc, obj))
								{
									clientStopMoving(null);
									final L2Object target = npc.getTarget();
									npc.setTarget(obj);
									npc.doCast(sk);
									npc.setTarget(target);
									// LOGGER.debug(this + " used heal skill " + sk + " on clan member " + obj);
									return;
								}
							}
						}
					}
					
					if (isParty(sk))
					{
						clientStopMoving(null);
						final L2Object target = npc.getTarget();
						npc.setTarget(npc);
						npc.doCast(sk);
						npc.setTarget(target);
						// LOGGER.debug(this + " used heal skill " + sk + " on party");
						return;
					}
				}
			}
		}
		
		final double dist = npc.calculateDistance2D(mostHate);
		final int dist2 = (int) dist - collision;
		int range = npc.getPhysicalAttackRange() + combinedCollision;
		if (mostHate.isMoving())
		{
			range += 50;
			if (npc.isMoving())
			{
				range += 50;
			}
		}
		
		// Long/Short Range skill usage.
		if (!npc.getShortRangeSkills().isEmpty() && npc.hasSkillChance())
		{
			final Skill shortRangeSkill = npc.getShortRangeSkills().get(Rnd.get(npc.getShortRangeSkills().size()));
			if (checkSkillCastConditions(npc, shortRangeSkill))
			{
				clientStopMoving(null);
				npc.doCast(shortRangeSkill);
				// LOGGER.debug(this + " used short range skill " + shortRangeSkill + " on " + npc.getTarget());
				return;
			}
		}
		
		if (!npc.getLongRangeSkills().isEmpty() && npc.hasSkillChance())
		{
			final Skill longRangeSkill = npc.getLongRangeSkills().get(Rnd.get(npc.getLongRangeSkills().size()));
			if (checkSkillCastConditions(npc, longRangeSkill))
			{
				clientStopMoving(null);
				npc.doCast(longRangeSkill);
				// LOGGER.debug(this + " used long range skill " + longRangeSkill + " on " + npc.getTarget());
				return;
			}
		}
		
		// Starts melee attack
		if ((dist2 > range) || !GeoEngine.getInstance().canSeeTarget(npc, mostHate))
		{
			if (npc.isMovementDisabled())
			{
				targetReconsider();
			}
			else
			{
				final L2Character target = getAttackTarget();
				if (target != null)
				{
					if (target.isMoving())
					{
						range -= 100;
					}
					moveToPawn(target, Math.max(range, 5));
				}
			}
			return;
		}
		
		// Attacks target
		_actor.doAttack(getAttackTarget());
	}
	
	private boolean cast(Skill sk)
	{
		if (sk == null)
		{
			return false;
		}
		
		final L2Attackable caster = getActiveChar();
		if (!checkSkillCastConditions(caster, sk))
		{
			return false;
		}
		
		if (getAttackTarget() == null)
		{
			if (caster.getMostHated() != null)
			{
				setAttackTarget(caster.getMostHated());
			}
		}
		
		final L2Character attackTarget = getAttackTarget();
		if (attackTarget == null)
		{
			return false;
		}
		
		final double dist = caster.calculateDistance2D(attackTarget);
		double dist2 = dist - attackTarget.getTemplate().getCollisionRadius();
		final double range = caster.getPhysicalAttackRange() + caster.getTemplate().getCollisionRadius() + attackTarget.getTemplate().getCollisionRadius();
		final double srange = sk.getCastRange() + caster.getTemplate().getCollisionRadius();
		if (attackTarget.isMoving())
		{
			dist2 -= 30;
		}
		
		if (sk.isContinuous())
		{
			if (!sk.isDebuff())
			{
				if (!caster.isAffectedBySkill(sk.getId()))
				{
					clientStopMoving(null);
					caster.setTarget(caster);
					caster.doCast(sk);
					_actor.setTarget(attackTarget);
					return true;
				}
				// If actor already have buff, start looking at others same faction mob to cast
				if (sk.getTargetType() == L2TargetType.SELF)
				{
					return false;
				}
				if (sk.getTargetType() == L2TargetType.ONE)
				{
					final L2Character target = effectTargetReconsider(sk, true);
					if (target != null)
					{
						clientStopMoving(null);
						caster.setTarget(target);
						caster.doCast(sk);
						caster.setTarget(attackTarget);
						return true;
					}
				}
				if (canParty(sk))
				{
					clientStopMoving(null);
					caster.setTarget(caster);
					caster.doCast(sk);
					caster.setTarget(attackTarget);
					return true;
				}
			}
			else
			{
				if (GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !canAOE(sk) && !attackTarget.isDead() && (dist2 <= srange))
				{
					if (!attackTarget.isAffectedBySkill(sk.getId()))
					{
						clientStopMoving(null);
						caster.doCast(sk);
						return true;
					}
				}
				else if (canAOE(sk))
				{
					if ((sk.getTargetType() == L2TargetType.AURA) || (sk.getTargetType() == L2TargetType.BEHIND_AURA) || (sk.getTargetType() == L2TargetType.FRONT_AURA) || (sk.getTargetType() == L2TargetType.AURA_CORPSE_MOB))
					{
						clientStopMoving(null);
						caster.doCast(sk);
						return true;
					}
					if (((sk.getTargetType() == L2TargetType.AREA) || (sk.getTargetType() == L2TargetType.BEHIND_AREA) || (sk.getTargetType() == L2TargetType.FRONT_AREA)) && GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !attackTarget.isDead() && (dist2 <= srange))
					{
						clientStopMoving(null);
						caster.doCast(sk);
						return true;
					}
				}
				else if (sk.getTargetType() == L2TargetType.ONE)
				{
					L2Character target = effectTargetReconsider(sk, false);
					if (target != null)
					{
						clientStopMoving(null);
						caster.doCast(sk);
						return true;
					}
				}
			}
		}
		
		if (sk.hasEffectType(L2EffectType.DISPEL, L2EffectType.DISPEL_BY_SLOT))
		{
			if (sk.getTargetType() == L2TargetType.ONE)
			{
				if ((attackTarget.getEffectList().getFirstEffect(L2EffectType.BUFF) != null) && GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !attackTarget.isDead() && (dist2 <= srange))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
				final L2Character target = effectTargetReconsider(sk, false);
				if (target != null)
				{
					clientStopMoving(null);
					caster.setTarget(target);
					caster.doCast(sk);
					caster.setTarget(attackTarget);
					return true;
				}
			}
			else if (canAOE(sk))
			{
				if (((sk.getTargetType() == L2TargetType.AURA) || (sk.getTargetType() == L2TargetType.BEHIND_AURA) || (sk.getTargetType() == L2TargetType.FRONT_AURA)) && GeoEngine.getInstance().canSeeTarget(caster, attackTarget))
				
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
				else if (((sk.getTargetType() == L2TargetType.AREA) || (sk.getTargetType() == L2TargetType.BEHIND_AREA) || (sk.getTargetType() == L2TargetType.FRONT_AREA)) && GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !attackTarget.isDead() && (dist2 <= srange))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
		}
		
		if (sk.hasEffectType(L2EffectType.HEAL))
		{
			double percentage = (caster.getCurrentHp() / caster.getMaxHp()) * 100;
			if (caster.isMinion() && (sk.getTargetType() != L2TargetType.SELF))
			{
				final L2Character leader = caster.getLeader();
				if ((leader != null) && !leader.isDead() && (Rnd.get(100) > ((leader.getCurrentHp() / leader.getMaxHp()) * 100)))
				{
					if (!Util.checkIfInRange((sk.getCastRange() + caster.getTemplate().getCollisionRadius() + leader.getTemplate().getCollisionRadius()), caster, leader, false) && !isParty(sk) && !caster.isMovementDisabled())
					{
						moveToPawn(leader, sk.getCastRange() + caster.getTemplate().getCollisionRadius() + leader.getTemplate().getCollisionRadius());
					}
					if (GeoEngine.getInstance().canSeeTarget(caster, leader))
					{
						clientStopMoving(null);
						caster.setTarget(leader);
						caster.doCast(sk);
						caster.setTarget(attackTarget);
						return true;
					}
				}
			}
			
			if (Rnd.get(100) < ((100 - percentage) / 3))
			{
				clientStopMoving(null);
				caster.setTarget(caster);
				caster.doCast(sk);
				caster.setTarget(attackTarget);
				return true;
			}
			
			if (sk.getTargetType() == L2TargetType.ONE)
			{
				for (L2Attackable obj : L2World.getInstance().getVisibleObjectsInRange(caster, L2Attackable.class, sk.getCastRange() + caster.getTemplate().getCollisionRadius()))
				{
					if (obj.isDead())
					{
						continue;
					}
					
					if (!caster.isInMyClan(obj))
					{
						continue;
					}
					
					percentage = (obj.getCurrentHp() / obj.getMaxHp()) * 100;
					if (Rnd.get(100) < ((100 - percentage) / 10))
					{
						if (GeoEngine.getInstance().canSeeTarget(caster, obj))
						{
							clientStopMoving(null);
							caster.setTarget(obj);
							caster.doCast(sk);
							caster.setTarget(attackTarget);
							return true;
						}
					}
				}
			}
			if (isParty(sk))
			{
				for (L2Attackable obj : L2World.getInstance().getVisibleObjectsInRange(caster, L2Attackable.class, sk.getAffectRange() + caster.getTemplate().getCollisionRadius()))
				{
					if (obj.isInMyClan(caster))
					{
						if ((obj.getCurrentHp() < obj.getMaxHp()) && (Rnd.get(100) <= 20))
						{
							clientStopMoving(null);
							caster.setTarget(caster);
							caster.doCast(sk);
							caster.setTarget(attackTarget);
							return true;
						}
					}
				}
			}
		}
		
		if (sk.hasEffectType(L2EffectType.PHYSICAL_ATTACK, L2EffectType.PHYSICAL_ATTACK_HP_LINK, L2EffectType.MAGICAL_ATTACK, L2EffectType.DEATH_LINK, L2EffectType.HP_DRAIN))
		{
			if (!canAura(sk))
			{
				if (GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !attackTarget.isDead() && (dist2 <= srange))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
				
				L2Character target = skillTargetReconsider(sk);
				if (target != null)
				{
					clientStopMoving(null);
					caster.setTarget(target);
					caster.doCast(sk);
					caster.setTarget(attackTarget);
					return true;
				}
			}
			else
			{
				clientStopMoving(null);
				caster.doCast(sk);
				return true;
			}
		}
		
		if (sk.hasEffectType(L2EffectType.SLEEP))
		{
			if (sk.getTargetType() == L2TargetType.ONE)
			{
				if (!attackTarget.isDead() && (dist2 <= srange))
				{
					if ((dist2 > range) || attackTarget.isMoving())
					{
						if (!attackTarget.isAffectedBySkill(sk.getId()))
						{
							clientStopMoving(null);
							caster.doCast(sk);
							return true;
						}
					}
				}
				
				L2Character target = effectTargetReconsider(sk, false);
				if (target != null)
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
			else if (canAOE(sk))
			{
				if ((sk.getTargetType() == L2TargetType.AURA) || (sk.getTargetType() == L2TargetType.BEHIND_AURA) || (sk.getTargetType() == L2TargetType.FRONT_AURA))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
				if (((sk.getTargetType() == L2TargetType.AREA) || (sk.getTargetType() == L2TargetType.BEHIND_AREA) || (sk.getTargetType() == L2TargetType.FRONT_AREA)) && GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !attackTarget.isDead() && (dist2 <= srange))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
		}
		
		if (sk.hasEffectType(L2EffectType.STUN, L2EffectType.ROOT, L2EffectType.PARALYZE, L2EffectType.MUTE, L2EffectType.FEAR))
		{
			if (GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !canAOE(sk) && (dist2 <= srange))
			{
				if (!attackTarget.isAffectedBySkill(sk.getId()))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
			else if (canAOE(sk))
			{
				if ((sk.getTargetType() == L2TargetType.AURA) || (sk.getTargetType() == L2TargetType.BEHIND_AURA) || (sk.getTargetType() == L2TargetType.FRONT_AURA))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
				if (((sk.getTargetType() == L2TargetType.AREA) || (sk.getTargetType() == L2TargetType.BEHIND_AREA) || (sk.getTargetType() == L2TargetType.FRONT_AREA)) && GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !attackTarget.isDead() && (dist2 <= srange))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
			else if (sk.getTargetType() == L2TargetType.ONE)
			{
				L2Character target = effectTargetReconsider(sk, false);
				if (target != null)
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
		}
		
		if (sk.hasEffectType(L2EffectType.DMG_OVER_TIME, L2EffectType.DMG_OVER_TIME_PERCENT))
		{
			if (GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !canAOE(sk) && !attackTarget.isDead() && (dist2 <= srange))
			{
				if (!attackTarget.isAffectedBySkill(sk.getId()))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
			else if (canAOE(sk))
			{
				if ((sk.getTargetType() == L2TargetType.AURA) || (sk.getTargetType() == L2TargetType.BEHIND_AURA) || (sk.getTargetType() == L2TargetType.FRONT_AURA) || (sk.getTargetType() == L2TargetType.AURA_CORPSE_MOB))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
				if (((sk.getTargetType() == L2TargetType.AREA) || (sk.getTargetType() == L2TargetType.BEHIND_AREA) || (sk.getTargetType() == L2TargetType.FRONT_AREA)) && GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !attackTarget.isDead() && (dist2 <= srange))
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
			else if (sk.getTargetType() == L2TargetType.ONE)
			{
				L2Character target = effectTargetReconsider(sk, false);
				if (target != null)
				{
					clientStopMoving(null);
					caster.doCast(sk);
					return true;
				}
			}
		}
		
		if (sk.hasEffectType(L2EffectType.RESURRECTION))
		{
			if (!isParty(sk))
			{
				if (caster.isMinion() && (sk.getTargetType() != L2TargetType.SELF))
				{
					L2Character leader = caster.getLeader();
					if (leader != null)
					{
						if (leader.isDead())
						{
							if (!Util.checkIfInRange((sk.getCastRange() + caster.getTemplate().getCollisionRadius() + leader.getTemplate().getCollisionRadius()), caster, leader, false) && !isParty(sk) && !caster.isMovementDisabled())
							{
								moveToPawn(leader, sk.getCastRange() + caster.getTemplate().getCollisionRadius() + leader.getTemplate().getCollisionRadius());
							}
						}
						if (GeoEngine.getInstance().canSeeTarget(caster, leader))
						{
							clientStopMoving(null);
							caster.setTarget(leader);
							caster.doCast(sk);
							caster.setTarget(attackTarget);
							return true;
						}
					}
				}
				
				for (L2Attackable obj : L2World.getInstance().getVisibleObjectsInRange(caster, L2Attackable.class, sk.getCastRange() + caster.getTemplate().getCollisionRadius()))
				{
					if (!obj.isDead())
					{
						continue;
					}
					
					if (!caster.isInMyClan(obj))
					{
						continue;
					}
					
					if (Rnd.get(100) < 10)
					{
						if (GeoEngine.getInstance().canSeeTarget(caster, obj))
						{
							clientStopMoving(null);
							caster.setTarget(obj);
							caster.doCast(sk);
							caster.setTarget(attackTarget);
							return true;
						}
					}
				}
			}
			else if (isParty(sk))
			{
				for (L2Npc obj : L2World.getInstance().getVisibleObjectsInRange(caster, L2Npc.class, sk.getAffectRange() + caster.getTemplate().getCollisionRadius()))
				{
					if (caster.isInMyClan(obj))
					{
						if ((obj.getCurrentHp() < obj.getMaxHp()) && (Rnd.get(100) <= 20))
						{
							clientStopMoving(null);
							caster.setTarget(caster);
							caster.doCast(sk);
							caster.setTarget(attackTarget);
							return true;
						}
					}
				}
			}
		}
		
		if (!canAura(sk))
		{
			if (GeoEngine.getInstance().canSeeTarget(caster, attackTarget) && !attackTarget.isDead() && (dist2 <= srange))
			{
				clientStopMoving(null);
				caster.doCast(sk);
				return true;
			}
			
			L2Character target = skillTargetReconsider(sk);
			if (target != null)
			{
				clientStopMoving(null);
				caster.setTarget(target);
				caster.doCast(sk);
				caster.setTarget(attackTarget);
				return true;
			}
		}
		else
		{
			clientStopMoving(null);
			caster.doCast(sk);
			return true;
		}
		return false;
	}
	
	private void movementDisable()
	{
		final L2Attackable npc = getActiveChar();
		final L2Character target = getAttackTarget();
		if (target == null)
		{
			return;
		}
		
		if (npc.getTarget() == null)
		{
			npc.setTarget(target);
		}
		
		final double dist = npc.calculateDistance2D(target);
		final int range = npc.getPhysicalAttackRange() + npc.getTemplate().getCollisionRadius() + target.getTemplate().getCollisionRadius();
		// TODO(Zoey76): Review this "magic changes".
		final int random = Rnd.get(100);
		if (!target.isImmobilized() && (random < 15))
		{
			if (tryCast(npc, target, AISkillScope.IMMOBILIZE, dist))
			{
				return;
			}
		}
		
		if (random < 20)
		{
			if (tryCast(npc, target, AISkillScope.COT, dist))
			{
				return;
			}
		}
		
		if (random < 30)
		{
			if (tryCast(npc, target, AISkillScope.DEBUFF, dist))
			{
				return;
			}
		}
		
		if (random < 40)
		{
			if (tryCast(npc, target, AISkillScope.NEGATIVE, dist))
			{
				return;
			}
		}
		
		if (npc.isMovementDisabled() || (npc.getAiType() == AIType.MAGE) || (npc.getAiType() == AIType.HEALER))
		{
			if (tryCast(npc, target, AISkillScope.ATTACK, dist))
			{
				return;
			}
		}
		
		if (tryCast(npc, target, AISkillScope.UNIVERSAL, dist))
		{
			return;
		}
		
		// If cannot cast, try to attack.
		if ((dist <= range) && GeoEngine.getInstance().canSeeTarget(npc, target))
		{
			_actor.doAttack(target);
			return;
		}
		
		// If cannot cast nor attack, find a new target.
		targetReconsider();
	}
	
	private boolean tryCast(L2Attackable npc, L2Character target, AISkillScope aiSkillScope, double dist)
	{
		for (Skill sk : npc.getTemplate().getAISkills(aiSkillScope))
		{
			if (!checkSkillCastConditions(npc, sk) || (((sk.getCastRange() + target.getTemplate().getCollisionRadius()) <= dist) && !canAura(sk)))
			{
				continue;
			}
			
			if (!GeoEngine.getInstance().canSeeTarget(npc, target))
			{
				continue;
			}
			
			clientStopMoving(null);
			npc.doCast(sk);
			return true;
		}
		return false;
	}
	
	/**
	 * @param caster the caster
	 * @param skill the skill to check.
	 * @return {@code true} if the skill is available for casting {@code false} otherwise.
	 */
	private static boolean checkSkillCastConditions(L2Attackable caster, Skill skill)
	{
		if (caster.isCastingNow() && !skill.isSimultaneousCast())
		{
			return false;
		}
		
		// Not enough MP.
		if (skill.getMpConsume() >= caster.getCurrentMp())
		{
			return false;
		}
		// Character is in "skill disabled" mode.
		if (caster.isSkillDisabled(skill))
		{
			return false;
		}
		// If is a static skill and magic skill and character is muted or is a physical skill muted and character is physically muted.
		if (!skill.isStatic() && ((skill.isMagic() && caster.isMuted()) || caster.isPhysicalMuted()))
		{
			return false;
		}
		return true;
	}
	
	private L2Character effectTargetReconsider(Skill sk, boolean positive)
	{
		if (sk == null)
		{
			return null;
		}
		final L2Attackable actor = getActiveChar();
		if (!sk.hasEffectType(L2EffectType.DISPEL, L2EffectType.DISPEL_BY_SLOT))
		{
			if (!positive)
			{
				double dist = 0;
				double dist2 = 0;
				int range = 0;
				
				for (L2Character obj : actor.getAttackByList())
				{
					if ((obj == null) || obj.isDead() || !GeoEngine.getInstance().canSeeTarget(actor, obj) || (obj == getAttackTarget()))
					{
						continue;
					}
					try
					{
						actor.setTarget(getAttackTarget());
						dist = actor.calculateDistance2D(obj);
						dist2 = dist - actor.getTemplate().getCollisionRadius();
						range = sk.getCastRange() + actor.getTemplate().getCollisionRadius() + obj.getTemplate().getCollisionRadius();
						if (obj.isMoving())
						{
							dist2 -= 70;
						}
					}
					catch (NullPointerException e)
					{
						continue;
					}
					if ((dist2 <= range) && !getAttackTarget().isAffectedBySkill(sk.getId()))
					{
						return obj;
					}
				}
				
				// ----------------------------------------------------------------------
				// If there is nearby Target with aggro, start going on random target that is attackable
				for (L2Character obj : L2World.getInstance().getVisibleObjectsInRange(actor, L2Character.class, range))
				{
					if (obj.isDead() || !GeoEngine.getInstance().canSeeTarget(actor, obj))
					{
						continue;
					}
					try
					{
						actor.setTarget(getAttackTarget());
						dist = actor.calculateDistance2D(obj);
						dist2 = dist;
						range = sk.getCastRange() + actor.getTemplate().getCollisionRadius() + obj.getTemplate().getCollisionRadius();
						if (obj.isMoving())
						{
							dist2 -= 70;
						}
					}
					catch (NullPointerException e)
					{
						continue;
					}
					
					if ((obj.isPlayer() || obj.isSummon()) && (dist2 <= range) && !getAttackTarget().isAffectedBySkill(sk.getId()))
					{
						return obj;
					}
				}
			}
			else if (positive)
			{
				double dist = 0;
				double dist2 = 0;
				int range = 0;
				for (L2Attackable targets : L2World.getInstance().getVisibleObjectsInRange(actor, L2Attackable.class, range))
				{
					if (targets.isDead() || !GeoEngine.getInstance().canSeeTarget(actor, targets))
					{
						continue;
					}
					
					if (targets.isInMyClan(actor))
					{
						continue;
					}
					
					try
					{
						actor.setTarget(getAttackTarget());
						dist = actor.calculateDistance2D(targets);
						dist2 = dist - actor.getTemplate().getCollisionRadius();
						range = sk.getCastRange() + actor.getTemplate().getCollisionRadius() + targets.getTemplate().getCollisionRadius();
						if (targets.isMoving())
						{
							dist2 -= 70;
						}
					}
					catch (NullPointerException e)
					{
						continue;
					}
					if ((dist2 <= range) && !targets.isAffectedBySkill(sk.getId()))
					{
						return targets;
					}
				}
			}
		}
		else
		{
			double dist = 0;
			double dist2 = 0;
			int range = sk.getCastRange() + actor.getTemplate().getCollisionRadius() + getAttackTarget().getTemplate().getCollisionRadius();
			for (L2Character obj : L2World.getInstance().getVisibleObjectsInRange(actor, L2Character.class, range))
			{
				if (obj.isDead() || !GeoEngine.getInstance().canSeeTarget(actor, obj))
				{
					continue;
				}
				try
				{
					actor.setTarget(getAttackTarget());
					dist = actor.calculateDistance2D(obj);
					dist2 = dist - actor.getTemplate().getCollisionRadius();
					range = sk.getCastRange() + actor.getTemplate().getCollisionRadius() + obj.getTemplate().getCollisionRadius();
					if (obj.isMoving())
					{
						dist2 -= 70;
					}
				}
				catch (NullPointerException e)
				{
					continue;
				}
				
				if ((obj.isPlayer() || obj.isSummon()) && (dist2 <= range) && (getAttackTarget().getEffectList().getFirstEffect(L2EffectType.BUFF) != null))
				{
					return obj;
				}
			}
		}
		return null;
	}
	
	private L2Character skillTargetReconsider(Skill sk)
	{
		double dist = 0;
		double dist2 = 0;
		int range = 0;
		final L2Attackable actor = getActiveChar();
		if (actor.getHateList() != null)
		{
			for (L2Character obj : actor.getHateList())
			{
				if ((obj == null) || !GeoEngine.getInstance().canSeeTarget(actor, obj) || obj.isDead())
				{
					continue;
				}
				try
				{
					actor.setTarget(getAttackTarget());
					dist = actor.calculateDistance2D(obj);
					dist2 = dist - actor.getTemplate().getCollisionRadius();
					range = sk.getCastRange() + actor.getTemplate().getCollisionRadius() + getAttackTarget().getTemplate().getCollisionRadius();
					// if(obj.isMoving())
					// dist2 = dist2 - 40;
				}
				catch (NullPointerException e)
				{
					continue;
				}
				if (dist2 <= range)
				{
					return obj;
				}
			}
		}
		
		if (!(actor instanceof L2GuardInstance))
		{
			for (L2Object target : L2World.getInstance().getVisibleObjects(actor, L2Object.class))
			{
				try
				{
					actor.setTarget(getAttackTarget());
					dist = actor.calculateDistance2D(target);
					dist2 = dist;
					range = sk.getCastRange() + actor.getTemplate().getCollisionRadius() + getAttackTarget().getTemplate().getCollisionRadius();
					// if(obj.isMoving())
					// dist2 = dist2 - 40;
				}
				catch (NullPointerException e)
				{
					continue;
				}
				final L2Character obj = target.isCharacter() ? (L2Character) target : null;
				if ((obj == null) || !GeoEngine.getInstance().canSeeTarget(actor, obj) || (dist2 > range))
				{
					continue;
				}
				if (obj.isPlayer())
				{
					return obj;
				}
				if (obj.isAttackable() && actor.isChaos())
				{
					if (!((L2Attackable) obj).isInMyClan(actor))
					{
						return obj;
					}
					continue;
				}
				if (obj.isSummon())
				{
					return obj;
				}
			}
		}
		return null;
	}
	
	private void targetReconsider()
	{
		double dist = 0;
		double dist2 = 0;
		int range = 0;
		final L2Attackable actor = getActiveChar();
		final L2Character MostHate = actor.getMostHated();
		if (actor.getHateList() != null)
		{
			for (L2Character obj : actor.getHateList())
			{
				if ((obj == null) || !GeoEngine.getInstance().canSeeTarget(actor, obj) || obj.isDead() || (obj != MostHate) || (obj == actor))
				{
					continue;
				}
				try
				{
					dist = actor.calculateDistance2D(obj);
					dist2 = dist - actor.getTemplate().getCollisionRadius();
					range = actor.getPhysicalAttackRange() + actor.getTemplate().getCollisionRadius() + obj.getTemplate().getCollisionRadius();
					if (obj.isMoving())
					{
						dist2 -= 70;
					}
				}
				catch (NullPointerException e)
				{
					continue;
				}
				
				if (dist2 <= range)
				{
					actor.addDamageHate(obj, 0, MostHate != null ? actor.getHating(MostHate) : 2000);
					actor.setTarget(obj);
					setAttackTarget(obj);
					return;
				}
			}
		}
		if (!(actor instanceof L2GuardInstance))
		{
			L2World.getInstance().forEachVisibleObject(actor, L2Character.class, obj ->
			{
				if ((obj == null) || !GeoEngine.getInstance().canSeeTarget(actor, obj) || obj.isDead() || (obj != MostHate) || (obj == actor) || (obj == getAttackTarget()))
				{
					return;
				}
				if (obj.isPlayer())
				{
					actor.addDamageHate(obj, 0, MostHate != null ? actor.getHating(MostHate) : 2000);
					actor.setTarget(obj);
					setAttackTarget(obj);
				}
				else if (obj.isAttackable())
				{
					if (actor.isChaos())
					{
						if (((L2Attackable) obj).isInMyClan(actor))
						{
							return;
						}
						actor.addDamageHate(obj, 0, MostHate != null ? actor.getHating(MostHate) : 2000);
						actor.setTarget(obj);
						setAttackTarget(obj);
					}
				}
				else if (obj.isSummon())
				{
					actor.addDamageHate(obj, 0, MostHate != null ? actor.getHating(MostHate) : 2000);
					actor.setTarget(obj);
					setAttackTarget(obj);
				}
			});
		}
	}
	
	private void aggroReconsider()
	{
		final L2Attackable actor = getActiveChar();
		final L2Character MostHate = actor.getMostHated();
		if (actor.getHateList() != null)
		{
			final int rand = Rnd.get(actor.getHateList().size());
			int count = 0;
			for (L2Character obj : actor.getHateList())
			{
				if (count < rand)
				{
					count++;
					continue;
				}
				
				if ((obj == null) || !GeoEngine.getInstance().canSeeTarget(actor, obj) || obj.isDead() || (obj == getAttackTarget()) || (obj == actor))
				{
					continue;
				}
				
				try
				{
					actor.setTarget(getAttackTarget());
				}
				catch (NullPointerException e)
				{
					continue;
				}
				actor.addDamageHate(obj, 0, MostHate != null ? actor.getHating(MostHate) : 2000);
				actor.setTarget(obj);
				setAttackTarget(obj);
				return;
			}
		}
		
		if (!(actor instanceof L2GuardInstance))
		{
			L2World.getInstance().forEachVisibleObject(actor, L2Character.class, obj ->
			{
				if (!GeoEngine.getInstance().canSeeTarget(actor, obj) || obj.isDead() || (obj != MostHate) || (obj == actor))
				{
					return;
				}
				if (obj.isPlayer())
				{
					actor.addDamageHate(obj, 0, (MostHate != null) && !MostHate.isDead() ? actor.getHating(MostHate) : 2000);
					actor.setTarget(obj);
					setAttackTarget(obj);
				}
				else if (obj.isAttackable())
				{
					if (actor.isChaos())
					{
						if (((L2Attackable) obj).isInMyClan(actor))
						{
							return;
						}
						actor.addDamageHate(obj, 0, MostHate != null ? actor.getHating(MostHate) : 2000);
						actor.setTarget(obj);
						setAttackTarget(obj);
					}
				}
				else if (obj.isSummon())
				{
					actor.addDamageHate(obj, 0, MostHate != null ? actor.getHating(MostHate) : 2000);
					actor.setTarget(obj);
					setAttackTarget(obj);
				}
			});
		}
	}
	
	/**
	 * Manage AI thinking actions of a L2Attackable.
	 */
	@Override
	protected void onEvtThink()
	{
		// Check if the actor can't use skills and if a thinking action isn't already in progress
		if (_thinking || getActiveChar().isAllSkillsDisabled())
		{
			return;
		}
		
		// Start thinking action
		_thinking = true;
		
		try
		{
			// Manage AI thinks of a L2Attackable
			switch (getIntention())
			{
				case AI_INTENTION_ACTIVE:
				{
					thinkActive();
					break;
				}
				case AI_INTENTION_ATTACK:
				{
					thinkAttack();
					break;
				}
				case AI_INTENTION_CAST:
				{
					thinkCast();
					break;
				}
			}
		}
		catch (Exception e)
		{
			// LOGGER.warning(getClass().getSimpleName() + ": " + this.getActor().getName() + " - onEvtThink() failed!");
		}
		finally
		{
			// Stop thinking action
			_thinking = false;
		}
	}
	
	/**
	 * Launch actions corresponding to the Event Attacked.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Init the attack : Calculate the attack timeout, Set the _globalAggro to 0, Add the attacker to the actor _aggroList</li>
	 * <li>Set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance</li>
	 * <li>Set the Intention to AI_INTENTION_ATTACK</li>
	 * </ul>
	 * @param attacker The L2Character that attacks the actor
	 */
	@Override
	protected void onEvtAttacked(L2Character attacker)
	{
		final L2Attackable me = getActiveChar();
		
		// Calculate the attack timeout
		_attackTimeout = MAX_ATTACK_TIMEOUT + GameTimeController.getInstance().getGameTicks();
		
		// Set the _globalAggro to 0 to permit attack even just after spawn
		if (_globalAggro < 0)
		{
			_globalAggro = 0;
		}
		
		// Add the attacker to the _aggroList of the actor
		me.addDamageHate(attacker, 0, 1);
		
		// Set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance
		if (!me.isRunning())
		{
			me.setRunning();
		}
		
		// Set the Intention to AI_INTENTION_ATTACK
		if (getIntention() != AI_INTENTION_ATTACK)
		{
			setIntention(AI_INTENTION_ATTACK, attacker);
		}
		else if (me.getMostHated() != getAttackTarget())
		{
			setIntention(AI_INTENTION_ATTACK, attacker);
		}
		
		if (me.isMonster())
		{
			L2MonsterInstance master = (L2MonsterInstance) me;
			
			if (master.hasMinions())
			{
				master.getMinionList().onAssist(me, attacker);
			}
			
			master = master.getLeader();
			if ((master != null) && master.hasMinions())
			{
				master.getMinionList().onAssist(me, attacker);
			}
		}
		
		super.onEvtAttacked(attacker);
	}
	
	/**
	 * Launch actions corresponding to the Event Aggression.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Add the target to the actor _aggroList or update hate if already present</li>
	 * <li>Set the actor Intention to AI_INTENTION_ATTACK (if actor is L2GuardInstance check if it isn't too far from its home location)</li>
	 * </ul>
	 * @param aggro The value of hate to add to the actor against the target
	 */
	@Override
	protected void onEvtAggression(L2Character target, int aggro)
	{
		final L2Attackable me = getActiveChar();
		if (me.isDead() || (target == null))
		{
			return;
		}
		
		// Add the target to the actor _aggroList or update hate if already present
		me.addDamageHate(target, 0, aggro);
		
		// Set the actor AI Intention to AI_INTENTION_ATTACK
		if (getIntention() != AI_INTENTION_ATTACK)
		{
			// Set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance
			if (!me.isRunning())
			{
				me.setRunning();
			}
			
			setIntention(AI_INTENTION_ATTACK, target);
		}
		
		if (me.isMonster())
		{
			L2MonsterInstance master = (L2MonsterInstance) me;
			
			if (master.hasMinions())
			{
				master.getMinionList().onAssist(me, target);
			}
			
			master = master.getLeader();
			if ((master != null) && master.hasMinions())
			{
				master.getMinionList().onAssist(me, target);
			}
		}
	}
	
	@Override
	protected void onIntentionActive()
	{
		// Cancel attack timeout
		_attackTimeout = Integer.MAX_VALUE;
		super.onIntentionActive();
	}
	
	public void setGlobalAggro(int value)
	{
		_globalAggro = value;
	}
	
	public L2Attackable getActiveChar()
	{
		return (L2Attackable) _actor;
	}
	
	public int getFearTime()
	{
		return _fearTime;
	}
	
	public void setFearTime(int fearTime)
	{
		_fearTime = fearTime;
	}
}

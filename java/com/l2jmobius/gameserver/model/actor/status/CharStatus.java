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
package com.l2jmobius.gameserver.model.actor.status;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.stats.Formulas;

public class CharStatus
{
	protected static final Logger LOGGER = Logger.getLogger(CharStatus.class.getName());
	
	private final L2Character _activeChar;
	
	private double _currentHp = 0; // Current HP of the L2Character
	private double _currentMp = 0; // Current MP of the L2Character
	
	/** Array containing all clients that need to be notified about hp/mp updates of the L2Character */
	private Set<L2Character> _statusListener;
	
	private Future<?> _regTask;
	
	protected byte _flagsRegenActive = 0;
	
	protected static final byte REGEN_FLAG_CP = 4;
	private static final byte REGEN_FLAG_HP = 1;
	private static final byte REGEN_FLAG_MP = 2;
	
	public CharStatus(L2Character activeChar)
	{
		_activeChar = activeChar;
	}
	
	/**
	 * Add the object to the list of L2Character that must be informed of HP/MP updates of this L2Character.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Each L2Character owns a list called <B>_statusListener</B> that contains all L2PcInstance to inform of HP/MP updates.<br>
	 * Players who must be informed are players that target this L2Character.<br>
	 * When a RegenTask is in progress sever just need to go through this list to send Server->Client packet StatusUpdate.<br>
	 * <B><U>Example of use</U>:</B>
	 * <ul>
	 * <li>Target a PC or NPC</li>
	 * <ul>
	 * @param object L2Character to add to the listener
	 */
	public final void addStatusListener(L2Character object)
	{
		if (object == _activeChar)
		{
			return;
		}
		
		getStatusListener().add(object);
	}
	
	/**
	 * Remove the object from the list of L2Character that must be informed of HP/MP updates of this L2Character.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Each L2Character owns a list called <B>_statusListener</B> that contains all L2PcInstance to inform of HP/MP updates.<br>
	 * Players who must be informed are players that target this L2Character.<br>
	 * When a RegenTask is in progress sever just need to go through this list to send Server->Client packet StatusUpdate.<br>
	 * <B><U>Example of use </U>:</B>
	 * <ul>
	 * <li>Untarget a PC or NPC</li>
	 * </ul>
	 * @param object L2Character to add to the listener
	 */
	public final void removeStatusListener(L2Character object)
	{
		getStatusListener().remove(object);
	}
	
	/**
	 * Return the list of L2Character that must be informed of HP/MP updates of this L2Character.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Each L2Character owns a list called <B>_statusListener</B> that contains all L2PcInstance to inform of HP/MP updates.<br>
	 * Players who must be informed are players that target this L2Character.<br>
	 * When a RegenTask is in progress sever just need to go through this list to send Server->Client packet StatusUpdate.
	 * @return The list of L2Character to inform or null if empty
	 */
	public final Set<L2Character> getStatusListener()
	{
		if (_statusListener == null)
		{
			_statusListener = ConcurrentHashMap.newKeySet();
		}
		return _statusListener;
	}
	
	// place holder, only PcStatus has CP
	public void reduceCp(int value)
	{
	}
	
	/**
	 * Reduce the current HP of the L2Character and launch the doDie Task if necessary.
	 * @param value
	 * @param attacker
	 */
	public void reduceHp(double value, L2Character attacker)
	{
		reduceHp(value, attacker, true, false, false);
	}
	
	public void reduceHp(double value, L2Character attacker, boolean isHpConsumption)
	{
		reduceHp(value, attacker, true, false, isHpConsumption);
	}
	
	public void reduceHp(double value, L2Character attacker, boolean awake, boolean isDOT, boolean isHPConsumption)
	{
		if (_activeChar.isDead())
		{
			return;
		}
		
		// invul handling
		if (_activeChar.isInvul() && !(isDOT || isHPConsumption))
		{
			return;
		}
		
		if (attacker != null)
		{
			final L2PcInstance attackerPlayer = attacker.getActingPlayer();
			if ((attackerPlayer != null) && attackerPlayer.isGM() && !attackerPlayer.getAccessLevel().canGiveDamage())
			{
				return;
			}
		}
		
		if (!isDOT && !isHPConsumption)
		{
			_activeChar.stopEffectsOnDamage(awake);
			if (_activeChar.isStunned() && (Rnd.get(10) == 0))
			{
				_activeChar.stopStunning(true);
			}
		}
		
		if (value > 0)
		{
			setCurrentHp(Math.max(_currentHp - value, 0));
		}
		
		if ((_activeChar.getCurrentHp() < 0.5) && _activeChar.isMortal()) // Die
		{
			_activeChar.abortAttack();
			_activeChar.abortCast();
			
			_activeChar.doDie(attacker);
		}
	}
	
	public void reduceMp(double value)
	{
		setCurrentMp(Math.max(_currentMp - value, 0));
	}
	
	/**
	 * Start the HP/MP/CP Regeneration task.<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>Calculate the regen task period</li>
	 * <li>Launch the HP/MP/CP Regeneration task with Medium priority</li>
	 * </ul>
	 */
	public final synchronized void startHpMpRegeneration()
	{
		if ((_regTask == null) && !_activeChar.isDead())
		{
			// Get the Regeneration period
			final int period = Formulas.getRegeneratePeriod(_activeChar);
			
			// Create the HP/MP/CP Regeneration task
			_regTask = ThreadPool.scheduleAtFixedRate(this::doRegeneration, period, period);
		}
	}
	
	/**
	 * Stop the HP/MP/CP Regeneration task.<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>Set the RegenActive flag to False</li>
	 * <li>Stop the HP/MP/CP Regeneration task</li>
	 * </ul>
	 */
	public final synchronized void stopHpMpRegeneration()
	{
		if (_regTask != null)
		{
			// Stop the HP/MP/CP Regeneration task
			_regTask.cancel(false);
			_regTask = null;
			
			// Set the RegenActive flag to false
			_flagsRegenActive = 0;
		}
	}
	
	// place holder, only PcStatus has CP
	public double getCurrentCp()
	{
		return 0;
	}
	
	// place holder, only PcStatus has CP
	public void setCurrentCp(double newCp)
	{
	}
	
	public final double getCurrentHp()
	{
		return _currentHp;
	}
	
	public final void setCurrentHp(double newHp)
	{
		setCurrentHp(newHp, true);
	}
	
	/**
	 * Sets the current hp of this character.
	 * @param newHp the new hp
	 * @param broadcastPacket if true StatusUpdate packet will be broadcasted.
	 * @return @{code true} if hp was changed, @{code false} otherwise.
	 */
	public boolean setCurrentHp(double newHp, boolean broadcastPacket)
	{
		// Get the Max HP of the L2Character
		final int currentHp = (int) _currentHp;
		final double maxHp = _activeChar.getMaxHp();
		
		synchronized (this)
		{
			if (_activeChar.isDead())
			{
				return false;
			}
			
			if (newHp >= maxHp)
			{
				// Set the RegenActive flag to false
				_currentHp = maxHp;
				_flagsRegenActive &= ~REGEN_FLAG_HP;
				
				// Stop the HP/MP/CP Regeneration task
				if (_flagsRegenActive == 0)
				{
					stopHpMpRegeneration();
				}
			}
			else
			{
				// Set the RegenActive flag to true
				_currentHp = newHp;
				_flagsRegenActive |= REGEN_FLAG_HP;
				
				// Start the HP/MP/CP Regeneration task with Medium priority
				startHpMpRegeneration();
			}
		}
		
		final boolean hpWasChanged = currentHp != _currentHp;
		
		// Send the Server->Client packet StatusUpdate with current HP and MP to all other L2PcInstance to inform
		if (hpWasChanged && broadcastPacket)
		{
			_activeChar.broadcastStatusUpdate();
		}
		
		return hpWasChanged;
	}
	
	public final void setCurrentHpMp(double newHp, double newMp)
	{
		if (setCurrentHp(newHp, false) | setCurrentMp(newMp, false))
		{
			_activeChar.broadcastStatusUpdate();
		}
	}
	
	public final double getCurrentMp()
	{
		return _currentMp;
	}
	
	public final void setCurrentMp(double newMp)
	{
		setCurrentMp(newMp, true);
	}
	
	/**
	 * Sets the current mp of this character.
	 * @param newMp the new mp
	 * @param broadcastPacket if true StatusUpdate packet will be broadcasted.
	 * @return @{code true} if mp was changed, @{code false} otherwise.
	 */
	public final boolean setCurrentMp(double newMp, boolean broadcastPacket)
	{
		// Get the Max MP of the L2Character
		final int currentMp = (int) _currentMp;
		final int maxMp = _activeChar.getMaxMp();
		
		synchronized (this)
		{
			if (_activeChar.isDead())
			{
				return false;
			}
			
			if (newMp >= maxMp)
			{
				// Set the RegenActive flag to false
				_currentMp = maxMp;
				_flagsRegenActive &= ~REGEN_FLAG_MP;
				
				// Stop the HP/MP/CP Regeneration task
				if (_flagsRegenActive == 0)
				{
					stopHpMpRegeneration();
				}
			}
			else
			{
				// Set the RegenActive flag to true
				_currentMp = newMp;
				_flagsRegenActive |= REGEN_FLAG_MP;
				
				// Start the HP/MP/CP Regeneration task with Medium priority
				startHpMpRegeneration();
			}
		}
		
		final boolean mpWasChanged = currentMp != _currentMp;
		
		// Send the Server->Client packet StatusUpdate with current HP and MP to all other L2PcInstance to inform
		if (mpWasChanged && broadcastPacket)
		{
			_activeChar.broadcastStatusUpdate();
		}
		
		return mpWasChanged;
	}
	
	protected void doRegeneration()
	{
		// Modify the current HP/MP of the L2Character and broadcast Server->Client packet StatusUpdate
		if (!_activeChar.isDead() && ((_currentHp < _activeChar.getMaxRecoverableHp()) || (_currentMp < _activeChar.getMaxRecoverableMp())))
		{
			final double newHp = _currentHp + Formulas.calcHpRegen(_activeChar);
			final double newMp = _currentMp + Formulas.calcMpRegen(_activeChar);
			setCurrentHpMp(newHp, newMp);
		}
		else
		{
			stopHpMpRegeneration();
		}
	}
	
	public L2Character getActiveChar()
	{
		return _activeChar;
	}
}

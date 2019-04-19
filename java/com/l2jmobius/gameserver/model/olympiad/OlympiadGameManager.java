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
package com.l2jmobius.gameserver.model.olympiad;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.instancemanager.ZoneManager;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.zone.type.L2OlympiadStadiumZone;

/**
 * @author GodKratos, DS
 */
public class OlympiadGameManager implements Runnable
{
	private static final Logger LOGGER = Logger.getLogger(OlympiadGameManager.class.getName());
	
	private volatile boolean _battleStarted = false;
	private final OlympiadGameTask[] _tasks;
	
	protected OlympiadGameManager()
	{
		final Collection<L2OlympiadStadiumZone> zones = ZoneManager.getInstance().getAllZones(L2OlympiadStadiumZone.class);
		if ((zones == null) || zones.isEmpty())
		{
			throw new Error("No olympiad stadium zones defined !");
		}
		
		_tasks = new OlympiadGameTask[zones.size()];
		int i = 0;
		int instanceId = 0;
		for (L2OlympiadStadiumZone zone : zones)
		{
			switch (zone.getName())
			{
				case "Grassy Arena":
				{
					instanceId = InstanceManager.getInstance().createDynamicInstance(147).getId();
					break;
				}
				case "Three Bridges Arena":
				{
					instanceId = InstanceManager.getInstance().createDynamicInstance(148).getId();
					break;
				}
				case "Heros's Vestiges Arena":
				{
					instanceId = InstanceManager.getInstance().createDynamicInstance(149).getId();
					break;
				}
				case "Orbis Arena":
				{
					instanceId = InstanceManager.getInstance().createDynamicInstance(150).getId();
					break;
				}
			}
			_tasks[i++] = new OlympiadGameTask(zone, instanceId);
		}
		
		LOGGER.log(Level.INFO, "Olympiad System: Loaded " + _tasks.length + " stadiums.");
	}
	
	public static OlympiadGameManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	protected final boolean isBattleStarted()
	{
		return _battleStarted;
	}
	
	protected final void startBattle()
	{
		_battleStarted = true;
	}
	
	@Override
	public final void run()
	{
		if (Olympiad.getInstance().isOlympiadEnd())
		{
			return;
		}
		
		if (Olympiad.getInstance().inCompPeriod())
		{
			OlympiadGameTask task;
			AbstractOlympiadGame newGame;
			
			List<List<Integer>> readyClassed = OlympiadManager.getInstance().hasEnoughRegisteredClassed();
			boolean readyNonClassed = OlympiadManager.getInstance().hasEnoughRegisteredNonClassed();
			boolean readyTeams = OlympiadManager.getInstance().hasEnoughRegisteredTeams();
			
			if ((readyClassed != null) || readyNonClassed || readyTeams)
			{
				// set up the games queue
				for (int i = 0; i < _tasks.length; i++)
				{
					task = _tasks[i];
					synchronized (task)
					{
						if (!task.isRunning())
						{
							// Fair arena distribution
							// 0,2,4,6,8.. arenas checked for classed or teams first
							if (((readyClassed != null) || readyTeams) && ((i % 2) == 0))
							{
								// 0,4,8.. arenas checked for teams first
								if (readyTeams && ((i % 4) == 0))
								{
									newGame = OlympiadGameTeams.createGame(i, OlympiadManager.getInstance().getRegisteredTeamsBased());
									if (newGame != null)
									{
										task.attachGame(newGame);
										continue;
									}
									readyTeams = false;
								}
								// if no ready teams found check for classed
								if (readyClassed != null)
								{
									newGame = OlympiadGameClassed.createGame(i, readyClassed);
									if (newGame != null)
									{
										task.attachGame(newGame);
										continue;
									}
									readyClassed = null;
								}
							}
							// 1,3,5,7,9.. arenas used for non-classed
							// also other arenas will be used for non-classed if no classed or teams available
							if (readyNonClassed)
							{
								newGame = OlympiadGameNonClassed.createGame(i, OlympiadManager.getInstance().getRegisteredNonClassBased());
								if (newGame != null)
								{
									task.attachGame(newGame);
									continue;
								}
								readyNonClassed = false;
							}
						}
					}
					
					// stop generating games if no more participants
					if ((readyClassed == null) && !readyNonClassed && !readyTeams)
					{
						break;
					}
				}
			}
		}
		// not in competition period
		else if (isAllTasksFinished())
		{
			OlympiadManager.getInstance().clearRegistered();
			_battleStarted = false;
			LOGGER.log(Level.INFO, "Olympiad System: All current games finished.");
		}
	}
	
	public final boolean isAllTasksFinished()
	{
		for (OlympiadGameTask task : _tasks)
		{
			if (task.isRunning())
			{
				return false;
			}
		}
		return true;
	}
	
	public final OlympiadGameTask getOlympiadTask(int id)
	{
		return (id < 0) || (id >= _tasks.length) ? null : _tasks[id];
	}
	
	public final int getNumberOfStadiums()
	{
		return _tasks.length;
	}
	
	public final void notifyCompetitorDamage(L2PcInstance player, int damage)
	{
		if (player == null)
		{
			return;
		}
		
		final int id = player.getOlympiadGameId();
		if ((id < 0) || (id >= _tasks.length))
		{
			return;
		}
		
		final AbstractOlympiadGame game = _tasks[id].getGame();
		if (game != null)
		{
			game.addDamage(player, damage);
		}
	}
	
	private static class SingletonHolder
	{
		protected static final OlympiadGameManager _instance = new OlympiadGameManager();
	}
}

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

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Predicate;

import com.l2jmobius.commons.util.EmptyQueue;
import com.l2jmobius.gameserver.model.events.listeners.AbstractEventListener;

/**
 * @author UnAfraid
 */
public class ListenersContainer
{
	private volatile Map<EventType, Queue<AbstractEventListener>> _listeners = new ConcurrentHashMap<>();
	
	/**
	 * Registers listener for a callback when specified event is executed.
	 * @param listener
	 * @return
	 */
	public AbstractEventListener addListener(AbstractEventListener listener)
	{
		if (listener == null)
		{
			throw new NullPointerException("Listener cannot be null!");
		}
		getListeners().computeIfAbsent(listener.getType(), k -> new PriorityBlockingQueue<>()).add(listener);
		return listener;
	}
	
	/**
	 * Unregisters listener for a callback when specified event is executed.
	 * @param listener
	 * @return
	 */
	public AbstractEventListener removeListener(AbstractEventListener listener)
	{
		if (listener == null)
		{
			throw new NullPointerException("Listener cannot be null!");
		}
		else if (!_listeners.containsKey(listener.getType()))
		{
			throw new IllegalAccessError("Listeners container doesn't had " + listener.getType() + " event type added!");
		}
		
		_listeners.get(listener.getType()).remove(listener);
		return listener;
	}
	
	/**
	 * @param type
	 * @return {@code List} of {@link AbstractEventListener} by the specified type
	 */
	public Queue<AbstractEventListener> getListeners(EventType type)
	{
		return _listeners.containsKey(type) ? _listeners.get(type) : EmptyQueue.emptyQueue();
	}
	
	public void removeListenerIf(EventType type, Predicate<? super AbstractEventListener> filter)
	{
		getListeners(type).stream().filter(filter).forEach(AbstractEventListener::unregisterMe);
	}
	
	public void removeListenerIf(Predicate<? super AbstractEventListener> filter)
	{
		getListeners().values().forEach(queue -> queue.stream().filter(filter).forEach(AbstractEventListener::unregisterMe));
	}
	
	public boolean hasListener(EventType type)
	{
		return !getListeners(type).isEmpty();
	}
	
	/**
	 * Creates the listeners container map if doesn't exists.
	 * @return the listeners container map.
	 */
	private Map<EventType, Queue<AbstractEventListener>> getListeners()
	{
		return _listeners;
	}
}

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
package com.l2jmobius.gameserver.instancemanager.tasks;

import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;

import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.instancemanager.FourSepulchersManager;

/**
 * Four Sepulchers change cool down time task.
 * @author xban1x
 */
public final class FourSepulchersChangeCoolDownTimeTask implements Runnable
{
	@Override
	public void run()
	{
		final FourSepulchersManager manager = FourSepulchersManager.getInstance();
		manager.setIsEntryTime(false);
		manager.setIsWarmUpTime(false);
		manager.setIsAttackTime(false);
		manager.setIsCoolDownTime(true);
		
		manager.clean();
		
		final Calendar time = Calendar.getInstance();
		// one hour = 55th min to 55 min of next hour, so we check for this,
		// also check for first launch
		if (!manager.isFirstTimeRun() && (Calendar.getInstance().get(Calendar.MINUTE) > manager.getCycleMin()))
		{
			time.set(Calendar.HOUR, Calendar.getInstance().get(Calendar.HOUR) + 1);
		}
		time.set(Calendar.MINUTE, manager.getCycleMin());
		if (manager.isFirstTimeRun())
		{
			manager.setIsFirstTimeRun(false);
		}
		
		final long interval = time.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
		
		manager.setChangeEntryTimeTask(ThreadPool.schedule(new FourSepulchersChangeEntryTimeTask(), interval));
		final ScheduledFuture<?> changeCoolDownTimeTask = manager.getChangeCoolDownTimeTask();
		
		if (changeCoolDownTimeTask != null)
		{
			changeCoolDownTimeTask.cancel(true);
			manager.setChangeCoolDownTimeTask(null);
		}
	}
}

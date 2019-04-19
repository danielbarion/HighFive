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

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.instancemanager.FourSepulchersManager;

/**
 * Four Sepulchers change entry time task.
 * @author xban1x
 */
public final class FourSepulchersChangeEntryTimeTask implements Runnable
{
	@Override
	public void run()
	{
		final FourSepulchersManager manager = FourSepulchersManager.getInstance();
		manager.setIsEntryTime(true);
		manager.setIsWarmUpTime(false);
		manager.setIsAttackTime(false);
		manager.setIsCoolDownTime(false);
		
		final long interval = manager.isFirstTimeRun() ? manager.getEntrytTimeEnd() - Calendar.getInstance().getTimeInMillis() : Config.FS_TIME_ENTRY * 60000;
		// launching saying process...
		ThreadPool.schedule(new FourSepulchersManagerSayTask(), 0);
		manager.setChangeWarmUpTimeTask(ThreadPool.schedule(new FourSepulchersChangeWarmUpTimeTask(), interval));
		final ScheduledFuture<?> changeEntryTimeTask = manager.getChangeEntryTimeTask();
		
		if (changeEntryTimeTask != null)
		{
			changeEntryTimeTask.cancel(true);
			manager.setChangeEntryTimeTask(null);
		}
	}
}

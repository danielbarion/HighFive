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
 * Four Sepulchers change attack time task.
 * @author xban1x
 */
public final class FourSepulchersChangeAttackTimeTask implements Runnable
{
	@Override
	public void run()
	{
		final FourSepulchersManager manager = FourSepulchersManager.getInstance();
		manager.setIsEntryTime(false);
		manager.setIsWarmUpTime(false);
		manager.setIsAttackTime(true);
		manager.setIsCoolDownTime(false);
		
		manager.locationShadowSpawns();
		
		manager.spawnMysteriousBox(31921);
		manager.spawnMysteriousBox(31922);
		manager.spawnMysteriousBox(31923);
		manager.spawnMysteriousBox(31924);
		
		if (!manager.isFirstTimeRun())
		{
			manager.setWarmUpTimeEnd(Calendar.getInstance().getTimeInMillis());
		}
		
		long interval = 0;
		// say task
		if (manager.isFirstTimeRun())
		{
			for (double min = Calendar.getInstance().get(Calendar.MINUTE); min < manager.getCycleMin(); min++)
			{
				// looking for next shout time....
				if ((min % 5) == 0)// check if min can be divided by 5
				{
					final Calendar inter = Calendar.getInstance();
					inter.set(Calendar.MINUTE, (int) min);
					ThreadPool.schedule(new FourSepulchersManagerSayTask(), inter.getTimeInMillis() - Calendar.getInstance().getTimeInMillis());
					break;
				}
			}
		}
		else
		{
			ThreadPool.schedule(new FourSepulchersManagerSayTask(), 5 * 60400);
		}
		// searching time when attack time will be ended:
		// counting difference between time when attack time ends and
		// current time
		// and then launching change time task
		if (manager.isFirstTimeRun())
		{
			interval = manager.getAttackTimeEnd() - Calendar.getInstance().getTimeInMillis();
		}
		else
		{
			interval = Config.FS_TIME_ATTACK * 60000;
		}
		
		manager.setChangeCoolDownTimeTask(ThreadPool.schedule(new FourSepulchersChangeCoolDownTimeTask(), interval));
		final ScheduledFuture<?> changeAttackTimeTask = manager.getChangeAttackTimeTask();
		
		if (changeAttackTimeTask != null)
		{
			changeAttackTimeTask.cancel(true);
			manager.setChangeAttackTimeTask(null);
		}
	}
}

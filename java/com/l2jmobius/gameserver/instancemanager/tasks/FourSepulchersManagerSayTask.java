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

import com.l2jmobius.Config;
import com.l2jmobius.commons.concurrent.ThreadPool;
import com.l2jmobius.gameserver.instancemanager.FourSepulchersManager;

/**
 * Four Sepulchers manager say tasks.
 * @author xban1x
 */
public final class FourSepulchersManagerSayTask implements Runnable
{
	@Override
	public void run()
	{
		if (FourSepulchersManager.getInstance().isAttackTime())
		{
			final Calendar tmp = Calendar.getInstance();
			tmp.setTimeInMillis(Calendar.getInstance().getTimeInMillis() - FourSepulchersManager.getInstance().getWarmUpTimeEnd());
			if ((tmp.get(Calendar.MINUTE) + 5) < Config.FS_TIME_ATTACK)
			{
				FourSepulchersManager.getInstance().managerSay((byte) tmp.get(Calendar.MINUTE)); // byte
				// because
				// minute
				// cannot be
				// more than
				// 59
				ThreadPool.schedule(new FourSepulchersManagerSayTask(), 5 * 60000);
			}
			// attack time ending chat
			else if ((tmp.get(Calendar.MINUTE) + 5) >= Config.FS_TIME_ATTACK)
			{
				FourSepulchersManager.getInstance().managerSay((byte) 90); // sending a unique id :D
			}
		}
		else if (FourSepulchersManager.getInstance().isEntryTime())
		{
			FourSepulchersManager.getInstance().managerSay((byte) 0);
		}
	}
}
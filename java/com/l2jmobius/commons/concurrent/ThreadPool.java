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
package com.l2jmobius.commons.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.l2jmobius.Config;

/**
 * This class handles thread pooling system. It relies on two ThreadPoolExecutor arrays, which poolers number is generated using config.
 * <p>
 * Those arrays hold following pools:
 * </p>
 * <ul>
 * <li>Scheduled pool keeps a track about incoming, future events.</li>
 * <li>Instant pool handles short-life events.</li>
 * </ul>
 */
public final class ThreadPool
{
	private static final Logger LOGGER = Logger.getLogger(ThreadPool.class.getName());
	
	private static ScheduledThreadPoolExecutor[] SCHEDULED_POOLS;
	private static ThreadPoolExecutor[] INSTANT_POOLS;
	private static int THREAD_POOL_RANDOMIZER;
	
	/**
	 * Init the different pools, based on Config. It is launched only once, on Gameserver instance.
	 */
	public static void init()
	{
		// Feed scheduled pool.
		int scheduledPoolCount = Config.SCHEDULED_THREAD_POOL_COUNT;
		if (scheduledPoolCount == -1)
		{
			scheduledPoolCount = Runtime.getRuntime().availableProcessors();
		}
		
		SCHEDULED_POOLS = new ScheduledThreadPoolExecutor[scheduledPoolCount];
		for (int i = 0; i < scheduledPoolCount; i++)
		{
			SCHEDULED_POOLS[i] = new ScheduledThreadPoolExecutor(Config.THREADS_PER_SCHEDULED_THREAD_POOL);
		}
		
		// Feed instant pool.
		int instantPoolCount = Config.INSTANT_THREAD_POOL_COUNT;
		if (instantPoolCount == -1)
		{
			instantPoolCount = Runtime.getRuntime().availableProcessors();
		}
		
		INSTANT_POOLS = new ThreadPoolExecutor[instantPoolCount];
		for (int i = 0; i < instantPoolCount; i++)
		{
			INSTANT_POOLS[i] = new ThreadPoolExecutor(Config.THREADS_PER_INSTANT_THREAD_POOL, Config.THREADS_PER_INSTANT_THREAD_POOL, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100000));
		}
		
		// Prestart core threads.
		for (ScheduledThreadPoolExecutor threadPool : SCHEDULED_POOLS)
		{
			threadPool.setRejectedExecutionHandler(new RejectedExecutionHandlerImpl());
			threadPool.prestartAllCoreThreads();
		}
		
		for (ThreadPoolExecutor threadPool : INSTANT_POOLS)
		{
			threadPool.setRejectedExecutionHandler(new RejectedExecutionHandlerImpl());
			threadPool.prestartAllCoreThreads();
		}
		
		// Launch purge task.
		scheduleAtFixedRate(() ->
		{
			purge();
		}, 600000, 600000);
		
		LOGGER.info("ThreadPool: Initialized");
		LOGGER.info("..." + scheduledPoolCount + " scheduled pool executors with " + (scheduledPoolCount * Config.THREADS_PER_SCHEDULED_THREAD_POOL) + " total threads.");
		LOGGER.info("..." + instantPoolCount + " instant pool executors with " + (instantPoolCount * Config.THREADS_PER_INSTANT_THREAD_POOL) + " total threads.");
	}
	
	public static void purge()
	{
		for (ScheduledThreadPoolExecutor threadPool1 : SCHEDULED_POOLS)
		{
			threadPool1.purge();
		}
		for (ThreadPoolExecutor threadPool2 : INSTANT_POOLS)
		{
			threadPool2.purge();
		}
	}
	
	/**
	 * Schedules a one-shot action that becomes enabled after a delay. The pool is chosen based on pools activity.
	 * @param r : the task to execute.
	 * @param delay : the time from now to delay execution.
	 * @return a ScheduledFuture representing pending completion of the task and whose get() method will return null upon completion.
	 */
	public static ScheduledFuture<?> schedule(Runnable r, long delay)
	{
		try
		{
			return getPool(SCHEDULED_POOLS).schedule(new RunnableWrapper(r), delay, TimeUnit.MILLISECONDS);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/**
	 * Schedules a periodic action that becomes enabled after a delay. The pool is chosen based on pools activity.
	 * @param r : the task to execute.
	 * @param delay : the time from now to delay execution.
	 * @param period : the period between successive executions.
	 * @return a ScheduledFuture representing pending completion of the task and whose get() method will throw an exception upon cancellation.
	 */
	public static ScheduledFuture<?> scheduleAtFixedRate(Runnable r, long delay, long period)
	{
		try
		{
			return getPool(SCHEDULED_POOLS).scheduleAtFixedRate(new RunnableWrapper(r), delay, period, TimeUnit.MILLISECONDS);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/**
	 * Executes the given task sometime in the future.
	 * @param r : the task to execute.
	 */
	public static void execute(Runnable r)
	{
		try
		{
			getPool(INSTANT_POOLS).execute(new RunnableWrapper(r));
		}
		catch (Exception e)
		{
		}
	}
	
	/**
	 * @param <T> : The pool type.
	 * @param threadPools : The pool array to check.
	 * @return the less fed pool.
	 */
	private static <T> T getPool(T[] threadPools)
	{
		return threadPools[THREAD_POOL_RANDOMIZER++ % threadPools.length];
	}
	
	public static String[] getStats()
	{
		final String[] stats = new String[(SCHEDULED_POOLS.length + INSTANT_POOLS.length) * 10];
		int pos = 0;
		for (int i = 0; i < SCHEDULED_POOLS.length; i++)
		{
			final ScheduledThreadPoolExecutor threadPool = SCHEDULED_POOLS[i];
			stats[pos++] = "Scheduled pool #" + i + ":";
			stats[pos++] = " |- ActiveCount: ...... " + threadPool.getActiveCount();
			stats[pos++] = " |- CorePoolSize: ..... " + threadPool.getCorePoolSize();
			stats[pos++] = " |- PoolSize: ......... " + threadPool.getPoolSize();
			stats[pos++] = " |- LargestPoolSize: .. " + threadPool.getLargestPoolSize();
			stats[pos++] = " |- MaximumPoolSize: .. " + threadPool.getMaximumPoolSize();
			stats[pos++] = " |- CompletedTaskCount: " + threadPool.getCompletedTaskCount();
			stats[pos++] = " |- QueuedTaskCount: .. " + threadPool.getQueue().size();
			stats[pos++] = " |- TaskCount: ........ " + threadPool.getTaskCount();
			stats[pos++] = " | -------";
		}
		for (int i = 0; i < INSTANT_POOLS.length; i++)
		{
			final ThreadPoolExecutor threadPool = INSTANT_POOLS[i];
			stats[pos++] = "Instant pool #" + i + ":";
			stats[pos++] = " |- ActiveCount: ...... " + threadPool.getActiveCount();
			stats[pos++] = " |- CorePoolSize: ..... " + threadPool.getCorePoolSize();
			stats[pos++] = " |- PoolSize: ......... " + threadPool.getPoolSize();
			stats[pos++] = " |- LargestPoolSize: .. " + threadPool.getLargestPoolSize();
			stats[pos++] = " |- MaximumPoolSize: .. " + threadPool.getMaximumPoolSize();
			stats[pos++] = " |- CompletedTaskCount: " + threadPool.getCompletedTaskCount();
			stats[pos++] = " |- QueuedTaskCount: .. " + threadPool.getQueue().size();
			stats[pos++] = " |- TaskCount: ........ " + threadPool.getTaskCount();
			stats[pos++] = " | -------";
		}
		return stats;
	}
	
	/**
	 * Shutdown thread pooling system correctly. Send different informations.
	 */
	public static void shutdown()
	{
		try
		{
			LOGGER.info("ThreadPool: Shutting down.");
			
			for (ScheduledThreadPoolExecutor threadPool : SCHEDULED_POOLS)
			{
				threadPool.shutdownNow();
			}
			
			for (ThreadPoolExecutor threadPool : INSTANT_POOLS)
			{
				threadPool.shutdownNow();
			}
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
}
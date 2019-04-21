package com.vert.fakeplayer.tasks;

import com.vert.fakeplayer.FakePlayerTaskManager;

import com.l2jmobius.commons.concurrent.ThreadPool;

import java.util.List;

/**
 * @author vert
 */
public class AITaskRunner implements Runnable {
    @Override
    public void run()
    {
        FakePlayerTaskManager.INSTANCE.adjustTaskSize();
        List<AITask> aiTasks = FakePlayerTaskManager.INSTANCE.getAITasks();
        aiTasks.forEach(aiTask -> ThreadPool.execute(aiTask));
    }
}

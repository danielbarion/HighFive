package com.vert.autopilot;

import com.vert.autopilot.tasks.AITask;
import com.vert.autopilot.tasks.AITaskRunner;

import com.l2jmobius.commons.concurrent.ThreadPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vert
 */
public enum FakePlayerTaskManager {
    INSTANCE;

    private final int aiTaskRunnerInterval = 700;
    private final int _playerCountPerTask = 2000;
    private List<AITask> _aiTasks;

    private FakePlayerTaskManager(){

    }

    public void initialize() {
        ThreadPool.scheduleAtFixedRate(new AITaskRunner(), aiTaskRunnerInterval, aiTaskRunnerInterval);
        _aiTasks = new ArrayList<>();
    }

    public void adjustTaskSize() {
        int fakePlayerCount = FakePlayerManager.INSTANCE.getFakePlayersCount();
        int tasksNeeded = calculateTasksNeeded(fakePlayerCount);
        _aiTasks.clear();

        for(int i = 0; i < tasksNeeded; i++ ) {
            int from = i * _playerCountPerTask;
            int to = (i + 1) * _playerCountPerTask;
            _aiTasks.add(new AITask(from, to));
        }
    }

    private int calculateTasksNeeded(int fakePlayerCount)
    {
        return fakePlayerCount == 0 ? 0 : fakePlayerCount > 0 && fakePlayerCount < _playerCountPerTask ? 1 : (fakePlayerCount +_playerCountPerTask) / _playerCountPerTask;
    }

    public int getPlayerCountPerTask() {
        return _playerCountPerTask;
    }

    public int getTaskCount() {
        return _aiTasks.size();
    }

    public List<AITask> getAITasks(){
        return _aiTasks;
    }
}

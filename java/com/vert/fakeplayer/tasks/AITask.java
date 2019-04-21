package com.vert.fakeplayer.tasks;

import com.vert.fakeplayer.FakePlayer;
import com.vert.fakeplayer.FakePlayerManager;

import java.util.List;

/**
 * @author vert
 */
public class AITask implements Runnable {
    private int _from;
    private int _to;

    public AITask(int from, int to) {
        _from = from;
        _to = to;
    }

    @Override
    public void run()
    {
        adjustPotentialIndexOutOfBounds();
        List<FakePlayer> fakePlayers = FakePlayerManager.INSTANCE.getFakePlayers().subList(_from, _to);
        try {
            fakePlayers.stream().filter(x-> !x.getFakeAi().isBusyThinking()).forEach(x-> x.getFakeAi().thinkAndAct());
        }catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    private void adjustPotentialIndexOutOfBounds() {
        if(_to > FakePlayerManager.INSTANCE.getFakePlayersCount()) {
            _to = 	FakePlayerManager.INSTANCE.getFakePlayersCount();
        }
    }
}

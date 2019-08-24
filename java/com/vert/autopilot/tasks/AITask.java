package com.vert.autopilot.tasks;

import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.FakePlayerManager;

import java.util.List;
import java.util.stream.Collectors;

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
            aiThinkAndAct(fakePlayers);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    public void aiThinkAndAct(List<FakePlayer> fakePlayers) {
        if (fakePlayers.size() > 0) {
            if (fakePlayers.stream().anyMatch(player -> {
                if (player != null && player.getFakeAi() != null) {
                    return !player.getFakeAi().isBusyThinking();
                }

                return false;
            })) {
                List<FakePlayer> notBusyFakes = fakePlayers.stream().filter(player-> {
                    if (player != null && player.getFakeAi() != null) {
                        return !player.getFakeAi().isBusyThinking();
                    }

                    return false;
                }).collect(Collectors.toList());
                if (notBusyFakes.size() > 0) {
                    notBusyFakes.forEach(player-> {
                        if (!player.getFakeAi().isBusyThinking()) {
                            player.getFakeAi().thinkAndAct();
                        }
                    });
                }
            }
        }
    }

    private void adjustPotentialIndexOutOfBounds() {
        if(_to > FakePlayerManager.INSTANCE.getFakePlayersCount()) {
            _to = FakePlayerManager.INSTANCE.getFakePlayersCount();
        }
    }
}

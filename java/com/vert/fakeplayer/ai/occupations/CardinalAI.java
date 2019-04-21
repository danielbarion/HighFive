package com.vert.fakeplayer.ai.occupations;

import com.l2jmobius.gameserver.enums.ShotType;

import com.l2jmobius.gameserver.model.skills.targets.L2TargetType;
import com.vert.fakeplayer.FakePlayer;
import com.vert.fakeplayer.ai.CombatAI;
import com.vert.fakeplayer.ai.interfaces.IHealer;
import com.vert.fakeplayer.helpers.FakeHelpers;
import com.vert.fakeplayer.models.HealingSpell;
import com.vert.fakeplayer.models.OffensiveSpell;
import com.vert.fakeplayer.models.SupportSpell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vert
 */
public class CardinalAI extends CombatAI implements IHealer {
    public CardinalAI(FakePlayer character)
    {
        super(character);
    }

    @Override
    public void thinkAndAct()
    {
        super.thinkAndAct();
        setBusyThinking(true);
        applyDefaultBuffs();
        handleShots();
        tryTargetingLowestHpTargetInRadius(_fakePlayer, FakePlayer.class, FakeHelpers.getTestTargetRange());
        tryHealingTarget(_fakePlayer);
        setBusyThinking(false);
    }

    @Override
    protected ShotType getShotType()
    {
        return ShotType.BLESSED_SPIRITSHOTS;
    }

    @Override
    protected List<OffensiveSpell> getOffensiveSpells()
    {
        return Collections.emptyList();
    }

    @Override
    protected List<HealingSpell> getHealingSpells()
    {
        List<HealingSpell> _healingSpells = new ArrayList<>();
        _healingSpells.add(new HealingSpell(1218, L2TargetType.ONE, 70, 1));
        _healingSpells.add(new HealingSpell(1217, L2TargetType.ONE, 80, 3));
        return _healingSpells;
    }

    @Override
    protected int[][] getBuffs()
    {
        return FakeHelpers.getMageBuffs();
    }

    @Override
    protected List<SupportSpell> getSelfSupportSpells() {
        return Collections.emptyList();
    }
}

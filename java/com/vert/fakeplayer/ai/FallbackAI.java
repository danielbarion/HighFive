package com.vert.fakeplayer.ai;

import com.l2jmobius.gameserver.enums.ShotType;
import com.vert.fakeplayer.FakePlayer;
import com.vert.fakeplayer.models.HealingSpell;
import com.vert.fakeplayer.models.OffensiveSpell;
import com.vert.fakeplayer.models.SupportSpell;

import java.util.Collections;
import java.util.List;

/**
 * @author vert
 */
public class FallbackAI extends CombatAI {
    public FallbackAI(FakePlayer character)
    {
        super(character);
    }

    @Override
    public void thinkAndAct()
    {

    }

    @Override
    protected ShotType getShotType()
    {
        return ShotType.SOULSHOTS;
    }

    @Override
    protected List<OffensiveSpell> getOffensiveSpells()
    {
        return Collections.emptyList();
    }

    @Override
    protected int[][] getBuffs()
    {
        return new int[0][0];
    }

    @Override
    protected List<HealingSpell> getHealingSpells()
    {
        return Collections.emptyList();
    }

    @Override
    protected List<SupportSpell> getSelfSupportSpells() {
        return Collections.emptyList();
    }
}

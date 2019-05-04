package com.vert.fakeplayer.ai.occupations;

import com.l2jmobius.gameserver.enums.ShotType;
import com.vert.fakeplayer.FakePlayer;
import com.vert.fakeplayer.ai.CombatAI;
import com.vert.fakeplayer.ai.interfaces.IConsumableSpender;
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
public class GhostSentinelAI extends CombatAI implements IConsumableSpender {
    public GhostSentinelAI(FakePlayer character)
    {
        super(character);
    }

    @Override
    public void thinkAndAct()
    {
        super.thinkAndAct();
        setBusyThinking(true);
        applyDefaultBuffs();
        handleConsumable(_fakePlayer, getArrowId());
        selfSupportBuffs();
        handleShots();
        tryTargetRandomCreatureByTypeInRadius(FakeHelpers.getTestTargetClass(), FakeHelpers.getTestTargetRange());
        tryAttackingUsingFighterOffensiveSkill();
        setBusyThinking(false);
    }

    @Override
    protected double chanceOfUsingSkill() {
        return 0.15;
    }

    @Override
    protected ShotType getShotType()
    {
        return ShotType.SOULSHOTS;
    }

    @Override
    protected List<OffensiveSpell> getOffensiveSpells()
    {
        List<OffensiveSpell> _offensiveSpells = new ArrayList<>();
        _offensiveSpells.add(new OffensiveSpell(101, 1));
        _offensiveSpells.add(new OffensiveSpell(343, 1));
        return _offensiveSpells;
    }

    @Override
    protected int[][] getBuffs()
    {
        return FakeHelpers.getFighterBuffs();
    }

    @Override
    protected List<HealingSpell> getHealingSpells()
    {
        return Collections.emptyList();
    }

    @Override
    protected List<SupportSpell> getSelfSupportSpells() {
        List<SupportSpell> _selfSupportSpells = new ArrayList<>();
        _selfSupportSpells.add(new SupportSpell(99, 1));
        return _selfSupportSpells;
    }
}

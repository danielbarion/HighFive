package com.vert.autopilot.ai.occupations;

import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.CombatAI;
import com.vert.autopilot.ai.interfaces.IConsumableSpender;
import com.vert.autopilot.helpers.FakeHelpers;
import com.vert.autopilot.models.HealingSpell;
import com.vert.autopilot.models.OffensiveSpell;
import com.vert.autopilot.models.SpellUsageCondition;
import com.vert.autopilot.models.SupportSpell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vert
 */
public class DuelistAI extends CombatAI implements IConsumableSpender {
    public DuelistAI(FakePlayer character) {
        super(character);
    }

    @Override
    public void thinkAndAct() {
        super.thinkAndAct();
        setBusyThinking(true);
        applyDefaultBuffs();
        handleShots();
        selfSupportBuffs();
        tryTargetRandomCreatureByTypeInRadius(FakeHelpers.getTestTargetRange());
        tryAttackingUsingFighterOffensiveSkill();
        setBusyThinking(false);
    }

    @Override
    protected ShotType getShotType() {
        return ShotType.SOULSHOTS;
    }

    @Override
    protected double chanceOfUsingSkill() {
        return 0.5;
    }

    @Override
    protected List<OffensiveSpell> getOffensiveSpells() {
        List<OffensiveSpell> _offensiveSpells = new ArrayList<>();
        _offensiveSpells.add(new OffensiveSpell(345, 1));
        _offensiveSpells.add(new OffensiveSpell(261, 2));
        _offensiveSpells.add(new OffensiveSpell(5, 3));
        _offensiveSpells.add(new OffensiveSpell(6, 4));
        _offensiveSpells.add(new OffensiveSpell(1, 5));
        return _offensiveSpells;
    }

    @Override
    protected List<SupportSpell> getSelfSupportSpells() {
        List<SupportSpell> _selfSupportSpells = new ArrayList<>();
        _selfSupportSpells.add(new SupportSpell(139, 1));
        _selfSupportSpells.add(new SupportSpell(297, 2));
        _selfSupportSpells.add(new SupportSpell(440, SpellUsageCondition.MISSINGCP, 1000, 3));
        return _selfSupportSpells;
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
    protected boolean classOffensiveSkillsId(Skill skill) {
        return false;
    }
}

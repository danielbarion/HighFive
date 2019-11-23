package com.vert.autopilot.ai.occupations;

import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.CombatAI;
import com.vert.autopilot.ai.interfaces.IConsumableSpender;
import com.vert.autopilot.helpers.FakeHelpers;
import com.vert.autopilot.helpers.FarmHelpers;
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
public class DreadnoughtAI extends CombatAI implements IConsumableSpender {
    public DreadnoughtAI(FakePlayer character) {
        super(character);
    }

    @Override
    public void thinkAndAct() {
        super.thinkAndAct();
        setBusyThinking(true);
        applyDefaultBuffs();
        handleShots();
        selfSupportBuffs();
        tryTargetRandomCreatureByTypeInRadius(FarmHelpers.getTestTargetRange());
        tryAttackingUsingFighterOffensiveSkill();
        setBusyThinking(false);
    }

    @Override
    protected ShotType getShotType() {
        return ShotType.SOULSHOTS;
    }

    @Override
    protected double chanceOfUsingSkill() {
        return 0.33;
    }

    @Override
    protected List<OffensiveSpell> getOffensiveSpells() {
        List<OffensiveSpell> _offensiveSpells = new ArrayList<>();
        _offensiveSpells.add(new OffensiveSpell(361, 1));
        _offensiveSpells.add(new OffensiveSpell(347, 2));
        _offensiveSpells.add(new OffensiveSpell(48, 3));
        _offensiveSpells.add(new OffensiveSpell(452, 4));
        _offensiveSpells.add(new OffensiveSpell(36, 5));
        return _offensiveSpells;
    }

    @Override
    protected List<SupportSpell> getSelfSupportSpells() {
        List<SupportSpell> _selfSupportSpells = new ArrayList<>();
        _selfSupportSpells.add(new SupportSpell(440, SpellUsageCondition.MISSINGCP, 1000, 1));
        return _selfSupportSpells;
    }

    @Override
    protected int[][] getBuffs()
    {
        return FarmHelpers.getFighterBuffs();
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

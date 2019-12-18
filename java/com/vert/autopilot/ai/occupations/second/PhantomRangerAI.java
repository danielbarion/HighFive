package com.vert.autopilot.ai.occupations.second;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.CombatAI;
import com.vert.autopilot.ai.interfaces.IConsumableSpender;
import com.vert.autopilot.helpers.FakeHelpers;
import com.vert.autopilot.helpers.FarmHelpers;
import com.vert.autopilot.models.HealingSpell;
import com.vert.autopilot.models.SupportSpell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vert
 */
public class PhantomRangerAI extends CombatAI implements IConsumableSpender {
    public PhantomRangerAI(FakePlayer character)
    {
        super(character);
    }

    @Override
    public void thinkAndAct()
    {
        super.thinkAndAct();

        if (!canDoThinkAndActFlow()) {
            _fakePlayer.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
            return;
        }

        setBusyThinking(true);
        applyDefaultBuffs();
        handleConsumable(_fakePlayer, getArrowId());
        selfSupportBuffs();
        handleShots();
        tryTargetRandomCreatureByTypeInRadius(FarmHelpers.getTestTargetRange());
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
    protected List<SupportSpell> getSelfSupportSpells() {
        List<SupportSpell> _selfSupportSpells = new ArrayList<>();
        _selfSupportSpells.add(new SupportSpell(99, 1));
        return _selfSupportSpells;
    }

    @Override
    protected boolean classOffensiveSkillsId(Skill skill) {
        ArrayList<Integer> mappedSkills = new ArrayList<>();

        mappedSkills.add(101); // Stun
        mappedSkills.add(19);  // Double Shot
        mappedSkills.add(314);  // Fatal Counter

        return mappedSkills.stream().anyMatch(id -> id == skill.getId());
    }
}

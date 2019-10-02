package com.vert.autopilot.ai;

import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.interfaces.IConsumableSpender;
import com.vert.autopilot.helpers.FakeHelpers;
import com.vert.autopilot.models.HealingSpell;
import com.vert.autopilot.models.OffensiveSpell;
import com.vert.autopilot.models.SupportSpell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vert
 */
public class FallbackAI extends CombatAI implements IConsumableSpender {
    public FallbackAI(FakePlayer character)
    {
        super(character);
    }

    @Override
    public void thinkAndAct() {
        super.thinkAndAct();
        setBusyThinking(true);
        applyDefaultBuffs();
        selfSupportBuffs();
        handleConsumable(_fakePlayer, getArrowId());
        handleShots();
        tryTargetRandomCreatureByTypeInRadius(FakeHelpers.getTestTargetRange());
        tryAttackingUsingFighterOffensiveSkill();
        tryAttackingUsingMageOffensiveSkill();
        setBusyThinking(false);
    }

    @Override
    protected double chanceOfUsingSkill() {
        return 1;
    }

    @Override
    protected ShotType getShotType()
    {
        return ShotType.SOULSHOTS;
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
        return _selfSupportSpells;
    }

    @Override
    protected boolean classOffensiveSkillsId(Skill skill) {
        ArrayList<Integer> mappedSkills = new ArrayList<>();

        // Fighters 1-19 Skills
        mappedSkills.add(3); // Power Strike
        mappedSkills.add(16); // Mortal Blow
        mappedSkills.add(56); // Power Shot

        // Fighters 20-39 Skills
        mappedSkills.add(255); // Power Smash
        mappedSkills.add(101); // Stun Shot

        // Mages 1-19 Skills
        mappedSkills.add(1177); // Wind Strike
        mappedSkills.add(1184); // Ice Bolt
        mappedSkills.add(1090); // Life Drain

        // Mages 20-39 Skills
        mappedSkills.add(1220); // Blaze
        mappedSkills.add(1031); // Disrupt Undead
        mappedSkills.add(1264); // Solar Spark
        mappedSkills.add(1175); // Aqua Swirl
        mappedSkills.add(1178); // Twister

        return mappedSkills.stream().anyMatch(id -> id == skill.getId());
    }
}

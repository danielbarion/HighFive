package com.vert.autopilot.ai.occupations.third;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.CombatAI;
import com.vert.autopilot.helpers.FakeHelpers;
import com.vert.autopilot.helpers.FarmHelpers;
import com.vert.autopilot.models.HealingSpell;
import com.vert.autopilot.models.OffensiveSpell;
import com.vert.autopilot.models.SupportSpell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vert
 */
public class StormScreamerAI extends CombatAI {
    public StormScreamerAI(FakePlayer character)
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
        selfSupportBuffs();
        handleShots();
        tryTargetRandomCreatureByTypeInRadius(FarmHelpers.getTestTargetRange());
        tryAttackingUsingMageOffensiveSkill();
        setBusyThinking(false);
    }

    @Override
    protected ShotType getShotType()
    {
        return ShotType.BLESSED_SPIRITSHOTS;
    }

    @Override
    protected int[][] getBuffs()
    {
        return FarmHelpers.getMageBuffs();
    }

    @Override
    protected List<HealingSpell> getHealingSpells()
    {
        return Collections.emptyList();
    }

    @Override
    protected List<SupportSpell> getSelfSupportSpells() {
        List<SupportSpell> _selfSupportSpells = new ArrayList<>();
        _selfSupportSpells.add(new SupportSpell(1287, 1)); // Seed of Wind
        _selfSupportSpells.add(new SupportSpell(1494, 1)); // Hurricane Armor
        // Over power but spent too mutch mana, commented for a while.
        // _selfSupportSpells.add(new SupportSpell(1457, 1)); // Empowering Echo
        return _selfSupportSpells;
    }

    @Override
    protected boolean classOffensiveSkillsId(Skill skill) {
        ArrayList<Integer> mappedSkills = new ArrayList<>();

        // mappedSkills.add(1074); // Surrender To Wind
        mappedSkills.add(1239); // Hurricane
        mappedSkills.add(1341); // Wind Vortex
        mappedSkills.add(1343); // Dark Vortex
        mappedSkills.add(1458); // Throne of Wind
        // mappedSkills.add(1234); // Vampiric Claw

        return mappedSkills.stream().anyMatch(id -> id == skill.getId());
    }
}

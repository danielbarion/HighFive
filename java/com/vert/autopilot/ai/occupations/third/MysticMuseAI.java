package com.vert.autopilot.ai.occupations.third;

import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.CombatAI;
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
public class MysticMuseAI extends CombatAI {
    public MysticMuseAI(FakePlayer character)
    {
        super(character);
    }

    @Override
    public void thinkAndAct()
    {
        super.thinkAndAct();
        setBusyThinking(true);
        applyDefaultBuffs();
        selfSupportBuffs();
        handleShots();
        tryTargetRandomCreatureByTypeInRadius(FakeHelpers.getTestTargetRange());
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
        return FakeHelpers.getMageBuffs();
    }

    @Override
    protected List<HealingSpell> getHealingSpells()
    {
        return Collections.emptyList();
    }

    @Override
    protected List<SupportSpell> getSelfSupportSpells() {
        List<SupportSpell> _selfSupportSpells = new ArrayList<>();
        _selfSupportSpells.add(new SupportSpell(1238, 1)); // Freezing Skin
        _selfSupportSpells.add(new SupportSpell(1493, 1)); // Frost Armor
        _selfSupportSpells.add(new SupportSpell(1286, 1)); // Seed of Water
        return _selfSupportSpells;
    }

    @Override
    protected boolean classOffensiveSkillsId(Skill skill) {
        ArrayList<Integer> mappedSkills = new ArrayList<>();

        // mappedSkills.add(1071); // Surrender To Water
        mappedSkills.add(1235); // Hydro Blast
        mappedSkills.add(1265); // Solar Flare
        mappedSkills.add(1340); // Light Vortex
        mappedSkills.add(1342); // Ice Vortex
        mappedSkills.add(1455); // Throne of Ice

        return mappedSkills.stream().anyMatch(id -> id == skill.getId());
    }
}

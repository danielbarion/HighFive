package com.vert.fakeplayer.ai.occupations;

import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
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
public class SagittariusAI extends CombatAI implements IConsumableSpender {
    public SagittariusAI(FakePlayer character)
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
        handleConsumable(_fakePlayer, getArrowId());
        handleShots();
        tryTargetRandomCreatureByTypeInRadius(FakeHelpers.getTestTargetClass(), FakeHelpers.getTestTargetRange());
        tryAttackingUsingFighterOffensiveSkill();
        setBusyThinking(false);
    }

    @Override
    protected double changeOfUsingSkill() {
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

//        _offensiveSpells.add(new OffensiveSpell(101, 1)); // Stun
//        _offensiveSpells.add(new OffensiveSpell(343, 1)); // Lethal Shot
//        _offensiveSpells.add(new OffensiveSpell(19, 1));  // Double Shot
//        _offensiveSpells.add(new OffensiveSpell(987, 1));  // Multiple Shot
//        _offensiveSpells.add(new OffensiveSpell(990, 1));  // Death Shot
//        _offensiveSpells.add(new OffensiveSpell(771, 1));  // Flame Hawk
//        _offensiveSpells.add(new OffensiveSpell(924, 1));  // Seven Arrow

        this._fakePlayer.getSkills().values().stream().forEach(skill -> {
            if (classSkillsId(skill)) {
                System.out.println("\n" + skill.getName());
                System.out.println("isPhysical: " + skill.isPhysical());
                System.out.println("isActive: " + skill.isActive());
                System.out.println("isAOE: " + skill.isAOE());
                System.out.println("isPvPOnly: " + skill.isPvPOnly());
                System.out.println("Delay: " + skill.getReuseDelay());

                // TODO: priority need to be more precise instead current size + 1
                _offensiveSpells.add(new OffensiveSpell(skill.getId(), skill.getReuseDelay(), _offensiveSpells.size() + 1));
            }
        });

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

    protected boolean classSkillsId(Skill skill) {
        ArrayList<Integer> mappedSkills = new ArrayList<>();

        mappedSkills.add(101); // Stun
        mappedSkills.add(343); // Lethal Shot
        mappedSkills.add(19);  // Double Shot
        mappedSkills.add(987);  // Multiple Shot
        mappedSkills.add(990);  // Death Shot
        mappedSkills.add(771);  // Flame Hawk
        mappedSkills.add(924);  // Seven Arrow

        return mappedSkills.stream().anyMatch(id -> id == skill.getId());
    }
}

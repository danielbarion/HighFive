package com.vert.fakeplayer.ai.occupations;

import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.vert.fakeplayer.FakePlayer;
import com.vert.fakeplayer.ai.CombatAI;
import com.vert.fakeplayer.ai.interfaces.IConsumableSpender;
import com.vert.fakeplayer.helpers.FakeHelpers;
import com.vert.fakeplayer.models.HealingSpell;
import com.vert.fakeplayer.models.OffensiveSpell;
import com.vert.fakeplayer.models.SpellUsageCondition;
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
    private int offensiveSpellPriority = 0;

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

        this._fakePlayer.getSkills().values().stream().forEach(skill -> {
            if (classSkillsId(skill)) {
                // TODO: priority need to be more precise instead current size + 1
                _offensiveSpells.add(new OffensiveSpell(skill.getId(), SpellUsageCondition.NONE, 0, skill.getReuseDelay(), offensiveSpellPriority));

                offensiveSpellPriority += 1;
            }
        });

        offensiveSpellPriority = 0;

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

    // Todo: update another occupations with this method
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

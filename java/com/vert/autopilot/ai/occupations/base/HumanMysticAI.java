package com.vert.autopilot.ai.occupations.base;

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
public class HumanMysticAI extends CombatAI implements IConsumableSpender {
    private final int boneId = 2508;

    public HumanMysticAI(FakePlayer character)
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
        //TODO: Remove the bone and add potion
        handleConsumable(_fakePlayer, boneId);
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
        return Collections.emptyList();
    }

    @Override
    protected boolean classOffensiveSkillsId(Skill skill) {
        ArrayList<Integer> mappedSkills = new ArrayList<>();

        mappedSkills.add(1177); // Wind Strike
        mappedSkills.add(1184); // Ice Bolt
        mappedSkills.add(1147); // Vampire Touch

        return mappedSkills.stream().anyMatch(id -> id == skill.getId());
    }
}

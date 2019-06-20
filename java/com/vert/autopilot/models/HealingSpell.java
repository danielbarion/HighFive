package com.vert.autopilot.models;

import com.l2jmobius.gameserver.model.skills.targets.L2TargetType;

/**
 * @author vert
 */
public class HealingSpell extends BotSkill {
    private L2TargetType _targetType;

    public HealingSpell (int skillId, L2TargetType targetType, SpellUsageCondition condition, int conditionValue, int priority) {
        super(skillId, condition, conditionValue, priority);
        _targetType = targetType;
    }

    public HealingSpell (int skillId, L2TargetType targetType, int conditionValue, int priority) {
        super(skillId, SpellUsageCondition.LESSHPPERCENT, conditionValue, priority);
        _targetType = targetType;
    }

    public L2TargetType getTargetType() {
        return _targetType;
    }
}

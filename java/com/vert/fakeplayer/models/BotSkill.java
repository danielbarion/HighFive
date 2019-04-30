package com.vert.fakeplayer.models;

/**
 * @author vert
 */
public abstract class BotSkill {
    protected int _skillId;
    protected SpellUsageCondition _condition;
    protected int _conditionValue;
    protected int _priority;
    protected int _delay;

    public BotSkill(int skillId, SpellUsageCondition condition, int conditionValue, int delay, int priority) {
        _skillId = skillId;
        _condition = condition;
        _conditionValue = conditionValue;
        _delay = delay;
    }

    public BotSkill(int skillId, SpellUsageCondition condition, int conditionValue, int priority) {
        _skillId = skillId;
        _condition = condition;
        _conditionValue = conditionValue;
    }

    public BotSkill(int skillId) {
        _skillId = skillId;
        _condition = SpellUsageCondition.NONE;
        _conditionValue = 0;
        _priority = 0;
    }

    public int getSkillId() {
        return _skillId;
    }

    public SpellUsageCondition getCondition(){
        return _condition;
    }

    public int getConditionValue() {
        return _conditionValue;
    }

    public int getPriority() {
        return _priority;
    }

    public int get_delay() {
        return _delay;
    }

    public void set_delay(int _delay) {
        this._delay = _delay;
    }
}

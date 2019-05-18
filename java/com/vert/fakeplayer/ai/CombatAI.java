package com.vert.fakeplayer.ai;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.model.actor.L2Decoy;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.zone.ZoneId;

import com.vert.fakeplayer.FakePlayer;
import com.vert.fakeplayer.models.BotSkill;
import com.vert.fakeplayer.models.HealingSpell;
import com.vert.fakeplayer.models.OffensiveSpell;
import com.vert.fakeplayer.models.SupportSpell;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vert
 */
public abstract class CombatAI extends FakePlayerAI {
    int skillIndex = 0;

    public CombatAI(FakePlayer character) {
        super(character);
    }

    protected void tryAttackingUsingMageOffensiveSkill() {
        if(_fakePlayer.getTarget() != null) {
            BotSkill botSkill = getRandomAvaiableMageSpellForTarget();
            if(botSkill == null) {
                return;
            }

            Skill skill = _fakePlayer.getKnownSkill(botSkill.getSkillId());

            if(skill != null) {
                castSpell(skill);
            }
        }
    }

    protected void tryAttackingUsingFighterOffensiveSkill()	{
        if(_fakePlayer.getTarget() != null && (_fakePlayer.getTarget() instanceof L2Decoy || _fakePlayer.getTarget() instanceof L2MonsterInstance)) {
            _fakePlayer.forceAutoAttack(_fakePlayer.getTarget());
            if(Rnd.nextDouble() < chanceOfUsingSkill()) {
                if(getOffensiveSpells() != null && !getOffensiveSpells().isEmpty()) {
                    Skill skill = getRandomAvaiableFighterSpellForTarget();
                    if(skill != null) {
                        castSpell(skill);
                    }
                }
            }
        }
    }

    @Override
    public void thinkAndAct() {
        handleDeath();
    }

    protected int getShotId() {
        int playerLevel = _fakePlayer.getLevel();
        if(playerLevel < 20)
            return getShotType() == ShotType.SOULSHOTS ? 1835 : 3947;
        if(playerLevel >= 20 && playerLevel < 40)
            return getShotType() == ShotType.SOULSHOTS ? 1463 : 3948;
        if(playerLevel >= 40 && playerLevel < 52)
            return getShotType() == ShotType.SOULSHOTS ? 1464 : 3949;
        if(playerLevel >= 52 && playerLevel < 61)
            return getShotType() == ShotType.SOULSHOTS ? 1465 : 3950;
        if(playerLevel >= 61 && playerLevel < 76)
            return getShotType() == ShotType.SOULSHOTS ? 1466 : 3951;
        if(playerLevel >= 76)
            return getShotType() == ShotType.SOULSHOTS ? 1467 : 3952;

        return 0;
    }

    protected int getArrowId() {
        int playerLevel = _fakePlayer.getLevel();
        if(playerLevel < 20)
            return 17; // wooden arrow
        if(playerLevel >= 20 && playerLevel < 40)
            return 1341; // bone arrow
        if(playerLevel >= 40 && playerLevel < 52)
            return 1342; // steel arrow
        if(playerLevel >= 52 && playerLevel < 61)
            return 1343; // Silver arrow
        if(playerLevel >= 61 && playerLevel < 76)
            return 1344; // Mithril Arrow
        if(playerLevel >= 76)
            return 1345; // shining

        return 0;
    }

    protected void handleShots() {
        if(_fakePlayer.getInventory().getItemByItemId(getShotId()) != null) {
            if(_fakePlayer.getInventory().getItemByItemId(getShotId()).getCount() <= 20) {
                _fakePlayer.getInventory().addItem("", getShotId(), 500, _fakePlayer, null);
            }
        }else {
            _fakePlayer.getInventory().addItem("", getShotId(), 500, _fakePlayer, null);
        }

        if(_fakePlayer.getAutoSoulShot().isEmpty()) {
            _fakePlayer.addAutoSoulShot(getShotId());
            _fakePlayer.rechargeShots(true, true);
        }
    }

    public HealingSpell getRandomAvaiableHealingSpellForTarget() {

        if(getHealingSpells().isEmpty())
            return null;

        List<HealingSpell> spellsOrdered = getHealingSpells().stream().sorted((o1, o2)-> Integer.compare(o1.getPriority(), o2.getPriority())).collect(Collectors.toList());
        int skillListSize = spellsOrdered.size();
        BotSkill skill = waitAndPickAvailablePrioritisedSpell(spellsOrdered, skillListSize);
        return (HealingSpell)skill;
    }

    protected BotSkill getRandomAvaiableMageSpellForTarget() {

        List<OffensiveSpell> spellsOrdered = getOffensiveSpells().stream().sorted(Comparator.comparingInt(BotSkill::getPriority)).collect(Collectors.toList());

        int skillListSize = spellsOrdered.size();

        BotSkill skill = waitAndPickAvailablePrioritisedSpell(spellsOrdered, skillListSize);

        return skill;
    }

    private BotSkill waitAndPickAvailablePrioritisedSpell(List<? extends BotSkill> spellsOrdered, int skillListSize) {
        BotSkill botSkill = spellsOrdered.get(skillIndex);
        Skill skill = _fakePlayer.getKnownSkill(botSkill.getSkillId());

        _fakePlayer.getCurrentSkill().setCtrlPressed(!_fakePlayer.getTarget().isInsideZone(ZoneId.PEACE));

        if (skill.getCastRange() > 0)
        {
            if (!GeoEngine.getInstance().canSeeTarget(_fakePlayer, _fakePlayer.getTarget()))
            {
                moveToPawn(_fakePlayer.getTarget(), 100);//skill.getCastRange()
                return null;
            }
        }

        while(!_fakePlayer.checkUseMagicConditions(skill,true,false)) {
            _isBusyThinking = true;
            if(_fakePlayer.isDead() || _fakePlayer.isOutOfControl()) {
                return null;
            }
            if((skillIndex < 0) || (skillIndex >= skillListSize)) {
                return null;
            }
            skill = _fakePlayer.getKnownSkill(spellsOrdered.get(skillIndex).getSkillId());
            botSkill = spellsOrdered.get(skillIndex);
            skillIndex++;
        }
        return botSkill;
    }

    protected Skill getRandomAvaiableFighterSpellForTarget() {
        if (!_fakePlayer.isDead()) {
            List<OffensiveSpell> spellsOrdered = getOffensiveSpells().stream().sorted(Comparator.comparingInt(BotSkill::getPriority)).collect(Collectors.toList());

            int skillListSize = spellsOrdered.size();

            if ((skillIndex - 1) == skillListSize) {
                skillIndex = 0;
            }

            Skill skill = _fakePlayer.getKnownSkill(spellsOrdered.get(skillIndex).getSkillId());

            boolean hasReuseHashCode = _fakePlayer.hasSkillReuse(skill.getReuseHashCode());

            if (skill != null && !hasReuseHashCode) {
                _fakePlayer.setCurrentSkill(skill, !_fakePlayer.getTarget().isInsideZone(ZoneId.PEACE), false);

                while(!_fakePlayer.checkUseMagicConditions(skill,true,false)) {
                    if((skillIndex < 0) || (skillIndex >= skillListSize)) {
                        return null;
                    }
                    skill = _fakePlayer.getKnownSkill(spellsOrdered.get(skillIndex).getSkillId());
                    skillIndex++;
                }

                if(!_fakePlayer.checkUseMagicConditions(skill,true,false)) {
                    _fakePlayer.forceAutoAttack(_fakePlayer.getTarget());
                    return null;
                }

                return skill;
            } else {
                if (skillIndex == skillListSize) {
                    skillIndex = 0;
                } else {
                    skillIndex ++;
                }

                return getRandomAvaiableFighterSpellForTarget();
            }
        }

        return null;
    }

    protected void selfSupportBuffs() {
//        List<Integer> activeEffects = Arrays.stream(_fakePlayer.getEffectList().getEffects())
//                .map(x->x.getSkill().getId())
//                .collect(Collectors.toList());
//
//        for(SupportSpell selfBuff : getSelfSupportSpells()) {
//            if(activeEffects.contains(selfBuff.getSkillId()))
//                continue;
//
//            Skill skill = SkillTable.getInstance().getInfo(selfBuff.getSkillId(), _fakePlayer.getSkillLevel(selfBuff.getSkillId()));
//
//            if(!_fakePlayer.checkUseMagicConditions(skill,true,false))
//                continue;
//
//            switch(selfBuff.getCondition()) {
//                case LESSHPPERCENT:
//                    if(Math.round(100.0 / _fakePlayer.getMaxHp() * _fakePlayer.getCurrentHp()) <= selfBuff.getConditionValue()) {
//                        castSelfSpell(skill);
//                    }
//                    break;
//                case MISSINGCP:
//                    if(getMissingHealth() >= selfBuff.getConditionValue()) {
//                        castSelfSpell(skill);
//                    }
//                    break;
//                case NONE:
//                    castSelfSpell(skill);
//                default:
//                    break;
//            }
//
//        }
    }

    private double getMissingHealth() {
        return _fakePlayer.getMaxCp() - _fakePlayer.getCurrentCp();
    }

    protected double chanceOfUsingSkill() {
        return 1.0;
    }

    protected abstract ShotType getShotType();
    protected abstract List<OffensiveSpell> getOffensiveSpells();
    protected abstract List<HealingSpell> getHealingSpells();
    protected abstract List<SupportSpell> getSelfSupportSpells();
}

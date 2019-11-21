package com.vert.autopilot.ai;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.PlayerTemplateData;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Decoy;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.templates.L2PcTemplate;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.itemcontainer.PcInventory;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.zone.ZoneId;

import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.helpers.FakeHelpers;
import com.vert.autopilot.models.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.vert.autopilot.helpers.FakeHelpers.getFakeSelectedClass;
import static com.vert.autopilot.helpers.FakeHelpers.giveArmorsByClass;
import static com.vert.autopilot.helpers.FakeHelpers.giveWeaponsByClass;

/**
 * @author vert
 */
public abstract class CombatAI extends FakePlayerAI {
    int skillIndex = 0;
    int offensiveSpellPriority = 0;

    public CombatAI(FakePlayer character) {
        super(character);
    }

    protected void tryAttackingUsingMageOffensiveSkill() {
        boolean validInstanceType = (_fakePlayer.getTarget() instanceof L2Decoy || _fakePlayer.getTarget() instanceof L2MonsterInstance || _fakePlayer.getTarget() instanceof L2Character);

        if(_fakePlayer.getTarget() != null && validInstanceType) {
            Skill skill = getRandomAvaiableMageSpellForTarget();

            if(skill == null) {
                return;
            }

            // Log for Debug
            // System.out.println("Distance from target: " + _fakePlayer.calculateDistance2D(_fakePlayer.getTarget().getLocation()));
            // System.out.println("Magical Attack Range: " + _fakePlayer.getMagicalAttackRange(skill));
            checkRangeForAnAttackAndAttack(skill);
        }
    }

    protected void tryAttackingUsingFighterOffensiveSkill()	{
        boolean validInstanceType = (_fakePlayer.getTarget() instanceof L2Decoy || _fakePlayer.getTarget() instanceof L2MonsterInstance || _fakePlayer.getTarget() instanceof L2Character);
        Skill skill = null;

        if(_fakePlayer.getTarget() != null && validInstanceType) {
            if (_fakePlayer.getIsMovingToPickItem()) {
                return;
            }

            if (_fakePlayer.getTarget() != null && !_fakePlayer.isInsideZone(ZoneId.PEACE)) {
                // Log for Debug
                // System.out.println("Distance from target: " + _fakePlayer.calculateDistance2D(_fakePlayer.getTarget().getLocation()));

                if(Rnd.nextDouble() < chanceOfUsingSkill()) {
                    if(getOffensiveSpells() != null && !getOffensiveSpells().isEmpty()) {
                        skill = getRandomAvaiableFighterSpellForTarget();
                        // Log for Debug
                        // System.out.println("Magical Attack Range: " + _fakePlayer.getMagicalAttackRange(skill));
                        checkRangeForAnAttackAndAttack(skill);
                    }
                } else {
                    // Log for Debug
                    // System.out.println("Physical Attack Range: " + _fakePlayer.getPhysicalAttackRange());
                    checkRangeForAnAttackAndAttack(skill);
                }
            }
        }
    }

    protected void checkRangeForAnAttackAndAttack(Skill skill) {
        if (_fakePlayer.getTarget() != _fakePlayer) {
            double distanceFromTarget = _fakePlayer.calculateDistance2D(_fakePlayer.getTarget().getLocation());

            if (skill != null) {
                if (_fakePlayer.getMagicalAttackRange(skill) >= distanceFromTarget) {
                    _fakePlayer.stopMove(_fakePlayer.getLocation());
                    _fakePlayer.doCast(skill);
                } else {
                    _fakePlayer.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, _fakePlayer.getTarget().getLocation());
                }
            } else {
                if (distanceFromTarget > _fakePlayer.getPhysicalAttackRange()) {
                    _fakePlayer.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, _fakePlayer.getTarget());
                } else {
                    _fakePlayer.forceAutoAttack();
                }
            }
        }
    }

    @Override
    public void thinkAndAct() {
        handleDeath();
        saveLastCharacterPosition();
        checkOccupationAndItems();

        // todo: improve pick items logic
        // need do tests to check the flow
        // need to improve the flow to be more "human"
        /** default disabled because this need to be more tested **/
        // pickupItemsInGround();
    }

    /**
     * This method check the fake player level and occupation
     * and update his occupation and items when needed
     */
    protected void checkOccupationAndItems() {
        ClassId classId = getFakeSelectedClass(_fakePlayer.getOccupation(), _fakePlayer.getLevel());
        L2Object oldTarget = _fakePlayer.getTarget();
//        System.out.println(_fakePlayer.getClassId() + " | " + classId + " | " + _fakePlayer.getFinalClassId());

        if (_fakePlayer.getClassId() != null && _fakePlayer.getOccupation() != null && _fakePlayer.getClassId() != classId) {

            _fakePlayer.setBaseClass(classId);
            _fakePlayer.setLearningClass(classId);
            _fakePlayer.setClassId(classId.getId());
            _fakePlayer.rewardSkills();

            /**
             * Disable any active SoulShot before equip another weapon.
             * This is necessary  because magicians are activating SS from another
             * grade and doesn't give damage to anyone.
             */
            for (int soulShotId : _fakePlayer.getAutoSoulShot())
            {
                _fakePlayer.removeAutoSoulShot(soulShotId);
            }

            _fakePlayer.addAutoSoulShot(getShotId());

            removeOldItems();

            giveArmorsByClass(_fakePlayer);
            giveWeaponsByClass(_fakePlayer,false);

            _fakePlayer.assignDefaultAI();
            _fakePlayer.heal();
            _fakePlayer.setTarget(oldTarget);

            // When update fake player appearance, need broadcast the user info.
            _fakePlayer.broadcastUserInfo();
        }
    }

    protected void removeOldItems() {
        PcInventory inventory = _fakePlayer.getInventory();

        Arrays.stream(inventory.getItems()).forEach(item -> {
            if (item.isArmor() || item.isWeapon() && item.isEquipped()) {
                inventory.destroyItem("L2ItemInstance", item, _fakePlayer, null);
            }
        });
    }

    protected void pickupItemsInGround() {
        if (!_fakePlayer.getIsPickingItemInGround() && _fakePlayer.getItemToPick() == null && !_fakePlayer.getIsMovingToPickItem()) {
            getItemsSurroudingCharacterRegion();

            // Sort items to get the closest item
            Collections.sort(_itemsInGround, (WordRegion1, WordRegion2) -> (int) (_fakePlayer.calculateDistance2D(WordRegion1.getX(), WordRegion1.getY(), WordRegion1.getZ()) - _fakePlayer.calculateDistance2D(WordRegion2.getX(), WordRegion2.getY(), WordRegion2.getZ())));

            if (!_targets.isEmpty()) {
                if (!_itemsInGround.isEmpty() && _fakePlayer.getTarget() == null) {
                    _fakePlayer.setIsPickingItemInGround(true);
                    pickItemsInGround();
                } else {
                    _fakePlayer.setIsPickingItemInGround(false);
                }
            }
        } else {
            pickItemsInGround();
        }
    }

    protected void saveLastCharacterPosition() {
        int lastX = _fakePlayer.getLastServerPosition().getX();
        int lastY = _fakePlayer.getLastServerPosition().getY();
        int lastZ = _fakePlayer.getLastServerPosition().getZ();
        // This is very necessary to prevent send player to { x: 0, y: 0, z: 0 }
        // When prevent player moving on ledges
        if (_fakePlayer.getX() != lastX && _fakePlayer.getY() != lastY && _fakePlayer.getZ() != lastZ) {
            _fakePlayer.setLastServerPosition(_fakePlayer.getX(), _fakePlayer.getY(), _fakePlayer.getZ());
        }
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
            return 1345; // Shinning Arrow

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

        List<HealingSpell> spellsOrdered = getHealingSpells().stream().sorted(Comparator.comparingInt(BotSkill::getPriority)).collect(Collectors.toList());
        int skillListSize = spellsOrdered.size();
        BotSkill skill = waitAndPickAvailablePrioritisedSpell(spellsOrdered, skillListSize);
        return (HealingSpell)skill;
    }

    protected Skill getRandomAvaiableMageSpellForTarget() {
        if (!_fakePlayer.isDead()) {
            List<OffensiveSpell> spellsOrdered = getOffensiveSpells().stream().sorted(Comparator.comparingInt(BotSkill::getPriority)).collect(Collectors.toList());

            int skillListSize = spellsOrdered.size();

            if (skillIndex == skillListSize) {
                skillIndex = 0;
                return null;
            }

            Skill skill = _fakePlayer.getKnownSkill(spellsOrdered.get(skillIndex).getSkillId());

            if (skill != null && !_fakePlayer.isSkillDisabled(skill)) {
                _fakePlayer.setCurrentSkill(skill, !_fakePlayer.getTarget().isInsideZone(ZoneId.PEACE), false);

                while(!_fakePlayer.checkUseMagicConditions(skill,true,false)) {
                    if((skillIndex < 0) || (skillIndex >= skillListSize)) {
                        return null;
                    }
                    skill = _fakePlayer.getKnownSkill(spellsOrdered.get(skillIndex).getSkillId());
                    skillIndex++;
                }

                if(!_fakePlayer.checkUseMagicConditions(skill,true,false)) {
                    _fakePlayer.forceAutoAttack();
                    return null;
                }

                return skill;
            } else {
                if (skillIndex == skillListSize) {
                    skillIndex = 0;
                } else {
                    skillIndex ++;
                }

                return getRandomAvaiableMageSpellForTarget();
            }
        }

        return null;
    }

    private BotSkill waitAndPickAvailablePrioritisedSpell(List<? extends BotSkill> spellsOrdered, int skillListSize) {
        BotSkill botSkill = spellsOrdered.get(skillIndex);
        Skill skill = _fakePlayer.getKnownSkill(botSkill.getSkillId());

        _fakePlayer.getCurrentSkill().setCtrlPressed(!_fakePlayer.getTarget().isInsideZone(ZoneId.PEACE));

        if (skill.getCastRange() > 0)
        {
            if (!GeoEngine.getInstance().canSeeTarget(_fakePlayer, _fakePlayer.getTarget()))
            {
                moveToPawn(_fakePlayer.getTarget(), 100); //skill.getCastRange()
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

            if (skillIndex == skillListSize) {
                skillIndex = 0;
                return null;
            }

            Skill skill = _fakePlayer.getKnownSkill(spellsOrdered.get(skillIndex).getSkillId());

            if (skill != null && !_fakePlayer.isSkillDisabled(skill)) {
                _fakePlayer.setCurrentSkill(skill, !_fakePlayer.getTarget().isInsideZone(ZoneId.PEACE), false);

                while(!_fakePlayer.checkUseMagicConditions(skill,true,false)) {
                    if((skillIndex < 0) || (skillIndex >= skillListSize)) {
                        return null;
                    }
                    skill = _fakePlayer.getKnownSkill(spellsOrdered.get(skillIndex).getSkillId());
                    skillIndex++;
                }

                if(!_fakePlayer.checkUseMagicConditions(skill,true,false)) {
                    _fakePlayer.forceAutoAttack();
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
        List<Integer> activeEffects = _fakePlayer.getEffectList().getEffects().stream()
                .map(effect -> effect.getSkill().getId()).collect(Collectors.toList());

        for (SupportSpell selfBuff : getSelfSupportSpells()) {
            if(activeEffects.contains(selfBuff.getSkillId()) || _fakePlayer.getKnownSkill(selfBuff.getSkillId()) == null) {
                continue;
            }

            L2Object initialTarget = _fakePlayer.getTarget();

            if (_fakePlayer.getKnownSkill(selfBuff.getSkillId()) != null) {
                if (_fakePlayer.getTarget() != _fakePlayer) {
                    _fakePlayer.setTarget(_fakePlayer);
                }

                Skill skill = SkillData.getInstance().getSkill(selfBuff.getSkillId(), _fakePlayer.getSkillLevel(selfBuff.getSkillId()));

                switch(selfBuff.getCondition()) {
                    case LESSHPPERCENT:
                        if(Math.round(100.0 / _fakePlayer.getMaxHp() * _fakePlayer.getCurrentHp()) <= selfBuff.getConditionValue()) {
                            castSelfSpell(skill);
                        }
                        break;
                    case MISSINGCP:
                        if(getMissingHealth() >= selfBuff.getConditionValue()) {
                            castSelfSpell(skill);
                        }
                        break;
                    case NONE:
                        castSelfSpell(skill);
                    default:
                        break;
                }
            }

            _fakePlayer.setTarget(initialTarget);
        }
    }

    private double getMissingHealth() {
        return _fakePlayer.getMaxCp() - _fakePlayer.getCurrentCp();
    }

    protected double chanceOfUsingSkill() {
        return 1.0;
    }

    protected abstract ShotType getShotType();

    protected List<OffensiveSpell> getOffensiveSpells()
    {
        List<OffensiveSpell> _offensiveSpells = new ArrayList<>();

        this._fakePlayer.getSkills().values().stream().forEach(skill -> {
            if (classOffensiveSkillsId(skill)) {
                // TODO: priority need to be more precise instead current size + 1
                _offensiveSpells.add(new OffensiveSpell(skill.getId(), SpellUsageCondition.NONE, 0, skill.getReuseDelay(), offensiveSpellPriority));

                offensiveSpellPriority += 1;
            }
        });

        offensiveSpellPriority = 0;

        return _offensiveSpells;
    }

    protected abstract boolean classOffensiveSkillsId(Skill skill);
    protected abstract List<HealingSpell> getHealingSpells();
    protected abstract List<SupportSpell> getSelfSupportSpells();
}

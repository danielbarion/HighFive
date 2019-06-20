package com.vert.autopilot.ai.interfaces;

import com.l2jmobius.gameserver.model.actor.L2Decoy;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.CombatAI;
import com.vert.autopilot.models.HealingSpell;

/**
 * @author vert
 */
public interface IHealer {
    default void tryTargetingLowestHpTargetInRadius(FakePlayer player, Class<? extends L2PcInstance> creatureClass, int radius) {
//        if(player.getTarget() == null) {
//            List<L2Object> targets = player.getWorldRegion().getVisibleObjects().stream()
//                    .filter(x->!x.isDead())
//                    .collect(Collectors.toList());
//
//            if(!player.isDead())
//                targets.add(player);
//
//            List<L2Decoy> sortedTargets = targets.stream().sorted((x1, x2) -> Double.compare(x1.getCurrentHp(), x2.getCurrentHp())).collect(Collectors.toList());
//
//            if(!sortedTargets.isEmpty()) {
//                L2Decoy target = sortedTargets.get(0);
//                player.setTarget(target);
//            }
//        }else {
//            if(((L2Decoy)player.getTarget()).isDead())
//                player.setTarget(null);
//        }
    }

    default void tryHealingTarget(FakePlayer player) {

        if(player.getTarget() != null && player.getTarget() instanceof L2Decoy)
        {
            L2Decoy target = (L2Decoy) player.getTarget();
            if(player.getFakeAi() instanceof CombatAI) {
                HealingSpell skill = ((CombatAI)player.getFakeAi()).getRandomAvaiableHealingSpellForTarget();
                if(skill != null) {
                    switch(skill.getCondition()){
                        case LESSHPPERCENT:
                            double currentHpPercentage = Math.round(100.0 / target.getMaxHp() * target.getCurrentHp());
                            if(currentHpPercentage <= skill.getConditionValue()) {
                                player.getFakeAi().castSpell(player.getKnownSkill(skill.getSkillId()));
                            }
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }
}

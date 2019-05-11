package com.vert.fakeplayer.ai;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.datatables.SpawnTable;
import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Decoy;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.templates.L2PcTemplate;
import com.l2jmobius.gameserver.model.effects.AbstractEffect;
import com.l2jmobius.gameserver.model.effects.L2EffectType;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.model.skills.EffectScope;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.skills.targets.L2TargetType;
import com.l2jmobius.gameserver.network.serverpackets.*;
import com.vert.fakeplayer.FakePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author vert
 */
public abstract class FakePlayerAI {
    protected final FakePlayer _fakePlayer;
    protected volatile boolean _clientMoving;
    protected volatile boolean _clientAutoAttacking;
    private long _moveToPawnTimeout;
    protected int _clientMovingToPawnOffset;
    protected boolean _isBusyThinking = false;
    protected int iterationsOnDeath = 0;
    private final int toVillageIterationsOnDeath = 10;

    public FakePlayerAI(FakePlayer character)
    {
        _fakePlayer = character;
        setup();
        applyDefaultBuffs();
    }

    public void setup() {
        _fakePlayer.setIsRunning(true);
    }

    protected void applyDefaultBuffs() {
        for(int[] buff : getBuffs()){
            try {
//                Map<Integer, L2EffectType> activeEffects = Arrays.stream(_fakePlayer.getEffectList().getEffects())
//                        .filter(x-> x.getEffectType() == L2EffectType.BUFF)
//                        .collect(Collectors.toMap(x-> x.getSkill().getId(), x -> x));
//
//                if(!activeEffects.containsKey(buff[0]))
//                    SkillTable.getInstance().getInfo(buff[0], buff[1]).getEffects(_fakePlayer, _fakePlayer);
//                else {
//                    if((activeEffects.get(buff[0]).getPeriod() - activeEffects.get(buff[0]).getTime()) <= 20) {
//                        SkillTable.getInstance().getInfo(buff[0], buff[1]).getEffects(_fakePlayer, _fakePlayer);
//                    }
//                }

//                List <BuffInfo> activeEffects = _fakePlayer.getEffectList().getEffects();

//
//                Skill skill = SkillData.getInstance().getSkill(buff[0], buff[1]);
//                skill.applyEffects(_fakePlayer, _fakePlayer);
//
//                BuffInfo newBuff = new BuffInfo(_fakePlayer, _fakePlayer, skill);
//

                List <BuffInfo> buffs = _fakePlayer.getEffectList().getEffects();
                ArrayList <Integer> buffsIds = new ArrayList<>();

                buffs.forEach(item -> buffsIds.add(item.getSkill().getId()));

                boolean canAddBuff = !buffsIds.contains(buff[0]);

                if (canAddBuff) {
                    System.out.println("Buff ID: " + buff[0] + " | Added");

                    Skill skill2 = SkillData.getInstance().getSkill(buff[0], buff[1]);

                    final BuffInfo info2 = new BuffInfo(_fakePlayer, _fakePlayer, skill2);

                    skill2.applyEffectScope(EffectScope.SELF, info2, false, true);
                    _fakePlayer.getEffectList().add(info2);

                    skill2.applyEffects(_fakePlayer, _fakePlayer);
                }

//                buffsIds.forEach(buffId -> {
//                    if (buffId != buff[0]) {
//                        Skill skill2 = SkillData.getInstance().getSkill(buff[0], buff[1]);
//
//                        final BuffInfo info2 = new BuffInfo(_fakePlayer, _fakePlayer, skill2);
//
//                        skill2.applyEffectScope(EffectScope.SELF, info2, false, true);
//                        _fakePlayer.getEffectList().add(info2);
//
//                        skill2.applyEffects(_fakePlayer, _fakePlayer);
//
////                _fakePlayer.getEffectList().add(newBuff);
//                    }
//                });

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void handleDeath() {
        if(_fakePlayer.isDead()) {
            if(iterationsOnDeath >= toVillageIterationsOnDeath) {
                toVillageOnDeath();
            }
            iterationsOnDeath++;
            return;
        }

        iterationsOnDeath = 0;
    }

    public void setBusyThinking(boolean thinking) {
        _isBusyThinking = thinking;
    }

    public boolean isBusyThinking() {
        return _isBusyThinking;
    }

    protected void teleportToLocation(int x, int y, int z, int randomOffset) {
        _fakePlayer.stopMove(null);
        _fakePlayer.abortAttack();
        _fakePlayer.abortCast();
        _fakePlayer.setIsTeleporting(true);
        _fakePlayer.setTarget(null);
        _fakePlayer.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
        if (randomOffset > 0)
        {
            x += Rnd.get(-randomOffset, randomOffset);
            y += Rnd.get(-randomOffset, randomOffset);
        }
        z += 5;
        _fakePlayer.broadcastPacket(new TeleportToLocation(_fakePlayer, x, y, z, _fakePlayer.getHeading()));
        _fakePlayer.decayMe();
        _fakePlayer.setXYZ(x, y, z);
        _fakePlayer.onTeleported();
        _fakePlayer.revalidateZone(true);
    }

    protected void tryTargetRandomCreatureByTypeInRadius(Class<? extends L2Object> creatureClass, int radius)
    {
        if(_fakePlayer.getTarget() == null) {
            List<L2Object> targets = _fakePlayer.getWorldRegion().getVisibleObjects().values().stream().filter(x-> x.getInstanceType().isType(InstanceType.L2MonsterInstance) || x.getInstanceType().isType(InstanceType.L2Decoy)).collect(Collectors.toList());
            if(!targets.isEmpty()) {
                L2Object target = targets.get(Rnd.get(0, targets.size() -1 ));
                _fakePlayer.setTarget(target);
            }
        }else {
            if(_fakePlayer.getTarget().getInstanceType().isType(InstanceType.L2MonsterInstance) && ((L2MonsterInstance)_fakePlayer.getTarget()).isDead() || _fakePlayer.getTarget().getInstanceType().isType(InstanceType.L2Decoy) && ((L2Decoy)_fakePlayer.getTarget()).isDead()) {
                _fakePlayer.setTarget(null);
            }
        }
    }

    public void castSpell(Skill skill) {
        if(!_fakePlayer.isCastingNow()) {
            if (skill.getTargetType() == L2TargetType.GROUND)
            {
                if (maybeMoveToPosition((_fakePlayer).getCurrentSkillWorldPosition(), skill.getCastRange()))
                {
                    _fakePlayer.setIsCastingNow(false);
                    return;
                }
            }
            else
            {
                if (checkTargetLost(_fakePlayer.getTarget()))
                {
                    if (!skill.isPassive() && _fakePlayer.getTarget() != null)
                        _fakePlayer.setTarget(null);

                    _fakePlayer.setIsCastingNow(false);
                    return;
                }

                if (_fakePlayer.getTarget() != null)
                {
                    if(maybeMoveToPawn(_fakePlayer.getLockedTarget(), skill.getCastRange())) {
                        return;
                    }
                }

                if (_fakePlayer.isSkillDisabled(skill)) {
                    return;
                }
            }

            if (skill.getHitTime() > 50 && !skill.isSimultaneousCast()) {
                clientStopMoving(null);
            }

            _fakePlayer.doCast(skill);
        }else {
            _fakePlayer.forceAutoAttack(_fakePlayer.getTarget());
        }
    }

    protected void castSelfSpell(Skill skill) {
        if(!_fakePlayer.isCastingNow() && !_fakePlayer.isSkillDisabled(skill)) {


            if (skill.getHitTime() > 50 && !skill.isSimultaneousCast())
                clientStopMoving(null);

            _fakePlayer.doCast(skill);
        }
    }

    protected void toVillageOnDeath() {
//        Location location = MapRegionData.getInstance().getLocationToTeleport(_fakePlayer, TeleportType.TOWN);
//
//        if (_fakePlayer.isDead())
//            _fakePlayer.doRevive();
//
//        _fakePlayer.getFakeAi().teleportToLocation(location.getX(), location.getY(), location.getZ(), 20);
    }

    protected void clientStopMoving(Location loc)
    {
        if (_fakePlayer.isMoving())
            _fakePlayer.stopMove(loc);

        _clientMovingToPawnOffset = 0;

        if (_clientMoving || loc != null)
        {
            _clientMoving = false;

            _fakePlayer.broadcastPacket(new StopMove(_fakePlayer));

            if (loc != null)
                _fakePlayer.broadcastPacket(new StopRotation(_fakePlayer.getObjectId(), loc.getHeading(), 0));
        }
    }

    protected boolean checkTargetLost(L2Object target)
    {
        if (target == null)
        {
            _fakePlayer.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
            return true;
        }
        return false;
    }

    protected boolean maybeMoveToPosition(Location worldPosition, int offset)
    {
        if (worldPosition == null)
        {
            return false;
        }

        if (offset < 0)
            return false;

        if (!_fakePlayer.isInsideRadius3D(worldPosition.getX(), worldPosition.getY(), (int) (offset + _fakePlayer.getCollisionRadius()), offset))
        {
            if (_fakePlayer.isMovementDisabled())
                return true;

            int x = _fakePlayer.getX();
            int y = _fakePlayer.getY();

            double dx = worldPosition.getX() - x;
            double dy = worldPosition.getY() - y;

            double dist = Math.sqrt(dx * dx + dy * dy);

            double sin = dy / dist;
            double cos = dx / dist;

            dist -= offset - 5;

            x += (int) (dist * cos);
            y += (int) (dist * sin);

            moveTo(x, y, worldPosition.getZ());
            return true;
        }

        return false;
    }

    protected void moveToPawn(L2Object pawn, int offset)
    {
        if (!_fakePlayer.isMovementDisabled())
        {
            if (offset < 10)
                offset = 10;

            boolean sendPacket = true;
            if (_clientMoving && (_fakePlayer.getTarget() == pawn))
            {
                if (_clientMovingToPawnOffset == offset)
                {
                    if (System.currentTimeMillis() < _moveToPawnTimeout)
                        return;

                    sendPacket = false;
                }
                else if (_fakePlayer.isOnGeodataPath())
                {
                    if (System.currentTimeMillis() < _moveToPawnTimeout + 1000)
                        return;
                }
            }

            _clientMoving = true;
            _clientMovingToPawnOffset = offset;
            _fakePlayer.setTarget(pawn);
            _moveToPawnTimeout = System.currentTimeMillis() + 1000;

            if (pawn == null)
                return;

            _fakePlayer.moveToLocation(pawn.getX(), pawn.getY(), pawn.getZ(), offset);

            if (!_fakePlayer.isMoving())
            {
                return;
            }

            if (pawn instanceof L2Decoy)
            {
                if (_fakePlayer.isOnGeodataPath())
                {
                    _fakePlayer.broadcastPacket(new MoveToLocation(_fakePlayer));
                    _clientMovingToPawnOffset = 0;
                }
                else if (sendPacket)
                    _fakePlayer.broadcastPacket(new MoveToPawn(_fakePlayer, pawn, offset));
            }
            else
                _fakePlayer.broadcastPacket(new MoveToLocation(_fakePlayer));
        }
    }

    public void moveTo(int x, int y, int z)	{

        if (!_fakePlayer.isMovementDisabled())
        {
            _clientMoving = true;
            _clientMovingToPawnOffset = 0;
            _fakePlayer.moveToLocation(x, y, z, 0);

            _fakePlayer.broadcastPacket(new MoveToLocation(_fakePlayer));

        }
    }

    protected boolean maybeMoveToPawn(L2Character target, int offset) {

        if (target == null || offset < 0) {
            return false;
        }

        offset += _fakePlayer.getCollisionRadius();

        if (!_fakePlayer.isInsideRadius3D(target.getX(), target.getY(), target.getZ(), offset))
        {
            if (_fakePlayer.isMovementDisabled())
            {
                if (_fakePlayer.getAI().getIntention() == CtrlIntention.AI_INTENTION_ATTACK)
                    _fakePlayer.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
                return true;
            }

            if ((target instanceof L2Decoy || target instanceof L2MonsterInstance) && !(target instanceof L2DoorInstance))
            {
                if (target.isMoving()) {
                    offset -= 30;
                }

                if (offset < 5) {
                    offset = 5;
                }
            }

            moveToPawn(target, offset);

            return true;
        }

        if(!GeoEngine.getInstance().canSeeTarget(_fakePlayer, _fakePlayer.getTarget())){
            _fakePlayer.setIsCastingNow(false);
            moveToPawn(target, 50);
            return true;
        }

        return false;
    }

    public abstract void thinkAndAct();
    protected abstract int[][] getBuffs();
}

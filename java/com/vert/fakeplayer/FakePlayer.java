package com.vert.fakeplayer;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.AdminData;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.instancemanager.CursedWeaponsManager;
import com.l2jmobius.gameserver.model.*;
import com.l2jmobius.gameserver.model.actor.L2Decoy;
import com.l2jmobius.gameserver.model.actor.L2Playable;
import com.l2jmobius.gameserver.model.actor.appearance.PcAppearance;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.templates.L2PcTemplate;
import com.l2jmobius.gameserver.model.olympiad.OlympiadManager;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.skills.targets.L2TargetType;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import com.l2jmobius.gameserver.network.serverpackets.PledgeShowMemberListUpdate;

import com.vert.fakeplayer.ai.FakePlayerAI;
import com.vert.fakeplayer.helpers.FakeHelpers;

import java.util.logging.Level;

import static com.vert.fakeplayer.FakePlayerNameManager._log;

public class FakePlayer extends L2PcInstance {
    private FakePlayerAI _fakeAi;
    private boolean _underControl = false;

    public boolean isUnderControl() {
        return _underControl;
    }

    public void setUnderControl(boolean underControl) {
        _underControl = underControl;
    }

     public FakePlayer(int objectId, L2PcTemplate template, String accountName, PcAppearance app)
    {
        super(objectId, template, accountName, app);
    }

    public FakePlayerAI getFakeAi()
    {
        return _fakeAi;
    }

    public void assignDefaultAI() {
        try {
            setFakeAi(FakeHelpers.getAIbyClassId(getClassId()).getConstructor(FakePlayer.class).newInstance(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFakeAi(FakePlayerAI _fakeAi)
    {
        this._fakeAi = _fakeAi;
    }

    public boolean checkUseMagicConditions(Skill skill, boolean forceUse, boolean dontMove)
    {
        if(skill == null)
            return false;

        if (isDead() || isOutOfControl())
        {
            sendPacket(ActionFailed.STATIC_PACKET);
            return false;
        }

        if(isSkillDisabled(skill))
            return false;

//        AbnormalType skillType = skill.getAbnormalType();
//
//        if (isFishing() && (skillType != AbnormalType.PUMPING && sklType != AbnormalType.REELING && sklType != AbnormalType.FISHING))
//        {
//            return false;
//        }

        if (isInObserverMode())
        {
            abortCast();
            return false;
        }

        if (isSitting())
        {
            if (skill.isToggle())
            {
//                L2Effect effect = getFirstEffect(skill.getId());
//                if (effect != null)
//                {
//                    effect.exit();
//                    return false;
//                }
            }
            return false;
        }

        if (skill.isToggle())
        {
//            L2Effect effect = getFirstEffect(skill.getId());
//
//            if (effect != null)
//            {
//                if (skill.getId() != 60)
//                    effect.exit();
//
//                sendPacket(ActionFailed.STATIC_PACKET);
//                return false;
//            }
        }

        if (isFakeDeath())
        {
            sendPacket(ActionFailed.STATIC_PACKET);
            return false;
        }

        L2Object target = null;
        L2TargetType skillTargetType = skill.getTargetType();
        Location worldPosition = getCurrentSkillWorldPosition();

        if (skillTargetType == L2TargetType.GROUND && worldPosition == null)
        {
            _log.info("WorldPosition is null for skill: " + skill.getName() + ", player: " + getName() + ".");
            sendPacket(ActionFailed.STATIC_PACKET);
            return false;
        }

        switch (skillTargetType)
        {
            // Target the player if skill type is AURA, PARTY, CLAN or SELF
            case AURA:
            case FRONT_AURA:
            case BEHIND_AURA:
            case UNDEAD:
            case TARGET_PARTY:
            case CLAN:
            case GROUND:
            case SELF:
            case CORPSE_CLAN:
            case AREA_SUMMON:
                target = this;
                break;
            case PET:
            case SUMMON:
                target = getSummon();
                break;
            default:
                target = getTarget();
                break;
        }

        // Check the validity of the target
        if (target == null)
        {
            sendPacket(ActionFailed.STATIC_PACKET);
            return false;
        }

        if (target instanceof L2DoorInstance)
        {
//            if (!((L2DoorInstance) target).isAutoAttackable(this) // Siege doors only hittable during siege
//                    || (((L2DoorInstance) target).isUnlockable() && skill.getSkillType() != L2SkillType.UNLOCK)) // unlockable doors
//            {
//                sendPacket(SystemMessageId.INCORRECT_TARGET);
//                sendPacket(ActionFailed.STATIC_PACKET);
//                return false;
//            }
        }

        // Are the target and the player in the same duel?
        if (isInDuel())
        {
            if (target instanceof L2Playable)
            {
                // Get Player
                L2PcInstance character = target.getActingPlayer();
                if (character.getDuelId() != getDuelId())
                {
                    sendPacket(SystemMessageId.INVALID_TARGET);
                    sendPacket(ActionFailed.STATIC_PACKET);
                    return false;
                }
            }
        }

        // ************************************* Check skill availability *******************************************

        // Siege summon checks. Both checks send a message to the player if it return false.
//        if (skill.isSiegeSummonSkill())
//        {
//            final Siege siege = CastleManager.getInstance().getActiveSiege(this);
//            if (siege == null || !siege.checkSide(getClan(), SiegeSide.ATTACKER) || (isInSiege() && isInsideZone(ZoneId.CASTLE)))
//            {
//                sendPacket(SystemMessage.getSystemMessage(SystemMessageId.NOT_CALL_PET_FROM_THIS_LOCATION));
//                return false;
//            }
//
//            if (SevenSigns.getInstance().isSealValidationPeriod() && SevenSigns.getInstance().getSealOwner(SealType.STRIFE) == CabalType.DAWN && SevenSigns.getInstance().getPlayerCabal(getObjectId()) == CabalType.DUSK)
//            {
//                sendPacket(SystemMessageId.SEAL_OF_STRIFE_FORBIDS_SUMMONING);
//                return false;
//            }
//        }

        // ************************************* Check casting conditions *******************************************

        // Check if all casting conditions are completed
        if (!skill.checkCondition(this, target, false))
        {
            // Send ActionFailed to the Player
            sendPacket(ActionFailed.STATIC_PACKET);
            return false;
        }

        // ************************************* Check Skill Type *******************************************

        // @Todo: need check this validation // vert
        // Check if this is offensive magic skill
        if (!skill.getAbnormalType().isNone())
        {
            if (isInsidePeaceZone(this, target))
            {
                // If Creature or target is in a peace zone, send a system message A_MALICIOUS_SKILL_CANNOT_BE_USED_WHEN_AN_OPPONENT_IS_IN_THE_PEACE_ZONE ActionFailed
                sendPacket(SystemMessageId.A_MALICIOUS_SKILL_CANNOT_BE_USED_WHEN_AN_OPPONENT_IS_IN_THE_PEACE_ZONE);
                sendPacket(ActionFailed.STATIC_PACKET);
                return false;
            }

            if (isInOlympiadMode() && !isOlympiadStart())
            {
                // if Player is in Olympia and the match isn't already start, send ActionFailed
                sendPacket(ActionFailed.STATIC_PACKET);
                return false;
            }

            // Check if the target is attackable
            if (!target.isAttackable() && !getAccessLevel().allowPeaceAttack()) {
                // If target is not attackable, send ActionFailed
                sendPacket(ActionFailed.STATIC_PACKET);
                return false;
            }

            // Check if a Forced ATTACK is in progress on non-attackable target
            if (!target.isAutoAttackable(this) && !forceUse) {
                switch (skillTargetType) {
                    case AURA:
                    case FRONT_AURA:
                    case BEHIND_AURA:
                    case UNDEAD:
                    case CLAN:
                    case TARGET_PARTY:
                    case SELF:
                    case GROUND:
                    case CORPSE_CLAN:
                    case AREA_SUMMON:
                        break;
                    default:
                        // Send ActionFailed to the Player
                        sendPacket(ActionFailed.STATIC_PACKET);
                    return false;
                }
            }

            // Check if the target is in the skill cast range
            if (dontMove)
            {
                // Calculate the distance between the Player and the target
                if (skillTargetType == L2TargetType.GROUND)
                {
                    if (!isInsideRadius3D(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), (int) (skill.getCastRange() + getCollisionRadius())))
                    {
                        // Send a System Message to the caster
                        sendPacket(SystemMessageId.THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_STOPPED);

                        // Send ActionFailed to the Player
                        sendPacket(ActionFailed.STATIC_PACKET);
                        return false;
                    }
                }
                else if (skill.getCastRange() > 0 && !isInsideRadius3D(target.getX(), target.getY(), target.getZ(), (int) (skill.getCastRange() + getCollisionRadius())))
                {
                    // Send a System Message to the caster
                    sendPacket(SystemMessageId.THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_STOPPED);

                    // Send ActionFailed to the Player
                    sendPacket(ActionFailed.STATIC_PACKET);
                    return false;
                }
            }
        }

//        // Check if the skill is defensive
//        if (!skill.isOffensive() && target instanceof Monster && !forceUse)
//        {
//            // check if the target is a monster and if force attack is set.. if not then we don't want to cast.
//            switch (sklTargetType)
//            {
//                case TARGET_PET:
//                case TARGET_SUMMON:
//                case TARGET_AURA:
//                case TARGET_FRONT_AURA:
//                case TARGET_BEHIND_AURA:
//                case TARGET_AURA_UNDEAD:
//                case TARGET_CLAN:
//                case TARGET_SELF:
//                case TARGET_CORPSE_ALLY:
//                case TARGET_PARTY:
//                case TARGET_ALLY:
//                case TARGET_CORPSE_MOB:
//                case TARGET_AREA_CORPSE_MOB:
//                case TARGET_GROUND:
//                    break;
//                default:
//                {
//                    switch (sklType)
//                    {
//                        case BEAST_FEED:
//                        case DELUXE_KEY_UNLOCK:
//                        case UNLOCK:
//                            break;
//                        default:
//                            sendPacket(ActionFailed.STATIC_PACKET);
//                            return false;
//                    }
//                    break;
//                }
//            }
//        }

//        // Check if the skill is Spoil type and if the target isn't already spoiled
//        if (skillType == L2SkillType.SPOIL)
//        {
//            if (!(target instanceof Monster))
//            {
//                // Send a System Message to the Player
//                sendPacket(SystemMessageId.INCORRECT_TARGET);
//
//                // Send ActionFailed to the Player
//                sendPacket(ActionFailed.STATIC_PACKET);
//                return false;
//            }
//        }

//        // Check if the skill is Sweep type and if conditions not apply
//        if (sklType == L2SkillType.SWEEP && target instanceof Attackable)
//        {
//            if (((Attackable) target).isDead())
//            {
//                final int spoilerId = ((Attackable) target).getSpoilerId();
//                if (spoilerId == 0)
//                {
//                    // Send a System Message to the Player
//                    sendPacket(SystemMessageId.SWEEPER_FAILED_TARGET_NOT_SPOILED);
//
//                    // Send ActionFailed to the Player
//                    sendPacket(ActionFailed.STATIC_PACKET);
//                    return false;
//                }
//
//                if (!isLooterOrInLooterParty(spoilerId))
//                {
//                    // Send a System Message to the Player
//                    sendPacket(SystemMessageId.SWEEP_NOT_ALLOWED);
//
//                    // Send ActionFailed to the Player
//                    sendPacket(ActionFailed.STATIC_PACKET);
//                    return false;
//                }
//            }
//        }

//        // Check if the skill is Drain Soul (Soul Crystals) and if the target is a MOB
//        if (sklType == L2SkillType.DRAIN_SOUL)
//        {
//            if (!(target instanceof Monster))
//            {
//                // Send a System Message to the Player
//                sendPacket(SystemMessageId.INCORRECT_TARGET);
//
//                // Send ActionFailed to the Player
//                sendPacket(ActionFailed.STATIC_PACKET);
//                return false;
//            }
//        }

        // Check if this is a Pvp skill and target isn't a non-flagged/non-karma player
        switch (skillTargetType)
        {
            case PARTY:
            case CLAN: // For such skills, checkPvpSkill() is called from L2Skill.getTargetList()
            case AURA:
            case FRONT_AURA:
            case BEHIND_AURA:
            case AREA_UNDEAD:
            case GROUND:
            case SELF:
            case CORPSE_CLAN:
            case AREA_SUMMON:
                break;
            default:
                if (!checkPvpSkill(target, skill) && !getAccessLevel().allowPeaceAttack())
                {
                    // Send a System Message to the Player
                    sendPacket(SystemMessageId.INVALID_TARGET);

                    // Send ActionFailed to the Player
                    sendPacket(ActionFailed.STATIC_PACKET);
                    return false;
                }
        }

//        if ((skillTargetType == SkillTargetType.TARGET_HOLY && !checkIfOkToCastSealOfRule(CastleManager.getInstance().getCastle(this), false, skill, target)) || (sklType == L2SkillType.SIEGEFLAG && !L2SkillSiegeFlag.checkIfOkToPlaceFlag(this, false)) || (sklType == L2SkillType.STRSIEGEASSAULT && !checkIfOkToUseStriderSiegeAssault(skill)) || (sklType == L2SkillType.SUMMON_FRIEND && !(checkSummonerStatus(this) && checkSummonTargetStatus(target, this))))
//        {
//            sendPacket(ActionFailed.STATIC_PACKET);
//            abortCast();
//            return false;
//        }


        // finally, after passing all conditions
        return true;
    }

    public void forceAutoAttack(L2Object creature) {
        if(this.getTarget() == null) {
            return;
        }

        if (isInsidePeaceZone(this, this.getTarget())) {
            return;
        }

        if (isInOlympiadMode() && getTarget() != null && getTarget() instanceof L2Playable) {
            L2PcInstance target = getTarget().getActingPlayer();
            if (target == null || (target.isInOlympiadMode() && (!isOlympiadStart() || getOlympiadGameId() != target.getOlympiadGameId()))) {
                return;
            }
        }

        if (getTarget() != null && !getTarget().isAttackable() && !getAccessLevel().allowPeaceAttack()) {
            return;
        }

        if (isConfused()) {
            return;
        }

        // GeoData Los Check or dz > 1000
        if (!GeoEngine.getInstance().canSeeTarget(this,this.getTarget())) {
            return;
        }

        // Notify AI with ATTACK
        getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, this.getTarget());
    }

    public synchronized void despawnPlayer()
    {
        try
        {
            // Put the online status to false
            setOnlineStatus(false, true);

            // abort cast & attack and remove the target. Cancels movement aswell.
            abortAttack();
            abortCast();
            stopMove(null);
            setTarget(null);

            leaveParty();

//            if (isFlying()) {
//                removeSkill(FrequentSkill.WYVERN_BREATH.getSkill().getId(), false);
//            }

            // Stop all scheduled tasks
            stopAllTimers();

//            // Cancel the cast of eventual fusion skill users on this target.
//            for (L2Decoy character : getKnownType(L2Decoy.class))
//                if (character.getFusionSkill() != null && character.getFusionSkill().getTarget() == this)
//                    character.abortCast();

//            // Stop signets & toggles effects.
//            for (L2EffectType effect : getAllEffects())
//            {
//                if (effect.getSkill().isToggle())
//                {
//                    effect.exit();
//                    continue;
//                }
//
//                switch (effect.getEffectType())
//                {
//                    case SIGNET_GROUND:
//                    case SIGNET_EFFECT:
//                        effect.exit();
//                        break;
//                    default:
//                        break;
//                }
//            }

            // Remove the Player from the world
            decayMe();

            // If a party is in progress, leave it
            if (getParty() != null) {
                getParty().removePartyMember(this, L2Party.MessageType.DISCONNECTED);
            }

            // If the Player has Pet, unsummon it
            if (getSummon() != null)
                getSummon().unSummon(this);

            // Handle removal from olympiad game
            if (OlympiadManager.getInstance().isRegistered(this) || getOlympiadGameId() != -1)
                OlympiadManager.getInstance().removeDisconnectedCompetitor(this);

            // set the status for pledge member list to OFFLINE
            if (getClan() != null) {
                L2ClanMember clanMember = getClan().getClanMember(getObjectId());
                if (clanMember != null) {
                    clanMember.setPlayerInstance(null);
                }
            }

            // deals with sudden exit in the middle of transaction
            if (getActiveRequester() != null)
            {
                setActiveRequester(null);
                cancelActiveTrade();
            }

            // If the Player is a GM, remove it from the GM List
            if (isGM()) {
                AdminData.getInstance().deleteGm(this);
            }

            // Check if the Player is in observer mode to set its position to its position before entering in observer mode
            if (isInObserverMode()) {
                setXYZInvisible(getLastLocation().getX(), getLastLocation().getY(), getLastLocation().getZ());
            }

            // Oust player from boat
            if (getVehicle() != null) {
                getVehicle().oustPlayer(this);
            }

            // Update inventory and remove them from the world
            getInventory().deleteMe();

            // Update warehouse and remove them from the world
            clearWarehouse();

            // Update freight and remove them from the world
            _freight.deleteMe();

            if (isCursedWeaponEquipped()) {
                CursedWeaponsManager.getInstance().getCursedWeapon(getCursedWeaponEquippedId()).setPlayer(null);
            }

            if (getClanId() > 0)
                getClan().broadcastToOtherOnlineMembers(new PledgeShowMemberListUpdate(this), this);

//            if (isSeated()) {
//                final WorldObject object = World.getInstance().getObject(_throneId);
//                if (object instanceof StaticObject)
//                    ((StaticObject) object).setBusy(false);
//            }

            L2World.getInstance().removeObject(this); // force remove in case of crash during teleport

            // friends & blocklist update
            notifyFriends();
            getBlockList().playerLogout();
        }
        catch (Exception e)
        {
            _log.log(Level.WARNING, "Exception on deleteMe()" + e.getMessage(), e);
        }
    }

    public void heal() {
        setCurrentCp(getMaxCp());
        setCurrentHp(getMaxHp());
        setCurrentMp(getMaxMp());
    }
}

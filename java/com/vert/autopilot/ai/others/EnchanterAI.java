package com.vert.autopilot.ai.others;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.model.items.L2Weapon;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.FakePlayerAI;
import com.vert.autopilot.helpers.FakeHelpers;

/**
 * @author vert
 */
public class EnchanterAI extends FakePlayerAI {
    private int _enchantIterations = 0;
    private int _maxEnchant = 25; // need get server max enchant here
    private final int iterationsForAction = Rnd.get(3,5);

    public EnchanterAI(FakePlayer character) {
        super(character);
    }

    @Override
    public void setup() {
        super.setup();
        L2ItemInstance weapon = _fakePlayer.getActiveWeaponInstance();
        weapon = checkIfWeaponIsExistsEquipped(weapon);
        weapon.setEnchantLevel(0);
        _fakePlayer.broadcastCharInfo();

    }

    @Override
    public void thinkAndAct() {

        handleDeath();
        setBusyThinking(true);
        if(_enchantIterations % iterationsForAction == 0) {
            L2ItemInstance weapon = _fakePlayer.getActiveWeaponInstance();
            weapon = checkIfWeaponIsExistsEquipped(weapon);
            double chance = getSuccessChance(weapon);

            int currentEnchantLevel = weapon.getEnchantLevel();
            if(currentEnchantLevel < _maxEnchant || serverHasUnlimitedMax()) {
                if (Rnd.nextDouble() < chance || weapon.getEnchantLevel() < 4) {
                    weapon.setEnchantLevel(currentEnchantLevel + 1);
                    _fakePlayer.broadcastCharInfo();
                } else {
                    destroyFailedItem(weapon);
                }
            }
        }
        _enchantIterations++;
        setBusyThinking(false);
    }

    private void destroyFailedItem(L2ItemInstance weapon) {
        _fakePlayer.getInventory().destroyItem("Enchant", weapon, _fakePlayer, null);
        _fakePlayer.broadcastUserInfo();
        _fakePlayer.setActiveEnchantItemId(-1);
    }

    private double getSuccessChance(L2ItemInstance weapon) {
        double chance = 0d;
        if (((L2Weapon) weapon.getItem()).isMagicWeapon())
//            chance = (weapon.getEnchantLevel() > 14) ? Config.ENCHANT_CHANCE_WEAPON_MAGIC_15PLUS : Config.ENCHANT_CHANCE_WEAPON_MAGIC;
            chance = (weapon.getEnchantLevel() > 14) ? 15: 10;
        else
//            chance = (weapon.getEnchantLevel() > 14) ? Config.ENCHANT_CHANCE_WEAPON_NONMAGIC_15PLUS : Config.ENCHANT_CHANCE_WEAPON_NONMAGIC;
            chance = (weapon.getEnchantLevel() > 14) ? 15 : 10;
        return chance;
    }

    private boolean serverHasUnlimitedMax() {
        return _maxEnchant == 0;
    }

    private L2ItemInstance checkIfWeaponIsExistsEquipped(L2ItemInstance weapon) {
        if(weapon == null) {
            FakeHelpers.giveWeaponsByClass(_fakePlayer, false);
            weapon = _fakePlayer.getActiveWeaponInstance();
        }
        return weapon;
    }

    @Override
    protected int[][] getBuffs() {
        return new int[0][0];
    }
}

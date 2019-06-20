package com.vert.autopilot.ai.interfaces;

import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.vert.autopilot.FakePlayer;

/**
 * @author vert
 */
public interface IConsumableSpender {
    default void handleConsumable(FakePlayer fakePlayer, int consumableId) {
        if (fakePlayer.getInventory().getItemByItemId(consumableId) != null) {
            if(fakePlayer.getInventory().getItemByItemId(consumableId).getCount() <= 20) {
                fakePlayer.getInventory().addItem("", consumableId, 500, fakePlayer, null);

            }
        } else {
            fakePlayer.getInventory().addItem("", consumableId, 500, fakePlayer, null);
            L2ItemInstance consumable = fakePlayer.getInventory().getItemByItemId(consumableId);
            if(consumable.isEquipable())
                fakePlayer.getInventory().equipItem(consumable);
        }
    }
}

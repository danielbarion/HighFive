/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.effecthandlers;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.instance.L2ChestInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.conditions.Condition;
import com.l2jmobius.gameserver.model.effects.AbstractEffect;
import com.l2jmobius.gameserver.model.skills.BuffInfo;

/**
 * Open Chest effect implementation.
 * @author Adry_85
 */
public final class OpenChest extends AbstractEffect
{
	public OpenChest(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
	}
	
	@Override
	public boolean isInstant()
	{
		return true;
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		if (!(info.getEffected() instanceof L2ChestInstance))
		{
			return;
		}
		
		final L2PcInstance player = info.getEffector().getActingPlayer();
		final L2ChestInstance chest = (L2ChestInstance) info.getEffected();
		if (chest.isDead() || (player.getInstanceId() != chest.getInstanceId()))
		{
			return;
		}
		
		if (((player.getLevel() <= 77) && (Math.abs(chest.getLevel() - player.getLevel()) <= 6)) || ((player.getLevel() >= 78) && (Math.abs(chest.getLevel() - player.getLevel()) <= 5)))
		{
			player.broadcastSocialAction(3);
			chest.setSpecialDrop();
			chest.setMustRewardExpSp(false);
			chest.reduceCurrentHp(chest.getMaxHp(), player, info.getSkill());
		}
		else
		{
			player.broadcastSocialAction(13);
			chest.addDamageHate(player, 0, 1);
			chest.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
		}
	}
}

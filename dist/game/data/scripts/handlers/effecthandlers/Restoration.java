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

import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.conditions.Condition;
import com.l2jmobius.gameserver.model.effects.AbstractEffect;
import com.l2jmobius.gameserver.model.skills.BuffInfo;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.PetItemList;

/**
 * Restoration effect implementation.
 * @author Zoey76
 */
public final class Restoration extends AbstractEffect
{
	private final int _itemId;
	private final int _itemCount;
	
	public Restoration(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
		
		_itemId = params.getInt("itemId", 0);
		_itemCount = params.getInt("itemCount", 0);
	}
	
	@Override
	public boolean isInstant()
	{
		return true;
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		if ((info.getEffected() == null) || !info.getEffected().isPlayable())
		{
			return;
		}
		
		if ((_itemId <= 0) || (_itemCount <= 0))
		{
			info.getEffected().sendPacket(SystemMessageId.THERE_WAS_NOTHING_FOUND_INSIDE);
			LOGGER.warning(Restoration.class.getSimpleName() + " effect with wrong item Id/count: " + _itemId + "/" + _itemCount + "!");
			return;
		}
		
		if (info.getEffected().isPlayer())
		{
			info.getEffected().getActingPlayer().addItem("Skill", _itemId, _itemCount, info.getEffector(), true);
		}
		else if (info.getEffected().isPet())
		{
			info.getEffected().getInventory().addItem("Skill", _itemId, _itemCount, info.getEffected().getActingPlayer(), info.getEffector());
			info.getEffected().getActingPlayer().sendPacket(new PetItemList(info.getEffected().getInventory().getItems()));
		}
	}
}

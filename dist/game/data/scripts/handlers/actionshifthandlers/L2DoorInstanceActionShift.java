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
package handlers.actionshifthandlers;

import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.handler.IActionShiftHandler;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jmobius.gameserver.network.serverpackets.StaticObject;

public class L2DoorInstanceActionShift implements IActionShiftHandler
{
	@Override
	public boolean action(L2PcInstance activeChar, L2Object target, boolean interact)
	{
		if (activeChar.isGM())
		{
			activeChar.setTarget(target);
			final L2DoorInstance door = (L2DoorInstance) target;
			activeChar.sendPacket(new StaticObject(door, activeChar.isGM()));
			
			final NpcHtmlMessage html = new NpcHtmlMessage();
			html.setFile(activeChar, "data/html/admin/doorinfo.htm");
			html.replace("%class%", target.getClass().getSimpleName());
			html.replace("%hp%", String.valueOf((int) door.getCurrentHp()));
			html.replace("%hpmax%", String.valueOf(door.getMaxHp()));
			html.replace("%objid%", String.valueOf(target.getObjectId()));
			html.replace("%doorid%", String.valueOf(door.getId()));
			
			html.replace("%minx%", String.valueOf(door.getX(0)));
			html.replace("%miny%", String.valueOf(door.getY(0)));
			html.replace("%minz%", String.valueOf(door.getZMin()));
			
			html.replace("%maxx%", String.valueOf(door.getX(2)));
			html.replace("%maxy%", String.valueOf(door.getY(2)));
			html.replace("%maxz%", String.valueOf(door.getZMax()));
			html.replace("%unlock%", door.isOpenableBySkill() ? "<font color=00FF00>YES<font>" : "<font color=FF0000>NO</font>");
			
			activeChar.sendPacket(html);
		}
		return true;
	}
	
	@Override
	public InstanceType getInstanceType()
	{
		return InstanceType.L2DoorInstance;
	}
}
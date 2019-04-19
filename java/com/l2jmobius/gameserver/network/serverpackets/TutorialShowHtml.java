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
package com.l2jmobius.gameserver.network.serverpackets;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.enums.HtmlActionScope;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * TutorialShowHtml server packet implementation.
 * @author HorridoJoho
 */
public final class TutorialShowHtml extends AbstractHtmlPacket
{
	public TutorialShowHtml(String html)
	{
		super(html);
	}
	
	/**
	 * This constructor is just here to be able to show a tutorial html<br>
	 * window bound to an npc.
	 * @param npcObjId
	 * @param html
	 */
	public TutorialShowHtml(int npcObjId, String html)
	{
		super(npcObjId, html);
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.TUTORIAL_SHOW_HTML.writeId(packet);
		packet.writeS(getHtml());
		return true;
	}
	
	@Override
	public HtmlActionScope getScope()
	{
		return HtmlActionScope.TUTORIAL_HTML;
	}
}
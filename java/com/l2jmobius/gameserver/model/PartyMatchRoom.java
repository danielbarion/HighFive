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
package com.l2jmobius.gameserver.model;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.gameserver.instancemanager.MapRegionManager;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.interfaces.IIdentifiable;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ExManagePartyRoomMember;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

/**
 * @author Gnacik
 */
public class PartyMatchRoom implements IIdentifiable
{
	private final int _id;
	private String _title;
	private int _loot;
	private int _minlvl;
	private int _maxlvl;
	private int _maxmem;
	private final List<L2PcInstance> _members = new ArrayList<>();
	
	public PartyMatchRoom(int id, String title, int loot, int minlvl, int maxlvl, int maxmem, L2PcInstance owner)
	{
		_id = id;
		_title = title;
		_loot = loot;
		_minlvl = minlvl;
		_maxlvl = maxlvl;
		_maxmem = maxmem;
		_members.add(owner);
	}
	
	public List<L2PcInstance> getPartyMembers()
	{
		return _members;
	}
	
	public void addMember(L2PcInstance player)
	{
		_members.add(player);
	}
	
	public void deleteMember(L2PcInstance player)
	{
		if (player != getOwner())
		{
			_members.remove(player);
			notifyMembersAboutExit(player);
		}
		else if (_members.size() == 1)
		{
			PartyMatchRoomList.getInstance().deleteRoom(_id);
		}
		else
		{
			changeLeader(_members.get(1));
			deleteMember(player);
		}
	}
	
	public void notifyMembersAboutExit(L2PcInstance player)
	{
		for (L2PcInstance _member : _members)
		{
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_LEFT_THE_PARTY_ROOM);
			sm.addString(player.getName());
			_member.sendPacket(sm);
			_member.sendPacket(new ExManagePartyRoomMember(player, this, 2));
		}
	}
	
	public void changeLeader(L2PcInstance newLeader)
	{
		// Get current leader
		final L2PcInstance oldLeader = _members.get(0);
		// Remove new leader
		_members.remove(newLeader);
		// Move him to first position
		_members.set(0, newLeader);
		// Add old leader as normal member
		_members.add(oldLeader);
		// Broadcast change
		for (L2PcInstance member : _members)
		{
			member.sendPacket(new ExManagePartyRoomMember(newLeader, this, 1));
			member.sendPacket(new ExManagePartyRoomMember(oldLeader, this, 1));
			member.sendPacket(SystemMessageId.THE_LEADER_OF_THE_PARTY_ROOM_HAS_CHANGED);
		}
	}
	
	@Override
	public int getId()
	{
		return _id;
	}
	
	public int getLootType()
	{
		return _loot;
	}
	
	public int getMinLvl()
	{
		return _minlvl;
	}
	
	public int getMaxLvl()
	{
		return _maxlvl;
	}
	
	/**
	 * <ul>
	 * <li>1 : Talking Island</li>
	 * <li>2 : Gludio</li>
	 * <li>3 : Dark Elven Ter.</li>
	 * <li>4 : Elven Territory</li>
	 * <li>5 : Dion</li>
	 * <li>6 : Giran</li>
	 * <li>7 : Neutral Zone</li>
	 * <li>8 : Lyonn</li>
	 * <li>9 : Schuttgart</li>
	 * <li>10 : Oren</li>
	 * <li>11 : Hunters Village</li>
	 * <li>12 : Innadril</li>
	 * <li>13 : Aden</li>
	 * <li>14 : Rune</li>
	 * <li>15 : Goddard</li>
	 * </ul>
	 * @return the id
	 */
	public int getLocation()
	{
		return MapRegionManager.getInstance().getMapRegion(_members.get(0)).getBbs();
	}
	
	public int getMembers()
	{
		return _members.size();
	}
	
	public int getMaxMembers()
	{
		return _maxmem;
	}
	
	public String getTitle()
	{
		return _title;
	}
	
	public L2PcInstance getOwner()
	{
		return _members.get(0);
	}
	
	/* SET */
	
	public void setMinLvl(int minlvl)
	{
		_minlvl = minlvl;
	}
	
	public void setMaxLvl(int maxlvl)
	{
		_maxlvl = maxlvl;
	}
	
	public void setLootType(int loot)
	{
		_loot = loot;
	}
	
	public void setMaxMembers(int maxmem)
	{
		_maxmem = maxmem;
	}
	
	public void setTitle(String title)
	{
		_title = title;
	}
}
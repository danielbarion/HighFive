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
import com.l2jmobius.gameserver.data.sql.impl.ClanTable;
import com.l2jmobius.gameserver.data.xml.impl.FakePlayerData;
import com.l2jmobius.gameserver.enums.Sex;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.holders.FakePlayerHolder;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author Mobius
 */
public class FakePlayerInfo implements IClientOutgoingPacket
{
	private final L2Npc _npc;
	private final int _objId;
	private final int _x;
	private final int _y;
	private final int _z;
	private final int _heading;
	private final int _mAtkSpd;
	private final int _pAtkSpd;
	private final int _runSpd;
	private final int _walkSpd;
	private final int _swimRunSpd;
	private final int _swimWalkSpd;
	private final int _flyRunSpd;
	private final int _flyWalkSpd;
	private final double _moveMultiplier;
	private final float _attackSpeedMultiplier;
	private final FakePlayerHolder _fpcHolder;
	private final L2Clan _clan;
	
	public FakePlayerInfo(L2Npc npc)
	{
		_npc = npc;
		_objId = npc.getObjectId();
		_x = npc.getX();
		_y = npc.getY();
		_z = npc.getZ();
		_heading = npc.getHeading();
		_mAtkSpd = npc.getMAtkSpd();
		_pAtkSpd = (int) npc.getPAtkSpd();
		_attackSpeedMultiplier = npc.getAttackSpeedMultiplier();
		_moveMultiplier = npc.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(npc.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(npc.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(npc.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(npc.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = npc.isFlying() ? _runSpd : 0;
		_flyWalkSpd = npc.isFlying() ? _walkSpd : 0;
		_fpcHolder = FakePlayerData.getInstance().getInfo(npc.getId());
		_clan = ClanTable.getInstance().getClan(_fpcHolder.getClanId());
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.CHAR_INFO.writeId(packet);
		packet.writeD(_x);
		packet.writeD(_y);
		packet.writeD(_z);
		packet.writeD(0x00); // vehicleId
		packet.writeD(_objId);
		packet.writeS(_npc.getName());
		
		packet.writeD(_npc.getRace().ordinal());
		packet.writeD(_npc.getTemplate().getSex() == Sex.FEMALE ? 0x01 : 0x00);
		packet.writeD(_fpcHolder.getClassId());
		
		packet.writeD(0x00); // Inventory.PAPERDOLL_UNDER
		packet.writeD(_fpcHolder.getEquipHead());
		packet.writeD(_fpcHolder.getEquipRHand());
		packet.writeD(_fpcHolder.getEquipLHand());
		packet.writeD(_fpcHolder.getEquipGloves());
		packet.writeD(_fpcHolder.getEquipChest());
		packet.writeD(_fpcHolder.getEquipLegs());
		packet.writeD(_fpcHolder.getEquipFeet());
		packet.writeD(_fpcHolder.getEquipCloak());
		packet.writeD(_fpcHolder.getEquipRHand()); // dual hand
		packet.writeD(_fpcHolder.getEquipHair());
		packet.writeD(_fpcHolder.getEquipHair2());
		packet.writeD(0x00); // Inventory.PAPERDOLL_RBRACELET
		packet.writeD(0x00); // Inventory.PAPERDOLL_LBRACELET
		packet.writeD(0x00); // Inventory.PAPERDOLL_DECO1
		packet.writeD(0x00); // Inventory.PAPERDOLL_DECO2
		packet.writeD(0x00); // Inventory.PAPERDOLL_DECO3
		packet.writeD(0x00); // Inventory.PAPERDOLL_DECO4
		packet.writeD(0x00); // Inventory.PAPERDOLL_DECO5
		packet.writeD(0x00); // Inventory.PAPERDOLL_DECO6
		packet.writeD(0x00); // Inventory.PAPERDOLL_BELT
		
		for (int i = 0; i < 21; i++)
		{
			packet.writeD(0x00);
		}
		
		packet.writeD(0x00); // getTalismanSlots
		packet.writeD(0x01); // canEquipCloak
		
		packet.writeD(_npc.getScriptValue()); // getPvpFlag()
		packet.writeD(_npc.getKarma());
		
		packet.writeD(_mAtkSpd);
		packet.writeD(_pAtkSpd);
		
		packet.writeD(0x00); // ?
		
		packet.writeD(_runSpd);
		packet.writeD(_walkSpd);
		packet.writeD(_swimRunSpd);
		packet.writeD(_swimWalkSpd);
		packet.writeD(_flyRunSpd);
		packet.writeD(_flyWalkSpd);
		packet.writeD(_flyRunSpd);
		packet.writeD(_flyWalkSpd);
		packet.writeF(_moveMultiplier);
		packet.writeF(_attackSpeedMultiplier);
		
		packet.writeF(_npc.getCollisionRadius());
		packet.writeF(_npc.getCollisionHeight());
		
		packet.writeD(_fpcHolder.getHair());
		packet.writeD(_fpcHolder.getHairColor());
		packet.writeD(_fpcHolder.getFace());
		
		packet.writeS(_npc.getTemplate().getTitle());
		
		if (_clan != null)
		{
			packet.writeD(_clan.getId());
			packet.writeD(_clan.getCrestId());
			packet.writeD(_clan.getAllyId());
			packet.writeD(_clan.getAllyCrestId());
		}
		else
		{
			packet.writeD(0x00);
			packet.writeD(0x00);
			packet.writeD(0x00);
			packet.writeD(0x00);
		}
		
		packet.writeC(0x01); // isSitting() ? 0x00 : 0x01 (at some initial tests it worked)
		packet.writeC(_npc.isRunning() ? 0x01 : 0x00);
		packet.writeC(_npc.isInCombat() ? 0x01 : 0x00);
		
		packet.writeC(_npc.isAlikeDead() ? 0x01 : 0x00);
		
		packet.writeC(_npc.isInvisible() ? 0x01 : 0x00);
		
		packet.writeC(0x00); // 1-on Strider, 2-on Wyvern, 3-on Great Wolf, 0-no mount
		packet.writeC(0x00); // getPrivateStoreType().getId()
		
		packet.writeH(0x00); // getCubics().size()
		// getCubics().keySet().forEach(packet::writeH);
		
		packet.writeC(0x00); // isInPartyMatchRoom
		
		packet.writeD(_npc.getAbnormalVisualEffects());
		
		packet.writeC(_npc.isInsideZone(ZoneId.WATER) ? 1 : 0);
		
		packet.writeH(_fpcHolder.getRecommends()); // Blue value for name (0 = white, 255 = pure blue)
		packet.writeD(0x00); // getMountNpcId() == 0 ? 0 : getMountNpcId() + 1000000
		packet.writeD(_fpcHolder.getClassId());
		packet.writeD(0x00); // ?
		packet.writeC(_fpcHolder.getWeaponEnchantLevel()); // isMounted() ? 0 : _enchantLevel
		
		packet.writeC(_npc.getTeam().getId());
		
		packet.writeD(_clan != null ? _clan.getCrestLargeId() : 0x00);
		packet.writeC(_fpcHolder.getNobleLevel());
		packet.writeC(_fpcHolder.isHero() ? 0x01 : 0x00);
		
		packet.writeC(_fpcHolder.isFishing() ? 0x01 : 0x00);
		
		packet.writeD(_fpcHolder.getBaitLocationX());
		packet.writeD(_fpcHolder.getBaitLocationY());
		packet.writeD(_fpcHolder.getBaitLocationZ());
		
		packet.writeD(_fpcHolder.getNameColor());
		
		packet.writeD(_heading);
		
		packet.writeD(_fpcHolder.getPledgeStatus());
		packet.writeD(0x00); // getPledgeType()
		
		packet.writeD(_fpcHolder.getTitleColor());
		
		packet.writeD(0x00); // isCursedWeaponEquipped
		
		packet.writeD(0x00); // getClanId() > 0 ? getClan().getReputationScore() : 0
		
		// T1
		packet.writeD(0x00); // getTransformationDisplayId()
		packet.writeD(_fpcHolder.getAgathionId());
		
		// T2
		packet.writeD(0x01);
		
		// T2.3
		packet.writeD(_npc.getAbnormalVisualEffectSpecial());
		return true;
	}
}

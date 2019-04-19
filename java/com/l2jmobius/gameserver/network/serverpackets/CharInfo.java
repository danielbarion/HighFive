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

import com.l2jmobius.Config;
import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.instancemanager.CursedWeaponsManager;
import com.l2jmobius.gameserver.model.actor.L2Decoy;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.skills.AbnormalVisualEffect;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class CharInfo implements IClientOutgoingPacket
{
	private final L2PcInstance _activeChar;
	private int _objId;
	private int _x;
	private int _y;
	private int _z;
	private int _heading;
	private final int _mAtkSpd;
	private final int _pAtkSpd;
	private final int _runSpd;
	private final int _walkSpd;
	private final int _swimRunSpd;
	private final int _swimWalkSpd;
	private final int _flyRunSpd;
	private final int _flyWalkSpd;
	private final double _moveMultiplier;
	
	private int _vehicleId = 0;
	private final boolean _gmSeeInvis;
	
	private static final int[] PAPERDOLL_ORDER = new int[]
	{
		Inventory.PAPERDOLL_UNDER,
		Inventory.PAPERDOLL_HEAD,
		Inventory.PAPERDOLL_RHAND,
		Inventory.PAPERDOLL_LHAND,
		Inventory.PAPERDOLL_GLOVES,
		Inventory.PAPERDOLL_CHEST,
		Inventory.PAPERDOLL_LEGS,
		Inventory.PAPERDOLL_FEET,
		Inventory.PAPERDOLL_CLOAK,
		Inventory.PAPERDOLL_RHAND,
		Inventory.PAPERDOLL_HAIR,
		Inventory.PAPERDOLL_HAIR2,
		Inventory.PAPERDOLL_RBRACELET,
		Inventory.PAPERDOLL_LBRACELET,
		Inventory.PAPERDOLL_DECO1,
		Inventory.PAPERDOLL_DECO2,
		Inventory.PAPERDOLL_DECO3,
		Inventory.PAPERDOLL_DECO4,
		Inventory.PAPERDOLL_DECO5,
		Inventory.PAPERDOLL_DECO6,
		Inventory.PAPERDOLL_BELT
	};
	
	public CharInfo(L2PcInstance cha, boolean gmSeeInvis)
	{
		_activeChar = cha;
		_objId = cha.getObjectId();
		if ((_activeChar.getVehicle() != null) && (_activeChar.getInVehiclePosition() != null))
		{
			_x = _activeChar.getInVehiclePosition().getX();
			_y = _activeChar.getInVehiclePosition().getY();
			_z = _activeChar.getInVehiclePosition().getZ();
			_vehicleId = _activeChar.getVehicle().getObjectId();
		}
		else
		{
			_x = _activeChar.getX();
			_y = _activeChar.getY();
			_z = _activeChar.getZ();
		}
		_heading = _activeChar.getHeading();
		_mAtkSpd = _activeChar.getMAtkSpd();
		_pAtkSpd = (int) _activeChar.getPAtkSpd();
		
		_moveMultiplier = cha.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(cha.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(cha.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(cha.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(cha.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = cha.isFlying() ? _runSpd : 0;
		_flyWalkSpd = cha.isFlying() ? _walkSpd : 0;
		_gmSeeInvis = gmSeeInvis;
	}
	
	public CharInfo(L2Decoy decoy, boolean gmSeeInvis)
	{
		this(decoy.getActingPlayer(), gmSeeInvis); // init
		_objId = decoy.getObjectId();
		_x = decoy.getX();
		_y = decoy.getY();
		_z = decoy.getZ();
		_heading = decoy.getHeading();
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.CHAR_INFO.writeId(packet);
		packet.writeD(_x);
		packet.writeD(_y);
		packet.writeD(_z);
		packet.writeD(_vehicleId);
		packet.writeD(_objId);
		packet.writeS(_activeChar.getAppearance().getVisibleName());
		packet.writeD(_activeChar.getRace().ordinal());
		packet.writeD(_activeChar.getAppearance().getSex() ? 1 : 0);
		packet.writeD(_activeChar.getBaseClass());
		
		for (int slot : getPaperdollOrder())
		{
			packet.writeD(_activeChar.getInventory().getPaperdollItemDisplayId(slot));
		}
		
		for (int slot : getPaperdollOrder())
		{
			packet.writeD(_activeChar.getInventory().getPaperdollAugmentationId(slot));
		}
		
		packet.writeD(_activeChar.getInventory().getTalismanSlots());
		packet.writeD(_activeChar.getInventory().canEquipCloak() ? 1 : 0);
		
		packet.writeD(_activeChar.getPvpFlag());
		packet.writeD(_activeChar.getKarma());
		
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
		packet.writeF(_activeChar.getAttackSpeedMultiplier());
		
		packet.writeF(_activeChar.getCollisionRadius());
		packet.writeF(_activeChar.getCollisionHeight());
		
		packet.writeD(_activeChar.getAppearance().getHairStyle());
		packet.writeD(_activeChar.getAppearance().getHairColor());
		packet.writeD(_activeChar.getAppearance().getFace());
		
		packet.writeS(_gmSeeInvis ? "Invisible" : _activeChar.getAppearance().getVisibleTitle());
		
		if (!_activeChar.isCursedWeaponEquipped())
		{
			packet.writeD(_activeChar.getClanId());
			packet.writeD(_activeChar.getClanCrestId());
			packet.writeD(_activeChar.getAllyId());
			packet.writeD(_activeChar.getAllyCrestId());
		}
		else
		{
			packet.writeD(0x00);
			packet.writeD(0x00);
			packet.writeD(0x00);
			packet.writeD(0x00);
		}
		
		packet.writeC(_activeChar.isSitting() ? 0 : 1); // standing = 1 sitting = 0
		packet.writeC(_activeChar.isRunning() ? 1 : 0); // running = 1 walking = 0
		packet.writeC(_activeChar.isInCombat() ? 1 : 0);
		
		packet.writeC(!_activeChar.isInOlympiadMode() && _activeChar.isAlikeDead() ? 1 : 0);
		
		packet.writeC(!_gmSeeInvis && _activeChar.isInvisible() ? 1 : 0); // invisible = 1 visible =0
		
		packet.writeC(_activeChar.getMountType().ordinal()); // 1-on Strider, 2-on Wyvern, 3-on Great Wolf, 0-no mount
		packet.writeC(_activeChar.getPrivateStoreType().getId());
		
		packet.writeH(_activeChar.getCubics().size());
		for (int cubicId : _activeChar.getCubics().keySet())
		{
			packet.writeH(cubicId);
		}
		
		packet.writeC(_activeChar.isInPartyMatchRoom() ? 1 : 0);
		
		packet.writeD(_gmSeeInvis ? (_activeChar.getAbnormalVisualEffects() | AbnormalVisualEffect.STEALTH.getMask()) : _activeChar.getAbnormalVisualEffects());
		
		packet.writeC(_activeChar.isInsideZone(ZoneId.WATER) ? 1 : _activeChar.isFlyingMounted() ? 2 : 0);
		
		packet.writeH(_activeChar.getRecomHave()); // Blue value for name (0 = white, 255 = pure blue)
		packet.writeD(_activeChar.getMountNpcId() + 1000000);
		packet.writeD(_activeChar.getClassId().getId());
		packet.writeD(0x00); // ?
		packet.writeC(_activeChar.isMounted() ? 0 : _activeChar.getEnchantEffect());
		
		packet.writeC(_activeChar.getTeam().getId());
		
		packet.writeD(_activeChar.getClanCrestLargeId());
		packet.writeC(_activeChar.isNoble() ? 1 : 0); // Symbol on char menu ctrl+I
		packet.writeC(_activeChar.isHero() || (_activeChar.isGM() && Config.GM_HERO_AURA) ? 1 : 0); // Hero Aura
		
		packet.writeC(_activeChar.isFishing() ? 1 : 0); // 0x01: Fishing Mode (Cant be undone by setting back to 0)
		packet.writeD(_activeChar.getFishx());
		packet.writeD(_activeChar.getFishy());
		packet.writeD(_activeChar.getFishz());
		
		packet.writeD(_activeChar.getAppearance().getNameColor());
		
		packet.writeD(_heading);
		
		packet.writeD(_activeChar.getPledgeClass());
		packet.writeD(_activeChar.getPledgeType());
		
		packet.writeD(_activeChar.getAppearance().getTitleColor());
		
		packet.writeD(_activeChar.isCursedWeaponEquipped() ? CursedWeaponsManager.getInstance().getLevel(_activeChar.getCursedWeaponEquippedId()) : 0);
		
		packet.writeD(_activeChar.getClanId() > 0 ? _activeChar.getClan().getReputationScore() : 0);
		
		// T1
		packet.writeD(_activeChar.getTransformationDisplayId());
		packet.writeD(_activeChar.getAgathionId());
		
		// T2
		packet.writeD(0x01);
		
		// T2.3
		packet.writeD(_activeChar.getAbnormalVisualEffectSpecial());
		return true;
	}
	
	@Override
	public int[] getPaperdollOrder()
	{
		return PAPERDOLL_ORDER;
	}
}

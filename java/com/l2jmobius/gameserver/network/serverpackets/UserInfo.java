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
import com.l2jmobius.gameserver.data.xml.impl.ExperienceData;
import com.l2jmobius.gameserver.instancemanager.CursedWeaponsManager;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager;
import com.l2jmobius.gameserver.model.Elementals;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.skills.AbnormalVisualEffect;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public final class UserInfo implements IClientOutgoingPacket
{
	private final L2PcInstance _activeChar;
	private int _relation;
	private int _airShipHelm;
	
	private final int _runSpd;
	private final int _walkSpd;
	private final int _swimRunSpd;
	private final int _swimWalkSpd;
	private final int _flyRunSpd;
	private final int _flyWalkSpd;
	private final double _moveMultiplier;
	
	public UserInfo(L2PcInstance cha)
	{
		_activeChar = cha;
		
		final int _territoryId = TerritoryWarManager.getInstance().getRegisteredTerritoryId(cha);
		_relation = _activeChar.isClanLeader() ? 0x40 : 0;
		if (_activeChar.getSiegeState() == 1)
		{
			if (_territoryId == 0)
			{
				_relation |= 0x180;
			}
			else
			{
				_relation |= 0x1000;
			}
		}
		if (_activeChar.getSiegeState() == 2)
		{
			_relation |= 0x80;
		}
		// _isDisguised = TerritoryWarManager.getInstance().isDisguised(character.getObjectId());
		if (_activeChar.isInAirShip() && _activeChar.getAirShip().isCaptain(_activeChar))
		{
			_airShipHelm = _activeChar.getAirShip().getHelmItemId();
		}
		else
		{
			_airShipHelm = 0;
		}
		
		_moveMultiplier = cha.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(cha.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(cha.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(cha.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(cha.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = cha.isFlying() ? _runSpd : 0;
		_flyWalkSpd = cha.isFlying() ? _walkSpd : 0;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.USER_INFO.writeId(packet);
		
		packet.writeD(_activeChar.getX());
		packet.writeD(_activeChar.getY());
		packet.writeD(_activeChar.getZ());
		packet.writeD(_activeChar.getVehicle() != null ? _activeChar.getVehicle().getObjectId() : 0);
		
		packet.writeD(_activeChar.getObjectId());
		packet.writeS(_activeChar.getAppearance().getVisibleName());
		packet.writeD(_activeChar.getRace().ordinal());
		packet.writeD(_activeChar.getAppearance().getSex() ? 1 : 0);
		
		packet.writeD(_activeChar.getBaseClass());
		
		packet.writeD(_activeChar.getLevel());
		packet.writeQ(_activeChar.getExp());
		packet.writeF((float) (_activeChar.getExp() - ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel())) / (ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel() + 1) - ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel()))); // High Five exp %
		packet.writeD(_activeChar.getSTR());
		packet.writeD(_activeChar.getDEX());
		packet.writeD(_activeChar.getCON());
		packet.writeD(_activeChar.getINT());
		packet.writeD(_activeChar.getWIT());
		packet.writeD(_activeChar.getMEN());
		packet.writeD(_activeChar.getMaxHp());
		packet.writeD((int) Math.round(_activeChar.getCurrentHp()));
		packet.writeD(_activeChar.getMaxMp());
		packet.writeD((int) Math.round(_activeChar.getCurrentMp()));
		packet.writeD((int) _activeChar.getSp());
		packet.writeD(_activeChar.getCurrentLoad());
		packet.writeD(_activeChar.getMaxLoad());
		
		packet.writeD(_activeChar.getActiveWeaponItem() != null ? 40 : 20); // 20 no weapon, 40 weapon equipped
		
		for (int slot : getPaperdollOrder())
		{
			packet.writeD(_activeChar.getInventory().getPaperdollObjectId(slot));
		}
		
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
		packet.writeD((int) _activeChar.getPAtk(null));
		packet.writeD((int) _activeChar.getPAtkSpd());
		packet.writeD((int) _activeChar.getPDef(null));
		packet.writeD(_activeChar.getEvasionRate(null));
		packet.writeD(_activeChar.getAccuracy());
		packet.writeD(_activeChar.getCriticalHit(null, null));
		packet.writeD((int) _activeChar.getMAtk(null, null));
		
		packet.writeD(_activeChar.getMAtkSpd());
		packet.writeD((int) _activeChar.getPAtkSpd());
		
		packet.writeD((int) _activeChar.getMDef(null, null));
		
		packet.writeD(_activeChar.getPvpFlag());
		packet.writeD(_activeChar.getKarma());
		
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
		packet.writeD(_activeChar.isGM() ? 1 : 0); // builder level
		
		String title = _activeChar.getTitle();
		if (_activeChar.isGM() && _activeChar.isInvisible())
		{
			title = "[Invisible]";
		}
		packet.writeS(title);
		
		packet.writeD(_activeChar.getClanId());
		packet.writeD(_activeChar.getClanCrestId());
		packet.writeD(_activeChar.getAllyId());
		packet.writeD(_activeChar.getAllyCrestId()); // ally crest id
		// 0x40 leader rights
		// siege flags: attacker - 0x180 sword over name, defender - 0x80 shield, 0xC0 crown (|leader), 0x1C0 flag (|leader)
		packet.writeD(_relation);
		packet.writeC(_activeChar.getMountType().ordinal()); // mount type
		packet.writeC(_activeChar.getPrivateStoreType().getId());
		packet.writeC(_activeChar.hasDwarvenCraft() ? 1 : 0);
		packet.writeD(_activeChar.getPkKills());
		packet.writeD(_activeChar.getPvpKills());
		
		packet.writeH(_activeChar.getCubics().size());
		for (int cubicId : _activeChar.getCubics().keySet())
		{
			packet.writeH(cubicId);
		}
		
		packet.writeC(_activeChar.isInPartyMatchRoom() ? 1 : 0);
		
		packet.writeD(_activeChar.isInvisible() ? _activeChar.getAbnormalVisualEffects() | AbnormalVisualEffect.STEALTH.getMask() : _activeChar.getAbnormalVisualEffects());
		packet.writeC(_activeChar.isInsideZone(ZoneId.WATER) ? 1 : _activeChar.isFlyingMounted() ? 2 : 0);
		
		packet.writeD(_activeChar.getClanPrivileges().getBitmask());
		
		packet.writeH(_activeChar.getRecomLeft()); // c2 recommendations remaining
		packet.writeH(_activeChar.getRecomHave()); // c2 recommendations received
		packet.writeD(_activeChar.getMountNpcId() > 0 ? _activeChar.getMountNpcId() + 1000000 : 0);
		packet.writeH(_activeChar.getInventoryLimit());
		
		packet.writeD(_activeChar.getClassId().getId());
		packet.writeD(0x00); // special effects? circles around player...
		packet.writeD(_activeChar.getMaxCp());
		packet.writeD((int) _activeChar.getCurrentCp());
		packet.writeC(_activeChar.isMounted() || (_airShipHelm != 0) ? 0 : _activeChar.getEnchantEffect());
		
		packet.writeC(_activeChar.getTeam().getId());
		
		packet.writeD(_activeChar.getClanCrestLargeId());
		packet.writeC(_activeChar.isNoble() ? 1 : 0); // 0x01: symbol on char menu ctrl+I
		packet.writeC(_activeChar.isHero() || (_activeChar.isGM() && Config.GM_HERO_AURA) ? 1 : 0); // 0x01: Hero Aura
		
		packet.writeC(_activeChar.isFishing() ? 1 : 0); // Fishing Mode
		packet.writeD(_activeChar.getFishx()); // fishing x
		packet.writeD(_activeChar.getFishy()); // fishing y
		packet.writeD(_activeChar.getFishz()); // fishing z
		packet.writeD(_activeChar.getAppearance().getNameColor());
		
		// new c5
		packet.writeC(_activeChar.isRunning() ? 0x01 : 0x00); // changes the Speed display on Status Window
		
		packet.writeD(_activeChar.getPledgeClass()); // changes the text above CP on Status Window
		packet.writeD(_activeChar.getPledgeType());
		
		packet.writeD(_activeChar.getAppearance().getTitleColor());
		
		packet.writeD(_activeChar.isCursedWeaponEquipped() ? CursedWeaponsManager.getInstance().getLevel(_activeChar.getCursedWeaponEquippedId()) : 0);
		
		// T1 Starts
		packet.writeD(_activeChar.getTransformationDisplayId());
		
		final byte attackAttribute = _activeChar.getAttackElement();
		packet.writeH(attackAttribute);
		packet.writeH(_activeChar.getAttackElementValue(attackAttribute));
		packet.writeH(_activeChar.getDefenseElementValue(Elementals.FIRE));
		packet.writeH(_activeChar.getDefenseElementValue(Elementals.WATER));
		packet.writeH(_activeChar.getDefenseElementValue(Elementals.WIND));
		packet.writeH(_activeChar.getDefenseElementValue(Elementals.EARTH));
		packet.writeH(_activeChar.getDefenseElementValue(Elementals.HOLY));
		packet.writeH(_activeChar.getDefenseElementValue(Elementals.DARK));
		
		packet.writeD(_activeChar.getAgathionId());
		
		// T2 Starts
		packet.writeD(_activeChar.getFame()); // Fame
		packet.writeD(_activeChar.isMinimapAllowed() ? 1 : 0); // Minimap on Hellbound
		packet.writeD(_activeChar.getVitalityPoints()); // Vitality Points
		packet.writeD(_activeChar.getAbnormalVisualEffectSpecial());
		// packet.writeD(_territoryId); // CT2.3
		// packet.writeD((_isDisguised ? 0x01: 0x00)); // CT2.3
		// packet.writeD(_territoryId); // CT2.3
		return true;
	}
}

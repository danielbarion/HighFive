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
import com.l2jmobius.gameserver.data.xml.impl.ExperienceData;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class GMViewCharacterInfo implements IClientOutgoingPacket
{
	private final L2PcInstance _activeChar;
	private final int _runSpd;
	private final int _walkSpd;
	private final int _swimRunSpd;
	private final int _swimWalkSpd;
	private final int _flyRunSpd;
	private final int _flyWalkSpd;
	private final double _moveMultiplier;
	
	public GMViewCharacterInfo(L2PcInstance cha)
	{
		_activeChar = cha;
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
		OutgoingPackets.GM_VIEW_CHARACTER_INFO.writeId(packet);
		
		packet.writeD(_activeChar.getX());
		packet.writeD(_activeChar.getY());
		packet.writeD(_activeChar.getZ());
		packet.writeD(_activeChar.getHeading());
		packet.writeD(_activeChar.getObjectId());
		packet.writeS(_activeChar.getName());
		packet.writeD(_activeChar.getRace().ordinal());
		packet.writeD(_activeChar.getAppearance().getSex() ? 1 : 0);
		packet.writeD(_activeChar.getClassId().getId());
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
		packet.writeD((int) _activeChar.getCurrentHp());
		packet.writeD(_activeChar.getMaxMp());
		packet.writeD((int) _activeChar.getCurrentMp());
		packet.writeD((int) _activeChar.getSp());
		packet.writeD(_activeChar.getCurrentLoad());
		packet.writeD(_activeChar.getMaxLoad());
		packet.writeD(_activeChar.getPkKills());
		
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
		
		packet.writeD(_activeChar.getInventory().getTalismanSlots()); // CT2.3
		packet.writeD(_activeChar.getInventory().canEquipCloak() ? 1 : 0); // CT2.3
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
		
		packet.writeD(_activeChar.getPvpFlag()); // 0-non-pvp 1-pvp = violett name
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
		packet.writeF(_activeChar.getAttackSpeedMultiplier()); // 2.9);//
		packet.writeF(_activeChar.getCollisionRadius()); // scale
		packet.writeF(_activeChar.getCollisionHeight()); // y offset ??!? fem dwarf 4033
		packet.writeD(_activeChar.getAppearance().getHairStyle());
		packet.writeD(_activeChar.getAppearance().getHairColor());
		packet.writeD(_activeChar.getAppearance().getFace());
		packet.writeD(_activeChar.isGM() ? 0x01 : 0x00); // builder level
		
		packet.writeS(_activeChar.getTitle());
		packet.writeD(_activeChar.getClanId()); // pledge id
		packet.writeD(_activeChar.getClanCrestId()); // pledge crest id
		packet.writeD(_activeChar.getAllyId()); // ally id
		packet.writeC(_activeChar.getMountType().ordinal()); // mount type
		packet.writeC(_activeChar.getPrivateStoreType().getId());
		packet.writeC(_activeChar.hasDwarvenCraft() ? 1 : 0);
		packet.writeD(_activeChar.getPkKills());
		packet.writeD(_activeChar.getPvpKills());
		
		packet.writeH(_activeChar.getRecomLeft());
		packet.writeH(_activeChar.getRecomHave()); // Blue value for name (0 = white, 255 = pure blue)
		packet.writeD(_activeChar.getClassId().getId());
		packet.writeD(0x00); // special effects? circles around player...
		packet.writeD(_activeChar.getMaxCp());
		packet.writeD((int) _activeChar.getCurrentCp());
		
		packet.writeC(_activeChar.isRunning() ? 0x01 : 0x00); // changes the Speed display on Status Window
		
		packet.writeC(321);
		
		packet.writeD(_activeChar.getPledgeClass()); // changes the text above CP on Status Window
		
		packet.writeC(_activeChar.isNoble() ? 0x01 : 0x00);
		packet.writeC(_activeChar.isHero() ? 0x01 : 0x00);
		
		packet.writeD(_activeChar.getAppearance().getNameColor());
		packet.writeD(_activeChar.getAppearance().getTitleColor());
		
		final byte attackAttribute = _activeChar.getAttackElement();
		packet.writeH(attackAttribute);
		packet.writeH(_activeChar.getAttackElementValue(attackAttribute));
		for (byte i = 0; i < 6; i++)
		{
			packet.writeH(_activeChar.getDefenseElementValue(i));
		}
		packet.writeD(_activeChar.getFame());
		packet.writeD(_activeChar.getVitalityPoints());
		return true;
	}
}

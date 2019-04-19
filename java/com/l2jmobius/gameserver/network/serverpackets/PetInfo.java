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
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2PetInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2ServitorInstance;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.OutgoingPackets;

public class PetInfo implements IClientOutgoingPacket
{
	private final L2Summon _summon;
	private final int _x;
	private final int _y;
	private final int _z;
	private final int _heading;
	private final boolean _isSummoned;
	private final int _val;
	private final int _mAtkSpd;
	private final int _pAtkSpd;
	private final int _runSpd;
	private final int _walkSpd;
	private final int _swimRunSpd;
	private final int _swimWalkSpd;
	private final int _flyRunSpd;
	private final int _flyWalkSpd;
	private final double _moveMultiplier;
	private final int _maxHp;
	private final int _maxMp;
	private int _maxFed;
	private int _curFed;
	
	public PetInfo(L2Summon summon, int val)
	{
		_summon = summon;
		_isSummoned = summon.isShowSummonAnimation();
		_x = summon.getX();
		_y = summon.getY();
		_z = summon.getZ();
		_heading = summon.getHeading();
		_mAtkSpd = summon.getMAtkSpd();
		_pAtkSpd = (int) summon.getPAtkSpd();
		_moveMultiplier = summon.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(summon.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(summon.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(summon.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(summon.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = summon.isFlying() ? _runSpd : 0;
		_flyWalkSpd = summon.isFlying() ? _walkSpd : 0;
		_maxHp = summon.getMaxHp();
		_maxMp = summon.getMaxMp();
		_val = val;
		if (summon.isPet())
		{
			final L2PetInstance pet = (L2PetInstance) _summon;
			_curFed = pet.getCurrentFed(); // how fed it is
			_maxFed = pet.getMaxFed(); // max fed it can be
		}
		else if (summon.isServitor())
		{
			final L2ServitorInstance sum = (L2ServitorInstance) _summon;
			_curFed = sum.getLifeTimeRemaining();
			_maxFed = sum.getLifeTime();
		}
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.PET_INFO.writeId(packet);
		packet.writeD(_summon.getSummonType());
		packet.writeD(_summon.getObjectId());
		packet.writeD(_summon.getTemplate().getDisplayId() + 1000000);
		packet.writeD(0); // 1=attackable
		
		packet.writeD(_x);
		packet.writeD(_y);
		packet.writeD(_z);
		packet.writeD(_heading);
		packet.writeD(0);
		packet.writeD(_mAtkSpd);
		packet.writeD(_pAtkSpd);
		packet.writeD(_runSpd);
		packet.writeD(_walkSpd);
		packet.writeD(_swimRunSpd);
		packet.writeD(_swimWalkSpd);
		packet.writeD(_flyRunSpd);
		packet.writeD(_flyWalkSpd);
		packet.writeD(_flyRunSpd);
		packet.writeD(_flyWalkSpd);
		packet.writeF(_moveMultiplier);
		packet.writeF(_summon.getAttackSpeedMultiplier()); // attack speed multiplier
		packet.writeF(_summon.getTemplate().getfCollisionRadius());
		packet.writeF(_summon.getTemplate().getfCollisionHeight());
		packet.writeD(_summon.getWeapon()); // right hand weapon
		packet.writeD(_summon.getArmor()); // body armor
		packet.writeD(0x00); // left hand weapon
		packet.writeC(_summon.getOwner() != null ? 1 : 0); // when pet is dead and player exit game, pet doesn't show master name
		packet.writeC(_summon.isRunning() ? 1 : 0); // running=1 (it is always 1, walking mode is calculated from multiplier)
		packet.writeC(_summon.isInCombat() ? 1 : 0); // attacking 1=true
		packet.writeC(_summon.isAlikeDead() ? 1 : 0); // dead 1=true
		packet.writeC(_isSummoned ? 2 : _val); // 0=teleported 1=default 2=summoned
		packet.writeD(-1); // High Five NPCString ID
		if (_summon.isPet())
		{
			packet.writeS(_summon.getName()); // Pet name.
		}
		else
		{
			packet.writeS(_summon.getTemplate().isUsingServerSideName() ? _summon.getName() : ""); // Summon name.
		}
		packet.writeD(-1); // High Five NPCString ID
		packet.writeS(_summon.getTitle()); // owner name
		packet.writeD(1);
		packet.writeD(_summon.getPvpFlag()); // 0 = white,2= purpleblink, if its greater then karma = purple
		packet.writeD(_summon.getKarma()); // karma
		packet.writeD(_curFed); // how fed it is
		packet.writeD(_maxFed); // max fed it can be
		packet.writeD((int) _summon.getCurrentHp());// current hp
		packet.writeD(_maxHp);// max hp
		packet.writeD((int) _summon.getCurrentMp());// current mp
		packet.writeD(_maxMp);// max mp
		packet.writeD((int) _summon.getStat().getSp()); // sp
		packet.writeD(_summon.getLevel());// lvl
		packet.writeQ(_summon.getStat().getExp());
		
		if (_summon.getExpForThisLevel() > _summon.getStat().getExp())
		{
			packet.writeQ(_summon.getStat().getExp());// 0% absolute value
		}
		else
		{
			packet.writeQ(_summon.getExpForThisLevel());// 0% absolute value
		}
		
		packet.writeQ(_summon.getExpForNextLevel());// 100% absoulte value
		packet.writeD(_summon.isPet() ? _summon.getInventory().getTotalWeight() : 0);// weight
		packet.writeD(_summon.getMaxLoad());// max weight it can carry
		packet.writeD((int) _summon.getPAtk(null));// patk
		packet.writeD((int) _summon.getPDef(null));// pdef
		packet.writeD((int) _summon.getMAtk(null, null));// matk
		packet.writeD((int) _summon.getMDef(null, null));// mdef
		packet.writeD(_summon.getAccuracy());// accuracy
		packet.writeD(_summon.getEvasionRate(null));// evasion
		packet.writeD(_summon.getCriticalHit(null, null));// critical
		packet.writeD((int) _summon.getMoveSpeed());// speed
		packet.writeD((int) _summon.getPAtkSpd());// atkspeed
		packet.writeD(_summon.getMAtkSpd());// casting speed
		
		packet.writeD(_summon.getAbnormalVisualEffects());// c2 abnormal visual effect... bleed=1; poison=2; poison & bleed=3; flame=4;
		packet.writeH(_summon.isMountable() ? 1 : 0);// c2 ride button
		
		packet.writeC(_summon.isInsideZone(ZoneId.WATER) ? 1 : _summon.isFlying() ? 2 : 0); // c2
		
		// Following all added in C4.
		packet.writeH(0); // ??
		packet.writeC(_summon.getTeam().getId());
		packet.writeD(_summon.getSoulShotsPerHit()); // How many soulshots this servitor uses per hit
		packet.writeD(_summon.getSpiritShotsPerHit()); // How many spiritshots this servitor uses per hit
		packet.writeD(_summon.getFormId());// CT1.5 Pet form and skills
		packet.writeD(_summon.getAbnormalVisualEffectSpecial());
		return true;
	}
}

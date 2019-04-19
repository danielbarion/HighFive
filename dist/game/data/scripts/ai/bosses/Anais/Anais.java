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
package ai.bosses.Anais;

import java.util.ArrayList;

import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;

import ai.AbstractNpcAI;

/**
 * Anais AI.
 * @author nonom
 */
public final class Anais extends AbstractNpcAI
{
	// NPCs
	private static final int ANAIS = 25701;
	private static final int DIVINE_BURNER = 18915;
	private static final int GRAIL_WARD = 18929;
	// Skill
	private static SkillHolder DIVINE_NOVA = new SkillHolder(6326, 1);
	// Instances
	ArrayList<L2Npc> _divineBurners = new ArrayList<>(4);
	private L2PcInstance _nextTarget = null;
	private L2Npc _current = null;
	private int _pot = 0;
	
	private Anais()
	{
		addAttackId(ANAIS);
		addSpawnId(DIVINE_BURNER);
		addKillId(GRAIL_WARD);
	}
	
	private void burnerOnAttack(int pot, L2Npc anais)
	{
		final L2Npc npc = _divineBurners.get(pot);
		npc.setDisplayEffect(1);
		npc.setWalking();
		if (pot < 4)
		{
			_current = npc;
			if (getQuestTimer("CHECK", anais, null) == null)
			{
				startQuestTimer("CHECK", 3000, anais, null);
			}
		}
		else
		{
			cancelQuestTimer("CHECK", anais, null);
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "CHECK":
			{
				if (!npc.isAttackingNow())
				{
					cancelQuestTimer("CHECK", npc, null);
				}
				if ((_current != null) || (_pot < 4))
				{
					final L2Object target = npc.getTarget();
					_nextTarget = (target != null) && target.isPlayer() ? (L2PcInstance) target : null;
					final L2Npc b = _divineBurners.get(_pot);
					_pot += 1;
					b.setDisplayEffect(1);
					b.setWalking();
					final L2Npc ward = addSpawn(GRAIL_WARD, new Location(b.getX(), b.getY(), b.getZ()), true, 0);
					((L2Attackable) ward).addDamageHate(_nextTarget, 0, 999);
					ward.setRunning();
					ward.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, _nextTarget, null);
					startQuestTimer("GUARD_ATTACK", 1000, ward, _nextTarget, true);
					startQuestTimer("SUICIDE", 20000, ward, null);
					ward.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, _nextTarget);
				}
				break;
			}
			case "GUARD_ATTACK":
			{
				if (_nextTarget != null)
				{
					final double distance = npc.calculateDistance2D(_nextTarget);
					if (distance < 100)
					{
						npc.doCast(DIVINE_NOVA.getSkill());
					}
					else if (distance > 2000)
					{
						npc.doDie(null);
						cancelQuestTimer("GUARD_ATTACK", npc, player);
					}
				}
				break;
			}
			case "SUICIDE":
			{
				npc.doCast(DIVINE_NOVA.getSkill());
				cancelQuestTimer("GUARD_ATTACK", npc, _nextTarget);
				if (_current != null)
				{
					_current.setDisplayEffect(2);
					_current.setWalking();
					_current = null;
				}
				npc.doDie(null);
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (_pot == 0)
		{
			burnerOnAttack(0, npc);
		}
		else if ((npc.getCurrentHp() <= (npc.getMaxRecoverableHp() * 0.75)) && (_pot == 1))
		{
			burnerOnAttack(1, npc);
		}
		else if ((npc.getCurrentHp() <= (npc.getMaxRecoverableHp() * 0.5)) && (_pot == 2))
		{
			burnerOnAttack(2, npc);
		}
		else if ((npc.getCurrentHp() <= (npc.getMaxRecoverableHp() * 0.25)) && (_pot == 3))
		{
			burnerOnAttack(3, npc);
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		_divineBurners.add(npc);
		return super.onSpawn(npc);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		npc.doCast(DIVINE_NOVA.getSkill());
		cancelQuestTimer("GUARD_ATTACK", npc, _nextTarget);
		cancelQuestTimer("CHECK", npc, null);
		if (_current != null)
		{
			_current.setDisplayEffect(2);
			_current.setWalking();
			_current = null;
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	public static void main(String[] args)
	{
		new Anais();
	}
}

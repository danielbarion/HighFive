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
package ai.others;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.network.NpcStringId;

import ai.AbstractNpcAI;

/**
 * AI for Kamaloka (33) - Crimson Hatu Otis
 * @author Gladicek
 */
public final class CrimsonHatuOtis extends AbstractNpcAI
{
	// Npc
	private static final int CRIMSON_HATU_OTIS = 18558;
	// Skills
	private static SkillHolder BOSS_SPINING_SLASH = new SkillHolder(4737, 1);
	private static SkillHolder BOSS_HASTE = new SkillHolder(4175, 1);
	
	private CrimsonHatuOtis()
	{
		addAttackId(CRIMSON_HATU_OTIS);
		addKillId(CRIMSON_HATU_OTIS);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "SKILL":
			{
				if (npc.isDead())
				{
					cancelQuestTimer("SKILL", npc, null);
					return null;
				}
				npc.setTarget(player);
				npc.doCast(BOSS_SPINING_SLASH.getSkill());
				startQuestTimer("SKILL", 60000, npc, null);
				break;
			}
			case "BUFF":
			{
				if (npc.isScriptValue(2))
				{
					npc.setTarget(npc);
					npc.doCast(BOSS_HASTE.getSkill());
				}
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (npc.isScriptValue(0))
		{
			npc.setScriptValue(1);
			startQuestTimer("SKILL", 5000, npc, null);
		}
		else if (npc.isScriptValue(1) && (npc.getCurrentHp() < (npc.getMaxHp() * 0.3)))
		{
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.I_VE_HAD_IT_UP_TO_HERE_WITH_YOU_I_LL_TAKE_CARE_OF_YOU);
			npc.setScriptValue(2);
			startQuestTimer("BUFF", 1000, npc, null);
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		cancelQuestTimer("SKILL", npc, null);
		cancelQuestTimer("BUFF", npc, null);
		return super.onKill(npc, player, isSummon);
	}
	
	public static void main(String[] args)
	{
		new CrimsonHatuOtis();
	}
}
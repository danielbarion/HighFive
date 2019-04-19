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
package ai.others.ArenaManager;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.zone.ZoneId;
import com.l2jmobius.gameserver.network.SystemMessageId;

import ai.AbstractNpcAI;

/**
 * Arena Manager AI.
 * @author St3eT
 */
public final class ArenaManager extends AbstractNpcAI
{
	// NPCs
	private static final int[] ARENA_MANAGER =
	{
		31226, // Arena Director (MDT)
		31225, // Arena Manager (Coliseum)
	};
	// Skill
	private static final SkillHolder[] BUFFS =
	{
		new SkillHolder(6805, 1), // Arena Empower
		new SkillHolder(6806, 1), // Arena Acumen
		new SkillHolder(6807, 1), // Arena Concentration
		new SkillHolder(6808, 1), // Arena Might
		new SkillHolder(6804, 1), // Arena Wind Walk
		new SkillHolder(6812, 1), // Arena Berserker Spirit
	};
	private static final SkillHolder CP_RECOVERY = new SkillHolder(4380, 1); // Arena: CP Recovery
	private static final SkillHolder HP_RECOVERY = new SkillHolder(6817, 1); // Arena HP Recovery
	// Misc
	private static final int CP_COST = 1000;
	private static final int HP_COST = 1000;
	private static final int BUFF_COST = 2000;
	
	private ArenaManager()
	{
		addStartNpc(ARENA_MANAGER);
		addTalkId(ARENA_MANAGER);
		addFirstTalkId(ARENA_MANAGER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "CPrecovery":
			{
				if (player.getAdena() >= CP_COST)
				{
					takeItems(player, Inventory.ADENA_ID, CP_COST);
					startQuestTimer("CPrecovery_delay", 2000, npc, player);
				}
				else
				{
					player.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				}
				break;
			}
			case "CPrecovery_delay":
			{
				if ((player != null) && !player.isInsideZone(ZoneId.PVP))
				{
					npc.setTarget(player);
					npc.doCast(CP_RECOVERY.getSkill());
				}
				break;
			}
			case "HPrecovery":
			{
				if (player.getAdena() >= HP_COST)
				{
					takeItems(player, Inventory.ADENA_ID, HP_COST);
					startQuestTimer("HPrecovery_delay", 2000, npc, player);
				}
				else
				{
					player.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				}
				break;
			}
			case "HPrecovery_delay":
			{
				if ((player != null) && !player.isInsideZone(ZoneId.PVP))
				{
					npc.setTarget(player);
					npc.doCast(HP_RECOVERY.getSkill());
				}
				break;
			}
			case "Buff":
			{
				if (player.getAdena() >= BUFF_COST)
				{
					takeItems(player, Inventory.ADENA_ID, BUFF_COST);
					npc.setTarget(player);
					for (SkillHolder skill : BUFFS)
					{
						npc.doCast(skill.getSkill());
					}
				}
				else
				{
					player.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				}
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	public static void main(String[] args)
	{
		new ArenaManager();
	}
}
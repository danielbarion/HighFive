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
package handlers.actionshifthandlers;

import java.util.Set;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.datatables.SpawnTable;
import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.handler.IActionShiftHandler;
import com.l2jmobius.gameserver.instancemanager.WalkingManager;
import com.l2jmobius.gameserver.model.Elementals;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jmobius.gameserver.util.Util;

import handlers.bypasshandlers.NpcViewMod;

public class L2NpcActionShift implements IActionShiftHandler
{
	/**
	 * Manage and Display the GM console to modify the L2NpcInstance (GM only).<BR>
	 * <BR>
	 * <B><U> Actions (If the L2PcInstance is a GM only)</U> :</B><BR>
	 * <BR>
	 * <li>Set the L2NpcInstance as target of the L2PcInstance player (if necessary)</li>
	 * <li>Send a Server->Client packet MyTargetSelected to the L2PcInstance player (display the select window)</li>
	 * <li>If L2NpcInstance is autoAttackable, send a Server->Client packet StatusUpdate to the L2PcInstance in order to update L2NpcInstance HP bar</li>
	 * <li>Send a Server->Client NpcHtmlMessage() containing the GM console about this L2NpcInstance</li><BR>
	 * <BR>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : Each group of Server->Client packet must be terminated by a ActionFailed packet in order to avoid that client wait an other packet</B></FONT><BR>
	 * <BR>
	 * <B><U> Example of use </U> :</B><BR>
	 * <BR>
	 * <li>Client packet : Action</li><BR>
	 * <BR>
	 */
	@Override
	public boolean action(L2PcInstance activeChar, L2Object target, boolean interact)
	{
		// Check if the L2PcInstance is a GM
		if (activeChar.isGM())
		{
			// Set the target of the L2PcInstance activeChar
			activeChar.setTarget(target);
			
			final NpcHtmlMessage html = new NpcHtmlMessage();
			html.setFile(activeChar, "data/html/admin/npcinfo.htm");
			
			html.replace("%objid%", String.valueOf(target.getObjectId()));
			html.replace("%class%", target.getClass().getSimpleName());
			html.replace("%race%", ((L2Npc) target).getTemplate().getRace().toString());
			html.replace("%id%", String.valueOf(((L2Npc) target).getTemplate().getId()));
			html.replace("%lvl%", String.valueOf(((L2Npc) target).getTemplate().getLevel()));
			html.replace("%name%", ((L2Npc) target).getTemplate().getName());
			html.replace("%tmplid%", String.valueOf(((L2Npc) target).getTemplate().getId()));
			html.replace("%aggro%", String.valueOf(target.isAttackable() ? ((L2Attackable) target).getAggroRange() : 0));
			html.replace("%hp%", String.valueOf((int) ((L2Character) target).getCurrentHp()));
			html.replace("%hpmax%", String.valueOf(((L2Character) target).getMaxHp()));
			html.replace("%mp%", String.valueOf((int) ((L2Character) target).getCurrentMp()));
			html.replace("%mpmax%", String.valueOf(((L2Character) target).getMaxMp()));
			
			html.replace("%patk%", String.valueOf((int) ((L2Character) target).getPAtk(null)));
			html.replace("%matk%", String.valueOf((int) ((L2Character) target).getMAtk(null, null)));
			html.replace("%pdef%", String.valueOf((int) ((L2Character) target).getPDef(null)));
			html.replace("%mdef%", String.valueOf((int) ((L2Character) target).getMDef(null, null)));
			html.replace("%accu%", String.valueOf(((L2Character) target).getAccuracy()));
			html.replace("%evas%", String.valueOf(((L2Character) target).getEvasionRate(null)));
			html.replace("%crit%", String.valueOf(((L2Character) target).getCriticalHit(null, null)));
			html.replace("%rspd%", String.valueOf((int) ((L2Character) target).getRunSpeed()));
			html.replace("%aspd%", String.valueOf(((L2Character) target).getPAtkSpd()));
			html.replace("%cspd%", String.valueOf(((L2Character) target).getMAtkSpd()));
			html.replace("%atkType%", String.valueOf(((L2Character) target).getTemplate().getBaseAttackType()));
			html.replace("%atkRng%", String.valueOf(((L2Character) target).getTemplate().getBaseAttackRange()));
			html.replace("%str%", String.valueOf(((L2Character) target).getSTR()));
			html.replace("%dex%", String.valueOf(((L2Character) target).getDEX()));
			html.replace("%con%", String.valueOf(((L2Character) target).getCON()));
			html.replace("%int%", String.valueOf(((L2Character) target).getINT()));
			html.replace("%wit%", String.valueOf(((L2Character) target).getWIT()));
			html.replace("%men%", String.valueOf(((L2Character) target).getMEN()));
			html.replace("%loc%", target.getX() + " " + target.getY() + " " + target.getZ());
			html.replace("%heading%", String.valueOf(((L2Character) target).getHeading()));
			html.replace("%collision_radius%", String.valueOf(((L2Character) target).getTemplate().getfCollisionRadius()));
			html.replace("%collision_height%", String.valueOf(((L2Character) target).getTemplate().getfCollisionHeight()));
			html.replace("%loc2d%", String.valueOf((int) activeChar.calculateDistance2D(target)));
			html.replace("%loc3d%", String.valueOf((int) activeChar.calculateDistance3D(target)));
			
			final byte attackAttribute = ((L2Character) target).getAttackElement();
			html.replace("%ele_atk%", Elementals.getElementName(attackAttribute));
			html.replace("%ele_atk_value%", String.valueOf(((L2Character) target).getAttackElementValue(attackAttribute)));
			html.replace("%ele_dfire%", String.valueOf(((L2Character) target).getDefenseElementValue(Elementals.FIRE)));
			html.replace("%ele_dwater%", String.valueOf(((L2Character) target).getDefenseElementValue(Elementals.WATER)));
			html.replace("%ele_dwind%", String.valueOf(((L2Character) target).getDefenseElementValue(Elementals.WIND)));
			html.replace("%ele_dearth%", String.valueOf(((L2Character) target).getDefenseElementValue(Elementals.EARTH)));
			html.replace("%ele_dholy%", String.valueOf(((L2Character) target).getDefenseElementValue(Elementals.HOLY)));
			html.replace("%ele_ddark%", String.valueOf(((L2Character) target).getDefenseElementValue(Elementals.DARK)));
			
			if (((L2Npc) target).getSpawn() != null)
			{
				html.replace("%territory%", ((L2Npc) target).getSpawn().getSpawnTerritory() == null ? "None" : ((L2Npc) target).getSpawn().getSpawnTerritory().getName());
				if (((L2Npc) target).getSpawn().isTerritoryBased())
				{
					html.replace("%spawntype%", "Random");
					final Location spawnLoc = ((L2Npc) target).getSpawn();
					html.replace("%spawn%", spawnLoc.getX() + " " + spawnLoc.getY() + " " + spawnLoc.getZ());
				}
				else
				{
					html.replace("%spawntype%", "Fixed");
					html.replace("%spawn%", ((L2Npc) target).getSpawn().getX() + " " + ((L2Npc) target).getSpawn().getY() + " " + ((L2Npc) target).getSpawn().getZ());
				}
				if (((L2Npc) target).getSpawn().getRespawnMinDelay() == 0)
				{
					html.replace("%resp%", "None");
				}
				else if (((L2Npc) target).getSpawn().hasRespawnRandom())
				{
					html.replace("%resp%", (((L2Npc) target).getSpawn().getRespawnMinDelay() / 1000) + "-" + (((L2Npc) target).getSpawn().getRespawnMaxDelay() / 1000) + " sec");
				}
				else
				{
					html.replace("%resp%", (((L2Npc) target).getSpawn().getRespawnMinDelay() / 1000) + " sec");
				}
				
				final String spawnFile = SpawnTable.getInstance().getSpawnFile(((L2Npc) target).getSpawn().getNpcSpawnTemplateId());
				html.replace("%spawnfile%", spawnFile.substring(spawnFile.lastIndexOf("\\") + 1));
			}
			else
			{
				html.replace("%spawnfile%", "<font color=FF0000>--</font>");
				html.replace("%territory%", "<font color=FF0000>--</font>");
				html.replace("%spawntype%", "<font color=FF0000>--</font>");
				html.replace("%spawn%", "<font color=FF0000>null</font>");
				html.replace("%resp%", "<font color=FF0000>--</font>");
			}
			
			if (((L2Npc) target).hasAI())
			{
				final Set<Integer> clans = ((L2Npc) target).getTemplate().getClans();
				final Set<Integer> ignoreClanNpcIds = ((L2Npc) target).getTemplate().getIgnoreClanNpcIds();
				final String clansString = clans != null ? Util.implode(clans.toArray(), ", ") : "";
				final String ignoreClanNpcIdsString = ignoreClanNpcIds != null ? Util.implode(ignoreClanNpcIds.toArray(), ", ") : "";
				
				html.replace("%ai_intention%", "<tr><td><table width=270 border=0 bgcolor=131210><tr><td width=100><font color=FFAA00>Intention:</font></td><td align=right width=170>" + ((L2Npc) target).getAI().getIntention().name() + "</td></tr></table></td></tr>");
				html.replace("%ai%", "<tr><td><table width=270 border=0><tr><td width=100><font color=FFAA00>AI</font></td><td align=right width=170>" + ((L2Npc) target).getAI().getClass().getSimpleName() + "</td></tr></table></td></tr>");
				html.replace("%ai_type%", "<tr><td><table width=270 border=0 bgcolor=131210><tr><td width=100><font color=FFAA00>AIType</font></td><td align=right width=170>" + ((L2Npc) target).getAiType() + "</td></tr></table></td></tr>");
				html.replace("%ai_clan%", "<tr><td><table width=270 border=0><tr><td width=100><font color=FFAA00>Clan & Range:</font></td><td align=right width=170>" + clansString + " " + ((L2Npc) target).getTemplate().getClanHelpRange() + "</td></tr></table></td></tr>");
				html.replace("%ai_enemy_clan%", "<tr><td><table width=270 border=0 bgcolor=131210><tr><td width=100><font color=FFAA00>Ignore & Range:</font></td><td align=right width=170>" + ignoreClanNpcIdsString + " " + ((L2Npc) target).getTemplate().getAggroRange() + "</td></tr></table></td></tr>");
			}
			else
			{
				html.replace("%ai_intention%", "");
				html.replace("%ai%", "");
				html.replace("%ai_type%", "");
				html.replace("%ai_clan%", "");
				html.replace("%ai_enemy_clan%", "");
			}
			
			final String routeName = WalkingManager.getInstance().getRouteName((L2Npc) target);
			if (!routeName.isEmpty())
			{
				html.replace("%route%", "<tr><td><table width=270 border=0><tr><td width=100><font color=LEVEL>Route:</font></td><td align=right width=170>" + routeName + "</td></tr></table></td></tr>");
			}
			else
			{
				html.replace("%route%", "");
			}
			activeChar.sendPacket(html);
		}
		else if (Config.ALT_GAME_VIEWNPC)
		{
			if (!target.isNpc() || target.isFakePlayer())
			{
				return false;
			}
			activeChar.setTarget(target);
			NpcViewMod.sendNpcView(activeChar, (L2Npc) target);
		}
		return true;
	}
	
	@Override
	public InstanceType getInstanceType()
	{
		return InstanceType.L2Npc;
	}
}

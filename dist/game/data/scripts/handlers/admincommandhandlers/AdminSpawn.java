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
package handlers.admincommandhandlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.SevenSigns;
import com.l2jmobius.gameserver.data.xml.impl.AdminData;
import com.l2jmobius.gameserver.data.xml.impl.NpcData;
import com.l2jmobius.gameserver.datatables.SpawnTable;
import com.l2jmobius.gameserver.handler.IAdminCommandHandler;
import com.l2jmobius.gameserver.instancemanager.DayNightSpawnManager;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.instancemanager.QuestManager;
import com.l2jmobius.gameserver.instancemanager.RaidBossSpawnManager;
import com.l2jmobius.gameserver.model.AutoSpawnHandler;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2Spawn;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Broadcast;
import com.l2jmobius.gameserver.util.BuilderUtil;
import com.l2jmobius.gameserver.util.Util;

/**
 * This class handles following admin commands: - show_spawns = shows menu - spawn_index lvl = shows menu for monsters with respective level - spawn_monster id = spawns monster id on target
 * @version $Revision: 1.2.2.5.2.5 $ $Date: 2005/04/11 10:06:06 $
 */
public class AdminSpawn implements IAdminCommandHandler
{
	private static final Logger LOGGER = Logger.getLogger(AdminSpawn.class.getName());
	
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_show_spawns",
		"admin_spawn",
		"admin_spawn_monster",
		"admin_spawn_index",
		"admin_unspawnall",
		"admin_respawnall",
		"admin_spawn_reload",
		"admin_npc_index",
		"admin_spawn_once",
		"admin_show_npcs",
		"admin_spawnnight",
		"admin_spawnday",
		"admin_instance_spawns",
		"admin_list_spawns",
		"admin_list_positions",
		"admin_spawn_debug_menu",
		"admin_spawn_debug_print",
		"admin_spawn_debug_print_menu",
		"admin_topspawncount",
		"admin_top_spawn_count"
	};
	
	@Override
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		if (command.equals("admin_show_spawns"))
		{
			AdminHtml.showAdminHtml(activeChar, "spawns.htm");
		}
		else if (command.equalsIgnoreCase("admin_spawn_debug_menu"))
		{
			AdminHtml.showAdminHtml(activeChar, "spawns_debug.htm");
		}
		else if (command.startsWith("admin_spawn_debug_print"))
		{
			final StringTokenizer st = new StringTokenizer(command, " ");
			final L2Object target = activeChar.getTarget();
			if (target instanceof L2Npc)
			{
				try
				{
					st.nextToken();
					final int type = Integer.parseInt(st.nextToken());
					printSpawn((L2Npc) target, type);
					if (command.contains("_menu"))
					{
						AdminHtml.showAdminHtml(activeChar, "spawns_debug.htm");
					}
				}
				catch (Exception e)
				{
				}
			}
			else
			{
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
			}
		}
		else if (command.startsWith("admin_spawn_index"))
		{
			final StringTokenizer st = new StringTokenizer(command, " ");
			try
			{
				st.nextToken();
				final int level = Integer.parseInt(st.nextToken());
				int from = 0;
				try
				{
					from = Integer.parseInt(st.nextToken());
				}
				catch (NoSuchElementException nsee)
				{
				}
				showMonsters(activeChar, level, from);
			}
			catch (Exception e)
			{
				AdminHtml.showAdminHtml(activeChar, "spawns.htm");
			}
		}
		else if (command.equals("admin_show_npcs"))
		{
			AdminHtml.showAdminHtml(activeChar, "npcs.htm");
		}
		else if (command.startsWith("admin_npc_index"))
		{
			final StringTokenizer st = new StringTokenizer(command, " ");
			try
			{
				st.nextToken();
				final String letter = st.nextToken();
				int from = 0;
				try
				{
					from = Integer.parseInt(st.nextToken());
				}
				catch (NoSuchElementException nsee)
				{
				}
				showNpcs(activeChar, letter, from);
			}
			catch (Exception e)
			{
				AdminHtml.showAdminHtml(activeChar, "npcs.htm");
			}
		}
		else if (command.startsWith("admin_instance_spawns"))
		{
			final StringTokenizer st = new StringTokenizer(command, " ");
			try
			{
				st.nextToken();
				final int instance = Integer.parseInt(st.nextToken());
				if (instance >= 300000)
				{
					final StringBuilder html = new StringBuilder(1500);
					html.append("<html><table width=\"100%\"><tr><td width=45><button value=\"Main\" action=\"bypass -h admin_admin\" width=45 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td><td width=180><center><font color=\"LEVEL\">Spawns for " + instance + "</font></td><td width=45><button value=\"Back\" action=\"bypass -h admin_current_player\" width=45 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr></table><br><table width=\"100%\"><tr><td width=200>NpcName</td><td width=70>Action</td></tr>");
					int counter = 0;
					int skiped = 0;
					final Instance inst = InstanceManager.getInstance().getInstance(instance);
					if (inst != null)
					{
						for (L2Npc npc : inst.getNpcs())
						{
							if (!npc.isDead())
							{
								// Only 50 because of client html limitation
								if (counter < 50)
								{
									html.append("<tr><td>" + npc.getName() + "</td><td><a action=\"bypass -h admin_move_to " + npc.getX() + " " + npc.getY() + " " + npc.getZ() + "\">Go</a></td></tr>");
									counter++;
								}
								else
								{
									skiped++;
								}
							}
						}
						html.append("<tr><td>Skipped:</td><td>" + skiped + "</td></tr></table></body></html>");
						final NpcHtmlMessage ms = new NpcHtmlMessage();
						ms.setHtml(html.toString());
						activeChar.sendPacket(ms);
					}
					else
					{
						BuilderUtil.sendSysMessage(activeChar, "Cannot find instance " + instance);
					}
				}
				else
				{
					BuilderUtil.sendSysMessage(activeChar, "Invalid instance number.");
				}
			}
			catch (Exception e)
			{
				BuilderUtil.sendSysMessage(activeChar, "Usage //instance_spawns <instance_number>");
			}
		}
		else if (command.startsWith("admin_unspawnall"))
		{
			Broadcast.toAllOnlinePlayers(SystemMessage.getSystemMessage(SystemMessageId.THE_NPC_SERVER_IS_NOT_OPERATING_AT_THIS_TIME));
			// Unload all scripts.
			QuestManager.getInstance().unloadAllScripts();
			// Delete all spawns.
			AutoSpawnHandler.getInstance().unload();
			RaidBossSpawnManager.getInstance().cleanUp();
			DayNightSpawnManager.getInstance().cleanUp();
			for (L2Object obj : L2World.getInstance().getVisibleObjects())
			{
				if ((obj != null) && obj.isNpc())
				{
					final L2Npc target = (L2Npc) obj;
					target.deleteMe();
					final L2Spawn spawn = target.getSpawn();
					if (spawn != null)
					{
						spawn.stopRespawn();
						SpawnTable.getInstance().deleteSpawn(spawn, false);
					}
				}
			}
			// Reload.
			QuestManager.getInstance().reloadAllScripts();
			AdminData.getInstance().broadcastMessageToGMs("NPC unspawn completed!");
		}
		else if (command.startsWith("admin_spawnday"))
		{
			DayNightSpawnManager.getInstance().spawnDayCreatures();
		}
		else if (command.startsWith("admin_spawnnight"))
		{
			DayNightSpawnManager.getInstance().spawnNightCreatures();
		}
		else if (command.startsWith("admin_respawnall") || command.startsWith("admin_spawn_reload"))
		{
			// Unload all scripts.
			QuestManager.getInstance().unloadAllScripts();
			// Delete all spawns.
			AutoSpawnHandler.getInstance().unload();
			RaidBossSpawnManager.getInstance().cleanUp();
			DayNightSpawnManager.getInstance().cleanUp();
			for (L2Object obj : L2World.getInstance().getVisibleObjects())
			{
				if ((obj != null) && obj.isNpc())
				{
					final L2Npc target = (L2Npc) obj;
					target.deleteMe();
					final L2Spawn spawn = target.getSpawn();
					if (spawn != null)
					{
						spawn.stopRespawn();
						SpawnTable.getInstance().deleteSpawn(spawn, false);
					}
				}
			}
			// Reload.
			SpawnTable.getInstance().load();
			RaidBossSpawnManager.getInstance().load();
			AutoSpawnHandler.getInstance().reload();
			SevenSigns.getInstance().spawnSevenSignsNPC();
			QuestManager.getInstance().reloadAllScripts();
			AdminData.getInstance().broadcastMessageToGMs("NPC respawn completed!");
		}
		else if (command.startsWith("admin_spawn_monster") || command.startsWith("admin_spawn"))
		{
			final StringTokenizer st = new StringTokenizer(command, " ");
			try
			{
				final String cmd = st.nextToken();
				final String id = st.nextToken();
				int respawnTime = 60;
				int mobCount = 1;
				if (st.hasMoreTokens())
				{
					mobCount = Integer.parseInt(st.nextToken());
				}
				if (st.hasMoreTokens())
				{
					respawnTime = Integer.parseInt(st.nextToken());
				}
				if (cmd.equalsIgnoreCase("admin_spawn_once"))
				{
					spawnMonster(activeChar, id, respawnTime, mobCount, false);
				}
				else
				{
					spawnMonster(activeChar, id, respawnTime, mobCount, true);
				}
			}
			catch (Exception e)
			{ // Case of wrong or missing monster data
				AdminHtml.showAdminHtml(activeChar, "spawns.htm");
			}
		}
		else if (command.startsWith("admin_list_spawns") || command.startsWith("admin_list_positions"))
		{
			int npcId = 0;
			int teleportIndex = -1;
			try
			{ // admin_list_spawns x[xxxx] x[xx]
				final String[] params = command.split(" ");
				final Pattern pattern = Pattern.compile("[0-9]*");
				final Matcher regexp = pattern.matcher(params[1]);
				if (regexp.matches())
				{
					npcId = Integer.parseInt(params[1]);
				}
				else
				{
					params[1] = params[1].replace('_', ' ');
					npcId = NpcData.getInstance().getTemplateByName(params[1]).getId();
				}
				if (params.length > 2)
				{
					teleportIndex = Integer.parseInt(params[2]);
				}
			}
			catch (Exception e)
			{
				BuilderUtil.sendSysMessage(activeChar, "Command format is //list_spawns <npcId|npc_name> [tele_index]");
			}
			if (command.startsWith("admin_list_positions"))
			{
				findNPCInstances(activeChar, npcId, teleportIndex, true);
			}
			else
			{
				findNPCInstances(activeChar, npcId, teleportIndex, false);
			}
		}
		else if (command.startsWith("admin_topspawncount") || command.startsWith("admin_top_spawn_count"))
		{
			final StringTokenizer st = new StringTokenizer(command, " ");
			st.nextToken();
			int count = 5;
			if (st.hasMoreTokens())
			{
				final String nextToken = st.nextToken();
				if (Util.isDigit(nextToken))
				{
					count = Integer.parseInt(nextToken);
				}
				if (count <= 0)
				{
					return true;
				}
			}
			final Map<Integer, Integer> npcsFound = new HashMap<>();
			for (L2Object obj : L2World.getInstance().getVisibleObjects())
			{
				if (!obj.isNpc())
				{
					continue;
				}
				final int npcId = obj.getId();
				if (npcsFound.containsKey(npcId))
				{
					npcsFound.put(npcId, npcsFound.get(npcId) + 1);
				}
				else
				{
					npcsFound.put(npcId, 1);
				}
			}
			BuilderUtil.sendSysMessage(activeChar, "Top " + count + " spawn count.");
			for (Entry<Integer, Integer> entry : Util.sortByValue(npcsFound, true).entrySet())
			{
				count--;
				if (count < 0)
				{
					break;
				}
				final int npcId = entry.getKey();
				BuilderUtil.sendSysMessage(activeChar, NpcData.getInstance().getTemplate(npcId).getName() + " (" + npcId + "): " + entry.getValue());
			}
		}
		return true;
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
	
	/**
	 * Get all the spawn of a NPC.
	 * @param activeChar
	 * @param npcId
	 * @param teleportIndex
	 * @param showposition
	 */
	private void findNPCInstances(L2PcInstance activeChar, int npcId, int teleportIndex, boolean showposition)
	{
		int index = 0;
		for (L2Spawn spawn : SpawnTable.getInstance().getSpawns(npcId))
		{
			index++;
			final L2Npc npc = spawn.getLastSpawn();
			if (teleportIndex > -1)
			{
				if (teleportIndex == index)
				{
					if (showposition && (npc != null))
					{
						activeChar.teleToLocation(npc.getLocation(), true);
					}
					else
					{
						activeChar.teleToLocation(spawn.getLocation(), true);
					}
				}
			}
			else
			{
				if (showposition && (npc != null))
				{
					activeChar.sendMessage(index + " - " + spawn.getTemplate().getName() + " (" + spawn + "): " + npc.getX() + " " + npc.getY() + " " + npc.getZ());
				}
				else
				{
					activeChar.sendMessage(index + " - " + spawn.getTemplate().getName() + " (" + spawn + "): " + spawn.getX() + " " + spawn.getY() + " " + spawn.getZ());
				}
			}
		}
		
		if (index == 0)
		{
			activeChar.sendMessage(getClass().getSimpleName() + ": No current spawns found.");
		}
	}
	
	private void printSpawn(L2Npc target, int type)
	{
		final int i = target.getId();
		final int x = target.getSpawn().getX();
		final int y = target.getSpawn().getY();
		final int z = target.getSpawn().getZ();
		final int h = target.getSpawn().getHeading();
		switch (type)
		{
			default:
			case 0:
			{
				LOGGER.info("('',1," + i + "," + x + "," + y + "," + z + ",0,0," + h + ",60,0,0),");
				break;
			}
			case 1:
			{
				LOGGER.info("<spawn npcId=\"" + i + "\" x=\"" + x + "\" y=\"" + y + "\" z=\"" + z + "\" heading=\"" + h + "\" respawn=\"0\" />");
				break;
			}
			case 2:
			{
				LOGGER.info("{ " + i + ", " + x + ", " + y + ", " + z + ", " + h + " },");
				break;
			}
		}
	}
	
	private void spawnMonster(L2PcInstance activeChar, String monsterId, int respawnTime, int mobCount, boolean permanent)
	{
		L2Object target = activeChar.getTarget();
		if (target == null)
		{
			target = activeChar;
		}
		
		L2NpcTemplate template;
		if (monsterId.matches("[0-9]*"))
		{
			// First parameter was an ID number
			template = NpcData.getInstance().getTemplate(Integer.parseInt(monsterId));
		}
		else
		{
			// First parameter wasn't just numbers so go by name not ID
			template = NpcData.getInstance().getTemplateByName(monsterId.replace('_', ' '));
		}
		
		if (!Config.FAKE_PLAYERS_ENABLED && template.isFakePlayer())
		{
			activeChar.sendPacket(SystemMessageId.YOUR_TARGET_CANNOT_BE_FOUND);
			return;
		}
		
		try
		{
			final L2Spawn spawn = new L2Spawn(template);
			spawn.setXYZ(target);
			spawn.setAmount(mobCount);
			spawn.setHeading(activeChar.getHeading());
			spawn.setRespawnDelay(respawnTime);
			if (activeChar.getInstanceId() > 0)
			{
				spawn.setInstanceId(activeChar.getInstanceId());
				permanent = false;
			}
			else
			{
				spawn.setInstanceId(0);
			}
			// TODO add checks for GrandBossSpawnManager
			if (RaidBossSpawnManager.getInstance().isDefined(spawn.getId()))
			{
				BuilderUtil.sendSysMessage(activeChar, "You cannot spawn another instance of " + template.getName() + ".");
			}
			else
			{
				if (template.isType("L2RaidBoss"))
				{
					spawn.setRespawnMinDelay(43200);
					spawn.setRespawnMaxDelay(129600);
					RaidBossSpawnManager.getInstance().addNewSpawn(spawn, 0, template.getBaseHpMax(), template.getBaseMpMax(), permanent);
				}
				else
				{
					SpawnTable.getInstance().addNewSpawn(spawn, permanent);
					spawn.init();
				}
				if (!permanent || (respawnTime <= 0))
				{
					spawn.stopRespawn();
				}
				BuilderUtil.sendSysMessage(activeChar, "Created " + template.getName() + " on " + target.getObjectId());
			}
		}
		catch (Exception e)
		{
			activeChar.sendPacket(SystemMessageId.YOUR_TARGET_CANNOT_BE_FOUND);
		}
	}
	
	private void showMonsters(L2PcInstance activeChar, int level, int from)
	{
		final List<L2NpcTemplate> mobs = NpcData.getInstance().getAllMonstersOfLevel(level);
		final int mobsCount = mobs.size();
		final StringBuilder tb = new StringBuilder(500 + (mobsCount * 80));
		tb.append("<html><title>Spawn Monster:</title><body><p> Level : " + level + "<br>Total NPCs : " + mobsCount + "<br>");
		
		// Loop
		int i = from;
		for (int j = 0; (i < mobsCount) && (j < 50); i++, j++)
		{
			tb.append("<a action=\"bypass -h admin_spawn_monster " + mobs.get(i).getId() + "\">" + mobs.get(i).getName() + "</a><br1>");
		}
		
		if (i == mobsCount)
		{
			tb.append("<br><center><button value=\"Back\" action=\"bypass -h admin_show_spawns\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></center></body></html>");
		}
		else
		{
			tb.append("<br><center><button value=\"Next\" action=\"bypass -h admin_spawn_index " + level + " " + i + "\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><button value=\"Back\" action=\"bypass -h admin_show_spawns\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></center></body></html>");
		}
		
		activeChar.sendPacket(new NpcHtmlMessage(tb.toString()));
	}
	
	private void showNpcs(L2PcInstance activeChar, String starting, int from)
	{
		final List<L2NpcTemplate> mobs = NpcData.getInstance().getAllNpcStartingWith(starting);
		final int mobsCount = mobs.size();
		final StringBuilder tb = new StringBuilder(500 + (mobsCount * 80));
		tb.append("<html><title>Spawn Monster:</title><body><p> There are " + mobsCount + " Npcs whose name starts with " + starting + ":<br>");
		
		// Loop
		int i = from;
		for (int j = 0; (i < mobsCount) && (j < 50); i++, j++)
		{
			tb.append("<a action=\"bypass -h admin_spawn_monster " + mobs.get(i).getId() + "\">" + mobs.get(i).getName() + "</a><br1>");
		}
		
		if (i == mobsCount)
		{
			tb.append("<br><center><button value=\"Back\" action=\"bypass -h admin_show_npcs\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></center></body></html>");
		}
		else
		{
			tb.append("<br><center><button value=\"Next\" action=\"bypass -h admin_npc_index " + starting + " " + i + "\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><button value=\"Back\" action=\"bypass -h admin_show_npcs\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></center></body></html>");
		}
		
		activeChar.sendPacket(new NpcHtmlMessage(tb.toString()));
	}
}

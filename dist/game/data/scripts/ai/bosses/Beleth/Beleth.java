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
package ai.bosses.Beleth;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.cache.HtmCache;
import com.l2jmobius.gameserver.data.xml.impl.DoorData;
import com.l2jmobius.gameserver.instancemanager.GrandBossManager;
import com.l2jmobius.gameserver.instancemanager.MapRegionManager;
import com.l2jmobius.gameserver.instancemanager.ZoneManager;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.TeleportWhereType;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.effects.L2EffectType;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.zone.L2ZoneType;
import com.l2jmobius.gameserver.network.serverpackets.DoorStatusUpdate;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jmobius.gameserver.network.serverpackets.PlaySound;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;
import com.l2jmobius.gameserver.network.serverpackets.SpecialCamera;
import com.l2jmobius.gameserver.network.serverpackets.StaticObject;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Beleth's AI.
 * @author Treat, Sahar
 */
public final class Beleth extends AbstractNpcAI
{
	// Status
	private static final int ALIVE = 0;
	private static final int INIT = 1;
	private static final int FIGHT = 2;
	private static final int DEAD = 3;
	// NPCs
	private static final int REAL_BELETH = 29118;
	private static final int FAKE_BELETH = 29119;
	private static final int STONE_COFFIN = 32470;
	private static final int ELF = 29128;
	private static final int WHIRPOOL = 29125;
	// Zones
	private static final L2ZoneType ZONE = ZoneManager.getInstance().getZoneById(12018);
	private static final Location BELETH_SPAWN = new Location(16323, 213059, -9357, 49152);
	// Skills
	private static final SkillHolder BLEED = new SkillHolder(5495, 1);
	private static final SkillHolder FIREBALL = new SkillHolder(5496, 1);
	private static final SkillHolder HORN_OF_RISING = new SkillHolder(5497, 1);
	private static final SkillHolder LIGHTENING = new SkillHolder(5499, 1);
	// Doors
	private static final int DOOR1 = 20240001;
	private static final int DOOR2 = 20240002;
	private static final int DOOR3 = 20240003;
	// Items
	private static final ItemHolder RING = new ItemHolder(10314, 1);
	// Variables
	private L2Npc _camera1;
	private L2Npc _camera2;
	private L2Npc _camera3;
	private L2Npc _camera4;
	private L2Npc _whirpool;
	private L2Npc _beleth;
	private L2Npc _priest;
	private L2Npc _stone;
	private L2PcInstance _killer;
	private int _allowedObjId;
	private int _killedCount;
	private long _lastAttack;
	private final List<L2Npc> _minions = new CopyOnWriteArrayList<>();
	
	private Beleth()
	{
		addEnterZoneId(ZONE.getId());
		registerMobs(REAL_BELETH, FAKE_BELETH);
		addStartNpc(STONE_COFFIN);
		addTalkId(STONE_COFFIN);
		addFirstTalkId(ELF);
		final StatsSet info = GrandBossManager.getInstance().getStatsSet(REAL_BELETH);
		final int status = GrandBossManager.getInstance().getBossStatus(REAL_BELETH);
		if (status == DEAD)
		{
			final long time = info.getLong("respawn_time") - System.currentTimeMillis();
			if (time > 0)
			{
				startQuestTimer("BELETH_UNLOCK", time, null, null);
			}
			else
			{
				GrandBossManager.getInstance().setBossStatus(REAL_BELETH, ALIVE);
			}
		}
		else if (status != ALIVE)
		{
			GrandBossManager.getInstance().setBossStatus(REAL_BELETH, ALIVE);
		}
		DoorData.getInstance().getDoor(DOOR1).openMe();
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		switch (event)
		{
			case "BELETH_UNLOCK":
			{
				GrandBossManager.getInstance().setBossStatus(REAL_BELETH, ALIVE);
				DoorData.getInstance().getDoor(DOOR1).openMe();
				break;
			}
			case "CAST":
			{
				if (!npc.isDead() && !npc.isCastingNow())
				{
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
					npc.doCast(FIREBALL.getSkill());
				}
				break;
			}
			case "SPAWN1":
			{
				ZONE.getCharactersInside().forEach(c ->
				{
					c.disableAllSkills();
					c.setIsInvul(true);
					c.setIsImmobilized(true);
				});
				
				_camera1 = addSpawn(29120, new Location(16323, 213142, -9357));
				_camera2 = addSpawn(29121, new Location(16323, 210741, -9357));
				_camera3 = addSpawn(29122, new Location(16323, 213170, -9357));
				_camera4 = addSpawn(29123, new Location(16323, 214917, -9356));
				
				ZONE.broadcastPacket(new PlaySound(1, "BS07_A", 1, _camera1.getObjectId(), _camera1.getX(), _camera1.getY(), _camera1.getZ()));
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 400, 75, -25, 0, 2500, 0, 0, 1, 0, 0));
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 400, 75, -25, 0, 2500, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN2", 300, null, null);
				break;
			}
			case "SPAWN2":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 1800, -45, -45, 5000, 5000, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN3", 4900, null, null);
				break;
			}
			case "SPAWN3":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 2500, -120, -45, 5000, 5000, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN4", 4900, null, null);
				break;
			}
			case "SPAWN4":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera2, 2200, 130, 0, 0, 1500, -20, 15, 1, 0, 0));
				
				startQuestTimer("SPAWN5", 1400, null, null);
				break;
			}
			case "SPAWN5":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera2, 2300, 100, 0, 2000, 4500, 0, 10, 1, 0, 0));
				
				startQuestTimer("SPAWN6", 2500, null, null);
				break;
			}
			case "SPAWN6":
			{
				final L2DoorInstance door = DoorData.getInstance().getDoor(DOOR1);
				door.closeMe();
				
				ZONE.broadcastPacket(new StaticObject(door, false));
				ZONE.broadcastPacket(new DoorStatusUpdate(door));
				
				startQuestTimer("SPAWN7", 1700, null, null);
				break;
			}
			case "SPAWN7":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera4, 1500, 210, 0, 0, 1500, 0, 0, 1, 0, 0));
				ZONE.broadcastPacket(new SpecialCamera(_camera4, 900, 255, 0, 5000, 6500, 0, 10, 1, 0, 0));
				
				startQuestTimer("SPAWN8", 6000, null, null);
				break;
			}
			case "SPAWN8":
			{
				_whirpool = addSpawn(WHIRPOOL, new Location(16323, 214917, -9356));
				
				ZONE.broadcastPacket(new SpecialCamera(_camera4, 900, 255, 0, 0, 1500, 0, 10, 1, 0, 0));
				
				startQuestTimer("SPAWN9", 1000, null, null);
				break;
			}
			case "SPAWN9":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera4, 1000, 255, 0, 7000, 17000, 0, 25, 1, 0, 0));
				
				startQuestTimer("SPAWN10", 3000, null, null);
				break;
			}
			case "SPAWN10":
			{
				_beleth = addSpawn(REAL_BELETH, new Location(16321, 214211, -9352, 49369));
				_beleth.disableAllSkills();
				_beleth.setIsInvul(true);
				_beleth.setIsImmobilized(true);
				
				startQuestTimer("SPAWN11", 200, null, null);
				break;
			}
			case "SPAWN11":
			{
				ZONE.broadcastPacket(new SocialAction(_beleth.getObjectId(), 1));
				
				for (int i = 0; i < 6; i++)
				{
					final int x = (int) ((150 * Math.cos(i * 1.046666667)) + 16323);
					final int y = (int) ((150 * Math.sin(i * 1.046666667)) + 213059);
					final L2Npc minion = addSpawn(FAKE_BELETH, new Location(x, y, -9357, 49152));
					minion.setShowSummonAnimation(true);
					minion.decayMe();
					
					_minions.add(minion);
				}
				
				startQuestTimer("SPAWN12", 6800, null, null);
				break;
			}
			case "SPAWN12":
			{
				ZONE.broadcastPacket(new SpecialCamera(_beleth, 0, 270, -5, 0, 4000, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN13", 3500, null, null);
				break;
			}
			case "SPAWN13":
			{
				ZONE.broadcastPacket(new SpecialCamera(_beleth, 800, 270, 10, 3000, 6000, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN14", 5000, null, null);
				break;
			}
			case "SPAWN14":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera3, 100, 270, 15, 0, 5000, 0, 0, 1, 0, 0));
				ZONE.broadcastPacket(new SpecialCamera(_camera3, 100, 270, 15, 0, 5000, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN15", 100, null, null);
				break;
			}
			case "SPAWN15":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera3, 100, 270, 15, 3000, 6000, 0, 5, 1, 0, 0));
				
				startQuestTimer("SPAWN16", 1400, null, null);
				break;
			}
			case "SPAWN16":
			{
				_beleth.teleToLocation(BELETH_SPAWN);
				
				startQuestTimer("SPAWN17", 200, null, null);
				break;
			}
			case "SPAWN17":
			{
				ZONE.broadcastPacket(new MagicSkillUse(_beleth, _beleth, 5532, 1, 2000, 0));
				
				startQuestTimer("SPAWN18", 2000, null, null);
				break;
			}
			case "SPAWN18":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera3, 700, 270, 20, 1500, 8000, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN19", 6900, null, null);
				break;
			}
			case "SPAWN19":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera3, 40, 260, 0, 0, 4000, 0, 0, 1, 0, 0));
				
				for (L2Npc fakeBeleth : _minions)
				{
					fakeBeleth.spawnMe();
					fakeBeleth.disableAllSkills();
					fakeBeleth.setIsInvul(true);
					fakeBeleth.setIsImmobilized(true);
				}
				
				startQuestTimer("SPAWN20", 3000, null, null);
				break;
			}
			case "SPAWN20":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera3, 40, 280, 0, 0, 4000, 5, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN21", 3000, null, null);
				break;
			}
			case "SPAWN21":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera3, 5, 250, 5, 0, 13000, 20, 15, 1, 0, 0));
				
				startQuestTimer("SPAWN22", 1000, null, null);
				break;
			}
			case "SPAWN22":
			{
				ZONE.broadcastPacket(new SocialAction(_beleth.getObjectId(), 3));
				
				startQuestTimer("SPAWN23", 4000, null, null);
				break;
			}
			case "SPAWN23":
			{
				ZONE.broadcastPacket(new MagicSkillUse(_beleth, _beleth, 5533, 1, 2000, 0));
				
				startQuestTimer("SPAWN24", 6800, null, null);
				break;
			}
			case "SPAWN24":
			{
				_beleth.deleteMe();
				_beleth = null;
				
				for (L2Npc fakeBeleth : _minions)
				{
					fakeBeleth.deleteMe();
				}
				_minions.clear();
				
				_camera1.deleteMe();
				_camera2.deleteMe();
				_camera3.deleteMe();
				_camera4.deleteMe();
				
				for (L2Character c : ZONE.getCharactersInside())
				{
					c.enableAllSkills();
					c.setIsInvul(false);
					c.setIsImmobilized(false);
				}
				
				_lastAttack = System.currentTimeMillis();
				
				startQuestTimer("CHECK_ATTACK", 60000, null, null);
				
				startQuestTimer("SPAWN25", 60000, null, null);
				break;
			}
			case "SPAWN25":
			{
				_minions.clear();
				
				int a = 0;
				for (int i = 0; i < 16; i++)
				{
					a++;
					
					final int x = (int) ((650 * Math.cos(i * 0.39)) + 16323);
					final int y = (int) ((650 * Math.sin(i * 0.39)) + 213170);
					
					npc = addSpawn(FAKE_BELETH, new Location(x, y, -9357, 49152));
					_minions.add(npc);
					
					if (a >= 2)
					{
						npc.setIsOverloaded(true);
						a = 0;
					}
				}
				
				final int[] xm = new int[16];
				final int[] ym = new int[16];
				for (int i = 0; i < 4; i++)
				{
					xm[i] = (int) ((1700 * Math.cos((i * 1.57) + 0.78)) + 16323);
					ym[i] = (int) ((1700 * Math.sin((i * 1.57) + 0.78)) + 213170);
					
					npc = addSpawn(FAKE_BELETH, new Location(xm[i], ym[i], -9357, 49152));
					npc.setIsImmobilized(true);
					
					_minions.add(npc);
				}
				
				xm[4] = (xm[0] + xm[1]) / 2;
				ym[4] = (ym[0] + ym[1]) / 2;
				npc = addSpawn(FAKE_BELETH, new Location(xm[4], ym[4], -9357, 49152));
				npc.setIsImmobilized(true);
				_minions.add(npc);
				xm[5] = (xm[1] + xm[2]) / 2;
				ym[5] = (ym[1] + ym[2]) / 2;
				npc = addSpawn(FAKE_BELETH, new Location(xm[5], ym[5], -9357, 49152));
				npc.setIsImmobilized(true);
				_minions.add(npc);
				xm[6] = (xm[2] + xm[3]) / 2;
				ym[6] = (ym[2] + ym[3]) / 2;
				npc = addSpawn(FAKE_BELETH, new Location(xm[6], ym[6], -9357, 49152));
				npc.setIsImmobilized(true);
				_minions.add(npc);
				xm[7] = (xm[3] + xm[0]) / 2;
				ym[7] = (ym[3] + ym[0]) / 2;
				npc = addSpawn(FAKE_BELETH, new Location(xm[7], ym[7], -9357, 49152));
				npc.setIsImmobilized(true);
				_minions.add(npc);
				
				xm[8] = (xm[0] + xm[4]) / 2;
				ym[8] = (ym[0] + ym[4]) / 2;
				_minions.add(addSpawn(FAKE_BELETH, new Location(xm[8], ym[8], -9357, 49152)));
				xm[9] = (xm[4] + xm[1]) / 2;
				ym[9] = (ym[4] + ym[1]) / 2;
				_minions.add(addSpawn(FAKE_BELETH, new Location(xm[9], ym[9], -9357, 49152)));
				xm[10] = (xm[1] + xm[5]) / 2;
				ym[10] = (ym[1] + ym[5]) / 2;
				_minions.add(addSpawn(FAKE_BELETH, new Location(xm[10], ym[10], -9357, 49152)));
				xm[11] = (xm[5] + xm[2]) / 2;
				ym[11] = (ym[5] + ym[2]) / 2;
				_minions.add(addSpawn(FAKE_BELETH, new Location(xm[11], ym[11], -9357, 49152)));
				xm[12] = (xm[2] + xm[6]) / 2;
				ym[12] = (ym[2] + ym[6]) / 2;
				_minions.add(addSpawn(FAKE_BELETH, new Location(xm[12], ym[12], -9357, 49152)));
				xm[13] = (xm[6] + xm[3]) / 2;
				ym[13] = (ym[6] + ym[3]) / 2;
				_minions.add(addSpawn(FAKE_BELETH, new Location(xm[13], ym[13], -9357, 49152)));
				xm[14] = (xm[3] + xm[7]) / 2;
				ym[14] = (ym[3] + ym[7]) / 2;
				_minions.add(addSpawn(FAKE_BELETH, new Location(xm[14], ym[14], -9357, 49152)));
				xm[15] = (xm[7] + xm[0]) / 2;
				ym[15] = (ym[7] + ym[0]) / 2;
				_minions.add(addSpawn(FAKE_BELETH, new Location(xm[15], ym[15], -9357, 49152)));
				
				_allowedObjId = _minions.get(getRandom(_minions.size())).getObjectId();
				break;
			}
			case "SPAWN_REAL":
			{
				_beleth = addSpawn(REAL_BELETH, new Location(16323, 213170, -9357, 49152));
				break;
			}
			case "SPAWN26":
			{
				_beleth.doDie(null);
				
				_camera1 = addSpawn(29122, new Location(16323, 213170, -9357));
				_camera1.broadcastPacket(new PlaySound(1, "BS07_D", 1, _camera1.getObjectId(), _camera1.getX(), _camera1.getY(), _camera1.getZ()));
				
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 400, 290, 25, 0, 10000, 0, 0, 1, 0, 0));
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 400, 290, 25, 0, 10000, 0, 0, 1, 0, 0));
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 400, 110, 25, 4000, 10000, 0, 0, 1, 0, 0));
				ZONE.broadcastPacket(new SocialAction(_beleth.getObjectId(), 5));
				
				startQuestTimer("SPAWN27", 4000, null, null);
				break;
			}
			case "SPAWN27":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 400, 295, 25, 4000, 5000, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN28", 4500, null, null);
				break;
			}
			case "SPAWN28":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 400, 295, 10, 4000, 11000, 0, 25, 1, 0, 0));
				
				startQuestTimer("SPAWN29", 9000, null, null);
				break;
			}
			case "SPAWN29":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 250, 90, 25, 0, 1000, 0, 0, 1, 0, 0));
				ZONE.broadcastPacket(new SpecialCamera(_camera1, 250, 90, 25, 0, 10000, 0, 0, 1, 0, 0));
				
				startQuestTimer("SPAWN30", 2000, null, null);
				break;
			}
			case "SPAWN30":
			{
				_priest.spawnMe();
				_beleth.deleteMe();
				
				_camera2 = addSpawn(29121, new Location(14056, 213170, -9357));
				
				startQuestTimer("SPAWN31", 3500, null, null);
				break;
			}
			case "SPAWN31":
			{
				ZONE.broadcastPacket(new SpecialCamera(_camera2, 800, 180, 0, 0, 4000, 0, 10, 1, 0, 0));
				ZONE.broadcastPacket(new SpecialCamera(_camera2, 800, 180, 0, 0, 4000, 0, 10, 1, 0, 0));
				
				final L2DoorInstance door2 = DoorData.getInstance().getDoor(DOOR2);
				door2.openMe();
				
				ZONE.broadcastPacket(new StaticObject(door2, false));
				ZONE.broadcastPacket(new DoorStatusUpdate(door2));
				
				DoorData.getInstance().getDoor(DOOR3).openMe();
				
				_camera1.deleteMe();
				_camera2.deleteMe();
				_whirpool.deleteMe();
				
				for (L2Character c : ZONE.getCharactersInside())
				{
					c.enableAllSkills();
					c.setIsInvul(false);
					c.setIsImmobilized(false);
				}
				break;
			}
			case "CHECK_ATTACK":
			{
				if ((_lastAttack + 900000) < System.currentTimeMillis())
				{
					GrandBossManager.getInstance().setBossStatus(REAL_BELETH, ALIVE);
					for (L2Character charInside : ZONE.getCharactersInside())
					{
						if (charInside != null)
						{
							if (charInside.isNpc())
							{
								charInside.deleteMe();
							}
							else if (charInside.isPlayer())
							{
								charInside.teleToLocation(MapRegionManager.getInstance().getTeleToLocation(charInside, TeleportWhereType.TOWN));
							}
						}
					}
					cancelQuestTimer("CHECK_ATTACK", null, null);
				}
				else
				{
					startQuestTimer("CHECK_ATTACK", 60000, null, null);
				}
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}
	
	@Override
	public String onEnterZone(L2Character character, L2ZoneType zone)
	{
		if (character.isPlayer() && (GrandBossManager.getInstance().getBossStatus(REAL_BELETH) == INIT))
		{
			if (_priest != null)
			{
				_priest.deleteMe();
			}
			if (_stone != null)
			{
				_stone.deleteMe();
			}
			
			GrandBossManager.getInstance().setBossStatus(REAL_BELETH, FIGHT);
			startQuestTimer("SPAWN1", 300000, null, null);
		}
		
		return super.onEnterZone(character, zone);
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance player, Skill skill, L2Object[] targets, boolean isSummon)
	{
		if (!npc.isDead() && (npc.getId() == REAL_BELETH) && !npc.isCastingNow() && skill.hasEffectType(L2EffectType.HEAL) && (getRandom(100) < 80))
		{
			npc.setTarget(player);
			npc.doCast(HORN_OF_RISING.getSkill());
		}
		
		return null;
	}
	
	@Override
	public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		if (!npc.isDead() && !npc.isCastingNow())
		{
			if ((getRandom(100) < 40) && !L2World.getInstance().getVisibleObjectsInRange(npc, L2PcInstance.class, 200).isEmpty())
			{
				npc.doCast(BLEED.getSkill());
				return null;
			}
			npc.setTarget(player);
			npc.doCast(FIREBALL.getSkill());
		}
		
		return null;
	}
	
	@Override
	public String onSpellFinished(L2Npc npc, L2PcInstance player, Skill skill)
	{
		if (!npc.isDead() && !npc.isCastingNow())
		{
			if (!player.isDead())
			{
				final double distance2 = npc.calculateDistance2D(player);
				if ((distance2 > 890) && !npc.isMovementDisabled())
				{
					npc.setTarget(player);
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, player);
					startQuestTimer("CAST", (int) (((distance2 - 890) / (npc.isRunning() ? npc.getRunSpeed() : npc.getWalkSpeed())) * 1000), npc, null);
				}
				else if (distance2 < 890)
				{
					npc.setTarget(player);
					npc.doCast(FIREBALL.getSkill());
				}
				return null;
			}
			if ((getRandom(100) < 40) && !L2World.getInstance().getVisibleObjectsInRange(npc, L2PcInstance.class, 200).isEmpty())
			{
				npc.doCast(LIGHTENING.getSkill());
				return null;
			}
			for (L2PcInstance plr : L2World.getInstance().getVisibleObjectsInRange(npc, L2PcInstance.class, 950))
			{
				npc.setTarget(plr);
				npc.doCast(FIREBALL.getSkill());
				return null;
			}
			((L2Attackable) npc).clearAggroList();
		}
		return null;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		npc.setRunning();
		if (!L2World.getInstance().getVisibleObjectsInRange(npc, L2PcInstance.class, 300).isEmpty() && (getRandom(100) < 60))
		{
			npc.doCast(BLEED.getSkill());
		}
		if (npc.getId() == REAL_BELETH)
		{
			npc.getSpawn().setRespawnDelay(0);
		}
		
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String html;
		if ((_killer != null) && (player.getObjectId() == _killer.getObjectId()))
		{
			_killer = null;
			
			giveItems(player, RING);
			
			html = "32470a.htm";
		}
		else
		{
			html = "32470b.htm";
		}
		
		return HtmCache.getInstance().getHtm(player, "data/html/default/" + html);
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		return onTalk(npc, player);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (getRandom(100) < 40)
		{
			return null;
		}
		
		final double distance = npc.calculateDistance2D(attacker);
		if ((distance > 500) || (getRandom(100) < 80))
		{
			for (L2Npc beleth : _minions)
			{
				if ((beleth != null) && !beleth.isDead() && Util.checkIfInRange(900, beleth, attacker, false) && !beleth.isCastingNow())
				{
					beleth.setTarget(attacker);
					beleth.doCast(FIREBALL.getSkill());
				}
			}
			if ((_beleth != null) && !_beleth.isDead() && Util.checkIfInRange(900, _beleth, attacker, false) && !_beleth.isCastingNow())
			{
				_beleth.setTarget(attacker);
				_beleth.doCast(FIREBALL.getSkill());
			}
		}
		else if (!npc.isDead() && !npc.isCastingNow())
		{
			if (!L2World.getInstance().getVisibleObjectsInRange(npc, L2PcInstance.class, 200).isEmpty())
			{
				npc.doCast(LIGHTENING.getSkill());
				return null;
			}
			((L2Attackable) npc).clearAggroList();
		}
		
		return null;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (npc.getId() == REAL_BELETH)
		{
			cancelQuestTimer("CHECK_ATTACK", null, null);
			
			setBelethKiller(killer);
			GrandBossManager.getInstance().setBossStatus(REAL_BELETH, DEAD);
			final long respawnTime = (Config.BELETH_SPAWN_INTERVAL + getRandom(-Config.BELETH_SPAWN_RANDOM, Config.BELETH_SPAWN_RANDOM)) * 3600000;
			final StatsSet info = GrandBossManager.getInstance().getStatsSet(REAL_BELETH);
			info.set("respawn_time", System.currentTimeMillis() + respawnTime);
			GrandBossManager.getInstance().setStatsSet(REAL_BELETH, info);
			startQuestTimer("BELETH_UNLOCK", respawnTime, null, null);
			
			deleteAll();
			npc.deleteMe();
			
			for (L2Character c : ZONE.getCharactersInside())
			{
				c.disableAllSkills();
				c.setIsInvul(true);
				c.setIsImmobilized(true);
			}
			
			_beleth = addSpawn(REAL_BELETH, new Location(16323, 213170, -9357, 49152));
			_beleth.disableAllSkills();
			_beleth.setIsInvul(true);
			_beleth.setIsImmobilized(true);
			
			_priest = addSpawn(ELF, new Location(_beleth));
			_priest.setShowSummonAnimation(true);
			_priest.decayMe();
			
			_stone = addSpawn(STONE_COFFIN, new Location(12470, 215607, -9381, 49152));
			
			startQuestTimer("SPAWN26", 1000, null, null);
		}
		else if (npc.getObjectId() == _allowedObjId)
		{
			deleteAll();
			
			_killedCount++;
			if (_killedCount >= 5)
			{
				startQuestTimer("SPAWN_REAL", 60000, null, null);
			}
			else
			{
				startQuestTimer("SPAWN25", 60000, null, null);
			}
		}
		
		return null;
	}
	
	private void setBelethKiller(L2PcInstance killer)
	{
		if (killer.getParty() != null)
		{
			if (killer.getParty().getCommandChannel() != null)
			{
				_killer = killer.getParty().getCommandChannel().getLeader();
			}
			else
			{
				_killer = killer.getParty().getLeader();
			}
		}
		else
		{
			_killer = killer;
		}
	}
	
	private void deleteAll()
	{
		_minions.stream().filter(n -> !n.isDead()).forEach(n ->
		{
			n.abortCast();
			n.setTarget(null);
			n.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
			n.deleteMe();
		});
		_allowedObjId = 0;
	}
	
	public static void main(String[] args)
	{
		new Beleth();
	}
}
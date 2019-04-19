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

import java.util.logging.Level;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.geoengine.GeoEngine;
import com.l2jmobius.gameserver.model.L2Spawn;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.interfaces.ILocational;

import ai.AbstractNpcAI;

/**
 * @author HorridoJoho, janiko, FinalDestination, lion
 */
public final class AltarsOfSacrifice extends AbstractNpcAI
{
	private final class Altar
	{
		private final ILocational _middlePoint;
		private final int[] _bossNpcIds;
		private L2Npc _spawnedBoss;
		
		protected Altar(ILocational middlePoint, int... bossNpcIds)
		{
			_middlePoint = middlePoint;
			_bossNpcIds = bossNpcIds;
			_spawnedBoss = null;
		}
		
		protected void spawnBoss() throws Exception
		{
			if (!hasBosses() || (_spawnedBoss != null))
			{
				throw new IllegalStateException();
			}
			
			final L2Spawn spawn = new L2Spawn(_bossNpcIds[Rnd.get(_bossNpcIds.length)]);
			spawn.setAmount(1);
			spawn.setHeading(Rnd.get(65536));
			
			final int radius = Rnd.get(BOSS_MIN_SPAWN_RADIUS, BOSS_MAX_SPAWN_RADIUS);
			final double angleRadians = Rnd.nextDouble() * 2 * Math.PI;
			final int spawnX = (int) (radius * Math.cos(angleRadians)) + _middlePoint.getX();
			final int spawnY = (int) (radius * Math.sin(angleRadians)) + _middlePoint.getY();
			
			spawn.setXYZ(spawnX, spawnY, GeoEngine.getInstance().getHeight(spawnX, spawnY, _middlePoint.getZ()));
			spawn.stopRespawn();
			_spawnedBoss = spawn.doSpawn(false);
		}
		
		protected void despawnBoss()
		{
			if (_spawnedBoss != null)
			{
				_spawnedBoss.deleteMe();
				_spawnedBoss = null;
			}
		}
		
		protected void unload()
		{
			despawnBoss();
		}
		
		protected boolean hasBosses()
		{
			return _bossNpcIds.length > 0;
		}
		
		protected boolean isBossFighting()
		{
			return (_spawnedBoss != null) && _spawnedBoss.isInCombat();
		}
	}
	
	private static final String EVT_SPAWN_BOSS_PRE = "spawnboss";
	private static final String EVT_DESPAWN_BOSS_PRE = "despawnboss";
	private static final int BOSS_MIN_SPAWN_RADIUS = 250;
	private static final int BOSS_MAX_SPAWN_RADIUS = 500;
	// every 240 minutes/4 hours, altars change
	private static final long ALTAR_STATE_CHANGE_DELAY = 240 * 60 * 1000;
	
	// disabling formatter here to make this easily readable
	// @formatter:off
	private final Altar[] _altars = new Altar[]
	{
		// TalkingIsland
		new Altar
		(
			new Location(-92481, 244812, -3505)
		),
		// Elven
		new Altar
		(
			new Location(40241, 53974, -3262)
		),
		// DarkElven
		new Altar
		(
			new Location(1851, 21697, -3305),
			25750
		),
		// Dwarven
		new Altar
		(
			new Location(130133, -180968, -3271),
			25800, 25782
		),
		// Orc
		new Altar
		(
			new Location(-45329, -118327, -166),
			25779
		),
		// Kamael
		new Altar
		(
			new Location(-104031, 45059, -1417)
		),
		// Oren
		new Altar
		(
			new Location(80188, 47037, -3109),
			25767, 25770
		),
		// Gludin
		new Altar
		(
			new Location(-86620, 151536, -3018),
			25735, 25738, 25741
		),
		// Gludio
		new Altar
		(
			new Location(-14152, 120674, -2935),
			25744, 25747
		),
		// Dion
		new Altar
		(
			new Location(16715, 148320, -3210),
			25753, 25754, 25757
		),
		// Heine
		new Altar
		(
			new Location(120123, 219164, -3319),
			25773, 25776
		),
		// Giran
		new Altar
		(
			new Location(80712, 142538, -3487),
			25760, 25763, 25766
		),
		// Aden
		new Altar
		(
			new Location(152720, 24714, -2083),
			25793, 25794, 25797
		),
		// Rune
		new Altar
		(
			new Location(28010, -49175, -1278)
		),
		// Goddard
		new Altar
		(
			new Location(152274, -57706, -3383),
			25787, 25790
		),
		// Schutgart
		new Altar
		(
			new Location(82066, -139418, -2220),
			25784
		),
		// Primeval
		new Altar
		(
			new Location(10998, -24068, -3603)
		),
		// Dragon Valley
		new Altar
		(
			new Location(69592, 118694, -3417)
		)
	};
	// @formatter:on
	
	public static void main(String[] args)
	{
		new AltarsOfSacrifice();
	}
	
	private AltarsOfSacrifice()
	{
		for (int i = 0; i < _altars.length; ++i)
		{
			if (_altars[i].hasBosses())
			{
				startQuestTimer(makeSpawnBossEvt(i), ALTAR_STATE_CHANGE_DELAY, null, null);
			}
		}
	}
	
	private String makeSpawnBossEvt(int altarIndex)
	{
		return EVT_SPAWN_BOSS_PRE + altarIndex;
	}
	
	private String makeDespawnBossEvt(int altarIndex)
	{
		return EVT_DESPAWN_BOSS_PRE + altarIndex;
	}
	
	private boolean isSpawnBossEvt(String event)
	{
		return event.startsWith(EVT_SPAWN_BOSS_PRE);
	}
	
	private boolean isDespawnBossEvt(String event)
	{
		return event.startsWith(EVT_DESPAWN_BOSS_PRE);
	}
	
	private int getSpawnBossIndex(String event)
	{
		return Integer.parseInt(event.substring(EVT_SPAWN_BOSS_PRE.length()));
	}
	
	private int getDespawnBossIndex(String event)
	{
		return Integer.parseInt(event.substring(EVT_DESPAWN_BOSS_PRE.length()));
	}
	
	@Override
	public boolean unload(boolean removeFromList)
	{
		LOGGER.info(getClass().getSimpleName() + ": Unloading altars due to script unloading.");
		
		for (Altar altar : _altars)
		{
			altar.unload();
		}
		
		return super.unload(removeFromList);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (isSpawnBossEvt(event))
		{
			final int altarIndex = getSpawnBossIndex(event);
			final Altar altar = _altars[altarIndex];
			try
			{
				altar.spawnBoss();
				startQuestTimer(makeDespawnBossEvt(altarIndex), ALTAR_STATE_CHANGE_DELAY, null, null);
			}
			catch (Exception e)
			{
				LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": Failed to spawn altar boss.", e);
				// let's try again to spawn it in 5 seconds
				startQuestTimer(event, 5000, null, null);
			}
		}
		else if (isDespawnBossEvt(event))
		{
			final int altarIndex = getDespawnBossIndex(event);
			final Altar altar = _altars[altarIndex];
			if (altar.isBossFighting())
			{
				// periodically check if the altar boss is fighting, only despawn when not fighting anymore
				startQuestTimer(event, 5000, null, null);
			}
			else
			{
				altar.despawnBoss();
				startQuestTimer(makeSpawnBossEvt(altarIndex), ALTAR_STATE_CHANGE_DELAY, null, null);
			}
		}
		
		return null;
	}
}

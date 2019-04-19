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
package ai.areas.Gracia.vehicles.KeucereusNorthController;

import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.VehiclePathPoint;

import ai.areas.Gracia.vehicles.AirShipController;

public final class KeucereusNorthController extends AirShipController
{
	private static final int DOCK_ZONE = 50602;
	private static final int LOCATION = 100;
	private static final int CONTROLLER_ID = 32606;
	
	private static final VehiclePathPoint[] ARRIVAL =
	{
		new VehiclePathPoint(-183218, 239494, 2500, 280, 2000),
		new VehiclePathPoint(-183218, 239494, 1336, 280, 2000)
	};
	
	private static final VehiclePathPoint[] DEPART =
	{
		new VehiclePathPoint(-183218, 239494, 1700, 280, 2000),
		new VehiclePathPoint(-181974, 235358, 1700, 280, 2000)
	};
	
	private static final VehiclePathPoint[][] TELEPORTS =
	{
		{
			new VehiclePathPoint(-183218, 239494, 1700, 280, 2000),
			new VehiclePathPoint(-181974, 235358, 1700, 280, 2000),
			new VehiclePathPoint(-186373, 234000, 2500, 0, 0)
		},
		{
			new VehiclePathPoint(-183218, 239494, 1700, 280, 2000),
			new VehiclePathPoint(-181974, 235358, 1700, 280, 2000),
			new VehiclePathPoint(-206692, 220997, 3000, 0, 0)
		},
		{
			new VehiclePathPoint(-183218, 239494, 1700, 280, 2000),
			new VehiclePathPoint(-181974, 235358, 1700, 280, 2000),
			new VehiclePathPoint(-235693, 248843, 5100, 0, 0)
		}
	};
	
	private static final int[] FUEL =
	{
		0,
		50,
		100
	};
	
	public KeucereusNorthController()
	{
		addStartNpc(CONTROLLER_ID);
		addFirstTalkId(CONTROLLER_ID);
		addTalkId(CONTROLLER_ID);
		
		_dockZone = DOCK_ZONE;
		addEnterZoneId(DOCK_ZONE);
		addExitZoneId(DOCK_ZONE);
		
		_shipSpawnX = -184145;
		_shipSpawnY = 242373;
		_shipSpawnZ = 3000;
		
		_oustLoc = new Location(-183900, 239384, 1320);
		
		_locationId = LOCATION;
		_arrivalPath = ARRIVAL;
		_departPath = DEPART;
		_teleportsTable = TELEPORTS;
		_fuelTable = FUEL;
		
		_movie = Movie.LAND_KSERTH_B;
		
		validityCheck();
	}
}

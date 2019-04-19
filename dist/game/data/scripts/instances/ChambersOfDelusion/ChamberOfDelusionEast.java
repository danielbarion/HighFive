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
package instances.ChambersOfDelusion;

import com.l2jmobius.gameserver.model.Location;

/**
 * Chamber of Delusion East.
 * @author GKR
 */
public final class ChamberOfDelusionEast extends Chamber
{
	// NPCs
	private static final int ENTRANCE_GATEKEEPER = 32658;
	private static final int ROOM_GATEKEEPER_FIRST = 32664;
	private static final int ROOM_GATEKEEPER_LAST = 32668;
	private static final int AENKINEL = 25690;
	private static final int BOX = 18838;
	
	// Misc
	private static final Location[] ENTER_POINTS = new Location[]
	{
		new Location(-122368, -218972, -6720),
		new Location(-122352, -218044, -6720),
		new Location(-122368, -220220, -6720),
		new Location(-121440, -218444, -6720),
		new Location(-121424, -220124, -6720), // Raid room
	};
	private static final int INSTANCEID = 127;
	
	private ChamberOfDelusionEast()
	{
		super(ChamberOfDelusionEast.class.getSimpleName(), INSTANCEID, ENTRANCE_GATEKEEPER, ROOM_GATEKEEPER_FIRST, ROOM_GATEKEEPER_LAST, AENKINEL, BOX);
		ROOM_ENTER_POINTS = ENTER_POINTS;
	}
	
	public static void main(String[] args)
	{
		new ChamberOfDelusionEast();
	}
}
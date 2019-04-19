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
package quests.TerritoryWarScripts;

import com.l2jmobius.gameserver.network.NpcStringId;

/**
 * For the Sake of the Territory - Gludio (717)
 * @author Gigiikun
 */
public final class Q00717_ForTheSakeOfTheTerritoryGludio extends TerritoryWarSuperClass
{
	public Q00717_ForTheSakeOfTheTerritoryGludio()
	{
		super(717);
		CATAPULT_ID = 36499;
		TERRITORY_ID = 81;
		LEADER_IDS = new int[]
		{
			36508,
			36510,
			36513,
			36591
		};
		GUARD_IDS = new int[]
		{
			36509,
			36511,
			36512
		};
		npcString = new NpcStringId[]
		{
			NpcStringId.THE_CATAPULT_OF_GLUDIO_HAS_BEEN_DESTROYED
		};
		registerKillIds();
	}
}

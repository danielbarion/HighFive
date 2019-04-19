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
package quests.Q00069_SagaOfTheTrickster;

import com.l2jmobius.gameserver.model.Location;

import quests.AbstractSagaQuest;

/**
 * Saga of the Trickster (69)
 * @author Emperorc
 */
public class Q00069_SagaOfTheTrickster extends AbstractSagaQuest
{
	public Q00069_SagaOfTheTrickster()
	{
		super(69);
		_npc = new int[]
		{
			32138,
			31270,
			31282,
			32228,
			32263,
			31646,
			31649,
			31653,
			31654,
			31655,
			31659,
			31283
		};
		Items = new int[]
		{
			7080,
			9761,
			7081,
			9742,
			9724,
			9727,
			9730,
			9733,
			9736,
			9739,
			9718,
			0
		};
		Mob = new int[]
		{
			27333,
			27334,
			27335
		};
		classid = new int[]
		{
			134
		};
		prevclass = new int[]
		{
			0x82
		};
		npcSpawnLocations = new Location[]
		{
			new Location(164014, -74733, -3093),
			new Location(124355, 82155, -2803),
			new Location(124376, 82127, -2796)
		};
		Text = new String[]
		{
			"PLAYERNAME! Pursued to here! However, I jumped out of the Banshouren boundaries! You look at the giant as the sign of power!",
			"... Oh ... good! So it was ... let's begin!",
			"I do not have the patience ..! I have been a giant force ...! Cough chatter ah ah ah!",
			"Paying homage to those who disrupt the orderly will be PLAYERNAME's death!",
			"Now, my soul freed from the shackles of the millennium, Halixia, to the back side I come ...",
			"Why do you interfere others' battles?",
			"This is a waste of time.. Say goodbye...!",
			"...That is the enemy",
			"...Goodness! PLAYERNAME you are still looking?",
			"PLAYERNAME ... Not just to whom the victory. Only personnel involved in the fighting are eligible to share in the victory.",
			"Your sword is not an ornament. Don't you think, PLAYERNAME?",
			"Goodness! I no longer sense a battle there now.",
			"let...",
			"Only engaged in the battle to bar their choice. Perhaps you should regret.",
			"The human nation was foolish to try and fight a giant's strength.",
			"Must...Retreat... Too...Strong.",
			"PLAYERNAME. Defeat...by...retaining...and...Mo...Hacker",
			"....! Fight...Defeat...It...Fight...Defeat...It..."
		};
		registerNPCs();
	}
}

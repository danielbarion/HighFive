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
package quests.Q00075_SagaOfTheTitan;

import com.l2jmobius.gameserver.model.Location;

import quests.AbstractSagaQuest;

/**
 * Saga of the Titan (75)
 * @author Emperorc
 */
public class Q00075_SagaOfTheTitan extends AbstractSagaQuest
{
	public Q00075_SagaOfTheTitan()
	{
		super(75);
		_npc = new int[]
		{
			31327,
			31624,
			31289,
			31290,
			31607,
			31646,
			31649,
			31651,
			31654,
			31655,
			31658,
			31290
		};
		Items = new int[]
		{
			7080,
			7539,
			7081,
			7490,
			7273,
			7304,
			7335,
			7366,
			7397,
			7428,
			7098,
			0
		};
		Mob = new int[]
		{
			27292,
			27224,
			27283
		};
		classid = new int[]
		{
			113
		};
		prevclass = new int[]
		{
			0x2e
		};
		npcSpawnLocations = new Location[]
		{
			new Location(119518, -28658, -3811),
			new Location(181215, 36676, -4812),
			new Location(181227, 36703, -4816)
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

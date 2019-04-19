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
package quests.Q00089_SagaOfTheMysticMuse;

import com.l2jmobius.gameserver.model.Location;

import quests.AbstractSagaQuest;

/**
 * Saga of the Mystic Muse (89)
 * @author Emperorc
 */
public class Q00089_SagaOfTheMysticMuse extends AbstractSagaQuest
{
	public Q00089_SagaOfTheMysticMuse()
	{
		super(89);
		_npc = new int[]
		{
			30174,
			31627,
			31283,
			31283,
			31643,
			31646,
			31648,
			31651,
			31654,
			31655,
			31658,
			31283
		};
		Items = new int[]
		{
			7080,
			7530,
			7081,
			7504,
			7287,
			7318,
			7349,
			7380,
			7411,
			7442,
			7083,
			0
		};
		Mob = new int[]
		{
			27251,
			27238,
			27255
		};
		classid = new int[]
		{
			103
		};
		prevclass = new int[]
		{
			0x1b
		};
		npcSpawnLocations = new Location[]
		{
			new Location(119518, -28658, -3811),
			new Location(181227, 36703, -4816),
			new Location(181215, 36676, -4812)
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

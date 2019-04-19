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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mobius
 */
public class TerritoryWarSuperClassLoader
{
	private static Map<Integer, TerritoryWarSuperClass> _forTheSakeScripts = new HashMap<>();
	private static Map<Integer, TerritoryWarSuperClass> _protectTheScripts = new HashMap<>();
	private static Map<Integer, TerritoryWarSuperClass> _killTheScripts = new HashMap<>();
	
	public static Map<Integer, TerritoryWarSuperClass> getForTheSakeScripts()
	{
		return _forTheSakeScripts;
	}
	
	public static Map<Integer, TerritoryWarSuperClass> getProtectTheScripts()
	{
		return _protectTheScripts;
	}
	
	public static Map<Integer, TerritoryWarSuperClass> getKillTheScripts()
	{
		return _killTheScripts;
	}
	
	public static void main(String[] args)
	{
		// initialize superclass
		new TerritoryWarSuperClass(-1);
		
		// initialize subclasses
		// "For The Sake" quests
		final TerritoryWarSuperClass gludio = new Q00717_ForTheSakeOfTheTerritoryGludio();
		_forTheSakeScripts.put(gludio.TERRITORY_ID, gludio);
		final TerritoryWarSuperClass dion = new Q00718_ForTheSakeOfTheTerritoryDion();
		_forTheSakeScripts.put(dion.TERRITORY_ID, dion);
		final TerritoryWarSuperClass giran = new Q00719_ForTheSakeOfTheTerritoryGiran();
		_forTheSakeScripts.put(giran.TERRITORY_ID, giran);
		final TerritoryWarSuperClass oren = new Q00720_ForTheSakeOfTheTerritoryOren();
		_forTheSakeScripts.put(oren.TERRITORY_ID, oren);
		final TerritoryWarSuperClass aden = new Q00721_ForTheSakeOfTheTerritoryAden();
		_forTheSakeScripts.put(aden.TERRITORY_ID, aden);
		final TerritoryWarSuperClass innadril = new Q00722_ForTheSakeOfTheTerritoryInnadril();
		_forTheSakeScripts.put(innadril.TERRITORY_ID, innadril);
		final TerritoryWarSuperClass goddard = new Q00723_ForTheSakeOfTheTerritoryGoddard();
		_forTheSakeScripts.put(goddard.TERRITORY_ID, goddard);
		final TerritoryWarSuperClass rune = new Q00724_ForTheSakeOfTheTerritoryRune();
		_forTheSakeScripts.put(rune.TERRITORY_ID, rune);
		final TerritoryWarSuperClass schuttgart = new Q00725_ForTheSakeOfTheTerritorySchuttgart();
		_forTheSakeScripts.put(schuttgart.TERRITORY_ID, schuttgart);
		// "Protect the" quests
		final TerritoryWarSuperClass catapult = new Q00729_ProtectTheTerritoryCatapult();
		_protectTheScripts.put(catapult.getId(), catapult);
		final TerritoryWarSuperClass supplies = new Q00730_ProtectTheSuppliesSafe();
		_protectTheScripts.put(supplies.getId(), supplies);
		final TerritoryWarSuperClass military = new Q00731_ProtectTheMilitaryAssociationLeader();
		_protectTheScripts.put(military.getId(), military);
		final TerritoryWarSuperClass religious = new Q00732_ProtectTheReligiousAssociationLeader();
		_protectTheScripts.put(religious.getId(), religious);
		final TerritoryWarSuperClass economic = new Q00733_ProtectTheEconomicAssociationLeader();
		_protectTheScripts.put(economic.getId(), economic);
		// "Kill" quests
		final TerritoryWarSuperClass knights = new Q00734_PierceThroughAShield();
		for (int i : knights.CLASS_IDS)
		{
			_killTheScripts.put(i, knights);
		}
		final TerritoryWarSuperClass warriors = new Q00735_MakeSpearsDull();
		for (int i : warriors.CLASS_IDS)
		{
			_killTheScripts.put(i, warriors);
		}
		final TerritoryWarSuperClass wizards = new Q00736_WeakenTheMagic();
		for (int i : wizards.CLASS_IDS)
		{
			_killTheScripts.put(i, wizards);
		}
		final TerritoryWarSuperClass priests = new Q00737_DenyBlessings();
		for (int i : priests.CLASS_IDS)
		{
			_killTheScripts.put(i, priests);
		}
		final TerritoryWarSuperClass keys = new Q00738_DestroyKeyTargets();
		for (int i : keys.CLASS_IDS)
		{
			_killTheScripts.put(i, keys);
		}
	}
}

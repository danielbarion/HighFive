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

/**
 * Protect the Supplies Safe (730)
 * @author Gigiikun
 */
public final class Q00730_ProtectTheSuppliesSafe extends TerritoryWarSuperClass
{
	public Q00730_ProtectTheSuppliesSafe()
	{
		super(730);
		NPC_IDS = new int[]
		{
			36591,
			36592,
			36593,
			36594,
			36595,
			36596,
			36597,
			36598,
			36599
		};
		addAttackId(NPC_IDS);
	}
	
	@Override
	public int getTerritoryIdForThisNPCId(int npcId)
	{
		return npcId - 36510;
	}
}

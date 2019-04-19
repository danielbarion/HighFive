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
 * Protect the Economic Association Leader (733)
 * @author Gigiikun
 */
public final class Q00733_ProtectTheEconomicAssociationLeader extends TerritoryWarSuperClass
{
	public Q00733_ProtectTheEconomicAssociationLeader()
	{
		super(733);
		NPC_IDS = new int[]
		{
			36513,
			36519,
			36525,
			36531,
			36537,
			36543,
			36549,
			36555,
			36561
		};
		addAttackId(NPC_IDS);
	}
	
	@Override
	public int getTerritoryIdForThisNPCId(int npcId)
	{
		return 81 + ((npcId - 36513) / 6);
	}
}

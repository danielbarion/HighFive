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
 * Protect the Religious Association Leader (732)
 * @author Gigiikun
 */
public final class Q00732_ProtectTheReligiousAssociationLeader extends TerritoryWarSuperClass
{
	public Q00732_ProtectTheReligiousAssociationLeader()
	{
		super(732);
		NPC_IDS = new int[]
		{
			36510,
			36516,
			36522,
			36528,
			36534,
			36540,
			36546,
			36552,
			36558
		};
		addAttackId(NPC_IDS);
	}
	
	@Override
	public int getTerritoryIdForThisNPCId(int npcId)
	{
		return 81 + ((npcId - 36510) / 6);
	}
}

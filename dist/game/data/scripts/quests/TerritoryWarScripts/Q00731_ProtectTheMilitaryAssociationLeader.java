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
 * Protect the Military Association Leader (731)
 * @author Gigiikun
 */
public final class Q00731_ProtectTheMilitaryAssociationLeader extends TerritoryWarSuperClass
{
	public Q00731_ProtectTheMilitaryAssociationLeader()
	{
		super(731);
		NPC_IDS = new int[]
		{
			36508,
			36514,
			36520,
			36526,
			36532,
			36538,
			36544,
			36550,
			36556
		};
		addAttackId(NPC_IDS);
	}
	
	@Override
	public int getTerritoryIdForThisNPCId(int npcId)
	{
		return 81 + ((npcId - 36508) / 6);
	}
}

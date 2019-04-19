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
package com.l2jmobius.gameserver.enums;

import com.l2jmobius.gameserver.model.items.type.CrystalType;

/**
 * @author UnAfraid
 */
public enum ItemGrade
{
	NONE,
	D,
	C,
	B,
	A,
	S;
	
	public static ItemGrade valueOf(CrystalType type)
	{
		switch (type)
		{
			case NONE:
			{
				return NONE;
			}
			case D:
			{
				return D;
			}
			case C:
			{
				return C;
			}
			case B:
			{
				return B;
			}
			case A:
			{
				return A;
			}
			case S:
			case S80:
			case S84:
			{
				return S;
			}
		}
		return null;
	}
}

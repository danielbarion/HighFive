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
package com.l2jmobius.gameserver.model.items.type;

/**
 * EtcItem Type enumerated.
 */
public enum EtcItemType implements ItemType
{
	NONE,
	ARROW,
	POTION,
	SCRL_ENCHANT_WP,
	SCRL_ENCHANT_AM,
	SCROLL,
	RECIPE,
	MATERIAL,
	PET_COLLAR,
	CASTLE_GUARD,
	LOTTO,
	RACE_TICKET,
	DYE,
	SEED,
	CROP,
	MATURECROP,
	HARVEST,
	SEED2,
	TICKET_OF_LORD,
	LURE,
	BLESS_SCRL_ENCHANT_WP,
	BLESS_SCRL_ENCHANT_AM,
	COUPON,
	ELIXIR,
	SCRL_ENCHANT_ATTR,
	BOLT,
	SCRL_INC_ENCHANT_PROP_WP,
	SCRL_INC_ENCHANT_PROP_AM,
	ANCIENT_CRYSTAL_ENCHANT_WP,
	ANCIENT_CRYSTAL_ENCHANT_AM,
	RUNE_SELECT,
	RUNE,
	
	// L2J CUSTOM, BACKWARD COMPATIBILITY
	SHOT;
	
	/**
	 * @return the ID of the item after applying the mask.
	 */
	@Override
	public int mask()
	{
		return 0;
	}
}

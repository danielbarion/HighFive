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
package com.l2jmobius.gameserver.model.actor.transform;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.gameserver.data.xml.impl.SkillTreesData;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.AdditionalItemHolder;
import com.l2jmobius.gameserver.model.holders.AdditionalSkillHolder;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.interfaces.IIdentifiable;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.model.stats.Stats;
import com.l2jmobius.gameserver.network.serverpackets.ExBasicActionList;

/**
 * @author UnAfraid
 */
public final class Transform implements IIdentifiable
{
	private final int _id;
	private final int _displayId;
	private final TransformType _type;
	private final boolean _canSwim;
	private final int _spawnHeight;
	private final boolean _canAttack;
	private final String _name;
	private final String _title;
	
	private TransformTemplate _maleTemplate;
	private TransformTemplate _femaleTemplate;
	
	public Transform(StatsSet set)
	{
		_id = set.getInt("id");
		_displayId = set.getInt("displayId", _id);
		_type = set.getEnum("type", TransformType.class, TransformType.COMBAT);
		_canSwim = set.getInt("can_swim", 0) == 1;
		_canAttack = set.getInt("normal_attackable", 1) == 1;
		_spawnHeight = set.getInt("spawn_height", 0);
		_name = set.getString("setName", null);
		_title = set.getString("setTitle", null);
	}
	
	/**
	 * Gets the transformation ID.
	 * @return the transformation ID
	 */
	@Override
	public int getId()
	{
		return _id;
	}
	
	public int getDisplayId()
	{
		return _displayId;
	}
	
	public TransformType getType()
	{
		return _type;
	}
	
	public boolean canSwim()
	{
		return _canSwim;
	}
	
	public boolean canAttack()
	{
		return _canAttack;
	}
	
	public int getSpawnHeight()
	{
		return _spawnHeight;
	}
	
	/**
	 * @return name that's going to be set to the player while is transformed with current transformation
	 */
	public String getName()
	{
		return _name;
	}
	
	/**
	 * @return title that's going to be set to the player while is transformed with current transformation
	 */
	public String getTitle()
	{
		return _title;
	}
	
	public TransformTemplate getTemplate(L2PcInstance player)
	{
		return player != null ? (player.getAppearance().getSex() ? _femaleTemplate : _maleTemplate) : null;
	}
	
	public void setTemplate(boolean male, TransformTemplate template)
	{
		if (male)
		{
			_maleTemplate = template;
		}
		else
		{
			_femaleTemplate = template;
		}
	}
	
	/**
	 * @return {@code true} if transform type is mode change, {@code false} otherwise
	 */
	public boolean isStance()
	{
		return _type == TransformType.MODE_CHANGE;
	}
	
	/**
	 * @return {@code true} if transform type is combat, {@code false} otherwise
	 */
	public boolean isCombat()
	{
		return _type == TransformType.COMBAT;
	}
	
	/**
	 * @return {@code true} if transform type is non combat, {@code false} otherwise
	 */
	public boolean isNonCombat()
	{
		return _type == TransformType.NON_COMBAT;
	}
	
	/**
	 * @return {@code true} if transform type is flying, {@code false} otherwise
	 */
	public boolean isFlying()
	{
		return _type == TransformType.FLYING;
	}
	
	/**
	 * @return {@code true} if transform type is cursed, {@code false} otherwise
	 */
	public boolean isCursed()
	{
		return _type == TransformType.CURSED;
	}
	
	/**
	 * @return {@code true} if transform type is raiding, {@code false} otherwise
	 */
	public boolean isRiding()
	{
		return _type == TransformType.RIDING_MODE;
	}
	
	/**
	 * @return {@code true} if transform type is pure stat, {@code false} otherwise
	 */
	public boolean isPureStats()
	{
		return _type == TransformType.PURE_STAT;
	}
	
	public double getCollisionHeight(L2PcInstance player)
	{
		final TransformTemplate template = getTemplate(player);
		return template != null ? template.getCollisionHeight() : player.getCollisionHeight();
	}
	
	public double getCollisionRadius(L2PcInstance player)
	{
		final TransformTemplate template = getTemplate(player);
		return template != null ? template.getCollisionRadius() : player.getCollisionRadius();
	}
	
	public int getBaseAttackRange(L2PcInstance player)
	{
		final TransformTemplate template = getTemplate(player);
		return template != null ? template.getBaseAttackRange() : player.getTemplate().getBaseAttackRange();
	}
	
	public void onTransform(L2PcInstance player)
	{
		final TransformTemplate template = getTemplate(player);
		if (template == null)
		{
			return;
		}
		
		// Start flying.
		if (isFlying())
		{
			player.setIsFlying(true);
		}
		
		if (_name != null)
		{
			player.getAppearance().setVisibleName(_name);
		}
		if (_title != null)
		{
			player.getAppearance().setVisibleTitle(_title);
		}
		
		// Set inventory blocks if needed.
		if (!template.getAdditionalItems().isEmpty())
		{
			final List<Integer> allowed = new ArrayList<>();
			final List<Integer> notAllowed = new ArrayList<>();
			for (AdditionalItemHolder holder : template.getAdditionalItems())
			{
				if (holder.isAllowedToUse())
				{
					allowed.add(holder.getId());
				}
				else
				{
					notAllowed.add(holder.getId());
				}
			}
			
			if (!allowed.isEmpty())
			{
				final int[] items = new int[allowed.size()];
				for (int i = 0; i < items.length; i++)
				{
					items[i] = allowed.get(i);
				}
				player.getInventory().setInventoryBlock(items, 1);
			}
			
			if (!notAllowed.isEmpty())
			{
				final int[] items = new int[notAllowed.size()];
				for (int i = 0; i < items.length; i++)
				{
					items[i] = notAllowed.get(i);
				}
				player.getInventory().setInventoryBlock(items, 2);
			}
		}
		
		// Send basic action list.
		if (template.hasBasicActionList())
		{
			player.sendPacket(template.getBasicActionList());
		}
	}
	
	public void onUntransform(L2PcInstance player)
	{
		final TransformTemplate template = getTemplate(player);
		if (template == null)
		{
			return;
		}
		
		// Stop flying.
		if (isFlying())
		{
			player.setIsFlying(false);
		}
		
		if (_name != null)
		{
			player.getAppearance().setVisibleName(null);
		}
		if (_title != null)
		{
			player.getAppearance().setVisibleTitle(null);
		}
		
		// Remove common skills.
		if (!template.getSkills().isEmpty())
		{
			for (SkillHolder holder : template.getSkills())
			{
				final Skill skill = holder.getSkill();
				if (!SkillTreesData.getInstance().isSkillAllowed(player, skill))
				{
					player.removeSkill(skill, false, skill.isPassive());
				}
			}
		}
		
		// Remove skills depending on level.
		if (!template.getAdditionalSkills().isEmpty())
		{
			for (AdditionalSkillHolder holder : template.getAdditionalSkills())
			{
				final Skill skill = holder.getSkill();
				if ((player.getLevel() >= holder.getMinLevel()) && !SkillTreesData.getInstance().isSkillAllowed(player, skill))
				{
					player.removeSkill(skill, false, skill.isPassive());
				}
			}
		}
		
		// Remove transformation skills.
		player.removeAllTransformSkills();
		
		// Remove inventory blocks if needed.
		if (!template.getAdditionalItems().isEmpty())
		{
			player.getInventory().unblock();
		}
		
		player.sendPacket(ExBasicActionList.STATIC_PACKET);
	}
	
	public void onLevelUp(L2PcInstance player)
	{
		final TransformTemplate template = getTemplate(player);
		// Add skills depending on level.
		if ((template != null) && !template.getAdditionalSkills().isEmpty())
		{
			for (AdditionalSkillHolder holder : template.getAdditionalSkills())
			{
				if ((player.getLevel() >= holder.getMinLevel()) && (player.getSkillLevel(holder.getSkillId()) < holder.getSkillLevel()))
				{
					player.addSkill(holder.getSkill(), false);
					player.addTransformSkill(holder.getSkill());
				}
			}
		}
	}
	
	public double getStat(L2PcInstance player, Stats stats)
	{
		double val = 0;
		final TransformTemplate template = getTemplate(player);
		if (template != null)
		{
			val = template.getStats(stats);
			final TransformLevelData data = template.getData(player.getLevel());
			if (data != null)
			{
				val = data.getStats(stats);
			}
		}
		return val;
	}
	
	/**
	 * @param player
	 * @param slot
	 * @return
	 */
	public int getBaseDefBySlot(L2PcInstance player, int slot)
	{
		final TransformTemplate template = getTemplate(player);
		return template != null ? template.getDefense(slot) : player.getTemplate().getBaseDefBySlot(slot);
	}
	
	/**
	 * @param player
	 * @return
	 */
	public double getLevelMod(L2PcInstance player)
	{
		double val = -1;
		final TransformTemplate template = getTemplate(player);
		if (template != null)
		{
			final TransformLevelData data = template.getData(player.getLevel());
			if (data != null)
			{
				val = data.getLevelMod();
			}
		}
		return val;
	}
}

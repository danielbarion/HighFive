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
package com.l2jmobius.gameserver.data.sql.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PetInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2ServitorInstance;
import com.l2jmobius.gameserver.model.skills.Skill;

/**
 * @author Nyaran
 */
public class SummonEffectsTable
{
	/** Servitors **/
	// Map tree
	// -> key: charObjectId, value: classIndex Map
	// --> key: classIndex, value: servitors Map
	// ---> key: servitorSkillId, value: Effects list
	private final Map<Integer, Map<Integer, Map<Integer, List<SummonEffect>>>> _servitorEffects = new HashMap<>();
	
	private Map<Integer, List<SummonEffect>> getServitorEffects(L2PcInstance owner)
	{
		final Map<Integer, Map<Integer, List<SummonEffect>>> servitorMap = _servitorEffects.get(owner.getObjectId());
		if (servitorMap == null)
		{
			return null;
		}
		return servitorMap.get(owner.getClassIndex());
	}
	
	private List<SummonEffect> getServitorEffects(L2PcInstance owner, int referenceSkill)
	{
		return containsOwner(owner) ? getServitorEffects(owner).get(referenceSkill) : null;
	}
	
	private boolean containsOwner(L2PcInstance owner)
	{
		return _servitorEffects.getOrDefault(owner.getObjectId(), Collections.emptyMap()).containsKey(owner.getClassIndex());
	}
	
	private void removeEffects(List<SummonEffect> effects, int skillId)
	{
		if ((effects != null) && !effects.isEmpty())
		{
			for (SummonEffect effect : effects)
			{
				final Skill skill = effect.getSkill();
				if ((skill != null) && (skill.getId() == skillId))
				{
					effects.remove(effect);
				}
			}
		}
	}
	
	private void applyEffects(L2Summon summon, List<SummonEffect> summonEffects)
	{
		if (summonEffects == null)
		{
			return;
		}
		for (SummonEffect se : summonEffects)
		{
			if (se != null)
			{
				se.getSkill().applyEffects(summon, summon, false, se.getEffectCurTime());
			}
		}
	}
	
	public boolean containsSkill(L2PcInstance owner, int referenceSkill)
	{
		return containsOwner(owner) && getServitorEffects(owner).containsKey(referenceSkill);
	}
	
	public void clearServitorEffects(L2PcInstance owner, int referenceSkill)
	{
		if (containsOwner(owner))
		{
			
			getServitorEffects(owner).getOrDefault(referenceSkill, Collections.emptyList()).clear();
		}
	}
	
	public void addServitorEffect(L2PcInstance owner, int referenceSkill, Skill skill, int effectCurTime)
	{
		_servitorEffects.putIfAbsent(owner.getObjectId(), new HashMap<>());
		_servitorEffects.get(owner.getObjectId()).putIfAbsent(owner.getClassIndex(), new HashMap<>());
		getServitorEffects(owner).putIfAbsent(referenceSkill, new CopyOnWriteArrayList<>());
		getServitorEffects(owner).get(referenceSkill).add(new SummonEffect(skill, effectCurTime));
	}
	
	public void removeServitorEffects(L2PcInstance owner, int referenceSkill, int skillId)
	{
		removeEffects(getServitorEffects(owner, referenceSkill), skillId);
	}
	
	public void applyServitorEffects(L2ServitorInstance l2ServitorInstance, L2PcInstance owner, int referenceSkill)
	{
		applyEffects(l2ServitorInstance, getServitorEffects(owner, referenceSkill));
	}
	
	/** Pets **/
	private final Map<Integer, List<SummonEffect>> _petEffects = new HashMap<>(); // key: petItemObjectId, value: Effects list
	
	public void addPetEffect(int controlObjectId, Skill skill, int effectCurTime)
	{
		_petEffects.computeIfAbsent(controlObjectId, k -> new ArrayList<>()).add(new SummonEffect(skill, effectCurTime));
	}
	
	public boolean containsPetId(int controlObjectId)
	{
		return _petEffects.containsKey(controlObjectId);
	}
	
	public void applyPetEffects(L2PetInstance l2PetInstance, int controlObjectId)
	{
		applyEffects(l2PetInstance, _petEffects.get(controlObjectId));
	}
	
	public void clearPetEffects(int controlObjectId)
	{
		_petEffects.getOrDefault(controlObjectId, Collections.emptyList()).clear();
	}
	
	public void removePetEffects(int controlObjectId, int skillId)
	{
		removeEffects(_petEffects.get(controlObjectId), skillId);
	}
	
	private class SummonEffect
	{
		Skill _skill;
		int _effectCurTime;
		
		public SummonEffect(Skill skill, int effectCurTime)
		{
			_skill = skill;
			_effectCurTime = effectCurTime;
		}
		
		public Skill getSkill()
		{
			return _skill;
		}
		
		public int getEffectCurTime()
		{
			return _effectCurTime;
		}
	}
	
	public static SummonEffectsTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final SummonEffectsTable _instance = new SummonEffectsTable();
	}
}

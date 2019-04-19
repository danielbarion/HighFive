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
package com.l2jmobius.gameserver.engines.skills;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jmobius.gameserver.data.xml.impl.EnchantSkillGroupsData;
import com.l2jmobius.gameserver.engines.DocumentBase;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.conditions.Condition;
import com.l2jmobius.gameserver.model.skills.EffectScope;
import com.l2jmobius.gameserver.model.skills.Skill;

/**
 * @author mkizub
 */
public class DocumentSkill extends DocumentBase
{
	public static class SkillInfo
	{
		public int id;
		public String name;
		public StatsSet[] sets;
		public StatsSet[] enchsets1;
		public StatsSet[] enchsets2;
		public StatsSet[] enchsets3;
		public StatsSet[] enchsets4;
		public StatsSet[] enchsets5;
		public StatsSet[] enchsets6;
		public StatsSet[] enchsets7;
		public StatsSet[] enchsets8;
		public int currentLevel;
		public List<Skill> skills = new ArrayList<>();
		public List<Skill> currentSkills = new ArrayList<>();
	}
	
	private SkillInfo _currentSkill;
	private final List<Skill> _skillsInFile = new ArrayList<>();
	
	public DocumentSkill(File file)
	{
		super(file);
	}
	
	private void setCurrentSkill(SkillInfo skill)
	{
		_currentSkill = skill;
	}
	
	@Override
	protected StatsSet getStatsSet()
	{
		return _currentSkill.sets[_currentSkill.currentLevel];
	}
	
	public List<Skill> getSkills()
	{
		return _skillsInFile;
	}
	
	@Override
	protected String getTableValue(String name)
	{
		try
		{
			return _tables.get(name)[_currentSkill.currentLevel];
		}
		catch (RuntimeException e)
		{
			LOGGER.log(Level.SEVERE, "Error in table: " + name + " of Skill Id " + _currentSkill.id, e);
			return "";
		}
	}
	
	@Override
	protected String getTableValue(String name, int idx)
	{
		try
		{
			return _tables.get(name)[idx - 1];
		}
		catch (RuntimeException e)
		{
			LOGGER.log(Level.SEVERE, "wrong level count in skill Id " + _currentSkill.id + " name: " + name + " index : " + idx, e);
			return "";
		}
	}
	
	@Override
	protected void parseDocument(Document doc)
	{
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equalsIgnoreCase(n.getNodeName()))
			{
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					if ("skill".equalsIgnoreCase(d.getNodeName()))
					{
						setCurrentSkill(new SkillInfo());
						parseSkill(d);
						_skillsInFile.addAll(_currentSkill.skills);
						resetTable();
					}
				}
			}
			else if ("skill".equalsIgnoreCase(n.getNodeName()))
			{
				setCurrentSkill(new SkillInfo());
				parseSkill(n);
				_skillsInFile.addAll(_currentSkill.skills);
			}
		}
	}
	
	protected void parseSkill(Node n)
	{
		final NamedNodeMap attrs = n.getAttributes();
		int enchantLevels1 = 0;
		int enchantLevels2 = 0;
		int enchantLevels3 = 0;
		int enchantLevels4 = 0;
		int enchantLevels5 = 0;
		int enchantLevels6 = 0;
		int enchantLevels7 = 0;
		int enchantLevels8 = 0;
		final int skillId = Integer.parseInt(attrs.getNamedItem("id").getNodeValue());
		final String skillName = attrs.getNamedItem("name").getNodeValue();
		final String levels = attrs.getNamedItem("levels").getNodeValue();
		final int lastLvl = Integer.parseInt(levels);
		if (attrs.getNamedItem("enchantGroup1") != null)
		{
			enchantLevels1 = EnchantSkillGroupsData.getInstance().addNewRouteForSkill(skillId, lastLvl, 1, Integer.parseInt(attrs.getNamedItem("enchantGroup1").getNodeValue()));
		}
		if (attrs.getNamedItem("enchantGroup2") != null)
		{
			enchantLevels2 = EnchantSkillGroupsData.getInstance().addNewRouteForSkill(skillId, lastLvl, 2, Integer.parseInt(attrs.getNamedItem("enchantGroup2").getNodeValue()));
		}
		if (attrs.getNamedItem("enchantGroup3") != null)
		{
			enchantLevels3 = EnchantSkillGroupsData.getInstance().addNewRouteForSkill(skillId, lastLvl, 3, Integer.parseInt(attrs.getNamedItem("enchantGroup3").getNodeValue()));
		}
		if (attrs.getNamedItem("enchantGroup4") != null)
		{
			enchantLevels4 = EnchantSkillGroupsData.getInstance().addNewRouteForSkill(skillId, lastLvl, 4, Integer.parseInt(attrs.getNamedItem("enchantGroup4").getNodeValue()));
		}
		if (attrs.getNamedItem("enchantGroup5") != null)
		{
			enchantLevels5 = EnchantSkillGroupsData.getInstance().addNewRouteForSkill(skillId, lastLvl, 5, Integer.parseInt(attrs.getNamedItem("enchantGroup5").getNodeValue()));
		}
		if (attrs.getNamedItem("enchantGroup6") != null)
		{
			enchantLevels6 = EnchantSkillGroupsData.getInstance().addNewRouteForSkill(skillId, lastLvl, 6, Integer.parseInt(attrs.getNamedItem("enchantGroup6").getNodeValue()));
		}
		if (attrs.getNamedItem("enchantGroup7") != null)
		{
			enchantLevels7 = EnchantSkillGroupsData.getInstance().addNewRouteForSkill(skillId, lastLvl, 7, Integer.parseInt(attrs.getNamedItem("enchantGroup7").getNodeValue()));
		}
		if (attrs.getNamedItem("enchantGroup8") != null)
		{
			enchantLevels8 = EnchantSkillGroupsData.getInstance().addNewRouteForSkill(skillId, lastLvl, 8, Integer.parseInt(attrs.getNamedItem("enchantGroup8").getNodeValue()));
		}
		
		_currentSkill.id = skillId;
		_currentSkill.name = skillName;
		_currentSkill.sets = new StatsSet[lastLvl];
		_currentSkill.enchsets1 = new StatsSet[enchantLevels1];
		_currentSkill.enchsets2 = new StatsSet[enchantLevels2];
		_currentSkill.enchsets3 = new StatsSet[enchantLevels3];
		_currentSkill.enchsets4 = new StatsSet[enchantLevels4];
		_currentSkill.enchsets5 = new StatsSet[enchantLevels5];
		_currentSkill.enchsets6 = new StatsSet[enchantLevels6];
		_currentSkill.enchsets7 = new StatsSet[enchantLevels7];
		_currentSkill.enchsets8 = new StatsSet[enchantLevels8];
		
		for (int i = 0; i < lastLvl; i++)
		{
			_currentSkill.sets[i] = new StatsSet();
			_currentSkill.sets[i].set("skill_id", _currentSkill.id);
			_currentSkill.sets[i].set("level", i + 1);
			_currentSkill.sets[i].set("name", _currentSkill.name);
		}
		
		if (_currentSkill.sets.length != lastLvl)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + lastLvl + " levels expected");
		}
		
		final Node first = n.getFirstChild();
		for (n = first; n != null; n = n.getNextSibling())
		{
			if ("table".equalsIgnoreCase(n.getNodeName()))
			{
				parseTable(n);
			}
		}
		for (int i = 1; i <= lastLvl; i++)
		{
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					// Extractable item skills by Zoey76
					if ("capsuled_items_skill".equalsIgnoreCase(n.getAttributes().getNamedItem("name").getNodeValue()))
					{
						setExtractableSkillData(_currentSkill.sets[i - 1], getTableValue("#extractableItems", i));
					}
					else
					{
						parseBeanSet(n, _currentSkill.sets[i - 1], i);
					}
				}
			}
		}
		for (int i = 0; i < enchantLevels1; i++)
		{
			_currentSkill.enchsets1[i] = new StatsSet();
			_currentSkill.enchsets1[i].set("skill_id", _currentSkill.id);
			// currentSkill.enchsets1[i] = currentSkill.sets[currentSkill.sets.length-1];
			_currentSkill.enchsets1[i].set("level", i + 101);
			_currentSkill.enchsets1[i].set("name", _currentSkill.name);
			// currentSkill.enchsets1[i].set("skillType", "NOTDONE");
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets1[i], _currentSkill.sets.length);
				}
			}
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant1".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets1[i], i + 1);
				}
			}
		}
		
		if (_currentSkill.enchsets1.length != enchantLevels1)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + enchantLevels1 + " levels expected");
		}
		
		for (int i = 0; i < enchantLevels2; i++)
		{
			_currentSkill.enchsets2[i] = new StatsSet();
			// currentSkill.enchsets2[i] = currentSkill.sets[currentSkill.sets.length-1];
			_currentSkill.enchsets2[i].set("skill_id", _currentSkill.id);
			_currentSkill.enchsets2[i].set("level", i + 201);
			_currentSkill.enchsets2[i].set("name", _currentSkill.name);
			// currentSkill.enchsets2[i].set("skillType", "NOTDONE");
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets2[i], _currentSkill.sets.length);
				}
			}
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant2".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets2[i], i + 1);
				}
			}
		}
		
		if (_currentSkill.enchsets2.length != enchantLevels2)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + enchantLevels2 + " levels expected");
		}
		
		for (int i = 0; i < enchantLevels3; i++)
		{
			_currentSkill.enchsets3[i] = new StatsSet();
			_currentSkill.enchsets3[i].set("skill_id", _currentSkill.id);
			_currentSkill.enchsets3[i].set("level", i + 301);
			_currentSkill.enchsets3[i].set("name", _currentSkill.name);
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets3[i], _currentSkill.sets.length);
				}
			}
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant3".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets3[i], i + 1);
				}
			}
		}
		
		if (_currentSkill.enchsets3.length != enchantLevels3)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + enchantLevels3 + " levels expected");
		}
		
		for (int i = 0; i < enchantLevels4; i++)
		{
			_currentSkill.enchsets4[i] = new StatsSet();
			_currentSkill.enchsets4[i].set("skill_id", _currentSkill.id);
			_currentSkill.enchsets4[i].set("level", i + 401);
			_currentSkill.enchsets4[i].set("name", _currentSkill.name);
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets4[i], _currentSkill.sets.length);
				}
			}
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant4".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets4[i], i + 1);
				}
			}
		}
		
		if (_currentSkill.enchsets4.length != enchantLevels4)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + enchantLevels4 + " levels expected");
		}
		
		for (int i = 0; i < enchantLevels5; i++)
		{
			_currentSkill.enchsets5[i] = new StatsSet();
			_currentSkill.enchsets5[i].set("skill_id", _currentSkill.id);
			_currentSkill.enchsets5[i].set("level", i + 501);
			_currentSkill.enchsets5[i].set("name", _currentSkill.name);
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets5[i], _currentSkill.sets.length);
				}
			}
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant5".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets5[i], i + 1);
				}
			}
		}
		
		if (_currentSkill.enchsets5.length != enchantLevels5)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + enchantLevels5 + " levels expected");
		}
		
		for (int i = 0; i < enchantLevels6; i++)
		{
			_currentSkill.enchsets6[i] = new StatsSet();
			_currentSkill.enchsets6[i].set("skill_id", _currentSkill.id);
			_currentSkill.enchsets6[i].set("level", i + 601);
			_currentSkill.enchsets6[i].set("name", _currentSkill.name);
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets6[i], _currentSkill.sets.length);
				}
			}
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant6".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets6[i], i + 1);
				}
			}
		}
		
		if (_currentSkill.enchsets6.length != enchantLevels6)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + enchantLevels6 + " levels expected");
		}
		
		for (int i = 0; i < enchantLevels7; i++)
		{
			_currentSkill.enchsets7[i] = new StatsSet();
			_currentSkill.enchsets7[i].set("skill_id", _currentSkill.id);
			_currentSkill.enchsets7[i].set("level", i + 701);
			_currentSkill.enchsets7[i].set("name", _currentSkill.name);
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets7[i], _currentSkill.sets.length);
				}
			}
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant7".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets7[i], i + 1);
				}
			}
		}
		
		if (_currentSkill.enchsets7.length != enchantLevels7)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + enchantLevels7 + " levels expected");
		}
		
		for (int i = 0; i < enchantLevels8; i++)
		{
			_currentSkill.enchsets8[i] = new StatsSet();
			_currentSkill.enchsets8[i].set("skill_id", _currentSkill.id);
			_currentSkill.enchsets8[i].set("level", i + 801);
			_currentSkill.enchsets8[i].set("name", _currentSkill.name);
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("set".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets8[i], _currentSkill.sets.length);
				}
			}
			
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant8".equalsIgnoreCase(n.getNodeName()))
				{
					parseBeanSet(n, _currentSkill.enchsets8[i], i + 1);
				}
			}
		}
		
		if (_currentSkill.enchsets8.length != enchantLevels8)
		{
			throw new RuntimeException("Skill id=" + skillId + " number of levels missmatch, " + enchantLevels8 + " levels expected");
		}
		
		makeSkills();
		for (int i = 0; i < lastLvl; i++)
		{
			_currentSkill.currentLevel = i;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("cond".equalsIgnoreCase(n.getNodeName()))
				{
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("for".equalsIgnoreCase(n.getNodeName()))
				{
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
		}
		
		for (int i = lastLvl; i < (lastLvl + enchantLevels1); i++)
		{
			_currentSkill.currentLevel = i - lastLvl;
			boolean foundCond = false;
			boolean foundFor = false;
			boolean foundChannelingEffects = false;
			boolean foundStartEffects = false;
			boolean foundPveEffects = false;
			boolean foundPvpEffects = false;
			boolean foundEndEffects = false;
			boolean foundSelfEffects = false;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant1cond".equalsIgnoreCase(n.getNodeName()))
				{
					foundCond = true;
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("enchant1for".equalsIgnoreCase(n.getNodeName()))
				{
					foundFor = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("enchant1startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundStartEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("enchant1channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundChannelingEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("enchant1pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPveEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("enchant1pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPvpEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("enchant1endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundEndEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("enchant1selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundSelfEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
			// If none found, the enchanted skill will take effects from maxLvL of norm skill
			if (!foundCond || !foundFor || !foundChannelingEffects || !foundStartEffects || !foundPveEffects || !foundPvpEffects || !foundEndEffects || !foundSelfEffects)
			{
				_currentSkill.currentLevel = lastLvl - 1;
				for (n = first; n != null; n = n.getNextSibling())
				{
					if (!foundCond && "cond".equalsIgnoreCase(n.getNodeName()))
					{
						final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
						final Node msg = n.getAttributes().getNamedItem("msg");
						final Node msgId = n.getAttributes().getNamedItem("msgId");
						if ((condition != null) && (msg != null))
						{
							condition.setMessage(msg.getNodeValue());
						}
						else if ((condition != null) && (msgId != null))
						{
							condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
							final Node addName = n.getAttributes().getNamedItem("addName");
							if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
							{
								condition.addName();
							}
						}
						_currentSkill.currentSkills.get(i).attach(condition, false);
					}
					else if (!foundFor && "for".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i));
					}
					else if (!foundStartEffects && "startEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
					}
					else if (!foundChannelingEffects && "channelingEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
					}
					else if (!foundPveEffects && "pveEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
					}
					else if (!foundPvpEffects && "pvpEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
					}
					else if (!foundEndEffects && "endEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
					}
					else if (!foundSelfEffects && "selfEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
					}
				}
			}
		}
		for (int i = lastLvl + enchantLevels1; i < (lastLvl + enchantLevels1 + enchantLevels2); i++)
		{
			boolean foundCond = false;
			boolean foundFor = false;
			boolean foundChannelingEffects = false;
			boolean foundStartEffects = false;
			boolean foundPveEffects = false;
			boolean foundPvpEffects = false;
			boolean foundEndEffects = false;
			boolean foundSelfEffects = false;
			_currentSkill.currentLevel = i - lastLvl - enchantLevels1;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant2cond".equalsIgnoreCase(n.getNodeName()))
				{
					foundCond = true;
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("enchant2for".equalsIgnoreCase(n.getNodeName()))
				{
					foundFor = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("enchant2startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundStartEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("enchant2channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundChannelingEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("enchant2pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPveEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("enchant2pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPvpEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("enchant2endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundEndEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("enchant2selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundSelfEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
			// If none found, the enchanted skill will take effects from maxLvL of norm skill
			if (!foundCond || !foundFor || !foundChannelingEffects || !foundStartEffects || !foundPveEffects || !foundPvpEffects || !foundEndEffects || !foundSelfEffects)
			{
				_currentSkill.currentLevel = lastLvl - 1;
				for (n = first; n != null; n = n.getNextSibling())
				{
					if (!foundCond && "cond".equalsIgnoreCase(n.getNodeName()))
					{
						final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
						final Node msg = n.getAttributes().getNamedItem("msg");
						final Node msgId = n.getAttributes().getNamedItem("msgId");
						if ((condition != null) && (msg != null))
						{
							condition.setMessage(msg.getNodeValue());
						}
						else if ((condition != null) && (msgId != null))
						{
							condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
							final Node addName = n.getAttributes().getNamedItem("addName");
							if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
							{
								condition.addName();
							}
						}
						_currentSkill.currentSkills.get(i).attach(condition, false);
					}
					else if (!foundFor && "for".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i));
					}
					else if (!foundChannelingEffects && "channelingEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
					}
					else if (!foundPveEffects && "pveEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
					}
					else if (!foundPvpEffects && "pvpEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
					}
					else if (!foundEndEffects && "endEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
					}
					else if (!foundSelfEffects && "selfEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
					}
				}
			}
		}
		for (int i = lastLvl + enchantLevels1 + enchantLevels2; i < (lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3); i++)
		{
			boolean foundCond = false;
			boolean foundFor = false;
			boolean foundChannelingEffects = false;
			boolean foundStartEffects = false;
			boolean foundPveEffects = false;
			boolean foundPvpEffects = false;
			boolean foundEndEffects = false;
			boolean foundSelfEffects = false;
			_currentSkill.currentLevel = i - lastLvl - enchantLevels1 - enchantLevels2;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant3cond".equalsIgnoreCase(n.getNodeName()))
				{
					foundCond = true;
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("enchant3for".equalsIgnoreCase(n.getNodeName()))
				{
					foundFor = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("enchant3startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundStartEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("enchant3channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundChannelingEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("enchant3pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPveEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("enchant3pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPvpEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("enchant3endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundEndEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("enchant3selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundSelfEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
			// If none found, the enchanted skill will take effects from maxLvL of norm skill
			if (!foundCond || !foundFor || !foundChannelingEffects || !foundStartEffects || !foundPveEffects || !foundPvpEffects || !foundEndEffects || !foundSelfEffects)
			{
				_currentSkill.currentLevel = lastLvl - 1;
				for (n = first; n != null; n = n.getNextSibling())
				{
					if (!foundCond && "cond".equalsIgnoreCase(n.getNodeName()))
					{
						final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
						final Node msg = n.getAttributes().getNamedItem("msg");
						final Node msgId = n.getAttributes().getNamedItem("msgId");
						if ((condition != null) && (msg != null))
						{
							condition.setMessage(msg.getNodeValue());
						}
						else if ((condition != null) && (msgId != null))
						{
							condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
							final Node addName = n.getAttributes().getNamedItem("addName");
							if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
							{
								condition.addName();
							}
						}
						_currentSkill.currentSkills.get(i).attach(condition, false);
					}
					else if (!foundFor && "for".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i));
					}
					else if (!foundStartEffects && "startEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
					}
					else if (!foundChannelingEffects && "channelingEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
					}
					else if (!foundPveEffects && "pveEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
					}
					else if (!foundPvpEffects && "pvpEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
					}
					else if (!foundEndEffects && "endEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
					}
					else if (!foundSelfEffects && "selfEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
					}
				}
			}
		}
		for (int i = lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3; i < (lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4); i++)
		{
			boolean foundCond = false;
			boolean foundFor = false;
			boolean foundChannelingEffects = false;
			boolean foundStartEffects = false;
			boolean foundPveEffects = false;
			boolean foundPvpEffects = false;
			boolean foundEndEffects = false;
			boolean foundSelfEffects = false;
			_currentSkill.currentLevel = i - lastLvl - enchantLevels1 - enchantLevels2 - enchantLevels3;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant4cond".equalsIgnoreCase(n.getNodeName()))
				{
					foundCond = true;
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("enchant4for".equalsIgnoreCase(n.getNodeName()))
				{
					foundFor = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("enchant4startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundStartEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("enchant4channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundChannelingEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("enchant4pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPveEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("enchant4pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPvpEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("enchant4endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundEndEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("enchant4selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundSelfEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
			// If none found, the enchanted skill will take effects from maxLvL of norm skill
			if (!foundCond || !foundFor || !foundChannelingEffects || !foundStartEffects || !foundPveEffects || !foundPvpEffects || !foundEndEffects || !foundSelfEffects)
			{
				_currentSkill.currentLevel = lastLvl - 1;
				for (n = first; n != null; n = n.getNextSibling())
				{
					if (!foundCond && "cond".equalsIgnoreCase(n.getNodeName()))
					{
						final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
						final Node msg = n.getAttributes().getNamedItem("msg");
						final Node msgId = n.getAttributes().getNamedItem("msgId");
						if ((condition != null) && (msg != null))
						{
							condition.setMessage(msg.getNodeValue());
						}
						else if ((condition != null) && (msgId != null))
						{
							condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
							final Node addName = n.getAttributes().getNamedItem("addName");
							if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
							{
								condition.addName();
							}
						}
						_currentSkill.currentSkills.get(i).attach(condition, false);
					}
					else if (!foundFor && "for".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i));
					}
					else if (!foundStartEffects && "startEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
					}
					else if (!foundChannelingEffects && "channelingEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
					}
					else if (!foundPveEffects && "pveEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
					}
					else if (!foundPvpEffects && "pvpEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
					}
					else if (!foundEndEffects && "endEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
					}
					else if (!foundSelfEffects && "selfEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
					}
				}
			}
		}
		for (int i = lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4; i < (lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4 + enchantLevels5); i++)
		{
			boolean foundCond = false;
			boolean foundFor = false;
			boolean foundChannelingEffects = false;
			boolean foundStartEffects = false;
			boolean foundPveEffects = false;
			boolean foundPvpEffects = false;
			boolean foundEndEffects = false;
			boolean foundSelfEffects = false;
			_currentSkill.currentLevel = i - lastLvl - enchantLevels1 - enchantLevels2 - enchantLevels3 - enchantLevels4;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant5cond".equalsIgnoreCase(n.getNodeName()))
				{
					foundCond = true;
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("enchant5for".equalsIgnoreCase(n.getNodeName()))
				{
					foundFor = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("enchant5startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundStartEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("enchant5channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundChannelingEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("enchant5pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPveEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("enchant5pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPvpEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("enchant5endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundEndEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("enchant5selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundSelfEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
			// If none found, the enchanted skill will take effects from maxLvL of norm skill
			if (!foundCond || !foundFor || !foundChannelingEffects || !foundStartEffects || !foundPveEffects || !foundPvpEffects || !foundEndEffects || !foundSelfEffects)
			{
				_currentSkill.currentLevel = lastLvl - 1;
				for (n = first; n != null; n = n.getNextSibling())
				{
					if (!foundCond && "cond".equalsIgnoreCase(n.getNodeName()))
					{
						final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
						final Node msg = n.getAttributes().getNamedItem("msg");
						final Node msgId = n.getAttributes().getNamedItem("msgId");
						if ((condition != null) && (msg != null))
						{
							condition.setMessage(msg.getNodeValue());
						}
						else if ((condition != null) && (msgId != null))
						{
							condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
							final Node addName = n.getAttributes().getNamedItem("addName");
							if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
							{
								condition.addName();
							}
						}
						_currentSkill.currentSkills.get(i).attach(condition, false);
					}
					else if (!foundFor && "for".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i));
					}
					else if (!foundStartEffects && "startEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
					}
					else if (!foundChannelingEffects && "channelingEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
					}
					else if (!foundPveEffects && "pveEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
					}
					else if (!foundPvpEffects && "pvpEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
					}
					else if (!foundEndEffects && "endEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
					}
					else if (!foundSelfEffects && "selfEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
					}
				}
			}
		}
		for (int i = lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4 + enchantLevels5; i < (lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4 + enchantLevels5 + enchantLevels6); i++)
		{
			boolean foundCond = false;
			boolean foundFor = false;
			boolean foundChannelingEffects = false;
			boolean foundStartEffects = false;
			boolean foundPveEffects = false;
			boolean foundPvpEffects = false;
			boolean foundEndEffects = false;
			boolean foundSelfEffects = false;
			_currentSkill.currentLevel = i - lastLvl - enchantLevels1 - enchantLevels2 - enchantLevels3 - enchantLevels4 - enchantLevels5;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant6cond".equalsIgnoreCase(n.getNodeName()))
				{
					foundCond = true;
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("enchant6for".equalsIgnoreCase(n.getNodeName()))
				{
					foundFor = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("enchant6startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundStartEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("enchant6channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundChannelingEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("enchant6pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPveEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("enchant6pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPvpEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("enchant6endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundEndEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("enchant6selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundSelfEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
			// If none found, the enchanted skill will take effects from maxLvL of norm skill
			if (!foundCond || !foundFor || !foundChannelingEffects || !foundStartEffects || !foundPveEffects || !foundPvpEffects || !foundEndEffects || !foundSelfEffects)
			{
				_currentSkill.currentLevel = lastLvl - 1;
				for (n = first; n != null; n = n.getNextSibling())
				{
					if (!foundCond && "cond".equalsIgnoreCase(n.getNodeName()))
					{
						final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
						final Node msg = n.getAttributes().getNamedItem("msg");
						final Node msgId = n.getAttributes().getNamedItem("msgId");
						if ((condition != null) && (msg != null))
						{
							condition.setMessage(msg.getNodeValue());
						}
						else if ((condition != null) && (msgId != null))
						{
							condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
							final Node addName = n.getAttributes().getNamedItem("addName");
							if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
							{
								condition.addName();
							}
						}
						_currentSkill.currentSkills.get(i).attach(condition, false);
					}
					else if (!foundFor && "for".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i));
					}
					else if (!foundStartEffects && "startEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
					}
					else if (!foundChannelingEffects && "channelingEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
					}
					else if (!foundPveEffects && "pveEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
					}
					else if (!foundPvpEffects && "pvpEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
					}
					else if (!foundEndEffects && "endEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
					}
					else if (!foundSelfEffects && "selfEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
					}
				}
			}
		}
		for (int i = lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4 + enchantLevels5 + enchantLevels6; i < (lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4 + enchantLevels5 + enchantLevels6 + enchantLevels7); i++)
		{
			boolean foundCond = false;
			boolean foundFor = false;
			boolean foundChannelingEffects = false;
			boolean foundStartEffects = false;
			boolean foundPveEffects = false;
			boolean foundPvpEffects = false;
			boolean foundEndEffects = false;
			boolean foundSelfEffects = false;
			_currentSkill.currentLevel = i - lastLvl - enchantLevels1 - enchantLevels2 - enchantLevels3 - enchantLevels4 - enchantLevels5 - enchantLevels6;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant7cond".equalsIgnoreCase(n.getNodeName()))
				{
					foundCond = true;
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("enchant7for".equalsIgnoreCase(n.getNodeName()))
				{
					foundFor = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("enchant7startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundStartEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("enchant7channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundChannelingEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("enchant7pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPveEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("enchant7pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPvpEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("enchant7endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundEndEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("enchant7selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundSelfEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
			// If none found, the enchanted skill will take effects from maxLvL of norm skill
			if (!foundCond || !foundFor || !foundChannelingEffects || !foundStartEffects || !foundPveEffects || !foundPvpEffects || !foundEndEffects || !foundSelfEffects)
			{
				_currentSkill.currentLevel = lastLvl - 1;
				for (n = first; n != null; n = n.getNextSibling())
				{
					if (!foundCond && "cond".equalsIgnoreCase(n.getNodeName()))
					{
						final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
						final Node msg = n.getAttributes().getNamedItem("msg");
						final Node msgId = n.getAttributes().getNamedItem("msgId");
						if ((condition != null) && (msg != null))
						{
							condition.setMessage(msg.getNodeValue());
						}
						else if ((condition != null) && (msgId != null))
						{
							condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
							final Node addName = n.getAttributes().getNamedItem("addName");
							if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
							{
								condition.addName();
							}
						}
						_currentSkill.currentSkills.get(i).attach(condition, false);
					}
					else if (!foundFor && "for".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i));
					}
					else if (!foundChannelingEffects && "startEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
					}
					else if (!foundChannelingEffects && "channelingEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
					}
					else if (!foundPveEffects && "pveEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
					}
					else if (!foundPvpEffects && "pvpEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
					}
					else if (!foundEndEffects && "endEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
					}
					else if (!foundSelfEffects && "selfEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
					}
				}
			}
		}
		for (int i = lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4 + enchantLevels5 + enchantLevels6 + enchantLevels7; i < (lastLvl + enchantLevels1 + enchantLevels2 + enchantLevels3 + enchantLevels4 + enchantLevels5 + enchantLevels6 + enchantLevels7 + enchantLevels8); i++)
		{
			boolean foundCond = false;
			boolean foundFor = false;
			boolean foundChannelingEffects = false;
			boolean foundStartEffects = false;
			boolean foundPveEffects = false;
			boolean foundPvpEffects = false;
			boolean foundEndEffects = false;
			boolean foundSelfEffects = false;
			_currentSkill.currentLevel = i - lastLvl - enchantLevels1 - enchantLevels2 - enchantLevels3 - enchantLevels4 - enchantLevels5 - enchantLevels6 - enchantLevels7;
			for (n = first; n != null; n = n.getNextSibling())
			{
				if ("enchant8cond".equalsIgnoreCase(n.getNodeName()))
				{
					foundCond = true;
					final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
					final Node msg = n.getAttributes().getNamedItem("msg");
					final Node msgId = n.getAttributes().getNamedItem("msgId");
					if ((condition != null) && (msg != null))
					{
						condition.setMessage(msg.getNodeValue());
					}
					else if ((condition != null) && (msgId != null))
					{
						condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
						final Node addName = n.getAttributes().getNamedItem("addName");
						if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
						{
							condition.addName();
						}
					}
					_currentSkill.currentSkills.get(i).attach(condition, false);
				}
				else if ("enchant8for".equalsIgnoreCase(n.getNodeName()))
				{
					foundFor = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i));
				}
				else if ("enchant8startEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundStartEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
				}
				else if ("enchant8channelingEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundChannelingEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
				}
				else if ("enchant8pveEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPveEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
				}
				else if ("enchant8pvpEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundPvpEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
				}
				else if ("enchant8endEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundEndEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
				}
				else if ("enchant8selfEffects".equalsIgnoreCase(n.getNodeName()))
				{
					foundSelfEffects = true;
					parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
				}
			}
			// If none found, the enchanted skill will take effects from maxLvL of norm skill
			if (!foundCond || !foundFor || !foundChannelingEffects || !foundStartEffects || !foundPveEffects || !foundPvpEffects || !foundEndEffects || !foundSelfEffects)
			{
				_currentSkill.currentLevel = lastLvl - 1;
				for (n = first; n != null; n = n.getNextSibling())
				{
					if (!foundCond && "cond".equalsIgnoreCase(n.getNodeName()))
					{
						final Condition condition = parseCondition(n.getFirstChild(), _currentSkill.currentSkills.get(i));
						final Node msg = n.getAttributes().getNamedItem("msg");
						final Node msgId = n.getAttributes().getNamedItem("msgId");
						if ((condition != null) && (msg != null))
						{
							condition.setMessage(msg.getNodeValue());
						}
						else if ((condition != null) && (msgId != null))
						{
							condition.setMessageId(Integer.decode(getValue(msgId.getNodeValue(), null)));
							final Node addName = n.getAttributes().getNamedItem("addName");
							if ((addName != null) && (Integer.decode(getValue(msgId.getNodeValue(), null)) > 0))
							{
								condition.addName();
							}
						}
						_currentSkill.currentSkills.get(i).attach(condition, false);
					}
					else if (!foundFor && "for".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i));
					}
					else if (!foundStartEffects && "startEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.START);
					}
					else if (!foundChannelingEffects && "channelingEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.CHANNELING);
					}
					else if (!foundPveEffects && "pveEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVE);
					}
					else if (!foundPvpEffects && "pvpEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.PVP);
					}
					else if (!foundEndEffects && "endEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.END);
					}
					else if (!foundSelfEffects && "selfEffects".equalsIgnoreCase(n.getNodeName()))
					{
						parseTemplate(n, _currentSkill.currentSkills.get(i), EffectScope.SELF);
					}
				}
			}
		}
		_currentSkill.skills.addAll(_currentSkill.currentSkills);
	}
	
	private void makeSkills()
	{
		int count = 0;
		_currentSkill.currentSkills = new ArrayList<>(_currentSkill.sets.length + _currentSkill.enchsets1.length + _currentSkill.enchsets2.length + _currentSkill.enchsets3.length + _currentSkill.enchsets4.length + _currentSkill.enchsets5.length + _currentSkill.enchsets6.length + _currentSkill.enchsets7.length + _currentSkill.enchsets8.length);
		StatsSet set;
		for (int i = 0; i < _currentSkill.sets.length; i++)
		{
			set = _currentSkill.sets[i];
			try
			{
				_currentSkill.currentSkills.add(i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
		int _count = count;
		for (int i = 0; i < _currentSkill.enchsets1.length; i++)
		{
			set = _currentSkill.enchsets1[i];
			try
			{
				_currentSkill.currentSkills.add(_count + i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
		_count = count;
		for (int i = 0; i < _currentSkill.enchsets2.length; i++)
		{
			set = _currentSkill.enchsets2[i];
			try
			{
				_currentSkill.currentSkills.add(_count + i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
		_count = count;
		for (int i = 0; i < _currentSkill.enchsets3.length; i++)
		{
			set = _currentSkill.enchsets3[i];
			try
			{
				_currentSkill.currentSkills.add(_count + i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
		_count = count;
		for (int i = 0; i < _currentSkill.enchsets4.length; i++)
		{
			set = _currentSkill.enchsets4[i];
			try
			{
				_currentSkill.currentSkills.add(_count + i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
		_count = count;
		for (int i = 0; i < _currentSkill.enchsets5.length; i++)
		{
			set = _currentSkill.enchsets5[i];
			try
			{
				_currentSkill.currentSkills.add(_count + i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
		_count = count;
		for (int i = 0; i < _currentSkill.enchsets6.length; i++)
		{
			set = _currentSkill.enchsets6[i];
			try
			{
				_currentSkill.currentSkills.add(_count + i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
		_count = count;
		for (int i = 0; i < _currentSkill.enchsets7.length; i++)
		{
			set = _currentSkill.enchsets7[i];
			try
			{
				_currentSkill.currentSkills.add(_count + i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
		_count = count;
		for (int i = 0; i < _currentSkill.enchsets8.length; i++)
		{
			set = _currentSkill.enchsets8[i];
			try
			{
				_currentSkill.currentSkills.add(_count + i, new Skill(set));
				count++;
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Skill id=" + set.getInt("skill_id") + "level" + set.getInt("level"), e);
			}
		}
	}
}
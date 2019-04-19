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
package com.l2jmobius.gameserver.data.xml.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jmobius.commons.util.IGameXmlReader;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.options.Options;
import com.l2jmobius.gameserver.model.options.OptionsSkillHolder;
import com.l2jmobius.gameserver.model.options.OptionsSkillType;
import com.l2jmobius.gameserver.model.stats.Stats;
import com.l2jmobius.gameserver.model.stats.functions.FuncTemplate;

/**
 * Item Option data.
 * @author UnAfraid
 */
public class OptionData implements IGameXmlReader
{
	private final Map<Integer, Options> _optionData = new HashMap<>();
	
	protected OptionData()
	{
		load();
	}
	
	@Override
	public synchronized void load()
	{
		_optionData.clear();
		parseDatapackDirectory("data/stats/augmentation/options", false);
		LOGGER.log(Level.INFO, getClass().getSimpleName() + ": Loaded: " + _optionData.size() + " Options.");
	}
	
	@Override
	public void parseDocument(Document doc, File f)
	{
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equalsIgnoreCase(n.getNodeName()))
			{
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					if ("option".equalsIgnoreCase(d.getNodeName()))
					{
						final int id = parseInteger(d.getAttributes(), "id");
						final Options op = new Options(id);
						
						for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
						{
							switch (cd.getNodeName())
							{
								case "for":
								{
									for (Node fd = cd.getFirstChild(); fd != null; fd = fd.getNextSibling())
									{
										switch (fd.getNodeName())
										{
											case "add":
											case "sub":
											case "mul":
											case "div":
											case "set":
											case "share":
											case "enchant":
											case "enchanthp":
											{
												parseFuncs(fd.getAttributes(), fd.getNodeName(), op);
											}
										}
										
									}
									break;
								}
								case "active_skill":
								{
									op.setActiveSkill(new SkillHolder(parseInteger(cd.getAttributes(), "id"), parseInteger(cd.getAttributes(), "level")));
									break;
								}
								case "passive_skill":
								{
									op.setPassiveSkill(new SkillHolder(parseInteger(cd.getAttributes(), "id"), parseInteger(cd.getAttributes(), "level")));
									break;
								}
								case "attack_skill":
								{
									op.addActivationSkill(new OptionsSkillHolder(parseInteger(cd.getAttributes(), "id"), parseInteger(cd.getAttributes(), "level"), parseDouble(cd.getAttributes(), "chance"), OptionsSkillType.ATTACK));
									break;
								}
								case "magic_skill":
								{
									op.addActivationSkill(new OptionsSkillHolder(parseInteger(cd.getAttributes(), "id"), parseInteger(cd.getAttributes(), "level"), parseDouble(cd.getAttributes(), "chance"), OptionsSkillType.MAGIC));
									break;
								}
								case "critical_skill":
								{
									op.addActivationSkill(new OptionsSkillHolder(parseInteger(cd.getAttributes(), "id"), parseInteger(cd.getAttributes(), "level"), parseDouble(cd.getAttributes(), "chance"), OptionsSkillType.CRITICAL));
									break;
								}
							}
						}
						_optionData.put(op.getId(), op);
					}
				}
			}
		}
	}
	
	private void parseFuncs(NamedNodeMap attrs, String functionName, Options op)
	{
		final Stats stat = Stats.valueOfXml(parseString(attrs, "stat"));
		final double val = parseDouble(attrs, "val");
		int order = -1;
		final Node orderNode = attrs.getNamedItem("order");
		if (orderNode != null)
		{
			order = Integer.parseInt(orderNode.getNodeValue());
		}
		op.addFunc(new FuncTemplate(null, null, functionName, order, stat, val));
	}
	
	public Options getOptions(int id)
	{
		return _optionData.get(id);
	}
	
	/**
	 * Gets the single instance of OptionsData.
	 * @return single instance of OptionsData
	 */
	public static OptionData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final OptionData _instance = new OptionData();
	}
}

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
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.transform.Transform;
import com.l2jmobius.gameserver.model.actor.transform.TransformLevelData;
import com.l2jmobius.gameserver.model.actor.transform.TransformTemplate;
import com.l2jmobius.gameserver.model.holders.AdditionalItemHolder;
import com.l2jmobius.gameserver.model.holders.AdditionalSkillHolder;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.network.serverpackets.ExBasicActionList;

/**
 * @author UnAfraid
 */
public final class TransformData implements IGameXmlReader
{
	private final Map<Integer, Transform> _transformData = new HashMap<>();
	
	protected TransformData()
	{
		load();
	}
	
	@Override
	public synchronized void load()
	{
		_transformData.clear();
		parseDatapackDirectory("data/stats/transformations", false);
		LOGGER.log(Level.INFO, getClass().getSimpleName() + ": Loaded: " + _transformData.size() + " transform templates.");
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
					if ("transform".equalsIgnoreCase(d.getNodeName()))
					{
						NamedNodeMap attrs = d.getAttributes();
						final StatsSet set = new StatsSet();
						for (int i = 0; i < attrs.getLength(); i++)
						{
							final Node att = attrs.item(i);
							set.set(att.getNodeName(), att.getNodeValue());
						}
						final Transform transform = new Transform(set);
						for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
						{
							final boolean isMale = "Male".equalsIgnoreCase(cd.getNodeName());
							if ("Male".equalsIgnoreCase(cd.getNodeName()) || "Female".equalsIgnoreCase(cd.getNodeName()))
							{
								TransformTemplate templateData = null;
								for (Node z = cd.getFirstChild(); z != null; z = z.getNextSibling())
								{
									switch (z.getNodeName())
									{
										case "common":
										{
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												switch (s.getNodeName())
												{
													case "base":
													case "stats":
													case "defense":
													case "magicDefense":
													case "collision":
													case "moving":
													{
														attrs = s.getAttributes();
														for (int i = 0; i < attrs.getLength(); i++)
														{
															final Node att = attrs.item(i);
															set.set(att.getNodeName(), att.getNodeValue());
														}
														break;
													}
												}
											}
											templateData = new TransformTemplate(set);
											transform.setTemplate(isMale, templateData);
											break;
										}
										case "skills":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("skill".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													final int skillId = parseInteger(attrs, "id");
													final int skillLevel = parseInteger(attrs, "level");
													templateData.addSkill(new SkillHolder(skillId, skillLevel));
												}
											}
											break;
										}
										case "actions":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											set.set("actions", z.getTextContent());
											final int[] actions = set.getIntArray("actions", " ");
											templateData.setBasicActionList(new ExBasicActionList(actions));
											break;
										}
										case "additionalSkills":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("skill".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													final int skillId = parseInteger(attrs, "id");
													final int skillLevel = parseInteger(attrs, "level");
													final int minLevel = parseInteger(attrs, "minLevel");
													templateData.addAdditionalSkill(new AdditionalSkillHolder(skillId, skillLevel, minLevel));
												}
											}
											break;
										}
										case "items":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("item".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													final int itemId = parseInteger(attrs, "id");
													final boolean allowed = parseBoolean(attrs, "allowed");
													templateData.addAdditionalItem(new AdditionalItemHolder(itemId, allowed));
												}
											}
											break;
										}
										case "levels":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											
											final StatsSet levelsSet = new StatsSet();
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("level".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													for (int i = 0; i < attrs.getLength(); i++)
													{
														final Node att = attrs.item(i);
														levelsSet.set(att.getNodeName(), att.getNodeValue());
													}
												}
											}
											templateData.addLevelData(new TransformLevelData(levelsSet));
											break;
										}
									}
								}
							}
						}
						_transformData.put(transform.getId(), transform);
					}
				}
			}
		}
	}
	
	public Transform getTransform(int id)
	{
		return _transformData.get(id);
	}
	
	public boolean transformPlayer(int id, L2PcInstance player)
	{
		final Transform transform = getTransform(id);
		if (transform != null)
		{
			player.transform(transform);
		}
		return transform != null;
	}
	
	public static TransformData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final TransformData _instance = new TransformData();
	}
}

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
package com.l2jmobius.commons.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.holders.MinionHolder;
import com.l2jmobius.gameserver.model.holders.SkillHolder;

/**
 * Interface for XML parsers.
 * @author Zoey76
 */
public interface IGameXmlReader extends IXmlReader
{
	/**
	 * Wrapper for {@link #parseFile(File)} method.
	 * @param path the relative path to the datapack root of the XML file to parse.
	 */
	default void parseDatapackFile(String path)
	{
		parseFile(new File(Config.DATAPACK_ROOT, path));
	}
	
	/**
	 * Wrapper for {@link #parseDirectory(File, boolean)}.
	 * @param path the path to the directory where the XML files are
	 * @param recursive parses all sub folders if there is
	 * @return {@code false} if it fails to find the directory, {@code true} otherwise
	 */
	default boolean parseDatapackDirectory(String path, boolean recursive)
	{
		return parseDirectory(new File(Config.DATAPACK_ROOT, path), recursive);
	}
	
	/**
	 * @param n
	 * @return a map of parameters
	 */
	default Map<String, Object> parseParameters(Node n)
	{
		final Map<String, Object> parameters = new HashMap<>();
		for (Node parameters_node = n.getFirstChild(); parameters_node != null; parameters_node = parameters_node.getNextSibling())
		{
			NamedNodeMap attrs = parameters_node.getAttributes();
			switch (parameters_node.getNodeName().toLowerCase())
			{
				case "param":
				{
					parameters.put(parseString(attrs, "name"), parseString(attrs, "value"));
					break;
				}
				case "skill":
				{
					parameters.put(parseString(attrs, "name"), new SkillHolder(parseInteger(attrs, "id"), parseInteger(attrs, "level")));
					break;
				}
				case "location":
				{
					parameters.put(parseString(attrs, "name"), new Location(parseInteger(attrs, "x"), parseInteger(attrs, "y"), parseInteger(attrs, "z"), parseInteger(attrs, "heading", 0)));
					break;
				}
				case "minions":
				{
					final List<MinionHolder> minions = new ArrayList<>(1);
					for (Node minions_node = parameters_node.getFirstChild(); minions_node != null; minions_node = minions_node.getNextSibling())
					{
						if (minions_node.getNodeName().equalsIgnoreCase("npc"))
						{
							attrs = minions_node.getAttributes();
							minions.add(new MinionHolder(parseInteger(attrs, "id"), parseInteger(attrs, "count"), parseInteger(attrs, "respawnTime"), parseInteger(attrs, "weightPoint")));
						}
					}
					
					if (!minions.isEmpty())
					{
						parameters.put(parseString(parameters_node.getAttributes(), "name"), minions);
					}
					break;
				}
			}
		}
		return parameters;
	}
	
	default Location parseLocation(Node n)
	{
		final NamedNodeMap attrs = n.getAttributes();
		final int x = parseInteger(attrs, "x");
		final int y = parseInteger(attrs, "y");
		final int z = parseInteger(attrs, "z");
		final int heading = parseInteger(attrs, "heading", 0);
		return new Location(x, y, z, heading);
	}
}

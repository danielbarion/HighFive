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
package com.l2jmobius.gameserver.cache;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.file.filter.HTMLFilter;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.util.BuilderUtil;
import com.l2jmobius.gameserver.util.Util;

/**
 * @author Layane
 */
public class HtmCache
{
	private static final Logger LOGGER = Logger.getLogger(HtmCache.class.getName());
	
	private static final HTMLFilter HTML_FILTER = new HTMLFilter();
	
	private static final Map<String, String> _cache = Config.LAZY_CACHE ? new ConcurrentHashMap<>() : new HashMap<>();
	
	private int _loadedFiles;
	private long _bytesBuffLen;
	
	protected HtmCache()
	{
		reload();
	}
	
	public void reload()
	{
		reload(Config.DATAPACK_ROOT);
	}
	
	public void reload(File f)
	{
		if (!Config.LAZY_CACHE)
		{
			LOGGER.info("Html cache start...");
			parseDir(f);
			LOGGER.info("Cache[HTML]: " + String.format("%.3f", getMemoryUsage()) + " megabytes on " + _loadedFiles + " files loaded");
		}
		else
		{
			_cache.clear();
			_loadedFiles = 0;
			_bytesBuffLen = 0;
			LOGGER.info("Cache[HTML]: Running lazy cache");
		}
	}
	
	public void reloadPath(File f)
	{
		parseDir(f);
		LOGGER.info("Cache[HTML]: Reloaded specified path.");
	}
	
	public double getMemoryUsage()
	{
		return (float) _bytesBuffLen / 1048576;
	}
	
	public int getLoadedFiles()
	{
		return _loadedFiles;
	}
	
	private void parseDir(File dir)
	{
		final File[] files = dir.listFiles();
		if (files != null)
		{
			for (File file : files)
			{
				if (!file.isDirectory())
				{
					loadFile(file);
				}
				else
				{
					parseDir(file);
				}
			}
		}
	}
	
	public String loadFile(File file)
	{
		if (!HTML_FILTER.accept(file))
		{
			return null;
		}
		
		final String relpath = Util.getRelativePath(Config.DATAPACK_ROOT, file);
		String content = null;
		try (FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis))
		{
			final int bytes = bis.available();
			final byte[] raw = new byte[bytes];
			
			bis.read(raw);
			content = new String(raw, "UTF-8");
			content = content.replaceAll("(?s)<!--.*?-->", ""); // Remove html comments
			
			final String oldContent = _cache.put(relpath, content);
			if (oldContent == null)
			{
				_bytesBuffLen += bytes;
				_loadedFiles++;
			}
			else
			{
				_bytesBuffLen = (_bytesBuffLen - oldContent.length()) + bytes;
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Problem with htm file " + e.getMessage(), e);
		}
		return content;
	}
	
	public String getHtmForce(L2PcInstance player, String path)
	{
		String content = getHtm(player, path);
		if (content == null)
		{
			content = "<html><body>My text is missing:<br>" + path + "</body></html>";
			LOGGER.warning("Cache[HTML]: Missing HTML page: " + path);
		}
		return content;
	}
	
	public String getHtm(L2PcInstance player, String path)
	{
		final String prefix = player != null ? player.getHtmlPrefix() : "en";
		String newPath = null;
		String content;
		if ((prefix != null) && !prefix.isEmpty())
		{
			newPath = prefix + path;
			content = getHtm(newPath);
			if (content != null)
			{
				if ((player != null) && player.isGM() && Config.GM_DEBUG_HTML_PATHS)
				{
					BuilderUtil.sendHtmlMessage(player, newPath.substring(5));
				}
				return content;
			}
		}
		
		content = getHtm(path);
		if ((content != null) && (newPath != null))
		{
			_cache.put(newPath, content);
		}
		
		if ((player != null) && player.isGM() && (path != null) && Config.GM_DEBUG_HTML_PATHS)
		{
			BuilderUtil.sendHtmlMessage(player, path.substring(5));
		}
		return content;
	}
	
	private String getHtm(String path)
	{
		// TODO: Check why some files do not get in cache on server startup.
		return (path == null) || path.isEmpty() ? "" : _cache.get(path) == null ? loadFile(new File(Config.DATAPACK_ROOT, path)) : _cache.get(path);
	}
	
	public boolean contains(String path)
	{
		return _cache.containsKey(path);
	}
	
	/**
	 * @param path The path to the HTM
	 * @return {@code true} if the path targets a HTM or HTML file, {@code false} otherwise.
	 */
	public boolean isLoadable(String path)
	{
		return HTML_FILTER.accept(new File(Config.DATAPACK_ROOT, path));
	}
	
	public static HtmCache getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final HtmCache _instance = new HtmCache();
	}
}

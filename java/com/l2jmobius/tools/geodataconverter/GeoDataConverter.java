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
package com.l2jmobius.tools.geodataconverter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.PropertiesParser;
import com.l2jmobius.gameserver.geoengine.geodata.ABlock;
import com.l2jmobius.gameserver.geoengine.geodata.BlockComplex;
import com.l2jmobius.gameserver.geoengine.geodata.BlockFlat;
import com.l2jmobius.gameserver.geoengine.geodata.BlockMultilayer;
import com.l2jmobius.gameserver.geoengine.geodata.GeoFormat;
import com.l2jmobius.gameserver.geoengine.geodata.GeoStructure;
import com.l2jmobius.gameserver.model.L2World;

/**
 * @author Hasha
 */
public final class GeoDataConverter
{
	private static GeoFormat _format;
	private static ABlock[][] _blocks;
	
	public static void main(String[] args)
	{
		// initialize config
		loadGeoengineConfigs();
		
		// get geodata format
		String type = "";
		try (Scanner scn = new Scanner(System.in))
		{
			while (!(type.equalsIgnoreCase("J") || type.equalsIgnoreCase("O") || type.equalsIgnoreCase("E")))
			{
				System.out.println("GeoDataConverter: Select source geodata type:");
				System.out.println("  J: L2J (e.g. 23_22.l2j)");
				System.out.println("  O: L2OFF (e.g. 23_22_conv.dat)");
				System.out.println("  E: Exit");
				System.out.print("Choice: ");
				type = scn.next();
			}
		}
		if (type.equalsIgnoreCase("E"))
		{
			System.exit(0);
		}
		
		_format = type.equalsIgnoreCase("J") ? GeoFormat.L2J : GeoFormat.L2OFF;
		
		// start conversion
		System.out.println("GeoDataConverter: Converting all " + _format + " files.");
		
		// initialize geodata container
		_blocks = new ABlock[GeoStructure.REGION_BLOCKS_X][GeoStructure.REGION_BLOCKS_Y];
		
		// initialize multilayer temporarily buffer
		BlockMultilayer.initialize();
		
		// load geo files
		int converted = 0;
		for (int rx = L2World.TILE_X_MIN; rx <= L2World.TILE_X_MAX; rx++)
		{
			for (int ry = L2World.TILE_Y_MIN; ry <= L2World.TILE_Y_MAX; ry++)
			{
				final String input = String.format(_format.getFilename(), rx, ry);
				final String filepath = Config.GEODATA_PATH;
				File f = new File(filepath + input);
				if (f.exists() && !f.isDirectory())
				{
					// load geodata
					if (!loadGeoBlocks(input))
					{
						System.out.println("GeoDataConverter: Unable to load " + input + " region file.");
						continue;
					}
					
					// recalculate nswe
					if (!recalculateNswe())
					{
						System.out.println("GeoDataConverter: Unable to convert " + input + " region file.");
						continue;
					}
					
					// save geodata
					final String output = String.format(GeoFormat.L2D.getFilename(), rx, ry);
					if (!saveGeoBlocks(output))
					{
						System.out.println("GeoDataConverter: Unable to save " + output + " region file.");
						continue;
					}
					
					converted++;
					System.out.println("GeoDataConverter: Created " + output + " region file.");
				}
			}
		}
		System.out.println("GeoDataConverter: Converted " + converted + " " + _format + " to L2D region file(s).");
		
		// release multilayer block temporarily buffer
		BlockMultilayer.release();
	}
	
	/**
	 * Loads geo blocks from buffer of the region file.
	 * @param filename : The name of the to load.
	 * @return boolean : True when successful.
	 */
	private static boolean loadGeoBlocks(String filename)
	{
		// region file is load-able, try to load it
		try (RandomAccessFile raf = new RandomAccessFile(Config.GEODATA_PATH + filename, "r");
			FileChannel fc = raf.getChannel())
		{
			MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			
			// load 18B header for L2off geodata (1st and 2nd byte...region X and Y)
			if (_format == GeoFormat.L2OFF)
			{
				for (int i = 0; i < 18; i++)
				{
					buffer.get();
				}
			}
			
			// loop over region blocks
			for (int ix = 0; ix < GeoStructure.REGION_BLOCKS_X; ix++)
			{
				for (int iy = 0; iy < GeoStructure.REGION_BLOCKS_Y; iy++)
				{
					if (_format == GeoFormat.L2J)
					{
						// get block type
						final byte type = buffer.get();
						
						// load block according to block type
						switch (type)
						{
							case GeoStructure.TYPE_FLAT_L2J_L2OFF:
							{
								_blocks[ix][iy] = new BlockFlat(buffer, _format);
								break;
							}
							case GeoStructure.TYPE_COMPLEX_L2J:
							{
								_blocks[ix][iy] = new BlockComplex(buffer, _format);
								break;
							}
							case GeoStructure.TYPE_MULTILAYER_L2J:
							{
								_blocks[ix][iy] = new BlockMultilayer(buffer, _format);
								break;
							}
							default:
							{
								throw new IllegalArgumentException("Unknown block type: " + type);
							}
						}
					}
					else
					{
						// get block type
						final short type = buffer.getShort();
						
						// load block according to block type
						switch (type)
						{
							case GeoStructure.TYPE_FLAT_L2J_L2OFF:
							{
								_blocks[ix][iy] = new BlockFlat(buffer, _format);
								break;
							}
							case GeoStructure.TYPE_COMPLEX_L2OFF:
							{
								_blocks[ix][iy] = new BlockComplex(buffer, _format);
								break;
							}
							default:
							{
								_blocks[ix][iy] = new BlockMultilayer(buffer, _format);
								break;
							}
						}
					}
				}
			}
			
			if (buffer.remaining() > 0)
			{
				System.out.println("GeoDataConverter: Region file " + filename + " can be corrupted, remaining " + buffer.remaining() + " bytes to read.");
				return false;
			}
			
			return true;
		}
		catch (Exception e)
		{
			System.out.println("GeoDataConverter: Error while loading " + filename + " region file.");
			return false;
		}
	}
	
	/**
	 * Recalculate diagonal flags for the region file.
	 * @return boolean : True when successful.
	 */
	private static boolean recalculateNswe()
	{
		try
		{
			for (int x = 0; x < GeoStructure.REGION_CELLS_X; x++)
			{
				for (int y = 0; y < GeoStructure.REGION_CELLS_Y; y++)
				{
					// get block
					ABlock block = _blocks[x / GeoStructure.BLOCK_CELLS_X][y / GeoStructure.BLOCK_CELLS_Y];
					
					// skip flat blocks
					if (block instanceof BlockFlat)
					{
						continue;
					}
					
					// for complex and multilayer blocks go though all layers
					short height = Short.MAX_VALUE;
					int index;
					while ((index = block.getIndexBelow(x, y, height)) != -1)
					{
						// get height and nswe
						height = block.getHeight(index);
						byte nswe = block.getNswe(index);
						
						// update nswe with diagonal flags
						nswe = updateNsweBelow(x, y, height, nswe);
						
						// set nswe of the cell
						block.setNswe(index, nswe);
					}
				}
			}
			
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Updates the NSWE flag with diagonal flags.
	 * @param x : Geodata X coordinate.
	 * @param y : Geodata Y coordinate.
	 * @param z : Geodata Z coordinate.
	 * @param nswe : NSWE flag to be updated.
	 * @return byte : Updated NSWE flag.
	 */
	private static byte updateNsweBelow(int x, int y, short z, byte nswe)
	{
		// calculate virtual layer height
		short height = (short) (z + GeoStructure.CELL_IGNORE_HEIGHT);
		
		// get NSWE of neighbor cells below virtual layer (NPC/PC can fall down of clif, but can not climb it -> NSWE of cell below)
		byte nsweN = getNsweBelow(x, y - 1, height);
		byte nsweS = getNsweBelow(x, y + 1, height);
		byte nsweW = getNsweBelow(x - 1, y, height);
		byte nsweE = getNsweBelow(x + 1, y, height);
		
		// north-west
		if ((((nswe & GeoStructure.CELL_FLAG_N) != 0) && ((nsweN & GeoStructure.CELL_FLAG_W) != 0)) || (((nswe & GeoStructure.CELL_FLAG_W) != 0) && ((nsweW & GeoStructure.CELL_FLAG_N) != 0)))
		{
			nswe |= GeoStructure.CELL_FLAG_NW;
		}
		
		// north-east
		if ((((nswe & GeoStructure.CELL_FLAG_N) != 0) && ((nsweN & GeoStructure.CELL_FLAG_E) != 0)) || (((nswe & GeoStructure.CELL_FLAG_E) != 0) && ((nsweE & GeoStructure.CELL_FLAG_N) != 0)))
		{
			nswe |= GeoStructure.CELL_FLAG_NE;
		}
		
		// south-west
		if ((((nswe & GeoStructure.CELL_FLAG_S) != 0) && ((nsweS & GeoStructure.CELL_FLAG_W) != 0)) || (((nswe & GeoStructure.CELL_FLAG_W) != 0) && ((nsweW & GeoStructure.CELL_FLAG_S) != 0)))
		{
			nswe |= GeoStructure.CELL_FLAG_SW;
		}
		
		// south-east
		if ((((nswe & GeoStructure.CELL_FLAG_S) != 0) && ((nsweS & GeoStructure.CELL_FLAG_E) != 0)) || (((nswe & GeoStructure.CELL_FLAG_E) != 0) && ((nsweE & GeoStructure.CELL_FLAG_S) != 0)))
		{
			nswe |= GeoStructure.CELL_FLAG_SE;
		}
		
		return nswe;
	}
	
	private static byte getNsweBelow(int geoX, int geoY, short worldZ)
	{
		// out of geo coordinates
		if ((geoX < 0) || (geoX >= GeoStructure.REGION_CELLS_X))
		{
			return 0;
		}
		
		// out of geo coordinates
		if ((geoY < 0) || (geoY >= GeoStructure.REGION_CELLS_Y))
		{
			return 0;
		}
		
		// get block
		final ABlock block = _blocks[geoX / GeoStructure.BLOCK_CELLS_X][geoY / GeoStructure.BLOCK_CELLS_Y];
		
		// get index, when valid, return nswe
		final int index = block.getIndexBelow(geoX, geoY, worldZ);
		return index == -1 ? 0 : block.getNswe(index);
	}
	
	/**
	 * Save region file to file.
	 * @param filename : The name of file to save.
	 * @return boolean : True when successful.
	 */
	private static boolean saveGeoBlocks(String filename)
	{
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(Config.GEODATA_PATH + filename), GeoStructure.REGION_BLOCKS * GeoStructure.BLOCK_CELLS * 3))
		{
			// loop over region blocks and save each block
			for (int ix = 0; ix < GeoStructure.REGION_BLOCKS_X; ix++)
			{
				for (int iy = 0; iy < GeoStructure.REGION_BLOCKS_Y; iy++)
				{
					_blocks[ix][iy].saveBlock(bos);
				}
			}
			
			// flush data to file
			bos.flush();
			
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	private static void loadGeoengineConfigs()
	{
		final PropertiesParser geoData = new PropertiesParser(Config.GEOENGINE_CONFIG_FILE);
		Config.GEODATA_PATH = geoData.getString("GeoDataPath", "./data/geodata/");
		Config.COORD_SYNCHRONIZE = geoData.getInt("CoordSynchronize", -1);
		Config.PART_OF_CHARACTER_HEIGHT = geoData.getInt("PartOfCharacterHeight", 75);
		Config.MAX_OBSTACLE_HEIGHT = geoData.getInt("MaxObstacleHeight", 32);
		Config.PATHFINDING = geoData.getBoolean("PathFinding", true);
		Config.PATHFIND_BUFFERS = geoData.getString("PathFindBuffers", "100x6;128x6;192x6;256x4;320x4;384x4;500x2");
		Config.BASE_WEIGHT = geoData.getInt("BaseWeight", 10);
		Config.DIAGONAL_WEIGHT = geoData.getInt("DiagonalWeight", 14);
		Config.OBSTACLE_MULTIPLIER = geoData.getInt("ObstacleMultiplier", 10);
		Config.HEURISTIC_WEIGHT = geoData.getInt("HeuristicWeight", 20);
		Config.MAX_ITERATIONS = geoData.getInt("MaxIterations", 3500);
	}
}
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
package com.l2jmobius.gameserver.datatables;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.data.xml.impl.OptionData;
import com.l2jmobius.gameserver.model.L2Augmentation;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.items.L2Item;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.options.Options;
import com.l2jmobius.gameserver.network.clientpackets.AbstractRefinePacket;

/**
 * Loads augmentation bonuses and skills.
 * @author durgus, Gigiikun, Sandro, UnAfraid
 */
public class AugmentationData
{
	// Zoey76: TODO: Implement using IXmlReader.
	private static final Logger LOGGER = Logger.getLogger(AugmentationData.class.getName());
	
	// stats
	private static final int STAT_BLOCKSIZE = 3640;
	private static final int STAT_SUBBLOCKSIZE = 91;
	public static final int MIN_SKILL_ID = STAT_BLOCKSIZE * 4;
	
	// skills
	private static final int BLUE_START = 14561;
	private static final int SKILLS_BLOCKSIZE = 178;
	
	// basestats
	private static final int BASESTAT_STR = 16341;
	private static final int BASESTAT_MEN = 16344;
	
	// accessory
	private static final int ACC_START = 16669;
	private static final int ACC_BLOCKS_NUM = 10;
	private static final int ACC_STAT_SUBBLOCKSIZE = 21;
	
	private static final int ACC_RING_START = ACC_START;
	private static final int ACC_RING_SKILLS = 18;
	private static final int ACC_RING_BLOCKSIZE = ACC_RING_SKILLS + (4 * ACC_STAT_SUBBLOCKSIZE);
	private static final int ACC_RING_END = (ACC_RING_START + (ACC_BLOCKS_NUM * ACC_RING_BLOCKSIZE)) - 1;
	
	private static final int ACC_EAR_START = ACC_RING_END + 1;
	private static final int ACC_EAR_SKILLS = 18;
	private static final int ACC_EAR_BLOCKSIZE = ACC_EAR_SKILLS + (4 * ACC_STAT_SUBBLOCKSIZE);
	private static final int ACC_EAR_END = (ACC_EAR_START + (ACC_BLOCKS_NUM * ACC_EAR_BLOCKSIZE)) - 1;
	
	private static final int ACC_NECK_START = ACC_EAR_END + 1;
	private static final int ACC_NECK_SKILLS = 24;
	private static final int ACC_NECK_BLOCKSIZE = ACC_NECK_SKILLS + (4 * ACC_STAT_SUBBLOCKSIZE);
	
	private final List<List<Integer>> _blueSkills = new ArrayList<>();
	private final List<List<Integer>> _purpleSkills = new ArrayList<>();
	private final List<List<Integer>> _redSkills = new ArrayList<>();
	
	private final List<AugmentationChance> _augmentationChances = new ArrayList<>();
	private final List<augmentationChanceAcc> _augmentationChancesAcc = new ArrayList<>();
	
	private final Map<Integer, SkillHolder> _allSkills = new HashMap<>();
	
	protected AugmentationData()
	{
		for (int i = 0; i < 10; i++)
		{
			_blueSkills.add(new ArrayList<Integer>());
			_purpleSkills.add(new ArrayList<Integer>());
			_redSkills.add(new ArrayList<Integer>());
		}
		
		load();
		if (!Config.RETAIL_LIKE_AUGMENTATION)
		{
			for (int i = 0; i < 10; i++)
			{
				LOGGER.info(getClass().getSimpleName() + ": Loaded: " + _blueSkills.get(i).size() + " blue, " + _purpleSkills.get(i).size() + " purple and " + _redSkills.get(i).size() + " red skills for lifeStoneLevel " + i);
			}
		}
		else
		{
			LOGGER.log(Level.INFO, getClass().getSimpleName() + ": Loaded: " + _augmentationChances.size() + " augmentations.");
			LOGGER.log(Level.INFO, getClass().getSimpleName() + ": Loaded: " + _augmentationChancesAcc.size() + " accessory augmentations.");
		}
	}
	
	public class AugmentationChance
	{
		private final String _WeaponType;
		private final int _StoneId;
		private final int _VariationId;
		private final int _CategoryChance;
		private final int _AugmentId;
		private final float _AugmentChance;
		
		public AugmentationChance(String WeaponType, int StoneId, int VariationId, int CategoryChance, int AugmentId, float AugmentChance)
		{
			_WeaponType = WeaponType;
			_StoneId = StoneId;
			_VariationId = VariationId;
			_CategoryChance = CategoryChance;
			_AugmentId = AugmentId;
			_AugmentChance = AugmentChance;
		}
		
		public String getWeaponType()
		{
			return _WeaponType;
		}
		
		public int getStoneId()
		{
			return _StoneId;
		}
		
		public int getVariationId()
		{
			return _VariationId;
		}
		
		public int getCategoryChance()
		{
			return _CategoryChance;
		}
		
		public int getAugmentId()
		{
			return _AugmentId;
		}
		
		public float getAugmentChance()
		{
			return _AugmentChance;
		}
	}
	
	public class augmentationChanceAcc
	{
		private final String _WeaponType;
		private final int _StoneId;
		private final int _VariationId;
		private final int _CategoryChance;
		private final int _AugmentId;
		private final float _AugmentChance;
		
		public augmentationChanceAcc(String WeaponType, int StoneId, int VariationId, int CategoryChance, int AugmentId, float AugmentChance)
		{
			_WeaponType = WeaponType;
			_StoneId = StoneId;
			_VariationId = VariationId;
			_CategoryChance = CategoryChance;
			_AugmentId = AugmentId;
			_AugmentChance = AugmentChance;
		}
		
		public String getWeaponType()
		{
			return _WeaponType;
		}
		
		public int getStoneId()
		{
			return _StoneId;
		}
		
		public int getVariationId()
		{
			return _VariationId;
		}
		
		public int getCategoryChance()
		{
			return _CategoryChance;
		}
		
		public int getAugmentId()
		{
			return _AugmentId;
		}
		
		public float getAugmentChance()
		{
			return _AugmentChance;
		}
	}
	
	private final void load()
	{
		// Load stats
		final DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
		factory2.setValidating(false);
		factory2.setIgnoringComments(true);
		
		// Load the skillmap
		// Note: the skillmap data is only used when generating new augmentations
		// the client expects a different id in order to display the skill in the
		// items description...
		if (!Config.RETAIL_LIKE_AUGMENTATION)
		{
			try
			{
				int badAugmantData = 0;
				final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setValidating(false);
				factory.setIgnoringComments(true);
				
				final File file = new File(Config.DATAPACK_ROOT + "/data/stats/augmentation/augmentation_skillmap.xml");
				if (!file.exists())
				{
					LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": ERROR The augmentation skillmap file is missing.");
					return;
				}
				
				final Document doc = factory.newDocumentBuilder().parse(file);
				
				for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
				{
					if ("list".equalsIgnoreCase(n.getNodeName()))
					{
						for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
						{
							if ("augmentation".equalsIgnoreCase(d.getNodeName()))
							{
								NamedNodeMap attrs = d.getAttributes();
								int skillId = 0;
								final int augmentationId = Integer.parseInt(attrs.getNamedItem("id").getNodeValue());
								int skillLvL = 0;
								String type = "blue";
								
								for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
								{
									if ("skillId".equalsIgnoreCase(cd.getNodeName()))
									{
										attrs = cd.getAttributes();
										skillId = Integer.parseInt(attrs.getNamedItem("val").getNodeValue());
									}
									else if ("skillLevel".equalsIgnoreCase(cd.getNodeName()))
									{
										attrs = cd.getAttributes();
										skillLvL = Integer.parseInt(attrs.getNamedItem("val").getNodeValue());
									}
									else if ("type".equalsIgnoreCase(cd.getNodeName()))
									{
										attrs = cd.getAttributes();
										type = attrs.getNamedItem("val").getNodeValue();
									}
								}
								if (skillId == 0)
								{
									badAugmantData++;
									continue;
								}
								else if (skillLvL == 0)
								{
									badAugmantData++;
									continue;
								}
								final int k = (augmentationId - BLUE_START) / SKILLS_BLOCKSIZE;
								
								if (type.equalsIgnoreCase("blue"))
								{
									_blueSkills.get(k).add(augmentationId);
								}
								else if (type.equalsIgnoreCase("purple"))
								{
									_purpleSkills.get(k).add(augmentationId);
								}
								else
								{
									_redSkills.get(k).add(augmentationId);
								}
								
								_allSkills.put(augmentationId, new SkillHolder(skillId, skillLvL));
							}
						}
					}
				}
				if (badAugmantData != 0)
				{
					LOGGER.info(getClass().getSimpleName() + ": " + badAugmantData + " bad skill(s) were skipped.");
				}
			}
			catch (Exception e)
			{
				LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": ERROR parsing augmentation_skillmap.xml.", e);
				return;
			}
		}
		else
		{
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringComments(true);
			
			final File aFile = new File(Config.DATAPACK_ROOT + "/data/stats/augmentation/retailchances.xml");
			if (aFile.exists())
			{
				Document aDoc = null;
				
				try
				{
					aDoc = factory.newDocumentBuilder().parse(aFile);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					return;
				}
				String aWeaponType = null;
				int aStoneId = 0;
				int aVariationId = 0;
				int aCategoryChance = 0;
				int aAugmentId = 0;
				float aAugmentChance = 0;
				
				for (Node l = aDoc.getFirstChild(); l != null; l = l.getNextSibling())
				{
					if (l.getNodeName().equals("list"))
					{
						NamedNodeMap aNodeAttributes = null;
						
						// System.out.println("We're going through the list now.");
						for (Node n = l.getFirstChild(); n != null; n = n.getNextSibling())
						{
							if (n.getNodeName().equals("weapon"))
							{
								aNodeAttributes = n.getAttributes();
								
								aWeaponType = aNodeAttributes.getNamedItem("type").getNodeValue();
								
								// System.out.println("Now showing Augmentations for " + aWeaponType + " Weapons.");
								for (Node c = n.getFirstChild(); c != null; c = c.getNextSibling())
								{
									if (c.getNodeName().equals("stone"))
									{
										aNodeAttributes = c.getAttributes();
										
										aStoneId = Integer.parseInt(aNodeAttributes.getNamedItem("id").getNodeValue());
										
										for (Node v = c.getFirstChild(); v != null; v = v.getNextSibling())
										{
											if (v.getNodeName().equals("variation"))
											{
												aNodeAttributes = v.getAttributes();
												
												aVariationId = Integer.parseInt(aNodeAttributes.getNamedItem("id").getNodeValue());
												
												for (Node j = v.getFirstChild(); j != null; j = j.getNextSibling())
												{
													if (j.getNodeName().equals("category"))
													{
														aNodeAttributes = j.getAttributes();
														
														aCategoryChance = Integer.parseInt(aNodeAttributes.getNamedItem("probability").getNodeValue());
														
														// System.out.println("Stone Id: " + aStoneId + ", Variation Id: " + aVariationId + ", Category Chances: " + aCategoryChance);
														for (Node e = j.getFirstChild(); e != null; e = e.getNextSibling())
														{
															if (e.getNodeName().equals("augment"))
															{
																aNodeAttributes = e.getAttributes();
																
																aAugmentId = Integer.parseInt(aNodeAttributes.getNamedItem("id").getNodeValue());
																aAugmentChance = Float.parseFloat(aNodeAttributes.getNamedItem("chance").getNodeValue());
																
																_augmentationChances.add(new AugmentationChance(aWeaponType, aStoneId, aVariationId, aCategoryChance, aAugmentId, aAugmentChance));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			else
			{
				LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": ERROR The retailchances.xml data file is missing.");
				return;
			}
		}
		if (Config.RETAIL_LIKE_AUGMENTATION_ACCESSORY)
		{
			final DocumentBuilderFactory factory3 = DocumentBuilderFactory.newInstance();
			factory3.setValidating(false);
			factory3.setIgnoringComments(true);
			
			final File aFile3 = new File(Config.DATAPACK_ROOT + "/data/stats/augmentation/retailchances_accessory.xml");
			if (aFile3.exists())
			{
				Document aDoc = null;
				
				try
				{
					aDoc = factory3.newDocumentBuilder().parse(aFile3);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					return;
				}
				String aWeaponType = null;
				int aStoneId = 0;
				int aVariationId = 0;
				int aCategoryChance = 0;
				int aAugmentId = 0;
				float aAugmentChance = 0;
				
				for (Node l = aDoc.getFirstChild(); l != null; l = l.getNextSibling())
				{
					if (l.getNodeName().equals("list"))
					{
						NamedNodeMap aNodeAttributes = null;
						for (Node n = l.getFirstChild(); n != null; n = n.getNextSibling())
						{
							if (n.getNodeName().equals("weapon"))
							{
								aNodeAttributes = n.getAttributes();
								
								aWeaponType = aNodeAttributes.getNamedItem("type").getNodeValue();
								
								for (Node c = n.getFirstChild(); c != null; c = c.getNextSibling())
								{
									if (c.getNodeName().equals("stone"))
									{
										aNodeAttributes = c.getAttributes();
										
										aStoneId = Integer.parseInt(aNodeAttributes.getNamedItem("id").getNodeValue());
										
										for (Node v = c.getFirstChild(); v != null; v = v.getNextSibling())
										{
											if (v.getNodeName().equals("variation"))
											{
												aNodeAttributes = v.getAttributes();
												
												aVariationId = Integer.parseInt(aNodeAttributes.getNamedItem("id").getNodeValue());
												
												for (Node j = v.getFirstChild(); j != null; j = j.getNextSibling())
												{
													if (j.getNodeName().equals("category"))
													{
														aNodeAttributes = j.getAttributes();
														
														aCategoryChance = Integer.parseInt(aNodeAttributes.getNamedItem("probability").getNodeValue());
														
														for (Node e = j.getFirstChild(); e != null; e = e.getNextSibling())
														{
															if (e.getNodeName().equals("augment"))
															{
																aNodeAttributes = e.getAttributes();
																
																aAugmentId = Integer.parseInt(aNodeAttributes.getNamedItem("id").getNodeValue());
																aAugmentChance = Float.parseFloat(aNodeAttributes.getNamedItem("chance").getNodeValue());
																
																_augmentationChancesAcc.add(new augmentationChanceAcc(aWeaponType, aStoneId, aVariationId, aCategoryChance, aAugmentId, aAugmentChance));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			else
			{
				LOGGER.log(Level.WARNING, getClass().getSimpleName() + ": ERROR The retailchances_accessory.xml data file is missing.");
				return;
			}
		}
	}
	
	/**
	 * Generate a new random augmentation
	 * @param lifeStoneLevel
	 * @param lifeStoneGrade
	 * @param bodyPart
	 * @param lifeStoneId
	 * @param targetItem
	 * @return
	 */
	public L2Augmentation generateRandomAugmentation(int lifeStoneLevel, int lifeStoneGrade, int bodyPart, int lifeStoneId, L2ItemInstance targetItem)
	{
		switch (bodyPart)
		{
			case L2Item.SLOT_LR_FINGER:
			case L2Item.SLOT_LR_EAR:
			case L2Item.SLOT_NECK:
			{
				return generateRandomAccessoryAugmentation(lifeStoneLevel, bodyPart, lifeStoneId);
			}
			default:
			{
				return generateRandomWeaponAugmentation(lifeStoneLevel, lifeStoneGrade, lifeStoneId, targetItem);
			}
		}
	}
	
	private L2Augmentation generateRandomWeaponAugmentation(int lifeStoneLevel, int lifeStoneGrade, int lifeStoneId, L2ItemInstance item)
	{
		int stat12 = 0;
		int stat34 = 0;
		if (Config.RETAIL_LIKE_AUGMENTATION)
		{
			if (item.getItem().isMagicWeapon())
			{
				final List<AugmentationChance> _selectedChances12 = new ArrayList<>();
				final List<AugmentationChance> _selectedChances34 = new ArrayList<>();
				for (AugmentationChance ac : _augmentationChances)
				{
					if (ac.getWeaponType().equals("mage") && (ac.getStoneId() == lifeStoneId))
					{
						if (ac.getVariationId() == 1)
						{
							_selectedChances12.add(ac);
						}
						else
						{
							_selectedChances34.add(ac);
						}
					}
				}
				int r = Rnd.get(10000);
				float s = 10000;
				for (AugmentationChance ac : _selectedChances12)
				{
					if (s > r)
					{
						s -= (ac.getAugmentChance() * 100);
						stat12 = ac.getAugmentId();
					}
				}
				int[] gradeChance = null;
				switch (lifeStoneGrade)
				{
					case AbstractRefinePacket.GRADE_NONE:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_NG_CHANCE;
						break;
					}
					case AbstractRefinePacket.GRADE_MID:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_MID_CHANCE;
						break;
					}
					case AbstractRefinePacket.GRADE_HIGH:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_HIGH_CHANCE;
						break;
					}
					case AbstractRefinePacket.GRADE_TOP:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_TOP_CHANCE;
						break;
					}
					default:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_NG_CHANCE;
					}
				}
				
				int c = Rnd.get(100);
				if (c < gradeChance[0])
				{
					c = 55;
				}
				else if (c < (gradeChance[0] + gradeChance[1]))
				{
					c = 35;
				}
				else if (c < (gradeChance[0] + gradeChance[1] + gradeChance[2]))
				{
					c = 7;
				}
				else
				{
					c = 3;
				}
				final List<AugmentationChance> _selectedChances34final = new ArrayList<>();
				for (AugmentationChance ac : _selectedChances34)
				{
					if (ac.getCategoryChance() == c)
					{
						_selectedChances34final.add(ac);
					}
				}
				
				r = Rnd.get(10000);
				s = 10000;
				
				for (AugmentationChance ac : _selectedChances34final)
				{
					if (s > r)
					{
						s -= (ac.getAugmentChance() * 100);
						stat34 = ac.getAugmentId();
					}
				}
			}
			else
			{
				final List<AugmentationChance> _selectedChances12 = new ArrayList<>();
				final List<AugmentationChance> _selectedChances34 = new ArrayList<>();
				for (AugmentationChance ac : _augmentationChances)
				{
					if (ac.getWeaponType().equals("warrior") && (ac.getStoneId() == lifeStoneId))
					{
						if (ac.getVariationId() == 1)
						{
							_selectedChances12.add(ac);
						}
						else
						{
							_selectedChances34.add(ac);
						}
					}
				}
				int r = Rnd.get(10000);
				float s = 10000;
				for (AugmentationChance ac : _selectedChances12)
				{
					if (s > r)
					{
						s -= (ac.getAugmentChance() * 100);
						stat12 = ac.getAugmentId();
					}
				}
				int[] gradeChance = null;
				switch (lifeStoneGrade)
				{
					case AbstractRefinePacket.GRADE_NONE:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_NG_CHANCE;
						break;
					}
					case AbstractRefinePacket.GRADE_MID:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_MID_CHANCE;
						break;
					}
					case AbstractRefinePacket.GRADE_HIGH:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_HIGH_CHANCE;
						break;
					}
					case AbstractRefinePacket.GRADE_TOP:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_TOP_CHANCE;
						break;
					}
					default:
					{
						gradeChance = Config.RETAIL_LIKE_AUGMENTATION_NG_CHANCE;
					}
				}
				
				int c = Rnd.get(100);
				if (c < gradeChance[0])
				{
					c = 55;
				}
				else if (c < (gradeChance[0] + gradeChance[1]))
				{
					c = 35;
				}
				else if (c < (gradeChance[0] + gradeChance[1] + gradeChance[2]))
				{
					c = 7;
				}
				else
				{
					c = 3;
				}
				final List<AugmentationChance> _selectedChances34final = new ArrayList<>();
				for (AugmentationChance ac : _selectedChances34)
				{
					if (ac.getCategoryChance() == c)
					{
						_selectedChances34final.add(ac);
					}
				}
				r = Rnd.get(10000);
				s = 10000;
				for (AugmentationChance ac : _selectedChances34final)
				{
					if (s > r)
					{
						s -= (ac.getAugmentChance() * 100);
						stat34 = ac.getAugmentId();
					}
				}
			}
			return new L2Augmentation(((stat34 << 16) + stat12));
		}
		boolean generateSkill = false;
		boolean generateGlow = false;
		
		// life stone level is used for stat Id and skill level, but here the max level is 9
		lifeStoneLevel = Math.min(lifeStoneLevel, 9);
		
		switch (lifeStoneGrade)
		{
			case AbstractRefinePacket.GRADE_NONE:
			{
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_NG_SKILL_CHANCE)
				{
					generateSkill = true;
				}
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_NG_GLOW_CHANCE)
				{
					generateGlow = true;
				}
				break;
			}
			case AbstractRefinePacket.GRADE_MID:
			{
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_MID_SKILL_CHANCE)
				{
					generateSkill = true;
				}
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_MID_GLOW_CHANCE)
				{
					generateGlow = true;
				}
				break;
			}
			case AbstractRefinePacket.GRADE_HIGH:
			{
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_HIGH_SKILL_CHANCE)
				{
					generateSkill = true;
				}
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_HIGH_GLOW_CHANCE)
				{
					generateGlow = true;
				}
				break;
			}
			case AbstractRefinePacket.GRADE_TOP:
			{
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_TOP_SKILL_CHANCE)
				{
					generateSkill = true;
				}
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_TOP_GLOW_CHANCE)
				{
					generateGlow = true;
				}
				break;
			}
			case AbstractRefinePacket.GRADE_ACC:
			{
				if (Rnd.get(1, 100) <= Config.AUGMENTATION_ACC_SKILL_CHANCE)
				{
					generateSkill = true;
				}
			}
		}
		
		if (!generateSkill && (Rnd.get(1, 100) <= Config.AUGMENTATION_BASESTAT_CHANCE))
		{
			stat34 = Rnd.get(BASESTAT_STR, BASESTAT_MEN);
		}
		
		// Second: decide which grade the augmentation result is going to have:
		// 0:yellow, 1:blue, 2:purple, 3:red
		// The chances used here are most likely custom,
		// what's known is: you can't have yellow with skill(or baseStatModifier)
		// noGrade stone can not have glow, mid only with skill, high has a chance(custom), top allways glow
		int resultColor = Rnd.get(0, 100);
		if ((stat34 == 0) && !generateSkill)
		{
			if (resultColor <= ((15 * lifeStoneGrade) + 40))
			{
				resultColor = 1;
			}
			else
			{
				resultColor = 0;
			}
		}
		else
		{
			if ((resultColor <= ((10 * lifeStoneGrade) + 5)) || (stat34 != 0))
			{
				resultColor = 3;
			}
			else if (resultColor <= ((10 * lifeStoneGrade) + 10))
			{
				resultColor = 1;
			}
			else
			{
				resultColor = 2;
			}
		}
		
		// generate a skill if necessary
		if (generateSkill)
		{
			switch (resultColor)
			{
				case 1: // blue skill
				{
					stat34 = _blueSkills.get(lifeStoneLevel).get(Rnd.get(0, _blueSkills.get(lifeStoneLevel).size() - 1));
					break;
				}
				case 2: // purple skill
				{
					stat34 = _purpleSkills.get(lifeStoneLevel).get(Rnd.get(0, _purpleSkills.get(lifeStoneLevel).size() - 1));
					break;
				}
				case 3: // red skill
				{
					stat34 = _redSkills.get(lifeStoneLevel).get(Rnd.get(0, _redSkills.get(lifeStoneLevel).size() - 1));
					break;
				}
			}
		}
		
		// Third: Calculate the subblock offset for the chosen color,
		// and the level of the lifeStone
		// from large number of retail augmentations:
		// no skill part
		// Id for stat12:
		// A:1-910 B:911-1820 C:1821-2730 D:2731-3640 E:3641-4550 F:4551-5460 G:5461-6370 H:6371-7280
		// Id for stat34(this defines the color):
		// I:7281-8190(yellow) K:8191-9100(blue) L:10921-11830(yellow) M:11831-12740(blue)
		// you can combine I-K with A-D and L-M with E-H
		// using C-D or G-H Id you will get a glow effect
		// there seems no correlation in which grade use which Id except for the glowing restriction
		// skill part
		// Id for stat12:
		// same for no skill part
		// A same as E, B same as F, C same as G, D same as H
		// A - no glow, no grade LS
		// B - weak glow, mid grade LS?
		// C - glow, high grade LS?
		// D - strong glow, top grade LS?
		
		// is neither a skill nor basestat used for stat34? then generate a normal stat
		int offset;
		if (stat34 == 0)
		{
			final int temp = Rnd.get(2, 3);
			final int colorOffset = (resultColor * (10 * STAT_SUBBLOCKSIZE)) + (temp * STAT_BLOCKSIZE) + 1;
			offset = (lifeStoneLevel * STAT_SUBBLOCKSIZE) + colorOffset;
			
			stat34 = Rnd.get(offset, (offset + STAT_SUBBLOCKSIZE) - 1);
			if (generateGlow && (lifeStoneGrade >= 2))
			{
				offset = (lifeStoneLevel * STAT_SUBBLOCKSIZE) + ((temp - 2) * STAT_BLOCKSIZE) + (lifeStoneGrade * (10 * STAT_SUBBLOCKSIZE)) + 1;
			}
			else
			{
				offset = (lifeStoneLevel * STAT_SUBBLOCKSIZE) + ((temp - 2) * STAT_BLOCKSIZE) + (Rnd.get(0, 1) * (10 * STAT_SUBBLOCKSIZE)) + 1;
			}
		}
		else
		{
			if (!generateGlow)
			{
				offset = (lifeStoneLevel * STAT_SUBBLOCKSIZE) + (Rnd.get(0, 1) * STAT_BLOCKSIZE) + 1;
			}
			else
			{
				offset = (lifeStoneLevel * STAT_SUBBLOCKSIZE) + (Rnd.get(0, 1) * STAT_BLOCKSIZE) + (((lifeStoneGrade + resultColor) / 2) * (10 * STAT_SUBBLOCKSIZE)) + 1;
			}
		}
		stat12 = Rnd.get(offset, (offset + STAT_SUBBLOCKSIZE) - 1);
		
		return new L2Augmentation(((stat34 << 16) + stat12));
	}
	
	private L2Augmentation generateRandomAccessoryAugmentation(int lifeStoneLevel, int bodyPart, int lifeStoneId)
	{
		int stat12 = 0;
		int stat34 = 0;
		if (Config.RETAIL_LIKE_AUGMENTATION_ACCESSORY)
		{
			final List<augmentationChanceAcc> _selectedChances12 = new ArrayList<>();
			final List<augmentationChanceAcc> _selectedChances34 = new ArrayList<>();
			for (augmentationChanceAcc ac : _augmentationChancesAcc)
			{
				if (ac.getWeaponType().equals("warrior") && (ac.getStoneId() == lifeStoneId))
				{
					if (ac.getVariationId() == 1)
					{
						_selectedChances12.add(ac);
					}
					else
					{
						_selectedChances34.add(ac);
					}
				}
			}
			int r = Rnd.get(10000);
			float s = 10000;
			for (augmentationChanceAcc ac : _selectedChances12)
			{
				if (s > r)
				{
					s -= (ac.getAugmentChance() * 100);
					stat12 = ac.getAugmentId();
				}
			}
			int c = Rnd.get(100);
			if (c < 55)
			{
				c = 55;
			}
			else if (c < 90)
			{
				c = 35;
			}
			else if (c < 99)
			{
				c = 9;
			}
			else
			{
				c = 1;
			}
			final List<augmentationChanceAcc> _selectedChances34final = new ArrayList<>();
			for (augmentationChanceAcc ac : _selectedChances34)
			{
				if (ac.getCategoryChance() == c)
				{
					_selectedChances34final.add(ac);
				}
			}
			r = Rnd.get(10000);
			s = 10000;
			for (augmentationChanceAcc ac : _selectedChances34final)
			{
				if (s > r)
				{
					s -= (ac.getAugmentChance() * 100);
					stat34 = ac.getAugmentId();
				}
			}
			
			return new L2Augmentation(((stat34 << 16) + stat12));
		}
		lifeStoneLevel = Math.min(lifeStoneLevel, 9);
		int base = 0;
		int skillsLength = 0;
		
		switch (bodyPart)
		{
			case L2Item.SLOT_LR_FINGER:
			{
				base = ACC_RING_START + (ACC_RING_BLOCKSIZE * lifeStoneLevel);
				skillsLength = ACC_RING_SKILLS;
				break;
			}
			case L2Item.SLOT_LR_EAR:
			{
				base = ACC_EAR_START + (ACC_EAR_BLOCKSIZE * lifeStoneLevel);
				skillsLength = ACC_EAR_SKILLS;
				break;
			}
			case L2Item.SLOT_NECK:
			{
				base = ACC_NECK_START + (ACC_NECK_BLOCKSIZE * lifeStoneLevel);
				skillsLength = ACC_NECK_SKILLS;
				break;
			}
			default:
			{
				return null;
			}
		}
		
		final int resultColor = Rnd.get(0, 3);
		
		// first augmentation (stats only)
		stat12 = Rnd.get(ACC_STAT_SUBBLOCKSIZE);
		Options op = null;
		if (Rnd.get(1, 100) <= Config.AUGMENTATION_ACC_SKILL_CHANCE)
		{
			// second augmentation (skill)
			stat34 = base + Rnd.get(skillsLength);
			op = OptionData.getInstance().getOptions(stat34);
		}
		
		if ((op == null) || (!op.hasActiveSkill() && !op.hasPassiveSkill() && !op.hasActivationSkills()))
		{
			// second augmentation (stats)
			// calculating any different from stat12 value inside sub-block
			// starting from next and wrapping over using remainder
			stat34 = (stat12 + 1 + Rnd.get(ACC_STAT_SUBBLOCKSIZE - 1)) % ACC_STAT_SUBBLOCKSIZE;
			// this is a stats - skipping skills
			stat34 = base + skillsLength + (ACC_STAT_SUBBLOCKSIZE * resultColor) + stat34;
		}
		
		// stat12 has stats only
		stat12 = base + skillsLength + (ACC_STAT_SUBBLOCKSIZE * resultColor) + stat12;
		
		return new L2Augmentation(((stat34 << 16) + stat12));
	}
	
	public static AugmentationData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final AugmentationData _instance = new AugmentationData();
	}
}

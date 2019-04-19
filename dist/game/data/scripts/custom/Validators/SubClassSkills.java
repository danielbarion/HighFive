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
package custom.Validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.data.xml.impl.ClassListData;
import com.l2jmobius.gameserver.enums.IllegalActionPunishmentType;
import com.l2jmobius.gameserver.model.PcCondOverride;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Sub-class skills validator.<br>
 * TODO: Rewrite.
 * @author DS
 */
public final class SubClassSkills extends AbstractNpcAI
{
	// arrays must be sorted
	// @formatter:off
	private static final int[] _allCertSkillIds =
	{
		631, 632, 633, 634, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646,
		647, 648, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661,
		662, 799, 800, 801, 802, 803, 804, 1489, 1490, 1491
	};
	private static final int[][] _certSkillsByLevel =
	{
		{
			631, 632, 633, 634
		},
		{
			631, 632, 633, 634
		},
		{
			637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 650,
			651, 652, 653, 654, 655, 799, 800, 801, 802, 803, 804, 1489, 1490,
			1491
		},
		{
			656, 657, 658, 659, 660, 661, 662
		}
	};
	
	private static final int[] _allCertItemIds =
	{
		10280, 10281, 10282, 10283, 10284, 10285, 10286, 10287, 10288, 10289,
		10290, 10291, 10292, 10293, 10294, 10612
	};
	private static final int[][] _certItemsByLevel =
	{
		{ 10280 },
		{ 10280 },
		{ 10612, 10281, 10282, 10283, 10284, 10285, 10286, 10287 },
		{ 10288, 10289, 10290, 10291, 10292, 10293, 10294 }
	};
	// @formatter:on
	
	private static final String[] VARS =
	{
		"EmergentAbility65-",
		"EmergentAbility70-",
		"ClassAbility75-",
		"ClassAbility80-"
	};
	
	private SubClassSkills()
	{
		setOnEnterWorld(true);
	}
	
	@Override
	public String onEnterWorld(L2PcInstance player)
	{
		if (!Config.SKILL_CHECK_ENABLE)
		{
			return null;
		}
		
		if (player.canOverrideCond(PcCondOverride.SKILL_CONDITIONS) && !Config.SKILL_CHECK_GM)
		{
			return null;
		}
		
		final List<Skill> certSkills = getCertSkills(player);
		if (player.isSubClassActive())
		{
			for (Skill s : certSkills)
			{
				Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " has cert skill on subclass :" + s.getName() + "(" + s.getId() + "/" + s.getLevel() + "), class:" + ClassListData.getInstance().getClass(player.getClassId()).getClassName(), IllegalActionPunishmentType.NONE);
				
				if (Config.SKILL_CHECK_REMOVE)
				{
					player.removeSkill(s);
				}
			}
			return null;
		}
		
		final int[][] cSkills = new int[certSkills.size()][2]; // skillId/skillLvl
		for (int i = certSkills.size(); --i >= 0;)
		{
			final Skill skill = certSkills.get(i);
			cSkills[i][0] = skill.getId();
			cSkills[i][1] = skill.getLevel();
		}
		
		final List<L2ItemInstance> certItems = getCertItems(player);
		final int[][] cItems = new int[certItems.size()][2]; // objectId/number
		for (int i = certItems.size(); --i >= 0;)
		{
			final L2ItemInstance item = certItems.get(i);
			cItems[i][0] = item.getObjectId();
			cItems[i][1] = (int) Math.min(item.getCount(), Integer.MAX_VALUE);
		}
		
		QuestState st = player.getQuestState("SubClassSkills");
		if (st == null)
		{
			st = newQuestState(player);
		}
		
		String qName;
		String qValue;
		int id;
		int index;
		for (int i = VARS.length; --i >= 0;)
		{
			for (int j = Config.MAX_SUBCLASS; j > 0; j--)
			{
				qName = VARS[i] + j;
				qValue = player.getVariables().getString(qName, "");
				if ((qValue == null) || qValue.isEmpty())
				{
					continue;
				}
				
				if (qValue.endsWith(";")) // found skill
				{
					try
					{
						id = Integer.parseInt(qValue.replace(";", ""));
						
						Skill skill = null;
						if (certSkills != null)
						{
							// searching skill in test array
							if (cSkills != null)
							{
								for (index = certSkills.size(); --index >= 0;)
								{
									if (cSkills[index][0] == id)
									{
										skill = certSkills.get(index);
										cSkills[index][1]--;
										break;
									}
								}
							}
							if (skill != null)
							{
								if (!CommonUtil.contains(_certSkillsByLevel[i], id))
								{
									// should remove this skill ?
									Util.handleIllegalPlayerAction(player, "Invalid cert variable WITH skill:" + qName + "=" + qValue + " - skill does not match certificate level", IllegalActionPunishmentType.NONE);
								}
							}
							else
							{
								Util.handleIllegalPlayerAction(player, "Invalid cert variable:" + qName + "=" + qValue + " - skill not found", IllegalActionPunishmentType.NONE);
							}
						}
						else
						{
							Util.handleIllegalPlayerAction(player, "Invalid cert variable:" + qName + "=" + qValue + " - no certified skills found", IllegalActionPunishmentType.NONE);
						}
					}
					catch (NumberFormatException e)
					{
						Util.handleIllegalPlayerAction(player, "Invalid cert variable:" + qName + "=" + qValue + " - not a number", IllegalActionPunishmentType.NONE);
					}
				}
				else
				// found item
				{
					try
					{
						id = Integer.parseInt(qValue);
						if (id == 0)
						{
							continue;
						}
						
						L2ItemInstance item = null;
						if (certItems != null)
						{
							// searching item in test array
							if (cItems != null)
							{
								for (index = certItems.size(); --index >= 0;)
								{
									if (cItems[index][0] == id)
									{
										item = certItems.get(index);
										cItems[index][1]--;
										break;
									}
								}
							}
							if (item != null)
							{
								if (!CommonUtil.contains(_certItemsByLevel[i], item.getId()))
								{
									Util.handleIllegalPlayerAction(player, "Invalid cert variable:" + qName + "=" + qValue + " - item found but does not match certificate level", IllegalActionPunishmentType.NONE);
								}
							}
							else
							{
								Util.handleIllegalPlayerAction(player, "Invalid cert variable:" + qName + "=" + qValue + " - item not found", IllegalActionPunishmentType.NONE);
							}
						}
						else
						{
							Util.handleIllegalPlayerAction(player, "Invalid cert variable:" + qName + "=" + qValue + " - no cert item found in inventory", IllegalActionPunishmentType.NONE);
						}
						
					}
					catch (NumberFormatException e)
					{
						Util.handleIllegalPlayerAction(player, "Invalid cert variable:" + qName + "=" + qValue + " - not a number", IllegalActionPunishmentType.NONE);
					}
				}
			}
		}
		
		if ((certSkills != null) && (cSkills != null))
		{
			for (int i = cSkills.length; --i >= 0;)
			{
				if (cSkills[i][1] == 0)
				{
					continue;
				}
				
				final Skill skill = certSkills.get(i);
				if (cSkills[i][1] > 0)
				{
					if (cSkills[i][1] == skill.getLevel())
					{
						Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " has invalid cert skill :" + skill.getName() + "(" + skill.getId() + "/" + skill.getLevel() + ")", IllegalActionPunishmentType.NONE);
					}
					else
					{
						Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " has invalid cert skill :" + skill.getName() + "(" + skill.getId() + "/" + skill.getLevel() + "), level too high", IllegalActionPunishmentType.NONE);
					}
					
					if (Config.SKILL_CHECK_REMOVE)
					{
						player.removeSkill(skill);
					}
				}
				else
				{
					Util.handleIllegalPlayerAction(player, "Invalid cert skill :" + skill.getName() + "(" + skill.getId() + "/" + skill.getLevel() + "), level too low", IllegalActionPunishmentType.NONE);
				}
			}
		}
		
		if ((certItems != null) && (cItems != null))
		{
			for (int i = cItems.length; --i >= 0;)
			{
				if (cItems[i][1] == 0)
				{
					continue;
				}
				
				final L2ItemInstance item = certItems.get(i);
				Util.handleIllegalPlayerAction(player, "Invalid cert item without variable or with wrong count:" + item.getObjectId(), IllegalActionPunishmentType.NONE);
			}
		}
		
		return null;
	}
	
	private List<Skill> getCertSkills(L2PcInstance player)
	{
		final List<Skill> tmp = new ArrayList<>();
		for (Skill s : player.getAllSkills())
		{
			if ((s != null) && (Arrays.binarySearch(_allCertSkillIds, s.getId()) >= 0))
			{
				tmp.add(s);
			}
		}
		return tmp;
	}
	
	private List<L2ItemInstance> getCertItems(L2PcInstance player)
	{
		final List<L2ItemInstance> tmp = new ArrayList<>();
		for (L2ItemInstance i : player.getInventory().getItems())
		{
			if ((i != null) && (Arrays.binarySearch(_allCertItemIds, i.getId()) >= 0))
			{
				tmp.add(i);
			}
		}
		return tmp;
	}
	
	public static void main(String[] args)
	{
		new SubClassSkills();
	}
}
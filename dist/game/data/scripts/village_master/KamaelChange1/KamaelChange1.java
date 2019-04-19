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
package village_master.KamaelChange1;

import com.l2jmobius.gameserver.data.xml.impl.CategoryData;
import com.l2jmobius.gameserver.enums.CategoryType;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.quest.QuestState;

import ai.AbstractNpcAI;
import quests.Q00062_PathOfTheTrooper.Q00062_PathOfTheTrooper;
import quests.Q00063_PathOfTheWarder.Q00063_PathOfTheWarder;

/**
 * Kamael class transfer AI.
 * @author Adry_85
 */
public final class KamaelChange1 extends AbstractNpcAI
{
	// NPCs
	private static int[] NPCS =
	{
		32191, // Hanarin
		32193, // Yeniche
		32196, // Gershwin
		32199, // Holst
		32202, // Khadava
	};
	
	// Items
	private static final int SHADOW_ITEM_EXCHANGE_COUPON_D_GRADE = 8869;
	private static final int GWAINS_RECOMMENDATION = 9753;
	private static final int STEELRAZOR_EVALUATION = 9772;
	
	private KamaelChange1()
	{
		addStartNpc(NPCS);
		addTalkId(NPCS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		switch (event)
		{
			case "32191-02.htm": // master_all_kamael003m
			case "32191-03.htm": // master_all_kamael006ma
			case "32191-04.htm": // master_all_kamael007ma
			case "32191-05.htm": // master_all_kamael007mait
			case "32191-06.htm": // master_all_kamael003f
			case "32191-07.htm": // master_all_kamael006fa
			case "32191-08.htm": // master_all_kamael007fa
			case "32191-09.htm": // master_all_kamael007fa
			{
				htmltext = event;
				break;
			}
			case "125":
			case "126":
			{
				htmltext = ClassChangeRequested(player, Integer.valueOf(event));
				break;
			}
		}
		return htmltext;
	}
	
	private String ClassChangeRequested(L2PcInstance player, int classId)
	{
		String htmltext = null;
		if (CategoryData.getInstance().isInCategory(CategoryType.KAMAEL_SECOND_CLASS_GROUP, classId))
		{
			if (player.isInCategory(CategoryType.KAMAEL_SECOND_CLASS_GROUP))
			{
				htmltext = "32191-10.htm"; // master_all_kamael004a
			}
			else if (player.isInCategory(CategoryType.KAMAEL_THIRD_CLASS_GROUP))
			{
				htmltext = "32191-11.htm"; // master_all_kamael005a
			}
			else if (player.isInCategory(CategoryType.KAMAEL_FOURTH_CLASS_GROUP))
			{
				htmltext = "32191-12.htm"; // master_all_kamael100a
			}
			else if ((classId == 125) && (player.getClassId() == ClassId.MALE_SOLDIER))
			{
				final QuestState qs = player.getQuestState(Q00062_PathOfTheTrooper.class.getSimpleName());
				if (player.getLevel() < 20)
				{
					if ((qs != null) && qs.isCompleted())
					{
						htmltext = "32191-13.htm"; // master_all_kamael009ma
					}
					else
					{
						htmltext = "32191-14.htm"; // master_all_kamael008ma
					}
				}
				else if ((qs == null) || !qs.isCompleted())
				{
					htmltext = "32191-15.htm"; // master_all_kamael010ma
				}
				else
				{
					takeItems(player, GWAINS_RECOMMENDATION, -1);
					player.setClassId(125);
					player.setBaseClass(125);
					// SystemMessage and cast skill is done by setClassId
					player.broadcastUserInfo();
					giveItems(player, SHADOW_ITEM_EXCHANGE_COUPON_D_GRADE, 15);
					htmltext = "32191-16.htm"; // master_all_kamael011ma
				}
			}
			else if ((classId == 126) && (player.getClassId() == ClassId.FEMALE_SOLDIER))
			{
				final QuestState qs = player.getQuestState(Q00063_PathOfTheWarder.class.getSimpleName());
				if (player.getLevel() < 20)
				{
					if ((qs != null) && qs.isCompleted())
					{
						htmltext = "32191-17.htm"; // master_all_kamael008fa
					}
					else
					{
						htmltext = "32191-18.htm"; // master_all_kamael009fa
					}
				}
				else if ((qs == null) || !qs.isCompleted())
				{
					htmltext = "32191-19.htm"; // master_all_kamael010fa
				}
				else
				{
					takeItems(player, STEELRAZOR_EVALUATION, -1);
					player.setClassId(126);
					player.setBaseClass(126);
					// SystemMessage and cast skill is done by setClassId
					player.broadcastUserInfo();
					giveItems(player, SHADOW_ITEM_EXCHANGE_COUPON_D_GRADE, 15);
					htmltext = "32191-20.htm"; // master_all_kamael011fa
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		if (player.getRace() != Race.KAMAEL)
		{
			htmltext = "32191-01.htm"; // master_all_kamael002a
		}
		else if (player.isInCategory(CategoryType.KAMAEL_FIRST_CLASS_GROUP))
		{
			if (player.getClassId() == ClassId.MALE_SOLDIER)
			{
				htmltext = "32191-02.htm"; // master_all_kamael003m
			}
			else if (player.getClassId() == ClassId.FEMALE_SOLDIER)
			{
				htmltext = "32191-06.htm"; // master_all_kamael003f
			}
		}
		else if (player.isInCategory(CategoryType.KAMAEL_SECOND_CLASS_GROUP))
		{
			htmltext = "32191-10.htm"; // master_all_kamael004a
		}
		else if (player.isInCategory(CategoryType.KAMAEL_THIRD_CLASS_GROUP))
		{
			htmltext = "32191-11.htm"; // master_all_kamael005a
		}
		else
		{
			htmltext = "32191-12.htm"; // master_all_kamael100a
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new KamaelChange1();
	}
}

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
package ai.others.SubclassCertification;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.data.xml.impl.ClassListData;
import com.l2jmobius.gameserver.enums.CategoryType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2VillageMasterInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

import ai.AbstractNpcAI;

/**
 * Subclass certification
 * @author xban1x, jurchiks
 */
public final class SubclassCertification extends AbstractNpcAI
{
	// @formatter:off
	private static final int[] NPCS =
	{
		30026, 30031, 30037, 30066, 30070, 30109, 30115, 30120, 30154, 30174,
		30175, 30176, 30187, 30191, 30195, 30288, 30289, 30290, 30297, 30358,
		30373, 30462, 30474, 30498, 30499, 30500, 30503, 30504, 30505, 30508,
		30511, 30512, 30513, 30520, 30525, 30565, 30594, 30595, 30676, 30677,
		30681, 30685, 30687, 30689, 30694, 30699, 30704, 30845, 30847, 30849,
		30854, 30857, 30862, 30865, 30894, 30897, 30900, 30905, 30910, 30913,
		31269, 31272, 31276, 31279, 31285, 31288, 31314, 31317, 31321, 31324,
		31326, 31328, 31331, 31334, 31336, 31755, 31958, 31961, 31965, 31968,
		31974, 31977, 31996, 32092, 32093, 32094, 32095, 32096, 32097, 32098,
		32145, 32146, 32147, 32150, 32153, 32154, 32157, 32158, 32160, 32171,
		32193, 32199, 32202, 32213, 32214, 32221, 32222, 32229, 32230, 32233,
		32234
	};
	// @formatter:on
	private static final int CERTIFICATE_EMERGENT_ABILITY = 10280;
	private static final int CERTIFICATE_MASTER_ABILITY = 10612;
	private static final Map<Integer, Integer> ABILITY_CERTIFICATES = new HashMap<>();
	private static final Map<Integer, Integer> TRANSFORMATION_SEALBOOKS = new HashMap<>();
	static
	{
		ABILITY_CERTIFICATES.put(0, 10281); // Certificate - Warrior Ability
		ABILITY_CERTIFICATES.put(1, 10283); // Certificate - Rogue Ability
		ABILITY_CERTIFICATES.put(2, 10282); // Certificate - Knight Ability
		ABILITY_CERTIFICATES.put(3, 10286); // Certificate - Summoner Ability
		ABILITY_CERTIFICATES.put(4, 10284); // Certificate - Wizard Ability
		ABILITY_CERTIFICATES.put(5, 10285); // Certificate - Healer Ability
		ABILITY_CERTIFICATES.put(6, 10287); // Certificate - Enchanter Ability
		
		TRANSFORMATION_SEALBOOKS.put(0, 10289); // Transformation Sealbook: Divine Warrior
		TRANSFORMATION_SEALBOOKS.put(1, 10290); // Transformation Sealbook: Divine Rogue
		TRANSFORMATION_SEALBOOKS.put(2, 10288); // Transformation Sealbook: Divine Knight
		TRANSFORMATION_SEALBOOKS.put(3, 10294); // Transformation Sealbook: Divine Summoner
		TRANSFORMATION_SEALBOOKS.put(4, 10292); // Transformation Sealbook: Divine Wizard
		TRANSFORMATION_SEALBOOKS.put(5, 10291); // Transformation Sealbook: Divine Healer
		TRANSFORMATION_SEALBOOKS.put(6, 10293); // Transformation Sealbook: Divine Enchanter
	}
	
	private static final int MIN_LVL = 65;
	
	private SubclassCertification()
	{
		addStartNpc(NPCS);
		addTalkId(NPCS);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		st.setState(State.STARTED);
		return "Main.html";
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "GetCertified":
			{
				if (!player.isSubClassActive())
				{
					htmltext = "NotSubclass.html";
				}
				else if (player.getLevel() < MIN_LVL)
				{
					htmltext = "NotMinLevel.html";
				}
				else if (((L2VillageMasterInstance) npc).checkVillageMaster(player.getActiveClass()))
				{
					htmltext = "CertificationList.html";
				}
				else
				{
					htmltext = "WrongVillageMaster.html";
				}
				break;
			}
			case "Obtain65":
			{
				htmltext = replaceHtml(player, "EmergentAbility.html", true, null).replace("%level%", "65").replace("%skilltype%", "common skill").replace("%event%", "lvl65Emergent");
				break;
			}
			case "Obtain70":
			{
				htmltext = replaceHtml(player, "EmergentAbility.html", true, null).replace("%level%", "70").replace("%skilltype%", "common skill").replace("%event%", "lvl70Emergent");
				break;
			}
			case "Obtain75":
			{
				htmltext = replaceHtml(player, "ClassAbility.html", true, null);
				break;
			}
			case "Obtain80":
			{
				htmltext = replaceHtml(player, "EmergentAbility.html", true, null).replace("%level%", "80").replace("%skilltype%", "transformation skill").replace("%event%", "lvl80Class");
				break;
			}
			case "lvl65Emergent":
			{
				htmltext = doCertification(player, st, "EmergentAbility", CERTIFICATE_EMERGENT_ABILITY, 65);
				break;
			}
			case "lvl70Emergent":
			{
				htmltext = doCertification(player, st, "EmergentAbility", CERTIFICATE_EMERGENT_ABILITY, 70);
				break;
			}
			case "lvl75Master":
			{
				htmltext = doCertification(player, st, "ClassAbility", CERTIFICATE_MASTER_ABILITY, 75);
				break;
			}
			case "lvl75Class":
			{
				htmltext = doCertification(player, st, "ClassAbility", ABILITY_CERTIFICATES.get(getClassIndex(player)), 75);
				break;
			}
			case "lvl80Class":
			{
				htmltext = doCertification(player, st, "ClassAbility", TRANSFORMATION_SEALBOOKS.get(getClassIndex(player)), 80);
				break;
			}
			case "Main.html":
			case "Explanation.html":
			case "NotObtain.html":
			{
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	private String replaceHtml(L2PcInstance player, String htmlFile, boolean replaceClass, String levelToReplace)
	{
		String htmltext = getHtm(player, htmlFile);
		if (replaceClass)
		{
			htmltext = htmltext.replace("%class%", ClassListData.getInstance().getClass(player.getActiveClass()).getClientCode());
		}
		if (levelToReplace != null)
		{
			htmltext = htmltext.replace("%level%", levelToReplace);
		}
		return htmltext;
	}
	
	private static int getClassIndex(L2PcInstance player)
	{
		if (player.isInCategory(CategoryType.SUB_GROUP_WARRIOR))
		{
			return 0;
		}
		else if (player.isInCategory(CategoryType.SUB_GROUP_ROGUE))
		{
			return 1;
		}
		else if (player.isInCategory(CategoryType.SUB_GROUP_KNIGHT))
		{
			return 2;
		}
		else if (player.isInCategory(CategoryType.SUB_GROUP_SUMMONER))
		{
			return 3;
		}
		else if (player.isInCategory(CategoryType.SUB_GROUP_WIZARD))
		{
			return 4;
		}
		else if (player.isInCategory(CategoryType.SUB_GROUP_HEALER))
		{
			return 5;
		}
		else if (player.isInCategory(CategoryType.SUB_GROUP_ENCHANTER))
		{
			return 6;
		}
		
		return -1;
	}
	
	private String doCertification(L2PcInstance player, QuestState qs, String variable, Integer itemId, int level)
	{
		if (itemId == null)
		{
			return null;
		}
		
		String htmltext;
		final String var = variable + level + "-" + player.getClassIndex();
		
		if (player.getVariables().hasVariable(var) && !player.getVariables().getString(var).equals("0"))
		{
			htmltext = "AlreadyReceived.html";
		}
		else if (player.getLevel() < level)
		{
			htmltext = replaceHtml(player, "LowLevel.html", false, Integer.toString(level));
		}
		else
		{
			// Add items to player's inventory
			final L2ItemInstance item = player.getInventory().addItem("Quest", itemId, 1, player, player.getTarget());
			if (item == null)
			{
				return null;
			}
			
			final SystemMessage smsg = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S1);
			smsg.addItemName(item);
			player.sendPacket(smsg);
			
			player.getVariables().set(var, String.valueOf(item.getObjectId()));
			htmltext = "GetAbility.html";
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new SubclassCertification();
	}
}

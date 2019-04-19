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
package ai.others.MercenaryCaptain;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.l2jmobius.gameserver.data.xml.impl.MultisellData;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager.Territory;
import com.l2jmobius.gameserver.instancemanager.TerritoryWarManager.TerritoryNPCSpawn;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.ExShowDominionRegistry;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;

import ai.AbstractNpcAI;

/**
 * Mercenary Captain AI.
 * @author malyelfik
 */
public final class MercenaryCaptain extends AbstractNpcAI
{
	// NPCs
	private static final Map<Integer, Integer> NPCS = new HashMap<>();
	static
	{
		NPCS.put(36481, 13757); // Mercenary Captain (Gludio)
		NPCS.put(36482, 13758); // Mercenary Captain (Dion)
		NPCS.put(36483, 13759); // Mercenary Captain (Giran)
		NPCS.put(36484, 13760); // Mercenary Captain (Oren)
		NPCS.put(36485, 13761); // Mercenary Captain (Aden)
		NPCS.put(36486, 13762); // Mercenary Captain (Innadril)
		NPCS.put(36487, 13763); // Mercenary Captain (Goddard)
		NPCS.put(36488, 13764); // Mercenary Captain (Rune)
		NPCS.put(36489, 13765); // Mercenary Captain (Schuttgart)
	}
	// Items
	private static final int STRIDER_WIND = 4422;
	private static final int STRIDER_STAR = 4423;
	private static final int STRIDER_TWILIGHT = 4424;
	private static final int GUARDIAN_STRIDER = 14819;
	private static final int ELITE_MERCENARY_CERTIFICATE = 13767;
	private static final int TOP_ELITE_MERCENARY_CERTIFICATE = 13768;
	// Misc
	private static final int DELAY = 3600000; // 1 hour
	private static final int MIN_LEVEL = 40;
	private static final int CLASS_LEVEL = 2;
	
	private MercenaryCaptain()
	{
		for (int id : NPCS.keySet())
		{
			addStartNpc(id);
			addFirstTalkId(id);
			addTalkId(id);
		}
		
		for (Territory terr : TerritoryWarManager.getInstance().getAllTerritories())
		{
			for (TerritoryNPCSpawn spawn : terr.getSpawnList())
			{
				if (NPCS.keySet().contains(spawn.getId()))
				{
					startQuestTimer("say", DELAY, spawn.getNpc(), null, true);
				}
			}
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		if (player != null)
		{
			final StringTokenizer st = new StringTokenizer(event, " ");
			switch (st.nextToken())
			{
				case "36481-02.html":
				{
					htmltext = event;
					break;
				}
				case "36481-03.html":
				{
					final NpcHtmlMessage html = new NpcHtmlMessage(npc.getObjectId());
					html.setHtml(getHtm(player, "36481-03.html"));
					html.replace("%strider%", String.valueOf(TerritoryWarManager.MINTWBADGEFORSTRIDERS));
					html.replace("%gstrider%", String.valueOf(TerritoryWarManager.MINTWBADGEFORBIGSTRIDER));
					player.sendPacket(html);
					break;
				}
				case "territory":
				{
					player.sendPacket(new ExShowDominionRegistry(npc.getCastle().getResidenceId(), player));
					break;
				}
				case "strider":
				{
					final String type = st.nextToken();
					final int price = (type.equals("3")) ? TerritoryWarManager.MINTWBADGEFORBIGSTRIDER : TerritoryWarManager.MINTWBADGEFORSTRIDERS;
					final int badgeId = NPCS.get(npc.getId());
					if (getQuestItemsCount(player, badgeId) < price)
					{
						return "36481-07.html";
					}
					
					final int striderId;
					switch (type)
					{
						case "0":
						{
							striderId = STRIDER_WIND;
							break;
						}
						case "1":
						{
							striderId = STRIDER_STAR;
							break;
						}
						case "2":
						{
							striderId = STRIDER_TWILIGHT;
							break;
						}
						case "3":
						{
							striderId = GUARDIAN_STRIDER;
							break;
						}
						default:
						{
							LOGGER.warning(MercenaryCaptain.class.getSimpleName() + ": Unknown strider type: " + type);
							return null;
						}
					}
					takeItems(player, badgeId, price);
					giveItems(player, striderId, 1);
					htmltext = "36481-09.html";
					break;
				}
				case "elite":
				{
					if (!hasQuestItems(player, ELITE_MERCENARY_CERTIFICATE))
					{
						htmltext = "36481-10.html";
					}
					else
					{
						final int listId = 676 + npc.getCastle().getResidenceId();
						MultisellData.getInstance().separateAndSend(listId, player, npc, false);
					}
					break;
				}
				case "top-elite":
				{
					if (!hasQuestItems(player, TOP_ELITE_MERCENARY_CERTIFICATE))
					{
						htmltext = "36481-10.html";
					}
					else
					{
						final int listId = 685 + npc.getCastle().getResidenceId();
						MultisellData.getInstance().separateAndSend(listId, player, npc, false);
					}
					break;
				}
			}
		}
		else if (event.equalsIgnoreCase("say") && !npc.isDecayed())
		{
			if (TerritoryWarManager.getInstance().isTWInProgress())
			{
				npc.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.CHARGE_CHARGE_CHARGE);
			}
			else if (getRandom(2) == 0)
			{
				npc.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.COURAGE_AMBITION_PASSION_MERCENARIES_WHO_WANT_TO_REALIZE_THEIR_DREAM_OF_FIGHTING_IN_THE_TERRITORY_WAR_COME_TO_ME_FORTUNE_AND_GLORY_ARE_WAITING_FOR_YOU);
			}
			else
			{
				npc.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.DO_YOU_WISH_TO_FIGHT_ARE_YOU_AFRAID_NO_MATTER_HOW_HARD_YOU_TRY_YOU_HAVE_NOWHERE_TO_RUN_BUT_IF_YOU_FACE_IT_HEAD_ON_OUR_MERCENARY_TROOP_WILL_HELP_YOU_OUT);
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final String htmltext;
		if ((player.getLevel() < MIN_LEVEL) || (player.getClassId().level() < CLASS_LEVEL))
		{
			htmltext = "36481-08.html";
		}
		else if (npc.isMyLord(player))
		{
			htmltext = (npc.getCastle().getSiege().isInProgress() || TerritoryWarManager.getInstance().isTWInProgress()) ? "36481-05.html" : "36481-04.html";
		}
		else
		{
			htmltext = (npc.getCastle().getSiege().isInProgress() || TerritoryWarManager.getInstance().isTWInProgress()) ? "36481-06.html" : npc.getId() + "-01.html";
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new MercenaryCaptain();
	}
}
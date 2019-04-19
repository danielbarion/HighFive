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
package quests.Q00610_MagicalPowerOfWaterPart2;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.instancemanager.GlobalVariablesManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Util;

/**
 * Magical Power of Water - Part 2 (610)
 * @author Joxit
 */
public class Q00610_MagicalPowerOfWaterPart2 extends Quest
{
	// NPCs
	private static final int ASEFA = 31372;
	private static final int VARKA_TOTEM = 31560;
	// Monster
	private static final int ASHUTAR = 25316;
	// Items
	private static final int GREEN_TOTEM = 7238;
	private static final int ASHUTAR_HEART = 7239;
	// Misc
	private static final int MIN_LEVEL = 75;
	
	public Q00610_MagicalPowerOfWaterPart2()
	{
		super(610);
		addStartNpc(ASEFA);
		addTalkId(ASEFA, VARKA_TOTEM);
		addKillId(ASHUTAR);
		registerQuestItems(GREEN_TOTEM, ASHUTAR_HEART);
		
		final long test = GlobalVariablesManager.getInstance().getLong("Q00610_respawn", 0);
		final long remain = test != 0 ? test - System.currentTimeMillis() : 0;
		if (remain > 0)
		{
			startQuestTimer("spawn_npc", remain, null, null);
		}
		else
		{
			addSpawn(VARKA_TOTEM, 105452, -36775, -1050, 34000, false, 0, true);
		}
	}
	
	@Override
	public void actionForEachPlayer(L2PcInstance player, L2Npc npc, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			if (npc.getId() == ASHUTAR)
			{
				switch (st.getCond())
				{
					case 1: // take the item and give the heart
					{
						takeItems(player, GREEN_TOTEM, 1);
					}
					case 2:
					{
						if (!hasQuestItems(player, ASHUTAR_HEART))
						{
							giveItems(player, ASHUTAR_HEART, 1);
						}
						st.setCond(3, true);
						break;
					}
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
			final QuestState st = getQuestState(player, false);
			if (st == null)
			{
				return null;
			}
			
			switch (event)
			{
				case "31372-02.html":
				{
					st.startQuest();
					htmltext = event;
					break;
				}
				case "give_heart":
				{
					if (hasQuestItems(player, ASHUTAR_HEART))
					{
						addExpAndSp(player, 10000, 0);
						st.exitQuest(true, true);
						htmltext = "31372-06.html";
					}
					else
					{
						htmltext = "31372-07.html";
					}
					break;
				}
				case "spawn_totem":
				{
					htmltext = (hasQuestItems(player, GREEN_TOTEM)) ? spawnAshutar(npc, st) : "31560-04.html";
					break;
				}
			}
		}
		else
		{
			if (event.equals("despawn_ashutar"))
			{
				npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.THE_POWER_OF_CONSTRAINT_IS_GETTING_WEAKER_YOUR_RITUAL_HAS_FAILED));
				npc.deleteMe();
				addSpawn(VARKA_TOTEM, 105452, -36775, -1050, 34000, false, 0, true);
			}
			else if (event.equals("spawn_npc"))
			{
				addSpawn(VARKA_TOTEM, 105452, -36775, -1050, 34000, false, 0, true);
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final int respawnMinDelay = (int) (43200000 * Config.RAID_MIN_RESPAWN_MULTIPLIER);
		final int respawnMaxDelay = (int) (129600000 * Config.RAID_MAX_RESPAWN_MULTIPLIER);
		final int respawnDelay = getRandom(respawnMinDelay, respawnMaxDelay);
		cancelQuestTimer("despawn_ashutar", npc, null);
		GlobalVariablesManager.getInstance().set("Q00610_respawn", String.valueOf(System.currentTimeMillis() + respawnDelay));
		startQuestTimer("spawn_npc", respawnDelay, null, null);
		executeForEachPlayer(killer, npc, isSummon, true, false);
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case ASEFA:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? (hasQuestItems(player, GREEN_TOTEM)) ? "31372-01.htm" : "31372-00a.html" : "31372-00b.html";
						break;
					}
					case State.STARTED:
					{
						htmltext = (st.isCond(1)) ? "31372-03.html" : (hasQuestItems(player, ASHUTAR_HEART)) ? "31372-04.html" : "31372-05.html";
						break;
					}
				}
				break;
			}
			case VARKA_TOTEM:
			{
				if (st.isStarted())
				{
					switch (st.getCond())
					{
						case 1:
						{
							htmltext = "31560-01.html";
							break;
						}
						case 2:
						{
							htmltext = spawnAshutar(npc, st);
							break;
						}
						case 3:
						{
							htmltext = "31560-05.html";
							break;
						}
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	private String spawnAshutar(L2Npc npc, QuestState st)
	{
		if (getQuestTimer("spawn_npc", null, null) != null)
		{
			return "31560-03.html";
		}
		if (st.isCond(1))
		{
			takeItems(st.getPlayer(), GREEN_TOTEM, 1);
			st.setCond(2, true);
		}
		npc.deleteMe();
		final L2Npc ashutar = addSpawn(ASHUTAR, 104825, -36926, -1136, 0, false, 0);
		ashutar.broadcastPacket(new NpcSay(ashutar, ChatType.NPC_GENERAL, NpcStringId.THE_MAGICAL_POWER_OF_WATER_COMES_FROM_THE_POWER_OF_STORM_AND_HAIL_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU));
		startQuestTimer("despawn_ashutar", 1200000, ashutar, null);
		return "31560-02.html";
	}
}
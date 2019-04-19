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
package quests.Q00616_MagicalPowerOfFirePart2;

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
 * Magical Power of Fire - Part 2 (616)
 * @author Joxit
 */
public class Q00616_MagicalPowerOfFirePart2 extends Quest
{
	// NPCs
	private static final int UDAN = 31379;
	private static final int KETRA_TOTEM = 31558;
	// Monster
	private static final int NASTRON = 25306;
	// Items
	private static final int RED_TOTEM = 7243;
	private static final int NASTRON_HEART = 7244;
	// Misc
	private static final int MIN_LEVEL = 75;
	
	public Q00616_MagicalPowerOfFirePart2()
	{
		super(616);
		addStartNpc(UDAN);
		addTalkId(UDAN, KETRA_TOTEM);
		addKillId(NASTRON);
		registerQuestItems(RED_TOTEM, NASTRON_HEART);
		
		final long test = GlobalVariablesManager.getInstance().getLong("Q00616_respawn", 0);
		final long remain = test != 0 ? test - System.currentTimeMillis() : 0;
		if (remain > 0)
		{
			startQuestTimer("spawn_npc", remain, null, null);
		}
		else
		{
			addSpawn(KETRA_TOTEM, 142368, -82512, -6487, 58000, false, 0, true);
		}
	}
	
	@Override
	public void actionForEachPlayer(L2PcInstance player, L2Npc npc, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			if (npc.getId() == NASTRON)
			{
				switch (st.getCond())
				{
					case 1: // take the item and give the heart
					{
						takeItems(player, RED_TOTEM, 1);
					}
					case 2:
					{
						if (!hasQuestItems(player, NASTRON_HEART))
						{
							giveItems(player, NASTRON_HEART, 1);
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
				case "31379-02.html":
				{
					st.startQuest();
					htmltext = event;
					break;
				}
				case "give_heart":
				{
					if (hasQuestItems(player, NASTRON_HEART))
					{
						addExpAndSp(player, 10000, 0);
						st.exitQuest(true, true);
						htmltext = "31379-06.html";
					}
					else
					{
						htmltext = "31379-07.html";
					}
					break;
				}
				case "spawn_totem":
				{
					htmltext = (hasQuestItems(player, RED_TOTEM)) ? spawnNastron(npc, st) : "31558-04.html";
					break;
				}
			}
		}
		else
		{
			if (event.equals("despawn_nastron"))
			{
				npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.THE_POWER_OF_CONSTRAINT_IS_GETTING_WEAKER_YOUR_RITUAL_HAS_FAILED));
				npc.deleteMe();
				addSpawn(KETRA_TOTEM, 142368, -82512, -6487, 58000, false, 0, true);
			}
			else if (event.equals("spawn_npc"))
			{
				addSpawn(KETRA_TOTEM, 142368, -82512, -6487, 58000, false, 0, true);
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
		cancelQuestTimer("despawn_nastron", npc, null);
		GlobalVariablesManager.getInstance().set("Q00616_respawn", String.valueOf(System.currentTimeMillis() + respawnDelay));
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
			case UDAN:
			{
				switch (st.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? (hasQuestItems(player, RED_TOTEM)) ? "31379-01.htm" : "31379-00a.html" : "31379-00b.html";
						break;
					}
					case State.STARTED:
					{
						htmltext = (st.isCond(1)) ? "31379-03.html" : (hasQuestItems(player, NASTRON_HEART)) ? "31379-04.html" : "31379-05.html";
						break;
					}
				}
				break;
			}
			case KETRA_TOTEM:
			{
				if (st.isStarted())
				{
					switch (st.getCond())
					{
						case 1:
						{
							htmltext = "31558-01.html";
							break;
						}
						case 2:
						{
							htmltext = spawnNastron(npc, st);
							break;
						}
						case 3:
						{
							htmltext = "31558-05.html";
							break;
						}
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	private String spawnNastron(L2Npc npc, QuestState st)
	{
		if (getQuestTimer("spawn_npc", null, null) != null)
		{
			return "31558-03.html";
		}
		if (st.isCond(1))
		{
			takeItems(st.getPlayer(), RED_TOTEM, 1);
			st.setCond(2, true);
		}
		npc.deleteMe();
		final L2Npc nastron = addSpawn(NASTRON, 142528, -82528, -6496, 0, false, 0);
		nastron.broadcastPacket(new NpcSay(nastron, ChatType.NPC_GENERAL, NpcStringId.THE_MAGICAL_POWER_OF_FIRE_IS_ALSO_THE_POWER_OF_FLAMES_AND_LAVA_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU));
		startQuestTimer("despawn_nastron", 1200000, nastron, null);
		return "31558-02.html";
	}
}
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
package quests.Q00512_BladeUnderFoot;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Blade Under Foot (512)
 * @author Adry_85
 * @since 2.6.0.0
 */
public class Q00512_BladeUnderFoot extends Quest
{
	private static final class DropInfo
	{
		public final int _firstChance;
		public final int _secondChance;
		
		public DropInfo(int firstChance, int secondChance)
		{
			_firstChance = firstChance;
			_secondChance = secondChance;
		}
		
		public int getFirstChance()
		{
			return _firstChance;
		}
		
		public int getSecondChance()
		{
			return _secondChance;
		}
	}
	
	// NPCs
	private static final int[] WARDEN =
	{
		36403, // Gludio
		36404, // Dion
		36405, // Giran
		36406, // Oren
		36407, // Aden
		36408, // Innadril
		36409, // Goddard
		36410, // Rune
		36411, // Schuttgart
	};
	
	// Misc
	private static final int MIN_LEVEL = 70;
	// Item
	private static final int FRAGMENT_OF_THE_DUNGEON_LEADER_MARK = 9798;
	// Reward
	private static final int KNIGHTS_EPAULETTE = 9912;
	// Raid Bosses
	private static final Map<Integer, DropInfo> RAID_BOSSES = new HashMap<>();
	static
	{
		RAID_BOSSES.put(25563, new DropInfo(175, 1443)); // Beautiful Atrielle
		RAID_BOSSES.put(25566, new DropInfo(176, 1447)); // Nagen the Tomboy
		RAID_BOSSES.put(25569, new DropInfo(177, 1450)); // Jax the Destroyer
	}
	
	public Q00512_BladeUnderFoot()
	{
		super(512);
		addStartNpc(WARDEN);
		addTalkId(WARDEN);
		addKillId(RAID_BOSSES.keySet());
		registerQuestItems(FRAGMENT_OF_THE_DUNGEON_LEADER_MARK);
	}
	
	@Override
	public void actionForEachPlayer(L2PcInstance player, L2Npc npc, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if ((st != null) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, player, false))
		{
			int playerCount = player.getParty().getMemberCount();
			int itemCount = RAID_BOSSES.get(npc.getId()).getSecondChance();
			
			if (playerCount > 0)
			{
				itemCount /= playerCount;
			}
			
			giveItems(player, FRAGMENT_OF_THE_DUNGEON_LEADER_MARK, itemCount);
			playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "36403-02.htm":
			{
				if (player.getLevel() >= MIN_LEVEL)
				{
					if ((npc.isMyLord(player) || ((npc.getCastle().getResidenceId() == player.getClan().getCastleId()) && (player.getClan().getCastleId() > 0))))
					{
						st.startQuest();
						htmltext = event;
					}
					else
					{
						htmltext = "36403-03.htm";
					}
				}
				break;
			}
			case "36403-04.html":
			case "36403-05.html":
			case "36403-06.html":
			case "36403-07.html":
			case "36403-10.html":
			{
				htmltext = event;
				break;
			}
			case "36403-11.html":
			{
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState st = getQuestState(player, false);
		if (st != null)
		{
			if (player.getParty() != null)
			{
				executeForEachPlayer(player, npc, isSummon, true, false);
			}
			else
			{
				giveItems(player, FRAGMENT_OF_THE_DUNGEON_LEADER_MARK, RAID_BOSSES.get(npc.getId()).getFirstChance());
				playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (st.isCreated())
		{
			if (player.getLevel() >= MIN_LEVEL)
			{
				htmltext = (npc.isMyLord(player) || ((player.getClan() != null) && (npc.getCastle().getResidenceId() == player.getClan().getCastleId()) && (player.getClan().getCastleId() > 0))) ? "36403-01.htm" : "36403-03.htm";
			}
			else
			{
				htmltext = "36403-08.htm";
			}
		}
		else if (st.isStarted())
		{
			if (hasQuestItems(player, FRAGMENT_OF_THE_DUNGEON_LEADER_MARK))
			{
				giveItems(player, KNIGHTS_EPAULETTE, getQuestItemsCount(player, FRAGMENT_OF_THE_DUNGEON_LEADER_MARK));
				takeItems(player, FRAGMENT_OF_THE_DUNGEON_LEADER_MARK, -1);
				htmltext = "36403-09.html";
			}
			else
			{
				htmltext = "36403-12.html";
			}
		}
		return htmltext;
	}
}
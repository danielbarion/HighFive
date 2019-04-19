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
package quests.Q00509_AClansFame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.PledgeShowInfoUpdate;
import com.l2jmobius.gameserver.network.serverpackets.RadarControl;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

/**
 * A Clan's Fame (509)
 * @author Adry_85
 */
public class Q00509_AClansFame extends Quest
{
	// NPC
	private static final int VALDIS = 31331;
	
	private static final Map<Integer, List<Integer>> REWARD_POINTS = new HashMap<>();
	static
	{
		REWARD_POINTS.put(1, Arrays.asList(25290, 8489, 1378)); // Daimon The White-Eyed
		REWARD_POINTS.put(2, Arrays.asList(25293, 8490, 1378)); // Hestia, Guardian Deity Of The Hot Springs
		REWARD_POINTS.put(3, Arrays.asList(25523, 8491, 1070)); // Plague Golem
		REWARD_POINTS.put(4, Arrays.asList(25322, 8492, 782)); // Demon's Agent Falston
	}
	
	private static final int[] RAID_BOSS =
	{
		25290,
		25293,
		25523,
		25322
	};
	
	public Q00509_AClansFame()
	{
		super(509);
		addStartNpc(VALDIS);
		addTalkId(VALDIS);
		addKillId(RAID_BOSS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "31331-0.html":
			{
				qs.startQuest();
				break;
			}
			case "31331-1.html":
			{
				qs.set("raid", "1");
				player.sendPacket(new RadarControl(0, 2, 186304, -43744, -3193));
				break;
			}
			case "31331-2.html":
			{
				qs.set("raid", "2");
				player.sendPacket(new RadarControl(0, 2, 134672, -115600, -1216));
				break;
			}
			case "31331-3.html":
			{
				qs.set("raid", "3");
				player.sendPacket(new RadarControl(0, 2, 170000, -60000, -3500));
				break;
			}
			case "31331-4.html":
			{
				qs.set("raid", "4");
				player.sendPacket(new RadarControl(0, 2, 93296, -75104, -1824));
				break;
			}
			case "31331-5.html":
			{
				qs.exitQuest(true, true);
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		if (player.getClan() == null)
		{
			return null;
		}
		
		QuestState qs = null;
		if (player.isClanLeader())
		{
			qs = player.getQuestState(getName());
		}
		else
		{
			final L2PcInstance pleader = player.getClan().getLeader().getPlayerInstance();
			if ((pleader != null) && player.isInsideRadius3D(pleader, Config.ALT_PARTY_RANGE))
			{
				qs = pleader.getQuestState(getName());
			}
		}
		
		if ((qs != null) && qs.isStarted())
		{
			final int raid = qs.getInt("raid");
			if (REWARD_POINTS.containsKey(raid) && (npc.getId() == REWARD_POINTS.get(raid).get(0)) && !hasQuestItems(player, REWARD_POINTS.get(raid).get(1)))
			{
				rewardItems(player, REWARD_POINTS.get(raid).get(1), 1);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		final L2Clan clan = player.getClan();
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = ((clan == null) || !player.isClanLeader() || (clan.getLevel() < 6)) ? "31331-0a.htm" : "31331-0b.htm";
				break;
			}
			case State.STARTED:
			{
				if ((clan == null) || !player.isClanLeader())
				{
					qs.exitQuest(true);
					return "31331-6.html";
				}
				
				final int raid = qs.getInt("raid");
				
				if (REWARD_POINTS.containsKey(raid))
				{
					if (hasQuestItems(player, REWARD_POINTS.get(raid).get(1)))
					{
						htmltext = "31331-" + raid + "b.html";
						playSound(player, QuestSound.ITEMSOUND_QUEST_FANFARE_1);
						takeItems(player, REWARD_POINTS.get(raid).get(1), -1);
						final int rep = REWARD_POINTS.get(raid).get(2);
						clan.addReputationScore(rep, true);
						player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_SUCCESSFULLY_COMPLETED_A_CLAN_QUEST_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_S_REPUTATION_SCORE).addInt(rep));
						clan.broadcastToOnlineMembers(new PledgeShowInfoUpdate(clan));
					}
					else
					{
						htmltext = "31331-" + raid + "a.html";
					}
				}
				else
				{
					htmltext = "31331-0.html";
				}
				break;
			}
			default:
			{
				break;
			}
		}
		return htmltext;
	}
}

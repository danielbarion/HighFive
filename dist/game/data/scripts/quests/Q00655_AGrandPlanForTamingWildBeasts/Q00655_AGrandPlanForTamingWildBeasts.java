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
package quests.Q00655_AGrandPlanForTamingWildBeasts;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.l2jmobius.gameserver.cache.HtmCache;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.instancemanager.CHSiegeManager;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.entity.clanhall.ClanHallSiegeEngine;
import com.l2jmobius.gameserver.model.entity.clanhall.SiegableHall;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * A Grand Plan for Taming Wild Beasts (655)
 * @author Zoey76
 */
public final class Q00655_AGrandPlanForTamingWildBeasts extends Quest
{
	// NPCs
	private static final int MESSENGER = 35627;
	// Items
	private static final int CRYSTAL_OF_PURITY = 8084;
	private static final int TRAINER_LICENSE = 8293;
	// Misc
	private static final int REQUIRED_CRYSTAL_COUNT = 10;
	private static final int REQUIRED_CLAN_LEVEL = 4;
	private static final int MINUTES_TO_SIEGE = 3600;
	private static final String PATH_TO_HTML = "data/scripts/conquerablehalls/flagwar/WildBeastReserve/messenger_initial.htm";
	
	public Q00655_AGrandPlanForTamingWildBeasts()
	{
		super(655);
		addStartNpc(MESSENGER);
		addTalkId(MESSENGER);
		registerQuestItems(CRYSTAL_OF_PURITY, TRAINER_LICENSE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		final L2Clan clan = player.getClan();
		final long minutesToSiege = getMinutesToSiege();
		switch (event)
		{
			case "35627-06.html":
			{
				if (qs.isCreated() && (clan != null) && (clan.getLevel() >= REQUIRED_CLAN_LEVEL) && (clan.getFortId() == 0) && player.isClanLeader() && (minutesToSiege > 0) && (minutesToSiege < MINUTES_TO_SIEGE))
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "35627-06a.html":
			{
				htmltext = event;
				break;
			}
			case "35627-11.html":
			{
				if ((minutesToSiege > 0) && (minutesToSiege < MINUTES_TO_SIEGE))
				{
					htmltext = HtmCache.getInstance().getHtm(player, PATH_TO_HTML);
				}
				else
				{
					htmltext = getHtm(player, event);
					htmltext = htmltext.replace("%next_siege%", getSiegeDate());
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		final long minutesToSiege = getMinutesToSiege();
		if (qs.isCreated())
		{
			final L2Clan clan = talker.getClan();
			if (clan == null)
			{
				return htmltext;
			}
			
			if ((minutesToSiege > 0) && (minutesToSiege < MINUTES_TO_SIEGE))
			{
				if (talker.isClanLeader())
				{
					if (clan.getFortId() == 0)
					{
						if (clan.getLevel() >= REQUIRED_CLAN_LEVEL)
						{
							htmltext = "35627-01.html";
						}
						else
						{
							htmltext = "35627-03.html";
						}
					}
					else
					{
						htmltext = "35627-04.html";
					}
				}
				else if ((clan.getFortId() == ClanHallSiegeEngine.BEAST_FARM) && (minutesToSiege > 0) && (minutesToSiege < MINUTES_TO_SIEGE))
				{
					htmltext = HtmCache.getInstance().getHtm(talker, PATH_TO_HTML);
				}
				else
				{
					htmltext = "35627-05.html";
				}
			}
			else
			{
				htmltext = getHtm(talker, "35627-02.html");
				htmltext = htmltext.replace("%next_siege%", getSiegeDate());
			}
		}
		else if ((minutesToSiege < 0) || (minutesToSiege > MINUTES_TO_SIEGE))
		{
			takeItems(talker, TRAINER_LICENSE, -1);
			takeItems(talker, CRYSTAL_OF_PURITY, -1);
			qs.exitQuest(true, true);
			htmltext = "35627-07.html";
		}
		else if (hasQuestItems(talker, TRAINER_LICENSE))
		{
			htmltext = "35627-09.html";
		}
		else if (getQuestItemsCount(talker, CRYSTAL_OF_PURITY) < REQUIRED_CRYSTAL_COUNT)
		{
			htmltext = "35627-08.html";
		}
		else
		{
			giveItems(talker, TRAINER_LICENSE, 1);
			takeItems(talker, CRYSTAL_OF_PURITY, -1);
			qs.setCond(3, true);
			htmltext = "35627-10.html";
		}
		return htmltext;
	}
	
	/**
	 * Gets the Wild Beast Reserve's siege date.
	 * @return the siege date
	 */
	private static String getSiegeDate()
	{
		final SiegableHall hall = CHSiegeManager.getInstance().getSiegableHall(ClanHallSiegeEngine.BEAST_FARM);
		return hall != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hall.getSiegeDate()) : "Error in date.";
	}
	
	/**
	 * Gets the minutes to next siege.
	 * @return minutes to next siege
	 */
	private static long getMinutesToSiege()
	{
		final SiegableHall hall = CHSiegeManager.getInstance().getSiegableHall(ClanHallSiegeEngine.BEAST_FARM);
		return hall != null ? (hall.getNextSiegeTime() - Calendar.getInstance().getTimeInMillis()) / 3600 : -1;
	}
	
	/**
	 * Rewards the clan leader with a Crystal of Purity after player tame a wild beast.
	 * @param player the player
	 * @param npc the wild beast
	 */
	public static void reward(L2PcInstance player, L2Npc npc)
	{
		final L2Clan clan = player.getClan();
		final L2PcInstance clanLeader = clan != null ? clan.getLeader().getPlayerInstance() : null;
		if (clanLeader != null)
		{
			final QuestState qs655 = clanLeader.getQuestState(Q00655_AGrandPlanForTamingWildBeasts.class.getSimpleName());
			if ((qs655 != null) && (getQuestItemsCount(clanLeader, CRYSTAL_OF_PURITY) < REQUIRED_CRYSTAL_COUNT) && Util.checkIfInRange(2000, clanLeader, npc, true))
			{
				if (clanLeader.getLevel() >= REQUIRED_CLAN_LEVEL)
				{
					giveItems(clanLeader, CRYSTAL_OF_PURITY, 1);
				}
				if (getQuestItemsCount(clanLeader, CRYSTAL_OF_PURITY) >= 9)
				{
					qs655.setCond(2, true);
				}
				else
				{
					playSound(clanLeader, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
	}
}
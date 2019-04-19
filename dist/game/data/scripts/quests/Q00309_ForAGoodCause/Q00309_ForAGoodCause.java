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
package quests.Q00309_ForAGoodCause;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.serverpackets.RadarControl;

import quests.Q00239_WontYouJoinUs.Q00239_WontYouJoinUs;
import quests.Q00308_ReedFieldMaintenance.Q00308_ReedFieldMaintenance;

/**
 * For A Good Cause (309)
 * @author nonom, Zoey76, Joxit
 * @version 2011/09/30 based on official server Naia
 */
public class Q00309_ForAGoodCause extends Quest
{
	// NPC
	private static final int ATRA = 32647;
	// Mobs
	private static final int CORRUPTED_MUCROKIAN = 22654;
	private static final Map<Integer, Integer> MUCROKIANS = new HashMap<>();
	static
	{
		MUCROKIANS.put(22650, 218); // Mucrokian Fanatic
		MUCROKIANS.put(22651, 258); // Mucrokian Ascetic
		MUCROKIANS.put(22652, 248); // Mucrokian Savior
		MUCROKIANS.put(22653, 290); // Mucrokian Preacher
		MUCROKIANS.put(22654, 124); // Contaminated Mucrokian
		MUCROKIANS.put(22655, 220); // Awakened Mucrokian
	}
	
	// Items
	private static final int MUCROKIAN_HIDE = 14873;
	private static final int FALLEN_MUCROKIAN_HIDE = 14874;
	// Rewards
	private static final int REC_DYNASTY_EARRINGS_70 = 9985;
	private static final int REC_DYNASTY_NECKLACE_70 = 9986;
	private static final int REC_DYNASTY_RING_70 = 9987;
	private static final int REC_DYNASTY_SIGIL_60 = 10115;
	
	private static final int[] MOIRAI_RECIPES =
	{
		15777,
		15780,
		15783,
		15786,
		15789,
		15790,
		15814,
		15813,
		15812
	};
	
	private static final int[] MOIRAI_PIECES =
	{
		15647,
		15650,
		15653,
		15656,
		15659,
		15692,
		15772,
		15773,
		15774
	};
	
	// Misc
	private static final int MIN_LEVEL = 82;
	
	public Q00309_ForAGoodCause()
	{
		super(309);
		addStartNpc(ATRA);
		addTalkId(ATRA);
		addKillId(MUCROKIANS.keySet());
	}
	
	private boolean canGiveItem(L2PcInstance player, int quanty)
	{
		final long mucrokian = getQuestItemsCount(player, MUCROKIAN_HIDE);
		final long fallen = getQuestItemsCount(player, FALLEN_MUCROKIAN_HIDE);
		if (fallen > 0)
		{
			if (fallen >= (quanty / 2))
			{
				takeItems(player, FALLEN_MUCROKIAN_HIDE, (quanty / 2));
				return true;
			}
			else if (mucrokian >= (quanty - (fallen * 2)))
			{
				takeItems(player, FALLEN_MUCROKIAN_HIDE, fallen);
				takeItems(player, MUCROKIAN_HIDE, (quanty - (fallen * 2)));
				return true;
			}
		}
		else if (mucrokian >= quanty)
		{
			takeItems(player, MUCROKIAN_HIDE, quanty);
			return true;
		}
		return false;
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
			case "32647-02.htm":
			case "32647-03.htm":
			case "32647-04.htm":
			case "32647-08.html":
			case "32647-10.html":
			case "32647-12.html":
			case "32647-13.html":
			{
				htmltext = event;
				break;
			}
			case "32647-05.html":
			{
				st.startQuest();
				player.sendPacket(new RadarControl(0, 2, 77325, 205773, -3432));
				htmltext = event;
				break;
			}
			case "claimreward":
			{
				final QuestState q239 = player.getQuestState(Q00239_WontYouJoinUs.class.getSimpleName());
				htmltext = ((q239 != null) && q239.isCompleted()) ? "32647-11.html" : "32647-09.html";
				break;
			}
			case "100":
			case "120":
			{
				htmltext = onItemExchangeRequest(player, MOIRAI_PIECES[getRandom(MOIRAI_PIECES.length - 1)], Integer.parseInt(event));
				break;
			}
			case "192":
			case "230":
			{
				htmltext = onItemExchangeRequest(player, REC_DYNASTY_EARRINGS_70, Integer.parseInt(event));
				break;
			}
			case "256":
			case "308":
			{
				htmltext = onItemExchangeRequest(player, REC_DYNASTY_NECKLACE_70, Integer.parseInt(event));
				break;
			}
			case "128":
			case "154":
			{
				htmltext = onItemExchangeRequest(player, REC_DYNASTY_RING_70, Integer.parseInt(event));
				break;
			}
			case "206":
			case "246":
			{
				htmltext = onItemExchangeRequest(player, REC_DYNASTY_SIGIL_60, Integer.parseInt(event));
				break;
			}
			case "180":
			case "216":
			{
				htmltext = onItemExchangeRequest(player, MOIRAI_RECIPES[getRandom(MOIRAI_RECIPES.length - 1)], Integer.parseInt(event));
				break;
			}
			case "32647-14.html":
			case "32647-07.html":
			{
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	private String onItemExchangeRequest(L2PcInstance player, int item, int quanty)
	{
		String htmltext;
		if (canGiveItem(player, quanty))
		{
			if (CommonUtil.contains(MOIRAI_PIECES, item))
			{
				giveItems(player, item, getRandom(1, 4));
			}
			else
			{
				giveItems(player, item, 1);
			}
			playSound(player, QuestSound.ITEMSOUND_QUEST_FINISH);
			htmltext = "32646-16.htm";
		}
		else
		{
			htmltext = "32646-15.htm";
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(killer, 1);
		if (partyMember != null)
		{
			final float chance = (MUCROKIANS.get(npc.getId()) * Config.RATE_QUEST_DROP);
			if (getRandom(1000) < chance)
			{
				if (npc.getId() == CORRUPTED_MUCROKIAN)
				{
					giveItems(partyMember, FALLEN_MUCROKIAN_HIDE, 1);
					rewardItems(partyMember, FALLEN_MUCROKIAN_HIDE, 1);
				}
				else
				{
					giveItems(partyMember, MUCROKIAN_HIDE, 1);
				}
				playSound(partyMember, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState st = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		
		final QuestState q308 = talker.getQuestState(Q00308_ReedFieldMaintenance.class.getSimpleName());
		if ((q308 != null) && q308.isStarted())
		{
			htmltext = "32647-17.html";
		}
		else if (st.isStarted())
		{
			htmltext = (hasQuestItems(talker, MUCROKIAN_HIDE) || hasQuestItems(talker, FALLEN_MUCROKIAN_HIDE)) ? "32647-08.html" : "32647-06.html";
		}
		else
		{
			htmltext = (talker.getLevel() >= MIN_LEVEL) ? "32647-01.htm" : "32647-00.html";
		}
		return htmltext;
	}
}

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
package quests.Q00235_MimirsElixir;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.QuestItemHolder;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;

/**
 * Mimir's Elixir (235)
 * @author Adry_85
 */
public final class Q00235_MimirsElixir extends Quest
{
	// NPCs
	private static final int JOAN = 30718;
	private static final int LADD = 30721;
	private static final int ALCHEMISTS_MIXING_URN = 31149;
	// Items
	private static final int STAR_OF_DESTINY = 5011;
	private static final int MAGISTERS_MIXING_STONE = 5905;
	private static final int BLOOD_FIRE = 6318;
	private static final int MIMIRS_ELIXIR = 6319;
	private static final int PURE_SILVER = 6320;
	private static final int TRUE_GOLD = 6321;
	private static final int SAGES_STONE = 6322;
	// Reward
	private static final int ENCHANT_WEAPON_A = 729;
	// Misc
	private static final int MIN_LEVEL = 75;
	// Skill
	private static SkillHolder QUEST_MIMIRS_ELIXIR = new SkillHolder(4339, 1);
	// Mobs
	private static final Map<Integer, QuestItemHolder> MOBS = new HashMap<>();
	static
	{
		MOBS.put(20965, new QuestItemHolder(SAGES_STONE, 4, 1)); // chimera_piece
		MOBS.put(21090, new QuestItemHolder(BLOOD_FIRE, 7, 1)); // bloody_guardian
	}
	
	public Q00235_MimirsElixir()
	{
		super(235);
		addStartNpc(LADD);
		addTalkId(LADD, JOAN, ALCHEMISTS_MIXING_URN);
		addKillId(MOBS.keySet());
		registerQuestItems(MAGISTERS_MIXING_STONE, BLOOD_FIRE, MIMIRS_ELIXIR, TRUE_GOLD, SAGES_STONE);
	}
	
	@Override
	public boolean checkPartyMember(L2PcInstance member, L2Npc npc)
	{
		final QuestState st = getQuestState(member, false);
		return ((st != null) && (st.isMemoState(3) || st.isMemoState(6)));
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
			case "30721-02.htm":
			case "30721-03.htm":
			case "30721-04.htm":
			case "30721-05.htm":
			{
				htmltext = event;
				break;
			}
			case "30721-06.htm":
			{
				st.setMemoState(1);
				st.startQuest();
				htmltext = event;
				break;
			}
			case "30721-12.html":
			{
				if (st.isMemoState(1))
				{
					st.setMemoState(2);
					st.setCond(2);
					htmltext = event;
				}
				break;
			}
			case "30721-15.html":
			{
				if (st.isMemoState(5))
				{
					giveItems(player, MAGISTERS_MIXING_STONE, 1);
					st.setMemoState(6);
					st.setCond(6);
					htmltext = event;
				}
				break;
			}
			case "30721-18.html":
			{
				if (st.isMemoState(8))
				{
					htmltext = event;
				}
				break;
			}
			case "30721-19.html":
			{
				if (st.isMemoState(8) && hasQuestItems(player, MAGISTERS_MIXING_STONE, MIMIRS_ELIXIR))
				{
					npc.setTarget(player);
					npc.doCast(QUEST_MIMIRS_ELIXIR.getSkill());
					takeItems(player, STAR_OF_DESTINY, -1);
					rewardItems(player, ENCHANT_WEAPON_A, 1);
					st.exitQuest(false, true);
					player.sendPacket(new SocialAction(player.getObjectId(), 3));
					htmltext = event;
				}
				break;
			}
			case "30718-02.html":
			{
				if (st.isMemoState(2))
				{
					htmltext = event;
				}
				break;
			}
			case "30718-03.html":
			{
				if (st.isMemoState(2))
				{
					st.setMemoState(3);
					st.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "30718-06.html":
			{
				if (st.isMemoState(4) && hasQuestItems(player, SAGES_STONE))
				{
					giveItems(player, TRUE_GOLD, 1);
					takeItems(player, SAGES_STONE, -1);
					st.setMemoState(5);
					st.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "31149-02.html":
			case "31149-05.html":
			case "31149-07.html":
			case "31149-09.html":
			case "31149-10.html":
			{
				if (st.isMemoState(7))
				{
					htmltext = event;
				}
				break;
			}
			case "PURE_SILVER":
			{
				if (st.isMemoState(7))
				{
					htmltext = ((hasQuestItems(player, PURE_SILVER)) ? "31149-04.html" : "31149-03.html");
				}
				break;
			}
			case "TRUE_GOLD":
			{
				if (st.isMemoState(7))
				{
					htmltext = ((hasQuestItems(player, TRUE_GOLD)) ? "31149-06.html" : "31149-03.html");
				}
				break;
			}
			case "BLOOD_FIRE":
			{
				if (st.isMemoState(7))
				{
					htmltext = ((hasQuestItems(player, BLOOD_FIRE)) ? "31149-08.html" : "31149-03.html");
				}
				break;
			}
			case "31149-11.html":
			{
				if (st.isMemoState(7) && hasQuestItems(player, BLOOD_FIRE, PURE_SILVER, TRUE_GOLD))
				{
					giveItems(player, MIMIRS_ELIXIR, 1);
					takeItems(player, -1, BLOOD_FIRE, PURE_SILVER, TRUE_GOLD);
					st.setMemoState(8);
					st.setCond(8, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		if (getRandom(5) == 0)
		{
			final L2PcInstance luckyPlayer = getRandomPartyMember(player, npc);
			if (luckyPlayer != null)
			{
				final QuestItemHolder item = MOBS.get(npc.getId());
				if (giveItemRandomly(luckyPlayer, npc, item.getId(), item.getCount(), item.getCount(), 1.0, true))
				{
					final QuestState st = luckyPlayer.getQuestState(getName());
					st.setMemoState(item.getChance());
					st.setCond(item.getChance());
				}
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
			if (npc.getId() == LADD)
			{
				if (player.getRace() == Race.KAMAEL)
				{
					htmltext = "30721-09.html";
				}
				else if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "30721-08.html";
				}
				else
				{
					htmltext = ((hasQuestItems(player, STAR_OF_DESTINY)) ? "30721-01.htm" : "30721-07.html");
				}
			}
		}
		else if (st.isStarted())
		{
			switch (npc.getId())
			{
				case LADD:
				{
					switch (st.getMemoState())
					{
						case 1:
						{
							htmltext = ((hasQuestItems(player, PURE_SILVER)) ? "30721-11.html" : "30721-10.html");
							break;
						}
						case 2:
						case 3:
						case 4:
						{
							htmltext = "30721-13.html";
							break;
						}
						case 5:
						{
							htmltext = "30721-14.html";
							break;
						}
						case 6:
						case 7:
						{
							htmltext = "30721-16.html";
							break;
						}
						case 8:
						{
							htmltext = "30721-17.html";
							break;
						}
					}
					break;
				}
				case JOAN:
				{
					switch (st.getMemoState())
					{
						case 2:
						{
							htmltext = "30718-01.html";
							break;
						}
						case 3:
						{
							htmltext = "30718-04.html";
							break;
						}
						case 4:
						{
							htmltext = "30718-05.html";
							break;
						}
					}
					break;
				}
				case ALCHEMISTS_MIXING_URN:
				{
					if (st.isMemoState(7) && hasQuestItems(player, MAGISTERS_MIXING_STONE))
					{
						htmltext = "31149-01.html";
					}
					break;
				}
			}
		}
		else if (st.isCompleted())
		{
			if (npc.getId() == LADD)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
		}
		return htmltext;
	}
}

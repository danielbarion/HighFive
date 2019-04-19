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
package quests.Q00458_PerfectForm;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.serverpackets.ExQuestNpcLogList;

/**
 * Perfect Form (458)
 * @author jurchiks
 */
public class Q00458_PerfectForm extends Quest
{
	// NPCs
	private static final int KELLEYIA = 32768;
	// Monsters
	// Level 4 (full grown) feedable beasts
	private static final int[] KOOKABURRAS =
	{
		18878,
		18879
	};
	private static final int[] COUGARS =
	{
		18885,
		18886
	};
	private static final int[] BUFFALOS =
	{
		18892,
		18893
	};
	private static final int[] GRENDELS =
	{
		18899,
		18900
	};
	
	// Rewards
	// 60% Icarus weapon recipes (except kamael weapons)
	// @formatter:off
	private static final int[] ICARUS_WEAPON_RECIPES =
	{
		10373, 10374, 10375, 10376, 10377, 10378, 10379, 10380, 10381
	};
	
	private static final int[] ICARUS_WEAPON_PIECES =
	{
		10397, 10398, 10399, 10400, 10401, 10402, 10403, 10404, 10405
	};
	// @formatter:on
	
	public Q00458_PerfectForm()
	{
		super(458);
		addStartNpc(KELLEYIA);
		addTalkId(KELLEYIA);
		addKillId(KOOKABURRAS);
		addKillId(COUGARS);
		addKillId(BUFFALOS);
		addKillId(GRENDELS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final String noQuest = getNoQuestMsg(player);
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return noQuest;
		}
		
		String htmltext = event;
		int overHits = 0;
		boolean overHitHtml = false;
		
		switch (event)
		{
			case "32768-10.htm":
			{
				qs.startQuest();
				break;
			}
			case "results1":
			{
				if (qs.isCond(2))
				{
					final int overhitsTotal = qs.getInt("overhitsTotal");
					if (overhitsTotal >= 35)
					{
						htmltext = "32768-14a.html";
					}
					else if (overhitsTotal >= 10)
					{
						htmltext = "32768-14b.html";
					}
					else
					{
						htmltext = "32768-14c.html";
					}
					overHits = overhitsTotal;
					overHitHtml = true;
				}
				else
				{
					htmltext = noQuest;
				}
				break;
			}
			case "results2":
			{
				if (qs.isCond(2))
				{
					final int overhitsCritical = qs.getInt("overhitsCritical");
					if (overhitsCritical >= 30)
					{
						htmltext = "32768-15a.html";
					}
					else if (overhitsCritical >= 5)
					{
						htmltext = "32768-15b.html";
					}
					else
					{
						htmltext = "32768-15c.html";
					}
					overHits = overhitsCritical;
					overHitHtml = true;
				}
				else
				{
					htmltext = noQuest;
				}
				break;
			}
			case "results3":
			{
				if (qs.isCond(2))
				{
					final int overhitsConsecutive = qs.getInt("overhitsConsecutive");
					if (overhitsConsecutive >= 20)
					{
						htmltext = "32768-16a.html";
					}
					else if (overhitsConsecutive >= 7)
					{
						htmltext = "32768-16b.html";
					}
					else
					{
						htmltext = "32768-16c.html";
					}
					overHits = overhitsConsecutive;
					overHitHtml = true;
				}
				else
				{
					htmltext = noQuest;
				}
				break;
			}
			case "32768-17.html":
			{
				if (qs.isCond(2))
				{
					final int overhitsConsecutive = qs.getInt("overhitsConsecutive");
					if (overhitsConsecutive >= 20)
					{
						rewardItems(player, ICARUS_WEAPON_RECIPES[getRandom(ICARUS_WEAPON_RECIPES.length)], 1);
					}
					else if (overhitsConsecutive >= 7)
					{
						rewardItems(player, ICARUS_WEAPON_PIECES[getRandom(ICARUS_WEAPON_PIECES.length)], 5);
					}
					else
					{
						final int rnd = getRandom(ICARUS_WEAPON_PIECES.length);
						rewardItems(player, ICARUS_WEAPON_PIECES[rnd], 2);
						// not sure if this should use rewardItems
						giveItems(player, 15482, 10); // Golden Spice Crate
						giveItems(player, 15483, 10); // Crystal Spice Crate
					}
					qs.exitQuest(QuestType.DAILY, true);
				}
				else
				{
					htmltext = noQuest;
				}
				break;
			}
		}
		
		if (overHitHtml)
		{
			htmltext = getHtm(player, htmltext);
			htmltext = htmltext.replace("<?number?>", String.valueOf(overHits));
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isCond(1))
		{
			int npcId = npc.getId();
			if ((npcId == KOOKABURRAS[0]) || (npcId == COUGARS[0]) || (npcId == BUFFALOS[0]) || (npcId == GRENDELS[0]))
			{
				npcId++;
			}
			
			final String variable = String.valueOf(npcId); // i3
			final int currentValue = qs.getInt(variable);
			if (currentValue < 10)
			{
				qs.set(variable, String.valueOf(currentValue + 1)); // IncreaseNPCLogByID
				
				final L2Attackable mob = (L2Attackable) npc;
				if (mob.isOverhit())
				{
					qs.set("overhitsTotal", String.valueOf(qs.getInt("overhitsTotal") + 1)); // memoStateEx 1
					final int maxHp = mob.getMaxHp();
					// L2Attackable#calculateOverhitExp() way of calculating overhit % seems illogical
					final double overhitPercentage = (maxHp + mob.getOverhitDamage()) / maxHp;
					if (overhitPercentage >= 1.2)
					{
						qs.set("overhitsCritical", String.valueOf(qs.getInt("overhitsCritical") + 1)); // memoStateEx 2
					}
					qs.set("overhitsConsecutive", String.valueOf(qs.getInt("overhitsConsecutive") + 1)); // memoStateEx 3
					/*
					 * Retail logic (makes for a long/messy string in database): int i0 = overhitsConsecutive % 100; int i1 = overhitsConsecutive - (i0 * 100); if (i0 < i1) { st.set("overhitsConsecutive", String.valueOf((i1 * 100) + i1)); }
					 */
				}
				// st.set("overhitsConsecutive", String.valueOf((st.getInt("overhitsConsecutive") % 100) * 100));
				else if (qs.getInt("overhitsConsecutive") > 0)
				{
					// avoid writing to database if variable is already zero
					qs.set("overhitsConsecutive", "0");
				}
				
				if ((qs.getInt("18879") == 10) && (qs.getInt("18886") == 10) && (qs.getInt("18893") == 10) && (qs.getInt("18900") == 10))
				{
					qs.setCond(2, true);
					// st.set("overhitsConsecutive", String.valueOf(st.getInt("overhitsConsecutive") % 100));
				}
				else
				{
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
				
				final ExQuestNpcLogList log = new ExQuestNpcLogList(getId());
				log.addNpc(18879, qs.getInt("18879"));
				log.addNpc(18886, qs.getInt("18886"));
				log.addNpc(18893, qs.getInt("18893"));
				log.addNpc(18900, qs.getInt("18900"));
				
				player.sendPacket(log);
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				if (!qs.isNowAvailable())
				{
					htmltext = "32768-18.htm";
					break;
				}
				qs.setState(State.CREATED);
				//$FALL-THROUGH$
			}
			case State.CREATED:
			{
				htmltext = (player.getLevel() > 81) ? "32768-01.htm" : "32768-00.htm";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						if ((qs.getInt("18879") == 0) && (qs.getInt("18886") == 0) && (qs.getInt("18893") == 0) && (qs.getInt("18900") == 0))
						{
							htmltext = "32768-11.html";
						}
						else
						{
							htmltext = "32768-12.html";
						}
						break;
					}
					case 2:
					{
						htmltext = "32768-13.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

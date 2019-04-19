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
package quests.Q00103_SpiritOfCraftsman;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.serverpackets.SocialAction;
import com.l2jmobius.gameserver.util.Util;

import quests.Q00281_HeadForTheHills.Q00281_HeadForTheHills;

/**
 * Spirit of Craftsman (103)
 * @author janiko
 */
public final class Q00103_SpiritOfCraftsman extends Quest
{
	// NPCs
	private static final int BLACKSMITH_KAROYD = 30307;
	private static final int CECON = 30132;
	private static final int HARNE = 30144;
	// Items
	private static final int KAROYDS_LETTER = 968;
	private static final int CECKTINONS_VOUCHER1 = 969;
	private static final int CECKTINONS_VOUCHER2 = 970;
	private static final int SOUL_CATCHER = 971;
	private static final int PRESERVE_OIL = 972;
	private static final int ZOMBIE_HEAD = 973;
	private static final int STEELBENDERS_HEAD = 974;
	private static final int BONE_FRAGMENT = 1107;
	// Monsters
	private static final int MARSH_ZOMBIE = 20015;
	private static final int DOOM_SOLDIER = 20455;
	private static final int SKELETON_HUNTER = 20517;
	private static final int SKELETON_HUNTER_ARCHER = 20518;
	// Rewards
	private static final int BLOODSABER = 975;
	private static final ItemHolder[] REWARDS =
	{
		new ItemHolder(1060, 100), // Lesser Healing Potion
		new ItemHolder(4412, 10), // Echo Crystal - Theme of Battle
		new ItemHolder(4413, 10), // Echo Crystal - Theme of Love
		new ItemHolder(4414, 10), // Echo Crystal - Theme of Solitude
		new ItemHolder(4415, 10), // Echo Crystal - Theme of Feast
		new ItemHolder(4416, 10), // Echo Crystal - Theme of Celebration
	};
	// Misc
	private static final int MIN_LVL = 10;
	
	public Q00103_SpiritOfCraftsman()
	{
		super(103);
		addStartNpc(BLACKSMITH_KAROYD);
		addTalkId(BLACKSMITH_KAROYD, CECON, HARNE);
		addKillId(MARSH_ZOMBIE, DOOM_SOLDIER, SKELETON_HUNTER, SKELETON_HUNTER_ARCHER);
		registerQuestItems(KAROYDS_LETTER, CECKTINONS_VOUCHER1, CECKTINONS_VOUCHER2, SOUL_CATCHER, PRESERVE_OIL, ZOMBIE_HEAD, STEELBENDERS_HEAD, BONE_FRAGMENT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		switch (event)
		{
			case "30307-04.htm":
			{
				htmltext = event;
				break;
			}
			case "30307-05.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					giveItems(player, KAROYDS_LETTER, 1);
					htmltext = event;
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
		switch (npc.getId())
		{
			case BLACKSMITH_KAROYD:
			{
				if (qs.isCreated())
				{
					if (talker.getRace() != Race.DARK_ELF)
					{
						htmltext = "30307-01.htm";
					}
					else if (talker.getLevel() < MIN_LVL)
					{
						htmltext = "30307-02.htm";
					}
					else
					{
						htmltext = "30307-03.htm";
					}
				}
				else if (qs.isStarted())
				{
					if (hasAtLeastOneQuestItem(talker, KAROYDS_LETTER, CECKTINONS_VOUCHER1, CECKTINONS_VOUCHER2))
					{
						htmltext = "30307-06.html";
					}
					else if (hasQuestItems(talker, STEELBENDERS_HEAD))
					{
						Q00281_HeadForTheHills.giveNewbieReward(talker);
						addExpAndSp(talker, 46663, 3999);
						giveAdena(talker, 19799, true);
						for (ItemHolder reward : REWARDS)
						{
							rewardItems(talker, reward);
						}
						rewardItems(talker, BLOODSABER, 1);
						qs.exitQuest(false, true);
						talker.sendPacket(new SocialAction(talker.getObjectId(), 3));
						htmltext = "30307-07.html";
					}
				}
				else if (qs.isCompleted())
				{
					htmltext = getAlreadyCompletedMsg(talker);
					break;
				}
			}
			case CECON:
			{
				if (qs.isStarted())
				{
					if (hasQuestItems(talker, KAROYDS_LETTER))
					{
						qs.setCond(2, true);
						takeItems(talker, KAROYDS_LETTER, 1);
						giveItems(talker, CECKTINONS_VOUCHER1, 1);
						htmltext = "30132-01.html";
					}
					else if (hasAtLeastOneQuestItem(talker, CECKTINONS_VOUCHER1, CECKTINONS_VOUCHER2))
					{
						htmltext = "30132-02.html";
					}
					else if (hasQuestItems(talker, SOUL_CATCHER))
					{
						qs.setCond(6, true);
						takeItems(talker, SOUL_CATCHER, 1);
						giveItems(talker, PRESERVE_OIL, 1);
						htmltext = "30132-03.html";
					}
					else if (hasQuestItems(talker, PRESERVE_OIL) && !hasQuestItems(talker, ZOMBIE_HEAD, STEELBENDERS_HEAD))
					{
						htmltext = "30132-04.html";
					}
					else if (hasQuestItems(talker, ZOMBIE_HEAD))
					{
						qs.setCond(8, true);
						takeItems(talker, ZOMBIE_HEAD, 1);
						giveItems(talker, STEELBENDERS_HEAD, 1);
						htmltext = "30132-05.html";
					}
					else if (hasQuestItems(talker, STEELBENDERS_HEAD))
					{
						htmltext = "30132-06.html";
					}
				}
				break;
			}
			case HARNE:
			{
				if (qs.isStarted())
				{
					if (hasQuestItems(talker, CECKTINONS_VOUCHER1))
					{
						qs.setCond(3, true);
						takeItems(talker, CECKTINONS_VOUCHER1, 1);
						giveItems(talker, CECKTINONS_VOUCHER2, 1);
						htmltext = "30144-01.html";
					}
					else if (hasQuestItems(talker, CECKTINONS_VOUCHER2))
					{
						if (getQuestItemsCount(talker, BONE_FRAGMENT) >= 10)
						{
							qs.setCond(5, true);
							takeItems(talker, CECKTINONS_VOUCHER2, 1);
							takeItems(talker, BONE_FRAGMENT, 10);
							giveItems(talker, SOUL_CATCHER, 1);
							htmltext = "30144-03.html";
						}
						else
						{
							htmltext = "30144-02.html";
						}
					}
					else if (hasQuestItems(talker, SOUL_CATCHER))
					{
						htmltext = "30144-04.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, -1, 3, npc);
		if (qs == null)
		{
			return super.onKill(npc, killer, isSummon);
		}
		
		switch (npc.getId())
		{
			case MARSH_ZOMBIE:
			{
				if (hasQuestItems(killer, PRESERVE_OIL) && (getRandom(10) < 5) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
				{
					giveItems(killer, ZOMBIE_HEAD, 1);
					takeItems(killer, PRESERVE_OIL, -1);
					qs.setCond(7, true);
				}
				break;
			}
			case DOOM_SOLDIER:
			case SKELETON_HUNTER:
			case SKELETON_HUNTER_ARCHER:
			{
				if (hasQuestItems(killer, CECKTINONS_VOUCHER2) && giveItemRandomly(qs.getPlayer(), npc, BONE_FRAGMENT, 1, 10, 1.0, true))
				{
					qs.setCond(4, true);
				}
				break;
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
}
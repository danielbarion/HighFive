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
package quests.Q00367_ElectrifyingRecharge;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.skills.Skill;

/**
 * Electrifying Recharge! (367)
 * @author Adry_85, jurchiks
 */
public final class Q00367_ElectrifyingRecharge extends Quest
{
	// NPC
	private static final int LORAIN = 30673;
	// Monster
	private static final int CATHEROK = 21035;
	// Items
	private static final int TITAN_LAMP1 = 5875;
	private static final int TITAN_LAMP2 = 5876;
	private static final int TITAN_LAMP3 = 5877;
	private static final int TITAN_LAMP4 = 5878;
	private static final int TITAN_LAMP5 = 5879;
	private static final int BROKEN_TITAN_LAMP = 5880;
	// Misc
	private static final int MIN_LEVEL = 37;
	// Skill
	private static final Skill NPC_THUNDER_STORM = new SkillHolder(4072, 4).getSkill();
	
	public Q00367_ElectrifyingRecharge()
	{
		super(367);
		addStartNpc(LORAIN);
		addTalkId(LORAIN);
		addAttackId(CATHEROK);
		registerQuestItems(TITAN_LAMP1, TITAN_LAMP2, TITAN_LAMP3, TITAN_LAMP4, TITAN_LAMP5, BROKEN_TITAN_LAMP);
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
			case "30673-02.htm":
			{
				st.startQuest();
				giveItems(player, TITAN_LAMP1, 1);
				htmltext = event;
				break;
			}
			case "30673-05.html":
			{
				htmltext = event;
				break;
			}
			case "30673-06.html":
			{
				st.exitQuest(true, true);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		if (npc.isScriptValue(367))
		{
			return super.onAttack(npc, attacker, damage, isSummon);
		}
		
		QuestState qs = getQuestState(attacker, false);
		if ((qs == null) || !qs.isStarted())
		{
			return super.onAttack(npc, attacker, damage, isSummon);
		}
		
		npc.setScriptValue(367);
		
		if ((NPC_THUNDER_STORM != null) //
			&& (NPC_THUNDER_STORM.getMpConsume() < npc.getCurrentMp()) // has enough MP
			&& (NPC_THUNDER_STORM.getHpConsume() < npc.getCurrentHp()) // has enough HP
			&& (npc.getSkillRemainingReuseTime(NPC_THUNDER_STORM.getReuseHashCode()) <= 0)) // no reuse delay
		{
			npc.doCast(NPC_THUNDER_STORM, attacker, null);
		}
		
		final L2PcInstance luckyPlayer = getRandomPartyMember(attacker, npc);
		if (luckyPlayer == null)
		{
			return super.onAttack(npc, attacker, damage, isSummon);
		}
		qs = getQuestState(luckyPlayer, false);
		
		if ((qs != null) && qs.isStarted() && !hasQuestItems(luckyPlayer, TITAN_LAMP5))
		{
			final int random = getRandom(37);
			if (random == 0)
			{
				if (hasQuestItems(luckyPlayer, TITAN_LAMP1))
				{
					giveItems(luckyPlayer, TITAN_LAMP2, 1);
					takeItems(luckyPlayer, TITAN_LAMP1, -1);
					playSound(luckyPlayer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
				}
				else if (hasQuestItems(luckyPlayer, TITAN_LAMP2))
				{
					giveItems(luckyPlayer, TITAN_LAMP3, 1);
					takeItems(luckyPlayer, TITAN_LAMP2, -1);
					playSound(luckyPlayer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
				}
				else if (hasQuestItems(luckyPlayer, TITAN_LAMP3))
				{
					giveItems(luckyPlayer, TITAN_LAMP4, 1);
					takeItems(luckyPlayer, TITAN_LAMP3, -1);
					playSound(luckyPlayer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
				}
				else if (hasQuestItems(luckyPlayer, TITAN_LAMP4))
				{
					giveItems(luckyPlayer, TITAN_LAMP5, 1);
					takeItems(luckyPlayer, TITAN_LAMP4, -1);
					luckyPlayer.getQuestState(getName()).setCond(2, true);
				}
			}
			else if ((random == 1) && !hasQuestItems(luckyPlayer, BROKEN_TITAN_LAMP))
			{
				giveItems(luckyPlayer, BROKEN_TITAN_LAMP, 1);
				takeItems(luckyPlayer, -1, TITAN_LAMP1, TITAN_LAMP2, TITAN_LAMP3, TITAN_LAMP4);
				playSound(luckyPlayer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (st.isCreated())
		{
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "30673-01.htm" : "30673-03.html";
		}
		else if (st.isStarted())
		{
			if (!hasAtLeastOneQuestItem(player, TITAN_LAMP5, BROKEN_TITAN_LAMP))
			{
				htmltext = "30673-04.html";
			}
			else if (hasQuestItems(player, BROKEN_TITAN_LAMP))
			{
				giveItems(player, TITAN_LAMP1, 1);
				takeItems(player, BROKEN_TITAN_LAMP, -1);
				htmltext = "30673-07.html";
			}
			else if (hasQuestItems(player, TITAN_LAMP5))
			{
				final int itemId;
				switch (getRandom(14))
				{
					case 0:
					{
						itemId = 4553; // Greater Dye of STR <Str+1 Con-1>
						break;
					}
					case 1:
					{
						itemId = 4554; // Greater Dye of STR <Str+1 Dex-1>
						break;
					}
					case 2:
					{
						itemId = 4555; // Greater Dye of CON <Con+1 Str-1>
						break;
					}
					case 3:
					{
						itemId = 4556; // Greater Dye of CON <Con+1 Dex-1>
						break;
					}
					case 4:
					{
						itemId = 4557; // Greater Dye of DEX <Dex+1 Str-1>
						break;
					}
					case 5:
					{
						itemId = 4558; // Greater Dye of DEX <Dex+1 Con-1>
						break;
					}
					case 6:
					{
						itemId = 4559; // Greater Dye of INT <Int+1 Men-1>
						break;
					}
					case 7:
					{
						itemId = 4560; // Greater Dye of INT <Int+1 Wit-1>
						break;
					}
					case 8:
					{
						itemId = 4561; // Greater Dye of MEN <Men+1 Int-1>
						break;
					}
					case 9:
					{
						itemId = 4562; // Greater Dye of MEN <Men+1 Wit-1>
						break;
					}
					case 10:
					{
						itemId = 4563; // Greater Dye of WIT <Wit+1 Int-1>
						break;
					}
					case 11:
					{
						itemId = 4564; // Greater Dye of WIT <Wit+1 Men-1>
						break;
					}
					default:
					{
						itemId = 4445; // Dye of STR <Str+1 Con-3>
						break;
					}
				}
				rewardItems(player, itemId, 1);
				takeItems(player, TITAN_LAMP5, -1);
				giveItems(player, TITAN_LAMP1, 1);
				htmltext = "30673-08.html";
			}
		}
		return htmltext;
	}
}

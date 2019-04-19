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
package quests.Q00060_GoodWorksReward;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.CategoryType;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.Race;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.base.ClassId;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Util;

import quests.Q00211_TrialOfTheChallenger.Q00211_TrialOfTheChallenger;
import quests.Q00212_TrialOfDuty.Q00212_TrialOfDuty;
import quests.Q00213_TrialOfTheSeeker.Q00213_TrialOfTheSeeker;
import quests.Q00214_TrialOfTheScholar.Q00214_TrialOfTheScholar;
import quests.Q00215_TrialOfThePilgrim.Q00215_TrialOfThePilgrim;
import quests.Q00216_TrialOfTheGuildsman.Q00216_TrialOfTheGuildsman;
import quests.Q00217_TestimonyOfTrust.Q00217_TestimonyOfTrust;
import quests.Q00218_TestimonyOfLife.Q00218_TestimonyOfLife;
import quests.Q00219_TestimonyOfFate.Q00219_TestimonyOfFate;
import quests.Q00220_TestimonyOfGlory.Q00220_TestimonyOfGlory;
import quests.Q00221_TestimonyOfProsperity.Q00221_TestimonyOfProsperity;
import quests.Q00222_TestOfTheDuelist.Q00222_TestOfTheDuelist;
import quests.Q00223_TestOfTheChampion.Q00223_TestOfTheChampion;
import quests.Q00224_TestOfSagittarius.Q00224_TestOfSagittarius;
import quests.Q00225_TestOfTheSearcher.Q00225_TestOfTheSearcher;
import quests.Q00226_TestOfTheHealer.Q00226_TestOfTheHealer;
import quests.Q00227_TestOfTheReformer.Q00227_TestOfTheReformer;
import quests.Q00228_TestOfMagus.Q00228_TestOfMagus;
import quests.Q00229_TestOfWitchcraft.Q00229_TestOfWitchcraft;
import quests.Q00230_TestOfTheSummoner.Q00230_TestOfTheSummoner;
import quests.Q00231_TestOfTheMaestro.Q00231_TestOfTheMaestro;
import quests.Q00232_TestOfTheLord.Q00232_TestOfTheLord;
import quests.Q00233_TestOfTheWarSpirit.Q00233_TestOfTheWarSpirit;

/**
 * Good Work's Reward (60)
 * @author ivantotov
 */
public final class Q00060_GoodWorksReward extends Quest
{
	// NPCs
	private static final int GROCER_HELVERIA = 30081;
	private static final int BLACK_MARKETEER_OF_MAMMON = 31092;
	private static final int BLUEPRINT_SELLER_DAEGER = 31435;
	private static final int MARK = 32487;
	// Items
	private static final int BLOODY_CLOTH_FRAGMENT = 10867;
	private static final int HELVETIAS_ANTIDOTE = 10868;
	// Reward
	private static final int MARK_OF_CHALLENGER = 2627;
	private static final int MARK_OF_DUTY = 2633;
	private static final int MARK_OF_SEEKER = 2673;
	private static final int MARK_OF_SCHOLAR = 2674;
	private static final int MARK_OF_PILGRIM = 2721;
	private static final int MARK_OF_TRUST = 2734;
	private static final int MARK_OF_DUELIST = 2762;
	private static final int MARK_OF_SEARCHER = 2809;
	private static final int MARK_OF_HEALER = 2820;
	private static final int MARK_OF_REFORMER = 2821;
	private static final int MARK_OF_MAGUS = 2840;
	private static final int MARK_OF_MAESTRO = 2867;
	private static final int MARK_OF_WARSPIRIT = 2879;
	private static final int MARK_OF_GUILDSMAN = 3119;
	private static final int MARK_OF_LIFE = 3140;
	private static final int MARK_OF_FATE = 3172;
	private static final int MARK_OF_GLORY = 3203;
	private static final int MARK_OF_PROSPERITY = 3238;
	private static final int MARK_OF_CHAMPION = 3276;
	private static final int MARK_OF_SAGITTARIUS = 3293;
	private static final int MARK_OF_WITCHCRAFT = 3307;
	private static final int MARK_OF_SUMMONER = 3336;
	private static final int MARK_OF_LORD = 3390;
	// Quest Monster
	private static final int PURSUER = 27340;
	// Misc
	private static final int MIN_LEVEL = 39;
	private static final int ONE_MILLION = 1000000;
	private static final int TWO_MILLION = 2000000;
	private static final int THREE_MILLION = 3000000;
	
	public Q00060_GoodWorksReward()
	{
		super(60);
		addStartNpc(BLUEPRINT_SELLER_DAEGER);
		addTalkId(BLUEPRINT_SELLER_DAEGER, GROCER_HELVERIA, BLACK_MARKETEER_OF_MAMMON, MARK);
		addKillId(PURSUER);
		addSpawnId(PURSUER);
		registerQuestItems(BLOODY_CLOTH_FRAGMENT, HELVETIAS_ANTIDOTE);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		
		if ("DESPAWN".equals(event))
		{
			npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.YOU_HAVE_GOOD_LUCK_I_SHALL_RETURN));
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			if (npc0 != null)
			{
				npc0.getVariables().set("SPAWNED", false);
			}
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "31435-07.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = event;
				}
				break;
			}
			case "31435-02.htm":
			{
				htmltext = event;
				break;
			}
			case "31435-10.html":
			{
				if (qs.isMemoState(3))
				{
					qs.setMemoState(4);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "31435-14.html":
			{
				if (qs.isMemoState(8))
				{
					qs.setMemoState(9);
					qs.setCond(9, true);
					htmltext = event;
				}
				break;
			}
			case "30081-02.html":
			{
				if (qs.isMemoState(4))
				{
					htmltext = event;
				}
				break;
			}
			case "30081-03.html":
			{
				if (qs.isMemoState(4))
				{
					takeItems(player, BLOODY_CLOTH_FRAGMENT, -1);
					qs.setMemoState(5);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "30081-05.html":
			{
				final int memoState = qs.getMemoState();
				if ((memoState >= 5) && (memoState <= 6))
				{
					if (getQuestItemsCount(player, Inventory.ADENA_ID) >= THREE_MILLION)
					{
						giveItems(player, HELVETIAS_ANTIDOTE, 1);
						takeItems(player, Inventory.ADENA_ID, THREE_MILLION);
						qs.setMemoState(7);
						qs.setCond(7, true);
						htmltext = event;
					}
					else
					{
						qs.setMemoState(6);
						qs.setCond(6, true);
						htmltext = "30081-06.html";
					}
				}
				break;
			}
			case "30081-07.html":
			{
				if (qs.isMemoState(5))
				{
					qs.setMemoState(6);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "REPLY_1":
			{
				if (qs.isMemoState(10))
				{
					final QuestState q211 = player.getQuestState(Q00211_TrialOfTheChallenger.class.getSimpleName());
					final QuestState q212 = player.getQuestState(Q00212_TrialOfDuty.class.getSimpleName());
					final QuestState q213 = player.getQuestState(Q00213_TrialOfTheSeeker.class.getSimpleName());
					final QuestState q214 = player.getQuestState(Q00214_TrialOfTheScholar.class.getSimpleName());
					final QuestState q215 = player.getQuestState(Q00215_TrialOfThePilgrim.class.getSimpleName());
					final QuestState q216 = player.getQuestState(Q00216_TrialOfTheGuildsman.class.getSimpleName());
					final QuestState q217 = player.getQuestState(Q00217_TestimonyOfTrust.class.getSimpleName());
					final QuestState q218 = player.getQuestState(Q00218_TestimonyOfLife.class.getSimpleName());
					final QuestState q219 = player.getQuestState(Q00219_TestimonyOfFate.class.getSimpleName());
					final QuestState q220 = player.getQuestState(Q00220_TestimonyOfGlory.class.getSimpleName());
					final QuestState q221 = player.getQuestState(Q00221_TestimonyOfProsperity.class.getSimpleName());
					final QuestState q222 = player.getQuestState(Q00222_TestOfTheDuelist.class.getSimpleName());
					final QuestState q223 = player.getQuestState(Q00223_TestOfTheChampion.class.getSimpleName());
					final QuestState q224 = player.getQuestState(Q00224_TestOfSagittarius.class.getSimpleName());
					final QuestState q225 = player.getQuestState(Q00225_TestOfTheSearcher.class.getSimpleName());
					final QuestState q226 = player.getQuestState(Q00226_TestOfTheHealer.class.getSimpleName());
					final QuestState q227 = player.getQuestState(Q00227_TestOfTheReformer.class.getSimpleName());
					final QuestState q228 = player.getQuestState(Q00228_TestOfMagus.class.getSimpleName());
					final QuestState q229 = player.getQuestState(Q00229_TestOfWitchcraft.class.getSimpleName());
					final QuestState q230 = player.getQuestState(Q00230_TestOfTheSummoner.class.getSimpleName());
					final QuestState q231 = player.getQuestState(Q00231_TestOfTheMaestro.class.getSimpleName());
					final QuestState q232 = player.getQuestState(Q00232_TestOfTheLord.class.getSimpleName());
					final QuestState q233 = player.getQuestState(Q00233_TestOfTheWarSpirit.class.getSimpleName());
					if (((q211 != null) && q211.isCompleted()) || ((q212 != null) && q212.isCompleted()) || ((q213 != null) && q213.isCompleted()) || ((q214 != null) && q214.isCompleted()) || ((q215 != null) && q215.isCompleted()) || ((q216 != null) && q216.isCompleted()))
					{
						if (((q217 != null) && q217.isCompleted()) || ((q218 != null) && q218.isCompleted()) || ((q219 != null) && q219.isCompleted()) || ((q220 != null) && q220.isCompleted()) || ((q221 != null) && q221.isCompleted()))
						{
							
							if (((q222 != null) && q222.isCompleted()) || ((q223 != null) && q223.isCompleted()) || ((q224 != null) && q224.isCompleted()) || ((q225 != null) && q225.isCompleted()) || ((q226 != null) && q226.isCompleted()) || ((q227 != null) && q227.isCompleted()) || ((q228 != null) && q228.isCompleted()) || ((q229 != null) && q229.isCompleted()) || ((q230 != null) && q230.isCompleted()) || ((q231 != null) && q231.isCompleted()) || ((q232 != null) && q232.isCompleted()) || ((q233 != null) && q233.isCompleted()))
							{
								qs.setMemoStateEx(1, 3);
							}
							else
							{
								qs.setMemoStateEx(1, 2);
							}
						}
						else if (((q222 != null) && q222.isCompleted()) || ((q223 != null) && q223.isCompleted()) || ((q224 != null) && q224.isCompleted()) || ((q225 != null) && q225.isCompleted()) || ((q226 != null) && q226.isCompleted()) || ((q227 != null) && q227.isCompleted()) || ((q228 != null) && q228.isCompleted()) || ((q229 != null) && q229.isCompleted()) || ((q230 != null) && q230.isCompleted()) || ((q231 != null) && q231.isCompleted()) || ((q232 != null) && q232.isCompleted()) || ((q233 != null) && q233.isCompleted()))
						{
							qs.setMemoStateEx(1, 2);
						}
						else
						{
							qs.setMemoStateEx(1, 1);
						}
					}
					else if (((q217 != null) && q217.isCompleted()) || ((q218 != null) && q218.isCompleted()) || ((q219 != null) && q219.isCompleted()) || ((q220 != null) && q220.isCompleted()) || ((q221 != null) && q221.isCompleted()))
					{
						if (((q222 != null) && q222.isCompleted()) || ((q223 != null) && q223.isCompleted()) || ((q224 != null) && q224.isCompleted()) || ((q225 != null) && q225.isCompleted()) || ((q226 != null) && q226.isCompleted()) || ((q227 != null) && q227.isCompleted()) || ((q228 != null) && q228.isCompleted()) || ((q229 != null) && q229.isCompleted()) || ((q230 != null) && q230.isCompleted()) || ((q231 != null) && q231.isCompleted()) || ((q232 != null) && q232.isCompleted()) || ((q233 != null) && q233.isCompleted()))
						{
							qs.setMemoStateEx(1, 2);
						}
						else
						{
							qs.setMemoStateEx(1, 1);
						}
					}
					else if (((q222 != null) && q222.isCompleted()) || ((q223 != null) && q223.isCompleted()) || ((q224 != null) && q224.isCompleted()) || ((q225 != null) && q225.isCompleted()) || ((q226 != null) && q226.isCompleted()) || ((q227 != null) && q227.isCompleted()) || ((q228 != null) && q228.isCompleted()) || ((q229 != null) && q229.isCompleted()) || ((q230 != null) && q230.isCompleted()) || ((q231 != null) && q231.isCompleted()) || ((q232 != null) && q232.isCompleted()) || ((q233 != null) && q233.isCompleted()))
					{
						qs.setMemoStateEx(1, 1);
					}
					htmltext = "31092-02.html";
				}
				break;
			}
			case "REPLY_2":
			{
				if (qs.isMemoState(10))
				{
					if (qs.getMemoStateEx(1) >= 3)
					{
						htmltext = "31092-03b.html";
					}
					else if (qs.getMemoStateEx(1) >= 1)
					{
						htmltext = "31092-03.html";
					}
					else
					{
						htmltext = "31092-03a.html";
					}
				}
				break;
			}
			case "REPLY_3":
			{
				if (qs.isMemoState(10))
				{
					if (qs.getMemoStateEx(1) >= 3)
					{
						giveItems(player, Inventory.ADENA_ID, THREE_MILLION);
						htmltext = "31092-04a.html";
					}
					else if (qs.getMemoStateEx(1) == 2)
					{
						giveItems(player, Inventory.ADENA_ID, TWO_MILLION);
						htmltext = "31092-04b.html";
					}
					else if (qs.getMemoStateEx(1) == 1)
					{
						giveItems(player, Inventory.ADENA_ID, ONE_MILLION);
						htmltext = "31092-04b.html";
					}
					qs.exitQuest(false, true);
				}
				break;
			}
			case "REPLY_4":
			{
				if (qs.isMemoState(10))
				{
					if ((player.getClassId() == ClassId.WARRIOR))
					{
						htmltext = "31092-05.html";
					}
					else if ((player.getClassId() == ClassId.KNIGHT))
					{
						htmltext = "31092-06.html";
					}
					else if ((player.getClassId() == ClassId.ROGUE))
					{
						htmltext = "31092-07.html";
					}
					else if ((player.getClassId() == ClassId.WIZARD))
					{
						htmltext = "31092-08.html";
					}
					else if ((player.getClassId() == ClassId.CLERIC))
					{
						htmltext = "31092-09.html";
					}
					else if ((player.getClassId() == ClassId.ELVEN_KNIGHT))
					{
						htmltext = "31092-10.html";
					}
					else if ((player.getClassId() == ClassId.ELVEN_SCOUT))
					{
						htmltext = "31092-11.html";
					}
					else if ((player.getClassId() == ClassId.ELVEN_WIZARD))
					{
						htmltext = "31092-12.html";
					}
					else if ((player.getClassId() == ClassId.ORACLE))
					{
						htmltext = "31092-13.html";
					}
					else if ((player.getClassId() == ClassId.PALUS_KNIGHT))
					{
						htmltext = "31092-14.html";
					}
					else if ((player.getClassId() == ClassId.ASSASSIN))
					{
						htmltext = "31092-15.html";
					}
					else if ((player.getClassId() == ClassId.DARK_WIZARD))
					{
						htmltext = "31092-16.html";
					}
					else if ((player.getClassId() == ClassId.SHILLIEN_ORACLE))
					{
						htmltext = "31092-17.html";
					}
					else if ((player.getClassId() == ClassId.ORC_RAIDER))
					{
						htmltext = "31092-18.html";
					}
					else if ((player.getClassId() == ClassId.ORC_MONK))
					{
						htmltext = "31092-19.html";
					}
					else if ((player.getClassId() == ClassId.ORC_SHAMAN))
					{
						htmltext = "31092-20.html";
					}
					else if ((player.getClassId() == ClassId.SCAVENGER))
					{
						htmltext = "31092-21.html";
					}
					else if ((player.getClassId() == ClassId.ARTISAN))
					{
						htmltext = "31092-22.html";
					}
					qs.exitQuest(false, true);
				}
			}
			case "REPLY_5":
			{
				if (player.isInCategory(CategoryType.SECOND_CLASS_GROUP))
				{
					if ((player.getClassId() == ClassId.WARRIOR))
					{
						htmltext = "31092-05a.html";
					}
					else if ((player.getClassId() == ClassId.KNIGHT))
					{
						htmltext = "31092-06a.html";
					}
					else if ((player.getClassId() == ClassId.ROGUE))
					{
						htmltext = "31092-07a.html";
					}
					else if ((player.getClassId() == ClassId.WIZARD))
					{
						htmltext = "31092-08a.html";
					}
					else if ((player.getClassId() == ClassId.CLERIC))
					{
						htmltext = "31092-09a.html";
					}
					else if ((player.getClassId() == ClassId.ELVEN_KNIGHT))
					{
						htmltext = "31092-10a.html";
					}
					else if ((player.getClassId() == ClassId.ELVEN_SCOUT))
					{
						htmltext = "31092-11a.html";
					}
					else if ((player.getClassId() == ClassId.ELVEN_WIZARD))
					{
						htmltext = "31092-12a.html";
					}
					else if ((player.getClassId() == ClassId.ORACLE))
					{
						htmltext = "31092-13a.html";
					}
					else if ((player.getClassId() == ClassId.PALUS_KNIGHT))
					{
						htmltext = "31092-14a.html";
					}
					else if ((player.getClassId() == ClassId.ASSASSIN))
					{
						htmltext = "31092-15a.html";
					}
					else if ((player.getClassId() == ClassId.DARK_WIZARD))
					{
						htmltext = "31092-16a.html";
					}
					else if ((player.getClassId() == ClassId.SHILLIEN_ORACLE))
					{
						htmltext = "31092-17a.html";
					}
					else if ((player.getClassId() == ClassId.ORC_RAIDER))
					{
						htmltext = "31092-18a.html";
					}
					else if ((player.getClassId() == ClassId.ORC_MONK))
					{
						htmltext = "31092-19a.html";
					}
					else if ((player.getClassId() == ClassId.ORC_SHAMAN))
					{
						htmltext = "31092-20a.html";
					}
					else if ((player.getClassId() == ClassId.SCAVENGER))
					{
						htmltext = "31092-21a.html";
					}
					else if ((player.getClassId() == ClassId.ARTISAN))
					{
						htmltext = "31092-22a.html";
					}
				}
			}
			case "REPLY_6":
			{
				if ((player.getClassId() == ClassId.WARRIOR))
				{
					if (!hasQuestItems(player, MARK_OF_CHALLENGER))
					{
						giveItems(player, MARK_OF_CHALLENGER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_DUELIST))
					{
						giveItems(player, MARK_OF_DUELIST, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_7":
			{
				if ((player.getClassId() == ClassId.WARRIOR))
				{
					if (!hasQuestItems(player, MARK_OF_CHALLENGER))
					{
						giveItems(player, MARK_OF_CHALLENGER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_CHAMPION))
					{
						giveItems(player, MARK_OF_CHAMPION, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_8":
			{
				if ((player.getClassId() == ClassId.KNIGHT))
				{
					if (!hasQuestItems(player, MARK_OF_DUTY))
					{
						giveItems(player, MARK_OF_DUTY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_HEALER))
					{
						giveItems(player, MARK_OF_HEALER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_9":
			{
				if ((player.getClassId() == ClassId.KNIGHT))
				{
					if (!hasQuestItems(player, MARK_OF_DUTY))
					{
						giveItems(player, MARK_OF_DUTY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_WITCHCRAFT))
					{
						giveItems(player, MARK_OF_WITCHCRAFT, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_10":
			{
				if ((player.getClassId() == ClassId.ROGUE))
				{
					if (!hasQuestItems(player, MARK_OF_SEEKER))
					{
						giveItems(player, MARK_OF_SEEKER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SEARCHER))
					{
						giveItems(player, MARK_OF_SEARCHER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_11":
			{
				if ((player.getClassId() == ClassId.ROGUE))
				{
					if (!hasQuestItems(player, MARK_OF_SEEKER))
					{
						giveItems(player, MARK_OF_SEEKER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SAGITTARIUS))
					{
						giveItems(player, MARK_OF_SAGITTARIUS, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_12":
			{
				if ((player.getClassId() == ClassId.WIZARD))
				{
					if (!hasQuestItems(player, MARK_OF_SCHOLAR))
					{
						giveItems(player, MARK_OF_SCHOLAR, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_MAGUS))
					{
						giveItems(player, MARK_OF_MAGUS, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_13":
			{
				if ((player.getClassId() == ClassId.WIZARD))
				{
					if (!hasQuestItems(player, MARK_OF_SCHOLAR))
					{
						giveItems(player, MARK_OF_SCHOLAR, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_WITCHCRAFT))
					{
						giveItems(player, MARK_OF_WITCHCRAFT, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_14":
			{
				if ((player.getClassId() == ClassId.WIZARD))
				{
					if (!hasQuestItems(player, MARK_OF_SCHOLAR))
					{
						giveItems(player, MARK_OF_SCHOLAR, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SUMMONER))
					{
						giveItems(player, MARK_OF_SUMMONER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_15":
			{
				if ((player.getClassId() == ClassId.CLERIC))
				{
					if (!hasQuestItems(player, MARK_OF_PILGRIM))
					{
						giveItems(player, MARK_OF_PILGRIM, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_HEALER))
					{
						giveItems(player, MARK_OF_HEALER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_16":
			{
				if ((player.getClassId() == ClassId.CLERIC))
				{
					if (!hasQuestItems(player, MARK_OF_PILGRIM))
					{
						giveItems(player, MARK_OF_PILGRIM, 1);
					}
					if (!hasQuestItems(player, MARK_OF_TRUST))
					{
						giveItems(player, MARK_OF_TRUST, 1);
					}
					if (!hasQuestItems(player, MARK_OF_REFORMER))
					{
						giveItems(player, MARK_OF_REFORMER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_17":
			{
				if ((player.getClassId() == ClassId.ELVEN_KNIGHT))
				{
					if (!hasQuestItems(player, MARK_OF_DUTY))
					{
						giveItems(player, MARK_OF_DUTY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_LIFE))
					{
						giveItems(player, MARK_OF_LIFE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_HEALER))
					{
						giveItems(player, MARK_OF_HEALER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_18":
			{
				if ((player.getClassId() == ClassId.ELVEN_KNIGHT))
				{
					if (!hasQuestItems(player, MARK_OF_CHALLENGER))
					{
						giveItems(player, MARK_OF_CHALLENGER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_LIFE))
					{
						giveItems(player, MARK_OF_LIFE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_DUELIST))
					{
						giveItems(player, MARK_OF_DUELIST, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_19":
			{
				if ((player.getClassId() == ClassId.ELVEN_SCOUT))
				{
					if (!hasQuestItems(player, MARK_OF_SEEKER))
					{
						giveItems(player, MARK_OF_SEEKER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_LIFE))
					{
						giveItems(player, MARK_OF_LIFE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SEARCHER))
					{
						giveItems(player, MARK_OF_SEARCHER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_20":
			{
				if ((player.getClassId() == ClassId.ELVEN_SCOUT))
				{
					if (!hasQuestItems(player, MARK_OF_SEEKER))
					{
						giveItems(player, MARK_OF_SEEKER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_LIFE))
					{
						giveItems(player, MARK_OF_LIFE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SAGITTARIUS))
					{
						giveItems(player, MARK_OF_SAGITTARIUS, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_21":
			{
				if ((player.getClassId() == ClassId.ELVEN_WIZARD))
				{
					if (!hasQuestItems(player, MARK_OF_SCHOLAR))
					{
						giveItems(player, MARK_OF_SCHOLAR, 1);
					}
					if (!hasQuestItems(player, MARK_OF_LIFE))
					{
						giveItems(player, MARK_OF_LIFE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_MAGUS))
					{
						giveItems(player, MARK_OF_MAGUS, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_22":
			{
				if ((player.getClassId() == ClassId.ELVEN_WIZARD))
				{
					if (!hasQuestItems(player, MARK_OF_SCHOLAR))
					{
						giveItems(player, MARK_OF_SCHOLAR, 1);
					}
					if (!hasQuestItems(player, MARK_OF_LIFE))
					{
						giveItems(player, MARK_OF_LIFE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SUMMONER))
					{
						giveItems(player, MARK_OF_SUMMONER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_23":
			{
				if ((player.getClassId() == ClassId.ORACLE))
				{
					if (!hasQuestItems(player, MARK_OF_PILGRIM))
					{
						giveItems(player, MARK_OF_PILGRIM, 1);
					}
					if (!hasQuestItems(player, MARK_OF_LIFE))
					{
						giveItems(player, MARK_OF_LIFE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_HEALER))
					{
						giveItems(player, MARK_OF_HEALER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_24":
			{
				if ((player.getClassId() == ClassId.PALUS_KNIGHT))
				{
					if (!hasQuestItems(player, MARK_OF_DUTY))
					{
						giveItems(player, MARK_OF_DUTY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_FATE))
					{
						giveItems(player, MARK_OF_FATE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_WITCHCRAFT))
					{
						giveItems(player, MARK_OF_WITCHCRAFT, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_25":
			{
				if ((player.getClassId() == ClassId.PALUS_KNIGHT))
				{
					if (!hasQuestItems(player, MARK_OF_CHALLENGER))
					{
						giveItems(player, MARK_OF_CHALLENGER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_FATE))
					{
						giveItems(player, MARK_OF_FATE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_DUELIST))
					{
						giveItems(player, MARK_OF_DUELIST, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_26":
			{
				if ((player.getClassId() == ClassId.ASSASSIN))
				{
					if (!hasQuestItems(player, MARK_OF_SEEKER))
					{
						giveItems(player, MARK_OF_SEEKER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_FATE))
					{
						giveItems(player, MARK_OF_FATE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SEARCHER))
					{
						giveItems(player, MARK_OF_SEARCHER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_27":
			{
				if ((player.getClassId() == ClassId.ASSASSIN))
				{
					if (!hasQuestItems(player, MARK_OF_SEEKER))
					{
						giveItems(player, MARK_OF_SEEKER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_FATE))
					{
						giveItems(player, MARK_OF_FATE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SAGITTARIUS))
					{
						giveItems(player, MARK_OF_SAGITTARIUS, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_28":
			{
				if ((player.getClassId() == ClassId.DARK_WIZARD))
				{
					if (!hasQuestItems(player, MARK_OF_SCHOLAR))
					{
						giveItems(player, MARK_OF_SCHOLAR, 1);
					}
					if (!hasQuestItems(player, MARK_OF_FATE))
					{
						giveItems(player, MARK_OF_FATE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_MAGUS))
					{
						giveItems(player, MARK_OF_MAGUS, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_29":
			{
				if ((player.getClassId() == ClassId.DARK_WIZARD))
				{
					if (!hasQuestItems(player, MARK_OF_SCHOLAR))
					{
						giveItems(player, MARK_OF_SCHOLAR, 1);
					}
					if (!hasQuestItems(player, MARK_OF_FATE))
					{
						giveItems(player, MARK_OF_FATE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SUMMONER))
					{
						giveItems(player, MARK_OF_SUMMONER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_30":
			{
				if ((player.getClassId() == ClassId.SHILLIEN_ORACLE))
				{
					if (!hasQuestItems(player, MARK_OF_PILGRIM))
					{
						giveItems(player, MARK_OF_PILGRIM, 1);
					}
					if (!hasQuestItems(player, MARK_OF_FATE))
					{
						giveItems(player, MARK_OF_FATE, 1);
					}
					if (!hasQuestItems(player, MARK_OF_REFORMER))
					{
						giveItems(player, MARK_OF_REFORMER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_31":
			{
				if ((player.getClassId() == ClassId.ORC_RAIDER))
				{
					if (!hasQuestItems(player, MARK_OF_CHALLENGER))
					{
						giveItems(player, MARK_OF_CHALLENGER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_GLORY))
					{
						giveItems(player, MARK_OF_GLORY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_CHAMPION))
					{
						giveItems(player, MARK_OF_CHAMPION, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_32":
			{
				if ((player.getClassId() == ClassId.ORC_MONK))
				{
					if (!hasQuestItems(player, MARK_OF_CHALLENGER))
					{
						giveItems(player, MARK_OF_CHALLENGER, 1);
					}
					if (!hasQuestItems(player, MARK_OF_GLORY))
					{
						giveItems(player, MARK_OF_GLORY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_DUELIST))
					{
						giveItems(player, MARK_OF_DUELIST, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_33":
			{
				if ((player.getClassId() == ClassId.ORC_SHAMAN))
				{
					if (!hasQuestItems(player, MARK_OF_PILGRIM))
					{
						giveItems(player, MARK_OF_PILGRIM, 1);
					}
					if (!hasQuestItems(player, MARK_OF_GLORY))
					{
						giveItems(player, MARK_OF_GLORY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_LORD))
					{
						giveItems(player, MARK_OF_LORD, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_34":
			{
				if ((player.getClassId() == ClassId.ORC_SHAMAN))
				{
					if (!hasQuestItems(player, MARK_OF_PILGRIM))
					{
						giveItems(player, MARK_OF_PILGRIM, 1);
					}
					if (!hasQuestItems(player, MARK_OF_GLORY))
					{
						giveItems(player, MARK_OF_GLORY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_WARSPIRIT))
					{
						giveItems(player, MARK_OF_WARSPIRIT, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_35":
			{
				if ((player.getClassId() == ClassId.SCAVENGER))
				{
					if (!hasQuestItems(player, MARK_OF_GUILDSMAN))
					{
						giveItems(player, MARK_OF_GUILDSMAN, 1);
					}
					if (!hasQuestItems(player, MARK_OF_PROSPERITY))
					{
						giveItems(player, MARK_OF_PROSPERITY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_SEARCHER))
					{
						giveItems(player, MARK_OF_SEARCHER, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "REPLY_36":
			{
				if ((player.getClassId() == ClassId.ARTISAN))
				{
					if (!hasQuestItems(player, MARK_OF_GUILDSMAN))
					{
						giveItems(player, MARK_OF_GUILDSMAN, 1);
					}
					if (!hasQuestItems(player, MARK_OF_PROSPERITY))
					{
						giveItems(player, MARK_OF_PROSPERITY, 1);
					}
					if (!hasQuestItems(player, MARK_OF_MAESTRO))
					{
						giveItems(player, MARK_OF_MAESTRO, 1);
					}
					htmltext = "31092-25.html";
				}
				break;
			}
			case "32487-04.html":
			{
				if (qs.isMemoState(1))
				{
					if (!npc.getVariables().getBoolean("SPAWNED", false))
					{
						npc.getVariables().set("SPAWNED", true);
						npc.getVariables().set("PLAYER_ID", player.getObjectId());
						final L2Npc pursuer = addSpawn(PURSUER, player.getX() + 50, player.getY() + 50, player.getZ(), 0, false, 0);
						pursuer.getVariables().set("PLAYER_ID", player.getObjectId());
						pursuer.getVariables().set("npc0", npc);
						pursuer.getVariables().set("player0", player);
						addAttackDesire(pursuer, player);
						htmltext = event;
					}
					else
					{
						htmltext = "32487-05.html";
					}
				}
				break;
			}
			case "32487-10.html":
			{
				if (qs.isMemoState(7))
				{
					takeItems(player, HELVETIAS_ANTIDOTE, 1);
					qs.setMemoState(8);
					qs.setCond(8, true);
					if (npc.getVariables().getBoolean("SPAWNED", true))
					{
						npc.getVariables().set("SPAWNED", false);
					}
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			if (qs.isMemoState(1))
			{
				if (killer.isPlayer())
				{
					if (killer.getObjectId() == npc.getVariables().getInt("PLAYER_ID", 0))
					{
						qs.setMemoState(2);
						qs.setCond(2, true);
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.YOU_ARE_STRONG_THIS_WAS_A_MISTAKE));
					}
					else
					{
						npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.WHO_ARE_YOU_TO_JOIN_IN_THE_BATTLE_HOW_UPSETTING));
					}
				}
			}
			final L2Npc npc0 = npc.getVariables().getObject("npc0", L2Npc.class);
			if (npc0 != null)
			{
				npc0.getVariables().set("SPAWNED", false);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		final int memoState = qs.getMemoState();
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			if (npc.getId() == BLUEPRINT_SELLER_DAEGER)
			{
				if (player.getRace() != Race.KAMAEL)
				{
					if (player.isInCategory(CategoryType.SECOND_CLASS_GROUP))
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? "31435-01.htm" : "31435-03.htm";
					}
					else
					{
						htmltext = "31435-04.htm";
					}
				}
				else
				{
					htmltext = "31435-06.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case BLUEPRINT_SELLER_DAEGER:
				{
					if (memoState <= 2)
					{
						htmltext = "31435-08.html";
					}
					else if (memoState == 3)
					{
						htmltext = "31435-09.html";
					}
					else if (memoState == 4)
					{
						htmltext = "31435-11.html";
					}
					else if ((memoState > 4) && (memoState < 8))
					{
						htmltext = "31435-12.html";
					}
					else if (memoState == 8)
					{
						htmltext = "31435-13.html";
					}
					else if (memoState == 9)
					{
						qs.setMemoState(10);
						qs.setCond(10, true);
						htmltext = "31435-15.html";
					}
					else if (memoState == 10)
					{
						htmltext = "31435-16.html";
					}
					break;
				}
				case GROCER_HELVERIA:
				{
					if (memoState == 4)
					{
						htmltext = "30081-01.html";
					}
					else if (memoState == 5)
					{
						htmltext = "30081-04.html";
					}
					else if (memoState == 6)
					{
						htmltext = "30081-08.html";
					}
					else if (memoState == 7)
					{
						if (!hasQuestItems(player, HELVETIAS_ANTIDOTE))
						{
							giveItems(player, HELVETIAS_ANTIDOTE, 1);
							htmltext = "30081-09.html";
						}
						else
						{
							htmltext = "30081-10.html";
						}
					}
					break;
				}
				case BLACK_MARKETEER_OF_MAMMON:
				{
					if (memoState == 10)
					{
						if (player.isInCategory(CategoryType.SECOND_CLASS_GROUP))
						{
							qs.setMemoStateEx(1, 0);
							htmltext = "31092-01.html";
						}
						else
						{
							giveItems(player, Inventory.ADENA_ID, THREE_MILLION);
							qs.exitQuest(false, true);
							htmltext = "31092-01a.html";
						}
					}
					break;
				}
				case MARK:
				{
					if (memoState == 1)
					{
						if (!npc.getVariables().getBoolean("SPAWNED", false))
						{
							htmltext = "32487-01.html";
						}
						else if (npc.getVariables().getBoolean("SPAWNED", true) && (npc.getVariables().getInt("PLAYER_ID", 0) == player.getObjectId()))
						{
							htmltext = "32487-03.html";
						}
						else if (npc.getVariables().getBoolean("SPAWNED", true))
						{
							htmltext = "32487-02.html";
						}
					}
					else if (memoState == 2)
					{
						giveItems(player, BLOODY_CLOTH_FRAGMENT, 1);
						qs.setMemoState(3);
						qs.setCond(3, true);
						htmltext = "32487-06.html";
					}
					else if ((memoState >= 3) && (memoState < 7))
					{
						htmltext = "32487-07.html";
					}
					else if (memoState == 7)
					{
						htmltext = "32487-09.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == BLUEPRINT_SELLER_DAEGER)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
			else if (npc.getId() == BLACK_MARKETEER_OF_MAMMON)
			{
				if (player.isInCategory(CategoryType.SECOND_CLASS_GROUP))
				{
					htmltext = "31092-23.html";
				}
				else
				{
					htmltext = "31092-24.html";
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		startQuestTimer("DESPAWN", 60000, npc, null);
		final L2PcInstance player = npc.getVariables().getObject("player0", L2PcInstance.class);
		if (player != null)
		{
			if (player.isPlayer())
			{
				npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.S1_I_MUST_KILL_YOU_BLAME_YOUR_OWN_CURIOSITY).addStringParameter(player.getAppearance().getVisibleName()));
			}
		}
		return super.onSpawn(npc);
	}
}
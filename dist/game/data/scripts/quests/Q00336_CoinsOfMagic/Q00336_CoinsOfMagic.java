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
package quests.Q00336_CoinsOfMagic;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Coin Of Magic (336)
 * @author Zealar
 */
public final class Q00336_CoinsOfMagic extends Quest
{
	// NPCs
	private static final int PANO = 30078;
	private static final int COLLOB = 30092;
	private static final int RAPIN = 30165;
	private static final int HAGGER = 30183;
	private static final int STAN = 30200;
	private static final int WAREHOUSE_KEEPER_SORINT = 30232;
	private static final int RESEARCHER_LORAIN = 30673;
	private static final int BLACKSMITH_DUNING = 30688;
	private static final int MAGISTER_PAGE = 30696;
	private static final int UNION_PRESIDENT_BERNARD = 30702;
	private static final int HEAD_BLACKSMITH_FERRIS = 30847;
	// Items
	private static final int Q_BLOOD_MEDUSA = 3472;
	private static final int Q_BLOOD_WEREWOLF = 3473;
	private static final int Q_BLOOD_BASILISK = 3474;
	private static final int Q_BLOOD_DREVANUL = 3475;
	private static final int Q_BLOOD_SUCCUBUS = 3476;
	private static final int Q_BLOOD_DRAGON = 3477;
	private static final int Q_BERETHS_BLOOD_DRAGON = 3478;
	private static final int Q_MANAKS_BLOOD_WEREWOLF = 3479;
	private static final int Q_NIAS_BLOOD_MEDUSA = 3480;
	private static final int Q_GOLD_DRAGON = 3481;
	private static final int Q_GOLD_WYVERN = 3482;
	private static final int Q_GOLD_KNIGHT = 3483;
	private static final int Q_GOLD_GIANT = 3484;
	private static final int Q_GOLD_DRAKE = 3485;
	private static final int Q_GOLD_WYRM = 3486;
	private static final int Q_BERETHS_GOLD_DRAGON = 3487;
	private static final int Q_MANAKS_GOLD_GIANT = 3488;
	private static final int Q_NIAS_GOLD_WYVERN = 3489;
	private static final int Q_SILVER_UNICORN = 3490;
	private static final int Q_SILVER_FAIRY = 3491;
	private static final int Q_SILVER_DRYAD = 3492;
	private static final int Q_SILVER_DRAGON = 3493;
	private static final int Q_SILVER_GOLEM = 3494;
	private static final int Q_SILVER_UNDINE = 3495;
	private static final int Q_BERETHS_SILVER_DRAGON = 3496;
	private static final int Q_MANAKS_SILVER_DRYAD = 3497;
	private static final int Q_NIAS_SILVER_FAIRY = 3498;
	private static final int Q_COIN_DIAGRAM = 3811;
	private static final int Q_KALDIS_GOLD_DRAGON = 3812;
	private static final int Q_CC_MEMBERSHIP_1 = 3813;
	private static final int Q_CC_MEMBERSHIP_2 = 3814;
	private static final int Q_CC_MEMBERSHIP_3 = 3815;
	// Monsters
	private static final int HEADLESS_KNIGHT = 20146;
	private static final int OEL_MAHUM = 20161;
	private static final int SHACKLE = 20235;
	private static final int ROYAL_CAVE_SERVANT = 20240;
	private static final int MALRUK_SUCCUBUS_TUREN = 20245;
	private static final int ROYAL_CAVE_SERVANT_HOLD = 20276;
	private static final int SHACKLE_HOLD = 20279;
	private static final int HEADLESS_KNIGHT_HOLD = 20280;
	private static final int H_MALRUK_SUCCUBUS_TUREN = 20284;
	private static final int BYFOOT = 20568;
	private static final int BYFOOT_SIGEL = 20569;
	private static final int TARLK_BUGBEAR_BOSS = 20572;
	private static final int OEL_MAHUM_WARRIOR = 20575;
	private static final int OEL_MAHUM_WITCH_DOCTOR = 20576;
	private static final int TIMAK_ORC = 20583;
	private static final int TIMAK_ORC_ARCHER = 20584;
	private static final int TIMAK_ORC_SOLDIER = 20585;
	private static final int TIMAK_ORC_SHAMAN = 20587;
	private static final int LAKIN = 20604;
	private static final int HARIT_LIZARDMAN_SHAMAN = 20644;
	private static final int HARIT_LIZARDM_MATRIARCH = 20645;
	private static final int HATAR_HANISHEE = 20663;
	private static final int DOOM_KNIGHT = 20674;
	private static final int PUNISHMENT_OF_UNDEAD = 20678;
	private static final int VANOR_SILENOS_SHAMAN = 20685;
	private static final int HUNGRY_CORPSE = 20954;
	private static final int NIHIL_INVADER = 20957;
	private static final int DARK_GUARD = 20959;
	private static final int BLOODY_GHOST = 20960;
	private static final int FLOAT_OF_GRAVE = 21003;
	private static final int DOOM_SERVANT = 21006;
	private static final int DOOM_ARCHER = 21008;
	private static final int KUKABURO = 21274;
	private static final int KUKABURO_A = 21275;
	private static final int KUKABURO_B = 21276;
	private static final int ANTELOPE = 21278;
	private static final int ANTELOPE_A = 21279;
	private static final int ANTELOPE_B = 21280;
	private static final int BANDERSNATCH = 21282;
	private static final int BANDERSNATCH_A = 21283;
	private static final int BANDERSNATCH_B = 21284;
	private static final int BUFFALO = 21286;
	private static final int BUFFALO_A = 21287;
	private static final int BUFFALO_B = 21288;
	private static final int BRILLIANT_CLAW = 21521;
	private static final int BRILLIANT_CLAW_1 = 21522;
	private static final int BRILLIANT_WISDOM = 21526;
	private static final int BRILLIANT_VENGEANCE = 21531;
	private static final int BRILLIANT_VENGEANCE_1 = 21658;
	private static final int BRILLIANT_ANGUISH = 21539;
	private static final int BRILLIANT_ANGUISH_1 = 21540;
	// Rewards
	private static final int DEMON_STAFF = 206;
	private static final int DARK_SCREAMER = 233;
	private static final int WIDOW_MAKER = 303;
	private static final int SWORD_OF_LIMIT = 132;
	private static final int DEMONS_BOOTS = 2435;
	private static final int DEMONS_HOSE = 472;
	private static final int DEMONS_GLOVES = 2459;
	private static final int FULL_PLATE_HELMET = 2414;
	private static final int MOONSTONE_EARING = 852;
	private static final int NASSENS_EARING = 855;
	private static final int RING_OF_BINDING = 886;
	private static final int NECKLACE_OF_PROTECTION = 916;
	// Variables name
	private static final String WEIGHT_POINT = "weight_point";
	private static final String PARAM_1 = "param1";
	private static final String PARAM_2 = "param2";
	private static final String PARAM_3 = "param3";
	private static final String FLAG = "flag";
	
	public Q00336_CoinsOfMagic()
	{
		super(336);
		addStartNpc(WAREHOUSE_KEEPER_SORINT);
		addTalkId(PANO, COLLOB, RAPIN, HAGGER, STAN, RESEARCHER_LORAIN, BLACKSMITH_DUNING, MAGISTER_PAGE, UNION_PRESIDENT_BERNARD, HEAD_BLACKSMITH_FERRIS);
		addKillId(HEADLESS_KNIGHT, OEL_MAHUM, SHACKLE, ROYAL_CAVE_SERVANT, MALRUK_SUCCUBUS_TUREN, ROYAL_CAVE_SERVANT_HOLD, SHACKLE_HOLD, HEADLESS_KNIGHT_HOLD, H_MALRUK_SUCCUBUS_TUREN, BYFOOT, BYFOOT_SIGEL, TARLK_BUGBEAR_BOSS, OEL_MAHUM_WARRIOR, OEL_MAHUM_WITCH_DOCTOR, TIMAK_ORC, TIMAK_ORC_ARCHER, TIMAK_ORC_SOLDIER, TIMAK_ORC_SHAMAN, LAKIN, HARIT_LIZARDMAN_SHAMAN, HARIT_LIZARDM_MATRIARCH, HATAR_HANISHEE, DOOM_KNIGHT, PUNISHMENT_OF_UNDEAD, VANOR_SILENOS_SHAMAN, HUNGRY_CORPSE, NIHIL_INVADER, DARK_GUARD, BLOODY_GHOST, FLOAT_OF_GRAVE, DOOM_SERVANT, DOOM_ARCHER, KUKABURO, KUKABURO_A, KUKABURO_B, ANTELOPE, ANTELOPE_A, ANTELOPE_B, BANDERSNATCH, BANDERSNATCH_A, BANDERSNATCH_B, BUFFALO, BUFFALO_A, BUFFALO_B, BRILLIANT_CLAW, BRILLIANT_CLAW_1, BRILLIANT_WISDOM, BRILLIANT_VENGEANCE, BRILLIANT_VENGEANCE_1, BRILLIANT_ANGUISH, BRILLIANT_ANGUISH_1);
		registerQuestItems(Q_COIN_DIAGRAM, Q_KALDIS_GOLD_DRAGON, Q_CC_MEMBERSHIP_1, Q_CC_MEMBERSHIP_2, Q_CC_MEMBERSHIP_3);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		final String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case PANO:
			case COLLOB:
			case HEAD_BLACKSMITH_FERRIS:
			{
				if (hasQuestItems(player, Q_CC_MEMBERSHIP_1))
				{
					resetParams(qs);
					return npc.getId() + "-01.html";
				}
				if (hasQuestItems(player, Q_CC_MEMBERSHIP_2) || hasQuestItems(player, Q_CC_MEMBERSHIP_3))
				{
					return npc.getId() + "-54.html";
				}
				break;
			}
			case RAPIN:
			case STAN:
			case BLACKSMITH_DUNING:
			{
				if (hasQuestItems(player, Q_CC_MEMBERSHIP_1) || hasQuestItems(player, Q_CC_MEMBERSHIP_2))
				{
					resetParams(qs);
					return npc.getId() + "-01.html";
				}
				if (hasQuestItems(player, Q_CC_MEMBERSHIP_3))
				{
					return npc.getId() + "-54.html";
				}
				break;
			}
			case HAGGER:
			case MAGISTER_PAGE:
			case RESEARCHER_LORAIN:
			{
				if (hasQuestItems(player, Q_CC_MEMBERSHIP_1) || hasQuestItems(player, Q_CC_MEMBERSHIP_2) || hasQuestItems(player, Q_CC_MEMBERSHIP_3))
				{
					resetParams(qs);
					return npc.getId() + "-01.html";
				}
				break;
			}
			case UNION_PRESIDENT_BERNARD:
			{
				if ((qs.getMemoState() == 1) && hasQuestItems(player, Q_COIN_DIAGRAM))
				{
					return "30702-01.html";
				}
				if (qs.getMemoState() >= 3)
				{
					return "30702-05.html";
				}
				if (qs.getMemoState() == 2)
				{
					return "30702-02a.html";
				}
			}
			case WAREHOUSE_KEEPER_SORINT:
			{
				if (qs.isCreated())
				{
					return player.getLevel() < 40 ? "30232-01.htm" : "30232-02.htm";
				}
				if (qs.isStarted())
				{
					if (!hasQuestItems(player, Q_KALDIS_GOLD_DRAGON) && ((qs.getMemoState() == 1) || (qs.getMemoState() == 2)))
					{
						return "30232-06.html";
					}
					if (hasQuestItems(player, Q_KALDIS_GOLD_DRAGON) && ((qs.getMemoState() == 1) || (qs.getMemoState() == 2)))
					{
						giveItems(player, Q_CC_MEMBERSHIP_3, 1);
						takeItems(player, Q_COIN_DIAGRAM, -1);
						takeItems(player, Q_KALDIS_GOLD_DRAGON, 1);
						qs.setMemoState(3);
						qs.setCond(4);
						qs.showQuestionMark(336);
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						return "30232-07.html";
					}
					if (hasQuestItems(player, Q_CC_MEMBERSHIP_3) && (qs.getMemoState() == 3))
					{
						return "30232-10.html";
					}
					if (hasQuestItems(player, Q_CC_MEMBERSHIP_2) && (qs.getMemoState() == 3))
					{
						return "30232-11.html";
					}
					if (hasQuestItems(player, Q_CC_MEMBERSHIP_1) && (qs.getMemoState() == 3))
					{
						return "30232-12.html";
					}
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		final String htmltext = null;
		
		if (event.equals("QUEST_ACCEPTED"))
		{
			playSound(player, QuestSound.ITEMSOUND_QUEST_ACCEPT);
			if (!hasQuestItems(player, Q_COIN_DIAGRAM))
			{
				giveItems(player, Q_COIN_DIAGRAM, 1);
			}
			qs.setMemoState(1);
			qs.startQuest();
			qs.showQuestionMark(336);
			playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
			return "30232-05.htm";
		}
		if (event.contains(".htm"))
		{
			return event;
		}
		final int npcId = npc.getId();
		final int eventID = Integer.parseInt(event);
		
		switch (npcId)
		{
			case PANO:
			case COLLOB:
			case RAPIN:
			case HAGGER:
			case STAN:
			case RESEARCHER_LORAIN:
			case BLACKSMITH_DUNING:
			case MAGISTER_PAGE:
			case HEAD_BLACKSMITH_FERRIS:
			{
				switch (eventID)
				{
					case 1:
					{
						qs.set(PARAM_2, 11);
						return npcId + "-02.html";
					}
					case 2:
					{
						qs.set(PARAM_2, 21);
						return npcId + "-03.html";
					}
					case 3:
					{
						qs.set(PARAM_2, 31);
						return npcId + "-04.html";
					}
					case 4:
					{
						qs.set(PARAM_2, 42);
						return npcId + "-05.html";
					}
					case 5:
					{
						return npcId + "-06.html";
					}
					case 9:
					{
						return npcId + "-53.html";
					}
					case 13:
					{
						if (qs.getInt(FLAG) == 1)
						{
							qs.set(FLAG, 16);
							return npcId + "-14.html";
						}
						break;
					}
					case 14:
					{
						if (qs.getInt(FLAG) == 1)
						{
							qs.set(FLAG, 32);
							return npcId + "-15.html";
						}
						break;
					}
					case 15:
					{
						if (qs.getInt(FLAG) == 1)
						{
							qs.set(FLAG, 48);
							return npcId + "-16.html";
						}
						break;
					}
					case 16:
					{
						qs.set(FLAG, qs.getInt(FLAG) + 4);
						return npcId + "-17.html";
					}
					case 17:
					{
						qs.set(FLAG, qs.getInt(FLAG) + 8);
						return npcId + "-18.html";
					}
					case 18:
					{
						qs.set(FLAG, qs.getInt(FLAG) + 12);
						return npcId + "-19.html";
					}
					case 22:
					{
						return npcId + "-01.html";
					}
				}
			}
		}
		switch (npcId)
		{
			case PANO:
			{
				switch (eventID)
				{
					case 6:
					{
						return shortFirstSteps(qs, PANO, 1, 4, Q_SILVER_DRYAD, Q_SILVER_UNDINE, 1, Q_GOLD_GIANT, Q_SILVER_DRYAD, Q_BLOOD_BASILISK);
					}
					case 7:
					{
						return shortFirstSteps(qs, PANO, 2, 8, Q_SILVER_DRYAD, Q_SILVER_UNDINE, 1, Q_GOLD_GIANT, Q_SILVER_DRYAD, Q_BLOOD_BASILISK);
					}
					case 8:
					{
						return shortFirstSteps(qs, PANO, 3, 9, Q_SILVER_DRYAD, Q_SILVER_UNDINE, 1, Q_GOLD_GIANT, Q_SILVER_DRYAD, Q_BLOOD_BASILISK);
					}
					case 10:
					{
						return shortSecondStepTwoItems(qs, PANO, 1, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_BERETHS_SILVER_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 11:
					{
						return shortSecondStepTwoItems(qs, PANO, 5, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_BERETHS_SILVER_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 12:
					{
						return shortSecondStepTwoItems(qs, PANO, 10, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_BERETHS_SILVER_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 19:
					{
						return shortThirdStep(qs, PANO, 1, Q_BERETHS_SILVER_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_SILVER_DRAGON);
					}
					case 20:
					{
						return shortThirdStep(qs, PANO, 2, Q_BERETHS_SILVER_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_SILVER_DRAGON);
					}
					case 21:
					{
						return shortThirdStep(qs, PANO, 3, Q_BERETHS_SILVER_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_SILVER_DRAGON);
					}
				}
				break;
			}
			case COLLOB:
			{
				switch (eventID)
				{
					case 6:
					{
						return shortFirstSteps(qs, COLLOB, 1, 4, Q_GOLD_WYRM, Q_GOLD_GIANT, 1, Q_GOLD_WYRM, Q_SILVER_UNDINE, Q_BLOOD_SUCCUBUS);
					}
					case 7:
					{
						return shortFirstSteps(qs, COLLOB, 2, 8, Q_GOLD_WYRM, Q_GOLD_GIANT, 1, Q_GOLD_WYRM, Q_SILVER_UNDINE, Q_BLOOD_SUCCUBUS);
					}
					case 8:
					{
						return shortFirstSteps(qs, COLLOB, 3, 9, Q_GOLD_WYRM, Q_GOLD_GIANT, 1, Q_GOLD_WYRM, Q_SILVER_UNDINE, Q_BLOOD_SUCCUBUS);
					}
					case 10:
					{
						return shortSecondStepTwoItems(qs, COLLOB, 1, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_BERETHS_GOLD_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 11:
					{
						return shortSecondStepTwoItems(qs, COLLOB, 5, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_BERETHS_GOLD_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 12:
					{
						return shortSecondStepTwoItems(qs, COLLOB, 10, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_BERETHS_GOLD_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 19:
					{
						return shortThirdStep(qs, COLLOB, 1, Q_BERETHS_GOLD_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_BLOOD_DRAGON);
					}
					case 20:
					{
						return shortThirdStep(qs, COLLOB, 2, Q_BERETHS_GOLD_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_BLOOD_DRAGON);
					}
					case 21:
					{
						return shortThirdStep(qs, COLLOB, 3, Q_BERETHS_GOLD_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_BLOOD_DRAGON);
					}
				}
				break;
			}
			case RAPIN:
			{
				switch (eventID)
				{
					case 6:
					{
						return shortFirstSteps(qs, RAPIN, 1, 3, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, 1, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_GOLD_DRAKE);
					}
					case 7:
					{
						return shortFirstSteps(qs, RAPIN, 2, 7, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, 1, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_GOLD_DRAKE);
					}
					case 8:
					{
						return shortFirstSteps(qs, RAPIN, 3, 9, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, 1, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_GOLD_DRAKE);
					}
					case 10:
					{
						return shortSecondStepTwoItems(qs, RAPIN, 1, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, Q_MANAKS_BLOOD_WEREWOLF, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_UNDINE, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_DRYAD, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, Q_GOLD_WYRM);
					}
					case 11:
					{
						return shortSecondStepTwoItems(qs, RAPIN, 5, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, Q_MANAKS_BLOOD_WEREWOLF, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_UNDINE, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_DRYAD, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, Q_GOLD_WYRM);
					}
					case 12:
					{
						return shortSecondStepTwoItems(qs, RAPIN, 10, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, Q_MANAKS_BLOOD_WEREWOLF, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_UNDINE, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_DRYAD, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, Q_GOLD_WYRM);
					}
					case 19:
					{
						return shortThirdStep(qs, RAPIN, 1, Q_MANAKS_BLOOD_WEREWOLF, Q_SILVER_UNDINE, Q_SILVER_DRYAD, Q_GOLD_WYRM);
					}
					case 20:
					{
						return shortThirdStep(qs, RAPIN, 2, Q_MANAKS_BLOOD_WEREWOLF, Q_SILVER_UNDINE, Q_SILVER_DRYAD, Q_GOLD_WYRM);
					}
					case 21:
					{
						return shortThirdStep(qs, RAPIN, 3, Q_MANAKS_BLOOD_WEREWOLF, Q_SILVER_UNDINE, Q_SILVER_DRYAD, Q_GOLD_WYRM);
					}
				}
				break;
			}
			case HAGGER:
			{
				switch (eventID)
				{
					case 6:
					{
						return shortFirstSteps(qs, HAGGER, 1, 4, Q_SILVER_UNICORN, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 7:
					{
						return shortFirstSteps(qs, HAGGER, 2, 8, Q_SILVER_UNICORN, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 8:
					{
						return shortFirstSteps(qs, HAGGER, 3, 9, Q_SILVER_UNICORN, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 10:
					{
						return shortSecondStepOneItem(qs, HAGGER, 1, Q_SILVER_UNICORN, 2, Q_NIAS_SILVER_FAIRY, Q_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_UNICORN, Q_SILVER_GOLEM, Q_GOLD_WYVERN, Q_GOLD_DRAKE);
					}
					case 11:
					{
						return shortSecondStepOneItem(qs, HAGGER, 5, Q_SILVER_UNICORN, 2, Q_NIAS_SILVER_FAIRY, Q_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_UNICORN, Q_SILVER_GOLEM, Q_GOLD_WYVERN, Q_GOLD_DRAKE);
					}
					case 12:
					{
						return shortSecondStepOneItem(qs, HAGGER, 10, Q_SILVER_UNICORN, 2, Q_NIAS_SILVER_FAIRY, Q_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_UNICORN, Q_SILVER_GOLEM, Q_GOLD_WYVERN, Q_GOLD_DRAKE);
					}
					case 19:
					{
						return shortThirdStep(qs, HAGGER, 1, Q_NIAS_SILVER_FAIRY, Q_BLOOD_WEREWOLF, Q_SILVER_GOLEM, Q_GOLD_DRAKE);
					}
					case 20:
					{
						return shortThirdStep(qs, HAGGER, 2, Q_NIAS_SILVER_FAIRY, Q_BLOOD_WEREWOLF, Q_SILVER_GOLEM, Q_GOLD_DRAKE);
					}
					case 21:
					{
						return shortThirdStep(qs, HAGGER, 3, Q_NIAS_SILVER_FAIRY, Q_BLOOD_WEREWOLF, Q_SILVER_GOLEM, Q_GOLD_DRAKE);
					}
				}
				break;
			}
			case STAN:
			{
				switch (eventID)
				{
					case 6:
					{
						return shortFirstSteps(qs, STAN, 1, 3, Q_SILVER_FAIRY, Q_SILVER_GOLEM, 1, Q_SILVER_FAIRY, Q_BLOOD_WEREWOLF, Q_GOLD_KNIGHT);
					}
					case 7:
					{
						return shortFirstSteps(qs, STAN, 2, 7, Q_SILVER_FAIRY, Q_SILVER_GOLEM, 1, Q_SILVER_FAIRY, Q_BLOOD_WEREWOLF, Q_GOLD_KNIGHT);
					}
					case 8:
					{
						return shortFirstSteps(qs, STAN, 3, 9, Q_SILVER_FAIRY, Q_SILVER_GOLEM, 1, Q_SILVER_FAIRY, Q_BLOOD_WEREWOLF, Q_GOLD_KNIGHT);
					}
					case 10:
					{
						return shortSecondStepTwoItems(qs, STAN, 1, Q_SILVER_FAIRY, Q_SILVER_GOLEM, Q_MANAKS_SILVER_DRYAD, Q_SILVER_FAIRY, Q_SILVER_GOLEM, Q_SILVER_DRYAD, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, Q_BLOOD_BASILISK, Q_GOLD_KNIGHT, Q_GOLD_DRAKE, Q_GOLD_GIANT);
					}
					case 11:
					{
						return shortSecondStepTwoItems(qs, STAN, 5, Q_SILVER_FAIRY, Q_SILVER_GOLEM, Q_MANAKS_SILVER_DRYAD, Q_SILVER_FAIRY, Q_SILVER_GOLEM, Q_SILVER_DRYAD, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, Q_BLOOD_BASILISK, Q_GOLD_KNIGHT, Q_GOLD_DRAKE, Q_GOLD_GIANT);
					}
					case 12:
					{
						return shortSecondStepTwoItems(qs, STAN, 10, Q_SILVER_FAIRY, Q_SILVER_GOLEM, Q_MANAKS_SILVER_DRYAD, Q_SILVER_FAIRY, Q_SILVER_GOLEM, Q_SILVER_DRYAD, Q_BLOOD_WEREWOLF, Q_BLOOD_DREVANUL, Q_BLOOD_BASILISK, Q_GOLD_KNIGHT, Q_GOLD_DRAKE, Q_GOLD_GIANT);
					}
					case 19:
					{
						return shortThirdStep(qs, STAN, 1, Q_MANAKS_SILVER_DRYAD, Q_SILVER_DRYAD, Q_BLOOD_BASILISK, Q_GOLD_GIANT);
					}
					case 20:
					{
						return shortThirdStep(qs, STAN, 2, Q_MANAKS_SILVER_DRYAD, Q_SILVER_DRYAD, Q_BLOOD_BASILISK, Q_GOLD_GIANT);
					}
					case 21:
					{
						return shortThirdStep(qs, STAN, 3, Q_MANAKS_SILVER_DRYAD, Q_SILVER_DRYAD, Q_BLOOD_BASILISK, Q_GOLD_GIANT);
					}
				}
				break;
			}
			case RESEARCHER_LORAIN:
			{
				switch (eventID)
				{
					case 6:
					{
						return shortFirstSteps(qs, RESEARCHER_LORAIN, 1, 4, Q_GOLD_WYVERN, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 7:
					{
						return shortFirstSteps(qs, RESEARCHER_LORAIN, 2, 8, Q_GOLD_WYVERN, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 8:
					{
						return shortFirstSteps(qs, RESEARCHER_LORAIN, 3, 9, Q_GOLD_WYVERN, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 10:
					{
						return shortSecondStepOneItem(qs, RESEARCHER_LORAIN, 1, Q_GOLD_WYVERN, 2, Q_NIAS_GOLD_WYVERN, Q_BLOOD_MEDUSA, Q_BLOOD_DREVANUL, Q_SILVER_UNICORN, Q_SILVER_GOLEM, Q_GOLD_WYVERN, Q_GOLD_KNIGHT);
					}
					case 11:
					{
						return shortSecondStepOneItem(qs, RESEARCHER_LORAIN, 5, Q_GOLD_WYVERN, 2, Q_NIAS_GOLD_WYVERN, Q_BLOOD_MEDUSA, Q_BLOOD_DREVANUL, Q_SILVER_UNICORN, Q_SILVER_GOLEM, Q_GOLD_WYVERN, Q_GOLD_KNIGHT);
					}
					case 12:
					{
						return shortSecondStepOneItem(qs, RESEARCHER_LORAIN, 10, Q_GOLD_WYVERN, 2, Q_NIAS_GOLD_WYVERN, Q_BLOOD_MEDUSA, Q_BLOOD_DREVANUL, Q_SILVER_UNICORN, Q_SILVER_GOLEM, Q_GOLD_WYVERN, Q_GOLD_KNIGHT);
					}
					case 19:
					{
						return shortThirdStep(qs, RESEARCHER_LORAIN, 1, Q_NIAS_GOLD_WYVERN, Q_BLOOD_DREVANUL, Q_SILVER_GOLEM, Q_GOLD_KNIGHT);
					}
					case 20:
					{
						return shortThirdStep(qs, RESEARCHER_LORAIN, 2, Q_NIAS_GOLD_WYVERN, Q_BLOOD_DREVANUL, Q_SILVER_GOLEM, Q_GOLD_KNIGHT);
					}
					case 21:
					{
						return shortThirdStep(qs, RESEARCHER_LORAIN, 3, Q_NIAS_GOLD_WYVERN, Q_BLOOD_DREVANUL, Q_SILVER_GOLEM, Q_GOLD_KNIGHT);
					}
				}
				break;
			}
			case BLACKSMITH_DUNING:
			{
				switch (eventID)
				{
					case 6:
					{
						return shortFirstSteps(qs, BLACKSMITH_DUNING, 1, 3, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, 1, Q_SILVER_GOLEM, Q_BLOOD_DREVANUL, Q_GOLD_DRAKE);
					}
					case 7:
					{
						return shortFirstSteps(qs, BLACKSMITH_DUNING, 2, 7, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, 1, Q_SILVER_GOLEM, Q_BLOOD_DREVANUL, Q_GOLD_DRAKE);
					}
					case 8:
					{
						return shortFirstSteps(qs, BLACKSMITH_DUNING, 3, 9, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, 1, Q_SILVER_GOLEM, Q_BLOOD_DREVANUL, Q_GOLD_DRAKE);
					}
					case 10:
					{
						return shortSecondStepTwoItems(qs, BLACKSMITH_DUNING, 1, Q_GOLD_KNIGHT, Q_GOLD_DRAKE, Q_MANAKS_GOLD_GIANT, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_UNDINE, Q_BLOOD_DREVANUL, Q_BLOOD_WEREWOLF, Q_BLOOD_SUCCUBUS, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, Q_GOLD_GIANT);
					}
					case 11:
					{
						return shortSecondStepTwoItems(qs, BLACKSMITH_DUNING, 5, Q_GOLD_KNIGHT, Q_GOLD_DRAKE, Q_MANAKS_GOLD_GIANT, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_UNDINE, Q_BLOOD_DREVANUL, Q_BLOOD_WEREWOLF, Q_BLOOD_SUCCUBUS, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, Q_GOLD_GIANT);
					}
					case 12:
					{
						return shortSecondStepTwoItems(qs, BLACKSMITH_DUNING, 10, Q_GOLD_KNIGHT, Q_GOLD_DRAKE, Q_MANAKS_GOLD_GIANT, Q_SILVER_GOLEM, Q_SILVER_FAIRY, Q_SILVER_UNDINE, Q_BLOOD_DREVANUL, Q_BLOOD_WEREWOLF, Q_BLOOD_SUCCUBUS, Q_GOLD_DRAKE, Q_GOLD_KNIGHT, Q_GOLD_GIANT);
					}
					case 19:
					{
						return shortThirdStep(qs, BLACKSMITH_DUNING, 1, Q_MANAKS_GOLD_GIANT, Q_SILVER_UNDINE, Q_BLOOD_SUCCUBUS, Q_GOLD_GIANT);
					}
					case 20:
					{
						return shortThirdStep(qs, BLACKSMITH_DUNING, 2, Q_MANAKS_GOLD_GIANT, Q_SILVER_UNDINE, Q_BLOOD_SUCCUBUS, Q_GOLD_GIANT);
					}
					case 21:
					{
						return shortThirdStep(qs, BLACKSMITH_DUNING, 3, Q_MANAKS_GOLD_GIANT, Q_SILVER_UNDINE, Q_BLOOD_SUCCUBUS, Q_GOLD_GIANT);
					}
				}
				break;
			}
			case MAGISTER_PAGE:
			{
				switch (eventID)
				{
					case 6:
					{
						return shortFirstSteps(qs, MAGISTER_PAGE, 1, 4, Q_BLOOD_MEDUSA, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 7:
					{
						return shortFirstSteps(qs, MAGISTER_PAGE, 2, 8, Q_BLOOD_MEDUSA, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 8:
					{
						return shortFirstSteps(qs, MAGISTER_PAGE, 3, 9, Q_BLOOD_MEDUSA, 0, 2, Q_BLOOD_MEDUSA, Q_SILVER_UNICORN, Q_GOLD_WYVERN);
					}
					case 10:
					{
						return shortSecondStepOneItem(qs, MAGISTER_PAGE, 1, Q_BLOOD_MEDUSA, 2, Q_NIAS_BLOOD_MEDUSA, Q_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_UNICORN, Q_SILVER_FAIRY, Q_GOLD_WYVERN, Q_GOLD_KNIGHT);
					}
					case 11:
					{
						return shortSecondStepOneItem(qs, MAGISTER_PAGE, 5, Q_BLOOD_MEDUSA, 2, Q_NIAS_BLOOD_MEDUSA, Q_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_UNICORN, Q_SILVER_FAIRY, Q_GOLD_WYVERN, Q_GOLD_KNIGHT);
					}
					case 12:
					{
						return shortSecondStepOneItem(qs, MAGISTER_PAGE, 10, Q_BLOOD_MEDUSA, 2, Q_NIAS_BLOOD_MEDUSA, Q_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_UNICORN, Q_SILVER_FAIRY, Q_GOLD_WYVERN, Q_GOLD_KNIGHT);
					}
					case 19:
					{
						return shortThirdStep(qs, MAGISTER_PAGE, 1, Q_NIAS_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_FAIRY, Q_GOLD_KNIGHT);
					}
					case 20:
					{
						return shortThirdStep(qs, MAGISTER_PAGE, 2, Q_NIAS_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_FAIRY, Q_GOLD_KNIGHT);
					}
					case 21:
					{
						return shortThirdStep(qs, MAGISTER_PAGE, 3, Q_NIAS_BLOOD_MEDUSA, Q_BLOOD_WEREWOLF, Q_SILVER_FAIRY, Q_GOLD_KNIGHT);
					}
				}
				break;
			}
			case HEAD_BLACKSMITH_FERRIS:
			{
				switch (Integer.parseInt(event))
				{
					case 6:
					{
						return shortFirstSteps(qs, HEAD_BLACKSMITH_FERRIS, 1, 4, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, 1, Q_GOLD_GIANT, Q_SILVER_DRYAD, Q_BLOOD_BASILISK);
					}
					case 7:
					{
						return shortFirstSteps(qs, HEAD_BLACKSMITH_FERRIS, 2, 8, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, 1, Q_GOLD_GIANT, Q_SILVER_DRYAD, Q_BLOOD_BASILISK);
					}
					case 8:
					{
						return shortFirstSteps(qs, HEAD_BLACKSMITH_FERRIS, 3, 9, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, 1, Q_GOLD_GIANT, Q_SILVER_DRYAD, Q_BLOOD_BASILISK);
					}
					case 10:
					{
						return shortSecondStepTwoItems(qs, HEAD_BLACKSMITH_FERRIS, 1, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BERETHS_BLOOD_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 11:
					{
						return shortSecondStepTwoItems(qs, HEAD_BLACKSMITH_FERRIS, 5, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BERETHS_BLOOD_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 12:
					{
						return shortSecondStepTwoItems(qs, HEAD_BLACKSMITH_FERRIS, 10, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BERETHS_BLOOD_DRAGON, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_GOLD_DRAGON, Q_SILVER_DRYAD, Q_SILVER_UNDINE, Q_SILVER_DRAGON, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_BLOOD_DRAGON);
					}
					case 19:
					{
						return shortThirdStep(qs, HEAD_BLACKSMITH_FERRIS, 1, Q_BERETHS_BLOOD_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_BLOOD_DRAGON);
					}
					case 20:
					{
						return shortThirdStep(qs, HEAD_BLACKSMITH_FERRIS, 2, Q_BERETHS_BLOOD_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_BLOOD_DRAGON);
					}
					case 21:
					{
						return shortThirdStep(qs, HEAD_BLACKSMITH_FERRIS, 3, Q_BERETHS_BLOOD_DRAGON, Q_GOLD_DRAGON, Q_SILVER_DRAGON, Q_BLOOD_DRAGON);
					}
				}
				break;
			}
			case UNION_PRESIDENT_BERNARD:
			{
				switch (eventID)
				{
					case 1:
					{
						return "30702-02.html";
					}
					case 2:
					{
						qs.setMemoState(2);
						qs.setCond(2);
						qs.showQuestionMark(336);
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						return "30702-03.html";
					}
					case 3:
					{
						qs.setMemoState(2);
						qs.setCond(2);
						qs.showQuestionMark(336);
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						return "30702-04.html";
					}
					case 4:
					{
						qs.setCond(7);
						qs.showQuestionMark(336);
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						return "30702-06.html";
					}
				}
				break;
			}
			case WAREHOUSE_KEEPER_SORINT:
			{
				switch (eventID)
				{
					case 1:
					{
						return "30232-03.html";
					}
					case 2:
					{
						return "30232-04.html";
					}
					case 3:
					{
						return "30232-08.html";
					}
					case 4:
					{
						return "30232-09.html";
					}
					case 5:
					{
						if (hasQuestItems(player, Q_CC_MEMBERSHIP_3))
						{
							if (hasQuestItems(player, Q_BLOOD_DREVANUL, Q_BLOOD_WEREWOLF, Q_GOLD_KNIGHT, Q_GOLD_DRAKE, Q_SILVER_FAIRY, Q_SILVER_GOLEM))
							{
								qs.setCond(9);
								qs.showQuestionMark(336);
								playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
								takeItems(player, Q_CC_MEMBERSHIP_3, -1);
								takeItems(player, Q_BLOOD_DREVANUL, 1);
								takeItems(player, Q_BLOOD_WEREWOLF, 1);
								takeItems(player, Q_GOLD_KNIGHT, 1);
								takeItems(player, Q_GOLD_DRAKE, 1);
								takeItems(player, Q_SILVER_FAIRY, 1);
								takeItems(player, Q_SILVER_GOLEM, 1);
								giveItems(player, Q_CC_MEMBERSHIP_2, 1);
								return "30232-16.html";
							}
							qs.setCond(8);
							qs.showQuestionMark(336);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-13.html";
						}
						if (hasQuestItems(player, Q_CC_MEMBERSHIP_2))
						{
							if (hasQuestItems(player, Q_BLOOD_BASILISK, Q_BLOOD_SUCCUBUS, Q_GOLD_GIANT, Q_GOLD_WYRM, Q_SILVER_UNDINE, Q_SILVER_DRYAD))
							{
								qs.setCond(11);
								qs.showQuestionMark(336);
								playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
								takeItems(player, Q_CC_MEMBERSHIP_2, -1);
								takeItems(player, Q_BLOOD_BASILISK, 1);
								takeItems(player, Q_BLOOD_SUCCUBUS, 1);
								takeItems(player, Q_GOLD_GIANT, 1);
								takeItems(player, Q_GOLD_WYRM, 1);
								takeItems(player, Q_SILVER_UNDINE, 1);
								takeItems(player, Q_SILVER_DRYAD, 1);
								giveItems(player, Q_CC_MEMBERSHIP_1, 1);
								return "30232-17.html";
							}
							qs.setCond(10);
							qs.showQuestionMark(336);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-14.html";
						}
						if (hasQuestItems(player, Q_CC_MEMBERSHIP_1))
						{
							return "30232-15.html";
						}
						break;
					}
					case 6:
					{
						return "30232-18.html";
					}
					case 7:
					{
						return "30232-19.html";
					}
					case 8:
					{
						return "30232-20.html";
					}
					case 9:
					{
						return "30232-21.html";
					}
					case 10:
					{
						qs.setCond(6);
						qs.showQuestionMark(336);
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						return "30232-22.html";
					}
					case 11:
					{
						qs.setCond(5);
						qs.showQuestionMark(336);
						playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
						return "30232-23.html";
					}
					case 20:
					{
						if (hasQuestItems(player, Q_BERETHS_BLOOD_DRAGON) && hasQuestItems(player, Q_SILVER_DRAGON) && (getQuestItemsCount(player, Q_GOLD_WYRM) >= 13))
						{
							takeItems(player, Q_BERETHS_BLOOD_DRAGON, 1);
							takeItems(player, Q_SILVER_DRAGON, 1);
							takeItems(player, Q_GOLD_WYRM, 13);
							giveItems(player, DEMON_STAFF, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24a.html";
						}
						return "30232-24.html";
					}
					case 21:
					{
						if (hasQuestItems(player, Q_BERETHS_GOLD_DRAGON) && hasQuestItems(player, Q_BLOOD_DRAGON) && hasQuestItems(player, Q_SILVER_DRYAD) && hasQuestItems(player, Q_GOLD_GIANT))
						{
							takeItems(player, Q_BERETHS_GOLD_DRAGON, 1);
							takeItems(player, Q_BLOOD_DRAGON, 1);
							takeItems(player, Q_SILVER_DRYAD, 1);
							takeItems(player, Q_GOLD_GIANT, 1);
							giveItems(player, DARK_SCREAMER, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24b.html";
						}
						return "30232-24.html";
					}
					case 22:
					{
						if (hasQuestItems(player, Q_BERETHS_SILVER_DRAGON) && hasQuestItems(player, Q_GOLD_DRAGON) && hasQuestItems(player, Q_BLOOD_SUCCUBUS) && (getQuestItemsCount(player, Q_BLOOD_BASILISK) >= 2))
						{
							takeItems(player, Q_BERETHS_SILVER_DRAGON, 1);
							takeItems(player, Q_GOLD_DRAGON, 1);
							takeItems(player, Q_BLOOD_SUCCUBUS, 1);
							takeItems(player, Q_BLOOD_BASILISK, 2);
							giveItems(player, WIDOW_MAKER, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24c.html";
						}
						return "30232-24.html";
					}
					case 23:
					{
						if (hasQuestItems(player, Q_GOLD_DRAGON) && hasQuestItems(player, Q_SILVER_DRAGON) && hasQuestItems(player, Q_BLOOD_DRAGON) && hasQuestItems(player, Q_SILVER_UNDINE))
						{
							takeItems(player, Q_GOLD_DRAGON, 1);
							takeItems(player, Q_SILVER_DRAGON, 1);
							takeItems(player, Q_BLOOD_DRAGON, 1);
							takeItems(player, Q_SILVER_UNDINE, 1);
							giveItems(player, SWORD_OF_LIMIT, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24d.html";
						}
						return "30232-24.html";
					}
					case 24:
					{
						if (hasQuestItems(player, Q_MANAKS_GOLD_GIANT))
						{
							takeItems(player, Q_MANAKS_GOLD_GIANT, 1);
							giveItems(player, DEMONS_BOOTS, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24e.html";
						}
						return "30232-24.html";
					}
					case 25:
					{
						if (hasQuestItems(player, Q_MANAKS_SILVER_DRYAD) && hasQuestItems(player, Q_SILVER_DRYAD))
						{
							takeItems(player, Q_MANAKS_SILVER_DRYAD, 1);
							takeItems(player, Q_SILVER_DRYAD, 1);
							giveItems(player, DEMONS_HOSE, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24f.html";
						}
						return "30232-24.html";
					}
					case 26:
					{
						if (hasQuestItems(player, Q_MANAKS_GOLD_GIANT))
						{
							takeItems(player, Q_MANAKS_GOLD_GIANT, 1);
							giveItems(player, DEMONS_GLOVES, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24g.html";
						}
						return "30232-24.html";
					}
					case 27:
					{
						if (hasQuestItems(player, Q_MANAKS_BLOOD_WEREWOLF) && hasQuestItems(player, Q_GOLD_GIANT) && hasQuestItems(player, Q_GOLD_WYRM))
						{
							takeItems(player, Q_MANAKS_BLOOD_WEREWOLF, 1);
							takeItems(player, Q_GOLD_GIANT, 1);
							takeItems(player, Q_GOLD_WYRM, 1);
							giveItems(player, FULL_PLATE_HELMET, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24h.html";
						}
						return "30232-24.html";
					}
					case 28:
					{
						if ((getQuestItemsCount(player, Q_NIAS_BLOOD_MEDUSA) >= 2) && (getQuestItemsCount(player, Q_GOLD_DRAKE) >= 2) && (getQuestItemsCount(player, Q_BLOOD_DREVANUL) >= 2) && (getQuestItemsCount(player, Q_GOLD_KNIGHT) >= 3))
						{
							takeItems(player, Q_NIAS_BLOOD_MEDUSA, 2);
							takeItems(player, Q_GOLD_DRAKE, 2);
							takeItems(player, Q_BLOOD_DREVANUL, 2);
							takeItems(player, Q_GOLD_KNIGHT, 3);
							giveItems(player, MOONSTONE_EARING, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24i.html";
						}
						return "30232-24.html";
					}
					case 29:
					{
						if ((getQuestItemsCount(player, Q_NIAS_BLOOD_MEDUSA) >= 7) && (getQuestItemsCount(player, Q_GOLD_KNIGHT) >= 5) && (getQuestItemsCount(player, Q_BLOOD_DREVANUL) >= 5) && (getQuestItemsCount(player, Q_SILVER_GOLEM) >= 5))
						{
							takeItems(player, Q_NIAS_BLOOD_MEDUSA, 7);
							takeItems(player, Q_GOLD_KNIGHT, 5);
							takeItems(player, Q_BLOOD_DREVANUL, 5);
							takeItems(player, Q_SILVER_GOLEM, 5);
							giveItems(player, NASSENS_EARING, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24j.html";
						}
						return "30232-24.html";
					}
					case 30:
					{
						if ((getQuestItemsCount(player, Q_NIAS_GOLD_WYVERN) >= 5) && (getQuestItemsCount(player, Q_SILVER_GOLEM) >= 4) && (getQuestItemsCount(player, Q_GOLD_DRAKE) >= 4) && (getQuestItemsCount(player, Q_BLOOD_DREVANUL) >= 4))
						{
							takeItems(player, Q_NIAS_GOLD_WYVERN, 5);
							takeItems(player, Q_SILVER_GOLEM, 4);
							takeItems(player, Q_GOLD_DRAKE, 4);
							takeItems(player, Q_BLOOD_DREVANUL, 4);
							giveItems(player, RING_OF_BINDING, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24k.html";
						}
						return "30232-24.html";
					}
					case 31:
					{
						if ((getQuestItemsCount(player, Q_NIAS_SILVER_FAIRY) >= 5) && (getQuestItemsCount(player, Q_SILVER_FAIRY) >= 3) && (getQuestItemsCount(player, Q_GOLD_KNIGHT) >= 3) && (getQuestItemsCount(player, Q_BLOOD_DREVANUL) >= 3))
						{
							takeItems(player, Q_NIAS_SILVER_FAIRY, 5);
							takeItems(player, Q_SILVER_FAIRY, 3);
							takeItems(player, Q_GOLD_KNIGHT, 3);
							takeItems(player, Q_BLOOD_DREVANUL, 3);
							giveItems(player, NECKLACE_OF_PROTECTION, 1);
							playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							return "30232-24l.html";
						}
						return "30232-24.html";
					}
					case 100:
					{
						takeItems(player, Q_CC_MEMBERSHIP_1, -1);
						takeItems(player, Q_CC_MEMBERSHIP_2, -1);
						takeItems(player, Q_CC_MEMBERSHIP_3, -1);
						playSound(player, QuestSound.ITEMSOUND_QUEST_FINISH);
						qs.exitQuest(true);
						return "30232-18a.html";
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
		switch (npc.getId())
		{
			case HARIT_LIZARDMAN_SHAMAN:
			case HARIT_LIZARDM_MATRIARCH:
			{
				final QuestState qs = getRandomPlayerFromPartyCoin(killer, npc, 2);
				if ((qs != null) && (getRandom(1000) < 63))
				{
					giveItemRandomly(qs.getPlayer(), npc, Q_KALDIS_GOLD_DRAGON, 1, 0, 1, true);
					qs.setCond(3);
					qs.showQuestionMark(336);
				}
				return super.onKill(npc, killer, isSummon);
			}
		}
		final QuestState qs = getRandomPlayerFromParty(killer, npc, 3);
		if (qs != null)
		{
			switch (npc.getId())
			{
				case SHACKLE:
				case SHACKLE_HOLD:
				{
					if (getRandom(1000) < 70)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_GOLD_WYVERN, 1, 0, 1, true);
					}
					break;
				}
				case HEADLESS_KNIGHT:
				case TIMAK_ORC:
				{
					if (getRandom(1000) < 80)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_GOLD_WYVERN, 1, 0, 1, true);
					}
					break;
				}
				case HEADLESS_KNIGHT_HOLD:
				{
					if (getRandom(1000) < 85)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_GOLD_WYVERN, 1, 0, 1, true);
					}
					break;
				}
				case ROYAL_CAVE_SERVANT:
				case MALRUK_SUCCUBUS_TUREN:
				case ROYAL_CAVE_SERVANT_HOLD:
				case KUKABURO_B:
				case ANTELOPE:
				case ANTELOPE_A:
				case ANTELOPE_B:
				case H_MALRUK_SUCCUBUS_TUREN:
				{
					if (getRandom(1000) < 100)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_GOLD_WYVERN, 1, 0, 1, true);
					}
					break;
				}
				case BUFFALO:
				case BUFFALO_A:
				case BUFFALO_B:
				case KUKABURO:
				case KUKABURO_A:
				{
					if (getRandom(1000) < 110)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_GOLD_WYVERN, 1, 0, 1, true);
					}
					break;
				}
				case DOOM_SERVANT:
				{
					if (getRandom(1000) < 140)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_GOLD_WYVERN, 1, 0, 1, true);
					}
					break;
				}
				case DOOM_KNIGHT:
				{
					if (getRandom(1000) < 210)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_GOLD_WYVERN, 1, 0, 1, true);
					}
					break;
				}
				case VANOR_SILENOS_SHAMAN:
				{
					if (getRandom(1000) < 70)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case BLOODY_GHOST:
				case TARLK_BUGBEAR_BOSS:
				case OEL_MAHUM:
				{
					if (getRandom(1000) < 80)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case OEL_MAHUM_WARRIOR:
				{
					if (getRandom(1000) < 90)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case HUNGRY_CORPSE:
				{
					if (getRandom(1000) < 100)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case BYFOOT:
				{
					if (getRandom(1000) < 110)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case BYFOOT_SIGEL:
				{
					if (getRandom(1000) < 120)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case DARK_GUARD:
				case BRILLIANT_CLAW:
				case BRILLIANT_CLAW_1:
				{
					if (getRandom(1000) < 150)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case OEL_MAHUM_WITCH_DOCTOR:
				{
					if (getRandom(1000) < 200)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case BRILLIANT_ANGUISH:
				case BRILLIANT_ANGUISH_1:
				{
					if (getRandom(1000) < 210)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_SILVER_UNICORN, 1, 0, 1, true);
					}
					break;
				}
				case LAKIN:
				{
					if (getRandom(1000) < 60)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
				case HATAR_HANISHEE:
				{
					if (getRandom(1000) < 70)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
				case PUNISHMENT_OF_UNDEAD:
				{
					if (getRandom(1000) < 80)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
				case FLOAT_OF_GRAVE:
				case BANDERSNATCH_A:
				case BANDERSNATCH_B:
				{
					if (getRandom(1000) < 90)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
				case BANDERSNATCH:
				{
					if (getRandom(1000) < 100)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
				case NIHIL_INVADER:
				{
					if (getRandom(1000) < 110)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
				case TIMAK_ORC_SHAMAN:
				{
					if (getRandom(1000) < 130)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
				case TIMAK_ORC_ARCHER:
				case TIMAK_ORC_SOLDIER:
				{
					if (getRandom(1000) < 140)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
				case DOOM_ARCHER:
				case BRILLIANT_WISDOM:
				case BRILLIANT_VENGEANCE:
				case BRILLIANT_VENGEANCE_1:
				{
					if (getRandom(1000) < 160)
					{
						giveItemRandomly(qs.getPlayer(), npc, Q_BLOOD_MEDUSA, 1, 0, 1, true);
					}
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	/**
	 * @param qs
	 */
	private void resetParams(QuestState qs)
	{
		qs.set(WEIGHT_POINT, 0);
		qs.set(PARAM_1, 0);
		qs.set(PARAM_2, 0);
		qs.set(PARAM_3, 0);
		qs.set(FLAG, 0);
	}
	
	/**
	 * @param qs
	 * @param npcId
	 * @param weightPoint
	 * @param base
	 * @param ITEM_1_1
	 * @param ITEM_1_2
	 * @param ITEM_1_MUL
	 * @param ITEM_2
	 * @param ITEM_3
	 * @param ITEM_4
	 * @return
	 */
	private String shortFirstSteps(QuestState qs, int npcId, int weightPoint, int base, int ITEM_1_1, int ITEM_1_2, int ITEM_1_MUL, int ITEM_2, int ITEM_3, int ITEM_4)
	{
		final L2PcInstance player = qs.getPlayer();
		switch (qs.getInt(PARAM_2))
		{
			case 42:
			{
				if ((getQuestItemsCount(player, ITEM_1_1) >= (base * ITEM_1_MUL)) && ((ITEM_1_2 == 0) || (getQuestItemsCount(player, ITEM_1_2) >= base)))
				{
					qs.set(FLAG, 1);
					takeItems(player, ITEM_1_1, base * ITEM_1_MUL);
					if (ITEM_1_2 > 0)
					{
						takeItems(player, ITEM_1_2, base);
					}
					qs.set(WEIGHT_POINT, weightPoint);
					int param1 = getRandom(3) + 1;
					param1 += (getRandom(3) + 1) * 4;
					param1 += (getRandom(3) + 1) * 16;
					qs.set(PARAM_1, param1);
					return npcId + "-11.html";
				}
				break;
			}
			case 31:
			{
				if (getQuestItemsCount(player, ITEM_2) >= base)
				{
					qs.set(FLAG, 1);
					takeItems(player, ITEM_2, base);
					qs.set(WEIGHT_POINT, weightPoint);
					int param1 = getRandom(3) + 1;
					param1 += (getRandom(3) + 1) * 4;
					param1 += (getRandom(3) + 1) * 16;
					qs.set(PARAM_1, param1);
					return npcId + "-11.html";
				}
				break;
			}
			case 21:
			{
				if (getQuestItemsCount(player, ITEM_3) >= base)
				{
					qs.set(FLAG, 1);
					takeItems(player, ITEM_3, base);
					qs.set(WEIGHT_POINT, weightPoint);
					int param1 = getRandom(3) + 1;
					param1 += (getRandom(3) + 1) * 4;
					param1 += (getRandom(3) + 1) * 16;
					qs.set(PARAM_1, param1);
					return npcId + "-11.html";
				}
				break;
			}
			case 11:
			{
				if (getQuestItemsCount(player, ITEM_4) >= base)
				{
					qs.set(FLAG, 1);
					takeItems(player, ITEM_4, base);
					qs.set(WEIGHT_POINT, weightPoint);
					int param1 = getRandom(3) + 1;
					param1 += (getRandom(3) + 1) * 4;
					param1 += (getRandom(3) + 1) * 16;
					qs.set(PARAM_1, param1);
					return npcId + "-11.html";
				}
				break;
			}
		}
		return npcId + "-10.html";
	}
	
	/**
	 * @param qs
	 * @param npcId
	 * @param mul
	 * @param ITEM_1
	 * @param ITEM_1_MUL
	 * @param REWARD_1
	 * @param ITEM_2
	 * @param REWARD_2
	 * @param ITEM_3
	 * @param REWARD_3
	 * @param ITEM_4
	 * @param REWARD_4
	 * @return
	 */
	private String shortSecondStepOneItem(QuestState qs, int npcId, int mul, int ITEM_1, int ITEM_1_MUL, int REWARD_1, int ITEM_2, int REWARD_2, int ITEM_3, int REWARD_3, int ITEM_4, int REWARD_4)
	{
		final L2PcInstance player = qs.getPlayer();
		switch (qs.getInt(PARAM_2))
		{
			case 42:
			{
				if (getQuestItemsCount(player, ITEM_1) >= (10 * mul * ITEM_1_MUL))
				{
					takeItems(player, ITEM_1, 10 * mul * ITEM_1_MUL);
					giveItems(player, REWARD_1, 1 * mul);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					return npcId + "-07.html";
				}
				break;
			}
			case 31:
			{
				if (getQuestItemsCount(player, ITEM_2) >= (5 * mul))
				{
					takeItems(player, ITEM_2, 5 * mul);
					giveItems(player, REWARD_2, 1 * mul);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					return npcId + "-07.html";
				}
				break;
			}
			case 21:
			{
				if (getQuestItemsCount(player, ITEM_3) >= (5 * mul))
				{
					takeItems(player, ITEM_3, 5 * mul);
					giveItems(player, REWARD_3, 1 * mul);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					return npcId + "-07.html";
				}
				break;
			}
			case 11:
			{
				if (getQuestItemsCount(player, ITEM_4) >= (5 * mul))
				{
					takeItems(player, ITEM_4, 5 * mul);
					giveItems(player, REWARD_4, 1 * mul);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					return npcId + "-07.html";
				}
				break;
			}
		}
		return npcId + "-10.html";
	}
	
	/**
	 * @param qs
	 * @param npcId
	 * @param mul
	 * @param ITEM_1_1
	 * @param ITEM_1_2
	 * @param REWARD_1
	 * @param ITEM_2_1
	 * @param ITEM_2_2
	 * @param REWARD_2
	 * @param ITEM_3_1
	 * @param ITEM_3_2
	 * @param REWARD_3
	 * @param ITEM_4_1
	 * @param ITEM_4_2
	 * @param REWARD_4
	 * @return
	 */
	private String shortSecondStepTwoItems(QuestState qs, int npcId, int mul, int ITEM_1_1, int ITEM_1_2, int REWARD_1, int ITEM_2_1, int ITEM_2_2, int REWARD_2, int ITEM_3_1, int ITEM_3_2, int REWARD_3, int ITEM_4_1, int ITEM_4_2, int REWARD_4)
	{
		final L2PcInstance player = qs.getPlayer();
		switch (qs.getInt(PARAM_2))
		{
			case 42:
			{
				if ((getQuestItemsCount(player, ITEM_1_1) >= (10 * mul)) && (getQuestItemsCount(player, ITEM_1_2) >= (10 * mul)))
				{
					takeItems(player, ITEM_1_1, 10 * mul);
					takeItems(player, ITEM_1_2, 10 * mul);
					giveItems(player, REWARD_1, 1 * mul);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					return npcId + "-07.html";
				}
				break;
			}
			case 31:
			{
				if ((getQuestItemsCount(player, ITEM_2_1) >= (5 * mul)) && (getQuestItemsCount(player, ITEM_2_2) >= (5 * mul)))
				{
					takeItems(player, ITEM_2_1, 5 * mul);
					takeItems(player, ITEM_2_2, 5 * mul);
					giveItems(player, REWARD_2, 1 * mul);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					return npcId + "-07.html";
				}
				break;
			}
			case 21:
			{
				if ((getQuestItemsCount(player, ITEM_3_1) >= (5 * mul)) && (getQuestItemsCount(player, ITEM_3_2) >= (5 * mul)))
				{
					takeItems(player, ITEM_3_1, 5 * mul);
					takeItems(player, ITEM_3_2, 5 * mul);
					giveItems(player, REWARD_3, 1 * mul);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					return npcId + "-07.html";
				}
				break;
			}
			case 11:
			{
				if ((getQuestItemsCount(player, ITEM_4_1) >= (5 * mul)) && (getQuestItemsCount(player, ITEM_4_2) >= (5 * mul)))
				{
					takeItems(player, ITEM_4_1, 5 * mul);
					takeItems(player, ITEM_4_2, 5 * mul);
					giveItems(player, REWARD_4, 1 * mul);
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
					return npcId + "-07.html";
				}
				break;
			}
		}
		return npcId + "-10.html";
	}
	
	/**
	 * @param qs
	 * @param npcId
	 * @param flag
	 * @param ITEM_1
	 * @param ITEM_2
	 * @param ITEM_3
	 * @param ITEM_4
	 * @return
	 */
	private String shortThirdStep(QuestState qs, int npcId, int flag, int ITEM_1, int ITEM_2, int ITEM_3, int ITEM_4)
	{
		final L2PcInstance player = qs.getPlayer();
		qs.set(PARAM_3, 0);
		qs.set(FLAG, qs.getInt(FLAG) + flag);
		if ((qs.getInt(PARAM_1) == qs.getInt(FLAG)) && (qs.getInt(WEIGHT_POINT) >= 0))
		{
			qs.set(WEIGHT_POINT, 0);
			switch (qs.getInt(PARAM_2))
			{
				case 42:
				{
					giveItems(player, ITEM_1, 1);
					break;
				}
				case 31:
				{
					giveItems(player, ITEM_2, 1);
					break;
				}
				case 21:
				{
					giveItems(player, ITEM_3, 1);
					break;
				}
				case 11:
				{
					giveItems(player, ITEM_4, 1);
					break;
				}
			}
			qs.set(PARAM_1, 0);
			return npcId + "-20.html";
		}
		if (qs.getInt(WEIGHT_POINT) == 0)
		{
			switch (qs.getInt(PARAM_1))
			{
				case 21:
				{
					return npcId + "-23.html";
				}
				case 25:
				{
					return npcId + "-24.html";
				}
				case 37:
				{
					return npcId + "-25.html";
				}
				case 41:
				{
					return npcId + "-26.html";
				}
				case 61:
				{
					return npcId + "-27.html";
				}
				case 29:
				{
					return npcId + "-28.html";
				}
				case 45:
				{
					return npcId + "-29.html";
				}
				case 53:
				{
					return npcId + "-30.html";
				}
				case 57:
				{
					return npcId + "-31.html";
				}
				case 22:
				{
					return npcId + "-32.html";
				}
				case 26:
				{
					return npcId + "-33.html";
				}
				case 38:
				{
					return npcId + "-34.html";
				}
				case 42:
				{
					return npcId + "-35.html";
				}
				case 62:
				{
					return npcId + "-36.html";
				}
				case 30:
				{
					return npcId + "-37.html";
				}
				case 46:
				{
					return npcId + "-38.html";
				}
				case 54:
				{
					return npcId + "-39.html";
				}
				case 58:
				{
					return npcId + "-40.html";
				}
				case 23:
				{
					return npcId + "-41.html";
				}
				case 27:
				{
					return npcId + "-42.html";
				}
				case 39:
				{
					return npcId + "-43.html";
				}
				case 43:
				{
					return npcId + "-44.html";
				}
				case 63:
				{
					return npcId + "-45.html";
				}
				case 31:
				{
					return npcId + "-46.html";
				}
				case 47:
				{
					return npcId + "-47.html";
				}
				case 55:
				{
					return npcId + "-48.html";
				}
				case 59:
				{
					return npcId + "-49.html";
				}
			}
			qs.set(PARAM_1, 0);
		}
		else
		{
			final int i0 = qs.getInt(PARAM_1) % 4;
			int i1 = qs.getInt(PARAM_1) / 4;
			final int i2 = i1 / 4;
			i1 = i1 % 4;
			
			final int i3 = qs.getInt(FLAG) % 4;
			int i4 = qs.getInt(FLAG) / 4;
			final int i5 = i4 / 4;
			i4 = i4 % 4;
			
			if (i0 == i3)
			{
				qs.set(PARAM_3, qs.getInt(PARAM_3) + 1);
			}
			if (i1 == i4)
			{
				qs.set(PARAM_3, qs.getInt(PARAM_3) + 1);
			}
			if (i2 == i5)
			{
				qs.set(PARAM_3, qs.getInt(PARAM_3) + 1);
			}
			qs.set(FLAG, 1);
			qs.set(WEIGHT_POINT, qs.getInt(WEIGHT_POINT) - 1);
			switch (qs.getInt(PARAM_3))
			{
				case 0:
				{
					return npcId + "-52.html";
				}
				case 1:
				{
					return npcId + "-50.html";
				}
				case 2:
				{
					return npcId + "-51.html";
				}
			}
		}
		return null;
	}
	
	private QuestState getRandomPlayerFromParty(L2PcInstance player, L2Npc npc, int memoState)
	{
		final QuestState qs = getQuestState(player, false);
		final List<QuestState> candidates = new ArrayList<>();
		
		if ((qs != null) && qs.isStarted() && (qs.getMemoState() == memoState))
		{
			candidates.add(qs);
			candidates.add(qs);
		}
		
		if (player.isInParty())
		{
			player.getParty().getMembers().stream().forEach(pm ->
			{
				final QuestState qss = getQuestState(pm, false);
				if ((qss != null) && qss.isStarted() && (qss.getMemoState() == memoState) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, pm, true))
				{
					candidates.add(qss);
				}
			});
		}
		return candidates.isEmpty() ? null : candidates.get(getRandom(candidates.size()));
	}
	
	private QuestState getRandomPlayerFromPartyCoin(L2PcInstance player, L2Npc npc, int memoState)
	{
		final QuestState qs = getQuestState(player, false);
		final List<QuestState> candidates = new ArrayList<>();
		if ((qs != null) && qs.isStarted() && (qs.getMemoState() == memoState) && !hasQuestItems(player, Q_KALDIS_GOLD_DRAGON))
		{
			candidates.add(qs);
			candidates.add(qs);
		}
		
		if (player.isInParty())
		{
			player.getParty().getMembers().stream().forEach(pm ->
			{
				final QuestState qss = getQuestState(pm, false);
				if ((qss != null) && qss.isStarted() && (qss.getMemoState() == memoState) && !hasQuestItems(player, Q_KALDIS_GOLD_DRAGON) && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, pm, true))
				{
					candidates.add(qss);
				}
			});
		}
		return candidates.isEmpty() ? null : candidates.get(getRandom(candidates.size()));
	}
}

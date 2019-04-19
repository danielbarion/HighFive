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
package quests.Q00337_AudienceWithTheLandDragon;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Audience With The Land Dragon (337)
 * @author ivantotov
 */
public final class Q00337_AudienceWithTheLandDragon extends Quest
{
	// NPCs
	private static final int WAREHOUSE_CHIEF_MOKE = 30498;
	private static final int BLACKSMITH_HELTON = 30678;
	private static final int PREFECT_CHAKIRIS = 30705;
	private static final int MAGISTER_KAIENA = 30720;
	private static final int GABRIELLE = 30753;
	private static final int ANTHARAS_WATCHMAN_GILMORE = 30754;
	private static final int ANTHARAS_WATCHMAN_THEODRIC = 30755;
	private static final int MASTER_KENDRA = 30851;
	private static final int HIGH_PRIEST_ORVEN = 30857;
	// Items
	private static final int FEATHER_OF_GABRIELLE = 3852;
	private static final int MARSH_STALKER_HORN = 3853;
	private static final int MARSH_DRAKE_TALONS = 3854;
	private static final int KRANROT_SKIN = 3855;
	private static final int HAMRUT_LEG = 3856;
	private static final int REMAINS_OF_SACRAFICE = 3857;
	private static final int TOTEM_OF_LAND_DRAGON = 3858;
	private static final int FRAGMENT_OF_ABYSS_JEWEL_1ST = 3859;
	private static final int FRAGMENT_OF_ABYSS_JEWEL_2ND = 3860;
	private static final int FRAGMENT_OF_ABYSS_JEWEL_3RD = 3861;
	private static final int MARA_FANG = 3862;
	private static final int MUSFEL_FANG = 3863;
	private static final int MARK_OF_WATCHMAN = 3864;
	private static final int HERALD_OF_SLAYER = 3890;
	// Reward
	private static final int PORTAL_STONE = 3865;
	// Monster
	private static final int BLOOD_QUEEN = 18001;
	private static final int CAVE_MAIDEN = 20134;
	private static final int CAVE_KEEPER = 20246;
	private static final int CAVE_KEEPER_HOLD = 20277;
	private static final int CAVE_MAIDEN_HOLD = 20287;
	private static final int HARIT_LIZARDMAN_SHAMAN = 20644;
	private static final int HARIT_LIZARDMAN_MATRIARCH = 20645;
	private static final int HAMRUT = 20649;
	private static final int KRANROT = 20650;
	private static final int MARSH_STALKER = 20679;
	private static final int MARSH_DRAKE = 20680;
	// Quest Monster
	private static final int ABYSSAL_JEWEL_1 = 27165;
	private static final int ABYSSAL_JEWEL_2 = 27166;
	private static final int ABYSSAL_JEWEL_3 = 27167;
	private static final int JEWEL_GUARDIAN_MARA = 27168;
	private static final int JEWEL_GUARDIAN_MUSFEL = 27169;
	private static final int JEWEL_GUARDIAN_PYTON = 27170;
	private static final int GHOST_OF_OFFERING = 27171;
	private static final int HARIT_LIZARDMAN_ZEALOT = 27172;
	// Misc
	private static final int MIN_LEVEL = 50;
	
	public Q00337_AudienceWithTheLandDragon()
	{
		super(337);
		addStartNpc(GABRIELLE);
		addTalkId(GABRIELLE, WAREHOUSE_CHIEF_MOKE, BLACKSMITH_HELTON, PREFECT_CHAKIRIS, MAGISTER_KAIENA, ANTHARAS_WATCHMAN_GILMORE, ANTHARAS_WATCHMAN_THEODRIC, MASTER_KENDRA, HIGH_PRIEST_ORVEN);
		addKillId(BLOOD_QUEEN, CAVE_MAIDEN, CAVE_KEEPER, CAVE_KEEPER_HOLD, CAVE_MAIDEN_HOLD, HARIT_LIZARDMAN_SHAMAN, HARIT_LIZARDMAN_MATRIARCH, HAMRUT, KRANROT, MARSH_STALKER, MARSH_DRAKE, ABYSSAL_JEWEL_1, ABYSSAL_JEWEL_2, ABYSSAL_JEWEL_3, JEWEL_GUARDIAN_MARA, JEWEL_GUARDIAN_MUSFEL, JEWEL_GUARDIAN_PYTON, GHOST_OF_OFFERING, HARIT_LIZARDMAN_ZEALOT);
		addAttackId(ABYSSAL_JEWEL_1, ABYSSAL_JEWEL_2, ABYSSAL_JEWEL_3);
		registerQuestItems(FEATHER_OF_GABRIELLE, MARSH_STALKER_HORN, MARSH_DRAKE_TALONS, KRANROT_SKIN, HAMRUT_LEG, REMAINS_OF_SACRAFICE, TOTEM_OF_LAND_DRAGON, FRAGMENT_OF_ABYSS_JEWEL_1ST, FRAGMENT_OF_ABYSS_JEWEL_2ND, FRAGMENT_OF_ABYSS_JEWEL_3RD, MARA_FANG, MUSFEL_FANG, MARK_OF_WATCHMAN, HERALD_OF_SLAYER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ("DESPAWN".equals(event))
		{
			npc.deleteMe();
			return super.onAdvEvent(event, npc, player);
		}
		else if ("DESPAWN_240".equals(event))
		{
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
			case "30753-05.htm":
			{
				if (qs.isCreated())
				{
					giveItems(player, FEATHER_OF_GABRIELLE, 1);
					qs.startQuest();
					qs.setMemoState(20000);
					htmltext = event;
				}
				break;
			}
			case "30753-09.html":
			{
				takeItems(player, MARK_OF_WATCHMAN, -1);
				qs.setMemoState(40000);
				qs.setCond(2, true);
				htmltext = event;
				break;
			}
			case "30754-03.html":
			{
				qs.setMemoState(70000);
				qs.setCond(4, true);
				htmltext = event;
				break;
			}
			case "30755-05.html":
			{
				if (qs.isMemoState(70000) && hasQuestItems(player, FRAGMENT_OF_ABYSS_JEWEL_3RD))
				{
					giveItems(player, PORTAL_STONE, 1);
					qs.exitQuest(true, true);
					htmltext = event;
				}
				break;
			}
			case "30498-02.html":
			case "30678-01a.html":
			case "30753-01a.html":
			case "30753-03.htm":
			case "30753-04.htm":
			case "30753-06a.html":
			{
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		final QuestState qs = getQuestState(attacker, false);
		if ((qs != null) && qs.isStarted())
		{
			switch (npc.getId())
			{
				case ABYSSAL_JEWEL_1:
				{
					if (qs.isMemoState(40000) || (qs.isMemoState(40001)))
					{
						if ((npc.getCurrentHp() < (npc.getMaxHp() * 0.8)) && (npc.getVariables().getInt("i_quest0") == 0))
						{
							for (int i = 0; i < 20; i++)
							{
								addAttackDesire(addSpawn(JEWEL_GUARDIAN_MARA, npc, true, 180000), attacker);
							}
							npc.getVariables().set("i_quest0", 1);
							startQuestTimer("DESPAWN", 900000, npc, attacker);
						}
						
						if ((npc.getCurrentHp() < (npc.getMaxHp() * 0.4)) && !hasQuestItems(attacker, FRAGMENT_OF_ABYSS_JEWEL_1ST))
						{
							giveItems(attacker, FRAGMENT_OF_ABYSS_JEWEL_1ST, 1);
							playSound(attacker, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							startQuestTimer("DESPAWN_240", 240000, npc, attacker);
						}
					}
					
					if (npc.getCurrentHp() < (npc.getMaxHp() * 0.1))
					{
						npc.deleteMe();
					}
					break;
				}
				case ABYSSAL_JEWEL_2:
				{
					if (qs.isMemoState(40000) || (qs.isMemoState(40010)))
					{
						if ((npc.getCurrentHp() < (npc.getMaxHp() * 0.8)) && (npc.getVariables().getInt("i_quest0") == 0))
						{
							for (int i = 0; i < 20; i++)
							{
								addAttackDesire(addSpawn(JEWEL_GUARDIAN_MUSFEL, npc, true, 180000), attacker);
							}
							npc.getVariables().set("i_quest0", 1);
							startQuestTimer("DESPAWN", 900000, npc, attacker);
						}
						
						if ((npc.getCurrentHp() < (npc.getMaxHp() * 0.4)) && !hasQuestItems(attacker, FRAGMENT_OF_ABYSS_JEWEL_2ND))
						{
							giveItems(attacker, FRAGMENT_OF_ABYSS_JEWEL_2ND, 1);
							playSound(attacker, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							startQuestTimer("DESPAWN_240", 240000, npc, attacker);
						}
					}
					
					if (npc.getCurrentHp() < (npc.getMaxHp() * 0.1))
					{
						npc.deleteMe();
					}
					break;
				}
				case ABYSSAL_JEWEL_3:
				{
					if (qs.isMemoState(70000))
					{
						if ((npc.getCurrentHp() < (npc.getMaxHp() * 0.8)) && (npc.getVariables().getInt("i_quest0") == 0))
						{
							addAttackDesire(addSpawn(JEWEL_GUARDIAN_PYTON, npc, true, 180000), attacker);
							addAttackDesire(addSpawn(JEWEL_GUARDIAN_PYTON, npc, true, 180000), attacker);
							addAttackDesire(addSpawn(JEWEL_GUARDIAN_PYTON, npc, true, 180000), attacker);
							addAttackDesire(addSpawn(JEWEL_GUARDIAN_PYTON, npc, true, 180000), attacker);
							npc.getVariables().set("i_quest0", 1);
						}
						
						if ((npc.getCurrentHp() < (npc.getMaxHp() * 0.4)) && !hasQuestItems(attacker, FRAGMENT_OF_ABYSS_JEWEL_3RD))
						{
							giveItems(attacker, FRAGMENT_OF_ABYSS_JEWEL_3RD, 1);
							playSound(attacker, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					
					if (npc.getCurrentHp() < (npc.getMaxHp() * 0.1))
					{
						npc.deleteMe();
					}
					break;
				}
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isStarted() && Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			switch (npc.getId())
			{
				case BLOOD_QUEEN:
				{
					switch (qs.getMemoState())
					{
						case 21011:
						case 21010:
						case 21001:
						case 21000:
						case 20011:
						case 20010:
						case 20001:
						case 20000:
						{
							if (!hasQuestItems(killer, REMAINS_OF_SACRAFICE))
							{
								for (int i = 0; i < 8; i++)
								{
									addSpawn(GHOST_OF_OFFERING, npc, true, 180000);
								}
							}
							break;
						}
					}
					break;
				}
				case CAVE_MAIDEN:
				case CAVE_KEEPER:
				case CAVE_KEEPER_HOLD:
				case CAVE_MAIDEN_HOLD:
				{
					if (qs.isMemoState(70000) && !hasQuestItems(killer, FRAGMENT_OF_ABYSS_JEWEL_3RD))
					{
						if (getRandom(5) == 0)
						{
							addSpawn(ABYSSAL_JEWEL_3, npc, true, 180000);
						}
					}
					break;
				}
				case HARIT_LIZARDMAN_SHAMAN:
				{
					switch (qs.getMemoState())
					{
						case 21110:
						case 21100:
						case 21010:
						case 21000:
						case 20110:
						case 20100:
						case 20010:
						case 20000:
						{
							if (!hasQuestItems(killer, TOTEM_OF_LAND_DRAGON))
							{
								addAttackDesire(addSpawn(HARIT_LIZARDMAN_ZEALOT, npc, true, 180000), killer);
								addAttackDesire(addSpawn(HARIT_LIZARDMAN_ZEALOT, npc, true, 180000), killer);
								addAttackDesire(addSpawn(HARIT_LIZARDMAN_ZEALOT, npc, true, 180000), killer);
							}
							break;
						}
					}
					break;
				}
				case HARIT_LIZARDMAN_MATRIARCH:
				{
					switch (qs.getMemoState())
					{
						case 21110:
						case 21100:
						case 21010:
						case 21000:
						case 20110:
						case 20100:
						case 20010:
						case 20000:
						{
							if (!hasQuestItems(killer, TOTEM_OF_LAND_DRAGON))
							{
								if (getRandom(5) == 0)
								{
									addAttackDesire(addSpawn(HARIT_LIZARDMAN_ZEALOT, npc, true, 180000), killer);
									addAttackDesire(addSpawn(HARIT_LIZARDMAN_ZEALOT, npc, true, 180000), killer);
									addAttackDesire(addSpawn(HARIT_LIZARDMAN_ZEALOT, npc, true, 180000), killer);
								}
							}
							break;
						}
					}
					break;
				}
				case HAMRUT:
				{
					switch (qs.getMemoState())
					{
						case 21101:
						case 21100:
						case 21001:
						case 21000:
						case 20101:
						case 20100:
						case 20001:
						case 20000:
						{
							if (!hasQuestItems(killer, HAMRUT_LEG))
							{
								giveItems(killer, HAMRUT_LEG, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							break;
						}
					}
					break;
				}
				case KRANROT:
				{
					switch (qs.getMemoState())
					{
						case 21101:
						case 21100:
						case 21001:
						case 21000:
						case 20101:
						case 20100:
						case 20001:
						case 20000:
						{
							if (!hasQuestItems(killer, KRANROT_SKIN))
							{
								giveItems(killer, KRANROT_SKIN, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							break;
						}
					}
					break;
				}
				case MARSH_STALKER:
				{
					switch (qs.getMemoState())
					{
						case 20111:
						case 20110:
						case 20101:
						case 20100:
						case 20011:
						case 20010:
						case 20001:
						case 20000:
						{
							if (!hasQuestItems(killer, MARSH_STALKER_HORN))
							{
								giveItems(killer, MARSH_STALKER_HORN, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							break;
						}
					}
					break;
				}
				case MARSH_DRAKE:
				{
					switch (qs.getMemoState())
					{
						case 20111:
						case 20110:
						case 20101:
						case 20100:
						case 20011:
						case 20010:
						case 20001:
						case 20000:
						{
							if (!hasQuestItems(killer, MARSH_DRAKE_TALONS))
							{
								giveItems(killer, MARSH_DRAKE_TALONS, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							break;
						}
					}
					break;
				}
				case JEWEL_GUARDIAN_MARA:
				{
					if (qs.isMemoState(40000) || (qs.isMemoState(40001)))
					{
						if (!hasQuestItems(killer, MARA_FANG))
						{
							giveItems(killer, MARA_FANG, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case JEWEL_GUARDIAN_MUSFEL:
				{
					if (qs.isMemoState(40000) || (qs.isMemoState(40010)))
					{
						if (!hasQuestItems(killer, MUSFEL_FANG))
						{
							giveItems(killer, MUSFEL_FANG, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
					}
					break;
				}
				case GHOST_OF_OFFERING:
				{
					switch (qs.getMemoState())
					{
						case 21011:
						case 21010:
						case 21001:
						case 21000:
						case 20011:
						case 20010:
						case 20001:
						case 20000:
						{
							if (!hasQuestItems(killer, REMAINS_OF_SACRAFICE))
							{
								giveItems(killer, REMAINS_OF_SACRAFICE, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							break;
						}
					}
					break;
				}
				case HARIT_LIZARDMAN_ZEALOT:
				{
					switch (qs.getMemoState())
					{
						case 21110:
						case 21100:
						case 21010:
						case 21000:
						case 20110:
						case 20100:
						case 20010:
						case 20000:
						{
							if (!hasQuestItems(killer, TOTEM_OF_LAND_DRAGON))
							{
								giveItems(killer, TOTEM_OF_LAND_DRAGON, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							break;
						}
					}
					break;
				}
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
			if (npc.getId() == GABRIELLE)
			{
				if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "30753-01.htm";
				}
				else
				{
					htmltext = "30753-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case GABRIELLE:
				{
					if ((memoState >= 20000) && (memoState < 30000))
					{
						htmltext = "30753-06.html";
					}
					else if (memoState == 30000)
					{
						htmltext = "30753-08.html";
					}
					else if ((memoState >= 40000) && (memoState < 50000))
					{
						htmltext = "30753-10.html";
					}
					else if (memoState == 50000)
					{
						takeItems(player, FEATHER_OF_GABRIELLE, -1);
						takeItems(player, MARK_OF_WATCHMAN, -1);
						giveItems(player, HERALD_OF_SLAYER, 1);
						qs.setMemoState(60000);
						qs.setCond(3, true);
						htmltext = "30753-11.html";
					}
					else if (memoState == 60000)
					{
						htmltext = "30753-12.html";
					}
					else if (memoState == 70000)
					{
						htmltext = "30753-13.html";
					}
					break;
				}
				case WAREHOUSE_CHIEF_MOKE:
				{
					if ((memoState == 40000) || (memoState == 40001))
					{
						if (hasQuestItems(player, FRAGMENT_OF_ABYSS_JEWEL_1ST, MARA_FANG))
						{
							takeItems(player, FRAGMENT_OF_ABYSS_JEWEL_1ST, -1);
							takeItems(player, MARA_FANG, -1);
							giveItems(player, MARK_OF_WATCHMAN, 1);
							if (qs.getMemoState() == 40001)
							{
								qs.setMemoState(50000);
							}
							else
							{
								qs.setMemoState(40010);
							}
							htmltext = "30498-03.html";
						}
						else
						{
							htmltext = "30498-01.html";
						}
					}
					else if (memoState == 40010)
					{
						htmltext = "30498-04.html";
					}
					else if (memoState >= 50000)
					{
						htmltext = "30498-05.html";
					}
					break;
				}
				case BLACKSMITH_HELTON:
				{
					if ((memoState == 40000) || (memoState == 40010))
					{
						if (hasQuestItems(player, FRAGMENT_OF_ABYSS_JEWEL_2ND, MUSFEL_FANG))
						{
							takeItems(player, FRAGMENT_OF_ABYSS_JEWEL_2ND, -1);
							takeItems(player, MUSFEL_FANG, -1);
							giveItems(player, MARK_OF_WATCHMAN, 1);
							if (qs.getMemoState() == 40010)
							{
								qs.setMemoState(50000);
							}
							else
							{
								qs.setMemoState(40001);
							}
							htmltext = "30678-02.html";
						}
						else
						{
							htmltext = "30678-01.html";
						}
					}
					else if (memoState == 40001)
					{
						htmltext = "30678-03.html";
					}
					else if (memoState >= 50000)
					{
						htmltext = "30678-04.html";
					}
					break;
				}
				case PREFECT_CHAKIRIS:
				{
					switch (qs.getMemoState())
					{
						case 21101:
						case 21000:
						case 21100:
						case 21001:
						case 20101:
						case 20100:
						case 20001:
						case 20000:
						{
							if (hasQuestItems(player, KRANROT_SKIN, HAMRUT_LEG))
							{
								takeItems(player, KRANROT_SKIN, -1);
								takeItems(player, HAMRUT_LEG, -1);
								giveItems(player, MARK_OF_WATCHMAN, 1);
								if ((qs.getMemoState() + 10) == 21111)
								{
									qs.setMemoState(30000);
								}
								else
								{
									qs.setMemoState(qs.getMemoState() + 10);
								}
								htmltext = "30705-02.html";
							}
							else
							{
								htmltext = "30705-01.html";
							}
							break;
						}
						case 21110:
						case 21011:
						case 21010:
						case 20111:
						case 20110:
						case 20011:
						case 20010:
						{
							htmltext = "30705-03.html";
							break;
						}
					}
					if (memoState >= 30000)
					{
						htmltext = "30705-04.html";
					}
					break;
				}
				case MAGISTER_KAIENA:
				{
					switch (qs.getMemoState())
					{
						case 20111:
						case 20110:
						case 20101:
						case 20100:
						case 20010:
						case 20011:
						case 20001:
						case 20000:
						{
							if (hasQuestItems(player, MARSH_STALKER_HORN, MARSH_DRAKE_TALONS))
							{
								takeItems(player, MARSH_STALKER_HORN, -1);
								takeItems(player, MARSH_DRAKE_TALONS, -1);
								giveItems(player, MARK_OF_WATCHMAN, 1);
								if ((qs.getMemoState() + 1000) == 21111)
								{
									qs.setMemoState(30000);
								}
								else
								{
									qs.setMemoState(qs.getMemoState() + 1000);
								}
								htmltext = "30720-02.html";
							}
							else
							{
								htmltext = "30720-01.html";
							}
							break;
						}
						case 21110:
						case 21101:
						case 21100:
						case 21011:
						case 21010:
						case 21001:
						case 21000:
						{
							htmltext = "30720-03.html";
							break;
						}
					}
					if (memoState >= 30000)
					{
						htmltext = "30720-04.html";
					}
					break;
				}
				case ANTHARAS_WATCHMAN_GILMORE:
				{
					if (memoState < 60000)
					{
						htmltext = "30754-01.html";
					}
					else if (memoState == 60000)
					{
						htmltext = "30754-02.html";
					}
					else if (memoState == 70000)
					{
						if (hasQuestItems(player, FRAGMENT_OF_ABYSS_JEWEL_3RD))
						{
							htmltext = "30754-05.html";
						}
						else
						{
							htmltext = "30754-04.html";
						}
					}
					break;
				}
				case ANTHARAS_WATCHMAN_THEODRIC:
				{
					if (memoState < 60000)
					{
						htmltext = "30755-01.html";
					}
					else if (memoState == 60000)
					{
						htmltext = "30755-02.html";
					}
					else if (memoState == 70000)
					{
						if (!hasQuestItems(player, FRAGMENT_OF_ABYSS_JEWEL_3RD))
						{
							htmltext = "30755-03.html";
						}
						else
						{
							htmltext = "30755-04.html";
						}
					}
					break;
				}
				case MASTER_KENDRA:
				{
					switch (qs.getMemoState())
					{
						case 21110:
						case 21100:
						case 21010:
						case 21000:
						case 20110:
						case 20100:
						case 20010:
						case 20000:
						{
							if (!hasQuestItems(player, TOTEM_OF_LAND_DRAGON))
							{
								htmltext = "30851-01.html";
							}
							else
							{
								takeItems(player, TOTEM_OF_LAND_DRAGON, -1);
								giveItems(player, MARK_OF_WATCHMAN, 1);
								if ((qs.getMemoState() + 1) == 21111)
								{
									qs.setMemoState(30000);
								}
								else
								{
									qs.setMemoState(qs.getMemoState() + 1);
								}
								htmltext = "30851-02.html";
							}
							break;
						}
						case 21101:
						case 21011:
						case 21001:
						case 20111:
						case 20101:
						case 20011:
						case 20001:
						{
							htmltext = "30851-03.html";
							break;
						}
					}
					if (memoState >= 30000)
					{
						htmltext = "30851-04.html";
					}
					break;
				}
				case HIGH_PRIEST_ORVEN:
				{
					switch (qs.getMemoState())
					{
						case 21011:
						case 21010:
						case 21001:
						case 21000:
						case 20011:
						case 20010:
						case 20001:
						case 20000:
						{
							if (!hasQuestItems(player, REMAINS_OF_SACRAFICE))
							{
								htmltext = "30857-01.html";
							}
							else
							{
								takeItems(player, REMAINS_OF_SACRAFICE, -1);
								giveItems(player, MARK_OF_WATCHMAN, 1);
								if ((qs.getMemoState() + 100) == 21111)
								{
									qs.setMemoState(30000);
								}
								else
								{
									qs.setMemoState(qs.getMemoState() + 100);
								}
								htmltext = "30857-02.html";
							}
							break;
						}
						case 21110:
						case 21101:
						case 21100:
						case 20111:
						case 20110:
						case 20101:
						case 20100:
						{
							htmltext = "30857-03.html";
							break;
						}
					}
					if (memoState >= 30000)
					{
						htmltext = "30857-04.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

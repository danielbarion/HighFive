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
package quests.Q00171_ActsOfEvil;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Util;

/**
 * Acts Of Evil (171)
 * @author ivantotov
 */
public final class Q00171_ActsOfEvil extends Quest
{
	// NPCs
	private static final int TRADER_ARODIN = 30207;
	private static final int GUARD_ALVAH = 30381;
	private static final int TYRA = 30420;
	private static final int NETI = 30425;
	private static final int TRADER_ROLENTO = 30437;
	private static final int TUREK_CHIEF_BURAI = 30617;
	// Items
	private static final int BLADE_MOLD = 4239;
	private static final int TYRAS_BILL = 4240;
	private static final int RANGERS_REPORT1 = 4241;
	private static final int RANGERS_REPORT2 = 4242;
	private static final int RANGERS_REPORT3 = 4243;
	private static final int RANGERS_REPORT4 = 4244;
	private static final int WEAPONS_TRADE_CONTRACT = 4245;
	private static final int ATTACK_DIRECTIVES = 4246;
	private static final int CERTIFICATE_OF_THE_SILVER_GUILD = 4247;
	private static final int ROLENTOS_CARGOBOX = 4248;
	private static final int OL_MAHUM_CAPTAINS_HEAD = 4249;
	// Monster
	private static final int TUMRAN_BUGBEAR = 20062;
	private static final int TUMRAN_BUGBEAR_WARRIOR = 20064;
	private static final int OL_MAHUM_CAPTAIN = 20066;
	private static final int OL_MAHUM_GENERAL = 20438;
	private static final int TUREK_ORC_ARCHER = 20496;
	private static final int TUREK_ORC_SKIRMISHER = 20497;
	private static final int TUREK_ORC_SUPPLIER = 20498;
	private static final int TUREK_ORC_FOOTMAN = 20499;
	// Quest Monster
	private static final int OL_MAHUM_SUPPORT_TROOP = 27190;
	// Misc
	private static final int MIN_LEVEL = 27;
	
	public Q00171_ActsOfEvil()
	{
		super(171);
		addStartNpc(GUARD_ALVAH);
		addTalkId(GUARD_ALVAH, TRADER_ARODIN, TYRA, NETI, TRADER_ROLENTO, TUREK_CHIEF_BURAI);
		addKillId(TUMRAN_BUGBEAR, TUMRAN_BUGBEAR_WARRIOR, OL_MAHUM_CAPTAIN, OL_MAHUM_GENERAL, TUREK_ORC_ARCHER, TUREK_ORC_SKIRMISHER, TUREK_ORC_SUPPLIER, TUREK_ORC_FOOTMAN, OL_MAHUM_SUPPORT_TROOP);
		addSpawnId(OL_MAHUM_SUPPORT_TROOP);
		registerQuestItems(BLADE_MOLD, TYRAS_BILL, RANGERS_REPORT1, RANGERS_REPORT2, RANGERS_REPORT3, RANGERS_REPORT4, WEAPONS_TRADE_CONTRACT, ATTACK_DIRECTIVES, CERTIFICATE_OF_THE_SILVER_GUILD, ROLENTOS_CARGOBOX, OL_MAHUM_CAPTAINS_HEAD);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ("DESPAWN".equals(event))
		{
			if (npc != null)
			{
				npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.YOU_SHOULD_CONSIDER_GOING_BACK));
				npc.deleteMe();
			}
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
			case "30381-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					qs.setMemoState(1);
					htmltext = event;
				}
				break;
			}
			case "30381-07.html":
			{
				qs.setMemoState(5);
				qs.setCond(5, true);
				htmltext = event;
				break;
			}
			case "30381-12.html":
			{
				qs.setMemoState(7);
				qs.setCond(7, true);
				htmltext = event;
				break;
			}
			case "30437-04.html":
			{
				takeItems(player, WEAPONS_TRADE_CONTRACT, 1);
				giveItems(player, CERTIFICATE_OF_THE_SILVER_GUILD, 1);
				giveItems(player, ROLENTOS_CARGOBOX, 1);
				qs.setMemoState(9);
				qs.setCond(9, true);
				htmltext = event;
				break;
			}
			case "30207-01a.html":
			case "30437-02.html":
			case "30437-03.html":
			case "30617-03.html":
			case "30617-04.html":
			{
				htmltext = event;
				break;
			}
			case "30617-05.html":
			{
				takeItems(player, ATTACK_DIRECTIVES, 1);
				takeItems(player, CERTIFICATE_OF_THE_SILVER_GUILD, 1);
				takeItems(player, ROLENTOS_CARGOBOX, 1);
				qs.setMemoState(10);
				qs.setCond(10, true);
				htmltext = event;
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
			switch (npc.getId())
			{
				case TUMRAN_BUGBEAR:
				case TUMRAN_BUGBEAR_WARRIOR:
				{
					if (qs.isMemoState(5))
					{
						if (!hasQuestItems(killer, RANGERS_REPORT1))
						{
							giveItems(killer, RANGERS_REPORT1, 1);
							playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
						}
						else if (hasQuestItems(killer, RANGERS_REPORT1) && !hasQuestItems(killer, RANGERS_REPORT2))
						{
							if (getRandom(100) <= 19)
							{
								giveItems(killer, RANGERS_REPORT2, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
						else if (hasQuestItems(killer, RANGERS_REPORT1, RANGERS_REPORT2) && !hasQuestItems(killer, RANGERS_REPORT3))
						{
							if (getRandom(100) <= 19)
							{
								giveItems(killer, RANGERS_REPORT3, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
						else if (hasQuestItems(killer, RANGERS_REPORT1, RANGERS_REPORT2, RANGERS_REPORT3) && !hasQuestItems(killer, RANGERS_REPORT4))
						{
							if (getRandom(100) <= 19)
							{
								giveItems(killer, RANGERS_REPORT4, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case OL_MAHUM_CAPTAIN:
				{
					if (qs.isMemoState(10) && (getQuestItemsCount(killer, OL_MAHUM_CAPTAINS_HEAD) < 30))
					{
						if (getRandom(100) <= 49)
						{
							giveItems(killer, OL_MAHUM_CAPTAINS_HEAD, 1);
							if (getQuestItemsCount(killer, OL_MAHUM_CAPTAINS_HEAD) == 30)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case OL_MAHUM_GENERAL:
				{
					if (qs.isMemoState(6))
					{
						if (getRandom(100) <= 9)
						{
							if (!hasQuestItems(killer, WEAPONS_TRADE_CONTRACT))
							{
								giveItems(killer, WEAPONS_TRADE_CONTRACT, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
							if (!hasQuestItems(killer, ATTACK_DIRECTIVES))
							{
								giveItems(killer, ATTACK_DIRECTIVES, 1);
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
					}
					break;
				}
				case TUREK_ORC_ARCHER:
				{
					if (qs.isMemoState(2) && (getQuestItemsCount(killer, BLADE_MOLD) < 20))
					{
						if (getRandom(100) < 53)
						{
							giveItems(killer, BLADE_MOLD, 1);
							if (getQuestItemsCount(killer, BLADE_MOLD) == 20)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
						if (getQuestItemsCount(killer, BLADE_MOLD) == 5)
						{
							addAttackDesire(addSpawn(OL_MAHUM_SUPPORT_TROOP, npc, true, 0, false), killer);
						}
						if (getQuestItemsCount(killer, BLADE_MOLD) >= 10)
						{
							if (getRandom(100) <= 24)
							{
								addAttackDesire(addSpawn(OL_MAHUM_SUPPORT_TROOP, npc, true, 0, false), killer);
							}
						}
					}
					break;
				}
				case TUREK_ORC_SKIRMISHER:
				{
					if (qs.isMemoState(2) && (getQuestItemsCount(killer, BLADE_MOLD) < 20))
					{
						if (getRandom(100) < 55)
						{
							giveItems(killer, BLADE_MOLD, 1);
							if (getQuestItemsCount(killer, BLADE_MOLD) == 20)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
						if (getQuestItemsCount(killer, BLADE_MOLD) == 5)
						{
							addAttackDesire(addSpawn(OL_MAHUM_SUPPORT_TROOP, npc, true, 0, false), killer);
						}
						if (getQuestItemsCount(killer, BLADE_MOLD) >= 10)
						{
							if (getRandom(100) <= 24)
							{
								addAttackDesire(addSpawn(OL_MAHUM_SUPPORT_TROOP, npc, true, 0, false), killer);
							}
						}
					}
					break;
				}
				case TUREK_ORC_SUPPLIER:
				{
					if (qs.isMemoState(2) && (getQuestItemsCount(killer, BLADE_MOLD) < 20))
					{
						if (getRandom(100) < 51)
						{
							giveItems(killer, BLADE_MOLD, 1);
							if (getQuestItemsCount(killer, BLADE_MOLD) == 20)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
						if (getQuestItemsCount(killer, BLADE_MOLD) == 5)
						{
							addAttackDesire(addSpawn(OL_MAHUM_SUPPORT_TROOP, npc, true, 0, false), killer);
						}
						if (getQuestItemsCount(killer, BLADE_MOLD) >= 10)
						{
							if (getRandom(100) <= 24)
							{
								addAttackDesire(addSpawn(OL_MAHUM_SUPPORT_TROOP, npc, true, 0, false), killer);
							}
						}
					}
					break;
				}
				case TUREK_ORC_FOOTMAN:
				{
					if (qs.isMemoState(2) && (getQuestItemsCount(killer, BLADE_MOLD) < 20))
					{
						if (getRandom(2) < 1)
						{
							giveItems(killer, BLADE_MOLD, 1);
							if (getQuestItemsCount(killer, BLADE_MOLD) == 20)
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_MIDDLE);
							}
							else
							{
								playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
							}
						}
						if (getQuestItemsCount(killer, BLADE_MOLD) == 5)
						{
							addAttackDesire(addSpawn(OL_MAHUM_SUPPORT_TROOP, npc, true, 0, false), killer);
						}
						if (getQuestItemsCount(killer, BLADE_MOLD) >= 10)
						{
							if (getRandom(100) <= 24)
							{
								addAttackDesire(addSpawn(OL_MAHUM_SUPPORT_TROOP, npc, true, 0, false), killer);
							}
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
			if (npc.getId() == GUARD_ALVAH)
			{
				if (player.getLevel() < MIN_LEVEL)
				{
					htmltext = "30381-01.htm";
				}
				else
				{
					htmltext = "30381-02.htm";
				}
			}
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case GUARD_ALVAH:
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30381-04.html";
							break;
						}
						case 2:
						case 3:
						{
							htmltext = "30381-05.html";
							break;
						}
						case 4:
						{
							htmltext = "30381-06.html";
							break;
						}
						case 5:
						{
							if (hasQuestItems(player, RANGERS_REPORT1, RANGERS_REPORT2, RANGERS_REPORT3, RANGERS_REPORT4))
							{
								takeItems(player, RANGERS_REPORT1, 1);
								takeItems(player, RANGERS_REPORT2, 1);
								takeItems(player, RANGERS_REPORT3, 1);
								takeItems(player, RANGERS_REPORT4, 1);
								qs.setMemoState(6);
								qs.setCond(6, true);
								htmltext = "30381-09.html";
							}
							else
							{
								htmltext = "30381-08.html";
							}
							break;
						}
						case 6:
						{
							if (hasQuestItems(player, WEAPONS_TRADE_CONTRACT, ATTACK_DIRECTIVES))
							{
								htmltext = "30381-11.html";
							}
							else
							{
								htmltext = "30381-10.html";
							}
							break;
						}
						case 7:
						{
							htmltext = "30381-13.html";
							break;
						}
						case 8:
						{
							htmltext = "30381-14.html";
							break;
						}
						case 9:
						{
							htmltext = "30381-15.html";
							break;
						}
						case 10:
						{
							htmltext = "30381-16.html";
							break;
						}
						case 11:
						{
							giveAdena(player, 95000, true);
							addExpAndSp(player, 159820, 9182);
							htmltext = "30381-17.html";
							qs.exitQuest(false, true);
							break;
						}
					}
					break;
				}
				case TRADER_ARODIN:
				{
					if (memoState == 1)
					{
						qs.setMemoState(2);
						qs.setCond(2, true);
						htmltext = "30207-01.html";
					}
					else if (memoState == 2)
					{
						if (getQuestItemsCount(player, BLADE_MOLD) < 20)
						{
							htmltext = "30207-02.html";
						}
						else
						{
							htmltext = "30207-03.html";
						}
					}
					else if (memoState == 3)
					{
						takeItems(player, TYRAS_BILL, 1);
						qs.setMemoState(4);
						qs.setCond(4, true);
						htmltext = "30207-04.html";
					}
					else if (memoState >= 4)
					{
						htmltext = "30207-05.html";
					}
					break;
				}
				case TYRA:
				{
					if (memoState == 2)
					{
						if (getQuestItemsCount(player, BLADE_MOLD) < 20)
						{
							htmltext = "30420-01.html";
						}
						else
						{
							takeItems(player, BLADE_MOLD, -1);
							giveItems(player, TYRAS_BILL, 1);
							qs.setMemoState(3);
							qs.setCond(3, true);
							htmltext = "30420-02.html";
						}
					}
					else if (memoState == 3)
					{
						htmltext = "30420-03.html";
					}
					else if (memoState >= 4)
					{
						htmltext = "30420-04.html";
					}
					break;
				}
				case NETI:
				{
					if (memoState == 7)
					{
						qs.setMemoState(8);
						qs.setCond(8, true);
						htmltext = "30425-01.html";
					}
					else if (memoState == 8)
					{
						htmltext = "30425-02.html";
					}
					else if (memoState >= 9)
					{
						htmltext = "30425-03.html";
					}
					break;
				}
				case TRADER_ROLENTO:
				{
					if (memoState == 8)
					{
						htmltext = "30437-02.html";
					}
					else if (memoState == 9)
					{
						htmltext = "30437-05.html";
					}
					else if (memoState >= 10)
					{
						htmltext = "30437-06.html";
					}
					break;
				}
				case TUREK_CHIEF_BURAI:
				{
					if (memoState < 9)
					{
						htmltext = "30617-01.html";
					}
					else if (memoState == 9)
					{
						htmltext = "30617-02.html";
					}
					else if (memoState == 10)
					{
						if (getQuestItemsCount(player, OL_MAHUM_CAPTAINS_HEAD) < 30)
						{
							htmltext = "30617-06.html";
						}
						else
						{
							giveAdena(player, 8000, true);
							takeItems(player, OL_MAHUM_CAPTAINS_HEAD, -1);
							qs.setMemoState(11);
							qs.setCond(11, true);
							htmltext = "30617-07.html";
						}
					}
					else if (memoState == 11)
					{
						htmltext = "30617-08.html";
					}
					break;
				}
			}
		}
		else if (qs.isCompleted())
		{
			if (npc.getId() == GUARD_ALVAH)
			{
				htmltext = getAlreadyCompletedMsg(player);
			}
		}
		return htmltext;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		startQuestTimer("DESPAWN", 200000, npc, null);
		return super.onSpawn(npc);
	}
}
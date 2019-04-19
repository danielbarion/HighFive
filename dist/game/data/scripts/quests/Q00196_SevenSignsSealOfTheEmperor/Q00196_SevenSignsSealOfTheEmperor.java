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
package quests.Q00196_SevenSignsSealOfTheEmperor;

import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

import quests.Q00195_SevenSignsSecretRitualOfThePriests.Q00195_SevenSignsSecretRitualOfThePriests;

/**
 * Seven Signs, Seal of the Emperor (196)
 * @author Adry_85
 */
public final class Q00196_SevenSignsSealOfTheEmperor extends Quest
{
	// NPCs
	private static final int IASON_HEINE = 30969;
	private static final int MERCHANT_OF_MAMMON = 32584;
	private static final int SHUNAIMAN = 32586;
	private static final int WOOD = 32593;
	private static final int COURT_MAGICIAN = 32598;
	// Items
	private static final int ELMOREDEN_HOLY_WATER = 13808;
	private static final int COURT_MAGICIANS_MAGIC_STAFF = 13809;
	private static final int SEAL_OF_BINDING = 13846;
	private static final int SACRED_SWORD_OF_EINHASAD = 15310;
	// Misc
	private static final int MIN_LEVEL = 79;
	private boolean isBusy = false;
	
	public Q00196_SevenSignsSealOfTheEmperor()
	{
		super(196);
		addFirstTalkId(MERCHANT_OF_MAMMON);
		addStartNpc(IASON_HEINE);
		addTalkId(IASON_HEINE, MERCHANT_OF_MAMMON, SHUNAIMAN, WOOD, COURT_MAGICIAN);
		registerQuestItems(ELMOREDEN_HOLY_WATER, COURT_MAGICIANS_MAGIC_STAFF, SEAL_OF_BINDING, SACRED_SWORD_OF_EINHASAD);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ((npc.getId() == MERCHANT_OF_MAMMON) && "DESPAWN".equals(event))
		{
			isBusy = false;
			npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.THE_ANCIENT_PROMISE_TO_THE_EMPEROR_HAS_BEEN_FULFILLED));
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
			case "30969-02.htm":
			case "30969-03.htm":
			case "30969-04.htm":
			{
				htmltext = event;
				break;
			}
			case "30969-05.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "ssq_mammon":
			{
				if (qs.isCond(1))
				{
					if (!isBusy)
					{
						isBusy = true;
						npc.setScriptValue(1);
						final L2Npc merchant = addSpawn(MERCHANT_OF_MAMMON, 109743, 219975, -3512, 0, false, 0, false);
						merchant.broadcastPacket(new NpcSay(merchant.getObjectId(), ChatType.NPC_GENERAL, merchant.getId(), NpcStringId.WHO_DARES_SUMMON_THE_MERCHANT_OF_MAMMON));
						htmltext = "30969-06.html";
						startQuestTimer("DESPAWN", 120000, merchant, null);
					}
					else
					{
						htmltext = "30969-07.html";
					}
				}
				break;
			}
			case "30969-13.html":
			{
				if (qs.isCond(5))
				{
					htmltext = event;
				}
				break;
			}
			case "30969-14.html":
			{
				if (qs.isCond(5))
				{
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "32584-02.html":
			case "32584-03.html":
			case "32584-04.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32584-05.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					htmltext = event;
					cancelQuestTimers("DESPAWN");
					npc.deleteMe();
					isBusy = false;
				}
				break;
			}
			case "32586-02.html":
			case "32586-03.html":
			case "32586-04.html":
			case "32586-06.html":
			{
				if (qs.isCond(3))
				{
					htmltext = event;
				}
				break;
			}
			case "32586-07.html":
			{
				if (qs.isCond(3))
				{
					giveItems(player, ELMOREDEN_HOLY_WATER, 1);
					giveItems(player, SACRED_SWORD_OF_EINHASAD, 1);
					qs.setCond(4, true);
					player.sendPacket(SystemMessageId.BY_USING_THE_SKILL_OF_EINHASAD_S_HOLY_SWORD_DEFEAT_THE_EVIL_LILIMS);
					player.sendPacket(SystemMessageId.BY_USING_THE_HOLY_WATER_OF_EINHASAD_OPEN_THE_DOOR_POSSESSED_BY_THE_CURSE_OF_FLAMES);
					htmltext = event;
				}
				break;
			}
			case "32586-11.html":
			case "32586-12.html":
			case "32586-13.html":
			{
				if (qs.isCond(4) && (getQuestItemsCount(player, SEAL_OF_BINDING) >= 4))
				{
					htmltext = event;
				}
				break;
			}
			case "32586-14.html":
			{
				if (qs.isCond(4) && (getQuestItemsCount(player, SEAL_OF_BINDING) >= 4))
				{
					takeItems(player, -1, ELMOREDEN_HOLY_WATER, COURT_MAGICIANS_MAGIC_STAFF, SEAL_OF_BINDING, SACRED_SWORD_OF_EINHASAD);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "finish":
			{
				if (qs.isCond(6))
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						addExpAndSp(player, 52518015, 5817677);
						qs.exitQuest(false, true);
						htmltext = "32593-02.html";
					}
					else
					{
						htmltext = "level_check.html";
					}
				}
				break;
			}
			case "32598-02.html":
			{
				if (qs.isCond(3) || qs.isCond(4))
				{
					giveItems(player, COURT_MAGICIANS_MAGIC_STAFF, 1);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		return "32584.htm";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == IASON_HEINE)
				{
					qs = player.getQuestState(Q00195_SevenSignsSecretRitualOfThePriests.class.getSimpleName());
					htmltext = ((player.getLevel() >= MIN_LEVEL) && (qs != null) && qs.isCompleted()) ? "30969-01.htm" : "30969-08.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case IASON_HEINE:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "30969-09.html";
								break;
							}
							case 2:
							{
								qs.setCond(3, true);
								npc.setScriptValue(0);
								htmltext = "30969-10.html";
								break;
							}
							case 3:
							case 4:
							{
								htmltext = "30969-11.html";
								break;
							}
							case 5:
							{
								htmltext = "30969-12.html";
								break;
							}
							case 6:
							{
								htmltext = "30969-15.html";
								break;
							}
						}
						break;
					}
					case MERCHANT_OF_MAMMON:
					{
						if (qs.isCond(1))
						{
							if (npc.isScriptValue(0))
							{
								npc.setScriptValue(player.getObjectId());
							}
							htmltext = npc.isScriptValue(player.getObjectId()) ? "32584-01.html" : "32584-06.html";
						}
						break;
					}
					case SHUNAIMAN:
					{
						switch (qs.getCond())
						{
							case 3:
							{
								htmltext = "32586-01.html";
								break;
							}
							case 4:
							{
								if (getQuestItemsCount(player, SEAL_OF_BINDING) < 4)
								{
									if (hasQuestItems(player, ELMOREDEN_HOLY_WATER, SACRED_SWORD_OF_EINHASAD))
									{
										htmltext = "32586-08.html";
									}
									else if (!hasQuestItems(player, ELMOREDEN_HOLY_WATER) && hasQuestItems(player, SACRED_SWORD_OF_EINHASAD))
									{
										htmltext = "32586-09.html";
										giveItems(player, ELMOREDEN_HOLY_WATER, 1);
									}
									else if (hasQuestItems(player, ELMOREDEN_HOLY_WATER) && !hasQuestItems(player, SACRED_SWORD_OF_EINHASAD))
									{
										htmltext = "32586-09.html";
										giveItems(player, SACRED_SWORD_OF_EINHASAD, 1);
									}
									player.sendPacket(SystemMessageId.BY_USING_THE_SKILL_OF_EINHASAD_S_HOLY_SWORD_DEFEAT_THE_EVIL_LILIMS);
									player.sendPacket(SystemMessageId.BY_USING_THE_HOLY_WATER_OF_EINHASAD_OPEN_THE_DOOR_POSSESSED_BY_THE_CURSE_OF_FLAMES);
								}
								else
								{
									htmltext = "32586-10.html";
								}
								break;
							}
							case 5:
							{
								htmltext = "32586-15.html";
								break;
							}
						}
						break;
					}
					case WOOD:
					{
						if (qs.isCond(6))
						{
							htmltext = "32593-01.html";
						}
						break;
					}
					case COURT_MAGICIAN:
					{
						if (qs.isCond(3) || qs.isCond(4))
						{
							htmltext = !hasQuestItems(player, COURT_MAGICIANS_MAGIC_STAFF) ? "32598-01.html" : "32598-03.html";
							player.sendPacket(SystemMessageId.BY_USING_THE_COURT_MAGICIAN_S_MAGIC_STAFF_OPEN_THE_DOOR_ON_WHICH_THE_MAGICIAN_S_BARRIER_IS_PLACED);
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

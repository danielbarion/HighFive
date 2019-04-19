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
package quests.Q00126_TheNameOfEvil2;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;

import quests.Q00125_TheNameOfEvil1.Q00125_TheNameOfEvil1;

/**
 * The Name of Evil - 2 (126)
 * @author Adry_85
 */
public class Q00126_TheNameOfEvil2 extends Quest
{
	// NPCs
	private static final int SHILENS_STONE_STATUE = 32109;
	private static final int MUSHIKA = 32114;
	private static final int ASAMAH = 32115;
	private static final int ULU_KAIMU = 32119;
	private static final int BALU_KAIMU = 32120;
	private static final int CHUTA_KAIMU = 32121;
	private static final int WARRIORS_GRAVE = 32122;
	// Items
	private static final int GAZKH_FRAGMENT = 8782;
	private static final int BONE_POWDER = 8783;
	// Reward
	private static final int ENCHANT_WEAPON_A = 729;
	
	public Q00126_TheNameOfEvil2()
	{
		super(126);
		addStartNpc(ASAMAH);
		addTalkId(ASAMAH, ULU_KAIMU, BALU_KAIMU, CHUTA_KAIMU, WARRIORS_GRAVE, SHILENS_STONE_STATUE, MUSHIKA);
		registerQuestItems(GAZKH_FRAGMENT, BONE_POWDER);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "32115-1.html":
			{
				qs.startQuest();
				break;
			}
			case "32115-1b.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
				}
				break;
			}
			case "32119-3.html":
			{
				if (qs.isCond(2))
				{
					qs.setCond(3, true);
				}
				break;
			}
			case "32119-4.html":
			{
				if (qs.isCond(3))
				{
					qs.setCond(4, true);
				}
				break;
			}
			case "32119-4a.html":
			case "32119-5b.html":
			{
				playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_1ST);
				break;
			}
			case "32119-5.html":
			{
				if (qs.isCond(4))
				{
					qs.setCond(5, true);
				}
				break;
			}
			case "32120-3.html":
			{
				if (qs.isCond(5))
				{
					qs.setCond(6, true);
				}
				break;
			}
			case "32120-4.html":
			{
				if (qs.isCond(6))
				{
					qs.setCond(7, true);
				}
				break;
			}
			case "32120-4a.html":
			case "32120-5b.html":
			{
				playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_2ND);
				break;
			}
			case "32120-5.html":
			{
				if (qs.isCond(7))
				{
					qs.setCond(8, true);
				}
				break;
			}
			case "32121-3.html":
			{
				if (qs.isCond(8))
				{
					qs.setCond(9, true);
				}
				break;
			}
			case "32121-4.html":
			{
				if (qs.isCond(9))
				{
					qs.setCond(10, true);
				}
				break;
			}
			case "32121-4a.html":
			case "32121-5b.html":
			{
				playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_3RD);
				break;
			}
			case "32121-5.html":
			{
				if (qs.isCond(10))
				{
					giveItems(player, GAZKH_FRAGMENT, 1);
					qs.setCond(11, true);
				}
				break;
			}
			case "32122-2a.html":
			{
				npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
				break;
			}
			case "32122-2d.html":
			{
				takeItems(player, GAZKH_FRAGMENT, -1);
				break;
			}
			case "32122-3.html":
			{
				if (qs.isCond(12))
				{
					qs.setCond(13, true);
				}
				break;
			}
			case "32122-4.html":
			{
				if (qs.isCond(13))
				{
					qs.setCond(14, true);
				}
				break;
			}
			case "DO_One":
			{
				qs.set("DO", "1");
				event = "32122-4d.html";
				break;
			}
			case "MI_One":
			{
				qs.set("MI", "1");
				event = "32122-4f.html";
				break;
			}
			case "FA_One":
			{
				qs.set("FA", "1");
				event = "32122-4h.html";
				break;
			}
			case "SOL_One":
			{
				qs.set("SOL", "1");
				event = "32122-4j.html";
				break;
			}
			case "FA2_One":
			{
				qs.set("FA2", "1");
				if (qs.isCond(14) && (qs.getInt("DO") > 0) && (qs.getInt("MI") > 0) && (qs.getInt("FA") > 0) && (qs.getInt("SOL") > 0) && (qs.getInt("FA2") > 0))
				{
					event = "32122-4n.html";
					qs.setCond(15, true);
				}
				else
				{
					event = "32122-4m.html";
				}
				qs.unset("DO");
				qs.unset("MI");
				qs.unset("FA");
				qs.unset("SOL");
				qs.unset("FA2");
				break;
			}
			case "32122-4m.html":
			{
				qs.unset("DO");
				qs.unset("MI");
				qs.unset("FA");
				qs.unset("SOL");
				qs.unset("FA2");
				break;
			}
			case "FA_Two":
			{
				qs.set("FA", "1");
				event = "32122-5a.html";
				break;
			}
			case "SOL_Two":
			{
				qs.set("SOL", "1");
				event = "32122-5c.html";
				break;
			}
			case "TI_Two":
			{
				qs.set("TI", "1");
				event = "32122-5e.html";
				break;
			}
			case "SOL2_Two":
			{
				qs.set("SOL2", "1");
				event = "32122-5g.html";
				break;
			}
			case "FA2_Two":
			{
				qs.set("FA2", "1");
				if (qs.isCond(15) && (qs.getInt("FA") > 0) && (qs.getInt("SOL") > 0) && (qs.getInt("TI") > 0) && (qs.getInt("SOL2") > 0) && (qs.getInt("FA2") > 0))
				{
					event = "32122-5j.html";
					qs.setCond(16, true);
				}
				else
				{
					event = "32122-5i.html";
				}
				qs.unset("FA");
				qs.unset("SOL");
				qs.unset("TI");
				qs.unset("SOL2");
				qs.unset("FA2");
				break;
			}
			case "32122-5i.html":
			{
				qs.unset("FA");
				qs.unset("SOL");
				qs.unset("TI");
				qs.unset("SOL2");
				qs.unset("FA2");
				break;
			}
			case "SOL_Three":
			{
				qs.set("SOL", "1");
				event = "32122-6a.html";
				break;
			}
			case "FA_Three":
			{
				qs.set("FA", "1");
				event = "32122-6c.html";
				break;
			}
			case "MI_Three":
			{
				qs.set("MI", "1");
				event = "32122-6e.html";
				break;
			}
			case "FA2_Three":
			{
				qs.set("FA2", "1");
				event = "32122-6g.html";
				break;
			}
			case "MI2_Three":
			{
				qs.set("MI2", "1");
				if (qs.isCond(16) && (qs.getInt("SOL") > 0) && (qs.getInt("FA") > 0) && (qs.getInt("MI") > 0) && (qs.getInt("FA2") > 0) && (qs.getInt("MI2") > 0))
				{
					event = "32122-6j.html";
					qs.setCond(17, true);
				}
				else
				{
					event = "32122-6i.html";
				}
				qs.unset("SOL");
				qs.unset("FA");
				qs.unset("MI");
				qs.unset("FA2");
				qs.unset("MI2");
				break;
			}
			case "32122-6i.html":
			{
				qs.unset("SOL");
				qs.unset("FA");
				qs.unset("MI");
				qs.unset("FA2");
				qs.unset("MI2");
				break;
			}
			case "32122-7.html":
			{
				giveItems(player, BONE_POWDER, 1);
				playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_FULL);
				npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
				break;
			}
			case "32122-8.html":
			{
				if (qs.isCond(17))
				{
					qs.setCond(18, true);
				}
				break;
			}
			case "32109-2.html":
			{
				if (qs.isCond(18))
				{
					qs.setCond(19, true);
				}
				break;
			}
			case "32109-3.html":
			{
				if (qs.isCond(19))
				{
					takeItems(player, BONE_POWDER, -1);
					qs.setCond(20, true);
				}
				break;
			}
			case "32115-4.html":
			{
				if (qs.isCond(20))
				{
					qs.setCond(21, true);
				}
				break;
			}
			case "32115-5.html":
			{
				if (qs.isCond(21))
				{
					qs.setCond(22, true);
				}
				break;
			}
			case "32114-2.html":
			{
				if (qs.isCond(22))
				{
					qs.setCond(23, true);
				}
				break;
			}
			case "32114-3.html":
			{
				rewardItems(player, ENCHANT_WEAPON_A, 1);
				giveAdena(player, 460483, true);
				addExpAndSp(player, 1015973, 102802);
				qs.exitQuest(false, true);
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = getNoQuestMsg(player);
		QuestState qs = getQuestState(player, true);
		
		switch (npc.getId())
		{
			case ASAMAH:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						if (player.getLevel() < 77)
						{
							htmltext = "32115-0.htm";
						}
						else
						{
							qs = player.getQuestState(Q00125_TheNameOfEvil1.class.getSimpleName());
							htmltext = ((qs != null) && qs.isCompleted()) ? "32115-0a.htm" : "32115-0b.htm";
						}
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "32115-1d.html";
								break;
							}
							case 2:
							{
								htmltext = "32115-1c.html";
								break;
							}
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							case 9:
							case 10:
							case 11:
							case 12:
							case 13:
							case 14:
							case 15:
							case 16:
							case 17:
							case 18:
							case 19:
							{
								htmltext = "32115-2.html";
								break;
							}
							case 20:
							{
								htmltext = "32115-3.html";
								break;
							}
							case 21:
							{
								htmltext = "32115-4j.html";
								break;
							}
							case 22:
							{
								htmltext = "32115-5a.html";
								break;
							}
						}
						break;
					}
					case State.COMPLETED:
					{
						htmltext = getAlreadyCompletedMsg(player);
						break;
					}
				}
				break;
			}
			case ULU_KAIMU:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "32119-1.html";
							break;
						}
						case 2:
						{
							htmltext = "32119-2.html";
							npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
							break;
						}
						case 3:
						{
							htmltext = "32119-3c.html";
							break;
						}
						case 4:
						{
							htmltext = "32119-4c.html";
							break;
						}
						case 5:
						{
							htmltext = "32119-5a.html";
							break;
						}
					}
				}
				break;
			}
			case BALU_KAIMU:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						case 4:
						{
							htmltext = "32120-1.html";
							break;
						}
						case 5:
						{
							htmltext = "32120-2.html";
							npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
							break;
						}
						case 6:
						{
							htmltext = "32120-3c.html";
							break;
						}
						case 7:
						{
							htmltext = "32120-4c.html";
							break;
						}
						default:
						{
							htmltext = "32120-5a.html";
							break;
						}
					}
				}
				break;
			}
			case CHUTA_KAIMU:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
						case 6:
						case 7:
						{
							htmltext = "32121-1.html";
							break;
						}
						case 8:
						{
							htmltext = "32121-2.html";
							npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
							break;
						}
						case 9:
						{
							htmltext = "32121-3e.html";
							break;
						}
						case 10:
						{
							htmltext = "32121-4e.html";
							break;
						}
						default:
						{
							htmltext = "32121-5a.html";
							break;
						}
					}
				}
				break;
			}
			case WARRIORS_GRAVE:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
						case 6:
						case 7:
						case 8:
						case 9:
						case 10:
						{
							htmltext = "32122-1.html";
							break;
						}
						case 11:
						{
							htmltext = "32122-2.html";
							qs.setCond(12, true);
							break;
						}
						case 12:
						{
							htmltext = "32122-2l.html";
							break;
						}
						case 13:
						{
							htmltext = "32122-3b.html";
							break;
						}
						case 14:
						{
							htmltext = "32122-4.html";
							qs.unset("DO");
							qs.unset("MI");
							qs.unset("FA");
							qs.unset("SOL");
							qs.unset("FA2");
							break;
						}
						case 15:
						{
							htmltext = "32122-5.html";
							qs.unset("FA");
							qs.unset("SOL");
							qs.unset("TI");
							qs.unset("SOL2");
							qs.unset("FA2");
							break;
						}
						case 16:
						{
							htmltext = "32122-6.html";
							qs.unset("SOL");
							qs.unset("FA");
							qs.unset("MI");
							qs.unset("FA2");
							qs.unset("MI2");
							break;
						}
						case 17:
						{
							htmltext = hasQuestItems(player, BONE_POWDER) ? "32122-7.html" : "32122-7b.html";
							break;
						}
						case 18:
						{
							htmltext = "32122-8.html";
							break;
						}
						default:
						{
							htmltext = "32122-9.html";
							break;
						}
					}
				}
				break;
			}
			case SHILENS_STONE_STATUE:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
						case 6:
						case 7:
						case 8:
						case 9:
						case 10:
						case 11:
						case 12:
						case 13:
						case 14:
						case 15:
						case 16:
						case 17:
						{
							htmltext = "32109-1a.html";
							break;
						}
						case 18:
						{
							if (hasQuestItems(player, BONE_POWDER))
							{
								htmltext = "32109-1.html";
							}
							break;
						}
						case 19:
						{
							htmltext = "32109-2l.html";
							break;
						}
						case 20:
						{
							htmltext = "32109-5.html";
							break;
						}
						default:
						{
							htmltext = "32109-4.html";
							break;
						}
					}
				}
				break;
			}
			case MUSHIKA:
			{
				if (qs.isStarted())
				{
					if (qs.getCond() < 22)
					{
						htmltext = "32114-4.html";
					}
					else if (qs.isCond(22))
					{
						htmltext = "32114-1.html";
					}
					else
					{
						htmltext = "32114-2.html";
					}
				}
				break;
			}
		}
		return htmltext;
	}
}

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
package quests.Q00136_MoreThanMeetsTheEye;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * More Than Meets the Eye (136)
 * @author malyelfik
 */
public class Q00136_MoreThanMeetsTheEye extends Quest
{
	// NPCs
	private static final int HARDIN = 30832;
	private static final int ERRICKIN = 30701;
	private static final int CLAYTON = 30464;
	// Monsters
	private static final int GLASS_JAGUAR = 20250;
	private static final int GHOST1 = 20636;
	private static final int GHOST2 = 20637;
	private static final int GHOST3 = 20638;
	private static final int MIRROR = 20639;
	// Items
	private static final int ECTOPLASM = 9787;
	private static final int STABILIZED_ECTOPLASM = 9786;
	private static final int ORDER = 9788;
	private static final int GLASS_JAGUAR_CRYSTAL = 9789;
	private static final int BOOK_OF_SEAL = 9790;
	private static final int TRANSFORM_BOOK = 9648;
	// Misc
	private static final int MIN_LEVEL = 50;
	private static final int ECTOPLASM_COUNT = 35;
	private static final int CRYSTAL_COUNT = 5;
	private static final int[] CHANCES =
	{
		0,
		40,
		90,
		290
	};
	
	public Q00136_MoreThanMeetsTheEye()
	{
		super(136);
		addStartNpc(HARDIN);
		addTalkId(HARDIN, ERRICKIN, CLAYTON);
		addKillId(GHOST1, GHOST2, GHOST3, GLASS_JAGUAR, MIRROR);
		
		registerQuestItems(ECTOPLASM, STABILIZED_ECTOPLASM, ORDER, GLASS_JAGUAR_CRYSTAL, BOOK_OF_SEAL);
	}
	
	private void giveItem(QuestState qs, int itemId, int count, int maxCount, int cond)
	{
		final L2PcInstance player = qs.getPlayer();
		giveItems(player, itemId, count);
		if (getQuestItemsCount(player, itemId) >= maxCount)
		{
			qs.setCond(cond, true);
		}
		else
		{
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = event;
		switch (event)
		{
			case "30832-05.html":
			case "30832-06.html":
			case "30832-12.html":
			case "30832-13.html":
			case "30832-18.html":
			{
				break;
			}
			case "30832-03.htm":
			{
				qs.startQuest();
				break;
			}
			case "30832-07.html":
			{
				qs.setCond(2, true);
				break;
			}
			case "30832-11.html":
			{
				qs.set("talked", "2");
				break;
			}
			case "30832-14.html":
			{
				qs.unset("talked");
				giveItems(player, ORDER, 1);
				qs.setCond(6, true);
				break;
			}
			case "30832-17.html":
			{
				qs.set("talked", "2");
				break;
			}
			case "30832-19.html":
			{
				giveItems(player, TRANSFORM_BOOK, 1);
				giveAdena(player, 67550, true);
				qs.exitQuest(false, true);
				break;
			}
			case "30701-03.html":
			{
				qs.setCond(3, true);
				break;
			}
			case "30464-03.html":
			{
				takeItems(player, ORDER, -1);
				qs.setCond(7, true);
				break;
			}
			default:
			{
				htmltext = null;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if (qs == null)
		{
			return super.onKill(npc, killer, isSummon);
		}
		
		final int npcId = npc.getId();
		if ((npcId != GLASS_JAGUAR) && qs.isCond(3))
		{
			final int count = ((npcId == MIRROR) && ((getQuestItemsCount(killer, ECTOPLASM) + 2) < ECTOPLASM_COUNT)) ? 2 : 1;
			final int index = npcId - GHOST1;
			
			if ((getRandom(1000) < CHANCES[index]) && ((getQuestItemsCount(killer, ECTOPLASM) + count) < ECTOPLASM_COUNT))
			{
				giveItems(killer, ECTOPLASM, 1);
			}
			giveItem(qs, ECTOPLASM, count, ECTOPLASM_COUNT, 4);
		}
		else if ((npcId == GLASS_JAGUAR) && qs.isCond(7))
		{
			giveItem(qs, GLASS_JAGUAR_CRYSTAL, 1, CRYSTAL_COUNT, 8);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (npc.getId())
		{
			case HARDIN:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						htmltext = (player.getLevel() >= MIN_LEVEL) ? "30832-01.htm" : "30832-02.htm";
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "30832-04.html";
								break;
							}
							case 2:
							case 3:
							case 4:
							{
								htmltext = "30832-08.html";
								break;
							}
							case 5:
							{
								if (qs.getInt("talked") == 1)
								{
									htmltext = "30832-10.html";
								}
								else if (qs.getInt("talked") == 2)
								{
									htmltext = "30832-12.html";
								}
								else if (hasQuestItems(player, STABILIZED_ECTOPLASM))
								{
									takeItems(player, STABILIZED_ECTOPLASM, -1);
									qs.set("talked", "1");
									htmltext = "30832-09.html";
								}
								else
								{
									htmltext = "30832-08.html";
								}
								break;
							}
							case 6:
							case 7:
							case 8:
							{
								htmltext = "30832-15.html";
								break;
							}
							case 9:
							{
								if (qs.getInt("talked") == 1)
								{
									qs.set("talked", "2");
									htmltext = "30832-17.html";
								}
								else if (qs.getInt("talked") == 2)
								{
									htmltext = "30832-18.html";
								}
								else
								{
									takeItems(player, BOOK_OF_SEAL, -1);
									qs.set("talked", "1");
									htmltext = "30832-16.html";
								}
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
			case ERRICKIN:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "30701-01.html";
							break;
						}
						case 2:
						{
							htmltext = "30701-02.html";
							break;
						}
						case 3:
						{
							htmltext = "30701-04.html";
							break;
						}
						case 4:
						{
							if (getQuestItemsCount(player, ECTOPLASM) < ECTOPLASM_COUNT)
							{
								giveItems(player, STABILIZED_ECTOPLASM, 1);
								qs.setCond(5, true);
								htmltext = "30701-06.html";
							}
							else
							{
								takeItems(player, ECTOPLASM, -1);
								htmltext = "30701-05.html";
							}
							break;
						}
						default:
						{
							htmltext = "30701-07.html";
							break;
						}
					}
				}
				break;
			}
			case CLAYTON:
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
						{
							htmltext = "30464-01.html";
							break;
						}
						case 6:
						{
							htmltext = "30464-02.html";
							break;
						}
						case 7:
						{
							htmltext = "30464-04.html";
							break;
						}
						case 8:
						{
							giveItems(player, BOOK_OF_SEAL, 1);
							takeItems(player, GLASS_JAGUAR_CRYSTAL, -1);
							qs.setCond(9, true);
							htmltext = "30464-05.html";
							break;
						}
						default:
						{
							htmltext = "30464-06.html";
							break;
						}
					}
				}
				break;
			}
		}
		return htmltext;
	}
}
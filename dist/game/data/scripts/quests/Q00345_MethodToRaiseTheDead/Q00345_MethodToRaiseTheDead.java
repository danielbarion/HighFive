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
package quests.Q00345_MethodToRaiseTheDead;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.util.Util;

/**
 * Method to Raise the Dead (345)
 * @author Adry_85
 */
public final class Q00345_MethodToRaiseTheDead extends Quest
{
	// NPCs
	private static final int XENOVIA = 30912;
	private static final int DOROTHY = 30970;
	private static final int ORPHEUS = 30971;
	private static final int MEDIUM_JAR = 30973;
	// Items
	private static final int IMPERIAL_DIAMOND = 3456;
	private static final int VICTIMS_ARM_BONE = 4274;
	private static final int VICTIMS_THIGH_BONE = 4275;
	private static final int VICTIMS_SKULL = 4276;
	private static final int VICTIMS_RIB_BONE = 4277;
	private static final int VICTIMS_SPINE = 4278;
	private static final int USELESS_BONE_PIECES = 4280;
	private static final int POWDER_TO_SUMMON_DEAD_SOULS = 4281;
	private static final int BILL_OF_IASON_HEINE = 4407;
	// Misc
	private static final int MIN_LEVEL = 35;
	// Monsters
	private static final int CROKIAN = 20789;
	private static final int CROKIAN_WARRIOR = 20791;
	
	public Q00345_MethodToRaiseTheDead()
	{
		super(345);
		addStartNpc(DOROTHY);
		addTalkId(DOROTHY, ORPHEUS, MEDIUM_JAR, XENOVIA);
		addKillId(CROKIAN, CROKIAN_WARRIOR);
		registerQuestItems(VICTIMS_ARM_BONE, VICTIMS_THIGH_BONE, VICTIMS_SKULL, VICTIMS_RIB_BONE, VICTIMS_SPINE, USELESS_BONE_PIECES, POWDER_TO_SUMMON_DEAD_SOULS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "30970-02.htm":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "30970-03.html":
			{
				qs.setMemoState(1);
				htmltext = event;
				break;
			}
			case "30970-07.html":
			{
				if (hasQuestItems(player, VICTIMS_ARM_BONE, VICTIMS_THIGH_BONE, VICTIMS_SKULL, VICTIMS_RIB_BONE, VICTIMS_SPINE))
				{
					qs.setMemoState(2);
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "30971-02.html":
			case "30912-05.html":
			{
				htmltext = event;
				break;
			}
			case "30971-03.html":
			{
				final long uselessBonePiecesCount = getQuestItemsCount(player, USELESS_BONE_PIECES);
				
				if (uselessBonePiecesCount > 0)
				{
					giveAdena(player, uselessBonePiecesCount * 104, true);
					takeItems(player, USELESS_BONE_PIECES, -1);
					htmltext = event;
				}
				break;
			}
			case "30973-02.html":
			{
				final int memoStateEx = qs.getMemoStateEx(1);
				
				if (memoStateEx == 1)
				{
					htmltext = event;
				}
				else if (memoStateEx == 2)
				{
					htmltext = "30973-04.html";
				}
				else if (memoStateEx == 3)
				{
					htmltext = "30973-06.html";
				}
				break;
			}
			case "30973-03.html":
			{
				if (qs.isMemoState(7) && (qs.getMemoStateEx(1) == 1))
				{
					qs.setMemoState(8);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "30973-05.html":
			{
				if (qs.isMemoState(7) && (qs.getMemoStateEx(1) == 2))
				{
					qs.setMemoState(8);
					qs.setCond(6, true);
					htmltext = event;
				}
				break;
			}
			case "30973-07.html":
			{
				if (qs.isMemoState(7) && (qs.getMemoStateEx(1) == 3))
				{
					qs.setMemoState(8);
					qs.setCond(7, true);
					htmltext = event;
				}
				break;
			}
			case "30912-02.html":
			{
				if (qs.isMemoState(2))
				{
					htmltext = event;
				}
				break;
			}
			case "30912-03.html":
			{
				if (qs.isMemoState(2))
				{
					if (player.getAdena() >= 1000)
					{
						giveItems(player, POWDER_TO_SUMMON_DEAD_SOULS, 1);
						takeItems(player, Inventory.ADENA_ID, 1000);
						qs.setMemoState(3);
						qs.setCond(3, true);
						htmltext = event;
					}
					else
					{
						htmltext = "30912-04.html";
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
		final QuestState qs = getRandomPartyMemberState(killer, 1, 3, npc);
		
		if ((qs == null) || !Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, killer, true))
		{
			return null;
		}
		
		final int random = getRandom(100);
		if (random <= 5)
		{
			if (!hasQuestItems(qs.getPlayer(), VICTIMS_ARM_BONE))
			{
				giveItems(qs.getPlayer(), VICTIMS_ARM_BONE, 1);
			}
			else
			{
				giveItems(qs.getPlayer(), USELESS_BONE_PIECES, 1);
			}
			
			playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		else if (random <= 11)
		{
			if (!hasQuestItems(qs.getPlayer(), VICTIMS_THIGH_BONE))
			{
				giveItems(qs.getPlayer(), VICTIMS_THIGH_BONE, 1);
			}
			else
			{
				giveItems(qs.getPlayer(), USELESS_BONE_PIECES, 1);
			}
			
			playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		else if (random <= 17)
		{
			if (!hasQuestItems(qs.getPlayer(), VICTIMS_SKULL))
			{
				giveItems(qs.getPlayer(), VICTIMS_SKULL, 1);
			}
			else
			{
				giveItems(qs.getPlayer(), USELESS_BONE_PIECES, 1);
			}
			
			playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		else if (random <= 23)
		{
			if (!hasQuestItems(qs.getPlayer(), VICTIMS_RIB_BONE))
			{
				giveItems(qs.getPlayer(), VICTIMS_RIB_BONE, 1);
			}
			else
			{
				giveItems(qs.getPlayer(), USELESS_BONE_PIECES, 1);
			}
			
			playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		else if (random <= 29)
		{
			if (!hasQuestItems(qs.getPlayer(), VICTIMS_SPINE))
			{
				giveItems(qs.getPlayer(), VICTIMS_SPINE, 1);
			}
			else
			{
				giveItems(qs.getPlayer(), USELESS_BONE_PIECES, 1);
			}
			
			playSound(qs.getPlayer(), QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		else if (random <= 60)
		{
			giveItems(qs.getPlayer(), USELESS_BONE_PIECES, 1);
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "30970-01.htm" : "30970-04.htm";
		}
		else if (qs.isStarted())
		{
			switch (npc.getId())
			{
				case DOROTHY:
				{
					switch (qs.getMemoState())
					{
						case 0:
						{
							htmltext = "30970-03.html";
							qs.setMemoState(1);
							break;
						}
						case 1:
						{
							htmltext = (!hasQuestItems(player, VICTIMS_ARM_BONE, VICTIMS_THIGH_BONE, VICTIMS_SKULL, VICTIMS_RIB_BONE, VICTIMS_SPINE)) ? "30970-05.html" : "30970-06.html";
							break;
						}
						case 2:
						{
							htmltext = "30970-08.html";
							break;
						}
						case 3:
						{
							htmltext = "30970-09.html";
							break;
						}
						case 7:
						{
							htmltext = "30970-10.html";
							break;
						}
						case 8:
						{
							final int memoStateEx = qs.getMemoStateEx(1);
							final long uselessBonePiecesCount = getQuestItemsCount(player, USELESS_BONE_PIECES);
							
							if ((memoStateEx == 1) || (memoStateEx == 2))
							{
								giveItems(player, BILL_OF_IASON_HEINE, 3);
								giveAdena(player, 5390 + (70 * uselessBonePiecesCount), true);
								htmltext = "30970-11.html";
							}
							else if (memoStateEx == 3)
							{
								if (getRandom(100) <= 92)
								{
									giveItems(player, BILL_OF_IASON_HEINE, 5);
								}
								else
								{
									giveItems(player, IMPERIAL_DIAMOND, 1);
								}
								
								giveAdena(player, 3040 + (70 * uselessBonePiecesCount), true);
								htmltext = "30970-12.html";
							}
							
							qs.exitQuest(true, true);
							break;
						}
					}
					break;
				}
				case ORPHEUS:
				{
					if (hasQuestItems(player, USELESS_BONE_PIECES))
					{
						htmltext = "30971-01.html";
					}
					break;
				}
				case MEDIUM_JAR:
				{
					switch (qs.getMemoState())
					{
						case 3:
						{
							takeItems(player, -1, POWDER_TO_SUMMON_DEAD_SOULS, VICTIMS_ARM_BONE, VICTIMS_THIGH_BONE, VICTIMS_SKULL, VICTIMS_RIB_BONE, VICTIMS_SPINE);
							qs.setMemoState(7);
							
							final int random = getRandom(100);
							
							if (random <= 39)
							{
								qs.setMemoStateEx(1, 1);
							}
							else if (random <= 79)
							{
								qs.setMemoStateEx(1, 2);
							}
							else
							{
								qs.setMemoStateEx(1, 3);
							}
							
							htmltext = "30973-01.html";
							break;
						}
						case 7:
						{
							final int memoStateEx = qs.getMemoStateEx(1);
							
							if (memoStateEx == 1)
							{
								htmltext = "30973-08.html";
							}
							else if (memoStateEx == 2)
							{
								htmltext = "30973-09.html";
							}
							else if (memoStateEx == 3)
							{
								htmltext = "30973-10.html";
							}
							break;
						}
						case 8:
						{
							htmltext = "30973-11.html";
							break;
						}
					}
					break;
				}
				case XENOVIA:
				{
					if (qs.isMemoState(2))
					{
						htmltext = "30912-01.html";
					}
					else if (qs.isMemoState(7) || qs.isMemoState(8) || hasQuestItems(player, POWDER_TO_SUMMON_DEAD_SOULS))
					{
						htmltext = "30912-06.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}
}

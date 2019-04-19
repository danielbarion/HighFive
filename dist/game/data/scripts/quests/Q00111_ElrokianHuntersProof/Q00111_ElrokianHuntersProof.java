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
package quests.Q00111_ElrokianHuntersProof;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemChanceHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Elrokian Hunter's Proof (111)
 * @author Adry_85
 */
public final class Q00111_ElrokianHuntersProof extends Quest
{
	// NPCs
	private static final int MARQUEZ = 32113;
	private static final int MUSHIKA = 32114;
	private static final int ASAMAH = 32115;
	private static final int KIRIKACHIN = 32116;
	// Items
	private static final int ELROKIAN_TRAP = 8763;
	private static final int TRAP_STONE = 8764;
	private static final int DIARY_FRAGMENT = 8768;
	private static final int EXPEDITION_MEMBERS_LETTER = 8769;
	private static final int ORNITHOMINUS_CLAW = 8770;
	private static final int DEINONYCHUS_BONE = 8771;
	private static final int PACHYCEPHALOSAURUS_SKIN = 8772;
	private static final int PRACTICE_ELROKIAN_TRAP = 8773;
	// Misc
	private static final int MIN_LEVEL = 75;
	// Mobs
	private static final Map<Integer, ItemChanceHolder> MOBS_DROP_CHANCES = new HashMap<>();
	static
	{
		MOBS_DROP_CHANCES.put(22196, new ItemChanceHolder(DIARY_FRAGMENT, 0.51, 4)); // velociraptor_leader
		MOBS_DROP_CHANCES.put(22197, new ItemChanceHolder(DIARY_FRAGMENT, 0.51, 4)); // velociraptor
		MOBS_DROP_CHANCES.put(22198, new ItemChanceHolder(DIARY_FRAGMENT, 0.51, 4)); // velociraptor_s
		MOBS_DROP_CHANCES.put(22218, new ItemChanceHolder(DIARY_FRAGMENT, 0.25, 4)); // velociraptor_n
		MOBS_DROP_CHANCES.put(22223, new ItemChanceHolder(DIARY_FRAGMENT, 0.26, 4)); // velociraptor_leader2
		MOBS_DROP_CHANCES.put(22200, new ItemChanceHolder(ORNITHOMINUS_CLAW, 0.66, 11)); // ornithomimus_leader
		MOBS_DROP_CHANCES.put(22201, new ItemChanceHolder(ORNITHOMINUS_CLAW, 0.33, 11)); // ornithomimus
		MOBS_DROP_CHANCES.put(22202, new ItemChanceHolder(ORNITHOMINUS_CLAW, 0.66, 11)); // ornithomimus_s
		MOBS_DROP_CHANCES.put(22219, new ItemChanceHolder(ORNITHOMINUS_CLAW, 0.33, 11)); // ornithomimus_n
		MOBS_DROP_CHANCES.put(22224, new ItemChanceHolder(ORNITHOMINUS_CLAW, 0.33, 11)); // ornithomimus_leader2
		MOBS_DROP_CHANCES.put(22203, new ItemChanceHolder(DEINONYCHUS_BONE, 0.65, 11)); // deinonychus_leader
		MOBS_DROP_CHANCES.put(22204, new ItemChanceHolder(DEINONYCHUS_BONE, 0.32, 11)); // deinonychus
		MOBS_DROP_CHANCES.put(22205, new ItemChanceHolder(DEINONYCHUS_BONE, 0.66, 11)); // deinonychus_s
		MOBS_DROP_CHANCES.put(22220, new ItemChanceHolder(DEINONYCHUS_BONE, 0.32, 11)); // deinonychus_n
		MOBS_DROP_CHANCES.put(22225, new ItemChanceHolder(DEINONYCHUS_BONE, 0.32, 11)); // deinonychus_leader2
		MOBS_DROP_CHANCES.put(22208, new ItemChanceHolder(PACHYCEPHALOSAURUS_SKIN, 0.50, 11)); // pachycephalosaurus_ldr
		MOBS_DROP_CHANCES.put(22209, new ItemChanceHolder(PACHYCEPHALOSAURUS_SKIN, 0.50, 11)); // pachycephalosaurus
		MOBS_DROP_CHANCES.put(22210, new ItemChanceHolder(PACHYCEPHALOSAURUS_SKIN, 0.50, 11)); // pachycephalosaurus_s
		MOBS_DROP_CHANCES.put(22221, new ItemChanceHolder(PACHYCEPHALOSAURUS_SKIN, 0.49, 11)); // pachycephalosaurus_n
		MOBS_DROP_CHANCES.put(22226, new ItemChanceHolder(PACHYCEPHALOSAURUS_SKIN, 0.50, 11)); // pachycephalosaurus_ldr2
	}
	
	public Q00111_ElrokianHuntersProof()
	{
		super(111);
		addStartNpc(MARQUEZ);
		addTalkId(MARQUEZ, MUSHIKA, ASAMAH, KIRIKACHIN);
		addKillId(MOBS_DROP_CHANCES.keySet());
		registerQuestItems(DIARY_FRAGMENT, EXPEDITION_MEMBERS_LETTER, ORNITHOMINUS_CLAW, DEINONYCHUS_BONE, PACHYCEPHALOSAURUS_SKIN, PRACTICE_ELROKIAN_TRAP);
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
			case "32113-02.htm":
			case "32113-05.htm":
			case "32113-04.html":
			case "32113-10.html":
			case "32113-11.html":
			case "32113-12.html":
			case "32113-13.html":
			case "32113-14.html":
			case "32113-18.html":
			case "32113-19.html":
			case "32113-20.html":
			case "32113-21.html":
			case "32113-22.html":
			case "32113-23.html":
			case "32113-24.html":
			case "32115-08.html":
			case "32116-03.html":
			{
				htmltext = event;
				break;
			}
			case "32113-03.html":
			{
				qs.startQuest();
				qs.setMemoState(1);
				htmltext = event;
				break;
			}
			case "32113-15.html":
			{
				if (qs.isMemoState(3))
				{
					qs.setMemoState(4);
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "32113-25.html":
			{
				if (qs.isMemoState(5))
				{
					qs.setMemoState(6);
					qs.setCond(6, true);
					giveItems(player, EXPEDITION_MEMBERS_LETTER, 1);
					htmltext = event;
				}
				break;
			}
			case "32115-03.html":
			{
				if (qs.isMemoState(2))
				{
					qs.setMemoState(3);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32115-06.html":
			{
				if (qs.isMemoState(9))
				{
					qs.setMemoState(10);
					qs.setCond(9);
					playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_FULL);
					htmltext = event;
				}
				break;
			}
			case "32115-09.html":
			{
				if (qs.isMemoState(10))
				{
					qs.setMemoState(11);
					qs.setCond(10, true);
					htmltext = event;
				}
				break;
			}
			case "32116-04.html":
			{
				if (qs.isMemoState(7))
				{
					qs.setMemoState(8);
					playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_FULL);
					htmltext = event;
				}
				break;
			}
			case "32116-07.html":
			{
				if (qs.isMemoState(8))
				{
					qs.setMemoState(9);
					qs.setCond(8, true);
					htmltext = event;
				}
				break;
			}
			case "32116-10.html":
			{
				if (qs.isMemoState(12) && hasQuestItems(player, PRACTICE_ELROKIAN_TRAP))
				{
					takeItems(player, PRACTICE_ELROKIAN_TRAP, -1);
					giveItems(player, ELROKIAN_TRAP, 1);
					giveItems(player, TRAP_STONE, 100);
					giveAdena(player, 1071691, true);
					addExpAndSp(player, 553524, 55538);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(player, -1, 3, npc);
		if (qs != null)
		{
			final ItemChanceHolder item = MOBS_DROP_CHANCES.get(npc.getId());
			if (item.getCount() == qs.getMemoState())
			{
				if (qs.isCond(4) && giveItemRandomly(qs.getPlayer(), npc, item.getId(), 1, 50, item.getChance(), true))
				{
					qs.setCond(5);
				}
				else if (qs.isCond(10) && giveItemRandomly(qs.getPlayer(), npc, item.getId(), 1, 10, item.getChance(), true) && (getQuestItemsCount(qs.getPlayer(), ORNITHOMINUS_CLAW) >= 10) && (getQuestItemsCount(qs.getPlayer(), DEINONYCHUS_BONE) >= 10) && (getQuestItemsCount(qs.getPlayer(), PACHYCEPHALOSAURUS_SKIN) >= 10))
				{
					qs.setCond(11);
				}
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				if (npc.getId() == MARQUEZ)
				{
					htmltext = getAlreadyCompletedMsg(player);
				}
				break;
			}
			case State.CREATED:
			{
				if (npc.getId() == MARQUEZ)
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "32113-01.htm" : "32113-06.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case MARQUEZ:
					{
						switch (qs.getMemoState())
						{
							case 1:
							{
								htmltext = "32113-07.html";
								break;
							}
							case 2:
							{
								htmltext = "32113-08.html";
								break;
							}
							case 3:
							{
								htmltext = "32113-09.html";
								break;
							}
							case 4:
							{
								if (getQuestItemsCount(player, DIARY_FRAGMENT) < 50)
								{
									htmltext = "32113-16.html";
								}
								else
								{
									takeItems(player, DIARY_FRAGMENT, -1);
									qs.setMemoState(5);
									htmltext = "32113-17.html";
								}
								break;
							}
							case 5:
							{
								htmltext = "32113-26.html";
								break;
							}
							case 6:
							{
								htmltext = "32113-27.html";
								break;
							}
							case 7:
							case 8:
							{
								htmltext = "32113-28.html";
								break;
							}
							case 9:
							{
								htmltext = "32113-29.html";
								break;
							}
							case 10:
							case 11:
							case 12:
							{
								htmltext = "32113-30.html";
								break;
							}
						}
						break;
					}
					case MUSHIKA:
					{
						if (qs.isMemoState(1))
						{
							qs.setCond(2, true);
							qs.setMemoState(2);
							htmltext = "32114-01.html";
						}
						else if ((qs.getMemoState() > 1) && (qs.getMemoState() < 10))
						{
							htmltext = "32114-02.html";
						}
						else
						{
							htmltext = "32114-03.html";
						}
						break;
					}
					case ASAMAH:
					{
						switch (qs.getMemoState())
						{
							case 1:
							{
								htmltext = "32115-01.html";
								break;
							}
							case 2:
							{
								htmltext = "32115-02.html";
								break;
							}
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							{
								htmltext = "32115-04.html";
								break;
							}
							case 9:
							{
								htmltext = "32115-05.html";
								break;
							}
							case 10:
							{
								htmltext = "32115-07.html";
								break;
							}
							case 11:
							{
								if ((getQuestItemsCount(player, ORNITHOMINUS_CLAW) < 10) || (getQuestItemsCount(player, DEINONYCHUS_BONE) < 10) || (getQuestItemsCount(player, PACHYCEPHALOSAURUS_SKIN) < 10))
								{
									htmltext = "32115-10.html";
								}
								else
								{
									qs.setMemoState(12);
									qs.setCond(12, true);
									giveItems(player, PRACTICE_ELROKIAN_TRAP, 1);
									takeItems(player, ORNITHOMINUS_CLAW, -1);
									takeItems(player, DEINONYCHUS_BONE, -1);
									takeItems(player, PACHYCEPHALOSAURUS_SKIN, -1);
									htmltext = "32115-11.html";
								}
								break;
							}
							case 12:
							{
								htmltext = "32115-12.html";
								break;
							}
						}
						break;
					}
					case KIRIKACHIN:
					{
						switch (qs.getMemoState())
						{
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							{
								htmltext = "32116-01.html";
								break;
							}
							case 6:
							{
								if (hasQuestItems(player, EXPEDITION_MEMBERS_LETTER))
								{
									qs.setMemoState(7);
									qs.setCond(7, true);
									takeItems(player, EXPEDITION_MEMBERS_LETTER, -1);
									htmltext = "32116-02.html";
								}
								break;
							}
							case 7:
							{
								htmltext = "32116-05.html";
								break;
							}
							case 8:
							{
								htmltext = "32116-06.html";
								break;
							}
							case 9:
							case 10:
							case 11:
							{
								htmltext = "32116-08.html";
								break;
							}
							case 12:
							{
								htmltext = "32116-09.html";
								break;
							}
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

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
package quests.Q00125_TheNameOfEvil1;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;

import quests.Q00124_MeetingTheElroki.Q00124_MeetingTheElroki;

/**
 * The Name of Evil - 1 (125)
 * @author Adry_85
 */
public class Q00125_TheNameOfEvil1 extends Quest
{
	// NPCs
	private static final int MUSHIKA = 32114;
	private static final int KARAKAWEI = 32117;
	private static final int ULU_KAIMU = 32119;
	private static final int BALU_KAIMU = 32120;
	private static final int CHUTA_KAIMU = 32121;
	// Items
	private static final int ORNITHOMIMUS_CLAW = 8779;
	private static final int DEINONYCHUS_BONE = 8780;
	private static final int EPITAPH_OF_WISDOM = 8781;
	private static final int GAZKH_FRAGMENT = 8782;
	
	private static final Map<Integer, Integer> ORNITHOMIMUS = new HashMap<>();
	private static final Map<Integer, Integer> DEINONYCHUS = new HashMap<>();
	static
	{
		ORNITHOMIMUS.put(22200, 661);
		ORNITHOMIMUS.put(22201, 330);
		ORNITHOMIMUS.put(22202, 661);
		ORNITHOMIMUS.put(22219, 327);
		ORNITHOMIMUS.put(22224, 327);
		DEINONYCHUS.put(22203, 651);
		DEINONYCHUS.put(22204, 326);
		DEINONYCHUS.put(22205, 651);
		DEINONYCHUS.put(22220, 319);
		DEINONYCHUS.put(22225, 319);
	}
	
	public Q00125_TheNameOfEvil1()
	{
		super(125);
		addStartNpc(MUSHIKA);
		addTalkId(MUSHIKA, KARAKAWEI, ULU_KAIMU, BALU_KAIMU, CHUTA_KAIMU);
		addKillId(ORNITHOMIMUS.keySet());
		addKillId(DEINONYCHUS.keySet());
		registerQuestItems(ORNITHOMIMUS_CLAW, DEINONYCHUS_BONE, EPITAPH_OF_WISDOM, GAZKH_FRAGMENT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		String htmltext = event;
		switch (event)
		{
			case "32114-05.html":
			{
				qs.startQuest();
				break;
			}
			case "32114-08.html":
			{
				if (qs.isCond(1))
				{
					giveItems(player, GAZKH_FRAGMENT, 1);
					qs.setCond(2, true);
				}
				break;
			}
			case "32117-09.html":
			{
				if (qs.isCond(2))
				{
					qs.setCond(3, true);
				}
				break;
			}
			case "32117-15.html":
			{
				if (qs.isCond(4))
				{
					qs.setCond(5, true);
				}
				break;
			}
			case "T_One":
			{
				qs.set("T", "1");
				htmltext = "32119-04.html";
				break;
			}
			case "E_One":
			{
				qs.set("E", "1");
				htmltext = "32119-05.html";
				break;
			}
			case "P_One":
			{
				qs.set("P", "1");
				htmltext = "32119-06.html";
				break;
			}
			case "U_One":
			{
				qs.set("U", "1");
				if (qs.isCond(5) && (qs.getInt("T") > 0) && (qs.getInt("E") > 0) && (qs.getInt("P") > 0) && (qs.getInt("U") > 0))
				{
					htmltext = "32119-08.html";
					qs.set("Memo", "1");
				}
				else
				{
					htmltext = "32119-07.html";
				}
				qs.unset("T");
				qs.unset("E");
				qs.unset("P");
				qs.unset("U");
				break;
			}
			case "32119-07.html":
			{
				qs.unset("T");
				qs.unset("E");
				qs.unset("P");
				qs.unset("U");
				break;
			}
			case "32119-18.html":
			{
				if (qs.isCond(5))
				{
					qs.setCond(6, true);
					qs.unset("Memo");
				}
				break;
			}
			case "T_Two":
			{
				qs.set("T", "1");
				htmltext = "32120-04.html";
				break;
			}
			case "O_Two":
			{
				qs.set("O", "1");
				htmltext = "32120-05.html";
				break;
			}
			case "O2_Two":
			{
				qs.set("O2", "1");
				htmltext = "32120-06.html";
				break;
			}
			case "N_Two":
			{
				qs.set("N", "1");
				if (qs.isCond(6) && (qs.getInt("T") > 0) && (qs.getInt("O") > 0) && (qs.getInt("O2") > 0) && (qs.getInt("N") > 0))
				{
					htmltext = "32120-08.html";
					qs.set("Memo", "1");
				}
				else
				{
					htmltext = "32120-07.html";
				}
				qs.unset("T");
				qs.unset("O");
				qs.unset("O2");
				qs.unset("N");
				break;
			}
			case "32120-07.html":
			{
				qs.unset("T");
				qs.unset("O");
				qs.unset("O2");
				qs.unset("N");
			}
			case "32120-17.html":
			{
				if (qs.isCond(6))
				{
					qs.setCond(7, true);
					qs.unset("Memo");
				}
				break;
			}
			case "W_Three":
			{
				qs.set("W", "1");
				htmltext = "32121-04.html";
				break;
			}
			case "A_Three":
			{
				qs.set("A", "1");
				htmltext = "32121-05.html";
				break;
			}
			case "G_Three":
			{
				qs.set("G", "1");
				htmltext = "32121-06.html";
				break;
			}
			case "U_Three":
			{
				qs.set("U", "1");
				if (qs.isCond(7) && (qs.getInt("W") > 0) && (qs.getInt("A") > 0) && (qs.getInt("G") > 0) && (qs.getInt("U") > 0))
				{
					htmltext = "32121-08.html";
					qs.set("Memo", "1");
				}
				else
				{
					htmltext = "32121-07.html";
				}
				qs.unset("W");
				qs.unset("A");
				qs.unset("G");
				qs.unset("U");
				break;
			}
			case "32121-07.html":
			{
				qs.unset("W");
				qs.unset("A");
				qs.unset("G");
				qs.unset("U");
				break;
			}
			case "32121-11.html":
			{
				qs.set("Memo", "2");
				break;
			}
			case "32121-16.html":
			{
				qs.set("Memo", "3");
				break;
			}
			case "32121-18.html":
			{
				if (qs.isCond(7) && hasQuestItems(player, GAZKH_FRAGMENT))
				{
					giveItems(player, EPITAPH_OF_WISDOM, 1);
					takeItems(player, GAZKH_FRAGMENT, -1);
					qs.setCond(8, true);
					qs.unset("Memo");
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 3);
		if (partyMember == null)
		{
			return null;
		}
		
		final QuestState qs = getQuestState(partyMember, false);
		final int npcId = npc.getId();
		if (ORNITHOMIMUS.containsKey(npcId))
		{
			if ((getQuestItemsCount(player, ORNITHOMIMUS_CLAW) < 2) && (getRandom(1000) < (ORNITHOMIMUS.get(npcId) * Config.RATE_QUEST_DROP)))
			{
				giveItems(player, ORNITHOMIMUS_CLAW, 1);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		else if (DEINONYCHUS.containsKey(npcId) && (getQuestItemsCount(player, DEINONYCHUS_BONE) < 2) && (getRandom(1000) < (DEINONYCHUS.get(npcId) * Config.RATE_QUEST_DROP)))
		{
			giveItems(player, DEINONYCHUS_BONE, 1);
			playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		}
		
		if ((getQuestItemsCount(player, ORNITHOMIMUS_CLAW) == 2) && (getQuestItemsCount(player, DEINONYCHUS_BONE) == 2))
		{
			qs.setCond(4, true);
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = getNoQuestMsg(player);
		QuestState qs = getQuestState(player, true);
		
		switch (npc.getId())
		{
			case MUSHIKA:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						if (player.getLevel() < 76)
						{
							htmltext = "32114-01a.htm";
						}
						else
						{
							qs = player.getQuestState(Q00124_MeetingTheElroki.class.getSimpleName());
							htmltext = ((qs != null) && qs.isCompleted()) ? "32114-01.htm" : "32114-01b.htm";
						}
						break;
					}
					case State.STARTED:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "32114-09.html";
								break;
							}
							case 2:
							{
								htmltext = "32114-10.html";
								break;
							}
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							{
								htmltext = "32114-11.html";
								break;
							}
							case 8:
							{
								if (hasQuestItems(player, EPITAPH_OF_WISDOM))
								{
									htmltext = "32114-12.html";
									addExpAndSp(player, 859195, 86603);
									qs.exitQuest(false, true);
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
			case KARAKAWEI:
			{
				if (qs.isStarted())
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = "32117-01.html";
							break;
						}
						case 2:
						{
							htmltext = "32117-02.html";
							break;
						}
						case 3:
						{
							htmltext = "32117-10.html";
							break;
						}
						case 4:
						{
							if ((getQuestItemsCount(player, ORNITHOMIMUS_CLAW) >= 2) && (getQuestItemsCount(player, DEINONYCHUS_BONE) >= 2))
							{
								takeItems(player, ORNITHOMIMUS_CLAW, -1);
								takeItems(player, DEINONYCHUS_BONE, -1);
								htmltext = "32117-11.html";
							}
							break;
						}
						case 5:
						{
							htmltext = "32117-16.html";
							break;
						}
						case 6:
						case 7:
						{
							htmltext = "32117-17.html";
							break;
						}
						case 8:
						{
							htmltext = "32117-18.html";
							break;
						}
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
						case 2:
						case 3:
						case 4:
						{
							htmltext = "32119-01.html";
							break;
						}
						case 5:
						{
							if (qs.get("Memo") == null)
							{
								htmltext = "32119-02.html";
								npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
								qs.unset("T");
								qs.unset("E");
								qs.unset("P");
								qs.unset("U");
							}
							else
							{
								htmltext = "32119-09.html";
							}
							break;
						}
						case 6:
						{
							htmltext = "32119-18.html";
							break;
						}
						default:
						{
							htmltext = "32119-19.html";
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
						case 5:
						{
							htmltext = "32120-01.html";
							break;
						}
						case 6:
						{
							if (qs.get("Memo") == null)
							{
								htmltext = "32120-02.html";
								npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
								qs.unset("T");
								qs.unset("O");
								qs.unset("O2");
								qs.unset("N");
							}
							else
							{
								htmltext = "32120-09.html";
							}
							break;
						}
						case 7:
						{
							htmltext = "32120-17.html";
							break;
						}
						default:
						{
							htmltext = "32119-18.html";
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
						{
							htmltext = "32121-01.html";
							break;
						}
						case 7:
						{
							if (qs.get("Memo") == null)
							{
								htmltext = "32121-02.html";
								npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
								qs.unset("W");
								qs.unset("A");
								qs.unset("G");
								qs.unset("U");
								break;
							}
							
							switch (qs.getInt("Memo"))
							{
								case 1:
								{
									htmltext = "32121-09.html";
									break;
								}
								case 2:
								{
									htmltext = "32121-19.html";
									break;
								}
								case 3:
								{
									htmltext = "32121-20.html";
									break;
								}
							}
							break;
						}
						case 8:
						{
							htmltext = "32121-21.html";
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

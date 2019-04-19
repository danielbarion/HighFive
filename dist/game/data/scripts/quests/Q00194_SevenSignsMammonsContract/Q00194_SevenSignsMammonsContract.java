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
package quests.Q00194_SevenSignsMammonsContract;

import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00193_SevenSignsDyingMessage.Q00193_SevenSignsDyingMessage;

/**
 * Seven Signs, Mammon's Contract (194)
 * @author Adry_85
 */
public final class Q00194_SevenSignsMammonsContract extends Quest
{
	// NPCs
	private static final int SIR_GUSTAV_ATHEBALDT = 30760;
	private static final int CLAUDIA_ATHEBALDT = 31001;
	private static final int COLIN = 32571;
	private static final int FROG = 32572;
	private static final int TESS = 32573;
	private static final int KUTA = 32574;
	// Items
	private static final int ATHEBALDTS_INTRODUCTION = 13818;
	private static final int NATIVES_GLOVE = 13819;
	private static final int FROG_KINGS_BEAD = 13820;
	private static final int GRANDA_TESS_CANDY_POUCH = 13821;
	// Misc
	private static final int MIN_LEVEL = 79;
	// Skills
	private static SkillHolder TRANSFORMATION_FROG = new SkillHolder(6201, 1);
	private static SkillHolder TRANSFORMATION_KID = new SkillHolder(6202, 1);
	private static SkillHolder TRANSFORMATION_NATIVE = new SkillHolder(6203, 1);
	
	public Q00194_SevenSignsMammonsContract()
	{
		super(194);
		addStartNpc(SIR_GUSTAV_ATHEBALDT);
		addTalkId(SIR_GUSTAV_ATHEBALDT, COLIN, FROG, TESS, KUTA, CLAUDIA_ATHEBALDT);
		registerQuestItems(ATHEBALDTS_INTRODUCTION, NATIVES_GLOVE, FROG_KINGS_BEAD, GRANDA_TESS_CANDY_POUCH);
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
			case "30760-02.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "30760-03.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
			case "30760-04.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
					playSound(player, QuestSound.ITEMSOUND_QUEST_MIDDLE);
				}
				break;
			}
			case "showmovie":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					playMovie(player, Movie.SSQ_CONTRACT_OF_MAMMON);
					return "";
				}
				break;
			}
			case "30760-07.html":
			{
				if (qs.isCond(2))
				{
					giveItems(player, ATHEBALDTS_INTRODUCTION, 1);
					qs.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "32571-03.html":
			case "32571-04.html":
			{
				if (qs.isCond(3) && hasQuestItems(player, ATHEBALDTS_INTRODUCTION))
				{
					htmltext = event;
				}
				break;
			}
			case "32571-05.html":
			{
				if (qs.isCond(3) && hasQuestItems(player, ATHEBALDTS_INTRODUCTION))
				{
					takeItems(player, ATHEBALDTS_INTRODUCTION, -1);
					npc.setTarget(player);
					npc.doCast(TRANSFORMATION_FROG.getSkill());
					qs.setCond(4, true);
					htmltext = event;
				}
				break;
			}
			case "32571-07.html":
			{
				if (qs.isCond(4) && (player.getTransformationId() != 111) && !hasQuestItems(player, FROG_KINGS_BEAD))
				{
					npc.setTarget(player);
					npc.doCast(TRANSFORMATION_FROG.getSkill());
					htmltext = event;
				}
				break;
			}
			case "32571-09.html":
			{
				if (qs.isCond(4) && (player.getTransformationId() == 111) && !hasQuestItems(player, FROG_KINGS_BEAD))
				{
					player.stopAllEffects();
					htmltext = event;
				}
				break;
			}
			case "32571-11.html":
			{
				if (qs.isCond(5) && hasQuestItems(player, FROG_KINGS_BEAD))
				{
					takeItems(player, FROG_KINGS_BEAD, -1);
					qs.setCond(6, true);
					htmltext = event;
					if (player.getTransformationId() == 111)
					{
						player.stopAllEffects();
					}
				}
				break;
			}
			case "32571-13.html":
			{
				if (qs.isCond(6))
				{
					npc.setTarget(player);
					npc.doCast(TRANSFORMATION_KID.getSkill());
					qs.setCond(7, true);
					htmltext = event;
				}
				break;
			}
			case "32571-15.html":
			{
				if (qs.isCond(7) && (player.getTransformationId() != 112) && !hasQuestItems(player, GRANDA_TESS_CANDY_POUCH))
				{
					npc.setTarget(player);
					npc.doCast(TRANSFORMATION_KID.getSkill());
					htmltext = event;
				}
				break;
			}
			case "32571-17.html":
			{
				if (qs.isCond(7) && (player.getTransformationId() == 112) && !hasQuestItems(player, GRANDA_TESS_CANDY_POUCH))
				{
					player.stopAllEffects();
					htmltext = event;
				}
				break;
			}
			case "32571-19.html":
			{
				if (qs.isCond(8) && hasQuestItems(player, GRANDA_TESS_CANDY_POUCH))
				{
					takeItems(player, GRANDA_TESS_CANDY_POUCH, -1);
					qs.setCond(9, true);
					htmltext = event;
					if (player.getTransformationId() == 112)
					{
						player.stopAllEffects();
					}
				}
				break;
			}
			case "32571-21.html":
			{
				if (qs.isCond(9))
				{
					npc.setTarget(player);
					npc.doCast(TRANSFORMATION_NATIVE.getSkill());
					qs.setCond(10, true);
					htmltext = event;
				}
				break;
			}
			case "32571-23.html":
			{
				if (qs.isCond(10) && (player.getTransformationId() != 124) && !hasQuestItems(player, NATIVES_GLOVE))
				{
					npc.setTarget(player);
					npc.doCast(TRANSFORMATION_NATIVE.getSkill());
					htmltext = event;
				}
				break;
			}
			case "32571-25.html":
			{
				if (qs.isCond(10) && (player.getTransformationId() == 124) && !hasQuestItems(player, NATIVES_GLOVE))
				{
					player.stopAllEffects();
					htmltext = event;
				}
				break;
			}
			case "32571-27.html":
			{
				if (qs.isCond(11) && hasQuestItems(player, NATIVES_GLOVE))
				{
					takeItems(player, NATIVES_GLOVE, -1);
					qs.setCond(12, true);
					htmltext = event;
					if (player.getTransformationId() == 124)
					{
						player.stopAllEffects();
					}
				}
				break;
			}
			case "32572-03.html":
			case "32572-04.html":
			{
				if (qs.isCond(4))
				{
					htmltext = event;
				}
				break;
			}
			case "32572-05.html":
			{
				if (qs.isCond(4))
				{
					giveItems(player, FROG_KINGS_BEAD, 1);
					qs.setCond(5, true);
					htmltext = event;
				}
				break;
			}
			case "32573-03.html":
			{
				if (qs.isCond(7))
				{
					htmltext = event;
				}
				break;
			}
			case "32573-04.html":
			{
				if (qs.isCond(7))
				{
					giveItems(player, GRANDA_TESS_CANDY_POUCH, 1);
					qs.setCond(8, true);
					htmltext = event;
				}
				break;
			}
			case "32574-03.html":
			case "32574-04.html":
			{
				if (qs.isCond(10))
				{
					htmltext = event;
				}
				break;
			}
			case "32574-05.html":
			{
				if (qs.isCond(10))
				{
					giveItems(player, NATIVES_GLOVE, 1);
					qs.setCond(11, true);
					htmltext = event;
				}
				break;
			}
			case "31001-02.html":
			{
				if (qs.isCond(12))
				{
					htmltext = event;
				}
				break;
			}
			case "31001-03.html":
			{
				if (qs.isCond(12))
				{
					if (player.getLevel() >= MIN_LEVEL)
					{
						addExpAndSp(player, 52518015, 5817677);
						qs.exitQuest(false, true);
						htmltext = event;
					}
					else
					{
						htmltext = "level_check.html";
					}
				}
				break;
			}
		}
		return htmltext;
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
				if (npc.getId() == SIR_GUSTAV_ATHEBALDT)
				{
					qs = player.getQuestState(Q00193_SevenSignsDyingMessage.class.getSimpleName());
					htmltext = ((player.getLevel() >= MIN_LEVEL) && (qs != null) && qs.isCompleted()) ? "30760-01.htm" : "30760-05.html";
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case SIR_GUSTAV_ATHEBALDT:
					{
						if (qs.isCond(1))
						{
							htmltext = "30760-02.html";
						}
						else if (qs.isCond(2))
						{
							htmltext = "30760-06.html";
						}
						else if (qs.isCond(3) && hasQuestItems(player, ATHEBALDTS_INTRODUCTION))
						{
							htmltext = "30760-08.html";
						}
						break;
					}
					case COLIN:
					{
						switch (qs.getCond())
						{
							case 1:
							case 2:
							{
								htmltext = "32571-01.html";
								break;
							}
							case 3:
							{
								if (hasQuestItems(player, ATHEBALDTS_INTRODUCTION))
								{
									htmltext = "32571-02.html";
								}
								break;
							}
							case 4:
							{
								if (!hasQuestItems(player, FROG_KINGS_BEAD))
								{
									htmltext = (player.getTransformationId() != 111) ? "32571-06.html" : "32571-08.html";
								}
								break;
							}
							case 5:
							{
								if (hasQuestItems(player, FROG_KINGS_BEAD))
								{
									htmltext = "32571-10.html";
								}
								break;
							}
							case 6:
							{
								htmltext = "32571-12.html";
								break;
							}
							case 7:
							{
								if (!hasQuestItems(player, GRANDA_TESS_CANDY_POUCH))
								{
									htmltext = (player.getTransformationId() != 112) ? "32571-14.html" : "32571-16.html";
								}
								break;
							}
							case 8:
							{
								if (hasQuestItems(player, GRANDA_TESS_CANDY_POUCH))
								{
									htmltext = "32571-18.html";
								}
								break;
							}
							case 9:
							{
								htmltext = "32571-20.html";
								break;
							}
							case 10:
							{
								if (!hasQuestItems(player, NATIVES_GLOVE))
								{
									htmltext = (player.getTransformationId() != 124) ? "32571-22.html" : "32571-24.html";
								}
								break;
							}
							case 11:
							{
								if (hasQuestItems(player, NATIVES_GLOVE))
								{
									htmltext = "32571-26.html";
								}
								break;
							}
							case 12:
							{
								htmltext = "32571-28.html";
								break;
							}
						}
						break;
					}
					case FROG:
					{
						switch (qs.getCond())
						{
							case 1:
							case 2:
							case 3:
							{
								htmltext = "32572-01.html";
								break;
							}
							case 4:
							{
								htmltext = (player.getTransformationId() == 111) ? "32572-02.html" : "32572-06.html";
								break;
							}
							case 5:
							{
								if (hasQuestItems(player, FROG_KINGS_BEAD) && (player.getTransformationId() == 111))
								{
									htmltext = "32572-07.html";
								}
								break;
							}
						}
						break;
					}
					case TESS:
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
								htmltext = "32573-01.html";
								break;
							}
							case 7:
							{
								htmltext = (player.getTransformationId() == 112) ? "32573-02.html" : "32573-05.html";
								break;
							}
							case 8:
							{
								if (hasQuestItems(player, GRANDA_TESS_CANDY_POUCH) && (player.getTransformationId() == 112))
								{
									htmltext = "32573-06.html";
								}
								break;
							}
						}
						break;
					}
					case KUTA:
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
							{
								htmltext = "32574-01.html";
								break;
							}
							case 10:
							{
								htmltext = (player.getTransformationId() == 124) ? "32574-02.html" : "32574-06.html";
								break;
							}
							case 11:
							{
								if (hasQuestItems(player, NATIVES_GLOVE) && (player.getTransformationId() == 124))
								{
									htmltext = "32574-07.html";
								}
								break;
							}
						}
						break;
					}
					case CLAUDIA_ATHEBALDT:
					{
						if (qs.isCond(12))
						{
							htmltext = "31001-01.html";
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

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
package quests.Q10294_SevenSignsToTheMonasteryOfSilence;

import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

import quests.Q10293_SevenSignsForbiddenBookOfTheElmoreAdenKingdom.Q10293_SevenSignsForbiddenBookOfTheElmoreAdenKingdom;

/**
 * Seven Signs, To the Monastery of Silence (10294)
 * @author Adry_85
 */
public final class Q10294_SevenSignsToTheMonasteryOfSilence extends Quest
{
	// NPCs
	private static final int ELCADIA = 32784;
	private static final int ELCADIA_INSTANCE = 32787;
	private static final int ERIS_EVIL_THOUGHTS = 32792;
	private static final int SOLINAS_EVIL_THOUGHTS = 32793;
	private static final int JUDE_VAN_ETINA = 32797;
	private static final int RELIC_GUARDIAN = 32803;
	private static final int RELIC_WATCHER1 = 32804;
	private static final int RELIC_WATCHER2 = 32805;
	private static final int RELIC_WATCHER3 = 32806;
	private static final int RELIC_WATCHER4 = 32807;
	private static final int ODD_GLOBE = 32815;
	private static final int READING_DESK1 = 32821;
	private static final int READING_DESK2 = 32822;
	private static final int READING_DESK3 = 32823;
	private static final int READING_DESK4 = 32824;
	private static final int READING_DESK5 = 32825;
	private static final int READING_DESK6 = 32826;
	private static final int READING_DESK7 = 32827;
	private static final int READING_DESK8 = 32828;
	private static final int READING_DESK9 = 32829;
	private static final int READING_DESK10 = 32830;
	private static final int READING_DESK11 = 32831;
	private static final int READING_DESK12 = 32832;
	private static final int READING_DESK13 = 32833;
	private static final int READING_DESK14 = 32834;
	private static final int READING_DESK15 = 32835;
	private static final int READING_DESK16 = 32836;
	private static final int JUDE_EVIL_THOUGHTS = 32888;
	// Monsters
	private static final int SOLINA_LAY_BROTHER = 22125;
	private static final int GUIDE_SOLINA = 27415;
	// Misc
	private static final int MIN_LEVEL = 81;
	// Buffs
	private static final SkillHolder VAMPIRIC_RAGE = new SkillHolder(6727, 1);
	private static final SkillHolder RESIST_HOLY = new SkillHolder(6729, 1);
	private static final SkillHolder[] MAGE_BUFFS =
	{
		new SkillHolder(6714, 1), // Wind Walk of Elcadia
		new SkillHolder(6721, 1), // Empower of Elcadia
		new SkillHolder(6722, 1), // Acumen of Elcadia
		new SkillHolder(6717, 1), // Berserker Spirit of Elcadia
	};
	private static final SkillHolder[] WARRIOR_BUFFS =
	{
		new SkillHolder(6714, 1), // Wind Walk of Elcadia
		new SkillHolder(6715, 1), // Haste of Elcadia
		new SkillHolder(6716, 1), // Might of Elcadia
		new SkillHolder(6717, 1), // Berserker Spirit of Elcadia
	};
	
	public Q10294_SevenSignsToTheMonasteryOfSilence()
	{
		super(10294);
		addFirstTalkId(ELCADIA_INSTANCE);
		addStartNpc(ELCADIA, ODD_GLOBE, ELCADIA_INSTANCE, RELIC_GUARDIAN);
		addTalkId(ELCADIA, ELCADIA_INSTANCE, ERIS_EVIL_THOUGHTS, RELIC_GUARDIAN, ODD_GLOBE, READING_DESK1, READING_DESK2, READING_DESK3, READING_DESK4, READING_DESK5, READING_DESK6, READING_DESK7, READING_DESK8, READING_DESK9, READING_DESK10, READING_DESK11, READING_DESK12, READING_DESK13, READING_DESK14, READING_DESK15, READING_DESK16, JUDE_VAN_ETINA, SOLINAS_EVIL_THOUGHTS, RELIC_WATCHER1, RELIC_WATCHER2, RELIC_WATCHER3, RELIC_WATCHER4);
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
			case "32784-03.htm":
			case "32784-04.htm":
			{
				htmltext = event;
				break;
			}
			case "32784-05.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32792-02.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
			case "32792-03.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					htmltext = event;
				}
				break;
			}
			case "32792-04.html":
			case "32792-05.html":
			case "32792-06.html":
			case "32803-02.html":
			case "32803-03.html":
			case "32822-02.html":
			case "32804-02.html":
			case "32804-04.html":
			case "32804-06.html":
			case "32804-07.html":
			case "32804-08.html":
			case "32804-09.html":
			case "32804-10.html":
			case "32805-02.html":
			case "32805-04.html":
			case "32805-06.html":
			case "32805-07.html":
			case "32805-08.html":
			case "32805-09.html":
			case "32805-10.html":
			case "32806-02.html":
			case "32806-04.html":
			case "32806-06.html":
			case "32806-07.html":
			case "32806-08.html":
			case "32806-09.html":
			case "32806-10.html":
			case "32807-02.html":
			case "32807-04.html":
			case "32807-06.html":
			case "32807-07.html":
			case "32807-08.html":
			case "32807-09.html":
			case "32807-10.html":
			{
				if (qs.isCond(2))
				{
					htmltext = event;
				}
				break;
			}
			case "32792-08.html":
			{
				if (qs.isCond(3))
				{
					qs.unset("good1");
					qs.unset("good2");
					qs.unset("good3");
					qs.unset("good4");
					addExpAndSp(player, 25000000, 2500000);
					qs.exitQuest(false, true);
					htmltext = event;
				}
				break;
			}
			case "BUFF":
			{
				npc.setTarget(player);
				if (player.isMageClass())
				{
					for (SkillHolder skill : MAGE_BUFFS)
					{
						npc.doSimultaneousCast(skill.getSkill());
					}
				}
				else
				{
					for (SkillHolder skill : WARRIOR_BUFFS)
					{
						npc.doSimultaneousCast(skill.getSkill());
					}
				}
				break;
			}
			case "RIGHT_BOOK1":
			{
				qs.set("good1", "1");
				npc.setDisplayEffect(1);
				startQuestTimer("SPAWN_MOBS", 22000, npc, player);
				htmltext = "32821-02.html";
				if (hasCheckedAllRightBooks(qs))
				{
					playMovie(player, Movie.SSQ2_HOLY_BURIAL_GROUND_CLOSING);
				}
				break;
			}
			case "RIGHT_BOOK2":
			{
				qs.set("good2", "1");
				npc.setDisplayEffect(1);
				npc.setTarget(player);
				npc.doCast(VAMPIRIC_RAGE.getSkill());
				htmltext = "32821-02.html";
				if (hasCheckedAllRightBooks(qs))
				{
					playMovie(player, Movie.SSQ2_HOLY_BURIAL_GROUND_CLOSING);
				}
				break;
			}
			case "RIGHT_BOOK3":
			{
				qs.set("good3", "1");
				npc.setDisplayEffect(1);
				final L2Npc jude = addSpawn(JUDE_VAN_ETINA, 85783, -253471, -8320, 65, false, 0, false, player.getInstanceId());
				jude.setTarget(player);
				jude.doCast(RESIST_HOLY.getSkill());
				htmltext = "32821-02.html";
				if (hasCheckedAllRightBooks(qs))
				{
					playMovie(player, Movie.SSQ2_HOLY_BURIAL_GROUND_CLOSING);
				}
				break;
			}
			case "RIGHT_BOOK4":
			{
				qs.set("good4", "1");
				npc.setDisplayEffect(1);
				final L2Npc solina = addSpawn(SOLINAS_EVIL_THOUGHTS, 85793, -247581, -8320, 0, false, 0, false, player.getInstanceId());
				solina.setTarget(player);
				solina.doCast(RESIST_HOLY.getSkill());
				htmltext = "32821-02.html";
				if (hasCheckedAllRightBooks(qs))
				{
					playMovie(player, Movie.SSQ2_HOLY_BURIAL_GROUND_CLOSING);
				}
				break;
			}
			case "DONE1":
			{
				htmltext = (qs.getInt("good1") == 1) ? "32804-05.html" : "32804-03.html";
				break;
			}
			case "DONE2":
			{
				htmltext = (qs.getInt("good2") == 1) ? "32805-05.html" : "32805-03.html";
				break;
			}
			case "DONE3":
			{
				htmltext = (qs.getInt("good3") == 1) ? "32806-05.html" : "32806-03.html";
				break;
			}
			case "DONE4":
			{
				htmltext = (qs.getInt("good4") == 1) ? "32807-05.html" : "32807-03.html";
				break;
			}
			case "SPAWN_MOBS":
			{
				addSpawn(JUDE_EVIL_THOUGHTS, 88655, -250591, -8320, 144, false, 0, false, player.getInstanceId());
				addSpawn(GUIDE_SOLINA, 88655, -250591, -8320, 144, false, 0, false, player.getInstanceId());
				addSpawn(SOLINA_LAY_BROTHER, 88655, -250591, -8320, 144, false, 0, false, player.getInstanceId());
				addSpawn(SOLINA_LAY_BROTHER, 88655, -250591, -8320, 144, false, 0, false, player.getInstanceId());
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		return "32787.html";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		switch (npc.getId())
		{
			case ELCADIA:
			{
				if (qs.isCompleted())
				{
					htmltext = "32784-02.html";
				}
				else if (qs.isCreated())
				{
					qs = player.getQuestState(Q10293_SevenSignsForbiddenBookOfTheElmoreAdenKingdom.class.getSimpleName());
					htmltext = ((player.getLevel() >= MIN_LEVEL) && (qs != null) && qs.isCompleted()) ? "32784-01.htm" : "32784-07.htm";
				}
				else if (qs.isStarted() && qs.isCond(1))
				{
					htmltext = "32784-06.html";
				}
				break;
			}
			case ERIS_EVIL_THOUGHTS:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "32792-01.html";
						break;
					}
					case 2:
					{
						htmltext = "32792-04.html";
						break;
					}
					case 3:
					{
						htmltext = player.isSubClassActive() ? "32792-09.html" : "32792-07.html";
						break;
					}
				}
				break;
			}
			case RELIC_GUARDIAN:
			{
				if (qs.isCond(2))
				{
					if (hasCheckedAllRightBooks(qs))
					{
						qs.setCond(3, true);
						htmltext = "32803-04.html";
					}
					else
					{
						htmltext = "32803-01.html";
					}
				}
				else if (qs.isCond(3))
				{
					htmltext = "32803-05.html";
				}
				break;
			}
			case ODD_GLOBE:
			{
				if (qs.getCond() < 3)
				{
					htmltext = "32815-01.html";
				}
				break;
			}
			case ELCADIA_INSTANCE:
			{
				if (qs.isCond(1))
				{
					htmltext = "32787-01.html";
				}
				else if (qs.isCond(2))
				{
					htmltext = "32787-02.html";
				}
				break;
			}
			case READING_DESK2:
			case READING_DESK3:
			case READING_DESK4:
			case READING_DESK6:
			case READING_DESK7:
			case READING_DESK8:
			case READING_DESK10:
			case READING_DESK11:
			case READING_DESK12:
			case READING_DESK14:
			case READING_DESK15:
			case READING_DESK16:
			{
				if (qs.isCond(2))
				{
					htmltext = "32822-01.html";
				}
				break;
			}
			case READING_DESK1:
			{
				htmltext = (qs.getInt("good1") == 1) ? "32821-03.html" : "32821-01.html";
				break;
			}
			case READING_DESK5:
			{
				htmltext = (qs.getInt("good2") == 1) ? "32821-03.html" : "32825-01.html";
				break;
			}
			case READING_DESK9:
			{
				htmltext = (qs.getInt("good3") == 1) ? "32821-03.html" : "32829-01.html";
				break;
			}
			case READING_DESK13:
			{
				htmltext = (qs.getInt("good4") == 1) ? "32821-03.html" : "32833-01.html";
				break;
			}
			case SOLINAS_EVIL_THOUGHTS:
			case JUDE_VAN_ETINA:
			case RELIC_WATCHER1:
			case RELIC_WATCHER2:
			case RELIC_WATCHER3:
			case RELIC_WATCHER4:
			{
				if (qs.isCond(2))
				{
					htmltext = npc.getId() + "-01.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	private boolean hasCheckedAllRightBooks(QuestState qs)
	{
		return (qs.getInt("good1") == 1) && (qs.getInt("good2") == 1) && (qs.getInt("good3") == 1) && (qs.getInt("good4") == 1);
	}
}

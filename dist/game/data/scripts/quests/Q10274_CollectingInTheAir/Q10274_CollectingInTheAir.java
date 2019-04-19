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
package quests.Q10274_CollectingInTheAir;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.model.skills.Skill;

import quests.Q10273_GoodDayToFly.Q10273_GoodDayToFly;

/**
 * Collecting in the Air (10274)<br>
 * Original Jython script by Kerberos v1.0 on 2009/04/26.
 * @author nonom
 */
public class Q10274_CollectingInTheAir extends Quest
{
	// NPC
	private static final int LEKON = 32557;
	// Items
	private static final int SCROLL = 13844;
	private static final int RED = 13858;
	private static final int BLUE = 13859;
	private static final int GREEN = 13860;
	// Monsters
	private static final int MOBS[] =
	{
		18684, // Red Star Stone
		18685, // Red Star Stone
		18686, // Red Star Stone
		18687, // Blue Star Stone
		18688, // Blue Star Stone
		18689, // Blue Star Stone
		18690, // Green Star Stone
		18691, // Green Star Stone
		18692, // Green Star Stone
	};
	
	public Q10274_CollectingInTheAir()
	{
		super(10274);
		addStartNpc(LEKON);
		addTalkId(LEKON);
		addSkillSeeId(MOBS);
		registerQuestItems(SCROLL, RED, BLUE, GREEN);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		if (event.equals("32557-03.html"))
		{
			qs.startQuest();
			giveItems(player, SCROLL, 8);
		}
		return event;
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isSummon)
	{
		final QuestState qs = getQuestState(caster, false);
		if ((qs == null) || !qs.isStarted())
		{
			return null;
		}
		
		if (qs.isCond(1) && (skill.getId() == 2630))
		{
			switch (npc.getId())
			{
				case 18684:
				case 18685:
				case 18686:
				{
					giveItems(caster, RED, 1);
					break;
				}
				case 18687:
				case 18688:
				case 18689:
				{
					giveItems(caster, BLUE, 1);
					break;
				}
				case 18690:
				case 18691:
				case 18692:
				{
					giveItems(caster, GREEN, 1);
					break;
				}
			}
			playSound(caster, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			npc.doDie(caster);
		}
		return super.onSkillSee(npc, caster, skill, targets, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = getNoQuestMsg(player);
		QuestState qs = getQuestState(player, true);
		
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				htmltext = "32557-0a.html";
				break;
			}
			case State.CREATED:
			{
				qs = player.getQuestState(Q10273_GoodDayToFly.class.getSimpleName());
				if (qs != null)
				{
					htmltext = ((player.getLevel() >= 75) && qs.isCompleted()) ? "32557-01.htm" : "32557-00.html";
				}
				else
				{
					htmltext = "32557-00.html";
				}
				break;
			}
			case State.STARTED:
			{
				if ((getQuestItemsCount(player, RED) + getQuestItemsCount(player, BLUE) + getQuestItemsCount(player, GREEN)) >= 8)
				{
					htmltext = "32557-05.html";
					giveItems(player, 13728, 1);
					addExpAndSp(player, 25160, 2525);
					qs.exitQuest(false, true);
				}
				else
				{
					htmltext = "32557-04.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

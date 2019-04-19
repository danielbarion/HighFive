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
package quests.Q10273_GoodDayToFly;

import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

/**
 * Good Day to Fly (10273)<br>
 * Original Jython script by Kerberos v1.0 on 2009/04/25
 * @author nonom
 */
public class Q10273_GoodDayToFly extends Quest
{
	// NPC
	private static final int LEKON = 32557;
	// Monsters
	private static final int[] MOBS =
	{
		22614, // Vulture Rider
		22615, // Vulture Rider
	};
	// Item
	private static final int MARK = 13856;
	// Skills
	private static final SkillHolder AURA_BIRD_FALCON = new SkillHolder(5982, 1);
	private static final SkillHolder AURA_BIRD_OWL = new SkillHolder(5983, 1);
	
	public Q10273_GoodDayToFly()
	{
		super(10273);
		addStartNpc(LEKON);
		addTalkId(LEKON);
		addKillId(MOBS);
		registerQuestItems(MARK);
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
			case "32557-06.htm":
			{
				qs.startQuest();
				break;
			}
			case "32557-09.html":
			{
				qs.set("transform", "1");
				AURA_BIRD_FALCON.getSkill().applyEffects(player, player);
				break;
			}
			case "32557-10.html":
			{
				qs.set("transform", "2");
				AURA_BIRD_OWL.getSkill().applyEffects(player, player);
				break;
			}
			case "32557-13.html":
			{
				switch (qs.getInt("transform"))
				{
					case 1:
					{
						AURA_BIRD_FALCON.getSkill().applyEffects(player, player);
						break;
					}
					case 2:
					{
						AURA_BIRD_OWL.getSkill().applyEffects(player, player);
						break;
					}
				}
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs == null) || !qs.isStarted())
		{
			return null;
		}
		
		final long count = getQuestItemsCount(killer, MARK);
		if (qs.isCond(1) && (count < 5))
		{
			giveItems(killer, MARK, 1);
			if (count == 4)
			{
				qs.setCond(2, true);
			}
			else
			{
				playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		final int transform = qs.getInt("transform");
		switch (qs.getState())
		{
			case State.COMPLETED:
			{
				htmltext = "32557-0a.html";
				break;
			}
			case State.CREATED:
			{
				htmltext = (player.getLevel() < 75) ? "32557-00.html" : "32557-01.htm";
				break;
			}
			default:
			{
				if (getQuestItemsCount(player, MARK) >= 5)
				{
					htmltext = "32557-14.html";
					if (transform == 1)
					{
						giveItems(player, 13553, 1);
					}
					else if (transform == 2)
					{
						giveItems(player, 13554, 1);
					}
					giveItems(player, 13857, 1);
					addExpAndSp(player, 25160, 2525);
					qs.exitQuest(false, true);
				}
				else if (transform == 0)
				{
					htmltext = "32557-07.html";
				}
				else
				{
					htmltext = "32557-11.html";
				}
				break;
			}
		}
		return htmltext;
	}
}

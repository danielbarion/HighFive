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
package quests.Q00998_FallenAngelSelect;

import com.l2jmobius.gameserver.instancemanager.QuestManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;

import quests.Q00141_ShadowFoxPart3.Q00141_ShadowFoxPart3;
import quests.Q00142_FallenAngelRequestOfDawn.Q00142_FallenAngelRequestOfDawn;
import quests.Q00143_FallenAngelRequestOfDusk.Q00143_FallenAngelRequestOfDusk;

/**
 * Fallen Angel Select (998 - Custom)<br>
 * NOTE: This quest is used for start quest 142 or 143
 * @author Nono
 */
public class Q00998_FallenAngelSelect extends Quest
{
	// NPCs
	private static final int NATOOLS = 30894;
	// Misc
	private static final int MIN_LEVEL = 38;
	
	public Q00998_FallenAngelSelect()
	{
		super(998);
		setIsCustom(true);
		addStartNpc(NATOOLS);
		addTalkId(NATOOLS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		switch (event)
		{
			case "30894-01.html":
			case "30894-02.html":
			case "30894-03.html":
			{
				return event;
			}
			case "dawn":
			{
				startQuest(Q00142_FallenAngelRequestOfDawn.class.getSimpleName(), player);
				break;
			}
			case "dusk":
			{
				startQuest(Q00143_FallenAngelRequestOfDusk.class.getSimpleName(), player);
				break;
			}
		}
		return null;
	}
	
	private void startQuest(String name, L2PcInstance player)
	{
		final Quest q = QuestManager.getInstance().getQuest(name);
		if (q != null)
		{
			q.newQuestState(player);
			q.notifyEvent("30894-01.html", null, player);
			player.getQuestState(getName()).setState(State.COMPLETED);
		}
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		final QuestState qs = player.getQuestState(Q00141_ShadowFoxPart3.class.getSimpleName());
		if (!st.isStarted())
		{
			return getNoQuestMsg(player);
		}
		return ((player.getLevel() >= MIN_LEVEL) && qs.isCompleted()) ? "30894-01.html" : "30894-00.html";
	}
}
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
package quests.Q00634_InSearchOfFragmentsOfDimension;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * In Search of Fragments of Dimension (634)
 * @author Adry_85
 */
public final class Q00634_InSearchOfFragmentsOfDimension extends Quest
{
	// NPCs
	private static final int[] DIMENSIONAL_GATE_KEEPER =
	{
		31494,
		31495,
		31496,
		31497,
		31498,
		31499,
		31500,
		31501,
		31502,
		31503,
		31504,
		31505,
		31506,
		31507
	};
	
	// Item
	private static final int DIMENSIONAL_FRAGMENT = 7079;
	// Misc
	private static final int MIN_LEVEL = 20;
	// Monsters
	private static final int[] MOBS =
	{
		21208, // Hallowed Watchman
		21209, // Hallowed Seer
		21210, // Vault Guardian
		21211, // Vault Seer
		21212, // Hallowed Sentinel
		21213, // Hallowed Monk
		21214, // Vault Sentinel
		21215, // Vault Monk
		21216, // Overlord of the Holy Lands
		21217, // Hallowed Priest
		21218, // Vault Overlord
		21219, // Vault Priest
		21220, // Sepulcher Archon
		21221, // Sepulcher Inquisitor
		21222, // Sepulcher Archon
		21223, // Sepulcher Inquisitor
		21224, // Sepulcher Guardian
		21225, // Sepulcher Sage
		21226, // Sepulcher Guardian
		21227, // Sepulcher Sage
		21228, // Sepulcher Guard
		21229, // Sepulcher Preacher
		21230, // Sepulcher Guard
		21231, // Sepulcher Preacher
		21232, // Barrow Guardian
		21233, // Barrow Seer
		21234, // Grave Guardian
		21235, // Grave Seer
		21236, // Barrow Sentinel
		21237, // Barrow Monk
		21238, // Grave Sentinel
		21239, // Grave Monk
		21240, // Barrow Overlord
		21241, // Barrow Priest
		21242, // Grave Overlord
		21243, // Grave Priest
		21244, // Crypt Archon
		21245, // Crypt Inquisitor
		21246, // Tomb Archon
		21247, // Tomb Inquisitor
		21248, // Crypt Guardian
		21249, // Crypt Sage
		21250, // Tomb Guardian
		21251, // Tomb Sage
		21252, // Crypt Guard
		21253, // Crypt Preacher
		21254, // Tomb Guard
		21255, // Tomb Preacher
		21256, // Underground Werewolf
	};
	
	public Q00634_InSearchOfFragmentsOfDimension()
	{
		super(634);
		addStartNpc(DIMENSIONAL_GATE_KEEPER);
		addTalkId(DIMENSIONAL_GATE_KEEPER);
		addKillId(MOBS);
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
			case "31494-02.htm":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "31494-05.html":
			case "31494-06.html":
			{
				if (qs.isStarted())
				{
					htmltext = event;
				}
				break;
			}
			case "31494-07.html":
			{
				if (qs.isStarted())
				{
					qs.exitQuest(true, true);
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
			final int i0 = (int) ((0.15 * npc.getLevel()) + 1.6);
			if (getRandom(100) < 10)
			{
				giveItemRandomly(qs.getPlayer(), npc, DIMENSIONAL_FRAGMENT, i0, 0, 1.0, true);
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (qs.isCreated())
		{
			htmltext = (player.getLevel() >= MIN_LEVEL) ? "31494-01.htm" : "31494-03.htm";
		}
		else if (qs.isStarted())
		{
			htmltext = "31494-04.html";
		}
		return htmltext;
	}
}

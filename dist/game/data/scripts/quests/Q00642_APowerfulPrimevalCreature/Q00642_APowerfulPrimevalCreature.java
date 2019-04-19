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
package quests.Q00642_APowerfulPrimevalCreature;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * A Powerful Primeval Creature (642)
 * @author Adry_85
 */
public class Q00642_APowerfulPrimevalCreature extends Quest
{
	// NPC
	private static final int DINN = 32105;
	// Items
	private static final int DINOSAUR_TISSUE = 8774;
	private static final int DINOSAUR_EGG = 8775;
	// Misc
	private static final int MIN_LEVEL = 75;
	// Mobs
	private static final int ANCIENT_EGG = 18344;
	
	private static final Map<Integer, Double> MOBS_TISSUE = new HashMap<>();
	static
	{
		MOBS_TISSUE.put(22196, 0.309); // Velociraptor
		MOBS_TISSUE.put(22197, 0.309); // Velociraptor
		MOBS_TISSUE.put(22198, 0.309); // Velociraptor
		MOBS_TISSUE.put(22199, 0.309); // Pterosaur
		MOBS_TISSUE.put(22215, 0.988); // Tyrannosaurus
		MOBS_TISSUE.put(22216, 0.988); // Tyrannosaurus
		MOBS_TISSUE.put(22217, 0.988); // Tyrannosaurus
		MOBS_TISSUE.put(22218, 0.309); // Velociraptor
		MOBS_TISSUE.put(22223, 0.309); // Velociraptor
	}
	
	public Q00642_APowerfulPrimevalCreature()
	{
		super(642);
		addStartNpc(DINN);
		addTalkId(DINN);
		addKillId(ANCIENT_EGG);
		addKillId(MOBS_TISSUE.keySet());
		registerQuestItems(DINOSAUR_TISSUE, DINOSAUR_EGG);
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
			case "32105-05.html":
			{
				qs.startQuest();
				break;
			}
			case "32105-06.htm":
			{
				qs.exitQuest(true);
				break;
			}
			case "32105-09.html":
			{
				if (hasQuestItems(player, DINOSAUR_TISSUE))
				{
					giveAdena(player, 5000 * getQuestItemsCount(player, DINOSAUR_TISSUE), true);
					takeItems(player, DINOSAUR_TISSUE, -1);
				}
				else
				{
					htmltext = "32105-14.html";
				}
				break;
			}
			case "exit":
			{
				if (hasQuestItems(player, DINOSAUR_TISSUE))
				{
					giveAdena(player, 5000 * getQuestItemsCount(player, DINOSAUR_TISSUE), true);
					qs.exitQuest(true, true);
					htmltext = "32105-12.html";
				}
				else
				{
					qs.exitQuest(true, true);
					htmltext = "32105-13.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, -1, 3, npc);
		
		if (qs == null)
		{
			return null;
		}
		
		final int npcId = npc.getId();
		
		if (MOBS_TISSUE.containsKey(npcId))
		{
			giveItemRandomly(qs.getPlayer(), npc, DINOSAUR_TISSUE, 1, 0, MOBS_TISSUE.get(npcId), true);
		}
		else
		{
			giveItemRandomly(qs.getPlayer(), npc, DINOSAUR_EGG, 1, 0, 1.0, true);
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
			htmltext = player.getLevel() < MIN_LEVEL ? "32105-01.htm" : "32105-02.htm";
		}
		else if (qs.isStarted())
		{
			htmltext = hasAtLeastOneQuestItem(player, DINOSAUR_TISSUE, DINOSAUR_EGG) ? "32105-08.html" : "32105-07.html";
		}
		return htmltext;
	}
}

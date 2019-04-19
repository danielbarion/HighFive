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
package instances.NornilsGardenQuest;

import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.QuestState;

import instances.AbstractInstance;
import quests.Q00236_SeedsOfChaos.Q00236_SeedsOfChaos;

/**
 * Nornil's Garden Quest instant zone.
 * @author Zoey76
 */
public final class NornilsGardenQuest extends AbstractInstance
{
	// NPCs
	private static final int RODENPICULA = 32237;
	private static final int MOTHER_NORNIL = 32239;
	// Location
	private static final Location ENTER_LOC = new Location(-119538, 87177, -12592);
	// Misc
	private static final int TEMPLATE_ID = 12;
	
	public NornilsGardenQuest()
	{
		addStartNpc(RODENPICULA, MOTHER_NORNIL);
		addTalkId(RODENPICULA, MOTHER_NORNIL);
		addFirstTalkId(RODENPICULA, MOTHER_NORNIL);
	}
	
	@Override
	protected boolean checkConditions(L2PcInstance player)
	{
		final QuestState qs = player.getQuestState(Q00236_SeedsOfChaos.class.getSimpleName());
		return (qs != null) && (qs.getMemoState() >= 40) && (qs.getMemoState() <= 45);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		final QuestState q236 = player.getQuestState(Q00236_SeedsOfChaos.class.getSimpleName());
		switch (event)
		{
			case "enter":
			{
				if (checkConditions(player))
				{
					final Location originLoc = player.getLocation();
					enterInstance(player, TEMPLATE_ID);
					InstanceManager.getInstance().getPlayerWorld(player).setParameter("ORIGIN_LOC", originLoc);
					q236.setCond(16, true);
					htmltext = "32190-02.html";
				}
				else
				{
					htmltext = "32190-03.html";
				}
				break;
			}
			case "exit":
			{
				if ((q236 != null) && q236.isCompleted())
				{
					final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
					world.removeAllowed(player);
					finishInstance(world, 5000);
					player.setInstanceId(0);
					player.teleToLocation(world.getParameters().getLocation("ORIGIN_LOC"));
					htmltext = "32239-03.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	protected void onEnterInstance(L2PcInstance player, InstanceWorld world, boolean firstEntrance)
	{
		if (firstEntrance)
		{
			world.addAllowed(player);
		}
		teleportPlayer(player, ENTER_LOC, world.getInstanceId(), false);
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = null;
		final QuestState q236 = player.getQuestState(Q00236_SeedsOfChaos.class.getSimpleName());
		switch (npc.getId())
		{
			case RODENPICULA:
			{
				htmltext = (q236 != null) && (q236.isCompleted()) ? "32237-02.html" : "32237-01.html";
				break;
			}
			case MOTHER_NORNIL:
			{
				htmltext = (q236 != null) && (q236.isCompleted()) ? "32239-02.html" : "32239-01.html";
				break;
			}
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new NornilsGardenQuest();
	}
}

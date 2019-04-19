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
package quests.Q00694_BreakThroughTheHallOfSuffering;

import java.util.Calendar;

import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public final class Q00694_BreakThroughTheHallOfSuffering extends Quest
{
	private static final int TEPIOS = 32603;
	private static final int TEPIOS2 = 32530;
	private static final int MARK = 13691;
	private static final int SOE = 736;
	private static final int TEMPLATE_ID = 115;
	
	public Q00694_BreakThroughTheHallOfSuffering()
	{
		super(694);
		addStartNpc(TEPIOS);
		addTalkId(TEPIOS);
		addTalkId(TEPIOS2);
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final String htmltext = event;
		final QuestState qs = player.getQuestState(getName());
		if (qs == null)
		{
			return htmltext;
		}
		
		if (event.equals("32603-02.html"))
		{
			qs.set("cond", "1");
			qs.setState(State.STARTED);
			playSound(player, "ItemSound.quest_accept");
		}
		return htmltext;
	}
	
	@Override
	public final String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				if ((player.getLevel() >= 75) && (player.getLevel() <= 82))
				{
					htmltext = "32603-01.htm";
				}
				else
				{
					htmltext = "32603-00.html";
					qs.exitQuest(true);
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case TEPIOS:
					{
						htmltext = "32603-01a.html";
						break;
					}
					case TEPIOS2:
					{
						final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
						if ((world != null) && (world.getTemplateId() == TEMPLATE_ID))
						{
							final int tag = world.getParameters().getInt("tag", -1);
							if (tag == -1)
							{
								htmltext = "32530-11.html";
							}
							else if ((player.getParty() != null) && (player.getParty().getLeaderObjectId() == player.getObjectId()))
							{
								for (L2PcInstance member : player.getParty().getMembers())
								{
									final QuestState qs1 = member.getQuestState(getName());
									if (qs1 != null)
									{
										if (tag == 13777)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13777, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-00.html";
											finishInstance(player);
										}
										else if (tag == 13778)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13778, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-01.html";
											finishInstance(player);
										}
										else if (tag == 13779)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13779, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-02.html";
											finishInstance(player);
										}
										else if (tag == 13780)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13780, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-03.html";
											finishInstance(player);
										}
										else if (tag == 13781)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13781, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-04.html";
											finishInstance(player);
										}
										else if (tag == 13782)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13782, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-05.html";
											finishInstance(player);
										}
										else if (tag == 13783)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13783, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-06.html";
											finishInstance(player);
										}
										else if (tag == 13784)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13784, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-07.html";
											finishInstance(player);
										}
										else if (tag == 13785)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13785, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-08.html";
											finishInstance(player);
										}
										else if (tag == 13786)
										{
											if (getQuestItemsCount(member, MARK) == 0)
											{
												giveItems(member, MARK, 1);
											}
											giveItems(member, 13786, 1);
											giveItems(member, SOE, 1);
											qs1.unset("cond");
											qs1.exitQuest(true);
											playSound(member, "ItemSound.quest_finish");
											htmltext = "32530-09.html";
											finishInstance(player);
										}
										else
										{
											htmltext = "32530-11.html";
										}
									}
								}
							}
							else
							{
								return "32530-10.html";
							}
						}
						else
						{
							htmltext = "32530-11.html";
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	private static void finishInstance(L2PcInstance player)
	{
		final InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		
		final Calendar reenter = Calendar.getInstance();
		reenter.set(Calendar.MINUTE, 30);
		
		if (reenter.get(Calendar.HOUR_OF_DAY) >= 6)
		{
			reenter.add(Calendar.DATE, 1);
		}
		reenter.set(Calendar.HOUR_OF_DAY, 6);
		
		final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.INSTANT_ZONE_S1_S_ENTRY_HAS_BEEN_RESTRICTED_YOU_CAN_CHECK_THE_NEXT_POSSIBLE_ENTRY_TIME_BY_USING_THE_COMMAND_INSTANCEZONE);
		sm.addInstanceName(TEMPLATE_ID);
		
		for (L2PcInstance plr : world.getAllowed())
		{
			if (plr != null)
			{
				InstanceManager.getInstance().setInstanceTime(plr.getObjectId(), TEMPLATE_ID, reenter.getTimeInMillis());
				if (plr.isOnline())
				{
					plr.sendPacket(sm);
				}
			}
		}
		final Instance inst = InstanceManager.getInstance().getInstance(world.getInstanceId());
		inst.setDuration(5 * 60000);
		inst.setEmptyDestroyTime(0);
	}
}
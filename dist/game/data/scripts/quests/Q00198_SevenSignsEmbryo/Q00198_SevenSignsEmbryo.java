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
package quests.Q00198_SevenSignsEmbryo;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.itemcontainer.Inventory;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;

import quests.Q00197_SevenSignsTheSacredBookOfSeal.Q00197_SevenSignsTheSacredBookOfSeal;

/**
 * Seven Signs, Embryo (198)
 * @author Adry_85
 */
public final class Q00198_SevenSignsEmbryo extends Quest
{
	// NPCs
	private static final int SHILENS_EVIL_THOUGHTS = 27346;
	private static final int WOOD = 32593;
	private static final int FRANZ = 32597;
	// Items
	private static final int SCULPTURE_OF_DOUBT = 14355;
	private static final int DAWNS_BRACELET = 15312;
	// Misc
	private static final int MIN_LEVEL = 79;
	private boolean isBusy = false;
	// Skill
	private static final SkillHolder NPC_HEAL = new SkillHolder(4065, 8);
	
	public Q00198_SevenSignsEmbryo()
	{
		super(198);
		addStartNpc(WOOD);
		addTalkId(WOOD, FRANZ);
		addKillId(SHILENS_EVIL_THOUGHTS);
		registerQuestItems(SCULPTURE_OF_DOUBT);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ((npc.getId() == SHILENS_EVIL_THOUGHTS) && "despawn".equals(event))
		{
			if (!npc.isDead())
			{
				isBusy = false;
				npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.NEXT_TIME_YOU_WILL_NOT_ESCAPE));
				npc.deleteMe();
			}
			return super.onAdvEvent(event, npc, player);
		}
		
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "32593-02.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32597-02.html":
			case "32597-03.html":
			case "32597-04.html":
			{
				if (qs.isCond(1))
				{
					htmltext = event;
				}
				break;
			}
			case "fight":
			{
				htmltext = "32597-05.html";
				if (qs.isCond(1))
				{
					isBusy = true;
					npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.S1_THAT_STRANGER_MUST_BE_DEFEATED_HERE_IS_THE_ULTIMATE_HELP).addStringParameter(player.getName()));
					startQuestTimer("heal", 30000 - getRandom(20000), npc, player);
					final L2MonsterInstance monster = (L2MonsterInstance) addSpawn(SHILENS_EVIL_THOUGHTS, -23734, -9184, -5384, 0, false, 0, false, npc.getInstanceId());
					monster.broadcastPacket(new NpcSay(monster.getObjectId(), ChatType.NPC_GENERAL, monster.getId(), NpcStringId.YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM));
					monster.setRunning();
					monster.addDamageHate(player, 0, 999);
					monster.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
					startQuestTimer("despawn", 300000, monster, null);
				}
				break;
			}
			case "heal":
			{
				if (!npc.isInsideRadius3D(player, 600))
				{
					final NpcSay ns = new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.LOOK_HERE_S1_DON_T_FALL_TOO_FAR_BEHIND);
					ns.addStringParameter(player.getName());
					npc.broadcastPacket(ns);
				}
				else if (!player.isDead())
				{
					npc.setTarget(player);
					npc.doCast(NPC_HEAL.getSkill());
				}
				startQuestTimer("heal", 30000 - getRandom(20000), npc, player);
				break;
			}
			case "32597-08.html":
			case "32597-09.html":
			case "32597-10.html":
			{
				if (qs.isCond(2) && hasQuestItems(player, SCULPTURE_OF_DOUBT))
				{
					htmltext = event;
				}
				break;
			}
			case "32597-11.html":
			{
				if (qs.isCond(2) && hasQuestItems(player, SCULPTURE_OF_DOUBT))
				{
					takeItems(player, SCULPTURE_OF_DOUBT, -1);
					qs.setCond(3, true);
					htmltext = event;
					npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.WE_WILL_BE_WITH_YOU_ALWAYS));
				}
				break;
			}
			case "32617-02.html":
			{
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final L2PcInstance partyMember = getRandomPartyMember(player, 1);
		if (partyMember == null)
		{
			return null;
		}
		
		final QuestState qs = getQuestState(partyMember, false);
		if (npc.isInsideRadius3D(partyMember, Config.ALT_PARTY_RANGE))
		{
			giveItems(partyMember, SCULPTURE_OF_DOUBT, 1);
			qs.setCond(2, true);
		}
		
		isBusy = false;
		cancelQuestTimers("despawn");
		cancelQuestTimers("heal");
		npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.S1_YOU_MAY_HAVE_WON_THIS_TIME_BUT_NEXT_TIME_I_WILL_SURELY_CAPTURE_YOU).addStringParameter(partyMember.getName()));
		npc.deleteMe();
		playMovie(partyMember, Movie.SSQ_EMBRYO);
		return super.onKill(npc, player, isSummon);
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
				if (npc.getId() == WOOD)
				{
					qs = player.getQuestState(Q00197_SevenSignsTheSacredBookOfSeal.class.getSimpleName());
					htmltext = ((player.getLevel() >= MIN_LEVEL) && (qs != null) && qs.isCompleted()) ? "32593-01.htm" : "32593-03.html";
				}
				break;
			}
			case State.STARTED:
			{
				if (npc.getId() == WOOD)
				{
					if ((qs.getCond() > 0) && (qs.getCond() < 3))
					{
						htmltext = "32593-04.html";
					}
					else if (qs.isCond(3))
					{
						if (player.getLevel() >= MIN_LEVEL)
						{
							addExpAndSp(player, 315108090, 34906059);
							giveItems(player, DAWNS_BRACELET, 1);
							giveItems(player, Inventory.ANCIENT_ADENA_ID, 1500000);
							qs.exitQuest(false, true);
							htmltext = "32593-05.html";
						}
						else
						{
							htmltext = "level_check.html";
						}
					}
				}
				else if (npc.getId() == FRANZ)
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = isBusy ? "32597-06.html" : "32597-01.html";
							break;
						}
						case 2:
						{
							if (hasQuestItems(player, SCULPTURE_OF_DOUBT))
							{
								htmltext = "32597-07.html";
							}
							break;
						}
						case 3:
						{
							htmltext = "32597-12.html";
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

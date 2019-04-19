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
package quests.Q00638_SeekersOfTheHolyGrail;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemChanceHolder;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;

/**
 * Seekers Of The Holy Grail (638)
 * @author netvirus
 */
public final class Q00638_SeekersOfTheHolyGrail extends Quest
{
	private static class DropInfo extends ItemChanceHolder
	{
		private final int _keyId;
		private final int _keyChance;
		private final int _keyCount;
		
		public DropInfo(int itemId, double chance)
		{
			this(itemId, chance, 0, 0, 0);
		}
		
		public DropInfo(int itemId, double chance, int keyId, int keyChance, int count)
		{
			super(itemId, chance);
			_keyId = keyId;
			_keyChance = keyChance;
			_keyCount = count;
		}
		
		public int getKeyId()
		{
			return _keyId;
		}
		
		public int getKeyChance()
		{
			return _keyChance;
		}
		
		public int getKeyCount()
		{
			return _keyCount;
		}
	}
	
	// NPC
	private static final int INNOCENTIN = 31328;
	// Items
	private static final int TOTEM = 8068;
	private static final int ANTEROOM_KEY = 8273;
	private static final int CHAPEL_KEY = 8274;
	private static final int KEY_OF_DARKNESS = 8275;
	// Misc
	private static final int MIN_LVL = 73;
	private static final int TOTEMS_REQUIRED_COUNT = 2000;
	// Rewards
	private static final int SCROLL_ENCHANT_W_S = 959;
	private static final int SCROLL_ENCHANT_A_S = 960;
	// Mobs
	private static final Map<Integer, DropInfo> MOBS_DROP_CHANCES = new HashMap<>();
	static
	{
		MOBS_DROP_CHANCES.put(22136, new DropInfo(TOTEM, 0.55)); // Gatekeeper Zombie
		MOBS_DROP_CHANCES.put(22137, new DropInfo(TOTEM, 0.06)); // Penance Guard
		MOBS_DROP_CHANCES.put(22138, new DropInfo(TOTEM, 0.06)); // Chapel Guard
		MOBS_DROP_CHANCES.put(22139, new DropInfo(TOTEM, 0.54)); // Old Aristocrat's Soldier
		MOBS_DROP_CHANCES.put(22140, new DropInfo(TOTEM, 0.54)); // Zombie Worker
		MOBS_DROP_CHANCES.put(22141, new DropInfo(TOTEM, 0.55)); // Forgotten Victim
		MOBS_DROP_CHANCES.put(22142, new DropInfo(TOTEM, 0.54)); // Triol's Layperson
		MOBS_DROP_CHANCES.put(22143, new DropInfo(TOTEM, 0.62, CHAPEL_KEY, 100, 1)); // Triol's Believer
		MOBS_DROP_CHANCES.put(22144, new DropInfo(TOTEM, 0.54)); // Resurrected Temple Knight
		MOBS_DROP_CHANCES.put(22145, new DropInfo(TOTEM, 0.53)); // Ritual Sacrifice
		MOBS_DROP_CHANCES.put(22146, new DropInfo(TOTEM, 0.54, KEY_OF_DARKNESS, 10, 1)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22147, new DropInfo(TOTEM, 0.55)); // Ritual Offering
		MOBS_DROP_CHANCES.put(22148, new DropInfo(TOTEM, 0.45)); // Triol's Believer
		MOBS_DROP_CHANCES.put(22149, new DropInfo(TOTEM, 0.54, ANTEROOM_KEY, 100, 6)); // Ritual Offering
		MOBS_DROP_CHANCES.put(22150, new DropInfo(TOTEM, 0.46)); // Triol's Believer
		MOBS_DROP_CHANCES.put(22151, new DropInfo(TOTEM, 0.62, KEY_OF_DARKNESS, 10, 1)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22152, new DropInfo(TOTEM, 0.55)); // Temple Guard
		MOBS_DROP_CHANCES.put(22153, new DropInfo(TOTEM, 0.54)); // Temple Guard Captain
		MOBS_DROP_CHANCES.put(22154, new DropInfo(TOTEM, 0.53)); // Ritual Sacrifice
		MOBS_DROP_CHANCES.put(22155, new DropInfo(TOTEM, 0.75)); // Triol's High Priest
		MOBS_DROP_CHANCES.put(22156, new DropInfo(TOTEM, 0.67)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22157, new DropInfo(TOTEM, 0.66)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22158, new DropInfo(TOTEM, 0.67)); // Triol's Believer
		MOBS_DROP_CHANCES.put(22159, new DropInfo(TOTEM, 0.75)); // Triol's High Priest
		MOBS_DROP_CHANCES.put(22160, new DropInfo(TOTEM, 0.67)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22161, new DropInfo(TOTEM, 0.78)); // Ritual Sacrifice
		MOBS_DROP_CHANCES.put(22162, new DropInfo(TOTEM, 0.67)); // Triol's Believer
		MOBS_DROP_CHANCES.put(22163, new DropInfo(TOTEM, 0.87)); // Triol's High Priest
		MOBS_DROP_CHANCES.put(22164, new DropInfo(TOTEM, 0.67)); // Triol's Believer
		MOBS_DROP_CHANCES.put(22165, new DropInfo(TOTEM, 0.66)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22166, new DropInfo(TOTEM, 0.66)); // Triol's Believer
		MOBS_DROP_CHANCES.put(22167, new DropInfo(TOTEM, 0.75)); // Triol's High Priest
		MOBS_DROP_CHANCES.put(22168, new DropInfo(TOTEM, 0.66)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22169, new DropInfo(TOTEM, 0.78)); // Ritual Sacrifice
		MOBS_DROP_CHANCES.put(22170, new DropInfo(TOTEM, 0.67)); // Triol's Believer
		MOBS_DROP_CHANCES.put(22171, new DropInfo(TOTEM, 0.87)); // Triol's High Priest
		MOBS_DROP_CHANCES.put(22172, new DropInfo(TOTEM, 0.78)); // Ritual Sacrifice
		MOBS_DROP_CHANCES.put(22173, new DropInfo(TOTEM, 0.66)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22174, new DropInfo(TOTEM, 0.67)); // Triol's Priest
		MOBS_DROP_CHANCES.put(22175, new DropInfo(TOTEM, 0.03)); // Andreas' Captain of the Royal Guard
		MOBS_DROP_CHANCES.put(22176, new DropInfo(TOTEM, 0.03)); // Andreas' Royal Guards
		MOBS_DROP_CHANCES.put(22188, new DropInfo(TOTEM, 0.03)); // Andreas' Captain of the Royal Guard
		MOBS_DROP_CHANCES.put(22189, new DropInfo(TOTEM, 0.03)); // Andreas' Royal Guards
		MOBS_DROP_CHANCES.put(22190, new DropInfo(TOTEM, 0.03)); // Ritual Sacrifice
		MOBS_DROP_CHANCES.put(22191, new DropInfo(TOTEM, 0.03)); // Andreas' Captain of the Royal Guard
		MOBS_DROP_CHANCES.put(22192, new DropInfo(TOTEM, 0.03)); // Andreas' Royal Guards
		MOBS_DROP_CHANCES.put(22193, new DropInfo(TOTEM, 0.03)); // Andreas' Royal Guards
		MOBS_DROP_CHANCES.put(22194, new DropInfo(TOTEM, 0.03)); // Penance Guard
		MOBS_DROP_CHANCES.put(22194, new DropInfo(TOTEM, 0.03)); // Ritual Sacrifice
	}
	
	public Q00638_SeekersOfTheHolyGrail()
	{
		super(638);
		addStartNpc(INNOCENTIN);
		addTalkId(INNOCENTIN);
		addKillId(MOBS_DROP_CHANCES.keySet());
		registerQuestItems(TOTEM);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "31328-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "31328-06.html":
			{
				if (qs.isStarted())
				{
					htmltext = event;
				}
				break;
			}
			case "reward":
			{
				if (qs.isStarted() && (getQuestItemsCount(player, TOTEM) >= TOTEMS_REQUIRED_COUNT))
				{
					if (getRandom(100) < 80)
					{
						if (getRandomBoolean())
						{
							rewardItems(player, SCROLL_ENCHANT_A_S, 1);
						}
						else
						{
							rewardItems(player, SCROLL_ENCHANT_W_S, 1);
						}
						htmltext = "31328-07.html";
					}
					else
					{
						giveAdena(player, 3576000, true);
						htmltext = "31328-08.html";
					}
					takeItems(player, TOTEM, 2000);
				}
				break;
			}
			case "31328-09.html":
			{
				if (qs.isStarted())
				{
					qs.exitQuest(true, true);
					htmltext = "31328-09.html";
				}
			}
			
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		final QuestState qs = getRandomPartyMemberState(killer, -1, 3, npc);
		if (qs != null)
		{
			final DropInfo info = MOBS_DROP_CHANCES.get(npc.getId());
			if (giveItemRandomly(qs.getPlayer(), npc, info.getId(), 1, 0, info.getChance(), true))
			{
				if ((info.getKeyId() > 0) && (getRandom(100) < info.getKeyChance()))
				{
					npc.dropItem(qs.getPlayer(), info.getKeyId(), info.getKeyCount());
				}
			}
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
			htmltext = ((player.getLevel() >= MIN_LVL) ? "31328-01.htm" : "31328-02.htm");
		}
		else if (qs.isStarted())
		{
			htmltext = ((getQuestItemsCount(player, TOTEM) >= TOTEMS_REQUIRED_COUNT) ? "31328-04.html" : "31328-05.html");
		}
		return htmltext;
	}
}

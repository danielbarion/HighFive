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
package quests.Q00456_DontKnowDontCare;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.CommonUtil;
import com.l2jmobius.gameserver.datatables.ItemTable;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.enums.QuestType;
import com.l2jmobius.gameserver.model.AggroInfo;
import com.l2jmobius.gameserver.model.L2CommandChannel;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.L2Item;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Util;

/**
 * Don't Know, Don't Care (456)
 * @author lion, ivantotov, jurchiks
 */
public final class Q00456_DontKnowDontCare extends Quest
{
	// NPCs
	// @formatter:off
	private static final int[] SEPARATED_SOUL =
	{
		32864, 32865, 32866, 32867, 32868, 32869, 32870, 32891
	};
	// @formatter:on
	private static final int DRAKE_LORD_CORPSE = 32884;
	private static final int BEHEMOTH_LEADER_CORPSE = 32885;
	private static final int DRAGON_BEAST_CORPSE = 32886;
	// Items
	private static final int DRAKE_LORD_ESSENCE = 17251;
	private static final int BEHEMOTH_LEADER_ESSENCE = 17252;
	private static final int DRAGON_BEAST_ESSENCE = 17253;
	// Misc
	private static final int MIN_PLAYERS = 18;
	private static final int MIN_LEVEL = 80;
	private static final Map<Integer, Integer> MONSTER_NPCS = new HashMap<>();
	private static final Map<Integer, Integer> MONSTER_ESSENCES = new HashMap<>();
	static
	{
		MONSTER_NPCS.put(25725, DRAKE_LORD_CORPSE);
		MONSTER_NPCS.put(25726, BEHEMOTH_LEADER_CORPSE);
		MONSTER_NPCS.put(25727, DRAGON_BEAST_CORPSE);
		MONSTER_ESSENCES.put(DRAKE_LORD_CORPSE, DRAKE_LORD_ESSENCE);
		MONSTER_ESSENCES.put(BEHEMOTH_LEADER_CORPSE, BEHEMOTH_LEADER_ESSENCE);
		MONSTER_ESSENCES.put(DRAGON_BEAST_CORPSE, DRAGON_BEAST_ESSENCE);
	}
	
	// Rewards
	private static final int[] WEAPONS =
	{
		15558, // Periel Sword
		15559, // Skull Edge
		15560, // Vigwik Axe
		15561, // Devilish Maul
		15562, // Feather Eye Blade
		15563, // Octo Claw
		15564, // Doubletop Spear
		15565, // Rising Star
		15566, // Black Visage
		15567, // Veniplant Sword
		15568, // Skull Carnium Bow
		15569, // Gemtail Rapier
		15570, // Finale Blade
		15571, // Dominion Crossbow
	};
	private static final int[] ARMOR =
	{
		15743, // Sealed Vorpal Helmet
		15746, // Sealed Vorpal Breastplate
		15749, // Sealed Vorpal Gaiters
		15752, // Sealed Vorpal Gauntlets
		15755, // Sealed Vorpal Boots
		15758, // Sealed Vorpal Shield
		15744, // Sealed Vorpal Leather Helmet
		15747, // Sealed Vorpal Leather Breastplate
		15750, // Sealed Vorpal Leather Leggings
		15753, // Sealed Vorpal Leather Gloves
		15756, // Sealed Vorpal Leather Boots
		15745, // Sealed Vorpal Circlet
		15748, // Sealed Vorpal Tunic
		15751, // Sealed Vorpal Stockings
		15754, // Sealed Vorpal Gloves
		15757, // Sealed Vorpal Shoes
		15759, // Sealed Vorpal Sigil
	};
	private static final int[] ACCESSORIES =
	{
		15763, // Sealed Vorpal Ring
		15764, // Sealed Vorpal Earring
		15765, // Sealed Vorpal Necklace
	};
	private static final int[] ATTRIBUTE_CRYSTALS =
	{
		9552, // Fire Crystal
		9553, // Water Crystal
		9554, // Earth Crystal
		9555, // Wind Crystal
		9556, // Dark Crystal
		9557, // Holy Crystal
	};
	private static final int BLESSED_SCROLL_ENCHANT_WEAPON_S = 6577;
	private static final int BLESSED_SCROLL_ENCHANT_ARMOR_S = 6578;
	private static final int SCROLL_ENCHANT_WEAPON_S = 959;
	private static final int GEMSTONE_S = 2134;
	private final Map<Integer, Set<Integer>> allowedPlayerMap = new HashMap<>();
	
	public Q00456_DontKnowDontCare()
	{
		super(456);
		addStartNpc(SEPARATED_SOUL);
		addTalkId(SEPARATED_SOUL);
		addFirstTalkId(DRAKE_LORD_CORPSE, BEHEMOTH_LEADER_CORPSE, DRAGON_BEAST_CORPSE);
		addTalkId(DRAKE_LORD_CORPSE, BEHEMOTH_LEADER_CORPSE, DRAGON_BEAST_CORPSE);
		addKillId(MONSTER_NPCS.keySet());
		registerQuestItems(DRAKE_LORD_ESSENCE, BEHEMOTH_LEADER_ESSENCE, DRAGON_BEAST_ESSENCE);
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		final Set<Integer> allowedPlayers = allowedPlayerMap.get(npc.getObjectId());
		
		if ((qs == null) || !qs.isCond(1) || (allowedPlayers == null) || !allowedPlayers.contains(player.getObjectId()))
		{
			return npc.getId() + "-02.html";
		}
		
		final int essence = MONSTER_ESSENCES.get(npc.getId());
		final String htmltext;
		
		if (hasQuestItems(player, essence))
		{
			htmltext = npc.getId() + "-03.html";
		}
		else
		{
			giveItems(player, essence, 1);
			htmltext = npc.getId() + "-01.html";
			
			if (hasQuestItems(player, getRegisteredItemIds()))
			{
				qs.setCond(2, true);
			}
			else
			{
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (CommonUtil.contains(SEPARATED_SOUL, npc.getId()))
		{
			switch (qs.getState())
			{
				case State.COMPLETED:
				{
					if (!qs.isNowAvailable())
					{
						htmltext = "32864-02.html";
						break;
					}
					qs.setState(State.CREATED);
					// intentional fall-through
				}
				case State.CREATED:
				{
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "32864-01.htm" : "32864-03.html";
					break;
				}
				case State.STARTED:
				{
					switch (qs.getCond())
					{
						case 1:
						{
							htmltext = hasAtLeastOneQuestItem(player, getRegisteredItemIds()) ? "32864-09.html" : "32864-08.html";
							break;
						}
						case 2:
						{
							if (hasQuestItems(player, getRegisteredItemIds()))
							{
								rewardPlayer(player, npc);
								qs.exitQuest(QuestType.DAILY, true);
								htmltext = "32864-10.html";
							}
							break;
						}
					}
					break;
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		
		switch (event)
		{
			case "32864-04.htm":
			case "32864-05.htm":
			case "32864-06.htm":
			{
				if ((qs != null) && qs.isCreated())
				{
					htmltext = event;
				}
				break;
			}
			case "32864-07.htm":
			{
				if ((qs != null) && qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "unspawnRaidCorpse":
			{
				allowedPlayerMap.remove(npc.getObjectId());
				npc.deleteMe();
				break;
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (!killer.isInParty() || !killer.getParty().isInCommandChannel())
		{
			// only the killing cc gets the quest
			return super.onKill(npc, killer, isSummon);
		}
		
		final L2CommandChannel cc = killer.getParty().getCommandChannel();
		
		if (cc.getMemberCount() < MIN_PLAYERS)
		{
			return super.onKill(npc, killer, isSummon);
		}
		
		final Set<Integer> allowedPlayers = new HashSet<>();
		
		for (AggroInfo aggro : ((L2Attackable) npc).getAggroList().values())
		{
			if ((aggro.getAttacker() == null) || !aggro.getAttacker().isPlayer())
			{
				continue;
			}
			
			final L2PcInstance attacker = aggro.getAttacker().getActingPlayer();
			
			if (attacker.isInParty() //
				&& attacker.getParty().isInCommandChannel() //
				&& attacker.getParty().getCommandChannel().equals(cc) // only players from the same cc are allowed
				&& Util.checkIfInRange(Config.ALT_PARTY_RANGE, npc, attacker, true))
			{
				allowedPlayers.add(attacker.getObjectId());
			}
		}
		
		if (!allowedPlayers.isEmpty())
		{
			// This depends on the boss respawn delay being at least 5 minutes.
			final L2Npc spawned = addSpawn(MONSTER_NPCS.get(npc.getId()), npc, true, 0);
			allowedPlayerMap.put(spawned.getObjectId(), allowedPlayers);
			startQuestTimer("unspawnRaidCorpse", 300000, npc, null);
		}
		
		return super.onKill(npc, killer, isSummon);
	}
	
	private static void rewardPlayer(L2PcInstance player, L2Npc npc)
	{
		final int chance = getRandom(10000);
		final int reward;
		int count = 1;
		
		if (chance < 170)
		{
			reward = ARMOR[getRandom(ARMOR.length)];
		}
		else if (chance < 200)
		{
			reward = ACCESSORIES[getRandom(ACCESSORIES.length)];
		}
		else if (chance < 270)
		{
			reward = WEAPONS[getRandom(WEAPONS.length)];
		}
		else if (chance < 325)
		{
			reward = BLESSED_SCROLL_ENCHANT_WEAPON_S;
		}
		else if (chance < 425)
		{
			reward = BLESSED_SCROLL_ENCHANT_ARMOR_S;
		}
		else if (chance < 925)
		{
			reward = ATTRIBUTE_CRYSTALS[getRandom(ATTRIBUTE_CRYSTALS.length)];
		}
		else if (chance < 1100)
		{
			reward = SCROLL_ENCHANT_WEAPON_S;
		}
		else
		{
			reward = GEMSTONE_S;
			count = 3;
		}
		
		giveItems(player, reward, count);
		final L2Item item = ItemTable.getInstance().getTemplate(reward);
		final NpcSay packet = new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.S1_RECEIVED_A_S2_ITEM_AS_A_REWARD_FROM_THE_SEPARATED_SOUL);
		packet.addStringParameter(player.getName());
		packet.addStringParameter(item.getName());
		npc.broadcastPacket(packet);
	}
}

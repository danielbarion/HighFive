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
package quests.Q00421_LittleWingsBigAdventure;

import java.util.HashMap;
import java.util.Map;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.network.serverpackets.NpcSay;
import com.l2jmobius.gameserver.util.Util;

/**
 * Little Wing's Big Adventure (421)
 * @author Pandragon, jurchiks
 */
public final class Q00421_LittleWingsBigAdventure extends Quest
{
	// NPCs
	private static final int CRONOS = 30610;
	private static final int MIMYU = 30747;
	// Items
	private static final int DRAGONFLUTE_OF_WIND = 3500;
	private static final int DRAGONFLUTE_OF_STAR = 3501;
	private static final int DRAGONFLUTE_OF_TWILIGHT = 3502;
	private static final int FAIRY_LEAF = 4325;
	// Monsters
	private static final int TREE_OF_WIND = 27185;
	private static final int TREE_OF_STAR = 27186;
	private static final int TREE_OF_TWILIGHT = 27187;
	private static final int TREE_OF_ABYSS = 27188;
	private static final int SOUL_OF_TREE_GUARDIAN = 27189;
	// Skills
	private static final SkillHolder CURSE_OF_MIMYU = new SkillHolder(4167, 1);
	private static final SkillHolder DRYAD_ROOT = new SkillHolder(1201, 33);
	private static final SkillHolder VICIOUS_POISON = new SkillHolder(4243, 1);
	// Rewards
	private static final int DRAGON_BUGLE_OF_WIND = 4422;
	private static final int DRAGON_BUGLE_OF_STAR = 4423;
	private static final int DRAGON_BUGLE_OF_TWILIGHT = 4424;
	// Misc
	private static final int MIN_PLAYER_LVL = 45;
	private static final int MIN_HACHLING_LVL = 55;
	private static final Map<Integer, NpcData> NPC_DATA = new HashMap<>();
	static
	{
		NPC_DATA.put(TREE_OF_WIND, new NpcData(NpcStringId.HEY_YOU_VE_ALREADY_DRUNK_THE_ESSENCE_OF_WIND, 2, 1, 27)); // retail min hits is 270
		NPC_DATA.put(TREE_OF_STAR, new NpcData(NpcStringId.HEY_YOU_VE_ALREADY_DRUNK_THE_ESSENCE_OF_A_STAR, 4, 2, 40)); // retail min hits is 400
		NPC_DATA.put(TREE_OF_TWILIGHT, new NpcData(NpcStringId.HEY_YOU_VE_ALREADY_DRUNK_THE_ESSENCE_OF_DUSK, 8, 4, 15)); // retail min hits is 150
		NPC_DATA.put(TREE_OF_ABYSS, new NpcData(NpcStringId.HEY_YOU_VE_ALREADY_DRUNK_THE_ESSENCE_OF_THE_ABYSS, 16, 8, 27)); // retail min hits is 270
	}
	
	public Q00421_LittleWingsBigAdventure()
	{
		super(421);
		addStartNpc(CRONOS);
		addTalkId(CRONOS, MIMYU);
		addAttackId(NPC_DATA.keySet());
		addKillId(NPC_DATA.keySet());
		registerQuestItems(FAIRY_LEAF);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if ("DESPAWN_GUARDIAN".equals(event))
		{
			if (npc != null)
			{
				npc.deleteMe();
			}
			return super.onAdvEvent(event, npc, player);
		}
		
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "30610-05.htm":
			{
				if (qs.isCreated())
				{
					if (getQuestItemsCount(player, DRAGONFLUTE_OF_WIND, DRAGONFLUTE_OF_STAR, DRAGONFLUTE_OF_TWILIGHT) == 1)
					{
						final L2ItemInstance flute = getFlute(player);
						
						if (flute.getEnchantLevel() < MIN_HACHLING_LVL)
						{
							htmltext = "30610-06.html";
						}
						else
						{
							qs.startQuest();
							qs.setMemoState(100);
							qs.set("fluteObjectId", flute.getObjectId());
							htmltext = event;
						}
					}
					else
					{
						htmltext = "30610-06.html";
					}
				}
				break;
			}
			case "30747-04.html":
			{
				final L2Summon summon = player.getSummon();
				
				if (summon == null)
				{
					htmltext = "30747-02.html";
				}
				else if (summon.getControlObjectId() != qs.getInt("fluteObjectId"))
				{
					htmltext = "30747-03.html";
				}
				else
				{
					htmltext = event;
				}
				break;
			}
			case "30747-05.html":
			{
				final L2Summon summon = player.getSummon();
				
				if (summon == null)
				{
					htmltext = "30747-06.html";
				}
				else if (summon.getControlObjectId() != qs.getInt("fluteObjectId"))
				{
					htmltext = "30747-06.html";
				}
				else
				{
					giveItems(player, FAIRY_LEAF, 4);
					qs.setCond(2, true);
					qs.setMemoState(0);
					htmltext = event;
				}
				break;
			}
			case "30747-07.html":
			case "30747-08.html":
			case "30747-09.html":
			case "30747-10.html":
			{
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		
		switch (npc.getId())
		{
			case CRONOS:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						final long fluteCount = getQuestItemsCount(talker, DRAGONFLUTE_OF_WIND, DRAGONFLUTE_OF_STAR, DRAGONFLUTE_OF_TWILIGHT);
						if (fluteCount == 0)
						{
							break; // this quest does not show up if no flute in inventory
						}
						
						if (talker.getLevel() < MIN_PLAYER_LVL)
						{
							htmltext = "30610-01.htm";
						}
						else if (fluteCount > 1)
						{
							htmltext = "30610-02.htm";
						}
						else if (getFlute(talker).getEnchantLevel() < MIN_HACHLING_LVL)
						{
							htmltext = "30610-03.html";
						}
						else
						{
							htmltext = "30610-04.htm";
						}
						break;
					}
					case State.STARTED:
					{
						htmltext = "30610-07.html";
						break;
					}
					case State.COMPLETED:
					{
						htmltext = getAlreadyCompletedMsg(talker);
						break;
					}
				}
				break;
			}
			case MIMYU:
			{
				switch (qs.getMemoState())
				{
					case 100:
					{
						qs.setMemoState(200);
						htmltext = "30747-01.html";
						break;
					}
					case 200:
					{
						final L2Summon summon = talker.getSummon();
						
						if (summon == null)
						{
							htmltext = "30747-02.html";
						}
						else if (summon.getControlObjectId() != qs.getInt("fluteObjectId"))
						{
							htmltext = "30747-03.html";
						}
						else
						{
							htmltext = "30747-04.html";
						}
						break;
					}
					case 0:
					{
						htmltext = "30747-07.html";
						break;
					}
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7:
					case 8:
					case 9:
					case 10:
					case 11:
					case 12:
					case 13:
					case 14:
					{
						if (hasQuestItems(talker, FAIRY_LEAF))
						{
							htmltext = "30747-11.html";
						}
						break;
					}
					case 15:
					{
						if (!hasQuestItems(talker, FAIRY_LEAF))
						{
							final L2Summon summon = talker.getSummon();
							
							if (summon == null)
							{
								htmltext = "30747-12.html";
							}
							else if (summon.getControlObjectId() == qs.getInt("fluteObjectId"))
							{
								qs.setMemoState(16);
								htmltext = "30747-13.html";
							}
							else
							{
								htmltext = "30747-14.html";
							}
						}
						break;
					}
					case 16:
					{
						if (!hasQuestItems(talker, FAIRY_LEAF))
						{
							if (talker.hasSummon())
							{
								htmltext = "30747-15.html";
							}
							else
							{
								final long fluteCount = getQuestItemsCount(talker, DRAGONFLUTE_OF_WIND, DRAGONFLUTE_OF_STAR, DRAGONFLUTE_OF_TWILIGHT);
								
								if (fluteCount > 1)
								{
									htmltext = "30747-17.html";
								}
								else if (fluteCount == 1)
								{
									final L2ItemInstance flute = getFlute(talker);
									
									if (flute.getObjectId() == qs.getInt("fluteObjectId"))
									{
										// TODO what if the hatchling has items in his inventory?
										// Should they be transfered to the strider or given to the player?
										switch (flute.getId())
										{
											case DRAGONFLUTE_OF_WIND:
											{
												takeItems(talker, DRAGONFLUTE_OF_WIND, -1);
												giveItems(talker, DRAGON_BUGLE_OF_WIND, 1);
												break;
											}
											case DRAGONFLUTE_OF_STAR:
											{
												takeItems(talker, DRAGONFLUTE_OF_STAR, -1);
												giveItems(talker, DRAGON_BUGLE_OF_STAR, 1);
												break;
											}
											case DRAGONFLUTE_OF_TWILIGHT:
											{
												takeItems(talker, DRAGONFLUTE_OF_TWILIGHT, -1);
												giveItems(talker, DRAGON_BUGLE_OF_TWILIGHT, 1);
												break;
											}
										}
										
										qs.exitQuest(true, true);
										htmltext = "30747-16.html";
									}
									else
									{
										npc.setTarget(talker);
										npc.doCast(CURSE_OF_MIMYU.getSkill());
										htmltext = "30747-18.html";
									}
								}
							}
						}
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isSummon)
	{
		final QuestState qs = getQuestState(attacker, false);
		if ((qs != null) && qs.isCond(2))
		{
			if (isSummon)
			{
				final NpcData data = NPC_DATA.get(npc.getId());
				if ((qs.getMemoState() % data.memoStateMod) < data.memoStateValue)
				{
					if (attacker.getSummon().getControlObjectId() == qs.getInt("fluteObjectId"))
					{
						final int hits = qs.getInt("hits") + 1;
						qs.set("hits", hits);
						
						if (hits < data.minHits)
						{
							if ((npc.getId() == TREE_OF_ABYSS) && (getRandom(100) < 2))
							{
								npc.setTarget(attacker);
								npc.doCast(DRYAD_ROOT.getSkill());
							}
						}
						else if (getRandom(100) < 6) // Retail is < 2
						{
							if (hasQuestItems(attacker, FAIRY_LEAF))
							{
								npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.GIVE_ME_A_FAIRY_LEAF));
								takeItems(attacker, FAIRY_LEAF, 1);
								qs.setMemoState(qs.getMemoState() + data.memoStateValue);
								qs.unset("hits");
								playSound(attacker, QuestSound.ITEMSOUND_QUEST_MIDDLE);
								
								if (qs.getMemoState() == 15)
								{
									qs.setCond(3);
								}
							}
						}
					}
				}
				else
				{
					switch (getRandom(3))
					{
						case 0:
						{
							npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.WHY_DO_YOU_BOTHER_ME_AGAIN));
							break;
						}
						case 1:
						{
							npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, data.message));
							break;
						}
						case 2:
						{
							npc.broadcastPacket(new NpcSay(npc, ChatType.NPC_GENERAL, NpcStringId.LEAVE_NOW_BEFORE_YOU_INCUR_THE_WRATH_OF_THE_GUARDIAN_GHOST));
							break;
						}
					}
				}
			}
			else if (getRandom(100) < 30)
			{
				npc.setTarget(attacker);
				npc.doCast(VICIOUS_POISON.getSkill());
			}
		}
		else if ((npc.getCurrentHp() < (npc.getMaxHp() * 0.67)) && (getRandom(100) < 30))
		{
			npc.setTarget(attacker);
			npc.doCast(VICIOUS_POISON.getSkill());
		}
		
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (Util.checkIfInRange(Config.ALT_PARTY_RANGE, killer, npc, true))
		{
			for (int i = 0; i < 20; i++)
			{
				final L2Npc guardian = addSpawn(SOUL_OF_TREE_GUARDIAN, npc);
				startQuestTimer("DESPAWN_GUARDIAN", 300000, guardian, null);
				
				if (i == 0)
				{
					npc.setTarget(killer);
					npc.doCast(VICIOUS_POISON.getSkill());
				}
				
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, killer);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	private static L2ItemInstance getFlute(L2PcInstance player)
	{
		final int fluteItemId;
		if (hasQuestItems(player, DRAGONFLUTE_OF_WIND))
		{
			fluteItemId = DRAGONFLUTE_OF_WIND;
		}
		else if (hasQuestItems(player, DRAGONFLUTE_OF_STAR))
		{
			fluteItemId = DRAGONFLUTE_OF_STAR;
		}
		else
		{
			fluteItemId = DRAGONFLUTE_OF_TWILIGHT;
		}
		return player.getInventory().getItemByItemId(fluteItemId);
	}
	
	private static final class NpcData
	{
		public final NpcStringId message;
		public final int memoStateMod;
		public final int memoStateValue;
		public final int minHits;
		
		public NpcData(NpcStringId message, int memoStateMod, int memoStateValue, int minHits)
		{
			this.message = message;
			this.memoStateMod = memoStateMod;
			this.memoStateValue = memoStateValue;
			this.minHits = minHits;
		}
	}
}
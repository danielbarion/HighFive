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
package quests.Q00144_PailakaInjuredDragon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l2jmobius.commons.util.Rnd;
import com.l2jmobius.gameserver.ai.CtrlEvent;
import com.l2jmobius.gameserver.ai.CtrlIntention;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.QuestSound;
import com.l2jmobius.gameserver.instancemanager.InstanceManager;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.ItemChanceHolder;
import com.l2jmobius.gameserver.model.instancezone.Instance;
import com.l2jmobius.gameserver.model.instancezone.InstanceWorld;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.model.zone.L2ZoneType;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.SpecialCamera;
import com.l2jmobius.gameserver.util.Util;

/**
 * Pailaka Injured Dragon
 * @author Pandragon
 */
public class Q00144_PailakaInjuredDragon extends Quest
{
	// NPCs
	private static final int KETRA_ORC_SHAMAN = 32499;
	private static final int KETRA_ORC_SUPPORTER = 32502;
	private static final int KETRA_ORC_SUPPORTER2 = 32512;
	private static final int KETRA_ORC_INTELIGENCE_OFFICER = 32509;
	// Wall Mobs
	private static final int VARKA_SILENOS_RECRUIT = 18635;
	private static final int VARKA_SILENOS_FOOTMAN = 18636;
	private static final int VARKA_SILENOS_WARRIOR = 18642;
	private static final int VARKA_SILENOS_OFFICER = 18646;
	private static final int VARKAS_COMMANDER = 18654;
	private static final int VARKA_ELITE_GUARD = 18653;
	private static final int VARKA_SILENOS_GREAT_MAGUS = 18649;
	private static final int VARKA_SILENOS_GENERAL = 18650;
	private static final int VARKA_SILENOS_HEAD_GUARD = 18655;
	private static final int PROPHET_GUARD = 18657;
	private static final int VARKAS_PROPHET = 18659;
	// Extra Wall Silenos
	private static final int VARKA_SILENOS_MEDIUM = 18644;
	private static final int VARKA_SILENOS_PRIEST = 18641;
	private static final int VARKA_SILENOS_SHAMAN = 18640;
	private static final int VARKA_SILENOS_SEER = 18648;
	private static final int VARKA_SILENOS_MAGNUS = 18645;
	private static final int DISCIPLE_OF_PROPHET = 18658;
	private static final int VARKA_HEAD_MAGUS = 18656;
	private static final int VARKA_SILENOS_GREAT_SEER = 18652;
	// Normal Mobs
	private static final int ANTYLOPE_1 = 18637;
	private static final int ANTYLOPE_2 = 18643;
	private static final int ANTYLOPE_3 = 18651;
	private static final int FLAVA = 18647;
	// Boss
	private static final int LATANA = 18660;
	// Items
	private static final int SPEAR = 13052;
	private static final int ENCHSPEAR = 13053;
	private static final int LASTSPEAR = 13054;
	private static final int STAGE1 = 13056;
	private static final int STAGE2 = 13057;
	private static final int SHIELD_POTION = 13032;
	private static final int HEAL_POTION = 13033;
	// Rewards
	private static final int PSHIRT = 13296;
	private static final int SCROLL_OF_ESCAPE = 736;
	// Arrays
	private static final int[] WALL_MONSTERS =
	{
		// 1st Row Mobs
		VARKA_SILENOS_FOOTMAN,
		VARKA_SILENOS_WARRIOR,
		VARKA_SILENOS_OFFICER,
		VARKAS_COMMANDER,
		VARKA_SILENOS_RECRUIT,
		PROPHET_GUARD,
		VARKA_ELITE_GUARD,
		VARKA_SILENOS_GREAT_MAGUS,
		VARKA_SILENOS_GENERAL,
		VARKA_SILENOS_HEAD_GUARD,
		PROPHET_GUARD,
		VARKAS_PROPHET,
		// 2nd Row Mobs
		DISCIPLE_OF_PROPHET,
		VARKA_HEAD_MAGUS,
		VARKA_SILENOS_GREAT_SEER,
		VARKA_SILENOS_SHAMAN,
		VARKA_SILENOS_MAGNUS,
		VARKA_SILENOS_SEER,
		VARKA_SILENOS_MEDIUM,
		VARKA_SILENOS_PRIEST
	};
	private static final List<ItemChanceHolder> DROPLIST = new ArrayList<>();
	{
		DROPLIST.add(new ItemChanceHolder(HEAL_POTION, 80));
		DROPLIST.add(new ItemChanceHolder(SHIELD_POTION, 30));
	}
	private static final int[] OTHER_MONSTERS =
	{
		ANTYLOPE_1,
		ANTYLOPE_2,
		ANTYLOPE_3,
		FLAVA
	};
	private static final int[] ITEMS =
	{
		SPEAR,
		ENCHSPEAR,
		LASTSPEAR,
		STAGE1,
		STAGE2,
		SHIELD_POTION,
		HEAL_POTION
	};
	// @formatter:off
	private static final Map<Integer, int[]> NOEXIT_ZONES = new HashMap<>();
	{
		NOEXIT_ZONES.put(200001, new int[]{123167, -45743, -3023});
		NOEXIT_ZONES.put(200002, new int[]{117783, -46398, -2560});
		NOEXIT_ZONES.put(200003, new int[]{116791, -51556, -2584});
		NOEXIT_ZONES.put(200004, new int[]{117993, -52505, -2480});
		NOEXIT_ZONES.put(200005, new int[]{113226, -44080, -2776});
		NOEXIT_ZONES.put(200006, new int[]{110326, -45016, -2444});
		NOEXIT_ZONES.put(200007, new int[]{118341, -55951, -2280});
		NOEXIT_ZONES.put(200008, new int[]{110127, -41562, -2332});
	}
	private static final int[][] BUFFS =
	{
		{4357,2}, // Haste Lv2
		{4342,2}, // Wind Walk Lv2
		{4356,3}, // Empower Lv3
		{4355,3}, // Acumen Lv3
		{4351,6}, // Concentration Lv6
		{4345,3}, // Might Lv3
		{4358,3}, // Guidance Lv3
		{4359,3}, // Focus Lv3
		{4360,3}, // Death Wisper Lv3
		{4352,2}, // Berserker Spirit Lv2
		{4354,4}, // Vampiric Rage Lv4
		{4347,6}  // Blessed Body Lv6
	};
	private static final int[][] HP_HERBS_DROPLIST =
	{
		// itemId, count, chance
		{ 8601, 1, 40 },
		{ 8600, 1, 70 }
	};
	private static final int[][] MP_HERBS_DROPLIST =
	{
		// itemId, count, chance
		{ 8604, 1, 40 },
		{ 8603, 1, 70 }
	};
	// @formatter:on
	
	static final Location TELEPORT = new Location(125757, -40928, -3736);
	private static final int MIN_LEVEL = 73;
	private static final int MAX_LEVEL = 77;
	private static final int MAX_SUMMON_LEVEL = 80;
	private static final int EXIT_TIME = 5;
	private static final int INSTANCE_ID = 45;
	private int buff_counter = 5;
	
	public Q00144_PailakaInjuredDragon()
	{
		super(144);
		addStartNpc(KETRA_ORC_SHAMAN, KETRA_ORC_INTELIGENCE_OFFICER);
		addFirstTalkId(KETRA_ORC_SUPPORTER, KETRA_ORC_INTELIGENCE_OFFICER, KETRA_ORC_SUPPORTER2);
		addTalkId(KETRA_ORC_SHAMAN, KETRA_ORC_SUPPORTER, KETRA_ORC_INTELIGENCE_OFFICER, KETRA_ORC_SUPPORTER2);
		addSpawnId(WALL_MONSTERS);
		addKillId(WALL_MONSTERS);
		addKillId(OTHER_MONSTERS);
		addKillId(LATANA);
		addSeeCreatureId(LATANA);
		addEnterZoneId(NOEXIT_ZONES.keySet());
		registerQuestItems(ITEMS);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		if (event.equals("enter"))
		{
			enterInstance(player);
			return "32499-08.html";
		}
		else if (event.equals("enters"))
		{
			enterInstance(player);
			return "32499-10.html";
		}
		else if (event.equals("32499-02.htm"))
		{
			if (qs.getCond() == 0)
			{
				qs.startQuest();
			}
		}
		else if (event.equals("32499-04.html"))
		{
			if (qs.isCond(1))
			{
				qs.setCond(2, true);
			}
		}
		else if (event.equals("32502-04.html"))
		{
			if (qs.isCond(2) && !hasQuestItems(player, SPEAR))
			{
				qs.setCond(3, true);
				giveItems(player, SPEAR, 1);
			}
		}
		else if (event.equals("32509-02.html"))
		{
			if (qs.isCond(3) && hasQuestItems(player, SPEAR))
			{
				if (!hasQuestItems(player, SPEAR) && !hasQuestItems(player, STAGE1))
				{
					return "32509-07.html";
				}
				if (hasQuestItems(player, SPEAR) && !hasQuestItems(player, STAGE1))
				{
					return "32509-01.html";
				}
				if (hasQuestItems(player, SPEAR) && hasQuestItems(player, STAGE1))
				{
					takeItems(player, SPEAR, -1);
					takeItems(player, STAGE1, -1);
					giveItems(player, ENCHSPEAR, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_MIDDLE.getPacket()); // TODO: Set proper cond.
					return "32509-02.html";
				}
			}
			if (qs.isCond(3) && hasQuestItems(player, ENCHSPEAR))
			{
				if (!hasQuestItems(player, ENCHSPEAR) && !hasQuestItems(player, STAGE2))
				{
					return "32509-07.html";
				}
				if (hasQuestItems(player, ENCHSPEAR) && !hasQuestItems(player, STAGE2))
				{
					return "32509-01.html";
				}
				if (hasQuestItems(player, ENCHSPEAR) && hasQuestItems(player, STAGE2))
				{
					takeItems(player, ENCHSPEAR, -1);
					takeItems(player, STAGE2, -1);
					giveItems(player, LASTSPEAR, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_MIDDLE.getPacket()); // TODO: Set proper cond.
					addSpawn(LATANA, 105732, -41787, -1782, 35742, false, 0, false, npc.getInstanceId());
					return "32509-03.html";
				}
			}
			if (qs.isCond(3) && hasQuestItems(player, LASTSPEAR))
			{
				if (hasQuestItems(player, LASTSPEAR))
				{
					return "32509-03a.html";
				}
				if (!hasQuestItems(player, LASTSPEAR))
				{
					return "32509-07.html";
				}
			}
			return "32509-07.html";
		}
		else if (event.equals("32509-06.html"))
		{
			if (buff_counter < 1)
			{
				return "32509-04.html";
			}
		}
		else if (event.equals("32512-02.html"))
		{
			final Instance inst = InstanceManager.getInstance().getInstance(npc.getInstanceId());
			inst.setDuration(EXIT_TIME * 60000);
			inst.setEmptyDestroyTime(0);
			
			if (inst.containsPlayer(player.getObjectId()))
			{
				player.setVitalityPoints(20000, true);
				addExpAndSp(player, 28000000, 2850000);
				giveItems(player, SCROLL_OF_ESCAPE, 1);
				giveItems(player, PSHIRT, 1);
			}
			
			qs.exitQuest(false, true);
		}
		else if (event.equals("LATANA_INTRO_CAMERA_START"))
		{
			npc.setScriptValue(1);
			npc.abortAttack();
			npc.abortCast();
			npc.setIsInvul(true);
			npc.setIsImmobilized(true);
			npc.disableAllSkills();
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
			player.abortAttack();
			player.abortCast();
			player.stopMove(null);
			player.setTarget(null);
			if (player.hasSummon())
			{
				player.getSummon().abortAttack();
				player.getSummon().abortCast();
				player.getSummon().stopMove(null);
				player.getSummon().getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
			}
			player.sendPacket(new SpecialCamera(npc, 600, 200, 5, 0, 15000, 10000, (-10), 8, 1, 1, 1));
			startQuestTimer("LATANA_INTRO_CAMERA_2", 2000, npc, player);
			return null;
		}
		else if (event.equals("LATANA_INTRO_CAMERA_2"))
		{
			player.sendPacket(new SpecialCamera(npc, 400, 200, 5, 4000, 15000, 10000, (-10), 8, 1, 1, 0));
			startQuestTimer("LATANA_INTRO_CAMERA_3", 4000, npc, player);
			return null;
		}
		else if (event.equals("LATANA_INTRO_CAMERA_3"))
		{
			player.sendPacket(new SpecialCamera(npc, 300, 195, 4, 1500, 15000, 10000, (-5), 10, 1, 1, 0));
			startQuestTimer("LATANA_INTRO_CAMERA_4", 1700, npc, player);
			return null;
		}
		else if (event.equals("LATANA_INTRO_CAMERA_4"))
		{
			player.sendPacket(new SpecialCamera(npc, 130, 2, 5, 0, 15000, 10000, 0, 0, 1, 0, 1));
			startQuestTimer("LATANA_INTRO_CAMERA_5", 2000, npc, player);
			return null;
		}
		else if (event.equals("LATANA_INTRO_CAMERA_5"))
		{
			player.sendPacket(new SpecialCamera(npc, 220, 0, 4, 800, 15000, 10000, 5, 10, 1, 0, 0));
			startQuestTimer("LATANA_INTRO_CAMERA_6", 2000, npc, player);
			return null;
		}
		else if (event.equals("LATANA_INTRO_CAMERA_6"))
		{
			player.sendPacket(new SpecialCamera(npc, 250, 185, 5, 4000, 15000, 10000, (-5), 10, 1, 1, 0));
			startQuestTimer("LATANA_INTRO_CAMERA_7", 4000, npc, player);
			return null;
		}
		else if (event.equals("LATANA_INTRO_CAMERA_7"))
		{
			player.sendPacket(new SpecialCamera(npc, 200, 0, 5, 2000, 15000, 10000, 0, 25, 1, 0, 0));
			startQuestTimer("LATANA_INTRO_CAMERA_8", 4530, npc, player);
			return null;
		}
		else if (event.equals("LATANA_INTRO_CAMERA_8"))
		{
			npc.doCast(SkillData.getInstance().getSkill(5759, 1));
			player.sendPacket(new SpecialCamera(npc, 300, (-3), 5, 3500, 15000, 6000, 0, 6, 1, 0, 0));
			startQuestTimer("LATANA_INTRO_CAMERA_9", 10000, npc, player);
			return null;
		}
		else if (event.equals("LATANA_INTRO_CAMERA_9"))
		{
			npc.setIsInvul(false);
			npc.setIsImmobilized(false);
			npc.enableAllSkills();
			npc.getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, player);
			return null;
		}
		else if (event.equals("LATANA_DEATH_CAMERA_START"))
		{
			player.sendPacket(new SpecialCamera(npc, 450, 200, 3, 0, 15000, 10000, (-15), 20, 1, 1, 1));
			startQuestTimer("LATANA_DEATH_CAMERA_1", 100, npc, player);
			return null;
		}
		else if (event.equals("LATANA_DEATH_CAMERA_1"))
		{
			player.sendPacket(new SpecialCamera(npc, 350, 200, 5, 5600, 15000, 10000, (-15), 10, 1, 1, 0));
			startQuestTimer("LATANA_DEATH_CAMERA_2", 5600, npc, player);
			return null;
		}
		else if (event.equals("LATANA_DEATH_CAMERA_2"))
		{
			player.sendPacket(new SpecialCamera(npc, 360, 200, 5, 1000, 15000, 2000, (-15), 10, 1, 1, 0));
			return null;
		}
		else if (event.startsWith("buff"))
		{
			if (buff_counter > 0)
			{
				final int nr = Integer.parseInt(event.split("buff")[1]);
				giveBuff(npc, player, BUFFS[nr - 1][0], BUFFS[nr - 1][1]);
				return "32509-06a.html";
			}
			return "32509-05.html";
		}
		return event;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		return npc.getId() + ".html";
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		
		switch (npc.getId())
		{
			case KETRA_ORC_SHAMAN:
			{
				switch (qs.getState())
				{
					case State.CREATED:
					{
						if (player.getLevel() < MIN_LEVEL)
						{
							return "32499-no.html";
						}
						if (player.getLevel() > MAX_LEVEL)
						{
							return "32499-over.html";
						}
						return "32499-00.htm";
					}
					case State.STARTED:
					{
						if (player.getLevel() < MIN_LEVEL)
						{
							return "32499-no.html";
						}
						if (player.getLevel() > MAX_LEVEL)
						{
							return "32499-over.html";
						}
						if (qs.isCond(1))
						{
							return "32499-06.html";
						}
						else if (qs.getCond() >= 2)
						{
							return "32499-09.html";
						}
					}
					case State.COMPLETED:
					{
						return "32499-completed.html";
					}
					default:
					{
						return "32499-no.html";
					}
				}
				// break;
			}
			case KETRA_ORC_SUPPORTER:
			{
				if (qs.getCond() > 2)
				{
					return "32502-04.html";
				}
				return "32502-00.html";
			}
			case KETRA_ORC_SUPPORTER2:
			{
				if (qs.getState() == State.COMPLETED)
				{
					return "32512-03.html";
				}
				else if (qs.isCond(4))
				{
					return "32512-01.html";
				}
				break;
			}
		}
		return getNoQuestMsg(player);
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		final QuestState qs = player.getQuestState(getName());
		if ((qs == null) || (qs.getState() != State.STARTED))
		{
			return null;
		}
		
		// There are lots of mobs walls, and item get is random, it could happen that you dont get the item until the last wall, and there's 4 different silenos groups. 1 enchant comes only from group 2 and the 2nd comes from group 4. Chances, lets say 20% of getting the enchant when killing the
		// right mob When you kill a mob wall, another mage type appears behind. If all mobs from the front are killed then the ones that are behind are despawned. Also this mobs should be damaged, like with 30% of max HP, because they should be easy to kill.
		switch (npc.getId())
		{
			case VARKA_SILENOS_FOOTMAN:
			case VARKA_SILENOS_RECRUIT:
			{
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				if (qs.isCond(3) && hasQuestItems(player, SPEAR) && !hasQuestItems(player, STAGE1) && (Rnd.get(100) < 25))
				{
					giveItems(player, STAGE1, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_ITEMGET.getPacket());
				}
				// Spawns Mage Type silenos behind the one that was killed.
				spawnMageBehind(npc, player, VARKA_SILENOS_MEDIUM);
				// Check if all the first row have been killed. Despawn mages.
				checkIfLastInWall(npc);
				break;
			}
			case VARKA_SILENOS_WARRIOR:
			{
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				if (qs.isCond(3) && hasQuestItems(player, SPEAR) && !hasQuestItems(player, STAGE1) && (Rnd.get(100) < 25))
				{
					giveItems(player, STAGE1, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_ITEMGET.getPacket());
				}
				// Spawns Mage Type silenos behind the one that was killed.
				spawnMageBehind(npc, player, VARKA_SILENOS_PRIEST);
				// Check if all the first row have been killed. Despawn mages.
				checkIfLastInWall(npc);
				break;
			}
			case VARKA_ELITE_GUARD:
			{
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				if (qs.isCond(3) && hasQuestItems(player, SPEAR) && !hasQuestItems(player, STAGE1) && (Rnd.get(100) < 25))
				{
					giveItems(player, STAGE1, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_ITEMGET.getPacket());
				}
				// Spawns Mage Type silenos behind the one that was killed.
				spawnMageBehind(npc, player, VARKA_SILENOS_SHAMAN);
				// Check if all the first row have been killed. Despawn mages.
				checkIfLastInWall(npc);
				break;
			}
			case VARKAS_COMMANDER:
			case VARKA_SILENOS_OFFICER:
			{
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				if (qs.isCond(3) && hasQuestItems(player, SPEAR) && !hasQuestItems(player, STAGE1) && (Rnd.get(100) < 25))
				{
					giveItems(player, STAGE1, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_ITEMGET.getPacket());
				}
				// Spawns Mage Type silenos behind the one that was killed.
				spawnMageBehind(npc, player, VARKA_SILENOS_SEER);
				// Check if all the first row have been killed. Despawn mages.
				checkIfLastInWall(npc);
				break;
			}
			case VARKA_SILENOS_GREAT_MAGUS:
			case VARKA_SILENOS_GENERAL:
			{
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				if (qs.isCond(3) && hasQuestItems(player, ENCHSPEAR) && !hasQuestItems(player, STAGE2) && (Rnd.get(100) < 25))
				{
					giveItems(player, STAGE2, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_ITEMGET.getPacket());
				}
				// Spawns Mage Type silenos behind the one that was killed.
				spawnMageBehind(npc, player, VARKA_SILENOS_MAGNUS);
				// Check if all the first row have been killed. Despawn mages.
				checkIfLastInWall(npc);
				break;
			}
			case VARKAS_PROPHET:
			{
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				if (qs.isCond(3) && hasQuestItems(player, ENCHSPEAR) && !hasQuestItems(player, STAGE2) && (Rnd.get(100) < 25))
				{
					giveItems(player, STAGE2, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_ITEMGET.getPacket());
				}
				// Spawns Mage Type silenos behind the one that was killed.
				spawnMageBehind(npc, player, DISCIPLE_OF_PROPHET);
				// Check if all the first row have been killed. Despawn mages.
				checkIfLastInWall(npc);
				break;
			}
			case VARKA_SILENOS_HEAD_GUARD:
			{
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				if (qs.isCond(3) && hasQuestItems(player, ENCHSPEAR) && !hasQuestItems(player, STAGE2) && (Rnd.get(100) < 25))
				{
					giveItems(player, STAGE2, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_ITEMGET.getPacket());
				}
				// Spawns Mage Type silenos behind the one that was killed.
				spawnMageBehind(npc, player, VARKA_HEAD_MAGUS);
				// Check if all the first row have been killed. Despawn mages.
				checkIfLastInWall(npc);
				break;
			}
			case PROPHET_GUARD:
			{
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				if (qs.isCond(3) && hasQuestItems(player, ENCHSPEAR) && !hasQuestItems(player, STAGE2) && (Rnd.get(100) < 25))
				{
					giveItems(player, STAGE2, 1);
					player.sendPacket(QuestSound.ITEMSOUND_QUEST_ITEMGET.getPacket());
				}
				// Spawns Mage Type silenos behind the one that was killed.
				spawnMageBehind(npc, player, VARKA_SILENOS_GREAT_SEER);
				// Check if all the first row have been killed. Despawn mages.
				checkIfLastInWall(npc);
				break;
			}
			case LATANA:
			{
				qs.setCond(4, true);
				startQuestTimer("LATANA_DEATH_CAMERA_START", 1000, npc, player);
				addSpawn(KETRA_ORC_SUPPORTER2, npc.getX(), npc.getY(), npc.getZ(), npc.getHeading(), false, 0, false, npc.getInstanceId());
				break;
			}
			case ANTYLOPE_1:
			case ANTYLOPE_2:
			case ANTYLOPE_3:
			case FLAVA:
			{
				dropItem(npc, player);
				break;
			}
			default:
			{
				// hardcoded herb drops.
				dropHerb(npc, player, HP_HERBS_DROPLIST);
				dropHerb(npc, player, MP_HERBS_DROPLIST);
				break;
			}
		}
		return super.onKill(npc, player, isSummon);
	}
	
	// Spawns Mage Type silenos behind the one that was killed. Aggro against the player that kill the mob.
	private void spawnMageBehind(L2Npc npc, L2PcInstance player, int mageId)
	{
		final double rads = Math.toRadians(Util.convertHeadingToDegree(npc.getSpawn().getHeading()) + 180);
		final int mageX = (int) (npc.getX() + (150 * Math.cos(rads)));
		final int mageY = (int) (npc.getY() + (150 * Math.sin(rads)));
		final L2Npc mageBack = addSpawn(mageId, mageX, mageY, npc.getZ(), npc.getSpawn().getHeading(), false, 0, true, npc.getInstanceId());
		mageBack.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, player, 1000);
	}
	
	// This function will check if there is other mob alive in this wall of mobs. If all mobs in the first row are dead then despawn the second row mobs, the mages.
	private void checkIfLastInWall(L2Npc npc)
	{
		final Collection<L2Npc> knowns = L2World.getInstance().getVisibleObjectsInRange(npc, L2Npc.class, 700);
		for (L2Npc npcs : knowns)
		{
			if (npcs.isDead())
			{
				continue;
			}
			
			switch (npc.getId())
			{
				case VARKA_SILENOS_FOOTMAN:
				case VARKA_SILENOS_RECRUIT:
				case VARKA_SILENOS_WARRIOR:
				{
					switch (npcs.getId())
					{
						case VARKA_SILENOS_FOOTMAN:
						case VARKA_SILENOS_RECRUIT:
						case VARKA_SILENOS_WARRIOR:
						{
							return;
						}
					}
					break;
				}
				case VARKA_ELITE_GUARD:
				case VARKAS_COMMANDER:
				case VARKA_SILENOS_OFFICER:
				{
					switch (npcs.getId())
					{
						case VARKA_ELITE_GUARD:
						case VARKAS_COMMANDER:
						case VARKA_SILENOS_OFFICER:
						{
							return;
						}
					}
					break;
				}
				case VARKA_SILENOS_GREAT_MAGUS:
				case VARKA_SILENOS_GENERAL:
				case VARKAS_PROPHET:
				{
					switch (npcs.getId())
					{
						case VARKA_SILENOS_GREAT_MAGUS:
						case VARKA_SILENOS_GENERAL:
						case VARKAS_PROPHET:
						{
							return;
						}
					}
					break;
				}
				case VARKA_SILENOS_HEAD_GUARD:
				case PROPHET_GUARD:
				{
					switch (npcs.getId())
					{
						case VARKA_SILENOS_HEAD_GUARD:
						case PROPHET_GUARD:
						{
							return;
						}
					}
					break;
				}
			}
		}
		
		// We didnt find any mob on the first row alive, so despawn the second row mobs.
		for (L2Character npcs : knowns)
		{
			if (npcs.isDead())
			{
				continue;
			}
			
			switch (npc.getId())
			{
				case VARKA_SILENOS_FOOTMAN:
				case VARKA_SILENOS_RECRUIT:
				case VARKA_SILENOS_WARRIOR:
				{
					switch (npcs.getId())
					{
						case VARKA_SILENOS_MEDIUM:
						case VARKA_SILENOS_PRIEST:
						{
							npcs.abortCast();
							npcs.deleteMe();
							break;
						}
					}
					break;
				}
				case VARKA_ELITE_GUARD:
				case VARKAS_COMMANDER:
				case VARKA_SILENOS_OFFICER:
				{
					switch (npcs.getId())
					{
						case VARKA_SILENOS_SHAMAN:
						case VARKA_SILENOS_SEER:
						{
							npcs.abortCast();
							npcs.deleteMe();
							break;
						}
					}
					break;
				}
				case VARKA_SILENOS_GREAT_MAGUS:
				case VARKA_SILENOS_GENERAL:
				case VARKAS_PROPHET:
				{
					switch (npcs.getId())
					{
						case VARKA_SILENOS_MAGNUS:
						case DISCIPLE_OF_PROPHET:
						{
							npcs.abortCast();
							npcs.deleteMe();
							break;
						}
					}
					break;
				}
				case VARKA_SILENOS_HEAD_GUARD:
				case PROPHET_GUARD:
				{
					switch (npcs.getId())
					{
						case VARKA_HEAD_MAGUS:
						case VARKA_SILENOS_GREAT_SEER:
						{
							npcs.abortCast();
							npcs.deleteMe();
							break;
						}
					}
					break;
				}
			}
		}
	}
	
	@Override
	public String onSeeCreature(L2Npc npc, L2Character creature, boolean isSummon)
	{
		if (creature.isPlayer() && npc.isScriptValue(0))
		{
			final QuestState qs = getQuestState(creature.getActingPlayer(), false);
			if ((qs == null) || (qs.getState() != State.STARTED) || isSummon)
			{
				return null;
			}
			startQuestTimer("LATANA_INTRO_CAMERA_START", 600, npc, creature.getActingPlayer());
		}
		return super.onSeeCreature(npc, creature, isSummon);
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		if (npc.isMonster())
		{
			for (int mobId : WALL_MONSTERS)
			{
				// Every monster on pailaka should be Aggresive and Active, with the same clan, also wall mobs cannot move, they all use magic from far, and if you get in combat range they hit.
				if (mobId == npc.getId())
				{
					final L2MonsterInstance monster = (L2MonsterInstance) npc;
					monster.setIsImmobilized(true);
					break;
				}
			}
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onEnterZone(L2Character character, L2ZoneType zone)
	{
		if (character.isPlayer() && !character.isDead() && !character.isTeleporting() && ((L2PcInstance) character).isOnline())
		{
			final InstanceWorld world = InstanceManager.getInstance().getWorld(character);
			if ((world != null) && (world.getTemplateId() == INSTANCE_ID))
			{
				// If a player wants to go by a mob wall without kill it, he will be returned back to a spawn point.
				final int[] zoneTeleport = NOEXIT_ZONES.get(zone.getId());
				if (zoneTeleport != null)
				{
					for (L2Character npc : L2World.getInstance().getVisibleObjectsInRange(character, L2Npc.class, 700))
					{
						if (npc.isDead())
						{
							continue;
						}
						teleportPlayer(character.getActingPlayer(), zoneTeleport, world.getInstanceId());
						break;
					}
				}
			}
		}
		return super.onEnterZone(character, zone);
	}
	
	private void dropHerb(L2Npc mob, L2PcInstance player, int[][] drop)
	{
		final int chance = Rnd.get(100);
		for (int[] element : drop)
		{
			if (chance < element[2])
			{
				((L2MonsterInstance) mob).dropItem(player, element[0], element[1]);
				return;
			}
		}
	}
	
	private void dropItem(L2Npc mob, L2PcInstance player)
	{
		// To make random drops, we shuffle the droplist every time its used.
		Collections.shuffle(DROPLIST);
		for (ItemChanceHolder drop : DROPLIST)
		{
			if (Rnd.get(100) < drop.getChance())
			{
				((L2MonsterInstance) mob).dropItem(player, drop.getId(), Rnd.get(1, 6));
				return;
			}
		}
	}
	
	private void giveBuff(L2Npc npc, L2PcInstance player, int skillId, int level)
	{
		buff_counter--;
		npc.setTarget(player);
		npc.doCast(SkillData.getInstance().getSkill(skillId, level));
	}
	
	private void teleportPlayer(L2PcInstance player, int[] coords, int instanceId)
	{
		player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		player.setInstanceId(instanceId);
		player.teleToLocation(coords[0], coords[1], coords[2], true);
	}
	
	private synchronized void enterInstance(L2PcInstance player)
	{
		// Check for existing instances for this player.
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		if (world != null)
		{
			if (world.getTemplateId() != INSTANCE_ID)
			{
				player.sendPacket(SystemMessageId.YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON);
				return;
			}
			if (InstanceManager.getInstance().getInstance(world.getInstanceId()) != null)
			{
				checkMaxSummonLevel(player);
				teleportPlayer(player, TELEPORT, world.getInstanceId());
			}
		}
		// New instance.
		else
		{
			world = new InstanceWorld();
			world.setInstance(InstanceManager.getInstance().createDynamicInstance(INSTANCE_ID));
			InstanceManager.getInstance().addWorld(world);
			
			// Check max summon levels.
			checkMaxSummonLevel(player);
			
			world.addAllowed(player);
			teleportPlayer(player, TELEPORT, world.getInstanceId());
		}
	}
	
	// Checks if the summon or pet that the player has can be used.
	private void checkMaxSummonLevel(L2PcInstance player)
	{
		final L2Summon pet = player.getSummon();
		if ((pet != null) && pet.isPet())
		{
			if (pet.getLevel() > MAX_SUMMON_LEVEL)
			{
				pet.unSummon(player);
			}
		}
	}
}

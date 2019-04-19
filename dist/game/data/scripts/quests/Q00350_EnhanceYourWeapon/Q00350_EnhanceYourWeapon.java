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
package quests.Q00350_EnhanceYourWeapon;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.model.AbsorberInfo;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.actor.L2Attackable;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.quest.Quest;
import com.l2jmobius.gameserver.model.quest.QuestState;
import com.l2jmobius.gameserver.model.quest.State;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.InventoryUpdate;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

/**
 * Enhance Your Weapon (350)
 * @author Gigiikun
 */
public class Q00350_EnhanceYourWeapon extends Quest
{
	private enum AbsorbCrystalType
	{
		LAST_HIT,
		FULL_PARTY,
		PARTY_ONE_RANDOM,
		PARTY_RANDOM
	}
	
	private static final class LevelingInfo
	{
		private final AbsorbCrystalType _absorbCrystalType;
		private final boolean _isSkillNeeded;
		private final int _chance;
		
		public LevelingInfo(AbsorbCrystalType absorbCrystalType, boolean isSkillNeeded, int chance)
		{
			_absorbCrystalType = absorbCrystalType;
			_isSkillNeeded = isSkillNeeded;
			_chance = chance;
		}
		
		public final AbsorbCrystalType getAbsorbCrystalType()
		{
			return _absorbCrystalType;
		}
		
		public final int getChance()
		{
			return _chance;
		}
		
		public final boolean isSkillNeeded()
		{
			return _isSkillNeeded;
		}
	}
	
	private static final class SoulCrystal
	{
		private final int _level;
		private final int _itemId;
		private final int _leveledItemId;
		
		public SoulCrystal(int level, int itemId, int leveledItemId)
		{
			_level = level;
			_itemId = itemId;
			_leveledItemId = leveledItemId;
		}
		
		public final int getItemId()
		{
			return _itemId;
		}
		
		public final int getLevel()
		{
			return _level;
		}
		
		public final int getLeveledItemId()
		{
			return _leveledItemId;
		}
	}
	
	// NPCs
	private static final int[] STARTING_NPCS =
	{
		30115,
		30856,
		30194
	};
	// Items
	private static final int RED_SOUL_CRYSTAL0_ID = 4629;
	private static final int GREEN_SOUL_CRYSTAL0_ID = 4640;
	private static final int BLUE_SOUL_CRYSTAL0_ID = 4651;
	
	private static final Map<Integer, SoulCrystal> SOUL_CRYSTALS = new HashMap<>();
	// <npcid, <level, LevelingInfo>>
	private static final Map<Integer, Map<Integer, LevelingInfo>> NPC_LEVELING_INFO = new HashMap<>();
	
	public Q00350_EnhanceYourWeapon()
	{
		super(350);
		addStartNpc(STARTING_NPCS);
		addTalkId(STARTING_NPCS);
		load();
		for (int npcId : NPC_LEVELING_INFO.keySet())
		{
			addSkillSeeId(npcId);
			addKillId(npcId);
		}
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final String htmltext = event;
		final QuestState st = getQuestState(player, false);
		if (event.endsWith("-04.htm"))
		{
			st.startQuest();
		}
		else if (event.endsWith("-09.htm"))
		{
			giveItems(player, RED_SOUL_CRYSTAL0_ID, 1);
		}
		else if (event.endsWith("-10.htm"))
		{
			giveItems(player, GREEN_SOUL_CRYSTAL0_ID, 1);
		}
		else if (event.endsWith("-11.htm"))
		{
			giveItems(player, BLUE_SOUL_CRYSTAL0_ID, 1);
		}
		else if (event.equalsIgnoreCase("exit.htm"))
		{
			st.exitQuest(true);
		}
		return htmltext;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (npc.isAttackable() && NPC_LEVELING_INFO.containsKey(npc.getId()))
		{
			levelSoulCrystals((L2Attackable) npc, killer);
		}
		
		return null;
	}
	
	@Override
	public String onSkillSee(L2Npc npc, L2PcInstance caster, Skill skill, L2Object[] targets, boolean isSummon)
	{
		super.onSkillSee(npc, caster, skill, targets, isSummon);
		
		if ((skill == null) || (skill.getId() != 2096))
		{
			return null;
		}
		else if ((caster == null) || caster.isDead())
		{
			return null;
		}
		if (!npc.isAttackable() || npc.isDead() || !NPC_LEVELING_INFO.containsKey(npc.getId()))
		{
			return null;
		}
		
		try
		{
			((L2Attackable) npc).addAbsorber(caster);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "", e);
		}
		return null;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		
		if (st.getState() == State.CREATED)
		{
			st.set("cond", "0");
		}
		if (st.getInt("cond") == 0)
		{
			htmltext = npc.getId() + "-01.htm";
		}
		else if (check(player))
		{
			htmltext = npc.getId() + "-03.htm";
		}
		else if (!hasQuestItems(player, RED_SOUL_CRYSTAL0_ID) && !hasQuestItems(player, GREEN_SOUL_CRYSTAL0_ID) && !hasQuestItems(player, BLUE_SOUL_CRYSTAL0_ID))
		{
			htmltext = npc.getId() + "-21.htm";
		}
		return htmltext;
	}
	
	private static boolean check(L2PcInstance player)
	{
		for (int i = 4629; i < 4665; i++)
		{
			if (hasQuestItems(player, i))
			{
				return true;
			}
		}
		return false;
	}
	
	private static void exchangeCrystal(L2PcInstance player, L2Attackable mob, int takeid, int giveid, boolean broke)
	{
		L2ItemInstance Item = player.getInventory().destroyItemByItemId("SoulCrystal", takeid, 1, player, mob);
		if (Item != null)
		{
			// Prepare inventory update packet
			final InventoryUpdate playerIU = new InventoryUpdate();
			playerIU.addRemovedItem(Item);
			
			// Add new crystal to the killer's inventory
			Item = player.getInventory().addItem("SoulCrystal", giveid, 1, player, mob);
			playerIU.addItem(Item);
			
			// Send a sound event and text message to the player
			if (broke)
			{
				player.sendPacket(SystemMessageId.THE_SOUL_CRYSTAL_BROKE_BECAUSE_IT_WAS_NOT_ABLE_TO_ENDURE_THE_SOUL_ENERGY);
			}
			else
			{
				player.sendPacket(SystemMessageId.THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL);
			}
			
			// Send system message
			final SystemMessage sms = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S1);
			sms.addItemName(giveid);
			player.sendPacket(sms);
			
			// Send inventory update packet
			player.sendPacket(playerIU);
		}
	}
	
	private static SoulCrystal getSCForPlayer(L2PcInstance player)
	{
		final QuestState st = player.getQuestState(Q00350_EnhanceYourWeapon.class.getSimpleName());
		if ((st == null) || !st.isStarted())
		{
			return null;
		}
		
		final L2ItemInstance[] inv = player.getInventory().getItems();
		SoulCrystal ret = null;
		for (L2ItemInstance item : inv)
		{
			final int itemId = item.getId();
			if (!SOUL_CRYSTALS.containsKey(itemId))
			{
				continue;
			}
			
			if (ret != null)
			{
				return null;
			}
			ret = SOUL_CRYSTALS.get(itemId);
		}
		return ret;
	}
	
	private static boolean isPartyLevelingMonster(int npcId)
	{
		for (LevelingInfo li : NPC_LEVELING_INFO.get(npcId).values())
		{
			if (li.getAbsorbCrystalType() != AbsorbCrystalType.LAST_HIT)
			{
				return true;
			}
		}
		return false;
	}
	
	private static void levelCrystal(L2PcInstance player, SoulCrystal sc, L2Attackable mob)
	{
		if ((sc == null) || !NPC_LEVELING_INFO.containsKey(mob.getId()))
		{
			return;
		}
		
		// If the crystal level is way too high for this mob, say that we can't increase it
		if (!NPC_LEVELING_INFO.get(mob.getId()).containsKey(sc.getLevel()))
		{
			player.sendPacket(SystemMessageId.THE_SOUL_CRYSTAL_IS_REFUSING_TO_ABSORB_THE_SOUL);
			return;
		}
		
		if (getRandom(100) <= NPC_LEVELING_INFO.get(mob.getId()).get(sc.getLevel()).getChance())
		{
			exchangeCrystal(player, mob, sc.getItemId(), sc.getLeveledItemId(), false);
		}
		else
		{
			player.sendPacket(SystemMessageId.THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL);
		}
	}
	
	/**
	 * Calculate the leveling chance of Soul Crystals based on the attacker that killed this L2Attackable
	 * @param mob
	 * @param killer The player that last killed this L2Attackable $ Rewrite 06.12.06 - Yesod $ Rewrite 08.01.10 - Gigiikun
	 */
	public static void levelSoulCrystals(L2Attackable mob, L2PcInstance killer)
	{
		// Only L2PcInstance can absorb a soul
		if (killer == null)
		{
			mob.resetAbsorbList();
			return;
		}
		
		final Map<L2PcInstance, SoulCrystal> players = new HashMap<>();
		int maxSCLevel = 0;
		
		// TODO: what if mob support last_hit + party?
		if (isPartyLevelingMonster(mob.getId()) && (killer.getParty() != null))
		{
			// firts get the list of players who has one Soul Cry and the quest
			for (L2PcInstance pl : killer.getParty().getMembers())
			{
				if (pl == null)
				{
					continue;
				}
				
				final SoulCrystal sc = getSCForPlayer(pl);
				if (sc == null)
				{
					continue;
				}
				
				players.put(pl, sc);
				if ((maxSCLevel < sc.getLevel()) && NPC_LEVELING_INFO.get(mob.getId()).containsKey(sc.getLevel()))
				{
					maxSCLevel = sc.getLevel();
				}
			}
		}
		else
		{
			final SoulCrystal sc = getSCForPlayer(killer);
			if (sc != null)
			{
				players.put(killer, sc);
				if ((maxSCLevel < sc.getLevel()) && NPC_LEVELING_INFO.get(mob.getId()).containsKey(sc.getLevel()))
				{
					maxSCLevel = sc.getLevel();
				}
			}
		}
		// Init some useful vars
		final LevelingInfo mainlvlInfo = NPC_LEVELING_INFO.get(mob.getId()).get(maxSCLevel);
		
		if (mainlvlInfo == null)
		{
			/* throw new NullPointerException("Target: "+mob+ " player: "+killer+" level: "+maxSCLevel); */
			return;
		}
		
		// If this mob is not require skill, then skip some checkings
		if (mainlvlInfo.isSkillNeeded())
		{
			// Fail if this L2Attackable isn't absorbed or there's no one in its _absorbersList
			if (!mob.isAbsorbed() /* || _absorbersList == null */)
			{
				mob.resetAbsorbList();
				return;
			}
			
			// Fail if the killer isn't in the _absorbersList of this L2Attackable and mob is not boss
			final AbsorberInfo ai = mob.getAbsorbersList().get(killer.getObjectId());
			boolean isSuccess = true;
			if ((ai == null) || (ai.getObjectId() != killer.getObjectId()))
			{
				isSuccess = false;
			}
			
			// Check if the soul crystal was used when HP of this L2Attackable wasn't higher than half of it
			if ((ai != null) && (ai.getAbsorbedHp() > (mob.getMaxHp() / 2.0)))
			{
				isSuccess = false;
			}
			
			if (!isSuccess)
			{
				mob.resetAbsorbList();
				return;
			}
		}
		
		switch (mainlvlInfo.getAbsorbCrystalType())
		{
			case PARTY_ONE_RANDOM:
			{
				// This is a naive method for selecting a random member. It gets any random party member and
				// then checks if the member has a valid crystal. It does not select the random party member
				// among those who have crystals, only. However, this might actually be correct (same as retail).
				if (killer.getParty() != null)
				{
					final L2PcInstance lucky = killer.getParty().getMembers().get(getRandom(killer.getParty().getMemberCount()));
					levelCrystal(lucky, players.get(lucky), mob);
				}
				else
				{
					levelCrystal(killer, players.get(killer), mob);
				}
				break;
			}
			case PARTY_RANDOM:
			{
				if (killer.getParty() != null)
				{
					final List<L2PcInstance> luckyParty = new ArrayList<>();
					luckyParty.addAll(killer.getParty().getMembers());
					while ((getRandom(100) < 33) && !luckyParty.isEmpty())
					{
						final L2PcInstance lucky = luckyParty.remove(getRandom(luckyParty.size()));
						if (players.containsKey(lucky))
						{
							levelCrystal(lucky, players.get(lucky), mob);
						}
					}
				}
				else if (getRandom(100) < 33)
				{
					levelCrystal(killer, players.get(killer), mob);
				}
				break;
			}
			case FULL_PARTY:
			{
				if (killer.getParty() != null)
				{
					for (L2PcInstance pl : killer.getParty().getMembers())
					{
						levelCrystal(pl, players.get(pl), mob);
					}
				}
				else
				{
					levelCrystal(killer, players.get(killer), mob);
				}
				break;
			}
			case LAST_HIT:
			{
				levelCrystal(killer, players.get(killer), mob);
				break;
			}
		}
	}
	
	/**
	 * TODO: Implement using DocumentParser.
	 */
	private static void load()
	{
		try
		{
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringComments(true);
			
			final File file = new File(Config.DATAPACK_ROOT, "data/LevelUpCrystalData.xml");
			if (!file.exists())
			{
				LOGGER.severe("[EnhanceYourWeapon] Missing LevelUpCrystalData.xml. The quest wont work without it!");
				return;
			}
			
			final Document doc = factory.newDocumentBuilder().parse(file);
			final Node first = doc.getFirstChild();
			if ((first != null) && "list".equalsIgnoreCase(first.getNodeName()))
			{
				for (Node n = first.getFirstChild(); n != null; n = n.getNextSibling())
				{
					if ("crystal".equalsIgnoreCase(n.getNodeName()))
					{
						for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
						{
							if ("item".equalsIgnoreCase(d.getNodeName()))
							{
								final NamedNodeMap attrs = d.getAttributes();
								Node att = attrs.getNamedItem("itemId");
								if (att == null)
								{
									LOGGER.severe("[EnhanceYourWeapon] Missing itemId in Crystal List, skipping");
									continue;
								}
								final int itemId = Integer.parseInt(attrs.getNamedItem("itemId").getNodeValue());
								
								att = attrs.getNamedItem("level");
								if (att == null)
								{
									LOGGER.severe("[EnhanceYourWeapon] Missing level in Crystal List itemId: " + itemId + ", skipping");
									continue;
								}
								final int level = Integer.parseInt(attrs.getNamedItem("level").getNodeValue());
								
								att = attrs.getNamedItem("leveledItemId");
								if (att == null)
								{
									LOGGER.severe("[EnhanceYourWeapon] Missing leveledItemId in Crystal List itemId: " + itemId + ", skipping");
									continue;
								}
								final int leveledItemId = Integer.parseInt(attrs.getNamedItem("leveledItemId").getNodeValue());
								
								SOUL_CRYSTALS.put(itemId, new SoulCrystal(level, itemId, leveledItemId));
							}
						}
					}
					else if ("npc".equalsIgnoreCase(n.getNodeName()))
					{
						for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
						{
							if ("item".equalsIgnoreCase(d.getNodeName()))
							{
								NamedNodeMap attrs = d.getAttributes();
								Node att = attrs.getNamedItem("npcId");
								if (att == null)
								{
									LOGGER.severe("[EnhanceYourWeapon] Missing npcId in NPC List, skipping");
									continue;
								}
								
								final int npcId = Integer.parseInt(att.getNodeValue());
								final Map<Integer, LevelingInfo> temp = new HashMap<>();
								for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
								{
									boolean isSkillNeeded = false;
									int chance = 5;
									AbsorbCrystalType absorbType = AbsorbCrystalType.LAST_HIT;
									
									if ("detail".equalsIgnoreCase(cd.getNodeName()))
									{
										attrs = cd.getAttributes();
										
										att = attrs.getNamedItem("absorbType");
										if (att != null)
										{
											absorbType = Enum.valueOf(AbsorbCrystalType.class, att.getNodeValue());
										}
										
										att = attrs.getNamedItem("chance");
										if (att != null)
										{
											chance = Integer.parseInt(att.getNodeValue());
										}
										
										att = attrs.getNamedItem("skill");
										if (att != null)
										{
											isSkillNeeded = Boolean.parseBoolean(att.getNodeValue());
										}
										
										final Node att1 = attrs.getNamedItem("maxLevel");
										final Node att2 = attrs.getNamedItem("levelList");
										if ((att1 == null) && (att2 == null))
										{
											LOGGER.severe("[EnhanceYourWeapon] Missing maxlevel/levelList in NPC List npcId: " + npcId + ", skipping");
											continue;
										}
										final LevelingInfo info = new LevelingInfo(absorbType, isSkillNeeded, chance);
										if (att1 != null)
										{
											final int maxLevel = Integer.parseInt(att1.getNodeValue());
											for (int i = 0; i <= maxLevel; i++)
											{
												temp.put(i, info);
											}
										}
										else if (att2 != null)
										{
											final StringTokenizer st = new StringTokenizer(att2.getNodeValue(), ",");
											final int tokenCount = st.countTokens();
											for (int i = 0; i < tokenCount; i++)
											{
												Integer value = Integer.decode(st.nextToken().trim());
												if (value == null)
												{
													LOGGER.severe("[EnhanceYourWeapon] Bad Level value!! npcId: " + npcId + " token: " + i);
													value = 0;
												}
												temp.put(value, info);
											}
										}
									}
								}
								
								if (temp.isEmpty())
								{
									LOGGER.severe("[EnhanceYourWeapon] No leveling info for npcId: " + npcId + ", skipping");
									continue;
								}
								NPC_LEVELING_INFO.put(npcId, temp);
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "[EnhanceYourWeapon] Could not parse LevelUpCrystalData.xml file: " + e.getMessage(), e);
		}
		LOGGER.info("[EnhanceYourWeapon] Loaded " + SOUL_CRYSTALS.size() + " Soul Crystal data.");
		LOGGER.info("[EnhanceYourWeapon] Loaded " + NPC_LEVELING_INFO.size() + " npc Leveling info data.");
	}
}

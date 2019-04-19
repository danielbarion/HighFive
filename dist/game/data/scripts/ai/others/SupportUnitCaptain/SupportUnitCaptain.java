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
package ai.others.SupportUnitCaptain;

import com.l2jmobius.gameserver.model.ClanPrivilege;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.clientpackets.RequestAcquireSkill;

import ai.AbstractNpcAI;

/**
 * Support Unit Captain AI.
 * @author St3eT
 */
public final class SupportUnitCaptain extends AbstractNpcAI
{
	// NPCs
	private static final int[] UNIT_CAPTAIN =
	{
		35662, // Shanty Fortress
		35694, // Southern Fortress
		35731, // Hive Fortress
		35763, // Valley Fortress
		35800, // Ivory Fortress
		35831, // Narsell Fortress
		35863, // Bayou Fortress
		35900, // White Sands Fortress
		35932, // Borderland Fortress
		35970, // Swamp Fortress
		36007, // Archaic Fortress
		36039, // Floran Fortress
		36077, // Cloud Mountain
		36114, // Tanor Fortress
		36145, // Dragonspine Fortress
		36177, // Antharas's Fortress
		36215, // Western Fortress
		36253, // Hunter's Fortress
		36290, // Aaru Fortress
		36322, // Demon Fortress
		36360, // Monastic Fortress
	};
	// Items
	private static final int EPAULETTE = 9912; // Knight's Epaulette
	private static final int RED_MEDITATION = 9931; // Red Talisman of Meditation
	private static final int BLUE_DIV_PROTECTION = 9932; // Blue Talisman - Divine Protection
	private static final int BLUE_EXPLOSION = 10416; // Blue Talisman - Explosion
	private static final int BLUE_M_EXPLOSION = 10417; // Blue Talisman - Magic Explosion
	private static final int RED_MIN_CLARITY = 9917; // Red Talisman of Minimum Clarity
	private static final int RED_MAX_CLARITY = 9918; // Red Talisman of Maximum Clarity
	private static final int RED_MENTAL_REG = 9928; // Red Talisman of Mental Regeneration
	private static final int BLUE_PROTECTION = 9929; // Blue Talisman of Protection
	private static final int BLUE_INVIS = 9920; // Blue Talisman of Invisibility
	private static final int BLUE_DEFENSE = 9916; // Blue Talisman of Defense
	private static final int BLACK_ESCAPE = 9923; // Black Talisman - Escape
	private static final int BLUE_HEALING = 9924; // Blue Talisman of Healing
	private static final int RED_RECOVERY = 9925; // Red Talisman of Recovery
	private static final int BLUE_DEFENSE2 = 9926; // Blue Talisman of Defense
	private static final int BLUE_M_DEFENSE = 9927; // Blue Talisman of Magic Defense
	private static final int RED_LIFE_FORCE = 10518; // Red Talisman - Life Force
	private static final int BLUE_GREAT_HEALING = 10424; // Blue Talisman - Greater Healing
	private static final int WHITE_FIRE = 10421; // White Talisman - Fire
	private static final int[] COMMON_TALISMANS =
	{
		9914, // Blue Talisman of Power
		9915, // Blue Talisman of Wild Magic
		9920, // Blue Talisman of Invisibility
		9921, // Blue Talisman - Shield Protection
		9922, // Black Talisman - Mending
		9933, // Yellow Talisman of Power
		9934, // Yellow Talisman of Violent Haste
		9935, // Yellow Talisman of Arcane Defense
		9936, // Yellow Talisman of Arcane Power
		9937, // Yellow Talisman of Arcane Haste
		9938, // Yellow Talisman of Accuracy
		9939, // Yellow Talisman of Defense
		9940, // Yellow Talisman of Alacrity
		9941, // Yellow Talisman of Speed
		9942, // Yellow Talisman of Critical Reduction
		9943, // Yellow Talisman of Critical Damage
		9944, // Yellow Talisman of Critical Dodging
		9945, // Yellow Talisman of Evasion
		9946, // Yellow Talisman of Healing
		9947, // Yellow Talisman of CP Regeneration
		9948, // Yellow Talisman of Physical Regeneration
		9949, // Yellow Talisman of Mental Regeneration
		9950, // Grey Talisman of Weight Training
		9952, // Orange Talisman - Hot Springs CP Potion
		9953, // Orange Talisman - Elixir of Life
		9954, // Orange Talisman - Elixir of Mental Strength
		9955, // Black Talisman - Vocalization
		9956, // Black Talisman - Arcane Freedom
		9957, // Black Talisman - Physical Freedom
		9958, // Black Talisman - Rescue
		9959, // Black Talisman - Free Speech
		9960, // White Talisman of Bravery
		9961, // White Talisman of Motion
		9962, // White Talisman of Grounding
		9963, // White Talisman of Attention
		9964, // White Talisman of Bandages
		9965, // White Talisman of Protection
		10418, // White Talisman - Storm
		10420, // White Talisman - Water
		10519, // White Talisman - Earth
		10422, // White Talisman - Light
		10423, // Blue Talisman - Self-Destruction
	};
	
	private SupportUnitCaptain()
	{
		addStartNpc(UNIT_CAPTAIN);
		addTalkId(UNIT_CAPTAIN);
		addFirstTalkId(UNIT_CAPTAIN);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		
		final int fortOwner = npc.getFort().getOwnerClan() == null ? 0 : npc.getFort().getOwnerClan().getId();
		if ((player.getClan() == null) || (player.getClanId() != fortOwner))
		{
			return "unitcaptain-04.html";
		}
		
		String htmltext = null;
		int itemId = 0;
		switch (event)
		{
			case "unitcaptain.html":
			case "unitcaptain-01.html":
			{
				htmltext = event;
				break;
			}
			case "giveTalisman":
			{
				if (getQuestItemsCount(player, EPAULETTE) < 10)
				{
					htmltext = "unitcaptain-05.html";
					break;
				}
				
				final int categoryChance = getRandom(100);
				if (categoryChance <= 5)
				{
					final int chance = getRandom(100);
					if (chance <= 25)
					{
						itemId = RED_MEDITATION;
					}
					else if (chance <= 50)
					{
						itemId = BLUE_DIV_PROTECTION;
					}
					else if (chance <= 75)
					{
						itemId = BLUE_EXPLOSION;
					}
					else
					{
						itemId = BLUE_M_EXPLOSION;
					}
				}
				else if (categoryChance <= 15)
				{
					final int chance = getRandom(100);
					if (chance <= 20)
					{
						itemId = RED_MIN_CLARITY;
					}
					else if (chance <= 40)
					{
						itemId = RED_MAX_CLARITY;
					}
					else if (chance <= 60)
					{
						itemId = RED_MENTAL_REG;
					}
					else if (chance <= 80)
					{
						itemId = BLUE_PROTECTION;
					}
					else
					{
						itemId = BLUE_INVIS;
					}
				}
				else if (categoryChance <= 30)
				{
					final int chance = getRandom(100);
					if (chance <= 12)
					{
						itemId = BLUE_DEFENSE;
					}
					else if (chance <= 25)
					{
						itemId = BLACK_ESCAPE;
					}
					else if (chance <= 37)
					{
						itemId = BLUE_HEALING;
					}
					else if (chance <= 50)
					{
						itemId = RED_RECOVERY;
					}
					else if (chance <= 62)
					{
						itemId = BLUE_DEFENSE2;
					}
					else if (chance <= 75)
					{
						itemId = BLUE_M_DEFENSE;
					}
					else if (chance <= 87)
					{
						itemId = RED_LIFE_FORCE;
					}
					else
					{
						itemId = BLUE_GREAT_HEALING;
					}
				}
				else
				{
					final int chance = getRandom(46);
					if (chance <= 41)
					{
						itemId = COMMON_TALISMANS[chance];
					}
					else
					{
						itemId = WHITE_FIRE;
					}
				}
				takeItems(player, EPAULETTE, 10);
				giveItems(player, itemId, 1);
				htmltext = "unitcaptain-02.html";
				break;
			}
			case "squadSkill":
			{
				if (player.isClanLeader() || player.hasClanPrivilege(ClanPrivilege.CL_TROOPS_FAME))
				{
					RequestAcquireSkill.showSubUnitSkillList(player);
				}
				else
				{
					htmltext = "unitcaptain-03.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final int fortOwner = npc.getFort().getOwnerClan() == null ? 0 : npc.getFort().getOwnerClan().getId();
		return ((player.getClan() != null) && (player.getClanId() == fortOwner)) ? "unitcaptain.html" : "unitcaptain-04.html";
	}
	
	public static void main(String[] args)
	{
		new SupportUnitCaptain();
	}
}
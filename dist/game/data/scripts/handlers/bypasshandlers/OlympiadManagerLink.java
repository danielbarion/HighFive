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
package handlers.bypasshandlers;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.data.xml.impl.MultisellData;
import com.l2jmobius.gameserver.handler.IBypassHandler;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.L2Summon;
import com.l2jmobius.gameserver.model.actor.instance.L2OlympiadManagerInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.entity.Hero;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.olympiad.CompetitionType;
import com.l2jmobius.gameserver.model.olympiad.Olympiad;
import com.l2jmobius.gameserver.model.olympiad.OlympiadManager;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ExHeroList;
import com.l2jmobius.gameserver.network.serverpackets.InventoryUpdate;
import com.l2jmobius.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import com.l2jmobius.gameserver.util.Util;

/**
 * @author DS
 */
public class OlympiadManagerLink implements IBypassHandler
{
	private static final String[] COMMANDS =
	{
		"olympiaddesc",
		"olympiadnoble",
		"olybuff",
		"olympiad"
	};
	
	private static final String FEWER_THAN = "Fewer than " + Config.ALT_OLY_REG_DISPLAY;
	private static final String MORE_THAN = "More than " + Config.ALT_OLY_REG_DISPLAY;
	private static final int GATE_PASS = Config.ALT_OLY_COMP_RITEM;
	
	private static final SkillHolder[] ALLOWED_BUFFS =
	{
		new SkillHolder(4357, 2), // Haste Lv2
		new SkillHolder(4342, 2), // Wind Walk Lv2
		new SkillHolder(4356, 3), // Empower Lv3
		new SkillHolder(4355, 3), // Acumen Lv3
		new SkillHolder(4351, 6), // Concentration Lv6
		new SkillHolder(4345, 3), // Might Lv3
		new SkillHolder(4358, 3), // Guidance Lv3
		new SkillHolder(4359, 3), // Focus Lv3
		new SkillHolder(4360, 3), // Death Whisper Lv3
		new SkillHolder(4352, 2), // Berserker Spirit Lv2
	};
	
	@Override
	public final boolean useBypass(String command, L2PcInstance activeChar, L2Character target)
	{
		if (!(target instanceof L2OlympiadManagerInstance))
		{
			return false;
		}
		
		try
		{
			if (command.toLowerCase().startsWith("olympiaddesc"))
			{
				final int val = Integer.parseInt(command.substring(13, 14));
				final String suffix = command.substring(14);
				((L2OlympiadManagerInstance) target).showChatWindow(activeChar, val, suffix);
			}
			else if (command.toLowerCase().startsWith("olympiadnoble"))
			{
				final NpcHtmlMessage html = new NpcHtmlMessage(target.getObjectId());
				if (activeChar.isCursedWeaponEquipped())
				{
					html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_cursed_weapon.htm");
					activeChar.sendPacket(html);
					return false;
				}
				if (activeChar.getClassIndex() != 0)
				{
					html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_sub.htm");
					html.replace("%objectId%", String.valueOf(target.getObjectId()));
					activeChar.sendPacket(html);
					return false;
				}
				if (!activeChar.isNoble() || (activeChar.getClassId().level() < 3))
				{
					html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_thirdclass.htm");
					html.replace("%objectId%", String.valueOf(target.getObjectId()));
					activeChar.sendPacket(html);
					return false;
				}
				
				final int val = Integer.parseInt(command.substring(14));
				switch (val)
				{
					case 0: // H5 match selection
					{
						if (!OlympiadManager.getInstance().isRegistered(activeChar))
						{
							html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_desc2a.htm");
							html.replace("%objectId%", String.valueOf(target.getObjectId()));
							html.replace("%olympiad_period%", String.valueOf(Olympiad.getInstance().getPeriod()));
							html.replace("%olympiad_cycle%", String.valueOf(Olympiad.getInstance().getCurrentCycle()));
							html.replace("%olympiad_opponent%", String.valueOf(OlympiadManager.getInstance().getCountOpponents()));
							activeChar.sendPacket(html);
						}
						else
						{
							html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_unregister.htm");
							html.replace("%objectId%", String.valueOf(target.getObjectId()));
							activeChar.sendPacket(html);
						}
						break;
					}
					case 1: // unregister
					{
						OlympiadManager.getInstance().unRegisterNoble(activeChar);
						break;
					}
					case 2: // show waiting list | TODO: cleanup (not used anymore)
					{
						final int nonClassed = OlympiadManager.getInstance().getRegisteredNonClassBased().size();
						final int teams = OlympiadManager.getInstance().getRegisteredTeamsBased().size();
						final Collection<List<Integer>> allClassed = OlympiadManager.getInstance().getRegisteredClassBased().values();
						int classed = 0;
						if (!allClassed.isEmpty())
						{
							for (List<Integer> cls : allClassed)
							{
								if (cls != null)
								{
									classed += cls.size();
								}
							}
						}
						html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_registered.htm");
						if (Config.ALT_OLY_REG_DISPLAY > 0)
						{
							html.replace("%listClassed%", classed < Config.ALT_OLY_REG_DISPLAY ? FEWER_THAN : MORE_THAN);
							html.replace("%listNonClassedTeam%", teams < Config.ALT_OLY_REG_DISPLAY ? FEWER_THAN : MORE_THAN);
							html.replace("%listNonClassed%", nonClassed < Config.ALT_OLY_REG_DISPLAY ? FEWER_THAN : MORE_THAN);
						}
						else
						{
							html.replace("%listClassed%", String.valueOf(classed));
							html.replace("%listNonClassedTeam%", String.valueOf(teams));
							html.replace("%listNonClassed%", String.valueOf(nonClassed));
						}
						html.replace("%objectId%", String.valueOf(target.getObjectId()));
						activeChar.sendPacket(html);
						break;
					}
					case 3: // There are %points% Grand Olympiad points granted for this event. | TODO: cleanup (not used anymore)
					{
						final int points = Olympiad.getInstance().getNoblePoints(activeChar.getObjectId());
						html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_points1.htm");
						html.replace("%points%", String.valueOf(points));
						html.replace("%objectId%", String.valueOf(target.getObjectId()));
						activeChar.sendPacket(html);
						break;
					}
					case 4: // register non classed
					{
						OlympiadManager.getInstance().registerNoble(activeChar, CompetitionType.NON_CLASSED);
						break;
					}
					case 5: // register classed
					{
						OlympiadManager.getInstance().registerNoble(activeChar, CompetitionType.CLASSED);
						break;
					}
					case 6: // request tokens reward
					{
						if (activeChar.getVariables().getInt(Olympiad.UNCLAIMED_OLYMPIAD_PASSES_VAR, 0) > 0)
						{
							html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_settle.htm");
							html.replace("%objectId%", String.valueOf(target.getObjectId()));
							activeChar.sendPacket(html);
						}
						else
						{
							html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_nopoints2.htm");
							html.replace("%objectId%", String.valueOf(target.getObjectId()));
							activeChar.sendPacket(html);
						}
						break;
					}
					case 7: // Equipment Rewards
					{
						MultisellData.getInstance().separateAndSend(102, activeChar, (L2Npc) target, false);
						break;
					}
					case 8: // Misc. Rewards
					{
						MultisellData.getInstance().separateAndSend(103, activeChar, (L2Npc) target, false);
						break;
					}
					case 9: // Your Grand Olympiad Score from the previous period is %points% point(s) | TODO: cleanup (not used anymore)
					{
						final int point = Olympiad.getInstance().getLastNobleOlympiadPoints(activeChar.getObjectId());
						html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "noble_points2.htm");
						html.replace("%points%", String.valueOf(point));
						html.replace("%objectId%", String.valueOf(target.getObjectId()));
						activeChar.sendPacket(html);
						break;
					}
					case 10: // give tokens to player
					{
						final int passes = activeChar.getVariables().getInt(Olympiad.UNCLAIMED_OLYMPIAD_PASSES_VAR, 0);
						if (passes > 0)
						{
							activeChar.getVariables().remove(Olympiad.UNCLAIMED_OLYMPIAD_PASSES_VAR);
							final L2ItemInstance item = activeChar.getInventory().addItem("Olympiad", GATE_PASS, passes, activeChar, target);
							final InventoryUpdate iu = new InventoryUpdate();
							iu.addModifiedItem(item);
							activeChar.sendPacket(iu);
							final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S2_S1_S);
							sm.addLong(passes);
							sm.addItemName(item);
							activeChar.sendPacket(sm);
						}
						break;
					}
					case 11: // register team
					{
						OlympiadManager.getInstance().registerNoble(activeChar, CompetitionType.TEAMS);
						break;
					}
					default:
					{
						LOGGER.warning("Olympiad System: Couldnt send packet for request " + val);
						break;
					}
				}
			}
			else if (command.toLowerCase().startsWith("olybuff"))
			{
				int buffCount = activeChar.getOlympiadBuffCount();
				if (buffCount <= 0)
				{
					return false;
				}
				
				final NpcHtmlMessage html = new NpcHtmlMessage(target.getObjectId());
				final String[] params = command.split(" ");
				
				if (!Util.isDigit(params[1]))
				{
					LOGGER.warning("Olympiad Buffer Warning: npcId = " + target.getId() + " has invalid buffGroup set in the bypass for the buff selected: " + params[1]);
					return false;
				}
				
				final int index = Integer.parseInt(params[1]);
				if ((index < 0) || (index >= ALLOWED_BUFFS.length))
				{
					LOGGER.warning("Olympiad Buffer Warning: npcId = " + target.getId() + " has invalid index sent in the bypass: " + index);
					return false;
				}
				
				if (buffCount > 0)
				{
					final Skill skill = ALLOWED_BUFFS[index].getSkill();
					if (skill != null)
					{
						target.setTarget(activeChar);
						
						activeChar.setOlympiadBuffCount(--buffCount);
						
						target.broadcastPacket(new MagicSkillUse(target, activeChar, skill.getId(), skill.getLevel(), 0, 0));
						skill.applyEffects(activeChar, activeChar);
						final L2Summon summon = activeChar.getSummon();
						if (summon != null)
						{
							target.broadcastPacket(new MagicSkillUse(target, summon, skill.getId(), skill.getLevel(), 0, 0));
							skill.applyEffects(summon, summon);
						}
					}
				}
				
				if (buffCount > 0)
				{
					html.setFile(activeChar, buffCount == Config.ALT_OLY_MAX_BUFFS ? Olympiad.OLYMPIAD_HTML_PATH + "olympiad_buffs.htm" : Olympiad.OLYMPIAD_HTML_PATH + "olympiad_5buffs.htm");
					html.replace("%objectId%", String.valueOf(target.getObjectId()));
					activeChar.sendPacket(html);
				}
				else
				{
					html.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "olympiad_nobuffs.htm");
					html.replace("%objectId%", String.valueOf(target.getObjectId()));
					activeChar.sendPacket(html);
					target.decayMe();
				}
			}
			else if (command.toLowerCase().startsWith("olympiad"))
			{
				final int val = Integer.parseInt(command.substring(9, 10));
				
				final NpcHtmlMessage reply = new NpcHtmlMessage(target.getObjectId());
				
				switch (val)
				{
					case 2: // show rank for a specific class
					{
						// for example >> Olympiad 1_88
						final int classId = Integer.parseInt(command.substring(11));
						if (((classId >= 88) && (classId <= 118)) || ((classId >= 131) && (classId <= 134)) || (classId == 136))
						{
							final List<String> names = Olympiad.getInstance().getClassLeaderBoard(classId);
							reply.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "olympiad_ranking.htm");
							int index = 1;
							for (String name : names)
							{
								reply.replace("%place" + index + "%", String.valueOf(index));
								reply.replace("%rank" + index + "%", name);
								index++;
								if (index > 10)
								{
									break;
								}
							}
							for (; index <= 10; index++)
							{
								reply.replace("%place" + index + "%", "");
								reply.replace("%rank" + index + "%", "");
							}
							reply.replace("%objectId%", String.valueOf(target.getObjectId()));
							activeChar.sendPacket(reply);
						}
						break;
					}
					case 4: // hero list
					{
						activeChar.sendPacket(new ExHeroList());
						break;
					}
					case 5: // Hero Certification
					{
						if (Hero.getInstance().isUnclaimedHero(activeChar.getObjectId()))
						{
							Hero.getInstance().claimHero(activeChar);
							reply.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "hero_receive.htm");
						}
						else
						{
							reply.setFile(activeChar, Olympiad.OLYMPIAD_HTML_PATH + "hero_notreceive.htm");
						}
						activeChar.sendPacket(reply);
						break;
					}
					default:
					{
						LOGGER.warning("Olympiad System: Couldnt send packet for request " + val);
						break;
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception in " + getClass().getSimpleName(), e);
		}
		
		return true;
	}
	
	@Override
	public final String[] getBypassList()
	{
		return COMMANDS;
	}
}

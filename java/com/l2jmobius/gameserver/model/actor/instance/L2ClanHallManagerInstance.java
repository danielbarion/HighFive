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
package com.l2jmobius.gameserver.model.actor.instance;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.cache.HtmCache;
import com.l2jmobius.gameserver.data.sql.impl.TeleportLocationTable;
import com.l2jmobius.gameserver.data.xml.impl.SkillData;
import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.instancemanager.CHSiegeManager;
import com.l2jmobius.gameserver.instancemanager.ClanHallManager;
import com.l2jmobius.gameserver.model.ClanPrivilege;
import com.l2jmobius.gameserver.model.L2TeleportLocation;
import com.l2jmobius.gameserver.model.PcCondOverride;
import com.l2jmobius.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jmobius.gameserver.model.effects.L2EffectType;
import com.l2jmobius.gameserver.model.entity.ClanHall;
import com.l2jmobius.gameserver.model.entity.clanhall.AuctionableHall;
import com.l2jmobius.gameserver.model.entity.clanhall.SiegableHall;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import com.l2jmobius.gameserver.network.serverpackets.AgitDecoInfo;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;

public class L2ClanHallManagerInstance extends L2MerchantInstance
{
	protected static final int COND_OWNER_FALSE = 0;
	protected static final int COND_ALL_FALSE = 1;
	protected static final int COND_BUSY_BECAUSE_OF_SIEGE = 2;
	protected static final int COND_OWNER = 3;
	private int _clanHallId = -1;
	
	/**
	 * Creates clan hall manager.
	 * @param template the clan hall manager NPC template
	 */
	public L2ClanHallManagerInstance(L2NpcTemplate template)
	{
		super(template);
		setInstanceType(InstanceType.L2ClanHallManagerInstance);
	}
	
	@Override
	public boolean isWarehouse()
	{
		return true;
	}
	
	@Override
	public void onBypassFeedback(L2PcInstance player, String command)
	{
		if (getClanHall().isSiegableHall() && ((SiegableHall) getClanHall()).isInSiege())
		{
			return;
		}
		
		final int condition = validateCondition(player);
		if (condition <= COND_ALL_FALSE)
		{
			return;
		}
		
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (condition == COND_OWNER)
		{
			final StringTokenizer st = new StringTokenizer(command, " ");
			final String actualCommand = st.nextToken(); // Get actual command
			String val = "";
			if (st.countTokens() >= 1)
			{
				val = st.nextToken();
			}
			
			if (actualCommand.equalsIgnoreCase("banish_foreigner"))
			{
				final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
				if (player.hasClanPrivilege(ClanPrivilege.CH_DISMISS))
				{
					if (val.equalsIgnoreCase("list"))
					{
						html.setFile(player, "data/html/clanHallManager/banish-list.htm");
					}
					else if (val.equalsIgnoreCase("banish"))
					{
						getClanHall().banishForeigners();
						html.setFile(player, "data/html/clanHallManager/banish.htm");
					}
				}
				else
				{
					html.setFile(player, "data/html/clanHallManager/not_authorized.htm");
				}
				sendHtmlMessage(player, html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("manage_vault"))
			{
				final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
				if (player.hasClanPrivilege(ClanPrivilege.CL_VIEW_WAREHOUSE))
				{
					if (getClanHall().getLease() <= 0)
					{
						html.setFile(player, "data/html/clanHallManager/vault-chs.htm");
					}
					else
					{
						html.setFile(player, "data/html/clanHallManager/vault.htm");
						html.replace("%rent%", String.valueOf(getClanHall().getLease()));
						html.replace("%date%", format.format(getClanHall().getPaidUntil()));
					}
					sendHtmlMessage(player, html);
				}
				else
				{
					html.setFile(player, "data/html/clanHallManager/not_authorized.htm");
					sendHtmlMessage(player, html);
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("door"))
			{
				final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
				if (player.hasClanPrivilege(ClanPrivilege.CH_OPEN_DOOR))
				{
					if (val.equalsIgnoreCase("open"))
					{
						getClanHall().openCloseDoors(true);
						html.setFile(player, "data/html/clanHallManager/door-open.htm");
					}
					else if (val.equalsIgnoreCase("close"))
					{
						getClanHall().openCloseDoors(false);
						html.setFile(player, "data/html/clanHallManager/door-close.htm");
					}
					else
					{
						html.setFile(player, "data/html/clanHallManager/door.htm");
					}
					sendHtmlMessage(player, html);
				}
				else
				{
					html.setFile(player, "data/html/clanHallManager/not_authorized.htm");
					sendHtmlMessage(player, html);
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("functions"))
			{
				if (val.equalsIgnoreCase("tele"))
				{
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					if (getClanHall().getFunction(ClanHall.FUNC_TELEPORT) == null)
					{
						html.setFile(player, "data/html/clanHallManager/chamberlain-nac.htm");
					}
					else
					{
						html.setFile(player, "data/html/clanHallManager/tele" + getClanHall().getLocation() + getClanHall().getFunction(ClanHall.FUNC_TELEPORT).getLvl() + ".htm");
					}
					sendHtmlMessage(player, html);
				}
				else if (val.equalsIgnoreCase("item_creation"))
				{
					if (getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE) == null)
					{
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player, "data/html/clanHallManager/chamberlain-nac.htm");
						sendHtmlMessage(player, html);
						return;
					}
					if (st.countTokens() < 1)
					{
						return;
					}
					final int valbuy = Integer.parseInt(st.nextToken()) + (getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE).getLvl() * 100000);
					showBuyWindow(player, valbuy);
				}
				else if (val.equalsIgnoreCase("support"))
				{
					
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					if (getClanHall().getFunction(ClanHall.FUNC_SUPPORT) == null)
					{
						html.setFile(player, "data/html/clanHallManager/chamberlain-nac.htm");
					}
					else
					{
						html.setFile(player, "data/html/clanHallManager/support" + getClanHall().getFunction(ClanHall.FUNC_SUPPORT).getLvl() + ".htm");
						html.replace("%mp%", String.valueOf((int) getCurrentMp()));
					}
					sendHtmlMessage(player, html);
				}
				else if (val.equalsIgnoreCase("back"))
				{
					showChatWindow(player);
				}
				else
				{
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player, "data/html/clanHallManager/functions.htm");
					if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP) != null)
					{
						html.replace("%xp_regen%", String.valueOf(getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP).getLvl()));
					}
					else
					{
						html.replace("%xp_regen%", "0");
					}
					if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP) != null)
					{
						html.replace("%hp_regen%", String.valueOf(getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP).getLvl()));
					}
					else
					{
						html.replace("%hp_regen%", "0");
					}
					if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP) != null)
					{
						html.replace("%mp_regen%", String.valueOf(getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP).getLvl()));
					}
					else
					{
						html.replace("%mp_regen%", "0");
					}
					sendHtmlMessage(player, html);
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("manage"))
			{
				if (player.hasClanPrivilege(ClanPrivilege.CH_SET_FUNCTIONS))
				{
					if (val.equalsIgnoreCase("recovery"))
					{
						if (st.countTokens() >= 1)
						{
							if (getClanHall().getOwnerId() == 0)
							{
								player.sendMessage("This clan hall has no owner, you cannot change the configuration.");
								return;
							}
							val = st.nextToken();
							if (val.equalsIgnoreCase("hp_cancel"))
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-cancel.htm");
								html.replace("%apply%", "recovery hp 0");
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("mp_cancel"))
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-cancel.htm");
								html.replace("%apply%", "recovery mp 0");
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("exp_cancel"))
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-cancel.htm");
								html.replace("%apply%", "recovery exp 0");
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("edit_hp"))
							{
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-apply.htm");
								html.replace("%name%", "Fireplace (HP Recovery Device)");
								final int percent = Integer.parseInt(val);
								int cost;
								switch (percent)
								{
									case 20:
									{
										cost = Config.CH_HPREG1_FEE;
										break;
									}
									case 40:
									{
										cost = Config.CH_HPREG2_FEE;
										break;
									}
									case 80:
									{
										cost = Config.CH_HPREG3_FEE;
										break;
									}
									case 100:
									{
										cost = Config.CH_HPREG4_FEE;
										break;
									}
									case 120:
									{
										cost = Config.CH_HPREG5_FEE;
										break;
									}
									case 140:
									{
										cost = Config.CH_HPREG6_FEE;
										break;
									}
									case 160:
									{
										cost = Config.CH_HPREG7_FEE;
										break;
									}
									case 180:
									{
										cost = Config.CH_HPREG8_FEE;
										break;
									}
									case 200:
									{
										cost = Config.CH_HPREG9_FEE;
										break;
									}
									case 220:
									{
										cost = Config.CH_HPREG10_FEE;
										break;
									}
									case 240:
									{
										cost = Config.CH_HPREG11_FEE;
										break;
									}
									case 260:
									{
										cost = Config.CH_HPREG12_FEE;
										break;
									}
									default:
									{
										cost = Config.CH_HPREG13_FEE;
										break;
									}
								}
								
								html.replace("%cost%", cost + "</font>Adena /" + (Config.CH_HPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Provides additional HP recovery for clan members in the clan hall.<font color=\"00FFFF\">" + percent + "%</font>");
								html.replace("%apply%", "recovery hp " + percent);
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("edit_mp"))
							{
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-apply.htm");
								html.replace("%name%", "Carpet (MP Recovery)");
								final int percent = Integer.parseInt(val);
								int cost;
								switch (percent)
								{
									case 5:
									{
										cost = Config.CH_MPREG1_FEE;
										break;
									}
									case 10:
									{
										cost = Config.CH_MPREG2_FEE;
										break;
									}
									case 15:
									{
										cost = Config.CH_MPREG3_FEE;
										break;
									}
									case 30:
									{
										cost = Config.CH_MPREG4_FEE;
										break;
									}
									default:
									{
										cost = Config.CH_MPREG5_FEE;
										break;
									}
								}
								html.replace("%cost%", cost + "</font>Adena /" + (Config.CH_MPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Provides additional MP recovery for clan members in the clan hall.<font color=\"00FFFF\">" + percent + "%</font>");
								html.replace("%apply%", "recovery mp " + percent);
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("edit_exp"))
							{
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-apply.htm");
								html.replace("%name%", "Chandelier (EXP Recovery Device)");
								final int percent = Integer.parseInt(val);
								int cost;
								switch (percent)
								{
									case 5:
									{
										cost = Config.CH_EXPREG1_FEE;
										break;
									}
									case 10:
									{
										cost = Config.CH_EXPREG2_FEE;
										break;
									}
									case 15:
									{
										cost = Config.CH_EXPREG3_FEE;
										break;
									}
									case 25:
									{
										cost = Config.CH_EXPREG4_FEE;
										break;
									}
									case 35:
									{
										cost = Config.CH_EXPREG5_FEE;
										break;
									}
									case 40:
									{
										cost = Config.CH_EXPREG6_FEE;
										break;
									}
									default:
									{
										cost = Config.CH_EXPREG7_FEE;
										break;
									}
								}
								html.replace("%cost%", cost + "</font>Adena /" + (Config.CH_EXPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Restores the Exp of any clan member who is resurrected in the clan hall.<font color=\"00FFFF\">" + percent + "%</font>");
								html.replace("%apply%", "recovery exp " + percent);
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("hp"))
							{
								if (st.countTokens() >= 1)
								{
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player, "data/html/clanHallManager/functions-apply_confirmed.htm");
									if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP) != null)
									{
										if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP).getLvl() == Integer.parseInt(val))
										{
											html.setFile(player, "data/html/clanHallManager/functions-used.htm");
											html.replace("%val%", val + "%");
											sendHtmlMessage(player, html);
											return;
										}
									}
									final int percent = Integer.parseInt(val);
									switch (percent)
									{
										case 0:
										{
											fee = 0;
											html.setFile(player, "data/html/clanHallManager/functions-cancel_confirmed.htm");
											break;
										}
										case 20:
										{
											fee = Config.CH_HPREG1_FEE;
											break;
										}
										case 40:
										{
											fee = Config.CH_HPREG2_FEE;
											break;
										}
										case 80:
										{
											fee = Config.CH_HPREG3_FEE;
											break;
										}
										case 100:
										{
											fee = Config.CH_HPREG4_FEE;
											break;
										}
										case 120:
										{
											fee = Config.CH_HPREG5_FEE;
											break;
										}
										case 140:
										{
											fee = Config.CH_HPREG6_FEE;
											break;
										}
										case 160:
										{
											fee = Config.CH_HPREG7_FEE;
											break;
										}
										case 180:
										{
											fee = Config.CH_HPREG8_FEE;
											break;
										}
										case 200:
										{
											fee = Config.CH_HPREG9_FEE;
											break;
										}
										case 220:
										{
											fee = Config.CH_HPREG10_FEE;
											break;
										}
										case 240:
										{
											fee = Config.CH_HPREG11_FEE;
											break;
										}
										case 260:
										{
											fee = Config.CH_HPREG12_FEE;
											break;
										}
										default:
										{
											fee = Config.CH_HPREG13_FEE;
											break;
										}
									}
									if (!getClanHall().updateFunctions(player, ClanHall.FUNC_RESTORE_HP, percent, fee, Config.CH_HPREG_FEE_RATIO, (getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP) == null)))
									{
										html.setFile(player, "data/html/clanHallManager/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									else
									{
										revalidateDeco(player);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
							else if (val.equalsIgnoreCase("mp"))
							{
								if (st.countTokens() >= 1)
								{
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player, "data/html/clanHallManager/functions-apply_confirmed.htm");
									if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP) != null)
									{
										if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP).getLvl() == Integer.parseInt(val))
										{
											html.setFile(player, "data/html/clanHallManager/functions-used.htm");
											html.replace("%val%", val + "%");
											sendHtmlMessage(player, html);
											return;
										}
									}
									final int percent = Integer.parseInt(val);
									switch (percent)
									{
										case 0:
										{
											fee = 0;
											html.setFile(player, "data/html/clanHallManager/functions-cancel_confirmed.htm");
											break;
										}
										case 5:
										{
											fee = Config.CH_MPREG1_FEE;
											break;
										}
										case 10:
										{
											fee = Config.CH_MPREG2_FEE;
											break;
										}
										case 15:
										{
											fee = Config.CH_MPREG3_FEE;
											break;
										}
										case 30:
										{
											fee = Config.CH_MPREG4_FEE;
											break;
										}
										default:
										{
											fee = Config.CH_MPREG5_FEE;
											break;
										}
									}
									if (!getClanHall().updateFunctions(player, ClanHall.FUNC_RESTORE_MP, percent, fee, Config.CH_MPREG_FEE_RATIO, (getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP) == null)))
									{
										html.setFile(player, "data/html/clanHallManager/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									else
									{
										revalidateDeco(player);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
							else if (val.equalsIgnoreCase("exp"))
							{
								if (st.countTokens() >= 1)
								{
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player, "data/html/clanHallManager/functions-apply_confirmed.htm");
									if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP) != null)
									{
										if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP).getLvl() == Integer.parseInt(val))
										{
											html.setFile(player, "data/html/clanHallManager/functions-used.htm");
											html.replace("%val%", val + "%");
											sendHtmlMessage(player, html);
											return;
										}
									}
									final int percent = Integer.parseInt(val);
									switch (percent)
									{
										case 0:
										{
											fee = 0;
											html.setFile(player, "data/html/clanHallManager/functions-cancel_confirmed.htm");
											break;
										}
										case 5:
										{
											fee = Config.CH_EXPREG1_FEE;
											break;
										}
										case 10:
										{
											fee = Config.CH_EXPREG2_FEE;
											break;
										}
										case 15:
										{
											fee = Config.CH_EXPREG3_FEE;
											break;
										}
										case 25:
										{
											fee = Config.CH_EXPREG4_FEE;
											break;
										}
										case 35:
										{
											fee = Config.CH_EXPREG5_FEE;
											break;
										}
										case 40:
										{
											fee = Config.CH_EXPREG6_FEE;
											break;
										}
										default:
										{
											fee = Config.CH_EXPREG7_FEE;
											break;
										}
									}
									if (!getClanHall().updateFunctions(player, ClanHall.FUNC_RESTORE_EXP, percent, fee, Config.CH_EXPREG_FEE_RATIO, (getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP) == null)))
									{
										html.setFile(player, "data/html/clanHallManager/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									else
									{
										revalidateDeco(player);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
						}
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player, "data/html/clanHallManager/edit_recovery.htm");
						final String hp_grade0 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 20\">20%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 40\">40%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 220\">220%</a>]";
						final String hp_grade1 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 40\">40%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 100\">100%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 160\">160%</a>]";
						final String hp_grade2 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 80\">80%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 140\">140%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 200\">200%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 260\">260%</a>]";
						final String hp_grade3 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 80\">80%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 120\">120%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 180\">180%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 240\">240%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 300\">300%</a>]";
						final String exp_grade0 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 5\">5%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 10\">10%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 25\">25%</a>]";
						final String exp_grade1 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 5\">5%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 15\">15%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 30\">30%</a>]";
						final String exp_grade2 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 5\">5%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 15\">15%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 25\">25%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 40\">40%</a>]";
						final String exp_grade3 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 15\">15%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 25\">25%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 35\">35%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 50\">50%</a>]";
						final String mp_grade0 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 5\">5%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 10\">10%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 25\">25%</a>]";
						final String mp_grade1 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 5\">5%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 15\">15%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 25\">25%</a>]";
						final String mp_grade2 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 5\">5%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 15\">15%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 30\">30%</a>]";
						final String mp_grade3 = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 5\">5%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 15\">15%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 30\">30%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 40\">40%</a>]";
						if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP) != null)
						{
							html.replace("%hp_recovery%", getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP).getLvl() + "%</font> (<font color=\"FFAABB\">" + getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP).getLease() + "</font>Adena /" + (Config.CH_HPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%hp_period%", "Withdraw the fee for the next time at " + format.format(getClanHall().getFunction(ClanHall.FUNC_RESTORE_HP).getEndTime()));
							final int grade = getClanHall().getGrade();
							switch (grade)
							{
								case 0:
								{
									html.replace("%change_hp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery hp_cancel\">Deactivate</a>]" + hp_grade0);
									break;
								}
								case 1:
								{
									html.replace("%change_hp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery hp_cancel\">Deactivate</a>]" + hp_grade1);
									break;
								}
								case 2:
								{
									html.replace("%change_hp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery hp_cancel\">Deactivate</a>]" + hp_grade2);
									break;
								}
								case 3:
								{
									html.replace("%change_hp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery hp_cancel\">Deactivate</a>]" + hp_grade3);
									break;
								}
							}
						}
						else
						{
							html.replace("%hp_recovery%", "none");
							html.replace("%hp_period%", "none");
							final int grade = getClanHall().getGrade();
							switch (grade)
							{
								case 0:
								{
									html.replace("%change_hp%", hp_grade0);
									break;
								}
								case 1:
								{
									html.replace("%change_hp%", hp_grade1);
									break;
								}
								case 2:
								{
									html.replace("%change_hp%", hp_grade2);
									break;
								}
								case 3:
								{
									html.replace("%change_hp%", hp_grade3);
									break;
								}
							}
						}
						if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP) != null)
						{
							html.replace("%exp_recovery%", getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP).getLvl() + "%</font> (<font color=\"FFAABB\">" + getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP).getLease() + "</font>Adena /" + (Config.CH_EXPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%exp_period%", "Withdraw the fee for the next time at " + format.format(getClanHall().getFunction(ClanHall.FUNC_RESTORE_EXP).getEndTime()));
							final int grade = getClanHall().getGrade();
							switch (grade)
							{
								case 0:
								{
									html.replace("%change_exp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery exp_cancel\">Deactivate</a>]" + exp_grade0);
									break;
								}
								case 1:
								{
									html.replace("%change_exp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery exp_cancel\">Deactivate</a>]" + exp_grade1);
									break;
								}
								case 2:
								{
									html.replace("%change_exp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery exp_cancel\">Deactivate</a>]" + exp_grade2);
									break;
								}
								case 3:
								{
									html.replace("%change_exp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery exp_cancel\">Deactivate</a>]" + exp_grade3);
									break;
								}
							}
						}
						else
						{
							html.replace("%exp_recovery%", "none");
							html.replace("%exp_period%", "none");
							final int grade = getClanHall().getGrade();
							switch (grade)
							{
								case 0:
								{
									html.replace("%change_exp%", exp_grade0);
									break;
								}
								case 1:
								{
									html.replace("%change_exp%", exp_grade1);
									break;
								}
								case 2:
								{
									html.replace("%change_exp%", exp_grade2);
									break;
								}
								case 3:
								{
									html.replace("%change_exp%", exp_grade3);
									break;
								}
							}
						}
						if (getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP) != null)
						{
							html.replace("%mp_recovery%", getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP).getLvl() + "%</font> (<font color=\"FFAABB\">" + getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP).getLease() + "</font>Adena /" + (Config.CH_MPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%mp_period%", "Withdraw the fee for the next time at " + format.format(getClanHall().getFunction(ClanHall.FUNC_RESTORE_MP).getEndTime()));
							final int grade = getClanHall().getGrade();
							switch (grade)
							{
								case 0:
								{
									html.replace("%change_mp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery mp_cancel\">Deactivate</a>]" + mp_grade0);
									break;
								}
								case 1:
								{
									html.replace("%change_mp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery mp_cancel\">Deactivate</a>]" + mp_grade1);
									break;
								}
								case 2:
								{
									html.replace("%change_mp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery mp_cancel\">Deactivate</a>]" + mp_grade2);
									break;
								}
								case 3:
								{
									html.replace("%change_mp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery mp_cancel\">Deactivate</a>]" + mp_grade3);
									break;
								}
							}
						}
						else
						{
							html.replace("%mp_recovery%", "none");
							html.replace("%mp_period%", "none");
							final int grade = getClanHall().getGrade();
							switch (grade)
							{
								case 0:
								{
									html.replace("%change_mp%", mp_grade0);
									break;
								}
								case 1:
								{
									html.replace("%change_mp%", mp_grade1);
									break;
								}
								case 2:
								{
									html.replace("%change_mp%", mp_grade2);
									break;
								}
								case 3:
								{
									html.replace("%change_mp%", mp_grade3);
									break;
								}
							}
						}
						sendHtmlMessage(player, html);
					}
					else if (val.equalsIgnoreCase("other"))
					{
						if (st.countTokens() >= 1)
						{
							if (getClanHall().getOwnerId() == 0)
							{
								player.sendMessage("This clan hall has no owner, you cannot change the configuration.");
								return;
							}
							val = st.nextToken();
							if (val.equalsIgnoreCase("item_cancel"))
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-cancel.htm");
								html.replace("%apply%", "other item 0");
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("tele_cancel"))
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-cancel.htm");
								html.replace("%apply%", "other tele 0");
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("support_cancel"))
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-cancel.htm");
								html.replace("%apply%", "other support 0");
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("edit_item"))
							{
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-apply.htm");
								html.replace("%name%", "Magic Equipment (Item Production Facilities)");
								final int stage = Integer.parseInt(val);
								int cost;
								switch (stage)
								{
									case 1:
									{
										cost = Config.CH_ITEM1_FEE;
										break;
									}
									case 2:
									{
										cost = Config.CH_ITEM2_FEE;
										break;
									}
									default:
									{
										cost = Config.CH_ITEM3_FEE;
										break;
									}
								}
								html.replace("%cost%", cost + "</font>Adena /" + (Config.CH_ITEM_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Allow the purchase of special items at fixed intervals.");
								html.replace("%apply%", "other item " + stage);
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("edit_support"))
							{
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-apply.htm");
								html.replace("%name%", "Insignia (Supplementary Magic)");
								final int stage = Integer.parseInt(val);
								int cost;
								switch (stage)
								{
									case 1:
									{
										cost = Config.CH_SUPPORT1_FEE;
										break;
									}
									case 2:
									{
										cost = Config.CH_SUPPORT2_FEE;
										break;
									}
									case 3:
									{
										cost = Config.CH_SUPPORT3_FEE;
										break;
									}
									case 4:
									{
										cost = Config.CH_SUPPORT4_FEE;
										break;
									}
									case 5:
									{
										cost = Config.CH_SUPPORT5_FEE;
										break;
									}
									case 6:
									{
										cost = Config.CH_SUPPORT6_FEE;
										break;
									}
									case 7:
									{
										cost = Config.CH_SUPPORT7_FEE;
										break;
									}
									default:
									{
										cost = Config.CH_SUPPORT8_FEE;
										break;
									}
								}
								html.replace("%cost%", cost + "</font>Adena /" + (Config.CH_SUPPORT_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Enables the use of supplementary magic.");
								html.replace("%apply%", "other support " + stage);
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("edit_tele"))
							{
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-apply.htm");
								html.replace("%name%", "Mirror (Teleportation Device)");
								final int stage = Integer.parseInt(val);
								int cost;
								switch (stage)
								{
									case 1:
									{
										cost = Config.CH_TELE1_FEE;
										break;
									}
									default:
									{
										cost = Config.CH_TELE2_FEE;
										break;
									}
								}
								html.replace("%cost%", cost + "</font>Adena /" + (Config.CH_TELE_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Teleports clan members in a clan hall to the target <font color=\"00FFFF\">Stage " + stage + "</font> staging area");
								html.replace("%apply%", "other tele " + stage);
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("item"))
							{
								if (st.countTokens() >= 1)
								{
									if (getClanHall().getOwnerId() == 0)
									{
										player.sendMessage("This clan hall has no owner, you cannot change the configuration.");
										return;
									}
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player, "data/html/clanHallManager/functions-apply_confirmed.htm");
									if (getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE) != null)
									{
										if (getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE).getLvl() == Integer.parseInt(val))
										{
											html.setFile(player, "data/html/clanHallManager/functions-used.htm");
											html.replace("%val%", "Stage " + val);
											sendHtmlMessage(player, html);
											return;
										}
									}
									int fee;
									final int lvl = Integer.parseInt(val);
									switch (lvl)
									{
										case 0:
										{
											fee = 0;
											html.setFile(player, "data/html/clanHallManager/functions-cancel_confirmed.htm");
											break;
										}
										case 1:
										{
											fee = Config.CH_ITEM1_FEE;
											break;
										}
										case 2:
										{
											fee = Config.CH_ITEM2_FEE;
											break;
										}
										default:
										{
											fee = Config.CH_ITEM3_FEE;
											break;
										}
									}
									if (!getClanHall().updateFunctions(player, ClanHall.FUNC_ITEM_CREATE, lvl, fee, Config.CH_ITEM_FEE_RATIO, (getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE) == null)))
									{
										html.setFile(player, "data/html/clanHallManager/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									else
									{
										revalidateDeco(player);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
							else if (val.equalsIgnoreCase("tele"))
							{
								if (st.countTokens() >= 1)
								{
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player, "data/html/clanHallManager/functions-apply_confirmed.htm");
									if (getClanHall().getFunction(ClanHall.FUNC_TELEPORT) != null)
									{
										if (getClanHall().getFunction(ClanHall.FUNC_TELEPORT).getLvl() == Integer.parseInt(val))
										{
											html.setFile(player, "data/html/clanHallManager/functions-used.htm");
											html.replace("%val%", "Stage " + val);
											sendHtmlMessage(player, html);
											return;
										}
									}
									final int lvl = Integer.parseInt(val);
									switch (lvl)
									{
										case 0:
										{
											fee = 0;
											html.setFile(player, "data/html/clanHallManager/functions-cancel_confirmed.htm");
											break;
										}
										case 1:
										{
											fee = Config.CH_TELE1_FEE;
											break;
										}
										default:
										{
											fee = Config.CH_TELE2_FEE;
											break;
										}
									}
									if (!getClanHall().updateFunctions(player, ClanHall.FUNC_TELEPORT, lvl, fee, Config.CH_TELE_FEE_RATIO, (getClanHall().getFunction(ClanHall.FUNC_TELEPORT) == null)))
									{
										html.setFile(player, "data/html/clanHallManager/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									else
									{
										revalidateDeco(player);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
							else if (val.equalsIgnoreCase("support"))
							{
								if (st.countTokens() >= 1)
								{
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player, "data/html/clanHallManager/functions-apply_confirmed.htm");
									if (getClanHall().getFunction(ClanHall.FUNC_SUPPORT) != null)
									{
										if (getClanHall().getFunction(ClanHall.FUNC_SUPPORT).getLvl() == Integer.parseInt(val))
										{
											html.setFile(player, "data/html/clanHallManager/functions-used.htm");
											html.replace("%val%", "Stage " + val);
											sendHtmlMessage(player, html);
											return;
										}
									}
									final int lvl = Integer.parseInt(val);
									switch (lvl)
									{
										case 0:
										{
											fee = 0;
											html.setFile(player, "data/html/clanHallManager/functions-cancel_confirmed.htm");
											break;
										}
										case 1:
										{
											fee = Config.CH_SUPPORT1_FEE;
											break;
										}
										case 2:
										{
											fee = Config.CH_SUPPORT2_FEE;
											break;
										}
										case 3:
										{
											fee = Config.CH_SUPPORT3_FEE;
											break;
										}
										case 4:
										{
											fee = Config.CH_SUPPORT4_FEE;
											break;
										}
										case 5:
										{
											fee = Config.CH_SUPPORT5_FEE;
											break;
										}
										case 6:
										{
											fee = Config.CH_SUPPORT6_FEE;
											break;
										}
										case 7:
										{
											fee = Config.CH_SUPPORT7_FEE;
											break;
										}
										default:
										{
											fee = Config.CH_SUPPORT8_FEE;
											break;
										}
									}
									if (!getClanHall().updateFunctions(player, ClanHall.FUNC_SUPPORT, lvl, fee, Config.CH_SUPPORT_FEE_RATIO, (getClanHall().getFunction(ClanHall.FUNC_SUPPORT) == null)))
									{
										html.setFile(player, "data/html/clanHallManager/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									else
									{
										revalidateDeco(player);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
						}
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player, "data/html/clanHallManager/edit_other.htm");
						final String tele = "[<a action=\"bypass -h npc_%objectId%_manage other edit_tele 1\">Level 1</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_tele 2\">Level 2</a>]";
						final String support_grade0 = "[<a action=\"bypass -h npc_%objectId%_manage other edit_support 1\">Level 1</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 2\">Level 2</a>]";
						final String support_grade1 = "[<a action=\"bypass -h npc_%objectId%_manage other edit_support 1\">Level 1</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 2\">Level 2</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 4\">Level 4</a>]";
						final String support_grade2 = "[<a action=\"bypass -h npc_%objectId%_manage other edit_support 3\">Level 3</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 4\">Level 4</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 5\">Level 5</a>]";
						final String support_grade3 = "[<a action=\"bypass -h npc_%objectId%_manage other edit_support 3\">Level 3</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 5\">Level 5</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 7\">Level 7</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 8\">Level 8</a>]";
						final String item = "[<a action=\"bypass -h npc_%objectId%_manage other edit_item 1\">Level 1</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_item 2\">Level 2</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_item 3\">Level 3</a>]";
						if (getClanHall().getFunction(ClanHall.FUNC_TELEPORT) != null)
						{
							html.replace("%tele%", "Stage " + getClanHall().getFunction(ClanHall.FUNC_TELEPORT).getLvl() + "</font> (<font color=\"FFAABB\">" + getClanHall().getFunction(ClanHall.FUNC_TELEPORT).getLease() + "</font>Adena /" + (Config.CH_TELE_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%tele_period%", "Withdraw the fee for the next time at " + format.format(getClanHall().getFunction(ClanHall.FUNC_TELEPORT).getEndTime()));
							html.replace("%change_tele%", "[<a action=\"bypass -h npc_%objectId%_manage other tele_cancel\">Deactivate</a>]" + tele);
						}
						else
						{
							html.replace("%tele%", "none");
							html.replace("%tele_period%", "none");
							html.replace("%change_tele%", tele);
						}
						if (getClanHall().getFunction(ClanHall.FUNC_SUPPORT) != null)
						{
							html.replace("%support%", "Stage " + getClanHall().getFunction(ClanHall.FUNC_SUPPORT).getLvl() + "</font> (<font color=\"FFAABB\">" + getClanHall().getFunction(ClanHall.FUNC_SUPPORT).getLease() + "</font>Adena /" + (Config.CH_SUPPORT_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%support_period%", "Withdraw the fee for the next time at " + format.format(getClanHall().getFunction(ClanHall.FUNC_SUPPORT).getEndTime()));
							final int grade = getClanHall().getGrade();
							switch (grade)
							{
								case 0:
								{
									html.replace("%change_support%", "[<a action=\"bypass -h npc_%objectId%_manage other support_cancel\">Deactivate</a>]" + support_grade0);
									break;
								}
								case 1:
								{
									html.replace("%change_support%", "[<a action=\"bypass -h npc_%objectId%_manage other support_cancel\">Deactivate</a>]" + support_grade1);
									break;
								}
								case 2:
								{
									html.replace("%change_support%", "[<a action=\"bypass -h npc_%objectId%_manage other support_cancel\">Deactivate</a>]" + support_grade2);
									break;
								}
								case 3:
								{
									html.replace("%change_support%", "[<a action=\"bypass -h npc_%objectId%_manage other support_cancel\">Deactivate</a>]" + support_grade3);
									break;
								}
							}
						}
						else
						{
							html.replace("%support%", "none");
							html.replace("%support_period%", "none");
							final int grade = getClanHall().getGrade();
							switch (grade)
							{
								case 0:
								{
									html.replace("%change_support%", support_grade0);
									break;
								}
								case 1:
								{
									html.replace("%change_support%", support_grade1);
									break;
								}
								case 2:
								{
									html.replace("%change_support%", support_grade2);
									break;
								}
								case 3:
								{
									html.replace("%change_support%", support_grade3);
									break;
								}
							}
						}
						if (getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE) != null)
						{
							html.replace("%item%", "Stage " + getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE).getLvl() + "</font> (<font color=\"FFAABB\">" + getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE).getLease() + "</font>Adena /" + (Config.CH_ITEM_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%item_period%", "Withdraw the fee for the next time at " + format.format(getClanHall().getFunction(ClanHall.FUNC_ITEM_CREATE).getEndTime()));
							html.replace("%change_item%", "[<a action=\"bypass -h npc_%objectId%_manage other item_cancel\">Deactivate</a>]" + item);
						}
						else
						{
							html.replace("%item%", "none");
							html.replace("%item_period%", "none");
							html.replace("%change_item%", item);
						}
						sendHtmlMessage(player, html);
					}
					else if (val.equalsIgnoreCase("deco") && !getClanHall().isSiegableHall())
					{
						if (st.countTokens() >= 1)
						{
							if (getClanHall().getOwnerId() == 0)
							{
								player.sendMessage("This clan hall has no owner, you cannot change the configuration.");
								return;
							}
							val = st.nextToken();
							if (val.equalsIgnoreCase("curtains_cancel"))
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-cancel.htm");
								html.replace("%apply%", "deco curtains 0");
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("fixtures_cancel"))
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-cancel.htm");
								html.replace("%apply%", "deco fixtures 0");
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("edit_curtains"))
							{
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-apply.htm");
								html.replace("%name%", "Curtains (Decoration)");
								final int stage = Integer.parseInt(val);
								int cost;
								switch (stage)
								{
									case 1:
									{
										cost = Config.CH_CURTAIN1_FEE;
										break;
									}
									default:
									{
										cost = Config.CH_CURTAIN2_FEE;
										break;
									}
								}
								html.replace("%cost%", cost + "</font>Adena /" + (Config.CH_CURTAIN_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "These curtains can be used to decorate the clan hall.");
								html.replace("%apply%", "deco curtains " + stage);
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("edit_fixtures"))
							{
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/functions-apply.htm");
								html.replace("%name%", "Front Platform (Decoration)");
								final int stage = Integer.parseInt(val);
								int cost;
								switch (stage)
								{
									case 1:
									{
										cost = Config.CH_FRONT1_FEE;
										break;
									}
									default:
									{
										cost = Config.CH_FRONT2_FEE;
										break;
									}
								}
								html.replace("%cost%", cost + "</font>Adena /" + (Config.CH_FRONT_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Used to decorate the clan hall.");
								html.replace("%apply%", "deco fixtures " + stage);
								sendHtmlMessage(player, html);
								return;
							}
							else if (val.equalsIgnoreCase("curtains"))
							{
								if (st.countTokens() >= 1)
								{
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player, "data/html/clanHallManager/functions-apply_confirmed.htm");
									if (getClanHall().getFunction(ClanHall.FUNC_DECO_CURTAINS) != null)
									{
										if (getClanHall().getFunction(ClanHall.FUNC_DECO_CURTAINS).getLvl() == Integer.parseInt(val))
										{
											html.setFile(player, "data/html/clanHallManager/functions-used.htm");
											html.replace("%val%", "Stage " + val);
											sendHtmlMessage(player, html);
											return;
										}
									}
									final int lvl = Integer.parseInt(val);
									switch (lvl)
									{
										case 0:
										{
											fee = 0;
											html.setFile(player, "data/html/clanHallManager/functions-cancel_confirmed.htm");
											break;
										}
										case 1:
										{
											fee = Config.CH_CURTAIN1_FEE;
											break;
										}
										default:
										{
											fee = Config.CH_CURTAIN2_FEE;
											break;
										}
									}
									if (!getClanHall().updateFunctions(player, ClanHall.FUNC_DECO_CURTAINS, lvl, fee, Config.CH_CURTAIN_FEE_RATIO, (getClanHall().getFunction(ClanHall.FUNC_DECO_CURTAINS) == null)))
									{
										html.setFile(player, "data/html/clanHallManager/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									else
									{
										revalidateDeco(player);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
							else if (val.equalsIgnoreCase("fixtures"))
							{
								if (st.countTokens() >= 1)
								{
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player, "data/html/clanHallManager/functions-apply_confirmed.htm");
									if (getClanHall().getFunction(ClanHall.FUNC_DECO_FRONTPLATEFORM) != null)
									{
										if (getClanHall().getFunction(ClanHall.FUNC_DECO_FRONTPLATEFORM).getLvl() == Integer.parseInt(val))
										{
											html.setFile(player, "data/html/clanHallManager/functions-used.htm");
											html.replace("%val%", "Stage " + val);
											sendHtmlMessage(player, html);
											return;
										}
									}
									final int lvl = Integer.parseInt(val);
									switch (lvl)
									{
										case 0:
										{
											fee = 0;
											html.setFile(player, "data/html/clanHallManager/functions-cancel_confirmed.htm");
											break;
										}
										case 1:
										{
											fee = Config.CH_FRONT1_FEE;
											break;
										}
										default:
										{
											fee = Config.CH_FRONT2_FEE;
											break;
										}
									}
									if (!getClanHall().updateFunctions(player, ClanHall.FUNC_DECO_FRONTPLATEFORM, lvl, fee, Config.CH_FRONT_FEE_RATIO, (getClanHall().getFunction(ClanHall.FUNC_DECO_FRONTPLATEFORM) == null)))
									{
										html.setFile(player, "data/html/clanHallManager/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									else
									{
										revalidateDeco(player);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
						}
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player, "data/html/clanHallManager/deco.htm");
						final String curtains = "[<a action=\"bypass -h npc_%objectId%_manage deco edit_curtains 1\">Level 1</a>][<a action=\"bypass -h npc_%objectId%_manage deco edit_curtains 2\">Level 2</a>]";
						final String fixtures = "[<a action=\"bypass -h npc_%objectId%_manage deco edit_fixtures 1\">Level 1</a>][<a action=\"bypass -h npc_%objectId%_manage deco edit_fixtures 2\">Level 2</a>]";
						if (getClanHall().getFunction(ClanHall.FUNC_DECO_CURTAINS) != null)
						{
							html.replace("%curtain%", "Stage " + getClanHall().getFunction(ClanHall.FUNC_DECO_CURTAINS).getLvl() + "</font> (<font color=\"FFAABB\">" + getClanHall().getFunction(ClanHall.FUNC_DECO_CURTAINS).getLease() + "</font>Adena /" + (Config.CH_CURTAIN_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%curtain_period%", "Withdraw the fee for the next time at " + format.format(getClanHall().getFunction(ClanHall.FUNC_DECO_CURTAINS).getEndTime()));
							html.replace("%change_curtain%", "[<a action=\"bypass -h npc_%objectId%_manage deco curtains_cancel\">Deactivate</a>]" + curtains);
						}
						else
						{
							html.replace("%curtain%", "none");
							html.replace("%curtain_period%", "none");
							html.replace("%change_curtain%", curtains);
						}
						if (getClanHall().getFunction(ClanHall.FUNC_DECO_FRONTPLATEFORM) != null)
						{
							html.replace("%fixture%", "Stage " + getClanHall().getFunction(ClanHall.FUNC_DECO_FRONTPLATEFORM).getLvl() + "</font> (<font color=\"FFAABB\">" + getClanHall().getFunction(ClanHall.FUNC_DECO_FRONTPLATEFORM).getLease() + "</font>Adena /" + (Config.CH_FRONT_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%fixture_period%", "Withdraw the fee for the next time at " + format.format(getClanHall().getFunction(ClanHall.FUNC_DECO_FRONTPLATEFORM).getEndTime()));
							html.replace("%change_fixture%", "[<a action=\"bypass -h npc_%objectId%_manage deco fixtures_cancel\">Deactivate</a>]" + fixtures);
						}
						else
						{
							html.replace("%fixture%", "none");
							html.replace("%fixture_period%", "none");
							html.replace("%change_fixture%", fixtures);
						}
						sendHtmlMessage(player, html);
					}
					else if (val.equalsIgnoreCase("back"))
					{
						showChatWindow(player);
					}
					else
					{
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player, getClanHall().isSiegableHall() ? "data/html/clanHallManager/manage_siegable.htm" : "data/html/clanHallManager/manage.htm");
						sendHtmlMessage(player, html);
					}
				}
				else
				{
					final NpcHtmlMessage html = new NpcHtmlMessage(1);
					html.setFile(player, "data/html/clanHallManager/not_authorized.htm");
					sendHtmlMessage(player, html);
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("support"))
			{
				if (player.isCursedWeaponEquipped())
				{
					// Custom system message
					player.sendMessage("The wielder of a cursed weapon cannot receive outside heals or buffs");
					return;
				}
				setTarget(player);
				Skill skill;
				if (val.isEmpty())
				{
					return;
				}
				
				try
				{
					final int skill_id = Integer.parseInt(val);
					try
					{
						int skill_lvl = 0;
						if (st.countTokens() >= 1)
						{
							skill_lvl = Integer.parseInt(st.nextToken());
						}
						skill = SkillData.getInstance().getSkill(skill_id, skill_lvl);
						if (skill.hasEffectType(L2EffectType.SUMMON))
						{
							player.doSimultaneousCast(skill);
						}
						else
						{
							final int mpCost = skill.getMpConsume() + skill.getMpInitialConsume();
							// If Clan Hall Buff are free or current MP is greater than MP cost, the skill should be casted.
							if ((getCurrentMp() >= mpCost) || Config.CH_BUFF_FREE)
							{
								doCast(skill);
							}
							else
							{
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player, "data/html/clanHallManager/support-no_mana.htm");
								html.replace("%mp%", String.valueOf((int) getCurrentMp()));
								sendHtmlMessage(player, html);
								return;
							}
						}
						if (getClanHall().getFunction(ClanHall.FUNC_SUPPORT) == null)
						{
							return;
						}
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						if (getClanHall().getFunction(ClanHall.FUNC_SUPPORT).getLvl() == 0)
						{
							return;
						}
						html.setFile(player, "data/html/clanHallManager/support-done.htm");
						html.replace("%mp%", String.valueOf((int) getCurrentMp()));
						sendHtmlMessage(player, html);
					}
					catch (Exception e)
					{
						player.sendMessage("Invalid skill level, contact your admin!");
					}
				}
				catch (Exception e)
				{
					player.sendMessage("Invalid skill level, contact your admin!");
				}
				return;
			}
			else if (actualCommand.equalsIgnoreCase("list_back"))
			{
				final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
				String file = "data/html/clanHallManager/chamberlain-" + getId() + ".htm";
				if (!HtmCache.getInstance().isLoadable(file))
				{
					file = "data/html/clanHallManager/chamberlain.htm";
				}
				html.setFile(player, file);
				html.replace("%objectId%", String.valueOf(getObjectId()));
				html.replace("%npcname%", getName());
				sendHtmlMessage(player, html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("support_back"))
			{
				final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
				if (getClanHall().getFunction(ClanHall.FUNC_SUPPORT).getLvl() == 0)
				{
					return;
				}
				html.setFile(player, "data/html/clanHallManager/support" + getClanHall().getFunction(ClanHall.FUNC_SUPPORT).getLvl() + ".htm");
				html.replace("%mp%", String.valueOf((int) getStatus().getCurrentMp()));
				sendHtmlMessage(player, html);
				return;
			}
			else if (actualCommand.equalsIgnoreCase("goto"))
			{
				final int whereTo = Integer.parseInt(val);
				doTeleport(player, whereTo);
				return;
			}
		}
		super.onBypassFeedback(player, command);
	}
	
	private void sendHtmlMessage(L2PcInstance player, NpcHtmlMessage html)
	{
		html.replace("%objectId%", String.valueOf(getObjectId()));
		html.replace("%npcId%", String.valueOf(getId()));
		player.sendPacket(html);
	}
	
	@Override
	public void showChatWindow(L2PcInstance player)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
		String filename = "data/html/clanHallManager/chamberlain-no.htm";
		
		final int condition = validateCondition(player);
		if (condition == COND_OWNER)
		{
			filename = "data/html/clanHallManager/chamberlain-" + getId() + ".htm";
			if (!HtmCache.getInstance().isLoadable(filename))
			{
				filename = "data/html/clanHallManager/chamberlain.htm";// Owner message window
			}
		}
		else if (condition == COND_OWNER_FALSE)
		{
			filename = "data/html/clanHallManager/chamberlain-of.htm";
		}
		final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(player, filename);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		html.replace("%npcId%", String.valueOf(getId()));
		player.sendPacket(html);
	}
	
	protected int validateCondition(L2PcInstance player)
	{
		if (getClanHall() == null)
		{
			return COND_ALL_FALSE;
		}
		if (player.canOverrideCond(PcCondOverride.CLANHALL_CONDITIONS))
		{
			return COND_OWNER;
		}
		if (player.getClan() != null)
		{
			if (getClanHall().getOwnerId() == player.getClanId())
			{
				return COND_OWNER;
			}
			return COND_OWNER_FALSE;
		}
		return COND_ALL_FALSE;
	}
	
	/**
	 * @return the L2ClanHall this L2NpcInstance belongs to.
	 */
	public final ClanHall getClanHall()
	{
		if (_clanHallId < 0)
		{
			ClanHall temp = ClanHallManager.getInstance().getNearbyClanHall(getX(), getY(), 500);
			if (temp == null)
			{
				temp = CHSiegeManager.getInstance().getNearbyClanHall(this);
			}
			
			if (temp != null)
			{
				_clanHallId = temp.getId();
			}
			
			if (_clanHallId < 0)
			{
				return null;
			}
		}
		return ClanHallManager.getInstance().getClanHallById(_clanHallId);
	}
	
	private void doTeleport(L2PcInstance player, int val)
	{
		final L2TeleportLocation list = TeleportLocationTable.getInstance().getTemplate(val);
		if (list != null)
		{
			if (player.isCombatFlagEquipped())
			{
				player.sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_WHILE_IN_POSSESSION_OF_A_WARD);
				return;
			}
			else if (player.destroyItemByItemId("Teleport", list.getItemId(), list.getPrice(), this, true))
			{
				player.teleToLocation(list.getLocX(), list.getLocY(), list.getLocZ());
			}
		}
		else
		{
			LOGGER.warning("No teleport destination with id:" + val);
		}
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	private void revalidateDeco(L2PcInstance player)
	{
		final AuctionableHall ch = ClanHallManager.getInstance().getClanHallByOwner(player.getClan());
		if (ch == null)
		{
			return;
		}
		final AgitDecoInfo bl = new AgitDecoInfo(ch);
		player.sendPacket(bl);
	}
}

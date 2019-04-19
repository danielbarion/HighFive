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
package handlers;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.handler.ActionHandler;
import com.l2jmobius.gameserver.handler.ActionShiftHandler;
import com.l2jmobius.gameserver.handler.AdminCommandHandler;
import com.l2jmobius.gameserver.handler.BypassHandler;
import com.l2jmobius.gameserver.handler.ChatHandler;
import com.l2jmobius.gameserver.handler.CommunityBoardHandler;
import com.l2jmobius.gameserver.handler.IHandler;
import com.l2jmobius.gameserver.handler.ItemHandler;
import com.l2jmobius.gameserver.handler.PunishmentHandler;
import com.l2jmobius.gameserver.handler.TargetHandler;
import com.l2jmobius.gameserver.handler.UserCommandHandler;
import com.l2jmobius.gameserver.handler.VoicedCommandHandler;
import com.l2jmobius.gameserver.network.telnet.TelnetServer;

import handlers.actionhandlers.L2ArtefactInstanceAction;
import handlers.actionhandlers.L2DecoyAction;
import handlers.actionhandlers.L2DoorInstanceAction;
import handlers.actionhandlers.L2ItemInstanceAction;
import handlers.actionhandlers.L2NpcAction;
import handlers.actionhandlers.L2PcInstanceAction;
import handlers.actionhandlers.L2PetInstanceAction;
import handlers.actionhandlers.L2StaticObjectInstanceAction;
import handlers.actionhandlers.L2SummonAction;
import handlers.actionhandlers.L2TrapAction;
import handlers.actionshifthandlers.L2DoorInstanceActionShift;
import handlers.actionshifthandlers.L2ItemInstanceActionShift;
import handlers.actionshifthandlers.L2NpcActionShift;
import handlers.actionshifthandlers.L2PcInstanceActionShift;
import handlers.actionshifthandlers.L2StaticObjectInstanceActionShift;
import handlers.actionshifthandlers.L2SummonActionShift;
import handlers.admincommandhandlers.AdminAdmin;
import handlers.admincommandhandlers.AdminAnnouncements;
import handlers.admincommandhandlers.AdminBBS;
import handlers.admincommandhandlers.AdminBuffs;
import handlers.admincommandhandlers.AdminCHSiege;
import handlers.admincommandhandlers.AdminCamera;
import handlers.admincommandhandlers.AdminChangeAccessLevel;
import handlers.admincommandhandlers.AdminClan;
import handlers.admincommandhandlers.AdminCreateItem;
import handlers.admincommandhandlers.AdminCursedWeapons;
import handlers.admincommandhandlers.AdminDelete;
import handlers.admincommandhandlers.AdminDestroyItems;
import handlers.admincommandhandlers.AdminDisconnect;
import handlers.admincommandhandlers.AdminDoorControl;
import handlers.admincommandhandlers.AdminEditChar;
import handlers.admincommandhandlers.AdminEffects;
import handlers.admincommandhandlers.AdminElement;
import handlers.admincommandhandlers.AdminEnchant;
import handlers.admincommandhandlers.AdminEventEngine;
import handlers.admincommandhandlers.AdminEvents;
import handlers.admincommandhandlers.AdminExpSp;
import handlers.admincommandhandlers.AdminFakePlayers;
import handlers.admincommandhandlers.AdminFence;
import handlers.admincommandhandlers.AdminFightCalculator;
import handlers.admincommandhandlers.AdminFortSiege;
import handlers.admincommandhandlers.AdminGamePoints;
import handlers.admincommandhandlers.AdminGeodata;
import handlers.admincommandhandlers.AdminGm;
import handlers.admincommandhandlers.AdminGmChat;
import handlers.admincommandhandlers.AdminGmSpeed;
import handlers.admincommandhandlers.AdminGraciaSeeds;
import handlers.admincommandhandlers.AdminGrandBoss;
import handlers.admincommandhandlers.AdminHeal;
import handlers.admincommandhandlers.AdminHide;
import handlers.admincommandhandlers.AdminHtml;
import handlers.admincommandhandlers.AdminInstance;
import handlers.admincommandhandlers.AdminInstanceZone;
import handlers.admincommandhandlers.AdminInvul;
import handlers.admincommandhandlers.AdminKick;
import handlers.admincommandhandlers.AdminKill;
import handlers.admincommandhandlers.AdminLevel;
import handlers.admincommandhandlers.AdminLogin;
import handlers.admincommandhandlers.AdminMammon;
import handlers.admincommandhandlers.AdminManor;
import handlers.admincommandhandlers.AdminMenu;
import handlers.admincommandhandlers.AdminMessages;
import handlers.admincommandhandlers.AdminMobGroup;
import handlers.admincommandhandlers.AdminMonsterRace;
import handlers.admincommandhandlers.AdminPForge;
import handlers.admincommandhandlers.AdminPathNode;
import handlers.admincommandhandlers.AdminPcCondOverride;
import handlers.admincommandhandlers.AdminPetition;
import handlers.admincommandhandlers.AdminPledge;
import handlers.admincommandhandlers.AdminPremium;
import handlers.admincommandhandlers.AdminPunishment;
import handlers.admincommandhandlers.AdminQuest;
import handlers.admincommandhandlers.AdminReload;
import handlers.admincommandhandlers.AdminRepairChar;
import handlers.admincommandhandlers.AdminRes;
import handlers.admincommandhandlers.AdminRide;
import handlers.admincommandhandlers.AdminScan;
import handlers.admincommandhandlers.AdminServerInfo;
import handlers.admincommandhandlers.AdminShop;
import handlers.admincommandhandlers.AdminShowQuests;
import handlers.admincommandhandlers.AdminShutdown;
import handlers.admincommandhandlers.AdminSiege;
import handlers.admincommandhandlers.AdminSkill;
import handlers.admincommandhandlers.AdminSpawn;
import handlers.admincommandhandlers.AdminSummon;
import handlers.admincommandhandlers.AdminSuperHaste;
import handlers.admincommandhandlers.AdminTarget;
import handlers.admincommandhandlers.AdminTargetSay;
import handlers.admincommandhandlers.AdminTeleport;
import handlers.admincommandhandlers.AdminTerritoryWar;
import handlers.admincommandhandlers.AdminTest;
import handlers.admincommandhandlers.AdminTransform;
import handlers.admincommandhandlers.AdminTvTEvent;
import handlers.admincommandhandlers.AdminUnblockIp;
import handlers.admincommandhandlers.AdminVitality;
import handlers.admincommandhandlers.AdminZone;
import handlers.admincommandhandlers.AdminZones;
import handlers.bypasshandlers.Augment;
import handlers.bypasshandlers.Buy;
import handlers.bypasshandlers.BuyShadowItem;
import handlers.bypasshandlers.ChatLink;
import handlers.bypasshandlers.ClanWarehouse;
import handlers.bypasshandlers.EventEngine;
import handlers.bypasshandlers.Festival;
import handlers.bypasshandlers.FindPvP;
import handlers.bypasshandlers.Freight;
import handlers.bypasshandlers.ItemAuctionLink;
import handlers.bypasshandlers.Link;
import handlers.bypasshandlers.Loto;
import handlers.bypasshandlers.Multisell;
import handlers.bypasshandlers.NpcViewMod;
import handlers.bypasshandlers.Observation;
import handlers.bypasshandlers.OlympiadManagerLink;
import handlers.bypasshandlers.OlympiadObservation;
import handlers.bypasshandlers.PlayerHelp;
import handlers.bypasshandlers.PrivateWarehouse;
import handlers.bypasshandlers.QuestLink;
import handlers.bypasshandlers.QuestList;
import handlers.bypasshandlers.ReceivePremium;
import handlers.bypasshandlers.ReleaseAttribute;
import handlers.bypasshandlers.RentPet;
import handlers.bypasshandlers.Rift;
import handlers.bypasshandlers.SkillList;
import handlers.bypasshandlers.SupportBlessing;
import handlers.bypasshandlers.SupportMagic;
import handlers.bypasshandlers.TerritoryStatus;
import handlers.bypasshandlers.TutorialClose;
import handlers.bypasshandlers.VoiceCommand;
import handlers.bypasshandlers.Wear;
import handlers.chathandlers.ChatAlliance;
import handlers.chathandlers.ChatBattlefield;
import handlers.chathandlers.ChatClan;
import handlers.chathandlers.ChatGeneral;
import handlers.chathandlers.ChatHeroVoice;
import handlers.chathandlers.ChatParty;
import handlers.chathandlers.ChatPartyMatchRoom;
import handlers.chathandlers.ChatPartyRoomAll;
import handlers.chathandlers.ChatPartyRoomCommander;
import handlers.chathandlers.ChatPetition;
import handlers.chathandlers.ChatShout;
import handlers.chathandlers.ChatTrade;
import handlers.chathandlers.ChatWhisper;
import handlers.communityboard.ClanBoard;
import handlers.communityboard.DropSearchBoard;
import handlers.communityboard.FavoriteBoard;
import handlers.communityboard.FriendsBoard;
import handlers.communityboard.HomeBoard;
import handlers.communityboard.HomepageBoard;
import handlers.communityboard.MailBoard;
import handlers.communityboard.MemoBoard;
import handlers.communityboard.RegionBoard;
import handlers.itemhandlers.BeastSoulShot;
import handlers.itemhandlers.BeastSpiritShot;
import handlers.itemhandlers.BlessedSpiritShot;
import handlers.itemhandlers.Book;
import handlers.itemhandlers.Bypass;
import handlers.itemhandlers.Calculator;
import handlers.itemhandlers.CharmOfCourage;
import handlers.itemhandlers.Disguise;
import handlers.itemhandlers.Elixir;
import handlers.itemhandlers.EnchantAttribute;
import handlers.itemhandlers.EnchantScrolls;
import handlers.itemhandlers.EventItem;
import handlers.itemhandlers.ExtractableItems;
import handlers.itemhandlers.FishShots;
import handlers.itemhandlers.Harvester;
import handlers.itemhandlers.ItemSkills;
import handlers.itemhandlers.ItemSkillsTemplate;
import handlers.itemhandlers.Maps;
import handlers.itemhandlers.MercTicket;
import handlers.itemhandlers.NicknameColor;
import handlers.itemhandlers.PetFood;
import handlers.itemhandlers.Recipes;
import handlers.itemhandlers.RollingDice;
import handlers.itemhandlers.Seed;
import handlers.itemhandlers.SevenSignsRecord;
import handlers.itemhandlers.SoulShots;
import handlers.itemhandlers.SpecialXMas;
import handlers.itemhandlers.SpiritShot;
import handlers.itemhandlers.SummonItems;
import handlers.itemhandlers.TeleportBookmark;
import handlers.punishmenthandlers.BanHandler;
import handlers.punishmenthandlers.ChatBanHandler;
import handlers.punishmenthandlers.JailHandler;
import handlers.targethandlers.Area;
import handlers.targethandlers.AreaCorpseMob;
import handlers.targethandlers.AreaFriendly;
import handlers.targethandlers.AreaSummon;
import handlers.targethandlers.Aura;
import handlers.targethandlers.AuraCorpseMob;
import handlers.targethandlers.AuraFriendly;
import handlers.targethandlers.BehindArea;
import handlers.targethandlers.BehindAura;
import handlers.targethandlers.Clan;
import handlers.targethandlers.ClanMember;
import handlers.targethandlers.CommandChannel;
import handlers.targethandlers.CorpseClan;
import handlers.targethandlers.CorpseMob;
import handlers.targethandlers.EnemySummon;
import handlers.targethandlers.FlagPole;
import handlers.targethandlers.FrontArea;
import handlers.targethandlers.FrontAura;
import handlers.targethandlers.Ground;
import handlers.targethandlers.Holy;
import handlers.targethandlers.One;
import handlers.targethandlers.OwnerPet;
import handlers.targethandlers.Party;
import handlers.targethandlers.PartyClan;
import handlers.targethandlers.PartyMember;
import handlers.targethandlers.PartyNotMe;
import handlers.targethandlers.PartyOther;
import handlers.targethandlers.PcBody;
import handlers.targethandlers.Pet;
import handlers.targethandlers.Self;
import handlers.targethandlers.Servitor;
import handlers.targethandlers.Summon;
import handlers.targethandlers.TargetParty;
import handlers.targethandlers.Unlockable;
import handlers.telnethandlers.chat.Announce;
import handlers.telnethandlers.chat.GMChat;
import handlers.telnethandlers.chat.Msg;
import handlers.telnethandlers.player.AccessLevel;
import handlers.telnethandlers.player.Ban;
import handlers.telnethandlers.player.BanChat;
import handlers.telnethandlers.player.Enchant;
import handlers.telnethandlers.player.GMList;
import handlers.telnethandlers.player.Give;
import handlers.telnethandlers.player.Jail;
import handlers.telnethandlers.player.Kick;
import handlers.telnethandlers.player.SendMail;
import handlers.telnethandlers.player.Unban;
import handlers.telnethandlers.player.UnbanChat;
import handlers.telnethandlers.player.Unjail;
import handlers.telnethandlers.server.ForceGC;
import handlers.telnethandlers.server.Memusage;
import handlers.telnethandlers.server.Performance;
import handlers.telnethandlers.server.Purge;
import handlers.telnethandlers.server.Reload;
import handlers.telnethandlers.server.ServerAbort;
import handlers.telnethandlers.server.ServerRestart;
import handlers.telnethandlers.server.ServerShutdown;
import handlers.telnethandlers.server.Status;
import handlers.usercommandhandlers.ChannelDelete;
import handlers.usercommandhandlers.ChannelInfo;
import handlers.usercommandhandlers.ChannelLeave;
import handlers.usercommandhandlers.ClanPenalty;
import handlers.usercommandhandlers.ClanWarsList;
import handlers.usercommandhandlers.Dismount;
import handlers.usercommandhandlers.ExperienceGain;
import handlers.usercommandhandlers.InstanceZone;
import handlers.usercommandhandlers.Loc;
import handlers.usercommandhandlers.Mount;
import handlers.usercommandhandlers.MyBirthday;
import handlers.usercommandhandlers.OlympiadStat;
import handlers.usercommandhandlers.PartyInfo;
import handlers.usercommandhandlers.SiegeStatus;
import handlers.usercommandhandlers.Time;
import handlers.usercommandhandlers.Unstuck;
import handlers.voicedcommandhandlers.AutoPotion;
import handlers.voicedcommandhandlers.Banking;
import handlers.voicedcommandhandlers.ChangePassword;
import handlers.voicedcommandhandlers.ChatAdmin;
import handlers.voicedcommandhandlers.Lang;
import handlers.voicedcommandhandlers.Premium;
import handlers.voicedcommandhandlers.StatsVCmd;
import handlers.voicedcommandhandlers.Wedding;

/**
 * Master handler.
 * @author UnAfraid
 */
public class MasterHandler
{
	private static final Logger LOGGER = Logger.getLogger(MasterHandler.class.getName());
	
	private static final IHandler<?, ?>[] LOAD_INSTANCES =
	{
		ActionHandler.getInstance(),
		ActionShiftHandler.getInstance(),
		AdminCommandHandler.getInstance(),
		BypassHandler.getInstance(),
		ChatHandler.getInstance(),
		CommunityBoardHandler.getInstance(),
		ItemHandler.getInstance(),
		PunishmentHandler.getInstance(),
		UserCommandHandler.getInstance(),
		VoicedCommandHandler.getInstance(),
		TargetHandler.getInstance(),
	};
	
	private static final Class<?>[][] HANDLERS =
	{
		{
			// Action Handlers
			L2ArtefactInstanceAction.class,
			L2DecoyAction.class,
			L2DoorInstanceAction.class,
			L2ItemInstanceAction.class,
			L2NpcAction.class,
			L2PcInstanceAction.class,
			L2PetInstanceAction.class,
			L2StaticObjectInstanceAction.class,
			L2SummonAction.class,
			L2TrapAction.class,
		},
		{
			// Action Shift Handlers
			L2DoorInstanceActionShift.class,
			L2ItemInstanceActionShift.class,
			L2NpcActionShift.class,
			L2PcInstanceActionShift.class,
			L2StaticObjectInstanceActionShift.class,
			L2SummonActionShift.class,
		},
		{
			// Admin Command Handlers
			AdminAdmin.class,
			AdminAnnouncements.class,
			AdminBBS.class,
			AdminBuffs.class,
			AdminCamera.class,
			AdminChangeAccessLevel.class,
			AdminCHSiege.class,
			AdminClan.class,
			AdminPcCondOverride.class,
			AdminCreateItem.class,
			AdminCursedWeapons.class,
			AdminDelete.class,
			AdminDestroyItems.class,
			AdminDisconnect.class,
			AdminDoorControl.class,
			AdminEditChar.class,
			AdminEffects.class,
			AdminElement.class,
			AdminEnchant.class,
			AdminEventEngine.class,
			AdminEvents.class,
			AdminExpSp.class,
			AdminFakePlayers.class,
			AdminFence.class,
			AdminFightCalculator.class,
			AdminFortSiege.class,
			AdminGamePoints.class,
			AdminGeodata.class,
			AdminGm.class,
			AdminGmChat.class,
			AdminGmSpeed.class,
			AdminGraciaSeeds.class,
			AdminGrandBoss.class,
			AdminHeal.class,
			AdminHide.class,
			AdminHtml.class,
			AdminInstance.class,
			AdminInstanceZone.class,
			AdminInvul.class,
			AdminKick.class,
			AdminKill.class,
			AdminLevel.class,
			AdminLogin.class,
			AdminMammon.class,
			AdminManor.class,
			AdminMenu.class,
			AdminMessages.class,
			AdminMobGroup.class,
			AdminMonsterRace.class,
			AdminPathNode.class,
			AdminPetition.class,
			AdminPForge.class,
			AdminPledge.class,
			AdminZones.class,
			AdminPremium.class,
			AdminPunishment.class,
			AdminQuest.class,
			AdminReload.class,
			AdminRepairChar.class,
			AdminRes.class,
			AdminRide.class,
			AdminScan.class,
			AdminServerInfo.class,
			AdminShop.class,
			AdminShowQuests.class,
			AdminShutdown.class,
			AdminSiege.class,
			AdminSkill.class,
			AdminSpawn.class,
			AdminSummon.class,
			AdminSuperHaste.class,
			AdminTarget.class,
			AdminTargetSay.class,
			AdminTeleport.class,
			AdminTerritoryWar.class,
			AdminTest.class,
			AdminTransform.class,
			AdminTvTEvent.class,
			AdminUnblockIp.class,
			AdminVitality.class,
			AdminZone.class,
		},
		{
			// Bypass Handlers
			Augment.class,
			Buy.class,
			BuyShadowItem.class,
			ChatLink.class,
			ClanWarehouse.class,
			EventEngine.class,
			Festival.class,
			FindPvP.class,
			Freight.class,
			ItemAuctionLink.class,
			Link.class,
			Loto.class,
			Multisell.class,
			NpcViewMod.class,
			Observation.class,
			OlympiadObservation.class,
			OlympiadManagerLink.class,
			QuestLink.class,
			PlayerHelp.class,
			PrivateWarehouse.class,
			QuestList.class,
			ReceivePremium.class,
			ReleaseAttribute.class,
			RentPet.class,
			Rift.class,
			SkillList.class,
			SupportBlessing.class,
			SupportMagic.class,
			TerritoryStatus.class,
			TutorialClose.class,
			VoiceCommand.class,
			Wear.class,
		},
		{
			// Chat Handlers
			ChatGeneral.class,
			ChatAlliance.class,
			ChatBattlefield.class,
			ChatClan.class,
			ChatHeroVoice.class,
			ChatParty.class,
			ChatPartyMatchRoom.class,
			ChatPartyRoomAll.class,
			ChatPartyRoomCommander.class,
			ChatPetition.class,
			ChatShout.class,
			ChatWhisper.class,
			ChatTrade.class,
		},
		{
			// Community Board
			ClanBoard.class,
			DropSearchBoard.class,
			FavoriteBoard.class,
			FriendsBoard.class,
			HomeBoard.class,
			HomepageBoard.class,
			MailBoard.class,
			MemoBoard.class,
			RegionBoard.class,
		},
		{
			// Item Handlers
			BeastSoulShot.class,
			BeastSpiritShot.class,
			BlessedSpiritShot.class,
			Book.class,
			Bypass.class,
			Calculator.class,
			CharmOfCourage.class,
			Disguise.class,
			Elixir.class,
			EnchantAttribute.class,
			EnchantScrolls.class,
			EventItem.class,
			ExtractableItems.class,
			FishShots.class,
			Harvester.class,
			ItemSkills.class,
			ItemSkillsTemplate.class,
			Maps.class,
			MercTicket.class,
			NicknameColor.class,
			PetFood.class,
			Recipes.class,
			RollingDice.class,
			Seed.class,
			SevenSignsRecord.class,
			SoulShots.class,
			SpecialXMas.class,
			SpiritShot.class,
			SummonItems.class,
			TeleportBookmark.class,
		},
		{
			// Punishment Handlers
			BanHandler.class,
			ChatBanHandler.class,
			JailHandler.class,
		},
		{
			// User Command Handlers
			ClanPenalty.class,
			ClanWarsList.class,
			Dismount.class,
			Unstuck.class,
			ExperienceGain.class,
			InstanceZone.class,
			Loc.class,
			Mount.class,
			PartyInfo.class,
			Time.class,
			OlympiadStat.class,
			ChannelLeave.class,
			ChannelDelete.class,
			ChannelInfo.class,
			MyBirthday.class,
			SiegeStatus.class,
		},
		{
			// Voiced Command Handlers
			StatsVCmd.class,
			// TODO: Add configuration options for this voiced commands:
			// CastleVCmd.class,
			// SetVCmd.class,
			Config.ALLOW_WEDDING ? Wedding.class : null,
			Config.BANKING_SYSTEM_ENABLED ? Banking.class : null,
			Config.CHAT_ADMIN ? ChatAdmin.class : null,
			Config.MULTILANG_ENABLE && Config.MULTILANG_VOICED_ALLOW ? Lang.class : null,
			Config.ALLOW_CHANGE_PASSWORD ? ChangePassword.class : null,
			Config.PREMIUM_SYSTEM_ENABLED ? Premium.class : null,
			Config.AUTO_POTIONS_ENABLED ? AutoPotion.class : null,
		},
		{
			// Target Handlers
			Area.class,
			AreaCorpseMob.class,
			AreaFriendly.class,
			AreaSummon.class,
			Aura.class,
			AuraCorpseMob.class,
			AuraFriendly.class,
			BehindArea.class,
			BehindAura.class,
			Clan.class,
			ClanMember.class,
			CommandChannel.class,
			CorpseClan.class,
			CorpseMob.class,
			EnemySummon.class,
			FlagPole.class,
			FrontArea.class,
			FrontAura.class,
			Ground.class,
			Holy.class,
			One.class,
			OwnerPet.class,
			Party.class,
			PartyClan.class,
			PartyMember.class,
			PartyNotMe.class,
			PartyOther.class,
			PcBody.class,
			Pet.class,
			Self.class,
			Servitor.class,
			Summon.class,
			TargetParty.class,
			Unlockable.class,
		},
	};
	
	public static void main(String[] args)
	{
		LOGGER.log(Level.INFO, "Loading Handlers...");
		
		final Map<IHandler<?, ?>, Method> registerHandlerMethods = new HashMap<>();
		for (IHandler<?, ?> loadInstance : LOAD_INSTANCES)
		{
			registerHandlerMethods.put(loadInstance, null);
			for (Method method : loadInstance.getClass().getMethods())
			{
				if (method.getName().equals("registerHandler") && !method.isBridge())
				{
					registerHandlerMethods.put(loadInstance, method);
				}
			}
		}
		
		registerHandlerMethods.entrySet().stream().filter(e -> e.getValue() == null).forEach(e ->
		{
			LOGGER.log(Level.WARNING, "Failed loading handlers of: " + e.getKey().getClass().getSimpleName() + " seems registerHandler function does not exist.");
		});
		
		for (Class<?> classes[] : HANDLERS)
		{
			for (Class<?> c : classes)
			{
				if (c == null)
				{
					continue; // Disabled handler
				}
				
				try
				{
					final Object handler = c.getDeclaredConstructor().newInstance();
					for (Entry<IHandler<?, ?>, Method> entry : registerHandlerMethods.entrySet())
					{
						if ((entry.getValue() != null) && entry.getValue().getParameterTypes()[0].isInstance(handler))
						{
							entry.getValue().invoke(entry.getKey(), handler);
						}
					}
				}
				catch (Exception e)
				{
					LOGGER.log(Level.WARNING, "Failed loading handler: " + c.getSimpleName(), e);
					continue;
				}
			}
		}
		
		if (Config.TELNET_ENABLED)
		{
			TelnetServer.getInstance().addHandler(new Announce());
			TelnetServer.getInstance().addHandler(new GMChat());
			TelnetServer.getInstance().addHandler(new Msg());
			TelnetServer.getInstance().addHandler(new AccessLevel());
			TelnetServer.getInstance().addHandler(new Ban());
			TelnetServer.getInstance().addHandler(new BanChat());
			TelnetServer.getInstance().addHandler(new Enchant());
			TelnetServer.getInstance().addHandler(new Give());
			TelnetServer.getInstance().addHandler(new GMList());
			TelnetServer.getInstance().addHandler(new Jail());
			TelnetServer.getInstance().addHandler(new Kick());
			TelnetServer.getInstance().addHandler(new Unban());
			TelnetServer.getInstance().addHandler(new UnbanChat());
			TelnetServer.getInstance().addHandler(new Unjail());
			TelnetServer.getInstance().addHandler(new ForceGC());
			TelnetServer.getInstance().addHandler(new Memusage());
			TelnetServer.getInstance().addHandler(new Performance());
			TelnetServer.getInstance().addHandler(new Purge());
			TelnetServer.getInstance().addHandler(new Reload());
			TelnetServer.getInstance().addHandler(new SendMail());
			TelnetServer.getInstance().addHandler(new ServerAbort());
			TelnetServer.getInstance().addHandler(new ServerRestart());
			TelnetServer.getInstance().addHandler(new ServerShutdown());
			TelnetServer.getInstance().addHandler(new Status());
			TelnetServer.getInstance().addHandler(new handlers.telnethandlers.server.Debug());
		}
		
		for (IHandler<?, ?> loadInstance : LOAD_INSTANCES)
		{
			LOGGER.log(Level.INFO, loadInstance.getClass().getSimpleName() + ": Loaded " + loadInstance.size() + " Handlers");
		}
		
		LOGGER.log(Level.INFO, "Handlers Loaded...");
	}
}
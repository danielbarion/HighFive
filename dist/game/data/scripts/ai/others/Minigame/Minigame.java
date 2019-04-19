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
package ai.others.Minigame;

import java.util.ArrayList;
import java.util.List;

import com.l2jmobius.gameserver.datatables.SpawnTable;
import com.l2jmobius.gameserver.enums.ChatType;
import com.l2jmobius.gameserver.model.L2Object;
import com.l2jmobius.gameserver.model.L2Spawn;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.events.EventType;
import com.l2jmobius.gameserver.model.events.impl.character.OnCreatureSkillUse;
import com.l2jmobius.gameserver.model.events.listeners.ConsumerEventListener;
import com.l2jmobius.gameserver.model.holders.SkillHolder;
import com.l2jmobius.gameserver.network.NpcStringId;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Monastery Minigame AI.
 * @author nonom
 */
public final class Minigame extends AbstractNpcAI
{
	private static final int SUMIEL = 32758;
	private static final int BURNER = 18913;
	private static final int TREASURE_BOX = 18911;
	
	private static final int UNLIT_TORCHLIGHT = 15540;
	private static final int TORCHLIGHT = 15485;
	
	private static final int SKILL_TORCH_LIGHT = 9059;
	private static final SkillHolder TRIGGER_MIRAGE = new SkillHolder(5144, 1);
	
	private static final Location TELEPORT1 = new Location(113187, -85388, -3424, 0);
	private static final Location TELEPORT2 = new Location(118833, -80589, -2688, 0);
	
	private static final int TIMER_INTERVAL = 3;
	private static final int MAX_ATTEMPTS = 3;
	
	private final List<MinigameRoom> _rooms = new ArrayList<>(2);
	
	private Minigame()
	{
		addStartNpc(SUMIEL);
		addFirstTalkId(SUMIEL);
		addTalkId(SUMIEL);
		addSpawnId(SUMIEL, TREASURE_BOX);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final MinigameRoom room = getRoomByManager(npc);
		switch (event)
		{
			case "restart":
			{
				final boolean miniGameStarted = room.getStarted();
				if (!miniGameStarted && !hasQuestItems(player, UNLIT_TORCHLIGHT))
				{
					return "32758-05.html";
				}
				if ((npc.getTarget() != null) && (npc.getTarget() != player))
				{
					return "32758-04.html";
				}
				
				takeItems(player, UNLIT_TORCHLIGHT, 1);
				giveItems(player, TORCHLIGHT, 1);
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THE_FURNACE_WILL_GO_OUT_WATCH_AND_SEE);
				
				room.getManager().setTarget(player);
				room.setParticipant(player);
				room.setStarted(true);
				for (int i = 0; i < 9; i++)
				{
					room.getOrder()[i] = getRandom(8);
				}
				cancelQuestTimer("hurry_up", npc, null);
				cancelQuestTimer("hurry_up2", npc, null);
				cancelQuestTimer("expire", npc, null);
				
				startQuestTimer("hurry_up", 120000, npc, null);
				startQuestTimer("expire", 190000, npc, null);
				startQuestTimer("start", 1000, npc, null);
				return null;
			}
			case "off":
			{
				if (npc.getId() == BURNER)
				{
					npc.setDisplayEffect(2);
					npc.setWalking();
				}
				else
				{
					for (L2Npc burner : room.getBurners())
					{
						burner.setDisplayEffect(2);
						burner.setWalking();
					}
				}
				break;
			}
			case "teleport1":
			{
				player.teleToLocation(TELEPORT1, 0);
				break;
			}
			case "teleport2":
			{
				player.teleToLocation(TELEPORT2, 0);
				break;
			}
			case "start":
			{
				room.burnThemAll();
				startQuestTimer("off", 2000, npc, null); // It should be null to stop burnthemAll 2s after
				startQuestTimer("timer", 4000, npc, null);
				break;
			}
			case "timer":
			{
				if (room.getCurrentPot() < 9)
				{
					final L2Npc b = room.getBurners()[room.getOrder()[room.getCurrentPot()]];
					b.setDisplayEffect(1);
					b.setWalking();
					startQuestTimer("off", 2000, b, null); // Stopping burning each pot 2s after
					startQuestTimer("timer", TIMER_INTERVAL * 1000, npc, null);
					room.setCurrentPot(room.getCurrentPot() + 1);
				}
				else
				{
					room.getManager().broadcastSay(ChatType.NPC_GENERAL, NpcStringId.NOW_LIGHT_THE_FURNACE_S_FIRE);
					room.burnThemAll();
					startQuestTimer("off", 2000, npc, null);
					final ConsumerEventListener listener = new ConsumerEventListener(room.getParticipant(), EventType.ON_CREATURE_SKILL_USE, (OnCreatureSkillUse listenerEvent) -> onSkillUse(listenerEvent), room);
					room.setListener(listener);
					room.setCurrentPot(0);
				}
				break;
			}
			case "hurry_up":
			{
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THERE_S_ABOUT_1_MINUTE_LEFT);
				startQuestTimer("hurry_up2", 60000, npc, null);
				break;
			}
			case "hurry_up2":
			{
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THERE_S_JUST_10_SECONDS_LEFT);
				startQuestTimer("expire", 10000, npc, null);
				break;
			}
			case "expire":
			{
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.TIME_IS_UP_AND_YOU_HAVE_FAILED_ANY_MORE_WILL_BE_DIFFICULT);
			}
			case "end":
			{
				cancelQuestTimer("expire", npc, null);
				cancelQuestTimer("hurry_up", npc, null);
				cancelQuestTimer("hurry_up2", npc, null);
				room.clean();
				break;
			}
			case "afterthat":
			{
				npc.deleteMe();
				break;
			}
		}
		return event;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance talker)
	{
		String htmltext = null;
		final MinigameRoom room = getRoomByManager(npc);
		final boolean miniGameStarted = room.getStarted();
		
		if (npc.getTarget() == null)
		{
			htmltext = miniGameStarted ? "32758-08.html" : "32758.html";
		}
		else if (npc.getTarget() == talker)
		{
			if (miniGameStarted)
			{
				htmltext = "32758-07.html";
			}
			else
			{
				final int attemptNumber = room.getAttemptNumber();
				
				if (attemptNumber == 2)
				{
					htmltext = "32758-02.html";
				}
				else if (attemptNumber == 3)
				{
					htmltext = "32758-03.html";
				}
			}
		}
		else
		{
			htmltext = "32758-04.html";
		}
		
		return htmltext;
	}
	
	@Override
	public String onSpawn(L2Npc npc)
	{
		switch (npc.getId())
		{
			case SUMIEL:
			{
				_rooms.add(initRoom(npc));
				break;
			}
			case TREASURE_BOX:
			{
				npc.disableCoreAI(true);
				startQuestTimer("afterthat", 180000, npc, null);
				break;
			}
		}
		return super.onSpawn(npc);
	}
	
	public void onSkillUse(OnCreatureSkillUse event)
	{
		final MinigameRoom room = getRoomByParticipant((L2PcInstance) event.getCaster());
		if (room.getStarted() && (event.getSkill().getId() == SKILL_TORCH_LIGHT))
		{
			for (L2Object obj : event.getTargets())
			{
				if ((obj != null) && obj.isNpc())
				{
					final L2Npc npc = (L2Npc) obj;
					if (npc.getId() == BURNER)
					{
						npc.doCast(TRIGGER_MIRAGE.getSkill());
						final int pos = room.getBurnerPos(npc);
						if (pos == room.getOrder()[room.getCurrentPot()])
						{
							if (room.getCurrentPot() < 8)
							{
								npc.setDisplayEffect(1);
								npc.setWalking();
								startQuestTimer("off", 2000, npc, null);
								room.setCurrentPot(room.getCurrentPot() + 1);
							}
							else
							{
								addSpawn(TREASURE_BOX, room.getParticipant().getLocation(), true, 0);
								room.getManager().broadcastSay(ChatType.NPC_GENERAL, NpcStringId.OH_YOU_VE_SUCCEEDED);
								room.setCurrentPot(0);
								room.burnThemAll();
								startQuestTimer("off", 2000, room.getManager(), null);
								startQuestTimer("end", 4000, room.getManager(), null);
							}
						}
						else if (room.getAttemptNumber() == MAX_ATTEMPTS)
						{
							room.getManager().broadcastSay(ChatType.NPC_GENERAL, NpcStringId.AH_I_VE_FAILED_GOING_FURTHER_WILL_BE_DIFFICULT);
							room.burnThemAll();
							startQuestTimer("off", 2000, room.getManager(), null);
							room.getParticipant().removeListenerIf(EventType.ON_CREATURE_SKILL_USE, listener -> listener.getOwner() == room);
							startQuestTimer("end", 4000, room.getManager(), null);
						}
						else if (room.getAttemptNumber() < MAX_ATTEMPTS)
						{
							room.getManager().broadcastSay(ChatType.NPC_GENERAL, NpcStringId.AH_IS_THIS_FAILURE_BUT_IT_LOOKS_LIKE_I_CAN_KEEP_GOING);
							room.burnThemAll();
							startQuestTimer("off", 2000, room.getManager(), null);
							room.setAttemptNumber(room.getAttemptNumber() + 1);
						}
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Create and initialize a MinigameRoom<br>
	 * It's loading the nearby pots around the game instructor NPC.<br>
	 * TODO: Load the pot_number value from npc ai_params.
	 * @param manager the NPC instructor
	 * @return MinigameRoom
	 */
	private MinigameRoom initRoom(L2Npc manager)
	{
		final L2Npc[] burners = new L2Npc[9];
		L2Npc lastSpawn;
		int potNumber = 0;
		
		for (L2Spawn spawn : SpawnTable.getInstance().getSpawns(BURNER))
		{
			lastSpawn = spawn.getLastSpawn();
			if ((potNumber <= 8) && Util.checkIfInRange(1000, manager, lastSpawn, false))
			{
				lastSpawn.setAutoAttackable(true);
				burners[potNumber++] = lastSpawn;
			}
		}
		return new MinigameRoom(burners, manager);
	}
	
	/**
	 * Retrieve a MinigameRoom by game instructor
	 * @param manager the NPC instructor
	 * @return MinigameRoom
	 */
	private MinigameRoom getRoomByManager(L2Npc manager)
	{
		for (MinigameRoom room : _rooms)
		{
			if (room.getManager() == manager)
			{
				return room;
			}
		}
		return null;
	}
	
	/**
	 * Retrieve a MinigameRoom by participant
	 * @param participant the L2PcInstance participating
	 * @return MinigameRoom
	 */
	private MinigameRoom getRoomByParticipant(L2PcInstance participant)
	{
		for (MinigameRoom room : _rooms)
		{
			if (room.getParticipant() == participant)
			{
				return room;
			}
		}
		return null;
	}
	
	/**
	 * An object that holds the participant, manager, burning order<br>
	 * and game status for each secret room into Monastery of Silence.
	 */
	private class MinigameRoom
	{
		private final L2Npc[] _burners;
		private final L2Npc _manager;
		private L2PcInstance _participant;
		private boolean _started;
		private int _attemptNumber;
		private int _currentPot;
		private final int _order[];
		private ConsumerEventListener _listener;
		
		public MinigameRoom(L2Npc[] burners, L2Npc manager)
		{
			_burners = burners;
			_manager = manager;
			_participant = null;
			_started = false;
			_attemptNumber = 1;
			_currentPot = 0;
			_order = new int[9];
		}
		
		/**
		 * Retrieve the burner position into the array
		 * @param npc the L2Npc burner
		 * @return the array index
		 */
		public int getBurnerPos(L2Npc npc)
		{
			for (int i = 0; i < 9; i++)
			{
				if (npc.equals(_burners[i]))
				{
					return i;
				}
			}
			return 0;
		}
		
		/**
		 * Burn all the pots into the room
		 */
		public void burnThemAll()
		{
			for (L2Npc burner : _burners)
			{
				burner.setDisplayEffect(1);
				burner.setWalking();
			}
		}
		
		/**
		 * Retrieve a list of burners
		 * @return An array of L2Npcs
		 */
		public L2Npc[] getBurners()
		{
			return _burners;
		}
		
		/**
		 * Retrieve the current game manager
		 * @return The L2Npc game instructor
		 */
		public L2Npc getManager()
		{
			return _manager;
		}
		
		/**
		 * Retrieve the current game participant
		 * @return The L2PcInstance who is participating
		 */
		public L2PcInstance getParticipant()
		{
			return _participant;
		}
		
		/**
		 * Set the current participant
		 * @param participant The L2PcInstance participating
		 */
		public void setParticipant(L2PcInstance participant)
		{
			_participant = participant;
		}
		
		/**
		 * Retrieves the MinigameRoom status
		 * @return {@code true} if the game is started, {@code false} otherwise
		 */
		public boolean getStarted()
		{
			return _started;
		}
		
		/**
		 * Set the MinigameRoom status
		 * @param started The game status {@code true} if the game is started, {@code false} otherwise
		 */
		public void setStarted(boolean started)
		{
			_started = started;
		}
		
		/**
		 * Retrieve the current burner position
		 * @return The array index
		 */
		public int getCurrentPot()
		{
			return _currentPot;
		}
		
		/**
		 * Set the current burner position
		 * @param pot The position
		 */
		public void setCurrentPot(int pot)
		{
			_currentPot = pot;
		}
		
		/**
		 * Retrieve the current attempt Number
		 * @return The attempt number
		 */
		public int getAttemptNumber()
		{
			return _attemptNumber;
		}
		
		/**
		 * Set the attempt number
		 * @param attempt attempt number
		 */
		public void setAttemptNumber(int attempt)
		{
			_attemptNumber = attempt;
		}
		
		/**
		 * Retrieve the burning order
		 * @return an array of Ids
		 */
		public int[] getOrder()
		{
			return _order;
		}
		
		/**
		 * Set a listener for player inside room
		 * @param listener
		 */
		public void setListener(ConsumerEventListener listener)
		{
			_listener = listener;
			_participant.addListener(listener);
		}
		
		/**
		 * Clean the room values
		 */
		public void clean()
		{
			if (_listener != null)
			{
				_participant.removeListener(_listener);
				_listener = null;
			}
			
			_manager.setTarget(null);
			setParticipant(null);
			setStarted(false);
			setAttemptNumber(1);
			setCurrentPot(0);
		}
	}
	
	public static void main(String[] args)
	{
		new Minigame();
	}
}

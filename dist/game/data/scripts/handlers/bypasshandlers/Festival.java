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

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.SevenSigns;
import com.l2jmobius.gameserver.SevenSignsFestival;
import com.l2jmobius.gameserver.handler.IBypassHandler;
import com.l2jmobius.gameserver.model.L2Party;
import com.l2jmobius.gameserver.model.L2Party.MessageType;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.model.actor.L2Character;
import com.l2jmobius.gameserver.model.actor.instance.L2FestivalGuideInstance;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.network.SystemMessageId;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jmobius.gameserver.network.serverpackets.SystemMessage;

public class Festival implements IBypassHandler
{
	private static final String[] COMMANDS =
	{
		"festival",
		"festivaldesc"
	};
	
	@Override
	public boolean useBypass(String command, L2PcInstance activeChar, L2Character target)
	{
		if (!(target instanceof L2FestivalGuideInstance))
		{
			return false;
		}
		
		final L2FestivalGuideInstance npc = (L2FestivalGuideInstance) target;
		try
		{
			final int val;
			if (command.toLowerCase().startsWith(COMMANDS[1]))
			{
				val = Integer.parseInt(command.substring(13));
				npc.showChatWindow(activeChar, val, null, true);
				return true;
			}
			
			final L2Party party;
			val = Integer.parseInt(command.substring(9, 10));
			switch (val)
			{
				case 1: // Become a Participant
				{
					// Check if the festival period is active, if not then don't allow registration.
					if (SevenSigns.getInstance().isSealValidationPeriod())
					{
						npc.showChatWindow(activeChar, 2, "a", false);
						return true;
					}
					// Check if a festival is in progress, then don't allow registration yet.
					if (SevenSignsFestival.getInstance().isFestivalInitialized())
					{
						activeChar.sendMessage("You cannot sign up while a festival is in progress.");
						return true;
					}
					// Check if the player is in a formed party already.
					if (!activeChar.isInParty())
					{
						npc.showChatWindow(activeChar, 2, "b", false);
						return true;
					}
					party = activeChar.getParty();
					// Check if the player is the party leader.
					if (!party.isLeader(activeChar))
					{
						npc.showChatWindow(activeChar, 2, "c", false);
						return true;
					}
					// Check to see if the party has at least 5 members.
					if (party.getMemberCount() < Config.ALT_FESTIVAL_MIN_PLAYER)
					{
						npc.showChatWindow(activeChar, 2, "b", false);
						return true;
					}
					// Check if all the party members are in the required level range.
					if (party.getLevel() > SevenSignsFestival.getMaxLevelForFestival(npc.getFestivalType()))
					{
						npc.showChatWindow(activeChar, 2, "d", false);
						return true;
					}
					// Check to see if the player has already signed up
					if (activeChar.isFestivalParticipant())
					{
						SevenSignsFestival.getInstance().setParticipants(npc.getFestivalOracle(), npc.getFestivalType(), party);
						npc.showChatWindow(activeChar, 2, "f", false);
						return true;
					}
					npc.showChatWindow(activeChar, 1, null, false);
					break;
				}
				case 2: // Seal Stones
				{
					final int stoneType = Integer.parseInt(command.substring(11));
					final int stoneCount = npc.getStoneCount(stoneType);
					if (stoneCount <= 0)
					{
						return false;
					}
					if (!activeChar.destroyItemByItemId("SevenSigns", stoneType, stoneCount, npc, true))
					{
						return false;
					}
					SevenSignsFestival.getInstance().setParticipants(npc.getFestivalOracle(), npc.getFestivalType(), activeChar.getParty());
					SevenSignsFestival.getInstance().addAccumulatedBonus(npc.getFestivalType(), stoneType, stoneCount);
					npc.showChatWindow(activeChar, 2, "e", false);
					break;
				}
				case 3: // Score Registration
				{
					// Check if the festival period is active, if not then don't register the score.
					if (SevenSigns.getInstance().isSealValidationPeriod())
					{
						npc.showChatWindow(activeChar, 3, "a", false);
						return true;
					}
					// Check if a festival is in progress, if it is don't register the score.
					if (SevenSignsFestival.getInstance().isFestivalInProgress())
					{
						activeChar.sendMessage("You cannot register a score while a festival is in progress.");
						return true;
					}
					// Check if the player is in a party.
					if (!activeChar.isInParty())
					{
						npc.showChatWindow(activeChar, 3, "b", false);
						return true;
					}
					final List<Integer> prevParticipants = SevenSignsFestival.getInstance().getPreviousParticipants(npc.getFestivalOracle(), npc.getFestivalType());
					// Check if there are any past participants.
					if ((prevParticipants == null) || prevParticipants.isEmpty() || !prevParticipants.contains(activeChar.getObjectId()))
					{
						npc.showChatWindow(activeChar, 3, "b", false);
						return true;
					}
					// Check if this player was the party leader in the festival.
					if (activeChar.getObjectId() != prevParticipants.get(0))
					{
						npc.showChatWindow(activeChar, 3, "b", false);
						return true;
					}
					final L2ItemInstance bloodOfferings = activeChar.getInventory().getItemByItemId(SevenSignsFestival.FESTIVAL_OFFERING_ID);
					// Check if the player collected any blood offerings during the festival.
					if (bloodOfferings == null)
					{
						activeChar.sendMessage("You do not have any blood offerings to contribute.");
						return true;
					}
					final long offeringScore = bloodOfferings.getCount() * SevenSignsFestival.FESTIVAL_OFFERING_VALUE;
					if (!activeChar.destroyItem("SevenSigns", bloodOfferings, npc, false))
					{
						return true;
					}
					final boolean isHighestScore = SevenSignsFestival.getInstance().setFinalScore(activeChar, npc.getFestivalOracle(), npc.getFestivalType(), offeringScore);
					final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_CONTRIBUTION_SCORE_HAS_INCREASED_BY_S1);
					sm.addLong(offeringScore);
					activeChar.sendPacket(sm);
					if (isHighestScore)
					{
						npc.showChatWindow(activeChar, 3, "c", false);
					}
					else
					{
						npc.showChatWindow(activeChar, 3, "d", false);
					}
					break;
				}
				case 4: // Current High Scores
				{
					final StringBuilder strBuffer = new StringBuilder(500);
					strBuffer.append("<html><body>Festival Guide:<br>These are the top scores of the week, for the ");
					final StatsSet dawnData = SevenSignsFestival.getInstance().getHighestScoreData(SevenSigns.CABAL_DAWN, npc.getFestivalType());
					final StatsSet duskData = SevenSignsFestival.getInstance().getHighestScoreData(SevenSigns.CABAL_DUSK, npc.getFestivalType());
					final StatsSet overallData = SevenSignsFestival.getInstance().getOverallHighestScoreData(npc.getFestivalType());
					final int dawnScore = dawnData.getInt("score");
					final int duskScore = duskData.getInt("score");
					int overallScore = 0;
					// If no data is returned, assume there is no record, or all scores are 0.
					if (overallData != null)
					{
						overallScore = overallData.getInt("score");
					}
					strBuffer.append(SevenSignsFestival.getFestivalName(npc.getFestivalType()) + " festival.<br>");
					if (dawnScore > 0)
					{
						strBuffer.append("Dawn: " + calculateDate(dawnData.getString("date")) + ". Score " + dawnScore + "<br>" + dawnData.getString("members") + "<br>");
					}
					else
					{
						strBuffer.append("Dawn: No record exists. Score 0<br>");
					}
					if (duskScore > 0)
					{
						strBuffer.append("Dusk: " + calculateDate(duskData.getString("date")) + ". Score " + duskScore + "<br>" + duskData.getString("members") + "<br>");
					}
					else
					{
						strBuffer.append("Dusk: No record exists. Score 0<br>");
					}
					if ((overallScore > 0) && (overallData != null))
					{
						final String cabalStr;
						if (overallData.getString("cabal").equals("dawn"))
						{
							cabalStr = "Children of Dawn";
						}
						else
						{
							cabalStr = "Children of Dusk";
						}
						strBuffer.append("Consecutive top scores: " + calculateDate(overallData.getString("date")) + ". Score " + overallScore + "<br>Affilated side: " + cabalStr + "<br>" + overallData.getString("members") + "<br>");
					}
					else
					{
						strBuffer.append("Consecutive top scores: No record exists. Score 0<br>");
					}
					strBuffer.append("<a action=\"bypass -h npc_" + npc.getObjectId() + "_Chat 0\">Go back.</a></body></html>");
					final NpcHtmlMessage html = new NpcHtmlMessage(npc.getObjectId());
					html.setHtml(strBuffer.toString());
					activeChar.sendPacket(html);
					break;
				}
				case 8: // Increase the Festival Challenge
				{
					if (!activeChar.isInParty())
					{
						return true;
					}
					if (!SevenSignsFestival.getInstance().isFestivalInProgress())
					{
						return true;
					}
					party = activeChar.getParty();
					if (!party.isLeader(activeChar))
					{
						npc.showChatWindow(activeChar, 8, "a", false);
						return true;
					}
					if (SevenSignsFestival.getInstance().increaseChallenge(npc.getFestivalOracle(), npc.getFestivalType()))
					{
						npc.showChatWindow(activeChar, 8, "b", false);
					}
					else
					{
						npc.showChatWindow(activeChar, 8, "c", false);
					}
					break;
				}
				case 9: // Leave the Festival
				{
					if (!activeChar.isInParty())
					{
						return true;
					}
					party = activeChar.getParty();
					if (party.isLeader(activeChar))
					{
						SevenSignsFestival.getInstance().updateParticipants(activeChar, null);
					}
					else
					{
						if (party.getMemberCount() > Config.ALT_FESTIVAL_MIN_PLAYER)
						{
							party.removePartyMember(activeChar, MessageType.EXPELLED);
						}
						else
						{
							activeChar.sendMessage("Only the party leader can leave a festival when a party has minimum number of members.");
						}
					}
					break;
				}
				case 0: // Distribute Accumulated Bonus
				{
					if (!SevenSigns.getInstance().isSealValidationPeriod())
					{
						activeChar.sendMessage("Bonuses cannot be paid during the competition period.");
						return true;
					}
					if (SevenSignsFestival.getInstance().distribAccumulatedBonus(activeChar) > 0)
					{
						npc.showChatWindow(activeChar, 0, "a", false);
					}
					else
					{
						npc.showChatWindow(activeChar, 0, "b", false);
					}
					break;
				}
				default:
				{
					npc.showChatWindow(activeChar, val, null, false);
				}
			}
			return true;
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception in " + getClass().getSimpleName(), e);
		}
		return false;
	}
	
	private final String calculateDate(String milliFromEpoch)
	{
		final long numMillis = Long.valueOf(milliFromEpoch);
		final Calendar calCalc = Calendar.getInstance();
		calCalc.setTimeInMillis(numMillis);
		return calCalc.get(Calendar.YEAR) + "/" + calCalc.get(Calendar.MONTH) + "/" + calCalc.get(Calendar.DAY_OF_MONTH);
	}
	
	@Override
	public String[] getBypassList()
	{
		return COMMANDS;
	}
}

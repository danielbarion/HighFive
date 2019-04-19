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
package com.l2jmobius.gameserver.network.serverpackets;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.SevenSigns;
import com.l2jmobius.gameserver.SevenSignsFestival;
import com.l2jmobius.gameserver.model.StatsSet;
import com.l2jmobius.gameserver.network.OutgoingPackets;
import com.l2jmobius.gameserver.network.SystemMessageId;

/**
 * Seven Signs Record Update
 * @author Tempy
 * @editor shansoft
 */
public class SSQStatus implements IClientOutgoingPacket
{
	private final int _objectId;
	private final int _page;
	
	public SSQStatus(int objectId, int recordPage)
	{
		_objectId = objectId;
		_page = recordPage;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		final int winningCabal = SevenSigns.getInstance().getCabalHighestScore();
		final int totalDawnMembers = SevenSigns.getInstance().getTotalMembers(SevenSigns.CABAL_DAWN);
		final int totalDuskMembers = SevenSigns.getInstance().getTotalMembers(SevenSigns.CABAL_DUSK);
		
		OutgoingPackets.SSQ_STATUS.writeId(packet);
		
		packet.writeC(_page);
		packet.writeC(SevenSigns.getInstance().getCurrentPeriod()); // current period?
		
		int dawnPercent = 0;
		int duskPercent = 0;
		
		switch (_page)
		{
			case 1:
			{
				// [ddd cc dd ddd c ddd c]
				
				packet.writeD(SevenSigns.getInstance().getCurrentCycle());
				
				final int currentPeriod = SevenSigns.getInstance().getCurrentPeriod();
				
				switch (currentPeriod)
				{
					case SevenSigns.PERIOD_COMP_RECRUITING:
					{
						packet.writeD(SystemMessageId.THIS_IS_THE_INITIAL_PERIOD.getId());
						break;
					}
					case SevenSigns.PERIOD_COMPETITION:
					{
						packet.writeD(SystemMessageId.THE_SSQ_COMPETITION_PERIOD_IS_UNDERWAY.getId());
						break;
					}
					case SevenSigns.PERIOD_COMP_RESULTS:
					{
						packet.writeD(SystemMessageId.THIS_IS_A_PERIOD_WHEN_SERVER_STATISTICS_ARE_CALCULATED.getId());
						break;
					}
					case SevenSigns.PERIOD_SEAL_VALIDATION:
					{
						packet.writeD(SystemMessageId.THIS_IS_THE_SEAL_VALIDATION_PERIOD.getId());
						break;
					}
				}
				
				switch (currentPeriod)
				{
					case SevenSigns.PERIOD_COMP_RECRUITING:
					case SevenSigns.PERIOD_COMP_RESULTS:
					{
						packet.writeD(SystemMessageId.UNTIL_TODAY_AT_6_00_P_M.getId());
						break;
					}
					case SevenSigns.PERIOD_COMPETITION:
					case SevenSigns.PERIOD_SEAL_VALIDATION:
					{
						packet.writeD(SystemMessageId.UNTIL_NEXT_MONDAY_AT_6_00_P_M.getId());
						break;
					}
				}
				
				packet.writeC(SevenSigns.getInstance().getPlayerCabal(_objectId));
				packet.writeC(SevenSigns.getInstance().getPlayerSeal(_objectId));
				
				packet.writeQ(SevenSigns.getInstance().getPlayerStoneContrib(_objectId)); // Seal Stones Turned-In
				packet.writeQ(SevenSigns.getInstance().getPlayerAdenaCollect(_objectId)); // Ancient Adena to Collect
				
				final double dawnStoneScore = SevenSigns.getInstance().getCurrentStoneScore(SevenSigns.CABAL_DAWN);
				final int dawnFestivalScore = SevenSigns.getInstance().getCurrentFestivalScore(SevenSigns.CABAL_DAWN);
				
				final double duskStoneScore = SevenSigns.getInstance().getCurrentStoneScore(SevenSigns.CABAL_DUSK);
				final int duskFestivalScore = SevenSigns.getInstance().getCurrentFestivalScore(SevenSigns.CABAL_DUSK);
				
				final double totalStoneScore = duskStoneScore + dawnStoneScore;
				
				/*
				 * Scoring seems to be proportionate to a set base value, so base this on the maximum obtainable score from festivals, which is 500.
				 */
				int duskStoneScoreProp = 0;
				int dawnStoneScoreProp = 0;
				
				if (totalStoneScore != 0)
				{
					duskStoneScoreProp = Math.round(((float) duskStoneScore / (float) totalStoneScore) * 500);
					dawnStoneScoreProp = Math.round(((float) dawnStoneScore / (float) totalStoneScore) * 500);
				}
				
				final int duskTotalScore = SevenSigns.getInstance().getCurrentScore(SevenSigns.CABAL_DUSK);
				final int dawnTotalScore = SevenSigns.getInstance().getCurrentScore(SevenSigns.CABAL_DAWN);
				
				final int totalOverallScore = duskTotalScore + dawnTotalScore;
				
				if (totalOverallScore != 0)
				{
					dawnPercent = Math.round(((float) dawnTotalScore / totalOverallScore) * 100);
					duskPercent = Math.round(((float) duskTotalScore / totalOverallScore) * 100);
				}
				
				/* DUSK */
				packet.writeQ(duskStoneScoreProp); // Seal Stone Score
				packet.writeQ(duskFestivalScore); // Festival Score
				packet.writeQ(duskTotalScore); // Total Score
				
				packet.writeC(duskPercent); // Dusk %
				
				/* DAWN */
				packet.writeQ(dawnStoneScoreProp); // Seal Stone Score
				packet.writeQ(dawnFestivalScore); // Festival Score
				packet.writeQ(dawnTotalScore); // Total Score
				
				packet.writeC(dawnPercent); // Dawn %
				break;
			}
			case 2:
			{
				// c cc hc [cd (dc (S))]
				packet.writeH(1);
				
				packet.writeC(5); // Total number of festivals
				
				for (int i = 0; i < 5; i++)
				{
					packet.writeC(i + 1); // Current client-side festival ID
					packet.writeD(SevenSignsFestival.FESTIVAL_LEVEL_SCORES[i]);
					
					final int duskScore = SevenSignsFestival.getInstance().getHighestScore(SevenSigns.CABAL_DUSK, i);
					final int dawnScore = SevenSignsFestival.getInstance().getHighestScore(SevenSigns.CABAL_DAWN, i);
					
					// Dusk Score \\
					packet.writeQ(duskScore);
					
					StatsSet highScoreData = SevenSignsFestival.getInstance().getHighestScoreData(SevenSigns.CABAL_DUSK, i);
					String[] partyMembers = highScoreData.getString("members").split(",");
					
					if (partyMembers != null)
					{
						packet.writeC(partyMembers.length);
						
						for (String partyMember : partyMembers)
						{
							packet.writeS(partyMember);
						}
					}
					else
					{
						packet.writeC(0);
					}
					
					// Dawn Score \\
					packet.writeQ(dawnScore);
					
					highScoreData = SevenSignsFestival.getInstance().getHighestScoreData(SevenSigns.CABAL_DAWN, i);
					partyMembers = highScoreData.getString("members").split(",");
					
					if (partyMembers != null)
					{
						packet.writeC(partyMembers.length);
						
						for (String partyMember : partyMembers)
						{
							packet.writeS(partyMember);
						}
					}
					else
					{
						packet.writeC(0);
					}
				}
				break;
			}
			case 3:
			{
				// c cc [ccc (cccc)]
				packet.writeC(10); // Minimum limit for winning cabal to retain their seal
				packet.writeC(35); // Minimum limit for winning cabal to claim a seal
				packet.writeC(3); // Total number of seals
				
				for (int i = 1; i < 4; i++)
				{
					final int dawnProportion = SevenSigns.getInstance().getSealProportion(i, SevenSigns.CABAL_DAWN);
					final int duskProportion = SevenSigns.getInstance().getSealProportion(i, SevenSigns.CABAL_DUSK);
					
					packet.writeC(i);
					packet.writeC(SevenSigns.getInstance().getSealOwner(i));
					
					if (totalDuskMembers == 0)
					{
						if (totalDawnMembers == 0)
						{
							packet.writeC(0);
							packet.writeC(0);
						}
						else
						{
							packet.writeC(0);
							packet.writeC(Math.round(((float) dawnProportion / totalDawnMembers) * 100));
						}
					}
					else
					{
						if (totalDawnMembers == 0)
						{
							packet.writeC(Math.round(((float) duskProportion / totalDuskMembers) * 100));
							packet.writeC(0);
						}
						else
						{
							packet.writeC(Math.round(((float) duskProportion / totalDuskMembers) * 100));
							packet.writeC(Math.round(((float) dawnProportion / totalDawnMembers) * 100));
						}
					}
				}
				break;
			}
			case 4:
			{
				// c cc [cc (ccD)] CT 2.3 update
				packet.writeC(winningCabal); // Overall predicted winner
				packet.writeC(3); // Total number of seals
				
				for (int i = 1; i < 4; i++)
				{
					final int dawnProportion = SevenSigns.getInstance().getSealProportion(i, SevenSigns.CABAL_DAWN);
					final int duskProportion = SevenSigns.getInstance().getSealProportion(i, SevenSigns.CABAL_DUSK);
					dawnPercent = Math.round((dawnProportion / (totalDawnMembers == 0 ? 1 : (float) totalDawnMembers)) * 100);
					duskPercent = Math.round((duskProportion / (totalDuskMembers == 0 ? 1 : (float) totalDuskMembers)) * 100);
					final int sealOwner = SevenSigns.getInstance().getSealOwner(i);
					
					packet.writeC(i);
					
					switch (sealOwner)
					{
						case SevenSigns.CABAL_NULL:
						{
							switch (winningCabal)
							{
								case SevenSigns.CABAL_NULL:
								{
									packet.writeC(SevenSigns.CABAL_NULL);
									packet.writeD(SystemMessageId.THE_COMPETITION_HAS_ENDED_IN_A_TIE_THEREFORE_NOBODY_HAS_BEEN_AWARDED_THE_SEAL.getId());
									break;
								}
								case SevenSigns.CABAL_DAWN:
								{
									if (dawnPercent >= 35)
									{
										packet.writeC(SevenSigns.CABAL_DAWN);
										packet.writeD(SystemMessageId.ALTHOUGH_THE_SEAL_WAS_NOT_OWNED_35_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else
									{
										packet.writeC(SevenSigns.CABAL_NULL);
										packet.writeD(SystemMessageId.SINCE_THE_SEAL_WAS_NOT_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_SINCE_LESS_THAN_35_PERCENT_OF_PEOPLE_HAVE_VOTED.getId());
									}
									break;
								}
								case SevenSigns.CABAL_DUSK:
								{
									if (duskPercent >= 35)
									{
										packet.writeC(SevenSigns.CABAL_DUSK);
										packet.writeD(SystemMessageId.ALTHOUGH_THE_SEAL_WAS_NOT_OWNED_35_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else
									{
										packet.writeC(SevenSigns.CABAL_NULL);
										packet.writeD(SystemMessageId.SINCE_THE_SEAL_WAS_NOT_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_SINCE_LESS_THAN_35_PERCENT_OF_PEOPLE_HAVE_VOTED.getId());
									}
									break;
								}
							}
							break;
						}
						case SevenSigns.CABAL_DAWN:
						{
							switch (winningCabal)
							{
								case SevenSigns.CABAL_NULL:
								{
									if (dawnPercent >= 10)
									{
										packet.writeC(SevenSigns.CABAL_DAWN);
										packet.writeD(SystemMessageId.SINCE_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_10_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else
									{
										packet.writeC(SevenSigns.CABAL_NULL);
										packet.writeD(SystemMessageId.THE_COMPETITION_HAS_ENDED_IN_A_TIE_THEREFORE_NOBODY_HAS_BEEN_AWARDED_THE_SEAL.getId());
									}
									break;
								}
								case SevenSigns.CABAL_DAWN:
								{
									if (dawnPercent >= 10)
									{
										packet.writeC(sealOwner);
										packet.writeD(SystemMessageId.SINCE_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_10_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else
									{
										packet.writeC(SevenSigns.CABAL_NULL);
										packet.writeD(SystemMessageId.ALTHOUGH_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_LESS_THAN_10_OF_PEOPLE_HAVE_VOTED.getId());
									}
									break;
								}
								case SevenSigns.CABAL_DUSK:
								{
									if (duskPercent >= 35)
									{
										packet.writeC(SevenSigns.CABAL_DUSK);
										packet.writeD(SystemMessageId.ALTHOUGH_THE_SEAL_WAS_NOT_OWNED_35_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else if (dawnPercent >= 10)
									{
										packet.writeC(SevenSigns.CABAL_DAWN);
										packet.writeD(SystemMessageId.SINCE_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_10_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else
									{
										packet.writeC(SevenSigns.CABAL_NULL);
										packet.writeD(SystemMessageId.ALTHOUGH_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_LESS_THAN_10_OF_PEOPLE_HAVE_VOTED.getId());
									}
									break;
								}
							}
							break;
						}
						case SevenSigns.CABAL_DUSK:
						{
							switch (winningCabal)
							{
								case SevenSigns.CABAL_NULL:
								{
									if (duskPercent >= 10)
									{
										packet.writeC(SevenSigns.CABAL_DUSK);
										packet.writeD(SystemMessageId.SINCE_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_10_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else
									{
										packet.writeC(SevenSigns.CABAL_NULL);
										packet.writeD(SystemMessageId.THE_COMPETITION_HAS_ENDED_IN_A_TIE_THEREFORE_NOBODY_HAS_BEEN_AWARDED_THE_SEAL.getId());
									}
									break;
								}
								case SevenSigns.CABAL_DAWN:
								{
									if (dawnPercent >= 35)
									{
										packet.writeC(SevenSigns.CABAL_DAWN);
										packet.writeD(SystemMessageId.ALTHOUGH_THE_SEAL_WAS_NOT_OWNED_35_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else if (duskPercent >= 10)
									{
										packet.writeC(sealOwner);
										packet.writeD(SystemMessageId.SINCE_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_10_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else
									{
										packet.writeC(SevenSigns.CABAL_NULL);
										packet.writeD(SystemMessageId.ALTHOUGH_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_LESS_THAN_10_OF_PEOPLE_HAVE_VOTED.getId());
									}
									break;
								}
								case SevenSigns.CABAL_DUSK:
								{
									if (duskPercent >= 10)
									{
										packet.writeC(sealOwner);
										packet.writeD(SystemMessageId.SINCE_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_10_OR_MORE_PEOPLE_HAVE_PARTICIPATED.getId());
									}
									else
									{
										packet.writeC(SevenSigns.CABAL_NULL);
										packet.writeD(SystemMessageId.ALTHOUGH_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_LESS_THAN_10_OF_PEOPLE_HAVE_VOTED.getId());
									}
									break;
								}
							}
							break;
						}
					}
				}
				break;
			}
		}
		return true;
	}
}
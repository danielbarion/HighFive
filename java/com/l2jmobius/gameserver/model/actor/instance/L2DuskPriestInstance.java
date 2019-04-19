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

import com.l2jmobius.gameserver.SevenSigns;
import com.l2jmobius.gameserver.enums.InstanceType;
import com.l2jmobius.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;

public class L2DuskPriestInstance extends L2SignsPriestInstance
{
	/**
	 * Creates a dusk priest.
	 * @param template the dusk priest NPC template
	 */
	public L2DuskPriestInstance(L2NpcTemplate template)
	{
		super(template);
		setInstanceType(InstanceType.L2DuskPriestInstance);
	}
	
	@Override
	public void onBypassFeedback(L2PcInstance player, String command)
	{
		if (command.startsWith("Chat"))
		{
			showChatWindow(player);
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}
	
	@Override
	public void showChatWindow(L2PcInstance player)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
		
		String filename = SevenSigns.SEVEN_SIGNS_HTML_PATH;
		final int sealGnosisOwner = SevenSigns.getInstance().getSealOwner(SevenSigns.SEAL_GNOSIS);
		final int playerCabal = SevenSigns.getInstance().getPlayerCabal(player.getObjectId());
		final boolean isSealValidationPeriod = SevenSigns.getInstance().isSealValidationPeriod();
		final boolean isCompResultsPeriod = SevenSigns.getInstance().isCompResultsPeriod();
		final int recruitPeriod = SevenSigns.getInstance().getCurrentPeriod();
		final int compWinner = SevenSigns.getInstance().getCabalHighestScore();
		
		switch (playerCabal)
		{
			case SevenSigns.CABAL_DUSK:
			{
				if (isCompResultsPeriod)
				{
					filename += "dusk_priest_5.htm";
				}
				else if (recruitPeriod == 0)
				{
					filename += "dusk_priest_6.htm";
				}
				else if (isSealValidationPeriod)
				{
					if (compWinner == SevenSigns.CABAL_DUSK)
					{
						if (compWinner != sealGnosisOwner)
						{
							filename += "dusk_priest_2c.htm";
						}
						else
						{
							filename += "dusk_priest_2a.htm";
						}
					}
					else if (compWinner == SevenSigns.CABAL_NULL)
					{
						filename += "dusk_priest_2d.htm";
					}
					else
					{
						filename += "dusk_priest_2b.htm";
					}
				}
				else
				{
					filename += "dusk_priest_1b.htm";
				}
				break;
			}
			case SevenSigns.CABAL_DAWN:
			{
				if (isSealValidationPeriod)
				{
					filename += "dusk_priest_3a.htm";
				}
				else
				{
					filename += "dusk_priest_3b.htm";
				}
				break;
			}
			default:
			{
				if (isCompResultsPeriod)
				{
					filename += "dusk_priest_5.htm";
				}
				else if (recruitPeriod == 0)
				{
					filename += "dusk_priest_6.htm";
				}
				else if (isSealValidationPeriod)
				{
					if (compWinner == SevenSigns.CABAL_DUSK)
					{
						filename += "dusk_priest_4.htm";
					}
					else if (compWinner == SevenSigns.CABAL_NULL)
					{
						filename += "dusk_priest_2d.htm";
					}
					else
					{
						filename += "dusk_priest_2b.htm";
					}
				}
				else
				{
					filename += "dusk_priest_1a.htm";
				}
				break;
			}
		}
		
		final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(player, filename);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		player.sendPacket(html);
	}
	
}
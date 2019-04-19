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
package com.l2jmobius.log.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import com.l2jmobius.Config;
import com.l2jmobius.commons.util.StringUtil;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.ConnectionState;
import com.l2jmobius.gameserver.network.L2GameClient;

public class AccountingFormatter extends Formatter
{
	private final SimpleDateFormat dateFmt = new SimpleDateFormat("dd MMM H:mm:ss");
	
	@Override
	public String format(LogRecord record)
	{
		final Object[] params = record.getParameters();
		final StringBuilder output = StringUtil.startAppend(30 + record.getMessage().length() + (params == null ? 0 : params.length * 10), "[", dateFmt.format(new Date(record.getMillis())), "] ", record.getMessage());
		
		if (params != null)
		{
			for (Object p : params)
			{
				if (p == null)
				{
					continue;
				}
				
				StringUtil.append(output, ", ");
				
				if (p instanceof L2GameClient)
				{
					final L2GameClient client = (L2GameClient) p;
					String address = null;
					try
					{
						if (!client.isDetached())
						{
							address = client.getConnectionAddress().getHostAddress();
						}
					}
					catch (Exception e)
					{
					}
					
					switch ((ConnectionState) client.getConnectionState())
					{
						case IN_GAME:
						{
							if (client.getActiveChar() != null)
							{
								StringUtil.append(output, client.getActiveChar().getName());
								StringUtil.append(output, "(", String.valueOf(client.getActiveChar().getObjectId()), ") ");
							}
							break;
						}
						case AUTHENTICATED:
						{
							if (client.getAccountName() != null)
							{
								StringUtil.append(output, client.getAccountName(), " ");
							}
							break;
						}
						case CONNECTED:
						{
							if (address != null)
							{
								StringUtil.append(output, address);
							}
							break;
						}
						default:
						{
							throw new IllegalStateException("Missing state on switch");
						}
					}
				}
				else if (p instanceof L2PcInstance)
				{
					final L2PcInstance player = (L2PcInstance) p;
					StringUtil.append(output, player.getName());
					StringUtil.append(output, "(", String.valueOf(player.getObjectId()), ")");
				}
				else
				{
					StringUtil.append(output, p.toString());
				}
			}
		}
		
		output.append(Config.EOL);
		return output.toString();
	}
}

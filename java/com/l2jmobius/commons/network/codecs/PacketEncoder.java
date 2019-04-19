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
package com.l2jmobius.commons.network.codecs;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jmobius.commons.network.IOutgoingPacket;
import com.l2jmobius.commons.network.PacketWriter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Nos
 */
@Sharable
public class PacketEncoder extends MessageToByteEncoder<IOutgoingPacket>
{
	private static final Logger LOGGER = Logger.getLogger(PacketEncoder.class.getName());
	
	private final int _maxPacketSize;
	
	public PacketEncoder(int maxPacketSize)
	{
		super();
		_maxPacketSize = maxPacketSize;
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, IOutgoingPacket packet, ByteBuf out)
	{
		try
		{
			if (packet.write(new PacketWriter(out)))
			{
				if (out.writerIndex() > _maxPacketSize)
				{
					throw new IllegalStateException("Packet (" + packet + ") size (" + out.writerIndex() + ") is bigger than the limit (" + _maxPacketSize + ")");
				}
			}
			else
			{
				// Avoid sending the packet
				out.clear();
			}
		}
		catch (Throwable e)
		{
			LOGGER.log(Level.WARNING, "Failed sending Packet(" + packet + ")", e);
			// Avoid sending the packet if some exception happened
			out.clear();
		}
	}
}
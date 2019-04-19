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
package com.l2jmobius.gameserver.network.clientpackets;

import com.l2jmobius.commons.network.PacketReader;
import com.l2jmobius.gameserver.enums.Movie;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.holders.MovieHolder;
import com.l2jmobius.gameserver.network.L2GameClient;

/**
 * @author JIV
 */
public final class EndScenePlayer implements IClientIncomingPacket
{
	private int _movieId;
	
	@Override
	public boolean read(L2GameClient client, PacketReader packet)
	{
		_movieId = packet.readD();
		return true;
	}
	
	@Override
	public void run(L2GameClient client)
	{
		final L2PcInstance activeChar = client.getActiveChar();
		if ((activeChar == null) || (_movieId == 0))
		{
			return;
		}
		final MovieHolder movieHolder = activeChar.getMovieHolder();
		if (movieHolder == null)
		{
			return;
		}
		final Movie movie = movieHolder.getMovie();
		if (movie.getClientId() != _movieId)
		{
			return;
		}
		activeChar.stopMovie();
		activeChar.setIsTeleporting(true, false); // avoid to get player removed from L2World
		activeChar.decayMe();
		activeChar.spawnMe(activeChar.getX(), activeChar.getY(), activeChar.getZ());
		activeChar.setIsTeleporting(false, false);
	}
}

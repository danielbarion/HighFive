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
package conquerablehalls.FortressOfResistance;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.l2jmobius.gameserver.cache.HtmCache;
import com.l2jmobius.gameserver.data.sql.impl.ClanTable;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.L2Spawn;
import com.l2jmobius.gameserver.model.Location;
import com.l2jmobius.gameserver.model.actor.L2Npc;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.entity.clanhall.ClanHallSiegeEngine;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jmobius.gameserver.util.Util;

/**
 * Fortress of Resistance clan hall siege Script.
 * @author BiggBoss
 */
public final class FortressOfResistance extends ClanHallSiegeEngine
{
	private final int MESSENGER = 35382;
	private final int BLOODY_LORD_NURKA = 35375;
	
	private final Location[] NURKA_COORDS =
	{
		new Location(45109, 112124, -1900), // 30%
		new Location(47653, 110816, -2110), // 40%
		new Location(47247, 109396, -2000)
		// 30%
	};
	
	private L2Spawn _nurka;
	private final Map<Integer, Long> _damageToNurka = new HashMap<>();
	private NpcHtmlMessage _messengerMsg;
	
	private FortressOfResistance()
	{
		super(FORTRESS_RESSISTANCE);
		addFirstTalkId(MESSENGER);
		addKillId(BLOODY_LORD_NURKA);
		addAttackId(BLOODY_LORD_NURKA);
		buildMessengerMessage();
		
		try
		{
			_nurka = new L2Spawn(BLOODY_LORD_NURKA);
			_nurka.setAmount(1);
			_nurka.setRespawnDelay(10800);
//			@formatter:off
//			int chance = getRandom(100) + 1;
//			if (chance <= 30)
//			{
//				coords = NURKA_COORDS[0];
//			}
//			else if ((chance > 30) && (chance <= 70))
//			{
//				coords = NURKA_COORDS[1];
//			}
//			else
//			{
//				coords = NURKA_COORDS[2];
//			}
//			@formatter:on
			_nurka.setLocation(NURKA_COORDS[0]);
		}
		catch (Exception e)
		{
			LOGGER.warning(getName() + ": Couldnt set the Bloody Lord Nurka spawn");
			e.printStackTrace();
		}
	}
	
	private final void buildMessengerMessage()
	{
		final String html = HtmCache.getInstance().getHtm(null, "data/scripts/conquerablehalls/FortressOfResistance/partisan_ordery_brakel001.htm");
		if (html != null)
		{
			// FIXME: We don't have an object id to put in here :(
			_messengerMsg = new NpcHtmlMessage();
			_messengerMsg.setHtml(html);
			_messengerMsg.replace("%nextSiege%", Util.formatDate(_hall.getSiegeDate().getTime(), "yyyy-MM-dd HH:mm:ss"));
		}
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		player.sendPacket(_messengerMsg);
		return null;
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isSummon)
	{
		if (!_hall.isInSiege())
		{
			return null;
		}
		
		final int clanId = player.getClanId();
		if (clanId > 0)
		{
			final long clanDmg = (_damageToNurka.containsKey(clanId)) ? _damageToNurka.get(clanId) + damage : damage;
			_damageToNurka.put(clanId, clanDmg);
			
		}
		return null;
	}
	
	@Override
	public String onKill(L2Npc npc, L2PcInstance killer, boolean isSummon)
	{
		if (!_hall.isInSiege())
		{
			return null;
		}
		
		_missionAccomplished = true;
		
		synchronized (this)
		{
			npc.getSpawn().stopRespawn();
			npc.deleteMe();
			cancelSiegeTask();
			endSiege();
		}
		return null;
	}
	
	@Override
	public L2Clan getWinner()
	{
		int winnerId = 0;
		long counter = 0;
		for (Entry<Integer, Long> e : _damageToNurka.entrySet())
		{
			final long dam = e.getValue();
			if (dam > counter)
			{
				winnerId = e.getKey();
				counter = dam;
			}
		}
		return ClanTable.getInstance().getClan(winnerId);
	}
	
	@Override
	public void onSiegeStarts()
	{
		_nurka.init();
	}
	
	@Override
	public void onSiegeEnds()
	{
		buildMessengerMessage();
	}
	
	public static void main(String[] args)
	{
		new FortressOfResistance();
	}
}
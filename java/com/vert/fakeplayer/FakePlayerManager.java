package com.vert.fakeplayer;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.model.L2Clan;
import com.l2jmobius.gameserver.model.L2World;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.serverpackets.PledgeShowMemberListUpdate;
import com.vert.fakeplayer.helpers.FakeHelpers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vert
 */
public enum FakePlayerManager {
    INSTANCE;

    FakePlayerManager() {

    }

    public void initialise() {
        FakePlayerNameManager.INSTANCE.initialise();
        FakePlayerTaskManager.INSTANCE.initialise();
    }

    public FakePlayer spawnPlayer(int x, int y, int z) {
        FakePlayer activeChar = FakeHelpers.createRandomFakePlayer();
//        L2World.getInstance().addObject(activeChar);

        if (Config.PLAYER_SPAWN_PROTECTION > 0)
            activeChar.setSpawnProtection(true);

        activeChar.spawnMe(x, y, z);
        activeChar.onPlayerEnter();
        handlePlayerClanOnSpawn(activeChar);

//        if (!activeChar.isGM() && (!activeChar.isInSiege() || activeChar.getSiegeState() < 2) && activeChar.isInsideZone(ZoneId.SIEGE)) {
//            activeChar.teleToLocation(L2TeleportLocation.TOWN);
//        }

        activeChar.heal();
        return activeChar;
    }

    public void despawnFakePlayer(int objectId) {
        L2PcInstance player = L2World.getInstance().getPlayer(objectId);
        if (player instanceof FakePlayer) {
            FakePlayer fakePlayer = (FakePlayer) player;
            fakePlayer.despawnPlayer();
        }
    }

    private static void handlePlayerClanOnSpawn(FakePlayer activeChar) {
        final L2Clan clan = activeChar.getClan();
        if (clan != null) {
            clan.getClanMember(activeChar.getObjectId()).setPlayerInstance(activeChar);

//            final SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.CLAN_MEMBER_S1_HAS_LOGGED_INTO_GAME).addCharName(activeChar);
            final PledgeShowMemberListUpdate update = new PledgeShowMemberListUpdate(activeChar);

            // Send packets to others members.
            for (L2PcInstance member : clan.getOnlineMembers(-1)) {
                if (member == activeChar)
                    continue;

//                member.sendPacket(msg);
                member.sendPacket(update);
            }

//            for (Castle castle : CastleManager.getInstance().getCastles()) {
//                final Siege siege = castle.getSiege();
//                if (!siege.isInProgress())
//                    continue;
//
//                final SiegeSide type = siege.getSide(clan);
//                if (type == SiegeSide.ATTACKER)
//                    activeChar.setSiegeState((byte) 1);
//                else if (type == SiegeSide.DEFENDER || type == SiegeSide.OWNER)
//                    activeChar.setSiegeState((byte) 2);
//            }
        }
    }

    public int getFakePlayersCount() {
        return getFakePlayers().size();
    }

    public List<FakePlayer> getFakePlayers() {
        return L2World.getInstance().getPlayers().stream().filter(x -> x instanceof FakePlayer).map(x -> (FakePlayer) x)
                .collect(Collectors.toList());
    }
}

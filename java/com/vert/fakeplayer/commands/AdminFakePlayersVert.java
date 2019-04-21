package com.vert.fakeplayer.commands;

import com.l2jmobius.gameserver.handler.IAdminCommandHandler;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.vert.fakeplayer.FakePlayer;
import com.vert.fakeplayer.FakePlayerManager;
import com.vert.fakeplayer.FakePlayerTaskManager;
import com.vert.fakeplayer.ai.others.EnchanterAI;
import com.vert.fakeplayer.ai.walkers.GiranWalkerAI;

/**
 * @author vert
 */
public class AdminFakePlayersVert implements IAdminCommandHandler {
    private final String fakesFolder = "data/html/admin/fakeplayers/";
    private static final String[] ADMIN_COMMANDS =
            {
                    "admin_takecontrol",
                    "admin_releasecontrol",
                    "admin_fakes",
                    "admin_spawnrandom",
                    "admin_deletefake",
                    "admin_spawnenchanter",
                    "admin_spawnwalker"
            };

    @Override
    public String[] getAdminCommandList()
    {
        return ADMIN_COMMANDS;
    }

    private void showFakeDashboard(L2PcInstance activeChar) {
        final NpcHtmlMessage html = new NpcHtmlMessage(0);
        html.setFile(activeChar, fakesFolder + "index.htm");
        html.replace("%fakecount%", FakePlayerManager.INSTANCE.getFakePlayersCount());
        html.replace("%taskcount%", FakePlayerTaskManager.INSTANCE.getTaskCount());
        activeChar.sendPacket(html);
    }

    @Override
    public boolean useAdminCommand(String command, L2PcInstance activeChar)
    {
        if (command.startsWith("admin_fakes"))
        {
            showFakeDashboard(activeChar);
        }

        if(command.startsWith("admin_deletefake")) {
            if(activeChar.getTarget() != null && activeChar.getTarget() instanceof FakePlayer) {
                FakePlayer fakePlayer = (FakePlayer)activeChar.getTarget();
                fakePlayer.despawnPlayer();
            }
            return true;
        }

        if(command.startsWith("admin_spawnwalker")) {
            if(command.contains(" ")) {
                String locationName = command.split(" ")[1];
                FakePlayer fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(activeChar.getX(),activeChar.getY(),activeChar.getZ());
                switch(locationName) {
                    case "giran":
                        fakePlayer.setFakeAi(new GiranWalkerAI(fakePlayer));
                        break;
                }
                return true;
            }

            return true;
        }

        if(command.startsWith("admin_spawnenchanter")) {
            FakePlayer fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(activeChar.getX(),activeChar.getY(),activeChar.getZ());
            fakePlayer.setFakeAi(new EnchanterAI(fakePlayer));
            return true;
        }

        if (command.startsWith("admin_spawnrandom")) {
            FakePlayer fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(activeChar.getX(),activeChar.getY(),activeChar.getZ());
            fakePlayer.assignDefaultAI();
            if(command.contains(" ")) {
                String arg = command.split(" ")[1];
                if(arg.equalsIgnoreCase("htm")) {
                    showFakeDashboard(activeChar);
                }
            }
            return true;
        }
		/*if (command.startsWith("admin_takecontrol"))
		{
			if(activeChar.getTarget() != null && activeChar.getTarget() instanceof FakePlayer) {
				FakePlayer fakePlayer = (FakePlayer)activeChar.getTarget();
				fakePlayer.setUnderControl(true);
				activeChar.setPlayerUnderControl(fakePlayer);
				activeChar.sendMessage("You are now controlling: " + fakePlayer.getName());
				return true;
			}

			activeChar.sendMessage("You can only take control of a Fake Player");
			return true;
		}
		if (command.startsWith("admin_releasecontrol"))
		{
			if(activeChar.isControllingFakePlayer()) {
				FakePlayer fakePlayer = activeChar.getPlayerUnderControl();
				activeChar.sendMessage("You are no longer controlling: " + fakePlayer.getName());
				fakePlayer.setUnderControl(false);
				activeChar.setPlayerUnderControl(null);
				return true;
			}

			activeChar.sendMessage("You are not controlling a Fake Player");
			return true;
		}*/
        return true;
    }
}

package com.vert.autopilot.commands;

import com.l2jmobius.gameserver.handler.IAdminCommandHandler;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.FakePlayerManager;
import com.vert.autopilot.FakePlayerTaskManager;
import com.vert.autopilot.ai.others.EnchanterAI;
import com.vert.autopilot.ai.walkers.GiranWalkerAI;

/**
 * @author vert
 */
public class AdminAutopilotEngine implements IAdminCommandHandler {
    private final String fakesFolder = "data/html/admin/autopilot/";
    private static final String[] ADMIN_COMMANDS =
            {
                    "admin_takecontrol",
                    "admin_releasecontrol",
                    "admin_fakes",
                    "admin_spawnrandom",
                    "admin_deletefake",
                    "admin_spawnenchanter",
                    "admin_spawnwalker",
                    "admin_fakespawn"
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

    private void spawnFakePlayer(String occupation, L2PcInstance activeChar, String level) {
        FakePlayer fakePlayer;

        if (occupation != null && level != null) {
            fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(occupation, level, activeChar.getX(), activeChar.getY(), activeChar.getZ());
        } else if (occupation != null && level == null) {
            fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(occupation, null, activeChar.getX(), activeChar.getY(), activeChar.getZ());
        } else {
            fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(null, null, activeChar.getX(),activeChar.getY(),activeChar.getZ());
        }

        fakePlayer.assignDefaultAI();
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
                FakePlayer fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(null, null, activeChar.getX(),activeChar.getY(),activeChar.getZ());
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
            FakePlayer fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(null, null, activeChar.getX(),activeChar.getY(),activeChar.getZ());
            fakePlayer.setFakeAi(new EnchanterAI(fakePlayer));
            return true;
        }

        if (command.startsWith("admin_spawnrandom")) {
            FakePlayer fakePlayer = FakePlayerManager.INSTANCE.spawnPlayer(null, null, activeChar.getX(),activeChar.getY(),activeChar.getZ());
            fakePlayer.assignDefaultAI();
            if(command.contains(" ")) {
                String arg = command.split(" ")[1];
                if(arg.equalsIgnoreCase("htm")) {
                    showFakeDashboard(activeChar);
                }
            }
            return true;
        }

        if (command.startsWith("admin_fakespawn")) {
            if(command.contains(" ")) {
                String[] args = command.split(" ");

                String occupation = null;
                String htm = null;
                String level = null;

                switch (args.length - 1) {
                    case 2:
                        occupation = args[1];
                        htm = args[2];
                        break;
                    case 3:
                        occupation = args[1];
                        htm = args[2];
                        level = args[3];
                        break;
                    default:
                        break;
                }

                spawnFakePlayer(occupation, activeChar, level);

                if(htm.equalsIgnoreCase("htm")) {
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

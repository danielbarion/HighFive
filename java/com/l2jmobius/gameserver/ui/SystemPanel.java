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
package com.l2jmobius.gameserver.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.GameServer;
import com.l2jmobius.gameserver.instancemanager.PlayerCountManager;
import com.l2jmobius.gameserver.util.Locator;

/**
 * @author Mobius
 */
public class SystemPanel extends JPanel
{
	static final long startTime = System.currentTimeMillis();
	
	public SystemPanel()
	{
		setBackground(Color.WHITE);
		setBounds(500, 20, 284, 140);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, false));
		setOpaque(true);
		setLayout(null);
		
		JLabel lblProtocol = new JLabel("Protocol");
		lblProtocol.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblProtocol.setBounds(10, 5, 264, 17);
		add(lblProtocol);
		
		JLabel lblConnected = new JLabel("Connected");
		lblConnected.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblConnected.setBounds(10, 23, 264, 17);
		add(lblConnected);
		
		JLabel lblMaxConnected = new JLabel("Max connected");
		lblMaxConnected.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblMaxConnected.setBounds(10, 41, 264, 17);
		add(lblMaxConnected);
		
		JLabel lblOfflineShops = new JLabel("Offline trade");
		lblOfflineShops.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblOfflineShops.setBounds(10, 59, 264, 17);
		add(lblOfflineShops);
		
		JLabel lblElapsedTime = new JLabel("Elapsed time");
		lblElapsedTime.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblElapsedTime.setBounds(10, 77, 264, 17);
		add(lblElapsedTime);
		
		JLabel lblJavaVersion = new JLabel("Build JDK");
		lblJavaVersion.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblJavaVersion.setBounds(10, 95, 264, 17);
		add(lblJavaVersion);
		
		JLabel lblBuildDate = new JLabel("Build date");
		lblBuildDate.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblBuildDate.setBounds(10, 113, 264, 17);
		add(lblBuildDate);
		
		// Set initial values.
		lblProtocol.setText("Protocol: 0");
		lblConnected.setText("Connected: 0");
		lblMaxConnected.setText("Max connected: 0");
		lblOfflineShops.setText("Offline trade: 0");
		lblElapsedTime.setText("Elapsed: 0 sec");
		lblJavaVersion.setText("Java version: " + System.getProperty("java.version"));
		lblBuildDate.setText("Build date: Unavailable");
		try
		{
			File jarName = Locator.getClassSource(GameServer.class);
			JarFile jarFile = new JarFile(jarName);
			Attributes attrs = jarFile.getManifest().getMainAttributes();
			lblBuildDate.setText("Build date: " + attrs.getValue("Build-Date").split(" ")[0]);
			jarFile.close();
		}
		catch (Exception e)
		{
		}
		
		// Initial update tasks.
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				lblProtocol.setText((Config.PROTOCOL_LIST.size() > 1 ? "Protocols: " : "Protocol: ") + Config.PROTOCOL_LIST.toString());
			}
		}, 4500);
		
		// Repeating elapsed time tasks.
		new Timer().scheduleAtFixedRate(new TimerTask()
		{
			@Override
			public void run()
			{
				lblConnected.setText("Connected: " + PlayerCountManager.getInstance().getConnectedCount());
				lblMaxConnected.setText("Max connected: " + PlayerCountManager.getInstance().getMaxConnectedCount());
				lblOfflineShops.setText("Offline trade: " + PlayerCountManager.getInstance().getOfflineTradeCount());
				lblElapsedTime.setText("Elapsed: " + getDurationBreakdown(System.currentTimeMillis() - startTime));
			}
		}, 1000, 1000);
	}
	
	static String getDurationBreakdown(long millis)
	{
		long days = TimeUnit.MILLISECONDS.toDays(millis);
		millis -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		
		return (days + "d " + hours + "h " + minutes + "m " + seconds + "s");
	}
}

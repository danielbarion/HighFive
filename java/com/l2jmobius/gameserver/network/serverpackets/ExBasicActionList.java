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
import com.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author KenM
 */
public final class ExBasicActionList implements IClientOutgoingPacket
{
	//@formatter:off
	public static final int[] ACTIONS_ON_TRANSFORM =
	{
		1, 2, 3, 4,
		5, 6, 7, 8,
		9, 11, 15, 16,
		17, 18, 19, 21,
		22, 23, 32, 36,
		39, 40, 41, 42,
		43, 44, 45, 46,
		47, 48, 50, 52,
		53, 54, 55, 56,
		57, 63, 64, 65,
		70, 1000, 1001, 1003,
		1004, 1005, 1006, 1007,
		1008, 1009, 1010, 1011,
		1012, 1013, 1014, 1015,
		1016, 1017, 1018, 1019,
		1020, 1021, 1022, 1023,
		1024, 1025, 1026, 1027,
		1028, 1029, 1030, 1031,
		1032, 1033, 1034, 1035,
		1036, 1037, 1038, 1039,
		1040, 1041, 1042, 1043,
		1044, 1045, 1046, 1047,
		1048, 1049, 1050, 1051,
		1052, 1053, 1054, 1055,
		1056, 1057, 1058, 1059,
		1060, 1061, 1062, 1063,
		1064, 1065, 1066, 1067,
		1068, 1069, 1070, 1071,
		1072, 1073, 1074, 1075,
		1076, 1077, 1078, 1079,
		1080, 1081, 1082, 1083,
		1084, 1089, 1090, 1091,
		1092, 1093, 1094, 1095,
		1096, 1097, 1098 
	};
	//@formatter:on
	public static final int[] DEFAULT_ACTION_LIST;
	
	static
	{
		final int count1 = 74; // 0 <-> (count1 - 1)
		final int count2 = 99; // 1000 <-> (1000 + count2 - 1)
		final int count3 = 16; // 5000 <-> (5000 + count3 - 1)
		DEFAULT_ACTION_LIST = new int[count1 + count2 + count3];
		int i;
		
		for (i = count1; i-- > 0;)
		{
			DEFAULT_ACTION_LIST[i] = i;
		}
		
		for (i = count2; i-- > 0;)
		{
			DEFAULT_ACTION_LIST[count1 + i] = 1000 + i;
		}
		
		for (i = count3; i-- > 0;)
		{
			DEFAULT_ACTION_LIST[count1 + count2 + i] = 5000 + i;
		}
	}
	
	public static final ExBasicActionList STATIC_PACKET = new ExBasicActionList(DEFAULT_ACTION_LIST);
	
	private final int[] _actionIds;
	
	public ExBasicActionList(int[] actionIds)
	{
		_actionIds = actionIds;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_BASIC_ACTION_LIST.writeId(packet);
		packet.writeD(_actionIds.length);
		for (int _actionId : _actionIds)
		{
			packet.writeD(_actionId);
		}
		return true;
	}
}

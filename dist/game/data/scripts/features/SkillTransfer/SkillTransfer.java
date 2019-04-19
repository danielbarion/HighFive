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
package features.SkillTransfer;

import com.l2jmobius.Config;
import com.l2jmobius.gameserver.data.xml.impl.ClassListData;
import com.l2jmobius.gameserver.data.xml.impl.SkillTreesData;
import com.l2jmobius.gameserver.enums.IllegalActionPunishmentType;
import com.l2jmobius.gameserver.model.L2SkillLearn;
import com.l2jmobius.gameserver.model.PcCondOverride;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerProfessionCancel;
import com.l2jmobius.gameserver.model.events.impl.character.player.OnPlayerProfessionChange;
import com.l2jmobius.gameserver.model.holders.ItemHolder;
import com.l2jmobius.gameserver.model.itemcontainer.PcInventory;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.l2jmobius.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Skill Transfer feature.
 * @author Zoey76
 */
public final class SkillTransfer extends AbstractNpcAI
{
	private static final String HOLY_POMANDER = "HOLY_POMANDER_";
	private static final ItemHolder[] PORMANDERS =
	{
		// Cardinal (97)
		new ItemHolder(15307, 1),
		// Eva's Saint (105)
		new ItemHolder(15308, 1),
		// Shillen Saint (112)
		new ItemHolder(15309, 4)
	};
	
	private SkillTransfer()
	{
		setPlayerProfessionChangeId(this::onProfessionChange);
		setPlayerProfessionCancelId(this::onProfessionCancel);
		setOnEnterWorld(Config.SKILL_CHECK_ENABLE);
	}
	
	public void onProfessionChange(OnPlayerProfessionChange event)
	{
		final L2PcInstance player = event.getActiveChar();
		final int index = getTransferClassIndex(player);
		if (index < 0)
		{
			return;
		}
		
		final String name = HOLY_POMANDER + player.getClassId().getId();
		if (!player.getVariables().getBoolean(name, false))
		{
			player.getVariables().set(name, true);
			giveItems(player, PORMANDERS[index]);
		}
	}
	
	public void onProfessionCancel(OnPlayerProfessionCancel event)
	{
		final L2PcInstance player = event.getActiveChar();
		final int index = getTransferClassIndex(player);
		
		// is a transfer class
		if (index < 0)
		{
			return;
		}
		
		int pomanderId = PORMANDERS[index].getId();
		// remove unsused HolyPomander
		PcInventory inv = player.getInventory();
		for (L2ItemInstance itemI : inv.getAllItemsByItemId(pomanderId))
		{
			inv.destroyItem("[HolyPomander - remove]", itemI, player, null);
		}
		// remove holy pomander variable
		final String name = HOLY_POMANDER + event.getClassId();
		player.getVariables().remove(name);
	}
	
	@Override
	public String onEnterWorld(L2PcInstance player)
	{
		if (!player.canOverrideCond(PcCondOverride.SKILL_CONDITIONS) || Config.SKILL_CHECK_GM)
		{
			final int index = getTransferClassIndex(player);
			if (index < 0)
			{
				return super.onEnterWorld(player);
			}
			long count = PORMANDERS[index].getCount() - player.getInventory().getInventoryItemCount(PORMANDERS[index].getId(), -1, false);
			for (Skill sk : player.getAllSkills())
			{
				for (L2SkillLearn s : SkillTreesData.getInstance().getTransferSkillTree(player.getClassId()).values())
				{
					if (s.getSkillId() == sk.getId())
					{
						// Holy Weapon allowed for Shilien Saint/Inquisitor stance
						if ((sk.getId() == 1043) && (index == 2) && player.isInStance())
						{
							continue;
						}
						
						count--;
						if (count < 0)
						{
							final String className = ClassListData.getInstance().getClass(player.getClassId()).getClassName();
							Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " has too many transfered skills or items, skill:" + s.getName() + " (" + sk.getId() + "/" + sk.getLevel() + "), class:" + className, IllegalActionPunishmentType.BROADCAST);
							if (Config.SKILL_CHECK_REMOVE)
							{
								player.removeSkill(sk);
							}
						}
					}
				}
			}
			// SkillTransfer or HolyPomander missing
			if (count > 0)
			{
				player.getInventory().addItem("[HolyPomander - missing]", PORMANDERS[index].getId(), count, player, null);
			}
		}
		return super.onEnterWorld(player);
	}
	
	private static int getTransferClassIndex(L2PcInstance player)
	{
		switch (player.getClassId())
		{
			case CARDINAL:
			{
				return 0;
			}
			case EVA_SAINT:
			{
				return 1;
			}
			case SHILLIEN_SAINT:
			{
				return 2;
			}
			default:
			{
				return -1;
			}
		}
	}
	
	public static void main(String[] args)
	{
		new SkillTransfer();
	}
}

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
package ai.areas.Gracia;

import java.util.logging.Level;
import java.util.logging.Logger;

import ai.areas.Gracia.AI.EnergySeeds;
import ai.areas.Gracia.AI.Lindvior;
import ai.areas.Gracia.AI.Maguen;
import ai.areas.Gracia.AI.StarStones;
import ai.areas.Gracia.AI.NPC.AbyssGaze.AbyssGaze;
import ai.areas.Gracia.AI.NPC.EkimusMouth.EkimusMouth;
import ai.areas.Gracia.AI.NPC.FortuneTelling.FortuneTelling;
import ai.areas.Gracia.AI.NPC.GeneralDilios.GeneralDilios;
import ai.areas.Gracia.AI.NPC.Klemis.Klemis;
import ai.areas.Gracia.AI.NPC.Lekon.Lekon;
import ai.areas.Gracia.AI.NPC.Nemo.Nemo;
import ai.areas.Gracia.AI.NPC.Nottingale.Nottingale;
import ai.areas.Gracia.AI.NPC.Seyo.Seyo;
import ai.areas.Gracia.AI.NPC.ZealotOfShilen.ZealotOfShilen;
import ai.areas.Gracia.AI.SeedOfAnnihilation.SeedOfAnnihilation;
import ai.areas.Gracia.instances.HallOfErosionAttack.HallOfErosionAttack;
import ai.areas.Gracia.instances.HallOfErosionDefence.HallOfErosionDefence;
import ai.areas.Gracia.instances.HallOfSufferingAttack.HallOfSufferingAttack;
import ai.areas.Gracia.instances.HallOfSufferingDefence.HallOfSufferingDefence;
import ai.areas.Gracia.instances.HeartInfinityAttack.HeartInfinityAttack;
import ai.areas.Gracia.instances.HeartInfinityDefence.HeartInfinityDefence;
import ai.areas.Gracia.instances.SecretArea.SecretArea;
import ai.areas.Gracia.instances.SeedOfDestruction.SeedOfDestruction;
import ai.areas.Gracia.vehicles.AirShipGludioGracia.AirShipGludioGracia;
import ai.areas.Gracia.vehicles.KeucereusNorthController.KeucereusNorthController;
import ai.areas.Gracia.vehicles.KeucereusSouthController.KeucereusSouthController;
import ai.areas.Gracia.vehicles.SoDController.SoDController;
import ai.areas.Gracia.vehicles.SoIController.SoIController;

/**
 * Gracia class-loader.
 * @author Pandragon
 */
public final class GraciaLoader
{
	private static final Logger LOGGER = Logger.getLogger(GraciaLoader.class.getName());
	
	private static final Class<?>[] SCRIPTS =
	{
		// AIs
		EnergySeeds.class,
		Lindvior.class,
		Maguen.class,
		StarStones.class,
		// NPCs
		AbyssGaze.class,
		EkimusMouth.class,
		FortuneTelling.class,
		GeneralDilios.class,
		Klemis.class,
		Lekon.class,
		Nemo.class,
		Nottingale.class,
		Seyo.class,
		ZealotOfShilen.class,
		// Seed of Annihilation
		SeedOfAnnihilation.class,
		// Instances
		SecretArea.class,
		SeedOfDestruction.class,
		HallOfErosionAttack.class,
		HallOfErosionDefence.class,
		HallOfSufferingAttack.class,
		HallOfSufferingDefence.class,
		HeartInfinityAttack.class,
		HeartInfinityDefence.class,
		// Vehicles
		AirShipGludioGracia.class,
		KeucereusNorthController.class,
		KeucereusSouthController.class,
		SoIController.class,
		SoDController.class,
	};
	
	public static void main(String[] args)
	{
		LOGGER.info(GraciaLoader.class.getSimpleName() + ": Loading Gracia related scripts.");
		for (Class<?> script : SCRIPTS)
		{
			try
			{
				script.getDeclaredConstructor().newInstance();
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, GraciaLoader.class.getSimpleName() + ": Failed loading " + script.getSimpleName() + ":", e);
			}
		}
	}
}

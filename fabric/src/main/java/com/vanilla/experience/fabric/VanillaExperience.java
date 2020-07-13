package com.vanilla.experience.fabric;

import com.vanilla.experience.fabric.enhancedbonemeal.EnhancedBoneMeal;
import com.vanilla.experience.fabric.enhancedbonemeal.EnhancedBoneMealDispenserBehaviour;
import com.vanilla.experience.fabric.enhancedseeds.EnhancedSeeds;
import com.vanilla.experience.fabric.enhancedseeds.EnhancedSeedsDispenserBehaviour;
import com.vanilla.experience.HelloMessage;
import net.fabricmc.api.ModInitializer;

public class VanillaExperience implements ModInitializer {

	@Override
	public void onInitialize() {
		new HelloMessage();
		new EnhancedBoneMeal();
		new EnhancedSeeds();
		new EnhancedBoneMealDispenserBehaviour();
		new EnhancedSeedsDispenserBehaviour();
	}
}

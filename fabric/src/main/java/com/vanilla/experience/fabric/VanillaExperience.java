package com.vanilla.experience.fabric;

import com.vanilla.experience.Config;
import com.vanilla.experience.fabric.datapack.DataPackDisabler;
import com.vanilla.experience.fabric.enhancedbonemeal.EnhancedBoneMeal;
import com.vanilla.experience.fabric.enhancedbonemeal.EnhancedBoneMealDispenserBehaviour;
import com.vanilla.experience.fabric.enhancedburning.FlintAndSteelAttack;
import com.vanilla.experience.fabric.enhancedkelp.EnhancedKelp;
import com.vanilla.experience.fabric.enhancedkelp.EnhancedKelpDispenserBehaviour;
import com.vanilla.experience.fabric.enhancedseeds.EnhancedSeeds;
import com.vanilla.experience.fabric.enhancedseeds.EnhancedSeedsDispenserBehaviour;
import com.vanilla.experience.HelloMessage;
import com.vanilla.experience.fabric.utils.VexUtils;
import net.fabricmc.api.ModInitializer;

public class VanillaExperience implements ModInitializer {

	@Override
	public void onInitialize() {
		Config.ConfigBean config = VexUtils.getConfig();

		new HelloMessage();
		if(config.isEnhancedBoneMealEnabled) {
			new EnhancedBoneMeal();
			new EnhancedBoneMealDispenserBehaviour();
		}
		if(config.isEnhancedSeedsEnabled) {
			new EnhancedSeeds();
			new EnhancedSeedsDispenserBehaviour();
		}
		if(config.isEnhancedKelpEnabled) {
			new EnhancedKelp();
			new EnhancedKelpDispenserBehaviour();
		}
		if(!config.isDataPackEnabled) {
			new DataPackDisabler();
		}
		if(config.isEnhancedBurningEnabled) {
			new FlintAndSteelAttack();
		}
	}

}

package com.vanilla.experience.fabric;

import com.vanilla.experience.fabric.enhancedbonemeal.EnhancedBoneMeal;
import com.vanilla.experience.fabric.enhancedbonemeal.EnhancedBoneMealDispenserBehaviour;
import com.vanilla.experience.fabric.enhancedburning.FlintAndSteelAttack;
import com.vanilla.experience.fabric.enhancedkelp.EnhancedKelp;
import com.vanilla.experience.fabric.enhancedkelp.EnhancedKelpDispenserBehaviour;
import com.vanilla.experience.fabric.enhancedseeds.EnhancedSeeds;
import com.vanilla.experience.fabric.enhancedseeds.EnhancedSeedsDispenserBehaviour;
import com.vanilla.experience.HelloMessage;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.MobSpawnerBlockEntity;

public class VanillaExperience implements ModInitializer {

	@Override
	public void onInitialize() {
		new HelloMessage();
		new EnhancedBoneMeal();
		new EnhancedSeeds();
		new EnhancedKelp();
		new EnhancedBoneMealDispenserBehaviour();
		new EnhancedSeedsDispenserBehaviour();
		new EnhancedKelpDispenserBehaviour();
		new FlintAndSteelAttack();
		ZombieEntity
	}

}

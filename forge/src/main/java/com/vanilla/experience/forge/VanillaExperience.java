package com.vanilla.experience.forge;

import com.vanilla.experience.forge.enhancedberries.*;
import com.vanilla.experience.forge.enhancedbonemeal.*;
import com.vanilla.experience.forge.enhancedseeds.*;
import com.vanilla.experience.forge.enhancedtotem.*;
import com.vanilla.experience.forge.fishingunpatch.override.FishingUnpatch;
import com.vanilla.experience.forge.utils.OverrideLoader;
import com.vanilla.experience.forge.utils.VexUtils;
import com.vanilla.experience.HelloMessage;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("vanillaexperience")
public class VanillaExperience {
    private static final Logger LOGGER = LogManager.getLogger();

    public VanillaExperience() {
        new HelloMessage();

        new OverrideLoader();

        new EnhancedBoneMealDispenserBehaviour();
        new EnhancedSeedsDispenserBehaviour();

        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLLoadCompleteEvent e) -> {
            new EnhancedBoneMeal();
            new EnhancedSeeds();
            new EnhancedBerries();
            new EnhancedTotem();
            if(!VexUtils.mixinExists()) new FishingUnpatch();
        });

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }
}
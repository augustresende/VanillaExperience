package com.vanilla.experience.forge;

import com.vanilla.experience.forge.enhancedberries.*;
import com.vanilla.experience.forge.enhancedbonemeal.*;
import com.vanilla.experience.forge.enhancedice.EnhancedIce;
import com.vanilla.experience.forge.enhancedkelp.EnhancedKelp;
import com.vanilla.experience.forge.enhancedkelp.EnhancedKelpDispenserBehaviour;
import com.vanilla.experience.forge.enhancedseeds.*;
import com.vanilla.experience.forge.enhancedtotem.*;
import com.vanilla.experience.forge.fishingunpatch.override.FishingUnpatch;
import com.vanilla.experience.forge.utils.OverrideLoader;
import com.vanilla.experience.forge.utils.VexUtils;
import com.vanilla.experience.HelloMessage;
import net.minecraftforge.fml.ExtensionPoint;
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
        new EnhancedKelpDispenserBehaviour();

        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLLoadCompleteEvent e) -> {
            new EnhancedBoneMeal();
            new EnhancedSeeds();
            new EnhancedKelp();
            new EnhancedBerries();
            new EnhancedTotem();
            new EnhancedIce();
            new FishingUnpatch();
        });

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }
}
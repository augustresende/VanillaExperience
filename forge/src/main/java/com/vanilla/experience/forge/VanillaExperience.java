package com.vanilla.experience.forge;

import com.vanilla.experience.Config;
import com.vanilla.experience.forge.datapack.*;
import com.vanilla.experience.forge.enhancedberries.*;
import com.vanilla.experience.forge.enhancedbonemeal.*;
import com.vanilla.experience.forge.enhancedburning.*;
import com.vanilla.experience.forge.enhancedice.*;
import com.vanilla.experience.forge.enhancedkelp.*;
import com.vanilla.experience.forge.enhancedseeds.*;
import com.vanilla.experience.forge.enhancedtotem.*;
import com.vanilla.experience.forge.slimepatch.*;
import com.vanilla.experience.forge.utils.VexUtils;
import com.vanilla.experience.HelloMessage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

@Mod("vanillaexperience")
public class VanillaExperience {

    public VanillaExperience() {
        MinecraftForge.EVENT_BUS.register(this);
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
        if(config.isEnhancedBerriesEnabled) {
            new EnhancedBerries();
        }
        if(config.isEnhancedTotemEnabled) {
            new EnhancedTotem();
        }
        if(config.isEnhancedIceEnabled) {
            new EnhancedIce();
        }
        if(config.isEnhancedBurningEnabled) {
            new EnhancedBurning();
        }
        if(config.isSlimeSuperFlatPatchEnabled) {
            new SlimePatch();
        }

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }
}
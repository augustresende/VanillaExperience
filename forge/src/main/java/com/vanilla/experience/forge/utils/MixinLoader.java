package com.vanilla.experience.forge.utils;

import com.vanilla.experience.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinLoader implements IMixinConnector {

    private static final Logger LOGGER = LogManager.getLogger();
    private Config.ConfigBean config = VexUtils.getConfig();

    @Override
    public void connect() {
        if(config.isZeroTickUnpatchEnabled && VexUtils.isZeroTickPatched()) {
            if(VexUtils.isZeroTickAbstractChanged()) {
                LOGGER.info("[VEX] Unpatching ZeroTick with Forge Mixin.");
                Mixins.addConfiguration("mixin/forge/zerotickunpatch.mixins.json");
            } else {
                LOGGER.info("[VEX] Unpatching ZeroTick with Forge Mixin.");
                Mixins.addConfiguration("mixin/forge/zerotickunpatchold.mixins.json");
            }
        }
        if(config.isWitherRosesUnpatchEnabled && VexUtils.isWitherRosesSpawnPatched()) {
            LOGGER.info("[VEX] Unpatcregistryhing Wither Rose spawning with Forge Mixin.");
            Mixins.addConfiguration("mixin/forge/witherrosesunpatch.mixins.json");
        }
        if(config.isProtectionUnpatchEnabled && VexUtils.isProtectionPatched()) {
            LOGGER.info("[VEX] Unpatching Protection Enchantments with Forge Mixin.");
            Mixins.addConfiguration("mixin/forge/protectionunpatch.mixins.json");
        }
        if(config.isFishingUnpatchEnabled && VexUtils.isFishingPatched()) {
            LOGGER.info("[VEX] Unpatching Fishing with Forge Mixin.");
            Mixins.addConfiguration("mixin/forge/fishingunpatch.mixins.json");
        }
    }
}
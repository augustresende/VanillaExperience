package com.vanilla.experience.forge.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinLoader implements IMixinConnector {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void connect() {
        if(VexUtils.isZeroTickPatched()) {
            LOGGER.info("[VEX] Unpatching ZeroTick with MixinBootstrap.");
            Mixins.addConfiguration("mixin/forge/zerotickunpatch.mixins.json");
        }
        if(VexUtils.isWitherRosesSpawnPatched()) {
            LOGGER.info("[VEX] Unpatching Wither Rose spawning with MixinBootstrap.");
            Mixins.addConfiguration("mixin/forge/witherrosesunpatch.mixins.json");
        }
    }
}
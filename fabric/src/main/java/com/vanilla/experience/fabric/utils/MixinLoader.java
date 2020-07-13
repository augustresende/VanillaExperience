package com.vanilla.experience.fabric.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

public class MixinLoader implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void run() {
        LOGGER.info("[VEX] Patching EnhancedTotem with Fabric Mixin.");
        Mixins.addConfiguration("mixin/fabric/enhancedtotem.mixins.json");

        LOGGER.info("[VEX] Unpatching Protection Enchantments with Fabric Mixin.");
        Mixins.addConfiguration("mixin/fabric/protectionunpatch.mixins.json");

        if (VexUtils.isZeroTickPatched()) {
            LOGGER.info("[VEX] Unpatching ZeroTick with Fabric Mixin.");
            Mixins.addConfiguration("mixin/fabric/zerotickunpatch.mixins.json");
        }
        if (VexUtils.isWitherRosesSpawnPatched()) {
            LOGGER.info("[VEX] Unpatching Wither Rose spawning with Fabric Mixin.");
            Mixins.addConfiguration("mixin/fabric/witherrosesunpatch.mixins.json");
        }
    }
}

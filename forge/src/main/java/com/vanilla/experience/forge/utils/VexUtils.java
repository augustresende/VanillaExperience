package com.vanilla.experience.forge.utils;

import com.vanilla.experience.CommonUtils;
import net.minecraft.util.SharedConstants;

public class VexUtils {
    public static boolean mixinExists() {
        try {
            Class.forName("org.spongepowered.asm.launch.MixinBootstrap");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isZeroTickPatched() {
        return SharedConstants.getVersion().getWorldVersion() >= CommonUtils.zeroTickVersionPatch;
    }

    public static boolean isWitherRosesSpawnPatched() {
        return SharedConstants.getVersion().getWorldVersion() >= CommonUtils.witherRosesVersionPatch;
    }
}

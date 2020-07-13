package com.vanilla.experience.fabric.utils;

import net.minecraft.SharedConstants;
import com.vanilla.experience.CommonUtils;

public class VexUtils {
    public static boolean isZeroTickPatched() {
        return SharedConstants.getGameVersion().getWorldVersion() >= CommonUtils.zeroTickVersionPatch;
    }

    public static boolean isWitherRosesSpawnPatched() {
        return SharedConstants.getGameVersion().getWorldVersion() >= CommonUtils.witherRosesVersionPatch;
    }
}

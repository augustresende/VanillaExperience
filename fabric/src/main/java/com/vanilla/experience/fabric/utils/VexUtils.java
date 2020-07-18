package com.vanilla.experience.fabric.utils;

import static net.minecraft.MinecraftVersion.field_25319;
import com.vanilla.experience.CommonUtils;

public class VexUtils {
    private static int worldVersion = field_25319.getWorldVersion();
    public static boolean isZeroTickPatched() {
        return worldVersion >= CommonUtils.zeroTickVersionPatch;
    }

    public static boolean isWitherRosesSpawnPatched() {
        return worldVersion >= CommonUtils.witherRosesVersionPatch;
    }

    public static boolean isProtectionPatched() {
        return worldVersion >= CommonUtils.protectionVersionPatch;
    }
}

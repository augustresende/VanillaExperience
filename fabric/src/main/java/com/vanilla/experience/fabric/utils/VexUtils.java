package com.vanilla.experience.fabric.utils;

import static net.minecraft.MinecraftVersion.field_25319;
import com.vanilla.experience.CommonUtils;

public class VexUtils {

    private static final CommonUtils utils = new CommonUtils(field_25319.getWorldVersion());

    public static boolean isZeroTickPatched() {
        return CommonUtils.isZeroTickPatched();
    }

    public static boolean isWitherRosesSpawnPatched() {
        return CommonUtils.isWitherRosesSpawnPatched();
    }

    public static boolean isProtectionPatched() {
        return CommonUtils.isProtectionPatched();
    }

    public static boolean isFishingPatched() {
        return CommonUtils.isFishingPatched();
    }
}

package com.vanilla.experience.forge.utils;

import com.vanilla.experience.CommonUtils;
import com.vanilla.experience.Config;
import static net.minecraft.util.MinecraftVersion.GAME_VERSION;

public class VexUtils {

    private static final CommonUtils utils = new CommonUtils(GAME_VERSION.getWorldVersion());

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

    public static boolean isZeroTickAbstractChanged() {
        return CommonUtils.isZeroTickAbstractChanged();
    }

    public static Config.ConfigBean getConfig() { return CommonUtils.getConfig(); }

    public static String getModId() {
        return CommonUtils.modId;
    }
}

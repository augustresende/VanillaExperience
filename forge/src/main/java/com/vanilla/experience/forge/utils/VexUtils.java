package com.vanilla.experience.forge.utils;

import com.vanilla.experience.CommonUtils;
import net.minecraftforge.versions.forge.ForgeVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;;

import static net.minecraft.util.MinecraftVersion.GAME_VERSION;

public class VexUtils {
    private static boolean mixinExistsVerify() {
        try {
            Class.forName("org.spongepowered.asm.launch.MixinBootstrap");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean mixinExists = mixinExistsVerify();

    public static boolean mixinExists() {
        return mixinExists;
    }

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
}

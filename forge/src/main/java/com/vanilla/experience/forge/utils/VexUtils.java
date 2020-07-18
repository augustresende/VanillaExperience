package com.vanilla.experience.forge.utils;

import com.vanilla.experience.CommonUtils;
import net.minecraftforge.versions.forge.ForgeVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;;

import static net.minecraft.util.MinecraftVersion.GAME_VERSION;

public class VexUtils {
    private static final Logger LOGGER = LogManager.getLogger();
    private static int worldVersion = GAME_VERSION.getWorldVersion();

    public static boolean mixinExists() {
        try {
            Class.forName("org.spongepowered.asm.launch.MixinBootstrap");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

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

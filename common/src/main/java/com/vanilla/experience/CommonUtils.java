package com.vanilla.experience;

public class CommonUtils {

    public static int zeroTickVersionPatch = 2524;
    public static int witherRosesVersionPatch = 2554;
    public static int protectionVersionPatch = 1965;
    public static int fishingVersionPatch = 2520;
    public static String modId = "vanillaexperience";

    private static int worldVersion;

    public CommonUtils(int version) {
        worldVersion = version;
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

    public static boolean isFishingPatched() {
        return worldVersion >= CommonUtils.fishingVersionPatch;
    }
}
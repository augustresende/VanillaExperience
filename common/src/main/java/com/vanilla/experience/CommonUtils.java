package com.vanilla.experience;

import java.io.File;

public class CommonUtils {

    public static int zeroTickVersionPatch = 2524;
    public static int witherRosesVersionPatch = 2554;
    public static int protectionVersionPatch = 1965;
    public static int fishingVersionPatch = 2520;
    public static int zeroTickAbstractChange = 2569;
    public static String modId = "vanillaexperience";

    private static int worldVersion;
    private static final File configFilePath = new File("./config/"+ modId +".json");
    private static Config.ConfigBean currentConfig = new Config.ConfigBean();

    public CommonUtils(int version) {
        worldVersion = version;

        currentConfig = new Config(configFilePath).get();
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

    public static boolean isZeroTickAbstractChanged() {
        return worldVersion >= zeroTickAbstractChange;
    }

    public static Config.ConfigBean getConfig() {
        return currentConfig;
    }
}
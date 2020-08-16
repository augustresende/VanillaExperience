package com.vanilla.experience;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private File configFilePath;

    private static final Logger LOGGER = LogManager.getLogger();

    public Config(File configFilePath) {
        this.configFilePath = configFilePath;
    }

    public static class ConfigBean {
        public boolean isDataPackEnabled = true;
        public boolean isEnhancedBerriesEnabled = true;
        public boolean isEnhancedBoneMealEnabled = true;
        public boolean isEnhancedBurningEnabled = true;
        public boolean isEnhancedIceEnabled = true;
        public boolean isEnhancedKelpEnabled = true;
        public boolean isEnhancedSeedsEnabled = true;
        public boolean isEnhancedTotemEnabled = true;
        public boolean isFishingUnpatchEnabled = true;
        public boolean isProtectionUnpatchEnabled = true;
        public boolean isSlimeSuperFlatPatchEnabled = true;
        public boolean isWitherRosesUnpatchEnabled = true;
        public boolean isZeroTickUnpatchEnabled = true;

        public int currentFileVersionPleaseNeverChangeThisModInternalUsageOnly = 1;
    }

    private ConfigBean currentConfig = null;

    public ConfigBean get() {
        if(currentConfig != null) return currentConfig;

        if(configFilePath.exists()) currentConfig = this.load();

        if(currentConfig != null) return currentConfig;

        currentConfig = new ConfigBean();

        this.writeConfigFile(currentConfig);
        return currentConfig;
    }

    private ConfigBean load() {
        ConfigBean currentConfig = this.readConfigFile(configFilePath);
        if (currentConfig != null) {
            if(currentConfig.currentFileVersionPleaseNeverChangeThisModInternalUsageOnly == 1) return currentConfig;
            // UPGRADE CONFIG ROUTINES
        }
        return null;
    }

    public ConfigBean readConfigFile(File path) {
        try (FileReader file = new FileReader(path)) {
            Gson gson = new Gson();
            return gson.fromJson(file, ConfigBean.class);
        }
        catch (IOException e) {
            LOGGER.error("Failed to read from config.");
            return null;
        }
    }

    public void writeConfigFile(ConfigBean config) {
        if (!configFilePath.getParentFile().exists() && !configFilePath.getParentFile().mkdirs()) {
            LOGGER.error("Failed to write the config.");
            return;
        }
        try (FileWriter file = new FileWriter(configFilePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(config));
            file.flush();
        }
        catch (IOException e) {
            LOGGER.error("Failed to write the config.");
        }
    }
}

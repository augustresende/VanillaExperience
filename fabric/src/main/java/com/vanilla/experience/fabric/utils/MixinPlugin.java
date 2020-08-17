package com.vanilla.experience.fabric.utils;

import com.vanilla.experience.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {

    private static final Logger LOGGER = LogManager.getLogger();
    private boolean sentZeroTickMessage = false;
    private Config.ConfigBean config = VexUtils.getConfig();

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if(mixinClassName.equals("com.vanilla.experience.fabric.enhancedtotem.mixin.EnhancedTotemMixin")) {
            if(config.isEnhancedTotemEnabled) {
                LOGGER.info("[VEX] Patching EnhancedTotem with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.enhancedberries.mixin.SweetBerryBushBlockMixin")) {
            if(config.isEnhancedBerriesEnabled) {
                LOGGER.info("[VEX] Patching EnhancedBerries with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.slimepatch.mixin.SlimePatchMixin")) {
            if(config.isSlimeSuperFlatPatchEnabled) {
                LOGGER.info("[VEX] Patching Slimes in SuperFlat with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.enhancedice.mixin.EnhancedIceMixin")) {
            if(config.isEnhancedIceEnabled) {
                LOGGER.info("[VEX] Patching EnhancedIce with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.contains("com.vanilla.experience.fabric.zerotickunpatch.mixin")) {
            if(config.isZeroTickUnpatchEnabled && VexUtils.isZeroTickPatched()) {
                if(!sentZeroTickMessage) {
                    sentZeroTickMessage = true;
                    LOGGER.info("[VEX] Unpatching ZeroTick with Fabric Mixin.");
                }
                if(mixinClassName.equals("com.vanilla.experience.fabric.zerotickunpatch.mixin.ZeroTickAbstractPlantPartBlock")) {
                    if(VexUtils.isZeroTickAbstractChanged()) {
                        return true;
                    }
                    return false;
                }
                if(mixinClassName.equals("com.vanilla.experience.fabric.zerotickunpatch.mixin.ZeroTickAbstractPlantStemBlock")) {
                    if(!VexUtils.isZeroTickAbstractChanged()) {
                        return true;
                    }
                    return false;
                }
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.witherrosesunpatch.mixin.EntityTypeMixin")) {
            if(config.isWitherRosesUnpatchEnabled && VexUtils.isWitherRosesSpawnPatched()) {
                LOGGER.info("[VEX] Unpatching Wither Rose spawning with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.protectionunpatch.mixin.ProtectionUnpatchMixin")) {
            if(config.isProtectionUnpatchEnabled && VexUtils.isProtectionPatched()) {
                LOGGER.info("[VEX] Unpatching Protection Enchantments with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.fishingunpatch.mixin.FishingUnpatchMixin")) {
            if(config.isFishingUnpatchEnabled && VexUtils.isFishingPatched()) {
                LOGGER.info("[VEX] Unpatching Fishing with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.enhancedburning.mixin.FirePatchMixin")) {
            if(config.isEnhancedBurningEnabled) {
                LOGGER.info("[VEX] Patching EnhancedBurning with Fabric Mixin.");
                return true;
            }
        }
        return false;
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}

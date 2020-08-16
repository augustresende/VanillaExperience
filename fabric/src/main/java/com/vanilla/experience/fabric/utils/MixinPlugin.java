package com.vanilla.experience.fabric.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {

    private static final Logger LOGGER = LogManager.getLogger();
    private boolean sentZeroTickMessage = false;

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if(mixinClassName.equals("com.vanilla.experience.fabric.enhancedtotem.mixin.EnhancedTotemMixin")) {
            LOGGER.info("[VEX] Patching EnhancedTotem with Fabric Mixin.");
            return true;
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.enhancedberries.mixin.SweetBerryBushBlockMixin")) {
            LOGGER.info("[VEX] Patching EnhancedBerries with Fabric Mixin.");
            return true;
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.slimepatch.mixin.SlimePatchMixin")) {
            LOGGER.info("[VEX] Patching Slimes in SuperFlat with Fabric Mixin.");
            return true;
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.enhancedice.mixin.EnhancedIceMixin")) {
            LOGGER.info("[VEX] Patching EnhancedIce with Fabric Mixin.");
            return true;
        }
        if(mixinClassName.contains("com.vanilla.experience.fabric.zerotickunpatch")) {
            if (VexUtils.isZeroTickPatched()) {
                if(!sentZeroTickMessage) {
                    sentZeroTickMessage = true;
                    LOGGER.info("[VEX] Unpatching ZeroTick with Fabric Mixin.");
                }
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.witherrosesunpatch.mixin.EntityTypeMixin")) {
            if (VexUtils.isWitherRosesSpawnPatched()) {
                LOGGER.info("[VEX] Unpatching Wither Rose spawning with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.protectionunpatch.mixin.ProtectionUnpatchMixin")) {
            if (VexUtils.isProtectionPatched()) {
                LOGGER.info("[VEX] Unpatching Protection Enchantments with Fabric Mixin.");
                return true;
            }
        }
        if(mixinClassName.equals("com.vanilla.experience.fabric.fishingunpatch.mixin.FishingUnpatchMixin")) {
            if (VexUtils.isFishingPatched()) {
                LOGGER.info("[VEX] Unpatching Fishing with Fabric Mixin.");
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

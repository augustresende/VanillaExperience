package com.vanilla.experience.forge.utils;

import com.vanilla.experience.CommonUtils;
import com.vanilla.experience.forge.protectionunpatch.override.ProtectionUnpatch;
import com.vanilla.experience.forge.witherrosesunpatch.override.WitherRose;
import com.vanilla.experience.forge.zerotickunpatch.override.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.MOD)
public class OverrideLoader {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        if(!VexUtils.mixinExists()) {
            if(VexUtils.isZeroTickPatched()) {
                LOGGER.info("[VEX] Unpatching ZeroTick, ignore the 'Potentially Dangerous' messages.");
                LOGGER.info("[VEX] If you want to disable the overrides, please install MixinBootstrap or use Fabric version.");
                blockRegistryEvent.getRegistry().register(new ZeroTickBambooBlock(Block.Properties.from(Blocks.BAMBOO)).setRegistryName("minecraft", "bamboo"));
                blockRegistryEvent.getRegistry().register(new ZeroTickCactusBlock(Block.Properties.from(Blocks.CACTUS)).setRegistryName("minecraft", "cactus"));
                blockRegistryEvent.getRegistry().register(new ZeroTickSugarCaneBlock(Block.Properties.from(Blocks.SUGAR_CANE)).setRegistryName("minecraft", "sugar_cane"));
                blockRegistryEvent.getRegistry().register(new ZeroTickKelpTopBlock(Block.Properties.from(Blocks.KELP)).setRegistryName("minecraft", "kelp"));
                blockRegistryEvent.getRegistry().register(new ZeroTickWeepingVinesTopBlock(Block.Properties.from(Blocks.WEEPING_VINES)).setRegistryName("minecraft", "weeping_vines"));
                blockRegistryEvent.getRegistry().register(new ZeroTickTwistingVinesTopBlock(Block.Properties.from(Blocks.TWISTING_VINES)).setRegistryName("minecraft", "twisting_vines"));
                blockRegistryEvent.getRegistry().register(new ZeroTickChorusFlowerBlock(Block.Properties.from(Blocks.CHORUS_FLOWER)).setRegistryName("minecraft", "chorus_flower"));
            }

            if(VexUtils.isWitherRosesSpawnPatched()) {
                LOGGER.info("[VEX] Unpatching Wither Rose spawning, ignore the 'Potentially Dangerous' messages.");
                LOGGER.info("[VEX] If you want to disable the overrides, please install MixinBootstrap or use Fabric version.");
                blockRegistryEvent.getRegistry().register(new WitherRose(Block.Properties.from(Blocks.WITHER_ROSE)).setRegistryName("minecraft", "wither_rose"));
            }
        }
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        if(!VexUtils.mixinExists()) {
            if(VexUtils.isZeroTickPatched()) {
                LOGGER.info("[VEX] Restoring ZeroTick Block Items");
                LOGGER.info("[VEX] If you want to disable the overrides, please install MixinBootstrap or use Fabric version.");
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.BAMBOO, new Item.Properties().group(Items.BAMBOO.getGroup())).setRegistryName("minecraft", "bamboo"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CACTUS, new Item.Properties().group(Items.CACTUS.getGroup())).setRegistryName("minecraft", "cactus"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.SUGAR_CANE, new Item.Properties().group(Items.SUGAR_CANE.getGroup())).setRegistryName("minecraft", "sugar_cane"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.KELP, new Item.Properties().group(Items.KELP.getGroup())).setRegistryName("minecraft", "kelp"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WEEPING_VINES, new Item.Properties().group(Items.WEEPING_VINES.getGroup())).setRegistryName("minecraft", "weeping_vines"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.TWISTING_VINES, new Item.Properties().group(Items.TWISTING_VINES.getGroup())).setRegistryName("minecraft", "twisting_vines"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CHORUS_FLOWER, new Item.Properties().group(Items.CHORUS_FLOWER.getGroup())).setRegistryName("minecraft", "chorus_flower"));
            }

            if(VexUtils.isWitherRosesSpawnPatched()) {
                LOGGER.info("[VEX] Restoring Wither Rose Block Item");
                LOGGER.info("[VEX] If you want to disable the block overrides, please install MixinBootstrap or use Fabric version.");
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.WITHER_ROSE, new Item.Properties().group(Items.CHORUS_FLOWER.getGroup())).setRegistryName("minecraft", "wither_rose"));
            }
        }
    }

    private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

    @SubscribeEvent
    public static void onEnchantmentsRegister(final RegistryEvent.Register<Enchantment> enchantmentRegister) {
        if(!VexUtils.mixinExists()){
            if(VexUtils.isProtectionPatched()) {
                LOGGER.info("[VEX] Unpatching Protection Enchantment, ignore the 'Potentially Dangerous' messages.");
                LOGGER.info("[VEX] If you want to disable the overrides, please install MixinBootstrap or use Fabric version.");
                enchantmentRegister.getRegistry().register(new ProtectionUnpatch(Enchantments.PROTECTION.getRarity(), ProtectionEnchantment.Type.ALL, ARMOR_SLOTS).setRegistryName("minecraft", "protection"));
                enchantmentRegister.getRegistry().register(new ProtectionUnpatch(Enchantments.FIRE_PROTECTION.getRarity(), ProtectionEnchantment.Type.FIRE, ARMOR_SLOTS).setRegistryName("minecraft", "fire_protection"));
                enchantmentRegister.getRegistry().register(new ProtectionUnpatch(Enchantments.FEATHER_FALLING.getRarity(), ProtectionEnchantment.Type.FALL, ARMOR_SLOTS).setRegistryName("minecraft", "feather_falling"));
                enchantmentRegister.getRegistry().register(new ProtectionUnpatch(Enchantments.BLAST_PROTECTION.getRarity(), ProtectionEnchantment.Type.EXPLOSION, ARMOR_SLOTS).setRegistryName("minecraft", "blast_protection"));
                enchantmentRegister.getRegistry().register(new ProtectionUnpatch(Enchantments.PROJECTILE_PROTECTION.getRarity(), ProtectionEnchantment.Type.PROJECTILE, ARMOR_SLOTS).setRegistryName("minecraft", "projectile_protection"));
            }
        }
    }
}

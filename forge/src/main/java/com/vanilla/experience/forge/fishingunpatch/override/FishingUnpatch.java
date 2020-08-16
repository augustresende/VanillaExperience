package com.vanilla.experience.forge.fishingunpatch.override;

import com.vanilla.experience.CommonUtils;
import com.vanilla.experience.forge.utils.VexUtils;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber
public class FishingUnpatch {

    public FishingUnpatch() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void lootTableLoad(LootTableLoadEvent event) {
        if(!VexUtils.mixinExists() && VexUtils.isFishingPatched()) {
            if (event.getName().equals(new ResourceLocation("minecraft", "gameplay/fishing"))) {
                LOGGER.info("[VEX] Unpatching Fishing with looting override. For better compatibility use MixinBootstrap or Fabric version.");
                event.getTable().removePool("main");
                event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(CommonUtils.modId, "inject/gameplay/fishing"))).build());
            }
        }
    }
}

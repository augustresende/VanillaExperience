package com.vanilla.experience;

import com.vanilla.experience.enhancedberries.*;
import com.vanilla.experience.enhancedbonemeal.*;
import com.vanilla.experience.zerotickunpatch.*;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SharedConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("vanillaexperience")
public class VanillaExperience
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public VanillaExperience() {
        /* // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff); */

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadCompleted);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        DispenserBlock.registerDispenseBehavior(Items.BONE_MEAL, new EnhancedBoneMealDispenserBehaviour());
    }

    private void loadCompleted(FMLLoadCompleteEvent e){
        MinecraftForge.EVENT_BUS.register(new EnhancedBoneMeal());
        MinecraftForge.EVENT_BUS.register(new EnhancedBerries());
    }

    /* private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        //LOGGER.info("HELLO FROM PREINIT");
        //LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        //LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        //InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        //LOGGER.info("Got IMC {}", event.getIMCStream().
        //        map(m->m.getMessageSupplier().get()).
        //        collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        //LOGGER.info("HELLO from server starting");
    } */

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            if(SharedConstants.getVersion().getWorldVersion() >= 2524){
                LOGGER.info("Unpatching ZeroTick, ignore the 'Potentially Dangerous' messages.");
                blockRegistryEvent.getRegistry().register(new ZeroTickBambooBlock(Block.Properties.from(Blocks.BAMBOO)).setRegistryName("minecraft", "bamboo"));
                blockRegistryEvent.getRegistry().register(new ZeroTickCactusBlock(Block.Properties.from(Blocks.CACTUS)).setRegistryName("minecraft", "cactus"));
                blockRegistryEvent.getRegistry().register(new ZeroTickSugarCaneBlock(Block.Properties.from(Blocks.SUGAR_CANE)).setRegistryName("minecraft", "sugar_cane"));
                blockRegistryEvent.getRegistry().register(new ZeroTickKelpTopBlock(Block.Properties.from(Blocks.KELP)).setRegistryName("minecraft", "kelp"));
                blockRegistryEvent.getRegistry().register(new ZeroTickWeepingVinesTopBlock(Block.Properties.from(Blocks.field_235384_mx_)).setRegistryName("minecraft", "weeping_vines"));
                blockRegistryEvent.getRegistry().register(new ZeroTickTwistingVinesTopBlock(Block.Properties.from(Blocks.field_235386_mz_)).setRegistryName("minecraft", "twisting_vines"));
                // blockRegistryEvent.getRegistry().register(new ZeroTickChorusFlowerBlock(new ChorusPlantBlock(Block.Properties.from(Blocks.CHORUS_PLANT)), Block.Properties.from(Blocks.CHORUS_FLOWER)).setRegistryName("minecraft", "chorus_flower"));
            }
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            if (SharedConstants.getVersion().getWorldVersion() >= 2524) {
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.BAMBOO, new Item.Properties().group(Items.BAMBOO.getGroup())).setRegistryName("minecraft", "bamboo"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CACTUS, new Item.Properties().group(Items.CACTUS.getGroup())).setRegistryName("minecraft", "cactus"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.SUGAR_CANE, new Item.Properties().group(Items.SUGAR_CANE.getGroup())).setRegistryName("minecraft", "sugar_cane"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.KELP, new Item.Properties().group(Items.KELP.getGroup())).setRegistryName("minecraft", "kelp"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.field_235384_mx_, new Item.Properties().group(Items.field_234718_bB_.getGroup())).setRegistryName("minecraft", "weeping_vines"));
                itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.field_235386_mz_, new Item.Properties().group(Items.field_234719_bC_.getGroup())).setRegistryName("minecraft", "twisting_vines"));
                // itemRegistryEvent.getRegistry().register(new BlockItem(Blocks.CHORUS_FLOWER, new Item.Properties().group(Items.CHORUS_FLOWER.getGroup())).setRegistryName("minecraft", "chorus_flower"));
            }
        }
    }
}


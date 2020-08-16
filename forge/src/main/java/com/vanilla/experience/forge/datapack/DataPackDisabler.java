package com.vanilla.experience.forge.datapack;

import com.vanilla.experience.forge.utils.VexUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@EventBusSubscriber
public class DataPackDisabler {

    public DataPackDisabler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        server.getCommandManager().handleCommand(server.getCommandSource().withPermissionLevel(2), "datapack disable \"mod:" + VexUtils.getModId() + "\"");
    }
}

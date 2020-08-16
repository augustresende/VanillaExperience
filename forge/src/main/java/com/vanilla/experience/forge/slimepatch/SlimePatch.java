package com.vanilla.experience.forge.slimepatch;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class SlimePatch {

    public SlimePatch() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onWorldJoin(EntityJoinWorldEvent e) {
        World world = e.getWorld();
        if(world instanceof ServerWorld) {
            ServerWorld serverworld = (ServerWorld)world;
            if (serverworld.getServer().func_240793_aU_().func_230418_z_().func_236228_i_() && e.getEntity() instanceof net.minecraft.entity.monster.SlimeEntity) e.setCanceled(true);
        }
    }
}

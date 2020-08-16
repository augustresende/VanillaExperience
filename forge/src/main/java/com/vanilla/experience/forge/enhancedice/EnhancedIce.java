package com.vanilla.experience.forge.enhancedice;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.CropGrowEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EnhancedIce {

    @SubscribeEvent
    public void growEvent(CropGrowEvent.Pre e) {
        World world = e.getWorld().getWorld();
        Block block = world.getBlockState(e.getPos().down(2)).getBlock();
        if (block.equals(Blocks.PACKED_ICE) || block.equals(Blocks.BLUE_ICE)) e.setResult(Event.Result.DENY);
    }
}

package com.vanilla.experience.enhancedbonemeal;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EnhancedBoneMeal {
    @SubscribeEvent
    public void onBoneMeal(BonemealEvent e) {

        World world = e.getWorld();

        PlayerEntity player = e.getPlayer();
        ItemStack hand = player.getHeldItem(Hand.MAIN_HAND);
        if (!hand.getItem().equals(Items.BONE_MEAL))
            return;

        BlockPos blockPos = e.getPos();
        BlockState state = world.getBlockState(blockPos);
        Block block = state.getBlock();

        BlockPos pos = e.getPos();
        if(block.equals(Blocks.CACTUS) || block.equals((Blocks.SUGAR_CANE))) {
            for (int y = pos.getY(); y <= 256; y++) {
                BlockPos upper = new BlockPos(pos.getX(), y, pos.getZ());
                Block upperBlock = world.getBlockState(upper).getBlock();
                if (upperBlock.equals(Blocks.AIR)) {
                    world.setBlockState(upper, block.getDefaultState());
                    world.playEvent(2005, upper, 0);
                    world.playEvent(2005, upper.up(), 0);
                    if (!player.isCreative())
                        hand.shrink(1);
                    return;
                }
            }
        }
        if(block.equals(Blocks.VINE)) {
            for (int y = pos.getY(); y > 0; y--) {
                BlockPos down = new BlockPos(pos.getX(), y, pos.getZ());
                Block downBlock = world.getBlockState(down).getBlock();
                if (downBlock.equals(Blocks.AIR)) {
                    world.setBlockState(down, block.getDefaultState());
                    world.playEvent(2005, down, 0);
                    world.playEvent(2005, down.down(), 0);
                    if (!player.isCreative())
                        hand.shrink(1);
                    return;
                }
            }
        }
        return;
    }
}


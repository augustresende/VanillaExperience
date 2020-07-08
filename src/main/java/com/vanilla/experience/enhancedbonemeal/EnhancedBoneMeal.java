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
        ItemStack hand = null;
        if(player.getHeldItemMainhand().getItem().equals(Items.BONE_MEAL)) {
            hand = player.getHeldItemMainhand();
        } else if(player.getHeldItemOffhand().getItem().equals(Items.BONE_MEAL)) {
            hand = player.getHeldItemOffhand();
        }
        if(hand == null) return;

        BlockPos blockPos = e.getPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block currentBlock = blockState.getBlock();

        if(currentBlock.equals(Blocks.CACTUS) || currentBlock.equals((Blocks.SUGAR_CANE))) {
            for(int y = blockPos.getY(); y <= 256; y++) {
                BlockPos upperPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                Block upperBlock = world.getBlockState(upperPos).getBlock();
                if(upperBlock.equals(Blocks.AIR)) {
                    world.setBlockState(upperPos, currentBlock.getDefaultState());
                    world.playEvent(2005, upperPos, 0);
                    world.playEvent(2005, upperPos.up(), 0);
                    if(!player.isCreative())
                        hand.shrink(1);
                    return;
                }
            }
        }
        if(currentBlock.equals(Blocks.VINE)) {
            for(int y = blockPos.getY(); y > 0; y--) {
                BlockPos downPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                Block downBlock = world.getBlockState(downPos).getBlock();
                if(downBlock.equals(Blocks.AIR)) {
                    world.setBlockState(downPos, currentBlock.getDefaultState());
                    world.playEvent(2005, downPos, 0);
                    world.playEvent(2005, downPos.down(), 0);
                    if(!player.isCreative())
                        hand.shrink(1);
                    return;
                }
            }
        }
        return;
    }
}


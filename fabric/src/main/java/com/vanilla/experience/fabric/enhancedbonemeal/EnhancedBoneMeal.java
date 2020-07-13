package com.vanilla.experience.fabric.enhancedbonemeal;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnhancedBoneMeal implements UseBlockCallback {

    public EnhancedBoneMeal(){
        UseBlockCallback.EVENT.register(this);
    }

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult block) {
        ItemStack stack = player.getStackInHand(hand);

        if(stack.getItem().equals(Items.BONE_MEAL)){
            BlockPos blockPos = block.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            Block currentBlock = blockState.getBlock();

            if(currentBlock.equals(Blocks.CACTUS) || currentBlock.equals((Blocks.SUGAR_CANE))) {
                for(int y = blockPos.getY(); y <= 256; y++) {
                    BlockPos upperPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block upperBlock = world.getBlockState(upperPos).getBlock();
                    if(upperBlock.equals(Blocks.AIR)) {
                        world.setBlockState(upperPos, currentBlock.getDefaultState());
                        if (!world.isClient) {
                            world.syncWorldEvent(2005, upperPos, 0);
                            world.syncWorldEvent(2005, upperPos.up(), 0);
                        }
                        if(!player.isCreative())
                            stack.decrement(1);
                        return ActionResult.SUCCESS;
                    }
                }
            }
            if(currentBlock.equals(Blocks.VINE)) {
                for(int y = blockPos.getY(); y > 0; y--) {
                    BlockPos downPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block downBlock = world.getBlockState(downPos).getBlock();
                    if(downBlock.equals(Blocks.AIR)) {
                        world.setBlockState(downPos, currentBlock.getDefaultState());
                        if (!world.isClient) {
                            world.syncWorldEvent(2005, downPos, 0);
                            world.syncWorldEvent(2005, downPos.down(), 0);
                        }
                        if(!player.isCreative())
                            stack.decrement(1);
                        return ActionResult.SUCCESS;
                    }
                }
            }

        }
        return ActionResult.PASS;
    }
}

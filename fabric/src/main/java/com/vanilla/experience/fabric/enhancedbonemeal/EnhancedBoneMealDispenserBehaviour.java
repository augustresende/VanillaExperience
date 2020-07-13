package com.vanilla.experience.fabric.enhancedbonemeal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;

public class EnhancedBoneMealDispenserBehaviour extends FallibleItemDispenserBehavior {

    public EnhancedBoneMealDispenserBehaviour() {
        DispenserBlock.registerBehavior(Items.BONE_MEAL, this);
    }

    protected ItemStack dispenseSilently(BlockPointer block, ItemStack stack) {
        this.setSuccess(true);
        ServerWorld world = (ServerWorld) block.getWorld();
        BlockPos blockPos = block.getBlockPos().offset(block.getBlockState().get(DispenserBlock.FACING));
        if (BoneMealItem.useOnFertilizable(stack, world, blockPos) || BoneMealItem.useOnGround(stack, world, blockPos, null)) {
            if (!world.isClient) {
                world.syncWorldEvent(2005, blockPos, 0);
            }
        } else {
            BlockState blockState = world.getBlockState(blockPos);
            Block currentBlock = blockState.getBlock();

            if(currentBlock.equals(Blocks.SUGAR_CANE) || currentBlock.equals(Blocks.CACTUS)) {
                for(int y = blockPos.getY(); y <= 256; y++) {
                    BlockPos upperPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block upperBlock = world.getBlockState(upperPos).getBlock();
                    if(upperBlock.equals(Blocks.AIR)) {
                        world.setBlockState(upperPos, blockState.getBlock().getDefaultState());
                        if (!world.isClient) {
                            world.syncWorldEvent(2005, upperPos, 0);
                            world.syncWorldEvent(2005, upperPos.up(), 0);
                        }
                        stack.decrement(1);
                        break;
                    }
                }
            } else if(blockState.getBlock().equals(Blocks.VINE)) {
                for(int y = blockPos.getY(); y > 0; y--) {
                    BlockPos downPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block downBlock = world.getBlockState(downPos).getBlock();
                    if (downBlock.equals(Blocks.AIR)) {
                        world.setBlockState(downPos, blockState.getBlock().getDefaultState());
                        if (!world.isClient) {
                            world.syncWorldEvent(2005, downPos, 0);
                            world.syncWorldEvent(2005, downPos.down(), 0);
                        }
                        stack.decrement(1);
                        break;
                    }
                }
            } else {
                this.setSuccess(false);
            }
        }
        return stack;
    }
}
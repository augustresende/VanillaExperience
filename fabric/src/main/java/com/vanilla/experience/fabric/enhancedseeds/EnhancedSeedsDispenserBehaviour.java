package com.vanilla.experience.fabric.enhancedseeds;

import net.minecraft.block.*;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnhancedSeedsDispenserBehaviour extends FallibleItemDispenserBehavior {

    public EnhancedSeedsDispenserBehaviour() {
        DispenserBlock.registerBehavior(Items.WHEAT_SEEDS, this);
    }

    protected ItemStack dispenseSilently(BlockPointer block, ItemStack stack) {
        this.setSuccess(true);
        ServerWorld world = (ServerWorld) block.getWorld();
        BlockPos blockPos = block.getBlockPos().offset(block.getBlockState().get(DispenserBlock.FACING));
        BlockState blockState = world.getBlockState(blockPos);
        Block currentBlock = blockState.getBlock();

        if(currentBlock.equals(Blocks.DIRT)) {
            world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState());
            world.syncWorldEvent(2005, blockPos, 0);
            stack.decrement(1);
        } else if(currentBlock.equals(Blocks.GRASS_BLOCK)) {
            BlockPos upperPos = blockPos.up();
            Block upperBlock = world.getBlockState(upperPos).getBlock();
            if(upperBlock.equals(Blocks.AIR)) {
                world.setBlockState(upperPos, Blocks.GRASS.getDefaultState());
                stack.decrement(1);
            } else if(upperBlock.equals(Blocks.GRASS)) {
                if(upgrade(world, upperPos)) {
                    stack.decrement(1);
                }
            }
        } else if(currentBlock.equals(Blocks.GRASS)) {
            if(upgrade(world, blockPos)) {
                stack.decrement(1);
            }
        } else {
            this.setSuccess(false);
        }

        return stack;
    }

    public boolean upgrade(World world, BlockPos pos) {
        TallPlantBlock blockDoublePlant = (TallPlantBlock) Blocks.TALL_GRASS;

        if(world.isAir(pos.up())) {
            blockDoublePlant.placeAt(world, pos, 2);
            return true;
        }
        return false;
    }
}
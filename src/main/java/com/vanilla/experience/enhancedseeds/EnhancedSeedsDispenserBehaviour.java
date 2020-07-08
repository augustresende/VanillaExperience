package com.vanilla.experience.enhancedseeds;

import net.minecraft.block.*;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnhancedSeedsDispenserBehaviour extends DefaultDispenseItemBehavior
{
    @Override
    public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        Direction facing = source.getBlockState().get(DispenserBlock.FACING);
        BlockPos blockPos = source.getBlockPos().offset(facing);
        World world = source.getWorld();

        BlockState blockState = world.getBlockState(blockPos);
        Block currentBlock = blockState.getBlock();

        if (currentBlock.equals(Blocks.DIRT)) {
            world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState());
            stack.shrink(1);
        } else if (currentBlock.equals(Blocks.GRASS_BLOCK)) {
            BlockPos upperPos = blockPos.up();
            Block upperBlock = world.getBlockState(upperPos).getBlock();
            if (upperBlock.equals(Blocks.AIR)) {
                world.setBlockState(upperPos, Blocks.GRASS.getDefaultState());
                stack.shrink(1);
            } else if (upperBlock.equals(Blocks.GRASS)) {
                if (upgrade(world, upperPos))
                    stack.shrink(1);
            }
        } else if (currentBlock.equals(Blocks.GRASS)) {
            if (upgrade(world, blockPos))
                stack.shrink(1);
        }
        return stack;
    }

    public boolean upgrade(World world, BlockPos pos) {
        DoublePlantBlock blockDoublePlant = (DoublePlantBlock)Blocks.TALL_GRASS;
        if (blockDoublePlant.getDefaultState().isValidPosition(world, pos) && world.isAirBlock(pos.up())) {
            blockDoublePlant.placeAt( world, pos, 2);
            return true;
        }
        return false;
    }
}
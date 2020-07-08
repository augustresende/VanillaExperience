package com.vanilla.experience.enhancedbonemeal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnhancedBoneMealDispenserBehaviour extends DefaultDispenseItemBehavior
{
    @Override
    public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        World world = source.getWorld();
        BlockPos blockpos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));
        if(!BoneMealItem.applyBonemeal(stack, world, blockpos) && !BoneMealItem.growSeagrass(stack, world, blockpos, (Direction)null)) {
            Direction facing = source.getBlockState().get(DispenserBlock.FACING);
            BlockPos blockPos = source.getBlockPos().offset(facing);

            BlockState blockState = world.getBlockState(blockPos);
            Block currentBlock = blockState.getBlock();

            if(currentBlock.equals(Blocks.SUGAR_CANE) || currentBlock.equals(Blocks.CACTUS)) {
                for(int y = blockPos.getY(); y <= 256; y++) {
                    BlockPos upperPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block upperBlock = world.getBlockState(upperPos).getBlock();
                    if(upperBlock.equals(Blocks.AIR)) {
                        world.setBlockState(upperPos, blockState.getBlock().getDefaultState());
                        world.playEvent(2005, upperPos, 0);
                        world.playEvent(2005, upperPos.up(), 0);
                        stack.shrink(1);
                        return stack;
                    }
                }
                return super.dispenseStack(source, stack);
            } else if(blockState.getBlock().equals(Blocks.VINE)) {
                for(int y = blockPos.getY(); y > 0; y--) {
                    BlockPos downPos = new BlockPos(blockPos.getX(), y, blockPos.getZ());
                    Block downBlock = world.getBlockState(downPos).getBlock();
                    if (downBlock.equals(Blocks.AIR)) {
                        world.setBlockState(downPos, blockState.getBlock().getDefaultState());
                        world.playEvent(2005, downPos, 0);
                        world.playEvent(2005, downPos.up(), 0);
                        stack.shrink(1);
                        return stack;
                    }
                }
                return stack;
            }
        } else {
            world.playEvent(2005, blockpos, 0);
            return stack;
        }

        return stack;
    }
}
package com.vanilla.experience.enhancedbonemeal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EnhancedBoneMealDispenserBehaviour extends DefaultDispenseItemBehavior
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        Direction facing = source.getBlockState().get(DispenserBlock.FACING);
        BlockPos pos = source.getBlockPos().offset(facing);
        World world = source.getWorld();

        BlockState blockState = world.getBlockState(pos);

        if(blockState.getBlock().equals(Blocks.SUGAR_CANE) || blockState.getBlock().equals(Blocks.CACTUS)) {
            for (int y = pos.getY(); y <= 256; y++) {
                BlockPos upper = new BlockPos(pos.getX(), y, pos.getZ());
                Block upperBlock = world.getBlockState(upper).getBlock();
                if (upperBlock.equals(Blocks.AIR)) {
                    world.setBlockState(upper, blockState.getBlock().getDefaultState());
                    world.playEvent(2005, upper, 0);
                    world.playEvent(2005, upper.up(), 0);
                    return stack.split(1);
                }
            }
            return stack;
        } else if (blockState.getBlock().equals(Blocks.VINE)) {
            for (int y = pos.getY(); y > 0; y--) {
                BlockPos down = new BlockPos(pos.getX(), y, pos.getZ());
                Block downBlock = world.getBlockState(down).getBlock();
                if (downBlock.equals(Blocks.AIR)) {
                    world.setBlockState(down, blockState.getBlock().getDefaultState());
                    world.playEvent(2005, down, 0);
                    world.playEvent(2005, down.up(), 0);
                    return stack.split(1);
                }
            }
            return stack;
        } else {
            return super.dispenseStack(source, stack);
        }
    }
}
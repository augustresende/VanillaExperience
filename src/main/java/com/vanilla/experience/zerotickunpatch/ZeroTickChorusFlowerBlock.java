package com.vanilla.experience.zerotickunpatch;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class ZeroTickChorusFlowerBlock extends ChorusFlowerBlock
{
    public ZeroTickChorusFlowerBlock(ChorusPlantBlock p_i48429_1_, Properties builder) {
        super(p_i48429_1_, builder);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if(!worldIn.isAirBlock(pos.down())) {
            super.randomTick(state, worldIn, pos, rand);
        }
        super.tick(state, worldIn, pos, rand);
    }
}
package com.vanilla.experience.zerotickunpatch;

import net.minecraft.block.KelpTopBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ZeroTickKelpTopBlock extends KelpTopBlock
{
    public ZeroTickKelpTopBlock(Properties properties) {
        super(properties);
    }
    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if(!worldIn.isAirBlock(pos.down())) {
            this.randomTick(state, worldIn, pos, rand);
        }
        super.tick(state, worldIn, pos, rand);
    }
}
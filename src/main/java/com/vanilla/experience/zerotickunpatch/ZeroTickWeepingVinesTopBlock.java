package com.vanilla.experience.zerotickunpatch;

import net.minecraft.block.WeepingVinesTopBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ZeroTickWeepingVinesTopBlock extends WeepingVinesTopBlock
{
    private static final Logger LOGGER = LogManager.getLogger();
    public ZeroTickWeepingVinesTopBlock(Properties properties) {
        super(properties);
    }
    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if(!worldIn.isAirBlock(pos.up())) {
            this.randomTick(state, worldIn, pos, rand);
        }
        LOGGER.info("DEBUG WEEPING");
        super.tick(state, worldIn, pos, rand);
    }
}
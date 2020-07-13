package com.vanilla.experience.forge.zerotickunpatch.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.BambooBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BambooBlock.class)
public class ZeroTickBambooBlock {
    private static final Logger LOGGER = LogManager.getLogger();

    @Shadow
    public void randomTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {}

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random, CallbackInfo info) {
        if(!world.isAirBlock(pos.down())) {
            this.randomTick(state, world, pos, random);
        }
    }

}
package com.vanilla.experience.fabric.enhancedice.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(CropBlock.class)
public class EnhancedIceMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        Block block = world.getBlockState(pos.down(2)).getBlock();
        if(block.equals(Blocks.PACKED_ICE) || block.equals(Blocks.BLUE_ICE)) ci.cancel();
    }

}

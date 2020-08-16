package com.vanilla.experience.fabric.zerotickunpatch.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractPlantPartBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

// this abstracts Kelp, TwistingVines and WeepingVines
@Mixin(AbstractPlantPartBlock.class)
public class ZeroTickAbstractPlantPartBlock extends AbstractBlock {

    public ZeroTickAbstractPlantPartBlock(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("TAIL"), method = "scheduledTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V")
    public void scheduledTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random, CallbackInfo info) {
        if(!world.isAir(pos.down())) {
            super.randomTick(state, world, pos, random);
        }
    }

    @Override
    public Item asItem() {
        return null;
    }

    @Override
    protected Block asBlock() {
        return null;
    }
}
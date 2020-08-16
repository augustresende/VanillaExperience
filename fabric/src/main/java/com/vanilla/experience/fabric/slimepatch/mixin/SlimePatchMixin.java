package com.vanilla.experience.fabric.slimepatch.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(SlimeEntity.class)
public class SlimePatchMixin {
    @Inject(method = "canSpawn", at = @At("RETURN"), cancellable = true)
    private static void canSpawn(EntityType<SlimeEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> info) {
        if(world instanceof ServerWorld) {
            ServerWorld serverworld = (ServerWorld)world;
            if(serverworld.getServer().getSaveProperties().getGeneratorOptions().isFlatWorld()) info.setReturnValue(false);
        }
    }
}

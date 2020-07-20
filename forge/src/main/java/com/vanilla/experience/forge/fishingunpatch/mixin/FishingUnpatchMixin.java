package com.vanilla.experience.forge.fishingunpatch.mixin;

import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public class FishingUnpatchMixin {
    @Inject(method = "func_234603_b_", cancellable = true, at = @At("RETURN"))
    private void isOpenOrWaterAround(BlockPos pos, CallbackInfoReturnable<Boolean> info){
        info.setReturnValue(true);
    }
}

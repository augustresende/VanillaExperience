package com.vanilla.experience.fabric.witherrosesunpatch.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityType.class)
public class EntityTypeMixin {

    @Redirect(method = "isInvalidSpawn(Lnet/minecraft/block/BlockState;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 2))
    private boolean restoreWitherRoseSpawning(BlockState blockState, Block block) {
        return false; // return false to method that checks if is inside a Whiter Rose
    }
}
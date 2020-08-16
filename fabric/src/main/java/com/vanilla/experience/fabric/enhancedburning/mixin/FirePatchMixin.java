package com.vanilla.experience.fabric.enhancedburning.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class FirePatchMixin extends Entity {

    public FirePatchMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource source, CallbackInfo info) {
        if(source.isFire()) {
            if(!this.isOnFire()) {
                this.setOnFireFor(1);
            }
        }
    }

    @Inject(method = "baseTick", at = @At("HEAD"))
    private void baseTick(CallbackInfo info) {
        if(this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
            this.extinguish();
        }
    }
}
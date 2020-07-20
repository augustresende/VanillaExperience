package com.vanilla.experience.fabric.enhancedtotem.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import org.apache.logging.log4j.core.jmx.Server;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class EnhancedTotemMixin {

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        LivingEntity livingEntity = ((LivingEntity)(Object)this);

        if(livingEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) livingEntity;

            ItemStack itemStack = null;

            PlayerInventory inventory = player.inventory;

            for(int i = 0; i < inventory.size(); i++) {
                ItemStack stack = inventory.getStack(i);
                if (stack.getItem().equals(Items.TOTEM_OF_UNDYING)) {
                    itemStack = stack;
                    break;
                }
            }

            if(itemStack != null) {
                player.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
                Criteria.USED_TOTEM.trigger(player, itemStack);

                itemStack.decrement(1);

                player.setHealth(1.0F);
                player.clearStatusEffects();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 2)); // 1.16.2
                player.world.sendEntityStatus(player, (byte)35);

                info.setReturnValue(true);
            }
        }
    }
}
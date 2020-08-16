package com.vanilla.experience.forge.enhancedburning;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EnhancedBurning {

    public EnhancedBurning(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingDeath (LivingDeathEvent event) {

        if (event.getSource().isFireDamage()) {
            if(!event.getEntityLiving().isBurning()) {
                event.getEntityLiving().setFire(1);
            }
        }
    }

    @SubscribeEvent
    public void onLivingTick (LivingUpdateEvent event) {
        if(event.getEntityLiving().isBurning() && event.getEntityLiving().isPotionActive(Effects.FIRE_RESISTANCE)) event.getEntityLiving().extinguish();
    }

    @SubscribeEvent
    public void onLivingAttack (LivingAttackEvent event) {

        Entity sourceEntity = event.getSource().getTrueSource();

        if(sourceEntity instanceof LivingEntity) {
            LivingEntity sourceLiving = (LivingEntity) sourceEntity;
            ItemStack heldItem = sourceLiving.getHeldItemMainhand();

            if(heldItem.getItem() == Items.FLINT_AND_STEEL) {
                 event.getEntityLiving().setFire(5);
                 ServerPlayerEntity player = sourceLiving instanceof ServerPlayerEntity ? (ServerPlayerEntity) sourceLiving : null;
                 if(player != null && !player.isCreative()) heldItem.attemptDamageItem(1, sourceLiving.getRNG(), player);
            }
        }
    }
}

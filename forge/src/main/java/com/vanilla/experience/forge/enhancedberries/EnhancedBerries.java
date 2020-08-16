package com.vanilla.experience.forge.enhancedberries;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EnhancedBerries {

    public EnhancedBerries() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onDamageEvent(LivingAttackEvent e) {
        if(!e.getSource().damageType.equals("sweetBerryBush")) return;

        if(e.getEntity().isCrouching()) e.setCanceled(true);
        if(e.getEntity() instanceof PlayerEntity
                && !((PlayerEntity) e.getEntity()).getItemStackFromSlot(EquipmentSlotType.FEET).isEmpty()
                && !((PlayerEntity) e.getEntity()).getItemStackFromSlot(EquipmentSlotType.LEGS).isEmpty()) e.setCanceled(true);
    }
}

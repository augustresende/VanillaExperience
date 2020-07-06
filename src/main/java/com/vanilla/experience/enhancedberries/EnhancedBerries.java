package com.vanilla.experience.enhancedberries;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EnhancedBerries {

    @SubscribeEvent
    public static void onDamageEvent(LivingAttackEvent e) {
        if(!e.getSource().damageType.equals("sweetBerryBush")) return;

        if(e.getEntity().isCrouching()) e.setCanceled(true);
        if(e.getEntity() instanceof PlayerEntity
                && !((PlayerEntity) e.getEntity()).getItemStackFromSlot(EquipmentSlotType.FEET).isEmpty()
                && !((PlayerEntity) e.getEntity()).getItemStackFromSlot(EquipmentSlotType.LEGS).isEmpty()) e.setCanceled(true);
    }
}

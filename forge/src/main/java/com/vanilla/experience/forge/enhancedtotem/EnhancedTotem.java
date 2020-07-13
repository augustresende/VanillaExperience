package com.vanilla.experience.forge.enhancedtotem;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EnhancedTotem {

    public EnhancedTotem(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent e){
        Entity entity = e.getEntity();
        World world = entity.getEntityWorld();
        if (!(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;

        ItemStack itemStack = null;
        if(player.getHeldItemMainhand().getItem().equals(Items.TOTEM_OF_UNDYING)) {
            itemStack = player.getHeldItemMainhand();
        } else if(player.getHeldItemOffhand().getItem().equals(Items.TOTEM_OF_UNDYING)) {
            itemStack = player.getHeldItemOffhand();
        }
        if(itemStack != null) return;

        PlayerInventory inventory = player.inventory;

        if(inventory == null);

        for(int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem().equals(Items.TOTEM_OF_UNDYING)) {
                itemStack = stack;
                break;
            }
        }
        if(itemStack == null) return;

        e.setCanceled(true);
        if(player instanceof ServerPlayerEntity) {
            ServerPlayerEntity entityPlayer = (ServerPlayerEntity) player;
            entityPlayer.addStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING));
            CriteriaTriggers.USED_TOTEM.trigger(entityPlayer, itemStack);
        }

        player.setHealth(player.getMaxHealth());
        player.clearActivePotions();
        player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 900, 1));
        player.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 100, 1));
        player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 800, 2)); // 1.16.2
        world.setEntityState(player, (byte)35);

        itemStack.shrink(1);
    }
}

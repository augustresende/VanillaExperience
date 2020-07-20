package com.vanilla.experience.fabric.enhancedburning;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class FlintAndSteelAttack implements AttackEntityCallback {

    public FlintAndSteelAttack(){
        AttackEntityCallback.EVENT.register(this);
    }

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult entityHitResult) {

        if(!world.isClient) {
            ItemStack stack = player.getStackInHand(hand);

            if(stack.getItem().equals(Items.FLINT_AND_STEEL)) {
                entity.setOnFireFor(5);
                if(player instanceof ServerPlayerEntity && !player.isCreative()) stack.damage(1, player.getRandom(), (ServerPlayerEntity) player);
            }
        }
        return ActionResult.PASS;
    }
}

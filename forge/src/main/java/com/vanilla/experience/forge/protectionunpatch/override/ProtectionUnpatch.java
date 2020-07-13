package com.vanilla.experience.forge.protectionunpatch.override;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class ProtectionUnpatch extends ProtectionEnchantment {
    public ProtectionUnpatch(Rarity rarityIn, Type protectionTypeIn, EquipmentSlotType... slots) {
        super(rarityIn, protectionTypeIn, slots);
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return true;
    }
}

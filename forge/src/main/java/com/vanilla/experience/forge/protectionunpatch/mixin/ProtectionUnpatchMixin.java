package com.vanilla.experience.forge.protectionunpatch.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public class ProtectionUnpatchMixin {

    @Inject(at = {@At("HEAD")}, method = {"canApplyTogether"}, cancellable = true)
    private void canApplyTogether(Enchantment other, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(true);
    }
}

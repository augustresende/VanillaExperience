package com.vanilla.experience.forge.utils;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinLoader implements IMixinConnector {

    @Override
    public void connect() {
        Mixins.addConfiguration("mixin/forge/zerotickunpatch.mixins.json");
        Mixins.addConfiguration("mixin/forge/witherrosesunpatch.mixins.json");
        Mixins.addConfiguration("mixin/forge/protectionunpatch.mixins.json");
        Mixins.addConfiguration("mixin/forge/fishingunpatch.mixins.json");
    }
}